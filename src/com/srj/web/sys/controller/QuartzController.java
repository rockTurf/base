package com.srj.web.sys.controller;

import com.alibaba.fastjson.JSONObject;
import com.srj.common.constant.Constant;
import com.srj.web.datacenter.article.model.Keyword;
import com.srj.web.datacenter.article.service.KeywordService;
import com.srj.web.datacenter.news.model.News;
import com.srj.web.datacenter.news.service.NewsService;
import com.srj.web.util.DateUtils;
import com.srj.web.util.SpiderUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Date;
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

      //凤凰网新闻爬虫
      @Scheduled(cron = "0 0 1/1 ? * *")
      //@RequestMapping(value = "/news")
      @ResponseBody
      public void getiFengNews(){
            List<JSONObject> list = SpiderUtils.getifeng();
            List<News> newsList = new ArrayList<>();
            if(list.size()<=0){
            	//System.out.println("------------------什么都没爬到");
            }
            //取出40条凤凰网财经的记录
            List<News> dataList = newsService.selectBySource(Constant.NEWS_SOURCE_IFENG);

            for(JSONObject obj:list){
            	  boolean b = true;	
             		
                  News news = new News();
                  news.setTitle(obj.getString("title"));
                  news.setContent(obj.getString("content"));
                  news.setNews_time(obj.getString("newsTime"));
                  news.setCreate_time(DateUtils.getDateTime());
                  news.setSource(Constant.NEWS_SOURCE_IFENG);

                  for(News item:dataList){
                	  if(item.getTitle().equals(obj.getString("title"))){
                		  //System.out.println("----去除重复新闻,标题："+obj.getString("title")+","+DateUtils.formatDateTime(new Date()));
                		  b=false;
                	  }
                  }
                  
                  if(b==true){
                	  newsList.add(news);
                  }
                  
            }
            newsService.insertList(newsList);
            System.out.println("增加"+newsList.size());
      }

      //中国证券网新闻爬虫
      @Scheduled(cron = "0 30 1/1 ? * *")
      //@RequestMapping(value = "/news")
      @ResponseBody
      public void getCsStockNews(){
            List<JSONObject> list = SpiderUtils.getCsStock();
            List<News> newsList = new ArrayList<>();
            if(list.size()<=0){
            	//System.out.println("------------------什么都没爬到");
            }
            //取出40条凤凰网财经的记录
            List<News> dataList = newsService.selectBySource(Constant.NEWS_SOURCE_CSSTOCK);

            for(JSONObject obj:list){
            	  boolean b = true;	
             		
                  News news = new News();
                  news.setTitle(obj.getString("title"));
                  news.setContent(obj.getString("content"));
                  news.setNews_time(obj.getString("newsTime"));
                  news.setAuthor(obj.getString("author"));
                  news.setCreate_time(DateUtils.getDateTime());
                  news.setSource(Constant.NEWS_SOURCE_CSSTOCK);

                  for(News item:dataList){
                	  if(item.getTitle().equals(obj.getString("title"))){
                		  //System.out.println("----去除重复新闻,标题："+obj.getString("title")+","+DateUtils.formatDateTime(new Date()));
                		  b=false;
                	  }
                  }
                  
                  if(b==true){
                	  newsList.add(news);
                  }
                  
            }
            newsService.insertList(newsList);
           // System.out.println("增加"+newsList.size());
      }

      //新闻关键词和标题关联
      //@RequestMapping(value = "/newkey")
      @Scheduled(cron = "0 35 1/1 ? * *")
      @ResponseBody
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
                  map.put("start",i*size);
                  map.put("size",size);
                  List<News> newsList = newsService.getPageNewsOneK(map);
                  int total = 0;//统计插入的数量
                  for(News news:newsList){
                        //取到之后，用新闻标题循环比对关键词，插库
                        for(Keyword key:keywordList){
                              int n = newsService.getInNewsKeyword(news,key);
                              if(n>0){
                                    total = total+1;
                              }
                        }
                  }
                  Long currentTime = System.currentTimeMillis();
                  //阶段性结束的时间,超过执行20分钟就结束
                  Long endTime = System.currentTimeMillis();
                  if((endTime-startTime)>2000*60*10){
                	  System.out.println("-----------新闻关键词和标题关联执行超过10分钟，共执行："+(i+1)+"千条");
                	  return;
                  }
            }
            Long endTime = System.currentTimeMillis();
      }
}
