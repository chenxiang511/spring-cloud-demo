package com.cx.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @Author cx
 * @Date 2020/11/5 13:17
 * @Version 1.0
 */

@SpringBootApplication
@EnableEurekaClient
public class ConfigClientMain3355 {

    public static void main(String[] args) {

        SpringApplication.run(ConfigClientMain3355.class,args);
    }
}
