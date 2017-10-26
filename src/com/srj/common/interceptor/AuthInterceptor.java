package com.srj.common.interceptor;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;


public class AuthInterceptor implements HandlerInterceptor {

   private Set<String> ignorePath = new HashSet<String>
    (Arrays.asList(
    		"/FlowApply/save_peoples",
    		"/SecondProcurement/appSecondProcurement",
    		"/KeyProject/appKeyProject",
    		"/onestage/purchaseCenter",
    		"/onestage/appPark",
    		"/ParkReport/ParkLog",
    		"/DutyManager/appDutyManager",
    		"/App", 
    		"/applogin",
    		"/loginapp",
    		"/getversionapk","/getversionipa",
    		"/FirstProcurement/appFirstProcurement",
    		"/toAppIndex",
    		"/onestage/dataCenter",
    		"/mobile/checkNotice",
    		"/FlowApply/appflow",
    		"/onestage/dataCenter",
    		"/upload/uploadGoodsPic",
    		"/FlowApply/appflow",
    		"/upload/uploadfiles",//App上传附件接口
    		"/FlowApply/selectCheck",//流程状态列表数据接口
    		"/FlowApply/add_apply",//流程申请回显数据接口
    		"/updatepassword",//普通用户修改密码接口
    		"/FlowApply/showdetails",//流程审批详情接口
    		//流程保存提交接口
    		"/FlowApply/save_people",
    		"/FlowApply/save_good",
    		"/FlowApply/temporary_flows",
    		"/FlowApply/save_fires",
    		"/FlowApply/save_builds",
    		"/FlowApply/save_visitors",
    		"/FlowApply/save_stairways",
    		"/FlowApply/save_lights",
    		"/FlowApply/save_overtimes",
    		"/FlowApply/detail_save",
    		"/FlowApply/test",
    		"/sessionIsHave",//检测session状态
    		//订单模块接口App
    		"/foodapp/findFoodList",//获取菜单
    		"/foodapp/saveOrder",//保存订单
    		"/foodapp/findOrdersList",//订单查询
    		"http://api.map.baidu.com/api?v=2.0&ak=wWy2A8K94nhntYTYUHS19RXW",
    		"/foodapp/selectAllAndDay",//今日菜单and本周菜单
    		"/foodapp/detailorders",//订单详情接口
    		"/pcarapp",//大巴预约车辆列表
    		"/pcarapp/saveYuyue",//保存提交预约车
    		"/pcarapp/sjhandle",
    		"/pcarapp/checkresult",
    		"/pcarapp/siji_check",//司机审批
    		//连接H5页面
    		"/cpropApp/newslist",//宣传稿件
    		"/csuperviseapp",//督办通告
    		"/cinspectionapp",//纪检监察
    		"/MeetRoomApp",//会议室预订
    		"/cinstitutionApp/list",//部门制度
    		"/onestage/checkInfo",//一期园区，查看明细
    		"/mealapp/getinfo",//接待餐查询接口
    		"/mealapp/savemealquest",//接待餐申请保存接口
    		"/mealapp/saveresult",//接待餐审批接口
    		"/perpairApp/arrlist",//维修申报app页面列表查询
    		"/perpairApp/details",
    		"/mealapp/tocheckuser",
    		"/pquestionnaireApp",//问卷
    		"/pcarapp/savegps",//保存GPS定位坐标
    		"/cinstitutionApp/initiallist",
    		"/pquestionnaireApp/finish",
    		"?",
    		"/pquestionnaireApp/addwenjuan",//移动端添加问卷
    		"/worderapp",
    		"/taskApp",
    		"/inspectApp",
    		"/stockInfApp",
    		"/stockManageApp",
    		"/stockOutApp",
    		"/stockEnterApp",
    		"/stockCheck",
    		"/stockLogApp",
    		"/resTypeApp",
    		"/stockAuditApp",
    		"/flowManageApp"//审批
    		));

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {

    	String uri = request.getRequestURI(); //请求路径
        String ctx = request.getContextPath();
        String path = uri.substring(ctx.length());
        Iterator<String> it = ignorePath.iterator();
    	while (it.hasNext()) {
    		Object x = it.next();
    		if(x.toString().equals(path)){
            	return true;
            }else
    		if(x.toString().contains("?")){
    		   	return true;	
    		}else if(x.toString().contains("pcarapp")||x.toString().contains("worderapp")||x.toString().contains("inspectApp")){
    			return true;
    		}else if(x.toString().contains("taskApp")){
    			return true;
    		}
    	}
        
     /*   if("/App".equals(path)){
        	return true;
        }
        if("/applogin".equals(path)){
            return true;
        }
        //登录接口
        if("/loginapp".equals(path)){
            return true;
        }
        //下载apk新版本接口
        if("/getversionapk".equals(path)){
            return true;
        }
        if("/FirstProcurement/appFirstProcurement".equals(path)){
            return true;
        }*/
        //if(!ignorePath.contains(path)){
            //获得session中的登陆用户
         /*   SysUser sessionUser = SysUserUtils.getSessionLoginUser();
            
            //获得缓存中的登陆用户
            SysUser cacheUser = SysUserUtils.getCacheLoginUser();
//	   	     JSONObject jsonobj = new JSONObject(userjson);  
//	   	     String username=jsonobj.getString("username");
//            if(sessionUser==null){
//            	sessionUser = SysUserUtils.getCookieLoginUser(username);
//            }
//            
            
            if ((sessionUser == null || cacheUser == null)) { // 转到登陆页面
            
				response.sendRedirect(ctx + "/notlogin");
            	 
                return false;
            } else {
            	
                Map<String, SysResource> allRes = BeetlUtils
                        .getBeetlSharedVars(Constant.CACHE_ALL_RESOURCE);
                String perPath = path.substring(1);
                SysResource sysResource = allRes.get(perPath);
                //判断如果url不在数据库中，则默认都有权限访问
                if (sysResource == null
                        || Constant.RESOURCE_COMMON.equals(sysResource.getCommon())) {
                    return true;
                }
                
                //实时的权限验证,检测用户认证是否改变，如果认证改变则重置，否则不进行任何操作
                SysUserUtils.setUserAuth();
                
                //从缓存中的用户权限 map<url:resource>
                Map<String, SysResource> userRes = SysUserUtils.getUserResources();
                if (userRes.containsKey(perPath)) { //有权限则过
                    return true;
                } else { //没有权限跳到没有权限
                    response.sendRedirect(ctx + "/notauth");
                    return false;
                }
            }*/
        //}
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request,
                           HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
    }

}
