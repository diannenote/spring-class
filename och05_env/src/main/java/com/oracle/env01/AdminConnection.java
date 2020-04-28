package com.oracle.env01;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;

public class AdminConnection implements EnvironmentAware, InitializingBean, DisposableBean {

	private Environment env;
	private String adminId;
	private String adminPw;
	
	// 외부에서 환경파일 연계 1
	@Override
	public void setEnvironment(Environment env) {
		System.out.println("setEnvironment()");
		setEnv(env);
	}
	// 2
	@Override
	public void afterPropertiesSet() throws Exception {
		System.out.println("afterPropertiesSet()");
		System.out.println("env.getProperty(admin.id)->"+env.getProperty("admin.id"));
		System.out.println("afterPropertiesSet()");
		setAdminId(env.getProperty("admin.id"));
		setAdminPw(env.getProperty("admin.pw"));
	}

	@Override
	public void destroy() throws Exception {
		System.out.println("destroy()");
	}
	
	public void setEnv(Environment env) {
		this.env = env;
	}
	
	public void setAdminId(String adminId) {
		System.out.println("setAdminId()");
		this.adminId = adminId;
	}
	
	public void setAdminPw(String adminPw) {
		System.out.println("setAdminPw()");
		this.adminPw = adminPw;
	}
	
	public String getAdminId() {
		return adminId;
	}
	
	public String getAdminPw() {
		return adminPw;
	}
	


}
