package com.haozi.hzweb.bean.auth.dao;

import com.haozi.hzweb.bean.auth.entity.Menu;
import com.haozi.hzweb.bean.auth.entity.page.MenuPage;
import com.haozi.hzweb.bean.auth.entity.page.MyPage;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface MenuMapper {
    @Delete({
        "delete from sys_menu",
        "where menu_id = #{menuId,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long menuId);

    @Insert({
        "insert into sys_menu (menu_id, parent_id, ",
        "name, url, perms, ",
        "type, icon, order_num, ",
        "gmt_create, gmt_modified)",
        "values (#{menuId,jdbcType=BIGINT}, #{parentId,jdbcType=BIGINT}, ",
        "#{name,jdbcType=VARCHAR}, #{url,jdbcType=VARCHAR}, #{perms,jdbcType=VARCHAR}, ",
        "#{type,jdbcType=INTEGER}, #{icon,jdbcType=VARCHAR}, #{orderNum,jdbcType=INTEGER}, ",
        "#{gmtCreate,jdbcType=TIMESTAMP}, #{gmtModified,jdbcType=TIMESTAMP})"
    })
    int insert(Menu record);

    @InsertProvider(type=MenuSqlProvider.class, method="insertSelective")
    int insertSelective(Menu record);

    @Select({
        "select",
        "menu_id, parent_id, name, url, perms, type, icon, order_num, gmt_create, gmt_modified",
        "from sys_menu",
        "where menu_id = #{menuId,jdbcType=BIGINT}"
    })
    @Results({
        @Result(column="menu_id", property="menuId", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="parent_id", property="parentId", jdbcType=JdbcType.BIGINT),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="url", property="url", jdbcType=JdbcType.VARCHAR),
        @Result(column="perms", property="perms", jdbcType=JdbcType.VARCHAR),
        @Result(column="type", property="type", jdbcType=JdbcType.INTEGER),
        @Result(column="icon", property="icon", jdbcType=JdbcType.VARCHAR),
        @Result(column="order_num", property="orderNum", jdbcType=JdbcType.INTEGER),
        @Result(column="gmt_create", property="gmtCreate", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="gmt_modified", property="gmtModified", jdbcType=JdbcType.TIMESTAMP)
    })
    Menu selectByPrimaryKey(Long menuId);

    @UpdateProvider(type=MenuSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(Menu record);

    @Update({
        "update sys_menu",
        "set parent_id = #{parentId,jdbcType=BIGINT},",
          "name = #{name,jdbcType=VARCHAR},",
          "url = #{url,jdbcType=VARCHAR},",
          "perms = #{perms,jdbcType=VARCHAR},",
          "type = #{type,jdbcType=INTEGER},",
          "icon = #{icon,jdbcType=VARCHAR},",
          "order_num = #{orderNum,jdbcType=INTEGER},",
          "gmt_create = #{gmtCreate,jdbcType=TIMESTAMP},",
          "gmt_modified = #{gmtModified,jdbcType=TIMESTAMP}",
        "where menu_id = #{menuId,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(Menu record);

    @Select("SELECT  menu_id, parent_id, name, url, perms, `type`, icon, order_num, gmt_create, gmt_modified FROM sys_menu where menu_id =#{id}")
    @Results({
            @Result(column="menu_id", property="menuId", jdbcType=JdbcType.BIGINT, id=true),
            @Result(column="parent_id", property="parentId", jdbcType=JdbcType.BIGINT),
            @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
            @Result(column="url", property="url", jdbcType=JdbcType.VARCHAR),
            @Result(column="perms", property="perms", jdbcType=JdbcType.VARCHAR),
            @Result(column="type", property="type", jdbcType=JdbcType.INTEGER),
            @Result(column="icon", property="icon", jdbcType=JdbcType.VARCHAR),
            @Result(column="order_num", property="orderNum", jdbcType=JdbcType.INTEGER),
            @Result(column="gmt_create", property="gmtCreate", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="gmt_modified", property="gmtModified", jdbcType=JdbcType.TIMESTAMP)
    })
    Menu getMenu(Long id);

    @Select("select distinct m.menu_id , parent_id, name, url, perms, `type`, icon, order_num, gmt_create, gmt_modified from sys_menu m left join sys_role_menu rm on m.menu_id = rm.menu_id left join sys_user_role ur on rm.role_id = ur.role_id where ur.user_id = #{id} and m.type in(0,1) order by m.type")
    @Results({
            @Result(column="menu_id", property="menuId", jdbcType=JdbcType.BIGINT, id=true),
            @Result(column="parent_id", property="parentId", jdbcType=JdbcType.BIGINT),
            @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
            @Result(column="url", property="url", jdbcType=JdbcType.VARCHAR),
            @Result(column="perms", property="perms", jdbcType=JdbcType.VARCHAR),
            @Result(column="type", property="type", jdbcType=JdbcType.INTEGER),
            @Result(column="icon", property="icon", jdbcType=JdbcType.VARCHAR),
            @Result(column="order_num", property="orderNum", jdbcType=JdbcType.INTEGER),
            @Result(column="gmt_create", property="gmtCreate", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="gmt_modified", property="gmtModified", jdbcType=JdbcType.TIMESTAMP)
    })
    List<Menu> listMenuByUserId(Long id);

    @Select("select m.perms from sys_menu m left join sys_role_menu rm on m.menu_id = rm.menu_id left join sys_user_role ur on rm.role_id = ur.role_id where ur.user_id = #{id} ")
    @Results({
            @Result(column="perms", property="perms", jdbcType=JdbcType.VARCHAR)
    })
    List<String> listUserPerms(Long id);

    @Select("SELECT menu_id, parent_id, name, url, perms, `type`, icon, order_num, gmt_create, gmt_modified FROM sys_menu order by type")
    @Results({
            @Result(column="menu_id", property="menuId", jdbcType=JdbcType.BIGINT, id=true),
            @Result(column="parent_id", property="parentId", jdbcType=JdbcType.BIGINT),
            @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
            @Result(column="url", property="url", jdbcType=JdbcType.VARCHAR),
            @Result(column="perms", property="perms", jdbcType=JdbcType.VARCHAR),
            @Result(column="type", property="type", jdbcType=JdbcType.INTEGER),
            @Result(column="icon", property="icon", jdbcType=JdbcType.VARCHAR),
            @Result(column="order_num", property="orderNum", jdbcType=JdbcType.INTEGER),
            @Result(column="gmt_create", property="gmtCreate", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="gmt_modified", property="gmtModified", jdbcType=JdbcType.TIMESTAMP)
    })
    List<Menu> listMenu();

    @SelectProvider(type = MenuSqlProvider.class,method = "selectByMenuPage")
    @Results({
            @Result(column="menu_id", property="menuId", jdbcType=JdbcType.BIGINT, id=true),
            @Result(column="parent_id", property="parentId", jdbcType=JdbcType.BIGINT),
            @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
            @Result(column="url", property="url", jdbcType=JdbcType.VARCHAR),
            @Result(column="perms", property="perms", jdbcType=JdbcType.VARCHAR),
            @Result(column="type", property="type", jdbcType=JdbcType.INTEGER),
            @Result(column="icon", property="icon", jdbcType=JdbcType.VARCHAR),
            @Result(column="order_num", property="orderNum", jdbcType=JdbcType.INTEGER),
            @Result(column="gmt_create", property="gmtCreate", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="gmt_modified", property="gmtModified", jdbcType=JdbcType.TIMESTAMP)
    })
    List<Menu> listMenus(MenuPage menuPage);

    @Delete("delete from sys_menu where menu_id = #{id}")
    int remove(Long id);

}