package com.srj.web.datacenter.stock.controller;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.pagehelper.PageInfo;
import com.srj.common.utils.SysUserUtil;
import com.srj.web.datacenter.stock.model.StockData;
import com.srj.web.datacenter.stock.model.StockDetail;
import com.srj.web.datacenter.stock.service.StockDataService;
import com.srj.web.datacenter.stock.service.StockService;
import com.srj.web.sys.model.SysUser;


@Controller
@RequestMapping("stockData")
public class StockDataController {

	@Resource
	private StockService stockService;
	@Resource
	private StockDataService stockDataService;
	/**
	 * 跳转到页面
	 */
	@RequestMapping
	public String toPage(Model model,Map<String, Object> params){
		SysUser u = SysUserUtil.getSessionLoginUser();
		model.addAttribute("params", params);
		return "datacenter/stock/stockData-manager";
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
		PageInfo<StockData> page = stockDataService.findPageInfo(params);
		model.addAttribute("page", page);
		return "datacenter/stock/stockData-list";
	}
	
}
