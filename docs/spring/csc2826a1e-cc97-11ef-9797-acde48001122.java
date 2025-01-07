package com.huifer.ssm.service;

import com.huifer.ssm.mapper.ItemMapper;
import com.huifer.ssm.pojo.Item;
import com.huifer.ssm.pojo.ItemExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 描述:
 *
 * @author huifer
 * @date 2019-03-11
 */
@Service
public class ItemServiceImpl implements ItemService {
    @Autowired
    private ItemMapper itemMapper;


    public List<Item> queryItemList() {
        // 使用逆向工程代码完成持久层查询
        ItemExample example = new ItemExample();
        List<Item> list = itemMapper.selectByExample(example);
        return list;
    }

    public Item queryItemById(Integer id) {
        return id == null ? null : itemMapper.selectByPrimaryKey(id);
    }

    public void updateItem(Item item) {
        if (item == null) {
            return;
        }
        if (item.getId() == null) {
            return;
        }

        itemMapper.updateByPrimaryKeySelective(item);
    }
}
