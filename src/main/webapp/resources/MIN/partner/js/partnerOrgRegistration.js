var registrationArray=new Array();
$(document).ready(function(){
	$('#partnerOrgUdyogForm #isApplicable').click(function(){
		var active='';
		if (this.checked) {
			active="Y";
			$('#partnerOrgUdyogForm .showField').find('input,select').val('');
			$("#partnerOrgUdyogForm #a_registrationCopy").html('');
		}else{
			active="N";
		}
		showOrgRegFields(active);
	});
	
	$('#addFactoryRegBtnId').click(function(event) {
		event.preventDefault();
		$('#partnerOrgUdyogForm')[0].reset();
		$('#partnerOrgUdyogForm #udyogRegistrationId').val("");
		$('#registrationCopy').val('');
		$("#partnerOrgUdyogForm #a_registrationCopy").html('');
		$("#partnerOrgUdyogForm .partnerOrgApproveDiv").hide();
		$(".registrationCopy").attr('disabled',true);
	
		$("#partnerOrgUdyogForm  .showField").show();
		   $("#partnerOrgUdyogForm #isApplicable").prop('checked',false);
		   $('#partnerOrgUdyogForm .showField').find('input, textarea,select').removeAttr('disabled','disabled');
		   
		});
		
	$('#editFactoryRegBtnId').click(function(event) {
		event.preventDefault();
		/*$('#OrgRegFormId').removeClass('readonly');*/
		
		$('#isApplicable').click(function(){
			var active='';
			if (this.checked) {
				active="Y";
				$('#partnerOrgUdyogForm .showField').find('input:text').val('');
				$('#partnerOrgUdyogForm .showField').find('select').val('');
				/*$('#addFactoryRegBtnId').show();
				$('#deleteFactoryRegBtnId').show();*/
			}else{
				active="N";
			}
			showOrgRegFields(active);
		});
		});
	$('#cancelOrgRegBtnId').click(function(event) {
		event.preventDefault();
		editMode=false;
	   	/*activeTabName="";*/
		var regId=$('.leftPaneData').find('li.active').attr('id');
		if(regId!=undefined)
			{ 
		      var data=registrationArray["orgReg"+regId];
		      loadPartnerOrgRegRightPane(data);
			}else{
			$("#udyogRegistrationId").val("");
			$('#partnerOrgUdyogForm')[0].reset();
			$("#partnerOrgUdyogForm  .showField").show();
		    $("#partnerOrgUdyogForm #isApplicable").prop('checked',false);
			$('#partnerOrgUdyogForm .showField').find('input, textarea,select').removeAttr('disabled','disabled');
		   }
    });
	
	$('#deleteFactoryRegBtnId').click(function(event) {
		
		event.preventDefault();
		submitWithParam('deletePartnerOrgRegistration','udyogRegistrationId','partnerOrgRegistrationDelResp');
    });
	
	$("#partnerOrgUdyogForm").find("input,select,textarea").change(function() {
		 
	   	 editMode=true;
	   	 activeTabName="Factory Registration";
	});
	$("#partnerOrgUdyogForm .fileDeleteBtn").click(function() {
		 
	   	 editMode=true;
	   	 activeTabName="Factory Registration";
	    requiredFileDeleted=true;
	});
});
function getFactoryRegistration(event,el)
{
	    event.preventDefault();	
	    if(!editMode && !requiredFileDeleted){
	    	cacheLi();
	    	setCurrentTab(el);
	    	if(getChangedFlag()){
	          submitWithParam('getOrgRegistration','partnerOrgId','onPartnerOrgRegTabLoad');
	          setChangedFlag(false);
	    	}else{
	    		getCacheLi();
	    	}
	    	setActiveTabName("Factory Registration",$('.leftPaneData li').length);
	 	    setHeaderValues("Factory Name: "+FactoryName, "Established Date : "+establishedDate, "License No.: "+licenseNo, "Location : "+locationName);
	 	   
		}else{
			event.stopPropagation();
	        Alert.warn("Please Save Changed Data Of "+activeTabName+" Tab");
		}
	    $("#filterBtnId").addClass("readonly");
	    checkForFilterByRole();
	    setFactoryDate();
}
function showOrgRegFields(active)
{
	
	if(active=='Y')
		{
		  $('#partnerOrgUdyogForm  #isApplicable').val(active);
          $("#partnerOrgUdyogForm  .showField").hide();
          $('#partnerOrgUdyogForm  .showField').find('input, textarea,select').attr('disabled','disabled');
          $('#partnerOrgUdyogForm .showField').find('input, textarea,select').val('');
		  $("#partnerOrgUdyogForm #a_registrationCopy").html('');
		}else{
		   $('#partnerOrgUdyogForm #isApplicable').val(active);
		   $("#partnerOrgUdyogForm  .showField").show();
		   $('#partnerOrgUdyogForm  .showField').find('input, textarea,select').removeAttr('disabled','disabled');
		}
}



function partnerOrgRegistrationDelResp(data)
{
	/*swal(data.message);
	var currentOrgRegId=$('ul.leftPaneData').find('li.active').attr('id');
	
	
	event.preventDefault();*/
   if(!isEmpty(data)){
	$('.pagination').children().remove();
	var currentOrgRegId=$('ul.leftPaneData').find('li.active').attr('id');
	if(data.hasError==false)
	{
		$('#'+currentOrgRegId).remove();
		$('#partnerOrgUdyogForm')[0].reset();
		$('#partnerOrgUdyogForm #udyogRegistrationId').val("");
		$('#registrationCopy').val('');
		showOrgRegFields("N");
		$("#partnerOrgUdyogForm #a_registrationCopy").html('');
		Alert.info(data.message,'','success');
		showSubmitFormOnOrgChanges();
	}
    else
	{
	  Alert.warn(data.message,"","error");
	}
   }
	$('.leftPaneData').paginathing();
	
}


function partnerOrgRegistrationResp(data){
	
	$('.pagination').children().remove();
   if(!isEmpty(data) && !isEmpty(data.response)){
	if(data.response.hasError==false)
	{
		if ($("#partnerData").val() == "partnerRegistration") {
			$("#partnerOrgUdyogForm  .partnerOrgEEApproveDiv").hide();
			$("#partnerOrgUdyogForm  .partnerOrgCEApproveDiv").hide();
		}
    editMode=false;
	activeTabName="";
	requiredFileDeleted=false;
	var regFlag=true;
	var leftPanelHtml='';
	var currentOrgRegId=$('ul.leftPaneData').find('li.active').attr('id');
	var udyogRegistrationId= data.partnerOrgRegistrationId;
	if(currentOrgRegId==udyogRegistrationId)
	{
		$('#'+currentOrgRegId).remove();
	}
	else
	{
		$('#'+currentOrgRegId).removeClass('active');
	}
	$("#partnerOrgUdyogForm #partnerOrgId").val(data.partnerOrg.partnerOrgId);
	$("#partnerOrgUdyogForm #udyogRegistrationId").val(udyogRegistrationId);
	leftPanelHtml=appendOrgRegData(data,regFlag);
	$(".leftPaneData").prepend(leftPanelHtml);
	if(data.registrationCopy!=null)
	{
	 data.registrationCopy.fileName=$("#partnerOrgUdyogForm #a_registrationCopy").html();
	}
	registrationArray["orgReg"+udyogRegistrationId]=data;
	
	$("#lblregistrationCopyName-"+udyogRegistrationId).html($("#partnerOrgUdyogForm #a_registrationCopy").html());
	regFlag=false;
	Alert.info(data.response.message,'','success');	
	showSubmitFormOnOrgChanges();
	setChildLoadFlag(true);
	setChangedFlagById("factoryItemManufacturedTabId",true);
	setHeaderValues("Factory Name: "+FactoryName, "Established Date : "+establishedDate, "License No.: "+licenseNo, "Location : "+locationName);
   }else{
		if(!$.isEmptyObject(data.response.errors))
		{
				var msg='';
				 $.each(data.response.errors,function(key,value){
				       msg=msg+'\n'+value.errorMessage+'\n';
				       
				   });
				   Alert.warn(msg);
		}else{ 
	      Alert.warn(data.response.message,'','error');
		}
	 }
   }
	$('.leftPaneData').paginathing();
	
	$(".tabs-left li a label").text(function(index, currentText) {
	    return currentText.substr(0, 20);
	});

}

function onPartnerTabClick(){
	submitToURL("getPartner", 'onPartnerOrgRegTabLoad');	
}

function onPartnerOrgRegTabLoad(data){
   if(!isEmpty(data) && !isEmpty(data.objectMap)){
	if(data.objectMap.hasOwnProperty('validityTypes'))
		loadReferenceListById('validityType',data.objectMap.validityTypes);
	if(data.objectMap.hasOwnProperty('registrationTypes'))
		loadReferenceListById('registrationType',data.objectMap.registrationTypes);
	if(data.objectMap.hasOwnProperty('natureCode'))
		loadReferenceListById('natureCode',data.objectMap.natureCode);
	if(data.objectMap.hasOwnProperty('categoryCode'))
		loadReferenceListById('categoryCode',data.objectMap.categoryCode);
	if(data.objectMap.hasOwnProperty('partnerOrgRegistrations')){
		loadPartnerOrgRegLeftPane(data.objectMap.partnerOrgRegistrations);
	}
   }
	setActiveTabName("Factory Registration",$('.leftPaneData li').length);
}

function loadPartnerOrgRegLeftPane(data){
	
	$('.pagination').children().remove();
	$(".leftPaneData").html("");
	var leftPanelHtml='';
	var i=0;
	var active=false;
	var firstRow=null;
	if(!isEmpty(data)){
	$.each(data,function(key,value){
		 var partnerOrgRegistrationId=value.partnerOrgRegistrationId==null?'':value.partnerOrgRegistrationId;
		registrationArray["orgReg"+partnerOrgRegistrationId]=value;
		if(i==0){
			firstRow = value;
			active=true;
		}
		leftPanelHtml= leftPanelHtml +appendOrgRegData(value,active);
		active=false;
		i++;
	});
	}
	$(".leftPaneData").append(leftPanelHtml);
	$('.leftPaneData').paginathing();
	
	$(".tabs-left li a label").text(function(index, currentText) {
	    return currentText.substr(0, 20);
	});
	loadPartnerOrgRegRightPane(firstRow);
}

function appendOrgRegData(value,active)
{
	
	 var leftPanelHtml='';
	 if(!isEmpty(value)){
	 var partnerOrgRegistrationId=value.partnerOrgRegistrationId==null?'':value.partnerOrgRegistrationId;
	 if(active)
		 {
		    leftPanelHtml = leftPanelHtml + ' <li class="active" onclick="showOrgRegDetail('+partnerOrgRegistrationId+')" id="'+partnerOrgRegistrationId+'">';
		 }else{
			leftPanelHtml = leftPanelHtml + ' <li onclick="showOrgRegDetail('+partnerOrgRegistrationId+')" id="'+partnerOrgRegistrationId+'">';
		 }
	
	    var partnerOrgId=value.partnerOrg.partnerOrgId==null?'':value.partnerOrg.partnerOrgId;
	 	var registrationAuthority = value.registrationAuthority==null?'':value.registrationAuthority;
		var registrationNo = value.registrationNo==null?'':value.registrationNo;
		var registrationIssueDate = value.issueDate==null?'':formatDate(value.issueDate);
		var validityDate = value.validityDate==null?'':formatDate(value.validityDate);
		var productCommencement = value.productCommencement==null?'':formatDate(value.productCommencement);
		var registrationCopy = value.registrationCopy==null?'':value.registrationCopy;
		var isApplicable = value.isApplicable==null?'':value.isApplicable;
		var registrationType = value.registrationType==null?'':value.registrationType;
		var registrationCopyId=value.registrationCopy==null?'':value.registrationCopy.attachmentId==null?'':value.registrationCopy.attachmentId;
		var registrationCopyName=value.registrationCopy==null?'':value.registrationCopy.fileName==null?'':value.registrationCopy.fileName;
		var remark=value.remark==null?'':value.remark;
		var isApproved=value.isApproved==null?'':value.isApproved;
		var eeComment=value.eeComment==null?'':value.eeComment;
		var isEEApproved=value.isEEApproved==null?'':value.isEEApproved;
		var ceComment=value.ceComment==null?'':value.ceComment;
		var isCEApproved=value.isCEApproved==null?'':value.isCEApproved;
		var validityType = value.validityType==null?'':value.validityType;
		
		if(isApplicable=='Y'){
			leftPanelHtml = leftPanelHtml +' <a href="#Master" data-toggle="tab">'
		    +' <div class="col-md-12">'
		    +'  <label class="col-xs-6" id="lblIsApplicable-'+partnerOrgRegistrationId+'">Is Not Applicable</label>'
		    +' </div>'	;
		}
		else{
			leftPanelHtml = leftPanelHtml +' <a href="#Master" data-toggle="tab">'
		    +' <div class="col-md-12">'
		    +'	<label class="col-xs-6" id="lblregistrationType-'+partnerOrgRegistrationId+'" >'+registrationType+'</label>'
		    +'	<label class="col-xs-6 " id="lblRegistrationNo-'+partnerOrgRegistrationId+'">'+registrationNo+'</label>'
		    +' </div>'	
		    +' <div class="col-md-12">'
		    +'	<label class="col-xs-6" id="lblRegistrationIssueDate-'+partnerOrgRegistrationId+'">'+registrationIssueDate+'</label>'
		    +'	<label class="col-xs-6" id="lblValidityDate-'+partnerOrgRegistrationId+'" >'+validityDate+'</label>'
		    +' </div>';
		}
		
		leftPanelHtml = leftPanelHtml +'<div class="col-md-12" style="display: none">'
        
		+'	<label class="col-xs-6" id="lblPartnerOrgRegistrationId-'+partnerOrgRegistrationId+'">'+partnerOrgRegistrationId+'</label>'
	    +'	<label class="col-xs-6" id="lblRegistrationAuthority-'+partnerOrgRegistrationId+'">'+registrationAuthority+'</label>'
	    +'	<label class="col-xs-6" id="lblRegistrationNo-'+partnerOrgRegistrationId+'" >'+registrationNo+'</label>'
	    +'	<label class="col-xs-6" id="lblRegistrationIssueDate-'+partnerOrgRegistrationId+'" >'+registrationIssueDate+'</label>'
	    +'	<label class="col-xs-6" id="lblValidityDate-'+partnerOrgRegistrationId+'" >'+validityDate+'</label>'
	    +'	<label class="col-xs-6" id="lblProductCommencement-'+partnerOrgRegistrationId+'" >'+productCommencement+'</label>'
/*	    +'	<label class="col-xs-6" id="lblItemList-'+partnerOrgRegistrationId+'" >'+itemList+'</label>'*/
	    +'	<label class="col-xs-6" id="lblRegistrationCopy-'+partnerOrgRegistrationId+'" >'+registrationCopy+'</label>'
	    +'	<label class="col-xs-6" id="lblIsApplicable-'+partnerOrgRegistrationId+'" >'+isApplicable+'</label>'
	    +'	<label class="col-xs-6" id="lblIsApplicableValue-'+partnerOrgRegistrationId+'" >'+isApplicable+'</label>'
	    +'	<label class="col-xs-6" id="lblpartnerOrg-'+partnerOrgRegistrationId+'" >'+partnerOrgId+'</label>'
	    +'  <label class="col-xs-6" id="lblRegistrationAuthority-'+partnerOrgRegistrationId+'">'+registrationAuthority+'</label>'
	    +'	<label class="col-xs-6" id="lblregistrationCopyId-'+partnerOrgRegistrationId+'" >'+registrationCopyId+'</label>'
	    +'	<label class="col-xs-6" id="lblregistrationCopyName-'+partnerOrgRegistrationId+'" >'+registrationCopyName+'</label>'
	    +'	<label class="col-xs-6" id="remark-'+partnerOrgRegistrationId+'" >'+remark+'</label>'
	    +'	<label class="col-xs-6" id="eeComment-'+partnerOrgRegistrationId+'">'+eeComment+'</label>'
	    +'	<label class="col-xs-6" id="ceComment-'+partnerOrgRegistrationId+'">'+ceComment+'</label>'
	    +'	<label class="col-xs-6" id="isEEApproved-'+partnerOrgRegistrationId+'">'+isEEApproved+'</label>'
	    +'	<label class="col-xs-6" id="isCEApproved-'+partnerOrgRegistrationId+'">'+isCEApproved+'</label>'
	    +'	<label class="col-xs-6" id="validityType-'+partnerOrgRegistrationId+'">'+validityType+'</label>'
		+' </div>'
        +' </a>'
        +' </li>';

	 }
	return leftPanelHtml;
	
}

function showOrgRegDetail(id)
{
	var regData=registrationArray["orgReg"+id];
	loadPartnerOrgRegRightPane(regData);
	
}

function loadPartnerOrgRegRightPane(data){
	
	editMode=false;
	
	setChildLoadFlag(true);
	/*activeTabName="";*/
if(!$.isEmptyObject(data)){
	var registrationAuthority = data.registrationAuthority==null?'':data.registrationAuthority;
	var registrationNo = data.registrationNo==null?'':data.registrationNo;
	var registrationIssueDate = data.issueDate==null?'':formatDate(data.issueDate);
	var validityDate = data.validityDate==null?'':formatDate(data.validityDate);
	var productCommencement = data.productCommencement==null?'':formatDate(data.productCommencement);
	/*var itemList = data.itemList==null?'':data.itemList;*/
	var registrationCopy = data.registrationCopy==null?'':data.registrationCopy;
	var isApplicable = data.isApplicable==null?'':data.isApplicable;
	var partnerOrgRegistrationId = data.partnerOrgRegistrationId==null?'':data.partnerOrgRegistrationId;
	var partnerOrgId=data.partnerOrg.partnerOrgId==null?'':data.partnerOrg.partnerOrgId;
	var registrationType = data.registrationType==null?'':data.registrationType;
	var registrationCopyId=data.registrationCopy==null?'':data.registrationCopy.attachmentId==null?'':data.registrationCopy.attachmentId;
	var registrationCopyName=data.registrationCopy==null?'':data.registrationCopy.fileName==null?'':data.registrationCopy.fileName;
	var remark=data.remark==null?'':data.remark;
	var isApproved=data.isApproved==null?'':data.isApproved;
	var eeComment=data.eeComment==null?'':data.eeComment;
	var isEEApproved=data.isEEApproved==null?'':data.isEEApproved;
	var ceComment=data.ceComment==null?'':data.ceComment;
	var isCEApproved=data.isCEApproved==null?'':data.isCEApproved;
	var validityType = data.validityType==null?'':data.validityType;
	var plantInvestment=getValue(data.plantInvestment);
	var natureCode=getValue(data.natureCode);
	var categoryCode=getValue(data.categoryCode);
	var monetaryLimit=getValue(data.monetaryLimit);

	checkForIsApplicableReg(isApplicable);
	$("#partnerOrgUdyogForm #udyogRegistrationId").val(partnerOrgRegistrationId);
	$("#partnerOrgUdyogForm #registrationAuthority").val(registrationAuthority);
	$("#partnerOrgUdyogForm #registrationNo").val(registrationNo);
	$("#partnerOrgUdyogForm #registrationIssueDate").val(registrationIssueDate);
	$("#partnerOrgUdyogForm #validityDate").val(validityDate);
	$("#partnerOrgUdyogForm #productCommencement").val(productCommencement);
	/*$("#partnerOrgUdyogForm #itemList").val(itemList);*/
	$("#partnerOrgUdyogForm #registrationCopy").val(registrationCopy);
	$("#partnerOrgUdyogForm #isApplicable").val(isApplicable);
	$("#partnerOrgUdyogForm #partnerOrgId").val(partnerOrgId);
	$("#partnerOrgUdyogForm #registrationType").val(registrationType);
	onChangeRegistrationType();
	$("#partnerOrgUdyogForm #plantInvestment").val(plantInvestment);
	$("#partnerOrgUdyogForm #natureCode").val(natureCode);
	$("#partnerOrgUdyogForm #categoryCode").val(categoryCode);
	$("#partnerOrgUdyogForm #monetaryLimit").val(monetaryLimit);
	
	$("#partnerOrgUdyogForm #registrationCopy").val(registrationCopyId);
	$("#partnerOrgUdyogForm #validityType").val(validityType);
	$("#partnerOrgUdyogForm .dropDown").removeClass('errorinput');
	$("#partnerOrgUdyogForm .requiredFile").removeClass('errorinput');
	/*$("#partnerOrgUdyogForm #remark").val(remark);
	setApprovedStatus('partnerOrgUdyogForm',isApproved);*/
	validityOnChange(validityType);
	changeOrgCommentAndStatusByRole('partnerOrgUdyogForm',isEEApproved,eeComment,isCEApproved,ceComment);
	
	var url=$("#a_registrationCopy").data('url');
	$("#a_registrationCopy").attr('href',url);
	var a_registrationCopy = $("#partnerOrgUdyogForm #a_registrationCopy").prop('href')+'/'+registrationCopyId;
    $("#partnerOrgUdyogForm #a_registrationCopy").prop('href', a_registrationCopy);
	$("#partnerOrgUdyogForm #a_registrationCopy").html(registrationCopyName);
	
	showRegFileDelBtn(registrationCopyId,'registrationCopy');
	
}
else{
	$('#partnerOrgUdyogForm').find('input:text').val('');
	$('#partnerOrgUdyogForm').find('select').val('');
	$("#partnerOrgUdyogForm #udyogRegistrationId").val("");
	$("#partnerOrgUdyogForm #a_registrationCopy").html('');
	$("#partnerOrgUdyogForm .partnerOrgEEApproveDiv").hide();
	$("#partnerOrgUdyogForm .partnerOrgCEApproveDiv").hide();
	checkForIsApplicableReg("N");
	onChangeRegistrationType();
}	
setHeaderValues("Factory Name: "+FactoryName, "Established Date : "+establishedDate, "License No.: "+licenseNo, "Location : "+locationName);
}
function validityOnChange(validityType)
{
	
	if(validityType=='PROVISIONAL')
		{
		  $("#partnerOrgUdyogForm #validityDiv").show();
		  $("#partnerOrgUdyogForm #validityDate").addClass('requiredDate');
		  
		}else{
			$("#partnerOrgUdyogForm #validityDate").removeClass('requiredDate');
			$("#partnerOrgUdyogForm #validityDiv").hide();
		}
}
function regAttachmentDeleteResp(data)
{
   if(!isEmpty(data)){
	if(!data.hasError)
    {		
       $('#registrationFileId').val('');
	   $('#registrationCopy').val('');
	   $("#a_registrationCopy").html('');
	   $('.registrationCopy').attr('disabled',true);
	   Alert.info(data.message);
	   var udyogRegistrationId=$("#udyogRegistrationId").val();
	   if(udyogRegistrationId!="")
		{
	     registrationArray["orgReg"+udyogRegistrationId].registrationCopy=null;
		}
    }else{
    	Alert.warn(data.message);
    }
   }
}

function showRegFileDelBtn(fileId,fieldClass)
{
	
  if(fileId!='')
	  {
	    $("."+fieldClass).attr('disabled',false);
	  }else{
		  $("."+fieldClass).attr('disabled',true);
	  }
 }

function checkForIsApplicableReg(isApplicable)
{
	
	if(isApplicable=='Y')
		{
		  $("#partnerOrgUdyogForm .showField").hide();
		  $('#partnerOrgUdyogForm  #isApplicable').val(isApplicable);
		  $("#partnerOrgUdyogForm #isApplicable").prop('checked',true);
		  $('#partnerOrgUdyogForm .showField').find('input, textarea,select').attr('disabled','disabled');
		  $('#partnerOrgUdyogForm .showField').find('input, textarea,select').val('');
		  $("#partnerOrgUdyogForm #a_registrationCopy").html('');
		}else{
		   $("#partnerOrgUdyogForm  .showField").show();
		   $("#partnerOrgUdyogForm #isApplicable").prop('checked',false);
		   $('#partnerOrgUdyogForm  #isApplicable').val(isApplicable);
		   $('#partnerOrgUdyogForm .showField').find('input, textarea,select').removeAttr('disabled','disabled');
		}
}

function onChangeRegistrationType(){
	
	var value=$('#registrationType').val();
	if(value=='MSE'){
		$(".MSE").show();
		$("#plantInvestment").removeAttr('disabled');
		$("#natureCode").removeAttr('disabled');
		$("#categoryCode").removeAttr('disabled');
		$(".NSIC").hide();
		$("#monetaryLimit").attr('disabled','disabled');
		$("#validityType").val('');
		$("#partnerOrgUdyogForm #validityDiv").hide();
		$("#partnerOrgUdyogForm #validityDate").removeClass('requiredDate');
		$("#partnerOrgUdyogForm .validityType").removeClass('readonly');
	}else if(value=="NSIC"){
		$(".MSE").hide();
		$("#plantInvestment").attr('disabled','disabled');
		$("#natureCode").attr('disabled','disabled');
		$("#categoryCode").attr('disabled','disabled');
		
		$(".NSIC").show();
		$("#monetaryLimit").removeAttr('disabled');
		$("#validityType").val('PROVISIONAL');
		$("#validityType").removeClass("errorinput");
		$("#partnerOrgUdyogForm #validityDiv").show();
		$("#partnerOrgUdyogForm #validityDate").addClass('requiredDate');
		$("#partnerOrgUdyogForm .validityType").addClass('readonly');
	}else{
		$(".MSE").hide();
		$("#plantInvestment").attr('disabled','disabled');
		$("#natureCode").attr('disabled','disabled');
		$("#categoryCode").attr('disabled','disabled');
		
		$(".NSIC").hide();
		$("#monetaryLimit").attr('disabled','disabled');
		$("#validityType").val('');
		$("#partnerOrgUdyogForm #validityDiv").hide();
		$("#partnerOrgUdyogForm #validityDate").removeClass('requiredDate');
		$("#partnerOrgUdyogForm .validityType").removeClass('readonly');
	}
}