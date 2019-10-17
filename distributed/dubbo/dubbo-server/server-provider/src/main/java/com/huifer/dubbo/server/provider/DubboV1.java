package com.huifer.dubbo.server.provider;

import com.huifer.dubbo.server.api.DubboVersion1;

/**
 * <p>Title : DubboV1 </p>
 * <p>Description : </p>
 *
 * @author huifer
 * @date 2019-06-14
 */
public class DubboV1 implements DubboVersion1 {

    @Override
    public String sayHelloV1(String o) {
        return "version1 : " + o;
    }
}
