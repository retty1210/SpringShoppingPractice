package com.shoppingmall.web.item;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.shoppingmall.web.account.AccountController;
import com.shoppingmall.web.item.model.*;

@Controller
public class ItemController {
	
	private static final Logger logger = LoggerFactory.getLogger(AccountController.class);

	@Autowired
	private ItemService service;
	
	@RequestMapping(value = "/itemlist", method = RequestMethod.GET)
	public String itemlist(HttpServletRequest request) {
		List<ItemVO> datas = service.selectAll();
		request.setAttribute("datas", datas);
		return "item/list";
	}
	
	@RequestMapping(value="/itemlist/add", method = RequestMethod.GET)
	public String itemlistAdd(HttpSession session) {
		boolean res = session.getAttribute("logined") != null;
		if(!res) {
			return "redirect:/login";
		} else {
			if(session.getAttribute("usertype").equals("seller")) {
				return "item/add";
			} else {
				return "redirect:/";
			}
		}
		
	}
	
	@RequestMapping(value="/itemlist/itemup", method = RequestMethod.POST)
	public String itemup(ItemVO itemvo) {
		boolean res = service.upload(itemvo);
		if(res) {
			return "redirect:/itemlist";
		} else {
			logger.error("item upload error");
			return "redirect:/itemlist/add";
		}
	}
	
	@RequestMapping(value="/itemlist/detail", method = RequestMethod.GET)
	public String itemDetail(ItemVO itemvo, HttpServletRequest request) {
		ItemVO data = service.selectOne(itemvo);
		System.out.println("ID: " + itemvo.getId());
		//if(data != null) {
			request.setAttribute("data", data);
			Cookie[] cookies = request.getCookies();
			
			if(cookies.length > 0) {
				for(Cookie c: cookies) {
					System.out.println("이름: " + c.getName());
					System.out.println("값: " + c.getValue());
					System.out.println("path: " + c.getPath());
				}
			} else {
				System.out.println("cookie값 못읽음");
			}
			return "item/detail";
		//} else {
		//	logger.error("item detail error");
		//	return "redirect:/item/list";
		//}
	}
	
	@RequestMapping(value="/cart", method = RequestMethod.GET)
	public String cart(HttpServletRequest request) {
		Cookie[] cookies = request.getCookies();
		
		if(cookies.length > 0) {
			for(Cookie c: cookies) {
				System.out.println("이름: " + c.getName());
				System.out.println("값: " + c.getValue());
				System.out.println("path: " + c.getPath());
			}
		} else {
			System.out.println("cookie값 못읽음");
		}
		
		return "item/cart";
	}
	
//	@RequestMapping(value="/upload", method = RequestMethod.POST)
//	public String fileUpload(HttpServletRequest request) throws IOException {
//		MultipartRequest multi = new MultipartRequest(
//				request,
//				request.getServletContext().getRealPath("/upload"),
//				1024* 1024*10,
//				"utf-8",
//				new DefaultFileRenamePolicy()
//				);
//		String filename = multi.getFilesystemName("upload");
//		File file = multi.getFile("upload");
//		return "redirect:/upload/file";
//	}
	
	

}
