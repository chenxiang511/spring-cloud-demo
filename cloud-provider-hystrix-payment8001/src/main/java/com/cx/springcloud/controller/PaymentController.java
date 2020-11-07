package com.cx.springcloud.controller;

import com.cx.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Author cx
 * @Date 2020/10/26 15:51
 * @Version 1.0
 */

@RestController
@Slf4j
@RequestMapping("payment")
public class PaymentController {

    @Resource
    private PaymentService paymentService;

    @GetMapping("hystrix/ok/{id}")
    public String paymentInfoOk(@PathVariable("id") Integer id){

        String result = paymentService.paymentInfoOk(id);
        log.info("result==========="+result);
        return result;
    }

    @GetMapping("hystrix/timeout/{id}")
    public String paymentInfoTimeout(@PathVariable("id") Integer id){

        String result = paymentService.paymentInfoTimeout(id);
        log.info("result==========="+result);
        return result;
    }

    //服务熔断
    @GetMapping("hystrix/circuitBreaker/{id}")
    public String paymentCircuitBreaker(@PathVariable("id")Integer id){

        String result = paymentService.paymentCircuitBreaker(id);
        log.info("result============" + result);
        return result;
    }
}
