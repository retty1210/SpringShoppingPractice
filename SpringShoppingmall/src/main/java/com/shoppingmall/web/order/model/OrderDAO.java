package com.shoppingmall.web.order.model;

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
	
	
}
