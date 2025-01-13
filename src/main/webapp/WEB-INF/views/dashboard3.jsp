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
.cont{    width: 90%;
    margin: 110px auto;}
/*  bhoechie tab */
        
        div.bhoechie-tab-container {
            z-index: 10;
            background-color: transparent;
            padding: 0 !important;
            border-radius: 4px;
            -moz-border-radius: 4px;
            border: none;
            margin-top: 0px;
            margin-left: 10px;
            -webkit-box-shadow: 0 6px 12px rgba(0, 0, 0, .175);
            box-shadow: none;
            -moz-box-shadow: 0 6px 12px rgba(0, 0, 0, .175);
            background-clip: padding-box;
            opacity: 0.97;
            filter: alpha(opacity=97);
        }
        
        div.bhoechie-tab-menu {
            padding-right: 0;
            padding-left: 0;
            padding-bottom: 0;
        }
        
        div.bhoechie-tab-menu div.list-group {
            margin-bottom: 0;
                width: 75%;
    margin: 0px auto;
        }
        
        div.bhoechie-tab-menu div.list-group>a {
            margin-bottom: 0;
        }
        
        div.bhoechie-tab-menu div.list-group>a .glyphicon,
        div.bhoechie-tab-menu div.list-group>a .fa {
            color: #c9261d !important;
                font-size: 20px;
        }
        
        div.bhoechie-tab-menu div.list-group>a:first-child {
            border-top-right-radius: 0;
            -moz-border-top-right-radius: 0;
        }
        
        div.bhoechie-tab-menu div.list-group>a:last-child {
            border-bottom-right-radius: 0;
            -moz-border-bottom-right-radius: 0;
        }
        
        div.bhoechie-tab-menu div.list-group>a.active,
        div.bhoechie-tab-menu div.list-group>a.active .glyphicon,
        div.bhoechie-tab-menu div.list-group>a.active .fa {
            background-color: #c9261d;
            background-image: #c9261d;
            color: #ffffff !important
        }
        
        div.bhoechie-tab-menu div.list-group>a.active:after {
            content: '';
            position: absolute;
            left: 100%;
            top: 50%;
            margin-top: -13px;
            border-left: 0;
            border-bottom: 13px solid transparent;
            border-top: 13px solid transparent;
            border-left: 10px solid #c9261d;
        }
        .tiles_background {
    background-size: 90.1% 100% !important;
    background-repeat: no-repeat !important;
    width: 100% !important;
    height: 100% !important;
    background-attachment: fixed !important;
    background-position: center;
}
.header{    left: 67px;
    right: 67px;    width: auto;}
.navbar-fixed-top {
    top: 45px;
    right: 67px;
    left: 67px;
}
        .list-group-item {
    padding: 5px 10px;
}
        div.bhoechie-tab-content {
            background-color: transparent;
            /* border: 1px solid #eeeeee; */
            padding-left: 15px;
            padding-right: 15px;
            padding-top: 0px;
            padding-bottom: 45px;
            overflow: hidden;
        }
        .tiles {margin-top:0px;width: 100%;}
        .col-md-2 {
    width: 16.66666667%;
    padding: 5px;
}
        div.bhoechie-tab div.bhoechie-tab-content:not(.active) {
            display: none;
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
<nav class="navbar navbar-inverse navbar-fixed-top">
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
                    <li class="home"><a href="home">Home</a></li>
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
    </nav>
<div class="full-container animated slideInLeft">
        <!-- MainProductTiles start-->
        <div id="MainProductTiles" class="container-fluid top20 cont">
        <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 bhoechie-tab-container nopadding">
            <div class="col-lg-2 col-md-2 col-sm-2 col-xs-2 bhoechie-tab-menu">
              <div class="list-group">
              	<a href="#" class="list-group-item text-center">
                  <h4 class="fa fa-fw fa-dashboard"></h4><br/>Dashboard
                </a>
                <a href="#" class="list-group-item text-center">
                  <h4 class="fa fa-id-card-o"></h4><br/>Master
                </a>
                <a href="#" class="list-group-item text-center">
                  <h4 class="fa fa-file-pdf-o"></h4><br/>Tenders
                </a>
                <a href="#" class="list-group-item text-center">
                  <h4 class="fa fa-gavel"></h4><br/>Auction
                </a>
                <a href="#" class="list-group-item text-center">
                  <h4 class="glyphicon glyphicon-list-alt"></h4><br/>Bids
                </a>
                <a href="#" class="list-group-item text-center">
                  <h4 class="fa fa-money"></h4><br/>Payments
                </a>
                <a href="#" class="list-group-item text-center">
                  <h4 class="fa fa-envelope"></h4><br/>Mail
                </a>
                <a href="#" class="list-group-item text-center">
                  <h4 class="fa fa-info-circle"></h4><br/>Role Details
                </a>                
              </div>
            </div>
            <div class="col-lg-10 col-md-10 col-sm-10 col-xs-10 bhoechie-tab">
                <!-- flight section -->
                <div class="bhoechie-tab-content active">               
            <a href="/eatApp/dashboard3" class="col-md-2 col-xs-6">
                <div class="tiles text-center">
                    <i class="fa fa-fw fa-dashboard pull-left font36"></i>
                    <h4 class="tiles-heading">Dashboard</h4>
                <!--     <h6 class="tiles-count">1 Records Of Dashboard</h6> -->
                    <div class="clearfix"></div>
                </div>           
            </a>
                </div>
                <div class="bhoechie-tab-content">
               
                       <a href="getUserView" id="" class="col-md-2 col-xs-6">
                    <div class="tiles text-center">
                        <i class="fa fa-eye pull-left font36"></i><i class="fa fa-plus pull-left font36"></i><i class="fa fa-pencil pull-left font36"></i>
                        <h4 class="tiles-heading">Add User</h4>
                        <div class="clearfix"></div>
                    </div>
                </a>
                <a href="material" id="" class="col-md-2 col-xs-6">
                    <div class="tiles text-center">
                        <i class="fa fa-eye pull-left font36"></i>
                        <h4 class="tiles-heading">Material</h4>
                        <div class="clearfix"></div>
                    </div>
                </a>
                <a href="DocumentType" id="" class="col-md-2 col-xs-6">
                    <div class="tiles text-center">
                        <i class="fa fa-eye pull-left font36"></i>
                        <h4 class="tiles-heading">Document Type</h4>
                        <div class="clearfix"></div>
                    </div>
                </a>
                <a href="TenderType" id="" class="col-md-2 col-xs-6">
                    <div class="tiles text-center">
                        <i class="fa fa-eye pull-left font36"></i>
                        <h4 class="tiles-heading">Tender Type</h4>
                        <div class="clearfix"></div>
                    </div>
                </a>
                <a href="TenderBudgetType" id="" class="col-md-2 col-xs-6">
                    <div class="tiles text-center">
                        <i class="fa fa-eye pull-left font36"></i>
                        <h4 class="tiles-heading">Tender Budget Type</h4>
                        <div class="clearfix"></div>
                    </div>
                </a>
                <a href="BidType" id="" class="col-md-2 col-xs-6">
                    <div class="tiles text-center">
                        <i class="fa fa-eye pull-left font36"></i>
                        <h4 class="tiles-heading">Bid Type</h4>
                        <div class="clearfix"></div>
                    </div>
                </a>
                <a href="uom" id="" class="col-md-2 col-xs-6">
                    <div class="tiles text-center">
                        <i class="fa fa-eye pull-left font36"></i>
                        <h4 class="tiles-heading">UOM</h4>
                        <div class="clearfix"></div>
                    </div>
                </a>
                
                <a href="PaymentType" id="" class="col-md-2 col-xs-6">
                    <div class="tiles text-center">
                        <i class="fa fa-eye pull-left font36"></i>
                        <h4 class="tiles-heading">Payment Type</h4>
                        <div class="clearfix"></div>
                    </div>
                </a>
                <a href="ContractorType" id="" class="col-md-2 col-xs-6">
                    <div class="tiles text-center">
                        <i class="fa fa-eye pull-left font36"></i>
                        <h4 class="tiles-heading">Contractor Type</h4>
                        <div class="clearfix"></div>
                    </div>
                </a>
                <a href="materialGroup" id="" class="col-md-2 col-xs-6">
                    <div class="tiles text-center">
                        <i class="fa fa-eye pull-left font36"></i>
                        <h4 class="tiles-heading">Material Group</h4>
                        <div class="clearfix"></div>
                    </div>
                </a>
                
                <a href="MaterialSubGroup" id="" class="col-md-2 col-xs-6">
                    <div class="tiles text-center">
                        <i class="fa fa-eye pull-left font36"></i>
                        <h4 class="tiles-heading">Material Sub-Group</h4>
                        <div class="clearfix"></div>
                    </div>
                </a>
                <a href="MaterialSpecification" id="" class="col-md-2 col-xs-6">
                    <div class="tiles text-center">
                        <i class="fa fa-eye pull-left font36"></i>
                        <h4 class="tiles-heading">Material Specification</h4>
                        <div class="clearfix"></div>
                    </div>
                </a>
                <a href="departmentView" id="" class="col-md-2 col-xs-6">
                    <div class="tiles text-center">
                        <i class="fa fa-eye pull-left font36"></i>
                        <h4 class="tiles-heading">Department</h4>
                        <div class="clearfix"></div>
                    </div>
                </a>
                <a href="MaterialVersion" id="" class="col-md-2 col-xs-6">
                    <div class="tiles text-center">
                        <i class="fa fa-eye pull-left font36"></i>
                        <h4 class="tiles-heading">Material Version</h4>
                        <div class="clearfix"></div>
                    </div>
                </a>
                <a href="GtpParameter" id="" class="col-md-2 col-xs-6">
                    <div class="tiles text-center">
                        <i class="fa fa-eye pull-left font36"></i><i class="fa fa-plus pull-left font36"></i><i class="fa fa-pencil pull-left font36"></i>
                        <h4 class="tiles-heading">GTP Parameters</h4>
                        <div class="clearfix"></div>
                    </div>
                </a>
                <a href="GtpParameterType" id="" class="col-md-2 col-xs-6">
                    <div class="tiles text-center">
                        <i class="fa fa-eye pull-left font36"></i><i class="fa fa-plus pull-left font36"></i><i class="fa fa-pencil pull-left font36"></i>
                        <h4 class="tiles-heading">GTP Parameters Type</h4>
                        <div class="clearfix"></div>
                    </div>
                </a>
                <a href="PublicNotice" id="" class="col-md-2 col-xs-6">
                    <div class="tiles text-center">
                        <i class="fa fa-eye pull-left font36"></i><i class="fa fa-plus pull-left font36"></i><i class="fa fa-pencil pull-left font36"></i>
                        <h4 class="tiles-heading">Public Notices</h4>
                        <div class="clearfix"></div>
                    </div>
                </a>
                <a href="IsStd" id="" class="col-md-2 col-xs-6">
                    <div class="tiles text-center">
                        <i class="fa fa-eye pull-left font36"></i>
                        <h4 class="tiles-heading">IS-STD</h4>
                        <div class="clearfix"></div>
                    </div>
                </a>
                <a href="designation" id="" class="col-md-2 col-xs-6">
                    <div class="tiles text-center">
                        <i class="fa fa-eye pull-left font36"></i>
                        <h4 class="tiles-heading">Designation</h4>
                        <div class="clearfix"></div>
                    </div>
                </a>
                <a href="CompanyType" id="" class="col-md-2 col-xs-6">
                    <div class="tiles text-center">
                        <i class="fa fa-eye pull-left font36"></i>
                        <h4 class="tiles-heading">Company Type</h4>
                        <div class="clearfix"></div>
                    </div>
                </a>
                <a href="RegistrationType" id="" class="col-md-2 col-xs-6">
                    <div class="tiles text-center">
                        <i class="fa fa-eye pull-left font36"></i>
                        <h4 class="tiles-heading">Registration Type</h4>
                        <div class="clearfix"></div>
                    </div>
                </a>
                <a href="tax" id="" class="col-md-2 col-xs-6">
                    <div class="tiles text-center">
                        <i class="fa fa-eye pull-left font36"></i>
                        <h4 class="tiles-heading">Tax</h4>
                        <div class="clearfix"></div>
                    </div>
                </a>
                <a href="TaxCategory" id="" class="col-md-2 col-xs-6">
                    <div class="tiles text-center">
                        <i class="fa fa-eye pull-left font36"></i>
                        <h4 class="tiles-heading">Tax Category</h4>
                        <div class="clearfix"></div>
                    </div>
                </a>
                <a href="HolderType" id="" class="col-md-2 col-xs-6">
                    <div class="tiles text-center">
                        <i class="fa fa-eye pull-left font36"></i>
                        <h4 class="tiles-heading">Holder Type</h4>
                        <div class="clearfix"></div>
                    </div>
                </a>
                 <a href="role" id="" class="col-md-2 col-xs-6">
                    <div class="tiles text-center">
                        <i class="fa fa-eye pull-left font36"></i>
                        <h4 class="tiles-heading">Role</h4>
                        <div class="clearfix"></div>
                    </div>
                </a>
                <a href="MaterialType" id="" class="col-md-2 col-xs-6">
                    <div class="tiles text-center">
                        <i class="fa fa-eye pull-left font36"></i>
                        <h4 class="tiles-heading">Material Type</h4>
                        <div class="clearfix"></div>
                    </div>
                </a>
                <a href="VendorType" id="" class="col-md-2 col-xs-6">
                    <div class="tiles text-center">
                        <i class="fa fa-eye pull-left font36"></i>
                        <h4 class="tiles-heading">Vendor Type</h4>
                        <div class="clearfix"></div>
                    </div>
                </a>
                <a href="ExemptedCategories" id="" class="col-md-2 col-xs-6">
                    <div class="tiles text-center">
                        <i class="fa fa-eye pull-left font36"></i><i class="fa fa-plus pull-left font36"></i><i class="fa fa-pencil pull-left font36"></i>
                        <h4 class="tiles-heading">Exempted Categories</h4>
                        <div class="clearfix"></div>
                    </div>
                </a>                
                 <a href="FinancialDocuments" id="" class="col-md-2 col-xs-6">
                    <div class="tiles text-center">
                        <i class="fa fa-eye pull-left font36"></i>
                        <h4 class="tiles-heading">Financial Documents</h4>
                        <div class="clearfix"></div>
                    </div>
                </a>                
                 <a href="BusinessPartnerGroup" id="" class="col-md-2 col-xs-6">
                    <div class="tiles text-center">
                        <i class="fa fa-eye pull-left font36"></i>
                        <h4 class="tiles-heading">Business Partner Group</h4>
                        <div class="clearfix"></div>
                    </div>
                </a>    
                </div>
                <!-- train section -->
                <div class="bhoechie-tab-content">
                   <a href="<%=context%>/tenderPreparation" id="" class="col-md-2">
                        <div class="tiles text-center">
                            <i class="fa fa-plus pull-left font36"></i>
                            <h4 class="tiles-heading">Tender Preparation </h4>
                            <!-- <h6 class="tiles-count">1 Records</h6> -->
                            <div class="clearfix"></div>
                        </div>
                    </a>
                    <a href="<%=context%>/tenderPublishing" id="" class="col-md-2">
                        <div class="tiles text-center">
                            <i class="fa fa-plus pull-left font36"></i>
                            <h4 class="tiles-heading">Tender Publishing</h4>
                            <!-- <h6 class="tiles-count">0 Records</h6> -->
                            <div class="clearfix"></div>
                        </div>
                    </a>
                    <a href="<%=context%>/tenderScheduling" id="" class="col-md-2">
                        <div class="tiles text-center">
                            <i class="fa fa-plus pull-left font36"></i>
                            <h4 class="tiles-heading">Tender Scheduling</h4>
                            <!-- <h6 class="tiles-count">0 Records</h6> -->
                            <div class="clearfix"></div>
                        </div>
                    </a>
                    <a href="<%=context%>/tenderPurchase" id="" class="col-md-2">
                        <div class="tiles text-center">
                            <i class="fa fa-plus pull-left font36"></i>
                            <h4 class="tiles-heading">Tender Purchase</h4>
                            <!-- <h6 class="tiles-count">0 Records</h6> -->
                            <div class="clearfix"></div>
                        </div>
                    </a>
                    <a href="<%=context%>/tenderSubmission" id="" class="col-md-2">
                        <div class="tiles text-center">
                            <i class="fa fa-plus pull-left font36"></i>
                            <h4 class="tiles-heading">Tender Submission</h4>
                            <!-- <h6 class="tiles-count">0 Records</h6> -->
                            <div class="clearfix"></div>
                        </div>
                    </a>
                    <a href="<%=context%>/tenderOpening" id="" class="col-md-2">
                        <div class="tiles text-center">
                            <i class="fa fa-plus pull-left font36"></i>
                            <h4 class="tiles-heading">Tender Opening</h4>
                            <!-- <h6 class="tiles-count">0 Records</h6> -->
                            <div class="clearfix"></div>
                        </div>
                    </a>
                    <a href="<%=context%>/tenderCommercialScrutiny" id="" class="col-md-2">
                        <div class="tiles text-center">
                            <i class="fa fa-plus pull-left font36"></i>
                            <h4 class="tiles-heading">Tender Commercial Scrutiny</h4>
                            <!-- <h6 class="tiles-count">0 Records</h6> -->
                            <div class="clearfix"></div>
                        </div>
                    </a>
                    <a href="tenderCommercialScrutiny" id="" class="col-md-2">
                        <div class="tiles text-center">
                            <i class="fa fa-plus pull-left font36"></i>
                            <h4 class="tiles-heading">Tender Details</h4>
                            <!-- <h6 class="tiles-count">0 Records</h6> -->
                            <div class="clearfix"></div>
                        </div>
                    </a>
                    <a href="<%=context%>/tenderTechnicalScrutiny" id="" class="col-md-2">
                        <div class="tiles text-center">
                            <i class="fa fa-plus pull-left font36"></i>
                            <h4 class="tiles-heading">Tender Technical Scrutiny</h4>
                            <!-- <h6 class="tiles-count">0 Records</h6> -->
                            <div class="clearfix"></div>
                        </div>
                    </a>         
                </div>
    
                <!-- hotel search -->
                <div class="bhoechie-tab-content">
                    <a href="<%=context%>/auctionPreparation" id="" class="col-md-2">
                        <div class="tiles text-center">
                            <i class="fa fa-plus pull-left font36"></i>
                            <h4 class="tiles-heading">Auction Preparation </h4>
                            <!-- <h6 class="tiles-count">1 Records</h6> -->
                            <div class="clearfix"></div>
                        </div>
                    </a>
                    
                    <a href="<%=context%>/auctionPublishing" id="" class="col-md-2">
                        <div class="tiles text-center">
                            <i class="fa fa-plus pull-left font36"></i>
                            <h4 class="tiles-heading">Auction Publishing</h4>
                            <!-- <h6 class="tiles-count">0 Records</h6> -->
                            <div class="clearfix"></div>
                        </div>
                    </a>
                    <a href="<%=context%>/auctionScheduling" id="" class="col-md-2">
                        <div class="tiles text-center">
                            <i class="fa fa-plus pull-left font36"></i>
                            <h4 class="tiles-heading">Auction Scheduling</h4>
                            <!-- <h6 class="tiles-count">0 Records</h6> -->
                            <div class="clearfix"></div>
                        </div>
                    </a>
                    <a href="<%=context%>/auctionPurchase" id="" class="col-md-2">
                        <div class="tiles text-center">
                            <i class="fa fa-plus pull-left font36"></i>
                            <h4 class="tiles-heading">Auction Purchase</h4>
                            <!-- <h6 class="tiles-count">0 Records</h6> -->
                            <div class="clearfix"></div>
                        </div>
                    </a>
                    <a href="<%=context%>/auctionSubmission" id="" class="col-md-2">
                        <div class="tiles text-center">
                            <i class="fa fa-plus pull-left font36"></i>
                            <h4 class="tiles-heading">Auction Submission</h4>
                            <!-- <h6 class="tiles-count">0 Records</h6> -->
                            <div class="clearfix"></div>
                        </div>
                    </a>
                    <a href="<%=context%>/auctionOpening" id="" class="col-md-2">
                        <div class="tiles text-center">
                            <i class="fa fa-plus pull-left font36"></i>
                            <h4 class="tiles-heading">Auction Opening</h4>
                            <!-- <h6 class="tiles-count">0 Records</h6> -->
                            <div class="clearfix"></div>
                        </div>
                    </a>
                    <a href="<%=context%>/auctionCommercialScrutiny" id="" class="col-md-2">
                        <div class="tiles text-center">
                            <i class="fa fa-plus pull-left font36"></i>
                            <h4 class="tiles-heading">Auction Commercial Scrutiny</h4>
                            <!-- <h6 class="tiles-count">0 Records</h6> -->
                            <div class="clearfix"></div>
                        </div>
                    </a>
                    <a href="auctionCommercialScrutiny" id="" class="col-md-2">
                        <div class="tiles text-center">
                            <i class="fa fa-plus pull-left font36"></i>
                            <h4 class="tiles-heading">Auction Details</h4>
                            <!-- <h6 class="tiles-count">0 Records</h6> -->
                            <div class="clearfix"></div>
                        </div>
                    </a>
                    <a href="<%=context%>/auctionTechnicalScrutiny" id="" class="col-md-2">
                        <div class="tiles text-center">
                            <i class="fa fa-plus pull-left font36"></i>
                            <h4 class="tiles-heading">Auction Technical Scrutiny</h4>
                            <!-- <h6 class="tiles-count">0 Records</h6> -->
                            <div class="clearfix"></div>
                        </div>
                    </a>       
                </div>
                <div class="bhoechie-tab-content">
                  <a href="bidsdetails" class="col-md-2 col-xs-6">
                <div class="tiles text-center">
                    <span class="glyphicon glyphicon-list-alt pull-left font36"></span>
                    <h4 class="tiles-heading">Bids</h4>
<!--                     <h6 class="tiles-count">10 Records Of Master</h6> -->
                    <div class="clearfix"></div>
                </div>
            </a>
                </div>
                <div class="bhoechie-tab-content">
                  <a href="payments" class="col-md-2 col-xs-6">
                <div class="tiles text-center">
                    <i class="fa fa-money pull-left font36"></i>
                    <h4 class="tiles-heading">Payment</h4>
<!--                     <h6 class="tiles-count">1 Records Of Vendor</h6> -->
                    <div class="clearfix"></div>
                </div>
            </a>
                </div>
                <div class="bhoechie-tab-content">
                  <a href="/eatApp/mail" class="col-md-2 col-xs-6">
                <div class="tiles text-center">
                    <i class="fa fa-envelope pull-left font36"></i>
                    <h4 class="tiles-heading">Mail</h4>
<!--                     <h6 class="tiles-count">2 Records Of User</h6> -->
                    <div class="clearfix"></div>
                </div>
            </a>
                </div>
                <div class="bhoechie-tab-content">
                  <a href="/eatApp/roleDetails" class="col-md-2 col-xs-6">
                <div class="tiles text-center">
                    <i class="fa fa-info-circle pull-left font36"></i>
                    <h4 class="tiles-heading">Role Details</h4>
<!--                     <h6 class="tiles-count">2 Records Of User</h6> -->
                    <div class="clearfix"></div>
                </div>
                 </a>
                </div>
            </div>
        </div>
        </div>
       
        
        <!-- ProductDetailsTiles end-->
    </div>
    <script>$("div.bhoechie-tab-menu>div.list-group>a").click(function(e) {
        e.preventDefault();
        $(this).siblings('a.active').removeClass("active");
        $(this).addClass("active");
        var index = $(this).index();
        $("div.bhoechie-tab>div.bhoechie-tab-content").removeClass("active");
        $("div.bhoechie-tab>div.bhoechie-tab-content").eq(index).addClass("active");           
    });</script>
</body>