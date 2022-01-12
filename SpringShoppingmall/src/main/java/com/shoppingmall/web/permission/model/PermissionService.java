package com.shoppingmall.web.permission.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PermissionService {
	
	@Autowired
	private PermissionDAO dao;
	
	public boolean existedDeletePerm(PermissionVO vo) {
		PermissionVO data = dao.selectAllPerm(vo);
		return data.getDeletepm() == "Y" ? true : false;
	}

}
