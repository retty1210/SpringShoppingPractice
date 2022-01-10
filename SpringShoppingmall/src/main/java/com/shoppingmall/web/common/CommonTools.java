package com.shoppingmall.web.common;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

}
