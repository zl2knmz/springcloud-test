package com.liang.redis.configure;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.client.codec.StringCodec;
import org.redisson.config.Config;
import org.redisson.config.SingleServerConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;

import javax.annotation.Resource;

@Slf4j
@Configuration
public class RedissionConfig {

//    @Resource
//    private LettuceConnectionFactory lettuceConnectionFactory;

    @Bean
    public RedissonClient getRedisson() {
        Config config = new Config();
        //单机模式  依次设置redis地址和密码
//        SingleServerConfig singleServerConfig = config.useSingleServer().
//                setAddress("redis://" + lettuceConnectionFactory.getHostName() + ":" + lettuceConnectionFactory.getPort());

        SingleServerConfig singleServerConfig = config.useSingleServer().
                setAddress("redis://192.168.1.128:6379" );
//        String password = lettuceConnectionFactory.getPassword();
        String password = null;
        if (StringUtils.isNotBlank(password)) {
            singleServerConfig.setPassword(password);
        }
        config.setCodec(new StringCodec());
        return Redisson.create(config);
    }


}
