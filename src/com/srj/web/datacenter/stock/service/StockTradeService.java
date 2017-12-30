package com.srj.web.datacenter.stock.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.srj.common.excel.template.ExcelUtils;
import com.srj.common.utils.SysConstant;
import com.srj.web.datacenter.stock.mapper.StockMapper;
import com.srj.web.datacenter.stock.mapper.StockTradeMapper;
import com.srj.web.datacenter.stock.model.Stock;
import com.srj.web.datacenter.stock.model.StockPrice;
import com.srj.web.datacenter.stock.model.StockTrade;

@Service("stockTradeService")
public class StockTradeService {

	@Resource
	private StockMapper stockMapper;
	@Resource
	private StockTradeMapper stockTradeMapper;
	
	ExcelUtils excelUtils=new ExcelUtils();
	//分页显示列表
	public PageInfo<StockTrade> findPageInfo(Map<String, Object> params) {
		PageHelper.startPage(params);
		List<StockTrade> list = stockTradeMapper.findPageInfo(params);
		return new PageInfo<StockTrade>(list);
	}

	//模板加载
	public Integer saveFile(Map<String, Object> params) throws IOException,
	FileNotFoundException{
		int result = 0;
		//读取服务器文件进行操作
		File file = new File(SysConstant.TempUrl()+params.get("fileUrl"));
		Workbook book = null;
		try{
		    book = new XSSFWorkbook(new FileInputStream(file));
		} catch (Exception ex) {
		    book = new HSSFWorkbook(new FileInputStream(file));
		}
		Sheet s1=book.getSheetAt(0);
		//操作开始
		int lastRowNum = s1.getLastRowNum();//从表第三行开始读起
		//行情对象
		List<StockTrade> list = new ArrayList<>();
		//从第二行开始读
		for(int i=1;i<=lastRowNum;i++){
			Row row = s1.getRow(i);
			StockTrade arg0 = new StockTrade();
			String time=excelUtils.getHHmmTimeValue(row.getCell(0));//时间
			time = params.get("trade_time")+" "+time;//加上年月日
			String price = excelUtils.getSmallIntegerValue(row.getCell(1));//价格
			String deal = excelUtils.getSmallIntegerValue(row.getCell(2));//成交
			String count = excelUtils.getSmallIntegerValue(row.getCell(3));//笔数
			String bs = excelUtils.getCellStringValue(row.getCell(4));//BS
			//赋值
			arg0.setTime(time);
			arg0.setPrice(Float.parseFloat(price));
			arg0.setDeal(deal);
			arg0.setCount(Integer.parseInt(count));
			arg0.setBs(bs);
			arg0.setStock_id(Long.parseLong((String) params.get("stock_id")));
			list.add(arg0);
		}
		result = stockTradeMapper.insertList(list);
		return result;
	}

}
