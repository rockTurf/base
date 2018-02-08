package com.srj.web.datacenter.stock.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
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
import com.srj.web.util.DateUtils;
import com.srj.web.util.StringUtil;
import com.srj.web.util.TxtUtil;

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
	public Integer saveTxt(String filedata) throws IOException,FileNotFoundException {
		int result = 0;
		List<String> filelist = StringUtil.String2List(filedata);
		String[] array = null;
		// 循环取得文件名和地址
		for (String vo : filelist) {
			array = vo.split("=");
			String fileName = array[0];
			String fileUrl = array[1];
			// 定位文件
			File file = new File(SysConstant.TempUrl() + fileUrl);
			String encoding = "GBK";
			InputStreamReader read = new InputStreamReader(new FileInputStream(file), encoding);// 考虑到编码格式
			BufferedReader bufferedReader = new BufferedReader(read);
			String strTxt = null;
			//交易日期
			String trade_date = DateUtils.formatDate(DateUtils.parseDate(fileName.split("_")[0]));
			//股票代码
			String code = fileName.split("_")[1];
			//验证股票代码是否在stock表中存在，如存在则使用该id，不存在则返回-100
			String sid = stockMapper.findStockIdByCode(code);
			if(sid==null){
				return -100;
			}
			//行情对象
			List<StockTrade> list = new ArrayList<>();
			//读取行
			while ((strTxt = bufferedReader.readLine()) != null) {
				if(strTxt.length()>3&&":".equals(strTxt.substring(2, 3))){//行第三个字符是冒号，说明是时间，此行数据有效
					String linetxt = TxtUtil.getLineTxt(strTxt);
					StockTrade arg0 = new StockTrade();
					String[] txt = linetxt.split("\\*");//将txt文本按逗号分割成数组，分别处理
					String trade_time=txt[0];//时间
					String price = txt[1];//价格
					String deal = txt[2];//成交
					String count = txt[3];//笔数
					String bs = "-";//BS
					if(txt.length>4){
						bs = txt[4];
					}
					//赋值
					arg0.setTrade_date(trade_date);
					arg0.setTrade_time(trade_time);
					arg0.setPrice(Float.parseFloat(price));
					arg0.setDeal(deal);
					arg0.setCount(Integer.parseInt(count));
					arg0.setBs(bs);
					arg0.setStock_id(Long.parseLong(sid));
					list.add(arg0);
				}
			}
			result = stockTradeMapper.insertList(list);
		}
		return result;
	}

	public List<String> checkTradeData(String id){
		return stockTradeMapper.checkTradeData(id);
	}
}
