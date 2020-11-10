package com.cx.springcloud.controller;


import com.cx.springcloud.entities.CommonResult;
import com.cx.springcloud.entities.Payment;
import com.cx.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @Author cx
 * @Date 2020/6/19  8:08
 */

@RestController
@Slf4j
@RequestMapping("/payment")
public class PaymentController {

    @Resource
    private PaymentService paymentService;

    @Resource
    private DiscoveryClient discoveryClient;

    @Value("${server.port}")
    private  String serverPort;

    @PostMapping("/create")
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

    @GetMapping("/getPaymentById/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id) {

        try {
            Payment payment=paymentService.getPaymentById(id);
            return new CommonResult<>(200,"数据查询成功"+serverPort,payment);
        }catch (Exception e){
        e.printStackTrace();
        return  new CommonResult<>(500,"系统内部异常，请联系管理员",null);
        }
    }

    @GetMapping("/getDiscovery")
    public Object getDiscovery(){

        List<String> services = discoveryClient.getServices();
        for(String service : services){
            log.info("*********服务名："+service);
        }
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        for(ServiceInstance instance : instances){
            log.info(instance.getServiceId()+"\t"+instance.getHost()+"\t"+instance.getPort()+"\t"+instance.getUri());
        }
        return this.discoveryClient;
    }

    @GetMapping("/lb")
    public String getPaymentLB(){

        return serverPort;
    }

    @GetMapping("/feign/timeout")
    public String paymentFeignTimeout(){

        try{
            //设置线程睡眠3秒，测试Feign超时机制
            TimeUnit.SECONDS.sleep(3);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        return serverPort;
    }

    @GetMapping("/zipkin")
    public String paymentZipKin(){
        return "测试链路追踪，服务提供端，端口号："+serverPort;
    }
}
