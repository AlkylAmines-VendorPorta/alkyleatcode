function loadVerticalBiddersListForLiveBid(data) {
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
		$(".v_listolivefbider").DataTable().destroy();
		$('.v_listolivefbider tbody').empty();
		$.each(data, function(key, value) {
			var bidderData=value;
			var partnername=bidderData.partner==null?'':bidderData.partner.name;
			var partnerId=bidderData.partner==null?'':bidderData.partner.bPartnerId;
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
			
			var getRank=rankArray['rankMap_'+partnerId]==undefined?'#':rankArray['rankMap_'+partnerId];
			var rank=getRank;
			if(key==0){
				$('.v_listolivefbider tbody').append('<tr class="greenrow"><td>'+partnername+'</td>' + 
						'<td>'+rank+'</td>' +
						'<td>'+offeredQuantity+'</td>' + 
						'<td>'+exGroupPriceRate+'</td>' +
						'<td>'+freightChargeRate+'</td>' + 
						'<td>'+ticRate+'</td>' + 
						'<td>'+ticRate+'</td>' + 
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
	            	$('.v_listolivefbider tbody').append('<tr><td>'+partnername+'</td>' + 
						'<td>'+rank+'</td>' + 
						'<td>'+offeredQuantity+'</td>' + 
						'<td>'+exGroupPriceRate+'</td>' +
						'<td>'+freightChargeRate+'</td>' + 
						'<td>'+ticRate+'</td>' + 
						'<td>'+ticRate+'</td>' +
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
		$('.v_listolivefbider tbody').empty();
		Alert.warn(data.objectMap.resultMessage);
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
	if ($(window).width() < 768) {
		$('.dataTables_length').parent().addClass('col-xs-6');
	
	 $('.dataTables_filter').parent().addClass('col-xs-6');
	}
	
	 var currentMode = $("#bidSheetTblId").data("tp_mode");
     if (currentMode == undefined) {
         $("#bidSheetTblId").transpose("transpose");
         $("#btnTpVertical").html("Reset");
     }
     else {
         $("#bidSheetTblId").transpose("reset");
         $("#btnTpVertical").html("Transpose");
     }
}