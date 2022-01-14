package com.shoppingmall.web.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class WishlistInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String usertype = getUsertype(request);
		
		if(usertype.equals("buyer")) {
			return true;
		} else {
			HttpSession session = request.getSession();
			session.setAttribute("error", true);
			session.setAttribute("error_msg", "즐겨찾기는 구매자 회원만 사용가능한 기능입니다.");
			response.sendRedirect("/");
			return false;
		}
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {

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
