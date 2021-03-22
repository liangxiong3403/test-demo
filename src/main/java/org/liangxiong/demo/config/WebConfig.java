package org.liangxiong.demo.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * @author liangxiong
 * @version 1.0.0
 * @date 2021-03-22 19:11
 * @description
 **/
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.stream().filter(c -> c instanceof MappingJackson2HttpMessageConverter).forEach(cvt -> {
            MappingJackson2HttpMessageConverter converter = (MappingJackson2HttpMessageConverter) cvt;
            ObjectMapper objectMapper = converter.getObjectMapper();
            SimpleModule simpleModule = new SimpleModule();
            // handle type of Long error for table primary key
            simpleModule.addSerializer(Long.class, ToStringSerializer.instance);
            objectMapper.registerModule(simpleModule);
        });
    }
}
