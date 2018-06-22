package com.whxx.auth;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.Date;

import org.apache.commons.lang3.time.DateUtils;

public class TestDemo {
	public static void main(String[] args) {
		/*try {
			Date date = DateUtils.parseDate("2018-06-07", "yyyy-MM-dd");
			System.out.println(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		DecimalFormat percent = new DecimalFormat("##%");//计算百分比
		
		Double inRoomPercent=(double) 2/(double)3;
		String format = percent.format(inRoomPercent);
		System.out.println(format);
	}
}
