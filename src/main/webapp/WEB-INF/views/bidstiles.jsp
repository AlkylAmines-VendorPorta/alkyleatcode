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
    <link rel="stylesheet/less" type="text/css" href="<%=request.getContextPath()%>/resource/css/styles.less" />
    <link rel="stylesheet" href="<%=request.getContextPath()%>/resource/css/animate.min.css?appVer=${appVer}" />
    <!-- HTML5 shim and Respond.js?appVer=${appVer} for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js?appVer=${appVer} doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js?appVer=${appVer}"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js?appVer=${appVer}"></script>
    <![endif]-->
</head>

<body class="tiles_background">
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
        <div class="container-fluid">
            <div class="pull-left">
                <button type="button" title="Menue" class="menu-toggle btn btn-info btnsm">
                    <i class="fa fa-bars"></i>
                </button>
                <a href="dashboard" title="Go Back" class="goback2  btn btn-info btnsm"><i
						class="fa fa-arrow-left" aria-hidden="true"></i></a> <a href="" class="goback3 btn btn-info btnsm pull-left"><i class="fa fa-arrow-left"
						aria-hidden="true"></i></a><img src="<%=request.getContextPath()%>/resource/images/E_logo.png?appVer=${appVer}" class="logo"></div>

        </div>
    </div>
    <!-- Header end -->
    <div class="clearfix"></div>

    <!-- full-container start -->
    <div class="full-container animated slideInLeft">
        <!-- MainProductTiles start-->

        <!-- MainProductTiles end-->
        <!-- ProductDetailsTiles start-->
        <div id="ProductDetailsTiles" class="container top20">

            <!--MasterDetailsTiles-->
            <div id="MasterDetailsTiles">
             <a href="bidsopening" id="" class="col-md-3">
                    <div class="tiles text-center">
                        <i class="fa fa-plus pull-left font36"></i>
                        <h4 class="tiles-heading">Bids Listing</h4>
                        <h6 class="tiles-count">0 Records</h6>
                        <div class="clearfix"></div>
                    </div>
                </a>
                <a href="bidsopening" id="" class="col-md-3">
                    <div class="tiles text-center">
                        <i class="fa fa-plus pull-left font36"></i>
                        <h4 class="tiles-heading">Bids Opening</h4>
                        <h6 class="tiles-count">0 Records</h6>
                        <div class="clearfix"></div>
                    </div>
                </a>           
               
            </div>
            <!--  MasterDetailsTiles	end -->

        </div>
        <!-- ProductDetailsTiles end-->
    </div>
    <div class="feedback">
        <div id="" style='display:none;' class="feedback-form col-xs-4 col-md-4 panel panel-default">
            <div class="form panel-body">
                <div class="col-lg-12 text-center">SIDEBAR ACTIVE COLOR</div>
                <div class="clearfix"></div>
                <div class="divider"></div>
                <ul class="colorpicker">
                    <li class="colorbox" style="background:#cc0000" data-foreground="#fbd1d1" data-hover="#fbd1d1"></li>
                    <li class="colorbox" style="background:#586d1f" data-foreground="#eefbcc" data-hover="#eefbcc"></li>
                    <li class="colorbox" style="background:#205b98" data-foreground="#dfefff" data-hover="#dfefff"></li>
                    <li class="colorbox" style="background:#991238" data-foreground="#ffdee7" data-hover="#ffdee7"></li>
                    <li class="colorbox" style="background:#00796a" data-foreground="#ddfdda" data-hover="#ddfdda"></li>
                    <li class="colorbox" style="background:#2c2c2c" data-foreground="#d6d6d6" data-hover="#d6d6d6"></li>
                    <li class="colorbox" style="background:#F44336" data-foreground="#fde1de" data-hover="#fde1de"></li>
                    <li class="colorbox" style="background:#2196F3" data-foreground="#cfeaff" data-hover="#cfeaff"></li>
                    <li class="colorbox" style="background:#4caf50" data-foreground="#deffdf" data-hover="#deffdf"></li>
                    <li class="colorbox" style="background:#ff9800" data-foreground="#fffadf" data-hover="#fffadf"></li>
                    <li class="colorbox" style="background:#26a69a" data-foreground="#eafffd" data-hover="#eafffd"></li>
                    <li class="colorbox" style="background:#9c27b0" data-foreground="#fae6ff" data-hover="#fae6ff"></li>
                    <li class="colorbox" style="background:#283593" data-foreground="#c3caff" data-hover="#c3caff"></li>
                    <li class="colorbox" style="background:#424242" data-foreground="#d2d2d2" data-hover="#d2d2d2"></li>
                    <li class="colorbox" style="background:#546e7a" data-foreground="#d8edf7" data-hover="#d8edf7"></li>
                    <li class="colorbox" style="background:#00bcd4" data-foreground="#e1fcff" data-hover="#e1fcff"></li>
                    <li class="colorbox" style="background:#e91e63" data-foreground="#e8e0ff" data-hover="#e8e0ff"></li>
                    <li class="colorbox" style="background:#544741" data-foreground="#dadada" data-hover="#dadada"></li>
                </ul>
            </div>
            <div class="col-lg-12 text-center">TILES BACKGROUND IMAGES</div>
            <div class="clearfix"></div>
            <div class="divider"></div>
            <ul class="backgroundImg">
                <li class="BackImg" style="background:url('<%=request.getContextPath()%>/resource/images/2.jpg?appVer=${appVer}')"></li>
                <li class="BackImg" style="background:url('<%=request.getContextPath()%>/resource/images/4.jpg?appVer=${appVer}')"></li>
                <li class="BackImg" style="background:url('<%=request.getContextPath()%>/resource/images/5.jpg?appVer=${appVer}')"></li>
                <li class="BackImg" style="background:url('<%=request.getContextPath()%>/resource/images/6.jpg?appVer=${appVer}')"></li>
                <li class="BackImg" style="background:url('<%=request.getContextPath()%>/resource/images/1.jpg?appVer=${appVer}')"></li>
                <li class="BackImg" style="background:url('<%=request.getContextPath()%>/resource/images/3.jpg?appVer=${appVer}')"></li>
                <li class="BackImg" style="background:url('<%=request.getContextPath()%>/resource/images/7.jpg?appVer=${appVer}')"></li>
                <li class="BackImg" style="background:url('<%=request.getContextPath()%>/resource/images/8.jpg?appVer=${appVer}')"></li>
                <li class="BackImg" style="background:url('<%=request.getContextPath()%>/resource/images/9.jpg?appVer=${appVer}')"></li>
            </ul>
            <div class="clearfix"></div>
        </div>
        <div class="feedback-tab"><i class="fa fa-cog"></i></div>
    </div>
    <!-- full-container end-->
    <div class="clearfix"></div>
    <div class="footer">© 2017 E-Auctionapp. All Rights Reserved.</div>
</body>
<script src="<%=request.getContextPath()%>/resource/js/jquery-3.2.1.min.js?appVer=${appVer}"></script>
<script src="<%=request.getContextPath()%>/resource/js/bootstrap.min.js?appVer=${appVer}"></script>
<script src="<%=request.getContextPath()%>/resource/js/tiles.js?appVer=${appVer}"></script>
<script src="<%=request.getContextPath()%>/resource/js/less.min.js?appVer=${appVer}"></script>
<script src="<%=request.getContextPath()%>/resource/js/color.js?appVer=${appVer}"></script>
</html>