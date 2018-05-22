package com.haozi.hzweb.bean.auth.service.impl;

import com.haozi.hzweb.bean.auth.dao.MenuMapper;
import com.haozi.hzweb.bean.auth.dao.RoleMenuMapper;
import com.haozi.hzweb.bean.auth.entity.Menu;
import com.haozi.hzweb.bean.auth.entity.page.MenuPage;
import com.haozi.hzweb.bean.auth.service.MenuService;
import com.haozi.hzweb.bean.auth.tools.BuildTree;
import com.haozi.hzweb.bean.auth.tools.Tree;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuMapper menuMapper;

    @Autowired
    private RoleMenuMapper roleMenuMapper;

    @Override
    public Tree<Menu> getSysMenuTree(Long id) {
        List<Tree<Menu>> trees = new ArrayList<>();
        List<Menu> menus = menuMapper.listMenuByUserId(id);
        for(Menu menu : menus){
            Tree<Menu> tree = new Tree<>();
            tree.setId(menu.getMenuId().toString());
            tree.setParentId(menu.getParentId().toString());
            tree.setText(menu.getName());
            Map<String,Object> attributes = new HashMap<>();
            attributes.put("url",menu.getUrl());
            attributes.put("icon",menu.getIcon());
            tree.setAttributes(attributes);
            trees.add(tree);
        }
        // 默认顶级菜单为０，根据数据库实际情况调整
        Tree<Menu> t = BuildTree.build(trees);
        return t;
    }

    @Override
    public List<Tree<Menu>> listMenuTree(Long id) {
        List<Tree<Menu>> trees = new ArrayList<Tree<Menu>>();
        List<Menu> menuDOs = menuMapper.listMenuByUserId(id);
        for (Menu sysMenuDO : menuDOs) {
            Tree<Menu> tree = new Tree<Menu>();
            tree.setId(sysMenuDO.getMenuId().toString());
            tree.setParentId(sysMenuDO.getParentId().toString());
            tree.setText(sysMenuDO.getName());
            Map<String, Object> attributes = new HashMap<>();
            attributes.put("url", sysMenuDO.getUrl());
            attributes.put("icon", sysMenuDO.getIcon());
            tree.setAttributes(attributes);
            trees.add(tree);
        }
        // 默认顶级菜单为０，根据数据库实际情况调整
        List<Tree<Menu>> list = BuildTree.buildList(trees,"0");
        return list;
    }

    @Override
    public Tree<Menu> getTree() {
        List<Tree<Menu>> trees = new ArrayList<Tree<Menu>>();
        List<Menu> menuDOs = menuMapper.listMenu();
        for (Menu sysMenuDO : menuDOs) {
            Tree<Menu> tree = new Tree<Menu>();
            tree.setId(sysMenuDO.getMenuId().toString());
            tree.setParentId(sysMenuDO.getParentId().toString());
            tree.setText(sysMenuDO.getName());
            trees.add(tree);
        }
        // 默认顶级菜单为０，根据数据库实际情况调整
        Tree<Menu> t = BuildTree.build(trees);
        return t;
    }

    @Override
    public Tree<Menu> getTree(Long id) {
        // 根据roleId查询权限
        List<Long> menuIds = roleMenuMapper.listMenuIdByRoleId(id);
        List<Tree<Menu>> trees = new ArrayList<Tree<Menu>>();
        List<Menu> menuDOs = menuMapper.listMenu();
        for (Menu sysMenuDO : menuDOs) {
            Tree<Menu> tree = new Tree<Menu>();
            tree.setId(sysMenuDO.getMenuId().toString());
            tree.setParentId(sysMenuDO.getParentId().toString());
            tree.setText(sysMenuDO.getName());
            Map<String, Object> state = new HashMap<>();
            Long menuId = sysMenuDO.getMenuId();
            if (menuIds.contains(menuId)) {
                state.put("selected", true);
            } else {
                state.put("selected", false);
            }
            tree.setState(state);
            trees.add(tree);
        }
        // 默认顶级菜单为０，根据数据库实际情况调整
        Tree<Menu> t = BuildTree.build(trees);
        return t;
    }

    @Override
    public List<Menu> list() {
        List<Menu> menus = menuMapper.listMenu();
        return menus;
    }

    @Override
    public List<Menu> lists(MenuPage menuPage) {
        List<Menu> menus = menuMapper.listMenus(menuPage);
        return menus;
    }

    @Override
    public int remove(Long id) {
        int result = menuMapper.remove(id);
        return result;
    }

    @Override
    public int save(Menu menu) {
        int r = menuMapper.insert(menu);
        return r;
    }

    @Override
    public int update(Menu menu) {
        int r = menuMapper.updateByPrimaryKey(menu);
        return r;
    }

    @Override
    public Menu get(Long id) {
        Menu menuDO = menuMapper.getMenu(id);
        return menuDO;
    }

    @Override
    public Set<String> listPerms(Long userId) {
        List<String> perms = menuMapper.listUserPerms(userId);
        Set<String> permsSet = new HashSet<>();
        for (String perm : perms) {
            if (StringUtils.isNotBlank(perm)) {
                permsSet.addAll(Arrays.asList(perm.trim().split(",")));
            }
        }
        return permsSet;
    }
}
