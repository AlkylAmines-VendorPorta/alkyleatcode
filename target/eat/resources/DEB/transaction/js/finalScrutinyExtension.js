function intimidateAuditor(event){
	var tahdrId=$('#confirmationFinalScrutinyForm #tahdrId').val();
	event.preventDefault();
	if(tahdrId!=undefined && tahdrId!=''){
		swal({
			  title: documentType+' will be locked for final auditing <br> Do you still wish to Continue ?',
			  type: 'warning',
			  showCancelButton: true,
			  confirmButtonColor: '#3085d6',
			  cancelButtonColor: '#d33',
			  confirmButtonText: 'Yes'
			})
			.then(function(result){
			  if (result.value) {
				  submitToURL("notifyAuditor?tahdrId="+tahdrId, "intimidateAuditorResp");
			  }
			});
	}
}

function intimidateScrutinyEngr(event){
	event.preventDefault();
		swal({
			  title: documentType+' will be sent back to Final Scrutiny <br> Do you still wish to Continue ?',
			  type: 'warning',
			  showCancelButton: true,
			  confirmButtonColor: '#3085d6',
			  cancelButtonColor: '#d33',
			  confirmButtonText: 'Yes'
			})
			.then(function(result){
			  if (result.value) {
				  submitIt('confirmAuditingForm','intimidateFinalAuditing','confirmAuditingResp');
			  }
			});
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
		var tahdrDetailId=$("#tahdrDetailForm #tahdrDetailId").val();
		tenderDetailArray['tender_'+tahdrDetailId].tahdr.isAuditing='Y';
		$('.finalScrutinyPage').addClass('readonly');
		Alert.info(data.objectMap.resultMessage+" <br> "+documentType+" IS UNDER AUDITING");
		$('#auditingStatus').html(documentType+' IS UNDER FINAL AUDITING');
	}else{
		Alert.warn(data.objectMap.resultMessage);
	}
}