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
                <a class="navbar-brand" href="#"><img alt="" src="<%=request.getContextPath()%>/resource/images/Mahadiscom_Logo.png"></a>
                <a href="javascript:void(0)" class="LoginLink LoginLinkMobile"><span class="glyphicon glyphicon-lock"></span> Login</a>
            </div>
            <div class="collapse navbar-collapse" id="myNavbar">
                <ul class="nav navbar-nav  main-nav">
                    <li class="HomeLink"><a href="home">Home</a></li>
                    <li><a href="tenders">Tenders</a></li>
                    <li class="active"><a href="bids">Bids</a></li>
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
                    <li><a href="download"><span class="glyphicon glyphicon-download-alt"></span>Downloads</a></li>
                    <li><a href="#"><span class="glyphicon glyphicon-list-alt"></span> Public Notices</a></li>
                    <li><a href="Notification"><span class="glyphicon glyphicon-bell"></span> Notification</a></li>
                    <li><a href="registration"><span class="glyphicon glyphicon-registration-mark"></span> Registration</a></li>
                    <li><a href="javascript:void(0)" class="LoginLink"><span class="glyphicon glyphicon-lock"></span> Login</a></li>
                </ul>
                <div class="welcome"> <span class="glyphicon glyphicon-user" style="color:gray"></span> Welcome TestContract |<a href="">Logout</a></div>
            </div>
        </div>
    </nav> 
     <div id="content-wrapper">
        <!-- PAGE CONTENT -->
        <div class="container-fluid" style="margin-bottom:40px;">

            <div class="clearfix"></div>

            <div class="col-sm-12 mobileNoPadding">
                <div class="card padding card-min-height">
                    <div class="col-sm-12 mobileNoPadding AddSlideTab">
                        <div class="bs-example bs-example-tabs" role="tabpanel" data-example-id="togglable-tabs">
                            <ul id="myTab" class="nav nav-tabs nav-tabs-responsive first-level-tab" role="tablist">
                                <li role="presentation" class="active tenderlist">
                                    <a href="#BidsListing" id="BidsListing-tab" role="tab" data-toggle="tab" aria-controls="BidsListing-tab" aria-expanded="true">
                                        <span class="text">Bids Listing</span>
                                    </a>
                                </li>
                                <li role="presentation" class="next tenderdet">
                                    <a href="#BidOpening" role="tab" id="BidOpening-tab" data-toggle="tab" aria-controls="BidOpening">
                                        <span class="text">Bid Opening</span>
                                    </a>
                                </li>
                            </ul>
                            <div id="myTabContent" class="tab-content">
                                <div role="tabpanel" class="tab-pane fade in active" id="BidsListing" aria-labelledby="BidsListing-tab">
                                    <div class="col-sm-12">
                                        <h4>Bids</h4>
                                        <div class="Row-card">

                                            <div class="form-group">
                                                <label class="col-sm-2 col-xs-6">Tender Number.</label>
                                                <div class="col-sm-4 col-xs-6">
                                                    <select class="form-control">
                                                        <option></option>
                                                    </select>
                                                </div>
                                                <label class="col-sm-2 col-xs-6">Tender Version</label>
                                                <label class="col-sm-4 col-xs-6">Tender-505</label>
                                            </div>
                                            <div class="clearfix"></div>
                                            <div class="form-group">
                                                <label class="col-sm-2 col-xs-6">Tender Details</label>
                                                <label class="col-sm-4 col-xs-6">
                                                    TestTender22032017
                                                </label>
                                                <label class="col-sm-2 col-xs-6">Issuing Authority</label>
                                                <label class="col-sm-4 col-xs-6">
                                                    TestTender22032017 , A C P O
                                                </label>
                                            </div>
                                            <div class="clearfix"></div>
                                            <div class="form-group">
                                                <label class="col-sm-2 col-xs-6">Sale Address Location</label>
                                                <label class="col-sm-4 col-xs-6">
                                                    TestTender22032017cx
                                                </label>
                                                <label class="col-sm-2 col-xs-6">Submission Address Location</label>
                                                <label class="col-sm-4 col-xs-6">
                                                    TestTender22032017
                                                </label>
                                            </div>
                                            <div class="clearfix"></div>
                                            <div class="form-group">
                                                <label class="col-sm-2 col-xs-6">Date of Submission</label>
                                                <label class="col-sm-4 col-xs-6">
                                                    01 Dec 2017 12:00 Hrs
                                                </label>
                                            </div>
                                        </div>
                                        <h5 class="red"> *Please Purchase Tender For Start Bid Creation</h5>
                                        <label class="col-sm-12">If above tender details confirm that you want to start bid submission process for this tender, please click below.</label>
                                        <button class="btn btn-info">Start Bid Creation </button>
                                    </div>
                                    <div class="col-sm-12">
                                        <h4>Bids Listing</h4>
                                        <table class="BidsListingTable table table-striped table-bordered" cellspacing="0" width="100%">
                                            <thead>
                                                <tr>
                                                    <th>Tender No.</th>
                                                    <th>Tender Version</th>
                                                    <th>Issuing Authority</th>
                                                    <th>Submission Due </th>
                                                    <th>Status</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <tr>
                                                    <th>TestTender1103</td>
                                                        <td>Browser Requirements </td>
                                                        <td>21st October 2017</td>
                                                        <td>21st October 2017</td>
                                                        <td>21st October 2017</td>
                                                </tr>
                                            </tbody>
                                        </table>
                                    </div>
                                    <div class="form-group">
                                        <div class="col-sm-3 col-xs-9">
                                            <select class="form-control">
                                                <option></option>
                                            </select>
                                        </div>
                                        <div class="col-sm-1 col-xs-3">
                                            <button class="btn btn-info">Process</button>
                                        </div>
                                    </div>
                                </div>

                                <div role="tabpanel" class="tab-pane fade" id="BidOpening" aria-labelledby="BidOpening-tab">
                                    <h4 class="col-sm-12">Bid Opening</h4>
                                    <div class="col-sm-12 mobileNoPadding">
                                        <div class="Row-card">
                                            <h4 class="red col-sm-12">Tender Number will be available in the list only after tender is opened by E-Auctionapp user</h4>
                                            <h4 class="red-text col-sm-12">Bids can be viewed and downloaded by vendor in next 48 hours after tender opening date</h4>
                                            <div class="form-group">
                                                <label class="col-sm-2 col-xs-6">Tender Number.</label>
                                                <div class="col-sm-3 col-xs-6">
                                                    <select class="form-control">
                                                        <option></option>
                                                    </select>
                                                </div>
                                                <label class="col-sm-2 col-xs-6 margintop">Tender Version</label>
                                                <div class="col-sm-3 col-xs-6 margintop">
                                                    <select class="form-control">
                                                        <option></option>
                                                    </select>
                                                </div>
                                                <div class="col-sm-2 col-xs-6">
                                                    <button class="btn btn-info">Search</button>
                                                </div>
                                            </div>
                                            <div class="clearfix"></div>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <div class="clearfix"></div>

                        </div>

                    </div>
                    <!-- End Container -->
                </div>
            </div>
        </div>
        </div>
        <!-- PAGE CONTENT -->
  <div class="height20"></div>      
<div class="footer">
        © 2017 E-Auctionapp. All Rights Reserved.
    </div>
</body>
<script src="<%=request.getContextPath()%>/resource/js/jquery-3.2.1.min.js?appVer=${appVer}"></script>
<script src="<%=request.getContextPath()%>/resource/js/bootstrap.min.js?appVer=${appVer}"></script>
<script src="<%=request.getContextPath()%>/resource/js/jquery.dataTables.min.js?appVer=${appVer}"></script>
<script src="<%=request.getContextPath()%>/resource/js/dataTables.bootstrap.min.js?appVer=${appVer}"></script>
<script src="<%=request.getContextPath()%>/resource/js/bootstrap-datepicker.js?appVer=${appVer}"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resource/js/Bid.js?appVer=${appVer}"></script>

</html>