package com.huifer.ssm.service;

import com.huifer.ssm.pojo.Item;

import java.util.List;

/**
 * 描述:
 *
 * @author huifer
 * @date 2019-03-11
 */
public interface ItemService {

        List<Item> queryItemList();

        Item queryItemById(Integer id);

        void updateItem(Item item);

}
