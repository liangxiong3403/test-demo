package org.liangxiong.demo.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author liangxiong
 * @version 1.0.0
 * @date 2021-04-17 14:02
 * @description
 **/
@RestController
public class MessageController {

    @Value("${rocketmq.topic}")
    private String topic;

    @Value("${rocketmq.groupId}")
    private String groupId;
}
