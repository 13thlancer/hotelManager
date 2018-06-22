package com.whxx.hms.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 调用中间层接口业务日志注解
 * @ClassName: HotelBussinessLog 
 * @Description: TODO
 * @author: zengxin
 * @date: 2018年4月3日 下午2:16:24
 */
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface HmsBussinessLog {
	  String description()  default "";    
}
