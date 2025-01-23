var statusList=new Array();
var tahdrStatus='';
var leftPanePageSize = 7;
var maxVisiblePageNumbers = 3;
var selectedPage = 1;
var pageSize=7;
var searchMode='none';
var searchValue='none';
var LastPage='';
var tenderPurchased=0;
var exemptionCount=0;
var activeMaterial='';
$(document).ready(
		function() {

			var lengthMenu;

		    if ($(window).width() < 480) {
		        $('.mobileNav').show();
		        $.fn.DataTable.ext.pager.numbers_length = 4;       
		        lengthMenu = [ 1, 5, 7, 10, ],
		        [ 1, 5, 7, 10, ]
		    } else {        
		        lengthMenu = [ 7, 10, ],
		        [ 7, 10, ]
		    }			
			$('.listolivefbider').DataTable({
				/*
				 * "scrollY": scrY, "scrollX": scrX,
				 */
				/* "pageLength": 4, */
				"lengthMenu":lengthMenu,
				"scrollX": true,
				"bSort":false
				
			});
			 onPageLoad();

			$("input[name='tenderTypeCodeToggle']").on("change",function() {
				selectedPage=1;
				         onPageLoad();
				});
			$('.tenderTab').click(function(event) {
				 
		        event.preventDefault();
		        cacheLi();
				setCurrentTab(this);
				$('.pagination').html('');
				if(getChangedFlag()){
					var typeCode = $('input[name=tenderTypeCodeToggle]:checked').val();
					var data=fetchTenderList(1, pageSize, searchMode, searchValue,typeCode);
					loadTenderList(data);
					LastPage=data.objectMap.LastPage;
					setPagination(data.objectMap.LastPage, selectedPage , maxVisiblePageNumbers);
					/*submitToURL("getTenderForLiveBid/" + typeCode,
							"loadTenderListForBidSheet");*/
					setChangedFlag(false);
				}else{
					getCacheLi();
					setPagination(LastPage, selectedPage , maxVisiblePageNumbers);
				}
		    });
			$('#pagination-here').paginate({
				pageSize:  7,
				dataSource: 'fetchTenderList',
				responseTo:  'loadTenderListPagination',
				maxVisiblePageNumbers:3,
				searchBoxID : 'searchLiteralId',
				loadOnStartup: false
			});
		});
var tenderArray = new Array();
var tahdrMaterialArray = new Array();
var rankArray = new Array();
var tahdrMaterialReqQty=0;

function onPageLoad(){
	$('.pagination').html('');
	var typeCode = $('input[name=tenderTypeCodeToggle]:checked').val();
	var data=fetchTenderList(1, pageSize, searchMode, searchValue,typeCode);
	loadTenderList(data);
	LastPage=data.objectMap.LastPage;
	setPagination(data.objectMap.LastPage, selectedPage , maxVisiblePageNumbers);
	/*submitToURL("getTenderForLiveBid/" + typeCode,
			"loadTenderListForBidSheet");*/
	setCurrentTab($("#tenderDetailTab")[0]);
    setChangedFlag(false);
	
}

function loadTenderListPagination(data){
	loadTenderListForBidSheet(data);
}

function loadTenderList(data){
	var data=data.data;
	loadTenderListForBidSheet(data);
}

function loadTenderListForBidSheet(data) {
	 
	/*$('.pagination').children().remove();*/
	$(".leftPaneData").html("");
	var leftPanelHtml = "";
	var i = 0;
	var firstRow = null;
	var firstTahdrId = '';
	statusList=data.statusList;
	if (!$.isEmptyObject(data.tahdrList)) {
		$.each(data.tahdrList, function(key, value) {
				var tahdrCode = value.tahdrCode == null ? '' : value.tahdrCode;
				var tahdrId = value.tahdrId == null ? '' : value.tahdrId;
				var title = value.title == null ? '' : value.title;
				var emdFee = value.tahdrDetail[0].emdFee == null ? ''
						: value.tahdrDetail[0].emdFee;
				var minQuantity = value.tahdrDetail[0].minQuantity == null ? ''
						: value.tahdrDetail[0].minQuantity;
				var status = value.tahdrStatusCode == null ? '' : value.tahdrStatusCode;
				var tahdrStatus=statusList[status];
				tenderArray['Tender_' + tahdrId] = value;
				if (i == 0) {
					firstRow = value;
					firstTahdrId = tahdrId;
					leftPanelHtml = leftPanelHtml
							+ ' <li class="active" onclick="showTahdrDetail('
							+ tahdrId + ')" id="' + tahdrId + '">';
				} else {
					leftPanelHtml = leftPanelHtml
							+ ' <li onclick="showTahdrDetail(' + tahdrId
							+ ')" id="' + tahdrId + '">';
				}

				leftPanelHtml = leftPanelHtml
						+ ' <a href="#Master" data-toggle="tab">'
						+ ' <div class="col-md-12">'
						+ '  <label class="col-xs-6" id="' + tahdrId+'_tenderNo">' + tahdrCode + '</label>'
						+ '	<label class="col-xs-6" id="' + tahdrId + '_title">'+ title + '</label>' + ' </div>'
						+ ' <div class="col-md-12">'
						+ '	<label class="col-xs-6" id="' + tahdrId+ '_tahdrValidity">' + tahdrStatus + '</label>'
						+ '	<label class="col-xs-6" id="' + tahdrId + '_emdFee">'+ emdFee + '</label>' + ' </div>' + ' </a>' + ' </li>';
				i++;
		});
		$(".leftPaneData").html(leftPanelHtml);
		loadTahdrRightPane(firstRow);
		$('.tenderMaterialTab').removeClass('readonly');
		
	} else {
		loadTahdrRightPane(firstRow);
		$('.tenderMaterialTab').addClass('readonly');
		Alert.warn(data.resultMessage);
	}

	/*$('.leftPaneData').paginathing({
		perPage : 6
	});*/
	$(".tabs-left li a label").text(function(index, currentText) {
		return currentText.substr(0, 20);
	});

	$(".tabs-left li a label").text(function(index, currentText) {
		return currentText.substr(0, 20);
	});

}
function loadTahdrRightPane(data) {
	 
	if (!$.isEmptyObject(data)) {
		var tenderCode = data.tahdrCode == null ? '' : data.tahdrCode;
		var tenderNo = data.title == null ? '' : data.title;
		var contactEmail = data.tahdrDetail[0].contactEmailId == null ? ''
				: data.tahdrDetail[0].contactEmailId;
		var description = data.tahdrDetail[0].description == null ? ''
				: data.tahdrDetail[0].description;

		var version = data.tahdrDetail[0].version == null ? ''
				: data.tahdrDetail[0].version;
		var emd = data.tahdrDetail[0].emdFee == null ? ''
				: data.tahdrDetail[0].emdFee;
		var tahdrId = data.tahdrId == null ? '' : data.tahdrId;
		
		var status = data.tahdrStatusCode == null ? '' : data.tahdrStatusCode;
		tahdrStatus=status;
		var tenderStatus=statusList[status];
		$('#tahdrForm #tenderNo').html(tenderCode);
		$('#tahdrForm #tenderVersion').html(version);
		$('#tahdrForm #description').html(description);
		$('#tahdrForm #emdFee').html(emd);
		$('#tahdrForm #tahdrId').val(tahdrId);
		$('#tahdrForm #status').html(tenderStatus);

		setHeaderValues("Tender No.: " + tenderCode, "Tender Title : "
				+ tenderNo, "Contact EmailId : " + contactEmail,
				"Description : " + description);
		tabVisibility(status);
	} else {
		$('#tahdrForm #tenderNo').html('');
		$('#tahdrForm #tenderVersion').html('');
		$('#tahdrForm #description').html('');
		$('#tahdrForm #emdFee').html('');
		$('#tahdrForm #tahdrId').val('');
		$('#tahdrForm #status').html('');
		setHeaderValues("Tender No.: ", "Tender Title : ",
				"Contact EmailId : ", "Description : ");
		tabVisibility(null);
	}
	setChildLoadFlag(true);
}
function tabVisibility(status){
	 
	if(status=="ASCH" || status=="PBOP"){
	$('.bidSheetTabDiv').show();
	}else{
		$('.bidSheetTabDiv').hide();	
	}
}
function showTahdrDetail(tahdrId) {
	loadTahdrRightPane(tenderArray['Tender_' + tahdrId]);
}

function loadTahdrMaterials(ele){
	loadTahdrMaterial(ele);
}

function loadTahdrMaterial(ele){
	  
	cacheLi();
	setCurrentTab(ele);
	if(getChangedFlag()){
		submitWithParam('getBidSheetAuctionMaterialListById','tahdrId','loadTahdrMaterialListForView');
		setChangedFlag(false);
	}else{
		getCacheLi();
		$('#leftPaneData li:not(.active)').show();
	}
	
}


function loadTahdrMaterialListForView(data) {
	$('.pagination').children().remove();
	$("#leftPane").html("");
	var leftPanelHtml = "";
	var i = 0;
	var firstRow = null;
	if (!$.isEmptyObject(data.objectMap.responseList)) {
		$.each(data.objectMap.responseList, function(key, value) {
			var tahdrMaterialId = value.tahdrMaterialId == null ? ''
					: value.tahdrMaterialId;
			var name = value.material == null ? ''
					: value.material.name == null ? '' : value.material.name;
			var uomName = value.material == null ? ''
					: value.material.uom == null ? ''
							: value.material.uom.name == null ? ''
									: value.material.uom.name;
			var description = value.material == null ? ''
					: value.material.description == null ? ''
							: value.material.description;
			var hsnCode = value.material == null ? ''
					: value.material.hsnCode == null ? ''
							: value.material.hsnCode.code == null ? ''
									: value.material.hsnCode.code;

			tahdrMaterialArray["tahdrMaterial_" + tahdrMaterialId] = value;
			if (i == 0) {
				firstRow = value;

				leftPanelHtml = leftPanelHtml + ' <li class="active" id="'
						+ tahdrMaterialId
						+ '"  onclick="showTahdrMaterialDetail(this)" data-matname='+name+'>';
			} else {
				leftPanelHtml = leftPanelHtml + ' <li class="itemBidLi" id="'
						+ tahdrMaterialId
						+ '"  onclick="showTahdrMaterialDetail(this)" data-matname='+name+'>';
			}

			leftPanelHtml = leftPanelHtml
					+ ' <a href="#Master" data-toggle="tab">'
					+ ' <div class="col-md-12">'
					+ '  <label class="col-xs-6" id="' + tahdrMaterialId
					+ '_name">' + name + '</label>'
					+ '	<label class="col-xs-6" id="' + tahdrMaterialId
					+ '_uomName">' + uomName + '</label>' + ' </div>'
					+ ' <div class="col-md-12">'
					+ '	<label class="col-xs-6" id="' + tahdrMaterialId
					+ '_description">' + description + '</label>'
					+ '	<label class="col-xs-6" id="' + tahdrMaterialId
					+ '_hcnCode">' + hsnCode + '</label>' + ' </div>' + ' </a>'
					+ ' </li>';
			i++;
		});
		$(".leftPaneData").html(leftPanelHtml);
		loadTahdrMaterialRightPane(firstRow);
		
	}else{
		$(".leftPaneData").html('');
		loadTahdrMaterialRightPane(null);
		$('.statisticTab').addClass('readonly');
	}
	
	$('.leftPaneData').paginathing({
		perPage : 6
	});

}

function loadTahdrMaterialRightPane(data) {
	 
	if (!$.isEmptyObject(data)) {
		var tahdrMaterialId = data.tahdrMaterialId == null ? ''
				: data.tahdrMaterialId;
		var name = data.material.materialId == null ? '' : data.material.name;
		
		var code = data.material.materialId == null ? '' : data.material.code;
		var uomName = data.material.uom.name == null ? ''
				: data.material.uom.name;
		var description = data.material.description == null ? ''
				: data.material.description;
		var hsnCode = data.material.code == null ? '' : data.material.code;
		var reqQuantity = data.quantity == null ? '' : data.quantity;
		var specVersion = data.materialVersion == null ? ''
				: data.materialVersion.name;
		var tenderMatType = data.materialTypeCode == null ? ''
				: data.materialTypeCode;
	    tahdrMaterialReqQty=reqQuantity;
	    $('#uomType').html(uomName);
		$("#itemDetailForm #tahdrMaterialId").val(tahdrMaterialId);
		$("#itemDetailForm #matName").html(name);
		$("#itemDetailForm #matCode").html(code);
		$("#itemDetailForm #uom").html(uomName);
		$("#itemDetailForm #description").html(description);

		$("#itemDetailForm #reqQuantity").html(reqQuantity);
		$("#itemDetailForm #specVersion").html(specVersion);
		$("#itemDetailForm #tenderMatType").html(tenderMatType);
		$('.statisticTab').removeClass('readonly');
	}
	setChildLoadFlag(true);
}

function showTahdrMaterialDetail(el) {
	var tahdrMaterialId = $(el).attr('id');
	loadTahdrMaterialRightPane(tahdrMaterialArray['tahdrMaterial_'
			+ tahdrMaterialId]);
}

function loadBidSheet(ele){
	submitWithTwoParam('getBidSheetByTahdrMaterialId','tahdrId','itemDetailForm #tahdrMaterialId','loadBiddersListForLiveBid');
}

function loadBiddersListForLiveBid(data) {
	var lengthMenu;
	$('#leftPaneData li:not(.active)').hide();
	/*$('#leftPaneData li:not(.active)').remove();*/
    if ($(window).width() < 480) {
        $('.mobileNav').show();
        $.fn.DataTable.ext.pager.numbers_length = 4;       
        lengthMenu = [ 1, 5, 7, 10, ],
        [ 1, 5, 7, 10, ]
    } else {        
        lengthMenu = [ 7, 10, ],
        [ 7, 10, ]
    }	
	
	/*$('.leftPaneData li').each(function() {
		$(this).attr('onclick','showBidderList(this)');
	});*/
	if (!$.isEmptyObject(data.objectMap.rank)) {
		rankArray=data.objectMap.rank;
	}
	if (!$.isEmptyObject(data.objectMap.bidderList)) {
		var data = data.objectMap.bidderList;
		$(".listolivefbider").DataTable().destroy();
		$('.listolivefbider tbody').empty();
		$.each(data, function(key, value) {
			var bidderData=value;
			var partnername=bidderData.partner==null?'':bidderData.partner.name;
			var partnerId=bidderData.partner==null?'':bidderData.partner.bPartnerId;
			var factoryName=bidderData.itemBid==null?'':bidderData.itemBid.bidder==null?'':bidderData.itemBid.bidder.factory==null?'':bidderData.itemBid.bidder.factory.name;
			var offeredQuantity=bidderData.offeredQuantity==null?'':bidderData.offeredQuantity;
			var exGroupPriceRate=bidderData.exGroupPriceRate==null?'':bidderData.exGroupPriceRate;
			var fddAmount=bidderData.fddAmount==null?'':bidderData.fddAmount;
			var ticRate=bidderData.ticRate==null?'':bidderData.ticRate;
			var freightChargeRate=bidderData.freightChargeRate==null?'':bidderData.freightChargeRate;
			var totalExGroupPrice=bidderData.totalExGroupPrice==null?'':bidderData.totalExGroupPrice;
			var totalFreightCharge=bidderData.totalFreightCharge==null?'':bidderData.totalFreightCharge;
			var totalTic=bidderData.totalTic==null?'':bidderData.totalTic;
			var fddRate=bidderData.fddRate==null?'':bidderData.fddRate;
			var totalTax=bidderData.totalTax==null?'':bidderData.totalTax;
			var fddRateWithGST=bidderData.fddRateWithGST==null?'':bidderData.fddRateWithGST;
			var fddAmountWithGST=bidderData.fddAmountWithGST==null?'':bidderData.fddAmountWithGST;
			
			var sgst=bidderData.totalSgst==null?0:bidderData.totalSgst;
			var igst=bidderData.totalIgst==null?0:bidderData.totalIgst;
			var cgst=bidderData.totalCgst==null?0:bidderData.totalCgst;
			var sgstPercent=bidderData.sgst==null?0:bidderData.sgst;
			var igstPercent=bidderData.igst==null?0:bidderData.igst;
			var cgstPercent=bidderData.cgst==null?0:bidderData.cgst;
			
			var getRank=rankArray['rankMap_'+partnerId]==undefined?'#':rankArray['rankMap_'+partnerId];
			var rank=getRank;
			if(key==0){
				$('.listolivefbider tbody').append('<tr class="greenrow"><td>'+partnername+'</td>' + 
						'<td>'+rank+'</td>' + 
						'<td>'+factoryName+'</td>' + 
						'<td>'+offeredQuantity+'</td>' + 
						'<td>'+exGroupPriceRate+'</td>' +
						'<td>'+freightChargeRate+'</td>' + 
						'<td>'+ticRate+'</td>' + 
						'<td>'+cgst+'('+cgstPercent +' %)'+'</td>' +
						'<td>'+igst+'('+igstPercent +' %)'+'</td>' + 
						'<td>'+sgst+'('+sgstPercent +' %)'+'</td>' + 
						'<td>'+fddRate+'</td>' + 
						'<td>'+fddRateWithGST+'</td>' + 
						'<td>'+totalExGroupPrice+'</td>' + 
						'<td>'+totalFreightCharge+'</td>' + 
						'<td>'+totalTic+'</td>' + 
						'<td>'+totalTax+'</td>' +
						'<td>'+fddAmount+'</td>' + 
						'<td>'+fddAmountWithGST+'</td>' + 
						'</tr>');
	
	            }else{
	            	$('.listolivefbider tbody').append('<tr><td>'+partnername+'</td>' + 
						'<td>'+rank+'</td>' + 
						'<td>'+factoryName+'</td>' + 
						'<td>'+offeredQuantity+'</td>' + 
						'<td>'+exGroupPriceRate+'</td>' +
						'<td>'+freightChargeRate+'</td>' + 
						'<td>'+ticRate+'</td>' + 
						'<td>'+cgst+'('+cgstPercent +' %)'+'</td>' +
						'<td>'+igst+'('+igstPercent +' %)'+'</td>' + 
						'<td>'+sgst+'('+sgstPercent +' %)'+'</td>' + 
						'<td>'+fddRate+'</td>' +
						'<td>'+fddRateWithGST+'</td>' +
						'<td>'+totalExGroupPrice+'</td>' + 
						'<td>'+totalFreightCharge+'</td>' + 
						'<td>'+totalTic+'</td>' + 
						'<td>'+totalTax+'</td>' + 
						'<td>'+fddAmount+'</td>' +
						'<td>'+fddAmountWithGST+'</td>' + 
						'</tr>');
			}
		});
		
	} else {
		$('.listolivefbider tbody').empty();
		Alert.warn(data.objectMap.resultMessage);
	}
	$('.listolivefbider').DataTable({
		"scrollX": true,
		"bSort":false,
		"lengthMenu":lengthMenu
	});
	$('table').each(
			function() {
				var text = []
				$(this).find('thead tr th').each(
						function() {
							text.push($(this).text())

							for (var i = 0; i < text.length; i++) {
								$(this).parents('table').find(
										'tbody tr td:nth-of-type('
												+ (i + 1) + ')').attr(
										'data-th', text[i])
							}
						});
			});
	if ($(window).width() < 768) {
		$('.dataTables_length').parent().addClass('col-xs-6');
	
	 $('.dataTables_filter').parent().addClass('col-xs-6');
	}
}

function showBidderList(el){
	var tahdrMaterialId = $(el).attr('id');
	$("#itemDetailForm #tahdrMaterialId").val(tahdrMaterialId);
	var tahdrId = $('#tahdrForm #tahdrId').val();
	submitToURL('getBidSheetByTahdrMaterialId/'+tahdrId+'/'+tahdrMaterialId,'loadBiddersListForLiveBid');
}
function loadStatisticDataWithMaterial(ele){
	cacheLi();
	submitWithTwoParam('getStatisticDetailsListByMaterial','tahdrId','itemDetailForm #tahdrMaterialId','bidderCount');
}

function loadStatisticDataWithTender(ele){
	submitWithTwoParam('getStatisticDetailsListByTender','tahdrId','bidderCount');
}

/*function showStatisticDetails(el){
	 
	    showLoader();
		var tahdrMaterialId = $(el).attr('id');
		$("#itemDetailForm #tahdrMaterialId").val(tahdrMaterialId);
		var tahdrId=$('#tahdrForm #tahdrId').val();
		submitToURL('getStatisticDetailsListByMaterial/'+tahdrId+'/'+tahdrMaterialId,'bidderCount');
		hideLoader();
}*/
function bidderCount(data){
	if(!isEmpty(data) && !isEmpty(data.objectMap)){
		if(data.objectMap.result){
			$('#leftPaneData li:not(.active)').hide();
			activeMaterial=$('#leftPaneData li.active').data('matname');
			if(tahdrStatus=='PU'){
				$('#bidderStastisticTblId').removeAttr('style');
				$("#statisticForm #bidderCountDiv").hide();
				$("#statisticForm #bidderPriceBidOpenedDiv").hide();
				
				otherBidderCount(data.objectMap.bidders);
				$('.bidderCountTable tbody').html('');
				$('.bidderCountTable tbody').append('<tr><td>'+tenderPurchased +'</td>' + 
						'<td>'+exemptionCount+'</td>' + 
						'<td>'+data.objectMap.bidderCount+'</td>' + 
						'<td>'+activeMaterial+'</td>' + 
						'<td>'+data.objectMap.technicalBidderCount+'</td>' + 
						'<td>'+data.objectMap.commercialBid+'</td>' + 
						'<td>'+data.objectMap.priceBidderCount+'</td>' + 
						'<td>'+data.objectMap.allBiddderCount+'</td>' +
						'</tr>');
				
			}else{
				$('#bidderStastisticTblId').attr('style','display : none ;');
				$("#statisticForm #bidderCountDiv").show();
				$("#statisticForm #bidderPriceBidOpenedDiv").show();
				var priceBidOpenedCount=0;
				var bidderCount=data.objectMap.bidderCount==null?0:data.objectMap.bidderCount.length;
				$("#statisticForm #bidderCount").html(bidderCount);
				$.each(data.objectMap.bidderCount, function(key, value) {
					if(value.status=='ASCH' || value.status=='PBOP'){
						priceBidOpenedCount++;
					}
				});
				$("#statisticForm #bidderPriceBidOpened").html(priceBidOpenedCount);
			}
		}else{
			Alert.warn(data.objectMap.resultMessage);
		}
	}
}

function otherBidderCount(data){
	tenderPurchased=0;
	exemptionCount=0;
	if(!$.isEmptyObject(data)){
		$.each(data, function(key, value) {
			if(value.tenderPurchase!=null){
				if(value.tenderPurchase.paymentMode=='ISEXEMP'){
					exemptionCount++;
				}else{
					tenderPurchased++;
				}
			}
		});
		$("#statisticForm #purchasedbidderCount").html(tenderPurchased);
		$("#statisticForm #emdBidderCount").html(exemptionCount);
	}else{
		$("#statisticForm #purchasedbidderCount").html(tenderPurchased);
		$("#statisticForm #emdBidderCount").html(exemptionCount);
	}
}

function fetchTenderList(pageNumber, pageSize, searchMode, searchValue){
	var typecode=$('input[name=tenderTypeCodeToggle]:checked').val();
	var response;
	$.ajax({
        type : "POST",
        contentType : "application/json",
        url: "fetchTenderListForBidSheet?pageNumber="+pageNumber+"&pageSize="+pageSize+'&searchMode='+searchMode+'&serachValue='+searchValue+'&typeCode='+typecode,
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

function downloadBidSheetPdf(event){
	var tahdrId=$('#tahdrForm #tahdrId').val();
	var tahdrMaterialId=$("#itemDetailForm #tahdrMaterialId").val();
	event.preventDefault();
	if(tahdrId!='' && tahdrMaterialId!='' ){
		showLoader();
		directSubmit(event,"gennerateBidSheetDoc","generateBidSheetReport/"+tahdrId+"/"+tahdrMaterialId);
		hideLoader();	
	}else{
		ALert.warn('Something went wrong !');
	}
	
}
