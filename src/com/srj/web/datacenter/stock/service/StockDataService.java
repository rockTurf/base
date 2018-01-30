package com.srj.web.datacenter.stock.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.srj.web.datacenter.stock.mapper.StockDataMapper;
import com.srj.web.datacenter.stock.mapper.StockMapper;
import com.srj.web.datacenter.stock.model.StockData;

@Service("stockDataService")
public class StockDataService {

	@Resource
	private StockMapper stockMapper;
	@Resource
	private StockDataMapper stockDataMapper;
	
	/*
	 * 分页显示行情列表
	 * */
	public PageInfo<StockData> findPageInfo(Map<String, Object> params) {
		PageHelper.startPage(params);
		List<StockData> list = stockDataMapper.findPageInfo(params);
		return new PageInfo<StockData>(list);
	}
}
