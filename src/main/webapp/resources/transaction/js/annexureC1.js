var bidderArray=new Array();
var lowestPriceBidArray=new Array();
var priceBidArray=new Array();
$(document).ready(function(){
	
	var itemCode;
	var totalQty;
	var L1Price;
	var L1OfferedQty;
	
	$("input[name='tenderTypeCodeToggle']").on("change",function(){
		debugger;
		$('.pagination').children().remove();
		var resp=fetchList("getAnnexureC1Tenders",$(this).val())
		loadInitialData(resp);
		
	});
	
	$("#tendeDetails").on("click",function(event){
		event.preventDefault();
		var resp=fetchList("getAnnexureC1Tenders",$("input[name='tenderTypeCodeToggle']:checked").val())
		loadTenderToLeftPane(resp.objectMap.bidderList);
	});
	
	$(".annexureBid").on("click",function(event){
		debugger;
		event.preventDefault();
		var tahdrDetailId=$(".tahdrDetailId").val();
		var resp=fetchList("getBidsForAnnexureC1Tender",tahdrDetailId);
		if(resp.objectMap.hasOwnProperty('isLowestPriceBid')){
			$.each(resp.objectMap.isLowestPriceBid,function(key,priceBid){
				var itemBid=priceBid.itemBid;
				var tahdrMaterial=itemBid.tahdrMaterial;
				lowestPriceBidArray["lowestPriceBid_"+tahdrMaterial.tahdrMaterialId]=priceBid;
			});
		}
		if(!$.isEmptyObject(resp.objectMap.priceBidList)){
			loadPriceBidToLeftPane(resp.objectMap.priceBidList);
		}
		
	});
	
	$("#saveAnnexureC1Bid").on("click",function(event){
		event.preventDefault();
		submitIt("saveAnnexureC1BidForm","saveAnnexureC1Bid","saveAnnexureC1BidResp");
	});
	
	var response=fetchList("getAnnexureC1Tenders",$("input[name='tenderTypeCodeToggle']:checked").val());
	loadInitialData(response);
});

function loadInitialData(resp){
	loadTenderToLeftPane(resp.objectMap.bidderList);
}

function loadTenderToLeftPane(bidderList){
	debugger;
	$('.pagination').children().remove();
	$("#leftPane").html("");
	var leftPanelHtml='';
	var i=0;
	var active="";
	var firstRow=null;

	bidderArray=[];
	$.each(Object.values(bidderList),function(key,bidder){
		$(".bidderId").val(bidder.bidderId);
		bidderArray["bidder"+bidder.bidderId]=bidder;
		if(i==0){
			firstRow = bidder;
			active="active";
		}
		leftPanelHtml= leftPanelHtml +appendTahdrLi(bidder,active);
		active="";
		i++;
	});
	setLeftPaneHeader("Tender List", bidderArray.length);	
	$("#leftPane").append(leftPanelHtml);
	loadTenderToRightPane(firstRow);
	$('#leftPane').paginathing({perPage: 6});
	$(".tahdrDetail").on('click',function(){
		var bidderId=$(this).attr('id');
		loadTenderToRightPane(bidderArray["bidder"+bidderId]);
	});
}

function appendTahdrLi(bidder,active){
	var tahdr=bidder.tahdr;
	var tahdrDetail=tahdr.tahdrDetail[0];
	return appendLiData(tahdr.tahdrCode, tahdr.title, 
			formatDateTime(getValue(tahdrDetail.c1ToDate)), 
			formatDateTime(getValue(tahdrDetail.c1OpenningDate)), 
			bidder.bidderId, active, 'tahdrDetail');
}

function loadTenderToRightPane(bidder){
	debugger;
	if(!$.isEmptyObject(bidder)){
		var tahdr=bidder.tahdr;
		var tahdrDetail=tahdr.tahdrDetail[0];
		$(".tahdrDetailId").val(getValue(tahdrDetail.tahdrDetailId));
		$("#tahdrCode").text(getValue(tahdr.tahdrCode));		
		$("#tahdrTitle").text(getValue(tahdr.title));
		$("#description").text(getValue(tahdrDetail.description));
		$("#c1OpenningDate").text(formatDateTime(getValue(tahdrDetail.c1OpenningDate)));
		$("#c1ToDate").text(formatDateTime(getValue(tahdrDetail.c1ToDate)));
		$("#c1ToDate").text(formatDateTime(getValue(tahdrDetail.c1ToDate)));
		$("#bidSubmissionDate").text(formatDateTime(getValue(tahdrDetail.priceBidToDate)));
		$("#priceBidOpeningDate").text(formatDateTime(getValue(tahdrDetail.priceBidOpeningDate)));
		$("#priceBidOpeningDate").text(formatDateTime(getValue(tahdrDetail.priceBidOpeningDate)));
		
		/*setHeaderValues(bidder.tenderDetail.tahdr.tahdrCode, bidder.tenderDetail.tahdr.title, formatDateTime(getValue(bidder.tenderDetail.c1ToDate)), formatDateTime(getValue(bidder.tenderDetail.c1OpenningDate)));*/
		setHeaderValues("Tender No.: "+tahdr.tahdrCode, "Tender Title : "+tahdr.title, "C1 Submission Date: "+formatDateTime(getValue(tahdrDetail.c1ToDate)), "C1 Opening Date : "+formatDateTime(getValue(tahdrDetail.c1OpenningDate)));
		$('#annexureBid').removeClass('readonly');
	}else{
		setHeaderValues("Tender No.: ", "Tender Title : ", "C1 Submission Date: ", "C1 Opening Date : ");
		$(".tahdrDetailId").val('');
		$("#tahdrCode").text('');		
		$("#tahdrTitle").text('');
		$("#description").text('');
		$("#c1OpenningDate").text('');
		$("#c1ToDate").text('');
		$("#c1ToDate").text('');
		$("#bidSubmissionDate").text('');
		$("#priceBidOpeningDate").text('');
		$("#priceBidOpeningDate").text('');
		$('#annexureBid').addClass('readonly');
	}
	$('#confirmAnnexureC1TabId').addClass('readonly');
}

function loadPriceBidToLeftPane(priceBidList){
	debugger;
	$("#leftPane").html("");
	var leftPanelHtml='';
	var i=0;
	var active="";
	var firstRow=null;

	priceBidArray=[];
	$.each(Object.values(priceBidList),function(key,priceBid){
		priceBidArray["priceBid"+priceBid.priceBidId]=priceBid;
		if(i==0){
			firstRow = priceBid;
			active="active";
		}
		leftPanelHtml= leftPanelHtml +appendPriceBidLi(priceBid,active);
		active="";
		i++;
	});
	setLeftPaneHeader("Item List", priceBidArray.length);	
	$("#leftPane").append(leftPanelHtml);
	loadPriceBidToRightPane(firstRow);
	$(".priceBid").on('click',function(){
		var priceBidId=$(this).attr('id');
		loadPriceBidToRightPane(priceBidArray["priceBid"+priceBidId]);
	});
}

function appendPriceBidLi(priceBid,active){
	var itemBid=priceBid.itemBid
	return appendLiData(itemBid.tahdrMaterial.material.name,itemBid.tahdrMaterial.materialDescription,
			itemBid.tahdrMaterial.material.uom.name,'', priceBid.priceBidId,active,'priceBid');
}

function setLowestBid(priceBid){
	if(!$.isEmptyObject(priceBid)){
		var l1price=priceBid.fddAmount;
		var l1quantity=priceBid.offeredQuantity;
		var remainingQuant=Number($('#totalQuantity').html())-l1quantity;
		$('#l1Price').html(l1price);
		$("#l1Quantity").html(l1quantity);
		$('#remainingQuantity').html(remainingQuant);
		
		L1Price=$('#L1Price').html();
		L1OfferedQty=$("#L1Quantity").html();
	}
	else{
		$('#l1Price').html(0);
		$("#l1Quantity").html(0);
		$('#remainingQuantity').html(0);
		
		L1Price=$('#L1Price').html();
		L1OfferedQty=$("#L1Quantity").html();
	}
}

function loadPriceBidToRightPane(priceBid){
	debugger;
	if(!$.isEmptyObject(priceBid)){
		var itemBid=priceBid.itemBid;
		$('.itemBidId').val(itemBid.itemBidId);
		$('.priceBidId').val(priceBid.priceBidId);
		$('#saveAnnexureC1BidForm #priceBidId').val(priceBid.priceBidId);
		$('#materialCode').text(getValue(itemBid.tahdrMaterial.material.name));
		$('#ItemDescription').text(getValue(itemBid.tahdrMaterial.materialDescription));
		$('#totalQuantity').text(getValue(itemBid.tahdrMaterial.quantity));
		$('#L1Price').text();
		$("#L1Quantity").text();
		$('#remainingQuantity').text();
		var isMatched=priceBid.isMatched==null?'':priceBid.isMatched;
		$('#isMatched').val(isMatched);
		if(isMatched==null){
			$('#itemConfirmAnnexureC1TabId').addClass('readonly');
		}else{
			$('#itemConfirmAnnexureC1TabId').removeClass('readonly');
		}
		$('#matchedQuantity').text(getValue(priceBid.offeredQuantity));
		$('#matchedPrice').text(getValue(priceBid.exGroupPriceRate));
		$('.tahdrMaterialId').val(itemBid.tahdrMaterial.tahdrMaterialId);
		itemCode=itemBid.tahdrMaterial.material.name;
		totalQty=itemBid.tahdrMaterial.quantity
		L1Price=$('#L1Price').text();
		L1OfferedQty=$("#L1Quantity").text();
		
		var data=priceBidArray["priceBid"+priceBid.priceBidId];
		
		var tahdrMatId=itemBid.tahdrMaterial.tahdrMaterialId;

		var vdata=lowestPriceBidArray["lowestPriceBid_"+tahdrMatId];
		
		setLowestBid(vdata);
		var clauseA=priceBid.clauseA;
		if(clauseA!='' && clauseA!='N' && clauseA!=null){
			$('#clauseAId').attr('checked','checked');
			$('#clauseAId').prop('checked',true);
		}else{
			$('#clauseAId').removeAttr('checked');
			$('#clauseAId').prop('checked',false);
		}
		var clauseB=priceBid.clauseB;
        if(clauseB!='' && clauseB!='N' && clauseB!=null){
        	$('#clauseBId').attr('checked','checked');
			$('#clauseBId').prop('checked',true);
		}else{
			$('#clauseBId').removeAttr('checked');
			$('#clauseBId').prop('checked',false);
		}
		$('#confirmAnnexureC1TabId').removeClass('readonly');
		 
		$('#annexureC1Form #itemBidId').val(itemBid.itemBidId);
		 /* $('#annexureC1Form #bidderId').val(bidderId);*/
		  $('#annexureC1Form #priceBidId').val(priceBid.priceBidId);
	}else{
		$('#isMatched').val('');
		$('#confirmAnnexureC1TabId').addClass('readonly');
		
		$('.itemBidId').val('');
		$('.priceBidId').val('');
		$('#materialCode').text('');
		$('#ItemDescription').text('');
		$('#totalQuantity').text('');
		$('#L1Price').text('');
		$("#L1Quantity").text('');
		$('#remainingQuantity').text('');
		
		$('#matchedQuantity').text('');
		$('#matchedPrice').text('');
		$('.tahdrMaterialId').val('');
	}
}

function saveAnnexureC1BidResp(resp){
    Alert.info(resp.response.message);
	$(".priceBidId").val(getValue(resp.priceBidId));
	$('#saveAnnexureC1BidForm #priceBidId').val(getValue(resp.priceBidId));
}
function getAnnexureConfirmatoryData(el){
	debugger;
	$("#leftPane").html('');
	/*var bidderId=$().data('bidderId');
	var tahdrId=$().data('tahdrId');
	if(bidderId!="" && tahdrId!=""){
		
	}else{
		Alert.warn('Something went wrong');
	}*/
	
}
function downloadAnnexureC1Pdf(){
	var priceBidId=$('#annexureC1Form #priceBidId').val();
	event.preventDefault();
	showLoader();
	directSubmit(event,"gennerateAnnexureC1Doc","generateAnnexureC1Report/"+priceBidId);
	hideLoader();
}
function annexurec1ConfirmDeviationfileDeleteResp(data){
	debugger;
	if (!data.hasError) {
		$('#annexurec1FileResponseUploadId').val('');
		$('#annexurec1FileResponseId').val('');
		$("#a_annexurec1FileResponse").html('');
		$('.annexurec1FileResponseId').attr('disabled', true);
		Alert.info(data.message);
	} else {
		Alert.warn(data.message);
	}
}
function confirmAnnexureC1(){
	debugger;
	var bidderId=$('#saveAnnexureC1BidForm #bidderId').val();
	if(bidderId!=''){
		submitToURL("confirmAnnexureC1/"+bidderId, "confirmAnnexureC1Resp");
	}else{
		Alert.warn('Something went wrong !');
	}
}
function confirmAnnexureC1Resp(data){
	debugger;
	if(data.objectMap.statusResult){
		Alert.info(data.objectMap.message);
	}else{
		Alert.warn(data.objectMap.message);
	}
}
function itemC1Comfirmatory(el){
	debugger;
	$('#leftPane li:not(.active)').remove();
	var priceBidId=$('#saveAnnexureC1BidForm #priceBidId').val();
	if(priceBidId!=null){
		submitToURL("getItemwiseComfirmC1/"+priceBidId, "itemC1ComfirmatoryResp");
	}else{
		Alert.warn('Something Went wrong !');
	}
}
function itemC1ComfirmatoryResp(data){
	  debugger;
	if(!$.isEmptyObject(data.objectMap.priceBid)){
		/*$('.itemConfirmC1').removeClass('readonly');*/
		var value=data.objectMap.priceBid;
		  var digiFile=value.digiSignedDoc==null?'':value.digiSignedDoc.attachmentId;
	      var digiFileName=value.digiSignedDoc==null?'':value.digiSignedDoc.name;
		  var url=$("#a_annexurec1FileResponse").data('url');
			
			$("#annexureC1Form #annexurec1FileResponseId").val(digiFileName);
			$("#annexureC1Form #a_annexurec1FileResponse").prop('href', url+'/'+digiFileName);
			$("#annexureC1Form #a_annexurec1FileResponse").html(digiFileName);
			if(digiFileName==''){
				$("#annexureC1Form #a_annexurec1FileResponse").removeAttr('href');
				$("#annexureC1Form #a_annexurec1FileResponse").html('');
				$('#annexureC1Form #deleteAnnexurec1AttachmentId').attr('disabled','disabled');
			}
			else{
				$('#annexureC1Form #deleteAnnexurec1AttachmentId').removeAttr('disabled');
			}
	}else{
		$("#annexureC1Form #a_annexurec1FileResponse").removeAttr('href');
		$("#annexureC1Form #a_annexurec1FileResponse").html('');
		$('#annexureC1Form #deleteAnnexurec1AttachmentId').attr('disabled','disabled');
			
		/*$('.itemConfirmC1').addClass('readonly');*/
	}
}
/*function setClauseRequest(el){
	showLoader();
	var flag=$(el).is(':checked');
	flag=flag==true?'Y':'N';
	submitIt("clauseForm","saveClauseData","setClauseRequestResp");
	hideLoader();
}*/
function setClauseRequestResp(data){
	
}