<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@taglib uri = "http://www.springframework.org/tags/form" prefix = "sf"%>
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/${appMode}/tilescommon/css/fioristyle.css?appVer=${appVer}"/>
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/${appMode}/tilescommon/css//borderTab.css?appVer=${appVer}">
            <script>
                $(document).ready(function() {
                    $("#tabstrip").kendoTabStrip();
                });
               
            </script>
    
        <!-- Header end -->
        <div class="full-container">
            <!-- left-side start-->
            <div class="clearfix"></div>
            <div id="mobile_first_container" class="left-side col-md-3 no-marg">
            <div class="detailsheader">
        		<div class="col-sm-3 text-center brdrgt"><label>Auction Purchase Report (<span class="reportCount">0</span>)</label></div>
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
									<input type="radio" name="options" id="option3">
									<span class="glyphicon glyphicon-ok"></span> Procurement 
                                </label>
                                <label class="btn btn-primary">
                                     <input type="radio" name="options" id="option3">
                                      <span class="glyphicon glyphicon-ok"></span> Works
                                </label>
                            </div>
                <ul id="example" class="nav nav-tabs tabs-left example">
                    <li class="active">
                        <a href="#Master" data-toggle="tab">
                            <!-- <div class="col-md-12">
                                <label class="col-xs-6"> NovelERP Solutions </label>
                            	<label class="col-xs-6 ">12765456</label>
                            </div>	
                            <div class="col-md-12">
                           		<label class="col-xs-6">Manufacturer</label>
                            	<label class="col-xs-6 ">EOCPK88Pk</label>
                            </div> -->
                            
                        </a>
                    </li>
                </ul>                
                <div class="clearfix"></div>
            </div>
            <!-- left-side end-->

            <!-- right-side start-->
            <div id="mobile_second_container" class="right-side col-md-9 no-marg">
             <div class="detailsheader toptabbrd">
        		<div class="col-sm-9 text-center"><label>Auction Purchase Details</label></div>
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
                                            <li class="k-state-active">Todays Top Auction</li>
                                            <li class="payment">Auction Fees Details</li>
                                        </ul>

                                        <!--fields of field group 1  -->
                                        <div>
                                        <div class="detailscont">
  							 				<div class="col-md-12" id="masterDetails">
                                
                           					 </div>
    									</div>
                                            <div class="form-group">
                                                <div class="col-sm-12">
                                                    <h4><b>Todays Top Auction</b></h4>
                                                    <hr>
                                                </div>
                                            </div>
                                            <div class="form-group readonly">
                                                <div class="col-sm-4">
                                                    <div class="styled-input">
                                                        <input type="text" id="description" name="description" required />
                                                        <label>Description Of Auction<span class="red">*</span></label>
                                                        <span></span>
                                                    </div>
                                                </div>
                                                <div class="col-sm-4">
                                                    <div class="styled-input">
                                                        <input type="text" id="estimatedCost" name="estimatedCost" required />
                                                        <label>Estimated Cost (in Rs in Lakhs)<span class="red">*</span></label>
                                                        <span></span>
                                                    </div>
                                                </div>
                                                <div class="col-sm-4">
                                                    <div class="styled-input">
                                                        <input type="text" id="AuctionFees" name="AuctionFees" required />
                                                        <label>Auction Fees [in Rupees]<span class="red">*</span></label>
                                                        <span></span>
                                                    </div>
                                                </div>
                                            </div>
                                             <div class="form-group">
                                             <div class="styled-input">
                                             	 <div class="col-sm-2" id="purchase">
                                             	 	<a><b><span class="glyphicon glyphicon-plus-sign paddright"></span>Purchase Auction</b></a>
                                             	 </div>
                                             	 <div class="col-sm-2">
                                             	 	<a><b><span class="glyphicon glyphicon-plus-sign paddright"></span>Pay Online</b> </a>
                                             	 </div>
                                             	 <div class="col-sm-2">
                                             	 	<a><b><span class="glyphicon glyphicon-plus-sign paddright"></span>View Details</b> </a>
                                             	 </div>
                                             	 </div>
                                             </div>
                                            <div class="clearfix"></div>
                                        </div>
                                        <!--fields of field group 1  -->
                                        <!--fields of field group 2  -->
                                        <div>
                                        <div class="detailscont">
  							 				<div class="col-md-12" id="masterDetails">
                                
                           					 </div>
    									</div>
                                            <div class="form-group">
                                                <div class="col-sm-12">
                                                    <h4><b>Auction Fees Details</b></h4>
                                                    <hr>
                                                </div>
                                            </div>
                                           
                                            <div class="form-group">                                                
                                                <div class="col-sm-4">
                                                    <div class="styled-input">
                                                        <input type="text" id="tahdrCode" name="tahdrCode" required />
                                                        <label>Auction Number<span class="red">*</span></label>
                                                        <span></span>
                                                    </div>
                                                </div>
                                                <div class="col-sm-4">
                                                  <div class="styled-input">
                                                    <select id="paymentType" name="paymentType" required="required">
                                                     <option value="select">Select </option>
                                                    <c:forEach var="paymentType" items="${paymentType}">
                                                    
								<option value="${paymentType.paymentTypeId}">${paymentType.name}</option>
							</c:forEach></select>
                                                    <label>Type of Payment<span class="red">*</span></label>
                                                    <span></span>
                                                  </div>
                                                </div>
                                                <div class="col-sm-4">
                                                  <div class="styled-input">
                                                     <select id="paymentMode" class="paymentMode" required="required">
                                                    <option value="select">Select </option>
                                                    <c:forEach var="paymentMode" items="${paymentMode}">
                                                    
								<option value="${paymentMode.name}">${paymentMode.name}</option>
							</c:forEach></select>
                                                    <label>Mode Of Payment<span class="red">*</span></label>
                                                    <span></span>
                                                  </div>
                                                </div>
                                            </div>
                                            <div class="form-group" style="margin-bottom: 0px;">
                                                <div class="col-sm-4">
                                                    <div class="form-group">
                                                        <label>DD Date</label>
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
                                                        <input type="text" id="" name="" required />
                                                        <label>Demand Draft Number<span class="red">*</span></label>
                                                        <span></span>
                                                    </div>
                                                </div>
                                                <div class="col-sm-4">
                                                    <div class="styled-input">
                                                        <input type="text" id="" name="" required />
                                                        <label>MICR Code<span class="red">*</span></label>
                                                        <span></span>
                                                    </div>
                                                </div>                                                
                                            </div>
											<div class="form-group">
                                                <div class="col-sm-4">
                                                    <div class="styled-input">
                                                        <input type="text" id="" name="" required />
                                                        <label>Amount<span class="red">*</span></label>
                                                        <span></span>
                                                    </div>
                                                </div>
                                                <div class="col-sm-4">
                                                    <div class="styled-input">
                                                        <input type="text" id="" name="" required />
                                                        <label>Bank Name<span class="red">*</span></label>
                                                        <span></span>
                                                    </div>
                                                </div>
                                                <div class="col-sm-4">
                                                    <div class="styled-input">
                                                        <input type="text" id="" name="" required />
                                                        <label>Branch Name<span class="red">*</span></label>
                                                        <span></span>
                                                    </div>
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
            <div class="clearfix"></div>
</div>


<script	src="<%=request.getContextPath()%>/resources/${appMode}/commons/js/tahdrPurchase.js?appVer=${appVer}"></script>