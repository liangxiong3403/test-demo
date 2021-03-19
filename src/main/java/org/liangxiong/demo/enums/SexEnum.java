package org.liangxiong.demo.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author liangxiong
 * @version 1.0.0
 * @date 2021-03-19 21:32
 * @description
 **/
@Getter
@AllArgsConstructor
public enum SexEnum {

    /**
     * 男性
     */
    MALE(1, "男"),
    /**
     * 女性
     */
    FEMALE(0, "女"),
    /**
     * 未知
     */
    UNKNOWN(-1, "未知");

    @EnumValue
    private Integer code;

    @JsonValue
    private String desc;

    @Override
    public String toString() {
        return this.desc;
    }
}
