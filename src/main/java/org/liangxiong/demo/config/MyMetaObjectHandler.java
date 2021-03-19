package org.liangxiong.demo.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.fasterxml.jackson.databind.SerializationFeature;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * @author liangxiong
 * @version 1.0.0
 * @date 2021-03-19 22:33
 * @description
 **/
@Slf4j
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        log.info("start insert fill ....");
        this.strictInsertFill(metaObject, "createdTime", LocalDateTime::now, LocalDateTime.class);
        this.strictInsertFill(metaObject, "updatedTime", LocalDateTime::now, LocalDateTime.class);
        this.strictInsertFill(metaObject, "deleted", () -> 0, Integer.class);
        this.strictInsertFill(metaObject, "createdBy", () -> "Administrator", String.class);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        log.info("start update fill ....");
        this.strictUpdateFill(metaObject, "updatedTime", LocalDateTime::now, LocalDateTime.class);
        this.strictUpdateFill(metaObject, "updatedBy", () -> "Administrator", String.class);
    }

    /**
     * 枚举值写入数据库;前端传递中文,数据库写入整型.比如:男->1;女->0;未知->-1
     *
     * @return
     */
    @Bean
    public Jackson2ObjectMapperBuilderCustomizer customizer() {
        return builder -> builder.featuresToEnable(SerializationFeature.WRITE_ENUMS_USING_TO_STRING);
    }
}
