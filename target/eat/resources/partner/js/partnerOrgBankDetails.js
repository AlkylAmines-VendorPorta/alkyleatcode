function partnerOrgBankDetailsResp(data) {
	swal(data.responseDto.message);
}

function onpartnerOrgBankDetailsTabClick() {
	submitToURL("getPartner", 'onpartnerOrgBankDetailTabLoad');
}
function onpartnerOrgBankDetailsTabLoad(data) {
	debugger;
	$.each(data, function(key, value) {
		if (key == 'objectMap') {
			var responseMap = value;
			loadPartnerOrgBankDetailsLeftPane(responseMap.companyContacts);
		}
	});
}

function loadPartnerOrgBankDetailsLeftPane(data) {
	$(".leftPaneData").html("");
	var leftPanelHtml = "";
	var i = 0;
	var firstRow = null;
	$.each(data, function(key, value) {
		var firstName = value.firstName == null ? '' : value.firstName;
		var lastName = value.lastName == null ? '' : value.lastName;
		var email = value.email == null ? '' : value.email;
		var mobileNo = value.mobileNo == null ? '' : value.mobileNo;
		if (i == 0) {
			firstRow = value;
			leftPanelHtml = leftPanelHtml + ' <li class="active">';
		} else {
			leftPanelHtml = leftPanelHtml + ' <li>';
		}

		leftPanelHtml = leftPanelHtml + ' <a href="#Master" data-toggle="tab">'
				+ ' <div class="col-md-12">' + '  <label class="col-xs-6"> '
				+ firstName + '</label>' + '	<label class="col-xs-6 ">'
				+ lastName + '</label>' + ' </div>'
				+ ' <div class="col-md-12">' + '	<label class="col-xs-6">'
				+ email + '</label>' + '	<label class="col-xs-6">' + mobileNo
				+ '</label>' + ' </div>' + ' </a>' + ' </li>';
		i = i++;
	});
	$(".leftPaneData").html(leftPanelHtml);
	loadPartnerOrgBankDetailsRightPane(firstRow);
}

function loadPartnerOrgBankDetailsRightPane(data) {

	var firstName = data.firstName == null ? '' : data.firstName;
	var lastName = data.lastName == null ? '' : data.lastName;
	var email = data.email == null ? '' : data.email;
	var mobileNo = data.mobileNo == null ? '' : data.mobileNo;
	var userDetailsId = data.userDetailsId == null ? '' : data.userDetailsId;
	var title = data.title == null ? '' : data.title;
	var middleName = data.middleName == null ? '' : data.middleName;
	var telephone1 = data.telephone1 == null ? '' : data.telephone1;
	var telephone2 = data.telephone2 == null ? '' : data.telephone2;
	var fax1 = data.fax1 == null ? '' : data.fax1;
	var fax2 = data.fax2 == null ? '' : data.fax2;
	var mobileNo = data.mobileNo == null ? '' : data.mobileNo;

	$("#labelFirstName").html('<h4>' + firstName + '</h4>');
	$("#labelLastName").html(lastName);
	$("#labelEmail").html(email);
	$("#labelMobileNo").html(mobileNo);

	$("#compContactForm #userDetailsId").val(userDetailsId);
	$("#compContactForm #title").val(title);
	$("#compContactForm #firstName").val(firstName);
	$("#compContactForm #lastName").val(lastName);
	$("#compContactForm #email").val(email);
	$("#compContactForm #middleName").val(middleName);
	$("#compContactForm #telephone1").val(telephone1);
	$("#compContactForm #telephone2").val(telephone2);
	$("#compContactForm #fax1").val(fax1);
	$("#compContactForm #fax2").val(fax2);
	$("#compContactForm #mobileNo").val(mobileNo);

	/*
	 * $("#compContactForm
	 * locationTypeRef").val(data.locationTypeRef==null?'':data.locationTypeRef);
	 */

}

function loadLocationTypes(data) {
	function loadLocationTypes(data) {
		$("#locationTypeRef").html("");
		var options = "<option>Select Location Type </option>";
		$.each(data, function(key, value) {
			options = options + '<option>'
		});
	}

}