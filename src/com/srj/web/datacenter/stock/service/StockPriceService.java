package com.srj.web.datacenter.stock.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
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
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import com.srj.common.excel.template.ExcelUtils;
import com.srj.common.utils.SysConstant;
import com.srj.common.utils.UUIDUtils;
import com.srj.web.datacenter.stock.mapper.StockMapper;
import com.srj.web.datacenter.stock.mapper.StockPriceMapper;
import com.srj.web.datacenter.stock.model.Stock;
import com.srj.web.datacenter.stock.model.StockPrice;
import com.srj.web.util.DateUtils;
import com.srj.web.util.StringUtil;
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
		for(StockPrice sp:list){
			sp.set("totalAmount",ExcelUtils.changeBigNumber(sp.getTotal_amount()));
		}
		return new PageInfo<StockPrice>(list);
	}
	
	//模板加载
	public Integer saveFile(String filedata) throws IOException,
	FileNotFoundException{
		int count = 0;
		Map<String,String> filemap = StringUtil.jsonToMap(filedata);//取得文件名和文件地址map
		String fileName = "";
		String fileUrl ="";
		if(filemap.size()!=1){
			return 0;
		}
		//取得文件名和地址
		for(Entry<String, String> vo : filemap.entrySet()){ 
			fileName = vo.getKey(); 
			fileUrl = vo.getValue(); 
        }
		String datetime = DateUtils.formatDate(DateUtils.parseDate(getLast8Integer(fileName)));
		//定位文件
		File file = new File(SysConstant.TempUrl()+fileUrl);
		//读取内容
		Workbook book = null;
		try{
		    book = new XSSFWorkbook(new FileInputStream(file));
		} catch (Exception ex) {
			try{
				book = new HSSFWorkbook(new FileInputStream(file));
			}catch (Exception e) {
				e.printStackTrace();
			}
		    
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
			String code=excelUtils.getStockCodeValue(row.getCell(0));//股票代码
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
			Long stock_id = Long.parseLong(UUIDUtils.getRandomInteger(12));//股票id,12位UUID
			if(StringUtils.isEmpty(sid)){
				stock.setId(stock_id);
				stock.setCode(code);
				stock.setName(stockName);
				stock.setIndustry(industry);
				stock.setArea(area);
				stockMapper.insert(stock);
			}else{
				stock_id = Long.parseLong(sid);
			}
			//给stockPrice行情实体类加数据
			sp.setId(Long.parseLong(UUIDUtils.getRandomInteger(12)));//12位UUID
			sp.setPrice_date(datetime);
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
			sp.setCreate_time(DateUtils.formatDateTime(new Date()));
			count = stockPriceMapper.insert(sp);
			if(count==0){
				System.out.println("第"+i+"行出错！");
			}
		}
		return count;
	}
	
	// 模板加载
	public Integer saveTxt(String filedata) throws IOException,FileNotFoundException {
		int count = 0;
		List<String> filelist = StringUtil.String2List(filedata);
		String[] array = null;
		// 循环取得文件名和地址
		for (String vo : filelist) {
			array = vo.split("=");
			String fileName = array[0];
			String fileUrl = array[1];
			String datetime = DateUtils.formatDate(DateUtils.parseDate(getLast8Integer(fileName)));
			// 定位文件
			File file = new File(SysConstant.TempUrl() + fileUrl);
			String encoding = "GBK";
			InputStreamReader read = new InputStreamReader(new FileInputStream(file), encoding);// 考虑到编码格式
			BufferedReader bufferedReader = new BufferedReader(read);
			String strTxt = null;
			//行情列表对象
			List<StockPrice> splist = new ArrayList<StockPrice>();
			//股票对象
			Stock stock = new Stock();
			//读取行
			while ((strTxt = bufferedReader.readLine()) != null) {
				if(StringUtil.isNumeric(strTxt.substring(0, 1))){
					String linetxt = getLineTxt(strTxt);
					String[] txt = linetxt.split(",");//将txt文本按逗号分割成数组，分别处理
					String code=txt[0];//股票代码
					String stockName = txt[1];//股票名称
					String rise = txt[2];//涨幅
					String presentPrice = txt[3];//现价
					String rise_full = txt[4];//涨跌
					String buyPrice=txt[5];//买价
					String salePrice = txt[6];//卖价
					String totalHand = txt[7];//总量
					String nowHand = txt[8];//现象
					String risingSpeed = txt[9];//涨速
					String turnOver=txt[10];//换手
					String open = txt[11];//今开
					String highest = txt[12];//最高
					String lowest = txt[13];//最低
					String prevClose = txt[14];//昨收
					String prowave=txt[15];//市盈(动)
					String totalAmont = txt[16];//总金额
					String qrr = txt[17];//量比
					String industry = txt[18];//细分行业				
					String area = "";//地区
					if(txt.length>19){
						area = txt[19];
					}
					//验证股票代码是否在stock表中存在，如存在则使用该id，不存在则新建
					String sid = stockMapper.findStockIdByCode(code);
					Long stock_id = Long.parseLong(UUIDUtils.getRandomInteger(12));//股票id,12位UUID
					if(StringUtils.isEmpty(sid)){
						stock.setId(stock_id);
						stock.setCode(code);
						stock.setName(stockName);
						stock.setIndustry(industry);
						stock.setArea(area);
						stockMapper.insert(stock);
					}else{
						stock_id = Long.parseLong(sid);
					}
					//给stockPrice行情实体类加数据
					//行情对象
					StockPrice sp = new StockPrice();
					//sp.setId(Long.parseLong(UUIDUtils.getRandomInteger(12)));//12位UUID
					sp.setPrice_date(datetime);
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
					sp.setCreate_time(DateUtils.formatDateTime(new Date()));
					splist.add(sp);
				}
			}
			count = stockPriceMapper.insertList(splist);
		}
		
		return count;
	}
		
	
	
	//提取出字符串中的后八位日期数字
	public static String getLast8Integer(String str) {
		str = str.substring(str.length()-8, str.length());
		return str;
	}
	//处理txt字符串(将tab替换为逗号并去空格返回字符串)
	public static String getLineTxt(String str){
		String[] lineArr = str.split("\\t");			
		String newline="";
		for (int i = 0; i < lineArr.length; i++){
		    if (i<lineArr.length){
		        newline+=lineArr[i]+",";
		    }else{
		    	newline+=newline+lineArr[i];
		    }
		}
		return newline.replaceAll("\\s*", "");
	}
}
