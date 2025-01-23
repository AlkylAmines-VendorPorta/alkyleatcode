$(document).ready(function(){
	if(!$.isEmptyObject($('#emailId').val())){
		$('.emailId').addClass("validinput");
	}
	else{
		$('.emailId').removeClass("validinput");
	}
	  $("#checkcreater").click(function(event){
		  $("#loading-wrapper").show();
		  event.preventDefault();
		  $('input:radio[class=checkregistrationtype]:nth(0)').attr('checked',true);
		  /*$("#checkparticipant").prop('disabled', true);*/
		  submitIt('newUserRegistrationForm','register', 'processResponse');
	    	
	    });
	  $("#checkparticipant").click(function(event){
		  event.preventDefault();
		  $('input:radio[class=checkregistrationtype]:nth(1)').attr('checked',true);
		/*  $("#checkcreater").prop('disabled', true);*/
		  submitIt('newUserRegistrationForm','register', 'processResponse');
	    	
	    });
});
function processResponse(data){
/*	alert (data.message);*/
	/*$("#loading-wrapper").fadeIn("slow");*/
	if(data.hasError){
		CreateCaptcha();
		var errorLog = "";
		$.each(data.errors,function(key, value){
			errorLog = errorLog+value.errorMessage+"\n";
		});
		swal(errorLog);
		
	}else{
		/*swal({ 
			   text: "User Registered successfully, Your password has been sent on your E-mail..!"
		  });
		$(".swal2-confirm").on('click',function(){
			window.location.href = "home";
			$(".swal2-confirm").empty();
		});*/
		swal({
	          title: "Done",
	          text: "User Registered successfully, Your password has been sent on your E-mail..!",
	          icon: "success",
	          type: "success",
	          dangerMode: true,
	        }).then(function(isConfirm) {
	          if (isConfirm) {
	        	  $("#newUserRegistrationForm")[0].reset();
	        	  window.location.href="/eatApp/"; 
	          } 
	        });
		/*$("#newUserRegistrationForm")[0].reset();*/
	}
}

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