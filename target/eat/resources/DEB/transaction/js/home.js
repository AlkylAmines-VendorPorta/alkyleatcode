$(document).ready(function(){
	/*if($.isEmptyObject($('#username').val())){
		$('.username').addClass("validinput");
	}
	else{
		$('.username').removeClass("validinput");
	}
	if($.isEmptyObject($('#password').val())){
		$('.password').addClass("validinput");
	}
	else{
		$('.password').removeClass("validinput");
	}*/
	
	
	$(".submitForgotPassword").on("click",function(event){
		event.preventDefault();
		var email=$("#emailFP").val();
		submitIt("forgotPassForm","forgotPassword","forgotPasswordResp");
	});
	/*$(".LatestTahdrTables").hide();
	
	$(".tahdrTypes").on("click", function(event){
		debugger;
		   event.preventDefault();
		   $(".LatestTahdrTables").show();
		 
		   $(".homeBody").hide();
		   
		   return false;
	});
	
	$(".submitForgotPassword").on("click",function(event){
		event.preventDefault();
		var email=$("#emailFP").val();
		submitIt("forgotPassForm","forgotPassword","forgotPasswordResp");
	});*/
	 $('.table').on("click",".viewTahdrDetail",function(){
		var tahdrId =  $(this).data('thdrcode');
		$('#tndrCode').text($(this).text());
		submitToURL('getTAHDRDetailsById/'+tahdrId,'tahdrDetailsResponse');
		$("#ViewTenderDetailModal").modal('show');
	 }); 
});

function processResponse(data){
	if(!isEmpty(data) && !isEmpty(data.response)){
		if(data.response.hasError){
			swal(data.response.message,'','error');
			CreateCaptcha();
		}
		else{
			if(data.isPasswordUpdated=="Y"){
			  window.location.replace("view");
		    }else{
		      window.location.replace("changePassword");
		    }
		}
	}
}


function getLatestTahdr(value){
	if(value!=''){
		submitToURL('getLatestTahdrTypeCode/'+value,'TahdrTableResponse');
	}
}

function TahdrTableResponse(data){
	if(!isEmpty(data) && !($.isEmptyObject(data.DATA))){
		appendList(data.DATA);
	}else{
		 $('#latestTahdr tbody').empty();
		  $('#latestTahdr tbody').append('<tr><td colspan="14" style="text-align: center;"><b>"No Records Found"</b></td></tr>');
	}
}
function appendList(data){
	 var res="";
	 $('#latestTahdr tbody').empty();
     for(var i=0;i<data.length;i++){
    	 var tdate="";
    	 var techDate="";
    	 var techbidToDate="";
    	 if(data[i]!=null){
    		 if(data[i].purchaseToDate!=null){
     		    tdate=formatDate(data[i].purchaseToDate);
     		  }
     	  if(data[i].techBidOpenningDate!=null) {
     		  techDate=formatDate(data[i].techBidOpenningDate);
 		  }
     	 if(data[i].technicalBidToDate!=null) {
     		techbidToDate=formatDate(data[i].technicalBidToDate);
		  }
     	  var tahdrCode=data[i].tahdr==null?'':data[i].tahdr.tahdrCode;
     	  var tahdrId=data[i].tahdr==null?'':data[i].tahdr.tahdrId;
     	 $('#latestTahdr tbody').append("<tr><td title='"+tahdrCode+"'><a href ='#' class = 'viewTahdrDetail' data-thdrCode="+tahdrId+">"+tahdrCode+"</a></td>" +
     			 "<td title='"+tdate+"'>"+tdate+"</td>" +
 		          "<td title='"+techbidToDate+"'>"+techbidToDate+"</td>" +
        	 		  "</tr>");
    	 }
     }
}

function forgotPasswordResp(resp){
	if(!$.isEmptyObject(resp)){
		if(resp.hasError){
			Alert.warn(resp.message);
		}else{
			Alert.info(resp.message);
		}
		
	}
	$('#ForgotPassDIV').hide();
	$('#loginDIV').show();
}

function tahdrDetailsResponse(data){
	if(!isEmpty(data) && !isEmpty(data.objectMap)){
		if(!$.isEmptyObject(data.objectMap.websiteUrl)){
			$(".webUrl").text(data.objectMap.websiteUrl);
		}

		if(data.objectMap.tahdr != undefined){
			if(data.objectMap.tahdr.tahdrDetail[0] != undefined){
				var tahdr = data.objectMap.tahdr;
				var tahdrDetail = tahdr.tahdrDetail[0]; 
				var tenderFee = tahdrDetail.tahdrFees;
				var gst = 18 ;
				var gstAmount = (tahdrDetail.tahdrFees*gst)/100;
				var totalAmount = tenderFee + gstAmount;
				var isPriceBid= tahdr.tahdrDetail[0]==null?'':tahdr.tahdrDetail[0].isPBDSetLater==null?'':tahdr.tahdrDetail[0].isPBDSetLater;
				var isAnnexure= tahdr.tahdrDetail[0]==null?'':tahdr.tahdrDetail[0].isAnnexureC1==null?'':tahdr.tahdrDetail[0].isAnnexureC1;
				var isSetC1 = tahdr.tahdrDetail[0]==null?'':tahdr.tahdrDetail[0].isSetC1Later==null?'':tahdr.tahdrDetail[0].isSetC1Later;
				
				$('#bidType').text(tahdr.bidTypeCode=='TB'?'Two Bid':'Single Bid');
				$('#materialDesc').text(tahdrDetail.description);
				$('#estimatedCost').text(tahdrDetail.estimatedCost);
				$('#tenderFee').text(tahdrDetail.tahdrFees);
				$('#gst').text(gstAmount);
				$('#ttltndrFee').text(totalAmount);
				$('#tndrVldt').text(tahdrDetail.tahdrValidity);
				$('#tndrSaleOpnDate').text(formatDateTime(tahdrDetail.purchaseFromDate));
				$('#tndrSaleClsDate').text(formatDateTime(tahdrDetail.purchaseToDate));
				$('#cntctEmail').text(tahdrDetail.contactEmailId);
				$('#sbmsnDate').text(formatDateTime(tahdrDetail.technicalBidToDate));
				$('#tchnCmrclBidDate').text(formatDateTime(tahdrDetail.techBidOpenningDate));
				/*$('#prcBid').text(formatDateTime(tahdrDetail.priceBidOpenningDate));*/
				$('#dvsnDate').text(tahdrDetail.deviationOpenningDate);
				/*$('#anxrc1Date').text(formatDateTime(tahdrDetail.c1OpenningDate));*/
				
				if(isPriceBid=='Y'){
					$('#prcBid').text('');
				}
				else{
					$('#prcBid').text(formatDateTime(tahdrDetail.priceBidOpenningDate));
				}
				
				if(tahdr.tahdrTypeCode == 'PT' || tahdr.tahdrTypeCode == 'FA'){
					if(isAnnexure=='N'){
						$('#anxrc1Date').text('');
					}
					else if(isAnnexure=='Y'){
						if(isSetC1=='Y'){
							$('#anxrc1Date').text('');
						}
						else{
							$('#anxrc1Date').text(formatDateTime(tahdrDetail.c1OpenningDate));
						}
					}	
				}
				else{
					$('#anxrc1Date').text('');
				}
				
				if(tahdr.tahdrTypeCode == 'PT'){
					if(!isEmpty(tahdrDetail.officeNote)){
						$('#dwnldTahdrDtil').attr('href','./download/'+tahdrDetail.officeNote.attachmentId);
					}else{
						$('#dwnldTahdrDtil').hide();
					}
				}else{
					if(tahdrDetail.estimatedCost < 800){
						if(!isEmpty(tahdrDetail.tenderDoc)){
							$('#dwnldTahdrDtil').attr('href','./download/'+tahdrDetail.tenderDoc.attachmentId);
						}
					}else{
						$('#dwnldTahdrDtil').hide();
					}
				}
				
			}
		}
	}
	
}