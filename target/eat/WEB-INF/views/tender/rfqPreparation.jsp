<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<!-- context path  -->
<%
	String context = request.getContextPath();
%>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/resources/${appMode}/tilescommon/css/fioristyle.css?appVer=${appVer}" />
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/resources/${appMode}/tilescommon/css/borderTab.css?appVer=${appVer}">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/resources/${appMode}/tilescommon/css/jquery-ui.css?appVer=${appVer}">
	<link rel="stylesheet"
	href="<%=request.getContextPath()%>/resources/${appMode}/tilescommon/css/mobile.css?appVer=${appVer}">
	<script src="<%=context%>/resources/${appMode}/tilescommon/js/kendo.all.min.js?appVer=${appVer}"></script>
<%-- <script src="<%=request.getContextPath()%>/resources/${appMode}/commons/js/springform.js?appVer=${appVer}"></script> --%>
<script src="<%=request.getContextPath()%>/resources/${appMode}/commons/js/utility.js?appVer=${appVer}"></script>
<script src="<%=request.getContextPath()%>/resources/${appMode}/commons/js/loadList.js?appVer=${appVer}"></script>
<script src="<%=request.getContextPath()%>/resources/${appMode}/commons/js/loadItemList.js?appVer=${appVer}"></script>
<script src="<%=request.getContextPath()%>/resources/${appMode}/commons/js/formFields.js?appVer=${appVer}"></script>
<script src="<%=request.getContextPath()%>/resources/${appMode}/commons/js/liCache.js?appVer=${appVer}"></script>
<script src="<%=request.getContextPath()%>/resources/${appMode}/partner/js/uploadFile.js?appVer=${appVer}"></script>
<script src="<%=request.getContextPath()%>/resources/${appMode}/transaction/js/tenderPreparation.js?appVer=${appVer}"></script>
<script src="<%=request.getContextPath()%>/resources/${appMode}/transaction/js/tahdr.js?appVer=${appVer}"></script>
<script src="<%=request.getContextPath()%>/resources/${appMode}/transaction/js/tahdrDetail.js?appVer=${appVer}"></script>
<script src="<%=request.getContextPath()%>/resources/${appMode}/transaction/js/tahdrDate.js?appVer=${appVer}"></script>
<script src="<%=request.getContextPath()%>/resources/${appMode}/transaction/js/tahdrMaterial.js?appVer=${appVer}"></script>
<script src="<%=request.getContextPath()%>/resources/${appMode}/transaction/js/tahdrMaterialGtp.js?appVer=${appVer}"></script>
<script
	src="<%=request.getContextPath()%>/resources/${appMode}/transaction/js/tahdrMaterialParts.js?appVer=${appVer}"></script>
<script src="<%=request.getContextPath()%>/resources/${appMode}/transaction/js/sectionDocuments.js?appVer=${appVer}"></script>
<script src="<%=request.getContextPath()%>/resources/${appMode}/transaction/js/standardCustomDoc.js?appVer=${appVer}"></script>

<script src="<%=request.getContextPath()%>/resources/${appMode}/commons/js/commonValidation.js?appVer=${appVer}"></script>
<script src="<%=request.getContextPath()%>/resources/${appMode}/transaction/js/tahdrConfirmation.js?appVer=${appVer}"></script>
<script src="<%=request.getContextPath()%>/resources/${appMode}/tilescommon/js/jquery-ui.js?appVer=${appVer}"></script>
<script src="<%=request.getContextPath()%>/resources/${appMode}/tilescommon/js/moment.js?appVer=${appVer}"></script>
<script
	src="<%=request.getContextPath()%>/resources/${appMode}/tilescommon/js/bootstrap-datetimepicker.js?appVer=${appVer}"></script>
<script
	src="<%=request.getContextPath()%>/resources/${appMode}/tilescommon/js/bootstrap-datepicker.js?appVer=${appVer}"></script>
<script>
  $( function() {
    $( "#orderTenderDocs" ).sortable();
    $( "#orderTenderDocs" ).disableSelection();
  });
  </script>
<style>
.checkbox10 {
	margin-top: 10px !important;
}

.frgrp {
	overflow: inherit;
}
.form-control2 {
    width: 100% !important;
}
</style>
<script>
	$(document).ready(function() {

		$("#tabstrip").kendoTabStrip();
		$("#tabstrip2").kendoTabStrip();
		$("#tabstrip3").kendoTabStrip();
		$("#tabstrip4").kendoTabStrip();
		$("#tabstrip5").kendoTabStrip();

	});
</script>
<style>
.checkbox-inline {
	margin-top: 40px;
}
</style>

<div class="full-container">
	<!-- left-side start-->
	<div class="clearfix"></div>
	<input type="hidden" id="dcumentType" value="${dcumentType}" />
	<input type="hidden" id="pageType" value="${pageType}" />
	<c:if test="${dataUrl.equalsIgnoreCase('tenderApprovalData')}">
			<input type="hidden" class="dataUrl" value="${dataUrl}" />
	</c:if>
	<c:if test="${dataUrl.equalsIgnoreCase('tenderPreparationData')}">
			<input type="hidden" class="dataUrl" value="${dataUrl}" />
	</c:if>
	<c:if test="${dataForTypeCode.equalsIgnoreCase('getTAHDRApprovalByTypeCode')}">
			<input type="hidden" class="dataUrlTypeCode" value="${dataForTypeCode}" />
	</c:if>
	<c:if test="${dataForTypeCode.equalsIgnoreCase('getTAHDRByTypeCode')}">
			<input type="hidden" class="dataUrlTypeCode" value="${dataForTypeCode}" />
	</c:if>
	<c:if test="${documentType.equalsIgnoreCase('Tender')}">
			<input type="hidden" class="documentType" value="${documentType}" />
	</c:if>
	<c:if test="${documentType.equalsIgnoreCase('Auction')}">
			<input type="hidden" class="documentType" value="${documentType}" />
	</c:if>
	<c:if test="${documentType.equalsIgnoreCase('Request For Proposal')}">
			<input type="hidden" class="documentType" value="${documentType}" />
	</c:if>
	<div id="mobile_first_container" class="left-side col-md-3 no-marg">
		<div class="detailsheader leftPaneHeader"></div>
		
		<div class="input-group">
                <div class="input-group-btn search-panel">
                    <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">
                    	<span id="search_concept">Filter by</span> <span class="caret"></span>
                    </button>
                    <ul class="dropdown-menu" role="menu">
                      <li><a href="#contains"><input type="radio" name="filterBy" value="tahdrCode" /> Code</a></li>
                      <!-- <li class="divider"></li>
                      <li><a href="#all"><input type="radio" name="filterBy" value="tahdrStatusCode" /> Status</a></li> -->
                    </ul>
                </div>
                <input type="hidden" name="search_param" value="all" id="search_param">         
                <input type="text" class="form-control" name="x" id="searchLiteralId" placeholder="Search term...">
                <span class="input-group-btn">
                    <button class="btn btn-default" type="button" id = "searchBtn"><span class="glyphicon glyphicon-search"></span></button>
                    <button class="btn btn-default addnewlist" type="button"><span class="glyphicon glyphicon-plus"></span></button>
                    <button class="btn btn-default" onClick="loadNewList(event)" type="button"><span class="glyphicon glyphicon-refresh"></span></button>
                </span>
            </div>
		<c:if test="${documentType.equalsIgnoreCase('Tender')}">
			<div class="btn-group btnmrg" data-toggle="buttons">
				<label class="btn btn-primary toggleNewTab"
					openTab="tenderBaseInfoTab"> <input type="radio"
					name="tenderTypeCodeToggle" value="PT"> <span
					class="glyphicon glyphicon-ok"></span> Procurement
				</label> <label class="btn btn-primary active toggleNewTab"
					openTab="tenderBaseInfoTab"> <input type="radio"
					name="tenderTypeCodeToggle" checked value="WT"> <span
					class="glyphicon glyphicon-ok"></span> Works
				</label>
			</div>
		</c:if>
		<c:if test="${documentType.equalsIgnoreCase('Auction')}">
			<input type="radio" name="tenderTypeCodeToggle" checked
				style="display: none;" value="FA">
		</c:if>
		<c:if test="${documentType.equalsIgnoreCase('Reverse Auction')}">
			<input type="radio" name="tenderTypeCodeToggle" checked
				style="display: none;" value="RA">
		</c:if>
		<c:if test="${documentType.equalsIgnoreCase('Request For Proposal')}">
			<input type="radio" name="tenderTypeCodeToggle" checked
				style="display: none;" value="RFQ">
		</c:if>
		<ul id="leftPane" class="nav nav-tabs tabs-left leftPaneData">
		</ul>
		<p id="pagination-here"></p>
		<div class="clearfix"></div>
	</div>
	<div id="mobile_second_container" class="right-side col-md-9 no-marg">
		<div class="detailsheader toptabbrd">
			<div class="col-sm-9 text-center">
				<label>${documentType} Preparation Details</label>
			</div>
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
									<li id="tenderBaseInfoTab" data-parentTab="" class="k-state-active normalli"><div class="tabcircle"><i class="fa fa-info-circle"></i></div>
										Base Information </li>
									<li class="CreatePrivateAuctiontab normalli" data-parentTab="tenderBaseInfoTab" style="display: none;"><div class="tabcircle"><i class="fa fa-address-card"></i></div>Vendor
										Selection</li>
									<li class="normalli readonly" data-parentTab="tenderBaseInfoTab" id="tahdrDetailTab"><div class="tabcircle"><i class="fa fa-calendar"></i></div>
										Details</li>
									<li class="normalli readonly" data-parentTab="tahdrDetailTab" id="impDatesTab"><div class="tabcircle"><i class="fa fa-calendar"></i></div>Important Dates</li>
									<li class="normalli readonly tenderDocsClass" data-parentTab="tahdrDetailTab" id="tenderDocTab"><div class="tabcircle"><i class="fa fa-file"></i></div>
										Documents</li>
									<li class="normalli readonly" data-parentTab="tahdrDetailTab" id="getTahdrMaterialList"
										attr-typeCode="procurementTender"><div class="tabcircle"><i class="fa fa-list"></i></div>List of items in
										</li>
									<li class="normalli readonly reqDocsClass " data-parentTab="tahdrDetailTab" id="sectionDocTab"><div class="tabcircle"><i class="fa fa-file-text-o"></i></div>Required Documents
										</li>
									<li class="confirmliclick readonly" data-parentTab="tahdrDetailTab"  id="confirmation" ><div class="tabcircle" id="tabcircle"><i class="fa fa-check-circle"></i></div>
										Confirmation</li>
								</ul>
								<!--fields of field group 1  -->
								<div>
									<div class="detailscont"></div>
									<sf:form class="" id="tahdrForm" action="createTahdr"
										method="POST" autocomplete="off" modelAttribute="tahdr">
										<div class="form-group">
											<div class="col-sm-6">
												<h4>
													<b>Base Information</b> 
												</h4>
											</div>
											<div class="col-sm-4">
												<div class="form-group">
													<label class="checkbox-inline" style="margin-top: 10px;">
														<input type="checkbox" name="isPrivateAuction" id="CreatePrivateAuction" >Create
														Private 
													</label>
												</div>
											</div>
											<div class="col-sm-6 text-right">
												<button type="button" onclick="addNewTahdr(event)"
													class="btn btn-default addTahd">
													<span class="glyphicon glyphicon-plus-sign"></span>Create New 
												</button>
												<button class="btn btn-default createVersion"
													onclick="createVersion(event);" style="display: none">
													<span class="glyphicon glyphicon-plus-sign"></span>Create
													Version
												</button>
												<button class="btn btn-default editTahdr"
													onclick="editTahdr(event)" style="display: none">
													<span class="glyphicon glyphicon-pencil"></span>Edit
												</button>
												<!-- <button class="btn btn-default deleteTahdr" onclick="deleteTahdr()"><span class="glyphicon glyphicon-trash"></span>Delete</button> -->
											</div>
											<hr>
										</div>
										<div id="tenderBaseForm">
											<div class="form-group">
												<div class="col-sm-4">
													<div class="styled-input">
														<input style="display: none" class="tahdrId" id="tenderID" name="tahdrId">
															 <input type="text" id="tahdrCode" name="tahdrCode" value="" class="requiredField alphaNumericWithSpaceAndSpecialCharacter" />
															 <input type="hidden" id="tahdrStatus" value=""/>
														<label> Code<span class="red">*</span></label>
														<span></span>
													</div>
												</div>
												<div class="col-sm-8">
													<div class="styled-input">
														<!-- <input type="text" id="tahdrTitle" name="title" value="" class="requiredField"> -->
														<textarea id="tahdrTitle" name="title"
															class="requiredField" maxlength="2000"></textarea>
														<label> Title<span class="red">*</span></label>
														<span></span>
													</div>
												</div>
												<div class="mobclearfix"></div>
												<c:if test="${documentType.equalsIgnoreCase('Tender')}">
													<div class="col-sm-4">
														<div class="styled-input">
															<select id="tahdrType"
																class="tahdrTypeCode readonly dropDown"
																name="tahdrTypeCode">
															</select> <label> Type<span class="red">*</span></label>
															<span></span>
														</div>
													</div>
												</c:if>
												<div class="mobclearfix"></div>
												<c:if test="${documentType.equalsIgnoreCase('Auction')}">
													<input type="hidden" value="FA" name="tahdrTypeCode">
												</c:if>
												<c:if test="${documentType.equalsIgnoreCase('Reverse Auction')}">
													<input type="hidden" value="RA" name="tahdrTypeCode">
												</c:if>
												<c:if test="${documentType.equalsIgnoreCase('Request For Proposal')}">
													<input type="hidden" value="RFQ" name="tahdrTypeCode">
												</c:if>
												<div class="mobclearfix"></div>
												<div class="col-sm-4">
													<div class="styled-input">
														<select id="department" class="departmentList  dropDown"
															name="department.departmentId">
														</select> <label>Department<span class="red">*</span></label> <span></span>
													</div>
												</div>
												<div class="col-sm-4">
													<div class="styled-input">
														<select id="bidTypeCode" class="bidTypeCode  dropDown"
															name="bidTypeCode">
														</select> <label>Bid Type<span class="red">*</span></label> <span></span>
													</div>
												</div>
												
												<div class="clearfix"></div>
												<div class="col-sm-4 ">
													<div class="styled-input">
														<select id="budgetType" class="budgetType  dropDown"
															name="budgetType">
														</select> <label>Budget Type<span class="red">*</span></label> <span></span>
													</div>
												</div>
												<div class="col-sm-4 " id="schemeNameDivId">
													<div class="styled-input">
														<input type="text" id="schemeName"
															class="schemeName requiredField  requiredalphabeticsWithSpace"
															name="schemeName"> <label>Scheme Name<span
															class="red">*</span></label> <span></span>
													</div>
												</div>
												<div class="col-sm-4 " id="schemeCodeDivId">
													<div class="styled-input">
														<input type="text" id="schemeCode"
															class="schemeCode requiredField" name="schemeCode">
														<label>Scheme Code<span class="red">*</span></label> <span></span>
													</div>
												</div>
												<div class="clearfix"></div>
												<div class="mobclearfix"></div>
												<div class="col-sm-4 ">
													<div class="styled-input">
														<select id="officeType" class="officeType  dropDown"
															name="officeType.locationTypeId" onchange="loadOfficeLocation()">
														</select> <label>Office Type<span class="red">*</span></label> <span></span>
													</div>
												</div>
												<div class="mobclearfix"></div>
												<div class="col-sm-4 ">
													<div class="styled-input">
														<select id="officeLocation"
															class="officeLocation dropDown"
															name="officeLocation.officeLocationId">
														</select> <label>Office Location<span class="red">*</span></label>
														<span></span>
													</div>
												</div>
												<div class="mobclearfix"></div>
												<c:if test="${documentType.equalsIgnoreCase('Auction')}">
													<div class="col-sm-4 " style="display: none;">
														<div class="form-group">
															<label class="checkbox-inline"> <input
																type="checkbox" value="Y" id="isAuction"
																name="isAuction" onchange="onCheckIsAuction();" checked>Is
																Reverse Auction
															</label>
														</div>
													</div>
												</c:if>
												<c:if
													test="${documentType.equalsIgnoreCase('Reverse Auction')}">
													<div class="col-sm-4">
														<div class="form-group">
															<label class="checkbox-inline readonly">
															<!--  <input type="checkbox" value="Y" id="isAuction" name="isAuction" onchange="onCheckIsAuction();" checked>Is -->
															 <input type="checkbox" value="Y" id="isAuction" name="isAuction" checked>Is
																Reverse Auction
															</label>
														</div>
													</div>
												</c:if>
												<c:if test="${documentType.equalsIgnoreCase('Tender')}">
													<div class="col-sm-4 procurementTender">
														<div class="form-group">
															<label class="checkbox-inline"> <input
																type="checkbox" value="Y" id="isAuction"
																name="isAuction" onchange="onCheckIsAuction();">Is
																Reverse Auction
															</label>
														</div>
													</div>
												</c:if>
												<div class="clearfix"></div>
												<c:if test="${documentType.equalsIgnoreCase('Tender')}">
													<div class="col-sm-4 procurementTender">
														<div class="form-group">
															<label class="checkbox-inline"> <input
																type="checkbox" value="Y" id="isManufacturer"
																name="isManufacturer" checked>Is
																Manufacturer
															</label>
														</div>
													</div>
													<div class="col-sm-4 procurementTender">
														<div class="form-group">
															<label class="checkbox-inline"> <input
																type="checkbox" value="Y" id="isTrader"
																name="isTrader" checked>Is
																Trader
															</label>
														</div>
													</div>
												</c:if>

											</div>
										</div>
										<div class="clearfix"></div>
										<div class="col-sm-12 text-center positionABS"
											style="margin-bottom: 10px;">
											<!-- <button class="btn-all btn btn-info k-tabstrip-prev pull-left tab-li-prev">Previous</button> -->
											<button class="btn btn-info createTahdr save">Save</button>
											<button class="btn btn-info cancel" id="cancelTahdr">Cancel</button>
											<button
												class="btn-all btn btn-info k-tabstrip-next pull-right tab-li-next">Next</button>
										</div>
									</sf:form>
								</div>
								<!--fields of field group 1  -->
								<div>
									<div class="detailscont">
									</div>
									<div class="col-md-12">
										<h4>
											<b>Vendor Selection</b>
										</h4>
									</div>
									<sf:form style="display:none;" id="hiddenformId"
										action="invitationAuctionParticipant" method="POST"
										autocomplete="off"
										modelAttribute="InvitationAuctionParticipantDto">
										<input type="hidden" id="aucid" name="auctionId" />
										<table>
											<tbody>

											</tbody>
										</table>
									</sf:form>
									<div class="col-sm-12">
										<table id="bpartnerdetailtab"
											class="display table table-striped table-bordered"
											cellspacing="0" width="100%">
											<thead>
												<tr>
													<th>Select</th>
													<th>Company Name</th>
													<th>Partner Name</th>
													<th>PAN Card</th>
													<th>Email Id</th>
													<th>Mobile No</th>
												</tr>
											</thead>
											<tfoot>
												<tr>

													<th></th>
													<th>Company Name</th>
													<th>Partner Name</th>
													<th>PAN Card</th>
													<th>Email Id</th>
													<th>Mobile No</th>
												</tr>
											</tfoot>
											<tbody>

											</tbody>
										</table>
										<!--fields of field group 1  -->
										<button type="button" class="btn btn-default bluebtn"
											id="sendpartnerinvitation">Send Invitation</button>
									</div>
								</div>

								<!--fields of field group 2  -->
								<div>
									<div class="detailscont">
										
									</div>
									<sf:form class="" action="saveTahdrDetails" id="TAHDRDetail"
										modelAttribute="tahdrDetail">
										<div class="form-group">
											<div class="col-sm-4">
												<input type="hidden" class="tahdrId" name="tahdr.tahdrId">
												<input type="hidden" id=tahdrCode name="tahdr.tahdrCode">
												<input type="hidden" class="tahdrStatus" id="tahdrStatus" name="tahdr.tahdrStatusCode"> 
												<input type="hidden" id="tahdrDetailId" class="tahdrDetailId" name="tahdrDetailId">
												<input type="hidden" class="isCreateVersion" name="isCreateVersion">
												<h4>
													<b>Details</b>
												</h4>
											</div>
											<div class="col-sm-8 text-right">
												<button class="btn btn-default createVersion"
													onclick="createVersion(event)" style="display: none">
													<span class="glyphicon glyphicon-plus-sign"></span>Create
													Version
												</button>
												<button class="btn btn-default editDates"
													onclick="editVersion(event)" style="display: none">
													<span class="glyphicon glyphicon-pencil"></span>Edit
													Version
												</button>
											</div>
											<hr>
										</div>
										<div class="clearfix"></div>
										<div id="tenderDetailForm">
											<div class="form-group">
												<div class="col-sm-4">
													<div class="styled-input">
														<input type="text" id="estimatedCost" name="estimatedCost"
															class="requiredField onlyNumberWithDecimal"/> 
														<label>Estimated
															Cost (INR in Lakhs)<span class="red">*</span>
														</label> <span></span>  <!--  onkeyup="calculateEMDFeeOnType()" --> 
													</div>
												</div></div>
												<!-- <div class="col-sm-4">
													<div class="styled-input">
														<input type="text" id="emdFee" name="emdFee"
															class="requiredField onlyNumber"
															onchange="return emdFeeLessThenCaluclatedValue(event)" />
														<label>EMD Fees [INR]<span class="red">*</span></label> <span></span>
													</div>
												</div> -->
												<%-- <div class="col-sm-4">
													<div class="styled-input">
														<input type="text" id="tahdrFees" name="tahdrFees"
															class="requiredField onlyNumber" readonly="readonly"
															onchange="calculateGST()" /> <label>${documentType}
															Fees [INR]<span class="red">*</span>
														</label> <span></span>
													</div>
												</div> --%>
												<!--  gst added and total field added -->
												<div class="clearfix"></div>
												<div>
												<!-- <div class="col-sm-4">
													<div class="styled-input">
														<input type="text" id="gst" disabled='disabled' /> <label>GST<span
															id="gstRate"></span><span>(@18% on ${documentType} Fee: SAC
																No.9984) in Rs.).)</span><span class="red">*</span></label> <span></span>
													</div>
												</div> -->
												<%-- <div class="col-sm-4">
													<div class="styled-input">
														<input type="text" id="totalFee" required
															disabled='disabled' /> <label>Total ${documentType} Fee
															Amount including GST 18%<span class="red">*</span>
														</label> <span></span>
													</div>
												</div> --%>
												<!--  gst added and total field added -->
												<%-- <c:if test="${documentType.equalsIgnoreCase('Tender')}"> --%>
												<div class="col-sm-4 procurementTender"
													id='minBidDifferenceDiv'">
													<div class="styled-input">
														<input type="text" id="minBidDifference"
															name="minBidDifference" class="requiredField onlyNumber" />
														<label>Bid <c:if
																test="${documentType.equalsIgnoreCase('Reverse Auction')}"> Decrement </c:if>
															<c:if test="${documentType.equalsIgnoreCase('Auction')}"> Increment </c:if>
															By [INR]<span class="red">*</span></label> <span></span>
													</div>
												</div>


												<div class="col-sm-4 procurementTender">
													<div class="styled-input">
														<select id="pricingProcCode" class="priceBasis dropDown"
															name="pricingProcCode">
														</select> <label>Basis of Prices<span class="red">*</span></label>
														<span></span>
													</div>
												</div>
												 <div class="basisdisclearfix clearfix"></div> 
												<c:if test="${documentType.equalsIgnoreCase('Tender')}">
												<div class="col-sm-4 procurementTender">
													<div class="styled-input">
														<input type="text" id="deliveryDuration"
															name="deliveryDuration" class="requiredField onlyNumber" onchange="onChangeDelivery()"/>
														<label>Delivery Requirement[In months]<span
															class="red">*</span></label> <span></span>
													</div>
												</div>
												</c:if>
												<c:if test="${documentType.equalsIgnoreCase('Request For Proposal')}">
												<div class="col-sm-4 procurementTender">
													<div class="styled-input">
														<input type="text" id="deliveryDuration"
															name="deliveryDuration" class="requiredField onlyNumber" onchange="onChangeDelivery()"/>
														<label>Delivery Requirement[In months]<span
															class="red">*</span></label> <span></span>
													</div>
												</div>
												</c:if>
												<div class="col-sm-4 procurementTender">
													<div class="styled-input">
														<input type="text" id="tahdrValidity" name="tahdrValidity"
															class="requiredField onlyNumber" /> <label>
															Validity [In Days]<span class="red">*</span>
														</label> <span></span>
													</div>
												</div>
												<div class="clearfix disclearfix"></div>
												<div class="col-sm-4 ">
													<div class="styled-input">
														<input type="text" id="contactPersonName"
															name="contactPersonName" class="requiredField requiredalphabeticsWithSpace"  />
														<label>Contact Person Name<span class="red">*</span></label>
														<span></span>
													</div>
												</div>
												
												<div class="col-sm-4 ">
													<div class="styled-input">
														<input type="text" id="contactEmailId"
															name="contactEmailId" class="requiredField emailaddress" oninput="this.value = this.value.toLowerCase()"/>
														<label>Contact Person Email-Id<span class="red">*</span></label>
														<span></span>
													</div>
												</div>
												<div class="col-sm-4 ">
													<div class="styled-input">
														<input type="text" id="contactPersonNo"
															name="contactPersonNo" class="requiredField mobile" maxlength="10"/>
														<label>Contact Person Number<span class="red">*</span></label>
														<span></span>
													</div>
												</div>
												<div  class="col-sm-4">
                                                    <div class="styled-input">
                                                        <select id="designation" name="designation.designationId" class="designationList dropDown"></select>
                                                        <label>Designation<span class="red">*</span></label>
                                                        <span></span>
                                                    </div>
                                                </div> 
												
												<div class="col-sm-4">
													<div class="styled-input">
														<input type="text" id="commencementPeriod"
															name="commencementPeriod"
															class="requiredField onlyNumber" onchange="onChangeMonthDate();"/> <label>Commencement
															Period within<span id="commencementPeriodSpan" class="red">*</span>
														</label> <span></span>
													</div>
												</div>
												
												<%-- <c:if test="${documentType.equalsIgnoreCase('Auction')}">
												<div class="col-sm-4">
													<div class="styled-input">
														<input type="text" id="commencementPeriod"
															name="commencementPeriod"
															class="requiredField onlyNumber" onchange="onChangeMonthDate();"/> <label>Commencement
															Period within
														</label> <span></span>
													</div>
												</div>
												</c:if> --%>
												<div id="MonthDate" style="display:none;">
												<!-- <div class="col-sm-3 text-right">
													 <select id="commencementPeriodMonth" class="commencementPeriodMonth dropDown" name="commencementPeriodCode" >
                                       </select> -->
													<!-- <label style="margin-top: 40px; margin-left: 45px;">Month
														From Date Of<span class="red">*</span>
													</label>
													<span></span> 

												</div>-->
												<div class="col-sm-4">
													<div class="styled-input">
														<select id="commencementPeriodMonth"
															class="commencementPeriodMonth"
															name="commencementPeriodCode">
														</select>
														<label>Month
														From Date Of<span class="red">*</span>
													</label>
													<span></span> 
														<!-- <label>Commencement Period Month From<span class="red">*</span></label> -->
														<span></span>
													</div>
												</div>
												</div>
												<div class="mobclearfix"></div>
												<div class="col-sm-6" style="margin-top: 20px; display:none;" id="otherCommsPeriodDivId">
													<div class="styled-input" style="margin-top: 5px;">
														<textarea id="otherCommencementPeriod" name="otherCommencementPeriod"
															class="" maxlength="2000"></textarea>
														<label>Others<span class="red">*</span></label>
														<span></span>
													</div>
												</div>
												</div>
												<div id="textAreaDiv">
												<div class="col-sm-6" style="margin-top: 20px;">
													<div class="styled-input" style="margin-top: 5px;">
														<textarea id="description" name="description"
															class="requiredField " maxlength="2000"></textarea>
														<label>Brief Description<span class="red">*</span></label>
														<span></span>
													</div>
												</div>
												<div class="mobclearfix"></div>
												<div class="col-sm-6 " style="margin-top: 20px;">
													<div class="styled-input" style="margin-top: 5px;">
														<textarea id="preQualReq" name="preQualReq"
															class="requiredField " maxlength="2000"></textarea>
														<label>Pre-Qualifying Requirements<span
															class="red">*</span></label> <span></span>
													</div>
												</div>
												<div class="mobclearfix"></div>
												</div>
												<div class="tenderDetailFormDiv" >
												<div class="col-sm-6" style="margin-top: 20px;">
													<div class="styled-input" style="margin-top: 5px;">
														<textarea id="preBidAddr" name="preBidAddr"
															class="requiredField " maxlength="2000"></textarea>
														<label>Pre-Bid Meeting Address<span class="red">*</span></label>
														<span></span>
													</div>
												</div>
												<div class="mobclearfix"></div>
												<div class="col-sm-6" style="margin-top: 20px;">
													<div class="styled-input" style="margin-top: 5px;">
														<textarea id="bidOpeningAddr" name="bidOpeningAddr"
															class="requiredField " maxlength="2000"></textarea>
														<label>Bid Opening Address<span class="red">*</span></label>
														<span></span>
													</div>
												</div>
												<div class="clearfix disclearfix"></div>
												<div class="col-sm-4 procurementTender">
													<div class="styled-input" title="To change this value approval is needed">
														<input type="text" id="minQuantity" name="minQuantity"
															class="requiredField onlyNumber" onchange="onMinQuantity();"/> <label>Minimum
															% of Offered Quantity<span class="red">*</span>
														</label> <span></span>
													</div>
												</div>
												<div class="col-sm-4">
													<div class="styled-input">
														<input type="text" id="version" name="version" class='readonly'
															readonly='readonly' required /> <label>Version</label> <span></span>
														<input type="hidden" id="oldTahdrDetailId" name="oldTahdrDetailId" />
													</div>
												</div>
												<!-- <div class="col-sm-4">
													<div class="styled-input">
														<input type="text" id="version" name="version" class='readonly'
															readonly='readonly' required /> <label>Version</label> <span></span>
														<input type="hidden" id="oldTahdrDetailId" name="oldTahdrDetailId" />
													</div>
												</div> -->
												<div class="clearfix disclearfix"></div>
												<!-- <div class="col-sm-4 procurementTender">
													<label class="checkbox-inline"> <input
														type="checkbox" value="Y" name="isDeviation"
														id="isDeviation">Call For Deviation
													</label>
												</div>
												<div class="col-sm-4 procurementTender">
													<label class="checkbox-inline"> <input
														type="checkbox" value="Y" name="isAnnexureC1"
														id="isAnnexureC1" checked="checked">Is C1
														Applicable
													</label>
												</div> -->
												<div class="col-sm-4 isC1Applicable procurementTender" style="display:none;">
													<div class="form-group">
														<label class="checkbox-inline"> <input
															type="checkbox" value="Y" id="isSetC1Later"
															name="isSetC1Later">Set C1 Date Later
														</label>
													</div>
												</div>
												<div class="col-sm-4">
													<div class="form-group">
														<label class="checkbox-inline"> <input
															type="checkbox" value="Y" id="isPBDSetLater"
															name="isPBDSetLater">Set Price Bid Opening Date Later
														</label>
													</div>
												</div>
												<c:if test="${documentType.equalsIgnoreCase('Tender')}">
												<div class="col-sm-4 procurementTender auctionLater">
													<div class="form-group">
														<label class="checkbox-inline"> <input
															type="checkbox" value="Y" id="isAuctionSetLater"
															name="isAuctionDateSetLater">Set Auctions Date Later
														</label>
													</div>
												</div>
												</c:if>
												<c:if test="${documentType.equalsIgnoreCase('Auction')}">
												<div class="col-sm-4">
													<div class="form-group">
														<label class="checkbox-inline"> <input
															type="checkbox" value="Y" id="isAuctionSetLater"
															name="isAuctionDateSetLater">Set Auctions Date Later
														</label>
													</div>
												</div>
												</c:if>
												<div class="col-sm-4">
													<div class="form-group">
														<label class="checkbox-inline"> <input
															type="checkbox" value="Y" id="isWinnerSetLater"
															name="isWinnerSelectionDateSetLater">Set Winner Selection Date Later
														</label>
													</div>
												</div>
												<div class="col-sm-4 procurementTender">
													<label class="checkbox-inline"> <input
														type="checkbox" name="isBiennialContractRate" value="Y"
														id="isBiennialContractRate">Is ${documentType} On
														Rate Contract Basis
													</label>
												</div>
												<div class="col-sm-4 procurementTender auctionLater">
													<div class="form-group">
														<label class="checkbox-inline"> <input
															type="checkbox" value="Y" id="isAuctionSetLater"
															name="isAuctionDateSetLater">Set Auctions Date Later
														</label>
													</div>
												</div>
												<div class="col-sm-4">
													<label class="checkbox-inline"> <input
														type="checkbox" class="tahdrDetailActive" checked value="Y" name="isActive" required>
														Active
													</label>
												 </div>
												</div>
												
												<div>
												<div class="clearfix"></div>
													<h4 class="col-sm-12">
													    <b>Tender Scope Configuration</b>
													</h4>
													<div class="clearfix"></div>
													<hr>
													<div class="clearfix"></div>
													<div class="col-sm-4">
														<div class="form-group">
															<label class="checkbox-inline"> <input
																type="checkbox" value="N" id="isTechnicalBid" 
																name="isTechnicalBid" onclick="onTechnicalBidClick()">
																Technical Bid Needed
															</label>
															<div class="form-group">
															<div class="col-sm-4 procurementTender">
															<label class="checkbox-inline techBid"> <input onclick="gtpCheck()"
																type="checkbox" value="N" id="isGTP" class="techBid"
																name="isGTP">
																GTP Needed
															</label>
															</div>
															<div class="col-sm-4">
															<label class="checkbox-inline techBid"> <input onclick="requirdedDocsCheck()"
																type="checkbox" value="N" id="isTechnicalDocs" class="techBid"
																name="isTechnicalDocs">
																Technical Docs Needed
															</label>
															</div>
															</div>
														</div>
													</div>
													<div class="col-sm-4">
														<div class="form-group">
															<label class="checkbox-inline"> <input onclick="requirdedDocsCheck()"
																type="checkbox" value="N" id="isCommercialDocs"
																name="isCommercialDocs">
																Commercial Docs Needed
															</label>
														</div>
													</div>
													<div class="col-sm-4">
														<div class="form-group">
															<label class="checkbox-inline"> <input onclick="requirdedDocsCheck()"
																type="checkbox" value="N" id="isPriceDocs"
																name="isPriceDocs">
																Price Docs Needed
															</label>
														</div>
													</div>
													<div class="col-sm-4">
														<div class="form-group">
															<label class="checkbox-inline"> <input onclick="onTenderDocsClick()"
																type="checkbox" value="N" id="isTenderDocs"
																name="isTenderDocs">
																Tender Docs Needed
															</label>
														</div>
													</div>
													<div class="col-sm-4">
														<div class="form-group">
															<label class="checkbox-inline"> <input
																type="checkbox" value="N" id="isReqDocs"
																name="isReqDocs">
																Required Docs Needed
															</label>
														</div>
													</div>
													<!-- <div class="col-sm-4">
														<div class="form-group">
															<label class="checkbox-inline"> <input onclick="preliminaryScrutinyCheck()"
																type="checkbox" value="N" id="isPreliminaryScrutiny"
																name="isPreliminaryScrutiny">
																Preliminary Scrutiny Needed
															</label>
														</div>
													</div> -->
													<!-- <div class="col-sm-4 ">
													<label class="checkbox-inline"> <input
														type="checkbox" value="Y" name="isDeviation"
														id="isDeviation">Call For Deviation
													</label>
												</div> -->
												
													<!-- <div class="col-sm-4">
														<div class="form-group">
															<label class="checkbox-inline"> <input onclick="final_ScrutinyCheck()"
																type="checkbox" value="N" id="isFinalScrutiny"
																name="isFinalScrutiny" disabled>
																Final Scrutiny Needed
															</label>
														</div>
													</div> -->
													
													<!-- <div class="col-sm-4 procurementTender">
													<label class="checkbox-inline"> <input
														type="checkbox" value="Y" name="isAnnexureC1"
														id="isAnnexureC1" checked="checked">Is C1
														Applicable
													</label>
												</div> -->
													
												</div>
												
											</div>
										
										<div class="col-sm-12 text-center positionABS"
											style="margin-bottom: 10px;">
											<button
												class="btn-all btn btn-info k-tabstrip-prev pull-left tab-li-prev">Previous</button>
											<button class="btn btn-info saveTahdrDetailsBtn save">Save</button>
											<button class="btn btn-info cancel" id="cancelTahdrDetail">Cancel</button>
											<button
												class="btn-all btn btn-info k-tabstrip-next pull-right tab-li-next">Next</button>
										</div>
									</sf:form>
								</div>
								<!--fields of field group 2  -->
								<!--fields of field group 3  -->
								<div>
									<div class="detailscont">
										
									</div>
									<sf:form class="" action="saveTahdrDates" id="TAHDRDates"
										modelAttribute="tahdrDetail">
										<div class="form-group">
											<div class="col-sm-4">
												<input type="hidden" class="tahdrId" name="tahdr.tahdrId">
												<input type="hidden" class="tahdrDetailId"
													name="tahdrDetailId">
												<h4>
													<b>Important Dates</b>
												</h4>
												<hr>
											</div>
											<div class="col-sm-8">
												<c:if test="${dataForTypeCode.equalsIgnoreCase('getTAHDRApprovalByTypeCode') || dataForTypeCode.equalsIgnoreCase('getTAHDRByTypeCode')}">
												<button class="btn btn-default editTahdrDate"
													onclick="editDates(event)" style="display: none">
													<span class="glyphicon glyphicon-pencil"></span>Edit Dates
												</button>
												</c:if>
												
												<!-- <button class="btn btn-default editDateAfterPublish"
													onclick="editDateAfterPublish(event)" style="display: none">
													<span class="glyphicon glyphicon-pencil"></span>Edit Dates
												</button> -->

											</div>
										</div>
										<div id="tenderDatesForm">
										<input type="hidden" id="editedDates" name="editedDates" />
											<div class="form-group">
												<div class="col-sm-4">
													<%-- <div class="form-group frgrp condisn purchaseFromDate">
														<label for="dtp_input1" class="control-label">${documentType}
															Sale Start Date<span class="red">*</span>
														</label>

														<div class="input-group date form_datetime"
															onchange="onChangeDate('DatesDisableId')">
															<input type="text" class="form-control dateValidate requiredDate"
																id="purchaseFromDate" data-label="Sale Start Date"
																name="purchaseFromDate">
															<div class="input-group-addon">
																<span class="glyphicon glyphicon-th"></span>
															</div>
														</div>
														<input type="hidden" id="dtp_input1" value="" /><br />
													</div> --%>
												</div>
												<div class="col-sm-4">
													<%-- <div class="form-group frgrp purchaseToDate pastdate">
														<label for="dtp_input1" class="control-label">${documentType}
															Sale End Date<span class="red">*</span>
														</label>
														<div class="input-group date form_datetime" id="purchaseDate" >
															<input type="text" class="form-control DatesDisableId requiredDate"
																id="purchaseToDate" name="purchaseToDate">
															<div class="input-group-addon">
																<span class="glyphicon glyphicon-th"></span>
															</div>
														</div>
														<input type="hidden" id="dtp_input1" value="" /><br />
													</div> --%>
												</div>
											</div>
											<div class="form-group">
												<div class="col-sm-4">
													<div class="form-group frgrp condisn technicalBidFromDate">
														<label for="dtp_input1" class="control-label">Bid
															Start Date & Time<span class="red">*</span>
														</label>
														<div class="input-group date form_datetime" id="technicalBidFromDates" onchange="onChangeDate('dateBid')">
															<input type="text" class="form-control DatesDisableId requiredDate" id="technicalBidFromDate" name="technicalBidFromDate"
																data-label="Bid Start Date & Time">
															<span class="input-group-addon"><span class="glyphicon glyphicon-th"></span></span>
														</div>
														<input type="hidden" id="dtp_input1" value="" /><br />
													</div>
												</div>
												<div class="col-sm-4">
													<div class="form-group frgrp technicalBidToDate pastdate">
														<label for="dtp_input1" class="control-label">Bid End Date & Time<span class="red">*</span>
														</label>
														<div class="input-group date form_datetime" id="technicalBidDates" onchange="onChangeDate('dateBidOpening')">
															<input type="text" class="form-control dateBid requiredDate" id="technicalBidToDate" name="technicalBidToDate">
															<span class="input-group-addon"><span
																class="glyphicon glyphicon-th"></span></span>
														</div>
														<input type="hidden" id="dtp_input1" value="" /><br />
													</div>
												</div>
												<div class="col-sm-4">
													<!-- <div class="form-group frgrp preBidDate pastdate">
														<label for="dtp_input1" class="control-label">Pre-Bid
															meeting Date & Time<span class="red">*</span>
														</label>
														<div class="input-group date form_datetime" id="preBidDates">
															<input type="text" class="form-control DatesDisableId requiredDate" id="preBidDate" name="preBidDate">
															name="lastC1SubmissionDate"
															<span class="input-group-addon"><span
																class="glyphicon glyphicon-th"></span></span>
														</div>
														<input type="hidden" id="dtp_input1" value="" /><br />
													</div> -->
												</div>
											</div>
											<div class="col-sm-4">
													<!-- <div class="form-group frgrp techBidOpenningDate pastdate">
														<label for="dtp_input1" class="control-label">Technical Bid Opening Date & Time<span class="red">*</span>
														</label>
														<div class="input-group date form_datetime" id="technicalBidOpeningDates" onchange="onChangeDate('preBidOpenDate')">
															<input type="text" class="form-control dateBidOpening requiredDate" id="techBidOpenningDate" name="techBidOpenningDate">
															name="lastC1SubmissionDate"
															<span class="input-group-addon"><span
																class="glyphicon glyphicon-th"></span></span>
														</div>
														<input type="hidden" id="dtp_input1" value="" /><br />
													</div> -->
												</div>
											<div class="col-sm-4 setPBDLater">
												<!-- <div class="form-group frgrp priceBidOpenningDate pastdate">
													<label for="dtp_input1" class="control-label">Price Bid Opening Date & Time<span class="red">*</span>
													</label>
													<div class="input-group date form_datetime" id="priceBidOpeningDates" onchange="onChangeDate('AnnextureOpenDate')">
														<input type="text" class="form-control preBidOpenDate requiredDate" id="priceBidOpenningDate" name="priceBidOpenningDate">
														name="lastC1SubmissionDate"
														<span class="input-group-addon"><span
															class="glyphicon glyphicon-th"></span></span>
													</div>
													<input type="hidden" id="dtp_input1" value="" /><br />
												</div> -->
											</div>
											<%-- <c:if test="${documentType.equalsIgnoreCase('Tender')}">
												<div class="col-sm-4 procurementTender setAuctionLater">
													<div class="form-group frgrp auctionDates auctionStartToDates pastdate">
														<label for="dtp_input1" class="control-label">Auction Start Date & Time<span class="red">*</span>
														</label>
														<div class="input-group date form_datetime" id="auctionStartDates" >
															<input type="text" class="form-control requiredDate" id="auctionStartToDates" name="auctionFromDate">
															<span class="input-group-addon"><span
																class="glyphicon glyphicon-th"></span></span>
														</div>
														<input type="hidden" id="dtp_input1" value="" /><br />
													</div>
												</div> 
												 <div class="col-sm-4 procurementTender setAuctionLater">
													<div class="form-group frgrp auctionDates auctionEndToDates pastdate">
														<label for="dtp_input1" class="control-label">Auction End Date & Time<span class="red">*</span>
														</label>
														<div class="input-group date form_datetime" id="auctionEndDates" >
															<input type="text" class="form-control requiredDate" id="auctionEndToDates" name="auctionToDate">
															<span class="input-group-addon"><span
																class="glyphicon glyphicon-th"></span></span>
														</div>
														<input type="hidden" id="dtp_input1" value="" /><br />
													</div>
												</div> 
												</c:if> --%>
												<%-- <c:if test="${documentType.equalsIgnoreCase('Auction')}">
												<div class="col-sm-4 setAuctionLater">
													<div class="form-group frgrp auctionStartToDates pastdate">
														<label for="dtp_input1" class="control-label">Auction Start Date & Time<span class="red">*</span>
														</label>
														<div class="input-group date form_datetime" id="auctionStartDates" >
															<input type="text" class="form-control requiredDate" id="auctionStartToDates" name="auctionFromDate">
															<span class="input-group-addon"><span
																class="glyphicon glyphicon-th"></span></span>
														</div>
														<input type="hidden" id="dtp_input1" value="" /><br />
													</div>
												</div> 
												 <div class="col-sm-4 setAuctionLater">
													<div class="form-group frgrp auctionEndToDates pastdate">
														<label for="dtp_input1" class="control-label">Auction End Date & Time<span class="red">*</span>
														</label>
														<div class="input-group date form_datetime" id="auctionEndDates" >
															<input type="text" class="form-control requiredDate" id="auctionEndToDates" name="auctionToDate">
															<span class="input-group-addon"><span
																class="glyphicon glyphicon-th"></span></span>
														</div>
														<input type="hidden" id="dtp_input1" value="" /><br />
													</div>
												</div> 
												</c:if> --%>
											<!-- <div class="col-sm-4 procurementTender setC1Later c1ToDate pastdate">
												<div class="form-group ">
													<label for="dtp_input1" class="control-label">Annexure C1 End Date & Time<span class="red">*</span>
													</label>
													<div class="input-group date form_datetime" id="C1AnnexDates">
														<input type="text" class="form-control c1Date requiredDate" id="c1ToDate"
															name="c1ToDate">
														name="lastC1SubmissionDate"
														<span class="input-group-addon"><span
															class="glyphicon glyphicon-th"></span></span>
													</div>
													<input type="hidden" id="dtp_input1" value="" /><br />
												</div>
											</div> -->
											<!-- <div class="col-sm-4 procurementTender setC1Later c1OpenningDate pastdate">
												<div class="form-group">
													<label for="dtp_input1" class="control-label">Annexure C1 Opening Date & Time<span class="red">*</span>
													</label>
													<div class="input-group date form_datetime" id="AnnexC1OpeningDates" onchange="onChangeDate('c1Date')">
														<input type="text" class="form-control AnnextureOpenDate requiredDate" id="c1OpenningDate" name="c1OpenningDate">
														name="lastC1SubmissionDate"
														<span class="input-group-addon"><span
															class="glyphicon glyphicon-th"></span></span>
													</div>
													<input type="hidden" id="dtp_input1" value="" /><br />
												</div>
											</div> -->
											<div class="col-sm-4 winnerLater">
													<div class="form-group frgrp winnerSelectionDate pastdate">
														<label for="dtp_input1" class="control-label">Winner Selection Date & Time<span class="red">*</span>
														</label>
														<div class="input-group date form_datetime" id="winnerDate">
															<input type="text" class="form-control requiredDate" id="winnerSelectionDate" name="winnerSelectionDate">
															<span class="input-group-addon"><span
																class="glyphicon glyphicon-th"></span></span>
														</div>
														<input type="hidden" id="dtp_input1" value="" /><br />
													</div>
											</div>
											<input type="hidden" class="datesSubmitted" id="datesSaved" name="datesSubmitted" value="N">
										</div>
										<div class="col-sm-12 text-center positionABS"
											style="margin-bottom: 10px;">
											<button
												class="btn-all btn btn-info k-tabstrip-prev pull-left tab-li-prev">Previous</button>
											<button class="btn btn-info saveTahdrDatesBtn save">Save</button>
											<button class="btn btn-info cancel" id="cancelTahdrDates">Cancel</button>
											<button
												class="btn-all btn btn-info k-tabstrip-next pull-right tab-li-next">Next</button>
										</div>
									</sf:form>
								</div>
								<!--fields of field group 3  -->
								<!--fields of field group 4  -->
								<div class="tenderDocsClass" style="display: none ;">
									<div class="detailscont">
										
									</div>
									<sf:form id='saveStdCstDocForm' modelAttribute='tahdrDetail'>
										<div>
											<div class="form-group">
												<div class="col-sm-12">
													<input type='hidden' class='tahdrDetailId'
														name='tahdrDetailId'>
													<h4>
														<b>Select Standard Documents</b>
													</h4>
													<hr>
												</div>
											</div>
											<div id="tenderStdCustDocsForm">
												<div class="form-group col-sm-7">
													<div class="form-group stdCustDocDiv">
														<div class="col-sm-6 col-xs-9">
															<label class="checkbox-inline checkbox10 standardCustomDocuments"> <input
																type="hidden" id="stdCstDoc_ST1" data-value="0">
																<input type="checkbox" id="ST1"
																data-name="ANNEXURE I TO SECTION I 33KV"
																class="standardCustomDoc" data-value="0"> <input
																type="hidden" id="std_doc_code_ST1" value='ST1'>
																ANNEXURE I TO SECTION I 33KV
															</label>
														</div>
														<div class="col-sm-6 col-xs-3">
															<input type="hidden" id="attachment_ST1"
																class="attachment"
																name="standardCustomDocList[0].attachment.attachmentId"
																value="3"> <a
																href="<%=request.getContextPath()%>/download/1">Preview</a>
														</div>
													</div>
													<div class="form-group stdCustDocDiv">
														<div class="col-sm-6 col-xs-9">
															<label class="checkbox-inline checkbox10 standardCustomDocuments"> <input
																type="hidden" id="stdCstDoc_ST2" data-value="1">
																<input type="checkbox" class="standardCustomDoc"
																id="ST2" data-name="ANNEXURE C1 33KV" data-value="1">ANNEXURE
																C1 33KV <input type='hidden' value='ST2'
																id="std_doc_code_ST2">
															</label>
														</div>
														<div class="col-sm-6 col-xs-3">
															<input type="hidden" id="attachment_ST2"
																class="attachment" value="2"> <a
																href="<%=request.getContextPath()%>/download/2">Preview</a>
														</div>
													</div>
													<div class="form-group stdCustDocDiv">
														<div class="col-sm-6 col-xs-9">
															<label class="checkbox-inline checkbox10 standardCustomDocuments"> <input
																type="hidden" id="stdCstDoc_ST3" data-value="2">
																<input type="checkbox" class="standardCustomDoc"
																id="ST3" data-name="ANNEXURE Q DIST" data-value="2">ANNEXURE
																Q DIST <input type='hidden' value='ST3'
																id="std_doc_code_ST3">
															</label>
														</div>
														<div class="col-sm-6 col-xs-3">
															<input type="hidden" id="attachment_ST3"
																class="attachment" value="3"> <a
																href="<%=request.getContextPath()%>/download/3">Preview</a>
														</div>
													</div>
													<div class="form-group stdCustDocDiv">
														<div class="col-sm-6 col-xs-9">
															<label class="checkbox-inline checkbox10 standardCustomDocuments"> <input
																type="hidden" id="stdCstDoc_ST4" data-value="3">
																<input type="checkbox" class="standardCustomDoc"
																id="ST4"
																data-name="CONDITIONS OF TENDER AND SUPPLY SECTION II 33KV"
																data-value="3">CONDITIONS OF TENDER AND SUPPLY
																SECTION II 33KV <input type='hidden' value='ST4'
																id="std_doc_code_ST4">
															</label>
														</div>
														<div class="col-sm-6 col-xs-3">
															<input type="hidden" id="attachment_ST4"
																class="attachment" value="4"> <a
																href="<%=request.getContextPath()%>/download/4">Preview</a>
														</div>
													</div>
													<div class="form-group stdCustDocDiv">
														<div class="col-sm-6 col-xs-9">
															<label class="checkbox-inline checkbox10 standardCustomDocuments"> <input
																type="hidden" id="stdCstDoc_ST5" data-value="3">
																<input type="checkbox" class="standardCustomDoc"
																id="ST5"
																data-name="SECTION I INSTRUCTIONS TO THE TENDERER 33KV"
																data-value="4">SECTION I INSTRUCTIONS TO THE
																TENDERER 33KV <input type='hidden' value='ST5'
																id="std_doc_code_ST5">
															</label>
														</div>
														<div class="col-sm-6 col-xs-3">
															<input type="hidden" id="attachment_ST5"
																class="attachment" value="5"> <a
																href="<%=request.getContextPath()%>/download/5">Preview</a>
														</div>
													</div>
													<div class="form-group stdCustDocDiv">
														<div class="col-sm-6 col-xs-9">
															<label class="checkbox-inline checkbox10 standardCustomDocuments"> <input
																type="hidden" id="stdCstDoc_ST6" data-value="5">
																<input type="checkbox" class="standardCustomDoc"
																id="ST6" data-name="SECTION I RE" data-value="5">SECTION
																I RE <input type='hidden' value='ST6'
																id="std_doc_code_ST6">
															</label>
														</div>
														<div class="col-sm-6  col-xs-3">
															<input type="hidden" id="attachment_ST6"
																class="attachment" value="6"> <a
																href="<%=request.getContextPath()%>/download/6">Preview</a>
														</div>
													</div>
													<div class="form-group stdCustDocDiv">
														<div class="col-sm-6 col-xs-9">
															<label class="checkbox-inline checkbox10 standardCustomDocuments"> <input
																type="hidden" id="stdCstDoc_ST7" data-value="6">
																<input type="checkbox" class="standardCustomDoc"
																id="ST7" data-name="SECTION II RE" data-value="6">SECTION
																II RE <input type='hidden' value='ST7'
																id="std_doc_code_ST7">
															</label>
														</div>
														<div class="col-sm-6 col-xs-3">
															<input type="hidden" id="attachment_ST7"
																class="attachment" value="7"> <a
																href="<%=request.getContextPath()%>/download/7">Preview</a>
														</div>
													</div>
													<div class="form-group stdCustDocDiv">
														<div class="col-sm-6 col-xs-9">
															<label class="checkbox-inline checkbox10 standardCustomDocuments"> <input
																type="hidden" id="stdCstDoc_ST8" data-value="7">
																<input type="checkbox" class="standardCustomDoc"
																id="ST8" data-name="ANNEXURE C1 RE" data-value="7">ANNEXURE
																C1 RE <input type='hidden' value='ST8'
																id="std_doc_code_ST8">
															</label>
														</div>
														<div class="col-sm-6 col-xs-3">
															<input type="hidden" id="attachment_ST8"
																class="attachment" value="8"> <a
																href="<%=request.getContextPath()%>/download/8">Preview</a>
														</div>
													</div>
													<div class="form-group stdCustDocDiv">
														<div class="col-sm-6 col-xs-9">
															<label class="checkbox-inline checkbox10 standardCustomDocuments"> <input
																type="hidden" id="stdCstDoc_ST9" data-value="8">
																<input type="checkbox" class="standardCustomDoc"
																id="ST9" data-name="ADDITIONAL INSTRUCTIONS RE"
																data-value="8">ADDITIONAL INSTRUCTIONS RE <input
																type='hidden' value='ST9' id="std_doc_code_ST9">
															</label>
														</div>
														<div class="col-sm-6 col-xs-3">
															<input type="hidden" id="attachment_ST9"
																class="attachment" value="9"> <a
																href="<%=request.getContextPath()%>/download/9">Preview</a>
														</div>
													</div>
												</div>
												<div class="col-sm-5">
													<div class="panel panel-primary" style="margin-top: 10px;">
														<div class="panel-heading">Order Selected Documents
														</div>
														<div class="panel-body"
															style="padding: 7px; height: 380px; overflow-y: auto;">
															<ul class="sortable standardCustomDocuments" id="orderTenderDocs">
															</ul>
														</div>
													</div>
												</div>
												<div class="form-group col-sm-7">
													<div class="form-group">
														<div class="col-sm-12">
															<h4>
																<b>Select ${documentType} Custom Documents</b>
															</h4>
															<hr>
														</div>
													</div>
													<div class='customDocs' id="customDocsDiv"></div>

												</div>
											</div>

											<div class="col-sm-12 text-center positionABS">
												<button
													class="btn btn-info k-tabstrip-prev pull-left tab-li-prev">Previous</button>
												<button class="btn btn-default saveTenderDocs save">Save</button>
												<button class="btn btn-default cancelTenderDocs cancel">Cancel</button>
												<button
													class="btn btn-info k-tabstrip-next pull-right tab-li-next">Next</button>
											</div>
										</div>
									</sf:form>
								</div>

								<!--fields of field group 6  -->
								<!--fields of field group 7  -->
								<div>
									<div class="detailscont"></div>
									<div id="tahdrMaterialList" style="display: none;">
										<div class="form-group">
											<div class="col-sm-12">
												<h4>
													<b>List of items in ${documentType} </b>
												</h4>
												<hr>
											</div>
										</div>
										<div class="col-sm-12">
											<table id="tahdrMaterialTable" class="table table-bordered">
												<thead>
													<tr>
														<th></th>
														<th>Material Code</th>
														<th>Unit</th>
														<th>Description</th>
														<th>Quantity</th>
														<th>SpecVersion</th>
														<th>MaterialType</th>
													</tr>
												</thead>
												<tbody>
													<tr></tr>
												</tbody>
											</table>
										</div>
									</div>
									<div id="tahdrMaterialDiv">

										<sf:form action="saveTahdrMaterial" id="saveTahdrMaterialForm"
											modelAttribute="tahdrMaterial" class="">
											<div class="form-group">
												<div class="col-sm-12">
													<input class="tahdrMaterialId" name="tahdrMaterialId"
														style="display: none;"> <input type="hidden"
														class="tahdrDetailId" name="tahdrDetail.tahdrDetailId">

													<h4 class="col-sm-4">
														<b>Items in ${documentType} </b>
													</h4>
													<div id=tenderMaterialButtonsId>
													<div class="col-sm-8 text-right nomopa">
													<button class="btn btn-default addTahdrMaterial">
															<span class="glyphicon glyphicon-plus-sign"></span>Add
															Item
														</button>
														<button class="btn btn-default editTahdrMaterial"
															style="display: none;">
															<span class="glyphicon glyphicon-pencil"></span>Edit Item
														</button>
														<button class="btn btn-default deleteTahdrMaterial">
															<span class="glyphicon glyphicon-trash"></span>Delete
															Item
														</button>
														<button
															class="btn btn-default addGtpToMaterial procurementTender" id="addGtpToMaterialBtnId"> 
															<span class="glyphicon glyphicon-plus-sign"></span>Add
															GTP
														</button>
													</div>
													</div>
													</div>
													<hr>
												</div>
											<!-- </div> -->
											<div id="tenderMaterialForm">
												<div class="form-group">
													<div class="col-sm-6">
														<div class="styled-input">
															<select id="selectedMaterialId"
																class="readonly requiredField"
																name="material.materialId" readonly="readonly">
																<option>Select Item</option>
															</select> 
															<label>Item<span class="red">*</span></label>
														</div>
															</div>
														<div class="col-sm-1">
															<button class="btn btn-default aditbt addItemPopup" id="">Search
																Item</button>
														</div>
												</div>
												<div class="form-group">
													<div class="col-sm-4">
														<div class="styled-input">
															<input type="text" class="readonly requiredField"
																id="selectedMaterialUom" name="materialUom" /> <label>UOM<span
																class="red">*</span></label> <span></span>
														</div>
													</div>
													<div class="col-sm-6">
														<div class="styled-input">
															<input type="text" class="readonly requiredField"
																id="selectedMaterialDesc" name="materialDescription" />
															<label>Item Description<span class="red">*</span></label>
															<span></span>
														</div>
													</div>
												</div>
												<div class="form-group">
												<div class="col-sm-4">
														<div class="styled-input">
															<input type="text" class="readonly requiredField"
																id="selectedItemCode" name="itemCode" />
															<label>Item Code<span class="red">*</span></label>
															<span></span>
														</div>
													</div>
													<div class="col-sm-4">
														<div class="styled-input">
															<input type="text" class="readonly requiredField"
																id="selectedHSNCode" name="hsnCode" />
															<label>HSN/SAC Code<span class="red">*</span></label>
															<span></span>
														</div>
													</div>
													<div class="mobclearfix"></div>
													<div class="col-sm-4">
														<div class="styled-input">
															<input type="text" id="materialQuantity" name="quantity"
																class="requiredField onlyNumber" /> <label>Quantity<span
																class="red">*</span></label> <span></span>
														</div>
													</div>
													<div class="mobclearfix"></div>
													<div class="col-sm-3">
														<label class="radio-inline" style="margin-top: 25px;">
															<input type="radio" name="aboutSpecCode"
															class="aboutSpecCode" value="inc">Include
															Specification
														</label> <br> <label class="radio-inline"
															style="margin-top: 5px;"> <input type="radio"
															name="aboutSpecCode" class="aboutSpecCode" value="ref">Refer
															Specification
														</label>
													</div>
													<div class="col-sm-3 MaterialForVersion" >
														<div class="styled-input">
															<select id="getMaterialForVersion" class="">

															<!-- </select> <label>Refer To <span class="red">*</span></label> -->
															</select> <label>Refer To </label>
														</div>
													</div>
													<div class="col-sm-3">
														<div class="styled-input">
															<select id="selectedMaterialVersionId" class="requiredField dropDown"
																name="materialVersion.materialVersionId" required>

															<!-- </select><label>Specification Version<span class="red">*</span></label> -->
															</select><label>Specification Version</label>
														</div>
													</div>
													
													 <div class="clearfix"></div> 
												<div class="form-group procurementTender" style="margin-top: 20px;">
														<div class="col-sm-3">
															<label class="radio-inline" style="margin-top: 5px;"><span
																style="margin-left: -18px;">Item Type</span><br> <input
																type="radio" name="materialTypeCode"
																class="input_margin Single"
																onclick="onMaterialTypeChange(this)" value="single"
																checked>Single<br> <input type="radio"
																name="materialTypeCode" class="input_margin BOM"
																onclick="onMaterialTypeChange(this)" value="bom">BOM<br>
															</label>
														</div>
														<div class="col-sm-3 BOMVersion " style="display: none">
															<div class="styled-input">
																<select id="BOMVersionId" name="bomVersion.bomVersionId" onchange="onChangeBOMVersion(this)">
																</select> <label>BOM Version<span class="red">*</span></label>
															</div>
														</div>
														<div class="col-sm-3 viewPartsBtn" style="display: none">
															<div>
																<button class="btn btn-default createTahdrMaterialParts">
																	<span class="glyphicon glyphicon-pencil"></span>Show Parts
																</button>
															</div>
														</div>
													</div>
												</div>
											</div>
											<div class="col-sm-12 text-center positionABS"
												style="margin-bottom: 10px;">
												<button
													class="btn-all btn btn-info k-tabstrip-prev pull-left tab-li-prev">Previous</button>
												<button class="btn btn-info saveTahdrMaterial save">Save</button>
												<button class="btn btn-info updateTahdrMaterial"
													style="display: none;">Update</button>
												<button class="btn btn-info cancelTahdrMaterial cancel">Cancel</button>
												<button
													class="btn-all btn btn-info k-tabstrip-next pull-right tab-li-next">Next</button>
											</div>
										</sf:form>
									</div>
									<div id="selectMaterialDiv" style="display: none;">
										<div class="form-group">
											<div class="col-sm-12">
												<h4>
													<b>Select Item Form</b>
												</h4>
												<hr>
											</div>
										</div>
										<div class="form-group">
											<div class="col-sm-2">
												<label class="radio-inline" style="margin-top: 25px;">
													<input type="radio" name="optradio">Search By
												</label>
											</div>
											<div class="col-sm-2">
												<div class="styled-input">
													<button class="btn btn-default">Go>></button>
												</div>
											</div>
										</div>
										<div class="form-group">
											<div class="col-sm-4">
												<div class="styled-input">
													<select id="srchMaterialGroup" name="srchMaterialGroup"
														class="materialGroupList" required></select> <label>Select
														Item Group<span class="red">*</span>
													</label> <span></span>
												</div>
											</div>
											<div class="col-sm-4">
												<div class="styled-input">
													<select id="srchMaterialSubGroup"
														name="srchMaterialSubGroup" class="materialSubGroupList"
														required></select> <label>Select Sub Group<span
														class="red">*</span></label> <span></span>
												</div>
											</div>
											<div class="col-sm-4">
												<div class="styled-input">
													<select id="srchMaterial" name="srchMaterial"
														class="materialList" required></select> <label>Select
														Item<span class="red">*</span>
													</label> <span></span>
												</div>
											</div>
										</div>
										<div class="form-group">
											<div class="col-sm-12">
												<div class="styled-input">
													<button class="btn btn-default" id="selectMaterialBtn">Add</button>
													<button class="btn btn-default loadList resetSrchMaterial"
														url="getMaterialGroupList">Reset</button>
													<button class="btn btn-default cancelSrchMaterial cancel">Cancel</button>
												</div>
											</div>
										</div>
									</div>
									<div id="tahdrMaterialGtp" style="display: none;">
										<div class="form-group">
											<div class="col-sm-12">
												<h4>
													<b>GTP Parameter</b>
												</h4>
												<hr>
											</div>
										</div>
										<sf:form style="display:none;" id="tahdrMaterialGtpListForm"
											action="savetahdrMaterialGtp" method="POST"
											autocomplete="off" modelAttribute="tahdrMaterialGTPList"
											class="">
											<table id="tahdrMaterialGtpListFormTable">
												<tbody>
												</tbody>
											</table>
											<button class="btn btn-default formSubmit">Save</button>
										</sf:form>
										<div class="form-group tahdrMaterialTableGTP">
											<div class="clearfix"></div>
											<div class="col-sm-12">
												<table id="tahdrMaterialGtpTable"
													class="table table-bordered">
													<thead>
														<tr>
															<th><input type="checkbox" onchange="onChangeSelectAll(this)"/>Select all</th>
															<th>GTP Parameter</th>
															<th>GTP Type</th>
															<!-- <th><input type="checkbox" />Publish all</th> -->
														</tr>
													</thead>
													<tbody>
													</tbody>
												</table>
											</div>
										</div>
										<div class="form-group">
											<div class="col-sm-4">
												<button class="btn btn-default saveTahdrMaterialGtp save">Save</button>
												<button class="btn btn-default" id="backToTahdrMaterial">Back</button>
												<button class="btn btn-default cancel" style="display: none"
													id="cancelTahdrMaterialGtp">Cancel</button>
											</div>
										</div>
									</div>
									<div id="tahdrMaterialParts" style="display: none;">
										<div class="form-group">
											<div class="col-sm-12">
												<h4>
													<b>Items Parts</b>
												</h4>
												<hr>
											</div>
										</div>
										<sf:form style="display:none;" id="tahdrMaterialPartsListForm"
											action="savetahdrMaterialParts" method="POST"
											autocomplete="off" modelAttribute="tahdrMaterialPartsList"
											class="">
											<!-- <div class="col-sm-8 ">
		                                    <button class="btn btn-default addSpecParts"><span class="glyphicon glyphicon-plus-sign"></span>Add</button>
		                                    <button class="btn btn-default editSpecParts" style="display:none;"><span class="glyphicon glyphicon-pencil"></span>Edit</button>
		                                    <button class="btn btn-default deleteSpecParts"><span class="glyphicon glyphicon-trash"></span>Delete</button>
		                               </div> -->
											<table id="tahdrMaterialPartsListFormTable">
												<tbody>
												</tbody>
											</table>
											<button class="btn btn-default PartsSubmit">Save</button>
										</sf:form>
										<div class="form-group">
											<div class="clearfix"></div>
											<div class="col-sm-12">
												<table id="tahdrMaterialPartsTable"
													class="table table-bordered">
													<thead>
														<tr>
															<!-- <th><input type="checkbox" />Select all</th> -->
															<th>Item Specification</th>
															<th>Quantity/Set</th>
															<!-- <th><input type="checkbox" />Publish all</th> -->
														</tr>
													</thead>
													<tbody>
													</tbody>
												</table>
											</div>
										</div>
										<div class="form-group">
											<div class="col-sm-4">
												<!-- <button class="btn btn-default saveTahdrMaterialParts save">Save</button> -->
												<button class="btn btn-default" id="backToTahdrMaterialTab">back</button>
												<button class="btn btn-default cancel" style="display: none"
													id="cancelTahdrMaterialParts">Cancel</button>
											</div>
										</div>
									</div>
								</div>
								<!--fields of field group 7  -->
								<!--fields of field group 8,9,10 merged into 7 -->
								<!--fields of field group 11  -->
								<div class="reqDocsClass">
									<div class="detailscont">
										
									</div>
									<div class="form-group">
										<div class="col-sm-4">
											<h4>
												<b>Required Docs List</b>
											</h4>
											<hr>
										</div>
										<div id="sectionButtonsId">
										<div class="col-sm-8 text-right">
											<button class="btn btn-default addSectionDoc">
												<span class="glyphicon glyphicon-plus-sign"></span>Add
											</button>
											<button class="btn btn-default deleteSectionDoc" ><span class="glyphicon glyphicon-pencil"></span>Delete</button>
											<!-- <button class="btn btn-default editSectionDoc" style="diplay:none"><span class="glyphicon glyphicon-pencil"></span>Edit</button> -->

										</div>
										</div>
									</div>

									<div class="col-sm-12 nomopa">
										<sf:form id="saveSectionDocumentForm"
											action="saveSectionDocument" modelAttribute="reqDocList">
											<div id="tenderRequiredDocsForm">
												<div id="reqDocTable">
													<div class="col-sm-4">
														<input type="hidden" class="tahdrId"
															name="tahdrDetail.tahdrId"> <input type="hidden"
															class="tahdrDetailId" name="tahdrDetail.tahdrDetailId">
														<input class="sectionDocumentId" name="sectionDocumentId"
															style="display: none;">
														<div class="styled-input">
															<select name="code" class="sectionCode requiredField dropDown"></select> <label>Select
																Section<span class="red">*</span>
															</label> <span></span>
														</div>
													</div>
													<div class="col-sm-4 sectionTahdrMaterialData ">
														<div class="styled-input">
															<select name="tahdrMaterial.tahdrMaterialId"
																id="sectionTahdrMaterial"
																class="sectionTahdrMaterial dropdown"></select> <label
																id="sectionTahdrMaterialLabel">Select Item<span
																class="red">*</span></label> <span></span>
														</div>
													</div>
													<div class="col-sm-4">
														<div class="styled-input">
															<input type="text" class="documentName requiredField "
																name="name" maxlength="20" /> <label>Document
																Name <span class="red">*</span>
															</label> <span></span>
														</div>
													</div>
													<div class="clearfix"></div>
													<div class="mobclearfix"></div>
													<div class="col-sm-6">
													<div class="mobclearfix"></div>
														<div class="styled-input">

															<textarea id="description" name="description"
																class="documentDescription requiredField "
																maxlength="200"></textarea>
															<!-- <input type="text" class="documentDescription requiredField " name="description"  /> -->

															<label>Short Description<span class="red">*</span></label>
															<span></span>
														</div>
													</div>
												<div class="mobclearfix"></div>
												</div>
											</div>
											<div class="col-sm-12 text-center positionABS"
												style="margin-bottom: 10px;">
												<button
													class="btn-all btn btn-info k-tabstrip-prev pull-left tab-li-prev">Previous</button>
												<button class="btn btn-info saveSectionDocBtn save">Save</button>
												<button class="btn btn-info cancelSectionDocBtn cancel">Cancel</button>
												<button
													class="btn-all btn btn-info k-tabstrip-next pull-right tab-li-next">Next</button>
											</div>
										</sf:form>
									</div>
								</div>
								<!-- field end 11 required -->
								<!--fields of field group 13  -->
								<div>
									<sf:form id="tahdrApprovalForm" modelAttribute="tahdrId"
										method="POST">
										<div class="detailscont"></div>
										<div class="form-group">
											<input type="hidden" id="tahdrId" class="tahdrId"
												name="tahdrId" /> <input type="hidden" id="tahdrStatusCode"
												name="tahdrStatusCode" value="" /> <input type="hidden"
												class="tahdrDetailId"
												name="tahdrDetailList[0].tahdrDetailId">
											<div class="col-sm-12">
												<h4>
													<b>${documentType} Prepare Confirmation</b>
												</h4>
												<hr>
											</div>
										</div>
										<div id="tenderConfirmationDivId">
											<div id="tahdrConfirmationFormFieldID">
												<div class="form-group">
													<div class="col-sm-6" id="approvalComment">
														<label class="col-sm-4"><b>Approval's Comment:</b></label>
														<div class="col-sm-8">
															<textarea class="form-control" id="approveComment"
																maxlength="2000"></textarea>
														</div>
													</div>
													<div class="col-sm-6" id="publishingComments">
														<label class="col-sm-4"><b>Publishing Comment:</b></label>
														<div class="col-sm-8">
															<textarea class="form-control" id="publishComment"
																maxlength="2000"></textarea>
														</div>
													</div>
												</div>	
													<div class="form-group">
													<div class="col-sm-4" id="publishDate">
														<label>${documentType} Publishing Date<span
															class="red">*</span></label>
														<div class="input-group date Pickdate"
															data-provide="datepicker">
															<input type="text" class="form-control PublishingDate"
																name="tahdrDetailList[0].publishingDate">
															<div class="input-group-addon">
																<span class="glyphicon glyphicon-th"></span>
															</div>
														</div>
													</div>
													<div class="col-sm-4" id="officeNote">
														<label>Attach Office Note</label>
														<div class="input-group">
															<input type="file" id="officeNoteId"
																data-id="officeNoteAttachment"
																data-name="officeNoteAttach"
																data-anchor="a_officeNoteAttachment"
																class="form-control uploadFile"> <input
																type="hidden" id="officeNoteAttachment"
																name="tahdrDetailList[0].officeNote.attachmentId"
																class="form-control" /> <span class="input-group-btn">
																<button class="btn btn-default officeNoteAttachment"
																	onclick="return submitWithParam('deleteAttachment','officeNoteAttachment','officeNoteDeleteResp');"
																	disabled="disabled">
																	<i class="fa fa-times"></i>
																</button>
															</span>
														</div>
														<a class="col-sm-12 filelabel"
															data-url="<%=request.getContextPath()%>/download"
															id="a_officeNoteAttachment"></a>
													</div>
												</div>
												<!-- <div class="form-group">
										</div> -->
											</div>
											<div class="form-group">
												<div class="col-sm-12" id="ShowButton"></div>

											</div>
											<div class="clearfix"></div>
											<!-- <div class="form-group">
											
										</div> -->
										</div>
										</sf:form>
										<!-- <div class="form-group inactiveVersion_Confirmation" id="approvalMatrixDiv">
										<div class="col-sm-12">
											<h3>Approval Matrix</h3>
											<button class="addMatrix btn btn-default inactiveVersion_Confirmation" id="addMatrixBtnId" >Add Approval's</button>
											<table id="approvalMatrixTable" class="table tableResponsive table-bordered" style="width:100% !important;">
												<thead>
													<tr>
														<th style="display:none;"></th>
														<th class="col-sm-2">Level of Approval</th>
														<th class="col-sm-3">User</th>
														<th class="col-sm-3">Remarks</th>
														<th class="col-sm-2">Status</th>
														<th class="col-sm-2 inactiveVersion_Confirmation" id="action">Action</th>
													</tr>
												</thead>
												<tbody id="approvalMatrixTableBody">
												
												</tbody>
											</table>
										</div>
										</div> -->
										<div class="col-sm-12 text-right positionABS" style="margin-bottom: 10px;">
											<button class="btn-all btn btn-info k-tabstrip-prev pull-left tab-li-prev">Previous</button>
											
											<button class="btn-all btn btn-info redbtn inactiveVersion_Confirmation" id="generateButton"  onclick="generateDocument(event);">Generate Document</button>
											<span style="padding-top:15px;" id="generatedDocText" ><b>${documentType} Document: </b></span>
											<span style="padding-top:5px;" id="generatedDocAnchor">
												
											</span>
										</div>
										
									
								</div>
								<!--fields of field group 13  -->
								<!--fields of field group 9  -->
						</div>
							
							
							<!--fields of field group 13  -->
						</div>
						
					</div>
					<!-- Master tab end-->
				</div>
			</div>
			<!-- right-side end-->
		</div>
	</div>
</div>
<script type="text/javascript">
	$(document)
			.ready(
					function() {
						var counter = 0;

						$("#reqDocTable")
								.on(
										"click",
										".addRow",
										function() {
											var rowCount = $('#reqDocTable tr').length;
											$(this)
													.parent()
													.html(
															'<input type="button" style="margin-top:20px;" class="ibtnDel btn btn-md btn-danger "  value="Delete">');
											$(this).parent().parent().parent()
											var newRow = $("<tr>");
											var cols = "";
											cols += '<td class="col-sm-3"><input type="hidden" class="tahdrId" name="secDocList['
													+ rowCount
													+ '].tahdrDetail.tahdrId" value="'
													+ $('.tahdrId').val()
													+ '">';
											cols += '<input type="hidden" class="tahdrDetailId" name="secDocList['
													+ rowCount
													+ '].tahdrDetail.tahdrDetailId" value="'
													+ $('.tahdrDetailId').val()
													+ '">';
											cols += '<input type="hidden" name="secDocList['+idx+'].sectionDocumentId">';
											cols += '<div class="styled-input"><select id="documentSection_'+rowCount+'" name="secDocList['+rowCount+'].code" class="sectionCode enableAddRow" required></select><label>Select Section<span class="red">*</span></label><span></span></div></td>';
											cols += '<td class="col-sm-4"><div class="styled-input"><input type="text" class="documentName enableAddRow" id="documentName_'+rowCount+'" name="secDocList['+rowCount+'].name" required /><label>Document Name <span class="red">*</span></label><span></span></div></td>';
											cols += '<td class="col-sm-4"><div class="styled-input"><input type="text" id="documentDescription_'+rowCount+'" class="documentDescription enableAddRow" name="secDocList['+rowCount+'].description" required /><label>Short Description<span class="red">*</span></label><span></span></div></td>';

											cols += '<td class="col-sm-1"><input type="button" disabled="disabled" class="btn btn-primary addRow" style="margin-top: 20px;" id="addRow_'+rowCount+'" value="Add Row" /></td>';
											newRow.append(cols);
											$("table.order-list")
													.append(newRow);

											counter++;

											loadReferenceListById(
													"documentSection_"
															+ rowCount,
													bidSection);
										});

						$("table.order-list").on("click", ".ibtnDel",
								function(event) {
									$(this).closest("tr").remove();
									counter -= 1
								});

						$(function() {
							$('#orderTenderDocs')
									.sortable(
											{
												start : function(event, ui) {
													var start_pos = ui.item
															.index();
													ui.item.data('start_pos',
															start_pos);
												},
												update : function(event, ui) {
													var index = ui.item.index();
													var start_pos = ui.item
															.data('start_pos');

													//update the html of the moved item to the current index
													$(
															'#orderTenderDocs li:nth-child('
																	+ (index + 1)
																	+ ')')
															.find('.sequence')
															.val(index);

													if (start_pos < index) {
														//update the items before the re-ordered item
														for (var i = index; i > 0; i--) {
															$(
																	'#orderTenderDocs li:nth-child('
																			+ i
																			+ ')')
																	.find(
																			'.sequence')
																	.val(i - 1);
														}
													} else {
														//update the items after the re-ordered item
														for (var i = index + 2; i <= $("#sortable li").length; i++) {
															$(
																	'#orderTenderDocs li:nth-child('
																			+ i
																			+ ')')
																	.find(
																			'.sequence')
																	.val(i - 1);
														}
													}
												},
												axis : 'y'
											});
						});
						
						$('#selectedMaterialId').parent().addClass('noneback');

					});
</script>
<script type="text/javascript">
	var date = new Date();
	date.setDate(date.getDate());

	$("#tabstrip").kendoTabStrip();
    $("#tabstrip2").kendoTabStrip();
    $("#tabstrip3").kendoTabStrip();
    $("#tabstrip4").kendoTabStrip();
    $("#tabstrip5").kendoTabStrip();
    $("#tabstrip6").kendoTabStrip();
    
	$('.form_datetime').datetimepicker({
		weekStart : 1,
		autoclose : true,
		startView : 2,
		forceParse : 0,
		format : 'dd-mm-yyyy hh:ii',
		showMeridian : 0,
		orientation : 'auto',
		pickerPosition : "top-left",
		startDate : date,
		pick12HourFormat : false,
		widgetPositioning : {
			horizontal : 'auto',
			vertical : 'bottom'
		}
	});
	$('.Pickdate').datepicker({
		startDate : date
	});
</script>
