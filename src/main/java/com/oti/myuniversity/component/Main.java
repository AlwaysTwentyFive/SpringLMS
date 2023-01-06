package com.oti.myuniversity.component;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Timestamp now = new Timestamp(System.currentTimeMillis());
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat timeFormat = new SimpleDateFormat("yyyy-MM-DD HH:mm:ss");
		
		String formattedDate = dateFormat.format(now);
		String formattedTime = timeFormat.format(now);
		
		Timestamp sqlTime = Timestamp.valueOf(formattedTime);

		
		String startTime = "09:00:00";
		String endTime = "18:00:00";
		String departTime = "08:00:00";
		int start = departTime.compareTo(startTime);
		//System.out.println(start);
		
		
		

	}

}
