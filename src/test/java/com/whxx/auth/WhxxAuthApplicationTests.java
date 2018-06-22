package com.whxx.auth;

import java.text.ParseException;
import java.util.Date;

import org.apache.commons.lang3.time.DateUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.whxx.core.utils.AppUtils;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WhxxAuthApplicationTests {

	@Test
	public void contextLoads() {
	}

	@Test
	public void appUtilsTest(){
		
		System.out.println(AppUtils.getApplicationName());
		System.out.println(AppUtils.getProperties("JDBC","USERNAME"));
		System.out.println(AppUtils.getProperties("JDBC","username"));
		System.out.println(AppUtils.getProperties("test","TEST_KEY"));
		System.out.println(AppUtils.getProperties("test","TEST_KEY1"));
		System.out.println(AppUtils.getProperties("test","TEST_KEY1","defaultValue"));
		try {
			Date date = DateUtils.parseDate("2018-06-07", "yyyy-MM-dd");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
