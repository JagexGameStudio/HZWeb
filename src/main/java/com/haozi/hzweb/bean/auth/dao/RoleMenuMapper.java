package com.haozi.hzweb.bean.auth.dao;

import com.haozi.hzweb.bean.auth.entity.RoleMenu;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface RoleMenuMapper {
    @Delete({
        "delete from sys_role_menu",
        "where role_id = #{id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long id);

    @Insert({
        "insert into sys_role_menu (id, role_id, ",
        "menu_id)",
        "values (#{id,jdbcType=BIGINT}, #{roleId,jdbcType=BIGINT}, ",
        "#{menuId,jdbcType=BIGINT})"
    })
    int insert(RoleMenu record);

    @InsertProvider(type=RoleMenuSqlProvider.class, method="insertSelective")
    int insertSelective(RoleMenu record);

    @Select({
        "select",
        "id, role_id, menu_id",
        "from sys_role_menu",
        "where id = #{id,jdbcType=BIGINT}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="role_id", property="roleId", jdbcType=JdbcType.BIGINT),
        @Result(column="menu_id", property="menuId", jdbcType=JdbcType.BIGINT)
    })
    RoleMenu selectByPrimaryKey(Long id);

    @UpdateProvider(type=RoleMenuSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(RoleMenu record);

    @Update({
        "update sys_role_menu",
        "set role_id = #{roleId,jdbcType=BIGINT},",
          "menu_id = #{menuId,jdbcType=BIGINT}",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(RoleMenu record);

    @Insert("INSERT INTO sys_role_menu" + "(id, role_id, menu_id)" + "VALUES(#{id}, #{roleId}, #{menuId})")
    int save(RoleMenu rm);

    @Insert("<script>" +
            "INSERT INTO sys_role_menu(role_id, menu_id) values"+
            " <foreach collection=\"list\" item=\"item\" index=\"index\" separator=\",\" >  " +
            " (#{item.roleId},#{item.menuId})" +
            " </foreach>  "+
            "</script>")
    int batchSave(List<RoleMenu> list);

    @Delete("DELETE FROM  sys_role_menu WHERE id=#{id}")
    int remove(Long id);

    @Delete("DELETE FROM  sys_role_menu WHERE role_id=#{roleId}")
    int removeByRoleId(Long roleId);


    @Select("select menu_id from sys_role_menu where role_id = #{roleId}")
    List<Long> listMenuIdByRoleId(Long roleId);
}