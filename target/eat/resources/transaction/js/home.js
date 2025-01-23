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
	
	submitToURL("getAdvanceSearchCount", "setAllCount");
	$(".submitForgotPassword").on("click",function(event){
		debugger;
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
	debugger;

	if(data.response.hasError){
		swal(data.response.message,'','error');
	    CreateCaptcha();
		/*alert (data.response.message);*/
	}else{
		if(data.isPasswordUpdated=="Y")
	    {
		  window.location.replace("view");
	    }else{
	      window.location.replace("changePassword");
	    }
	}
	/*else{
		window.location.replace("view");
	}*/

}


function getLatestTahdr(value){
	debugger;
	submitToURL('getLatestTahdrTypeCode/'+value,'TahdrTableResponse');
}

function setAllCount(data){
	$(".setFACount").text(data.FACount);
	$(".setRACount").text(data.RACount)
}
function TahdrTableResponse(data){
	if(!($.isEmptyObject(data.DATA)))
	{
		console.log(data.DATA);
		appendList(data.DATA);
		
	 
	}else
		{
		$('#latestTahdr tbody').empty();
		  $('#latestTahdr tbody').append('<tr><td colspan="14" style="text-align: center;"><b>"No Records Found"</b></td></tr>');
		}
}
function appendList(data)
{
	 var res="";
	 $('#latestTahdr tbody').empty();
     for(i=0;i<data.length;i++){
    	 var tdate="";
    	 var techDate="";
    	  if(data[i].purchaseToDate!=null)
    		  {
    		    tdate=formatDate(data[i].purchaseToDate);
    		  }
    	  if(data[i].techBidOpenningDate!=null)
		  {
    		  techDate=formatDate(data[i].techBidOpenningDate);
		  }
    	 $('#latestTahdr tbody').append("<tr><td>"+data[i].tahdr.title+"</td>" +
    			  "<td>"+tdate+"</td>" +
		          "<td>"+techDate+"</td>" +
       	 		  "</tr>");
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