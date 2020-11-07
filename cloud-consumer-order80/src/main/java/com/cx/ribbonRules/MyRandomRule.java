package com.cx.ribbonRules;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 返回IRule下随机负载均衡算法
 */

@Configuration
public class MyRandomRule {

    @Bean
    public IRule myRule(){

        return new RandomRule();
    }
}
