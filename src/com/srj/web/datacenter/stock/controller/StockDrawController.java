package com.srj.web.datacenter.stock.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.srj.common.utils.SysUserUtil;
import com.srj.web.datacenter.stock.model.Stock;
import com.srj.web.datacenter.stock.model.StockTrade;
import com.srj.web.datacenter.stock.service.StockDataService;
import com.srj.web.datacenter.stock.service.StockDrawService;
import com.srj.web.datacenter.stock.service.StockService;
import com.srj.web.datacenter.stock.service.StockTradeService;
import com.srj.web.sys.model.SysUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("stockDraw")
public class StockDrawController {

	@Resource
	private StockService stockService;
	@Resource
	private StockDrawService stockDrawService;
	/**
	 * 跳转到页面
	 */
	@RequestMapping
	public String toPage(Model model,Map<String, Object> params){
		SysUser u = SysUserUtil.getSessionLoginUser();
		List<Stock> stockList = stockService.getAllStock();
		model.addAttribute("stockList", stockList);
		return "datacenter/stock/stock-draw";
	}

	/**
	 *
	 * 绘制图案
	 *
	 */
	@RequestMapping(value = "draw")
	public @ResponseBody JSONObject DrawInit(String stock_id,String type,String search_time){
		JSONObject obj = new JSONObject();
		//量价交易
		obj = stockDrawService.selectDraw(stock_id,type,search_time);

		obj.put("success","true");
		return obj;
	}
}
