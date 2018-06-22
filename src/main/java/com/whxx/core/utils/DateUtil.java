/**
 * Copyright (c) 2015-2016, Chill Zhuang 庄骞 (smallchill@163.com).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.whxx.core.utils;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;

public class DateUtil extends cn.hutool.core.date.DateUtil{


	/**
	 * 获取YYYY格式
	 *
	 * @return
	 */
	public static String getYear() {
		return formatDate(new Date(), "yyyy");
	}

	/**
	 * 获取YYYY格式
	 *
	 * @return
	 */
	public static String getYear(Date date) {
		return formatDate(date, "yyyy");
	}

	/**
	 * 获取YYYY-MM-DD格式
	 *
	 * @return
	 */
	public static String getDay() {
		return formatDate(new Date(), "yyyy-MM-dd");
	}

	/**
	 * 获取YYYY-MM-DD格式
	 *
	 * @return
	 */
	public static String getDay(Date date) {
		return formatDate(date, "yyyy-MM-dd");
	}

	/**
	 * 获取YYYYMMDD格式
	 *
	 * @return
	 */
	public static String getDays() {
		return formatDate(new Date(), "yyyyMMdd");
	}

	/**
	 * 获取YYYYMMDD格式
	 *
	 * @return
	 */
	public static String getDays(Date date) {
		return formatDate(date, "yyyyMMdd");
	}

	/**
	 * 获取YYYY-MM-DD HH:mm:ss格式
	 *
	 * @return
	 */
	public static String getTime() {
		return formatDate(new Date(), "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * 获取YYYY-MM-DD HH:mm:ss.SSS格式
	 *
	 * @return
	 */
	public static String getMsTime() {
		return formatDate(new Date(), "yyyy-MM-dd HH:mm:ss.SSS");
	}

	/**
	 * 获取YYYYMMDDHHmmss格式
	 *
	 * @return
	 */
	public static String getAllTime() {
		return formatDate(new Date(), "yyyyMMddHHmmss");
	}

	/**
	 * 获取YYYY-MM-DD HH:mm:ss格式
	 *
	 * @return
	 */
	public static String getTime(Date date) {
		return formatDate(date, "yyyy-MM-dd HH:mm:ss");
	}

	public static String formatDate(Date date, String pattern) {
		String formatDate = null;
		if (StringUtils.isNotBlank(pattern)) {
			formatDate = DateFormatUtils.format(date, pattern);
		} else {
			formatDate = DateFormatUtils.format(date, "yyyy-MM-dd");
		}
		return formatDate;
	}

	/**
	 * @Title: compareDate
	 * @Description:(日期比较，如果s>=e 返回true 否则返回false)
	 * @param s
	 * @param e
	 * @return boolean
	 * @throws
	 * @author luguosui
	 */
	public static boolean compareDate(String s, String e) {
		if (parseDate(s) == null || parseDate(e) == null) {
			return false;
		}
		return parseDate(s).getTime() >= parseDate(e).getTime();
	}
	
	public static boolean compareDateLess(String s, String e) {
		if (parseDate(s) == null || parseDate(e) == null) {
			return false;
		}
		return parseDate(s).getTime() < parseDate(e).getTime();
	}

	/**
	 * 格式化日期
	 *
	 * @return
	 *//*
	public static Date parseDate(String date) {
		return parse(date,"yyyy-MM-dd");
	}

	*//**
	 * 格式化日期
	 *
	 * @return
	 *//*
	public static Date parseTime(String date) {
		return parse(date,"yyyy-MM-dd HH:mm:ss");
	}

	*//**
	 * 格式化日期
	 *
	 * @return
	 *//*
	public static Date parse(String date, String pattern) {
		try {
			return DateUtils.parseDate(date,pattern);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}*/

	/**
	 * 格式化日期
	 *
	 * @return
	 */
	public static String format(Date date, String pattern) {
		return DateFormatUtils.format(date, pattern);
	}

	/**
	 * 把日期转换为Timestamp
	 *
	 * @param date
	 * @return
	 */
	public static Timestamp format(Date date) {
		return new Timestamp(date.getTime());
	}

	/**
	 * 校验日期是否合法
	 *
	 * @return
	 */
	public static boolean isValidDate(String s) {
		return parse(s, "yyyy-MM-dd HH:mm:ss") != null;
	}

	/**
	 * 校验日期是否合法
	 *
	 * @return
	 */
	public static boolean isValidDate(String s, String pattern) {
        return parse(s, pattern) != null;
	}

	public static int getDiffYear(String startTime, String endTime) {
		DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
		try {
			int years = (int) (((fmt.parse(endTime).getTime() - fmt.parse(
					startTime).getTime()) / (1000 * 60 * 60 * 24)) / 365);
			return years;
		} catch (Exception e) {
			// 如果throw java.text.ParseException或者NullPointerException，就说明格式不对
			return 0;
		}
	}

	/**
	 * <li>功能描述：时间相减得到天数
	 *
	 * @param beginDateStr
	 * @param endDateStr
	 * @return long
	 * @author Administrator
	 */
	public static long getDaySub(String beginDateStr, String endDateStr) {
		long day = 0;
		SimpleDateFormat format = new SimpleDateFormat(
				"yyyy-MM-dd");
		Date beginDate = null;
		Date endDate = null;

		try {
			beginDate = format.parse(beginDateStr);
			endDate = format.parse(endDateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		day = (endDate.getTime() - beginDate.getTime()) / (24 * 60 * 60 * 1000);
		// System.out.println("相隔的天数="+day);

		return day;
	}

	/**
	 * 得到n天之后的日期
	 *
	 * @param days
	 * @return
	 */
	public static String getAfterDayDate(String days) {
		int daysInt = Integer.parseInt(days);

		Calendar canlendar = Calendar.getInstance(); // java.util包
		canlendar.add(Calendar.DATE, daysInt); // 日期减 如果不够减会将月变动
		Date date = canlendar.getTime();

		SimpleDateFormat sdfd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateStr = sdfd.format(date);

		return dateStr;
	}

	/**
	 * 得到n天之后是周几
	 *
	 * @param days
	 * @return
	 */
	public static String getAfterDayWeek(String days) {
		int daysInt = Integer.parseInt(days);

		Calendar canlendar = Calendar.getInstance(); // java.util包
		canlendar.add(Calendar.DATE, daysInt); // 日期减 如果不够减会将月变动
		Date date = canlendar.getTime();

		SimpleDateFormat sdf = new SimpleDateFormat("E");
		String dateStr = sdf.format(date);

		return dateStr;
	}
	
	/**
	 * 日期增加指定小时
	 * @Title: getSpecifiedHoursAfter 
	 * @Description: TODO
	 * @param dateStr
	 * @param hours
	 * @return
	 * @return: String
	 */
    public static String getAfterHours(String dateStr,int hours) {  
        Calendar c = Calendar.getInstance();  
        String convertDateStr = null;  
        try {  
        	Date date = new SimpleDateFormat("yy-MM-dd HH:mm:ss").parse(dateStr); 
            c.setTime(date);
            c.add(Calendar.HOUR_OF_DAY, hours);
            date = c.getTime();
            convertDateStr = formatDate(date, "yy-MM-dd HH:mm:ss");
        } catch (ParseException e) {  
            e.printStackTrace();  
        }  
        return convertDateStr;  
    } 
	
	/**
	 * 字符串日期转为标准格式
	 * @Title: formatDate 
	 * @Description: TODO
	 * @param dateStr
	 * @param pattern
	 * @return
	 * @return: String
	 */
	public static String formatDate(String dateStr, String pattern) {
		String formatDate = null;
		try {
			Date  date = new SimpleDateFormat("yyyy-MM-dd").parse(dateStr);
			if (StringUtils.isNotBlank(pattern)) {
				formatDate = DateFormatUtils.format(date, pattern);
			} else {
				formatDate = DateFormatUtils.format(date, "yyyy-MM-dd");
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return formatDate;
	}

	/**
	 * 格式化Oracle Date
	 * @param value
	 * @return
	 */
//	public static String buildDateValue(Object value){
//		if(Func.isOracle()){
//			return "to_date('"+ value +"','yyyy-mm-dd HH24:MI:SS')";
//		}else{
//			return Func.toStr(value);
//		}
//	}

	public static void main(String[] args) {
		System.out.println(getTime(new Date()));
		System.out.println(getAfterDayWeek("3"));
	}
	
	//得到本月的最后一天
	public static String getActdates(String actdate) {
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        Date d = new Date();
        try {
            d = sf.parse(actdate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        GregorianCalendar gc=new GregorianCalendar();
      //得到本月的最后一天
        gc.setTime(d);
        gc.set(gc.get(Calendar.YEAR),gc.get(Calendar.MONTH),gc.getActualMaximum(Calendar.DAY_OF_MONTH));
        String curmonthActdate = sf.format(gc.getTime());
        return curmonthActdate;
	}
	
	//日期相加天数
	public static String getDateAdd(String date,int day) throws ParseException {
		Date parseDate = new SimpleDateFormat("yyyy-MM-dd").parse(date);
		SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd"); 
		String format = df.format(new Date(parseDate.getTime() + 1000*60*60*24*day));
		return format;
	}
	
	
	/**
	 * 日期格式偏移
	 * @Title: newDate 
	 * @Description: TODO
	 * @param old
	 * @param field
	 * @param amount
	 * @return
	 * @return: Date
	 */
	public static Date newDate(Date old,int field ,int amount) {
		Date date = null;
		try {
			Calendar calendar = Calendar.getInstance();
        	calendar.setTime(old);
        	calendar.add(field, amount);
        	date = calendar.getTime();
        	
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return date;
	}
	
	/**
	 * 时间戳转日期格式
	 * @Title: long2Date 
	 * @Description: TODO
	 * @param amount
	 * @return
	 * @return: Date
	 */
	public static Date long2Date(long amount) {
		Date date = null;
		try {
			Calendar cal = Calendar.getInstance();
    		cal.setTimeInMillis(amount);
        	date = cal.getTime();
        	
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return date;
	}
}
