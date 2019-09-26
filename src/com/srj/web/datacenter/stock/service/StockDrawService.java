package com.srj.web.datacenter.stock.service;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.srj.common.utils.OtherUtils;
import com.srj.common.utils.UUIDUtils;
import com.srj.web.datacenter.stock.mapper.StockMapper;
import com.srj.web.datacenter.stock.mapper.StockTradeMapper;
import com.srj.web.datacenter.stock.model.Stock;
import com.srj.web.datacenter.stock.model.StockSet;
import com.srj.web.datacenter.stock.model.StockTrade;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

@Service("stockDrawService")
public class StockDrawService {

	@Resource
	private StockMapper stockMapper;
	@Resource
	private StockTradeMapper stockTradeMapper;


	public JSONObject selectDraw(String stock_id, String type, String search_time) {
		JSONObject obj = new JSONObject();
		//价值列表X轴
		Set<String> priceArray = new HashSet<>();
		//买入
		List<String> B_Array = new ArrayList<>();
		List<String> S_Array = new ArrayList<>();
		//取出当日包含交易的所有价格段
		Map<String,Object> params = new HashMap<>();
		params.put("stock_id",stock_id);
		params.put("search_time",search_time);
		List<StockTrade> list = stockTradeMapper.selectList(params);
		//list判空
        if(list.size()<=0){
            obj.put("success","false");
            return obj;
        }
		//取出不重复的价值
		for(StockTrade item : list){
			priceArray.add(item.getPrice().toString());
		}
		//Set排序
		priceArray = OtherUtils.sortSet(priceArray);
		obj.put("priceArray",priceArray);
		//求BS集合，算法需要优化
		for(String price : priceArray){
			Float totalB = 0F;
			Float totalS = 0F;
			for(StockTrade item : list){
				if(item.getPrice().toString().equals(price)){
					if(item.getBs().equals("B")){
						totalB += item.getPrice();
					}
					if(item.getBs().equals("S")){
						totalS += item.getPrice();
					}
				}
			}
			B_Array.add(String.valueOf(totalB));
			S_Array.add(String.valueOf(0-totalS));
		}
		obj.put("BArray",B_Array);
		obj.put("SArray",S_Array);
        obj.put("success","true");
		return obj;
	}

	//找到该股票最近一天的日期
	public String selectLastTradeDate(String stock_id) {
		StockTrade record = stockTradeMapper.selectLastTradeDate(stock_id);
		//判空
		if(record==null){
			return null;
		}
		return record.getTrade_date();

	}
}
