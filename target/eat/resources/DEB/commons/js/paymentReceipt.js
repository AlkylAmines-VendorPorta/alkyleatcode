$(document).ready(function(){
	debugger;
	$("#print").show();
	var dataUrl=$('#receiptUrl').val();
	if(dataUrl!=null || dataUrl!='' || dataUrl!=undefined ){
		submitToURL(dataUrl, "paymentReceiptData");
	}
});

function paymentReceiptData(data){
	console.log(data);
	if ($.isEmptyObject(data)) {

		return;
	}
	else{
		var internalUser=data.objectMap==null?'':data.objectMap.internalUser==null?'':data.objectMap.internalUser;
		var paymentDetail=data.objectMap==null?'':data.objectMap.paymentDetail==null?'':data.objectMap.paymentDetail;
		var userDetailsData=data.objectMap==null?'':data.objectMap.userDetailsData==null?'':data.objectMap.userDetailsData;
		var companyAddressDto=data.objectMap==null?'':data.objectMap.companyAddressDto==null?'':data.objectMap.companyAddressDto;
		var today=data.objectMap==null?'':data.objectMap.today==null?'':data.objectMap.today;
		$("#date").html(today);
		if(internalUser!=null){
			
			
			if(internalUser.partner!=null){
				$("#supplierName").html(internalUser.partner.name);
				$("#supplierGSTIN").html(internalUser.partner.gstinNo);
			}	
			if(internalUser.userDetails!=null){
				$("#signatoryName").html(internalUser.userDetails.firstName+" "+internalUser.userDetails.lastName);
				var designation=internalUser.userDetails.designation==null?'':internalUser.userDetails.designation.name==null?'':internalUser.userDetails.designation.name;
				$("#signatoryDesignation").html(designation);
			}
			if(internalUser.userDetails.location!=null){
				$("#supplierAddress").html(internalUser.userDetails.location.address1);
			}
			
		}
		if(userDetailsData!=null){
			$("#vendorName").html(userDetailsData.firstName+" "+userDetailsData.lastName);
			if(userDetailsData.partner!=null){
			$("#companyName").html(userDetailsData.partner.name);
			$("#vendorGSTIN").html(userDetailsData.partner.gstinNo);
			$("#vendorPAN").html(userDetailsData.partner.panNumber);
			$("#companyGSTIN").html(userDetailsData.partner.gstinNo);
			$("#companyPAN").html(userDetailsData.partner.panNumber);
			}
		}
		if(companyAddressDto!=null){
		if(companyAddressDto.location!=null){
			$("#vendorAddress").html(companyAddressDto.location.address1+", "+companyAddressDto.location.city+", "+"Pincode:"+companyAddressDto.location.postal);
			$("#companyAddress").html(companyAddressDto.location.address1+", "+companyAddressDto.location.city+", "+"Pincode:"+companyAddressDto.location.postal);
			if(companyAddressDto.location.region!=null){
			$("#companyState").html(companyAddressDto.location.region.name);
			$("#vendorState").html(companyAddressDto.location.region.name);
			}
		}
		}
		if(paymentDetail!=null){
		var paymentTypeName=paymentDetail.paymentType.code==null?'':paymentDetail.paymentType.code;
		var factoryName=paymentDetail.partnerOrg==null?'':paymentDetail.partnerOrg.name==null?'':paymentDetail.partnerOrg.name;
		$("#invoiceNo").html(paymentDetail.paymentDetailId);
		$("#invoiceDate").html(formatDate(paymentDetail.paymentDate));
		var fees='';
		if(paymentDetail.paymentType!=null && paymentDetail.paymentType.hsn!=null){
			var descriptionGoods=paymentDetail.paymentType.description==null?'':paymentDetail.paymentType.description;
			var hsnSacCode=paymentDetail.paymentType.hsn.code==null?'':paymentDetail.paymentType.hsn.code;
			var isRefundable=paymentDetail.paymentType.isRefundable==null?'':paymentDetail.paymentType.isRefundable;
			var total=Number(paymentDetail.amount).toFixed(2);
			var taxableAmount=Number(total+0).toFixed(2);
			var totalofAllWithGST=Number(paymentDetail.total).toFixed(2);
			var amountInWords=numberToWords(Number(totalofAllWithGST).toFixed(2));
			var cgst='';
			var igst=''
			var sgst='';
			var cgstAmount='';
			var igstAmount='';
			var sgstAmount='';
			var cgstRate=paymentDetail.cgst==null?'':paymentDetail.cgst;
			var sgstRate=paymentDetail.sgst==null?'':paymentDetail.sgst;
			var igstRate=paymentDetail.igst==null?'':paymentDetail.igst;
			
			if(cgstRate!=null && cgstRate!=""){
				cgst=Number(cgstRate).toFixed(2);
				cgstAmount=(cgst*taxableAmount)/100;
			}
			else{
				cgst='NA';
				cgstAmount='NA';
			}
			
			if(sgstRate!=null && sgstRate!=""){
				sgst=Number(sgstRate).toFixed(2);
				sgstAmount=(sgst*taxableAmount)/100;
			}
			else{
				sgst='NA';
				sgstAmount='NA';
			}
			
			if(igstRate!=null && igstRate!=""){
				igst=Number(igstRate).toFixed(2);
				igstAmount=(igst*taxableAmount)/100;
			}
			else{
				igst='NA';
				igstAmount='NA';
			}
		}
		
		if(paymentDetail.tahdr!=null && (paymentTypeName=='TF'|| paymentTypeName=='EMD')){
			fees=paymentDetail.tahdr.tahdrDetail[0].tahdrFees==null?'':paymentDetail.tahdr.tahdrDetail[0].tahdrFees;
			var tahdrCode=paymentDetail.tahdr.tahdrCode==null?'':paymentDetail.tahdr.tahdrCode;
			var description='';
				if(tahdrCode!=null){
					 descriptionGoods+="/("+tahdrCode+")";
				}
		}
		else if(paymentDetail.tahdr==null && (paymentTypeName=='RG' || paymentTypeName=='RN')){
			var description='';
			fees=Number(paymentDetail.amount).toFixed(2);
			if(paymentDetail.vendorTypePayment!=null){
				if(paymentDetail.vendorTypePayment=='MP'){
					 descriptionGoods+="/(Manufacturer/"+factoryName+")";
				}
				else if(paymentDetail.vendorTypePayment=='TP')
				 descriptionGoods+="/(Trader)";
			}
		}
				
					$("#paymentDetail").append('<td rowspan="5">1</td>'+
							'<td rowspan="5">'+descriptionGoods+'</td>'+
							'<td rowspan="5">'+hsnSacCode+'</td>'+
							'<td rowspan="5">1</td>'+
							'<td rowspan="5">No</td>'+
							'<td rowspan="5">'+fees+'</td>'+
							'<td rowspan="5">'+total+'</td>'+
							'<td rowspan="5">0	</td>'+
							'<td  rowspan="5">'+taxableAmount+'</td>'+
							'<td rowspan="5">'+cgst+'</td>'+ 
						    '<td rowspan="5">'+cgstAmount+'</td>'+
						    '<td rowspan="5">'+sgst+'</td>'+
						    '<td rowspan="5">'+sgstAmount+'</td>'+
						    '<td rowspan="5">'+igst+'</td>'+
						    '<td rowspan="5">'+igstAmount+'</td>'+
						    '<td rowspan="5">NA</td>'+
						    '<td rowspan="5">NA</td>');	
					
					$("#totalOfAllValues").append('<td colspan="6">Total</td>'+
							'<td >'+total+'</td>'+
							'<td >0</td>'+
							'<td>'+taxableAmount+'</td>'+
							'<td></td>'+ 
						    '<td>'+cgstAmount+'</td>'+
						    '<td></td>'+
						    '<td>'+sgstAmount+'</td>'+
						    '<td></td>'+
						    '<td>'+igstAmount+'</td>'+
						    '<td></td>'+
						    '<td></td>');	
					
					$("#totalInvoiceValue").append('<td height="20" colspan="9" style="height:15.00pt;">Total Invoice value (in figure)</td>'+
							'<td colspan="8" >'+totalofAllWithGST+'</td>');
					
					$("#totalInvoiceValueInWords").append('<td height="20" colspan="9" style="height:15.00pt;">Total Invoice value (in words)</td>'+
							'<td colspan="8" >'+amountInWords+'</td>');
					
					$("#isRefundable").append('<td height="20" colspan="9" style="height:15.00pt;">Whether Reverse charge applicable (Y/N)</td>'+
							'<td colspan="8" >'+isRefundable+'</td>');
			
			
		}
		
		}
}

function numberToWords(amount){
	if(!isEmpty(amount)){
		var amountArr=amount.split('.');
		var amountInWords='';
		var amountInRupees=convertNumberToWords(amountArr[0]);
		var amountInPaise=convertNumberToWords(amountArr[1]);
		if(!$.isEmptyObject(amountInRupees)){
			amountInWords=amountInRupees+" Rupees";
		}
		if(!$.isEmptyObject(amountInPaise)){
			amountInWords=amountInWords+ " " + amountInPaise +" Paise ";
		}
		return amountInWords;
	}
}

function printPage() {
	$("#print").hide();
    window.print();
}