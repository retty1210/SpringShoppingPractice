package com.shoppingmall.web.item.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
			data.setUserid(vo.getId());
			data.setUsername(vo.getUsername());
			data.setWishlist(id + "");
			return dao.makeWish(data);
		} else {
			/*
			 * 
<wishlist가 있는 상태>
1.username으로 wishlist table search
2. 결과값이 1이면 update로 들어감
3. 기존 wishlist값을 int[]로 풀어냄
4. for문으로 중복값 테스트
5. 중복값이 없으면 int[]에 상품id값 추가
6. int[]를 String으로 join
7. join한 값을 update

<<Wishlist를 조회할 때>>
1. session에서 AccountDAO값을 읽음
2. dao.username을 mapper로 보냄
3. select, 검색값 username
4. 나온 wishlist값을 int[]로 풀어냄
5. int[]로 itemVO List값 가져옴
6. 가져온 List 출력
			 */
			
		}
		return false;
	}
	
//	public int[] viewWish(AccountVO vo) {
//		WishlistVO data = dao.viewWishAccount(vo);
//		int[] l = new int[1];
//		if(data.getWishlist().contains("_")) {
//			String[] list = data.getWishlist().split("_");
//			l = Arrays.asList(list).stream().mapToInt(Integer::parseInt).toArray();
//		} else if(!data.getWishlist().isEmpty()) {
//			l[0] = Integer.parseInt(data.getWishlist());
//		} else {
//			l[0] = -1;
//		}
//		return l;
//	}
//	
//	public int[] viewWish(WishlistVO vo) {
//		WishlistVO data = dao.viewWishList(vo);
//		int[] l = new int[1];
//		if(data.getWishlist().contains("_")) {
//			String[] list = data.getWishlist().split("_");
//			l = Arrays.asList(list).stream().mapToInt(Integer::parseInt).toArray();
//		} else if(!data.getWishlist().isEmpty()) {
//			l[0] = Integer.parseInt(data.getWishlist());
//		} else {
//			l[0] = -1;
//		}
//		return l;
//	}
//	
//	public List<ItemVO> wishlist(int[] i) {
//		List<ItemVO> datas = dao.viewCart(i);
//		return datas;
//	}
}
