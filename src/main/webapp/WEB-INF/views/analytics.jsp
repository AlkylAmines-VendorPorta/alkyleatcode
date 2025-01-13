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
<script src="<%=request.getContextPath()%>/resources/${appMode}/tilescommon/js/kendo.all.min.js?appVer=${appVer}"></script> 
<link href="http://www.jqueryscript.net/css/jquerysctipttop.css?appVer=${appVer}" rel="stylesheet" type="text/css">

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
                                            <li class="k-state-active">Total Revenue</li>
                                            <li id="getTotalSavingDetails">Total Savings</li>
                                            <!-- <li >3rd Chart</li> -->
                                            <!-- <li>Bid Submission </li>
                                            <li>Digital Signing Of Bid</li>
                                            <li>Tender Bid Opening</li>
                                            <li>Tender Report Preparation</li>  -->                                         
                                        </ul>
                                        <!--fields of field group 1  -->
                                        <div>
                                        <div class="col-sm-3">
														<div class="styled-input">
															<select id="FiscalYear"
																class=" dropDown"
																name="tahdrTypeCode">
																	<c:forEach var="finYear" items="${finYear}">
																<option value="${finYear.financialYearId}">${finYear.name}</option>
															</c:forEach>
															</select> <label>Fiscal Year<span class="red">*</span></label>
															<span></span>
														</div>
													</div>
													<div class="clearfix"></div>
  							 				<div id="chartContainer" style="height: 400px; width: 100%;"></div>
    									</div>
    									<div>
    										<div class="col-sm-3">
														<div class="styled-input">
															<select id="FiscalYearForSaving"
																class=" dropDown"
																name="tahdrTypeCode">
																	<c:forEach var="finYear" items="${finYear}">
																<option value="${finYear.financialYearId}">${finYear.name}</option>
															</c:forEach>
															</select> <label>Fiscal Year<span class="red">*</span></label>
															<span></span>
														</div>
														</div>
													 <div class="col-sm-3">
                    <div class="styled-input">
                         <select  id="MaterialForSaving"  class="specName" name="specification.materialId" required="required">
                        </select>
                        <label>Material<span class="red">*</span></label>
                        <span></span>
                    </div>
                </div>
                <div class="col-sm-2">
                 <button class="btn btn-default cancel" style="margin-top:20px; margin-left:-20px;">X</button>
                       <button class="btn btn-default aditbt addMatPopup" id="materialId">Select Material</button>
                      
                </div>
														<div class="col-sm-3">
														<div class="styled-input">
															<select id="DepartmentIDForSaving"
																class=" dropDown"
																name="tahdrTypeCode">
																<option value="0"> </option>
																	<c:forEach var="departmentList" items="${departmentList}">
																<option value="${departmentList.departmentId}">${departmentList.name}</option>
															</c:forEach>
															</select> <label>Department<span class="red">*</span></label>
															<span></span>
														</div>
													</div>
													<div class="clearfix"></div>
  							 				<div id="savingChart" style="height: 400px; width: 100%;"></div>
    									
    									
    								<!-- 	<div>
    										3 rd chart
    									</div> -->
    									</div>
    									 </div>
    									</div>
	
	
</div>
<script type="text/javascript" src="https://canvasjs.com/assets/script/jquery.canvasjs.min.js?appVer=${appVer}"></script>
<script src ="<%=request.getContextPath()%>/resources/${appMode}/tilescommon/js/Analytics.js?appVer=${appVer}"></script>
<script src="<%=request.getContextPath()%>/resources/${appMode}/commons/js/loadList.js?appVer=${appVer}"></script>
<script src="<%=request.getContextPath()%>/resources/${appMode}/commons/js/formFields.js?appVer=${appVer}"></script>
<script src="<%=request.getContextPath()%>/resources/${appMode}/commons/js/loadItemList.js?appVer=${appVer}"></script>

