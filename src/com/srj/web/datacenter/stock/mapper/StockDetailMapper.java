package com.srj.web.datacenter.stock.mapper;

import java.util.List;
import java.util.Map;

import com.github.abel533.mapper.Mapper;
import com.srj.web.datacenter.stock.model.StockDetail;

public interface StockDetailMapper extends Mapper<StockDetail>{

	List<StockDetail> findPageInfo(Map<String, Object> params);

	Integer insertList(List<StockDetail> list);

}
