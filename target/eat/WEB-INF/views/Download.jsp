<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
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
    <link rel="stylesheet" href="<%=request.getContextPath()%>/resource/css/bootstrap.css?appVer=${appVer}">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/resource/css/jquery.dataTables.min.css?appVer=${appVer}">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/resource/css/style.css?appVer=${appVer}">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/resource/css/dataTables.bootstrap.min.css?appVer=${appVer}">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/resource/css/TableResponsive.css?appVer=${appVer}">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/resource/css/Responsive.css?appVer=${appVer}">
</head>

<body>
<nav class="navbar navbar-inverse navbar-fixed-top">
        <div class="container-fluid">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="#"><img alt="" src="<%=request.getContextPath()%>/resource/images/Mahadiscom_Logo.png?appVer=${appVer}"></a>
                <a href="javascript:void(0)" class="LoginLink LoginLinkMobile"><span class="glyphicon glyphicon-lock"></span> Login</a>
            </div>
            <div class="collapse navbar-collapse" id="myNavbar">
                <ul class="nav navbar-nav  main-nav">
                    <li class="HomeLink"><a href="home">Home</a></li>
                    <li><a href="tenders">Tenders</a></li>
                    <li><a href="bids">Bids</a></li>
                    <li><a href="profile">Profile</a></li>
                    <li><a href="help">Help</a></li>
                    <li><a href="contactus">Contact Us</a></li>
                    <li><a href="sitemap">Site Map</a></li>
                </ul>
                <ul id="SmallNav" class="nav navbar-nav navbar-right sidenav">
                    <li><a href="disclaimer"><span class="glyphicon glyphicon-exclamation-sign"></span>Disclaimer</a></li>
                    <li><a href="#"><span class="glyphicon glyphicon-th-list"></span>Related Links</a></li>
                    <li><a href="#"><span class=" glyphicon glyphicon-credit-card"></span>Payments</a></li>
                   <li><a href="#"><span class=" glyphicon glyphicon-cog"></span>Utilities</a></li>
                    <li><a href="#"><span class="glyphicon glyphicon-calendar"></span>Calendar</a></li>
                    <li class="active"><a href="download"><span class="glyphicon glyphicon-download-alt"></span>Downloads</a></li>
                    <li><a href="#"><span class="glyphicon glyphicon-list-alt"></span> Public Notices</a></li>
                    <li><a href="Notification"><span class="glyphicon glyphicon-bell"></span> Notification</a></li>
                    <li><a href="registration"><span class="glyphicon glyphicon-registration-mark"></span> Registration</a></li>
                    <li><a href="javascript:void(0)" class="LoginLink"><span class="glyphicon glyphicon-lock"></span> Login</a></li>
                </ul>
                <div class="welcome"> <span class="glyphicon glyphicon-user" style="color:gray"></span> Welcome TestContract |<a href="">Logout</a></div>
            </div>
        </div>
    </nav>>
    <div id="content-wrapper">
        <!-- PAGE CONTENT -->
        <div class="container-fluid" style="margin-bottom:20px;">
            <div class="clearfix"></div>
            <div class="col-sm-12 mobileNoPadding">
                <div class="card padding card-min-height card-min-height">
                    <div class="col-sm-12 mobileNoPadding">
                        <h4>User Guide</h4>
                        <div class="single category">
                            <ul class="list-unstyled">
                               <li><a class="col-sm-10">Adobe Reader</a>
                                <a  class="col-sm-2">Download</a>
                                </li>
                                <div class="clearfix"></div>
                               <li><a  class="col-sm-10">Secure-Sign Software</a>
                                <a  class="col-sm-2">Download</a>
                                </li>
                                <div class="clearfix"></div>
                                <div class="clearfix"></div>
                               <li><a  class="col-sm-10">MS Office 2007 to MS Office 2003 file Converter </a>
                                <a  class="col-sm-2">Download</a>
                                </li>
                                <div class="clearfix"></div>
                               <li><a  class="col-sm-10">Primo PDFSetup.exe </a>
                                <a  class="col-sm-2">Download</a>
                                </li>
                                <div class="clearfix"></div>
                               <li><a  class="col-sm-10">Adobe Zip Security for 7 and 8 reader.zip</a>
                                <a  class="col-sm-2">Download</a>
                                </li>
                                <div class="clearfix"></div>
                               <li><a  class="col-sm-10">Public Notice-Vendor Registration Process</a>
                                <a  class="col-sm-2">Download</a>
                                </li>
                                <div class="clearfix"></div>
                               <li><a  class="col-sm-10">Request Format for Change in Digital Signature Holder Details</a>
                                <a  class="col-sm-2">Download</a>
                                </li>
                                <div class="clearfix"></div>
                               <li><a  class="col-sm-10">Receipt Voucher for Payment of Registration Fees.</a>
                                <a  class="col-sm-2">Download</a>
                                </li>
                                <div class="clearfix"></div>
                            </ul>
                        </div>
                    </div>
                </div>
                <!-- End Container -->
            </div>
        </div>
    </div>
    <!-- PAGE CONTENT -->
     <div class="height20"></div>
    <div class="footer">
        © 2017 E-Auctionapp. All Rights Reserved.
    </div>
</body>

</html>