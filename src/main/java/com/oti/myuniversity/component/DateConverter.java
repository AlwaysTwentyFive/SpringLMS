package com.oti.myuniversity.component;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.springframework.stereotype.Component;

@Component
public class DateConverter {
	private Timestamp now = new Timestamp(System.currentTimeMillis());
	private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	private SimpleDateFormat timeFormat = new SimpleDateFormat("yyyy-MM-DD HH:mm:ss");
	private String formattedDate = dateFormat.format(now);
	private String formattedTime = timeFormat.format(now);
	private Date sqlDate = Date.valueOf(formattedDate);
	private Timestamp sqlTime = Timestamp.valueOf(formattedTime);
	
	public int getDayOfWeek() {
		Calendar rightNow = Calendar.getInstance();
		int day = rightNow.get(Calendar.DAY_OF_WEEK);
		return day;
	}
	
	public Timestamp getNow() {
		return now;
	}
	public void setNow(Timestamp now) {
		this.now = now;
	}
	public SimpleDateFormat getDateFormat() {
		return dateFormat;
	}
	public void setDateFormat(SimpleDateFormat dateFormat) {
		this.dateFormat = dateFormat;
	}
	public SimpleDateFormat getTimeFormat() {
		return timeFormat;
	}
	public void setTimeFormat(SimpleDateFormat timeFormat) {
		this.timeFormat = timeFormat;
	}
	public String getFormattedDate() {
		return formattedDate;
	}
	public void setFormattedDate(String formattedDate) {
		this.formattedDate = formattedDate;
	}
	public String getFormattedTime() {
		return formattedTime;
	}
	public void setFormattedTime(String formattedTime) {
		this.formattedTime = formattedTime;
	}
	public Date getSqlDate() {
		return sqlDate;
	}
	public void setSqlDate(Date sqlDate) {
		this.sqlDate = sqlDate;
	}
	public Timestamp getSqlTime() {
		return sqlTime;
	}
	public void setSqlTime(Timestamp sqlTime) {
		this.sqlTime = sqlTime;
	}

}
