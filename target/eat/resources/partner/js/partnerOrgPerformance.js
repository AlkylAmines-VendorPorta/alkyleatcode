var orgPerformanceArray=new Array();
$(document).ready(function(){
	$('#addPerformanceBtnId').click(function(event) {
		event.preventDefault();
		
		$('#partnerOrgPerformanceForm')[0].reset();
		$("#partnerOrgPerformanceForm .singleselectitem").val("");
		$('#partnerOrgPerformanceForm .saveBtnId').show();
		
		$(".singleselectitem").empty();
		$(".singleselectitem").append("<option value=''></option>");
		$('#certificateAward').val("");
		$("#partnerOrgPerformanceForm #a_certificateAward").html('');
		$(".certificateAward").attr('disabled',true);
		$('#partnerOrgPerformanceForm #performanceId').val("");
		$("#partnerOrgPerformanceForm .partnerOrgApproveDiv").hide();
		
		
		});
		
	$('#editPerformanceBtnId').click(function(event) {
		event.preventDefault();
		$('#partnerOrgPerformanceForm .saveBtnId').show();
		$('#partnerOrgPerformanceForm .cancelBtnId').show();
		
		});
	
	$('#cancelPerformanceBtnId').click(function(event) {
		event.preventDefault();
		editMode=false;
		/*activeTabName="";*/
		var activePerformanceId=$('.leftPaneData').find('li.active').attr('id');
		if(activePerformanceId!=undefined)
			{
			showPerformanceDetail(activePerformanceId);
			}
		else
			$('#partnerOrgPerformanceForm')[0].reset();
		
    });
	
	$('#deletePerformanceBtnId').click(function(event) {
		event.preventDefault();
		submitWithParam('deleteOrgPerformance','performanceId','partnerOrgPerformanceDelResp');
    });
	$("#partnerOrgPerformanceForm").find("input,select,textarea").change(function() {
		 debugger;
	   	 editMode=true;
	   	 activeTabName="Factory Past Performance";
	});
	$("#partnerOrgPerformanceForm .fileDeleteBtn").click(function() {
		 debugger;
	   	 editMode=true;
	   	 activeTabName="Factory Past Performance";
	   	requiredFileDeleted=true;
	});
	$("#partnerOrgPerformanceForm  #performanceAddItemBtnId").click(function() {
		 debugger;
	   	 editMode=true;
	   	 activeTabName="Factory Past Performance";
	});
});
function getFactoryPastPerformance(event,el)
{
	debugger;
	event.preventDefault();	
	if(!editMode && !requiredFileDeleted){
		cacheLi();
		setCurrentTab(el);
		$('#AdditemButton').removeData('callback');
		$('#AdditemButton').attr('data-callback','loadPerformanceItemList');
		$("#srchItemForm #orgId").val($("#partnerOrgForm #partnerOrgId").val());
		$('#searchItemList').attr('onclick',"return submitIt('srchItemForm','getItemList','populateItemListWithSingleSelect');");
		if(getChangedFlag()){
			submitWithParam('getOrgPerformance','partnerOrgId','onPartnerOrgPerformanceTabLoad');
			setChangedFlag(false);
		}else{
			getCacheLi();
		}
		setActiveTabName("Past Performance",$('.leftPaneData li').length);
		setHeaderValues("Factory Name: "+FactoryName, "Established Date : "+establishedDate, "License No.: "+licenseNo, "Location : "+locationName);
		}else{
			event.stopPropagation();
	        Alert.warn("Please Save Changed Data Of "+activeTabName+" Tab");
		}
	$("#filterBtnId").addClass("readonly");
    checkForFilterByRole();
}
function partnerOrgPerformanceDelResp(data)
{

	Alert.info(data.message);
	$('.pagination').children().remove();
	var currentPerformanceId=$('ul.leftPaneData').find('li.active').attr('id');
	if(data.hasError==false)
	{
		$('#'+currentPerformanceId).remove();
		$('#partnerOrgPerformanceForm')[0].reset();
		$('#partnerOrgPerformanceForm #performanceId').val("");
		$(".singleselectitem").empty();
		$(".singleselectitem").append("<option value=''></option>");
		$("#partnerOrgPerformanceForm #a_certificateAward").html('');
		/*$('#productformDivId').addClass('readonly');*/
		Alert.info(data.message,'','success');
		showSubmitFormOnOrgChanges();
	}
else
	{
	  Alert.warn(data.message,"","error");
	}
	$('.leftPaneData').paginathing();
	
	$(".tabs-left li a label").text(function(index, currentText) {
	    return currentText.substr(0, 20);
	});
}

function partnerOrgPerformanceResp(data){
	debugger;
	
	setChildLoadFlag(true);
	$('.pagination').children().remove();
	  if(data.response.hasError==false)
	   {
		  if ($("#partnerData").val() == "partnerRegistration") {
			$("#partnerOrgPerformanceForm  .partnerOrgEEApproveDiv").hide();
			$("#partnerOrgPerformanceForm  .partnerOrgCEApproveDiv").hide();
		  }
		showSubmitFormOnOrgChanges();
		editMode=false;
		activeTabName="";
		requiredFileDeleted=false;
		var userFlag=true;
		var leftPanelHtml='';
		var partnerOrgPerformanceId=data.partnerOrgPerformanceId;
		var currentPerformanceId=$('ul.leftPaneData').find('li.active').attr('id');
		if(currentPerformanceId==partnerOrgPerformanceId)
		{
			$('#'+currentPerformanceId).remove();
		}
		else
		{
			$('#'+currentPerformanceId).removeClass('active');
		}
		$("#partnerOrgPerformanceForm #partnerOrgId").val(data.partnerOrg.partnerOrgId);
		$("#partnerOrgPerformanceForm #performanceId").val(partnerOrgPerformanceId);

		leftPanelHtml=appendPerformanceData(data,userFlag);
		$(".leftPaneData").prepend(leftPanelHtml);
		if(data.certificateAward!=null)
		{
		 data.certificateAward.fileName=$("#partnerOrgPerformanceForm #a_certificateAward").html();
		}
		if(data.material!=null)
		{
		  data.material.name=$("#partnerOrgPerformanceForm #itemName").text();
		}
		orgPerformanceArray["orgPerformanace"+partnerOrgPerformanceId]=data;
		$("#lblcertificateAwardName-"+partnerOrgPerformanceId).html($("#partnerOrgPerformanceForm #a_certificateAward").html());
		userFlag=false;
		
		
		setActiveTabName("Past Performance",$('.leftPaneData li').length);
		Alert.info(data.response.message,'','success');
		setHeaderValues("Factory Name: "+FactoryName, "Established Date : "+establishedDate, "License No.: "+licenseNo, "Location : "+locationName);
	   }else{
		   if(!$.isEmptyObject(data.response.errors))
			{
					var msg=data.response.message;
					 $.each(data.response.errors,function(key,value){
					       msg=msg+'\n'+value.errorMessage+'\n';
					       
					   });
					    Alert.warn(msg);
			}
			else{
				Alert.warn(data.response.message);
			}
		}
	  
	 $('.leftPaneData').paginathing();
	 
	 $(".tabs-left li a label").text(function(index, currentText) {
		    return currentText.substr(0, 20);
		});
	}



function loadPerformanceItemList(values){
	debugger;
	$(".singleselectitem").html("");
	var options='';
	$.each(values.map,function(index,val){
		options +='<option value="'+index+'">'+val +'</option>'
		
	});
	$(".singleselectitem").append(options);
	$("#partnerOrgPerformanceForm .dropDown").removeClass('errorinput');
}
function onPartnerOrgPerformanceTabClick(){
	submitToURL("getPartner", 'onPartnerOrgPerformanceTabLoad');	
}

function onPartnerOrgPerformanceTabLoad(data){
	debugger;
	$('#AdditemButton').removeData('callback');
	$('#AdditemButton').attr('data-callback','loadPerformanceItemList');
	$("#srchItemForm #orgId").val($("#partnerOrgForm #partnerOrgId").val());
	$('#searchItemList').attr('onclick',"return submitIt('srchItemForm','getItemList','populateItemListWithSingleSelect');");
	if(data.objectMap.hasOwnProperty('partnerOrgPerformances'))
		loadPartnerOrgPerformanceLeftPane(data.objectMap.partnerOrgPerformances);
	
	setActiveTabName("Past Performance",$('.leftPaneData li').length);
}

function appendPerformanceData(value,active)
{
	
	 var leftPanelHtml='';
	 $('.pagination').children().remove();
	 var partnerOrgPerformanceId=value.partnerOrgPerformanceId==null?'':value.partnerOrgPerformanceId;
	 if(active)
		 {
		    leftPanelHtml = leftPanelHtml + ' <li class="active" onclick="showPerformanceDetail('+partnerOrgPerformanceId+')" id="'+partnerOrgPerformanceId+'">';
		 }else{
			leftPanelHtml = leftPanelHtml + ' <li onclick="showPerformanceDetail('+partnerOrgPerformanceId+')" id="'+partnerOrgPerformanceId+'">';
		 }
	
		var itemName = value.material==null?'':value.material.materialId==null?'':value.material.materialId;
		var item =value.material==null?'':value.material.materialId==null?'':value.material.name;
		var firmName = value.firmName==null?'':value.firmName;
		var orderStartDate = value.orderStartDate==null?'':formatDate(value.orderStartDate);
		var orderEndDate = value.orderEndDate==null?'':formatDate(value.orderEndDate);
		var quantitySupplied = value.quantitySupplied==null?'':value.quantitySupplied;
		var referenc1 = value.referenc1==null?'':value.referenc1;
		var referenc2 = value.referenc2==null?'':value.referenc2;
		var partnerOrgId=value.partnerOrg.partnerOrgId==null?'':value.partnerOrg.partnerOrgId;
		var certificateAwardId=value.certificateAward==null?'':value.certificateAward.attachmentId==null?'':value.certificateAward.attachmentId;
		var certificateAwardName=value.certificateAward==null?'':value.certificateAward.fileName==null?'':value.certificateAward.fileName;
		var remark=value.remark==null?'':value.remark;
		var isApproved=value.isApproved==null?'':value.isApproved;
		var eeComment=value.eeComment==null?'':value.eeComment;
		var isEEApproved=value.isEEApproved==null?'':value.isEEApproved;
		var ceComment=value.ceComment==null?'':value.ceComment;
		var isCEApproved=value.isCEApproved==null?'':value.isCEApproved;
		
		leftPanelHtml = leftPanelHtml +' <a href="#Master" data-toggle="tab">'
        +' <div class="col-md-12">'
        +'	<label class="col-xs-6 " id="lblFirmName-'+partnerOrgPerformanceId+'">'+firmName+'</label>'
        +'	<label class="col-xs-6" id="lblQuantitySupplied-'+partnerOrgPerformanceId+'">'+quantitySupplied+'</label>'
        +' </div>'	
        +' <div class="col-md-12">'
        +'	<label class="col-xs-6" id="lblOrderStartDate-'+partnerOrgPerformanceId+'">'+orderStartDate+'</label>'
        +'	<label class="col-xs-6" id="lblOrderEndDate-'+partnerOrgPerformanceId+'">'+orderEndDate+'</label>'
        +' </div>'
		+' <div class="col-md-12" style="display: none">'
		+'	<label class="col-xs-6" id="lblPartnerOrgPerformanceId-'+partnerOrgPerformanceId+'">'+partnerOrgPerformanceId+'</label>'
	    +'	<label class="col-xs-6" id="lblItemName-'+partnerOrgPerformanceId+'">'+itemName+'</label>'
	    +'	<label class="col-xs-6" id="lblItem-'+partnerOrgPerformanceId+'" >'+item+'</label>'
	    +'	<label class="col-xs-6" id="lblFirmName-'+partnerOrgPerformanceId+'">'+firmName+'</label>'
	    +'	<label class="col-xs-6" id="lblOrderStartDate-'+partnerOrgPerformanceId+'" >'+orderStartDate+'</label>'
	    +'	<label class="col-xs-6" id="lblQuantitySupplied-'+partnerOrgPerformanceId+'">'+quantitySupplied+'</label>'
	    +'	<label class="col-xs-6" id="lblReferenc1-'+partnerOrgPerformanceId+'">'+referenc1+'</label>'
	    +'	<label class="col-xs-6" id="lblReferenc2-'+partnerOrgPerformanceId+'">'+referenc2+'</label>'
	    +'	<label class="col-xs-6" id="labelPartnerOrgId-'+partnerOrgPerformanceId+'">'+partnerOrgId+'</label>'
	    +'	<label class="col-xs-6" id="lblcertificateAwardId-'+partnerOrgPerformanceId+'" >'+certificateAwardId+'</label>'
	    +'	<label class="col-xs-6" id="lblcertificateAwardName-'+partnerOrgPerformanceId+'" >'+certificateAwardName+'</label>'
	    +'	<label class="col-xs-6" id="remark-'+partnerOrgPerformanceId+'" >'+remark+'</label>'
	    +'	<label class="col-xs-6" id="isApproved-'+partnerOrgPerformanceId+'" >'+isApproved+'</label>'
	    +'	<label class="col-xs-6" id="eeComment-'+partnerOrgPerformanceId+'">'+eeComment+'</label>'
	    +'	<label class="col-xs-6" id="ceComment-'+partnerOrgPerformanceId+'">'+ceComment+'</label>'
	    +'	<label class="col-xs-6" id="isEEApproved-'+partnerOrgPerformanceId+'">'+isEEApproved+'</label>'
	    +'	<label class="col-xs-6" id="isCEApproved-'+partnerOrgPerformanceId+'">'+isCEApproved+'</label>'
		+' </div>'
        +' </a>'
        +' </li>';
	
	return leftPanelHtml;
	$('.leftPaneData').paginathing();
	
	$(".tabs-left li a label").text(function(index, currentText) {
	    return currentText.substr(0, 20);
	});
}
function showPerformanceDetail(id)
{
	debugger;
	var performanceData=orgPerformanceArray["orgPerformanace"+id];
	loadPartnerOrgPerformanceRightPane(performanceData);
	
}

function loadPartnerOrgPerformanceLeftPane(data){
	$('.pagination').children().remove();
	$(".leftPaneData").html("");
	var leftPanelHtml='';
	var i=0;
	var active=false;
	var firstRow=null;
	$.each(data,function(key,value){
		var partnerOrgPerformanceId = value.partnerOrgPerformanceId==null?'':value.partnerOrgPerformanceId;
		orgPerformanceArray["orgPerformanace"+partnerOrgPerformanceId]=value;
		if(i==0){
			firstRow = value;
			active=true;
		}
		leftPanelHtml= leftPanelHtml +appendPerformanceData(value,active);
		active=false;
		i++;
	});
	$(".leftPaneData").append(leftPanelHtml);
	$('.leftPaneData').paginathing();
	
	$(".tabs-left li a label").text(function(index, currentText) {
	    return currentText.substr(0, 20);
	});
		loadPartnerOrgPerformanceRightPane(firstRow);
}

function loadPartnerOrgPerformanceRightPane(data){

	editMode=false;
	
	setChildLoadFlag(true);
	/*activeTabName="";*/
	if(!$.isEmptyObject(data))
	{
	var itemId = data.material==null?'':data.material.materialId==null?'':data.material.materialId;
	var itemName =data.material==null?'':data.material.name==null?'':data.material.name;
	var firmName = data.firmName==null?'':data.firmName;
	var orderStartDate = data.orderStartDate==null?'':formatDate(data.orderStartDate);
	var orderEndDate = data.orderEndDate==null?'':formatDate(data.orderEndDate);
	var quantitySupplied = data.quantitySupplied==null?'':data.quantitySupplied;
	var referenc1 = data.referenc1==null?'':data.referenc1;
	var referenc2 = data.referenc2==null?'':data.referenc2;
	var certificateAward = data.certificateAward==null?'':data.certificateAward;
	var partnerOrgPerformanceId = data.partnerOrgPerformanceId==null?'':data.partnerOrgPerformanceId;
	var partnerOrgId=data.partnerOrg.partnerOrgId==null?'':data.partnerOrg.partnerOrgId;
	var certificateAwardId=data.certificateAward==null?'':data.certificateAward.attachmentId==null?'':data.certificateAward.attachmentId;
	var certificateAwardName=data.certificateAward==null?'':data.certificateAward.fileName==null?'':data.certificateAward.fileName;
	var remark=data.remark==null?'':data.remark;
	var isApproved=data.isApproved==null?'':data.isApproved;
	var eeComment=data.eeComment==null?'':data.eeComment;
	var isEEApproved=data.isEEApproved==null?'':data.isEEApproved;
	var ceComment=data.ceComment==null?'':data.ceComment;
	var isCEApproved=data.isCEApproved==null?'':data.isCEApproved;
	var poNumber=data.poNumber==null?'':data.poNumber;
	}
	/*$("#labelFirstName").html('<h4>'+firstName+'</h4>');
	$("#labelLastName").html(lastName);
	$("#labelEmail").html(email);
	$("#labelMobileNo").html(mobileNo);*/
	if(!$.isEmptyObject(data))
	{
	$("#partnerOrgPerformanceForm #performanceId").val(partnerOrgPerformanceId);
	$("#partnerOrgPerformanceForm #PerformanceitemName").val(itemName);
	$("#partnerOrgPerformanceForm #firmName").val(firmName);
	$("#partnerOrgPerformanceForm #orderStartDate").val(orderStartDate);
	$("#partnerOrgPerformanceForm #orderEndDate").val(orderEndDate);
	$("#partnerOrgPerformanceForm #quantitySupplied").val(quantitySupplied);
	$("#partnerOrgPerformanceForm #referenc1").val(referenc1);
	$("#partnerOrgPerformanceForm #referenc2").val(referenc2);
	$("#partnerOrgPerformanceForm #certificateAward").val(certificateAward);
	$("#partnerOrgPerformanceForm .partnerOrgId").val(partnerOrgId);
	$("#partnerOrgPerformanceForm #certificateAward").val(certificateAwardId);
	$(".singleselectitem").empty();
	$(".singleselectitem").append('<option value="'+itemId+'">'+itemName+'</option>');
	$("#partnerOrgPerformanceForm .singleselectitem").val(itemId);
	$("#partnerOrgPerformanceForm .dropDown").removeClass('errorinput');
	$("#partnerOrgPerformanceForm .requiredFile").removeClass('errorinput');
	$("#partnerOrgPerformanceForm #poNumber").val(poNumber);
	/*$("#partnerOrgPerformanceForm #remark").val(remark);
	setApprovedStatus('partnerOrgPerformanceForm',isApproved);*/
	changeOrgCommentAndStatusByRole('partnerOrgPerformanceForm',isEEApproved,eeComment,isCEApproved,ceComment);
	
	var url=$("#a_certificateAward").data('url');
	$("#a_certificateAward").attr('href',url);
	var a_certificateCopy = $("#partnerOrgPerformanceForm #a_certificateAward").prop('href')+'/'+certificateAwardId;
    $("#partnerOrgPerformanceForm #a_certificateAward").prop('href', a_certificateCopy);
	$("#partnerOrgPerformanceForm #a_certificateAward").html(certificateAwardName);
	
	showPPFileDelBtn(certificateAwardId,'certificateAward');
	
	}
	else{
		$("#partnerOrgPerformanceForm #performanceId").val("");
		$('#partnerOrgPerformanceForm')[0].reset();
		$("#partnerOrgPerformanceForm #certificateAward").val("");
		$('#OrgPerformanceFormId').find('input:text').val('');
		$('#OrgPerformanceFormId').find('select').val('');
		$("#partnerOrgPerformanceForm #a_certificateAward").html("");
		$(".singleselectitem").empty();
		$(".singleselectitem").append("<option value=''></option>");
		$('#certificateAward').val("");
		$("#partnerOrgPerformanceForm #a_certificateAward").html('');
		$(".certificateAward").attr('disabled',true);
		$("#partnerOrgPerformanceForm .partnerOrgEEApproveDiv").hide();
	    $("#partnerOrgPerformanceForm .partnerOrgCEApproveDiv").hide();
		
	}
	setHeaderValues("Factory Name: "+FactoryName, "Established Date : "+establishedDate, "License No.: "+licenseNo, "Location : "+locationName);
/*	$("#compContactForm locationTypeRef").val(data.locationTypeRef==null?'':data.locationTypeRef);*/
	
}
/*function performanceAttachmentDeleteResp(data)
{
	if(!data.hasError)
    {		
       $('#certificateFileId').val('');
	   $('#certificateAward').val('');
	   $("#a_certificateAward").html('');
	   $('.certificateAward').attr('disabled',true);
	   Alert.info(data.message);
    }else{
    	Alert.warn(data.message);
    }
}*/

function showPPFileDelBtn(fileId,fieldClass)
{
	debugger;
  if(fileId!='')
	  {
	    $("."+fieldClass).attr('disabled',false);
	  }else{
		  $("."+fieldClass).attr('disabled',true);
	  }
 }
function performanceAttachmentDeleteResp(data)
{
	$('.pagination').children().remove();
	if(!data.hasError)
    {		
       $('#certificateFileId').val('');
	   $('#certificateAward').val('');
	   $('#a_certificateAward').html('');
	   $('.certificateAward').attr('disabled',true);
	   Alert.info(data.message);
	   var performanceId=$("#performanceId").val();
	   if(performanceId!="")
	   {
	     orgPerformanceArray["orgPerformanace"+performanceId].certificateAward=null;
	   }
    }else{
    	Alert.warn(data.message);
    }
	$('.leftPaneData').paginathing();
	
	$(".tabs-left li a label").text(function(index, currentText) {
	    return currentText.substr(0, 20);
	});
}