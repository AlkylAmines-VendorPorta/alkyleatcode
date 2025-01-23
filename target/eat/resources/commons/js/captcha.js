 var cd;

 $(function(){
   CreateCaptcha();
 });

 // Create Captcha
 function CreateCaptcha() {	
   //$('#InvalidCapthcaError').hide();
  /* var alpha = new Array('1','2','3','4','5','6','7','8','9','0');
                     
   var i;
   for (i = 0; i < 6; i++) {
     var a = alpha[Math.floor(Math.random() * alpha.length)];
     var b = alpha[Math.floor(Math.random() * alpha.length)];
     var c = alpha[Math.floor(Math.random() * alpha.length)];
     var d = alpha[Math.floor(Math.random() * alpha.length)];
     var e = alpha[Math.floor(Math.random() * alpha.length)];
     var f = alpha[Math.floor(Math.random() * alpha.length)];
   }
   cd = a + ' ' + b + ' ' + c + ' ' + d ;*/
	 
   var genCaptcha = fetchList('createCaptcha',null).message;
   $('#CaptchaImageCode').empty().append('<canvas id="CapCode" class="capcode" width="300" height="80"></canvas>')
   
   var c = document.getElementById("CapCode"),
       ctx=c.getContext("2d"),
       x = c.width / 2,
       img = new Image();
   var rootContex=$('#rootContext').val();
   img.src = rootContex+"/resources/commons/images/captcha_bg.jpg";
   img.onload = function () {
       var pattern = ctx.createPattern(img, "repeat");
       ctx.fillStyle = pattern;
       ctx.fillRect(0, 0, c.width, c.height);
       ctx.font="46px Roboto Slab";
       ctx.fillStyle = '#ccc';
       ctx.textAlign = 'center';
       ctx.setTransform (1, -0.12, 0, 1, 0, 15);
       ctx.fillText(genCaptcha,x,55);
   };
   
   
 }

 // Validate Captcha
 function ValidateCaptcha() {
   var string1 = removeSpaces(cd);
   var string2 = removeSpaces($('#UserCaptchaCode').val());
   if (string1 == string2) {
     return true;
   }
   else {
     return false;
   }
 }

 // Remove Spaces
 function removeSpaces(string) {
   return string.split(' ').join('');
 }

 // Check Captcha

 function CheckCaptcha() {
	
   var result = ValidateCaptcha();
   if( $("#UserCaptchaCode").val() == "" || $("#UserCaptchaCode").val() == null || $("#UserCaptchaCode").val() == "undefined") {
     $('#WrongCaptchaError').text('Please enter Captcha.').show();
     $('#UserCaptchaCode').focus();
   } else {
     if(result == false) { 
       $('#WrongCaptchaError').text('Invalid Captcha!.').show();
       CreateCaptcha();
       $('#UserCaptchaCode').focus().select();
     }
     else { 
       $('#UserCaptchaCode').val('').attr('place-holder','Enter Captcha - Case Sensitive');
       CreateCaptcha();
       $('#WrongCaptchaError').fadeOut(100);
       $('#SuccessMessage').fadeIn(500).css('display','block').delay(5000).fadeOut(250);
     }
   }  
 }


