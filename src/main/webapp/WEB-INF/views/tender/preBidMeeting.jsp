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
                   
                        /* $('#leftPane').paginate({
                    		perPage: 6,
                    	}); */
                    });               
                </script>
            
        <div class="full-container">
        <input type="hidden" id="myTenderUrl" class="myTenderUrl" value="${myTenderUrl}" />
            <!-- left-side start-->
            <div class="clearfix"></div>
            <div id="mobile_first_container" class="left-side col-md-3 no-marg">
                <div class="detailsheader">
        	<div class="col-sm-3 text-center brdrgt"><label>Pre Bid Meeting Report (0)</label></div>
        </div>               
                <div class="input-group">
                    <div class="input-group-btn search-panel">
                        <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">
                            <span id="search_concept">Filter by</span> <span class="caret"></span>
                        </button>
                         <ul class="dropdown-menu" role="menu">
                      <li><a href="#contains"><input type="radio" name="filterBy" value="tahdrCode" checked/> Tender Code</a></li>
                      <li class="divider"></li>
                      <li><a href="#all"><input type="radio" name="filterBy" value="title" /> Title</a></li>
                    </ul>
                    </div>
                     <input type="text" id="searchLiteralId" class="form-control" name="x" placeholder="Search term...">
                    <span class="input-group-btn">
                    <button class="btn btn-default" type="button"  id="searchBtn"><span class="glyphicon glyphicon-search"></span></button>
                   
					<span class="glyphicon glyphicon-plus"></span>
				</button>
                    </span>
                </div>
               <c:if test="${documentType.equalsIgnoreCase('Tender')}">
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
                <ul id="leftPane" class="nav nav-tabs tabs-left example leftPaneData">
                   
                </ul>
                 <p id="pagination-here"></p>   
                <div class="clearfix"></div>
            </div>
            <!-- left-side end-->

            <!-- right-side start-->
            <div id="mobile_second_container" class="right-side col-md-9 no-marg">
            <div class="detailsheader toptabbrd">
        	<div class="col-sm-9 text-center"><label>Pre Bid Meeting Details</label></div>
        </div>
                <div class="clearfix"></div>
                <div class="tab-content">
                    <!-- Master tab start-->

                    <div class="tab-pane active in">
                        <div class="card">
                            <div class="posrelative">
                           <div class="demo-section k-content">
                                    <div id="tabstrip" class="Firsttab">
                                        <ul>
                                            <!-- tabs -->
                                            <li class="k-state-active prebidmeetingtab">Pre Bid Meeting</li>
                                           
                                        </ul>
                                        
                                        <div class="prebidmeetingtab">
                                        <div class="detailscont">
  							 				
    									</div>
                                            <div class="form-group">
                                                <div class="col-sm-12">
                                                    <h4><b>Pre Bid Meeting </b></h4>
                                                    <hr>
                                                </div>	
                                            </div>
                                            <sf:form id="uploadMomForm" modelAttribute="tahdr" method="POST">
                                            <div class="form-group">
                                         
                                                <div class="mobclearfix"></div>
                                                
                                      
    									          <div class="form-group">
    									              <input type="text" style="display: none;" id="tahdrId" name="tahdrId" >
    									              <input type="text" style="display: none;" id="tenderNo" name="tahdrCode" >
                                                <div class="col-sm-4">
                                                 
                                                        <label>Tender/Auction No:</label>
                                                        <span class="detspan" id="tenderNo" ></span>
                                                </div>
                                                <div class="col-sm-4">
                                                 <label>Tender/Auction Version:</label>
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
                                                <div class="mobclearfix"></div>
                                                <div class="col-sm-3">
                                                    <div class="styled-input">
                                                      
                                                    </div>
                                                    <div class="clearfix"></div>
                                                </div>
                                                <div class="mobclearfix"></div> 
                                                </div>
                                                   
                                                   	 <div class="form-group">
                                                   	 <div class="col-sm-2" id="fileId">
                                                   	  <label>Upload Minutes of Meeting:</label>
                                                   	  </div>
	                                                   	<div class="col-sm-4" id="fileId">
	                                                     <div class="input-group" >               
	                                                          <input type="file"  id="momUploadId" data-id="momResponseId" data-name="downloadMom" data-anchor="downloadMom" class="form-control uploadFile requiredField">
	                                                          <input type="hidden" id="momResponseId" name="mom.attachmentId" class="form-control fileResponse requiredField" /> 
													                <span class="input-group-btn">
													                    <button id="deleteMomAttachmentId" class="btn btn-default momResponseId" onclick="return submitWithParam('deleteAttachment','momResponseId','momAttachmentDeleteResp');"><i class="fa fa-times"></i></button>
													                </span>
	           	                                            </div>
	           	                                             
	           	                                       </div>
	           	                                       </div> 
	           	                                       <div class="form-group">
	           	                                       <div class="col-sm-4">
	           	                                             <label>Minutes of Meeting (For Download):</label>
	           	                                             <a class="col-sm-12 filelabel" data-url="<%=request.getContextPath()%>/download" id="downloadMom"></a> 
	           	                                             </div>
	           	                                       </div> 
	           	                                       
                                              
                                     <div class="col-sm-12 text-center positionABS" style="margin-bottom:10px;">
                                        
                                        <button class="btn btn-info save" onclick="return submitIt('uploadMomForm','saveMom','uploadMOMResp');">Save</button>
                                        <!-- <button class="btn btn-info cancel" id="cancelBtnId">Cancel</button> -->
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
<script src="<%=request.getContextPath()%>/resources/${appMode}/transaction/js/preBidMeeting.js?appVer=${appVer}"></script>
<script src="<%=request.getContextPath()%>/resources/${appMode}/commons/js/formFields.js?appVer=${appVer}"></script>
<script src="<%=request.getContextPath()%>/resources/${appMode}/partner/js/uploadFile.js?appVer=${appVer}"></script>
<script src="<%=request.getContextPath()%>/resources/${appMode}/commons/js/utility.js?appVer=${appVer}"></script>