
$(document).ready(function(){
    $(".okTick").hide();   
});

$("#confirmPswd").on('blur', function () {
	var pass1 = document.getElementById("password").value;
    var pass2 = document.getElementById("confirmPswd").value;
	
		 
	     if (pass1 != pass2) {
	         $('#errorMsg').html(" Passwords Do not match.")
	         document.getElementById("password").style.borderColor = "#E34234";
	         document.getElementById("confirmPswd").style.borderColor = "#E34234";
	         $(".okTick").hide();
	     }
	     else{
	    	 $('#errorMsg').html("")
	    	 document.getElementById("password").style.borderColor = "#C0BFBF";
	         document.getElementById("confirmPswd").style.borderColor = "#C0BFBF";
	         $(".okTick").show();
	     }
		
	   
    });
var email="";
var company="";
var crn="";
var pan="";
var phone="";
var fax="";
var mobile="";
var validate=false;
function bufferValues()
{
	email=$('#emailId').val();
	company=$('#name').val();
	crn=$('#crn').val();
	pan=$('#pan').val();
	phone=$('#phoneno1').val();
	fax=$('#fax1').val();
	mobile=$('#mobileno').val();
	
	}

function validateForm()
{
	debugger;
	var selectelement="";
	var emailpttrn=/^([\w-\.]+@([\w-]+\.)+[\w-]{2,4})?$/;
	var panpttrn=new RegExp('[A-Z]{5}[0-9]{4}[A-Z]{1}');
	var filter = /^[0-9-+]+$/;

	selectelement=$('#emailId').val();
	alert(selectelement.toLowerCase);
	if(selectelement.trim()!="" && emailpttrn.test(selectelement))
		{
			$('#errorMsg').html("");
			validate=true;
		}
	else 
		{
			$('#errorMsg').html("Enter Correct Email")
			return;
		}
	selectelement=$('#pan').val();
	if(selectelement.trim()!="" && panpttrn.test(selectelement.toUpperCase()))
	{
		$('#errorMsg').html("");
		validate=true;
	}
	else 
		{
			$('#errorMsg').html("Enter Correct Pan No")
			return;
		}
	selectelement=$('#phoneno1').val();
	if(selectelement.trim().length==11 && filter.test(selectelement))
	{
		$('#errorMsg').html("");
		validate=true;
	}
	else 
		{
			$('#errorMsg').html("Enter Correct Telephone No")
			return;
		}
	selectelement=$('#fax1').val();
	if(selectelement.trim().length==10 && filter.test(selectelement))
	{
		$('#errorMsg').html("");
		validate=true;
	}
	else 
		{
			$('#errorMsg').html("Enter Correct Fax No")
			return;
		}
	selectelement=$('#mobileno').val();
	if(selectelement.trim().length==10 && filter.test(selectelement))
	{
		$('#errorMsg').html("");
		validate=true;
	}
	else 
		{
			$('#errorMsg').html("Enter Correct Mobile No")
			return;
		}
		
	$('#regformId :input[type=text]').each(function(){
		var fieldname=$(this).attr("name");
	   if($(this).val().trim()!="" ){
		   validate=true;
	    }else 
	    {
	    	$('#errorMsg').html(fieldname+" is required")
	    	validate=false;
	    	return;}
	   return;
	 });
	$('#regformId :input[type=password]').each(function(){
		var fieldname=$(this).attr("name");
	   if($(this).val().trim()!=""){
		   validate=true;
	    }else 
	    {
	    	$('#errorMsg').html(fieldname+" is required")
	    	validate=false;
	    	return;}
	   return;
	 });
		
	}
function registration()
 {
	debugger;
	validateForm();
	 if(validate)
		 {
		 var userObj={};
		 userObj.emailId=$('#emailId').val();
		 userObj.mobileno=$('#mobileno').val();
		 userObj.phoneno1=$('#phoneno1').val();
		 userObj.password=$('#password').val();
		 userObj.fax1=$('#fax1').val();
		 
		 userObj.partner={};
		 userObj.partner.crn=$('#crn').val();
		 userObj.partner.pan=$('#pan').val();
		 userObj.partner.name=$('#name').val();
		 console.log(userObj);
		 bufferValues();
		 $.ajax({
		        type : "POST",
		        data : JSON.stringify(userObj),
		        contentType : "application/json",
		        url: "registrationProcess",
		        dataType:"json",
		        async:false,
		        success: function (data) {
		        	if(data.RESULT=="SUCCESS")
		        		{
			        		$('#msgId').removeClass('hide');
			        		$('#successlabelId').removeClass('hide');
			        		$('#msgId').val(data.MSG);
			        		$("#regformId").hide();
				        	
			        		alert(data.MSG);
		        		}
		        	else
		        	{
		        		alert(data.MSG);
		        		$('#msgId').removeClass('hide');
		        		$('#msgId').html(data.MSG);
		        		
		        	}
		        },
		        error: function (e) {
					alert(e.message);
		        }
		    });
		 }
	 
 }