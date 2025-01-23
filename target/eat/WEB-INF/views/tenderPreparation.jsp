<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@taglib uri = "http://www.springframework.org/tags/form" prefix = "sf"%>
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
        <script src="<%=request.getContextPath()%>/resources/${appMode}/commons/js/springform.js?appVer=${appVer}"></script>
        <script src="<%=request.getContextPath()%>/resources/${appMode}/commons/js/utility.js?appVer=${appVer}"></script>
        <script src="<%=request.getContextPath()%>/resources/${appMode}/commons/js/loadList.js?appVer=${appVer}"></script>
        <script src="<%=request.getContextPath()%>/resources/${appMode}/transaction/js/tenderPreparation.js?appVer=${appVer}"></script>
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
        <!-- <div id="loading-wrapper">
            <div id="loading-text">LOADING</div>
            <div id="loading-content"></div>
             </div> --> 
   
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
                <ul id="tahdrList" class="nav nav-tabs tabs-left tahdrList">
                   <c:forEach var="tahdr" items="${tahdrList}" varStatus="count">
                    <li class="tahdr" id='${tahdr.tahdrId}'>
                        <a data-toggle="tab">
                            <div class="col-md-12">
                                <label class="col-xs-6">${tahdr.tahdrCode}</label>
                                <label class="col-xs-6">${tahdr.tahdrTypeCode}</label>
                                <label class="col-xs-6">${tahdr.bidTypeCode}</label>
                                <label class="col-xs-6">${tahdr.department.name}</label>
                            </div>

                        </a>
                    </li>
                    </c:forEach>
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
                                            <li class="k-state-active">Tender Base Info</li>
                                            <li class="tahdrDetail">Tender Details</li>
                                            <li class="impDates">Important Dates</li>
                                            <li>Tender Standard Docs</li>
                                            <li>Tender Custom Docs</li>
                                            <li>Ordering of Tender Docs</li>
                                            <li class="getTahdrMaterialList">List of items in Tender</li>
                                            <!-- <li class="tahdrMaterial">Item in Tender</li>
                                            <li class="sekectMaterial">Select Item Form</li> -->
                                            <li>GTP Parameter</li>
                                            <li>Required Docs List</li>
                                            <li>Required Docs Details</li>
                                            <li>Tender Prepare Confirmation</li>
                                            <li>View Tender</li>
                                        </ul>

                                        <!--fields of field group 1  -->
                                        
                                        <div>
                                        <sf:form id="TAHDR" action="createtahdr" method="POST" autocomplete="off" modelAttribute="tahdr">
                                            <div class="form-group">
                                                <div class="col-sm-12">
                                                    <h4><b>Tender Type Details</b></h4>
                                                    <hr>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <div class="col-sm-4">
                                                    <div class="styled-input">
                                                    	<input type="hidden" class="t_tahdr_id" name="tahdrId">
                                                        <input type="text" id="tahdrCode" name="tahdrCode" value="" required />
                                                        <label>Tender Code<span class="red">*</span></label>
                                                        <span></span>
                                                    </div>
                                                </div>
                                                <div class="col-sm-4">
                                                    <div class="styled-input">
                                                        <select id="tahdrType" class="tahdrTypeCode" name="tahdrTypeCode" required>
                                                        </select>
                                                        <label>Tender Type<span class="red">*</span></label>
                                                        <span></span>
                                                    </div>
                                                </div>

                                                <div class="col-sm-4">
                                                    <div class="styled-input">
                                                        <select id="department" class="departmentList" name="department.departmentId" required>
                                                        </select>
                                                        <label>Department<span class="red">*</span></label>
                                                        <span></span>
                                                    </div>
                                                </div>
                                                <div class="col-sm-4">
                                                    <div class="styled-input">
                                                        <select id="bidTypeCode" class="bidTypeCode" name="bidTypeCode" required>
                                                        </select>
                                                        <label>Bid Type<span class="red">*</span></label>
                                                        <span></span>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="clearfix"></div>
                                            <div class="col-sm-12 text-center positionABS" style="margin-bottom:10px;">
		                                        <button class="btn-all btn btn-info k-tabstrip-prev btnPrevious pull-left">Previous</button>
		                                        <button class="btn btn-info createTahdr">Save</button>
		                                        <button class="btn-all btn btn-info k-tabstrip-next btnNext pull-right">Next</button>
		                                    </div>
                                        	</sf:form>
                                        </div>
                                        
                                        <!--fields of field group 1  -->
                                        <!--fields of field group 2  -->
                                        <div>
                                        <sf:form action="saveTahdrDetails" id="TAHDRDetail" modelAttribute="tahdrDetail">
                                            <div class="form-group">
                                                <div class="col-sm-12">
                                                	<input type="hidden" class="t_tahdr_id" name="tahdr.tahdrId">
                                                    <input type="hidden" class="tahdrDetailId" name="tahdrDetailId">
                                                    <h4><b>Tender Details</b></h4>
                                                    <hr>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <div class="col-sm-4">
                                                    <div class="styled-input">
                                                        <input type="text" id="estimatedCost" name="estimatedCost" required />
                                                        <label>Estimated Cost (in Rs in Lakhs)<span class="red">*</span></label>
                                                        <span></span>
                                                    </div>
                                                </div>
                                                <div class="col-sm-4">
                                                    <div class="styled-input">
                                                        <input type="text" id="tahdrFees" name="tahdrFees" required />
                                                        <label>Tender Fees [in Rupees]<span class="red">*</span></label>
                                                        <span></span>
                                                    </div>
                                                </div>
												<div class="col-sm-4">
                                                    <div class="styled-input">
                                                        <select id="pricingProcCode" name="pricingProcCode" required>
                                                        </select>
                                                        <label>Basis of Prices<span class="red">*</span></label>
                                                        <span></span>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <div class="col-sm-4">
                                                    <div class="styled-input">
                                                        <input type="text" id="deliveryDuration" name="deliveryDuration" required />
                                                        <label>Delivery Duration[In months]<span class="red">*</span></label>
                                                        <span></span>
                                                    </div>
                                                </div>
                                                <!-- <div class="col-sm-4">
                                                    <div class="styled-input">
                                                        <input type="text" id="" name="" required />
                                                        <label>Materials Description<span class="red">*</span></label>
                                                        <span></span>
                                                    </div>
                                                </div> -->
                                                <div class="col-sm-4">
                                                    <div class="styled-input">
                                                        <input type="text" id="contactEmailId" name="contactEmailId" required />
                                                        <label>Contact Email-Id<span class="red">*</span></label>
                                                        <span></span>
                                                    </div>
                                                </div>
                                                <div class="col-sm-4">
                                                    <div class="styled-input">
                                                        <input type="text" id="commencementPeriod" name="commencementPeriod" required />
                                                        <label>Commencment Period within<span class="red">*</span></label>
                                                        <span></span>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <div class="col-sm-4">
                                                    <label class="checkbox-inline">
                                                        <input type="checkbox" name="isDeviation" id="isDeviation">Call For Deviation
                                                    </label>
                                                </div>
                                                <div class="col-sm-4">
                                                    <label class="checkbox-inline">
                                                        <input type="checkbox" name="isAnnexureC1" id="isAnnexureC1">Is C1 Applicable
                                                    </label>
                                                </div>
                                                <div class="col-sm-4">
                                                	<div class="form-group">
                                                    <label class="checkbox-inline">
                                                        <input type="checkbox" id="isSetC1Later" name="isSetC1Later">Set C1 Date Later</label>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <div class="col-sm-4">
                                                    <label class="checkbox-inline">
                                                        <input type="checkbox" name="isBiennialContractRate" id="isBiennialContractRate">Is Tender Biennial Contract rate Basis
                                                    </label>
                                                </div>
                                                <div class="col-sm-4">
                                                    <div class="styled-input">
                                                        <input type="text" id="minQuantity" name="minQuantity" required />
                                                        <label>Minimum % of Offered Quantity<span class="red">*</span></label>
                                                        <span></span>
                                                    </div>
                                                </div>
                                            </div>
                                            
                                            <div class="col-sm-12 text-center positionABS" style="margin-bottom:10px;">
		                                        <button class="btn-all btn btn-info k-tabstrip-prev btnPrevious pull-left">Previous</button>
		                                        <button class="btn btn-info saveTahdrDetails">Save</button>
		                                        <button class="btn-all btn btn-info k-tabstrip-next btnNext pull-right">Next</button>
		                                    </div>
                                            </sf:form>
                                        </div>
                                        <!--fields of field group 2  -->
                                        <!--fields of field group 3  -->
                                        <div>
                                        <sf:form action="saveTahdrDates" id="TAHDRDetail" modelAttribute="tahdrDetail" >
                                            <div class="form-group">
                                                <div class="col-sm-12">
                                                	<input type="hidden" class="t_tahdr_id" name="tahdr.tahdrId">
                                                    <input type="hidden" class="tahdrDetailId" name="tahdrDetailId">
                                                    <h4><b>Important Dates</b></h4>
                                                    <hr>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <div class="col-sm-4">
                                                    <div class="form-group">
                                                        <label>Last Tender Submission date<span class="red">*</span></label>
                                                        <div class="input-group date" data-provide="datepicker">
                                                            <input type="text" class="form-control" id="lastSubmissionDate" name="lastSubmissionDate" ><!-- name="lastSubmissionDate" -->
                                                            <div class="input-group-addon">
                                                                <span class="glyphicon glyphicon-th"></span>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="col-sm-4">
                                                    <div class="form-group">
                                                        <label>Last C-1 Submission Date<span class="red">*</span></label>
                                                        <div class="input-group date" data-provide="datepicker">
                                                            <input type="text" class="form-control" id="lastC1SubmissionDate" name="lastC1SubmissionDate" > <!-- name="lastC1SubmissionDate" -->
                                                            <div class="input-group-addon">
                                                                <span class="glyphicon glyphicon-th"></span>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="col-sm-4">
		                                                    <label>Tender Validity<span class="red">*</span></label>
		                                                    <div class="input-group date" data-provide="datepicker">
		                                                            <input type="text" class="form-control" id="tahdrValidity" name="tahdrValidity"><!-- name="tahdrValidity" -->
		                                                            <div class="input-group-addon">
		                                                                <span class="glyphicon glyphicon-th"></span>
		                                                            </div>
		                                                    </div>
	                                            </div>
                                            </div>
                                            <div class="form-group">
                                                <div class="col-sm-4">
                                                    <div class="form-group">
                                                        <label>Technical Bid Start date<span class="red">*</span></label>
                                                        <div class="input-group date" data-provide="datepicker">
                                                            <input type="text" class="form-control" id="technicalBidFromDate" name="technicalBidFromDate"><!-- name="lastSubmissionDate" -->
                                                            <div class="input-group-addon">
                                                                <span class="glyphicon glyphicon-th"></span>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="col-sm-4">
                                                    <div class="form-group">
                                                        <label>Technical Bid End date<span class="red">*</span></label>
                                                        <div class="input-group date" data-provide="datepicker">
                                                            <input type="text" class="form-control" id="technicalBidToDate" name="technicalBidToDate"> <!-- name="lastC1SubmissionDate" -->
                                                            <div class="input-group-addon">
                                                                <span class="glyphicon glyphicon-th"></span>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="col-sm-4">
                                                    <div class="form-group">
                                                        <label>Technical Bid Openning date<span class="red">*</span></label>
                                                        <div class="input-group date" data-provide="datepicker">
                                                            <input type="text" class="form-control" id="techBidOpenningDate" name="techBidOpenningDate"> <!-- name="lastC1SubmissionDate" -->
                                                            <div class="input-group-addon">
                                                                <span class="glyphicon glyphicon-th"></span>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <div class="col-sm-4">
                                                    <div class="form-group">
                                                        <label>Commercial Bid Start date<span class="red">*</span></label>
                                                        <div class="input-group date" data-provide="datepicker">
                                                            <input type="text" class="form-control" id="commercialBidFromDate" name="commercialBidFromDate"><!-- name="lastSubmissionDate" -->
                                                            <div class="input-group-addon">
                                                                <span class="glyphicon glyphicon-th"></span>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="col-sm-4">
                                                    <div class="form-group">
                                                        <label>Commercial Bid End date<span class="red">*</span></label>
                                                        <div class="input-group date" data-provide="datepicker">
                                                            <input type="text" class="form-control" id="commercialBidToDate" name="commercialBidToDate"> <!-- name="lastC1SubmissionDate" -->
                                                            <div class="input-group-addon">
                                                                <span class="glyphicon glyphicon-th"></span>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="col-sm-4">
                                                    <div class="form-group">
                                                        <label>Commercial Bid Openning date<span class="red">*</span></label>
                                                        <div class="input-group date" data-provide="datepicker">
                                                            <input type="text" class="form-control" id="commercialBidOpenningDate" name="commercialBidOpenningDate"> <!-- name="lastC1SubmissionDate" -->
                                                            <div class="input-group-addon">
                                                                <span class="glyphicon glyphicon-th"></span>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                            <!-- <div class="form-group">
                                                <div class="col-sm-3">
                                                    <label class="checkbox-inline top20">
                                                        <input type="checkbox" value="">Price Bid Will Be set Later</label>
                                                </div>

                                                <div class="col-sm-3">
                                                    <div class="form-group">
                                                        <label>Anexure C-1 Date <span class="red">*</span></label>
                                                        <div class="input-group date" data-provide="datepicker">
                                                            <input type="text" class="form-control">
                                                            <div class="input-group-addon">
                                                                <span class="glyphicon glyphicon-th"></span>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="col-sm-3">
                                                    <div class="form-group">
                                                        <label>Anexure C-1 Time<span class="red">*</span></label>
                                                        <div class="input-group bootstrap-timepicker timepicker">
                                                            <input type="text" class="form-control input-small timepicker">
                                                            <span class="input-group-addon"><i class="glyphicon glyphicon-time"></i></span>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="col-sm-3">
                                                    <label class="checkbox-inline top20">
                                                        <input type="checkbox" value="">Anexure C-1-Will be set Later</label>
                                                </div>
                                            </div> -->
                                            <div class="col-sm-12 text-center positionABS" style="margin-bottom:10px;">
		                                        <button class="btn-all btn btn-info k-tabstrip-prev btnPrevious pull-left">Previous</button>
		                                        <button class="btn btn-info formSubmit">Save</button>
		                                        <button class="btn-all btn btn-info k-tabstrip-next btnNext pull-right">Next</button>
		                                    </div>
		                                    </sf:form>
                                        </div>
                                        <!--fields of field group 3  -->
                                        <!--fields of field group 4  -->
                                        <div>
                                            <div class="form-group">
                                                <div class="col-sm-12">
                                                    <h4><b>Select Tender Standard Documents</b></h4>
                                                    <hr>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <div class="col-sm-12">
                                                    <label class="checkbox-inline">
                                                        <input type="checkbox" value="">ST1</label>
                                                    <label class="checkbox-inline">
                                                        <input type="checkbox" value="">ST2</label>
                                                    <label class="checkbox-inline">
                                                        <input type="checkbox" value="">ST3</label>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <div class="col-sm-12">
                                                    <button class="btn btn-default">Preview</button>
                                                </div>
                                            </div>
                                        </div>
                                        <!--fields of field group 4  -->
                                        <!--fields of field group 5  -->
                                        <div>
                                            <div class="form-group">
                                                <div class="col-sm-12">
                                                    <h4><b>Select Tender Custom Docs</b></h4>
                                                    <hr>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <div class="col-sm-12">
                                                    <label class="checkbox-inline">
                                                        <input type="checkbox" value="">CM1</label>
                                                    <label class="checkbox-inline">
                                                        <input type="checkbox" value="">CM2</label>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <div class="col-sm-4">
                                                    <input type="file" class="form-control">
                                                </div>
                                                <div class="col-sm-2">
                                                    <button class="btn btn-default">Download</button>
                                                </div>
                                            </div>
                                        </div>
                                        <!--fields of field group 5  -->
                                        <!--fields of field group 6  -->
                                        <div>
                                            <div class="form-group">
                                                <div class="col-sm-12">
                                                    <h4><b>Ordering of Tender Docs</b></h4>
                                                    <hr>
                                                </div>
                                            </div>

                                            <div class="form-group">
                                                <div class="col-sm-4">
                                                    <button class="btn btn-default">Move Up</button>
                                                    <button class="btn btn-default">Move Down</button>
                                                </div>
                                            </div>
                                        </div>
                                        <!--fields of field group 6  -->
                                        <!--fields of field group 7  -->
                                        <div>
	                                        <div id="tahdrMaterialList">
	                                            <div class="form-group">
	                                                <div class="col-sm-12">
	                                                    <h4><b>List of items in Tender</b></h4>
	                                                    <hr>
	                                                </div>
	                                            </div>
	                                            <div class="col-sm-12">
	                                                <div class="form-group">
	
	                                                    <button class="btn btn-default"><span class="glyphicon glyphicon-plus-sign"></span>Add GTP</button>
	                                                    <button class="btn btn-default"><span class="glyphicon glyphicon-pencil"></span>Create Parts</button>
	                                                    <button class="btn btn-default addTahdrMaterial"><span class="glyphicon glyphicon-plus-sign"></span>Add Item</button>
	                                                    <button class="btn btn-default editTahdrMaterial"><span class="glyphicon glyphicon-pencil"></span>Edit Item</button>
	                                                    <button class="btn btn-default deleteTahdrMaterial"><span class="glyphicon glyphicon-trash"></span>Delete Item</button>
	                                                </div>
	
	                                                <table id="tahdrMaterialTable" class="table table-bordered">
	                                                    <thead>
	                                                        <tr>
	                                                        	<th></th>
	                                                            <th>Material Code</th>
	                                                            <th>Unit</th>
	                                                            <th>Description</th>
	                                                            <th>Quantity</th>
	                                                            <th>SpecVersion</th>
	                                                            <th>MaterialType</th>
	                                                        </tr>
	                                                    </thead>
	                                                    <tbody>
	                                                        <tr></tr>
	                                                    </tbody>
	                                                </table>
	                                            </div>
	                                        </div>
	                                        <div id="tahdrMaterialDiv" style="display:none;">
		                                        <sf:form action="saveTahdrMaterial" id="saveTahdrMaterialForm" modelAttribute="tahdrMaterial">
		                                            <div class="form-group">
		                                                <div class="col-sm-12">
		                                                	<input type="hidden" id="tahdrMaterialId" name="tahdrMaterialId">
		                                                	<input type="hidden" class="tahdrDetailId" name="tahdrDetail.tahdrDetailId">
		                                                    <h4><b>Material in Tender</b></h4>
		                                                    <hr>
		                                                </div>
		                                            </div>
		                                            <div class="form-group" >
		                                            	<div class="col-sm-4">
		                                                    <div class="styled-input">
		                                                        <select id="selectedMaterialId" name="material.materialId" required>
		                                                        <option>Select Material</option>
		                                                        </select>
		                                                        <label>Material<span class="red">*</span></label>
		                                                        <button class="searchMaterial"><span class="glyphicon glyphicon-search"></span>
		                                                        </button>
	                                                    	</div>
		                                            	</div>
		                                            	<div class="col-sm-4">
		                                                    <div class="styled-input">
		                                                        <input type="text" id="selectedMaterialUom" disabled name="materialUom" required />
		                                                        <label>UOM<span class="red">*</span></label>
		                                                        <span></span>
		                                                    </div>
		                                                </div>
		                                                <div class="col-sm-4">
		                                                    <div class="styled-input">
		                                                        <input type="text" id="selectedMaterialDesc" name="materialDescription" required />
		                                                        <label>Material Description<span class="red">*</span></label>
		                                                        <span></span>
		                                                    </div>
		                                                </div>
		                                            </div>
		                                            <div class="form-group">
		                                            	<div class="col-sm-3">
		                                                    <div class="styled-input">
		                                                        <input type="text" id="materialQuantity" name="quantity" required />
		                                                        <label>Quantity<span class="red">*</span></label>
		                                                        <span></span>
		                                                    </div>
		                                                </div>
		                                                <div class="col-sm-3">
		                                                    <label class="radio-inline">
		                                                        <input type="radio" name="aboutSpecCode" class="aboutSpecCode" value="inc">Include Specification
		                                                    </label>
		                                                    <label class="radio-inline">
		                                                        <input type="radio" name="aboutSpecCode" class="aboutSpecCode" value="ref">Refer Specification
		                                                    </label>
		                                                </div>
		                                                <div class="col-sm-3">
		                                                    <div class="styled-input">
		                                                        <input type="text" id="specVersion" name="specVersion" disabled="disabled">
		                                                        <label>Specification version Of Item<span class="red">*</span></label>
		                                                        <span></span>
		                                                    </div>
		                                                </div>
		                                                <div class="col-sm-3">
		                                                    <label class="radio-inline">Material Type
		                                                        <input type="radio" name="materialTypeCode" value="single" checked>Single
		                                                        <input type="radio" name="materialTypeCode" value="bom">BOM
		                                                    </label>
		                                                </div>
														
		                                            </div>
		                                            <div class="col-sm-12 text-center positionABS" style="margin-bottom:10px;">
				                                        <button class="btn-all btn btn-info k-tabstrip-prev btnPrevious pull-left">Previous</button>
				                                        <button class="btn btn-info saveTahdrMaterial">Save</button>
				                                        <button class="btn btn-info cancelTahdrMaterial">cancel</button>
				                                        <button class="btn-all btn btn-info k-tabstrip-next btnNext pull-right">Next</button>
				                                    </div>
		                                        </sf:form>
                                            </div>
                                            <div id="selectMaterialDiv" style="display:none;">
	                                            <div class="form-group">
	                                                <div class="col-sm-12">
	                                                    <h4><b>Select Material Form</b></h4>
	                                                    <hr>
	                                                </div>
	                                            </div>
	                                            <div class="form-group">
	                                                <div class="col-sm-2">
	                                                    <label class="radio-inline" style="margin-top:25px;">
	                                                        <input type="radio" name="optradio">Search By
	                                                    </label>
	                                                </div>
	                                                <div class="col-sm-2">
	                                                    <div class="styled-input">
	                                                        <button class="btn btn-default">Go>></button>
	                                                    </div>
	                                                </div>
	                                            </div>
	                                            <div class="form-group">
	                                                <div class="col-sm-4">
	                                                    <div class="styled-input">
	                                                        <select id="srchMaterialGroup" name="srchMaterialGroup" class="materialGroupList" required></select>
	                                                        <label>Select Material Group<span class="red">*</span></label>
	                                                        <span></span>
	                                                    </div>
	                                                </div>
	                                                <div class="col-sm-4">
	                                                    <div class="styled-input">
	                                                        <select id="srchMaterialSubGroup" name="srchMaterialSubGroup" class="materialSubGroupList" required></select>
	                                                        <label>Select Sub Group<span class="red">*</span></label>
	                                                        <span></span>
	                                                    </div>
	                                                </div>
	                                                <div class="col-sm-4">
	                                                    <div class="styled-input">
	                                                        <select id="srchMaterial" name="srchMaterial" class="materialList"  required></select>
	                                                        <label>Select Material<span class="red">*</span></label>
	                                                        <span></span>
	                                                    </div>
	                                                </div>
	                                            </div>
	                                            <div class="form-group">
	                                                <div class="col-sm-12">
	                                                    <div class="styled-input">
	                                                        <button class="btn btn-default loadList" id="selectMaterialBtn">Add</button>
	                                                        <button class="btn btn-default loadList resetSrchMaterial">Reset</button>
	                                                        <button class="btn btn-default cancelSrchMaterial">Cancel</button>
	                                                    </div>
	                                                </div>
	                                            </div>
											</div>
                                        </div>
                                        <!--fields of field group 9  -->
                                        <!--fields of field group 10  -->
                                        <div>
                                            <div class="form-group">
                                                <div class="col-sm-12">
                                                    <h4><b>GTP Parameter</b></h4>
                                                    <hr>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <div class="col-sm-4">
                                                    <label class="checkbox-inline">
                                                        <input type="checkbox" name="">Select All
                                                    </label>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <div class="col-sm-4">
                                                    <div class="styled-input">
                                                        <input type="text" id="" name="" required />
                                                        <label>List of GTP Parameters <span class="red">*</span></label>
                                                        <span></span>
                                                    </div>
                                                </div>
                                                <div class="col-sm-4">
                                                    <div class="styled-input">
                                                        <input type="text" id="" name="" required />
                                                        <label>Type<span class="red">*</span></label>
                                                        <span></span>
                                                    </div>
                                                </div>
                                                <div class="col-sm-4">
                                                    <label class="checkbox-inline" style="margin-top: 40px;">
                                                        <input type="checkbox" name="">Publish All
                                                    </label>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <div class="col-sm-4">
                                                    <button class="btn btn-default">Goto Main Page</button>
                                                </div>
                                            </div>
                                        </div>
                                        <!--fields of field group 10  -->

                                        <!--fields of field group 11  -->
                                        <div>
                                            <div class="form-group">
                                                <div class="col-sm-12">
                                                    <h4><b>Required Docs List</b></h4>
                                                    <hr>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <div class="col-sm-12">
                                                    <button class="btn btn-default"><span class="glyphicon glyphicon-plus-sign"></span>Add Item</button>
                                                    <button class="btn btn-default"><span class="glyphicon glyphicon-pencil"></span>Edit Item</button>
                                                    <button class="btn btn-default"><span class="glyphicon glyphicon-trash"></span>Delete Item</button>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <div class="col-sm-4">
                                                    <label class="checkbox-inline" style="margin-top: 40px;">
                                                        <input type="checkbox" name="">Select
                                                    </label>
                                                </div>
                                                <div class="col-sm-4">
                                                    <div class="styled-input">
                                                        <input type="text" id="" name="" required />
                                                        <label>Required Document Name<span class="red">*</span></label>
                                                        <span></span>
                                                    </div>
                                                </div>
                                                <div class="col-sm-4">
                                                    <div class="styled-input">
                                                        <input type="text" id="" name="" required />
                                                        <label>Document Section Name<span class="red">*</span></label>
                                                        <span></span>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <!--fields of field group 11  -->
                                        <!--fields of field group 12  -->
                                        <div>
                                            <div class="form-group">
                                                <div class="col-sm-12">
                                                    <h4><b>Required Docs Details</b></h4>
                                                    <hr>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <div class="col-sm-4">
                                                    <div class="styled-input">
                                                        <select id="" name="" required=""></select>
                                                        <label>Required Doc Section Name<span class="red">*</span></label>
                                                        <span></span>
                                                    </div>
                                                </div>
                                                <div class="col-sm-4">
                                                    <div class="styled-input">
                                                        <input type="text" id="" name="" required />
                                                        <label>Required Doc Name<span class="red">*</span></label>
                                                        <span></span>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <!--fields of field group 12  -->
                                        <!--fields of field group 13  -->
                                        <div>
                                            <div class="form-group">
                                                <div class="col-sm-12">
                                                    <h4><b>Tender Prepare Confirmation</b></h4>
                                                    <hr>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <div class="col-sm-4">
                                                    <label class="radio-inline" style="margin-top:25px;">
                                                        <input type="radio" name="optradio">Do You Want To prepare Tender Now
                                                    </label>
                                                </div>
                                                <div class="col-sm-2">
                                                    <div class="styled-input">
                                                        <button class="btn btn-default">Submit</button>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <!--fields of field group 13  -->
                                        <!--fields of field group 14  -->
                                        <div>
                                            <div class="form-group">
                                                <div class="col-sm-12">
                                                    <h4><b>View Tender</b></h4>
                                                    <hr>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <div class="col-sm-2">
                                                    <div class="styled-input">
                                                        <button class="btn btn-default">View Tender Document</button>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <!--fields of field group 14  -->
                                    </div>
<!-- 
                                    <div class="col-sm-12 text-center positionABS" style="margin-bottom:10px;">
                                        <button class="btn-all btn btn-info k-tabstrip-prev btnPrevious pull-left">Previous</button>
                                        <button class="btn btn-info">Save</button>
                                        <button class="btn-all btn btn-info k-tabstrip-next btnNext pull-right">Next</button>
                                    </div> -->
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