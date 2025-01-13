function loadQuickBidderAllBids(){
	var tahdrMaterialId=$("#itemDetailForm #tahdrMaterialId").val();
	var bidderId=$("#bidderDetailForm #bidderId").val();
	if(bidderId!='' && tahdrMaterialId!=''){
	    submitToURL("getQuickBidderAllBids/" + bidderId+"/"+tahdrMaterialId,'loadQuickBidderAllBidsResp');
	}else{
		Alert.warn('Something is wrong');
	}
}

function loadQuickBidderAllBidsResp(data){
	if (!$.isEmptyObject(data.objectMap)) {
		if(data.objectMap.result){
			$("#price_Bid_Table").DataTable().destroy();
			$('#price_Bid_Table tbody').empty();
			$.each(data.objectMap.bidHistory, function(key, value) {
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
							/*'<td>'+tenderCode+'</td>' + 
							'<td>'+tenderVersion+'</td>' + */
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
		}else{
			
				Alert.warm(data.objectMap.resultMessage);
		}
		
	} else {
		$('#price_Bid_Table tbody').empty();
	}
	$('.price_Bid_Table').DataTable({
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