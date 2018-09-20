package com.transaction.control.trans;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.transaction.configuration.Autoconfiguratuions;
import com.transaction.control.AbstractBaseControl;

@RestController
@RequestMapping("transactionServer")
public class TransactionControl extends AbstractBaseControl {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(TransactionControl.class);


	@RequestMapping(path = "query/{path}", method = RequestMethod.POST)
	public Map<String, Object> query(@PathVariable(value = "path", required = true) String path,
			@RequestBody(required = true) Map<String, Object> requestBody, HttpServletRequest request,
			HttpServletResponse respose) {

		return null;
	}

	@RequestMapping(path = "save/{path}", method = RequestMethod.POST)
	public Map<String, Object> save(@PathVariable(value = "path", required = true) String path,
			@RequestBody(required = true) Map<String, Object> requestBody, HttpServletRequest request,
			HttpServletResponse respose) {

		return null;
	}
	
	@RequestMapping(path = "query/{path}", method = RequestMethod.GET)
	public String test(@PathVariable(value = "path", required = true) String path,HttpServletRequest request,
			HttpServletResponse respose) {
		LOGGER.info("tread :{}",Thread.currentThread().getName());
		return "hello world"+path;
	}

}
