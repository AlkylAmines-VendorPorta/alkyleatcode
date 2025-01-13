<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@taglib uri = "http://www.springframework.org/tags/form" prefix = "sf"%>
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
  <input type = "hidden" id = "tahdrTileType" value = 3>
 
   <div class="full-container animated slideInLeft">
            <!-- ProductDetailsTiles start-->
            <div id="ProductDetailsTiles" class="container top20">
                
                <div id="TenderDetailsTiles">
                <c:forEach var="authorisedTiles" items="${authorisedTiles}" varStatus="count">
           			<c:if test="${authorisedTiles== 'My Tender'}">
		              <div class="col-md-2 col-xs-6">
               				<a href="<%=context%>/myTenders">
                			<div class="tiles text-center">
                    			<i class="fa fa-file-pdf-o pull-left font36"></i>
                   				 <h4 class="tiles-heading">My Tenders</h4>
						<h6 class="tiles-count"><i id = "myTender" class="fa fa-refresh fa-spin"></i> Records</h6>
                    		<div class="clearfix"></div>
                   			</div>
               			 </a>
           			 </div>
		              </c:if>
           			<c:if test="${authorisedTiles== 'Tenders Preparation'}">
					<div class="col-md-2 col-xs-6">
                    <a href="<%=context%>/tenderPreparation" id="">
                        <div class="tiles text-center">
                            <i class="fa fa-plus pull-left font36"></i>
                            <h4 class="tiles-heading">Tender Preparation </h4>
							<h6 class="tiles-count"><i id = "TAHDR" class="fa fa-refresh fa-spin"></i> Records</h6>
                            <div class="clearfix"></div>
                        </div>
                    </a>
                    </div>
                    </c:if>
           			<c:if test="${authorisedTiles== 'Tender Approval'}">
                     <div class="col-md-2 col-xs-6">
		                <a href="<%=context%>/tenderApproval">
		                <div class="tiles text-center">
		                    <i class="fa fa-file-pdf-o pull-left font36"></i>
		                    <h4 class="tiles-heading">Tender Approval</h4>
						<h6 class="tiles-count"><i id = "tenderApproval" class="fa fa-refresh fa-spin"></i> Records</h6>
		                    <div class="clearfix"></div>
		                    </div>
                		</a>
		            </div>
		            </c:if>
           			<c:if test="${authorisedTiles== 'Tender Scheduling'}">
					<div class="col-md-2 col-xs-6">
                    <a href="<%=context%>/tenderScheduling" id="">
                        <div class="tiles text-center">
                            <i class="fa fa-plus pull-left font36"></i>
                            <h4 class="tiles-heading">Tender Scheduling</h4>
						<h6 class="tiles-count"><i id = "tenderScheduling" class="fa fa-refresh fa-spin"></i> Records</h6>
                            <div class="clearfix"></div>
                        </div>
                    </a>
                    </div>
                      </c:if>
           			<c:if test="${authorisedTiles== 'Pre Bid Meeting'}">
                     <div class="col-md-2 col-xs-6">
	                    <a href="<%=context%>/preBidMeeting" id="">
	                        <div class="tiles text-center">
	                            <i class="fa fa-plus pull-left font36"></i>
	                            <h4 class="tiles-heading">Pre Bid Meeting</h4>
						<h6 class="tiles-count"><i id = "preBidMeeting" class="fa fa-refresh fa-spin"></i> Records</h6>
	                            <div class="clearfix"></div>
	                        </div>
	                    </a>
	                    </div>
                    </c:if>
           			<c:if test="${authorisedTiles== 'Tender Purchase'}">
					<div class="col-md-2 col-xs-6">
                    <a href="<%=context%>/tenderPurchase" id="">
                        <div class="tiles text-center">
                            <i class="fa fa-plus pull-left font36"></i>
                            <h4 class="tiles-heading">Tender Purchase</h4>
						<h6 class="tiles-count"><i id = "tenderPurchase" class="fa fa-refresh fa-spin"></i> Records</h6>
                            <div class="clearfix"></div>
                        </div>
                    </a>
                    </div>
                     </c:if>
           			<c:if test="${authorisedTiles== 'Tender Submission'}">
					<div class="col-md-2 col-xs-6">
                    <a href="<%=context%>/tenderSubmission" id="">
                        <div class="tiles text-center">
                            <i class="fa fa-plus pull-left font36"></i>
                            <h4 class="tiles-heading">Bid Submission</h4>
						<h6 class="tiles-count"><i id = "tenderSubmission" class="fa fa-refresh fa-spin"></i> Records</h6>
                            <div class="clearfix"></div>
                        </div>
                    </a>
                    </div>
                    </c:if>
           			<c:if test="${authorisedTiles== 'Tender Committee'}">
                     <div class="col-md-2 col-xs-6">
		                    <a href="<%=context%>/tenderCommittee" id="">
		                        <div class="tiles text-center">
		                            <i class="fa fa-plus pull-left font36"></i>
		                            <h4 class="tiles-heading">Tender Committee</h4>
						<h6 class="tiles-count"><i id = "tendercommittee" class="fa fa-refresh fa-spin"></i> Records</h6>
		                            <div class="clearfix"></div>
		                        </div>
		                    </a>
		                    </div>
                     </c:if>
           			<c:if test="${authorisedTiles== 'Tender Opening'}">
					<div class="col-md-2 col-xs-6">
                    <a href="<%=context%>/tenderOpening" id="">
                        <div class="tiles text-center">
                            <i class="fa fa-plus pull-left font36"></i>
                            <h4 class="tiles-heading">Tender Opening</h4>
						<h6 class="tiles-count"><i id = "tenderOpening" class="fa fa-refresh fa-spin"></i> Records</h6>
                            <div class="clearfix"></div>
                        </div>
                    </a>
                    </div>
                     </c:if>
           			<c:if test="${authorisedTiles== 'Preliminary Scrutiny'}">
					<div class="col-md-2 col-xs-6">
                    <a href="<%=context%>/tenderTechnicalScrutiny" id="">
                        <div class="tiles text-center">
                            <i class="fa fa-plus pull-left font36"></i>
                            <h4 class="tiles-heading">Preliminary Scrutiny</h4>
		                    <h6 class="tiles-count"><i id = "preliminaryScrutinyCount" class="fa fa-refresh fa-spin"></i> Records</h6> 
                            <div class="clearfix"></div>
                        </div>
                    </a>
                    </div>
                     </c:if>
                     <c:if test="${authorisedTiles== 'Deviation Bid'}">
                     <div class="col-md-2 col-xs-6">
		                <a href="<%=context%>/deviationBid">
		                <div class="tiles text-center">
		                    <i class="fa fa-file-pdf-o pull-left font36"></i>
		                    <h4 class="tiles-heading">Deviation Bid</h4>
						<h6 class="tiles-count"><i id = "deviationBid" class="fa fa-refresh fa-spin"></i> Records</h6>
		                    <div class="clearfix"></div>
		                    </div>
                		</a>
		            </div>
		            </c:if>
                    <c:if test="${authorisedTiles== 'Final Scrutiny'}">
					<div class="col-md-2 col-xs-6">
                    <a href="<%=context%>/finalScrutiny" id="">
                        <div class="tiles text-center">
                            <i class="fa fa-plus pull-left font36"></i>
                            <h4 class="tiles-heading">Final Scrutiny</h4>
						<h6 class="tiles-count"><i id = "finalScrutiny" class="fa fa-refresh fa-spin"></i> Records</h6>
                            <div class="clearfix"></div>
                        </div>
                    </a>
                    </div>
                    </c:if> 
                    <c:if test="${authorisedTiles== 'Annexure C1'}">
					<div class="col-md-2 col-xs-6">
                    <a href="<%=context%>/annexureC1" id="">
                        <div class="tiles text-center">
                            <i class="fa fa-plus pull-left font36"></i>
                            <h4 class="tiles-heading">Annexure C1</h4>
						<h6 class="tiles-count"><i id = "annexurec1" class="fa fa-refresh fa-spin"></i> Records</h6>
                            <div class="clearfix"></div>
                        </div>
                    </a> 
                    </div>
                    </c:if> 
                     <c:if test="${authorisedTiles== 'Bid Sheet'}">
		              <div class="col-md-2 col-xs-6">
							<a href="<%=context%>/bidSheet">
								<div class="tiles text-center">
									<i class="fa fa-file-text-o pull-left font36"></i>
									<h4 class="tiles-heading">Tender Status</h4>
									<!-- <h6 class="tiles-count">1 Records</h6> -->
									<div class="clearfix"></div>
								</div>
							</a>
						</div>
		              </c:if>
                     <c:if test="${authorisedTiles== 'Tender Winner Selection'}">
                     <div class="col-md-2 col-xs-6">
		                <a href="<%=context%>/tenderWinnerSelection">
		                <div class="tiles text-center">
		                    <i class="fa fa-file-pdf-o pull-left font36"></i>
		                    <h4 class="tiles-heading">Winner Selection</h4>
						<h6 class="tiles-count"><i id = "tenderWinnerSelection" class="fa fa-refresh fa-spin"></i> Records</h6>
		                    <div class="clearfix"></div>
		                    </div>
                		</a>
		            </div>
		            </c:if>
           			<c:if test="${authorisedTiles== 'Vendor Rating'}">
						<div class="col-md-2 col-xs-6">
							<a href="<%=context%>/rating">
								<div class="tiles text-center">
									<i class="fa fa-star pull-left font36"></i>
									<h4 class="tiles-heading">Rating</h4>
						<h6 class="tiles-count"><i id = "vendorRating" class="fa fa-refresh fa-spin"></i> Records</h6>
									<div class="clearfix"></div>
								</div>
							</a>
						</div>
						</c:if>
                    <c:if test="${authorisedTiles== 'Tender Publishing'}">
					<div class="col-md-2 col-xs-6">
                    <a href="<%=context%>/tenderPublishing" id="">
                        <div class="tiles text-center">
                            <i class="fa fa-plus pull-left font36"></i>
                            <h4 class="tiles-heading">Tender Publishing</h4>
						<h6 class="tiles-count"><i id = "tenderPublishing" class="fa fa-refresh fa-spin"></i> Records</h6>
                            <div class="clearfix"></div>
                        </div>
                    </a>
                    </div>
                      </c:if>
                    <%-- <c:if test="${authorisedTiles== 'Tender Details'}">
                    <div class="col-md-2 col-xs-6">
                    <a href="tenderCommercialScrutiny" id="">
                        <div class="tiles text-center">
                            <i class="fa fa-plus pull-left font36"></i>
                            <h4 class="tiles-heading">Tender Details</h4>
						<h6 class="tiles-count"><i id = "tenderDetails" class="fa fa-refresh fa-spin"></i> Records</h6>
                            <div class="clearfix"></div>
                        </div>
                    </a>
                    </div>
                    </c:if> --%>
						<c:if test="${authorisedTiles== 'Tender Bids'}">
						<div class="col-md-2 col-xs-6">
										<a href="<%=context%>/newTenderBids">
											<div class="tiles text-center">
												<i class="fa fa-file-text-o pull-left font36"></i>
												<h4 class="tiles-heading">Tender Bids</h4>
												<h6 class="tiles-count"><i id = "tenderBids" class="fa fa-refresh fa-spin"></i> Records</h6>
												<div class="clearfix"></div>
											</div>
										</a>
									</div>
						</c:if>
                    </c:forEach>
						<%-- <div class="col-md-2 col-xs-6">
										<a href="<%=context%>/newTenderBids">
											<div class="tiles text-center">
												<i class="fa fa-file-text-o pull-left font36"></i>
												<h4 class="tiles-heading">New Tender Bids</h4>
												<h6 class="tiles-count"><i id = "tenderBids" class="fa fa-refresh fa-spin"></i> Records</h6>
												<div class="clearfix"></div>
											</div>
										</a>
									</div> --%>
									
                </div>
                <!--  AuctionDetailsTiles	end -->

            </div>
            <!-- ProductDetailsTiles end-->
        </div>
        <script src ="<%=request.getContextPath()%>/resources/${appMode}/tilescommon/js/tenderTilesRecordCount.js?appVer=${appVer}"></script>
</body>