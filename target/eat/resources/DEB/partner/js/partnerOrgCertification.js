var certificationArray=new Array();
$(document).ready(function(){
	$("#partnerOrgCertificationForm .requiredFile").removeClass('errorinput');
	
	$('#partnerOrgCertificationForm #isNotApplicable').click(function(){
		var active='';
		if (this.checked) {
			active="Y";
			$('#partnerOrgCertificationForm  .showIsoFields').find('input:text').val('');
		}else{
			active="N";
			
		}
		showOrgCertificationFields(active);
	});
	$('#cancelISOBtnId').click(function(event) {
		event.preventDefault();
		editMode=false;
		/*activeTabName="";*/
		var activeISOId=$("#partnerOrgCertificationForm #isoCertificationId").val();
		if(activeISOId!="" || activeISOId!=undefined)
			{
			   var data=certificationArray["certification"+activeISOId];
			   loadPartnerOrgCertificationRightPane(data);
			}else{
			$("#partnerOrgCertificationForm #isoCertificationId").val("");
			$('#partnerOrgCertificationForm')[0].reset();
			$('#partnerOrgCertificationForm #isNotApplicable').prop('checked',false);
			   $("#partnerOrgCertificationForm .showIsoFields").show();
			   $('#isoName').removeAttr('disabled','disabled');
			   $('#isoCertifyingAuthority').removeAttr('disabled','disabled');
			   $('#isoValidityDate').removeAttr('disabled','disabled');
			   $('#isoCertificationNo').removeAttr('disabled','disabled');
		       $('#certificateCopyId').removeAttr('disabled','disabled');
		       $('#certificateCopy').val('');
			   $("#a_certificateCopy").html('');
			}
    });
	
	$("#partnerOrgCertificationForm").find("input,select,textarea").change(function() {
		 
	  	 editMode=true;
	  	 activeTabName="Factory ISO Certification Details";
	});
	$("#partnerOrgCertificationForm .fileDeleteBtn").click(function() {
		 
	  	 editMode=true;
	  	requiredFileDeleted=true;
	  	 activeTabName="Factory ISO Certification Details";
	});
});

function getFactoryCertificationDetails(event,el)
{
	event.preventDefault();	
	
		if(!editMode && !requiredFileDeleted){
			cacheLi();
			setCurrentTab(el);
			if(getChangedFlag()){
     			submitWithParam('getOrgCertificate','partnerOrgId','onPartnerOrgCertificationTabLoad');	
     			setChangedFlag(false);
			}else{
				getCacheLi();
			}
			setActiveTabName("Factory ISO Certification Details",$('.leftPaneData li').length);
			setHeaderValues("Factory Name: "+FactoryName, "Established Date : "+establishedDate, "License No.: "+licenseNo, "Location : "+locationName);
		}else{
			event.stopPropagation();
	        Alert.warn("Please Save Changed Data Of "+activeTabName+" Tab");
		}
		$("#filterBtnId").addClass("readonly");
	    checkForFilterByRole();
}
function showOrgCertificationFields(active)
{
	if(active=='Y')
		{
		  $('#partnerOrgCertificationForm #isNotApplicable').val(active);
          $("#partnerOrgCertificationForm .showIsoFields").hide();
          $('#isoName').attr('disabled','disabled');
          $('#isoCertifyingAuthority').attr('disabled','disabled');
          $('#isoValidityDate').attr('disabled','disabled');
          $('#isoCertificationNo').attr('disabled','disabled');
          $("#partnerOrgCertificationForm  #certificateCopyId").attr('disabled','disabled');
		}else{
		   $('#partnerOrgCertificationForm #isNotApplicable').val(active);
		   $("#partnerOrgCertificationForm .showIsoFields").show();
		   $('#isoName').removeAttr('disabled','disabled');
		   $('#isoCertifyingAuthority').removeAttr('disabled','disabled');
		   $('#isoValidityDate').removeAttr('disabled','disabled');
		   $('#isoCertificationNo').removeAttr('disabled','disabled');
		   $("#partnerOrgCertificationForm  #certificateCopyId").removeAttr('disabled','disabled');
		}
}

  

function partnerOrgCertificationResp(data){
	$('.pagination').children().remove();
    if(!isEmpty(data) && !isEmpty(data.response)){
	if(!data.response.hasError)
	{
		if ($("#partnerData").val() == "partnerRegistration") {
			$("#partnerOrgCertificationForm  .partnerOrgEEApproveDiv").hide();
			$("#partnerOrgCertificationForm  .partnerOrgCEApproveDiv").hide();
		}
	showSubmitFormOnManufacturerChanges();
	editMode=false;
	setChildLoadFlag(true);
	activeTabName="";
	requiredFileDeleted=false;
	var active=true;
	var leftPanelHtml='';
	var partnerOrgCertificationId=data.partnerOrgCertificateId;
	var currentOrgCertificationId=$('ul.leftPaneData').find('li.active').attr('id');
	if(currentOrgCertificationId==partnerOrgCertificationId)
	{
		$('#'+currentOrgCertificationId).remove();
	}
	else
	{
		$('#'+currentOrgCertificationId).removeClass('active');
	}
	$("#partnerOrgCertificationForm #partnerOrgId").val(data.partnerOrg.partnerOrgId);
	$("#partnerOrgCertificationForm #isoCertificationId").val(partnerOrgCertificationId);
	leftPanelHtml=appendOrgCertificationData(data,active);
	$(".leftPaneData").prepend(leftPanelHtml);
	$("#partnerOrgCertificationForm .requiredFile").removeClass('errorinput');
	if(data.certificateCopy!=null)
	{
		 data.certificateCopy.fileName=$("#partnerOrgCertificationForm  #a_certificateCopy").html();
	}
	certificationArray["certification"+partnerOrgCertificationId]=data;
 	$('.leftPaneData').paginathing();
	
	$(".tabs-left li a label").text(function(index, currentText) {
	    return currentText.substr(0, 20);
	});
	active=false;
	Alert.info(data.response.message);
	}else{
		if(!$.isEmptyObject(data.response.errors))
		{
				var msg='';
				 $.each(data.response.errors,function(key,value){
				       msg=msg+'\n'+value.errorMessage+'\n';
				       
				   });
				    Alert.warn(msg);
		}
		else{
			Alert.warn(data.response.message);
		}
	}
    }
	$('.leftPaneData').paginathing();
	setHeaderValues("Factory Name: "+FactoryName, "Established Date : "+establishedDate, "License No.: "+licenseNo, "Location : "+locationName);
}

function onPartnerOrgCertificationTabClick(){
	submitToURL("getPartner", 'onPartnerOrgCertificationTabLoad');	
}

function onPartnerOrgCertificationTabLoad(data){
	if(!isEmpty(data) && !isEmpty(data.objectMap) && data.objectMap.hasOwnProperty('partnerOrgCertifications')){
		loadPartnerOrgCertificationLeftPane(data.objectMap.partnerOrgCertifications);
	}
	setActiveTabName("Factory ISO Certification Details",$('.leftPaneData li').length);
}
function loadPartnerOrgCertificationLeftPane(data) {
	$('.pagination').children().remove();
	$(".leftPaneData").html("");
	var leftPanelHtml = "";
	var active=false;
	var i = 0;
	var firstRow = null;
    if(!isEmpty(data)){
	$.each(data, function(key, value) {
		var partnerOrgCertificateId = value.partnerOrgCertificateId==null?'':value.partnerOrgCertificateId;
		certificationArray["certification"+partnerOrgCertificateId]=value;
		if (i == 0) {
			firstRow = value;
			active=true;
		} 
		leftPanelHtml=leftPanelHtml+appendOrgCertificationData(value,active);
		active=false;
		i++;
	});
    }
	$(".leftPaneData").append(leftPanelHtml);
	$('.leftPaneData').paginathing();
	
	$(".tabs-left li a label").text(function(index, currentText) {
	    return currentText.substr(0, 20);
	});
	loadPartnerOrgCertificationRightPane(firstRow);
}
function appendOrgCertificationData(value,active)
{
	var leftPanelHtml='';
    if(!isEmpty(value)){
	var partnerOrgCertificateId = value.partnerOrgCertificateId==null?'':value.partnerOrgCertificateId; 
	 if(active)
	 {
	    leftPanelHtml = leftPanelHtml + ' <li class="active" onclick="showOrgCertificationDetail('+partnerOrgCertificateId+')" id="'+partnerOrgCertificateId+'">';
	 }else{
		leftPanelHtml = leftPanelHtml + ' <li onclick="showOrgCertificationDetail('+partnerOrgCertificateId+')" id="'+partnerOrgCertificateId+'">';
	 }
	var isoName = value.isoName == null?'':value.isoName;
	var isoCertifyingAuthority  = value.isoCertifyingAuthority==null?'': value.isoCertifyingAuthority;
	var isoCertificationNo  =  value.isoCertificationNo == null ? '': value.isoCertificationNo;
	var isNotApplicable       = value.isNotApplicable== null ? '': value.isNotApplicable;
	var isoValidityDate       = value.isoValidityDate== null ? '': formatDate(value.isoValidityDate);
	var partnerOrgId=value.partnerOrg==null?'':value.partnerOrg.partnerOrgId==null?'':value.partnerOrg.partnerOrgId;
	var remark=value.remark==null?'':value.remark;
	var isApproved=value.isApproved==null?'':value.isApproved;
	var eeComment=value.eeComment==null?'':value.eeComment;
	var isEEApproved=value.isEEApproved==null?'':value.isEEApproved;
	var ceComment=value.ceComment==null?'':value.ceComment;
	var isCEApproved=value.isCEApproved==null?'':value.isCEApproved;

	if(isNotApplicable=='Y'){
		leftPanelHtml = leftPanelHtml +' <a href="#Master" data-toggle="tab">'
	    +' <div class="col-md-12">'
	    +'  <label class="col-xs-6" id="lblIsNotApplicable-'+partnerOrgCertificateId+'">Is Not Applicable</label>'
	    +' </div>'	;
	}
	else{
		leftPanelHtml = leftPanelHtml +' <a href="#Master" data-toggle="tab">'
		 +' <div class="col-md-12">'
		    +'  <label class="col-xs-6" id="lblIsoName-'+partnerOrgCertificateId+'">'+isoName+'</label>'
		    +'	<label class="col-xs-6 " id="lblIsoCertifyingAuthority-'+partnerOrgCertificateId+'">'+isoCertifyingAuthority+'</label>'
		    +' </div>'	
		    +' <div class="col-md-12">'
		    +'	<label class="col-xs-6" id="lblIsoCertificationNo-'+partnerOrgCertificateId+'">'+isoCertificationNo+'</label>'
		    +'	<label class="col-xs-6" id="lblIsoValidityDate-'+partnerOrgCertificateId+'">'+isoValidityDate+'</label>'
			+' </div>';
	}
			
			leftPanelHtml = leftPanelHtml +'<div class="col-md-12" style="display: none">'
			+'	<label class="col-xs-6" id="lblPartnerOrgCertificateId-'+partnerOrgCertificateId+'">'+partnerOrgCertificateId+'</label>'
		    +'	<label class="col-xs-6" id="lblIsNotApplicable-'+partnerOrgCertificateId+'">'+isNotApplicable+'</label>'
		    +'	<label class="col-xs-6" id="lblIsNotApplicableValue-'+partnerOrgCertificateId+'">'+isNotApplicable+'</label>'
		    +'	<label class="col-xs-6" id="lblpartnerOrgId-'+partnerOrgCertificateId+'">'+partnerOrgId+'</label>'
		    +'	<label class="col-xs-6" id="remark-'+partnerOrgCertificateId+'">'+remark+'</label>'
		    +'	<label class="col-xs-6" id="isApproved-'+partnerOrgCertificateId+'">'+isApproved+'</label>'
		    +'	<label class="col-xs-6" id="eeComment-'+partnerOrgCertificateId+'">'+eeComment+'</label>'
		    +'	<label class="col-xs-6" id="ceComment-'+partnerOrgCertificateId+'">'+ceComment+'</label>'
		    +'	<label class="col-xs-6" id="isEEApproved-'+partnerOrgCertificateId+'">'+isEEApproved+'</label>'
		    +'	<label class="col-xs-6" id="isCEApproved-'+partnerOrgCertificateId+'">'+isCEApproved+'</label>'
			+' </div>'
		    +' </a>'
		    +' </li>';

	   return leftPanelHtml;
    }
}

function showOrgCertificationDetail(id){
	
	var certificationData=certificationArray["certification"+id];
	loadPartnerOrgCertificationRightPane(certificationData);
}

function loadPartnerOrgCertificationRightPane(data) {
	editMode=false;
	setChildLoadFlag(true);
	/*activeTabName="";*/
  if(!$.isEmptyObject(data)){
	var isoName  = data.isoName == null ? '' : data.isoName;
	var isoCertifyingAuthority  = data.isoCertifyingAuthority == null ? '': data.isoCertifyingAuthority;
	var isoCertificationNo = data.isoCertificationNo== null ? '': data.isoCertificationNo;
	var isoValidityDate = data.isoValidityDate== null ? '': formatDate(data.isoValidityDate);
	var isNotApplicable         = data.isNotApplicable == null ? '': data.isNotApplicable;
	var partnerOrgCertificateId = data.partnerOrgCertificateId==null?'':data.partnerOrgCertificateId; 
	var partnerOrgId=data.partnerOrg==null?'':data.partnerOrg.partnerOrgId==null?'':data.partnerOrg.partnerOrgId;
	var remark=data.remark==null?'':data.remark;
	var isApproved=data.isApproved==null?'':data.isApproved;
	var eeComment=data.eeComment==null?'':data.eeComment;
	var isEEApproved=data.isEEApproved==null?'':data.isEEApproved;
	var ceComment=data.ceComment==null?'':data.ceComment;
	var isCEApproved=data.isCEApproved==null?'':data.isCEApproved;
	var certificateCopyId=data.certificateCopy==null?'':data.certificateCopy.attachmentId==null?'':data.certificateCopy.attachmentId;
	var certificateCopyName=data.certificateCopy==null?'':data.certificateCopy.fileName==null?'':data.certificateCopy.fileName;
	
	$("#partnerOrgCertificationForm #partnerOrgId").val(partnerOrgId);
	$("#partnerOrgCertificationForm #isoCertificationId").val(partnerOrgCertificateId);
	$("#partnerOrgCertificationForm #isoName").val(isoName);
	$("#partnerOrgCertificationForm #isoCertifyingAuthority").val(isoCertifyingAuthority);
	$("#partnerOrgCertificationForm #isoCertificationNo").val(isoCertificationNo);
	$("#partnerOrgCertificationForm #isoValidityDate").val(isoValidityDate);
	$("#partnerOrgCertificationForm #isNotApplicable").val(isNotApplicable);
	$("#partnerOrgCertificationForm #certificateCopy").val(certificateCopyId);
	$("#partnerOrgCertificationForm .requiredFile").removeClass('errorinput');
	var url=$("#a_certificateCopy").data('url');
	$("#a_certificateCopy").attr('href',url);
	var a_certificateCopy = $("#partnerOrgCertificationForm  #a_certificateCopy").prop('href')+'/'+certificateCopyId;
    $("#partnerOrgCertificationForm  #a_certificateCopy").prop('href', a_certificateCopy);
	$("#partnerOrgCertificationForm  #a_certificateCopy").html(certificateCopyName);
	
	checkForIsApplicable(isNotApplicable);
	changeOrgCommentAndStatusByRole('partnerOrgCertificationForm',isEEApproved,eeComment,isCEApproved,ceComment);
	showISOCertificationDelButton(certificateCopyId,'certificateCopy');
	
  }else{
	  $("#partnerOrgCertificationForm")[0].reset();
	  $("#partnerOrgCertificationForm #isoCertificationId").val("");
	  $("#partnerOrgCertificationForm  #a_certificateCopy").html("");
	  checkForIsApplicable("N");
	  $("#partnerOrgCertificationForm .partnerOrgEEApproveDiv").hide();
	  $("#partnerOrgCertificationForm .partnerOrgCEApproveDiv").hide();
  }
  setHeaderValues("Factory Name: "+FactoryName, "Established Date : "+establishedDate, "License No.: "+licenseNo, "Location : "+locationName);
}
function checkForIsApplicable(active)
{
	if(active=='Y')
	{
	  $('#partnerOrgCertificationForm #isNotApplicable').prop('checked',true);
	  $('#partnerOrgCertificationForm #isNotApplicable').val(active);
	  $("#partnerOrgCertificationForm .showIsoFields").hide();
      $('#isoName').attr('disabled','disabled');
      $('#isoCertifyingAuthority').attr('disabled','disabled');
      $('#isoValidityDate').attr('disabled','disabled');
      $('#isoCertificationNo').attr('disabled','disabled');
      $("#partnerOrgCertificationForm #certificateCopyId").attr('disabled','disabled');
      $("#partnerOrgCertificationForm .showIsoFields").find("input,select").val('');
      $("#partnerOrgCertificationForm  #a_certificateCopy").html("");
	}else{
	   $('#partnerOrgCertificationForm #isNotApplicable').prop('checked',false);
	   $('#partnerOrgCertificationForm #isNotApplicable').val(active);
	   $("#partnerOrgCertificationForm .showIsoFields").show();
	   $('#isoName').removeAttr('disabled','disabled');
	   $('#isoCertifyingAuthority').removeAttr('disabled','disabled');
	   $('#isoValidityDate').removeAttr('disabled','disabled');
	   $('#isoCertificationNo').removeAttr('disabled','disabled');
	   $("#partnerOrgCertificationForm #certificateCopyId").removeAttr('disabled','disabled');
	}

}
function certificateCopyDeleteResp(data)
{
   if(!isEmpty(data)){
	if(!data.hasError)
    {		
       $('#certificateCopyId').val('');
	   $('#certificateCopy').val('');
	   $("#a_certificateCopy").html('');
	   $('.certificateCopy').attr('disabled',true);
	   Alert.info(data.message);
	   var isoCertificationId=$("#isoCertificationId").val();
	   if(isoCertificationId!="")
       {
		   certificationArray["certification"+isoCertificationId].certificateCopy=null;
       }
    }else{
    	Alert.warn(data.message);
    }
   }
}
function showISOCertificationDelButton(fileId,fieldClass)
{
 if(fileId!='')
	  {
	    $("."+fieldClass).attr('disabled',false);
	  }else{
		  $("."+fieldClass).attr('disabled',true);
	  }
}