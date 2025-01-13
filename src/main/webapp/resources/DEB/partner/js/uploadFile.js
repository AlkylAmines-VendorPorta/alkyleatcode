
var url="";
$(document).ready(function(){
$('.uploadFile').on("change",function(){
	uploadFile(this);
});

$('.uploadImageFile').on("change",function(){
	uploadImageFile(this);
});

});

function uploadFile(el){
	/*	var fileFieldId=$(el).attr('id');*/
	debugger;
	if($(el).hasClass("conf")){
		url="addSignedAttachment";
	}else{
		url="addAttachment";
	}
	  var fileFieldId=$(el);
	  var selectId=$(el).data('id');
	  var selectName=$(el).data('name');
	  var anchorTag=$(el).data('anchor');
	  var signed=$(el).data('signed');
	 /* var flag=$(el).data('encrypt');*/
	  var flag='N';
	  var isAsync=$(el).data('async');
	 /* if(flag!="Y"){
		  flag=="N"
	  }*/
	  var formData = new FormData();
		$.each(el.files,function(idx,obj){
			 formData.append('file', obj);
		});
		formData.append('flag',flag);
		if(checkForMinSize(fileFieldId)){
		   if($.isEmptyObject(signed)){
			if(checkFileSize(fileFieldId))
				{
				   return;
				}
			}
		}else{
			return;
		}
		$('#myModal').modal('show');
		showProgressBarModal();		
		if(isAsync=="N"){
			if($.isEmptyObject(anchorTag)){
				sendRequest(formData,selectId,selectName,fileFieldId,url);
			}else{
				sendRequestForAnchor(formData,selectId, anchorTag,fileFieldId,url);
			}
		}else{
			if($.isEmptyObject(anchorTag)){
				sendRequestAsync(formData,selectId,selectName,fileFieldId,url);
			}else{
				sendRequestForAnchorAsync(formData,selectId, anchorTag,fileFieldId,url);
			}
		}

}

function uploadImageFile(el){
	debugger;
	  var fileFieldId=$(el);
	  var selectId=$(el).data('id');
	  var selectName=$(el).data('name');
	  var anchorTag=$(el).data('anchor');
	  var flag='N';
	  var formData = new FormData();
		$.each(el.files,function(idx,obj){
			 formData.append('file', obj);
		});
		formData.append('flag',flag);
		$('#myModal').modal('show');
		showProgressBarModal();		
		if($.isEmptyObject(anchorTag)){
				sendRequestAsync(formData,selectId,selectName,fileFieldId,"addImageAttachment");
		}else{
				sendRequestForAnchorAsync(formData,selectId, anchorTag,fileFieldId,"addImageAttachment");
		}
}

function sendRequest(formData,firstParamField,secondParamField,fileFieldId,url){
	$.ajax({
		type:"POST",
		url:url,
		async:false,
	    data:formData,
	    contentType:false,
	    processData: false,
	    success:function(response){
	    	debugger;
	    	fileFieldId.removeClass("errorinput");
	    	$("#"+firstParamField).val(response.attachmentId);
	    	$('.'+firstParamField).attr('disabled',false);
	    	$("#"+secondParamField).val(response.fileName);
	    	$("."+secondParamField).text(response.fileName);
	    	$('#myModal').modal('hide');
	    	Alert.info("File is attached, Pls save changes");
	    },
	    error: function(ex){
	    	$('#myModal').modal('hide');
	    	uploadFileExceptionHandler(ex) 
	    }
		
	});
}


function sendRequestForAnchor(formData,attachmentField,anchorTag,fileFieldId,url){
	$.ajax({
		type:"POST",
		//url:"addAttachment",
		url:url,
		/*enctype:'multipart/form-data',*/
		async:false,
	    data:formData,
	    contentType:false,
	    processData: false,
	    success:function(response){
	    	$('#myModal').modal('hide');
	    	/*$("#"+fileFieldId).removeClass("errorinput");*/
	    	fileFieldId.removeClass("errorinput");
	    	afterUpload(response,attachmentField,anchorTag);
	    },
	    error: function(ex){
	    	$('#myModal').modal('hide');
	    	uploadFileExceptionHandler(ex) 
	    }
		
	});
}


function sendRequestAsync(formData,firstParamField,secondParamField,fileFieldId,url){
		$.ajax({
			type:"POST",
			//url:"addAttachment",
			url:url,
			/*enctype:'multipart/form-data',*/
			async:true,
		    data:formData,
		    contentType:false,
		    processData: false,
		    success:function(response){
	/*	    	$("#"+fileFieldId).removeClass("errorinput");*/
		    	fileFieldId.removeClass("errorinput");
		    	$("#"+firstParamField).val(response.attachmentId);
		    	$('.'+firstParamField).attr('disabled',false);
		    	$("#"+secondParamField).val(response.fileName);
		    	$("."+secondParamField).text(response.fileName);
		    	$('#myModal').modal('hide');
		    	Alert.info("File is attached, Pls save changes");
		    },
		    error: function(ex){
		    	$('#myModal').modal('hide');
		    	uploadFileExceptionHandler(ex) 
		    }
			
		});
	}


	function sendRequestForAnchorAsync(formData,attachmentField,anchorTag,fileFieldId,url){
		$.ajax({
			type:"POST",
			//url:"addAttachment",
			url:url,
			/*enctype:'multipart/form-data',*/
			async:true,
		    data:formData,
		    contentType:false,
		    processData: false,
		    success:function(response){
		    	$('#myModal').modal('hide');
		    	/*$("#"+fileFieldId).removeClass("errorinput");*/
		    	fileFieldId.removeClass("errorinput");
		    	afterUpload(response,attachmentField,anchorTag);
		    },
		    error: function(ex){
		    	$('#myModal').modal('hide');
		    	uploadFileExceptionHandler(ex) 
		    }
			
		});
	}

function afterUpload(response,attachmentField,anchorTag){
	var resp=response.response;
	if(!isEmpty(resp) && resp.hasError){
		Alert.warn(resp.message);
	}else{
		$("#"+attachmentField).val(response.attachmentId);
		$('.'+attachmentField).attr('disabled',false);
		var url=$("#"+anchorTag).data('url');
		$("#"+anchorTag).attr('href',url);
		var a_pnlFile = $("#"+anchorTag).prop('href')+'/'+response.attachmentId;
		$("#"+anchorTag).prop('href',a_pnlFile);
		$("#"+anchorTag).html(response.fileName);
		Alert.info("File is attached, Pls save changes");
	}

}

function resetFileInput(attachmentField,anchorTag){
	$("input[data-id='"+attachmentField+"']").val('');
	$("#"+attachmentField).val('');
	$('.'+attachmentField).attr('disabled',true);
	$("#"+anchorTag).prop('href','');
	$("#"+anchorTag).html('');
}

function showProgressBarModal(){
	var progress = setInterval(function() {
	    var $bar = $('.bar');

	    if ($bar.width()==500) {
	      
	        clearInterval(progress);
	        $('.progress').removeClass('active');
	        $('#myModal').modal('hide');
	        $bar.width(0);
	        
	    } else {
	       $bar.width($bar.width()+50);
	    }
	    
	    $bar.text($bar.width()/5 + "%");
		}, 800);
}
/*$(document).ready(function(){
	$('.removeFile').click(function(event) {
		debugger;
		event.preventDefault();
		var file=$(this).data('file');
		var fieldId=$(this).data('id');
	    submitWithParam('deleteAttachment',fieldId,'attachmentDeleteResp');
	    
		
		
		$('#'+file).val('');
		$('#'+fieldId).val('');
		swal("File Removed");
		alert("khjjhj");
	});

});*/
function checkFileSize(fileId){
	var maxSize=parseInt($("#fileSize").val());/*1048576;*/
	if(maxSize!=undefined || maxSize!=""){
		if(fileId.get(0).files.length){
	        var fileSize = fileId.get(0).files[0].size; /* in bytes */
	        if(fileSize>maxSize){
	        	var sizeInMB = (maxSize / (1024*1024)).toFixed(2);
	        	 Alert.warn('File size exceeds'+sizeInMB+'MB');
	        	return true;
	        }else{
	        	return false;
	        }
		}else{
			Alert.warn('No file selected');
			return false;
		}	
	}else{
		return true;
	}
}
function checkForMinSize(fileId){
	if(fileId.get(0).files.length){
        var fileSize = fileId.get(0).files[0].size;
        var size=0;
        if(fileSize<=size){
        	Alert.warn('Empty File');
        	return false;
        }else{
        	return true;
        } 
	}else{
		return false;
	}
}

function uploadFileExceptionHandler(ex){
	var status=ex.status;
	if(status==401 || status==405 || status==403){
		/*window.location.href="sessionOut";*/
		Alert.warn("Exception: "+ex.responseText);
	}
	else if(status==200){
		Alert.info("Exception: "+ex.responseText);
	}else{
		Alert.warn("Exception: "+ex.responseText);	
	}
}