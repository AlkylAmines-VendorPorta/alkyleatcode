function changeFields(partnerData)
{
	debugger;
	if(partnerData == 'partnerProfiles'){
		$("#partnerDetailsApprovalForm #submitBtnId").attr("disabled","disabled");
		$("#partnerDetailsApprovalForm #partnerSubmitDivId").hide();
		$("#partnerDetailsApprovalForm #partnerApprovalDivId").show();
		$("#partnerDetailsApprovalForm #partnerSubmitRespDivId").hide();
		$("#partnerDetailsApprovalForm #partnerApprovalBtnId").removeAttr("disabled","disabled");
		$("#partnerDetailsApprovalForm #approveBtnId").removeAttr("disabled","disabled");
		$("#partnerDetailsApprovalForm #rejectBtnId").removeAttr("disabled","disabled");
		
	}else{
		$("#partnerDetailsApprovalForm #partnerSubmitDivId").show();
		$("#partnerDetailsApprovalForm #partnerSubmitRespDivId").hide();
		$("#partnerDetailsApprovalForm #submitBtnId").removeAttr("disabled","disabled");
		$("#partnerDetailsApprovalForm #partnerApprovalBtnId").attr("disabled","disabled");
		$("#partnerDetailsApprovalForm #approveBtnId").attr('disabled','disabled');
		$("#partnerDetailsApprovalForm #rejectBtnId").attr('disabled','disabled');
		$("#partnerDetailsApprovalForm #partnerApprovalDivId").hide();
		
		
	}
	
}

