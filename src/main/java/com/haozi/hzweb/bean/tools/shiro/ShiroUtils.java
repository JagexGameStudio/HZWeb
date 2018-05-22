package com.haozi.hzweb.bean.tools.shiro;

import com.haozi.hzweb.bean.auth.entity.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

public class ShiroUtils {

    public static Subject getSubjct(){
        return SecurityUtils.getSubject();
    }

    public static User getUser(){
        return (User)getSubjct().getPrincipal();
    }

    public static Long getUserId(){
        return getUser().getUserId();
    }

    public static void logout(){
        getSubjct().logout();
    }
}
