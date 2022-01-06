package com.shoppingmall.web.item.model;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.shoppingmall.web.account.AccountVO;

@Repository
public class ItemDAO {
	
	@Autowired
	private SqlSession sess;
	
	public boolean uploadItem(ItemVO vo) {
		int res = this.sess.insert("ItemMapper.upload", vo);
		return res == 1 ? true : false;
	}
	
	public boolean uploadText(ItemVO vo) {
		int res = this.sess.insert("ItemMapper.upText", vo);
		return res == 1 ? true : false;
	}
	
	public ItemVO selectOneItem(ItemVO vo) {
		ItemVO data = this.sess.selectOne("ItemMapper.selectOneItem", vo);
		return data;
	}
	
	public List<ItemVO> selectAllItem() {
		List<ItemVO> datas = this.sess.selectList("ItemMapper.selectAllItem");
		return datas;
	}
	
	public List<ItemVO> viewCart(int[] list) {
		List<ItemVO> datas = this.sess.selectList("ItemMapper.viewCart", list);
		return datas;
	}
	
	public int countAll() {
		int res = 0;
		res = this.sess.selectOne("ItemMapper.countAll");
		return res;
	}
	
	public WishlistVO viewWish(AccountVO vo) {
		WishlistVO data = this.sess.selectOne("ItemMapper.viewWish", vo);
		return data;
	}
	
	public boolean makeWish(WishlistVO vo) {
		int res = this.sess.insert("ItemMapper.makeWish", vo);
		return res == 1 ? true : false;
	}
	
	

}
