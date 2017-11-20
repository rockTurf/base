//人员表单附件控件
 $('#signup-idimage2').uploadifive({
		        'auto' : true,
		        'uploadScript' : 'upload/uploadFile',
		        'fileObjName' : 'goodspic',
		        'buttonText' : '上传附件',
		        'queueID' : 'tip-queue2',
		        'fileType' : '*',//'image/*',
		        'multi' : true,
		        'fileSizeLimit'   : 5242880,
		        'uploadLimit' : 9,
		        'queueSizeLimit'  : 9,   
		        'onUploadComplete' : function(file, data) {
		        	 filemap.put(data);//添加到附件map
		        	 $("#apply_attach_dir2").val(filemap);
		        	 //console.info(filemap.toString());
		        },
		        onCancel : function(file) {
		            /* 注意：取消后应重新设置uploadLimit */
		            $data    = $(this).data('uploadifive'),
		            settings = $data.settings;
		            settings.uploadLimit++;
		            $.ajax({
						 type: "POST",
						 url:"upload/deleteFile",
						 dataType: 'JSON',
						 data:"filename="+filemap.get(file.name),
					}); 
		            filemap.remove(file.name);//删除附件map
		            $("#apply_attach_dir2").val(filemap);
		        },
		        onFallback : function() {
		            alert("该浏览器无法使用!");
		        },
		        onUpload : function(file) {
//		        	alert(file);
//		            document.getElementById("submit").disabled = true;//当开始上传文件，要防止上传未完成而表单被提交
		        },
		    });	


var fileArray = new Array();
function insertTr(){
	var html='';
	val = $("table[id$='table_people']>tbody").children("tr").length;
	fileArray[val] = new Map();
	  html+='<tr>';
	  html+='<td class="center" ><input datatype="pleaseFull"  name="apply_person"/ required="required"></td>';
      html+='<td class="center" ><input  datatype="*,idcard"  nullmsg="请填写负责人身份证号！" name="person_id"/></td>';
      html+='<td class="center" >';
      html+='<input type="text" name="proj_addr" id="'+val+'_address" />';
    //  html+='<div id="'+val+'_selectItem" name="selectItemhidden" style="overflow:scroll; width:160px; height:250px;" class="selectItemhidden">'; 
     // html+='<div  class="selectItemtit bgc_ccc">';
     // html+='<h2 class="selectItemleft" style="padding-top: 2px;">请选择</h2>'; 
      //html+='<div id="selectItemClose" class="selectItemright">关闭</div>';
     // html+='</div>';
      html+='<div id="" class="selectItemcont">';
      html+='<div id="'+val+'_selectSub" style="text-align: left;padding: 2px;">';
      //html+='<div><input type="checkbox" class="all" id="all-people_flow" value=""/><label for="all-people_flow">全部</label></div>';
     // html+='<div style="width: 100%;padding: 0px;" class="addresspeople_flow">';
      //$.each(arealist, function(idx, item) {
      //html+='&nbsp;<input type="checkbox" id="'+val+'_people_flow" name="gogogoall-people_flow" value="'+item+'"/><label for="'+val+'_people_flow">'+item+'</label></br>';
      //});
     // html+='</div>';
      html+='</div> ';
      html+='</div> ';
      html+='</div>';
      html+='</td>';
      html+='<td class="center" ><input datatype="pleaseFull" class="Wdate" onFocus="WdatePicker()" name="time_limit"/></td>';
      html+='<td class="center" >';
      html+='<label class="image-replace cd-photo2" for="'+val+'_signup-idimage2" style="display: none;"></label>';
      html+='<input class="full-width has-padding has-border"';
      html+='id="'+val+'_signup-idimage2" type="file" name="goodspic">';
      html+='<input type="text" name="fujian_dir" id="'+val+'_apply_attach_dir2">';
      html+='<div id="'+val+'_tip-queue2"></div>';
      html+='</td>';
      html+='<td class="center" ><input type="button" value="删除" onclick="deleteTr(this)" title="点击删除本记录"/></td>';
      html+='</tr>';
      html+='<script src="static/js/jquery.uploadifive.min.js" type="text/javascript"><\/script>';
      $("#table_people").append(html);
     // $("#"+val+"_address").selectCity("#"+val+"_selectItem");
      
      $('#'+val+'_signup-idimage2').uploadifive({
	        'auto' : true,
	        'uploadScript' : 'upload/uploadFile',
	        'fileObjName' : 'goodspic',
	        'buttonText' : '上传附件',
	        'queueID' : ''+val+'_tip-queue2',
	        'fileType' : '*',//'image/*',
	        'multi' : true,
	        'fileSizeLimit'   : 5242880,
	        'uploadLimit' : 9,
	        'queueSizeLimit'  : 9,   
	        'onUploadComplete' : function(file, data) {
	        	 fileArray[val].put(data);//添加到附件map
	        	 $("#"+val+"_apply_attach_dir2").val(fileArray[val]);
	        	 //console.info(fileArray);
	        },
	        onCancel : function(file) {
	            /* 注意：取消后应重新设置uploadLimit */
	            $data    = $(this).data('uploadifive'),
	            settings = $data.settings;
	            settings.uploadLimit++;
	            $.ajax({
					 type: "POST",
					 url:"upload/deleteFile",
					 dataType: 'JSON',
					 data:"filename="+fileArray[val].get(file.name),
				}); 
	            fileArray[val].remove(file.name);//删除附件map
	            $("#"+val+"_apply_attach_dir2").val(fileArray[val]);
	         //   alert(file.name + " 已取消上传~!");
	        },
	        onFallback : function() {
	            alert("该浏览器无法使用!");
	        },
	    });	
}
var set_id="";
jQuery.fn.selectCity = function(targetId) {
	var _seft = this;
	var targetId = $(targetId);

	this.click(function(){
	targetId.bgiframe();
	targetId.show().css({"position":"absolute","top":"auto" ,"left":"auto"});//,"left":A_left+"px"
	});
	targetId.find("#selectItemClose").click(function(){
		targetId.hide();
	});

	//targetId.find("#selectSub :checkbox").click(function(){		
	//	targetId.find(":checkbox").attr("checked",false);
	//	$(this).attr("checked",true);	
	//	_seft.val( $(this).val() );
		//targetId.hide();
	//});

	$(document).click(function(event){
		if(event.target.id!=_seft.selector.substring(1)){
			targetId.hide();	
		}
	});

	targetId.click(function(e){
		e.stopPropagation(); //  2
	});
	//选择点击单个按钮
	/*targetId.find("#"+val+"_selectSub :input[type='checkbox'][class!='all']").click(function(){
	var obj = $("input[type='checkbox'][class!='all']");
			var str=""
		    for(var i=0; i<obj.length; i ++){
		        if(obj[i].checked){
		        	obj[i].checked = true;
		            	if(i==0)
							str+=obj[i].value;
							else
							str+=","+obj[i].value;
		        }else{
		        	obj[i].checked = false;
		        }
		    }
		     if(str.indexOf(',')!=-1){
			 str=str.substr(1);
			 }
		$("#"+set_id).val(str);
		$("#"+set_id).attr("title",str);
	}); 
	//选择点击全选按钮
	targetId.find("#"+val+"_selectSub :input[type='checkbox'][class='all']").click(function(){
		console.info('getin'+val);
		var str = cli('gogogo'+this.id,this.id);
			_seft.val(str);
			$("input[class='area']").attr("title",str);
	})
    return this;*/
}


