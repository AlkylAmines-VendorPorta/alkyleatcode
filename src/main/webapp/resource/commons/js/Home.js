    $(document).ready(function() {
        $('ul#SmallNav li a').click(function() {
            var $this = $(this);
            $this.parent().siblings().removeClass('active').end().addClass('active');
        });
        $(".list-group-item-1").mouseover(function(event) {
            $('.loginSection').hide();
            $(".list-group-item-1").addClass('active');
            $('.bhoechie-tab-content-1').addClass("animated slideInDown active");
            $('.bhoechie-tab-content-2,.bhoechie-tab-content-3,.bhoechie-tab-content-4,.bhoechie-tab-content-5').removeClass("animated slideInDown active");
            $(".list-group-item-2,.list-group-item-3,.list-group-item-4,.list-group-item-5").removeClass('active');
        });
        $(".list-group-item-2").mouseover(function(event) {
            $('.loginSection').hide();
            $(".list-group-item-2").addClass('active');
            $('.bhoechie-tab-content-2').addClass("animated slideInDown active");
            $('.bhoechie-tab-content-5,.bhoechie-tab-content-3,.bhoechie-tab-content-4,.bhoechie-tab-content-1').removeClass("animated slideInDown active");
            $(".list-group-item-3,.list-group-item-3,.list-group-item-4,.list-group-item-1").removeClass('active');
        });
        $(".list-group-item-3").mouseover(function(event) {
            $('.loginSection').hide();
            $(".list-group-item-3").addClass('active');
            $('.bhoechie-tab-content-3').addClass("animated slideInDown active");
            $('.bhoechie-tab-content-2,.bhoechie-tab-content-5,.bhoechie-tab-content-4,.bhoechie-tab-content-1').removeClass("animated slideInDown active");
            $(".list-group-item-2,.list-group-item-5,.list-group-item-4,.list-group-item-1").removeClass('active');
        });
        $(".list-group-item-4").mouseover(function(event) {
            $('.loginSection').hide();
            $('.bhoechie-tab-content-4').addClass("animated slideInDown active");
            $(".list-group-item-4").addClass('active');
            $('.bhoechie-tab-content-2,.bhoechie-tab-content-3,.bhoechie-tab-content-5,.bhoechie-tab-content-1').removeClass("animated slideInDown active");
            $(".list-group-item-2,.list-group-item-3,.list-group-item-5,.list-group-item-1").removeClass('active');
        });
        $(".list-group-item-5").mouseover(function(event) {
            $('.loginSection').hide();
            $('.bhoechie-tab-content-5').addClass("animated slideInDown active");
            $(".list-group-item-5").addClass('active');
            $('.bhoechie-tab-content-2,.bhoechie-tab-content-3,.bhoechie-tab-content-4,.bhoechie-tab-content-1').removeClass("animated slideInDown active");
            $(".list-group-item-2,.list-group-item-3,.list-group-item-4,.list-group-item-1").removeClass('active');
        });
        $('.LoginLink').click(function() {
            $('.loginSection').show();
            $("div.bhoechie-tab>div.bhoechie-tab-content").removeClass("active");
            $(".list-group-item").removeClass("active");
        });
        $("div.bhoechie-tab-menu>div.list-group>a").click(function(e) {
            e.preventDefault();
            $(this).siblings('a.active').removeClass("active");
            $(this).addClass("active");
            var index = $(this).index();
            $("div.bhoechie-tab>div.bhoechie-tab-content").removeClass("active");
            $("div.bhoechie-tab>div.bhoechie-tab-content").eq(index).addClass("active");
        });
        $(".RemoveTab").click(function(){
            $("div.bhoechie-tab>div.bhoechie-tab-content").removeClass("active");
            $(".list-group-item").removeClass("active");
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


 var alpha = new Array('A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z','a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z');
 var i;
 for (i=0;i<6;i++){
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


