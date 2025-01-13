<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@taglib uri = "http://www.springframework.org/tags/form" prefix = "sf"%>

	<!-- Context path Set -->
       <% String context=request.getContextPath(); %>
	<!-- Context path Set -->

 <body class="tiles_background">
 <!-- <input type = "hidden" id = "tahdrTileType" value = 4> -->
 
   <div class="full-container animated slideInLeft">
            <!-- ProductDetailsTiles start-->
            <div id="ProductDetailsTiles" class="container top20">
                
                <div id="TenderDetailsTiles">   
                 <c:forEach var="authorisedTiles" items="${authorisedTiles}" varStatus="count">
                <c:if test="${authorisedTiles.value== 'Tender Approval'}">           
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
		             <c:if test="${authorisedTiles.value== 'Auction Approval'}">   
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
                    <c:if test="${authorisedTiles.value== 'Payment Approval'}">
								  <div class="col-md-2 col-xs-6">
                <a href="<%=context%>/approval">
                	<div class="tiles text-center">
                    <i class="fa fa-shopping-cart pull-left font36"></i>
                    <h4 class="tiles-heading">Payment Approval</h4>
                   <h6 class="tiles-count"><i id = "paymentApproval" class="fa fa-refresh fa-spin"></i> Records</h6>
                    <div class="clearfix"></div>
                    </div>
                </a>
            </div>
            </c:if>
            <c:if test="${authorisedTiles.value== 'Registration'}">
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
			<c:if test="${authorisedTiles.value== 'Infra Approval'}">
										<div class="col-md-2 col-xs-6">
				<a href="<%=context%>/infraItemApproval">
					<div class="tiles text-center">
						<i class="fa fa-podcast pull-left font36"></i>
						<h4 class="tiles-heading">Infra Approval</h4>
                   		<h6 class="tiles-count"><i id = "infraApproval" class="fa fa-refresh fa-spin"></i> Records</h6>
						<div class="clearfix"></div>
					</div>
				</a>
			</div>
			</c:if>
			<c:if test="${authorisedTiles.value== 'Final Scrutiny'}">
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
                    <c:if test="${authorisedTiles.value== 'Preliminary Scrutiny'}">
                    	<div class="col-md-2 col-xs-6">
                    <a href="<%=context%>/tenderTechnicalScrutiny" id="">
                        <div class="tiles text-center">
                            <i class="fa fa-plus pull-left font36"></i>
                            <h4 class="tiles-heading">Preliminary Scrutiny</h4>
		                    <h6 class="tiles-count"><i id = "preliminaryScrutiny" class="fa fa-refresh fa-spin"></i> Records</h6> 
                            <div class="clearfix"></div>
                        </div>
                    </a>
                    </div>
                    </c:if>
                          <c:if test="${authorisedTiles.value== 'Auction Preliminary Scrutiny'}">
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
                     <c:if test="${authorisedTiles.value== 'Auction Final Scrutiny'}">
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
							</c:forEach>
                </div>
                <!--  AuctionDetailsTiles	end -->

            </div>
            <!-- ProductDetailsTiles end-->
        </div>
        <script src ="<%=request.getContextPath()%>/resources/${appMode}/tilescommon/js/workflowTilesRecordCount.js?appVer=${appVer}"></script>
        
</body>