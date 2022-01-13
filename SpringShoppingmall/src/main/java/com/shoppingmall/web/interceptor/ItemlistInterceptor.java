package com.shoppingmall.web.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class ItemlistInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		System.out.println("ItemlistInterceptor PostHandle");
		if(usertype(request).equals("admin")) {
			modelAndView.setViewName("admin/" + modelAndView.getViewName());
		}
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		System.out.println("ItemlistInterceptor Aftercomp");

	}
	
	public String usertype(HttpServletRequest request) {
		HttpSession session = request.getSession();
		String res = "";
		if(session.getAttribute("usertype") != null) {
			res = session.getAttribute("usertype").toString();
			System.out.println("ItemlistInterceptor, usertype: " + res);
		} else {
			res = "notlogined";
		}
		return res;
	}

}
