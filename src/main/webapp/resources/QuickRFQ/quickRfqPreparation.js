var tahdrMaterial;
var bidSection;
var tahdrStatusList;
var tahdrViewButton = new Array();
var roleDto;
var comencementPeriod;
var dataUrlTypeCode;
var dataUrl;
var leftPanePageSize = 10;
var maxVisiblePageNumbers = 3;
var selectedPage = 1;
var bpartnerarray = [];
var tahdrArray = new Array();
var initCount=
function processResponse(data){

	swal(data.response.message);
	responseData=data;
	
}
$(document).ready(function(){
	/*if ( $( "#pagination-here" ).length ) {
		$('#pagination-here').paginate({
			pageSize:  10,
			dataSource: 'fetchQuickAuctionList',
			responseTo:  'loadQuickAuctionToLeftPane',
			maxVisiblePageNumbers:3,
			searchBoxID : 'searchLiteralId',
			loadOnStartup : true,
		});
		}*/
	$('.addItemPopup').click(function(event){
		event.preventDefault();
		$('.commenSearchModal').modal('show');
		$(".itemTable").DataTable().destroy();
		$(".itemTable tbody").empty();
		populateMaterialGroupList(fetchList("getMaterialGroupList",null));/*
		populateSubGroupList(fetchList("getMaterialSubGroupList",null));*/
		
	});
	dataUrl=$(".dataUrl").val();
	loadQuickAuctionListDropDowns(dataUrl+"?tenderTypeCode="+$("input[name='tenderTypeCodeToggle']:checked").val()+"&pageNumber=1&pageSize="+leftPanePageSize+"&searchMode=none&searchValue=none");
	
});

function createQuickRfq(event) {
	event.preventDefault();
	submitIt("tahdrForm", "createQuickRfq", "saveQuickAuctionResp");
}

function cancel(event) {
	
	event.preventDefault();
	$('#tahdrForm .save').hide();
	$('#tahdrForm .cancel').hide();
	var activeTahdrId = $('.leftPaneData').find('li.active').attr('id');
	if (activeTahdrId != undefined) {
		loadQuickAuctionToRightPane(tahdrArray["tahdr"+ activeTahdrId]);
	} else{
		$('#tahdrForm')[0].reset();
	}
}

function fetchQuickAuctionList(pageNumber, pageSize, searchMode, searchValue){
	debugger;
	var response;
	var url="getQuickAuctionByTypeCode";
	
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

function loadQuickAuctionToLeftPane(tahdrList) {
	$("#leftPane").html("");
	var leftPanelHtml = '';
	var i = 0;
	var active = "";
	var firstRow = null;
	tahdrArray = [];
	$.each(Object.values(tahdrList), function(key, tahdr) {
		
		tahdrArray["tahdr" + tahdr.tahdrId] = tahdr;
		if (i == 0) {
			firstRow = tahdr;
			active = "active";
		}
		leftPanelHtml = leftPanelHtml + appendQuickAuctionLi(tahdr, active);
		active = "";
		i++;
	});
	$("#leftPane").append(leftPanelHtml);
	
	var documentType=$(".documentType").val();
	setLeftPaneHeader(documentType+"List", Object.values(tahdrArray).length);
	loadQuickAuctionToRightPane(firstRow);
	
	$("#leftPane").on('click', '.tahdr', function() {
		var id = $(this).attr('id');
		loadQuickAuctionToRightPane(tahdrArray["tahdr" + id]);
	});
	$(".tabs-left li a label").text(function(index, currentText) {
	    return currentText.substr(0, 20);
	});
}

function loadQuickAuctionToRightPane(tahdr){
	debugger;
	if(!$.isEmptyObject(tahdr)){
		var tahdrDetail=tahdr.tahdrDetail[0];
		$(".tahdrId").val(tahdr.tahdrId);
		$("#tahdrForm #tahdrCode").val(getValue(tahdr.tahdrCode));
		$("#tahdrForm #tahdrCode").attr('readonly','readonly');
		$("#tahdrForm #tahdrType").val(getValue(tahdr.tahdrTypeCode));
		$("#tahdrForm #bidTypeCode").val(getValue(tahdr.bidTypeCode));
		$("#tahdrForm #CreatePrivateAuction").prop('checked',getCheckBoxVal(tahdr.isPrivateAuction));
		$("#CreatePrivateAuction").trigger('change');
		$("#tahdrForm #tahdrTitle").val(getValue(tahdr.title));
		$("#tahdrForm #tahdrTitle").attr("title", getValue(tahdr.title));
		$("#tahdrForm #description").val(getValue(tahdr.description));
		
		if(!$.isEmptyObject(tahdrDetail)){
			$("#tahdrForm #estimatedCost").val(getValue(tahdrDetail.estimatedCost));
			$(".tahdrDetailId").val(getValue(tahdrDetail.tahdrDetailId));
			$("#tahdrForm #contactEmailId").val(getValue(tahdrDetail.contactEmailId));
			$("#tahdrForm #contactPersonName").val(getValue(tahdrDetail.contactPersonName));
			$("#tahdrForm #contactPersonNo").val(getValue(tahdrDetail.contactPersonNo));
			$("#tahdrForm #description").val(getValue(tahdrDetail.description));
			
			
			$("#tahdrForm #minQuantity").val(getValue(tahdrDetail.minQuantity));
			$("#tahdrForm #commencementPeriod").val(getValue(tahdrDetail.commencementPeriod));
			$("#deliveryDuration").val(tahdrDetail.deliveryDuration);
			$("#commencementPeriodMonth").val(tahdrDetail.commencementPeriodCode);
			$("#auctionStartToDates").val(formatDateTime(tahdrDetail.technicalBidFromDate));
			$("#auctionEndToDates").val(formatDateTime(tahdrDetail.technicalBidToDate));
			
			$("#version").val(tahdrDetail.version);
			/*if(tahdrDetail.isActive=='Y' && tenderStatus=='Published'){
				$("#TAHDRDetail .createVersion").css("display","block");
				$("#tahdrForm .editTahdrDate").css("display","block");
			}
			else{
				$("#TAHDRDetail .createVersion").css("display","none");
				$("#tahdrForm .editTahdrDate").css("display","none");
			}*/
		}
		
		if (tahdr.tahdrStatusCode == 'DR'){
			$('#tenderBaseForm').removeClass('readonly');
			$('#tahdrForm .save').show();
			$('#tahdrForm .cancel').show();
			$("#tahdrForm .createVersion").css("display","none");
		}else {
			if(tahdr.tahdrStatusCode == 'PU'){
				$("#tahdrForm .createVersion").css("display","block");
			}else{
				$("#tahdrForm .createVersion").css("display","none");
			}
			$('#tenderBaseForm').addClass('readonly');
			$('#tahdrForm .save').hide();
			$('#tahdrForm .cancel').hide();
			$(".editTahdr").css("display","none");
		}
		
		$('#tahdrForm #isAuction').prop("checked",getCheckBoxVal(tahdr.isAuction));
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
			var bidder=fetchList("getBidderByTahdrId/"+tahdr.tahdrId,null);
			var status=bidder.objectMap.bidder==null?'Not Submitted':bidder.objectMap.bidder.status;
			var bidderCurrentStatus=bidderStatus[status];
			bidderCurrentStatus=bidderCurrentStatus==undefined?'No Status':bidderCurrentStatus;
				$('#bidderStatusSpanId').html(bidderCurrentStatus);
		}else{
			$('#bidderStatusDivId').attr('style','display: none;');
			$('#bidderStatusSpanId').html('');
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

function saveQuickAuctionResp(data){
	if(data.response.hasError==false)
	{
		var tahdrDetail=data.tahdrDetail[0];
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
		$(".tahdrDetailId").val(tahdrDetail.tahdrDetailId);
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
		}
		var documentType=$(".documentType").val();
		/*setHeaderValues(documentType+"No.: " + tahdrCodes, documentType+"Title : " + title, "Department : " + department, "Status : " + status);*/
		setHeaderValues(documentType+"No.: " + tahdrCodes, documentType+"Title : " + title.substring(0, 100), "Department : " + department, "Status : " + tenderStatus);
		/*$('#leftPane').paginathing({perPage: 10});*/
	} else {
		if (data.response.hasError) {
			Alert.warn(data.response.message);
		} else {
			Alert.info(data.response.message);
		}
	}
	setChildLoadFlag(true);
}

function loadQuickAuctionListDropDowns(url){
	debugger;
	var tahdrPreparationData=fetchList(url,null);
	tahdrTypeList=tahdrPreparationData.objectMap.tahdrTypeList;
	bidTypeList=tahdrPreparationData.objectMap.bidTypeList;
	bidSection=tahdrPreparationData.objectMap.bidSection;
	tahdrStatusList =  tahdrPreparationData.objectMap.tenderStatus;
	roleDto = tahdrPreparationData.objectMap.roleDto;
	comencementPeriod= tahdrPreparationData.objectMap.commencementPeriodValue;
	loadReferenceList("tahdrTypeCode",tahdrTypeList);
	loadReferenceList("bidTypeCode",bidTypeList);
	loadReferenceList("sectionCode",bidSection);
	loadReferenceList("commencementPeriodMonth",comencementPeriod);
	tahdrViewButton=tahdrPreparationData.objectMap.viewButton;
	setCurrentTab($("#tenderBaseInfoTab"));
	setChangedFlag(false);
	loadQuickAuctionList(tahdrPreparationData.objectMap.listTahdr.data,tahdrPreparationData.objectMap.tenderTypeCode);
	setPagination(tahdrPreparationData.objectMap.listTahdr.objectMap.LastPage, 1, maxVisiblePageNumbers);
}


function loadQuickAuctionList(listTahdr,tenderTypeCode){
	$("#tabstrip").kendoTabStrip();
	$("#tahdrForm")[0].reset();
	
	if($(".dataUrlTypeCode").val()=='getQuickAuctionByTypeCode' && $(".dataUrl").val()=='quickAuctionPreparationData'){
		$(".addTahd").show();
	}
	else {
		$(".addTahd").hide();
	}
	
	$("#tahdrType").val(tenderTypeCode);
	/*if(tenderTypeCode=="PT"){		
		loadFieldsForProcurement();		
	}else if(tenderTypeCode=="WT"){
		loadFieldsForWork();
	}*/

	loadQuickAuctionToLeftPane(listTahdr);
	$("#tabstrip").kendoTabStrip();
}

function appendQuickAuctionLi(tahdr, active) {
	
	return appendLiData(tahdr.tahdrCode,tahdrStatusList[tahdr.tahdrStatusCode], tahdr.title,bidTypeList[tahdr.bidTypeCode], tahdr.tahdrId, active, 'tahdr');
}

function getQuickAuctionBaseInfo(){
	setCurrentTab(this);
	$('.pagination-container').remove();
	setPagination(totalPages, selectedPage , maxVisiblePageNumbers);
	if(getChangedFlag()){
		$('.pagination-container').remove();
		$('#tahdrForm').removeClass('errorinput');
		$('#loading-wrapper').show();
		var searchby = $('#searchLiteralId').val();
		var searchMode = $('input[name=filterBy]:checked').val();
		if(searchby != ''){
			var response = fetchQuickAuctionList(selectedPage, leftPanePageSize, searchMode, searchby);
			loadQuickAuctionToLeftPane(response.data);
			setPagination(response.objectMap.LastPage, selectedPage , maxVisiblePageNumbers);
		}else{
			var response = fetchQuickAuctionList(selectedPage, leftPanePageSize, 'none', 'none');
			loadQuickAuctionToLeftPane(response.data);
			setPagination(response.objectMap.LastPage, selectedPage , maxVisiblePageNumbers);
		}
		if($(".dataUrlTypeCode").val()=='getQuickAuctionByTypeCode' && $(".dataUrl").val()=='quickAuctionPreparationData'){
			$(".addTahd").show();
		}else{
			$(".addTahd").hide();
		}
		$('#loading-wrapper').fadeOut('slow');
		setChangedFlag(false);
	}else{
		getCacheLi();
	}
	setChildLoadFlag(true);
}

function addNewQuickAuction(event){
	debugger;
	event.preventDefault();
	$(".tahdrId").val("");
	$(".tahdrDetailId").val("");
	$(".tahdrStatus").val("");
	$("#CreatePrivateAuction").prop('checked','N');
	$("#CreatePrivateAuction").trigger('click');
	$('#tahdrForm').find('input,select,textarea').removeClass('errorinput');
	$("#tahdrForm #tahdrCode").removeAttr('readonly');
	$("#tahdrForm .createVersion").css("display","none");
	var temp = $("#tahdrType").val();
	document.getElementById("tahdrForm").reset();
	$('#tenderBaseForm').removeClass('readonly');
	$("#tahdrType").val(temp);
	$('#tahdrForm .save').show();
	$('#tahdrForm .cancel').show();
	var documentType=$(".documentType").val();
	setHeaderValues(documentType+"No.: ", documentType+"Title : ", "Department : ", "Status : ");
	
}

function itemListDropDown(values)
{
	debugger;
		$("#selectedMaterialId").html("");
		var options='';
		var materialId;
		$.each(values.map,function(index,val){
			options +='<option value="'+index+'">'+val +'</option>'
			materialId=index;
		});
		$("#selectedMaterialId").append(options);
		var material=fetchList("getMaterial",materialId);
		 $('#selectedMaterialUom').val(values.uomName);
		 $('#selectedMaterialDesc').val(values.matDescription);
		 $('#selectedItemCode').val(material.code);
		 $('#selectedHSNCode').val(material.hsnCode.code);
		 var materialSelectedId=$("#selectedMaterialId").val();
			includeSpec(materialSelectedId);
}

function submitQuickAuction(event){
	event.preventDefault();
	var tahdrId=$(".tahdrId").val();
	var resp=fetchList("submitQuickAuction",tahdrId);
	Alert.info(resp.statusData);	
}