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
    <link rel="stylesheet" href="<%=request.getContextPath()%>/resource/css/jquery.paginate.css?appVer=${appVer}" />
    <link rel="stylesheet" href="<%=request.getContextPath()%>/resource/css/bootstrap-datetimepicker.css?appVer=${appVer}" />
    <link rel="stylesheet/less" type="text/css" href="<%=request.getContextPath()%>/resource/css/styles.less" />
    <link rel="stylesheet" href="<%=request.getContextPath()%>/resource/css/animate.min.css?appVer=${appVer}" />
    
    <!-- HTML5 shim and Respond.js?appVer=${appVer} for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js?appVer=${appVer} doesn't work if you view the page via file:// -->    
	
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js?appVer=${appVer}"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js?appVer=${appVer}"></script>
    <![endif]-->
</head>

<body>
   <div class="zoom">
        <a class="zoom-fab zoom-btn-large" id="zoomBtn"><i class="fa fa-bars"></i></a>
        <div class="zoom-menu">
            <a class="zoom-fab zoom-btn-sm zoom-btn-doc scale-transition scale-out"><i class="fa fa-refresh" aria-hidden="true"></i></a>
            <a class="zoom-fab zoom-btn-sm zoom-btn-tangram scale-transition scale-out"><i class="fa fa-plus-square"></i></a>
            <a class="zoom-fab zoom-btn-sm zoom-btn-report scale-transition scale-out"><i class="fa fa-edit"></i></a>
            <a class="zoom-fab zoom-btn-sm zoom-btn-feedback scale-transition scale-out"><i class="fa fa-trash"></i></a>
        </div>
    </div>
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
    <div class="full-container">
        <!-- Header start -->
        
        <!-- Header end -->
        <div class="clearfix"></div>
        <!-- Batch_container start -->
        <div class="Batch_container">
            <div class="header">
            <div class="col-xs-5">
                <img src="<%=request.getContextPath()%>/resource/images/E_logo.png?appVer=${appVer}" class="logo">
                    <button type="button" title="Menue" class="menu-toggle btn btn-info btnsm">
                        <i class="fa fa-bars"></i>
                    </button>
                    <a href="auctiontiles" title="Go Back" class="BAtilse btn btn-info btnsm"><i
						class="fa fa-arrow-left"></i></a>
                    <button title="Go Back" class="btn btn-info btnsm BAlist"><i class="fa fa-arrow-left"></i></button>
                </div>
                <div class="col-xs-7 text-right actionbutton">
                    <button type="button" title="Previous" class="btn btn-info btnPrevious btnsm"><i class="fa fa-arrow-up"></i></button>
                    <button type="button" title="Next" class="btn btn-info btnNext btnsm"><i class="fa fa-arrow-down"></i></button>
                    <button type="button" title="Add/New" class="btn btn-info btnsm">
                        <i class="fa fa-plus-square"></i>
                    </button>
                    <button type="button" title="edit" class="btn btn-info btnsm">
                        <i class="fa fa-pencil-square-o"></i>
                    </button>
                    <button type="button" title="Delete" class="btn btn-info btnsm">
                        <i class="fa fa-trash"></i>
                    </button>
                </div>
        </div>
            <!-- left-side start-->
            <div class="clearfix"></div>
            <div id="mobile_first_container" class="left-side col-md-3 no-marg">
                <div id="custom-search-input">
                    <div class="input-group col-md-12">
                        <input type="text" class="  search-query form-control" placeholder="Serch">
                        <button class="btn btn-danger" type="button">
                            <span class=" glyphicon glyphicon-search"></span>
                        </button>
                    </div>
                </div>
                <ul class="nav nav-tabs tabs-left batchnav example">
                    <li class="active">
                        <a href="#Master" data-toggle="tab">
                            <div class="col-md-12">
                                <label class="col-xs-6">10KG</label>
                                <label class="col-xs-6">20 Sep 2017</label>
                            </div>
                            <div class="col-md-12">
                                <label class="col-xs-6">500Rs</label>
                                <label class="col-xs-6">INR</label>
                            </div>
                        </a>
                    </li>
                    <li>
                        <a href="#ProductCategory" data-toggle="tab">
                            <div class="col-md-12">
                                <label class="col-xs-6">LTR</label>
                                <label class="col-xs-6">5 Aug 2017</label>
                            </div>
                            <div class="col-md-12">
                                <label class="col-xs-6">50Rs</label>
                                <label class="col-xs-6">INR</label>
                            </div>
                        </a>
                    </li>
                    <li>
                        <a href="#AddDepartment" data-toggle="tab">
                            <div class="col-md-12">
                                <label class="col-xs-6">QTY</label>
                                <label class="col-xs-6">12 Aug 2017</label>
                            </div>
                            <div class="col-md-12">
                                <label class="col-xs-6">550Rs</label>
                                <label class="col-xs-6">INR</label>
                            </div>
                        </a>
                    </li>
                    <li>
                        <a href="#AddDepartment" data-toggle="tab">
                            <div class="col-md-12">
                                <label class="col-xs-6">MTR</label>
                                <label class="col-xs-6">15 June 2017</label>
                            </div>
                            <div class="col-md-12">
                                <label class="col-xs-6">350Rs</label>
                                <label class="col-xs-6">INR</label>
                            </div>
                        </a>
                    </li>
                    <li>
                        <a href="#AddDepartment2" data-toggle="tab">
                            <div class="col-md-12">
                                <label class="col-xs-6">QTY</label>
                                <label class="col-xs-6">12 Aug 2017</label>
                            </div>
                            <div class="col-md-12">
                                <label class="col-xs-6">550Rs</label>
                                <label class="col-xs-6">INR</label>
                            </div>
                        </a>
                    </li>
                    <li>
                        <a href="#Master" data-toggle="tab">
                            <div class="col-md-12">
                                <label class="col-xs-6">10KG</label>
                                <label class="col-xs-6">20 Sep 2017</label>
                            </div>
                            <div class="col-md-12">
                                <label class="col-xs-6">500Rs</label>
                                <label class="col-xs-6">INR</label>
                            </div>
                        </a>
                    </li>
                    <li>
                        <a href="#ProductCategory" data-toggle="tab">
                            <div class="col-md-12">
                                <label class="col-xs-6">LTR</label>
                                <label class="col-xs-6">5 Aug 2017</label>
                            </div>
                            <div class="col-md-12">
                                <label class="col-xs-6">50Rs</label>
                                <label class="col-xs-6">INR</label>
                            </div>
                        </a>
                    </li>
                    <li>
                        <a href="#AddDepartment" data-toggle="tab">
                            <div class="col-md-12">
                                <label class="col-xs-6">QTY</label>
                                <label class="col-xs-6">12 Aug 2017</label>
                            </div>
                            <div class="col-md-12">
                                <label class="col-xs-6">550Rs</label>
                                <label class="col-xs-6">INR</label>
                            </div>
                        </a>
                    </li>
                </ul>
                <div class="clearfix"></div>
            </div>
            <!-- left-side end-->

            <!-- right-side start-->
            <div id="mobile_second_container" class="right-side col-md-9 no-marg ">
                <div class="clearfix"></div>
                <div class="tab-content">
                    <!-- Master tab start-->
                    <div class="tab-pane active slideInRight" id="Master">

                        <form>
                            <div class="panel-group" id="accordion">
                                <div class="panel panel-default">
                                    <div class="panel-heading">
                                        <h4 class="panel-title">
											<a data-toggle="collapse" data-parent="#accordion"
												href="#collapse1"> Add Batches</a>
										</h4>
                                    </div>
                                    <div id="collapse1" class="panel-collapse collapse in">
                                        <div class="panel-body">
                                            <label class="col-md-6 col-xs-12">UOM<span class="red">*</span>
                                                <input type="text" class="form-control" placeholder="10KG">
                                            </label>
                                            <div class="col-md-6 col-xs-12">
                                                <label>Delivery Date<span class="red">*</span>
                                                </label>
                                                <input size="16" type="text" value="2012-06-15 14:45" class="form_datetime form-control">
                                            </div>
                                            <label class="col-md-6 col-xs-12">Price<span class="red">*</span>
                                                <input type="text" placeholder="500RS" class="form-control">
                                            </label>
                                            <label class="col-md-6 col-xs-12">Currency<span class="red">*</span>
                                                <select class="form-control">
                                                    <option>INR</option>
                                                </select>
                                            </label>
                                            <div class="clearfix"></div>
                                            <div class="col-md-12 text-center">
                                                <a class="btn btn-info AuctionShow">Auction</a>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </form>
                    </div>
                    <!-- Master tab end-->

                    <!-- Product Category tab start-->
                    <div class="tab-pane slideInRight" id="ProductCategory">
                        <div class="col-md-12">
                            <label class="col-xs-6">ID</label>
                            <label class="col-xs-6">100063</label>
                        </div>
                        <div class="col-md-12">
                            <label class="col-xs-6">Product</label>
                            <label class="col-xs-6">Computer</label>
                        </div>
                        <div class="col-md-12">
                            <button type="button" class="btn btn-info">Save</button>
                        </div>
                    </div>
                    <!-- Product Category tab end-->
                </div>
            </div>
            <!-- right-side end-->
        </div>
        <!-- batch_container end -->
        <!-- Auction_container start -->
        <div class="Auction_container">
            <div class="header">
                <div class="col-xs-5">
                <img src="<%=request.getContextPath()%>/resource/images/E_logo.png?appVer=${appVer}" class="logo">
                    <button type="button" title="Menue" class="btn btn-info btnsm menu-toggle">
                        <i class="fa fa-bars"></i>
                    </button>
                    <a href="javascript:void(0)" title="Goback" class="btn btn-info btnsm Backtobatch"><i
						class="fa fa-arrow-left" ></i></a>
                    <button title="Goback" class="btn btn-info btnsm auctinlist"><i class="fa fa-arrow-left"></i></button>
                </div>
                <div class="col-xs-7 text-right actionbutton">
                    <button type="button" class="btn btn-info btnPrevious btnsm"><i class="fa fa-arrow-up"></i></button>
                    <button type="button" class="btn btn-info btnNext btnsm"><i class="fa fa-arrow-down"></i></button>
                    <button type="button" class="btn btn-info btnsm">
                        <i class="fa fa-plus-square"></i>
                    </button>
                    <button type="button" class="btn btn-info btnsm">
                        <i class="fa fa-pencil-square-o"></i>
                    </button>
                    <button type="button" class="btn btn-info btnsm">
                        <i class="fa fa-trash"></i>
                    </button>
                </div>
            </div>
            <!-- left-side start-->
            <div class="clearfix"></div>
            <div id="mobile_Batch_container" class="left-side col-md-3 no-marg ">
                <div id="custom-search-input">
                    <div class="input-group col-md-12">
                        <input type="text" class="  search-query form-control" placeholder="Serch">
                        <button class="btn btn-danger" type="button">
                            <span class=" glyphicon glyphicon-search"></span>
                        </button>
                    </div>
                </div>
                <ul id="" class="nav nav-tabs auctionnav tabs-left example">
                    <li class="active">
                        <a href="#AuctionDetails" data-toggle="tab">
                            <div class="col-md-12">
                                <label class="col-xs-6">Auction</label>
                                <label class="col-xs-6">100063</label>
                            </div>
                            <div class="col-md-12">
                                <label class="col-xs-6">Details</label>
                                <label class="col-xs-6">Cement</label>
                            </div>
                        </a>
                    </li>
                    <li>
                        <a href="#AuctionDetails1" data-toggle="tab">
                            <div class="col-md-12">
                                <label class="col-xs-6">ID</label>
                                <label class="col-xs-6">100063</label>
                            </div>
                            <div class="col-md-12">
                                <label class="col-xs-6">Product</label>
                                <label class="col-xs-6">Cement</label>
                            </div>
                        </a>
                    </li>
                </ul>
                <div class="clearfix"></div>
                <div class="clearfix"></div>
            </div>
            <!-- left-side end-->
            <div id="mobile_Auction_container" class="right-side col-md-9 no-marg">
                <div class="clearfix"></div>
                <div class="tab-content">
                    <!-- Master tab start-->
                    <div class="tab-pane active" id="AuctionDetails">

                        <div id="collapse2" class="panel-collapse collapse in">
                            <div class="panel-body mob-body">
                                <ul class="nav nav-tabs aucttab">
                                    <li class="active"><a data-toggle="tab" href="#home">Auction
													Details</a></li>
                                    <li><a data-toggle="tab" href="#menu1">Date/Time
													Configration</a></li>
                                </ul>

                                <div class="tab-content">
                                    <div id="home" class="tab-pane fade in active">
                                        <div class="panel-group" id="accordion1">
                                            <div class="panel panel-default">
                                                <div class="panel-heading">
                                                    <h4 class="panel-title">
																<a data-toggle="collapse" data-parent="#accordion1"
																	href="#collapseA"> Product Details</a>
															</h4>
                                                </div>
                                                <div id="collapseA" class="panel-collapse collapse in">
                                                    <div class="panel-body">
                                                        <div class="col-md-6 col-xs-12">
                                                            <label>Date From<span class="red">*</span>
                                                            </label>
                                                            <input size="16" type="text" value="2012-06-15 14:45" class="form_datetime form-control">
                                                        </div>
                                                        <div class="col-md-6 col-xs-12">
                                                            <label>Date To<span class="red">*</span>
                                                            </label>
                                                            <input size="16" type="text" value="2012-06-15 14:45" class="form_datetime form-control">
                                                        </div>
                                                        <label class="col-md-6 col-xs-12">Product<span class="red">*</span>
                                                            <select class="form-control">
                                                                <option>Information Technology</option>
                                                            </select>
                                                        </label>
                                                        <label class="col-md-6 col-xs-12">Product Description
                                                            <span class="red">*</span>
                                                            <select class="form-control">
                                                                <option>Information Technology</option>
                                                            </select>
                                                        </label>
                                                        <label class="col-md-6 col-xs-12">Product Specification
                                                            <span class="red">*</span>
                                                            <select class="form-control">
                                                                <option>Information Technology</option>
                                                            </select>
                                                        </label>
                                                        <label class="col-md-6 col-xs-12">Taxes And Other Charges <span class="red">*</span>
                                                            <input type="text" class="form-control">
                                                        </label>
                                                        <label class="col-md-6 col-xs-12">UOM<span class="red">*</span>
                                                            <input type="text" class="form-control">
                                                        </label>
                                                        <div class="clearfix"></div>
                                                        <div class="col-md-12 text-center">
                                                            <a data-toggle="collapse" class="btn btn-info" data-parent="#accordion1" href="#collapseB">Next</a>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="panel panel-default">
                                                <div class="panel-heading">
                                                    <h4 class="panel-title">
																<a> Delivery Details</a>
															</h4>
                                                </div>
                                                <div id="collapseB" class="panel-collapse collapse">
                                                    <div class="panel-body">
                                                        <div class="col-md-6 col-xs-12">
                                                            <label>Delivery Date<span class="red">*</span>
                                                            </label>
                                                            <input size="16" type="text" value="2012-06-15 14:45" class="form_datetime form-control">
                                                        </div>
                                                        <label class="col-md-6 col-xs-12">Required Quantity
                                                            <span class="red">*</span>
                                                            <input type="text" class="form-control">
                                                        </label>
                                                        <label class="col-md-6 col-xs-12">Quantity<span class="red">*</span>
                                                            <input type="text" class="form-control">
                                                        </label>
                                                        <label class="col-md-6 col-xs-12">Unit Price<span class="red">*</span>
                                                            <input type="text" class="form-control">
                                                        </label>
                                                        <label class="col-md-6 col-xs-12">Bid Price<span class="red">*</span>
                                                            <input type="text" class="form-control">
                                                        </label>
                                                        <label class="col-md-6 col-xs-12">Landing Price<span class="red">*</span>
                                                            <input type="text" class="form-control">
                                                        </label>
                                                        <label class="col-md-6 col-xs-12">Currency<span class="red">*</span>
                                                            <select class="form-control">
                                                                <option>Information Technology</option>
                                                            </select>
                                                        </label>
                                                        <label class="col-md-6 col-xs-12">Decrement By<span class="red">*</span>
                                                            <input type="text" class="form-control">
                                                        </label>
                                                        <div class="clearfix"></div>
                                                        <div class="col-md-12 text-center">
                                                            <a data-toggle="collapse" class="btn btn-info" data-parent="#accordion1" href="#collapseC">Next</a>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="panel panel-default">
                                                <div class="panel-heading">
                                                    <h4 class="panel-title">
																<a> Address Details</a>
															</h4>
                                                </div>
                                                <div id="collapseC" class="panel-collapse collapse">
                                                    <div class="panel-body">
                                                        <label class="col-md-6 col-xs-12">Bill To Address
                                                            <span class="red">*</span>
                                                            <select class="form-control">
                                                                <option>Information Technology</option>
                                                            </select>
                                                        </label>
                                                        <label class="col-md-6 col-xs-12">Ship To Address
                                                            <span class="red">*</span>
                                                            <select class="form-control">
                                                                <option>Information Technology</option>
                                                            </select>
                                                        </label>
                                                        <label class="col-md-6 col-xs-12">Payment Term
                                                            <input type="text" class="form-control">
                                                        </label>
                                                        <label class="col-md-6 col-xs-12">Short Description
                                                            <input type="text" class="form-control">
                                                        </label>
                                                        <div class="col-md-6 col-xs-12">
                                                            <label class="checkbox-inline">
                                                                <input type="checkbox" value=""> CForm Provided
                                                            </label>
                                                            <label class="checkbox-inline">
                                                                <input type="checkbox" value=""> Public
                                                            </label>
                                                            <label class="checkbox-inline">
                                                                <input type="checkbox" value=""> BOQ
                                                            </label>
                                                            <label class="checkbox-inline">
                                                                <input type="checkbox" value=""> Show All Bid History
                                                            </label>
                                                            <label class="checkbox-inline">
                                                                <input type="checkbox" value=""> Bid With Own Decrement
                                                            </label>
                                                            <label class="checkbox-inline">
                                                                <input type="checkbox" value=""> Active
                                                            </label>
                                                            <label class="checkbox-inline">
                                                                <input type="checkbox" value=""> Show Lowest bid Value
                                                            </label>
                                                        </div>
                                                        <div class="clearfix"></div>
                                                        <div class="col-md-12 text-center">
                                                            <button type="button" class="btn btn-info">Save</button>
                                                        </div>
                                                        <div class="clearfix"></div>
                                                        <br>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="clearfix"></div>
                                            <br>
                                            <div class="col-md-12 text-center">
                                                <a data-toggle="collapse" data-parent="#accordion1" href="#collapseD" class="btn btn-info mobbtn">Tax and
															Other Charges</a> <a data-toggle="collapse" data-parent="#accordion1" href="#collapseE" class="btn btn-info">Specifications</a> <a data-toggle="collapse" data-parent="#accordion1" href="#collapseF" class="btn btn-info">Vendor</a> <a data-toggle="collapse" data-parent="#accordion1" href="#collapseG" class="btn btn-info">Add Vendor</a>
                                            </div>
                                            <div class="clearfix"></div>
                                            <br>
                                            <div class="panel panel-default">
                                                <div class="panel-heading">
                                                    <h4 class="panel-title">
																<a>Tax and Other Charges</a>
															</h4>
                                                </div>
                                                <div id="collapseD" class="panel-collapse collapse">
                                                    <div class="panel-body">
                                                        <label class="col-md-6 col-xs-12">Bill To Address
                                                            <span class="red">*</span>
                                                            <select class="form-control">
                                                                <option>Information Technology</option>
                                                            </select>
                                                        </label>
                                                        <label class="col-md-6 col-xs-12">Tax and Other Charges
                                                            <input type="text" class="form-control">
                                                        </label>
                                                        <div class="col-md-6 col-xs-12">
                                                            <label class="checkbox-inline">
                                                                <input type="checkbox" value=""> Mandatory
                                                            </label>
                                                            <label class="checkbox-inline">
                                                                <input type="checkbox" value=""> Tax Componentc
                                                            </label>
                                                        </div>
                                                        <div class="clearfix"></div>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="panel panel-default">
                                                <div class="panel-heading">
                                                    <h4 class="panel-title">
																<a>Specification</a>
															</h4>
                                                </div>
                                                <div id="collapseE" class="panel-collapse collapse">
                                                    <div class="panel-body">
                                                        <label class="col-md-6 col-xs-12">Product Specification Title
                                                            <input type="text" class="form-control">
                                                        </label>
                                                        <label class="col-md-6 col-xs-12">Product Specification Values
                                                            <input type="text" class="form-control">
                                                        </label>
                                                        <label class="col-md-6 col-xs-12">Requirment Level
                                                            <input type="text" class="form-control">
                                                        </label>
                                                        <div class="col-md-6 col-xs-12">
                                                            <label class="checkbox-inline">
                                                                <input type="checkbox" value=""> Active
                                                            </label>
                                                        </div>
                                                        <div class="clearfix"></div>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="panel panel-default">
                                                <div class="panel-heading">
                                                    <h4 class="panel-title">
																<a>Vendor</a>
															</h4>
                                                </div>
                                                <div id="collapseF" class="panel-collapse collapse">
                                                    <div class="panel-body">
                                                        <label class="col-md-6 col-xs-12">Vendor Name
                                                            <input type="time" class="form-control">
                                                        </label>
                                                        <label class="col-md-6 col-xs-12">Company Name
                                                            <input type="text" class="form-control">
                                                        </label>
                                                        <label class="col-md-6 col-xs-12">Proposal Invitation
                                                            <input type="text" class="form-control">
                                                        </label>
                                                        <label class="col-md-6 col-xs-12">Proposal Received
                                                            <input type="text" class="form-control">
                                                        </label>
                                                        <label class="col-md-6 col-xs-12">Bid Invitation Sent
                                                            <input type="text" class="form-control">
                                                        </label>
                                                        <div class="col-md-6 col-xs-12">
                                                            <label class="checkbox-inline">
                                                                <input type="checkbox" value=""> Active
                                                            </label>
                                                        </div>
                                                        <div class="clearfix"></div>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="panel panel-default">
                                                <div class="panel-heading">
                                                    <h4 class="panel-title">
																<a>Add Vendor</a>
															</h4>
                                                </div>
                                                <div id="collapseG" class="panel-collapse collapse">
                                                    <div class="panel-body">
                                                        <label class="col-md-6 col-xs-12">Vendor Name
                                                            <input type="text" class="form-control">
                                                        </label>
                                                        <label class="col-md-6 col-xs-12">Company Name
                                                            <input type="text" class="form-control">
                                                        </label>
                                                        <label class="col-md-6 col-xs-12">PAN NO
                                                            <input type="text" class="form-control">
                                                        </label>
                                                        <label class="col-md-6 col-xs-12">Email
                                                            <input type="text" class="form-control">
                                                        </label>
                                                        <div class="col-md-6 col-xs-12">
                                                            <label class="checkbox-inline">
                                                                <input type="checkbox" value=""> Active
                                                            </label>
                                                        </div>
                                                        <div class="clearfix"></div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div id="menu1" class="tab-pane fade">
                                        <div class="panel-group" id="accordion2">
                                            <div class="panel panel-default">
                                                <div class="panel-heading">
                                                    <h4 class="panel-title">
																<a data-toggle="collapse" data-parent="#accordion2"
																	href="#collapseAA"> Department Details</a>
															</h4>
                                                </div>
                                                <div id="collapseAA" class="panel-collapse collapse in">
                                                    <div class="panel-body">
                                                        <div class="col-md-6 col-xs-12">
                                                            <label>From Date<span class="red">*</span>
                                                            </label>
                                                            <input size="16" type="text" value="2012-06-15 14:45" class="form_datetime form-control">
                                                        </div>
                                                        <div class="col-md-6 col-xs-12">
                                                            <label>To Date<span class="red">*</span>
                                                            </label>
                                                            <input size="16" type="text" value="2012-06-15 14:45" class="form_datetime form-control">
                                                        </div>
                                                        <div class="col-md-6 col-xs-12">
                                                            <label class="checkbox-inline">
                                                                <input type="checkbox" value=""> Hours
                                                            </label>
                                                            <label class="checkbox-inline">
                                                                <input type="checkbox" value=""> Standard
                                                            </label>
                                                        </div>
                                                        <div class="clearfix"></div>
                                                        <label class="col-md-12 col-xs-12">Working Time<span class="red">*</span></label>
                                                        <div class="col-xs-6 col-md-6">
                                                            <select class="form-control">
                                                                <option>9.30AM</option>
                                                            </select>
                                                        </div>
                                                        <div class="col-xs-6 col-md-6">
                                                            <select class="form-control">
                                                                <option>9.30PM</option>
                                                            </select>
                                                        </div>
                                                        <div class="form-group">
                                                            <label class="col-md-12 col-xs-12"><a>Add
																			Vendor</a></label>
                                                            <label class="col-md-5 col-xs-5">Total Hours
                                                                <span class="red">*</span>
                                                            </label>
                                                            <div class="col-md-7 col-xs-7">
                                                                <input type="text" class="form-control">
                                                            </div>
                                                            <label class="col-md-5 col-xs-5">From Time<span class="red">*</span></label>
                                                            <div class="col-md-7 col-xs-7">
                                                                <input size="16" type="text" value="2012-06-15 14:45" class="form_datetime form-control">
                                                            </div>
                                                            <label class="col-md-5 col-xs-5">To Time<span class="red">*</span></label>
                                                            <div class="col-md-7 col-xs-7">
                                                                <input size="16" type="text" value="2012-06-15 14:45" class="form_datetime form-control">
                                                            </div>
                                                        </div>
                                                        <div class="form-group">
                                                            <label class="col-md-12 col-xs-12"><a>Proposal
																			Date</a></label>
                                                            <label class="col-md-5 col-xs-5">Total Hours
                                                                <span class="red">*</span>
                                                            </label>
                                                            <div class="col-md-7 col-xs-7">
                                                                <input type="text" class="form-control">
                                                            </div>
                                                            <label class="col-md-5 col-xs-5">From Time<span class="red">*</span></label>
                                                            <div class="col-md-7 col-xs-7">
                                                                <input size="16" type="text" value="2012-06-15 14:45" class="form_datetime form-control">
                                                            </div>
                                                            <label class="col-md-5 col-xs-5">To Time<span class="red">*</span></label>
                                                            <div class="col-md-7 col-xs-7">
                                                                <input size="16" type="text" value="2012-06-15 14:45" class="form_datetime form-control">
                                                            </div>
                                                        </div>
                                                        <div class="form-group">
                                                            <label class="col-md-12 col-xs-12"><a>Vendors
																			Invitation</a></label>
                                                            <label class="col-md-5 col-xs-5">Total Hours
                                                                <span class="red">*</span>
                                                            </label>
                                                            <div class="col-md-7 col-xs-7">
                                                                <input type="text" class="form-control">
                                                            </div>
                                                            <label class="col-md-5 col-xs-5">From Time<span class="red">*</span></label>
                                                            <div class="col-md-7 col-xs-7">
                                                                <input size="16" type="text" value="2012-06-15 14:45" class="form_datetime form-control">
                                                            </div>
                                                            <label class="col-md-5 col-xs-5">To Time<span class="red">*</span></label>
                                                            <div class="col-md-7 col-xs-7">
                                                                <input size="16" type="text" value="2012-06-15 14:45" class="form_datetime form-control">
                                                            </div>
                                                        </div>
                                                        <div class="form-group">
                                                            <label class="col-md-12 col-xs-12"><a>Bidding
																			Date</a></label>
                                                            <label class="col-md-5 col-xs-5">Total Hours
                                                                <span class="red">*</span>
                                                            </label>
                                                            <div class="col-md-7 col-xs-7">
                                                                <input type="text" class="form-control">
                                                            </div>
                                                            <label class="col-md-5 col-xs-5">From Time<span class="red">*</span></label>
                                                            <div class="col-md-7 col-xs-7">
                                                                <input size="16" type="text" value="2012-06-15 14:45" class="form_datetime form-control">
                                                            </div>
                                                            <label class="col-md-5 col-xs-5">To Time<span class="red">*</span></label>
                                                            <div class="col-md-7 col-xs-7">
                                                                <input size="16" type="text" value="2012-06-15 14:45" class="form_datetime form-control">
                                                            </div>
                                                        </div>
                                                        <div class="form-group">
                                                            <label class="col-md-12 col-xs-12"><a>Winner
																			Selection Date</a></label>
                                                            <label class="col-md-5 col-xs-5">Total Hours
                                                                <span class="red">*</span>
                                                            </label>
                                                            <div class="col-md-7 col-xs-7">
                                                                <input type="text" class="form-control">
                                                            </div>
                                                            <label class="col-md-5 col-xs-5">From Time<span class="red">*</span></label>
                                                            <div class="col-md-7 col-xs-7">
                                                                <input size="16" type="text" value="2012-06-15 14:45" class="form_datetime form-control">
                                                            </div>
                                                            <label class="col-md-5 col-xs-5">To Time<span class="red">*</span></label>
                                                            <div class="col-md-7 col-xs-7">
                                                                <input size="16" type="text" value="2012-06-15 14:45" class="form_datetime form-control">
                                                            </div>
                                                        </div>
                                                        <div class="clearfix"></div>
                                                        <div class="col-md-12 text-center">
                                                            <a class="btn btn-info">Save</a>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>

                                </div>
                                <div class="clearfix"></div>
                            </div>
                            <div class="clearfix"></div>
                        </div>

                        <!-- Auction_container end-->
                    </div>
                    <div class="tab-pane" id="AuctionDetails1">dsfdjnfdsn</div>
                </div>
            </div>
        </div>
        <!-- Auction_container end-->
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
                <li class="BackImg" style="background:url('<%=request.getContextPath()%>/resource/images/2.jpg')"></li>
                <li class="BackImg" style="background:url('<%=request.getContextPath()%>/resource/images/4.jpg')"></li>
                <li class="BackImg" style="background:url('<%=request.getContextPath()%>/resource/images/5.jpg')"></li>
                <li class="BackImg" style="background:url('<%=request.getContextPath()%>/resource/images/6.jpg')"></li>
                <li class="BackImg" style="background:url('<%=request.getContextPath()%>/resource/images/1.jpg')"></li>
                <li class="BackImg" style="background:url('<%=request.getContextPath()%>/resource/images/3.jpg')"></li>
                <li class="BackImg" style="background:url('<%=request.getContextPath()%>/resource/images/7.jpg')"></li>
                <li class="BackImg" style="background:url('<%=request.getContextPath()%>/resource/images/8.jpg')"></li>
                <li class="BackImg" style="background:url('<%=request.getContextPath()%>/resource/images/9.jpg')"></li>
            </ul>
            <div class="clearfix"></div>
        </div>
        <div class="feedback-tab"><i class="fa fa-cog"></i></div>
    </div>
    <!-- full-container end-->
    <div class="clearfix"></div>
    <div class="footer">© 2017 E-Auctionapp. All Rights Reserved.</div>
</body>
<script src="<%=request.getContextPath()%>/resource/js/jquery-3.2.1.min.js"></script>
	<script src="<%=request.getContextPath()%>/resource/js/bootstrap.min.js"></script>
	<script src="<%=request.getContextPath()%>/resource/js/tiles.js"></script>
	<script src="<%=request.getContextPath()%>/resource/js/AddAuction.js"></script>
	<script src="<%=request.getContextPath()%>/resource/js/jquery.paginate.js"></script>
	<script src="<%=request.getContextPath()%>/resource/js/bootstrap-datetimepicker.js"></script>
	<script src="<%=request.getContextPath()%>/resource/js/less.min.js"></script>
	<script src="<%=request.getContextPath()%>/resource/js/color.js"></script>
</html>