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

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.alibaba.druid.util.StringUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.srj.common.excel.template.ExcelUtils;
import com.srj.common.utils.SysConstant;
import com.srj.common.utils.UUIDUtils;
import com.srj.web.datacenter.stock.mapper.StockDetailMapper;
import com.srj.web.datacenter.stock.mapper.StockMapper;
import com.srj.web.datacenter.stock.model.StockDetail;
import com.srj.web.util.DateUtils;
import com.srj.web.util.StringUtil;
import com.srj.web.util.TxtUtil;
import com.srj.web.util.UpAndDownUtils;

@Service("stockDetailService")
public class StockDetailService {

	@Resource
	private StockMapper stockMapper;
	@Resource
	private StockDetailMapper stockDetailMapper;
	
	ExcelUtils excelUtils=new ExcelUtils();
	/*
	 * 分页显示行情列表
	 * */
	public PageInfo<StockDetail> findPageInfo(Map<String, Object> params) {
		PageHelper.startPage(params);
		List<StockDetail> list = stockDetailMapper.findPageInfo(params);
		return new PageInfo<StockDetail>(list);
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
			// 定位文件
			File file = new File(SysConstant.TempUrl() + fileUrl);
			String encoding = "GBK";
			InputStreamReader read = new InputStreamReader(new FileInputStream(file), encoding);// 考虑到编码格式
			BufferedReader bufferedReader = new BufferedReader(read);
			String strTxt = null;
			//行情列表对象
			List<StockDetail> list = new ArrayList<StockDetail>();
			//文件名就是股票代码
			String stock_id = stockMapper.findStockIdByCode(fileName);
			//读取行
			while ((strTxt = bufferedReader.readLine()) != null) {
				if(strTxt.length()>0&&StringUtil.isNumeric(strTxt.substring(0, 1))){//第一个字符是数字，说明是股票代码，此行数据有效
					String linetxt = TxtUtil.getLineTxt(strTxt);
					String[] txt = linetxt.split("\\*");//将txt文本按逗号分割成数组，分别处理
					String datetime=txt[0].split(",")[0];//时间
					String open = txt[1];//开盘
					String highest = txt[3];//最高
					String lowest = txt[5];//最低
					String close = txt[7];//收盘
					String rise = txt[9];//涨幅
					String swing = txt[11];//振幅
					String total_hand = TxtUtil.getBigInteger(txt[13]);//总手
					String amount = TxtUtil.getBigInteger(txt[14]);//金额
					String change_hand = txt[15];//换手
					String deal= txt[17];//成交次数
					StockDetail sd = new StockDetail();
					sd.setDetail_date(datetime);
					sd.setOpen(open);
					sd.setHighest(highest);
					sd.setLowest(lowest);
					sd.setClose(close);
					sd.setRise(rise);
					sd.setSwing(swing);
					sd.setTotal_hands(total_hand);
					sd.setChange_hand(change_hand);
					sd.setAmount(amount);
					sd.setDeal(deal);
					sd.setStock_id(Long.parseLong(stock_id));
					list.add(sd);
				}
			}
			count = stockDetailMapper.insertList(list);
		}
		
		return count;
	}
		
	
	
	//提取出字符串中的后八位日期数字
	public static String getLast8Integer(String str) {
		str = str.substring(str.length()-8, str.length());
		return str;
	}
	
}
