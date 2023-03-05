package com.github.huifer.full.shiro.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.huifer.full.shiro.dao.ShiroUserDao;
import com.github.huifer.full.shiro.dao.ShiroUserExtendDao;
import com.github.huifer.full.shiro.entity.ShiroCompany;
import com.github.huifer.full.shiro.entity.ShiroDept;
import com.github.huifer.full.shiro.entity.ShiroPost;
import com.github.huifer.full.shiro.entity.ShiroUser;
import com.github.huifer.full.shiro.entity.ShiroUserExtend;
import com.github.huifer.full.shiro.ex.ServerEx;
import com.github.huifer.full.shiro.model.req.user.UserCreateParam;
import com.github.huifer.full.shiro.model.res.user.UserCompany;
import com.github.huifer.full.shiro.model.res.user.UserInfo;
import com.github.huifer.full.shiro.service.CompanyService;
import com.github.huifer.full.shiro.service.DeptService;
import com.github.huifer.full.shiro.service.PostService;
import com.github.huifer.full.shiro.service.UserService;
import com.github.huifer.full.shiro.utils.EncryptionUtils;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class UserServiceImpl implements UserService {

  @Autowired
  private ShiroUserDao userDao;

  @Override
  public boolean create(
      UserCreateParam param) {
    String loginName = param.getLoginName();
    if (!StringUtils.hasText(loginName)) {
      throw new ServerEx("login name not null");
    }
    ShiroUser shiroUserEntityByLoginName = userDao
        .findShiroUserEntityByLoginName(param.getLoginName());
    if (shiroUserEntityByLoginName == null) {

      ShiroUser shiroUser = new ShiroUser();
      shiroUser.setLoginName(param.getLoginName());
      shiroUser.setUsername(param.getUsername());
      String salt = EncryptionUtils.randomSalt(EncryptionUtils.SLAT_LEN);
      shiroUser.setPassword(EncryptionUtils.genMD5Hash(param.getPassword(), salt));
      shiroUser.setSalt(salt);
      shiroUser.setEmail(param.getEmail());
      shiroUser.setGender(param.getGender());
      return userDao.insert(shiroUser) > 0;
    }
    throw new ServerEx("登录名已存在");
  }


  @Override
  public boolean update(UserCreateParam param, int id) {
    ShiroUser byId = userDao.selectById(id);
    if (byId != null) {
      byId.setUsername(param.getUsername());
      String salt = EncryptionUtils.randomSalt(EncryptionUtils.SLAT_LEN);
      byId.setPassword(EncryptionUtils.genMD5Hash(param.getPassword(), salt));
      byId.setSalt(salt);
      byId.setEmail(param.getEmail());
      byId.setGender(param.getGender());
      int i = userDao.updateById(byId);
      return i > 0;
    }
    throw new ServerEx("当前id对应用户不存在");

  }

  @Override
  public boolean delete(int id) {
    return this.userDao.deleteById(id) > 0;
  }

  @Override
  public Page<ShiroUser> findByUserList(
      String username, String loginName, Integer gender, String email, int start, int offset) {
    IPage<ShiroUser> userPage = new Page<>(start, offset);
    return this.userDao
        .findByUserList(username, loginName, gender, email, userPage);
  }

  @Override
  public ShiroUser byId(int id) {
    return this.userDao.selectById(id);
  }

  @Autowired
  private CompanyService companyService;
  @Autowired
  private ShiroUserExtendDao shiroUserExtendDao;
  @Autowired
  private DeptService deptService;
  @Autowired
  private PostService postService;

  public UserInfo userInfo(int userId) {
    // 用户登陆信息
    ShiroUser shiroUser = this.userDao.selectById(userId);
    if (shiroUser == null) {
      return null;
    }
    List<ShiroUserExtend> userExtends = this.shiroUserExtendDao.findByUserId(userId);

    UserInfo userInfo = new UserInfo();
    userInfo.setLoginName(shiroUser.getLoginName());
    userInfo.setUsername(shiroUser.getUsername());
    userInfo.setEmail(shiroUser.getEmail());
    userInfo.setGender(shiroUser.getGender());
    if (userExtends == null) {
      return userInfo;
    }
    List<UserCompany> userCompanies = new ArrayList<>(userExtends.size());

    for (ShiroUserExtend userExtend : userExtends) {
      UserCompany userCompany = new UserCompany();

      Integer companyId = userExtend.getCompanyId();
      if (companyId != null) {
        ShiroCompany company = this.companyService.byId(companyId);
        if (company != null) {
          userCompany.setCompName(company.getName());
        }
      }

      Integer deptId = userExtend.getDeptId();
      if (deptId != null) {
        ShiroDept shiroDept = this.deptService.byId(deptId);
        if (shiroDept != null) {
          userCompany.setDeptName(shiroDept.getName());
        }
      }

      Integer postId = userExtend.getPostId();
      if (postId != null) {
        ShiroPost shiroPost = this.postService.byId(postId);
        if (shiroPost != null) {
          userCompany.setPostName(shiroPost.getName());
        }
      }
      userCompanies.add(userCompany);
    }
    userInfo.setCompanyList(userCompanies);
    return userInfo;
  }
}
