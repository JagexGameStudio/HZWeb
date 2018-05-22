package com.haozi.hzweb.bean.auth.service.impl;

import com.haozi.hzweb.bean.auth.dao.UserMapper;
import com.haozi.hzweb.bean.auth.dao.UserRoleMapper;
import com.haozi.hzweb.bean.auth.entity.User;
import com.haozi.hzweb.bean.auth.entity.UserRole;
import com.haozi.hzweb.bean.auth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Autowired
    UserRoleMapper userRoleMapper;

    @Override
    public User get(Long id) {
        List<Long> roleIds = userRoleMapper.listRoleId(id);
        User user = userMapper.selectByPrimaryKey(id);
        user.setRoleIds(roleIds);
        return user;
    }

    @Override
    public List<User> list(Map<String, Object> map) {
        return userMapper.list(map);
    }

    @Override
    public List<User> getlist() {
        return userMapper.getlist();
    }

    @Override
    public int count(Map<String, Object> map) {
        return userMapper.count(map);
    }

    @Override
    public int save(User user) {
        int count = userMapper.insert(user);
        Long userId = user.getUserId();
        List<Long> roles = user.getRoleIds();
        userRoleMapper.removeByUserId(userId);
        List<UserRole> list = new ArrayList<>();
        for(long roleId : roles){
            UserRole ur = new UserRole();
            ur.setUserId(userId);
            ur.setRoleId(roleId);
            list.add(ur);
        }
        if(list.size()>0){
            userRoleMapper.batchSave(list);
        }
        return count;
    }

    @Override
    public int update(User user) {
        int r = userMapper.updateByPrimaryKey(user);
        Long userId = user.getUserId();
        List<Long> roles = user.getRoleIds();
        userRoleMapper.removeByUserId(userId);
        List<UserRole> list = new ArrayList<>();
        for(Long roleId : roles){
            UserRole ur = new UserRole();
            ur.setUserId(userId);
            ur.setRoleId(roleId);
            list.add(ur);
        }
        if(list.size()>0){
            userRoleMapper.batchSave(list);
        }
        return r;
    }

    @Override
    public int remove(Long userId) {
        userRoleMapper.removeByUserId(userId);
        return userMapper.deleteByPrimaryKey(userId);
    }

    @Override
    public int batchremove(List<Long> userIds) {
        int count = userMapper.batchRemove(userIds);
        userRoleMapper.batchRemoveByUserId(userIds);
        return count;
    }

    @Override
    public boolean exit(Map<String, Object> params) {
        boolean exit;
        exit = userMapper.list(params).size()>0;
        return exit;
    }

    @Override
    public Set<String> listRoles(Long userId) {
        return null;
    }

    @Override
    public int resetPwd(User user) {
        int r = userMapper.updateByPrimaryKey(user);
        return r;
    }
}
