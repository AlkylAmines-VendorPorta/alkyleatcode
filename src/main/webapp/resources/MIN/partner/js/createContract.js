var array = new Array();
var pageSize = 7;
var maxVisiblePage = 3;
var awardWinner;
var selectedtahdr;
var selectedBidder;
var selectedItem;
var contractType = 'ZVAL';
var formToSubmit = 'contractHeaderForm';
var itemArray = new Array();
var contractItemArray = new Array();
var contractCndnArray = new Array();
var contractSrvcArray =  new Array();
var isCreated = 'N';

$(document).ready(function(){
	
	loadTahdrListValues();
	
	$('#pagination-here').paginate({
		pageSize:  7,
		dataSource: 'fetchData',
		responseTo:  'loadTahdrList',
		maxVisiblePageNumbers:3,
		searchBoxID : 'searchLiteralId',
		searchBtnId : 'searchBtn'
	});
	
	$("input[name='tenderTypeCodeToggle']").on("change",function(){
		//$('.pagination').children().remove();
		
		loadTahdrListValues();
		$('.disclearfix').css('display','block');
		 $(".tabs-left li a label").text(function(index, currentText) {
			    return currentText.substr(0, 20);
		});
		$('#tenderDetails').click(); 
		
		var type = $("input[name='tenderTypeCodeToggle']:checked").val();
		if(type == 'WT'){
			contractType = 'ZVAL';
			$('.contractService').show();
		}else{
			contractType = 'ZQTY'
			//$('.contractService').hide();	
		}
		$('#contractType').val(contractType);	
	});
	
	$("#leftPane").on('click', '.tahdr', function() {
		var id = $(this).attr('id');
		$("#tenderId").val(id);
		loadTahdrToRightPane(tahdrArray["tahdr" + id]);
	});
	
	$(".createContractBtn").on("click", function(event) {
		event.preventDefault();
		var uri =$("#"+formToSubmit).attr( 'action' );
		submitIt(formToSubmit, uri, "createContractResponse");
	});
	
});


function getData(){
	var response = fetchList('getTahdrForCreateContract',null);
	return response;
}

function loadTahdrListValues(){
	
	var tahdrPreparationData = fetchData(1,pageSize,'none','none');
	setPagination(tahdrPreparationData.objectMap.LastPage, 1 , maxVisiblePage);
	loadTahdrList(tahdrPreparationData.data);
}

function loadTahdrList(listTahdr){
	
	$("#tabstrip").kendoTabStrip();
	loadTahdrToLeftPane(listTahdr);
	$("#tabstrip").kendoTabStrip();
}

function loadTahdrToLeftPane(tahdrList) {
	debugger;
	$("#leftPane").html("");
	var leftPanelHtml = '';
	var i = 0;
	var firstRow = null;
	tahdrArray = [];
	$.each(Object.values(tahdrList), function(key, tahdr) {
		debugger;
		var active = "";
		tahdrArray["tahdr" + tahdr.tahdrId] = tahdr;
		if(selectedtahdr != undefined){
			firstRow = selectedtahdr;
			if(tahdr.tahdrId == selectedtahdr){
				active = "active";
				$("#tenderId").val(tahdr.tahdrId);
			}
		}else {
			tahdrArray["tahdr" + tahdr.tahdrId] = tahdr;
			if(i == 0){
				firstRow = tahdr.tahdrId;
				active = "active";
				$("#tenderId").val(tahdr.tahdrId);
				}
		}
		leftPanelHtml = leftPanelHtml + appendTahdrLi(tahdr, active);
		active = "";
		i++;
	});
	$("#leftPane").append(leftPanelHtml);
	setLeftPaneHeader("Tender List", Object.values(tahdrArray).length);
	loadTahdrToRightPane(tahdrArray["tahdr" + firstRow]);

	$(".tabs-left li a label").text(function(index, currentText) {
	    return currentText.substr(0, 20);
	});
}

function loadTahdrToRightPane(tahdr){

	
	if(!$.isEmptyObject(tahdr)){
		$("#tahdrCode").val(getValue(tahdr.tahdrCode));
		$("#totalBidderCount").val(tahdr.bidders.length);
		awardWinnerCount();
		$("#EMD").val(getValue(tahdr.tahdrDetail[0].emdFee));
		$("#saleStartDate").val(formatDate(tahdr.tahdrDetail[0].purchaseFromDate));
		$("#technicalBidToDate").val(formatDateTime(tahdr.tahdrDetail[0].technicalBidToDate));
		$("#techBidOpenningDate").val(formatDateTime(tahdr.tahdrDetail[0].techBidOpenningDate));
		$("#priceBidOpenningDate").val(formatDateTime(tahdr.tahdrDetail[0].priceBidOpenningDate));
		$("#c1OpenningDate").val(formatDateTime(tahdr.tahdrDetail[0].c1OpenningDate));
		var documentType=$(".documentType").val();

	}
	
}

function awardWinnerCount(){
	var tahdrId=$("#tenderId").val();
	submitWithParam("getAwardWinnerCount", "tenderId","awardWinnerCountResponse");
}

function awardWinnerCountResponse(data){
	if(!$.isEmptyObject(data)){
		$("#awardWinnerCount").val(data.objectMap.biddercount);
		awardWinner=data.objectMap.biddercount;
	}
}

function appendTahdrLi(tahdr, active) {
	return appendLiData(tahdr.tahdrCode, tahdr.title,'test',tahdr.tahdrCode, tahdr.tahdrId, active, 'tahdr');
}
function fetchData(pageNumber, pageSize, searchMode, searchValue){
	var response;
	var url = '';
	url="getTahdrForContract/"+$("input[name='tenderTypeCodeToggle']:checked").val()+"/"+isCreated+"/"+pageNumber+"/"+pageSize+'/'+searchMode+'/'+searchValue;	
	var response=fetchList(url,null);
	return response;
}


$("#BidderDetails").on('click',function(event) {
	var tahdrId = $("#tenderId").val();
	selectedtahdr = tahdrId;
	var response = fetchList('getWinnerBidderListByTahdrId',tahdrId);
	loadBidderListToLeftPanel(response);
});
$("#tenderDetails").on('click',function(event) {
	var tahdrId = $("#tenderId").val();
	selectedtahdr = tahdrId;
	loadTahdrToLeftPane(tahdrArray);
	loadTahdrToRightPane(tahdrArray["tahdr" + selectedtahdr]);
	$('.pagination-container').remove();
	setPagination(totalPages, selectedPage , maxVisiblePage);
});


function loadBidderListToLeftPanel(data){
	
		$('.pagination').children().remove();
		$("#leftPane").html("");
		var leftPanelHtml="";
		var i=0;
		var firstRow=null;
		bidderArray = [];
		if(!$.isEmptyObject(data)){
				$.each(Object.values(data),function(key,value){
					debugger;
					var active = "";
					bidderArray["bidder" + value.bidderId] = value;
					
					if(selectedBidder != undefined){
						if(value.bidderId == selectedBidder){
							firstRow = selectedBidder;
							active = "active";
						}else{
							active = "";
						}
					}else{
						if(i==0){
							firstRow = value.bidderId;
							active = "active";
						}else{
							active = "";
						}
					}		
					leftPanelHtml = leftPanelHtml + ' <li class ="'+active+'" onclick="loadBidderDetails('+value.bidderId+')" id="'+value.bidderId+'">';
					leftPanelHtml = leftPanelHtml +' <a href="#Master" data-toggle="tab">'
				    +' <div class="col-md-12">'
				    +'  <label class="col-xs-6" >'+value.partner.name+'</label>'
				    +'	<label class="col-xs-6" ></label>'
				    +' </div>'	
				    +' <div class="col-md-12">'
				    +'	<label class="col-xs-6" ></label>'
					+'	<label class="col-xs-6" ></label>'
					+' </div>'
				    +' </a>'
				    +' </li>';
					i++;
				});
		}
		else{
			
		}
		$(".leftPaneData").html(leftPanelHtml);
		loadBidderDetails(firstRow);
		$('.leftPaneData').paginathing({perPage: 6});
		//$(".leftPaneData li").first().click();
}

function loadBidderDetails(id){
	$('#bidderId').val(id);
	var tahdrId = $('#tenderId').val();
	selectedBidder = id;
	var response = getContractDetails(id, tahdrId);
	debugger;
	if(response != undefined){
		setContractHeader(response.objectMap);
		//contractItemArray = [];
		var itemList = response.objectMap.itemList;
		var contractItemList = response.objectMap.contractItem;
		var contractCndnList = response.objectMap.contractCondition;
		var contractSrvcList = response.objectMap.contractService;
		
		for(var i = 0; i < itemList.length ; i++ ){
			itemArray["item" + itemList[i].winnerSelectionId] = itemList[i];
		}
		if(contractItemList != undefined)
			for(var i = 0; i < contractItemList.length ; i++ ){
				contractItemArray["contractItem" + contractItemList[i].winnerSelection.winnerSelectionId] = contractItemList[i];
			}
		if(contractCndnList != undefined){
			for(var i = 0; i < contractCndnList.length ; i++ ){
				contractCndnArray["contractCndn" + contractCndnList[i].winnerSelection.winnerSelectionId] = contractCndnList[i];
			}
		}
		if(contractSrvcList != undefined){
			for(var i = 0; i < contractSrvcList.length ; i++ ){
				contractSrvcArray["contractSrvc" + contractSrvcList[i].winnerSelection.winnerSelectionId] = contractSrvcList[i];
			}
		}
		
			
	}
}

function loadContract(contractType){
	
	loadItemListToLeftPanel(itemArray);
	
	if(contractType == 'i'){
		formToSubmit = 'contractItemForm';
	}else if(contractType == 's'){
		formToSubmit = 'contractServiceForm';
	}else {
		formToSubmit = 'contractCndnFrm';
	}
}
function loadBidder(){
	var id = $('#bidderId').val();
	formToSubmit = 'contractHeaderForm';
	loadBidderListToLeftPanel(bidderArray);
}

function loadItemListToLeftPanel(data){
	
	$('.pagination').children().remove();
	$("#leftPane").html("");
	var leftPanelHtml="";
	var i=0;
	var firstRow=null;
	if(!$.isEmptyObject(data)){
		$.each(Object.values(data), function(key, value) {
			
			var active = "";
			if(selectedItem != undefined){
				if(value.winnerSelectionId == selectedItem){
					firstRow = selectedItem;
					active = "active";
				}else{
					active = "";
				}
			}else{
				if(i==0){
					firstRow = value.winnerSelectionId;
					active = "active";
				}else{
					active = "";
				}
			}	
			
			leftPanelHtml = leftPanelHtml + ' <li class="' + active + '" onclick="loadItemCotractDetail('+value.winnerSelectionId+')" id="">';
			leftPanelHtml = leftPanelHtml +' <a href="#Master" data-toggle="tab">'
		    +' <div class="col-md-12">'
		    +'  <label class="col-xs-6" >'+value.itemBid.tahdrMaterial.material.code+'</label>'
		    +'	<label class="col-xs-6" ></label>'
		    +' </div>'	
		    +' <div class="col-md-12">'
		    +'	<label class="col-xs-6" ></label>'
			+'	<label class="col-xs-6" ></label>'
			+' </div>'
		    +' </a>'
		    +' </li>';
			i++;
		});
	}
	$(".leftPaneData").html(leftPanelHtml);
	//loadTahdrDetailRightPane(firstRow);
	$('.leftPaneData').paginathing({perPage: 6});
	loadItemCotractDetail(firstRow);
}

function loadItemCotractDetail(id){
	selectedItem = id;
	setContractItem(id);
	setContractService(id);
	setContractCondition(id);
}

function setContractHeader(data){
	
	if(data.contractHeader != null){
		$('.contractHeaderId').val(data.contractHeader.contractHeaderId);
		$('#startDate').datepicker("setDate", new Date());
		$('#valEndDate').datepicker("setDate", data.contractHeader.valEndDate);
		$('#targValue').val(data.contractHeader.targValue);
		$('#securityDep').val(data.contractHeader.securityDep);
		$('#performanceDep').val(data.contractHeader.performanceDep);
		$('#accAssCateg').val(data.contractHeader.accAssCateg);
		if(data.contractHeader.isContractCreated == 'Y'){
			contractCreated();
		}else{
			contractNtCreated();
		}
	}else{
		$('.contractHeaderId').val('');
		$('#startDate').datepicker("setDate", new Date());
		$('#valEndDate').datepicker("setDate", '');
		$('#targValue').val('');
		contractNtCreated();
	}
	var bidder = data.bidder;
	$('#vendocCode').val(bidder.partner.vendorSapCode==null?'100008432':bidder.partner.vendorSapCode);
	$('#purOrg').val('1000');
	$('#purGrp').val('104');
	
	//$('#endDate').val('');
}
function setContractItem(id){
		
	debugger;
		var contractData = contractItemArray["contractItem"+id];
		if(contractData !== undefined){
			$('.itemNo').val(contractData.item);
			$('.matNo').val(contractData.materialNo);
			$('.shortTxt').val(contractData.shortTexr);
			$('.trgtQty').val(contractData.targetQuantity);
			$('.netprice').val(contractData.netPrice);
			$('.taxCode').val(contractData.taxCode);
			$('.valType').val(contractData.valuationType);
			$('.matGrp').val(contractData.materialGroup);
			$('.contractItemId').val(contractData.contractItemId);
			$('.winnerSelectionId').val(contractData.winnerSelection.winnerSelectionId);
		}else{
			var data = itemArray["item" + id];
			if (data !== undefined) {
				$('.contractItemId').val('');
				$('.itemNo').val(data.itemBid.tahdrMaterial.material.itemCode);
				$('.matNo').val(data.itemBid.tahdrMaterial.material.itemCode);
				$('.shortTxt').val(data.itemBid.tahdrMaterial.material.code);
				$('.trgtQty').val(data.allocatedQty);
				$('.netprice').val('');
				$('.taxCode').val('G3');
				$('.valType').val('G3');
				$('.matGrp').val('2022HCAP');
				$('.winnerSelectionId').val(data.winnerSelectionId);
			}
		}
}
function setContractService(id){
	
	var contractData = contractSrvcArray["contractSrvc"+id];
	if(contractData !== undefined){
		$('.contractServiceId').val(contractData.contractServiceId);
		$('.srvcMatNo').val(contractData.materialNo);
		$('.srvclineItemNo').val(contractData.srvcLineItemNo);
		$('.srvcNo').val(contractData.serviceNo);
		$('.srvcQty').val(contractData.quantity);
		$('.srvcAmount').val(contractData.amount);
		$('.srvcCostCenter').val(contractData.costCenter);
		$('.winnerSelectionId').val(contractData.winnerSelection.winnerSelectionId);
	}else{
		var data = itemArray["item" + id];
		if(data != undefined){
			$('.contractServiceId').val('');
			$('.srvcMatNo').val(data.itemBid.tahdrMaterial.material.itemCode);
			$('.srvclineItemNo').val(data.itemBid.tahdrMaterial.material.itemCode);
			$('.srvcNo').val('');
			$('.srvcQty').val(data.allocatedQty);
			$('.srvcAmount').val('');
			$('.srvcCostCenter').val('');
			$('.winnerSelectionId').val(data.winnerSelectionId);
		}
	}
}

function setContractCondition(id){
	
	var contractData = contractCndnArray["contractCndn"+id];
		if(contractData !== undefined){
			$('.contractConditionId').val(contractData.contractConditionId);
			$('.cndnLineItemNo').val(contractData.srvcLineItemNo);
			$('.cndnServiceLineItem').val(contractData.srvcLineItem);
			$('.cndnType').val(contractData.conditionType);
			$('.cndnAmount').val(contractData.amount);
			$('.winnerSelectionId').val(contractData.winnerSelection.winnerSelectionId);
		}else{
		    var data = itemArray["item" + id];
		    if(data != undefined){
				$('.contractConditionId').val('');
		    	$('.cndnLineItemNo').val(data.itemBid.tahdrMaterial.material.itemCode);
				$('.cndnServiceLineItem').val(data.itemBid.tahdrMaterial.material.itemCode);
				$('.cndnType').val('');
				$('.cndnAmount').val('');
				$('.winnerSelectionId').val(data.winnerSelectionId);
		    }
		}
}

function getContractDetails(bidderId, tahdrId){
	
	var response = fetchList('getContractDetails/'+bidderId, tahdrId);
	return response;
}

function createContract(){
	var headerId = $('.contractHeaderId').val();
	submitToURL('createContract/'+headerId, 'createContractResponse')
}

function createContractResponse(data){

	if(data.hasError){
		Alert.warn(data.message);
	}else{
		Alert.info(data.message);
		//contractCreated()
	}
}
function contractCreated(){
	$('.createContractBtn').hide();
	$('.cntrct').hide();
	$('#contractHeaderForm').find('.inputField').attr("readonly", true);
}
function contractNtCreated(){
	$('.createContractBtn').show();
	$('.cntrct').show();
	$('#contractHeaderForm').find('.inputField').attr("readonly", false);
}
function loadCreated(){
	isCreated = 'Y'
	loadTahdrListValues();	
	
}
function loadPending(){
	isCreated = 'N'
	loadTahdrListValues();	
	
}

