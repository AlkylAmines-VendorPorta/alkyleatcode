var holderTypeList;
$(document).ready(function(){
	$("#attorneyCertDiv").hide();
	
	$('#addSignatureBtnId').click(function(event) {
		event.preventDefault();
		$('#partnerSignatoryForm')[0].reset();
		$("#radio_male").attr('checked', 'checked');
		$("#partnerSignatoryForm #locationId").val("");
		$("#partnerSignatoryForm #userDetailId").val("");
		$("#partnerSignatoryForm #partnerSignatoryId").val("");
		$("#partnerSignatoryForm #a_digitallySignCopyCopy").text("");
		$("#partnerSignatoryForm #a_powerAttorneyCopy").text("");
		$('#partnerSignatoryForm #powerAttorneyFileId').val('');
		$('#partnerSignatoryForm #powerAttorneyCopy').val('');
		$('#partnerSignatoryForm #digitallySignFileId').val('');
		$('#partnerSignatoryForm #digitallySignCopy').val('');
		$("#partnerSignatoryForm #attorneyCertDiv").hide();
		$("#partnerSignatoryForm .approveDiv").hide();
		$("#partnerSignatoryForm #holderTypeId").removeClass("readonly");
    });
	$('#editSignatureBtnId').click(function(event) {
		event.preventDefault();
		
    });
	$('#deleteSignatureBtnId').click(function(event) {
		event.preventDefault();
		$("#holderTypeId").removeClass("readonly");
		deleteSignatory();
    });
	$('#cancelSignatoryBtnId').click(function(event) {
		event.preventDefault();
		$("#holderTypeId").removeClass("readonly");
		editMode=false;
	   /*	activeTabName="";*/
		var activeSignatoryId=$('.leftPaneData').find('li.active').attr('id');
		if(activeSignatoryId!=undefined)
			{
			 showSignatoryDetail(activeSignatoryId);
			}
		else
		   {
			$('#partnerSignatoryForm')[0].reset();
		    $("#a_digitallySignCopyCopy").text("");
		    $("#a_powerAttorneyCopy").text("");
		   }
    });
	
	$('#copyAddress').click(function(event){
		   
		event.preventDefault();
		submitWithParam('copyAddress','bPartnerId','CopyAllAddres');
	});
	$("#partnerSignatoryForm").find("input,select,textarea").change(function() {
		   
	   	 editMode=true;
	   	 activeTabName="Digital Signatory";
	});
	$("#partnerSignatoryForm #copyAddress").click(function() {
   	 editMode=true;
   	 activeTabName="Digital Signatory";
   });
	$("#partnerSignatoryForm .fileDeleteBtn").click(function() {
	   	 editMode=true;
	   	 requiredFileDeleted=true;
	   	 activeTabName="Digital Signatory";
	});
	
});

function getPartnerSignatory(event,el)
{
	    event.preventDefault();	
		if(!editMode && !requiredFileDeleted){
			$('#pagination-here').empty();
			cacheLi();
			setCurrentTab(el);
			if(getChangedFlag()){
			  submitWithParam('getSignatoryDetails','bPartnerId','onPartnerOrgSigTabLoad');	
			  setChangedFlag(false);
			}else{
				getCacheLi();
			}
			setActiveTabName("Digital Signatory",$('.leftPaneData li').length);	
			setPartnerHeaderValues("Company Name: "+companyName, "Company Registration No. : "+registrationNo, "Vendor SAP Code : "+vendorSAPCode,"Status : "+partnerStatus);
			/*setPartnerHeaderValues("Company Name: "+companyName, "Company Registration No. : "+registrationNo, "Company Type : "+companyType,"Status : "+partnerStatus);*/
		}else{
			event.stopPropagation();
	        Alert.warn("Please Save Changed Data Of "+activeTabName+" Tab");
		}
		$("#filterBtnId").addClass("readonly");
	    checkForFilterByRole();
}
function setGender(){
	var title=$("#partnerSignatoryForm #title").val();
	if(title=="MR."){
		$("#radio_male").prop("checked",true);
		$("#radio_male").attr("disabled",false);
		$("#radio_female").attr("disabled",true);
		$("#radio_other").prop("checked",false);
	}else if(title=="MRS." || title=="MISS"){
		$("#radio_male").prop("checked",false);
		$("#radio_male").attr("disabled",true);
		$("#radio_female").attr("disabled",false);
		$("#radio_female").prop("checked",true);
		$("#radio_other").prop("checked",false);
	}else {
		$("#radio_male").prop("checked",true);
		$("#radio_male").attr("disabled",false);
		$("#radio_female").attr("disabled",false);
		$("#radio_female").prop("checked",false);
		$("#radio_other").prop("checked",false);
	}
}
function onPartnerOrgSigResp(data){
	setChildLoadFlag(true);
	if(!isEmpty(data) && !isEmpty(data.response)){
		if(data.response.hasError==false){
			if ($("#partnerData").val() == "partnerRegistration") {
			$("#partnerSignatoryForm  .approveCEDiv").hide();
			$("#partnerSignatoryForm  .approveEEDiv").hide();
		    }
			loadRecentSignatory(data);
			Alert.info(data.response.message);
			setPartnerHeaderValues("Company Name: "+companyName, "Company Registration No. : "+registrationNo, "Vendor SAP Code : "+vendorSAPCode,"Status : "+partnerStatus);
			 /*setPartnerHeaderValues("Company Name: "+companyName, "Company Registration No. : "+registrationNo, "Company Type : "+companyType,"Status : "+partnerStatus);*/
			setActiveTabName("Digital Signatory",$('.leftPaneData li').length);
		}
	else{
		if (data.response.hasError==true) {
			var msg = '';
			$.each(data.response.errors, function(key, value) {
				if(!isEmpty(value)){
					msg = msg +  value.errorMessage +',';
				}
			});
			Alert.warn(msg);
		}
	}
	}	
}

function onPartnerOrgSigTabClick(){
	submitToURL("getPartner", 'onPartnerOrgSigTabLoad');
}

function onPartnerOrgSigTabLoad(data){
	if(!isEmpty(data) && !isEmpty(data.objectMap)){
			if(data.objectMap.hasOwnProperty('designations')){
				loadSigDesignation(data.objectMap.designations);
		    }
			/*if(data.objectMap.hasOwnProperty('districts')){
				loadSigDistrict(data.objectMap.districts);
		}
			if(data.objectMap.hasOwnProperty('regions')){
				loadSigRegion(data.objectMap.regions);
		}*/
			if(data.objectMap.hasOwnProperty('countries')){
				 loadSigCountry(data.objectMap.countries);
	    	}
			if(data.objectMap.hasOwnProperty('holderType')){
				holderTypeList=data.objectMap.holderType;
				loadReferenceListById('partnerSignatoryForm #holderTypeId',data.objectMap.holderType);
			}
			if(data.objectMap.hasOwnProperty('title')){
				loadReferenceListById('partnerSignatoryForm #title',data.objectMap.title);
			}
			if(data.objectMap.hasOwnProperty('partnerSignatories')){
				if(!$.isEmptyObject(data.objectMap.partnerSignatories)){
					loadPartnerOrgSigLeftPane(data.objectMap.partnerSignatories);
				}else{
					$(".leftPaneData").html("");
					$('#partnerSignatoryForm')[0].reset();
					$("#radio_male").attr('checked', 'checked');
					$("#partnerSignatoryForm #locationId").val("");
					$("#partnerSignatoryForm #userDetailId").val("");
					$("#partnerSignatoryForm #partnerSignatoryId").val("");
					$("#partnerSignatoryForm #a_digitallySignCopyCopy").text("");
					$("#partnerSignatoryForm #a_powerAttorneyCopy").text("");
					$('#partnerSignatoryForm #powerAttorneyFileId').val('');
					$('#partnerSignatoryForm #powerAttorneyCopy').val('');
					$('#partnerSignatoryForm #digitallySignFileId').val('');
					$('#partnerSignatoryForm #digitallySignCopy').val('');
					$("#partnerSignatoryForm #attorneyCertDiv").hide();
					$("#partnerSignatoryForm .approveDiv").hide();
					$("#partnerSignatoryForm #holderTypeId").removeClass("readonly");
				}
		    }
			/*if(data.objectMap.hasOwnProperty('digitalTestCopy')){
				showDigitalTestCopy(data.objectMap.digitalTestCopy);
		    }*/
	}
			setActiveTabName("Digital Signatory",$('.leftPaneData li').length);
}
function loadPartnerOrgSigLeftPane(data){
	   
	$('.pagination').children().remove();
	$(".leftPaneData").html("");
	var leftPanelHtml="";
	var i=0;
	var firstRow=null;
	if(!isEmpty(data)){
		$.each(data,function(key,value){
			if(!isEmpty(value) && !$.isEmptyObject(value.userDetail)){
				var userDetailId=value.userDetail==null?'':value.userDetail.userDetailsId;
					var firstName = value.userDetail==null?'':value.userDetail.firstName;
					var middleName = value.userDetail==null?'':value.userDetail.middleName;
					var lastName  = value.userDetail==null?'':value.userDetail.lastName;
					var email 	  = value.userDetail==null?'':value.userDetail.email;
					var mobileNo  = value.userDetail==null?'':value.userDetail.mobileNo;
					var telephone1 = value.userDetail==null?'':value.userDetail.telephone1;
					var telephone2 = value.userDetail==null?'':value.userDetail.telephone2;
					var fax1  = value.userDetail==null?'':value.userDetail.fax1;
					var fax2 	  = value.userDetail==null?'':value.userDetail.fax2;
					var designationId  = value.userDetail==null?'':value.userDetail.designation==null?'':value.userDetail.designation.designationId;
					var dob=value.userDetail==null?'':value.userDetail.dob==null?'':formatDate(value.userDetail.dob);
					var gender=value.userDetail==null?'':value.userDetail.gender;
					var title=value.userDetail==null?'':value.userDetail.title;
					
					if(!$.isEmptyObject(value.location)){
						var locationId= value.location==null?'':value.location.locationId;
						var registeredAddress = value.location==null?'':value.location.address1;
						var city = value.location==null?'':value.location.city;
						var districtId  = value.location==null?'':value.location.district==null?'':value.location.district.districtId;
						var countryId  = value.location==null?'':value.location.country==null?'':value.location.country.countryId;
						var regionId  = value.location==null?'':value.location.region==null?'':value.location.region.regionId;
						var postal = value.location.postal==null?'':value.location.postal;
						
						
					}
					
					var validity=value.validFrom==null?'':formatDate(value.validFrom);
					var partnerSignatoryId=value.partnerSignatoryId==null?'':value.partnerSignatoryId;
					var remark=value.remark==null?'':value.remark;
					var isApproved=value.isApproved==null?'':value.isApproved;
					var eeComment=value.eeComment==null?'':value.eeComment;
					var isEEApproved=value.isEEApproved==null?'':value.isEEApproved;
					var ceComment=value.ceComment==null?'':value.ceComment;
					var isCEApproved=value.isCEApproved==null?'':value.isCEApproved;
					
					var holderType=value.holderType==null?'':value.holderType;
					var holderTypeName=holderTypeList[holderType];
					
					var attorneyCertificateId = value.attorneyCertificate == null ? '': value.attorneyCertificate.attachmentId == null ? '': value.attorneyCertificate.attachmentId;
					var attorneyCertificateName = value.attorneyCertificate == null ? '': value.attorneyCertificate.fileName == null ? '': value.attorneyCertificate.fileName;
					var digitallySignTestDocId = value.digitallySignTestDoc == null ? '': value.digitallySignTestDoc.attachmentId == null ? '': value.digitallySignTestDoc.attachmentId;
					var digitallySignTestDocName = value.digitallySignTestDoc == null ? '': value.digitallySignTestDoc.fileName == null ? '': value.digitallySignTestDoc.fileName;
					var otherDesignation=value.otherDesignation==null?'':value.otherDesignation;
					
					if(i==0){
						firstRow = value;
						leftPanelHtml = leftPanelHtml + ' <li class="active" onclick="showSignatoryDetail('+partnerSignatoryId+')" id="'+partnerSignatoryId+'">';
					}else{
						leftPanelHtml = leftPanelHtml + ' <li onclick="showSignatoryDetail('+partnerSignatoryId+')" id="'+partnerSignatoryId+'">';
					}

					leftPanelHtml = leftPanelHtml +' <a href="#Master" data-toggle="tab">'
			        +' <div class="col-md-12">'
			        +'  <label class="col-xs-6" id="signatory_'+partnerSignatoryId+'_firstName">'+firstName+'</label>'
			        +'	<label class="col-xs-6" id="signatory_'+partnerSignatoryId+'_lastName">'+lastName+'</label>'
			        +' </div>'	
			        +' <div class="col-md-12">'
			        +'	<label class="col-xs-6" id="holderTypeName_'+partnerSignatoryId+'">'+holderTypeName+'</label>'
			       	+' </div>'
			        +' </a>'
			        +' <div class="col-md-12" style="display: none">'
			        +'	<label class="col-xs-6" id="signatory_'+partnerSignatoryId+'_mobileNo">'+mobileNo+'</label>'
			        +'	<label class="col-xs-6" id="signatory_'+partnerSignatoryId+'_signatoryId">'+partnerSignatoryId+'</label>'
				    +'	<label class="col-xs-6" id="signatory_'+partnerSignatoryId+'_locationId">'+locationId+'</label>'
				    +'	<label class="col-xs-6" id="signatory_'+partnerSignatoryId+'_userDetailId">'+userDetailId+'</label>'
				    +'	<label class="col-xs-6" id="signatory_'+partnerSignatoryId+'_title">'+title+'</label>'
				    +'	<label class="col-xs-6" id="signatory_'+partnerSignatoryId+'_middleName">'+middleName+'</label>'
				    +'	<label class="col-xs-6" id="signatory_'+partnerSignatoryId+'_telephone1">'+telephone1+'</label>'
				    +'	<label class="col-xs-6" id="signatory_'+partnerSignatoryId+'_telephone2">'+telephone2+'</label>'
				    +'	<label class="col-xs-6" id="signatory_'+partnerSignatoryId+'_fax1">'+fax1+'</label>'
				    +'	<label class="col-xs-6" id="signatory_'+partnerSignatoryId+'_fax2">'+fax2+'</label>'
				    +'	<label class="col-xs-6" id="signatory_'+partnerSignatoryId+'_designationId">'+designationId+'</label>'
				    +'	<label class="col-xs-6" id="signatory_'+partnerSignatoryId+'_designationName">'+districtId+'</label>'
				    +'	<label class="col-xs-6" id="signatory_'+partnerSignatoryId+'_registeredAddress">'+registeredAddress+'</label>'
				    +'	<label class="col-xs-6" id="signatory_'+partnerSignatoryId+'_city">'+city+'</label>'
				    +'	<label class="col-xs-6" id="signatory_'+partnerSignatoryId+'_districtId">'+districtId+'</label>'
				    +'	<label class="col-xs-6" id="signatory_'+partnerSignatoryId+'_districtName">'+districtId+'</label>'
				    +'	<label class="col-xs-6" id="signatory_'+partnerSignatoryId+'_countryId">'+countryId+'</label>'
				    +'	<label class="col-xs-6" id="signatory_'+partnerSignatoryId+'_countryName">'+districtId+'</label>'
				    +'	<label class="col-xs-6" id="signatory_'+partnerSignatoryId+'_regionId">'+regionId+'</label>'
				    +'	<label class="col-xs-6" id="signatory_'+partnerSignatoryId+'_regionName">'+districtId+'</label>'
				    +'	<label class="col-xs-6" id="signatory_'+partnerSignatoryId+'_postal">'+postal+'</label>'
				    +'	<label class="col-xs-6" id="signatory_'+partnerSignatoryId+'_email">'+email+'</label>'
				    +'	<label class="col-xs-6" id="signatory_'+partnerSignatoryId+'_validity">'+validity+'</label>'
				    +'	<label class="col-xs-6" id="signatory_'+partnerSignatoryId+'_gender">'+gender+'</label>'
				    +'	<label class="col-xs-6" id="remark_'+partnerSignatoryId+'">'+remark+'</label>'
				    +'	<label class="col-xs-6" id="isApproved_'+partnerSignatoryId+'">'+isApproved+'</label>'
				    +'	<label class="col-xs-6" id="eeComment_'+partnerSignatoryId+'">'+eeComment+'</label>'
				    +'	<label class="col-xs-6" id="ceComment_'+partnerSignatoryId+'">'+ceComment+'</label>'
				    +'	<label class="col-xs-6" id="isEEApproved_'+partnerSignatoryId+'">'+isEEApproved+'</label>'
				    +'	<label class="col-xs-6" id="isCEApproved_'+partnerSignatoryId+'">'+isCEApproved+'</label>'
				    +'	<label class="col-xs-6" id="signatory_'+partnerSignatoryId+'_dob">'+dob+'</label>'
				    +'	<label class="col-xs-6" id="holderType_'+partnerSignatoryId+'">'+holderType+'</label>'
				    +'	<label class="col-xs-6" id="attorneyCertificateId_'+partnerSignatoryId+'">'+attorneyCertificateId+'</label>'
				    +'	<label class="col-xs-6" id="attorneyCertificateName_'+partnerSignatoryId+'">'+attorneyCertificateName+'</label>'
				    +'	<label class="col-xs-6" id="digitallySignTestDocId_'+partnerSignatoryId+'">'+digitallySignTestDocId+'</label>'
				    +'	<label class="col-xs-6" id="digitallySignTestDocName_'+partnerSignatoryId+'">'+digitallySignTestDocName+'</label>'
				    +'	<label class="col-xs-6" id="otherDesignation_'+partnerSignatoryId+'">'+otherDesignation+'</label>'
				    +' </div>'
			        +' </li>';
					i++;
				}
		});
	}
	
	$(".leftPaneData").html(leftPanelHtml);
	loadPartnerOrgSigRightPane(firstRow);
	$('.leftPaneData').paginathing();
	
	$(".tabs-left li a label").text(function(index, currentText) {
	    return currentText.substr(0, 20);
	});
}

function loadPartnerOrgSigRightPane(data){
	   
	editMode=false;
	
	setChildLoadFlag(true);
	/*activeTabName="";*/
	if(!isEmpty(data) && !$.isEmptyObject(data.userDetail)){
		var userDetailId=data.userDetail.userDetailsId==null?'':data.userDetail.userDetailsId;
		var firstName = data.userDetail.firstName==null?'':data.userDetail.firstName;
		var middleName = data.userDetail.middleName==null?'':data.userDetail.middleName;
		var lastName  = data.userDetail.lastName==null?'':data.userDetail.lastName;
		var email 	  = data.userDetail.email==null?'':data.userDetail.email;
		var mobileNo  = data.userDetail.mobileNo==null?'':data.userDetail.mobileNo;
		var telephone1 = data.userDetail.telephone1==null?'':data.userDetail.telephone1;
		var telephone2 = data.userDetail.telephone2==null?'':data.userDetail.telephone2;
		var fax1  = data.userDetail.fax1==null?'':data.userDetail.fax1;
		var fax2 	  = data.userDetail.fax2==null?'':data.userDetail.fax2;
		var designationId  = data.userDetail.designation==null?'':data.userDetail.designation.designationId==null?'':data.userDetail.designation.designationId;
		var dob=data.userDetail.dob==null?'':formatDate(data.userDetail.dob);
		var gender=data.userDetail.gender==null?'':data.userDetail.gender;
		var title=data.userDetail.title==null?'':data.userDetail.title;
		
	}
	if(!$.isEmptyObject(data.location)){
		var locationId=data.location.locationId==null?'':data.location.locationId;
			var registeredAddress = data.location.address1==null?'':data.location.address1;
			var city = data.location.city==null?'':data.location.city;
			var districtId  = data.location.district==null?'':data.location.district.districtId==null?'':data.location.district.districtId;
			var countryId   = data.location.country==null?'':data.location.country.countryId==null?'':data.location.country.countryId;
			var regionId    = data.location.region==null?'':data.location.region.regionId==null?'':data.location.region.regionId;
			var postal      = data.location.postal==null?'':data.location.postal;
	}

	var holderType=data.holderType==null?'':data.holderType;
	var attorneyCertificateId = data.attorneyCertificate == null ? '': data.attorneyCertificate.attachmentId == null ? '': data.attorneyCertificate.attachmentId;
	var attorneyCertificateName = data.attorneyCertificate == null ? '': data.attorneyCertificate.fileName == null ? '': data.attorneyCertificate.fileName;
	var digitallySignTestDocId = data.digitallySignTestDoc == null ? '': data.digitallySignTestDoc.attachmentId == null ? '': data.digitallySignTestDoc.attachmentId;
	var digitallySignTestDocName = data.digitallySignTestDoc == null ? '': data.digitallySignTestDoc.fileName == null ? '': data.digitallySignTestDoc.fileName;
	var otherDesignation=data.otherDesignation==null?'':data.otherDesignation;
	
	var validity=data.validFrom==null?'':formatDate(data.validFrom);
	var partnerSignatoryId=data.partnerSignatoryId==null?'':data.partnerSignatoryId;
	var remark=data.remark==null?'':data.remark;
	var isApproved=data.isApproved==null?'':data.isApproved;
	var eeComment=data.eeComment==null?'':data.eeComment;
	var isEEApproved=data.isEEApproved==null?'':data.isEEApproved;
	var ceComment=data.ceComment==null?'':data.ceComment;
	var isCEApproved=data.isCEApproved==null?'':data.isCEApproved;
		
	$("#partnerSignatoryForm #partnerSignatoryId").val(partnerSignatoryId);
	$("#partnerSignatoryForm #userDetailId").val(userDetailId);
	$("#partnerSignatoryForm #locationId").val(locationId);
	
	$("#partnerSignatoryForm #firstName").val(firstName);
	$("#partnerSignatoryForm #middleName").val(middleName);
	$("#partnerSignatoryForm #lastName").val(lastName);
	$("#partnerSignatoryForm #email").val(email);
	$("#partnerSignatoryForm #mobileNo").val(mobileNo);
	$("#partnerSignatoryForm #telephone1").val(telephone1);
	$("#partnerSignatoryForm #telephone2").val(telephone2);
	$("#partnerSignatoryForm #fax1").val(fax1);
	$("#partnerSignatoryForm #fax2").val(fax2);
	$("#partnerSignatoryForm #designationId").val(designationId);
	$("#partnerSignatoryForm #title").val(title);
	
	$("#partnerSignatoryForm #registeredAddress").val(registeredAddress);
	$("#partnerSignatoryForm #city").val(city);
	$("#partnerSignatoryForm #countryId").val(countryId);
	loadStateByCountry('countryId','partnerSignatoryForm','regionId');
	$("#partnerSignatoryForm #regionId").val(regionId);
	loadDistrictByState('regionId','partnerSignatoryForm','districtId');
	$("#partnerSignatoryForm #districtId").val(districtId);
	$("#partnerSignatoryForm #postal").val(postal);
	
	$("#partnerSignatoryForm #dob").val(dob);
	$("#partnerSignatoryForm #validFrom").val(validity);
	$("#partnerSignatoryForm #holderTypeId").val(holderType);
	$("#partnerSignatoryForm #powerAttorneyCopy").val(attorneyCertificateId);
	$("#partnerSignatoryForm #digitallySignCopy").val(digitallySignTestDocId);
	$("#partnerSignatoryForm .dropDown").removeClass('errorinput');
	$("#partnerSignatoryForm .requiredFile").removeClass('errorinput');
	$("#partnerSignatoryForm #otherDesignation").val(otherDesignation);
	setHolderType();
	if(gender=='male'){
			$("#radio_male").prop('checked', true);
			$("#radio_female").prop('checked', false);
			$("#radio_other").prop('checked', false);
		}
	else if(gender=='female'){
				$("#radio_male").prop('checked', false);
				$("#radio_female").prop('checked', true);
				$("#radio_other").prop('checked', false);
		}
	else{
			$("#radio_male").prop('checked', false);
			$("#radio_female").prop('checked', false);
			$("#radio_other").prop('checked', true);
		}
	
	var url = $("#a_digitallySignCopyCopy").data('url');
	$("#a_digitallySignCopyCopy").attr('href', url);
	var a_digitallySignCopyCopy = $("#partnerSignatoryForm #a_digitallySignCopyCopy").prop('href') + '/'+ digitallySignTestDocId;
	$("#partnerSignatoryForm #a_digitallySignCopyCopy").prop('href', a_digitallySignCopyCopy);
	$("#partnerSignatoryForm #a_digitallySignCopyCopy").html(digitallySignTestDocName);
	
	var url = $("#a_powerAttorneyCopy").data('url');
	$("#a_powerAttorneyCopy").attr('href', url);
	var a_powerAttorneyCopy = $("#partnerSignatoryForm #a_powerAttorneyCopy").prop('href') + '/'+ attorneyCertificateId;
	$("#partnerSignatoryForm #a_powerAttorneyCopy").prop('href', a_powerAttorneyCopy);
	$("#partnerSignatoryForm #a_powerAttorneyCopy").html(attorneyCertificateName);
	
	showFileFieldByHolderType(holderType);
	showSignatoryFileDeleteBtn(digitallySignTestDocId,attorneyCertificateId);

	changeCommentAndStatusByRole('partnerSignatoryForm',isEEApproved,eeComment,isCEApproved,ceComment);
	setPartnerHeaderValues("Company Name: "+companyName, "Company Registration No. : "+registrationNo, "Vendor SAP Code : "+vendorSAPCode,"Status : "+partnerStatus);
	 /*setPartnerHeaderValues("Company Name: "+companyName, "Company Registration No. : "+registrationNo, "Company Type : "+companyType,"Status : "+partnerStatus);*/
}
/*function showDigitalTestCopy(data){
	var attachmentId=data.attachmentId==null?'':data.attachmentId;
	var attachmentName=data.fileName==null?'':data.fileName;
	var url = $("#a_digitalTestCopy").data('url');
	var a_digitalTestCopy = url + '/'+ attachmentId;
	$("#partnerSignatoryForm #a_digitalTestCopy").prop('href', a_digitalTestCopy);
	$("#partnerSignatoryForm #a_digitalTestCopy").html(attachmentName);
}*/
function showFileFieldByHolderType(type){
	if(type=='POA')
	{
	   $("#attorneyCertDiv").show();
	   $("#powerAttorneyFileId").addClass("requiredFile");
	  
	}else{
		$("#attorneyCertDiv").hide();
		$("#powerAttorneyFileId").removeClass("requiredFile");
	
	}
}
function showSignatoryDetail(id){
	   
	editMode=false;
	/*activeTabName="";*/
	$("#partnerSignatoryForm #copyAddress").prop("checked",false);
	$("#partnerSignatoryForm #partnerSignatoryId").val($("#signatory_"+id+"_signatoryId").html());
	$("#partnerSignatoryForm #userDetailId").val($("#signatory_"+id+"_userDetailId").html());
	$("#partnerSignatoryForm #locationId").val($("#signatory_"+id+"_locationId").html())
	
	$("#partnerSignatoryForm #firstName").val($("#signatory_"+id+"_firstName").html());
	$("#partnerSignatoryForm #middleName").val($("#signatory_"+id+"_middleName").html());
	$("#partnerSignatoryForm #lastName").val($("#signatory_"+id+"_lastName").html());
	$("#partnerSignatoryForm #email").val($("#signatory_"+id+"_email").html());
	$("#partnerSignatoryForm #mobileNo").val($("#signatory_"+id+"_mobileNo").html());
	$("#partnerSignatoryForm #telephone1").val($("#signatory_"+id+"_telephone1").html());
	$("#partnerSignatoryForm #telephone2").val($("#signatory_"+id+"_telephone2").html());
	$("#partnerSignatoryForm #fax1").val($("#signatory_"+id+"_fax1").html());
	$("#partnerSignatoryForm #fax2").val($("#signatory_"+id+"_fax2").html());
	$("#partnerSignatoryForm #designationId").val($("#signatory_"+id+"_designationId").html());
	$("#partnerSignatoryForm #title").val($("#signatory_"+id+"_title").html());
	
	$("#partnerSignatoryForm #registeredAddress").val($("#signatory_"+id+"_registeredAddress").html());
	$("#partnerSignatoryForm #city").val($("#signatory_"+id+"_city").html());
	$("#partnerSignatoryForm #countryId").val($("#signatory_"+id+"_countryId").html());
	loadStateByCountry('countryId','partnerSignatoryForm','regionId');
	$("#partnerSignatoryForm #regionId").val($("#signatory_"+id+"_regionId").html());
	loadDistrictByState('regionId','partnerSignatoryForm','districtId');
	$("#partnerSignatoryForm #districtId").val($("#signatory_"+id+"_districtId").html());	
	$("#partnerSignatoryForm #postal").val($("#signatory_"+id+"_postal").html());
	
	$("#partnerSignatoryForm #dob").val($("#signatory_"+id+"_dob").html());
	$("#partnerSignatoryForm #validFrom").val($("#signatory_"+id+"_validity").html());
	$("#partnerSignatoryForm #holderTypeId").val($("#holderType_"+id).html());
	
	$("#partnerSignatoryForm #digitallySignCopy").val($("#digitallySignTestDocId_"+id).html());
	$("#partnerSignatoryForm #a_digitallySignCopyCopy").text($("#digitallySignTestDocName_"+id).html());
	$("#partnerSignatoryForm #powerAttorneyCopy").val($("#attorneyCertificateId_"+id).html());
	$("#partnerSignatoryForm #a_powerAttorneyCopy").text($("#attorneyCertificateName_"+id).html());
	$("#partnerSignatoryForm #otherDesignation").text($("#otherDesignation_"+id).html());
	$("#partnerSignatoryForm .dropDown").removeClass('errorinput');
	$("#partnerSignatoryForm .requiredFile").removeClass('errorinput');
	setHolderType();
	if($("#signatory_"+id+"_gender").html()=='male')
		{
			$("#radio_male").prop('checked', true);
			$("#radio_female").prop('checked', false);
			$("#radio_other").prop('checked', false);
		}
	else if($("#signatory_"+id+"_gender").html()=='female')
		{
				$("#radio_male").prop('checked', false);
				$("#radio_female").prop('checked', true);
				$("#radio_other").prop('checked', false);
		}
	else
	{
		$("#radio_male").prop('checked', false);
		$("#radio_female").prop('checked', false);
		$("#radio_other").prop('checked', true);
	}
	showFileFieldByHolderType($("#holderType_"+id).html());
	showSignatoryFileDeleteBtn($("#digitallySignTestDocId_"+id).html(),$("#attorneyCertificateName_"+id).html());
	changeCommentAndStatusByRole('partnerSignatoryForm',$("#isEEApproved_"+id).html(),$("#eeComment_"+id).html(),$("#isCEApproved_"+id).html(),$("#ceComment_"+id).html())

}
function loadRecentSignatory(data)
{
	   
	$('.pagination').children().remove();
	var currentSignatoryId=$('.leftPaneData').find('li.active').attr('id');
	if(!isEmpty(data)){
		var partnerSignatoryId=data.partnerSignatoryId;
		if(currentSignatoryId==partnerSignatoryId){
			$('#'+currentSignatoryId).remove();
		}else{
			 $('#'+currentSignatoryId).removeClass('active');
			}
		
		editMode=false;
		activeTabName="";
		requiredFileDeleted=false;
		var eeComment=data.eeComment==null?'':data.eeComment;
		var isEEApproved=data.isEEApproved==null?'':data.isEEApproved;
		var ceComment=data.ceComment==null?'':data.ceComment;
		var isCEApproved=data.isCEApproved==null?'':data.isCEApproved;
		var holderType=data.holderType==null?'':data.holderType;
		var otherDesignation=data.otherDesignation==null?'':data.otherDesignation;
		$("#partnerSignatoryForm #partnerSignatoryId").val(partnerSignatoryId);
		var locationId=data.location==null?'':data.location.locationId;
		$("#partnerSignatoryForm #locationId").val(locationId);
		var userDetailsId=data.userDetail==null?'':data.userDetail.userDetailsId;
		$('#partnerSignatoryForm #userDetailId').val(userDetailsId);
		
		$('#signatureHeaderId').empty();
		$('#signatureHeaderId').append(' <div class="col-md-12"><label class="col-xs-6"> <h4>'+$("#partnerSignatoryForm #firstName").val()+'</h4></label>'
	            +'<label class="col-xs-6 ">'+$("#partnerSignatoryForm #lastName").val()+'</label></div>	 '
	            +'<div class="col-md-12"><label class="col-xs-6">'+$("#partnerSignatoryForm #email").val()+'</label>'
	            +'<label class="col-xs-6 ">'+$("#partnerSignatoryForm #mobileNo").val()+'</label></div>');

		var active=' class="active"';
		$('.leftPaneData').prepend( '<li '+active+' onclick="showSignatoryDetail('+partnerSignatoryId+')" id="'+partnerSignatoryId+'">'
		+' <a href="#Master" data-toggle="tab">'
	    +' <div class="col-md-12">'
	    +'  <label class="col-xs-6" id="signatory_'+partnerSignatoryId+'_firstName">'+$("#partnerSignatoryForm #firstName").val()+'</label>'
	    +'	<label class="col-xs-6" id="signatory_'+partnerSignatoryId+'_lastName">'+$("#partnerSignatoryForm #lastName").val()+'</label>'
	    +' </div>'	
	    +' <div class="col-md-12">'
	    +'	<label class="col-xs-6" id="holderTypeName_'+partnerSignatoryId+'">'+holderTypeList[holderType]+'</label>'
	    +' </div>'
	    +' </a>'
	    +' <div class="col-md-12" style="display: none">'
	    +'	<label class="col-xs-6" id="signatory_'+partnerSignatoryId+'_dob">'+$("#partnerSignatoryForm #dob").val()+'</label>'
		+'	<label class="col-xs-6" id="signatory_'+partnerSignatoryId+'_mobileNo">'+$("#partnerSignatoryForm #mobileNo").val()+'</label>'
		+'	<label class="col-xs-6" id="signatory_'+partnerSignatoryId+'_signatoryId">'+$("#partnerSignatoryForm #partnerSignatoryId").val()+'</label>'
	    +'	<label class="col-xs-6" id="signatory_'+partnerSignatoryId+'_locationId">'+$("#partnerSignatoryForm #locationId").val()+'</label>'
	    +'	<label class="col-xs-6" id="signatory_'+partnerSignatoryId+'_userDetailId">'+$("#partnerSignatoryForm #userDetailId").val()+'</label>'
	    +'	<label class="col-xs-6" id="signatory_'+partnerSignatoryId+'_title">'+$("#partnerSignatoryForm #title").val()+'</label>'
	    +'	<label class="col-xs-6" id="signatory_'+partnerSignatoryId+'_middleName">'+$("#partnerSignatoryForm #middleName").val()+'</label>'
	    +'	<label class="col-xs-6" id="signatory_'+partnerSignatoryId+'_telephone1">'+$("#partnerSignatoryForm #telephone1").val()+'</label>'
	    +'	<label class="col-xs-6" id="signatory_'+partnerSignatoryId+'_telephone2">'+$("#partnerSignatoryForm #telephone2").val()+'</label>'
	    +'	<label class="col-xs-6" id="signatory_'+partnerSignatoryId+'_fax1">'+$("#partnerSignatoryForm #fax1").val()+'</label>'
	    +'	<label class="col-xs-6" id="signatory_'+partnerSignatoryId+'_fax2">'+$("#partnerSignatoryForm #fax2").val()+'</label>'
	    +'	<label class="col-xs-6" id="signatory_'+partnerSignatoryId+'_designationId">'+$("#partnerSignatoryForm #designationId").val()+'</label>'
	    +'	<label class="col-xs-6" id="signatory_'+partnerSignatoryId+'_designationName">'+$("#partnerSignatoryForm #designationId").val()+'</label>'
	    +'	<label class="col-xs-6" id="signatory_'+partnerSignatoryId+'_registeredAddress">'+$("#partnerSignatoryForm #registeredAddress").val()+'</label>'
	    +'	<label class="col-xs-6" id="signatory_'+partnerSignatoryId+'_city">'+$("#partnerSignatoryForm #city").val()+'</label>'
	    +'	<label class="col-xs-6" id="signatory_'+partnerSignatoryId+'_districtId">'+$("#partnerSignatoryForm #districtId").val()+'</label>'
	    +'	<label class="col-xs-6" id="signatory_'+partnerSignatoryId+'_districtName">'+$("#partnerSignatoryForm #districtId").val()+'</label>'
	    +'	<label class="col-xs-6" id="signatory_'+partnerSignatoryId+'_countryId">'+$("#partnerSignatoryForm #countryId").val()+'</label>'
	    +'	<label class="col-xs-6" id="signatory_'+partnerSignatoryId+'_countryName">'+$("#partnerSignatoryForm #countryId").val()+'</label>'
	    +'	<label class="col-xs-6" id="signatory_'+partnerSignatoryId+'_regionId">'+$("#partnerSignatoryForm #regionId").val()+'</label>'
	    +'	<label class="col-xs-6" id="signatory_'+partnerSignatoryId+'_postal">'+$("#partnerSignatoryForm #postal").val()+'</label>'
	    +'	<label class="col-xs-6" id="signatory_'+partnerSignatoryId+'_email">'+$("#partnerSignatoryForm #email").val()+'</label>'
	    +'	<label class="col-xs-6" id="signatory_'+partnerSignatoryId+'_validity">'+$("#partnerSignatoryForm #validFrom").val()+'</label>'
	    +'	<label class="col-xs-6" id="signatory_'+partnerSignatoryId+'_gender">'+$('#partnerSignatoryForm  .genderType:checked').val()+'</label>'
	    +'	<label class="col-xs-6" id="holderType_'+partnerSignatoryId+'">'+$("#partnerSignatoryForm #holderTypeId").val()+'</label>'
	    +'	<label class="col-xs-6" id="digitallySignTestDocId_'+partnerSignatoryId+'">'+$("#partnerSignatoryForm #digitallySignCopy").val()+'</label>'
	    +'	<label class="col-xs-6" id="attorneyCertificateId_'+partnerSignatoryId+'">'+$("#partnerSignatoryForm #powerAttorneyCopy").val()+'</label>'
	    +'	<label class="col-xs-6" id="digitallySignTestDocName_'+partnerSignatoryId+'">'+$("#partnerSignatoryForm #a_digitallySignCopyCopy").text()+'</label>'
	    +'	<label class="col-xs-6" id="attorneyCertificateName_'+partnerSignatoryId+'">'+$("#partnerSignatoryForm #a_powerAttorneyCopy").text()+'</label>'
	    +'	<label class="col-xs-6" id="eeComment_'+partnerSignatoryId+'">'+eeComment+'</label>'
	    +'	<label class="col-xs-6" id="ceComment_'+partnerSignatoryId+'">'+ceComment+'</label>'
	    +'	<label class="col-xs-6" id="isEEApproved_'+partnerSignatoryId+'">'+isEEApproved+'</label>'
	    +'	<label class="col-xs-6" id="isCEApproved_'+partnerSignatoryId+'">'+isCEApproved+'</label>'
	    +'	<label class="col-xs-6" id="otherDesignation_'+partnerSignatoryId+'">'+otherDesignation+'</label>'
	    +' </div>'
	    +' </li>');
		
	}
	
	$('.leftPaneData').paginathing();
	
	$(".tabs-left li a label").text(function(index, currentText) {
	    return currentText.substr(0, 20);
	});
}
function deleteSignatory(){
	$('.pagination').children().remove();
	input_box = confirm("Do you really want to delete this Signatory?");
	 if (input_box == true) {

	 submitWithParam('deletePartnerSignatory','partnerSignatoryId','deleteSignatoryResponse');
			
	 }
	 $('.leftPaneData').paginathing();
	 
	 $(".tabs-left li a label").text(function(index, currentText) {
		    return currentText.substr(0, 20);
		});
	}
function deleteSignatoryResponse(data){
	$('.pagination').children().remove();
	var currentSignatoryId=$('.leftPaneData').find('li.active').attr('id');
	if(!isEmpty(data)){
		if(data.hasError==false){
			$('#'+currentSignatoryId).remove();
			$('#partnerSignatoryForm')[0].reset();
			$("#partnerSignatoryForm #a_digitallySignCopyCopy").text("");
			$("#partnerSignatoryForm #a_powerAttorneyCopy").text("");
			$('#partnerSignatoryForm #powerAttorneyFileId').val('');
			$('#partnerSignatoryForm #powerAttorneyCopy').val('');
			$('#partnerSignatoryForm #digitallySignFileId').val('');
			$('#partnerSignatoryForm #digitallySignCopy').val('');
			$("#partnerSignatoryForm #locationId").val("");
			$("#partnerSignatoryForm #userDetailId").val("");
			$("#partnerSignatoryForm #partnerSignatoryId").val("");
			Alert.info(data.message);

		}else{
		  Alert.warn(data.responseMsg);
		}
	}
	$('.leftPaneData').paginathing();
	
	$(".tabs-left li a label").text(function(index, currentText) {
	    return currentText.substr(0, 20);
	});
}

function CopyAllAddres(data){
	if(!isEmpty(data) && !isEmpty(data.objectMap)){
		if(data.objectMap.hasOwnProperty('location')){
			copySigAddress(data.objectMap.location);
		}else{
			Alert.warn("Please fill contact address to do copy address");
		}
	}
}

function loadSigCountry(data){
		$("#partnerSignatoryForm #countryId").html("");
		var options = "<option value=''>Select Country</option>";
		$.each(data,function(key,value){
			if(!isEmpty(value)){
				options +='<option value="'+value.countryId+'">'+value.name +'</option>';
			}
		});
		$("#partnerSignatoryForm #countryId").append(options);
}
function loadSigRegion(data){
		$("#partnerSignatoryForm #regionId").html("");
		var options = "<option value=''>Select State</option>";
		$.each(data,function(key,value){
			if(!isEmpty(value)){
			options +='<option value="'+value.regionId+'">'+value.name +'</option>';
			}
		});
		$("#partnerSignatoryForm #regionId").append(options);
}
function loadSigDistrict(data){
	$("#partnerSignatoryForm #districtId").html("");
		var options = "<option value=''>Select District</option>";
		$.each(data,function(key,value){
			if(!isEmpty(value)){
			options +='<option value="'+value.districtId+'">'+value.name +'</option>';
			}
		});
		$("#partnerSignatoryForm #districtId").append(options);
}
function loadSigDesignation(data){
		$("#partnerSignatoryForm #designationId").html("");
		var options = "<option value=''>Select Designation</option>";
		$.each(data,function(key,value){
			if(!isEmpty(value)){
			options +='<option value="'+value.designationId+'" data-code="'+value.code+'" >'+value.name+'</option>';
			}
		});
		$("#partnerSignatoryForm #designationId").append(options);
}

/*Function to copy address data*/
function copySigAddress(data){
	   
	$("#partnerSignatoryForm .dropDown").removeClass('errorinput');
	if(!$.isEmptyObject(data)){
			var registeredAddress = data.location==null?'':data.location.address1;
			var city = data.location==null?'':data.location.city;
			var districtId  = data.location==null?'':data.location.district==null?'':data.location.district.districtId;
			var countryId  = data.location==null?'':data.location.country==null?'':data.location.country.countryId;
			var regionId  = data.location==null?'':data.location.region==null?'':data.location.region.regionId;
			var postal = data.location.postal==null?'':data.location.postal;
		
		$('#partnerSignatoryForm #registeredAddress').val(registeredAddress);
		$('#partnerSignatoryForm #city').val(city);
		$('#partnerSignatoryForm #countryId').val(countryId);
		loadStateByCountry('countryId','partnerSignatoryForm','regionId');
		$('#partnerSignatoryForm #regionId').val(regionId);
		loadDistrictByState('regionId','partnerSignatoryForm','districtId');
		$('#partnerSignatoryForm #districtId').val(districtId);
		$('#partnerSignatoryForm #postal').val(postal);
		}
		else{
			$('#partnerSignatoryForm #registeredAddress').val("");
			$('#partnerSignatoryForm #city').val("");
			$('#partnerSignatoryForm #districtId').val("");
			$('#partnerSignatoryForm #countryId').val("");
			$('#partnerSignatoryForm #regionId').val("");
			$('#partnerSignatoryForm #postal').val("");
		}
	
}
function digitallySignCopyDeleteResp(data) {
	if(!isEmpty(data)){
		if (!data.hasError) {
			$('#digitallySignFileId').val('');
			$('#digitallySignCopy').val('');
			$("#a_digitallySignCopyCopy").html('');
			$('.digitallySignCopy').attr('disabled', true);
			Alert.info(data.message);
			var partnerSignatoryId=$("#partnerSignatoryId").val();
			$("#digitallySignTestDocId_"+partnerSignatoryId).html("");
			$("#digitallySignTestDocName_"+partnerSignatoryId).html("");
	     } else {
			Alert.warn(data.message);
		}
	} 
}
function powerAttorneyCopyDeleteResp(data) {
	  if(!isEmpty(data)){
		  if (!data.hasError) {
				$('#powerAttorneyFileId').val('');
				$('#powerAttorneyCopy').val('');
				$("#a_powerAttorneyCopy").html('');
				$('.powerAttorneyCopy').attr('disabled', true);
				Alert.info(data.message);
				var partnerSignatoryId=$("#partnerSignatoryId").val();
				$("#attorneyCertificateId_"+partnerSignatoryId).html("");
				$("#attorneyCertificateName_"+partnerSignatoryId).html("");

			} else {
				Alert.warn(data.message);
			}
	  } 
	

}
function showSignatoryFileDeleteBtn(signatoryFileId,attorneyFileId)
{
	if (signatoryFileId != '') {
		$(".digitallySignCopy").attr('disabled', false);
	} else {
		$(".digitallySignCopy").attr('disabled', true);
	}
	if (attorneyFileId != '' ) {
		$(".powerAttorneyCopy").attr('disabled', false);
	} else {
		$(".powerAttorneyCopy").attr('disabled', true);
	}

}
function setHolderType()
{
	   
	 $("#otherDesignationDivId").css('display','none');
	 $("#otherDesignation").removeClass('requiredField');
	var code=$("#partnerSignatoryForm #designationId option:selected").data('code');
	if(code=='MD')
		{
		  /*$("#holderTypeId").val('');*/
		  $("#holderTypeId").removeClass("readonly");
		  /*$("#attorneyCertDiv").hide();
		  $("#powerAttorneyFileId").removeClass("requiredFile");
		  $("#powerAttorneyCopy").attr('disabled','disabled');*/
		}else{
			$("#holderTypeId").val('POA');
			$("#holderTypeId").removeClass('errorinput');
			$("#holderTypeId").addClass("readonly");
			$("#attorneyCertDiv").show();
			$("#powerAttorneyFileId").addClass("requiredFile");
		    $("#powerAttorneyCopy").removeAttr('disabled','disabled');
		    if(code=='OTHER')
		    	{
		    	   $("#otherDesignationDivId").css('display','block');
		    	   $("#otherDesignation").addClass('requiredField');
		    	}
		}
	 
}
function showFileField()
{
	   
	if($("#holderTypeId").val()=='POA')
		{
		  $("#attorneyCertDiv").show();
		  $("#powerAttorneyFileId").addClass("requiredFile");
		  $("#powerAttorneyCopy").removeAttr('disabled','disabled');
		}else{
			$("#attorneyCertDiv").hide();
			$("#powerAttorneyFileId").removeClass("requiredFile");
			$("#powerAttorneyCopy").attr('disabled','disabled');
			var codeValue=$("#partnerSignatoryForm #designationId").find('[data-code="MD"]').val();
			$("#partnerSignatoryForm #designationId").val(codeValue);
		}
}
function downloadTestFile(event,el){
	   
	event.preventDefault();
	/*fetchList("downloadTestFile",null);*/
	directSubmit(event,"downloadForm","downloadTestFile");
	
}
