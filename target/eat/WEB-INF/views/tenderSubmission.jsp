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
        <link rel="stylesheet" href="<%=request.getContextPath()%>/resource/css/styleless.css?appVer=${appVer}" />
        <!--css included  -->

        <!--js included  -->
        <script src="<%=request.getContextPath()%>/resource/js/jquery-3.2.1.min.js?appVer=${appVer}"></script>
        <script src="<%=request.getContextPath()%>/resource/js/bootstrap.min.js?appVer=${appVer}"></script>
        <script src="<%=request.getContextPath()%>/resource/js/bootstrap-datepicker.js?appVer=${appVer}"></script>
        <script src="<%=request.getContextPath()%>/resource/js/bootstrap-timepicker.js?appVer=${appVer}"></script>
        <script src="<%=request.getContextPath()%>/resource/js/tiles.js?appVer=${appVer}"></script>
        <%-- 
	    <script src="<%=request.getContextPath()%>/resource/js/less.min.js?appVer=${appVer}"></script> --%>
            <script src="<%=request.getContextPath()%>/resource/js/color.js?appVer=${appVer}"></script>
            <script src="<%=request.getContextPath()%>/resource/js/kendo.all.min.js?appVer=${appVer}"></script>
            <!--js included  -->
            <script>
                $(document).ready(function() {
                    $("#tabstrip").kendoTabStrip();
                    $("#tabstrip2").kendoTabStrip();
                    $("#tabstrip3").kendoTabStrip();
                    $("#tabstrip4").kendoTabStrip();
                    $("#tabstrip5").kendoTabStrip();

                });

                function valueChanged() {
                    debugger;
                    if ($('.Materialvender').is(":checked"))
                        $(".Subvender").show();
                    else
                        $(".Subvender").hide();
                }
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
                <!-- <div id="custom-search-input">
      <div class="input-group col-md-12">
        <input type="text" class="  search-query form-control" placeholder="Search">
        <button class="btn btn-danger" type="button"> <span class=" glyphicon glyphicon-search"></span> </button>
      </div>
    </div> -->
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
                                            <li class="k-state-active">My Tenders</li>
                                            <li>Tender Filling Page</li>
                                            <li>Techno Commercial Bid</li>
                                            <li>GTP Parameter</li>
                                            <li>Delivery Details</li>
                                            <li>ATCR Doc For the Tender</li>
                                            <li>TC Bid Confirmation</li>
                                            <li>Confirmation Letter</li>
                                            <li>Price Bid Details</li>
                                            <li>Item List</li>
                                            <li>Annexure 'B'</li>
                                            <li>Annexure 'I' to SECTION I</li>
                                            <li>Letter</li>
                                            <li>EMD Payment Details</li>
                                            <li>APBR Doc For the Tender</li>
                                            <li>Bid Submission</li>
                                            <li>Digital Signing Of Bid(TCB)</li>
                                            <li>Digital Signing Of Bid(PB)</li>
                                        </ul>

                                        <!--fields of field group 1  -->
                                        <div>
                                            <div class="form-group">
                                                <div class="col-sm-12">
                                                    <h4><b>My Tender</b></h4>
                                                    <hr>
                                                </div>
                                            </div>

                                            <div class="form-group">
                                                <div class="col-sm-4">
                                                    <div class="styled-input">
                                                        <select id="companyType" name="companyType" required>
                                                        </select>
                                                        <label>Select Tender Type<span class="red">*</span></label>
                                                        <span></span>
                                                    </div>
                                                </div>
                                                <div class="col-sm-4">
                                                    <label class="checkbox-inline top40">
                                                        <input type="checkbox" value="">Table</label>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <div class="col-sm-4">
                                                    <button class="btn btn-default">Fill Tender</button>
                                                    <button class="btn btn-default">Download Tender</button>
                                                </div>
                                            </div>
                                            <div class="clearfix"></div>
                                        </div>
                                        <!--fields of field group 1  -->
                                        <!--fields of field group 2  -->
                                        <div>
                                            <div class="form-group">
                                                <div class="col-sm-12">
                                                    <h4><b>Tender Filling Main Page</b></h4>
                                                    <hr>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <div class="col-sm-6">
                                                    <button class="btn btn-default">Download Tender Doc</button>
                                                    <button class="btn btn-default">Download Registeration File</button>
                                                </div>
                                                <div class="col-sm-6">
                                                    <label class="radio-inline" style="margin:0px;">
                                                        <input type="radio" name="optradio">Filling Details
                                                    </label>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <div class="col-sm-12">
                                                    <button class="btn btn-default">Continue</button>
                                                    <button class="btn btn-default">Go to Previous Page</button>
                                                </div>
                                            </div>
                                        </div>
                                        <!--fields of field group 2  -->
                                        <!--fields of field group 3  -->
                                        <div>
                                            <div class="form-group">
                                                <div class="col-sm-12">
                                                    <h4><b>Techno Commercial Bid</b></h4>
                                                    <hr>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <div class="col-sm-3">
                                                    <label class="radio-inline" style="margin:0px;">
                                                        <input type="radio" name="optradio">Filling Details
                                                    </label>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <div class="col-sm-12">
                                                    <button class="btn btn-default">Continue</button>
                                                    <button class="btn btn-default">Go to Previous Page</button>
                                                </div>
                                            </div>
                                        </div>
                                        <!--fields of field group 3  -->
                                        <!--fields of field group 4  -->
                                        <div>
                                            <div class="form-group">
                                                <div class="col-sm-12">
                                                    <h4><b>GTP Parameter</b></h4>
                                                    <hr>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <div class="col-sm-12">
                                                    <label class="checkbox-inline">
                                                        <input type="checkbox" value="">NA</label>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <div class="col-sm-4">
                                                    <div class="styled-input">
                                                        <input type="text" id="" name="" required="">
                                                        <label>Name of manufactures & Company Origin<span class="red">*</span></label>
                                                        <span></span>
                                                    </div>
                                                </div>
                                                <div class="col-sm-4">
                                                    <div class="styled-input">
                                                        <input type="text" id="" name="" required="">
                                                        <label>Comforming to Standard<span class="red">*</span></label>
                                                        <span></span>
                                                    </div>
                                                </div>
                                                <div class="col-sm-4">
                                                    <div class="styled-input">
                                                        <input type="text" id="" name="" required="">
                                                        <label>Service(Indoor/ OutDoor)<span class="red">*</span></label>
                                                        <span></span>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <div class="col-sm-4">
                                                    <div class="styled-input">
                                                        <input type="text" id="" name="" required="">
                                                        <label>Rating:With ONAN Cooling-MVA<span class="red">*</span></label>
                                                        <span></span>
                                                    </div>
                                                </div>
                                                <div class="col-sm-4">
                                                    <div class="styled-input">
                                                        <input type="text" id="" name="" required="">
                                                        <label>Rating:With ONAF Cooling-MVA<span class="red">*</span></label>
                                                        <span></span>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <div class="col-sm-12">
                                                    <button class="btn btn-default">Continue</button>
                                                    <button class="btn btn-default">Go to Main Page</button>
                                                </div>
                                            </div>
                                        </div>
                                        <!--fields of field group 4  -->
                                        <!--fields of field group 5  -->
                                        <div>
                                            <div class="form-group">
                                                <div class="col-sm-12">
                                                    <h4><b>Delivery Details</b></h4>
                                                    <hr>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <div class="col-sm-4">
                                                    <div class="styled-input">
                                                        <input type="text" id="" name="" required="">
                                                        <label>First Lot<span class="red">*</span></label>
                                                        <span></span>
                                                    </div>
                                                </div>
                                                <div class="col-sm-4">
                                                    <div class="styled-input">
                                                        <input type="text" id="" name="" required="">
                                                        <label>First Delivering Month From Letter of Award<span class="red">*</span></label>
                                                        <span></span>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <div class="col-sm-6">
                                                    <div class="styled-input">
                                                        <input type="text" id="" name="" required="">
                                                        <label>Rate Of Assorted Size per Month After Completion of Period Supply<span class="red">*</span></label>
                                                        <span></span>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <div class="col-sm-4">
                                                    <input type="file" class="form-control">
                                                </div>
                                                <div class="col-sm-2">
                                                    <button class="btn btn-default">Save</button>
                                                </div>
                                            </div>
                                        </div>
                                        <!--fields of field group 5  -->
                                        <!--fields of field group 6  -->
                                        <div>
                                            <div class="form-group">
                                                <div class="col-sm-12">
                                                    <h4><b>Addition Techno Commercial Required Doc For the Tender</b></h4>
                                                    <hr>
                                                </div>
                                            </div>
                                            <div class="form-group">
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
                                            </div>
                                            <div class="form-group">
                                                <div class="col-sm-12">
                                                    <button class="btn btn-default">Save</button>
                                                    <button class="btn btn-default">Go to Main Page</button>
                                                </div>
                                            </div>
                                        </div>
                                        <!--fields of field group 6  -->
                                        <!--fields of field group 7  -->
                                        <div>
                                            <div class="form-group">
                                                <div class="col-sm-12">
                                                    <h4><b>Techno Commercial  Bid Confirmation</b></h4>
                                                    <hr>
                                                </div>
                                            </div>
                                        </div>
                                        <!--fields of field group 7  -->
                                        <!--fields of field group 8  -->
                                        <div>
                                            <div class="form-group">
                                                <div class="col-sm-12">
                                                    <h4><b>Confirmation Letter</b></h4>
                                                    <hr>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <div class="col-sm-4">
                                                    <div class="styled-input">
                                                        <input type="text" id="" name="" required />
                                                        <label>Normal Excise Duty-Item<span class="red">*</span></label>
                                                        <span></span>
                                                    </div>
                                                </div>
                                                <div class="col-sm-4">
                                                    <div class="styled-input">
                                                        <input type="text" id="" name="" required />
                                                        <label>Normal Excise Duty-Rate of Excise Duty<span class="red">*</span></label>
                                                        <span></span>
                                                    </div>
                                                </div>
                                                <div class="col-sm-4">
                                                    <div class="styled-input">
                                                        <select id="companyType" name="companyType" required></select>
                                                        <label>Sales TAX/VAT-Tax<span class="red">*</span></label>
                                                        <span></span>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <div class="col-sm-12">
                                                    <button class="btn btn-default">Save</button>
                                                    <button class="btn btn-default">Go to Main Page</button>
                                                </div>
                                            </div>
                                        </div>
                                        <!--fields of field group 8  -->
                                        <!--fields of field group 9  -->
                                        <div>
                                            <div class="form-group">
                                                <div class="col-sm-12">
                                                    <h4><b>Price Bid Details</b></h4>
                                                    <hr>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <div class="col-sm-2">
                                                    <label class="radio-inline" style="margin-top:25px;">
                                                        <input type="radio" name="optradio">Price Bid Details 
                                                    </label>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <div class="col-sm-12">
                                                    <button class="btn btn-default">Continue</button>
                                                    <button class="btn btn-default">Go to Main Page</button>
                                                </div>
                                            </div>
                                        </div>
                                        <!--fields of field group 9  -->
                                        <!--fields of field group 10  -->
                                        <div>
                                            <div class="form-group">
                                                <div class="col-sm-12">
                                                    <h4><b>Item List</b></h4>
                                                    <hr>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <div class="col-sm-4">
                                                    <label class="checkbox-inline">
                                                        <input type="checkbox" name="">Item Decription
                                                    </label>
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
                                            <div class="form-group">
                                                <div class="col-sm-12">
                                                	<button class="btn btn-default">Fill Price</button>
                                                    <button class="btn btn-default">Continue</button>
                                                    <button class="btn btn-default">Go to Main Page</button>
                                                </div>
                                            </div>
                                        </div>
                                        <!--fields of field group 10  -->

                                        <!--fields of field group 11  -->
                                        <div>
                                            <div class="form-group">
                                                <div class="col-sm-12">
                                                    <h4><b>Annexure 'B' (Price schedule)</b></h4>
                                                    <hr>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <div class="col-sm-4">
                                                    <div class="styled-input">
                                                        <input type="text" id="" name="" required="">
                                                        <label>Desc Of Material<span class="red">*</span></label>
                                                        <span></span>
                                                    </div>
                                                </div>
                                                <div class="col-sm-4">
                                                    <div class="styled-input">
                                                        <input type="text" id="" name="" required="">
                                                        <label>Item Code<span class="red">*</span></label>
                                                        <span></span>
                                                    </div>
                                                </div>
                                                <div class="col-sm-4">
                                                    <div class="styled-input">
                                                        <input type="text" id="" name="" required="">
                                                        <label>Required quantity<span class="red">*</span></label>
                                                        <span></span>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <div class="col-sm-4">
                                                    <div class="styled-input">
                                                        <input type="text" id="" name="" required="">
                                                        <label>Quantity Offered<span class="red">*</span></label>
                                                        <span></span>
                                                    </div>
                                                </div>
                                                <div class="col-sm-4">
                                                    <div class="styled-input">
                                                        <input type="text" id="" name="" required="">
                                                        <label>Unit<span class="red">*</span></label>
                                                        <span></span>
                                                    </div>
                                                </div>
                                                <div class="col-sm-4">
                                                <div class="styled-input">
                                                        <input type="text" id="" name="" title="Per Unit Ex-Group Price Including Packaging But Excluding Excise duty and Taxes(In Rs)" required="">
                                                        <label>Per Unit Ex-Group Price <span class="red">*</span></label>
                                                        <span></span>
                                                    </div>
                                                </div>
                                            </div>
                                            
                                             <div class="form-group">
                                               <div class="col-sm-4">                                                    
                                                    <div class="styled-input">
                                                        <input type="text" id="" name="" required="">
                                                        <label>Freight charges per unit(in Rs)</label>
                                                        <span></span>
                                                    </div>
                                                </div>    
                                                <div class="col-sm-4">
                                                    <div class="styled-input">
                                                        <input type="text" id="" name="" required="">
                                                        <label>Transit Insurance Charges Per Unit(In Rs)<span class="red">*</span></label>
                                                        <span></span>
                                                    </div>
                                                </div>
                                                <div class="col-sm-4">
                                                    <div class="styled-input">
                                                        <input type="text" id="" name="" required="">
                                                        <label>Excise-Duty in ex-Works Price (in %)<span class="red">*</span></label>
                                                        <span></span>
                                                    </div>
                                                </div>                                                
                                            </div> 
                                            <div class="form-group">
                                            <div class="col-sm-4">
                                                    <div class="styled-input">
                                                        <input type="text" id="" name="" required="">
                                                        <label>Compute Excise Duty<span class="red">*</span></label>
                                                        <span></span>
                                                    </div>
                                                </div>
                                                <div class="col-sm-4">
                                                    <div class="styled-input">
                                                        <input type="text" id="" name="" required="" title="Central sales Tax/VAT For outside for Outside State Transaction On against Declaration Form 'C' OR Maharashtra State Sales TAX/VAT for within state on Transaction (in %)">
                                                        <label>Central sales Tax/VAT<span class="red">*</span></label>
                                                        <span></span>
                                                    </div>
                                                </div>
                                                <div class="col-sm-4">
                                                    <div class="styled-input">
                                                        <input type="text" id="" name="" required="">
                                                        <label>Compute Sales TAX<span class="red">*</span></label>
                                                        <span></span>
                                                    </div>
                                                </div>
                                            </div>  
                                            <div class="form-group">
                                                <div class="col-sm-4">
                                                    <div class="styled-input">
                                                        <input type="text" id="" name="" required="" title="Free Door Delivery Price in Rs per Unit on Road Upto Destination/Store/Sub-Station (in Rs)">
                                                        <label>Free Door Delivery Price in Rs<span class="red">*</span></label>
                                                        <span></span>
                                                    </div>
                                                </div>
                                                <div class="col-sm-4">
                                                    <div class="styled-input">
                                                        <input type="text" title="" id="" name="" required="" title="Free Door Delivery Price in Rs per Unit on Road Upto Destination/Store/Sub-Station (in Words)">
                                                        <label>Free Door Delivery Price in Rs </label>
                                                        <span></span>
                                                    </div>
                                                </div>
                                                <div class="col-sm-4">
                                                    <div class="styled-input">
                                                        <input type="text" id="" name="" required="">
                                                        <label>Excise-Duty in ex-Works Price (in %)<span class="red">*</span></label>
                                                        <span></span>
                                                    </div>
                                                </div>                                                
                                            </div>
                                            <div class="form-group">
                                                <div class="col-sm-12">
                                                    <button class="btn btn-default">Save</button>
                                                    <button class="btn btn-default">Go to Previous Page</button>
                                                </div>
                                            </div>  
                                        </div>
                                        <!--fields of field group 11  -->
                                        <!--fields of field group 12  -->
                                        <div>
                                            <div class="form-group">
                                                <div class="col-sm-12">
                                                    <h4><b>Annexure 'I' to SECTION I</b></h4>
                                                    <hr>
                                                </div>
                                            </div>
                                            
                                        </div>
                                        <!--fields of field group 12  -->
                                        <!--fields of field group 13  -->
                                        <div>
                                            <div class="form-group">
                                                <div class="col-sm-12">
                                                    <h4><b>Letter</b></h4>
                                                    <hr>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <div class="col-sm-12">
                                                    <label class="checkbox-inline" style="margin-top:25px;">
                                                        <input type="checkbox">All The Above Details are True and I/We accept this details.
                                                    </label>
                                                </div>
                                            </div>
                                             <div class="form-group">
                                                <div class="col-sm-12">
                                                    <button class="btn btn-default">Pay EMD</button>
                                                    <button class="btn btn-default">Continue</button>
                                                    <button class="btn btn-default">Goto Main Page</button>
                                                </div>
                                            </div>
                                        </div>
                                        <!--fields of field group 13  -->
                                        <!--fields of field group 14  -->
                                        <div>
                                            <div class="form-group">
                                                <div class="col-sm-12">
                                                    <h4><b>EARNEST MONET DEPOSIT (EMD) payment details</b></h4>
                                                    <hr>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                               <div class="col-sm-4">                                                    
                                                    <div class="styled-input">
                                                        <select id="" name="" required=""></select>
                                                        <label>Type of Payment</label>
                                                        <span></span>
                                                    </div>
                                                </div>    
                                                <div class="col-sm-4">
                                                    <label class="checkbox-inline" style="margin-top:25px;">
                                                        <input type="checkbox">Mode Of Payment
                                                    </label>
                                                </div>
                                                <div class="col-sm-4">
                                                    <div class="form-group">
                                                        <label>DD Date<span class="red">*</span></label>
                                                        <div class="input-group date" data-provide="datepicker">
                                                            <input type="text" class="form-control">
                                                            <div class="input-group-addon">
                                                                <span class="glyphicon glyphicon-th"></span>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>                                                
                                            </div> 
                                            <div class="form-group">
                                               <div class="col-sm-4">                                                    
                                                    <div class="styled-input">
                                                        <select id="" name="" required=""></select>
                                                        <label>Demand Draft Number</label>
                                                        <span></span>
                                                    </div>
                                                </div>  
                                                <div class="col-sm-4">                                                    
                                                    <div class="styled-input">
                                                        <select id="" name="" required=""></select>
                                                        <label>MICR Code</label>
                                                        <span></span>
                                                    </div>
                                                </div>
                                                <div class="col-sm-4">                                                    
                                                    <div class="styled-input">
                                                        <select id="" name="" required=""></select>
                                                        <label>Amount</label>
                                                        <span></span>
                                                    </div>
                                                </div>                                         
                                            </div> 
                                            <div class="form-group">
                                               <div class="col-sm-4">                                                    
                                                    <div class="styled-input">
                                                        <select id="" name="" required=""></select>
                                                        <label>Bank Name</label>
                                                        <span></span>
                                                    </div>
                                                </div>  
                                                <div class="col-sm-4">                                                    
                                                    <div class="styled-input">
                                                        <select id="" name="" required=""></select>
                                                        <label>Branch Name</label>
                                                        <span></span>
                                                    </div>
                                                </div>                                                                                         
                                            </div> 
                                            <div class="form-group">
                                                <div class="col-sm-12">
                                                    <div class="styled-input">
                                                        <button class="btn btn-default">Save</button>
                                                        <button class="btn btn-default">Cancle</button>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <!--fields of field group 14  -->

                                        <!--fields of field group 15  -->
                                        <div>
                                            <div class="form-group">
                                                <div class="col-sm-12">
                                                    <h4><b>Addition PriceBid Required Doc For the Tender</b></h4>
                                                    <hr>
                                                </div>
                                            </div>
                                             <div class="form-group">
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
                                             </div>   
                                            <div class="form-group">
                                                <div class="col-sm-12">
                                                    <div class="styled-input">
                                                        <button class="btn btn-default">Save</button>
                                                        <button class="btn btn-default">Go to Previous Page</button>
                                                    </div>
                                                </div>
                                            </div>                                            
                                        </div>
                                        <!--fields of field group 15  -->
                                        <!--fields of field group 16  -->
                                        <div>
                                            <div class="form-group">
                                                <div class="col-sm-12">
                                                    <h4><b>Bid Submission</b></h4>
                                                    <hr>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <div class="col-sm-6">
                                                    <label class="radio-inline">
                                                        <input type="radio" name="">Select the Bid You want to Submit
                                                    </label>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <div class="col-sm-12">
                                                    <div class="styled-input">
                                                        <button class="btn btn-default">Save</button>
                                                        <button class="btn btn-default">Goto Main Page</button>
                                                    </div>
                                                </div>
                                            </div>                                             
                                        </div>
                                        <!--fields of field group 16  -->
                                        <!--fields of field group 17  -->
                                        <div>
                                            <div class="form-group">
                                                <div class="col-sm-12">
                                                    <h4><b>Digital Signing Of Bid (TECHNO-COMMERCIAL BID)</b></h4>
                                                    <hr>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <div class="col-sm-12">
                                                        <button class="btn btn-default"><span class="glyphicon glyphicon-download"></span>Download Techno-Commercial Bid</button>
                                                        
                                                    </div>
                                                    </div>
                                                     <div class="form-group">
                                                <div class="col-sm-12">
                                                    <span class="fileselector">
       													  <label class="btn btn-default" for="upload-file-selector">
           												  <input id="upload-file-selector" type="file">
          												  <span class="glyphicon glyphicon-download"></span>Upload Signed Pdf of Techno-Commercial Bid
       													  </label>
   														 </span>
                                                </div>
                                            </div> 
                                            <div class="form-group">
                                                <div class="col-sm-12">
                                                    <div class="styled-input">
                                                        <button class="btn btn-default">Save</button>
                                                        <button class="btn btn-default">Go to Previous Page</button>
                                                    </div>
                                                </div>
                                            </div>                                             
                                        </div>
                                        <!--fields of field group 17  -->
                                        <!--fields of field group 18  -->
                                        <div>
                                            <div class="form-group">
                                                <div class="col-sm-12">
                                                    <h4><b>Digital Signing Of Bid (PRICE BID)</b></h4>
                                                    <hr>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <div class="col-sm-12">
                                                        <button class="btn btn-default"><span class="glyphicon glyphicon-download"></span>Download PRICE BID</button>
                                                        
                                                    </div>
                                                    </div>
                                                     <div class="form-group">
                                                <div class="col-sm-12">
                                                    <span class="fileselector">
       													  <label class="btn btn-default" for="upload-file-selector">
           												  <input id="upload-file-selector" type="file">
          												  <span class="glyphicon glyphicon-download"></span>Upload Signed Pdf of PRICE BID Bid
       													  </label>
   														 </span>
                                                </div>
                                            </div> 
                                            <div class="form-group">
                                                <div class="col-sm-12">
                                                    <div class="styled-input">
                                                        <button class="btn btn-default">Save</button>
                                                        <button class="btn btn-default">Go to Previous Page</button>
                                                    </div>
                                                </div>
                                            </div>                                             
                                        </div>
                                        <!--fields of field group 18  -->
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