package com.haozi.hzweb.bean.auth.service;

import com.haozi.hzweb.bean.auth.entity.Role;

import java.util.List;

public interface RoleService {
    Role get(Long id);
    List<Role> list();
    int save(Role role);
    int update(Role role);
    int remove(Long id);
    List<Role> list(Long userId);
}
