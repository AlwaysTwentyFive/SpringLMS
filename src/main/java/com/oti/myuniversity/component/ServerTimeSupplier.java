package com.oti.myuniversity.component;

import static com.oti.myuniversity.common.Consts.DATE_FORMAT;
import static com.oti.myuniversity.common.Consts.TIME_FORMAT;


import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.springframework.stereotype.Component;

@Component
public class ServerTimeSupplier {
	private static Date today;
	private static Timestamp record;
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
	private static final SimpleDateFormat timeFormat = new SimpleDateFormat(TIME_FORMAT);
	
	static {
		today = new Date(System.currentTimeMillis());
	}
	
	public static int getDayOfWeek() {
		Calendar rightNow = Calendar.getInstance();
		int day = rightNow.get(Calendar.DAY_OF_WEEK);
		return day;
	}
	
	public ServerTimeSupplier() {
		setTime();
	}

	public static void setTime() {
		record = new Timestamp(System.currentTimeMillis());
	}
	
	public static String checkTime() {
		if (record == null) {
			record = new Timestamp(System.currentTimeMillis());
		}
		return record.toString();
	}
	
	public static Date getDate() {
		return Date.valueOf(dateFormat.format(record));
	}
	
	public static Timestamp getTime() {
		return Timestamp.valueOf(timeFormat.format(record));
	}
	
	public static void plusToday() {
		today = Date.valueOf(today.toLocalDate().plusDays(1).toString());
	}
	
	public static Date getToday() {
		return today;
	}
	
	public static Date timestampToDate(Timestamp timestamp) {
		return Date.valueOf(dateFormat.format(timestamp));
	}
	
	public static Timestamp dateToTimestamp(Date date) {
		return Timestamp.valueOf(timeFormat.format(date));
	}
}
