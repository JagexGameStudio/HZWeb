package com.haozi.hzweb.bean.auth.dao;

import com.haozi.hzweb.bean.auth.entity.UserRole;
import org.apache.ibatis.jdbc.SQL;

public class UserRoleSqlProvider {

    public String insertSelective(UserRole record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("sys_user_role");
        
        if (record.getId() != null) {
            sql.VALUES("id", "#{id,jdbcType=BIGINT}");
        }
        
        if (record.getUserId() != null) {
            sql.VALUES("user_id", "#{userId,jdbcType=BIGINT}");
        }
        
        if (record.getRoleId() != null) {
            sql.VALUES("role_id", "#{roleId,jdbcType=BIGINT}");
        }
        
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(UserRole record) {
        SQL sql = new SQL();
        sql.UPDATE("sys_user_role");
        
        if (record.getUserId() != null) {
            sql.SET("user_id = #{userId,jdbcType=BIGINT}");
        }
        
        if (record.getRoleId() != null) {
            sql.SET("role_id = #{roleId,jdbcType=BIGINT}");
        }
        
        sql.WHERE("id = #{id,jdbcType=BIGINT}");
        
        return sql.toString();
    }
}