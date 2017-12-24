package com.srj.common.excel.template;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.Cell;

import com.srj.common.excel.template.utils.PoiUtil;
import com.srj.common.utils.FileUtil;


@SuppressWarnings({ "rawtypes", "unchecked" })
public class ExcelUtils {

	/**
	 * 导出excel,普通形式(流的形式直接下载)
	 * @param response
	 * @param fileName 下载的excel文件名
	 * @param data 数据
	 * @param titleMap (key:"列名",value = "属性名")
	 */
	public static void exportExcel(HttpServletResponse response,
			String fileName, List<?> data, Map titleMap) throws Exception {
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		PoiUtil.writeExcel(data, os, titleMap);
		os.flush();
		byte[] buf = os.toByteArray();
		InputStream is = new ByteArrayInputStream(buf, 0, buf.length);
		FileUtil.downloadFile(response, is, fileName);
		is.close();
		os.close();
	}
	//不带标题导出excel
	public static void exportExcelWithOutTitle(HttpServletResponse response,
			String fileName, List<?> data, Map titleMap) throws Exception {
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		PoiUtil.writeExcelWithOutTitle(data, os, titleMap);
		os.flush();
		byte[] buf = os.toByteArray();
		InputStream is = new ByteArrayInputStream(buf, 0, buf.length);
		FileUtil.downloadFile(response, is, fileName);
		is.close();
		os.close();
	}


	/**
	 * 导出excel,模板形式(流方式直接下载)
	 * @param response
	 * @param templatePath 模板excel的路径
	 * @param fileName 导出excel文件名
	 * @param data 数据map
	 */
	public static void exportExcel(HttpServletResponse response,
			String templatePath,String fileName, Map data) throws Exception {
		ByteArrayOutputStream os = new ByteArrayOutputStream();;
		PoiTemplate template = new PoiTemplate(templatePath,os);
		template.addMap(data);
		template.writeExcel();
		os.flush();
		byte[] buf = os.toByteArray();
		InputStream is = new ByteArrayInputStream(buf, 0, buf.length);
		FileUtil.downloadFile(response, is, fileName);
		is.close();
		os.close();
	}
	
	
	public String getCellStringValue(Cell cell) {      
        String cellValue = "";
        cellValue = cell.getStringCellValue().trim(); 
        cellValue = cellValue.replaceAll("\"","");
       /* if(cell==null)
        	return "";
        switch (cell.getCellType()) {      
        case Cell.CELL_TYPE_STRING://字符串类型   
            cellValue = cell.getStringCellValue().trim();      
            if(cellValue ==null ||"".equals(cellValue.trim()))      
                cellValue="";      
            break;      
        case Cell.CELL_TYPE_NUMERIC: //数值类型   
        	 if(HSSFDateUtil.isCellDateFormatted(cell)){//yyyy-MM-dd, d/m/yyyy h:mm, HH:mm 等不含文字的日期格式
        		 Date date = new Date(0) ;
        		 date = HSSFDateUtil.getJavaDate(new Double( cell.getNumericCellValue()));
        		 SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        		 cellValue = df.format(date);
        	 }else{
        		 DecimalFormat df = new DecimalFormat("#");//将1.700001234E10转换成整型的字符串
        		 cellValue = df.format(cell.getNumericCellValue());
        	 }
        	 
            break;      
        case Cell.CELL_TYPE_FORMULA: //公式   
            // DataFormatter formatter = new DataFormatter();     
            // cellValue = formatter.formatCellValue(cell);
    	    try {
    		    cellValue = String.valueOf(cell.getNumericCellValue());
    	    } catch (IllegalStateException e) {
    		    cellValue = String.valueOf(cell.getNumericCellValue());
    	    }
            break;      
        case Cell.CELL_TYPE_BLANK:      
            cellValue="";      
            break;      
        case Cell.CELL_TYPE_BOOLEAN:      
            break;      
        case Cell.CELL_TYPE_ERROR:      
            break;      
        default:      
            break;      
        }  */    
        return cellValue;      
    }   
	//大数值
	public String getBigIntegerValue(Cell cell) { 
		 DecimalFormat df = new DecimalFormat("#");//将1.700001234E10转换成整型的字符串
		 return df.format(cell.getNumericCellValue());
	}
	//带小数点的值
	public String getSmallIntegerValue(Cell cell){
		String cellValue="";
		cell.setCellType(Cell.CELL_TYPE_STRING);
        String temp = cell.getStringCellValue();  
        // 判断是否包含小数点，如果不含小数点，则以字符串读取，如果含小数点，则转换为Double类型的字符串  
        if (temp.indexOf(".") > -1) {  
        	cellValue = String.valueOf(new Double(temp)).trim();  
        } else {  
        	cellValue = temp.trim();  
        }  
   	 
		return cellValue;
	}
	
	//将1.700001234E10转换成整型的字符串
	public static String changeBigNumber(String num){
		BigDecimal bd = new BigDecimal(num);
		return bd.toPlainString();
	}
	
	/**  
     * 使用java正则表达式去掉多余的.与0  
     * @param s  
     * @return   
     */    
    public static int subZeroAndDot(String s){    
        if(s.indexOf(".") > 0){    
            s = s.replaceAll("0+?$", "");//去掉多余的0    
            s = s.replaceAll("[.]$", "");//如最后一位是.则去掉    
        }    
        return Integer.parseInt(s);    
    }    

}
