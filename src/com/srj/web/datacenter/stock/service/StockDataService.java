package com.srj.web.datacenter.stock.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.srj.common.utils.SysConstant;
import com.srj.web.datacenter.stock.mapper.StockDataMapper;
import com.srj.web.datacenter.stock.mapper.StockMapper;
import com.srj.web.datacenter.stock.model.StockData;
import com.srj.web.datacenter.stock.model.StockDetail;
import com.srj.web.util.DateUtils;
import com.srj.web.util.StringUtil;
import com.srj.web.util.TxtUtil;

@Service("stockDataService")
public class StockDataService {

	@Resource
	private StockMapper stockMapper;
	@Resource
	private StockDataMapper stockDataMapper;
	@Resource
	private StockService stockService;
	
	/*
	 * 分页显示行情列表
	 * */
	public PageInfo<StockData> findPageInfo(Map<String, Object> params) {
		PageHelper.startPage(params);
		List<StockData> list = stockDataMapper.findPageInfo(params);
		return new PageInfo<StockData>(list);
	}

	//执行计算存储过程
	public void CallProcedure() {
		System.out.println("执行存储过程CallProcedure");
		stockDataMapper.CallProcedure();
		
	}

	//通过上传文件添加记录
	public Integer saveTxt(String filedata) throws NumberFormatException, IOException {
		int count = 0;
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
			//行情列表对象
			List<StockData> list = new ArrayList<StockData>();
			//文件名是日期格式
			String datetime = DateUtils.formatDateFromYYYYmmDD(fileName);
			//读取行
			while ((strTxt = bufferedReader.readLine()) != null) {
				if(strTxt.length()>0&&StringUtil.isNumeric(strTxt.substring(0, 1))){//第一个字符是数字，说明是股票代码，此行数据有效
					String linetxt = TxtUtil.getLineTxt(strTxt);
					String[] txt = linetxt.split("\\*");//将txt文本按逗号分割成数组，分别处理
					String code = txt[0];//股票代码
					String stockName = txt[1];
					String stock_id = stockService.getStockId(code,stockName);//取得股票id
					String rise = txt[2];//涨幅
					String price = txt[3];//现价
					String turnover = txt[4];//换手
					String inflow = txt[5];//当日净流入
					String day_huge = txt[6];//当日超大单
					String day_large = txt[7];//当日大单
					String day_middle = txt[8];//当日中单
					String day_small = txt[9];//当日小单
					StockData item = new StockData();
					item.setStock_id(stock_id);
					item.setData_time(datetime);
					item.setRise(rise);
					item.setPrice(price);
					item.setTurnover(turnover);
					item.setDay_huge(day_huge);
					item.setDay_large(day_large);
					item.setDay_middle(day_middle);
					item.setDay_small(day_small);
					item.setData_time(datetime);
					item.setInflow(inflow);
					list.add(item);
				}
			}
			count = stockDataMapper.insertList(list);
		}
		
		return count;
	}
}
