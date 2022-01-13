package com.shoppingmall.web.order.model;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.shoppingmall.web.account.AccountVO;
import com.shoppingmall.web.common.CommonTools;
import com.shoppingmall.web.item.model.ItemVO;

@Repository
public class OrderDAO {
	
	@Autowired
	private SqlSession sess;
	private CommonTools ct;
	
	public boolean makeOrder(OrderVO vo) {
		int res = this.sess.insert("OrderMapper.makeOrder", vo);
		return res == 1 ? true : false;
	}
	
	public boolean paycheck(OrderVO vo) {
		//pay기능 호출한 뒤 거기에 vo 값 중 결제금액값을 넣어줌
		//pay에서 결제성공여부를 boolean으로 알려주지 않을까?아무튼 그걸 받아와서 띄워줌
		//나는 테스트해야하니까 기본 true로 리턴
		return true;
	}
	
	public OrderVO selectOrder(OrderVO vo) {
		if(vo.getId() == 0 && ct.isEmpty(vo.getOrderno())) {
			OrderVO data = new OrderVO();
			return data;
		}
		OrderVO data = this.sess.selectOne("OrderMapper.selectOrder", vo);
		return data;
	}
	
	public OrderVO selectOrder(int res) {
		OrderVO vo = new OrderVO(res);
		OrderVO data = this.sess.selectOne("OrderMapper.selectOrder", vo);
		return data;
	}
	
	public OrderVO selectOrder(String orderno) {
		OrderVO vo = new OrderVO();
		vo.setOrderno(orderno);
		OrderVO data = this.sess.selectOne("OrderMapper.selectOrder", vo);
		return data;
	}
	
	public List<OrderstatVO> selectOrderstats() {
		List<OrderstatVO> datas = this.sess.selectList("OrderMapper.selectOrderstats");
		return datas;
	}
	
	public OrderstatVO selectOrderstat(OrderVO vo) {
		OrderstatVO data = this.sess.selectOne("OrderMapper.selectOrderstat", vo);
		return data;
	}
	
	public boolean updateOrderstat(OrderVO vo) {
		int res = this.sess.update("OrderMapper.updateOrderstat", vo);
		return res == 1 ? true : false;
	}
	
	public boolean insertOrderSeller(OrderSellerVO vo) {
		int res = this.sess.insert("OrderMapper.insertOrderSeller", vo);
		return res == 1 ? true : false;
	}
	
	public List<ItemVO> selectSellerItemlist(String seller) {
		List<ItemVO> datas = this.sess.selectList("ItemMapper.selectSellerItemlist", seller);
		return datas;
	}
	
	public int[] itemIDlist(List<ItemVO> datas) {
		int[] list = new int[datas.size()];
		for(int i = 0; i < datas.size(); i++) {
			list[i] = datas.get(i).getId();
		}
		return list;
	}
	
	public int[] itemIDlist(String seller) {
		List<ItemVO> datas = this.selectSellerItemlist(seller);
		int[] list = this.itemIDlist(datas);
		return list;
	}
	
	public int findUserid(String username) {
		AccountVO account = this.sess.selectOne("AccountMapper.checkUsernameString", username);
		int res = account.getId();
		return res;
	}
	
	public int findOrderid(OrderVO vo) {
		vo.setId(0);
		OrderVO data = this.sess.selectOne("OrderMapper.selectOrder", vo);
		return data.getId();
	}
	
	public List<OrderAndSellerVO> selectOrderSeller(AccountVO vo) {
		int id = vo.getId();
		List<OrderAndSellerVO> datas = this.sess.selectList("OrderMapper.selectOrderandsellerbySellerid", id);
		return datas;
	}
	
	public List<OrderAndSellerVO> selectOrderSeller(int id) {
		List<OrderAndSellerVO> datas = this.sess.selectList("OrderMapper.selectOrderandsellerbySellerid", id);
		return datas;
	}
	 
	
}
