package com.shoppingmall.web.permission.model;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class PermissionDAO {
	
	@Autowired
	private SqlSession sess;
	
	public PermissionVO selectAllPerm(PermissionVO vo) {
		PermissionVO data = this.sess.selectOne("PermissionMapper.selectAll", vo);
		return data;
	}
}
