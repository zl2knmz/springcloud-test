package com.liang.lock;

import com.liang.redis.service.RedisService;
import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

/**
 * @author zl
 * @date 2022/5/27 23:56
 */
public class GoodController {
    private final int serverPort = 8888;
    private final String REDIS_LOCK = "good_lock";

    @Autowired
    private Redisson redisson;

    @Autowired
    private RedisService redisService;

    public String buyGoods() throws Exception {
//        String vlaue = UUID.randomUUID().toString() + Thread.currentThread().getName();
        RLock redissonLock = redisson.getLock(REDIS_LOCK);
        redissonLock.lock();
        String goodsKey = "goods:001";

        try {
            String result = redisService.get(goodsKey).toString();
            int goodsNumber = result == null ? 0 : Integer.parseInt(result);
            if (goodsNumber > 0) {
                int realNumber = goodsNumber - 1;
                redisService.set(goodsKey, String.valueOf(realNumber));
                System.out.println("成功买到商品，库存还剩下：" + realNumber + " 件 \t 服务提供端口：" + serverPort);
                return "成功买到商品，库存还剩下：" + realNumber + " 件 \t 服务提供端口：" + serverPort;
            } else {
                System.out.println("商品已经售罄，欢迎下次光临 \t 服务提供端口：" + serverPort);
            }
            return  "商品已经售罄，欢迎下次光临 \t 服务提供端口：" + serverPort;
        } finally {
            if (redissonLock.isLocked() && redissonLock.isHeldByCurrentThread()) {
                redissonLock.unlock();
            }
        }

    }

    public static void main(String[] args) {

    }
}
