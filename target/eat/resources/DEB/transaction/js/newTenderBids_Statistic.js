function loadStatisticData(){
	submitWithTwoParam('getStatisticDetailsListByMaterial','tenderDetailsForm #tahdrId','itemDetailForm #tahdrMaterialId','bidderCount');
}

function bidderCount(data){
	if(data.objectMap.result){
		$('#leftPaneData li:not(.active)').hide();
		if(activeTenderStatus=='PU'){
			$('#bidderStastisticTblId').removeAttr('style');
			$("#statisticForm #bidderCountDiv").hide();
			$("#statisticForm #bidderPriceBidOpenedDiv").hide();
			otherBidderCount(data.objectMap.bidders);
			$('.bidderCountTable tbody').html('');
			$('.bidderCountTable tbody').append('<tr><td>'+tenderPurchased +'</td>' + 
					'<td>'+exemptionCount+'</td>' + 
					'<td>'+data.objectMap.bidderCount+'</td>' + 
					'<td>'+$("#itemDetailForm #matName").html()+'</td>' + 
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

function otherBidderCount(data){
	tenderPurchased=0;
	exemptionCount=0;
	if(!$.isEmptyObject(data)){
		$.each(data, function(key, value) {
			if(value.tenderPurchase.paymentMode=='ISEXEMP'){
				exemptionCount++;
			}else{
				tenderPurchased++;
			}
		});
		$("#statisticForm #purchasedbidderCount").html(tenderPurchased);
		$("#statisticForm #emdBidderCount").html(exemptionCount);
	}else{
		$("#statisticForm #purchasedbidderCount").html(tenderPurchased);
		$("#statisticForm #emdBidderCount").html(exemptionCount);
	}
}
