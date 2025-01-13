
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
                });
               
            </script>
<!-- <style>
.disabled {
   color: darkgrey;
   background-color: grey;
}
</style> -->
    
        <!-- Header end -->
        <div class="full-container">
            <!-- left-side start-->
            <div class="clearfix"></div>
            <input type="hidden" value="${documentType }" id="documentType"/>
            <c:if test="${documentType.equalsIgnoreCase('Tender')}">
			<input type="hidden" class="documentType" value="${documentType}" />
	</c:if>
	<c:if test="${documentType.equalsIgnoreCase('Auction')}">
			<input type="hidden" class="documentType" value="${documentType}" />
	</c:if>
            <div id="mobile_first_container" class="left-side col-md-3 no-marg">
            <div class="detailsheader leftPaneHeader">
        		
       		</div>         
                <div class="input-group">
                <div class="input-group-btn search-panel">
                    <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">
                    	<span id="search_concept">Filter by</span> <span class="caret"></span>
                    </button>
                    <ul class="dropdown-menu" role="menu">
                      <li><a href="#contains"><input type="radio" name="filterBy" value="tahdr.tahdrCode" /> Code</a></li>
                      <li class="divider"></li>
                      <li><a href="#all"><input type="radio" name="filterBy" value="tahdr.title" /> Name</a></li>
                    </ul>
                </div>
                <input type="hidden" name="search_param" value="all" id="search_param">         
                <input type="text" class="form-control" name="x" id="searchLiteralId" placeholder="Search term...">
                <span class="input-group-btn">
                    <button class="btn btn-default" type="button" id = "searchBtn"><span class="glyphicon glyphicon-search"></span></button>
                </span>
            </div>
                <c:if test="${documentType.equalsIgnoreCase('Tender')}">
                <div class="btn-group btnmrg toggleTab" openTab="tenderDet" data-toggle="buttons">
				    <label class="btn btn-primary" id="labelProcurement">
						<input type="radio" name="toggleTenderType" id="procurement" value="PT">
						<span class="glyphicon glyphicon-ok"></span> Procurement 
                    </label>
                    <label class="btn btn-primary" id="labelWorks">
                         <input type="radio" name="toggleTenderType" id="works" value="WT" checked>
                         <span class="glyphicon glyphicon-ok"></span> Works
                    </label>
                </div>
                </c:if>
				<c:if test="${documentType.equalsIgnoreCase('Auction')}">
					<input type="radio" name="toggleTenderType" checked style="display:none;" value="FA" checked>
				</c:if>
                <ul id="example" class="nav nav-tabs tabs-left example">
                    <li class="active">
                        
                    </li>
                </ul> 
                <p id="pagination-here"></p>               
                <div class="clearfix"></div>
            </div>
            <!-- left-side end-->

            <!-- right-side start-->
            <div id="mobile_second_container" class="right-side col-md-9 no-marg">
             <div class="detailsheader toptabbrd">
        		<div class="col-sm-9 text-center"><label>${documentType} Purchase </label></div>
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
                                            <li class="k-state-active tenderDet">Todays Top ${documentType} </li>
                                            <li class="paymentTab">${documentType} Fees Details</li>
                                        </ul>

                                        <!--fields of field group 1  -->
                                        <div>
                                        <div class="detailscont">
  							 				<div class="col-md-12" id="masterDetails">
                                
                           					 </div>
    									</div>
                                            <div class="form-group">
                                                <div class="col-sm-6">
                                                    <h4><b>Todays Top ${documentType} </b></h4>
                                                    <hr>
                                                </div>
                                                <div id="paymentStatusDiv" class="col-xs-6 errorlabel" style="margin-top: 10px; display: none;">
												<label id="paymentStatusLabel" style="color: red"></label>
												</div>
                                            </div>
                                            <div class="form-group readonly">
                                            <form id="tendertab">
	                                            <div class="col-sm-4">
	                                                    <div class="styled-input">
	                                                        <input type="text" id="tenderName" name="" required />
	                                                        <label>${documentType} Name</label>
	                                                        <span></span>
	                                                    </div>
	                                                </div>
	                                                <div class="col-sm-4">
                                                    <div class="styled-input">
                                                        <input type="text" id="tenderTitle" name="" required />
                                                        <label>${documentType} Title</label>
                                                        <span></span>
                                                    </div>
                                                </div>
                                                <div class="col-sm-4">
                                                    <div class="styled-input">
                                                        <input type="text" id="description" name="description" required />
                                                        <label>Description Of ${documentType} </label>
                                                        <span></span>
                                                    </div>
                                                </div>
                                                <div class="col-sm-4">
                                                    <div class="styled-input">
                                                        <input type="text" id="estimatedCost" name="estimatedCost" required />
                                                        <label>Estimated Cost (INR in Lakhs)</label>
                                                        <span></span>
                                                    </div>
                                                </div>
                                                <div class="col-sm-4">
                                                    <div class="styled-input">
                                                        <input type="text" id="tenderFees" name="tenderFees" required />
                                                        <label>${documentType} Fees [INR]</label>
                                                        <span></span>
                                                    </div>
                                                </div>
                                                <div class="col-sm-4">
                                                    <label>${documentType} Closing Date</label>
                                                        <div class="input-group date" data-provide="datepicker">
                                                            <input type="text" class="form-control TenderSaleOpeningDate" id="tenderClosingDate">
                                                            <div class="input-group-addon">
                                                                <span class="glyphicon glyphicon-th"></span>
                                                            </div>
                                                        </div>
                                                </div>
                                                </form>
                                            </div>
                                             <div class="form-group">
                                             <div class="styled-input" id="purchaseActionBtnDivId">
                                             	 <div class="col-sm-2" id="purchase">
                                             	 	<a id="purchaseTenderLinkId" class="toggleTab" openTab="paymentTab"><b><span class="glyphicon glyphicon-plus-sign paddright"></span><span id="purchaseBtn">Purchase ${documentType} </span></b></a>
                                             	 <button class="btn btn-info acceptinvitation" id="acceptinvitation">Participate</button>
                                             	 </div>
                                             	 <!-- <div class="col-sm-2" id='payOnlineLink'>
                                             	 	<a id="payOnclickLinkId" ><b><span class="glyphicon glyphicon-plus-sign paddright"></span>Pay Online</b> </a>
                                                  	 </div> -->

                                             	 <div class="col-sm-6" id="viewDetail">
                                             	 	<div id="viewDetailLinkId"><b>
                                             	 	<span class="glyphicon glyphicon-plus-sign paddright"></span>View Tender Document:</b> 
                                             	 	<span id="generatedDocAnchor"></span>
                                             	 	</div>
                                             	 </div>
                                             	 <div class="col-sm-2" id='payReceipt'>
                                             	 	<a id="payReceiptLinkId" target="_blank"><b><span class="glyphicon glyphicon-plus-sign paddright"></span>Receipt</b> </a>
                                             	 </div>
                                             	 <div class="col-sm-2">
                                             	 	<a id="downloadDocLinkId" style="display:none;" onclick="downloadDoc()"><b><span class="glyphicon glyphicon-plus-sign paddright"></span>View Tender Detail</b> </a>
                                             	 </div>
                                             	 </div>
                                             </div>
                                            <div class="clearfix"></div>
                                        </div>
                                        <!--fields of field group 1  -->
                                        <!--fields of field group 2  -->
                                        <div>
                                        <div class="detailscont">
  							 				<div class="col-md-12" id="masterDetails">
                                
                           					 </div>
    									</div>
                                            <div class="form-group">
                                                <div class="col-sm-12">
                                                    <h4><b>${documentType} Fees Details</b></h4>
                                                    <hr>
                                                </div>
                                            </div>
                                        <sf:form id="tahdrPurchaseDetailForm" modelAttribute="paymentDetail" method="POST" action="tahdrDocPurchase">
                                        	<input id="tahdrId" type="hidden" name="tahdr.tahdrId">
                                        	<input id="tahdrDetailId" type="hidden" name="tahdrDetail.tahdrDetailId">
                                        	  <input id="paymentDetailId" type="hidden" name="paymentDetailId">
                                        	  <input type="hidden" name="isActive" value="Y" />
                                        	  <input type="hidden" id="isTrader_Flag" name="isTrader" value="" disabled/>
                                        	  
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
                                                    <label>Amount in INR<span class="red">*</span></label>
                                                    <span></span>
                                                </div>
                                            </div>
                                            <div class="col-sm-4 readonly" id="igstDiv">
                                                <div class="styled-input">
                                                	
                                                    <input readonly="readonly" type="text" id="igst" name="igst" required />
                                                    <label>IGST in %<span id="igstRate"></span><span class="red">*</span></label>
                                                    <span></span>
                                                </div>
                                            </div>
                                            <div class="col-sm-4 readonly" id="cgstDiv">
                                                <div class="styled-input">
                                                	
                                                    <input readonly="readonly" type="text" id="cgst" name="cgst" required />
                                                    <label>CGST in %<span class="red">*</span></label>
                                                    <span></span>
                                                </div>
                                            </div>
                                            <div class="col-sm-4 readonly" id="sgstDiv">
                                                <div class="styled-input">
                                                	
                                                    <input readonly="readonly" type="text" id="sgst" name="sgst" required />
                                                    <label>SGST in %</span><span class="red">*</span></label>
                                                    <span></span>
                                                </div>
                                            </div>
                                            <div class="col-sm-4 readonly">
                                                <div class="styled-input">
                                                	<input type="hidden" id="gst" name="gst" required />
                                                    <input type="text" id="gstAmount" name="gstAmount" required />
                                                    <label>GST in INR (<span class="gstRate"></span><span>% on Amount: SAC No.9984)</span><span class="red">*</span></label>
                                                    <span></span>
                                                </div>
                                            </div>
                                            <div class="col-sm-4 readonly">
                                                <div class="styled-input">
                                                    <input type="text" id="totalFee" required />
                                                    <label>Total Amount Including GST in INR<span class="red">*</span></label>
                                                    <span></span>
                                                </div>
                                            </div>
                                            <div class="col-sm-4 readonly">
                                               <div class="styled-input">
                                                 <select id="paymentType" name="paymentType.paymentTypeId" required="required">
                                                 </select>
                                                 <label>Type of Payment<span class="red">*</span></label>
                                                 <span></span>
                                               </div>
                                            </div>
                                            <div class="clearfix"></div>
                                            <c:if test="${documentType.equalsIgnoreCase('Tender')}">
                                            <div class="col-sm-4" id="isTraderDiv">
			                                       <label class="checkbox-inline" style="margin-top:40px;">
			                                       <input type="checkbox" value="Y" id="isTrader" onchange="isTraderChanged(this)" name="isTrader" >
			                                       		Purchase as Trader
			                                       </label>
			                                </div>
			                                </c:if>
			                                <div class="clearfix"></div>
                                            <c:if test="${documentType.equalsIgnoreCase('Tender')}">
													<div class="col-sm-4" id="factorySeclectId">
                                                <div class="styled-input">
                                                   <select id="factoryId" name="partnerOrg.partnerOrgId" class="factory requiredField dropDown" required="required">
												   </select> 
												   <label>Factory<span class="red">*</span></label>
                                                </div>
                                            </div>
												</c:if>
                                            <div class="col-sm-4">
                                              <div class="styled-input">
                                                 <select id="paymentMode" class="paymentMode" name="paymentMode" onchange="formFieldChange(this)" required="required">
                                                 </select>
                                                 <label>Mode Of Payment<span class="red">*</span></label>
                                                 <span></span>
                                              </div>
                                            </div>
                                           <div class="clearfix"></div>
                                            <div class="form-group">
                                            <div class="col-sm-4">
                                                <div class="styled-input onlinePaymentDocNoDivId">
                                                   <input type="text" id="onlinePaymentDocNo" class="readonly"/>
                                                    <label id="onlinePaymentDocNolbl">Doc Number</label>
                                                    <span></span>
                                                </div>
                                            </div>
                                            <div class="col-sm-4">
                                                <div class="styled-input onlinePaymentDocNoDivId">
                                                    <input type="text" id="onlinePaymentDate" class="readonly"/>
                                                    <label id="onlinePaymentDocNolbl">Payment date:</label>
                                                    <span></span>
                                                </div>
                                            </div>
                                            </div>
                                            
                                           <div class="form-group">
                                            <div class="col-sm-4"  >
                                              <div class="form-group" id="paymentDateDiv">
                                                 <label id="paymentDate">Payment Date<span class="red">*</span></label>
                                                 <div class="input-group date pastDate" data-provide="datepicker">
                                                     <input type="text" class="form-control requiredField" id="dateOfPayment" name="paymentDate">
                                                     <div class="input-group-addon">
                                                         <span class="glyphicon glyphicon-th"></span>
                                                     </div>
                                                 </div>
                                              </div>
	                                        </div>
	                                        <div class="col-sm-4">
	                                           <div class="styled-input" id="referenceNoDiv">
                                                 <input type="text" id="referenceNo" name="referenceNo" class="requiredField onlyNumber" maxlength="6" />
                                                 <label id="paymentNo">Demand Draft Number<span class="red">*</span></label>
                                                 <span></span>
                                           	   </div>
                                            </div>
                                          	<div class="col-sm-4">
	                                           <div class="styled-input" id="micrCodeDivId">
	                                              <input type="text" id="micrCode" name="micrCode" class="requiredField onlyNumber" maxlength="9"/>
	                                              <label id="paymentRefNo">MICR Code<span class="red">*</span></label>
	                                              <span></span>
	                                           </div>
                                          	</div>
                                          	<div class="clearfix"></div>
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
                                            </div>
                                              <div class="col-sm-12 text-center positionABS" style="margin-bottom:10px;">
                                        <!-- <button class="btn-all btn btn-info k-tabstrip-prev btnPrevious pull-left">Previous</button> -->
                                        <button class="btn btn-info tahdrPurchaseDetail save">Save</button>
                                        <button id="cancelBtn" class="btn-all btn btn-info k-tabstrip-next btnNext cancel toggleTab" openTab="tenderDet">Cancel</button>
                                    </div>
                                           </sf:form>  
                                   
                                    <form class="col-sm-4 text-right" action="onlineTenderFeePayment" method = "post" id = "onlineTenderFeeForm" >
                                        	 <input id="optahdrId" type="hidden" name="tahdr.tahdrId">
                                        			<input id="optahdrDetailId" type="hidden" name="tahdrDetail.tahdrDetailId">
                                        			<input type="hidden" id="factoryIdOP" name="partnerOrg.partnerOrgId" >
                                        			<input type="hidden" id="isTraderOP"  name="isTrader" >
	                                           		<input type = "hidden" name="paymentMode" value = "OP">
	                                           		<input type="hidden" name="${_csrf.parameterName}"  value="${_csrf.token}" />
	                                           		<button type = "submit" class="btn btn-info save" style="display:none; margin-top:-25px;" id = "payOnlineTahrdFeeBtn">Pay Online</button>
	                                        
	                                        </form>
	                                      
                                                                     
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
<script src="<%=request.getContextPath()%>/resources/${appMode}/commons/js/springform.js?appVer=${appVer}"></script>
<script src="<%=request.getContextPath()%>/resources/${appMode}/commons/js/utility.js?appVer=${appVer}"></script>
<script	src="<%=request.getContextPath()%>/resources/${appMode}/commons/js/formFields.js?appVer=${appVer}"></script>
<script	src="<%=request.getContextPath()%>/resources/${appMode}/commons/js/tahdrPurchase.js?appVer=${appVer}"></script>
<script type="text/javascript">

	var date = new Date();
	date.setDate(date.getDate());
	/* show only past date including today */
	$('.pastDate').datepicker({
		endDate : date
	});
</script>