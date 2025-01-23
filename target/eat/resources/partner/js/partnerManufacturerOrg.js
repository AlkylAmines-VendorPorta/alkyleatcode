var partnerManufacturerOrgArray=new Array();
$(document).ready(function() {

	$('#addManufacturerFactoryBtnId').click(function(event) {
		event.preventDefault();
		$('#manufacturerOrgForm')[0].reset();
		$('#partnerManufacturerOrgId').val("");
		$('#partnerManufacturerOrgLocationId').val("");
		$("#manufacturerOrgLicenceCopy").val("");
		$('#manufacturerOrgInspectionCopy').val('');
		$("#manufacturerOrgMachinaryListCopy").val("");
		$("#manufacturerOrgForm #a_manufacturerOrgMachinaryCopy").html('');
		$("#manufacturerOrgForm #a_manufacturerOrgLicenceCopy").html('');
		$("#manufacturerOrgForm #a_manufacturerOrgInspectionCopy").html('');
		$("#manufacturerOrgForm #a_manufacturerStaffListCopy").html('');
		$('#manufacturerAuthorizationCertificateId').val('');
		$('#manufacturerAuthorizationCertificate').val('');
		$("#a_manufacturerAuthorizationCertificate").html('');
		$('.manufacturerAuthorizationCertificate').attr('disabled', true);
		$("#manufacturerStaffListCopy").val("");
		$(".manufacturerStaffListCopy").attr('disabled', true);
		$(".manufacturerOrgLicenceCopy").attr('disabled', true);
		$(".manufacturerOrgInspectionCopy").attr('disabled', true);
		$(".manufacturerOrgMachinaryListCopy").attr('disabled', true);
		$("#manufacturerOrgForm .manufacturerEEApproveDiv").hide();
		$("#manufacturerOrgForm .manufacturerCEApproveDiv").hide();
		$("#manufacturerOrgForm #a_mTestingEquipmentDetails").html('');
		$("#manufacturerOrgForm #mTestingEquipmentDetails").val('');
		$("#manufacturerOrgForm .mTestingEquipmentDetails").attr('disabled', true);
		/*$(".disableTraderFactoryTabs").css('display','none');*/

	});
	
	$('#cancelManuctureFactoryDetailsBtnId').click(function(event) {
		debugger;
		event.preventDefault();
		editMode=false;
	   /*	activeTabName="";*/
		var activeFactoryId = $('.leftPaneData').find('li.active').attr('id');
		if (activeFactoryId != undefined) {
			showManufacturerOrgDetail(activeFactoryId);
		} else
		    {
			   $('#manufacturerOrgForm')[0].reset();
				$("#manufacturerOrgForm #a_manufacturerOrgMachinaryCopy").html('');
				$("#manufacturerOrgForm #a_manufacturerOrgLicenceCopy").html('');
				$("#manufacturerOrgForm #a_manufacturerOrgInspectionCopy").html('');
				$("#manufacturerOrgForm #a_manufacturerStaffListCopy").html('');
				$("#manufacturerStaffListCopy").val("");
				$(".manufacturerStaffListCopy").attr('disabled', true);
				$('#manufacturerAuthorizationCertificateId').val('');
				$('#manufacturerAuthorizationCertificate').val('');
				$("#a_manufacturerAuthorizationCertificate").html('');
				$('.manufacturerAuthorizationCertificate').attr('disabled', true);
				$("#manufacturerOrgForm #a_mTestingEquipmentDetails").html('');
				$("#manufacturerOrgForm #mTestingEquipmentDetails").val('');
				$("#manufacturerOrgForm .mTestingEquipmentDetails").attr('disabled', true);
		    }
		
	});
	$('#manufacturerOrgManPower').on('keyup',function(event) {
		event.preventDefault();
		var manpower=$(this).val();
		if(manpower<10)
		{
		    $("#manufacturerfactoryLicenseCopyLabel").html("<label>Attach Undertaking Certificate Copy<span class='red'>*</span></label>");
		    $("#manufacturerOrgForm #manufacturerfactoryLicenseDivId").hide();
			$("#manufacturerOrgForm #manufacturerFactoryLicenseDateDivId").hide();
		    $("#manufacturerOrgForm #manufacturerOrgLicenceValidityDate").attr("disabled","disabled");
			$("#manufacturerOrgForm #manufacturerOrgLicenceNo").attr("disabled","disabled");
			$("#manufacturerOrgForm #a_manufacturerOrgLicenceCopy").html('');
			$(".manufacturerOrgLicenceCopy").attr('disabled', true);
			$("#manufacturerOrgLicenceCopy").val("");
			$('#manufacturerOrgLicenceCopy').val('');
		}else{
			$("#manufacturerfactoryLicenseCopyLabel").html("<label>Attach Factory License Copy<span class='red'>*</span></label>");
			$("#manufacturerOrgForm #manufacturerfactoryLicenseDivId").show();
			$("#manufacturerOrgForm #manufacturerFactoryLicenseDateDivId").show();
			$("#manufacturerOrgForm #manufacturerOrgLicenceValidityDate").removeAttr("disabled","disabled");
		    $("#manufacturerOrgForm #manufacturerOrgLicenceNo").removeAttr("disabled","disabled");
		    $("#manufacturerOrgForm #a_manufacturerOrgLicenceCopy").html('');
			$(".manufacturerOrgLicenceCopy").attr('disabled', true);
			$("#manufacturerOrgLicenceCopy").val("");
			$('#manufacturerOrgLicenceCopy').val('');
		}
		
    });
	$("#manufacturerOrgForm").find("input,select,textarea").change(function() {
		 debugger;
	   	 editMode=true;
	   	 activeTabName="Manufacturer Fatory Details";
	});
	$("#manufacturerOrgForm .fileDeleteBtn").click(function() {
  	 editMode=true;
  	 activeTabName="Manufacturer Fatory Details";
  	 requiredFileDeleted=true;
   });
});
function getManufacturerFactory(event,el)
{
	event.preventDefault();	
	
		if(!editMode && !requiredFileDeleted){
			cacheLi();
			setCurrentTab(el);
			if(getChangedFlag()){
    			submitWithParam('getManufacturerOrg','partnerItemManufacturerId','onManufacturerOrgTabResp');	
    			setChangedFlag(false);
			}else{
				getCacheLi();
			}
			setActiveTabName("Factory Details", $('.leftPaneData li').length);
			setHeaderValues("Manufacturer Name: "+manufacturerName, "Email : "+emailId, "Mobile No.: "+mobNo, "Location : "+manufacturerCity);
		}else{
			event.stopPropagation();
	        Alert.warn("Please Save Changed Data Of "+activeTabName+" Tab");
		}
		$("#filterBtnId").addClass("readonly");
	    checkForFilterByRole();
}
function partnerManuctureOrgResp(data) {
	debugger;
	
	setChildLoadFlag(true);
	$('.pagination').children().remove();
	if(!data.response.hasError)
	{
	
		showSubmitFormOnManufacturerChanges();
		editMode=false;
	   	activeTabName="";
	   	requiredFileDeleted=false;
	var estdDate = data.estdDate == null ? '' : data.estdDate;
	var currentPartnerOrgId = $('ul.leftPaneData').find('li.active').attr('id');
	var leftPanelHtml = "";
	var status = true;
	var partnerOrgId = data.partnerOrgId;
	$(".partnerManufacturerOrgId").val(partnerOrgId);
	$('#manufacturerOrgForm #partnerManufacturerOrgId').val(partnerOrgId);
	$('#manufacturerOrgForm #partnerManufacturerOrgLocationId').val(data.location.locationId);
	if (currentPartnerOrgId == partnerOrgId) {
		$('#' + currentPartnerOrgId).remove();
	}else {
		$('#' + currentPartnerOrgId).removeClass('active');
	}
	leftPanelHtml = appendPartnerManufacturerOrgDetail(data, status);	
	$(".leftPaneData").prepend(leftPanelHtml);
	if(data.licenceCopy!=null)
	{  
		data.licenceCopy.fileName=$("#manufacturerOrgForm #a_manufacturerOrgLicenceCopy").html();
	}
	if(data.machinaryListCopy!=null)
	{
	  data.machinaryListCopy.fileName=$("#manufacturerOrgForm #a_manufacturerOrgMachinaryCopy").html();
	}
	if(data.inspectionReportCopy!=null)
	{
	 data.inspectionReportCopy.fileName=$("#manufacturerOrgForm #a_manufacturerOrgInspectionCopy").html();
	}
	if(data.staffListCopy!=null)
	{
	 data.staffListCopy.fileName=$("#manufacturerOrgForm #a_manufacturerStaffListCopy").html();
	}
	if(data.authorizationCertificate!=null)
	{
	 data.authorizationCertificate.fileName=$("#manufacturerOrgForm #a_manufacturerAuthorizationCertificate").html();
	}
	if(data.testingEquipmentDetails!=null)
	{
	 data.testingEquipmentDetails.fileName=$("#manufacturerOrgForm #a_mTestingEquipmentDetails").html();
	}
	partnerManufacturerOrgArray["org"+partnerOrgId]=data;
	status = false;
	Alert.info(data.response.message);
	/*$(".disableTraderFactoryTabs").css('display','inline-block');*/
	}else{
		Alert.warn(data.response.message);
		
	}
	
	$('.leftPaneData').paginathing();
	
	$(".tabs-left li a label").text(function(index, currentText) {
	    return currentText.substr(0, 20);
	});
}

function partnerManufacturerOrgDelResp(data) {
	$('.pagination').children().remove();
	if(data.hasError==false)
	{
		if ($("#partnerData").val() == "partnerRegistration") {
		$("#manufacturerOrgForm  .manufacturerCEApproveDiv").hide();
		$("#manufacturerOrgForm  .manufacturerEEApproveDiv").hide();
		}
		Alert.info(data.message);
		showSubmitFormOnManufacturerChanges();
		var currentPartnerOrgId = $('ul.leftPaneData').find('li.active').attr('id');
		$('#' + currentPartnerOrgId).remove();
		$('#manufacturerOrgForm')[0].reset();
		$('#partnerManufacturerOrgId').val("");
		$('#partnerManufacturerOrgLocationId').val("");
		$("#manufacturerOrgForm #a_manufacturerOrgLicenceCopy").html("");
		$("#manufacturerOrgForm #a_manufacturerOrgMachinaryCopy").html("");
		$("#manufacturerOrgForm #a_manufacturerOrgInspectionCopy").html("");
		$("#manufacturerOrgForm #a_manufacturerStaffListCopy").html('');
		$("#manufacturerOrgForm #a_manufacturerAuthorizationCertificate").html('');
		$("#manufacturerOrgForm #manufacturerAuthorizationCertificate").val('');
		$("#manufacturerOrgForm #a_mTestingEquipmentDetails").html('');
		$("#manufacturerOrgForm #mTestingEquipmentDetails").val('');
		$("#manufacturerOrgForm .mTestingEquipmentDetails").attr('disabled', true);
		$(".partnerManufacturerOrgId").val("");
	}else{
		Alert.warn(data.message);
	}
	setActiveTabName("Factory Details", $('.leftPaneData li').length);
	event.preventDefault();
	$('.leftPaneData').paginathing();
	
	 $(".tabs-left li a label").text(function(index, currentText) {
		    return currentText.substr(0, 20);
		});
}
function setLeftPaneDropDownOnOrgResp(id) {
	var country = $("#manufacturerOrgForm #manufacturerOrgCountry").val();
	var region = $("#manufacturerOrgForm #manufacturerOrgRegion").val();
	var district = $("#manufacturerOrgForm #manufacturerOrgDistrict").val();
	$("#labelCountry-" + id).html(country);
	$("#labelRegion-" + id).html(region);
	$("#labelDistrict-" + id).html(district);
}
function onManufacturerOrgTabResp(data) {
debugger;
	if (data.objectMap.hasOwnProperty('countries')) {
		loadManufacturerOrgCountry(data.objectMap.countries);
	}
	if (data.objectMap.hasOwnProperty('regions')) {
		loadManufacturerOrgRegion(data.objectMap.regions);
	}
	if (data.objectMap.hasOwnProperty('districts')) {
		loadManufacturerOrgDistrict(data.objectMap.districts);
	}
	if (data.objectMap.hasOwnProperty('orgs')) {
		if (!$.isEmptyObject(data.objectMap.orgs)) {
			loadPartnerManufacturerOrgLeftPane(data.objectMap.orgs);
			$('.disableFactoryTabs').show();
			$("#tabstrip3").kendoTabStrip();
		}
		else {
			$('.disableFactoryTabs').hide();
			$(".leftPaneData").html("");
			$('#manufacturerOrgForm')[0].reset();
			$('#partnerManufacturerOrgId').val("");
			$('#partnerManufacturerOrgLocationId').val("");
			$("#manufacturerOrgForm #a_manufacturerOrgLicenceCopy").html("");
			$("#manufacturerOrgForm #a_manufacturerOrgMachinaryCopy").html("");
			$("#manufacturerOrgForm #a_manufacturerOrgInspectionCopy").html("");
			$("#manufacturerOrgForm #a_manufacturerStaffListCopy").html('');
			$("#manufacturerOrgForm #a_manufacturerAuthorizationCertificate").html('');
			$("#manufacturerOrgForm #a_mTestingEquipmentDetails").html('');
			$("#manufacturerOrgForm #mTestingEquipmentDetails").val('');
			$("#manufacturerOrgForm .mTestingEquipmentDetails").attr('disabled', true);
		}
		
	}
	setActiveTabName("Factory Details", $('.leftPaneData li').length);
}

function appendPartnerManufacturerOrgDetail(value, active) {
	debugger;
	var leftPanelHtml = '';
	var partnerOrgId = value.partnerOrgId == null ? '' : value.partnerOrgId;
	if (active) {
		leftPanelHtml = leftPanelHtml
				+ ' <li class="active" onclick="showManufacturerOrgDetail(' + partnerOrgId
				+ ')" id="' + partnerOrgId + '">';
	} else {
		leftPanelHtml = leftPanelHtml + ' <li onclick="showManufacturerOrgDetail('
				+ partnerOrgId + ')" id="' + partnerOrgId + '">';
	}
	var factoryName = value.name == null ? '' : value.name;
	var estdDate = value.estdDate == null ? '' :formatDate(value.estdDate);
	var address1 = value.location.address1 == null ? '': value.location.address1;
	var licenceNo = value.licenceNo == null ? '' : value.licenceNo;	
	
	leftPanelHtml = leftPanelHtml + ' <a href="#Master" data-toggle="tab">'
			+ ' <div class="col-md-12">'
			+ '  <label class="col-xs-6" id="labelManufacturerFactoryName-' + partnerOrgId
			+ '">'+factoryName+'</label>'
		    + ' </div>'
			+ ' </a>' + ' </li>';

	$('.disableFactoryTabs').show();
	$("#tabstrip3").kendoTabStrip();
	return leftPanelHtml;
}
function showManufacturerOrgDetail(id) {
	debugger;
	var orgData=partnerManufacturerOrgArray["org"+id];
	loadPartnerManufacturerOrgRightPane(orgData);
}
function loadPartnerManufacturerOrgLeftPane(data) {
	$('.pagination').children().remove();
	$(".leftPaneData").html("");
	var leftPanelHtml = "";
	var i = 0;
	var active = false;
	var firstRow = null;
	$.each(data,
			function(key, value) {
				var partnerOrgId = value.partnerOrgId == null ? '': value.partnerOrgId;
				partnerManufacturerOrgArray["org"+partnerOrgId]=value;
				if (i == 0) {
					firstRow = value;
					active = true;
				}
				leftPanelHtml = leftPanelHtml+ appendPartnerManufacturerOrgDetail(value, active);
				active = false;
				i++;

			});
	$(".leftPaneData").append(leftPanelHtml);
	$('.leftpaneData').paginathing();
	
	 $(".tabs-left li a label").text(function(index, currentText) {
		    return currentText.substr(0, 20);
		});
	 loadPartnerManufacturerOrgRightPane(firstRow);
}

function loadPartnerManufacturerOrgRightPane(data) {
	debugger;
	editMode=false;
	
	setChildLoadFlag(true);
   	/*activeTabName="";*/
	if (data == null) {
		    $(".partnerManufacturerOrgId").val("");
		    $('#partnerManufacturerOrgLocationId').val("");
		    $('#manufacturerOrgForm')[0].reset();
		    $('#partnerManufacturerOrgId').val("");
			$('#partnerManufacturerOrgLocationId').val("");
			$("#manufacturerOrgForm #a_manufacturerOrgMachinaryCopy").html('');
			$("#manufacturerOrgForm #a_manufacturerOrgLicenceCopy").html('');
			$("#manufacturerOrgForm #a_manufacturerOrgInspectionCopy").html('');
			$("#manufacturerOrgForm #a_manufacturerAuthorizationCertificate").html('');	
			$("#manufacturerOrgForm #a_mTestingEquipmentDetails").html('');
			$("#manufacturerOrgForm #mTestingEquipmentDetails").val('');
			$("#manufacturerOrgForm .mTestingEquipmentDetails").attr('disabled', true);
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

	var manPower = data.manPower == null ? '' : data.manPower;
	var remark = data.remark == null ? '' : data.remark;
	var isApproved = data.isApproved == null ? '' : data.isApproved;

	/*FactoryName = data.name == null ? '' : data.name;
	establishedDate = data.estdDate == null ? '' : formatDate(data.estdDate);
	licenseNo = data.licenceNo == null ? '' : data.licenceNo;
	locationName = data.location.city == null ? '' : data.location.city;*/
	var inspectionDate = data.inspectionDate == null ? '' :formatDate(data.inspectionDate);
	
	var eeComment=data.eeComment==null?'':data.eeComment;
	var isEEApproved=data.isEEApproved==null?'':data.isEEApproved;
	var ceComment=data.ceComment==null?'':data.ceComment;
	var isCEApproved=data.isCEApproved==null?'':data.isCEApproved;

    var partnerManufacturerId=data.partnerItemManufacturer==null?"":data.partnerItemManufacturer.partnerItemManufacturerId==null?"":data.partnerItemManufacturer.partnerItemManufacturerId;
    var staffListCopyId = data.staffListCopy == null ? '': data.staffListCopy.attachmentId == null ? '': data.staffListCopy.attachmentId;
	var staffListCopyName = data.staffListCopy == null ? '': data.staffListCopy.fileName == null ? '': data.staffListCopy.fileName;
	var orgInspected= data.isFactoryInspected == null ? '': data.isFactoryInspected;
	
	var authorizationCertificateId = data.authorizationCertificate == null ? '': data.authorizationCertificate.attachmentId == null ? '': data.authorizationCertificate.attachmentId;
	var authorizationCertificateName = data.authorizationCertificate == null ? '': data.authorizationCertificate.fileName == null ? '': data.authorizationCertificate.fileName;
	
	var testingEquipmentDetailsId = data.testingEquipmentDetails == null ? '': data.testingEquipmentDetails.attachmentId == null ? '': data.testingEquipmentDetails.attachmentId;
	var testingEquipmentDetailsName = data.testingEquipmentDetails == null ? '': data.testingEquipmentDetails.fileName == null ? '': data.testingEquipmentDetails.fileName;
	
	$("#manufacturerOrgForm #partnerManufacturerOrgLocationId").val(partnerOrgLocationId);
	$(".partnerManufacturerOrgId").val(partnerOrgId);
	$("#manufacturerOrgForm .partnerManufacturerId").val(partnerManufacturerId);
	$("#manufacturerOrgForm #partnerManufacturerOrgId").val(partnerOrgId);
	$("#manufacturerOrgForm #manufacturerOrgName").val(factoryName);
	$("#manufacturerOrgForm #manufacturerOrgEstdDate").val(estdDate);
	$("#manufacturerOrgForm #manufacturerOrgInspectionReportNo").val(inspectionReportNo);
	$("#manufacturerOrgForm #manufacturerOrgAddress1").val(address1);
	$("#manufacturerOrgForm #manufacturerOrgcity").val(city);
	$("#manufacturerOrgForm #manufacturerOrgCountry").val(country);
	$("#manufacturerOrgForm #manufacturerOrgRegion").val(region);
	$("#manufacturerOrgForm #manufacturerOrgDistrict").val(district);
	$("#manufacturerOrgForm #manufacturerOrgPostal").val(postal);
	$("#manufacturerOrgForm #manufacturerOrgLicenceValidityDate").val(licenceValidityDate);
	$("#manufacturerOrgForm #manufacturerOrgLicenceNo").val(licenceNo);
	$("#manufacturerOrgForm #manufacturerOrgLicenseType").val(licenseType);
	$("#manufacturerOrgForm #manufacturerOrgLicenceCopy").val(licenceCopyId);
	$("#manufacturerOrgForm #manufacturerOrgMachinaryListCopy").val(machinaryListCopyId);
	$("#manufacturerOrgForm #manufacturerOrgInspectionCopy").val(inspectionCopyId);
	$("#manufacturerOrgForm #manufacturerOrgManPower").val(manPower);
	$("#manufacturerOrgForm #manufacturerOrgInspectionDate").val(inspectionDate);
	$("#manufacturerOrgForm .dropDown").removeClass('errorinput');
	$("#manufacturerOrgForm .requiredFile").removeClass('errorinput');
	$("#manufacturerOrgForm #manufacturerStaffListCopy").val(staffListCopyId);
	$("#manufacturerOrgForm #manufacturerAuthorizationCertificate").val(authorizationCertificateId);
	$("#manufacturerOrgForm #isOrgInspection").val(orgInspected);
	$("#manufacturerOrgForm #mTestingEquipmentDetails").val(testingEquipmentDetailsId);
	showInspectionFiles();
	
	var url = $("#a_manufacturerOrgLicenceCopy").data('url');
	$("#a_manufacturerOrgLicenceCopy").attr('href', url);
	var a_licenceCopy = $("#manufacturerOrgForm #a_manufacturerOrgLicenceCopy").prop('href') + '/'
			+ licenceCopyId;
	$("#manufacturerOrgForm #a_manufacturerOrgLicenceCopy").prop('href', a_licenceCopy);
	$("#manufacturerOrgForm #a_manufacturerOrgLicenceCopy").html(licenceCopyName);

	var url = $("#a_manufacturerOrgMachinaryCopy").data('url');
	$("#a_manufacturerOrgMachinaryCopy").attr('href', url);
	var a_machinaryCopy = $("#manufacturerOrgForm #a_manufacturerOrgMachinaryCopy").prop('href')
			+ '/' + machinaryListCopyId;
	$("#manufacturerOrgForm #a_manufacturerOrgMachinaryCopy").prop('href', a_machinaryCopy);
	$("#manufacturerOrgForm #a_manufacturerOrgMachinaryCopy").html(machinaryListCopyName);
	
	var url = $("#a_manufacturerOrgInspectionCopy").data('url');
	$("#a_manufacturerOrgInspectionCopy").attr('href', url);
	var a_inspectionCopy = $("#manufacturerOrgForm #a_manufacturerOrgInspectionCopy").prop('href')+ '/' + inspectionCopyId;
	$("#manufacturerOrgForm #a_manufacturerOrgInspectionCopy").prop('href', a_inspectionCopy);
	$("#manufacturerOrgForm #a_manufacturerOrgInspectionCopy").html(inspectionCopyName);
	
	var url = $("#a_manufacturerStaffListCopy").data('url');
	$("#a_manufacturerStaffListCopy").attr('href', url);
	var a_manufacturerStaffListCopy = $("#manufacturerOrgForm #a_manufacturerStaffListCopy").prop('href')+ '/' + staffListCopyId;
	$("#manufacturerOrgForm #a_manufacturerStaffListCopy").prop('href', a_manufacturerStaffListCopy);
	$("#manufacturerOrgForm #a_manufacturerStaffListCopy").html(staffListCopyName);
	
	var url = $("#a_manufacturerAuthorizationCertificate").data('url');
	$("#a_manufacturerAuthorizationCertificate").attr('href', url);
	var a_manufacturerAuthorizationCertificate = $("#manufacturerOrgForm #a_manufacturerAuthorizationCertificate").prop('href')+ '/' +authorizationCertificateId;
	$("#manufacturerOrgForm #a_manufacturerAuthorizationCertificate").prop('href', a_manufacturerAuthorizationCertificate);
	$("#manufacturerOrgForm #a_manufacturerAuthorizationCertificate").html(authorizationCertificateName);
	
	var url = $("#a_mTestingEquipmentDetails").data('url');
	$("#a_mTestingEquipmentDetails").attr('href', url);
	var a_mTestingEquipmentDetails = $("#manufacturerOrgForm #a_mTestingEquipmentDetails").prop('href')+ '/' +testingEquipmentDetailsId;
	$("#manufacturerOrgForm #a_mTestingEquipmentDetails").prop('href', a_mTestingEquipmentDetails);
	$("#manufacturerOrgForm #a_mTestingEquipmentDetails").html(testingEquipmentDetailsName);
	
	showManufacturerLicenseField(manPower);

	showManufacturerFileDeleteBtn(licenceCopyId, machinaryListCopyId,inspectionCopyId,staffListCopyId,authorizationCertificateId,testingEquipmentDetailsId);
	changeManufacturerCommentAndStatusByRole('manufacturerOrgForm',isEEApproved,eeComment,isCEApproved,ceComment);
	setHeaderValues("Manufacturer Name: "+manufacturerName, "Email : "+emailId, "Mobile No.: "+mobNo, "Location : "+manufacturerCity);
    /*$(".disableTraderFactoryTabs").css('display','inline-block');*/
}
function showManufacturerLicenseField(manpower)
{
	if(manpower<10)
	{
	    $("#manufacturerfactoryLicenseCopyLabel").html("<label>Attach Undertaking Certificate Copy<span class='red'>*</span></label>");
	    $("#manufacturerOrgForm #manufacturerfactoryLicenseDivId").hide();
		$("#manufacturerOrgForm #manufacturerFactoryLicenseDateDivId").hide();
	    $("#manufacturerOrgForm #manufacturerOrgLicenceValidityDate").attr("disabled","disabled");
		$("#manufacturerOrgForm #manufacturerOrgLicenceNo").attr("disabled","disabled");
	}else{
		$("#manufacturerfactoryLicenseCopyLabel").html("<label>Attach Factory License Copy<span class='red'>*</span></label>");
		$("#manufacturerOrgForm #manufacturerfactoryLicenseDivId").show();
		$("#manufacturerOrgForm #manufacturerFactoryLicenseDateDivId").show();
		$("#manufacturerOrgForm #manufacturerOrgLicenceValidityDate").removeAttr("disabled","disabled");
	    $("#manufacturerOrgForm #manufacturerOrgLicenceNo").removeAttr("disabled","disabled");
	}
}
function showManufacturerFileDeleteBtn(licenceAttachmentId, machinaryAttachmentId,inspectionAttachmentId,staffListCopyId,authorizationCertificateId,testingEquipmentDetailsId) {
	debugger;
	if (licenceAttachmentId != '') {
		$(".manufacturerOrgLicenceCopy").attr('disabled', false);
	} else {
		$(".manufacturerOrgLicenceCopy").attr('disabled', true);
	}
	if (machinaryAttachmentId != '' ) {
		$(".manufacturerOrgMachinaryListCopy").attr('disabled', false);
	} else {
		$(".manufacturerOrgMachinaryListCopy").attr('disabled', true);
	}
	if (inspectionAttachmentId != '' ) {
		$(".manufacturerOrgInspectionCopy").attr('disabled', false);
	} else {
		$(".manufacturerOrgInspectionCopy").attr('disabled', true);
	}
	if (staffListCopyId != '' ) {
		$(".manufacturerStaffListCopy").attr('disabled', false);
	} else {
		$(".manufacturerStaffListCopy").attr('disabled', true);
	}
	if (authorizationCertificateId != '' ) {
		$(".manufacturerAuthorizationCertificate").attr('disabled', false);
	} else {
		$(".manufacturerAuthorizationCertificate").attr('disabled', true);
	}
	if (testingEquipmentDetailsId != '' ) {
		$(".mTestingEquipmentDetails").attr('disabled', false);
	} else {
		$(".mTestingEquipmentDetails").attr('disabled', true);
	}
}
function loadManufacturerOrgCountry(data) {

	$("#manufacturerOrgForm #manufacturerOrgCountry").html("");
	var options = '<option value="" ></option>';
	$.each(data, function(key, value) {
		options += '<option value="' + value.countryId + '">' + value.name
				+ '</option>'

	});
	$("#manufacturerOrgForm #manufacturerOrgCountry").append(options);
}

function loadManufacturerOrgRegion(data) {

	$("#manufacturerOrgForm #manufacturerOrgRegion").html("");
	var options = '<option value=""></option>';
	$.each(data, function(key, value) {
		options += '<option value="' + value.regionId + '">' + value.name
				+ '</option>'

	});

	$("#manufacturerOrgForm #manufacturerOrgRegion").append(options);
}
function loadManufacturerOrgDistrict(data) {

	$("#manufacturerOrgForm #manufacturerOrgDistrict").html("");
	var options = '<option value=""></option>';
	$.each(data, function(key, value) {
		options += '<option value="' + value.districtId + '">' + value.name
				+ '</option>'

	});

	$("#manufacturerOrgForm #manufacturerOrgDistrict").append(options);
}
function manufacturerOrgLicenceAttachmentDeleteResp(data) {

	if (!data.hasError) {
		$('#manufacturerOrgLicenceFileId').val('');
		$('#manufacturerOrgLicenceCopy').val('');
		$("#a_manufacturerOrgLicenceCopy").html('');
		$('.manufacturerOrgLicenceCopy').attr('disabled', true);
		Alert.info(data.message);
		 var partnerManufacturerOrgId=$("#partnerManufacturerOrgId").val();
		   if(partnerManufacturerOrgId!="")
	       {
			   partnerManufacturerOrgArray["org"+partnerManufacturerOrgId].licenceCopy=null;
	       }
	} else {
		Alert.warn(data.message);
	}

}
function manufacturerOrgMachinaryAttachmentDeleteResp(data) {
	if (!data.hasError) {
		$('#manufacturerOrgMachinaryFileId').val('');
		$('#manufacturerOrgMachinaryListCopy').val('');
		$("#a_manufacturerOrgMachinaryCopy").html('');
		$('.manufacturerOrgMachinaryListCopy').attr('disabled', true);
		Alert.info(data.message);
		var partnerManufacturerOrgId=$("#partnerManufacturerOrgId").val();
		   if(partnerManufacturerOrgId!="")
	       {
			   partnerManufacturerOrgArray["org"+partnerManufacturerOrgId].machinaryListCopy=null;
	       }
	} else {
		Alert.warn(data.message);
	}
}
function manufacturerOrgInspectionAttachmentDeleteResp(data) {
	debugger;
	if (!data.hasError) {
		$('#manufacturerOrgInspectionId').val('');
		$('#manufacturerOrgInspectionCopy').val('');
		$("#a_manufacturerOrgInspectionCopy").html('');
		$('.manufacturerOrgInspectionCopy').attr('disabled', true);
		Alert.info(data.message);
		var partnerManufacturerOrgId=$("#partnerManufacturerOrgId").val();
		   if(partnerManufacturerOrgId!="")
	       {
			   partnerManufacturerOrgArray["org"+partnerManufacturerOrgId].inspectionReportCopy=null;
	       }
	} else {
		Alert.warn(data.message);
	}

}
function showInspectionFiles()
{
	debugger;
	var inspectionDone=$("#isOrgInspection").val();

	if(inspectionDone=='Y'){
		$(".manufacturerOrgInspectionDone").css("display","block");
		$("#manufacturerOrgInspectionDate").addClass("requiredDate");
		$("#manufacturerOrgInspectionId").addClass("requiredFile");
		$("#manufacturerOrgInspectionDate").removeAttr("disabled");
		$("#manufacturerOrgInspectionId").removeAttr("disabled");
		$("#manufacturerOrgInspectionReportNo").removeAttr("disabled","disabled");
	}
	else if(inspectionDone=='N'){
		$(".manufacturerOrgInspectionDone").css("display","none");
		$("#manufacturerOrgInspectionDate").removeClass("requiredDate");
		$("#manufacturerOrgInspectionId").removeClass("requiredFile");
				
		$("#manufacturerOrgInspectionDate").attr("disabled","disabled");
		$("#manufacturerOrgInspectionId").attr("disabled","disabled");
		$("#manufacturerOrgInspectionReportNo").attr("disabled","disabled");
	}


}
function manufacturerStaffCopyDelResp(data)
{
	if (!data.hasError) {
		$('#manufacturerListOfStaff').val('');
		$('#manufacturerStaffListCopy').val('');
		$("#a_manufacturerStaffListCopy").html('');
		$('.manufacturerStaffListCopy').attr('disabled', true);
		Alert.info(data.message);
		var partnerManufacturerOrgId=$("#partnerManufacturerOrgId").val();
		   if(partnerManufacturerOrgId!="")
	       {
			   partnerManufacturerOrgArray["org"+partnerManufacturerOrgId].staffListCopy=null;
	       }
	} else {
		Alert.warn(data.message);
	}

}
function manufacturerAuthorizationCertificateDeleteResp(data)
{
	if (!data.hasError) {
		$('#manufacturerAuthorizationCertificateId').val('');
		$('#manufacturerAuthorizationCertificate').val('');
		$("#a_manufacturerAuthorizationCertificate").html('');
		$('.manufacturerAuthorizationCertificate').attr('disabled', true);
		Alert.info(data.message);
		var partnerManufacturerOrgId=$("#partnerManufacturerOrgId").val();
		   if(partnerManufacturerOrgId!="")
	       {
			   partnerManufacturerOrgArray["org"+partnerManufacturerOrgId].authorizationCertificate=null;
	       }
	} else {
		Alert.warn(data.message);
	}
}
function mTestingEquipmentDetailsDeleteResp(data)
{	
	if (!data.hasError) {
		$('#mTestingEquipmentFileId').val('');
		$('#mTestingEquipmentDetails').val('');
		$("#a_mTestingEquipmentDetails").html('');
		$('.mTestingEquipmentDetails').attr('disabled', true);
		Alert.info(data.message);
		var partnerManufacturerOrgId=$("#partnerManufacturerOrgId").val();
		   if(partnerManufacturerOrgId!="")
	       {
			   partnerManufacturerOrgArray["org"+partnerManufacturerOrgId].testingEquipmentDetails=null;
	       }
	} else {
		Alert.warn(data.message);
	}
}