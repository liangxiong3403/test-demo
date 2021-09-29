package org.liangxiong.demo.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author liangxiong
 * @version 1.0.0
 * @date 2021-09-29 08:01
 * @description 所有枚举类的基类
 **/
@Getter
@AllArgsConstructor
public enum BaseEnum {

    ;

    private String code;

    private String desc;
}
