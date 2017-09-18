/**
 * LoginController 2017/9/13 20:11
 * <p>
 * Copyright (C) HAND Enterprise Solutions Company Ltd.
 * All Rights Reserved
 */
package com.learn.cas.casclientapp2.controller;

import org.jasig.cas.client.util.AssertionHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @author gang.wang
 * @Title: LoginController
 * @Description: (描述此类的功能)
 * @date 2017/9/13 20:11
 */

@Controller
@RequestMapping("/")
public class LoginController {

	@RequestMapping("/")
	public String index(){
		String userName = AssertionHolder.getAssertion().getPrincipal().getName();
		Map map = AssertionHolder.getAssertion().getAttributes();
		return "index";
	}

	@RequestMapping("/login")
	public String login(){
		String userName = AssertionHolder.getAssertion().getPrincipal().getName();
		Map map = AssertionHolder.getAssertion().getAttributes();
		return "index";
	}

	@RequestMapping("/app1")
	public void redirectToApp2(HttpServletResponse response){
		try {
			response.sendRedirect("http://www.cas.app1:9999/app1");
		}catch (Exception e){
			e.printStackTrace();
		}
	}
}
