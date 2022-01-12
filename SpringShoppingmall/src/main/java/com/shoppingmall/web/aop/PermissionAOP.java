package com.shoppingmall.web.aop;

import java.lang.reflect.Method;

import javax.servlet.http.HttpSession;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.PermissionDeniedDataAccessException;
import org.springframework.stereotype.Component;

import com.shoppingmall.web.account.AccountVO;
import com.shoppingmall.web.aop.annotation.Perm;
import com.shoppingmall.web.permission.model.PermissionService;
import com.shoppingmall.web.permission.model.PermissionVO;

@Component
@Aspect
public class PermissionAOP {

	/*
	 * service 객체에서 메서드 명이 insert, update, delete에 해당하는 
	 * 메서드들에 대해 Permission을 검사하는 AOP를 만든다.
	 * 
	 * 계정별(USERTYPE으로 만들었음) 권한 정보가 담겨있는 테이블이 필요.(VO도)
	 * 
	 */
	
	@Autowired
	PermissionService service;
	
	@Before("@annotation(com.shoppingmall.web.aop.annotation.Perm)")
	public void getPerm(JoinPoint jp) throws Exception {
		MethodSignature mSignature = (MethodSignature) jp.getSignature();
		Method method = mSignature.getMethod();
		
		Perm perm = method.getAnnotation(Perm.class);
		String table = perm.table();
		String crud = perm.crud();
		
		System.out.println("table: " + table);
		System.out.println("crud: " + crud);
		//@Perm(table="members", crud="insert") <- 이 어노테이션을 넣으면
		//  members 테이블에 insert 권한이 있는지 getPerm에서 체크해줌.
		throw new PermissionDeniedDataAccessException(table + "." + crud + " : 권한이 없습니다.", null);
		//throw new TransientDataAccessResourceException(null);리소스가 일시적으로 실패, 재시작 가능할때->강사님은 AccountService의 join에 사용
	}
//	
//	//insert
//	@Pointcut(value="execution(* com.shoppingmall.web..*Service.join*(..) )")
//	private void joinPMCut() {}
//	@Pointcut(value="execution(* com.shoppingmall.web..*Service.upload*(..) )")
//	private void upPMCut() {}
//	@Pointcut(value="execution(* com.shoppingmall.web..*Service.add*(..) )")
//	private void addPMCut() {}
//	
//	//select : 근데 이건 안쓸것
//	@Pointcut(value="execution(* com.shoppingmall.web..*Service.select*(..) )")
//	private void selectPMCut() {}
//	@Pointcut(value="execution(* com.shoppingmall.web..*Service.view*(..) )")
//	private void viewPMCut() {}
//	
//	//update: 분명 구현한거같은데 다 어디갔지..
//	//delete기능 아직 구현안함
//	
//	@Before("joinPMCut() || upPMCut() || addPMCut()")
//	public void insertPMBefore(JoinPoint jp) throws Exception {
//		HttpSession session = this.getSession(jp);
//		AccountVO data = (AccountVO) session.getAttribute("account");
//		PermissionVO permvo = new PermissionVO();
//		permvo.setTablename("SHOP_ACCOUNTS");
//		permvo.setUsertype(data.getUsertype());
//		if(!service.existedDeletePerm(permvo)) {
//			throw new Exception("삭제 권한이 없습니다");
//		}
//	}
//	
//	private HttpSession getSession(JoinPoint jp) {
//		HttpSession session = null;
//		for(Object arg: jp.getArgs()) {
//			if(arg instanceof HttpSession) {
//				session = (HttpSession)arg;
//			}
//		}
//		return session;
//	}
}
