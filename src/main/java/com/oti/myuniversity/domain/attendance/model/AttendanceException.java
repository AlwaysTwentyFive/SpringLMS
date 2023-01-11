package com.oti.myuniversity.domain.attendance.model;

import java.sql.Date;
import java.sql.Timestamp;

public class AttendanceException {
	private int attendanceExceptionId;
	private int attendanceId;
	private Date attendanceExceptionApplyDate;
	private String attendanceExceptionStatus;
	private Boolean attendanceExceptionApproved;
	private String attendanceExceptionTitle;
	private String attendanceExceptionContent;
	private Date attendanceExceptionDate;
	
	public String getAttendanceExceptionTitle() {
		return attendanceExceptionTitle;
	}
	public void setAttendanceExceptionTitle(String attendanceExceptionTitle) {
		this.attendanceExceptionTitle = attendanceExceptionTitle;
	}
	public String getAttendanceExceptionContent() {
		return attendanceExceptionContent;
	}
	public void setAttendanceExceptionContent(String attendanceExceptionContent) {
		this.attendanceExceptionContent = attendanceExceptionContent;
	}
	public int getAttendanceExceptionId() {
		return attendanceExceptionId;
	}
	public void setAttendanceExceptionId(int attendanceExceptionId) {
		this.attendanceExceptionId = attendanceExceptionId;
	}
	public int getAttendanceId() {
		return attendanceId;
	}
	public void setAttendanceId(int attendanceId) {
		this.attendanceId = attendanceId;
	}
	public Date getAttendanceExceptionApplyDate() {
		return attendanceExceptionApplyDate;
	}
	public void setAttendanceExceptionApplyDate(Date attendanceExceptionApplyDate) {
		this.attendanceExceptionApplyDate = attendanceExceptionApplyDate;
	}
	public String getAttendanceExceptionStatus() {
		return attendanceExceptionStatus;
	}
	public void setAttendanceExceptionStatus(String attendanceExceptionStatus) {
		this.attendanceExceptionStatus = attendanceExceptionStatus;
	}
	public Boolean getAttendanceExceptionApproved() {
		return attendanceExceptionApproved;
	}
	public void setAttendanceExceptionApproved(Boolean attendanceExceptionApproved) {
		this.attendanceExceptionApproved = attendanceExceptionApproved;
	}
	public Date getAttendanceExceptionDate() {
		return attendanceExceptionDate;
	}
	public void setAttendanceExceptionDate(Date attendanceExceptionDate) {
		this.attendanceExceptionDate = attendanceExceptionDate;
	}
	
	@Override
	public String toString() {
		return "AttendanceException [attendanceExceptionId=" + attendanceExceptionId + ", attendanceId=" + attendanceId
				+ ", attendanceExceptionApplyDate=" + attendanceExceptionApplyDate + ", attendanceExceptionStatus="
				+ attendanceExceptionStatus + ", attendanceExceptionApproved=" + attendanceExceptionApproved
				+ ", attendanceExceptionTitle=" + attendanceExceptionTitle + ", attendanceExceptionContent="
				+ attendanceExceptionContent + ", attendanceExceptionDate=" + attendanceExceptionDate + "]";
	}

}
