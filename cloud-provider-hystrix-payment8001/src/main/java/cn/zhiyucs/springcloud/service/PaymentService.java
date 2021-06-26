package cn.zhiyucs.springcloud.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class PaymentService {

    /**
     * 正常返回
     *
     * @param id
     * @return
     */
    public String paymentInfo_OK(Integer id) {
        return "线程池：" + Thread.currentThread().getName() + "\tpaymentInfo_OK: " + id + "\t" + "哈哈哈~";
    }

    @HystrixCommand(fallbackMethod = "paymentInfo_TimeoutHandler",
            commandProperties = {
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "5000")
            }
    )
    public String paymentInfo_Timeout(Integer id) {
        int timeNumber = 5;
//        int a = 10 / 0;
        try {
            TimeUnit.SECONDS.sleep(timeNumber);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "线程池：" + Thread.currentThread().getName() + "\tpaymentInfo_Timeout: " + id + "\t" + "耗时(s): " ;
    }

    public String paymentInfo_TimeoutHandler(Integer id) {
        return "线程池：" + Thread.currentThread().getName() + "\t 8001系统繁忙或者运行报错，请稍后再试: " + id + "\t" + "/(ㄒoㄒ)/~~";
    }
}
