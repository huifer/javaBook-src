package com.huifer.redis.history;

import com.huifer.redis.string.FastJsonUtils;
import com.huifer.redis.string.StringRedisExample;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 在表中有很多数据的情况下,查询方式有多种,这些查询频率相对较高.使用redis进行查询缓存<br/>
 * 定时任务呢
 * 内设自增长列,整点查询的时候,
 * 1. 整点的查询累加到Redis缓存中,并且获取最大的自增长数字
 * 2. 后续查询通过步骤(1) 得到的最大数字进行一次过滤查询
 */
@Service
@Slf4j
public class WorkService {
    public static final String HISTORY = "history";
    @Autowired
    private TesService tesService;
    @Autowired
    private EsMapper esMapper;
    @Autowired
    private StringRedisExample stringRedisExample;

    public void work() {
        TableStatus t_es = esMapper.qTable("t_es");
        // 1. 定时任务
        // 1. 每次操作先从redis读取数据,数据进行累加
        //  假设我需要对com.huifer.redis.history.EsMapper.query 方法进行数据累加


        String string = stringRedisExample.getString(HISTORY);
        HistoryEntity stringBean = FastJsonUtils.toBean(string, HistoryEntity.class);

        int start = 0;
        int end = t_es.getAutoIncrement();

        if (stringBean == null) {
            start = 0;
            List<QueryResult> query = esMapper.query(start, end);
            HistoryEntity h = new HistoryEntity();
            h.setBigSelfPro(end);
            h.setQueryResult(query);

            stringRedisExample.setStringBean(HISTORY, h);
            System.out.println();
        } else {
            start = stringBean.getBigSelfPro() - 1;
            List<QueryResult> query = esMapper.query(start, end);
            HistoryEntity h = new HistoryEntity();
            h.setBigSelfPro(end);
            h.setQueryResult(query);

            HistoryEntity historyEntity = old2new(stringBean, h);
            stringRedisExample.setStringBean(HISTORY, historyEntity);
            System.out.println();
        }
    }

    private HistoryEntity old2new(HistoryEntity o, HistoryEntity newH) {
        HistoryEntity tHistoryEntity = new HistoryEntity();
        tHistoryEntity.setLastTime(System.currentTimeMillis());
        tHistoryEntity.setName(newH.getName());
        tHistoryEntity.setSql(newH.getSql());
        tHistoryEntity.setBigSelfPro(newH.getBigSelfPro());


        tHistoryEntity.setQueryResult(old2new(o.getQueryResult(), newH.getQueryResult()));
        return tHistoryEntity;
    }

    private List<QueryResult> old2new(List<QueryResult> h1, List<QueryResult> h2) {

        if (h2.isEmpty()) {
            return h1;
        }
        List<QueryResult> all = new ArrayList<>();

        all.addAll(h1);
        all.addAll(h2);

        List<QueryResult> result = new ArrayList<>();
        all.stream().collect(Collectors.groupingBy(
                QueryResult::getName,
                Collectors.summingLong(QueryResult::getNs)
        )).forEach((k, v) -> {
            result.add(QueryResult.builder().name(k).ns(v).build());
        });
        return result;
    }


}
