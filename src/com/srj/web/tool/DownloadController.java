package com.srj.web.tool;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.srj.common.utils.FileUtil;
import com.srj.common.utils.SysConstant;

@Controller
@RequestMapping("download")
public class DownloadController {

	@RequestMapping("model")
	public void model(String filename,String model,HttpServletResponse response,HttpServletRequest request) throws Exception{
		 //采购项目进展
		 if("keyproject".equals(model)){
			 filename="采购项目进展表格（模板）.xls";
		 }else if("duty".equals(model)){
			 filename="xxxx年x月值班表（模板）.xlsx";
		 }else if("stock".equals(model)){
			 filename="库存模板.xlsx";
		 }else if("equipment".equals(model)){
			 filename="设备模板.xlsx";
		 }else if("project".equals(model)){
			 filename="备案表含项目模板.xlsx";
		 }else if("noProject".equals(model)){
			 filename="备案表不含项目模板.xlsx";
		 }
		File previewFile = new File(SysConstant.DownloadUrl()+filename); 
		FileUtil.downloadFile(filename, response, previewFile); 
	}
	
	
	//附件下载
	@RequestMapping("file")
	public void downLoadFile(String fileUrl,HttpServletResponse response,HttpServletRequest request) throws Exception{
		File previewFile = new File(SysConstant.UploadUrl()+fileUrl); 
		//取得文件名
		String temp[] = fileUrl.replaceAll("\\\\","/").split("/"); 
		if (temp.length > 1) { 
			fileUrl = temp[temp.length - 1]; 
		}
		FileUtil.downloadFile(fileUrl, response, previewFile); 
	}


	

}
