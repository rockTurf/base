package com.srj.web.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.srj.web.datacenter.news.model.News;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.*;


public class SpiderUtils {


    protected static final Logger logger = LoggerFactory.getLogger(SpiderUtils.class);

    private static String IFENG_URL = "http://finance.ifeng.com/stock/";


    /**
     * 爬虫程序--获取网页新闻数据
     *
     * @param url       爬虫访问地址
     * @param encodeing 网站编码
     * @return
     */
    public static String getSourceFormHtml(String url, String encodeing) {
        URL ur = null;
        InputStreamReader reader = null;
        StringBuffer sb = null;
        try {
            ur = new URL(url);

            URLConnection coon = ur.openConnection();

            coon.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/68.0.3440.106 Safari/537.36");

            reader = new InputStreamReader(coon.getInputStream(), encodeing);

            BufferedReader read = new BufferedReader(reader);

            sb = new StringBuffer();
            String temp;
            while ((temp = read.readLine()) != null) {
                sb.append(temp).append("\n");
            }

        } catch (Exception e) {
            e.printStackTrace();
            logger.error("一条新闻未读取成功");
            return null;
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return sb.toString();
    }

    public static String getAcSourceFormHtml(String url, String encodeing) {
        URL ur = null;
        InputStreamReader reader = null;
        StringBuffer sb = null;
        try {
            ur = new URL(url);

            URLConnection coon = ur.openConnection();
            coon.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/68.0.3440.106 Safari/537.36");
            coon.setRequestProperty("Referer", "Referer:https://www.aicoin.net.cn/");
            reader = new InputStreamReader(coon.getInputStream(), encodeing);

            BufferedReader read = new BufferedReader(reader);

            sb = new StringBuffer();
            String temp;
            while ((temp = read.readLine()) != null) {
                sb.append(temp).append("\n");
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return sb.toString();
    }

    /**
     * 获取凤凰网的股票新闻JSON
     *
     */
    public static JSONObject getifeng() {
        String url = IFENG_URL;
        //开爬
        String string = getSourceFormHtml(url, "UTF-8");
        if (StringUtils.isNotBlank(string)) {
            //处理返回的字符串，把头尾去掉,只保留stockNewsList里面的内容
            int n = string.indexOf("stockNewsList");
            String str = string.substring(n,string.length());
            str = str.replace("stockNewsList","");
            //去掉尾巴
            int m = str.indexOf("newsTab");
            str = str.substring(2, m);
            str = str.trim();
            str = str.substring(0,str.length()-2);
            //转化为JSONArray
            JSONArray array = JSONArray.parseArray(str);
            List<News> list = new ArrayList<>();
            for(int i=0;i<array.size();i++){
            	JSONObject obj = array.getJSONObject(i); // 遍历 jsonarray 数组，把每一个对象转成 json 对象
            	//放入实体类
            	News item = new News();
            	item.setAuthor(obj.getString("author"));
            	item.setTitle(obj.getString("title"));
            	item.setSource(obj.getString("source"));
            	item.setNews_time(obj.getString("newsTime"));
            	list.add(item);
            	System.out.println(item);
            }
            
            

        }
        return null;

    }

}
