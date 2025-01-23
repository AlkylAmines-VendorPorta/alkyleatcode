var tenderCommitteeArray = new Array();
var versionArray = new Array();
var userArray = new Array();
var bidOpeningType = '';

var leftPanePageSize = 7;
var maxVisiblePageNumbers = 3;
var selectedPage = 1;
var pageSize=7;
var searchMode='none';
var searchValue='none';
var LastPage='';
var tenderType='';
var documentType='Tender or Auction'
$(document).ready(
		function() {
			$('#filterById').addClass('readonly');
			documentType=$(".documentType").val();
			var tahdrName = "";
			var tahdrVersionNumber = "";
			var chairPersionName = "";
			/* submitToURL('getTenderCommittees','tenderCommitteeTabResp'); */

			$('#addCommitteeBtnId').click(function(event) {
				event.preventDefault();
				$('#sessionKeyDivId').attr('style', 'display: none ;');
				var openingType=$("#openingTypeId").find('option:selected').val();
				$('#tenderCommitteForm')[0].reset();
				$("#tenderCommitteeId").val("");
				$("#tenderCommitteForm #tenderId").removeClass("readonly");
				$('#participantTabId').hide();
				setTenderDropDownActive(openingType);
				$(".tenderCommitteeField").removeClass("readonly");
				$(".tenderCommitteeBtn").removeAttr("disabled", 'disabled');
			});

			$('#cancelTenderCommitteeId').click(
					function(event) {
						event.preventDefault();
						var activeCommitteeId = $('.leftPaneData').find(
								'li.active').attr('id');
						if (activeCommitteeId != undefined) {
							showTenderCommittee(activeCommitteeId);
							$('#sessionKeyDivId').removeAttr('style');
						} else
							$('#tenderCommitteForm')[0].reset();

						resetTenderDropDown();
					});
			$('#keyGenerationBtnId').click(function(event) {
						event.preventDefault();
						$("#loading-wrapper").show();
						var tenderCommitteeId = $("#tenderCommitteeId").val();
						if (tenderCommitteeId.trim()!= "" ) {
							submitToURL('generateSessionKey/'+tenderCommitteeId, 'sessionKeyResp');
						} else {
							Alert.warn('No Tender Selected');
						}
						$("#loading-wrapper").fadeOut('slow');
			});
			
			$(".tabs-left li a label").text(function(index, currentText) {
				return currentText.substr(0, 20);
			});
			
			$('#searchTenderBtnId').click(function(event) {
						$('.pagination').html('');
						$("#loading-wrapper").show();
						event.preventDefault();
						var typeCode='TENDER';
						tenderType=typeCode;
						bidOpeningType = $("#openingTypeId").val();
						if (bidOpeningType != '') {
							var data=fetchTenderCommitteeList(1, pageSize, searchMode, searchValue,bidOpeningType,typeCode);
							loadTenderCommitteeList(data);
						    LastPage=data.objectMap.LastPage;
							setPagination(data.objectMap.LastPage, selectedPage , maxVisiblePageNumbers);
							/*submitToURL('getTenderCommittees/' + bidOpeningType+'/'+typeCode,
									'tenderCommitteeTabResp');*/
							$('#tenderCommitteTabId').removeClass('readonly');
						} else {
							Alert.warn('Select Tender Opening Type !');
						}
						$("#loading-wrapper").fadeOut('slow');
					});

			$('#tenderCommitteTabId').click(
					function(event) {
						$('.pagination').html('');
						$("#loading-wrapper").show();
						event.preventDefault();
						var typeCode='TENDER';
						tenderType=typeCode;
						if (bidOpeningType != '') {
							var data=fetchTenderCommitteeList(1, pageSize, searchMode, searchValue,bidOpeningType,typeCode);
							loadTenderCommitteeList(data);
							 LastPage=data.objectMap.LastPage;
							setPagination(data.objectMap.LastPage, selectedPage , maxVisiblePageNumbers);
							/*submitToURL(
									'getTenderCommittees/' + bidOpeningType+'/'+typeCode,
									'tenderCommitteeTabResp');*/
						} else {
							Alert.warn('Select Tender Opening Type !');
						}
						$("#loading-wrapper").fadeOut('slow');
					});
			
			$('#searchAuctionBtnId').click(
					function(event) {
						$('.pagination').html('');
						$("#loading-wrapper").show();
						event.preventDefault();
						var typeCode='AUCTION';
						tenderType=typeCode;
						bidOpeningType = $("#openingTypeId").val();
						if (bidOpeningType != '') {
							var data=fetchTenderCommitteeList(1, pageSize, searchMode, searchValue,bidOpeningType,typeCode);
							loadTenderCommitteeList(data);
							 LastPage=data.objectMap.LastPage;
							setPagination(data.objectMap.LastPage, selectedPage , maxVisiblePageNumbers);
							/*submitToURL(
									'getTenderCommittees/' + bidOpeningType+'/'+typeCode,
									'tenderCommitteeTabResp');*/
							$('#auctionCommitteTabId').removeClass('readonly');
						} else {
							Alert.warn('Select Tender Opening Type !');
						}
						$("#loading-wrapper").fadeOut('slow');
					});

			$('#auctionCommitteTabId').click(
					function(event) {
						$('.pagination').html('');
						$("#loading-wrapper").show();
						event.preventDefault();
						var typeCode='AUCTION';
						tenderType=typeCode;
						if (bidOpeningType != '') {
							var data=fetchTenderCommitteeList(1, pageSize, searchMode, searchValue,bidOpeningType,typeCode);
							loadTenderCommitteeList(data);
							 LastPage=data.objectMap.LastPage;
							setPagination(data.objectMap.LastPage, selectedPage , maxVisiblePageNumbers);
							/*submitToURL(
									'getTenderCommittees/' + bidOpeningType+'/'+typeCode,
									'tenderCommitteeTabResp');*/
						} else {
							Alert.warn('Select Tender Opening Type !');
						}
						$("#loading-wrapper").fadeOut('slow');
					});

			$('#resetBtnId').click(function(event) {
				event.preventDefault();
				$('#tenderCommitteForm')[0].reset();
				$("#tenderCommitteeId").val("");
				$("#tenderCommitteForm #tenderId").removeClass("readonly");
				return false;
			});
			
			$('#pagination-here').paginate({
				pageSize:  7,
				dataSource: 'fetchTenderCommitteeList',
				responseTo:  'loadTenderCommitteeList',
				maxVisiblePageNumbers:3,
				searchBoxID : 'searchLiteralId',
				loadOnStartup: false,
				defaultNextPage:false,
				defaultSearch:false,
				defaultManualSearch:false
			});
			
        		$(document).on("page", function(event, num){
            		selectedPage = num;
            		var searchby = $('#searchLiteralId').val();
            		var searchMode = $('input[name=filterBy]:checked').val();
            		bidOpeningType = $("#openingTypeId").val();
            		if(searchby != '' && searchby!=undefined && bidOpeningType!=''){
            			var data=fetchTenderCommitteeList(num, pageSize, searchMode, searchValue,bidOpeningType,tenderType);
            			loadTenderCommitteeList(data);
            			 LastPage=data.objectMap.LastPage;
							setPagination(data.objectMap.LastPage, selectedPage , maxVisiblePageNumbers);
            		}else{
            			var data=fetchTenderCommitteeList(num, pageSize, searchMode, searchValue,bidOpeningType,tenderType);
						loadTenderCommitteeList(data);
						 LastPage=data.objectMap.LastPage;
							setPagination(data.objectMap.LastPage, selectedPage , maxVisiblePageNumbers);
            		}
            	});
        		$('#searchLiteralId').keyup(function(){
            		
            		isServerSidePaginationEmpty = $('#pagination-here').is(':empty');
            		if(!isServerSidePaginationEmpty){
            			var searchby = $('#searchLiteralId').val();
            			bidOpeningType = $("#openingTypeId").val();
            			if(searchby.length >=3 ){
            				var searchMode = $('input[name=filterBy]:checked').val();
            				if(searchMode != undefined && searchby!='' && searchby!=undefined){
            					
            					var data=fetchTenderCommitteeList(selectedPage, pageSize, searchMode, searchby,bidOpeningType,tenderType);
                    			loadTenderCommitteeList(data);
                    			 LastPage=data.objectMap.LastPage;
     							setPagination(data.objectMap.LastPage, selectedPage , maxVisiblePageNumbers);
                    			
            				}else{
            					Alert.warn('Select Filter Mode');
            				}
            			}
            		}
            	});
        		
        		$('#searchBtn').click(function( e ){
        			
					isServerSidePaginationEmpty = $('#pagination-here').is(':empty');
	        		if(!isServerSidePaginationEmpty){
	        			var searchby = $('#searchLiteralId').val();
	        			bidOpeningType = $("#openingTypeId").val();
	        			var searchMode = $('input[name=filterBy]:checked').val();
	        			if(searchMode != undefined && searchby!='' && searchby!=undefined){
	        				var data=fetchTenderCommitteeList(selectedPage, pageSize, searchMode, searchby,bidOpeningType,tenderType);
                			loadTenderCommitteeList(data);
                			 LastPage=data.objectMap.LastPage;
 							setPagination(data.objectMap.LastPage, selectedPage , maxVisiblePageNumbers);
	        			}else{
	        				var data=fetchTenderCommitteeList(selectedPage, pageSize, 'none', 'none',bidOpeningType,tenderType);
                			loadTenderCommitteeList(data);
                			 LastPage=data.objectMap.LastPage;
 							setPagination(data.objectMap.LastPage, selectedPage , maxVisiblePageNumbers);
	        			}
	        		}
        	});
		});
function setTenderDropDownActive(bidOpeningType) {
	
	if (bidOpeningType == 'TCO') {
		$("#tenderCommitteForm #tenderId").find('[data-status !="PU"]').hide();
		
		$("#tenderCommitteForm #tenderId").find('[data-status !="PU"]').attr('disabled','disabled');
		
		$("#tenderCommitteForm #tenderId").find('[data-bidtype ="SB"]').hide();
		
		$("#tenderCommitteForm #tenderId").find('[data-bidtype ="SB"]').attr('disabled','disabled');
	} else if (bidOpeningType == 'DBO') {
		$("#tenderCommitteForm #tenderId").find('[data-status !="DBSCH"]').hide();

		$("#tenderCommitteForm #tenderId").find('[data-status !="DBSCH"]').attr('disabled','disabled');
        $("#tenderCommitteForm #tenderId").find('[data-bidtype ="SB"]').hide();
		
		$("#tenderCommitteForm #tenderId").find('[data-bidtype ="SB"]').attr('disabled','disabled');
	} else if (bidOpeningType == 'PBO') {
		$("#tenderCommitteForm #tenderId").find('[data-status !="PBSCH"]').hide();

		$("#tenderCommitteForm #tenderId").find('[data-status !="PBSCH"]').attr('disabled','disabled');
	} else if (bidOpeningType == 'C1O') {
		$("#tenderCommitteForm #tenderId").find('[data-status !="C1SCH"]').hide();

		$("#tenderCommitteForm #tenderId").find('[data-status !="C1SCH"]').attr('disabled','disabled');
	} else if (bidOpeningType == 'RBO') {
		$("#tenderCommitteForm #tenderId").find('[data-status !="RBSCH"]').hide();

		$("#tenderCommitteForm #tenderId").find('[data-status !="RBSCH"]').attr('disabled','disabled');
	}
	
}
function resetTenderDropDown() {
		$("#tenderCommitteForm #tenderId").find('[data-status !="PU"]').show();
		
		$("#tenderCommitteForm #tenderId").find('[data-status !="PU"]').removeAttr('disabled');
		$("#tenderCommitteForm #tenderId").find('[data-status !="DBSCH"]').show();

		$("#tenderCommitteForm #tenderId").find('[data-status !="DBSCH"]').removeAttr('disabled');
		$("#tenderCommitteForm #tenderId").find('[data-status !="PBSCH"]').show();

		$("#tenderCommitteForm #tenderId").find('[data-status !="PBSCH"]').removeAttr('disabled');
		$("#tenderCommitteForm #tenderId").find('[data-status !="C1SCH"]').show();

		$("#tenderCommitteForm #tenderId").find('[data-status !="C1SCH"]').removeAttr('disabled');
	$("#tenderCommitteForm #tenderId").find('[data-status !="RBSCH"]').show();

	$("#tenderCommitteForm #tenderId").find('[data-status !="RBSCH"]').removeAttr('disabled');
	$("#tenderCommitteForm #tenderId").find('[data-bidtype ="SB"]').show();
	
	$("#tenderCommitteForm #tenderId").find('[data-bidtype ="SB"]').removeAttr('disabled');
}

function sessionKeyResp(data) {
	if (!$.isEmptyObject(data)) {
		if (data.objectMap.resultStatus) {
			Alert.info(data.objectMap.resultMessage);
		} else {
			Alert.warn(data.objectMap.resultMessage)
		}
	}

}

function loadTenderCommitteeList(data){
	var data=data.data;
	tenderCommitteeTabResp(data);
}
function tenderCommitteeTabResp(data) {
	if(data==undefined){
		return;
	}
	if (data.hasOwnProperty('tahdr')) {
		loadTAHDR(data.tahdr);
		userArray["tahdr"] = data.tahdr;
	}
	if (data.hasOwnProperty('tenderCommittees')) {
		if (!$.isEmptyObject(data.tenderCommittees)) {
			$('#filterById').removeClass('readonly');
			loadTenderCommitteeLeftPane(data.tenderCommittees);
			$('#tenderCommitteeDivId').removeClass('readonly');

			$('#participantTabId').show();
		} else {
			$('#filterById').addClass('readonly');
			$('#tenderCommitteForm')[0].reset();
			$("#tenderCommitteeId").val("");
			$("#tenderCommitteForm #tenderId").removeClass("readonly");
			
			setHeaderValues("", "", "", "");
			
			$('#participantTabId').hide();
			$(".leftPaneData").html("");
			$('#tenderCommitteeDivId').removeClass('readonly');
		}
	}
	/* setActiveTabName("Tender Committee",$('.leftPaneData li').length); */
}

function getTenderVersion(el) {
	var tahdrId = $("#tenderCommitteForm #tenderId").val();
	var data = versionArray["version" + tahdrId];
	$("#tenderCommitteForm #hiddenTenderVersionId").val(data[0].tahdrDetailId);
	$("#tenderCommitteForm #tenderVersionId").val(data[0].version);

	getEEUsersForChairPerson(tahdrId);
	setBidOpening(el);
}

function setBidOpening(el) {
	var status = $(el).find('option:selected').data('status');
	var bidType = $(el).find('option:selected').data('bidtype');
	var bidOpening=bidOpeningType;
	if (status == 'PU' && bidType == 'SB') {
		$('#bidOpeningTypeId').val('PBO');
		bidOpening='PBO';
	} else {
		$('#bidOpeningTypeId').val(bidOpeningType);
	}
	setBidOpeningView(bidOpening);	
}

function setBidOpeningView(bidOpening){
	switch (bidOpening) {
    case 'TCO':
    	$('#viewBidOpeningTypeId').val('Techno-Commercial Opening');
        break;
    case 'DBO':
    	$('#viewBidOpeningTypeId').val('Deviation Bid Opening');
        break;
    case 'PBO':
    	$('#viewBidOpeningTypeId').val('Price Bid Opening');
        break;
    case 'C1O':
    	$('#viewBidOpeningTypeId').val('Annexure C1 Opening');
        break;
    case 'RBO':
    	$('#viewBidOpeningTypeId').val('Price Bid Opening');
        break;
    default:
    	$('#viewBidOpeningTypeId').val('');
}
}

function getEEUsersForChairPerson(tahdrId) {
	$("#tenderCommitteForm #tenderId").val(tahdrId);
	submitWithParam('getUserForCommittee', 'tenderCommitteForm #tenderId', 'loadUsers');
}

function loadUsers(data) {
	$("#tenderCommitteForm #chairPersonId").html("");
	var options = '<option value="">Select Chair Person</option>';
	if (!$.isEmptyObject(data.objectMap.users)) {
		$.each(data.objectMap.users, function(key, value) {
			options += '<option value="' + value.userId + '">'
					+ value.userDetails.firstName + " "
					+ value.userDetails.lastName + " "
					+ value.userDetails.designation.name + '</option>'

		});
	}
	$("#tenderCommitteForm #chairPersonId").append(options);
}

function loadTAHDR(data) {
	$("#tenderCommitteForm #tenderId").html("");
	var options = '<option value="">Select Tender</option>';
	$.each(data, function(key, value) {
		versionArray["version" + value.tahdrId] = value.tahdrDetail;
		options += '<option value="' + value.tahdrId + '" data-bidType="'
				+ value.bidTypeCode + '" data-status="' + value.tahdrStatusCode
				+ '">' + value.tahdrCode + '</option>'

	});
	$("#tenderCommitteForm #tenderId").append(options);
}

function loadVersion(data) {
	$("#tenderCommitteForm #tenderVersionId").html("");
	var options = '<option value="">Select Tender Version</option>';
	$.each(data, function(key, value) {
		options += '<option value="' + value.tahdrDetailId + '">'
				+ value.version + '</option>'
	});
	$("#tenderCommitteForm #tenderVersionId").append(options);

}

function loadTenderCommitteeLeftPane(data) {
	if (data.length == 0) {
		$('#tenderCommitteForm')[0].reset();
		$("#tenderCommitteeId").val("");
		$("#tenderCommitteForm #tenderId").removeClass("readonly");
		$('#participantTabId').removeClass('readonly');
		$('#participantTabId').addClass('readonly');
		/*$('#tenderCommitteeDivId').addClass('readonly');*/
		return;
	}else{
		$('#participantTabId').removeClass('readonly');
	}
	/*$('.pagination').children().remove();*/
	$(".leftPaneData").html("");
	var leftPanelHtml = "";
	var i = 0;
	var active = false;
	var firstRow = null;
	$.each(data, function(key, value) {
		var tenderCommitteeId = value.tenderCommitteeId == null ? ''
				: value.tenderCommitteeId;
		tenderCommitteeArray["tenderCommittee" + tenderCommitteeId] = value;
		if (i == 0) {
			firstRow = value;
			active = true;
		}
		leftPanelHtml = leftPanelHtml
				+ appendTenderCommitteeData(value, active);
		active = false;
		i++;

	});
	$(".leftPaneData").append(leftPanelHtml);
	/*$('.leftPaneData').paginathing({
		perPage : 7
	});*/
	/* plugin.init(); */
	$(".tabs-left li a label").text(function(index, currentText) {
		return currentText.substr(0, 20);
	});
	loadTenderCommitteeRightPane(firstRow);
}

function appendTenderCommitteeData(data, active) {
	var leftPanelHtml = '';
	var tenderCommitteeId = data.tenderCommitteeId == null ? ''
			: data.tenderCommitteeId;
	var tahdrId = data.tahdr == null ? '' : data.tahdr.tahdrId == null ? ''
			: data.tahdr.tahdrId;
	var tahdrCode = data.tahdr == null ? '' : data.tahdr.tahdrCode == null ? ''
			: data.tahdr.tahdrCode;
	var tahdrDetailId = data.tenderVersion == null ? ''
			: data.tenderVersion.tahdrDetailId == null ? ''
					: data.tenderVersion.tahdrDetailId;
	var tahdrDetailVersion = data.tenderVersion == null ? ''
			: data.tenderVersion.version == null ? ''
					: data.tenderVersion.version;
	var firstName = data.chairPerson == null ? ''
			: data.chairPerson.userDetails == null ? ''
					: data.chairPerson.userDetails.firstName == null ? ''
							: data.chairPerson.userDetails.firstName;
	var lastName = data.chairPerson == null ? ''
			: data.chairPerson.userDetails == null ? ''
					: data.chairPerson.userDetails.lastName == null ? ''
							: data.chairPerson.userDetails.lastName;

	if (active) {
		leftPanelHtml = leftPanelHtml
				+ ' <li class="active" onclick="showTenderCommittee('
				+ tenderCommitteeId + ')" id="' + tenderCommitteeId + '">';
	} else {
		leftPanelHtml = leftPanelHtml + ' <li onclick="showTenderCommittee('
				+ tenderCommitteeId + ')" id="' + tenderCommitteeId + '">';
	}
	leftPanelHtml = leftPanelHtml + ' <a href="#Master" data-toggle="tab">'
			+ ' <div class="col-md-12">'
			+ '  <label class="col-xs-6" id="codeLabel-' + tenderCommitteeId
			+ '"> ' + tahdrCode + '</label>'
			+ '	<label class="col-xs-6" id="verionLabel-' + tenderCommitteeId
			+ '" >' + tahdrDetailVersion + '</label>' + ' </div>'
			+ ' <div class="col-md-12">'
			+ '	<label class="col-xs-6" id="userName-' + tenderCommitteeId
			+ '" >' + firstName + lastName + '</label>' + ' </div>' + ' </a>'
			+ ' </li>';

	return leftPanelHtml;

}

function loadTenderCommitteeRightPane(data) {
	if (data == null) {
		return;
	}
	var tenderCommitteeId = data.tenderCommitteeId == null ? ''
			: data.tenderCommitteeId;
	var tahdrId = data.tahdr == null ? '' : data.tahdr.tahdrId == null ? ''
			: data.tahdr.tahdrId;
	var tahdrCode = data.tahdr == null ? '' : data.tahdr.tahdrCode == null ? ''
			: data.tahdr.tahdrCode;
	var bidTypeCode = data.tahdr == null ? '' : data.tahdr.bidTypeCode == null ? ''
			: data.tahdr.bidTypeCode;
	
	var tahdrStatus = data.tahdr == null ? ''
			: data.tahdr.tahdrStatusCode == null ? ''
					: data.tahdr.tahdrStatusCode;
	var tahdrDetailId = data.tenderVersion == null ? ''
			: data.tenderVersion.tahdrDetailId == null ? ''
					: data.tenderVersion.tahdrDetailId;
	var tahdrVersion = data.tenderVersion == null ? ''
			: data.tenderVersion.version == null ? ''
					: data.tenderVersion.version;
	var tahdrDetailVersion = data.tenderVersion == null ? ''
			: data.tenderVersion.version == null ? ''
					: data.tenderVersion.version;
	var userId = data.chairPerson == null ? ''
			: data.chairPerson.userId == null ? '' : data.chairPerson.userId;
	var firstName = data.chairPerson == null ? ''
			: data.chairPerson.userDetails == null ? ''
					: data.chairPerson.userDetails.firstName == null ? ''
							: data.chairPerson.userDetails.firstName;
	var lastName = data.chairPerson == null ? ''
			: data.chairPerson.userDetails == null ? ''
					: data.chairPerson.userDetails.lastName == null ? ''
							: data.chairPerson.userDetails.lastName;
	var bidOpeningTypeId = data.bidOpeningType == null ? ''
			: data.bidOpeningType;

	$("#tenderCommitteForm #tenderCommitteeId").val(tenderCommitteeId);
	$(".tenderCommitteeId").val(tenderCommitteeId);
	$("#tenderCommitteForm #tenderVersionId").val(tahdrVersion);
	$("#tenderCommitteForm #hiddenTenderVersionId").val(tahdrDetailId);
	$("#tenderCommitteForm #bidOpeningTypeId").val(bidOpeningTypeId);
	setBidOpeningView(bidOpeningTypeId);
	if (tahdrStatus == "PU" || tahdrStatus == "DBSCH" || tahdrStatus == "PBSCH"
			|| tahdrStatus == "C1SCH" || tahdrStatus == "RBSCH") {
		$("#tenderCommitteForm #tenderId").val(tahdrId);
		$(".tenderCommitteeField").removeClass("readonly");
		$(".tenderCommitteeBtn").removeAttr("disabled", 'disabled');

	} else {
		/* $("#tenderCommitteForm #tenderId").empty(); */
		$("#tenderCommitteForm #tenderId").append(
				'<option value="' + tahdrId + '" data-bidType="'
				+ bidTypeCode + '" data-status="' + tahdrStatus
				+ '">' + tahdrCode + '</option>');
		$("#tenderCommitteForm #tenderId").val(tahdrId);
		$(".tenderCommitteeField").addClass("readonly");
		$(".tenderCommitteeBtn").attr("disabled", 'disabled');
	}
	getEEUsersForChairPerson(tahdrId);
	$("#tenderCommitteForm #chairPersonId").val(userId);
	$("#tenderCommitteForm #tenderId").addClass("readonly");
	$('#participantTabId').show();
	$("#tenderCommitteForm  select").removeClass("errorinput");
	chairPersionName = firstName + " " + lastName;
	tahdrName = tahdrCode;
	tahdrVersionNumber = tahdrVersion;
	setHeaderValues(documentType+" Name:" + tahdrName, documentType+" version : " + tahdrVersionNumber, "Chairperson : " + chairPersionName, "");
}

function showTenderCommittee(id) {
	resetTenderDropDown();
	var data = tenderCommitteeArray["tenderCommittee" + id];
	loadTenderCommitteeRightPane(data);
	$("#tenderCommitteForm #tenderId").addClass("readonly");
	$('#sessionKeyDivId').removeAttr('style');
}

function tenderCommitteeDelResp(data) {
	var currentTenderCommitteeId = $('ul.leftPaneData').find('li.active').attr(
			'id');
	if (data.hasError == false) {
		$('#' + currentTenderCommitteeId).remove();
		$('#tenderCommitteForm')[0].reset();
		$("#tenderCommitteeId").val("");
		$(".tenderCommitteeField").removeClass("readonly");
		$(".tenderCommitteeBtn").removeAttr("disabled", 'disabled');
		if ($('.leftPaneData li').length == 0) {
			$('#participantTabId').hide();
		}
		Alert.info(data.message);
	} else {
		Alert.warn(data.message);
	}
}

function tenderCommitteeResp(data) {
	$('#participantTabId').show();
	if (!data.response.hasError) {
		var firstName = data.chairPerson == null ? ''
				: data.chairPerson.userDetails == null ? ''
						: data.chairPerson.userDetails.firstName == null ? ''
								: data.chairPerson.userDetails.firstName;
		var lastName = data.chairPerson == null ? ''
				: data.chairPerson.userDetails == null ? ''
						: data.chairPerson.userDetails.lastName == null ? ''
								: data.chairPerson.userDetails.lastName;
		var currentTenderCommitteeId = $('ul.leftPaneData').find('li.active')
				.attr('id');
		var leftPanelHtml = "";
		var status = true;
		var tenderCommitteeId = data.tenderCommitteeId;
		$('#tenderCommitteForm #tenderCommitteeId').val(tenderCommitteeId);
		$('.tenderCommitteeId').val(tenderCommitteeId);
		if (currentTenderCommitteeId == tenderCommitteeId) {
			$('#' + currentTenderCommitteeId).remove();
		} else {
			$('#' + currentTenderCommitteeId).removeClass('active');
		}
		leftPanelHtml = appendTenderCommitteeData(data, status);
		$(".leftPaneData").prepend(leftPanelHtml);
		data.tenderVersion.version = $("#tenderCommitteForm #tenderVersionId")
				.val();
		tenderCommitteeArray["tenderCommittee" + tenderCommitteeId] = data;
		getDropDwonValueForLabel(tenderCommitteeId);
		$("#tenderCommitteForm #tenderId").addClass("readonly");
		$('#participantTabId').show();
		$("#tenderCommitteForm  select").removeClass("errorinput");
		$(".tenderCommitteeField").removeClass("readonly");
		$(".tenderCommitteeBtn").removeAttr("disabled", 'disabled');
		/*$("#tenderCommitteForm #tenderId").removeClass("readonly");*/
		status = false;
		Alert.info(data.response.message);
		$('#sessionKeyDivId').removeAttr('style');
		setHeaderValues(
				"Tender : "
						+ $("#tenderCommitteForm #tenderId option:selected")
								.text(),
				"Tender Version : "
						+ $("#tenderCommitteForm #tenderVersionId").val(),
				"Chairperson : "
						+ $(
								"#tenderCommitteForm #chairPersonId option:selected")
								.text(), "");
	} else {
		Alert.warn(data.response.message);
	}
}

function getDropDwonValueForLabel(id) {
	
	$("#codeLabel-" + id).html(
			$("#tenderCommitteForm #tenderId option:selected").text());
	$("#verionLabel-" + id).html(
			$("#tenderCommitteForm #tenderVersionId").val());
	$("#userName-" + id).html(
			$("#tenderCommitteForm #chairPersonId option:selected").text());
}

function fetchTenderCommitteeList(pageNumber, pageSize, searchMode, searchValue,openingType,typecode){
	var response;
	$.ajax({
        type : "POST",
        contentType : "application/json",
        url: "fetchTenderCommittee?pageNumber="+pageNumber+"&pageSize="+pageSize+'&searchMode='+searchMode+'&serachValue='+searchValue+'&openingType='+bidOpeningType+'&typeCode='+tenderType,
        dataType:"json",
        async:false,
        success: function (data) {
        	response = data;
        },
        error: function (e) {
			Alert.warn(e.message);
        }
    });
	return response;
}

