package com.srj.web.datacenter.stock.controller;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.srj.common.utils.SysUserUtil;
import com.srj.web.datacenter.stock.service.StockService;
import com.srj.web.sys.model.SysUser;


@Controller
@RequestMapping("data")
public class StockDataController {

	@Resource
	private StockService stockService;
	/**
	 * 跳转到页面
	 */
	@RequestMapping
	public String toPage(Model model,Map<String, Object> params){
		SysUser u = SysUserUtil.getSessionLoginUser();
		model.addAttribute("params", params);
		return "datacenter/stock/stockData-manager";
	}
	
}
