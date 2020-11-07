package com.cx.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @Author cx
 * @Date 2020/10/15 11:58
 * @Version 1.0
 */

@SpringBootApplication
@EnableDiscoveryClient//该注解用于使用consul或者zookeeper作为注册中心时注册服务
public class OrderConsulMain80 {

    public static void main(String[] args) {

        SpringApplication.run(OrderConsulMain80.class,args);
    }
}
