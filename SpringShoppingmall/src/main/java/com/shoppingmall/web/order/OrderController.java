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
			vo.setPrice(res);
		} else {
			vo.setPrice(res + 3000);
		}
		return "order/buy";
	}
}
