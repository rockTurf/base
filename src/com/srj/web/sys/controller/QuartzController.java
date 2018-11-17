package com.srj.web.sys.controller;

import com.alibaba.fastjson.JSONObject;
import com.srj.web.datacenter.news.model.News;
import com.srj.web.datacenter.news.service.NewsService;
import com.srj.web.util.DateUtils;
import com.srj.web.util.SpiderUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("quartz")
public class QuartzController {

      @Autowired
      NewsService newsService;

      @RequestMapping(value = "/news")
      public void getNews(){
            List<JSONObject> list = SpiderUtils.getifeng();
            List<News> newsList = new ArrayList<>();
            if(list.size()<=0){
            	System.out.println("------------------什么都没爬到");
            }
            for(JSONObject obj:list){
                  News news = new News();
                  news.setTitle(obj.getString("title"));
                  news.setContent(obj.getString("content"));
                  news.setNews_time(obj.getString("newsTime"));
                  news.setCreate_time(DateUtils.getDateTime());
                  news.setSource("凤凰网财经");
                  newsList.add(news);
            }
            newsService.insertList(newsList);
      }
}
