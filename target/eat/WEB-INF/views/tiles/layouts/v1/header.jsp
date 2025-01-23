<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<sec:csrfMetaTags />
<div id="loading-wrapper">
<div id="loading-content">
	<div id="loader">
    <div id="loader_1" class="loader"></div>
    <div id="loader_2" class="loader"></div>
    <div id="loader_3" class="loader"></div>
    <div id="loader_4" class="loader"></div>
    <div id="loader_5" class="loader"></div>
    <div id="loader_6" class="loader"></div>
    <div id="loader_7" class="loader"></div>
    <div id="loader_8" class="loader"></div>
    </div>
    </div>
</div>
    <!-- <nav class="navbar navbar-inverse navbar-fixed-top">
        <div class="container-fluid nopadding">
    <div class="row topnewnav" ><ul id="SmallNav" class="nav navbar-nav navbar-right sidenav activenav">
                    <li><a href="disclaimer"><span class="glyphicon glyphicon-exclamation-sign"></span>Disclaimer</a></li>
                    <li><a href="calendar"><span class="glyphicon glyphicon-calendar"></span>Calendar</a></li>
                    <li><a href="notification"><span class="glyphicon glyphicon-bell"></span> Notification</a></li>                    
                    <li><a href="contactUs"><span class="glyphicon glyphicon-earphone"></span>Contact Us</a></li>
                    <li><a href="faq"><span class="glyphicon glyphicon-list-alt"></span>FAQ</a></li>
                    <li><a href="termsnCondition"><span class="glyphicon glyphicon-cog"></span>Terms &amp; Conditions</a></li>
                    <li><a href="advanceSearch"><span class="glyphicon glyphicon-search"></span>Advance Search</a></li> 
                    <li><a href="#"><span class="glyphicon glyphicon-question-sign"></span>Help</a></li>    
                                    
    </ul></div>
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="home"><img alt="" src="resources/commons/images/eauction1.png"></a>
                <a href="javascript:void(0)" class="LoginLink LoginLinkMobile"><span class="glyphicon glyphicon-lock"></span> Login</a>
            </div>
            <div class="collapse navbar-collapse" id="myNavbar">
             
                 
                
                 
                <ul class="nav navbar-nav  main-nav activenav">
                    <li class="home"><a href="<%=request.getContextPath()%>">Home</a></li>
                    <li class="dropdown">
         			 <a href="#" class="dropdown-toggle" data-toggle="dropdown">Tenders <b class="caret"></b></a>
        			  <ul class="dropdown-menu">           				 
           				 <li><a href="latestTendersWorks">Latest Tenders Work</a></li>
           				 <li><a href="latestTendersProcurement">Latest Tenders Procurement</a></li>
           				 <li><a href="latestAuctionForward">Forward Auction</a></li>
           				 <li><a href="latestAuctionReverse">Reverse Auction</a></li>
         			 </ul>
       				 </li>
       				 <li><a href="latestAnnouncements">Announcements</a></li>
                    <li class="dropdown">
         			 <a href="#" class="dropdown-toggle" data-toggle="dropdown">Vendors <b class="caret"></b></a>
        			  <ul class="dropdown-menu">
           				 <li><a href="blacklistedVendors">Blacklisted Vendors</a></li>
           				 <li><a href="registeredVendors">Registered Vendors</a></li>
						
         			 </ul>
       				 </li>
       				 <li class="dropdown">
         			 <a href="#" class="dropdown-toggle" data-toggle="dropdown">customer <b class="caret"></b></a>
        			  <ul class="dropdown-menu">
        			     <li><a href="blacklistedCustomer">Blacklisted customer</a></li>
           				 <li><a href="registeredCustomer"> Registered customer</a></li>
         			 </ul>
       				 </li>
                    <li><a href="download">Download</a></li>
                    <li><a href="publicNotices"> Public Notices</a></li>
		    <li class="responsiveli"><a href="disclaimer"><span class="glyphicon glyphicon-exclamation-sign"></span>Disclaimer</a></li>
                    <li class="responsiveli"><a href="calendar"><span class="glyphicon glyphicon-calendar"></span>Calendar</a></li>
                    <li class="responsiveli"><a href="notification"><span class="glyphicon glyphicon-bell"></span> Notification</a></li>                    
                    <li class="responsiveli"><a href="contactUs"><span class="glyphicon glyphicon-earphone"></span>Contact Us</a></li>
                    <li class="responsiveli"><a href="faq"><span class="glyphicon glyphicon-list-alt"></span>FAQ</a></li>
                    <li class="responsiveli"><a href="termsnCondition"><span class="glyphicon glyphicon-cog"></span>Terms &amp; Conditions</a></li>
                    <li class="responsiveli"><a href="advanceSearch"><span class="glyphicon glyphicon-search"></span>Advance Search</a></li> 
                </ul>
               
            </div>
        </div>
    </nav> -->
    <nav class="navbar navbar-inverse navbar-fixed-top"" id="top-menu" role="navigation">
        <div class="container-fluid">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-target="#navbarCollapse" data-toggle="collapse">
                	<span class="sr-only">Toggle navigation</span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
                </button>
      <a class="navbar-brand" href="" class="dash_logo" id="header_logoId">
               <c:choose>
			  <c:when test="${userLogo != null}">
			  <img src='data:image/png;base64,${userLogo}'></a>
			  </c:when>
			  <c:otherwise>
			  <img src="resources/home/img/eauction2.png">
			  </c:otherwise>
			</c:choose>
     </a>
            </div>
            <!-- Collapse navigation -->
            <div class="collapse navbar-collapse" id="navbarCollapse">
                <ul id="headerOptionId" class="nav navbar-nav navbar-right">
				  
				  <li class="active headerOption">
					<a href="<%=request.getContextPath()%>">Home</a>
				  </li>
				  <!--  <li class=" headerOption">
					<a href="notification">Notification</a>
				  </li>
				  <li class=" headerOption">
					<a href="calendar">Calendar</a>
				  </li> -->
				  <li class=" headerOption">
					<a href="disclaimer">Disclaimer</a>
				  </li>
				  <li class=" headerOption">
					<a href="contactUs">Contact Us</a>
				  </li>
				 <!--  <li class=" headerOption">
					<a href="faq">FAQ</a>
				  </li> -->
				   <li class="headerOption">
					<a href="credits">Credits</a>
				  </li>
				  <!-- <li>
					<a href="termsnCondition">Terms & Condition</a>
				  </li>
				  <li class="mobli">
					<a href="latestAuctionForward">Forward Auction</a>
				  </li>
				  <li class="mobli">
					<a href="latestAuctionReverse">Reverse Auctionn</a>
				  </li>
				  <li class="mobli">
					<a href="latestTendersWorks">Latest Tenders Service</a>
				  </li>
				  <li class="mobli">
					<a href="latestTendersProcurement">Latest Tenders Material</a>
				  </li>				  
				  <li class="mobli">
					<a href="latestAnnouncements">Announcements</a>
				  </li>
				  <li class="mobli">
					<a href="blacklistedVendors">Blacklisted Vendors</a>
				  </li>
				  <li class="mobli">
					<a href="registeredVendors">Registered Vendors</a>
				  </li>
				  <li class="mobli">
					<a href="blacklistedCustomer">Blacklisted customer</a>
				  </li>
				  <li class="mobli">
					<a href="registeredCustomer">Registered Customer</a>
				  </li> -->
				  <li class="mobli">
					<a href="download">Download</a>
				  </li>
				  
				</ul>
            </div>
        </div>
        <!-- End container-fluid -->
    </nav>