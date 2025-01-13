
var tenderArray=new Array();

$(document).ready(function() {
	onPageLoad();

	$(".saveRating").on("click", function(event) {
		event.preventDefault();
		submitIt("rating", "saveRating", "saveRatingresp");
	});
    
    $("input[name='tenderTypeCodeToggle']").on("change",function(){
    	var typeCode=$('input[name=tenderTypeCodeToggle]:checked').val();
    	submitToURL("getTahdrForRating/"+typeCode, "loadTenderListForRating");	
    });
});

function onPageLoad(){
	var typeCode=$('input[name=tenderTypeCodeToggle]:checked').val();
	submitToURL("getTahdrForRating/"+typeCode, "loadTenderListForRating");
}
function loadTenderListForRating(data){
			$('.pagination').children().remove();
			$(".leftPaneData").html("");
			var leftPanelHtml="";
			var i=0;
			var firstRow=null;
			if(!$.isEmptyObject(data.objectMap.WinnerSelectionList)){
					$.each(data.objectMap.WinnerSelectionList,function(key,value){
								var tahdrCode = value.tahdr.tahdrCode==null?'': value.tahdr.tahdrCode;
								var tahdrId = value.tahdr.tahdrId==null?'': value.tahdr.tahdrId;
								var material=value.itemBid.tahdrMaterial==null?'':value.itemBid.tahdrMaterial.material==null?'':value.itemBid.tahdrMaterial.material.name;
								var partner=value.itemBid==null?'':value.itemBid.partner==null?'':value.itemBid.partner.name;
								
								tenderArray["wnrSecId_"+value.winnerSelectionId]=value;
								
								if(i==0){
									firstRow = value;
									leftPanelHtml = leftPanelHtml + ' <li class="active" onclick="showDetail('+value.winnerSelectionId+')" id="'+value.winnerSelectionId+'">';
								}else{
									leftPanelHtml = leftPanelHtml + ' <li onclick="showDetail('+value.winnerSelectionId+')" id="'+value.winnerSelectionId+'">';
								}
							
								leftPanelHtml = leftPanelHtml +' <a href="#Master" data-toggle="tab">'
							    +' <div class="col-md-12">'
							    +'  <label class="col-xs-6" id="'+tahdrId+'_tenderNo">'+tahdrCode+'</label>'
							    +'	<label class="col-xs-6" id="'+tahdrId+'_title"></label>'
							    +' </div>'	
							    +' <div class="col-md-12">'
							    +'	<label class="col-xs-6" id="'+tahdrId+'_material">'+material+'</label>'
								+'	<label class="col-xs-6" id="'+tahdrId+'_user">'+partner+'</label>'
								+' </div>'
							    +' </a>'
							    +' </li>';
								i++;
					});
				
			}
			else{
				
			}
			$(".leftPaneData").html(leftPanelHtml);
			loadTahdrDetailRightPane(firstRow);
			$('.leftPaneData').paginathing({perPage: 6});
}
function loadTahdrDetailRightPane(data){
	debugger;
	if(!$.isEmptyObject(data)){
		var companyName= data.itemBid==null?'':data.itemBid.partner==null?'':data.itemBid.partner.name;
		var factoryName= data.itemBid==null?'':data.itemBid.bidder==null?'':data.itemBid.bidder.factory==null?'':data.itemBid.bidder.factory.name;
		var alloccatedQty= data.allocatedQty;
		var tenderNo= data.itemBid==null?'':data.itemBid.bidder==null?'':data.itemBid.bidder.tahdr==null?'':data.itemBid.bidder.tahdr.tahdrCode;
		$('#bidderDetailsForm #factoryName').val(companyName);
		$('#bidderDetailsForm #factoryOfPurchased').val(factoryName);
		$('#bidderDetailsForm #allocateQty').val(alloccatedQty);
		$('#bidderDetailsForm #tahdrNo').val(tenderNo);
		$('.winnerSelectionId').val(data.winnerSelectionId);
		$('.bidderId').val(data.itemBid.bidder.bidderId);
		$('#rating').removeClass('readonly');
			if(data.qualityRating != null){
				loadRatingData(data);
			}else{
				resetRatingFrm();
			}
	}else{
		$('#bidderDetailsForm #factoryName').val('');
		$('#bidderDetailsForm #factoryOfPurchased').val('');
		$('#bidderDetailsForm #allocateQty').val(0);
		$('#bidderDetailsForm #tahdrNo').val('');
		$('#rating').addClass('readonly');
	}
	
}
function showDetail(wnrsecid){
	loadTahdrDetailRightPane(tenderArray["wnrSecId_"+wnrsecid]);
	
}
function saveRatingresp(data) {
	debugger
	if(data.objectMap.result){
		var id = $('#leftPane li.active').attr('id');
		var ratingData=data.objectMap.ratingMap;
		tenderArray["wnrSecId_"+id].qualityRating = ratingData.qualityRating
		tenderArray["wnrSecId_"+id].deliveryRating = ratingData.deliveryRating
		tenderArray["wnrSecId_"+id].serviceRating = ratingData.serviceRating
		tenderArray["wnrSecId_"+id].priceRating = ratingData.priceRating
		loadRatingData(ratingData);
		Alert.info(data.objectMap.message);
	}else{
		Alert.warn(data.objectMap.message);
	}
}

function loadRatingData(data){
	debugger;
	var averageRating;
	averageRating = ((data.qualityRating + data.qualityRating + data.qualityRating + data.qualityRating) * 10) / 40
	$('.saveRating').hide();
	$('#averageRating').text(averageRating);
	$('#weightageRating').text(data.rating);
	$('.Star_rating').barrating('destroy');
	setRating('qualityRating', data.qualityRating);
	setRating('deliveryRating', data.deliveryRating);
	setRating('serviceRating', data.serviceRating);
	setRating('priceRating', data.priceRating);
}

function setRating(id, rate) {
	$('#' + id).barrating({
		initialRating : rate,
		theme : 'fontawesome-stars',
		readonly : 'true'
	});
}
function resetRatingFrm(){
	$('.Star_rating').barrating('destroy');
	$('.Star_rating').val(1);
	$('.saveRating').show();
	$('#averageRating').text('');
	$('#weightageRating').text('');
	$('.Star_rating').barrating({
	        theme: 'fontawesome-stars'
	});

}