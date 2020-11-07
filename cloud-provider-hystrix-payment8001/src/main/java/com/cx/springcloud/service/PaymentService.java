package com.cx.springcloud.service;

import cn.hutool.core.util.IdUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @Author cx
 * @Date 2020/10/26 15:24
 * @Version 1.0
 */

@Service
public class PaymentService {

    public String paymentInfoOk(Integer id){

        return "线程池:" + Thread.currentThread().getName()+"paymentInfoOk,id:" + id;
    }

    @HystrixCommand(fallbackMethod = "paymentInfoTimeoutHandler",commandProperties = {
                @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "2500")
    })
    public String paymentInfoTimeout(Integer id){

        //模拟运行异常
        //int n = 10/0;
        int n = 2000;
        try{
            //设置线程睡眠n秒:
            // 1.测试Feign超时机制
            // 2.测试hystrix超时后执行fallback服务降级
            TimeUnit.MILLISECONDS.sleep(n);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        return "线程池:" + Thread.currentThread().getName()+"-paymentInfoTimeout,id:" + id+"\t耗时"+ n/1000 + "秒钟";
    }

    public String paymentInfoTimeoutHandler(Integer id){

        return "线程池:" + Thread.currentThread().getName()+"—paymentInfoTimeoutHandler:8001系统繁忙或运行报错，请稍后再试,id:" + id;
    }

    //服务熔断
    @HystrixCommand(fallbackMethod = "paymentCircuitBreakerHandler",commandProperties = {
            //此属性确定断路器是否将用于跟踪运行状况以及在跳闸时用于短路请求。默认为true
            @HystrixProperty(name = "circuitBreaker.enabled",value = "true"),
            //此属性在滚动窗口中设置将使电路跳闸的最小请求数,默认为20,如果该值为20，则如果在滚动窗口（例如10秒的窗口）中仅收到19个请求，则即使所有19个失败，电路也不会跳闸断开。
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value = "10"),
            //此属性设置电路跳闸后拒绝请求的时间，然后允许再次尝试确定是否应再次闭合电路。open->half-open,默认为5000
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds",value = "10000"),
            //此属性设置错误百分比，电路应在该百分比以上触发跳闸并启动对后备逻辑的短路请求。默认为50
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage",value = "60")
    })
    public String paymentCircuitBreaker(Integer id){

        if(id < 0){
            throw new RuntimeException("************id 不能为负数");
        }
        String serialNum = UUID.randomUUID().toString();
        return Thread.currentThread().getName() + "\t" + "调用成功" +id+ "，流水号：" + serialNum;
    }

    public String paymentCircuitBreakerHandler(Integer id){

        return "请求超时，或者程序异常，请稍后再试！！！id:" + id;
    }

}
