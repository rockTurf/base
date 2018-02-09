package com.srj.web.datacenter.stock.controller;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.srj.common.utils.SysUserUtil;
import com.srj.web.datacenter.stock.model.Stock;
import com.srj.web.datacenter.stock.model.StockTrade;
import com.srj.web.datacenter.stock.service.StockDataService;
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
	@Resource
	private StockDataService stockDataService;
	
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
	
	/**
	 * 通过上传的添加行情列表
	 * 
	 * @param params
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "addStockTrade")
	public @ResponseBody Integer addByExcel(@RequestParam String filedata,Model model,HttpServletResponse res) throws IOException, ParseException{
		int count = stockTradeService.saveTxt(filedata,res);
		//如果成功，执行计算的存储过程
		stockDataService.CallProcedure();
		
		return count;
	}
	/**
	 * 检查数据完整性
	 * 
	 * @param params
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "checkTradeData")
	public @ResponseBody List<String> checkTradeData(@RequestParam String id){
		List<String> list = stockTradeService.checkTradeData(id);
		return list;
	}
	
}
