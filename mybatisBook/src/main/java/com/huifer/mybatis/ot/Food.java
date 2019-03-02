package com.huifer.mybatis.ot;

/**
 * 描述:
 *
 * @author huifer
 * @date 2019-02-26
 */

import java.util.ArrayList;
import java.util.List;

/**
 * 食物
 */
public class Food {
    public static void main(String[] args) {
        List<? extends ShuiGuo> sg = new ArrayList<>();
        /////////////
        // 不管干嘛都不能add
        YouZha youZha = new YouZha();
//        sg.add(youZha);

        PingGuo pg = new PingGuo();
//        sg.add(pg);
        Da<? extends ShuiGuo> shuiguo = new Da<PingGuo>(
                new PingGuo()
        );

        ShuiGuo data = shuiguo.getData();
        System.out.println(data);

        //////////////////////
        ShuTiao shu = new ShuTiao();
        PingGuo pg2 = new PingGuo();
        List<? super YouZha> youZhaArrayList = new ArrayList<>();

        youZhaArrayList.add(shu);
//        youZhaArrayList.add(pg2);


        System.out.println(youZhaArrayList);
    }
}

class  Da <T>{
    public Da(T data) {
        this.data = data;
    }

    private T data;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}

/**
 * 油炸
 */
class YouZha extends Food {

}

/**
 * 水果
 */
class ShuiGuo extends Food {

}
/**
 * 薯条
 */
class ShuTiao extends YouZha{

}

/**
 * 苹果
 */
class PingGuo extends ShuiGuo {
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("pingguo a ");
        sb.append('}');
        return sb.toString();
    }
}