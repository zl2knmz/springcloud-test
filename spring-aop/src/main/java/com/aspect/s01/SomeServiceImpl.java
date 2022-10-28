package com.aspect.s01;

/**
 * @author zl
 * @date 2022/10/28 23:45
 */
public class SomeServiceImpl implements SomeService {
    @Override
    public String doSome() {
        System.out.println("doSome........");
        return "123";
    }
}
