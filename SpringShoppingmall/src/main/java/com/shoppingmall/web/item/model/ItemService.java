package com.shoppingmall.web.item.model;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.shoppingmall.web.account.AccountVO;
import com.shoppingmall.web.common.CommonTools;

@Service
public class ItemService {

	@Autowired
	private ItemDAO dao;
	private CommonTools ct;
	
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
			int[] ilist = ct.makeIntList(data.getWishlist());
			boolean isDup = ct.isDuplicate(ilist, id);
			
			if(!isDup) {
				int[] newlist = ct.plusIntList(ilist, id);
				result = ct.intArrToString(newlist, "_");
				data.setWishlist(result);
				return dao.updateWish(data);
			}
			isDup = false;
		}
		return false;
	}
	
	public List<ItemVO> viewWishList(AccountVO vo) {
		System.out.println("service.viewwishlist 진입");
		WishlistVO data = dao.viewWish(vo);
		System.out.println("wishlistVO 생성");
		int[] ilist = new int[1];
		System.out.println("int[] 생성");
		String ws = data.getWishlist();
		System.out.println("data.getWishlist() : " + ws);
		ilist = ct.makeIntList(ws);
		System.out.println("int[]에 data값 읽어옴");
		//Arrays.sort(ilist);
		List<ItemVO> datas = dao.viewCart(ilist);
		System.out.println("ItemVO list 만듬");
		return datas;
	}
	
	public boolean viewwish(AccountVO vo) {
		WishlistVO data = dao.viewWish(vo);
	
		return data != null ? true : false;
	}
	
	public int itemAllPrice(List<ItemVO> datas) {
		int res = 0;
		for(ItemVO d : datas) {
			res += d.getPrice();
		}
		return res;
	}

}
