var tahdrArray = new Array();
var url='';
$(document).ready(function() {
			var tahdrCodes;
			var title;
			var department;
			var status;
			var tenderStatus;
			var documentType=$(".documentType").val();
			if(documentType=='Tender'){
				url="value.tenderUrl";
			}else if(documentType=='Auction'){
				url="value.auctionUrl";
			}
			
			/*Onclick of Save button*/
			$(".createTahdr").on("click", function(event) {
				event.preventDefault();
				var documentType=$(".documentType").val();
				if(documentType=='Tender')
					{
				var manufacturer=$('#isManufacturer').is(':checked');
				var trader=$('#isTrader').is(':checked');

				if($("#tahdrType").val()=='PT'){
				if(manufacturer==false && trader==false){
					Alert.warn("Select atleast one checkbox of Manufacturer or Trader.");
				}
				else{
					submitIt("tahdrForm", "createTahdr", "saveTahdrResp");
					return false;
				}
				}
				else if($("#tahdrType").val()=='WT'){
					submitIt("tahdrForm", "createTahdr", "saveTahdrResp");
					return false;
				}
					}
				else if(documentType=='Auction'){
					submitIt("tahdrForm", "createTahdr", "saveTahdrResp");
				}
			});
			/*Onclick of Save button*/

			/*Onclick of cancel button*/
			$('#cancelTahdr').click(function(event) {
				event.preventDefault();
				$('#tahdrForm .save').hide();
				$('#tahdrForm .cancel').hide();
				var activeTahdrId = $('.leftPaneData').find('li.active').attr('id');
				if (activeTahdrId != undefined) {
					loadTahdrToRightPane(tahdrArray["tahdr"+ activeTahdrId]);
						} else
							$('#tahdrForm')[0].reset();
				});
			/*Onclick of cancel button*/
			
			/*Onclick of first tab click*/
			$("#tenderBaseInfoTab").on('click',function(event) {
				event.preventDefault();
				$("#leftPane").removeClass('readonly');
				setCurrentTab(this);
				getTahdrBaseInfo();
				disableDetailChildTab();
			});
			/*Onclick of first tab click*/
			
			
			if ( $( "#pagination-here" ).length ) {
			$('#pagination-here').paginate({
				pageSize:  10,
				dataSource: 'fetchtahdrList',
				responseTo:  'loadTahdrToLeftPane',
				maxVisiblePageNumbers:3,
				searchBoxID : 'searchLiteralId',
				loadOnStartup : true,
			});
			}

});

function getTahdrBaseInfo(){
	$('.pagination-container').remove();
	setPagination(totalPages, selectedPage , maxVisiblePageNumbers);
	if(getChangedFlag()){
		$('.pagination-container').remove();
		$('#tahdrForm').removeClass('errorinput');
		$('#loading-wrapper').show();
		var searchby = $('#searchLiteralId').val();
		var searchMode = $('input[name=filterBy]:checked').val();
		var response='';
		if(searchby != ''){
			response = fetchtahdrList(selectedPage, leftPanePageSize, searchMode, searchby);
			loadTahdrToLeftPane(response.data);
			setPagination(response.objectMap.LastPage, selectedPage , maxVisiblePageNumbers);
		}else{
			response = fetchtahdrList(selectedPage, leftPanePageSize, 'none', 'none');
			loadTahdrToLeftPane(response.data);
			setPagination(response.objectMap.LastPage, selectedPage , maxVisiblePageNumbers);
		}
		
		if($(".dataUrlTypeCode").val()=='getTAHDRByTypeCode' && $(".dataUrl").val()=='tenderPreparationData'){
			$(".addTahd").show();
		}
		else if($(".dataUrlTypeCode").val()=='getTAHDRApprovalByTypeCode' && $(".dataUrl").val()=='tenderApprovalData'){
			$(".addTahd").hide();
		}
		$('#loading-wrapper').fadeOut('slow');
		setChangedFlag(false);
	}else{
		getCacheLi();
	}
	setChildLoadFlag(true);
}

function disableDetailChildTab(){
	$('#impDatesTab').addClass('readonly');
	$('#tenderDocTab').addClass('readonly');
	$('#getTahdrMaterialList').addClass('readonly');
	$('#sectionDocTab').addClass('readonly');
	$('#confirmation').addClass('readonly');
}

function loadAllTabs(tahdrList){
		if(!$.isEmptyObject(tahdrList)){
			loadTahdrToLeftPane(tahdrList);
			$('#tahdrDetailTab').removeClass('readonly');
			$('#impDatesTab').attr("title", "Click on Tender Details First");
			$('#tenderDocTab').attr("title","Click on Tender Details First");
			$('#getTahdrMaterialList').attr("title","Click on Tender Details First");
			$('#sectionDocTab').attr("title","Click on Tender Details First");
			$('#confirmation').attr("title","Click on Tender Details First");
		}else{
			disableDetailChildTab();
			$(".leftPaneData").html("");
			Alert.warn("No Records To Display");
		}
}

function addNewTahdr(event){
	event.preventDefault();
	$(".tahdrId").val("");
	$(".tahdrStatus").val("");
	$('#tahdrForm').find('input,select,textarea').removeClass('errorinput');
	$("#tahdrForm #tahdrCode").removeAttr('readonly');
	$("#tahdrForm .createVersion").css("display","none");
	$('#isAuction').val('N');
	var temp = $("#tahdrType").val();
	document.getElementById("tahdrForm").reset();
	$('#tenderBaseForm').removeClass('readonly');
	$("#tahdrType").val(temp);
	$('#tahdrForm .save').show();
	$('#tahdrForm .cancel').show();
	var documentType=$(".documentType").val();
	setHeaderValues(documentType+"No.: ", documentType+"Title : ", "Department : ", "Status : ");
}

function editTahdr(event){
	event.preventDefault();
	var tahdrId= $(".tahdrId").val();
	$('#tenderBaseForm').removeClass('readonly');
	$('#tahdrForm .save').show();
	$('#tahdrForm .cancel').show();
	
	$("#TAHDRDetail .createVersion").css("display","none");
	$('#tenderDetailForm').removeClass('readonly');
	$('#tenderDatesForm').removeClass('readonly');
	$('#TAHDRDetail .saveTahdrDetailsBtn').show();
	$('#TAHDRDetail .cancel').show();
	$('#TAHDRDates .saveTahdrDatesBtn').show();
	$('#TAHDRDates .cancel').show();
	
	$('#saveSectionDocumentForm').removeClass("readonly");
	$('#saveSectionDocumentForm .save').show();
	$('#saveSectionDocumentForm .cancel').show();
	
	$('#tenderMaterialForm').removeClass('readonly');
	$('#saveTahdrMaterialForm .saveTahdrMaterial').show();
	$('#saveTahdrMaterialForm .cancelTahdrMaterial').show();
	$('#tenderMaterialButtonsId').removeClass('readonly');
	
	$('#tenderStdCustDocsForm').removeClass('readonly');
	$('#saveStdCstDocForm .save').show();
	$('#saveStdCstDocForm .cancel').show();
	
	$("#tenderConfirmationDivId").removeClass('readonly');
	$("#approvalMatrixDiv").removeClass('readonly');
	submitWithParam("getRejectedTahdr", "tahdrId", "tahdrStatusFromRJtoDR");
}

function tahdrStatusFromRJtoDR(data){
/*	var active = "";
	active = "active";
	appendTahdrLi(data.objectMap.tahdrData, active);
*/	
	if ($.isEmptyObject(data.objectMap.tahdrData)) {
		return;
	}
	tahdrCodes=data.objectMap.tahdrData==null?'':data.objectMap.tahdrData.tahdrCode==null?'':data.objectMap.tahdrData.tahdrCode;
	title=data.objectMap.tahdrData==null?'':data.objectMap.tahdrData.title==null?'':data.objectMap.tahdrData.title;
	department=data.objectMap.tahdrData==null?'':data.objectMap.tahdrData.department==null?$("#department option:selected" ).text():data.objectMap.tahdrData.department.name==null?$("#department option:selected" ).text():data.objectMap.tahdrData.department.name;
	status=tahdrStatusList[data.objectMap.tahdrData.tahdrStatusCode];
	tenderStatus=tahdrStatusList[data.objectMap.tahdrData.tahdrStatusCode];
	
	$("#tahdrForm #tahdrCode").attr('readonly','readonly');
	var leftPanelHtml = '';
	var currentTahdrId = $('ul.leftPaneData').find('li.active').attr('id');
	var tahdrId = data.objectMap.tahdrData==null?'':data.objectMap.tahdrData.tahdrId==null?'':data.objectMap.tahdrData.tahdrId;
	if (currentTahdrId == tahdrId) {
		$('#' + currentTahdrId).remove();
	}
	if (currentTahdrId < tahdrId) {
		$('#' + currentTahdrId).removeClass('active');
	}
	$(".tahdrId").val(tahdrId);
	leftPanelHtml = appendTahdrLi(data.objectMap.tahdrData, "active");
	tahdrArray["tahdr" + tahdrId] = data;
	$(".leftPaneData").prepend(leftPanelHtml);

	if ($('.leftPaneData li').length == 0) {
		$('#tahdrDetailTab').addClass('readonly');
		$('#impDatesTab').addClass('readonly');
		$('#tenderDocTab').addClass('readonly');
		$('#getTahdrMaterialList').addClass('readonly');
		$('#sectionDocTab').addClass('readonly');
		$('#confirmation').addClass('readonly');
	} else {
		$('#tahdrDetailTab').removeClass('readonly');
		$('#impDatesTab').attr("title","Click on Tender Details First");
		$('#tenderDocTab').attr("title","Click on Tender Details First");
		$('#getTahdrMaterialList').attr("title","Click on Tender Details First");
		$('#sectionDocTab').attr("title","Click on Tender Details First");
		$('#confirmation').attr("title","Click on Tender Details First");
	}
	var documentType=$(".documentType").val();
	setHeaderValues(documentType+"No.: " + tahdrCodes, documentType+"Title : " + title.substring(0, 100), "Department : " + department, "Status : " + tenderStatus);
}

function appendTahdrLi(tahdr, active) {
	var tahdrCode= tahdr==null?'':tahdr.tahdrCode==null?'':tahdr.tahdrCode;
	var StatusCode = tahdr==null?'':tahdr.tahdrStatusCode==null?'':tahdr.tahdrStatusCode;
	var tahdrTitle= tahdr==null?'':tahdr.title==null?'':tahdr.title;
	var bidTypes= tahdr==null?'':tahdr.bidTypeCode==null?'':tahdr.bidTypeCode;
	var tahdrID=tahdr==null?'':tahdr.tahdrId==null?'':tahdr.tahdrId;
	return appendLiData(tahdrCode,tahdrStatusList[StatusCode], tahdrTitle,bidTypeList[bidTypes], tahdrID, active, 'tahdr');
}

function loadTahdrToLeftPane(tahdrList) {
	$("#leftPane").html("");
	var leftPanelHtml = '';
	var i = 0;
	var active = "";
	var firstRow = null;
	tahdrArray = [];
	if(!isEmpty(tahdrList)){
	$.each(Object.values(tahdrList), function(key, tahdr) {
		
		tahdrArray["tahdr" + tahdr.tahdrId] = tahdr;
		if (i == 0) {
			firstRow = tahdr;
			active = "active";
		}
		leftPanelHtml = leftPanelHtml + appendTahdrLi(tahdr, active);
		active = "";
		i++;
	});
	}
	$("#leftPane").append(leftPanelHtml);
	
	var documentType=$(".documentType").val();
	setLeftPaneHeader(documentType+"List", Object.values(tahdrArray).length);
	loadTahdrToRightPane(firstRow);
	
	
	if ($('.leftPaneData li').length == 0) {
		$('#tahdrDetailTab').addClass('readonly');
		$('#impDatesTab').addClass('readonly');
		$('#tenderDocTab').addClass('readonly');
		$('#getTahdrMaterialList').addClass('readonly');
		$('#sectionDocTab').addClass('readonly');
		$('#confirmation').addClass('readonly');
	} else {
		$('#tahdrDetailTab').removeClass('readonly');
		$('#impDatesTab').attr("title","Click on Tender Details First");
		$('#tenderDocTab').attr("title","Click on Tender Details First");
		$('#getTahdrMaterialList').attr("title","Click on Tender Details First");
		$('#sectionDocTab').attr("title","Click on Tender Details First");
		$('#confirmation').attr("title","Click on Tender Details First");
		
	}
	$("#leftPane").on('click', '.tahdr', function() {
		var id = $(this).attr('id');
		loadTahdrToRightPane(tahdrArray["tahdr" + id]);
	});
	$(".tabs-left li a label").text(function(index, currentText) {
	    return currentText.substr(0, 20);
	});
}

function loadTahdrToRightPane(tahdr){
	if(!$.isEmptyObject(tahdr)){
		$(".tahdrId").val(getValue(tahdr.tahdrId));
		$("#tahdrForm #tahdrCode").val(getValue(tahdr.tahdrCode));
		$("#tahdrForm #tahdrCode").attr('readonly','readonly');
		$("#tahdrForm #tahdrType").val(getValue(tahdr.tahdrTypeCode));
		$("#tahdrForm #bidTypeCode").val(getValue(tahdr.bidTypeCode));
		$('#tahdrForm .tahdrStatus').val(getValue(tahdr.tahdrStatusCode));
		$('#TAHDRDetail #tahdrCode').val(getValue(tahdr.tahdrCode));

		if (!$.isEmptyObject(tahdr.department))
		{
		$("#tahdrForm #department").val(getValue(tahdr.department.departmentId));
		}
		
		$("#tahdrForm #tahdrTitle").val(getValue(tahdr.title));
		$("#tahdrForm #tahdrTitle").attr("title", getValue(tahdr.title));
		$("#tahdrForm #description").val(getValue(tahdr.description));
		$("#tahdrForm #budgetType").val(getValue(tahdr.budgetType));

		if ($("#tahdrForm #budgetType").val() == 'RVN'){
		$('#tahdrForm #budgetType').trigger('change');}
		if ($("#tahdrForm #budgetType").val() == 'CAP'){
		$('#tahdrForm #budgetType').trigger('change');}
		
		if (!$.isEmptyObject(tahdr.officeType)){
		$("#tahdrForm #officeType").val(getValue(tahdr.officeType.locationTypeId));
			loadOfficeLocation();
		if(getValue(tahdr.tahdrTypeCode)=='FA'){
			loadOfficeLocation();
		}
		}
		$("#tahdrForm #schemeName").val(getValue(tahdr.schemeName));
		$("#tahdrForm #schemeCode").val(getValue(tahdr.schemeCode));

		if (!$.isEmptyObject(tahdr.officeLocation)){
		$("#tahdrForm #officeLocation").val(getValue(tahdr.officeLocation.officeLocationId));
		}
		
		if (tahdr.tahdrStatusCode == 'DR'){
			$('#tenderBaseForm').removeClass('readonly');
			$('#tahdrForm .save').show();
			$('#tahdrForm .cancel').show();
			$("#tahdrForm .createVersion").css("display","none");
		}else if(tahdr.tahdrStatusCode == 'RJ'){
			$('#tenderBaseForm').addClass('readonly');
			$('#tahdrForm .save').hide();
			$('#tahdrForm .cancel').hide();
			$(".editTahdr").css("display","inline-block");
			$("#tahdrForm .createVersion").css("display","none");
		}else {
			if(tahdr.tahdrStatusCode == 'PU'){
				$("#tahdrForm .createVersion").css("display","inline-block");
			}else{
				$("#tahdrForm .createVersion").css("display","none");
			}
			$('#tenderBaseForm').addClass('readonly');
			$('#tahdrForm .save').hide();
			$('#tahdrForm .cancel').hide();
			$(".editTahdr").css("display","none");
		}
		
		$('#tahdrForm #isAuction').prop("checked",getCheckBoxVal(tahdr.isAuction));
		onCheckIsAuction();
		$('#tahdrForm #isManufacturer').prop("checked",getCheckBoxVal(tahdr.isManufacturer));
		$('#tahdrForm #isTrader').prop("checked",getCheckBoxVal(tahdr.isTrader));
		
		tahdrCodes=tahdr.tahdrCode==null?'':tahdr.tahdrCode;
		title=tahdr.title==null?'':tahdr.title;
		department=tahdr.department==null?'':tahdr.department.name==null?'':tahdr.department.name;
		status=tahdrStatusList[tahdr.tahdrStatusCode];
		tenderStatus=tahdrStatusList[tahdr.tahdrStatusCode];
		var documentType=$(".documentType").val();
		/*setHeaderValues(documentType+"No.: "+tahdrCodes, documentType+"Title : "+title, "Department : "+department, "Status : "+status);*/
		setHeaderValues(documentType+"No.: "+tahdrCodes, documentType+"Title : "+title.substring(0, 100), "Department : "+department, "Status : "+tenderStatus);
		if(roleDto.value=='VENADM' && tahdr.tahdrId!=null){
			$('#bidderStatusDivId').removeAttr('style');
			var bidderData=fetchList("getBidderByTahdrId/"+tahdr.tahdrId,null);
			var status=bidderData.objectMap.bidder==null?'Not Submitted':bidderData.objectMap.bidder.status;
			var bidderCurrentStatus=bidderStatus[status];
			if(status=='DR' || status=='PRCH'){
				if(bidderData.objectMap.bidder!=null){
					$('#feesStatusDivId').removeAttr('style');
					if(bidderData.objectMap.bidder.tenderPurchase!=null){
						var payment=bidderData.objectMap.bidder.tenderPurchase;
						var tendeFeeStatus=payment.paymentMode=='ISEXEMP'?'EXEMPTED':payment.isFAApproved=='Y'?'APPROVED':'APPROVAL IS PENDING';
						$('#tenderfeesStatusSpanId').html(tendeFeeStatus);
					}
					if(bidderData.objectMap.emdPayment!=null && bidderData.objectMap.emdPayment.length!=0){
						var payment=bidderData.objectMap.emdPayment;
						var emdFeeStatus=payment.paymentMode=='ISEXEMP'?'EXEMPTED':payment.isFAApproved=='Y'?'APPROVED':'APPROVAL IS PENDING';
						$('#emdFeesStatusSpanId').html(emdFeeStatus);
					}else{
						$('#emdFeesStatusSpanId').html('NOT PAID');
					}
				}else{
					$('#feesStatusDivId').attr('style','display: none;');
					$('.feesbidderStatus').html('');
				}
			}else{
				$('#feesStatusDivId').attr('style','display: none;');
			}
			bidderCurrentStatus=bidderCurrentStatus==undefined?'No Status':bidderCurrentStatus;
				$('#bidderStatusSpanId').html(bidderCurrentStatus);
		}else{
			$('.bidderStatusDiv').attr('style','display: none;');
			$('.bidderStatus').html('');
		}
		setChildLoadFlag(true);
	}else{
		if(roleDto.value=='VENADM'){
			$('#bidderStatusDivId').attr('style','display: none;');
			$('#bidderStatusSpanId').html('');
		}
	}
	statusButton(tahdrViewButton, tahdr);
}

function saveTahdrResp(data){
	if(data.response.hasError==false)
	{
		tahdrCodes=data.tahdrCode==null?'':data.tahdrCode;
		title=data.title==null?'':data.title;
		department=data.department==null?$("#department option:selected" ).text():data.department.name==null?$("#department option:selected" ).text():data.department.name;
		status=tahdrStatusList[data.tahdrStatusCode];
		tenderStatus=tahdrStatusList[data.tahdrStatusCode];
		$('#tahdrForm .tahdrStatus').val(getValue(data.tahdrStatusCode));	
		$("#tahdrForm #tahdrCode").attr('readonly','readonly');
		var leftPanelHtml = '';
		var currentTahdrId = $('ul.leftPaneData').find('li.active').attr('id');
		var tahdrId = data.tahdrId==null?'':data.tahdrId;
		if (currentTahdrId == tahdrId) {
			$('#' + currentTahdrId).remove();
		}
		if (currentTahdrId < tahdrId) {
			$('#' + currentTahdrId).removeClass('active');
		}
		$(".tahdrId").val(tahdrId);
		leftPanelHtml = appendTahdrLi(data, "active");
		tahdrArray["tahdr" + tahdrId] = data;
		$(".leftPaneData").prepend(leftPanelHtml);

		if (data.response.hasError) {
			Alert.warn(data.response.message);
		} else {
			Alert.info(data.response.message);
		}

		if ($('.leftPaneData li').length == 0) {
			$('#tahdrDetailTab').addClass('readonly');
			$('#impDatesTab').addClass('readonly');
			$('#tenderDocTab').addClass('readonly');
			$('#getTahdrMaterialList').addClass('readonly');
			$('#sectionDocTab').addClass('readonly');
			$('#confirmation').addClass('readonly');
		} else {
			$('#tahdrDetailTab').removeClass('readonly');
			$('#impDatesTab').attr("title","Click on Tender Details First");
			$('#tenderDocTab').attr("title","Click on Tender Details First");
			$('#getTahdrMaterialList').attr("title","Click on Tender Details First");
			$('#sectionDocTab').attr("title","Click on Tender Details First");
			$('#confirmation').attr("title","Click on Tender Details First");

		}
		
		if($('#isAuction').prop('checked')){
			$("#minBidDifferenceDiv").show();
			$("#minBidDifference").removeAttr('disabled');
			
		}else{
			$("#minBidDifferenceDiv").hide();
			$("#minBidDifference").attr('disabled','disabled');
		}
		var documentType=$(".documentType").val();
		/*setHeaderValues(documentType+"No.: " + tahdrCodes, documentType+"Title : " + title, "Department : " + department, "Status : " + status);*/
		setHeaderValues(documentType+"No.: " + tahdrCodes, documentType+"Title : " + title.substring(0, 100), "Department : " + department, "Status : " + tenderStatus);
		/*$('#leftPane').paginathing({perPage: 10});*/
	} else {
		
			/*if(data.response.hasError){
				Alert.warn(data.response.message);
			}else{
				Alert.info(data.response.message);
			}*/
				if (data.response.hasError) {
					var msg = '';
					$.each(data.response.errors, function(key, value) {
					     msg=msg+'\n'+value.errorMessage +','+ '<br/>';
					       
					   });
					    Alert.warn(msg);
				}
		 
		/*if (data.response.hasError) {
			Alert.warn(data.response.message);
		} else {
			Alert.info(data.response.message);
		}*/
	}
	setChildLoadFlag(true);
}
			
function officeLocationResp(data) {
	
	$("#tahdrForm #officeLocation").html('');
	var options = '<option value="">Select Office Location </option>';
	$.each(data, function(key, value) {
		options += '<option value="' + value.officeLocationId + '">' + value.name + '</option>'
	});

	$("#tahdrForm #officeLocation").append(options);
}

function statusButton(viewButton,tahdrList){

	 
	$("#ButtonView").empty();
	if ($.isEmptyObject(tahdrList)) {

		return;
	}
	var addButton = '';
	$.each(viewButton,function(key,value){
		 
		var urlType=eval(url);
		var tahdrId=tahdrList.tahdrId;
		urlType=urlType+"?tahdrId="+tahdrId;
	 	var date= new Date();
		var longD1=Number(date.getTime());
		//bid
		if(tahdrList.tahdrDetail!=null){
			if(roleDto.value=='VENADM'){
				if(tahdrList.tahdrStatusCode=='PU' && longD1<=tahdrList.tahdrDetail[0].technicalBidToDate && (value.code=='BID')){
					addButton=addButton+'<a href="'+urlType+'"><button class="btn btn-default bluebtn statusButtons"  id="approvalBtn-'+value.code+'" value="'+value.code+'">'+value.name+'</button></a>';
					}
					else if(tahdrList.tahdrStatusCode=='C1SCH' && longD1<=tahdrList.tahdrDetail[0].c1ToDate && (value.code=='C1')){
					addButton=addButton+'<a href="'+urlType+'"><button class="btn btn-default bluebtn statusButtons"  id="approvalBtn-'+value.code+'" value="'+value.code+'">'+value.name+'</button></a>';
					}
					else if(tahdrList.tahdrStatusCode=='DBSCH' && longD1<=tahdrList.tahdrDetail[0].deviationToDate && (value.code=='DVTN')){
					addButton=addButton+'<a href="'+urlType+'"><button class="btn btn-default bluebtn statusButtons"  id="approvalBtn-'+value.code+'" value="'+value.code+'">'+value.name+'</button></a>';
					}
					else if(tahdrList.tahdrStatusCode=='RBSCH' && longD1<=tahdrList.tahdrDetail[0].deviationToDate && (value.code=='REVISED')){
					addButton=addButton+'<a href="'+urlType+'"><button class="btn btn-default bluebtn statusButtons"  id="approvalBtn-'+value.code+'" value="'+value.code+'">'+value.name+'</button></a>';
					}
					else if(tahdrList.tahdrStatusCode=='TCOP' && (value.code=='TCOP')){
					addButton=addButton+'<a href="'+urlType+'"><button class="btn btn-default bluebtn statusButtons"  id="approvalBtn-'+value.code+'" value="'+value.code+'">'+value.name+'</button></a>';
					}
					else if(tahdrList.tahdrStatusCode=='DBOP' && (value.code=='DVOP')){
					addButton=addButton+'<a href="'+urlType+'"><button class="btn btn-default bluebtn statusButtons"  id="approvalBtn-'+value.code+'" value="'+value.code+'">'+value.name+'</button></a>';
					}
					else if(tahdrList.tahdrStatusCode=='PBOP' && (value.code=='PBOP')){
					addButton=addButton+'<a href="'+urlType+'"><button class="btn btn-default bluebtn statusButtons"  id="approvalBtn-'+value.code+'" value="'+value.code+'">'+value.name+'</button></a>';
					}
					else if(tahdrList.tahdrStatusCode=='C1OP' && (value.code=='C1OP')){
						addButton=addButton+'<a href="'+urlType+'"><button class="btn btn-default bluebtn statusButtons"  id="approvalBtn-'+value.code+'" value="'+value.code+'">'+value.name+'</button></a>';
					}
					else if(tahdrList.tahdrStatusCode=='ASCH' && longD1<=tahdrList.tahdrDetail[0].auctionToDate && (value.code=='LIVBID')){
						addButton=addButton+'<a href="'+urlType+'"><button class="btn btn-default bluebtn statusButtons"  id="approvalBtn-'+value.code+'" value="'+value.code+'">'+value.name+'</button></a>';
					}
					
			}
			else if(roleDto.value=='EXEENGR'){
				//opening
				if(tahdrList.tahdrStatusCode=='PU' && longD1>=tahdrList.tahdrDetail[0].techBidOpenningDate  && (value.code=='TCOP')){
				addButton=addButton+'<a href="'+urlType+'"><button class="btn btn-default bluebtn statusButtons" id="approvalBtn-'+value.code+'" value="'+value.code+'">'+value.name+'</button></a>';
				}
				else if(tahdrList.tahdrStatusCode=='PBSCH' && longD1>=tahdrList.tahdrDetail[0].priceBidOpenningDate  && (value.code=='PBOP')){
				addButton=addButton+'<a href="'+urlType+'"><button class="btn btn-default bluebtn statusButtons" id="approvalBtn-'+value.code+'" value="'+value.code+'" >'+value.name+'</button></a>';
				}
				else if(tahdrList.tahdrStatusCode=='C1SCH' && longD1>=tahdrList.tahdrDetail[0].priceBidOpenningDate && (value.code=='C1OP')){
				addButton=addButton+'<a href="'+urlType+'"><button class="btn btn-default bluebtn statusButtons" id="approvalBtn-'+value.code+'" value="'+value.code+'">'+value.name+'</button></a>';
				}
				else if(tahdrList.tahdrStatusCode=='RBSCH' && longD1>=tahdrList.tahdrDetail[0].deviationOpenningDate && (value.code=='DVOP')){
				addButton=addButton+'<a href="'+urlType+'"><button class="btn btn-default bluebtn statusButtons" id="approvalBtn-'+value.code+'" value="'+value.code+'">'+value.name+'</button></a>';
				}
				//scheduling
				else if((tahdrList.tahdrStatusCode=='PBOP' || tahdrList.tahdrStatusCode=='SRCDONE')  && (value.code=='C1SCHD' || value.code=='RBSCHD' || value.code=='AWSCHD')){
				addButton=addButton+'<a href="'+urlType+'"><button class="btn btn-default bluebtn statusButtons" id="approvalBtn-'+value.code+'" value="'+value.code+'" >'+value.name+'</button></a>';
				}
				else if(tahdrList.tahdrStatusCode=='C1OP'   && value.code=='AWSCHD'){
				addButton=addButton+'<a href="'+urlType+'"><button class="btn btn-default bluebtn statusButtons" id="approvalBtn-'+value.code+'" value="'+value.code+'">'+value.name+'</button></a>';
				}
				else if(tahdrList.tahdrStatusCode=='RBOP' && (value.code=='C1SCHD' || value.code=='AWSCHD')){
				addButton=addButton+'<a href="'+urlType+'"><button class="btn btn-default bluebtn statusButtons" id="approvalBtn-'+value.code+'" value="'+value.code+'">'+value.name+'</button></a>';
				}
				else if(tahdrList.tahdrStatusCode=='TCOP' && value.code=='PBSCHD'){
				addButton=addButton+'<a href="'+urlType+'"><button class="btn btn-default bluebtn statusButtons" id="approvalBtn-'+value.code+'" value="'+value.code+'">'+value.name+'</button></a>';
				}
			}else if(roleDto.value=='SCRENGR'){
				    if(tahdrList.tahdrStatusCode=='TCOP'   && value.code=='DBSCHD'){
					addButton=addButton+'<a href="'+urlType+'"><button class="btn btn-default bluebtn statusButtons" id="approvalBtn-'+value.code+'" value="'+value.code+'">'+value.name+'</button></a>';
					}
					if(tahdrList.tahdrStatusCode=='TCOP' && value.code=='PSCRUTINY'){
					addButton=addButton+'<a href="'+urlType+'"><button class="btn btn-default bluebtn statusButtons" id="approvalBtn-'+value.code+'" value="'+value.code+'">'+value.name+'</button></a>';
					}
					if(tahdrList.tahdrStatusCode=='DBOP' && value.code=='FSCRUTINY'){
					addButton=addButton+'<a href="'+urlType+'"><button class="btn btn-default bluebtn statusButtons" id="approvalBtn-'+value.code+'" value="'+value.code+'">'+value.name+'</button></a>';
					}
			}else if(roleDto.value=='AUDIT'){
				    if(tahdrList.tahdrStatusCode=='TCOP'   && value.code=='PSCRUTINY'){
					addButton=addButton+'<a href="'+urlType+'"><button class="btn btn-default bluebtn statusButtons" id="approvalBtn-'+value.code+'" value="'+value.code+'">'+value.name+'</button></a>';
					}
					if(tahdrList.tahdrStatusCode=='DBOP' && value.code=='FSCRUTINY'){
					addButton=addButton+'<a href="'+urlType+'"><button class="btn btn-default bluebtn statusButtons" id="approvalBtn-'+value.code+'" value="'+value.code+'">'+value.name+'</button></a>';
					}

			}
		
		}
	});
	$("#ButtonView").append(addButton);

}

function loadOfficeLocation(){
	
	var optionName = $('#officeType').find('option:selected').text();
	if(optionName!=''){
		submitToURL('getOfficeLocation/' + optionName,'officeLocationResp');
	}
}



function fetchtahdrList(pageNumber, pageSize, searchMode, searchValue){
	var response;
	var url="";
	if($(".dataUrlTypeCode").val()=='getTAHDRByTypeCode' && $(".dataUrl").val()=='tenderPreparationData'){
		url="getTAHDRByTypeCode";
	}
	else if($(".dataUrlTypeCode").val()=='getTAHDRApprovalByTypeCode' && $(".dataUrl").val()=='tenderApprovalData'){
		url="getTAHDRApprovalByTypeCode";
	}
	if(url!=null && url!=""){
	$.ajax({
        type : "POST",
        contentType : "application/json",
        url: url+"?tahdrTypeCode="+$("input[name='tenderTypeCodeToggle']:checked").val()+"&pageNumber="+pageNumber+"&pageSize="+pageSize+"&searchMode="+searchMode+"&searchValue="+searchValue,
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
	else{
		return;
	}
}

function createVersion(event){
	event.preventDefault();
	swal({
		  title: 'All the submitted Bids will be cancelled after version creation, do you still want to proceed ?',
		  type: 'warning',
		  showCancelButton: true,
		  confirmButtonColor: '#3085d6',
		  cancelButtonColor: '#d33',
		  confirmButtonText: 'Yes'
		})
		.then(function(result){
		  if (result.value) {
			  submitWithParamByParamClass('createVersion', 'tahdrId', 'createVersionResp');
		  }
		})
}

function createVersionResp(resp){
	if(!isEmpty(resp) && !isEmpty(resp.response) ){
		if(!resp.response.hasError){
			$('#tenderBaseForm').removeClass('readonly');
			$('#tahdrForm .save').show();
			$('#tahdrForm .cancel').show();
			$("#tahdrForm .createVersion").css("display","none");
			Alert.info("New Vesion Created Successfully");
		}else{
			Alert.warn(resp.response.message);
		}
	}
}

function doConfirm(msg, yesFn, noFn)
{
    var confirmBox = $("#confirmBox");
    confirmBox.find(".message").text(msg);
    confirmBox.find(".yes,.no").unbind().click(function()
    {
        confirmBox.hide();
    });
    confirmBox.find(".yes").click(yesFn);
    confirmBox.find(".no").click(noFn);
    confirmBox.show();
}