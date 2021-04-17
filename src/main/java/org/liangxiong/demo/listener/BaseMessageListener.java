package org.liangxiong.demo.listener;

import com.aliyun.openservices.ons.api.Action;
import com.aliyun.openservices.ons.api.ConsumeContext;
import com.aliyun.openservices.ons.api.Message;
import com.aliyun.openservices.ons.api.MessageListener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;

/**
 * @author liangxiong
 * @version 1.0.0
 * @date 2021-04-17 14:09
 * @description 消息消费监听器
 **/
@Component
@Slf4j
public class BaseMessageListener implements MessageListener {

    @Override
    public Action consume(Message message, ConsumeContext context) {
        if (log.isInfoEnabled()) {
            log.info("receive message: {}", new String(message.getBody(), StandardCharsets.UTF_8));
        }
        return Action.CommitMessage;
    }
}
