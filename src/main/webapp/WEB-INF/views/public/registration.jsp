<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page isELIgnored="false" %>
        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
            <%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
                <%@taglib uri = "http://www.springframework.org/tags/form" prefix = "sf"%>                
                <link rel="stylesheet" href="<%=request.getContextPath()%>/resources/${appMode}/tilescommon/css/captcha.css?appVer=${appVer}"/>
                <style>#CaptchaImageCode{    width: 47%;}.ReloadBtn{left:50%;} #Full-page{background: transparent;} input, textarea, select{    background: transparent;}
               @media only screen and (max-width: 768px) { .ReloadBtn {
    left: 50%;
    top: 2px;
}}
                </style>
				<body class="tiles_background">
				 <input type="hidden" id="rootContext" value="<%=request.getContextPath()%>" />		
                    <div class="container-fluid slidepadding">
                        <section class="center-section">
                        <div class="col-sm-6 mobdisplayNone">
                        	<div class="form-group">
                        		<div class="col-sm-12">
                        			<ul class="regiul"><li>Effective Operation</li>
                        			<li>Competitive advantage</li>
                        			<li>Better supply chain strategies</li>
                        			<li>Increased customer satisfaction</li>
                        			<li>Improved supplier management</li>
                        			</ul>
                        		</div>
                        	</div>
                        </div>
                            <div class="col-sm-6 mobileNoPadding cardshodow">
                                <sf:form id="newUserRegistrationForm" action="register" modelAttribute="user" method="POST">
                                    <span id="errorMsg">
                            <c:if test="${not empty errorMsg}">
							   <c:out value="${errorMsg}"/>
							   
							</c:if></span>
								<div class="form-group" style="display:none;">
								<input type="hidden"  name="userDetails.userDetailType" value="COMPUSR"/>
								<input name="partner.registrationType" />
                                        <div class="col-sm-12">
                                            <label class="radio-inline">
                                                <input type="radio" class="checkregistrationtype" style="margin-left:-160px;" name="partner.registrationType" value="CREATER"><b>Register to create reverse/forward Auction</b></label>
                                        </div>
                                        <div class="clearfix"></div>
                                        <div class="col-sm-12"  style="margin-top:20px;">
                                            <label class="radio-inline">
                                                <input type="radio"  class="checkregistrationtype" style="margin-left:-182px;" name="partner.registrationType" value="PARTICIPANT"><b>Register to participate in reverse/forward Auction</b></label>
                                        </div>
                                        </div>
                                    <div class="form-group">
                                        <div class="col-sm-6">
                                            <div class="styled-input">
                                                <input type="text" id="emailId" class="emailaddress requiredField" name="email" autocomplete="nope" value="" oninput="this.value = this.value.toLowerCase()" required/>
                                                <label class="emailId">Company Email Id<span class="red">*</span></label>
                                                <span></span> </div>
                                        </div>

                                        <!-- <div class="col-sm-6 ">
                                            <div class="styled-input">
                                                <input type="password" id="password" name="password" required />
                                                <label>Password<span class="red">*</span></label>
                                                <span></span> </div>
                                        </div> -->
										
                                        <!-- <div class="col-sm-6 "  >
							         <div class="styled-input">
							      <input type="password" id="confirmPswd" name="confirmPswd"  required/>
							      <label>Confirm Password<span class="red">*</span> 
							      <img alt="" class="okTick" src="images/ok_mark_clip_art.jpg" width="5%" style="display: none">
							      </label>
							      <span></span> </div></div> -->
                                    </div>
                                    <div class="form-group">
                                        <div class="col-sm-6" style="margin-top: 40px;">
                                            <label class="checkbox-inline">
                                                <input type="checkbox" name="partner.isGstApplicable" value="Y" onchange="gstnumShow()" id="isGstApplicable" style="margin-left: -85px;">Is GSTN Applicable</label>
                                        </div>
                                        <div class="col-sm-6 GSTidenNumb">
                                            <div class="styled-input">
                                                <input type="text" id="gstinNo" class="gstIn" name="partner.gstinNo" oninput="this.value = this.value.toUpperCase()"
                                                value="" maxlength="15" disabled required>
                                                <label>GST Number<span class="red">*</span></label>
                                                <span></span>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="clearfix"></div>
                                    <div class="form-group">
                                        <div class="col-sm-6">
                                            <div class="styled-input">
                                                <input type="text" class="requiredField" id="companyName" name="partner.name" maxlength="50" required />
                                                <label>Company Name<span class="red">*</span></label>
                                                <span></span> </div>
                                        </div>
                                        <div class="col-sm-6 ">
                                            <div class="styled-input">
                                                <input type="text" class="requiredField" id="crnNumber" name="partner.crnNumber" 
                                                maxlength="21" oninput="this.value = this.value.toUpperCase()" required />
                                                <label>Company Registration Number<span class="red">*</span></label>
                                                <span></span> </div>
                                        </div>
									</div>
									<div class="form-group">
                                        <div class="col-sm-6 ">
                                            <div class="styled-input">
                                                <input type="text"class="requiredField panNumber"  id="panNumber" name="partner.panNumber" oninput="this.value = this.value.toUpperCase()" maxlength="10" required />
                                                <label>Pan Number<span class="red">*</span></label>
                                                <span></span> </div>
                                        </div>
                                                                                
                                    

                                    
                                        <div class="col-sm-6">
                                            <div class="styled-input">
                                                <input type="text" class="requiredField mobile" id="Mobile" name="userDetails.mobileNo" maxlength="10" required />
                                                <label>Mobile Number<span class="red">*</span></label>
                                                <span></span> </div>
                                        </div>
                                        <div class="col-sm-6">
                                            <div class="styled-input">
                                                <input type="text" class="" id="fname" name="userDetails.firstName"  autocomplete="nope" required />
                                                <label>First Name</label>
                                                <span></span> </div>
                                        </div>
                                        <div class="col-sm-6">
                                            <div class="styled-input">
                                                <input type="text" id="lname" class="" name="userDetails.lastName"  autocomplete="nope"  required/>
                                                <label>Last Name<span></span></label>
                                                <span></span> </div>
                                        </div>
                                        
                             </div>
                             <div class="clearfix"></div>
                                <div class="col-sm-12">
									<div class="form-group">
																		
									
									    <div class='CaptchaWrap'>
									      <div id="CaptchaImageCode" class="CaptchaTxtField">
									        <canvas id="CapCode" class="capcode" width="300" height="80"></canvas>
									      </div> 
									      <input type="button" class="ReloadBtn" onclick='CreateCaptcha();'>
									    </div>
									        <div class="form-group">
			                               <div class="styled-input">
										      <input type="text" id="UserCaptchaCode"  required name = "userDetails.userCaptchaCode" />
										      <label>Type the above Text</label>
										      <span></span> </div>
			                             </div>
			                          </div>
			                          </div>
                                    <div class="clearfix"></div>
                                    <div class="form-group ">
                                        <div class="col-sm-12 text-left">
                                            By clicking Register, you agree to our
                                            <a href=""> <b>Terms </b></a> and confirm that you have read our Data Policy.
                                        </div>
                                    </div>

                                    <div class="clearfix"></div>
                                    <div class="form-group">
                                    <label class="col-sm-3">Register As</label>
                                    <div class="col-sm-9">
                                    	
										  <button class="btn btn-primary clickRegistration" id="checkcreater">Partner To Create Auction </button>
										  <button class="btn btn-primary clickRegistration" id="checkparticipant">Participant in Auction </button>
										
										</div>
                                    </div>
                                    <div class="clearfix"></div>
                                    <div class="form-group " style="margin-bottom:10px; margin-top:10px;">
                                        <div class="col-sm-12 text-center">
                                           <!--  <button class="btn-all btn btn-info formSubmit">Register</button> -->
                                           <!-- <button class="btn-all btn btn-info" id="formregistration" onclick="return submitIt('newUserRegistrationForm','register', 'processResponse');">Register</button> -->
                                            <a href="home" class="btn btn-info"> Cancel</a>
                                        </div>
                                    </div>

                                    <div class="clearfix"></div>
                                </sf:form>
                            </div>
                        </section>
                        <div class="clearfix"></div>
                        <br>
                    </div>
                    
                    
                    </body>
                    <div class="clearfix"></div>
                    <script src="resources/${appMode}/commons/js/jquery-3.2.1.min.js?appVer=${appVer}"></script>
                    <script src="resources/${appMode}/master/js/registration.js?appVer=${appVer}"></script>
                    <script src="resources/${appMode}/commons/js/formFields.js?appVer=${appVer}"></script>
                    <script src="resources/${appMode}/commons/js/captcha.js?appVer=${appVer}"></script>
                    <script src="<%=request.getContextPath()%>/resources/${appMode}/commons/js/commonValidation.js?appVer=${appVer}"></script>
                    <script>
                        function gstnumShow() {
                            if ($('#isGstApplicable').is(":checked")) {
                                $(".GSTidenNumb").show();
                                $("#gstinNo").removeAttr('disabled');
                                $("#gstinNo").addClass('requiredField');
                            } else {
                                $(".GSTidenNumb").hide();
                                $("#gstinNo").val("");
                                $("#gstinNo").attr('disabled','disabled');
                                $("#gstinNo").removeClass('requiredField');
                            }
                        }
                    </script>