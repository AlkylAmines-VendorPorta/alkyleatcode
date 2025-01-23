function loadActivity(){
	hideAllActivity();
	$('#leftPaneData li:not(.active)').remove();
	var sessionString=$('#sessionFormId #sessionId').text();
	if(sessionString!="" && sessionString!=undefined){
		submitToURL("getSessionActivity/"+sessionString,"loadActivityResp");
	}else{
		Alert.warn('Something is wrong !');
	}
}

function loadActivityResp(data){
	if(data.objectMap.result){
		console.log(data);
		setActivityView(data.objectMap.role);
	}else{
		Alert.warn(data.objectMap.message);
	}
}

function setActivityView(role){
	hideAllActivity()
	if(role=='VENADM'){
		$('#priceBidTblDivId').show();
	}else{
		console.log(role);
	}
}

function hideAllActivity(){
	$('#priceBidTblDivId').hide();
}

function onSessionActivityTabLoad(data){
	loadBidList(data.objectMap.priceBidList);
}

function getAllBidsSubmitted(ele){
	var flag=$(ele).data('open');
	if(flag=='false'){
		var sessionString=$('#sessionFormId #sessionId').text();
		if(sessionString!="" && sessionString!=undefined){
			submitToURL("getBidsSubmittedBySessionKey/"+sessionString,"onSessionActivityTabLoad");
		}else{
			Alert.warn('Something is wrong !');
		}
	}
}

function loadBidList(data){
	if (!$.isEmptyObject(data)) {
		$('#collapseableDivId').data('open','true');
		$("#price_Bid_Table").DataTable().destroy();
		$('#price_Bid_Table tbody').empty();
		$.each(data, function(key, value) {
			var bidderData=value;
			var partnerId=bidderData.partner==null?'':bidderData.partner.bPartnerId;var offeredQuantity=bidderData.offeredQuantity==null?'':bidderData.offeredQuantity;
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
			var matName=bidderData.itemBid==null?'':bidderData.itemBid.tahdrMaterial==null?
					 '':bidderData.itemBid.tahdrMaterial.material==null?'':bidderData.itemBid.tahdrMaterial.material.name;
			var tenderCode=bidderData.itemBid==null?'':bidderData.itemBid.bidder==null?
					 '':bidderData.itemBid.bidder.tahdr==null?'':bidderData.itemBid.bidder.tahdr.tahdrCode;
			var tenderVersion=bidderData.itemBid==null?'':bidderData.itemBid.tahdrMaterial==null?
					 '':bidderData.itemBid.tahdrMaterial.tahdrDetail==null?'':bidderData.itemBid.tahdrMaterial.tahdrDetail.version;
			$('#price_Bid_Table tbody').append('<tr class="">' +
						'<td>'+tenderCode+'</td>' + 
						'<td>'+tenderVersion+'</td>' + 
					    '<td>'+matName+'</td>' + 
						'<td>'+offeredQuantity+'</td>' + 
						'<td>'+exGroupPriceRate+'</td>' +
						'<td>'+freightChargeRate+'</td>' + 
						'<td>'+ticRate+'</td>' + 
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
		});
		
	} else {
		$('#price_Bid_Table tbody').empty();
	}
}