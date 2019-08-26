package com.huifer.redis.history;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Service
public class TesService {

    @Autowired(required = false)
    EsMapper esMapper;

    Random rand = new Random();


    public void randomAdd(int i) {
        List<String> names = Arrays.asList(new String[]{"a", "b", "c"});
        for (int i1 = 0; i1 < i; i1++) {
            String s = names.get(rand.nextInt(names.size()));
            Es es = new Es();
            es.setName(s);
            es.setId(new Long(i1));
            add(es);
        }
    }


    @Transactional
    public void add(Es es) {
        esMapper.insert(es);
    }

}
