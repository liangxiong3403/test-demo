package org.liangxiong.demo.controller;

import net.oschina.j2cache.CacheChannel;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author liangxiong
 * @version 1.0.0
 * @date 2021-03-28 18:04
 * @description
 **/
@RestController
@RequestMapping("/cache")
public class CacheController {

    @Resource
    private CacheChannel cacheChannel;

    @GetMapping("/getByKey")
    public Object getByKey(String key) {
        return cacheChannel.get("default", key).getValue();
    }

    @PutMapping("/addByKey")
    public void addByKey(String key) {
        cacheChannel.set("default", key, "李白");
    }

    @DeleteMapping("/deleteByKey")
    public void deleteByKey(String key) {
        cacheChannel.evict("default", key);
    }
}
