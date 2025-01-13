<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@taglib uri = "http://www.springframework.org/tags/form" prefix = "sf"%>
	
<meta http-equiv="refresh" content="<%=session.getMaxInactiveInterval()%>;url=./"/>

<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/${appMode}/tilescommon/css/captcha.css?appVer=${appVer}?appVer=${appVer}"/>

<body class="bodybackground">
 <div class="container-fluid nopadding">
 <div class="main-content">
  <div class="col-sm-8 position-relative">
  <input type="hidden" id="rootContext" value="<%=request.getContextPath()%>" />			
  <div class="position-absolute">
   	 <div class="tilescol">
  		<a href="latestAuctionForward">
  			<div class="tiles">
  			   <h4>Forward Auction</h4>
  			   <span class="leftFont setFACount"></span>
  			   <i class="fa fa-chevron-circle-right font36"></i>
  			</div>
  		</a>
  	</div>
  	<div class="tilescol">
  		<a href="latestAuctionReverse">
  			<div class="tiles">
  			   <h4>Reverse Auction</h4>
  			    <span class="leftFont setRACount"></span>
  			   <i class="fa fa-chevron-circle-left font36"></i>
  			</div>
  		</a>
  	</div>
  	<div class="tilescol">
  		<a href="latestTendersWorks">
  			<div class="tiles">
  			   <h4>Latest Tenders Service</h4>
  			    <span class="leftFont">65</span>
  			   <i class="fa fa-clock-o font36"></i>
  			</div>
  		</a>
  	</div>
  	<div class="tilescol">
  		<a href="latestTendersProcurement">
  			<div class="tiles">
  			   <h4>Latest Tenders Material</h4>
  			    <span class="leftFont">33</span>
  			   <i class="fa fa-id-card-o font36"></i>
  			</div>
  		</a>
  	</div>
  	<div class="tilescol">
  		<a href="latestAnnouncements">
  			<div class="tiles">
  			   <h4>Announcements</h4>
  			    <span class="leftFont">0</span>
  			   <i class="fa fa-bullhorn font36"></i>
  			</div>
  		</a>
  	</div>
  	<div class="tilescol">
  		<a href="blacklistedVendors">
  			<div class="tiles">
  			   <h4>Black Listed Vendor</h4>
  			    <span class="leftFont">7</span>
  			   <i class="fa fa-question-circle font36"></i>
  			</div>
  		</a>
  	</div>
  	<div class="tilescol">
  		<a href="registeredVendors">
  			<div class="tiles">
  			   <h4>Registred Vendor</h4>
  			    <span class="leftFont">8</span>
  			   <i class="fa fa-registered font36"></i>
  			</div>
  		</a>
  	</div>
  	<div class="tilescol">
  		<a href="blacklistedCustomer">
  			<div class="tiles">
  			   <h4>Black Listed Customer</h4>
  			    <span class="leftFont">11</span>
  			   <i class="fa fa-exclamation-circle font36"></i>
  			</div>
  		</a>
  	</div>
  	<div class="tilescol">
  		<a href="registeredCustomer">
  			<div class="tiles">
  			   <h4>Registred Customer</h4>
  			    <span class="leftFont">9</span>
  			   <i class="fa fa-check-square-o font36"></i>
  			</div>
  		</a>
  	</div>
  	<div class="tilescol">
  		<a href="download">
  			<div class="tiles">
  			   <h4>Download</h4>
  			    <span class="leftFont">26</span>
  			   <i class="fa fa-download font36"></i>
  			</div>
  		</a>
  	</div>
  	</div>
  </div>
  <div class="col-sm-4 rightLogin">
  	<div class="login-box">
  	<div id="loginDIV">
  	<sf:form id="ajax-login-form" name="ajax-login-form" action="/api/auth/signin" modelAttribute="user" method="post" role="form" autocomplete="off">
  		<div class="form-group">
  			<p class="loginheading">LOGIN</p>
  		</div>
  		<div class="form-group">
  			<label>User Name</label>
  			<input type="text" id="username" name="usernameOrEmail" autocomplete="nope" class="form-control brdraduis" required>
  		</div>
  		<div class="form-group">
  			<label>Password</label>
  			<input type="password" class="form-control brdraduis" id="password" name="password" autocomplete="new-password" required>
  		</div>
  		<div class="form-group">
			<div class='CaptchaWrap'>
		      <div id="CaptchaImageCode" class="CaptchaTxtField">
		        <canvas id="CapCode" class="capcode" width="300" height="80"></canvas>
		      </div> 
		      <input type="button" class="ReloadBtn" onclick='CreateCaptcha();'>
		    </div>
       </div>
       <div class="form-group">
       		 <label>Type the above Text</label>
       		<input type="text" id="UserCaptchaCode" class="form-control brdraduis" name = "UserCaptchaCode" required />
       </div>
        <div class="form-group">
               <!--  <input name="login-submit"  id="login-submit" tabindex="4" class="form-control btn btn-info formSubmit" value="Log In"> -->
               <button class="btn btn-info login-submit" onclick="return submitIt('ajax-login-form','api/auth/signin','processResponse'); CheckCaptcha();">Log In</button>
        </div>
         <div class="form-group">
             <div class="col-xs-5 no-padding">
                    <a href="registration" class="loginBtn whitetext">New User ?</a>
                 </div> 
                <div class="col-xs-7 no-padding text-right">
                   <a href="javascript:void(0)" class="forgot-password whitetext">Forgot Password?</a>
                </div> 
             <!-- <div class="no-padding text-right">
                <a href="javascript:void(0)" class="forgot-password whitetext">Forgot Password?</a>
            </div> -->
          </div>
      </sf:form>
      </div>   
          <div class="clearfix"></div>
          <div id="ForgotPassDIV">
               <div class="form-group">
                <sf:form id="forgotPassForm" modelAttribute="user"> 
                      <label>Email</label>
				      <input type="text" class="form-control" id="emailFP" name="email" required />				     
			    </sf:form>  
			 </div>			
		     <div class="clearfix"></div>
		       <div class="form-group" style="margin-top:10px;">
                    <button type="button" class="btn btn-info submitForgotPassword">Submit</button>
                    <button type="button" class="btn btn-info cancelForgotPassDIV">Cancel</button>                       
               	</div>
              </div>
  	</div>
  </div>
  </div>
        </div>
        </body>
        <script src="resources/${appMode}/commons/js/jquery-3.2.1.min.js?appVer=${appVer}"></script>
        <script	src="<%=request.getContextPath()%>/resources/${appMode}/commons/js/springform.js?appVer=${appVer}"></script>
        <script src="resources/${appMode}/commons/js/commonValidation.js?appVer=${appVer}"></script> 
        <script src="resources/${appMode}/commons/js/formFields.js?appVer=${appVer}"></script>
        <script src="resources/${appMode}/transaction/js/home.js?appVer=${appVer}"></script>
        <script src="resources/${appMode}/commons/js/utility.js?appVer=${appVer}"></script> 
        <script src="resources/${appMode}/commons/js/captcha.js?appVer=${appVer}"></script>
        <script>
        $(document).ready(function(){
        	$('.forgot-password').click (function(){
        		$('#ForgotPassDIV').show();
        		$('#loginDIV').hide();
        	});
        	$('.cancelForgotPassDIV').click (function(){
        		$('#ForgotPassDIV').hide();
        		$('#loginDIV').show();
        	});
        	    check_capslock_form($('#ajax-login-form')); //applies the capslock check to all input tags
        	  
        	
        });
        document.onkeydown = function (e) { //check if capslock key was pressed in the whole window
        	  e = e || event;
        	  if (typeof (window.lastpress) === 'undefined') { window.lastpress = e.timeStamp; }
        	  if (typeof (window.capsLockEnabled) !== 'undefined') {
        	    if (e.keyCode == 20 && e.timeStamp > window.lastpress + 50) {
        	      window.capsLockEnabled = !window.capsLockEnabled;
        	      $('#capslockdiv').toggle();
        	    }
        	    window.lastpress = e.timeStamp;
        	    //sometimes this function is called twice when pressing capslock once, so I use the timeStamp to fix the problem
        	  }

        	};

        	function check_capslock(e) { //check what key was pressed in the form
        	  var s = String.fromCharCode(e.keyCode);
        	  if (s.toUpperCase() === s && s.toLowerCase() !== s && !e.shiftKey) {
        	    window.capsLockEnabled = true;
        	    $('#capslockdiv').show();
        	  }
        	  else {
        	    window.capsLockEnabled = false;
        	    $('#capslockdiv').hide();
        	  }
        	}

        	function check_capslock_form(where) {
        	  if (!where) { where = $(document); }
        	  where.find('input,select').each(function () {
        	    if (this.type != "hidden") {
        	      $(this).keypress(check_capslock);
        	    }
        	  });
        	}
        </script>