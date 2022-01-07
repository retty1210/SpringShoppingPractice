package com.shoppingmall.web.item.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.shoppingmall.web.account.AccountVO;

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
		//int r = l.length;
		List<ItemVO> datas = dao.viewCart(l);
			
		return datas;
	}
	
	public boolean addWish(AccountVO vo, int id) {
		WishlistVO data = dao.viewWish(vo);
		if(data == null) {
			WishlistVO res = new WishlistVO(vo.getId(), vo.getUsername(), id + "");
			return dao.makeWish(res);
		} else {
			String result = "";
			int[] ilist = dao.makeIntList(data);
			boolean isDup = false;
			for(int i : ilist) {
				if(i == id) {
					isDup = true;
				}
			}
			
			if(!isDup) {
				int[] newlist = dao.plusIntList(ilist, id);
				result = Arrays.stream(newlist).mapToObj(String::valueOf).collect(Collectors.joining("_"));
				data.setWishlist(result);
				return dao.updateWish(data);
			}
			isDup = false;
		}
		return false;
	}
	
	public List<ItemVO> viewWishList(AccountVO vo) {
		WishlistVO data = dao.viewWish(vo);
		int[] ilist = dao.makeIntList(data);
		Arrays.sort(ilist);
		for(int i : ilist) {
			System.out.println("ilist: " + i);
		}
		List<ItemVO> datas = dao.viewCart(ilist);
		return datas;
	}
	
	public boolean viewwish(AccountVO vo) {
		WishlistVO data = dao.viewWish(vo);
	
		return data != null ? true : false;
	}

}
