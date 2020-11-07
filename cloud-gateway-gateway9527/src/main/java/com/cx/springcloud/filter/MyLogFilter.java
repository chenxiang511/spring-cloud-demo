package com.cx.springcloud.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.time.ZonedDateTime;

/**
 * @Author cx
 * @Date 2020/11/4 13:59
 * @Version 1.0
 */

@Component
@Slf4j
public class MyLogFilter implements GlobalFilter, Ordered {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        log.info("************进入日志过滤器："+ ZonedDateTime.now());
        String userName = exchange.getRequest().getQueryParams().getFirst("userName");

        if(userName == null){
            log.info("********用户名为空，非法用户********");
            exchange.getResponse().setStatusCode(HttpStatus.NOT_ACCEPTABLE);
            return exchange.getResponse().setComplete();
        }

        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {

        //过滤器的排序，取值范围为: -2147483648 ~ 2147483647
        return -1;
    }
}
