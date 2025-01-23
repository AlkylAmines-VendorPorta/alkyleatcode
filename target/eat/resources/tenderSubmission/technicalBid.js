
var tbsdCount=0;

$(document).ready(function(){
	/*$('#technicalBidTable').DataTable({
		"lengthMenu":lengthMenu
	});*/
	
	$('#saveTechnicalBidBtn').on('click',function(event){
		event.preventDefault();
		$(this).attr("disabled","disabled");
		
		var technicalBidId=$(".technicalBidId").val();
		$(".technicalBidId").val(getValue(technicalBidId));
		
		var itemBidId=$(".itemBidId").val();
		$(".itemBidId").val(getValue(itemBidId));
		
		submitIt("saveTechnicalBidForm","saveTechnicalBid","populateSaveTechnicalBidResponse");
		updateBidderStatus();
		$(this).removeAttr("disabled");
	});
	
	$('#cancelTechnicalBid').on('click',function(event){
		event.preventDefault();
		$(this).attr("disabled","disabled");
		if(!$.isEmptyObject(itemBidArray["tahdrMaterial"+$(".tahdrMaterialId").val()].technicalBid)){
			populateTechnicalBid(itemBidArray["tahdrMaterial"+$(".tahdrMaterialId").val()].technicalBid);
		}
		$(this).removeAttr("disabled");
	});
	
	$("#saveTechnicalDocBtn").on('click',function(event){
		event.preventDefault();
		$(this).attr("disabled","disabled");
		
		var technicalBidId=$(".technicalBidId").val();
		$(".technicalBidId").val(getValue(technicalBidId));
		
		var itemBidId=$(".itemBidId").val();
		$(".itemBidId").val(getValue(itemBidId));
		
		submitIt("saveTechSecDocForm","saveTechnicalSectionDocument","saveTechnicalDocResponse");
		updateBidderStatus();
		$(this).removeAttr("disabled");
	});
	
	$("#cancelTechnicalDocBtn").on('click',function(event){
		event.preventDefault();
		$(this).attr("disabled","disabled");
		if(!$.isEmptyObject(itemBidArray["tahdrMaterial"+$(".tahdrMaterialId").val()].technicalBid)){
			populateTBDocs(itemBidArray["tahdrMaterial"+$(".tahdrMaterialId").val()].technicalBid.bidderSecDoc);
		}
		$(this).removeAttr("disabled");
	});
	
	$("#submitTechnicalBid").on("click",function(event){
		event.preventDefault();
		$(this).attr("disabled","disabled");
		submitIt("submitTechnicalBidForm","submitTechnicalBid","populateSaveTechnicalBidResponse");
		updateBidderStatus();
		$(this).removeAttr("disabled");
	});
});

function populateTechnicalBid(resp){
	var technicalBid=resp.objectMap.technicalBid;
	var itemBid;
	
	var itemBidId;
	var bidderId=$(".bidderId").val();
	var tahdrMaterialId=$(".tahdrMaterialId").val();
	var tahdrDetailId=$(".tahdrDetailId").val();
	if(!$.isEmptyObject(technicalBid)){
		$(".technicalBidId").val(getValue(technicalBid.technicalBidId));
		itemBid=technicalBid.itemBid;
		if(!$.isEmptyObject(itemBid)){
			$(".itemBidId").val(getValue(itemBid.itemBidId));
			itemBidId=getValue(itemBid.itemBidId);
		}
		$('#generateTBDocBtnId').removeAttr('disabled');
	}else{
		$(".technicalBidId").val('');
		$('#generateTBDocBtnId').attr('disabled','disabled');
	}
	
	if(!$.isEmptyObject(technicalBid) && !$.isEmptyObject(technicalBid.digiSignedDoc)){
		$("#digitalSignaturedTB").val(technicalBid.digiSignedDoc.attachmentId);
		$('.digitalSignaturedTB').attr('disabled',false);
		var url=$("#digitalSignaturedTBAnchor").data('url');
		$("#digitalSignaturedTBAnchor").attr('href',url);
		var a_pnlFile = $("#digitalSignaturedTBAnchor").prop('href')+'/'+technicalBid.digiSignedDoc.attachmentId;
		$("#digitalSignaturedTBAnchor").prop('href',a_pnlFile);
		$("#digitalSignaturedTBAnchor").html(technicalBid.digiSignedDoc.fileName);
	}else{
		resetFileInput("digitalSignaturedTB","digitalSignaturedTBAnchor");
	}	
}

function populateTechnicalBidWithBidderGtp(resp){
	populateTechnicalBid(resp);
	populateBidderGtpList(resp);
}

function populateTBDocs(resp){	
	tbsdCount=0;
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
				"<td class='col-sm-3'><div class='col-sm-10' style='padding:0px;'><input type='file' id='' data-id='secDocAtt_"+doc.sectionDocument.sectionDocumentId+"' data-anchor='secDocAttName_"+doc.sectionDocument.sectionDocumentId+"' class='form-control uploadFile requiredFile ' onchange='onTBFileUpload(this)' /> "+
					"<input type='hidden' id='secDocAtt_"+doc.sectionDocument.sectionDocumentId+"' class='form-control attachment' value='"+attachmentId+"' /></div>" +
					"<span class='input-group-btn col-sm-1' style='padding-left:0px;'>" +
						"<button class='btn btn-default pnlFile_1' onclick='deleteAdditionalDocTB("+doc.sectionDocument.sectionDocumentId+",event)' >" +
							"<i class='fa fa-times'></i>" +
						"</button>" +
					"</span></div>" +
				" </td>" +
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

function technicalBidSubmitResp(resp){
	Alert.info(resp.respose.message);
}

function onTBFileUpload(ele){
	var tr=$(ele).parents('tr');
	if($.isEmptyObject($(tr).find('.bidderSectionDocId').attr("name"))){
		$(tr).find('.bidderSectionDocId').attr("name","technicalBid.bidderSecDocList["+tbsdCount+"].bidderSectionDocId");
		$(tr).find('.sectionDocumentId').attr("name","technicalBid.bidderSecDocList["+tbsdCount+"].sectionDocument.sectionDocumentId");
		$(tr).find('.attachment').attr("name","technicalBid.bidderSecDocList["+tbsdCount+"].attachment.attachmentId");
		tbsdCount++;
	}
	$(".tenderattchfile").text(function(index, currentText) {
	    return currentText.substr(0, 20);
	});
}

function populateSaveTechnicalBidResponse(itemBid){
	if(!$.isEmptyObject(itemBid)){
		$(".itemBidId").val(getValue(itemBid.itemBidId));
	}
	
	if(!$.isEmptyObject(itemBid.technicalBid)){
		$(".technicalBidId").val(getValue(itemBid.technicalBid.technicalBidId));
	}else{
		$(".technicalBidId").val("");
	}
	
	if(itemBid.response.hasError){
		if(!$.isEmptyObject(itemBid.response.message)){
			Alert.warn(itemBid.response.message);
		}else{
			Alert.warn(getErrorMsgFromList(itemBid.response.errors));
		}	
	}else{
		Alert.info(itemBid.response.message);
	}
	
	if(!$.isEmptyObject(itemBidArray["tahdrMaterial"+itemBid.tahdrMaterial.tahdrMaterialId].technicalBid)){
		itemBid.technicalBid.bidderSecDoc=itemBidArray["tahdrMaterial"+itemBid.tahdrMaterial.tahdrMaterialId].technicalBid.bidderSecDoc;
	}
	if(!$.isEmptyObject(itemBid.technicalBid.digiSignedDoc)){
		itemBid.technicalBid.digiSignedDoc.fileName=$("#digitalSignaturedTBAnchor").text();
	}	
	itemBidArray["tahdrMaterial"+itemBid.tahdrMaterial.tahdrMaterialId].itemBidId=itemBid.itemBidId;
	itemBidArray["tahdrMaterial"+itemBid.tahdrMaterial.tahdrMaterialId].technicalBid=itemBid.technicalBid;
	$.each(itemBid.technicalBid.bidderGtp,function(idx,obj){
		$('#bidderGtp_'+obj.tahdrMaterialgtp.tahdrMaterialGtpId).val(obj.bidderGtpId);
	});
}

function saveTechnicalDocResponse(itemBid){
	
	if(!$.isEmptyObject(itemBid)){
		$(".itemBidId").val(getValue(itemBid.itemBidId));
		selectedItemBid[0].itemBidId=getValue(itemBid.itemBidId);
	}
	
	if(!$.isEmptyObject(itemBid.technicalBid))
		$(".technicalBidId").val(getValue(itemBid.technicalBid.technicalBidId));
	
	Alert.info("Record Saved");
	
	populateTechSecDocSet(itemBid.technicalBid.bidderSecDoc);
}

function populateTechSecDocSet(respList){
	$.each(respList,function(idx,bdrSecDoc){
		$("#TechnicalSectionDocId_"+bdrSecDoc.sectionDocument.sectionDocumentId).val(bdrSecDoc.bidderSectionDocId)
	});
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
		tr+="<td class='col-sm-6'> <input type='file' "+dis+" id='file_"+index+"' data-id='response_"+index+"' data-anchor='anchor"+index+"' class='form-control uploadFile'>" +
			"<input type='hidden' "+dis+" id='response_"+index+"' class='requiredField form-control'  name='technicalBid.bidderGtpList["+index+"].fileResponse.attachmentId'>";
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

function deleteAdditionalDocTB(secDocId,event){
	event.preventDefault();
	var bidderSecDocId=$("#TechnicalSectionDocId_"+secDocId).val();
	var bidderId=$(".bidderId").val();
	var itemBidId=$(".itemBidId").val();
	var tahdrDetailID=$(".tahdrDetailId").val();
	var resp;
	if(!$.isEmptyObject(bidderSecDocId)){
		resp=fetchAllList(bidderSecDocId,bidderId,itemBidId,tahdrDetailID);
		/*resp=fetchList("deleteBidderSecDoc",bidderSecDocId);*/
		deleteAdditionalDocTBResp(resp,secDocId,bidderSecDocId);
	}else{
		resetFileInput("secDocAtt_"+secDocId,"secDocAttName_"+secDocId);
	}
}

function deleteAdditionalDocTBResp(resp,secDocId,bidderSecDocId){
	if(!$.isEmptyObject(resp.objectMap.bidderSecDoc)){
		var bidderSecDoc=resp.objectMap.bidderSecDoc;
		if(bidderSecDoc.response.hasError){
			Alert.warn(bidderSecDoc.response.message);
		}else{
			Alert.info(bidderSecDoc.response.message);
			$("#TechnicalSectionDocId_"+secDocId).val("");
			resetFileInput("secDocAtt_"+secDocId,"secDocAttName_"+secDocId);
			var tahdrMaterialId=$(".tahdrMaterialId").val();
			$.each(itemBidArray["tahdrMaterial"+tahdrMaterialId].technicalBid.bidderSecDoc, function(idx,bidderSecDoc){
				if(bidderSecDoc.bidderSectionDocId==bidderSecDocId){
					bidderSecDoc.bidderSectionDocId=null;
					bidderSecDoc.attachment=null;
				}
			});
		}
	}
}

function generateTBDocument(event){
	event.preventDefault();
	showLoader();
	var technicalBidId=$(".technicalBidId").val();
	var technicalBidReportUrl=$("#tbReportUrl").val();
	directSubmit(event,"gennerateTBDoc",technicalBidReportUrl+technicalBidId);
	hideLoader();
}


function getTechnicalBidDetails(){
	/*setLeftPane();*/
	$(".technicalBidId").val('');
	var tahdrMaterialId=$(".tahdrMaterialId").val();
	submitToURL("getTechnicalBid/"+tahdrMaterialId,"populateTechnicalBid");
}


function getTechnicalBid(){
		getTechnicalBidDetails();
		if(selectedTender.tahdr.tahdrTypeCode=='WT'){
			document.getElementById('sectionDocumentTab').click();
			$('#sectionDocumentTab').addClass('active');
			$('#tab_c').addClass('active');
			$('#bidderGtpSubTab').removeClass('active');
			$('#tab_a').removeClass('active');
			$('#submitTBTab').removeClass('active');
			$('#tab_d').removeClass('active');
			/*getTechnicalBidSecDoc();*/
		}else{
			document.getElementById('bidderGtpSubTab').click();
			$('#bidderGtpSubTab').addClass('active');
			$('#tab_a').addClass('active');
			$('#sectionDocumentTab').removeClass('active');
			$('#tab_c').removeClass('active');
			$('#submitTBTab').removeClass('active');
			$('#tab_d').removeClass('active');
			/*getBidderGtp();*/
		}
}

function getTechnicalBidWithBidderGtp(){
	cacheLi();
	setCurrentTab($("#technicalBidTab"));
	setLeftPane();
	var tahdrMaterialId=$(".tahdrMaterialId").val();
	submitToURL("getTechnicalBidWithBidderGtp/"+tahdrMaterialId,"populateTechnicalBidWithBidderGtp");
}

function getBidderGtp(){
	cacheLi();
	setCurrentTab($("#bidderGtpSubTab"));
	setLeftPane();
	var tahdrMaterialId=$(".tahdrMaterialId").val();
	submitToURL("getTechnicalBidBidderGtp/"+tahdrMaterialId,"populateBidderGtpList");
}

function getTechnicalBidSecDoc(){
	cacheLi();
	setCurrentTab($("#sectionDocumentTab"));
	setLeftPane();
	var tahdrMaterialId=$(".tahdrMaterialId").val();
	submitToURL("getTechnicalBidBidderSecDoc/"+tahdrMaterialId,"populateTBDocs");
}

function populateBidderGtpList(resp){
	var itemBidId=getValue($(".itemBidId").val());
	var tahdrMaterialId=getValue($(".tahdrMaterialId").val());
	var bidderId=getValue($(".bidderId").val());
	var tahdrDetailId=getValue($(".tahdrDetailId").val());
	var technicalBidId=getValue($(".technicalBidId").val());
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
			"<input type='hidden' class='itemBidId' value='"+itemBidId+"' name='itemBidId' >" +
			"<input type='hidden' class='tahdrMaterialId' value='"+tahdrMaterialId+"' name='tahdrMaterial.tahdrMaterialId' > "+
			"<input type='hidden' class='bidderId' value='"+bidderId+"' name='bidder.bidderId' > "+
			"<input type='hidden' class='tahdrDetailId' value='"+tahdrDetailId+"' name='itemBid.bidder.tahdrDetail.tahdrDetailId' > "+
			
			"<input type='hidden' class='technicalBidId' value='"+technicalBidId+"' name='technicalBid.technicalBidId' >" +
			
			"<input type='hidden' value='"+tahdrMaterialGtpId+"' name='technicalBid.bidderGtpList["+index+"].tahdrMaterialgtp.tahdrMaterialGtpId' > " +
			"<input type='hidden' value='"+gtp+"' name='technicalBid.bidderGtpList["+index+"].tahdrMaterialgtp.gtp.name' > "+ gtp +
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

function deleteAttachmentTB(){
	var bidderId=$(".bidderId").val();
	var itemBidId=$(".itemBidId").val();
	var tahdrDetailID=$(".tahdrDetailId").val();
	submitToURL("deleteFinalTBDoc/"+bidderId+"/"+itemBidId+"/"+tahdrDetailID,"respOfTBSubmit");
	submitWithParam('deleteAttachment','digitalSignaturedTB','digitalSignatureTBDelResp');
}


function digitalSignatureTBDelResp(data) {
	
	if (!data.hasError) {
		$('#digitalSignaturedTBFile').val('');
		$('#digitalSignaturedTB').val('');
		/*$("#digitalSignaturedTBAnchor").removeAttr('href');*/
		$("#digitalSignaturedTBAnchor").html('');
		$('.digitalSignaturedTB').attr('disabled', true);
		Alert.info(data.message);
	} else {
		Alert.warn(data.message);
	}

}

function respOfTBSubmit(data){
	console.log(data);
}