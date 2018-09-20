package com.transaction.service.support.config;

import java.lang.reflect.Method;

import com.transaction.service.support.BaseService;

public class ServiceBeanConfig {

	/**
	 * 服务实例
	 */
	private BaseService service;

	/**
	 * 服务调用方法
	 */
	private Method method;

	public BaseService getService() {
		return service;
	}

	public void setService(BaseService service) {
		this.service = service;
	}

	public Method getMethod() {
		return method;
	}

	public void setMethod(Method method) {
		this.method = method;
	}

}
