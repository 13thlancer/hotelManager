package com.whxx.core.filter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.filter.OncePerRequestFilter;

import com.whxx.hms.properties.HmsProperties;

public class SignFilter extends OncePerRequestFilter {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
	private HmsProperties hmsProperties;
    
//    @Autowired
//	private DeviceSrv deviceSrv;

    @Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
    	String servletPath = request.getServletPath();
    	//1、判断是否是访问获取绿云接口，验证签名
//		if (servletPath.indexOf(hotelProperties.getGreencloudSign()) >= 0) {
//			Map<String, String> paramMap = getParamMap(request);
//			if(null == paramMap || paramMap.isEmpty()){
//				logger.info("请求参数为空");
//				RenderUtil.renderJson(response, RestParams.newInstance(303, "请求参数为空", null));
//				return ;
//			}
			//2、判断设备号是否为空
//			String deviceCode = paramMap.get("deviceCode");
//			if (StringUtils.isBlank(deviceCode)) {
//				logger.error("deviceCode【" + deviceCode + "】为空");
//				RenderUtil.renderJson(response, RestParams.newInstance(304, "设备编码为空", null));
//				return ;
//			}
//			//3、查询设备公钥
//			String publicKey = deviceSrv.selectPublicKey(deviceCode);
//			if (StringUtils.isBlank(publicKey)) {
//				logger.error("【" + deviceCode + "】无密钥，请先设置密钥");
//				RenderUtil.renderJson(response, RestParams.newInstance(305, deviceCode + "无密钥，请先设置密钥", null));
//				return ;
//			}
//			//4、验签
//			if (!SignUtil.ifCValidSignature(paramMap, publicKey)) {
//				logger.info("验签失败");
//				RenderUtil.renderJson(response, RestParams.newInstance(200, "验签失败,请核查公私钥是否匹配", null));
//				return ;
//			}
			//return paramMap;
//		} 
		chain.doFilter(request, response);
	}
    

	/**
	 * 获取request将ParamMap参数转换为MAP
	 * 
	 * @Title: getParamMap
	 * @Description: TODO
	 * @param request
	 * @return
	 * @return: Map<String,String>
	 */
	public static Map<String, String> getParamMap(HttpServletRequest request) {
		Map<String, String> paramMap = new HashMap<String, String>();
		Map<String, String[]> requestMap = request.getParameterMap();
		Iterator<Entry<String, String[]>> it = requestMap.entrySet().iterator();
		while (it.hasNext()) {
			Entry<String, String[]> entry = it.next();
			if (entry.getValue().length == 1) {
				paramMap.put(entry.getKey(), entry.getValue()[0]);
			} else {
				String[] values = entry.getValue();
				String value = "";
				for (int i = 0; i < values.length; i++) {
					value = values[i] + ",";
				}
				value = value.substring(0, value.length() - 1);
				paramMap.put(entry.getKey(), value);
			}
		}
		return paramMap;
	}


}