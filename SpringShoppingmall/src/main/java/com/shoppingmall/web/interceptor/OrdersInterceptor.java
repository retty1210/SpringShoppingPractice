package com.shoppingmall.web.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class OrdersInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		System.out.println("postHandle");
//		HttpSession session = request.getSession();
//		session.setAttribute("notjoined", true);
		if(!isLogined(request)) {
			modelAndView.setViewName("notjoined/" + modelAndView.getViewName());
		} else if(getUsertype(request).equals("buyer")) {
			modelAndView.setViewName("buyer/" + modelAndView.getViewName());
		} else if(getUsertype(request).equals("admin")) {
			modelAndView.setViewName("admin/" + modelAndView.getViewName());
		}
		
//		response.sendRedirect("/orders");
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		System.out.println("afterCompletion");
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
	
	public String getUsertype(HttpServletRequest request) {
		HttpSession session = request.getSession();
		String usertype = "";
		if(session.getAttribute("usertype") != null) {
			usertype = session.getAttribute("usertype").toString();
		}
		return usertype;
	}

}
