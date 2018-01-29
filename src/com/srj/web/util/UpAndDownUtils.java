package com.srj.web.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartFile;


/**   
 * 上传下载工具类(导出和导入)
 *
 * @author zkk
 * @date 2015年9月8日14:25:51   
 */
public class UpAndDownUtils {

	
	 /**
     * @author zkk
     * @throws IOException 
     * @date 2015年7月29日14:40:29
     * @desc 上传的Excel文件保存 
     */
    public String fileSave(MultipartFile file, String fileName,HttpServletRequest request) throws IOException{
   	       InputStream stream = file.getInputStream() ;
   	       String path = request.getSession().getServletContext().getRealPath("/files/"+fileName);
           FileOutputStream fs=new FileOutputStream(path);
           byte[] buffer =new byte[1024*1024];
           int bytesum = 0;
           int byteread = 0; 
           while ((byteread=stream.read(buffer))!=-1)
           {
              bytesum+=byteread;
              fs.write(buffer,0,byteread);
              fs.flush();
           } 
           fs.close();
           stream.close();  
           
           return path;
   	 
    }
    
    public void fileDown(String srcName,String dscName,HttpServletRequest request,HttpServletResponse response) throws IOException{
       
    	File f=new File(request.getSession().getServletContext().getRealPath("/files/"+srcName));
       
        OutputStream outp = null;
		FileInputStream br = null;
		int len = 0;
		try {
			br = new FileInputStream(f);
			response.reset();
			outp = response.getOutputStream();
			response.setContentLength((int) f.length());
			String header = (false ? "inline" : "attachment") + ";filename="
					+ new String(dscName.getBytes("utf-8"), "ISO8859-1");
			response.addHeader("Content-Disposition", header);
			byte[] buf = new byte[1024];
			while ((len = br.read(buf)) != -1) {
				outp.write(buf, 0, len);
			}
			outp.flush();
			outp.close();
		//	f.delete();
		} finally {
			if (br != null) {
				if (0 == br.available()) {
					br.close();
				}
			}
		}
 }




	
	
	
}
