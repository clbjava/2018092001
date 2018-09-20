package com.transaction.service.support;

import java.util.Map;

public interface DispatcherService extends BaseService {

	public <T> T dispatch(Map<String, Object> params);
}
