package org.liangxiong.demo.controller;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.EnumUtil;
import lombok.extern.slf4j.Slf4j;
import org.liangxiong.demo.enums.BaseEnum;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

/**
 * @author liangxiong
 * @version 1.0.0
 * @date 2021-09-29 07:50
 * @description 根据枚举类全限定类名获取k-v键值对
 **/
@Slf4j
@RestController
@RequestMapping("/enumCodeAndValue")
public class EnumController {

    @GetMapping("/className")
    public HashMap<String, List<HashMap<String, String>>> getByClassName(@RequestParam("className") String className) {
        Class<BaseEnum> classObject = null;
        try {
            classObject = (Class<BaseEnum>) Class.forName(className);
        } catch (ClassNotFoundException e) {
            log.error("className is error: {}", className);
        }
        Objects.requireNonNull(classObject, "classObject can not be null!");
        HashMap<String, List<HashMap<String, String>>> linkedHashMap = MapUtil.newHashMap(16, true);
        List<String> fieldNames = EnumUtil.getNames(classObject);
        List<HashMap<String, String>> listMap = new ArrayList<>(10);
        if (CollUtil.isNotEmpty(fieldNames)) {
            Class<BaseEnum> finalClassObject = classObject;
            fieldNames.forEach(fieldName -> {
                HashMap<String, String> nameMapValue = MapUtil.newHashMap(4);
                nameMapValue.put(EnumUtil.fromString(finalClassObject, fieldName).getCode(), EnumUtil.fromString(finalClassObject, fieldName).getDesc());
                listMap.add(nameMapValue);
            });
            linkedHashMap.put(classObject.getName(), listMap);
        }
        return linkedHashMap;
    }
}
