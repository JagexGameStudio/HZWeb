package com.haozi.hzweb.bean.auth.dao;

import com.haozi.hzweb.bean.auth.entity.Role;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface RoleMapper {
    @Delete({
        "delete from sys_role",
        "where role_id = #{roleId,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long roleId);

    @Insert({
        "insert into sys_role (role_id, role_name, ",
        "role_sign, remark, ",
        "user_id_create, gmt_create, ",
        "gmt_modified)",
        "values (#{roleId,jdbcType=BIGINT}, #{roleName,jdbcType=VARCHAR}, ",
        "#{roleSign,jdbcType=VARCHAR}, #{remark,jdbcType=VARCHAR}, ",
        "#{userIdCreate,jdbcType=BIGINT}, #{gmtCreate,jdbcType=TIMESTAMP}, ",
        "#{gmtModified,jdbcType=TIMESTAMP})"
    })
    int insert(Role record);

    @InsertProvider(type=RoleSqlProvider.class, method="insertSelective")
    int insertSelective(Role record);

    @Select({
        "select",
        "role_id, role_name, role_sign, remark, user_id_create, gmt_create, gmt_modified",
        "from sys_role",
        "where role_id = #{roleId,jdbcType=BIGINT}"
    })
    @Results({
        @Result(column="role_id", property="roleId", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="role_name", property="roleName", jdbcType=JdbcType.VARCHAR),
        @Result(column="role_sign", property="roleSign", jdbcType=JdbcType.VARCHAR),
        @Result(column="remark", property="remark", jdbcType=JdbcType.VARCHAR),
        @Result(column="user_id_create", property="userIdCreate", jdbcType=JdbcType.BIGINT),
        @Result(column="gmt_create", property="gmtCreate", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="gmt_modified", property="gmtModified", jdbcType=JdbcType.TIMESTAMP)
    })
    Role selectByPrimaryKey(Long roleId);

    @UpdateProvider(type=RoleSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(Role record);

    @Update({
        "update sys_role",
        "set role_name = #{roleName,jdbcType=VARCHAR},",
          "role_sign = #{roleSign,jdbcType=VARCHAR},",
          "remark = #{remark,jdbcType=VARCHAR},",
          "user_id_create = #{userIdCreate,jdbcType=BIGINT},",
          "gmt_create = #{gmtCreate,jdbcType=TIMESTAMP},",
          "gmt_modified = #{gmtModified,jdbcType=TIMESTAMP}",
        "where role_id = #{roleId,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(Role record);

    @Select("SELECT role_id, role_name, role_sign, remark, user_id_create, gmt_create, gmt_modified FROM sys_role")
    @Results({
            @Result(column="role_id", property="roleId", jdbcType=JdbcType.BIGINT, id=true),
            @Result(column="role_name", property="roleName", jdbcType=JdbcType.VARCHAR),
            @Result(column="role_sign", property="roleSign", jdbcType=JdbcType.VARCHAR),
            @Result(column="remark", property="remark", jdbcType=JdbcType.VARCHAR),
            @Result(column="user_id_create", property="userIdCreate", jdbcType=JdbcType.BIGINT),
            @Result(column="gmt_create", property="gmtCreate", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="gmt_modified", property="gmtModified", jdbcType=JdbcType.TIMESTAMP)
    })
    List<Role> listRole();
}