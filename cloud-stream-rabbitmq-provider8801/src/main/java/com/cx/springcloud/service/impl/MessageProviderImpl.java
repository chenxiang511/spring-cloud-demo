package com.cx.springcloud.service.impl;

import cn.hutool.core.util.IdUtil;
import com.cx.springcloud.service.IMessageProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;

import javax.annotation.Resource;


/**
 * @Author cx
 * @Date 2020/11/9 16:46
 * @Version 1.0
 * 消息推送服务
 */

@EnableBinding(Source.class)//定义消息的推送通道
@Slf4j
public class MessageProviderImpl implements IMessageProvider {

    @Resource
    private MessageChannel output; //消息发送通道

    @Override
    public String send() {
        String serial = IdUtil.simpleUUID();
        output.send(MessageBuilder.withPayload(serial).build());
        log.info("******serial="+serial);
        return null;
    }
}
