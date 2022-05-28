package com.liang.controller;

import com.liang.redis.service.RedisService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.UUID;

/**
 * @author zl
 * @date 2022/5/27 23:56
 */
@Api(tags = "商品管理")
@Slf4j
@RestController
@RequestMapping("/good")
public class GoodController {
    private final String REDIS_LOCK = "good_lock";

    @Value("${server.port}")
    private Integer serverPort;

    @Resource
    private RedissonClient redissonClient;

    @Resource
    private RedisService redisService;

    @ApiOperation(value = "获取库存")
    @GetMapping("/buy_good")
    public String buyGoods() {
        String value = UUID.randomUUID().toString() + Thread.currentThread().getName();
        log.info(value);

        RLock redissonLock = redissonClient.getLock(REDIS_LOCK);
        redissonLock.lock();
        String goodsKey = "goods:001";

        try {
            String result = redisService.get(goodsKey).toString();
            int goodsNumber = result == null ? 0 : Integer.parseInt(result);
            if (goodsNumber > 0) {
                int realNumber = goodsNumber - 1;
                redisService.set(goodsKey, realNumber);
                log.info("成功买到商品，库存还剩下：" + realNumber + " 件 \t 服务提供端口：" + serverPort);
                return "成功买到商品，库存还剩下：" + realNumber + " 件 \t 服务提供端口：" + serverPort;
            } else {
                log.info("商品已经售罄，欢迎下次光临 \t 服务提供端口：" + serverPort);
            }
            return "商品已经售罄，欢迎下次光临 \t 服务提供端口：" + serverPort;
        } finally {
            if (redissonLock.isLocked() && redissonLock.isHeldByCurrentThread()) {
                redissonLock.unlock();
            }
        }

    }

}
