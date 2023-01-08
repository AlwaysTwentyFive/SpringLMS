package com.oti.myuniversity.component;

import static com.oti.myuniversity.common.Consts.DATE_FORMAT;

import java.sql.Date;
import java.text.SimpleDateFormat;

import org.springframework.stereotype.Component;

@Component
public class ServerTimeSupplier {
	private static Date record;
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
	
	public ServerTimeSupplier() {
		setTime();
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
}
