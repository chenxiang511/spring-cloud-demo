package com.cx.springcloud.controller;

import com.cx.springcloud.entities.CommonResult;
import com.cx.springcloud.entities.Payment;
import com.cx.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @Author cx
 * @Date 2020/6/19  8:08
 */

@RestController
@Slf4j
public class PaymentController {

    @Resource
    private PaymentService paymentService;

    @Value("${server.port}")
    private  String serverPort;

    @PostMapping("/payment/create")
    public CommonResult<Integer> create(@RequestBody Payment payment) {

        try{
            int result=paymentService.create(payment);
            if(result>0){
                return new CommonResult<>(200,"数据插入成功"+serverPort, result);
            }else {
                return new CommonResult<>(444,"数据插入失败",0);
            }
        }catch (Exception e){
            e.printStackTrace();
            return  new CommonResult<>(500,"系统内部异常，请联系管理员",0);
        }
    }

    @GetMapping("/payment/getPaymentById/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id) {

        try {
            Payment payment=paymentService.getPaymentById(id);
            return new CommonResult<>(200,"数据查询成功"+serverPort,payment);
        }catch (Exception e){
        e.printStackTrace();
        return  new CommonResult<>(500,"系统内部异常，请联系管理员",null);
        }
    }

    @GetMapping("/payment/lb")
    public String getPaymentLB(){

        return serverPort;
    }

    @GetMapping("payment/feign/timeout")
    public String paymentFeignTimeout(){

        try{
            //设置线程睡眠3秒，测试Feign超时机制
            TimeUnit.SECONDS.sleep(3);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        return serverPort;
    }
}
