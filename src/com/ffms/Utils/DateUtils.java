package com.ffms.Utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
	
	/**
	 * 	获取系统当前的日期
	 * @return
	 */
	public static String getCurrentTime() {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		//	转换日期
		return sdf.format(date);
	}
}
