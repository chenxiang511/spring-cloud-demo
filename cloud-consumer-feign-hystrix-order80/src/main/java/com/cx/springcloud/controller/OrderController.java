package com.cx.springcloud.controller;

import com.cx.springcloud.service.OrderService;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.UUID;

/**
 * @Author cx
 * @Date 2020/10/27 8:31
 * @Version 1.0
 */

@RestController
@Slf4j
@RequestMapping("consumer")
@DefaultProperties(defaultFallback = "paymentDefaultFallback")
public class OrderController {

    @Resource
    private OrderService orderService;

    @GetMapping("payment/hystrix/ok/{id}")
    @HystrixCommand
    public String paymentInfoOk(@PathVariable("id")Integer id){

        return orderService.paymentInfoOk(id);
    }

    @GetMapping("payment/hystrix/timeout/{id}")
    @HystrixCommand(fallbackMethod = "paymentInfoTimeoutHandler",commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "3000")})
    //@HystrixCommand //没有单独配置fallback方法时，使用默认的全局fallback方法
    public String paymentInfoTimeout(@PathVariable("id")Integer id){

        return orderService.paymentInfoTimeout(id);
    }

    public String paymentInfoTimeoutHandler(Integer id){

        return "线程池:" + Thread.currentThread().getName()+"——消费端80：paymentInfoTimeoutHandler系统繁忙或运行报错，请稍后再试,id:" + id;
    }

    //默认的全局fallback方法
    public String paymentDefaultFallback(){

        return "线程池:" + Thread.currentThread().getName()+"——消费端80：默认fallback系统繁忙或运行报错，请稍后再试!";
    }

}
