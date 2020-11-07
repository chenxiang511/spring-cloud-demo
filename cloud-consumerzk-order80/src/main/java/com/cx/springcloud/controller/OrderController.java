package com.cx.springcloud.controller;

import com.cx.springcloud.entities.CommonResult;
import com.cx.springcloud.entities.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * @Author cx
 * @Date 2020/9/10 15:28
 * @Version 1.0
 */

@RestController
@Slf4j
@RequestMapping("consumer")
public class OrderController {

    public static final String INVOKE_URL= "http://cloud-provider-payment";

    @Resource
    private RestTemplate restTemplate;
    @GetMapping("payment/zk")
    public CommonResult<Payment> create(@RequestBody Payment payment){

        return restTemplate.postForObject(INVOKE_URL+"/payment/zk",payment, CommonResult.class);    
    }

}
