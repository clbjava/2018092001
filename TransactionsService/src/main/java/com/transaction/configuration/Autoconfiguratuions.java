package com.transaction.configuration;

import java.util.Locale;
import java.util.concurrent.ThreadPoolExecutor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
@ComponentScan
//@EnableTransactionManagement
@EnableScheduling
@EnableAsync
@EnableCaching
public class Autoconfiguratuions {
		
	@Value("${corePoolSize:20}")
	private int corePoolSize;
	
	@Value("${maxPoolSize:40}")
	private int maxPoolSize;
	
	@Value("${queueCapacity:50}")
	private int queueCapacity;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(Autoconfiguratuions.class);

	@Bean(name="asyncServiceExecutor")
	public ThreadPoolTaskExecutor asyncServiceExecutor() {
		LOGGER.info("create asyncServiceExecutor");
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		// 配置核心线程数
		executor.setCorePoolSize(corePoolSize);
		// 配置最大线程数
		executor.setMaxPoolSize(maxPoolSize);
		// 配置队列大小
		executor.setQueueCapacity(queueCapacity);
		// 配置线程池中的线程的名称前缀
		executor.setThreadNamePrefix("asyncServiceExecutor-");
		executor.setRejectedExecutionHandler(new ThreadPoolExecutor.AbortPolicy());
		// 执行初始化
		executor.initialize();
		return executor;
	}

	@Bean(name="syncServiceExecutor")
	public ThreadPoolTaskExecutor syncServiceExecutor() {
		LOGGER.info("create syncServiceExecutor");
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		// 配置核心线程数
		executor.setCorePoolSize(corePoolSize);
		// 配置最大线程数
		executor.setMaxPoolSize(maxPoolSize);
		// 配置队列大小
		executor.setQueueCapacity(queueCapacity);
		// 配置线程池中的线程的名称前缀
		executor.setThreadNamePrefix("syncServiceExecutor-");
		executor.setRejectedExecutionHandler(new ThreadPoolExecutor.AbortPolicy());
		// 执行初始化
		//executor.initialize();
		return executor;
	}
	
	@Bean(name="messageSource")
	public MessageSource  messageSource() {
		ReloadableResourceBundleMessageSource messageSource=new ReloadableResourceBundleMessageSource();
		String[] basenames= {"classpath:messages"};
		messageSource.setBasenames(basenames);
		messageSource.setDefaultEncoding("UTF-8");
		//messageSource.setCacheSeconds(36);
		LOGGER.info(messageSource.getMessage("0000", null,null, Locale.getDefault()));
		LOGGER.info("{}",messageSource.getMessage("0001", null,null, Locale.getDefault()));
		return messageSource;
	}
}
