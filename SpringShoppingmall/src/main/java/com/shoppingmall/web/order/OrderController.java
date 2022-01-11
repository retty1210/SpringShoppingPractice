package com.shoppingmall.web.order;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.shoppingmall.web.account.AccountController;
import com.shoppingmall.web.item.model.ItemVO;
import com.shoppingmall.web.order.model.OrderService;
import com.shoppingmall.web.order.model.OrderVO;

@Controller
public class OrderController {
	
	private static final Logger logger = LoggerFactory.getLogger(AccountController.class);

	@Autowired
	private OrderService service;
	
	@RequestMapping(value = "/buy", method = RequestMethod.GET)
	public String itemlist(OrderVO vo, HttpServletRequest request, HttpSession session) {
		List<ItemVO> datas = (List<ItemVO>) session.getAttribute("datas");
		int res = service.getPriceAll(datas);
		session.setAttribute("priceAll", res);
		if(res >= 30000) {
			session.setAttribute("payPrice", res);
		} else {
			res += 3000;
			session.setAttribute("payPrice", res);
		}
		return "order/buy";
	}
	
	@RequestMapping(value="/payment", method = RequestMethod.POST)
	public String payment(OrderVO vo, HttpSession session) {
		String paym = vo.getPaymethod();
		if(paym.equals("bank")) {
			vo.setOrderstate(1);
			boolean res = service.insertOrder(vo);
			if(res) {
				String no = service.orderNo(vo);
				session.setAttribute("orderno", no);
				return "order/payaccount";
			} else {
				session.setAttribute("error_msg", "주문과정에서 서버에 오류가 발생했습니다.");
				return "order/fail";
			}
		} else if(paym.equals("card")) {
			boolean payres = service.paycheck(vo);
			if(payres) {
				vo.setOrderstate(2);
				boolean res = service.insertOrder(vo);
				if(res) {
					String no = service.orderNo(vo);
					session.setAttribute("orderno", no);
					return "order/paycard";
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
}
