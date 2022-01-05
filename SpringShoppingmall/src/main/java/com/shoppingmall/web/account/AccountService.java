package com.shoppingmall.web.account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {
	
	@Autowired
	private AccountDAO dao;
	
	public AccountVO login(LoginVO vo) {
		AccountVO data = dao.selectLoginAccount(vo);
		data.setPassword("");
		return data;
	}
	
	public boolean joinSeller(AccountVO vo) {
		return dao.joinSellerAccount(vo);
	}
	
	public boolean joinBuyer(AccountVO vo) {
		return dao.joinBuyerAccount(vo);
	}
	
	public boolean joinCheck(AccountVO vo) {
		if(dao.checkUsername(vo)) {
			if(dao.checkEmail(vo)) {
				return true;
			}
		}
		return false;
	}

}
