<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@taglib uri = "http://www.springframework.org/tags/form" prefix = "sf"%>
<%@taglib uri = "http://www.springframework.org/tags/form" prefix = "sf"%>
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/${appMode}/tilescommon/css/dashboardView.css?appVer=${appVer}"/>
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
  <div class="container-fluid dashcontent"  style="margin-bottom:40px;">
      <!-- Breadcrumbs-->
      <ol class="breadcrumb">
        <li class="breadcrumb-item">
          <a href="#">Dashboard</a>
        </li>
        <li class="breadcrumb-item active">My Dashboard</li>
      </ol>
      <!-- Icon Cards-->
      <div class="row">
        <div class="col-sm-3">
          <div class="card2 tendworkdet text-white bg-primary o-hidden h-100">
            <div class="card-body">
              <div class="card-body-icon">
                <i class="fa fa-file-pdf-o"></i>
              </div>
              <div class="mr-5">Tenders Work</div>
            </div>
            <a class="card-footer text-white clearfix small z-1 tendworkdet" href="javascript:void(0)">
              <span class="float-left">View Details</span>
              <span class="float-right">
                <i class="fa fa-angle-right"></i>
              </span>
            </a>
          </div>
        </div>
        <div class="col-sm-3">
          <div class="card2 tendprodet text-white bg-warning o-hidden h-100">
            <div class="card-body">
              <div class="card-body-icon">
                <i class="fa fa-gavel"></i>
              </div>
              <div class="mr-5">Tenders Procurements</div>
            </div>
            <a class="card-footer text-white clearfix small z-1 tendprodet" href="javascript:void(0)">
              <span class="float-left">View Details</span>
              <span class="float-right">
                <i class="fa fa-angle-right"></i>
              </span>
            </a>
          </div>
        </div>
        <div class="col-sm-3">
          <div class="card2 foauctdet text-white bg-success o-hidden h-100">
            <div class="card-body">
              <div class="card-body-icon">
                <i class="glyphicon glyphicon-list-alt"></i>
              </div>
              <div class="mr-5">Forward Auction</div>
            </div>
            <a class="card-footer text-white clearfix small z-1 foauctdet" href="javascript:void(0)">
              <span class="float-left">View Details</span>
              <span class="float-right">
                <i class="fa fa-angle-right"></i>
              </span>
            </a>
          </div>
        </div>
        <div class="col-sm-3">
          <div class="card2 text-white reauctdet bg-danger o-hidden h-100">
            <div class="card-body">
              <div class="card-body-icon">
                <i class="fa fa-file-text"></i>
              </div>
              <div class="mr-5">Reverse Auction</div>
            </div>
            <a class="card-footer text-white clearfix small z-1 reauctdet" href="javascript:void(0)">
              <span class="float-left">View Details</span>
              <span class="float-right">
                <i class="fa fa-angle-right"></i>
              </span>
            </a>
          </div>
        </div>
      </div>
      <div class="clearfix"></div>
      
      <div class="workstender_details_containt">
      	<h4>Latest Tenders Work</h4>
           <table class="TenderListingTable tableResponsive table table-striped table-bordered" width="100%">
                                            <thead>
                                                <tr>
                                                    <th>Tender No</th>
                                                    <th>Description</th>
                                                    <th>Sale Opening</th>
                                                    <th>Sale Closing</th>
                                                    <th>Submission Due</th>
                                                    <th>Tender Fee(Rs)</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <tr>
                                                    <td><a class="tenderdetailsTab" data-toggle="tab" href="#TenderDetails">TestTender1103</a></td>
                                                    <td>Browser Requirements </td>
                                                    <td>21st October 2017</td>
                                                    <td>21st October 2017</td>
                                                    <td>21st October 2017</td>
                                                    <td>20,00,000</td>
                                                </tr>
                                                <tr>
                                                    <td><a class="tenderdetailsTab" data-toggle="tab" href="#TenderDetails">TestTender1103</a></td>
                                                    <td>Browser Requirements </td>
                                                    <td>21st October 2017</td>
                                                    <td>21st October 2017</td>
                                                    <td>21st October 2017</td>
                                                    <td>20,00,000</td>
                                                </tr>
                                                <tr>
                                                    <td><a class="tenderdetailsTab" data-toggle="tab" href="#TenderDetails">TestTender1103</a></td>
                                                    <td>Browser Requirements </td>
                                                    <td>21st October 2017</td>
                                                    <td>21st October 2017</td>
                                                    <td>21st October 2017</td>
                                                    <td>20,00,000</td>
                                                </tr>
                                                <tr>
                                                    <td><a class="tenderdetailsTab" data-toggle="tab" href="#TenderDetails">TestTender1103</a></td>
                                                    <td>Browser Requirements </td>
                                                    <td>21st October 2017</td>
                                                    <td>21st October 2017</td>
                                                    <td>21st October 2017</td>
                                                    <td>20,00,000</td>
                                                </tr>
                                                <tr>
                                                    <td><a class="tenderdetailsTab" data-toggle="tab" href="#TenderDetails">TestTender1103</a></td>
                                                    <td>Browser Requirements </td>
                                                    <td>21st October 2017</td>
                                                    <td>21st October 2017</td>
                                                    <td>21st October 2017</td>
                                                    <td>20,00,000</td>
                                                </tr>
                                                <tr>
                                                    <td><a class="tenderdetailsTab" data-toggle="tab" href="#TenderDetails">TestTender1103</a></td>
                                                    <td>Browser Requirements </td>
                                                    <td>21st October 2017</td>
                                                    <td>21st October 2017</td>
                                                    <td>21st October 2017</td>
                                                    <td>20,00,000</td>
                                                </tr>
                                                <tr>
                                                    <td><a class="tenderdetailsTab" data-toggle="tab" href="#TenderDetails">TestTender1103</a></td>
                                                    <td>Browser Requirements </td>
                                                    <td>21st October 2017</td>
                                                    <td>21st October 2017</td>
                                                    <td>21st October 2017</td>
                                                    <td>20,00,000</td>
                                                </tr>
                                                <tr>
                                                    <td><a class="tenderdetailsTab" data-toggle="tab" href="#TenderDetails">TestTender1103</a></td>
                                                    <td>Browser Requirements </td>
                                                    <td>21st October 2017</td>
                                                    <td>21st October 2017</td>
                                                    <td>21st October 2017</td>
                                                    <td>20,00,000</td>
                                                </tr>
                                                <tr>
                                                    <td><a class="tenderdetailsTab" data-toggle="tab" href="#TenderDetails">TestTender1103</a></td>
                                                    <td>Browser Requirements </td>
                                                    <td>21st October 2017</td>
                                                    <td>21st October 2017</td>
                                                    <td>21st October 2017</td>
                                                    <td>20,00,000</td>
                                                </tr>
                                                <tr>
                                                    <td><a class="tenderdetailsTab" data-toggle="tab" href="#TenderDetails">TestTender1103</a></td>
                                                    <td>Browser Requirements </td>
                                                    <td>21st October 2017</td>
                                                    <td>21st October 2017</td>
                                                    <td>21st October 2017</td>
                                                    <td>20,00,000</td>
                                                </tr>
                                            </tbody>
                                        </table>      
      </div>
      <div class="Procurementstender_details_containt">
      	<h4>Tenders Procurements</h4>
           <table class="TenderListingTable tableResponsive table table-striped table-bordered" width="100%">
                                            <thead>
                                                <tr>
                                                    <th>Tender No</th>
                                                    <th>Description</th>
                                                    <th>Sale Opening</th>
                                                    <th>Sale Closing</th>
                                                    <th>Submission Due</th>
                                                    <th>Tender Fee(Rs)</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <tr>
                                                    <td><a class="tenderdetailsTab" data-toggle="tab" href="#TenderDetails">TestTender1103</a></td>
                                                    <td>Browser Requirements </td>
                                                    <td>21st October 2017</td>
                                                    <td>21st October 2017</td>
                                                    <td>21st October 2017</td>
                                                    <td>20,00,000</td>
                                                </tr>
                                                <tr>
                                                    <td><a class="tenderdetailsTab" data-toggle="tab" href="#TenderDetails">TestTender1103</a></td>
                                                    <td>Browser Requirements </td>
                                                    <td>21st October 2017</td>
                                                    <td>21st October 2017</td>
                                                    <td>21st October 2017</td>
                                                    <td>20,00,000</td>
                                                </tr>
                                                <tr>
                                                    <td><a class="tenderdetailsTab" data-toggle="tab" href="#TenderDetails">TestTender1103</a></td>
                                                    <td>Browser Requirements </td>
                                                    <td>21st October 2017</td>
                                                    <td>21st October 2017</td>
                                                    <td>21st October 2017</td>
                                                    <td>20,00,000</td>
                                                </tr>
                                                <tr>
                                                    <td><a class="tenderdetailsTab" data-toggle="tab" href="#TenderDetails">TestTender1103</a></td>
                                                    <td>Browser Requirements </td>
                                                    <td>21st October 2017</td>
                                                    <td>21st October 2017</td>
                                                    <td>21st October 2017</td>
                                                    <td>20,00,000</td>
                                                </tr>
                                                <tr>
                                                    <td><a class="tenderdetailsTab" data-toggle="tab" href="#TenderDetails">TestTender1103</a></td>
                                                    <td>Browser Requirements </td>
                                                    <td>21st October 2017</td>
                                                    <td>21st October 2017</td>
                                                    <td>21st October 2017</td>
                                                    <td>20,00,000</td>
                                                </tr>
                                                <tr>
                                                    <td><a class="tenderdetailsTab" data-toggle="tab" href="#TenderDetails">TestTender1103</a></td>
                                                    <td>Browser Requirements </td>
                                                    <td>21st October 2017</td>
                                                    <td>21st October 2017</td>
                                                    <td>21st October 2017</td>
                                                    <td>20,00,000</td>
                                                </tr>
                                                <tr>
                                                    <td><a class="tenderdetailsTab" data-toggle="tab" href="#TenderDetails">TestTender1103</a></td>
                                                    <td>Browser Requirements </td>
                                                    <td>21st October 2017</td>
                                                    <td>21st October 2017</td>
                                                    <td>21st October 2017</td>
                                                    <td>20,00,000</td>
                                                </tr>
                                                <tr>
                                                    <td><a class="tenderdetailsTab" data-toggle="tab" href="#TenderDetails">TestTender1103</a></td>
                                                    <td>Browser Requirements </td>
                                                    <td>21st October 2017</td>
                                                    <td>21st October 2017</td>
                                                    <td>21st October 2017</td>
                                                    <td>20,00,000</td>
                                                </tr>
                                                <tr>
                                                    <td><a class="tenderdetailsTab" data-toggle="tab" href="#TenderDetails">TestTender1103</a></td>
                                                    <td>Browser Requirements </td>
                                                    <td>21st October 2017</td>
                                                    <td>21st October 2017</td>
                                                    <td>21st October 2017</td>
                                                    <td>20,00,000</td>
                                                </tr>
                                                <tr>
                                                    <td><a class="tenderdetailsTab" data-toggle="tab" href="#TenderDetails">TestTender1103</a></td>
                                                    <td>Browser Requirements </td>
                                                    <td>21st October 2017</td>
                                                    <td>21st October 2017</td>
                                                    <td>21st October 2017</td>
                                                    <td>20,00,000</td>
                                                </tr>
                                            </tbody>
                                        </table>      
      </div>
      <div class="ForwardAuction_details_containt">
      	<h4>Forward Auction</h4>
           <table class="TenderListingTable tableResponsive table table-striped table-bordered" width="100%">
                                            <thead>
                                                <tr>
                                                    <th>Tender No</th>
                                                    <th>Description</th>
                                                    <th>Sale Opening</th>
                                                    <th>Sale Closing</th>
                                                    <th>Submission Due</th>
                                                    <th>Tender Fee(Rs)</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <tr>
                                                    <td><a class="tenderdetailsTab" data-toggle="tab" href="#TenderDetails">TestTender1103</a></td>
                                                    <td>Browser Requirements </td>
                                                    <td>21st October 2017</td>
                                                    <td>21st October 2017</td>
                                                    <td>21st October 2017</td>
                                                    <td>20,00,000</td>
                                                </tr>
                                                <tr>
                                                    <td><a class="tenderdetailsTab" data-toggle="tab" href="#TenderDetails">TestTender1103</a></td>
                                                    <td>Browser Requirements </td>
                                                    <td>21st October 2017</td>
                                                    <td>21st October 2017</td>
                                                    <td>21st October 2017</td>
                                                    <td>20,00,000</td>
                                                </tr>
                                                <tr>
                                                    <td><a class="tenderdetailsTab" data-toggle="tab" href="#TenderDetails">TestTender1103</a></td>
                                                    <td>Browser Requirements </td>
                                                    <td>21st October 2017</td>
                                                    <td>21st October 2017</td>
                                                    <td>21st October 2017</td>
                                                    <td>20,00,000</td>
                                                </tr>
                                                <tr>
                                                    <td><a class="tenderdetailsTab" data-toggle="tab" href="#TenderDetails">TestTender1103</a></td>
                                                    <td>Browser Requirements </td>
                                                    <td>21st October 2017</td>
                                                    <td>21st October 2017</td>
                                                    <td>21st October 2017</td>
                                                    <td>20,00,000</td>
                                                </tr>
                                                <tr>
                                                    <td><a class="tenderdetailsTab" data-toggle="tab" href="#TenderDetails">TestTender1103</a></td>
                                                    <td>Browser Requirements </td>
                                                    <td>21st October 2017</td>
                                                    <td>21st October 2017</td>
                                                    <td>21st October 2017</td>
                                                    <td>20,00,000</td>
                                                </tr>
                                                <tr>
                                                    <td><a class="tenderdetailsTab" data-toggle="tab" href="#TenderDetails">TestTender1103</a></td>
                                                    <td>Browser Requirements </td>
                                                    <td>21st October 2017</td>
                                                    <td>21st October 2017</td>
                                                    <td>21st October 2017</td>
                                                    <td>20,00,000</td>
                                                </tr>
                                                <tr>
                                                    <td><a class="tenderdetailsTab" data-toggle="tab" href="#TenderDetails">TestTender1103</a></td>
                                                    <td>Browser Requirements </td>
                                                    <td>21st October 2017</td>
                                                    <td>21st October 2017</td>
                                                    <td>21st October 2017</td>
                                                    <td>20,00,000</td>
                                                </tr>
                                                <tr>
                                                    <td><a class="tenderdetailsTab" data-toggle="tab" href="#TenderDetails">TestTender1103</a></td>
                                                    <td>Browser Requirements </td>
                                                    <td>21st October 2017</td>
                                                    <td>21st October 2017</td>
                                                    <td>21st October 2017</td>
                                                    <td>20,00,000</td>
                                                </tr>
                                                <tr>
                                                    <td><a class="tenderdetailsTab" data-toggle="tab" href="#TenderDetails">TestTender1103</a></td>
                                                    <td>Browser Requirements </td>
                                                    <td>21st October 2017</td>
                                                    <td>21st October 2017</td>
                                                    <td>21st October 2017</td>
                                                    <td>20,00,000</td>
                                                </tr>
                                                <tr>
                                                    <td><a class="tenderdetailsTab" data-toggle="tab" href="#TenderDetails">TestTender1103</a></td>
                                                    <td>Browser Requirements </td>
                                                    <td>21st October 2017</td>
                                                    <td>21st October 2017</td>
                                                    <td>21st October 2017</td>
                                                    <td>20,00,000</td>
                                                </tr>
                                            </tbody>
                                        </table>      
      </div>
      <div class="ReverseAuction_details_containt">
      	<h4>Reverse Auction</h4>
           <table class="TenderListingTable tableResponsive table table-striped table-bordered" width="100%">
                                            <thead>
                                                <tr>
                                                    <th>Tender No</th>
                                                    <th>Description</th>
                                                    <th>Sale Opening</th>
                                                    <th>Sale Closing</th>
                                                    <th>Submission Due</th>
                                                    <th>Tender Fee(Rs)</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <tr>
                                                    <td><a class="tenderdetailsTab" data-toggle="tab" href="#TenderDetails">TestTender1103</a></td>
                                                    <td>Browser Requirements </td>
                                                    <td>21st October 2017</td>
                                                    <td>21st October 2017</td>
                                                    <td>21st October 2017</td>
                                                    <td>20,00,000</td>
                                                </tr>
                                                <tr>
                                                    <td><a class="tenderdetailsTab" data-toggle="tab" href="#TenderDetails">TestTender1103</a></td>
                                                    <td>Browser Requirements </td>
                                                    <td>21st October 2017</td>
                                                    <td>21st October 2017</td>
                                                    <td>21st October 2017</td>
                                                    <td>20,00,000</td>
                                                </tr>
                                                <tr>
                                                    <td><a class="tenderdetailsTab" data-toggle="tab" href="#TenderDetails">TestTender1103</a></td>
                                                    <td>Browser Requirements </td>
                                                    <td>21st October 2017</td>
                                                    <td>21st October 2017</td>
                                                    <td>21st October 2017</td>
                                                    <td>20,00,000</td>
                                                </tr>
                                                <tr>
                                                    <td><a class="tenderdetailsTab" data-toggle="tab" href="#TenderDetails">TestTender1103</a></td>
                                                    <td>Browser Requirements </td>
                                                    <td>21st October 2017</td>
                                                    <td>21st October 2017</td>
                                                    <td>21st October 2017</td>
                                                    <td>20,00,000</td>
                                                </tr>
                                                <tr>
                                                    <td><a class="tenderdetailsTab" data-toggle="tab" href="#TenderDetails">TestTender1103</a></td>
                                                    <td>Browser Requirements </td>
                                                    <td>21st October 2017</td>
                                                    <td>21st October 2017</td>
                                                    <td>21st October 2017</td>
                                                    <td>20,00,000</td>
                                                </tr>
                                                <tr>
                                                    <td><a class="tenderdetailsTab" data-toggle="tab" href="#TenderDetails">TestTender1103</a></td>
                                                    <td>Browser Requirements </td>
                                                    <td>21st October 2017</td>
                                                    <td>21st October 2017</td>
                                                    <td>21st October 2017</td>
                                                    <td>20,00,000</td>
                                                </tr>
                                                <tr>
                                                    <td><a class="tenderdetailsTab" data-toggle="tab" href="#TenderDetails">TestTender1103</a></td>
                                                    <td>Browser Requirements </td>
                                                    <td>21st October 2017</td>
                                                    <td>21st October 2017</td>
                                                    <td>21st October 2017</td>
                                                    <td>20,00,000</td>
                                                </tr>
                                                <tr>
                                                    <td><a class="tenderdetailsTab" data-toggle="tab" href="#TenderDetails">TestTender1103</a></td>
                                                    <td>Browser Requirements </td>
                                                    <td>21st October 2017</td>
                                                    <td>21st October 2017</td>
                                                    <td>21st October 2017</td>
                                                    <td>20,00,000</td>
                                                </tr>
                                                <tr>
                                                    <td><a class="tenderdetailsTab" data-toggle="tab" href="#TenderDetails">TestTender1103</a></td>
                                                    <td>Browser Requirements </td>
                                                    <td>21st October 2017</td>
                                                    <td>21st October 2017</td>
                                                    <td>21st October 2017</td>
                                                    <td>20,00,000</td>
                                                </tr>
                                                <tr>
                                                    <td><a class="tenderdetailsTab" data-toggle="tab" href="#TenderDetails">TestTender1103</a></td>
                                                    <td>Browser Requirements </td>
                                                    <td>21st October 2017</td>
                                                    <td>21st October 2017</td>
                                                    <td>21st October 2017</td>
                                                    <td>20,00,000</td>
                                                </tr>
                                            </tbody>
                                        </table>      
      </div>
      <!-- <div class="row">
      	<div class="col-sm-6">
	<div id="q2_2010">
	<div id="q1_2010">
	<div id="q4_2009">
	<div id="q3_2009">
	<div id="q2_2009">
	<div id="q1_2005">
		<div id="labels">
			<ul>
				<li><span></span>Internet Explorer</li>
				<li><span></span>Mozilla Firefox</li>
				<li><span></span>Google Chrome</li>
				<li><span></span>Safari</li>
				<li><span></span>Opera</li>
			</ul>
		</div>
		<div id="pie_ico">Pie &raquo;</div>
		<div id="pyr_ico">&laquo; Pyramid</div>
		<div id="percentage_wrapper">
			<div id="percentage">
				<ul>
					<li><p>60.14%</p><p>61.79%</p><p>63.90%</p><p>67.02%</p><p>68.28%</p><p>89.68%</p></li>
					<li><p>24.98%</p><p>24.56%</p><p>24.39%</p><p>23.28%</p><p>23.22%</p><p>6.83%</p></li>
					<li><p>7.14%</p><p>6.03%</p><p>4.27%</p><p>3.08%</p><p>2.35%</p><p>0%</p></li>
					<li><p>5.10%</p><p>4.91%</p><p>4.64%</p><p>4.35%</p><p>3.89%</p><p>2.36%</p></li>
					<li><p>2.66%</p><p>2.73%</p><p>2.52%</p><p>2.29%</p><p>2.27%</p><p>1.15%</p></li>
				</ul>
			</div>
		</div>
		<div id="slider">
			<div id="chart_holder">
				<div id="pie_chart">
					<ul>
						<li id="c1_r"><p><span class="pie_left"></span></p></li> 	
						<li id="c1_l"><p><span class="pie_right"></span></p></li>
						<li id="c2_r"><p><span class="pie_left"></span></p></li> 	
						<li id="c2_l"><p><span class="pie_right"></span></p></li>
						<li id="c3_r"><p><span class="pie_left"></span></p></li> 	
						<li id="c3_l"><p><span class="pie_right"></span></p></li>
						<li id="c4_r"><p><span class="pie_left"></span></p></li> 	
						<li id="c4_l"><p><span class="pie_right"></span></p></li>
						<li id="c5_r"><p><span class="pie_left"></span></p></li> 	
						<li id="c5_l"><p><span class="pie_right"></span></p></li>
					</ul>
				</div>
				<div id="pyr_chart">
					<ul>
						<li></li>
						<li></li>
						<li></li>
						<li></li>
						<li></li>
						<li></li>
						<li></li>
					</ul>
				</div>
			</div>
		</div>
		
	</div> q1_2005
	</div> q2_2009
	</div> q3_2009
	</div> q4_2009
	</div> q1_2010
	</div> q2_2010
</div>
      	<div class="col-sm-6"></div>
      </div> -->
  </div>
  <div class="clearfix"></div>
  <script>
  $(document).ready(function(){
    $('#sidebar-wrapper').addClass('active');
    $('.tendworkdet').click(function(){
    	$('.Procurementstender_details_containt').hide();
    	$('.ReverseAuction_details_containt').hide();
    	$('.ForwardAuction_details_containt').hide();
    	$('.workstender_details_containt').show();
    });
    $('.tendprodet').click(function(){
    	$('.Procurementstender_details_containt').show();
    	$('.ReverseAuction_details_containt').hide();
    	$('.ForwardAuction_details_containt').hide();
    	$('.workstender_details_containt').hide();
    });
    $('.foauctdet').click(function(){
    	$('.Procurementstender_details_containt').hide();
    	$('.ReverseAuction_details_containt').hide();
    	$('.ForwardAuction_details_containt').show();
    	$('.workstender_details_containt').hide();
    });
    $('.reauctdet').click(function(){
    	$('.Procurementstender_details_containt').hide();
    	$('.ReverseAuction_details_containt').show();
    	$('.ForwardAuction_details_containt').hide();
    	$('.workstender_details_containt').hide();
    });
    
  });
</script>
<script>
$(document).ready(function(){
	 var lengthMenu;

	    if ($(window).width() < 480) {
	        $('.mobileNav').show();
	        $.fn.DataTable.ext.pager.numbers_length = 4;       
	        lengthMenu = [ 1, 5, 7, 10, ],
	        [ 1, 5, 7, 10, ]
	    } else {        
	        lengthMenu = [ 5, 10, ],
	        [ 5, 10, ]
	    }
	    $('table').each(function(){		
			var text = []
			$(this).find('thead tr th').each(function(){
				text.push($(this).text())

				for (var i = 0; i < text.length; i++) {
					$(this).parents('table').find('tbody tr td:nth-of-type(' + (i + 1) +')').attr('data-th', text[i])
				}	
			});		
		});	
		$('table').DataTable({
			"lengthMenu":lengthMenu
		});
		$(".menu-toggle").click(function(e) {
			e.preventDefault();
			$('.dashcontent').toggleClass('fulldashcont');
		});
		$('#menu-content li:nth-child(8)').hide();
		$('#menu-content li:nth-child(9)').hide();
});
</script>