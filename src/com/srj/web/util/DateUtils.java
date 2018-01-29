package com.srj.web.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.time.DateFormatUtils;

/**
 * 日期工具类, 继承org.apache.commons.lang.time.DateUtils类
 */
public class DateUtils extends org.apache.commons.lang3.time.DateUtils {
	static String dateFormat = "yyyy-MM-dd";
	static SimpleDateFormat format = new SimpleDateFormat(dateFormat);
	private static String[] parsePatterns = { "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm", 
		"yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm", "yyyyMMdd" };

	/**
	 * 得到当前日期字符串 格式（yyyy-MM-dd）
	 */
	public static String getDate() {
		return getDate("yyyy-MM-dd");
	}
	
	/**
	 * 得到当前日期字符串 格式（yyyy-MM-dd） pattern可以为："yyyy-MM-dd" "HH:mm:ss" "E"
	 */
	public static String getDate(String pattern) {
		return DateFormatUtils.format(new Date(), pattern);
	}
	/**
	 * 指定日期返回星期几
	 */
	public static String getWeekOfDate(Date date) {      
	    String[] weekOfDays = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};        
	    Calendar calendar = Calendar.getInstance();      
	    if(date != null){        
	         calendar.setTime(date);      
	    }        
	    int w = calendar.get(Calendar.DAY_OF_WEEK) - 1;      
	    if (w < 0){        
	        w = 0;      
	    }      
	    return weekOfDays[w];    
	}
	/**
	 * 得到日期字符串 默认格式（yyyy-MM-dd） pattern可以为："yyyy-MM-dd" "HH:mm:ss" "E"
	 */
	public static String formatDate(Date date, Object... pattern) {
		String formatDate = null;
		if (pattern != null && pattern.length > 0) {
			formatDate = DateFormatUtils.format(date, pattern[0].toString());
		} else {
			formatDate = DateFormatUtils.format(date, "yyyy-MM-dd");
		}
		return formatDate;
	}
	
	/**
	 * 得到日期时间字符串，转换格式（yyyy-MM-dd HH:mm:ss）
	 */
	public static String formatDateTime(Date date) {
		return formatDate(date, "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * 得到当前时间字符串 格式（HH:mm:ss）
	 */
	public static String getTime() {
		return formatDate(new Date(), "HH:mm:ss");
	}

	/**
	 * 得到当前日期和时间字符串 格式（yyyy-MM-dd HH:mm:ss）
	 */
	public static String getDateTime() {
		return formatDate(new Date(), "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * 得到当前年份字符串 格式（yyyy）
	 */
	public static String getYear() {
		return formatDate(new Date(), "yyyy");
	}

	/**
	 * 得到当前月份字符串 格式（MM）
	 */
	public static String getMonth() {
		return formatDate(new Date(), "MM");
	}

	/**
	 * 得到当天字符串 格式（dd）
	 */
	public static String getDay() {
		return formatDate(new Date(), "dd");
	}

	/**
	 * 得到当前星期字符串 格式（E）星期几
	 */
	public static String getWeek() {
		return formatDate(new Date(), "E");
	}
	
	/**
	 * 日期型字符串转化为日期 格式
	 * { "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm", 
	 *   "yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm" }
	 */
	public static Date parseDate(Object str) {
		if (str == null){
			return null;
		}
		try {
			return parseDate(str.toString(), parsePatterns);
		} catch (ParseException e) {
			return null;
		}
	}

	/**
	 * 获取过去的天数
	 * @param date
	 * @return
	 */
	public static long pastDays(Date date) {
		long t = new Date().getTime()-date.getTime();
		return t/(24*60*60*1000);
	}
	
    
	public static Date getDateStart(Date date) {
		if(date==null) {
			return null;
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			date= sdf.parse(formatDate(date, "yyyy-MM-dd")+" 00:00:00");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}
	
	public static Date getDateEnd(Date date) {
		if(date==null) {
			return null;
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			date= sdf.parse(formatDate(date, "yyyy-MM-dd") +" 23:59:59");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}
	/**
	 * 将一个日期字符串dateStr按format的形式进行设置
	 * 
	 * @param dateStr
	 *            日期字符串
	 * @param format
	 *            日期的格式字符串
	 * @return format形式的日期
	 */
	public static Date StringToDate(String dateStr, String format) {
		if (dateStr == null || dateStr.length() == 0)
			return null;

		DateFormat dateFormat = new SimpleDateFormat(format);

		try {
			return dateFormat.parse(dateStr);
		} catch (ParseException ex) {
			ex.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 计算入学年
	 * @param before 基于当前年往前推几年
	 * @param after 基于当前年往后推几年
	 * @return
	 */
	public static List<Integer> getClassYear(int before,int after){
		List<Integer> years = new ArrayList<Integer>();
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		for(int i = year + after; i > year - before; i --){
			years.add(i);
		}
		return years;
	}
	/**
	 * 当前日期上加一定天数
	 * @param date
	 * @param n
	 * @return
	 */
	public static Date addDay(Date date, int n) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DATE, n);
		return cal.getTime();
	}
	/**
	 * 计算两个时间比较
		 int i= compare_date("2016-09-01 02:60", "2016-09-01 02:59");
		 i=1 DATE1>DATE2
		 i=-1 DATE1<DATE2
		 i=0 DATE1=DATE2
	 * @return
	 */
	 public static int compare_date(String DATE1, String DATE2) {
	         DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm");
	         try {
	             Date dt1 = df.parse(DATE1);
	             Date dt2 = df.parse(DATE2);
	             if (dt1.getTime() > dt2.getTime()) {
	                 return 1;
	             } else if (dt1.getTime() < dt2.getTime()) {
	                 return -1;
	             } else {
	                 return 0;
	             }
	         } catch (Exception exception) {
	             exception.printStackTrace();
	         }
	         return 0;
     }
	 /**
	  * 计算两个时间相差天数
	  * @param datestr1
	  * @param datestr2
	  * @return
	  * @throws ParseException
	  */
	  public static long daysOfTwo(String datestr1,String datestr2) throws ParseException {
	        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
	        //跨年不会出现问题
	        //如果时间为：2016-03-18 11:59:59 和 2016-03-19 00:00:01的话差值为 0
	        Date fDate=sdf.parse(datestr2);
	        Date oDate=sdf.parse(datestr1);
	        long days=(oDate.getTime()-fDate.getTime())/(1000*3600*24);
	        return days;
	    }
	  /** 
	     *  
	     * 1 第一季度 2 第二季度 3 第三季度 4 第四季度 
	     *  
	     * @param date 
	     * @return 
	     */  
	    public static int getSeason(Date date) {  
	  
	        int season = 0;  
	  
	        Calendar c = Calendar.getInstance();  
	        c.setTime(date);  
	        int month = c.get(Calendar.MONTH);  
	        switch (month) {  
	        case Calendar.JANUARY:  
	        case Calendar.FEBRUARY:  
	        case Calendar.MARCH:  
	            season = 1;  
	            break;  
	        case Calendar.APRIL:  
	        case Calendar.MAY:  
	        case Calendar.JUNE:  
	            season = 2;  
	            break;  
	        case Calendar.JULY:  
	        case Calendar.AUGUST:  
	        case Calendar.SEPTEMBER:  
	            season = 3;  
	            break;  
	        case Calendar.OCTOBER:  
	        case Calendar.NOVEMBER:  
	        case Calendar.DECEMBER:  
	            season = 4;  
	            break;  
	        default:  
	            break;  
	        }  
	        return season;  
	    } 
	    /**
	     * 输出两个时间间的所有日期
	     * @param date1
	     * @param date2
	     */
		public static List<String> process(String date1, String date2){
			List<String>  linkedList = new ArrayList<String>();
			if(date1.equals(date2)){
//				System.out.println(date1);
				linkedList.add(date1);
			    return linkedList;
			}
			
			String tmp;
			if(date1.compareTo(date2) > 0){  //确保 date1的日期不晚于date2
				tmp = date1; date1 = date2; date2 = tmp;
			}
			
			tmp = format.format(str2Date(date1).getTime() + 3600*24*1000);
			
	        int num = 0; 
	        while(tmp.compareTo(date2) < 0){  
	        	if(num==0)
//	        		System.out.println(date1);
	        		linkedList.add(date1);
//	        	System.out.println(tmp);  
	        	linkedList.add(tmp);
	        	num++;
	        	tmp = format.format(str2Date(tmp).getTime() + 3600*24*1000);
	        }
	        
	        if(num == 0){
//	        	System.out.println(date1);
//	        	System.out.println(date2);
	        	linkedList.add(date1);
	        	linkedList.add(date2);
	        }else{
//	        	System.out.println(date2);
	        	linkedList.add(date2);
	        }
	        return linkedList;
		}

	public static Date str2Date(String str) {
			if (str == null) return null;
			
			try {
				return format.parse(str);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			return null;
		}

	/*
	 * 2017/01/23 shiruojiang
	 * 如果现在时间是早8点以前，输入昨天的日期yy-MM-dd格式
	 * */
	public static String getDateBy8(){
		Date date= new Date();
		int h = date.getHours();
		if(h<8){
			date=new Date(date.getTime()-24*60*60*1000);
		}
		return new SimpleDateFormat("yyyy-MM-dd").format(date);
	}
}
