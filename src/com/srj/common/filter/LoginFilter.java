package com.srj.common.filter;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.srj.common.utils.SysUserUtil;
import com.srj.web.sys.model.SysUser;



public class LoginFilter implements Filter{

	@Override
	public void destroy() {
		
	}
	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req=(HttpServletRequest) request;
		HttpServletResponse res=(HttpServletResponse) response;
		
		//获得请求的URL
		String url=req.getRequestURL().toString();
		List<String> passlist = new ArrayList<String>();
		passlist.add(".jpg");
		passlist.add(".png");
		passlist.add(".gif");
		passlist.add(".css");
		passlist.add(".js");
		passlist.add(".ico");
		passlist.add(".woff");
		passlist.add(".tff");
		passlist.add("login");		
		passlist.add("register");//注册页面
		passlist.add("userRegist");//注册添加用户
		//获得session中的对象
		SysUser user=SysUserUtil.getSessionLoginUser();
		if(user==null){//如果session为空
			boolean b = false;//是否允许通过
			for(String pass:passlist){
				if(url.indexOf(pass)!=-1){
					//System.out.println("url:"+url+","+"user:"+user+" -------pass");
					b = true;//如果url匹配上面的地址栏，状态置为通过
					break;
				}
			}
			if(b){
				chain.doFilter(request, response);
			}else{
				System.out.println("url:"+url+","+"user:"+user+" --------REBUT");
				/*String str = "<script language='javascript'>alert('会话过期,请重新登录');"
				+ "window.top.location.href='login';</script>";*/
				//chain.doFilter(request, response);
				String str = "<script language='javascript'>window.top.location.href='login';</script>";
				response.setContentType("text/html; charset=UTF-8"); //转码
				PrintWriter writer = response.getWriter();
				writer.write(str);
				writer.flush();
				writer.close(); 
				//chain.doFilter(request, response);
			}
		}else{
			chain.doFilter(request, response);
		}
		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
	}

}
