package com.oti.myuniversity.config;

import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.oti.myuniversity.component.ServerTimeSupplier;
import com.oti.myuniversity.domain.attendance.model.Attendance;
import com.oti.myuniversity.domain.attendance.repository.IAttendanceRepository;

@EnableScheduling
@Configuration
public class AppConfig {

	@Autowired
	IAttendanceRepository attendanceRepository;

	//Http 요청 시 마다 attendance 객체 생성 후 반환.
	//request scope를 가지는 경우, Provider를 따로 만들거나, proxtmode의 TARGET_CLASS을 이용해야 한다. (Controller에 직접 DI 되면 컴파일 시 해당 빈이 없어(request scope에서 생성되기 때문에 없음) 초기화 실패가 발생, 서버 구동이 안됨)
	@Bean(autowire=Autowire.BY_TYPE)
	@Scope(value="request", proxyMode=ScopedProxyMode.TARGET_CLASS)
	public Attendance attendance() {
		return new Attendance();
	}
	
	//Http 요청 시 마다 attendance 객체 생성, Attendance 필드 생성 시, initializedAttendance라고 명명하면 이  Bean이 반환 됨.
	@Bean(autowire=Autowire.BY_NAME, name="initializedAttendance")
	@Scope(value="request", proxyMode=ScopedProxyMode.TARGET_CLASS)
	public Attendance initializedAttendance() {
		ServerTimeSupplier.setTime();
		Attendance attendance = new Attendance();
		attendance.setAttendanceDate(ServerTimeSupplier.getDate());
		attendance.setAttendanceArriveTime(ServerTimeSupplier.getTime());
		attendance.setAttendanceDepartTime(ServerTimeSupplier.getTime());
		return attendance;
	}
	
	
	//초 분 시 일 월 요일 [연도]
	@Scheduled(cron="0 0 0 * * *")
	public void checkTodayAttendance() {
		String attendanceDate = ServerTimeSupplier.getToday().toString();
		int result = attendanceRepository.updateTodayAttendance(attendanceDate);
		ServerTimeSupplier.plusToday();
		System.out.println(System.currentTimeMillis());
		System.out.println("[Auto update Attendance]: " + result);
	}
}
