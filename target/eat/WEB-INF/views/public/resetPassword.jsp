<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@taglib uri = "http://www.springframework.org/tags/form" prefix = "sf"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<link rel="stylesheet" href="<%=request.getContextPath()%>/resource/css/bootstrap-datepicker.css?appVer=${appVer}">
<style>
.table-condensed thead, .table-condensed tbody {border-bottom: none;}
.table-condensed tbody tr td, .table-condensed thead tr td{padding:5px !important;}
.table-condensed tbody tr{border-bottom:0px;}
.form-group { margin-bottom: 10px;}
.btn-primary span{margin-right:5px;}

</style>
<style>
.btn-primary {
  background-color: #fa1954;
  border: 0;
  margin: 10px 0;
}
.btn-primary:hover {
  background-color: #db053d;
}
.form-group.error input {
  border-color: #ee4141;
  box-shadow: inset 0 1px 1px rgba(0, 0, 0, .075), 0 0 8px rgba(238, 65, 65, .4);
}
.form-group.error label.error {
  margin-top: 5px;
  color: #ee4141;
}
#password-info {
  margin: 0px 0;
  overflow: hidden;
  text-shadow: 0 1px 0 #fff;
}
#password-info ul {
  list-style: none;
  margin: 0;
  padding: 0;
}
#password-info ul li {
  padding: 10px 10px 10px 50px;
  margin-bottom: 1px;
  background: #f4f4f4;
  font-size: 12px;
  transition: 250ms ease;
  position: relative;
}
#password-info ul li .icon-container {
  display: block;
  width: 40px;
  background: #92bce0;
  position: absolute;
  top: 0;
  bottom: 0;
  left: 0;
  text-align: center;
}
#password-info ul li .icon-container .glyphicon {
  color: white;
  padding-top: 10px;
  position: relative;
  top: 2px;
}
#password-info ul li .tip {
  color: #5ca6d5;
  text-decoration: underline;
}
#password-info ul li.valid {
  color: #129652;
}
#password-info ul li.valid .icon-container {
  background-color: #18c36b;
}
#password-info ul li span.invalid {
  color: #ff642e;
}

</style>
<div id="contextVal"><%=request.getContextPath()%></div>
 <div class="container-fluid slidepadding">
        <section class="center-section">
         <div class="col-sm-12 nopasspad">
            <div class="col-sm-12 nopasspad">
               	<div class="col-sm-6 col-sm-offset-3 nopasspad">
            		<div class="panel panel-primary">
            		
				      <div class="panel-heading">Reset Password</div>
				       <sf:form id="changePasswordForm" name="changePasswordForm" modelAttribute="userDto" method="POST"> 
				      <div class="panel-body ">
				     
				      	 <div class="form-group">
				      	   <div class="col-sm-12" id="password-info">
				      	    <ul>
					                    <li id="length" class="invalid clearfix">
					                      <span class="icon-container">
					                        <i class="glyphicon glyphicon-ok" aria-hidden="true"></i>
					                      </span>
					                      At least 8 characters
					                    </li>
					                    <li id="capital" class="invalid clearfix">
					                      <span class="icon-container">
					                        <i class="glyphicon glyphicon-ok" aria-hidden="true"></i>
					                      </span>
					                      At least 1 uppercase letter
					                    </li>
					                    <li id="lowercase" class="invalid clearfix">
					                      <span class="icon-container">
					                        <i class="glyphicon glyphicon-ok" aria-hidden="true"></i>
					                      </span>
					                      At least 1 lowercase letter
					                    </li>
					                    <li id="number-special" class="invalid clearfix">
					                      <span class="icon-container">
					                        <i class="glyphicon glyphicon-ok" aria-hidden="true"></i>
					                      </span>
					                      At least 1 number & <span title="&#96; &#126; &#33; &#64; &#35; &#36; &#37; &#94; &#38; &#42; &#40; &#41; &#43; &#61; &#124; &#59; &#58; &#39; &#34; &#44; &#46; &#60; &#62; &#47; &#63; &#92; &#45;" class="special-characters tip">special character</span>
					                    </li>
					                  </ul>
				      	   	
				      	   </div>
				      	   <div class="col-sm-10 col-sm-offset-1">
				      	   <div class="col-sm-12">
				      			<div class="styled-input">
							      <input type="password" id="oldPassword" name="oldPassword" required>
							      <label>Old Password<span>*</span></label>
							      <span></span>
							      </div>
				      		</div>
				      		<div class="clearfix"></div>
				      		<div class="col-sm-12">
				      			<div class="styled-input">
							      <input type="password" class="validation" id="password" name="password" required>
							      <label>New Password<span>*</span></label>
							      <span></span>
							      </div>
				      		</div>
				      		<div class="clearfix"></div>
				      		<div class="col-sm-12">
				      			<div class="styled-input">
							      <input type="password" class="validation"  id="confirmPassword" name="confirmPassword" required>
							      <label>Confirm Password<span>*</span></label>
							      <span></span>
							      </div>
							      <label><span id="pwmatch" class="glyphicon glyphicon-remove" style="color:#FF0004;"></span> Passwords Match</label>
				      		</div>
				      		<div class="clearfix"></div>
				      		</div>
				      	 </div>	 
				      	 
				      	
				      	 <div class="form-group">
				      	 <div class="col-sm-12 text-center">
				      	 <div class="styled-input">
				      	 	<!-- <button type="submit" class="btn btn-primary" onclick="return submitIt('changePasswordForm','updatePassword','changePasswordResp');">Submit</button> -->
				      	 	<button type="submit" class="btn btn-primary" id="submitBtnId">Submit</button>
				      	 	</div>
				      	 	</div>
				      	 </div> 
				      </div>
				       </sf:form>
				       <!-- <div class="panel-body">
				       		<div id="page">   
					          <div class="pod">            
					            <form class="validate-password" method="post" action="#">
					              <fieldset class="fieldset-password">
					                <div id="alert-invalid-password" class="alert alert-danger hide">Please enter a valid password</div>
					                <div id="password-info">
					                  <ul>
					                    <li id="length" class="invalid clearfix">
					                      <span class="icon-container">
					                        <i class="glyphicon glyphicon-ok" aria-hidden="true"></i>
					                      </span>
					                      At least 8 characters
					                    </li>
					                    <li id="capital" class="invalid clearfix">
					                      <span class="icon-container">
					                        <i class="glyphicon glyphicon-ok" aria-hidden="true"></i>
					                      </span>
					                      At least 1 uppercase letter
					                    </li>
					                    <li id="lowercase" class="invalid clearfix">
					                      <span class="icon-container">
					                        <i class="glyphicon glyphicon-ok" aria-hidden="true"></i>
					                      </span>
					                      At least 1 lowercase letter
					                    </li>
					                    <li id="number-special" class="invalid clearfix">
					                      <span class="icon-container">
					                        <i class="glyphicon glyphicon-ok" aria-hidden="true"></i>
					                      </span>
					                      At least 1 number or <span title="&#96; &#126; &#33; &#64; &#35; &#36; &#37; &#94; &#38; &#42; &#40; &#41; &#43; &#61; &#124; &#59; &#58; &#39; &#34; &#44; &#46; &#60; &#62; &#47; &#63; &#92; &#45;" class="special-characters tip">special character</span>
					                    </li>
					                  </ul>
					                </div>
					                <div class="form-group password-group">
					                    <label for="input-password">Old Password:</label>
					                    <input type="password" id="oldPassword" name="oldPassword" class="required form-control" autocomplete="off">
					                </div>
					                <div class="form-group password-group">
					                    <label for="input-password">New Password:</label>
					                    <input type="password" id="input-password" name="passwordMasked" class="required form-control" autocomplete="off"/>
					                </div>
					                <div class="form-group password-group">
					                    <label for="input-password-check">Re-enter Password:</label>
					                    <input type="password" id="input-password-check" name="passwordCheckMasked" class="required form-control" autocomplete="off"/>
					                </div>
					              </fieldset>
					              <div class="form-actions clearfix text-center">
					                  <button type="submit" class="btn btn-primary">Submit</button>
					              </div>
					            </form>
					    </div>
					</div>
				       </div> -->
				    </div>
            	</div>
            </div>
           </div> 
        </section> 
        </div>
<script src="<%=request.getContextPath()%>/resources/${appMode}/tilescommon/js/jquery-3.2.1.min.js?appVer=${appVer}"></script>
<script src="<%=request.getContextPath()%>/resources/${appMode}/partner/js/resetPassword.js?appVer=${appVer}"></script>
<script src="<%=request.getContextPath()%>/resources/${appMode}/commons/js/commonValidation.js?appVer=${appVer}"></script>