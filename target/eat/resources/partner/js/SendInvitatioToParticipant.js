$(document).ready(function(){
	$("#companypanno").attr('readonly', true);
	$("#companyemail").attr('readonly', true);
	$("#comapanyname").attr('readonly', true);
	$("#companymob").attr('readonly', true);
	$("#userfirtsname").attr('readonly', true);
	$("#userlastname").attr('readonly', true);
	$("#initationstatus").attr('readonly', true);
	renderList();
	$("#sendInvitationLable").hide();
	$("#sendRequestLable").hide();
	$("#invitationForm").hide();
	$("#sendRequest").hide();
	$("#sendInvitation").hide();
	$("#getbpartnerforinvitation").hide();
	$("#sendinvitationmail").hide();
	$("#sendQuickInvitationMail").hide();
	$("#getbpartnerforinvitation").click(function(event){
		 event.preventDefault();
		 var panno=$('#companypanno').val();
		 if(panno!=''){
			 submitWithParam("getBpartnerForInvitation", "companypanno", 'getBPDetailsResponse');
		 }else{
			 Alert.warn('Please provide PAN to search !');
		 }
		 
		/* $("#getbpartnerforinvitation").hide();
		$("#sendinvitationmail").removeClass('readonly');*/
	});
	$("#sendnewinvitation").click(function(){
		debugger;
		$("#userdetailsforinvitation").removeClass('readonly');
		$("#sendinvitationmail").addClass('readonly');
		$("#sendQuickInvitationMail").addClass('readonly');
		$("#getbpartnerforinvitation").show();
		$("#sendinvitationmail").show();
		$("#sendQuickInvitationMail").show();
		$("#companypanno").val("");
		$("#companypanno").attr('readonly', false);
		$("#companyemail").val("");
		$("#companyemail").attr('readonly', true);
		$("#comapanyname").val("");
		$("#comapanyname").attr('readonly', true);
		$("#companymob").val("");
		$("#companymob").attr('readonly', true);
		$("#userfirtsname").val("");
		$("#userfirtsname").attr('readonly', true);
		$("#userlastname").val("");
		$("#userlastname").attr('readonly', true);
		$("#initationstatus").val("");
		$("#initationstatus").attr('readonly', true);
		
	});
	
	
	$("#sendinvitationmail").click(function(event){
		 event.preventDefault();
		 submitIt("userdetailsforinvitation", "sendInvitationMail", 'sendInvitationResp');
		/* submitIt("userdetailsforinvitation", "sendInvitationMailForQuickAuction", 'sendInvitationResp');*/
		 /*renderList();*/
	});
	$("#sendQuickInvitationMail").click(function(event){
		 event.preventDefault();
		 submitIt("userdetailsforinvitation", "sendInvitationMailForQuickAuction", 'sendInvitationResp');
		 /*renderList();*/
	});
});


function renderList(){
	$.ajax({
        type : "POST",
        contentType : "application/json",
        url: "getInvitationList",
        dataType:"json",
        async:false,
        success: function (data) {
        	debugger;
        	if($.isEmptyObject(data)){
        		  Alert.warn("No Request Pending");
        		}
        	else{
	        	appendList(data);
        	}	
        },
        error: function (e) {
        	Alert.warn("Exception :");
        }
    });
	}

function appendList(data){
	debugger;
	var active=" class='active'";
	$('#example').empty();
	var activeStatus="";
	for (var i=0;i<data.length;i++)  {
		if(data[i].isActive=="Y")
			activeStatus="Active";
		else
			activeStatus="InActive";
		if(i==0){
				$("#companypanno").val(data[i].vendorPancardNo);
				$("#companyemail").val(data[i].vendorEmailId);
				$("#comapanyname").val(data[i].companyName);
				$("#companymob").val(data[i].mobileNo);
				$("#userfirtsname").val(data[i].firstName);
				$("#userlastname").val(data[i].lastName);
				if(data[i].isInvitationApproved=="Y"){
					$("#initationstatus").val("Accepted");
				}else if(data[i].isInvitationApproved=="N"){
					$("#initationstatus").val("Pending");
				}else{
					$("#initationstatus").val("Rejected");
				}
				$(".Id").val(data[i].mBPartnerInvitationId);
				if(data[i].isActive=="Y")
				if(activeStatus=="Active")
					$('.isActive').prop('checked', true);
				else
					$('.isActive').prop('checked', false);
				
			$('#masterDetails').empty();
			$('#masterDetails').append('<div class="row"><label class="col-xs-6" ><h4 class="detail_Name">Company Name: '+data[i].companyName+'</h4></label>'
		            +'<label class="col-xs-6 mytext detail_Code">First Name: '+data[i].firstName+'</label></div> '
		            +'<div class="row"><label class="col-xs-6 mytext detail_Desc">Last Name: '+data[i].lastName+'</label>'
		            +'<label class="col-xs-6 mytext detail_Active">Is Inviation Approved: '+data[i].isInvitationApproved+'</label></div>');
			}
			
		$('#example').append('<li '+active+' onclick="showdetails('+data[i].mBPartnerInvitationId+')"> <a href="" class="" data-toggle="tab">'
		           +' <div class="col-md-12">'
		           +'  <label class="col-xs-6">'+data[i].companyName+'</label>'
		           +'   <label class="col-xs-6 mytext" data-Id="'+data[i].mBPartnerInvitationId+'">'+data[i].firstName+'</label>'
		           +'  </div>'
		           +'  <div class="col-md-12">'
		           +'    <label class="col-xs-6">'+data[i].lastName+'</label>'
		           +'    <label class="col-xs-6 mytext2">'+data[i].isInvitationApproved+'</label>'
		           +'  </div>'
		           +'  </a>'
		           +'  </li>');
		active="";
          }
	$('.example').paginathing({
		perPage : 7
	});	
		
		$(".tabs-left li a label").text(function(index, currentText) {
		    return currentText.substr(0, 20);
	});
		$('.reportCount').html(data.length);
	}

function showdetails(id){
	debugger;
	$(".save").hide();
	$(".cancle").hide();
	$("#editroles").hide();
	$.ajax({
        type : "POST",
        contentType : "application/json",
        url: "getPartnerById/"+id,
        dataType:"json",
        async:false,
        success: function (data) {
        	if($.isEmptyObject(data)){
        		Alert.warn("something went wrong");
        	}else{
				$("#companypanno").val(data.vendorPancardNo);
				$("#companyemail").val(data.vendorEmailId);
				$("#comapanyname").val(data.companyName);
				$("#companymob").val(data.mobileNo);
				$("#userfirtsname").val(data.firstName);
				$("#userlastname").val(data.lastName);
				if(data.isInvitationApproved=="Y"){
					$("#initationstatus").val("Accepted");
				}else if(data.isInvitationApproved=="N"){
					$("#initationstatus").val("Pending");
				}else{
					$("#initationstatus").val("Rejected");
				}
				
				$(".Id").val(data.vendorPancardNo);
				if(data.isActive=="Y")
				if(activeStatus=="Active")
					$('.isActive').prop('checked', true);
				else
					$('.isActive').prop('checked', false);
				
				$(".detail_Name").html('Company Name: '+data.companyName);
				$(".detail_Code").html('First Name: '+data.firstName);
				$(".detail_Desc").html('Last Name: '+data.lastName);
				$(".detail_Active").html('Is Inviation Approved: '+data.isInvitationApproved);	
        	}  	
        },
        error: function (e){
        	Alert.warn("Exception :");
        }
    });
	}

function getBPDetailsResponse(data){
	debugger;
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
			$("#companypanno").attr('readonly', true);
			$("#getbpartnerforinvitation").hide();
			$("#sendinvitationmail").removeClass('readonly');
			$("#sendQuickInvitationMail").removeClass('readonly');
		}else{
			$("#companyemail").val("");
			$("#companyemail").attr('readonly', false);
			$("#comapanyname").val("");
			$("#comapanyname").attr('readonly', false);
			$("#companymob").val("");
			$("#companymob").attr('readonly', false);
			$("#userfirtsname").val("");
			$("#userfirtsname").attr('readonly', false);
			$("#userlastname").val("");
			$("#userlastname").attr('readonly', false);
			$("#initationstatus").val("");
			$("#initationstatus").attr('readonly', true);
			$("#sendinvitationmail").removeClass('readonly');
			$("#sendQuickInvitationMail").removeClass('readonly');
		}
	}else if(data.success==false){
			Alert.warn(data.message);
	}else{
			Alert.warn("Some Thing Went Wrong");
	}
}

function sendInvitationMail(event){
	debugger;
	 event.preventDefault();
	 submitIt("userdetailsforinvitation", "sendInvitationMail", 'sendInvitationResp');
}

function sendInvitationResp(data){
	if(!isEmpty(data) && !isEmpty(data.objectMap)){
		if(data.objectMap.RESULT_STATUS){
			$("#sendinvitationmail").hide();
			$("#sendQuickInvitationMail").hide();
			  $("#userdetailsforinvitation").addClass('readonly');
			  Alert.info(data.objectMap.RESULT_MESSAGE);
			  renderList();	
		}else{
			Alert.info(data.objectMap.RESULT_MESSAGE);	
		}
	}
	$("#getbpartnerforinvitation").hide();
}