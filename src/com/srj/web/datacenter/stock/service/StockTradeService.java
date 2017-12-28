package com.srj.web.datacenter.stock.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.srj.common.excel.template.ExcelUtils;
import com.srj.web.datacenter.stock.mapper.StockMapper;
import com.srj.web.datacenter.stock.mapper.StockTradeMapper;
import com.srj.web.datacenter.stock.model.StockPrice;
import com.srj.web.datacenter.stock.model.StockTrade;

@Service("stockTradeService")
public class StockTradeService {

	@Resource
	private StockMapper stockMapper;
	@Resource
	private StockTradeMapper stockTradeMapper;
	
	//分页显示列表
	public PageInfo<StockTrade> findPageInfo(Map<String, Object> params) {
		PageHelper.startPage(params);
		List<StockTrade> list = stockTradeMapper.findPageInfo(params);
		return new PageInfo<StockTrade>(list);
	}

}
