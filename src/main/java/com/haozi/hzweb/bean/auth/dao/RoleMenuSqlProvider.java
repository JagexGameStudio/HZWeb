package com.haozi.hzweb.bean.auth.dao;

import com.haozi.hzweb.bean.auth.entity.RoleMenu;
import org.apache.ibatis.jdbc.SQL;

public class RoleMenuSqlProvider {

    public String insertSelective(RoleMenu record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("sys_role_menu");
        
        if (record.getId() != null) {
            sql.VALUES("id", "#{id,jdbcType=BIGINT}");
        }
        
        if (record.getRoleId() != null) {
            sql.VALUES("role_id", "#{roleId,jdbcType=BIGINT}");
        }
        
        if (record.getMenuId() != null) {
            sql.VALUES("menu_id", "#{menuId,jdbcType=BIGINT}");
        }
        
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(RoleMenu record) {
        SQL sql = new SQL();
        sql.UPDATE("sys_role_menu");
        
        if (record.getRoleId() != null) {
            sql.SET("role_id = #{roleId,jdbcType=BIGINT}");
        }
        
        if (record.getMenuId() != null) {
            sql.SET("menu_id = #{menuId,jdbcType=BIGINT}");
        }
        
        sql.WHERE("id = #{id,jdbcType=BIGINT}");
        
        return sql.toString();
    }
}