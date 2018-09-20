package com.transaction.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.transaction.service.TransactionService;
import com.transaction.service.support.GoalService;

@Service("com.transactions.service.impl.TransactionServiceImpl")
public class TransactionServiceImpl implements TransactionService {
	
	private final Logger LOG=LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	ObjectMapper objectMapper;

	@Override
	@GoalService(serviceId = "1234T001")
	public Map<String, Object> recharge(Map<String, Object> params) {
		try {
			LOG.info("-----:{}",objectMapper.writeValueAsString(params));
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Map<String, Object> response = new HashMap<String, Object>();
		response.put("code", "0000");
		response.put("msg", "success");
		return response;
	}

	@Override
	@GoalService(serviceId = "1234T002")
	public Map<String, Object> pay(Map<String, Object> params) {
		Map<String, Object> response = new HashMap<String, Object>();
		response.put("code", "0000");
		response.put("msg", "success");
		return response;
	}

	@Override
	@GoalService(serviceId = "1234T003")
	public Map<String, Object> query(Map<String, Object> params) {
		Map<String, Object> response = new HashMap<String, Object>();
		response.put("code", "0000");
		response.put("msg", "success");
		return response;
	}

}