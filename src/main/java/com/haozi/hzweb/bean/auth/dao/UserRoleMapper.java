package com.haozi.hzweb.bean.auth.dao;

import com.haozi.hzweb.bean.auth.entity.UserRole;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface UserRoleMapper {
    @Delete({
        "delete from sys_user_role",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long id);

    @Delete("DELETE FROM  sys_user_role WHERE user_id=#{id}")
    int removeByUserId(Long id);

    @Insert({
        "insert into sys_user_role (id, user_id, ",
        "role_id)",
        "values (#{id,jdbcType=BIGINT}, #{userId,jdbcType=BIGINT}, ",
        "#{roleId,jdbcType=BIGINT})"
    })
    int insert(UserRole record);

    @InsertProvider(type=UserRoleSqlProvider.class, method="insertSelective")
    int insertSelective(UserRole record);

    @Select({
        "select",
        "id, user_id, role_id",
        "from sys_user_role",
        "where id = #{id,jdbcType=BIGINT}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="user_id", property="userId", jdbcType=JdbcType.BIGINT),
        @Result(column="role_id", property="roleId", jdbcType=JdbcType.BIGINT)
    })
    UserRole selectByPrimaryKey(Long id);

    @UpdateProvider(type=UserRoleSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(UserRole record);

    @Update({
        "update sys_user_role",
        "set user_id = #{userId,jdbcType=BIGINT},",
          "role_id = #{roleId,jdbcType=BIGINT}",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(UserRole record);

    @Select("select role_id from sys_user_role where user_id=#{userId}")
    List<Long> listRoleId(Long userId);

    @Insert("<script>" +
            "INSERT INTO sys_user_role(user_id, role_id) values"+
            " <foreach collection=\"list\" item=\"item\" index=\"index\" separator=\",\" >  " +
            " (#{item.userId},#{item.roleId})" +
            " </foreach>"+
            "</script>")
    int batchSave(List<UserRole> list);

    @Delete("<script>" + "delete from sys_user_role where user_id in"
            + " <foreach collection=\"list\" index=\"i\" open=\"(\" separator=\",\" close=\")\" item=\"item\"  >#{item}</foreach>"
            + "</script>")
    int batchRemoveByUserId(List<Long> list);
}