package com.transaction.configuration;

import org.apache.catalina.connector.Connector;
import org.apache.coyote.http11.Http11Nio2Protocol;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.autoconfigure.web.embedded.TomcatWebServerFactoryCustomizer;
import org.springframework.boot.web.embedded.tomcat.ConfigurableTomcatWebServerFactory;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class TomcatConnector extends TomcatWebServerFactoryCustomizer {
	
	@Value("${server.protocol:org.apache.coyote.http11.Http11Nio2Protocol}")
	private String protocol;  

	public TomcatConnector(Environment environment, ServerProperties serverProperties) {
		super(environment, serverProperties);
	}

	private final Logger LOGGER = LoggerFactory.getLogger(TomcatConnector.class);

	public void customize(ConfigurableTomcatWebServerFactory factory) {
		LOGGER.info("------------------{}",factory);
		//新增NIO2
		((TomcatServletWebServerFactory) factory).addAdditionalTomcatConnectors(createNioConnector1());
		super.customize(factory);
	}

	public Connector createNioConnector1() {
		Connector connector = new Connector(protocol);
		Http11Nio2Protocol protocol = (Http11Nio2Protocol) connector.getProtocolHandler();
		// 设置超时时间
		protocol.setConnectionTimeout(3000);
		// 设置最大线程数
		protocol.setMaxThreads(200);
		// 设置最大连接数
		protocol.setMaxConnections(1000);
		// 请求方式
		connector.setScheme("http");
		connector.setPort(8015); // 自定义的
		connector.setRedirectPort(8443);
		return connector;
	}

}
