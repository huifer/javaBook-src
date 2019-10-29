package com.huifer.xz.controller;

import com.huifer.xz.mq.MsgProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 描述:
 *
 * @author: huifer
 * @date: 2019-10-16
 */
@RestController
@RequestMapping("/")
public class RabbitController {
    @Autowired
    private MsgProducer rabbitSender;

}
