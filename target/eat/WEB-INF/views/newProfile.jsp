<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri = "http://www.springframework.org/tags/form" prefix = "sf"%>
<!doctype html>
<html lang="en">
<head>
	<meta charset="utf-8" />
	<!-- <link rel="icon" type="image/png" href="<%=request.getContextPath()%>/resources/${appMode}/assets/img/favicon.ico"> -->
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />

	<title>E-Auctionapp e-Tendering</title>

	<meta content='width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0' name='viewport' />
    <meta name="viewport" content="width=device-width" />


    <!-- Bootstrap core CSS     -->
    <link href="<%=request.getContextPath()%>/resources/${appMode}/assets/css/bootstrap.min.css?appVer=${appVer}" rel="stylesheet" />

    <!-- Animation library for notifications   -->
    <link href="<%=request.getContextPath()%>/resources/${appMode}/assets/css/animate.min.css?appVer=${appVer}" rel="stylesheet"/>

    <!--  Light Bootstrap Table core CSS    -->
    <link href="<%=request.getContextPath()%>/resources/${appMode}/assets/css/light-bootstrap-dashboard.css?appVer=${appVer}?v=1.4.0" rel="stylesheet"/>
	<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/${appMode}/assets/css/kendo.common-material.min.css?appVer=${appVer}" />
    <link rel="stylesheet" href="<%=request.getContextPath()%>/resources/${appMode}/assets/css/kendo.material.min.css?appVer=${appVer}" />
    <link rel="stylesheet" href="<%=request.getContextPath()%>/resources/${appMode}/assets/css/kendo.material.mobile.min.css?appVer=${appVer}" /> 

    <!--  CSS for Demo Purpose, don't include it in your project     -->
    <link href="<%=request.getContextPath()%>/resources/assets/css/common.css" rel="stylesheet" />
    


    <!--     Fonts and icons     -->
    <link href="<%=request.getContextPath()%>/resources/${appMode}/assets/css/font-awesome.min.css?appVer=${appVer}" rel="stylesheet">
    <link href='<%=request.getContextPath()%>/resources/${appMode}/assets/css/css.css?appVer=${appVer}' rel='stylesheet' type='text/css'>
    <link href="<%=request.getContextPath()%>/resources/${appMode}/assets/css/pe-icon-7-stroke.css?appVer=${appVer}" rel="stylesheet" />  
    <link href="<%=request.getContextPath()%>/resources/${appMode}/assets/css/demo.css?appVer=${appVer}" rel="stylesheet" />
    <!--   Core JS Files   -->
    <script src="<%=request.getContextPath()%>/resources/${appMode}/assets/js/jquery.3.2.1.min.js?appVer=${appVer}" type="text/javascript"></script>
	<script src="<%=request.getContextPath()%>/resources/${appMode}/assets/js/bootstrap.min.js?appVer=${appVer}" type="text/javascript"></script>

	<!--  Charts Plugin -->
	

    <!--  Notifications Plugin    -->
    <script src="<%=request.getContextPath()%>/resources/${appMode}/assets/js/bootstrap-notify.js?appVer=${appVer}"></script>

    <!--  Google Maps Plugin    -->

    <!-- Light Bootstrap Table Core javascript and methods for Demo purpose -->
	<script src="<%=request.getContextPath()%>/resources/${appMode}/assets/js/light-bootstrap-dashboard.js?appVer=${appVer}?v=1.4.0"></script>

	<!-- Light Bootstrap Table DEMO methods, don't include it in your project! -->
	<script src="<%=request.getContextPath()%>/resources/${appMode}/assets/js/demo.js?appVer=${appVer}"></script>
    <script src="<%=request.getContextPath()%>/resources/${appMode}/assets/js/kendo.all.min.js?appVer=${appVer}"></script>
</head>
<body>
<div id="loading-wrapper">
 		  <div id="loading-text">LOADING</div>
 		  <div id="loading-content"></div>
		</div>
<div class="wrapper">
    <div class="sidebar" data-color="blue" data-image="<%=request.getContextPath()%>/resources/${appMode}/assets/img/sidebar-5.jpg?appVer=${appVer}">

    <!--

        Tip 1: you can change the color of the sidebar using: data-color="blue | azure | green | orange | red | purple"
        Tip 2: you can also add an image using data-image tag

    -->

    	<div class="sidebar-wrapper">
            <div class="logo">
                <a href="" class="simple-text">
                   E-Auctionapp e-Tendering
                </a>
            </div>

            <ul class="nav">
                <li class="active">
                    <a href="dashboard.html">
                        <i class="fa fa-tachometer" aria-hidden="true"></i>
                        <p>Dashboard</p>
                    </a>
                </li>
                
                <li class="nav-itemmain">
                        <a class="nav-link collapsed" data-toggle="collapse" href="#componentsExamples" aria-expanded="false">                            
                            
                                <i class="fa fa-id-card-o" aria-hidden="true"></i>
                       		    <p>Master  <b class="caret"></b></p>
                               
                            
                        </a>
                        <div class="collapse" id="componentsExamples" style="">
                            <ul class="nav col-sub-nav">
                                <li class="nav-item">
                                    <a class="nav-link" href="./components/buttons.html">
                                        <i class="fa fa-plus-square-o" aria-hidden="true"></i>
                                        <span class="sidebar-normal">Material</span>
                                    </a>
                                </li>
                                <li class="nav-item">
                                    <a class="nav-link" href="./components/grid.html">
                                        <i class="fa fa-plus-square-o" aria-hidden="true"></i>
                                        <span class="sidebar-normal">Document Type</span>
                                    </a>
                                </li> 
                                <li class="nav-item">
                                    <a class="nav-link" href="./components/grid.html">
                                        <i class="fa fa-plus-square-o" aria-hidden="true"></i>
                                        <span class="sidebar-normal">Tender Budget Type</span>
                                    </a>
                                </li>  
                                <li class="nav-item">
                                    <a class="nav-link" href="./components/grid.html">
                                        <i class="fa fa-plus-square-o" aria-hidden="true"></i>
                                        <span class="sidebar-normal">Bid Type</span>
                                    </a>
                                </li>
                                <li class="nav-item">
                                    <a class="nav-link" href="./components/grid.html">
                                        <i class="fa fa-plus-square-o" aria-hidden="true"></i>
                                        <span class="sidebar-normal">UOM</span>
                                    </a>
                                </li>
                                <li class="nav-item">
                                    <a class="nav-link" href="./components/grid.html">
                                        <i class="fa fa-plus-square-o" aria-hidden="true"></i>
                                        <span class="sidebar-normal">Payment Type</span>
                                    </a>
                                </li>
                                <li class="nav-item">
                                    <a class="nav-link" href="./components/grid.html">
                                        <i class="fa fa-plus-square-o" aria-hidden="true"></i>
                                        <span class="sidebar-normal">Contractor Type</span>
                                    </a>
                                </li> 
                                <li class="nav-item">
                                    <a class="nav-link" href="./components/grid.html">
                                        <i class="fa fa-plus-square-o" aria-hidden="true"></i>
                                        <span class="sidebar-normal">Material Group</span>
                                    </a>
                                </li>
                                <li class="nav-item">
                                    <a class="nav-link" href="./components/grid.html">
                                        <i class="fa fa-plus-square-o" aria-hidden="true"></i>
                                        <span class="sidebar-normal">Material Sub Group</span>
                                    </a>
                                </li>                                          
                            </ul>
                        </div>
                    </li>
                    <li class="nav-itemmain">
                        <a class="nav-link collapsed" data-toggle="collapse" href="#componentsExamples2" aria-expanded="false">                            
                             <i class="fa fa-file-text" aria-hidden="true"></i>
                        <p>Tenders <b class="caret"></b></p>   
                        </a>
                        <div class="collapse" id="componentsExamples2" style="">
                            <ul class="nav col-sub-nav">
                                <li class="nav-item">
                                    <a class="nav-link" href="./components/buttons.html">
                                        <i class="fa fa-plus-square-o" aria-hidden="true"></i>
                                        <span class="sidebar-normal">Tenders For Procurement</span>
                                    </a>
                                </li>
                                <li class="nav-item">
                                    <a class="nav-link" href="./components/grid.html">
                                        <i class="fa fa-plus-square-o" aria-hidden="true"></i>
                                        <span class="sidebar-normal">Tenders For Works</span>
                                    </a>
                                </li>
                                <li class="nav-item">
                                    <a class="nav-link" href="./components/grid.html">
                                        <i class="fa fa-plus-square-o" aria-hidden="true"></i>
                                        <span class="sidebar-normal">Reverse Auction For Procurement</span>
                                    </a>
                                </li>                                  
                            </ul>
                        </div>
                    </li>
                <li>
                    <a href="">
                        <i class="fa fa-newspaper-o" aria-hidden="true"></i>
                        <p>Bids</p>
                    </a>
                </li>
                <li>
                    <a href="">
                        <i class="fa fa-money" aria-hidden="true"></i>
                        <p>Payments</p>
                    </a>
                </li>
                <li>
                    <a href="">
                        <i class="fa fa-cogs" aria-hidden="true"></i>
                        <p>Utilities</p>
                    </a>
                </li>
                <li>
                    <a href="">
                        <i class="fa fa-certificate" aria-hidden="true"></i>
                        <p>Certificate</p>
                    </a>
                </li>
				
            </ul>
    	</div>
    </div>

    <div class="main-panel">
        <nav class="navbar navbar-default navbar-fixed">
            <div class="container-fluid">
                <div class="navbar-header">
                <button id="minimizeSidebar" class="btn btn-primary btn-fill btn-round slidetoggle btn-icon pull-left">
                           <i class="fa fa-navicon"></i>
                        </button>
                    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#navigation-example-2">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <a class="navbar-brand" href="#">Vendor Registration</a>
                </div>
                <div class="collapse navbar-collapse">
                    <ul class="nav navbar-nav navbar-left">
                        <li>
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                                <i class="fa fa-dashboard"></i>
								<p class="hidden-lg hidden-md">Vendor Registration</p>
                            </a>
                        </li>
                        <li class="dropdown">
                              <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                                    <i class="fa fa-globe"></i>
                                    <b class="caret hidden-lg hidden-md"></b>
									<p class="hidden-lg hidden-md">
										5 Notifications
										<b class="caret"></b>
									</p>
                              </a>
                              <ul class="dropdown-menu">
                                <li><a href="#">Notification 1</a></li>
                                <li><a href="#">Notification 2</a></li>
                                <li><a href="#">Notification 3</a></li>
                                <li><a href="#">Notification 4</a></li>
                                <li><a href="#">Another notification</a></li>
                              </ul>
                        </li>                        
                    </ul>

                    <ul class="nav navbar-nav navbar-right">                       
                        <li class="dropdown">                        	
                              <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                                     <p>
                                    <span>Welcome | <i class="fa fa-user" aria-hidden="true"></i>
										E-Auctionapp_User 
										<b class="caret"></b>
									</p>

                              </a>
                              <ul class="dropdown-menu">
                                <li><a href="Profile.html">Profile</a></li>
                                <li><a href="#">Logout</a></li>
                              </ul>
                        </li>
                        
						<li class="separator hidden-lg"></li>
                    </ul>
                </div>
            </div>
        </nav>


        <div class="content">
            <div class="container-fluid">
                <div class="row">
                    <div class="col-md-12">
                        <div class="card">
							<div id="example">

                                    <div class="demo-section k-content">

                                        <div id="tabstrip" class="Firsttab">
                                            <ul>
                                                <!-- tabs -->
                                                <li class="k-state-active">Account Type</li>
                                                <li>Company Details</li>
                                                <li>Company Contacts</li>
                                                <li>Digital Signatory</li>
                                                <li>Director Details</li>
                                                <!-- <li>Experience Details</li> -->
                                                <li>Factory Details</li>
                                                <li>Financial Details</li>
                                                <!-- <li>Attach Copy of Profit and Loss Account</li>
                         <li>Attach Copy of Balance Sheet Account</li>
                         <li>Attach Documents Details</li>
                         <li>Attach Turnover(in lakhs)</li> -->
                                                <!-- <li>Permanent Bank Guarantee</li> -->
                                                
                                                <!-- <li>Factory Essential Details<</li>
                         <li>Factory License Details</li>
                         <li>Factory Contact Person Details</li> -->
                                                <!-- <li>ISO Certification</li> 
                         <li>Items Manufactured</li>
                         <li>SSI Registration</li>
                         <li>NSIC Registration</li> 
                         <li>Past Performance</li>
                         <li>RDAE</li>
                         <li>Other Eligibilities</li>
                         <li>BIS</li>
                         <li>Payment</li> -->
                                                <li>Completion Page</li>
                                                <li>Manufacturer</li>
                                                <!-- 
                         <li>Factory Essential</li>
                         <li>Factory License Details</li>
                         <li>Items Traded by Trader</li>
                         <li>Past Performance Details</li>
                         <li>Vendor Payment Details</li>
                         <li>Completion Page</li>  -->
                                                <!-- tabs -->
                                            </ul>

                                            <!--fields of field group 1  -->
                                            <div>
                                                <div class="btn-group" data-toggle="buttons">
                                                    <div class="form-group">
                                                        <div class="col-sm-12">
                                                            <label class="btn-fill btn-wd btn btn-primary active">
                                                                <input type="radio" name="options" onchange="valueChanged()" id="option2" autocomplete="off" chacked>
                                                                <span class="glyphicon glyphicon-ok"></span> Contractor
                                                            </label>
                                                        </div>
                                                    </div>
                                                    <div class="clearfix"></div>
                                                    <div class="form-group">
                                                        <div class="col-sm-12">
                                                            <label class="btn-fill btn-wd btn btn-primary">
                                                                <input type="radio" name="options" class="Materialvender" id="option2" onchange="valueChanged()" autocomplete="off">
                                                                <span class="glyphicon glyphicon-ok"></span> Material Vender
                                                            </label>
                                                            <span class="Subvender">
												<div class="btn-group btnmrg" data-toggle="buttons">
												 <label class="btn-fill btn-wd btn btn-success">
														<input type="radio" name="options" id="option3">
														<span class="glyphicon glyphicon-ok"></span> Manufacturer
                                                            </label>

                                                            <label class="btn-fill btn-wd btn btn-warning">
                                                                <input type="radio" name="options" id="option3">
                                                                <span class="glyphicon glyphicon-ok"></span> Trader
                                                            </label>
                                                        </div>
                                                        </span>
                                                    </div>															
                                                </div>
                                                <div class="form-group">
                                                    <div class="col-sm-12">
                                                        <label class="btn-fill btn-wd btn btn-primary ">
                                                            <input type="radio" name="options" onchange="valueChanged()" id="option2" autocomplete="off" chacked>
                                                            <span class="glyphicon glyphicon-ok"></span> Customer
                                                        </label>
                                                    </div>
                                                </div>
                                                </div>
                                        </div>
                                        <!--fields of field group 1  -->

                                        <!--fields of field group 2  -->
                                        <div>
                                        <div class="form-group">
                                        <div class="col-sm-12"><h4 ><b>Company Details</b></h4><hr></div>
                                        </div>
                                        
                                            <div class="form-group">
                                                <div class="col-sm-3">
                                                    <div class="styled-input">
                                                        <input type="text" id="" name="" value="NovelERP Solution" required />
                                                        <label>Company Name<span class="red">*</span></label>
                                                        <span></span>
                                                    </div>
                                                </div>
                                                <div class="col-sm-3">
                                                    <div class="styled-input">
                                                        <select id="companyType" name="companyType" required></select>
                                                        <label>Company Type<span class="red">*</span></label>
                                                        <span></span>
                                                    </div>
                                                </div>

                                                <div class="col-sm-3">
                                                    <div class="styled-input">
                                                        <input type="text" id="" name="" value="12345" title="Company Registration Number"  required />
                                                        <label>CRN<span class="red">*</span></label>
                                                        <span></span>
                                                    </div>
                                                </div>
                                                
                                                

                                                <div class="col-sm-3">
                                                    <div class="styled-input">
                                                        <input type="text" id="GSTNumber" name="GSTNumber" required />
                                                        <label>GST Identification Number<span class="red">*</span></label>
                                                        <span></span>
                                                    </div>
                                                </div>
                                            </div>

                                            <div class="clearfix"></div>

                                            <div class="form-group">

                                                <div class="col-sm-3">
                                                    <div class="styled-input">
                                                        <input type="text" id="GSTIN" name="GSTIN" required />
                                                        <label>GSTIN Applicable Type<span class="red">*</span></label>
                                                        <span></span></div>
                                                </div>
                                                <div class="col-sm-3">
                                                    <div class="styled-input">
                                                        <select id="contractorType" name="contractorType" required /></select>
                                                        <label>Contractor Type<span class="red">*</span></label>
                                                        <span></span>
                                                    </div>
                                                </div>

                                                <div class="col-sm-3">
                                                    <div class="styled-input">
                                                        <input type="text" id="contractorType" name="contractorType" required />
                                                        <label>License Number<span class="red">*</span></label>
                                                        <span></span>
                                                    </div>
                                                </div>
                                            </div>
                                            
                                            <div class="clearfix"></div>
                                            <div class="form-group">
                                                <label class="col-sm-10">Note : <span class="smalltext">Registration process does not guarantee award of any contract.</span></label>
                                            </div>
                                            <div class="clearfix"></div>
                                            <div class="form-group">
                                             <div class="col-md-12 col-xs-12">
                                                <label class="checkbox-inline">
                                                    <input type="checkbox" value="" class=" isActive" checked="checked" >I have read the standards terms and conditions as specified by E-Auctionapp and I agree to them</label>
                                            </div>
                                               <!--  <div class="col-sm-12">
                                                    <input type="checkbox" class="checkbox">
                                               <label>I have read the standards terms and conditions as specified by E-Auctionapp and I agree to them</label>
                                                </div> -->
                                                
                                            </div>
                                        </div>
                                        <!--fields of field group 2  -->

                                        <!--fields of field group 3  -->
                                        <div>
                                        <!-- tab inside tab -->
                                            <div id="tabstrip6">
                                                <ul>
                                                    <li  class="k-state-active">Company Contact</li>
                                                    <li>Company Address</li>
                                                </ul>
												 <!--tab inside tab fields 1  -->
                                                <div>
                                                 
                                        <div class="form-group">
                                        <div class="col-sm-12"><h4 ><b>Company Contact Details</b></h4><hr></div></div>
                                        
                                                    <div class="form-group">
                                                <div class="col-sm-3">
                                                    <div class="styled-input">
                                                        <input type="text" id="title" name="title" required />
                                                        <label>Title [Mr.,Miss.,Mrs.,Dr.]<span class="red">*</span></label>
                                                        <span></span>
                                                    </div>
                                                </div>

                                                <div class="col-sm-3">
                                                    <div class="styled-input">
                                                        <input type="text" id="" name="" required />
                                                        <label>First name<span class="red">*</span></label>
                                                        <span></span>
                                                    </div>
                                                </div>

                                                <div class="col-sm-3">
                                                    <div class="styled-input">
                                                        <input type="text" id="" name="" required />
                                                        <label>Middle name<span class="red">*</span></label>
                                                        <span></span>
                                                    </div>
                                                </div>

                                                <div class="col-sm-3">
                                                    <div class="styled-input">
                                                        <input type="text" id="" name="" required />
                                                        <label>Last name<span class="red">*</span></label>
                                                        <span></span>
                                                    </div>
                                                </div>

                                            </div>
                                            
                                                    <div class="form-group">

                                                  <div class="col-sm-3">
                                                    <div class="styled-input">
                                                        <input type="text" id="Telephone1" name="Telephone1" title="Enter 10 digit number" required />
                                                        <label>Telephone 1<span class="red">*</span></label>
                                                        <span></span>
                                                    </div>
                                                </div>

                                                <div class="col-sm-3">
                                                    <div class="styled-input">
                                                        <input type="text" id="" name="" title="Enter 10 digit number" />
                                                        <label>Telephone 2</label>
                                                        <span></span>
                                                    </div>
                                                </div>

                                                <div class="col-sm-3">
                                                    <div class="styled-input">
                                                        <input type="text" id="" name="" title="Enter 10 digit number" />
                                                        <label>Mobile</label>
                                                        <span></span>
                                                    </div>
                                                </div>
                                                
                                                <div class="col-sm-3">
                                                    <div class="styled-input">
                                                        <input type="text" id="Fax1" name="Fax1" title="Enter 10 digit number" />
                                                        <label>Fax 1</label>
                                                        <span></span>
                                                    </div>
                                                </div>

                                            </div>

                                            <div class="clearfix"></div>

                                            <div class="form-group">

                                                

                                                <div class="col-sm-3">
                                                    <div class="styled-input">
                                                        <input type="text" id="Fax2" name="Fax2" title="Enter 10 digit number" />
                                                        <label>Fax 2</label>
                                                        <span></span>
                                                    </div>
                                                </div>

                                                <div class="col-sm-3">
                                                    <div class="styled-input">
                                                        <input type="text" id="" name="" required />
                                                        <label>Email Address<span class="red">*</span></label>
                                                        <span></span>
                                                    </div>
                                                </div>

                                                <div class="col-sm-3">
                                                    <div class="styled-input">
                                                        <select id="" name="" required /></select>
                                                        <label>Office Location Type<span class="red">*</span></label>
                                                        <span></span>
                                                    </div>
                                                </div>
                                                
                                                <div class="col-sm-3">
                                                    <div class="styled-input">
                                                        <select id="" name="" required /></select>
                                                        <label>Select Office Location<span class="red">*</span></label>
                                                        <span></span>
                                                    </div>
                                                </div>

                                            </div>
                                            </div>
                                            <!--tab inside tab fields 1  -->
                                            
                                            <!--tab inside tab fields 2  -->
												<div>
												
												<div class="form-group">
                                        <div class="col-sm-12"><h4 ><b>Company Address Details</b></h4><hr></div></div>
                                        
                                                    <div class="form-group">
                                                        <div class="col-sm-12">
                                                            <div class="styled-input">
                                                                <textarea type="text" id="address" name="address" required></textarea>
                                                                <label>Registered Office Address<span class="red">*</span></label>
                                                                <span></span></div>
                                                        </div>
                                                    </div>

                                                    <div class="form-group">
                                                        <div class="col-sm-3">
                                                            <div class="styled-input">
                                                                <input type="text" id="" name="" required />
                                                                <label>City<span class="red">*</span></label>
                                                                <span></span>
                                                            </div>
                                                        </div>

                                                        <div class="col-sm-3">
                                                            <div class="styled-input">
                                                                <select id="" name="" required /></select>
                                                                <label>District<span class="red">*</span></label>
                                                                <span></span>
                                                            </div>
                                                        </div>

                                                        <div class="col-sm-3">
                                                            <div class="styled-input">
                                                                <select id="" name="" required /></select>
                                                                <label>Country<span class="red">*</span></label>
                                                                <span></span>
                                                            </div>
                                                        </div>

                                                        <div class="col-sm-3">
                                                            <div class="styled-input">
                                                                <select id="" name="" required /></select>
                                                                <label>State<span class="red">*</span></label>
                                                                <span></span>
                                                            </div>
                                                        </div>

                                                    </div>

                                                    <div class="form-group">
                                                        <div class="col-sm-3">
                                                            <div class="styled-input">
                                                                <select id="" name="" required /></select>
                                                                <label>Pincode<span class="red">*</span></label>
                                                                <span></span>
                                                            </div>
                                                        </div>
                                                        
                                                        <div class="col-sm-3">
                                                            <div class="styled-input">
                                                                <input type="text" id="" name="" required />
                                                                <label>Ship to Address<span class="red">*</span></label>
                                                                <span></span>
                                                            </div>
                                                        </div>
                                                        
                                                        <div class="col-sm-3">
                                                            <div class="styled-input">
                                                                <input type="text" id="" name="" required />
                                                                <label>Bill to Address<span class="red">*</span></label>
                                                                <span></span>
                                                            </div>
                                                        </div>
                                                        
                                                    </div>
                                                </div>
                                                <!--tab inside tab fields 2  -->

                                        </div>
                                        </div>
                                        
                                        <!--fields of field group 3  -->

                                        <!--fields of field group 4  -->
                                        <div>
                                        <div class="form-group">
                                        <div class="col-sm-12">
                                        <h4 ><b>Digital Signatory</b></h4>
                                        <hr>
                                        </div>
                                        </div>
                                        
                                            <div class="form-group">
                                                <div class="col-sm-3">
                                                    <div class="styled-input">
                                                        <input type="text" id="title" name="title" required />
                                                        <label>Title [Mr.,Miss.,Mrs.,Dr.]<span class="red">*</span></label>
                                                        <span></span>
                                                    </div>
                                                </div>

                                                <div class="col-sm-3">
                                                    <div class="styled-input">
                                                        <input type="text" id="" name="" required />
                                                        <label>First name<span class="red">*</span></label>
                                                        <span></span>
                                                    </div>
                                                </div>

                                                <div class="col-sm-3">
                                                    <div class="styled-input">
                                                        <input type="text" id="" name="" required />
                                                        <label>Middle name<span class="red">*</span></label>
                                                        <span></span>
                                                    </div>
                                                </div>

                                                <div class="col-sm-3">
                                                    <div class="styled-input">
                                                        <input type="text" id="" name="" required />
                                                        <label>Last name<span class="red">*</span></label>
                                                        <span></span>
                                                    </div>
                                                </div>

                                            </div>

                                            <div class="clearfix"></div>

                                            <div class="form-group">
                                                <div class="col-sm-12">
                                                    <div class="styled-input">
                                                        <textarea type="text" id="address" name="address" required></textarea>
                                                        <label>Registered Office Address<span class="red">*</span></label>
                                                        <span></span></div>
                                                </div>

                                            </div>

                                            <div class="clearfix"></div>

                                            <div class="form-group">
                                                <div class="col-sm-3">
                                                    <div class="styled-input">
                                                        <input type="text" id="City" name="City" required />
                                                        <label>City<span class="red">*</span></label>
                                                        <span></span>
                                                    </div>
                                                </div>

                                                <div class="col-sm-3">
                                                    <div class="styled-input">
                                                        <input type="text" id="District" name="District" required />
                                                        <label>District<span class="red">*</span></label>
                                                        <span></span>
                                                    </div>
                                                </div>

                                                <div class="col-sm-3">
                                                    <div class="styled-input">
                                                        <select id="State" name="State" required /></select>
                                                        <label>Country<span class="red">*</span></label>
                                                        <span></span>
                                                    </div>
                                                </div>

                                                <div class="col-sm-3">
                                                    <div class="styled-input">
                                                        <select id="Country" name="Country" required /></select>
                                                        <label>State<span class="red">*</span></label>
                                                        <span></span>
                                                    </div>
                                                </div>

                                            </div>

                                            <div class="clearfix"></div>
                                            <div class="form-group">

                                                <div class="col-sm-3">
                                                    <div class="styled-input">
                                                        <input type="text" id="Pincode" name="Pincode" required />
                                                        <label>Pincode<span class="red">*</span></label>
                                                        <span></span>
                                                    </div>
                                                </div>

                                                <div class="col-sm-3">
                                                    <div class="styled-input">
                                                        <input type="text" id="Telephone1" name="Telephone1" title="Enter 10 digit number" required />
                                                        <label>Telephone 1<span class="red">*</span></label>
                                                        <span></span>
                                                    </div>
                                                </div>

                                                <div class="col-sm-3">
                                                    <div class="styled-input">
                                                        <input type="text" id="Telephone1" name="Telephone1" title="Enter 10 digit number"/>
                                                        <label>Telephone 2</label>
                                                        <span></span>
                                                    </div>
                                                </div>

                                                <div class="col-sm-3">
                                                    <div class="styled-input">
                                                        <input type="text" id="Telephone1" name="Telephone1" title="Enter 10 digit number" />
                                                        <label>Mobile</label>
                                                        <span></span>
                                                    </div>
                                                </div>

                                            </div>

                                            <div class="clearfix"></div>

                                            <div class="form-group">

                                                <div class="col-sm-3">
                                                    <div class="styled-input">
                                                        <input type="text" id="Fax1" name="Fax1" title="Enter 10 digit number"/>
                                                        <label>Fax 1</label>
                                                        <span></span>
                                                    </div>
                                                </div>

                                                <div class="col-sm-3">
                                                    <div class="styled-input">
                                                        <input type="text" id="Fax2" name="Fax2" title="Enter 10 digit number" />
                                                        <label>Fax 2<span class="red">*</span></label>
                                                        <span></span>
                                                    </div>
                                                </div>

                                                <div class="col-sm-3">
                                                    <div class="styled-input">
                                                        <input type="text" id="" name="" required />
                                                        <label>Email Address<span class="red">*</span></label>
                                                        <span></span>
                                                    </div>
                                                </div>

                                                <div class="col-sm-3">
                                                    <div class="styled-input">
                                                        <input type="text" id="" name="" required />
                                                        <label>Designation<span class="red">*</span></label>
                                                        <span></span>
                                                    </div>
                                                </div>

                                            </div>

                                            <div class="clearfix"></div>
                                            <div class="form-group">
                                                <div class="col-sm-3">
                                                    <label>Gender: <span class="red">*</span>
                                                        <input type="radio" name="gender" value="male" checked> Male
                                                        <input type="radio" name="gender" value="female"> Female
                                                        <input type="radio" name="gender" value="other"> Other
                                                    </label>
                                                </div>

                                                <div class="col-sm-3">
                                                    <div class="styled-input">
                                                        <input type="text" id="" name="" title="Date of Birth" required />
                                                        <label>DOB<span class="red">*</span></label>
                                                        <span></span>
                                                    </div>
                                                </div>

                                                <div class="col-sm-3">
                                                    <div class="styled-input">
                                                        <input type="text" id="" name="" required />
                                                        <label>Validity<span class="red">*</span></label>
                                                        <span></span>
                                                    </div>
                                                </div>

                                            </div>
                                        </div>
                                        <!--fields of field group 4  -->

                                        <!--fields of field group 5  -->
                                        <div>
                                        <div class="form-group">
                                        <div class="col-sm-12">
                                        <h4 ><b>Director Details</b></h4>
                                        <hr>
                                        </div>
                                        </div>
                                            <div class="form-group">
                                                <div class="col-sm-3">
                                                    <div class="styled-input">
                                                        <input type="text" id="title" name="title" required />
                                                        <label>Title [Mr.,Miss.,Mrs.,Dr.]<span class="red">*</span></label>
                                                        <span></span>
                                                    </div>
                                                </div>

                                                <div class="col-sm-3">
                                                    <div class="styled-input">
                                                        <input type="text" id="" name="" required />
                                                        <label>First Name<span class="red">*</span></label>
                                                        <span></span>
                                                    </div>
                                                </div>

                                                <div class="col-sm-3">
                                                    <div class="styled-input">
                                                        <input type="text" id="" name="" required />
                                                        <label>Middle Name<span class="red">*</span></label>
                                                        <span></span></div>
                                                </div>

                                                <div class="col-sm-3">
                                                    <div class="styled-input">
                                                        <input type="text" id="" name="" required />
                                                        <label>Last Name<span class="red">*</span></label>
                                                        <span></span>
                                                    </div>
                                                </div>
                                            </div>

                                            <div class="clearfix"></div>

                                            <div class="form-group">
                                                <div class="col-sm-12">
                                                    <div class="styled-input">
                                                        <textarea type="text" id="address" name="address" required></textarea>
                                                        <label>Registered Office Address<span class="red">*</span></label>
                                                        <span></span>
                                                    </div>
                                                </div>
                                            </div>

                                            <div class="clearfix"></div>

                                            <div class="form-group">
                                                <div class="col-sm-3">
                                                    <div class="styled-input">
                                                        <input type="text" id="" name="" required />
                                                        <label>City<span class="red">*</span></label>
                                                        <span></span>
                                                    </div>
                                                </div>

                                                <div class="col-sm-3">
                                                    <div class="styled-input">
                                                        <select id="" name="" required /></select>
                                                        <label>District<span class="red">*</span></label>
                                                        <span></span>
                                                    </div>
                                                </div>

                                                <div class="col-sm-3">
                                                    <div class="styled-input">
                                                        <select id="" name="" required /></select>
                                                        <label>Country<span class="red">*</span></label>
                                                        <span></span>
                                                    </div>
                                                </div>

                                                <div class="col-sm-3">
                                                    <div class="styled-input">
                                                        <select id="" name="" required /></select>
                                                        <label>State<span class="red">*</span></label>
                                                    </div>
                                                </div>
                                            </div>

                                            <div class="clearfix"></div>

                                            <div class="form-group">
                                                <div class="col-sm-3">
                                                    <div class="styled-input">
                                                        <select id="" name="" required /></select>
                                                        <label>Pincode<span class="red">*</span></label>
                                                        <span></span>
                                                    </div>
                                                </div>

                                                <div class="col-sm-3">
                                                    <div class="styled-input">
                                                        <input type="text" id="" name="" required />
                                                        <label>Email Id<span class="red">*</span></label>
                                                        <span></span>
                                                    </div>
                                                </div>

                                                <div class="col-sm-3">
                                                    <div class="styled-input">
                                                        <input type="text" id="" name="" required />
                                                        <label>Designation<span class="red">*</span></label>
                                                        <span></span>
                                                    </div>
                                                </div>

                                                <div class="col-sm-3">
                                                    <div class="styled-input">
                                                        <input type="text" id="" name="" title="Emter 10 digit number" required />
                                                        <label>Mobile Number<span class="red">*</span></label>
                                                        <span></span>
                                                    </div>
                                                </div>
                                            </div>

                                            <div class="clearfix"></div>

                                            <div class="form-group">
                                                <div class="col-sm-3">
                                                    <div class="styled-input">
                                                        <input type="text" id="" name="" title="Enter 10 digit number" required />
                                                        <label>Telephone 1<span class="red">*</span></label>
                                                        <span></span>
                                                    </div>
                                                </div>

                                                <div class="col-sm-3">
                                                    <div class="styled-input">
                                                        <input type="text" id="" name="" title="Enter 10 digit number" />
                                                        <label>Telephone 2</label>
                                                        <span></span>
                                                    </div>
                                                </div>

                                                <div class="col-sm-3">
                                                    <div class="styled-input">
                                                        <input type="text" id="" name="" title="Enter 10 digit number" />
                                                        <label>Fax 1</label>
                                                        <span></span>
                                                    </div>
                                                </div>

                                                <div class="col-sm-3">
                                                    <div class="styled-input">
                                                        <input type="text" id="" name="" title="Enter 10 digit number"/>
                                                        <label>Fax 2</label>
                                                        <span></span>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <!--fields of field group 5  -->

                                       

                                        <!--fields of field group 6  -->
                                        <div>

                                            <!-- tab inside tab -->
                                            <div id="tabstrip3">
                                                <ul>
                                                    <li  class="k-state-active">Factory Details</li>
                                                    <li>License Details</li>
                                                    <li>Contact Person</li>
                                                    <li>Experience Details</li>
                                                    <li>PBG Details</li>                                                    
                                                    <li>ISO Certification</li>
                                                    <li>Items Manufactured</li>
                                                    <li>SSI Registration</li>
                                                    <li>NSIC Registration</li>
                                                    <li>Past Performance</li>
                                                    <li>RDAEC</li>
                                                    <li>Other Eligibilty Details</li>
                                                    <li>BIS Details</li>
                                                    <li>Vendor Payment Details</li>
                                                </ul>
												 <!--tab inside tab fields 1  -->
                                                <div>
                                                <div class="form-group">
                                        <div class="col-sm-12">
                                        <h4 ><b>Factory Essential Details</b></h4>
                                        <hr>
                                        </div>
                                        </div>
                                                    <div class="form-group">
                                                        <div class="col-sm-3">
                                                            <div class="styled-input">
                                                                <input type="text" id="" name="" required />
                                                                <label>Factory Name<span class="red">*</span></label>
                                                                <span></span>
                                                            </div>
                                                        </div>

                                                        <div class="col-sm-3">
                                                            <div class="styled-input">
                                                                <input type="text" id="" name="" required />
                                                                <label>Established date of Factory<span class="red">*</span></label>
                                                                <span></span>
                                                            </div>
                                                        </div>

                                                        <div class="col-sm-3">
                                                            <div class="styled-input">
                                                                <input type="text" id="" name="" required />
                                                                <label>Manpower<span class="red">*</span></label>
                                                                <span></span>
                                                            </div>
                                                        </div>

                                                        <div class="col-sm-3">
                                                            <div class="styled-input">
                                                                <input type="text" id="" name="" />
                                                                <label>Factory Inspection Report no.</label>
                                                                <span></span>
                                                            </div>
                                                        </div>
                                                    </div>

                                                    <div class="form-group">
                                                        <div class="col-sm-12">
                                                            <div class="styled-input">
                                                                <textarea type="text" id="address" name="address" required></textarea>
                                                                <label>Registered Office Address<span class="red">*</span></label>
                                                                <span></span></div>
                                                        </div>
                                                    </div>

                                                    <div class="form-group">
                                                        <div class="col-sm-3">
                                                            <div class="styled-input">
                                                                <input type="text" id="" name="" required />
                                                                <label>City<span class="red">*</span></label>
                                                                <span></span>
                                                            </div>
                                                        </div>

                                                        <div class="col-sm-3">
                                                            <div class="styled-input">
                                                                <select id="" name="" required /></select>
                                                                <label>District<span class="red">*</span></label>
                                                                <span></span>
                                                            </div>
                                                        </div>

                                                        <div class="col-sm-3">
                                                            <div class="styled-input">
                                                                <select id="" name="" required /></select>
                                                                <label>Country<span class="red">*</span></label>
                                                                <span></span>
                                                            </div>
                                                        </div>

                                                        <div class="col-sm-3">
                                                            <div class="styled-input">
                                                                <select id="" name="" required /></select>
                                                                <label>State<span class="red">*</span></label>
                                                                <span></span>
                                                            </div>
                                                        </div>

                                                    </div>

                                                    <div class="form-group">
                                                        <div class="col-sm-3">
                                                            <div class="styled-input">
                                                                <select id="" name="" required /></select>
                                                                <label>Pincode<span class="red">*</span></label>
                                                                <span></span>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                <!--tab inside tab fields 1  -->		
                                                
                                                <!-- tab inside tab fields 2 -->
                                                <div>
                                                <div class="form-group">
			                                        <div class="col-sm-12">
			                                        <h4 ><b>Factory License Details</b></h4>
			                                        <hr>
			                                        </div>
			                                        </div>
                                                    <div class="form-group">
                                                        <div class="col-sm-3">
                                                            <div class="styled-input">
                                                                <input type="text" id="" name="" />
                                                                <label>Factory License Number</label>
                                                                <span></span>
                                                            </div>
                                                        </div>

                                                        <div class="col-sm-3">
                                                            <div class="styled-input">
                                                                <input type="text" id="" name="" />
                                                                <label>Factory License Validity Date</label>
                                                                <span></span>
                                                            </div>
                                                        </div>

                                                        <div class="col-sm-3">
                                                            <label>Attach Factory License Copy</label>
                                                            <input type="file" id="" name="" class="form-control" />
                                                            <span></span>
                                                        </div>

                                                        <div class="col-sm-3">
                                                            <label>Attach Machinery List Copy of Factory<span class="red">*</span></label>
                                                            <input type="file" id="" name="" class="form-control" required />
                                                            <span></span>
                                                        </div>
                                                    </div>
                                                </div>
                                                <!-- tab inside tab fields 2 -->
                                                
                                                <!-- tab inside tab fields 3 -->
                                                <div>
                                                <div class="form-group">
			                                        <div class="col-sm-12">
			                                        <h4 ><b>Factory Contact Person Details</b></h4>
			                                        <hr>
			                                        </div>
			                                        </div>
                                                    <div class="form-group">
                                                        <div class="col-sm-3">
                                                            <div class="styled-input">
                                                                <input type="text" id="title" name="title" required />
                                                                <label>Title [Mr.,Miss.,Mrs.,Dr.]<span class="red">*</span></label>
                                                                <span></span>
                                                            </div>
                                                        </div>

                                                        <div class="col-sm-3">
                                                            <div class="styled-input">
                                                                <input type="text" id="" name="" required />
                                                                <label>First Name<span class="red">*</span></label>
                                                                <span></span>
                                                            </div>
                                                        </div>

                                                        <div class="col-sm-3">
                                                            <div class="styled-input">
                                                                <input type="text" id="" name="" required />
                                                                <label>Middle Name<span class="red">*</span></label>
                                                                <span></span></div>
                                                        </div>

                                                        <div class="col-sm-3">
                                                            <div class="styled-input">
                                                                <input type="text" id="" name="" required />
                                                                <label>Last Name<span class="red">*</span></label>
                                                                <span></span>
                                                            </div>
                                                        </div>
                                                    </div>

                                                    <div class="clearfix"></div>

                                                    <div class="form-group">
                                                        <div class="col-sm-12">
                                                            <div class="styled-input">
                                                                <textarea type="text" id="address" name="address" required></textarea>
                                                                <label>Registered Office Address<span class="red">*</span></label>
                                                                <span></span>
                                                            </div>
                                                        </div>
                                                    </div>

                                                    <div class="clearfix"></div>

                                                    <div class="form-group">
                                                        <div class="col-sm-3">
                                                            <div class="styled-input">
                                                                <input type="text" id="" name="" required />
                                                                <label>City<span class="red">*</span></label>
                                                                <span></span>
                                                            </div>
                                                        </div>

                                                        <div class="col-sm-3">
                                                            <div class="styled-input">
                                                                <select id="" name="" required /></select>
                                                                <label>District<span class="red">*</span></label>
                                                                <span></span>
                                                            </div>
                                                        </div>

                                                        <div class="col-sm-3">
                                                            <div class="styled-input">
                                                                <select id="" name="" required /></select>
                                                                <label>Country<span class="red">*</span></label>
                                                                <span></span>
                                                            </div>
                                                        </div>

                                                        <div class="col-sm-3">
                                                            <div class="styled-input">
                                                                <select id="" name="" required /></select>
                                                                <label>State<span class="red">*</span></label>
                                                            </div>
                                                        </div>
                                                    </div>

                                                    <div class="clearfix"></div>

                                                    <div class="form-group">
                                                        <div class="col-sm-3">
                                                            <div class="styled-input">
                                                                <input type="text" id="" name="" required />
                                                                <label>Pincode<span class="red">*</span></label>
                                                                <span></span>
                                                            </div>
                                                        </div>

                                                        <div class="col-sm-3">
                                                            <div class="styled-input">
                                                                <input type="text" id="" name="" required />
                                                                <label>Email Id<span class="red">*</span></label>
                                                                <span></span>
                                                            </div>
                                                        </div>

                                                        <div class="col-sm-3">
                                                            <div class="styled-input">
                                                                <input type="text" id="" name="" required />
                                                                <label>Designation<span class="red">*</span></label>
                                                                <span></span>
                                                            </div>
                                                        </div>

                                                        <div class="col-sm-3">
                                                            <div class="styled-input">
                                                                <input type="text" id="" name="" />
                                                                <label>Department</label>
                                                                <span></span>
                                                            </div>
                                                        </div>
                                                    </div>

                                                    <div class="clearfix"></div>

                                                    <div class="form-group">
                                                        <div class="col-sm-3">
                                                            <div class="styled-input">
                                                                <input type="text" id="" name="" title="Enter 10 digit number" required />
                                                                <label>Telephone 1<span class="red">*</span></label>
                                                                <span></span>
                                                            </div>
                                                        </div>

                                                        <div class="col-sm-3">
                                                            <div class="styled-input">
                                                                <input type="text" id="" name="" title="Enter 10 digit number" />
                                                                <label>Telephone 2</label>
                                                                <span></span>
                                                            </div>
                                                        </div>

                                                        <div class="col-sm-3">
                                                            <div class="styled-input">
                                                                <input type="text" id="" name="" title="Enter 10 digit number" />
                                                                <label>Fax 1</label>
                                                                <span></span>
                                                            </div>
                                                        </div>

                                                        <div class="col-sm-3">
                                                            <div class="styled-input">
                                                                <input type="text" id="" name="" title="Enter 10 digit number"/>
                                                                <label>Fax 2</label>
                                                                <span></span>
                                                            </div>
                                                        </div>
                                                    </div>

                                                    <div class="form-group">
                                                        <div class="col-sm-3">
                                                            <div class="styled-input">
                                                                <input type="text" id="" name="" title="Enter 10 digit number" />
                                                                <label>Mobile Number</label>
                                                                <span></span>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                <!-- tab inside tab fields 3 -->
                                                
                                                <!-- tab inside tab fields 4 -->
                                                <div>
                                                <div class="form-group">
                                        <div class="col-sm-12">
                                        <h4 ><b>Experience Details</b></h4>
                                        <hr>
                                        </div>
                                        </div>
                                                <div class="col-sm-12">
                                                    <label>Experience in Manufacturing: </label>

                                                    <div class="form-group">
                                                        <div class="col-sm-3">
                                                            <div class="styled-input">
                                                                <select id="" name="" /></select>
                                                                <label>Years</label>
                                                                <span></span>
                                                            </div>
                                                        </div>

                                                        <div class="col-sm-3">
                                                            <div class="styled-input">
                                                                <select id="" name="" /></select>
                                                                <label>Months</label>
                                                                <span></span>
                                                            </div>
                                                        </div>
                                                    </div>

                                                    <label>Experience in Design: </label>

                                                    <div class="form-group">
                                                        <div class="col-sm-3">
                                                            <div class="styled-input">
                                                                <select id="" name="" /></select>
                                                                <label>Years</label>
                                                                <span></span>
                                                            </div>
                                                        </div>

                                                        <div class="col-sm-3">
                                                            <div class="styled-input">
                                                                <select id="" name="" /></select>
                                                                <label>Months</label>
                                                                <span></span>
                                                            </div>
                                                        </div>
                                                    </div>

                                                    <label>Experience in Testing: </label>

                                                    <div class="form-group">
                                                        <div class="col-sm-3">
                                                            <div class="styled-input">
                                                                <select id="" name="" /></select>
                                                                <label>Years</label>
                                                                <span></span>
                                                            </div>
                                                        </div>

                                                        <div class="col-sm-3">
                                                            <div class="styled-input">
                                                                <select id="" name="" /></select>
                                                                <label>Months</label>
                                                                <span></span>
                                                            </div>
                                                        </div>
                                                    </div>

                                                    <label>Experience in Supplying: </label>

                                                    <div class="form-group">
                                                        <div class="col-sm-3">
                                                            <div class="styled-input">
                                                                <select id="" name="" /></select>
                                                                <label>Years</label>
                                                                <span></span>
                                                            </div>
                                                        </div>

                                                        <div class="col-sm-3">
                                                            <div class="styled-input">
                                                                <select id="" name="" /></select>
                                                                <label>Months</label>
                                                                <span></span>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                </div>
                                                <!-- tab inside tab fields 4 -->
												
												 <!-- tab inside tab fields 5  -->
                                                <div>
                                                <div class="form-group">
                                        <div class="col-sm-12">
                                        <h4 ><b>Permanent Bank Guarantee Details</b></h4>
                                        <hr>
                                        </div>
                                        </div>
                                                    <div class="form-group">
                                                        <div class="col-sm-3">
                                                            <input type="checkbox" id="" name="" />
                                                            <label>Not Applicable</label>
                                                            <span></span>
                                                        </div>
                                                    </div>

                                                    <div class="form-group">
                                                        <div class="col-sm-4">
                                                            <div class="styled-input">
                                                                <input type="text" id="" name="" required />
                                                                <label>Bank Guarantee Number<span class="red">*</span></label>
                                                                <span></span>
                                                            </div>
                                                        </div>

                                                        <div class="col-sm-4">
                                                            <div class="styled-input">
                                                                <input type="text" id="" name="" title="Permanent Bank Guarantee Amount" required />
                                                                <label>PBG Amount<span class="red">*</span></label>
                                                                <span></span></div>
                                                        </div>

                                                        <div class="col-sm-4">
                                                            <div class="styled-input">
                                                                <input type="text" id="" name="" required />
                                                                <label>Issue date<span class="red">*</span></label>
                                                                <span></span></div>
                                                        </div>
                                                    </div>

                                                    <div class="form-group">
                                                        <div class="col-sm-4">
                                                            <div class="styled-input">
                                                                <input type="text" id="" name="" required />
                                                                <label>Validity date<span class="red">*</span></label>
                                                                <span></span></div>
                                                        </div>

                                                        <div class="col-sm-4">
                                                            <label>Bank Guarantee Copy<span class="red">*</span></label>
                                                            <input type="file" id="" name="" class="form-control" required />
                                                            <span></span>
                                                        </div>

                                                    </div>
                                                </div>
                                                <!-- tab inside tab fields 5 -->

                                                <!--tab inside tab fields 6  -->
                                                <div>
                                                <div class="form-group">
			                                        <div class="col-sm-12">
			                                        <h4 ><b>Factory ISO Certification Details</b></h4>
			                                        <hr>
			                                        </div>
			                                        </div>
                                                    
                                                    <div class="form-group">
                                                        <div class="col-sm-3">
                                                            <input type="checkbox" id="" name="" />
                                                            <label>Not Applicable</label>
                                                            <span></span>
                                                        </div>
                                                    </div>

                                                    <div class="form-group">
                                                        <div class="col-sm-4">
                                                            <div class="styled-input">
                                                                <input type="text" id="" name="" required />
                                                                <label>Name of ISO Standard<span class="red">*</span></label>
                                                                <span></span>
                                                            </div>
                                                        </div>

                                                        <div class="col-sm-4">
                                                            <div class="styled-input">
                                                                <input type="text" id="" name="" required />
                                                                <label>ISO Certifying Authority<span class="red">*</span></label>
                                                                <span></span></div>
                                                        </div>

                                                        <div class="col-sm-4">
                                                            <div class="styled-input">
                                                                <input type="text" id="" name="" required />
                                                                <label>ISO Certificate Number<span class="red">*</span></label>
                                                                <span></span></div>
                                                        </div>
                                                    </div>
                                                </div>
                                                <!--tab inside tab fields 6  -->

                                                <!--tab inside tab fields 7  -->
                                                <div>
													<div class="form-group">
			                                        <div class="col-sm-12">
			                                        <h4 ><b>Items Manufactured by Firms</b></h4>
			                                        <hr>
			                                        </div>
			                                        </div>
			                                        
                                                    <div class="form-group">
                                                        <div class="col-sm-3">
                                                            <div class="styled-input">
                                                                <input type="text" id="" name="" required />
                                                                <label>Item Name</label>
                                                                <span></span>
                                                            </div>
                                                        </div>

                                                        <div class="col-sm-3">
                                                            <div class="styled-input">
                                                                <input type="text" id="" name="" required />
                                                                <label>Unit<span class="red">*</span></label>
                                                                <span></span></div>
                                                        </div>

                                                        <div class="col-sm-3">
                                                            <div class="styled-input">
                                                                <input type="text" id="" name="" title="Quantity Manufactured Per Month"  required />
                                                                <label>Quantity Manufactured/Month<span class="red">*</span></label>
                                                                <span></span></div>
                                                        </div>
                                                        <div class="col-sm-3">
                                                            <div class="styled-input">
                                                                <input type="text" id="" name="" title="Turnover Per Annum For Item(In Lakhs)" required />
                                                                <label>Turnover Per Annum For Item<span class="red">*</span></label>
                                                                <span></span></div>
                                                        </div>
                                                    </div>
                                                    
                                                    <div class="form-group">
                                                        <div class="col-sm-3">
                                                            <div class="styled-input">
                                                                <input type="text" id="" name="" required />
                                                                <label>Industrial license number</label>
                                                                <span></span>
                                                            </div>
                                                        </div>

                                                        <div class="col-sm-3">
                                                            <div class="styled-input">
                                                                <input type="text" id="" name="" required />
                                                                <label>Industrial License Copy<span class="red">*</span></label>
                                                                <span></span></div>
                                                        </div>
                                                    </div>

                                                </div>
                                                <!--tab inside tab fields 7   -->

                                                <!--tab inside tab fields 8   -->
                                                <div>
                                                <div class="form-group">
			                                        <div class="col-sm-12">
			                                        <h4 ><b>Small Scale Industry Registration Details</b></h4>
			                                        <hr>
			                                        </div>
			                                        </div>
			                                        
                                                    <div class="form-group">
                                                        <div class="col-sm-3">
                                                            <input type="checkbox" id="" name="" />
                                                            <label>Not Applicable</label>
                                                            <span></span>
                                                        </div>
                                                    </div>

                                                    <div class="form-group">
                                                        <div class="col-sm-4">
                                                            <div class="styled-input">
                                                                <select id="" name="" /></select>
                                                                <label>Type of SSI Registration</label>
                                                                <span></span>
                                                            </div>
                                                        </div>

                                                        <div class="col-sm-4">
                                                            <div class="styled-input">
                                                                <input type="text" id="" name="" required />
                                                                <label>SSI Registration Authority<span class="red">*</span></label>
                                                                <span></span></div>
                                                        </div>

                                                        <div class="col-sm-4">
                                                            <div class="styled-input">
                                                                <input type="text" id="" name="" required />
                                                                <label>SSI Registration Number<span class="red">*</span></label>
                                                                <span></span></div>
                                                        </div>
                                                    </div>

                                                    <div class="form-group">
                                                        <div class="col-sm-4">
                                                            <div class="styled-input">
                                                                <input type="text" id="" name="" required />
                                                                <label>Issue Date of SSI Registration<span class="red">*</span></label>
                                                                <span></span></div>
                                                        </div>

                                                        <div class="col-sm-4">
                                                            <div class="styled-input">
                                                                <input type="text" id="" name="" required />
                                                                <label>Validity Date of SSI Registration<span class="red">*</span></label>
                                                                <span></span>
                                                            </div>
                                                        </div>

                                                        <div class="col-sm-4">
                                                            <div class="styled-input">
                                                                <input type="text" id="" name="" />
                                                                <label>Date of Commencement of Commercial Production</label>
                                                                <span></span></div>
                                                        </div>
                                                    </div>

                                                    <div class="form-group">
                                                        <div class="col-sm-4">
                                                            <div class="styled-input">
                                                                <select id="" name="" required /></select>
                                                                <label>Items For Which SSI Registration is Given<span class="red">*</span></label>
                                                                <span></span></div>
                                                        </div>

                                                        <div class="col-sm-4">
                                                            <div class="styled-input">
                                                                <select id="" name="" required /></select>
                                                                <label>SSI Registration Certificate is under renewal process<span class="red">*</span></label>
                                                                <span></span></div>
                                                        </div>

                                                        <div class="col-sm-4">
                                                            <label>Attach SSI Registration Copy<span class="red">*</span></label>
                                                            <input type="file" id="" name="" class="form-control" required />
                                                            <span></span>
                                                        </div>

                                                    </div>
                                                </div>
                                                <!--tab inside tab fields 8   -->

                                                <!--tab inside tab fields 9   -->
                                                <div>
                                                <div class="form-group">
			                                        <div class="col-sm-12">
			                                        <h4 ><b>Factory NSIC Registration Details</b></h4>
			                                        <hr>
			                                        </div>
			                                        </div>
			                                        
                                                    <div class="form-group">
                                                        <div class="col-sm-3">
                                                            <input type="checkbox" id="" name="" />
                                                            <label>Not Applicable</label>
                                                            <span></span>
                                                        </div>
                                                    </div>

                                                    <div class="form-group">
                                                        <div class="col-sm-4">
                                                            <div class="styled-input">
                                                                <input type="text" id="" name="" required />
                                                                <label>NSIC Registering Authority<span class="red">*</span></label>
                                                                <span></span>
                                                            </div>
                                                        </div>

                                                        <div class="col-sm-4">
                                                            <div class="styled-input">
                                                                <input type="text" id="" name="" required />
                                                                <label>NSIC Registration Number<span class="red">*</span></label>
                                                                <span></span></div>
                                                        </div>

                                                        <div class="col-sm-4">
                                                            <div class="styled-input">
                                                                <input type="text" id="" name="" required />
                                                                <label>Issue Date of NSIC Registration<span class="red">*</span></label>
                                                                <span></span></div>
                                                        </div>
                                                    </div>

                                                    <div class="form-group">
                                                        <div class="col-sm-4">
                                                            <div class="styled-input">
                                                                <input type="text" id="" name="" required />
                                                                <label>Validity Date of NSIC Registration<span class="red">*</span></label>
                                                                <span></span></div>
                                                        </div>

                                                        <div class="col-sm-4">
                                                            <div class="styled-input">
                                                                <input type="text" id="" name="" />
                                                                <label>Date of Commencement of Commercial Production</label>
                                                                <span></span>
                                                            </div>
                                                        </div>

                                                        <div class="col-sm-4">
                                                            <div class="styled-input">
                                                                <select id="" name="" required /></select>
                                                                <label>Items For Which NSIC Registration is Given<span class="red">*</span></label>
                                                                <span></span></div>
                                                        </div>
                                                    </div>

                                                    <div class="form-group">
                                                        <div class="col-sm-4">
                                                            <label>Attach SSI Registration Copy<span class="red">*</span></label>
                                                            <input type="file" id="" name="" class="form-control" required />
                                                            <span></span>
                                                        </div>
                                                    </div>
                                                </div>
                                                <!--tab inside tab fields 9  -->

                                                <!--tab inside tab fields 10  -->
                                                <div>
                                                
                                                <div class="form-group">
			                                        <div class="col-sm-12">
			                                        <h4 ><b>Past Performance Details</b></h4>
			                                        <hr>
			                                        </div>
			                                        </div>
			                                        
                                                    <div class="form-group">
                                                        <div class="col-sm-3">
                                                            <div class="styled-input">
                                                                <select id="" name="" required /></select>
                                                                <label>Item Name<span class="red">*</span></label>
                                                                <span></span>
                                                            </div>
                                                        </div>

                                                        <div class="col-sm-3">
                                                            <div class="styled-input">
                                                                <select id="" name="" required /></select>
                                                                <label>Name of Firm<span class="red">*</span></label>
                                                                <span></span></div>
                                                        </div>

                                                        <div class="col-sm-3">
                                                            <div class="styled-input">
                                                                <input type="text" id="" name="" required />
                                                                <label>Order Start Date<span class="red">*</span></label>
                                                                <span></span></div>
                                                        </div>

                                                        <div class="col-sm-3">
                                                            <div class="styled-input">
                                                                <select id="" name="" required /></select>
                                                                <label>Order Completion Date<span class="red">*</span></label>
                                                                <span></span></div>
                                                        </div>
                                                    </div>

                                                    <div class="form-group">
                                                        <div class="col-sm-3">
                                                            <div class="styled-input">
                                                                <input type="text" id="" name="" required />
                                                                <label>Quantity Supplied<span class="red">*</span></label>
                                                                <span></span></div>
                                                        </div>

                                                        <div class="col-sm-3">
                                                            <div class="styled-input">
                                                                <input type="text" id="" name="" />
                                                                <label>Reference 1</label>
                                                                <span></span>
                                                            </div>
                                                        </div>

                                                        <div class="col-sm-3">
                                                            <div class="styled-input">
                                                                <select id="" name="" /></select>
                                                                <label>Reference 2</label>
                                                                <span></span></div>
                                                        </div>

                                                        <div class="col-sm-3">
                                                            <label>Certificate Awarded (If Any)</label>
                                                            <input type="file" id="" name="" class="form-control" />
                                                            <span></span>
                                                        </div>

                                                    </div>
                                                </div>
                                                <!--tab inside tab fields 10  -->

                                                <!--tab inside tab fields 11 -->
                                                <div>
                                                <div class="form-group">
			                                        <div class="col-sm-12">
			                                        <h4 ><b>Regional Development Authority Eligibility</b></h4>
			                                        <hr>
			                                        </div>
			                                        </div>
			                                        
                                                    <div class="form-group">
                                                        <div class="col-sm-3">
                                                            <input type="checkbox" id="" name="" />
                                                            <label>Not Applicable</label>
                                                            <span></span>
                                                        </div>
                                                    </div>

                                                    <div class="form-group">
                                                        <div class="col-sm-3">
                                                            <div class="styled-input">
                                                                <select id="" name="" required /></select>
                                                                <label>Type of Eligibility<span class="red">*</span></label>
                                                                <span></span>
                                                            </div>
                                                        </div>

                                                        <div class="col-sm-3">
                                                            <div class="styled-input">
                                                                <select id="" name="" required /></select>
                                                                <label>Developing Region<span class="red">*</span></label>
                                                                <span></span></div>
                                                        </div>

                                                        <div class="col-sm-3">
                                                            <div class="styled-input">
                                                                <select id="" name="" /></select>
                                                                <label>Pioneer Industry</label>
                                                                <span></span></div>
                                                        </div>

                                                        <div class="col-sm-3">
                                                            <div class="styled-input">
                                                                <input type="text" id="" name="" required />
                                                                <label>Issue Date of Eligibility<span class="red">*</span></label>
                                                                <span></span>
                                                            </div>
                                                        </div>

                                                    </div>

                                                    <div class="form-group">
                                                        <div class="col-sm-3">
                                                            <div class="styled-input">
                                                                <input type="text" id="" name="" required />
                                                                <label>Validity Date of Eligibility Certificate<span class="red">*</span></label>
                                                                <span></span>
                                                            </div>
                                                        </div>

                                                        <div class="col-sm-3">
                                                            <label>Attach Valid Eligibility Certificate<span class="red">*</span></label>
                                                            <input type="file" id="" name="" class="form-control" required />
                                                            <span></span>
                                                        </div>

                                                    </div>
                                                </div>
                                                <!--tab inside tab fields 11  -->

                                                <!-- tab inside tab fields 12  -->
                                                <div>
                                                <div class="form-group">
			                                        <div class="col-sm-12">
			                                        <h4 ><b>Other Eligibility Details</b></h4>
			                                        <hr>
			                                        </div>
			                                        </div>
			                                        
                                                    <div class="form-group">
                                                        <div class="col-sm-3">
                                                            <input type="checkbox" id="" name="" />
                                                            <label>Not Applicable for DGS&D Registration Eligibility</label>
                                                            <span></span>
                                                        </div>
                                                    </div>

                                                    <div class="form-group">

                                                        <div class="col-sm-3">
                                                            <div class="styled-input">
                                                                <select id="" name="" /></select>
                                                                <label>Select DGS&D Type</label>
                                                                <span></span>
                                                            </div>
                                                        </div>

                                                        <div class="col-sm-3">
                                                            <div class="styled-input">
                                                                <input type="text" id="" name="" required />
                                                                <label>DGS&D Registering Authority<span class="red">*</span></label>
                                                                <span></span>
                                                            </div>
                                                        </div>

                                                        <div class="col-sm-3">
                                                            <div class="styled-input">
                                                                <input type="text" id="" name="" required />
                                                                <label>DGS&D Registration Number<span class="red">*</span></label>
                                                                <span></span>
                                                            </div>
                                                        </div>

                                                        <div class="col-sm-3">
                                                            <div class="styled-input">
                                                                <input type="text" id="" name="" required />
                                                                <label>Issue Date Of DGS&D Registration<span class="red">*</span></label>
                                                                <span></span>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="form-group">
                                                        <div class="col-sm-3">
                                                            <input type="checkbox" id="" name="" />
                                                            <label>Not Applicable for DGTD Registration Eligibility</label>
                                                            <span></span>
                                                        </div>
                                                    </div>

                                                    <div class="form-group">

                                                        <div class="col-sm-3">
                                                            <div class="styled-input">
                                                                <input type="text" id="" name="" required />
                                                                <label>DGTD Registering Authority<span class="red">*</span></label>
                                                                <span></span>
                                                            </div>
                                                        </div>

                                                        <div class="col-sm-3">
                                                            <div class="styled-input">
                                                                <input type="text" id="" name="" required />
                                                                <label>DGTD Registration Number<span class="red">*</span></label>
                                                                <span></span>
                                                            </div>
                                                        </div>

                                                        <div class="col-sm-3">
                                                            <div class="styled-input">
                                                                <input type="text" id="" name="" required />
                                                                <label>Issue Date Of DGTD Registration<span class="red">*</span></label>
                                                                <span></span>
                                                            </div>
                                                        </div>

                                                        <div class="col-sm-3">
                                                            <div class="styled-input">
                                                                <input type="text" id="" name="" required />
                                                                <label>Validity Date Of DGTD Registration<span class="red">*</span></label>
                                                                <span></span>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                <!-- tab inside tab fields 12  -->

                                                <!-- tab inside tab fields 13  -->
                                                <div>
                                                <div class="form-group">
			                                        <div class="col-sm-12">
			                                        <h4 ><b>Bureau of Indian Standards</b></h4>
			                                        <hr>
			                                        </div>
			                                        </div>
			                                        
                                                    <div class="form-group">

                                                        <div class="col-sm-3">
                                                            <div class="styled-input">
                                                                <input type="text" id="" name="" title="Bureau of Indian Standards License Number" required />
                                                                <label>BIS License number<span class="red">*</span></label>
                                                                <span></span>
                                                            </div>
                                                        </div>

                                                        <div class="col-sm-3">
                                                            <div class="styled-input">
                                                                <input type="text" id="" name="" required />
                                                                <label>Issue Date of BIS License<span class="red">*</span></label>
                                                                <span></span>
                                                            </div>
                                                        </div>

                                                        <div class="col-sm-3">
                                                            <div class="styled-input">
                                                                <input type="text" id="" name="" required />
                                                                <label>Validity Date of BIS License<span class="red">*</span></label>
                                                                <span></span>
                                                            </div>
                                                        </div>

                                                        <div class="col-sm-3">
                                                            <label>Attach BIS License Certificate<span class="red">*</span></label>
                                                            <input type="file" id="" name="" class="form-control" required />
                                                            <span></span>
                                                        </div>

                                                    </div>

                                                    <div class="form-group">
                                                        <div class="col-sm-3">
                                                            <div class="styled-input">
                                                                <select id="" name="" required /></select>
                                                                <label>Items In BIS License<span class="red">*</span></label>
                                                                <span></span>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                <!-- tab inside tab fields 13  -->

                                                <!-- tab inside tab fields 14  -->
                                                <div>
                                                
                                                <div class="form-group">
			                                        <div class="col-sm-12">
			                                        <h4 ><b>Vendor Payment</b></h4>
			                                        <hr>
			                                        </div>
			                                        </div>
			                                        
                                                    <div class="form-group">

                                                        <div class="col-sm-3">
                                                            <div class="styled-input">
                                                                <select id="" name="" required /></select>
                                                                <label>Type of Payment<span class="red">*</span></label>
                                                                <span></span>
                                                            </div>
                                                        </div>

                                                        <div class="col-sm-3">
                                                            <label>Mode of Payment: <span class="red">*</span>
                                                                <input type="radio" name="DD" value="DD" checked> DD
                                                            </label>
                                                        </div>

                                                        <div class="col-sm-3">
                                                            <div class="styled-input">
                                                                <input type="text" id="" name="" required />
                                                                <label>DD Date<span class="red">*</span></label>
                                                                <span></span>
                                                            </div>
                                                        </div>

                                                        <div class="col-sm-3">
                                                            <div class="styled-input">
                                                                <input type="text" id="" name="" required />
                                                                <label>Demand Draft Number<span class="red">*</span></label>
                                                                <span></span>
                                                            </div>
                                                        </div>

                                                    </div>

                                                    <div class="form-group">
                                                        <div class="col-sm-3">
                                                            <div class="styled-input">
                                                                <input type="text" id="" name="" required />
                                                                <label>MICR Code<span class="red">*</span></label>
                                                                <span></span>
                                                            </div>
                                                        </div>

                                                        <div class="col-sm-3">
                                                            <div class="styled-input">
                                                                <input type="text" id="" name="" required />
                                                                <label>Amount in Rs.<span class="red">*</span></label>
                                                                <span></span>
                                                            </div>
                                                        </div>

                                                        <div class="col-sm-3">
                                                            <div class="styled-input">
                                                                <input type="text" id="" name="" required />
                                                                <label>GST Identification Number(GSTIN) <span class="red">*</span></label>
                                                                <span></span>
                                                            </div>
                                                        </div>

                                                        <div class="col-sm-3">
                                                            <div class="styled-input">
                                                                <input type="text" id="" name="" required />
                                                                <label>GST(@18% on Amount:SAC No.998599) in Rs. <span class="red">*</span></label>
                                                                <span></span>
                                                            </div>
                                                        </div>
                                                    </div>

                                                    <div class="form-group">
                                                        <div class="col-sm-3">
                                                            <div class="styled-input">
                                                                <input type="text" id="" name="" required />
                                                                <label>Total Amount including GST in Rs.<span class="red">*</span></label>
                                                                <span></span>
                                                            </div>
                                                        </div>

                                                        <div class="col-sm-3">
                                                            <div class="styled-input">
                                                                <input type="text" id="" name="" required />
                                                                <label>Bank Name<span class="red">*</span></label>
                                                                <span></span>
                                                            </div>
                                                        </div>

                                                        <div class="col-sm-3">
                                                            <div class="styled-input">
                                                                <input type="text" id="" name="" required />
                                                                <label>Branch Name <span class="red">*</span></label>
                                                                <span></span>
                                                            </div>
                                                        </div>

                                                    </div>
                                                </div>
                                                <!-- tab inside tab fields 14  -->

                                            </div>
                                            <!-- tab inside tab -->

                                        </div>
                                        <!--fields of field group 6  -->
                                        
                                         <!--fields of field group 7  -->
                                        <div>

                                            <!-- tab inside tab -->
                                            <div id="tabstrip2">
                                                <ul>
                                                    <li class="k-state-active">
                                                        P&L Account
                                                    </li>
                                                    <li>
                                                        BS Account
                                                    </li>
                                                    <li>
                                                        Other Documents Details
                                                    </li>
                                                    <li>
                                                        Turnover
                                                    </li>
                                                </ul>

                                                <!--tab inside tab fields 1  -->
                                                <div>
                                                <div class="form-group">
			                                        <div class="col-sm-12">
			                                        <h4 ><b>Profit and Loss Account</b></h4>
			                                        <hr>
			                                        </div>
			                                        </div>
			                                        
                                                    <div class="form-group">
                                                        <div class="col-sm-4">
                                                            <label>Attach Copy of Profit and loss Account for the year 2016-2017</label>
                                                            <input type="file" id="" name="" class="form-control" />
                                                            <span></span>
                                                        </div>

                                                        <div class="col-sm-4">
                                                            <label>Attach Copy of Profit and loss Account for the year 2015-2016</label>
                                                            <input type="file" id="" name="" class="form-control" />
                                                            <span></span>
                                                        </div>

                                                        <div class="col-sm-4">
                                                            <label>Attach Copy of Profit and loss Account for the year 2014-2015</label>
                                                            <input type="file" id="Pincode" name="Pincode" class="form-control" />
                                                            <span></span>
                                                        </div>
                                                    </div>
                                                </div>
                                                <!--tab inside tab fields 1  -->

                                                <!--tab inside tab fields 2  -->
                                                <div>
                                                <div class="form-group">
			                                        <div class="col-sm-12">
			                                        <h4 ><b>Balance Sheet Account</b></h4>
			                                        <hr>
			                                        </div>
			                                        </div>
                                                    <div class="form-group">
                                                        <div class="col-sm-4">
                                                            <label>Attach Copy of Balance sheet Account for the year 2016-2017</label>
                                                            <input type="file" id="" name="" class="form-control" />
                                                            <span></span>
                                                        </div>

                                                        <div class="col-sm-4">
                                                            <label>Attach Copy of Balance sheet Account for the year 2015-2016</label>
                                                            <input type="file" id="" name="" class="form-control" />
                                                            <span></span>
                                                        </div>

                                                        <div class="col-sm-4">
                                                            <label>Attach Copy of Balance sheet Account for the year 2014-2015</label>
                                                            <input type="file" id="" name="" class="form-control" />
                                                            <span></span>
                                                        </div>
                                                    </div>
                                                </div>
                                                <!--tab inside tab fields 2  -->

                                                <!--tab inside tab fields 3  -->
                                                <div>
                                                <div class="form-group">
			                                        <div class="col-sm-12">
			                                        <h4 ><b>Other Document Details</b></h4>
			                                        <hr>
			                                        </div>
			                                        </div>
                                                    <div class="form-group">
                                                        <div class="col-sm-3">
                                                            <div class="styled-input">
                                                                <input type="text" id="" name="" required />
                                                                <label>GST Identification Number(GSTIN)<span class="red">*</span></label>
                                                                <span></span>
                                                            </div>
                                                        </div>

                                                        <div class="col-sm-3">
                                                            <label>Attach Copy of GSTIN Registration Certificate<span class="red">*</span></label>
                                                            <input type="file" id="" name="" class="form-control" required />
                                                            <span></span>
                                                        </div>

                                                        <div class="col-sm-3">
                                                            <div class="styled-input">
                                                                <input type="text" id="" name="" required />
                                                                <label>Pan Number<span class="red">*</span></label>
                                                                <span></span>
                                                            </div>
                                                        </div>

                                                        <div class="col-sm-3">
                                                            <label>Attach Copy of Pan Card<span class="red">*</span></label>
                                                            <input type="file" id="" name="" class="form-control" required />
                                                            <span></span>
                                                        </div>
                                                    </div>
                                                </div>
                                                <!--tab inside tab fields 3  -->

                                                <!--tab inside tab fields 4  -->
                                                <div>
                                                <div class="form-group">
			                                        <div class="col-sm-12">
			                                        <h4 ><b>Turnover Details</b></h4>
			                                        <hr>
			                                        </div>
			                                        </div>
                                                    <div class="form-group">
                                                        <div class="col-sm-4">
                                                            <label>Turnover for year 2016-2017(in lakhs)</label>
                                                            <input type="file" id="" name="" class="form-control" />
                                                            <span></span>
                                                        </div>

                                                        <div class="col-sm-4">
                                                            <label>Turnover for year 2015-2016(in lakhs)</label>
                                                            <input type="file" id="" name="" class="form-control" />
                                                            <span></span>
                                                        </div>

                                                        <div class="col-sm-4">
                                                            <label>Turnover for year 2014-2015(in lakhs)</label>
                                                            <input type="file" id="" name="" class="form-control" />
                                                            <span></span>
                                                        </div>
                                                    </div>

                                                    <div class="form-group">
                                                        <div class="col-sm-4">
                                                            <label>Turnover for year 2013-2014(in lakhs)</label>
                                                            <input type="file" id="" name="" class="form-control" />
                                                            <span></span>
                                                        </div>

                                                        <div class="col-sm-4">
                                                            <label>Turnover for year 2012-2013(in lakhs)</label>
                                                            <input type="file" id="" name="" class="form-control" />
                                                            <span></span>
                                                        </div>
                                                    </div>
                                                </div>
                                                <!--tab inside tab fields 4  -->

                                            </div>
                                            <!-- tab inside tab -->

                                        </div>
                                        <!--fields of field group 7  -->

                                        <!--fields of field group 8  -->
                                        <div>
                                            <div class="form-group">
                                                <label><b>CONGRATULATIONS.....!  

                                          You have successfully filled all the details.</b>
                                                </label>
                                            </div>

                                            <div class="form-group">
                                                <button class="btn-all btn btn-info btnPrevious pull-left">Click Here</button>
                                            </div>

                                            <div class="form-group">
                                                <label>Do you want to apply for the registration now?</label>
                                                <input type="radio" name="" value="" checked> No
                                                <input type="radio" name="" value="" checked> Yes

                                            </div>
                                        </div>
                                        <!--fields of field group 8  -->

                                        <!--fields of field group 9  -->
                                        <div>
                                        
                                        <div class="form-group">
			                                        <div class="col-sm-12">
			                                        <h4 ><b>Details Of Manufacturer</b></h4>
			                                        <hr>
			                                        </div>
			                                        </div>
                                            <div class="form-group">

                                                <div class="col-sm-3">
                                                    <div class="styled-input">
                                                        <input type="text" id="" name="" required />
                                                        <label>Name of Manufacturer<span class="red">*</span></label>
                                                        <span></span>
                                                    </div>
                                                </div>

                                                <div class="col-sm-3">
                                                    <div class="styled-input">
                                                        <input type="text" id="" name="" required />
                                                        <label>Email Id<span class="red">*</span></label>
                                                        <span></span>
                                                    </div>
                                                </div>
                                            </div>

                                            <div class="clearfix"></div>

                                            <div class="form-group">
                                                <div class="col-sm-12">
                                                    <div class="styled-input">
                                                        <textarea type="text" id="address" name="address" required></textarea>
                                                        <label>Registered Office Address<span class="red">*</span></label>
                                                        <span></span>
                                                    </div>
                                                </div>
                                            </div>

                                            <div class="clearfix"></div>

                                            <div class="form-group">
                                                <div class="col-sm-3">
                                                    <div class="styled-input">
                                                        <input type="text" id="" name="" required />
                                                        <label>City<span class="red">*</span></label>
                                                        <span></span>
                                                    </div>
                                                </div>

                                                <div class="col-sm-3">
                                                    <div class="styled-input">
                                                        <select id="" name="" required /></select>
                                                        <label>District<span class="red">*</span></label>
                                                        <span></span>
                                                    </div>
                                                </div>

                                                <div class="col-sm-3">
                                                    <div class="styled-input">
                                                        <select id="" name="" required /></select>
                                                        <label>Country<span class="red">*</span></label>
                                                        <span></span>
                                                    </div>
                                                </div>

                                                <div class="col-sm-3">
                                                    <div class="styled-input">
                                                        <select id="" name="" required /></select>
                                                        <label>State<span class="red">*</span></label>
                                                    </div>
                                                </div>
                                            </div>

                                            <div class="clearfix"></div>

                                            <div class="form-group">
                                                <div class="col-sm-3">
                                                    <div class="styled-input">
                                                        <select id="" name="" required /></select>
                                                        <label>Pincode<span class="red">*</span></label>
                                                        <span></span>
                                                    </div>
                                                </div>

                                                <div class="col-sm-3">
                                                    <div class="styled-input">
                                                        <input type="text" id="" name="" required />
                                                        <label>Telephone 1(Enter 10 digit number)<span class="red">*</span></label>
                                                        <span></span>
                                                    </div>
                                                </div>

                                                <div class="col-sm-3">
                                                    <div class="styled-input">
                                                        <input type="text" id="" name="" />
                                                        <label>Telephone 2(Enter 10 digit number)</label>
                                                        <span></span>
                                                    </div>
                                                </div>

                                                <div class="col-sm-3">
                                                    <div class="styled-input">
                                                        <input type="text" id="" name="" />
                                                        <label>Fax 1(Enter 10 digit Number)</label>
                                                        <span></span>
                                                    </div>
                                                </div>

                                            </div>

                                            <div class="clearfix"></div>

                                            <div class="form-group">

                                                <div class="col-sm-3">
                                                    <div class="styled-input">
                                                        <input type="text" id="" name="" />
                                                        <label>Fax 2(Enter 10 digit number)</label>
                                                        <span></span>
                                                    </div>
                                                </div>

                                                <div class="col-sm-3">
                                                    <div class="styled-input">
                                                        <input type="text" id="" name="" />
                                                        <label>Mobile Number(Enter 10 digit number)</label>
                                                        <span></span>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <!--fields of field group 8  -->
                                        <!-- <div>
                        <div class="form-group">
                                                          <div class="col-sm-3">
													      <div class="styled-input">
													      <input type="text" id="" name="" required />
													      <label>Factory Name<span class="red">*</span></label>
													      <span></span>
													      </div></div>

													       <div class="col-sm-3">
													      <div class="styled-input">
													      <input type="text" id="" name=""  />
													      <label>Established date of Factory</label>
													      <span></span>
													      </div></div>

													       <div class="col-sm-3">
													      <div class="styled-input">
													      <input type="text" id="" name=""  />
													      <label>Manpower</label>
													      <span></span>
													      </div></div>

													       <div class="col-sm-3">
													      <div class="styled-input">
													      <input type="text" id="" name=""  />
													      <label>Factory Inspection Report no.</label>
													      <span></span>
													      </div></div>
                                        		  </div>

                                        		  <div class="form-group">
                                        		          <div class="col-sm-12">
                                                          <div class="styled-input">
													      <textarea type="text" id="address" name="address" required ></textarea>
													      <label>Full Address of Factory<span class="red">*</span></label>
													      <span></span></div></div> 
                                        		  </div>

                                        		    <div class="form-group">
                                                        <div class="col-sm-3">
						                               	  <div class="styled-input">
						                                  <input type="text" id="" name="" required />
													      <label>City<span class="red">*</span></label>
													      <span></span> 
													      </div></div>

													      <div class="col-sm-3">
						                               	  <div class="styled-input">
						                                  <select  id="" name="" required /></select>
													      <label>District<span class="red">*</span></label>
													      <span></span> 
													      </div></div>

													      <div class="col-sm-3">
						                               	  <div class="styled-input">
						                                  <select  id="" name="" required /></select>
													      <label>Country<span class="red">*</span></label>
													      <span></span> 
													      </div></div>

													      <div class="col-sm-3">
						                               	  <div class="styled-input">
						                                  <select  id="" name="" required /></select>
													      <label>State<span class="red">*</span></label>
													      <span></span> 
													      </div></div>

                                                        </div>

                                                        <div class="form-group">
                                                        <div class="col-sm-3">
						                               	  <div class="styled-input">
						                                  <select  id="" name="" required /></select>
													      <label>Pincode<span class="red">*</span></label>
													      <span></span> 
													      </div></div>
                                                        </div>
                    </div> -->
                                        <!-- <div>
                        <div class="form-group">
                                                          <div class="col-sm-3">
													      <div class="styled-input">
													      <input type="text" id="" name="" required />
													      <label>Factory License Number<span class="red">*</span></label>
													      <span></span>
													      </div></div>

													       <div class="col-sm-3">
													      <div class="styled-input">
													      <input type="text" id="" name="" required />
													      <label>Factory License Validity Date<span class="red">*</span></label>
													      <span></span>
													      </div></div>

													      <div class="col-sm-3">
						                               	  <label>Attach Factory License Copy<span class="red">*</span></label>
						                               	  <input type="file" id="" name="" class="form-control"  required />
													      <span></span> 
													      </div>

													      <div class="col-sm-3">
						                               	  <label>Attach Machinery List Copy of Factory<span class="red">*</span></label>
						                               	  <input type="file" id="" name="" class="form-control"  required />
													      <span></span> 
													      </div>
												</div>

												<div class="form-group">
												<div class="col-sm-3">
						                               	  <label>Attach Testing Equipments List<span class="red">*</span></label>
						                               	  <input type="file" id="" name="" class="form-control"  required />
													      <span></span> 
													      </div>
												</div>
                    </div>
                     <div>
                        <p>Ut orci ligula, varius ac consequat in, rhoncus in dolor. Mauris pulvinar molestie accumsan. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae.</p>
                    </div> -->
                                        <!-- <div>
                        <div class="form-group">
						                                  <div class="col-sm-3">
						                               	  <div class="styled-input">
						                                  <select  id="" name="" required /></select>
													      <label>Item Name<span class="red">*</span></label>
													      <span></span> 
													      </div></div>

													      <div class="col-sm-3">
													      <div class="styled-input">
													      <select  id="" name="" required /></select>
													      <label>Name of Firm<span class="red">*</span></label>
													      <span></span></div> </div>

													     <div class="col-sm-3">
													      <div class="styled-input">
													      <input type="text" id="" name="" required />
													      <label>Order Start Date<span class="red">*</span></label>
													      <span></span></div> </div>

													      <div class="col-sm-3">
													      <div class="styled-input">
													      <select  id="" name="" required /></select>
													      <label>Order Completion Date<span class="red">*</span></label>
													      <span></span></div> </div>
													   </div>

										<div class="form-group">
						                                  <div class="col-sm-3">
													      <div class="styled-input">
													      <input type="text" id="" name="" required />
													      <label>Quantity Supplied<span class="red">*</span></label>
													      <span></span></div> </div>

						                                  <div class="col-sm-3">
						                               	  <div class="styled-input">
						                               	  <input type="text" id="" name=""  />
						                                  <label>Reference 1</label>
													      <span></span> 
													      </div></div>

													      <div class="col-sm-3">
													      <div class="styled-input">
													      <select  id="" name=""  /></select>
													      <label>Reference 2</label>
													      <span></span></div> </div>

													      <div class="col-sm-3">
						                               	  <label>Certificate Awarded (If Any)</label>
						                               	  <input type="file" id="" name="" class="form-control"   />
													      <span></span> 
													      </div>

											</div>
                    </div>                  
                    <div>
                        <div class="form-group">

                                         				  <div class="col-sm-3">
						                               	  <div class="styled-input">
						                               	  <select  id="" name="" required /></select>
						                                  <label>Type of Payment<span class="red">*</span></label>
													      <span></span> 
													      </div></div>

													      <div class="col-sm-3">
                                                          <label>Mode of Payment: <span class="red">*</span>
													      <input type="radio" name="DD" value="DD" checked> DD
 														  </label> 
													      </div>

													      <div class="col-sm-3">
						                               	  <div class="styled-input">
						                               	  <input type="text" id="" name="" required />
						                                  <label>DD Date<span class="red">*</span></label>
													      <span></span> 
													      </div></div>

													      <div class="col-sm-3">
						                               	  <div class="styled-input">
						                               	  <input type="text" id="" name="" required />
						                                  <label>Demand Draft Number<span class="red">*</span></label>
													      <span></span> 
													      </div></div>

													      </div>

											<div class="form-group">
													      <div class="col-sm-3">
						                               	  <div class="styled-input">
						                               	  <input type="text" id="" name="" required />
						                                  <label>MICR Code<span class="red">*</span></label>
													      <span></span> 
													      </div></div>

													      <div class="col-sm-3">
						                               	  <div class="styled-input">
						                               	  <input type="text" id="" name="" required />
						                                  <label>Amount in Rs.<span class="red">*</span></label>
													      <span></span> 
													      </div></div>

													      <div class="col-sm-3">
						                               	  <div class="styled-input">
						                               	  <input type="text" id="" name="" required />
						                                  <label>GST Identification Number(GSTIN) <span class="red">*</span></label>
													      <span></span> 
													      </div></div>

													      <div class="col-sm-3">
						                               	  <div class="styled-input">
						                               	  <input type="text" id="" name="" required />
						                                  <label>GST(@18% on Amount:SAC No.998599) in Rs. <span class="red">*</span></label>
													      <span></span> 
													      </div></div>
											 </div>

											 <div class="form-group">
													      <div class="col-sm-3">
						                               	  <div class="styled-input">
						                               	  <input type="text" id="" name="" required />
						                                  <label>Total Amount including GST in Rs.<span class="red">*</span></label>
													      <span></span> 
													      </div></div>

													      <div class="col-sm-3">
						                               	  <div class="styled-input">
						                               	  <input type="text" id="" name="" required />
						                                  <label>Bank Name<span class="red">*</span></label>
													      <span></span> 
													      </div></div>

													      <div class="col-sm-3">
						                               	  <div class="styled-input">
						                               	  <input type="text" id="" name="" required />
						                                  <label>Branch Name <span class="red">*</span></label>
													      <span></span> 
													      </div></div>

											 </div>
                    </div> -->
                                        <!-- <div>
                                <div class="form-group">
                                          <label><b>CONGRATULATIONS.....!  

                                          You have successfully filled all the details.</b>
                                          </label></div>

                                          <div class="form-group">
                                          <button class="btn-all btn btn-info btnPrevious pull-left">Click Here</button>
                                          </div>

                                          <div class="form-group">
                                          <label>Do you want to apply for the registration now?</label>
                                           <input type="radio" name="" value="" checked> No
                                           <input type="radio" name="" value="" checked> Yes

                                           </div>
                    </div> -->

                                    </div>
                                    <div class="col-sm-12 text-center positionABS" style="margin-bottom: 10px;">
                                        <button class="btn kendo-btn pull-left">Previous</button>
                                        <button class="btn kendo-btn">Save</button>
                                        <button class="btn kendo-btn pull-right">Next</button>
                                    </div>

                                </div>

                            </div>   
                        </div>
                    </div>
                </div>     
            </div>
        </div>


        <footer class="footer">
            <div class="container-fluid">
                <nav class="pull-left">
                    <ul>
                        <li>
                            <a href="#">
                                Home
                            </a>
                        </li>
                        <li>
                            <a href="#">
                                Company
                            </a>
                        </li>
                        <li>
                            <a href="#">
                                Portfolio
                            </a>
                        </li>
                        <li>
                            <a href="#">
                               Blog
                            </a>
                        </li>
                    </ul>
                </nav>
                <p class="copyright pull-right">
                    © 2017 MSECDL. All Rights Reserved.
                </p>
            </div>
        </footer>

    </div>
</div>


</body>


	<script type="text/javascript">
	 $(document).ready(function() {
		 $("#tabstrip").kendoTabStrip();
         $("#tabstrip2").kendoTabStrip();
         $("#tabstrip3").kendoTabStrip();
         $("#tabstrip4").kendoTabStrip();
         $("#tabstrip5").kendoTabStrip();
         $("#tabstrip6").kendoTabStrip();
		 /* $('.sidebar').toggleClass("hidebar");
         $(".main-panel").toggleClass("fullpanel");
         $(".navbar-fixed").toggleClass("fullnavbar");
         $(".fa-navicon").toggleClass("fa-ellipsis-v");
          */
        /* $(".slidetoggle").click(function(){
        	 $('.sidebar').toggleClass("hidebar");
             $(".main-panel").toggleClass("fullpanel");
             $(".navbar-fixed").toggleClass("fullnavbar");
             $(".fa-navicon").toggleClass("fa-ellipsis-v");
             
         }); */
         $(".slidetoggle").click(function(){
     		$('.sidebar').toggleClass("hidebar");
             $(".main-panel").toggleClass("fullpanel");
             $(".navbar-fixed").toggleClass("fullnavbar");
             $(".fa-navicon").toggleClass("fa-ellipsis-v");
             
 	});
     });
    	function valueChanged() {
        	debugger;
            if ($('.Materialvender').is(":checked"))
                $(".Subvender").show();
            else
                $(".Subvender").hide();
        }
    	$(window).on('load', function(){
    		$('#loading-wrapper').fadeOut("slow");
    	});
	</script>
	

</html>
