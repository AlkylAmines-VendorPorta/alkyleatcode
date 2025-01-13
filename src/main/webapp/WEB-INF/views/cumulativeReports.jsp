<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@taglib uri = "http://www.springframework.org/tags/form" prefix = "sf"%>
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/${appMode}/tilescommon/css/fioristyle.css?appVer=${appVer}"/>
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/${appMode}/tilescommon/css//borderTab.css?appVer=${appVer}">
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/${appMode}/tilescommon/css/mobile.css?appVer=${appVer}">
<script src="<%=request.getContextPath()%>/resources/${appMode}/tilescommon/js/bootstrap-datetimepicker.js?appVer=${appVer}"></script>
<script src="<%=request.getContextPath()%>/resources/${appMode}/tilescommon/js/kendo.all.min.js?appVer=${appVer}"></script>
			<style>.panel-body{padding:0px 10px;}
			.bidpBody{padding:10px !important;}
			   #bidSheetTblId tbody td, #bidSheetTblId thead th {border-right: 1px solid #000;}
   #bidSheetTblId {border: 1px solid #000 !important;}</style>
       		
            <script>
                $(document).ready(function() {
                	$("#tabstrip").kendoTabStrip();
                    $("#tabstrip2").kendoTabStrip();
                    $("#tabstrip3").kendoTabStrip();
                    $("#tabstrip4").kendoTabStrip();
                    $("#tabstrip5").kendoTabStrip();
                    $("#tabstrip6").kendoTabStrip();
                    $('#leftPane').paginathing({perPage: 6});
                });               
            </script>
   
        <div class="full-container"></div>
       
            <!-- left-side start-->
            <div class="clearfix"></div>
           <%-- <input type="hidden" id="myTenderTahdrId" value="${tahdrId}">
           <input type="hidden" id="myTenderTenderType" value="${tenderType}"> --%>
            <div id="mobile_first_container" class="left-side col-md-3 no-marg">   
            <div class="detailsheader leftPaneHeader">
        		<div class="col-sm-3 text-center brdrgt"><label> Cumulative Report (0)</label></div>
       		</div>        
               <div class="input-group">
                <div class="input-group-btn search-panel">
                    <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">
                    	<span id="search_concept">Filter by</span> <span class="caret"></span>
                    </button>
		                   <ul class="dropdown-menu" role="menu" id="filter">
							<li data-documenttype="TENDER" class='active filterByTahdrCode'><a href="#">Tender</a></li>
							<li class="divider"></li>
							<li data-documenttype="AUCTION" class='filterByTahdrCode'><a href="#">Auction</a></li>
							<li class="divider"></li>
							<li data-documenttype="QUICK_AUCTION" class='filterByTahdrCode'><a href="#">Quick Auction</a></li>
							<li class="divider"></li>
							<li data-documenttype="QUICK_RFQ" class='filterByTahdrCode'><a href="#">Quick RFQ</a></li>
							<li class="divider"></li>
							<li data-documenttype="RFQ" class='filterByTahdrCode'><a href="#">RFQ</a></li>
						</ul>
                </div>
                <input type="hidden" name="search_param" value="all" id="search_param">         
                <input type="text" class="form-control" name="x" id="searchLiteralId" placeholder="Search term...">
                <span class="input-group-btn">
                    <button class="btn btn-default" type="button" id = "searchBtn"><span class="glyphicon glyphicon-search"></span></button>
                    <button class="btn btn-default addnewlist" type="button"><span class="glyphicon glyphicon-plus"></span></button>
                    <button class="btn btn-default" onClick="loadNewList(event)"type="button"><span class="glyphicon glyphicon-refresh"></span></button>
                </span>
            </div>
             <div class="btn-group btnmrg" data-toggle="buttons" id="toggleBtn">
				   
                </div>
	           
                 <ul id="leftPaneData" class="nav nav-tabs tabs-left leftPaneData">
            
                   </ul>
               
                <p id="pagination-here"></p>
               
                <div class="clearfix"></div>
            </div>
            <!-- left-side end-->

            <!-- right-side start-->
            <div id="mobile_second_container" class="right-side col-md-9 no-marg">
            <div class="detailsheader toptabbrd">
        		<div class="col-sm-9 text-center"><label>Bids Details</label></div>
        	 </div>
        	 
        	 
                <div class="clearfix"></div>
                <div class="tab-content">
                    <!-- Master tab start-->

                    <div class="tab-pane active in" id="">
                        <div class="card">
                             <div class="posrelative">
                               <div class="demo-section k-content">
                                    <div id="tabstrip" class="Firsttab">
                                        <ul>
                                            <!-- tabs -->
                                            <li id="tenderDetailTab" data-parentTab="" class="k-state-active tenderDetailsTab" id="tenderTabId" onclick="">Base Info</li>
                                            
                                            <li class="cumulativeReportsTab" onclick="loadCumulativeData()">Cumulative Reports</li>
                                            
                                           
                                        </ul>

                                        <!--fields of field group 1  -->
                                        <div>
                                        <div class="detailscont">
  							 				
    									</div>
                                            <div class="form-group">
                                                <div class="col-sm-12">
                                                    <h4><b>Short Details</b></h4>
                                                    <hr>
                                                </div>
                                            </div>
                                           
                                            <div class="form-group">
                                                
                                            </div>
                                             <div class="col-sm-12">
                                             	<form id="tenderDetailsForm">
                                             	<input type="text" name="tahdrId" id="tahdrId" style="display: none;">
		                                            <div class="form-group">
		                                                <div class="col-sm-4">
		                                                        <label>Code No:</label>
		                                                         <span class="detspan" id="tenderNo"></span>
		                                                </div>
		                                                 <div class="col-sm-4">
		                                                        <label>Created On:</label>
		                                                         <span class="detspan" id="createdOn"></span>
		                                                </div> 
		                                                <div class="col-sm-4">
		                                                        <label>Created By:</label>
		                                                         <span  class="detspan" id="createdBy"> </span>
		                                                </div>
		                                              </div>  
		                                              <!-- <div class="form-group">
		                                                <div class="col-sm-4">
		                                                        <label>Approved On:</label>
		                                                         <span class="detspan" id="approvedOn"></span>
		                                                </div>
		                                                 <div class="col-sm-4">
		                                                        <label>Approved By:</label>
		                                                         <span class="detspan" id="ApprovedBy"></span>
		                                                </div> 
		                                                <div class="col-sm-4">
		                                                        <label>Approval Comment:</label>
		                                                         <span  class="detspan" id="ApprovedComment"> </span>
		                                                </div>
		                                              </div>   -->
		                                              <div class="form-group">
		                                              <div class="col-sm-4">
		                                                        <label>Status:</label>
		                                                        <span class="detspan" id="status"></span>
		                                                </div>
		                                                
		                                                <div class="col-sm-4">
		                                                        <label>No of Bidder:</label>
		                                                        <span class="detspan" id="noBidder"></span>
		                                                </div>
		                                                <div class="col-sm-4">
		                                                        <label>Bid Type:</label>
		                                                        <span class="detspan" id="tenderBidType"></span>
		                                                </div> 
		                                              </div>                                                     
		                                              <div class="form-group">
		                                              <div class="col-sm-4">
		                                                        <label>Details Document:</label>
		                                                        <a data-url="<%=request.getContextPath()%>/download" class="" id="downloadTenderDoc"></a>
		                                                </div> 
                                                    	
                                             		</div> 
                                             		</form>
                                             		
                                             </div>
                                            <div class="clearfix"></div>
                                        </div>
                                        <!--fields of field group 1  -->
                                      <!--sub of field group 1  -->
                                       <div class="cumulativeReportsTab">
                                       
                                            <div class="form-group">
                                                <div class="col-sm-12">
                                                    <h4><b>Cumulative Sheet</b></h4>
                                                    <hr>
                                                </div>
                                            </div>
                                             <sf:form id="gennerateBidSheetDoc">
						                      </sf:form>
                                                    <div class="col-sm-12">
                                                    	<button onclick="downloadCumulativeDetailsPdf(this)" class="btn btn-default pdfDownloadBtnId" data-type="csv">Excel</button>
                                                    	<button onclick="downloadCumulativeDetailsPdf(this)" class="btn btn-default pdfDownloadBtnId" data-type="pdf">PDF</button>
                                                    </div> 
                                                    
                                                    <div style="display:none;">
													    <div id="tip">
													        <div id="tooltipText">TEST</div>
													    </div>
													</div>
                                            <div class="col-sm-12">
                                               <table id="bidSheetTblId" class="v_listolivefbider table table-striped tableResponsive table-bordered" style="width:100% !important;">
									         	 <thead>
                                            		<tr>
                                            		     <th>Material</th>
										         		 <th>Bidder</th>
										         		 <th>Amount</th>
									         		</tr>
									         		</thead>
									         		<tbody>  
									         		
                                            
									         </tbody>
									         </table>		
                                        <!--fields of field group 1  -->
                                </div>
                                <!-- Master tab end-->

                            </div>
                                      
                                      </div>
                                </div>
                                

                            </div>
                        </div>
                        <!-- right-side end-->
                    </div>
                </div>
            </div>
            <div class="clearfix"></div>
<script src="<%=request.getContextPath()%>/resources/Dashboard/jquery-ui.min.js"></script>
<script src="<%=request.getContextPath()%>/resources/${appMode}/commons/js/jquery.table.transpose.min.js"></script>
<script src="<%=request.getContextPath()%>/resources/${appMode}/tilescommon/js/jspdf.min.js?appVer=${appVer}"></script>
<script src="<%=request.getContextPath()%>/resources/${appMode}/tilescommon/js/jspdf.plugin.autotable.min.js?appVer=${appVer}"></script>
<script src="<%=request.getContextPath()%>/resources/${appMode}/commons/js/tableHTMLExport.js"></script>
<script src="<%=request.getContextPath()%>/resources/Dashboard/cumulativeReports.js"></script>
<script src="<%=request.getContextPath()%>/resources/Dashboard/contructCumulativeReports.js"></script>
<script src="<%=request.getContextPath()%>/resources/${appMode}/commons/js/formFields.js?appVer=${appVer}"></script>
<script src="<%=request.getContextPath()%>/resources/${appMode}/tilescommon/js/bootstrap-datetimepicker.js?appVer=${appVer}"></script> 
<script src="<%=request.getContextPath()%>/resources/${appMode}/commons/js/utility.js?appVer=${appVer}"></script>
<script src="<%=request.getContextPath()%>/resources/${appMode}/commons/js/liCache.js?appVer=${appVer}"></script>
