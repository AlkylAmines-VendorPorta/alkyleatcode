$(document).ready(function(){
	renderList();
	$(".sendstatus").click(function(){
		var atr = $(this).attr("name");
		$("#actionstatus").val(atr);
		submitWithTwoParam("updateStatus", "invitationid","actionstatus", "getresponse");
		
	});
	
	
});
function getresponse(data){
	if(data.objectMap.RESULT_STATUS){
		Alert.info(data.objectMap.RESULT_MESSAGE);
		appendList(data.objectMap.invitationList);
	}else{
		Alert.info(data.objectMap.RESULT_MESSAGE);
	}
	
}

function renderList(){ 
	$.ajax({
        type : "POST",
        contentType : "application/json",
        url: "getInvitationsList",
        dataType:"json",
        async:false,
        success: function (data) {
        	if($.isEmptyObject(data)){
        		  swal("No Request Pending");
        		}
        	else{
	        	appendList(data);
        	}
		        	
        },
        error: function (e) {
			swal("Exception :");
        }
    });
	}

function appendList(data){
	var active=" class='active'";
	$('#example').empty();
	var activeStatus="";
	if(!isEmpty(data)){
		for (var i=0;i<data.length;i++)  {
			if(data[i].isActive=="Y")
				activeStatus="Active";
			else
				activeStatus="InActive";
			if(i==0){
					$("#invitedcompanypanno").val(data[i].partner.panNumber);
					$("#invitedcomapanyname").val(data[i].partner.name);
					$("#invitedcompanycrn").val(data[i].partner.crnNumber);
					$("#invitedgstinnoe").val(data[i].partner.gstinNo);
					
					$("#invitationid").val(data[i].mBPartnerInvitationId);
					if(data[i].isActive=="Y")
					if(activeStatus=="Active")
						$('.isActive').prop('checked', true);
					else
						$('.isActive').prop('checked', false);
					
				$('#masterDetails').empty();
				$('#masterDetails').append('<div class="row"><label class="col-xs-6" ><h4 class="detail_Name">'+data[i].partner.name+'</h4></label>'
			            +'<label class="col-xs-6 mytext detail_Code">'+data[i].partner.panNumber+'</label></div> '
			            +'<div class="row"><label class="col-xs-6 mytext detail_Desc">'+data[i].partner.crnNumber+'</label>'
			            +'<label class="col-xs-6 mytext detail_Active">'+data[i].partner.gstinNo+'</label></div>');
				}
				
			$('#example').append('<li '+active+' onclick="showdetails('+data[i].mBPartnerInvitationId+')"> <a href="" class="" data-toggle="tab">'
			           +' <div class="col-md-12">'
			           +'  <label class="col-xs-6">'+data[i].partner.name+'</label>'
			           +'   <label class="col-xs-6 mytext" data-Id="'+data[i].mBPartnerInvitationId+'">'+data[i].partner.crnNumber+'</label>'
			           +'  </div>'
			           +'  <div class="col-md-12">'
			           +'    <label class="col-xs-6">'+data[i].partner.panNumber+'</label>'
			           +'    <label class="col-xs-6 mytext2">'+data[i].partner.gstinNo+'</label>'
			           +'  </div>'
			           +'  </a>'
			           +'  </li>');
			active="";
	          }
		$("#approveinvitation").show();
		$("#rejectinvitation").show();
	}else{
		$('#masterDetails').empty();
		$("#invitedcompanypanno").val('');
		$("#invitedcomapanyname").val('');
		$("#invitedcompanycrn").val('');
		$("#invitedgstinnoe").val('');
		$('.isActive').prop('checked', false);
		$("#approveinvitation").hide();
		$("#rejectinvitation").hide();
	}
	
	$('.example').paginathing();	
		
		$(".tabs-left li a label").text(function(index, currentText) {
		    return currentText.substr(0, 20);
	});
		$('.reportCount').html(data.length);
		
	}

function showdetails(id){
	
	$(".save").hide();
	$(".cancle").hide();
	$("#editroles").hide();
	$.ajax({
        type : "POST",
        contentType : "application/json",
        url: "getInvitationListId/"+id,
        dataType:"json",
        async:false,
        success: function (data) {
        	if($.isEmptyObject(data)){
        		  swal("something went wrong");
        		}
        	else{
        		$("#invitedcompanypanno").val(data.partner.panNumber);
				$("#invitedcomapanyname").val(data.partner.name);
				$("#invitedcompanycrn").val(data.partner.crnNumber);
				$("#invitedgstinnoe").val(data.partner.gstinNo);
				
				
				
				$("#invitationid").val(data.mBPartnerInvitationId);
				if(data.isActive=="Y")
				if(activeStatus=="Active")
					$('.isActive').prop('checked', true);
				else
					$('.isActive').prop('checked', false);
				
				$(".detail_Name").html(data.partner.name);
				$(".detail_Code").html(data.partner.panNumber);
				$(".detail_Desc").html(data.partner.crnNumber);
				$(".detail_Active").html(data.partner.gstinNo);			
				$("#approveinvitation").show();
				$("#rejectinvitation").show();
        	}
		        	
        },
        error: function (e){
			swal("Exception :");
        }
    });
	}

function getBPDetailsResponse(data){
	if(data.objectMap.hasOwnProperty('user')){
		var user=data.objectMap.user;
		if(!$.isEmptyObject(user)){
			var userId=user.userId==null?'':user.userId;
			var companyName=user.partner==null?'':user.partner.name==null?'':user.partner.name;
			var email=user.email==null?'':user.email;
			var firstName=user.userDetails==null?'':user.userDetails.firstName==null?'':user.userDetails.firstName;
			var lastName=user.userDetails==null?'':user.userDetails.lastName==null?'':user.userDetails.lastName;
			var mobile=user.userDetails==null?'':user.userDetails.mobileNo==null?'':user.userDetails.mobileNo;
			
			$("#useridforinvitation").val(userId);
			$("#comapanyname").val(companyName);
			$("#companyemail").val(email);
			$("#userfirtsname").val(firstName);
			$("#userlastname").val(lastName);
			$("#companymob").val(mobile);
			
		}else{
			/*$("#invitationForm").show();
			$("#sendRequest").hide();
			$("#sendInvitation").show();
			$("#sendInvitationLable").show();
			$("#sendRequestLable").hide();*/
		}
		}
	
}

function sendInvitationMail(event){
	 event.preventDefault();
	 submitIt("userdetailsforinvitation", "sendInvitationMail", 'sendInvitationResp');
}

function sendInvitationResp(data){
  Alert.info("helo");	
}