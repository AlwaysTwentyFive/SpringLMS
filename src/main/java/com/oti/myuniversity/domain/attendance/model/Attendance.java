package com.oti.myuniversity.domain.attendance.model;

import java.sql.Date;
import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Attendance {
	private int attendanceId;
	private String memberId;
	private Date attendanceDate;
	@JsonFormat(timezone="Asia/Seoul", shape=JsonFormat.Shape.STRING,pattern="yyyy-MM-dd HH:mm:ss")
	private Timestamp attendanceArriveTime;
	@JsonFormat(timezone="Asia/Seoul", shape=JsonFormat.Shape.STRING,pattern="yyyy-MM-dd HH:mm:ss")
	private Timestamp attendanceDepartTime;
	private String attendanceStatus;
	private String memberName;
	private String memberType;
	
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

	public String getMemberName() {
		return memberName;
	}
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	
	public String getMemberType() {
		return memberType;
	}
	public void setMemberType(String memberType) {
		this.memberType = memberType;
	}
	
	@Override
	public String toString() {
		return "Attendance [attendanceId=" + attendanceId + ", memberId=" + memberId + ", attendanceDate="
				+ attendanceDate + ", attendanceArriveTime=" + attendanceArriveTime + ", attendanceDepartTime="
				+ attendanceDepartTime + ", attendanceStatus=" + attendanceStatus + ", memberName=" + memberName
				+ ", memberType=" + memberType + "]";
	}
	
	
	
	
}
