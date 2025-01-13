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
	href="<%=request.getContextPath()%>/resources/tilescommon/css/fioristyle.css" />
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/resources/tilescommon/css/borderTab.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/resources/tilescommon/css/jquery-ui.css">
	<link rel="stylesheet"
	href="<%=request.getContextPath()%>/resources/tilescommon/css/mobile.css">

<script src="<%=request.getContextPath()%>/resources/tilescommon/js/jquery-ui.js"></script>
<script src="<%=request.getContextPath()%>/resources/tilescommon/js/moment.js"></script>
<script
	src="<%=request.getContextPath()%>/resources/tilescommon/js/bootstrap-datetimepicker.js"></script>
<script
	src="<%=request.getContextPath()%>/resources/tilescommon/js/bootstrap-datepicker.js"></script>
	
<%-- <script src="<%=request.getContextPath()%>/resources/commons/js/springform.js"></script> --%>
<script src="<%=request.getContextPath()%>/resources/commons/js/utility.js"></script>
<script src="<%=request.getContextPath()%>/resources/commons/js/loadList.js"></script>
<script src="<%=request.getContextPath()%>/resources/commons/js/loadItemList.js"></script>
<script src="<%=request.getContextPath()%>/resources/commons/js/formFields.js"></script>
<script src="<%=request.getContextPath()%>/resources/commons/js/liCache.js"></script>
<script src="<%=request.getContextPath()%>/resources/partner/js/uploadFile.js"></script>

<script src="<%=request.getContextPath()%>/resources/QuickRFQ/quickRfqPreparation.js"></script>
<script src="<%=request.getContextPath()%>/resources/transaction/js/tahdr.js"></script>
<script src="<%=request.getContextPath()%>/resources/transaction/js/tahdrDetail.js"></script>
<script src="<%=request.getContextPath()%>/resources/transaction/js/tahdrDate.js"></script>
<script src="<%=request.getContextPath()%>/resources/transaction/js/tahdrMaterial.js"></script>
<script src="<%=request.getContextPath()%>/resources/transaction/js/tahdrMaterialGtp.js"></script>
<script
	src="<%=request.getContextPath()%>/resources/transaction/js/tahdrMaterialParts.js"></script>
<script src="<%=request.getContextPath()%>/resources/transaction/js/sectionDocuments.js"></script>
<script src="<%=request.getContextPath()%>/resources/transaction/js/standardCustomDoc.js"></script>
<script src="<%=request.getContextPath()%>/resources/${appMode}/tilescommon/js/kendo.all.min.js?appVer=${appVer}"></script>
<script src="<%=request.getContextPath()%>/resources/commons/js/commonValidation.js"></script>
<script src="<%=request.getContextPath()%>/resources/transaction/js/tahdrConfirmation.js"></script>

<script>
	$(function() {
		$("#orderTenderDocs").sortable();
		$("#orderTenderDocs").disableSelection();
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

#bpartnerdetailtab tfoot {
	display: table-header-group;
}

th>input {
	width: 100%;
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
	<input type="hidden" id="dcumentType" class="documentType" value="${documentType}" />
	
		<input type="hidden" class="dataUrl" value="${dataUrl}" />
		<input type="hidden" class="dataUrlTypeCode" value="${dataForTypeCode}" />
	
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
                    <button class="btn btn-default addnewlist" onclick="addNewQuickAuction(event)" type="button"><span class="glyphicon glyphicon-plus"></span></button>
                    <button class="btn btn-default" onClick="loadNewList(event)"type="button"><span class="glyphicon glyphicon-refresh"></span></button>
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
		<c:if test="${documentType.equalsIgnoreCase('Quick Reverse Auction')}">
			<input type="radio" name="tenderTypeCodeToggle" checked
				style="display: none;" value="QRA">
		</c:if>
		<c:if test="${documentType.equalsIgnoreCase('Quick Forword Auction')}">
			<input type="radio" name="tenderTypeCodeToggle" checked
				style="display: none;" value="QFA">
		</c:if>
		<c:if test="${documentType.equalsIgnoreCase('Quick Request For Proposal')}">
			<input type="radio" name="tenderTypeCodeToggle" checked
				style="display: none;" value="QRFQ">
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
									<li id="tenderBaseInfoTab" onclick="getQuickAuctionBaseInfo();" data-parentTab="" class="k-state-active normalli"><div class="tabcircle"><i class="fa fa-info-circle"></i></div>
										Base Information</li>
									
									<li class="normalli" data-parentTab="tahdrDetailTab" id="getTahdrMaterialList"
										attr-typeCode="procurementTender"><div class="tabcircle"><i class="fa fa-list"></i></div>List of items
										</li>
									
									<li class="confirmliclick" data-parentTab="tahdrDetailTab"  id="confirmation" ><div class="tabcircle" id="tabcircle"><i class="fa fa-check-circle"></i></div>
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
													<b> Base Information Details</b>
												</h4>
											</div>
											<div class="col-sm-4">
												<div class="form-group">
													
														<input type="hidden" name="isPrivateAuction" id="CreatePrivateAuction" value="Y">
														
													<!-- </label> -->
												</div>
											</div>
											<div class="col-sm-4 text-right">
												
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
														<input type="text" id="tahdrCode" name="tahdrCode" value=""
															class="requiredField alphaNumericWithSpaceAndSpecialCharacter" />
														<input type="hidden" id=tahdrCode name="tahdr.tahdrCode">
														<input type="hidden" id=tahdrStatus name="tahdr.tahdrStatusCode"> 
														<input type="hidden" class="tahdrDetailId" name="tahdrDetailList[0].tahdrDetailId">
														<input type="hidden" value="Y" name="isActive">
														<input type="hidden" value="Y" name="tahdrDetailList[0].isActive">
														
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
															</select> <label>${documentType} Type<span class="red">*</span></label>
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
												<c:if test="${documentType.equalsIgnoreCase('Quick Reverse Auction')}">
													<input type="hidden" value="QRA" name="tahdrTypeCode">
												</c:if>
												<c:if test="${documentType.equalsIgnoreCase('Quick Forword Auction')}">
													<input type="hidden" value="QFA" name="tahdrTypeCode">
												</c:if>
												<c:if test="${documentType.equalsIgnoreCase('Quick Request For Proposal')}">
													<input type="hidden" value="QRFQ" name="tahdrTypeCode">
												</c:if>
												<div class="mobclearfix"></div>
												
												<div class="col-sm-4">
													<div class="styled-input">
														
														<input type="hidden" value="SB" name="bidTypeCode">
													</div>
													
												</div>
												<div class="clearfix"></div>
												
												
												<c:if
													test="${documentType.equalsIgnoreCase('Reverse Auction') 
													|| documentType.equalsIgnoreCase('Quick Reverse Auction')
													|| documentType.equalsIgnoreCase('Quick Forword Auction') }">
													<div class="col-sm-4">
														<div class="form-group">
															<label class="checkbox-inline readonly">
															<!--  <input type="checkbox" value="Y" id="isAuction" name="isAuction" onchange="onCheckIsAuction();" checked>Is -->
															 <input type="hidden" value="Y" id="isAuction" name="isAuction" checked>
																<!-- Is Reverse Auction -->
															</label>
														</div>
													</div>
												</c:if>

												
												
												<div class="clearfix disclearfix"></div>
												<div class="col-sm-4 ">
													<div class="styled-input">
														<input type="text" id="contactPersonName"
															name="tahdrDetailList[0].contactPersonName" class="requiredField" /> <label>Contact
															Person Name<span class="red">*</span>
														</label> <span></span>
													</div>
												</div>

												<div class="col-sm-4 ">
													<div class="styled-input">
														<input type="text" id="contactEmailId"
															name="tahdrDetailList[0].contactEmailId" class="requiredField emailaddress"
															oninput="this.value = this.value.toLowerCase()" /> <label>Contact
															Person Email-Id<span class="red">*</span>
														</label> <span></span>
													</div>
												</div>

												<div class="col-sm-4 ">
													<div class="styled-input">
														<input type="text" id="contactPersonNo"
															name="tahdrDetailList[0].contactPersonNo" class="requiredField mobile" /> <label>Contact
															Person Number<span class="red">*</span>
														</label> <span></span>
													</div>
												</div>
												<div class="col-sm-4 procurementTender">
													<div class="styled-input">
														<input type="text" id="deliveryDuration"
															name="tahdrDetailList[0].deliveryDuration" class="requiredField onlyNumber" onchange="onChangeDelivery()"/>
														<label>Delivery Requirement[In months]<span
															class="red">*</span></label> <span></span>
													</div>
												</div>
												<div class="col-sm-4">
													<div class="styled-input">
														<input type="text" id="commencementPeriod"
															name="tahdrDetailList[0].commencementPeriod"
															class="requiredField onlyNumber" onchange="onChangeMonthDate();"/> <label>Commencement
															Period within<span id="commencementPeriodSpan" class="red">*</span>
														</label> <span></span>
													</div>
												</div>
												<div class="col-sm-4">
													<div class="styled-input">
														<select id="commencementPeriodMonth"
															class="commencementPeriodMonth"
															name="tahdrDetailList[0].commencementPeriodCode">
														</select>
														<label>Month
														From Date Of<span class="red">*</span>
													</label>
													<span></span> 
														<span></span>
													</div>
												</div>
												<div class="clearfix disclearfix"></div>
												<div class="col-sm-4 procurementTender">
													<div class="styled-input" title="To change this value approval is needed">
														<input type="text" id="minQuantity" name="tahdrDetailList[0].minQuantity"
															class="requiredField onlyNumber" onchange="onMinQuantity();"/> <label>Minimum
															% of Offered Quantity<span class="red">*</span>
														</label> <span></span>
													</div>
												</div>
												<div class="col-sm-6" style="margin-top: 20px;">
													<div class="styled-input" style="margin-top: 5px;">
														<textarea id="description" name="tahdrDetailList[0].description"
															class=" " maxlength="2000"></textarea>
														<label>Brief Description<span class="red">*</span></label>
														<span></span>
													</div>
												</div>
												
												<div class="form-group">
													<div class="col-sm-4 procurementTender setAuctionLater">
													<div class="form-group frgrp auctionDates">
														<label for="dtp_input1" class="control-label">Quotation Start Date & Time<span class="red">*</span>
														</label>
														<div class="input-group date form_datetime" id="auctionStartDates" >
															<input type="text" class="form-control requiredDate" id="auctionStartToDates" name="tahdrDetailList[0].technicalBidFromDate">
															<span class="input-group-addon"><span
																class="glyphicon glyphicon-th"></span></span>
														</div>
														<input type="hidden" id="dtp_input1" value="" /><br />
													</div>
												</div> 
												 <div class="col-sm-4 procurementTender setAuctionLater">
													<div class="form-group frgrp auctionDates">
														<label for="dtp_input1" class="control-label">Quotation End Date & Time<span class="red">*</span>
														</label>
														<div class="input-group date form_datetime" id="auctionEndDates" >
															<input type="text" class="form-control requiredDate" id="auctionEndToDates" name="tahdrDetailList[0].technicalBidToDate">
															<span class="input-group-addon"><span
																class="glyphicon glyphicon-th"></span></span>
														</div>
														<input type="hidden" id="dtp_input1" value="" /><br />
													</div>
												</div> 
												</div>
											</div>
										</div>
										<div class="clearfix"></div>
										<div class="col-sm-12 text-center positionABS"
											style="margin-bottom: 10px;">
											<!-- <button class="btn-all btn btn-info k-tabstrip-prev pull-left tab-li-prev">Previous</button> -->
											<button class="btn btn-info save" onclick=" createQuickRfq(event)">Save</button>
											<button class="btn btn-info cancel" id="cancelTahdr">Cancel</button>
											<button
												class="btn-all btn btn-info k-tabstrip-next pull-right tab-li-next">Next</button>
										</div>
									</sf:form>
								</div>
								<!--fields of field group 1  -->
								

								<!--fields of field group 2  -->
								
								<!--fields of field group 2  -->
								<!--fields of field group 3  -->
								
								<!--fields of field group 3  -->
								<!--fields of field group 4  -->
								

								<!--fields of field group 6  -->
								<!--fields of field group 7  -->
								<div>
									<div class="detailscont"></div>
									<div id="tahdrMaterialList" style="display: none;">
										<div class="form-group">
											<div class="col-sm-12">
												<h4>
													<b>List of items </b>
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
														<b>Items  </b>
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
														<!-- <button
															class="btn btn-default addGtpToMaterial procurementTender">
															<span class="glyphicon glyphicon-plus-sign"></span>Add
															GTP
														</button> -->
													</div>
													</div>
												</div>
												<hr>
											</div>
											<!-- </div> -->
											<div id="tenderMaterialForm">
												<div class="form-group">
													<div class="col-sm-3">
														<div class="styled-input">
															<select id="selectedMaterialId"
																class="readonly requiredField"
																name="material.materialId">
																<option>Select Item</option>
															</select> <label>Item<span class="red">*</span></label>
														</div>
															</div>
														<div class="col-sm-1">
															<button class="btn btn-default aditbt addItemPopup" id="">Search
																Item</button>
														</div>
												
													<div class="col-sm-4">
														<div class="styled-input">
															<input type="text" class="readonly requiredField"
																id="selectedMaterialUom" name="materialUom" /> <label>UOM<span
																class="red">*</span></label> <span></span>
														</div>
													</div>
													<div class="col-sm-4">
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
															<input type="text" id="materialQuantity" name="quantity"
																class="requiredField onlyNumber" /> <label>Quantity<span
																class="red">*</span></label> <span></span>
														</div>
													</div>
													<div class="mobclearfix"></div>
													
													
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
										<div class="form-group">
											<div class="clearfix"></div>
											<div class="col-sm-12">
												<table id="tahdrMaterialGtpTable"
													class="table table-bordered">
													<thead>
														<tr>
															<th><input type="checkbox"
																onchange="onChangeSelectAll(this)" />Select all</th>
															<th>GTP Parameter</th>
															<th>GTP Type</th>
															
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
												<button class="btn btn-default" id="backToTahdrMaterial">back</button>
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
															
															<th>Item Specification</th>
															<th>Quantity/Set</th>
															
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
													<b> Confirmation</b>
												</h4>
												<hr>
											</div>
										</div>
										<div id="tenderConfirmationDivId">
											
											<div class="form-group">
												<!-- <div class="col-sm-12" id="ShowButton"></div> -->
												<button class="btn btn-default" onclick="submitQuickAuction(event)"> Publish Quick Auction</button>
											</div>
											<div class="clearfix"></div>
											<!-- <div class="form-group">
											
										</div> -->
										</div>
									</sf:form>
									
									<div class="col-sm-12 text-center positionABS"
										style="margin-bottom: 10px;">
										<button
											class="btn-all btn btn-info k-tabstrip-prev pull-left tab-li-prev">Previous</button>

										

										<span style="padding-top: 10px;" id="generatedDocAnchor">

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

					});
</script>
<script type="text/javascript">
	$(document)
			.ready(
					function() {
						// Setup - add a text input to each footer cell
						
					});

	var date = new Date();
	date.setDate(date.getDate());

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
