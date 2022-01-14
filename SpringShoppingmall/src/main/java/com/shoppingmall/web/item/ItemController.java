package com.shoppingmall.web.item;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.shoppingmall.web.account.AccountController;
import com.shoppingmall.web.account.AccountVO;
import com.shoppingmall.web.common.CommonTools;
import com.shoppingmall.web.item.model.*;

@Controller
public class ItemController {
	
	private static final Logger logger = LoggerFactory.getLogger(AccountController.class);

	@Autowired
	private ItemService service;
	
	private CommonTools ct;
//	
//	@Resource(name = "uploadPath")
//	private String uploadPath;
//	
//	@PostConstruct
//	public void initController() {
//		
//		String sessionpath = session.getServletContext().getRealPath("/");
//		String attachpath = "static/up/";
//		String realPath = sessionpath + attachpath;
//	}
	
	@RequestMapping(value = "/itemlist", method = RequestMethod.GET)
	public String itemlist(HttpServletRequest request, HttpSession session) {
		List<ItemVO> datas = service.selectAll();
		request.setAttribute("datas", datas);
		
		String path = session.getServletContext().getRealPath("/");
		path += "static/up/";
		request.setAttribute("path", path);
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
	public String itemup( ItemVO data, MultipartFile thum, HttpServletRequest request, HttpSession session
			) {
		//String path = session.getServletContext().getRealPath("/");
		//String path = "/shop/";
		String path = "C:\\Users/retty/git/SpringShoppingPractice/SpringShoppingmall/src/main/webapp/resources/upload/";
		request.setAttribute("path", path);
		if(thum.getSize() != 0) {
			String uuidname = UUID.randomUUID().toString().replace("-", "") + "_" + thum.getOriginalFilename();
			byte[] bytes;
			
			try {
				bytes = thum.getBytes();
				File file = new File(path + uuidname);
				System.out.println("file: " + file);
				thum.transferTo(file);
				//FileCopyUtils.copy(bytes, file);
//				if(file.createNewFile()) {
//					System.out.println("CreateNewFile O");
//				} else {
//					System.out.println("CreateNewFile X");
//				}
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			data.setThumURL(uuidname);
			data.setInfoURL(uuidname);
		}
		
		boolean res = service.upload(data);
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
			
			HttpSession session = request.getSession();
			//String path = session.getServletContext().getRealPath("/");
			String path="/shop";
			path += "static/up/";
			request.setAttribute("path", path);
			
			return "item/detail";
		//} else {
		//	logger.error("item detail error");
		//	return "redirect:/item/list";
		//}
	}
	
	@RequestMapping(value="/cart", method = RequestMethod.GET)
	public String cart(HttpServletRequest request, @CookieValue(value="cart", required=false)Cookie cart) {
//		Cookie[] cookies = request.getCookies();
//		int res = cookies.length;
//		if(res > 0) {
//			for(Cookie c: cookies) {
//				System.out.println("<cart cookie 출력, cookie개수: " + res + ">");
//				System.out.println("이름: " + c.getName());
//				System.out.println("값: " + c.getValue());
//				System.out.println("path: " + c.getPath());
//				if(c.getName().equals("cart")) {
//					System.out.println("cart 있음");
//				}
//			}
//		} else {
//			System.out.println("cookie값 못읽음");
//		}
		String[] cList = new String[0];
		int listN = 0;
		int[] list = new int[0];
		if(cart.getValue().contains("_")) {
			cList = cart.getValue().split("_");
			for(String s: cList) {
				System.out.println("cList: " + s);
			}
			listN = cList.length;
			list = Arrays.asList(cList).stream().mapToInt(Integer::parseInt).toArray();
			for(int i: list) {
				System.out.println("i: " + i);
			}
//			for(int i = 0; i > listN; i++) {
//				int num = Integer.parseInt(cList[i]);
//				System.out.println("num: " + num);
//				list[i] = num;
//				System.out.println("list: " + list[i]);
//			}
		} else if(!cart.getValue().isEmpty()) {
			listN = 1;
			cList = new String[listN];
			cList[0] = cart.getValue();
			list = new int[listN];
			list[0] = Integer.parseInt(cList[0]);
		}
		
		if(listN == 0) {
			System.out.println("listN = 0");
			return "item/cart";
		} else {
			System.out.println("listN = " + listN);
			
			List<ItemVO> datas = service.viewCart(list);
			String res = "";
			for(int i: list) {
				res += i + "_";
			}
			request.setAttribute("res", res);
			
			System.out.println("datas: " + datas);
			for(ItemVO d : datas) {
				System.out.println("번호: " + d.getId());
				System.out.println("이름: " + d.getItemname());
			}
			request.setAttribute("datas", datas);
			int pr = service.itemAllPrice(datas);
			request.setAttribute("priceAll", pr);
			return "item/cart";
		}
	}
	
	@RequestMapping(value="/wish/add", method=RequestMethod.POST, produces="application/json; charset=utf-8")
	@ResponseBody
	public String addWish(HttpSession session, int id) {
		AccountVO vo = (AccountVO) session.getAttribute("account");
		JSONObject json = new JSONObject();
		boolean res = false;
		if(vo == null) {
			json.put("msg", "로그인하세요.");
			return json.toJSONString();
		}
		res = service.addWish(vo, id);
		
		if(res) {
			json.put("msg", "위시리스트에 추가했습니다.");
		} else {
			json.put("msg", "에러 - 위시리스트에 이미 있는지 확인해주세요.");
		}
		return json.toJSONString();
	}
	
	@RequestMapping(value = "/wishlist", method = RequestMethod.GET)
	public String wish(HttpServletRequest request, HttpSession session) {
		AccountVO ac = new AccountVO();
		ac.setUsername(session.getAttribute("username").toString());
		boolean res = service.viewwish(ac);
		if(res) {
			List<ItemVO> datas = service.viewWishList(ac);
			int pr = service.itemAllPrice(datas);
			request.setAttribute("datas", datas);
			request.setAttribute("priceAll", pr);
		}
		
		
		return "item/wish";
	}
	
	@RequestMapping(value="/deleteitem", method=RequestMethod.POST, produces="application/json; charset=utf-8")
	@ResponseBody
	public String deleteCheck(@RequestParam(value="arr")String arr) {
		JSONObject json = new JSONObject();
		
		if(ct.isEmpty(arr)) {
			json.put("msg", "선택된 아이템이 없습니다");
		} else {
			int[]iarr = ct.makeIntList(arr);
			boolean res = service.deleteItemCheck(iarr);
			System.out.println("res : " + res);
			int len = iarr.length;
			System.out.println("삭제아이템개수: " + len);
			if(res) {
				json.put("msg", "아이템의 삭제에 성공하였습니다");
			} 
			//삭제아이템이 1개일때: 메시지가 잘 뜸
			//삭제아이템이 2개 이상일 때: res는 true임에도 메시지가 안 뜸
		}
		
		return json.toJSONString();
	}
	

}
