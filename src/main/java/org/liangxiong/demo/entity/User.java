package org.liangxiong.demo.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelIgnore;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.ContentRowHeight;
import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.liangxiong.demo.config.LocalDateTimeConverter;
import org.liangxiong.demo.enums.SexEnum;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author liangxiong
 * @version 1.0.0
 * @date 2020-09-06 13:57
 * @description
 **/
@Builder
@Getter
@Setter
@TableName("user")
@NoArgsConstructor
@AllArgsConstructor
@ExcelTarget("user")
@ContentRowHeight(50)
@ColumnWidth(20)
public class User {

    /**
     * 用户id
     */
    @TableId(type = IdType.AUTO)
    @Excel(name = "id",orderNum = "1",width = 10)
    @ExcelProperty(value = "id",index = 0)
    private Long id;

    /**
     * 用户名
     */
    @Excel(name = "用户名",orderNum = "2",width = 10)
    @ExcelProperty(value = "用户名",index = 1)
    private String name;

    /**
     * 年龄
     */
    @Excel(name = "年龄",orderNum = "3",width = 10,suffix = "岁")
    @ExcelProperty(value = "年龄",index = 2)
    private Integer age;

    /**
     * 邮箱
     */
    @Excel(name = "邮箱",orderNum = "4",width = 10)
    @ExcelProperty(value = "邮箱",index = 3)
    private String email;

    /**
     * 数据库最好使用int类型;虽然高版本mybatis-plus可以识别数据库tinyint类型的枚举
     */
    @Excel(name = "性别",orderNum = "5",width = 10)
    @com.alibaba.excel.annotation.ExcelIgnore
    private SexEnum gender;

    @JsonIgnore
    @TableLogic
    @TableField(fill = FieldFill.INSERT)
    @ExcelIgnore
    @com.alibaba.excel.annotation.ExcelIgnore
    private Integer deleted;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @TableField(fill = FieldFill.INSERT)
    @Excel(name = "创建时间",orderNum = "6",width = 10,format = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @ExcelProperty(value = "创建时间",index = 4,converter = LocalDateTimeConverter.class)
    private LocalDateTime createdTime;

    @TableField(fill = FieldFill.INSERT)
    @ExcelIgnore
    @com.alibaba.excel.annotation.ExcelIgnore
    private String createdBy;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    @Excel(name = "修改时间",orderNum = "7",width = 10,format = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @ExcelProperty(value = "修改时间",index = 5,converter = LocalDateTimeConverter.class)
    private LocalDateTime updatedTime;

    @TableField(fill = FieldFill.UPDATE)
    @ExcelIgnore
    @com.alibaba.excel.annotation.ExcelIgnore
    private String updatedBy;

    /**
     * 租户id
     */
    private Long tenantId;

    @ExcelIgnore
    @TableField(exist = false)
    @com.alibaba.excel.annotation.ExcelIgnore
    private List<Long> ids;
}
