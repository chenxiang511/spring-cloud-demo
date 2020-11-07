package com.cx.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @Author cx
 * @Date 2020/7/21 15:53
 * @Version 1.0
 */

@SpringBootApplication
@EnableEurekaServer
public class Eureka7002 {

    public static void main(String[] args) {
        SpringApplication.run(Eureka7002.class,args);
    }
}
