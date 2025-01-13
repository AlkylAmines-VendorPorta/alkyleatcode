<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <!DOCTYPE html>
    <html lang="en">

    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!--css included  -->
        <link href="<%=request.getContextPath()%>/resource/css/bootstrap.css?appVer=${appVer}" rel="stylesheet">
        <link rel="stylesheet" href="<%=request.getContextPath()%>/resource/css/font-awesome.css?appVer=${appVer}">
        <link rel="stylesheet" href="<%=request.getContextPath()%>/resource/css/bootstrap-datepicker.css?appVer=${appVer}">
        <link rel="stylesheet" href="<%=request.getContextPath()%>/resource/css/bootstrap-timepicker.css?appVer=${appVer}">
        <link rel="stylesheet" href="<%=request.getContextPath()%>/resource/css/common.css?appVer=${appVer}">
        <link rel="stylesheet" href="<%=request.getContextPath()%>/resource/css/mobile.css?appVer=${appVer}">
        <link rel="stylesheet" href="<%=request.getContextPath()%>/resource/css/kendo.common-material.min.css?appVer=${appVer}" />
        <link rel="stylesheet" href="<%=request.getContextPath()%>/resource/css/kendo.material.min.css?appVer=${appVer}" />
        <link rel="stylesheet" href="<%=request.getContextPath()%>/resource/css/kendo.material.mobile.min.css?appVer=${appVer}" />
        <link rel="stylesheet" href="<%=request.getContextPath()%>/resource/css/roleButton.css?appVer=${appVer}" />
        <link rel="stylesheet" href="<%=request.getContextPath()%>/resource/css/styleless.css?appVer=${appVer}" />
        <!--css included  -->

        <!--js included  -->
        <script src="<%=request.getContextPath()%>/resource/js/jquery-3.2.1.min.js?appVer=${appVer}"></script>
        <script src="<%=request.getContextPath()%>/resource/js/bootstrap.min.js?appVer=${appVer}"></script>
        <script src="<%=request.getContextPath()%>/resource/js/bootstrap-datepicker.js?appVer=${appVer}"></script>
        <script src="<%=request.getContextPath()%>/resource/js/bootstrap-timepicker.js?appVer=${appVer}"></script>
        <script src="<%=request.getContextPath()%>/resource/js/tiles.js?appVer=${appVer}"></script>       
	    <!-- <script src="<%=request.getContextPath()%>/resource/js/less.min.js?appVer=${appVer}"></script>  -->
            <script src="<%=request.getContextPath()%>/resource/js/color.js?appVer=${appVer}"></script>
            <script src="<%=request.getContextPath()%>/resource/js/kendo.all.min.js?appVer=${appVer}"></script>
            <!--js included  -->
            <script>
                $(document).ready(function() {
                    $("#tabstrip").kendoTabStrip();
                });
               
            </script>
    </head>

    <body style="background:#fff !important;">
        <div id="loading-wrapper">
            <div id="loading-text">LOADING</div>
            <div id="loading-content"></div>
        </div>
        <jsp:include page="dashbordSideBar.jsp" />

        <!-- full-container start -->

        <!-- Header start -->

        <div class="header text-center">
            <div class="col-xs-4">
                <div class="pull-left">
                    <%-- <img src="<%=request.getContextPath()%>/resource/images/E_logo.png?appVer=${appVer}" class="logo"> --%>
                        <button type="button" title="Menue" class="menu-toggle btn btn-info btnsm">
                            <i class="fa fa-bars"></i>
                        </button>
                        <a href="tendertiles" title="Go_Back_to_Tiles" class="backTotils btn btn-info btnsm"><i
						class="fa fa-arrow-left"></i></a>
                        <button title="Go_Back_to_List" class="btn btn-info btnsm backTolist"><i class="fa fa-arrow-left"></i></button>
                </div>
            </div>
            <div class="col-xs-4"><img src="<%=request.getContextPath()%>/resource/images/Mahadiscom_Logo.jpg?appVer=${appVer}" class="dash_logo"></div>
            <div class="col-xs-4">
                <div class="pull-right">
                    <div class="dropdown welcome">
                        Welcome |<a class="dropdown-toggle" type="button" id="dropdownMenu1" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true"><span class="glyphicon glyphicon-user userdet"></span>MSCDCL_User<span class="caret"></span></a>

                        <ul class="dropdown-menu" aria-labelledby="dropdownMenu1">
                            <li><a href="vendorRegistration">Profile</a></li>
                            <li><a href="logout">Logout</a></li>
                        </ul>
                    </div>
                </div>
            </div>

        </div>
        <!-- Header end -->
        <div class="full-container">
            <!-- left-side start-->
            <div class="clearfix"></div>
            <div id="mobile_first_container" class="left-side col-md-3 no-marg">         
                <div class="input-group">
                    <div class="input-group-btn search-panel">
                        <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">
                            <span id="search_concept">Filter by</span> <span class="caret"></span>
                        </button>
                        <ul class="dropdown-menu" role="menu">
                            <li><a href="#contains">Code</a></li>
                            <li class="divider"></li>
                            <li><a href="#all">Name</a></li>
                        </ul>
                    </div>
                    <input type="hidden" name="search_param" value="all" id="search_param">
                    <input type="text" class="form-control" name="x" placeholder="Search term...">
                    <span class="input-group-btn">
                    <button class="btn btn-default" type="button"><span class="glyphicon glyphicon-search"></span></button>
                    </span>
                </div>
                <ul id="example" class="nav nav-tabs tabs-left example">
                    <li>
                        <a href="#Master" data-toggle="tab">
                            <div class="col-md-12">
                                <label class="col-xs-6">Account type</label>
                                <label class="col-xs-6"> Manufacturer</label>
                            </div>

                        </a>
                    </li>
                </ul>
                <nav aria-label="Page navigation">
                    <ul class="pagination">
                        <li>
                            <a href="#" aria-label="Previous">
                                <span aria-hidden="true">&laquo;</span>
                            </a>
                        </li>
                        <li><a href="#">1</a></li>
                        <li>
                            <a href="#" aria-label="Next">
                                <span aria-hidden="true">&raquo;</span>
                            </a>
                        </li>
                    </ul>
                </nav>
                <div class="clearfix"></div>
            </div>
            <!-- left-side end-->

            <!-- right-side start-->
            <div id="mobile_second_container" class="right-side col-md-9 no-marg">
                <div class="clearfix"></div>
                <div class="tab-content">
                    <!-- Master tab start-->

                    <div class="tab-pane active in" id="AddUoM">
                        <div class="card">
                             <div class="posrelative" id="example">
                            <div class="TypeOfTender"><div class="btn-group btnmrg" data-toggle="buttons">
							    <label class="btn btn-primary">
									<input type="radio" name="options" id="option3">
									<span class="glyphicon glyphicon-ok"></span> Procurement 
                                </label>
                                <label class="btn btn-primary">
                                     <input type="radio" name="options" id="option3">
                                      <span class="glyphicon glyphicon-ok"></span> Works
                                </label>
                            </div>
                            </div>
                                <div class="demo-section k-content">
                                    <div id="tabstrip" class="Firsttab">
                                        <ul>
                                            <!-- tabs -->
                                            <li class="k-state-active">Tender Bid Opening</li>
                                            <li>Explorer User Prompt</li>
                                            <li>Tender Opening Wizard of Tender </li>
                                        </ul>

                                        <!--fields of field group 1  -->
                                        <div>
                                            <div class="form-group">
                                                <div class="col-sm-12">
                                                    <h4><b>Tender Bid Opening</b></h4>
                                                    <hr>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                             <div class="col-sm-4">
                                                    <div class="form-group">
                                                        <label>Tender Bid Opening On Date</label>
                                                        <div class="input-group date" data-provide="datepicker">
                                                            <input type="text" class="form-control">
                                                            <div class="input-group-addon">
                                                                <span class="glyphicon glyphicon-th"></span>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="col-sm-1">
                                                	<button class="btn btn-default top20">Search</button>
                                                </div>
                                                <div class="col-sm-4">
                                                <label>Search Tenders by Tender Code</label>      
                                                	<div class="input-group">
                 					 				  <input type="hidden" name="search_param" value="all" id="search_param">
                    								  <input type="text" class="form-control" name="x" placeholder="Search term...">
                    								  <span class="input-group-btn">
                    								    <button class="btn btn-default" type="button"><span class="glyphicon glyphicon-search"></span></button>
                   									  </span>
                                                    </div>
                                                </div>
                                            </div>
                                             <div class="col-sm-12">
                                             	<table class="table table-bordered">
                                                        <thead>
                                                            <tr>
                                                                <th scope="col">#</th>
                                                                <th scope="col">First Name</th>
                                                                <th scope="col">Last Name</th>
                                                                <th scope="col">Username</th>
                                                            </tr>
                                                        </thead>
                                                        <tbody>
                                                            <tr>
                                                                <th scope="row">1</th>
                                                                <td>Mark</td>
                                                                <td>Otto</td>
                                                                <td>@mdo</td>
                                                            </tr>
                                                            <tr>
                                                                <th scope="row">2</th>
                                                                <td>Mark</td>
                                                                <td>Otto</td>
                                                                <td>@TwBootstrap</td>
                                                            </tr>                                                            
                                                        </tbody>
                                                    </table>
                                             </div>
                                            <div class="clearfix"></div>
                                        </div>
                                        <!--fields of field group 1  -->
                                        <!--fields of field group 2  -->
                                        <div>
                                            <div class="form-group">
                                                <div class="col-sm-12">
                                                    <h4><b>Explorer User Prompt</b></h4>
                                                    <hr>
                                                </div>
                                            </div>
                                            <div class="form-group">                                                
                                                <div class="col-sm-6">
                                                    <div class="styled-input">
                                                        <input type="text" id="" name="" required />
                                                        <label>Please Enter unique sessionkey Recieved through email<span class="red">*</span></label>
                                                        <span></span>
                                                    </div>
                                                </div>                                                
                                            </div>
                                            
											<div class="form-group">
                                                    <div class="col-sm-12 text-center">
                                                    	<button class="btn btn-default">Save</button>
                                                    	<button class="btn btn-default">Cancle</button>
                                                    </div>                                     
                                            </div>                                            
                                        </div>
                                        <!--fields of field group 2  -->
                                        
                                        <!--fields of field group 3  -->
                                        <div>
                                            <div class="form-group">
                                                <div class="col-sm-12">
                                                    <h4><b>Explorer User Prompt</b></h4>
                                                    <hr>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <div class="col-sm-3">
                                                    <label class="radio-inline" style="margin:0px;">
                                                        <input type="radio" name="optradio">Status
                                                    </label>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <div class="col-sm-4">
                                                    <div class="styled-input">
                                                        <input type="text" id="" name="" required="">
                                                        <label>Comments<span class="red">*</span></label>
                                                        <span></span>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                    <div class="col-sm-12">
                                                    	<button class="btn btn-default">Declare</button>
                                                    	<button class="btn btn-default">Open All Bid</button>
                                                    </div>                                     
                                            </div>
                                             <div class="form-group">
                                                <div class="col-sm-4">
                                                    <div class="styled-input">
                                                        <select id="companyType" name="companyType" required="">
                                                        </select>
                                                        <label>Logged in MAHADISCOMUSERS<span class="red">*</span></label>
                                                        <span></span>
                                                    </div>
                                                 </div>
                                                 <div class="col-sm-4">
                                                    <div class="styled-input">
                                                        <select id="companyType" name="companyType" required="">
                                                        </select>
                                                        <label>Logged in Vendors<span class="red">*</span></label>
                                                        <span></span>
                                                    </div>
                                                 </div>
                                               </div>                                            
                                        </div>
                                        <!--fields of field group 3  -->
                                    </div>

                                </div>
                                <!-- Master tab end-->

                            </div>
                        </div>
                        <!-- right-side end-->
                    </div>
                </div>
            </div>
            <div class="clearfix"></div>

            <div class="footer">@ All Right Reserved E-Auctionapp</div>

    </body>

    </html>