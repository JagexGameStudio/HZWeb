package com.haozi.hzweb.bean.auth.service.impl;

import com.haozi.hzweb.bean.auth.dao.RoleMapper;
import com.haozi.hzweb.bean.auth.dao.RoleMenuMapper;
import com.haozi.hzweb.bean.auth.dao.UserRoleMapper;
import com.haozi.hzweb.bean.auth.entity.Role;
import com.haozi.hzweb.bean.auth.entity.RoleMenu;
import com.haozi.hzweb.bean.auth.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    public RoleMapper roleMapper;

    @Autowired
    public UserRoleMapper userRoleMapper;

    @Autowired
    public RoleMenuMapper roleMenuMapper;

    @Override
    public Role get(Long id) {
        Role role = roleMapper.selectByPrimaryKey(id);
        return role;
    }

    @Override
    public List<Role> list() {
        List<Role> roles = roleMapper.listRole();
        return roles;
    }

    @Override
    public int save(Role role) {
        int count = roleMapper.insert(role);
        List<Long> menuIds = role.getMenuIds();
        Long roleId = role.getRoleId();
        List<RoleMenu> rms = new ArrayList<>();
        for(Long menuId : menuIds){
            RoleMenu rm = new RoleMenu();
            rm.setRoleId(roleId);
            rm.setMenuId(menuId);
            rms.add(rm);
        }
        roleMenuMapper.removeByRoleId(roleId);
        if(rms.size()>0){
            int sss = roleMenuMapper.batchSave(rms);
        }
        return count;
    }

    @Override
    public int update(Role role) {
        int r = roleMapper.updateByPrimaryKey(role);
        Long roldId = role.getRoleId();
        List<Long> menuIds = role.getMenuIds();
        List<RoleMenu> rms = new ArrayList<>();
        for(Long menuId:menuIds){
            RoleMenu rm = new RoleMenu();
            rm.setRoleId(roldId);
            rm.setMenuId(menuId);
            rms.add(rm);
        }
        roleMenuMapper.removeByRoleId(roldId);
        if(rms.size()>0){
            roleMenuMapper.batchSave(rms);
        }
        return r;
    }

    @Override
    public int remove(Long id) {
        int count = roleMapper.deleteByPrimaryKey(id);
        roleMenuMapper.deleteByPrimaryKey(id);
        return count;
    }

    @Override
    public List<Role> list(Long userId) {
        List<Long> rolesIds = userRoleMapper.listRoleId(userId);
        List<Role> roles = roleMapper.listRole();
        for(Role role: roles){
            role.setRoleSign("false");
            for(Long roleId:rolesIds){
                if(role.getRoleId()==roleId){
                    role.setRoleSign("true");
                    break;
                }
            }
        }
        return roles;
    }
}
