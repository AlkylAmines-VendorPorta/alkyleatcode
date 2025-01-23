
function uploadTechnicalScrutinyPdf(event){
	var bidderId=$('#bidderDetailForm #bidderId').val();
	var itemBidId=$("#itemDetailForm #itemBidId").val();
	event.preventDefault();
	if(bidderId!='' & itemBidId!=''){
		showLoader();
		submitIt("gennerateTechnicalScrutinyDoc", "getPreliminaryScrutinyReport","uploadPreliminaryScrutinyPdfResp");
		/*directSubmit(event,"gennerateTechnicalScrutinyDoc","generateTechnicalScrutinyReport/"+bidderId+"/"+itemBidId);*/
		hideLoader();
	}else{
		Alert.warn('Something went wrong');
	}
}


function uploadCommercialScrutinyPdf(event){
	var bidderId=$('#bidderDetailForm #bidderId').val();
	event.preventDefault();
	if(bidderId!=''){
		showLoader();
		submitIt("gennerateCommercialScrutinyDoc", "getPreliminaryScrutinyReport","uploadPreliminaryScrutinyPdfResp");
		/*directSubmit(event,"gennerateCommercialScrutinyDoc","generateCommercialScrutinyReport/"+bidderId);*/
		hideLoader();
	}else{
		Alert.warn('Something went wrong');
	}
}

function uploadPreliminaryScrutinyPdfResp(data){
	if(!isEmpty(data) && !isEmpty(data.objectMap)){
		$('#commercialScrutinyUploadForm').removeClass('readonly');
		$('#technicalScrutinyUploadForm').removeClass('readonly');
		var href=$('#a_techFileResponse').data('url');
		var value=data.objectMap.newScrutinyFile==null?'':data.objectMap.newScrutinyFile;
		if(value!=''){
	    	var attachmentId=value.attachment==null?0:value.attachment.attachmentId;
	    	var attachmentName=value.attachment==null?'':value.attachment.fileName;
			if(value.scrutinyType=='TECHSCR'){
	        	$('#gennerateTechnicalScrutinyDoc #scrutinyFileId').val(value.scrutinyFileId);
	        	$('#gennerateTechnicalScrutinyDoc #techFileResponseId').val(attachmentId);
	        	$('#a_techFileResponse').html(attachmentName);
	        	$('#a_techFileResponse').attr('href',href+'/'+attachmentId);
	    	}else{
	        	$('#gennerateCommercialScrutinyDoc #scrutinyFileId').val(value.scrutinyFileId);
	        	$('#gennerateCommercialScrutinyDoc #commFileResponseId').val(attachmentId);
	        	$('#a_commFileResponse').html(attachmentName);
	        	$('#a_commFileResponse').attr('href',href+'/'+attachmentId);
	    	}
		}
		if(data.objectMap.result){
			Alert.info(data.objectMap.message);
		}else{
			Alert.warn(data.objectMap.message);
		}
	}
	else{
		removeScrutinyFileData();
	}
}

function setScrutinyFileData(scrutinyType){
	if(scrutinyType=='TECHSCR'){
		var bidderId=$('#bidderDetailForm #bidderId').val();
		var itemBidId=$('#itemDetailForm #itemBidId').val();
		submitToURL('getTechnicalScrutiny?bidderId='+bidderId+'&itemBidId='+itemBidId,'setScrutinyFileDataResp');
	}else if(scrutinyType=='COMMSCR'){
		var bidderId=$('#bidderDetailForm #bidderId').val();
		submitToURL('getCommercialScrutiny?bidderId='+bidderId,'setScrutinyFileDataResp');
	}else{
		Alert.warn('Scrutiny File not set properly');
	}
}

function setScrutinyFileDataResp(data){
    	var href=$('#a_techFileResponse').data('url');
    	var value=data.objectMap.data==null?'':data.objectMap.data;
    	if(value!=''){
    		var scrutinyFileId=value.scrutinyFileId;
        	var attachmentId=value.attachment==null?0:value.attachment.attachmentId;
        	var attachmentName=value.attachment==null?'':value.attachment.fileName;
    		if(value.scrutinyType=='TECHSCR'){
            	$('#gennerateTechnicalScrutinyDoc #scrutinyFileId').val(scrutinyFileId);
            	$('#gennerateTechnicalScrutinyDoc #techFileResponseId').val(attachmentId);
            	$('#a_techFileResponse').html(attachmentName);
            	$('#a_techFileResponse').attr('href',href+'/'+attachmentId);
        	}else{
            	$('#gennerateCommercialScrutinyDoc #scrutinyFileId').val(scrutinyFileId);
            	$('#gennerateCommercialScrutinyDoc #commFileResponseId').val(attachmentId);
            	$('#a_commFileResponse').html(attachmentName);
            	$('#a_commFileResponse').attr('href',href+'/'+attachmentId);
        	}
    	}else{
    		removeScrutinyFileData();
    	}
}


function resetScrutinyFileData(){
	$('#technicalScrutinyUploadForm').addClass('readonly');
	$('#commercialScrutinyUploadForm').addClass('readonly');
}

function removeScrutinyFileData(){
	$('#technicalScrutinyUploadForm #scrutinyFileId').val('');
	$('#technicalScrutinyUploadForm #a_techFileResponse').html('');
	$('#technicalScrutinyUploadForm #a_techFileResponse').removeAttr('href');
	$('#commercialScrutinyUploadForm #scrutinyFileId').val('');
	$('#commercialScrutinyUploadForm #a_commFileResponse').html('');
	$('#commercialScrutinyUploadForm #a_commFileResponse').removeAttr('href');
	$('#commercialScrutinyUploadForm #deleteCommercialAttachmentId').attr('disabled','disabled');
	$('#technicalScrutinyUploadForm #deleteTechnicalAttachmentId').attr('disabled','disabled');
	$('#a_techFileResponse').removeAttr('href');
	$('#a_commFileResponse').removeAttr('href');
}

function  techScrutinyDeleteResp(data){
	if (!data.hasError) {
		$('#techFileResponseUploadId').val('');
		$('#techFileResponseId').val('');
		$("#a_techFileResponse").html('');
		$('.techFileResponseId').attr('disabled', true);
		Alert.info(data.message);
	} else {
		Alert.warn(data.message);
	}
}

function commScrutinyDeleteResp(data){
	if (!data.hasError) {
		$('#commFileResponseUploadId').val('');
		$('#commFileResponseId').val('');
		$("#a_commFileResponse").html('');
		$('.commFileResponseId').attr('disabled', true);
		Alert.info(data.message);
	} else {
		Alert.warn(data.message);
	}
}

function saveTechnicalScrutinyFileResp(data){
	if (data.objectMap.result) {
	$('#technicalScrutinyUploadForm #scrutinyFileId').val(data.objectMap.newScrutinyFile.scrutinyFileId);
		Alert.info(data.objectMap.message);
	} else {
		Alert.warn(data.objectMap.message);
	}
}

function saveCommercialScrutinyFileResp(data){
	if (data.objectMap.result) {
	$('#commercialScrutinyUploadForm #scrutinyFileId').val(data.objectMap.newScrutinyFile.scrutinyFileId);
		Alert.info(data.objectMap.message);
	} else {
		Alert.warn(data.objectMap.message);
	}
}