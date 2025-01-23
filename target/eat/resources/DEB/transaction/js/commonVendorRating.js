
var tenderArray=new Array();

$(document).ready(function() {
	var typeCode=$('#filter li.active').data('documenttype');
	onPageLoad(typeCode);
	$("input[name='tenderTypeCodeToggle']").on("change",function(){
    	var typeCode=$('input[name=tenderTypeCodeToggle]:checked').val();
    	submitToURL("getTahdrForRating/"+typeCode, "loadTenderListForRating");	
    });
	
	$(".saveRating").on("click", function(event) {
		event.preventDefault();
		submitIt("rating", "saveRating", "saveRatingresp");
	});
	
	$('.filterByTenderType').click(function(){
		var typeCode=$(this).data('documenttype');
		$('#filter li.active').removeClass('active');
		$(this).addClass('active');
		onPageLoad(typeCode);
	});
	
});

function dataBind(){
	$("input[name='tenderTypeCodeToggle']").on("change",function(){
    	var typeCode=$('input[name=tenderTypeCodeToggle]:checked').val();
    	submitToURL("getTahdrForRating/"+typeCode, "loadTenderListForRating");	
    });
}

function setTogglebtn(typeCode){
	var htmlToggle='';
	if(typeCode=='AUCTION'){
		htmlToggle=htmlToggle+' <label class="btn btn-primary toggleNewTab" openTab="tenderDet">'+
		'<input type="radio" name="tenderTypeCodeToggle" value="FA">'+
		'<span class="glyphicon glyphicon-ok"></span> Forward '+
    '</label><label class="btn btn-primary active toggleNewTab" openTab="tenderDet">'+
   ' <input type="radio" name="tenderTypeCodeToggle" checked value="RA"> '+
   ' <span class="glyphicon glyphicon-ok"></span> Reverse   </label>';
	}else if(typeCode=='QUICK_AUCTION'){
		htmlToggle=htmlToggle+' <label class="btn btn-primary toggleNewTab" openTab="tenderDet">'+
		'<input type="radio" name="tenderTypeCodeToggle" value="QFA">'+
		'<span class="glyphicon glyphicon-ok"></span> Quick Forward '+
    '</label><label class="btn btn-primary active toggleNewTab" openTab="tenderDet">'+
   ' <input type="radio" name="tenderTypeCodeToggle" checked value="QRA"> '+
   ' <span class="glyphicon glyphicon-ok"></span> Quick Reverse   </label>';
	}else if(typeCode=='QUICK_RFQ'){
		htmlToggle=htmlToggle+' <input type="radio" name="tenderTypeCodeToggle" checked style="display:none;" value="QRFQ" >';
	}else if(typeCode=='RFQ'){
		htmlToggle=htmlToggle+' <input type="radio" name="tenderTypeCodeToggle" checked style="display:none;" value="RFQ" >';
	}else{
		htmlToggle=htmlToggle+' <label class="btn btn-primary toggleNewTab" openTab="tenderDet">'+
		'<input type="radio" name="tenderTypeCodeToggle" value="PT">'+
		'<span class="glyphicon glyphicon-ok"></span> Procurement '+
    '</label><label class="btn btn-primary active toggleNewTab" openTab="tenderDet">'+
   ' <input type="radio" name="tenderTypeCodeToggle" checked value="WT"> '+
   ' <span class="glyphicon glyphicon-ok"></span> Works   </label>';
	}
	$('#toggleBtn').html(htmlToggle);
	dataBind();
}


function onPageLoad(typeCode){
	debugger;
	setTogglebtn(typeCode);
	var typeCode=$('input[name=tenderTypeCodeToggle]:checked').val();
	submitToURL("getTahdrForRating/"+typeCode, "loadTenderListForRating");
}

function loadTenderListForRating(data){
	debugger;
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
		loadRatingData(null);
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
	if(data!=null){
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
	}else{
		$('.Star_rating').barrating('destroy');
		setRating('qualityRating', 0);
		setRating('deliveryRating', 0);
		setRating('serviceRating', 0);
		setRating('priceRating', 0);
		$('#averageRating').text(0);
		$('#weightageRating').text(0);
	}
	
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