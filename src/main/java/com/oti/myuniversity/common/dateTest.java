package com.oti.myuniversity.common;

import static com.oti.myuniversity.common.Consts.DATE_FORMAT;
import static com.oti.myuniversity.common.Consts.TIME_FORMAT;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class dateTest {
	
	private static Date record;
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
	private static final SimpleDateFormat timeFormat = new SimpleDateFormat(TIME_FORMAT);
	
	public static int getDayOfWeek() {
		Calendar rightNow = Calendar.getInstance();
		int day = rightNow.get(Calendar.DAY_OF_WEEK);
		return day;
	}
	
	public static void setTime() {
		record = new Date(System.currentTimeMillis());
	}
	
	public static String checkTime() {
		if (record == null) {
			record = new Date(System.currentTimeMillis());
		}
		return record.toString();
	}
	
	public static Date getDate() {
		return Date.valueOf(dateFormat.format(record));
	}
	
	public static Date getTime() {
		return Date.valueOf(timeFormat.format(record));
	}

	public static void main(String[] args) {
		setTime();
		Date date = getTime();
		System.out.println(date);
	}

}
