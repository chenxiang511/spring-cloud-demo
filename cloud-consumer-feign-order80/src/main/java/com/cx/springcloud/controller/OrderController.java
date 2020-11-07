package com.cx.springcloud.controller;

import com.cx.springcloud.entities.CommonResult;
import com.cx.springcloud.entities.Payment;
import com.cx.springcloud.service.PaymentFeignService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Author cx
 * @Date 2020/10/23 9:26
 * @Version 1.0
 */

@RestController
@Slf4j
@RequestMapping("consumer")
public class OrderController {

    @Resource
    private PaymentFeignService paymentFeignService;

    @GetMapping("/payment/getPaymentById/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id){

        return paymentFeignService.getPaymentById(id);
    }

    @GetMapping("/payment/feign/timeout")
    public String paymentFeignTimeout(){

       return paymentFeignService.paymentFeignTimeout();
    }
}
