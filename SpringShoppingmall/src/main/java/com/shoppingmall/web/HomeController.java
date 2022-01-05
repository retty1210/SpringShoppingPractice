package com.shoppingmall.web;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model, HttpServletRequest request, HttpServletResponse response) {
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		String formattedDate = dateFormat.format(date);
		model.addAttribute("serverTime", formattedDate );
		logger.info("Welcome home! The client time is {}.", formattedDate);
		
		Cookie[] cookies = request.getCookies();
		Cookie cart = null;
		for(Cookie c: cookies) {
			if(c.getName().equals("cart")) {
				cart = c;
			}
		}
		if(cart == null) {
			cart = new Cookie("cart", "");
		}
		cart.setMaxAge(60*60*24*14); //쿠키 갱신기간 2주
		cart.setPath(request.getContextPath());
		response.addCookie(cart);
		
		return "home";
	}
	
}
