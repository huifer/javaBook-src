package com.huifer.spring.session.springsession.service;

import java.util.Set;

/**
 * <p>Title : SysResourceService </p>
 * <p>Description : </p>
 *
 * @author huifer
 * @date 2019-06-26
 */
public interface SysResourceService {

    Set<String> selectUserPerms(Integer userId);

}
