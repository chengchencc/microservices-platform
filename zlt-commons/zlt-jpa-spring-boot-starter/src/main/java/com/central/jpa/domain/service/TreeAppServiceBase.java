package com.central.jpa.domain.service;



import com.central.jpa.domain.entity.TreeEntityBase;
import com.central.jpa.dto.TreeEntityDto;
import com.central.jpa.utils.ModelMapperUtils;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public abstract class TreeAppServiceBase<TRepository extends TreeAppRepository<TEntityBase, TKey>,TEntityBase extends TreeEntityBase<TEntityBase,TKey>,TEntityDto extends TreeEntityDto<TEntityDto,TKey >,TKey extends Serializable>
extends CrudAppServiceBase <TRepository,TEntityBase,TEntityDto,TKey>{
    public TreeAppServiceBase(TRepository repository) {
        super(repository);
    }

    /**添加子级节点*/
    public TEntityDto  addChild(TEntityDto dto,TKey key){
        if (key==null)
            throw new RuntimeException("传入ID不能为null!");
        TEntityBase parent = repository.findById(key).orElse(null);
        if (parent==null)
            throw new RuntimeException("传入的ID虽对应的节点不存在");
        TEntityBase tEntityBase = map(dto);
        tEntityBase = referSiblingOrParentAttributes(tEntityBase,parent);
        tEntityBase.setLeaf(true);//新增是叶子
        tEntityBase.setParentId(key);
        tEntityBase.setTlevel(parent.getTlevel()+1);//新增节点的层级=父节点的层级+1
        TEntityBase save = repository.saveAndFlush(tEntityBase);
        //子栏目创建成功后，再更改该栏目isLeaf
        if (parent.isLeaf()){
            parent.setLeaf(false);
            repository.saveAndFlush(parent);
        }
        dto.setId(save.getId());
        return dto;
    }

    /**添加首个树节点*/
    public TEntityDto addFirstRoot(TEntityDto dto) {
        TEntityBase tEntity = map(dto);
        tEntity.setLeaf(true);
        tEntity.setTlevel(1);
        TEntityBase save = repository.save(tEntity);
        dto.setId(save.getId());
        return dto;
    }

    /**添加同级节点*/
    public TEntityDto  addSibling(TEntityDto dto,TKey key){
        if (key==null){
            return this.addFirstRoot(dto);
        }
        TEntityBase tEntity = map(dto);
        tEntity.setLeaf(true);
        TEntityBase sibing = repository.findById(key).orElse(null);
        if (sibing==null)
            throw new RuntimeException("传入的ID所对应的节点不存在");
        tEntity = referSiblingOrParentAttributes(tEntity,sibing);
        tEntity.setParentId(sibing.getParentId());
        tEntity.setTlevel(sibing.getTlevel());
        TEntityBase save = repository.saveAndFlush(tEntity);
        dto.setId(save.getId());
        return dto;
    }

    /**获取全部，以树形结构返回*/
    public List<TEntityDto> findAllWithTree(){
        List<TEntityBase> tEntityBaseList = repository.findAll();
        List<TEntityBase> tree = this.buildTree(tEntityBaseList);
        return tree.stream().map(entity -> map(entity)).collect(Collectors.toList());
    }

    @Override
    public void delete(TEntityDto dto) {
        this.delete(dto.getId());
    }

    /**
     * 删除，把子节点一并删除
     * @param id 当前节点ID
     */
    @Transactional
    public void delete(TKey id){
        //删除所有子节点
        repository.deleteByParentId(id);
        TEntityBase entity = repository.getOne(id);
        //如果父节点只有自己一个子节点时，需修改其isLeaf=true
        TEntityBase parent = this.findParentOrNull(id);
        if (parent!=null) {
            List<TEntityBase> bases = repository.findAllByParentId(parent.getId());
            if (bases.size()==1){
                parent.setLeaf(true);
                repository.save(parent);
            }
        }
        //删除自己
        repository.deleteById(id);
    }

    @Override
    public TEntityDto update(TEntityDto dto) {
        TEntityBase entity = repository.findById(dto.getId()).orElse(null);
        if (entity==null){
            System.out.println("不存在");
        }else {
            entity = map(dto);
            repository.save(entity);
        }
        return dto;
    }

    /**获取父节点,没有返回null*/
    public TEntityDto findParentDtoOrNull(TKey id){
        TEntityBase base = this.findParentOrNull(id);
        if (base==null)
            return null;
        return map(base);
    }

    /**获取当前节点和其所有子代节点，以树结构返回*/
    public TEntityDto findByIdAndOffSpringWithTree(TKey id){
        TEntityBase current = repository.findById(id).orElse(null);
        if (current==null)
            return null;
        List<TEntityBase> currentAndOffSpring = this.findCurrentAndOffSpring(current);
        List<TEntityBase> tEntityBaseList = this.buildTree(currentAndOffSpring);
        return map(tEntityBaseList.get(0));
    }
    /**获取当前节点和其所有子代节点，以列表结构返回*/
    public List<TEntityDto> findByIdAndOffSpring(TKey id){
        TEntityBase current = repository.findById(id).orElse(null);
        if (current==null)
            return null;
        List<TEntityBase> currentAndOffSpring = this.findCurrentAndOffSpring(current);
        return currentAndOffSpring.stream().map(node -> map(node)).collect(Collectors.toList());
    }
    private List<TEntityBase> findCurrentAndOffSpring(TEntityBase tEntityBase){
        List<TEntityBase> tEntityBaseList = new ArrayList<>();
        tEntityBaseList.add(tEntityBase);
        List<TEntityBase> children = this.findAllChildren(tEntityBase.getId());
        for (TEntityBase t:children){
            tEntityBaseList.addAll(findCurrentAndOffSpring(t));
        }
        return tEntityBaseList;
    }

    /**获取当前节点和其子节点，以树结构返回*/
    public TEntityDto findByIdAndChildrenWithTree(TKey id){
        TEntityBase current = repository.findById(id).orElse(null);
        if (current==null)
            return null;
        List<TEntityBase> currentAndChildren = this.findCurrentAndChildren(current);
        List<TEntityBase> tEntityBaseList = this.buildTree(currentAndChildren);
        return map(tEntityBaseList.get(0));
    }
    /**获取当前节点和其子节点，以列表结构返回*/
    public List<TEntityDto> findByIdAndChildren(TKey id){
        TEntityBase current = repository.findById(id).orElse(null);
        if (current==null)
            return null;
        List<TEntityBase> currentAndChildren = this.findCurrentAndChildren(current);
        return currentAndChildren.stream().map(node -> map(node)).collect(Collectors.toList());
    }
    private List<TEntityBase> findCurrentAndChildren(TEntityBase tEntityBase){
        List<TEntityBase> tEntityBaseList = new ArrayList<>();
        tEntityBaseList.add(tEntityBase);
        List<TEntityBase> children = this.findAllChildren(tEntityBase.getId());
        tEntityBaseList.addAll(children);
        return tEntityBaseList;
    }

    /**获取库下的所有的节点，以列表形式返回*/
    public List<TEntityDto> findByStoreId(TKey id){
        List<TEntityBase> tEntityBaseList = repository.findAllByStoreId(id);
        return tEntityBaseList.stream().map(node -> map(node)).collect(Collectors.toList());
    }
    /**获取库下的所有的节点，以树形式返回*/
    public List<TEntityDto> findByStoreIdWithTree(TKey id){
        List<TEntityBase> tEntityBaseList = repository.findAllByStoreId(id);
        List<TEntityBase> tree = buildTree(tEntityBaseList);
        return tree.stream().map(node -> map(node)).collect(Collectors.toList());
    }

    /**个性化获取*/
    public List<TEntityDto > getTree(Map<String,Object> params){
        List<TEntityBase> entityBases = getEntityList(params);
        List<TEntityBase> tree = buildTree(entityBases);
        return tree.stream().map(entity -> map(entity)).collect(Collectors.toList());
    }

    private List<TEntityDto> getTree(List<TEntityBase> tEntityBaseList){
        List<TEntityBase> tree = this.buildTree(tEntityBaseList);
        return tree.stream().map(node -> map(node)).collect(Collectors.toList());
    }

    private List<TEntityBase> buildTree(List<TEntityBase> entityBases){
        List<TEntityBase> trees = new ArrayList<>(); //返回树结果（树结构）
        for (TEntityBase entity:entityBases){
            entity.setChildren(new ArrayList<>());
            if (entity.getParentId()==null||"0".equals(entity.getParentId())){ //根节点
                trees.add(entity);
            }
            for (TEntityBase e : entityBases){
                if (e.getParentId()!=null&&e.getParentId().equals(entity.getId())){
                    if (entity.getChildren()==null){
                        entity.setChildren(new ArrayList<TEntityBase>());
                    }
                    entity.getChildren().add(e);
                }
            }
        }
        return  trees;
    }

    private TEntityBase findParentOrNull(TKey id){
        TEntityBase tEntityBase = repository.findById(id).orElse(null);
        if (tEntityBase==null)
            return null;
        if (tEntityBase.getParentId()==null)
            return null;
        return repository.findById(tEntityBase.getParentId()).orElse(null);
    }

    private List<TEntityBase> findAllChildren(TKey parentId){
        return repository.findAllByParentId(parentId);
    }

    public List<TEntityDto> findChildren(TKey key){
        List<TEntityBase> tEntityBaseList = repository.findAllByParentId(key);
        return tEntityBaseList.stream().map(entity->map(entity)).collect(Collectors.toList());
    }


    protected TEntityBase map(TEntityDto dto){
        if (dto == null) return null;
        TEntityBase tEntityBase = ModelMapperUtils.map(dto, getEntityType());
        if (tEntityBase.getSortOrder()==null) {   //没有设置排序号
            tEntityBase.setSortOrder(Integer.MAX_VALUE);    //设置默认排序号
        }
        return tEntityBase;
    }

    protected TEntityDto map(TEntityBase entity){
        if(entity == null) return null;
        return ModelMapperUtils.map(entity,getDtoType());
    }

    protected abstract List<TEntityBase> getEntityList(Map<String,Object> params );

    /**
     * 从同级或上级参考一些属性
     * @param tEntityBase 当前要保存的实体
     * @param siblingOrParent 一般是父节点 OR 同级节点
     * @return  参考后的属性
     */
    public TEntityBase referSiblingOrParentAttributes(TEntityBase tEntityBase, TEntityBase siblingOrParent){
        tEntityBase.setStoreId(siblingOrParent.getStoreId());   //将父节点或同级节点的storeI属性 参考下来
        return tEntityBase;
    }
}
