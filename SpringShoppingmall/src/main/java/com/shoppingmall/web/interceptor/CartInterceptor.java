package com.shoppingmall.web.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class CartInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String usertype = getUsertype(request);
		if(usertype.equals("admin") || usertype.equals("seller")) {
			HttpSession session = request.getSession();
			session.setAttribute("error", true);
			session.setAttribute("error_msg", "카트에는 관리자 / 판매자 회원은 접근할 수 없습니다.");
			response.sendRedirect("/");
			return false;
		} else {
			return true;
		}
		
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub

	}
	
	public String getUsertype(HttpServletRequest request) {
		HttpSession session = request.getSession();
		String usertype = "";
		if(session.getAttribute("usertype") != null) {
			usertype = session.getAttribute("usertype").toString();
		}
		return usertype;
	}

}
