package com.service.support.impl;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import com.JunitBaseTest;
import com.transaction.service.support.DispatcherService;

public class DispatcherServiceImplTest extends JunitBaseTest {

	final Logger LOGGER = LoggerFactory.getLogger(DispatcherServiceImplTest.class);

	@Autowired
	@Qualifier("com.service.support.DispatcherService")
	DispatcherService dispatcherService;
	
	@Autowired
	@Qualifier("syncServiceExecutor")
	ThreadPoolTaskExecutor threadPoolTaskExecutor;

	@Test
	public void testDispatch() {/*
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("serviceId", "1234T001");
		Object object = dispatcherService.dispatch(params);
		LOGGER.info("back: {}", object);
	*/
		threadPoolTaskExecutor.execute(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				LOGGER.info("ff");
			}
		});
	
	}

}
