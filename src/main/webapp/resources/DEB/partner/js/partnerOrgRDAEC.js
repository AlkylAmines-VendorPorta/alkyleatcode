var orgRDAECArray=new Array();
$(document).ready(function(){
	$('#partnerOrgRDAECForm #isApplicable').click(function(){
		debugger;
		var active='';
		if (this.checked) {
			active="Y";
			$('#partnerOrgRDAECForm .showField').find('input:text').val('');
			$('#partnerOrgRDAECForm .showField').find('select').val('');
		}else{
			active="N";
		}
		showFields(active);
	});
	$('#cancelRDAECBtnId').click(function(event) {
		event.preventDefault();

		editMode = false;
		/* activeTabName=""; */

		var activeRDAECId = $("#partnerOrgRDAECId").val();
		if (activeRDAECId != "" || activeRDAECId != undefined) {
			var data = orgRDAECArray["RDAEC" + activeRDAECId];
			loadPartnerOrgRDAECRightPane(data);
		} else {
			$('#partnerOrgRDAECForm')[0].reset();
			$("#partnerOrgRDAECId").val("");
			$("#partnerOrgRDAECForm #eligibilityCertificate").val("");
			$("#partnerOrgRDAECForm #a_eligibilityCertificate").html("");
			$("#partnerOrgRDAECForm .showField").show();
			$("#partnerOrgRDAECForm #isApplicable").prop('checked', false);
			$('#elegibilityType').removeAttr('disabled', 'disabled');
			$('#developingRegion').removeAttr('disabled', 'disabled');
			$('#isPioneer').removeAttr('disabled', 'disabled');
			$('#validFrom').removeAttr('disabled', 'disabled');
			$('#validTo').removeAttr('disabled', 'disabled');
			$('#eligibilityFileId').removeAttr('disabled', 'disabled');
		}

	});
	$("#partnerOrgRDAECForm").find("input,select,textarea").change(function() {
		 debugger;
	   	 editMode=true;
	   	 activeTabName="Factory Regional Development Authority Eligibility";
	});
	$("#partnerOrgRDAECForm .fileDeleteBtn").click(function() {
		 debugger;
	   	 editMode=true;
	   	requiredFileDeleted=true;
	   	 activeTabName="Factory Regional Development Authority Eligibility";
	});
});
function getFactoryRDAEC(event,el)
{
	event.preventDefault();	
	
		if(!editMode && !requiredFileDeleted){
			cacheLi();
			setCurrentTab(el);
			if(getChangedFlag()){
			  submitWithParam('getOrgRDAEC','partnerOrgId','onPartnerOrgRDAECTabLoad');	
			  setChangedFlag(false);
			}else{
				getCacheLi();
			}
		}else{
			event.stopPropagation();
	        Alert.warn("Please Save Changed Data Of "+activeTabName+" Tab");
		}
		$("#filterBtnId").addClass("readonly");
	    checkForFilterByRole(); 
		setActiveTabName("RDAEC",$('.leftPaneData li').length);
}
function showFields(active)
{
	if(active=='Y')
		{
		  $('#partnerOrgRDAECForm  #isApplicable').val(active);
          $("#partnerOrgRDAECForm .showField").hide();
          $('#elegibilityType').attr('disabled','disabled');
          $('#developingRegion').attr('disabled','disabled');
          $('#isPioneer').attr('disabled','disabled');
          $('#validFrom').attr('disabled','disabled');
          $('#validTo').attr('disabled','disabled');
          $('#eligibilityFileId').attr('disabled','disabled');
          
		}else{
		   $('#partnerOrgRDAECForm  #isApplicable').val(active);
		   $("#partnerOrgRDAECForm .showField").show();
		   $('#elegibilityType').removeAttr('disabled','disabled');
		   $('#developingRegion').removeAttr('disabled','disabled');
		   $('#isPioneer').removeAttr('disabled','disabled');
		   $('#validFrom').removeAttr('disabled','disabled');
		   $('#validTo').removeAttr('disabled','disabled');
		   $('#eligibilityFileId').removeAttr('disabled','disabled');
		}
}


function partnerOrgRDAECResp(data){
	
debugger;
$('.pagination').children().remove();
if(data.response.hasError==false)
{
		if ($("#partnerData").val() == "partnerRegistration") {
			$("#partnerOrgRDAECForm  .partnerOrgEEApproveDiv").hide();
			$("#partnerOrgRDAECForm  .partnerOrgCEApproveDiv").hide();
		}
		editMode=false;
		setChildLoadFlag(true);
		activeTabName="";
		requiredFileDeleted=false;
	var rdaecFlag=true;
	var leftPanelHtml='';
	var currentOrgRDAECId=$('ul.leftPaneData').find('li.active').attr('id');
	var partnerOrgRDAECId=data.partnerOrgRDAECId;
	if(currentOrgRDAECId==partnerOrgRDAECId)
	{
		$('#'+currentOrgRDAECId).remove();
		
	}
	$("#partnerOrgRDAECForm .partnerOrgId").val(data.partnerOrg.partnerOrgId);
	$("#partnerOrgRDAECForm #partnerOrgRDAECId").val(partnerOrgRDAECId);
	leftPanelHtml=appendRDAECData(data,rdaecFlag);
	$(".leftPaneData").prepend(leftPanelHtml);
	if(data.eligibilityCertificate!=null)
	{
	 data.eligibilityCertificate.fileName=$("#partnerOrgRDAECForm #a_eligibilityCertificate").text();
	}
	orgRDAECArray["RDAEC"+partnerOrgRDAECId]=data;
	
	$('.leftPaneData').paginathing();
	
	$(".tabs-left li a label").text(function(index, currentText) {
	    return currentText.substr(0, 20);
	});
	rdaecFlag=false;
	Alert.info(data.response.message);
	showSubmitFormOnOrgChanges();
}else{
	Alert.warn(data.response.message);
}
    $('.leftPaneData').paginathing();
	setHeaderValues("Factory Name: "+FactoryName, "Established Date : "+establishedDate, "License No.: "+licenseNo, "Location : "+locationName);
}

function onPartnerOrgRDAECTabClick(){
	submitToURL("getPartner", 'onPartnerOrgRDAECTabLoad');	
}

function onPartnerOrgRDAECTabLoad(data){
	debugger;
	if(data.objectMap.hasOwnProperty('developingRegions'))
		loadReferenceListById('developingRegion',data.objectMap.developingRegions);
	if(data.objectMap.hasOwnProperty('partnerOrgRDAECs')){
		loadPartnerOrgRDAECLeftPane(data.objectMap.partnerOrgRDAECs);
}
	setActiveTabName("RDAEC",$('.leftPaneData li').length);
}

function loadPartnerOrgRDAECLeftPane(data){
debugger;
$('.pagination').children().remove();
	$(".leftPaneData").html("");
	var leftPanelHtml='';
	var i=0;
	var active=false;
	var firstRow=null;
	$.each(data,function(key,value){
		debugger;
		var partnerOrgRDAECId=value.partnerOrgRDAECId==null?'':value.partnerOrgRDAECId;
		orgRDAECArray["RDAEC"+partnerOrgRDAECId]=value;
		if(i==0){
			firstRow = value;
			active=true;
		}
		leftPanelHtml= leftPanelHtml +appendRDAECData(value,active);
		active=false;
		i++;
	});
	$(".leftPaneData").append(leftPanelHtml);
	$('.leftPaneData').paginathing();
	
	$(".tabs-left li a label").text(function(index, currentText) {
	    return currentText.substr(0, 20);
	});
	loadPartnerOrgRDAECRightPane(firstRow);
}

function loadPartnerOrgRDAECRightPane(data){
	debugger;
	editMode=false;
	setChildLoadFlag(true);
	/*activeTabName="";*/
	if(!$.isEmptyObject(data))
	{
	var isApplicable=data.isApplicable==null?'':data.isApplicable;
	var isPioneer=data.isPioneer=="Yes"?'':data.isPioneer;
	var validFrom=data.validFrom==null?'':formatDate(data.validFrom);
	var validTo=data.validTo==null?'':formatDate(data.validTo);
	var developingRegion=data.developingRegion==null?'':data.developingRegion;
	var partnerOrgRDAECId=data.partnerOrgRDAECId==null?'':data.partnerOrgRDAECId;
	var partnerOrgId = data.partnerOrg.partnerOrgId==null?'':data.partnerOrg.partnerOrgId;
	var elegibilityType = data.elegibilityType==null?'':data.elegibilityType;
	var eligibilityCertificateId=data.eligibilityCertificate==null?'':data.eligibilityCertificate.attachmentId==null?'':data.eligibilityCertificate.attachmentId;
	var eligibilityCertificateName=data.eligibilityCertificate==null?'':data.eligibilityCertificate.fileName==null?'':data.eligibilityCertificate.fileName;
	var remark=data.remark==null?'':data.remark;
	var isApproved=data.isApproved==null?'':data.isApproved;
	var eeComment=data.eeComment==null?'':data.eeComment;
	var isEEApproved=data.isEEApproved==null?'':data.isEEApproved;
	var ceComment=data.ceComment==null?'':data.ceComment;
	var isCEApproved=data.isCEApproved==null?'':data.isCEApproved;
	}
	/*$("#labelFirstName").html('<h4>'+firstName+'</h4>');
	$("#labelLastName").html(lastName);
	$("#labelEmail").html(email);
	$("#labelMobileNo").html(mobileNo);*/
	if(!$.isEmptyObject(data))
	{
	$("#partnerOrgRDAECForm #partnerOrgRDAECId").val(partnerOrgRDAECId);
	$("#partnerOrgRDAECForm #partnerOrgId").val(partnerOrgId);
	$("#partnerOrgRDAECForm #isApplicable").val(isApplicable);
	$("#partnerOrgRDAECForm #isPioneer").val(isPioneer);
	$("#partnerOrgRDAECForm #validFrom").val(validFrom);
	$("#partnerOrgRDAECForm #validTo").val(validTo);
	$("#partnerOrgRDAECForm #elegibilityType").val(elegibilityType);
	$("#partnerOrgRDAECForm #developingRegion").val(developingRegion);
	$("#partnerOrgRDAECForm #eligibilityCertificate").val(eligibilityCertificateId);
	changeOrgCommentAndStatusByRole('partnerOrgRDAECForm',isEEApproved,eeComment,isCEApproved,ceComment);
	checkForIsApplicableRDAEC(isApplicable);
	
	var url=$("#a_eligibilityCertificate").data('url');
	$("#a_eligibilityCertificate").attr('href',url);
	var a_eligibilityCertificate = $("#partnerOrgRDAECForm #a_eligibilityCertificate").prop('href')+'/'+eligibilityCertificateId;
    $("#partnerOrgRDAECForm #a_eligibilityCertificate").prop('href', a_eligibilityCertificate);
	$("#partnerOrgRDAECForm #a_eligibilityCertificate").html(eligibilityCertificateName);
	
	showRADECFileDelBtn(eligibilityCertificateId,'eligibilityCertificate');
	
	}else{
		$('#partnerOrgRDAECForm')[0].reset();
		$("#partnerOrgRDAECForm #partnerOrgRDAECId").val("");
		checkForIsApplicableRDAEC("N");
		$("#partnerOrgRDAECForm #eligibilityCertificate").val("");
		$("#partnerOrgRDAECForm #a_eligibilityCertificate").html("");
		$("#partnerOrgRDAECForm .partnerOrgEEApproveDiv").hide();
	    $("#partnerOrgRDAECForm .partnerOrgCEApproveDiv").hide();
	}
/*	$("#compContactForm locationTypeRef").val(data.locationTypeRef==null?'':data.locationTypeRef);*/
	setHeaderValues("Factory Name: "+FactoryName, "Established Date : "+establishedDate, "License No.: "+licenseNo, "Location : "+locationName);
}



function appendRDAECData(value,active){
	debugger;
	 var leftPanelHtml='';
	 var partnerOrgRDAECId=value.partnerOrgRDAECId==null?'':value.partnerOrgRDAECId;
	 if(active)
		 {
		    leftPanelHtml = leftPanelHtml + ' <li class="active" onclick="showOrgRdaecDetail('+partnerOrgRDAECId+')" id="'+partnerOrgRDAECId+'">';
		 }else{
			leftPanelHtml = leftPanelHtml + ' <li onclick="showOrgRdaecDetail('+partnerOrgRDAECId+')" id="'+partnerOrgRDAECId+'">';
		 }
	
	    var isApplicable=value.isApplicable==null?'':value.isApplicable;
		var isPioneer=value.isPioneer=="Yes"?'':value.isPioneer;
		var validFrom=value.validFrom==null?'':formatDate(value.validFrom);
		var validTo=value.validTo==null?'':formatDate(value.validTo);
		var developingRegion=value.developingRegion==null?'':value.developingRegion;
		var partnerOrgId = value.partnerOrg.partnerOrgId==null?'':value.partnerOrg.partnerOrgId;
		var elegibilityType = value.elegibilityType==null?'':value.elegibilityType;
		var eligibilityCertificateId=value.eligibilityCertificate==null?'':value.eligibilityCertificate.attachmentId==null?'':value.eligibilityCertificate.attachmentId;
		var eligibilityCertificateName=value.eligibilityCertificate==null?'':value.eligibilityCertificate.fileName==null?'':value.eligibilityCertificate.fileName;
		var remark=value.remark==null?'':value.remark;
		var isApproved=value.isApproved==null?'':value.isApproved;
		var eeComment=value.eeComment==null?'':value.eeComment;
		var isEEApproved=value.isEEApproved==null?'':value.isEEApproved;
		var ceComment=value.ceComment==null?'':value.ceComment;
		var isCEApproved=value.isCEApproved==null?'':value.isCEApproved;
		
		if(isApplicable=='Y'){
			leftPanelHtml = leftPanelHtml +' <a href="#Master" data-toggle="tab">'
		    +' <div class="col-md-12">'
		    +'  <label class="col-xs-6" id="labelOrgisApplicable-'+partnerOrgRDAECId+'">Is Not Applicable</label>'
		    +' </div>'	;
		}
		else{
			leftPanelHtml = leftPanelHtml +' <a href="#Master" data-toggle="tab">'
		    +' <div class="col-md-12">'
		    +'	<label class="col-xs-6" id="labelOrgelegibilityType-'+partnerOrgRDAECId+'">'+elegibilityType+'</label>'
		    +'	<label class="col-xs-6" id="labelOrgisPioneer-'+partnerOrgRDAECId+'">'+isPioneer+'</label>'
		    +' </div>'	
		    +' <div class="col-md-12">'
		    +'	<label class="col-xs-6 " id="labelOrgvalidFrom-'+partnerOrgRDAECId+'">'+validFrom+'</label>'
		    +'	<label class="col-xs-6" id="labelOrgvalidTo-'+partnerOrgRDAECId+'">'+validTo+'</label>'  
			+' </div>';
		}
				
				leftPanelHtml = leftPanelHtml +'<div class="col-md-12" style="display: none">'
	   +'	<label class="col-xs-6" id="labelOrgisApplicable-'+partnerOrgRDAECId+'">'+isApplicable+'</label>'
	   +'	<label class="col-xs-6" id="labelOrgisApplicableValue-'+partnerOrgRDAECId+'">'+isApplicable+'</label>'
	   +'	<label class="col-xs-6" id="labelOrgisPioneer-'+partnerOrgRDAECId+'">'+isPioneer+'</label>'
	   +'	<label class="col-xs-6" id="labelOrgvalidFrom-'+partnerOrgRDAECId+'">'+validFrom+'</label>'
	   +'	<label class="col-xs-6" id="labelOrgvalidTo-'+partnerOrgRDAECId+'">'+validTo+'</label>'
	  +'	<label class="col-xs-6" id="labelOrgdevelopingRegion-'+partnerOrgRDAECId+'">'+developingRegion+'</label>'
	  +'	<label class="col-xs-6" id="labelOrgpartnerOrgId-'+partnerOrgRDAECId+'">'+partnerOrgId+'</label>'
	  +'	<label class="col-xs-6" id="labelOrgelegibilityType-'+partnerOrgRDAECId+'">'+elegibilityType+'</label>'
	  +'	<label class="col-xs-6" id="labelOrgpartnerOrgRDAECId-'+partnerOrgRDAECId+'">'+partnerOrgRDAECId+'</label>'
	  +'	<label class="col-xs-6" id="labelEligibilityCertificateId-'+partnerOrgRDAECId+'">'+eligibilityCertificateId+'</label>'
	  +'	<label class="col-xs-6" id="labelEligibilityCertificateName-'+partnerOrgRDAECId+'">'+eligibilityCertificateName+'</label>'
	  +'	<label class="col-xs-6" id="remark-'+partnerOrgRDAECId+'">'+remark+'</label>'
	  +'	<label class="col-xs-6" id="isApproved-'+partnerOrgRDAECId+'">'+isApproved+'</label>'
	  +'	<label class="col-xs-6" id="eeComment-'+partnerOrgRDAECId+'">'+eeComment+'</label>'
	    +'	<label class="col-xs-6" id="ceComment-'+partnerOrgRDAECId+'">'+ceComment+'</label>'
	    +'	<label class="col-xs-6" id="isEEApproved-'+partnerOrgRDAECId+'">'+isEEApproved+'</label>'
	    +'	<label class="col-xs-6" id="isCEApproved-'+partnerOrgRDAECId+'">'+isCEApproved+'</label>'
	  +' </div>'
    +' </a>'
    +' </li>';
	
	
	return leftPanelHtml;
}

function attachmentRDAECDeleteResp(data)
{
	if(!data.hasError)
    {		
       $('#eligibilityFileId').val('');
	   $('#eligibilityCertificate').val('');
	   $("#a_eligibilityCertificate").html('');
	   $('.eligibilityCertificate').attr('disabled',true);
	   Alert.info(data.message);
	   var partnerOrgRDAECId=$("#partnerOrgRDAECId").val();
	   if(partnerOrgRDAECId!="")
       {
		   orgRDAECArray["RDAEC"+partnerOrgRDAECId].eligibilityCertificate=null;
       }
    }else{
    	Alert.warn(data.message);
    }
}
function showRADECFileDelBtn(fileId,fieldClass)
{
	debugger;
  if(fileId!='')
	  {
	    $("."+fieldClass).attr('disabled',false);
	  }else{
		  $("."+fieldClass).attr('disabled',true);
	  }
 }
function showOrgRdaecDetail(id){
	debugger;
	var orgRDAECdata=orgRDAECArray["RDAEC"+id];
	loadPartnerOrgRDAECRightPane(orgRDAECdata);
}
function checkForIsApplicableRDAEC(isApplicable)
{
	if(isApplicable=="Y")
		{
		   $("#partnerOrgRDAECForm .showField").hide();
		   $("#partnerOrgRDAECForm #isApplicable").prop('checked',true);
		   $('#partnerOrgRDAECForm  #isApplicable').val(isApplicable);
		   $('#elegibilityType').attr('disabled','disabled');
	       $('#developingRegion').attr('disabled','disabled');
	       $('#isPioneer').attr('disabled','disabled');
	       $('#validFrom').attr('disabled','disabled');
	       $('#validTo').attr('disabled','disabled');
	       $('#eligibilityFileId').attr('disabled','disabled');
		}else{
			   $("#partnerOrgRDAECForm .showField").show();
			   $("#partnerOrgRDAECForm #isApplicable").prop('checked',false);
			   $('#partnerOrgRDAECForm  #isApplicable').val(isApplicable);
			   $('#elegibilityType').removeAttr('disabled','disabled');
			   $('#developingRegion').removeAttr('disabled','disabled');
			   $('#isPioneer').removeAttr('disabled','disabled');
			   $('#validFrom').removeAttr('disabled','disabled');
			   $('#validTo').removeAttr('disabled','disabled');
			   $('#eligibilityFileId').removeAttr('disabled','disabled');
		}
	
}