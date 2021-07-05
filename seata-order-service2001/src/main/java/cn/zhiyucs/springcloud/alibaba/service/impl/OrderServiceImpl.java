package cn.zhiyucs.springcloud.alibaba.service.impl;

import cn.zhiyucs.springcloud.alibaba.dao.OrderDao;
import cn.zhiyucs.springcloud.alibaba.domain.Order;
import cn.zhiyucs.springcloud.alibaba.service.AccountService;
import cn.zhiyucs.springcloud.alibaba.service.OrderService;
import cn.zhiyucs.springcloud.alibaba.service.StorageService;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Resource
    private OrderDao orderDao;

    @Resource
    private AccountService accountService;

    @Resource
    private StorageService storageService;

    /**
     * 创建订单->调用库存服务扣减库存->调用账户服务扣减账户余额->修改订单状态
     */
    @Override
    @GlobalTransactional(name = "fsp-create-order", rollbackFor = Exception.class)
    public void create(Order order) {
        // 新建订单
        log.info("------->开始创建订单");
        orderDao.create(order);
        // 扣减库存
        log.info("------->订单微服务开始调用库存，做扣减");
        storageService.decrease(order.getProductId(), order.getCount());
        // 扣减账户
        log.info("------->订单微服务开始调用账户，做扣减");
        accountService.decrease(order.getUserId(), order.getMoney());

        // 修改订单状态，从0到1，1代表完成
        log.info("------->修改订单状态开始");
        orderDao.update(order.getUserId(), 0);
        log.info("------->修改订单状态结束");

        log.info("------->下订单结束了，哈哈O(∩_∩)O");
    }
}
