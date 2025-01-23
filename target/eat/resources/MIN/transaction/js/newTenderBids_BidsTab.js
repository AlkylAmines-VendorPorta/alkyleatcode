$(document).ready(function(){
	$("#deliveryDetail").on("click",function(event){
		event.preventDefault();
		$(this).attr("disabled","disabled");
		getBids_DeliverDetail();
		$(this).removeAttr("disabled");
	});
	
	$("#commercialDocumentTab").on("click",function(event){
		event.preventDefault();
		$(this).attr("disabled","disabled");
		getBids_CommercialDocs();
		$(this).removeAttr("disabled");
	});
	
});

function getTechnicalBid(){
	var tahdrMaterialId=$("#itemDetailForm #tahdrMaterialId").val();
	var bidderId=$("#bidderDetailForm #bidderId").val();
	var resp=fetchList("getPartnerTechnicalBidData/"+bidderId+"/"+tahdrMaterialId,null);
	var data=resp.objectMap.technicalBid==null?null:resp.objectMap.technicalBid.digiSignedDoc;
	var url=$("#downloadTechnicalBid").data('url');
	if(data!=null){
		var file=data.fileName==null?'':data.fileName;
		var techBid=data.attachmentId==null?null:data.attachmentId;
		$("#downloadTechnicalBid").attr('href',url);
		$("#downloadTechnicalBid").prop('href', url+'/'+techBid);
		$("#downloadTechnicalBid").html(file);
	}else{
		$("#downloadTechnicalBid").removeAttr('href',url);
		$("#downloadTechnicalBid").html(''); 
	}
}

function getPriceBid(){
	var tahdrMaterialId=$("#itemDetailForm #tahdrMaterialId").val();
	var bidderId=$("#bidderDetailForm #bidderId").val();
	var resp=fetchList("getPartnerPriceBidData/"+bidderId+"/"+tahdrMaterialId,null);
	var data=resp.objectMap.priceBid==null?null:resp.objectMap.priceBid.digiSignedDoc;
	var url=$("#downloadPriceBid").data('url');
	if(data!=null){
		var file=data.fileName==null?'':data.fileName;
		var priceBid=data.attachmentId==null?null:data.attachmentId;
		$("#downloadPriceBid").attr('href',url);
		$("#downloadPriceBid").prop('href', url+'/'+priceBid);
		$("#downloadPriceBid").html(file);
	}else{
		$("#downloadPriceBid").removeAttr('href',url);
		$("#downloadPriceBid").html(''); 
	}
}

function getCommercialBid(){
	var bidderId=$("#bidderDetailForm #bidderId").val();
	var resp=fetchList("getPartnerCommercialBidData/"+bidderId,null);
	var data=resp.objectMap.commercialBid==null?null:resp.objectMap.commercialBid.digiSignedDoc;
	var url=$("#downloadCommercialBid").data('url');
	if(data!=null){
		var file=data.fileName==null?'':data.fileName;
		var commBid=data.attachmentId==null?null:data.attachmentId;
		$("#downloadCommercialBid").attr('href',url);
		$("#downloadCommercialBid").prop('href', url+'/'+commBid);
		$("#downloadCommercialBid").html(file);
	}else{
		$("#downloadCommercialBid").removeAttr('href',url);
		$("#downloadCommercialBid").html(''); 
	}
}

function getBids_GTP_Parameter(){
	var tahdrMaterialId=$("#itemDetailForm #tahdrMaterialId").val();
	var partnerId=$("#bidderDetailForm #partnerId").val();
	if(tahdrMaterialId!=null && partnerId!=null){
		submitToURL("getTechnicalBidderGtp/"+tahdrMaterialId+"/"+partnerId,"getBids_GTP_ParameterResp");
	}else{
		alert("#itemDetailForm #tahdrMaterialId OR #bidderDetailForm #bidderId is NULL");
	}
}

function getBids_GTP_ParameterResp(resp){
	var bidderGtpList=resp.objectMap.bidderGtpList;
	$("#technicalBidTable").DataTable().destroy();
	$("#technicalBidTable tbody").empty();
	$.each(bidderGtpList,function(idx,obj){
		var index=idx;
		var tahdrMaterialGtpId = getValue(obj.tahdrMaterialgtp.tahdrMaterialGtpId);
		var gtp=getValue(obj.tahdrMaterialgtp.gtp.name);
		var gtpType=getValue(obj.tahdrMaterialgtp.gtp.gtpParameterType.name);
		var gtpTypeCode=getValue(obj.tahdrMaterialgtp.gtp.gtpParameterType.code);
		var response=gtpTypeCode=='FILE'?obj.fileResponse:getValue(obj.textResponse);
		var bidderGtpId=obj.bidderGtpId==null?'':obj.bidderGtpId;
		var checked=obj.isNotApplicable=='Y'?'checked':'';
		
		var	tr="<tr>" +
		"<td class='col-sm-3'> " +
			"<input type='hidden' value='"+gtp+"' > "+ gtp +
		"</td>" +
		"<td class='col-sm-3'> <input type='hidden' id='bidderGtp_"+tahdrMaterialGtpId+"' value='"+bidderGtpId+"' name='technicalBid.bidderGtpList["+index+"].bidderGtpId' > " +
			 "<input type='hidden' value='"+gtpType+"' name='technicalBid.bidderGtpList["+index+"].tahdrMaterialgtp.gtp.gtpParameterType.name' > "+ gtpType +"</td>";
		
		tr+=getResponseCell(response,gtpTypeCode,index,obj.isNotApplicable);
		
		tr+="<td><input type='checkbox' "+checked+" id='na_"+index+"' class='NA' name='technicalBid.bidderGtpList["+index+"].isNotApplicable' value='Y' onchange='checkedNA(this)' /></td>";
		tr+="</tr>";
		$("#technicalBidTable tbody").append(tr);
	});
	checkKeyPressEvents();
	$('#technicalBidTable').DataTable({
		"lengthMenu":lengthMenu,
		"bSort":false
	});
	mobiletable();
}

function getBids_TechincalDocs(){
	var tahdrMaterialId=$("#itemDetailForm #tahdrMaterialId").val();
	var partnerId=$("#bidderDetailForm #partnerId").val();
	if(tahdrMaterialId!=null && partnerId!=null){
		submitToURL("getTechnicalBidderSecDoc/"+tahdrMaterialId+"/"+partnerId,"getBids_TechincalDocsResp");
	}else{
		alert("#itemDetailForm #tahdrMaterialId OR #bidderDetailForm #bidderId is NULL");
	}
}

function getBids_TechincalDocsResp(resp){
	var tbDocList=resp.objectMap.bidderSecDocList;
	$("#tbDocumentTable").DataTable().destroy();
	$("#tbDocumentTable tbody").empty();
	$.each(tbDocList,function(idx,doc){
		if(idx==0){
			if(!$.isEmptyObject(doc.technicalBid)){
				$(".technicalBidId").val(doc.technicalBid.technicalBidId);
				if(!$.isEmptyObject(doc.itemBid)){
					$(".itemBidId").val(doc.technicalBid.itemBid.itemBidId);
				}
			}
		}
		var attachmentId='';
		var fileName='';
		if(!$.isEmptyObject(doc.attachment)){
			attachmentId=getValue(doc.attachment.attachmentId);
			fileName=getValue(doc.attachment.fileName);
		}
		var tr=	"<tr>"+
				"<td class='col-sm-3'><input type='hidden' id='TechnicalSectionDocId_"+getValue(doc.sectionDocument.sectionDocumentId)+"' class='bidderSectionDocId' value='"+getValue(doc.bidderSectionDocId)+"'>" +
					"<input type='hidden' class='sectionDocumentId' value='"+getValue(doc.sectionDocument.sectionDocumentId)+"'>"+getValue(doc.sectionDocument.name)+"</td>"+
				"<td class='col-sm-3'>"+getValue(doc.sectionDocument.description)+"</td>"+
				"<td class='col-sm-3'></td>" +
				"<td class='col-sm-3'><a data-url='download' title='"+fileName+"' href='download/"+attachmentId+"' id='secDocAttName_"+doc.sectionDocument.sectionDocumentId+"' class='tenderattchfile secDocAttName_"+doc.sectionDocument.sectionDocumentId+"'>"+fileName+"</a> </td>" +
			"</tr>";
		$("#tbDocumentTable tbody").append(tr);
	});	
	$("#tbDocumentTable").DataTable({
		"lengthMenu":lengthMenu,
		"bSort":false
	});
	mobiletable();

}

function getBids_PriceBid(){
	var tahdrMaterialId=$("#itemDetailForm #tahdrMaterialId").val();
	var partnerId=$("#bidderDetailForm #partnerId").val();
	if(tahdrMaterialId!=null && partnerId!=null){
		submitToURL("getPartnerPriceBid/"+tahdrMaterialId+"/"+partnerId,"getBids_PriceBidResp");
	}else{
		alert("#itemDetailForm #tahdrMaterialId OR #bidderDetailForm #bidderId is NULL");
	}
}

function getBids_PriceBidResp(resp){
	var priceBid=resp.objectMap.priceBid;
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
	}
}
function getBids_PriceBidDocs(){
	var tahdrMaterialId=$("#itemDetailForm #tahdrMaterialId").val();
	var partnerId=$("#bidderDetailForm #partnerId").val();
	if(tahdrMaterialId!=null && partnerId!=null){
		submitToURL("getPartnerPriceBidBidderSecDoc/"+tahdrMaterialId+"/"+partnerId,"getBids_PriceBidDocsResp");
	}else{
		alert("#itemDetailForm #tahdrMaterialId OR #bidderDetailForm #bidderId is NULL");
	}
}

function getBids_PriceBidDocsResp(resp){
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
		"<td class='col-sm-3'></td>" +
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

function getBids_DeliverDetail(){
	var tahdrId=$('#tahdrId').val();
	var partnerId=$("#bidderDetailForm #partnerId").val();
	var resp=fetchList("getPartnerCommercialBid/"+tahdrId+"/"+partnerId,null);
	getBids_DeliverDetailResp(resp);
}

function getBids_DeliverDetailResp(resp){
	var cb = resp.objectMap.commercialBid;
	var ref=resp.objectMap.ref;
	if(!$.isEmptyObject(ref)){
		$("#referenceField").text(ref.name);
	}
	document.getElementById("saveCommercialBidForm").reset();
	populateCommercialBid(cb);
}

function populateCommercialBid(commercialBid){
	var commencementWithin=commencementPeriod;

	$("#deliveringMonth").val(commencementWithin);
	if(!isEmpty(commercialBid)){

		var commercialBidId=commercialBid.commercialBidId==null?'':commercialBid.commercialBidId;
		$(".commercialBidId").val(commercialBidId);
		
		var firstLot=commercialBid.firstLot==null?'':commercialBid.firstLot;
		
		var ratePerMonth=commercialBid.ratePerMonth==null?'':commercialBid.ratePerMonth;
		
		var gst=commercialBid.gst==null?'':commercialBid.gst;
		var igst=commercialBid.igst==null?'':commercialBid.igst;
		var cgst=commercialBid.cgst==null?'':commercialBid.cgst;
		var sgst=commercialBid.sgst==null?'':commercialBid.sgst;
		
		
		$("#firstLot").val(firstLot);
		$("#ratePerMonth").val(ratePerMonth);
		/*$("#exciseDuty").val(exciseDuty);
		$("#exciseDutyRate").val(exciseDutyRate);*/
		
		$("#gstCB").val(gst);
		$("#igstCB").val(igst);
		$("#cgstCB").val(cgst);
		$("#sgstCB").val(sgst);
		
		if(!$.isEmptyObject(commercialBid) && !$.isEmptyObject(commercialBid.digiSignedDoc)){
			$("#digitalSignaturedCB").val(commercialBid.digiSignedDoc.attachmentId);
			$('.digitalSignaturedCB').attr('disabled',false);
			var url=$("#digitalSignaturedCBAnchor").data('url');
			$("#digitalSignaturedCBAnchor").attr('href',url);
			var a_pnlFile = $("#digitalSignaturedCBAnchor").prop('href')+'/'+commercialBid.digiSignedDoc.attachmentId;
			$("#digitalSignaturedCBAnchor").prop('href',a_pnlFile);
			$("#digitalSignaturedCBAnchor").html(commercialBid.digiSignedDoc.fileName);
		}else{
			resetFileInput("digitalSignaturedCB","digitalSignaturedCBAnchor");
		}
	}
}

function getBids_ItemQuoted(){
	alert("Hi getBids_ItemQuoted");
}

function getBids_CommercialDocs(){
	var tahdrId=$('#tahdrId').val();
	var partnerId=$("#bidderDetailForm #partnerId").val();
	var resp=fetchList("getPartnerCommercialBidDocs/"+tahdrId+"/"+partnerId,null);
	var bidderSecDocSet = resp.objectMap.bidderSecDocSet;
	populateCBDocs(bidderSecDocSet);
}

function populateCBDocs(cbDocList){
	$("#cbDocumentTable").DataTable().destroy();
	$("#cbDocumentTable tbody").empty();
	
	$.each(cbDocList,function(idx,doc){
		var attachmentId='';
		var fileName='';
		if(!$.isEmptyObject(doc.attachment)){
			attachmentId=getValue(doc.attachment.attachmentId);
			fileName=getValue(doc.attachment.fileName);
		}
		
		var tr=	"<tr>"+
		"<td class='col-sm-3'><input type='hidden' id='commercialSectionDocId_"+getValue(doc.sectionDocument.sectionDocumentId)+"' class='bidderSectionDocId' value='"+getValue(doc.bidderSectionDocId)+"'>" +
			"<input type='hidden' class='sectionDocumentId' value='"+getValue(doc.sectionDocument.sectionDocumentId)+"'>"+getValue(doc.sectionDocument.name)+"</td>"+
		"<td class='col-sm-3'>"+getValue(doc.sectionDocument.description)+"</td>"+
		"<td class='col-sm-3'></td>" +
		"<td class='col-sm-3'><a data-url='download' title='"+fileName+"' href='download/"+attachmentId+"' id='secDocAttName_"+doc.sectionDocument.sectionDocumentId+"' class='tenderattchfile secDocAttName_"+doc.sectionDocument.sectionDocumentId+"'>"+fileName+"</a> </td>" +
		"</tr>";
	$("#cbDocumentTable tbody").append(tr);
	});
	$("#cbDocumentTable").DataTable({
		"lengthMenu":lengthMenu,
		"bSort":false
	});
	$(".tenderattchfile").text(function(index, currentText) {
	    return currentText.substr(0, 20);
	});
	mobiletable();
}

function getResponseCell(response,gtpTypeCode,index,na){
	var tr='';
	var dis='';
	if(na=='Y'){
		dis="disabled='disabled'";
	}
	if(gtpTypeCode=='FILE'){
		var attId='';
		var fileName='';
		tr+="<td class='col-sm-6'> ";
		if(!$.isEmptyObject(response)){
			attId=response.attachmentId;
			fileName=response.fileName;
		}
		tr+="<a class='mrgleft' data-url='download' href='download/"+attId+"' id='anchor"+index+"'>"+fileName+"</a> </td>";
	}else if(gtpTypeCode=='NUMERIC'){
		tr+="<td> <input type='text' "+dis+" id='response_"+index+"' class='onlyNumber form-control' name='technicalBid.bidderGtpList["+index+"].textResponse' value='"+response+"'> </td>";
	}else if(gtpTypeCode=='TEXT'){
		tr+="<td> <input type='text' "+dis+" id='response_"+index+"' class='requiredField form-control' name='technicalBid.bidderGtpList["+index+"].textResponse' value='"+response+"'> </td>";
	}else if(gtpTypeCode=='BOOLEAN'){
		var selectedY=response=='Y'?'selected':'';
		var selectedN=response=='N'?'selected':'';
		tr+="<td> <select "+dis+" id='response_"+index+"' class='requiredField form-control' name='technicalBid.bidderGtpList["+index+"].textResponse' >" +
				"<option value='Y' "+selectedY+">YES</option><option value='N' "+selectedN+" >NO</option> </td>";
	}
	return tr;
}

