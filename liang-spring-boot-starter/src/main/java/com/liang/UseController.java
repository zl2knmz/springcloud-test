package com.liang;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.DefaultSingletonBeanRegistry;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zl
 * @date 2022/2/16 16:34
 */
@RestController
@RequestMapping(value = "use")
public class UseController {

    @Autowired
    private KiteService kiteService;

    @GetMapping(value = "print")
    public void print(){
        kiteService.print();
//        DefaultSingletonBeanRegistry
    }
}
