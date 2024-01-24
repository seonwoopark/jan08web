package com.seonwoo.util;

import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class Util {
	
	public static String ipMasking(String ip) {
		if(ip.indexOf('.') != -1) {
			int index = ip.indexOf(".")+1;
			int index2 = ip.indexOf(".", index);
			
			StringBuffer buffer = new StringBuffer(ip);
			
			return new String(buffer.replace(index,index2,"♥"));
		} else {
			return ip;
		}
	}
	
	public static String addBR(String str) {
		 return str.replaceAll("(\r\n|\r|\n|\n\r)", "<br>");
	}
	
	//HTML 태그를 특수기호로 변경하기
	public static String removeTag(String str) {

		str = str.replaceAll(">", "&gt");
		str = str.replaceAll("<", "&lt");
		
		return str;
	}
	
	public static int str2Int(String str) {
		
		StringBuilder builder = new StringBuilder();
		
		for (int i = 0; i < str.length(); i++) {
			if(!Character.isAlphabetic(str.charAt(i))) {
				builder.append(str.charAt(i));
			}
		}
		if(builder.isEmpty()) {
			return 0;
		} else {
			return  Integer.parseInt(builder.toString());	
		}
		
	}

	public static int str2Int2(String str) {
		
		String number = str.replaceAll("[^0-9]", "");
		return Integer.parseInt(number);
	}
	
	public static boolean checkInt(String str) {
		
		try {
			Integer.parseInt(str);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	//String값이 들어오면 int타입인지 확인해보는 메소드
	public static boolean checkInt2(String str) {
		boolean result = true;
		for (int i = 0; i < str.length(); i++) {
			if(Character.isAlphabetic(str.charAt(i))) {
				result = false;
				break;
			}
		}
		return result;
	}
	
	
	 //IP얻어오기
	   public static String getIP(HttpServletRequest request) {
	      String ip = request.getHeader("X-FORWARDED-FOR");
	      
	      if(ip == null) {
	         ip = request.getHeader("Proxy-Client-IP");
	      }
	      if(ip == null) {
	         ip = request.getHeader("WL-Proxy-Client-IP");   
	      }
	      if(ip == null) {
	         ip = request.getHeader("HTTP_CLIENT_IP");
	      }
	      if(ip == null) {
	         ip = request.getHeader("HTTP_X_FORWARDED_FOR");
	      }
	      if(ip == null) {
	         ip = request.getRemoteAddr();
	      }
	      return ip;
	   }
	
	
	
	
	
	
	
	
	
}
