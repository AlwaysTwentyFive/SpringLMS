package com.oti.myuniversity.attendance.model;

import java.sql.Date;

public class Exception {
	private int exceptionId;
	private int attendanceId;
	private Date exceptionApplyDate;
	private String exceptionStatus;
	private boolean exceptionApproved;
	
	public int getExceptionId() {
		return exceptionId;
	}
	public void setExceptionId(int exceptionId) {
		this.exceptionId = exceptionId;
	}
	public int getAttendanceId() {
		return attendanceId;
	}
	public void setAttendanceId(int attendanceId) {
		this.attendanceId = attendanceId;
	}
	public Date getExceptionApplyDate() {
		return exceptionApplyDate;
	}
	public void setExceptionApplyDate(Date exceptionApplyDate) {
		this.exceptionApplyDate = exceptionApplyDate;
	}
	public String getExceptionStatus() {
		return exceptionStatus;
	}
	public void setExceptionStatus(String exceptionStatus) {
		this.exceptionStatus = exceptionStatus;
	}
	public boolean isExceptionApproved() {
		return exceptionApproved;
	}
	public void setExceptionApproved(boolean exceptionApproved) {
		this.exceptionApproved = exceptionApproved;
	}
	
	@Override
	public String toString() {
		return "Exception [exceptionId=" + exceptionId + ", attendanceId=" + attendanceId + ", exceptionApplyDate="
				+ exceptionApplyDate + ", exceptionStatus=" + exceptionStatus + ", exceptionApproved="
				+ exceptionApproved + "]";
	}
	
}
