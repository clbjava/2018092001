package com.transaction.configuration;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
//@EnableDiscoveryClient
@ComponentScan(basePackages = { "com.transaction" }, lazyInit = false)
public class TransactionsServiceApplication {
	
	public static void main(String[] args) {
		SpringApplication SpringApplication=new SpringApplication(TransactionsServiceApplication.class);
		SpringApplication.setBannerMode(Banner.Mode.OFF);
		SpringApplication.run( args);
	}
	
}
