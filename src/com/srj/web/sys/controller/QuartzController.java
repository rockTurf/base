package com.srj.web.sys.controller;

import com.srj.web.util.SpiderUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("quartz")
public class QuartzController {


      @RequestMapping(value = "/news")
      public void getNews(){
            SpiderUtils.getifeng();
      }
}
