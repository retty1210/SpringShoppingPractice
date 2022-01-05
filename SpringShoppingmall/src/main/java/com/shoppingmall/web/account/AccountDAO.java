package com.shoppingmall.web.account;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AccountDAO {
	
	@Autowired
	private SqlSession sess;
	
	public AccountVO selectLoginAccount(LoginVO vo) {
		AccountVO data = this.sess.selectOne("AccountMapper.selectLoginAccount", vo);
		return data;
	}
	
	public boolean joinSellerAccount(AccountVO vo) {
		int res = this.sess.insert("AccountMapper.joinSellerAccount", vo);
		return res == 1 ? true : false;
	}
	
	public boolean joinBuyerAccount(AccountVO vo) {
		int res = this.sess.insert("AccountMapper.joinBuyerAccount", vo);
		return res == 1 ? true : false;
	}
	
	public boolean checkUsername(AccountVO vo) {
		AccountVO data = this.sess.selectOne("AccountMapper.checkUsername", vo);
		return data == null ? true : false;
	}
	
	public boolean checkEmail(AccountVO vo) {
		AccountVO data = this.sess.selectOne("AccountMapper.checkEmail", vo);
		return data == null ? true : false;
	}

}
