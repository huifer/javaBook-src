package com.huifer.dubbo.server.provider;

/**
 * <p>Title : Protocol$Adaptive </p>
 * <p>Description : </p>
 *
 * @author huifer
 * @date 2019-06-17
 */

import org.apache.dubbo.common.extension.ExtensionLoader;

public class Protocol$Adaptive implements org.apache.dubbo.rpc.Protocol {

    public void destroy() {
        throw new UnsupportedOperationException(
                "The method public abstract void org.apache.dubbo.rpc.Protocol.destroy() of interface org.apache.dubbo.rpc.Protocol is not adaptive method!");
    }

    public int getDefaultPort() {
        throw new UnsupportedOperationException(
                "The method public abstract int org.apache.dubbo.rpc.Protocol.getDefaultPort() of interface org.apache.dubbo.rpc.Protocol is not adaptive method!");
    }

    public org.apache.dubbo.rpc.Exporter export(org.apache.dubbo.rpc.Invoker arg0)
            throws org.apache.dubbo.rpc.RpcException {
        if (arg0 == null) {
            throw new IllegalArgumentException("org.apache.dubbo.rpc.Invoker argument == null");
        }
        if (arg0.getUrl() == null) {
            throw new IllegalArgumentException(
                    "org.apache.dubbo.rpc.Invoker argument getUrl() == null");
        }
        org.apache.dubbo.common.URL url = arg0.getUrl();
        String extName = (url.getProtocol() == null ? "dubbo" : url.getProtocol());
        if (extName == null) {
            throw new IllegalStateException(
                    "Failed to get extension (org.apache.dubbo.rpc.Protocol) name from url (" + url
                            .toString() + ") use keys([protocol])");
        }
        org.apache.dubbo.rpc.Protocol extension = ExtensionLoader
                .getExtensionLoader(org.apache.dubbo.rpc.Protocol.class).getExtension(extName);
        return extension.export(arg0);
    }

    public org.apache.dubbo.rpc.Invoker refer(java.lang.Class arg0,
                                              org.apache.dubbo.common.URL arg1) throws org.apache.dubbo.rpc.RpcException {
        if (arg1 == null) {
            throw new IllegalArgumentException("url == null");
        }
        org.apache.dubbo.common.URL url = arg1;
        String extName = (url.getProtocol() == null ? "dubbo" : url.getProtocol());
        if (extName == null) {
            throw new IllegalStateException(
                    "Failed to get extension (org.apache.dubbo.rpc.Protocol) name from url (" + url
                            .toString() + ") use keys([protocol])");
        }
        org.apache.dubbo.rpc.Protocol extension = ExtensionLoader
                .getExtensionLoader(org.apache.dubbo.rpc.Protocol.class).getExtension(extName);
        return extension.refer(arg0, arg1);
    }
}
