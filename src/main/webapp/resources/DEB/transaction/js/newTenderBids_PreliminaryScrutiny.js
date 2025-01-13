function getTechnicalScrutiny(){
	var tahdrMaterialId=$("#itemDetailForm #tahdrMaterialId").val();
	var bidderId=$("#bidderDetailForm #bidderId").val();
	var resp=fetchList("getPartnerPreTechnicalScrutinyData/"+bidderId+"/"+tahdrMaterialId,null);
	var data=resp.objectMap.technicalScrutiny;
	var adata=resp.objectMap.technicalScrutiny==null?null:resp.objectMap.technicalScrutiny.attachment;
	var url=$("#downloadTechicalScrutiny").data('url');
	if(data!=null){
		var file=adata.fileName==null?'':adata.fileName;
		var techBid=adata.attachmentId==null?null:adata.attachmentId;
		$("#downloadTechicalScrutiny").attr('href',url);
		$("#downloadTechicalScrutiny").prop('href', url+'/'+techBid);
		$("#downloadTechicalScrutiny").html(file);
		$("#PS_T_Status").html(data.itemScrutiny.preliminaryScrutinyStatus); 
		$("#PS_T_Comment").html(data.itemScrutiny.preliminaryScrutinyComment); 
	}else{
		$("#downloadTechicalScrutiny").removeAttr('href',url);
		$("#downloadTechicalScrutiny").html(''); 
		$("#PS_T_Status").html(''); 
		$("#PS_T_Comment").html(''); 
	}
}

function getCommercialScrutiny(){
	var bidderId=$("#bidderDetailForm #bidderId").val();
	var resp=fetchList("getPartnerPreCommercialScrutinyData/"+bidderId,null);
	var data=resp.objectMap.commercialScrutiny;
	var adata=resp.objectMap.commercialScrutiny==null?null:resp.objectMap.commercialScrutiny.attachment;
	var url=$("#downloadCommercialScrutiny").data('url');
	if(data!=null){
		var file=adata.fileName==null?'':adata.fileName;
		var priceBid=adata.attachmentId==null?null:adata.attachmentId;
		$("#downloadCommercialScrutiny").attr('href',url);
		$("#downloadCommercialScrutiny").prop('href', url+'/'+priceBid);
		$("#downloadCommercialScrutiny").html(file);
		$("#PS_C_Status").html(data.itemScrutiny.preliminaryScrutinyStatus); 
		$("#PS_C_Comment").html(data.itemScrutiny.preliminaryScrutinyComment); 
		$("#PS_T_A_Status").html(data.itemScrutiny.preliminaryAuditorStatus); 
		$("#PS_C_A_Comment").html(data.itemScrutiny.preliminaryAuditorComment); 
	}else{
		$("#downloadCommercialScrutiny").removeAttr('href',url);
		$("#downloadCommercialScrutiny").html(''); 
		$("#PS_C_Status").html(''); 
		$("#PS_C_Comment").html(''); 
		$("#PS_T_A_Status").html(''); 
		$("#PS_C_A_Comment").html(''); 
	}
}

function getBids_PS_GTP(){
	var tahdrMaterialId=$("#itemDetailForm #tahdrMaterialId").val();
	var bidderId=$("#bidderDetailForm #bidderId").val();
	if(tahdrMaterialId!=null && bidderId!=null){
		submitToURL("getBidderGtpByTahdrMaterialIdAndBidderId/"+tahdrMaterialId+"/"+bidderId,"getBids_PS_GTPResp");
	}else{
		alert("#itemDetailForm #tahdrMaterialId OR #bidderDetailForm #bidderId is NULL");
	}
}

function getBids_PS_GTPResp(resp){
	$("#PS_GTPTable").DataTable().destroy();
	$("#PS_GTPTable tbody").empty();
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
		$("#PS_GTPTable tbody").append(tr);
	});
	checkKeyPressEvents();
	$('#PS_GTPTable').DataTable({
		"lengthMenu":lengthMenu,
		"bSort":false
	});
	mobiletable();
}

function getBids_PS_TSDoc(){
	var tahdrMaterialId=$("#itemDetailForm #tahdrMaterialId").val();
	var bidderId=$("#bidderDetailForm #bidderId").val();
	if(tahdrMaterialId!=null && bidderId!=null){
		submitToURL("getTechnicalDocumentsByTahdrMaterialIdAndBidderId/"+tahdrMaterialId+"/"+bidderId,"getBids_PS_TSDocsResp");
	}else{
		alert("#itemDetailForm #tahdrMaterialId OR #bidderDetailForm #bidderId is NULL");
	}
}

function getBids_PS_TSDocsResp(resp){
	var documentList=resp.objectMap.documentList;
	$("#PS_TSDocTable").DataTable().destroy();
	$("#PS_TSDocTable tbody").empty();
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
		$("#PS_TSDocTable tbody").append(tr);
	});
	checkKeyPressEvents();
	$('#PS_TSDocTable').DataTable({
		"lengthMenu":lengthMenu,
		"bSort":false
	});
	mobiletable();
}

function getBids_PS_SP(){
	var bidderId=$("#bidderDetailForm #bidderId").val();
	if(bidderId!=null){
		submitToURL("getPreScrutinyPointByBidderId/"+bidderId,"getBids_PS_SPResp");
	}else{
		alert("#itemDetailForm #tahdrMaterialId OR #bidderDetailForm #bidderId is NULL");
	}
}

function getBids_PS_SPResp(resp){
	var scrutinyPointList=resp.objectMap.scrutinyPointList;
	$("#PS_SPTable").DataTable().destroy();
	$("#PS_SPTable tbody").empty();
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
		$("#PS_SPTable tbody").append(tr);
	});
	checkKeyPressEvents();
	$('#PS_SPTable').DataTable({
		"lengthMenu":lengthMenu,
		"bSort":false
	});
	mobiletable();
}

function getBids_PS_CSDocs(){
	var bidderId=$("#bidderDetailForm #bidderId").val();
	if(bidderId!=null){
		submitToURL("getPreCommercialDocumentsBybidderId/"+bidderId,"getBids_PS_CSDocsResp");
	}else{
		alert("#itemDetailForm #tahdrMaterialId OR #bidderDetailForm #bidderId is NULL");
	}
}

function getBids_PS_CSDocsResp(resp){
	var documentList=resp.objectMap.documentList;
	$("#PS_CSDocTable").DataTable().destroy();
	$("#PS_CSDocTable tbody").empty();
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
		$("#PS_CSDocTable tbody").append(tr);
	});
	checkKeyPressEvents();
	$('#PS_CSDocTable').DataTable({
		"lengthMenu":lengthMenu,
		"bSort":false
	});
	mobiletable();
}
