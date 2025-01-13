var interval = null;
var timesRun = 0;
var _requestID = $("#request_id").val();
getStatus(_requestID);

interval = setInterval(function() {
	timesRun += 1;
	if (timesRun === 10) {
		clearInterval(interval);
		$(".waitingDiv").hide();
		$(".paymentpendingDiv").show();
	} 
	getStatus(_requestID);
}, 10000);

function getStatus(_requestID) {
	console.log("called function" + timesRun);
	var response = fetchList('paymentResponseStatus?doc_no='+_requestID, null);
	debugger;
	if (response.objectMap.paymentGatewayResponseDto != undefined) {
		if (response.objectMap.paymentGatewayResponseDto.status == 'failed') {
			$(".paymentFailedDiv").show();
		} else if (response.objectMap.paymentGatewayResponseDto.status == 'Success') {
			$(".paymentSuccessDiv").show();
		}
		$(".waitingDiv").hide();
		clearInterval(interval);
	}
	if (response.objectMap.paymentDetailDto != null) {
		$(".amount").text(response.objectMap.paymentDetailDto.amount);
		$(".requestID").text(response.objectMap.paymentDetailDto.docNo);
		$(".paymentType").text(
				response.objectMap.paymentDetailDto.paymentType.description);
	}

}