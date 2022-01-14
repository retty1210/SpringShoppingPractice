package com.shoppingmall.web.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class PaymentInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		if(!isLogined(request)) {
			modelAndView.setViewName("notjoined/" + modelAndView.getViewName());
		}

	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub

	}
	
	public boolean isLogined(HttpServletRequest request) {
		HttpSession session = request.getSession();
		boolean res = false;
		if(session.getAttribute("logined") != null) {
			boolean logined = (boolean) session.getAttribute("logined");
			if(logined) {
				res = true;
			}
		}
		return res;
	}

}
