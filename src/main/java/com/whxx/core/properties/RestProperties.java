package com.whxx.core.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = RestProperties.REST_PREFIX)
public class RestProperties {
	
	 public static final String REST_PREFIX = "rest";
	 
	 private String authOpen = "aaa";
	 
	 
	public static String getRestPrefix() {
		return REST_PREFIX;
	}

	/**
	 * @return the authOpen
	 */
	public String getAuthOpen() {
		return authOpen;
	}

	/**
	 * @param authOpen the authOpen to set
	 */
	public void setAuthOpen(String authOpen) {
		this.authOpen = authOpen;
	}

	

	
	 
	 

}
