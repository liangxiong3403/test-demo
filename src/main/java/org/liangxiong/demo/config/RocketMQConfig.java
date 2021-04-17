package org.liangxiong.demo.config;

import com.aliyun.openservices.ons.api.PropertyKeyConst;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

/**
 * @author liangxiong
 * @version 1.0.0
 * @date 2021-04-17 14:05
 * @description
 **/
@Configuration
@ConfigurationProperties(prefix = "rocketmq")
@Getter
@Setter
public class RocketMQConfig {

    private String accessKeyId;

    private String accessKeySecret;

    private String nameSrvAddr;

    private String topic;

    private String groupId;

    private String tag;

    public Properties getRocketMQPropertie() {
        Properties properties = new Properties();
        properties.setProperty(PropertyKeyConst.AccessKey, this.accessKeyId);
        properties.setProperty(PropertyKeyConst.SecretKey, this.accessKeySecret);
        properties.setProperty(PropertyKeyConst.NAMESRV_ADDR, this.nameSrvAddr);
        return properties;
    }

}
