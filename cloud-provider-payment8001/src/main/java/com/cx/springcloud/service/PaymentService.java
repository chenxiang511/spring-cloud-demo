package com.cx.springcloud.service;

import com.cx.springcloud.entities.Payment;

/**
 * @Author cx
 * @Date: 2020/6/19  8:04
 */
public interface PaymentService {

    public int create(Payment payment);

    public Payment getPaymentById(Long id);
}
