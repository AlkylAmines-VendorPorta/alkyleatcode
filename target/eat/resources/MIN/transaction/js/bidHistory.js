function getSelfBidHistory(){
	submitWithTwoParam("getSelfBidHistory", 'itemDetailForm #tahdrMaterialId','bidderDetailForm #bidderId', "populateSelfBidHistory");
}

function getCompleteBidHistory(){
	submitWithParam("getCompleteBidHistory", 'itemDetailForm #tahdrMaterialId', "populateCompleteBidHistory");
}

function populateSelfBidHistory(data){
	populateBidList(data,'selfBidHistoryTable');
}

function populateCompleteBidHistory(data){
	populateBidList(data,'completeBidHistoryTable');
}

function populateBidList(data,tableClass){
		$("."+tableClass).DataTable().destroy();
		$('.'+tableClass+' tbody').empty();
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
			$('.'+tableClass+' tbody').append('<tr>' + 
					'<td class="col-sm-2">'+partnername+'</td>' + 
					'<td class="col-sm-2">'+offeredQuantity+'</td>' + 
					'<td class="col-sm-2">'+date+'</td>'+
					'<td class="col-sm-2">'+prevFddAmountWithGST+'</td>'+
					'<td class="col-sm-2">'+exGroupPriceRate+'</td>'+
					'<td class="col-sm-2">'+fddAmountWithGST+'</td>');
			});
		}
		$('.'+tableClass).DataTable({
		});
	
}