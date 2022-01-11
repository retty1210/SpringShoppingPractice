package com.shoppingmall.web.aop;

import javax.servlet.http.HttpSession;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import com.shoppingmall.web.account.AccountVO;

@Component
@Aspect
public class PermissionAOP {

	/*
	 * service 객체에서 메서드 명이 insert, update, delete에 해당하는 
	 * 메서드들에 대해 Permission을 검사하는 AOP를 만든다.
	 * 
	 * 계정별(USERTYPE으로 만들었음) 권한 정보가 담겨있는 테이블이 필요.
	 * 
	 */
	
	//insert
	@Pointcut(value="execution(* com.shoppingmall.web..*Service.join*(..) )")
	private void joinPMCut() {}
	@Pointcut(value="execution(* com.shoppingmall.web..*Service.upload*(..) )")
	private void upPMCut() {}
	@Pointcut(value="execution(* com.shoppingmall.web..*Service.add*(..) )")
	private void addPMCut() {}
	
	//select : 근데 이건 안쓸것
	@Pointcut(value="execution(* com.shoppingmall.web..*Service.select*(..) )")
	private void selectPMCut() {}
	@Pointcut(value="execution(* com.shoppingmall.web..*Service.view*(..) )")
	private void viewPMCut() {}
	
	//update: 분명 구현한거같은데 다 어디갔지..
	//delete기능 아직 구현안함
	
	@Before("joinPMCut() || upPMCut() || addPMCut()")
	public void insertPMBefore(JoinPoint jp) throws Exception {
		HttpSession session = this.getSession(jp);
		AccountVO data = (AccountVO) session.getAttribute("account");
		//PermissionDTO 객체 생성
		//어느 테이블의 정보를 볼 것인지 지정
		//service에서 표에서 account의 permission권한 조회 후 false이면
		//throw new Exception("삭제 권한이 없습니다");
	}
	
	private HttpSession getSession(JoinPoint jp) {
		HttpSession session = null;
		for(Object arg: jp.getArgs()) {
			if(arg instanceof HttpSession) {
				session = (HttpSession)arg;
			}
		}
		return session;
	}
}
