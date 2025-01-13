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
 <input type = "hidden" id = "tahdrTileType" value = 4>
 
   <div class="full-container animated slideInLeft">
            <!-- ProductDetailsTiles start-->
            <div id="ProductDetailsTiles" class="container top20">
                
                <div id="TenderDetailsTiles">
                 <c:forEach var="authorisedTiles" items="${authorisedTiles}" varStatus="count">
           			<c:if test="${authorisedTiles== 'Auction Preparation'}">
                <div class="col-md-2 col-xs-6">
                 <a href="<%=context%>/auctionPreparation" id="">
                        <div class="tiles text-center">
                            <i class="fa fa-plus pull-left font36"></i>
                            <h4 class="tiles-heading">Forward Auction</h4>
                            <!-- <h6 class="tiles-count">1 Records</h6> -->
                            <div class="clearfix"></div>
                        </div>
                    </a>
                    </div>
                     
                    </c:if>
                    <c:if test="${authorisedTiles== 'Auction Preparation'}">
                    <div class="col-md-2 col-xs-6">
                   <a href="<%=context%>/reverseAuctionPreparation" id="">
                        <div class="tiles text-center">
                            <i class="fa fa-plus pull-left font36"></i>
                            <h4 class="tiles-heading">Reverse Auction </h4>
                            <!-- <h6 class="tiles-count">1 Records</h6> -->
                            <div class="clearfix"></div>
                        </div>
                    </a>
                    </div>
                    </c:if>
                    <c:if test="${authorisedTiles== 'Auction Publishing'}">
                      <div class="col-md-2 col-xs-6">
                    <a href="<%=context%>/auctionPublishing" id="">
                        <div class="tiles text-center">
                            <i class="fa fa-plus pull-left font36"></i>
                            <h4 class="tiles-heading">Auction Publishing</h4>
						<h6 class="tiles-count"><i id = "auctionPublishing" class="fa fa-refresh fa-spin"></i> Records</h6>
                            <div class="clearfix"></div>
                        </div>
                    </a>
                    </div>
                     </c:if>
                    <c:if test="${authorisedTiles== 'Auction Scheduling'}">
                      <div class="col-md-2 col-xs-6">
                    <a href="<%=context%>/auctionScheduling" id="">
                        <div class="tiles text-center">
                            <i class="fa fa-plus pull-left font36"></i>
                            <h4 class="tiles-heading">Auction Scheduling</h4>
						<h6 class="tiles-count"><i id = "auctionScheduling" class="fa fa-refresh fa-spin"></i> Records</h6>
                            <div class="clearfix"></div>
                        </div>
                    </a>
                    </div>
                     </c:if>
                    <c:if test="${authorisedTiles== 'Auction Purchase'}">
                      <div class="col-md-2 col-xs-6">
                    <a href="<%=context%>/auctionPurchase" id="">
                        <div class="tiles text-center">
                            <i class="fa fa-plus pull-left font36"></i>
                            <h4 class="tiles-heading">Auction Purchase</h4>
						<h6 class="tiles-count"><i id = "auctionPurchase" class="fa fa-refresh fa-spin"></i> Records</h6>
                            <div class="clearfix"></div>
                        </div>
                    </a>
                    </div>
                     </c:if>
                    <c:if test="${authorisedTiles== 'Auction Submission'}">
                      <div class="col-md-2 col-xs-6">
                    <a href="<%=context%>/auctionSubmission" id="">
                        <div class="tiles text-center">
                            <i class="fa fa-plus pull-left font36"></i>
                            <h4 class="tiles-heading">Bid Submission</h4>
						<h6 class="tiles-count"><i id = "auctionSubimmision" class="fa fa-refresh fa-spin"></i> Records</h6>
                            <div class="clearfix"></div>
                        </div>
                    </a>
                    </div>
                     </c:if>
                    <c:if test="${authorisedTiles== 'Auction Opening'}">
                      <div class="col-md-2 col-xs-6">
                    <a href="<%=context%>/auctionOpening" id="">
                        <div class="tiles text-center">
                            <i class="fa fa-plus pull-left font36"></i>
                            <h4 class="tiles-heading">Auction Opening</h4>
							<h6 class="tiles-count"><i id = "auctionOpening" class="fa fa-refresh fa-spin"></i> Records</h6>
                            <div class="clearfix"></div>
                        </div>
                    </a>
                    </div>
                     </c:if>
                    <c:if test="${authorisedTiles== 'Auction Final Scrutiny'}">
                      <div class="col-md-2 col-xs-6">
                    <a href="<%=context%>/auctionFinalScrutiny" id="">
                        <div class="tiles text-center">
                            <i class="fa fa-plus pull-left font36"></i>
                            <h4 class="tiles-heading">Auction Final Scrutiny</h4>
						<h6 class="tiles-count"><i id = "auctionFinalScrutiny" class="fa fa-refresh fa-spin"></i> Records</h6>
                            <div class="clearfix"></div>
                        </div>
                    </a>
                    </div>
                     </c:if>
                    
                    <c:if test="${authorisedTiles== 'Auction Preliminary Scrutiny'}">
                      <div class="col-md-2 col-xs-6">
                    <a href="<%=context%>/auctionTechnicalScrutiny" id="">
                        <div class="tiles text-center">
                            <i class="fa fa-plus pull-left font36"></i>
                            <h4 class="tiles-heading">Auction Preliminary Scrutiny</h4>
						<h6 class="tiles-count"><i id = "auctionPreliminaryScrutiny" class="fa fa-refresh fa-spin"></i> Records</h6>
                            <div class="clearfix"></div>
                        </div>
                    </a>
                    </div>
                     </c:if>
                    <c:if test="${authorisedTiles== 'Auction Committee'}">
                      <div class="col-md-2 col-xs-6">
                    <a href="<%=context%>/auctionCommittee" id="">
                        <div class="tiles text-center">
                            <i class="fa fa-plus pull-left font36"></i>
                            <h4 class="tiles-heading">Auction Committee</h4>
						<h6 class="tiles-count"><i id = "auctionCommittee" class="fa fa-refresh fa-spin"></i> Records</h6>
                            <div class="clearfix"></div>
                        </div>
                    </a> 
                    </div>   
                     </c:if>
                    <c:if test="${authorisedTiles== 'Auction Approval'}">
                    <div class="col-md-2 col-xs-6">
                    <a href="<%=context%>/auctionApproval" id="" >
                        <div class="tiles text-center">
                            <i class="fa fa-plus pull-left font36"></i>
                            <h4 class="tiles-heading">Auction Approval</h4>
						<h6 class="tiles-count"><i id = "auctionApproval" class="fa fa-refresh fa-spin"></i> Records</h6>
                            <div class="clearfix"></div>
                        </div>
                    </a>   
                    </div>
                     </c:if>
                    <c:if test="${authorisedTiles== 'Auction Winner Selection'}">
                     <div class="col-md-2 col-xs-6">
		                <a href="<%=context%>/auctionWinnerSelection">
		                <div class="tiles text-center">
		                    <i class="fa fa-plus pull-left font36"></i>
		                    <h4 class="tiles-heading">Winner Selection</h4>
						<h6 class="tiles-count"><i id = "auctionWinnerSelection" class="fa fa-refresh fa-spin"></i> Records</h6>
		                    <div class="clearfix"></div>
		                    </div>
                		</a>
		            </div>
		             </c:if>
                    <c:if test="${authorisedTiles== 'My Auction'}">
		            <div class="col-md-2 col-xs-6">
		                <%-- <a href="<%=context%>/auctionWinnerSelection"> --%>
		                <a href="<%=context%>/myAuctions">
		                <div class="tiles text-center">
		                    <i class="fa fa-plus pull-left font36"></i>
		                    <h4 class="tiles-heading">My Auction</h4>
		                    <h6 class="tiles-count"><i id = "myAuction" class="fa fa-refresh fa-spin"></i> Records</h6>
		                    <div class="clearfix"></div>
		                    </div>
                		</a>
		            </div>
		             </c:if>
                    <c:if test="${authorisedTiles== 'Live Bid'}">
		            <div class="col-md-2 col-xs-6">
							<a href="<%=context%>/liveBid">
								<div class="tiles text-center">
									<i class="fa fa-line-chart pull-left font36"></i>
									<h4 class="tiles-heading">Live Bid</h4>
									<!-- <h6 class="tiles-count">1 Records</h6> -->
									<div class="clearfix"></div>
								</div>
							</a>
						</div>
						 </c:if>
				 <c:if test="${authorisedTiles== 'Quick Live Bid'}">
		            <div class="col-md-2 col-xs-6">
							<a href="<%=context%>/QuickLiveBid">
								<div class="tiles text-center">
									<i class="fa fa-line-chart pull-left font36"></i>
									<h4 class="tiles-heading">Quick Live Bid</h4>
									<!-- <h6 class="tiles-count">1 Records</h6> -->
									<div class="clearfix"></div>
								</div>
							</a>
						</div>
						 </c:if>
		                    <c:if test="${authorisedTiles== 'Bid Sheet'}">
								<div class="col-md-2 col-xs-6">
									<a href="<%=context%>/auctionBidSheet">
										<div class="tiles text-center">
											<i class="fa fa-file-text-o pull-left font36"></i>
											<h4 class="tiles-heading">Bid Sheet</h4>
											<!-- <h6 class="tiles-count">1 Records</h6> -->
											<div class="clearfix"></div>
										</div>
									</a>
								</div>
								 </c:if>
								 <c:if test="${authorisedTiles== 'Vendor Rating'}">
								<div class="col-md-2 col-xs-6">
									<a href="<%=context%>/auctionRating">
										<div class="tiles text-center">
											<i class="fa fa-star pull-left font36"></i>
											<h4 class="tiles-heading">Rating</h4>
											<h6 class="tiles-count">
											<i id = "AuctionVendorRating" class="fa fa-refresh fa-spin"></i> Records</h6>
											<div class="clearfix"></div>
										</div>
									</a>
								</div>
								</c:if>
								<c:if test="${authorisedTiles== 'Auction Annexure C1'}">
										<div class="col-md-2 col-xs-6">
											<a href="<%=context%>/auctionAnnexureC1" id="">
												<div class="tiles text-center">
													<i class="fa fa-plus pull-left font36"></i>
													<h4 class="tiles-heading">Annexure C1</h4>
											<h6 class="tiles-count"><i id = "auctionAnnexureC1" class="fa fa-refresh fa-spin"></i> Records</h6>													<div class="clearfix"></div>
												</div>
											</a>
										</div>
									</c:if>
                   <%-- <c:if test="${authorisedTiles== 'Auction Invitation'}">
                    <div class="col-md-2 col-xs-6">
							<a href="<%=context%>/QuickLiveBid">
								<div class="tiles text-center">
									<i class="fa fa-line-chart pull-left font36"></i>
									<h4 class="tiles-heading">Quick Live Bid</h4>
									<!-- <h6 class="tiles-count">1 Records</h6> -->
									<div class="clearfix"></div>
								</div>
							</a>
						</div>
						</c:if> --%>
						<c:if test="${authorisedTiles== 'Auction Invitation'}">
                      <div class="col-md-2 col-xs-6">
                    <a href="<%=context%>/auctionInvitation" id="">
                        <div class="tiles text-center">
                            <i class="fa fa-plus pull-left font36"></i>
                            <h4 class="tiles-heading">Auction Invitation</h4>
                            <!-- <h6 class="tiles-count">0 Records</h6> -->
                            <div class="clearfix"></div>
                        </div>
                    </a> 
                    </div>
                    </c:if>
                    <c:if test="${authorisedTiles== 'Quick Forward Auction'}">
                    <div class="col-md-2 col-xs-6">
						<a href="<%=context%>/quickForwordAuction" id="">
							<div class="tiles text-center">
								<i class="fa fa-plus pull-left font36"></i>
								<h4 class="tiles-heading">Quick Forward Auction</h4>
								<div class="clearfix"></div>
							</div>
						</a>
					</div>
					</c:if>
					<c:if test="${authorisedTiles== 'Quick Reverse Auction'}"> 
					<div class="col-md-2 col-xs-6">
						<a href="<%=context%>/quickReverseAuction" id="">
							<div class="tiles text-center">
								<i class="fa fa-plus pull-left font36"></i>
								<h4 class="tiles-heading">Quick Reverse Auction</h4>
								<div class="clearfix"></div>
							</div>
						</a>
					</div>
					</c:if>
					<c:if test="${authorisedTiles== 'Quick Bid Sheet'}">    
					<div class="col-md-2 col-xs-6">
						<a href="<%=context%>/quickBidSheet" id="">
							<div class="tiles text-center">
								<i class="fa fa-plus pull-left font36"></i>
								<h4 class="tiles-heading">Quick Bid Sheet</h4>
								<div class="clearfix"></div>
							</div>
						</a>
					</div> 
					</c:if>
					<c:if test="${authorisedTiles== 'Quick Winner Selection'}">
					<div class="col-md-2 col-xs-6">
		                <a href="<%=context%>/quickAuctionWinnerSelection">
		                <div class="tiles text-center">
		                    <i class="fa fa-file-pdf-o pull-left font36"></i>
		                    <h4 class="tiles-heading">Quick Winner Selection</h4>
						<h6 class="tiles-count"><i id = "auctionWinnerSelection" class="fa fa-refresh fa-spin"></i> Records</h6>
		                    <div class="clearfix"></div>
		                    </div>
                		</a>
		            </div>  
		            </c:if>
			    	<c:if test="${authorisedTiles== 'Auction Bids'}">
										 <div class="col-md-2 col-xs-6">
										<a href="<%=context%>/newAuctionBids">
											<div class="tiles text-center">
												<i class="fa fa-file-text-o pull-left font36"></i>
												<h4 class="tiles-heading">Auction Bids</h4>
												<h6 class="tiles-count"><i id = "auctionBids" class="fa fa-refresh fa-spin"></i> Records</h6>
												<div class="clearfix"></div>
											</div>
										</a>
									</div>
				   </c:if>
				 
					</c:forEach>
					<div class="col-md-2 col-xs-6">
		                <a href="<%=context%>/pendingRequest">
		                <div class="tiles text-center">
		                    <i class="fa fa-file-pdf-o pull-left font36"></i>
		                    <h4 class="tiles-heading">Pending Request</h4>
						<h6 class="tiles-count"><i id = "auctionWinnerSelection" class="fa fa-refresh fa-spin"></i> Records</h6>
		                    <div class="clearfix"></div>
		                    </div>
                		</a>
		            </div>  
                </div>
                <!--  AuctionDetailsTiles	end -->

            </div>
            <!-- ProductDetailsTiles end-->
        </div>
        <script src ="<%=request.getContextPath()%>/resources/${appMode}/tilescommon/js/tenderTilesRecordCount.js?appVer=${appVer}"></script>
</body>