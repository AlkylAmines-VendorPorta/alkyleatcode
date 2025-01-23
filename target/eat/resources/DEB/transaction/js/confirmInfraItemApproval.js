var infraLineArray=new Array();

function onTabInfraConfirmLoad(event){
	
	event.preventDefault();
	var bPartnerId=$('.bPartnerId').val();
	var infraItemId=$('.partnerInfraItemId').val();
	if(bPartnerId!='' && infraItemId!=''){
	 submitToURL('getInfraLine?partnerId='+bPartnerId+'&infraItemId='+infraItemId, 'onTabInfraConfirmLoadResp');
	}else{
		Alert.warn('Something went wrong');
		event.stopPropagation();
	}
}
function createInfraLine(event){
	
	event.preventDefault();
	var bPartnerId=$('.bPartnerId').val();
	var infraItemId=$('.partnerInfraItemId').val();
	if(bPartnerId!='' && infraItemId!=''){
	 submitToURL('createInfraLine?partnerId='+bPartnerId+'&infraItemId='+infraItemId, 'onTabInfraConfirmLoadResp');
	}else{
		Alert.warn('Something went wrong');
	}
}
function saveInfraApproval(event){
	
	event.preventDefault();
	submitIt('approavalForm','saveInfraLine','saveInfraConfirmation');
}
function onTabInfraConfirmLoadResp(data){
	
	console.log(data);
	if(data.objectMap.hasOwnProperty('result')){
		if(!data.objectMap.result)
			{
			  if(data.objectMap.message!=undefined)
			  { Alert.warn(data.objectMap.message); 
			  }
			  return;
			}else{
				if(data.objectMap.message!=undefined)
				Alert.info(data.objectMap.message);
				
				/* $("#infraVendorTab").trigger('click');*/
			}
	}
	if(data.objectMap.hasOwnProperty('infraLineList')){
		loadInfraConfirmLeftPane(data.objectMap.infraLineList);
	}
	
}

function loadInfraConfirmLeftPane(data){
	
	$('.pagination').children().remove();
	$(".leftPaneData").html("");
	var leftPanelHtml="";
	var i=0;
	var active=false;
	var firstRow=null;
	if(data==null || data.length==0){
		  $("#confirmComment").text("");
		  setStatus("",'confirmApprove','confirmClarify','confirmReject');
		  $('#confirmInfraLineStatus').html('');
		  $('#confirmInfraLineComment').html('');  
		  $('#confirmApprovalBtnId').show();
		  $("#confirmMessageLabelId").html("");
		  $("#confirmMessageDiv").hide();
		  return;
		}
	$.each(data,function(key,value){
		
		infraLineArray["infraLine"+value.partnerInfraItemApprovalId]=value;
		if(i==0){
			firstRow = value;
			leftPanelHtml = leftPanelHtml + ' <li onclick="showInfraConfirmInfo('+value.partnerInfraItemApprovalId+')" id="'+value.partnerInfraItemApprovalId+'" class="active">'
		}else{
			leftPanelHtml = leftPanelHtml + ' <li onclick="showInfraConfirmInfo('+value.partnerInfraItemApprovalId+')" id="'+value.partnerInfraItemApprovalId+'">'
		}
		leftPanelHtml = leftPanelHtml +' <a href="#Master" data-toggle="tab">'
        +' <div class="col-md-12">'
        +'  <label class="col-xs-6 ">'+value.partnerInfraItem.material.name+' </label>'
        +'	<label class="col-xs-6 "></label>'
        +' </div>'	
        +' </a>'
        +' </li>';
		i++;
	});
	$(".leftPaneData").append(leftPanelHtml);
	$('.leftPaneData').paginathing();
	
	$(".tabs-left li a label").text(function(index, currentText) {
	    return currentText.substr(0, 20);
	});
	loadInfraConfirmRightPane(firstRow);
}
function showInfraConfirmInfo(id){
	
	var infraLineData=infraLineArray["infraLine"+id];
	loadInfraConfirmRightPane(infraLineData);
}
function loadInfraConfirmRightPane(data){
	
	 showFieldsByRole(data);
     if(!$.isEmptyObject(data)){
	  var status=data.status==null?'':data.status;
	  var comment=data.comment==null?'':data.comment;
	  var levelNo=data.partnerInfraItem==null?'':data.partnerInfraItem.levelNo==null?'':data.partnerInfraItem.levelNo;
	  var infraStatus=data.partnerInfraItem.status==null?'':data.partnerInfraItem.status;
	  var attachementId=data.partnerInfraItem==null?'':data.partnerInfraItem.attachment==null?'':data.partnerInfraItem.attachment.attachmentId==null?'':data.partnerInfraItem.attachment.attachmentId;
	  var attachmentName=data.partnerInfraItem==null?'':data.partnerInfraItem.attachment==null?'':data.partnerInfraItem.attachment.fileName==null?'':data.partnerInfraItem.attachment.fileName;
	  $("#confirmatoryLevelSpanId").html(levelNo);
	  $('#confirmInfraLineStatus').html(status);
	  $('#confirmInfraLineComment').html(comment);
	  $("#confirmComment").text(comment);
	  setStatus(status,'confirmApprove','confirmClarify','confirmReject');
	  
	    var url = $("#a_docFileResponse").data('url');
		$("#a_docFileResponse").attr('href', url);
		var a_docFileResponse = $("#a_docFileResponse").prop('href')+ '/' +attachementId;
		$("#a_docFileResponse").prop('href', a_docFileResponse);
		$("#a_docFileResponse").html(attachmentName);
		
	 
		var roleName=$('#roleName').val();
		var pageInfo=$("#pageInfo").val();
		if(pageInfo=="vendorDetails"){
			if(infraStatus=="DR"){
				$("#confirmMessageLabelId").html("");
			    $("#confirmMessageDiv").hide();
			}else if(infraStatus=="IP"){
				 $("#confirmMessageLabelId").html("Infra Item Submitted!");
				 $("#confirmMessageDiv").show();
				 $("#confirmComment").attr('disabled','disabled');
				 $('#clarificationDivId').attr('disabled','disabled');
		    	 $("#docFileResponseUploadId").removeClass('requiredFile');
		    	 $('#clarificationDivId').attr('disabled','disabled');
		    	 $('#confirmApprovalBtnId').hide();
			}else if(infraStatus=='CLRFN' && (roleName=='VENADM' || roleName=='INFADM') && pageInfo=="vendorDetails"){
			 $("#confirmComment").attr('disabled','disabled');
			 $('#confirmApprovalBtnId').show();
			 $('#confirmApprovalBtnId').attr("onclick","return submitIt('approavalForm','saveClarificationFile','confirmInfraItemLineApprovalResp');");
		     $('#infraClarifyReadonlyDiv').removeClass('readonly'); 
		     if(infraStatus=='CLRFN'){
		    	 $('#clarificationDivId').show();
		    	 $('#clarificationDivId').removeAttr('disabled','disabled');
		    	 $("#docFileResponseUploadId").addClass('requiredFile');
		     }else {
		    	 $('#clarificationDivId').attr('disabled','disabled');
		    	 $("#docFileResponseUploadId").removeClass('requiredFile');
		     }
		   }
		}else if(pageInfo=="vendorApproval"){
			if(attachementId!=""){
			 $('#clarificationDivId').show();
			 $('#clarificationDivId').removeAttr('disabled','disabled');
			 $("#docFileResponseUploadId").removeClass('requiredFile');
			}else{
				 $('#clarificationDivId').attr('disabled','disabled');
				 $("#docFileResponseUploadId").removeClass('requiredFile');
			}
		}    		
  }else{
	  $("#confirmComment").text("");
	  setStatus("",'confirmApprove','confirmClarify','confirmReject');
	  $('#confirmInfraLineStatus').html('');
	  $('#confirmInfraLineComment').html('');  
	  $('#confirmApprovalBtnId').show();
	  $("#confirmMessageLabelId").html("");
	  $("#confirmMessageDiv").hide();
  }	
}

function confirmInfraItemLineApprovalResp(data){
	if(data.objectMap.result){
		Alert.info(data.objectMap.message);
		$("#clarificationDivId").hide();
		$("#confirmMessageLabelId").html(data.objectMap.message);
		$("#confirmMessageDiv").show();
		$('#confirmApprovalBtnId').hide();
	    $("#infraVendorTab").trigger('click');
	}else{
		Alert.warn(data.objectMap.message);
	}	
}

function clarificationDocfileDeleteResp(data){
	if (!data.hasError) {
		$('#docFileResponseUploadId').val('');
		$('#docFileResponseId').val('');
		$("#a_docFileResponse").html('');
		$('#deleteDocAttachmentId').attr('disabled', true);
		Alert.info(data.message);
	} else {
		Alert.warn(data.message);
	}
}
function saveInfraConfirmation(data){

	if(!data.hasError){
		Alert.info(data.message);
        $("#infraVendorTab").trigger('click');
		/*var pageInfo=$("#pageInfo").val();
		if(pageInfo=="vendorDetails")
			{
			   submitToURL('getInfraVendor', 'onTabloadVendorResp');
			}else if(pageInfo=="vendorApproval"){
			   submitToURL('getInfraVendors', 'onTabloadVendorResp');
			}*/
		$('#confirmApprovalBtnId').hide();
	}else{
		if(!$.isEmptyObject(data.errors))
		{
				var msg=data.message;
				  $.each(data.errors,function(key,value){
				       msg=msg+'\n'+value.errorMessage+',';
				       
				   });
				   Alert.warn(msg);
		}
		else{
			Alert.warn(data.message);
		}
	}
}
function showFieldsByRole(data){
	
	var pageInfo=$("#pageInfo").val();
	if(pageInfo=="vendorDetails"){
		 $('#confirmApprovalBtnId').attr("onclick","createInfraLine(event)");
	}else{
		 $('#confirmApprovalBtnId').attr("onclick","saveInfraApproval(event)");
	}
	
}