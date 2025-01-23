function intimidateAuditor(event){
	var tahdrName=$('#addAuditorForm #tahdrName').val();
	var auditorId=$('#addAuditorForm #auditorDropDownId').val();
	var tahdrId=$('#addAuditorForm #tahdrId').val();
	event.preventDefault();
	if(tahdrName!='' && auditorId!='' && tahdrId!=''){
		submitToURL("intimidateAuditor?tahdrId="+tahdrId+"&tahdrName="+tahdrName+"&auditorId="+auditorId, "intimidateAuditorResp");
	}else{
		Alert.warn('Something went wrong');
	}
}

function confirmAuditingTab(el){
	var documentType=$(".documentType").val();
	setHeaderValues(documentType+" Code:" +tenderCode , documentType+" Version : "+tenderVersion, "Description: "+tenderDescription, "EMD Fees : "+tenderEmdFee);
}

function confirmAuditingResp(data){
	if(data.objectMap.resultStatus){
		var tahdrDetailId=$('#tahdrDetailForm #tahdrDetailId').val();
		tenderArray["tender_"+tahdrDetailId].isAuditing='N';
		Alert.info(data.objectMap.resultMessage);
	}else{
		Alert.warn(data.objectMap.resultMessage);
	}
}

function intimidateAuditorResp(data){
	if(data.objectMap.resultStatus){
		var tahdrDetailId=$('#tahdrDetailForm #tahdrDetailId').val();
		tenderArray["tender_"+tahdrDetailId].isAuditing='Y';
		Alert.info(data.objectMap.resultMessage);
	}else{
		Alert.warn(data.objectMap.resultMessage);
	}
}