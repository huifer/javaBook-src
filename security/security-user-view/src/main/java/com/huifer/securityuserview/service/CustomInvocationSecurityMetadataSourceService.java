package com.huifer.securityuserview.service;

import com.huifer.securityuserview.entity.SysResource;
import com.huifer.securityuserview.entity.SysRole;
import com.huifer.securityuserview.repository.SysResourceRepository;
import com.huifer.securityuserview.repository.SysRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.*;
/**
 * 描述:
 *
 * @author huifer
 * @date 2019-03-24
 */

@Service
public class CustomInvocationSecurityMetadataSourceService implements FilterInvocationSecurityMetadataSource {

    @Autowired
    private SysRoleRepository roleRepository;

    @Autowired
    private SysResourceRepository resourceRepository;

    /**
     * 权限和url映射
     */
    private static Map<String, Collection<ConfigAttribute>> resourceMap = null;

    @PostConstruct
    private void loadResourceDefine() {
        // 在Web服务器启动时，提取系统中的所有权限。
        List<SysRole> roleList = roleRepository.findAll();

        List<String> roleNameList = new ArrayList<String>();
        if (roleList != null && roleList.size() > 0) {
            for (SysRole role : roleList) {
                roleNameList.add(role.getRoleName());
            }
        }

        resourceMap = new HashMap<String, Collection<ConfigAttribute>>();
        for (String roleName : roleNameList) {
            ConfigAttribute configAttribute = new SecurityConfig(roleName);
            List<String> urlList = new ArrayList<String>();
            List<SysResource> resourceList = resourceRepository.findByRoleName(roleName);
            if (resourceList != null && resourceList.size() > 0) {
                for (SysResource resource : resourceList) {
                    urlList.add(resource.getResourceName());
                }
            }
            for (String res : urlList) {
                String url = res;
                if (resourceMap.containsKey(url)) {
                    Collection<ConfigAttribute> value = resourceMap.get(url);
                    value.add(configAttribute);
                    resourceMap.put(url, value);
                } else {
                    Collection<ConfigAttribute> atts = new ArrayList<ConfigAttribute>();
                    atts.add(configAttribute);
                    resourceMap.put(url, atts);
                }
            }
        }

    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return new ArrayList<ConfigAttribute>();
    }

    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        // object 是用户请求的url
        FilterInvocation filterInvocation = (FilterInvocation) object;
        if (resourceMap == null) {
            loadResourceDefine();
        }
        Iterator<String> iterator = resourceMap.keySet().iterator();
        while (iterator.hasNext()) {
            String resURL = iterator.next();
            // 优化请求路径后面带参数的部分
            RequestMatcher requestMatcher = new AntPathRequestMatcher(resURL);
            if (requestMatcher.matches(filterInvocation.getHttpRequest())) {
                return resourceMap.get(resURL);
            }
        }
        return null;
    }

    @Override
    public boolean supports(Class<?> arg0) {
        return true;
    }

}
