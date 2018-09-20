package com.transaction.service;

import java.util.Map;

import com.transaction.service.support.BaseService;

public interface TransactionService extends BaseService {

	public Map<String, Object> recharge(Map<String, Object> params);

	public Map<String, Object> pay(Map<String, Object> params);

	public Map<String, Object> query(Map<String, Object> params);

}
