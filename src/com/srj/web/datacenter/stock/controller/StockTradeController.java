package com.srj.web.datacenter.stock.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.pagehelper.PageInfo;
import com.srj.common.utils.SysUserUtil;
import com.srj.web.datacenter.stock.model.Stock;
import com.srj.web.datacenter.stock.model.StockTrade;
import com.srj.web.datacenter.stock.service.StockService;
import com.srj.web.datacenter.stock.service.StockTradeService;
import com.srj.web.sys.model.SysUser;

@Controller
@RequestMapping("stockTrade")
public class StockTradeController {


	@Resource
	private StockService stockService;
	@Resource
	private StockTradeService stockTradeService;
	
	/**
	 * 跳转到页面
	 */
	@RequestMapping
	public String toPage(Model model,Map<String, Object> params){
		SysUser u = SysUserUtil.getSessionLoginUser();
		List<Stock> stockList = stockService.getAllStock();
		model.addAttribute("stockList", stockList);
		return "datacenter/stock/stockTrade-manager";
	}
	
	/**
	 * 分页显示列表
	 * 
	 * @param params
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "list", method = RequestMethod.POST)
	public String list(@RequestParam Map<String, Object> params, Model model) {
		PageInfo<StockTrade> page = stockTradeService.findPageInfo(params);
		model.addAttribute("page", page);
		return "datacenter/stock/stockTrade-list";
	}
}
