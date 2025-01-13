function checkOtp(event){
	event.preventDefault();
     var otp=$('#otpFormId #otpId').val();
     if(otp==''){
    	 Alert.warn('Please enter OTP !'); 
     }else{
    	 submitIt('otpFormId','validateOtp','checkOtpResp');
     }
}
function checkOtpResp(data){
	if(!$.isEmptyObject(data)){
		if(data.objectMap.result){
			$('.livebidclass').show();
			$('.otpcontent').hide();
			submitWithParam('getLiveBidAuctionMaterialListById','tahdrId','loadAuctionMaterialListForView');
		}else{
			Alert.warn(data.objectMap.resultMessage);
		}
	}else{
		Alert.warn('Request has not processed !');
	}
		
}

function generateOtp(event){
	event.preventDefault();
	submitToURL('sendOtp?tahdrId='+tenderId+'&tahdrName='+tenderName,'sendOtpResp');
}
/*function newBidSubmit(event){
	event.preventDefault();
	var newExGroupPriceRate=$('#inputExGroupPriceRateId').val();
	if(newExGroupPriceRate!='' && newExGroupPriceRate!=0){
		$('#otpFormId #otpId').val(''); 
		 var fddRateGST=$("#fddRateGST").html();
	     $('#fddRateWithGST').val(Number(fddRateGST));
		submitIt('bidFormId','saveNewBid','saveNewBidResp');
	}else{
		Alert.warn('Ex Group Price Rate should not be empty or Zero');
	}
}*/
function newBidSubmit(event){
	event.preventDefault();
	swal({
		  title: 'Do You wish to Continue with new Bid?',
		  type: 'warning',
		  showCancelButton: true,
		  confirmButtonColor: '#3085d6',
		  cancelButtonColor: '#d33',
		  confirmButtonText: 'Yes,Proceed'
		})
		.then((result) => {
		  if (result.value) {
			  var newExGroupPriceRate=$('#inputExGroupPriceRateId').val();
				if(newExGroupPriceRate!='' && newExGroupPriceRate!=0){
					$('#otpFormId #otpId').val(''); 
					 var fddRateGST=$("#fddRateGST").html();
				     $('#fddRateWithGST').val(Number(fddRateGST));
					submitIt('bidFormId','saveNewBid','saveNewBidResp');
				}else{
					Alert.warn('Ex Group Price Rate should not be empty or Zero');
				}
		  }
		});
}
function sendOtpResp(data){
	if(data.objectMap.result){
		if(!data.objectMap.hasRegistered){
			if(data.objectMap.otpResult){
				$('#otpFormId #otpId').val('');
				Alert.info(data.objectMap.resultMessage);
				$('#resendOtpBtnId').show();
			}else{
				$('#otpFormId #otpId').val('');
				Alert.warn(data.objectMap.resultMessage);
				$('#resendOtpBtnId').show();
			}
		}else{
			$('.livebidclass').show();
			$('.otpcontent').hide();
			submitWithParam('getLiveBidAuctionMaterialListById','tahdrId','loadAuctionMaterialListForView');
		}
	}else{
		Alert.warn('Something went wrong!');
		$('#resendSpinId').removeClass('fa');
		$('#resendSpinId').removeClass('fa-refresh');
		$('#resendSpinId').removeClass('fa-spin');
		$('#sendOtpBtnId').html('Generate Otp');
		$('#sendOtpBtnId').removeClass('readonly');
	}
}
function resendOtp(event){
	event.preventDefault();
	$('#resendOtpBtnId').html('re-sending ....');
	$('#resendOtpBtnId').addClass('readonly');
	$('#resendSpinId').addClass('fa');
	$('#resendSpinId').addClass('fa-refresh');
	$('#resendSpinId').addClass('fa-spin');
	submitToURL('resendOtp?tahdrId='+tenderId+'&tahdrName='+tenderName,'resendOtpResp');
}
function resendOtpResp(data){
	if(data.objectMap.result){
		Alert.info(data.objectMap.resultMessage);
	}else{
		Alert.warn(data.objectMap.resultMessage);
	}
	$('#resendSpinId').removeClass('fa');
	$('#resendSpinId').removeClass('fa-refresh');
	$('#resendSpinId').removeClass('fa-spin');
	$('#resendOtpBtnId').html('Resend Otp');
	$('#resendOtpBtnId').removeClass('readonly');
}
/*function saveNewBidResp(data){
	if(!$.isEmptyObject(data.objectMap)){
		if(data.objectMap.result){
			$('#bidderDetailForm #rank').html(1);
			var currentRate=$("#bidderDetailForm #fddRateGST").html();
			if(data.objectMap.isAuctionExtended){
				var bidEndDate=data.objectMap.newDetail==null?'':data.objectMap.newDetail.auctionToDate==null?'':data.objectMap.newDetail.auctionToDate;
				$('.rightTopClass').timer({
				    countdown: true,
				    duration: formateCurrentDate(bidEndDate),
				    callback: function() {
				    	$('.rightTopClass').html('Times Up !');
				    }
				});
			}
			$('.detailscont #rightBottomId').html(currentLabelName+" Rate : "+currentRate+" &nbsp;&nbsp;&nbsp;&nbsp; Your "+currentLabelName+" rate : "+g_exGroupPriceRate+" &nbsp;&nbsp; Req. Quantity:"+totalQuantity);
			Alert.info(data.objectMap.resultMessage);
		}else{
			var bidEndDate=data.objectMap.newDetail==null?'':data.objectMap.newDetail.auctionToDate==null?'':data.objectMap.newDetail.auctionToDate;
			$('.rightTopClass').timer({
			    countdown: true,
			    duration: formateCurrentDate(bidEndDate),
			    callback: function() {
			    	$('.rightTopClass').html('Times Up !');
			    }
			});
			Alert.warn(data.objectMap.resultMessage);
		}	
	}else{
		Alert.warn('something went wrong !');
		
	}
	
}*/

function calculate(){
	 
	var qty=$("#offeredQuantity").html();
	var exGroupPrice=$("#inputExGroupPriceRateId").val();
	$("#exGroupPriceRate").html(exGroupPrice);
	var totalExGroupPrice=Number(qty)*Number(exGroupPrice);
	$("#totalExGroupPrice").html(totalExGroupPrice);
	
	var freightCharges=$("#freightChargeRate").html();
	var totalFreightCharges=Number(qty)*Number(freightCharges);
	$("#totalFreightCharges").html(totalFreightCharges);
	
	var tic=$("#ticRate").html();
	var totalTic=Number(qty)*Number(tic);
	$("#totalTic").html(totalTic);
	
	var fddRate=(Number(exGroupPrice)+Number(freightCharges)+Number(tic)).toFixed(2);
	$("#fddRate").html(fddRate);
	var fddAmount=(Number(qty)*Number(fddRate)).toFixed(2);
	$("#fddAmount").html(fddAmount);
	
	var taxRate=$("#taxRate").html();
	var taxAmount=Number((fddRate*taxRate)/100).toFixed(2);
	var totalTax=(Number(qty)*Number(taxAmount)).toFixed(2);
	$("#taxAmount").html(taxAmount);
	$("#totalTax").html(totalTax);
	
	var igstRate=$("#igst").html();
	var igstAmount=Number((fddRate*Number(igstRate))/100).toFixed(2);
	$("#igstAmount").html(igstAmount);
	
	var cgstRate=$("#cgst").html();
	var cgstAmount=Number((fddRate*Number(cgstRate))/100).toFixed(2);
	$("#cgstAmount").html(cgstAmount);
	
	var sgstRate=$("#sgst").html();
	var sgstAmount=Number((fddRate*Number(sgstRate))/100).toFixed(2);
	$("#sgstAmount").html(sgstAmount);
	
	var fddRateGST=(Number(fddRate)+Number(taxAmount)).toFixed(2);
	$("#fddRateGST").html(fddRateGST);
	var fddAmountGST=(Number(qty)*Number(fddRateGST)).toFixed(2);
	$("#fddAmountGST").html(fddAmountGST);
}