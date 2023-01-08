package com.oti.myuniversity.component;

import org.springframework.stereotype.Component;

import com.oti.myuniversity.domain.attendance.model.Attendance;

@Component
public class OneDayPolicy implements AttendPolicy {
	
	@Override
	public String evaluateAttend(Attendance attendance) {
			String startTime = "09:00:00";
			String endTime = "18:00:00";
			String arriveTime = attendance.getAttendanceArriveTime().toString().substring(11,19);
			String departTime = attendance.getAttendanceDepartTime().toString().substring(11,19);
			int endSuccess = departTime.compareTo(endTime);
			int startSuccess = arriveTime.compareTo(startTime);
	
			if(startSuccess<=0 && endSuccess>=0) {
				return "정상";
			}
			if(startSuccess>0 && endSuccess>=0) {
				return "지각";
			} else {
				return "결석";
			}
			
	}

}
