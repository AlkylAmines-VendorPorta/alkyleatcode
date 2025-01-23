<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@taglib uri = "http://www.springframework.org/tags/form" prefix = "sf"%>

<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/${appMode}/tilescommon/css/fioristyle.css?appVer=${appVer}"/>
<style>.backTolist{display:none !important;} .backTotils{display:none !important;}.k-tabstrip .k-content.k-state-active {
    background-color: transparent;
}.Firsttab {
    background-color: transparent !important;border:none !important;}
    .Firsttab>.k-tabstrip-items {
    background-color: transparent !important;
    
}
.k-panelbar>li.k-state-default>.k-link, .k-tabstrip-items .k-item .k-link {
    color: #fff;
    font-size: 16px;
}
.k-panelbar>li.k-state-default>.k-link, .k-tabstrip-items .k-item .k-link {
   color: #fff;
}
.Firsttab>.k-tabstrip-items>.k-item:hover {
    background-color: transparent !important; color:#cf271c !important; border-bottom: 4px solid #088eff !important;}
    .Firsttab>.k-tabstrip-items>.k-state-active>.k-link {
    color: #fff;
}
.Firsttab .k-content.k-state-active {
    border-top: 1px solid #038cff !important;
}

.k-tabstrip>.k-tabstrip-items>.k-item {
    background: transparent;
}.Firsttab>.k-tabstrip-items .k-state-active {
    background-color: transparent !important;    border-bottom: 4px solid #088eff !important;}
    .k-content, .k-list-container, .k-panel>li.k-item, .k-panelbar>li.k-item, .k-tiles {
    background-color: transparent;
    border: none !important;
}
</style>
	
	<!-- Context path Set -->
       <% String context=request.getContextPath(); %>
	<!-- Context path Set -->
	
 <body class="tiles_background">

<div class="full-container animated slideInLeft">
        <!-- MainProductTiles start-->
        <div id="MainProductTiles" class="container top20">
            <div class="container">
	<div class="col-sm-12">
	
      <ul class="nav nav-tabs" id="DashboradTab">
          	<li class="active"><a data-toggle="tab" href="#Dasboard">Dasboard</a></li>
          	<li><a data-toggle="tab" href="#MasterTab">Master</a></li>
          	<li><a data-toggle="tab" href="#AuctionTab">Auction</a></li>
          	<li><a data-toggle="tab" href="#TenderTab">Tender</a></li>
         	 <li><a data-toggle="tab" href="#Bids">Bids</a></li>  
          	<li><a data-toggle="tab" href="#RoleDetails">Role Details</a></li>	
         	 <li><a data-toggle="tab" href="#Certificate">Certificate</a></li> 
          	<li><a data-toggle="tab" href="#Profile">Profile</a></li>
          	<li><a data-toggle="tab" href="#PaymentApproval">Payment Approval</a></li>
          	<li><a data-toggle="tab" href="#Payment">Payment</a></li>
         	 <li><a data-toggle="tab" href="#Reports">Reports</a></li>
          	<li><a data-toggle="tab" href="#PurchaseProposal">Purchase Proposal</a></li>
          	<li><a data-toggle="tab" href="#Notification">Notification</a></li>
          
         
          <li><a data-toggle="tab" href="#MyTender">My Tender</a></li>
          <li><a data-toggle="tab" href="#DeviationBid">Deviation Bid</a></li>	
          <li><a data-toggle="tab" href="#WinnerSelection">Winner Selection</a></li>
          <li id="lastTab" class="button-dropdown">
             <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button">More<span class="caret"></span></a>
            <ul class="dropdown-menu" id="collapsed">
              
            </ul>
          </li>
          
      </ul>
      
      <!-- Tab panes -->
  <div class="tab-content">
    <div id="Dasboard" class="tab-pane active"> 
    <div class="col-sm-12" style="background:#fff; padding-top:10px;">
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
								<tbody>
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

								</tbody>
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
								<tbody>
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

								</tbody>
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
								<tbody>
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
								</tbody>
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
								<tbody>
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

								</tbody>
							</table>
						</div>    
    </div>
    </div>
    <div id="MasterTab" class="tab-pane fade">    
           <div  class="col-md-2 col-xs-6">
             <a href="getUserView" id="">
                    <div class="tiles text-center">
                        <i class="fa fa-eye pull-left font36"></i><i class="fa fa-plus pull-left font36"></i><i class="fa fa-pencil pull-left font36"></i>
                        <h4 class="tiles-heading">Add User</h4>
                        <div class="clearfix"></div>
                    </div>
                </a>
               </div>
               <div class="col-md-2 col-xs-6">
                <a href="material" id="">
                    <div class="tiles text-center">
                        <i class="fa fa-eye pull-left font36"></i>
                        <h4 class="tiles-heading">Material</h4>
                        <div class="clearfix"></div>
                    </div>
                </a>
                </div>
               <div class="col-md-2 col-xs-6">
                <a href="DocumentType" id="" >
                    <div class="tiles text-center">
                        <i class="fa fa-eye pull-left font36"></i>
                        <h4 class="tiles-heading">Document Type</h4>
                        <div class="clearfix"></div>
                    </div>
                </a>
                </div>
                <div class="col-md-2 col-xs-6">
                <a href="TenderType" id="">
                    <div class="tiles text-center">
                        <i class="fa fa-eye pull-left font36"></i>
                        <h4 class="tiles-heading">Tender Type</h4>
                        <div class="clearfix"></div>
                    </div>
                </a>
                </div>
               <div class="col-md-2 col-xs-6">
                <a href="TenderBudgetType" id="">
                    <div class="tiles text-center">
                        <i class="fa fa-eye pull-left font36"></i>
                        <h4 class="tiles-heading">Tender Budget Type</h4>
                        <div class="clearfix"></div>
                    </div>
                </a>
                </div>
                <div class="col-md-2 col-xs-6">
                <a href="BidType" id="">
                    <div class="tiles text-center">
                        <i class="fa fa-eye pull-left font36"></i>
                        <h4 class="tiles-heading">Bid Type</h4>
                        <div class="clearfix"></div>
                    </div>
                </a>
                </div>
                <div class="col-md-2 col-xs-6">
                <a href="uom" id="" >
                    <div class="tiles text-center">
                        <i class="fa fa-eye pull-left font36"></i>
                        <h4 class="tiles-heading">UOM</h4>
                        <div class="clearfix"></div>
                    </div>
                </a>
                </div>
                <div class="col-md-2 col-xs-6">
                <a href="PaymentType" id="">
                    <div class="tiles text-center">
                        <i class="fa fa-eye pull-left font36"></i>
                        <h4 class="tiles-heading">Payment Type</h4>
                        <div class="clearfix"></div>
                    </div>
                </a>
                </div>
               <div class="col-md-2 col-xs-6">
                <a href="ContractorType" id="">
                    <div class="tiles text-center">
                        <i class="fa fa-eye pull-left font36"></i>
                        <h4 class="tiles-heading">Contractor Type</h4>
                        <div class="clearfix"></div>
                    </div>
                </a>
                </div>
               <div class="col-md-2 col-xs-6">
                <a href="materialGroup" id="">
                    <div class="tiles text-center">
                        <i class="fa fa-eye pull-left font36"></i>
                        <h4 class="tiles-heading">Material Group</h4>
                        <div class="clearfix"></div>
                    </div>
                </a>
                </div>
                <div class="col-md-2 col-xs-6">
                <a href="MaterialSubGroup" id="">
                    <div class="tiles text-center">
                        <i class="fa fa-eye pull-left font36"></i>
                        <h4 class="tiles-heading">Material Sub-Group</h4>
                        <div class="clearfix"></div>
                    </div>
                </a>
                </div>
               <div class="col-md-2 col-xs-6">
                <a href="MaterialSpecification" id="">
                    <div class="tiles text-center">
                        <i class="fa fa-eye pull-left font36"></i>
                        <h4 class="tiles-heading">Material Specification</h4>
                        <div class="clearfix"></div>
                    </div>
                </a>
                </div>
                <div class="col-md-2 col-xs-6">
                <a href="departmentView" id="">
                    <div class="tiles text-center">
                        <i class="fa fa-eye pull-left font36"></i>
                        <h4 class="tiles-heading">Department</h4>
                        <div class="clearfix"></div>
                    </div>
                </a>
                </div>
                <div class="col-md-2 col-xs-6">
                <a href="MaterialVersion" id="" >
                    <div class="tiles text-center">
                        <i class="fa fa-eye pull-left font36"></i><i class="fa fa-plus pull-left font36"></i><i class="fa fa-pencil pull-left font36"></i>
                        <h4 class="tiles-heading">Material Version</h4>
                        <div class="clearfix"></div>
                    </div>
                </a>
                </div>
                 <div class="col-md-2 col-xs-6">
                <a href="GtpParameter" id="">
                    <div class="tiles text-center">
                        <i class="fa fa-eye pull-left font36"></i><i class="fa fa-plus pull-left font36"></i><i class="fa fa-pencil pull-left font36"></i>
                        <h4 class="tiles-heading">GTP Parameters</h4>
                        <div class="clearfix"></div>
                    </div>
                </a>
                </div>
              	<div class="col-md-2 col-xs-6">
                <a href="GtpParameterType" id="">
                    <div class="tiles text-center">
                        <i class="fa fa-eye pull-left font36"></i><i class="fa fa-plus pull-left font36"></i><i class="fa fa-pencil pull-left font36"></i>
                        <h4 class="tiles-heading">GTP Parameters Type</h4>
                        <div class="clearfix"></div>
                    </div>
                </a>
                </div>
                <div  class="col-md-2 col-xs-6">
                <a href="PublicNotice" id="">
                    <div class="tiles text-center">
                        <i class="fa fa-eye pull-left font36"></i><i class="fa fa-plus pull-left font36"></i><i class="fa fa-pencil pull-left font36"></i>
                        <h4 class="tiles-heading">Public Notices</h4>
                        <div class="clearfix"></div>
                    </div>
                </a>
                </div>
                <div class="col-md-2 col-xs-6">
                <a href="IsStd" id="">
                    <div class="tiles text-center">
                        <i class="fa fa-eye pull-left font36"></i>
                        <h4 class="tiles-heading">IS-STD</h4>
                        <div class="clearfix"></div>
                    </div>
                </a>
                </div>
                 <div class="col-md-2 col-xs-6">
                <a href="designation" id="">
                    <div class="tiles text-center">
                        <i class="fa fa-eye pull-left font36"></i>
                        <h4 class="tiles-heading">Designation</h4>
                        <div class="clearfix"></div>
                    </div>
                </a>
                </div>
                <div class="col-md-2 col-xs-6">
                <a href="CompanyType" id="">
                    <div class="tiles text-center">
                        <i class="fa fa-eye pull-left font36"></i>
                        <h4 class="tiles-heading">Company Type</h4>
                        <div class="clearfix"></div>
                    </div>
                </a>
                </div>
           		<div class="col-md-2 col-xs-6">
                <a href="RegistrationType" id="">
                    <div class="tiles text-center">
                        <i class="fa fa-eye pull-left font36"></i>
                        <h4 class="tiles-heading">Registration Type</h4>
                        <div class="clearfix"></div>
                    </div>
                </a>
                </div>
                 <div class="col-md-2 col-xs-6">
                <a href="tax" id="">
                    <div class="tiles text-center">
                        <i class="fa fa-eye pull-left font36"></i>
                        <h4 class="tiles-heading">Tax</h4>
                        <div class="clearfix"></div>
                    </div>
                </a>
                </div>
                <div class="col-md-2 col-xs-6">
                <a href="TaxCategory" id="" >
                    <div class="tiles text-center">
                        <i class="fa fa-eye pull-left font36"></i>
                        <h4 class="tiles-heading">Tax Category</h4>
                        <div class="clearfix"></div>
                    </div>
                </a>
                </div>
                <div class="col-md-2 col-xs-6">
                <a href="HolderType" id="">
                    <div class="tiles text-center">
                        <i class="fa fa-eye pull-left font36"></i>
                        <h4 class="tiles-heading">Holder Type</h4>
                        <div class="clearfix"></div>
                    </div>
                </a>
                </div>
                 <div class="col-md-2 col-xs-6">
                 <a href="role" id="">
                    <div class="tiles text-center">
                        <i class="fa fa-eye pull-left font36"></i>
                        <h4 class="tiles-heading">Role</h4>
                        <div class="clearfix"></div>
                    </div>
                </a>
                </div>
                 <div class="col-md-2 col-xs-6">
                <a href="MaterialType" id="">
                    <div class="tiles text-center">
                        <i class="fa fa-eye pull-left font36"></i>
                        <h4 class="tiles-heading">Material Type</h4>
                        <div class="clearfix"></div>
                    </div>
                </a>
                </div>
                <div class="col-md-2 col-xs-6">
                <a href="VendorType" id="">
                    <div class="tiles text-center">
                        <i class="fa fa-eye pull-left font36"></i>
                        <h4 class="tiles-heading">Vendor Type</h4>
                        <div class="clearfix"></div>
                    </div>
                   
                </a>
                 </div>
                 <div class="col-md-2 col-xs-6">
                <a href="ExemptedCategories" id="">
                    <div class="tiles text-center">
                        <i class="fa fa-eye pull-left font36"></i><i class="fa fa-plus pull-left font36"></i><i class="fa fa-pencil pull-left font36"></i>
                        <h4 class="tiles-heading">Exempted Categories</h4>
                        <div class="clearfix"></div>
                    </div>
                </a>
                </div>
                <div class="col-md-2 col-xs-6">
                 <a href="FinancialDocuments" id="">
                    <div class="tiles text-center">
                        <i class="fa fa-eye pull-left font36"></i>
                        <h4 class="tiles-heading">Financial Documents</h4>
                        <div class="clearfix"></div>
                    </div>
                </a>
                </div>
                <div class="col-md-2 col-xs-6">
                 <a href="BusinessPartnerGroup" id="">
                    <div class="tiles text-center">
                        <i class="fa fa-eye pull-left font36"></i>
                        <h4 class="tiles-heading">Business Partner Group</h4>
                        <div class="clearfix"></div>
                    </div>
                </a>
                </div>
                <div class="col-md-2 col-xs-6">
                <a href="BusinessPartnerGroup" id="">
                    <div class="tiles text-center">
                        <i class="fa fa-eye pull-left font36"></i>
                        <h4 class="tiles-heading">Reference</h4>
                        <div class="clearfix"></div>
                    </div>
                </a>
                </div>
    </div>
    <div id="AuctionTab" class="tab-pane fade">
					<div class="col-md-2 col-xs-6">
                    <a href="<%=context%>/tenderPreparation" id="">
                        <div class="tiles text-center">
                            <i class="fa fa-plus pull-left font36"></i>
                            <h4 class="tiles-heading">Auction Preparation </h4>
                            <!-- <h6 class="tiles-count">1 Records</h6> -->
                            <div class="clearfix"></div>
                        </div>
                    </a>
                    </div>
					<div class="col-md-2 col-xs-6">
                    <a href="<%=context%>/tenderPublishing" id="">
                        <div class="tiles text-center">
                            <i class="fa fa-plus pull-left font36"></i>
                            <h4 class="tiles-heading">Auction Publishing</h4>
                            <!-- <h6 class="tiles-count">0 Records</h6> -->
                            <div class="clearfix"></div>
                        </div>
                    </a>
                    </div>
					<div class="col-md-2 col-xs-6">
                    <a href="<%=context%>/tenderScheduling" id="">
                        <div class="tiles text-center">
                            <i class="fa fa-plus pull-left font36"></i>
                            <h4 class="tiles-heading">Auction Scheduling</h4>
                            <!-- <h6 class="tiles-count">0 Records</h6> -->
                            <div class="clearfix"></div>
                        </div>
                    </a>
                    </div>
					<div class="col-md-2 col-xs-6">
                    <a href="<%=context%>/tenderPurchase" id="">
                        <div class="tiles text-center">
                            <i class="fa fa-plus pull-left font36"></i>
                            <h4 class="tiles-heading">Auction Purchase</h4>
                            <!-- <h6 class="tiles-count">0 Records</h6> -->
                            <div class="clearfix"></div>
                        </div>
                    </a>
                    </div>
					<div class="col-md-2 col-xs-6">
                    <a href="<%=context%>/tenderSubmission" id="">
                        <div class="tiles text-center">
                            <i class="fa fa-plus pull-left font36"></i>
                            <h4 class="tiles-heading">Tender Submission</h4>
                            <!-- <h6 class="tiles-count">0 Records</h6> -->
                            <div class="clearfix"></div>
                        </div>
                    </a>
                    </div>
					<div class="col-md-2 col-xs-6">
                    <a href="<%=context%>/tenderOpening" id="">
                        <div class="tiles text-center">
                            <i class="fa fa-plus pull-left font36"></i>
                            <h4 class="tiles-heading">Auction Opening</h4>
                            <!-- <h6 class="tiles-count">0 Records</h6> -->
                            <div class="clearfix"></div>
                        </div>
                    </a>
                    </div>
					<div class="col-md-2 col-xs-6">
                    <a href="<%=context%>/finalScrutiny" id="">
                        <div class="tiles text-center">
                            <i class="fa fa-plus pull-left font36"></i>
                            <h4 class="tiles-heading">Final Scrutiny</h4>
                            <!-- <h6 class="tiles-count">0 Records</h6> -->
                            <div class="clearfix"></div>
                        </div>
                    </a>
                    </div>
                    <div class="col-md-2 col-xs-6">
                    <a href="tenderCommercialScrutiny" id="">
                        <div class="tiles text-center">
                            <i class="fa fa-plus pull-left font36"></i>
                            <h4 class="tiles-heading">Auction Details</h4>
                            <!-- <h6 class="tiles-count">0 Records</h6> -->
                            <div class="clearfix"></div>
                        </div>
                    </a>
                    </div>
					<div class="col-md-2 col-xs-6">
                    <a href="<%=context%>/tenderTechnicalScrutiny" id="">
                        <div class="tiles text-center">
                            <i class="fa fa-plus pull-left font36"></i>
                            <h4 class="tiles-heading">Preliminary Scrutiny</h4>
                            <!-- <h6 class="tiles-count">0 Records</h6> -->
                            <div class="clearfix"></div>
                        </div>
                    </a>
                    </div>
					<div class="col-md-2 col-xs-6">
                    <a href="<%=context%>/annexureC1" id="">
                        <div class="tiles text-center">
                            <i class="fa fa-plus pull-left font36"></i>
                            <h4 class="tiles-heading">Annexure C1</h4>
                            <!-- <h6 class="tiles-count">0 Records</h6> -->
                            <div class="clearfix"></div>
                        </div>
                    </a> 
                    </div>
                     <div class="col-md-2 col-xs-6">
		                    <a href="<%=context%>/tenderCommittee" id="">
		                        <div class="tiles text-center">
		                            <i class="fa fa-plus pull-left font36"></i>
		                            <h4 class="tiles-heading">Auction Committee</h4>
		                            <!-- <h6 class="tiles-count">0 Records</h6> -->
		                            <div class="clearfix"></div>
		                        </div>
		                    </a>
		                    </div>
                     <div class="col-md-2 col-xs-6">
	                    <a href="<%=context%>/preBidMeeting" id="">
	                        <div class="tiles text-center">
	                            <i class="fa fa-plus pull-left font36"></i>
	                            <h4 class="tiles-heading">Pre Bid Meeting</h4>
	                            <!-- <h6 class="tiles-count">0 Records</h6> -->
	                            <div class="clearfix"></div>
	                        </div>
	                    </a>
	                    </div>
                     <div class="col-md-2 col-xs-6">
		                <a href="<%=context%>/deviationBid">
		                <div class="tiles text-center">
		                    <i class="fa fa-file-pdf-o pull-left font36"></i>
		                    <h4 class="tiles-heading">Deviation Bid</h4>
		                <!--     <h6 class="tiles-count">7 Records Of TendAuction6> -->
		                    <div class="clearfix"></div>
		                    </div>
                		</a>
		            </div>
                     <div class="col-md-2 col-xs-6">
		                <a href="<%=context%>/tenderApproval">
		                <div class="tiles text-center">
		                    <i class="fa fa-file-pdf-o pull-left font36"></i>
		                    <h4 class="tiles-heading">Auction Approval</h4>
		                <!--     <h6 class="tiles-count">7 Records Of Tenders</h6> -->
		                    <div class="clearfix"></div>
		                    </div>
                		</a>
		            </div>
    </div>
    <div id="TenderTab" class="tab-pane fade">
    <div class="col-md-2 col-xs-6">
                    <a href="<%=context%>/tenderPreparation" id="">
                        <div class="tiles text-center">
                            <i class="fa fa-plus pull-left font36"></i>
                            <h4 class="tiles-heading">Tender Preparation </h4>
                            <!-- <h6 class="tiles-count">1 Records</h6> -->
                            <div class="clearfix"></div>
                        </div>
                    </a>
                    </div>
					<div class="col-md-2 col-xs-6">
                    <a href="<%=context%>/tenderPublishing" id="">
                        <div class="tiles text-center">
                            <i class="fa fa-plus pull-left font36"></i>
                            <h4 class="tiles-heading">Tender Publishing</h4>
                            <!-- <h6 class="tiles-count">0 Records</h6> -->
                            <div class="clearfix"></div>
                        </div>
                    </a>
                    </div>
					<div class="col-md-2 col-xs-6">
                    <a href="<%=context%>/tenderScheduling" id="">
                        <div class="tiles text-center">
                            <i class="fa fa-plus pull-left font36"></i>
                            <h4 class="tiles-heading">Tender Scheduling</h4>
                            <!-- <h6 class="tiles-count">0 Records</h6> -->
                            <div class="clearfix"></div>
                        </div>
                    </a>
                    </div>
					<div class="col-md-2 col-xs-6">
                    <a href="<%=context%>/tenderPurchase" id="">
                        <div class="tiles text-center">
                            <i class="fa fa-plus pull-left font36"></i>
                            <h4 class="tiles-heading">Tender Purchase</h4>
                            <!-- <h6 class="tiles-count">0 Records</h6> -->
                            <div class="clearfix"></div>
                        </div>
                    </a>
                    </div>
					<div class="col-md-2 col-xs-6">
                    <a href="<%=context%>/tenderSubmission" id="">
                        <div class="tiles text-center">
                            <i class="fa fa-plus pull-left font36"></i>
                            <h4 class="tiles-heading">Tender Submission</h4>
                            <!-- <h6 class="tiles-count">0 Records</h6> -->
                            <div class="clearfix"></div>
                        </div>
                    </a>
                    </div>
					<div class="col-md-2 col-xs-6">
                    <a href="<%=context%>/tenderOpening" id="">
                        <div class="tiles text-center">
                            <i class="fa fa-plus pull-left font36"></i>
                            <h4 class="tiles-heading">Tender Opening</h4>
                            <!-- <h6 class="tiles-count">0 Records</h6> -->
                            <div class="clearfix"></div>
                        </div>
                    </a>
                    </div>
					<div class="col-md-2 col-xs-6">
                    <a href="<%=context%>/finalScrutiny" id="">
                        <div class="tiles text-center">
                            <i class="fa fa-plus pull-left font36"></i>
                            <h4 class="tiles-heading">Final Scrutiny</h4>
                            <!-- <h6 class="tiles-count">0 Records</h6> -->
                            <div class="clearfix"></div>
                        </div>
                    </a>
                    </div>
                    <div class="col-md-2 col-xs-6">
                    <a href="tenderCommercialScrutiny" id="">
                        <div class="tiles text-center">
                            <i class="fa fa-plus pull-left font36"></i>
                            <h4 class="tiles-heading">Tender Details</h4>
                            <!-- <h6 class="tiles-count">0 Records</h6> -->
                            <div class="clearfix"></div>
                        </div>
                    </a>
                    </div>
					<div class="col-md-2 col-xs-6">
                    <a href="<%=context%>/tenderTechnicalScrutiny" id="">
                        <div class="tiles text-center">
                            <i class="fa fa-plus pull-left font36"></i>
                            <h4 class="tiles-heading">Preliminary Scrutiny</h4>
                            <!-- <h6 class="tiles-count">0 Records</h6> -->
                            <div class="clearfix"></div>
                        </div>
                    </a>
                    </div>
					<div class="col-md-2 col-xs-6">
                    <a href="<%=context%>/annexureC1" id="">
                        <div class="tiles text-center">
                            <i class="fa fa-plus pull-left font36"></i>
                            <h4 class="tiles-heading">Annexure C1</h4>
                            <!-- <h6 class="tiles-count">0 Records</h6> -->
                            <div class="clearfix"></div>
                        </div>
                    </a> 
                    </div>
                     <div class="col-md-2 col-xs-6">
		                    <a href="<%=context%>/tenderCommittee" id="">
		                        <div class="tiles text-center">
		                            <i class="fa fa-plus pull-left font36"></i>
		                            <h4 class="tiles-heading">Tender Committee</h4>
		                            <!-- <h6 class="tiles-count">0 Records</h6> -->
		                            <div class="clearfix"></div>
		                        </div>
		                    </a>
		                    </div>
                     <div class="col-md-2 col-xs-6">
	                    <a href="<%=context%>/preBidMeeting" id="">
	                        <div class="tiles text-center">
	                            <i class="fa fa-plus pull-left font36"></i>
	                            <h4 class="tiles-heading">Pre Bid Meeting</h4>
	                            <!-- <h6 class="tiles-count">0 Records</h6> -->
	                            <div class="clearfix"></div>
	                        </div>
	                    </a>
	                    </div>
                     <div class="col-md-2 col-xs-6">
		                <a href="<%=context%>/deviationBid">
		                <div class="tiles text-center">
		                    <i class="fa fa-file-pdf-o pull-left font36"></i>
		                    <h4 class="tiles-heading">Deviation Bid</h4>
		                <!--     <h6 class="tiles-count">7 Records Of Tenders</h6> -->
		                    <div class="clearfix"></div>
		                    </div>
                		</a>
		            </div>
                     <div class="col-md-2 col-xs-6">
		                <a href="<%=context%>/tenderApproval">
		                <div class="tiles text-center">
		                    <i class="fa fa-file-pdf-o pull-left font36"></i>
		                    <h4 class="tiles-heading">Tender Approval</h4>
		                <!--     <h6 class="tiles-count">7 Records Of Tenders</h6> -->
		                    <div class="clearfix"></div>
		                    </div>
                		</a>
		            </div>
    </div>
    <div id="Bids" class="tab-pane fade">
    	<div class="col-md-2 col-xs-6">
                <a href="bidsdetails">
                	<div  class="tiles text-center">
                    <span class="glyphicon glyphicon-list-alt pull-left font36"></span>
                    <h4 class="tiles-heading">Bids</h4>
                    <h6 class="tiles-count">1 Records</h6>
                    <div class="clearfix"></div>
                    </div>
                </a>
            </div>
    </div>
    <div id="RoleDetails" class="tab-pane fade">
    	<div class="col-md-2 col-xs-6">
                <a href="<%=context%>/roleDetails">
                <div class="tiles text-center">
                    <i class="fa fa-info-circle pull-left font36"></i>
                    <h4 class="tiles-heading">Role Details</h4>
                    <h6 class="tiles-count">1 Records</h6>
                    <div class="clearfix"></div>
                    </div>
                </a>
             </div>
    </div>
    <div id="Certificate" class="tab-pane fade">
    	<div class="col-md-2 col-xs-6">
                <a href="#">
                <div class="tiles text-center">
                    <i class="fa fa-certificate pull-left font36"></i>
                    <h4 class="tiles-heading">Certificate</h4>
                    <h6 class="tiles-count">1 Records</h6>
                    <div class="clearfix"></div>
                    </div>
                </a>
            </div>
    </div>
    <div id="Profile" class="tab-pane fade">
    	<div class="col-md-2 col-xs-6">
                <a href="<%=context%>/partnerProfiles">
                <div class="tiles text-center">
                    <i class="fa fa-address-card-o pull-left font36"></i>
                    <h4 class="tiles-heading">Partners</h4>
                    <h6 class="tiles-count">1 Records</h6>
                    <div class="clearfix"></div>
                    </div>
                </a>
            </div>
    </div>
    <div id="PaymentApproval" class="tab-pane fade">
    	<div class="col-md-2 col-xs-6">
                <a href="<%=context%>/approval">
                	<div class="tiles text-center">
                    <i class="fa fa-money pull-left font36"></i>
                    <h4 class="tiles-heading">Payment Approval</h4>
                    <h6 class="tiles-count">1 Records</h6>
                    <div class="clearfix"></div>
                    </div>
                </a>
            </div>
    </div>
    <div id="Payment" class="tab-pane fade">
    	 <div class="col-md-2 col-xs-6">
                <a href="payments">
                <div class="tiles text-center">
                    <i class="fa fa-money pull-left font36"></i>
                    <h4 class="tiles-heading">Payment</h4>
                    <h6 class="tiles-count">1 Records</h6>
                    <div class="clearfix"></div>
                    </div>
                </a>
            </div>
    </div>
    <div id="Reports" class="tab-pane fade">
    	<div class="col-md-2 col-xs-6">
                <a href="#">
                	<div class="tiles text-center">
                    <i class="fa fa-file-text pull-left font36"></i>
                    <h4 class="tiles-heading">Reports</h4>
                    <h6 class="tiles-count">1 Records</h6>
                    <div class="clearfix"></div>
                    </div>
                </a>
            </div>
    </div>
    <div id="PurchaseProposal" class="tab-pane fade">
   		<div class="col-md-2 col-xs-6">
                <a href="#">
                <div class="tiles text-center">
                    <i class="fa fa-shopping-cart pull-left font36"></i>
                    <h4 class="tiles-heading">Purchase Proposal</h4>
                    <h6 class="tiles-count">1 Records</h6>
                    <div class="clearfix"></div>
                    </div>
                </a>
            </div>
    </div>
    <div id="Notification" class="tab-pane fade">
    	<div class="col-md-2 col-xs-6">
                <a href="<%=context%>/mail">
                	<div class="tiles text-center">
                    <i class="fa fa-envelope pull-left font36"></i>
                    <h4 class="tiles-heading">Notification</h4>
                    <h6 class="tiles-count">1 Records</h6>
                    <div class="clearfix"></div>
                    </div>
                </a>
            </div>
    </div>
    <div id="MyTender" class="tab-pane fade">
    	<div class="col-md-2 col-xs-6">
                <a href="<%=context%>/myTenders">
                	<div class="tiles text-center">
                    <i class="fa fa-file-pdf-o pull-left font36"></i>
                    <h4 class="tiles-heading">My Tenders</h4>
                    <h6 class="tiles-count">8 Records</h6>
                    <div class="clearfix"></div>
                    </div>
                </a>
            </div>
    </div>
    <div id="DeviationBid" class="tab-pane fade">
    	<div class="col-md-2 col-xs-6">
                <a href="<%=context%>/deviationBid">
                <div class="tiles text-center">
                    <i class="fa fa-file-pdf-o pull-left font36"></i>
                    <h4 class="tiles-heading">Deviation Bid</h4>
                    <h6 class="tiles-count">1 Records</h6>
                    <div class="clearfix"></div>
                    </div>
                </a>
            </div>
    </div>
    <div id="Winner Selection" class="tab-pane fade">
    	<div  class="col-md-2 col-xs-6">
                <a href="<%=context%>/winnerSelection">
                	<div class="tiles text-center">
                    <i class="fa fa-trophy pull-left font36"></i>
                    <h4 class="tiles-heading">Winner Selection</h4>
                    <h6 class="tiles-count">1 Records</h6>
                    <div class="clearfix"></div>
                    </div>
                </a>
            </div>
    </div>
  </div>
    </div>
</div>
        
       
        </div>
       
        
        <!-- ProductDetailsTiles end-->
    </div>
	<script src="resources/commons/js/newDashboard.js"></script>
	<script	src="<%=request.getContextPath()%>/resources/commons/js/formFields.js"></script>
	<script	src="<%=request.getContextPath()%>/resources/commons/js/utility.js"></script> 
	 <script src="<%=request.getContextPath()%>/resources/tilescommon/js/script.js"></script>
</body>