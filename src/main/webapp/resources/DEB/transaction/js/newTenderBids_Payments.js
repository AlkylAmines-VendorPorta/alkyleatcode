$(document).ready(function(){
	$("#checkByDate").on("click",function(){
		isDateSearchChecked();
	});
	
	$("#checkByStatus").on("click",function(){
		isStatusSearchChecked();
	});
});

function isDateSearchChecked(){
	var status1=$('#checkByDate').is(':checked');
	if(!status1){
		$('.dateCheck').children().attr('disabled','disabled');
	}else{
		$('.dateCheck').children().removeAttr('disabled');
	}
	var status2=$('#checkByStatus').is(':checked');
	if(!status1 && !status2){
		$('#PD_SearchBtnId').hide();
	}else{
		$('#PD_SearchBtnId').show();
	}	
}

function isStatusSearchChecked(){
	var status1=$('#checkByStatus').is(':checked');
	if(!status1){
		$('.statusCheck').attr('disabled','disabled');
	}else{
		$('.statusCheck').removeAttr('disabled');
	}
	var status2=$('#checkByDate').is(':checked');
	if(!status1 && !status2){
		$('#PD_SearchBtnId').hide();
	}else{
		$('#PD_SearchBtnId').show();
	}
}

function loadPaymentData(){
	submitWithParam('getPaymentDetailsListByTahdrId','tenderDetailsForm #tahdrId','loadPaymentDataResp');
	$('#fromDateAndTime').val('');
	$('#toDateAndTime').val('');
}

function loadPaymentDataResp(data){
	$("#bidderPaymentTblId").DataTable().destroy();
	$('#bidderPaymentTblId tbody').empty();
	if(data.objectMap.result){
		var data = data.objectMap.paymentDetailList;
		$.each(data, function(key, value) {
			var bidderData=value;
			var partnername=bidderData.partner==null?'':bidderData.partner.name;
			var partnerId=bidderData.partner==null?'':bidderData.partner.bPartnerId;
			var paymentType=bidderData.paymentType==null?'':bidderData.paymentType.name;
			var amount=bidderData.total==null?'':bidderData.total;
			var mode=bidderData.paymentMode==null?'':bidderData.paymentMode;
			var time=bidderData.created==null?'':formatDateTime(bidderData.created);
			var optime=bidderData.foApprovedDate==null?'':formatDateTime(bidderData.foApprovedDate);
			var adtime=bidderData.faApprovedDate==null?'':formatDateTime(bidderData.faApprovedDate);
			var financeOp=bidderData.foApprovedBy==null?'':bidderData.foApprovedBy.name;
			var opStatus=bidderData.isFOApproved==null?'':bidderData.isFOApproved=='Y'?'Approved':'Rejected';
			var opRemark=bidderData.foComment==null?'':bidderData.foComment;
			var financeAd=bidderData.faApprovedBy==null?'':bidderData.faApprovedBy.name;
			var adStatus=bidderData.isFAApproved==null?'':bidderData.isFAApproved=='Y'?'Approved':'Rejected';
			var adRemark=bidderData.faComment==null?'':bidderData.faComment;
	        $('#bidderPaymentTblId tbody').append('<tr><td>'+partnername+'</td>' + 
						'<td>'+paymentType+'</td>' + 
						'<td>'+amount+'</td>' +
						'<td>'+mode+'</td>' + 
						'<td>'+time+'</td>' + 
						'<td>'+financeOp+'</td>' + 
						'<td>'+opStatus+'</td>' +
						'<td>'+opRemark+'</td>' +
						'<td>'+optime+'</td>' +
						'<td>'+financeAd+'</td>' + 
						'<td>'+adStatus+'</td>' +
						'<td>'+adRemark+'</td>' +
						'<td>'+adtime+'</td>' +
						'</tr>');
		});
	}else{
		Alert.warn(data.objectMap.resultMessage);
	}
	$('#bidderPaymentTblId').DataTable({
		"lengthMenu":lengthMenu,
		"scrollX": true,
		"bSort":false
		
	});
	resetPaymentSearchDetails();
}

function downloadPaymentDetailsPdf(ele){
	event.preventDefault();
	var docType=$(ele).data('type');
	var tenderCode= $('#tenderDetailsForm #tenderNo').html();
	$("#bidderPaymentTblId").tableHTMLExport({type:docType,filename:'paymentDetail_'+tenderCode+'.'+docType+''});
	Alert.info('Pdf Dowloaded!');
}

function searchPaymentDetails(ele){
	debugger;
	event.preventDefault();
	var fromDate=$('#fromDateAndTime').val()==""?0:new Date ($('#fromDateAndTime').val()).getTime();
	var	toDate=$('#toDateAndTime').val()==""?0:new Date ($('#toDateAndTime').val()).getTime();
	var	appStatus=$("input[name='approvalStatus']:checked").val()==""?"X":$("input[name='approvalStatus']:checked").val();
	var tahdrId=$('#tenderDetailsForm #tahdrId').val();
	var dateValidation=(fromDate==0 && toDate==0)?true:fromDate<toDate;
	if(dateValidation){
			submitToURL('getPaymentDetails/'+tahdrId+'/'+fromDate+'/'+toDate+'/'+appStatus,'loadPaymentDataResp');
	}else{
		Alert.info('Provided date is not proper');
	}
}

function resetPaymentSearchDetails(){
	/*$('#fromDateAndTime').val('');
	$('#toDateAndTime').val('');*/
	/*$('#PD_ResetBtnId').hide();
	$('#PD_SearchBtnId').show();
	$('#PD_SearchBtnId').data('ifsearched',false);*/
}

function searchAllPaymentDetails(){
	loadPaymentData();
}