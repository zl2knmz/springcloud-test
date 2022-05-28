package com.liang.config;

import org.springframework.stereotype.Component;

/**
 * @author zl
 * @date 2022/5/22 11:53
 */
//@Component
public class B {

    private A a;

    public A getA() {
        return a;
    }

    public void setA(A a) {
        this.a = a;
    }

    public B() {
        System.out.println("---B create success--");
    }
}
