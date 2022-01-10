package com.shoppingmall.web.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggingAOP {
	private static Logger logger = LoggerFactory.getLogger(LoggingAOP.class);

//	@Pointcut(value="execution(public String com.shoppingmall.web.HomeController.*(..))")
//	private void homeLogCut() {}
//	
//	
//	@Pointcut(value="execution(public String com.shoppingmall.web.account.AccountController.join*(..))")
//	private void accountJoinLogCut() {}
//	
//	@Pointcut(value="execution(public String com.shoppingmall.web.account.AccountController.login*(..))")
//	private void accountLoginLogCut() {}
	
	@Pointcut(value="execution(* com.shoppingmall.web..*Controller.*(..) )")
	private void controllerLoggingCut() {}
	
	@Pointcut(value="execution(* com.shoppingmall.web..*Service.*(..) )")
	private void serviceLoggingCut() {}
	
	@Pointcut(value="execution(* com.shoppingmall.web..*DAO.*(..) )")
	private void daoLoggingCut() {}
	
	
	private String getJoinPointName(JoinPoint jp) {
		String pkgName = jp.getThis().getClass().getPackage().getName();
		String clsName = jp.getThis().getClass().getSimpleName().split("\\$\\$")[0];
		return pkgName + "." + clsName;
	}
	
	private String toStringParameters(JoinPoint jp) {
		StringBuilder sb = new StringBuilder();
		MethodSignature method = (MethodSignature) jp.getSignature();
		//getSignature: 시그니쳐 타입의 자료가 반환
		//MethodSignature: 이걸로 다운캐스팅하면 파라미터타입, 파라미터명을 쓸 수 있게 해줌
		
		for(int i = 0; i < jp.getArgs().length; i++) {
			String[] typNames = method.getParameterTypes()[i].getTypeName().split("\\.");
			sb.append(typNames[typNames.length - 1] + " ");
			sb.append(method.getParameterNames()[i] + ": ");
			sb.append(jp.getArgs()[i]);
			
			if(i + 1 != jp.getArgs().length) {
				sb.append(", ");
			}
		}
		return sb.toString();
	}
	
	
	@Before("controllerLoggingCut() || serviceLoggingCut() || daoLoggingCut()")
	public void loggingBefore(JoinPoint jp) {
		String mthName = jp.getSignature().getName();
		this.printLog(jp, mthName, "Before");
	}
	
	@After("controllerLoggingCut() || serviceLoggingCut() || daoLoggingCut()")
	public void loggingAfter(JoinPoint jp) {
		String mthName = jp.getSignature().getName();
		this.printLog(jp, mthName, "After");
	}
	
	private void printLog(JoinPoint jp, String mthName, String advPoint) {
		logger = LoggerFactory.getLogger(this.getJoinPointName(jp)); 
		//특정클래스가 아니라 핵심기능 동작에 따른 클래스 구분이 필요하고 그 구분에 따라 로거팩토리가 만들어져야 해서 이런식으로 코드를 짠 것.
		//공통기능으로 동작할 수 있게 만드는 것.
		logger.debug("@" + advPoint + " " + mthName + "(" + this.toStringParameters(jp) + ")");
		//advPoint: 패키지&클래스
		//mthName: 메서드명
		//파라미터타입, 파라미터명, 객체 출력
	}
	
//	@After("homeLogCut()")
//	private void homeLogAfter(JoinPoint jp) {
//		logger.info("HOME");
//	}
//	
//	@AfterThrowing("homeLogCut()")
//	private void homeLogAfterThrow(JoinPoint jp) {
//		logger.debug("HOME ERROR: " + jp.getSignature());
//	}
//	
//	
//	@Before("accountJoinLogCut()")
//	private void joinLogBefore(JoinPoint jp) {
//		logger.info("JOIN: " + jp.getStaticPart());
//	}
//	
//	@After("accountJoinLogCut()")
//	private void joinLogAfter(JoinPoint jp) {
//		logger.info("JOINTYPE: " + jp.getArgs()[0].toString());
//	}
//	
//	@AfterThrowing("accountJoinLogCut()")
//	private void joinLogAfterThrow(JoinPoint jp) {
//		logger.debug("JOIN ERROR: ");
//	}
	
}
