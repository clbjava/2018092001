package com.transaction.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.embedded.tomcat.TomcatReactiveWebServerFactory;
import org.springframework.stereotype.Component;

@Component
public class TomcatReactivePro extends TomcatReactiveWebServerFactory {
	
	private final Logger LOGGER = LoggerFactory.getLogger(TomcatReactivePro.class);
	
	/*@Value("${server.protocol:org.apache.coyote.http11.Http11Nio2Protocol}")
	private String protocol; */
	public TomcatReactivePro() {
		super();
	}
	
	public void setProtocol(String protocol) {
		super.setProtocol("server.protocol:org.apache.coyote.http11.Http11Nio2Protocol");
		LOGGER.info("+++{}",protocol);
	}
}
