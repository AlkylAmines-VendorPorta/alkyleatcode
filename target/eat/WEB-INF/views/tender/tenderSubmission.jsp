<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@taglib uri = "http://www.springframework.org/tags/form" prefix = "sf"%>
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/${appMode}/tilescommon/css/fioristyle.css?appVer=${appVer}"/>
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/${appMode}/tilescommon/css//borderTab.css?appVer=${appVer}">
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/${appMode}/tilescommon/css/mobile.css?appVer=${appVer}">
 <script src="<%=request.getContextPath()%>/resources/${appMode}/tilescommon/js/kendo.all.min.js?appVer=${appVer}"></script>
<style>
.form-inline .form-control {
    width: 100%;
}
td > .form-control {
    width: auto !important;
}
</style>
            <script>
                $(document).ready(function() {
                    $("#tabstrip").kendoTabStrip();
                    $("#tabstrip2").kendoTabStrip();
                    $("#tabstrip3").kendoTabStrip();
                    $("#tabstrip4").kendoTabStrip();
                    $("#tabstrip5").kendoTabStrip();

               
                	/* $('#leftPane').paginathing({perPage: 6}); */
                });
                </script>
          
    
        <div class="full-container">
            <!-- left-side start-->
            <div class="clearfix"></div>
            <input type="hidden" id="myTenderUrl" class="myTenderUrl" value="${myTenderUrl}" />
            <input type="hidden" value="${documentType }" id="documentType"/>
            <div id="mobile_first_container" class="left-side col-md-3 no-marg">
            <div class="detailsheader leftPaneHeader">
        		
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
                    <input type="text"  id='searchLiteralId' class="form-control" name="x" placeholder="Search term...">
                    <span class="input-group-btn">
	                    <button class="btn btn-default" type="button">
	                    	<span class="glyphicon glyphicon-search"></span>
	                    </button>
	                    <button class="btn btn-default addnewlist" type="button">
							<span class="glyphicon glyphicon-plus"></span>
						</button>
						<button class="btn btn-default" onClick="loadNewList(event)"type="button">
							<span class="glyphicon glyphicon-refresh"></span>
						</button>
                    </span>
                </div>
                <c:if test="${documentType.equalsIgnoreCase('Tender')}">
                <div class="btn-group btnmrg" data-toggle="buttons">
				    <label class="btn btn-primary toggleNewTab" id="labelProcurement" openTab="tahdrDetailsTab">
						<input type="radio" name="toggleTenderType" id="procurement" value="PT">
						<input type="hidden" id="fileSizeForPT" value="${fileSizeForPT}"/>
						<input type="hidden" id="fileSize" />
						<span class="glyphicon glyphicon-ok"></span> Procurement 
                    </label>
                    <label class="btn btn-primary toggleNewTab" id="labelWorks" openTab="tahdrDetailsTab">
                         <input type="radio" name="toggleTenderType" id="works" checked value="WT">
                         <input type="hidden" id="fileSizeForWT" value="${fileSizeForWT}"/>
                         <input type="hidden" id="fileSize" />
                          <span class="glyphicon glyphicon-ok"></span> Works
                    </label>
                </div>
                </c:if>
				<c:if test="${documentType.equalsIgnoreCase('Auction')}">
					<input type="radio" name="toggleTenderType" checked style="display:none;" value="FA" >
				</c:if>
                <ul id="leftPane" class="nav nav-tabs tabs-left leftPane">
                   
                </ul>                
                <div class="clearfix"></div>
            </div>
            <!-- left-side end-->

            <!-- right-side start-->
            <div id="mobile_second_container" class="right-side col-md-9 no-marg">
            <div class="detailsheader toptabbrd">
        		<div class="col-sm-9 text-center"><label>${documentType} Submission Details</label></div>
        	 </div>
                <div class="clearfix"></div>
                <div class="tab-content">
                    <!-- Master tab start-->

                    <div class="tab-pane active in" id="">
                        <div class="card">
                            <div class="posrelative">
                              <div class="demo-section k-content">
                                    <div id="tabstrip" class="Firsttab">
                                        <ul id="tabUL">
                                            <!-- tabs -->
                                            <li data-parentTab="" id="tahdrDetailsTab" class="k-state-active">${documentType} Details</li>
                                            <li class="tahdrTab" data-parentTab="tahdrDetailsTab" id="emdPaymentTab">EMD Payment Details</li>
                                            <li class="tahdrTab" data-parentTab="tahdrDetailsTab" id="deliveryDetailTab" onclick="onClickCommercialBid();">Commercial Details</li>
                                            <li class="tahdrTab toggleTab" opentab="itemDetails_Tab" data-parentTab="tahdrDetailsTab" id="bidTab">Bids</li>
                                            <li class="tahdrTab" data-parentTab="tahdrDetailsTab" id="submitItemBidTab">Bid Submission</li>
                                        </ul>

                                        <!--fields of field group 1  -->
                                        <div id="tahdrDetailsDiv">
                                        <div class="detailscont">
  							 				
    									</div>
                                            <div class="form-group">
                                                <div class="col-sm-12">
                                                    <h4><b>${documentType} Details</b></h4>
                                                    <hr>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <div class="col-sm-6">
                                                    <button class="btn btn-default" id="tahdrDoc" onclick="downloadTahdrDetailDoc();">View ${documentType} Details</button>
                                                    <span id="generatedDoc">
												
													</span>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                            <div class="col-sm-12">
                                                    <table class="table table-bordered tableResponsive" id="tahdrmaterial">
                                                        <thead>
                                                            <tr>
                                                                <th class="col-sm-3">Material</th>
                                                                <th class="col-sm-1">UOM</th>
                                                                <th class="col-sm-1">Quantity</th>
                                                                <th class="col-sm-3">Description</th>
                                                                <th class="col-sm-2">Specification</th>
                                                                <th class="col-sm-2">Material Type</th>
                                                            </tr>
                                                        </thead>
                                                        <tbody>
                                                                                                         
                                                        </tbody>
                                                    </table>
                                                    </div>
                                                </div>
                                                <!-- <div class="form-group">
	                                                <div class="col-sm-4">
	                                                	<input type="hidden" class="tahdrDetailId">
	                                                	<button class="btn btn-default" id="emdDetail">EMD Payment</button>
	                                                    <button class="btn btn-default" id="fillBids">Bids</button>
	                                                </div>
	                                            </div> -->
	                                            <div class="clearfix"></div>
	                                            <div class="col-sm-12 text-center positionABS" style="margin-bottom: 10px;">
											<button class="btn-all btn btn-info k-tabstrip-next pull-right tab-li-next">Next</button>
										</div>
                                        </div>
                                        <!--fields of field group 1  -->
                                        <!--fields of field group 2  -->
                                        <div id="emdPaymentDiv">
                                        <div class="detailscont">
  							 				
    									</div>
                                            <div class="form-group">
                                                <div class="col-sm-12">
                                                    <h4><b>EMD Payment Details</b></h4>
                                                    <hr>
                                                </div>
                                                 <div id="paymentStatusDiv" class="col-xs-6 errorlabel" style="margin-top: 10px; display: none;">
												<label id="paymentStatusLabel" style="color: red"></label>
												</div>
                                            </div>
                                        <sf:form id="emdPaymentDetailForm" modelAttribute="paymentDetail" method="POST" action="tahdrDocPurchase">
                                        	<input id="tahdrId" type="hidden" name="tahdr.tahdrId">
                                        	<input id="tahdrDetailId" type="hidden" name="tahdrDetail.tahdrDetailId">
                                   			<input id="paymentDetailId" type="hidden" name="paymentDetailId">
                                       		<input type="hidden" name="isActive" value="Y" />
                                       		<div class="form-group">
                                       		<div class="col-sm-4 readonly">
                                                <div class="styled-input">
                                                    <input type="text" id="tahdrCode" required />
                                                    <label>${documentType} Number<span class="red">*</span></label>
                                                    <span></span>
                                                </div>
                                            </div>
                                            <div class="col-sm-4 readonly">
                                                <div class="styled-input">
                                                    <input type="text" id="amount" required />
                                                    <label>Amount<span class="red">*</span></label>
                                                    <span></span>
                                                </div>
                                            </div>
                                            <!-- <div class="col-sm-4 readonly">
                                                <div class="styled-input">
                                                    <input type="text" id="gst" name="gst" required />
                                                    <label>GST <span id="gstRate"></span><span>%</span><span class="red">*</span></label>
                                                    <span></span>
                                                </div>
                                            </div> -->
                                            <!-- <div class="col-sm-4 readonly">
                                                <div class="styled-input">
                                                    <input type="text" id="totalFee" required />
                                                    <label>Total<span class="red">*</span></label>
                                                    <span></span>
                                                </div>
                                            </div> -->
                                            <div class="col-sm-4 readonly">
                                               <div class="styled-input">
                                                 <select id="paymentType" name="paymentType.paymentTypeId" class="requiredField">
                                                 </select>
                                                 <label>Type of Payment<span class="red">*</span></label>
                                                 <span></span>
                                               </div>
                                            </div>
                                            </div>
                                            <div class="clearfix"></div>
                                            <div class="form-group">
                                            <div class="col-sm-4">
                                              <div class="styled-input">
                                                 <select id="paymentMode" class="paymentMode" name="paymentMode" onchange="formFieldChange(this)" required="required">
                                                 </select>
                                                 <label>Mode Of Payment<span class="red">*</span></label>
                                                 <span></span>
                                              </div>
                                            </div>
                                            <div class="col-sm-4"  >
                                              <div class="form-group" id="paymentDateDiv">
                                                 <label id="paymentDate">Payment Date</label>
                                                 <div class="input-group date" data-provide="datepicker">
                                                     <input type="text" class="form-control requiredField" id="dateOfPayment" name="paymentDate" >
                                                     <div class="input-group-addon">
                                                         <span class="glyphicon glyphicon-th"></span>
                                                     </div>
                                                 </div>
                                              </div>
	                                        </div>
	                                        <div class="col-sm-4"  >
                                              <div class="form-group" id="validityDateDiv">
                                                 <label id="validityDate">Validity Date</label>
                                                 <div class="input-group date Pickdate" data-provide="datepicker">
                                                     <input type="text" class="form-control requiredField" id="dateOfValidity" name="validityDate" >
                                                     <div class="input-group-addon">
                                                         <span class="glyphicon glyphicon-th"></span>
                                                     </div>
                                                 </div>
                                              </div>
	                                        </div>
	                                        <div class="col-sm-4">
	                                           <div class="styled-input" id="referenceNoDiv">
                                                 <input type="text" id="referenceNo" name="referenceNo" class="requiredField" />
                                                 <label id="paymentNo">Demand Draft Number<span class="red">*</span></label>
                                                 <span></span>
                                           	   </div>
                                            </div>
                                           <!--  <div class="clearfix"></div> -->
                                          	<div class="col-sm-4" id="micrCodeDivId">
	                                           <div class="styled-input" >
	                                              <input type="text" id="micrCode" name="micrCode" class="requiredField onlyNumber" maxlength="9" />
	                                              <label id="paymentRefNo">MICR Code<span class="red">*</span></label>
	                                              <span></span>
	                                           </div>
                                          	</div>
											<div class="col-sm-4">
                                                <div class="styled-input" id="bankNameDivId">
                                                    <input type="text" id="bankName" name="bankName" class="requiredField" />
                                                    <label id="bankNamelbl">Bank Name<span class="red">*</span></label>
                                                    <span></span>
                                                </div>
                                            </div>
                                            <div class="col-sm-4">
                                                <div class="styled-input" id="branchNameDivId">
                                                    <input type="text" id="branchName" name="branchName" class="requiredField" />
                                                    <label id="branchNamelbl">Branch Name<span class="red">*</span></label>
                                                    <span></span>
                                                </div>
                                            </div>
                                            <div class="col-sm-4" id="bankGuaranteeAttachment">
												<label>Attachment<span class="red">*</span></label>
												<div class="input-group">
													<input type="file" id="BGId" data-id="BGAttachment" data-name="BGAttach" data-anchor="a_bgAttachment" class="form-control uploadFile requiredFile"> 
													<input type="hidden" id="BGAttachment" name="bankGuaranteeAttachment.attachmentId" class="form-control" /> 
													<span class="input-group-btn">
														<button class="btn btn-default BGAttachment"
															onclick="return submitWithParam('deleteAttachment','BGAttachment','BGDeleteResp');"
															disabled="disabled">
															<i class="fa fa-times"></i>
														</button>
													</span>
												</div>
												<a class="col-sm-12 filelabel"
													data-url="<%=request.getContextPath()%>/download"
													id="a_bgAttachment"></a>
											</div>
                                            </div>
                                           </sf:form>
                                           <div class="col-sm-12 text-center positionABS" style="margin-bottom:10px;">
		                                        <!-- <button class="btn-all btn btn-info k-tabstrip-prev tab-li-prev pull-left">Previous</button> -->
		                                          <div class="col-sm-2" id='payReceipt'>
                                             	 	<a id="payReceiptLinkId" target="_blank"><b><span class="glyphicon glyphicon-plus-sign paddright"></span>Payment Receipt</b> </a>
                                             	 </div> 
                                             	 
		                                        <button class="btn btn-info tahdrEMDPayment save rbschClass">Save</button>
		                                        <!-- <button id="cancelBtn" class="btn-all btn btn-info cancel">Cancel</button> -->
		                                        <button class="btn btn-default backToTenders">Back To ${documentType}s</button>
		                                        <!-- <button class="btn-all btn btn-info k-tabstrip-prev tab-li-next pull-right" onclick="getBids">Next</button> -->
		                                    </div>
		                                    <form class="col-sm-6 text-right" id="onlineTenderFeeForm" action="onlineEmdPayment" method = "post">
                                        			<input id="tahdrDetailId" type="hidden" name="tahdrDetail.tahdrDetailId">
	                                           		<input class = "tahdrId" type="hidden" name="tahdr.tahdrId">
	                                           		
	                                           		<input type = "hidden" name="paymentMode" value = "OP">
	                                           		<input type="hidden" name="${_csrf.parameterName}"  value="${_csrf.token}" />
	                                           		<button type="submit" class="btn btn-info save">Pay Online</button>
	                                           </form>
		                                    <div class="form-group">
	                                            <div class="col-sm-12">
	                                                <table class="table tableResponsive table-bordered" id="emdExemptionTable">
	                                                    <thead>
	                                                        <tr>
	                                                        	<th class="col-sm-3">Item Code</th>
	                                                            <th class="col-sm-3">Item</th>
	                                                            <th class="col-sm-3">Exemption Demanded</th>
	                                                            <th class="col-sm-3">Exemption Approved</th>
	                                                        </tr>
	                                                    </thead>
	                                                    <tbody>
	                                                                                                     
	                                                    </tbody>
	                                                </table>
	                                            </div>
                                        	</div>  
                                        	<div class="col-sm-12 text-center positionABS" style="margin-bottom: 10px;">
											<button class="btn-all btn btn-info k-tabstrip-prev pull-left tab-li-prev">Previous</button>
											<button class="btn-all btn btn-info k-tabstrip-next pull-right tab-li-next">Next</button>
										</div>                                           
                                        </div>
                                        
                                        <!-- Fields group 2 -->
                                        <!-- Fields group 3 -->
                                        <div>
         <div class="detailscont">
			</div>
          <div class="col-sm-12">
               	<ul class="nav nav-pills col-md-12 comBidTab">
					<li id="deliveryDetail" class="active" data-placement="right" data-toggle="tooltip" title="Delivery Details" ><a href="#tab_h" data-toggle="pill"><i class="fa fa-gavel"></i></a><label>Delivery Details</label></li>
				  	<!-- <li id="confirmationTab" data-toggle="tooltip" data-placement="right" title="Confirmation" ><a href="#tab_j" data-toggle="pill"><i class="fa fa-check-circle-o"></i></a><label>Confirmation Details</label></li> -->
				  	<li id="commercialDocumentTab" class="commercialDocsClass" data-toggle="tooltip" data-placement="right" title="Documents" ><a href="#tab_i" data-toggle="pill"><i class="fa fa-file-text"></i></a><label>Commercial Documents</label></li>
				</ul>
				<div class="tab-content col-md-12">
					<div class="tab-pane active" id="tab_h">
						<sf:form action="saveCommercialBid" id="saveCommercialBidForm" modelAttribute="commercialBid">
				             <div class="form-group">
				             	<div class="col-sm-12">
				                	<h4><b>Delivery Details</b></h4>
				                    <hr>
				                </div>
				             </div>
				             <div class="clearfix"></div>
				             <div class="form-group">
                            <label class="col-sm-12"><span style="color:red;">Note : </span> Any changes made in Delivery Details will change bid submission status to drafted </label>
                            </div>
                            <div class="clearfix"></div>
				             <input type="hidden" class="bidderId" name="bidder.bidderId" />
				             <input type="hidden" class="tahdrDetailId" name="tahdrDetail.tahdrDetailId" />
				           	 <input type="hidden" class="commercialBidId" name="commercialBidId" />
					             <div class="form-group">
					             	<!-- <div class="col-sm-4">
					                	<div class="styled-input">
					                    	<input type="text" id="firstLot" name="commercialBid.firstLot">
					                        <label>First Lot<span class="red">*</span></label>
					                        <span></span>
					                    </div>
					                </div>
					                <div class="col-sm-6">
					                	<div class="styled-input">
					                    	<input type="text" id="deliveringMonth" name="commercialBid.deliveringMonth" required="">
					                        <label>First Delivering Month From Letter of Award<span class="red">*</span></label>
					                        <span></span>
					                    </div>
					                </div> -->
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
				                <!-- <div class="form-group">
				                    <div class="col-sm-4">
				                        <div class="styled-input">
				                            <input type="text" id="ratePerMonth" name="commercialBid.ratePerMonth" required="">
				                            <label>Rate Of Assorted Size per Month <span class="red">*</span></label>After Completion of Period Supply
				                            <span></span>
				                        </div>
				                    </div>
				                </div> -->
				                <div class="col-sm-12">
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
				            </div>    
				                <div class="form-group">
				                	<!-- <div class="col-sm-4">
				                        <div class="styled-input">
				                            <select id="tax" name="commercialBid.tax.taxId" required>
				                            	
				                            </select>
				                            <label>Normal (full) rate of tax<span class="red">*</span></label>
				                            <span></span>
				                        </div>
				                    </div> -->
				                    <!-- <div class="col-sm-4">
				                        <div class="styled-input">
				                            <input type="text" id="exciseDutyRate" name="commercialBid.exciseDutyRate" required />
				                            <label>GST (in %)<span class="red">*</span></label>
				                            <span></span>
				                        </div>
				                    </div>
				                    <div class="col-sm-4" style="display:none;">
				                        <div class="styled-input">
				                            <input type="text" id="exciseDuty" name="commercialBid.exciseDuty" required disabled="disabled" />
				                            <label>GST Amount<span class="red">*</span></label>
				                            <span></span>
				                        </div>
				                    </div> -->
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
				                 <div class="form-group">
				                    <div class="col-sm-12">
				                        <button class="btn btn-default rbschClass" id="saveCommercialBidBtn">Save</button>
				                        <button class="btn btn-default cancel rbschClass" id="cancelCommercialBid">cancel</button>
				                        <button class="btn btn-default backToTenders">Back To ${documentType}s</button>
				                    </div>
				                </div>           
			            </sf:form>
								<!-- <div class="col-sm-12 text-center positionABS" style="margin-bottom: 10px;">
											<button class="btn-all btn btn-info k-tabstrip-prev pull-left tab-li-prev">Previous</button>
											<button class="btn-all btn btn-info k-tabstrip-next pull-right tab-li-next">Next</button>
								</div> -->			            
					</div>
					
					<div class="commercialDocsClass tab-pane" id="tab_i">
						<sf:form id="saveCommercialSecDocForm" modelAttribute="commercialBid">
				        	<input type='hidden' class='bidderId' name="bidder.bidderId">
							<input type='hidden' class='commercialBidId' name="commercialBidId">
				            <input type="hidden" class="tahdrDetailId" name="tahdrDetail.tahdrDetailId" />
				            <h4>Additional Required Commercial Documents</h4>
				            <div class="clearfix"></div>
				            <div class="form-group">
                            <label><span style="color:red;">Note : </span> Any changes made in Required Commercial Documents  will change bid submission status to drafted </label>
                            </div>
                            <div class="clearfix"></div>
				            <table id="cbDocumentTable" class="table tableResponsive table-bordered">
					            <thead>
					            	<tr>
					            	<th class='col-sm-3'>Document Name</th>
					            	<th class='col-sm-3'>Description</th>
					            	<th class='col-sm-3'>Attachment</th>
					            	<th class='col-sm-3'>File Name</th>
					            	</tr>
					            </thead>
				            <tbody>
				            </tbody>
				            </table>
				            <div class="clearfix"></div>
				            <div class="form-group">
				               <div class="col-sm-12">
				                   <button class="btn btn-default save rbschClass" id="saveCommercialDocBtn">Save</button>
				                   <!-- <button class="btn btn-default cancel" id="cancelCommercialDocBtn">cancel</button> -->
				                   <button class="btn btn-default backToTenders">Back To ${documentType}s</button>
				               </div>
				           </div>  
				        </sf:form>
				        <div class="col-sm-12 text-center positionABS" style="margin-bottom: 10px;">
											<button class="btn-all btn btn-info k-tabstrip-prev pull-left tab-li-prev">Previous</button>
											<button class="btn-all btn btn-info k-tabstrip-next pull-right tab-li-next">Next</button>
								</div>
					</div>
				</div>
			</div>
		</div>
                                        <div id="bidDiv">
                                        <div class="detailscont">
    									</div>
                                         <div id="tabstrip2">
                                                <ul>
                                                	<li class="itemDetails_Tab" data-parentTab="tahdrDetailsTab" id="itemDetailsTab" onclick="getBids();" class="k-state-active">Item Details</li>
                                                   	<%-- <c:if test="${documentType.equalsIgnoreCase('Tender')}"> --%> 
                                                   		<li data-parentTab="itemDetailsTab" id="technicalBidTab" class="technicalBidClass" onclick="getTechnicalBid()">Technical Bid</li>
                                                   	<%-- </c:if> --%>
                                                    <li data-parentTab="itemDetailsTab" id="priceBidTab" onclick="onClickPriceBid();">Price Bid</li>
                                                </ul>
									<!--tab inside tab fields 1  -->
                                 <div id="itemDetailsDiv">
                                 <br>
                                 <div class="form-group">
                                       	 <div class="col-sm-12">
                               		<div class="col-sm-3">
	                                    <label>Item Code<span class="red">*</span></label>
	                                    <div class="materialCode"></div>
	                                    <span></span>
                                	</div>
	                                <div class="col-sm-3">
		                                    <label>Item Name<span class="red">*</span></label>
		                                    <div class="materialName" ></div>
		                                    <span></span>
	                                </div>
	                                <div class="col-sm-3">
		                                    <label>Description Of Material<span class="red">*</span></label>
		                                    <div class="materialDescription"></div>
		                                    <span></span>
	                                </div>
	                                <div class="col-sm-3">
	                                        <label>Required quantity<span class="red">*</span></label>
	                                        <div class="referedQuantity" ></div>
	                                        <span></span>
	                                </div>
	                                <div class="col-sm-3">
	                                		<label>Unit<span class="red">*</span></label>
	                                        <div class="materialUnit"></div>
	                                        <span></span>
	                                </div>
	                                <div class="col-sm-3">
	                                		<label>HSN/SAC code<span class="red">*</span></label>
	                                        <div class="hsnCodeField"></div>
	                                        <span></span>
	                                </div>
	                                <div class="col-sm-3">
	                                		<label>Bid Status<span class="red">*</span></label>
	                                        <div class="itemBididStatus"></div>
	                                        <span></span>
	                                </div>
                                  </div>
                                  </div>
                                  <div class="col-sm-12 text-center positionABS" style="margin-bottom: 10px;">
											<button class="btn-all btn btn-info k-tabstrip-prev pull-left tab-li-prev">Previous</button>
											<button class="btn-all btn btn-info k-tabstrip-next pull-right tab-li-next">Next</button>
								</div>
                                  </div>
                                  <%-- <c:if test="${documentType.equalsIgnoreCase('Tender')}"> --%>
                                      <div id="technicalBidDiv" class="technicalBidClass">
                                      <div class="col-sm-12">
                                      <ul class="nav nav-pills techbidstab col-md-12">
										  <li id="bidderGtpSubTab" class="gtpClass" data-parentTab="itemDetailsTab"  class="active" data-placement="right" data-toggle="tooltip" title="Technical Bid" onclick="getBidderGtp();" ><a href="#tab_a" data-toggle="pill"><i class="fa fa-gavel"></i></a><label>Technical Bid</label></li>
										  <li id="sectionDocumentTab" class="technicalDocsClass" data-parentTab="itemDetailsTab"  data-toggle="tooltip" data-placement="right" title="Documents" onclick="getTechnicalBidSecDoc();"><a href="#tab_c" data-toggle="pill" ><i class="fa fa-file-text"></i></a><label>Technical Documents</label></li>
										  <li id="submitTBTab" data-parentTab="itemDetailsTab" data-toggle="tooltip" data-placement="right" title="Confirmation" onclick="getTechnicalBidDetails();"><a href="#tab_d" data-toggle="pill"><i style="font-size:24px;" class="fa fa-check-circle-o" aria-hidden="true"></i></a><label>Confirmation</label></li>
										</ul>
						<div class="technicalBidClass tab-content col-md-12">
						        <div class="gtpClass tab-pane active" id="tab_a">
						              <div class="form-group">
                                       	 <div class="col-sm-12"><h4 ><b>Technical Bid</b></h4><hr>
                                       	 </div>
                                      </div>
                                      <div class="clearfix"></div>
				             <div class="form-group">
                            <label class="col-sm-12"><span style="color:red;">Note : </span> Any changes made in Technical Bid will change Technical Bid Status, Price Bid status
                             and Bid Submission status to Drafted </label>
                            </div>
                            <div class="clearfix"></div>
                                <sf:form id="saveTechnicalBidForm" action="saveTechnicalbid" modelAttribute="itemBid">
                                        <input type="hidden" class="bidderId">
                                  	 	<input type="hidden" class="itemBidId">
                                  	 	<input type="hidden" class="technicalBidId">
                                        <input type="hidden" class="tahdrMaterialId">
                                        <div class="form-group">
                                       	 <div class="col-sm-12">
                                       	 	<table class="table tableResponsive table-bordered" id="technicalBidTable">
                                               <thead>
                                                   <tr>
                                                       <th class="col-sm-3">GTP</th>
                                                       <th class="col-sm-3">GTP Type</th>
                                                       <th class="col-sm-3">Value</th>
                                                       <th class="col-sm-3">NA</th>
                                                   </tr>
                                               </thead>
                                               <tbody>                                            
                                               </tbody>
                                           	</table>
	                                     </div>
                                       </div>
                                      <div class="form-group">
                                            <div class="col-sm-12">
                                                <button class="btn btn-default rbschClass" id="saveTechnicalBidBtn">Save</button>
                                                <button class="btn btn-default cancel rbschClass" id="cancelTechnicalBid">cancel</button>
                                                <button class="btn btn-default backToTenders">Back To ${documentType}s</button>
                                            </div>
                                      </div>      
                                    </sf:form> 
        </div>
        <div class="technicalDocsClass tab-pane" id="tab_c">
	        <sf:form id="saveTechSecDocForm" modelAttribute="itemBid">
	        	<input type='hidden' class='bidderId' name="bidder.bidderId">
				<input type='hidden' class='itemBidId' name="itemBidId">
				<input type='hidden' class='technicalBidId' name="technicalBid.technicalBidId">
				<input type='hidden' class='tahdrMaterialId' name="tahdrMaterial.tahdrMaterialId">
	            <h4 class="col-sm-12">Additional Required Technical Documents</h4>
	              <div class="clearfix"></div>
				             <div class="form-group">
                            <label class="col-sm-12"><span style="color:red;">Note : </span> Any changes made in Required Technical Documents will change Technical Bid Status, Price Bid status
                             and Bid Submission status to Drafted </label>
                            </div>
                            <div class="clearfix"></div>
	            <div class="col-sm-12">
	            <table id="tbDocumentTable" class="table tableResponsive table-bordered">
		            <thead>
		            	<th class='col-sm-3'>Document Name</th>
		            	<th class='col-sm-3'>Description</th>
		            	<th class='col-sm-3'>Attachment</th>
		            	<th class='col-sm-3'>File Name</th>
		            </thead>
	            <tbody>
	            </tbody>
	            </table>
	            </div>
	            <div class="clearfix"></div>
	            <div class="form-group">
	               <div class="col-sm-12">
	                   <button class="btn btn-default save rbschClass" id="saveTechnicalDocBtn">Save</button>
	                   <!-- <button class="btn btn-default cancel" id="saveTechnicalDocBtn">cancel</button> -->
	                   <button class="btn btn-default backToTenders">Back To ${documentType}s</button>
	               </div>
	           </div>  
	        </sf:form>
        </div>
        <div class="tab-pane" id="tab_d">
           	<sf:form id="submitTechnicalBidForm" modelAttribute="itemBid">
	        	<input type='hidden' class='bidderId' name="bidder.bidderId">
				<input type='hidden' class='itemBidId' name="itemBidId">
				<input type='hidden' class='technicalBidId' name="technicalBid.technicalBidId">
				<input type='hidden' class='tahdrMaterialId' name="tahdrMaterial.tahdrMaterialId">
	             <div class="clearfix"></div>
	             <div class="mobclearfix"></div>
	               <div class="clearfix"></div>
				             <div class="form-group">
                            <label class="col-sm-12"><span style="color:red;">Note : </span> Deleting Technical Bid Document will change Technical Bid Status, Price Bid status
                             and Bid Submission status to Drafted </label>
                            </div>
                            <div class="clearfix"></div>
	            <div class="col-sm-4  col-xs-6">
	            <div class="mobclearfix"></div>
                   <!-- <div class="col-sm-10" style="padding:0px;"> -->
                  <div class="input-group">
                   <input type="file" id="digitalSignaturedTBFile" data-id="digitalSignaturedTB" data-anchor='digitalSignaturedTBAnchor' class="form-control uploadFile">
                	
                   <input type="hidden" id="digitalSignaturedTB" class="digitalSignaturedTB" name="technicalBid.digiSignedDoc.attachmentId" >
                   <span class="input-group-btn">
														<button class="btn btn-default digitalSignaturedTB"
															onclick="deleteAttachmentTB();"
															disabled="disabled">
															<i class="fa fa-times"></i>
														</button>
													</span>
                  </div>
                   <!-- <span lass="input-group-btn col-sm-1" style="padding-left:0px;">
                   	<button class="btn btn-default pnlFile_1" style="margin: 0px;" id="deleteAdditionalDoc"><i class="fa fa-times"></i></button>
                   </span> -->
                   <a type="hidden" id="digitalSignaturedTBAnchor" data-url="download"></a>
                </div>
                
	            <div class="col-sm-2 col-xs-6">
	            <div class="mobclearfix"></div>
	            		<input type='hidden' id='tbReportUrl' value=''>
						<button id="generateTBDocBtnId" class="btn-all btn btn-info redbtn rbschClass" onclick="generateTBDocument(event);">Generate Document</button>
					</div>
					<div class="mobclearfix"></div>
	            <div class="clearfix"></div>
	            <div class="form-group">
	               <div class="col-sm-12">
	                   <button class="btn btn-default submitTechnicalBid rbschClass" id="submitTechnicalBid">Submit</button>
	                   <button class="btn btn-default backToTenders">Back To ${documentType}s</button>
	               </div>
	           </div>  
	        </sf:form>
			<sf:form id="gennerateTBDoc">
			</sf:form>
			<div class="col-sm-12 text-center positionABS" style="margin-bottom: 10px;">
											<button class="btn-all btn btn-info k-tabstrip-prev pull-left tab-li-prev">Previous</button>
											<button class="btn-all btn btn-info k-tabstrip-next pull-right tab-li-next">Next</button>
								</div>
       		</div>
		</div><!-- tab content -->                                                 
	</div>
</div>
<%--  </c:if> --%>        
                    
			<div id="priceBidDiv">
				<div class="col-sm-12">
					<ul class="nav nav-pills pricebidtab col-md-12">
						<li data-parentTab="itemDetailsTab" id="partsTab" data-placement="right" data-toggle="tooltip" title="Parts" style="display:none;" onclick="getPriceBidForParts();" ><a href="#tab_k" data-toggle="pill"><i class="fa fa-gavel"></i></a><label>Parts</label></li>
					  	<li data-parentTab="itemDetailsTab" id="priceSubTab" class="active" data-placement="right" data-toggle="tooltip" title="Price Bid" onclick="getPriceBid();"><a href="#tab_e" data-toggle="pill"><i class="fa fa-gavel"></i></a><label>Price Bid</label></li>
					  	<li data-parentTab="itemDetailsTab" class="priceDocsClass" id="sectionDocumentSubTab" data-toggle="tooltip" data-placement="right" title="Documents" onclick="getPriceBidSecDoc();" ><a href="#tab_f" data-toggle="pill"><i class="fa fa-file-text"></i></a><label>Bid Documents</label></li>
					  	<li data-parentTab="itemDetailsTab" id="submitPBTab" data-toggle="tooltip" data-placement="right" title="Confirmation" onclick="confirmPriceBid();" ><a href="#tab_g" data-toggle="pill" ><i class="fa fa-check-circle-o"></i></a><label>Confirmation</label></li>
					</ul>
					<div class="tab-content col-md-12">
						<div class="tab-pane active" id="tab_e">
	             			<div class="form-group">
                      	 		<div class="col-sm-12">
                      	 			<h4><b>Price Bid</b></h4><hr>
                      	 		</div>
                            </div>
                           <div class="clearfix"></div>
				            <div class="form-group">
                            <label class="col-sm-12"><span style="color:red;">Note : </span> Any Changes Made In Price Bid Will Change Price Bid Status And Bid Submission Status To Drafted</label>
                            </div>
                            <div class="clearfix"></div>
							<sf:form action="savePriceBid" id="savePriceBidForm" modelAttribute="itemBid">                                      
                            	<input type="hidden" class="bidderId" name="bidder.bidderId">
                                <input type="hidden" class="itemBidId" name="itemBidId">
                                <input type="hidden" class="tahdrMaterialId" name="tahdrMaterial.tahdrMaterialId">
                                <input type="hidden" class="priceBidId" name="priceBid.priceBidId">
                                <input type="hidden" class="tahdrId" name="bidder.tahdr.tahdrId">
                                <input type="hidden" class="rbschVariable" name="priceBid.isRevised">
                                
                                <div class="clearfix"></div>
                                <div class="form-group">
                                <div class="col-sm-3">
	                                    <label>Item Code<span class="red">*</span></label>
	                                    <div class="materialCode"></div>
	                                    <span></span>
                                </div>
                                <div class="col-sm-3">
	                                    <label>Item Name<span class="red">*</span></label>
	                                    <div class="materialName" ></div>
	                                    <span></span>
                                </div>
                                <div class="col-sm-3">
	                                    <label>Description Of Material<span class="red">*</span></label>
	                                    <div class="materialDescription"></div>
	                                    <span></span>
                                </div>
                                </div>
                                <div class="clearfix"></div>
                                <div class="form-group">
                                <div class="col-sm-3">
                                        <label>Required quantity<span class="red">*</span></label>
                                        <div class="referedQuantity" ></div>
                                        <span></span>
                                </div>
                                <div class="col-sm-3">
                                		<label>Unit<span class="red">*</span></label>
                                        <div class="materialUnit"></div>
                                        <span></span>
                                </div>
                                <div class="col-sm-3">
                                		<label>HSN/SAC code<span class="red">*</span></label>
                                        <div class="hsnCodeField"></div>
                                        <span></span>
                                </div>
                                <div class="col-sm-3">
                                    <div class="styled-input">
                                        <input type="text" class="onlyNumber requiredField" onchange="onChangeOfferedQuantity(this)" id="offeredQuantity" name="priceBid.offeredQuantity">
                                        <label>Quantity Offered<span class="red">*</span></label>
                                        <span></span>
                                    </div>
                                </div>
                                </div>
                                <div class="clearfix"></div>
                                <div class="col-sm-3">
	                                 <div class="styled-input">
	                                         <input type="text" class="onlyNumberWithDecimal bom requiredField" id="exGroupPriceRate" title="Per Unit Ex-Works Price (in Rs.)" onchange="calculate()" name="priceBid.exGroupPriceRate" title="Per Unit Ex-Group Price Including Packaging But Excluding Excise duty and Taxes(In Rs)" required="">
	                                         <label>Per Unit Ex-Works Price (in INR) <span class="red">*</span></label>
	                                         <span></span>
	                                 </div>
                                </div>
                            	<div class="col-sm-3">                                                    
                                    <div class="styled-input">
                                        <input type="text" class="onlyNumberWithDecimal bom requiredField" id="freightChargeRate" title="Per Unit Freight Charges (in Rs.)" onchange="calculate()" name="priceBid.freightChargeRate" required="">
                                        <label>Per Unit Freight charges (in INR)</label>
                                        <span></span>
                                    </div>
                                </div>    
                                <div class="col-sm-3">
                                    <div class="styled-input">
                                        <input type="text" class="onlyNumberWithDecimal bom requiredField" id="ticRate" title="Per Unit Transit Insurance Charge(in Rs.)" onchange="calculate()" name="priceBid.ticRate" required="">
                                        <label>Per Unit TIC (in INR)<span class="red">*</span></label>
                                        <span></span>
                                    </div>
                                </div>
                                <div class="col-sm-3 readonly">
                                    <div class="styled-input">
                                        <input type="text" readonly="readonly" id="fddRate" title="Per unit Free Door Delivery Price Without GST(in Rs.)" name="priceBid.fddRate" title="Free Door Delivery Price in Rs per Unit on Road Upto Destination/Store/Sub-Station (in Rs)">
                                        <label>Per unit FDD Price Without GST (in INR)<span class="red">*</span></label>
                                        <span></span>
                                    </div>
                                </div>
                                <div class="col-sm-3 readonly">
                                	<div class="styled-input">
                                        <input type="text" readonly="readonly" id="totalExGroupPrice" title="Total Ex-Works Price (in Rs.)" name="priceBid.totalExGroupPrice" title="Per Unit Ex-Group Price Including Packaging But Excluding Excise duty and Taxes(In Rs)" required="">
                                        <label>Total Ex-Works Price (in INR)<span class="red">*</span></label>
                                        <span></span>
                                    </div>
                                </div>
                                <div class="col-sm-3 readonly">                                                    
                                    <div class="styled-input">
                                        <input type="text" readonly="readonly" id="totalFreightCharges" title="Total Freight Charge(in Rs.)" name="priceBid.totalFreightCharge" required="">
                                        <label>Total Freight charges (in INR)</label>
                                        <span></span>
                                    </div>
                                </div>
                                 <div class="col-sm-3 readonly">
                                    <div class="styled-input">
                                        <input type="text" readonly="readonly" id="totalTic" title="Total Transit Insurance Charge(in Rs.)" name="priceBid.totalTic" required="">
                                        <label>Total TIC (in INR)<span class="red">*</span></label>
                                        <span></span>
                                    </div>
                                </div>
                                <div class="col-sm-3 readonly">
                                    <div class="styled-input">
                                        <input type="text" readonly="readonly" title="" id="fddAmount" title="Total Free Door Delivery Price Without GST (in Rs.)" name="priceBid.fddAmount" required="" title="Free Door Delivery Price in Rs per Unit on Road Upto Destination/Store/Sub-Station (in Words)">
                                        <label>Total FDD Price Without GST (in INR)</label>
                                        <span></span>
                                    </div>
                                </div>
                                <div class="col-sm-3" id="taxRateDiv">
                                    <div class="styled-input">
                                        <input type="text" class="onlyNumberWithDecimal requiredField" id="taxRate" name="priceBid.taxRate" title="GST(in %)" onchange="onChangeGSTPriceBid()" > <!-- onchange="gstCalculate()" -->
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
                                        <label>Per Unit GST (in INR)<span class="red">*</span></label>
                                        <span></span>
                                    </div>
                                </div>
                                <div class="col-sm-3 readonly" id="totalGSTDiv">
                                    <div class="styled-input">
                                        <input type="text" readonly="readonly" id="totalTax" name="priceBid.totalTax" title="Total GST(in Rs.)">
                                        <label>Total GST (in INR)<span class="red">*</span></label>
                                        <span></span>
                                    </div>
                                </div>
                                <div class="col-sm-3 readonly" id="fddRateWithGSTDiv">
                                    <div class="styled-input">
                                        <input type="text" readonly="readonly" id="fddRateGST" name="priceBid.fddRateWithGST" title="Free Door Delivery Price With GST (in Rs.) per Unit on Road Upto Destination/Store/Sub-Station">
                                        <label>Per unit FDD With GST (in INR)<span class="red">*</span></label>
                                        <span></span>
                                    </div>
                                </div>
                                <div class="col-sm-3 readonly" id="fddAmountWithGSTDiv">
                                    <div class="styled-input">
                                        <input type="text" readonly="readonly" title="" id="fddAmountGST" name="priceBid.fddAmountWithGST" required="" title="Total Free Door Delivery Price With GST (in Rs.) on Road Upto Destination/Store/Sub-Station">
                                        <label>Total FDD With GST (in INR)</label>
                                        <span></span>
                                    </div>
                                </div>
                                <div class="col-sm-3 readonly top20" id="fddAmountWithGSTInWordsDiv">
                                	<label>PER UNIT FDD with GST In Words</label>
                                    <input type="hidden" class="readonly" name="priceBid.amountInWords" id="fddAmountWithGSTInWords">
                                    <label class="detspan" id="fddAmountWithGSTInWordsLabel" title=""></label>
                                </div>
                            	<div class="col-sm-12">
                                    <button id="savePriceBidBtn" class="btn btn-default">Save</button>
                                    <button class="btn btn-default cancel" id="cancelPriceBid">cancel</button>
                                    <button class="btn btn-default backToTenders">Back To ${documentType}s</button>
                                </div>
                          </sf:form>
                      </div>
                      <div class="tab-pane" id="tab_k">
	             			<div class="form-group">
                      	 		<div class="col-sm-12">
                      	 			<h4><b>BOM</b></h4><hr>
                      	 		</div>
                            </div>
                            <div class="clearfix"></div>
				             <div class="form-group">
                            <label class="col-sm-12"><label><span style="color:red;">Note : </span> Any Changes Made In Price Bid Will Change Price Bid Status And Bid Submission Status To Drafted</label> </label>
                            </div>
                            <div class="clearfix"></div>
							<sf:form action="savePriceBid" id="savePartsPriceBidForm" modelAttribute="itemBid">                                      
                            	<input type="hidden" class="bidderId" name="bidder.bidderId">
                                <input type="hidden" class="itemBidId" name="itemBidId">
                                <input type="hidden" class="tahdrMaterialId" name="tahdrMaterial.tahdrMaterialId">
                                <input type="hidden" class="materialSpecificationId" name="priceBid.materialSpecification.materialSpecificationId">
                                <input type="hidden" class="priceBidId" id="priceBidId" name="priceBid.priceBidId">
                                <input type="hidden" class="tahdrId" name="bidder.tahdr.tahdrId">
                                <input type="hidden" class="rbschVariable" name="priceBid.isRevised">
                                
                                <div class="clearfix"></div>
                                <div class="form-group">
                                <div class="col-sm-3">
	                                    <label>Item Code<span class="red">*</span></label>
	                                    <div class="partMaterialCode"></div>
	                                    <span></span>
                                </div>
                                <div class="col-sm-3">
	                                    <label>Item Name<span class="red">*</span></label>
	                                    <div class="partMaterialName" ></div>
	                                    <span></span>
                                </div>
                                <div class="col-sm-3">
	                                    <label>Description Of Material<span class="red">*</span></label>
	                                    <div class="partMaterialDescription"></div>
	                                    <span></span>
                                </div>
                                </div>
                                <div class="clearfix"></div>
                                <div class="form-group">
                                <div class="col-sm-3">
                                        <label>Required quantity<span class="red">*</span></label>
                                        <div class="partReferedQuantity" ></div>
                                        <span></span>
                                </div>
                                <div class="col-sm-3">
                                		<label>Unit<span class="red">*</span></label>
                                        <div class="partMaterialUnit"></div>
                                        <span></span>
                                </div>
                                <div class="col-sm-3">
                                		<label>HSN/SAC code<span class="red">*</span></label>
                                        <div class="partHsnCodeField"></div>
                                        <span></span>
                                </div>
                                <div class="col-sm-3">
                                    <div class="styled-input">
                                        <input type="text" class="partReferedQuantity onlyNumber requiredField" id="partOfferedQuantity" name="priceBid.offeredQuantity" readonly="readonly">
                                        <label>Quantity Offered<span class="red">*</span></label>
                                        <span></span>
                                    </div>
                                </div>
                                </div>
                                <div class="clearfix"></div>
                                <div class="col-sm-3">
	                                 <div class="styled-input">
	                                         <input type="text" class="onlyNumberWithDecimal requiredField" id="partExGroupPriceRate" title="Per Unit Ex-Works Price (in Rs.)" onchange="calculateForParts()" name="priceBid.exGroupPriceRate" title="Per Unit Ex-Group Price Including Packaging But Excluding Excise duty and Taxes(In Rs)" required="">
	                                         <label>Per Unit Ex-Works Price <span class="red">*</span></label>
	                                         <span></span>
	                                 </div>
                                </div>
                            	<div class="col-sm-3">                                                    
                                    <div class="styled-input">
                                        <input type="text" class="onlyNumberWithDecimal requiredField" id="partFreightChargeRate" title="Per Unit Freight Charges (in Rs.)" onchange="calculateForParts()" name="priceBid.freightChargeRate" required="">
                                        <label>Per Unit Freight charges(in Rs)</label>
                                        <span></span>
                                    </div>
                                </div>    
                                <div class="col-sm-3">
                                    <div class="styled-input">
                                        <input type="text" class="onlyNumberWithDecimal requiredField" id="partTicRate" title="Per Unit Transit Insurance Charge(in Rs.)" onchange="calculateForParts()" name="priceBid.ticRate" required="">
                                        <label>Per Unit TIC(In Rs)<span class="red">*</span></label>
                                        <span></span>
                                    </div>
                                </div>
                                <div class="col-sm-3 readonly">
                                    <div class="styled-input">
                                        <input type="text" readonly="readonly" id="partFddRate" title="Per unit Free Door Delivery Price Without GST(in Rs.)" name="priceBid.fddRate" title="Free Door Delivery Price in Rs per Unit on Road Upto Destination/Store/Sub-Station (in Rs)">
                                        <label>Per unit FDD Price Without GST<span class="red">*</span></label>
                                        <span></span>
                                    </div>
                                </div>
                                <div class="col-sm-3 readonly">
                                	<div class="styled-input">
                                        <input type="text" readonly="readonly" id="partTotalExGroupPrice" title="Total Ex-Works Price (in Rs.)" name="priceBid.totalExGroupPrice" title="Per Unit Ex-Group Price Including Packaging But Excluding Excise duty and Taxes(In Rs)" required="">
                                        <label>Total Ex-Works Price <span class="red">*</span></label>
                                        <span></span>
                                    </div>
                                </div>
                                <div class="col-sm-3 readonly">                                                    
                                    <div class="styled-input">
                                        <input type="text" readonly="readonly" id="partTotalFreightCharges" title="Total Freight Charge(in Rs.)" name="priceBid.totalFreightCharge" required="">
                                        <label>Total Freight charges</label>
                                        <span></span>
                                    </div>
                                </div>
                                 <div class="col-sm-3 readonly">
                                    <div class="styled-input">
                                        <input type="text" readonly="readonly" id="partTotalTic" title="Total Transit Insurance Charge(in Rs.)" name="priceBid.totalTic" required="">
                                        <label>Total TIC<span class="red">*</span></label>
                                        <span></span>
                                    </div>
                                </div>
                                <div class="col-sm-3 readonly">
                                    <div class="styled-input">
                                        <input type="text" readonly="readonly" title="" id="partFddAmount" title="Total Free Door Delivery Price Without GST (in Rs.)" name="priceBid.fddAmount" required="" title="Free Door Delivery Price in Rs per Unit on Road Upto Destination/Store/Sub-Station (in Words)">
                                        <label>Total FDD Price Without GST</label>
                                        <span></span>
                                    </div>
                                </div>
                                <!-- <div class="col-sm-3" id="partTaxRateDiv">
                                    <div class="styled-input">
                                        <input type="text" class="" id="partTaxRate" name="priceBid.taxRate" title="GST(in %)" onchange="gstForPartsCalculate()">
                                        <label>GST(in %)<span class="red">*</span></label>
                                        <span></span>
                                    </div>
                                </div>
                                <div class="col-sm-3" id="partIgstDiv">
                                    <div class="styled-input">
                                        <input type="text" class="" readonly="readonly" id="partIgst" name="priceBid.igst" title="IGST(in %)" >
                                        <label>IGST(in %)<span class="red">*</span></label>
                                        <span></span>
                                    </div>
                                </div>
                                <div class="col-sm-3" id="partCgstDiv">
                                    <div class="styled-input">
                                        <input type="text" class="" readonly="readonly" id="partCgst" name="priceBid.cgst" title="CGST(in %)" >
                                        <label>CGST(in %)<span class="red">*</span></label>
                                        <span></span>
                                    </div>
                                </div>
                                <div class="col-sm-3" id="partSgstDiv">
                                    <div class="styled-input">
                                        <input type="text" class="" readonly="readonly" id="partSgst" name="priceBid.sgst" title="SGST(in %)" >
                                        <label>SGST(in %)<span class="red">*</span></label>
                                        <span></span>
                                    </div>
                                </div>
                                <div class="col-sm-3 readonly">
                                    <div class="styled-input">
                                        <input type="text" readonly="readonly" id="partTaxAmount" name="priceBid.taxAmount" title="Per Unit GST(in Rs.)">
                                        <label>Per Unit GST<span class="red">*</span></label>
                                        <span></span>
                                    </div>
                                </div>
                                <div class="col-sm-3 readonly">
                                    <div class="styled-input">
                                        <input type="text" readonly="readonly" id="partTotalTax" name="priceBid.totalTax" title="Total GST(in Rs.)">
                                        <label>Total GST<span class="red">*</span></label>
                                        <span></span>
                                    </div>
                                </div>
                                <div class="col-sm-3 readonly">
                                    <div class="styled-input">
                                        <input type="text" readonly="readonly" id="partFddRateGST" name="priceBid.fddRate" title="Free Door Delivery Price With GST (in Rs.) per Unit on Road Upto Destination/Store/Sub-Station">
                                        <label>Per unit FDD With GST<span class="red">*</span></label>
                                        <span></span>
                                    </div>
                                </div>
                                <div class="col-sm-3 readonly">
                                    <div class="styled-input">
                                        <input type="text" readonly="readonly" title="" id="partFddAmountGST" name="priceBid.fddAmount" required="" title="Total Free Door Delivery Price With GST (in Rs.) on Road Upto Destination/Store/Sub-Station">
                                        <label>Total FDD With GST</label>
                                        <span></span>
                                    </div>
                                </div> -->
                            	<div class="col-sm-12">
                                    <button id="savePartsPriceBidBtn" class="btn btn-default">Save</button>
                                    <button class="btn btn-default cancel" id="cancelPartsPriceBid">cancel</button>
                                    <button class="btn btn-default backToTenders">Back To ${documentType}s</button>
                                </div>
                          </sf:form>
                      </div>
                      <div class="priceDocsClass tab-pane" id="tab_f">
                      	<sf:form id="savePbSecDocForm" modelAttribute="itemBid">
				        	<input type='hidden' class='bidderId' name="bidder.bidderId">
							<input type='hidden' class='itemBidId' name="itemBidId">
							<input type='hidden' class='priceBidId' name="priceBid.priceBidId">
							<input type='hidden' class='tahdrMaterialId' name="tahdrMaterial.tahdrMaterialId">
							<input type="hidden" class="rbschVariable" name="priceBid.isRevised">
				            <h4 class="col-sm-12">
				            	Additional Required Price Bid Documents
				            </h4>
				            <div class="clearfix"></div>
				             <div class="form-group">
                            <label class="col-sm-12"><label><span style="color:red;">Note : </span> Any Changes Made In Required Price Bid Documents Will Change Price Bid Status And Bid Submission Status To Drafted</label> </label>
                            </div>
                            <div class="clearfix"></div>
				            <div class="col-sm-12">
					            <table id="pbDocumentTable" class="table tableResponsive table-bordered">
						            <thead>
						            	<tr>
						            	<th class='col-sm-3'>Document Name</th>
						            	<th class='col-sm-3'>Description</th>
						            	<th class='col-sm-3'>Attachment</th>
						            	<th class='col-sm-3'>File Name</th>
						            	</tr>
						            </thead>
						            <tbody>
						            </tbody>
					            </table>
				            </div>
				            <div class="clearfix"></div>
				            <div class="form-group">
				               <div class="col-sm-12">
				                   <button class="btn btn-default save" id="savePbAdditionalDocumentBtn">Save</button>
				                   <!-- <button class="btn btn-default cancel" id="cancelPbAdditionalDocument">cancel</button> -->
				                   <button class="btn btn-default backToTenders">Back To ${documentType}s</button>
				               </div>
				           </div>  
				        </sf:form>
                      </div>
                      <div class="tab-pane" id="tab_g">
                      	<sf:form id="submitPriceBidForm" modelAttribute="itemBid">
				        	<input type='hidden' class='bidderId' name="bidder.bidderId">
							<input type='hidden' class='itemBidId' name="itemBidId">
							<input type='hidden' class='priceBidId' name="priceBid.priceBidId">
							<input type='hidden' class='tahdrMaterialId' name="tahdrMaterial.tahdrMaterialId">
							<input type="hidden" class="rbschVariable" name="priceBid.isRevised">
							<div class="mobclearfix"></div>
							<div class="clearfix"></div>
				             <div class="form-group">
                            <label class="col-sm-12"><label><span style="color:red;">Note : </span> Deleting Price Bid Document Will Change Price Bid Status And Bid Submission Status To Drafted</label> </label>
                            </div>
                            <div class="clearfix"></div>
<div id="pbCertificate">				           
			                  <h2 align="center">CERTIFICATE</h2>

<p>To,</p>
<b><p>NovelErp Pvt Ltd,</p></b>
<p>Andheri(E),</p>
<br>
<p>Dear Sir/Madam,</p>
<br>
<p>We hereby offer to carry out the work of design, manufacture, testing and supply of material/equipments as detailed in your tender specification and in accordance with the terms and conditions thereof.</p>
<br>
<p>We have carefully perused the above tender specification connected with the work and agree to abide by the same.</p>
<br>
<p>We agree to pay Security Deposit and to give the Contract Performance Guarantee as per your requirements in case we are the
successful tenderer and also accept all terms & conditions of the tender specification.</p>
<br>
<p>I/We agree to supply the materials at the rates herein tendered by me/us subject to the conditions of tender and supply Section-II of this tender which I/We have carefully read and which I/We thoroughly understood and to which I/We agree.</p> 
<br>
<p>I/We hereby agree to keep this offer open upto the date as indicated in tender details & clause of validity of the tender of  Section-I and shall be bound by communication of acceptance dispatched within the validity period.</p>
<br>
<p>We further agree to execute the contract if awarded referred to in your tender specification as per the terms and conditions
specified therein.</p>
<br>
<b><p>We are enclosing herewith Section-I, II & III and other Schedules and Annexure as per the tender.</p>
<br>
<p>All the above details are true. And I/We accept these details.</p></b>
<br>
<b><p>Thanking you.</p>
</b><br>
</div>
			      <div class="col-sm-4  col-xs-6">
			          <div class="input-group">         
                   <input type="file" id="digitalSignaturedPBFile" data-encrypt="Y" data-id="digitalSignaturedPB" data-anchor="digitalSignaturedPBAnchor" class="form-control uploadFile">
     			   <input type="hidden" id="digitalSignaturedPB" class="digitalSignaturedPB" name="priceBid.digiSignedDoc.attachmentId" >           	
                   <span class="input-group-btn">
						<button class="btn btn-default digitalSignaturedPB"
															onclick="deleteAttachmentPB();"
															disabled="disabled">
															<i class="fa fa-times"></i>
														</button>
													</span>
                  </div>
                   				<a type="hidden" id="digitalSignaturedPBAnchor" data-url="download"></a>
			                </div>
			                <div class="col-sm-4 col-xs-6">
									<button id="generatePbDocBtnId" class="btn-all btn btn-info redbtn" onclick="generatePBDocument(event);">Generate Document</button>
							</div>
							<div class="mobclearfix"></div>
				            <div class="clearfix"></div>
				            <div class="form-group">
				               <div class="col-sm-12">
				                   <button class="btn btn-default submitPriceBid" id="submitPriceBid">Submit</button>
				                   <button class="btn btn-default backToTenders">Back To ${documentType}s</button>
				               </div>
				           </div>  
				        </sf:form>
				         <div class="col-sm-12 text-center positionABS" style="margin-bottom: 10px;">
											<button class="btn-all btn btn-info k-tabstrip-prev pull-left tab-li-prev">Previous</button>
											<button class="btn-all btn btn-info k-tabstrip-next pull-right tab-li-next">Next</button>
								</div>
				        <sf:form id="genneratePBDoc">
						</sf:form>
                      </div>
                   </div>
                 </div>
             </div>
           </div>
         </div>
              <!--fields of field group 3  -->                     
              <!--fields of field group 4  -->
              
         <div>
         	<div class="detailscont">
			</div>
		    <div class="form-group">
		    	<div class="col-sm-12">
		        	<h4><b>Bid Submission</b></h4>
		            <hr>
		        </div>
		    </div>
		    <div class="clearfix"></div>
				             <div class="form-group">
                            <label class="col-sm-12"><label><span style="color:red;">Note : </span> Deleting Bid Submission Document Will Change Bid Submission Status To Drafted</label> </label>
                            </div>
                            <div class="clearfix"></div>
		    <div class="form-group">
		    	<div class="col-sm-12">
		    		<sf:form id="submitItemBidForm" modelAttribute="commercialBid">
		    			<div class="form-group">
                            <div class="col-sm-12">
                                <table class="table tableResponsive table-bordered" id="quotedStatusTable">
                                    <thead>
                                        <tr>
                                        	<th class="col-sm-3">Item Code</th>
                                            <th class="col-sm-2">Item</th>
                                            <th class="col-sm-3">Is Quoted</th>
                                    </thead>
                                    <tbody>
                                                                                     
                                    </tbody>
                                </table>
                            </div>
                       	</div>
		            	<input type="hidden" class="tahdrDetailId" name="tahdrDetail.tahdrDetailId">
		            	<input type="hidden" class="commercialBidId" name="commercialBidId">	
		            	<div class="col-sm-2 col-xs-6">
							<button id="generateCbBtnId" class="btn-all btn btn-info redbtn" onclick="generateCBDocument(event);">Generate Document</button>
						</div>
						<div class="col-sm-4  col-xs-6">
		                   	<div class="input-group">
		                   	<input type="file" id="digitalSignaturedCBFile" data-id="digitalSignaturedCB" data-anchor="digitalSignaturedCBAnchor" class="form-control uploadFile">
		                   	<input type="hidden" id="digitalSignaturedCB" class="digitalSignaturedCB" name="digiSignedDoc.attachmentId" >
                  			<span class="input-group-btn">
							<button class="btn btn-default digitalSignaturedCB" onclick="deleteAttachmentCB();"
													disabled="disabled">
								<i class="fa fa-times"></i>
							</button>
							</span>
							</div>
                  			<a type="hidden" id="digitalSignaturedCBAnchor" data-url="download"></a>
                	  
		                </div>
		            </sf:form>
		            <sf:form id="generateCBDoc">
		            </sf:form>
		        </div>
		    </div>
		    <div class="form-group">
		    	<div class="col-sm-12">
		        	<div class="styled-input">
		            	<button class="btn btn-default submitItemBid" id="submitItemBid">Submit</button>
		                <button class="btn btn-default backToTenders"">Goto Main Page</button>
					</div>
		        </div>
		     </div> 
		     <div class="col-sm-12 text-center positionABS" style="margin-bottom: 10px;">
											<button class="btn-all btn btn-info k-tabstrip-prev pull-left tab-li-prev">Previous</button>
								</div>                                            
		 </div>
		<!--fields of field group 4  -->
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

<script src="<%=request.getContextPath()%>/resources/${appMode}/tenderSubmission/tenderSubmission.js?appVer=${appVer}"></script>
<script src="<%=request.getContextPath()%>/resources/${appMode}/tenderSubmission/technicalBid.js?appVer=${appVer}"></script>
<script src="<%=request.getContextPath()%>/resources/${appMode}/tenderSubmission/commercialBid.js?appVer=${appVer}"></script>
<script src="<%=request.getContextPath()%>/resources/${appMode}/tenderSubmission/priceBid.js?appVer=${appVer}"></script>
<script src="<%=request.getContextPath()%>/resources/${appMode}/tenderSubmission/parts.js?appVer=${appVer}"></script>
<script src="<%=request.getContextPath()%>/resources/${appMode}/commons/js/formFields.js?appVer=${appVer}"></script>
<script src="<%=request.getContextPath()%>/resources/${appMode}/commons/js/utility.js?appVer=${appVer}"></script>
<script src="<%=request.getContextPath()%>/resources/${appMode}/partner/js/uploadFile.js?appVer=${appVer}"></script>
<script src="<%=request.getContextPath()%>/resources/${appMode}/commons/js/liCache.js?appVer=${appVer}"></script>
<script src="<%=request.getContextPath()%>/resources/${appMode}/commons/js/numberToWords.js?appVer=${appVer}"></script>
<script>
$(document).ready(function(){
		
	   $('#TechnoCommercialBidTable').DataTable({
			"lengthMenu":lengthMenu,
			"ordering": false,
			"bSortable": false 
		});
		
		$("#tbDocumentTable").DataTable({
			"lengthMenu":lengthMenu
		});
		
		$("#pbDocumentTable").DataTable({
			"lengthMenu":lengthMenu
		});
		
		$("#cbDocumentTable").DataTable({
			"lengthMenu":lengthMenu
		});
		mobiletable();
});
</script>
<script type="text/javascript">
	var date = new Date();
	date.setDate(date.getDate());

	
	$('.Pickdate').datepicker({
		startDate : "+1d"
	});
</script>