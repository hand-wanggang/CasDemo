/**
 * CasConfig 2017/9/14 10:52
 * <p>
 * Copyright (C) HAND Enterprise Solutions Company Ltd.
 * All Rights Reserved
 */
package com.learn.cas.casshiroapp.config;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.cas.CasFilter;
import org.apache.shiro.cas.CasRealm;
import org.apache.shiro.cas.CasSubjectFactory;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author gang.wang
 * @Title: CasConfig
 * @Description: (描述此类的功能)
 * @date 2017/9/14 10:52
 */
@Configuration
public class CasConfig {

	@Bean
	@ConfigurationProperties(prefix = "shiro.shiroFilter")
	public ShiroFilterFactoryBean shiroFilter(){
		ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
		bean.setSecurityManager(securityManager());
		bean.getFilters().put("casFilter",casFilter());
		return bean;
	}

	@Bean
	public DefaultWebSecurityManager securityManager(){
		DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
		securityManager.setRealm(casRealm());
		securityManager.setSubjectFactory(casSubjectFactory());
		SecurityUtils.setSecurityManager(securityManager);
		return securityManager;
	}

	@Bean
	@ConfigurationProperties(prefix = "shiro.casFilter")
	public CasFilter casFilter(){
		return new CasFilter();
	}

	@Bean
	@ConfigurationProperties(prefix = "shiro.CasRealm")
	public CasRealm casRealm(){
		return new CasRealm();
	}

	@Bean
	public CasSubjectFactory casSubjectFactory(){
		return new CasSubjectFactory();
	}

	@Bean
	public LifecycleBeanPostProcessor lifecycleBeanPostProcessor(){
		return new LifecycleBeanPostProcessor();
	}

}
