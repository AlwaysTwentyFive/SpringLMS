package com.oti.myuniversity.domain.attendance.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.oti.myuniversity.domain.attendance.model.AttendanceExceptionFile;
import com.oti.myuniversity.domain.attendance.service.IAttendanceExceptionFileService;

@Controller
public class AttendanceExceptionFileController {

	@Autowired
	IAttendanceExceptionFileService attendanceExceptionFileService;
	
	@RequestMapping("/exceptionfile/{attendanceExceptionFileId}")
	public ResponseEntity<byte[]> getFile(@PathVariable int attendanceExceptionFileId) throws UnsupportedEncodingException {
		AttendanceExceptionFile attendanceExceptionFile = attendanceExceptionFileService.getAttendanceExceptionFileByExceptionFileId(attendanceExceptionFileId);
		final HttpHeaders headers = new HttpHeaders();
		
		if (attendanceExceptionFile != null) {
			String[] mtypes = attendanceExceptionFile.getAttendanceExceptionFileContentType().split("/");
			headers.setContentType(new MediaType(mtypes[0], mtypes[1]));
			headers.setContentLength(attendanceExceptionFile.getAttendanceExceptionFileSize());
			headers.setContentDispositionFormData("attachment", URLEncoder.encode(attendanceExceptionFile.getAttendanceExceptionFileName(), "UTF-8"));
			return new ResponseEntity<byte[]>(attendanceExceptionFile.getAttendanceExceptionFileData(), headers, HttpStatus.OK);
		}		
		return new ResponseEntity<byte[]>(HttpStatus.NOT_FOUND);
	}
}
