function checkKeyword(){
	name = $('#name').val();
	$.ajax({
		//async:false,
		type: "POST",
		url: 'keyword/checkKeyword',
    	data: {
    		name:name
    	},
		dataType:'json',
		cache: true,
		success: function(data){ 
			$("#result").html('');
			if(data=="0"){
				$("#result").append('<font color="green"><i class="fa fa-check-circle-o fa-2x"></i></font>');
				$("save-btn").remove("display");
			}else{
				$("#result").append('<font color="red"><i class="fa fa-times fa-2x"></i></font>');
				$("save-btn").attr("display","none");
			}		
		}
	});
}

function submitForm(){
	name = $('#name').val();
	$.ajax({
		//async:false,
		type: "POST",
		url: 'keyword/save',
    	data: {
    		name:name
    	},
		dataType:'json',
		cache: true,
		success: function(data){ 
			if(data>0){
				console.info(data);
			}else{
				console.info(data);
			}		
		}
	});
	
}