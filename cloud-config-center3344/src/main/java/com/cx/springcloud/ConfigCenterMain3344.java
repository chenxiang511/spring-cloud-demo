package com.cx.springcloud;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * @Author cx
 * @Date 2020/11/5 10:58
 * @Version 1.0
 */

@SpringBootApplication
@EnableConfigServer
@EnableRabbit
public class ConfigCenterMain3344 {

    public static void main(String[] args) {

        SpringApplication.run(ConfigCenterMain3344.class,args);
    }
}
