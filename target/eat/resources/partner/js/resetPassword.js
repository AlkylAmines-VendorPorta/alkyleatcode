 var formPasswordForm = document.forms["changePasswordForm"];
 var elemPW = document.getElementById("password");
 var elemPWCON = document.getElementById("confirmPassword");
 var bPasswordPasses = false;
 var oldNewPasswordMatch=false;
 var validateMessage='';
 
 var regexPasswordLength = /.{8,}/; // test for at least 8 characters
 // enter your regular expression to check for an uppercase letter here
 var regexPasswordContainsUpperCase = /[A-Z]/; //test for uppercase letter
 // enter your regular expression to check for a lowercase letter here
 var regexPasswordContainsLowerCase = /[a-z]/; //test for lowercase letter
 // enter your regular expression to check for a number here
 var regexPasswordContainsNumber = /\d/; //test for number 
 // enter your regular expression to check for a special character here
var regexPasswordContainsSpecialChar = /\W/;
 
 /*var regexPasswordContainsSpecialChar = /^[!@#\$%\^\&*\)\(+=._-]+$/;//test for special character
*/ 
 
 $(document).ready(function(){
	 $("#submitBtnId").on('click',function(event){
		 debugger;
		 $("#loading-wrapper").show();
			event.preventDefault();
			if(bPasswordPasses){
				submitIt('changePasswordForm','updatePassword','changePasswordResp');
			}
			else{
				if(!oldNewPasswordMatch){
					swal('New Password should not be same as Old Password!');
				}
				else{
					swal('Password should meet All above requirements !');	
				}
			}
				
			$("#loading-wrapper").fadeOut();
			
		});
			$(".validation").on('keyup',function(event){
				if($("#password").val() == $("#confirmPassword").val()){
					$("#pwmatch").removeClass("glyphicon-remove");
					$("#pwmatch").addClass("glyphicon-ok");
					$("#pwmatch").css("color","#00A41E");
				}else{
					$("#pwmatch").removeClass("glyphicon-ok");
					$("#pwmatch").addClass("glyphicon-remove");
					$("#pwmatch").css("color","#FF0004");
				}
				event.preventDefault();
				validatePassword();
				
				
			});
	});
		
 function changePasswordResp(data)
{
	debugger;
  if(data.hasError)
	  {
	    var errorList=data.errors;
        var errorLog='';
		   $.each(errorList,function(key,value){
			   errorLog=errorLog+value.errorMessage+'\n'+',';
		       
		   });
       
	    Alert.warn(errorLog);
	  }else{
		  swal({
	          title: "Done",
	          text: "Password Has Been Changed !",
	          icon: "success",
	          type: "success",
	          dangerMode: true,
	        }).then(function(isConfirm) {
	          if (isConfirm) {
	        	  window.location.href="/eatApp/logout"; 
	          } 
	        });
	  }
}
 
/*formPasswordForm.addEventListener("submit", changePasswordResp);*/
$(function() {
	  Alert = {
	    show: function($div, msg) {
	      $div.find('.alert-msg').text(msg);
	      if ($div.css('display') === 'none') {
	        // fadein, fadeout.
	        $div.fadeIn(100).delay(1000).fadeOut(2000);
	      }
	    },
	    info: function(msg) {
	      this.show($('#alert-info'), msg);
	    },
	    warn: function(msg) {
	      this.show($('#alert-warn'), msg);
	    }
	  }
	  $('body').on('click', '.alert-close', function() {
	  	$(this).parents('.alert').hide();
	  });
	  $('#info').click(function() {
	    Alert.info('This is infomation alert This is infomation alert This is infomation alert.')
	  });
	  $('#warn').click(function() {
	    Alert.warn('This is warning alert This is infomation alert This is infomation alert This is infomation alert.')
	  });
	});
function validatePassword(form, event) {
    if (pwdValid === true) {
        form.submit();
    } else {
        $('#alert-invalid-password').removeClass('hide');
        event.preventDefault();
    }
}
function validatePassword(){
	var password=$('#changePasswordForm #password').val();
	/*var confirmPassword=$('#changePasswordForm #confirmPassword').val();*/
	debugger;
	// enter your regular expression to check for at least 8 characters here
    var lengthtest=regexPasswordLength.test(elemPW.value);
    var upperCasetest=regexPasswordContainsUpperCase.test(elemPW.value);
    var lowerCasetest=regexPasswordContainsLowerCase.test(elemPW.value);
    var noOrSpecialChartest=(regexPasswordContainsNumber.test(elemPW.value)==true &&
    		                 regexPasswordContainsSpecialChar.test(elemPW.value)==true)?true:false;
	var oldPassword=$('#changePasswordForm #oldPassword').val();	
    if (!lengthtest) {
			validateMessage='Password must be at least 8 characters.';
	        bPasswordPasses = false;
	        $('#length').removeClass('valid').addClass('invalid');
	    }
		else{
			$('#length').removeClass('invalid').addClass('valid');
		}
	    if (!upperCasetest) {
	    	validateMessage='Password must contain an uppercase character.';
	        bPasswordPasses = false;
	        $('#capital').removeClass('valid').addClass('invalid');
	    }
	    else{
	    	$('#capital').removeClass('invalid').addClass('valid');
	    	}
	    if (!lowerCasetest) {
	    	validateMessage='Password must contain an lowercase character.';
	        bPasswordPasses = false;
	        $('#lowercase').removeClass('valid').addClass('invalid');
	    }
	    else{
	    	$('#lowercase').removeClass('invalid').addClass('valid');
	    	
	    	}
	    if (!noOrSpecialChartest) {
	    	validateMessage='Password must contain a number.';
	        bPasswordPasses = false;
	        $('#number-special').removeClass('valid').addClass('invalid');
	    }
	    
	
	   /* if (!regexPasswordContainsSpecialChar.test(elemPW.value)) {
	    	validateMessage='Password must contain a special character.';         
	        bPasswordPasses = false;
	        
	    }*/
	    else{
	    	$('#number-special').removeClass('invalid').addClass('valid');
	    	
	    }
	    if ( noOrSpecialChartest
	    		&& lowerCasetest && upperCasetest && lengthtest) {
	        // looks goood
	    	bPasswordPasses = true;
	
	    }  
	    if(oldPassword==password){
	    	 bPasswordPasses = false;
	    	 oldNewPasswordMatch=false;
	    	Alert.warn("The new password cannot be the same as old password");
	    }
	    else{
	    	 oldNewPasswordMatch=true;
	    }
	
}