package com.liang;

import lombok.extern.slf4j.Slf4j;

/**
 * @author zl
 * @date 2022/2/16 16:31
 */
@Slf4j
public class KiteService {

    private String host;

    private int port;

    public KiteService(KiteProperties kiteProperties){
        this.host = kiteProperties.getHost();
        this.port = kiteProperties.getPort();
    }

    public void print(){
        log.info(this.host + ":" +this.port);
    }
}
