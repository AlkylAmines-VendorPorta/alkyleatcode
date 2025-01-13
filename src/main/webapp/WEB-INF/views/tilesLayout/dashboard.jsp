<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@taglib uri = "http://www.springframework.org/tags/form" prefix = "sf"%>
<style>.backTolist{display:none !important;} .backTotils{display:none !important;}</style>
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/${appMode}/tilescommon/css/mobile.css?appVer=${appVer}">	
	<!-- Context path Set -->
       <% String context=request.getContextPath(); %>
	<!-- Context path Set -->
	
<c:choose>
  <c:when test="${sessionScope.userThemeColor != null}">
   <body class="${sessionScope.userThemeColor}">
  </c:when>
  <c:otherwise>
   <body class="tiles_background">
  </c:otherwise>
</c:choose>

<div class="full-container animated slideInLeft">
        <!-- MainProductTiles start-->
        <div id="MainProductTiles" class="container top20">
        <c:forEach var="authorisedTiles" items="${authorisedTiles}" varStatus="count">
        <c:if test="${authorisedTiles.value== 'Dashboard'}">
            <div class="col-md-2 col-xs-6">
            <a href="<%=context%>/analytics">
                <div class="tiles text-center">
                    <i class="fa fa-fw fa-dashboard pull-left font36"></i>
                    <h4 class="tiles-heading">Dashboard</h4>
                    <div class="clearfix"></div>
                </div>
                </a>
                </div>
                </c:if>
            <c:if test="${authorisedTiles.value== 'Master'}">
            <div class="col-md-2 col-xs-6">
                <a href="<%=context%>/mastertiles">
                <div class="tiles text-center">
                    <i class="fa fa-id-card-o pull-left font36" aria-hidden="true"></i>
                    <h4 class="tiles-heading">Master</h4>
                    <div class="clearfix"></div>
                    </div>
                </a>
            </div>
            </c:if>
            <c:if test="${authorisedTiles.value== 'Tenders'}">
            <div  class="col-md-2 col-xs-6">
                <a href="<%=context%>/tendertiles">
                <div  class="tiles text-center">
                    <i class="fa fa-file-pdf-o pull-left font36"></i>
                    <h4 class="tiles-heading">Tenders</h4>
                   <div class="clearfix"></div>
                    </div>
                </a>
            </div></c:if>
          
            <c:if test="${authorisedTiles.value== 'Auction'}">
            <div  class="col-md-2 col-xs-6">
                <a href="<%=context%>/auctionsTiles">
                <div  class="tiles text-center">
                    <i class="fa fa-gavel pull-left font36"></i>
                    <h4 class="tiles-heading">Auction</h4>
                    <div class="clearfix"></div>
                    </div>
                </a>
            </div></c:if>
            
             <c:if test="${authorisedTiles.value== 'RFQ'}">
            <div  class="col-md-2 col-xs-6">
                <a href="<%=context%>/rfqTiles">
                <div  class="tiles text-center">
                    <i class="fa fa-gavel pull-left font36"></i>
                    <h4 class="tiles-heading">RFQs</h4>
                    <div class="clearfix"></div>
                    </div>
                </a>
            </div></c:if> 
           
            <c:if test="${authorisedTiles.value== 'Payments'}">
            <div class="col-md-2 col-xs-6">
                <a href="payments">
                <div class="tiles text-center">
                    <i class="fa fa-money pull-left font36"></i>
                    <h4 class="tiles-heading">Payment</h4>
                   <h6 class="tiles-count">7 Records</h6>
                    <div class="clearfix"></div>
                    </div>
                </a>
            </div>
            </c:if>
             <c:if test="${authorisedTiles.value== 'Reports'}">
             <div class="col-md-2 col-xs-6">
                <a href="reports">
                	<div class="tiles text-center">
                    <i class="fa fa-file-text pull-left font36"></i>
                    <h4 class="tiles-heading">Reports</h4>
                   <h6 class="tiles-count">7 Records</h6>
                    <div class="clearfix"></div>
                    </div>
                </a>
            </div>
           </c:if>
           
           <c:if test="${authorisedTiles.value== 'Purchase Proposal'}">
            <div class="col-md-2 col-xs-6">
                <a href="#">
                <div class="tiles text-center">
                    <i class="fa fa-shopping-cart pull-left font36"></i>
                    <h4 class="tiles-heading">Purchase Proposal</h4>
                   <h6 class="tiles-count">7 Records</h6>
                    <div class="clearfix"></div>
                    </div>
                </a>
            </div></c:if>
            
             <c:if test="${authorisedTiles.value== 'Payment Approval'}">
            <div class="col-md-2 col-xs-6">
                <a href="<%=context%>/approval">
                	<div class="tiles text-center">
                    <i class="fa fa-shopping-cart pull-left font36"></i>
                    <h4 class="tiles-heading">Payment Approval</h4>
                   <h6 class="tiles-count">7 Records</h6>
                    <div class="clearfix"></div>
                    </div>
                </a>
            </div></c:if>
            
           <c:if test="${authorisedTiles.value== 'Notification'}">
            <div class="col-md-2 col-xs-6">
                <a href="<%=context%>/mail">
                	<div class="tiles text-center">
                    <i class="fa fa-envelope pull-left font36"></i>
                    <h4 class="tiles-heading">Notification</h4>
                   <h6 class="tiles-count">7 Records</h6>
                    <div class="clearfix"></div>
                    </div>
                </a>
            </div></c:if>
            <c:if test="${authorisedTiles.value== 'Role Details'}">
             <div class="col-md-2 col-xs-6">
                <a href="<%=context%>/roleDetails">
                <div class="tiles text-center">
                    <i class="fa fa-info-circle pull-left font36"></i>
                    <h4 class="tiles-heading">Role Details</h4>
                   <h6 class="tiles-count">7 Records</h6>
                    <div class="clearfix"></div>
                    </div>
                </a>
                 </div>
                </c:if>
               
            <c:if test="${authorisedTiles.value== 'Utilities'}">
            <div class="col-md-2 col-xs-6">
                <a href="<%=context%>/approval">
                	<div class="tiles text-center">
                    <i class="fa fa-money pull-left font36"></i>
                    <h4 class="tiles-heading">Payment Approval</h4>
                   <h6 class="tiles-count">7 Records</h6>
                    <div class="clearfix"></div>
                    </div>
                </a>
            </div>
            </c:if>
            <c:if test="${authorisedTiles.value== 'Certificate'}">
            <div class="col-md-2 col-xs-6">
                <a href="#">
                <div class="tiles text-center">
                    <i class="fa fa-certificate pull-left font36"></i>
                    <h4 class="tiles-heading">Certificate</h4>
                   <h6 class="tiles-count">7 Records</h6>
                    <div class="clearfix"></div>
                    </div>
                </a>
            </div>
            </c:if>
            <c:if test="${authorisedTiles.value== 'Partners'}">
            <div class="col-md-2 col-xs-6">
                <a href="<%=context%>/partnerProfiles">
                <div class="tiles text-center">
                    <i class="fa fa-address-card-o pull-left font36"></i>
                    <h4 class="tiles-heading">Partners</h4>
                   <h6 class="tiles-count" ><i id = "dbBpartner" class="fa fa-refresh fa-spin"></i> Records</h6>
                    <div class="clearfix"></div>
                    </div>
                </a>
            </div>
            </c:if>
            <c:if test="${authorisedTiles.value== 'Session Audit'}">
			<div class="col-md-2 col-xs-6">
				<a href="<%=context%>/sessionAudit">
					<div class="tiles text-center">
						<i class="fa fa-podcast pull-left font36"></i>
						<h4 class="tiles-heading">Session Audit</h4>
                   <h6 class="tiles-count">7 Records</h6>
						<div class="clearfix"></div>
					</div>
				</a>
			</div>
			</c:if>
			<c:if test="${authorisedTiles.value== 'Approve Vendor'}"> 
			 <div class="col-md-2 col-xs-6">
				<a href="<%=context%>/approveVendor">
					<div class="tiles text-center">
						<i class="fa fa-users pull-left font36"></i>
						<h4 class="tiles-heading">Approve Vendor</h4>
                   <h6 class="tiles-count">7 Records</h6>
						<div class="clearfix"></div>
					</div>
				</a>
			</div>
			</c:if>
			<c:if test="${authorisedTiles.value== 'Payment Gateway'}">
			<div class="col-md-2 col-xs-6">
				<a href="<%=context%>/paymentgateway">
					<div class="tiles text-center">
						<i class="fa fa-money pull-left font36"></i>
						<h4 class="tiles-heading">Payment Gateway</h4>
                   <h6 class="tiles-count">7 Records</h6>
						<div class="clearfix"></div>
					</div>
				</a>
			</div>
			</c:if>
			<c:if test="${authorisedTiles.value== 'Infra Approval'}">
			<div class="col-md-2 col-xs-6">
				<a href="<%=context%>/infraItemApproval">
					<div class="tiles text-center">
						<i class="fa fa-thumbs-o-up pull-left font36"></i>
						<h4 class="tiles-heading">Infra Approval</h4>
                   		<h6 class="tiles-count"></h6>
						<div class="clearfix"></div>
					</div>
				</a>
			</div>
			</c:if>
            <c:if test="${authorisedTiles.value== 'Invite Participation'}">
            <div class="col-md-2 col-xs-6">
				<a href="<%=context%>/inviteParticipant">
					<div class="tiles text-center">
						<i class="fa fa-envelope pull-left font36"></i>
						<h4 class="tiles-heading">Invite Participant</h4>
						<h6 class="tiles-count">1 Records</h6>
						<div class="clearfix"></div>
					</div>
				</a>
			</div>
			</c:if>
			<c:if test="${authorisedTiles.value== 'Invite Participation'}">
            <div class="col-md-2 col-xs-6">
				<a href="<%=context%>/inviteQuickParticipant">
					<div class="tiles text-center">
						<i class="fa fa-envelope pull-left font36"></i>
						<h4 class="tiles-heading">Invite Quick Participant</h4>
						<h6 class="tiles-count">1 Records</h6>
						<div class="clearfix"></div>
					</div>
				</a>
			</div>
			</c:if>
			<c:if test="${authorisedTiles.value== 'Pending Request'}">
			<div class="col-md-2 col-xs-6">
				<a href="<%=context%>/pendingRequest">
					<div class="tiles text-center">
						<i class="fa fa-thumbs-o-up pull-left font36"></i>
						<h4 class="tiles-heading">Pending Request</h4>
						<h6 class="tiles-count">1 Records</h6>
						<div class="clearfix"></div>
					</div>
				</a>
			</div>
			</c:if>
			 <c:if test="${authorisedTiles.value== 'Payment Gateway'}">
			<div class="col-md-2 col-xs-6">
				<a href="<%=context%>/paymentgateway">
					<div class="tiles text-center">
						<i class="fa fa-money pull-left font36"></i>
						<h4 class="tiles-heading">Payment Gateway</h4>
                   <h6 class="tiles-count">7 Records</h6>
						<div class="clearfix"></div>
					</div>
				</a>
			</div>
			</c:if>
			<c:if test="${authorisedTiles.value== 'Create Contract'}">
			<div class="col-md-2 col-xs-6">
				<a href="<%=context%>/createContract">
					<div class="tiles text-center">
						<i class="fa fa-pencil pull-left font36"></i>
						<h4 class="tiles-heading">Create Contract</h4>
                   		<h6 class="tiles-count">7 Records</h6>
						<div class="clearfix"></div>
					</div>
				</a>
			</div>
			</c:if>
			<c:if test="${authorisedTiles.value== 'Payment Posting'}">
			<div class="col-md-2 col-xs-6">
				<a href="<%=context%>/paymentPosting">
					<div class="tiles text-center">
						<i class="fa fa-podcast pull-left font36"></i>
						<h4 class="tiles-heading">Payment Posting</h4>
                   		<h6 class="tiles-count">7 Records</h6>
						<div class="clearfix"></div>
					</div>
				</a>
			</div>
			</c:if>
			<c:if test="${authorisedTiles.value== 'Workflows'}">
			<div class="col-md-2 col-xs-6">
				<a href="<%=context%>/workflows">
					<div class="tiles text-center">
						<i class="fa fa-line-chart pull-left font36"></i>
						<h4 class="tiles-heading">Workflows</h4>
                   		<h6 class="tiles-count">7 Records</h6>
						<div class="clearfix"></div>
					</div>
				</a>
			</div>
			</c:if>
			<%-- <c:if test="${authorisedTiles.value== ''}">
			<div class="col-md-2 col-xs-6">
				 <a href="<%=context%>/quickAuctionTiles">
                <div  class="tiles text-center">
                    <i class="fa fa-gavel pull-left font36"></i>
                    <h4 class="tiles-heading">Quick Auction</h4>
                    <div class="clearfix"></div>
                    </div>
                </a>
			</div>
			</c:if> --%>
			<%-- <c:if test="${authorisedTiles.value== ''}">
			<div class="col-md-2 col-xs-6">
				 <a href="<%=context%>/rfqTiles">
                <div  class="tiles text-center">
                    <i class="fa fa-gavel pull-left font36"></i>
                    <h4 class="tiles-heading">RFQs</h4>
                    <div class="clearfix"></div>
                    </div>
                </a>
			</div>
			</c:if> --%>
			<c:if test="${authorisedTiles.value== 'Quick RFQ'}">
			<div class="col-md-2 col-xs-6">
				 <a href="<%=context%>/quickRfqTiles">
                <div  class="tiles text-center">
                    <i class="fa fa-gavel pull-left font36"></i>
                    <h4 class="tiles-heading">Quick RFQs</h4>
                    <div class="clearfix"></div>
                    </div>
                </a>
			</div>
			</c:if>
			</c:forEach>
			<div class="col-md-2 col-xs-6">
				 <a href="<%=context%>/InviteAndRequest">
                <div  class="tiles text-center">
                    <i class="fa fa-gavel pull-left font36"></i>
                    <h4 class="tiles-heading">Invite Request</h4>
                    <div class="clearfix"></div>
                    </div>
                </a>
			</div>
			<div class="col-md-2 col-xs-6">
				 <a href="<%=context%>/commonVendorRating">
                <div  class="tiles text-center">
                    <i class="fa fa-gavel pull-left font36"></i>
                    <h4 class="tiles-heading">Rating</h4>
                    <div class="clearfix"></div>
                    </div>
                </a>
			</div>
			<div class="col-md-2 col-xs-6">
				 <a href="<%=context%>/CumulativeReports">
                <div  class="tiles text-center">
                    <i class="fa fa-gavel pull-left font36"></i>
                    <h4 class="tiles-heading">Cumulative Readings</h4>
                    <div class="clearfix"></div>
                    </div>
                </a>
			</div>
        <!-- ProductDetailsTiles end-->
    </div>
       <script src ="<%=request.getContextPath()%>/resources/${appMode}/tilescommon/js/tilesRecordCount.js?appVer=${appVer}"></script>
       <!-- <script>
       var data = '${authorisedTiles}';
       console.log(data);
       </script> -->
</body>