package com.srj.web.datacenter.stock.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.srj.web.datacenter.stock.mapper.StockMapper;
import com.srj.web.datacenter.stock.model.Stock;

@Service("stockService")
public class StockService {

	@Resource
	private StockMapper stockMapper;
	/*
	 * 分页显示股票列表
	 * */
	public PageInfo<Stock> findPageInfo(Map<String, Object> params) {
		PageHelper.startPage(params);
		List<Stock> list = stockMapper.findPageInfo(params);
		return new PageInfo<Stock>(list);
	}
	/*
	 * 新增股票
	 * */
	public Integer saveRecord(Stock record) {
		return stockMapper.insertSelective(record);
	}
	/*
	 * 删除股票
	 * */
	public Integer deleteRecord(Long id) {
		return stockMapper.deleteByPrimaryKey(id);
	}
	
}
