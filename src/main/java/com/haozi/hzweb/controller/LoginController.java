package com.haozi.hzweb.controller;


import com.haozi.hzweb.bean.auth.entity.Menu;
import com.haozi.hzweb.bean.auth.service.impl.MenuServiceImpl;
import com.haozi.hzweb.bean.auth.tools.Tree;
import com.haozi.hzweb.bean.tools.md5.MD5Tools;
import com.haozi.hzweb.bean.tools.shiro.ShiroUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.apache.shiro.subject.Subject;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/")
@Slf4j
public class LoginController extends BaseController{

    @Autowired
    private MenuServiceImpl menuService;

    @GetMapping("/")
    public String welcome(){
        return "admin/welcome";
    }

    @GetMapping("/index")
    public String index(Model model){
        List<Tree<Menu>> menus = menuService.listMenuTree(getUserId());
        model.addAttribute("username",getUser().getName());
        model.addAttribute("menus",menus);
        return "admin/index/index";
    }

    @GetMapping("/default")
    @ResponseBody
    public String defaults(Model model){
        return "ok";
    }

    @GetMapping("/login")
    public String login(){
        return "admin/welcome";
    }

    @PostMapping("/login")
    public String dologin(String username,String password){
        password = MD5Tools.encrypt(username,password);
        UsernamePasswordToken token = new UsernamePasswordToken(username,password);
        Subject subject = SecurityUtils.getSubject();
        try{
            subject.login(token);
            return "redirect:/index";
        }catch (AuthenticationException e){
            log.info("登陆失败",e);
            return "redirect:/login";
        }
    }

    @GetMapping("/logout")
    String logout(HttpServletRequest request) {
        ShiroUtils.logout();
        return "redirect:/login";
    }

}
