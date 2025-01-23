

var BidderListArray= new Array();
var winnerSelectionArray=new Array();

$(document).ready(function() {
			
	$("#BidderDetails").on('click',function(event) {
		debugger;
		$('.pagination').children().remove();
		event.preventDefault();
		var typeCode=$("input[name='tenderTypeCodeToggle']:checked").val();
		var BidderList=fetchList("getBidderListFromTahdrMaterial/"+typeCode, $("#itemForm #tahdrMaterial").val())
		loadAwardBidderListToLeftPane(BidderList);
		$('#leftPane').paginathing({perPage: 6});
     });
			$("#ratingDetails").on('click',function(event) {
				debugger;
				$('.pagination').children().remove();
				event.preventDefault();
				var BidderList=fetchList("getBidderListFromTahdrMaterial", $("#itemForm #tahdrMaterial").val())
				loadAwardBidderListToLeftPane(BidderList);
				$('#leftPane').paginathing({perPage: 6});
			});
			
			$(".winnerSelectionBidder").on("click", function(event){
				debugger;
				event.preventDefault();
				submitIt("MaterialBidders","saveWinnerBidder","saveAwardResp");
			});
			
			$(".winnerSubmit").on("click", function(event){
				debugger;
				event.preventDefault();
				var tahdrId=$(".tahdrId").val();
				submitWithParam("submitWinnerSelectionTahdr", "tahdrId", "submitTahdrResp");
				
			});
			
		});

function loadAwardBidderListToLeftPane(BidderList){
	debugger;
	console.log(BidderList);
	$("#leftPane").html("");
	var leftPanelHtml='';
	var i=0;
	var active="";
	var bidderId="";
	var tahdrMaterialId="";
	var firstRow=null;
	BidderListArray=[];
	$.each(Object.values(BidderList),function(key,bidder){
		
		BidderListArray["bidder"+bidder.priceBidId]=bidder;
		if(i==0){
			firstRow = bidder;
			active="active";
			bidderId=bidder.itemBid.bidder.bidderId;
		}
		leftPanelHtml= leftPanelHtml +appendMatBidderLi(bidder,active);
		active="";
		i++;
	});
	$("#leftPane").append(leftPanelHtml);
	loadAwardMatBidderToRightPane(firstRow);
	//setRatingByClass('biderRate');
	setNumberStarRating();
	tahdrMaterialId=$(".tahdrMaterialId").val();
	if((bidderId!=null && bidderId!="") && (tahdrMaterialId!=null && tahdrMaterialId!="")){
		loadWinnerSelectionData(bidderId,tahdrMaterialId);
	}
	setLeftPaneHeader("Bidder List", Object.values(BidderListArray).length);
	$("#leftPane").on('click','.bidder',function(){
		var id=$(this).attr('id');
		var tahdrMaterial=$(".tahdrMaterialId").val();
		loadAwardMatBidderToRightPane(BidderListArray["bidder"+id]);
		var bidderId=$(".bidderId").val();
		loadWinnerSelectionData(bidderId,tahdrMaterial);
	});
	
	$('#leftPane').paginathing({perPage: 6});
}

function loadAwardMatBidderToRightPane(bidderData){
	debugger;
	if(!$.isEmptyObject(bidderData)){
		var priceBidId=bidderData.priceBidId==null?'':bidderData.priceBidId;
		var bidderId=bidderData.itemBid==null?'':bidderData.itemBid.bidder==null?'':bidderData.itemBid.bidder.bidderId==null?'':bidderData.itemBid.bidder.bidderId;
		var itemBidId=bidderData.itemBid==null?'':bidderData.itemBid.itemBidId==null?'':bidderData.itemBid.itemBidId;
		/*var tahdrMaterialId=bidderData.itemBid==null?'':bidderData.itemBid.tahdrMaterial==null?'':bidderData.itemBid.tahdrMaterial.tahdrMaterialId==null?'':bidderData.itemBid.tahdrMaterial.tahdrMaterialId;*/
		var factoryName=bidderData.partner==null?'':bidderData.partner.name==null?'':bidderData.partner.name;
		var tahdrDeatialId=bidderData.itemBid==null?'':bidderData.itemBid.bidder==null?'':bidderData.itemBid.bidder.tahdr==null?'':bidderData.itemBid.bidder.tahdr.tahdrDetail[0]==null?'':bidderData.itemBid.bidder.tahdr.tahdrDetail[0].tahdrDetailId==null?'':bidderData.itemBid.bidder.tahdr.tahdrDetail[0].tahdrDetailId;
		var tenderName=bidderData.itemBid==null?'':bidderData.itemBid.bidder==null?'':bidderData.itemBid.bidder.tahdr==null?'':bidderData.itemBid.bidder.tahdr.title==null?'':bidderData.itemBid.bidder.tahdr.title;
		var tahdrId=bidderData.itemBid==null?'':bidderData.itemBid.bidder==null?'':bidderData.itemBid.bidder.tahdr==null?'':bidderData.itemBid.bidder.tahdr.tahdrId==null?'':bidderData.itemBid.bidder.tahdr.tahdrId;
		var tenderNo=bidderData.itemBid==null?'':bidderData.itemBid.bidder==null?'':bidderData.itemBid.bidder.tahdr==null?'':bidderData.itemBid.bidder.tahdr.tahdrCode==null?'':bidderData.itemBid.bidder.tahdr.tahdrCode;
		var fddRate=bidderData.fddRate==null?'':bidderData.fddRate;
		var fddAmountGST=bidderData.fddAmountWithGST==null?'':bidderData.fddAmountWithGST;
		var fddAmount=bidderData.fddAmount==null?'':bidderData.fddAmount;
		var offeredQuantity=bidderData.offeredQuantity==null?0:bidderData.offeredQuantity;
		var tahdrType=bidderData.itemBid==null?'':bidderData.itemBid.bidder==null?'':bidderData.itemBid.bidder.tahdr==null?'':bidderData.itemBid.bidder.tahdr.tahdrTypeCode==null?'':bidderData.itemBid.bidder.tahdr.tahdrTypeCode;
		var tahdrPurchase=bidderData.itemBid==null?'':bidderData.itemBid.bidder==null?'':bidderData.itemBid.bidder.tenderPurchase==null?'':bidderData.itemBid.bidder.tenderPurchase.isTrader==null?'':bidderData.itemBid.bidder.tenderPurchase.isTrader;
		var tahdrDeatilId=$(".tahdrDetail").val();
		var tahdrMaterialId=$(".tahdrMaterial").val();
		var allocatedQty=bidderData.itemBid==null?'':bidderData.itemBid.tahdrMaterial==null?'':bidderData.itemBid.tahdrMaterial.allocatedQty=null?'':bidderData.itemBid.tahdrMaterial.allocatedQty;
		var quantity=bidderData.itemBid==null?'':bidderData.itemBid.tahdrMaterial==null?'':bidderData.itemBid.tahdrMaterial.quantity=null?'':bidderData.itemBid.tahdrMaterial.quantity;
		var bidderStatus=bidderData.itemBid==null?'':bidderData.itemBid.bidder==null?'':bidderData.itemBid.bidder.status==null?'':bidderData.itemBid.bidder.status;
		$(".priceBidId").val(priceBidId);
		$(".bidderId").val(bidderId);
		$(".itemBidId").val(itemBidId);
		$(".tahdrMaterialId").val(tahdrMaterialId);
		$(".tahdrId").val(tahdrId);
		$(".tahdrDetail").val(tahdrDeatilId);
		$("#MaterialBidders #factoryName").val(factoryName);
		
		$("#MaterialBidders #tahdrName").val(tenderName);
		$("#MaterialBidders #tahdrNo").val(tenderNo);
		$("#MaterialBidders #fddRate").val(fddRate);
		if(offeredQuantity==0){
			$("#MaterialBidders #fddAmountGST").val(0);
		}else{
			$("#MaterialBidders #fddAmountGST").val(fddAmountGST);
		}
		/*calcualteQty();*/
		$("#MaterialBidders #offeredQty").val(offeredQuantity);
		if(allocatedQty==null){
			$("#MaterialBidders #remQty").val(quantity);	
		}
		else if(allocatedQty!=null){
			$("#MaterialBidders #remQty").val(allocatedQty);	
		}
		
		
	if(tahdrType=='WT' || tahdrType=='FA'){
			$(".purchased").hide();
			$("#purchasedAs").val('');
			$(".factoryOfPurchased").hide();
			$("#factoryOfPurchased").val('');
		}
		else if(tahdrType=='PT' && tahdrPurchase=='N'){
			$(".purchased").show();
			$("#purchasedAs").val('Manufacturer');
			$(".factoryOfPurchased").show();
			$("#factoryOfPurchased").val(factoryName);
		}
		else if(tahdrType=='PT' && tahdrPurchase=='Y'){
			$(".purchased").show();
			$("#purchasedAs").val('Trader');
			$(".factoryOfPurchased").hide();
			$("#factoryOfPurchased").val('');
		}
	if(bidderStatus=="QAW"){
		$("#bidderStatus").val('Winner');
	}else{
		$("#bidderStatus").val('Runner_Up');
	}
	}
	/*setHeaderValues("Tender No.: ", "Tender Title : ", "Department : ", "Status : ");*/
}

function appendMatBidderLi(priceBid,active){
	var rate = '<span class="stars">'+priceBid.itemBid.bidder.rating+'</span>';
	return appendLiData(priceBid.partner.name, rate, priceBid.itemBid.bidder.tahdr.tahdrCode, priceBid.itemBid.bidder.tahdr.tahdrCode, priceBid.priceBidId, active, 'bidder');
}


function calcualteQty(quantity){
	debugger;	
	var alocatedQty= Number(quantity.value);
	var FDDRate= Number($("#fddRate").val());
	var FDDAmount=(alocatedQty*FDDRate);
	$("#fddAmountofAQ").val(FDDAmount);
	var remQty=$("#remQty").val();
	if(alocatedQty>remQty)
		{
		Alert.warn("Allocated quantity cannot exceed required quantity");
		$(quantity).val($($(".quantity")[0]).text());
		}
}

function saveAwardResp(data){
	
	debugger;
	if(data.success==true){
		if(data.objectMap.winnerSelectionDto.response.hasError==false)
		{
			var winnerDto=data.objectMap==null?'':data.objectMap.winnerSelectionDto==null?'':data.objectMap.winnerSelectionDto;
			if(winnerDto!=null){
				var winnerSelectionId = winnerDto.winnerSelectionId==null?'':winnerDto.winnerSelectionId;
				var biiderId = winnerDto.itemBid==null?'':winnerDto.itemBid.bidder==null?'':winnerDto.itemBid.bidder.bidderId==null?'':winnerDto.itemBid.bidder.bidderId;
				var allocateQty=winnerDto.allocatedQty==null?'':winnerDto.allocatedQty;
				var remainingQty=winnerDto.remainingQty==null?'':winnerDto.remainingQty;
				$(".winnerSelectionId").val(winnerSelectionId);
				$("#allocateQty").val(allocateQty);
				$("#remQty").val(remainingQty);
				if(allocateQty!=null){
					calucateFddAmount(allocateQty);
				}	
			}
			
			if (data.objectMap.winnerSelectionDto.response.hasError) {
				Alert.warn(data.objectMap.winnerSelectionDto.response.message);
			} else {
				Alert.info(data.objectMap.winnerSelectionDto.response.message);
			}
			if(!$.isEmptyObject(data.objectMap.priceBidList)){
				$.each(Object.values(data.objectMap.priceBidList),function(key,bidder){
					
					BidderListArray["bidder"+bidder.priceBidId]=bidder;
				});
			}
		}
	}
	else if(data.success==false){
		Alert.warn(data.message);
	}

}

function loadWinnerSelectionData(bidderId,tahdrMaterialId)
{debugger;
   var bidderId=bidderId;
   $("#bidderId").val(bidderId);
	/*submitWithParam("getWinnerSelectionDataFromBidder", "bidderId", "winnerSelectionResponse");*/
   submitWithTwoParam("getWinnerSelectionDataFromBidder", "bidderId","tahdrMaterialId", "winnerSelectionResponse")
}


function winnerSelectionResponse(data){
	debugger;
	if(!$.isEmptyObject(data.objectMap.winnerData)){
		debugger;
		var winnerSelectionId=data.objectMap==null?'':data.objectMap.winnerData==null?'':data.objectMap.winnerData.winnerSelectionId==null?'':data.objectMap.winnerData.winnerSelectionId;
		var allocateQty=data.objectMap==null?'':data.objectMap.winnerData==null?'':data.objectMap.winnerData.allocatedQty==null?'':data.objectMap.winnerData.allocatedQty;
		$(".winnerSelectionId").val(winnerSelectionId);
		$("#allocateQty").val(allocateQty);
		if(allocateQty!=null){
			calucateFddAmount(allocateQty);
		}
		if(data.objectMap.winnerData.rating > 0){
			loadRatingData(data.objectMap.winnerData);
		}else{
			resetRating();
		}
	}
	else{
		$(".winnerSelectionId").val('');
		$("#allocateQty").val('');
	}
}

function allowedAllocationQty(num){
	debugger;
	var allocated=num;
	var requiredQty=$("#remQty").val();
	
	
}

function calucateFddAmount(quantity){
	debugger;
	var alocatedQty= quantity;
	var FDDRate= Number($("#fddRate").val());
	var FDDAmount=(alocatedQty*FDDRate);
	$("#fddAmountofAQ").val(FDDAmount);
}

function submitTahdrResp(data){
	debugger;
	if (data.success) {
		$("#submitRespLabelId").html(data.message);
	} else {
		$("#submitRespLabelId").html(data.message);
	}
}
function loadRatingData(){
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
function resetRating(){
	$('.saveRating').show();
	$('#averageRating').text('');
	$('#weightageRating').text('');
	$('.Star_rating').barrating('destroy');
	$('.Star_rating').barrating({
		initialRating : 1,
		theme : 'fontawesome-stars',
	});
}

function setRating(id, rate) {
	$('#' + id).barrating({
		initialRating : rate,
		theme : 'fontawesome-stars',
		readonly : 'true'
	});
}
function setRatingByClass(eleClass) {
	$('.' + eleClass).barrating({
		theme : 'fontawesome-stars',
		readonly : 'true'
	});
}
function setNumberStarRating(){
	 $('span.stars').stars();
}