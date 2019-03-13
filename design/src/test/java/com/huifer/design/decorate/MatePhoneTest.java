package com.huifer.design.decorate;

import org.junit.Test;

import static org.junit.Assert.*;

public class MatePhoneTest {
    @Test
    public void call() {
        HuaWei matePhone = new MatePhone();
        matePhone.call();
    }

    @Test
    public void HuaWeiDec(){
        HuaWei matePhone = new MatePhone();
        matePhone.call();
        System.out.println("===============");
        HuaWei huaWeiDec = new HuaWeiDec(matePhone);
        huaWeiDec.call();
    }

}