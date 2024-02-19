package com.baeldung.example.trackperformance;


import java.util.Arrays;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class PerforamanceLoggingAspect {

	Logger logger = LoggerFactory.getLogger(PerforamanceLoggingAspect.class);
	
	@Pointcut("within(com.baeldung..*) && @annotation(com.baeldung.example.trackperformance.TrackPerformance)")
	//@Pointcut("within(com.baeldung..*) && execution(* com.baeldung.pointcutadvice.dao.FooDao.*(..))")
	//@Pointcut("within(com.baeldung..*) && @annotation(com.baeldung.pointcutadvice.annotations.Loggable)")
	public void trackPerformancePointCut() { }
	 
	@Around("trackPerformancePointCut()")
	public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
		String methodName = joinPoint.getSignature().getName();
		try {
			String classname = joinPoint.getSignature().getDeclaringTypeName();
			String methodname = joinPoint.getSignature().getName();
			long start = System.currentTimeMillis();
			Object result = joinPoint.proceed();
			long elapsedTime = System.currentTimeMillis() - start;
			
			logger.info("Method {}, {}() completed. Actual time: {} ms", classname, methodname, elapsedTime);
			
			return result;
		} catch (IllegalArgumentException e) {
			logger.error("Illegal arguments {} in {}()", Arrays.toString(joinPoint.getArgs()), joinPoint.getSignature().getName());
			throw e;
		}
	}
	
}
