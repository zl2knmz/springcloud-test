package com.liang;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author zl
 * @date 2022/2/16 16:08
 */

@Configuration
@ConditionalOnClass(KiteService.class)
@EnableConfigurationProperties(KiteProperties.class)
@Slf4j
public class LiangAutoConfigure {

    @Autowired
    private KiteProperties kiteProperties;

    @Bean
    @ConditionalOnMissingBean(KiteService.class)
    @ConditionalOnProperty(prefix = "liang.example",value = "enabled", havingValue = "true")
    KiteService kiteService(){
        return new KiteService(kiteProperties);
    }
}