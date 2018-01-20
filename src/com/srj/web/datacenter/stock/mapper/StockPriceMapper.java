package com.srj.web.datacenter.stock.mapper;

import java.util.List;
import java.util.Map;

import com.github.abel533.mapper.Mapper;
import com.srj.web.datacenter.stock.model.Stock;
import com.srj.web.datacenter.stock.model.StockPrice;

public interface StockPriceMapper extends Mapper<StockPrice>{

	List<StockPrice> findPageInfo(Map<String, Object> params);

	Integer insertList(List<StockPrice> list);

}
