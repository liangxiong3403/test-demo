package org.liangxiong.demo;

import net.oschina.j2cache.CacheChannel;
import net.oschina.j2cache.J2Cache;

/**
 * @author liangxiong
 * @version 1.0.0
 * @date 2021-03-27 22:28
 * @description
 **/
public class CacheTest {

    public static void main(String[] args) {
        CacheChannel cache = J2Cache.getChannel();
        // 缓存操作
        String key = "name";
        cache.set("default", key, "Hello J2Cache");
        System.out.println(cache.get("default", key));
        cache.evict("default", key);
        System.out.println(cache.get("default", key));
        cache.close();
    }
}
