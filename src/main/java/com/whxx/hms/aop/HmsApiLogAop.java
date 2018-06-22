package com.whxx.hms.aop;

import java.lang.reflect.Method;
import java.util.Date;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.whxx.core.kit.HttpKit;
import com.whxx.core.utils.FormatUtil;
import com.whxx.core.utils.SpringContextHolder;
import com.whxx.core.utils.UUIDUtil;
import com.whxx.hms.annotation.HmsBussinessLog;
import com.whxx.hms.dao.AccessLogMapper;
import com.whxx.hms.model.TbAccessLog;

@Aspect
@Component
public class HmsApiLogAop {
	
	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	private static  AccessLogMapper accessLogMapper = SpringContextHolder.getBean(AccessLogMapper.class);

	/**
	 * @HotelBussinessLog为注解，可以自动生成日志
	 * @Title: cutService 
	 * @Description: TODO
	 * @return: void
	 */
    @Pointcut(value = "@annotation(com.whxx.hms.annotation.HmsBussinessLog)")
    public void cutService() {
    }

    @Around("cutService()")
    public Object recordSysLog(ProceedingJoinPoint point) throws Throwable {

        //先执行业务
        Object result = point.proceed();

        try {
            handle(point,result);
        } catch (Exception e) {
            log.error("酒店管理平台访问接口日志记录出错!" + e.getMessage(), e);
        }

        return result;
    }

    private void handle(ProceedingJoinPoint point,Object result) throws Exception {
        //获取拦截的方法名
        Signature sig = point.getSignature();
        MethodSignature msig = null;
        if (!(sig instanceof MethodSignature)) {
            throw new IllegalArgumentException("该注解只能用于方法");
        }
        msig = (MethodSignature) sig;
        Object target = point.getTarget();
        Method currentMethod = target.getClass().getMethod(msig.getName(), msig.getParameterTypes());
        String methodName = currentMethod.getName();
        //获取request
        HttpServletRequest request = HttpKit.getRequest();
        //获取拦截方法的参数
       /* Map<String, String[]> paramMap = request.getParameterMap();*/
        StringBuilder sb = new StringBuilder();
        Enumeration<String> enu=request.getParameterNames();
        while(enu.hasMoreElements()){
        String paraName=(String)enu.nextElement();
        	sb.append(paraName);
        	sb.append("=");
        	sb.append(request.getParameter(paraName));
        	sb.append("&");
        } 
        //获取注解方法
        HmsBussinessLog annotation = currentMethod.getAnnotation(HmsBussinessLog.class);
        String deviceId = request.getParameter("deviceCode");
        StringBuffer url = request.getRequestURL();
        url.append("?");
        url.append(sb);
        String msg = "方法名 【【【【【" + methodName + "】】】】====" + annotation.description();//描述
        
        //新增访问日志表
        TbAccessLog accessLog = new TbAccessLog();
    	accessLog.setTbAccessLogId(UUIDUtil.getUUID());
    	accessLog.setDeviceId(deviceId);
    	accessLog.setUrl(FormatUtil.toString(url));
    	accessLog.setInfo(msg.getBytes());
    	accessLog.setRequest(FormatUtil.toString(url));
    	accessLog.setResponse(FormatUtil.toString(result));
    	accessLog.setCreateDate(new Date());
    	accessLog.setUpdateDate(new Date());
    	accessLog.setVersion(0);
    	accessLog.setCreateEmp("SYS");
    	accessLogMapper.insert(accessLog);
    }
 
}
