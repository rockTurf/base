package com.srj.common.utils;

import java.util.Properties;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

public class SysConstant {
	private static Resource resource = new ClassPathResource("resources.properties");
	private static Properties props = null;
	static{
		try {
			props = PropertiesLoaderUtils.loadProperties(resource);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	public static Properties getConfigPropertis() {
		return props;
	}
	public static String getValue(String key) {
		if(null!=key&&!"".equals(key)&&props.containsKey(key)){
			return (String) props.get(key);
		}
		return null;
	}
	
	public static String UploadUrl(){
		return SysConstant.getValue("upload.rootPath");
	}
	public static String DownloadUrl(){
		return SysConstant.getValue("download.rootPath");
	}
	public static String APPUrl(){
		return SysConstant.getValue("app.rootPath");
	}
	public static String getVersionApkFile(){
		return SysConstant.getValue("versionApkFile");
	}
	public static String getVersionIOSFile(){
		return SysConstant.getValue("versionIOSFile");
	}


	public static int BEFORE_YEAR = 5;
	public static int AFTER_YEAR = 0;
}
