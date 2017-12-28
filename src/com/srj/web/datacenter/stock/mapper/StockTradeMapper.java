package com.srj.web.datacenter.stock.mapper;

import java.util.List;
import java.util.Map;

import com.github.abel533.mapper.Mapper;
import com.srj.web.datacenter.stock.model.StockTrade;

public interface StockTradeMapper extends Mapper<StockTrade>{
	
	List<StockTrade> findPageInfo(Map<String, Object> params);

}
