package com.oti.myuniversity.component;

import static com.oti.myuniversity.common.Consts.logCount;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.oti.myuniversity.domain.attendance.model.Attendance;
import com.oti.myuniversity.domain.attendance.model.AttendanceException;
import com.oti.myuniversity.domain.attendance.model.AttendanceExceptionFile;
import com.oti.myuniversity.domain.member.model.Member;

@Component
@Aspect
@Order(2)
public class ServiceLogAspect {
	//private static int logCount = 0;

	@Pointcut(value="execution(public void com.oti.myuniversity..*.*Service.*(..))")
	private void logPointcut() {}
	
	@Pointcut(value="execution(public int com.oti.myuniversity..*.*Service.*(..))")
	private void returnIntPointcut() {}
	
	@Pointcut(value="execution(public com.oti.myuniversity.domain.attendance.model.Attendance com.oti.myuniversity..*.*Service.*(..))")
	private void returnAttendancePointcut() {}
	
	@Pointcut(value="execution(public com.oti.myuniversity.domain.member.model.Member com.oti.myuniversity..*.*Service.*(..))")
	private void returnMemberPointcut() {}
	
	@Pointcut(value="execution(public com.oti.myuniversity.domain.board.model.Board com.oti.myuniversity..*.*Service.*(..))")
	private void returnBoardPointcut() {}

	@Pointcut(value="execution(public com.oti.myuniversity.domain.attendance.model.AttendanceException com.oti.myuniversity..*.*Service.*(..))")
	private void returnAttendanceExceptionPointcut() {}
	
	@Pointcut(value="execution(public com.oti.myuniversity.domain.attendance.model.AttendanceExceptionFile com.oti.myuniversity..*.*Service.*(..))")
	private void returnAttendanceExceptionFilePointcut() {}
	
	@Before("logPointcut()")
	public void beforeLog(JoinPoint joinPoint) {
		MethodSignature signature = (MethodSignature) joinPoint.getSignature();
		String typeName = signature.getDeclaringType().getSimpleName();
		if ('I' == typeName.charAt(0)) {
			typeName = typeName.substring(1);			
		}
		//logCount.set(logCount.get() + 1);
		logCount++;
		System.out.println("----------------------------------------");
		System.out.println("[log No." + logCount + "]");
		System.out.println("[Class] " + typeName);
		System.out.println("[Method] " + signature.getName() + "() - executed");
		System.out.println("----------------------------------------");
	}
	
	@AfterReturning(value="returnIntPointcut()", returning="num")
	public void returnIntLog(JoinPoint joinPoint, int num) {
		MethodSignature signature = (MethodSignature) joinPoint.getSignature();
		String typeName = signature.getDeclaringType().getSimpleName();
		if ('I' == typeName.charAt(0)) {
			typeName = typeName.substring(1);			
		}
		//logCount.set(logCount.get() + 1);
		logCount++;
		System.out.println("----------------------------------------");
		System.out.println("[log No." + logCount + "]");
		System.out.println("[Class] " + typeName);
		System.out.println("[Method] " + signature.getName() + "() - return int");
		System.out.println(num);
		System.out.println("----------------------------------------");
	}
	
	@AfterReturning(value="returnAttendancePointcut()", returning="attendance")
	public void returnAttendanceLog(JoinPoint joinPoint, Attendance attendance) {
		MethodSignature signature = (MethodSignature) joinPoint.getSignature();
		String typeName = signature.getDeclaringType().getSimpleName();
		if ('I' == typeName.charAt(0)) {
			typeName = typeName.substring(1);			
		}
		//logCount.set(logCount.get() + 1);
		logCount++;
		System.out.println("----------------------------------------");
		System.out.println("[log No." + logCount + "]");
		System.out.println("[Class] " + typeName);
		if (attendance != null) {
			System.out.println("[Method] " + signature.getName() + "() - return Attendance");
			System.out.println(attendance.toString());
		}
		else {
			System.out.println("[Method] " + signature.getName() + "() - return NULL");
		}
		System.out.println("----------------------------------------");
	}
	
	@AfterReturning(value="returnMemberPointcut()", returning="member")
	public void returnMemberLog(JoinPoint joinPoint, Member member) {
		MethodSignature signature = (MethodSignature) joinPoint.getSignature();
		String typeName = signature.getDeclaringType().getSimpleName();
		if ('I' == typeName.charAt(0)) {
			typeName = typeName.substring(1);			
		}
		//logCount.set(logCount.get() + 1);
		logCount++;
		System.out.println("----------------------------------------");
		System.out.println("[log No." + logCount + "]");
		System.out.println("[Class] " + typeName);
		if (member != null) {			
			System.out.println("[Method] " + signature.getName() + "() - return Member");
			System.out.println(member.toString());
		}
		else {
			System.out.println("[Method] " + signature.getName() + "() - return NULL");
		}
		System.out.println("----------------------------------------");
	}
	
	@AfterReturning(value="returnAttendanceExceptionPointcut()", returning="attendanceException")
	public void returnAttendanceExceptionLog(JoinPoint joinPoint, AttendanceException attendanceException) {
		MethodSignature signature = (MethodSignature) joinPoint.getSignature();
		String typeName = signature.getDeclaringType().getSimpleName();
		if ('I' == typeName.charAt(0)) {
			typeName = typeName.substring(1);			
		}
		//logCount.set(logCount.get() + 1);
		logCount++;
		System.out.println("----------------------------------------");
		System.out.println("[log No." + logCount + "]");
		System.out.println("[Class] " + typeName);
		if (attendanceException != null) {
			System.out.println("[Method] " + signature.getName() + "() - return AttendanceException");
			System.out.println(attendanceException.toString());
		}
		else {
			System.out.println("[Method] " + signature.getName() + "() - return NULL");			
		}
		System.out.println("----------------------------------------");
	}
	
	@AfterReturning(value="returnAttendanceExceptionFilePointcut()", returning="attendanceExceptionFile")
	public void returnAttendanceExceptionFileLog(JoinPoint joinPoint, AttendanceExceptionFile attendanceExceptionFile) {
		MethodSignature signature = (MethodSignature) joinPoint.getSignature();
		String typeName = signature.getDeclaringType().getSimpleName();
		if ('I' == typeName.charAt(0)) {
			typeName = typeName.substring(1);			
		}
		//logCount.set(logCount.get() + 1);
		logCount++;
		System.out.println("----------------------------------------");
		System.out.println("[log No." + logCount + "]");
		System.out.println("[Class] " + typeName);
		if (attendanceExceptionFile != null) {
			System.out.println("[Method] " + signature.getName() + "() - return AttendanceExceptionFile");
			System.out.println(attendanceExceptionFile.toString());
		}
		else {
			System.out.println("[Method] " + signature.getName() + "() - return NULL");			
		}
		System.out.println("----------------------------------------");
	}
}
