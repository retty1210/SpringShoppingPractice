package com.shoppingmall.web.order.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shoppingmall.web.common.CommonTools;
import com.shoppingmall.web.item.model.*;

@Service
public class OrderService {
	
	@Autowired
	private OrderDAO dao;
	
	@Autowired
	private ItemService iservice;
	private CommonTools ct;
	
	public int getPriceAll(List<ItemVO> ilist) {
		int res = 0;
		for(ItemVO i : ilist) {
			res += i.getPrice();
		}
		return res;
	}
	
	public int getPriceAll(int[] list) {
		int res = 0;
		List<ItemVO> ilist = iservice.viewCart(list);
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
		
		OrderVO data = dao.selectOrder(vo.getId());
		String res = data.getOrderno();
		System.out.println("Orderno: " + res);
		return res;
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
	
	public boolean insertSellectOrder(OrderVO vo) {
		boolean res = false;
		//1. ordervo의 orderlist에서 int[]값 추출
		int[] ilist = ct.makeIntList(vo.getOrderlist()); 
		
		//2. where id in(ids) 로 List<ItemVO> 만듬 - viewcart
		List<ItemVO> items = iservice.viewCart(ilist);
		
		//3. ItemVO.getSellername으로 String array만듬
		List<String> sarr = new ArrayList<String>();
		for(ItemVO i : items) {
			sarr.add(i.getSellername());
		}
		
		//4. for문으로 중복 제거한 새로운 String array만듬 - sellername값
		List<String> newsarr = ct.stringArrDup(sarr);

		//5. array의 값을 for문으로 하나씩 쿼리select해서 VO값 만들고 리스트에 추가해줌
		for(String seller: newsarr) {
			//추가)sellername별로 itemVO id값 리스트 구함. 
			int[] orgarr = dao.itemIDlist(seller);
			//    ilist랑 새로 구한 리스트 대조시켜서 중복되는 값 결과리스트에 추가.
			//    ->sellername 하나당 전체id값리스트 하나, 주문값중 해당되는 id리스트 하나 총 두개를 갖게됨.
			int[] resarr = ct.checkIntarr(ilist, orgarr);
			//	  ->해당되는 id리스트를 string화해준 다음 OrderSellerVO 생성.
			String idlist = ct.intArrToString(resarr, "_");
			//  추가의 추가)sellername으로 account에서 userid 찾아줌
			int sellerid = dao.findUserid(seller);
			int orderid = dao.findOrderid(vo);
			System.out.println("id: " + orderid + " | sellerid: " + sellerid + " | orderno: " + vo.getOrderno() + " | list: " + idlist);
			OrderSellerVO osvo = new OrderSellerVO(
					orderid, sellerid, vo.getOrderno(), idlist);
			//    ->쿼리인서트
			res = dao.insertOrderSeller(osvo);
			
		}
		
		//6. 리턴
		return res;
	}
	
	

}
