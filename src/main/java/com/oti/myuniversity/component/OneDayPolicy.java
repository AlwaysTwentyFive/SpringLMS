package com.oti.myuniversity.component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Component;

import com.oti.myuniversity.attendance.model.Attendance;

@Component
public class OneDayPolicy implements AttendPolicy {
	
	@Override
	public String evaluateAttend(Attendance attendance) {
		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat("hh:mm:ss");
			Date startTime = dateFormat.parse("09:00:00");
			Date endTime = dateFormat.parse("18:00:00");	
			int startSuccess = attendance.getAttendanceArriveTime().compareTo(startTime);
			int endSuccess = attendance.getAttendanceDepartTime().compareTo(endTime);
	
			if(startSuccess<=0 && endSuccess>=0) {
				return "정상";
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
