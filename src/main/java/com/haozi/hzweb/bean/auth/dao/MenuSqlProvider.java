package com.haozi.hzweb.bean.auth.dao;

import com.haozi.hzweb.bean.auth.entity.Menu;
import com.haozi.hzweb.bean.auth.entity.page.MenuPage;
import com.haozi.hzweb.bean.auth.entity.page.MyPage;
import org.apache.ibatis.jdbc.SQL;

public class MenuSqlProvider {

    public String insertSelective(Menu record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("sys_menu");
        
        if (record.getMenuId() != null) {
            sql.VALUES("menu_id", "#{menuId,jdbcType=BIGINT}");
        }
        
        if (record.getParentId() != null) {
            sql.VALUES("parent_id", "#{parentId,jdbcType=BIGINT}");
        }
        
        if (record.getName() != null) {
            sql.VALUES("name", "#{name,jdbcType=VARCHAR}");
        }
        
        if (record.getUrl() != null) {
            sql.VALUES("url", "#{url,jdbcType=VARCHAR}");
        }
        
        if (record.getPerms() != null) {
            sql.VALUES("perms", "#{perms,jdbcType=VARCHAR}");
        }
        
        if (record.getType() != null) {
            sql.VALUES("type", "#{type,jdbcType=INTEGER}");
        }
        
        if (record.getIcon() != null) {
            sql.VALUES("icon", "#{icon,jdbcType=VARCHAR}");
        }
        
        if (record.getOrderNum() != null) {
            sql.VALUES("order_num", "#{orderNum,jdbcType=INTEGER}");
        }
        
        if (record.getGmtCreate() != null) {
            sql.VALUES("gmt_create", "#{gmtCreate,jdbcType=TIMESTAMP}");
        }
        
        if (record.getGmtModified() != null) {
            sql.VALUES("gmt_modified", "#{gmtModified,jdbcType=TIMESTAMP}");
        }
        
        return sql.toString();
    }

    public String selectByMenuPage(MenuPage menuPage){
        SQL sql = new SQL();
        sql.SELECT("*").FROM("sys_menu");
        if(menuPage.getParentId()!=-1){
            sql.WHERE("parent_id=#{parentId,jdbcType=INTEGER}");
        }
        if(menuPage.getType()!=-1){
            sql.WHERE("type=#{type,jdbcType=INTEGER}");
        }
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(Menu record) {
        SQL sql = new SQL();
        sql.UPDATE("sys_menu");
        
        if (record.getParentId() != null) {
            sql.SET("parent_id = #{parentId,jdbcType=BIGINT}");
        }
        
        if (record.getName() != null) {
            sql.SET("name = #{name,jdbcType=VARCHAR}");
        }
        
        if (record.getUrl() != null) {
            sql.SET("url = #{url,jdbcType=VARCHAR}");
        }
        
        if (record.getPerms() != null) {
            sql.SET("perms = #{perms,jdbcType=VARCHAR}");
        }
        
        if (record.getType() != null) {
            sql.SET("type = #{type,jdbcType=INTEGER}");
        }
        
        if (record.getIcon() != null) {
            sql.SET("icon = #{icon,jdbcType=VARCHAR}");
        }
        
        if (record.getOrderNum() != null) {
            sql.SET("order_num = #{orderNum,jdbcType=INTEGER}");
        }
        
        if (record.getGmtCreate() != null) {
            sql.SET("gmt_create = #{gmtCreate,jdbcType=TIMESTAMP}");
        }
        
        if (record.getGmtModified() != null) {
            sql.SET("gmt_modified = #{gmtModified,jdbcType=TIMESTAMP}");
        }
        
        sql.WHERE("menu_id = #{menuId,jdbcType=BIGINT}");
        
        return sql.toString();
    }
}