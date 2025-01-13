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
        <!-- Bootstrap -->
        <link href="<%=request.getContextPath()%>/resource/css/bootstrap.css?appVer=${appVer}" rel="stylesheet">
        <link rel="stylesheet" href="<%=request.getContextPath()%>/resource/css/font-awesome.css?appVer=${appVer}">
        <link href="<%=request.getContextPath()%>/resource/css/common.css?appVer=${appVer}" rel="stylesheet">
        <link rel="stylesheet" href="<%=request.getContextPath()%>/resource/css/mobile.css?appVer=${appVer}">
        <link rel="stylesheet" href="<%=request.getContextPath()%>/resource/css/styleless.css?appVer=${appVer}" />
        <link rel="stylesheet" href="<%=request.getContextPath()%>/resource/css/animate.min.css?appVer=${appVer}" />
        <!-- HTML5 shim and Respond.js?appVer=${appVer} for IE8 support of HTML5 elements and media queries -->
        <!-- WARNING: Respond.js?appVer=${appVer} doesn't work if you view the page via file:// -->
        <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js?appVer=${appVer}"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js?appVer=${appVer}"></script>
    <![endif]-->
    </head>

    <body class="tiles_background">
        <div id="preloader"></div>
        <div id="sidebar-wrapper">
            <div class="sidebar-nav">
                <div class="nav-side-menu">
                    <div class="brand"><img src="<%=request.getContextPath()%>/resource/images/logo.png?appVer=${appVer}" class="navlogo logo"></div>

                    <div class="menu-list">

                        <ul id="menu-content" class="menu-content">
                            <li data-toggle="collapse" data-target="#products" class="collapsed active">
                                <a href="#"><i class="fa fa-user-circle usericon" aria-hidden="true"></i>Welcome<span class="arrow"></span></a>
                            </li>
                            <ul class="sub-menu collapse" id="products">
                                <li><a href="#">Logout</a></li>
                            </ul>
                            <li>
                                <a href="#">
                                    <i class="fa fa-dashboard fa-lg"></i> Dashboard
                                </a>
                            </li>

                            <li data-toggle="collapse" data-target="#service" class="collapsed">
                                <a href="#"><i class="fa fa-globe fa-lg"></i> Services <span class="arrow"></span></a>
                            </li>
                            <ul class="sub-menu collapse" id="service">
                                <li>New Service 1</li>
                                <li>New Service 2</li>
                                <li>New Service 3</li>
                            </ul>
                            <li>
                                <a href="#">
                                    <i class="fa fa-user fa-lg"></i> Profile
                                </a>
                            </li>

                            <li>
                                <a href="#">
                                    <i class="fa fa-users fa-lg"></i> Users
                                </a>
                            </li>
                        </ul>
                    </div>
                </div>
                <div class="clearfix"></div>

            </div>
        </div>
        <!-- Header start -->
        <div class="header">
            <div class="container-fluid text-center">
                <div class="col-xs-4">
                    <div class="pull-left">
                        <button type="button" title="Menue" class="menu-toggle btn btn-info btnsm">
                            <i class="fa fa-bars"></i>
                        </button>
                        <a href="tendertiles" title="Go Back" class="goback2  btn btn-info btnsm"><i
						class="fa fa-arrow-left" aria-hidden="true"></i></a> <a href="" class="goback3 btn btn-info btnsm pull-left"><i class="fa fa-arrow-left"
						aria-hidden="true"></i></a></div>
                </div>
                <div class="col-xs-4"><img src="<%=request.getContextPath()%>/resource/images/Mahadiscom_Logo.jpg?appVer=${appVer}" class="dash_logo"></div>
                <div class="col-xs-4">
                    <div class="welcome pull-right"> <span class="glyphicon glyphicon-user" style="color:#fff"></span> Welcome MSCDCL_User |<a href="home">Logout</a></div>
                </div>
            </div>
        </div>
        <!-- Header end -->
        <div class="clearfix"></div>

        <!-- full-container start -->
        <div class="full-container animated slideInLeft">
            <!-- ProductDetailsTiles start-->
            <div id="ProductDetailsTiles" class="container top20">
                <!--AuctionDetailsTiles-->
                <div id="AuctionDetailsTiles">
                    <a href="tenderPreparation" id="" class="col-md-2">
                        <div class="tiles text-center">
                            <i class="fa fa-plus pull-left font36"></i>
                            <h4 class="tiles-heading">Tender Preparation </h4>
                            <!-- <h6 class="tiles-count">1 Records</h6> -->
                            <div class="clearfix"></div>
                        </div>
                    </a>

                    <a href="tenderPublishing" id="" class="col-md-2">
                        <div class="tiles text-center">
                            <i class="fa fa-plus pull-left font36"></i>
                            <h4 class="tiles-heading">Tender Publishing</h4>
                            <!-- <h6 class="tiles-count">0 Records</h6> -->
                            <div class="clearfix"></div>
                        </div>
                    </a>
                    <a href="tenderScheduling" id="" class="col-md-2">
                        <div class="tiles text-center">
                            <i class="fa fa-plus pull-left font36"></i>
                            <h4 class="tiles-heading">Tender Scheduling</h4>
                            <!-- <h6 class="tiles-count">0 Records</h6> -->
                            <div class="clearfix"></div>
                        </div>
                    </a>
                    <a href="tenderPurchase" id="" class="col-md-2">
                        <div class="tiles text-center">
                            <i class="fa fa-plus pull-left font36"></i>
                            <h4 class="tiles-heading">Tender Purchase</h4>
                            <!-- <h6 class="tiles-count">0 Records</h6> -->
                            <div class="clearfix"></div>
                        </div>
                    </a>
                    <a href="tenderSubmission" id="" class="col-md-2">
                        <div class="tiles text-center">
                            <i class="fa fa-plus pull-left font36"></i>
                            <h4 class="tiles-heading">Tender Submission</h4>
                            <!-- <h6 class="tiles-count">0 Records</h6> -->
                            <div class="clearfix"></div>
                        </div>
                    </a>
                    <a href="tenderOpening" id="" class="col-md-2">
                        <div class="tiles text-center">
                            <i class="fa fa-plus pull-left font36"></i>
                            <h4 class="tiles-heading">Tender Opening</h4>
                            <!-- <h6 class="tiles-count">0 Records</h6> -->
                            <div class="clearfix"></div>
                        </div>
                    </a>
                    <a href="tenderCommercialScrutiny" id="" class="col-md-2">
                        <div class="tiles text-center">
                            <i class="fa fa-plus pull-left font36"></i>
                            <h4 class="tiles-heading">Tender Commercial Scrutiny</h4>
                            <!-- <h6 class="tiles-count">0 Records</h6> -->
                            <div class="clearfix"></div>
                        </div>
                    </a>
                    <a href="tenderTechnicalScrutiny" id="" class="col-md-2">
                        <div class="tiles text-center">
                            <i class="fa fa-plus pull-left font36"></i>
                            <h4 class="tiles-heading">Tender Technical Scrutiny</h4>
                            <!-- <h6 class="tiles-count">0 Records</h6> -->
                            <div class="clearfix"></div>
                        </div>
                    </a>
                </div>
                <!--  AuctionDetailsTiles	end -->

            </div>
            <!-- ProductDetailsTiles end-->
        </div>

        <!-- full-container end-->
        <div class="clearfix"></div>
        <div class="footer">© 2017 E-Auctionapp. All Rights Reserved.</div>
    </body>
    <script src="<%=request.getContextPath()%>/resource/js/jquery-3.2.1.min.js?appVer=${appVer}"></script>
    <script src="<%=request.getContextPath()%>/resource/js/bootstrap.min.js?appVer=${appVer}"></script>
    <script src="<%=request.getContextPath()%>/resource/js/tiles.js?appVer=${appVer}"></script>
    <%-- <script src="<%=request.getContextPath()%>/resource/js/less.min.js?appVer=${appVer}"></script> --%>
        <script src="<%=request.getContextPath()%>/resource/js/color.js?appVer=${appVer}"></script>

    </html>