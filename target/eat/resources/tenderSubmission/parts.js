var partsArray=new Array();
var selectedPart;
$(document).ready(function(){
	$('#savePartsPriceBidBtn').on('click',function(event){
		event.preventDefault();
		submitIt("savePartsPriceBidForm","savePriceBid","populateSavePartsPriceBidResponse");
	});
	
	$('#cancelPartsPriceBid').on('click',function(event){
		event.preventDefault();
		if(!$.isEmptyObject(partsArray["part"+$(".materialSpecificationId").val()]))
			populatePriceBid(partsArray["part"+$(".materialSpecificationId").val()]);		
	});
	
});

function getPriceBidForParts(){
	cacheLi();
	setCurrentTab($("#partsTab"));
	activeParts();
	var tahdrMaterialId=$(".tahdrMaterialId").val();
	submitToURL("getPriceBidForParts/"+tahdrMaterialId,"populateParts");
}

function populateParts(resp){
	var parts=resp.objectMap.partsPriceBidList;
	populatePartsToLeftPane(parts);
}

function populatePartsToLeftPane(parts){

	$('.pagination').children().remove();
	$("#leftPane").html("");	
	var leftPanelHtml='';
	var i=0;
	var active="";
	var firstRow=null;
	
	partsArray=[];
	$.each(Object.values(parts),function(key,part){
		
		partsArray["part"+part.materialSpecification.materialSpecificationId]=part;
		
		if(i==0){
			firstRow = part;
			active="active";
		}
		leftPanelHtml= leftPanelHtml +appendPartsData(part,active);
		active="";
		i++;
	});
	setLeftPaneHeader("Parts", partsArray.length);
	
	$("#leftPane").append(leftPanelHtml);
	populatePartsBid(firstRow);
	$("#leftPane").on('click','.part',function(){
		var partId=$(this).attr('id');
		populatePartsBid(partsArray["part"+partId]);
	});
	$('#leftPane').paginathing({perPage: 6});

}

function appendPartsData(part,active){
	return appendLiData(part.materialSpecification.specification.name,"Quantity:"+part.materialSpecification.quantity,"","",part.materialSpecification.materialSpecificationId,active,"part");
}

function populatePartsBid(partsBid){
	selectedPart=partsBid;
	if(!$.isEmptyObject(partsBid)){
		var priceBidId=partsBid.priceBidId==null?'':partsBid.priceBidId;
		var offeredQuantity=partsBid.offeredQuantity==null?'':partsBid.offeredQuantity;
		var exGroupPriceRate=partsBid.exGroupPriceRate==null?'':partsBid.exGroupPriceRate;
		var freightChargeRate=partsBid.freightChargeRate==null?'':partsBid.freightChargeRate;
		var ticRate=partsBid.ticRate==null?'':partsBid.ticRate;
		var taxRate=partsBid.taxRate==null?'':partsBid.taxRate;
		var taxAmount=partsBid.taxAmount==null?'':partsBid.taxAmount;
		var fddRate=partsBid.fddRate==null?'':partsBid.fddRate;
		var fddInWords=partsBid.fddInWords==null?'':partsBid.fddInWords;
		var isConfirmed=partsBid.isConfirmed==null?'':partsBid.isConfirmed;
		var materialSpecificationId;
		var itemCode;
		var hsnCode;
		var unit;
		var itemName;
		var itemDesc;
		var requiredQuantity;
		if(!$.isEmptyObject(partsBid.materialSpecification)){
			materialSpecificationId=getValue(partsBid.materialSpecification.materialSpecificationId);
			itemCode=partsBid.materialSpecification.specification.code;
			hsnCode=partsBid.materialSpecification.specification.hsnCode.code;
			unit=partsBid.materialSpecification.specification.uom.name;
			itemName=partsBid.materialSpecification.specification.name;
			itemDesc=partsBid.materialSpecification.specification.description;
			requiredQuantity=partsBid.materialSpecification.quantity;
		}
		if(!$.isEmptyObject(partsBid.itemBid)){
			itemBidId=getValue(partsBid.itemBid.itemBidId);
			$(".itemBidId").val(itemBidId);
		}
		$(".priceBidId").val(priceBidId);
		
		$(".partMaterialDescription").text(itemDesc);
		$(".partMaterialName").text(itemName);
		$(".partMaterialCode").text(itemCode);
		$(".partMaterialUnit").text(unit);
		$(".partHsnCodeField").text(hsnCode);
		$(".partReferedQuantity").text(requiredQuantity);
		$(".partReferedQuantity").val(requiredQuantity);
		$(".materialSpecificationId").val(materialSpecificationId);
		$("#priceBidId").val(priceBidId);
		$("#partExGroupPriceRate").val(exGroupPriceRate);
		$("#partFreightChargeRate").val(freightChargeRate);
		$("#partTicRate").val(ticRate); //transit insurance charges
		$("#partTaxRate").val(taxRate);
		gstForPartsCalculate()
		$("#partTaxAmount").val(taxAmount);
		$("#partFddRate").val(fddRate);
		$("#partFddInWords").val(fddInWords);
		$("#isConfirmed").val(isConfirmed);
		calculateForParts();
	}
}

function gstForPartsCalculate(){
	var taxRate=$("#partTaxRate").val();
	var igst=Number(taxRate);
	var cgst=(igst/2).toFixed(2);
	var sgst=(igst/2).toFixed(2);
	
	$("#partIgst").val(igst);
	$("#partCgst").val(cgst);
	$("#partSgst").val(sgst);
	
	calculateForParts();
}

function calculateForParts(){
	var qty=$("#partOfferedQuantity").val();
	var exGroupPrice=$("#partExGroupPriceRate").val();
	var totalExGroupPrice=Number(qty)*Number(exGroupPrice);
	$("#partTotalExGroupPrice").val(totalExGroupPrice);
	
	var freightCharges=$("#partFreightChargeRate").val();
	var totalFreightCharges=Number(qty)*Number(freightCharges);
	$("#partTotalFreightCharges").val(totalFreightCharges);
	
	var tic=$("#partTicRate").val();
	var totalTic=Number(qty)*Number(tic);
	$("#partTotalTic").val(totalTic);
	
	var fddRate=(Number(exGroupPrice)+Number(freightCharges)+Number(tic)).toFixed(2);
	$("#partFddRate").val(fddRate);
	var fddAmount=(Number(qty)*Number(fddRate)).toFixed(2);
	$("#partFddAmount").val(fddAmount);
	
	var taxRate=$("#partTaxRate").val();
	var taxAmount=Number((fddRate*taxRate)/100).toFixed(2);
	var totalTax=(Number(qty)*Number(taxAmount)).toFixed(2);
	$("#partTaxAmount").val(taxAmount);
	$("#partTotalTax").val(totalTax);
	
	var fddRateGST=(Number(fddRate)+Number(taxAmount)).toFixed(2);
	$("#partFddRateGST").val(fddRateGST);
	var fddAmountGST=(Number(qty)*Number(fddRateGST)).toFixed(2);
	$("#partFddAmountGST").val(fddAmountGST);
}

function populateSavePartsPriceBidResponse(itemBid){
	var itemBidId=itemBid.itemBidId==null?'':itemBid.itemBidId;
	$(".itemBidId").val(itemBid.itemBidId);
	$(".priceBidId").val(itemBid.priceBid.priceBidId);
	
	if(itemBid.response.hasError){
		Alert.warn(itemBid.response.message);
	}else{
		if(itemBid.priceBid.response.hasError){
			Alert.warn(itemBid.priceBid.response.message);
		}else{
			Alert.info(itemBid.priceBid.response.message);
		}
	}
	itemBid.priceBid.materialSpecification=selectedPart.materialSpecification;
	partsArray["part"+itemBid.priceBid.materialSpecification.materialSpecificationId]=itemBid.priceBid;
}

function formulatePriceBid(){
	var exGroupPriceRate='';
	var freightChargeRate='';
	var ticRate='';
	
	$.each(Object.values(partsArray),function(idx,part){
		exGroupPriceRate=Number(exGroupPriceRate)+Number(part.totalExGroupPrice);
		freightChargeRate=Number(freightChargeRate)+Number(getValue(part.totalFreightCharge));
		ticRate=Number(ticRate)+Number(getValue(part.totalTic));
	});
	
	$(".priceBidId").val('');
	$(".materialSpecificationId").val('');
	$("#priceBidId").val('');
	$("#offeredQuantity").val($($(".referedQuantity")[0]).text());
	$("#exGroupPriceRate").val(exGroupPriceRate);
	$("#freightChargeRate").val(freightChargeRate);
	$("#ticRate").val(ticRate); //transit insurance charges
	$("#taxRate").val('');
	gstCalculate()
	$("#taxAmount").val('');
	$("#isConfirmed").val('');
	calculate();
	
	$("#digitalSignaturedPB").val('');
	$('.digitalSignaturedPB').attr('disabled',true);
	$("#digitalSignaturedPBAnchor").attr('href','');
	$("#digitalSignaturedPBAnchor").html('');

}