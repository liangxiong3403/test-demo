package org.liangxiong.demo.consumer;

import com.aliyun.openservices.ons.api.MessageListener;
import com.aliyun.openservices.ons.api.PropertyKeyConst;
import com.aliyun.openservices.ons.api.bean.ConsumerBean;
import com.aliyun.openservices.ons.api.bean.Subscription;
import org.liangxiong.demo.config.RocketMQConfig;
import org.liangxiong.demo.listener.BaseMessageListener;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @author liangxiong
 * @version 1.0.0
 * @date 2021-04-17 14:11
 * @description 消息消费组
 **/
@Component
public class UserMessageConsumer {

    @Resource
    private RocketMQConfig rocketMQConfig;

    @Resource
    private BaseMessageListener messageListener;

    @Bean(initMethod = "start", destroyMethod = "shutdown")
    public ConsumerBean buildConsumer() {
        ConsumerBean consumerBean = new ConsumerBean();
        // 配置文件
        Properties properties = rocketMQConfig.getRocketMQPropertie();
        properties.setProperty(PropertyKeyConst.GROUP_ID, rocketMQConfig.getGroupId());
        // 将消费者线程数固定为20个 20为默认值
        properties.setProperty(PropertyKeyConst.ConsumeThreadNums, "20");
        consumerBean.setProperties(properties);
        // 订阅关系
        Map<Subscription, MessageListener> subscriptionTable = new HashMap<>();
        Subscription subscription = new Subscription();
        subscription.setTopic(rocketMQConfig.getTopic());
        subscription.setExpression(rocketMQConfig.getTag());
        subscriptionTable.put(subscription, messageListener);
        // 订阅多个topic如上面设置
        consumerBean.setSubscriptionTable(subscriptionTable);
        return consumerBean;
    }
}
