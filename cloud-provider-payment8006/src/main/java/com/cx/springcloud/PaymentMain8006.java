package com.cx.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @Author cx
 * @DATE 2020/10/15
 */

@SpringBootApplication
@EnableDiscoveryClient//该注解用于使用consul或者zookeeper作为注册中心时注册服务
public class PaymentMain8006 {

    public static void main(String[] args) {

        SpringApplication.run(PaymentMain8006.class,args);
    }
}
