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
			submitWithParam('getQuickLiveBidAuctionMaterialListById','tahdrId','loadAuctionMaterialListForView');
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
function newBidSubmit(){
	var newExGroupPriceRate=$('#inputExGroupPriceRateId').val();
	if(newExGroupPriceRate!='' && newExGroupPriceRate!=0){
		calculate();
		$('#otpFormId #otpId').val(''); 
		 /*var fddRateGST=$("#fddRateGST").html();*/
		 var fddRateGST=$("#fddRateGST_input").val();
	     $('#fddRateWithGST').val(Number(fddRateGST));
		submitIt('bidFormId','saveNewBid','saveNewBidResp');
	}else{
		Alert.warn('Ex Group Price Rate should not be empty or Zero');
	}
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
			submitWithParam('getQuickLiveBidAuctionMaterialListById','tahdrId','loadAuctionMaterialListForView');
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

function calculate(){
	/*var qty=$("#offeredQuantity").html();*/var qty=totalQuantity;
	var exGroupPrice=$("#inputExGroupPriceRateId").val();
	$("#exGroupPriceRate").html(exGroupPrice);
	var totalExGroupPrice=Number(qty)*Number(exGroupPrice);
	$("#totalExGroupPrice").html(totalExGroupPrice);
	
	/*var freightCharges=$("#freightChargeRate").html();
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
	*/
	/*var fddRateGST=(Number(fddRate)+Number(taxAmount)).toFixed(2);*/
	/*var fddRateGST=(Number(exGroupPrice)*Number(qty)).toFixed(2);*/
	var fddRateGST=(Number(exGroupPrice)).toFixed(2);
	/*$("#fddRateGST").html(fddRateGST);*/
	$("#fddRateGST_input").val(fddRateGST);
	/*var fddAmountGST=(Number(qty)*Number(fddRateGST)).toFixed(2);
	$("#fddAmountGST").html(fddAmountGST);*/
}

function setNewBid(){
	var bidValue=$('#bidFormId #inputExGroupPriceRateId').val();
	var times=$('#bidFormId #inputExGroupPriceRateId').data('times');
	var highestBidOrLowest=0;;
	if(currentRate>0){
		highestBidOrLowest=currentRate;
	}else{
		highestBidOrLowest=baseRate;
	}
	var incrementOrDecremnetBy=tenderMinBidDiffer;
	var newBid=typeCode=='QFA'?Number(highestBidOrLowest)+Number(Number(times)*Number(incrementOrDecremnetBy)):Number(highestBidOrLowest)-Number(Number(times)*Number(incrementOrDecremnetBy));
	$('#bidFormId #inputExGroupPriceRateId').val(newBid);
	times=Number(times)+1;
	$('#bidFormId #inputExGroupPriceRateId').data('times',times);
	/*calculate();*/
}

function setTimer(remainingTime,isRemainingTimeCalculated){
	var remainingDuration='0s';
	if(isRemainingTimeCalculated){
		remainingDuration=remainingTime;
	}else{
		remainingDuration=formateServerDate(remainingTime,serverTime);
	}
	$('.rightTopClass').html("");
	$('.rightTopClass').css("color","red");
	$('.rightTopClass').html('Remaining Time: <label class="rightTopTimerClass"></label>');
	$('.rightTopTimerClass').append().timer({
	    countdown: true,
	    duration: remainingDuration,
	    callback: function() {
	    	$('.rightTopTimerClass').html('Times Up !');
	    	$("#autoRefresh").timer('remove');
	    	 var tahdrId=$('#bidFormId #tahdrId').val();
	    	 if(tahdrId!=''){
	    		 submitToURL("saveQuickAuctionWinner/" + tahdrId,'quickAuctionWinnerResp'); 
	    	 }
	    	   
	    }
	});
}

function quickAuctionWinnerResp(data){
	if(data.objectMap.Status){
		/*swal({
			  title: data.objectMap.Message,
			  type: 'success',
			  showCancelButton: true,
			  confirmButtonColor: '#3085d6',
			  cancelButtonColor: '#d33',
			  confirmButtonText: 'OK'
			})
			.then((result) => {
			  if (result.value) {
				  window.location.reload();
			  }
			});*/
		Alert.info(data.objectMap.Message);
		window.location.reload();
	}else{
		Alert.warn(data.objectMap.Message);
		window.location.reload();
	}
}