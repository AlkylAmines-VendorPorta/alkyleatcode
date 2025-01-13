

$(function(){
    $.ajaxSetup({
    	 statusCode: {
             401: function(){
            	 location.reload(); // or window.location="http://www.example.com"
                 }
         }
    });
});


$( document ).ready(function() {
   $('.formSubmit').click(function(event){
	   /*  debugger; */
	   event.preventDefault();
	   var formObj = $(this).parents('form:first');
	   submitForm(formObj);
	   return false;
   });
 
});

function submitForm(formObj){
	/*  debugger; */
	var formData = formObj.serialize() ;
	var uri =formObj.attr( 'action' );
	$.ajax({
        type : "POST",
        data : formData, 
        url: uri,
        async: false,
        success: function (data) {
        	/*  debugger; */
        	try{
        		processResponse(data);
        	}catch(e){
        		console.log(e);
        		commonResponseHandler(data);
        	}
        },
        error: function (e) {
        	Alert.warn(e.responseText);
        }
    });
	//return false;
}

function submitIt(formId,action, callback){
	/*  debugger; */
	$("#loading-wrapper").show();
	var validate=checkValidationOnSubmit(formId);
	if(validate)
		{
		
			var formData = $('#'+formId).serialize();
			var uri =action;
			setTimeout(function(){
				$.ajax({
			
		        type : "POST",
		        data : formData,
		        url: uri,
		        async: false,
		        success: function (data) {
		        	/*  debugger; */
		        	
		        	try{
		       			window[callback](data);
		        	}catch(e){
		        		console.log(e);
		        		commonResponseHandler(data);
		        	}
		        },
		        beforeSend : function() {
		        	$("#loading-wrapper").show();
		        },
		        complete : function() {
		        	$("#loading-wrapper").fadeOut("slow");
		        },
		        error: function (e) {
		        	Alert.warn(e.responseText);
		        }
				},10);
		    });
			return false;
		}
	else{
		/*CreateCaptcha();*/
		$("#loading-wrapper").fadeOut("slow");
	}
	return false;
	
}

function submitWithParam(url, paramFieldId, callback){
	/*  debugger; */
	var val = $("#"+paramFieldId).val();
	url = url+'/'+val;
	$.ajax({
        type : "POST",
        contentType : "application/json",
        url: url,
        dataType:"json",
        async:false,
        success: function (data) {
        	/*  debugger; */
        	try{
        		window[callback](data);
        	}catch (e){
        		console.log(e);
        		urlResponseHandler(data);
        	}
        },
        error: function (e) {
        	Alert.warn("Exception: " + e.responseText);
        }
    });
	return false;
}
function submitWithTwoParam(url, firstParamFieldId,secondParamFieldId, callback){
	/*  debugger; */
	var firstValue = $("#"+firstParamFieldId).val();
	var secondValue = $("#"+secondParamFieldId).val();
	url = url+'/'+firstValue+'/'+secondValue;
	$.ajax({
        type : "POST",
        contentType : "application/json",
        url: url,
        dataType:"json",
        async:false,
        success: function (data) {
        	/*  debugger; */
        	try{
        		window[callback](data);
        	}catch (e){
        		console.log(e);
        		urlResponseHandler(data);
        	}
        },
        error: function (e) {
        	Alert.warn("Exception: " + e.responseText);
        }
    });
	return false;
}

function submitToURL(url, callback){
	$.ajax({
        type : "POST",
        contentType : "application/json",
        url: url,
        dataType:"json",
        async:false,
        success: function (data) {
        	try{
        		window[callback](data);
        	}catch (e){
        		console.log(e);
        		urlResponseHandler(data);
        	}
        },
        error: function (e) {
        	Alert.warn("Exception: " + e.responseText);
        }
    });
	return false;
}


function urlResponseHandler(data){
	if(data != null && data.message !=null){
		Alert.info (data.message);
	}else{
		Alert.warn("OK");
	}
}

function commonResponseHandler(data){
	/*  debugger; */
	/*CreateCaptcha();*/
	if(data != null && data.response !== null && data.response.message != null){
		Alert.info (data.response.message);
	}else{
		Alert.warn("OK");
	}	
}

function directSubmit(event,form, action){
	event.preventDefault();
	$("#"+form).attr( 'action', action );
	$("#"+form).submit();
}

function submitWithTwoParam(url, firstParamFieldId,secondParamFieldId, callback){
	/*  debugger; */
	var firstValue = $("#"+firstParamFieldId).val();
	var secondValue = $("#"+secondParamFieldId).val();
	url = url+'/'+firstValue+'/'+secondValue;
	$.ajax({
        type : "POST",
        contentType : "application/json",
        url: url,
        dataType:"json",
        async:false,
        success: function (data) {
        	/*  debugger; */
        	try{
        		window[callback](data);
        	}catch (e){
        		console.log(e);
        		urlResponseHandler(data);
        	}
        },
        error: function (e) {
        	Alert.warn("Exception: " + e.responseText);
        }
    });
	return false;
}
function submitWithThreeParam(url, name,paramFieldName,paramFieldId,callback){
	/*  debugger; */
	$("#loading-wrapper").show();
	var value = $("#"+paramFieldId).val();
	url = url+'/'+name+'/'+paramFieldName+'/'+value;
	setTimeout(function(){
	$.ajax({
        type : "POST",
        contentType : "application/json",
        url: url,
        dataType:"json",
        async:false,
        success: function (data) {
        	/*  debugger; */
        	try{
        		window[callback](data);
        	}catch (e){
        		console.log(e);
        		urlResponseHandler(data);
        	}
        },
        beforeSend : function() {
        	$("#loading-wrapper").show();
        },
        complete : function() {
        	$("#loading-wrapper").fadeOut("slow");
        },
        error: function (e) {
        	Alert.warn("Exception: " + e.responseText);
        }
	},10);
    });
	return false;
}
$(window).on('load',function(){
    $.ajaxSetup({
        statusCode: {
            401: function(){
                    location.reload(); // or window.location="http://www.example.com"
                }
        }
    });
});

function submitWithParamByParamClass(url, paramFieldClass, callback){
	
	var firstValue = $("."+paramFieldClass).val();
	url = url+'/'+firstValue;
	$.ajax({
        type : "POST",
        contentType : "application/json",
        url: url,
        dataType:"json",
        async:false,
        success: function (data) {
        	/*  debugger; */
        	try{
        		window[callback](data);
        	}catch (e){
        		console.log(e);
        		urlResponseHandler(data);
        	}
        },
        error: function (e) {
        	Alert.warn("Exception: " + e.responseText);
        }
    });
	return false;
}