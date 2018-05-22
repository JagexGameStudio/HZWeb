package com.haozi.hzweb.bean.auth.service;


import com.haozi.hzweb.bean.auth.entity.User;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface UserService {
    User get(Long id);
    List<User> list(Map<String, Object> map);
    List<User> getlist();
    int count(Map<String, Object> map);
    int save(User user);
    int update(User user);
    int remove(Long userId);
    int batchremove(List<Long> userIds);
    boolean exit(Map<String, Object> params);
    Set<String> listRoles(Long userId);
    int resetPwd(User user);
}
