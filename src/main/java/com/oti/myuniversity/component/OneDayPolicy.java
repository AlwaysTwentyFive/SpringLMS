package com.oti.myuniversity.component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.oti.myuniversity.attendance.model.Attendance;

public class OneDayPolicy implements AttendPolicy {
	
	@Override
	public String evaluateAttend(Attendance attendance) {
		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat("hh:mm:ss");
			Date startTime = dateFormat.parse("09:00:00");
			Date endTime = dateFormat.parse("18:00:00");	
			int endSuccess = attendance.getAttendanceArriveTime().compareTo(startTime);
			int startSuccess = attendance.getAttendanceDepartTime().compareTo(endTime);
			
			if(startSuccess<=0 && endSuccess>=0) {
				return "출석";
			}
			if(startSuccess>0 && endSuccess>=0) {
				return "지각";
			} else {
				return "결석";
			}
			
		} catch(ParseException e) {
			e.printStackTrace();
			
		}
		return null;
	}

}
