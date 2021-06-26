package cn.zhiyucs.springcloud.service;

import org.springframework.stereotype.Component;

@Component
public class PaymentFallbackService implements PaymentHystrixService{
    // 服务器宕机或者超时后，会报错，进行以下处理
    // 异常处理优先级：服务器 > 客户端，这里的处理是客户端处理
    @Override
    public String paymentInfo_OK(Integer id) {
        return "----------PaymentFallbackService fallback -- paymentInfo_OK, /(ㄒoㄒ)/~~";
    }

    @Override
    public String paymentInfo_Timeout(Integer id) {
        return "----------PaymentFallbackService fallback -- paymentInfo_Timeout, /(ㄒoㄒ)/~~";
    }
}
