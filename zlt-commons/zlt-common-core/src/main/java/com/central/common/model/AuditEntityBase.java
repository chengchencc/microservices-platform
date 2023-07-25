//package com.central.common.model;
//
//import com.baomidou.mybatisplus.annotation.FieldFill;
//import com.baomidou.mybatisplus.annotation.IdType;
//import com.baomidou.mybatisplus.annotation.TableField;
//import com.baomidou.mybatisplus.annotation.TableId;
//import com.baomidou.mybatisplus.extension.activerecord.Model;
//import lombok.Getter;
//import lombok.Setter;
//import lombok.experimental.Accessors;
//
//import java.util.Date;
//
//@Getter
//@Setter
//@Accessors(chain = true)
//public class AuditEntityBase<T extends Model<?>> extends Model<T> {
//    /**
//     * 主键ID
//     */
//    @TableId(type = IdType.ASSIGN_ID)
//    private Long id;
//
//    @TableField(fill = FieldFill.INSERT)
//    private Date createTime;
//    @TableField(fill = FieldFill.INSERT_UPDATE)
//    private Date updateTime;
//
//    @TableField(fill = FieldFill.INSERT)
//    private String createByUserId;
//
//    @TableField(fill = FieldFill.INSERT_UPDATE)
//    private String updateByUserId;
//
//    @TableField(fill = FieldFill.INSERT)
//    private String createByUserName;
//
//    @TableField(fill = FieldFill.INSERT_UPDATE)
//    private String updateByUserName;
//
//}
