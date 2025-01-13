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
<!-- <style>

.sweet-alert {
    background-color: rgba(0,0,0,.5);
    width: 478px;
    padding: 17px;
    border-radius: 5px;
    text-align: center;
    position: fixed;
    left: 50%;
    top: 50%;
    margin-left: -256px;
    margin-top: -200px;
    overflow: hidden;
    display: none;
    z-index: 2000;
}
h1, h2 {
    font-weight: 300;
    color: #fcfcfc;
    margin-top: 32px;
    margin-bottom: 32px;
    text-align: center;
}
</style> -->
        <div class="full-container">
        <input type="hidden" id="myTenderUrl" class="myTenderUrl" value="${myTenderUrl}" />
            <!-- left-side start-->
            <div class="clearfix"></div>
            <div id="mobile_first_container" class="left-side col-md-3 no-marg">
            <div class="detailsheader">
        		<div class="col-sm-3 text-center brdrgt">
        		<label><span class="activeTab">${documentType} Scrutiny Report</span> (<span class="reportCount">0</span>)</label></div>
       		</div>         
                <div class="input-group">
                    <div class="input-group-btn search-panel">
                        <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">
                            <span id="search_concept">Filter by</span> <span class="caret"></span>
                        </button>
                        <ul class="dropdown-menu" role="menu">
                      <li><a href="#contains"><input type="radio" name="filterBy" value="tahdrCode" checked/> ${documentType} Code</a></li>
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
                <c:if test="${documentType.equalsIgnoreCase('Tender')}">
                <input type="hidden" class="documentType" value="${documentType}" />
					<div class="btn-group btnmrg" data-toggle="buttons">
			         <label class="btn btn-primary toggleTab" openTab="tenderDetailsTab" id="procurementToggle">
			         <input type="radio" name="tenderTypeCodeToggle" value="PT">
			         <span class="glyphicon glyphicon-ok"></span> Procurement 
			         </label>
			         <label class="btn btn-primary active toggleTab" openTab="tenderDetailsTab" id="worksToggle">
			         <input type="radio" name="tenderTypeCodeToggle" checked  id="worksCheckBoxId" value="WT">
			         <span class="glyphicon glyphicon-ok"></span> Works
			         </label>
			      </div> 
				</c:if>
				 <c:if test="${documentType.equalsIgnoreCase('Auction')}">
				 <input type="hidden" class="documentType" value="${documentType}" />
					<div class="btn-group btnmrg" data-toggle="buttons">
			         <label class="btn btn-primary toggleTab" openTab="tenderDetailsTab" id="forwardToggle">
			         <input type="radio" name="tenderTypeCodeToggle" value="FA">
			         <span class="glyphicon glyphicon-ok"></span> Forward 
			         </label>
			         <label class="btn btn-primary active toggleTab" openTab="tenderDetailsTab" id="reverseToggle">
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
        		<div class="col-sm-9 text-center"><label>${documentType} Scrutiny Details</label></div>
        	 </div>
                <div class="clearfix"></div>
                <div class="tab-content">
                    <!-- Master tab start-->

                    <div class="tab-pane active in" id="">
                        <div class="card">
                             <div class="posrelative">
                              <div class="demo-section k-content" id="preliminaryScrutinyPageId">
                                    <div id="tabstrip1" class="Firsttab">
                                        <ul>
                                            <!-- tabs -->
                                            <li id="tenderDetailTab" data-parentTab="" class="k-state-active tenderDetailsTab">${documentType} Details</li>
                                            <li data-parentTab="tenderDetailTab" id="bidderTabId" class="ulId"  onclick="loadBidders(this);">Bidder</li>
                                            
                                            <li data-parentTab="bidderTabId" class="scrutinyTabsId auditClass auditHidClass toggleTab" openTab="itemsTab" onclick="loadItems(this)">Preliminary Technical Scrutiny</li>
                                        
                                            
                                            <li data-parentTab="bidderTabId"  class="scrutinyTabsId toggleTab" openTab="scrutinyPointsTab" onclick="loadCommercialScrutinyDetail(this)">Preliminary Commercial Scrutiny</li>
                                            <li data-parentTab="tenderDetailTab" class="auditHidClass ulId" onclick="loadAuditor(this)">Auditor</li>
                                            <li data-parentTab="tenderDetailTab" class="ulId auditHidClass" onclick="loadDeviationScheduleDetail(this)">Schedule Deviation Bid</li>
                                             <li data-parentTab="tenderDetailTab" class="ulId auditNotHidClass" onclick="confirmAuditingTab(this)">Confirm Preliminary Auditing</li>
                                        </ul>
										<!--fields of field group 1  -->
                                        <div class="preliminaryScrutinyPage">
                                        <div class="detailscont">
  							 				
    									</div>
    									<div class="form-group">
                                                <div class="col-sm-12">
                                                    <h4><b>${documentType} Details</b></h4>
                                                     <span class="detspan approvedStatus" id="auditingStatus" ></span>
                                                    <hr>
                                                </div>
                                            </div>  
    									<form id="tahdrDetailForm" class="readonly">
    									<div class="form-group">
    									 <input type="text" style="display: none;" id="tahdrDetailId" name="" >
                                                <div class="col-sm-4">
                                                 
                                                        <label>${documentType} No:</label>
                                                        <span class="detspan" id="tenderNo" ></span>
                                                </div>
                                                <div class="col-sm-4">
                                                 <label>${documentType} Version:</label>
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
                                        <div class="preliminaryScrutinyPage">
                                        <div class="detailscont">
  							 				
    									</div>
    									<div class="form-group">
                                                <div class="col-sm-12">
                                                    <h4><b>Bidder Details</b></h4>
                                                    <hr>
                                                </div>
                                            </div>  
    									<form id="bidderDetailForm">
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
                                              <div class="form-group">
                                                <div class="col-sm-6">
                                                <label>Bidder Status:</label>
                                                        <span class="detspan" id="status" ></span>
                                                    
                                                </div>
                                                
                                             </div>
                                             
                                              <div class="form-group">
                                                <div class="col-sm-4">
                                                <label>Digital Signed Partner Registration Copy:</label>
                                                     <div class="styled-input">
			                                             	 <div id="">
			                                             	 	<a id="registrationCopydownloadLinkId" data-url="<%=request.getContextPath()%>/download"></a>
			                                             	 </div>
           	                                            </div>
                                                </div>                                                
                                            </div>
                                            
                                            <div class="col-sm-6">
                                            <div class="form-group">
                                            	<b>Technical Scrutiny Status :</b>
                                            	</div>
                                            	
                                            	<div class="clearfix"></div>
                                            <div class="form-group">
                                            <div class="col-sm-12">
                                            <div id="tScrutinyStatusDiv"></div>
                                            </div>
                                            </div>
                                             </div>
                                             <div class="col-sm-6">
                                            <div class="form-group">
                                            <label class="col-sm-6">Commercial Scrutiny Status :</label>
                                            <div class="col-sm-6" id="cScrutinyStatusDiv"></div>
                                            </div>
                                            <div class="form-group">
                                            <label class="col-sm-6">Auditing Scrutiny Status :</label>
                                            <div  class="col-sm-6" id="aScrutinyStatusDiv"></div>
                                            </div>
                                            </div>
                                            </form>
                                             
    									</div>
                                        <!--fields of field group 2  -->
                                        
                                        <div class="auditHidClass">
                                        <div class="detailscont">
  							 				
    									</div>
                                    <div id="tabstrip">
                                        <ul class="ulId">
                                            <!-- tabs -->
                                           <li data-parentTab="bidderTabId" id="itemTabId" class="k-state-active technicalScrutinyTab itemsTab" onclick="loadItems(this)">Items</li>
                                            
                                            <li data-parentTab="itemTabId" class="technicalScrutinyTab gtpTab pTSGtpFormTabId" onclick="loadGtpDetail(this)">PTS GTP Details</li>
                                            
                                            <li data-parentTab="itemTabId" class="technicalScrutinyTab" onclick="loadTSDocumentDetail(this)">PTS Document Details</li>
                                            <li data-parentTab="itemTabId" class="technicalScrutinyTab" onclick="loadTSFinalResp(this)">PTS Verify Details</li>
                                        </ul>

                                       
                                        <div>
                                            <div class="form-group">
                                                <div class="col-sm-12">
                                                    <h4><b>Item Details</b></h4>
                                                    <hr>
                                                </div>
                                            </div>  
                                            <sf:form id="itemDetailForm" modelAttribute="itemScrutiny" method="POST" class="auditClass ">                                          
                                             <div class="form-group">
                                                <div class="col-sm-4">
                                                    <input type="text" style="display: none;" id="itemBidId" >
                                                    <input type="text" style="display: none;" id="tahdrMaterialId" >
                                                    <input type="text" style="display: none;" id="ItemScrutinyId" >
                                                     <!--  <div class="styled-input">
                                                       <input type="text" id="name" class="readonly" >
                                                        <label>Item Name</label>
                                                        <span></span> 
                                                    </div> -->
                                                    <label>Item Name:</label>
                                                        <span class="detspan" id="name" ></span>
                                                </div>
                                                <div class="col-sm-4">
                                                    <!-- <div class="styled-input">
                                                     <input type="text" id="uomName" class="readonly" >
                                                        <label>UOM</label>
                                                        <span></span> 
                                                    </div> -->
                                                    <label>UOM:</label>
                                                        <span class="detspan" id="uomName" ></span>
                                                </div>
                                                <div class="col-sm-4">
                                                   <!--  <div class="styled-input">
                                                      <input type="text" id="description" class="readonly">
                                                        <label>Description</label>
                                                        <span></span>
                                                    </div> -->
                                                     <label>Description:</label>
                                                        <span class="detspan" id="description" ></span>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <div class="col-sm-4">
                                                    <!-- <div class="styled-input">
                                                        <input type="text" id="hsnCode" class="readonly">
                                                        <label>hsnCode</label>
                                                        <span></span>
                                                    </div> -->
                                                     <label>Hsn Code:</label>
                                                        <span class="detspan" id="hsnCode" ></span>
                                                </div>                                                
                                            </div>
                                            <div class="form-group">
                                                <div class="col-sm-4">
                                                <label>Digital Signed Technical Bid:</label>
                                                     <div class="styled-input">
			                                             	 <div id="">
			                                             	 	<a id="technicaldownloadLinkId" data-url="<%=request.getContextPath()%>/download"><b><span class="glyphicon glyphicon-plus-sign paddright"></span><span id="">No file</span></b></a>
			                                             	 </div>
           	                                            </div>
                                                </div>                                                
                                            </div>
                                            <div class="form-group">
                                                <div class="col-sm-12">
                                                    <!-- <button class="btn btn-default" id="startScrutinyBtnId" data-itembidid="" onclick="startScrutiny(this)">Start Scrutiny</button> -->
                                                </div>
                                              </div> 
                                              </sf:form>
                                                                               
                                        </div>
                                         
                                        <!--fields of field group 2  -->
                                        
                                        <!--fields of field group 3  -->
                                        <div class="preliminaryScrutinyPage">
                                            <div class="form-group pTSGtpFormTabId">
                                                <div class="col-sm-12">
                                                    <h4><b>Preliminary Technical scrutiny GTP Details</b></h4>
                                                    <hr>
                                                </div>
                                            </div>                                            
                                             <sf:form id="PTSGtpForm" modelAttribute="itemScrutinyLine" method="POST" class="auditClass">                                          
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
                                                    <!-- <div class="styled-input" class="readonly">
                                                        <input type="text" id="gtptype" name="" >
                                                        <label>GTP Type</label>
                                                        <span></span>
                                                    </div> -->
                                                     <div class="col-sm-12">
                                                    <label>GTP Type:</label>
                                                        <span class="detspan" id="gtptype" ></span>
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
                                                                <select id="deviationTypeCode" name="isDeviation" class="dropDown" onchange="deviationStatusChange(this,'PTSGtpForm')">
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
                                                    <textarea class="requiredField" id="comment" name="deviationComment" maxlength="255" ></textarea>
                                                        <!-- <input type="text" id="comment" class="requiredField" name="deviationComment" > -->
                                                        <label id="labelId">Comment <span class="red">*</span></label>
                                                        <span></span>
                                                    </div>
                                               </div>                                               
                                            </div>
                                            
                                               <div class="form-group">
                                                <div class="col-sm-12 text-center">
                                                    <!-- <button class="btn btn-default save" onclick="return submitIt('PTSGtpForm','saveItemScrutinyLine','itemScrutinyLineRespForTechnical');">Save</button> -->
                                                    <button class="btn btn-default save" onclick="return submitIt('PTSGtpForm','updateTechnicalItemScrutinyLine','itemScrutinyLineRespForTechnical');">Save</button>
                                                    <!-- <button class="btn btn-default cancel">Cancel</button> -->
                                                </div>
                                              </div> 
                                              </sf:form>                                            
                                        </div>
                                        <!--fields of field group 3  -->
                                        <!--fields of field group 4  -->
                                        <div class="preliminaryScrutinyPage">
                                            <div class="form-group">
                                                <div class="col-sm-12">
                                                    <h4><b>Preliminary Technical Required Document Details</b></h4>
                                                    <hr>
                                                </div>
                                            </div>
                                            <sf:form id="PTSDocForm" modelAttribute="itemScrutinyLine" method="POST" class="auditClass">                                          
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
			                                             	 	<a id="PTSdownloadLinkId" data-url="<%=request.getContextPath()%>/download"><b><span class="glyphicon glyphicon-plus-sign paddright"></span><span id="">No File</span></b></a>
			                                             	 </div>
           	                                            </div>
           	                                            </div>
                                              </div>
                                            <div class="form-group">
                                                <div class="col-sm-4">
                                                    <div class="styled-input">
                                                                <select id="deviationTypeCode" name="isDeviation" class="dropDown" onchange="deviationStatusChange(this,'PTSDocForm')">
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
                                                    <textarea class="requiredField" id="comment" name="deviationComment" maxlength="255" ></textarea>
                                                       <!--  <input type="text" id="comment" class="requiredField" name="deviationComment" > -->
                                                        <label id="labelId">Comment<span class="red">*</span></label>
                                                        <span></span>
                                                    </div>
                                               </div>                                               
                                            </div>
                                            
                                               <div class="form-group">
                                                <div class="col-sm-12 text-center">
                                                   <!--  <button class="btn btn-default save" onclick="return submitIt('PTSDocForm','saveItemScrutinyLine','itemScrutinyLineRespForTechnical');">Save</button> -->
                                                    <button class="btn btn-default save" onclick="return submitIt('PTSDocForm','updateTechnicalItemScrutinyLine','itemScrutinyLineRespForTechnicalDoc');">Save</button>
                                                   <!--  <button class="btn btn-default cancel">Cancel</button> -->
                                                </div>
                                              </div> 
                                              </sf:form>                                                                                                    
                                        </div>
                                        <!--fields of field group 4  -->
                                        <!--fields of field group 5  -->
                                        <div class="preliminaryScrutinyPage">
                                       
                                            <div class="form-group">
                                                <div class="col-sm-12">
                                                    <h4><b>Confirm Technical  scrutiny</b></h4>
                                                    <hr>
                                                </div>
                                            </div>
                                            
                                            <div class="form-group">
                                             <sf:form id="gennerateTechnicalScrutinyDoc">
                                                        <input type="text"  style="display: none;" id="scrutinyFileId" name="scrutinyFileId" value="">
                                                        <input type="text"  style="display: none;" id="bidderId" name="bidder.bidderId" value="">
                                                         <input type="text"  style="display: none;" id="itemScrutinyId" name="itemScrutiny.itemScrutinyId" value="">
                                                         <input type="text"  style="display: none;" id="itemBidId" name="itemBid.itemBidId" value="">
                                            			 <input type="text"  style="display: none;" id="scrutinyLevel" name="scrutinyLevel" value="PRELIMINARY">
                                            			 <input type="text"  style="display: none;" id="scrutinyType" name="scrutinyType" value="TECHSCR">
						                      </sf:form>
                                                    
                                                    <sf:form id="technicalFinalScrutinyForm" modelAttribute="itemScrutiny" method="POST" class="auditClass">
                                                     <div class="form-group">
                                                     
                                            			 <input type="text"  style="display: none;" id="itemScrutinyId" name="itemScrutinyId" value="">
                                            			 <input type="text"  style="display: none;" id="bidderId" name="bidder.bidderId" value="">
                                            			 <input type="text"  style="display: none;" id="itemBidId" name="itemBid.itemBidId" value="">
                                                     <div class="col-sm-6">
                                                     
			                                                    <label class="radio-inline" style="margin-top:20px;">
			                                                        <input type="radio" id="statusApproved" data-status="PTP" name="preliminaryScrutinyStatus" value="Approved">Approved
			                                                         </label>
			                                                       
			                                                     <label class="radio-inline"  style="margin-top:20px;">  
			                                                       <input type="radio" id="statusDeviation" data-status="DVTN" name="preliminaryScrutinyStatus" value="Deviation" checked>Deviation
			                                                    </label>
			                                                    
			                                                    <label class="radio-inline"  style="margin-top:20px;">  
			                                                       <input type="radio" id="statusRejected" data-status="PTF" name="preliminaryScrutinyStatus" value="Rejected">Reject
			                                                    </label>
			                                                    </div>
                                            		</div>
                                                    <div class="form-group">
                                                     <div class="col-sm-4">
                                                    <div class="styled-input">
                                                    <textarea id="finalComment"  class="requiredField" name="preliminaryScrutinyComment" maxlength="255" ></textarea>
                                                       <!--  <input type="text" id="finalComment"  class="requiredField" name="preliminaryScrutinyComment"> -->
                                                        <label>Comments<span class="red">*</span></label>
                                                        <span></span>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                    <div class="col-sm-12">
                                                    	
                                                        <button class="btn btn-default save" onclick="return submitIt('technicalFinalScrutinyForm','savePreliminaryTechnicalScrutiny','finalScrutinyLineResp');">Save Scrutiny</button>
                                                    </div>                                     
                                            </div> 
                                             </sf:form>
                                            <div class="auditHidClass">
           	                                    <div class="col-sm-12">
                                                    	<button onclick="uploadTechnicalScrutinyPdf(event)" class="btn btn-default scrDocDownloadBtnId">Generate Technical Scrutiny PDF</button>
                                                    	<!-- <button class="btn btn-default">Goto Main Page</button> -->
                                                    </div> 
                                                    <div class="clearfix"></div>
                                                    <br/>
           	                                    <label class="col-sm-12">Download Technical Scrutiny File:
           	                                             <a class="filelabel" data-url="<%=request.getContextPath()%>/download" id="a_techFileResponse"></a>
           	                                             </label>  
           	                                </div>              
                                            </div> 
                                                                                                                                
                                        </div>
                                        <!--fields of field group 5  -->

                                </div>
                                        </div>
                                        <!--fields of field group 1  -->
                                         <!--fields of field group 2  -->
                                        <div>
                                        <div class="detailscont">
  							 				
    									</div>
                               				<div id="tabstrip2">
                                        <ul class="preliminaryCommScr">
                                            <!-- tabs -->
                                            <li id="financialDetailBtnId" data-parentTab="bidderTabId" id="finacialDocId" class="k-state-active scrutinyPointsTab" data-patnerid="" onclick="getFinancialDocuments(this)">View Financial Details</li>
                                            <li data-parentTab="bidderTabId" class="" onclick="loadCSDocumentDetail(this)" >CS Document Details</li>
                                            <li data-parentTab="bidderTabId" id="PCSTabId"  data-itemscrutinyid="" onclick="getScrutinyPointList(this)">PC Commercial Scrutiny Verify Details</li>
                                            <li data-parentTab="bidderTabId" class="" onclick="loadCSFinalResp(this)" >PC Verify Details </li>
                                        </ul>
                                        
										<div class="preliminaryScrutinyPage">
									
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
                                             <div class="form-group">
                                                <div class="col-sm-4">
                                                <label>Digital Signed Commercial Bid:</label>
                                                     <div class="styled-input">
			                                             	 <div id="">
			                                             	 	<a id="commercialdownloadLinkId" data-url="<%=request.getContextPath()%>/download"><b><span class="glyphicon glyphicon-plus-sign paddright"></span><span id="">No File</span></b></a>
			                                             	 </div>
           	                                            </div>
                                                </div>                                                
                                            </div>
										</div>
                                        <div class="preliminaryScrutinyPage">
                          
                                            <div class="form-group">
                                                <div class="col-sm-12">
                                                    <h4><b>Preliminary Commercial Required Document Details</b></h4>
                                                    <hr>
                                                </div>
                                            </div>
                                            <sf:form id="PCSDocForm" modelAttribute="itemScrutinyLine" method="POST" class="auditClass">                                          
                                             <div class="form-group">
                                             <div style="display: none;">
                                             <input type="text" class="itemScrutinyLineId" id="itemScrutinyLineId" name="itemScrutinyLineId" value="">
                                             <input type="text" class="itemScrutinyId" id="itemScrutinyId" name="itemScrutiny.itemScrutinyId" value="">
                                             <input type="text" id="bidderId" name="itemScrutiny.bidder.bidderId" value="">
                                             <input type="text" class="tahdrMaterialId" id="tahdrMaterialId" name="" value="">
                                             <input type="text" id="bidderDocSecId" name="bidderSectionDoc.bidderSectionDocId" value="">
                                             <input type="text" id="isActive" name="isActive" value="Y">
                                             <input type="text" id="auditPrevStatus" name="auditorStatus" value="">
                                             <input type="text" id="auditPrevComment" name="auditorComment" value="">
                                             </div>
                                             
                                             <div class="form-group" id="auditorInputDivId" style="display: none ;">
	                                              <div class="col-sm-4">
	                                                    
	                                                        <label>Auditor Status:</label>
                                                            <span class="detspan" id="auditorDocPrevStatus" ></span>
	                                                   
	                                               </div>   
	                                               <div class="col-sm-4">
	                                                    
	                                                    <label>Auditor Comment:</label>
                                                        <span class="detspan" id="auditorDocPrevComment" ></span>
	                                                
                                                  </div> 
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
                                                                <select id="deviationTypeCode" name="isDeviation" class="dropDown" onchange="deviationStatusChange(this,'PCSDocForm')">
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
                                                    <textarea id="comment" class="requiredField" name="deviationComment"  maxlength="255" ></textarea>
                                                        <!-- <input type="text" id="comment" class="requiredField" name="deviationComment" > -->
                                                        <label id="labelId">Comment<span class="red">*</span></label>
                                                        <span></span>
                                                    </div>
                                               </div>                                               
                                            </div>
                                            
                                               <div class="form-group">
                                                <div class="col-sm-12 text-center">
                                                    <!-- <button class="btn btn-default save" onclick="return submitIt('PCSDocForm','saveItemScrutinyLine','itemScrutinyLineRespForCommercial');">Save</button> -->
                                                   <button class="btn btn-default save" onclick="return submitIt('PCSDocForm','updateCommercialItemScrutinyLine','itemScrutinyLineRespForCommercialDoc');">Save</button>
                                                   <!--  <button class="btn btn-default cancel">Cancel</button> -->
                                                </div>
                                              </div> 
                                              </sf:form>
                                              <sf:form  id="auditorDocForm" modelAttribute="auditorData" method="POST" class="readonly">  
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
                                                                <select id="auditTypeCode" name="auditorStatus" class="dropDown">
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
                                                    <textarea id="comment" name="auditorComment" class="requiredField"  maxlength="255" ></textarea>
                                                        <!-- <input type="text" id="comment" name="auditorComment" class="requiredField"> -->
                                                        <label id="labelId">Comment<span class="red">*</span></label>
                                                        <span></span>
                                                    </div>
                                               </div>                                               
                                            </div>
                                            
                                               <div class="form-group">
                                                <div class="col-sm-12 text-center">
                                                    <button class="btn btn-default save" onclick="return submitIt('auditorDocForm','saveAuditorComment','saveAuditorCommentResp');">Save</button>
                                                   <!--  <button class="btn btn-default cancel">Cancel</button> -->
                                                </div>
                                              </div>
                                              </sf:form>                                                                                                                                             
                                        </div>
                                        <div class="preliminaryScrutinyPage">
                     
                                            <div class="form-group">
                                                <div class="col-sm-12">
                                                    <h4><b>Commercial scrutiny Point Details</b></h4>
                                                    <hr>
                                                </div>
                                            </div>                                            
                                            <sf:form id="PCSSPForm" modelAttribute="itemScrutinyLine" method="POST" class="auditClass">                                          
                                             <div class="form-group">
                                             <div style="display: none;">
                                             <input type="text" class="itemScrutinyLineId" id="itemScrutinyLineId" name="itemScrutinyLineId" value="">
                                             <input type="text" class="itemScrutinyId" id="itemScrutinyId" name="itemScrutiny.itemScrutinyId" value="">
                                             <input type="text" id="bidderId" name="itemScrutiny.bidder.bidderId" value="">
                                             <input type="text" class="scrutinyPointId" id="scrutinyPointId" name="scrutinyPoint.scrutinyPointId" value="">
                                             <input type="text" class="tahdrMaterialId" id="tahdrMaterialId" name="" value="">
                                             <input type="text" id="isActive" name="isActive" value="Y">
                                             <input type="text" id="auditPrevStatus" name="auditorStatus" value="">
                                             <input type="text" id="auditPrevComment" name="auditorComment" value="">
                                             </div>
                                             <div class="form-group" id="auditorInputDivId" style="display: none ;">
	                                              <div class="col-sm-4">
	                                                   
	                                                        <label>Auditor Status:</label>
                                                            <span class="detspan" id="auditorPrevStatus" ></span>
	                                                   
	                                               </div>   
	                                               <div class="col-sm-4">
	                                                   
	                                                    <label>Auditor Comment:</label>
                                                        <span class="detspan" id="auditorPrevComment" ></span>
	                                                
                                                  </div> 
                                                </div>      
                                                
                                                <div class="col-sm-6">
                                                   <!--  <div class="styled-input">
                                                     <textarea id="scrutinyPointName"></textarea>
                                                        <label id="labelId">Scrutiny Point Name</label>
                                                        <span></span>
                                                    </div> -->
                                                    <div class="col-sm-12">
                                                    <label>Scrutiny Point Name:</label>
                                                        <span class="detspan" id="scrutinyPointName" ></span>
                                                     </div>
                                               </div>       
                                               <div class="col-sm-6">
                                                    <!-- <div class="styled-input">
                                                    <textarea id="scrutinyPointDesp"></textarea>
                                                        <label id="labelId">Scrutiny Point Description</label>
                                                        <span></span>
                                                    </div> -->
                                                    <div class="col-sm-12">
                                                    <label>Scrutiny Point Description:</label>
                                                        <span class="detspan" id="scrutinyPointDesp" ></span>
                                                     </div>
                                               </div> 
                                                </div>
                                                 
                                             
                                            <div class="form-group">
                                                <div class="col-sm-4">
                                                    <div class="styled-input">
                                                                <select id="deviationTypeCode" name="isDeviation" class="dropDown" onchange="deviationStatusChange(this,'PCSSPForm')">
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
                                                    <textarea id="comment" class="requiredField" name="deviationComment"  maxlength="255" ></textarea>
                                                       <!--  <input type="text" id="comment" class="requiredField" name="deviationComment" > -->
                                                        <label id="labelId">Comment<span class="red">*</span></label>
                                                        <span></span>
                                                    </div>
                                               </div>                                               
                                            </div>
                                            
                                               <div class="form-group" id="PCSSPActionBtnDiveId">
                                                <div class="col-sm-12 text-center">
                                                    <!-- <button class="btn btn-default save" onclick="return submitIt('PCSSPForm','saveItemScrutinyLine','itemScrutinyLineRespForScrutinyPoint');">Save</button> -->
                                                    <button class="btn btn-default save" onclick="return submitIt('PCSSPForm','updateCommercialItemScrutinyLine','itemScrutinyLineRespForCommercial');">Save</button>
                                                    <!-- <button class="btn btn-default cancel">Cancel</button> -->
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
                                                                <select id="auditTypeCode" name="auditorStatus" class="dropDown">
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
                                                     <textarea id="comment" name="auditorComment" class="requiredField" maxlength="255" ></textarea>
                                                        <!-- <input type="text" id="comment" name="auditorComment" class="requiredField"> -->
                                                        <label id="labelId">Comment<span class="red">*</span></label>
                                                        <span></span>
                                                    </div>
                                               </div>                                               
                                            </div>
                                            
                                               <div class="form-group">
                                                <div class="col-sm-12 text-center">
                                                    <button class="btn btn-default save" onclick="return submitIt('auditorForm','saveAuditorComment','saveAuditorCommentResp');">Save</button>
                                                   <!--  <button class="btn btn-default cancel">Cancel</button> -->
                                                </div>
                                              </div>
                                              </sf:form>                                                                                
                                        </div>
                                        <!--fields of field group 3  -->
                                        
                                        <!--fields of field group 5  -->
                                        <div class="preliminaryScrutinyPage">
                                        
                                            <div class="form-group">
                                                <div class="col-sm-12">
                                                    <h4><b>Confirm Commercial Scrutiny</b></h4>
                                                    <hr>
                                                </div>
                                            </div>
                                           <div class="auditorClass">
                                            <div class="col-sm-12">
                                             <sf:form id="gennerateCommercialScrutinyDoc">
                                                         <input type="text"  style="display: none;" id="scrutinyFileId" name="scrutinyFileId" value="">  
                                                         <input type="text"  style="display: none;" id="bidderId" name="bidder.bidderId" value="">
                                                         <input type="text"  style="display: none;" id="itemScrutinyId" name="itemScrutiny.itemScrutinyId" value="">
                                            			 <input type="text"  style="display: none;" id="scrutinyLevel" name="scrutinyLevel" value="PRELIMINARY">
                                            			 <input type="text"  style="display: none;" id="scrutinyType" name="scrutinyType" value="COMMSCR">
						                      </sf:form>
                                                    </div> 
                                            </div>
                                            <sf:form id="commercialFinalScrutinyForm" modelAttribute="itemScrutiny" method="POST" class="auditClass">
                                             <div class="form-group">
                                             <input type="text"  style="display: none;" id="itemScrutinyId" name="itemScrutinyId" value="">
                                             <input type="text"  style="display: none;" id="bidderId" name="bidder.bidderId" value="">
                                             <input type="text"  style="display: none;" id="auditPrevStatus" name="preliminaryAuditorStatus" value="">
                                             <input type="text"  style="display: none;" id="auditPrevComment" name="preliminaryAuditorStatus" value="">
                                             
                                             <div class="form-group" id="auditorInputDivId" style="display: none ;">
	                                              <div class="col-sm-4">
	                                                    
	                                                        <label>Auditor Status:</label>
                                                            <span class="detspan" id="auditorDocPrevStatus" ></span>
	                                                   
	                                               </div>   
	                                               <div class="col-sm-4">
	                                                    
	                                                    <label>Auditor Comment:</label>
                                                        <span class="detspan" id="auditorDocPrevComment" ></span>
	                                                
                                                  </div> 
                                                </div>
                                                 <!-- <div class="styled-input">
                                                                <select id="auditorDropDown" name="" class="dropDown">
                                                                <option value="">Select</option>
                                                                </select>
                                                                <label>Auditor User<span class="red">*</span></label>
                                                                <span></span>
                                                            </div> -->
                                                     <div class="col-sm-6">
			                                                    <label class="radio-inline" style="margin-top:20px;">
			                                                        <input type="radio"  id="statusApproved"  name="preliminaryScrutinyStatus" value="Approved">Approved
			                                                     </label>
			                                                      <label class="radio-inline"  style="margin-top:20px;">  
			                                                       <input type="radio"  id="statusDeviation"  name="preliminaryScrutinyStatus" value="Deviation" checked>Deviation
			                                                    </label>
			                                                     <label class="radio-inline"  style="margin-top:20px;">  
			                                                       	<input type="radio" id="statusRejected"  name="preliminaryScrutinyStatus" value="Rejected">Reject
			                                                    </label>
			                                                    </div>
                                            		</div>
                                            <div class="form-group">
                                                <div class="col-sm-4">
                                                    <div class="styled-input">
                                                    <textarea id="ComComments" class="requiredField" name="preliminaryScrutinyComment" maxlength="255" ></textarea>
                                                       <!--  <input type="text" id="ComComments" class="requiredField" name="preliminaryScrutinyComment"> -->
                                                        <label>Comments<span class="red">*</span></label>
                                                        <span></span>
                                                    </div>
                                                </div>
                                            </div>
                                            
                                            <div class="form-group">
                                                    <div class="col-sm-12 text-center">
                                                    	<button class="btn btn-default save" onclick="return submitIt('commercialFinalScrutinyForm','savePreliminaryCommercialScrutiny','finalScrutinyLineResp');">Save Scrutiny</button>
	
                                                    </div>                                     
                                            </div> 
                                            </sf:form> 
                                            <div class="auditHidClass">
                                            <div class="col-sm-12">
           	                                    <button onclick="uploadCommercialScrutinyPdf(event)" class="btn btn-default scrDocDownloadBtnId scrCommDocDownloadBtnId">Generate Commercial Scrutiny PDF</button>
           	                                   </div>
           	                                   <div class="clearfix"></div>
           	                                   <br/>
           	                                    <label class="col-sm-12">Download Commercial Scrutiny File:
           	                                             <a class="filelabel" data-url="<%=request.getContextPath()%>/download" id="a_commFileResponse"></a>
                                            </label>
                                            </div>
                                            <sf:form  id="auditorConfirmForm" modelAttribute="auditorData" method="POST" class="readonly">  
                                                <div class="form-group">
	                                                <div class="col-sm-12">
	                                                    <hr>
	                                                    <h4><b>Auditor Comment</b></h4>
	                                                    <hr>
	                                                </div>
                                            	</div>
                                            <div class="form-group">
                                                <input type="text" style="display: none ;" id="itemScrutinyId" name="itemScrutinyId" value="">
                                                <input type="text" style="display: none ;" id="bidderId" name="bidder.bidderId" value="">
                                                <input type="text" style="display: none ;" id="" name="isAudited" value="Y">
                                                <div class="col-sm-4">
                                                    <div class="styled-input">
                                                                <select id="auditTypeCode" name="preliminaryAuditorStatus" class="dropDown">
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
                                                    <textarea id="comment" name="preliminaryAuditorComment" class="requiredField" maxlength="255" ></textarea>
                                                       <!--  <input type="text" id="comment" name="preliminaryAuditorComment" class="requiredField"> -->
                                                        <label id="labelId">Comment<span class="red">*</span></label>
                                                        <span></span>
                                                    </div>
                                               </div>                                               
                                            </div>
                                            
                                               <div class="form-group">
                                                <div class="col-sm-12 text-center">
                                                    <button class="btn btn-default save" onclick="return submitIt('auditorConfirmForm','confirmAuditorComment','saveAuditorCommentResp');">Save</button>
                                                   <!--  <button class="btn btn-default cancel">Cancel</button> -->
                                                </div>
                                              </div>
                                              </sf:form>                                                                                                                          
                                        </div>
                                        <!--fields of field group 5  -->
                                    </div>
                                        </div>
                                        <!--fields of field group 2  -->
                                         <!--fields of field group 3  -->
                                        <div class="auditHidClass">
                                        	<div class="detailscont">
  							 				
    									</div>
    									<div class="form-group preliminaryScrutinyPage">
                                                <div class="col-sm-12">
                                                    <h4 class="col-sm-6"><b>Add Auditor</b></h4>
                                                     <div class="col-sm-6 text-right" style="margin-top: 5px;">
	                                                <button type="button"  id="intimidateAuditorBtnId" class="btn btn-info bluebutton" onclick="intimidateAuditor(event)" >
	                                                    <i class="fa fa-envelope" style="font-size:16px;" aria-hidden="true"></i>  Notify Auditor</button>
	                                                </div>
                                                </div>
                                                <hr>
                                            </div>
                                            <sf:form id="addAuditorForm" modelAttribute="tahdr" method="POST" >
                                            <div class="form-group">
                                            <input type="text" style="display: none ;" id="tahdrId" name="tahdrId" value="">
                                            <input type="text" style="display: none ;" id="tahdrName" name="tahdrCode" value="">
                                            <div class="col-sm-4">
			                                     <div class="styled-input preliminaryScrutinyPage">
                                                                <select  id="auditorDropDownId" name="auditorUser.userId" class="dropDown">
                                                                </select>
                                                                <label>Auditors List:<span class="red">*</span></label>
                                                                <span></span>
                                                            </div>
			                                  </div>
			                                    
			                                 </div>
			                       
                                            <div class="form-group">
                                            	<div class="col-sm-12 text-center">
                                            	 <button type="button" id="saveAuditorBtnId" class="btn btn-info save" onclick="addAuditor(event)">Save</button>
                                            	</div>
                                            </div>
                                            </sf:form>
                                        </div>
                                        <!--fields of field group 3  -->
                                        <!--fields of field group 3  -->
                                        <div class="auditHidClass preliminaryScrutinyPage">
                                        	<div class="detailscont">
  							 				
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
                                             <input type="text" style="display: none;" id="bidType" name="tahdr.bidTypeCode" />
                                               <input type="text" style="display: none;" id="tahdrId" name="tahdr.tahdrId" />
                                               <input type="text" style="display: none;" id="" name="tahdrScheduling" value="DBSCH"/>
                                            <div class="col-sm-4">
			                                    <div class="styled-input">
			                                       <input type="text" id="tenderNo" value="" class="readonly" />
			                                       <label>${documentType} No<span class="red">*</span></label>
			                                       <span></span>
			                                    </div>
			                                  </div>
			                                 </div>
			                       <div class="form-group">
			                        <div class="col-sm-4">
                                                <label class="checkbox-inline" id="checkBoxLabelId">
                                                    <input type="checkbox" id="checkBoxId" value="N" name="isNoDeviation" > Complete Scrutiny with No Deviation</label>
                                            </div>
			                      
			                        <div class="col-sm-4">
						                <label for="dtp_input1" class="control-label">Deviation Bid Submission Date<span class="red">*</span></label>
						                <div class="input-group date form_datetime">				
						                    <input type="text" class="form-control" id="deviationToDate" name="deviationToDate" class="requiredField"> <!-- name="lastC1SubmissionDate" -->
											<span class="input-group-addon"><span class="glyphicon glyphicon-th"></span></span>
						                </div>
										<input type="hidden" id="dtp_input1" value="" /><br/>
						            </div>
                                   
			                        <div class="col-sm-4">
						                <label for="dtp_input1" class="control-label">Deviation Bid Opening Date<span class="red">*</span></label>
						                <div class="input-group date form_datetime">				
						                    <input type="text" class="form-control " id="deviationOpenningDate" name="deviationOpenningDate" class="requiredField"> <!-- name="lastC1SubmissionDate" -->
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
                                        <div class="auditNotHidClass preliminaryScrutinyPage">
                                        	<div class="detailscont">
  							 				
    									</div>
    									<div class="form-group">
                                                <div class="col-sm-12">
                                                    <h4><b>Confirm Auditing</b></h4>
                                                    <hr>
                                                </div>
                                            </div>
                                            
                                            <sf:form class="" id="confirmAuditingForm" modelAttribute="tahdr" method="POST" >
                                            <div class="form-group">
                                               <input type="text" style="display: none;" id="tahdrId" name="tahdrId" />
                                               <input type="text" style="display: none;" id="tahdrName" name="tahdrCode" />
			                                 </div>
			                       <div class="form-group">
			                        <div class="col-sm-4">
                                                <label class="checkbox-inline" id="checkBoxLabelId">
                                                    <input type="checkbox" value="Y" checked> Auditing is Done.All Bidder Commercial Scrutiny is Audited.</label>
                                            </div>
						            </div>
                                            <div class="form-group">
                                            	<div class="col-sm-12 text-center">
                                            	 <button type="button" id="confirmAuditingBtnId" class="btn btn-info save" onclick="intimidateScrutinyEngr(event)">Notify Scrutiny Engineers</button>
                                            	</div>
                                            </div>
                                            </sf:form>
                                        </div> 
                                        <!--fields of field group 3  -->
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
<script src="<%=request.getContextPath()%>/resources/${appMode}/transaction/js/preliminaryScrutinyFile.js?appVer=${appVer}"></script>
<script src="<%=request.getContextPath()%>/resources/${appMode}/transaction/js/preliminaryScrutiny.js?appVer=${appVer}"></script>
<script src="<%=request.getContextPath()%>/resources/${appMode}/commons/js/formFields.js?appVer=${appVer}"></script>
<script src="<%=request.getContextPath()%>/resources/${appMode}/partner/js/uploadFile.js?appVer=${appVer}"></script>
<script src="<%=request.getContextPath()%>/resources/${appMode}/commons/js/utility.js?appVer=${appVer}"></script>
<script src="<%=request.getContextPath()%>/resources/${appMode}/tilescommon/js/bootstrap-datetimepicker.js?appVer=${appVer}"></script>
<script src="<%=request.getContextPath()%>/resources/${appMode}/commons/js/liCache.js?appVer=${appVer}"></script>
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
		startDate : "+1d",
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
