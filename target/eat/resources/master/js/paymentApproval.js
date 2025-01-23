var paymentDetailArray = new Array();
var roleDto;
var leftPanePageSize = 7;
var maxVisiblePageNumbers = 3;
var selectedPage = 1;
var paymentStatus='';
$(document).ready(function() {
	renderList();
	/*submitToURL("getPayments", 'getPagePaymentResp');*/
	/*$('#leftPane').paginathing({perPage: 13});*/
	$(".submitPaymentDetail").on("click", function(event) {
				debugger;
				event.preventDefault();
				if(roleDto=='FINOPR')
				{
						var approveFo=$('#approveFOBtnId').is(':checked');
						var rejectFo=$('#rejectFOBtnId').is(':checked');
						$("#approveFOBtnId").attr('name','isFOApproved');		
						$("#rejectFOBtnId").attr('name','isFOApproved');
						$("#realisationStatusY").attr('name','realisationStatus');		
						$("#realisationStatusN").attr('name','realisationStatus');
						
						if(rejectFo==true){
							$("#foComment").addClass("requiredField");
						}
						else{
							$("#foComment").removeClass("requiredField");
						}
						
						if(approveFo==false && rejectFo==false){
							Alert.warn("Select Status");
						}
						else{
							submitIt("paymentFormId", "updatePaymentStatus", "appendTableData");
							return false;
						}
				
				}
				else if(roleDto=='FINADM')
				{
						var approveFa=$('#approveFABtnId').is(':checked');
						var rejectFa=$('#rejectFABtnId').is(':checked');
						$("#approveFABtnId").attr('name','isFAApproved');		
						$("#rejectFABtnId").attr('name','isFAApproved');
						$("#realisationStatusY").attr('name','realisationStatus');		
						$("#realisationStatusN").attr('name','realisationStatus');	
						
						if(rejectFa==true){
							$("#faComment").addClass("requiredField");
						}
						else{
							$("#faComment").removeClass("requiredField");
						}
						
						if(approveFa==false && rejectFa==false){
							Alert.warn("Select Status");
						}
						else{
							submitIt("paymentFormId", "updatePaymentStatus", "appendTableData");
							return false;
						}
				}
				
			});
			
	//cancel
			$('.cancelPaymentDetail').click(function(event) {
				debugger;
				event.preventDefault();
				var activeId = $('.leftPaneData').find('li.active').attr('id');
				if (activeId != undefined) {
					loadPaymentDetailToRightPane(paymentDetailArray["payment"+ activeId]);
						} else
							$('#tahdrForm')[0].reset();
					});
             
			$('#pagination-here').paginate({
				pageSize:  7,
				loadOnStartup : false,
				dataSource: 'fetchPaymentApprovalList',
				responseTo:  'loadPaymentDetailToLeftPane',
				maxVisiblePageNumbers:3,
				searchBoxID : 'searchLiteralId'
			});
			
		});

function appendTableData(data){
	debugger;
	if(!$.isEmptyObject(data)){
		if(!data.hasError)
		{
	     Alert.info(data.message);
	    /* submitToURL("getPayments", 'getPagePaymentResp');*/
	     var data = fetchPaymentApprovalList(1, leftPanePageSize, 'none', 'none');
	 	getPagePaymentResp(data);
	 	setPagination(data.objectMap.LastPage, selectedPage , maxVisiblePageNumbers);	
		}
		else{
			 Alert.warn(data.message);
		}
	}
}

function renderList(){ 
	debugger;
	var data = fetchPaymentApprovalList(1, leftPanePageSize, 'none', 'none');
	getPagePaymentResp(data);
	setPagination(data.objectMap.LastPage, selectedPage , maxVisiblePageNumbers);	
	
}

function searchByStatus(status){
debugger;
if(status=='A'){
	paymentStatus ='Y';
	var data = fetchPaymentApprovalList(1, leftPanePageSize, 'none', 'none');
	getPagePaymentResp(data);
	setPagination(data.objectMap.LastPage, selectedPage , maxVisiblePageNumbers);
}
else if(status=='R'){
	paymentStatus ='N';
	var data = fetchPaymentApprovalList(1, leftPanePageSize, 'none', 'none');
	getPagePaymentResp(data);
	setPagination(data.objectMap.LastPage, selectedPage , maxVisiblePageNumbers);
}
else if(status=='P'){
	paymentStatus ='';
	var data = fetchPaymentApprovalList(1, leftPanePageSize, 'none', 'none');
	getPagePaymentResp(data);
	setPagination(data.objectMap.LastPage, selectedPage , maxVisiblePageNumbers);
}
else{
	paymentStatus ='';
}
}

function getPagePaymentResp(data){
	debugger;
	$(".disableByStatus").removeAttr('disabled','disabled');
	if(data.objectMap.hasOwnProperty('role')){
		roleDto=data.objectMap.role;
		if(roleDto=='FINOPR')
		{
		$("#isFOApproved").removeAttr("name","isFOApproved");
		$(".AdminComment").hide();
		$("#approveFOBtnId").attr('name','isFOApproved');		
		$("#rejectFOBtnId").attr('name','isFOApproved');
		$("#realisationStatusY").attr('name','realisationStatus');		
		$("#realisationStatusN").attr('name','realisationStatus');		
		}
		else if(roleDto=='FINADM')
		{
		$("#isFOApproved").attr("name","isFOApproved");
		$(".AdminComment").show();
		$("#foComment").addClass('readonly');
		$(".FoStatus").addClass('readonly');
		$("#approveFABtnId").attr('name','isFAApproved');		
		$("#rejectFABtnId").attr('name','isFAApproved');
		$("#realisationStatusY").attr('name','realisationStatus');		
		$("#realisationStatusN").attr('name','realisationStatus');	
		}
	}
	if(!$.isEmptyObject(data.data)){
		loadPaymentDetailToLeftPane(data.data);
	}
	setActiveTabName("Payment Approval",$('.leftPaneData li').length);

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
    if(roleDto=='FINOPR')
	{
    	if(isFOApproved=='Y'){
    		approvalStatus='Approved';
    	}
    	else if(isFOApproved=='N'){
    		approvalStatus='Rejected';
    	}
    	else{
    		approvalStatus='Pending';
    	}
    	
	}
    else if(roleDto=='FINADM'){
    	if(isFAApproved=='Y'){
    		approvalStatus='Approved';
    	}
    	else if(isFAApproved=='N'){
    		approvalStatus='Rejected';
    	}
    	else{
    		approvalStatus='Pending';
    	}
    }
	return appendLiData(partnerName,paymentType,paymentFor,approvalStatus,paymentDetailId, active, 'payment');
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
	setLeftPaneHeader("Payment List", Object.values(paymentDetailArray).length);
	loadPaymentDetailToRightPane(firstRow);

	$("#leftPane").on('click', '.payment', function() {
		var id = $(this).attr('id');
		loadPaymentDetailToRightPane(paymentDetailArray["payment" + id]);
	});
	/*$('#leftPane').paginathing({perPage: 13});*/
	$(".tabs-left li a label").text(function(index, currentText) {
	    return currentText.substr(0, 20);
	});
}

function loadPaymentDetailToRightPane(payment){
	debugger;
	if(!$.isEmptyObject(payment)){
		
		var paymentFor="";
		var partnerId=payment.partner==null?'':payment.partner.bPartnerId==null?'':payment.partner.bPartnerId;
		var paymentDetailId=payment.paymentDetailId==null?'':payment.paymentDetailId;
		var partnerName=payment.partner==null?'':payment.partner.name==null?'':payment.partner.name;
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
        $("#vendorTypePayment").val(paymentFor);
		}
		else if(payment.tahdr!=null){
			var vendorTypePayment=payment.tahdr==null?'':payment.tahdr.tahdrCode==null?'':payment.tahdr.tahdrCode;
			if(payment.tahdrDetail!=null){
				var version=payment.tahdrDetail==null?'':payment.tahdrDetail.version==null?'':payment.tahdrDetail.version;
			}
        	  paymentFor=vendorTypePayment+"/version-"+version;
             $("#vendorTypePayment").val(paymentFor);
		}
		var paymentType=payment.paymentType.name==null?'':payment.paymentType.name;
		var paymentMode=payment.paymentMode==null?'':payment.paymentMode;
		var amount=payment.amount==null?'':payment.amount;
		var gstin=payment.partner==null?'':payment.partner.gstinNo==null?'':payment.partner.gstinNo;
        var gstAmount=payment.gstAmount==null?'':payment.gstAmount;
        var total=payment.total==null?'':payment.total;
        var tahdr=payment.tahdr==null?'':payment.tahdr.tahdrCode==null?'':payment.tahdr.tahdrCode;
        var ddDate=payment.paymentDate==null?'':formatDate(payment.paymentDate);
        var ddNumber=payment.referenceNo==null?'':payment.referenceNo;
        var MICR=payment.micrCode==null?'':payment.micrCode;
        var bankName=payment.bankName==null?'':payment.bankName;
        var branchName=payment.branchName==null?'':payment.branchName;
        var isFOApproved=payment.isFOApproved==null?'':payment.isFOApproved;
        var isFAApproved=payment.isFAApproved==null?'':payment.isFAApproved;
        var foComment=payment.foComment==null?'':payment.foComment;
        var faComment=payment.faComment==null?'':payment.faComment;
        var realisationStatus=payment.realisationStatus==null?'':payment.realisationStatus;
        var realisationDate=payment.realisationDate==null?'':formatDate(payment.realisationDate);
        var moneyReceiptDate=payment.moneyReceiptDate==null?'':formatDate(payment.moneyReceiptDate);
        var moneyReceiptNo=payment.moneyReceiptNumber==null?'':payment.moneyReceiptNumber;
        
       
        var isTrader=payment.partner==null?'':payment.partner.isTrader==null?'':payment.partner.isTrader;
        /* var isManufacturer=value.partner==null?'':value.partner.isManufacturer==null?'':value.partner.isManufacturer;*/
        
		$("#paymentdetailId").val(paymentDetailId);
		$("#isFOApproved").val(isFOApproved);
		$("#isFAApproved").val(isFAApproved);
		if(isFOApproved=='Y'){
		$("#approveFOBtnId").prop("checked",isFOApproved);
		}
		else if(isFOApproved=='N'){
		$("#rejectFOBtnId").prop("checked",isFOApproved);
		}
	    else if(isFOApproved==''){
		$("#approveFOBtnId").prop("checked",true);
		$("#rejectFOBtnId").prop("checked",false);
		}
		
		if(isFAApproved=='Y'){
			$("#approveFABtnId").prop("checked",isFAApproved);
		}else if(isFAApproved=='N')
			{
			  $("#rejectFABtnId").prop("checked",isFAApproved);
			}else if(isFAApproved==''){
				$("#approveFABtnId").prop("checked",true);
				$("#rejectFABtnId").prop("checked",false);
			}
		$("#partnerId").val(partnerId);
		$("#partnerName").val(partnerName);
		$("#paymentType").val(paymentType);
		if(paymentMode=='DD'){
			$('#paymentMode').prop("checked",'Y');
		}
		
		$("#amount").val(amount);
		$("#gstin").val(gstin);
		$("#gstSAC").val(gstAmount);
		$("#total").val(total);
		$("#ddDate").val(ddDate);
		$("#referenceNo").val(ddNumber);
		$("#micrCode").val(MICR);
		$("#bankName").val(bankName);
		$("#branchName").val(branchName);
		$("#foComment").val(foComment);
		$("#faComment").val(faComment);
		$("#realisationDate").val(realisationDate);
		$("#moneyReceiptDate").val(moneyReceiptDate);
		$("#moneyReceiptNo").val(moneyReceiptNo);
		if(realisationStatus=='Y' || realisationStatus=='')
			{
			  $("#realisationStatusY").prop("checked",true);
			  $("#realisationStatusN").prop("checked",false);
			}else if(realisationStatus=='N')
				{
				  $("#realisationStatusY").prop("checked",false);
				  $("#realisationStatusN").prop("checked",true);
				}
		if(roleDto=='FINOPR')
		{
		$(".AdminComment").hide();
		if(isFOApproved==null || isFOApproved==""){
			$("#realisationStatusY").removeClass('readonly');
			$("#realisationStatusN").removeClass('readonly');
			$("#realisationDate").removeClass('readonly');
			$("#moneyReceiptDate").removeClass('readonly');
			$("#moneyReceiptNo").removeClass('readonly');
			$(".FOperatorStatus").removeClass('readonly');
			$("#foComment").removeClass('readonly');
			$(".submitPaymentDetail").show();
			$(".cancelPaymentDetail").show();
		}
		else{
			$("#realisationStatusY").addClass('readonly');
			$("#realisationStatusN").addClass('readonly');
			$("#realisationDate").addClass('readonly');
			$("#moneyReceiptDate").addClass('readonly');
			$("#moneyReceiptNo").addClass('readonly');
			$(".FOperatorStatus").addClass('readonly');
			$("#foComment").addClass('readonly');
			$(".submitPaymentDetail").hide();
			$(".cancelPaymentDetail").hide();
		}
		}
		else if(roleDto=='FINADM')
		{
		$("#foComment").val(foComment);
		$(".AdminComment").show();
		if(isFAApproved==null || isFAApproved==""){
			$("#realisationStatusY").removeClass('readonly');
			$("#realisationStatusN").removeClass('readonly');
			$("#realisationDate").removeClass('readonly');
			$("#moneyReceiptDate").removeClass('readonly');
			$("#moneyReceiptNo").removeClass('readonly');
			$(".FAdminStatus").removeClass('readonly');
			$("#faComment").removeClass('readonly');
			$(".submitPaymentDetail").show();
			$(".cancelPaymentDetail").show();
		}
		else{
			$("#realisationStatusY").addClass('readonly');
			$("#realisationStatusN").addClass('readonly');
			$("#realisationDate").addClass('readonly');
			$("#moneyReceiptDate").addClass('readonly');
			$("#moneyReceiptNo").addClass('readonly');
			$(".FAdminStatus").addClass('readonly');
			$("#faComment").addClass('readonly');
			$(".submitPaymentDetail").hide();
			$(".cancelPaymentDetail").hide();
		}
		}
	}
}
function loadPaymentsByStatusResp(data)
{
	$(".disableByStatus").attr('disabled','disabled');
	loadPaymentDetailToLeftPane(data.objectMap.payments);
}

function fetchPaymentApprovalList(pageNumber, pageSize, searchMode, searchValue){
	var paymentValue=paymentStatus;
	var response;
	$.ajax({
        type : "POST",
        contentType : "application/json",
        /*url: "getMaterialList/"+pageNumber+"/"+pageSize+'/'+searchMode+'/'+searchValue,*/
        url: "getPayments?pageNumber="+pageNumber+"&pageSize="+pageSize+"&searchMode=none&searchValue="+searchValue+"&paymentStatus="+paymentValue,
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