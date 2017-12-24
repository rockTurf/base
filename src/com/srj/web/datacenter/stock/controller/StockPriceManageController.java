package com.srj.web.datacenter.stock.controller;

import java.io.IOException;
import java.text.ParseException;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.srj.common.utils.SysUserUtil;
import com.srj.web.datacenter.stock.model.Stock;
import com.srj.web.datacenter.stock.model.StockPrice;
import com.srj.web.datacenter.stock.service.StockPriceService;
import com.srj.web.datacenter.stock.service.StockService;
import com.srj.web.sys.model.SysUser;

@Controller
@Transactional
@RequestMapping("stockPrice")
public class StockPriceManageController {
	
	@Resource
	private StockService stockService;
	@Resource
	private StockPriceService stockPriceService;
	/**
	 * 跳转到页面
	 */
	@RequestMapping
	public String toPage(Model model,Map<String, Object> params){
		SysUser u = SysUserUtil.getSessionLoginUser();
		model.addAttribute("params", params);
		return "datacenter/stock/stockPrice-manager";
	}
	
	/**
	 * 分页显示行情列表
	 * 
	 * @param params
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "list", method = RequestMethod.POST)
	public String list(@RequestParam Map<String, Object> params, Model model) {
		PageInfo<StockPrice> page = stockPriceService.findPageInfo(params);
		model.addAttribute("page", page);
		return "datacenter/stock/stockPrice-list";
	}
	/**
	 * 通过上传的添加行情列表
	 * 
	 * @param params
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "addStockPrice")
	public @ResponseBody Integer addByExcel(@RequestParam String fileUrl,Model model) throws IOException, ParseException{
		
		return stockPriceService.saveFile(fileUrl);
	}
	
	
	
	
}
