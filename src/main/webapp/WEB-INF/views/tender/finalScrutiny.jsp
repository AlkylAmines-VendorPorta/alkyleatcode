<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@taglib uri = "http://www.springframework.org/tags/form" prefix = "sf"%>

<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/${appMode}/tilescommon/css/fioristyle.css?appVer=${appVer}"/>
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/${appMode}/tilescommon/css/borderTab.css?appVer=${appVer}">
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
            
        <div class="full-container">
         <input type="hidden" id="myTenderUrl" class="myTenderUrl" value="${myTenderUrl}" />
            <!-- left-side start-->
            <div class="clearfix"></div>
            <div id="mobile_first_container" class="left-side col-md-3 no-marg">
                <div class="detailsheader">
        	<div class="col-sm-3 text-center brdrgt"><label>Final Scrutiny Report (0)</label></div>
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
							    <label class="btn btn-primary toggleTab" openTab="TenderDetailsTab" id="procurementToggle">
									<input type="radio" name="tenderTypeCodeToggle" value="PT" id="option3">
									<span class="glyphicon glyphicon-ok"></span> Procurement 
                                </label>
                                <label class="btn btn-primary active toggleTab" openTab="TenderDetailsTab" id="worksToggle">
                                     <input type="radio" name="tenderTypeCodeToggle" checked id="woksCheckBoxId" value="WT">
                                      <span class="glyphicon glyphicon-ok"></span> Works
                                </label>
                            </div>
				</c:if>
				 <c:if test="${documentType.equalsIgnoreCase('Auction')}">
				 <input type="hidden" class="documentType" value="${documentType}" />
					<div class="btn-group btnmrg" data-toggle="buttons">
							    <label class="btn btn-primary toggleTab" openTab="TenderDetailsTab" id="forwardToggle">
									<input type="radio" name="tenderTypeCodeToggle" value="FA" id="option3">
									<span class="glyphicon glyphicon-ok"></span> Forward 
                                </label>
                                <label class="btn btn-primary active toggleTab" openTab="TenderDetailsTab" id="reverseToggle">
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
        	<div class="col-sm-9 text-center"><label>Final Scrutiny Details</label></div>
        </div>
                <div class="clearfix"></div>
                <div class="tab-content">
                    <!-- Master tab start-->

                    <div class="tab-pane active in" id="">
                        <div class="card">
                            <div class="posrelative">
                           <div class="demo-section k-content" id="finalScrutinyPageId">
                                    <div id="tabstrip" class="Firsttab">
                                        <ul>
                                            <!-- tabs -->
                                            <li id="tenderDetailTab"  class="k-state-active TenderDetailsTab">${documentType} Details</li>
                                            <li data-parentTab="tenderDetailTab" id="deviationBidderTabId" class="bidderTabs toggleTab" openTab="bidderDetailsTab" onclick="loadBidders(bidderTabId)">Deviation Bid</li>
                                            <li data-parentTab="tenderDetailTab" id="finalConfirmTabId" class="bidderTabs auditorClass" onclick="loadFinalScrutinyResp(this)">Confirm Final Scrutiny</li>
                                             <li data-parentTab="tenderDetailTab" class="bidderTabs auditNotHidClass" onclick="confirmFinalAuditingTab(this)">Confirm Final Auditing</li> 
                                        </ul>

                                        <!--fields of field group 1  -->
                                        <div class="finalScrutinyPage">
                                        <div class="detailscont">
  							 				
    									</div>
                                            <div class="form-group">
                                                <div class="col-sm-12">
                                                    <h4><b>${documentType} Details</b></h4>
                                                    <span class="detspan approvedStatus" id="auditingStatus" ></span>
                                                    <hr>
                                                </div>
                                            </div>
                                            <form id="tahdrDetailForm">
                                             <input type="text" id="tahdrDetailId" style="display: none;">
                                            <div class="form-group">
                                                <div class="col-sm-4">
                                                        <label>${documentType} No:</label>
                                                         <span class="detspan" id="tenderNo"></span>
                                                </div>
                                                <div class="col-sm-4">
                                                        <label>Submission Date:</label>
                                                         <span class="detspan" id="submissionDate"></span>
                                                </div>
                                                <div class="col-sm-4">
                                                        <label>Description:</label>
                                                        <span class="detspan" id="description"></span>
                                                </div>
                                              </div>  
                                              <div class="form-group">
                                                <div class="col-sm-4">
                                                        <label>${documentType} Type:</label>
                                                         <span  class="detspan" id="tenderType"> </span>
                                                </div>
                                                <div class="col-sm-4">
                                                        <label>Department:</label>
                                                        <span class="detspan" id="department"></span>
                                                </div>
                                              </div> 
                                              </form>                                             
                                        </div>
                                        <!--fields of field group 1  -->
                                         <!--fields of field group 2  -->
                                        	<div>
                                        	<div class="detailscont">
  							 				
    									</div>
    									
                                        	<div id="tabstrip2">
                                        <ul>
                                            <!-- tabs -->
                                            <li data-parentTab="tenderDetailTab" class="k-state-active bidderDetailsTab" id="bidderTabId" onclick="loadBidder(this)">Bidders</li><!-- onclick="return submitWithParam('getDeviationBidderListByTahdrDetailId','tahdrDetailId' ,'loadBidderListForScrutiny');" -->
                                            <li data-parentTab="bidderTabId" class="auditorClass" id="itemTabId" onclick="loadItems(this)">Items</li>
                                            <li id="pTSGtpFormTabId" class="deviationTabs pTSGtpFormTabId auditorClass" onclick="loadBidderGtp(this)">Technical Bid Deviation</li>
                                            <li class="deviationTabs auditorClass" onclick="loadTechnicalDoc(this)">Required Technical Docs</li>
                                            <li id="technicalConfirmTabId" class="deviationTabs readonly auditorClass" onclick="loadTechnicalConfirmatory(this)">Technical Confirmation</li>
                                            <li class="commercialTabs" onclick="loadScrutinyPoint(this)">Commercial Bid Deviation</li>
                                            <li class="commercialTabs" onclick="loadCommercialDoc(this)">Required Commercial Docs</li>
                                            <li class="commercialTabs readonly" onclick="loadCommercialConfirmatory(this)">Commercial Confirmation</li>
                                        </ul>
                                        <!--sub of field group 1  -->
                                        	<div>
                                        	<div class="form-group">
                                                <div class="col-sm-12">
                                                    <h4><b>Bidders</b></h4>
                                                    <hr>
                                                </div>
                                            </div>
                                            <form id="bidderForm">
                                            <div class="form-group">
                                            <input type="text" style="display: none;" id="bidderId" name="" >
    									         <div class="col-sm-6">
                                                        <label>Company Name:</label>
                                                        <span class="detspan" id="name" ></span>
                                                    </div>
                                                
                                                <div class="col-sm-6">
                                                        <label>PAN No:</label>
                                                        <span class="detspan" id="panno" ></span>
                                                    </div>
                                                </div>
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
                                         <!--sub of field group 1  -->
                                         
                                         <!--sub of field group 2  -->
                                        	<div class="auditorClass finalScrutinyPage">
                                        	<div class="form-group">
                                                <div class="col-sm-12">
                                                    <h4><b>Items</b></h4>
                                                    <hr>
                                                </div>
                                            </div>
                                            <form id="itemDetailForm">
                                            <div class="form-group">
                                            <div class="col-sm-4">                                                
                                                  <label>Digitally Signed Technical Deviation Bid :</label>
                                                  <a class="col-sm-12 filelabel" data-url="<%=request.getContextPath()%>/download" id="downloadTechScrutinyFile"></a>  
                                                </div>
                                             </div> 
                                            <div class="form-group">
                                            <input style="display: none;" id="itemBidId">
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
                                         <!--sub of field group 2  -->
                                         
                                         <!--sub of field group 3  -->
                                        	<div class="auditorClass finalScrutinyPage">
                                        	<div class="form-group pTSGtpFormTabId">
                                                <div class="col-sm-12">
                                                    <h4><b>Technical Bid Deviation</b></h4>
                                                    <hr>
                                                </div>
                                            </div>
                                            <sf:form id="technicalDeviationForm" modelAttribute="itemScrutinyLine" method="POST">
                                            <div class="form-group">
                                            <input type="text" style="display: none;" id="itemScrutinyLineId" name="itemScrutinyLineId" value="">
                                             <input type="text" style="display: none;" id="itemScrutinyId" name="itemScrutiny.itemScrutinyId" value="">
                                              <input type="text" style="display: none;" id="bidderId" name="itemScrutiny.bidder.bidderId" value="">
                                              <input type="text" style="display: none;" id="bidderGtpId" value="">
                                                <div class="col-sm-4">
                                                		 
                                                        <label>GTP:</label>
                                                        <span class="detspan" id="gtp" ></span>
                                                </div>
                                                <div class="col-sm-4">
                                                        <label>GTP Type:</label>
                                                        <span class="detspan" id="gtpType" ></span>
                                                </div>
                                      
                                              </div>  
                                             
                                               <div class="form-group">
                                                
                                                <div class="col-sm-4">
                                                    <label>Deviation Comment:</label>
                                                    <span class="detspan" id="deviationComment" ></span>
                                                </div>
                                                <div class="col-sm-4 readonly" id="textResponseDivId">
                                                    <div class="styled-input">
                                                    <textarea id="textResponse" name="textResponse" class="textResponse requiredField" maxlength="255" ></textarea>
                                                      <!--  <input type="text" id="textResponse" name="textResponse" class="textResponse"/> -->
                                                        <label>Deviation Response<span class="red">*</span></label>
                                                        <span></span>
                                                    </div>
                                                </div>
                                                
                                                <div class="col-sm-4" id="fileResponseDivId" style="display: none ;">
                                                <div class="input-group" >               
                                                          <input type="file"  id="techFileResponseUploadId" data-id="techFileResponseId" data-name="techFileResponseName" data-anchor="a_techFileResponse" class="form-control uploadFile">
                                                          <input type="hidden" id="techFileResponseId" name="fileResponse.attachmentId" class="form-control fileResponse" /> 
												                <span class="input-group-btn">
												                    <button id="deleteTechnicalAttachmentId" class="btn btn-default techFileResponseId" onclick="return submitWithParam('deleteAttachment','techFileResponseId','fileResponseAttachmentDeleteResp');" disabled="disabled"><i class="fa fa-times"></i></button>
												                </span>
           	                                            </div>
           	                                             <a class="col-sm-12 filelabel" data-url="<%=request.getContextPath()%>/download" id="downloadFileResponse"></a> 
           	                                             <a class="col-sm-12 filelabel" data-url="<%=request.getContextPath()%>/download" id="a_techFileResponse"></a>
           	                                       </div>
           	                                       <div class="form-group">
		                                                <div class="col-sm-4">
		                                                    <label>Deviation Type:</label>
		                                                    <span class="detspan" id="deviationType" ></span>
		                                                </div>
                                                    </div>
                                                <div id="finalScrutinyDivId">
                                                  <div class="form-group">
                                                     <div class="col-sm-6">
			                                                    <label class="radio-inline" style="margin-top:20px;">
			                                                        <input type="radio" name="finalScrutinyStatus" id="approvedId" value="Approved" class="finalStatus" checked>Approved
			                                                     </label>
			                                                     <label class="radio-inline"  style="margin-top:20px;">  
			                                                       	<input type="radio" name="finalScrutinyStatus" id="rejectId" value="Rejected" class="finalStatus">Reject
			                                                    </label>
			                                                    </div>
                                            		</div>
			                                            <div class="form-group">
			                                                <div class="col-sm-4">
			                                                    <div class="styled-input">
			                                                    <textarea id="finalScrutinyComment" name="finalScrutinyComment" class="finalStatus requiredField" maxlength="255" ></textarea>
			                                                        <!-- <input type="text" id="finalScrutinyComment" name="finalScrutinyComment" class="finalStatus requiredField"> -->
			                                                        <label>Final Comments<span class="red">*</span></label>
			                                                        <span></span>
			                                                    </div>
			                                                </div>
			                                            </div>
			                                     </div>
                                              </div> 
                                              <div class="form-group text-center">
                                              	<button class="btn btn-info actionbtn save" onclick="return submitIt('technicalDeviationForm','saveTechnicalDeviationResponse','bidderDeviationResponseSubmitResp');">Save</button>
                                              	
                                              </div>
                                              </sf:form>
                                            </div>
                                         <!--sub of field group 3  -->
                                         <div class="auditorClass finalScrutinyPage">
                                        	<div class="form-group">
                                                <div class="col-sm-12">
                                                    <h4><b>Required Technical Docs</b></h4>
                                                    <hr>
                                                </div>
                                            </div>
                                            <sf:form id="technicalDocumentDeviationForm" modelAttribute="itemScrutinyLine" method="POST">
                                            <div class="form-group">
                                             <input type="text" style="display: none;" id="itemScrutinyLineId" name="itemScrutinyLineId" value="">
                                             <input type="text" style="display: none;" id="itemScrutinyId" name="itemScrutiny.itemScrutinyId" value="">
                                              <input type="text" style="display: none;" id="bidderId" name="itemScrutiny.bidder.bidderId" value="">
                                               <input type="text" style="display: none;" id="bidderSectionDocId" value="">
                                                <div class="col-sm-4">
                                                        <label>Uploaded Documents:</label>
                                                    <span class="detspan" id="uploadedDoc" ></span>
                                                </div>                                                
                                              </div>  
                                              
                                               <div class="form-group">
                                                       <div class="col-sm-4">
                                                    <label>Deviation Comment:</label>
                                                    <span class="detspan" id="deviationComment" ></span>
                                                </div>
                                                <div class="col-sm-4 readonly" id="textResponseDivId">
                                                    <div class="styled-input">
                                                    <textarea id="textResponse" name="textResponse" class="textResponse requiredField" maxlength="255" ></textarea>
                                                       <!-- <input type="text" id="textResponse" name="textResponse" class="textResponse"/> -->
                                                        <label>Deviation Response<span class="red">*</span></label>
                                                        <span></span>
                                                    </div>
                                                </div>
                                                <div class="col-sm-4" id="fileResponseDivId" style="display: none ;">
                                                <div class="input-group" >               
                                                          <input type="file"  id="docFileResponseUploadId" data-id="docFileResponseId" data-name="docFileResponseName" data-anchor="a_docFileResponse" class="form-control uploadFile">
                                                          <input type="hidden" id="docFileResponseId" name="fileResponse.attachmentId" class="form-control fileResponse" /> 
												                <span class="input-group-btn">
												                    <button  id="deleteTechDocAttachmentId" class="btn btn-default docFileResponseId" onclick="return submitWithParam('deleteAttachment','docFileResponseId','fileResponseAttachmentDeleteResp');" disabled="disabled"><i class="fa fa-times"></i></button>
												                </span>
           	                                            </div>
           	                                            <a class="col-sm-12 filelabel" data-url="<%=request.getContextPath()%>/download" id="downloadFileResponse"></a> 
           	                                             <a class="col-sm-12 filelabel" data-url="<%=request.getContextPath()%>/download" id="a_docFileResponse"></a>
           	                                       </div>
           	                                        <div class="form-group">
		                                                <div class="col-sm-4">
		                                                    <label>Deviation Type:</label>
		                                                    <span class="detspan" id="deviationType" ></span>
		                                                </div>
                                                    </div>
                                                <div id="finalScrutinyDivId">
                                                  <div class="form-group">
                                                     <div class="col-sm-6">
			                                                    <label class="radio-inline" style="margin-top:20px;">
			                                                        <input type="radio" name="finalScrutinyStatus" id="approvedId" value="Approved" class="finalStatus" checked>Approved
			                                                     </label>
			                                                     <label class="radio-inline"  style="margin-top:20px;">  
			                                                       	<input type="radio" name="finalScrutinyStatus" id="rejectId" value="Rejected" class="finalStatus">Reject
			                                                    </label>
			                                                    </div>
                                            		</div>
			                                            <div class="form-group">
			                                                <div class="col-sm-4">
			                                                    <div class="styled-input">
			                                                    <textarea id="finalScrutinyComment" name="finalScrutinyComment" class="finalStatus requiredField" maxlength="255" ></textarea>
			                                                        <!-- <input type="text" id="finalScrutinyComment" name="finalScrutinyComment" class="finalStatus requiredField"> -->
			                                                        <label>Final Comments<span class="red">*</span></label>
			                                                        <span></span>
			                                                    </div>
			                                                </div>
			                                            </div>
			                                     </div>
                                              </div>
                                            <div class="form-group text-center">
                                              	<button class="btn btn-info save" onclick="return submitIt('technicalDocumentDeviationForm','saveTechnicalDocumentDeviationResponse','bidderDeviationResponseSubmitResp');">Save</button>
                                              	
                                              </div>
                                              </sf:form>
                                            </div>
                                         <!--sub of field group 5  -->
                                          <!--sub of field group 6  -->
                                        	<div class="auditorClass finalScrutinyPage">
                                        	<div class="form-group">
                                                <div class="col-sm-12">
                                                    <h4><b>Final Technical Confirmation</b></h4>
                                                    <hr>
                                                </div>
                                            </div>
                                            <div class="form-group" id="techConfirmMsgDivId" style="display: none;">
                                                    <div class="col-sm-6">
                                                       <label>Technical Confirmation Status:</label>
                                                    <span class="detspan" id="technicalConfirmStatus" ></span>
                                                </div>
                                                </div>
                                           <div class="auditorClass">
                                            <div class="col-sm-12">
                                             <sf:form id="gennerateFinalTechnicalScrutinyDoc">
                                             			<input type="text"  style="display: none;" id="scrutinyFileId" name="scrutinyFileId" value="">
                                                        <input type="text"  style="display: none;" id="bidderId" name="bidder.bidderId" value="">
                                                         <input type="text"  style="display: none;" id="itemScrutinyId" name="itemScrutiny.itemScrutinyId" value="">
                                                         <input type="text"  style="display: none;" id="itemBidId" name="itemBid.itemBidId" value="">
                                            			 <input type="text"  style="display: none;" id="scrytinyLevel" name="scrutinyLevel" value="FINAL">
                                            			 <input type="text"  style="display: none;" id="scrutinyType" name="scrutinyType" value="TECHSCR">
						                      </sf:form>
                                                    	
                                                    </div> 
                                                    
                                           </div>
                                            <sf:form id="confirmationTechnicalDevitionForm" modelAttribute="itemScrutiny" method="POST">
                                            <div class="form-group">
                                                    <input type="text" style="display: none;" id="itemScrutinyId" name="itemScrutinyId" value="">
                                                    <input type="text" style="display: none;" id="bidderId" name="bidder.bidderId" value="">
                                                    <input type="text" style="display: none;" id="itemBidId" name="itemBid.itemBidId" value="">
                                                <%-- <div class="col-sm-4">                                                
                                                  <label>Digital Sign Deviation bid :</label>
                                                  <a class="col-sm-12 filelabel" data-url="<%=request.getContextPath()%>/download" id="downloadTechFileResponse"></a>  
                                                </div>  --%>
                                                <!-- <div class="col-sm-4">
                                                <div class="styled-input">
                                                	<button class="btn btn-default">Download Digital Sign Bid PDF</button>
                                                	</div>
                                                </div> -->                                               
                                              </div>  
                                              
                                               <div class="form-group">
                                                <div class="col-md-12">
									                    <label class="checkbox-inline" id="confirmCheckBox">
									                      <input type="checkbox" name="bidderDeviationStatus" id="confirmCheckBox" value="N">
									                      I Submit Deviation for the Selected Item </label>
									                  </div>
                                              </div>
                                              <div id="finalScrutinyDivId" style="display: none;">
                                                  <div class="form-group">
                                                     <div class="col-sm-6">
			                                                    <label class="radio-inline" style="margin-top:20px;">
			                                                        <input type="radio" class="finalApproval" name="finalScrutinyStatus" id="approvedId" value="Approved" class="finalStatus" checked>Approved
			                                                     </label>
			                                                     <label class="radio-inline"  style="margin-top:20px;">  
			                                                       	<input type="radio" class="finalApproval" name="finalScrutinyStatus" id="rejectedId" value="Rejected" class="finalStatus">Reject
			                                                    </label>
			                                                    </div>
                                            		</div>
			                                            <div class="form-group">
			                                                <div class="col-sm-4">
			                                                    <div class="styled-input">
			                                                    <textarea id="finalScrutinyComment" name="finalScrutinyComment" class="finalStatus requiredField" maxlength="255" ></textarea>
			                                                       <!--  <input type="text" id="finalScrutinyComment" name="finalScrutinyComment" class="finalStatus finalApproval requiredField"> -->
			                                                        <label>Final Comments<span class="red">*</span></label>
			                                                        <span></span>
			                                                    </div>
			                                                </div>
			                                            </div>
			                                     </div>
                                            <div class="form-group text-center">
                                              	<button class="btn btn-info save" onclick="return submitIt('confirmationTechnicalDevitionForm','confirmTechnicalDeviationByBidder','confirmDeviationSubmitResp');">Save Scrutiny</button>
                                              </div>
                                              </sf:form>
                                             <div class="auditorClass">
           	                                     <div class="col-sm-12">
                                                    	<button onclick="uploadFinalTechnicalScrutinyPdf(event)" class="btn btn-default">Generate Final Technical Scrutiny PDF</button>
                                                    	<!-- <button class="btn btn-default">Goto Main Page</button> -->
                                                    </div> 
                                                    <div class="clearfix"></div>
                                                    <br/>
           	                                    <label class="col-sm-12">Uploaded File:
           	                                             <a class="filelabel" data-url="<%=request.getContextPath()%>/download" id="a_finalTechFileResponse"></a>
           	                                             </label> </div>
                                            </div>
                                         <!--sub of field group 4  -->
                                        	<div class="finalScrutinyPage">
                                        	<div class="form-group">
                                                <div class="col-sm-12">
                                                    <h4><b>Commercial Bid Deviation</b></h4>
                                                    <hr>
                                                </div>
                                            </div>
                                          <sf:form id="commercialDeviationForm" modelAttribute="itemScrutinyLine" method="POST">
                                             <div class="form-group">
                                             <div class="col-sm-4">                                                
                                                  <label>Digitally Signed Commercial Deviation Bid :</label>
                                                  <a class="col-sm-12 filelabel" data-url="<%=request.getContextPath()%>/download" id="downloadCommScrutinyFile"></a>  
                                                </div> 
                                              </div> 
                                            <div class="form-group">
                                            <input type="text" style="display: none;" id="itemScrutinyLineId" name="itemScrutinyLineId" value="">
                                            <input type="text" style="display: none;" id="itemScrutinyId" name="itemScrutiny.itemScrutinyId" value="">
                                             <input type="text" style="display: none;" id="bidderId" name="itemScrutiny.bidder.bidderId" value="">
                                             <input type="text" style="display: none;" id="auditPrevStatus" name="finalAuditorStatus" value="">
                                             <input type="text" style="display: none;" id="auditPrevComment" name="finalAuditorComment" value="">
                                              <div class="form-group" id="auditorInputDivId">
	                                              <div class="col-sm-4">
	                                                    
	                                                        <label>Auditor Final Status:</label>
                                                            <span class="detspan" id="auditorPrevStatus" ></span>
	                                                   
	                                               </div>   
	                                               <div class="col-sm-4">
	                                                    
	                                                    <label>Auditor Final Comment:</label>
                                                        <span class="detspan" id="auditorPrevComment" ></span>
	                                                
                                                  </div> 
                                                </div>
                                                <div class="col-sm-6">
                                                        <label>Scrutiny Point:</label>
                                                        <span class="detspan" id="scrutinyPointName" ></span>
                                                </div>
                                                <div class="col-sm-6">
                                                        <label>Scrutiny Point Description:</label>
                                                        <span class="detspan" id="scrutinyPointDesp" ></span>
                                                </div>
                                              </div>
                                               <div class="form-group">
                                              <div class="col-sm-6">
                                                    <label>Deviation Comment:</label>
                                                    <span class="detspan" id="deviationComment" ></span>
                                                  </div>
                                               
                                              
                                              <div class="col-sm-6 readonly" id="textResponseDivId">
                                                    <div class="styled-input">
                                                    <textarea id=textResponse name="textResponse" class="textResponse requiredField" maxlength="255" ></textarea>
                                                      <!--  <input type="text" id=textResponse name="textResponse" class="textResponse"/> -->
                                                        <label>Deviation Response<span class="red">*</span></label>
                                                        <span></span>
                                                    </div>
                                                </div>
                                                </div>
                                                 <div class="form-group">
		                                                <div class="col-sm-4">
		                                                    <label>Deviation Type:</label>
		                                                    <span class="detspan" id="deviationType" ></span>
		                                                </div>
                                                    </div>
                                                <div class="col-sm-4" id="fileResponseDivId" style="display: none ;">
                                                <div class="input-group" >               
                                                          <input type="file"  id="commFileResponseUploadId" data-id="commFileResponseId" data-name="commFileResponseName" data-anchor="a_commFileResponse" class="form-control uploadFile">
                                                          <input type="hidden" id="commFileResponseId" name="fileResponse.attachmentId" class="form-control fileResponse" /> 
												                <span class="input-group-btn">
												                    <button id="deletecommercialAttachmentId" class="btn btn-default commfileResponseId" onclick="return submitWithParam('deleteAttachment','commFileResponseId','fileResponseAttachmentDeleteResp');" disabled="disabled"><i class="fa fa-times"></i></button>
												                </span>
           	                                            </div>
           	                                            <a class="col-sm-12 filelabel" data-url="<%=request.getContextPath()%>/download" id="downloadFileResponse"></a> 
           	                                             <a class="col-sm-12 filelabel" data-url="<%=request.getContextPath()%>/download" id="a_commFileResponse"></a>
           	                                       </div>
                                             
                                                <div id="finalScrutinyDivId">
                                                  <div class="form-group">
                                                     <div class="col-sm-6">
			                                                    <label class="radio-inline" style="margin-top:20px;">
			                                                        <input type="radio" name="finalScrutinyStatus" id="approvedId" value="Approved" class="finalStatus" checked>Approved
			                                                     </label>
			                                                     <label class="radio-inline"  style="margin-top:20px;">  
			                                                       	<input type="radio" name="finalScrutinyStatus" id="rejectedId" value="Rejected" class="finalStatus">Reject
			                                                    </label>
			                                                    </div>
                                            		</div>
			                                            <div class="form-group">
			                                                <div class="col-sm-4">
			                                                    <div class="styled-input">
			                                                    <textarea id="finalScrutinyComment" name="finalScrutinyComment" class="finalStatus requiredField" maxlength="255" ></textarea>
			                                                        <!-- <input type="text" id="finalScrutinyComment" name="finalScrutinyComment" class="finalStatus requiredField"> -->
			                                                        <label>Final Comments<span class="red">*</span></label>
			                                                        <span></span>
			                                                    </div>
			                                                </div>
			                                            </div>
			                                     </div>
                                              <div class="form-group text-center">
                                              	<button class="btn btn-info save" onclick="return submitIt('commercialDeviationForm','saveCommercialDeviationResponse','bidderDeviationResponseSubmitResp');">Save</button>
                                              	
                                              </div>
                                              </sf:form>
                                              <sf:form  id="auditorForm" modelAttribute="auditorData" method="POST" class="">  
                                                <div class="form-group">
	                                                <div class="col-sm-12">
	                                                    <hr>
	                                                    <h4><b>Auditor Final Comment</b></h4>
	                                                    <hr>
	                                                </div>
                                            	</div>
                                            <div class="form-group">
                                                <input type="text" style="display: none ;" id="itemScrutinyLineId" name="itemScrutinyLineId" value="">
                                                <div class="col-sm-4">
                                                    <div class="styled-input">
                                                                <select id="auditTypeCode" name="finalAuditorStatus" class="dropDown">
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
                                                    <textarea id="comment" name="finalAuditorComment" class="requiredField" maxlength="255" ></textarea>
                                                        <!-- <input type="text" id="comment" name="finalAuditorComment" class="requiredField"> -->
                                                        <label id="labelId">Comment</label>
                                                        <span></span>
                                                    </div>
                                               </div>                                               
                                            </div>
                                            
                                               <div class="form-group">
                                                <div class="col-sm-12 text-center">
                                                    <button class="btn btn-default save" onclick="return submitIt('auditorForm','saveAuditorFinalComment','saveFinalAuditorCommentResp');">Save</button>
                                                   <!--  <button class="btn btn-default cancel">Cancel</button> -->
                                                </div>
                                              </div>
                                              </sf:form>
                                            </div>
                                         <!--sub of field group 4  -->
                                         
                                         <!--sub of field group 5  -->
                                        	<div class="finalScrutinyPage">
                                        	<div class="form-group">
                                                <div class="col-sm-12">
                                                    <h4><b>Required Commercial Docs</b></h4>
                                                    <hr>
                                                </div>
                                            </div>
                                            <sf:form id="commercialDocumentDeviationForm" modelAttribute="itemScrutinyLine" method="POST">
                                            <div class="form-group">
                                             <input type="text" style="display: none;" id="itemScrutinyLineId" name="itemScrutinyLineId" value="">
                                             <input type="text" style="display: none;" id="itemScrutinyId" name="itemScrutiny.itemScrutinyId" value="">
                                             <input type="text" style="display: none;" id="bidderId" name="itemScrutiny.bidder.bidderId" value="">
                                             <input type="text" style="display: none;" id="bidderSectionDocId" value="">
                                             <input type="text" style="display: none;" id="auditPrevStatus" name="finalAuditorStatus" value="">
                                             <input type="text" style="display: none;" id="auditPrevComment" name="finalAuditorComment" value="">
                                               
                                                <div class="form-group" id="auditorInputDivId">
	                                              <div class="col-sm-4">
	                                                    
	                                                        <label>Auditor Final Status:</label>
                                                            <span class="detspan" id="auditorDocPrevStatus" ></span>
	                                                   
	                                               </div>   
	                                               <div class="col-sm-4">
	                                                    
	                                                    <label>Auditor Final Comment:</label>
                                                        <span class="detspan" id="auditorDocPrevComment" ></span>
	                                                
                                                  </div> 
                                                </div>
                                                
                                                <div class="col-sm-4">
                                                        <label>Uploaded Documents:</label>
                                                    <span class="detspan" id="uploadedDoc" ></span>
                                                </div>                                                
                                              </div>  
                                              
                                               <div class="form-group">
                                                       <div class="col-sm-4">
                                                    <label>Deviation Comment:</label>
                                                    <span class="detspan" id="deviationComment" ></span>
                                                </div>
                                                <div class="col-sm-4 readonly" id="textResponseDivId">
                                                    <div class="styled-input">
                                                    <textarea id="textResponse" name="textResponse" class="textResponse requiredField" maxlength="255" ></textarea>
                                                       <!-- <input type="text" id="textResponse" name="textResponse" class="textResponse"/> -->
                                                        <label>Deviation Response<span class="red">*</span></label>
                                                        <span></span>
                                                    </div>
                                                </div>
                                                 <div class="form-group">
		                                                <div class="col-sm-4">
		                                                    <label>Deviation Type:</label>
		                                                    <span class="detspan" id="deviationType" ></span>
		                                                </div>
                                                    </div>
                                                <div class="col-sm-4" id="fileResponseDivId" style="display: none ;">
                                                <div class="input-group" >               
                                                          <input type="file"  id="commDocFileResponseUploadId" data-id="commDocFileResponseId" data-name="commDocFileResponseName" data-anchor="a_commDocFileResponse" class="form-control uploadFile">
                                                          <input type="hidden" id="commDocFileResponseId" name="fileResponse.attachmentId" class="form-control fileResponse" /> 
												                <span class="input-group-btn">
												                    <button id="deleteCommDocAttachmentId" class="btn btn-default commDocFileResponseId" onclick="return submitWithParam('deleteAttachment','docFileResponseId','fileResponseAttachmentDeleteResp');" disabled="disabled"><i class="fa fa-times"></i></button>
												                </span>
           	                                            </div>
           	                                            <a class="col-sm-12 filelabel" data-url="<%=request.getContextPath()%>/download" id="downloadFileResponse"></a> 
           	                                             <a class="col-sm-12 filelabel" data-url="<%=request.getContextPath()%>/download" id="a_commDocFileResponse"></a>
           	                                       </div>
           	                                       
                                                <div id="finalScrutinyDivId">
                                                  <div class="form-group">
                                                     <div class="col-sm-6">
			                                                    <label class="radio-inline" style="margin-top:20px;">
			                                                        <input type="radio" name="finalScrutinyStatus" id="approvedId" value="Approved" class="finalStatus" checked>Approved
			                                                     </label>
			                                                     <label class="radio-inline"  style="margin-top:20px;">  
			                                                       	<input type="radio" name="finalScrutinyStatus" id="rejectedId" value="Rejected" class="finalStatus">Reject
			                                                    </label>
			                                                    </div>
                                            		</div>
			                                            <div class="form-group">
			                                                <div class="col-sm-4">
			                                                    <div class="styled-input">
			                                                    <textarea id="finalScrutinyComment" name="finalScrutinyComment" class="finalStatus requiredField" maxlength="255" ></textarea>
			                                                        <!-- <input type="text" id="finalScrutinyComment" name="finalScrutinyComment" class="finalStatus requiredField"> -->
			                                                        <label>Final Comments<span class="red">*</span></label>
			                                                        <span></span>
			                                                    </div>
			                                                </div>
			                                            </div>
			                                     </div>
                                              </div>
                                            <div class="form-group text-center">
                                              	<button class="btn btn-info save" onclick="return submitIt('commercialDocumentDeviationForm','saveCommercialDocumentDeviationResponse','bidderDeviationResponseSubmitResp');">Save</button>
                                              	
                                              </div>
                                              </sf:form>
                                              <sf:form  id="auditorDocForm" modelAttribute="auditorData" method="POST" class="">  
                                                <div class="form-group">
	                                                <div class="col-sm-12">
	                                                    <hr>
	                                                    <h4><b>Auditor Final Comment</b></h4>
	                                                    <hr>
	                                                </div>
                                            	</div>
                                            <div class="form-group">
                                                <input type="text" style="display: none ;" class="itemScrutinyLineId" id="itemScrutinyLineId" name="itemScrutinyLineId" value="">
                                                <div class="col-sm-4">
                                                    <div class="styled-input">
                                                                <select id="auditTypeCode" name="finalAuditorStatus" class="dropDown">
                                                                <option value="">Select</option>
                                                                <option value="APPROVED">Approved</option>
                                                                 <option value="CLARIFICATION">Clarification</option>
                                                                </select>
                                                                <label>Audit Final Status<span class="red">*</span></label>
                                                                <span></span>
                                                            </div>
                                                </div>                                    
                                            
                                            
                                                <div class="col-sm-4">
                                                    <div class="styled-input">
                                                    <textarea id="comment" name="finalAuditorComment" class="requiredField" maxlength="255" ></textarea>
                                                        <!-- <input type="text" id="comment" name="finalAuditorComment" class="requiredField"> -->
                                                        <label id="labelId">Comment</label>
                                                        <span></span>
                                                    </div>
                                               </div>                                               
                                            </div>
                                            
                                               <div class="form-group">
                                                <div class="col-sm-12 text-center">
                                                    <button class="btn btn-default save" onclick="return submitIt('auditorDocForm','saveAuditorFinalComment','saveFinalDocAuditorCommentResp');">Save</button>
                                                   <!--  <button class="btn btn-default cancel">Cancel</button> -->
                                                </div>
                                              </div>
                                              </sf:form>
                                            </div>
                                         <!--sub of field group 5  -->
                                          <!--sub of field group 6  -->
                                        	<div class="finalScrutinyPage">
                                        	<div class="form-group">
                                                <div class="col-sm-12">
                                                    <h4><b>Final Commercial Confirmation</b></h4>
                                                    <hr>
                                                </div>
                                            </div>
                                            <div class="form-group" id="commConfirmMsgDivId" style="display: none;">
                                                    <div class="col-sm-6">
                                                       <label>Commercial Confirmation Status:</label>
                                                    <span class="detspan" id="commercialConfirmStatus" ></span>
                                                </div>
                                                </div>
                                           <div class="auditorClass">
                                            <div class="col-sm-12">
                                            <sf:form id="gennerateFinalCommercialScrutinyDoc">
                                             			<input type="text"  style="display: none;" id="scrutinyFileId" name="scrutinyFileId" value="">  
                                                         <input type="text"  style="display: none;" id="bidderId" name="bidder.bidderId" value="">
                                                         <input type="text"  style="display: none;" id="itemScrutinyId" name="itemScrutiny.itemScrutinyId" value="">
                                            			 <input type="text"  style="display: none;" id="scrytinyLevel" name="scrutinyLevel" value="FINAL">
                                            			 <input type="text"  style="display: none;" id="scrutinyType" name="scrutinyType" value="COMMSCR">
						                      </sf:form>
                                                    	
                                                    </div> 
                                            </div>
                                            <sf:form id="confirmationCommercialDevitionForm" modelAttribute="itemScrutiny" method="POST">
                                            <div class="form-group">
                                                    <input type="text" style="display: none;" id="itemScrutinyId" name="itemScrutinyId" value="">
                                                    <input type="text" style="display: none;" id="bidderId" name="bidder.bidderId" value="">
                                                    <input type="text" style="display: none;" id="auditPrevStatus" name="finalAuditorStatus" value="">
                                                    <input type="text" style="display: none;" id="auditPrevComment" name="finalAuditorComment" value="">
                                                    
                                                    <div class="form-group" id="auditorInputDivId">
	                                              <div class="col-sm-4">
	                                                    
	                                                        <label>Auditor Final Status:</label>
                                                            <span class="detspan" id="auditorConfirmPrevStatus" ></span>
	                                                   
	                                               </div>   
	                                               <div class="col-sm-4">
	                                                    
	                                                    <label>Auditor Final Comment:</label>
                                                        <span class="detspan" id="auditorConfirmPrevComment" ></span>
	                                                
                                                  </div> 
                                                </div>
                                                
                                                <%-- <div class="col-sm-4">                                                
                                                  <label>Digital Sign Deviation bid :</label>
                                                  <a class="col-sm-12 filelabel" data-url="<%=request.getContextPath()%>/download" id="downloadComFileResponse"></a> 
                                                </div> --%>                                         
                                              </div>  
                                              
                                               <div class="form-group">
                                                <div class="col-md-12" id="confirmCheckBox">
									                    <label class="checkbox-inline">
									                      <input type="checkbox" name="bidderDeviationStatus" value="N">
									                      I Submit Deviation for the Selected Item </label>
									                  </div>
                                              </div>
                                              <div id="finalScrutinyDivId" style="display: none;">
                                                  <div class="form-group">
                                                     <div class="col-sm-6">
			                                                    <label class="radio-inline" style="margin-top:20px;">
			                                                        <input type="radio" name="finalScrutinyStatus" value="Approved" id="approvedId" class="finalStatus" checked>Approved
			                                                     </label>
			                                                     <label class="radio-inline"  style="margin-top:20px;">  
			                                                       	<input type="radio" name="finalScrutinyStatus" value="Rejected" id="rejectedId" class="finalStatus">Reject
			                                                    </label>
			                                                    </div>
                                            		</div>
			                                            <div class="form-group">
			                                                <div class="col-sm-4">
			                                                    <div class="styled-input">
			                                                    <textarea id="finalScrutinyComment" name="finalScrutinyComment" class="finalStatus requiredField" maxlength="255" ></textarea>
			                                                       <!--  <input type="text" id="finalScrutinyComment" name="finalScrutinyComment" class="finalStatus requiredField"> -->
			                                                        <label>Final Comments<span class="red">*</span></label>
			                                                        <span></span>
			                                                    </div>
			                                                </div>
			                                            </div>
			                                     </div>
                                            <div class="form-group text-center">
                                              	<button class="btn btn-info save" onclick="return submitIt('confirmationCommercialDevitionForm','confirmCommercialDeviationByBidder','confirmDeviationSubmitResp');">Save Scrutiny</button>
                                              </div>
                                              </sf:form>
                                              <div class="auditorClass">
           	                                    <div class="col-sm-12">
                                                    	<button onclick="uploadFinalCommercialScrutinyPdf(event)" class="btn btn-default">Download Final Commercial Scrutiny PDF</button>
                                                    	<!-- <button class="btn btn-default">Goto Main Page</button> -->
                                                    </div> 
                                                    <div class="clearfix"></div>
                                                    <br/>
           	                                    <label class="col-sm-12">Uploaded File:
           	                                             <a class="filelabel" data-url="<%=request.getContextPath()%>/download" id="a_finalCommFileResponse"></a>
           	                                             </label></div>
           	                                             
                                              <sf:form  id="auditorConfirmForm" modelAttribute="auditorData" method="POST" class="">  
                                                <div class="form-group">
	                                                <div class="col-sm-12">
	                                                    <hr>
	                                                    <h4><b>Auditor Final Comment</b></h4>
	                                                    <hr>
	                                                </div>
                                            	</div>
                                            <div class="form-group">
                                                <input type="text" style="display: none ;" id="itemScrutinyId" name="itemScrutinyId" value="">
                                                  <input type="text" style="display: none ;" id="bidderId" name="bidder.bidderId" value="">
                                                  <input type="text" style="display: none ;" id="" name="isFinalAudited" value="Y">
                                                <div class="col-sm-4">
                                                    <div class="styled-input">
                                                                <select id="auditTypeCode" name="finalAuditorStatus" class="dropDown">
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
                                                    <textarea id="comment" name="finalAuditorComment" class="requiredField" maxlength="255" ></textarea>
                                                        <!-- <input type="text" id="comment" name="finalAuditorComment" class="requiredField" > -->
                                                        <label id="labelId">Comment</label>
                                                        <span></span>
                                                    </div>
                                               </div>                                               
                                            </div>
                                            
                                               <div class="form-group">
                                                <div class="col-sm-12 text-center">
                                                    <button class="btn btn-default save" onclick="return submitIt('auditorConfirmForm','confirmAuditorFinalComment','saveFinalConfirmAuditorCommentResp');">Save</button>
                                                   <!--  <button class="btn btn-default cancel">Cancel</button> -->
                                                </div>
                                              </div>
                                              </sf:form>
                                            </div>
                                         <!--sub of field group 6  -->
                                            </div>
                                         <!--fields of field group 2  -->
                                       
                                    </div>
                                         <div class="finalScrutinyPage">
                                        <div class="detailscont">
  							 				
    									</div>
                                            <div class="form-group">
                                                <div class="col-sm-12">
                                                    <h4 class="col-sm-6"><b>Confirm Final Scrutiny</b></h4>
                                                     <div class="col-sm-6 text-right" style="margin-top:5px;">
	                                                <button type="button" id="intimidateAuditorBtnId" class="btn btn-info bluebutton" onclick="intimidateAuditor(event)">
	                                                    <i class="fa fa-envelope" style="font-size:16px;" aria-hidden="true"></i>  Notify Auditor</button>
	                                                </div>
                                                </div>
                                                    <hr>
                                            </div>
                                             <sf:form id="confirmationFinalScrutinyForm" modelAttribute="tahdr" method="POST">
                                            <div class="form-group">
                                                    <input type="text" style="display: none;" id="tahdrId" name="tahdrId" value="">
                                                     <input type="text" style="display: none;" id="tahdrName" name="tahdrCode" value="">
                                              </div>  
                                              
                                               <div class="form-group">
                                                <div class="col-md-12">
									                    <label class="checkbox-inline" id="confirmCheckBox">
									                      <input type="checkbox" id="confirmCheckBox" name="tahdrStatusCode" value="SCRDONE" >
									                      Confirm Final Deviation for the Selected ${documentType} </label>
									                  </div>
									                   <!-- <input type="text" style="display: none ;" name="tahdrStatusCode" id="tahdrStatusId" checked="checked" value="SCRDONE"> -->
                                              </div>
                                            <div class="form-group text-center">
                                              	<button class="btn btn-info save" id="finalDeviationSaveBtnId" onclick="return submitIt('confirmationFinalScrutinyForm','confirmFinalScrutiny','confirmFinalScrutinyResp');">Submit</button>
                                              </div>
                                              </sf:form>
                                                                    
                                        </div>
                                       <div class="auditNotHidClass">
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
                                                    <input type="checkbox" value="Y" checked> Auditing is Done.All Bidder Commercial Final Scrutiny is Audited.</label>
                                            </div>
						            </div>
                                            <div class="form-group">
                                            	<div class="col-sm-12 text-center">
                                            	 <button type="button" id="confirmAuditingBtnId" class="btn btn-info save" onclick="intimidateScrutinyEngr(event)">Notify Scrutiny Engineers</button>
                                            	</div>
                                            </div>
                                            </sf:form>
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
            </div>
<script src="<%=request.getContextPath()%>/resources/${appMode}/transaction/js/finalScrutiny.js?appVer=${appVer}"></script>
<script src="<%=request.getContextPath()%>/resources/${appMode}/transaction/js/finalScrutinyExtension.js?appVer=${appVer}"></script>
<script src="<%=request.getContextPath()%>/resources/${appMode}/transaction/js/finalScrutinyFile.js?appVer=${appVer}"></script>
<script src="<%=request.getContextPath()%>/resources/${appMode}/commons/js/formFields.js?appVer=${appVer}"></script>
<script src="<%=request.getContextPath()%>/resources/${appMode}/partner/js/uploadFile.js?appVer=${appVer}"></script>
<script src="<%=request.getContextPath()%>/resources/${appMode}/commons/js/utility.js?appVer=${appVer}"></script>
<script src="<%=request.getContextPath()%>/resources/${appMode}/commons/js/liCache.js?appVer=${appVer}"></script>