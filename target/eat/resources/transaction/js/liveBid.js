/**
 * 
 */
$(document).ready( function() {
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
			
			$('.rightTopClass').addClass('blinker');
			$('.biddertable').DataTable({
				"lengthMenu":lengthMenu,
				"ordering": false,
				"columnDefs": [
					{ "width": "15%", "targets": 0 },
				    { "width": "15%", "targets": 1 },
				    { "width": "15%", "targets": 2 },
				    { "width": "20%", "targets": 3 },
				    { "width": "20%", "targets": 4 },
				    { "width": "15%", "targets": 5 }
				  ]
			});
			if ($(window).width() < 768) {
				$('.dataTables_length').parent().addClass('col-xs-6');
			
			 $('.dataTables_filter').parent().addClass('col-xs-6');
			}
			
			/*$($.fn.dataTable.tables(true)).DataTable().columns.adjust();*/
			
			var dataUrl=$('#myTenderUrl').val();
			if(dataUrl==null || dataUrl=='' || dataUrl ==undefined ){
				 typeCode = $('input[name=tenderTypeCodeToggle]:checked').val();
					submitToURL("getLiveAuctions/" + typeCode,
							"loadAuctionListForLiveBid");
			}else{
				$('#worksToggle').removeClass('active');
				$('#worksCheckBoxId').removeAttr('checked');
				$('#auctionsCheckBoxId').removeAttr('checked');
				submitToURL(dataUrl, "loadAuctionListForLiveBid");
				setToggle(tenderTypeCode);
			}
			
			$("input[name='tenderTypeCodeToggle']").on(
					"change",
					function() {
						 typeCode = $(this).val();
						submitToURL("getLiveAuctions/" + typeCode,
								"loadAuctionListForLiveBid");
					});
			$('.tenderTab').click(function(event) {
				 
		        event.preventDefault();
		         typeCode = $('input[name=tenderTypeCodeToggle]:checked').val();
				 submitToURL("getLiveAuctions/" + typeCode,
						"loadAuctionListForLiveBid");
		    });
			
			/*$('#otpId').bind("cut copy paste",function(e) {
		        e.preventDefault();
		    });*/
			
	});

var typeCode = ''
var auctionArray = new Array();
var auctionMaterialArray = new Array();
var baseRate = 0;
var lowestBid='';
var currentRate=0;
var partnerPreviousBid='';
var bidEndDate='';
var bidStartDate='';
var totalQuantity=0;
var bidderUniqueCode=0;
var currentLabelName='';
var decrementOrIncrement='';
var g_exGroupPriceRate=0;
var g_fddRateWithGST=0;
var myVar =0;
var timePeriod='10s';
var sessionVaildate=false;
var tenderId='';
function loadAuctionListForLiveBid(data){
	$("#autoRefresh").timer('remove');
	$('.pagination').children().remove();
	$(".leftPaneData").html("");
	var leftPanelHtml = "";
	var i = 0;
	var firstRow = null;
	var firstTahdrId = '';
	timePeriod=data.objectMap.autoRefreshTimer;
	if (!$.isEmptyObject(data.objectMap.tahdrList)) {
		$.each(data.objectMap.tahdrList, function(key, value) {
			var tahdrCode = value.tahdrCode == null ? '' : value.tahdrCode;
			var title = value.title == null ? '' : value.title;
			var emdFee = value.tahdrDetail[0].emdFee == null ? ''
					: value.tahdrDetail[0].emdFee;
			var minQuantity = value.tahdrDetail[0].minQuantity == null ? ''
					: value.tahdrDetail[0].minQuantity;
			var tahdrId = value.tahdrId == null ? '' : value.tahdrId;
			auctionArray['Auction_' + tahdrId] = value;
			if (i == 0) {
				firstRow = value;
				firstTahdrId = tahdrId;
				leftPanelHtml = leftPanelHtml
						+ ' <li class="active" onclick="showAuctionDetail('
						+ tahdrId + ')" id="' + tahdrId + '">';
			} else {
				leftPanelHtml = leftPanelHtml
						+ ' <li onclick="showAuctionDetail(' + tahdrId
						+ ')" id="' + tahdrId + '">';
			}

			leftPanelHtml = leftPanelHtml
					+ ' <a href="#Master" data-toggle="tab">'
					+ ' <div class="col-md-12">'
					+ '  <label class="col-xs-6" id="' + tahdrId
					+ '_tenderNo">' + tahdrCode + '</label>'
					+ '	<label class="col-xs-6" id="' + tahdrId + '_title">'
					+ title + '</label>' + ' </div>'
					+ ' <div class="col-md-12">'
					+ '	<label class="col-xs-6" id="' + tahdrId
					+ '_tahdrValidity">' + minQuantity + '</label>'
					+ '	<label class="col-xs-6" id="' + tahdrId + '_emdFee">'
					+ emdFee + '</label>' + ' </div>' + ' </a>' + ' </li>';
			i++;

		});
		$(".leftPaneData").html(leftPanelHtml);
		loadAuctionRightPane(firstRow);
		$('.tenderMaterialTab').removeClass('readonly');
	} else {
		loadAuctionRightPane(firstRow);
		$('.tenderMaterialTab').addClass('readonly');
		Alert.warn(data.objectMap.resultMessage);
	}

	$('.leftPaneData').paginathing({
		perPage : 6
	});
	$(".tabs-left li a label").text(function(index, currentText) {
		return currentText.substr(0, 20);
	});

	$(".tabs-left li a label").text(function(index, currentText) {
		return currentText.substr(0, 20);
	});
}

function loadAuctionRightPane(data) {
	if (!$.isEmptyObject(data)) {
		
		var tenderCode = data.tahdrCode == null ? '' : data.tahdrCode;
		var location=data.officeLocation==null?'':data.officeLocation.name;
		var tenderNo = data.title == null ? '' : data.title;
		var contactEmail = data.tahdrDetail[0].contactEmailId == null ? ''
				: data.tahdrDetail[0].contactEmailId;
		var description = data.tahdrDetail[0].description == null ? ''
				: data.tahdrDetail[0].description;
		var minBidDiffer = data.tahdrDetail[0].minBidDifference == null ? ''
				: data.tahdrDetail[0].minBidDifference;
		var version = data.tahdrDetail[0].version == null ? ''
				: data.tahdrDetail[0].version;
		var emd = data.tahdrDetail[0].emdFee == null ? ''
				: data.tahdrDetail[0].emdFee;
		var tahdrId = data.tahdrId == null ? '' : data.tahdrId;
		tenderId=tahdrId;
		tenderName=tenderNo;
		/*bidderCount=data.bidders.length;*/
		
		bidEndDate=data.tahdrDetail[0].auctionToDate==null?'':data.tahdrDetail[0].auctionToDate;
		bidStartDate=data.tahdrDetail[0].auctionFromDate==null?'':data.tahdrDetail[0].auctionFromDate;
		$('#tahdrForm #tenderNo').html(tenderCode);
		$('#tahdrForm #tenderVersion').html(version);
		$('#tahdrForm #description').html(description);
		$('#tahdrForm #emdFee').html(emd);
		$('#tahdrForm #tahdrId').val(tahdrId);
		$('#tenderDetailsForm #location').html(location);
		$('#tenderDetailsForm #tahdrNo').html(tenderCode);
		$("#tenderDetailsForm #decOrIncById").html(minBidDiffer);
		
		$('#otpFormId #tenderId').val(tahdrId);
		$('#bidFormId #tahdrId').val(tahdrId);

		setHeaderValues("Tender No.: " + tenderCode, "Time Left : "
				, "Contact EmailId : " + contactEmail,
				"Description : " + description);
		$('.rightTopClass').timer({
		    countdown: true,
		    duration: formateCurrentDate(bidEndDate),
		    callback: function() {
		    	$('.rightTopClass').html('Times Up !');
		    	window.reload();
		    }
		});
		submitToURL("getOverAllRank/" + tahdrId,"setOverRank");
	} else {
		$('#tahdrForm #tenderNo').html('');
		$('#tahdrForm #tenderVersion').html('');
		$('#tahdrForm #description').html('');
		$('#tahdrForm #emdFee').html('');
		$('#tahdrForm #tahdrId').val('');
		setHeaderValues("Tender No.: ", "Time Left : ",
				"Contact EmailId : ", "Description : ");
		bidEndDate='';
		$('#otpFormId #tenderId').val('');
		$('#bidFormId #tahdrId').val('');
	}
}
function setOverRank(data){
	if($.isEmptyObject(data.objectMap.result)){
		$('#tahdrForm #overAllRank').html(data.objectMap.overAllRank);
	}
}
function showAuctionDetail(tahdrId) {
	loadAuctionRightPane(auctionArray['Auction_' + tahdrId]);
}
function showBiddersDetail(tahdrId) {
	submitToURL("getBidderForLiveBidByTahdrId/" + tahdrId,
			"loadBiddersListForLiveBid");
}
function loadAuctionMaterialListForView(data) {
	$('.pagination').children().remove();
	$("#leftPane").html("");
	var leftPanelHtml = "";
	var i = 0;
	var firstRow = null;

	if(!$.isEmptyObject(data.objectMap.lowestBid)){
		lowestBid=data.objectMap.lowestBid;
	}
	if(!$.isEmptyObject(data.objectMap.partnerLatestBid)){
		partnerPreviousBid=data.objectMap.partnerLatestBid;
	}
	if(!$.isEmptyObject(data.objectMap.basePriceBid)){
		baseRate=data.objectMap.basePriceBid;
	}
	
	if (!$.isEmptyObject(data.objectMap.responseList)) {
		$.each(data.objectMap.responseList, function(key, value) {
			var materialData=value.tahdrMaterial==null?null:value.tahdrMaterial;
			bidderUniqueCode=value.bidder==null?0:value.bidder.created;
			var tahdrMaterialId = materialData.tahdrMaterialId == null ? ''
					: materialData.tahdrMaterialId;
			var name = materialData.material == null ? ''
					: materialData.material.name == null ? '' : materialData.material.name;
			var uomName = materialData.material == null ? ''
					: materialData.material.uom == null ? ''
							: materialData.material.uom.name == null ? ''
									: materialData.material.uom.name;
			var description = materialData.material == null ? ''
					: materialData.material.description == null ? ''
							: materialData.material.description;
			var hsnCode = materialData.material == null ? ''
					: materialData.material.hsnCode == null ? ''
							: materialData.material.hsnCode.code == null ? ''
									: materialData.material.hsnCode.code;

			auctionMaterialArray["AuctionMaterial_" + tahdrMaterialId] = materialData;
			if (i == 0) {
				firstRow = materialData;

				leftPanelHtml = leftPanelHtml + ' <li class="active" id="'
						+ tahdrMaterialId
						+ '"  onclick="showLiveBidByMaterialId(this)">';
			} else {
				leftPanelHtml = leftPanelHtml + ' <li class="itemBidLi" id="'
						+ tahdrMaterialId
						+ '"  onclick="showLiveBidByMaterialId(this)">';
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
		loadAuctionMaterialRightPane(firstRow,data.objectMap.rank);
	}else{
		$(".leftPaneData").html('');
		loadAuctionMaterialRightPane(null,null);
	}
	$('.leftPaneData').paginathing({
		perPage : 6
	});
	autoRefresh();
}
function autoRefresh(){
	$('#autoRefresh').timer({
	    duration: timePeriod,
	    callback: function() {
	    	var tahdrId= $('#tahdrForm #tahdrId').val();
	    	var tahdrMaterialId=$('.leftPaneData').find('li.active').attr('id');
	    	/*submitToURL('autoRefreshData/'+tahdrId+'/'+tahdrMaterialId,'autoRefreshDataView');
	    	submitToURL('getBidderListByTahdrId/'+tahdrId+'/'+tahdrMaterialId,'bidderListResp');*/
	    	$('#autoRefresh').timer('reset');
	    },
	   repeat: true
	});
}
function autoRefreshDataView(data){
	$('#autoRefresh').timer('pause');
	if(data.objectMap.result){
		var lowestBid=data.objectMap.lowestBid;
		var mylowestBid=data.objectMap.mylowestBid;
		var mynewLowestBidValue=mylowestBid==null?'0':mylowestBid.fddRateWithGST;
		var newLowestBidValue=lowestBid==null?'0':lowestBid.fddRateWithGST;
		
		$('.detailscont #middleRightId').html(currentLabelName+" Rate(In Rs): "+newLowestBidValue);
		$('.detailscont #bottomLeftId').html("My "+currentLabelName+" rate(In Rs) : "+mynewLowestBidValue);
		$('.detailscont #bottomRightId').html("Req. Quantity:"+totalQuantity);
		var currentRank=data.objectMap.rank;
		$('#bidderDetailForm #rank').html(currentRank);
		$('#autoRefresh').timer('resume');
	}else{
		$('#autoRefresh').timer('reset');
	}
	
}
function loadAuctionMaterialRightPane(data,rank) {
	debugger;
	currentLabelName=typeCode =='FA'?'Highest':'Lowest';
	decrementOrIncrement=typeCode =='FA'?'Increment By :':'Decrement By :';
	if (!$.isEmptyObject(data)) {
		var tahdrMaterialId = data.tahdrMaterialId == null ? ''
				: data.tahdrMaterialId;
		var name = data.material == null ? '' : data.material.name;
		var code = data.material == null ? '' : data.material.code;
		var uomName = data.material.uom == null ? ''
				: data.material.uom.name;
		var totalQty = data.quantity== null ? ''
				: data.quantity;
		var baseRateValue=data.basePriceRate==null?0:data.basePriceRate;
		totalQuantity=totalQty;
		$("#itemDetailForm #tahdrMaterialId").val(tahdrMaterialId);
		
		$("#tenderDetailsForm #uom").html(uomName);
		$("#tenderDetailsForm #decOrIncByLblId").html(decrementOrIncrement);
		$("#tenderDetailsForm #qtyId").html(totalQty);
		$("#tenderDetailsForm #bidEndDateId").html( formatDateTime(bidEndDate));
		$("#tenderDetailsForm #bidStartDateId").html( formatDateTime(bidStartDate));
		$("#tenderDetailsForm #psuedoName").html(bidderUniqueCode);
		$('#bidFormId #tahdrMaterialId').val(tahdrMaterialId);
		
		loadPartnerPreviosBid(partnerPreviousBid);
		setBidderDetail(partnerPreviousBid);
		currentRate=lowestBid.fddRateWithGST==null?0:lowestBid.fddRateWithGST;
		
		$('#bidFormId #preTotalFDDAmountWithGST').val(currentRate);
		debugger;
		setHeaderValuesWithMoreParam("Material Name.: " + name, "Time Left : ", "Base Price Rate (in Rs): "+baseRateValue ,
				currentLabelName+" Rate (in Rs): "+currentRate,"My "+currentLabelName+" rate (in Rs): "+g_fddRateWithGST," Req. Quantity:"+totalQty );
		
		$('.rightTopClass').timer({
		    countdown: true,
		    duration: formateCurrentDate(bidEndDate),
		    callback: function() {
		    	$('.rightTopClass').html('Times Up !');
		    }
		});
		$('#bidderDetailForm #rank').html(rank);
		$('.bluebutton').removeAttr('disabled');
		getBidderCount();
		$('#bidHistoryIconId').attr("onclick","return submitWithTwoParam('getBidderListByTahdrId','tahdrId','itemDetailForm #tahdrMaterialId','bidderListResp');");
		submitWithTwoParam('getBidderListByTahdrId','tahdrId','itemDetailForm #tahdrMaterialId','bidderListResp');
	}else{
		setHeaderValuesWithMoreParam("Material Name.: ", "Time Left : ", "Base Price Rate (in Rs): " ,
				" Rate (in Rs): ","My  rate (in Rs): "," Req. Quantity:" );
		
		
		$("#tenderDetailsForm #uom").html('');
		$("#tenderDetailsForm #decOrIncByLblId").html(decrementOrIncrement);
		$("#tenderDetailsForm #qtyId").html('');
		$("#tenderDetailsForm #bidEndDateId").html( '');
		$("#tenderDetailsForm #noOfBidderId").html('');
		$("#tenderDetailsForm #location").html('');
		
		$('#bidFormId #tahdrMaterialId').val('');
		$('.bluebutton').attr('disabled','disabled');
		$('#bidderDetailForm #rank').html(0);
		$('#bidHistoryIconId').removeAttr('onclick');
		
		setBidderDetail(null);
	}
}
function getBidderCount(){
	var data=fetchList("getBidderListByTahdrId/"+tenderId,null);
	var bidderCount=0;
	if(!$.isEmptyObject(data.objectMap.responseList)){
		 
				$.each(data.objectMap.responseList,function(key,value){
					if(value.status=='PBOP'){
						bidderCount++;
					}
				});
			}
	$("#tenderDetailsForm #noOfBidderId").html(bidderCount);
}
function showAuctionMaterialDetail(el) {
	var tahdrMaterialId = $(el).attr('id');
	loadAuctionMaterialRightPane(auctionMaterialArray['AuctionMaterial_'
			+ tahdrMaterialId]);
}

function showLiveBidByMaterialId(el){
	debugger;
	var tahdrMaterialId = $(el).attr('id');
	$("#itemDetailForm #tahdrMaterialId").val(tahdrMaterialId);
	var tahdrId = $('#tahdrForm #tahdrId').val();
	submitToURL('getLiveBidByTahdrId/'+tahdrId+'/'+tahdrMaterialId,'loadLiveBidView');
	
	loadAuctionMaterialRightPane(auctionMaterialArray['AuctionMaterial_'
			+ tahdrMaterialId]);
	$(".selfbidHistory").DataTable().destroy();
	$('.selfbidHistory tbody').empty();
	$('#autoRefresh').timer('reset');
	/*$(".biddertable").DataTable().destroy();
	$('.biddertable tbody').empty();*/
}
function loadLiveBidView(data){
	 
	$('#bidderDetailForm #rank').html('NA');
	if(!$.isEmptyObject(data.objectMap.lowestBid)){
		lowestBid=data.objectMap.lowestBid;
	}else{
		lowestBid='';
	}
	if(!$.isEmptyObject(data.objectMap.partnerLatestBid)){
		partnerPreviousBid=data.objectMap.partnerLatestBid;
	}else{
		partnerPreviousBid='';
	}
	if(!$.isEmptyObject(data.objectMap.newDetail)){
		bidEndDate=data.objectMap.newDetail.auctionToDate==null?'':data.objectMap.newDetail.auctionToDate;
		/*$('.rightTopClass').timer({
		    countdown: true,
		    duration: formateCurrentDate(bidEndDate),
		    callback: function() {
		    	$('.rightTopClass').html('Times Up !');
		    }
		});*/
	}
	
		$('#bidderDetailForm #rank').html(data.objectMap.rank);
	
}
function loadPartnerPreviosBid(data) {
	 
	$('.bidtableedit tbody').empty();
	if(data!=''){
		var priceBidId=data.priceBidId==null?'':data.priceBidId;
		$('#bidFormId #priceBidId').val(priceBidId);
		
		var offeredQuantity=data.offeredQuantity==null?'':data.offeredQuantity;
		var ticRate=data.ticRate==null?'':data.ticRate;
		var freightChargeRate=data.freightChargeRate==null?'':data.freightChargeRate;
		var exGroupPriceRate=data.exGroupPriceRate==null?'':data.exGroupPriceRate;
		var fddRateWithGST=data.fddRateWithGST==null?'':data.fddRateWithGST;
		g_exGroupPriceRate=exGroupPriceRate;
		g_fddRateWithGST=fddRateWithGST;
		$('#bidFormId #inputExGroupPriceRateId').val(exGroupPriceRate);
		
		
	}else{
		$('#bidFormId #priceBidId').val();
		
		$('#bidderDetailForm #propQty').html(0);
		}
}

function setBidderDetail(data){
	 
	if(!$.isEmptyObject(data)){
		var priceBid=data;
		var priceBidId=priceBid.priceBidId==null?'':priceBid.priceBidId;
		var offeredQuantity=priceBid.offeredQuantity==null?'':priceBid.offeredQuantity;
		var exGroupPriceRate=priceBid.exGroupPriceRate==null?'':priceBid.exGroupPriceRate;
		var freightChargeRate=priceBid.freightChargeRate==null?'':priceBid.freightChargeRate;
		var ticRate=priceBid.ticRate==null?'':priceBid.ticRate;
		var taxRate=priceBid.taxRate==null?'':priceBid.taxRate;
		var taxAmount=priceBid.taxAmount==null?'':priceBid.taxAmount;
		var fddRate=priceBid.fddRate==null?'':priceBid.fddRate;
		var fddInWords=priceBid.fddInWords==null?'':priceBid.fddInWords;
		var isConfirmed=priceBid.isConfirmed==null?'':priceBid.isConfirmed;
		
		var igst=getValue(priceBid.igst);
		var sgst=getValue(priceBid.cgst);
		var cgst=getValue(priceBid.sgst);
		
		var totalExGroupPrice=getValue(priceBid.totalExGroupPrice);
		var totalFreightCharges=getValue(priceBid.totalFreightCharge);
		var totalTic=getValue(priceBid.totalTic);
		var fddRate=getValue(priceBid.fddRate);
		var fddAmount=getValue(priceBid.fddAmount);
		var taxAmount=getValue(priceBid.taxAmount);
		var totalTax=getValue(priceBid.totalTax);
		var fddRateGST=getValue(priceBid.fddRateWithGST);
		var fddAmountGST=getValue(priceBid.fddAmountWithGST);
		var igstAmount=getValue(priceBid.igstAmount);
		var sgstAmount=getValue(priceBid.cgstAmount);
		var cgstAmount=getValue(priceBid.sgstAmount);
		
		$("#bidderDetailForm #priceBidId").html(priceBidId);
		$("#bidderDetailForm #offeredQuantity").html(offeredQuantity);
		$("#bidderDetailForm #exGroupPriceRate").html(exGroupPriceRate);
		$("#bidderDetailForm #freightChargeRate").html(freightChargeRate);
		$("#bidderDetailForm #ticRate").html(ticRate); //transit insurance charges
		$("#bidderDetailForm #taxRate").html(taxRate);
		
		/*$("#bidderDetailForm #igstLable").text(igst+"%");
		$("#bidderDetailForm #cgstLable").text(cgst+"%");
		$("#bidderDetailForm #sgstLable").text(sgst+"%");*/
		
		$("#bidderDetailForm #igst").html(igst);
		$("#bidderDetailForm #cgst").html(cgst);
		$("#bidderDetailForm #sgst").html(sgst);
		
		$("#bidderDetailForm #taxAmount").html(taxAmount);
		$("#bidderDetailForm #fddRate").html(fddRate);
		$("#bidderDetailForm #fddInWords").html(fddInWords);
		$("#bidderDetailForm #isConfirmed").html(isConfirmed);
		
		$("#bidderDetailForm #totalExGroupPrice").html(totalExGroupPrice);
		$("#bidderDetailForm #totalFreightCharges").html(totalFreightCharges);
		$("#bidderDetailForm #totalTic").html(totalTic);
		$("#bidderDetailForm #fddRate").html(fddRate);
		$("#bidderDetailForm #fddAmount").html(fddAmount);
		$("#bidderDetailForm #taxAmount").html(taxAmount);
		$("#bidderDetailForm #totalTax").html(totalTax);
		$("#bidderDetailForm #igstAmount").html(igstAmount);
		$("#bidderDetailForm #cgstAmount").html(cgstAmount);
		$("#bidderDetailForm #sgstAmount").html(sgstAmount);
		$("#bidderDetailForm #fddRateGST").html(fddRateGST);
		$("#bidderDetailForm #fddAmountGST").html(fddAmountGST);
	}else{
		$("#bidderDetailForm #priceBidId").html('');
		$("#bidderDetailForm #offeredQuantity").html('');
		$("#bidderDetailForm #exGroupPriceRate").html('');
		$("#bidderDetailForm #freightChargeRate").html('');
		$("#bidderDetailForm #ticRate").html(''); 
		$("#bidderDetailForm #taxRate").html('');
		
		$("#bidderDetailForm #igstLable").html('');
		$("#bidderDetailForm #cgstLable").html('');
		$("#bidderDetailForm #sgstLable").html('');
		
		$("#bidderDetailForm #igst").html('');
		$("#bidderDetailForm #cgst").html('');
		$("#bidderDetailForm #sgst").html('');
		
		$("#bidderDetailForm #taxAmount").html('');
		$("#bidderDetailForm #fddRate").html('');
		$("#bidderDetailForm #fddInWords").html('');
		$("#bidderDetailForm #isConfirmed").html('');
		
		$("#bidderDetailForm #totalExGroupPrice").html('');
		$("#bidderDetailForm #totalFreightCharges").html('');
		$("#bidderDetailForm #totalTic").html('');
		$("#bidderDetailForm #fddRate").html('');
		$("#bidderDetailForm #fddAmount").html('');
		$("#bidderDetailForm #taxAmount").html('');
		$("#bidderDetailForm #totalTax").html('');
		$("#bidderDetailForm #igstAmount").html('');
		$("#bidderDetailForm #cgstAmount").html('');
		$("#bidderDetailForm #sgstAmount").html('');
		$("#bidderDetailForm #fddRateGST").html('');
		$("#bidderDetailForm #fddAmountGST").html('');
	}
	
}
function resetToPreviousBid(){
	 
   /*  var tahdrMaterialId=$("#itemDetailForm #tahdrMaterialId").val();
     var tahdrId=$('#tahdrForm #tahdrId').val();
     if(tahdrId!='' && tahdrMaterialId!=''){
     	submitToURL("getLiveBidByTahdrId/" + tahdrId+"/"+tahdrMaterialId,'loadLiveBidView');
     	
     	loadAuctionMaterialRightPane(auctionMaterialArray['AuctionMaterial_'
     			+ tahdrMaterialId]);
		
     }else{
     	Alert.warn('Something went wrong !');
     }*/
	var tahdrId= $('#tahdrForm #tahdrId').val();
	var tahdrMaterialId=$('.leftPaneData').find('li.active').attr('id');
	submitToURL('autoRefreshData/'+tahdrId+'/'+tahdrMaterialId,'autoRefreshDataView');
	submitToURL('getBidderListByTahdrId/'+tahdrId+'/'+tahdrMaterialId,'bidderListResp');
	$('#autoRefresh').timer('reset');
}
function saveNewBidResp(data){
	 
	if(!$.isEmptyObject(data.objectMap)){
		if(data.objectMap.result){
			$('#bidderDetailForm #rank').html(1);
			var currentRate=$("#bidderDetailForm #fddRateGST").html();
			if(data.objectMap.isAuctionExtended){
				var bidEndDate=data.objectMap.newDetail==null?'':data.objectMap.newDetail.auctionToDate==null?'':data.objectMap.newDetail.auctionToDate;
				$('.rightTopClass').timer({
				    countdown: true,
				    duration: formateCurrentDate(bidEndDate),
				    callback: function() {
				    	$('.rightTopClass').html('Times Up !');
				    }
				});
			}
			/*$('.detailscont #rightBottomId').html(currentLabelName+" Rate : "+currentRate+" &nbsp;&nbsp;&nbsp;&nbsp; My "+currentLabelName+" rate : "+g_exGroupPriceRate+" &nbsp;&nbsp; Req. Quantity:"+totalQuantity);*/
			$('.detailscont #middleRightId').html(currentLabelName+" Rate(In Rs): "+currentRate);
			$('.detailscont #bottomLeftId').html("My "+currentLabelName+" rate(In Rs): "+g_fddRateWithGST);
			$('.detailscont #bottomRightId').html("Req. Quantity:"+totalQuantity);
			Alert.info(data.objectMap.resultMessage);
		}else{
			var bidEndDate=data.objectMap.newDetail==null?'':data.objectMap.newDetail.auctionToDate==null?'':data.objectMap.newDetail.auctionToDate;
			$('.rightTopClass').timer({
			    countdown: true,
			    duration: formateCurrentDate(bidEndDate),
			    callback: function() {
			    	$('.rightTopClass').html('Times Up !');
			    }
			});
			Alert.warn(data.objectMap.resultMessage);
		}	
	}else{
		Alert.warn('something went wrong !');
	}
	
}
function myBidHistory(){
	var tahdrMaterialId=$("#bidFormId #tahdrMaterialId").val();
    var tahdrId=$('#bidFormId #tahdrId').val();
    submitToURL("getBidListByTahdrId/" + tahdrId+"/"+tahdrMaterialId,'loadSelfBidHistoryView');
}
function loadSelfBidHistoryView(data){
	$(".selfbidHistory").DataTable().destroy();
	$('.selfbidHistory tbody').empty();
	if(!$.isEmptyObject(data.objectMap.bidHistory)){
		 
		$.each(data.objectMap.bidHistory, function(key, value) {
		
		var partnername=value.partner==null?'':value.partner.name;
		var factoryName=value.itemBid==null?'':value.itemBid.bidder==null?'':value.itemBid.bidder.factory==null?''
				:value.itemBid.bidder.factory.name;
		
		var offeredQuantity=value.offeredQuantity==null?'':value.offeredQuantity;
		var date=value.updated==null?'':formatDateTime(value.updated);
		var exGroupPriceRate=value.exGroupPriceRate==null?'':value.exGroupPriceRate;
		var fddAmountWithGST=value.fddAmountWithGST==null?'':value.fddAmountWithGST;
		var prevFddAmountWithGST=value.previousFddAmountWithGST==null?'':value.previousFddAmountWithGST;
		$('.selfbidHistory tbody').append('<tr>' + 
				'<td class="col-sm-2">'+offeredQuantity+'</td>' + 
				'<td class="col-sm-2">'+date+'</td>'+
				'<td class="col-sm-2">'+prevFddAmountWithGST+'</td>'+
				'<td class="col-sm-2">'+exGroupPriceRate+'</td>'+
				'<td class="col-sm-2">'+fddAmountWithGST+'</td>');
		});
	}
	$('.selfbidHistory').DataTable({
	});

}
function bidderListResp(data){
	var count=0;
	var i=0;
	var leftqty=0;
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
	/*$(".biddertable").DataTable().destroy();
	$('.biddertable thead').empty();
	$('.biddertable thead').append('<tr> <th class="col-sm-2">Bidder Unique Code</th>' + 
			'<th class="col-sm-2">Offered Qty</th>' + 
			'<th class="col-sm-2">Date Of Bidding</th>'+
			'<th class="col-sm-2">Bid Amount</th>'+
			'<th class="col-sm-2">Total Amount</th></tr>');*/
    $(".biddertable").DataTable().destroy();
	$('.biddertable tbody').empty();
	if(!$.isEmptyObject(data.objectMap.bidHistory)){
		
		$.each(data.objectMap.bidHistory, function(key, value) {
			 
		var partnername=value.partner==null?'':value.partner.name;
		var factoryName=value.itemBid==null?'':value.itemBid.bidder==null?'':value.itemBid.bidder.factory==null?''
				:value.itemBid.bidder.factory.name;
		var unique=value.itemBid.bidder.created;
		var offeredQuantity=value.offeredQuantity==null?'':value.offeredQuantity;
		var date=value.updated==null?'':formatDateTime(value.updated);
		var exGroupPriceRate=value.exGroupPriceRate==null?'':value.exGroupPriceRate;
		var fddAmountWithGST=value.fddAmountWithGST==null?'':value.fddAmountWithGST;
		var fddRateWithGST=value.fddRateWithGST==null?'':value.fddRateWithGST;
		count=count+Number(offeredQuantity);
		debugger;
		if(totalQuantity>=count){
			$('.biddertable tbody').append('<tr class="greenrow"><td class="col-sm-2">'+unique+'</td>' + 
					'<td class="col-sm-2">'+offeredQuantity+'</td>' + 
					'<td class="col-sm-2">'+date+'</td>'+
					'<td class="col-sm-2">'+exGroupPriceRate+'</td>'+
					'<td class="col-sm-2">'+fddRateWithGST+'</td>'+
					'<td class="col-sm-2">'+fddAmountWithGST+'</td>');
			leftqty=totalQuantity-count;
		}else{
			if(i==0 && leftqty>0){
				$('.biddertable tbody').append('<tr class="greenrow"><td class="col-sm-2">'+unique+'</td>' + 
						'<td class="col-sm-2">'+offeredQuantity+'</td>' + 
						'<td class="col-sm-2">'+date+'</td>'+
						'<td class="col-sm-2">'+exGroupPriceRate+'</td>'+
						'<td class="col-sm-2">'+fddRateWithGST+'</td>'+
						'<td class="col-sm-2">'+fddAmountWithGST+'</td>');
			}else{
				$('.biddertable tbody').append('<tr><td class="col-sm-2">'+unique+'</td>' + 
						'<td class="col-sm-2">'+offeredQuantity+'</td>' + 
						'<td class="col-sm-2">'+date+'</td>'+
						'<td class="col-sm-2">'+exGroupPriceRate+'</td>'+
						'<td class="col-sm-2">'+fddRateWithGST+'</td>'+
						'<td class="col-sm-2">'+fddAmountWithGST+'</td>');
			}
			i++;
		}
		
	});
		$('.biddertable').DataTable({
			"lengthMenu":lengthMenu,
			"ordering": false,
			"columnDefs": [
			    { "width": "15%", "targets": 0 },
			    { "width": "15%", "targets": 1 },
			    { "width": "15%", "targets": 2 },
			    { "width": "20%", "targets": 3 },
			    { "width": "20%", "targets": 4 },
			    { "width": "15%", "targets": 5 }			    
			  ]
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
		
		
	}else{
		/*$('.biddertable tbody').append('<tr><td class="col-sm-2"></td>' + 
				'<td class="col-sm-2"></td>' + 
				'<td class="col-sm-2"></td>' + 
				'<td class="col-sm-2"></td>' +
				'<td class="col-sm-2"></td>' +
				'<td class="col-sm-2"></td>'); */
		
		}
       
}