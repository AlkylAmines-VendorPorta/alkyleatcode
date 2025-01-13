var partnerOrgArray=new Array();
$(document).ready(function() {
	$(".partnerOrgEEApproveDiv").hide();
	$(".partnerOrgCEApproveDiv").hide();
	
	var FactoryName;
	var establishedDate;
	var licenseNo;
	var locationName;

	$('#addFactoryBtnId').click(function(event) {
		event.preventDefault();
		$('#partnerOrgForm')[0].reset();
		$('#partnerOrgForm #partnerOrgId').val("");
		$('#partnerOrgForm #partnerOrgLocationId').val("");
		$("#partnerOrgForm #licenceCopy").val("");
		$('#partnerOrgForm #inspectionCopy').val('');
		$("#partnerOrgForm #machinaryListCopy").val("");
		$("#partnerOrgForm #staffListCopy").val("");
		$('#partnerOrgForm .authorizationCertificate').attr('disabled', true);
		$('#partnerOrgForm .inspectionCopy').attr('disabled', true);
		$("#partnerOrgForm .staffListCopy").attr('disabled', true);
		$('#partnerOrgForm #authorizationCertificateId').val('');
		$('#partnerOrgForm #authorizationCertificate').val('');
		$("#partnerOrgForm #a_authorizationCertificate").html('');
		$("#partnerOrgForm #a_inspectionCopy").html('');
		$("#partnerOrgForm #a_licenceCopy").html('');
		$("#partnerOrgForm #a_machinaryCopy").html('');
		$("#partnerOrgForm #a_staffCopy").html('');
		$("#partnerOrgForm .licenceCopy").attr('disabled', true);
		$("#partnerOrgForm .machinaryListCopy").attr('disabled', true);
		$("#partnerOrgForm .partnerOrgEEApproveDiv").hide();
		$("#partnerOrgForm .partnerOrgCEApproveDiv").hide();
		$('.partnerOrgTabs').removeClass("readonly");
		$('.partnerOrgActionBtn').removeClass("readonly");
		$("#a_testingEquipmentDetails").html("");
		$("#testingEquipmentDetails").val('');
		$(".testingEquipmentDetails").attr('disabled', true);

	});
	$('#editFactoryBtnId').click(function(event) {
		event.preventDefault();
		$('.partnerOrgTabs').removeClass("readonly");
		$('.partnerOrgActionBtn').removeClass("readonly");
		
		
	});
	

	$('#cancelFactoryDetailsBtnId').click(function(event) {
		event.preventDefault();
		editMode=false;
	   /*	activeTabName="";*/
		var activeFactoryId = $('.leftPaneData').find('li.active').attr('id');
		if (activeFactoryId != undefined) {
			showOrgDetail(activeFactoryId);
		} else{
			$('#partnerOrgForm')[0].reset();
			$("#licenceCopy").val("");
			$('#inspectionCopy').val('');
			$("#machinaryListCopy").val("");
			$("#staffListCopy").val("");
			$("#partnerOrgForm #a_inspectionCopy").html('');
			$("#partnerOrgForm #a_licenceCopy").html('');
			$("#partnerOrgForm #a_machinaryCopy").html('');
			$("#partnerOrgForm #a_staffCopy").html('');
			$('#authorizationCertificateId').val('');
			$('#authorizationCertificate').val('');
			$("#a_authorizationCertificate").html('');
			$('.authorizationCertificate').attr('disabled', true);
			$("#partnerOrgForm #a_testingEquipmentDetails").html('');
			$("#partnerOrgForm #testingEquipmentDetails").val('');
			$(".testingEquipmentDetails").attr('disabled', true);
		}
	});
	$('#manPower').on('keyup',function(event) {
		event.preventDefault();
		var manpower=$(this).val();
		if(manpower<10)
		{
		    $("#factoryLicenseCopyLabel").html("<label>Attach Undertaking Certificate Copy<span class='red'>*</span></label>");
		    $("#partnerOrgForm #factoryLicenseDivId").hide();
			$("#partnerOrgForm #factoryLicenseDateDivId").hide();
		    $("#partnerOrgForm #licenceValidityDate").attr("disabled","disabled");
			$("#partnerOrgForm #licenceNo").attr("disabled","disabled");
			$("#partnerOrgForm #a_licenceCopy").html('');
			$(".licenceCopy").attr('disabled', true);
			$("#licenceCopy").val("");
			$('#licenceFileId').val('');
		}else{
			$("#factoryLicenseCopyLabel").html("<label>Attach Factory License Copy<span class='red'>*</span></label>");
			$("#partnerOrgForm #factoryLicenseDivId").show();
			$("#partnerOrgForm #factoryLicenseDateDivId").show();
			$("#partnerOrgForm #licenceValidityDate").removeAttr("disabled","disabled");
		    $("#partnerOrgForm #licenceNo").removeAttr("disabled","disabled");
		    $("#partnerOrgForm #a_licenceCopy").html('');
		    $(".licenceCopy").attr('disabled', true);
		    $("#licenceCopy").val("");
		    $('#licenceFileId').val('');
		}
		
    });
	var sd=$("#estdDate").val();
    $(".setDates").datepicker("remove");
	$(".setDates").datepicker({
		startDate: sd,
		endDate:date
	});
	
	$("#partnerOrgForm").find("input,select,textarea").change(function() {
		 debugger;
	   	 editMode=true;
	   	 activeTabName="Factory Details";
	});
	$("#partnerOrgForm .fileDeleteBtn").click(function() {
   	 editMode=true;
   	 activeTabName="Factory Details";
     requiredFileDeleted=true;
    });

});
function changeDates(){
	/*var sd=$("#estdDate").val();
    $(".setDates").datepicker("remove");
	$(".setDates").datepicker({
		startDate: sd,
		endDate:date
	});*/
}
function getPartnerFactoryDetails(event,el)
{
	event.preventDefault();
	var ele=$("#factoryDetailsTab")[0];
	getFactoryDetails(event,ele);
}
function getFactoryDetails(event,el)
{
	event.preventDefault();	
	if(!editMode && !requiredFileDeleted){
		$('#pagination-here').empty();
		cacheLi();
		setCurrentTab(el);
	    if(getChangedFlag()){
	      submitWithParam('getOrgDetails','bPartnerId','onPartnerOrgTabLoad');	
	      setChangedFlag(false);
		}else{
			getCacheLi();
		}
	    setActiveTabName("Factory Details", $('.leftPaneData li').length); 
		if($('.leftPaneData li').length==0){
			setPartnerHeaderValues("Company Name: "+companyName, "Company Registration No. : "+registrationNo, "Company Type : "+companyType,"Status : "+partnerStatus);
		}else{
			setHeaderValues("Factory Name: "+FactoryName, "Established Date : "+establishedDate, "License No.: "+licenseNo, "Location : "+locationName);
		}
		}else{
			event.stopPropagation();
	        Alert.warn("Please Save Changed Data Of "+activeTabName+" Tab");
		}
	$("#filterBtnId").addClass("readonly");
    checkForFilterByRole();
}
function showSubmitFormOnOrgChanges()
{
	var partnerData =  $("#partnerData").val();
	if(partnerData=='partnerRegistration')
	{
		$("#partnerDetailsApprovalForm #partnerSubmitDivId").show();
		$("#partnerDetailsApprovalForm #partnerSubmitRespDivId").hide();
		$("#partnerDetailsApprovalForm #partnerApprovalDivId").hide();	
	}/*else if(partnerData=='partnerProfiles'){
		$("#partnerDetailsApprovalForm #partnerSubmitDivId").hide();
		$("#partnerDetailsApprovalForm #partnerSubmitRespDivId").hide();
		$("#partnerDetailsApprovalForm #partnerApprovalDivId").show();	
	}

*/}
function partnerOrgResp(data) {
	debugger;
	
	setChildLoadFlag(true);	
	if ($("#partnerData").val() == "partnerRegistration") {
		$("#partnerOrgForm  .partnerOrgEEApproveDiv").hide();
		$("#partnerOrgForm  .partnerOrgCEApproveDiv").hide();
	}
	$('.pagination').children().remove();
	if(!data.response.hasError)
	{
		showSubmitFormOnOrgChanges();
		
	editMode=false;
	activeTabName="";
	requiredFileDeleted=false;
	var estdDate = data.estdDate == null ? '' : data.estdDate;
	var currentPartnerOrgId = $('ul.leftPaneData').find('li.active').attr('id');
	var leftPanelHtml = "";
	var status = true;
	var partnerOrgId = data.partnerOrgId;

	$('#partnerOrgForm #partnerOrgId').val(partnerOrgId);
	$('#partnerOrgForm #partnerOrgLocationId').val(data.location.locationId);
	$(".partnerOrgId").val(partnerOrgId);
	if (currentPartnerOrgId == partnerOrgId) {
		$('#' + currentPartnerOrgId).remove();
	}else {
		$('#' + currentPartnerOrgId).removeClass('active');
	}
	leftPanelHtml = appendPartnerOrgDetail(data, status);
	$(".leftPaneData").prepend(leftPanelHtml);
	if(data.licenceCopy!=null)
	{
	   data.licenceCopy.fileName=$("#partnerOrgForm #a_licenceCopy").html();
	}
	if(data.machinaryListCopy!=null)
	{
	  data.machinaryListCopy.fileName=$("#partnerOrgForm #a_machinaryCopy").html();
	}
	if(data.inspectionReportCopy!=null)
	{
	 data.inspectionReportCopy.fileName=$("#partnerOrgForm #a_inspectionCopy").html();
	}
	if(data.staffListCopy!=null)
	{
	 data.staffListCopy.fileName=$("#partnerOrgForm #a_staffCopy").html();
	}
	if(data.authorizationCertificate!=null)
	{
	 data.authorizationCertificate.fileName=$("#partnerOrgForm #a_authorizationCertificate").html();
	}
	if(data.testingEquipmentDetails!=null)
	{
	 data.testingEquipmentDetails.fileName=$("#partnerOrgForm #a_testingEquipmentDetails").html();
	}
	partnerOrgArray["org"+partnerOrgId]=data;

	$("#labellicenceCopyName-" + partnerOrgId).html(
			$("#partnerOrgForm #a_licenceCopy").html());
	$("#labelmachinaryListCopyName-" + partnerOrgId).html(
			$("#partnerOrgForm #a_machinaryCopy").html());
	$("#labelInspectionReportName-" + partnerOrgId).html(
			$("#partnerOrgForm #a_inspectionCopy").html());
	$("#labelStaffReportName-" + partnerOrgId).html(
			$("#partnerOrgForm #a_staffCopy").html());
	
	setLeftPaneDropDownOnOrgResp(partnerOrgId);
	/* $('#factoryFormDivId').addClass('readonly'); */
	status = false;
	Alert.info(data.response.message);
	FactoryName= data.name == null ? '' : data.name;
	establishedDate = data.estdDate == null ? '' : formatDate(data.estdDate);
	licenseNo= data.licenceNo == null ? '' : data.licenceNo;
	locationName=data.location.city == null ? '' : data.location.city;//$("#partnerOrgForm #city")
    setHeaderValues("Factory Name: "+FactoryName, "Established Date : "+establishedDate, "License No.: "+licenseNo, "Location : "+locationName);
	
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
		
		setPartnerHeaderValues("Company Name: "+companyName, "Company Registration No. : "+registrationNo, "Company Type : "+companyType,"Status : "+partnerStatus);
	}
	
	$('.leftPaneData').paginathing();
	
	$(".tabs-left li a label").text(function(index, currentText) {
	    return currentText.substr(0, 20);
	});
}

function partnerOrgDelResp(data) {
	$('.pagination').children().remove();
	if(!data.hasError)
	{
	Alert.info(data.message);
	showSubmitFormOnOrgChanges();
	var currentPartnerOrgId = $('ul.leftPaneData').find('li.active').attr('id');
	$('#' + currentPartnerOrgId).remove();
	$('#partnerOrgForm')[0].reset();
	$('#partnerOrgForm #partnerOrgId').val("");
	$('#partnerOrgForm #partnerOrgLocationId').val("");
	$('.partnerOrgId').val("");
	$("#licenceCopy").val("");
	$('#inspectionCopy').val('');
	$("#machinaryListCopy").val("");
	$("#staffListCopy").val("");
	$("#partnerOrgForm #a_licenceCopy").html('');
	$("#partnerOrgForm #a_machinaryCopy").html('');
	$("#partnerOrgForm #a_inspectionCopy").html('');
	$("#partnerOrgForm #a_staffCopy").html('');
	$('#authorizationCertificateId').val('');
	$('#authorizationCertificate').val('');
	$("#a_authorizationCertificate").html('');
	$("#partnerOrgForm #a_testingEquipmentDetails").html('');
	$("#partnerOrgForm #testingEquipmentDetails").val('');
	$(".licenceCopy").attr('disabled', true);
	$(".machinaryListCopy").attr('disabled', true);
	$("#partnerOrgForm .testingEquipmentDetails").attr('disabled', true);
	$('#partnerOrgForm .authorizationCertificate').attr('disabled', true);
	$('#partnerOrgForm .inspectionCopy').attr('disabled', true);
	$("#partnerOrgForm .staffListCopy").attr('disabled', true);
	setActiveTabName("Factory Details", $('.leftPaneData li').length);
	event.preventDefault();
	if ($('.leftPaneData li').length == 0) {
		$('.disableFactoryTabs').hide();
	}
	}else{
		Alert.warn(data.message);
	}
	$('.leftPaneData').paginathing();
	
	 $(".tabs-left li a label").text(function(index, currentText) {
		    return currentText.substr(0, 20);
		});
}
function setLeftPaneDropDownOnOrgResp(id) {
	var country = $("#partnerOrgForm #country").val();
	var region = $("#partnerOrgForm #region").val();
	var district = $("#partnerOrgForm #district").val();
	$("#labelCountry-" + id).html(country);
	$("#labelRegion-" + id).html(region);
	$("#labelDistrict-" + id).html(district);
}

function onPartnerOrgTabClick() {
	submitToURL("getPartner", 'onPartnerOrgTabLoad');
}

function onPartnerOrgTabLoad(data) {
debugger;
	if (data.objectMap.hasOwnProperty('countries')) {
		loadOrgCountry(data.objectMap.countries);
	}
	if (data.objectMap.hasOwnProperty('regions')) {
		loadOrgRegion(data.objectMap.regions);
	}
	if (data.objectMap.hasOwnProperty('districts')) {
		loadOrgDistrict(data.objectMap.districts);
	}
	if (data.objectMap.hasOwnProperty('paymentDetail')) {
		loadPaymentDetail(data.objectMap.paymentDetail);
	}
	if (data.objectMap.hasOwnProperty('orgs')) {
		if (!$.isEmptyObject(data.objectMap.orgs)) {
			loadPartnerOrgLeftPane(data.objectMap.orgs);
			$('.disableFactoryTabs').show();
			$("#tabstrip3").kendoTabStrip();
		}
		else {
			$('.disableFactoryTabs').hide();
			$(".leftPaneData").html("");
			$('#partnerOrgForm')[0].reset();
			$('#partnerOrgId').val("");
			$('#partnerOrgLocationId').val("");
			$("#licenceCopy").val("");
			$('#inspectionCopy').val('');
			$("#machinaryListCopy").val("");
			$("#staffListCopy").val("");
			$("#partnerOrgForm #a_inspectionCopy").html('');
			$("#partnerOrgForm #a_licenceCopy").html('');
			$("#partnerOrgForm #a_machinaryCopy").html('');
			$("#partnerOrgForm #a_staffCopy").html('');
			$('#authorizationCertificateId').val('');
			$('#authorizationCertificate').val('');
			$("#a_authorizationCertificate").html('');
			$("#partnerOrgForm #a_testingEquipmentDetails").html('');
			$("#partnerOrgForm #testingEquipmentDetails").val('');
			$("#partnerOrgForm .testingEquipmentDetails").attr('disabled', true);
		}
		
	}
	setActiveTabName("Factory Details", $('.leftPaneData li').length);
}

function appendPartnerOrgDetail(value, active) {
	debugger;
	var leftPanelHtml = '';
	var partnerOrgId = value.partnerOrgId == null ? '' : value.partnerOrgId;
	if (active) {
		leftPanelHtml = leftPanelHtml
				+ ' <li class="active" onclick="showOrgDetail(' + partnerOrgId
				+ ')" id="' + partnerOrgId + '">';
	} else {
		leftPanelHtml = leftPanelHtml + ' <li onclick="showOrgDetail('
				+ partnerOrgId + ')" id="' + partnerOrgId + '">';
	}
	var factoryName = value.name == null ? '' : value.name;
	var estdDate = value.estdDate == null ? '' :formatDate(value.estdDate);
	var address1 = value.location.address1 == null ? ''
			: value.location.address1;
	var inspectionReportNo = value.inspectionReportNo == null ? ''
			: value.inspectionReportNo;
	var postal = value.location.postal == null ? '' : value.location.postal;
	var country = value.location.country == null ? ''
			: value.location.country.countryId == null ? ''
					: value.location.country.countryId;
	var region = value.location.region == null ? ''
			: value.location.region.regionId == null ? ''
					: value.location.region.regionId;
	var city = value.location.city == null ? '' : value.location.city;
	var district = value.location.district == null ? ''
			: value.location.district.districtId == null ? ''
					: value.location.district.districtId;
	var partnerOrgLocationId = value.location.locationId == null ? ''
			: value.location.locationId;
	var licenceNo = value.licenceNo == null ? '' : value.licenceNo;
	var licenceValidityDate = value.licenceValidityDate == null ? ''
			: formatDate(value.licenceValidityDate);
	var licenseType = value.licenseType == null ? '' : value.licenseType;
	var licenceCopyId = value.licenceCopy == null ? ''
			: value.licenceCopy.attachmentId == null ? ''
					: value.licenceCopy.attachmentId;
	var licenceCopyName = value.licenceCopy == null ? ''
			: value.licenceCopy.fileName == null ? ''
					: value.licenceCopy.fileName;
	var machinaryListCopyId = value.machinaryListCopy == null ? ''
			: value.machinaryListCopy.attachmentId == null ? ''
					: value.machinaryListCopy.attachmentId;
	var machinaryListCopyName = value.machinaryListCopy == null ? ''
			: value.machinaryListCopy.fileName == null ? ''
					: value.machinaryListCopy.fileName;
	var inspectionCopyId = value.inspectionReportCopy == null ? '': value.inspectionReportCopy.attachmentId == null ? '': value.inspectionReportCopy.attachmentId;
	var inspectionCopyName = value.inspectionReportCopy == null ? '': value.inspectionReportCopy.fileName == null ? '': value.inspectionReportCopy.fileName;
	var inspectionDate = value.inspectionDate == null ? '' :formatDate(value.inspectionDate);
	
	var manpower = value.manPower == null ? '' : value.manPower;
	var remark = value.remark == null ? '' : value.remark;
	var isApproved = value.isApproved == null ? '' : value.isApproved;
	var eeComment=value.eeComment==null?'':value.eeComment;
	var isEEApproved=value.isEEApproved==null?'':value.isEEApproved;
	var ceComment=value.ceComment==null?'':value.ceComment;
	var isCEApproved=value.isCEApproved==null?'':value.isCEApproved;
	var staffListId = value.staffListCopy == null ? '': value.staffListCopy.attachmentId == null ? '': value.staffListCopy.attachmentId;
	var staffListCopyName = value.staffListCopy == null ? '': value.staffListCopy.fileName == null ? '': value.staffListCopy.fileName;
	
	var factoryInspected= value.isFactoryInspected == null ? '': value.isFactoryInspected;
	var paymentId=value.paymentDetail==null?'':value.paymentDetail.paymentDetailId==null?'':value.paymentDetail.paymentDetailId;
	
	
	leftPanelHtml = leftPanelHtml + ' <a href="#Master" data-toggle="tab">'
			+ ' <div class="col-md-12">'
			+ '  <label class="col-xs-6" id="labelFactoryName-' + partnerOrgId
			+ '">'+factoryName+'</label>'
		    + ' </div>'
			+ ' <div class="col-md-12" style="display: none">'
			+ '	<label class="col-xs-6" id="labelAddress-' + partnerOrgId
			+ '">'+address1+'</label>'
			+ '	<label class="col-xs-6" id="labelLicenseNo-' + partnerOrgId
			+ '">'+licenceNo+'</label>'
			+ '	<label class="col-xs-6" id="labelEstdDate-' + partnerOrgId
			+ '">'+estdDate+ '</label>' 
			+ '	<label class="col-xs-6" id="labelPartnerOrgId-' + partnerOrgId
			+ '">'+partnerOrgId+'</label>'
			+ '	<label class="col-xs-6" id="labelpartnerOrgLocationId-'
			+ partnerOrgId+ '">'+partnerOrgLocationId+'</label>'
			+ '	<label class="col-xs-6" id="labelInspReportNo-' + partnerOrgId
			+ '">'+inspectionReportNo+'</label>'
			+ '	<label class="col-xs-6" id="labelAddress-' + partnerOrgId
			+ '">'+address1+'</label>'
			+ '	<label class="col-xs-6" id="labelPostal-' + partnerOrgId + '">'+postal+'</label>'
			+ '	<label class="col-xs-6" id="labelCountry-' + partnerOrgId
			+ '">'+country+'</label>'
			+ '	<label class="col-xs-6" id="labelRegion-' + partnerOrgId + '">'+region+'</label>'
			+ '	<label class="col-xs-6" id="labelManPower-' + partnerOrgId
			+ '">'+manpower+'</label>'
			+ '	<label class="col-xs-6" id="labelDistrict-' + partnerOrgId
			+ '">'+district+'</label>'
			+ '	<label class="col-xs-6" id="labelCity-' + partnerOrgId + '">'+city+'</label>'
			+ '	<label class="col-xs-6" id="labelLicenseValidity-'
			+ partnerOrgId + '">' + licenceValidityDate + '</label>'
			+ '	<label class="col-xs-6" id="labellicenseType-' + partnerOrgId
			+ '">'+licenseType+'</label>'
			+ '	<label class="col-xs-6" id="labelllicenceCopyId-'
			+ partnerOrgId + '">'+licenceCopyId+'</label>'
			+ '	<label class="col-xs-6" id="labellicenceCopyName-'
			+ partnerOrgId + '">'+licenceCopyName+'</label>'
			+ '	<label class="col-xs-6" id="labelmachinaryListCopyId-' + partnerOrgId + '">'+machinaryListCopyId+'</label>'
			+ '	<label class="col-xs-6" id="labelmachinaryListCopyName-' + partnerOrgId + '">'+machinaryListCopyName+'</label>'
			+ '	<label class="col-xs-6" id="labelStaffReportId-' + partnerOrgId + '">'+staffListId+'</label>'
			+ '	<label class="col-xs-6" id="labelStaffReportName-' + partnerOrgId + '">'+staffListCopyName+'</label>'
			+ '	<label class="col-xs-6" id="remark-' + partnerOrgId + '">'+remark+'</label>' 
			+ '	<label class="col-xs-6" id="isApproved-' + partnerOrgId + '">'+isApproved+'</label>' 
			+ '	<label class="col-xs-6" id="labelInspectionReportId-' + partnerOrgId + '">'+inspectionCopyId+'</label>'
			+ '	<label class="col-xs-6" id="labelInspectionReportName-' + partnerOrgId + '">'+inspectionCopyName+'</label>'			
			+ '	<label class="col-xs-6" id="labelinspectionDate-' + partnerOrgId + '">'+inspectionDate+'</label>'			
			+'	<label class="col-xs-6" id="eeComment-'+partnerOrgId+'">'+eeComment+'</label>'
			+'	<label class="col-xs-6" id="ceComment-'+partnerOrgId+'">'+ceComment+'</label>'
			+'	<label class="col-xs-6" id="isEEApproved-'+partnerOrgId+'">'+isEEApproved+'</label>'
			+'	<label class="col-xs-6" id="isCEApproved-'+partnerOrgId+'">'+isCEApproved+'</label>'
			+'	<label class="col-xs-6" id="isFactoryinspectionDone-'+partnerOrgId+'">'+factoryInspected+'</label>'
			+'	<label class="col-xs-6" id="labelPaymentId-'+partnerOrgId+'">'+paymentId+'</label>'
			+' </div>'
			+ ' </a>' + ' </li>';

	$('.disableFactoryTabs').show();
	$("#tabstrip3").kendoTabStrip();
	return leftPanelHtml;
}
function showOrgDetail(id) {
	debugger;
	var orgData=partnerOrgArray["org"+id];
	loadPartnerOrgRightPane(orgData);

}
function loadPartnerOrgLeftPane(data) {
	$('.pagination').children().remove();
	$(".leftPaneData").html("");
	
	var leftPanelHtml = "";
	var i = 0;
	var active = false;
	var firstRow = null;
	$.each(data,
			function(key, value) {
				var partnerOrgId = value.partnerOrgId == null ? '': value.partnerOrgId;
				partnerOrgArray["org"+partnerOrgId]=value;
				if (i == 0) {
					firstRow = value;
					active = true;
				}
				leftPanelHtml = leftPanelHtml+ appendPartnerOrgDetail(value, active);
				active = false;
				i++;

			});
	$(".leftPaneData").append(leftPanelHtml);
	$('.leftpaneData').paginathing();
	
	 $(".tabs-left li a label").text(function(index, currentText) {
		    return currentText.substr(0, 20);
		});
	loadPartnerOrgRightPane(firstRow);
}

function loadPartnerOrgRightPane(data) {
	debugger;
	editMode=false;
	
	setChildLoadFlag(true);	
	/*activeTabName="";*/
	if (data == null) {
		$('#partnerOrgForm')[0].reset();
		$("#partnerOrgForm #a_inspectionCopy").html('');
		$("#partnerOrgForm #a_licenceCopy").html('');
		$("#partnerOrgForm #a_machinaryCopy").html('');
		$("#partnerOrgForm #a_staffCopy").html('');
		$("#partnerOrgForm .partnerOrgEEApproveDiv").hide();
		$("#partnerOrgForm .partnerOrgCEApproveDiv").hide();
		$('#authorizationCertificateId').val('');
		$('#authorizationCertificate').val('');
		$("#a_authorizationCertificate").html('');
		$("#partnerOrgForm #a_testingEquipmentDetails").html('');
		$("#partnerOrgForm #testingEquipmentDetails").val('');
		$("#partnerOrgForm .testingEquipmentDetails").attr('disabled', true);
		return;
	}

	var factoryName = data.name == null ? '' : data.name;
	var estdDate = data.estdDate == null ? '' : formatDate(data.estdDate);
	var address1 = data.location.address1 == null ? '' :data.location.address1;
	var inspectionReportNo = data.inspectionReportNo == null ? ''
			: data.inspectionReportNo;
	var postal = data.location.postal == null ? '' : data.location.postal;
	var country = data.location.country == null ? ''
			: data.location.country.countryId == null ? ''
					: data.location.country.countryId;
	var region = data.location.region == null ? ''
			: data.location.region.regionId == null ? ''
					: data.location.region.regionId;
	var city = data.location.city == null ? '' : data.location.city;
	var district = data.location.district.districtId == null ? ''
			: data.location.district.districtId;
	var partnerOrgId = data.partnerOrgId == null ? '' : data.partnerOrgId;
	var partnerOrgLocationId = data.location.locationId == null ? ''
			: data.location.locationId;
	var licenceNo = data.licenceNo == null ? '' : data.licenceNo;
	var licenceValidityDate = data.licenceValidityDate == null ? ''
			: formatDate(data.licenceValidityDate);
	var licenseType = data.licenseType == null ? '' : data.licenseType;
	var licenceCopyId = data.licenceCopy == null ? ''
			: data.licenceCopy.attachmentId == null ? ''
					: data.licenceCopy.attachmentId;
	var licenceCopyName = data.licenceCopy == null ? ''
			: data.licenceCopy.fileName == null ? ''
					: data.licenceCopy.fileName;
	var machinaryListCopyId = data.machinaryListCopy == null ? ''
			: data.machinaryListCopy.attachmentId == null ? ''
					: data.machinaryListCopy.attachmentId;
	var machinaryListCopyName = data.machinaryListCopy == null ? ''
			: data.machinaryListCopy.fileName == null ? ''
					: data.machinaryListCopy.fileName;
	var inspectionCopyId = data.inspectionReportCopy == null ? '': data.inspectionReportCopy.attachmentId == null ? '': data.inspectionReportCopy.attachmentId;
	var inspectionCopyName = data.inspectionReportCopy == null ? '': data.inspectionReportCopy.fileName == null ? '': data.inspectionReportCopy.fileName;

	var staffListId = data.staffListCopy == null ? '': data.staffListCopy.attachmentId == null ? '': data.staffListCopy.attachmentId;
	var staffListCopyName = data.staffListCopy == null ? '': data.staffListCopy.fileName == null ? '': data.staffListCopy.fileName;
	
	var authorizationCertificateId = data.authorizationCertificate == null ? '': data.authorizationCertificate.attachmentId == null ? '': data.authorizationCertificate.attachmentId;
	var authorizationCertificateName = data.authorizationCertificate == null ? '': data.authorizationCertificate.fileName == null ? '': data.authorizationCertificate.fileName;
	
	var factoryInspected= data.isFactoryInspected == null ? '': data.isFactoryInspected;
	
	var manPower = data.manPower == null ? '' : data.manPower;
	var remark = data.remark == null ? '' : data.remark;
	var isApproved = data.isApproved == null ? '' : data.isApproved;

	FactoryName = data.name == null ? '' : data.name;
	establishedDate = data.estdDate == null ? '' : formatDate(data.estdDate);
	licenseNo = data.licenceNo == null ? '' : data.licenceNo;
	locationName = data.location.city == null ? '' : data.location.city;
	var inspectionDate = data.inspectionDate == null ? '' :formatDate(data.inspectionDate);
	
	var eeComment=data.eeComment==null?'':data.eeComment;
	var isEEApproved=data.isEEApproved==null?'':data.isEEApproved;
	var ceComment=data.ceComment==null?'':data.ceComment;
	var isCEApproved=data.isCEApproved==null?'':data.isCEApproved;
	
	var paymentId=data.paymentDetail==null?'':data.paymentDetail.paymentDetailId==null?'':data.paymentDetail.paymentDetailId;
	var isFOApproved=data.paymentDetail==null?'':data.paymentDetail.isFOApproved==null?'':data.paymentDetail.isFOApproved;
	var isFAApproved=data.paymentDetail==null?'':data.paymentDetail.isFAApproved==null?'':data.paymentDetail.isFAApproved;
	
	var testingEquipmentDetailsId = data.testingEquipmentDetails == null ? '': data.testingEquipmentDetails.attachmentId == null ? '': data.testingEquipmentDetails.attachmentId;
	var testingEquipmentDetailsName = data.testingEquipmentDetails == null ? '': data.testingEquipmentDetails.fileName == null ? '': data.testingEquipmentDetails.fileName;
	
	$(".partnerOrgId").val(partnerOrgId);
	$("#partnerOrgForm #partnerOrgLocationId").val(partnerOrgLocationId);
	$("#partnerOrgForm #partnerOrgId").val(partnerOrgId);
	$("#partnerOrgForm #name").val(factoryName);
	$("#partnerOrgForm #estdDate").val(estdDate);
	$("#partnerOrgForm #inspectionReportNo").val(inspectionReportNo);
	$("#partnerOrgForm #address1").val(address1);
	$("#partnerOrgForm #city").val(city);
	$("#partnerOrgForm #country").val(country);
	$("#partnerOrgForm #region").val(region);
	$("#partnerOrgForm #district").val(district);
	$("#partnerOrgForm #postal").val(postal);
	$("#partnerOrgForm #licenceValidityDate").val(licenceValidityDate);
	$("#partnerOrgForm #licenceNo").val(licenceNo);
	$("#partnerOrgForm #licenseType").val(licenseType);
	$("#partnerOrgForm #licenceCopy").val(licenceCopyId);
	$("#partnerOrgForm #IsFactoryInspection").val(factoryInspected);
	onchangeoffactoryInspectiondone();
	$("#partnerOrgForm #machinaryListCopy").val(machinaryListCopyId);
	$("#partnerOrgForm #inspectionCopy").val(inspectionCopyId);
	$("#partnerOrgForm #staffListCopy").val(staffListId);
	$("#partnerOrgForm #manPower").val(manPower);
	$("#partnerOrgForm #inspectionDate").val(inspectionDate);
	$("#partnerOrgForm #orgPaymmentId").val(paymentId);
	$("#partnerOrgForm .dropDown").removeClass('errorinput');
	$("#partnerOrgForm .requiredFile").removeClass('errorinput');
	$('#partnerOrgForm #authorizationCertificate').val(authorizationCertificateId); 
	$('#partnerOrgForm #testingEquipmentDetails').val(testingEquipmentDetailsId);
	
	var url = $("#a_licenceCopy").data('url');
	$("#a_licenceCopy").attr('href', url);
	var a_licenceCopy = $("#partnerOrgForm #a_licenceCopy").prop('href') + '/'
			+ licenceCopyId;
	$("#partnerOrgForm #a_licenceCopy").prop('href', a_licenceCopy);
	$("#partnerOrgForm #a_licenceCopy").html(licenceCopyName);

	var url = $("#a_machinaryCopy").data('url');
	$("#a_machinaryCopy").attr('href', url);
	var a_machinaryCopy = $("#partnerOrgForm #a_machinaryCopy").prop('href')
			+ '/' + machinaryListCopyId;
	$("#partnerOrgForm #a_machinaryCopy").prop('href', a_machinaryCopy);
	$("#partnerOrgForm #a_machinaryCopy").html(machinaryListCopyName);
	
	var url = $("#a_inspectionCopy").data('url');
	$("#a_inspectionCopy").attr('href', url);
	var a_inspectionCopy = $("#partnerOrgForm #a_inspectionCopy").prop('href')+ '/' + inspectionCopyId;
	$("#partnerOrgForm #a_inspectionCopy").prop('href', a_inspectionCopy);
	$("#partnerOrgForm #a_inspectionCopy").html(inspectionCopyName);
	
	var url = $("#a_staffCopy").data('url');
	$("#a_staffCopy").attr('href', url);
	var a_staffCopy = $("#partnerOrgForm #a_staffCopy").prop('href')+ '/' + staffListId;
	$("#partnerOrgForm #a_staffCopy").prop('href', a_staffCopy);
	$("#partnerOrgForm #a_staffCopy").html(staffListCopyName);
	
	var url = $("#a_testingEquipmentDetails").data('url');
	$("#a_testingEquipmentDetails").attr('href', url);
	var a_testingEquipmentDetails = $("#partnerOrgForm #a_testingEquipmentDetails").prop('href')+'/'+testingEquipmentDetailsId;
	$("#partnerOrgForm #a_testingEquipmentDetails").prop('href', a_testingEquipmentDetails);
	$("#partnerOrgForm #a_testingEquipmentDetails").html(testingEquipmentDetailsName);
	
	var url = $("#a_authorizationCertificate").data('url');
	$("#a_authorizationCertificate").attr('href', url);
	var a_authorizationCertificate = $("#partnerOrgForm #a_authorizationCertificate").prop('href')+ '/' + authorizationCertificateId;
	$("#partnerOrgForm #a_authorizationCertificate").prop('href', a_authorizationCertificate);
	$("#partnerOrgForm #a_authorizationCertificate").html(authorizationCertificateName);
	
	
	showLicenseField(manPower);

	changeButtonPropertiesByOrgStatus(data);
	showOrgEditForPaymentChanges(isFOApproved,isFAApproved);
	showFileDeleteBtn(licenceCopyId, machinaryListCopyId,inspectionCopyId,staffListId,authorizationCertificateId,testingEquipmentDetailsId);
	changeOrgCommentAndStatusByRole('partnerOrgForm',isEEApproved,eeComment,isCEApproved,ceComment);
	setHeaderValues("Factory Name: "+factoryName, "Established Date : "+estdDate, "License No.: "+licenceNo, "Location : "+city);

}
function showOrgEditForPaymentChanges(foApproved,faApproved){
	var partnerData =  $("#partnerData").val();
	if(partnerData=="partnerRegistration")
		{
		  if(foApproved=="N" || faApproved=="N")
			  {
			    $("#editFactoryBtnId").show();
			    $("#editFactoryBtnId").removeClass("readonly");
			  }
		}
}
function changeButtonPropertiesByOrgStatus(data)
{
	debugger;
	 var partnerData =  $("#partnerData").val();
	 $("#addFactoryBtnId").addClass("readonly");
	 $("#editFactoryBtnId").hide();
	 $("#deleteFactoryBtnId").addClass("readonly");
	 $(".partnerOrgTabs").addClass("readonly");
	 $(".partnerOrgStatusBtn").addClass("readonly");
	 $(".partnerOrgRemark").addClass("readonly");
	 $(".partnerOrgEEApproveDiv").hide();
	 $(".partnerOrgCEApproveDiv").hide();
	 $('.partnerOrgActionBtn').addClass("readonly");
	 if(partnerData=="partnerRegistration")
		{
		    if(data.partner!=null && (data.partner.status=='CO' || data.partner.status=='EEC'||  data.partner.status=='EDIT' || data.partner.status=='DR'))
			  {
			     $("#addFactoryBtnId").removeClass("readonly");
				 $("#editFactoryBtnId").removeClass("readonly");
				 $("#editFactoryBtnId").show();
				 $("#deleteFactoryBtnId").removeClass("readonly");
				 $(".partnerOrgCEApproveDiv").show();
				 $(".partnerOrgEEApproveDiv").show();
			  }else if(data.partner!=null && (data.partner.status=='RJ' || data.partner.status=='CEC' || data.partner.isEEApproved=='Y' || data.partner.status=='IP')){
				  $("#addFactoryBtnId").addClass("readonly");
				  $("#deleteFactoryBtnId").addClass("readonly");
				  $("#editFactoryBtnId").hide();
				  $(".partnerOrgCEApproveDiv").show();
				  $(".partnerOrgEEApproveDiv").show();
			   }else if(data.partner.status==null || data.partner.status=="")
				 {
				    $("#editFactoryBtnId").hide();
				    $(".partnerOrgTabs").removeClass("readonly");
				    $(".partnerOrgActionBtn").removeClass("readonly");
				    $("#addFactoryBtnId").removeClass("readonly");
				    $("#deleteFactoryBtnId").removeClass("readonly");
				 }		       
		}else{
			$('.partnerOrgActionBtn').removeClass("readonly");
			$(".partnerOrgEEApproveDiv").show();
			$(".partnerOrgCEApproveDiv").show();
			$(".partnerOrgStatusBtn").removeClass("readonly");
			$(".partnerOrgRemark").removeClass("readonly");
		}
	 
}
function showLicenseField(manpower)
{
	if(manpower<10)
	{
	    $("#factoryLicenseCopyLabel").html("<label>Attach Undertaking Certificate Copy<span class='red'>*</span></label>");
	    $("#partnerOrgForm #factoryLicenseDivId").hide();
		$("#partnerOrgForm #factoryLicenseDateDivId").hide();
	    $("#partnerOrgForm #licenceValidityDate").attr("disabled","disabled");
		$("#partnerOrgForm #licenceNo").attr("disabled","disabled");
	}else{
		$("#factoryLicenseCopyLabel").html("<label>Attach Factory License Copy<span class='red'>*</span></label>");
		$("#partnerOrgForm #factoryLicenseDivId").show();
		$("#partnerOrgForm #factoryLicenseDateDivId").show();
		$("#partnerOrgForm #licenceValidityDate").removeAttr("disabled","disabled");
	    $("#partnerOrgForm #licenceNo").removeAttr("disabled","disabled");
	}
}
function showFileDeleteBtn(licenceAttachmentId, machinaryAttachmentId,inspectionAttachmentId,staffAttachmentId,authorizationCertificateId,testingEquipmentDetailsId) {
	debugger;
	if (licenceAttachmentId != '') {
		$(".licenceCopy").attr('disabled', false);
	} else {
		$(".licenceCopy").attr('disabled', true);
	}
	if (machinaryAttachmentId != '' ) {
		$(".machinaryListCopy").attr('disabled', false);
	} else {
		$(".machinaryListCopy").attr('disabled', true);
	}
	if (inspectionAttachmentId != '' ) {
		$(".inspectionCopy").attr('disabled', false);
	} else {
		$(".inspectionCopy").attr('disabled', true);
	}
	if (staffAttachmentId != '' ) {
		$(".staffListCopy").attr('disabled', false);
	} else {
		$(".staffListCopy").attr('disabled', true);
	}
	if (authorizationCertificateId != '' ) {
		$(".authorizationCertificate").attr('disabled', false);
	} else {
		$(".authorizationCertificate").attr('disabled', true);
	}
	if (testingEquipmentDetailsId!= '' ){
		$(".testingEquipmentDetails").attr('disabled', false);
	}else{
		$(".testingEquipmentDetails").attr('disabled', true);
	}
}
function loadOrgCountry(data) {

	$("#partnerOrgForm #country").html("");
	var options = '<option value="" ></option>';
	$.each(data, function(key, value) {
		options += '<option value="' + value.countryId + '">' + value.name
				+ '</option>'

	});
	$("#partnerOrgForm #country").append(options);
}

function loadOrgRegion(data) {

	$("#partnerOrgForm #region").html("");
	var options = '<option value=""></option>';
	$.each(data, function(key, value) {
		options += '<option value="' + value.regionId + '">' + value.name
				+ '</option>'

	});

	$("#partnerOrgForm #region").append(options);
}
function loadOrgDistrict(data) {

	$("#partnerOrgForm #district").html("");
	var options = '<option value=""></option>';
	$.each(data, function(key, value) {
		options += '<option value="' + value.districtId + '">' + value.name
				+ '</option>'

	});

	$("#partnerOrgForm #district").append(options);
}
function loadPaymentDetail(data)
{
	$("#partnerOrgForm #orgPaymmentId").html("");
	var options = '<option value=""></option>';
	$.each(data, function(key, value) {
		options += '<option value="' + value.paymentDetailId + '">'+value.paymentType.name+"-"+value.referenceNo+ '</option>'

	});

	$("#partnerOrgForm #orgPaymmentId").append(options);

}
function testingEquipmentDetailsDeleteResp(data){
	if (!data.hasError) {
		$('#testingEquipmentFileId').val('');
		$('#testingEquipmentDetails').val('');
		$("#a_testingEquipmentDetails").html('');
		$('.testingEquipmentDetails').attr('disabled', true);
		Alert.info(data.message);
		var partnerOrgId=$("#partnerOrgId").val();
		if(partnerOrgId!="")
		{
		 partnerOrgArray["org"+partnerOrgId].testingEquipmentDetails=null;
		}
	} else {
		Alert.warn(data.message);
	}

}
function licenceAttachmentDeleteResp(data) {

	if (!data.hasError) {
		$('#licenceFileId').val('');
		$('#licenceCopy').val('');
		$("#a_licenceCopy").html('');
		$('.licenceCopy').attr('disabled', true);
		Alert.info(data.message);
		var partnerOrgId=$("#partnerOrgId").val();
		if(partnerOrgId!="")
		{
		 partnerOrgArray["org"+partnerOrgId].licenceCopy=null;
		}
	} else {
		Alert.warn(data.message);
	}
}
function machinaryAttachmentDeleteResp(data) {
	if (!data.hasError) {
		$('#machinaryFileId').val('');
		$('#machinaryListCopy').val('');
		$("#a_machinaryCopy").html('');
		$('.machinaryListCopy').attr('disabled', true);
		Alert.info(data.message);
		var partnerOrgId=$("#partnerOrgId").val();
		if(partnerOrgId!="")
		{
		 partnerOrgArray["org"+partnerOrgId].machinaryListCopy=null;
		}
	} else {
		Alert.warn(data.message);
	}
}

function staffAttachmentDeleteResp(data) {
	if (!data.hasError) {
		$('#listOfStaff').val('');
		$('#staffListCopy').val('');
		$("#a_staffCopy").html('');
		$('.staffListCopy').attr('disabled', true);
		Alert.info(data.message);
		var partnerOrgId=$("#partnerOrgId").val();
		if(partnerOrgId!="")
		{
		 partnerOrgArray["org"+partnerOrgId].staffListCopy=null;
		}
	} else {
		Alert.warn(data.message);
	}
}
function inspectionAttachmentDeleteResp(data) {
	debugger;
	if (!data.hasError) {
		$('#inspectionId').val('');
		$('#inspectionCopy').val('');
		$("#a_inspectionCopy").html('');
		$('.inspectionCopy').attr('disabled', true);
		Alert.info(data.message);
		var partnerOrgId=$("#partnerOrgId").val();
		if(partnerOrgId!="")
		{
		  partnerOrgArray["org"+partnerOrgId].inspectionReportCopy=null;
		}
	} else {
		Alert.warn(data.message);
	}

}
function changeOrgCommentAndStatusByRole(formId,isEEApproved,eeComment,isCEApproved,ceComment)
{
	 debugger;
	    $("#"+formId+" .partnerOrgEEApproveDiv").find('input:radio, textarea').removeClass('readonly');
		$("#"+formId+" .partnerOrgCEApproveDiv").find('input:radio, textarea').removeClass('readonly');
		$("#"+formId+" .partnerOrgEEApproveDiv").find('input:radio, textarea').css("background-color","#FFF");
		$("#"+formId+" .partnerOrgCEApproveDiv").find('input:radio, textarea').css("background-color","#FFF");
		
	 /*$("#"+formId+" .partnerOrgEEApproveDiv").find('input:radio, textarea').removeAttr('disabled','disabled');
	 $("#"+formId+" .partnerOrgCEApproveDiv").find('input:radio, textarea').removeAttr('disabled','disabled');
	*/ $("#"+formId+" .partnerOrgEEApproveDiv").hide();
	 $("#"+formId+" .partnerOrgCEApproveDiv").hide();
	 
	 var role=$("#roleData").val();
	 var partnerData =  $("#partnerData").val();
	 if(role=='EXEENGR')
	 {
		 $("#"+formId+" .partnerOrgEEApproveDiv").show();
	    $("#"+formId+" #eeRemark").val(eeComment);
	    setStatusButton(formId,isEEApproved,'eeApproveBtn','eeClarification');
	 /*   $("#"+formId+" .partnerOrgCEApproveDiv").find('input:radio, textarea').addClass('readonly');
	    $("#"+formId+" .partnerOrgCEApproveDiv").find('input:radio, textarea').css("background-color","#DADCE2");
	 */  
	    $("#"+formId+" .partnerOrgCEApproveDiv").find('input:radio, textarea').attr('disabled','disabled');
	    if(isCEApproved=='C' || isCEApproved=='Y')
	    	 {
	    	     $("#"+formId+" .partnerOrgCEApproveDiv").show();
	    	     $("#"+formId+" .partnerOrgCEApproveDiv").find('input:radio, textarea').addClass('readonly');
	    		 $("#"+formId+" .partnerOrgCEApproveDiv").find('input:radio, textarea').css("background-color","#DADCE2");
	    		 $("#"+formId+" .partnerOrgCEApproveDiv").find('input:radio, textarea').removeAttr('disabled','disabled');
		    	 $("#"+formId+" #ceRemark").val(ceComment);
			     setStatusButton(formId,isCEApproved,'ceApproveBtn','ceClarification');
		     }
	     
	 }else if(role=='CHFENGR'){
		 $("#"+formId+" .partnerOrgCEApproveDiv").find('input:radio, textarea').removeAttr('disabled','disabled');
		 $("#"+formId+" .partnerOrgCEApproveDiv").show();
		 $("#"+formId+" #ceRemark").val(ceComment);
		 setStatusButton(formId,isCEApproved,'ceApproveBtn','ceClarification');
		 $("#"+formId+" .partnerOrgEEApproveDiv").show();
		 $("#"+formId+" .partnerOrgEEApproveDiv").find('input:radio, textarea').addClass('readonly');
		 $("#"+formId+" .partnerOrgEEApproveDiv").find('input:radio, textarea').css("background-color","#DADCE2");
		 /*$("#"+formId+" .partnerOrgEEApproveDiv").find('input:radio, textarea').attr('disabled','disabled');*/
		 $("#"+formId+" #eeRemark").val(eeComment);
		 setStatusButton(formId,isEEApproved,'eeApproveBtn','eeClarification');
		 
	 }else if(partnerData=="partnerRegistration"){
		/* $("#"+formId+" .partnerOrgEEApproveDiv").find('input:radio, textarea').attr('disabled','disabled');
		 $("#"+formId+" .partnerOrgCEApproveDiv").find('input:radio, textarea').attr('disabled','disabled');
		*/
		   $("#"+formId+" .partnerOrgEEApproveDiv").find('input:radio, textarea').addClass('readonly');
		   $("#"+formId+" .partnerOrgCEApproveDiv").find('input:radio, textarea').addClass('readonly');
		   $("#"+formId+" .partnerOrgEEApproveDiv").find('input:radio, textarea').css("background-color","#DADCE2");
		   $("#"+formId+" .partnerOrgCEApproveDiv").find('input:radio, textarea').css("background-color","#DADCE2");
		   $("#"+formId+" .partnerOrgCEApproveDiv").hide();
		    /*if(isCEApproved!="")
		    {
		    	if(isCEApproved!="Y"){
		    	$("#"+formId+" .partnerOrgCEApproveDiv").show();
                setStatusButton(formId,isCEApproved,'ceApproveBtn','ceClarification');
		    	}
		    }*/
		    if(isEEApproved!=""){
		    	if(isEEApproved=="C"){
		    	 $("#"+formId+" .partnerOrgEEApproveDiv").show();
		      setStatusButton(formId,isEEApproved,'eeApproveBtn','eeClarification');
		    	}
		    }
		    $("#"+formId+" .remark").addClass('readonly');
	        $("#"+formId+" .statusBtn").addClass('readonly');
		    $("#"+formId+" #eeRemark").val(eeComment);
		    $("#"+formId+" #ceRemark").val(ceComment);
		 
	 }
}


function onchangeoffactoryInspectiondone(){
	debugger;
var inspectionDone=$("#IsFactoryInspection").val();

if(inspectionDone=='Y'){
	$(".factoryInspectionDone").css("display","block");
	$(".divcler").css("display","block");
	$('.divcler').addClass('clearfix');
	$("#inspectionDate").addClass("requiredDate");
	if($("#inspectionCopy").val()==null || $("#inspectionCopy").val()=="" ){
		$("#inspectionId").addClass("requiredFile");
	}
	else{
		$("#inspectionId").removeClass("requiredFile");
	}
	
	$("#inspectionReportNo").removeAttr("disabled","disabled");
	$("#inspectionDate").removeAttr("disabled");
	$("#inspectionId").removeAttr("disabled");
	
}
else if(inspectionDone=='N'){
	$(".factoryInspectionDone").css("display","none");
	$(".divcler").css("display","none");
	$('.divcler').removeClass('clearfix');
	$("#inspectionDate").removeClass("requiredDate");
	$("#inspectionId").removeClass("requiredFile");
	
	$("#inspectionReportNo").attr("disabled","disabled");
	$("#inspectionDate").attr("disabled","disabled");
	$("#inspectionId").attr("disabled","disabled");
	
}
}
function authorizationCertificateDeleteResp(data)
{
	if (!data.hasError) {
		$('#authorizationCertificateId').val('');
		$('#authorizationCertificate').val('');
		$("#a_authorizationCertificate").html('');
		$('.authorizationCertificate').attr('disabled', true);
		Alert.info(data.message);
		var partnerOrgId=$("#partnerOrgId").val();
		if(partnerOrgId!="")
		{
		  partnerOrgArray["org"+partnerOrgId].authorizationCertificate=null;
		}
	} else {
		Alert.warn(data.message);
	}
}