package com.srj.web.datacenter.stock.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.srj.common.utils.UUIDUtils;
import com.srj.web.datacenter.stock.mapper.StockMapper;
import com.srj.web.datacenter.stock.model.Stock;
import com.srj.web.datacenter.stock.model.StockSet;

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
	/*
	 * 查找所有股票
	 * */
	public List<Stock> getAllStock(){
		List<Stock> list = stockMapper.getAll();
		return list;
	}
	/*
	 * 设置股票交易的大中小单数
	 * */
	public Integer updateStockSet(Map<String, Object> params){
		return stockMapper.updateStockSet(params);
	}
	//根据id取出单支股票信息
	public Stock SelectRecordById(Long id) {
		return stockMapper.selectByPrimaryKey(id);
	}
	public StockSet SelectSettingById(Long id) {
		return stockMapper.selectSettingById(id);
	}
	//根据code和股票名称取得id，如果没有就新增并返回id
	public String getStockId(String code, String stockName) {
		String stock_id = stockMapper.findStockIdByCode(code);
		if(stock_id!=null){
			return stock_id;
		}
		//如果为空，则新增一个
		Long id = Long.parseLong(UUIDUtils.getRandomInteger(12));
		Stock stock = new Stock();
		stock.setId(id);
		stock.setCode(code);
		stock.setName(stockName);
		stockMapper.insertSelective(stock);
		return id.toString();
	}
	
	
}
