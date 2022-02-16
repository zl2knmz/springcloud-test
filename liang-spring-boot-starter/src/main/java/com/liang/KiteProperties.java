package com.liang;

import org.springframework.boot.context.properties.ConfigurationProperties;
import lombok.Data;

/**
 * @author zl
 * @date 2022/2/16 16:24
 */
@Data
@ConfigurationProperties("com.liang")
public class KiteProperties {

    private String host;

    private int port;
}
