
var cbsdCount=0;
var commercialBid={};
$(document).ready(function(){
	
	$("#deliveryDetail").on("click",function(event){
		event.preventDefault();
		$(this).attr("disabled","disabled");
		getCommercialBid();
		$(this).removeAttr("disabled");
	});
	
	$("#commercialDocumentTab").on("click",function(event){
		event.preventDefault();
		$(this).attr("disabled","disabled");
		getCommercialBidDoc();
		$(this).removeAttr("disabled");
	});
	
	$('#saveCommercialBidBtn').on('click',function(event){
		event.preventDefault();
		$(this).attr("disabled","disabled");
		submitIt("saveCommercialBidForm","saveCommercialBid","populateSaveCommercialBidResponse");
		updateBidderStatus();
		$(this).removeAttr("disabled");
	});
	
	$('#cancelCommercialBid').on('click',function(event){
		event.preventDefault();
		$(this).attr("disabled","disabled");
		if(!$.isEmptyObject(bidderArray["bidder"+$(".bidderId").val()].commercialBid)){
			populateCommercialBid(bidderArray["bidder"+$(".bidderId").val()].commercialBid);
		}
		$(this).removeAttr("disabled");
	});
	
	$("#saveCommercialDocBtn").on('click',function(event){
		event.preventDefault();
		if($(this).attr("disabled")!="disabled"){
			$(this).attr("disabled","disabled");
			
			var commercialBidId=$(".commercialBidId").val();
			$(".commercialBidId").val(getValue(commercialBidId));
			
			var itemBidId=$(".itemBidId").val();
			$(".itemBidId").val(getValue(itemBidId));
			
			submitIt("saveCommercialSecDocForm","saveCommercialSectionDocument","saveCommercialSectionDocumentResp");
			updateBidderStatus();
			$(this).removeAttr("disabled");
		}
	});
	
	$("#cancelCommercialDocBtn").on('click',function(){
		event.preventDefault();
		$(this).attr("disabled","disabled");
		if(!$.isEmptyObject(itemBidArray["tahdrMaterial"+$(".tahdrMaterialId").val()].commercialBid)){
			populateCBDocs(itemBidArray["tahdrMaterial"+$(".tahdrMaterialId").val()].commercialBid.bidderSecDoc);
		}
		$(this).removeAttr("disabled");
	});
	
});

function getCommercialBid(){
		cacheLi();
		setCurrentTab($("#deliveryDetailTab"));
		var tahdrId=$(".tahdrId").val();
		var resp=fetchList("getCommercialBid",tahdrId);
		var cb = resp.objectMap.commercialBid;
		var bidder=resp.objectMap.bidder;
		var ref=resp.objectMap.ref;
		if(!$.isEmptyObject(ref)){
			if(!$.isEmptyObject(selectedTender) && !$.isEmptyObject(selectedTender.tahdr) && !$.isEmptyObject(selectedTender.tahdr.tahdrDetail[0])){
				if(ref.code==="OTHERS"){
					var otheComPeriod=selectedTender.tahdr.tahdrDetail[0].otherCommencementPeriod;
					$("#referenceField").text(otheComPeriod);
				}else{
					$("#referenceField").text(ref.name);
				}
			}
			
			
		}
		/*var bidderId=bidder.bidderId==null?'':bidder.bidderId;*/
		/*$(".bidderId").val(bidderId);*/
		document.getElementById("saveCommercialBidForm").reset();
		populateCommercialBid(cb);
}

function populateCBDocs(cbDocList){
	cbsdCount=0;
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
		"<td class='col-sm-3'><div class='col-sm-10 col-xs-10' style='padding:0px;'><input type='file' id='' data-id='secDocAtt_"+doc.sectionDocument.sectionDocumentId+"' data-anchor='secDocAttName_"+doc.sectionDocument.sectionDocumentId+"' class='form-control uploadFile requiredFile ' onchange='onCBFileUpload(this)' /> "+
					"<input type='hidden' id='secDocAtt_"+doc.sectionDocument.sectionDocumentId+"' class='form-control attachment' value='"+attachmentId+"' /></div>" +
					"<span class='input-group-btn col-sm-1 col-xs-2' style='padding-left:0px;'>" +
						"<button class='btn btn-default pnlFile_1' onclick='deleteAdditionalDocCB("+doc.sectionDocument.sectionDocumentId+",event)' >" +
							"<i class='fa fa-times'></i>" +
						"</button>" +
					"</span></div>" +
					"</td>" +
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

function onCBFileUpload(ele){
	var tr=$(ele).parents('tr');
	if($.isEmptyObject($(tr).find('.bidderSectionDocId').attr("name"))){
	$(tr).find('.bidderSectionDocId').attr("name","bidderSecDocList["+cbsdCount+"].bidderSectionDocId");
	$(tr).find('.sectionDocumentId').attr("name","bidderSecDocList["+cbsdCount+"].sectionDocument.sectionDocumentId");
	$(tr).find('.attachment').attr("name","bidderSecDocList["+cbsdCount+"].attachment.attachmentId");
	cbsdCount++;
	}
	$(".tenderattchfile").text(function(index, currentText) {
	    return currentText.substr(0, 20);
	});
}

function populateSaveCommercialBidResponse(commercialBid){
	$(".bidderId").val(commercialBid.bidder.bidderId);
	$(".commercialBidId").val(commercialBid.commercialBidId);
	
	if(commercialBid.response.hasError){
		var msg=getErrorMsgFromList(commercialBid.response.errors);
		Alert.warn(msg);
		$("#firstLot").addClass('errorinput');
		$("#ratePerMonth").addClass('errorinput');
	}else{
		Alert.info(commercialBid.response.message);
	}
}

function populateCommercialBid(commercialBid){
	var commencementWithin=selectedTender.tahdr.tahdrDetail[0].commencementPeriod;

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
	setChildLoadFlag(true);
}

function saveCommercialSectionDocumentResp(commercialBid){
	
	/*if(!$.isEmptyObject(bidder.commercialBid)){
		$(".commercialBidId").val(getValue(bidder.commercialBid.commercialBidId));
	}*/
	
	Alert.info("Record Saved");
	populateCommercialSecDocSet(commercialBid.bidderSecDoc);
}

function populateCommercialSecDocSet(list){
	$.each(list,function(idx,bdrSecDoc){
		if(idx==0){
			$(".commercialBidId").val(getValue(bdrSecDoc.commercialBid.commercialBidId));
		}
		$("#commercialSectionDocId_"+bdrSecDoc.sectionDocument.sectionDocumentId).val(bdrSecDoc.bidderSectionDocId)
	});
}

function deleteAdditionalDocCB(secDocId,event){
	debugger;
	event.preventDefault();
	var bidderSecDocId=$("#commercialSectionDocId_"+secDocId).val();
	var bidderId=$(".bidderId").val();
	var itemBidId=$(".itemBidId").val();
	var tahdrDetailID=$(".tahdrDetailId").val();
	var resp;
	if(!$.isEmptyObject(bidderSecDocId)){
		resp=fetchAllList(bidderSecDocId,bidderId,itemBidId,tahdrDetailID);
		/*resp=fetchList("deleteBidderSecDoc",bidderSecDocId,bidderId);*/
		deleteAdditionalDocCBResp(resp,secDocId,bidderSecDocId);
	}else{
		resetFileInput("secDocAtt_"+secDocId,"secDocAttName_"+secDocId);
	}
}

function deleteAdditionalDocCBResp(resp,secDocId,bidderSecDocId){
	debugger;
	if(!$.isEmptyObject(resp.objectMap.bidderSecDoc)){
		var bidderSecDoc=resp.objectMap.bidderSecDoc;
		if(bidderSecDoc.response.hasError){
			Alert.warn(bidderSecDoc.response.message);
		}else{
			Alert.info(bidderSecDoc.response.message);
			$("#commercialSectionDocId_"+secDocId).val("");
			resetFileInput("secDocAtt_"+secDocId,"secDocAttName_"+secDocId);
		}
	}
}

function generateCBDocument(event){
	event.preventDefault();
	showLoader();
	var bidderId=$(".bidderId").val();
	directSubmit(event,"generateCBDoc","commercialBidReport/"+bidderId);
	hideLoader();
}

function calculateCBGST(){
	var gst=$("#gstCB").val();
	var igst=Number(gst);
	var cgst=(igst/2).toFixed(2);
	var sgst=(igst/2).toFixed(2);
	
	$("#igstCB").val(igst);
	$("#cgstCB").val(cgst);
	$("#sgstCB").val(sgst);

}

function onClickCommercialBid(){
	if(selectedTender.tahdr.tahdrTypeCode=='PT' || selectedTender.tahdr.tahdrTypeCode=='RA' ){
		getCommercialBid();
	}else if(selectedTender.tahdr.tahdrTypeCode=='WT' || selectedTender.tahdr.tahdrTypeCode=='FA'){
		getCommercialBidDoc();
	}
}

function getCommercialBidDoc(){
	cacheLi();
	setCurrentTab($("#commercialDocumentTab"));
	var tahdrId=$(".tahdrId").val();
	var resp=fetchList("getCommercialBidDocs",tahdrId);
	var bidderSecDocSet = resp.objectMap.bidderSecDocSet;
	populateCBDocs(bidderSecDocSet);
}

function deleteAttachmentCB(){
	var bidderId=$(".bidderId").val();
	var tahdrDetailID=$(".tahdrDetailId").val();
	submitToURL("deleteFinalCBDoc/"+bidderId+"/"+tahdrDetailID,"respOfCBSubmit");
	submitWithParam('deleteAttachment','digitalSignaturedCB','digitalSignatureCBDelResp');
}


function digitalSignatureCBDelResp(data) {
	
	if (!data.hasError) {
		$('#digitalSignaturedCBFile').val('');
		$('#digitalSignaturedCB').val('');
		/*$("#digitalSignaturedTBAnchor").removeAttr('href');*/
		$("#digitalSignaturedCBAnchor").html('');
		$('.digitalSignaturedCB').attr('disabled', true);
		Alert.info(data.message);
	} else {
		Alert.warn(data.message);
	}


}

function respOfCBSubmit(data){
	console.log(data);
}

function onDeliverGST(){
	debugger;
	var minQuantity=$('#gstCB').val();
	if(Number(minQuantity)<=100){
		$("#gstCB").val(minQuantity);
		calculateCBGST();
	}
	else{
		Alert.warn("Minimum % of GST cannot be more than 100.")
		$("#gstCB").val('');
	}
}