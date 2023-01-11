package com.oti.myuniversity.servletcomponent;

import static com.oti.myuniversity.common.Consts.logCount;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Order(1)
public class LogAspect {
	//private static int logCount = 0;

	@Pointcut(value="execution(* com.oti.myuniversity.domain..*.*Controller.*(..)) || execution(* com.oti.myuniversity.domain.HomeController.*(..))")
	private void logPointcut() {}
	
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

}
