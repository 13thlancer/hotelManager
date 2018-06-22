package com.whxx.hms.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = HmsProperties.HMS_PREFIX)
public class HmsProperties {
	
	public static final String HMS_PREFIX = "hms";
	
	private String filePath = "";
	
	private String swaggerEnable = "true";
	
	private String restfulSign = "/rest";
	
	
	public String getRestfulSign() {
		return restfulSign;
	}

	public void setRestfulSign(String restfulSign) {
		this.restfulSign = restfulSign;
	}

	/**
	 * @return the filePath
	 */
	public String getFilePath() {
		return filePath;
	}

	/**
	 * @param filePath the filePath to set
	 */
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	/**
	 * @return the swaggerEnable
	 */
	public String getSwaggerEnable() {
		return swaggerEnable;
	}

	/**
	 * @param swaggerEnable the swaggerEnable to set
	 */
	public void setSwaggerEnable(String swaggerEnable) {
		this.swaggerEnable = swaggerEnable;
	}
}
