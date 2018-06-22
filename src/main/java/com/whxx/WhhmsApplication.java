package com.whxx;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * 酒店PMS管理系统平台
 * @ClassName: WhhmsApplication 
 * @Description: TODO
 * @author: zengxin
 * @date: 2018年5月28日 上午9:27:56
 */
@MapperScan(basePackages = "com.whxx.**.dao")
@EnableCaching
@SpringBootApplication
public class WhhmsApplication {

	public static void main(String[] args) {
		
		SpringApplication.run(WhhmsApplication.class, args);
		
	}
	
}
