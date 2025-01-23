<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
       <!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>E-Auctionapp e-Tendering</title>
    <meta name="description" content="Fullscreen Pageflip Layout with BookBlock" />
    <meta name="keywords" content="fullscreen pageflip, booklet, layout, bookblock, jquery plugin, flipboard layout, sidebar menu" />
    <meta name="author" content="Codrops" />
    <link rel="stylesheet" href="<%=request.getContextPath()%>/resource/Landing/css/bootstrap.css?appVer=${appVer}">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/resource/Landing/css/style.css?appVer=${appVer}">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/resource/Landing/css/homeSlider.css?appVer=${appVer}">
    <link href="<%=request.getContextPath()%>/resource/Landing/css/login.css?appVer=${appVer}" rel="stylesheet" media="all">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/resource/Landing/css/Responsive.css?appVer=${appVer}">
    <link href="<%=request.getContextPath()%>/resource/Landing/css/animate.min.css?appVer=${appVer}" rel="stylesheet" media="all">
    <style>
 
    </style>
</head>

<body>
<section id="Full-page">
    <nav class="navbar navbar-inverse navbar-fixed-top">
        <div class="container-fluid nopadding">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="#"><img alt="" src="<%=request.getContextPath()%>/resource/Landing/images/Mahadiscom_Logo.jpg?appVer=${appVer}"></a>
                <!-- <a href="javascript:void(0)" class="LoginLink LoginLinkMobile"><span class="glyphicon glyphicon-lock"></span> Login</a> -->
            </div>
            <div class="collapse navbar-collapse" id="myNavbar">
             <ul id="SmallNav" class="nav navbar-nav navbar-right sidenav">
                    <li><a href="Disclaimer.html"><span class="glyphicon glyphicon-exclamation-sign"></span>Disclaimer</a></li>
                    <li><a href="#"><span class="glyphicon glyphicon-calendar"></span>Calendar</a></li>
                    <li><a href="Notification.html"><span class="glyphicon glyphicon-bell"></span> Notification</a></li>                    
                    <li><a href="ContactUs.html"><span class="glyphicon glyphicon-earphone"></span>Contact Us</a></li>
                    <li><a href="SiteMap.html"><span class="glyphicon glyphicon-map-marker"></span>Site Map</a></li>
                    <li><a href="SiteMap.html"><span class="glyphicon glyphicon-question-sign"></span>Help</a></li>
                    <li><a href="SiteMap.html"><span class="glyphicon glyphicon-list-alt"></span>FAQ</a></li>
                    <li><a href="SiteMap.html"><span class="glyphicon glyphicon-cog"></span>Terms & Condition</a></li>                     
                </ul>
                <div class="clearfix"></div> 
                <form class="navbar-form pull-right">
                    <div class="input-group">
                        <input type="search" class="form-control" placeholder="Search">
                        <span class="input-group-btn">
                            <button type="submit" class="btn btn-default"><span class="glyphicon glyphicon-search"></span></button>
                        </span>
                    </div>                                       
                </form>
                <div class="clearfix"></div> 
                <ul class="nav navbar-nav  main-nav">
                    <li  class="active"><a href="Home.html">Home</a></li>
                    <li class="dropdown">
         			 <a href="#" class="dropdown-toggle" data-toggle="dropdown">Tenders <b class="caret"></b></a>
        			  <ul class="dropdown-menu">
           				 <li><a href="LatestAnnouncements.html">Latest Announcements</a></li>
           				 <li><a href="LatestTendersWorks.html">Latest Tenders Work</a></li>
           				 <li><a href="LatestTendersProcurements.html">Latest Tenders Procurements</a></li>
           				 <li><a href="ForwardAuction.html">Forward Auction</a></li>
           				 <li><a href="ReverseAuction.html">Reverse Auction</a></li>
         			 </ul>
       				 </li>
                    <li class="dropdown">
         			 <a href="#" class="dropdown-toggle" data-toggle="dropdown">Vendors <b class="caret"></b></a>
        			  <ul class="dropdown-menu">
           				 <li><a href="BlackListedVendor.html">Blacklisted Vendors</a></li>
           				 <li><a href="VendorListforPublic.html">Vendor List for Public</a></li>
         			 </ul>
       				 </li>
                    <li><a href="Download.html">Download</a></li>
                    <li><a href="PublicNotices.html"> Public Notices</a></li>
                </ul>
               
            </div>
        </div>
    </nav>
    <section class="homeSection">
        <div class="container-fluid nopadding">
            <!-- Add this css File in head tag-->
            <div id="first-slider">
                <div id="carousel-example-generic" class="carousel slide carousel-fade">
                    <!-- data-interval="false" -->
                    <!-- Indicators -->
                    <ol class="carousel-indicators">
                        <li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>
                        <li data-target="#carousel-example-generic" data-slide-to="1"></li>
                        <li data-target="#carousel-example-generic" data-slide-to="2"></li>
                        <!--<li data-target="#carousel-example-generic" data-slide-to="3"></li>
                         <li data-target="#carousel-example-generic" data-slide-to="4"></li> -->
                    </ol>
                    <!-- Wrapper for slides -->

                    <div class="carousel-inner" role="listbox">
                        <!-- Item 1 -->
                        <div class="item active slide1">
                            <div class="row">

                            </div>
                        </div>
                        <div class="item slide2">
                            <div class="row">

                            </div>
                        </div>
                        <div class="item slide3">
                            <div class="row">

                            </div>
                        </div>

                    </div>

                    <div class="container-fluid NoPadding overpos">
                                    <div class="col-md-8 text-left nopadding">
                                   <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 bhoechie-tab-container nopadding">
            <div class="col-lg-2 col-md-2 col-sm-2 col-xs-2 bhoechie-tab-menu">
              <div class="list-group">
              	<a href="#" class="list-group-item text-center">
                  <h4 class="glyphicon glyphicon-list-alt"></h4><br/>Latest Announcements
                </a>
                <a href="#" class="list-group-item text-center">
                  <h4 class="glyphicon glyphicon-book"></h4><br/>Tenders Work
                </a>
                <a href="#" class="list-group-item text-center">
                  <h4 class="glyphicon glyphicon-globe"></h4><br/>Tenders Procurements
                </a>
                <a href="#" class="list-group-item text-center">
                  <h4 class="glyphicon glyphicon-forward"></h4><br/>Forward Auction
                </a>
                <a href="#" class="list-group-item text-center">
                  <h4 class="glyphicon glyphicon-backward"></h4><br/>Reverse Auction
                </a>                
              </div>
            </div>
            <div class="col-lg-10 col-md-10 col-sm-10 col-xs-10 bhoechie-tab">
                <!-- flight section -->
                <div class="bhoechie-tab-content">
                   <br><a class="RemoveTab"><span class="glyphicon glyphicon-remove pull-right"></span></a>
                      <h3 class="text-center" style="margin-top: 0;color:#d92605;">Latest Announcements</h3>
                   
                    <br>
                    <table class="table table-bordered">
    <thead>
      <tr>
        <th width="56%">Title</th>
        <th width="22%">Sale Closing</th>
        <th width="22%">Submission Due</th>
      </tr>
    </thead>
    <tbody>
      <tr>
        <td><a>MSED Co. Ltd.</a></td>
        <td>21/11/2017</td>
        <td>21/11/2017</td>
      </tr><tr>
        <td><a>MSED Co. Ltd.</a></td>
        <td>21/11/2017</td>
        <td>21/11/2017</td>
      </tr>
      <tr>
        <td><a>MSED Co. Ltd.</a></td>
        <td>21/11/2017</td>
        <td>21/11/2017</td>
      </tr>
      <tr>
        <td><a>MSED Co. Ltd.</a></td>
        <td>21/11/2017</td>
        <td>21/11/2017</td>
      </tr>
      <tr>
        <td><a>MSED Co. Ltd.</a></td>
        <td>21/11/2017</td>
        <td>21/11/2017</td>
      </tr>
    </tbody>
  </table>
                </div>
                <div class="bhoechie-tab-content">
                <br><a class="RemoveTab"><span class="glyphicon glyphicon-remove pull-right"></span></a>
                      <h3 class="text-center" style="margin-top: 0;color:#d92605;">Latest Tenders Work</h3>
                   
                   <br>
                    <table class="table table-bordered">
    <thead>
      <tr>
        <th width="56%">Title</th>
        <th width="22%">Sale Closing</th>
        <th width="22%">Submission Due</th>
      </tr>
    </thead>
    <tbody>
      <tr>
        <td><a>E-Auctionapp Co. Ltd.</a></td>
        <td>21/11/2017</td>
        <td>21/11/2017</td>
      </tr><tr>
        <td><a>MSED Co. Ltd.</a></td>
        <td>21/11/2017</td>
        <td>21/11/2017</td>
      </tr>
      <tr>
        <td><a>E-Auctionapp Co. Ltd.</a></td>
        <td>21/11/2017</td>
        <td>21/11/2017</td>
      </tr>
      <tr>
        <td><a>MSED Co. Ltd.</a></td>
        <td>21/11/2017</td>
        <td>21/11/2017</td>
      </tr>
      <tr>
        <td><a>MSED Co. Ltd.</a></td>
        <td>21/11/2017</td>
        <td>21/11/2017</td>
      </tr>
    </tbody>
  </table>
                </div>
                <!-- train section -->
                <div class="bhoechie-tab-content">
                    <br><a class="RemoveTab"><span class="glyphicon glyphicon-remove pull-right"></span></a>
                      <h3 class="text-center" style="margin-top: 0;color:#d92605;">Latest Tenders Procurements</h3>
                   
                    <br>
                    <table class="table table-bordered">
    <thead>
      <tr>
        <th width="56%">Title</th>
        <th width="22%">Sale Closing</th>
        <th width="22%">Submission Due</th>
      </tr>
    </thead>
    <tbody>
      <tr>
        <td><a>MSED Co. Ltd.</a></td>
        <td>21/11/2017</td>
        <td>21/11/2017</td>
      </tr><tr>
        <td><a>MSED Co. Ltd.</a></td>
        <td>21/11/2017</td>
        <td>21/11/2017</td>
      </tr>
      <tr>
        <td><a>MSED Co. Ltd.</a></td>
        <td>21/11/2017</td>
        <td>21/11/2017</td>
      </tr>
      <tr>
        <td><a>MSED Co. Ltd.</a></td>
        <td>21/11/2017</td>
        <td>21/11/2017</td>
      </tr>
      <tr>
        <td><a>MSED Co. Ltd.</a></td>
        <td>21/11/2017</td>
        <td>21/11/2017</td>
      </tr>
    </tbody>
  </table>
                </div>
    
                <!-- hotel search -->
                <div class="bhoechie-tab-content">
                    <br><a class="RemoveTab"><span class="glyphicon glyphicon-remove pull-right"></span></a>
                      <h3 class="text-center" style="margin-top: 0;color:#d92605;">Forward Auction</h3>
                   
                    <br>
                    <table class="table table-bordered">
    <thead>
      <tr>
        <th width="56%">Title</th>
        <th width="22%">Sale Closing</th>
        <th width="22%">Submission Due</th>
      </tr>
    </thead>
    <tbody>
      <tr>
        <td><a>MSED Co. Ltd.</a></td>
        <td>21/11/2017</td>
        <td>21/11/2017</td>
      </tr><tr>
        <td><a>MSED Co. Ltd.</a></td>
        <td>21/11/2017</td>
        <td>21/11/2017</td>
      </tr>
      <tr>
        <td><a>MSED Co. Ltd.</a></td>
        <td>21/11/2017</td>
        <td>21/11/2017</td>
      </tr>
      <tr>
        <td><a>MSED Co. Ltd.</a></td>
        <td>21/11/2017</td>
        <td>21/11/2017</td>
      </tr>
      <tr>
        <td><a>MSED Co. Ltd.</a></td>
        <td>21/11/2017</td>
        <td>21/11/2017</td>
      </tr>
    </tbody>
  </table>
                </div>
                <div class="bhoechie-tab-content">
                    <br><a class="RemoveTab"><span class="glyphicon glyphicon-remove pull-right"></span></a>
                      <h3 class="text-center" style="margin-top: 0;color:#d92605;">Reverse Auction</h3>
                   
                    <br>
                    <table class="table table-bordered">
    <thead>
      <tr>
        <th width="56%">Title</th>
        <th width="22%">Sale Closing</th>
        <th width="22%">Submission Due</th>
      </tr>
    </thead>
    <tbody>
      <tr>
        <td><a>E-Auctionapp Co. Ltd.</a></td>
        <td>21/11/2017</td>
        <td>21/11/2017</td>
      </tr><tr>
        <td><a>E-Auctionapp Co. Ltd.</a></td>
        <td>21/11/2017</td>
        <td>21/11/2017</td>
      </tr>
      <tr>
        <td><a>MSED Co. Ltd.</a></td>
        <td>21/11/2017</td>
        <td>21/11/2017</td>
      </tr>
      <tr>
        <td><a>MSED Co. Ltd.</a></td>
        <td>21/11/2017</td>
        <td>21/11/2017</td>
      </tr>
      <tr>
        <td><a>E-Auctionapp Co. Ltd.</a></td>
        <td>21/11/2017</td>
        <td>21/11/2017</td>
      </tr>
    </tbody>
  </table>
                </div>
                
            </div>
        </div>
					 </div>
					 <div class="col-sm-4">
                <div class="col-sm-11 LOGINSEC">
                    <div class="card padding20">
                        <div class="text-center">
                            <h3 class="loginheading"><b>Log In</b></h3></div>
                        <form id="ajax-login-form" action="login" method="post" role="form" autocomplete="off">
                            <div class="form-group">
                            <span id="errorMsg">
                            <c:if test="${not empty errorMsg}">
							   <c:out value="${errorMsg}"/>
							</c:if></span>
                           </div> 
                               <div class="styled-input">
							      <input type="text" id="username" name="username" required />
							      <label>Email</label>
							      <span></span> </div>

                            
                               <div class="styled-input">
							      <input type="password" id="password" name="password" required />
							      <label>Password</label>
							      <span></span> </div>
                             
                              <!-- captcha code  -->
                             <br>
               					 <div class="col-sm-12">
                    				  <div class="row form-group  formgroup">
                                      <div class="capbox">
									  <div id="CaptchaDiv"></div>
										<div class="capbox-inner">
                                        <input type="hidden" id="txtCaptcha">
                                        <div class="form-group">
			                               <div class="styled-input">
										      <input type="text" id="CaptchaInput" name="CaptchaInput" required />
										      <label>Type the above Text</label>
										      <span></span> </div>
			                             </div>
                                        </div>
									</div>
									</div>
									</div>
                             <!-- captcha code -->
                                <br>
                                <div class="row">
                                    <div class="col-xs-12">
                                        <input type="submit" name="login-submit" id="login-submit" tabindex="4" class="form-control btn btn-info" value="Log In">
                                    </div>
                                </div>
                                
                                <div class="clearfix"></div>
                            <div class="form-group">
                                <div class="row">
                                    <div class="col-xs-6">
                                        <a href="registration.html" class="loginBtn">New User ?</a>
                                        </div>
                                         <div class="col-xs-6">
                                        <a href="" class="forgot-password">Forgot Password?</a>
                                    </div>
                                </div>
                            </div>
                            <input type="hidden" class="hide" name="token" id="token" value="a465a2791ae0bae853cf4bf485dbe1b6">
                        </form>
                    </div>
                </div>
            </div>
                                    
                    </div>
                </div>

            </div>

            <div class="clearfix"></div>
            <div class="container-fluid">
                <!-- <a href="" class="vlink pull-left">Black listed vendors &gt;&gt;</a> -->
                <h3 class="headingBig">The E-Auctionapp e-Tendering Network</h3>
                <h5 class="headingSmall">Get the most complete, most widely adopted strategic sourcing solutions</h5>
                <hr class="Divider">
                <div class="form-group">
                    <div class="col-sm-4">
                        <div class="Homecard text-center padding20 margin15">
                            <span class="glyphicon glyphicon-calendar Homeicon"></span>
                            <h4>FAQ</h4>
                            <p>Frequently asked questions.</p>
                        </div>
                    </div>
                    <div class="col-sm-4">
                        <div class="Homecard text-center padding20 margin15">
                            <span class="glyphicon glyphicon-user Homeicon"></span>
                            <h4> Tender For Contracts</h4>
                            <p>Contractors can get contractors through tender process.</p>
                        </div>
                    </div>
                    <div class="col-sm-4">
                        <div class="Homecard text-center padding20 margin15">
                            <span class="glyphicon glyphicon-dashboard Homeicon"></span>
                            <h4>Tender For Procurement</h4>
                            <p>Vendors can sell material though tender process.</p>
                        </div>
                    </div>
                </div>
                <div class="clearfix"></div>
                <div class="form-group">
                    <div class="col-sm-4">
                        <div class="Homecard text-center padding20 margin15">
                            <span class="glyphicon glyphicon-retweet Homeicon"></span>
                            <h4>E-Auction Forward</h4>
                            <p>Customer can purchase material through open bidding process.</p>
                        </div>
                    </div>
                    <div class="col-sm-4">
                        <div class="Homecard text-center padding20 margin15">
                            <span class="glyphicon glyphicon-globe Homeicon"></span>
                            <h4>E-Auction Reverse</h4>
                            <p>Vendors can sell material through open bidding process.</p>
                        </div>
                    </div>
                    <div class="col-sm-4">
                        <div class="Homecard text-center padding20 margin15">
                            <span class="glyphicon glyphicon-time Homeicon"></span>
                            <h4>Registration</h4>
                            <p>Registration guides for the Contractors, Vendors and Customers</p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <div class="footer">
       <div class="col-sm-6">
           <p>Copyright © E-Auctionapp All rights reserved.</p>
           <p>Website Owned By : E-Auctionapp</p>
           <p>Maintained By : NovelERP Solution Pvt. Ltd.</p>
        </div> 
        <div class="col-sm-6">
			<p>Phone : +91-22-22619100</p>
			<p>Email : helpdesk_pg@mahadiscom.in</p>
			<p>Address : Hongkong Bank Building, M.G. Road, Fort, Mumbai-400001.</p>
		</div>        
        <div class="clearfix"></div>
    </div>
    </section>
</body> 

<script src="<%=request.getContextPath()%>/resource/Landing/js/jquery-3.2.1.min.js?appVer=${appVer}"></script>
<script src="<%=request.getContextPath()%>/resource/Landing/js/bootstrap.min.js?appVer=${appVer}"></script>
<script src="<%=request.getContextPath()%>/resource/Landing/js/homeSlider.js?appVer=${appVer}"></script>
<script src="<%=request.getContextPath()%>/resource/Landing/js/Home.js?appVer=${appVer}"></script>
<script src="<%=request.getContextPath()%>/resource/Landing/js/header.js?appVer=${appVer}"></script>
</html>