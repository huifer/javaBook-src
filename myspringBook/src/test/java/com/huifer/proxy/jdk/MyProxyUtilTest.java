package com.huifer.proxy.jdk;

import com.huifer.proxy.BaseService;
import com.huifer.proxy.impl.BaseServiceImpl;
import org.junit.Test;

public class MyProxyUtilTest {
    @Test
    public void testJdkProxy(){

        BaseService bs = new BaseServiceImpl();
        BaseService proxy = MyProxyUtil.getBaseServiceWithJdk(bs);
        proxy.doSome();

    }

    @Test
    public void testCgLibProxy(){

        BaseService bs = new BaseServiceImpl();
        BaseService proxy = MyProxyUtil.getBaseServiceWithCgLib(bs);
        proxy.doSome();

    }


}