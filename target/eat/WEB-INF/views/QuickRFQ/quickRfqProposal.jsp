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
			.bidpBody{padding:10px !important;}</style>
       		
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
           <input type="hidden" id="documentType" class="documentType" value="${documentType}">
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
                      <li><a href="#contains"><input type="radio" name="filterBy" value="tahdrCode" />Code</a></li>
                      <li><a href="#contains"><input type="radio" name="filterBy" value="title" /> Title</a></li>
                      <li><a href="#contains"><input type="radio" name="filterBy" value="tahdrStatusCode" />Status</a></li>
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
			         <label class="btn btn-primary toggleTab" openTab="tenderDetailsTab" id="procurementToggle">
			         <input type="radio" name="tenderTypeCodeToggle"  value="PT">
			         <span class="glyphicon glyphicon-ok"></span> Procurement 
			         </label>
			         <label class="btn btn-primary active toggleTab" openTab="tenderDetailsTab" id="worksToggle">
			         <input type="radio" name="tenderTypeCodeToggle" checked id="worksCheckBoxId" value="WT">
			         <span class="glyphicon glyphicon-ok"></span> Works
			         </label>
			      </div>
				</c:if>
				<c:if test="${documentType.equalsIgnoreCase('Auction')}">
				 <input type="hidden" class="documentType" value="${documentType}" />
					<div class="btn-group btnmrg" data-toggle="buttons">
			         <label class="btn btn-primary toggleTab" openTab="tenderDetailsTab" id="forwardToggle">
			         <input type="radio" name="tenderTypeCodeToggle"  value="FA">
			         <span class="glyphicon glyphicon-ok"></span> Forward 
			         </label>
			         <label class="btn btn-primary active toggleTab" openTab="tenderDetailsTab" id="reverseToggle">
			         <input type="radio" name="tenderTypeCodeToggle" checked id="auctionsCheckBoxId" value="RA">
			         <span class="glyphicon glyphicon-ok"></span> Reverse
			         </label>
			      </div>
				</c:if>
				<c:if test="${documentType.equalsIgnoreCase('Quick Request For Proposal')}">
					<input type="radio" name="tenderTypeCodeToggle" checked style="display:none;" value="QRFQ" >
				</c:if>
	           <c:if test="${documentType.equalsIgnoreCase('Request For Proposal')}">
					<input type="radio" name="tenderTypeCodeToggle" checked style="display:none;" value="RFQ" >
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
        		<div class="col-sm-9 text-center"><label> Bids Details</label></div>
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
                                            <li class="k-state-active tenderDetailsTab" id="tenderTabId" onclick="">Base Information</li>
                                            
                                            <li data-parentTab="tenderTabId" id="tahdrMaterialTabId" class="tenderMaterialTab viewBidParentTabId toggleTab" openTab="tenderMaterialSubTab"  
                                              onclick="return submitWithParam('getTAHDRMaterialListByTahdrId','tahdrId','loadTahdrMaterialListForView')"> Material List</li>
                                              
                                            <li data-parentTab="tahdrMaterialTabId" class="bidderTab" id="bidTabId" onclick="loadBidders(this)" >List of Bidders</li>
                                             
                                             <li data-parentTab="bidTabId" class="bidTab toggleTab regularAuction" openTab="priceBidTab" id="bidsDocTabId" 
                                             onclick="$('#leftPaneData li:not(.active)').remove(); resetCollapseData(); getPriceBid();" >Bids</li>
                                             
                                             <!--   <li data-parentTab="bidTabId" class="bidTab " openTab="" id="" 
                                             onclick="$('#leftPaneData li:not(.active)').remove(); loadQuickBidderAllBids();" >Sheet</li> -->
                                           
                                        </ul>

                                        <!--fields of field group 1  -->
                                        <div>
                                        <div class="detailscont">
  							 				
    									</div>
                                            <div class="form-group">
                                                <div class="col-sm-12">
                                                    <h4><b>Base Info Details</b></h4>
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
		                                                        <label> Code/No:</label>
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
		                                              <%-- <div class="col-sm-4">
		                                                        <label>Tender Details Document:</label>
		                                                        <a data-url="<%=request.getContextPath()%>/download" class="" id="downloadTenderDoc"></a>
		                                                </div> --%> 
                                                    	
                                             		</div> 
                                             		</form>
                                             		
                                             </div>
                                            <div class="clearfix"></div>
                                        </div>
                                        <!--fields of field group 1  -->
                                      <!--sub of field group 1  -->
                                       
                                        	<div>
                                        	<div class="detailscont">
  							 				
    									    </div>
                                        	<div id="tabstrip2" class="Firsttab">
                                        	<ul>
                                            <!-- tabs -->
                                            <li class="k-state-active tenderMaterialSubTab" id="tenderTabId" onclick=""> Material Details</li>
                                            <li class="auctionTab bidHistorySubTab regularAuction" id="bidHistoryTabId" onclick="getCompleteBidHistory();">Bid History</li>
                                            
                                            </ul>
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
	                                                        <span class="detspan" id="matName" class="matName"></span>
	                                                </div>
	                                                <div class="col-sm-4">
	                                                        <label>Material Code:</label>
	                                                        <span class="detspan" id="matCode" class="matCode" ></span>
	                                                </div>
	                                                <div class="col-sm-4">
	                                                        <label>Unit:</label>
	                                                         <span class="detspan" id="uom" class="uom"></span>
	                                                </div>
	                                              </div>  
	                                              <div class="form-group">
	                                                <div class="col-sm-4">
	                                                        <label>Description:</label>
	                                                         <span class="detspan" id="description" class="description"></span>
	                                                </div>
	                                                <div class="col-sm-4">
	                                                        <label>Required Quantity:</label>
	                                                         <span class="detspan" id="reqQuantity" class="reqQuantity"></span>
	                                                </div>
	                                                 <div class="col-sm-4">
	                                                        <label>Specification Version:</label>
	                                                         <span class="detspan" id="specVersion" class="specVersion"></span>
	                                                </div>
	                                              </div> 
	                                              <div class="form-group">
	                                                <div class="col-sm-4">
	                                                        <label>Material Type:</label>
	                                                         <span class="detspan" id="tenderMatType" ></span>
	                                                </div>
	                                              </div> 
                                              	</form>
                                            </div>
                                            <div class="auctionTab regularAuction">
                                            	<div class="form-group">
	                                                <div class="col-sm-12">
	                                                    <h4><b>Bid Sheet</b></h4>
	                                                    <label  id="infolabelId"></label>
	                                                    <hr>
	                                                </div>
	                                            </div>
	                                            <div class="form-group">
		           	                                       <div class="col-sm-4">
		           	                                             <label>Bid Sheet Export (For Download):</label>
		           	                                             <a class="col-sm-12 filelabel" data-url="<%=request.getContextPath()%>/download" id="downloadBidSheet"></a> 
		           	                                       </div>
		           								</div>
		           								<div>
		           									<div class="form-group">
                                              			<div class="col-sm-12">
		                                             		<table class="completeBidHistoryTable tableResponsive table table-striped table-bordered" style="width:100% !important; ">
									         									<thead>
															         				<tr>
															         				    <th class="col-sm-2">Bidder Name</th>
																		         		<th class="col-sm-2">Offered Qty</th>
																		         		<th class="col-sm-2">Date Of Bidding</th>
																		         		<th class="col-sm-2">Previous Bid Amount</th>
																		         		<th class="col-sm-2">Per Unit Bid Amount</th>
																		         		<th class="col-sm-2">Total Amount</th>
									         										</tr>
									         									</thead>
																         		<tbody>
																         		 
																                </tbody>
									                						</table>
													  	</div>
		                                           </div>
		                                       </div> 
                                            </div>
                                           
                        
                               
                                            
                                            </div>
                                        
                                            </div>
                                         <!--sub of field group 1  -->
                                         
                                         <!--sub of field group 2  -->
                                        	<div >
                                        	<div class="detailscont">
  							 				
    									</div>
                                        	<div class="form-group">
                                                <div class="col-sm-12">
                                                    <h4><b>Bidder Info</b></h4>
                                                    <hr>
                                                </div>
                                            </div>
                                            <form id="bidderDetailForm">
                                            <input style="display: none;" id="bidderId">
                                             <input style="display: none;" id="partnerId">
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
		                                                        <label>No:</label>
		                                                         <span class="detspan" id="tenderNo"></span>
		                                                         
		                                                </div>
		                                           </div>
		                                           <div class="form-group">
		                                                <div class="col-sm-4">
		                                                        <label>Bidder Status:</label>
		                                                         <span class="detspan" id="bidderStatus"></span>
		                                                         
		                                                </div>
		                                              <div class="col-sm-4" id="allocatedQuantityDiv">
		                                                        <label>Allocated Quantity:</label>
		                                                         <span class="detspan" id="allocatedQty"></span>
		                                                         
		                                                </div>
		                                           </div>
		                                         
		                                        </form> 
                                            </div>
                                         <!--sub of field group 2  -->
                                         <div class="regularAuction">
                                          <div class="detailscont">
  							 				
    									</div>
                                         <div id="tabstrip3">
                                        <ul>
                                            <!-- tabs -->
                                           
                                            <li class="k-state-active priceBidTab" id="priceBidTab" onclick="resetCollapseData(); getPriceBid();">Price Bid</li>
                                            <li onclick="resetCollapseData(); getCommercialBid();">Commercial Bid</li>
                                            
                                        </ul>
                                        <!--sub of field group 1  -->
                                        	
                                        	<div>
                                        	<div class="form-group">
                                                <div class="col-sm-12">
                                                    <h4><b>Price Bids</b></h4>
                                                    <label  id="infolabelId"></label>
                                                    <hr>
                                                </div>
                                            </div>
                                            
                                            <div class="form-group">
	           	                                       <div class="col-sm-4">
	           	                                             <label>Price Bid (For Download):</label>
	           	                                             <a class="col-sm-12 filelabel" data-url="<%=request.getContextPath()%>/download" id="downloadPriceBid"></a> 
	           	                                             </div>
	           	                                       </div> 
                                              <div class="form-group">
                                              <div class="col-sm-12">
		                                             <div class="panel-group" id="accordion2">
													    <div class="panel panel-default bidpanel workscollapseaple">
													      <div onclick="getBids_PriceBid()" class="panel-heading" data-toggle="collapse" data-parent="#accordion2" href="#PriceFilledData">
													        <h4 class="panel-title">
													          <a data-toggle="collapse" data-parent="#accordion2" href="#PriceFilledData">Price Filled Data</a>
													        </h4>
													      </div>
													      <div id="PriceFilledData" class="panel-collapse collapse ">
													        <div class="panel-body bidpBody">
											                <form action="savePriceBid" id="savePriceBidForm">
								                                <div class="clearfix"></div>
								                                <div class="form-group">
								                                <div class="col-sm-3">
									                                    <label>Item Code<span class="red">*</span></label>
									                                    <div class="matCode"></div>
									                                    <span></span>
								                                </div>
								                                <div class="col-sm-3">
									                                    <label>Item Name<span class="red">*</span></label>
									                                    <div class="matName" ></div>
									                                    <span></span>
								                                </div>
								                                <div class="col-sm-3">
									                                    <label>Description Of Material<span class="red">*</span></label>
									                                    <div class="description"></div>
									                                    <span></span>
								                                </div>
								                                </div>
								                                <div class="clearfix"></div>
								                                <div class="form-group">
								                                <div class="col-sm-3">
								                                        <label>Required quantity<span class="red">*</span></label>
								                                        <div class="reqQuantity" ></div>
								                                        <span></span>
								                                </div>
								                                <div class="col-sm-3">
								                                		<label>Unit<span class="red">*</span></label>
								                                        <div class="uom"></div>
								                                        <span></span>
								                                </div>
								                                <div class="col-sm-3">
								                                		<label>HSN/SAC code<span class="red">*</span></label>
								                                        <div class="hsn"></div>
								                                        <span></span>
								                                </div>
								                                <div class="col-sm-3">
								                                    <div class="styled-input">
								                                        <input type="text"  id="offeredQuantity">
								                                        <label>Quantity Offered<span class="red">*</span></label>
								                                        <span></span>
								                                    </div>
								                                </div>
								                                </div>
								                                <div class="clearfix"></div>
								                                <div class="col-sm-3">
									                                 <div class="styled-input">
									                                         <input type="text" id="exGroupPriceRate" title="Per Unit Ex-Group Price Including Packaging But Excluding Excise duty and Taxes(In Rs)">
									                                         <label>Per Unit Ex-Works Price <span class="red">*</span></label>
									                                         <span></span>
									                                 </div>
								                                </div>
								                            	<div class="col-sm-3">                                                    
								                                    <div class="styled-input">
								                                        <input type="text" class="onlyNumber bom requiredField" id="freightChargeRate" title="Per Unit Freight Charges (in Rs.)" onchange="calculate()" name="priceBid.freightChargeRate" required="">
								                                        <label>Per Unit Freight charges(in Rs)</label>
								                                        <span></span>
								                                    </div>
								                                </div>    
								                                <div class="col-sm-3">
								                                    <div class="styled-input">
								                                        <input type="text" class="onlyNumber bom requiredField" id="ticRate" title="Per Unit Transit Insurance Charge(in Rs.)" onchange="calculate()" name="priceBid.ticRate" required="">
								                                        <label>Per Unit TIC(In Rs)<span class="red">*</span></label>
								                                        <span></span>
								                                    </div>
								                                </div>
								                                <div class="col-sm-3 readonly">
								                                    <div class="styled-input">
								                                        <input type="text" readonly="readonly" id="fddRate" title="Per unit Free Door Delivery Price Without GST(in Rs.)" name="priceBid.fddRate" title="Free Door Delivery Price in Rs per Unit on Road Upto Destination/Store/Sub-Station (in Rs)">
								                                        <label>Per unit FDD Price Without GST<span class="red">*</span></label>
								                                        <span></span>
								                                    </div>
								                                </div>
								                                <div class="col-sm-3 readonly">
								                                	<div class="styled-input">
								                                        <input type="text" readonly="readonly" id="totalExGroupPrice" title="Total Ex-Works Price (in Rs.)" name="priceBid.totalExGroupPrice" title="Per Unit Ex-Group Price Including Packaging But Excluding Excise duty and Taxes(In Rs)" required="">
								                                        <label>Total Ex-Works Price <span class="red">*</span></label>
								                                        <span></span>
								                                    </div>
								                                </div>
								                                <div class="col-sm-3 readonly">                                                    
								                                    <div class="styled-input">
								                                        <input type="text" readonly="readonly" id="totalFreightCharges" title="Total Freight Charge(in Rs.)" name="priceBid.totalFreightCharge" required="">
								                                        <label>Total Freight charges</label>
								                                        <span></span>
								                                    </div>
								                                </div>
								                                 <div class="col-sm-3 readonly">
								                                    <div class="styled-input">
								                                        <input type="text" readonly="readonly" id="totalTic" title="Total Transit Insurance Charge(in Rs.)" name="priceBid.totalTic" required="">
								                                        <label>Total TIC<span class="red">*</span></label>
								                                        <span></span>
								                                    </div>
								                                </div>
								                                <div class="col-sm-3 readonly">
								                                    <div class="styled-input">
								                                        <input type="text" readonly="readonly" title="" id="fddAmount" title="Total Free Door Delivery Price Without GST (in Rs.)" name="priceBid.fddAmount" required="" title="Free Door Delivery Price in Rs per Unit on Road Upto Destination/Store/Sub-Station (in Words)">
								                                        <label>Total FDD Price Without GST</label>
								                                        <span></span>
								                                    </div>
								                                </div>
								                                <div class="col-sm-3" id="taxRateDiv">
								                                    <div class="styled-input">
								                                        <input type="text" class="requiredField" id="taxRate" name="priceBid.taxRate" title="GST(in %)" onchange="onChangeGSTPriceBid()" > <!-- onchange="gstCalculate()" -->
								                                        <label>GST(in %)<span class="red">*</span></label>
								                                        <span></span>
								                                    </div>
								                                </div>
								                                <div class="col-sm-3" id="igstDiv">
								                                    <div class="styled-input">
								                                        <input type="text" class="" readonly="readonly" id="igstAmount" name="priceBid.igstAmount" title="IGST(in %)" >
								                                        <input type="hidden" class="" readonly="readonly" id="igst" name="priceBid.igst">
								                                        <label>IGST(<span id='igstLable'></span> %)<span class="red">*</span></label>
								                                        <span></span>
								                                    </div>
								                                </div>
								                                <div class="col-sm-3" id="cgstDiv">
								                                    <div class="styled-input">
								                                        <input type="text" class="" readonly="readonly" id="cgstAmount" name="priceBid.cgstAmount" title="CGST(in %)" >
								                                        <input type="hidden" class="" readonly="readonly" id="cgst" name="priceBid.cgst">
								                                        <label>CGST(<span id='cgstLable'></span> %)<span class="red">*</span></label>
								                                        <span></span>
								                                    </div>
								                                </div>
								                                <div class="col-sm-3" id="sgstDiv">
								                                    <div class="styled-input">
								                                        <input type="text" class="" readonly="readonly" id="sgstAmount" name="priceBid.sgstAmount" >
								                                        <input type="hidden" class="" readonly="readonly" id="sgst" name="priceBid.sgst">
								                                        <label>SGST(<span id='sgstLable'></span> %)<span class="red">*</span></label>
								                                        <span></span>
								                                    </div>
								                                </div>
								                                <div class="col-sm-3 readonly" id="perUnitGSTDiv">
								                                    <div class="styled-input">
								                                        <input type="text" readonly="readonly" id="taxAmount" name="priceBid.taxAmount" title="Per Unit GST(in Rs.)">
								                                        <label>Per Unit GST<span class="red">*</span></label>
								                                        <span></span>
								                                    </div>
								                                </div>
								                                <div class="col-sm-3 readonly" id="totalGSTDiv">
								                                    <div class="styled-input">
								                                        <input type="text" readonly="readonly" id="totalTax" name="priceBid.totalTax" title="Total GST(in Rs.)">
								                                        <label>Total GST<span class="red">*</span></label>
								                                        <span></span>
								                                    </div>
								                                </div>
								                                <div class="col-sm-3 readonly" id="fddRateWithGSTDiv">
								                                    <div class="styled-input">
								                                        <input type="text" readonly="readonly" id="fddRateGST" name="priceBid.fddRateWithGST" title="Free Door Delivery Price With GST (in Rs.) per Unit on Road Upto Destination/Store/Sub-Station">
								                                        <label>Per unit FDD With GST<span class="red">*</span></label>
								                                        <span></span>
								                                    </div>
								                                </div>
								                                <div class="col-sm-3 readonly" id="fddAmountWithGSTDiv">
								                                    <div class="styled-input">
								                                        <input type="text" readonly="readonly" title="" id="fddAmountGST" name="priceBid.fddAmountWithGST" required="" title="Total Free Door Delivery Price With GST (in Rs.) on Road Upto Destination/Store/Sub-Station">
								                                        <label>Total FDD With GST</label>
								                                        <span></span>
								                                    </div>
								                                </div>
								                                <div class="col-sm-3 readonly top20" id="fddAmountWithGSTInWordsDiv">
								                                	<label>FDD PER UNIT In Words</label>
								                                    <input type="hidden" class="readonly" name="priceBid.amountInWords" id="fddAmountWithGSTInWords">
								                                    <label class="detspan" id="fddAmountWithGSTInWordsLabel" title=""></label>
								                                </div>
								                          </form>
															</div>
													      </div>
													    </div>
													   
													  </div> 
													  </div>
		                                           </div>
		                                      <div id="partsPriceBidDiv">
		                                      <div class="form-group">
	           	                                       <div class="col-sm-4">
	           	                                             <label>Price Bid For Parts:</label>
	           	                                             </div>
	           	                                       </div> 
                                              <div class="form-group">
                                              <div class="col-sm-12">
		                                             <div class="panel-group" id="accordion22">
													    <div class="panel panel-default bidpanel">
													      <div id="PartsPriceDivId"  class="panel-heading" data-toggle="collapse" data-parent="#accordion22" href="#PartsPriceFilledData">
													        <h4 class="panel-title">
													          <a onclick="getBids_PartsPriceBid()" data-toggle="collapse" data-parent="#accordion22" href="#PartsPriceFilledData">Parts Price Filled Data</a>
													        </h4>
													      </div>
													      <div id="#PartsPriceFilledData" class="panel-collapse collapse ">
													        <div class="panel-body bidpBody">
											                <form action="" id="partsPriceBidForm">
								                                <div class="clearfix"></div>
								                                <div class="form-group">
								                                <div class="col-sm-3">
									                                    <label>Item Code<span class="red">*</span></label>
									                                    <div class="matCode"></div>
									                                    <span></span>
								                                </div>
								                                <div class="col-sm-3">
									                                    <label>Item Name<span class="red">*</span></label>
									                                    <div class="matName" ></div>
									                                    <span></span>
								                                </div>
								                                <div class="col-sm-3">
									                                    <label>Description Of Material<span class="red">*</span></label>
									                                    <div class="description"></div>
									                                    <span></span>
								                                </div>
								                                </div>
								                                <div class="clearfix"></div>
								                                <div class="form-group">
								                                <div class="col-sm-3">
								                                        <label>Required quantity<span class="red">*</span></label>
								                                        <div class="reqQuantity" ></div>
								                                        <span></span>
								                                </div>
								                                <div class="col-sm-3">
								                                		<label>Unit<span class="red">*</span></label>
								                                        <div class="uom"></div>
								                                        <span></span>
								                                </div>
								                                <div class="col-sm-3">
								                                		<label>HSN/SAC code<span class="red">*</span></label>
								                                        <div class="hsn"></div>
								                                        <span></span>
								                                </div>
								                                <div class="col-sm-3">
								                                    <div class="styled-input">
								                                        <input type="text"  id="offeredQuantity">
								                                        <label>Quantity Offered<span class="red">*</span></label>
								                                        <span></span>
								                                    </div>
								                                </div>
								                                </div>
								                                <div class="clearfix"></div>
								                                <div class="col-sm-3">
									                                 <div class="styled-input">
									                                         <input type="text" id="exGroupPriceRate" title="Per Unit Ex-Group Price Including Packaging But Excluding Excise duty and Taxes(In Rs)">
									                                         <label>Per Unit Ex-Works Price <span class="red">*</span></label>
									                                         <span></span>
									                                 </div>
								                                </div>
								                            	<div class="col-sm-3">                                                    
								                                    <div class="styled-input">
								                                        <input type="text" class="onlyNumber bom requiredField" id="freightChargeRate" title="Per Unit Freight Charges (in Rs.)" onchange="calculate()" name="priceBid.freightChargeRate" required="">
								                                        <label>Per Unit Freight charges(in Rs)</label>
								                                        <span></span>
								                                    </div>
								                                </div>    
								                                <div class="col-sm-3">
								                                    <div class="styled-input">
								                                        <input type="text" class="onlyNumber bom requiredField" id="ticRate" title="Per Unit Transit Insurance Charge(in Rs.)" onchange="calculate()" name="priceBid.ticRate" required="">
								                                        <label>Per Unit TIC(In Rs)<span class="red">*</span></label>
								                                        <span></span>
								                                    </div>
								                                </div>
								                                <div class="col-sm-3 readonly">
								                                    <div class="styled-input">
								                                        <input type="text" readonly="readonly" id="fddRate" title="Per unit Free Door Delivery Price Without GST(in Rs.)" name="priceBid.fddRate" title="Free Door Delivery Price in Rs per Unit on Road Upto Destination/Store/Sub-Station (in Rs)">
								                                        <label>Per unit FDD Price Without GST<span class="red">*</span></label>
								                                        <span></span>
								                                    </div>
								                                </div>
								                                <div class="col-sm-3 readonly">
								                                	<div class="styled-input">
								                                        <input type="text" readonly="readonly" id="totalExGroupPrice" title="Total Ex-Works Price (in Rs.)" name="priceBid.totalExGroupPrice" title="Per Unit Ex-Group Price Including Packaging But Excluding Excise duty and Taxes(In Rs)" required="">
								                                        <label>Total Ex-Works Price <span class="red">*</span></label>
								                                        <span></span>
								                                    </div>
								                                </div>
								                                <div class="col-sm-3 readonly">                                                    
								                                    <div class="styled-input">
								                                        <input type="text" readonly="readonly" id="totalFreightCharges" title="Total Freight Charge(in Rs.)" name="priceBid.totalFreightCharge" required="">
								                                        <label>Total Freight charges</label>
								                                        <span></span>
								                                    </div>
								                                </div>
								                                 <div class="col-sm-3 readonly">
								                                    <div class="styled-input">
								                                        <input type="text" readonly="readonly" id="totalTic" title="Total Transit Insurance Charge(in Rs.)" name="priceBid.totalTic" required="">
								                                        <label>Total TIC<span class="red">*</span></label>
								                                        <span></span>
								                                    </div>
								                                </div>
								                                <div class="col-sm-3 readonly">
								                                    <div class="styled-input">
								                                        <input type="text" readonly="readonly" title="" id="fddAmount" title="Total Free Door Delivery Price Without GST (in Rs.)" name="priceBid.fddAmount" required="" title="Free Door Delivery Price in Rs per Unit on Road Upto Destination/Store/Sub-Station (in Words)">
								                                        <label>Total FDD Price Without GST</label>
								                                        <span></span>
								                                    </div>
								                                </div>
								                                <div class="col-sm-3" id="taxRateDiv">
								                                    <div class="styled-input">
								                                        <input type="text" class="requiredField" id="taxRate" name="priceBid.taxRate" title="GST(in %)" onchange="onChangeGSTPriceBid()" > onchange="gstCalculate()"
								                                        <label>GST(in %)<span class="red">*</span></label>
								                                        <span></span>
								                                    </div>
								                                </div>
								                                <div class="col-sm-3" id="igstDiv">
								                                    <div class="styled-input">
								                                        <input type="text" class="" readonly="readonly" id="igstAmount" name="priceBid.igstAmount" title="IGST(in %)" >
								                                        <input type="hidden" class="" readonly="readonly" id="igst" name="priceBid.igst">
								                                        <label>IGST(<span id='igstLable'></span> %)<span class="red">*</span></label>
								                                        <span></span>
								                                    </div>
								                                </div>
								                                <div class="col-sm-3" id="cgstDiv">
								                                    <div class="styled-input">
								                                        <input type="text" class="" readonly="readonly" id="cgstAmount" name="priceBid.cgstAmount" title="CGST(in %)" >
								                                        <input type="hidden" class="" readonly="readonly" id="cgst" name="priceBid.cgst">
								                                        <label>CGST(<span id='cgstLable'></span> %)<span class="red">*</span></label>
								                                        <span></span>
								                                    </div>
								                                </div>
								                                <div class="col-sm-3" id="sgstDiv">
								                                    <div class="styled-input">
								                                        <input type="text" class="" readonly="readonly" id="sgstAmount" name="priceBid.sgstAmount" >
								                                        <input type="hidden" class="" readonly="readonly" id="sgst" name="priceBid.sgst">
								                                        <label>SGST(<span id='sgstLable'></span> %)<span class="red">*</span></label>
								                                        <span></span>
								                                    </div>
								                                </div>
								                                <div class="col-sm-3 readonly" id="perUnitGSTDiv">
								                                    <div class="styled-input">
								                                        <input type="text" readonly="readonly" id="taxAmount" name="priceBid.taxAmount" title="Per Unit GST(in Rs.)">
								                                        <label>Per Unit GST<span class="red">*</span></label>
								                                        <span></span>
								                                    </div>
								                                </div>
								                                <div class="col-sm-3 readonly" id="totalGSTDiv">
								                                    <div class="styled-input">
								                                        <input type="text" readonly="readonly" id="totalTax" name="priceBid.totalTax" title="Total GST(in Rs.)">
								                                        <label>Total GST<span class="red">*</span></label>
								                                        <span></span>
								                                    </div>
								                                </div>
								                                <div class="col-sm-3 readonly" id="fddRateWithGSTDiv">
								                                    <div class="styled-input">
								                                        <input type="text" readonly="readonly" id="fddRateGST" name="priceBid.fddRateWithGST" title="Free Door Delivery Price With GST (in Rs.) per Unit on Road Upto Destination/Store/Sub-Station">
								                                        <label>Per unit FDD With GST<span class="red">*</span></label>
								                                        <span></span>
								                                    </div>
								                                </div>
								                                <div class="col-sm-3 readonly" id="fddAmountWithGSTDiv">
								                                    <div class="styled-input">
								                                        <input type="text" readonly="readonly" title="" id="fddAmountGST" name="priceBid.fddAmountWithGST" required="" title="Total Free Door Delivery Price With GST (in Rs.) on Road Upto Destination/Store/Sub-Station">
								                                        <label>Total FDD With GST</label>
								                                        <span></span>
								                                    </div>
								                                </div>
								                                <div class="col-sm-3 readonly top20" id="fddAmountWithGSTInWordsDiv">
								                                	<label>FDD PER UNIT In Words</label>
								                                    <input type="hidden" class="readonly" name="priceBid.amountInWords" id="fddAmountWithGSTInWords">
								                                    <label class="detspan" id="fddAmountWithGSTInWordsLabel" title=""></label>
								                                </div>
								                          </form>
															</div>
													      </div>
													    </div>
													   
													  </div> 
													  </div>
		                                           </div>
		                                           </div>
		                                           
                                        	</div>
                                        	<div>
                                        	<div class="form-group">
                                                <div class="col-sm-12">
                                                    <h4><b>Commercial Bids</b></h4>
                                                    <label  id="infolabelId"></label>
                                                    <hr>
                                                </div>
                                            </div>
                                            <div class="form-group">
	           	                                       <div class="col-sm-4">
	           	                                             <label>Commercial Bid (For Download):</label>
	           	                                             <a class="col-sm-12 filelabel" data-url="<%=request.getContextPath()%>/download" id="downloadCommercialBid"></a> 
	           	                                             </div>
	           	                                       </div> 
                                              <div class="form-group">
                                              <div class="col-sm-12">
		                                             <div class="panel-group" id="accordion3">
													    <div class="panel panel-default bidpanel">
													      <div onclick="getBids_DeliverDetail()" class="panel-heading" data-toggle="collapse" data-parent="#accordion3" href="#collapse1">
													        <h4 class="panel-title">
													          <a data-toggle="collapse" data-parent="#accordion3" href="#collapse1">Delivery Details</a>
													        </h4>
													      </div>
													      <div id="collapse1" class="panel-collapse collapse ">
													        <div class="panel-body bidpBody">
															<form  id="saveCommercialBidForm">
												             <input type="hidden" class="bidderId" name="bidder.bidderId" />
												             <input type="hidden" class="tahdrDetailId" name="tahdrDetail.tahdrDetailId" />
												           	 <input type="hidden" class="commercialBidId" name="commercialBidId" />
													             <div class="form-group">
													             	
													                <label class="col-sm-2 labeltopmrg">First lot of </label>
													                	<div class="col-sm-2">
													                		<input type="text" id="firstLot" name="firstLot" class="form-control requiredField onlyNumber leftmargin" required="">
													                    </div>
													                    <label class="col-sm-4 labeltopmrg" >assorted sizes will be delivered within</label> 
																		<div class="col-sm-2">
													                			<input type="text" id="deliveringMonth" name="deliveringMonth"  class="form-control requiredField readonly onlyNumber" required="">
													                    </div>
													                     <label class="col-sm-4 labeltopmrg">Months from the date of <span style="color:red;" id='referenceField'></span>.</label>
													                     <br><br>
													                     <label class="col-sm-6 labeltopmrg"> After This Period Supply will be completed at the rate of </label>
													                     <div class="col-sm-2">
													                			<input type="text" id="ratePerMonth" name="ratePerMonth" class="form-control requiredField onlyNumber" required="">
													                    </div>
													                    <label class="col-sm-3 labeltopmrg">  in assorted sized per month</label>
													             </div>
												                <h4><b>Confirmation Details</b></h4>
																	<p>We Confirm The Following :
																	</p>
																	<p>
																		I) Goods and Services Tax(GST) i.e <span class="interState">Integrated GST</span><span class="intraState">(Central GST+ State GST)</span>:									
																	</p>
																	<p>The GST is included in our prices quoted in price bid <span class="intraState">(Central GST+ State GST)
																	 for within Maharashtra State</span><span class="interState">Integrated GST for outside State</span> and we shall not charge 
																	 any additional amount towards<span class="interState"> Integrated GST</span><span class="intraState">(Central GST+ State GST)</span>, during 
																	 currency of contract except statutory variation by Central / State Government in 
																	 normal (full) rate of <span class="interState">Integrated GST</span><span class="intraState">(Central GST+ State GST)</span>, in case of 
																	 <span class="interState"> Integrated GST</span><span class="intraState">(Central GST+ State GST)</span> Rate is increased. In case the <span class="interState">Integrated 
																	 GST</span><span class="intraState">(Central GST+ State GST) </span>is decreased than the rate indicated in the price 
																	 bid, the benefits of the reduction in the <span class="interState">Integrated GST </span><span class="intraState">(Central GST+ State GST) </span> 
																	 shall be passed on to the Purchaser.The increase in the <span class="interState">Integrated GST </span><span class="intraState">(Central 
																	 GST+ State GST) </span>rate due to increase in turnover during the contractual delivery 
																	 period shall not be charged to the Purchaser .If the <span class="interState">Integrated GST </span><span class="intraState">(Central GST+ 
																	 State GST) </span>is not payable at present,we shall not charge the same, if it becomes 
																	 applicable during the currency of contract due to expiry / withdrawal of tax 
																	 concessions and incentives during the currency of contract except for statutory 
																	 variation by Central / State Government.
																	</p>
																	<p>
																		(i) Necessary documentary evidence for the GST claimed by us shall be 
																		submitted along with the bills.									
																	</p>
																	<p>
																		(ii) We here by declare that while quoting the price in the Price Bid, we 
																		have taken into account the entire credit on inputs available under the GST 
																		Act.									
																	</p>
												                
												                <div class="form-group">
												                    <div class="col-sm-4" id="gstCBDiv">
												                        <div class="styled-input">
												                            <input type="text" onchange="onDeliverGST()" id="gstCB" name="gst" class="requiredField onlyNumber" required />
												                            <label>Normal (full) rate of tax <span id="taxLabel"></span> (in %)<span class="red">*</span></label>
												                            <span></span>
												                        </div>
												                    </div>
												                    <div class="col-sm-4" id="igstCBDiv">
												                        <div class="styled-input">
												                            <input type="text" id="igstCB" readonly="readonly" name="igst" required />
												                            <label>IGST (in %)<span class="red">*</span></label>
												                            <span></span>
												                        </div>
												                    </div>
												                    <div class="col-sm-4" id="cgstCBDiv">
												                        <div class="styled-input">
												                            <input type="text" id="cgstCB" readonly="readonly" name="cgst" required />
												                            <label>CGST (in %)<span class="red">*</span></label>
												                            <span></span>
												                        </div>
												                    </div>
												                    <div class="col-sm-4" id="sgstCBDiv">
												                        <div class="styled-input">
												                            <input type="text" id="sgstCB" readonly="readonly" name="sgst" required />
												                            <label>SGST (in %)<span class="red">*</span></label>
												                            <span></span>
												                        </div>
												                    </div>
												                </div>
											            </form>	
															</div>
													      </div>
													    </div>
													   
													   
													  </div> 
													  </div>
		                                           </div>
                                        	</div>
                                        	</div>
                                         	
		                                                
                                         </div>
									
                                        	<div class="quickAuction">
									<div class="detailscont">
  							 				
    									    </div>
                                        	<div class="form-group">
                                                <div class="col-sm-12">
                                                    <h4><b>Proposals</b></h4>
                                                    <hr>
                                                </div>
                                                <div class="col-sm-12">
												            <table class="table table-striped tableResponsive table-bordered" style="width:100% !important;" id="price_Bid_Table">
				                                               <thead>
				                                                   <tr>
				                                                    <th>Material Name</th>
													         		<th>Qty. Offered</th>
													         		<th>Per Unit Ex-Works Price</th>
													         		<th>Per Unit Freight charges(in Rs)</th>
													         		<th>Per Unit TIC(In Rs)</th>
													         		<th>Per unit FDD Price Without GST</th>
													         		<th>Total Ex-Works Price</th>
													         		<th>Total Freight charges</th>
													         		<th>Total TIC</th>
													         		<th>Total FDD Price Without GST</th>
													         		<th>Total GST</th>
													         		<th>Per unit FDD With GST</th>
													         		<th>Total FDD With GST</th>
													         		<th>Total FDD With GST</th>
													         		<th>Total FDD With GST</th>
													         		<th>Total FDD With GST</th>
													         		<th>Total FDD With GST</th>
				                                                   </tr>
				                                               </thead>
				                                               <tbody>                                            
				                                               </tbody>
                                           					</table>
                                           					</div>
                                            </div>
                                            
                                              <div class="form-group">
                                              <div class="col-sm-12">
		                                        
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
<!-- <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script> -->
  <script src="<%=request.getContextPath()%>/resources/${appMode}/tilescommon/js/jspdf.min.js?appVer=${appVer}"></script>
  <script src="<%=request.getContextPath()%>/resources/${appMode}/tilescommon/js/jspdf.plugin.autotable.min.js?appVer=${appVer}"></script>
 <script src="<%=request.getContextPath()%>/resources/${appMode}/commons/js/tableHTMLExport.js"></script>
<script src="<%=request.getContextPath()%>/resources/${appMode}/transaction/js/newTenderBids.js?appVer=${appVer}"></script>
<script src="<%=request.getContextPath()%>/resources/${appMode}/transaction/js/newTenderBids_BidsTab.js?appVer=${appVer}"></script>
<script src="<%=request.getContextPath()%>/resources/${appMode}/transaction/js/newTenderBids_QuickBids.js?appVer=${appVer}"></script>
<script src="<%=request.getContextPath()%>/resources/${appMode}/transaction/js/bidHistory.js?appVer=${appVer}"></script>
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
		$('.tableResponsive').DataTable();
	        
});
</script>
<script>
 $(function () {
		    	
		        $('#datetimepicker6').datetimepicker();
		        $('#datetimepicker7').datetimepicker({
		            useCurrent: false //Important! See issue #1075
		        });
		        $("#datetimepicker6").on("dp.change", function (e) {
		            $('#datetimepicker7').data("DateTimePicker").minDate(e.date);
		        });
		        $("#datetimepicker7").on("dp.change", function (e) {
		            $('#datetimepicker6').data("DateTimePicker").maxDate(e.date);
		        });
		    });
</script>