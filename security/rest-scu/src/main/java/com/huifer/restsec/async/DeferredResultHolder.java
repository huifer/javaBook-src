package com.huifer.restsec.async;

import lombok.Data;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.HashMap;
import java.util.Map;

/**
 * 描述:
 *
 * @author: huifer
 * @date: 2019-10-07
 */
@Data
@Component
public class DeferredResultHolder {

    /**
     * 订单号-> 处理结果
     */
    private Map<String, DeferredResult<String>> map = new HashMap<>();
}
