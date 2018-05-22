package com.haozi.hzweb.bean.auth.service;


import com.haozi.hzweb.bean.auth.entity.Menu;
import com.haozi.hzweb.bean.auth.entity.page.MenuPage;
import com.haozi.hzweb.bean.auth.tools.Tree;

import java.util.List;
import java.util.Set;

public interface MenuService {
    Tree<Menu> getSysMenuTree(Long id);
    List<Tree<Menu>> listMenuTree(Long id);
    Tree<Menu> getTree();
    Tree<Menu> getTree(Long id);
    List<Menu> list();
    public List<Menu> lists(MenuPage menuPage);
    int remove(Long id);
    int save(Menu menu);
    int update(Menu menu);
    Menu get(Long id);
    Set<String> listPerms(Long userId);
}
