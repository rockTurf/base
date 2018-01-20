package com.srj.web.tool;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.alibaba.fastjson.JSON;
import com.srj.common.constant.Constant;
import com.srj.common.utils.FileUtil;
import com.srj.common.utils.SysConstant;
import com.srj.common.utils.UUIDUtils;

@Controller
@RequestMapping("upload")
public class UploadController {
	
	@ResponseBody
	@RequestMapping("/uploadFile")
	public String uploadFile (HttpServletRequest request,HttpServletResponse response) throws IOException{
		MultipartHttpServletRequest multipartRequest  =  (MultipartHttpServletRequest) request;  
        MultipartFile file  =  multipartRequest.getFile("files");
        Map<String,String> map = new HashMap<String, String>();
	    String fileName = file.getOriginalFilename();//真实文件名
		int  startIndex =fileName.lastIndexOf(".");
		String suffix =fileName.substring(startIndex);//文件类型
	    String filePath = SysConstant.UploadUrl();//文件上传路径 (后加随机文件夹)
	    //文件真实路径:上传路径+随机文件夹+重构文件名随机字串时间戳+类型
	    String fileUrl = UUIDUtils.getRandomInteger(2)+"/"+UUIDUtils.getRandomString(4)+"-"+System.currentTimeMillis()+suffix;
	    File targetFile = new File(filePath, fileUrl);
	    if(!targetFile.exists()){  
            targetFile.mkdirs();  
        }  
        //保存  
        try {  
            file.transferTo(targetFile);  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        map.put(fileName, fileUrl);
        return JSON.toJSONString(map);
	}
	//删除文件
	@ResponseBody
	@RequestMapping("/deleteFile")
	public String deleteFile(@RequestParam Map<String, Object> params
			,Model model,HttpServletRequest request,HttpServletResponse response) throws IOException{
		String filename = (String) params.get("fileUrl");
		String filePath = SysConstant.UploadUrl();//文件上传路径 
		if(FileUtil.deleteFile(filePath+filename)==false){
			System.out.println("删除文件:"+filePath+filename+"失败！");
		}
		return null;
	}
	//临时文件上传到临时文件夹
	@ResponseBody
	@RequestMapping("/uploadTemp")
	public String uploadTemp (HttpServletRequest request,HttpServletResponse response) throws IOException{
		MultipartHttpServletRequest multipartRequest  =  (MultipartHttpServletRequest) request;  
        MultipartFile file  =  multipartRequest.getFile("files");
        Map<String,String> map = new HashMap<String, String>();
	    String fileName = file.getOriginalFilename();//真实文件名
		int  startIndex =fileName.lastIndexOf(".");
		String suffix =fileName.substring(startIndex);//文件类型
	    String filePath = SysConstant.TempUrl();//文件上传路径 (后加随机文件夹)
	    fileName = fileName.substring(0, startIndex);
	    //文件真实路径:temp文件夹下
	    String fileUrl = UUIDUtils.getUUID()+suffix;
	    File targetFile = new File(filePath, fileUrl);
	    if(!targetFile.exists()){  
            targetFile.mkdirs();  
        }  
        //保存  
        try {  
            file.transferTo(targetFile);  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        map.put(fileName, fileUrl);
        return JSON.toJSONString(map);
	}

}
