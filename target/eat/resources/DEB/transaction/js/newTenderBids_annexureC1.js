function getAnnexureC1BidWithDoc(){
	var tahdrMaterialId=$("#itemDetailForm #tahdrMaterialId").val();
	var bidderId=$("#bidderDetailForm #bidderId").val();
	var resp=fetchList("getPartnerBidsForAnnexureC1/"+tahdrMaterialId+"/"+bidderId,null);
	var data=resp.objectMap.priceBidList==null?null:resp.objectMap.priceBidList[0].digiSignedDoc;
	var url=$("#downloadC1Bid").data('url');
	if(data!=null){
		var file=data.fileName==null?'':data.fileName;
		var c1Bid=data.attachmentId==null?null:data.attachmentId;
		$("#downloadC1Bid").attr('href',url);
		$("#downloadC1Bid").prop('href', url+'/'+c1Bid);
		$("#downloadC1Bid").html(file);
		loadPriceBidToRightPane(resp.objectMap.priceBidList[0]);
	}else{
		$("#downloadC1Bid").removeAttr('href',url);
		$("#downloadC1Bid").html(''); 
	}
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

