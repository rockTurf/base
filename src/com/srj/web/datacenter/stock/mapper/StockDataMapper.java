package com.srj.web.datacenter.stock.mapper;

import java.util.List;
import java.util.Map;

import com.github.abel533.mapper.Mapper;
import com.srj.web.datacenter.stock.model.StockData;

public interface StockDataMapper extends Mapper<StockData>{

	List<StockData> findPageInfo(Map<String, Object> params);

	void CallProcedure();

	int insertList(List<StockData> list);


}
