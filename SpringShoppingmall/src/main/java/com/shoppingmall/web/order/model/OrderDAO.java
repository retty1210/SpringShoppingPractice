package com.shoppingmall.web.order.model;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class OrderDAO {
	
	@Autowired
	private SqlSession sess;
	
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
		OrderVO data = this.sess.selectOne("OrderMapper.selectOrder", vo);
		return data;
	}
	
	public OrderVO selectOrder(int res) {
		OrderVO vo = new OrderVO(res);
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
	
	
}
