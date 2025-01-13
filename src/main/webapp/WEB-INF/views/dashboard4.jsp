<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>

<link rel="stylesheet"	href="<%=request.getContextPath()%>/resources/${appMode}/tilescommon/css/fioristyle.css?appVer=${appVer}" />
<link rel="stylesheet"	href="<%=request.getContextPath()%>/resources/${appMode}/tilescommon/css/dashboard.css?appVer=${appVer}" />
<link rel="stylesheet"	href="<%=request.getContextPath()%>/resources/${appMode}/tilescommon/css/mobile.css?appVer=${appVer}">

<!-- Context path Set -->
<% String context=request.getContextPath(); %>
<!-- Context path Set -->

<body class="tiles_background">
	<!-- <nav class="navbar navbar-inverse navbar-fixed-top">
        <div class="container-fluid nopadding">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
            </div>
            <div class="collapse navbar-collapse" id="myNavbar"> 
                <ul class="nav navbar-nav  main-nav activenav">
                    <li class="home"><a href="dashboard4">Home</a></li>
                    <li class="dropdown">
         			 <a href="#" class="dropdown-toggle" data-toggle="dropdown">Tenders <b class="caret"></b></a>
        			  <ul class="dropdown-menu">           				 
           				 <li><a href="latestTendersWorks">Latest Tenders Work</a></li>
           				 <li><a href="latestTendersProcurements">Latest Tenders Procurements</a></li>
           				 <li><a href="forwardAuction">Forward Auction</a></li>
           				 <li><a href="reverseAuction">Reverse Auction</a></li>
         			 </ul>
       				 </li>
       				 <li><a href="latestAnnouncements">Announcements</a></li>
                    <li class="dropdown">
         			 <a href="#" class="dropdown-toggle" data-toggle="dropdown">Vendors <b class="caret"></b></a>
        			  <ul class="dropdown-menu">
           				 <li><a href="blackListedVendor">Blacklisted Vendors</a></li>
           				 <li><a href="registeredVendors">Registered Vendors</a></li>
						
         			 </ul>
       				 </li>
       				 <li class="dropdown">
         			 <a href="#" class="dropdown-toggle" data-toggle="dropdown">customer <b class="caret"></b></a>
        			  <ul class="dropdown-menu">
        			     <li><a href="blackListedCustomer">Blacklisted customer</a></li>
           				 <li><a href="registeredCustomer"> Registered customer</a></li>
         			 </ul>
       				 </li>
                    <li><a href="download">Download</a></li>
                    <li><a href="publicNotices"> Public Notices</a></li>
                </ul>
               
            </div>
        </div>
    </nav> -->
	<div class="clearfix"></div>
	<div class="full-container animated slideInLeft"
		style="margin-top: 60px; rmargin-bottom: 60px;">
		<!-- MainProductTiles start-->
		<div id="MainProductTiles" class="container-fluid top20 cont">
			<div
				class="col-lg-12 col-md-12 col-sm-12 col-xs-12 bhoechie-tab-container nopadding">
				<div class="col-lg-2 col-md-2 col-sm-2 col-xs-4 bhoechie-tab-menu">
					<div class="list-group">
						<c:forEach var="authorisedTiles" items="${authorisedTiles}"
							varStatus="count">
							<c:if test="${authorisedTiles.value== 'Dashboard'}">
								<a href="#" class="list-group-item text-center active">
									<h4 class="fa fa-fw fa-dashboard"></h4>
									<br />Dashboard
								</a>
							</c:if>
							<c:if test="${authorisedTiles.value== 'Master'}">
								<a href="#" class="list-group-item text-center">
									<h4 class="fa fa-id-card-o"></h4>
									<br />Master
								</a>
							</c:if>
							<c:if test="${authorisedTiles.value== 'Tenders'}">
								<a href="#" class="list-group-item text-center">
									<h4 class="fa fa-file-pdf-o"></h4>
									<br />Tenders
								</a>
							</c:if>
							<c:if test="${authorisedTiles.value== 'Auction'}">
								<a href="#" class="list-group-item text-center">
									<h4 class="fa fa-gavel"></h4>
									<br />Auction
								</a>
							</c:if>
							<%-- <c:if test="${authorisedTiles.value== 'Bids'}">
								<a href="#" class="list-group-item text-center">
									<h4 class="glyphicon glyphicon-list-alt"></h4>
									<br />Bids
								</a>
							</c:if> --%>
							<c:if test="${authorisedTiles.value== 'Payments'}">
								<a href="#" class="list-group-item text-center">
									<h4 class="fa fa-money"></h4>
									<br />Payments
								</a>
							</c:if>
							<c:if test="${authorisedTiles.value== 'Session Audit'}">
								<a href="#" class="list-group-item text-center">
									<h4 class="fa fa-money"></h4>
									<br />Session Audit
								</a>
							</c:if>
							<c:if test="${authorisedTiles.value== 'Role Details'}">
								<a href="#" class="list-group-item text-center">
									<h4 class="fa fa-money"></h4>
									<br />Role Details
								</a>
							</c:if>
							<%-- <c:if test="${authorisedTiles.value== 'Notification'}">
								<a href="#" class="list-group-item text-center">
									<h4 class="fa fa-envelope"></h4>
									<br />Notification
								</a>
							</c:if> --%>
							
							<c:if test="${authorisedTiles.value== 'Partner'}">
								<a href="#" class="list-group-item text-center">
									<h4 class="fa fa-info-circle"></h4>
									<br />Partner
								</a>
							</c:if>
							<c:if test="${authorisedTiles.value== 'Infra Approval'}">
					           <c:if test="${partner.isInfra=='Y'}">
					             <a href="#" class="list-group-item text-center">
									<h4 class="fa fa-info-circle"></h4>
									<br />Infra Details
								</a>
								</c:if>
			                   </c:if>
							<%-- <c:if test="${authorisedTiles.value== 'Purchase Proposal'}">
								<a href="#" class="list-group-item text-center">
									<h4 class="fa fa-info-circle"></h4>
									<br />Purchase Proposal
								</a>
							</c:if> --%>
						<%-- 	<c:if test="${authorisedTiles.value== 'Certificate'}">
								<a href="#" class="list-group-item text-center">
									<h4 class="fa fa-info-circle"></h4>
									<br />Certificate
								</a>
							</c:if> --%>
							<%-- <c:if test="${authorisedTiles.value== 'Reports'}">
								<a href="#" class="list-group-item text-center">
									<h4 class="fa fa-info-circle"></h4>
									<br />Reports
								</a>
							</c:if> --%>
						</c:forEach>
					</div>
				</div>
				<div class="col-lg-10 col-md-10 col-sm-10 col-xs-8 bhoechie-tab">
					<!-- flight section -->
					<div class="bhoechie-tab-content active nopadtab">
						<!-- Breadcrumbs-->
						<ol class="breadcrumb">
							<li class="breadcrumb-item"><a href="#">Dashboard</a></li>
							<li class="breadcrumb-item active">My Dashboard</li>
						</ol>
						<!-- Icon Cards-->
						<div class="row">
							<div class="col-sm-3 col-xs-6 npd">
								<div
									class="cardactive card2 tendworkdet text-white bg-primary o-hidden h-100"
									onclick="getTenderByTypeCode('WT','tahdrWorksTable')">
									<div class="card-body">
										<div class="card-body-icon">
											<i class="fa fa-file-pdf-o"></i>
										</div>
										<div class="mr-5">Tenders Work</div>
									</div>
									<a
										class="card-footer text-white clearfix small z-1 tendworkdet"
										href="javascript:void(0)"> <span class="float-left">View
											Details</span> <span class="float-right"> <i
											class="fa fa-angle-right"></i>
									</span>
									</a>
								</div>
							</div>
							<div class="col-sm-3 col-xs-6 npd">
								<div
									class="card2 tendprodet text-white bg-warning o-hidden h-100"
									onclick="getTenderByTypeCode('PT','tahdrProcurementTable')">
									<div class="card-body">
										<div class="card-body-icon">
											<i class="fa fa-gavel"></i>
										</div>
										<div class="mr-5">Tenders Procurements</div>
									</div>
									<a class="card-footer text-white clearfix small z-1 tendprodet"
										href="javascript:void(0)"> <span class="float-left">View
											Details</span> <span class="float-right"> <i
											class="fa fa-angle-right"></i>
									</span>
									</a>
								</div>
							</div>
							<div class="col-sm-3 col-xs-6 npd">
								<div
									class="card2 foauctdet text-white bg-success o-hidden h-100"
									onclick="getTenderByTypeCode('FA','forwardAuctionTable')">
									<div class="card-body">
										<div class="card-body-icon">
											<i class="glyphicon glyphicon-list-alt"></i>
										</div>
										<div class="mr-5">Forward Auction</div>
									</div>
									<a class="card-footer text-white clearfix small z-1 foauctdet"
										href="javascript:void(0)"> <span class="float-left">View
											Details</span> <span class="float-right"> <i
											class="fa fa-angle-right"></i>
									</span>
									</a>
								</div>
							</div>
							<div class="col-sm-3 col-xs-6 npd">
								<div class="card2 text-white reauctdet bg-danger o-hidden h-100"
									onclick="getTenderByTypeCode('FA','reverseAuctionTable')">
									<div class="card-body">
										<div class="card-body-icon">
											<i class="fa fa-file-text"></i>
										</div>
										<div class="mr-5">Reverse Auction</div>
									</div>
									<a class="card-footer text-white clearfix small z-1 reauctdet"
										href="javascript:void(0)"> <span class="float-left">View
											Details</span> <span class="float-right"> <i
											class="fa fa-angle-right"></i>
									</span>
									</a>
								</div>
							</div>
						</div>
						<div class="clearfix"></div>

						<div class="workstender_details_containt">
							<h4>Latest Tenders Work</h4>
							<table
								class="TenderListingTable tableResponsive table table-striped table-bordered"
								width="100%" id="tahdrWorksTable">
								<thead>
									<tr>
										<!-- <th>Tender No</th>
                                                    <th>Description</th>
                                                    <th>Sale Opening</th>
                                                    <th>Sale Closing</th>
                                                    <th>Submission Due</th>
                                                    <th>Tender Fee(Rs)</th>  -->
										<th>Tender No</th>
										<th>Description</th>
										<th>Purchase FromDate</th>
										<th>Purchase ToDate</th>
										<th>TechnicalBid OpenDate</th>
										<th>Submission Due</th>
										<th>Tender Fee(Rs)</th>
										<th>Pre Bid MOM</th>
									</tr>
								</thead>
								<!-- <tbody>
									<tr>
										<td><a class="tenderdetailsTab" data-toggle="tab"
											href="#TenderDetails">TestTender1103</a></td>
										<td>Browser Requirements</td>
										<td>21st October 2017</td>
										<td>21st October 2017</td>
										<td>21st October 2017</td>
										<td>20,00,000</td>
										<td></td>
										<td></td>
									</tr>
									<tr>
										<td><a class="tenderdetailsTab" data-toggle="tab"
											href="#TenderDetails">TestTender1103</a></td>
										<td>Browser Requirements</td>
										<td>21st October 2017</td>
										<td>21st October 2017</td>
										<td>21st October 2017</td>
										<td>20,00,000</td>
										<td></td>
										<td></td>
									</tr>
									<tr>
										<td><a class="tenderdetailsTab" data-toggle="tab"
											href="#TenderDetails">TestTender1103</a></td>
										<td>Browser Requirements</td>
										<td>21st October 2017</td>
										<td>21st October 2017</td>
										<td>21st October 2017</td>
										<td>20,00,000</td>
										<td></td>
										<td></td>
									</tr>
									<tr>
										<td><a class="tenderdetailsTab" data-toggle="tab"
											href="#TenderDetails">TestTender1103</a></td>
										<td>Browser Requirements</td>
										<td>21st October 2017</td>
										<td>21st October 2017</td>
										<td>21st October 2017</td>
										<td>20,00,000</td>
										<td></td>
										<td></td>
									</tr>
									<tr>
										<td><a class="tenderdetailsTab" data-toggle="tab"
											href="#TenderDetails">TestTender1103</a></td>
										<td>Browser Requirements</td>
										<td>21st October 2017</td>
										<td>21st October 2017</td>
										<td>21st October 2017</td>
										<td>20,00,000</td>
										<td></td>
										<td></td>
									</tr>
									<tr>
										<td><a class="tenderdetailsTab" data-toggle="tab"
											href="#TenderDetails">TestTender1103</a></td>
										<td>Browser Requirements</td>
										<td>21st October 2017</td>
										<td>21st October 2017</td>
										<td>21st October 2017</td>
										<td>20,00,000</td>
										<td></td>
										<td></td>
									</tr>

								</tbody> -->
							</table>
						</div>
						<div class="Procurementstender_details_containt">
							<h4>Tenders Procurements</h4>
							<table
								class="TenderListingTable tableResponsive table table-striped table-bordered"
								width="100%" id="tahdrProcurementTable">
								<thead>
									<tr>
										<!-- <th>Tender No</th>
                                                    <th>Description</th>
                                                    <th>Sale Opening</th>
                                                    <th>Sale Closing</th>
                                                    <th>Submission Due</th>
                                                    <th>Tender Fee(Rs)</th>  -->
										<th>Tender No</th>
										<th>Description</th>
										<th>Purchase FromDate</th>
										<th>Purchase ToDate</th>
										<th>TechnicalBid OpenDate</th>
										<th>Submission Due</th>
										<th>Tender Fee(Rs)</th>
										<th>Pre Bid MOM</th>
									</tr>
								</thead>
								<!-- <tbody>
									<tr>
										<td><a class="tenderdetailsTab" data-toggle="tab"
											href="#TenderDetails">TestpTender1103</a></td>
										<td>Browser Requirements</td>
										<td>21st October 2017</td>
										<td>21st October 2017</td>
										<td>21st October 2017</td>
										<td>20,00,000</td>
										<td></td>
										<td></td>
									</tr>
									<tr>
										<td><a class="tenderdetailsTab" data-toggle="tab"
											href="#TenderDetails">TestpTender1103</a></td>
										<td>Browser Requirements</td>
										<td>21st October 2017</td>
										<td>21st October 2017</td>
										<td>21st October 2017</td>
										<td>20,00,000</td>
										<td></td>
										<td></td>
									</tr>
									<tr>
										<td><a class="tenderdetailsTab" data-toggle="tab"
											href="#TenderDetails">TestpTender1103</a></td>
										<td>Browser Requirements</td>
										<td>21st October 2017</td>
										<td>21st October 2017</td>
										<td>21st October 2017</td>
										<td>20,00,000</td>
										<td></td>
										<td></td>
									</tr>
									<tr>
										<td><a class="tenderdetailsTab" data-toggle="tab"
											href="#TenderDetails">TestTender1103</a></td>
										<td>Browser Requirements</td>
										<td>21st October 2017</td>
										<td>21st October 2017</td>
										<td>21st October 2017</td>
										<td>20,00,000</td>
										<td></td>
										<td></td>
									</tr>
									<tr>
										<td><a class="tenderdetailsTab" data-toggle="tab"
											href="#TenderDetails">TestTender1103</a></td>
										<td>Browser Requirements</td>
										<td>21st October 2017</td>
										<td>21st October 2017</td>
										<td>21st October 2017</td>
										<td>20,00,000</td>
										<td></td>
										<td></td>
									</tr>
									<tr>
										<td><a class="tenderdetailsTab" data-toggle="tab"
											href="#TenderDetails">TestTender1103</a></td>
										<td>Browser Requirements</td>
										<td>21st October 2017</td>
										<td>21st October 2017</td>
										<td>21st October 2017</td>
										<td>20,00,000</td>
										<td></td>
										<td></td>
									</tr>

								</tbody> -->
							</table>
						</div>
						<div class="ForwardAuction_details_containt">
							<h4>Forward Auction</h4>
							<table
								class="TenderListingTable tableResponsive table table-striped table-bordered"
								width="100%" id="forwardAuctionTable">
								<thead>
									<tr>
										<!-- <th>Tender No</th>
                                                    <th>Description</th>
                                                    <th>Sale Opening</th>
                                                    <th>Sale Closing</th>
                                                    <th>Submission Due</th>
                                                    <th>Tender Fee(Rs)</th>  -->
										<th>Tender No</th>
										<th>Description</th>
										<th>Purchase FromDate</th>
										<th>Purchase ToDate</th>
										<th>TechnicalBid OpenDate</th>
										<th>Submission Due</th>
										<th>Tender Fee(Rs)</th>
										<th>Pre Bid MOM</th>
									</tr>
								</thead>
								<!-- <tbody>
									<tr>
										<td><a class="tenderdetailsTab" data-toggle="tab"
											href="#TenderDetails">TestfTender1103</a></td>
										<td>Browser Requirements</td>
										<td>21st October 2017</td>
										<td>21st October 2017</td>
										<td>21st October 2017</td>
										<td>20,00,000</td>
										<td></td>
										<td></td>
									</tr>
									<tr>
										<td><a class="tenderdetailsTab" data-toggle="tab"
											href="#TenderDetails">TestfTender1103</a></td>
										<td>Browser Requirements</td>
										<td>21st October 2017</td>
										<td>21st October 2017</td>
										<td>21st October 2017</td>
										<td>20,00,000</td>
										<td></td>
										<td></td>
									</tr>
									<tr>
										<td><a class="tenderdetailsTab" data-toggle="tab"
											href="#TenderDetails">TestTender1103</a></td>
										<td>Browser Requirements</td>
										<td>21st October 2017</td>
										<td>21st October 2017</td>
										<td>21st October 2017</td>
										<td>20,00,000</td>
										<td></td>
										<td></td>
									</tr>
									<tr>
										<td><a class="tenderdetailsTab" data-toggle="tab"
											href="#TenderDetails">TestTender1103</a></td>
										<td>Browser Requirements</td>
										<td>21st October 2017</td>
										<td>21st October 2017</td>
										<td>21st October 2017</td>
										<td>20,00,000</td>
										<td></td>
										<td></td>
									</tr>
									<tr>
										<td><a class="tenderdetailsTab" data-toggle="tab"
											href="#TenderDetails">TestTender1103</a></td>
										<td>Browser Requirements</td>
										<td>21st October 2017</td>
										<td>21st October 2017</td>
										<td>21st October 2017</td>
										<td>20,00,000</td>
										<td></td>
										<td></td>
									</tr>
								</tbody> -->
							</table>
						</div>
						<div class="ReverseAuction_details_containt">
							<h4>Reverse Auction</h4>
							<table
								class="TenderListingTable tableResponsive table table-striped table-bordered"
								width="100%" id="reverseAuctionTable">
								<thead>
									<tr>
										<!-- <th>Tender No</th>
                                                    <th>Description</th>
                                                    <th>Sale Opening</th>
                                                    <th>Sale Closing</th>
                                                    <th>Submission Due</th>
                                                    <th>Tender Fee(Rs)</th>  -->
										<th>Tender No</th>
										<th>Description</th>
										<th>Purchase FromDate</th>
										<th>Purchase ToDate</th>
										<th>TechnicalBid OpenDate</th>
										<th>Submission Due</th>
										<th>Tender Fee(Rs)</th>
										<th>Pre Bid MOM</th>
									</tr>
								</thead>
								<!-- <tbody>
									<tr>
										<td><a class="tenderdetailsTab" data-toggle="tab"
											href="#TenderDetails">TestrTender1103</a></td>
										<td>Browser Requirements</td>
										<td>21st October 2017</td>
										<td>21st October 2017</td>
										<td>21st October 2017</td>
										<td>20,00,000</td>
										<td></td>
										<td></td>
									</tr>
									<tr>
										<td><a class="tenderdetailsTab" data-toggle="tab"
											href="#TenderDetails">TestTender1103</a></td>
										<td>Browser Requirements</td>
										<td>21st October 2017</td>
										<td>21st October 2017</td>
										<td>21st October 2017</td>
										<td>20,00,000</td>
										<td></td>
										<td></td>
									</tr>
									<tr>
										<td><a class="tenderdetailsTab" data-toggle="tab"
											href="#TenderDetails">TestTender1103</a></td>
										<td>Browser Requirements</td>
										<td>21st October 2017</td>
										<td>21st October 2017</td>
										<td>21st October 2017</td>
										<td>20,00,000</td>
										<td></td>
										<td></td>
									</tr>
									<tr>
										<td><a class="tenderdetailsTab" data-toggle="tab"
											href="#TenderDetails">TestTender1103</a></td>
										<td>Browser Requirements</td>
										<td>21st October 2017</td>
										<td>21st October 2017</td>
										<td>21st October 2017</td>
										<td>20,00,000</td>
										<td></td>
										<td></td>
									</tr>
									<tr>
										<td><a class="tenderdetailsTab" data-toggle="tab"
											href="#TenderDetails">TestTender1103</a></td>
										<td>Browser Requirements</td>
										<td>21st October 2017</td>
										<td>21st October 2017</td>
										<td>21st October 2017</td>
										<td>20,00,000</td>
										<td></td>
										<td></td>
									</tr>

								</tbody> -->
							</table>
						</div>
					</div>
					<c:forEach var="authorisedTiles" items="${authorisedTiles}"
						varStatus="count">
						<c:if test="${authorisedTiles.value== 'Master'}">
							<div class="bhoechie-tab-content">
								<c:forEach var="authorisedMasterTiles"
									items="${authorisedMasterTiles}" varStatus="count">
									<c:if test="${authorisedMasterTiles== 'Add User'}">
										<div class="col-md-2 col-xs-6">
											<a href="getUserView" id="">
												<div class="tiles text-center">
													<i class="fa fa-eye pull-left font36"></i><i
														class="fa fa-plus pull-left font36"></i><i
														class="fa fa-pencil pull-left font36"></i>
													<h4 class="tiles-heading">Add User</h4>
													<h6 class="tiles-count"><i id = "addUser" class="fa fa-refresh fa-spin"></i> Records</h6>
													<div class="clearfix"></div>
												</div>
											</a>
										</div>
									</c:if>
									<c:if test="${authorisedMasterTiles== 'Material'}">
										<div class="col-md-2 col-xs-6">
											<a href="material" id="">
												<div class="tiles text-center">
													<i class="fa fa-eye pull-left font36"></i>
													<h4 class="tiles-heading">Material</h4>
													<h6 class="tiles-count"><i id = "Material" class="fa fa-refresh fa-spin"></i> Records</h6> 
													<div class="clearfix"></div>
												</div>
											</a>
										</div>
									</c:if>
									<c:if test="${authorisedMasterTiles== 'Document Type'}">
										<div class="col-md-2 col-xs-6">
											<a href="DocumentType" id="">
												<div class="tiles text-center">
													<i class="fa fa-eye pull-left font36"></i>
													<h4 class="tiles-heading">Document Type</h4>
													<h6 class="tiles-count"><i id = "DocumentType" class="fa fa-refresh fa-spin"></i> Records</h6> 
													<div class="clearfix"></div>
												</div>
											</a>
										</div>
									</c:if>
									<c:if test="${authorisedMasterTiles== 'Tender Type'}">
										<div class="col-md-2 col-xs-6">
											<a href="TenderType" id="">
												<div class="tiles text-center">
													<i class="fa fa-eye pull-left font36"></i>
													<h4 class="tiles-heading">Tender Type</h4>
													<h6 class="tiles-count"><i id = "TenderType" class="fa fa-refresh fa-spin"></i> Records</h6>
													<div class="clearfix"></div>
												</div>
											</a>
										</div>
									</c:if>
									<c:if test="${authorisedMasterTiles== 'Tender Budget Type'}">
										<div class="col-md-2 col-xs-6">
											<a href="TenderBudgetType" id="">
												<div class="tiles text-center">
													<i class="fa fa-eye pull-left font36"></i>
													<h4 class="tiles-heading">Tender Budget Type</h4>
													<h6 class="tiles-count"><i id = "TenderBudgetType" class="fa fa-refresh fa-spin"></i> Records</h6>
													<div class="clearfix"></div>
												</div>
											</a>
										</div>
									</c:if>
									<c:if test="${authorisedMasterTiles== 'Bid Type'}">
										<div class="col-md-2 col-xs-6">
											<a href="BidType" id="">
												<div class="tiles text-center">
													<i class="fa fa-eye pull-left font36"></i>
													<h4 class="tiles-heading">Bid Type</h4>
													<h6 class="tiles-count"><i id = "BidType" class="fa fa-refresh fa-spin"></i> Records</h6>													<div class="clearfix"></div>
												</div>
											</a>
										</div>
									</c:if>
									<c:if test="${authorisedMasterTiles== 'UOM'}">
										<div class="col-md-2 col-xs-6">
											<a href="uom" id="">
												<div class="tiles text-center">
													<i class="fa fa-eye pull-left font36"></i>
													<h4 class="tiles-heading">UOM</h4>
													<h6 class="tiles-count"><i id = "UOM" class="fa fa-refresh fa-spin"></i> Records</h6>
													<div class="clearfix"></div>
												</div>
											</a>
										</div>
									</c:if>
									<c:if test="${authorisedMasterTiles== 'Payment Type'}">
										<div class="col-md-2 col-xs-6">
											<a href="PaymentType" id="">
												<div class="tiles text-center">
													<i class="fa fa-eye pull-left font36"></i>
													<h4 class="tiles-heading">Payment Type</h4>
													<h6 class="tiles-count"><i id = "PaymentType" class="fa fa-refresh fa-spin"></i> Records</h6>
													<div class="clearfix"></div>
												</div>
											</a>
										</div>
									</c:if>
									<c:if test="${authorisedMasterTiles== 'Contractor Type'}">
										<div class="col-md-2 col-xs-6">
											<a href="ContractorType" id="">
												<div class="tiles text-center">
													<i class="fa fa-eye pull-left font36"></i>
													<h4 class="tiles-heading">Contractor Type</h4>
													<h6 class="tiles-count"><i id = "ContractorType" class="fa fa-refresh fa-spin"></i> Records</h6>
													<div class="clearfix"></div>
												</div>
											</a>
										</div>
									</c:if>
									<c:if test="${authorisedMasterTiles== 'Material Group'}">
										<div class="col-md-2 col-xs-6">
											<a href="materialGroup" id="">
												<div class="tiles text-center">
													<i class="fa fa-eye pull-left font36"></i>
													<h4 class="tiles-heading">Material Group</h4>
													<h6 class="tiles-count"><i id = "MaterialGroup" class="fa fa-refresh fa-spin"></i> Records</h6>
													<div class="clearfix"></div>
												</div>
											</a>
										</div>
									</c:if>
									<c:if test="${authorisedMasterTiles== 'Material SubGroup'}">
										<div class="col-md-2 col-xs-6">
											<a href="MaterialSubGroup" id="">
												<div class="tiles text-center">
													<i class="fa fa-eye pull-left font36"></i>
													<h4 class="tiles-heading">Material Sub-Group</h4>
													<h6 class="tiles-count"><i id = "MaterialSubGroup" class="fa fa-refresh fa-spin"></i> Records</h6>
													<div class="clearfix"></div>
												</div>
											</a>
										</div>
									</c:if>
									<c:if
										test="${authorisedMasterTiles== 'Material Specification'}">
										<div class="col-md-2 col-xs-6">
											<a href="MaterialSpecification" id="">
												<div class="tiles text-center">
													<i class="fa fa-eye pull-left font36"></i>
													<h4 class="tiles-heading">Material Specification</h4>
													<h6 class="tiles-count"><i id = "MaterialSpecification" class="fa fa-refresh fa-spin"></i> Records</h6>
													<div class="clearfix"></div>
												</div>
											</a>
										</div>
									</c:if>
									<c:if test="${authorisedMasterTiles== 'Department'}">
										<div class="col-md-2 col-xs-6">
											<a href="departmentView" id="">
												<div class="tiles text-center">
													<i class="fa fa-eye pull-left font36"></i>
													<h4 class="tiles-heading">Department</h4>
													<h6 class="tiles-count"><i id = "Department" class="fa fa-refresh fa-spin"></i> Records</h6>
													<div class="clearfix"></div>
												</div>
											</a>
										</div>
									</c:if>
									<c:if test="${authorisedMasterTiles== 'Material Version'}">
										<div class="col-md-2 col-xs-6">
											<a href="MaterialVersion" id="">
												<div class="tiles text-center">
													<i class="fa fa-eye pull-left font36"></i>
													<h4 class="tiles-heading">Material Version</h4>
													<h6 class="tiles-count"><i id = "MaterialVersion" class="fa fa-refresh fa-spin"></i> Records</h6>
													<div class="clearfix"></div>
												</div>
											</a>
										</div>
									</c:if>
									<c:if test="${authorisedMasterTiles== 'GTP Parameter'}">
										<div class="col-md-2 col-xs-6">
											<a href="GtpParameter" id="">
												<div class="tiles text-center">
													<i class="fa fa-eye pull-left font36"></i><i
														class="fa fa-plus pull-left font36"></i><i
														class="fa fa-pencil pull-left font36"></i>
													<h4 class="tiles-heading">GTP Parameters</h4>
													<h6 class="tiles-count"><i id = "GtpParameter" class="fa fa-refresh fa-spin"></i> Records</h6>
													<div class="clearfix"></div>
												</div>
											</a>
										</div>
									</c:if>
									<c:if test="${authorisedMasterTiles== 'GTP ParameterType'}">
										<div class="col-md-2 col-xs-6">
											<a href="GtpParameterType" id="">
												<div class="tiles text-center">
													<i class="fa fa-eye pull-left font36"></i><i
														class="fa fa-plus pull-left font36"></i><i
														class="fa fa-pencil pull-left font36"></i>
													<h4 class="tiles-heading">GTP Parameters Type</h4>
													<h6 class="tiles-count"><i id = "GtpParameterType" class="fa fa-refresh fa-spin"></i> Records</h6>
														<div class="clearfix"></div>
												</div>
											</a>
										</div>
									</c:if>
									<c:if test="${authorisedMasterTiles== 'Public Notices'}">
										<div class="col-md-2 col-xs-6">
											<a href="PublicNotice" id="">
												<div class="tiles text-center">
													<i class="fa fa-eye pull-left font36"></i><i
														class="fa fa-plus pull-left font36"></i><i
														class="fa fa-pencil pull-left font36"></i>
													<h4 class="tiles-heading">Public Notices</h4>
													<h6 class="tiles-count"><i id = "PublicNotice" class="fa fa-refresh fa-spin"></i> Records</h6>
													<div class="clearfix"></div>
												</div>
											</a>
										</div>
									</c:if>
									<c:if test="${authorisedMasterTiles== 'Is Standard'}">
										<div class="col-md-2 col-xs-6">
											<a href="IsStd" id="">
												<div class="tiles text-center">
													<i class="fa fa-eye pull-left font36"></i>
													<h4 class="tiles-heading">IS-STD</h4>
													<h6 class="tiles-count"><i id = "IsStd" class="fa fa-refresh fa-spin"></i> Records</h6>
													<div class="clearfix"></div>
												</div>
											</a>
										</div>
									</c:if>
									<c:if test="${authorisedMasterTiles== 'Designation'}">
										<div class="col-md-2 col-xs-6">
											<a href="designation" id="">
												<div class="tiles text-center">
													<i class="fa fa-eye pull-left font36"></i>
													<h4 class="tiles-heading">Designation</h4>
													<h6 class="tiles-count"><i id = "Designation" class="fa fa-refresh fa-spin"></i> Records</h6>
													<div class="clearfix"></div>
												</div>
											</a>
										</div>
									</c:if>
									<c:if test="${authorisedMasterTiles== 'Company Type'}">
										<div class="col-md-2 col-xs-6">
											<a href="CompanyType" id="">
												<div class="tiles text-center">
													<i class="fa fa-eye pull-left font36"></i>
													<h4 class="tiles-heading">Company Type</h4>
													<h6 class="tiles-count"><i id = "CompanyType" class="fa fa-refresh fa-spin"></i> Records</h6>
													<div class="clearfix"></div>
												</div>
											</a>
										</div>
									</c:if>
									<c:if test="${authorisedMasterTiles== 'Registration Type'}">
										<div class="col-md-2 col-xs-6">
											<a href="RegistrationType" id="">
												<div class="tiles text-center">
													<i class="fa fa-eye pull-left font36"></i>
													<h4 class="tiles-heading">Registration Type</h4>
													<h6 class="tiles-count"><i id = "RegistrationType" class="fa fa-refresh fa-spin"></i> Records</h6>
													<div class="clearfix"></div>
												</div>
											</a>
										</div>
									</c:if>
									<c:if test="${authorisedMasterTiles== 'Tax'}">
										<div class="col-md-2 col-xs-6">
											<a href="tax" id="">
												<div class="tiles text-center">
													<i class="fa fa-eye pull-left font36"></i>
													<h4 class="tiles-heading">Tax</h4>
													<h6 class="tiles-count"><i id = "Tax" class="fa fa-refresh fa-spin"></i> Records</h6>
													<div class="clearfix"></div>
												</div>
											</a>
										</div>
									</c:if>
									<c:if test="${authorisedMasterTiles== 'Tax Category'}">
										<div class="col-md-2 col-xs-6">
											<a href="TaxCategory" id="">
												<div class="tiles text-center">
													<i class="fa fa-eye pull-left font36"></i>
													<h4 class="tiles-heading">Tax Category</h4>
													<h6 class="tiles-count"><i id = "TaxCategory" class="fa fa-refresh fa-spin"></i> Records</h6>
													<div class="clearfix"></div>
												</div>
											</a>
										</div>
									</c:if>
									<c:if test="${authorisedMasterTiles== 'Holder Type'}">
										<div class="col-md-2 col-xs-6">
											<a href="HolderType" id="">
												<div class="tiles text-center">
													<i class="fa fa-eye pull-left font36"></i>
													<h4 class="tiles-heading">Holder Type</h4>
													<h6 class="tiles-count"><i id = "HolderType" class="fa fa-refresh fa-spin"></i> Records</h6>
													<div class="clearfix"></div>
												</div>
											</a>
										</div>
									</c:if>
									<c:if test="${authorisedMasterTiles== 'Role'}">
										<div class="col-md-2 col-xs-6">
											<a href="role" id="">
												<div class="tiles text-center">
													<i class="fa fa-eye pull-left font36"></i>
													<h4 class="tiles-heading">Role</h4>
													<h6 class="tiles-count"><i id = "Role" class="fa fa-refresh fa-spin"></i> Records</h6>
													<div class="clearfix"></div>
												</div>
											</a>
										</div>
									</c:if>
									<c:if test="${authorisedMasterTiles== 'Material Type'}">
										<div class="col-md-2 col-xs-6">
											<a href="MaterialType" id="">
												<div class="tiles text-center">
													<i class="fa fa-eye pull-left font36"></i>
													<h4 class="tiles-heading">Material Type</h4>
													<h6 class="tiles-count"><i id = "MaterialType" class="fa fa-refresh fa-spin"></i> Records</h6> 
													<div class="clearfix"></div>
												</div>
											</a>
										</div>
									</c:if>
									<c:if test="${authorisedMasterTiles== 'Vendor Type'}">
										<div class="col-md-2 col-xs-6">
											<a href="VendorType" id="">
												<div class="tiles text-center">
													<i class="fa fa-eye pull-left font36"></i>
													<h4 class="tiles-heading">Vendor Type</h4>
													<h6 class="tiles-count"><i id = "VendorType" class="fa fa-refresh fa-spin"></i> Records</h6>
													<div class="clearfix"></div>
												</div>
											</a>
										</div>
									</c:if>
									<c:if test="${authorisedMasterTiles== 'Exempted Categories'}">
										<div class="col-md-2 col-xs-6">
											<a href="ExemptedCategories" id="">
												<div class="tiles text-center">
													<i class="fa fa-eye pull-left font36"></i><i
														class="fa fa-plus pull-left font36"></i><i
														class="fa fa-pencil pull-left font36"></i>
													<h4 class="tiles-heading">Exempted Categories</h4>
													<h6 class="tiles-count"><i id = "ExemptedCategories" class="fa fa-refresh fa-spin"></i> Records</h6>
													<div class="clearfix"></div>
												</div>
											</a>
										</div>
									</c:if>
									<c:if test="${authorisedMasterTiles== 'Financial Documents'}">
										<div class="col-md-2 col-xs-6">
											<a href="FinancialDocuments" id="">
												<div class="tiles text-center">
													<i class="fa fa-eye pull-left font36"></i>
													<h4 class="tiles-heading">Financial Documents</h4>
													<h6 class="tiles-count"><i id = "FinancialDocuments" class="fa fa-refresh fa-spin"></i> Records</h6>
													<div class="clearfix"></div>
												</div>
											</a>
										</div>
									</c:if>
									<c:if
										test="${authorisedMasterTiles== 'Business Partner Group'}">
										<div class="col-md-2 col-xs-6">
											<a href="BusinessPartnerGroup" id="">
												<div class="tiles text-center">
													<i class="fa fa-eye pull-left font36"></i>
													<h4 class="tiles-heading">Business Partner Group</h4>
													<div class="clearfix"></div>
												</div>
											</a>
										</div>
									</c:if>
									<c:if
										test="${authorisedMasterTiles== 'CMS'}">
										<div class="col-md-2 col-xs-6">
                						<a href="cms" id="">
                    					<div class="tiles text-center">
			                        <i class="fa fa-eye pull-left font36"></i>
			                        <h4 class="tiles-heading">CMS</h4>
			                        <div class="clearfix"></div>
                    </div>
                </a>
                </div>
									</c:if>
								</c:forEach>
							</div>
						</c:if>
						<c:if test="${authorisedTiles.value== 'Tenders'}">
							<!-- train section -->
							<div class="bhoechie-tab-content">
								<c:forEach var="authorisedTenderTiles"
									items="${authorisedTenderTiles}" varStatus="count">
									<c:if test="${authorisedTenderTiles== 'Tenders Preparation'}">
										<div class="col-md-2 col-xs-6">
											<a href="<%=context%>/tenderPreparation">
												<div class="tiles text-center">
													<i class="fa fa-plus pull-left font36"></i>
													<h4 class="tiles-heading">Tender Preparation</h4>
													<h6 class="tiles-count"><i id = "TAHDR" class="fa fa-refresh fa-spin"></i> Records</h6>
													<div class="clearfix"></div>
												</div>
											</a>
										</div>
									</c:if>
									<c:if test="${authorisedTenderTiles== 'Tender Publishing'}">
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
									<c:if test="${authorisedTenderTiles== 'Tender Scheduling'}">
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
									<c:if test="${authorisedTenderTiles== 'Tender Purchase'}">
										<div class="col-md-2 col-xs-6">
											<a href="<%=context%>/tenderPurchase">
												<div class="tiles text-center">
													<i class="fa fa-plus pull-left font36"></i>
													<h4 class="tiles-heading">Tender Purchase</h4>
													<h6 class="tiles-count"><i id = "tenderPurchase" class="fa fa-refresh fa-spin"></i> Records</h6>
													<div class="clearfix"></div>
												</div>
											</a>
										</div>
									</c:if>
									<c:if test="${authorisedTenderTiles== 'Tender Submission'}">
										<div class="col-md-2 col-xs-6">
											<a href="<%=context%>/tenderSubmission">
												<div class="tiles text-center">
													<i class="fa fa-plus pull-left font36"></i>
													<h4 class="tiles-heading">Bid Submission</h4>
													<h6 class="tiles-count"><i id = "tenderSubmission" class="fa fa-refresh fa-spin"></i> Records</h6>
													<div class="clearfix"></div>
												</div>
											</a>
										</div>
									</c:if>
									<c:if test="${authorisedTenderTiles== 'Tender Opening'}">
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
									<c:if test="${authorisedTenderTiles== 'Final Scrutiny'}">
										<div class="col-md-2 col-xs-6">
											<a href="<%=context%>/finalScrutiny">
												<div class="tiles text-center">
													<i class="fa fa-plus pull-left font36"></i>
													<h4 class="tiles-heading">Final Scrutiny</h4>
													<h6 class="tiles-count"><i id = "finalScrutiny" class="fa fa-refresh fa-spin"></i> Records</h6>
													<div class="clearfix"></div>
												</div>
											</a>
										</div>
									</c:if>
									<c:if test="${authorisedTenderTiles== 'Preliminary Scrutiny'}">
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
									<c:if test="${authorisedTenderTiles== 'Deviation Bid'}">
										<div class="col-md-2 col-xs-6">
											<a href="<%=context%>/deviationBid" id="">
												<div class="tiles text-center">
													<i class="fa fa-plus pull-left font36"></i>
													<h4 class="tiles-heading">Deviation Bid</h4>
													<h6 class="tiles-count"><i id = "deviationBid" class="fa fa-refresh fa-spin"></i> Records</h6>
													<div class="clearfix"></div>
												</div>
											</a>
										</div>
									</c:if>
									<c:if test="${authorisedTenderTiles== 'Annexure C1'}">
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
									<c:if test="${authorisedTenderTiles== 'Tender Committee'}">
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
									
									<c:if test="${authorisedTenderTiles== 'Pre Bid Meeting'}">
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
									<c:if test="${authorisedTenderTiles== 'My Tender'}">
										<div class="col-md-2 col-xs-6">
											<a href="<%=context%>/myTenders" id="">
												<div class="tiles text-center">
													<i class="fa fa-plus pull-left font36"></i>
													<h4 class="tiles-heading">My Tender</h4>
													<h6 class="tiles-count"><i id = "myTender" class="fa fa-refresh fa-spin"></i> Records</h6>
													<div class="clearfix"></div>
												</div>
											</a>
										</div>
									</c:if>
									<c:if test="${authorisedTenderTiles== 'Tender Winner Selection'}">
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
									<c:if test="${authorisedTenderTiles== 'Bid Sheet'}">
									<div class="col-md-2 col-xs-6">
										<a href="<%=context%>/bidSheet">
											<div class="tiles text-center">
												<i class="fa fa-file-text-o pull-left font36"></i>
												<h4 class="tiles-heading">Bid Sheet</h4>
												<h6 class="tiles-count"><i id = "auctionBidSheet" class="fa fa-refresh fa-spin"></i> Records</h6>
												<div class="clearfix"></div>
											</div>
										</a>
									</div>
		          				  </c:if>
								</c:forEach>
								
							</div>
						</c:if>
						<c:if test="${authorisedTiles.value== 'Auction'}">
							<!-- hotel search -->
							<div class="bhoechie-tab-content">
							<c:forEach var="authorisedAuctionTiles"
									items="${authorisedAuctionTiles}" varStatus="count">
								<c:if test="${authorisedAuctionTiles== 'Auction Preparation'}">
								<div class="col-md-2 col-xs-6">
									<a href="<%=context%>/auctionPreparation" id="">
										<div class="tiles text-center">
											<i class="fa fa-plus pull-left font36"></i>
											<h4 class="tiles-heading">Auction Preparation</h4>
											<!-- <h6 class="tiles-count">1 Records</h6> -->
											<div class="clearfix"></div>
										</div>
									</a>
								</div>
								</c:if>
								<c:if test="${authorisedAuctionTiles== 'Auction Publishing'}">
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
								<c:if test="${authorisedAuctionTiles== 'Auction Scheduling'}">
								<div class="col-md-2 col-xs-6">
									<a href="<%=context%>/auctionScheduling" id="">
										<div class="tiles text-center">
											<i class="fa fa-plus pull-left font36"></i>
											<h4 class="tiles-heading">Auction Scheduling</h4>
											<h6 class="tiles-count"><i id = "auctionPublishing" class="fa fa-refresh fa-spin"></i> Records</h6>
											<div class="clearfix"></div>
										</div>
									</a>
								</div>
								</c:if>
								<c:if test="${authorisedAuctionTiles== 'Auction Purchase'}">
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
								<c:if test="${authorisedAuctionTiles== 'Auction Submission'}">
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
								<c:if test="${authorisedAuctionTiles== 'Auction Opening'}">
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
								<c:if test="${authorisedAuctionTiles== 'Auction Final Scrutiny'}">
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
								<c:if test="${authorisedAuctionTiles== 'Auction Details'}">
								<div class="col-md-2 col-xs-6">
									<a href="<%=context%>/auctionCommercialScrutiny" id="">
										<div class="tiles text-center">
											<i class="fa fa-plus pull-left font36"></i>
											<h4 class="tiles-heading">Auction Details</h4>
											<h6 class="tiles-count"><i id = "auctionDetails" class="fa fa-refresh fa-spin"></i> Records</h6>
											<div class="clearfix"></div>
										</div>
									</a>
									</div>
									</c:if>
								<c:if test="${authorisedAuctionTiles== 'Auction Preliminary Scrutiny'}">
									<div class="col-md-2 col-xs-6">
										<a href="<%=context%>/auctionTechnicalScrutiny" id="">
											<div class="tiles text-center">
												<i class="fa fa-plus pull-left font36"></i>
											<h6 class="tiles-count"><i id = "auctionPreliminaryScrutiny" class="fa fa-refresh fa-spin"></i> Records</h6>											<h6 class="tiles-count"><i id = "auctionFinalScrutiny" class="fa fa-refresh fa-spin"></i> Records</h6>												<div class="clearfix"></div>
											</div>
										</a>
									</div>
									</c:if>
									<c:if test="${authorisedAuctionTiles== 'Auction Committee'}">
										<div class="col-md-2 col-xs-6">
											<a href="<%=context%>/auctionCommittee" id="">
												<div class="tiles text-center">
													<i class="fa fa-plus pull-left font36"></i>
													<h4 class="tiles-heading">Auction Committee</h4>
											<h6 class="tiles-count"><i id = "auctionCommittee" class="fa fa-refresh fa-spin"></i> Records</h6>													<div class="clearfix"></div>
												</div>
											</a>
										</div>
									</c:if>
									<c:if test="${authorisedAuctionTiles== 'Auction Annexure C1'}">
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
									<c:if test="${authorisedAuctionTiles== 'Auction Pre Bid Meeting'}">
										<div class="col-md-2 col-xs-6">
											<a href="<%=context%>/auctionPreBidMeeting" id="">
												<div class="tiles text-center">
													<i class="fa fa-plus pull-left font36"></i>
													<h4 class="tiles-heading">Pre Bid Meeting</h4>
											<h6 class="tiles-count"><i id = "auctionPreBidMeeting" class="fa fa-refresh fa-spin"></i> Records</h6>													<div class="clearfix"></div>
												</div>
											</a>
										</div>
									</c:if>
									<%-- <c:if test="${authorisedAuctionTiles== 'Auction Deviation Bid'}">
										<div class="col-md-2 col-xs-6">
											<a href="<%=context%>/auctionDeviationBid" id="">
												<div class="tiles text-center">
													<i class="fa fa-plus pull-left font36"></i>
													<h4 class="tiles-heading">Deviation Bid</h4>
											<h6 class="tiles-count"><i id = "auctionDeviationBid" class="fa fa-refresh fa-spin"></i> Records</h6>													<div class="clearfix"></div>
												</div>
											</a>
										</div>
									</c:if> --%>
									<c:if test="${authorisedAuctionTiles== 'Auction Winner Selection'}">
					                     <div class="col-md-2 col-xs-6">
							                <a href="<%=context%>/auctionWinnerSelection">
							                <div class="tiles text-center">
							                    <i class="fa fa-file-pdf-o pull-left font36"></i>
							                    <h4 class="tiles-heading">Winner Selection</h4>
											<h6 class="tiles-count"><i id = "auctionWinnerSelection" class="fa fa-refresh fa-spin"></i> Records</h6>							                    <div class="clearfix"></div>
							                    </div>
					                		</a>
							            </div>
							       </c:if>
							       <c:if test="${authorisedAuctionTiles== 'My Auction'}">
							            <div class="col-md-2 col-xs-6">
							                <%-- <a href="<%=context%>/auctionWinnerSelection"> --%>
							                <a href="<%=context%>/myAuctions">
							                <div class="tiles text-center">
							                    <i class="fa fa-file-pdf-o pull-left font36"></i>
							                    <h4 class="tiles-heading">My Auction</h4>
							                    <h6 class="tiles-count"><i id = "myAuction" class="fa fa-refresh fa-spin"></i> Records</h6>
							                    <div class="clearfix"></div>
							                    </div>
					                		</a>
							            </div>
		           				  </c:if>
									<c:if test="${authorisedAuctionTiles== 'Auction Deviation Bid'}">
									<div class="col-md-2 col-xs-6">
										<a href="<%=context%>/auctionDeviationBid">
											<div class="tiles text-center">
												<i class="fa fa-plus pull-left font36"></i>
												<h4 class="tiles-heading">Auction Deviation Bid</h4>
											<h6 class="tiles-count"><i id = "" class="fa fa-refresh fa-spin"></i> Records</h6>												<div class="clearfix"></div>
											</div>
										</a>
									</div>
									</c:if>
									<c:if test="${authorisedAuctionTiles== 'Live Bid'}">
									<div class="col-md-2 col-xs-6">
										<a href="<%=context%>/liveBid">
											<div class="tiles text-center">
												<i class="fa fa-line-chart pull-left font36"></i>
												<h4 class="tiles-heading">Live Bid</h4>
											<h6 class="tiles-count"><i id = "" class="fa fa-refresh fa-spin"></i> Records</h6>												<div class="clearfix"></div>
											</div>
										</a>
									</div>
									</c:if>
									<c:if test="${authorisedAuctionTiles== 'Bid Sheet'}">
									<div class="col-md-2 col-xs-6">
										<a href="<%=context%>/auctionBidSheet">
											<div class="tiles text-center">
												<i class="fa fa-file-text-o pull-left font36"></i>
												<h4 class="tiles-heading">Bid Sheet</h4>
											<h6 class="tiles-count"><i id = "" class="fa fa-refresh fa-spin"></i> Records</h6>												<div class="clearfix"></div>
											</div>
										</a>
									</div>
									</c:if>
									</c:forEach>
									<div class="col-md-2 col-xs-6">
							                <a href="<%=context%>/QuickLiveBid">
							                <div class="tiles text-center">
							                    <i class="fa fa-file-pdf-o pull-left font36"></i>
							                    <h4 class="tiles-heading">Quick Live Bid</h4>
											<h6 class="tiles-count"><i id = "" class="fa fa-refresh fa-spin"></i> Records</h6>							                    <div class="clearfix"></div>
							                    </div>
					                		</a>
							            </div>
							            
								</div>
								</c:if>
								 
						<%-- <c:if test="${authorisedTiles.value== 'Bids'}">
							<div class="bhoechie-tab-content">
								<div class="col-md-2 col-xs-6">
									<a href="<%=context%>/bids">
										<div class="tiles text-center">
											<span class="glyphicon glyphicon-list-alt pull-left font36"></span>
											<h4 class="tiles-heading">Bids</h4>
											<h6 class="tiles-count"><i id = "" class="fa fa-refresh fa-spin"></i> Records</h6>											<div class="clearfix"></div>
										</div>
									</a>
								</div>
							</div>
						</c:if> --%>
						
						<c:if test="${authorisedTiles.value== 'Payments'}">
							<div class="bhoechie-tab-content">
								<div class="col-md-2 col-xs-6">
									<a href="<%=context%>/payments">
										<div class="tiles text-center">
											<i class="fa fa-money pull-left font36"></i>
											<h4 class="tiles-heading">Payments</h4>
											<h6 class="tiles-count"><i id = "" class="fa fa-refresh fa-spin"></i> Records</h6>											<div class="clearfix"></div>
										</div>
									</a>
								</div>
							</div>
						</c:if>
						<c:if test="${authorisedTiles.value== 'Infra Approval'}">
						<div class="bhoechie-tab-content">
			<div class="col-md-2 col-xs-6">
				<a href="<%=context%>/infraItemApproval">
					<div class="tiles text-center">
						<i class="fa fa-podcast pull-left font36"></i>
						<h4 class="tiles-heading">Infra Details</h4>
                   		<h6 class="tiles-count"></h6>
						<div class="clearfix"></div>
					</div>
				</a>
			</div>
			</div>
			</c:if>
						<%-- <c:if test="${authorisedTiles.value== 'Notification'}">
							<div class="bhoechie-tab-content">
								<div class="col-md-2 col-xs-6">
									<a href="<%=context%>/notification">
										<div class="tiles text-center">
											<i class="fa fa-envelope pull-left font36"></i>
											<h4 class="tiles-heading">Notification</h4>
											<h6 class="tiles-count"><i id = "notification" class="fa fa-refresh fa-spin"></i> Records</h6>
											<div class="clearfix"></div>
										</div>
									</a>
								</div>
							</div>
						</c:if> --%>
						<c:if test="${authorisedTiles.value== 'Session Audit'}">
							<div class="bhoechie-tab-content">
								<div class="col-md-2 col-xs-6">
									<a href="<%=context%>/sessionAudit">
										<div class="tiles text-center">
											<i class="fa fa-podcast pull-left font36"></i>
											<h4 class="tiles-heading">Session Audit</h4>
											<h6 class="tiles-count"><i id = "sessionAudit" class="fa fa-refresh fa-spin"></i> Records</h6>											<div class="clearfix"></div>
										</div>
									</a>
								</div>
							</div>
						</c:if>
						<c:if test="${authorisedTiles.value== 'Role Details'}">
							<div class="bhoechie-tab-content">
								<div class="col-md-2 col-xs-6">
									<a href="<%=context%>/roleDetails">
										<div class="tiles text-center">
											<i class="fa fa-info-circle pull-left font36"></i>
											<h4 class="tiles-heading">Role Details</h4>
											<h6 class="tiles-count"><i id = "roleDetails" class="fa fa-refresh fa-spin"></i> Records</h6>											<div class="clearfix"></div>
										</div>
									</a>
								</div>
							</div>
						</c:if>
						
						<c:if test="${authorisedTiles.value== 'Partner'}">
							<div class="bhoechie-tab-content">
								<div class="col-md-2 col-xs-6">
									<a href="<%=context%>/partnerRegistration">
										<div class="tiles text-center">
											<i class="fa fa-info-circle pull-left font36"></i>
											<h4 class="tiles-heading">Partner</h4>
											<h6 class="tiles-count"><i id = "" class="fa fa-refresh fa-spin"></i> Records</h6>											<div class="clearfix"></div>
										</div>
									</a>
								</div>
							</div>
						</c:if>
						<%-- <c:if test="${authorisedTiles.value== 'Purchase Proposal'}">
							<div class="bhoechie-tab-content">
								<div class="col-md-2 col-xs-6">
									<a href="<%=context%>/bids">
										<div class="tiles text-center">
											<i class="fa fa-info-circle pull-left font36"></i>
											<h4 class="tiles-heading">Purchase Proposal</h4>
											<h6 class="tiles-count"><i id = "" class="fa fa-refresh fa-spin"></i> Records</h6>											<div class="clearfix"></div>
										</div>
									</a>
								</div>
							</div>
						</c:if> --%>
						<%-- <c:if test="${authorisedTiles.value== 'Certificate'}">
							<div class="bhoechie-tab-content">
								<div class="col-md-2 col-xs-6">
									<a href="<%=context%>/bids">
										<div class="tiles text-center">
											<i class="fa fa-info-circle pull-left font36"></i>
											<h4 class="tiles-heading">Certificates</h4>
											<h6 class="tiles-count"><i id = "accessMasterDto" class="fa fa-refresh fa-spin"></i> Records</h6>											<div class="clearfix"></div>
										</div>
									</a>
								</div>
							</div>
						</c:if>
						<c:if test="${authorisedTiles.value== 'Reports'}">
							<div class="bhoechie-tab-content">
								<div class="col-md-2 col-xs-6">
									<a href="<%=context%>/bids">
										<div class="tiles text-center">
											<i class="fa fa-info-circle pull-left font36"></i>
											<h4 class="tiles-heading">Reports</h4>
											<!--                     <h6 class="tiles-count">2 Records Of User</h6> -->
											<div class="clearfix"></div>
										</div>
									</a>
								</div>
							</div>
						</c:if> --%>

					</c:forEach>
				</div>
			</div>
		</div>


		<!-- ProductDetailsTiles end-->
	</div>
	<div id="ViewTenderDetailModal" class="modal fade" style="display:none;" role="dialog">
					  <div class="modal-dialog modal-lg">
					
					    <!-- Modal content-->
					    <div class="modal-content">
					      <div class="modal-header">
					        <button type="button" class="close" data-dismiss="modal">&times;</button>
					        <h4 class="modal-title">Tender Details</h4>
					      </div>
					      <div class="modal-body">
									 <div class="form-group">
                                                    <label class="col-sm-3 col-xs-6">Tender Code</label>
                                                    <label class="col-sm-3 detspan col-xs-6" id = "tndrCode">
                                                        OPEN
                                                    </label>
                                                <div class="mobclearfix"></div>
                                                    <label class="col-sm-3 col-xs-6">Bid Type</label>
                                                    <label class="col-sm-3  detspan col-xs-6" id = "bidType">
                                                        
                                                    </label>
                                                    </div>
                                                    <div class="clearfix"></div>
                                                     <div class="form-group">
                                                    <label class="col-sm-3 col-xs-6" >Descipation Of Material</label>
                                                    <label class="col-sm-3  detspan col-xs-6" id = "materialDesc">
                                                        
                                                    </label>
                                                    <div class="mobclearfix"></div>
                                                    <label class="col-sm-3 col-xs-6">Estimated Cost inclusive of GST [in Rupees in Lakhs ]</label>
                                                    <label class="col-sm-3  detspan col-xs-6" id = "estimatedCost">
                                                          
                                                    </label>
                                                </div>
                                                <div class="clearfix"></div>
                                                <div class="form-group">
                                                    <label class="col-sm-3 col-xs-6">Tender Fee [in Rupees]</label>
                                                    <label class="col-sm-3 detspan col-xs-6" id = "tenderFee">
                                                         
                                                    </label>
                                                    <div class="mobclearfix"></div>
                                                    <label class="col-sm-3 col-xs-6">GST(@18% on Tender Fee:SAC No.9984) in Rs.</label>
                                                    <label class="col-sm-3 detspan col-xs-6" id = "gst">
                                                        
                                                    </label>
                                                </div>
                                                <div class="clearfix"></div>
                                                <div class="form-group">
                                                    <label class="col-sm-3 col-xs-6">Total Tender Fee Amount including GST in Rs.</label>
                                                    <Label class="col-sm-3 detspan col-xs-6" id = "ttltndrFee">
                                                        
                                                    </label>
                                                    <div class="mobclearfix"></div>
                                                    <label class="col-sm-3 col-xs-6">Tender Validity[in days]</label>
                                                    <label class="col-sm-3 detspan col-xs-6" >
                                                        Tender/offer should be kept valid for acceptance upto and including last day of the calender month covering the completion of <span id = "tndrVldt"></span> days from the  date of tender opening.
                                                    </label>
                                                </div>
                                                <div class="clearfix"></div>
                                                <div class="form-group">
                                                    <label class="col-sm-3 col-xs-6">Tender Sale Opening Date</label>
                                                    <label class="col-sm-3 detspan col-xs-6" id = "tndrSaleOpnDate">
                                                   
                                                    </label>
                                                    <div class="mobclearfix"></div>
                                                    <label class="col-sm-3 col-xs-6">Tender Sale Closing Date</label>
                                                    <label class="col-sm-3 detspan col-xs-6" id = "tndrSaleClsDate">
                                                       
                                                    </label> </div>
                                                <div class="clearfix"></div>
                                                <div class="form-group">
                                                    <label class="col-sm-3 col-xs-6">Contact email</label>
                                                    <label class="col-sm-3 detspan col-xs-6" id = "cntctEmail">
                                                       
                                                    </label>
                                                    <div class="mobclearfix"></div>
                                                    <label class="col-sm-3 col-xs-6">Submission Date</label>
                                                    <label class="col-sm-3 detspan col-xs-6" id = "sbmsnDate">
                                                         
                                                    </label>
                                                </div>
                                                <div class="clearfix"></div>
                                                <div class="form-group">
                                                    <label class="col-sm-3 col-xs-6">TECHNO-COMMERCIAL BID</label>
                                                    <label class="col-sm-3 detspan col-xs-6" id = "tchnCmrclBidDate">
                                                        
                                                    </label>  
                                                    <div class="mobclearfix"></div>                                              
                                                    <label class="col-sm-3 col-xs-6">PRICE BID</label>
                                                    <label class="col-sm-3 detspan col-xs-6" id = "prcBid">
                                                        
                                                    </label>
                                                </div>
                                                <div class="clearfix"></div>
                                                <div class="form-group">
                                                    <label class="col-sm-3 col-xs-6">DEVIATION BID</label>
                                                    <label class="col-sm-3 detspan col-xs-6" id = "dvsnDate">
                                                        
                                                    </label>
                                                    <div class="mobclearfix"></div>
                                                    <label class="col-sm-3 col-xs-6">ANNEXURE C-1</label>
                                                    <label class="col-sm-3 detspan col-xs-6" id = "anxrc1Date">
                                                       
                                                    </label>
                                                </div>
                                                <div class="clearfix"></div>
                                                <div class="form-group">
                                                    <label class="col-sm-3 col-xs-6">Download PDF</label>
                                                    <label class="col-sm-3 detspan col-xs-6">
<!--                                                         <button type="button" class="btn btn-default">Download</button>
 -->                                                        <a href ="" id = "dwnldTahdrDtil">Download PDF</a>
                                                    </label>                                             
                                                    
                                                </div>
                                                 <div class="clearfix"></div>
                                                <div class="form-group">
                                                	<h5>Note:</h5>
                                                	<ul>
                                                		<li>  (1) The registered vendors can purchase online Tender Documents on website https://cpa1.mahadiscom.in upto the last sale date & time of the tender, on payment of above specified non refundable tender fee by using net banking facility available on the website.</li>
                                                		<li>(2) The tender is to be submitted online on website https://cpa1.mahadiscom.in. Tender will be opened online on due date and time of opening.</li>
                                                		<li>(3) The tender documents are non-transferable. Change in name and address of any kind will not be entertained. The tenders duly signed with digital signature will be accepted upto due date and time of submission. It is advisable to submit the digitally signed tenders sufficiently in advance of due date & time, so as to avoid last minute trafficking at server.</li>
                                                		<li>(4) The tenders will not be accepted after due date and time of submission.</li>
                                                		<li>(5) If the date of opening happens to be holiday, it will stand extended to the next working day with no change in timings.</li>
                                                		<li>(6) The purchaser does not accept any responsibility for inability to use and / or for any delay in service provided by the site.</li>
                                                		<li>(7) The tender fee paid against the particular tender shall not be refunded / transferred / adjusted at all.</li>
                                                		<li>(8) The Company reserves the right to reject any or all the tenders without assigning any reason whatsoever.</li>
                                                		<li>(9) a. The participants vendors are requested to click on the link 'Calendar' (provided at the home page of cpa1.mahadiscom.in) regularly to check the submission & opening dates of all the bids (Techno commercial , Deviation , Price Bid , Annexure - 'C-1') Vendors are requested not to depend on the receipt of mails for knowing the submission & opening dates and follow the above procedure.</li>
                                                		<li>(9) b.The registered vendors are requested to check the validity of their vendor registration and prior to the expiry date they are requested to contact E.E.(M.M.Cell) , E-Auctionapp alongwith the renewal fee of Rs. 2950 (Rs. 2500+18% GST) by Crossed "A/C Payee" demand draft payable to "Maharashtra State Electricity Distribution Co.Ltd." drawn on any Nationalized Bank payable at Mumbai.</li>
                                                		<li>(9) c. The registered vendors are requested to update their vendor registration details & documents from time to time and get the approval of the E-Auctionapp authority accordingly after verification of original documents.</li>
                                                		<li>(9) d. The registered vendors are requested to check the validity of digital signature and prior to the expiry date they are requested to get their Digital signature key validated before expiry of the same. E-Auctionapp shall not be responsible for Non-submission of any of the Bids (Techno Commercial Bid, Deviation Bid, Price Bid, Annexure-C-1) by vendors due to expired/Invalid Digital signature. All vendors are requested to opt for class-III digital certificate from any of the certifying authority licensed from controller of certifying authorities (CCA) mentioned in certificate link(for registered vendor) & get digital certificate link (for new vendor) . You may please visit their center nearest to you or website or call on any of their phone numbers for new class-III digital signing certificate.</li>
                                                	</ul>
                                                </div>
                                                <div class="clearfix"></div>
					      </div>
					      <div class="modal-footer">
					        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
					      </div>
					    </div>
					
					  </div>
					</div>
	<script src="resources/${appMode}/commons/js/newDashboard.js?appVer=${appVer}"></script>
	<script	src="<%=request.getContextPath()%>/resources/${appMode}/commons/js/formFields.js?appVer=${appVer}"></script>
	<script	src="<%=request.getContextPath()%>/resources/${appMode}/commons/js/utility.js?appVer=${appVer}"></script>
	<%-- <script	src="<%=request.getContextPath()%>/resource/js/dashboard4TileRecCount.js?appVer=${appVer}"></script> --%>
	<script>$("div.bhoechie-tab-menu>div.list-group>a").click(function(e) {
        e.preventDefault();
        $(this).siblings('a.active').removeClass("active");
        $(this).addClass("active");
        var index = $(this).index();
        $("div.bhoechie-tab>div.bhoechie-tab-content").removeClass("active");
        $("div.bhoechie-tab>div.bhoechie-tab-content").eq(index).addClass("active");           
    });</script>
	<script>
  $(document).ready(function(){
   
    
  });
</script>
	<script>
$(document).ready(function(){
	
		$(".menu-toggle").click(function(e) {
			e.preventDefault();
			$('.dashcontent').toggleClass('fulldashcont');
		});
});
</script>
</body>