var orgOEArray=new Array();
$(document).ready(function(){
	$('#addOEBtnId').click(function(event){
		
		event.preventDefault();
		resetOEForm();
	});
	$('#deleteOEBtnId').click(function(event){
		event.preventDefault();
		if($("#orgOEId").val()=="" || $("#orgOEId").val()==undefined){
			Alert.warn("No records for delete");
		}else{
		  submitWithParam('deleteOrgOE','orgOEId','partnerOrgOEDelResp');
		}
    });
	$('#cancelOEBtnId').click(function(event){
		
		event.preventDefault();
		editMode = false;
		var currentOEId=$('ul.leftPaneData').find('li.active').attr('id');
		var data=orgOEArray["OE"+currentOEId];
		if(data!=undefined){
				loadOrgOERightPane(data);
			}else{
				resetOEForm();
			}
	});
		$("#orgOEForm").find("input,select,textarea").change(function() {
			 
		  	 editMode=true;
		  	 activeTabName="Factory Other Eligibility Details";
		});
		$("#orgOEForm .fileDeleteBtn").click(function() {
			 
		  	 editMode=true;
		  	 activeTabName="Factory Other Eligibility Details";
		  	 requiredFileDeleted=true;
		});

});
function getFactoryOED(event,el){
	    event.preventDefault();	
	    if(!editMode && !requiredFileDeleted){
			cacheLi();
			setCurrentTab(el);
			if(getChangedFlag()){
			  submitWithParam('getOrgOtherDetails','partnerOrgId','onPartnerOrgOETabLoad');	
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
		setActiveTabName("Other Eligibility Details",$('.leftPaneData li').length);
}
function onPartnerOrgOETabLoad(data){
	if(!isEmpty(data) && !isEmpty(data.objectMap)){
		if(data.objectMap.hasOwnProperty('oeTypes'))
			loadReferenceListById('oeTypeId',data.objectMap.oeTypes);
		if(data.objectMap.hasOwnProperty('validityTypes'))
			loadReferenceListById('validityTypeId',data.objectMap.validityTypes);
		if(data.objectMap.hasOwnProperty('partnerOrgOEs')){
			loadPartnerOrgOELeftPane(data.objectMap.partnerOrgOEs);
	    }
	}
	setActiveTabName("Other Eligibility Details",$('.leftPaneData li').length);
}
function loadPartnerOrgOELeftPane(data){

    $('.pagination').children().remove();
	$(".leftPaneData").html("");
	var leftPanelHtml='';
	var i=0;
	var active=false;
	var firstRow=null;
	if(data==null){
		resetOEForm();
		return;
	}
	$.each(data,function(key,value){
		if(!isEmpty(value)){
			var partnerOrgOEId=value.partnerOrgOEId==null?'':value.partnerOrgOEId;
			orgOEArray["OE"+partnerOrgOEId]=value;
			if(i==0){
				firstRow = value;
				active=true;
			}
			leftPanelHtml= leftPanelHtml +appendOEData(value,active);
			active=false;
			i++;
		}
	});
	$(".leftPaneData").append(leftPanelHtml);
	$('.leftPaneData').paginathing();
	
	$(".tabs-left li a label").text(function(index, currentText) {
	    return currentText.substr(0, 20);
	});
	loadOrgOERightPane(firstRow);
}

function loadOrgOERightPane(data){
	
	editMode=false;
	setChildLoadFlag(true);
	/*activeTabName="";*/
	if(!$.isEmptyObject(data)){
		var partnerOrgOEId =  data.partnerOrgOEId==null?'':data.partnerOrgOEId;
		
		var oeType =data.oeType==null?'':data.oeType;
		var authority = data.authority==null?'':data.authority;
		var regsNo = data.regsNo==null?'':data.regsNo;
		var validFrom = data.validFrom==null?'':formatDate(data.validFrom);
		var validTo =  data.validTo==null?'':formatDate(data.validTo);
		var isApproved = data.isApproved==null?'':data.isApproved;
	    var remark = data.remark==null?'':data.remark;
		var eeComment=data.eeComment==null?'':data.eeComment;
		var isEEApproved=data.isEEApproved==null?'':data.isEEApproved;
		var ceComment=data.ceComment==null?'':data.ceComment;
		var isCEApproved=data.isCEApproved==null?'':data.isCEApproved;
		var validityType=data.validityType==null?'':data.validityType;
	    var attachmentId=data.eligibilityCertificte==null?'':data.eligibilityCertificte.attachmentId==null?'':data.eligibilityCertificte.attachmentId;
	    var attachmentName=data.eligibilityCertificte==null?'':data.eligibilityCertificte.fileName==null?'':data.eligibilityCertificte.fileName;
	    var isNotApplicable=data.isNotApplicable==null?'':data.isNotApplicable;
	
	setAttribute("oeIsNotApplicable",isNotApplicable);
	$("#orgOEForm #orgOEId").val(partnerOrgOEId);
	$("#orgOEForm #oeTypeId").val(oeType);
	$("#orgOEForm #oeAuthority").val(authority);
	$("#orgOEForm #oeRegsNo").val(regsNo);
	$("#orgOEForm #oeValidFrom").val(validFrom);
	$("#orgOEForm #oeValidTo").val(validTo);
	$("#orgOEForm #validityTypeId").val(validityType);
	$("#orgOEForm #oeEligibilityCertificateId").val(attachmentId);
	$("#oeIsNotApplicable").val(isNotApplicable);
	changeOrgCommentAndStatusByRole('orgOEForm',isEEApproved,eeComment,isCEApproved,ceComment);
	oeValidityOnChange();
		
	var url=$("#a_oeEligibilityCertificate").data('url');
	$("#a_oeEligibilityCertificate").attr('href',url);
	var a_oeEligibilityCertificate = $("#orgOEForm #a_oeEligibilityCertificate").prop('href')+'/'+attachmentId;
    $("#orgOEForm #a_oeEligibilityCertificate").prop('href', a_oeEligibilityCertificate);
	$("#orgOEForm #a_oeEligibilityCertificate").html(attachmentName);
	
	showOEFileDelBtn(attachmentId,'oeEligibilityCertificateId');
	changeOEFormFields();
	$('#orgOEForm').find('input,select').removeClass("errorinput");
	}else{
		resetOEForm();
	}
	setHeaderValues("Factory Name: "+FactoryName, "Established Date : "+establishedDate, "License No.: "+licenseNo, "Location : "+locationName);
}
function changeOEFormFields(){
	if($("#oeIsNotApplicable").prop('checked')==true){
		$('#orgOEForm .hideOEFields').hide();
		$('#orgOEForm .hideOEFields').find("input, select").attr('disabled','disabled');
		$('#orgOEForm .hideOEFields').find("input, select").val('');
		$("#orgOEForm #a_oeEligibilityCertificate").html('');
	}else{
		$('#orgOEForm .hideOEFields').show();
		$('#orgOEForm .hideOEFields').find("input, select").removeAttr('disabled','disabled');
	}
}

function appendOEData(value,active){
	
	 var leftPanelHtml='';
	 if(!isEmpty(value)){
		 var partnerOrgOEId =  value.partnerOrgOEId==null?'':value.partnerOrgOEId;
		 var isNotApplicable=  value.isNotApplicable==null?'':value.isNotApplicable;
		 if(active){
			    leftPanelHtml = leftPanelHtml + ' <li class="active" onclick="showOrgOEDetail('+partnerOrgOEId+')" id="'+partnerOrgOEId+'">';
			 }else{
				leftPanelHtml = leftPanelHtml + ' <li onclick="showOrgOEDetail('+partnerOrgOEId+')" id="'+partnerOrgOEId+'">';
			 }
			var oeType =value.oeType==null?'':value.oeType;
			var authority = value.authority==null?'':value.authority;
			var regsNo = value.regsNo==null?'':value.regsNo;
			var validityType=value.validityType==null?'':value.validityType;
		   	if(isNotApplicable=="Y"){
		   	
		   		leftPanelHtml = leftPanelHtml +' <a href="#Master" data-toggle="tab">'
			    +' <div class="col-md-12">'
			    +' <label class="col-xs-6" >'+'Not Applicable'+'</label>'
			    +' </div>'	
			    +' </a>'
				+' </li>';
		   	}else{
		   		
			leftPanelHtml = leftPanelHtml +' <a href="#Master" data-toggle="tab">'
			    +' <div class="col-md-12">'
			    +'	<label class="col-xs-6" >'+oeType+'</label>'
			    +'	<label class="col-xs-6" >'+validityType+'</label>'
			    +' </div>'	
			    +' <div class="col-md-12">'
			    +'	<label class="col-xs-6" >'+authority+'</label>'
			    +'	<label class="col-xs-6" >'+regsNo+'</label>'  
				+' </div>'
				+'</a>'
				+'</li>';
		   	}
		  return leftPanelHtml;
	 }
}

function oeEligibilityCertResp(data){
	if(!isEmpty(data)){
		if(!data.hasError){		
		       $('#oeEligibilityFileId').val('');
			   $('#oeEligibilityCertificateId').val('');
			   $("#a_oeEligibilityCertificate").html('');
			   $('.oeEligibilityCertificateId').attr('disabled',true);
			   Alert.info(data.message);
			   var partnerOrgOEId=$("#orgOEId").val();
			   if(partnerOrgOEId!="")
		       {
				   orgOEArray["OE"+partnerOrgOEId].eligibilityCertificte=null;
		       }
		    }else{
		    	Alert.warn(data.message);
		    }
	}
}

function showOEFileDelBtn(fileId,fieldClass){
	 
     if(fileId!=''){
	    $("."+fieldClass).attr('disabled',false);
	  }else{
		  $("."+fieldClass).attr('disabled',true);
	  }
 }

function showOrgOEDetail(id){
	
	var oeData=orgOEArray["OE"+id];
	loadOrgOERightPane(oeData);
}
function orgSaveOEDResp(data){
	$('.pagination').children().remove();
	if(!isEmpty(data) && !isEmpty(data.response)){
		if(data.response.hasError==false){
			if ($("#partnerData").val() == "partnerRegistration") {
				$("#orgOEForm  .partnerOrgEEApproveDiv").hide();
				$("#orgOEForm  .partnerOrgCEApproveDiv").hide();
			}
			editMode=false;
			setChildLoadFlag(true);
			activeTabName="";
			requiredFileDeleted=false;
		var oeFlag=true;
		var leftPanelHtml='';
		var currentOrgOEId=$('ul.leftPaneData').find('li.active').attr('id');
		var partnerOrgOEId=data.partnerOrgOEId;
		if(currentOrgOEId==partnerOrgOEId)
		{
			$('#'+currentOrgOEId).remove();			
		}else {
		   $('#' + currentOrgOEId).removeClass('active');
	    }
		var partnerOrgId=data.partnerOrg==null?'':data.partnerOrg.partnerOrgId
		$("#orgOEForm .partnerOrgId").val(partnerOrgId);
		$("#orgOEForm #orgOEId").val(partnerOrgOEId);
		leftPanelHtml=appendOEData(data,oeFlag);
		$(".leftPaneData").prepend(leftPanelHtml);
		if(data.eligibilityCertificte!=null)
		{
		  data.eligibilityCertificte.fileName=$("#orgOEForm #a_oeEligibilityCertificate").text();
		}
		orgOEArray["OE"+partnerOrgOEId]=data;
		
		$('.leftPaneData').paginathing();
		
		$(".tabs-left li a label").text(function(index, currentText) {
		    return currentText.substr(0, 20);
		});
		oeFlag=false;
		Alert.info(data.response.message);
		showSubmitFormOnOrgChanges();
	}else{
		if(!$.isEmptyObject(data.response.errors)){
				var msg='';
				 $.each(data.response.errors,function(key,value){
					 if(!isEmpty(value)){
						 msg=msg+'\n'+value.errorMessage+'\n';
					 }
				   });
				   Alert.warn(msg);
		}else{ 
	      Alert.warn(data.response.message);
		}
	}
	}
	
	    $('.leftPaneData').paginathing();
		setHeaderValues("Factory Name: "+FactoryName, "Established Date : "+establishedDate, "License No.: "+licenseNo, "Location : "+locationName);
}

function resetOEForm(){
	$('#orgOEForm')[0].reset();
	$("#orgOEForm #orgOEId").val("");
	$("#orgOEForm #oeEligibilityCertificateId").val("");
	$("#orgOEForm #a_oeEligibilityCertificate").html("");
	/*$("#orgOEForm #orgOEId").val("");
	$("#orgOEForm #oeTypeId").val("");
	$("#orgOEForm #oeAuthority").val("");
	$("#orgOEForm #oeRegsNo").val("");
	$("#orgOEForm #oeValidFrom").val("");
	$("#orgOEForm #oeValidTo").val("");
	$("#orgOEForm #validityTypeId").val("");
	$("#orgOEForm #oeEligibilityCertificateId").val("");
    $("#orgOEForm #a_oeEligibilityCertificate").html("");*/
	showOEFileDelBtn('','oeEligibilityCertificateId');
	$("#orgOEForm .partnerOrgEEApproveDiv").hide();
    $("#orgOEForm .partnerOrgCEApproveDiv").hide();
    setAttribute("oeIsNotApplicable","N");
    changeOEFormFields();
}
function partnerOrgOEDelResp(data){	
	
	$('.pagination').children().remove();
	if(!isEmpty(data)){
		var currentOEId=$('ul.leftPaneData').find('li.active').attr('id');
		if(data.hasError==false){
			$('#'+currentOEId).remove();
			Alert.info(data.message);
			resetOEForm();
			/*orgOEArray["OE"+]*/
			showSubmitFormOnOrgChanges();
		}else{
		  Alert.warn(data.message,"","error");
		}
	}
	$('.leftPaneData').paginathing();
	$(".tabs-left li a label").text(function(index, currentText) {
	    return currentText.substr(0, 20);
	});
}

function oeValidityOnChange(){
	var validityType=$("#validityTypeId").val();
	if(validityType=='PROVISIONAL'){
		  $("#orgOEForm #oeValidityDateDiv").show();
		  $("#orgOEForm #oeValidTo").addClass('requiredDate');
		  $("#orgOEForm #oeValidTo").removeAttr('disabled','disabled');
		}else{
			$("#orgOEForm #oeValidTo").removeClass('requiredDate');
			$("#orgOEForm #oeValidityDateDiv").hide();
			$("#orgOEForm #oeValidTo").attr('disabled','disabled');
		}
}