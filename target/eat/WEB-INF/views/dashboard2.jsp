<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@taglib uri = "http://www.springframework.org/tags/form" prefix = "sf"%>
<style>.backTolist{display:none !important;} .backTotils{display:none !important;}#sidebar-wrapper{top:97px;} .navbar-fixed-top{z-index:1;}</style>
	
	<!-- Context path Set -->
       <% String context=request.getContextPath(); %>
	<!-- Context path Set -->
	
 <body class="tiles_background">
 <!-- Header -->
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
        <div id="MainProductTiles" class="container top20">
        <c:forEach var="authorisedTiles" items="${authorisedTiles}" varStatus="count">
        <c:if test="${authorisedTiles.value== 'Dashboard'}">
            <a href="<%=context%>/dashboardView" class="col-md-2 col-xs-6">
                <div class="tiles text-center">
                    <i class="fa fa-fw fa-dashboard pull-left font36"></i>
                    <h4 class="tiles-heading">Dashboard</h4>
                <!--     <h6 class="tiles-count">1 Records Of Dashboard</h6> -->
                    <div class="clearfix"></div>
                </div>
                </c:if>
            <c:if test="${authorisedTiles.value== 'Master'}">
            </a><a href="<%=context%>/mastertiles" class="col-md-2 col-xs-6">
                <div class="tiles text-center">
                    <i class="fa fa-sticky-note pull-left font36" aria-hidden="true"></i>
                    <h4 class="tiles-heading">Master</h4>
<!--                     <h6 class="tiles-count">1 Records Of Master</h6> -->
                    <div class="clearfix"></div>
                </div>
            </a>
            </c:if>
            <c:if test="${authorisedTiles.value== 'Tenders'}">
            <a href="<%=context%>/tendertiles"  class="col-md-2 col-xs-6">
                <div class="tiles text-center">
                    <i class="fa fa-file-pdf-o pull-left font36"></i>
                    <h4 class="tiles-heading">Tenders</h4>
                <!--     <h6 class="tiles-count">7 Records Of Tenders</h6> -->
                    <div class="clearfix"></div>
                </div>
            </a></c:if>
            <c:if test="${authorisedTiles.value== 'Auction'}">
            <a href="<%=context%>/tendertiles"  class="col-md-2 col-xs-6">
                <div class="tiles text-center">
                    <i class="fa fa-gavel pull-left font36"></i>
                    <h4 class="tiles-heading">Auction</h4>
                <!--     <h6 class="tiles-count">7 Records Of Tenders</h6> -->
                    <div class="clearfix"></div>
                </div>
            </a></c:if>
            <c:if test="${authorisedTiles.value== 'Bids'}">
            <a href="bidsdetails" class="col-md-2 col-xs-6">
                <div class="tiles text-center">
                    <span class="glyphicon glyphicon-list-alt pull-left font36"></span>
                    <h4 class="tiles-heading">Bids</h4>
<!--                     <h6 class="tiles-count">10 Records Of Master</h6> -->
                    <div class="clearfix"></div>
                </div>
            </a></c:if>
            <c:if test="${authorisedTiles.value== 'Payment'}">
            <a href="#" class="col-md-2 col-xs-6">
                <div class="tiles text-center">
                    <i class="fa fa-money pull-left font36"></i>
                    <h4 class="tiles-heading">Payment</h4>
<!--                     <h6 class="tiles-count">1 Records Of Vendor</h6> -->
                    <div class="clearfix"></div>
                </div>
            </a>
            </c:if>
            <c:if test="${authorisedTiles.value== 'Reports'}">
             <a href="#" class="col-md-2 col-xs-6">
                <div class="tiles text-center">
                    <i class="fa fa-file-text pull-left font36"></i>
                    <h4 class="tiles-heading">Reports</h4>
<!--                     <h6 class="tiles-count">2 Records Of User</h6> -->
                    <div class="clearfix"></div>
                </div>
            </a>
           </c:if>
           
            <c:if test="${authorisedTiles.value== 'Purchase Proposal'}">
            <a href="#" class="col-md-2 col-xs-6">
                <div class="tiles text-center">
                    <i class="fa fa-shopping-cart pull-left font36"></i>
                    <h4 class="tiles-heading">Purchase Proposal</h4>
<!--                     <h6 class="tiles-count">2 Records Of User</h6> -->
                    <div class="clearfix"></div>
                </div>
            </a></c:if>
            <c:if test="${authorisedTiles.value== 'Mail'}">
            <a href="<%=context%>/mail" class="col-md-2 col-xs-6">
                <div class="tiles text-center">
                    <i class="fa fa-envelope pull-left font36"></i>
                    <h4 class="tiles-heading">Mail</h4>
<!--                     <h6 class="tiles-count">2 Records Of User</h6> -->
                    <div class="clearfix"></div>
                </div>
            </a></c:if>
            <c:if test="${authorisedTiles.value== 'Role Details'}">
             <a href="<%=context%>/roleDetails" class="col-md-2 col-xs-6">
                <div class="tiles text-center">
                    <i class="fa fa-info-circle pull-left font36"></i>
                    <h4 class="tiles-heading">Role Details</h4>
<!--                     <h6 class="tiles-count">2 Records Of User</h6> -->
                    <div class="clearfix"></div>
                </div></c:if>
           
                </c:forEach>
            </a> <a href="<%=context%>/Usertiles.html" class="col-md-2 col-xs-6">
                <div class="tiles text-center">
                    <i class="fa fa-cog pull-left font36"></i>
                    <h4 class="tiles-heading">Utilities</h4>
 <!--                    <h6 class="tiles-count">2 Records Of User</h6> -->
                    <div class="clearfix"></div>
                </div>
            </a>
            <a href="#" class="col-md-2 col-xs-6">
                <div class="tiles text-center">
                    <i class="fa fa-certificate pull-left font36"></i>
                    <h4 class="tiles-heading">Certificate</h4>
<!--                     <h6 class="tiles-count">2 Records Of User</h6> -->
                    <div class="clearfix"></div>
                </div>
            </a>
            <a href="<%=context%>/partnerRegistration" class="col-md-2 col-xs-6">
                <div class="tiles text-center">
                    <i class="fa fa-address-card-o pull-left font36"></i>
                    <h4 class="tiles-heading">Registration</h4>
<!--                     <h6 class="tiles-count">2 Records Of User</h6> -->
                    <div class="clearfix"></div>
                </div>
            </a>
                
        </div>
       
        
        <!-- ProductDetailsTiles end-->
    </div>
</body>
<script>
if ($(window).width() > 768) { 
    $(".dropdown").hover(            
            function() {
                $('.dropdown-menu', this).stop( true, true ).fadeIn("fast");
                $(this).toggleClass('open');
                $('b', this).toggleClass("caret caret-up");                
            },
            function() {
                $('.dropdown-menu', this).stop( true, true ).fadeOut("fast");
                $(this).toggleClass('open');
                $('b', this).toggleClass("caret caret-up");                
            });
    $('#sidebar-wrapper').addClass('active');
    }
</script>