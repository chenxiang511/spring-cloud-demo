package com.cx.springcloud.service.impl;

import com.cx.springcloud.dao.PaymentDao;
import com.cx.springcloud.service.PaymentService;
import com.cx.springcloud.entities.Payment;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Author cx
 * @Date: 2020/6/19  8:04
 */

@Service
public class PaymentServiceImpl implements PaymentService {

    @Resource
    private PaymentDao paymentDao;

    @Override
    public int create(Payment payment) {

        return paymentDao.create(payment);
    }

    @Override
    public Payment getPaymentById(Long id) {

        return paymentDao.getPaymentById(id);
    }
}
