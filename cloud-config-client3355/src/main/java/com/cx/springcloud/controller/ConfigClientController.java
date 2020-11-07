package com.cx.springcloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author cx
 * @Date 2020/11/5 13:24
 * @Version 1.0
 */

@RestController
@Slf4j
@RefreshScope
public class ConfigClientController {

    @Value("${server.port}")
    private String port;

    @Value("${config.info}")
    private String configInfo;

    @GetMapping("/configInfo")
    public String getConfigInfo(){

        return configInfo + "\tport" + port;
    }

}
