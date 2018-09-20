package com.registration.center;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class RegistrationCenterApplication {

	/**
	 * 如何持久化注册的IP
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(RegistrationCenterApplication.class, args);
	}
}
