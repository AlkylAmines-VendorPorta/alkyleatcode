<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@taglib uri = "http://www.springframework.org/tags/form" prefix = "sf"%>
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/${appMode}/tilescommon/css/fioristyle.css?appVer=${appVer}"/>
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/${appMode}/tilescommon/css//borderTab.css?appVer=${appVer}">
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/${appMode}/tilescommon/css/mobile.css?appVer=${appVer}">
			<style>.panel-body{padding:0px 10px;}</style>
       		
            <script>
                $(document).ready(function() {
                    $("#tabstrip").kendoTabStrip();
                    $('#leftPane').paginathing({perPage: 6});
                });               
            </script>
   
        <div class="full-container"></div>
       
            <!-- left-side start-->
            <div class="clearfix"></div>
           <%-- <input type="hidden" id="myTenderTahdrId" value="${tahdrId}">
           <input type="hidden" id="myTenderTenderType" value="${tenderType}"> --%>
            <div id="mobile_first_container" class="left-side col-md-3 no-marg">   
            <div class="detailsheader">
        		<div class="col-sm-3 text-center brdrgt"><label>Tender Bids Report (0)</label></div>
       		</div>        
               <div class="input-group">
                <div class="input-group-btn search-panel">
                    <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">
                    	<span id="search_concept">Filter by</span> <span class="caret"></span>
                    </button>
                    <ul class="dropdown-menu" role="menu">
                      <li><a href="#contains"><input type="radio" name="filterBy" value="tahdrCode" /> ${documentType} Code</a></li>
                      <li><a href="#contains"><input type="radio" name="filterBy" value="title" /> ${documentType} Title</a></li>
                      <li><a href="#contains"><input type="radio" name="filterBy" value="tahdrStatusCode" /> ${documentType} Status</a></li>
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
        		<div class="col-sm-9 text-center"><label>${documentType} Bids Details</label></div>
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
                                            <li class="k-state-active" id="tenderTabId" onclick="">${documentType}</li>
                                           
                                            <li data-parentTab="tenderTabId" class="viewBidTab toggleTab" id="viewBidParentTabId" openTab="tenderMaterialTab" onclick="return submitWithParam('getTAHDRMaterialListByTahdrId','tahdrId','loadTahdrMaterialListForView')">View Bid </li>
                                        </ul>

                                        <!--fields of field group 1  -->
                                        <div>
                                        <div class="detailscont">
  							 				
    									</div>
                                            <div class="form-group">
                                                <div class="col-sm-12">
                                                    <h4><b>${documentType} Short Details</b></h4>
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
		                                                        <label>${documentType} No:</label>
		                                                         <span class="detspan" id="tenderNo"></span>
		                                                </div>
		                                                <div class="col-sm-4">
		                                                        <label>For Trader:</label>
		                                                         <span class="detspan" id="trader"></span>
		                                                </div> 
		                                                <div class="col-sm-4">
		                                                        <label>For Manufacturer:</label>
		                                                         <span  class="detspan" id="manfacturer"> </span>
		                                                </div>
		                                              </div>  
		                                              <div class="form-group">
		                                              <div class="col-sm-4">
		                                                        <label>Tender Status:</label>
		                                                        <span class="detspan" id="status"></span>
		                                                </div>
		                                                
		                                                <div class="col-sm-4">
		                                                        <label>No of Bidder:</label>
		                                                        <span class="detspan" id="noBidder"></span>
		                                                </div>
		                                                <div class="col-sm-4">
		                                                        <label>Tender Bid Type:</label>
		                                                        <span class="detspan" id="tenderBidType"></span>
		                                                </div> 
		                                              </div>                                                     
		                                              <div class="form-group">
		                                              <div class="col-sm-4">
		                                                        <label>Tender Details Document:</label>
		                                                        <a data-url="<%=request.getContextPath()%>/download" class="" id="downloadTenderDoc"></a>
		                                                </div> 
                                                    	
                                             		</div> 
                                             		</form>
                                             		
                                             </div>
                                            <div class="clearfix"></div>
                                        </div>
                                        <!--fields of field group 1  -->
                                    
                                        
                                        <div>
                                        <div class="detailscont">
  							 				
    									</div>
    									<div id="tabstrip3">
                                        <ul>
                                            <!-- tabs -->
                                            <li data-parentTab="tenderTabId" id="tahdrMaterialTabId" class="k-state-active tenderMaterialTab viewBidParentTabId" onclick="loadMaterial(this)">${documentType} Material List</li>
                                            <li data-parentTab="tahdrMaterialTabId" class="bidderTab" id="bidTabId"  onclick="loadBidders(this)">Bidders</li>
                                            <li data-parentTab="bidTabId" class="bidTab preliminaryScrutiny"  onclick="loadPreliminaryScrutiny(this)">Preliminary Scrutiny File</li>
                                            <li data-parentTab="bidTabId" class="bidTab finalScrutiny"  onclick="loadFinalScrutiny(this)">Final Scrutiny File</li>
                                            <li data-parentTab="bidTabId" class="bidTab" id="bidsDocTabId" onclick="return submitWithTwoParam('getBidderBids','bidderDetailForm #bidderId','itemDetailForm #tahdrMaterialId' ,'loadBidderBidsForView');" >Bids</li>
                                           
                                        </ul>
                                        <!--sub of field group 1  -->
                                        	<div>
                                        	<div class="form-group">
                                                <div class="col-sm-12">
                                                    <h4><b>Material Details</b></h4>
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
                                            <form id="bidderDetailForm">
                                            <input style="display: none;" id="bidderId">
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
		                                                <div class="col-sm-4">
		                                                        <label>Bidder Status:</label>
		                                                         <span class="detspan" id="bidderStatus"></span>
		                                                         
		                                                </div>
		                                              
		                                           </div>
		                                         
		                                        </form> 
                                            </div>
                                         <!--sub of field group 2  -->
                                         <div >
                                         <div class="form-group">
                                                <div class="col-sm-12">
                                                    <h4><b>Preliminary Scrutiny File</b></h4>
                                                    <hr>
                                                </div>
                                                <div class="col-sm-6">
                                            
                                            	<div class="clearfix"></div>
                                            <div class="form-group">
                                              <h4><b>Technical Scrutiny File</b></h4>
                                            <div class="col-sm-12">
                                            <div id="preliminary_tScrutinyStatusDiv"></div>
                                            </div>
                                            </div>
                                             </div>
                                             <div class="col-sm-6">
                                             <div class="form-group">
                                             <h4><b>Commercial Scrutiny File</b></h4>
                                            <label class="col-sm-6">Commercial Scrutiny Status :</label>
                                            <div class="col-sm-6" id="preliminary_cScrutinyStatusDiv"></div>
                                            </div>
                                            </div>
                                            </div>
                                         <div class="form-group"></div>
                                         </div>
                                         
                                         <div class="finalScrutiny">
                                         <div class="form-group">
                                                <div class="col-sm-12">
                                                    <h4><b>Final Scrutiny File </b></h4>
                                                    <hr>
                                                </div>
                                            </div>
                                                <div class="col-sm-6">
                                            
                                            	<div class="clearfix"></div>
                                            <div class="form-group">
                                            <h4><b>Technical Scrutiny File</b></h4>
                                            <div class="col-sm-12">
                                            <div id="final_tScrutinyStatusDiv"></div>
                                            </div>
                                            </div>
                                             </div>
                                             <div class="col-sm-6">
                                            <div class="form-group">
                                            <h4><b>Commercial Scrutiny File</b></h4>
                                            <label class="col-sm-6">Commercial Scrutiny Status :</label>
                                            <div class="col-sm-6" id="final_cScrutinyStatusDiv"></div>
                                            </div>
                                           
                                            </div>
                                         </div>
                                         
                                          <div>
                                         	<div class="form-group">
                                                <div class="col-sm-12">
                                                    <h4><b>Bids</b></h4>
                                                    <label  id="infolabelId"></label>
                                                    <hr>
                                                </div>
                                            </div>
                                              <div class="form-group">
		                                                <div class="col-sm-6">
		                                                	<div id="tcDivId">
		                                                    	<label  id="tclabelId"> Technical Bid:</label>
			                                                    <div id="technicalDivId">
			                                                        <a data-url="<%=request.getContextPath()%>/download" class="docs" id="downloadTechDoc"></a>
			                                                    </div> <br>
		                                                      	 <div id="commercialDivId">
			                                                        <label id="tcclabelId"> Commercial Details:</label><br>
			                                                        <a data-url="<%=request.getContextPath()%>/download"  class="docs" id="downloadCommDoc"></a>
		                                                     	</div>
		                                                  	</div>
		                                                  	<div id="pbDivId">
		                                                    	<label  id="pblabelId"> Price Bid:</label>
			                                                    <div id="priceBidlDivId">
			                                                        <a data-url="<%=request.getContextPath()%>/download" class="docs" id="downloadPBDoc"></a>
			                                                    </div>
		                                                  	</div>  
		                                                  	</div>
		                                                  	<div class="col-sm-6">
		                                                  	<div id="deviationDivId">  
		                                                        <label id="techDvtnlabelId"> Technical Deviation Bid:</label>
		                                                       	<div id="techDvtnBidTypeDivId">
		                                                          	<a data-url="<%=request.getContextPath()%>/download" class="docs" id="downloadTechDvtnDoc"></a>
		                                                       	</div>
		                                                       	
		                                                       	<label id="commDvtnlabelId"> Commercial Deviation Bid:</label>
		                                                       	<div id="commDvtnBidTypeDivId">
		                                                          	<a data-url="<%=request.getContextPath()%>/download" class="docs" id="downloadOtherDoc"></a>
		                                                       	</div>
		                                                  	</div>
		                                                  	</div>
		                                                  	
		                                                  	<div class="clearfix"></div>
		                                                  	<br>	<br>
		                                                  	<div class="col-sm-6">
		                                                  	<div id="c1DivId">  
		                                                        <label id="c1labelId"> Annexure C1 Bid:</label>
		                                                       	<div id="c1BidTypeDivId">
		                                                          	<a data-url="<%=request.getContextPath()%>/download" class="docs" id="downloadC1Doc"></a>
		                                                       	</div>
		                                                  	</div>
		                                                  	
		                                                </div>
		                                           </div>
		                                                
                                         </div>
                                            </div>
                                                                                   
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
<!-- Modal -->

<script src="<%=request.getContextPath()%>/resources/${appMode}/transaction/js/tenderBids.js?appVer=${appVer}"></script>
<script src="<%=request.getContextPath()%>/resources/${appMode}/commons/js/formFields.js?appVer=${appVer}"></script>
<script src="<%=request.getContextPath()%>/resources/${appMode}/tilescommon/js/bootstrap-datetimepicker.js?appVer=${appVer}"></script> 
<script src="<%=request.getContextPath()%>/resources/${appMode}/commons/js/utility.js?appVer=${appVer}"></script>
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
	    
		$('.CommercialDocumentTable').DataTable({
			"lengthMenu":lengthMenu
		});
});
</script>
