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
                    <%-- </c:if>
                    
                    
                    <c:if test="${authorisedTiles== 'RFQ Bid Proposal'}"> --%>
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
                     <%-- </c:if>
		             	
						<c:if test="${authorisedTiles== 'RFQ Invitation'}"> --%>
                     <div class="col-md-2 col-xs-6">
						<a href="<%=context%>/quickBidSheet" id="">
							<div class="tiles text-center">
								<i class="fa fa-plus pull-left font36"></i>
								<h4 class="tiles-heading">Quick Bid Sheet</h4>
								<div class="clearfix"></div>
							</div>
						</a>
					</div> 
                   <%--  </c:if>
                   
                   <c:if test="${authorisedTiles== 'RFQ Invitation'}"> --%>
                      <div class="col-md-2 col-xs-6">
                   <%--  <a href="<%=context%>/newQuickBids" id=""> --%>
                    <a href="#" id="">
                        <div class="tiles text-center">
                            <i class="fa fa-plus pull-left font36"></i>
                            <h4 class="tiles-heading">Quick Bids</h4>
                            <!-- <h6 class="tiles-count">0 Records</h6> -->
                            <div class="clearfix"></div>
                        </div>
                    </a> 
                    </div>
                   <%--  </c:if>
			    	
				 
					</c:forEach> --%>
					<%-- <div class="col-md-2 col-xs-6">
							<a href="<%=context%>/quickAuctionRating">
								<div class="tiles text-center">
									<i class="fa fa-star pull-left font36"></i>
									<h4 class="tiles-heading">Rating</h4>
						<h6 class="tiles-count"><i id = "vendorRating" class="fa fa-refresh fa-spin"></i> Records</h6>
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