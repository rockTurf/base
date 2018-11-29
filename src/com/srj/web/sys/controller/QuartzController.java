package com.srj.web.sys.controller;

import com.alibaba.fastjson.JSONObject;
import com.srj.web.datacenter.article.model.Keyword;
import com.srj.web.datacenter.article.service.KeywordService;
import com.srj.web.datacenter.news.model.News;
import com.srj.web.datacenter.news.service.NewsService;
import com.srj.web.util.DateUtils;
import com.srj.web.util.SpiderUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("quartz")
public class QuartzController {

      @Autowired
      NewsService newsService;
      @Autowired
      KeywordService keywordService;

      //新闻爬虫
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


      //新闻关键词和标题关联
      public void newsTitleGetKeyword(){
            List<Keyword> keywordList = keywordService.getAllKeyword();
            //计时器开始
            Long startTime = System.currentTimeMillis();
            int size = 1000;//循环取出新闻数据，1000条为一单位
            //1.先查看新闻总条数
            int totalNews = newsService.getTotalNewsNumber();
            //2.最高循环次数
            int count = (totalNews  +  size  - 1) / size;
            //3.开始循环，1000条一取
            for(int i=0;i<count;i++){
                  Map<String,Object> map = new HashMap<>();
                  map.put("start",i);
                  map.put("size",size);
                  List<News> newsList = newsService.getPageNewsOneK(map);
                  for(News news:newsList){
                        //取到之后，用新闻标题循环比对关键词，插库
                        for(Keyword key:keywordList){
                              newsService.getInNewsKeyword(news,key);
                        }
                  }

            }
      }
}
