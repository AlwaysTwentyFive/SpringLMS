package com.oti.myuniversity.attendance.model;

import java.sql.Date;
import java.sql.Timestamp;

public class Attendance {
	private int attendanceId;
	private String memberId;
	private Date attendanceDate;
	private Timestamp attendanceArriveTime;
	private Timestamp attendanceDepartTime;
	private String attendanceStatus;
	
	public int getAttendanceId() {
		return attendanceId;
	}
	public void setAttendanceId(int attendanceId) {
		this.attendanceId = attendanceId;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public Date getAttendanceDate() {
		return attendanceDate;
	}
	public void setAttendanceDate(Date attendanceDate) {
		this.attendanceDate = attendanceDate;
	}
	public Timestamp getAttendanceArriveTime() {
		return attendanceArriveTime;
	}
	public void setAttendanceArriveTime(Timestamp attendanceArriveTime) {
		this.attendanceArriveTime = attendanceArriveTime;
	}
	public Timestamp getAttendanceDepartTime() {
		return attendanceDepartTime;
	}
	public void setAttendanceDepartTime(Timestamp attendanceDepartTime) {
		this.attendanceDepartTime = attendanceDepartTime;
	}
	public String getAttendanceStatus() {
		return attendanceStatus;
	}
	public void setAttendanceStatus(String attendanceStatus) {
		this.attendanceStatus = attendanceStatus;
	}
	
	@Override
	public String toString() {
		return "Attendance [attendanceId=" + attendanceId + ", memberId=" + memberId + ", attendanceDate="
				+ attendanceDate + ", attendanceArriveTime=" + attendanceArriveTime + ", attendanceDepartTime="
				+ attendanceDepartTime + ", attendanceStatus=" + attendanceStatus + "]";
	}
}
