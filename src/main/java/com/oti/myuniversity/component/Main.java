package com.oti.myuniversity.component;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Date utilDate = new Date();
		SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		
		String formattedDate = dateFormat.format(utilDate);
		String formattedTime = timeFormat.format(utilDate);
		
		java.sql.Date sqlDate = java.sql.Date.valueOf(formattedDate);
		java.sql.Time sqlTime = java.sql.Time.valueOf(formattedTime);
		
		System.out.println("오늘 날짜 : "+sqlDate);
		System.out.println("현재 시간 : "+sqlTime);

	}

}
