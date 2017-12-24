package com.srj.web.datacenter.stock.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.alibaba.druid.util.StringUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.srj.common.excel.template.ExcelUtils;
import com.srj.common.utils.SysConstant;
import com.srj.web.datacenter.stock.mapper.StockMapper;
import com.srj.web.datacenter.stock.mapper.StockPriceMapper;
import com.srj.web.datacenter.stock.model.Stock;
import com.srj.web.datacenter.stock.model.StockPrice;
import com.srj.web.util.UpAndDownUtils;

@Service("stockPriceService")
public class StockPriceService {

	@Resource
	private StockMapper stockMapper;
	@Resource
	private StockPriceMapper stockPriceMapper;
	
	ExcelUtils excelUtils=new ExcelUtils();
	/*
	 * 分页显示行情列表
	 * */
	public PageInfo<StockPrice> findPageInfo(Map<String, Object> params) {
		PageHelper.startPage(params);
		List<StockPrice> list = stockPriceMapper.findPageInfo(params);
		return new PageInfo<StockPrice>(list);
	}
	
	//模板加载
	public Integer saveFile(String fileUrl) throws IOException,
	FileNotFoundException{
		int count = 0;
		
		//读取服务器文件进行操作
		File file = new File(SysConstant.TempUrl()+fileUrl);
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
		StockPrice sp = new StockPrice();
		//股票对象
		Stock stock = new Stock();
		//从第二行开始读
		for(int i=1;i<=lastRowNum;i++){
			Row row = s1.getRow(i);
			String code=excelUtils.getCellStringValue(row.getCell(0));//股票代码
			String stockName = excelUtils.getCellStringValue(row.getCell(1));//股票名称
			String rise = excelUtils.getSmallIntegerValue(row.getCell(2));//涨幅
			String presentPrice = excelUtils.getSmallIntegerValue(row.getCell(3));//现价
			String rise_full = excelUtils.getSmallIntegerValue(row.getCell(4));//涨跌
			String buyPrice=excelUtils.getSmallIntegerValue(row.getCell(5));//买价
			String salePrice = excelUtils.getSmallIntegerValue(row.getCell(6));//卖价
			String totalHand = excelUtils.getSmallIntegerValue(row.getCell(7));//总量
			String nowHand = excelUtils.getSmallIntegerValue(row.getCell(8));//现象
			String risingSpeed = excelUtils.getSmallIntegerValue(row.getCell(9));//涨速
			String turnOver=excelUtils.getSmallIntegerValue(row.getCell(10));//换手
			String open = excelUtils.getSmallIntegerValue(row.getCell(11));//今开
			String highest = excelUtils.getSmallIntegerValue(row.getCell(12));//最高
			String lowest = excelUtils.getSmallIntegerValue(row.getCell(13));//最低
			String prevClose = excelUtils.getSmallIntegerValue(row.getCell(14));//昨收
			String prowave=excelUtils.getSmallIntegerValue(row.getCell(15));//市盈(动)
			String totalAmont = excelUtils.getBigIntegerValue(row.getCell(16));//总金额
			String qrr = excelUtils.getSmallIntegerValue(row.getCell(17));//量比
			String industry = excelUtils.getCellStringValue(row.getCell(18));//细分行业
			String area = excelUtils.getCellStringValue(row.getCell(19));//地区
			
			//验证股票代码是否在stock表中存在，如存在则使用该id，不存在则新建
			String sid = stockMapper.findStockIdByCode(code);
			Long stock_id = 0L;//股票id
			if(StringUtils.isEmpty(sid)){
				stock.setCode(code);
				stock.setName(stockName);
				stock.setIndustry(industry);
				stock.setArea(area);
				stockMapper.insert(stock);
				stock_id = stock.getId();
			}else{
				stock_id = Long.parseLong(sid);
			}
			//给stockPrice行情实体类加数据
			sp.setRise(rise);
			sp.setPresent_price(presentPrice);
			sp.setRise_full(rise_full);
			sp.setBuy_price(buyPrice);
			sp.setSale_price(salePrice);
			sp.setTotal_hand(totalHand);
			sp.setNow_hand(nowHand);
			sp.setRising_speed(risingSpeed);
			sp.setTurnover(turnOver);
			sp.setOpen(open);
			sp.setHighest(highest);
			sp.setLowest(lowest);
			sp.setPrev_close(prevClose);
			sp.setProwave(prowave);
			sp.setTotal_amount(totalAmont);
			sp.setQrr(qrr);
			sp.setStock_id(stock_id);
			/*if(temp2>1){
				sp.setId(temp2+1);//自增
			}*/
			count = stockPriceMapper.insert(sp);
			if(count==0){
				System.out.println("第"+i+"行出错！");
			}
		}
		return count;
	}
}
