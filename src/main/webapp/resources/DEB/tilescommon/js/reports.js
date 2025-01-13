$(document).ready(function(){
	if ($(window).width() < 480) {
        $('.mobileNav').show();
        $.fn.DataTable.ext.pager.numbers_length = 4;       
        lengthMenu = [ 1, 5, 7, 10, ],
        [ 1, 5, 7, 10, ]
    }else{        
        lengthMenu = [ 5, 10, ],
        [ 5, 10, ]
    }
	loadPaymentReportData();
	
	$("#expandInfo").on("click", function(event) {
		event.preventDefault();
	});
	
});
var detailsArray=new Array();

function loadPaymentReportData(){
	submitToURL('getPaymentDetails/'+0+'/'+0+'/'+0+'/'+0+'/'+0,'loadPaymentDataResp');
}

function loadPaymentReportDataByFiscalYear(){
	var fiscalYear=$('#fiscalYear').val();
	if(fiscalYear!=0){
		$('#paymentTypeId').val(0);
		$('#tahdrId').val(0);
	 submitToURL('getPaymentDetails/'+0+'/'+fiscalYear+'/'+0+'/'+0+'/'+0,'loadPaymentDataResp');
	}
}

function loadPaymentReportDataByTahdrId(){
	var tahdrId=$('#tahdrId').val();
	if(tahdrId!=0){
		$('#fiscalYear').val(0);
		$('#paymentTypeId').val(0);
		submitToURL('getPaymentDetails/'+tahdrId+'/'+0+'/'+0+'/'+0+'/'+0,'loadPaymentDataResp');
	}
}
function loadPaymentReportDataByPaymentType(){
	var paymentTypeId=$('#paymentTypeId').val();
	if(paymentTypeId!=0){
		$('#fiscalYear').val(0);
		$('#tahdrId').val(0);
		submitToURL('getPaymentDetails/'+0+'/'+0+'/'+paymentTypeId+'/'+0+'/'+0,'loadPaymentDataResp');
	}
}

function searchPaymentDetails(ele){
	event.preventDefault();
	var fromDate=$('#fromDateAndTime').val()==""?0:new Date ($('#fromDateAndTime').val()).getTime();
	var	toDate=$('#toDateAndTime').val()==""?0:new Date ($('#toDateAndTime').val()).getTime();
	/*var	appStatus=$("input[name='approvalStatus']:checked").val()==""?"X":$("input[name='approvalStatus']:checked").val();*/
	var dateValidation=(fromDate==0 && toDate==0)?true:fromDate<toDate;
	if(dateValidation){
			submitToURL('getPaymentDetails/'+0+'/'+0+'/'+0+'/'+fromDate+'/'+toDate,'loadPaymentDataResp');
	}else{
		Alert.info('Provided date is not proper');
	}
}

function loadPaymentDataResp(data){
	$("#bidderPaymentTblId").DataTable().destroy();
	$('#bidderPaymentTblId tbody').empty();
	if(data.objectMap.result){
		var i=0;
		var data = data.objectMap.paymentDetailList;
		$.each(data, function(key, value) {
			var bidderData=value;
			detailsArray['details_'+i]=value;
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
			var tahdrCode=bidderData.tahdr==null?'':bidderData.tahdr.tahdrCode;
	        $('#bidderPaymentTblId tbody').append('<tr><td onclick="showToolTip('+i+')"><span class="glyphicon glyphicon-plus-sign"></span></td>'+
	        		'<td>'+tahdrCode+'</td>'+
	        		'<td>'+partnername+'</td>' + 
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
	        i++;
		});
	}else{
		Alert.warn(data.objectMap.resultMessage);
	}
	$('#bidderPaymentTblId').DataTable({
		"lengthMenu":lengthMenu,
		"scrollX": true,
		"bSort":false
		
	});
}

function showToolTip(count){
	debugger;
	var bidderData=detailsArray['details_'+count];
	var partnername=bidderData.partner==null?'':bidderData.partner.name;
	var paymentType=bidderData.paymentType==null?'':bidderData.paymentType.name;
	var amount=bidderData.total==null?'':bidderData.total;
	var mode=bidderData.paymentMode==null?'':bidderData.paymentMode;
	var time=bidderData.created==null?'':formatDateTime(bidderData.created);
	var optime=bidderData.foApprovedDate==null?'':formatDateTime(bidderData.foApprovedDate);
	var adtime=bidderData.faApprovedDate==null?'':formatDateTime(bidderData.faApprovedDate);
	var financeOp=bidderData.foApprovedBy==null?'':bidderData.foApprovedBy.name;
	var opStatus=bidderData.isFOApproved==null?'':bidderData.isFOApproved=='Y'?'Approved':'Rejected';
	var html= '<div id="tooltiptext_'+count+'"><h3>'+partnername+'</h3><ul>'+
        '<li>'+paymentType+'</li><li>'+amount+'</li><li>'+mode+'</li><li>'+time+'</li><li>'+optime+'</li>'+
        '<li>'+adtime+'</li><li></li><li></li><li></li><li></li>'+
    '</ul><i></i></div>';
	$('#tooltipText').html(html);
	tooltip.pop(this, '#tip', {sticky:true, position:0, cssClass:'no-padding'});
}
