<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@taglib uri = "http://www.springframework.org/tags/form" prefix = "sf"%>

<% String context=request.getContextPath(); %>

<link rel="stylesheet" href="<%=context%>/resources/${appMode}/tilescommon/css/fioristyle.css?appVer=${appVer}"/>
<link rel="stylesheet" href="<%=context%>/resources/${appMode}/tilescommon/css/borderTab.css?appVer=${appVer}">

<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/${appMode}/tilescommon/css/mobile.css?appVer=${appVer}">
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/${appMode}/tilescommon/css/barchart.css?appVer=${appVer}">

<link href="http://www.jqueryscript.net/css/jquerysctipttop.css?appVer=${appVer}" rel="stylesheet" type="text/css">
 <link href="<%=request.getContextPath()%>/resources/${appMode}/tilescommon/css/tooltip.css" rel="stylesheet" type="text/css" />
 <script src="<%=request.getContextPath()%>/resources/${appMode}/tilescommon/js/tooltip.js" type="text/javascript"></script>
 <script src="<%=request.getContextPath()%>/resources/${appMode}/tilescommon/js/kendo.all.min.js?appVer=${appVer}"></script>
<style>
.tooltip {
    display:inline-block;
    position:relative;
    border-bottom:1px dotted #666;
    text-align:left;
}

.tooltip:hover #tooltiptext {
  visibility: visible;
}
</style>
<script>
                $(document).ready(function() {
                    $("#tabstrip").kendoTabStrip();
                    /* $('#leftPane').paginate({
                		perPage: 6,
                	}); */
                });
            </script>
            
<div class="container" style="margin-top:100px;">

<div class="demo-section k-content">
                                    <div id="tabstrip" class="Firsttab">
                                        <ul>
                                            <!-- tabs -->
                                            <li class="k-state-active">Payment Reports</li>
                                            <!-- <li id="getTotalSavingDetails">Cumulative Reports</li> -->
                                                                                    
                                        </ul>
                                        <!--fields of field group 1  -->
                                        <div>
                                        <div>
                                        <div class="form-group">
                                        <div class='col-md-3'>
												        <div class="form-group">
												         <label id="">Payment From Date And Time<span class="red">*</span></label>
												            <div class='input-group date dateCheck' id='datetimepicker6'>
												                <input type='text' class="form-control" id="fromDateAndTime"/>
												                <span class="input-group-addon">
												                    <span class="glyphicon glyphicon-calendar"></span>
												                </span>
												            </div>
												        </div>
												    </div>
												    <div class='col-md-3'>
												        <div class="form-group">
												         <label id="">Payment To Date And Time<span class="red">*</span></label>
												            <div class='input-group date dateCheck' id='datetimepicker7'>
												                <input type='text' class="form-control" id="toDateAndTime" />
												                <span class="input-group-addon">
												                    <span class="glyphicon glyphicon-calendar"></span>
												                </span>
												            </div>
												        </div>
												    </div>
												    <!--  <div class="col-sm-3">
												      <input class="statusCheck" type="radio" name="approvalStatus" value="Y" checked>Approved<br>
													  <input class="statusCheck" type="radio" name="approvalStatus" value="N">Rejected<br>
												     </div> -->
												    <div class="col-sm-3">
	                                                   <button id="PD_SearchBtnId" onclick="searchPaymentDetails(this)" class="btn btn-default" data-ifsearched="false">Search</button>
	                                               </div>
	                                     </div>
	                                    <div class="form-group">
                                        <div class="col-sm-3">
														<div class="styled-input">
															<select id="fiscalYear"
																class=" dropDown"
																name="" onchange="loadPaymentReportDataByFiscalYear()">
																<option value="0">Select</option>
																	<c:forEach var="finYear" items="${finYear}">
																<option value="${finYear.financialYearId}">${finYear.name}</option>
															</c:forEach>
															</select> <label>Search By Fiscal Year<span class="red">*</span></label>
															<span></span>
														</div>
										</div>
										<div class="col-sm-3">
														<div class="styled-input">
															<select id="tahdrId"
																class=" dropDown"
																name="tahdrId" onchange="loadPaymentReportDataByTahdrId()">
																<option value="0">Select</option>
																	<c:forEach var="tahdrList" items="${tahdrList}">
																<option value="${tahdrList.tahdrId}">${tahdrList.tahdrCode}</option>
															</c:forEach>
															</select> <label>Search By Tender Id<span class="red">*</span></label>
															<span></span>
														</div>
									  </div>
									  <div class="col-sm-3">
														<div class="styled-input">
															<select id="paymentTypeId"
																class=" dropDown"
																name="tahdrId" onchange="loadPaymentReportDataByPaymentType()">
																<option value="0">Select</option>
																	<c:forEach var="paymentType" items="${paymentType}">
																<option value="${paymentType.paymentTypeId}">${paymentType.name}</option>
															</c:forEach>
															</select> <label>Search By Payment Type<span class="red">*</span></label>
															<span></span>
														</div>
									  </div>
									  </div>
													 <div class="regularAuction">
    									   <div class="form-group">
                                                <div class="col-sm-12">
                                                    <h4><b>Payments Details</b></h4>
                                                    <hr>
                                                </div>
                                            </div>
                                            
                                            <div style="display:none;">
											    <div id="tip">
											        <div id="tooltipText">TEST</div>
											    </div>
											</div>
    									      
										 <div class="col-sm-12">
											<table class="table table-bordered table-responsive" id="bidderPaymentTblId">
                                                        <thead>
                                                            <tr>
                                                             <th></th>
                                                                <th>Tender Name</th>
                                                                <th>Bidder</th>
                                                                <th>Payment Type</th>
                                                                <th>Amount</th>
                                                                <th>Payment Mode</th>
                                                                <th>Time Of Payment</th>
                                                                <th>Finance operator</th>
                                                                <th>Payment Status (Operator)</th>
                                                                <th>Payment Remarks (Operator)</th>
                                                                <th>Approval Time(Operator)</th>
                                                                <th>Finance Admin</th>
                                                                <th>Payment Status (Admin)</th>
                                                                <th>Payment Remarks (Admin)</th> 
                                                                <th>Approval Time(Admin)</th>                                                           
                                                            </tr>
                                                        </thead>
                                                        <tbody>
                                                                                  
                                                        </tbody>
                                                    </table>
										
										</div>
										</div>
										</div>
										</div>
    								  <div>
    								   <div class="form-group">
                                                <div class="col-sm-12">
                                                    <h4><b>Cumulative Details</b></h4>
                                                    <hr>
                                                </div>
                                            </div>
    								 
                                        <div class="col-sm-3">
														<div class="styled-input">
															<select id="FiscalYear" class="dropDown"
																name="" >
																<option value="0">Select</option>
																	<c:forEach var="finYear" items="${finYear}">
																<option value="${finYear.financialYearId}">${finYear.name}</option>
															</c:forEach>
															</select> <label>Fiscal Year<span class="red">*</span></label>
															<span></span>
														</div>
													</div>
													<div class="clearfix"></div>
  							 				<!-- <div id="chartContainer" style="height: 400px; width: 100%;"></div> -->
  							 				<table class="table table-bordered table-responsive" id="tendertblId">
                                                        <thead>
                                                            <tr>
                                                             <th></th>
                                                                <th>Tender Name</th>
                                                                <th>Create On</th>
                                                                <th>Created By</th>
                                                                <th>Approved By</th>
                                                                <th>Approval Comment</th>
                                                                <th>Approved On</th>
                                                                <th>No of Bidder</th>
                                                                <th>Cumulative Reports</th>                                                    
                                                            </tr>
                                                        </thead>
                                                        <tbody>
                                                                                  
                                                        </tbody>
                                                    </table>
    									</div>
    									 </div>
    									</div>
	
	
</div>
<script type="text/javascript" src="https://canvasjs.com/assets/script/jquery.canvasjs.min.js?appVer=${appVer}"></script>
<script src ="<%=request.getContextPath()%>/resources/${appMode}/tilescommon/js/reports.js?appVer=${appVer}"></script>
<script src="<%=request.getContextPath()%>/resources/${appMode}/commons/js/loadList.js?appVer=${appVer}"></script>
<script src="<%=request.getContextPath()%>/resources/${appMode}/commons/js/formFields.js?appVer=${appVer}"></script>
<script src="<%=request.getContextPath()%>/resources/${appMode}/commons/js/utility.js?appVer=${appVer}"></script>
<script src="<%=request.getContextPath()%>/resources/${appMode}/commons/js/loadItemList.js?appVer=${appVer}"></script>
<script src="<%=request.getContextPath()%>/resources/${appMode}/tilescommon/js/bootstrap-datetimepicker.js?appVer=${appVer}"></script> 

<script>
 $(function () {
		    	
		        $('#datetimepicker6').datetimepicker();
		        $('#datetimepicker7').datetimepicker({
		            useCurrent: false //Important! See issue #1075
		        });
		        $("#datetimepicker6").on("dp.change", function (e) {
		            $('#datetimepicker7').data("DateTimePicker").minDate(e.date);
		        });
		        $("#datetimepicker7").on("dp.change", function (e) {
		            $('#datetimepicker6').data("DateTimePicker").maxDate(e.date);
		        });
		    });
</script>
