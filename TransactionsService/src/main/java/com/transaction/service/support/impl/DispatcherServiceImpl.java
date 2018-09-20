package com.transaction.service.support.impl;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.transaction.service.support.BaseService;
import com.transaction.service.support.DispatcherService;
import com.transaction.service.support.GoalService;
import com.transaction.service.support.config.ServiceBeanConfig;

@Service("com.service.support.DispatcherService")
@Lazy(true)
public class DispatcherServiceImpl implements InitializingBean, ApplicationContextAware, DispatcherService {

	final Logger LOGGER = LoggerFactory.getLogger(DispatcherServiceImpl.class);

	final Map<String, ServiceBeanConfig> serviceBeanConfigs = new ConcurrentHashMap<String, ServiceBeanConfig>();

	private ApplicationContext applicationContext;

	@SuppressWarnings("unchecked")
	@Override
	public <T> T dispatch(Map<String, Object> params) {
		LOGGER.info("serviceBeanConfigs {}",serviceBeanConfigs);
		// 反射
		String methodKey = (String) params.get("serviceId");
		ServiceBeanConfig serviceBeanConfig = serviceBeanConfigs.get(methodKey);
		Method method = serviceBeanConfig.getMethod();
		BaseService baseService = serviceBeanConfig.getService();
		try {
			Object returnObject = method.invoke(baseService, params);
			return (T) returnObject;
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			LOGGER.error("error");
		}

		return null;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		// 获取所有service bean
		Map<String, BaseService> allerviceBeans = this.applicationContext.getBeansOfType(BaseService.class);

		if (Optional.ofNullable(allerviceBeans).isPresent()) {
			// 填充 method, method作为单个服务调用 ->serviceBeanConfigs
			allerviceBeans.values().stream().forEach(service -> {
				Class<?> serviceClass = service.getClass();
				Method[] methods = serviceClass.getMethods();
				
				Arrays.asList(methods).stream().filter(method -> method.isAnnotationPresent(GoalService.class))
						.forEach(method -> {
							GoalService goalService = method.getDeclaredAnnotation(GoalService.class);
							String key = goalService.serviceId();
							ServiceBeanConfig serviceBeanConfig = new ServiceBeanConfig();
							serviceBeanConfig.setMethod(method);
							serviceBeanConfig.setService(service);
							serviceBeanConfigs.put(key, serviceBeanConfig);
						});

			/*	for (Method method : methods) {
					GoalService goalService = method.getDeclaredAnnotation(GoalService.class);
					// 简单处理
					if (Optional.ofNullable(goalService).isPresent()) {
						String key = goalService.serviceId();
						ServiceBeanConfig serviceBeanConfig = new ServiceBeanConfig();
						serviceBeanConfig.setMethod(method);
						serviceBeanConfig.setService(service);
						serviceBeanConfigs.put(key, serviceBeanConfig);
					}
				}*/

			});
		} else {
			LOGGER.info("not found service beans");
			return;
		}
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}

}
