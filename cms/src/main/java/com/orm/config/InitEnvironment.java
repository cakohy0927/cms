package com.orm.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.orm.commons.spring.SpringContextHolder;

public class InitEnvironment {
	private final static Logger log = LoggerFactory.getLogger(InitEnvironment.class);
	private String outsideOfficeHoursPage;// outsideOfficeHoursPage属性指定错误提示页面的URL
	private String ignoreResources;// 需要忽略的文件或者路径
	private String errorPage;// 前台错误页面
	private String cmsIndex;// 前台首页
	private String initUsername;
	private String initPassword;
	private static InitEnvironment environment  = null;

	public void init() {
		log.info("环境正在初始化");
		if (environment == null) {
			environment = SpringContextHolder.getBean(InitEnvironment.class);
		}
	}
	
	public static InitEnvironment getInitEnvironmentInstance() {
		return getInitEnvironment();
	}

	private static InitEnvironment getInitEnvironment() {
		
		return environment;
	}

	public String getOutsideOfficeHoursPage() {
		return outsideOfficeHoursPage;
	}

	public void setOutsideOfficeHoursPage(String outsideOfficeHoursPage) {
		this.outsideOfficeHoursPage = outsideOfficeHoursPage;
	}

	public String getIgnoreResources() {
		return ignoreResources;
	}

	public void setIgnoreResources(String ignoreResources) {
		this.ignoreResources = ignoreResources;
	}

	public String getErrorPage() {
		return errorPage;
	}

	public void setErrorPage(String errorPage) {
		this.errorPage = errorPage;
	}

	public String getCmsIndex() {
		return cmsIndex;
	}

	public void setCmsIndex(String cmsIndex) {
		this.cmsIndex = cmsIndex;
	}
	public String getInitPassword() {
		return initPassword;
	}
	
	public String getInitUsername() {
		return initUsername;
	}
	
	public void setInitPassword(String initPassword) {
		this.initPassword = initPassword;
	}
	
	public void setInitUsername(String initUsername) {
		this.initUsername = initUsername;
	}

	@Override
	public String toString() {
		return "InitEnvironment [outsideOfficeHoursPage="
				+ outsideOfficeHoursPage + ", ignoreResources="
				+ ignoreResources + ", errorPage=" + errorPage + ", cmsIndex="
				+ cmsIndex + ", initUsername=" + initUsername
				+ ", initPassword=" + initPassword + "]";
	}
}
