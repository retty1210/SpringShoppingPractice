package com.shoppingmall.web.order;

import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shoppingmall.web.account.*;
import com.shoppingmall.web.common.CommonTools;
import com.shoppingmall.web.item.model.*;
import com.shoppingmall.web.order.model.*;

@Controller
public class OrderController {
	
	private static final Logger logger = LoggerFactory.getLogger(AccountController.class);

	@Autowired
	private OrderService service;
	
	private CommonTools ct;
	
	@Autowired
	private ItemService iservice;
	
	
	@RequestMapping(value = "/buy", method = RequestMethod.POST)
	public String itemlist(OrderVO vo, HttpServletRequest request, HttpSession session) {
		String ress = vo.getOrderlist();
		System.out.println("res: " + ress);
		int[] ilist = ct.makeIntList(ress);
		int res = service.getPriceAll(ilist);
		session.setAttribute("priceAll", res);
		if(res >= 30000) {
			session.setAttribute("payPrice", res);
		} else {
			res += 3000;
			session.setAttribute("payPrice", res);
		}
		List<ItemVO> data = iservice.viewCart(ilist);
		session.setAttribute("datas", data);
		
		session.setAttribute("ordervo", vo);
		return "order/buy";
	}
	
	@RequestMapping(value="/payment", method = RequestMethod.POST)
	public String payment(OrderVO vo, HttpSession session, HttpServletResponse response, HttpServletRequest request) {
		String paym = vo.getPaymethod();
		if(paym.equals("bank")) {
			vo.setOrderstate(1);
			String orderno = ct.generateOrderno();
			System.out.println("주문번호: " + orderno);
			vo.setOrderno(orderno);
			boolean res = service.insertOrder(vo);
			if(res) {
				if(service.insertSellectOrder(vo)) {
					session.setAttribute("orderno", orderno);
					Cookie[] cookies = request.getCookies();
					for(Cookie c: cookies) {
						if(c.getName().equals("cart")) {
							c.setMaxAge(0);
							response.addCookie(c);
						}
					}
					return "order/payaccount";
				} else {
					session.setAttribute("error_msg", "주문과정에서 서버에 오류가 발생했습니다.");
					return "order/fail";
				}
			} else {
				session.setAttribute("error_msg", "주문과정에서 서버에 오류가 발생했습니다.");
				return "order/fail";
			}
		} else if(paym.equals("card")) {
			boolean payres = service.paycheck(vo);
			if(payres) {
				vo.setOrderstate(2);
				String orderno = ct.generateOrderno();
				System.out.println("주문번호: " + orderno);
				vo.setOrderno(orderno);
				boolean res = service.insertOrder(vo);
				if(res) {
					if(service.insertSellectOrder(vo)) {
						session.setAttribute("orderno", orderno);
						Cookie[] cookies = request.getCookies();
						for(Cookie c: cookies) {
							if(c.getName().equals("cart")) {
								c.setMaxAge(0);
								response.addCookie(c);
							}
						}
						return "order/paycard";
					} else {
						session.setAttribute("error_msg", "주문과정에서 서버에 오류가 발생했습니다.");
						return "order/fail";
					}
				} else {
					session.setAttribute("error_msg", "주문과정에서 서버에 오류가 발생했습니다.");
					return "order/fail";
				}
			} else {
				session.setAttribute("error_msg", "결제가 실패했습니다.");
				return "order/fail";
			}
			
		} else {
			session.setAttribute("error_msg", "결제수단 선택 과정에서 오류가 발생했습니다.");
			return "order/fail";
		}
	}
	
	@RequestMapping(value="/orders", method = RequestMethod.GET)
	public String orders(OrderVO vo, HttpSession session, HttpServletResponse response, HttpServletRequest request) {
		String usertype = "";
		String username = "";
		if(session.getAttribute("usertype") != null) {
			usertype = session.getAttribute("usertype").toString();
			username = session.getAttribute("username").toString();
		}
		
		List<OrderstatVO> orderstats = service.orderstats();
		session.setAttribute("orderstats", orderstats);
		
		if(usertype.equals("seller")) {
			System.out.println("seller");
			List<OrderAndSellerVO> datas = service.selectOrderSeller(username);
			session.setAttribute("datas", datas);
		} else if(usertype.equals("buyer")) {
			List<OrderVO> datas = service.selectOrderBuyer(username);
			session.setAttribute("datas", datas);
		} else if(usertype.equals("admin")) {
			List<OrderVO> datas = service.selectOrderAdmin();
			session.setAttribute("datas", datas);
		} else {
			System.out.println("notjoined");
		}
		
		return "order/list";
	}
	
	@RequestMapping(value="/orders/detail", method = RequestMethod.GET)
	public String orderdetail(OrderVO vo, HttpSession session) {
		OrderVO data = service.selectOrder(vo);
		String slist = data.getOrderlist();
		List<ItemVO> datas = service.getItemlist(slist);
		
		session.setAttribute("data", data);
		session.setAttribute("datas", datas);
		return "order/detail";
	}
	
	@RequestMapping(value="/notloginorder", method = RequestMethod.POST)
	public String notLoginOrderlist(OrderVO vo, HttpSession session) {
		OrderVO data = service.selectOrder(vo);
		String slist = data.getOrderlist();
		List<ItemVO> datas = service.getItemlist(slist);
		session.setAttribute("data", data);
		session.setAttribute("datas", datas);
		return "order/detail";
	}
	
	@RequestMapping(value="/changeorderstat", method = RequestMethod.POST, produces="application/json; charset=utf-8")
	@ResponseBody
	public String changeOrderstat(@RequestParam(value="selectstat") int userstat, 
			@RequestParam(value="id") int orderid,
			@RequestParam(value="orgstat") int orgstat) {
		System.out.println("주문id: " + orderid + " | 선택단계: " + userstat);
		boolean res = service.updateOrderstat(userstat, orderid);
		JSONObject json = new JSONObject();
		if(res) {
			json.put("selectstat", userstat);
		} else {
			json.put("selectstat", orgstat);
		}
		return json.toJSONString();
	}
}
