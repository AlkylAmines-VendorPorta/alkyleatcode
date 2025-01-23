<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@taglib uri = "http://www.springframework.org/tags/form" prefix = "sf"%>

<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/tilescommon/css/fioristyle.css"/>
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/tilescommon/css//borderTab.css"/>
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/tilescommon/css/mobile.css"/>
<script src="<%=request.getContextPath()%>/resources/${appMode}/tilescommon/js/kendo.all.min.js?appVer=${appVer}"></script>

<script src="<%=request.getContextPath()%>/resources/${appMode}/tilescommon/js/kendo.all.min.js?appVer=${appVer}"></script> 

<script>
	$(document).ready(function() {
		$("#tabstrip").kendoTabStrip();
		$("#tabstrip2").kendoTabStrip();
		$("#tabstrip3").kendoTabStrip();
		$("#tabstrip4").kendoTabStrip();
		$("#tabstrip5").kendoTabStrip();
		$("#tabstrip6").kendoTabStrip();
	});
</script>

<style>
table tbody td {
min-width: 150px !important;
}
table thead th {
min-width: 150px !important;
}
.canvasjs-chart-canvas{top: 0px;
    bottom: 50px;}
</style>


            
        <div class="full-container">
            <!-- left-side start-->
            <div class="clearfix"></div>
            <div id="mobile_first_container" class="left-side col-md-3 no-marg">
                <div class="detailsheader leftPaneHeader">
        	<div class="col-sm-3 text-center brdrgt"><label></label></div>
        </div>               
                <div class="input-group">
                    <div class="input-group-btn search-panel">
                        <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">
                            <span id="search_concept">Filter by</span> <span class="caret"></span>
                        </button>
                       <ul class="dropdown-menu" role="menu">
                      <li><a href="#contains"><input type="radio" name="filterBy" value="tahdrCode" checked/> Auction Code</a></li>
                      <li class="divider"></li>
                      <li><a href="#all"><input type="radio" name="filterBy" value="title" /> Title</a></li>
                    </ul>
                    </div>
                    <input type="hidden" name="search_param" value="all" id="search_param">         
                <input type="text" class="form-control" name="x" id="searchLiteralId" placeholder="Search term...">
                <span class="input-group-btn">
                    <button class="btn btn-default" type="button" id = "searchBtn"><span class="glyphicon glyphicon-search"></span>
                    </button>
				<button class="btn btn-default" onClick="loadNewList(event)"type="button">
					<span class="glyphicon glyphicon-refresh"></span>
				</button>
                    </span>
                </div>
                <%--  <c:if test="${documentType.equalsIgnoreCase('Tender')}">
					<div class="btn-group btnmrg" data-toggle="buttons">
							    <label class="btn btn-primary toggleTab" openTab="tenderTab">
									<input type="radio" name="tenderTypeCodeToggle" value="PT" id="option3">
									<span class="glyphicon glyphicon-ok"></span> Procurement 
                                </label>
                                <label class="btn btn-primary active  toggleTab" openTab="tenderTab">
                                     <input type="radio" name="tenderTypeCodeToggle" checked="checked" value="WT" id="option3">
                                      <span class="glyphicon glyphicon-ok"></span> Works
                                </label>
                            </div>
				</c:if> --%>
				<%-- <c:if test="${documentType.equalsIgnoreCase('Auction')}"> --%>
					<div class="btn-group btnmrg" data-toggle="buttons">
							    <label class="btn btn-primary toggleTab" openTab="tenderTab">
									<input type="radio" name="tenderTypeCodeToggle" value="QFA" id="option3">
									<span class="glyphicon glyphicon-ok"></span> Quick Forward 
                                </label>
                                <label class="btn btn-primary active  toggleTab" openTab="tenderTab">
                                     <input type="radio" name="tenderTypeCodeToggle" checked="checked" value="QRA" id="option3">
                                      <span class="glyphicon glyphicon-ok"></span> Quick Reverse
                                </label>
                            </div>
				<%-- </c:if> --%>
                <ul id="leftPaneData" class="nav nav-tabs tabs-left leftPaneData">
                   
                </ul>
                 <p id="pagination-here"></p>  
                <div class="clearfix"></div>
            </div>
            <!-- left-side end-->

            <!-- right-side start-->
            <div id="mobile_second_container" class="right-side col-md-9 no-marg">
            <div class="detailsheader toptabbrd">
        	<div class="col-sm-9 text-center"><label>Bid Details</label></div>
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
                                            <li class="k-state-active tenderTab" id="tenderDetailTab" >Auction</li>     
                                            <li data-parentTab="tenderDetailTab" id="materialTabId" class="toggleTab tenderMaterialTab materialUlTabId" openTab="materialTab" onclick="loadTahdrMaterial(this)">Auction Statistic</li>
                                            <!-- <li class="statisticTab" id="statisticTabId" onclick="loadStatisticData(this)">Statistics </li> -->                                           
                                        </ul>
										<div>
										 <div class="detailscont">
  							 				
    									</div>
    									   <div class="form-group">
                                                <div class="col-sm-12">
                                                    <h4><b>Auction Details</b></h4>
                                                    <hr>
                                                </div>
                                            </div>
    									      <div class="form-group">
    									<form id="tahdrForm" class="readonly">
    									<div class="form-group">
    									 <input type="text" style="display: none;" id="tahdrId" name="" >
                                                <div class="col-sm-4">
                                                 
                                                        <label>Auction No:</label>
                                                        <span class="detspan" id="tenderNo" ></span>
                                                </div>
                                                <div class="col-sm-4">
                                                 <label>Auction Version:</label>
                                                        <span class="detspan" id="tenderVersion" ></span>
                                                   
                                                </div>
                                                <div class="col-sm-4">
                                                 <label>Description:</label>
                                                        <span class="detspan" id="description" ></span>
                                                  
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <!-- <div class="col-sm-4">
                                                 <label>EMD Fees:</label>
                                                        <span class="detspan" id="emdFee" ></span>
                                                   
                                                </div>   -->
                                                <div class="col-sm-6">
                                                 <label>Tender Status:</label>
                                                        <span class="detspan" id="status" ></span>
                                                   
                                                </div>                                                     
                                            </div>
                                            </form>
										</div>
										</div>
                                        <!--fields of field group 1  -->
                                        <div>
                                        <div class="detailscont">
  							 				
    									</div>
                                       
                                            <div id="tabstrip2">
                                       <!--  <ul class="materialUlTabId"> -->
                                            <!-- tabs -->
                                             <ul >
                                            <li class="k-state-active materialTab" onclick="return submitWithParam('getBidSheetAuctionMaterialListById','tahdrId','loadTahdrMaterialListForView');">Material</li>                                            
                                            <li class="bidSheetTabDiv" onclick="return submitWithTwoParam('getBidSheetByTahdrMaterialId','tahdrId','itemDetailForm #tahdrMaterialId','loadBiddersListForLiveBid');">Bid Sheet</li>      
                                            <li class="statisticTab" id="statisticTabId" onclick="loadBidStatisticData(this)">Statistics </li><!-- onclick="loadStatisticDataWithMaterial(this)" -->
                                            <li class="chartTab" id="chartTabId" onclick="return submitWithParam('getBidHistoryByTahdrMaterialId','itemDetailForm #MaterialIdForChart','loadHistoryChart');">Previous Bid History</li>                                       
                                            <li class="PiechartTab" id="PiechartTab" onclick="return submitWithTwoParam('getBidderParticipation','tahdrId','itemDetailForm #tahdrMaterialId','loadBidderParticipation');">Bidder Participation</li>
                                        </ul>

                                        <!--fields of field group 1  -->
                                        
                            <div>
                                      
                                            <div class="form-group">
                                                <div class="col-sm-12">
                                                    <h4><b>Auction Material</b></h4>
                                                    <hr>
                                                </div>
                                            </div>
                                         <form id="itemDetailForm">
                                            <div class="form-group">
                                            <input style="display: none;" id="tahdrMaterialId">
                                            <input style="display: none;" id="MaterialIdForChart">
                                                <div class="col-sm-4">
                                                        <label>Material Name:</label>
                                                        <span class="detspan" id="matName" ></span>
                                                </div>
                                                <div class="col-sm-4">
                                                        <label>Material Code:</label>
                                                        <span class="detspan" id="matCode" ></span>
                                                </div>
                                                <div class="col-sm-4">
                                                        <label>Unit:</label>
                                                         <span class="detspan" id="uom" ></span>
                                                </div>
                                              </div>  
                                              <div class="form-group">
                                                <div class="col-sm-4">
                                                        <label>Description:</label>
                                                         <span class="detspan" id="description" ></span>
                                                </div>
                                                <div class="col-sm-4">
                                                        <label>Required Quantity:</label>
                                                         <span class="detspan" id="reqQuantity" ></span>
                                                </div>
                                                 <div class="col-sm-4">
                                                        <label>Specification Version:</label>
                                                         <span class="detspan" id="specVersion" ></span>
                                                </div>
                                              </div> 
                                              <div class="form-group">
                                                <div class="col-sm-4">
                                                        <label>Auction Material Type:</label>
                                                         <span class="detspan" id="tenderMatType" ></span>
                                                </div>
                                              </div> 
                                              </form>
                                <!-- Master tab end-->

                            </div>
                            <div>
                                       
                                            <div class="form-group">
                                                <div class="col-sm-6">
                                                    <h4><b>Live Bidder List</b></h4>
                                                    <hr>
                                                </div>
                                                 <div class="col-sm-6 text-right">
                                                 	<button onclick="downloadBidSheetPdf(event)" class="btn btn-default pdfDownloadBtnId">Export Bid Sheet to PDF</button>
                                                 	<button onclick="downloadCummulativeBidSheetPdf(event)" class="btn btn-default pdfDownloadBtnId">Cummulative Bid Sheet</button>
                                                 </div>
                                            </div>
                                             <sf:form id="gennerateBidSheetDoc">
						                      </sf:form>
                                                    
                                             <div class="form-group">
                                              <div class="col-sm-12">
                                              <button class="auctionbtn btn btn-default auctionSpecificBtn" type="button" onclick="refresh()" >
										        <span id="" class="glyphicon glyphicon-refresh"></span></button>
                                		      <button type="button"  id="endAuctionBtnId" data-status="true" class="btn btn-default bluebutton auctionSpecificBtn">End Auction</button>
                                              </div>
                                             <div class="timerbid">
	                                            <label class="bluetext">Auto Refresh in</label>
	                                            <label id="autoRefresh" class="greentext" data-callback="">0s</label>
                                            </div>
                                            
                                            <div class="timerbid">
	                                            <label class="bluetext">Remaining Time: </label>
	                                            <label id="auctionTimer" class="red">0s</label>
                                            </div>
                                            </div>
                                            <div class="col-sm-12">
                                               <table class="listolivefbider table table-striped tableResponsive table-bordered" style="width:100% !important;">
									         	<thead>
									         		
                                            		<tr>
										         		<th>Partner Name</th>
										         		<th>RANK</th>
										         		<!-- <th>Company Name</th> -->
										         		<th>Qty. Offered</th>
										         		<th>Bid Price</th>
										         		<!-- <th>Per Unit Freight charges(in Rs)</th>
										         		<th>Per Unit TIC(In Rs)</th>
										         		<th>Per unit FDD Price Without GST</th>
										         		<th>Total Ex-Works Price</th>
										         		<th>Total Freight charges</th>
										         		<th>Total TIC</th>
										         		<th>Total FDD Price Without GST</th>
										         		<th>Total GST</th>
										         		<th>Per unit FDD With GST</th>
										         		<th>Total FDD With GST</th> -->
									         		</tr>
									         		</thead>
									         		<tbody>
									         		
									         </tbody>
									         </table>		
                                        <!--fields of field group 1  -->
                                </div>
                                
                                <!-- <div class="col-sm-6"><div class="chartContainer1" style="height: 300px; overflow: hidden; width: 100%;"></div></div> -->
                                <!-- Master tab end-->

                            </div>
                             <div>
    									   <div class="form-group">
                                                <div class="col-sm-12">
                                                    <h4><b>Statistics Details</b></h4>
                                                    <hr>
                                                </div>
                                            </div>
    									<div class="col-sm-6"><div class="chartContainer1" style="height: 300px; overflow: hidden; width: 100%;"></div></div>
										
                        </div>
                           <!--   <div>
    									   <div class="form-group">
                                                <div class="col-sm-12">
                                                    <h4><b>Statistics Details</b></h4>
                                                    <hr>
                                                </div>
                                            </div>
    									      <div class="form-group">
    									<form id="statisticForm" class="readonly">
    									 <input type="text" style="display: none;" id="tahdrId" name="" >
    									 <div class="form-group">
                                                <div class="col-sm-4" id="bidderCountDiv">
                                                <label class="col-sm-9">Bidder Count:</label>
                                                <span class="detspan col-sm-3" id="bidderCount"></span>
                                                </div>
                                                </div>
                                                 <div class="form-group">
                                                <div class="col-sm-4" id="bidderTechnicalCountDiv">
                                                 <label class="col-sm-9">Technical Bid Count:</label>
                                                        <span class="detspan col-sm-3" id="bidderTechnicalCount" ></span>
                                                   
                                                </div>
                                                </div>
                                                
                                            
                                            <div class="form-group" id="bidderPriceCountDiv">
                                                <div class="col-sm-4">
                                                 <label class="col-sm-9">Price Bid Count:</label>
                                                        <span class="detspan col-sm-3" id="bidderPriceCount" ></span>
                                                   
                                                </div>                                                
                                            </div>
                                            <div class="form-group" id="bidderAllCountDiv">
                                                <div class="col-sm-4">
                                                 <label class="col-sm-9">All Bid Count:</label>
                                                        <span class="detspan col-sm-3" id="bidderAllCount" ></span>
                                                   
                                                </div>                                                
                                            </div>
                                             <div class="form-group" id="bidderPriceBidOpenedDiv">
                                                <div class="col-sm-4">
                                                 <label class="col-sm-9">PriceBid Opened Bidder Count:</label>
                                                        <span class="detspan col-sm-3" id="bidderPriceBidOpened" ></span>
                                                   
                                                </div>                                                
                                            </div>
                                            </form>
										</div>
                        </div> -->
                        
                        <div>
                        
                        <div class="col-sm-6">
                            <div style="height: 300px; width: 100%; ">
                        	  <div class="chartContainer" style="height: 250px;" id="chartContainer" ></div> 
                        	</div>
                        </div>
                      
                        <div class="clearfix"></div>
                        <br>
                        </div>
                         <div>
                        
                           <div class="col-sm-6">
                            <div style="height: 300px; width: 100%; ">
                        	  <div class="PiechartContainer" style="height: 250px;" id="PiechartContainer" ></div> 
                        	</div>
                        </div>
                        <div class="clearfix"></div>
                        <br>
                        </div>
                                            
                                <!-- Master tab end-->

                            </div>
                           
										</div>
                                        <!--fields of field group 1  -->
                        </div>
                        <!-- right-side end-->
                    </div>
                </div>
            </div>
            </div>
            </div>
            </div>
            </div>
<script type="text/javascript" src="resources/commons/js/jquery.canvasjs.min.js"></script> 
<script src="<%=request.getContextPath()%>/resources/transaction/js/quickBidSheet.js"></script>
<script src="<%=request.getContextPath()%>/resources/commons/js/formFields.js"></script>
<script src="<%=request.getContextPath()%>/resources/tilescommon/js/bootstrap-datetimepicker.js"></script> 
<script src="<%=request.getContextPath()%>/resources/commons/js/utility.js"></script> 
<script src="<%=request.getContextPath()%>/resources/commons/js/liCache.js"></script>
<script src="<%=request.getContextPath()%>/resources/tilescommon/js/timer.jquery.js"></script>
