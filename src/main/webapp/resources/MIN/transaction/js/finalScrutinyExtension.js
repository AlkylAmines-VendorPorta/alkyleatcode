function intimidateAuditor(event){
	var tahdrId=$('#confirmationFinalScrutinyForm #tahdrId').val();
	event.preventDefault();
	submitToURL("notifyAuditor?tahdrId="+tahdrId, "intimidateAuditorResp");
}

function confirmFinalAuditingTab(el){
	$(".leftPaneData").html('');
	var documentType=$(".documentType").val();
	setHeaderValues(documentType+" Code:" +tenderCode , documentType+" Contact : "+tenderContact, "Description: "+tenderDescription, "Department : "+tenderDepartment);
}

function confirmAuditingResp(data){
	if(data.objectMap.resultStatus){
		Alert.info(data.objectMap.resultMessage);
	}else{
		Alert.warn(data.objectMap.resultMessage);
	}
}

function intimidateAuditorResp(data){
	if(data.objectMap.resultStatus){
		Alert.info(data.objectMap.resultMessage);
	}else{
		Alert.warn(data.objectMap.resultMessage);
	}
}