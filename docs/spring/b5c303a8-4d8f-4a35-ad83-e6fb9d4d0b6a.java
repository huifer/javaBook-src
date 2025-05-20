package com.huifer.dubbo.client.mock;

import com.huifer.dubbo.server.api.DubboVersion1;

/**
 * <p>Title : MockDemo </p>
 * <p>Description : </p>
 *
 * @author huifer
 * @date 2019-06-14
 */
public class MockDemo implements DubboVersion1 {

    @Override
    public String sayHelloV1(String o) {
        return "服务降级-mock : " + o;
    }
}
