package com.github.huifer.tuc.service.abc;

import static com.github.huifer.tuc.service.abc.HandlerOperation.MIDD_HANDLER_BEAN_NAME;
import static com.github.huifer.tuc.service.abc.HandlerOperation.ROLE_HANDLER_BEAN_NAME;
import com.github.huifer.tuc.model.AbsData;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.CachedIntrospectionResults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

@Service
public class Openmapi {

    static Map<Class<?>, String> aaa = new HashMap<>();

    static {
        aaa.put(MiddleRoleAndUserEntiry.class, MIDD_HANDLER_BEAN_NAME);
        aaa.put(RoleEntityHandler.class, ROLE_HANDLER_BEAN_NAME);
    }



    @Autowired
    private ApplicationContext context;

    public HandlerOperation handler(Class<?> clazz) {
        Map<String, HandlerOperation> beansOfType = context.getBeansOfType(HandlerOperation.class);
        // O(1)
        String s = aaa.get(clazz);
        return context.getBean(s, HandlerOperation.class);
//        HandlerOperation res = null;
//        // O(n)
//        for (Entry<String, HandlerOperation> entry : beansOfType.entrySet()) {
//            String k = entry.getKey();
//            HandlerOperation v = entry.getValue();
//            if (v.type().equals(clazz)) {
//                res = v;
//                break;
//            }
//
//        }
//        return res;
    }


    public void handler(AbsData data) {
        HandlerOperation handler = handler(data.getClass());
        handler.handler(data);

    }


}
