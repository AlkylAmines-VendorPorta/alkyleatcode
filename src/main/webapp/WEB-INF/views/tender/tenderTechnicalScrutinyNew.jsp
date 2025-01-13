<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@taglib uri = "http://www.springframework.org/tags/form" prefix = "sf"%>
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/${appMode}/tilescommon/css/fioristyle.css?appVer=${appVer}"/>
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/${appMode}/tilescommon/css//borderTab.css?appVer=${appVer}">
<style>
.Firsttab>.k-tabstrip-items {
    background-color: #eff4f9 !important;
}
.firstli{    height: 45px;
    margin-top: 0px !important;
    line-height: 33px;
    padding: 0px 10px;}
   .k-tabstrip>.k-tabstrip-items>.k-state-active>.k-link {
    color: #ffffff;
}
.k-tabstrip .k-tabstrip-items {
    background: white;
}
.k-widget.k-tabstrip{    border-top: 1px solid #eff4f9;}
.k-panelbar>li.k-state-default>.k-link, .k-tabstrip-items .k-item .k-link {
    color: #fff;
}
.Firsttab>.k-tabstrip-items .k-state-active {
    background-color: #4caf50 !important;
}
.k-tabstrip>.k-tabstrip-items .k-state-active {
    background-color: #4caf50 !important;
}
.Firsttab {
    background-color: #eff4f9 !important;
    border-top: transparent;
}
.k-tabstrip>.k-tabstrip-items>.k-item {
    text-transform: capitalize;
    border-bottom: 0px !important;
    background: #1089ef;
    border: 2px solid #2066a1 !important;
    margin-top: 10px;
    border-top: 1px solid #2066a1 !important;
}
.k-widget.k-tabstrip{border-top:    border-top: 1px solid #eff4f9;}
.Firsttab .k-content.k-state-active {
    border-color: transparent;
    padding-top: 0px;
    border-top: none !important;
    padding: 0px;
}
</style>
            <script>
                $(document).ready(function() {
                    $("#tabstrip").kendoTabStrip();
                    $("#tabstrip1").kendoTabStrip();
                    $("#tabstrip2").kendoTabStrip();                   
                });
               
            </script>
        <div class="full-container">
            <!-- left-side start-->
            <div class="clearfix"></div>
            <div id="mobile_first_container" class="left-side col-md-3 no-marg">
            <div class="detailsheader">
        		<div class="col-sm-3 text-center brdrgt">
        		<label><span class="activeTab">Tender Scrutiny Report</span> (<span class="reportCount">0</span>)</label></div>
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
			         <input type="radio" name="tenderTypeCodeToggle" value="PT">
			         <span class="glyphicon glyphicon-ok"></span> Procurement 
			         </label>
			         <label class="btn btn-primary active">
			         <input type="radio" name="tenderTypeCodeToggle" checked value="WT">
			         <span class="glyphicon glyphicon-ok"></span> Works
			         </label>
			      </div>
                <ul id="leftPaneData" class="nav nav-tabs tabs-left leftPaneData">
            
                </ul>                
                <div class="clearfix"></div>
            </div>
            <!-- left-side end-->

            <!-- right-side start-->
            <div id="mobile_second_container" class="right-side col-md-9 no-marg">
            <div class="detailsheader toptabbrd">  
        		<div class="col-sm-9 text-center"><label>Tender Scrutiny Details</label></div>
        	 </div>
                <div class="clearfix"></div>
                <div class="tab-content">
                    <!-- Master tab start-->

                    <div class="tab-pane active in" id="">
                        <div class="card">
                             <div class="posrelative" id="example">
                              <div class="demo-section k-content">
                                    <div id="tabstrip1" class="Firsttab">
                                        <ul>
                                            <!-- tabs -->
                                            <li id="tenderDetailTab" class="k-state-active firstli">Details</li>
                                            <li>Bidder</li>
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
    									<div class="form-group">
                                                <div class="col-sm-12">
                                                    <h4><b>Tender Details</b></h4>
                                                    <hr>
                                                </div>
                                            </div>  
    									<form id="tahdrDetailForm" class="readonly">
    									<div class="form-group">
    									 <input type="text" style="display: none;" id="tahdrDetailId" name="" >
                                                <div class="col-sm-4">
                                                 
                                                        <label>Tender No:</label>
                                                        <span class="detspan" id="tenderNo" ></span>
                                                </div>
                                                <div class="col-sm-4">
                                                 <label>Tender Version:</label>
                                                        <span class="detspan" id="tenderVersion" ></span>
                                                   
                                                </div>
                                                <div class="col-sm-4">
                                                 <label>Description:</label>
                                                        <span class="detspan" id="description" ></span>
                                                  
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <div class="col-sm-4">
                                                 <label>EMD Fees:</label>
                                                        <span class="detspan" id="emdFee" ></span>
                                                   
                                                </div>                                                
                                            </div>
                                            </form>
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
    									<div>
                                    <div id="tabstrip3">
                                        <ul class="ulId">
                                            <!-- tabs -->
                                           <li class="k-state-active technicalScrutinyTab firstli ulId" onclick="return submitWithParam('getBidderListByTahdrDetailId','tahdrDetailId' ,'loadBidderListForScrutiny');">Bidder Details</li>
                                            <li class="scrutinyTabsId" onclick="return submitWithParam('getItemListBybidderId','bidderId' ,'loadItemListForScrutiny');">Preliminary Technical Scrutiny</li>
                                        
                                            
                                            <li  class="scrutinyTabsId" onclick="return submitWithParam('saveItemScrutinyForCommercial','bidderId' ,'loadScrutinyPointListForScrutiny');">Preliminary Commercial Scrutiny</li>
                                            <li class="ulId" onclick="return submitWithParam('checkDeviationSchedule', 'tahdrDetailId', 'setDeviationSchedule');">Schedule Deviation Bid</li>
                                        </ul>

                                       
                                        <div>
                                            <div class="form-group">
                                                <div class="col-sm-12">
                                                    <h4><b>Bidder Details</b></h4>
                                                    <hr>
                                                </div>
                                            </div>  
    									<form id="bidderDetailForm" class="readonly">
    									<div class="form-group">
    									<div class="col-sm-6">
    										<label>Company Name:</label>
                                                        <span class="detspan" id="name" ></span>
                                                         <input type="text" style="display: none;" id="bidderId" name="" >
                                                    
                                                </div>
                                                
                                                <div class="col-sm-6">
                                               	 <label>PAN No:</label>
                                                        <span class="detspan" id="panno" ></span>
                                                  
                                                </div>
                                                </div >
                                                <div class="form-group">
                                                <div class="col-sm-6">
                                                <label>GSTN No:</label>
                                                        <span class="detspan" id="gstNo" ></span>
                                                    
                                                </div>
                                                <div class="col-sm-6">
                                                <label>Company Registration No:</label>
                                                        <span class="detspan" id="crnNumber" ></span>
                                                   
                                                </div>
                                             </div>
                                                
                                           
                                            </form>
                                                                               
                                        </div>
                                         
                                        <!--fields of field group 2  -->
                                        
                                        <!--fields of field group 3  -->
                                        <div>
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
                                    <div id="tabstrip" style="margin-top:15px;">
                                        <ul class="ulId">
                                            <!-- tabs -->
                                           <li class="k-state-active technicalScrutinyTab firstli" onclick="return submitWithParam('getItemListBybidderId','bidderId' ,'loadItemListForScrutiny');">Technical Scrutiny Items</li>
                                            
                                            <li class="technicalScrutinyTab" onclick="return submitWithParam('getBidderGtpByItemScrutinyId','itemScrutinyId' ,'loadItemScrutinyLineDetails');">PTS GTP Details</li>
                                            
                                            <li class="technicalScrutinyTab" onclick="return submitWithParam('getTechnicalDoc','itemBidId' ,'loadDocDetails');">PTS Document Details</li>
                                            <li class="technicalScrutinyTab" onclick="return submitWithParam('getFinalResp','technicalFinalScrutinyForm #itemScrutinyId' ,'setFinalResp');">PTS Verify Details</li>
                                        </ul>

                                       
                                        <div>
                                            <div class="form-group">
                                                <div class="col-sm-12">
                                                    <h4><b>Item Details</b></h4>
                                                    <hr>
                                                </div>
                                            </div>  
                                            <sf:form id="itemDetailForm" modelAttribute="itemScrutiny" method="POST">                                          
                                             <div class="form-group">
                                                <div class="col-sm-4">
                                                    <div class="styled-input">
                                                    <input type="text" style="display: none;" id="itemBidId" >
                                                    <input type="text" style="display: none;" id="tahdrMaterialId" >
                                                    <input type="text" style="display: none;" id="ItemScrutinyId" >
                                                        <input type="text" id="name" class="readonly" >
                                                        <label>Item Name</label>
                                                        <span></span>
                                                    </div>
                                                </div>
                                                <div class="col-sm-4">
                                                    <div class="styled-input">
                                                        <input type="text" id="uomName" class="readonly" >
                                                        <label>UOM</label>
                                                        <span></span>
                                                    </div>
                                                </div>
                                                <div class="col-sm-4">
                                                    <div class="styled-input">
                                                        <input type="text" id="description" class="readonly">
                                                        <label>Description</label>
                                                        <span></span>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <div class="col-sm-4">
                                                    <div class="styled-input">
                                                        <input type="text" id="hsnCode" class="readonly">
                                                        <label>hsnCode</label>
                                                        <span></span>
                                                    </div>
                                                </div>                                                
                                            </div>
                                              
                                            <div class="form-group">
                                                <div class="col-sm-12">
                                                   
                                                    <button class="btn btn-default" id="startScrutinyBtnId" data-itembidid="" onclick="startScrutiny(this)">Start Scrutiny</button>
                                                </div>
                                              </div> 
                                              </sf:form>
                                                                               
                                        </div>
                                         
                                        <!--fields of field group 2  -->
                                        
                                        <!--fields of field group 3  -->
                                        <div>
                                            <div class="form-group">
                                                <div class="col-sm-12">
                                                    <h4><b>Preliminary Technical scrutiny GTP Details</b></h4>
                                                    <hr>
                                                </div>
                                            </div>                                            
                                             <sf:form id="PTSGtpForm" modelAttribute="itemScrutinyLine" method="POST">                                          
                                             <div class="form-group">
                                             <div style="display: none;">
                                             <input type="text" id="itemScrutinyLineId" name="itemScrutinyLineId" value="">
                                             <input type="text" id="itemScrutinyId" name="itemScrutiny.itemScrutinyId" value="">
                                              <input type="text" id="bidderId" name="itemScrutiny.bidder.bidderId" value="">
                                              <!-- <input type="text" id="" name="deviationType" value="Technical"> -->
                                             <input type="text" id="tahdrMaterialId" name="tahdrMaterial.tahdrMaterialId" value="">
                                             <input type="text" id="bidderGtpId" name="bidderGtp.bidderGtpId" value="">
                                             <input type="text" id="isActive" name="isActive" value="Y">
                                             </div>
                                             	<div class="col-sm-4">
                                                    <div class="col-sm-12">
                                                		<label>GTP</label>
                                                        <div id="gtpName"></div>
                                                	</div>
                                                </div>
                                                <div class="col-sm-4">
                                                    <div class="styled-input" class="readonly">
                                                        <input type="text" id="gtptype" name="" >
                                                        <label>GTP Type</label>
                                                        <span></span>
                                                    </div>
                                                </div>
                                                <div class="col-sm-4">
                                                	<div class="col-sm-12">
                                                		<label>Value</label>
                                                        <div id="bidderGtpResp"></div>
                                                	</div>
                                                        
                                                </div>
                                                
                                            </div>
                                            <div class="form-group">
                                                <div class="col-sm-4">
                                                    <div class="styled-input">
                                                                <select id="deviationTypeCode" name="isDeviation" class="dropDown" onchange="deviationStatusChange(this)">
                                                                <option value="">Select</option>
                                                                <option value="Y">YES</option>
                                                                 <option value="N">NO</option>
                                                                </select>
                                                                <label>DeviationStatus<span class="red">*</span></label>
                                                                <span></span>
                                                            </div>
                                                </div>                                                
                                            
                                            
                                                <div class="col-sm-4">
                                                     <div class="styled-input">
                                                                <select id="deviationType" name="deviationType" class="readonly dropDown" disabled="disabled"></select>
                                                                 <label>DeviationTypeCode<span class="red">*</span></label>
                                                                 
                                                                <span></span>
                                                            </div>
                                                </div>                                                
                                            
                                            
                                                <div class="col-sm-4">
                                                    <div class="styled-input">
                                                        <input type="text" id="comment" name="deviationComment" >
                                                        <label id="labelId">Comment</label>
                                                        <span></span>
                                                    </div>
                                               </div>                                               
                                            </div>
                                            
                                               <div class="form-group">
                                                <div class="col-sm-12 text-center">
                                                    <button class="btn btn-default save" onclick="return submitIt('PTSGtpForm','saveItemScrutinyLine','itemScrutinyLineRespForTechnical');">Save</button>
                                                    <button class="btn btn-default cancel">Cancel</button>
                                                </div>
                                              </div> 
                                              </sf:form>                                            
                                        </div>
                                        <!--fields of field group 3  -->
                                        <!--fields of field group 4  -->
                                        <div>
                                            <div class="form-group">
                                                <div class="col-sm-12">
                                                    <h4><b>Preliminary Technical scrutiny Document Details</b></h4>
                                                    <hr>
                                                </div>
                                            </div>
                                            <sf:form id="PTSDocForm" modelAttribute="itemScrutinyLine" method="POST">                                          
                                             <div class="form-group">
                                             <div style="display: none;">
                                             <input type="text" class="itemScrutinyLineId" id="itemScrutinyLineId" name="itemScrutinyLineId" value="">
                                             <input type="text" class="itemScrutinyId" id="itemScrutinyId" name="itemScrutiny.itemScrutinyId" value="">
                                             <input type="text" id="bidderId" name="itemScrutiny.bidder.bidderId" value="">
                                             <!-- <input type="text" id="" name="deviationType" value="Commercial"> -->
                                             <input type="text" class="tahdrMaterialId" id="tahdrMaterialId" name="tahdrMaterial.tahdrMaterialId" value="">
                                             <input type="text" id="bidderDocSecId" name="bidderSectionDoc.bidderSectionDocId" value="">
                                             <input type="text" id="isActive" name="isActive" value="Y">
                                             </div>
                                                <div class="col-sm-4">
                                                   <label>Document Name :</label>
                                                         <span class="detspan" id=documentCopy></span>
														
                                                </div>
                                                <div class="col-sm-4">
                                               <div class="styled-input">
			                                             	 <div id="purchase">
			                                             	 	<a id="PTSdownloadLinkId" data-url="<%=request.getContextPath()%>/download"><b><span class="glyphicon glyphicon-plus-sign paddright"></span><span id="">Download Document</span></b></a>
			                                             	 </div>
           	                                            </div>
           	                                            </div>
                                              </div>
                                            <div class="form-group">
                                                <div class="col-sm-4">
                                                    <div class="styled-input">
                                                                <select id="deviationTypeCode" name="isDeviation" class="dropDown" onchange="deviationStatusChange(this)">
                                                                <option value="">Select</option>
                                                                <option value="Y">YES</option>
                                                                 <option value="N">NO</option>
                                                                </select>
                                                                <label>DeviationStatus<span class="red">*</span></label>
                                                                <span></span>
                                                            </div>
                                                </div>                                                
                                            
                                            
                                                <div class="col-sm-4">
                                                     <div class="styled-input">
                                                                <select id="deviationType" name="deviationType" class="dropDown" disabled="disabled"></select>
                                                                 <label>DeviationTypeCode<span class="red">*</span></label>
                                                                 
                                                                <span></span>
                                                            </div>
                                                </div>                                                
                                            
                                            
                                                <div class="col-sm-4">
                                                    <div class="styled-input">
                                                        <input type="text" id="comment" name="deviationComment" >
                                                        <label id="labelId">Comment</label>
                                                        <span></span>
                                                    </div>
                                               </div>                                               
                                            </div>
                                            
                                               <div class="form-group">
                                                <div class="col-sm-12 text-center">
                                                    <button class="btn btn-default save" onclick="return submitIt('PTSDocForm','saveItemScrutinyLine','itemScrutinyLineRespForTechnical');">Save</button>
                                                    <button class="btn btn-default cancel">Cancel</button>
                                                </div>
                                              </div> 
                                              </sf:form>                                                                                                    
                                        </div>
                                        <!--fields of field group 4  -->
                                        <!--fields of field group 5  -->
                                        <div>
                                       
                                            <div class="form-group">
                                                <div class="col-sm-12">
                                                    <h4><b>Verify Technical  scrutiny</b></h4>
                                                    <hr>
                                                </div>
                                            </div>
                                            
                                            <div class="form-group">
                                                    <div class="col-sm-12">
                                                    	<button class="btn btn-default">Download Technical Scrutiny PDF</button>
                                                    	<button class="btn btn-default">Goto Main Page</button>
                                                    </div> 
                                                    <hr>
                                                    <sf:form id="technicalFinalScrutinyForm" modelAttribute="itemScrutiny" method="POST">
                                                     <div class="form-group">
                                                     
                                            			 <input type="text"  style="display: none;" id="itemScrutinyId" name="itemScrutinyId" value="">
                                            			 <input type="text"  style="display: none;" id="bidderId" name="bidder.bidderId" value="">
                                            			 <input type="text"  style="display: none;" id="itemBidId" name="itemBid.itemBidId" value="">
                                                     <div class="col-sm-6">
                                                     
			                                                    <label class="radio-inline" style="margin-top:20px;">
			                                                        <input type="radio" id="statusApproved" name="preliminaryScrutinyStatus" value="Approved">Approved
			                                                         </label>
			                                                       
			                                                     <label class="radio-inline"  style="margin-top:20px;">  
			                                                       <input type="radio" id="statusDeviation" name="preliminaryScrutinyStatus" value="Deviation">Deviation
			                                                    </label>
			                                                    
			                                                    <label class="radio-inline"  style="margin-top:20px;">  
			                                                       <input type="radio" id="statusRejected" name="preliminaryScrutinyStatus" value="Rejected">Reject
			                                                    </label>
			                                                    </div>
                                            		</div>
                                                    <div class="form-group">
                                                     <div class="col-sm-4">
                                                    <div class="styled-input">
                                                        <input type="text" id="finalComment" name="preliminaryScrutinyComment">
                                                        <label>Comments<span class="red">*</span></label>
                                                        <span></span>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                    <div class="col-sm-12">
                                                    	<button class="btn btn-default" onclick="return submitIt('technicalFinalScrutinyForm','savePreliminaryTechnicalScrutiny','finalScrutinyLineResp');">Submit</button>
                                                    </div>                                     
                                            </div> 
                                             </sf:form>                                           
                                            </div> 
                                                                                                                                
                                        </div>
                                        <!--fields of field group 5  -->

                                </div>
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
                               				<div id="tabstrip2">
                                        <ul class="preliminaryCommScr">
                                            <!-- tabs -->
                                            <li id="financialDetailBtnId" class="k-state-active" data-patnerid="" onclick="getFinancialDocuments(this)">View Financial Details</li>
                                            <li id="PCSTabId" data-itemscrutinyid="" onclick="getScrutinyPointList(this)">PC Commercial Scrutiny Verify Details</li>
                                            <li onclick="return submitWithParam('getCommercialDoc','bidderId' ,'loadDocDetails');" >CS Document Details</li>
                                            <li onclick="return submitWithParam('getFinalResp','commercialFinalScrutinyForm #itemScrutinyId' ,'setFinalResp');" >PC Verify Details </li>
                                        </ul>
                                        
										<div>
									
                                             <div id="financialDetailsDivId">
                                             	<div class="col-sm-4" >
                                             		<h4><b>Profit and Loss Account (CA Certified)</b></h4>
                                             		<div id="pnlFinancialDetailsId">
                                             		
                                             		</div>
                                             	</div>
                                             	<div class="col-sm-4" >
                                             		<h4><b>Balance Sheet Account (CA Certified)</b></h4>
                                             		<div id="bsFinancialDetailsId">
                                             		
                                             		</div>
                                             	</div>
                                             	<div class="col-sm-4">
                                             		<h4><b>Turnover Details</b></h4>
                                             		<div id="tdFinancialDetailsId">
                                             		
                                             		</div>
                                             	</div>
                                             </div>
										</div>
                                        
                                        <div>
                     
                                            <div class="form-group">
                                                <div class="col-sm-12">
                                                    <h4><b>Commercial scrutiny Document Details</b></h4>
                                                    <hr>
                                                </div>
                                            </div>                                            
                                            <sf:form id="PCSSPForm" modelAttribute="itemScrutinyLine" method="POST">                                          
                                             <div class="form-group">
                                             <div style="display: none;">
                                             <input type="text" class="itemScrutinyLineId" id="itemScrutinyLineId" name="itemScrutinyLineId" value="">
                                             <input type="text" class="itemScrutinyId" id="itemScrutinyId" name="itemScrutiny.itemScrutinyId" value="">
                                             <input type="text" id="bidderId" name="itemScrutiny.bidder.bidderId" value="">
                                             <input type="text" class="scrutinyPointId" id="scrutinyPointId" name="scrutinyPoint.scrutinyPointId" value="">
                                             <input type="text" class="tahdrMaterialId" id="tahdrMaterialId" name="" value="">
                                             <input type="text" id="isActive" name="isActive" value="Y">
                                             </div>
                                                <div class="col-sm-4">
                                                    <div class="styled-input">
                                                        <input type="text" id="scrutinyPointName" class="readonly">
                                                        <label id="labelId">Scrutiny Point Name</label>
                                                        <span></span>
                                                    </div>
                                               </div>       
                                               <div class="col-sm-4">
                                                    <div class="styled-input">
                                                        <input type="text" id="scrutinyPointDesp" class="readonly">
                                                        <label id="labelId">Scrutiny Point Description</label>
                                                        <span></span>
                                                    </div>
                                               </div> 
                                                </div>
                                                 <div class="form-group" id="auditorInputDivId" style="display: none ;">
	                                              <div class="col-sm-4">
	                                                    <!-- <div class="styled-input">
	                                                        <input type="text" id="auditorStatus" class="readonly">
	                                                        <label id="labelId"></label>
	                                                        <span></span>
	                                                         </div> -->
	                                                        <label>Auditor Status:</label>
                                                            <span class="detspan" id="auditorPrevStatus" ></span>
	                                                   
	                                               </div>   
	                                               <div class="col-sm-4">
	                                                    <!-- <div class="styled-input">
	                                                        <input type="text" id="auditorComment" class="readonly">
	                                                        <label id="labelId">Auditor Comment:</label>
	                                                        <span></span>
	                                                    </div>
	                                                    </div>   -->
	                                                    <label>Auditor Comment:</label>
                                                        <span class="detspan" id="auditorPrevComment" ></span>
	                                                
                                                  </div> 
                                                </div>      
                                             
                                            <div class="form-group">
                                                <div class="col-sm-4">
                                                    <div class="styled-input">
                                                                <select id="deviationTypeCode" name="isDeviation" class="dropDown" onchange="deviationStatusChange(this)">
                                                                <option value="">Select</option>
                                                                <option value="Y">YES</option>
                                                                 <option value="N">NO</option>
                                                                </select>
                                                                <label>DeviationStatus<span class="red">*</span></label>
                                                                <span></span>
                                                            </div>
                                                </div>                                                
                                            
                                            
                                                <div class="col-sm-4">
                                                     <div class="styled-input">
                                                                <select id="deviationType" name="deviationType" class="dropDown" disabled="disabled"></select>
                                                                 <label>DeviationTypeCode<span class="red">*</span></label>
                                                                 
                                                                <span></span>
                                                            </div>
                                                </div>                                                
                                            
                                            
                                                <div class="col-sm-4">
                                                    <div class="styled-input">
                                                        <input type="text" id="comment" name="deviationComment" >
                                                        <label id="labelId">Comment</label>
                                                        <span></span>
                                                    </div>
                                               </div>                                               
                                            </div>
                                            
                                               <div class="form-group" id="PCSSPActionBtnDiveId">
                                                <div class="col-sm-12 text-center">
                                                    <button class="btn btn-default save" onclick="return submitIt('PCSSPForm','saveItemScrutinyLine','itemScrutinyLineRespForScrutinyPoint');">Save</button>
                                                    <button class="btn btn-default cancel">Cancel</button>
                                                </div>
                                              </div> 
                                              </sf:form>
                                              <sf:form  id="auditorForm" modelAttribute="auditorData" method="POST" class="readonly">  
                                                <div class="form-group">
	                                                <div class="col-sm-12">
	                                                    <hr>
	                                                    <h4><b>Auditor Comment</b></h4>
	                                                    <hr>
	                                                </div>
                                            	</div>
                                            <div class="form-group">
                                                <input type="text" style="display: none ;" class="itemScrutinyLineId" id="itemScrutinyLineId" name="itemScrutinyLineId" value="">
                                                <div class="col-sm-4">
                                                    <div class="styled-input">
                                                                <select id="auditTypeCode" name="auditorStatus" class="">
                                                                <option value="">Select</option>
                                                                <option value="APPROVED">Approved</option>
                                                                 <option value="CLARIFICATION">Clarification</option>
                                                                </select>
                                                                <label>Audit Status<span class="red">*</span></label>
                                                                <span></span>
                                                            </div>
                                                </div>                                    
                                            
                                            
                                                <div class="col-sm-4">
                                                    <div class="styled-input">
                                                        <input type="text" id="comment" name="auditorComment" >
                                                        <label id="labelId">Comment</label>
                                                        <span></span>
                                                    </div>
                                               </div>                                               
                                            </div>
                                            
                                               <div class="form-group">
                                                <div class="col-sm-12 text-center">
                                                    <button class="btn btn-default save" onclick="return submitIt('auditorForm','saveAuditorComment','saveAuditorCommentResp');">Save</button>
                                                    <button class="btn btn-default cancel">Cancel</button>
                                                </div>
                                              </div>
                                              </sf:form>                                                                                
                                        </div>
                                        <!--fields of field group 3  -->
                                        <!--fields of field group 4  -->
                                        <div>
                          
                                            <div class="form-group">
                                                <div class="col-sm-12">
                                                    <h4><b>Preliminary Commercial scrutiny Details</b></h4>
                                                    <hr>
                                                </div>
                                            </div>
                                            <sf:form id="PCSDocForm" modelAttribute="itemScrutinyLine" method="POST">                                          
                                             <div class="form-group">
                                             <div style="display: none;">
                                             <input type="text" class="itemScrutinyLineId" id="itemScrutinyLineId" name="itemScrutinyLineId" value="">
                                             <input type="text" class="itemScrutinyId" id="itemScrutinyId" name="itemScrutiny.itemScrutinyId" value="">
                                             <input type="text" id="bidderId" name="itemScrutiny.bidder.bidderId" value="">
                                             <!-- <input type="text" id="" name="deviationType" value="Commercial"> -->
                                             <input type="text" class="tahdrMaterialId" id="tahdrMaterialId" name="tahdrMaterial.tahdrMaterialId" value="">
                                             <input type="text" id="bidderDocSecId" name="bidderSectionDoc.bidderSectionDocId" value="">
                                             <input type="text" id="isActive" name="isActive" value="Y">
                                             </div>
                                                <div class="col-sm-4">
		                                                <label>Document Name :</label>
		                                                         <span class="detspan" id=documentCopy></span>
														<!-- <label>Document Name</label> 
														    <input type="text" id="documentCopy" class="form-control" /> -->  
                                                </div>
                                                <div class="col-sm-4">
                                               <div class="styled-input">
			                                             	 <div id="purchase">
			                                             	 	<a id="PCSdownloadLinkId" data-url="<%=request.getContextPath()%>/download"><b><span class="glyphicon glyphicon-plus-sign paddright"></span><span id="">Download Document</span></b></a>
			                                             	 </div>
           	                                            </div>
           	                                            </div>
                                              </div>
                                            <div class="form-group">
                                                <div class="col-sm-4">
                                                    <div class="styled-input">
                                                                <select id="deviationTypeCode" name="isDeviation" class="dropDown" onchange="deviationStatusChange(this)">
                                                                <option value="">Select</option>
                                                                <option value="Y">YES</option>
                                                                 <option value="N">NO</option>
                                                                </select>
                                                                <label>DeviationStatus<span class="red">*</span></label>
                                                                <span></span>
                                                            </div>
                                                </div>                                                
                                            
                                            
                                                <div class="col-sm-4">
                                                     <div class="styled-input">
                                                                <select id="deviationType" name="deviationType" class="dropDown" disabled="disabled"></select>
                                                                 <label>DeviationTypeCode<span class="red">*</span></label>
                                                                 
                                                                <span></span>
                                                            </div>
                                                </div>                                                
                                            
                                            
                                                <div class="col-sm-4">
                                                    <div class="styled-input">
                                                        <input type="text" id="comment" name="deviationComment" >
                                                        <label id="labelId">Comment</label>
                                                        <span></span>
                                                    </div>
                                               </div>                                               
                                            </div>
                                            
                                               <div class="form-group">
                                                <div class="col-sm-12 text-center">
                                                    <button class="btn btn-default save" onclick="return submitIt('PCSDocForm','saveItemScrutinyLine','itemScrutinyLineRespForCommercial');">Save</button>
                                                    <button class="btn btn-default cancel">Cancel</button>
                                                </div>
                                              </div> 
                                              </sf:form>                                                                                                        
                                        </div>
                                        <!--fields of field group 4  -->
                                        <!--fields of field group 5  -->
                                        <div>
                                        
                                            <div class="form-group">
                                                <div class="col-sm-12">
                                                    <h4><b>Verify Commercial Scrutiny</b></h4>
                                                    <hr>
                                                </div>
                                            </div>
                                            <sf:form id="commercialFinalScrutinyForm" modelAttribute="itemScrutiny" method="POST">
                                             <div class="form-group">
                                             <input type="text"  style="display: none;" id="itemScrutinyId" name="itemScrutinyId" value="">
                                             <input type="text"  style="display: none;" id="bidderId" name="bidder.bidderId" value="">
                                                     <div class="col-sm-6">
			                                                    <label class="radio-inline" style="margin-top:20px;">
			                                                        <input type="radio"  id="statusApproved"  name="preliminaryScrutinyStatus" value="Approved">Approved
			                                                     </label>
			                                                      <label class="radio-inline"  style="margin-top:20px;">  
			                                                       <input type="radio"  id="statusDeviation"  name="preliminaryScrutinyStatus" value="Deviation">Deviation
			                                                    </label>
			                                                     <label class="radio-inline"  style="margin-top:20px;">  
			                                                       	<input type="radio" id="statusRejected"  name="preliminaryScrutinyStatus" value="Rejected">Reject
			                                                    </label>
			                                                    </div>
                                            		</div>
                                            <div class="form-group">
                                                <div class="col-sm-4">
                                                    <div class="styled-input">
                                                        <input type="text" id="" name="preliminaryScrutinyComment">
                                                        <label>Comments<span class="red">*</span></label>
                                                        <span></span>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                    <div class="col-sm-12 text-center">

                                                    	<button class="btn btn-default" onclick="return submitIt('commercialFinalScrutinyForm','savePreliminaryCommercialScrutiny','finalScrutinyLineResp');">Submit</button>

                                                    	
                                                    </div>                                     
                                            </div> 
                                            </sf:form>                                                                                      
                                        </div>
                                        <!--fields of field group 5  -->
                                    </div>
                                        </div>
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
                                                    <h4><b>Schedule Deviation Bid</b></h4>
                                                    <hr>
                                                </div>
                                            </div>
                                            <sf:form class="" id="deviationScheduleForm" modelAttribute="tahdrDetail" method="POST" >
                                            <div class="form-group">
                                            <input type="text" style="display: none;" id="tahdrDetailId" name="tahdrDetailId" />
                                            <input type="text" style="display: none;" id="isPBDSetLater" name="isPBDSetLater" />
                                             <input type="text" style="display: none;" id="tahdrCode" name="tahdr.tahdrCode" />
                                               <input type="text" style="display: none;" id="tahdrId" name="tahdr.tahdrId" />
                                            <div class="col-sm-4">
			                                    <div class="styled-input">
			                                       <input type="text" id="tenderNo" value="" class="readonly" />
			                                       <label>Tender No<span class="red">*</span></label>
			                                       <span></span>
			                                    </div>
			                                 </div>
			                        <div class="col-sm-4">
						                <label for="dtp_input1" class="control-label">Last Submission Date<span class="red">*</span></label>
						                <div class="input-group date form_datetime">				
						                    <input type="text" class="form-control" id="deviationToDate" name="deviationToDate"> <!-- name="lastC1SubmissionDate" -->
											<span class="input-group-addon"><span class="glyphicon glyphicon-th"></span></span>
						                </div>
										<input type="hidden" id="dtp_input1" value="" /><br/>
						            </div>
                                   
			                        <div class="col-sm-4">
						                <label for="dtp_input1" class="control-label">Deviation Bid Opening Date<span class="red">*</span></label>
						                <div class="input-group date form_datetime">				
						                    <input type="text" class="form-control" id="deviationOpenningDate" name="deviationOpenningDate"> <!-- name="lastC1SubmissionDate" -->
											<span class="input-group-addon"><span class="glyphicon glyphicon-th"></span></span>
						                </div>
										<input type="hidden" id="dtp_input1" value="" /><br/>
						            </div>
			                          
                                            </div>
                                            <div class="form-group">
                                            	<div class="col-sm-12 text-center">
                                            	 <button type="button" id="scheduleDeviationBtnId" class="btn btn-info save" onclick="return submitIt('deviationScheduleForm','saveDeviationSchedule','deviationScheduleResp');">Submit</button>
                                            	</div>
                                            </div>
                                            </sf:form>
                                        </div>
                                </div>
                                        </div>
                                             
    									</div>
                                        <!--fields of field group 2  -->
                                        
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
<script src="<%=request.getContextPath()%>/resources/${appMode}/transaction/js/tenderTechnicalScrutiny.js?appVer=${appVer}"></script>
<script src="<%=request.getContextPath()%>/resources/${appMode}/commons/js/formFields.js?appVer=${appVer}"></script>
<script src="<%=request.getContextPath()%>/resources/${appMode}/commons/js/utility.js?appVer=${appVer}"></script>
<script src="<%=request.getContextPath()%>/resources/${appMode}/tilescommon/js/bootstrap-datetimepicker.js?appVer=${appVer}"></script>
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
	    $('table').each(function(){		
			var text = []
			$(this).find('thead tr th').each(function(){
				text.push($(this).text())

				for (var i = 0; i < text.length; i++) {
					$(this).parents('table').find('tbody tr td:nth-of-type(' + (i + 1) +')').attr('data-th', text[i])
				}	
			});		
		});	
		$('.tableResponsive').DataTable({
			"lengthMenu":lengthMenu
		});
});
</script>
<script type="text/javascript">
    $('.form_datetime').datetimepicker({
    	weekStart: 1,
		autoclose: 1,
		startView: 2,
		forceParse: 0,
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