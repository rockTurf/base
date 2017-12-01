package com.srj.web.datacenter.stock.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.srj.web.datacenter.stock.mapper.StockMapper;
import com.srj.web.datacenter.stock.mapper.StockPriceMapper;
import com.srj.web.datacenter.stock.model.Stock;
import com.srj.web.datacenter.stock.model.StockPrice;

@Service("stockPriceService")
public class StockPriceService {

	@Resource
	private StockMapper stockMapper;
	@Resource
	private StockPriceMapper stockPriceMapper;
	/*
	 * 分页显示行情列表
	 * */
	public PageInfo<StockPrice> findPageInfo(Map<String, Object> params) {
		PageHelper.startPage(params);
		List<StockPrice> list = stockPriceMapper.findPageInfo(params);
		return new PageInfo<StockPrice>(list);
	}
	
}
