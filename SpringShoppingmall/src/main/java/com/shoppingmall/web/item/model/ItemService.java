package com.shoppingmall.web.item.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class ItemService {

	@Autowired
	private ItemDAO dao;
	
	public boolean upload(ItemVO vo) {
		boolean res = false;
		if(StringUtils.isEmpty(vo.getThumURL())) {
			res = dao.uploadText(vo);
		} else {
			res = dao.uploadItem(vo);
		}
		return res;	
	}
	
	public ItemVO selectOne(ItemVO vo) {
		ItemVO data = dao.selectOneItem(vo);
		return data;
	}
	
	public List<ItemVO> selectAll() {
		List<ItemVO> datas = dao.selectAllItem();
		return datas;
	}
	
	public List<ItemVO> viewCart(int[] l) {
		int r = l.length;
		List<ItemVO> datas;
		if(r > 0) {
			datas = dao.viewCart(l);
		} else {
			ItemVO vo = new ItemVO();
			vo.setId(-1);
			datas = new ArrayList<ItemVO>();
			datas.add(vo);
		}
		return datas;
	}
}
