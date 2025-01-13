<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <title>E-Auctionapp e-Tendering</title>

    <!-- Bootstrap -->
    <link href="<%=request.getContextPath()%>/resource/css/bootstrap.css?appVer=${appVer}" rel="stylesheet">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/resource/css/font-awesome.css?appVer=${appVer}">
    <link href="<%=request.getContextPath()%>/resource/css/common.css?appVer=${appVer}" rel="stylesheet">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/resource/css/mobile.css?appVer=${appVer}">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/resource/css/styleless.css?appVer=${appVer}" />
    <link rel="stylesheet" href="<%=request.getContextPath()%>/resource/css/animate.min.css?appVer=${appVer}" />
    <!-- HTML5 shim and Respond.js?appVer=${appVer} for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js?appVer=${appVer} doesn't work if you view the page via file:// -->
    <script src="<%=request.getContextPath()%>/resource/js/jquery-3.2.1.min.js?appVer=${appVer}"></script>
<script src="<%=request.getContextPath()%>/resource/js/bootstrap.min.js?appVer=${appVer}"></script>
<script src="<%=request.getContextPath()%>/resource/js/tiles.js?appVer=${appVer}"></script>
<%-- <script src="<%=request.getContextPath()%>/resource/js/less.min.js?appVer=${appVer}"></script> --%>
<script src="<%=request.getContextPath()%>/resource/js/color.js?appVer=${appVer}"></script>

    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js?appVer=${appVer}"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js?appVer=${appVer}"></script>
    <![endif]-->
</head>

<body class="tiles_background">
 <div id="preloader"></div>
    <jsp:include page="dashbordSideBar.jsp" />

    <!-- Header start -->
    <div class="header text-center">
           <div class="col-xs-4">
           	<div class="pull-left"> <button type="button" title="Menue" class="btn btn-info btnsm menu-toggle">
                <i class="fa fa-bars"></i>
            </button>
            </div>
            </div>
            <div class="col-xs-4"><img src="<%=request.getContextPath()%>/resource/images/Mahadiscom_Logo.jpg?appVer=${appVer}" class="dash_logo"></div>
	<div class="col-xs-4">
			<div class="pull-right">
				 <div class="dropdown welcome">
				    Welcome |<a class="dropdown-toggle" type="button" id="dropdownMenu1" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true"><span class="glyphicon glyphicon-user userdet"></span>MSCDCL_User<span class="caret"></span></a> 
				    
				  <ul class="dropdown-menu" aria-labelledby="dropdownMenu1">
				    <li><a href="partnerRegistration">Profile</a></li>
				    <li><a href="logout">Logout</a></li>
				  </ul>
				</div>
			</div>
			</div>
        </div>
    <!-- Header end -->
    <div class="clearfix"></div>

    <!-- full-container start -->
    <div class="full-container animated slideInLeft">
        <!-- MainProductTiles start-->
        <div id="MainProductTiles" class="container top20">
            <a href="javascript:void(0)" class="col-md-2 col-xs-6">
                <div class="tiles text-center">
                    <i class="fa fa-fw fa-dashboard pull-left font36"></i>
                    <h4 class="tiles-heading">Dashboard</h4>
                <!--     <h6 class="tiles-count">1 Records Of Dashboard</h6> -->
                    <div class="clearfix"></div>
                </div>
            </a><a href="mastertiles" class="col-md-2 col-xs-6">
                <div class="tiles text-center">
                    <i class="fa fa-sticky-note pull-left font36" aria-hidden="true"></i>
                    <h4 class="tiles-heading">Master</h4>
<!--                     <h6 class="tiles-count">1 Records Of Master</h6> -->
                    <div class="clearfix"></div>
                </div>
            </a>
            <a href="tendertiles"  class="col-md-2 col-xs-6">
                <div class="tiles text-center">
                    <i class="fa fa-file-pdf-o pull-left font36"></i>
                    <h4 class="tiles-heading">Tenders</h4>
                <!--     <h6 class="tiles-count">7 Records Of Tenders</h6> -->
                    <div class="clearfix"></div>
                </div>
            </a>
            <a href="tendertiles"  class="col-md-2 col-xs-6">
                <div class="tiles text-center">
                    <i class="fa fa-gavel pull-left font36"></i>
                    <h4 class="tiles-heading">Auction</h4>
                <!--     <h6 class="tiles-count">7 Records Of Tenders</h6> -->
                    <div class="clearfix"></div>
                </div>
            </a>
            <a href="#" class="col-md-2 col-xs-6">
                <div class="tiles text-center">
                    <span class="glyphicon glyphicon-list-alt pull-left font36"></span>
                    <h4 class="tiles-heading">Bids</h4>
<!--                     <h6 class="tiles-count">10 Records Of Master</h6> -->
                    <div class="clearfix"></div>
                </div>
            </a>
            <a href="#" class="col-md-2 col-xs-6">
                <div class="tiles text-center">
                    <i class="fa fa-money pull-left font36"></i>
                    <h4 class="tiles-heading">Payment</h4>
<!--                     <h6 class="tiles-count">1 Records Of Vendor</h6> -->
                    <div class="clearfix"></div>
                </div>
            </a>
			<a href="partnerRegistration" class="col-md-2 col-xs-6">
                <div class="tiles text-center">
                    <i class="fa fa-address-card-o pull-left font36"></i>
                    <h4 class="tiles-heading">Registration</h4>
<!--                     <h6 class="tiles-count">2 Records Of User</h6> -->
                    <div class="clearfix"></div>
                </div>
            </a>
             <a href="#" class="col-md-2 col-xs-6">
                <div class="tiles text-center">
                    <i class="fa fa-file-text pull-left font36"></i>
                    <h4 class="tiles-heading">Reports</h4>
<!--                     <h6 class="tiles-count">2 Records Of User</h6> -->
                    <div class="clearfix"></div>
                </div>
            </a>
            <a href="Usertiles.html" class="col-md-2 col-xs-6">
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
            <a href="#" class="col-md-2 col-xs-6">
                <div class="tiles text-center">
                    <i class="fa fa-shopping-cart pull-left font36"></i>
                    <h4 class="tiles-heading">Purchase Proposal</h4>
<!--                     <h6 class="tiles-count">2 Records Of User</h6> -->
                    <div class="clearfix"></div>
                </div>
            </a>
            <a href="mail" class="col-md-2 col-xs-6">
                <div class="tiles text-center">
                    <i class="fa fa-envelope pull-left font36"></i>
                    <h4 class="tiles-heading">Mail</h4>
<!--                     <h6 class="tiles-count">2 Records Of User</h6> -->
                    <div class="clearfix"></div>
                </div>
            </a>
             <a href="roleDetails" class="col-md-2 col-xs-6">
                <div class="tiles text-center">
                    <i class="fa fa-envelope pull-left font36"></i>
                    <h4 class="tiles-heading">Role Details</h4>
<!--                     <h6 class="tiles-count">2 Records Of User</h6> -->
                    <div class="clearfix"></div>
                </div>
            </a>
                
        </div>
       
        
        <!-- ProductDetailsTiles end-->
    </div>
   
    <div class="clearfix"></div>
    <div class="footer">© 2017 E-Auctionapp. All Rights Reserved.</div>
</body>

</html>