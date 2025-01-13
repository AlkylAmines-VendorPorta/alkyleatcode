var paymentMode;
var paymentType;
var isVondor;
var isContractor;
var isManufacturer;
var isTrader;
var isCustomer;
var leftPanePageSize = 7;
var maxVisiblePageNumbers = 3;
var selectedPage = 1;
var urlName=null;
var paramName=null;
var onlinePaymentStatus='';
var tenderTypeCode='';
$(document).ready(function(){
	$("#acceptinvitation").hide();
  $('.onlinePaymentDocNoDivId').hide();
	renderList();
	$(".acceptinvitation").on("click",function(){
		debugger;
		event.preventDefault();
		$('#tahdrPurchaseDetailForm #isTrader_Flag').removeAttr('disabled');
		$('#tahdrPurchaseDetailForm #isTrader_Flag').val('Y');
		submitIt("tahdrPurchaseDetailForm","tahdrDocPurchase","populateTenderPurchaseResponse");
		$('#tahdrPurchaseDetailForm #isTrader_Flag').attr('disabled','disabled');
	});
	$(".tahdrPurchaseDetail").on("click",function(event){
		event.preventDefault();
		submitIt("tahdrPurchaseDetailForm","tahdrDocPurchase","populateTenderPurchaseResponse");
	});
	
	$("#payReceiptLinkId").on("click",function(event){
		var id=$("#tahdrId").val();
		var paymentType=$("#paymentType").find('option:selected').html();
		if(paymentType=='Tender/Auction Fee'){
			paymentType='TF'
		}
		var anchorHtml="invoice?id="+id+"&paymentType="+paymentType;
		$("#payReceiptLinkId").attr('href',anchorHtml);
	});
	
	$(".tabs-left li a label").text(function(index, currentText) {
		return currentText.substr(0, 20);
	});
	
	$("input[name='toggleTenderType']").change(function(){
		urlName='getTahdrListForPurchase';
		paramName=$('input[name=toggleTenderType]:checked' ).val();
		var responseData=fetchTahdrPurchaseList(1, leftPanePageSize, 'none', 'none');
		 $(".tabs-left li a label").text(function(index, currentText) {
				    return currentText.substr(0, 20);
				});
		 if(!isEmpty(responseData) && !isEmpty(responseData.objectMap)){
			 if(responseData.objectMap.status){
					if(responseData.data.length==0){
					    Alert.warn("You do not have Tender/Auction Present in this List");
					    $('#example').empty();
					    $(".paymentTab").addClass('readonly');
					    $("#purchaseTenderLinkId").addClass('readonly');
					    resetAllTab();
					}else{
						$('#purchaseActionBtnDivId').show();
				    	if($('input[name=toggleTenderType]:checked' ).val()=='PT' ){
				    		 $(".paymentTab").removeClass('readonly');
				    		 $("#purchaseTenderLinkId").removeClass('readonly');
				    		$("#isTraderDiv").css("display","block");
			    			$("#isTrader").css("display","block");
			    			
			    			$('#factorySeclectId').css('display','block');
							$('#factoryId').removeAttr('disabled');
			        		
							//paymentMode=responseData.objectMap.paymentMode;
					    	
							if(!$.isEmptyObject(responseData.objectMap.factoryList)){
			    				$('#factorySeclectId').css('display','block');
			    				$('#factoryId').removeAttr('disabled');
			        			loadFactoryList("factory",responseData.objectMap.factoryList);
				    		}
				    		
						}else{
							$("#isTraderDiv").css("display","none");
			    			$("#isTrader").css("display","none");
			    			
							
							$("#factorySeclectId").css("display","none");
							$('#factoryId').attr('disabled','disabled');
						}
				    	var tahdrType=responseData.objectMap==null?'':responseData.objectMap.tenderType;
				    	if(tahdrType=='PT' || tahdrType=='FA'){
				    	/*loadReferenceList("paymentMode",responseData.objectMap.paymentMode);*/
				    		loadPaymentModeList("paymentMode",responseData.objectMap.paymentMode);
				    	}
				    	else if(tahdrType=='WT'){
				    		loadPaymentModeList("paymentMode",responseData.objectMap.paymentMode);
				    	}
				    	populatePaymentType(responseData.objectMap.paymentType);
				    	populatePaymentDetail(responseData.objectMap.paymentDetail);
				    	appendList(responseData.data);
				    	downloadTahdrDetailDoc();
				    	
				    	setPagination(responseData.objectMap.LastPage, 1, maxVisiblePageNumbers);
				    	 $(".paymentTab").removeClass('readonly');
						 $("#purchaseTenderLinkId").removeClass('readonly');
					}
					 $('#purchase').show();
				}else{
					Alert.warn(responseData.objectMap.resultMessage);
					$('#viewDetail').hide();
					$('#viewTenderLinkId').hide();
					$('#payReceipt').hide();
					$('#payReceiptLinkId').hide();
					$('.paymentTab').addClass('readonly');
					$('#purchase').hide();
					$('#paymentStatusDiv').css('display','none');
				}
		 }
	});
	
	$(".paymentTab").click(function(event){
		event.preventDefault();
		$('#payOnlineTahrdFeeBtn').show();
	});
	
	$(".toggleTab").click(function(event){
		$('#payOnlineTahrdFeeBtn').show();
	});
	
	$(".tenderDet").click(function(event){
		$('#payOnlineTahrdFeeBtn').hide();
	});
	
	$("#payOnlineTahrdFeeBtn").click(function(event){
    	event.preventDefault();
    	if(!$("#isTrader").prop("checked")){
    		var validate=$("#factoryId").val() != '';
    		if(validate){
    			$("#factoryIdOP").removeAttr("disabled");
    			var factoryId=$('#factoryId').val();
    			$("#factoryIdOP").val(factoryId);
       		 	$("#onlineTenderFeeForm").submit();
       		 	
    		}else{
    			Alert.warn("Please select Factory");
    		}
    	}else{  
    		$("#factoryIdOP").attr("disabled","disabled");
			var isTrader=$("#isTrader").val();
			$("#isTraderOP").val(isTrader);
    		$("#onlineTenderFeeForm").submit();
    	}
    });
	
	$('#pagination-here').paginate({
		pageSize:  7,
		dataSource: 'fetchTahdrPurchaseList',
		responseTo:  'appendList',
		maxVisiblePageNumbers:3,
		searchBoxID : 'searchLiteralId',
		loadOnStartup : false
	});
	
});

function renderList(){ 
	debugger;
	urlName='getTahdrListForPurchase';
	paramName=$('input[name=toggleTenderType]:checked' ).val();
	var data = fetchTahdrPurchaseList(1, leftPanePageSize, 'none', 'none');
	if(!isEmpty(data) && !isEmpty(data.objectMap)){
		 purchaseList(data);
	     setPagination(data.objectMap.LastPage, 1, maxVisiblePageNumbers);
	}else{
		$('#purchaseActionBtnDivId').hide();
		resetAllTab();
	}        	
}


function purchaseList(data){
	if(data.objectMap.status){
		if($.isEmptyObject(data.data)){
		  Alert.warn("No Tender/Auction present in List");
		  $(".paymentTab").addClass('readonly');
		  $("#purchaseTenderLinkId").addClass('readonly');
		  viewToggleFromRole(data);
		  $('#viewDetail').hide();
			$('#viewTenderLinkId').hide();
			$('#payReceipt').hide();
			$('#payReceiptLinkId').hide();
			$('.paymentTab').addClass('readonly');
			$('#purchase').hide();
			$('#paymentStatusDiv').css('display','none');
		}else{
		 $(".paymentTab").removeClass('readonly');
		 $('#purchase').show();
		 $("#purchaseTenderLinkId").removeClass('readonly');
    	paymentMode=data.objectMap.paymentMode;
    	var tahdrType=data.objectMap.tenderType==null?'':data.objectMap.tenderType;
    	if(tahdrType=='PT' || tahdrType=='FA' || tahdrType=='RA'){
    		/*loadReferenceList("paymentMode",paymentMode);*/
    	loadPaymentModeList("paymentMode",paymentMode);
    	}
    	else if(tahdrType=='WT'){
    		loadPaymentModeList("paymentMode",paymentMode);
    	}
    	paymentType=data.objectMap.paymentType;
    	isContractor=data.objectMap.isContractor;
		isVendor=data.objectMap.isVendor;
		isManufacturer=data.objectMap.isManufacturer;
		isTrader=data.objectMap.isTrader;
		isCustomer=data.objectMap.isCustomer;
		tahdrList=data.data;
    	populatePaymentType(paymentType);
    	viewToggleFromRole(data);
    	
    	populatePaymentDetail(data.objectMap.paymentDetail);
    	appendList(data.data);
    	downloadTahdrDetailDoc();
    	
    	if(data.objectMap.isIntraState){
			$("#igst").attr("disabled","disabled");
			
			$("#igstDiv").css("display","none");
			
			$("#cgst").removeAttr("disabled");
			$("#sgst").removeAttr("disabled");
			
			$("#cgstDiv").css("display","block");
			$("#sgstDiv").css("display","block");
		}else{
			$("#igst").removeAttr("disabled");
			$("#igstDiv").css("display","block");
			
			
			$("#cgst").attr("disabled","disabled");
			$("#sgst").attr("disabled","disabled");
			
			$("#cgstDiv").css("display","none");
			$("#sgstDiv").css("display","none");
		}
	}  	
	
	}
	else{
		Alert.warn(data.objectMap.resultMessage);
		$('#viewDetail').hide();
		$('#viewTenderLinkId').hide();
		$('#payReceipt').hide();
		$('#payReceiptLinkId').hide();
		$('.paymentTab').addClass('readonly');
		$('#purchase').hide();
		$('#paymentStatusDiv').css('display','none');
	}

}

function viewToggleFromRole(data){
	debugger;
 if(!isEmpty(data) && !isEmpty(data.objectMap)){
	 if(data.objectMap.isContractor && data.objectMap.isVendor){
			$("#factorySeclectId").css("display","none");
			$('#factoryId').attr('disabled','disabled');
			
			$("#isTrader").css("display","none");
			$("#isTraderDiv").css("display","none");
			
			$('#labelWorks').addClass('active');
		}else if(data.objectMap.isContractor){
				$("#factorySeclectId").css("display","none");
				$('#factoryId').attr('disabled','disabled');
			
				$("#isTrader").css("display","none");
				$("#isTraderDiv").css("display","none");
				
				$('#labelWorks').addClass('active');
				$("#labelProcurement").addClass('readonly', 'readonly').css({backgroundColor:'#fcc'});
		}else if (data.objectMap.isVendor){
	    		if(data.objectMap.isManufacturer=='Y' && data.objectMap.isTrader=='Y'){
	    			$("#isTraderDiv").css("display","block");
	    			$("#isTrader").css("display","block");
	    			
	    			if(!$.isEmptyObject(data.objectMap.factoryList)){
	        			$('#factorySeclectId').css('display','block');
	    				$('#factoryId').removeAttr('disabled');
	        			loadFactoryList("factory",data.objectMap.factoryList);
	        		}
	    		}else if (data.objectMap.isManufacturer=='Y' ){
	    			$("#isTrader").css("display","none");
	    			$("#isTraderDiv").css("display","none");
	    			
	    			if(!$.isEmptyObject(data.objectMap.factoryList)){
	        			$('#factorySeclectId').css('display','block');
	    				$('#factoryId').removeAttr('disabled');
	        			loadFactoryList("factory",data.objectMap.factoryList);
	        		}
	    		}else if (data.objectMap.isTrader=='Y' ){
	    			$("#isTraderDiv").css("display","block");
	    			$("#isTrader").css("display","block");
	    			
	    			$('#factorySeclectId').css('display','none');
					$('#factoryId').removeAttr('disabled');
	    		}
				$('#labelProcurement').addClass('active');
				$("#labelWorks").addClass('readonly', 'readonly').css({backgroundColor:'#fcc'});
		}else{
			$("#labelWorks").addClass('active');
		} 
 }
}

function appendList(data){
	if(!isEmpty(data)){
		var active=" class='active'";
		$('#example').empty();
		$('.pagination').children().remove();
		for (var i=0;i<data.length;i++)  { 
			if(i==0){
				var manufacturer=data[i].tahdr==null?'':data[i].tahdr.isManufacturer==null?'':data[i].tahdr.isManufacturer;
				var trader =data[i].tahdr==null?'':data[i].tahdr.isTrader==null?'':data[i].tahdr.isTrader;
				if((manufacturer=='Y' && trader=='Y') && (isManufacturer=='Y' && isTrader=='Y')){
					$("#isTraderDiv").css("display","block");
	    			$("#isTrader").css("display","block");
	    			
	        			$('#factorySeclectId').css('display','block');
	    				$('#factoryId').removeAttr('disabled');
				}
				else if(manufacturer=='Y' && isManufacturer=='Y'){
					$('#factorySeclectId').css('display','block');
					$('#factoryId').removeAttr('disabled');
					
					$("#isTraderDiv").css("display","none");
	    			$("#isTrader").css("display","none");
				}
				else if(trader=='Y' && isTrader=='Y' ){
					$("#isTraderDiv").css("display","block");
	    			$("#isTrader").css("display","block");
	    			
	    			$('#factorySeclectId').css('display','none');
					$('#factoryId').attr('disabled','disabled');
				}
				else if(isContractor=='Y' || isCustomer=='Y'){
					$('#factorySeclectId').css('display','none');
					$('#factoryId').attr('disabled','disabled');
					
					$("#isTraderDiv").css("display","none");
	    			$("#isTrader").css("display","none");
				}
					$("#description").val(data[i].description);
					$("#estimatedCost").val(data[i].estimatedCost);
					$("#tenderFees").val(data[i].tahdrFees);
					$("#amount").val(data[i].tahdrFees);
          if($("#amount").val() == 0){
					$(".paymentTab").hide();
					$("#acceptinvitation").show();
					$("#paymentMode").removeClass('requiredField');
					$("#referenceNo").removeClass('requiredField');
					$("#purchaseTenderLinkId").hide();
				}else{
					$(".paymentTab").show();
					$("#acceptinvitation").hide();
				}
					$("#tenderName").val(data[i].tahdr.tahdrCode);
					$("#tenderTitle").val(data[i].tahdr.title);
					$("#tenderClosingDate").val(formatDate(data[i].purchaseToDate));
					$("#gstAmount").val((data[i].tahdrFees*$("#gst").val())/100);
					var totalFee=Number($("#amount").val())+Number($("#gstAmount").val());
					$("#totalFee").val(totalFee);
					$("#tahdrCode").val(data[i].tahdr.tahdrCode);
					$("#tahdrDetailId").val(data[i].tahdrDetailId);
					$("#tahdrId").val(data[i].tahdr.tahdrId);
					$("#optahdrDetailId").val(data[i].tahdrDetailId);
					$("#optahdrId").val(data[i].tahdr.tahdrId);
					
					tenderTypeCode=data[i].tahdr.tahdrTypeCode;
					setPaymentTypeByAmount(totalFee,tenderTypeCode);
				$('#masterDetails').empty();
				$('#masterDetails').append('<div class="row"><label class="col-xs-6" ><h4 class="details-Name">Tender Number</h4></label>'
			            +'<label class="col-xs-6 mytext detail_Code">'+data[i].tahdr.tahdrCode+'</label></div> '
			            +'<div class="row"><label class="col-xs-6">Tender Type Code</label>'
			            +'<label class="col-xs-6 mytext detail_typecode">'+data[i].tahdr.tahdrTypeCode+'</label></div>');
				}

			$('#example').append('<li '+active+' onclick="showdetails('+data[i].tahdr.tahdrId+')"> <a href="" class="" data-toggle="tab">'
			           +' <div class="col-md-12">'
			           +'  <label class="col-xs-6">'+data[i].tahdr.tahdrCode+'</label>'
			           +'   <label class="col-xs-6 mytext" data-id="'+data[i].tahdrDetailId+'">'+data[i].tahdr.tahdrTypeCode+'</label>'
			           +'  </div>'
			           +'  <div class="col-md-12">'
			           +'<label class="col-xs-6">'+data[i].estimatedCost+'</label>'
			           +'    <label class="col-xs-6">'+data[i].tahdrFees+'</label>'
			           +'  </div>'
			           +'  </a>'
			           +'  </li>');
			active="";
			setHeaderValues("Tender Name: "+data[i].tahdr.tahdrCode, "Tender Title : "+data[i].tahdr.title, "Estimated Cost : "+data[i].estimatedCost, "Tender fees :"+data[i].tahdrFees);
		}
		$(".tabs-left li a label").text(function(index, currentText) {
		    return currentText.substr(0, 20);
	  });
		$('.reportCount').html(data.length);
		
		$('#purchaseActionBtnDivId').show();
	}
}

function showdetails(id){
	$.ajax({
        type : "POST",
        contentType : "application/json",
        url: "getTenderDocPurchaseDetails/"+id,
        dataType:"json",
        async:false,
        success: function (data) {
        	 
        	if($.isEmptyObject(data)){
        		  Alert.warn("Tender details is empty");
        	}else{
        		var activeStatus='';
	        	var tahdrDetail=data.objectMap==null?null:data.objectMap.tahdrDetail;
				var paymentDetail=data.objectMap==null?null:data.objectMap.paymentDetail;
				var manufacturer=data.objectMap.tahdrDetail==null?'':data.objectMap.tahdrDetail.tahdr==null?'':data.objectMap.tahdrDetail.tahdr.isManufacturer==null?'':data.objectMap.tahdrDetail.tahdr.isManufacturer;
				var trader =data.objectMap.tahdrDetail==null?'':data.objectMap.tahdrDetail.tahdr==null?'':data.objectMap.tahdrDetail.tahdr.isTrader==null?'':data.objectMap.tahdrDetail.tahdr.isTrader;
				if((manufacturer=='Y' && trader=='Y') && (isManufacturer=='Y' && isTrader=='Y')){
					$("#isTraderDiv").css("display","block");
	    			$("#isTrader").css("display","block");
	    			
	        			$('#factorySeclectId').css('display','block');
	    				$('#factoryId').removeAttr('disabled');
				}
				else if(manufacturer=='Y' && isManufacturer=='Y'){
					$('#factorySeclectId').css('display','block');
					$('#factoryId').removeAttr('disabled');
					
					$("#isTraderDiv").css("display","none");
	    			$("#isTrader").css("display","none");
				}
				else if(trader=='Y' && isTrader=='Y' ){
					$("#isTraderDiv").css("display","block");
	    			$("#isTrader").css("display","block");
	    			
	    			$('#factorySeclectId').css('display','none');
					$('#factoryId').attr('disabled','disabled');
				}
				else if(isContractor=='Y' || isCustomer=='Y'){
					$('#factorySeclectId').css('display','none');
					$('#factoryId').attr('disabled','disabled');
					
					$("#isTraderDiv").css("display","none");
	    			$("#isTrader").css("display","none");
				}
				$("#description").val(tahdrDetail.description);
				$("#estimatedCost").val(tahdrDetail.estimatedCost);
				$("#tenderFees").val(tahdrDetail.tahdrFees);
				$("#amount").val(tahdrDetail.tahdrFees);
				$("#gstAmount").val((tahdrDetail.tahdrFees*$("#gst").val())/100);
				$("#totalFee").val(Number($("#amount").val())+Number($("#gstAmount").val()));
				if($("#tenderFees").val() == 0){
					$(".paymentTab").hide();
					$("#acceptinvitation").show();
					$("#purchaseTenderLinkId").hide();
					$("#paymentMode").val("CA")
					$("#referenceNo").val("00000");
				}else{
					$(".paymentTab").show();
					$("#purchaseTenderLinkId").show();
					$("#acceptinvitation").hide();
				}
				$("#tenderName").val(tahdrDetail.tahdr.tahdrCode);
				$("#tenderTitle").val(tahdrDetail.tahdr.title);
				$("#tenderClosingDate").val(formatDate(tahdrDetail.purchaseToDate));
				$("#amount").val(tahdrDetail.tahdrFees);
				$("#gstAmount").val((tahdrDetail.tahdrFees*$("#gst").val())/100);
				var totalFee=Number($("#amount").val())+Number($("#gstAmount").val());
				$("#totalFee").val(totalFee);
				$("#tahdrCode").val(tahdrDetail.tahdr.tahdrCode);
				$("#tahdrDetailId").val(tahdrDetail.tahdrDetailId);
				$("#tahdrId").val(tahdrDetail.tahdr.tahdrId);
				$("#optahdrDetailId").val(tahdrDetail.tahdrDetailId);
				$("#optahdrId").val(tahdrDetail.tahdr.tahdrId);
				
				tenderTypeCode=tahdrDetail.tahdr.tahdrTypeCode;
				populatePaymentDetail(paymentDetail);
				if(paymentDetail==null && tenderTypeCode=='WT'){
					setPaymentTypeByAmount(totalFee,tenderTypeCode);
				}
				downloadTahdrDetailDoc();
				setHeaderValues("Tender Name: "+tahdrDetail.tahdr.tahdrCode, "Tender Title : "+tahdrDetail.tahdr.title, "Estimated Cost : "+tahdrDetail.estimatedCost, "Tender fees :"+tahdrDetail.tahdrFees);
        	} 	
        },
        error: function (e) {
			Alert.warn("Exception :"+e);
        }
    });
}

function formFieldChange(el){
	 
	var paymentMode=$(el).val();
	if($.isEmptyObject(paymentMode)){
		$('#paymentDate').hide();
		$('#paymentNo').hide();
		$('#paymentRefNo').hide();
		$('#bankNamelbl').hide();
		$('#branchNamelbl').hide();
		$('#micrCode').hide();
		$('#bankName').hide();
		$('#branchName').hide();
		$('#paymentDateDiv').hide();
		$('#referenceNoDiv').hide();

		$('#dateOfPayment').attr('disabled','disabled');
		$('#referenceNo').attr('disabled','disabled');
		$('#micrCode').attr('disabled','disabled');
		$('#bankName').attr('disabled','disabled');
		$('#branchName').attr('disabled','disabled');
		
		$(".positionABS").hide();
		$("#onlineTenderFeeForm").hide();
	}else if(paymentMode=="DD"){
		$('#paymentDate').show();
		$('#paymentDateDiv').show();
		$('#paymentNo').show();
		$('#paymentRefNo').show();
		$('#referenceNoDiv').show();
		$('#bankNamelbl').show();
		$('#branchNamelbl').show();
		$('#micrCode').show();
		$('#bankName').show();
		$('#branchName').show();
		
		$("#micrCodeDivId").show();
		$("#bankNameDivId").show();
		$("#branchNameDivId").show();
		
		$('#dateOfPayment').removeAttr('disabled','disabled');
		$('#referenceNo').removeAttr('disabled','disabled');
		$('#micrCode').removeAttr('disabled','disabled');
		$('#bankName').removeAttr('disabled','disabled');
		$('#branchName').removeAttr('disabled','disabled');
		
		$('#paymentDate').text('DD Date');
		$('#paymentNo').text('Demand Draft No');
		$('#paymentRefNo').text('MICR NO');
		$('#bankNamelbl').text('Bank Name');
		$('#branchNamelbl').text('Branch Name');
		
		$('#paymentDate').text('DD Date');
		$('#paymentNo').text('Demand Draft No');
		$('#paymentRefNo').text('MICR NO');
		$('#bankNamelbl').text('Bank Name');
		$('#branchNamelbl').text('Branch Name');

		$('#paymentDate').append('<span class="red">*</span>');
		$('#paymentNo').append('<span class="red">*</span>');
		$('#paymentRefNo').append('<span class="red">*</span>');
		$('#bankNamelbl').append('<span class="red">*</span>');
		$('#branchNamelbl').append('<span class="red">*</span>');
		
		$(".positionABS").show();
		$("#onlineTenderFeeForm").hide();
	}else if(paymentMode=="CH"){
		$('#paymentDate').show();
		$('#paymentDateDiv').show();
		$('#paymentNo').show();
		$('#paymentRefNo').show();
		$('#referenceNoDiv').show();
		$('#bankNamelbl').show();
		$('#branchNamelbl').show();
		$('#micrCode').show();
		$('#bankName').show();
		$('#branchName').show();
		
		$("#micrCodeDivId").show();
		$("#bankNameDivId").show();
		$("#branchNameDivId").show();
		
		$('#dateOfPayment').removeAttr('disabled','disabled');
		$('#referenceNo').removeAttr('disabled','disabled');
		$('#micrCode').removeAttr('disabled','disabled');
		$('#bankName').removeAttr('disabled','disabled');
		$('#branchName').removeAttr('disabled','disabled');
		
		$('#paymentDate').text('Cheque Payment Date');
		$('#paymentNo').text('Cheque No');
		$('#paymentRefNo').text('MICR NO');
		$('#bankNamelbl').text('Bank Name');
		$('#branchNamelbl').text('Branch Name');

		$('#paymentDate').append('<span class="red">*</span>');
		$('#paymentNo').append('<span class="red">*</span>');
		$('#paymentRefNo').append('<span class="red">*</span>');
		$('#bankNamelbl').append('<span class="red">*</span>');
		$('#branchNamelbl').append('<span class="red">*</span>');
		
		$(".positionABS").show();
		$("#onlineTenderFeeForm").hide();
	}else if(paymentMode=="CA"){	
		$('#paymentDate').show();
		$('#paymentDateDiv').show();
		$('#paymentNo').show();
		$('#paymentRefNo').show();
		$('#referenceNoDiv').show();
		$('#bankNamelbl').show();
		$('#branchNamelbl').show();

		$('#micrCode').hide();
		$('#bankName').hide();
		$('#branchName').hide();
		
		$('#paymentDate').text('Payment Date');
		$('#paymentNo').text('Reciept No');
		$('#paymentRefNo').text('');
		$('#bankNamelbl').text('');
		$('#branchNamelbl').text('');
		
		$('#dateOfPayment').removeAttr('disabled','disabled');
		$('#referenceNo').removeAttr('disabled','disabled');
		$('#micrCode').attr('disabled','disabled');
		$('#bankName').attr('disabled','disabled');
		$('#branchName').attr('disabled','disabled');
		
		$('#paymentDate').append('<span class="red">*</span>');
		$('#paymentNo').append('<span class="red">*</span>');
		$('#paymentRefNo span').remove();
		$('#bankNamelbl span').remove();
		$('#branchNamelbl span').remove();
		
		$(".positionABS").show();
		$("#onlineTenderFeeForm").hide();
	}else if(paymentMode=="ISEXEMP"){
	   $("#paymentDateDiv").hide();
	   $("#referenceNoDiv").hide();
	   $("#micrCodeDivId").hide();
	   $("#bankNameDivId").hide();
	   $("#branchNameDivId").hide();
	   $('#payReceipt').hide();
	   $('#dateOfPayment').attr('disabled','disabled');
		$('#referenceNo').attr('disabled','disabled');
		$('#micrCode').attr('disabled','disabled');
		$('#bankName').attr('disabled','disabled');
		$('#branchName').attr('disabled','disabled');
		
	   $(".positionABS").show();
	   $("#onlineTenderFeeForm").hide();
	}else if(paymentMode=="OP"){
		$('#paymentDate').hide();
		$('#paymentDateDiv').hide();
		$('#paymentNo').show();
		$('#paymentRefNo').hide();
		$('#referenceNoDiv').hide();
		$('#bankNamelbl').hide();
		$('#branchNamelbl').hide();

		$('#micrCode').hide();
		$('#bankName').hide();
		$('#branchName').hide();
		
		$('#paymentDate').text('Payment Date');
		$('#paymentNo').text('Reciept No');
		$('#paymentRefNo').text('');
		$('#bankNamelbl').text('');
		$('#branchNamelbl').text('');
		
		$('#paymentDate').append('<span class="red">*</span>');
		$('#paymentNo').append('<span class="red">*</span>');
		$('#paymentRefNo span').remove();
		$('#bankNamelbl span').remove();
		$('#branchNamelbl span').remove();
		
		$(".positionABS").hide();
		if(onlinePaymentStatus!='Success'){
			$("#onlineTenderFeeForm").show();
		}
	}
}

function populatePaymentType(paymentType){
	 
	if(!isEmpty(paymentType)){
		var gst=paymentType.gst;
		$(".gstRate").text(gst);
		$("#gst").val(gst);
		$("#igst").val(Number(gst));
		$("#cgst").val(Number(gst/2));
		$("#sgst").val(Number(gst/2));
		
		$("#paymentType").empty();
		$("#paymentType").append('<option selected="selected" value="'+paymentType.paymentTypeId+'">'+paymentType.name+'</option>');
	}
}

function populateTenderPurchaseResponse(purchaseDetail){
	 debugger;
	if(!isEmpty(purchaseDetail) && !isEmpty(purchaseDetail.response)){
		if(purchaseDetail.response.hasError){
			if(!isEmpty(purchaseDetail.response.message)){
				Alert.warn(purchaseDetail.response.message);
			}else{
				var msg=getErrorMsgFromList(purchaseDetail.response.errors);
				Alert.warn(msg);
			}
		}else{
			Alert.info(purchaseDetail.response.message);
		}
		
		if(!$.isEmptyObject(purchaseDetail.tenderPurchase)){
			populatePaymentDetail(purchaseDetail.tenderPurchase);
		}	
	}
}
		
function populatePaymentDetail(paymentDetail){
	 debugger;
	onlinePaymentStatus='';
	if($.isEmptyObject(paymentDetail)){
		/*$('#payOnlineLink').show();*/
		$('#viewDetail').hide();
		$('#viewTenderLinkId').hide();
		$('#payReceipt').hide();
		$('#payReceiptLinkId').hide();
		$('.tahdrPurchaseDetail').show();
		$('#paymentStatusDiv').css('display','none');
		$('#paymentStatusLabel').html('');
		$('#purchaseBtn').html('Purchase '+$('#documentType').val());
		$('#cancelBtn').html("Cancel");
		
		/*$("#isTrader").prop("checked",false);
		isTraderChanged($("#isTrader"));*/
		$("#paymentDetailId").val('');
		$('#paymentMode').val('');
		$('#paymentMode').trigger('change');
		$('#dateOfPayment').val(formatDate(""));
		$('#referenceNo').val("");
		$('#micrCode').val("");
		$('#bankName').val("");
		$('#branchName').val("");
		$("#factoryId").val("");
		$("#isTrader").removeClass("readonly");
		$("#isTrader").removeAttr("disabled","disabled");
		$('#dateOfPayment').removeClass("readonly");
		$('#referenceNo').removeClass("readonly");
		$('#micrCode').removeClass("readonly");
		$('#bankName').removeClass("readonly");
		$('#branchName').removeClass("readonly");
		$('#paymentMode').removeClass('readonly');
	}else{
		var factoryId=paymentDetail.partnerOrg==null?'':paymentDetail.partnerOrg.partnerOrgId;
		var faApproved=paymentDetail.isFAApproved==null?'':paymentDetail.isFAApproved;
		var foApproved=paymentDetail.isFOApproved==null?'':paymentDetail.isFOApproved;
		var paymentDetailId=paymentDetail.paymentDetailId==null?'':paymentDetail.paymentDetailId;
		var paymentMode = paymentDetail.paymentMode==null?'':paymentDetail.paymentMode;
		var paymentGatewayStatus= paymentDetail.paymentGatewayStatus==null?'':paymentDetail.paymentGatewayStatus;
		onlinePaymentStatus=paymentGatewayStatus==''?'NA':paymentGatewayStatus;
		var paymentDocNo= paymentDetail.docNo==null?'':paymentDetail.docNo;
		var paymentDate= paymentDetail.created;
		$("#paymentDetailId").val(paymentDetailId);
		if(faApproved=='Y'){
			/*$('#payOnlineLink').hide();*/
			$('#viewDetail').show();
			$('#viewTenderLinkId').show();
			 /*$('#payReceipt').show();
			$('#payReceiptLinkId').show();*/
			/*downloadTahdrDetailDoc();*/
      /*downloadTahdrDetailDoc();*/
			$('.tahdrPurchaseDetail').hide();
			$('#purchaseBtn').html('Payment Detail');
			$('#cancelBtn').html("Back");
			$('#paymentStatusDiv').css('display','none');
			$('#paymentStatusLabel').html('');
			
			$("#amount").val(paymentDetail.amount);
			
			$("#gst").val(paymentDetail.gst);
			$("#gstAmount").val(paymentDetail.gstAmount);
			$("#igst").val(paymentDetail.igst);
			$("#cgst").val(paymentDetail.cgst);
			$("#sgst").val(paymentDetail.sgst);
			
			$("#totalFee").val(paymentDetail.total);
			
			$("#isTrader").prop("checked",getCheckBoxVal(paymentDetail.isTrader));
			isTraderChanged($("#isTrader"));
			$('#paymentMode').val(paymentDetail.paymentMode);
			$('#paymentMode').trigger('change');
			$('#dateOfPayment').val(formatDate(paymentDetail.paymentDate));
			$('#referenceNo').val(paymentDetail.referenceNo);
			$('#micrCode').val(paymentDetail.micrCode);
			$('#bankName').val(paymentDetail.bankName);
			$('#branchName').val(paymentDetail.branchName);
			$("#factoryId").val(factoryId);
			$("#factoryId").attr("disabled","disabled");
			$("#isTrader").attr("disabled","disabled");
			$('#paymentMode').addClass("readonly");
			$('#dateOfPayment').addClass("readonly");
			$('#referenceNo').addClass("readonly");
			$('#micrCode').addClass("readonly");
			$('#bankName').addClass("readonly");
			$('#branchName').addClass("readonly");
			if(paymentMode=='DD' || (paymentMode=='OP' && paymentGatewayStatus=='Success')){
				$('#paymentMode').addClass('readonly');
					$('#payReceipt').show();
					$('#payReceiptLinkId').show();
					if(paymentMode=='OP' && paymentGatewayStatus=='Success'){
						$('#paymentMode').val("OP");
						$("#onlineTenderFeeForm").hide();
					}
					if(paymentDocNo!=''){
						$('.onlinePaymentDocNoDivId').show();
						$('#onlinePaymentDocNo').val(paymentDocNo);
						$('#onlinePaymentDate').val(formatDate(paymentDate));
					}
					
				}else{
					$('#payReceipt').hide();
					$('#payReceiptLinkId').hide();
				}
		}
		else if(faApproved=='N' || foApproved=='N'){
			/*$('#payOnlineLink').show();*/
			$('#viewDetail').hide();
			$('#viewTenderLinkId').hide();
			$('#payReceipt').hide();
			$('#payReceiptLinkId').hide();
			$('.tahdrPurchaseDetail').show();
			$('#purchaseBtn').html('Purchase '+$('#documentType').val());
			$('#cancelBtn').html("Cancel");
			$('#paymentStatusDiv').css('display','block');
			$('#paymentStatusLabel').html('Your Payment is Rejected.');
			
			$('#paymentMode').val('');
			$('#paymentMode').trigger('change');
			$('#dateOfPayment').val(formatDate(""));
			$('#referenceNo').val("");
			$('#micrCode').val("");
			$('#bankName').val("");
			$('#branchName').val("");
			$("#factoryId").val("");
			$("#isTrader").removeClass("readonly");
			$("#isTrader").removeAttr("disabled","disabled");
			$('#paymentMode').removeClass("readonly");
			$('#dateOfPayment').removeClass("readonly");
			$('#referenceNo').removeClass("readonly");
			$('#micrCode').removeClass("readonly");
			$('#bankName').removeClass("readonly");
			$('#branchName').removeClass("readonly");
		}
		else{
			/*$('#payOnlineLink').hide();*/
			$('#viewDetail').hide();
			$('#viewTenderLinkId').hide();
			$('#payReceipt').hide();
			$('#payReceiptLinkId').hide();
			/*downloadTahdrDetailDoc();*/
			$('.tahdrPurchaseDetail').hide();
			$('#purchaseBtn').html('Payment is Under Approval');
			$('#cancelBtn').html("Back");
			$('#paymentStatusDiv').css('display','none');
			$('#paymentStatusLabel').html('');
			$("#amount").val(paymentDetail.amount);
			
			$("#gst").val(paymentDetail.gst);
			$("#gstAmount").val(paymentDetail.gstAmount);
			$("#igst").val(paymentDetail.igst);
			$("#cgst").val(paymentDetail.cgst);
			$("#sgst").val(paymentDetail.sgst);
			
			$("#totalFee").val(paymentDetail.total);
			
			$("#isTrader").prop("checked",getCheckBoxVal(paymentDetail.isTrader));
			isTraderChanged($("#isTrader"));
			$('#paymentMode').val(paymentDetail.paymentMode);
			$('#paymentMode').trigger('change');
			$('#dateOfPayment').val(formatDate(paymentDetail.paymentDate));
			$('#referenceNo').val(paymentDetail.referenceNo);
			$('#micrCode').val(paymentDetail.micrCode);
			$('#bankName').val(paymentDetail.bankName);
			$('#branchName').val(paymentDetail.branchName);
			$("#factoryId").val(factoryId);
			$("#factoryId").attr("disabled","disabled");
			$("#isTrader").attr("disabled","disabled");
			$('#paymentMode').addClass("readonly");
			$('#dateOfPayment').addClass("readonly");
			$('#referenceNo').addClass("readonly");
			$('#micrCode').addClass("readonly");
			$('#bankName').addClass("readonly");
			$('#branchName').addClass("readonly");
		}
	}
}

function loadFactoryList(cls,list){
	$("."+cls).empty();
	$("."+cls).append("<option value=''></option>" );
	$.each(list,function(key,value){
		$("."+cls).append("<option value='"+value.partnerOrgId+"'>"+value.name+" </option>" );
	});
}

function loadPaymentModeList(cls,list){
	$("."+cls).empty();
	$("."+cls).append("<option value=''></option>" );
	$.each(list,function(key,value){
		$("."+cls).append("<option value='"+value.code+"'>"+value.name+" </option>" );
	});
}

function isTraderChanged(ele){
	
	if($(ele).css("display")!="none"){
		if(isTrader=='Y' && isManufacturer=='Y'){
		if($(ele).prop("checked")){
			$("#factorySeclectId").css("display","none");
			$('#factoryId').attr('disabled','disabled');
			$("#paymentMode option[value='ISEXEMP']").remove();
		}else{
			$("#factorySeclectId").css("display","block");
			$('#factoryId').removeAttr('disabled');
			$("#paymentMode option[value='ISEXEMP']").remove();
			$("#paymentMode").append('<option value="ISEXEMP">IS EXEMPTED</option>');
		}
	}
		else if(isTrader=='Y' && isManufacturer=='N'){
			if($(ele).prop("checked")){
				$("#factorySeclectId").css("display","none");
				$('#factoryId').attr('disabled','disabled');
				$("#paymentMode option[value='ISEXEMP']").remove();
			}
		}
	}
}

function downloadTahdrDetailDoc(){
	 
    var tahdrId=$('#tahdrPurchaseDetailForm #tahdrId').val();
    	if(tahdrId!=''){
    		submitToURL("getTAHDRDocDetail/"+tahdrId, "generateDocumentResp");	
    	}
}

function generateDocumentResp(resp){
	
	if($.isEmptyObject(resp)){
		$("#generatedDocAnchor").empty();
		var anchorHtml='<a href="#">No Tender Doc</a>';
		$("#generatedDocAnchor").append(anchorHtml);
	}else{
		    var tenderDocs=resp.objectMap.tahdrDetail==null?'':resp.objectMap.tahdrDetail.tenderDoc==null?'':resp.objectMap.tahdrDetail.tenderDoc;
			var anchorHtml='<a href="download/'+tenderDocs.attachmentId+'">'+tenderDocs.fileName+'</a>';
			$("#generatedDocAnchor").empty();
			$("#generatedDocAnchor").append(anchorHtml);
	}
}

function fetchTahdrPurchaseList(pageNumber, pageSize, searchMode, searchValue){
	var urlValue=urlName;
	var paramValue=paramName;
	
	var response;
	$.ajax({
        type : "POST",
        contentType : "application/json",
        url: urlValue+"?docType="+paramValue+"&pageNumber="+pageNumber+"&pageSize="+pageSize+"&searchMode="+searchMode+"&searchValue="+searchValue,
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

function setPaymentTypeByAmount(totalFee,tenderTypeCode){
	if(tenderTypeCode=='WT'){
		if(totalFee>1000){
			$('.onlinePaymentDocNoDivId').hide();
			$('#paymentMode').val("OP");
			$('#paymentMode').trigger('change');
		/*	$('#paymentMode').addClass('readonly');*/
		}else{
			$('#paymentMode').removeClass('readonly');
		}
	}
}

function resetAllTab(){
	resetTopTenderTab();
	/*resetTenderFeesDetailTab();*/
}

function resetTopTenderTab(){
	$('#tendertab #tenderName').val('');
	$('#tendertab #tenderTitle').val('');
	$('#tendertab #description').val('');
	$('#tendertab #estimatedCost').val('');
	$('#tendertab #tenderFees').val('');
	$('#tendertab #tenderClosingDate').val('');
	
	$('#purchaseActionBtnDivId').hide();
	setHeaderValues("", "", "", "");
}

function resetTenderFeesDetailTab(){
	
}

