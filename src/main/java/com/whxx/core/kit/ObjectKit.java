package com.whxx.core.kit;

import java.math.BigDecimal;
import java.util.regex.Pattern;

/**
 * 一些通用的函数
 * 
 * @author Looly
 *
 */
public class ObjectKit {
	/**
	 * 用于过滤数值型的字母和符号
	 */
	private static final Pattern numFilter = Pattern.compile("[^0-9.+-]");
	
	/**
	 * 用于判断是否是数字型
	 */
	private static final Pattern numFormat = Pattern.compile("^(\\-|\\+)?\\d*(\\.\\d+)?$");

	/**
	 * 比较两个对象是否相等。<br>
	 * 相同的条件有两个，满足其一即可：<br>
	 * 1. obj1 == null && obj2 == null; 2. obj1.equals(obj2)
	 * 
	 * @param obj1 对象1
	 * @param obj2 对象2
	 * @return 是否相等
	 */
	public static boolean equals(Object obj1, Object obj2) {
		return (obj1 != null) ? (obj1.equals(obj2)) : (obj2 == null);
	}
	
	/**
	 * 
	 * 
	 * @param obj
	 * @return
	 * @exception @since
	 *                1.0.0
	 */
	public static final long getLong(Object obj) {
		return (long)getInt(obj);
	}
	
	/**
	 * 
	 * 
	 * @param obj
	 * @return
	 * @exception @since
	 *                1.0.0
	 */
	public static final int getInt(Object obj) {
		int ret = 0;
		if (null != obj) {
			if (obj instanceof BigDecimal) {
				ret = ((BigDecimal) obj).setScale(0, BigDecimal.ROUND_HALF_UP).intValue();
			} else if (obj instanceof String) {
				String value = (String) obj;
				if (!"".equals(value) && !"-".equals(value) && !"+".equals(value)
						&& numFormat.matcher(value).matches()) {
					ret = new BigDecimal(value).setScale(0, BigDecimal.ROUND_HALF_UP).intValue();
				} else {
					value = numFilter.matcher(value).replaceAll("").trim();
					if (!"".equals(value) && !"-".equals(value) && !"+".equals(value)
							&& numFormat.matcher(value).matches()) {
						ret = new BigDecimal(value).setScale(0, BigDecimal.ROUND_HALF_UP).intValue();
					}
				}
			} else if (obj instanceof Long) {
				ret = ((Long) obj).intValue();
			} else if (obj instanceof Integer) {
				ret = ((Integer) obj).intValue();
			} else if (obj instanceof Number) {
				ret = new BigDecimal(((Number) obj).doubleValue()).setScale(0, BigDecimal.ROUND_HALF_UP).intValue();
			}
		}
		return ret;
	}
}
