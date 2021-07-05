package cn.zhiyucs.springcloud.alibaba.service;

import cn.zhiyucs.springcloud.entities.CommonResult;
import cn.zhiyucs.springcloud.entities.Payment;
import org.springframework.stereotype.Component;

@Component
public class PaymentFallbackService implements PaymentService{
    @Override
    public CommonResult<Payment> paymentSQL(Long id) {
        return new CommonResult<>(44444, "服务降级返回, --PaymentFallbackService", new Payment(id, "errorService"));
    }
}
