package com.haozi.hzweb.controller;

import com.haozi.hzweb.bean.auth.entity.User;
import com.haozi.hzweb.bean.tools.shiro.ShiroUtils;
import org.springframework.stereotype.Controller;

@Controller
public class BaseController {

	public User getUser() {
		return ShiroUtils.getUser();
	}

	public Long getUserId() {
		return getUser().getUserId();
	}

	public String getUsername() {
		return getUser().getUsername();
	}
}