package com.srj.web.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.srj.web.datacenter.news.model.News;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
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

    private static String IFENG_URL = "http://finance.ifeng.com/listpage/110/1/list.shtml";


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
    public static List<JSONObject> getifeng() {
        //返回值
        List<JSONObject> list = new ArrayList<>();

        String url = IFENG_URL;
        //开爬
        String string = getSourceFormHtml(url, "UTF-8");
        if (StringUtils.isNotBlank(string)) {
            Document doc = Jsoup.parse(string);
            if (doc != null) {
                Elements elements = doc.select("span[class=txt01]");
                for(Element el:elements){
                    String title = el.text();
                    String link = el.select("a").attr("href").toString();
                    //文章链接
                    String string_article = getSourceFormHtml(link, "UTF-8");
                    Document doc2 = Jsoup.parse(string_article);
                    String content = "";
                    String newsTime = "";
                    if (doc2 != null) {
                        Element element = doc2.getElementById("main_content");
                        newsTime = doc2.select("meta[name=og:time]").get(0).attr("content").toString();
                        content = element.text();
                    }
                    JSONObject obj = new JSONObject();
                    obj.put("title",title);
                    obj.put("content",content);
                    obj.put("newsTime",newsTime);
                    list.add(obj);
                }
            }
        }
        return list;

    }

}
