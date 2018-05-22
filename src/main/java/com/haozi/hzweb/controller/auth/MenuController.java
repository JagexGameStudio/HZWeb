package com.haozi.hzweb.controller.auth;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.haozi.hzweb.bean.auth.entity.Menu;
import com.haozi.hzweb.bean.auth.entity.page.MenuPage;
import com.haozi.hzweb.bean.auth.entity.page.MyPage;
import com.haozi.hzweb.bean.auth.entity.sys.ReturnMessage;
import com.haozi.hzweb.bean.auth.service.impl.MenuServiceImpl;
import com.haozi.hzweb.controller.BaseController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@Slf4j
@RequestMapping("/sys/menu")
public class MenuController extends BaseController{

    @Autowired
    private MenuServiceImpl menuService;

    @GetMapping
    public String menu(Model model){
        PageHelper.startPage(1,10);
        PageInfo<Menu> list = new PageInfo<>(menuService.list());
        model.addAttribute("list",list);
        model.addAttribute("menuPage",new MenuPage());
        return "auth/menu/menu";
    }

    @PostMapping
    public String menus(Model model, MenuPage menuPage){
        if(menuPage==null){
            menuPage = new MenuPage();
        }
        PageHelper.startPage(menuPage.getPageNum(),menuPage.getPageSize());
        PageInfo<Menu> list = new PageInfo<>(menuService.lists(menuPage));
        model.addAttribute("list",list);
        model.addAttribute("menuPage",menuPage);
        return "auth/menu/menu";
    }

    @GetMapping("/edit/{id}")
    public String edit(HttpServletRequest request,Model model, @PathVariable("id") Long id) {
        model.addAttribute("menu", menuService.get(id));
        if (id == 0) {
            model.addAttribute("pName", "根目录");
        } else {
            model.addAttribute("pName", menuService.get(id).getName());
        }
        return "auth/menu/edit";
    }

    @GetMapping("/add/{pId}")
    public String add(HttpServletRequest request,Model model, @PathVariable("pId") Long pId) {
        model.addAttribute("pId", pId);
        if (pId == 0) {
            model.addAttribute("pName", "根目录");
        } else {
            model.addAttribute("pName", menuService.get(pId).getName());
        }
        return "auth/menu/add";
    }

    @GetMapping("/remove/{id}")
    @ResponseBody
    public ReturnMessage remove( @PathVariable("id")Long id) {
        if (menuService.remove(id) > 0) {
            return ReturnMessage.ok();
        } else {
            return ReturnMessage.error(1, "删除失败");
        }
    }

}
