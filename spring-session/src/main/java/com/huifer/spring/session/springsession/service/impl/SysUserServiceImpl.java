package com.huifer.spring.session.springsession.service.impl;

import static com.huifer.spring.session.springsession.service.impl.SysRoleServiceImpl.like;
import static com.huifer.spring.session.springsession.util.ResultUtil.success;

import com.huifer.spring.session.springsession.entity.SysRole;
import com.huifer.spring.session.springsession.entity.SysUser;
import com.huifer.spring.session.springsession.entity.SysUserRole;
import com.huifer.spring.session.springsession.entity.enums.ResultEnum;
import com.huifer.spring.session.springsession.repository.SysRoleRepository;
import com.huifer.spring.session.springsession.repository.SysUserRepository;
import com.huifer.spring.session.springsession.repository.SysUserRoleRepository;
import com.huifer.spring.session.springsession.service.SysUserService;
import com.huifer.spring.session.springsession.table.SysUserTable;
import com.huifer.spring.session.springsession.util.ResultUtil;
import com.huifer.spring.session.springsession.util.ShrioUtil;
import com.huifer.spring.session.springsession.vo.ResultVo;
import com.huifer.spring.session.springsession.vo.SysUserVo;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * <p>Title : SysUserServiceImpl </p>
 * <p>Description : </p>
 *
 * @author huifer
 * @date 2019-06-26
 */
@Service
@Transactional
@Slf4j
public class SysUserServiceImpl implements SysUserService {

    @Autowired
    private SysUserRepository sysUserRepository;
    @Autowired
    private SysUserRoleRepository sysUserRoleRepository;
    @Autowired
    private SysRoleRepository sysRoleRepository;


    @Override
    public SysUser findByAccount(String account) {
        return sysUserRepository.findByAccount(account);
    }

    @Override
    public ResultVo saveSysUser(SysUserTable sysUserTable) {
        if (sysUserRepository.findByAccount(sysUserTable.getAccount()) != null) {
            log.error("账户存在 = {}" + sysUserTable.getAccount());
            return ResultUtil.error(ResultEnum.ACCOUNT_EXIST.getCode(),
                    ResultEnum.ACCOUNT_EXIST.getMessage());
        }
        SysUser sysUser = new SysUser();
        BeanUtils.copyProperties(sysUserTable, sysUser);
        String salt = ShrioUtil.getSalt();
        String md5 = ShrioUtil.MD5(sysUser.getPassword(), salt);
        sysUser.setSalt(salt);
        sysUser.setPassword(md5);
        SysUser save = sysUserRepository.save(sysUser);
        log.info("用户保存 = {}" + save);

        List<SysUserRole> sysUserRoles = new ArrayList<>();

        if (sysUserTable.getSysRoleList().size() > 0) {

            for (SysRole ro : sysUserTable.getSysRoleList()) {
                SysUserRole sysUserRole = new SysUserRole();
                sysUserRole.setUserId(save.getId());
                sysUserRole.setRoleId(ro.getId());
                sysUserRoles.add(sysUserRole);
            }
        }
        List<SysUserRole> sysUserRoles1 = sysUserRoleRepository.saveAll(sysUserRoles);
        log.info("用户角色信息保存= {}" + sysUserRoles1);

        return success();
    }

    @Override
    public ResultVo selectSysUserList(String name, Pageable pageable) {
        Page<SysUser> name1 = sysUserRepository.findAll(new Specification<SysUser>() {
            @Override
            public Predicate toPredicate(Root<SysUser> root, CriteriaQuery<?> criteriaQuery,
                    CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<>();
                if (StringUtils.isEmpty(name)) {
                    predicates.add(criteriaBuilder
                            .like(root.get("name").as(String.class), like(name)));
                }
                Predicate[] predicates1 = new Predicate[predicates.size()];
                return criteriaQuery.where(predicates.toArray(predicates1)).getRestriction();
            }
        }, pageable);
        return success(name1);
    }

    @Override
    public ResultVo selectSysUserDetail(Integer userId) {
        SysUserVo sysUserVo = new SysUserVo();
        SysUser sysUser = sysUserRepository.findById(userId).get();
        BeanUtils.copyProperties(sysUser, sysUserVo);
        log.info("用户信息={}" + sysUser);
        List<SysUserRole> byUserId = sysUserRoleRepository.findByUserId(userId);
        List<Integer> sysUserRoles = new ArrayList<>();
        byUserId.forEach(o -> {
            sysUserRoles.add(o.getRoleId());
        });

        List<SysRole> all = sysRoleRepository.findAllById(sysUserRoles);
        sysUserVo.setSysRoleVoList(all);
        log.info("用户角色 = {}" + all);

        return success(sysUserVo);
    }

    @Override
    public ResultVo updateSysUser(SysUserTable userTable) {

        boolean equals = sysUserRepository.findById(userTable.getId()).get().getAccount()
                .equals(userTable.getAccount());
        if (!equals) {
            if (sysUserRepository.findByAccount(userTable.getAccount()) != null) {
                log.error("userTable.getAccount()");
                return ResultUtil.error(ResultEnum.ACCOUNT_EXIST.getCode(),
                        ResultEnum.ACCOUNT_EXIST.getMessage());
            }
        }
        SysUser sysUser = new SysUser();

        BeanUtils.copyProperties(userTable, sysUser);

        String salt = ShrioUtil.getSalt();
        String md5 = ShrioUtil.MD5(sysUser.getPassword(), salt);
        sysUser.setSalt(salt);
        sysUser.setPassword(md5);
        SysUser save = sysUserRepository.save(sysUser);
        log.info("用户更新={}" + save);
        sysUserRoleRepository.deleteByUserId(userTable.getId());
        List<SysUserRole> sysUserRoles = new ArrayList<>();
        userTable.getSysRoleList().forEach(

                ro -> {
                    SysUserRole sysUserRole = new SysUserRole();
                    sysUserRole.setRoleId(ro.getId());
                    sysUserRole.setUserId(save.getId());
                    sysUserRoles.add(sysUserRole);
                }
        );
        List<SysUserRole> sysUserRoles1 = sysUserRoleRepository.saveAll(sysUserRoles);
        log.info("用户角色更新={}" + sysUserRoles1);
        return success();
    }

    @Override
    public ResultVo deleteSysUser(Integer userId) {
        sysUserRoleRepository.deleteByUserId(userId);

        sysUserRepository.deleteById(userId);
        return success();
    }
}
