package com.shoppingmall.web.order.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shoppingmall.web.common.CommonTools;
import com.shoppingmall.web.item.model.ItemDAO;
import com.shoppingmall.web.item.model.ItemVO;

@Service
public class OrderService {
	
	@Autowired
	private OrderDAO dao;
	private ItemDAO itemdao;
	private CommonTools ct;
	
	public int getPriceAll(List<ItemVO> ilist) {
		int res = 0;
		for(ItemVO i : ilist) {
			res += i.getPrice();
		}
		return res;
	}
	

}
