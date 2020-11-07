package com.cx.springcloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * @Author cx
 * @Date 2020/10/15 13:11
 * @Version 1.0
 */

@RestController
@Slf4j
@RequestMapping("consumer")
public class OrderController {

    public static final String INVOKE_URL= "http://consul-provider-payment";

    @Resource
    private RestTemplate restTemplate;
    @GetMapping("payment/consul")
    public String paymentConsul(){

        return restTemplate.getForObject(INVOKE_URL+"/payment/consul",String.class);
    }

}
