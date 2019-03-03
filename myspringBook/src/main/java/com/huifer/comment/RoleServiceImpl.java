package com.huifer.comment;

import org.springframework.stereotype.Component;

/**
 * 描述:
 *
 * @author huifer
 * @date 2019-03-03
 */
@Component
public class RoleServiceImpl implements RoleService {
    @Override
    public void saveRole() {
        System.out.println("save role");
    }
}
