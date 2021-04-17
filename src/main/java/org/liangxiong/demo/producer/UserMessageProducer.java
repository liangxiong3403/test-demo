package org.liangxiong.demo.producer;

import com.aliyun.openservices.ons.api.bean.ProducerBean;
import org.liangxiong.demo.config.RocketMQConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author liangxiong
 * @version 1.0.0
 * @date 2021-04-17 14:16
 * @description 消息生产者
 **/
@Component
public class UserMessageProducer {

    @Resource
    private RocketMQConfig rocketMQConfig;

    @Bean(initMethod = "start", destroyMethod = "shutdown")
    public ProducerBean buildProducer() {
        ProducerBean producer = new ProducerBean();
        producer.setProperties(rocketMQConfig.getRocketMQPropertie());
        return producer;
    }
}
