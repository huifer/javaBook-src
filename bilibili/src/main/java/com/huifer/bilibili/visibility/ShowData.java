package com.huifer.bilibili.visibility;

import java.util.List;

public interface ShowData {
    /**
     * 可见字段
     * @param displayFiled
     * @return
     */
    Object display(List<String> displayFiled ) throws IllegalAccessException;
}
