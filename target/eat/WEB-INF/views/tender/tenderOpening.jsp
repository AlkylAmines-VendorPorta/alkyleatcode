<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@taglib uri = "http://www.springframework.org/tags/form" prefix = "sf"%>
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/${appMode}/tilescommon/css/fioristyle.css?appVer=${appVer}"/>
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/${appMode}/tilescommon/css//borderTab.css?appVer=${appVer}">
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/${appMode}/tilescommon/css/mobile.css?appVer=${appVer}">
<script src="<%=request.getContextPath()%>/resources/${appMode}/tilescommon/js/kendo.all.min.js?appVer=${appVer}"></script>
			<style>.panel-body{padding:0px 10px;}</style>
       		
            <script>
                $(document).ready(function() {
                    $("#tabstrip").kendoTabStrip();
                    $('#leftPane').paginathing({perPage: 6});
                });               
            </script>
   
        <div class="full-container">
        <input type="hidden" id="myTenderUrl" class="myTenderUrl" value="${myTenderUrl}" />
            <!-- left-side start-->
            <div class="clearfix"></div>
           <%-- <input type="hidden" id="myTenderTahdrId" value="${tahdrId}">
           <input type="hidden" id="myTenderTenderType" value="${tenderType}"> --%>
            <div id="mobile_first_container" class="left-side col-md-3 no-marg">   
            <div class="detailsheader">
        		<div class="col-sm-3 text-center brdrgt"><label>Tender Opening Report (0)</label></div>
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
                <c:if test="${documentType.equalsIgnoreCase('Tender')}">
                <input type="hidden" class="documentType" value="${documentType}" />
					<div class="btn-group btnmrg" data-toggle="buttons">
				         <label class="btn btn-primary toggleTab" openTab="tenderOpeningTab" id="procurementToggle">
						         <input type="radio" name="tenderTypeCodeToggle" id="procurememtRadioId" value="PT">
						         <span class="glyphicon glyphicon-ok"></span> Procurement 
				         </label>
				         <label class="btn btn-primary active toggleTab" openTab="tenderOpeningTab" id="worksToggle">
						         <input type="radio" name="tenderTypeCodeToggle" id="worksRadioId" checked value="WT">
						         <span class="glyphicon glyphicon-ok"></span> Works
				         </label>
				      </div>
				</c:if>
				<c:if test="${documentType.equalsIgnoreCase('Auction')}">
				<input type="hidden" class="documentType" value="${documentType}" />
					<div class="btn-group btnmrg" data-toggle="buttons">
				         <label class="btn btn-primary toggleTab" openTab="tenderOpeningTab" id="forwardToggle">
						         <input type="radio" name="tenderTypeCodeToggle" id="procurememtRadioId" value="FA">
						         <span class="glyphicon glyphicon-ok"></span> Forward 
				         </label>
				         <label class="btn btn-primary active toggleTab" openTab="tenderOpeningTab" id="reverseToggle">
						         <input type="radio" name="tenderTypeCodeToggle" id="worksRadioId" checked value="PT">
						         <span class="glyphicon glyphicon-ok"></span> Reverse
				         </label>
				      </div>
				</c:if>
	           
                <ul id="leftPane" class="nav nav-tabs tabs-left example">
                  
                </ul>
               
                <div class="clearfix"></div>
            </div>
            <!-- left-side end-->

            <!-- right-side start-->
            <div id="mobile_second_container" class="right-side col-md-9 no-marg">
            <div class="detailsheader toptabbrd">
        		<div class="col-sm-9 text-center"><label>${documentType} Opening Details</label></div>
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
                                            <li class="k-state-active tenderOpeningTab" id="tenderBidOpeningTabId" onclick="return submitIt('tenderOpeningSearchForm','searchOpeningTenderList','searchOpeningTenderListResp');">${documentType} Bid Opening</li>
                                            <li class="wizardTab readonly">${documentType} Opening Wizard of ${documentType} </li>
                                            <li class="viewBidTab toggleTab readonly" id="viewBidParentTabId" openTab="tenderMaterialTab" onclick="return submitWithParam('getTAHDRMaterialListByTahdrId','tahdrId','loadTahdrMaterialListForView')">View Bid </li>
                                        </ul>

                                        <!--fields of field group 1  -->
                                        <div>
                                        <div class="detailscont">
  							 				
    									</div>
                                            <div class="form-group">
                                                <div class="col-sm-12">
                                                    <h4><b>${documentType} Bid Opening</b></h4>
                                                    <hr>
                                                </div>
                                            </div>
                                           
                                            <div class="form-group">
                                             <sf:form id="tenderOpeningSearchForm" modelAttribute="tenderSearchData" method="POST" >
                                             <div class="col-sm-4">
                                                     <div class="form-group frgrp">
                                                     <input type="text" id="tenderStatus" name="tahdr.tahdrStatusCode" style="display: none;">
                                                     <input type="text" style="display: none;" id="tenderBidType" name="tahdr.bidTypeCode" value="">
                                                     <c:if test="${documentType.equalsIgnoreCase('Auction')}">
                                                     <input type="text" style="display: none;" id="tenderTypeCode" name="tahdr.tahdrTypeCode" value="PT">
                                                     </c:if>
                                                     <c:if test="${documentType.equalsIgnoreCase('Tender')}">
                                                     <input type="text" style="display: none;" id="tenderTypeCode" name="tahdr.tahdrTypeCode" value="WT">
                                                     </c:if>
										                <label for="dtp_input1" class="control-label">${documentType} Bid Opening On Date<span class="red">*</span></label>
										                <div class="input-group date form_datetime pastDate">				
										                    <input type="text" class="form-control" id="bidOpeningDate" name="">
															<span class="input-group-addon"><span class="glyphicon glyphicon-th"></span></span>
										                </div>
														<input type="hidden" id="dtp_input1" value="" /><br/>
										            </div>
						            
                                                </div>
                                                
                                                <div class="col-sm-4">
                                                 	<div class="styled-input">
                                                    	<input type="text" id="tahdrCode" name="tahdr.tahdrCode" >
                                                   		<label>${documentType} Code</label>
                                                  	</div>
                                                </div>
                                                <div class="col-sm-4">
                                                	<div class="styled-input">
															 <select id="openingType" name="" class="dropDown" onchange="openingTypeChange(this)"> 
													
															 </select>
													  <label>${documentType} Opening Type</label> 
												    </div> 
                                                </div>
                                                <div class="form-group">
                                                    <div class="col-sm-12">
	                                                	<button class="btn btn-default" onclick="return submitIt('tenderOpeningSearchForm','searchOpeningTenderList','searchOpeningTenderListResp');">Search</button>
	                                                	
                                                	</div>
                                                 </div>
                                                 </sf:form>
                                                 <div class="form-group" id="userResetBtnDivId">
                                                    <div class="col-sm-12">
                                                        <button class="btn btn-default" id="" onclick="resetSearchForm()">Reset</button>
                                                        </div>
                                                 </div>
                                                <sf:form id="venderTenderOpeningSearchForm" modelAttribute="tenderSearchData" method="POST" >
                                             <div class="col-sm-4">
                                                     <div class="form-group frgrp">
                                                     <input type="text" id="vtenderStatus" name="tahdr.tahdrStatusCode" style="display: none;">
                                                     <input type="text" style="display: none;" id="vtenderTypeCode" name="tahdr.tahdrTypeCode" value="WT">
                                                     <input type="text" style="display: none;" id="vtenderBidType" name="tahdr.bidTypeCode" value="">
										                <label for="dtp_input1" class="control-label">${documentType} Bid Opening On Date<span class="red">*</span></label>
										                <div class="input-group date form_datetime">				
										                    <input type="text" class="form-control" id="vbidOpeningDate" name="">
															<span class="input-group-addon"><span class="glyphicon glyphicon-th"></span></span>
										                </div>
														<input type="hidden" id="vdtp_input1" value="" /><br/>
										            </div>
						            
                                                </div>
                                                
                                                <div class="col-sm-4">
                                                 	<div class="styled-input">
                                                    	<input type="text" id="vtahdrCode" name="tahdr.tahdrCode" >
                                                   		<label>${documentType} Code</label>
                                                  	</div>
                                                </div>
                                                <div class="col-sm-4">
                                                	<div class="styled-input">
															 <select id="vopeningType" name="" class="dropDown" onchange="openingTypeChange(this)"> 
													
															 </select>
													  <label>${documentType} Opening Type</label> 
												    </div> 
                                                </div>
                                                <div class="form-group">
                                                    <div class="col-sm-12">
	                                                	<button class="btn btn-default" onclick="return submitIt('venderTenderOpeningSearchForm','searchOpeningTenderList','searchOpeningTenderListForVendorResp');">Search</button>
	                                                	<!-- <button class="btn btn-default" class="resetBtnId">Reset</button> -->
                                                    </div>
                                                </div>
                                                 </sf:form>
                                                 <div class="form-group" id="venderResetBtnDivId">
                                                    <div class="col-sm-12">
                                                        <button class="btn btn-default" id="" onclick="resetSearchForm()">Reset</button>
                                                        </div>
                                                 </div>
                                            </div>
                                             <div class="col-sm-12">
                                             	<sf:form id="tenderOpeningForm" modelAttribute="tenderOpeningData" method="POST" >
                                             	<input type="text" id="tenderStatus" name="tahdrStatusCode" style="display: none;">
                                             	
                                             	<input type="text" name="tahdrId" id="tahdrId" style="display: none;">
		                                            <div class="form-group">
		                                                <div class="col-sm-4">
		                                                        <label>${documentType} No:</label>
		                                                         <span class="detspan" id="tenderNo"></span>
		                                                </div>
		                                                <div class="col-sm-4">
		                                                        <label>Opening Date:</label>
		                                                         <span class="detspan" id="openingDate"></span>
		                                                </div>
		                                                <div class="col-sm-4">
		                                                        <label>Status:</label>
		                                                        <span class="detspan" id="status"></span>
		                                                </div>
		                                              </div>  
		                                              <div class="form-group">
		                                                <div class="col-sm-4">
		                                                        <label>Bid End Date:</label>
		                                                         <span  class="detspan" id="bidEndDate"> </span>
		                                                </div>
		                                                <div class="col-sm-4">
		                                                        <label>No of Bidder:</label>
		                                                        <span class="detspan" id="noBidder"></span>
		                                                </div>
		                                                <div class="col-sm-4">
		                                                        <label>No of Bid Submitted:</label>
		                                                        <span class="detspan" id="bidSubmitted"></span>
		                                                </div>
		                                              </div>                                                     
		                                              <div class="form-group">
		                                              
                                                    	<button class="btn btn-default ExplorerUserPrompt" id="joinTenderBtnId" 
                                                    	   style="display: none ;">Join ${documentType} Opening</button>
                                                    	   <button class="btn btn-default toggleTab" id="vitualJoinTenderBtnId" openTab="wizardTab" 
                                                    	   style="display: none ;">XXXXXX</button>
                                                    	   <button class="btn btn-default toggleTab indirectBidderFormSubmit" style="display: none ;" openTab="wizardTab" id="bidderViewBidBtnId" >View Joined User And Other Vendor</button>
                                                    	   <button class="btn btn-default toggleTab" style="display: none ;" openTab="viewBidTab" id="viewBidBtnId" 
                                                    	   onclick="return submitWithParam('getTAHDRMaterialListByTahdrId','tahdrId','loadTahdrMaterialListForView')">View Bid</button>
                                             		</div> 
                                             		</sf:form>
                                             		<div id="viewBidStatusDivId" style="display: none ;">
	                                             		<div class="col-sm-6">
	                                             		   <label>MESSAGE :</label>
	                                                 	    <span class="detspan" id="viewBidStatus"></span>
	                                                  	</div>
	                                                  	<div class="col-sm-6" >
	                                                 	     <label>Bid Open Date:</label>
			                                                 <span  class="detspan" id="bidOppeningDate"> </span>
	                                                  	</div>
                                                  	</div>
                                             </div>
                                            <div class="clearfix"></div>
                                        </div>
                                        <!--fields of field group 1  -->
                                        
                                        
                                        <!--fields of field group 3  -->
                                        <div>
                                        <div class="detailscont">
  							 				<div class="col-md-12">
                                				<label class="col-xs-6"> <h4></h4></label>
                            					<label class="col-xs-6 "></label>
                            				</div>	
                            				<div class="col-md-12">
                           						<label class="col-xs-6"></label>
                            					<label class="col-xs-6 "></label>
                            				</div>
    									</div>
                                            <div class="form-group">
                                                <div class="col-sm-12">
                                                    <h4 class="col-sm-6"><b>Explorer User Prompt</b>
                                                     <label class="bluetext">Auto Refresh in</label>
			                                            <label id="autoRefresh" class="greentext">0s</label>
                                                    </h4>
                                                    
                                                    <div class="col-sm-6 text-right">
                                                    
                                                    <button class="btn btn-default" id="tenderLogOutBtnId">
                                                    <i class="fa fa-power-off" style="font-size:16px;" aria-hidden="true"></i> ${documentType} Logout</button></div>
                                                    <hr>
                                                </div>
                                            </div>
                                            
                                            <div class="col-sm-6">
                                            
                                      			<!-- <div class="form-group">
                                                    	<button class="btn btn-default" id="refreshBtnId">Refresh</button>
                                            	</div> -->
                                            <div class="col-sm-12">
                                            	<div class="panel panel-primary">
										      <div class="panel-heading">Joined in MAHADISCOMUSERS<button class="btn btn-default orange pull-right" onclick="return submitWithParam('getLoggedInAuditorList', 'openAllBidForm #tahdrId', 'loggedInUserResp');"><span class="glyphicon glyphicon-refresh"></span></button></div>
										      <div class="panel-body">
										      	<div class="form-group" id="loggedInCommitteeMemberDivId">
										      		<h4></h4>
										         </div>
										      </div>
										    </div>
                                            </div>
                                            <div class="col-sm-12">
                                            	<div class="panel panel-primary">
										      <div class="panel-heading">Joined in Vendor<button class="btn btn-default orange pull-right" onclick="return submitWithParam('getLoggedInBidderList', 'openAllBidForm #tahdrId', 'loggedInBidderrResp');"><span class="glyphicon glyphicon-refresh"></span></button></div>
										      <div class="panel-body">
										      	<div class="form-group" id="loggedInVenderDivId">
										      		<h4></h4>
										         </div>									         
										      </div>
										    </div>
                                            </div>
                                            <sf:form id="openAllBidForm" modelAttribute="openAllBidData" method="POST">
                                            <div class="form-group" style="margin-top:-20px;">
                                            	 <input type="text" id="tahdrCode" name="tahdrCode" style="display: none;">
                                            	 <input type="text" id="tenderBidType" name="bidTypeCode" style="display: none;">
                                             	<input type="text" name="tahdrId" id="tahdrId" style="display: none;">
                                                <div class="col-sm-12">                                               
                                                    <label> Status:
                                                    </label>
                                                     <label  class="radio-inline" style="margin-top:0px;">
                                                        <input type="radio" name="tahdrStatusCode" onclick="$('#openTenderBtnId').html('Cancel Tender');" value="VO">Cancel ${documentType}
                                                     </label>   
                                                    <label  class="radio-inline">
                                                        <input type="radio" id="tenderStatus" onclick="$('#openTenderBtnId').html('Open All Bid');" name="tahdrStatusCode" value="" checked="checked">Open Successfully 
                                                    </label>
                                                </div>
                                                <div class="col-sm-8">
                                                    <div class="styled-input">
                                                        <input type="text" id="" name="remarks" class="requiredField">
                                                        <label>Comments<span class="red">*</span></label>
                                                        <span></span>
                                                    </div>
                                                </div>
                                                <div class="col-sm-4">
                                                    	<button class="btn btn-default" style="margin-top: 20px;" id="openTenderBtnId" onclick="return submitIt('openAllBidForm','openTenderByOpeningType','openingTenderResp');">Open All Bid</button></div>
                                                    	 <button class="btn btn-default toggleTab" style="display: none ;" openTab="viewBidTab" id="viewBidBtnId" 
                                                    	   onclick="return submitWithParam('getTAHDRMaterialListByTahdrId','tahdrId','loadTahdrMaterialListForView')">View Bid</button>
                                            </div>
                                            </sf:form>
                                            <button class="btn btn-default toggleTab" style="display: none ;" openTab="viewBidTab" id="viewBidBtnId_1" 
                                                    	   onclick="return submitWithParam('getTAHDRMaterialListByTahdrId','tahdrId','loadTahdrMaterialListForView')">View Bid</button>
                                            </div>
                                                 
                                            <div class="col-sm-6">
                                              
                                           <div class="panel panel-primary">
										      <div class="panel-heading"><label class="col-sm-6">Bidders</label><label class="col-sm-6 text-right">Status</label><div class="clearfix"></div></div>
										      <div class="panel-body">
										      	<div class="form-group" id="bidderDivId">
										         </div>
										      </div>
										    </div>
										    <div class="form-group">
                                                    	<!-- <button class="btn btn-default tab-li-next" id="viewAllBidBtnId" style="display: none ;"
                                                    	onclick="return submitWithParam('getBidderListByViewOpeningType','tahdrId','loadBidderListForView')" >View Bid</button> -->
                                                        <!-- <button class="btn btn-default toggleTab" style="display: none ;" openTab="viewBidTab" id="viewAllBidBtnId" 
                                                    	   onclick="return submitWithParam('getBidderListByViewOpeningType','tahdrId','loadBidderListForView')">View Bid</button>                             -->   
                                            </div>
                                            </div>
                                                                                   
                                        </div>
                                        <!--fields of field group 3  -->
                                        
                                        <div>
                                        <div class="detailscont">
  							 				<div class="col-md-12">
                                				<label class="col-xs-6"> <h4></h4></label>
                            					<label class="col-xs-6 "></label>
                            				</div>	
                            				<div class="col-md-12">
                           						<label class="col-xs-6"></label>
                            					<label class="col-xs-6 "></label>
                            				</div>
    									</div>
    									<div id="tabstrip3">
                                        <ul>
                                            <!-- tabs -->
                                            <li class="k-state-active tenderMaterialTab" onclick="return submitWithParam('getTAHDRMaterialListByTahdrId','tahdrId','loadTahdrMaterialListForView')">${documentType} Material List</li>
                                            <li class="" id="bidTabId" onclick="return submitWithTwoParam('getBidderListByViewOpeningType','tahdrId','tahdrMaterialId' ,'loadBidderListForView');" >Bidders</li>
                                            <li class="" id="bidsDocTabId" style="display: none ;" onclick="return submitWithTwoParam('getBidderListByViewOpeningType','tahdrId','tahdrMaterialId' ,'loadBidderListForView');" >Bids</li>
                                            <li class="" id="dbopConditionalId" onclick="return submitWithParam('getBidderListByViewCommercialBid','tahdrId','loadBidderListForComercialBidView');" >Commercial Bids</li>
                                        </ul>
                                        <!--sub of field group 1  -->
                                        	<div>
                                        	<div class="form-group">
                                                <div class="col-sm-12">
                                                    <h4><b>Bidders</b></h4>
                                                    <hr>
                                                </div>
                                            </div>
                                            <form id="itemDetailForm">
                                            <div class="form-group">
                                            <input style="display: none;" id="tahdrMaterialId">
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
                                                        <label>${documentType} Material Type:</label>
                                                         <span class="detspan" id="tenderMatType" ></span>
                                                </div>
                                              </div> 
                                              </form>
                                            </div>
                                         <!--sub of field group 1  -->
                                         
                                         <!--sub of field group 2  -->
                                        	<div >
                                        	<div class="form-group">
                                                <div class="col-sm-12">
                                                    <h4><b>Bidder Info</b></h4>
                                                    <hr>
                                                </div>
                                            </div>
                                            <form id="viewBidForm">
                                                  <div class="form-group">
		                                                <div class="col-sm-4">
		                                                        <label>Bidder Name:</label>
		                                                         <span class="detspan" id="bidderName"></span>
		                                                         
		                                                </div>
		                                                <div class="col-sm-4">
		                                                        <label>${documentType} Name:</label>
		                                                         <span class="detspan" id="tenderName"></span>
		                                                         
		                                                </div>
		                                                <div class="col-sm-4">
		                                                        <label>${documentType} No:</label>
		                                                         <span class="detspan" id="tenderNo"></span>
		                                                         
		                                                </div>
		                                           </div>
		                                           <div class="form-group">
		                                                <!-- <div class="col-sm-4">
		                                                        <label>Items bidding for:</label>
		                                                         <span class="detspan" id="openingDate"></span>
		                                                </div> -->
		                                                
		                                                <div class="col-sm-6">
		                                                
		                                                	<div id="tcDivId" style="display: none ;">
		                                                    	<label  id="tclabelId">Download Technical Bid:</label>
			                                                    <div id="technicalDivId">
			                                                        <a data-url="<%=request.getContextPath()%>/download" id="downloadTechDoc"></a>
			                                                    </div> <br>
		                                                      	<div id="commercialDivId">
			                                                        <label id="tcclabelId">Download Commercial Details:</label>
			                                                        <a data-url="<%=request.getContextPath()%>/download" id="downloadCommDoc"></a>
		                                                     	</div>
		                                                  	</div>
		                                                  	<div id="pbDivId" style="display: none ;">
		                                                    	<label  id="pblabelId">Download Price Bid:</label>
			                                                    <div id="priceBidlDivId">
			                                                        <a data-url="<%=request.getContextPath()%>/download" id="downloadPBDoc"></a>
			                                                    </div>
		                                                  	</div>  
		                                                  	<div id="c1DivId" style="display: none ;">  
		                                                        <label id="c1labelId">Download Annexure C1 Bid:</label>
		                                                       	<div id="c1BidTypeDivId">
		                                                          	<a data-url="<%=request.getContextPath()%>/download" id="downloadC1Doc"></a>
		                                                       	</div>
		                                                  	</div>
		                                                  	<div id="deviationDivId" style="display: none ;">  
		                                                        <label id="techDvtnlabelId">Download Technical Deviation Bid:</label>
		                                                       	<div id="techDvtnBidTypeDivId">
		                                                          	<a data-url="<%=request.getContextPath()%>/download" id="downloadTechDvtnDoc"></a>
		                                                       	</div>
		                                                       	
		                                                       	<label id="commDvtnlabelId">Download Commercial Deviation Bid:</label>
		                                                       	<div id="commDvtnBidTypeDivId">
		                                                          	<a data-url="<%=request.getContextPath()%>/download" id="downloadOtherDoc"></a>
		                                                       	</div>
		                                                  	</div>
		                                                  	 <div id="otherDivId" style="display: none ;">  
		                                                        <label id="otherlabelId">Download Bid:</label>
		                                                       	<div id="otherBidTypeDivId">
		                                                          	<a data-url="<%=request.getContextPath()%>/download" id="downloadOtherDoc"></a>
		                                                       	</div>
		                                                  	</div> 
		                                                  <%-- 	<div id="otherDivId" style="display: none ;">  
		                                                        <label id="otherlabelId">Download Bid:</label>
		                                                       	<div id="otherBidTypeDivId">
		                                                          	<a data-url="<%=request.getContextPath()%>/download" id="downloadOtherDoc"></a>
		                                                       	</div>
		                                                  	</div>   --%>
		                                                </div>
		                                           </div>
		                                                
		                                        </form> 
                                            </div>
                                         <!--sub of field group 2  -->
                                         <div>
                                         	<div class="form-group">
                                                <div class="col-sm-12">
                                                    <h4><b>Bids</b></h4>
                                                    <hr>
                                                </div>
                                            </div>
                                         </div>
                                         <div id="dbopConditionalId">
                                        	<div class="form-group">
                                                <div class="col-sm-12">
                                                    <h4><b>View Commercial Bids</b></h4>
                                                    <hr>
                                                </div>
                                            </div>
                                            <form id="viewCommercialBidForm">
                                                  <div class="form-group">
		                                                <div class="col-sm-4">
		                                                        <label>Bidder Name:</label>
		                                                         <span class="detspan" id="bidderName"></span>
		                                                         
		                                                </div>
		                                                <div class="col-sm-4">
		                                                        <label>${documentType} Name:</label>
		                                                         <span class="detspan" id="tenderName"></span>
		                                                         
		                                                </div>
		                                                <div class="col-sm-4">
		                                                        <label>${documentType} No:</label>
		                                                         <span class="detspan" id="tenderNo"></span>
		                                                         
		                                                </div>
		                                           </div>
		                                           <div class="form-group">
		                                               
		                                                
		                                                <div class="col-sm-6">
		                                             
		                                                  <div >  
		                                                        <label id="labelId">Download Commercial Bid:</label>
		                                                       <div >
		                                                          <a data-url="<%=request.getContextPath()%>/download" id="downloadDoc"></a>
		                                                       </div>
		                                                  </div>  
		                                                </div>
		                                           </div>
		                                                
		                                        </form> 
                                            </div>
                                      									
		                                        
                                                
                                            </div>
                                            
                                            <div class="col-sm-6">
                                            
                                      
                                            
                                            </div>
                                                                                   
                                        </div>
                                    </div>

                                </div>
                                <!-- Master tab end-->
                                            <%-- <div class="form-group">
                                                <div class="col-sm-12">
                                                    <h4><b>View Bids</b></h4>
                                                    <hr>
                                                </div>
                                                <form id="viewBidForm">
                                                  <div class="form-group">
		                                                <div class="col-sm-4">
		                                                        <label>Bidder Name:</label>
		                                                         <span class="detspan" id="bidderName"></span>
		                                                         
		                                                </div>
		                                                <div class="col-sm-4">
		                                                        <label>Tender Name:</label>
		                                                         <span class="detspan" id="tenderName"></span>
		                                                         
		                                                </div>
		                                                <div class="col-sm-4">
		                                                        <label>Tender No:</label>
		                                                         <span class="detspan" id="tenderNo"></span>
		                                                         
		                                                </div>
		                                           </div>
		                                           <div class="form-group">
		                                                <!-- <div class="col-sm-4">
		                                                        <label>Items bidding for:</label>
		                                                         <span class="detspan" id="openingDate"></span>
		                                                </div> -->
		                                                
		                                                <div class="col-sm-6">
		                                                <div id="tcDivId" style="display: none ;">
		                                                     
		                                                        <label  id="tclabelId">Download Technical Bid:</label>
		                                                     <div id="technicalDivId">
		                                                        <a data-url="<%=request.getContextPath()%>/download" id="downloadTechDoc"></a>
		                                                     </div> <br>
		                                                        <label id="tcclabelId">Download Commercial Details:</label>
		                                                        <a data-url="<%=request.getContextPath()%>/download" id="downloadCommDoc"></a>
		                                                  </div>  
		                                                  <div id="otherDivId" style="display: none ;">  
		                                                        <label id="labelId">Download Bid:</label>
		                                                       <div id="otherBidTypeDivId">
		                                                          <a data-url="<%=request.getContextPath()%>/download" id="downloadTechDoc"></a>
		                                                       </div>
		                                                  </div>  
		                                                </div>
		                                           </div>
		                                                
		                                        </form> 
		                                      </div> 
		                                        
                                                
                                            </div>
                                            
                                            <div class="col-sm-6">
                                            
                                      
                                            
                                            </div>
                                                                                   
                                        </div>
                                    </div>

                                </div>
                                <!-- Master tab end--> --%>

                            </div>
                        </div>
                        <!-- right-side end-->
                    </div>
                </div>
            </div>
            <div class="clearfix"></div>
<!-- Modal -->
  <div class="modal fade ExplorerUserPromptPopup" id="" role="dialog">
    <div class="modal-dialog">
    
      <!-- Modal content-->
      <div class="modal-content">
       <sf:form id="sessionKeyForm" modelAttribute="sessionKeyData" method="POST">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title">Join Tender Opening</h4>
        </div>
        <div class="modal-body">
        	<div class="form-group">                                                
                                                <div class="col-sm-12">
                                                    <div class="styled-input">
                                                        <input type="text" style="display: none ;" id="tahdrId" name="tahdrId" required />
                                                        <input type="text" style="display: none ;" id="openingType" name="openingType" required />
                                                        <input type="password" id="inputSessionKey" name="sessionKey" class="requiredField" />
                                                        <label>Please Enter unique Session key received<span class="red">*</span></label>
                                                        <span></span>
                                                    </div>
                                                </div>                                                
                                            </div><br>
                                            <div class="claerfix"></div>								
	                                                
        </div>
        <div class="modal-footer">
	        <button type="button" class="btn btn-default" onclick="return submitIt('sessionKeyForm','checkSessionKey','loadMemberDiv');" data-dismiss="modal">Validate Key</button>
	        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        </div>
        </sf:form>
      </div>
      
    </div>
  </div>
<script src="<%=request.getContextPath()%>/resources/${appMode}/tilescommon/js/timer.jquery.js?appVer=${appVer}"></script>
<script src="<%=request.getContextPath()%>/resources/${appMode}/transaction/js/tenderOpening.js?appVer=${appVer}"></script>
<script src="<%=request.getContextPath()%>/resources/${appMode}/transaction/js/tenderOpeningTimer.js?appVer=${appVer}"></script>
<script src="<%=request.getContextPath()%>/resources/${appMode}/commons/js/formFields.js?appVer=${appVer}"></script>
<script src="<%=request.getContextPath()%>/resources/${appMode}/tilescommon/js/bootstrap-datetimepicker.js?appVer=${appVer}"></script> 
<script src="<%=request.getContextPath()%>/resources/${appMode}/commons/js/utility.js?appVer=${appVer}"></script>
<script>
$(document).ready(function(){
	 var lengthMenu;

	    if ($(window).width() < 480) {
	        $('.mobileNav').show();
	        $.fn.DataTable.ext.pager.numbers_length = 4;       
	        lengthMenu = [ 1, 5, 7, 10, ],
	        [ 1, 5, 7, 10, ]
	    } else {        
	        lengthMenu = [ 5, 10, ],
	        [ 5, 10, ]
	    }
	    
		$('.CommercialDocumentTable').DataTable({
			"lengthMenu":lengthMenu
		});
		$('.ExplorerUserPrompt').click(function(event){
			 event.preventDefault();
			$('.ExplorerUserPromptPopup').modal('show');
			return false;
		})
});
</script>

<script type="text/javascript">
var date = new Date();
 $('.form_datetime').datetimepicker({
    	weekStart: 1,
		autoclose: 1,
		startView: 2,
		forceParse: 0,
		format: 'dd-mm-yyyy HH:ii',
        showMeridian: 1,
		orientation: 'auto',
		pickerPosition: "top-left",
		endDate : date,
        widgetPositioning:{
            horizontal: 'auto',
            vertical: 'bottom'
        }
    }); 
</script>	