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
import com.srj.web.util.StringUtil;
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
	@Resource
	private StockTradeService stockTradeService;
	/**
	 * 跳转到页面
	 */
	@RequestMapping
	public String toPage(Model model,Map<String, Object> params){
		SysUser u = SysUserUtil.getSessionLoginUser();
		List<Stock> stockList = stockService.getAllStock();
		//取出最新的单只股票的单日交易情况
		//1.先取出库里最新记录日期和股票id
		StockTrade lastOne = stockTradeService.getNewestRecord();
		//这条记录的股票id
		String stock_id = lastOne.getStock_id().toString();
		String trade_date = lastOne.getTrade_date();
		//绘制初始化图案(按照默认量价交易【值为1】来计算
		JSONObject obj = stockDrawService.selectDraw(stock_id,"1",trade_date);

		model.addAttribute("stockList", stockList);
		model.addAttribute("obj",obj);
		return "datacenter/stock/stock-draw";
	}

	/**
	 *
	 * 绘制图案
	 *
	 */
	@RequestMapping(value = "draw")
	public @ResponseBody JSONObject DrawInit(@RequestParam(required = false)String stock_id,
		@RequestParam(required = false)String type,@RequestParam(required = false) String search_time){
		JSONObject obj = new JSONObject();
		/**
		 * 先判断参数
		 * 1.如果日期为空而股票id不为空，则找到该股票最近一天的数据
		 */
		if(StringUtil.isEmpty(search_time)){
			//查询到日期
			search_time = stockDrawService.selectLastTradeDate(stock_id);
		}
		//量价交易
		obj = stockDrawService.selectDraw(stock_id,type,search_time);
		obj.put("search_time",search_time);
		obj.put("success","true");
		return obj;
	}
}
