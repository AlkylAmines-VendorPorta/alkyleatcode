var traderPerformanceArray=new Array();
$(document).ready(function(){
	$('#addTraderPerformanceBtnId').click(function(event) {
		event.preventDefault();
		
		$('#traderPerformanceForm')[0].reset();
		$("#traderPerformanceForm .tpSingleSelectItem").val("");
		$(".tpSingleSelectItem").empty();
		$(".tpSingleSelectItem").append("<option value=''></option>");
		$('#traderCertificateAward').val("");
		$("#traderPerformanceForm #a_traderCertificateAward").html('');
		$(".traderCertificateAward").attr('disabled',true);
		$('#traderPerformanceForm #traderPerformanceId').val("");
		$("#traderPerformanceForm .manufacturerEEApproveDiv").hide(); 
		$("#traderPerformanceForm .manufacturerCEApproveDiv").hide();
	});
	
	$('#cancelTraderPerformanceBtnId').click(function(event){
		event.preventDefault();
		editMode=false;
		/*activeTabName="";*/
		var activePerformanceId=$('.leftPaneData').find('li.active').attr('id');
		if(activePerformanceId!=undefined)
			{
			  showTraderPerformanceDetail(activePerformanceId);
			}else{
			    $('#traderPerformanceForm')[0].reset();
			    $("#traderPerformanceForm .tpSingleSelectItem").val("");
				$(".tpSingleSelectItem").empty();
				$(".tpSingleSelectItem").append("<option value=''></option>");
				$('#traderCertificateAward').val("");
				$("#traderPerformanceForm #a_traderCertificateAward").html('');
				$(".traderCertificateAward").attr('disabled',true);
				$('#traderPerformanceForm #traderPerformanceId').val("");
			}
		
    });
	
	$('#deleteTraderPerformanceBtnId').click(function(event) {
		event.preventDefault();
		submitWithParam('deleteTraderPerformance','traderPerformanceId','traderPerformanceDelResp');
    });
	$("#traderPerformanceForm").find("input,select,textarea").change(function() {
		 debugger;
	   	 editMode=true;
	   	 activeTabName="Trader Past Performance";
	});
	$("#traderPerformanceForm .fileDeleteBtn").click(function() {
		 debugger;
	   	 editMode=true;
	   	 activeTabName="Trader Past Performance";
	   	requiredFileDeleted=true;
	});
	$("#traderPerformanceForm  #traderPerformanceAddItemBtnId").click(function() {
		 debugger;
	   	 editMode=true;
	   	 activeTabName="Trader Past Performance";
	});
});
function getTraderPerformance(event,el)
{
	debugger;
	event.preventDefault();	
	if(!editMode && !requiredFileDeleted){
		cacheLi();
		setCurrentTab(el);
		$('#AdditemButton').removeData('callback');
		$('#AdditemButton').attr('data-callback','loadPerformanceItem');
		$("#srchItemForm #orgId").val("");
		$('#searchItemList').removeAttr("onclick");
		$('#searchItemList').attr('onclick',"return submitIt('srchItemForm','getTradingItem','loadSearchItemTable');");
		if(getChangedFlag()){
			submitWithParam('getTraderPerformance','bPartnerId','onTraderPerformanceTabLoad');
			setChangedFlag(false);
		}else{
			getCacheLi();
		}
		setActiveTabName("Past Performance",$('.leftPaneData li').length);
		setPartnerHeaderValues("Company Name: "+companyName, "Company Registration No. : "+registrationNo, "Company Type : "+companyType,"Status : "+partnerStatus);

		}else{
			event.stopPropagation();
	        Alert.warn("Please Save Changed Data Of "+activeTabName+" Tab");
		}
	$("#filterBtnId").addClass("readonly");
    checkForFilterByRole();
}
function traderPerformanceDelResp(data)
{

	Alert.info(data.message);
	$('.pagination').children().remove();
	var currentPerformanceId=$('ul.leftPaneData').find('li.active').attr('id');
	if(data.hasError==false)
	{
		$('#'+currentPerformanceId).remove();
		$('#traderPerformanceForm')[0].reset();
		$('#traderPerformanceForm #traderPerformanceId').val("");
		$(".tpSingleSelectItem").empty();
		$(".tpSingleSelectItem").append("<option value=''></option>");
		$("#traderPerformanceForm #a_traderCertificateAward").html('');
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

function traderPerformanceResp(data){
	debugger;
	
	setChildLoadFlag(true);
	$('.pagination').children().remove();
	  if(data.response.hasError==false)
	   {
		  if ($("#partnerData").val() == "partnerRegistration") {
			$("#traderPerformanceForm  .manufacturerEEApproveDiv").hide();
			$("#traderPerformanceForm  .manufacturerEEApproveDiv").hide();
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
		/*$("#traderPerformanceForm #partnerOrgId").val(data.partnerOrg.partnerOrgId);*/
		$("#traderPerformanceForm #traderPerformanceId").val(partnerOrgPerformanceId);

		leftPanelHtml=appendTraderPerformanceData(data,userFlag);
		$(".leftPaneData").prepend(leftPanelHtml);
		if(data.certificateAward!=null)
		{
		 data.certificateAward.fileName=$("#traderPerformanceForm #a_traderCertificateAward").html();
		}
		if(data.material!=null)
		{
		  data.material.name=$("#traderPerformanceForm #traderItemName").text();
		}
		traderPerformanceArray["traderPerformanace"+partnerOrgPerformanceId]=data;
		$("#lblcertificateAwardName-"+partnerOrgPerformanceId).html($("#traderPerformanceForm #a_traderCertificateAward").html());
		userFlag=false;
		
		
		setActiveTabName("Past Performance",$('.leftPaneData li').length);
		Alert.info(data.response.message,'','success');
		setPartnerHeaderValues("Company Name: "+companyName, "Company Registration No. : "+registrationNo, "Company Type : "+companyType,"Status : "+partnerStatus);
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
function onTraderPerformanceTabLoad(data){
	debugger;
	$('#AdditemButton').removeData('callback');
	$('#AdditemButton').attr('data-callback','loadPerformanceItem');
	$("#srchItemForm #orgId").val("");
	$('#searchItemList').removeAttr("onclick");
	$('#searchItemList').attr('onclick',"return submitIt('srchItemForm','getTradingItem','loadSearchItemTable');");
	if(data.objectMap.hasOwnProperty('partnerOrgPerformances'))
		loadTraderPerformanceLeftPane(data.objectMap.partnerOrgPerformances);
	setActiveTabName("Past Performance",$('.leftPaneData li').length);
}
function appendTraderPerformanceData(value,active)
{
	
	 var leftPanelHtml='';
	 $('.pagination').children().remove();
	 var partnerOrgPerformanceId=value.partnerOrgPerformanceId==null?'':value.partnerOrgPerformanceId;
	 if(active)
		 {
		    leftPanelHtml = leftPanelHtml + ' <li class="active" onclick="showTraderPerformanceDetail('+partnerOrgPerformanceId+')" id="'+partnerOrgPerformanceId+'">';
		 }else{
			leftPanelHtml = leftPanelHtml + ' <li onclick="showTraderPerformanceDetail('+partnerOrgPerformanceId+')" id="'+partnerOrgPerformanceId+'">';
		 }
	
		var itemName = value.material==null?'':value.material.materialId==null?'':value.material.materialId;
		var item =value.material==null?'':value.material.materialId==null?'':value.material.name;
		var firmName = value.firmName==null?'':value.firmName;
		var orderStartDate = value.orderStartDate==null?'':formatDate(value.orderStartDate);
		var orderEndDate = value.orderEndDate==null?'':formatDate(value.orderEndDate);
		var quantitySupplied = value.quantitySupplied==null?'':value.quantitySupplied;
		var referenc1 = value.referenc1==null?'':value.referenc1;
		var referenc2 = value.referenc2==null?'':value.referenc2;
		var partnerOrgId=value.partnerOrg==null?'':value.partnerOrg.partnerOrgId==null?'':value.partnerOrg.partnerOrgId;
		var certificateAwardId=value.traderCertificateAward==null?'':value.traderCertificateAward.attachmentId==null?'':value.traderCertificateAward.attachmentId;
		var certificateAwardName=value.traderCertificateAward==null?'':value.traderCertificateAward.fileName==null?'':value.traderCertificateAward.fileName;
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
	    +' </a>'
        +' </li>';
	
	return leftPanelHtml;
	$('.leftPaneData').paginathing();
	
	$(".tabs-left li a label").text(function(index, currentText) {
	    return currentText.substr(0, 20);
	});
}
function showTraderPerformanceDetail(id)
{
	debugger;
	var performanceData=traderPerformanceArray["traderPerformanace"+id];
	loadTraderPerformanceRightPane(performanceData);
	
}

function loadTraderPerformanceLeftPane(data){
	$('.pagination').children().remove();
	$(".leftPaneData").html("");
	var leftPanelHtml='';
	var i=0;
	var active=false;
	var firstRow=null;
	$.each(data,function(key,value){
		var partnerOrgPerformanceId = value.partnerOrgPerformanceId==null?'':value.partnerOrgPerformanceId;
		traderPerformanceArray["traderPerformanace"+partnerOrgPerformanceId]=value;
		if(i==0){
			firstRow = value;
			active=true;
		}
		leftPanelHtml= leftPanelHtml +appendTraderPerformanceData(value,active);
		active=false;
		i++;
	});
	$(".leftPaneData").append(leftPanelHtml);
	$('.leftPaneData').paginathing();
	
	$(".tabs-left li a label").text(function(index, currentText) {
	    return currentText.substr(0, 20);
	});
		loadTraderPerformanceRightPane(firstRow);
}

function loadTraderPerformanceRightPane(data){

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
	var certificateAward = data.traderCertificateAward==null?'':data.traderCertificateAward;
	var partnerOrgPerformanceId = data.partnerOrgPerformanceId==null?'':data.partnerOrgPerformanceId;
	var partnerOrgId=data.partnerOrg==null?'':data.partnerOrg.partnerOrgId==null?'':data.partnerOrg.partnerOrgId;
	var certificateAwardId=data.traderCertificateAward==null?'':data.traderCertificateAward.attachmentId==null?'':data.traderCertificateAward.attachmentId;
	var certificateAwardName=data.traderCertificateAward==null?'':data.traderCertificateAward.fileName==null?'':data.traderCertificateAward.fileName;
	var remark=data.remark==null?'':data.remark;
	var isApproved=data.isApproved==null?'':data.isApproved;
	var eeComment=data.eeComment==null?'':data.eeComment;
	var isEEApproved=data.isEEApproved==null?'':data.isEEApproved;
	var ceComment=data.ceComment==null?'':data.ceComment;
	var isCEApproved=data.isCEApproved==null?'':data.isCEApproved;
	var poNumber=data.poNumber==null?'':data.poNumber;
	}
	if(!$.isEmptyObject(data))
	{
	$("#traderPerformanceForm #traderPerformanceId").val(partnerOrgPerformanceId);
	$("#traderPerformanceForm #traderItemName").val(itemName);
	$("#traderPerformanceForm #traderFirmName").val(firmName);
	$("#traderPerformanceForm #traderOrderStartDate").val(orderStartDate);
	$("#traderPerformanceForm #traderOrderEndDate").val(orderEndDate);
	$("#traderPerformanceForm #traderQuantitySupplied").val(quantitySupplied);
	$("#traderPerformanceForm #traderReferenc1").val(referenc1);
	$("#traderPerformanceForm #traderReferenc2").val(referenc2);
	$("#traderPerformanceForm #traderCertificateAward").val(certificateAward);
	/*$("#traderPerformanceForm .partnerOrgId").val(partnerOrgId);*/
	$("#traderPerformanceForm #traderCertificateAward").val(certificateAwardId);
	$(".tpSingleSelectItem").empty();
	$(".tpSingleSelectItem").append('<option value="'+itemId+'">'+itemName+'</option>');
	$("#traderPerformanceForm .tpSingleSelectItem").val(itemId);
	$("#traderPerformanceForm .dropDown").removeClass('errorinput');
	$("#traderPerformanceForm .requiredFile").removeClass('errorinput');
	$("#traderPerformanceForm #traderPoNumber").val(poNumber);
	/*$("#traderPerformanceForm #remark").val(remark);
	setApprovedStatus('mOrgPerformanceForm',isApproved);*/
	changeCommentAndStatusByRole('traderPerformanceForm',isEEApproved,eeComment,isCEApproved,ceComment);
	var url=$("#a_traderCertificateAward").data('url');
	$("#a_traderCertificateAward").attr('href',url);
	var a_certificateCopy = $("#traderPerformanceForm #a_traderCertificateAward").prop('href')+'/'+certificateAwardId;
    $("#traderPerformanceForm #a_traderCertificateAward").prop('href', a_certificateCopy);
	$("#traderPerformanceForm #a_traderCertificateAward").html(certificateAwardName);
	
	showPPFileDelBtn(certificateAwardId,'traderCertificateAward');
	
	}
	else{
		$("#traderPerformanceForm #traderPerformanceId").val("");
		$('#traderPerformanceForm')[0].reset();
		$("#traderPerformanceForm #traderCertificateAward").val("");
		$('#traderPerformanceForm').find('input:text').val('');
		$('#traderPerformanceForm').find('select').val('');
		$("#traderPerformanceForm #a_traderCertificateAward").html("");
		$(".tpSingleSelectItem").empty();
		$(".tpSingleSelectItem").append("<option value=''></option>");
		$('#traderCertificateAward').val("");
		$("#traderPerformanceForm #a_traderCertificateAward").html('');
		$(".traderCertificateAward").attr('disabled',true);
		$("#traderPerformanceForm .manufacturerEEApproveDiv").hide();
	    $("#traderPerformanceForm .manufacturerCEApproveDiv").hide();
		
	}
	setPartnerHeaderValues("Company Name: "+companyName, "Company Registration No. : "+registrationNo, "Company Type : "+companyType,"Status : "+partnerStatus);
    /*$("#compContactForm locationTypeRef").val(data.locationTypeRef==null?'':data.locationTypeRef);*/
	
}
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
function traderPerformanceAttachmentDelResp(data)
{
	$('.pagination').children().remove();
	if(!data.hasError)
    {		
       $('#traderCertificateFileId').val('');
	   $('#traderCertificateAward').val('');
	   $('#a_traderCertificateAward').html('');
	   $('.traderCertificateAward').attr('disabled',true);
	   Alert.info(data.message);
	   var performanceId=$("#traderPerformanceId").val();
	   if(performanceId!="")
	   {
	     traderPerformanceArray["traderPerformanace"+performanceId].certificateAward=null;
	   }
    }else{
    	Alert.warn(data.message);
    }
	$('.leftPaneData').paginathing();
	
	$(".tabs-left li a label").text(function(index, currentText) {
	    return currentText.substr(0, 20);
	});
}
function loadPerformanceItem(data){
	debugger;
	$(".tpSingleSelectItem").html("");
	var options='';
	$.each(data.map,function(index,val){
		options +='<option value="'+index+'">'+val +'</option>'
	});
	$(".tpSingleSelectItem").append(options);
	$("#traderPerformanceForm .dropDown").removeClass('errorinput');
   
}
function loadSearchItemTable(data){
	
		if(data.objectMap.hasOwnProperty('tradingItems')){
		if($.isEmptyObject(data.objectMap.tradingItems)){
			Alert.warn("Please add trading item");
			$("#traderPerformanceForm .dropDown").removeClass('errorinput');
			return;
		}
		$(".itemTable").DataTable().destroy();
		$(".itemTable tbody").empty();
		if(!$.isEmptyObject(data)){
			var itemList='';
			$.each(data.objectMap.tradingItems,function(index,material){
				var materialId=material.materialId==null?'':material.materialId;
				var name=material.name==null?'':material.name;
			    var itemCode=material.itemCode==null?'':material.itemCode;
			    var description=material.description==null?'':material.description;
			    var hsnCode=material.hsnCode==null?'':material.hsnCode.code==null?'':material.hsnCode.code;
			   
			    itemList +='<tr> <td><input id="'+materialId+'" class="selectitemradio" type="radio" value="'+materialId+'" name="materialRadio" />'+
					'<td>'+hsnCode+'</td>'+
					'<td data-itemCode="'+itemCode+'" data-hsn="'+hsnCode+'" data-itemname="'+name+'"  data-matdescription="'+description+'" data-materialid="'+materialId+'">'+itemCode+'</td>'+
					'<td>'+name+'</td>'+
					'</tr>';
			});
			
			$(".itemTable tbody").append(itemList);
			$(".itemTable").DataTable({
				"lengthMenu": [ 5, 10, 75, 100 ],
				"ordering": false
			});
		}
	}
}