package com.shoppingmall.web.account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AccountService {
	
	@Autowired
	private AccountDAO dao;
	
	public AccountVO login(LoginVO vo) {
		AccountVO data = dao.selectLoginAccount(vo);
		if(data != null) {
			data.setPassword("");
		}
		
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
	
//	@Transactional(rollbackFor=Exception.class)
//	public boolean join(AccountVO vo) throws Exception {
//		if(dao.joinBuyerAccount(vo)) {
//			return true;
//		} else {
//			throw new Exception("데이터 처리 과정중 문제 발생");
//		}
//	}

}
