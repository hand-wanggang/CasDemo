/**
 * FilterConfig 2017/9/13 19:59
 * <p>
 * Copyright (C) HAND Enterprise Solutions Company Ltd.
 * All Rights Reserved
 */
package com.learn.cas.casclientapp2.config;

import org.jasig.cas.client.authentication.AuthenticationFilter;
import org.jasig.cas.client.session.SingleSignOutFilter;
import org.jasig.cas.client.session.SingleSignOutHttpSessionListener;
import org.jasig.cas.client.util.AssertionThreadLocalFilter;
import org.jasig.cas.client.util.HttpServletRequestWrapperFilter;
import org.jasig.cas.client.validation.Cas10TicketValidationFilter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author gang.wang
 * @Title: FilterConfig
 * @Description: (描述此类的功能)
 * @date 2017/9/13 19:59
 */
@Configuration
public class FilterConfig {

	@Bean
	public ServletListenerRegistrationBean<SingleSignOutHttpSessionListener> singleSignOutHttpSessionListener() {
		ServletListenerRegistrationBean<SingleSignOutHttpSessionListener> listener = new ServletListenerRegistrationBean<>();
		listener.setEnabled(Boolean.TRUE);
		listener.setListener(new SingleSignOutHttpSessionListener());
		listener.setOrder(1);
		return listener;
	}

	@Bean
	//@ConfigurationProperties(prefix = "filter.AuthenticationFilter")
	public AuthenticationFilter authenticationFilter(){
		return new AuthenticationFilter();
	}

	@Bean
	//@ConfigurationProperties(prefix = "filter.TicketValidationFilter")
	public Cas10TicketValidationFilter ticketValidationFilter(){
		return new Cas10TicketValidationFilter();
	}

	@Bean
	public FilterRegistrationBean singleSignOutFilter(){
		FilterRegistrationBean bean = new FilterRegistrationBean();
		bean.setFilter(new SingleSignOutFilter());
		bean.setName("singleSignOutFilter");
		bean.setOrder(2);
		return bean;
	}


	@Bean
	@ConfigurationProperties(prefix = "filter.AuthenticationFilterBean")
	public FilterRegistrationBean authenticationFilterBean(){
		FilterRegistrationBean bean = new FilterRegistrationBean();
		bean.setFilter(authenticationFilter());
		bean.setName("authenticationFilter");
		bean.addUrlPatterns("/*");
		bean.setOrder(3);
		return bean;
	}

	@Bean
	@ConfigurationProperties(prefix = "filter.FilterRegistrationBean")
	public FilterRegistrationBean ticketValidationFilterBean(){
		FilterRegistrationBean bean = new FilterRegistrationBean();
		bean.setFilter(ticketValidationFilter());
		bean.setName("ticketValidationFilter");
		bean.addUrlPatterns("/*");
		bean.setOrder(4);
		return bean;
	}

	@Bean
	public FilterRegistrationBean httpServletRequestWrapperFilter(){
		FilterRegistrationBean bean = new FilterRegistrationBean();
		bean.setFilter(new HttpServletRequestWrapperFilter());
		bean.setName("httpServletRequestWrapperFilter");
		bean.addUrlPatterns("/*");
		bean.setOrder(5);
		return bean;
	}

	@Bean
	public FilterRegistrationBean assertionThreadLocalFilter(){
		FilterRegistrationBean bean = new FilterRegistrationBean();
		bean.setFilter(new AssertionThreadLocalFilter());
		bean.setName("assertionThreadLocalFilter");
		bean.addUrlPatterns("/*");
		bean.setOrder(6);
		return bean;
	}
}
