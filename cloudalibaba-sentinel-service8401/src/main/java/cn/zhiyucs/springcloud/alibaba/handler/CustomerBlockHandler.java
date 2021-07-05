package cn.zhiyucs.springcloud.alibaba.handler;

import cn.zhiyucs.springcloud.entities.CommonResult;
import com.alibaba.csp.sentinel.slots.block.BlockException;

public class CustomerBlockHandler {

    public static CommonResult handlerException(BlockException e) {
        return new CommonResult(4444, "按客户自定义, global handlerException1");
    }

    public static CommonResult handlerException2(BlockException e) {
        return new CommonResult(4444, "按客户自定义, global handlerException2");
    }
}
