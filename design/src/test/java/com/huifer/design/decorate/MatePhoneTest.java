package com.huifer.design.decorate;

import com.huifer.design.decorate.phone.HuaWei;
import com.huifer.design.decorate.phone.HuaWeiDec;
import com.huifer.design.decorate.phone.MatePhone;
import org.junit.Test;

public class MatePhoneTest {
    @Test
    public void call() {
        HuaWei matePhone = new MatePhone();
        matePhone.call();
    }

    @Test
    public void HuaWeiDec() {
        HuaWei matePhone = new MatePhone();
        matePhone.call();
        System.out.println("===============");
        HuaWei huaWeiDec = new HuaWeiDec(matePhone);
        huaWeiDec.call();
    }

}
