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
	
	public boolean paycheck(OrderVO vo) {
		boolean res = dao.paycheck(vo);
		return res;
	}
	
	public boolean insertOrder(OrderVO vo) {
		boolean res = dao.makeOrder(vo);
		return res;
	}
	
	public String orderNo(OrderVO vo) {
		OrderVO data = dao.selectOrder(vo);
		return data.getOrderno();
	}
	
	public List<OrderstatVO> orderstats() {
		List<OrderstatVO> datas = dao.selectOrderstats();
		return datas;
	}
	
	public OrderVO updateOrderstat(OrderVO vo) {
		if(dao.updateOrderstat(vo)) {
			int res = vo.getId();
			OrderVO data = dao.selectOrder(res);
			return data;
		} else {
			return vo;
		}
	}
	
//	public List<OrderVO> selectSellerOrder(AccountVO vo) {
//		//1. 모든 order의 item리스트 받아와서(서버부하 무엇?) sellername으로 분류
//		//2. seller ID를 FK로 하는 테이블을 만들어서 주문시 orderid/orderno/sellerid/itemlist/insert
//	}
	

}
