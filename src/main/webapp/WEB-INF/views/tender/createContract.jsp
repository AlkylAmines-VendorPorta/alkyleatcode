<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/${appMode}/tilescommon/css/fioristyle.css?appVer=${appVer}"/>
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/${appMode}/tilescommon/css//borderTab.css?appVer=${appVer}" />
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/resources/${appMode}/tilescommon/css/mobile.css?appVer=${appVer}" />
<style>
.input_margin {
	margin-left: 67px !important;
	margin-top: 5px;
}

input[type="radio"], input[type="checkbox"] {
	margin: 1px 5px 0;
	margin-top: 1px \9;
	line-height: normal;
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
<div class="full-container">
	<!-- left-side start-->
	<div class="clearfix"></div>
	<div id="mobile_first_container" class="left-side col-md-3 no-marg">
		<div class="detailsheader">
			<div class="col-sm-3 text-center brdrgt">
				<label>Contractor List (5)</label>
			</div>
		</div>
		<div class="input-group">
			<div class="input-group-btn search-panel">
				<button type="button" class="btn btn-default dropdown-toggle"
					data-toggle="dropdown">
					<span id="search_concept">Filter by</span> <span class="caret"></span>
				</button>
				<ul class="dropdown-menu" role="menu">
					<li><a href="#contains" onclick="loadPending()">Pending</a></li>
					<li class="divider"></li>
					<li><a href="#all" onclick="loadCreated()">Created</a></li>
				</ul>
			</div>
			<input type="hidden" name="search_param" value="all"
				id="search_param"> <input type="text" class="form-control"
				name="x" placeholder="Search term..."> <span
				class="input-group-btn">
				<button class="btn btn-default" type="button" id = "searchBtn">
					<span class="glyphicon glyphicon-search"></span>
				</button>
				<button class="btn btn-default addnewlist" type="button">
					<span class="glyphicon glyphicon-plus"></span>
				</button>
			</span>
		</div>
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
		<ul id="leftPane" class="nav nav-tabs tabs-left example leftPaneData">
			<!-- <li class="active">
                        <a href="#Master" data-toggle="tab">
                            <div class="col-md-12">
                                <label class="col-xs-6"> NovelERP Solutions </label>
                            	<label class="col-xs-6 ">12765456</label>
                            </div>	
                            <div class="col-md-12">
                           		<label class="col-xs-6">Manufacturer</label>
                            	<label class="col-xs-6 ">EOCPK88Pk</label>
                            </div>
                            
                        </a>
                    </li> -->
		</ul>
		<p id="pagination-here"></p>
		<div class="clearfix"></div>
	</div>
	<!-- left-side end-->

	<!-- right-side start-->
	<div id="mobile_second_container" class="right-side col-md-9 no-marg">
		<div class="detailsheader toptabbrd">
			<div class="col-sm-9 text-center">
				<label>Contract Details</label>
			</div>
		</div>
		<div class="clearfix"></div>
		<div class="tab-content">
			<!-- Master tab start-->

			<div class="tab-pane active in" id="">
				<div class="card">
					<div class="posrelative" id="example">
						<div class="demo-section k-content">
							<div id="tabstrip" class="Firsttab">
								<ul>
									<!-- tabs -->
									
									<li id = "tenderDetails" class="k-state-active">Tender Details</li> 
									<li id = "BidderDetails">Create Contract</li>
								</ul>

								
								<!--fields of field group 2  -->
								<div>
								<div class="detailscont"></div>
									<sf:form class="" id="tahdrForm" action="createTahdr" method="POST" autocomplete="off" modelAttribute="tahdr">
										<div class="form-group">
											<div class="col-sm-4">
												<h4>
													<b>${documentType} Details</b>
												</h4>
											</div>
											<div class="clearfix"></div>
											<hr>
										</div>
										<div id="tenderBaseForm">
											<div class="form-group">
												<div class="col-sm-4">
													<div class="styled-input">
														<input style="display: none" class="tahdrDetail" id="tahdrDetail" >
														<input type="text" id="tahdrCode" name="" value="" readonly="readonly" />
														<label>${documentType} Code<span class="red">*</span></label>
														<span></span>
													</div>
												</div>
												<div class="col-sm-4">
													<div class="styled-input">
														<input type="text" id="totalBidderCount" name="" value="" readonly="readonly" />
														<label>Total no. of Bidders<span class="red">*</span></label>
														<span></span>
													</div>
												</div>
												<div class="col-sm-4">
													<div class="styled-input">
														<input type="text" id="awardWinnerCount" name="" value="" readonly="readonly" />
														<label>Award Winners Count<span class="red">*</span></label>
														<span></span>
													</div>
												</div>
												<div class="col-sm-4">
													<div class="styled-input">
														<input type="text" id="EMD" name="" value="" readonly="readonly" />
														<label>EMD<span class="red">*</span></label>
														<span></span>
													</div>
												</div>
												<div class="col-sm-4 readonly">
													<div class="form-group frgrp">
													<label for="dtp_input1" class="control-label">Sale Start Date<span class="red">*</span></label>
													<div class="input-group date Pickdate" data-provide="datepicker">
													<input type="text" class="form-control dateValidate" id="saleStartDate" data-label="Sale Start Date" name="" readonly="readonly">
														<div class="input-group-addon"><span class="glyphicon glyphicon-th"></span></div>
													</div>
													<input type="hidden" id="dtp_input1" value="" /><br />
													</div>
												</div>
												<div class="col-sm-4 readonly">
													<div class="form-group frgrp">
														<label for="dtp_input1" class="control-label">Bid End Date & Time<span class="red">*</span>
														</label>
														<div class="input-group date form_datetime" id="technicalBidDates">
															<input type="text" class="form-control dateBid" id="technicalBidToDate" name="" readonly="readonly">
															<span class="input-group-addon"><span class="glyphicon glyphicon-th"></span></span>
														</div>
														<input type="hidden" id="dtp_input1" value="" /><br />
													</div>
												</div>
												<div class="col-sm-4 readonly">
													<div class="form-group frgrp">
														<label for="dtp_input1" class="control-label">Technical Bid Opening Date & Time<span class="red">*</span>
														</label>
														<div class="input-group date form_datetime" id="technicalBidOpeningDates" >
															<input type="text" class="form-control dateBidOpening" id="techBidOpenningDate" name="" readonly="readonly">
															<span class="input-group-addon"><span class="glyphicon glyphicon-th"></span></span>
														</div>
														<input type="hidden" id="dtp_input1" value="" /><br />
													</div>
												</div>
												<div class="col-sm-4 readonly">
												<div class="form-group frgrp">
													<label for="dtp_input1" class="control-label">Price Bid Opening Date & Time<span class="red">*</span>
													</label>
													<div class="input-group date form_datetime" id="priceBidOpeningDates" >
														<input type="text" class="form-control preBidOpenDate" id="priceBidOpenningDate" name="" readonly="readonly">
														<span class="input-group-addon"><span class="glyphicon glyphicon-th"></span></span>
													</div>
													<input type="hidden" id="dtp_input1" value="" /><br />
												</div>
												</div>
												<div class="col-sm-4 readonly">
												<div class="form-group">
													<label for="dtp_input1" class="control-label">Annexure C1 Opening Date & Time<span class="red">*</span>
													</label>
													<div class="input-group date form_datetime" id="AnnexC1OpeningDates" >
														<input type="text" class="form-control AnnextureOpenDate" id="c1OpenningDate" name="" readonly="readonly">
														<span class="input-group-addon"><span class="glyphicon glyphicon-th"></span></span>
													</div>
													<input type="hidden" id="dtp_input1" value="" /><br />
												</div>
											</div>
												<div class="clearfix"></div>
											</div>
										</div>
										<div class="clearfix"></div>
									</sf:form>
								</div>
								<!--fields of field group 2  -->
								<!--fields of field group 1  -->
								
								<!--fields of field group 1  -->
								<!--fields of field group 3  -->
								<div>
								
								<div class="detailscont">
									
								</div>
								<div id="tabstrip2">
								<ul>
									<!-- tabs -->
									
									<li onclick = "loadBidder()"class="k-state-active">Contract Header</li> 
									<li onclick = "loadContract('i')" >Contract Item</li>
									<li onclick = "loadContract('s')" class = "contractService">Contract Service</li>
									<li onclick = "loadContract('c')">Contract Condition</li>
								</ul>
								
								<!--fields of sub field group 1  -->
									<div>
									<sf:form id="contractHeaderForm" action = "saveContractHeader" method="POST" autocomplete="off" modelAttribute="contract">
										<input style="display: none" class="contractHeaderId" name="contractHeaderId" value = "0">
										<input style="display: none" class="bidderId" name="bidder.bidderId" id="bidderId">
										<input style="display: none" class="tahdrId" name="tahdr.tahdrId" id="tenderId">
										<div class="form-group">
											<div class="col-sm-4">
												<h4>
													<b>Contract Header</b>
												</h4>
											 </div>
											 <div class="col-sm-8 text-right"><button type="button" class="btn btn-default bluebutton cntrct" onclick="createContract()">Create Contract</button></div>
											 <div class="clearfix"></div>
											<hr>
										</div>
										<div class="form-group">
										<div class="col-sm-4">
											<div class="styled-input partnerTabs">
                                                <input type="text" id="vendocCode" name="vendorSapCode" required class = "inputField"/>
                                                <label>Vendor<span class="red">*</span></label>
                                                <span></span>
                                            </div>
										</div>
										<div class="mobclearfix"></div>
										<div class="col-sm-4">
											<div class="styled-input partnerTabs">
                                                <input type="text" name="agrType" value="ZVAL" readonly="readonly" required id = "contractType"/>
                                                <label>Agreement Type<span class="red">*</span></label>
                                                <span></span>
                                            </div>
										</div>
										<div class="mobclearfix"></div>
										<div class="col-sm-4">
											<div class="styled-input partnerTabs">
                                                <input type="text" id="purOrg" name="purOrg" required />
                                                <label>Purchase Organization<span class="red">*</span></label>
                                                <span></span>
                                            </div>
										</div>
									</div>
									<div class="form-group">
										<div class="col-sm-4">
											<div class="styled-input partnerTabs">
                                                <input type="text" id="purGrp" name="purGrp" readonly="readonly" value="" required />
                                                <label>Purchase Group<span class="red">*</span></label>
                                                <span></span>
                                            </div>
										</div>
										<div class="col-sm-4">
											<div class="styled-input partnerTabs">
                                                <input type="text" id="" name="plant" required />
                                                <label>Plant<span class="red"></span></label>
                                                <span></span>
                                            </div>
										</div>
										<div class="col-sm-4">
											<div class="styled-input partnerTabs">
                                                <input type="text" id="" name="storageLoc" required />
                                                <label>Storage location<span class="red"></span></label>
                                                <span></span>
                                            </div>
										</div>
										<div class="mobclearfix"></div>
										
									</div>
									
									<div class="form-group">
										<div class="col-sm-4">
											<div class="styled-input partnerTabs">
                                                <select name = "accAssCateg" id = "accAssCateg" class = "inputField">
                                                <option value = "F">F</option>
                                                <option value = "K">K</option>
                                                </select>
                                                <label>Account access category<span class="red">*</span></label>
                                                <span></span>
                                            </div>
										</div>
										<div class="col-sm-4">
	                                        <div class="form-group">
	                                            <label>Validity Start Date<span class="red">*</span></label>
	                                            <div class="input-group date" data-provide="datepicker">
	                                                <input type="text" class="form-control inputField" tabindex="-1" id = "startDate" name = "valStartDate">
	                                                <div class="input-group-addon">
	                                                    <span class="glyphicon glyphicon-th"></span>
	                                                </div>
	                                            </div>
                                         	 </div>
                                        </div>
                                        <div class="col-sm-4">
                                                    <div class="form-group">
                                                        <label>Validity End Date<span class="red">*</span></label>
                                                        <div class="input-group date" data-provide="datepicker">
                                                            <input type="text" class="form-control inputField" tabindex="-1" name = "valEndDate" id = "valEndDate">
                                                            <div class="input-group-addon">
                                                                <span class="glyphicon glyphicon-th"></span>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>        
										<div class="mobclearfix"></div>
									</div>
									<div class="form-group">
										<div class="col-sm-4">
											<div class="styled-input partnerTabs">
                                                <input type="text" id = "targValue" name="targValue" required  class = "inputField"/>
                                                <label>Target Value<span class="red">*</span></label>
                                                <span></span>
                                            </div>
										</div>
										<div class="mobclearfix"></div>
										<div class="col-sm-4">
											<div class="styled-input partnerTabs">
                                                <select name = "securityDep" id = "securityDep" class = "inputField">
	                                                <option value = "YES">YES</option>
	                                                <option value = "NO">NO</option>
                                                </select>
                                                <label>Security Dep<span class="red">*</span></label>
                                                <span></span>
                                            </div>
										</div>
										<div class="mobclearfix"></div>
										<div class="col-sm-4">
											<div class="styled-input partnerTabs">
												<select name = "performanceDep" id = "performanceDep" class = "inputField">
	                                                <option value = "YES">YES</option>
	                                                <option value = "NO">NO</option>
                                                </select>                                                
                                                <label>Performance Dep.<span class="red">*</span></label>
                                                <span></span>
                                            </div>
										</div>
									</div>
									<div class="mobclearfix"></div>
									</sf:form>
								</div>
								<!--fields of sub field group 1  -->
								
								<!--fields of sub field group 2  -->
								<div id = "contractItemDiv">
									<sf:form class="" id="contractItemForm" action = "saveContractItem" method="POST" autocomplete="off" modelAttribute="contract">
										<input style="display: none" class="contractHeaderId" name="contractHeader.contractHeaderId">
										<input style="display: none" class="winnerSelectionId" name="winnerSelection.winnerSelectionId">
										<input style="display: none" class="contractItemId" name="contractItemId" >
										
										<div class="form-group">
											<div class="col-sm-4">
												<h4>
												    <b>
													   Contract Item
													</b>
												</h4>
											</div>
											<div class="clearfix"></div>
											<hr>
										</div>
										<div class="form-group">
										<div class="col-sm-3">
											<div class="styled-input partnerTabs">
                                                <input type="text" class = "itemNo" name="item" value=""  required/>
                                                <label>Item No<span class="red">*</span></label>
                                                <span></span>
                                            </div>
										</div>
										<div class="mobclearfix"></div>
										<div class="col-sm-3">
											<div class="styled-input partnerTabs">
                                                <input type="text" class = "matNo" name="materialNo" value=""  required/>
                                                <label>Material No<span class="red">*</span></label>
                                                <span></span>
                                            </div>
										</div>
										<div class="mobclearfix"></div>
										<div class="col-sm-3">
											<div class="styled-input partnerTabs">
                                                <input type="text" class = "shortTxt" id="" name="shortTexr"  required />
                                                <label>Short Text<span class="red">*</span></label>
                                                <span></span>
                                            </div>
										</div>
										<div class="mobclearfix"></div>
										<div class="col-sm-3">
											<div class="styled-input partnerTabs">
                                                <input type="text" class = "trgtQty" name="targetQuantity" value=""  required/>
                                                <label>Target Quantity<span class="red">*</span></label>
                                                <span></span>
                                            </div>
										</div>
										<div class="mobclearfix"></div>
										<div class="col-sm-3">
											<div class="styled-input partnerTabs">
                                                <input type="text" class = "netprice inputField" name="netPrice"  required />
                                                <label>Net Price<span class="red">*</span></label>
                                                <span></span>
                                            </div>
										</div>
										<div class="mobclearfix"></div>
										<div class="col-sm-3">
											<div class="styled-input partnerTabs">
                                                <input type="text" class = "taxCode" id="" name="taxCode"  required />
                                                <label>Tax Code<span class="red">*</span></label>
                                                <span></span>
                                            </div>
										</div>
										<div class="mobclearfix"></div>
										<div class="col-sm-3">
											<div class="styled-input partnerTabs">
                                                <input type="text" class = "valType" id="" name="valuationType"  required />
                                                <label>Valuation Type<span class="red">*</span></label>
                                                <span></span>
                                            </div>
										</div>
										<div class="mobclearfix"></div>
										<div class="col-sm-3">
											<div class="styled-input partnerTabs">
                                                <input type="text" class = "matGrp" id="" name="materialGroup"  required />
                                                <label>Material Group<span class="red">*</span></label>
                                                <span></span>
                                            </div>
										</div>
									</div>
									</sf:form>
									</div>
								<!--fields of sub field group 2  -->
								
								<!--fields of sub field group 3  -->
									<div id = "contractServiceDiv" class = "contractService">
										<sf:form class="" id="contractServiceForm" action = "saveContractService" method="POST" autocomplete="off" modelAttribute="contract">
											<input style="display: none" class="contractHeaderId" name="contractHeader.contractHeaderId">
											<input style="display: none" class="winnerSelectionId" name="winnerSelection.winnerSelectionId">
											<input style="display: none" class="contractServiceId" name="contractServiceId">
										
										<div class="form-group">
											<div class="col-sm-4">
												<h4>
													<b>Contract Service</b>
												</h4>
											</div>
											<div class="clearfix"></div>
											<hr>
										</div>
										<div class="form-group">
										<div class="col-sm-4">
											<div class="styled-input partnerTabs">
                                                <input type="text" class="srvcMatNo" name="materialNo" required/>
                                                <label>Material Item No<span class="red">*</span></label>
                                                <span></span>
                                            </div>
										</div>
										<div class="mobclearfix"></div>
										<div class="col-sm-4">
											<div class="styled-input partnerTabs">
                                                <input type="text" class="srvclineItemNo" name = "srvcLineItemNo" required />
                                                <label>Service Line item No<span class="red">*</span></label>
                                                <span></span>
                                            </div>
										</div>
										<div class="mobclearfix"></div>
										<div class="col-sm-4">
											<div class="styled-input partnerTabs">
                                                <input type="text" class = "srvcNo" name = "serviceNo" required />
                                                <label>Service No.<span class="red">*</span></label>
                                                <span></span>
                                            </div>
										</div>
										<div class="mobclearfix"></div>
										<div class="col-sm-4">
											<div class="styled-input partnerTabs">
                                                <input type="text" class = "srvcQty" required name = "quantity"/>
                                                <label>Quantity<span class="red">*</span></label>
                                                <span></span>
                                            </div>
										</div>
										<div class="mobclearfix"></div>
										<div class="col-sm-4">
											<div class="styled-input partnerTabs">
                                                <input type="text" class = "srvcAmount" required name = "amount"/>
                                                <label>Amount<span class="red">*</span></label>
                                                <span></span>
                                            </div>
										</div>
										<div class="mobclearfix"></div>
										<div class="col-sm-4">
											<div class="styled-input partnerTabs">
                                                <input type="text" class = "srvcCostCenter" required name = "costCenter"/>
                                                <label>Cost Center<span class="red">*</span></label>
                                                <span></span>
                                            </div>
										</div>
									</div>
									</sf:form>
									</div>
								<!--fields of sub field group 3  -->
								
								<!--fields of sub field group 4  -->
									<div id = "contractCondtion">
										<sf:form class="" id="contractCndnFrm" action = "saveContractCndnForm" method="POST" autocomplete="off" modelAttribute="contract">
										<input style="display: none" class="contractHeaderId" name="contractHeader.contractHeaderId">
										<input style="display: none" class="winnerSelectionId" name="winnerSelection.winnerSelectionId">
										<input style="display: none" class="contractConditionId" name="contractConditionId" >
										
										<div class="form-group">
											<div class="col-sm-4">
												<h4>
													<b>Contract Condition</b>
												</h4>
											</div>
											<div class="clearfix"></div>
											<hr>
										</div>
										<div class="form-group">
										<div class="col-sm-4">
											<div class="styled-input partnerTabs">
                                                <input type="text" class = "cndnLineItemNo" name = "srvcLineItemNo" required/>
                                                <label>Service Line item No<span class="red">*</span></label>
                                                <span></span>
                                            </div>
										</div>
										<div class="mobclearfix"></div>
										<div class="col-sm-4">
											<div class="styled-input partnerTabs">
                                                <input type="text" class = "cndnServiceLineItem" name = "srvcLineItem" required />
                                                <label>Service Line Item<span class="red">*</span></label>
                                                <span></span>
                                            </div>
										</div>
										<div class="mobclearfix"></div>
										<div class="col-sm-4">
											<div class="styled-input partnerTabs">
                                                <input type="text" class = "cndnType" name = "conditionType" required />
                                                <label>Condition type<span class="red">*</span></label>
                                                <span></span>
                                            </div>
										</div>
										<div class="mobclearfix"></div>
										<div class="col-sm-4">
											<div class="styled-input partnerTabs">
                                                <input type="text" class = "cndnAmount" name = "amount" required />
                                                <label>Amount<span class="red">*</span></label>
                                                <span></span>
                                            </div>
										</div>
									</div>
									</sf:form>
									</div>
								<!--fields of sub field group 4  -->
								
								</div>
								<!-- <div class="text-center"><button class="btn btn-default createContractBtn save">Create Cotract</button></div> -->
								
								<!--fields of field group 3  -->
								<div class="col-sm-12 text-center positionABS" style="margin-bottom:10px;">
		                                        <button class="btn-all btn btn-info   btnPrevious pull-left tab-li-prev">Previous</button>
		                                        <button class="btn btn-info save disableBtn createContractBtn">Save</button>
		                                        <!-- <button class="btn btn-info cancel disableBtn" id="cancelCompContactBtnId" >Reset</button> -->
		                                        <button class="btn-all btn btn-info   btnNext pull-right tab-li-next">Next</button>
		                       </div>
		                       </div>
							</div>

						</div>
						<!-- Master tab end-->

					</div>
					
					<br>
				</div>
				<!-- right-side end-->
			</div>
		</div>
	</div>
</div>
<script src="<%=request.getContextPath()%>/resources/${appMode}/commons/js/formFields.js?appVer=${appVer}"></script>
 <script src="<%=request.getContextPath()%>/resources/${appMode}/commons/js/springform.js?appVer=${appVer}"></script>
<script src="<%=request.getContextPath()%>/resources/${appMode}/commons/js/utility.js?appVer=${appVer}"></script>
<script src="<%=request.getContextPath()%>/resources/${appMode}/partner/js/createContract.js?appVer=${appVer}"></script>
