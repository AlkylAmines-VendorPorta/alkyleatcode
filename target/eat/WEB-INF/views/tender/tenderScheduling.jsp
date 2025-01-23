<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@taglib uri = "http://www.springframework.org/tags/form" prefix = "sf"%>
<!-- context path  -->
<% String context=request.getContextPath(); %>
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/${appMode}/tilescommon/css/fioristyle.css?appVer=${appVer}"/>
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/${appMode}/tilescommon/css//borderTab.css?appVer=${appVer}">
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/${appMode}/tilescommon/css/mobile.css?appVer=${appVer}">
<script src="<%=context%>/resources/${appMode}/commons/js/springform.js?appVer=${appVer}"></script>
<script src="<%=context%>/resources/${appMode}/commons/js/utility.js?appVer=${appVer}"></script>
<script src="<%=context%>/resources/${appMode}/commons/js/loadList.js?appVer=${appVer}"></script>
<script src="<%=context%>/resources/${appMode}/commons/js/loadItemList.js?appVer=${appVer}"></script>
<script src="<%=context%>/resources/${appMode}/commons/js/formFields.js?appVer=${appVer}"></script>
<script src="<%=context%>/resources/${appMode}/partner/js/uploadFile.js?appVer=${appVer}"></script>
<script src="<%=context%>/resources/${appMode}/transaction/js/tahdrScheduling.js?appVer=${appVer}"></script>
 <script src="<%=context%>/resources/${appMode}/tilescommon/js/bootstrap-datetimepicker.js?appVer=${appVer}"></script>
  <script src="<%=context%>/resources/${appMode}/tilescommon/js/kendo.all.min.js?appVer=${appVer}"></script> 
            <script>
                $(document).ready(function() {
                    $("#tabstrip").kendoTabStrip();
                });
                /* $('.leftPaneData').paginathing({
        			perPage: 6,
        		}); */
            </script>
            
        <div class="full-container">

	
			<input type="hidden" id="myTenderUrl" class="myTenderUrl" value="${myTenderUrl}" />
            <!-- left-side start-->
            <div class="clearfix"></div>
            <div id="mobile_first_container" class="left-side col-md-3 no-marg">
                <div class="detailsheader">
        	<div class="col-sm-3 text-center brdrgt"><label>${documentType} Scheduling Report (0)</label></div>
        </div>               
                <div class="input-group">
                    <div class="input-group-btn search-panel">
                        <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">
                            <span id="search_concept">Filter by</span> <span class="caret"></span>
                        </button>
                         <ul class="dropdown-menu" role="menu" id="filterById">
                         <li class="divider"></li>
                      <li><a href="#contains"><input type="radio" name="filterBy" value="tahdrCode" data-type="${documentType} Code" checked/> ${documentType} Code</a></li>
                      <li><a href="#all"><input type="radio" name="filterBy" data-type="${documentType} Title" value="title" /> ${documentType} Title</a></li>
                       <li><a href="#all"><input type="radio" name="filterBy" data-type="${documentType} Status" value="tahdrStatusCode" /> ${documentType} Status</a></li>
                    </ul>
                    </div>
              
                    <input type="text" id="searchLiteralId" class="form-control" name="x" placeholder="Search term...">
                    <span class="input-group-btn">
                    <button class="btn btn-default" type="button" id="searchBtn"><span class="glyphicon glyphicon-search"></span></button>
                   
					<span class="glyphicon glyphicon-plus"></span>
                    </span>
                </div>
                <c:if test="${documentType.equalsIgnoreCase('Tender')}">
                 <input type="hidden" class="documentType" value="${documentType}" />
					<div class="btn-group btnmrg" data-toggle="buttons">
			         <label class="btn btn-primary" id="procurementToggle">
			         <input type="radio" name="tenderTypeCodeToggle"  value="PT">
			         <span class="glyphicon glyphicon-ok"></span> Procurement 
			         </label>
			         <label class="btn btn-primary active" id="worksToggle">
			         <input type="radio" name="tenderTypeCodeToggle" checked id="worksCheckBoxId" value="WT">
			         <span class="glyphicon glyphicon-ok"></span> Works
			         </label>
			      </div>
				</c:if>
				<c:if test="${documentType.equalsIgnoreCase('Auction')}">
				 <input type="hidden" class="documentType" value="${documentType}" />
					<div class="btn-group btnmrg" data-toggle="buttons">
			         <label class="btn btn-primary" id="forwardToggle">
			         <input type="radio" name="tenderTypeCodeToggle"  value="FA">
			         <span class="glyphicon glyphicon-ok"></span> Forward 
			         </label>
			         <label class="btn btn-primary active" id="reverseToggle">
			         <input type="radio" name="tenderTypeCodeToggle" checked id="auctionsCheckBoxId" value="RA">
			         <span class="glyphicon glyphicon-ok"></span> Reverse
			         </label>
			      </div>
				</c:if>
			       <ul id="leftPaneData" class="nav nav-tabs tabs-left leftPaneData">
            
                   </ul>
               
                <p id="pagination-here"></p>
                <div class="clearfix"></div>
            </div>
            <!-- left-side end-->

            <!-- right-side start-->
            <div id="mobile_second_container" class="right-side col-md-9 no-marg">
            <div class="detailsheader toptabbrd">
        	<div class="col-sm-9 text-center"><label>${documentType} Scheduling Details</label></div>
        </div>
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
                                            <li class="k-state-active">${documentType} Opening Schedule</li>
                                        </ul>

                                        <!--fields of field group 1  -->
                                        <div>
                                       <sf:form id="tenderSchedulingForm" modelAttribute="tahdrSchedule" method="POST">
                                        <div class="detailscont">
    									</div>
                                            <div class="form-group">
                                                <div class="col-sm-12">
                                                    <h4><b>${documentType} Opening Schedule</b></h4>
                                                    <hr>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                            <input style="display: none" id="tahdrId" class="tahdrId" name="tahdr.tahdrId">
                                            <input type="text" id="tahdrCode" name="tahdr.tahdrCode" style="display: none;">
                                             <input style="display: none" id="tahdrDetailId" class="tahdrDetailId" name="tahdrDetailId">
                                                <div class="col-sm-3">
                                                 
                                                        <label>${documentType} Code:</label>
                                                        <span class="detspan" id="tenderCode" ></span>
                                               
                                                    </div>
                                                    
                                               
                                                <div class="col-sm-3 readonly" id="scheduleListDivId">
                                                    <div class="styled-input">
                                                        <select id="scheduleList" name="tahdrScheduling" class="dropDown"></select>
                                                        <label>${documentType} Schedule<span class="red">*</span></label>
                                                        <span></span>
                                                    </div>
                                                    <div class="clearfix"></div>
                                                </div>
                                                
                                                 <div class="col-sm-3" id="submissionDateDivId" style="display: none ;">
                                                        <label id="lastDateId">Last Submission Date And Time<span class="red">*</span></label>
                                                        <div class="input-group date form_datetime3">				
							                                <input type="text" class="form-control requiredField" id="closingDateTime" > <!-- name="lastC1SubmissionDate" -->
												            <span class="input-group-addon"><span class="glyphicon glyphicon-th"></span></span>
						                                </div>
                                                </div>
                                                <div class="col-sm-3"  >
                                                        <label id="OpeningDateId">Opening Date And Time<span class="red">*</span></label>
                                                       
                                                        <div class="input-group date form_datetime2" id="openingDateDivId3">				
							                                <input type="text" class="form-control requiredField" id="openingDateTime" > <!-- name="lastC1SubmissionDate" -->
												            <span class="input-group-addon"><span class="glyphicon glyphicon-th"></span></span>
						                				</div>
                                                </div>
                                               <!--  <div class="col-sm-3" id="openingDateDivId2">
                                                        <label>Opening Date & Time<span class="red">*</span></label>
                                                       
                                                        <div class="input-group date form_datetime2" >				
							                                <input type="text" class="form-control requiredField" id="openingDateTime" > name="lastC1SubmissionDate"
												            <span class="input-group-addon"><span class="glyphicon glyphicon-th"></span></span>
						                				</div>
                                                </div> -->
                                               <div class="col-sm-3">
                                                 
                                                        <label>${documentType} Status:</label>
                                                        <span class="detspan" id="tenderStatus" ></span>
                                               
                                                    </div>

                                            </div>
                                             
                                           <!--  <div class="form-group">
                                                <div class="col-sm-4">
                                                    <div class="styled-input">
                                                        <select id="companyType" name="companyType" required></select>
                                                        <label>Select Opening Authorities<span class="red">*</span></label>
                                                        <span></span>
                                                    </div>
                                                    <div class="clearfix"></div>
                                                </div>
                                                <div class="col-sm-4">
                                                    <div class="styled-input">
                                                        <select id="companyType" name="companyType" required></select>
                                                        <label>Tender Opening Chairman<span class="red">*</span></label>
                                                        <span></span>
                                                    </div>
                                                    <div class="clearfix"></div>
                                                </div>
                                                <div class="col-sm-4">
                                                    <button class="btn btn-default" style="margin-top: 20px;">Upload</button>
                                                </div>
                                            </div> -->
                                            <div class="col-sm-12 text-center positionABS" style="margin-bottom:10px;">
                                        <button class="btn btn-info" onclick="savetahdrScehdule(event)">Save</button>
                                    </div>
                                            </sf:form>
                                        </div>
                                        <!--fields of field group 1  -->
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
        <script type="text/javascript">
    
	    var today = new Date();
	    $('.form_datetime3').datetimepicker({
	    	weekStart: 1,
			autoclose: 1,
			startView: 2,
			forceParse: 0,
			startDate :  '+1d',
			format: 'dd-mm-yyyy HH:ii',
	        showMeridian: 1,
			orientation: 'auto',
			pickerPosition: "top-left",
	        widgetPositioning:{
	            horizontal: 'auto',
	            vertical: 'bottom'
	        } 
	    }); 
        </script>
<!--

//-->
