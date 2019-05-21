package com.huifer.design.delegate;

import com.huifer.design.delegate.subordinate.Dev;
import com.huifer.design.delegate.subordinate.HD;
import com.huifer.design.delegate.subordinate.QD;
import com.huifer.design.delegate.subordinate.YW;
import java.util.HashMap;

/**
 * <p>Title : XMFZR </p>
 * <p>Description : 项目负责人</p>
 *
 * @author huifer
 * @date 2019-05-21
 */
public class XMFZR {


    private HashMap<String, Dev> map = new HashMap<>();

    public XMFZR() {
        map.put("前端", new QD());
        map.put("后端", new HD());
        map.put("运维", new YW());
    }

    public static void main(String[] args) {
// 1. 确认一个任务
        String s = "这是一个随机任务";

        XMFZR xmfzr = new XMFZR();
// 2. 模拟委派的过程
        double random = Math.random();
        if (random < 0.3) {
            Dev qd = xmfzr.getMap().get("前端");
            qd.work(s);
        } else if (random > 0.3 & random < 0.6) {
            Dev hd = xmfzr.getMap().get("后端");
            hd.work(s);
        } else {
            Dev yw = xmfzr.getMap().get("运维");
            yw.work(s);
        }


    }

    public HashMap<String, Dev> getMap() {
        return map;
    }

}
