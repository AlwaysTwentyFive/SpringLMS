package com.oti.myuniversity.domain.attendance.model;

import java.sql.Date;

public class Attendance {
	private int attendanceId;
	private String memberId;
	private Date attendanceDate;
	private Date attendanceArriveTime;
	private Date attendanceDepartTime;
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
	public Date getAttendanceArriveTime() {
		return attendanceArriveTime;
	}
	public void setAttendanceArriveTime(Date attendanceArriveTime) {
		this.attendanceArriveTime = attendanceArriveTime;
	}
	public Date getAttendanceDepartTime() {
		return attendanceDepartTime;
	}
	public void setAttendanceDepartTime(Date attendanceDepartTime) {
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
