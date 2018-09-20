package com.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.JunitBaseTest;
import com.transaction.service.TransactionService;

public class TransactionServiceImplTest extends JunitBaseTest {
	
	@Autowired
	@Qualifier("com.transactions.service.impl.TransactionServiceImpl")
	TransactionService transactionService;

	final Logger LOGGER = LoggerFactory.getLogger(TransactionServiceImplTest.class);


	@Test
	public void testRecharge() {
		//transactionServicepay(null);
		Map<String,Object> params=new HashMap<String,Object>();
		params.put("w", "0000000");
		transactionService.recharge(params);
		/*try {
			UrlResource resource=new UrlResource("https://www.test.webankcdn.net/ymfadm/sit/W6130274/W6130274");
			LOGGER.info("back :{}",resource.getFilename());
			byte b[]=new byte[1024];
			InputStream input=resource.getInputStream();
			while(input.read(b)<0) {
				LOGGER.info("close");
				input.close();
			}
			LOGGER.info("file :{}",new String(b,"UTF-8"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	}

	

	@Test
	public void testPay() {
		
	}

	@Test
	public void testQuery() {
		
	}

}
