package com.shoppingmall.web.common;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.shoppingmall.web.item.model.ItemVO;

public class CommonTools {
	
	public CommonTools() {}
	
	//공백, null 체크
	public static boolean isEmpty(Object obj) {
		if(obj == null) return true;
		if ((obj instanceof String) && (((String)obj).trim().length() == 0)) { return true; }
	        if (obj instanceof Map) { return ((Map<?, ?>) obj).isEmpty(); }
	        if (obj instanceof Map) { return ((Map<?, ?>)obj).isEmpty(); } 
	        if (obj instanceof List) { return ((List<?>)obj).isEmpty(); }
	        if (obj instanceof Object[]) { return (((Object[])obj).length == 0); }

		return false;
	}
	
	
	public static int[] makeIntList(String s) {
		String[] slist = new String[1];
		if(!s.isEmpty()) {
			if(s.contains("_")) {
				slist = s.split("_");
			} else {
				slist[0] = s;
			}
		} else {
			System.out.println("input null값 체크-ㅇ");
			int[] res = {-1};
			return res;
		}
		
		int leng = slist.length;
		int[] ilist = new int[leng];
		for(int i = 0; i < leng; i++) {
			String ss = slist[i];
			ilist[i] = Integer.parseInt(ss);
		}
		return ilist;
	}
	
	public static int[] makeIntList(String s, String sep) {
		String[] slist = new String[1];
		if(!s.isEmpty()) {
			if(s.contains(sep)) {
				slist = s.split(sep);
			} else {
				slist[0] = s;
			}
		} else {
			int[] res = {-1};
			return res;
		}
		
		int leng = slist.length;
		int[] ilist = new int[leng];
		for(int i = 0; i < leng; i++) {
			String ss = slist[i];
			ilist[i] = Integer.parseInt(ss);
		}
		return ilist;
	}
	
	public static int[] plusIntList(int[] list, int id) {
		int len = list.length;
		int[] newlist = new int[len + 1];
		for(int i = 0; i < len ; i++) {
			newlist[i] = list[i];
		}
		newlist[len] = id;
		return newlist;
	}
	
	public static boolean isDuplicate(int[] list, int id) {
		boolean res = false;
		for(int i : list) {
			if(i == id) {
				res = true;
			}
		}
		return res;
	}
	
	public static String intArrToString(int[] list, String s) {
		String result = Arrays.stream(list).mapToObj(String::valueOf).collect(Collectors.joining(s));
		return result;
	}
	
	public static List<String> stringArrDup(List<String> arr) {
		Map<String, Integer> map = new HashMap<String, Integer>();
		for(String k : arr) {
			map.put(k, map.getOrDefault(k, 0) + 1);
		}
		List<String> newarr = new ArrayList<String>();
		for(String key : map.keySet()) {
			newarr.add(key);
		}
		return newarr;
	}
	
	public static int[] checkIntarr(int[] aarr, int[] barr) {
		List<Integer> intlist = new ArrayList<Integer>();
		for(int a: aarr) {
			for(int b: barr) {
				if(a == b) {
					intlist.add(a);
				}
			}
		}
		int[] result = intlist.stream().mapToInt(Integer::intValue).toArray();
		return result;
	}
	
	public static String generateOrderno() {
		int leftlimit = 48;
		int rightlimit = 90;
		int targetlength = 13;
		Random random = new Random();
		
		String rand = random.ints(leftlimit, rightlimit + 1)
				.filter(i -> (i <= 57 || i >= 65))
				.limit(targetlength)
				.collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
				.toString();
		
		DateFormat dfm = new SimpleDateFormat("YYMMDDHH24mmss");
		Date date = new Date();
		String dateTostr = dfm.format(date);
		String res = dateTostr + rand;
		
		return res;
	}
	
	public static String getUsertype(HttpServletRequest request) {
		HttpSession session = request.getSession();
		String usertype = "";
		if(session.getAttribute("usertype") != null) {
			usertype = session.getAttribute("usertype").toString();
		}
		return usertype;
	}
	
	public static boolean checkIntlistNull(int[] ilist) {
		boolean res = true;
		int count = ilist.length;
		if(count == 0) {
			res = false;
		}
		for(int i: ilist) {
			if(i == 0 || isEmpty(i)) {
				res = false;
				break;
			} 
		}
		
		return res;
	}
	
	public static int[] makeIntlisttrue(int[] ilist) {
		if(ilist.length == 0) {
			int[] newl = {-1};
			return newl;
		} else {
			int count = ilist.length;
			for(int i : ilist) {
				if(i == 0 || isEmpty(i)) {
					count = count - 1;
				}
			}
			int[] newl = new int[count];
			int j = 0;
			for(int i = 0; i < ilist.length; i++) {
				if(ilist[i] != 0) {
					newl[j] = ilist[i];
					j++;
				}
			}
			if(newl[0] != 0) {
				return newl;
			} else {
				int[] n = {-1};
				return n;
			}
		}
	}
}
