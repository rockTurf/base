package com.srj.web.util;

public class TxtUtil {
	
	//处理txt字符串(将tab替换为逗号并去空格返回字符串)
	public static String getLineTxt(String str){
		String[] lineArr = str.split("\\t");			
		String newline="";
		for (int i = 0; i < lineArr.length; i++){
		    if (i<lineArr.length){
		        newline+=lineArr[i]+",";
		    }else{
		    	newline+=newline+lineArr[i];
		    }
		}
		return newline.replaceAll("\\s*", "");
	}

}
