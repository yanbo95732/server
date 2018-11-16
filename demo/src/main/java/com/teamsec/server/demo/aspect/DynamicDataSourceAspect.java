/**
 * 
 */
package com.teamsec.server.demo.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.teamsec.server.demo.config.DataSourceContextHolder;

/**
 * @author admin
 *
 */
@Aspect
@Component
public class DynamicDataSourceAspect {
	//
	private static final Logger log = LoggerFactory.getLogger(DynamicDataSourceAspect.class);

	@Pointcut("execution(* com.teamsec.server.demo..*.*(..))")
	private void aspect() {
	}

	@Around("aspect()")
	public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
		String method = joinPoint.getSignature().getName();

		if (method.startsWith("find") || method.startsWith("select") || method.startsWith("query")
				|| method.startsWith("search")) {
			DataSourceContextHolder.setDataSource("slave");
			log.info("switch to slave datasource...");
		} else {
			DataSourceContextHolder.setDataSource("master");
			log.info("switch to master datasource...");
		}

		try {
			return joinPoint.proceed();
		} finally {
			log.info("清除 datasource router...");
			DataSourceContextHolder.clear();
		}
	}
}
