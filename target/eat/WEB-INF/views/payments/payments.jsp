<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@taglib uri = "http://www.springframework.org/tags/form" prefix = "sf"%>
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/${appMode}/tilescommon/css/fioristyle.css?appVer=${appVer}"/>
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/${appMode}/tilescommon/css/mobile.css?appVer=${appVer}"/>
<script src="<%=request.getContextPath()%>/resources/${appMode}/tilescommon/js/kendo.all.min.js?appVer=${appVer}"></script>
<style>.input_margin {
    margin-left: 67px !important;
    margin-top: 5px;
}input[type="radio"], input[type="checkbox"] {
    margin: 1px 5px 0;
    margin-top: 1px \9;
    line-height: normal;
}</style>
            <script>
                $(document).ready(function() {
                    $("#tabstrip").kendoTabStrip();
                    $("#tabstrip2").kendoTabStrip();
                    $("#tabstrip3").kendoTabStrip();
                    $("#tabstrip4").kendoTabStrip();
                    $("#tabstrip5").kendoTabStrip();

                });
            </script>
        <div class="full-container">
            <!-- left-side start-->
            <div class="clearfix"></div>
            <div id="mobile_first_container" class="left-side col-md-3 no-marg">
                  <div class="detailsheader">
        	<div class="col-sm-3 text-center brdrgt"><label>Payments  Report (5)</label></div>
        </div>
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
                    <button class="btn btn-default addnewlist" type="button">
					<span class="glyphicon glyphicon-plus"></span>
				</button>
                    </span>
                </div>
                <ul id="example" class="nav nav-tabs tabs-left example">
                    <li class="active">
                        <a href="#Master" data-toggle="tab">
                            <div class="col-md-12">
                                <label class="col-xs-6"> NovelERP Solutions </label>
                            	<label class="col-xs-6 ">12765456</label>
                            </div>	
                            <div class="col-md-12">
                           		<label class="col-xs-6">Manufacturer</label>
                            	<label class="col-xs-6 ">EOCPK88Pk</label>
                            </div>
                            
                        </a>
                    </li>
                </ul>                
                <div class="clearfix"></div>
            </div>
            <!-- left-side end-->

            <!-- right-side start-->
            <div id="mobile_second_container" class="right-side col-md-9 no-marg">
            <div class="detailsheader toptabbrd">
        	<div class="col-sm-9 text-center"><label>Payments Details</label></div>
        </div>
                <div class="clearfix"></div>
                <div class="tab-content">
                    <!-- Master tab start-->

                    <div class="tab-pane active in" id="AddUoM">
                        <div class="card">
                             <div class="posrelative" id="example">                            
                                <div class="demo-section k-content">
                                    <div id="tabstrip" class="Firsttab">
                                        <ul>
                                            <!-- tabs -->
                                            <li class="k-state-active">Payment Details</li>
                                            <!-- <li>Tender Public Notice</li> -->
                                        </ul>

                                        <!--fields of field group 1  -->
                                        <div>                                        
                                            <div class="form-group">
                                                <div class="col-sm-12">
                                                    <h4><b>Payment Details</b></h4>
                                                    <hr>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <div class="col-sm-4">
	                                                <div class="styled-input">
	                                                    <select id="companyType" name="companyType" required></select>
	                                                    <label>Firm Name<span class="red">*</span></label>
	                                                    <span></span>
	                                                </div>
                                                </div>
                                                <div class="mobclearfix"></div>
                                                 <div class="col-sm-4">
	                                                <div class="styled-input">
	                                                    <select id="companyType" name="companyType" required></select>
	                                                    <label>Select Payment Type<span class="red">*</span></label>
	                                                    <span></span>
	                                                </div>
                                                </div>
                                                <div class="mobclearfix"></div>
                                                <div class="col-sm-4">
                                                    <label>Mode of Payment<span class="red">*</span><br>
                                                        <input type="radio"name="ModeofPayment"> By DD <br>
                                                        <input type="radio" name="ModeofPayment"> Credit/Debit Card<br>
                                                     
                                                    </label>
                                                </div>
                                                </div>
                                                <div class="mobclearfix"></div>
                                                <div class="form-group">
                                                <div class="col-sm-4">
                                                    <div class="styled-input">
                                                        <input type="text" id="" name="" required />
                                                        <label>Amount (in Rs)<span class="red">*</span></label>
                                                        <span></span>
                                                    </div>
                                                </div>
                                                <div class="col-sm-4">
                                                    <div class="styled-input">
                                                        <input type="text" id="" name="" required title="GST Identification Number(GSTIN)" />
                                                        <label>GST Identification Number(GSTIN)<span class="red">*</span></label>
                                                        <span></span>
                                                    </div>
                                                </div>
                                                <div class="col-sm-4">
                                                    <div class="styled-input">
                                                        <input type="text" id="" name="" required title="GST(@18% on Amount:SAC No.998599) in Rs."/>
                                                        <label>GST(@18% on Amount:SAC No.998599)Rs.<span class="red">*</span></label>
                                                        <span></span>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="mobclearfix"></div>
                                            <div class="form-group">
                                            <div class="mobclearfix"></div>
                                                <div class="col-sm-4">
                                                    <div class="styled-input">
                                                        <input type="text" id="" name="" required />
                                                        <label>Total Amount including GST in Rs.<span class="red">*</span></label>
                                                        <span></span>
                                                    </div>
                                                </div>
                                                <div class="mobclearfix"></div>
                                                <div class="col-sm-4">
                                                    <div class="form-group" style="margin-bottom: 0px;">
                                                        <label>DD Date<span class="red">*</span></label>
                                                        <div class="input-group date" data-provide="datepicker">
                                                            <input type="text" class="form-control">
                                                            <div class="input-group-addon">
                                                                <span class="glyphicon glyphicon-th"></span>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="col-sm-4">
                                                    <div class="styled-input">
                                                        <input type="text" id="" name="" required title="GST(@18% on Amount:SAC No.998599) in Rs."/>
                                                        <label>Demand Draft Number<span class="red">*</span></label>
                                                        <span></span>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <div class="col-sm-4">
                                                    <div class="styled-input">
                                                        <input type="text" id="" name="" required />
                                                        <label>MICR Code<span class="red">*</span></label>
                                                        <span></span>
                                                    </div>
                                                </div>
                                                <div class="col-sm-4">
                                                    <div class="styled-input">
                                                        <input type="text" id="" name="" required />
                                                        <label> Bank Name <span class="red">*</span></label>
                                                        <span></span>
                                                    </div>
                                                </div>
                                                <div class="col-sm-4">
                                                    <div class="styled-input">
                                                        <input type="text" id="" name="" required />
                                                        <label>Branch Name
                                                        <span class="red">*</span></label>
                                                        <span></span>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <div class="col-sm-4" style="margin-top:40px;">
                                                    <label>Realisation Status<span class="red">*</span>
                                                        <input type="radio"name="RealisationStatus"> Yes
                                                        <input type="radio" name="RealisationStatus">No
                                                     
                                                    </label>
                                                </div>
                                                <div class="col-sm-4">
                                                    <div class="form-group">
                                                        <label>Realisation Date<span class="red">*</span></label>
                                                        <div class="input-group date" data-provide="datepicker">
                                                            <input type="text" class="form-control">
                                                            <div class="input-group-addon">
                                                                <span class="glyphicon glyphicon-th"></span>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="col-sm-4">
                                                    <div class="form-group">
                                                        <label>Money Receipt Date<span class="red">*</span></label>
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
                                                        <input type="text" id="" name="" required />
                                                        <label>Money Receipt Number
                                                        <span class="red">*</span></label>
                                                        <span></span>
                                                    </div>
                                                </div>
                                                <div class="col-sm-8" style="margin-top:40px;">
                                                    <label>Recommended Status<span class="red">*</span>
                                                        <input type="radio"name="RecommendedStatus">Pending
                                                        <input type="radio" name="RecommendedStatus">Clarification
                                                        <input type="radio"name="RecommendedStatus">Approved
                                                        <input type="radio" name="RecommendedStatus">Rejected
                                                     
                                                    </label>
                                                </div>
                                             </div>
                                             <div class="form-group">
                                            <div class="col-sm-12">
                                                    <div class="styled-input">
                                                        <textarea type="text" id="" name="" required ></textarea>
                                                        <label>Comments
                                                        <span class="red">*</span></label>
                                                        <span></span>
                                                    </div>
                                                </div>
                                             </div>   
                                            </div>
                                        <!--fields of field group 1  -->
                                        <!--fields of field group 2  -->
                                        <div>                                        
                                            <div class="form-group">
                                                <div class="col-sm-12">
                                                    <h4><b>Tender Public Notice (To publich New Tender)</b></h4>
                                                    <hr>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <div class="styled-input">
                                                    <select id="companyType" name="companyType" required></select>
                                                    <label>Select Tender<span class="red">*</span></label>
                                                    <span></span>
                                                </div>
                                                <div class="col-sm-4">
                                                    <div class="styled-input">
                                                        <input type="text" id="" name="" required />
                                                        <label>Tender Notice Title<span class="red">*</span></label>
                                                        <span></span>
                                                    </div>
                                                </div>
                                                <div class="col-sm-4">
                                                    <div class="styled-input">
                                                        <input type="text" id="" name="" required />
                                                        <label>Tender Notice Matter<span class="red">*</span></label>
                                                        <span></span>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <div class="col-sm-3">
                                                    <div class="form-group">
                                                        <label>Tender Notice Publishing Date<span class="red">*</span></label>
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
                                                        <label>Tender Notice Publishing Time<span class="red">*</span></label>
                                                        <div class="input-group bootstrap-timepicker timepicker">
                                                            <input type="text" class="form-control input-small timepicker">
                                                            <span class="input-group-addon"><i class="glyphicon glyphicon-time"></i></span>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="col-sm-3">
                                                    <div class="form-group">
                                                        <label>Tender Sale opening Date<span class="red">*</span></label>
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
                                                        <label>Tender Sale opening Time<span class="red">*</span></label>
                                                        <div class="input-group bootstrap-timepicker timepicker">
                                                            <input type="text" class="form-control input-small timepicker">
                                                            <span class="input-group-addon"><i class="glyphicon glyphicon-time"></i></span>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="col-sm-3">
                                                    <div class="form-group">
                                                        <label>Tender Sale closing Date<span class="red">*</span></label>
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
                                                        <label>Tender Sale closing Time<span class="red">*</span></label>
                                                        <div class="input-group bootstrap-timepicker timepicker">
                                                            <input type="text" class="form-control input-small timepicker">
                                                            <span class="input-group-addon"><i class="glyphicon glyphicon-time"></i></span>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>

                                            <div class="form-group">
                                                <div class="col-sm-4">
                                                    <input type="file" name="" class="form-control">
                                                </div>
                                                <div class="col-sm-4">
                                                    <button class="btn btn-default">Upload</button>
                                                </div>
                                            </div>
                                        </div>
                                        <!--fields of field group 2  -->
                                    </div>

                                    <div class="col-sm-12 text-center positionABS" style="margin-bottom:10px;">
                                        <button class="btn-all btn btn-info k-tabstrip-prev btnPrevious pull-left">Previous</button>
                                        <button class="btn btn-info">Save</button>
                                        <button class="btn-all btn btn-info k-tabstrip-next btnNext pull-right">Next</button>
                                    </div>
                                </div>
                                <!-- Master tab end-->

                            </div>
                        </div>
                        <!-- right-side end-->
                    </div>
                </div>
            </div>
            </div>