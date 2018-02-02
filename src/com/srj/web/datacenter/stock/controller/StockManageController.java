package com.srj.web.datacenter.stock.controller;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.srj.common.utils.SysUserUtil;
import com.srj.web.datacenter.stock.model.Stock;
import com.srj.web.datacenter.stock.model.StockSet;
import com.srj.web.datacenter.stock.service.StockService;
import com.srj.web.sys.model.SysUser;

@Controller
@RequestMapping("stock")
public class StockManageController {
	
	@Resource
	private StockService stockService;
	/**
	 * 跳转到页面
	 */
	@RequestMapping
	public String toPage(Model model,Map<String, Object> params){
		SysUser u = SysUserUtil.getSessionLoginUser();
		model.addAttribute("params", params);
		return "datacenter/stock/stock-manager";
	}
	
	/**
	 * 分页显示股票列表
	 * 
	 * @param params
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "list", method = RequestMethod.POST)
	public String list(@RequestParam Map<String, Object> params, Model model) {
		PageInfo<Stock> page = stockService.findPageInfo(params);
		model.addAttribute("page", page);
		return "datacenter/stock/stock-list";
	}
	/**
	 * 新增股票
	 */
	@RequestMapping(value = "save")
	public @ResponseBody Integer save(@ModelAttribute Stock record,@RequestParam Map<String, Object> params,
			Model model,HttpServletRequest request,HttpServletResponse response){
		SysUser u = SysUserUtil.getSessionLoginUser();
		return stockService.saveRecord(record);
	}

	/**
	 * 删除
	 */
	@RequestMapping(value = "delete")
	public @ResponseBody Integer delete(@RequestParam Long id,Model model,HttpServletRequest request,HttpServletResponse response){
		//删除文章
		int count = stockService.deleteRecord(id);
		return count;
	}
	/**
	 * 跳转到设置大中小单页面
	 */
	@RequestMapping(value = "setting")
	public String toSettingPage(@RequestParam Long id,Model model,HttpServletRequest request,HttpServletResponse response){
		//股票详情
		Stock stock = stockService.SelectRecordById(id);
		//大中小单值
		StockSet stockSet = stockService.SelectSettingById(id);
		model.addAttribute("stock", stock);
		model.addAttribute("stockSet", stockSet);
		return "datacenter/stock/stock-setting";
	}
	/**
	 * 设置大中小单
	 */
	@RequestMapping(value = "updateSet")
	public @ResponseBody Integer updateStockSet(@RequestParam Map<String, Object> params){
		return stockService.updateStockSet(params);
	}
}
