package com.huifer.zk.zkfind.loadbalance;

import java.util.List;

/**
 * <p>Title : AbstractLoadBanance </p>
 * <p>Description : </p>
 *
 * @author huifer
 * @date 2019-06-13
 */
public abstract class AbstractLoadBanance implements LoadBanalce {

    @Override
    public String randHost(List<String> servicePaths) {
        if (servicePaths.isEmpty()) {
            return null;
        }
        if (servicePaths.size() == 1) {
            return servicePaths.get(0);
        }
        return doRandHost(servicePaths);
    }

    protected abstract String doRandHost(List<String> servicePaths);
}
