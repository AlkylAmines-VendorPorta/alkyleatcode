
function uploadFinalTechnicalScrutinyPdf (event){
	event.preventDefault();
	var bidderId=$('#bidderForm #bidderId').val();
	var itemBidId=$("#itemDetailForm #itemBidId").val();
	if(bidderId!='' && itemBidId!=''){
		showLoader();
		submitIt("gennerateFinalTechnicalScrutinyDoc", "getFinalScrutinyReport","uploadFinalScrutinyPdfResp");
		/*directSubmit(event,"gennerateFinalTechnicalScrutinyDoc","generateFinalTechnicalScrutinyReport/"+bidderId+"/"+itemBidId);*/
		hideLoader();
	}
	
}
function uploadFinalCommercialScrutinyPdf(event){
	event.preventDefault();
	var bidderId=$('#bidderForm #bidderId').val();
	if(bidderId!=''){
		showLoader();
		submitIt("gennerateFinalCommercialScrutinyDoc", "getFinalScrutinyReport","uploadFinalScrutinyPdfResp");
		/*directSubmit(event,"gennerateFinalCommercialScrutinyDoc","generateFinalCommercialScrutinyReport/"+bidderId);*/
		hideLoader();
	}
}

function uploadFinalScrutinyPdfResp(data){
	if(!isEmpty(data) && !isEmpty(data.objectMap)){
		$('#commercialScrutinyUploadForm').removeClass('readonly');
		$('#technicalScrutinyUploadForm').removeClass('readonly');
		var href=$('#a_finalTechFileResponse').data('url');
		var value=data.objectMap.newScrutinyFile==null?'':data.objectMap.newScrutinyFile;
		if(value!=''){
	    	var attachmentId=value.attachment==null?0:value.attachment.attachmentId;
	    	var attachmentName=value.attachment==null?'':value.attachment.fileName;
			if(value.scrutinyType=='TECHSCR'){
	        	$('#gennerateFinalTechnicalScrutinyDoc #scrutinyFileId').val(value.scrutinyFileId);
	        	$('#gennerateFinalTechnicalScrutinyDoc #techFileResponseId').val(attachmentId);
	        	$('#a_finalTechFileResponse').html(attachmentName);
	        	$('#a_finalTechFileResponse').attr('href',href+'/'+attachmentId);
	    	}else{
	        	$('#gennerateFinalCommercialScrutinyDoc #scrutinyFileId').val(value.scrutinyFileId);
	        	$('#gennerateFinalCommercialScrutinyDoc #commFileResponseId').val(attachmentId);
	        	$('#a_finalCommFileResponse').html(attachmentName);
	        	$('#a_finalCommFileResponse').attr('href',href+'/'+attachmentId);
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
		var bidderId=$('#bidderForm #bidderId').val();
		var itemBidId=$('#itemDetailForm #itemBidId').val();
		submitToURL('getFinalTechnicalScrutiny?bidderId='+bidderId+'&itemBidId='+itemBidId,'setScrutinyFileDataResp');
	}else if(scrutinyType=='COMMSCR'){
		var bidderId=$('#bidderForm #bidderId').val();
		submitToURL('getFinalCommercialScrutiny?bidderId='+bidderId,'setScrutinyFileDataResp');
	}else{
		Alert.warn('Scrutiny File not set properly');
	}
}

function setScrutinyFileDataResp(data){
    	$('#commercialScrutinyUploadForm').removeClass('readonly');
    	$('#technicalScrutinyUploadForm').removeClass('readonly');
    	var href=$('#technicalScrutinyUploadForm #a_techFileResponse').data('url');
    	var value=data.objectMap.data==null?'':data.objectMap.data;
    	if(value!=''){
    		var scrutinyFileId=value.scrutinyFileId;
        	var attachmentId=value.attachment==null?0:value.attachment.attachmentId;
        	var attachmentName=value.attachment==null?'':value.attachment.fileName;
    		if(value.scrutinyType=='TECHSCR'){
            	$('#gennerateFinalTechnicalScrutinyDoc #scrutinyFileId').val(scrutinyFileId);
            	$('#gennerateFinalTechnicalScrutinyDoc #finalTechFileResponseId').val(attachmentId);
            	$('#a_finalTechFileResponse').html(attachmentName);
            	$('#a_finalTechFileResponse').attr('href',href+'/'+attachmentId);
        	}else{
            	$('#gennerateFinalCommercialScrutinyDoc #scrutinyFileId').val(scrutinyFileId);
            	$('#gennerateFinalCommercialScrutinyDoc #finalCommFileResponseId').val(attachmentId);
            	$('#a_finalCommFileResponse').html(attachmentName);
            	$('#a_finalCommFileResponse').attr('href',href+'/'+attachmentId);
        	}
    	}else{
    		removeScrutinyFileData()
    	}
}

function resetScrutinyFileData(){
	$('#technicalScrutinyUploadForm').addClass('readonly');
	$('#commercialScrutinyUploadForm').addClass('readonly');
}

function removeScrutinyFileData(){
	$('#gennerateFinalTechnicalScrutinyDoc #scrutinyFileId').val('');
	$('#a_finalTechFileResponse').html('');
	$('#a_finalTechFileResponse').removeAttr('href');
	$('#gennerateFinalCommercialScrutinyDoc #scrutinyFileId').val('');
	$('#a_finalCommFileResponse').html('');
	$('#a_finalCommFileResponse').removeAttr('href');
}

function  techScrutinyDeleteResp(data){
	if (!data.hasError) {
		$('#finalTechFileResponseUploadId').val('');
		$('#finalTechFileResponseId').val('');
		$("#a_finalTechFileResponse").html('');
		$('.finalTechFileResponseId').attr('disabled', true);
		Alert.info(data.message);
	} else {
		Alert.warn(data.message);
	}
}

function commScrutinyDeleteResp(data){
	if (!data.hasError) {
		$('#finalCommFileResponseUploadId').val('');
		$('#finalCommFileResponseId').val('');
		$("#a_finalCommFileResponse").html('');
		$('.finalCommFileResponseId').attr('disabled', true);
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