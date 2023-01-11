package com.oti.myuniversity.config;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.oti.myuniversity.component.ServerTimeSupplier;
import com.oti.myuniversity.domain.attendance.model.Attendance;
import com.oti.myuniversity.domain.attendance.model.AttendanceExceptionFile;

@Configuration
public class AppConfig {

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
	
//	@Bean(autowire=Autowire.BY_NAME, name="initializedExceptionFiles")
//	public AttendanceExceptionFile attendanceExceptionFile() {
//		return new AttendanceExceptionFile();
//	}
//
//	//Context를 이용해서 요청할 때 마다 Bean 반환
//	@Bean(autowire=Autowire.BY_TYPE)
//	@Scope(value="request", proxyMode=ScopedProxyMode.TARGET_CLASS)
//	public List<AttendanceExceptionFile> initializedExceptionFiles(HttpServletRequest request) throws IOException {
//		List<AttendanceExceptionFile> attendanceExceptionFiles = new ArrayList<AttendanceExceptionFile>();
//		MultipartHttpServletRequest multiRequeset = (MultipartHttpServletRequest) request;
//		List<MultipartFile> multiPartFiles = multiRequeset.getFiles("attendanceExceptionFiles");
//		for (MultipartFile multiPartFile : multiPartFiles) {
//			AttendanceExceptionFile attendanceExceptionFile = new AttendanceExceptionFile();
//			attendanceExceptionFile.setAttendanceExceptionFileName(multiPartFile.getOriginalFilename());
//			attendanceExceptionFile.setAttendanceExceptionFileSize(multiPartFile.getSize());
//			attendanceExceptionFile.setAttendanceExceptionFileData(multiPartFile.getBytes());
//			attendanceExceptionFile.setAttendanceExceptionFileContentType(request.getContentType());
//			attendanceExceptionFiles.add(attendanceExceptionFile);
//		}
//		return attendanceExceptionFiles;
//	}

}
