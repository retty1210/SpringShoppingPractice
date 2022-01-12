package com.shoppingmall.web.account;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shoppingmall.web.aop.annotation.Perm;

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
	
	@Transactional(rollbackFor=Exception.class)
	public boolean join(AccountVO vo) throws Exception {
		if(dao.joinBuyerAccount(vo) || dao.joinSellerAccount(vo)) {
			return true;
		} else {
			throw new Exception("데이터 처리 과정중 문제 발생");
		}
	}
	
	@Perm(table="SHOP_ACCOUNTS", crud="delete")
	public void deleteAccount(HttpSession session) {
		System.out.println("deleteAccount 실행됨");
	}

}
