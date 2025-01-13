/**
 * 
 */
var leftPanePageSize = 7;
var maxVisiblePageNumbers = 3;
var paymentStatus='';
var showPushedData = 'N';
var paymentModeArray = new Array();


$(document).ready(function(){
	getPaymentModes();
	searchByStatus();
	
	$('#pagination-here').paginate({
		pageSize:  7,
		loadOnStartup : false,
		dataSource: 'fetchPaymentApprovalList',
		responseTo:  'loadPaymentDetailToLeftPane',
		maxVisiblePageNumbers:3,
		searchBoxID : 'searchLiteralId'
	});
	
	$("#leftPane").on('click', '.payment', function() {
		var id = $(this).attr('id');
		loadPaymentDetailToRightPane(paymentDetailArray["payment" + id]);
	});
	
	$("#pushedPayments").click(function(){
		showPushedData = 'Y';
		searchByStatus();
	});
	$("#unpushedPayments").click(function(){
		showPushedData = 'N';
		searchByStatus();
	});

	$("#postPaymentDetail").click(function(event){
		event.preventDefault();
		var paymentDetailId = $('#eq').val();
		var response = fetchList('postPaymentDetail', paymentDetailId);
		debugger;
		if(response.hasError){
			Alert.warn(response.message);
		}else{
			Alert.warn(response.message);
			$("#leftPane li.active").remove()
		}
	});
});
function searchByStatus(){
	debugger;
		var data = fetchPaymentApprovalList(1, leftPanePageSize, 'none', 'none');
		getPagePaymentResp(data);
		setPagination(data.objectMap.LastPage, 1 , maxVisiblePageNumbers);
	}

function getPagePaymentResp(data){
	
	//if(!$.isEmptyObject(data.data)){
		loadPaymentDetailToLeftPane(data.data);
		debugger;
//	}

}

function loadPaymentDetailToLeftPane(paymentList) {
	debugger;
		$("#leftPane").html("");
		var leftPanelHtml = '';
		var i = 0;
		var active = "";
		var firstRow = null;
		paymentDetailArray = [];
		$.each(Object.values(paymentList), function(key, payment) {
			paymentDetailArray["payment" + payment.paymentDetailId] = payment;
			if (i == 0) {
				firstRow = payment;
				active = "active";
			}
			leftPanelHtml = leftPanelHtml + appendPaymentLi(payment, active);
			active = "";
			i++;
		});
		$("#leftPane").append(leftPanelHtml);
		$('.leftPaneData >li >a').find('.leftPaneLowerDiv').removeClass('leftPaneLowerDiv');
		//setLeftPaneHeader("Payment List", Object.values(paymentDetailArray).length);
		loadPaymentDetailToRightPane(firstRow);

		
		/*$('#leftPane').paginathing({perPage: 13});*/
		$(".tabs-left li a label").text(function(index, currentText) {
		    return currentText.substr(0, 20);
		});
	}
function appendPaymentLi(payment, active) {
	var partnerName=payment.partner==null?'':payment.partner.name==null?'':payment.partner.name;
	var paymentType=payment.paymentType==null?'':payment.paymentType.name==null?'':payment.paymentType.name;
	/*var amount= payment.amount==null?'': payment.amount;*/
	/*var paymentMode=payment.paymentMode==null?'':payment.paymentMode;*/
	var paymentDetailId=payment.paymentDetailId==null?'':payment.paymentDetailId;
	var paymentFor="";
	var partnerOrgName=payment.partnerOrg==null?'':payment.partnerOrg.name==null?'':" - "+payment.partnerOrg.name;
	if(payment.vendorTypePayment!=null){
		var vendorTypePayment=payment.vendorTypePayment==null?'':payment.vendorTypePayment;	
		if(vendorTypePayment=="TP")
    	{
    	  paymentFor="Trader Payment";
    	}else if(vendorTypePayment=="MP")
    	{
    	  paymentFor="Factory"+partnerOrgName;
    	}
	}
	else if(payment.tahdr!=null){
		var vendorTypePayment=payment.tahdr==null?'':payment.tahdr.tahdrCode==null?'':payment.tahdr.tahdrCode;
		if(payment.tahdrDetail!=null){
			var version=payment.tahdrDetail==null?'':payment.tahdrDetail.version==null?'':payment.tahdrDetail.version;
		}
    	  paymentFor=vendorTypePayment+"/version-"+version;
	}
	var isFOApproved=payment.isFOApproved==null?'':payment.isFOApproved;
    var isFAApproved=payment.isFAApproved==null?'':payment.isFAApproved;
    var approvalStatus='';
   

 
	return appendLiData(partnerName,paymentType,paymentFor,paymentModeArray[payment.paymentMode].name,paymentDetailId, active, 'payment');
}

function loadPaymentDetailToRightPane(payment){
	debugger;
	var paymentDetailId=payment.paymentDetailId==null?'':payment.paymentDetailId;
	$("#eq").val(paymentDetailId);
	if (payment != undefined) {
		$('.docNO').val(payment.docNo == undefined?payment.referenceNo:payment.docNo );
		$('.vendorCode').val(payment.partner.vendorSapCode == undefined?'':payment.partner.vendorSapCode);
		if(payment.tahdr!=null){
			$('.tendorNo').val(payment.tahdr.tahdrCode==undefined?'':payment.tahdr.tahdrCode);
		}else{
			$('.tendorNo').val('');
		}
		if(payment.lvexport != undefined){
			$('.lvexport').val(payment.lvexport);
		}else{
			$('.lvexport').val('');
		}
		$('.paymentType').val(payment.paymentType.name == undefined? '' : payment.paymentType.name);
		$('.grossAmount').val(payment.total);
		$('.isPushed').val(payment.isPushed=='N'?'No':'Yes');
		$('.charges').val('');
	}
}
function getPaymentModes(){
	var paymentModeList = fetchList('getPaymentModes', null);
	for(var i = 0; i < paymentModeList.length; i++){
		paymentModeArray[paymentModeList[i].code] = paymentModeList[i]
	}
}


function fetchPaymentApprovalList(pageNumber, pageSize, searchMode, searchValue){
	
	var response;
	$.ajax({
        type : "POST",
        contentType : "application/json",
        /*url: "getMaterialList/"+pageNumber+"/"+pageSize+'/'+searchMode+'/'+searchValue,*/
        url: "getPaymentsForPosting?showPushedData="+showPushedData+"&pageNumber="+pageNumber+"&pageSize="+pageSize+"&searchMode=none&searchValue="+searchValue,
        dataType:"json",
        async:false,
        success: function (data) {
        	response = data;
        },
        error: function (e) {
			Alert.warn(e.message);
        }
    });
	return response;
}