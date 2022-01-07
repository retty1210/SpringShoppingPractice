package com.shoppingmall.web.account;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shoppingmall.web.common.CommonTools;

@Controller
public class AccountController {
	
	private static final Logger logger = LoggerFactory.getLogger(AccountController.class);

	@Autowired
	private AccountService service;
	
	@RequestMapping(value = "/join", method = RequestMethod.GET)
	public String join() {
		return "accounts/join";
	}
	
	@RequestMapping(value = "/usertype", method = RequestMethod.POST)
	public String usertype(AccountVO accountVo) {
		System.out.println("usertype: " + accountVo.getUsertype());
		if(accountVo.getUsertype().equals("seller")) {
			return "accounts/joinseller";
		} else if(accountVo.getUsertype().equals("buyer")) {
			return "accounts/joinbuyer";
		} else {
			System.out.println("usertype값 error");
			return "accounts/join";
		}
	}

	@RequestMapping(value = "/joinseller", method = RequestMethod.POST)
	public String joinseller(AccountVO accountVo, Model model) {
		accountVo.setUsertype("seller");
		boolean res = false;
		if(service.joinCheck(accountVo)) {
			res = service.joinSeller(accountVo);
		} else {
			model.addAttribute("isError", true);
			model.addAttribute("error_msg", "아이디나 이메일이 중복되었습니다.");
			System.out.println("아이디나 이메일이 중복되었습니다.");
			return "redirect:/join";
		}
		if(res) {
			model.addAttribute("alert", true);
			model.addAttribute("alert_msg", "회원가입 성공, 로그인 페이지로 이동합니다.");
			System.out.println("회원가입 성공, 로그인 페이지로 이동합니다");
			return "accounts/login";
		} else {
			model.addAttribute("isError", true);
			model.addAttribute("error_msg", "회원가입 실패, 다시 회원가입 페이지로 이동합니다");
			return "redirect:/join";
		}
	}
	
	@RequestMapping(value = "/joinbuyer", method = RequestMethod.POST)
	public String joinbuyer(AccountVO accountVo, Model model, HttpServletRequest request) {
		accountVo.setUsertype("buyer");
		String address = request.getParameter("address1") + " " + request.getParameter("address2") + " " + request.getParameter("address3");
		accountVo.setAddress(address);
		boolean res = service.joinBuyer(accountVo);
		if(res) {
			model.addAttribute("alert", true);
			model.addAttribute("alert_msg", "회원가입 성공, 로그인 페이지로 이동합니다.");
			System.out.println("회원가입 성공, 로그인 페이지로 이동합니다");
			return "accounts/login";
		} else {
			model.addAttribute("isError", true);
			model.addAttribute("error_msg", "회원가입 실패, 다시 회원가입 페이지로 이동합니다");
			return "redirect:/join";
		}
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login() {
		return "accounts/login";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(@ModelAttribute("account") LoginVO loginVo, HttpSession session, Model model) {
		System.out.println("username: " + loginVo.getUsername());
		System.out.println("password: " + loginVo.getPassword());
		AccountVO data = service.login(loginVo);
		if(data != null) {
			System.out.println("로그인 성공, 메인 페이지로 이동합니다");
			session.setAttribute("logined", true);
			data.setPassword("");
			session.setAttribute("account", data);
			session.setAttribute("usertype", data.getUsertype());
			return "redirect:/";
		} else {
			System.out.println("로그인 실패, 다시 로그인 페이지로 이동합니다");
			model.addAttribute("isError", true);
			model.addAttribute("error_msg", "아이디 또는 패스워드가 잘못되었습니다.");
			return "redirect:/login";
		}
	}
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpSession session) {
		session.invalidate();
		
		System.out.println("로그아웃 성공, 메인 페이지로 이동합니다");
		return "redirect:/";
	}
	
//	@RequestMapping(value="/join/chk-username", method=RequestMethod.POST, produces="application/json; charset=uft-8")
//	@ResponseBody
//	public String usernameCheck(String username) {
//		//중복체크 서비스 동작
//		boolean res = false;
//		
//		JSONObject json = new JSONObject();
//		if(res) {
//			json.put("state", "fail");
//			json.put("msg", "아이디가 중복되었습니다.");
//		} else {
//			json.put("state", "success");
//			
//		}
//		return json.toJSONString();
//	}

}
