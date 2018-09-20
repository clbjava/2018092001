package com;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.transaction.configuration.TransactionsServiceApplication;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=TransactionsServiceApplication.class)
//@TestPropertySource(locations= {"classpath:application.properties"})
public class JunitBaseTest {

}
