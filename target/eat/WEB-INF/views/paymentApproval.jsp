<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@taglib uri = "http://www.springframework.org/tags/form" prefix = "sf"%>
<!-- context path  -->
<% String context=request.getContextPath(); %>
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/${appMode}/tilescommon/css/fioristyle.css?appVer=${appVer}"/>
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/${appMode}/tilescommon/css//borderTab.css?appVer=${appVer}">
<script src="<%=context%>/resources/${appMode}/commons/js/utility.js?appVer=${appVer}"></script>
<script src="<%=context%>/resources/${appMode}/commons/js/formFields.js?appVer=${appVer}"></script>
 <script src="<%=request.getContextPath()%>/resources/${appMode}/tilescommon/js/kendo.all.min.js?appVer=${appVer}"></script>
               <script>
                $(document).ready(function() {
                    $("#tabstrip").kendoTabStrip();                   
                    });               
                </script>
            
        <div class="full-container">
            <!-- left-side start-->
            <div class="clearfix"></div>
            <div id="mobile_first_container" class="left-side col-md-3 no-marg">
                <div class="detailsheader">
        	<div class="col-sm-3 text-center brdrgt"><label>Payment Approval (5)</label></div>
        </div>               
                <div class="input-group">
                    <div class="input-group-btn search-panel">
                        <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">
                            <span id="search_concept">Filter by</span> <span class="caret"></span>
                        </button>
                        <ul class="dropdown-menu" role="menu">
                            <li style="display:none;"><a href="#contains"><input type="radio" name="filterBy" value="none" checked=checked/></a></li> 
                            <li onclick="searchByStatus('A');"><a>Approved</a></li>
                            <li onclick="searchByStatus('R');"><a>Rejected</a></li>
                            <li onclick="searchByStatus('P');"><a>Pending</a></li>
                        </ul>
                    </div>
                    <div style="display: none;">
	                    <input type="text" id="approveStatus" value="Y"/>
	                    <input type="text" id="rejectStatus" value="N"/>
	                </div>
                     <input type="hidden" name="search_param" value="all" id="search_param">         
                <input type="text" class="form-control" name="x" id="searchLiteralId" placeholder="Search term...">
                <span class="input-group-btn">
                    <button class="btn btn-default" type="button" id = "searchBtn"><span class="glyphicon glyphicon-search"></span></button>
                </span>
                </div>
                <ul id="leftPane" class="nav nav-tabs tabs-left example leftPaneData">
                </ul>
                <p id="pagination-here"></p>
                <div class="clearfix"></div>
            </div>
            <!-- left-side end-->

            <!-- right-side start-->
            <div id="mobile_second_container" class="right-side col-md-9 no-marg">
            <div class="detailsheader toptabbrd">
        	<div class="col-sm-9 text-center"><label>Payment Approval</label></div>
        </div>
                <div class="clearfix"></div>
                <div class="tab-content">
                    <!-- Master tab start-->
                  <input type="hidden" id="loginRole" value="${role}"/> 
                    <div class="tab-pane active in" id="tenAucData">
                        <div class="card">
                     
                            <div class="posrelative" id="example">                            
                                <div class="demo-section k-content">
                                    <div id="tabstrip" class="Firsttab">
                                        <ul><li id="paymentDetailTab" class="k-state-active">Approval</li>
                                        </ul>

                                        <!--fields of field group 1  -->
                                        <div>
                                        <sf:form id="paymentFormId" method="POST" autocomplete="off" modelAttribute="paymentDetail">
                                          <div class="detailscont">
  							 				
    									</div> 
                                            <div class="form-group">
                                                <div class="col-sm-12">
                                                    <h4><b>Payment Approval</b></h4>
                                                    <hr>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                            <input type="hidden"  name="partner.bPartnerId" id="partnerId"/>
                                            <input type="hidden"  name="paymentDetailId" id="paymentdetailId"/>
                                            <input type="hidden"  name="" id="isFOApproved"/> 
        									<input type="hidden"  name="" id="isFAApproved"/>
        									<input type="hidden"  name="" id="tahdrId"/>
                                             <div class="col-sm-4">
													<div class="styled-input">
														<input type="text" id="partnerName" name=""  readonly="readonly"/>
														<label>Firm Name<span class="red">*</span></label>
														<span></span>
													</div>
												</div>
                                                 <div class="col-sm-4">
													<div class="styled-input">
														<input type="text" id="vendorTypePayment" name="" readonly="readonly"/>
														<label>Payment For<span class="red">*</span></label>
														<span></span>
													</div>
												</div>
                                                <div class="col-sm-4">
													<div class="styled-input">
														<input type="text" id="paymentType" name="" readonly="readonly"/>
														<input type="hidden" id="paymentTypeCode" name="paymentType.code" readonly="readonly"/>
														<label>Payment Type<span class="red">*</span></label>
														<span></span>
													</div>
												</div>
												</div>
												<div class="clearfix"></div>
												<div class="form-group">
												<!-- <div class="col-sm-4">
															<label><b>Mode of Payment:</b> </label> 
															<input type="radio"  value="Y" id="paymentMode"  name="" checked readonly="readonly"/>By DD 
												</div> -->
												<div class="col-sm-4">
													<div class="styled-input">
														<input type="text" id="paymentMode" name="" readonly="readonly"/>
														<label>Mode of Payment<span class="red">*</span></label>
														<span></span>
													</div>
												</div>
												<div class="col-sm-4">
													<div class="styled-input">
														<input type="text" id="amount" name="" readonly="readonly"/>
														<label>Amount(in Rs.)<span class="red">*</span></label>
														<span></span>
													</div>
												</div>
												<div class="col-sm-4">
													<div class="styled-input">
														<input type="text" id="gstin" name="" readonly="readonly"/>
														<label>GST Identification Number(GSTIN)<span class="red">*</span></label>
														<span></span>
													</div>
												</div>
												</div>
												<div class="clearfix"></div>
												<div class="form-group">
												<div class="col-sm-4">
													<div class="styled-input">
														<input type="text" id="gstSAC" name="" readonly="readonly"/>
														<label>GST(@18% on Amount:SAC No.998599)in Rs.<span class="red">*</span></label>
														<span></span>
													</div>
												</div>
												<div class="col-sm-4">
													<div class="styled-input">
														<input type="text" id="total" name="" readonly="readonly" />
														<label>Total Amount Including GST in Rs.<span class="red">*</span></label>
														<span></span>
													</div>
												</div>
												<div class="col-sm-4 ddFields">
                                                    <div class="form-group">
                                                        <label id="paymentDate">DD Date<span class="red">*</span></label>
                                                        <div class="input-group date" data-provide="datepicker">
                                                            <input type="text" class="form-control" id="ddDate" name="" readonly="readonly">
                                                            <div class="input-group-addon">
                                                                <span class="glyphicon glyphicon-th"></span>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                               
                                                </div>
                                                <div class="clearfix"></div>
                                                <div class="form-group ">
                                                 <div class="col-sm-4 BGFields">
                                                    <div class="form-group">
                                                        <label>Validity Date<span class="red">*</span></label>
                                                        <div class="input-group date" data-provide="datepicker">
                                                            <input type="text" class="form-control" id="validityDate" name="" readonly="readonly">
                                                            <div class="input-group-addon">
                                                                <span class="glyphicon glyphicon-th"></span>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="col-sm-4 ddFields">
													<div class="styled-input">
														<input type="text" id="referenceNo" name="" readonly="readonly"/>
														<label id="paymentNo">Demand Draft Number<span class="red">*</span></label>
														<span></span>
													</div>
												</div>
												<div class="col-sm-4 ddFields micrCode">
													<div class="styled-input">
														<input type="text" id="micrCode" name="" readonly="readonly"/>
														<label>MICR Code<span class="red">*</span></label>
														<span></span>
													</div>
												</div>
												<div class="col-sm-4 ddFields">
													<div class="styled-input">
														<input type="text" id="bankName" name="" readonly="readonly"/>
														<label>Bank Name<span class="red">*</span></label>
														<span></span>
													</div>
												</div> 
												</div>
												<div class="clearfix"></div>
												<div class="form-group">
												<div class="col-sm-4 ddFields">
													<div class="styled-input">
														<input type="text" id="branchName" name="" readonly="readonly"/>
														<label>Branch Name<span class="red">*</span></label>
														<span></span>
													</div>
												</div>
												<div class="col-sm-4 BGFields" id="bankGuaranteeAttachment">
												<label>Attachment<span class="red">*</span></label>
												<div class="input-group">
													<input type="file" id="BGId" data-id="BGAttachment" data-name="BGAttach" data-anchor="a_bgAttachment" class="form-control" readonly="readonly"> 
													<input type="hidden" id="BGAttachment" name="" class="form-control" /> 
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
												<div class="col-sm-4">
															<label><b>Realisation Status:</b> </label> 
															<input type="radio"  value="Y" id="realisationStatusY"  name=""  checked/>Yes
															<input type="radio"  value="N" id="realisationStatusN"  name=""  />No
												</div>
												<div class="clearfix attchmentaftershow" style="display:none;"></div>
												<div class="col-sm-4">
                                                    <div class="form-group">
                                                        <label>Realisation Date</label>
                                                        <div class="input-group date dateSetter" data-provide="datepicker">
                                                            <input type="text" class="form-control" id="realisationDate" name="realisationDate">
                                                            <div class="input-group-addon">
                                                                <span class="glyphicon glyphicon-th"></span>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
												<!-- </div>
												<div class="clearfix"></div>
												<div class="form-group" > -->
												<div class="col-sm-4">
                                                    <div class="form-group">
                                                        <label>Money Receipt Date</label>
                                                        <div class="input-group date dateSetter" data-provide="datepicker">
                                                            <input type="text" class="form-control" id="moneyReceiptDate" name="moneyReceiptDate">
                                                            <div class="input-group-addon">
                                                                <span class="glyphicon glyphicon-th"></span>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="col-sm-4">
													<div class="styled-input">
														<input type="text" id="moneyReceiptNo" name="moneyReceiptNumber" />
														<label>Money Receipt Number</label>
														<span></span>
													</div>
												</div>
												</div>
												<div class="clearfix"></div>
												<!-- <div class="form-group" >
                                                    <div id="financeOperatorComment">
                                                    <label class="col-sm-2"><b>Add Operator Comment:</b> </label>
														<div class="col-sm-4">
															<textarea class="form-control" name="foComment" id="foComment" maxlength="255" ></textarea>
														</div>
														</div>
														<div id="financeAdminComment" style="display:none;">
														<label class="col-sm-2"><b>Add Admin Comment:</b> </label>
														<div class="col-sm-4">
															<textarea class="form-control" name="faComment" id="faComment" maxlength="255" ></textarea>
														</div>
														</div>
														<div class="col-sm-6">
															<label><b>Select Status:</b> </label> 
															<input type="radio"  value="Y" id="approveBtnId" name=""/>Approve 
															<input type="radio" id="rejectBtnId" name="" value="N" /> Reject 
														</div>
											  </div> -->
											   <div class="form-group OperatorComment" >
		                                                    <label class="col-sm-2"><b>Add Operator Comment:</b> </label>
																<div class="col-sm-4">
															<textarea class="form-control disableByStatus" name="foComment" id="foComment" maxlength="255" ></textarea>
														</div>
														<div class="col-sm-6 FoStatus">
															<label><b>Select Status:</b> </label> 
															<input type="radio"  value="Y" id="approveFOBtnId" class="FOperatorStatus disableByStatus" name="" checked/>Approve 
															<input type="radio" id="rejectFOBtnId" name="" class="FOperatorStatus  disableByStatus" value="N" /> Reject 
														</div>
													  </div>
													   <div class="form-group AdminComment" >
		                                                    <label class="col-sm-2"><b>Add Admin Comment:</b> </label>
														<div class="col-sm-4">
															<textarea class="form-control disableByStatus" name="faComment" id="faComment" maxlength="255" ></textarea>
														</div>
														<div class="col-sm-6">
															<label><b>Select Status:</b> </label> 
															<input type="radio"  value="Y" id="approveFABtnId" name="" class="FAdminStatus disableByStatus" checked/>Approve 
															<input type="radio" id="rejectFABtnId" name="" value="N" class="FAdminStatus disableByStatus"/> Reject 
														</div>
													  </div> 
                                              <div class="col-sm-2" id='payReceipt'>
                                             	 		<a id="payReceiptLinkId" target="_blank"><b><span class="glyphicon glyphicon-plus-sign paddright"></span>Receipt</b> </a>
                                             	 	</div>
                                     <div class="col-sm-12 text-center positionABS" style="margin-bottom:10px;">
                                         <!-- <button class="btn-all btn btn-info k-tabstrip-prev pull-left tab-li-prev">Previous</button> -->
                                        <button class="btn btn-info save submitPaymentDetail disableByStatus" >Save</button>
                                        <button class="btn btn-info cancel cancelPaymentDetail disableByStatus" >Cancel</button>
                                   	    <!-- <button class="btn-all btn btn-info k-tabstrip-next pull-right tab-li-next">Next</button> -->
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
<!--js included  -->
<script src="<%=request.getContextPath()%>/resources/${appMode}/master/js/paymentApproval.js?appVer=${appVer}"></script>
<!--js included  -->

<script type="text/javascript">
	var date = new Date();
	date.setDate(date.getDate());

</script>

           
