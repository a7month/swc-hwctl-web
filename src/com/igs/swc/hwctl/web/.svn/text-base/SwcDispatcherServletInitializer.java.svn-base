package com.igs.swc.hwctl.web;

import javax.servlet.Filter;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.XmlWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.servlet.support.AbstractDispatcherServletInitializer;

public class SwcDispatcherServletInitializer extends
		AbstractDispatcherServletInitializer {

	@Override
	protected String getServletName() {
		return "swc";
	}

	@Override
	protected Filter[] getServletFilters() {
		return new Filter[] { new CharacterEncodingFilter(),
				new HiddenHttpMethodFilter() };
	}

	@Override
	protected WebApplicationContext createServletApplicationContext() {
		XmlWebApplicationContext ctx = new XmlWebApplicationContext();
		ctx.setConfigLocation("/WEB-INF/conf/swc/swc-config.xml");
		return ctx;
	}

	@Override
	protected String[] getServletMappings() {
		return new String[] { "/" };
	}

	@Override
	protected WebApplicationContext createRootApplicationContext() {
		XmlWebApplicationContext ctx = new XmlWebApplicationContext();
		ctx.setConfigLocation("/WEB-INF/conf/main/app-config.xml");
		return ctx;
	}
}
