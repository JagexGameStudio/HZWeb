package com.haozi.hzweb.adapter.shiro;

import com.haozi.hzweb.bean.auth.dao.UserMapper;
import com.haozi.hzweb.bean.auth.entity.User;
import com.haozi.hzweb.bean.auth.service.impl.MenuServiceImpl;
import com.haozi.hzweb.bean.tools.shiro.ShiroUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Slf4j
public class UserRealm extends AuthorizingRealm{

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private MenuServiceImpl menuService;

    /**
     * 授权
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        Long userId = ShiroUtils.getUserId();
        Set<String> perms = menuService.listPerms(userId);
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.setStringPermissions(perms);
        return info;
    }

    /**
     * 认证
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String username = (String)authenticationToken.getPrincipal();
        Map<String,String> map = new HashMap<>();
        map.put("username",username);
        String password = new String((char[])authenticationToken.getCredentials());
        User user = userMapper.selectByUsername(username);
        if(user==null){
            throw new UnknownAccountException("用户不存在");
        }
        if(!password.equals(user.getPassword())){
            throw new UnknownAccountException("密码不正确");
        }
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user,password,getName());
        return info;
    }
}
