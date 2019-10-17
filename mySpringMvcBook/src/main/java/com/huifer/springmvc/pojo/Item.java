package com.huifer.springmvc.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 描述:
 *
 * @author huifer
 * @date 2019-03-10
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Item {
    private String name;
    private Double price;
    private Date date;
}
