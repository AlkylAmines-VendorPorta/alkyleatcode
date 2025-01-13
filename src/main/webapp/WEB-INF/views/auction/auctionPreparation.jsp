<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@taglib uri = "http://www.springframework.org/tags/form" prefix = "sf"%>

<!-- context path  -->
<% String context=request.getContextPath(); %>

<link rel="stylesheet" href="<%=context%>/resources/${appMode}/tilescommon/css/fioristyle.css?appVer=${appVer}"/>
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/${appMode}/tilescommon/css//borderTab.css?appVer=${appVer}">
<script src="<%=context%>/resources/${appMode}/commons/js/springform.js?appVer=${appVer}"></script>
<script src="<%=context%>/resources/${appMode}/commons/js/utility.js?appVer=${appVer}"></script>
<script src="<%=context%>/resources/${appMode}/commons/js/loadList.js?appVer=${appVer}"></script>
<script src="<%=context%>/resources/${appMode}/transaction/js/AuctionPreparation.js?appVer=${appVer}"></script>
        <style></style>
            <script>
            var context="<%=context%>";
                $(document).ready(function() {
                	
                	
                    $("#tabstrip").kendoTabStrip();
                    $("#tabstrip2").kendoTabStrip();
                    $("#tabstrip3").kendoTabStrip();
                    $("#tabstrip4").kendoTabStrip();
                    $("#tabstrip5").kendoTabStrip();

                });
            </script>
		     <div class="zoom">
		        <a class="zoom-fab zoom-btn-large" id="zoomBtn"><i class="fa fa-bars"></i></a>
		        <div class="zoom-menu">
		            <a  id="refreshId" title="create version" class="zoom-fab zoom-btn-sm zoom-btn-doc scale-transition" onclick="createVersion()"><i  class="fa fa-refresh" aria-hidden="true"></i></a>
		            <a  id="addId" title="add new" class="zoom-fab zoom-btn-sm zoom-btn-tangram scale-transition" onclick="addNewTahdr()"><i  class="fa fa-plus-square"></i></a>
		            
		            <a  id="editId" title="edit" class="zoom-fab zoom-btn-sm zoom-btn-report scale-transition" onclick="editTahdr()"><i  class="fa fa-edit"></i></a>
		            <a  id="deleteId" title="delete" class="zoom-fab zoom-btn-sm zoom-btn-feedback scale-transition" onclick="deleteTahdr()"><i  class="fa fa-trash"></i></a>
		        </div>
		    </div> 

        <div class="full-container">
            <!-- left-side start-->
            <div class="clearfix"></div>
            <div id="mobile_first_container" class="left-side col-md-3 no-marg">
                 <div class="detailsheader">
        	<div class="col-sm-3 text-center brdrgt"><label>Auction Preparation Report (5)</label></div>
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
                    <button class="btn btn-default" type="button">
					<span class="glyphicon glyphicon-plus"></span>
				</button>
                    </span>
                </div>
                <div class="btn-group btnmrg" data-toggle="buttons">
							    <label class="btn btn-primary">
									<input type="radio" name="AuctionTypeCodeToggle" value="PT">
									<span class="glyphicon glyphicon-ok"></span> Procurement 
                                </label>
                                <label class="btn btn-primary active">
                                     <input type="radio" name="AuctionTypeCodeToggle" checked value="WT">
                                      <span class="glyphicon glyphicon-ok"></span> Works
                                </label>
                            </div>
                <ul id="tahdrList" class="nav nav-tabs tabs-left tahdrList">
                    
                </ul>
                
                <div class="clearfix"></div>
            </div>
            <!-- left-side end-->

            <!-- right-side start-->
            <div id="mobile_second_container" class="right-side col-md-9 no-marg">
            <div class="detailsheader toptabbrd">
        	<div class="col-sm-9 text-center"><label>Auction Preparation Details</label></div>
        </div>
        <!-- <div class="detailscont">
  							 				<div class="col-md-12">
                                				<label class="col-xs-6"> <h4>NovelERP Solutions</h4></label>
                            					<label class="col-xs-6 ">12765456</label>
                            				</div>	
                            				<div class="col-md-12">
                           						<label class="col-xs-6">Manufacturer</label>
                            					<label class="col-xs-6 ">EOCPK88Pk</label>
                            				</div>
    									</div> -->
                <div class="clearfix"></div>
                <div class="tab-content">
                    <!-- Master tab start-->

                    <div class="tab-pane active in" id="">
                        <div class="card">
                            <div class="posrelative" id="example">
                           <div class="demo-section k-content">
                                    <div id="tabstrip" class="Firsttab">                                    
                                        <ul>
                                            <!-- tabs -->
                                            <li class="k-state-active">Auction Base Info</li>
                                            <li class="tahdrDetail">Auction Details</li>
                                            <li class="impDates">Important Dates</li>
                                            <li>Auction Standard Docs</li>
                                            <li>Auction Custom Docs</li>
                                            <li>Ordering of Auction Docs</li>
                                            <li class="getTahdrMaterialList">List of items in Auction</li>
                                            <!-- <li class="tahdrMaterial">Item in Auction</li>
                                            <li class="sekectMaterial">Select Item Form</li> -->
                                            <!-- <li>GTP Parameter</li> -->
                                            <li>Required Docs List</li>
                                            <li>Required Docs Details</li>
                                            <li>Auction Prepare Confirmation</li>
                                            <li>View Auction</li>
                                        </ul>

                                        <!--fields of field group 1  -->
                                        
                                        <div>
                                        <div class="detailscont">
  							 				<div class="col-md-12">
                                				<label class="col-xs-6"> <h4>NovelERP Solutions</h4></label>
                            					<label class="col-xs-6 ">12765456</label>
                            				</div>	
                            				<div class="col-md-12">
                           						<label class="col-xs-6">Manufacturer</label>
                            					<label class="col-xs-6 ">EOCPK88Pk</label>
                            				</div>
    									</div>
                                        <sf:form class="readonly" id="TAHDR" action="createtahdr" method="POST" autocomplete="off" modelAttribute="tahdr">
                                            <div class="form-group">
                                                <div class="col-sm-12">
                                                    <h4><b>Auction Type Details</b></h4>
                                                    <hr>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <div class="col-sm-4">
                                                    <div class="styled-input">
                                                    	<input type="hidden" class="t_tahdr_id" name="tahdrId">
                                                        <input type="text" id="tahdrCode" name="tahdrCode" value="" required />
                                                        <label>Auction Code<span class="red">*</span></label>
                                                        <span></span>
                                                    </div>
                                                </div>
                                                <div class="col-sm-4">
                                                    <div class="styled-input">
                                                    	<input type="text" id="tahdrTitle" name="title" value="" required>
                                                        <label>Auction Title<span class="red">*</span></label>
                                                        <span></span>
                                                    </div>
                                                </div>
                                                <div class="col-sm-4">
                                                    <div class="styled-input">
                                                        <select id="tahdrType" class="tahdrTypeCode readonly" name="tahdrTypeCode" required>
                                                        </select>
                                                        <label>Auction Type<span class="red">*</span></label>
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
                                        <div class="detailscont">
  							 				<div class="col-md-12">
                                				<label class="col-xs-6"> <h4>NovelERP Solutions</h4></label>
                            					<label class="col-xs-6 ">12765456</label>
                            				</div>	
                            				<div class="col-md-12">
                           						<label class="col-xs-6">Manufacturer</label>
                            					<label class="col-xs-6 ">EOCPK88Pk</label>
                            				</div>
    									</div>
                                        <sf:form class="readonly" action="saveTahdrDetails" id="TAHDRDetail" modelAttribute="tahdrDetail">
                                            <div class="form-group">
                                                <div class="col-sm-12">
                                                	<input type="hidden" class="t_tahdr_id" name="tahdr.tahdrId">
                                                    <input type="hidden" class="tahdrDetailId" name="tahdrDetailId">
                                                    <h4><b>Auction Details</b></h4>
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
                                                        <label>Auction Fees [in Rupees]<span class="red">*</span></label>
                                                        <span></span>
                                                    </div>
                                                </div>
												<div class="col-sm-4">
                                                    <div class="styled-input">
                                                        <select id="pricingProcCode" class="priceBasis" name="pricingProcCode" required>
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
                                                        <input type="checkbox" name="isBiennialContractRate" id="isBiennialContractRate">Is Auction Biennial Contract rate Basis
                                                    </label>
                                                </div>
                                                <div class="col-sm-4">
                                                    <div class="styled-input">
                                                        <input type="text" id="minQuantity" name="minQuantity" required />
                                                        <label>Minimum % of Offered Quantity<span class="red">*</span></label>
                                                        <span></span>
                                                    </div>
                                                </div>
                                                <div class="col-sm-4">
                                                    <div class="styled-input">
                                                        <input type="checkbox" checked value="Y" name="isActive" required />
                                                        <label>Is Active<span class="red">*</span></label>
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
                                        <div class="detailscont">
  							 				<div class="col-md-12">
                                				<label class="col-xs-6"> <h4>NovelERP Solutions</h4></label>
                            					<label class="col-xs-6 ">12765456</label>
                            				</div>	
                            				<div class="col-md-12">
                           						<label class="col-xs-6">Manufacturer</label>
                            					<label class="col-xs-6 ">EOCPK88Pk</label>
                            				</div>
    									</div>
                                        <sf:form class="readonly" action="saveTahdrDates" id="TAHDRDates" modelAttribute="tahdrDetail" >
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
                                                        <label>Last Auction Submission date<span class="red">*</span></label>
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
		                                                    <label>Auction Validity<span class="red">*</span></label>
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
		                                        <button class="btn btn-info saveTahdrDatesBtn">Save</button>
		                                        <button class="btn-all btn btn-info k-tabstrip-next btnNext pull-right">Next</button>
		                                    </div>
		                                    </sf:form>
                                        </div>
                                        <!--fields of field group 3  -->
                                        <!--fields of field group 4  -->
                                        <div>
                                        <div class="detailscont">
  							 				<div class="col-md-12">
                                				<label class="col-xs-6"> <h4>NovelERP Solutions</h4></label>
                            					<label class="col-xs-6 ">12765456</label>
                            				</div>	
                            				<div class="col-md-12">
                           						<label class="col-xs-6">Manufacturer</label>
                            					<label class="col-xs-6 ">EOCPK88Pk</label>
                            				</div>
    									</div>
                                            <div class="form-group">
                                                <div class="col-sm-12">
                                                    <h4><b>Select Auction Standard Documents</b></h4>
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
                                        <div class="detailscont">
  							 				<div class="col-md-12">
                                				<label class="col-xs-6"> <h4>NovelERP Solutions</h4></label>
                            					<label class="col-xs-6 ">12765456</label>
                            				</div>	
                            				<div class="col-md-12">
                           						<label class="col-xs-6">Manufacturer</label>
                            					<label class="col-xs-6 ">EOCPK88Pk</label>
                            				</div>
    									</div>
                                            <div class="form-group">
                                                <div class="col-sm-12">
                                                    <h4><b>Select Auction Custom Docs</b></h4>
                                                    <hr>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <div class="col-sm-12">
                                                	<div class="col-sm-12">
                                                		<div class="col-sm-2">
                                                    		<label class="checkbox-inline">
                                                              <input type="checkbox" value="">CM1
                                                            </label>
                                                        </div>
                                                        <div class="col-sm-4">
		                                                    <input type="file" class="form-control uploadFile">
		                                                </div>
		                                                <div class="col-sm-2">
		                                                	<sf:form action="downloadAttachment">
		                                                	<button class="btn btn-default">Download</button>
		                                                    </sf:form>
		                                                </div>
		                                            </div>
		                                            <div class="col-sm-12">
                                                        <div class="col-sm-2">
                                                    		<label class="checkbox-inline">
                                                              <input type="checkbox" value="">CM2
                                                            </label>
                                                        </div>
                                                        <div class="col-sm-4">
		                                                    <input type="file" class="form-control uploadFile">
		                                                </div>
		                                                <div class="col-sm-2">
		                                                	<sf:form action="downloadAttachment">
		                                                	<button class="btn btn-default">Download</button>
		                                                    </sf:form>
		                                                </div>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                
                                            </div>
                                        </div>
                                        <!--fields of field group 5  -->
                                        <!--fields of field group 6  -->
                                        <div>
                                        <div class="detailscont">
  							 				<div class="col-md-12">
                                				<label class="col-xs-6"> <h4>NovelERP Solutions</h4></label>
                            					<label class="col-xs-6 ">12765456</label>
                            				</div>	
                            				<div class="col-md-12">
                           						<label class="col-xs-6">Manufacturer</label>
                            					<label class="col-xs-6 ">EOCPK88Pk</label>
                            				</div>
    									</div>
                                            <div class="form-group">
                                                <div class="col-sm-12">
                                                    <h4><b>Ordering of Auction Docs</b></h4>
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
                                        <div class="detailscont">
  							 				<div class="col-md-12">
                                				<label class="col-xs-6"> <h4>NovelERP Solutions</h4></label>
                            					<label class="col-xs-6 ">12765456</label>
                            				</div>	
                            				<div class="col-md-12">
                           						<label class="col-xs-6">Manufacturer</label>
                            					<label class="col-xs-6 ">EOCPK88Pk</label>
                            				</div>
    									</div>
	                                        <div id="tahdrMaterialList">
	                                            <div class="form-group">
	                                                <div class="col-sm-12">
	                                                    <h4><b>List of items in Auction</b></h4>
	                                                    <hr>
	                                                </div>
	                                            </div>
	                                            <div class="col-sm-12">
	                                                <div class="form-group">
	
	                                                    <button class="btn btn-default addGtpToMaterial"><span class="glyphicon glyphicon-plus-sign"></span>Add GTP</button>
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
		                                                    <h4><b>Material in Auction</b></h4>
		                                                    <hr>
		                                                </div>
		                                            </div>
		                                            <div class="form-group" >
		                                            	<div class="col-sm-4">
		                                                    <div class="styled-input">
		                                                        <select id="selectedMaterialId" class="readonly" name="material.materialId" required>
		                                                        <option>Select Material</option>
		                                                        </select>
		                                                        <label>Material<span class="red">*</span></label>
		                                                        <button class="searchMaterial"><span class="glyphicon glyphicon-search"></span>
		                                                        </button>
	                                                    	</div>
		                                            	</div>
		                                            	<div class="col-sm-4">
		                                                    <div class="styled-input">
		                                                        <input type="text" class="readonly" id="selectedMaterialUom" name="materialUom" required />
		                                                        <label>UOM<span class="red">*</span></label>
		                                                        <span></span>
		                                                    </div>
		                                                </div>
		                                                <div class="col-sm-4">
		                                                    <div class="styled-input">
		                                                        <input type="text" class="readonly" id="selectedMaterialDesc" name="materialDescription" required />
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
		                                                    <label class="radio-inline" style="margin-top: 25px;">
		                                                        <input type="radio" name="aboutSpecCode" class="aboutSpecCode" value="inc">Include Specification
		                                                    </label>
		                                                    <br>
		                                                    <label class="radio-inline" style="margin-top:5px;">
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
		                                                    <label class="radio-inline" style="margin-top:5px;"><span style="margin-left: -18px;">Material Type</span><br>
		                                                        <input type="radio" name="materialTypeCode" class="input_margin" value="single" checked>Single<br>
		                                                        <input type="radio" name="materialTypeCode" class="input_margin" value="bom">BOM<br>
		                                                    </label>
		                                                </div>
														
		                                            </div>
		                                            <div class="col-sm-12 text-center positionABS" style="margin-bottom:10px;">
				                                        <button class="btn-all btn btn-info k-tabstrip-prev btnPrevious pull-left">Previous</button>
				                                        <button class="btn btn-info saveTahdrMaterial">Save</button>
				                                        <button class="btn btn-info updateTahdrMaterial">update</button>
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
	                                                        <button class="btn btn-default" id="selectMaterialBtn">Add</button>
	                                                        <button class="btn btn-default loadList resetSrchMaterial" url="getMaterialGroupList">Reset</button>
	                                                        <button class="btn btn-default cancelSrchMaterial">Cancel</button>
	                                                    </div>
	                                                </div>
	                                            </div>
											</div>
									<div id="tahdrMaterialGtp" style="display:none;">
                                            <div class="form-group">
                                                <div class="col-sm-12">
                                                    <h4><b>GTP Parameter</b></h4>
                                                    <hr>
                                                </div>
                                            </div>
                                            <sf:form style="display:none;" id="tahdrMaterialGtpListForm" action="savetahdrMaterialGtp" 
                                            method="POST" autocomplete="off" modelAttribute="tahdrMaterialGTPList">
				                                <table id="tahdrMaterialGtpListFormTable">
				                                    <tbody>
				                                    </tbody>
				                                </table>
				                                <button class="btn btn-default formSubmit">Save</button>
				                            </sf:form>
                                            <div class="form-group">
                                                <div class="col-sm-12">
                                                	<div class="styled-input">
                                                        <input type="text" class="tahdrMaterialId" required />
                                                        <label>List of GTP Parameters <span class="red">*</span></label>
                                                        <span></span>
                                                    </div>
                                                    <table id="tahdrMaterialGtpTable" class="table table-bordered">
	                                                    <thead>
	                                                        <tr>
	                                                        	<th><input type="checkbox" />Select all</th>
	                                                            <th>GTP Parameter</th>
	                                                            <th>GTP Type</th>
	                                                            <th><input type="checkbox" />Publish all</th>
	                                                        </tr>
	                                                    </thead>
	                                                    <tbody>
	                                                        
	                                                    </tbody>
	                                                </table>
                                                </div>
                                            </div>
                                            
                                            <div class="form-group">
                                                <div class="col-sm-4">
                                                    <button class="btn btn-default saveTahdrMaterialGtp">Save</button>
                                                    <button class="btn btn-default cancelTahdrMaterialGtp">Cancel</button>
                                                </div>
                                            </div>
                                        </div>
                                        </div>
                                        <!--fields of field group 9  -->
                                        <!--fields of field group 10  -->
                                        
                                        <!--fields of field group 10  -->

                                        <!--fields of field group 11  -->
                                        <div>
                                        <div class="detailscont">
  							 				<div class="col-md-12">
                                				<label class="col-xs-6"> <h4>NovelERP Solutions</h4></label>
                            					<label class="col-xs-6 ">12765456</label>
                            				</div>	
                            				<div class="col-md-12">
                           						<label class="col-xs-6">Manufacturer</label>
                            					<label class="col-xs-6 ">EOCPK88Pk</label>
                            				</div>
    									</div>
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
                                        <div class="detailscont">
  							 				<div class="col-md-12">
                                				<label class="col-xs-6"> <h4>NovelERP Solutions</h4></label>
                            					<label class="col-xs-6 ">12765456</label>
                            				</div>	
                            				<div class="col-md-12">
                           						<label class="col-xs-6">Manufacturer</label>
                            					<label class="col-xs-6 ">EOCPK88Pk</label>
                            				</div>
    									</div>
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
                                        <div class="detailscont">
  							 				<div class="col-md-12">
                                				<label class="col-xs-6"> <h4>NovelERP Solutions</h4></label>
                            					<label class="col-xs-6 ">12765456</label>
                            				</div>	
                            				<div class="col-md-12">
                           						<label class="col-xs-6">Manufacturer</label>
                            					<label class="col-xs-6 ">EOCPK88Pk</label>
                            				</div>
    									</div>
                                            <div class="form-group">
                                                <div class="col-sm-12">
                                                    <h4><b>Auction Prepare Confirmation</b></h4>
                                                    <hr>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <div class="col-sm-4">
                                                    <label class="radio-inline" style="margin-top:25px;">
                                                        <input type="radio" name="optradio">Do You Want To prepare Auction Now
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
                                        <div class="detailscont">
  							 				<div class="col-md-12">
                                				<label class="col-xs-6"> <h4>NovelERP Solutions</h4></label>
                            					<label class="col-xs-6 ">12765456</label>
                            				</div>	
                            				<div class="col-md-12">
                           						<label class="col-xs-6">Manufacturer</label>
                            					<label class="col-xs-6 ">EOCPK88Pk</label>
                            				</div>
    									</div>
                                            <div class="form-group">
                                                <div class="col-sm-12">
                                                    <h4><b>View Auction</b></h4>
                                                    <hr>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <div class="col-sm-2">
                                                    <div class="styled-input">
                                                        <button class="btn btn-default">View Auction Document</button>
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
            </div>