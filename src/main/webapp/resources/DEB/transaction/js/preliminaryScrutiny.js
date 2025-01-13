function addAuditor(event){
	event.preventDefault();
		swal({
			  title: documentType+' will be locked for auditing <br> Do you still wish to Continue ?',
			  type: 'warning',
			  showCancelButton: true,
			  confirmButtonColor: '#3085d6',
			  cancelButtonColor: '#d33',
			  confirmButtonText: 'Yes'
			})
			.then(function(result){
			  if (result.value) {
				  submitIt('addAuditorForm','addAuditor','addAuditorResp');
			  }
			});
}

function addAuditorResp(data){
	if(data.objectMap.resultStatus){
		$('.preliminaryScrutinyPage').addClass('readonly');
		Alert.info(data.objectMap.resultMessage+" <br> "+documentType+" IS UNDER AUDITING");
		$('#auditingStatus').html(documentType+' IS UNDER AUDITING');
	}else{
		Alert.warn(data.objectMap.resultMessage);
	}
}

function intimidateScrutinyEngr(event){
	event.preventDefault();
		swal({
			  title: documentType+' will be sent back to Preliminary Scrutiny <br> Do you still wish to Continue ?',
			  type: 'warning',
			  showCancelButton: true,
			  confirmButtonColor: '#3085d6',
			  cancelButtonColor: '#d33',
			  confirmButtonText: 'Yes'
			})
			.then(function(result){
			  if (result.value) {
				  submitIt('confirmAuditingForm','intimidateAuditing','confirmAuditingResp');
			  }
			});
}

function intimidateAuditor(event){
	var tahdrName=$('#addAuditorForm #tahdrName').val();
	var auditorId=$('#addAuditorForm #auditorDropDownId').val();
	var tahdrId=$('#addAuditorForm #tahdrId').val();
	event.preventDefault();
	if(tahdrName!='' && auditorId!='' && tahdrId!=''){
		swal({
			  title: documentType+' will be locked for Auditing <br> Do you still wish to Continue ?',
			  type: 'warning',
			  showCancelButton: true,
			  confirmButtonColor: '#3085d6',
			  cancelButtonColor: '#d33',
			  confirmButtonText: 'Yes'
			})
			.then(function(result){
			  if (result.value) {
				  submitToURL("intimidateAuditor?tahdrId="+tahdrId+"&tahdrName="+tahdrName+"&auditorId="+auditorId, "intimidateAuditorResp");
			  }
			});
		
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
		tenderArray["tender_"+tahdrDetailId].tahdr.isAuditing='Y';
		$('.preliminaryScrutinyPage').addClass('readonly');
		Alert.info(data.objectMap.resultMessage+" <br> "+documentType+" IS UNDER AUDITING");
		$('#auditingStatus').html(documentType+' IS UNDER AUDITING');
	}else{
		Alert.warn(data.objectMap.resultMessage);
	}
}