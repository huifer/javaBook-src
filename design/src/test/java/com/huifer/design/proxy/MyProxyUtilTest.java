package com.huifer.design.proxy;

import org.junit.Test;

public class MyProxyUtilTest {


    @Test
    public void getBaseServiceWithJdk() {
        BaseService bs = new BaseServiceImpl();
        BaseService proxy = MyProxyUtil.getBaseServiceWithJdk(bs);
        proxy.doSome();
    }

    @Test
    public void getBaseServiceWithCgLib() {
        BaseService bs = new BaseServiceImpl();
        BaseService proxy = MyProxyUtil.getBaseServiceWithCgLib(bs);
        proxy.doSome();

    }
}