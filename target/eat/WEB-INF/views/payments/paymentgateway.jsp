<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@taglib uri = "http://www.springframework.org/tags/form" prefix = "sf"%>
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/${appMode}/tilescommon/css/fioristyle.css?appVer=${appVer}"/>

       <body class="tiles_background">   
       <input type = "hidden" value = "<%= request.getParameter("request_id") %>" id = "request_id">   
        <div class="full-container" style="margin-top:80px;">
            <div class="col-sm-4 col-sm-offset-4">
            	<div style="    background: #f5f5f5; padding:20px;">
            		<div class="form-group">
            		<div class="col-sm-12">
                       <div class="card text-center paymentSuccessDiv" style="display:none; margin-top:10px; padding:20px;">
                         <img class="tickpnhg" src="<%=request.getContextPath()%>/resources/${appMode}/tilescommon/images/tick.png?appVer=${appVer}">
                         <h2>Gateway Response Successful</h2>
                         <label>Thank you for processing payment of ammount Rs. <span class = "amount"></span> towards <span class = "paymentType"></span></label>
                         <label>Your request id is <span class = "requestID"></span></label>
                       </div>
                       <div class="card text-center paymentFailedDiv" style="display:none; margin-top:10px; padding:20px;">
                         <img class="tickpnhg" src="<%=request.getContextPath()%>/resources/${appMode}/tilescommon/images/cross.png?appVer=${appVer}">
                         <h2>Gateway Response Failed</h2>
                         <label>Thank you for processing payment of ammount Rs. <span class = "amount"></span> towards <span class = "paymentType"></span></label>
                         <label>Your request id is <span class = "requestID"></span></label>
                       </div>
                       <div class="card text-center paymentpendingDiv" style="display:none; margin-top:10px; padding:20px;">
                         <img class="tickpnhg" src="<%=request.getContextPath()%>/resources/${appMode}/tilescommon/images/pending.png?appVer=${appVer}">
                         <h2>Gateway Response Pending</h2>
                         <label>Thank you for processing payment of ammount Rs. <span class = "amount"></span> towards <span class = "apaymentType"></span></label>
                         <label>Your request id is <span class = "requestID"></span></label>
                       </div>
                   </div>
                         <div class="col-sm-12">
                         	<div class="styled-input waitingDiv">
                         		<button type="button" class="btn btn-default bluebutton">Waiting For Confirmation</button>
                         		<button type="button" class="btn btn-default bluebutton"><i class="fa fa-refresh fa-spin" style="font-size:20px"></i></button>
                         	</div>
                         </div>
                         <div class="col-sm-12">
                          <div class="styled-input">
                         	<div class="alert alert-success paymentSuccessDiv" style="display:none;">
							  <strong>Success!</strong> Payment Status success.
							</div>
							<div class="alert alert-warning paymentFailedDiv" style="display:none;">
							  <strong>Failed!</strong> Payment Status Failed.
							</div>
							<div class="alert alert-warning paymentpendingDiv" style="display:none;">
							  <strong>Warning!</strong> Payment Status pending. Please contact to administrator.
							</div>
						  </div>	
                         </div>
                    </div>
            	</div>
            </div>
            </div>
            <script	src="<%=request.getContextPath()%>/resources/${appMode}/commons/js/springform.js?appVer=${appVer}"></script>
            <script	src="<%=request.getContextPath()%>/resources/${appMode}/commons/js/formFields.js?appVer=${appVer}"></script>
            <script	src="<%=request.getContextPath()%>/resources/${appMode}/commons/js/paymentgatewayrespnse.js?appVer=${appVer}"></script>
            </body>