package com.huifer.design.adapter.ChaTou;

import org.junit.Test;

/**
 * 描述:
 * 使用类
 *
 * @author huifer
 * @date 2019-03-13
 */
public class Using {
    @Test
    public void testChinCha() {
        ChinaCha chinaCha = new ChinaChaImpl();
        chinaCha.method();
    }

    @Test
    public void testUsCha() {
        UsCha usCha = new UsChaImpl();
        ChaAdapter chaAdapter = new ChaAdapter(usCha);

        chaAdapter.method();
    }

    /**
     * 适配器的作用让 国内插头能够在us插头上用
     */
    @Test
    public void testAdapter() {
        // 我有国内的插头
        ChinaCha chinaCha = new ChinaChaImpl();
        // 我装了适配器
        ChaAdapter chaAdapter = new ChaAdapter(chinaCha);
        // 我能用国外的插头了
        chaAdapter.method();


    }
}
