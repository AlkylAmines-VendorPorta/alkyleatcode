<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<!-- context path  -->
<% String context=request.getContextPath(); %>
<link rel="stylesheet"	href="<%=request.getContextPath()%>/resources/${appMode}/tilescommon/css/fioristyle.css?appVer=${appVer}" />
<link rel="stylesheet"	href="<%=request.getContextPath()%>/resources/${appMode}/tilescommon/css//borderTab.css?appVer=${appVer}">
<link rel="stylesheet"	href="<%=request.getContextPath()%>/resources/${appMode}/tilescommon/css/mobile.css?appVer=${appVer}">
<script src="<%=context%>/resources/${appMode}/commons/js/utility.js?appVer=${appVer}"></script>
<script src="<%=context%>/resources/${appMode}/commons/js/formFields.js?appVer=${appVer}"></script>
<script src="<%=context%>/resources/${appMode}/tilescommon/js/kendo.all.min.js?appVer=${appVer}"></script> 
<script>
                $(document).ready(function() {
                    $("#tabstrip").kendoTabStrip();                   
                    });               
                </script>

<div class="full-container">
	<!-- left-side start-->
	<div class="clearfix"></div>
	<c:if test="${documentType.equalsIgnoreCase('Tender')}">
			<input type="hidden" class="documentType" value="${documentType}" />
	</c:if>
	<c:if test="${documentType.equalsIgnoreCase('Auction')}">
			<input type="hidden" class="documentType" value="${documentType}" />
	</c:if>
	<div id="mobile_first_container" class="left-side col-md-3 no-marg">
		<div class="detailsheader">
			<div class="col-sm-3 text-center brdrgt">
				<label>${documentType} Committee Report (0)</label>
			</div>
		</div>
		<div class="input-group" id="filterById">
			<div class="input-group-btn search-panel">
				<button type="button" class="btn btn-default dropdown-toggle"
					data-toggle="dropdown">
					<span id="search_concept">Filter by</span> <span class="caret"></span>
				</button>
				  <ul class="dropdown-menu" role="menu">
                      <li><a href="#contains"><input type="radio" name="filterBy" value="tahdrCode" checked/> Tender Code</a></li>
                      <li class="divider"></li>
                      <!-- <li><a href="#all"><input type="radio" name="filterBy" value="title" /> Name</a></li> -->
                    </ul>

			</div>
			<input type="hidden" name="search_param" value="all" id="search_param">         
                <input type="text" class="form-control" name="x" id="searchLiteralId" placeholder="Search term...">
                <span class="input-group-btn">
                    <button class="btn btn-default" type="button" id = "searchBtn"><span class="glyphicon glyphicon-search"></span>
                    </button>
                    </span>
		</div>
	
		<ul id="leftPane" class="nav nav-tabs tabs-left leftPaneData">
			
		</ul>
		<p id="pagination-here"></p>      
		<div class="clearfix"></div>
	</div>
	<!-- left-side end-->

	<!-- right-side start-->
	<div id="mobile_second_container" class="right-side col-md-9 no-marg">
		<div class="detailsheader toptabbrd">
			<div class="col-sm-9 text-center">
				<label>Committee Details</label>
			</div>
		</div>
		<div class="clearfix"></div>
		<div class="tab-content">
			<!-- Master tab start-->

			<div class="tab-pane active in" id="">
				<div class="card">
					<div class="posrelative" id="">
						<div class="demo-section k-content">
							<div id="tabstrip" class="Firsttab">
								<ul>
									<!-- tabs -->
									 <c:if test="${documentType.equalsIgnoreCase('Tender')}">
										<li class="k-state-active readonly" id="tenderCommitteTabId">Tender
										Committee</li>
										
										<li id="participantTabId" class="readonly"
										onclick="return submitWithTwoParam('getTenderCommmitteeParticipant','tenderCommitteeId','tenderId','tenderCommitteeParticipantTabResp')">Tender
										Committee Participant</li>
									</c:if>
									 <c:if test="${documentType.equalsIgnoreCase('Auction')}">
										<li class="k-state-active readonly" id="auctionCommitteTabId">Auction
										Committee</li>
										
										<li id="participantTabId"  class="readonly"
										onclick="return submitWithTwoParam('getTenderCommmitteeParticipant','tenderCommitteeId','tenderId','tenderCommitteeParticipantTabResp')">Auction
										Committee Participant</li>
									</c:if>
									
									
								</ul>

								<!--fields of field group 1  -->
								<div>
									<div class="detailscont"></div>
									<div class="form-group">
											<div class="col-sm-3">
												<div class="styled-input">
													<select id="openingTypeId">
													<option value="">select Opening Type</option>
														<c:forEach var="bidOpeningType" items="${bidOpeningType}">
															<option value="${bidOpeningType.key}">${bidOpeningType.value}</option>
														</c:forEach>
													</select> <label>Select Bid Opening Type<span class="red">*</span></label>
													<span></span>
												</div>
												<div class="clearfix"></div>
											</div>
											<div class="col-sm-3">
											<c:if test="${documentType.equalsIgnoreCase('Tender')}">
										      <button class="btn btn-default " id="searchTenderBtnId">Search
													Committee</button>
											</c:if>
											<c:if test="${documentType.equalsIgnoreCase('Auction')}">
										      <button class="btn btn-default " id="searchAuctionBtnId">Search
													Committee</button>
											</c:if>
												
												<button class="btn btn-default" id="resetBtnId">Reset</button>
											</div>
											</div>
									<sf:form id="tenderCommitteForm" modelAttribute="tenderCommittee" method="POST">
									
											<div  id="tenderCommitteeDivId">
											<div class="col-sm-12">
												<h4>
													<b>${documentType} Committee </b>
												</h4>

												<hr>
											</div>
											<div class="col-sm-12 text-right">
												<button class="btn btn-default" id="addCommitteeBtnId">
													<span class="glyphicon glyphicon-plus-sign"></span>Add
													Committee
												</button>
												<button class="btn btn-default" id="delCommitteeBtnId"
													onclick="return submitWithParam('deleteTenderCommittee','tenderCommitteeId','tenderCommitteeDelResp')">
													<span class="glyphicon glyphicon-trash"></span>Delete
													Committee
												</button>
											</div>
										
										<div class="form-group">
											<input type="hidden" name="isActive" value="Y" /> <input
												type="hidden" id="tenderCommitteeId"
												class="tenderCommitteeId" name="tenderCommitteeId" />
												<div class="mobclearfix"></div>
											<div class="col-sm-3 tenderCommitteeField">
												<div class="styled-input">
													<select id="tenderId" name="tahdr.tahdrId" class="dropDown"
														onchange="getTenderVersion(this);"></select> <label>${documentType}<span
														class="red">*</span></label> <span></span>
												</div>
												<div class="clearfix"></div>
											</div>
											<div class="mobclearfix"></div>
											<div class="col-sm-3">
												<div class="styled-input tenderCommitteeField">
													
													<input type="hidden" id="hiddenTenderVersionId"
														name="tenderVersion.tahdrDetailId" /> <input type="text"
														id="tenderVersionId" disabled="disabled" /> <label>${documentType}
													 Version<span class="red">*</span>
													</label>
												</div>
												<div class="clearfix"></div>
											</div>
											<div class="mobclearfix"></div>
											<div class="col-sm-3">
												<div class="styled-input tenderCommitteeField">
													<select id="chairPersonId" name="chairPerson.userId"
														class="dropDown"></select> <label>Chair Person<span
														class="red">*</span></label> <span></span>
												</div>
												<div class="clearfix"></div>
											</div>
											<div class="col-sm-3 readonly" style="display: none">
												<div class="styled-input">
													<input type="text" id="bidOpeningTypeId" class="requiredField"
														name="bidOpeningType" /> <label>Bid Opening Type:</label>
												</div>
												<div class="clearfix"></div>
											</div>

										</div>
										<div class="form-group">
										<div class="col-sm-5 readonly">
												<div class="styled-input">
													<input type="text" id="viewBidOpeningTypeId"> <label>Bid Opening Type:</label>
												</div>
												<div class="clearfix"></div>
											</div>
											</div>
										<div class="clearfix"></div>
										<div class="form-group tenderCommitteeField"
											id="sessionKeyDivId">
											<div class="clearfix"></div>
											<div class="col-sm-3">
												<label>Generate Session Key :</label>
											</div>
											<div class="col-sm-3" style="padding-top: 1px;">
												<button class="btn btn-info" id="keyGenerationBtnId"
													style="margin-bottom: 10px;">Generate Key</button>
											</div>
											<div class="clearfix"></div>
										</div>
										<div class="form-group">
										 <div class="col-sm-4">
                                                 <label>Is Session key Generated yet:</label>
                                                        <span class="detspan" id="isSessionKeyGenerated">NA</span>
                                                </div>
                                        </div>

										<div class="col-sm-12 text-center positionABS buttonDivClsId"
											style="margin-bottom: 10px;">
											<button class="btn btn-info save tenderCommitteeBtn"
												onclick="return submitIt('tenderCommitteForm','saveTenderCommittee','tenderCommitteeResp');">Save</button>
											<button class="btn btn-info cancel tenderCommitteeBtn"
												id="cancelTenderCommitteeId">Cancel</button>
											<button
												class="btn-all btn btn-info k-tabstrip-next pull-right tab-li-next">Next</button>
										</div>
									</div>
									</sf:form>
								</div>
								<!--fields of field group 1  -->
								<!--fields of field group 2  -->
								<div>
									<sf:form id="committeeParticipantForm"
										modelAttribute="committeParticipant" method="POST">

										<div class="detailscont">
											
										</div>
										<div class="form-group">
											<div class="col-sm-12">
												<h4>
													<b>${documentType} Committee Participant</b>
												</h4>
												<hr>
											</div>
										</div>
										<div class="col-sm-12 text-right">
											<button class="btn btn-default tenderCommitteeField"
												id="addParticipantBtnId">
												<span class="glyphicon glyphicon-plus-sign"></span>Add
												Participant
											</button>
											<button class="btn btn-default tenderCommitteeField"
												id="delParticipantBtnId">
												<span class="glyphicon glyphicon-trash"></span>Delete
												Participant
											</button>
										</div>
										<div class="form-group tenderCommitteeField">
											<div class="col-sm-3">
												<input type="hidden" name="isActive" value="Y" /> <input
													type="hidden" id="committeeParticipantId"
													name="committeeParticipantId" /> <input type="hidden"
													id="tenderCommitteeId" class="tenderCommitteeId"
													name="tenderCommittee.tenderCommitteeId" />
												<div class="styled-input">
													<select id="participantId" name="user.userId"
														class="dropDown"></select> <label>Participant<span
														class="red">*</span></label> <span></span>
												</div>
												<div class="clearfix"></div>
											</div>
											<div class="col-sm-3">
												<div class="styled-input readonly">
													<select id="designationId" name="designation.designationId"
														class="dropDown"></select> <label>Designation<span
														class="red">*</span></label> <span></span>
												</div>
												<div class="clearfix"></div>
											</div>
										</div>
										<div class="col-sm-12 text-center positionABS"
											style="margin-bottom: 10px;">
											<button
												class="btn-all btn btn-info k-tabstrip-prev pull-left tab-li-prev">Previous</button>
											<button class="btn btn-info save tenderCommitteeBtn"
												onclick="return submitIt('committeeParticipantForm','saveCommitteeParticipant','committeeParticipantResp')">Save</button>
											<!-- <button class="btn btn-info cancel" id="cancelParticipantBtnId" >Cancel</button>
	                                 		<button class="btn-all btn btn-info k-tabstrip-next pull-right tab-li-next">Next</button> -->
										</div>
									</sf:form>
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
</div>
<script
	src="<%=request.getContextPath()%>/resources/${appMode}/transaction/js/tenderCommittee.js?appVer=${appVer}"></script>
<script
	src="<%=request.getContextPath()%>/resources/${appMode}/transaction/js/tenderCommitteeParticipant.js?appVer=${appVer}"></script>
