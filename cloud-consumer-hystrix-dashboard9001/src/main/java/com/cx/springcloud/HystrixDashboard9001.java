package com.cx.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

/**
 * @Author cx
 * @Date 2020/10/31 10:26
 * @Version 1.0
 */

@SpringBootApplication
@EnableHystrixDashboard
public class HystrixDashboard9001 {

    public static void main(String[] args) {

        SpringApplication.run(HystrixDashboard9001.class,args);
    }
}
