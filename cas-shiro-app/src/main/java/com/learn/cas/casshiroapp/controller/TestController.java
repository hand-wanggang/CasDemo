/**
 * TestController 2017/9/14 11:32
 * <p>
 * Copyright (C) HAND Enterprise Solutions Company Ltd.
 * All Rights Reserved
 */
package com.learn.cas.casshiroapp.controller;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author gang.wang
 * @Title: TestController
 * @Description: (描述此类的功能)
 * @date 2017/9/14 11:32
 */
@Controller
@RequestMapping("/")
public class TestController {

	@Value("${casLogout}")
	private String logoutUrl;

	@RequestMapping("/msuccess")
	public String success(){
		return "success";
	}

	@RequestMapping("/merror")
	public String error(){
		return "error";
	}

	@RequestMapping("/sec/mresource")
	public String protectResource(){
		return "resource";
	}

	@RequestMapping("/logout")
	public void logout(HttpServletResponse response){
		SecurityUtils.getSubject().logout();
		try {
			response.sendRedirect(logoutUrl);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
