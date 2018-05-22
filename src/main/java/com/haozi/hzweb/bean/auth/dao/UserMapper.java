package com.haozi.hzweb.bean.auth.dao;

import com.haozi.hzweb.bean.auth.entity.User;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public interface UserMapper {
    @Delete({
        "delete from sys_user",
        "where user_id = #{userId,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long userId);

    @Insert({
        "insert into sys_user (user_id, username, ",
        "password, email, ",
        "mobile, status, ",
        "user_id_create, gmt_create, ",
        "gmt_modified, name)",
        "values (#{userId,jdbcType=BIGINT}, #{username,jdbcType=VARCHAR}, ",
        "#{password,jdbcType=VARCHAR}, #{email,jdbcType=VARCHAR}, ",
        "#{mobile,jdbcType=VARCHAR}, #{status,jdbcType=TINYINT}, ",
        "#{userIdCreate,jdbcType=BIGINT}, #{gmtCreate,jdbcType=TIMESTAMP}, ",
        "#{gmtModified,jdbcType=TIMESTAMP}, #{name,jdbcType=VARCHAR})"
    })
    int insert(User record);

    @InsertProvider(type=UserSqlProvider.class, method="insertSelective")
    int insertSelective(User record);

    @Select({
        "select",
        "user_id, username, password, email, mobile, status, user_id_create, gmt_create, ",
        "gmt_modified, name",
        "from sys_user",
        "where user_id = #{userId,jdbcType=BIGINT}"
    })
    @Results({
        @Result(column="user_id", property="userId", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="username", property="username", jdbcType=JdbcType.VARCHAR),
        @Result(column="password", property="password", jdbcType=JdbcType.VARCHAR),
        @Result(column="email", property="email", jdbcType=JdbcType.VARCHAR),
        @Result(column="mobile", property="mobile", jdbcType=JdbcType.VARCHAR),
        @Result(column="status", property="status", jdbcType=JdbcType.TINYINT),
        @Result(column="user_id_create", property="userIdCreate", jdbcType=JdbcType.BIGINT),
        @Result(column="gmt_create", property="gmtCreate", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="gmt_modified", property="gmtModified", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR)
    })
    User selectByPrimaryKey(Long userId);

    @Select({
            "select",
            "user_id, username, password, email, mobile, status, user_id_create, gmt_create, ",
            "gmt_modified, name",
            "from sys_user",
            "where username = #{username,jdbcType=VARCHAR}"
    })
    @Results({
            @Result(column="user_id", property="userId", jdbcType=JdbcType.BIGINT, id=true),
            @Result(column="username", property="username", jdbcType=JdbcType.VARCHAR),
            @Result(column="password", property="password", jdbcType=JdbcType.VARCHAR),
            @Result(column="email", property="email", jdbcType=JdbcType.VARCHAR),
            @Result(column="mobile", property="mobile", jdbcType=JdbcType.VARCHAR),
            @Result(column="status", property="status", jdbcType=JdbcType.TINYINT),
            @Result(column="user_id_create", property="userIdCreate", jdbcType=JdbcType.BIGINT),
            @Result(column="gmt_create", property="gmtCreate", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="gmt_modified", property="gmtModified", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR)
    })
    User selectByUsername(String username);

    @UpdateProvider(type=UserSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(User record);

    @Update("<script>"+
            "update sys_user " +
            "<set>" +
            "<if test=\"userId != null\">`user_id` = #{userId}, </if>" +
            "<if test=\"username != null\">`username` = #{username}, </if>" +
            "<if test=\"password != null\">`password` = #{password}, </if>" +
            "<if test=\"email != null\">`email` = #{email}, </if>" +
            "<if test=\"mobile != null\">`mobile` = #{mobile}, </if>" +
            "<if test=\"status != null\">`status` = #{status}, </if>" +
            "<if test=\"userIdCreate != null\">`user_id_create` = #{userIdCreate}, </if>" +
            "<if test=\"gmtCreate != null\">`gmt_create` = #{gmtCreate}, </if>" +
            "<if test=\"gmtModified != null\">`gmt_modified` = #{gmtModified}, </if>" +
            "<if test=\"name != null\">`name` = #{name}, </if>" +
            "</set>" +
            "where user_id = #{userId}"+
            "</script>")
    int updateByPrimaryKey(User record);

    @Select("<script>" +
            "select * from sys_user " +
            "<where>" +
            "<if test=\"userId != null and userId != ''\">"+ "and user_id = #{userId} " + "</if>" +
            "<if test=\"username != null and username != ''\">"+ "and username = #{username} " + "</if>" +
            "<if test=\"password != null and password != ''\">"+ "and password = #{password} " + "</if>" +
            "<if test=\"email != null and email != ''\">"+ "and email = #{email} " + "</if>" +
            "<if test=\"mobile != null and mobile != ''\">"+ "and mobile = #{mobile} " + "</if>" +
            "<if test=\"status != null and status != ''\">"+ "and status = #{status} " + "</if>" +
            "<if test=\"userIdCreate != null and userIdCreate != ''\">"+ "and user_id_create = #{userIdCreate} " + "</if>" +
            "<if test=\"gmtCreate != null and gmtCreate != ''\">"+ "and gmt_create = #{gmtCreate} " + "</if>" +
            "<if test=\"gmtModified != null and gmtModified != ''\">"+ "and gmt_modified = #{gmtModified} " + "</if>" +
            "<if test=\"name != null and name != ''\">"+ "and name = #{name} " + "</if>" +
            "</where>"+
            "<if test=\"offset != null and limit != null\">"+
            "limit #{offset}, #{limit}" +
            "</if>"+
            "</script>")
    List<User> list(Map<String, Object> param);

    @Select({
            "select",
            "user_id, username, password, email, mobile, status, user_id_create, gmt_create, ",
            "gmt_modified, name",
            "from sys_user"
    })
    @Results({
            @Result(column="user_id", property="userId", jdbcType=JdbcType.BIGINT, id=true),
            @Result(column="username", property="username", jdbcType=JdbcType.VARCHAR),
            @Result(column="password", property="password", jdbcType=JdbcType.VARCHAR),
            @Result(column="email", property="email", jdbcType=JdbcType.VARCHAR),
            @Result(column="mobile", property="mobile", jdbcType=JdbcType.VARCHAR),
            @Result(column="status", property="status", jdbcType=JdbcType.TINYINT),
            @Result(column="user_id_create", property="userIdCreate", jdbcType=JdbcType.BIGINT),
            @Result(column="gmt_create", property="gmtCreate", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="gmt_modified", property="gmtModified", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR)
    })
    List<User> getlist();

    @Select("<script>" +
            "select count(*) from sys_user " +
            "<where>" +
            "<if test=\"userId != null and userId != ''\">"+ "and user_id = #{userId} " + "</if>" +
            "<if test=\"username != null and username != ''\">"+ "and username = #{username} " + "</if>" +
            "<if test=\"password != null and password != ''\">"+ "and password = #{password} " + "</if>" +
            "<if test=\"email != null and email != ''\">"+ "and email = #{email} " + "</if>" +
            "<if test=\"mobile != null and mobile != ''\">"+ "and mobile = #{mobile} " + "</if>" +
            "<if test=\"status != null and status != ''\">"+ "and status = #{status} " + "</if>" +
            "<if test=\"userIdCreate != null and userIdCreate != ''\">"+ "and user_id_create = #{userIdCreate} " + "</if>" +
            "<if test=\"gmtCreate != null and gmtCreate != ''\">"+ "and gmt_create = #{gmtCreate} " + "</if>" +
            "<if test=\"gmtModified != null and gmtModified != ''\">"+ "and gmt_modified = #{gmtModified} " + "</if>" +
            "<if test=\"name != null and name != ''\">"+ "and name = #{name} " + "</if>" +
            "</where>"+
            "</script>")
    int count(Map<String, Object> map);

    @Delete("<script>"+
            "delete from sys_user where user_id in "+
            "<foreach collection=\"list\" index=\"i\" open=\"(\" separator=\",\" close=\")\" item=\"item\"  >#{item}</foreach>"+
            "</script>")
    int batchRemove(List<Long> list);
}