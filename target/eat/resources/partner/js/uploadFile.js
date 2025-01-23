/**
 * Aman Sahu
 */

$(document).on("change",".uploadFile",function(){
debugger;
/*	var fileFieldId=$(this).attr('id');*/
	var fileFieldId=$(this);
  var selectId=$(this).data('id');
  var selectName=$(this).data('name');
  var anchorTag=$(this).data('anchor');
  var signed=$(this).data('signed');
  var flag=$(this).data('encrypt');
 /* if(flag!="Y"){
	  flag=="N"
  }*/
  var formData = new FormData();
	$.each(this.files,function(idx,obj){
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
	/*if(flag=="N"){*/
	if($.isEmptyObject(anchorTag)){
		sendRequest(formData,selectId,selectName,fileFieldId);
	}else{
		sendRequestForAnchor(formData,selectId, anchorTag,fileFieldId);
	}
	/*}else{
		sendRequestForProcDocument(formData,selectId, anchorTag,fileFieldId);
	}*/
});

function sendRequest(formData,firstParamField,secondParamField,fileFieldId){
	
debugger	
	$.ajax({
		type:"POST",
		url:"addAttachment",
		/*enctype:'multipart/form-data',*/
		async:true,
	    data:formData,
	    contentType:false,
	    processData: false,
	    success:function(response){
	    	debugger;
	    	console.log(response);
/*	    	$("#"+fileFieldId).removeClass("errorinput");*/
	    	fileFieldId.removeClass("errorinput");
	    	$("#"+firstParamField).val(response.attachmentId);
	    	$('.'+firstParamField).attr('disabled',false);
	    	$("#"+secondParamField).val(response.fileName);
	    	$("."+secondParamField).text(response.fileName);
	    	$('#myModal').modal('hide');
	    	Alert.info("file uploaded");
	    },
	    error: function(e){
	    	$('#myModal').modal('hide');
	    	Alert.warn("file not uploaded"+e);
	    }
		
	});
}


function sendRequestForAnchor(formData,attachmentField,anchorTag,fileFieldId){	
debugger	
	$.ajax({
		type:"POST",
		url:"addAttachment",
		/*enctype:'multipart/form-data',*/
		async:true,
	    data:formData,
	    contentType:false,
	    processData: false,
	    success:function(response){
	    	debugger;
	    	console.log(response);
	    	$('#myModal').modal('hide');
	    	/*$("#"+fileFieldId).removeClass("errorinput");*/
	    	fileFieldId.removeClass("errorinput");
	    	afterUpload(response,attachmentField,anchorTag);
	    },
	    error: function(e){
	    	
	    	$('#myModal').modal('hide');
	    	Alert.warn("file not saved"+e);
	    }
		
	});
}


function afterUpload(response,attachmentField,anchorTag){
		
	$("#"+attachmentField).val(response.attachmentId);
	$('.'+attachmentField).attr('disabled',false);
	var url=$("#"+anchorTag).data('url');
	$("#"+anchorTag).attr('href',url);
	var a_pnlFile = $("#"+anchorTag).prop('href')+'/'+response.attachmentId;
	$("#"+anchorTag).prop('href',a_pnlFile);
	$("#"+anchorTag).html(response.fileName);
	var resp=response.response;
	if(!isEmpty(resp) && resp.hasError){
		Alert.warn(resp.message);
	}else{
		Alert.info("file saved");
	}

}

function resetFileInput(attachmentField,anchorTag){
	$("input[data-id='"+attachmentField+"']").val('');
	$("#"+attachmentField).val();
	$('.'+attachmentField).attr('disabled',true);
	$("#"+anchorTag).prop('href','');
	$("#"+anchorTag).html('');
}

function showProgressBarModal()
{
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
function checkFileSize(fileId)
{
	debugger;
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
function checkForMinSize(fileId)
{
	debugger;
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