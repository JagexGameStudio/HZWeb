package com.haozi.hzweb.bean.auth.dao;

import com.haozi.hzweb.bean.auth.entity.Log;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface LogMapper {
    @Delete({
        "delete from sys_log",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long id);

    @Insert({
        "insert into sys_log (id, user_id, ",
        "username, operation, ",
        "time, method, params, ",
        "ip, gmt_create)",
        "values (#{id,jdbcType=BIGINT}, #{userId,jdbcType=BIGINT}, ",
        "#{username,jdbcType=VARCHAR}, #{operation,jdbcType=VARCHAR}, ",
        "#{time,jdbcType=INTEGER}, #{method,jdbcType=VARCHAR}, #{params,jdbcType=VARCHAR}, ",
        "#{ip,jdbcType=VARCHAR}, #{gmtCreate,jdbcType=TIMESTAMP})"
    })
    int insert(Log record);

    @InsertProvider(type=LogSqlProvider.class, method="insertSelective")
    int insertSelective(Log record);

    @Select({
        "select",
        "id, user_id, username, operation, time, method, params, ip, gmt_create",
        "from sys_log",
        "where id = #{id,jdbcType=BIGINT}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="user_id", property="userId", jdbcType=JdbcType.BIGINT),
        @Result(column="username", property="username", jdbcType=JdbcType.VARCHAR),
        @Result(column="operation", property="operation", jdbcType=JdbcType.VARCHAR),
        @Result(column="time", property="time", jdbcType=JdbcType.INTEGER),
        @Result(column="method", property="method", jdbcType=JdbcType.VARCHAR),
        @Result(column="params", property="params", jdbcType=JdbcType.VARCHAR),
        @Result(column="ip", property="ip", jdbcType=JdbcType.VARCHAR),
        @Result(column="gmt_create", property="gmtCreate", jdbcType=JdbcType.TIMESTAMP)
    })
    Log selectByPrimaryKey(Long id);

    @UpdateProvider(type=LogSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(Log record);

    @Update({
        "update sys_log",
        "set user_id = #{userId,jdbcType=BIGINT},",
          "username = #{username,jdbcType=VARCHAR},",
          "operation = #{operation,jdbcType=VARCHAR},",
          "time = #{time,jdbcType=INTEGER},",
          "method = #{method,jdbcType=VARCHAR},",
          "params = #{params,jdbcType=VARCHAR},",
          "ip = #{ip,jdbcType=VARCHAR},",
          "gmt_create = #{gmtCreate,jdbcType=TIMESTAMP}",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(Log record);

    @Select({"select * from sys_log"})
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
            @Result(column="user_id", property="userId", jdbcType=JdbcType.BIGINT),
            @Result(column="username", property="username", jdbcType=JdbcType.VARCHAR),
            @Result(column="operation", property="operation", jdbcType=JdbcType.VARCHAR),
            @Result(column="time", property="time", jdbcType=JdbcType.INTEGER),
            @Result(column="method", property="method", jdbcType=JdbcType.VARCHAR),
            @Result(column="params", property="params", jdbcType=JdbcType.VARCHAR),
            @Result(column="ip", property="ip", jdbcType=JdbcType.VARCHAR),
            @Result(column="gmt_create", property="gmtCreate", jdbcType=JdbcType.TIMESTAMP)
    })
    List<Log> list();
}