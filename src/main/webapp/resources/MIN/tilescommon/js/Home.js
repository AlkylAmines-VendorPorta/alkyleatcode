 $(document).ready(function() {
	
	 $('.LoginLink').click(function() {
            $('.loginSection').show();
            $('.homeSection').hide();
            $('.HomeLink').removeClass('active');
            $('.LoginLink').addClass('active');
        });
        

    });


 /* Captcha validation Script */

 function checkform(){
 var captchaValue = "";

 if(CaptchaInput.value == ""){
 captchaValue += "Please Enter CAPTCHA Code.\n";
 $('#errorMsg').html(" Please Enter CAPTCHA Code.")
 }
 if(CaptchaInput.value != ""){
 if(ValidCaptcha(CaptchaInput.value) == false){
 captchaValue += "The CAPTCHA Code Does Not Match.\n";
 }
 }
 if(captchaValue != ""){
 $('#errorMsg').html("The CAPTCHA Code Does Not Match.")
 /* return false; */
 $('#login-submit').attr("disabled", true);
 }
 }


 var alpha = new Array('1','2','3','4','5','6','7','8','9','0');
 var i;
 for (i=0;i<4;i++){
   var a = alpha[Math.floor(Math.random() * alpha.length)];
   var b = Math.ceil(Math.random() * 9)+ '';
   var c = alpha[Math.floor(Math.random() * alpha.length)];
   var d = Math.ceil(Math.random() * 9)+ '';
   var e = Math.ceil(Math.random() * 9)+ '';
   var f = alpha[Math.floor(Math.random() * alpha.length)];
   var g = Math.ceil(Math.random() * 9)+ '';
 }
   var code = a + ' ' + b + ' ' + ' ' + c + ' ' + d + ' ' + e + ' '+ f + ' ' + g;

 document.getElementById("txtCaptcha").value = code;
 document.getElementById("CaptchaDiv").innerHTML = code;

 /* Validate input against the generated number */
 function ValidCaptcha(){
 var str1 = removeSpaces(document.getElementById('txtCaptcha').value);
 var str2 = removeSpaces(document.getElementById('CaptchaInput').value);
 if (str1 == str2){
 return true;
 }else{
 return false;
 }
 }

 /* Remove the spaces from the entered and generated code */
 function removeSpaces(string){
 return string.split(' ').join('');
 }
 /* Captcha validation Script */

 /* captcha function call on login button */
 $("#login-submit").click(function(){
 	checkform();
 	});
 	
 /* captcha function call on login button */


 $("#CaptchaInput").click(function(){
 	$('#login-submit').attr("disabled", false);
 	});


