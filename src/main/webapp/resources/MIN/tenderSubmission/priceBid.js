
var pbsdCount=0;

$(document).ready(function(){
	$('#savePriceBidBtn').on('click',function(event){
		event.preventDefault();
		$(this).attr("disabled","disabled");
		submitIt("savePriceBidForm","savePriceBid","populateSavePriceBidResponse");
		updateBidderStatus();
		$(this).removeAttr("disabled");
	});
	
	$('#cancelPriceBid').on('click',function(event){
		event.preventDefault();
		$(this).attr("disabled","disabled");
		if(!$.isEmptyObject(itemBidArray["tahdrMaterial"+$(".tahdrMaterialId").val()].priceBid)){
			populatePriceBid(itemBidArray["tahdrMaterial"+$(".tahdrMaterialId").val()].priceBid);
		}
		$(this).removeAttr("disabled");		
	});

	$("#savePbAdditionalDocumentBtn").on('click',function(event){
		event.preventDefault();
		$(this).attr("disabled","disabled");
		
		var priceBidId=$(".priceBidId").val();
		$(".priceBidId").val(getValue(priceBidId));
		
		var itemBidId=$(".itemBidId").val();
		$(".itemBidId").val(getValue(itemBidId));
		
		submitIt("savePbSecDocForm","savePriceSectionDocument","savePbBidderDoc");
		updateBidderStatus();
		$(this).removeAttr("disabled");	
	});
	
	$("#cancelPbAdditionalDocument").on('click',function(event){
		event.preventDefault();
		if(!$.isEmptyObject(itemBidArray["tahdrMaterial"+$(".tahdrMaterialId").val()].priceBid)){
			populatePBDocs(itemBidArray["tahdrMaterial"+$(".tahdrMaterialId").val()].priceBid.bidderSecDoc);
		}
		$(this).removeAttr("disabled");	
	});
	
	$("#submitPriceBid").on("click",function(event){
		event.preventDefault();
		$(this).attr("disabled","disabled");
		submitIt("submitPriceBidForm","submitPriceBid","populateSavePriceBidResponse");
		updateBidderStatus();
		$(this).removeAttr("disabled");
	});
});

function onChangeOfferedQuantity(ele){
	
	var offeredQty=Number($(ele).val().trim());
	var requiredQty=Number($($(".referedQuantity")[0]).text().trim());
	var minQty=(Number(requiredQty)*Number(selectedTender.tahdr.tahdrDetail[0].minQuantity)/Number(100)).toFixed(0);
	if(isZero(ele)){
		Alert.warn("Offered quantity cannot be Zero");
		$(ele).val('');
	}else if(offeredQty>requiredQty){
		Alert.warn("Offered quantity cannot exceed required quantity");
		$(ele).val($($(".referedQuantity")[0]).text());
	}else if(offeredQty<minQty){
		Alert.warn("Offered quantity cannot less than "+minQty);
		$(ele).val(minQty);
	}
	calculate();
}

function gstCalculate(){
	debugger;
	var taxRate=$("#taxRate").val();
	var igst=Number(taxRate);
	var cgst=(igst/2).toFixed(2);
	var sgst=(igst/2).toFixed(2);
	
	$("#igstLable").text(igst);
	$("#cgstLable").text(cgst);
	$("#sgstLable").text(sgst);
	
	$("#igst").val(igst);
	$("#cgst").val(cgst);
	$("#sgst").val(sgst);
	
	calculate();
}

function calculate(){
	debugger;
	var qty=$("#offeredQuantity").val();
	if(isZeroValue(qty)){
		$("#offeredQuantity").val('');
		/*$("#offeredQuantity").addClass('errorinput');*/
		$("#offeredQuantity").attr('title','Offered Quantity Cannot be Zero');
		return;
	}/*else{
		$("#offeredQuantity").removeClass('errorinput');
	}*/
	var exGroupPrice=$("#exGroupPriceRate").val();
	if(isZeroValue(exGroupPrice)){
		$("#exGroupPriceRate").val('');
		/*$("#exGroupPriceRate").addClass('errorinput');*/
		$("#exGroupPriceRate").attr('title','Ex-Works Price Cannot be Zero');
		/*Alert.warn("Ex-Works Price Cannot be Zero");*/
		return;
	}/*else{
		$("#exGroupPriceRate").removeClass('errorinput');
	}*/
	var totalExGroupPrice=Number(qty)*Number(exGroupPrice).toFixed(2);
	$("#totalExGroupPrice").val(totalExGroupPrice);
	
	var freightCharges=$("#freightChargeRate").val();
	if(isZeroValue(freightCharges)){
		$("#freightChargeRate").val('');
		$("#freightChargeRate").addClass('errorinput');
		$("#freightChargeRate").attr('title','Freight Chanrges Cannot be Zero');
		return;
	}/*else{
		$("#freightChargeRate").removeClass('errorinput');
	}*/
	var totalFreightCharges=Number(qty)*Number(freightCharges).toFixed(2);
	$("#totalFreightCharges").val(totalFreightCharges);
	
	var tic=$("#ticRate").val();
	if(isZeroValue(tic)){
		$("#ticRate").val('');
		$("#ticRate").addClass('errorinput');
		$("#ticRate").attr('title','Transit Insurance Chanrge Cannot be Zero');
		return;
	}/*else{
		$("#ticRate").removeClass('errorinput');
	}*/
	var totalTic=Number(qty)*Number(tic);
	$("#totalTic").val(totalTic);
	
	var fddRate=(Number(exGroupPrice)+Number(freightCharges)+Number(tic)).toFixed(2);
	$("#fddRate").val(fddRate);
	var fddAmount=(Number(qty)*Number(fddRate)).toFixed(2);
	$("#fddAmount").val(fddAmount);
	
	var taxRate=$("#taxRate").val();
	var taxAmount=Number((fddRate*taxRate)/100).toFixed(2);
	var totalTax=(Number(qty)*Number(taxAmount)).toFixed(2);
	$("#taxAmount").val(taxAmount);
	$("#totalTax").val(totalTax);
	
	var igstRate=$("#igst").val();
	var igstAmount=Number((fddRate*igstRate)/100).toFixed(2);
	$("#igstAmount").val(igstAmount);
	
	var cgstRate=$("#cgst").val();
	var cgstAmount=Number((fddRate*cgstRate)/100).toFixed(2);
	$("#cgstAmount").val(cgstAmount);
	
	var sgstRate=$("#sgst").val();
	var sgstAmount=Number((fddRate*sgstRate)/100).toFixed(2);
	$("#sgstAmount").val(sgstAmount);
	
	var fddRateGST=(Number(fddRate)+Number(taxAmount)).toFixed(2);
	$("#fddRateGST").val(fddRateGST);
	var fddAmountGST=(Number(qty)*Number(fddRateGST)).toFixed(2);
	$("#fddAmountGST").val(fddAmountGST);
	
	var amountInWords=numberToWords(fddRateGST);

	$("#fddAmountWithGSTInWords").val(amountInWords);
	$("#fddAmountWithGSTInWordsLabel").attr("title",amountInWords);
	$("#fddAmountWithGSTInWordsLabel").text(amountInWords);
}

function numberToWords(amount){
	if(!isEmpty(amount)){
		var amountArr=amount.split('.');
		var amountInWords='';
		var amountInRupees=convertNumberToWords(amountArr[0]);
		var amountInPaise;
		if(!isEmpty(amountArr[1])){
			amountInPaise=convertNumberToWords(amountArr[1]);
		}
		if(!$.isEmptyObject(amountInRupees)){
			amountInWords=amountInRupees+" Rupees";
		}
		if(!$.isEmptyObject(amountInPaise)){
			amountInWords=amountInWords+ " " + amountInPaise +" Paise ";
		}
		return amountInWords;
	}
}

function populatePBDocs(resp){
	pbsdCount=0;
	var pbDocList=resp.objectMap.bidderSecDocList;
	
	$("#pbDocumentTable").DataTable().destroy();
	$("#pbDocumentTable tbody").empty();
	$.each(pbDocList,function(idx,doc){
		var attachmentId='';
		var fileName='';
		
		if(idx==0){
			if(!$.isEmptyObject(doc.priceBid)){
				$(".priceBidId").val(doc.priceBid.priceBidId);
				if(!$.isEmptyObject(doc.itemBid)){
					$(".itemBidId").val(doc.priceBid.itemBid.itemBidId);
				}
			}
			
			
		}
		
		if(!$.isEmptyObject(doc.attachment)){
			attachmentId=getValue(doc.attachment.attachmentId);
			fileName=getValue(doc.attachment.fileName);
		}
		
		var tr=	"<tr>"+
		"<td class='col-sm-3'><input type='hidden' id='priceSectionDocId_"+getValue(doc.sectionDocument.sectionDocumentId)+"' class='bidderSectionDocId' value='"+getValue(doc.bidderSectionDocId)+"'>" +
			"<input type='hidden' class='sectionDocumentId' value='"+getValue(doc.sectionDocument.sectionDocumentId)+"'>"+getValue(doc.sectionDocument.name)+"</td>"+
		"<td class='col-sm-3'>"+getValue(doc.sectionDocument.description)+"</td>"+
		"<td class='col-sm-3'><div class='col-sm-10 col-xs-10' style='padding:0px;'><input type='file' id='' data-id='secDocAtt_"+doc.sectionDocument.sectionDocumentId+"' data-anchor='secDocAttName_"+doc.sectionDocument.sectionDocumentId+"' class='form-control uploadFile requiredFile ' onchange='onPBFileUpload(this)' /> "+
					"<input type='hidden' id='secDocAtt_"+doc.sectionDocument.sectionDocumentId+"' class='form-control attachment' value='"+attachmentId+"' /></div>" +
					"<span class='input-group-btn col-sm-1 col-xs-2' style='padding-left:0px;'>" +
						"<button class='btn btn-default pnlFile_1' onclick='deleteAdditionalDocPB("+doc.sectionDocument.sectionDocumentId+",event)' >" +
							"<i class='fa fa-times'></i>" +
						"</button>" +
					"</span></div>" +
					"</td>" +
		"<td class='col-sm-3'><a data-url='download' title='"+fileName+"' href='download/"+attachmentId+"' id='secDocAttName_"+doc.sectionDocument.sectionDocumentId+"' class='tenderattchfile secDocAttName_"+doc.sectionDocument.sectionDocumentId+"'>"+fileName+"</a> </td>" +
		"</tr>";
	$("#pbDocumentTable tbody").append(tr);
	});
	$("#pbDocumentTable").DataTable({
		"lengthMenu":lengthMenu,
		"bSort":false
	});
	mobiletable();
	$(".tenderattchfile").text(function(index, currentText) {
	    return currentText.substr(0, 20);
	});
}

function onPBFileUpload(ele){
	var tr=$(ele).parents('tr');
	if($.isEmptyObject($(tr).find('.bidderSectionDocId').attr("name"))){
	$(tr).find('.bidderSectionDocId').attr("name","priceBid.bidderSecDocList["+pbsdCount+"].bidderSectionDocId");
	$(tr).find('.sectionDocumentId').attr("name","priceBid.bidderSecDocList["+pbsdCount+"].sectionDocument.sectionDocumentId");
	$(tr).find('.attachment').attr("name","priceBid.bidderSecDocList["+pbsdCount+"].attachment.attachmentId");
	pbsdCount++;
	}
	$(".tenderattchfile").text(function(index, currentText) {
	    return currentText.substr(0, 20);
	});
}

function populateSavePriceBidResponse(itemBid){
	debugger;
	var itemBidId=itemBid.itemBidId==null?'':itemBid.itemBidId;
	$(".itemBidId").val(itemBid.itemBidId);
	$(".priceBidId").val(itemBid.priceBid.priceBidId);
	selectedItemBid[0].itemBidId=getValue(itemBidId);
	if(itemBid.response.hasError){
		  Alert.warn(getErrorMsgFromList(itemBid.response.errors));
	}else{
		if(itemBid.priceBid.response.hasError){
			Alert.warn(itemBid.priceBid.response.message);
		}else{
			Alert.info(itemBid.priceBid.response.message);
		}
	}
	
	if(!$.isEmptyObject(itemBidArray["tahdrMaterial"+itemBid.tahdrMaterial.tahdrMaterialId].priceBid)){
		itemBid.priceBid.bidderSecDoc=itemBidArray["tahdrMaterial"+itemBid.tahdrMaterial.tahdrMaterialId].priceBid.bidderSecDoc;
	}
	if(!$.isEmptyObject(itemBid.priceBid.digiSignedDoc)){
		itemBid.priceBid.digiSignedDoc.fileName=$("#digitalSignaturedPBAnchor").text();
	}
	itemBidArray["tahdrMaterial"+itemBid.tahdrMaterial.tahdrMaterialId].itemBidId=itemBid.itemBidId;
	itemBidArray["tahdrMaterial"+itemBid.tahdrMaterial.tahdrMaterialId].priceBid=itemBid.priceBid;
}

function priceBidSubmitResp(resp){
	Alert.info(resp.respose.message);
}


function savePbBidderDoc(itemBid){
	if(!isEmpty(itemBid) && !isEmpty(itemBid.response)){
		var response=itemBid.response;
		if(response.hasError){
			Alert.warn(response.message);
			return;
		}else if(!isEmpty(itemBid.itemBidId)){
			$(".itemBidId").val(getValue(itemBid.itemBidId));
			if(!isEmpty(itemBid.priceBid)){
				$(".priceBidId").val(getValue(itemBid.priceBid.priceBidId));
				Alert.info("Record Saved");
				populatePriceSecDocSet(itemBid.priceBid.bidderSecDoc);
			}
		}
	}
}
function populatePriceBidToRightPane(resp){
	var priceBid=resp.objectMap.priceBid;
	populatePriceBid(priceBid);
}
function populatePriceBid(priceBid){
	if(!$.isEmptyObject(priceBid)){
		var priceBidId=priceBid.priceBidId==null?'':priceBid.priceBidId;
		var offeredQuantity=priceBid.offeredQuantity==null?'':priceBid.offeredQuantity;
		var exGroupPriceRate=priceBid.exGroupPriceRate==null?'':priceBid.exGroupPriceRate;
		var freightChargeRate=priceBid.freightChargeRate==null?'':priceBid.freightChargeRate;
		var ticRate=priceBid.ticRate==null?'':priceBid.ticRate;
		var taxRate=priceBid.taxRate==null?'':priceBid.taxRate;
		var taxAmount=priceBid.taxAmount==null?'':priceBid.taxAmount;
		var fddRate=priceBid.fddRate==null?'':priceBid.fddRate;
		var fddInWords=priceBid.fddInWords==null?'':priceBid.fddInWords;
		var isConfirmed=priceBid.isConfirmed==null?'':priceBid.isConfirmed;
		
		var igst=getValue(priceBid.igst);
		var sgst=getValue(priceBid.cgst);
		var cgst=getValue(priceBid.sgst);
		
		var totalExGroupPrice=getValue(priceBid.totalExGroupPrice);
		var totalFreightCharges=getValue(priceBid.totalFreightCharge);
		var totalTic=getValue(priceBid.totalTic);
		var fddRate=getValue(priceBid.fddRate);
		var fddAmount=getValue(priceBid.fddAmount);
		var taxAmount=getValue(priceBid.taxAmount);
		var totalTax=getValue(priceBid.totalTax);
		var fddRateGST=getValue(priceBid.fddRateWithGST);
		var fddAmountGST=getValue(priceBid.fddAmountWithGST);
		var igstAmount=getValue(priceBid.igstAmount);
		var sgstAmount=getValue(priceBid.cgstAmount);
		var cgstAmount=getValue(priceBid.sgstAmount);
		var amountInWords=getValue(priceBid.amountInWords);
		
		var materialSpecificationId;
		if(!$.isEmptyObject(priceBid.materialSpecification)){
			materialSpecificationId=getValue(priceBid.materialSpecification.materialSpecificationId);
		}
		if(!$.isEmptyObject(priceBid.itemBid)){
			itemBidId=getValue(priceBid.itemBid.itemBidId);

			$(".itemBidId").val(itemBidId);
		}
		$(".priceBidId").val(priceBidId);
		$(".materialSpecificationId").val(materialSpecificationId);
		$("#priceBidId").val(priceBidId);
		$("#offeredQuantity").val(offeredQuantity);
		$("#exGroupPriceRate").val(exGroupPriceRate);
		$("#freightChargeRate").val(freightChargeRate);
		$("#ticRate").val(ticRate); //transit insurance charges
		$("#taxRate").val(taxRate);
		
		$("#igstLable").text(igst);
		$("#cgstLable").text(cgst);
		$("#sgstLable").text(sgst);
		
		$("#igst").val(igst);
		$("#cgst").val(cgst);
		$("#sgst").val(sgst);
		
		$("#taxAmount").val(taxAmount);
		$("#fddRate").val(fddRate);
		$("#fddInWords").val(fddInWords);
		$("#isConfirmed").val(isConfirmed);
		
		$("#totalExGroupPrice").val(totalExGroupPrice);
		$("#totalFreightCharges").val(totalFreightCharges);
		$("#totalTic").val(totalTic);
		$("#fddRate").val(fddRate);
		$("#fddAmount").val(fddAmount);
		$("#taxAmount").val(taxAmount);
		$("#totalTax").val(totalTax);
		$("#igstAmount").val(igstAmount);
		$("#cgstAmount").val(cgstAmount);
		$("#sgstAmount").val(sgstAmount);
		$("#fddRateGST").val(fddRateGST);
		$("#fddAmountGST").val(fddAmountGST);
		$("#fddAmountWithGSTInWords").val(amountInWords);
		$("#fddAmountWithGSTInWordsLabel").attr("title",amountInWords);
		$("#fddAmountWithGSTInWordsLabel").text(amountInWords);
		
		if(!$.isEmptyObject(priceBid) && !$.isEmptyObject(priceBid.digiSignedDoc)){
			$("#digitalSignaturedPB").val(priceBid.digiSignedDoc.attachmentId);
			$('.digitalSignaturedPB').attr('disabled',false);
			var url=$("#digitalSignaturedPBAnchor").data('url');
			$("#digitalSignaturedPBAnchor").attr('href',url);
			var a_pnlFile = $("#digitalSignaturedPBAnchor").prop('href')+'/'+priceBid.digiSignedDoc.attachmentId;
			$("#digitalSignaturedPBAnchor").prop('href',a_pnlFile);
			$("#digitalSignaturedPBAnchor").html(priceBid.digiSignedDoc.fileName);
		}else{
			resetFileInput("digitalSignaturedPB","digitalSignaturedPBAnchor");
		}
		$('#generatePbDocBtnId').removeAttr('disabled');
	}else{
		formulatePriceBid();
		$('#generatePbDocBtnId').attr('disabled','disabled');
		resetFileInput("digitalSignaturedPB","digitalSignaturedPBAnchor");
	}
}

function populatePriceSecDocSet(respList){
	$.each(respList,function(idx,bdrSecDoc){
		$("#priceSectionDocId_"+bdrSecDoc.sectionDocument.sectionDocumentId).val(bdrSecDoc.bidderSectionDocId)
	});
}

function deleteAdditionalDocPB(secDocId,event){
	event.preventDefault();
	var bidderSecDocId=$("#priceSectionDocId_"+secDocId).val();
	var bidderId=$(".bidderId").val();
	var itemBidId=$(".itemBidId").val();
	var tahdrDetailID=$(".tahdrDetailId").val();
	var resp;
	if(!$.isEmptyObject(bidderSecDocId)){
		resp=fetchAllList(bidderSecDocId,bidderId,itemBidId,tahdrDetailID);
		/*resp=fetchList("deleteBidderSecDoc",bidderSecDocId);*/
		deleteAdditionalDocPBResp(resp,secDocId,bidderSecDocId);
	}else{
		resetFileInput("secDocAtt_"+secDocId,"secDocAttName_"+secDocId);
	}
}

function deleteAdditionalDocPBResp(resp,secDocId,bidderSecDocId){
	if(!$.isEmptyObject(resp.objectMap.bidderSecDoc)){
		var bidderSecDoc=resp.objectMap.bidderSecDoc;
		if(bidderSecDoc.response.hasError){
			Alert.warn(bidderSecDoc.response.message);
		}else{
			Alert.info(bidderSecDoc.response.message);
			$("#priceSectionDocId_"+secDocId).val("");
			resetFileInput("secDocAtt_"+secDocId,"secDocAttName_"+secDocId);
			var tahdrMaterialId=$(".tahdrMaterialId").val();
			$.each(itemBidArray["tahdrMaterial"+tahdrMaterialId].priceBid.bidderSecDoc, function(idx,bidderSecDoc){
				if(bidderSecDoc.bidderSectionDocId==bidderSecDocId){
					bidderSecDoc.bidderSectionDocId=null;
					bidderSecDoc.attachment=null;
				}
			});
		}
	}
}

function generatePBDocument(event){
	event.preventDefault();
	showLoader();
	debugger;
	var itemBidId=$(".itemBidId").val();
	var priceBidId=$(".priceBidId").val();
	directSubmit(event,"genneratePBDoc","priceBid/"+itemBidId+"/"+priceBidId);
	hideLoader();
}

function onClickPriceBid(){
		partsArray=[];
		$(".priceBidId").val('');
		if(selectedTender.tahdr.tahdrTypeCode=='PT'){
			if(selectedItemBid[0].tahdrMaterial.materialTypeCode=='single'){
				$("#partsTab").hide();
				$("#priceSubTab").show();			
				getPriceBid();
				activePriceBid();
			}else if(selectedItemBid[0].tahdrMaterial.materialTypeCode=='bom'){
				$("#partsTab").show();
				$("#priceSubTab").show();
				getPriceBidForParts();
			}
		}else if(selectedTender.tahdr.tahdrTypeCode=='WT'){
			getPriceBidSecDoc();
			$("#partsTab").hide();
			$("#priceSubTab").hide();
			$("#pbCertificate").hide();
		}else if(selectedTender.tahdr.tahdrTypeCode=='FA'){
			$("#partsTab").hide();
			$("#priceSubTab").show();			
			getPriceBid();
			activePriceBid();
			$("#pbCertificate").hide();
		}
}

function getPriceBid(){
	cacheLi();
	setCurrentTab($("#priceSubTab"));
	setLeftPane();
	var tahdrMaterialId=$(".tahdrMaterialId").val();
	submitToURL("getPriceBid/"+tahdrMaterialId,"populatePriceBidToRightPane");
}

function getPriceBidSecDoc(){
	cacheLi();
	setCurrentTab($("#sectionDocumentSubTab"));
	setLeftPane();
	var tahdrMaterialId=$(".tahdrMaterialId").val();
	submitToURL("getPriceBidBidderSecDoc/"+tahdrMaterialId,"populatePBDocs");
	activePBDocs();
}

function activeParts(){
	$("#partsTab").addClass("active");
	$("#priceSubTab").removeClass("active");
	$("#sectionDocumentSubTab").removeClass("active");
	$("#submitPBTab").removeClass("active");
	
	$("#tab_k").addClass("active");
	$("#tab_e").removeClass("active");
	$("#tab_f").removeClass("active");
	$("#tab_g").removeClass("active");
}

function activePriceBid(){
	$("#partsTab").removeClass("active");
	$("#priceSubTab").addClass("active");
	$("#sectionDocumentSubTab").removeClass("active");
	$("#submitPBTab").removeClass("active");
	
	$("#tab_k").removeClass("active");
	$("#tab_e").addClass("active");
	$("#tab_f").removeClass("active");
	$("#tab_g").removeClass("active");
}

function activePBDocs(){
	$("#partsTab").removeClass("active");
	$("#priceSubTab").removeClass("active");
	$("#sectionDocumentSubTab").addClass("active");
	$("#submitPBTab").removeClass("active");
	
	$("#tab_k").removeClass("active");
	$("#tab_e").removeClass("active");
	$("#tab_f").addClass("active");
	$("#tab_g").removeClass("active");
}

function activePBConfirmation(){
	$("#partsTab").removeClass("active");
	$("#priceSubTab").removeClass("active");
	$("#sectionDocumentSubTab").removeClass("active");
	$("#submitPBTab").addClass("active");
	
	$("#tab_k").removeClass("active");
	$("#tab_e").removeClass("active");
	$("#tab_f").removeClass("active");
	$("#tab_g").addClass("active");
}

function confirmPriceBid(){
	getPriceBid();
	activePBConfirmation();
}

function deleteAttachmentPB(){
	var bidderId=$(".bidderId").val();
	var itemBidId=$(".itemBidId").val();
	submitToURL("deleteFinalPBDoc/"+bidderId+"/"+itemBidId,"respOfPBSubmit");
	submitWithParam('deleteAttachment','digitalSignaturedPB','digitalSignaturePBDelResp');
}


function digitalSignaturePBDelResp(data) {
	
	if (!data.hasError) {
		$('#digitalSignaturedPBFile').val('');
		$('#digitalSignaturedPB').val('');
		/*$("#digitalSignaturedTBAnchor").removeAttr('href');*/
		$("#digitalSignaturedPBAnchor").html('');
		$('.digitalSignaturedPB').attr('disabled', true);
		Alert.info(data.message);
	} else {
		Alert.warn(data.message);
	}

}

function respOfPBSubmit(data){
	console.log(data);
}

function onChangeGSTPriceBid(){
	debugger;
	gstCalculate();
	/*var tahdrId=$(".tahdrId").val();
	var resp=fetchList("getCommercialBid",tahdrId);
	var commercialBid = resp.objectMap.commercialBid;
	var commercialGst=commercialBid.gst==null?'':commercialBid.gst;
	var priceBidGst=Number($("#taxRate").val());
	if($("input[name='toggleTenderType']").val()=='PT'){
		if(commercialGst!=null){
			if(commercialGst<priceBidGst){
				$("#taxRate").val('');
				$("#igst").val('');
				$("#cgst").val('');
				$("#sgst").val('');
				Alert.warn("Entered Gst should be less than or equal to Gst entered in Commercial Details.");
				return false;
			}else{
				gstCalculate();
			}
		}else{
			Alert.warn("Enter Gst in Delivery Details.");
		}
	}*/
}