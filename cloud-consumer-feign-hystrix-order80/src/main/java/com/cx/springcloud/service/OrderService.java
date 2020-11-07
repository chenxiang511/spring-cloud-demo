package com.cx.springcloud.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @Author cx
 * @Date 2020/10/27 8:27
 * @Version 1.0
 */

@Component
@FeignClient(value = "cloud-provider-hystrix-payment")
//@FeignClient(value = "cloud-provider-hystrix-payment",fallback = OrderFallbackService.class)//接口实现类的方式实现熔断
public interface OrderService {

    @GetMapping("payment/hystrix/ok/{id}")
    public String paymentInfoOk(@PathVariable("id") Integer id);

    @GetMapping("payment/hystrix/timeout/{id}")
    public String paymentInfoTimeout(@PathVariable("id") Integer id);
}
