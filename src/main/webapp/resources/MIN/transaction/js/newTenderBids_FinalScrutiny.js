function getFinalTechnicalScrutiny(){
	var tahdrMaterialId=$("#itemDetailForm #tahdrMaterialId").val();
	var bidderId=$("#bidderDetailForm #bidderId").val();
	var resp=fetchList("getPartnerFinalTechnicalScrutinyData/"+bidderId+"/"+tahdrMaterialId,null);
	var data=resp.objectMap.technicalScrutiny;
	var adata=resp.objectMap.technicalScrutiny==null?null:resp.objectMap.technicalScrutiny.attachment;
	var url=$("#downloadTechicalScrutiny").data('url');
	if(data!=null){
		var file=adata.fileName==null?'':adata.fileName;
		var techBid=adata.attachmentId==null?null:adata.attachmentId;
		$("#downloadTechicalScrutiny").attr('href',url);
		$("#downloadTechicalScrutiny").prop('href', url+'/'+techBid);
		$("#downloadTechicalScrutiny").html(file);
		$("#FS_T_Status").html(data.itemScrutiny.finalScrutinyStatus); 
		$("#FS_T_Comment").html(data.itemScrutiny.finalScrutinyComment); 
	}else{
		$("#downloadTechicalScrutiny").removeAttr('href',url);
		$("#downloadTechicalScrutiny").html(''); 
		$("#FS_T_Status").html(''); 
		$("#FS_T_Comment").html(''); 
	}
}

function getFinalCommercialScrutiny(){
	var bidderId=$("#bidderDetailForm #bidderId").val();
	var resp=fetchList("getPartnerFinalCommercialScrutinyData/"+bidderId,null);
	var data=resp.objectMap.commercialScrutiny;
	var adata=resp.objectMap.commercialScrutiny==null?null:resp.objectMap.commercialScrutiny.attachment;
	var url=$("#downloadCommercialScrutiny").data('url');
	if(data!=null){
		var file=adata.fileName==null?'':adata.fileName;
		var priceBid=adata.attachmentId==null?null:adata.attachmentId;
		$("#downloadCommercialScrutiny").attr('href',url);
		$("#downloadCommercialScrutiny").prop('href', url+'/'+priceBid);
		$("#downloadCommercialScrutiny").html(file);
		$("#FS_C_Status").html(data.itemScrutiny.finalScrutinyStatus); 
		$("#FS_C_Comment").html(data.itemScrutiny.finalScrutinyComment); 
		$("#FS_T_A_Status").html(data.itemScrutiny.finalAuditorStatus); 
		$("#FS_C_A_Comment").html(data.itemScrutiny.finalAuditorComment); 
	}else{
		$("#downloadCommercialScrutiny").removeAttr('href',url);
		$("#downloadCommercialScrutiny").html(''); 
		$("#FS_C_Status").html(''); 
		$("#FS_C_Comment").html(''); 
		$("#FS_T_A_Status").html(''); 
		$("#FS_C_A_Comment").html(''); 
	}
}

function getBids_FS_GTP(){
	var tahdrMaterialId=$("#itemDetailForm #tahdrMaterialId").val();
	var bidderId=$("#bidderDetailForm #bidderId").val();
	if(tahdrMaterialId!=null && bidderId!=null){
		submitToURL("getFinalBidderGtpByTahdrMaterialIdAndBidderId/"+tahdrMaterialId+"/"+bidderId,"getBids_FS_GTPResp");
	}else{
		alert("#itemDetailForm #tahdrMaterialId OR #bidderDetailForm #bidderId is NULL");
	}
}

function getBids_FS_GTPResp(resp){
	$("#FS_GTPTable").DataTable().destroy();
	$("#FS_GTPTable tbody").empty();
	$.each(resp,function(idx,data){
		var gtp =  getValue(data.bidderGtp.tahdrMaterialgtp.gtp.name);
		var gtpType =  data.bidderGtp.tahdrMaterialgtp.gtp.gtpParameterType.name==null?'':data.bidderGtp.tahdrMaterialgtp.gtp.gtpParameterType.name;
		var gtpTypeCode =  getValue(data.bidderGtp.tahdrMaterialgtp.gtp.gtpParameterType.code);
		var deviationTypeCode=data.isDeviation=='Y'?'YES': 'NO';
		var deviationType=data.deviationType==null?'NA': data.deviationType;
		var comment=data.deviationComment==null?'': data.deviationComment;
		var response=gtpTypeCode=='FILE'?'<a href="download/'+data.bidderGtp.fileResponse.attachmentId+'">'+data.bidderGtp.fileResponse.fileName+'</a>':getValue(data.bidderGtp.textResponse);
		
		var	tr="<tr>" +
		"<td class='col-sm-3'> "+ gtp +"</td>" +
		"<td class='col-sm-3'> "+ gtpType +"</td>" +
		"<td class='col-sm-3'> "+ response +"</td>"+
		"<td class='col-sm-3'> "+ deviationTypeCode +"</td>" +
		"<td class='col-sm-3'> "+ deviationType +"</td>" +
		"<td class='col-sm-3'> "+ comment +"</td>";
		tr+="</tr>";
		$("#FS_GTPTable tbody").append(tr);
	});
	checkKeyPressEvents();
	$('#FS_GTPTable').DataTable({
		"lengthMenu":lengthMenu,
		"bSort":false
	});
	mobiletable();
}

function getBids_FS_TSDocs(){
	var tahdrMaterialId=$("#itemDetailForm #tahdrMaterialId").val();
	var bidderId=$("#bidderDetailForm #bidderId").val();
	if(tahdrMaterialId!=null && bidderId!=null){
		submitToURL("getFinalTechnicalDocumentsByTahdrMaterialIdAndBidderId/"+tahdrMaterialId+"/"+bidderId,"getBids_FS_TSDocsResp");
	}else{
		alert("#itemDetailForm #tahdrMaterialId OR #bidderDetailForm #bidderId is NULL");
	}
}

function getBids_FS_TSDocsResp(resp){
	var documentList=resp.objectMap.documentList;
	$("#FS_TSDocTable").DataTable().destroy();
	$("#FS_TSDocTable tbody").empty();
	$.each(documentList,function(idx,data){
		 var bidsection=data.bidderSectionDoc.bidSection==null?'': data.bidderSectionDoc.bidSection;
		 var sectionDocName=data.bidderSectionDoc.sectionDocument==null?'': data.bidderSectionDoc.sectionDocument.name;
		 var attachmentId=data.bidderSectionDoc.attachment==null?'': data.bidderSectionDoc.attachment.attachmentId;
		 var attachmentName=data.bidderSectionDoc.attachment==null?'': data.bidderSectionDoc.attachment.fileName;
		 var deviationTypeCode=data.isDeviation=='Y'?'YES': 'NO';
		 var deviationType=data.deviationType==null?'NA': data.deviationType;
		 var comment=data.deviationComment==null?'': data.deviationComment;
		
		var	tr="<tr>" +
		"<td class='col-sm-3'> "+ sectionDocName +"</td>" +
		"<td class='col-sm-3'> <a href=download/"+attachmentId+">"+attachmentName+"</a></td>" +
		"<td class='col-sm-3'> "+ deviationTypeCode +"</td>" +
		"<td class='col-sm-3'> "+ deviationType +"</td>" +
		"<td class='col-sm-3'> "+ comment +"</td>" ;
		tr+="</tr>";
		$("#FS_TSDocTable tbody").append(tr);
	});
	checkKeyPressEvents();
	$('#FS_TSDocTable').DataTable({
		"lengthMenu":lengthMenu,
		"bSort":false
	});
	mobiletable();
}

function getBids_FS_SP(){
	var bidderId=$("#bidderDetailForm #bidderId").val();
	if(bidderId!=null){
		submitToURL("getFinalScrutinyPointByBidderId/"+bidderId,"getBids_FS_SPResp");
	}else{
		alert("#itemDetailForm #tahdrMaterialId OR #bidderDetailForm #bidderId is NULL");
	}
}

function getBids_FS_SPResp(resp){
	var scrutinyPointList=resp.objectMap.scrutinyPointList;
	$("#FS_SPTable").DataTable().destroy();
	$("#FS_SPTable tbody").empty();
	$.each(scrutinyPointList,function(idx,data){
		var scrutinypointName=data.scrutinyPoint.name==null?'': data.scrutinyPoint.name;
		var deviationTypeCode=data.isDeviation=='Y'?'YES': 'NO';
		var deviationType=data.deviationType==null?'NA': data.deviationType;
		var comment=data.deviationComment==null?'': data.deviationComment;
		var auditorPrevStatus=data.auditorStatus==null?'':data.auditorStatus;
		var auditorPrevComment=data.auditorComment==null?'No Comment':data.auditorComment;
		
		var	tr="<tr>" +
		"<td class='col-sm-3'> "+ scrutinypointName +"</td>" +
		"<td class='col-sm-3'> "+ deviationTypeCode +"</td>" +
		"<td class='col-sm-3'> "+ deviationType +"</td>" +
		"<td class='col-sm-3'> "+ comment +"</td>" +
		"<td class='col-sm-3'> "+ auditorPrevStatus +"</td>" +
		"<td class='col-sm-3'> "+ auditorPrevComment +"</td>" ;
		tr+="</tr>";
		$("#FS_SPTable tbody").append(tr);
	});
	checkKeyPressEvents();
	$('#FS_SPTable').DataTable({
		"lengthMenu":lengthMenu,
		"bSort":false
	});
	mobiletable();
}

function getBids_FS_CSDocs(){
	var bidderId=$("#bidderDetailForm #bidderId").val();
	if(bidderId!=null){
		submitToURL("getFinalCommercialDocumentsBybidderId/"+bidderId,"getBids_FS_CSDocsResp");
	}else{
		alert("#itemDetailForm #tahdrMaterialId OR #bidderDetailForm #bidderId is NULL");
	}
}

function getBids_FS_CSDocsResp(resp){
	var documentList=resp.objectMap.documentList;
	$("#FS_CSDocTable").DataTable().destroy();
	$("#FS_CSDocTable tbody").empty();
	$.each(documentList,function(idx,data){
		 var sectionDocName=data.bidderSectionDoc.sectionDocument==null?'': data.bidderSectionDoc.sectionDocument.name;
		 var attachmentId=data.bidderSectionDoc.attachment==null?'': data.bidderSectionDoc.attachment.attachmentId;
		 var attachmentName=data.bidderSectionDoc.attachment==null?'': data.bidderSectionDoc.attachment.fileName;
		 var deviationTypeCode=data.isDeviation=='Y'?'YES': 'NO';
		 var deviationType=data.deviationType==null?'NA': data.deviationType;
		 var comment=data.deviationComment==null?'': data.deviationComment;
		
		var	tr="<tr>" +
		"<td class='col-sm-3'> "+ sectionDocName +"</td>" +
		"<td class='col-sm-3'> <a href=download/"+attachmentId+">"+attachmentName+"</a></td>" +
		"<td class='col-sm-3'> "+ deviationTypeCode +"</td>" +
		"<td class='col-sm-3'> "+ deviationType +"</td>" +
		"<td class='col-sm-3'> "+ comment +"</td>" ;
		tr+="</tr>";
		$("#FS_CSDocTable tbody").append(tr);
	});
	checkKeyPressEvents();
	$('#FS_CSDocTable').DataTable({
		"lengthMenu":lengthMenu,
		"bSort":false
	});
	mobiletable();
}
