package com.huifer.dubbo.spi;

/**
 * <p>Title : BaseServiceImplV1 </p>
 * <p>Description : </p>
 *
 * @author huifer
 * @date 2019-06-14
 */
public class BaseServiceImplV2 implements BaseService {

    @Override
    public String hello(String msg) {
        return "v2 : " + msg;
    }
}
