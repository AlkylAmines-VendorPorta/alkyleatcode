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
                    $('#leftPaneData').paginathing({perPage: 6});
                });
                
            </script>
            
        <div class="full-container">
        <input type="hidden" id="myTenderUrl" class="myTenderUrl" value="${myTenderUrl}" />
            <!-- left-side start-->
            <div class="clearfix"></div>
            <div id="mobile_first_container" class="left-side col-md-3 no-marg">
                <div class="detailsheader">
        	<div class="col-sm-3 text-center brdrgt"><label>Deviation Bid Report (0)</label></div>
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
                                     <input type="radio" name="tenderTypeCodeToggle" value="WT" checked id="option3">
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
                                     <input type="radio" name="tenderTypeCodeToggle" value="PT" checked id="option3">
                                      <span class="glyphicon glyphicon-ok"></span> Reserve
                                </label>
                            </div>
				</c:if>
                <ul id="leftPaneData" class="nav nav-tabs tabs-left leftPaneData">
                    
                </ul>
                
                <div class="clearfix"></div>
            </div>
            <!-- left-side end-->

            <!-- right-side start-->
            <div id="mobile_second_container" class="right-side col-md-9 no-marg">
            <div class="detailsheader toptabbrd">
        	<div class="col-sm-9 text-center"><label>Deviation Bid Details</label></div>
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
                                            <li id="tenderDetailTab" data-parentTab="" class="k-state-active TenderDetailsTab">${documentType} Details</li>
                                            <li data-parentTab="tenderDetailTab" id="deviationBidderTabId" class="bidderTabs toggleTab" openTab="bidderDetailsTab" onclick="loadBidders(bidderTabId)">Deviation Bid</li>
                                            <!-- <li data-parentTab="tenderDetailTab" id="deviationBidderTabId" class="bidderTabs" >Confirm Deviation Bid Submitted</li> -->
                                        </ul>

                                        <!--fields of field group 1  -->
                                        <div>
                                        <div class="detailscont">
  							 				
    									</div>
                                            <div class="form-group">
                                                <div class="col-sm-12">
                                                    <h4><b>${documentType} Details</b></h4>
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
                                            <li data-parentTab="tenderDetailTab" id="bidderTabId" class="k-state-active bidderDetailsTab" onclick="loadBidder(this)">Bidders</li>
                                            <li data-parentTab="bidderTabId" class="" id="itemTabId" onclick="loadItemList(this)">Items</li>
                                            <li data-parentTab="itemTabId" class="deviationTabs bidderGtpTab pTSGtpFormTabId" onclick="loadBidderGtpList(this)">Technical Bid Deviation</li>
                                            <li data-parentTab="itemTabId" class="deviationTabs techDocTab" onclick="loadTSDocumentDetail(this)">Required Technical Docs</li>
                                            <li data-parentTab="itemTabId" id="technicalConfirmTabId" class="readonly" onclick="loadTSConfirmResp(this)">Technical Confirmation</li>
                                            <li data-parentTab="bidderTabId" class="" onclick="loadScrutinyPointList(this)">Commercial Bid Deviation</li>
                                            <li data-parentTab="bidderTabId" class="" onclick="loadCSDocumentDetail(this)">Required Commercial Docs</li>
                                            <li data-parentTab="bidderTabId" id="" class="ConfirmTabs readonly" onclick="loadCSConfirmResp(this)">Commercial Confirmation</li>
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
    									         <div class="col-sm-4">
                                                        <label>Company Name:</label>
                                                        <span class="detspan" id="name" ></span>
                                                    </div>
                                                
                                                <div class="col-sm-4">
                                                        <label>PAN No:</label>
                                                        <span class="detspan" id="panno" ></span>
                                                    </div>
                                                </div>
                                                <div class="col-sm-4">
                                                        <label>GSTN No:</label>
                                                        <span class="detspan" id="gstNo" ></span>
                                                    </div> 
                                                <div class="col-sm-4">
                                                        <label>Company Registration No:</label>
                                                        <span class="detspan" id="crnNumber" ></span>
                                                        
                                                    </div>
                                              </form>
                                            </div>
                                         <!--sub of field group 1  -->
                                         
                                         <!--sub of field group 2  -->
                                        	<div>
                                        	<div class="form-group">
                                                <div class="col-sm-12">
                                                    <h4><b>Items</b></h4>
                                                     <span class="detspan" id="itemTabFlag" ></span>
                                                    <hr>
                                                </div>
                                            </div>
                                            <form id="itemDetailForm">
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
                                        	<div>
                                        	<div class="form-group pTSGtpFormTabId">
                                                <div class="col-sm-12">
                                                    <h4><b>Technical Bid Deviation</b></h4>
                                                     <span class="detspan" id="itemGtpTabFlag" ></span>
                                                    <hr>
                                                </div>
                                            </div>
                                            <sf:form id="technicalDeviationForm" modelAttribute="itemScrutinyLine" method="POST">
                                            <div class="form-group">
                                            <input type="text" style="display: none;" id="itemScrutinyLineId" name="itemScrutinyLineId" value="">
                                             <input type="text" style="display: none;" id="itemScrutinyId" name="itemScrutiny.itemScrutinyId" value="">
                                              <input type="text" style="display: none;" id="bidderId" name="itemScrutiny.bidder.bidderId" value="">
                                              <input type="text" style="display: none;" id="itemBidId" name="itemScrutiny.itemBid.itemBidId" value="">
                                               <input type="text" style="display: none;" id="bidderGtpId"  value="">
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
                                                <div class="col-sm-4" id="textResponseDivId">
                                                    <div class="styled-input">
                                                    <textarea id="textResponse" name="textResponse" class="textResponse requiredField" maxlength="255" ></textarea>
                                                       <!-- <input type="text" id="textResponse" name="textResponse" class="textResponse"/> -->
                                                        <label>Deviation Response<span class="red">*</span></label>
                                                        <span></span>
                                                    </div>
                                                </div>
                                                 <div class="col-sm-4 booleanResponse" id="booleanResponseDivId">
                                                 <div class="styled-input">
                                                                <select id="booleanResponse" class="booleanResponse" name="textResponse" class="dropDown">
                                                                <option value="">Select</option>
                                                                <option value="YES">YES</option>
                                                                 <option value="NO">NO</option>
                                                                </select>
                                                                <label>Deviation Response<span class="red">*</span></label>
                                                                <span></span>
                                                            </div>
                                                </div>
                                                
                                                <div class="col-sm-4" id="fileResponseDivId" style="display: none ;">
                                                <label>Deviation Response<span class="red">*</span></label>
                                                                <span></span>
                                                <div class="input-group" >   
                                                            
                                                          <input type="file"  id="techFileResponseUploadId" data-id="techFileResponseId" data-name="techFileResponseName" data-anchor="a_techFileResponse" class="form-control fileResponse uploadFile requiredFile">
                                                          <input type="hidden" id="techFileResponseId" name="fileResponse.attachmentId" class="form-control fileResponse " /> 
												                <span class="input-group-btn">
												                   <!--  <button id="deleteTechnicalAttachmentId" class="btn btn-default techFileResponseId" onclick="return submitWithParam('deleteAttachment','techFileResponseId','fileResponseAttachmentDeleteResp');" disabled="disabled"><i class="fa fa-times"></i></button> -->
												                 <button id="deleteTechnicalAttachmentId" class="btn btn-default techFileResponseId" onclick="return submitWithThreeParam('deleteAttachmentById','ItemScrutinyLine','fileResponse','techFileResponseId','gtpDeviationfileDeleteResp');"><i class="fa fa-times"></i></button>
												                </span>
           	                                            </div>
           	                                           <%--   <a class="col-sm-12 filelabel" data-url="<%=request.getContextPath()%>/download" id="downloadFileResponse"></a>  --%>
           	                                             <a class="col-sm-12 filelabel" data-url="<%=request.getContextPath()%>/download" id="a_techFileResponse"></a>
           	                                             
           	                                       </div>
           	                                        <div class="form-group">
		                                                <div class="col-sm-4">
		                                                    <label>Deviation Type:</label>
		                                                    <span class="detspan" id="deviationType" ></span>
		                                                </div>
                                                    </div>
           	                                   
                                              </div> 
                                              <div class="form-group text-center">
                                              	<button class="btn btn-info save" onclick="return submitIt('technicalDeviationForm','saveTechnicalDeviationResponse','bidderDeviationResponseSubmitResp');">Save</button>
                                              	<!-- <button class="btn btn-info actionbtn cancel">Cancel</button>
                                              	<button class="btn btn-info actionbtn">Reset</button> -->
                                              </div>
                                              </sf:form>
                                            </div>
                                         <!--sub of field group 3  -->
                                         <div>
                                        	<div class="form-group techDocFormDiv">
                                                <div class="col-sm-12">
                                                    <h4><b>Technical Required Documents</b></h4>
                                                     <span class="detspan" id="itemTechDocTabFlag" ></span>
                                                    <hr>
                                                </div>
                                            </div>
                                            <sf:form id="technicalDocumentDeviationForm" modelAttribute="itemScrutinyLine" method="POST">
                                            <div class="form-group">
                                             <input type="text" style="display: none;" id="itemScrutinyLineId" name="itemScrutinyLineId" value="">
                                             <input type="text" style="display: none;" id="itemScrutinyId" name="itemScrutiny.itemScrutinyId" value="">
                                              <input type="text" style="display: none;" id="bidderId" name="itemScrutiny.bidder.bidderId" value="">
                                               <input type="text" style="display: none;" id="itemBidId" name="itemScrutiny.itemBid.itemBidId" value="">
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
                                                <div class="col-sm-4" id="textResponseDivId">
                                                    <div class="styled-input">
                                                    <textarea id="textResponse" name="textResponse" class="textResponse requiredField" maxlength="255" ></textarea>
                                                      <!--  <input type="text" id="textResponse" name="textResponse" class="textResponse"/> -->
                                                        <label>Deviation Response<span class="red">*</span></label>
                                                        <span></span>
                                                    </div>
                                                </div>
                                                 <div class="col-sm-4 booleanResponse" id="booleanResponseDivId">
                                                 <div class="styled-input">
                                                                <select id="booleanResponse" class="booleanResponse" name="textResponse" class="dropDown">
                                                                <option value="">Select</option>
                                                                <option value="YES">YES</option>
                                                                 <option value="NO">NO</option>
                                                                </select>
                                                                <label>Deviation Response<span class="red">*</span></label>
                                                                <span></span>
                                                            </div>
                                                </div>
                                                <div class="col-sm-4" id="fileResponseDivId" style="display: none ;">
                                                 <label>Deviation Response<span class="red">*</span></label>
                                                                <span></span>
                                                <div class="input-group" >               
                                                          <input type="file"  id="docFileResponseUploadId" data-id="docFileResponseId" data-name="docFileResponseName" data-anchor="a_docFileResponse" class="form-control fileResponse uploadFile requiredFile">
                                                          <input type="hidden" id="docFileResponseId" name="fileResponse.attachmentId" class="form-control fileResponse" /> 
												                <span class="input-group-btn">
												                   <!--  <button  id="deleteTechDocAttachmentId" class="btn btn-default docFileResponseId" onclick="return submitWithParam('deleteAttachment','docFileResponseId','fileResponseAttachmentDeleteResp');" disabled="disabled"><i class="fa fa-times"></i></button> -->
												                <button  id="deleteTechDocAttachmentId" class="btn btn-default docFileResponseId" onclick="return submitWithThreeParam('deleteAttachmentById','ItemScrutinyLine','fileResponse','docFileResponseId','techDocDeviationfileDeleteResp');"><i class="fa fa-times"></i></button>
												                </span>
           	                                            </div>
           	                                            <%-- <a class="col-sm-12 filelabel" data-url="<%=request.getContextPath()%>/download" id="downloadFileResponse"></a> --%> 
           	                                             <a class="col-sm-12 filelabel" data-url="<%=request.getContextPath()%>/download" id="a_docFileResponse"></a>
           	                                       </div>
           	                                       <div class="form-group">
		                                                <div class="col-sm-4">
		                                                    <label>Deviation Type:</label>
		                                                    <span class="detspan" id="deviationType" ></span>
		                                                </div>
                                                    </div>
           	                                       
                                               
                                              </div>
                                            <div class="form-group text-center">
                                              	<button class="btn btn-info save" onclick="return submitIt('technicalDocumentDeviationForm','saveTechnicalDocumentDeviationResponse','bidderDeviationResponseSubmitResp');">Save</button>
                                              	<!-- <button class="btn btn-info cancel">Cancel</button>
                                              	<button class="btn btn-default">Reset</button> -->
                                              </div>
                                              </sf:form>
                                            </div>
                                         <!--sub of field group 5  -->
                                          <!--sub of field group 6  -->
                                        	<div>
                                        	<div class="form-group">
                                                <div class="col-sm-12">
                                                    <h4><b>Technical Confirmation</b></h4>
                                                    <hr>
                                                </div>
                                                
                                                <div class="form-group" id="techConfirmMsgDivId" style="display: none;">
                                                    <div class="col-sm-6">
                                                       <label>Technical Confirmation Status:</label>
                                                    <span class="detspan" id="technicalConfirmStatus" ></span>
                                                </div>
                                                </div>
                                                
                                            </div>
                                            <sf:form id="gennerateTechnicalDeviationDoc">
						                      </sf:form>
                                             <div class="col-sm-12">
                                                    	<button onclick="downloadTechnicalDeviationPdf(event)" class="btn btn-default deviationDocDownloadBtnClass">Download Technical Deviation PDF</button>
                                                    	<!-- <button class="btn btn-default">Goto Main Page</button> -->
                                                    </div>  
                                                    <hr>
                                            <sf:form id="confirmationTechnicalDevitionForm" modelAttribute="itemScrutiny" method="POST">
                                            <div class="form-group">
                                                    <input type="text" style="display: none;" id="itemScrutinyId" name="itemScrutinyId" value="">
                                                    <input type="text" style="display: none;" id="bidderId" name="bidder.bidderId" value="">
                                                    <input type="text" style="display: none;" id="itemBidId" name="itemBid.itemBidId" value="">
                                                <div class="col-sm-4">                                                
                                                  <label>Digital Sign Deviation bid</label>
                                                
                                                  <div class="input-group" >               
                                                          <input type="file"  id="techdocFileResponseUploadId" data-id="techdocFileResponseId" data-name="techdocFileResponseName" data-anchor="a_techdocFileResponse" class="form-control uploadFile requiredFile ">
                                                          <input type="hidden" id="techdocFileResponseId" name="scrutinyFile.attachmentId" class="form-control fileResponse" /> 
												                <span class="input-group-btn">
												                    <!-- <button  id="deleteTechDocAttachmentId" class="btn btn-default docFileResponseId" onclick="return submitWithParam('deleteAttachment','techdocFileResponseId','fileResponseAttachmentDeleteResp');" disabled="disabled"><i class="fa fa-times"></i></button> -->
												                <button  id="deleteTechDocAttachmentId" class="btn btn-default techdocFileResponseId" onclick="return submitWithThreeParam('deleteAttachmentById','ItemScrutiny','scrutinyFile','techdocFileResponseId','techConfirmDeviationfileDeleteResp');"><i class="fa fa-times"></i></button>
												                </span>
           	                                            </div>
                                                </div> 
                                                <div class="col-sm-4">
                                               
                                                	<%-- <a class="col-sm-12 filelabel" data-url="<%=request.getContextPath()%>/download" id="downloadTechFileResponse"></a>  --%>
           	                                             <a class="col-sm-12 filelabel" data-url="<%=request.getContextPath()%>/download" id="a_techdocFileResponse"></a>
                                                </div>                                               
                                              </div>  
                                              
                                               <div class="form-group">
                                                <div class="col-md-12 readonly">
									                    <label class="checkbox-inline " id="confirmCheckBox">
									                      <input type="checkbox" class="" name="bidderDeviationStatus" id="confirmCheckBox" value="Y" checked>
									                      I Submit Deviation for the Selected Item </label>
									                  </div>
                                              </div>
                                              <div id="finalScrutinyDivId" style="display: none;">
                                                  <div class="form-group">
                                                     <div class="col-sm-6">
			                                                    <label class="radio-inline" style="margin-top:20px;">
			                                                        <input type="radio" class="finalApproval" name="finalScrutinyStatus" value="Approved" class="finalStatus">Approved
			                                                     </label>
			                                                     <label class="radio-inline"  style="margin-top:20px;">  
			                                                       	<input type="radio" class="finalApproval" name="finalScrutinyStatus" value="Rejected" class="finalStatus">Reject
			                                                    </label>
			                                                    </div>
                                            		</div>
			                                            <div class="form-group">
			                                                <div class="col-sm-4">
			                                                    <div class="styled-input">
			                                                        <input type="text" id="finalScrutinyComment" name="finalScrutinyComment" class="finalStatus finalApproval">
			                                                        <label>Comments<span class="red">*</span></label>
			                                                        <span></span>
			                                                    </div>
			                                                </div>
			                                            </div>
			                                     </div>
                                            <div class="form-group text-center">
                                              	<button class="btn btn-info save" onclick="return submitIt('confirmationTechnicalDevitionForm','confirmTechnicalDeviationByBidder','confirmDeviationSubmitResp');">Submit</button>
                                              </div>
                                              </sf:form>
                                            </div>
                                         <!--sub of field group 4  -->
                                        	<div>
                                        	<div class="form-group commDocFormDiv">
                                                <div class="col-sm-12">
                                                    <h4><b>Commercial Bid Deviation</b></h4>
                                                     <span class="detspan" id="scrutinyPointTabFlag" ></span>
                                                    <hr>
                                                </div>
                                            </div>
                                          <sf:form id="commercialDeviationForm" modelAttribute="itemScrutinyLine" method="POST">
                                             <div class="form-group">
                                            <input type="text" style="display: none;" id="itemScrutinyLineId" name="itemScrutinyLineId" value="">
                                            <input type="text" style="display: none;" id="itemScrutinyId" name="itemScrutiny.itemScrutinyId" value="">
                                              <input type="text" style="display: none;" id="bidderId" name="itemScrutiny.bidder.bidderId" value="">
                                                <div class="col-sm-6">
                                                        <label>Scrutiny Point:</label>
                                                        <span style="color:blue;text-align:center"id="scrutinyPointName" ></span>
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
                                               
                                              
                                              <div class="col-sm-6" id="textResponseDivId">
                                                    <div class="styled-input">
                                                    <textarea id="textResponse" name="textResponse" class="textResponse requiredField" maxlength="255" ></textarea>
                                                       <!-- <input type="text" id=textResponse name="textResponse" class="textResponse"/> -->
                                                        <label>Deviation Response<span class="red">*</span></label>
                                                        <span></span>
                                                    </div>
                                                </div>
                                                 <div class="col-sm-4 booleanResponse" id="booleanResponseDivId">
                                                 <div class="styled-input">
                                                                <select id="booleanResponse" class="booleanResponse" name="textResponse" class="dropDown">
                                                                <option value="">Select</option>
                                                                <option value="YES">YES</option>
                                                                 <option value="NO">NO</option>
                                                                </select>
                                                                <label>Deviation Response<span class="red">*</span></label>
                                                                <span></span>
                                                            </div>
                                                </div>
                                                </div>
                                                <div class="col-sm-4" id="fileResponseDivId" style="display: none ;">
                                                 <label>Deviation Response<span class="red">*</span></label>
                                                                <span></span>
                                                <div class="input-group" >               
                                                          <input type="file"  id="commFileResponseUploadId" data-id="commFileResponseId" data-name="commFileResponseName" data-anchor="a_commFileResponse" class="form-control fileResponse uploadFile requiredFile">
                                                          <input type="hidden" id="commFileResponseId" name="fileResponse.attachmentId" class="form-control fileResponse" /> 
												                <span class="input-group-btn">
												                    <!-- <button id="deletecommercialAttachmentId" class="btn btn-default commfileResponseId" onclick="return submitWithParam('deleteAttachment','commFileResponseId','fileResponseAttachmentDeleteResp');" disabled="disabled"><i class="fa fa-times"></i></button> -->
												               <button id="deletecommercialAttachmentId" class="btn btn-default commFileResponseId" onclick="return submitWithThreeParam('deleteAttachmentById','ItemScrutinyLine','fileResponse','commFileResponseId','scrutinyPointDeviationfileDeleteResp');"><i class="fa fa-times"></i></button>
												                </span>
           	                                            </div>
           	                                             
           	                                             <a class="col-sm-12 filelabel" data-url="<%=request.getContextPath()%>/download" id="a_commFileResponse"></a>
           	                                       </div>
           	                                       <div class="form-group">
		                                                <div class="col-sm-4">
		                                                    <label>Deviation Type:</label>
		                                                    <span class="detspan" id="deviationType" ></span>
		                                                </div>
                                                    </div>
                                             
                                            
                                              <div class="form-group text-center">
                                              	<button class="btn btn-info save" onclick="return submitIt('commercialDeviationForm','saveCommercialDeviationResponse','bidderDeviationResponseSubmitResp');">Save</button>
                                              	
                                              </div>
                                              </sf:form>
                                            </div>
                                         <!--sub of field group 4  -->
                                         
                                         <!--sub of field group 5  -->
                                        	<div>
                                        	<div class="form-group">
                                                <div class="col-sm-12">
                                                    <h4><b>Commercial Required Documents</b></h4>
                                                    <span class="detspan" id="commDocTabFlag" ></span>
                                                    <hr>
                                                </div>
                                            </div>
                                            <sf:form id="commercialDocumentDeviationForm" modelAttribute="itemScrutinyLine" method="POST">
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
                                                <div class="col-sm-4" id="textResponseDivId">
                                                    <div class="styled-input">
                                                    <textarea id="textResponse" name="textResponse" class="textResponse requiredField" maxlength="255" ></textarea>
                                                     <!--   <input type="text" id="textResponse" name="textResponse" class="textResponse"/> -->
                                                        <label>Deviation Response<span class="red">*</span></label>
                                                        <span></span>
                                                    </div>
                                                </div>
                                                 <div class="col-sm-4 booleanResponse" id="booleanResponseDivId" style="display: none ;">
                                                 <div class="styled-input">
                                                                <select id="booleanResponse" class="booleanResponse" name="textResponse" class="dropDown">
                                                                <option value="">Select</option>
                                                                <option value="YES">YES</option>
                                                                 <option value="NO">NO</option>
                                                                </select>
                                                                <label>Deviation Response<span class="red">*</span></label>
                                                                <span></span>
                                                            </div>
                                                </div>
                                                <div class="col-sm-4" id="fileResponseDivId" style="display: none ;">
                                                 <label>Deviation Response<span class="red">*</span></label>
                                                                <span></span>
                                                <div class="input-group" >               
                                                          <input type="file"  id="commDocFileResponseUploadId" data-id="commDocFileResponseId" data-name="commDocFileResponseName" data-anchor="a_commDocFileResponse" class="form-control fileResponse uploadFile requiredFile">
                                                          <input type="hidden" id="commDocFileResponseId" name="fileResponse.attachmentId" class="form-control fileResponse" /> 
												                <span class="input-group-btn">
												                    <!-- <button id="deleteCommDocAttachmentId" class="btn btn-default commDocFileResponseId" onclick="return submitWithParam('deleteAttachment','docFileResponseId','fileResponseAttachmentDeleteResp');" disabled="disabled"><i class="fa fa-times"></i></button> -->
												                     <button id="deleteCommDocAttachmentId" class="btn btn-default commDocFileResponseId" onclick="return submitWithThreeParam('deleteAttachmentById','ItemScrutinyLine','fileResponse','commDocFileResponseId','commDocDeviationfileDeleteResp');"><i class="fa fa-times"></i></button>
												                </span>
           	                                            </div>
           	                                            
           	                                             <a class="col-sm-12 filelabel" data-url="<%=request.getContextPath()%>/download" id="a_commDocFileResponse"></a>
           	                                       </div>
           	                                       <div class="form-group">
		                                                <div class="col-sm-4">
		                                                    <label>Deviation Type:</label>
		                                                    <span class="detspan" id="deviationType" ></span>
		                                                </div>
                                                    </div>
                                              
                                              </div>
                                            <div class="form-group text-center">
                                              	<button class="btn btn-info save" onclick="return submitIt('commercialDocumentDeviationForm','saveCommercialDocumentDeviationResponse','bidderDeviationResponseSubmitResp');">Save</button>
                                             
                                              </div>
                                              </sf:form>
                                            </div>
                                         <!--sub of field group 5  -->
                                          <!--sub of field group 6  -->
                                        	<div>
                                        	<div class="form-group">
                                                <div class="col-sm-12">
                                                    <h4><b>Commercial Confirmation</b></h4>
                                                    <hr>
                                                </div>
                                                <div class="form-group" id="commConfirmMsgDivId" style="display: none;'">
                                                    <div class="col-sm-6">
                                                       <label>Commercial Confirmation Status:</label>
                                                    <span class="detspan" id="commercialConfirmStatus" ></span>
                                                </div>
                                                </div>
                                            </div>
                                            <sf:form id="gennerateCommercialDeviationDoc">
						                      </sf:form>
                                             <div class="col-sm-12">
                                                    	<button onclick="downloadCommercialDeviationPdf(event)" class="btn btn-default deviationDocDownloadBtnClass">Download Commercial Deviation PDF</button>
                                                    	<!-- <button class="btn btn-default">Goto Main Page</button> -->
                                                    </div>  
                                                    <hr>
                                            <sf:form id="confirmationCommercialDevitionForm" modelAttribute="itemScrutiny" method="POST">
                                            <div class="form-group">
                                                    <input type="text" style="display: none;" id="itemScrutinyId" name="itemScrutinyId" value="">
                                                    <input type="text" style="display: none;" id="bidderId" name="bidder.bidderId" value="">
                                                <div class="col-sm-4">                                                
                                                  <label>Digital Sign Deviation bid</label>
                                                 <!--  <input type="file" class="form-control"> -->
                                                  <div class="input-group" >               
                                                          <input type="file"  id="comdocFileResponseUploadId" data-id="comdocFileResponseId" data-name="comdocFileResponseName" data-anchor="a_comdocFileResponse" class="form-control uploadFile requiredFile ">
                                                          <input type="hidden" id="comdocFileResponseId" name="scrutinyFile.attachmentId" class="form-control fileResponse" /> 
												                <span class="input-group-btn">
												               
												                    <!-- <button  id="deleteComDocAttachmentId" class="btn btn-default comdocFileResponseId" onclick="return submitWithParam('deleteAttachment','comdocFileResponseId','fileResponseAttachmentDeleteResp');" disabled="disabled"><i class="fa fa-times"></i></button> -->
												                     <button  id="deleteComDocAttachmentId" class="btn btn-default comdocFileResponseId"  onclick="return submitWithThreeParam('deleteAttachmentById','ItemScrutiny','scrutinyFile','comdocFileResponseId','commConfirmDeviationfileDeleteResp');"><i class="fa fa-times"></i></button>
												                </span>
           	                                            </div>
           	                                            <%-- <a class="col-sm-12 filelabel" data-url="<%=request.getContextPath()%>/download" id="downloadFileResponse"></a> 
           	                                             <a class="col-sm-12 filelabel" data-url="<%=request.getContextPath()%>/download" id="a_docFileResponse"></a> --%>
                                                </div> 
                                                <div class="col-sm-4">
                                               <!--  <div class="styled-input">
                                                	<button class="btn btn-default">Download Digital Sign Bid PDF</button>
                                                	</div> -->
                                                	<%-- <a class="col-sm-12 filelabel" data-url="<%=request.getContextPath()%>/download" id="downloadComFileResponse"></a> --%> 
           	                                             <a class="col-sm-12 filelabel" data-url="<%=request.getContextPath()%>/download" id="a_comdocFileResponse"></a>
                                                </div>                                               
                                              </div>  
                                              
                                               <div class="form-group">
                                                <div class="col-md-12 readonly" id="confirmCheckBox" >
									                    <label class="checkbox-inline">
									                      <input type="checkbox"  id="confirmCheckBox" name="bidderDeviationStatus" value="Y" checked>
									                      I Submit Deviation for the Selected Item </label>
									                  </div>
                                              </div>
                                              <div id="finalScrutinyDivId" style="display: none;">
                                                  <div class="form-group">
                                                     <div class="col-sm-6">
			                                                    <label class="radio-inline" style="margin-top:20px;">
			                                                        <input type="radio" name="finalScrutinyStatus" value="Approved" class="finalStatus">Approved
			                                                     </label>
			                                                     <label class="radio-inline"  style="margin-top:20px;">  
			                                                       	<input type="radio" name="finalScrutinyStatus" value="Rejected" class="finalStatus">Reject
			                                                    </label>
			                                                    </div>
                                            		</div>
			                                            <div class="form-group">
			                                                <div class="col-sm-4">
			                                                    <div class="styled-input">
			                                                        <input type="text" id="finalScrutinyComment" name="finalScrutinyComment" class="finalStatus">
			                                                        <label>Comments<span class="red">*</span></label>
			                                                        <span></span>
			                                                    </div>
			                                                </div>
			                                            </div>
			                                     </div>
                                            <div class="form-group text-center">
                                              	<button class="btn btn-info save" onclick="return submitIt('confirmationCommercialDevitionForm','confirmCommercialDeviationByBidder','confirmDeviationSubmitResp');">Submit</button>
                                              </div>
                                              </sf:form>
                                            </div>
                                         <!--sub of field group 6  -->
                                            </div>
                                         <!--fields of field group 2  -->
                                       
                                    </div>
									 <!-- <div >
                                        	<div class="detailscont">
  							 				
    									</div>
    									<div class="form-group">
                                                <div class="col-sm-12">
                                                    <h4><b>Confirm Deviation Submission</b></h4>
                                                    <hr>
                                                </div>
                                            </div>
                                            
                                            
                                            <div class="form-group">
			                                 </div>
			                       <div class="form-group">
			                        <div class="col-sm-4">
                                                <label class="checkbox-inline" id="checkBoxLabelId">
                                                    <input type="checkbox" value="Y" checked> All Deviation has been Submitted.</label>
                                            </div>
						            </div>
                                            <div class="form-group">
                                            	<div class="col-sm-12 text-center">
                                            	 <button type="button" id="confirmDeviationBtnId" class="btn btn-info save" onclick="confirmAlldeviationSubmit(event)">Submit</button>
                                            	</div>
                                            </div>
                                            
                                        </div>  -->
                                    
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
 <script src="<%=request.getContextPath()%>/resources/${appMode}/transaction/js/deviationBid.js?appVer=${appVer}"></script>
<script src="<%=request.getContextPath()%>/resources/${appMode}/commons/js/formFields.js?appVer=${appVer}"></script>
<script src="<%=request.getContextPath()%>/resources/${appMode}/partner/js/uploadFile.js?appVer=${appVer}"></script>
<script src="<%=request.getContextPath()%>/resources/${appMode}/commons/js/utility.js?appVer=${appVer}"></script>
<script src="<%=request.getContextPath()%>/resources/${appMode}/commons/js/liCache.js?appVer=${appVer}"></script>