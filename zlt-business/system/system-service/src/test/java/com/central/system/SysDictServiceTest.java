package com.central.system;

import com.central.system.dictionary.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

/**
 * Unit test for simple App.
 */
@SpringBootTest
@Slf4j
public class SysDictServiceTest
{
    @Autowired
    private SysDictService service;

    @Autowired
    private SysDictItemService itemService;

    @Autowired
    private SysDictRepository dictRepository;

    @Autowired
    private SysDictItemRepository itemRepository;

    @Test
    public void sysDictTest(){

        String s = service.get();

        List<SysDict> all = dictRepository.findAll();
        Optional<SysDict> byId = dictRepository.findById("1683787726777745408");
//        byId.get().getItems().forEach(s->{
//            log.info(s.getCode());
//        });


        SysDictDto byId1 = service.findById("1683787726777745408");
        Optional<SysDict> byIdTest = service.findByIdTest("1683787726777745408");
        List<SysDictItem> byDictId = itemRepository.findByDict_Id("1683787726777745408");
//        List<SysDictItem> byDictId1 = itemRepository.findByDictId("1683787726777745408");
//        List<SysDictItem> byDictId2 = itemRepository.findByDictId("1683787726777745408");
//        List<SysDictItem> byDictId3 = itemRepository.findByDictId("1683787726777745408");
//        List<SysDictItem> byDictId4 = itemRepository.findByDictId("1683787726777745408");
//        List<SysDictItem> byDictId5 = itemRepository.findByDictId("1683787726777745408");
//        List<SysDictItem> byDictId6 = itemRepository.findByDictId("1683787726777745408");
//        List<SysDictItem> byDictId7 = itemRepository.findByDictId("1683787726777745408");
//        List<SysDictItem> byDictId8 = itemRepository.findByDictId("1683787726777745408");
//        List<SysDictItem> byDictId9 = itemRepository.findByDictId("1683787726777745408");
//        List<SysDictItem> byDictId10 = itemRepository.findByDictId("1683787726777745408");
//        byId1.getItems();
//        SysDictDto sysDictDto = new SysDictDto();
//        sysDictDto.setCode("0001");
//        sysDictDto.setName("dict1");
//        sysDictDto.setParentCode("");
//
//        SysDictDto result = service.create(sysDictDto);
//
//        SysDict sysDict = new SysDict();
//        sysDict.setCode("0001");
//        sysDict.setName("dict1");
//        sysDict.setParentCode("");
//
//
//        List<SysDictItemDto> sysDictItemDtos = new ArrayList<>();
//        sysDictItemDtos.add(new SysDictItemDto(result.getId(),"01","v1","",0,"","",true));
//        sysDictItemDtos.add(new SysDictItemDto(result.getId(),"02","v2","",1,"","",true));
//        sysDictItemDtos.add(new SysDictItemDto(result.getId(),"03","v3","",2,"","",true));
//
//        List<SysDictItemDto> sysDictItemDtos1 = itemService.create(sysDictItemDtos);
//
//        SysDictDto byId = service.findById(result.getId());

    }
}
