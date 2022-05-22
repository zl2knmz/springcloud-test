package com.liang.config;

import org.springframework.stereotype.Component;

/**
 * @author zl
 * @date 2022/5/22 11:52
 */
@Component
public class A {

    private B b;

    public B getB() {
        return b;
    }

    public void setB(B b) {
        this.b = b;
    }

    public A() {
        System.out.println("---A create success--");
    }
}
