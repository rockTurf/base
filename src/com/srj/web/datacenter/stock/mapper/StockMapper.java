package com.srj.web.datacenter.stock.mapper;

import java.util.List;
import java.util.Map;

import com.github.abel533.mapper.Mapper;
import com.srj.web.datacenter.stock.model.Stock;
import com.srj.web.datacenter.stock.model.StockSet;
import com.srj.web.datacenter.stock.model.StockTrade;

public interface StockMapper extends Mapper<Stock>{

	List<Stock> findPageInfo(Map<String, Object> params);

	String findStockIdByCode(String code);

	List<Stock> getAll();

	Integer updateStockSet(Map<String, Object> params);

	StockSet selectSettingById(Long id);

}
