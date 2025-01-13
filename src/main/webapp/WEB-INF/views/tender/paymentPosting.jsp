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
<script src="<%=request.getContextPath()%>/resources/${appMode}/commons/js/utility.js?appVer=${appVer}"></script>
<script src="<%=request.getContextPath()%>/resources/${appMode}/commons/js/formFields.js?appVer=${appVer}"></script>
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
				<label>Payment Posting List (5)</label>
			</div>
		</div>
		<div class="input-group">
			<div class="input-group-btn search-panel">
				<button type="button" class="btn btn-default dropdown-toggle"
					data-toggle="dropdown">
					<span id="search_concept">Filter by</span> <span class="caret"></span>
				</button>
				<ul class="dropdown-menu" role="menu">
					<li><a href="#contains">Code</a></li>
					<li class="divider"></li>
					<li><a href="#all">Name</a></li>
				</ul>
			</div>
			<input type="hidden" name="search_param" value="all" id="search_param">
				 <input type="text" class="form-control" id="searchLiteralId"
				name="x" placeholder="Search term..."> <span
				class="input-group-btn">
				<button class="btn btn-default" type="button">
					<span class="glyphicon glyphicon-search"></span>
				</button>
				<button class="btn btn-default addnewlist" type="button">
					<span class="glyphicon glyphicon-plus"></span>
				</button>
			</span>
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
				<label>Payment Posting Details</label>
			</div>
		</div>
		<div class="clearfix"></div>
		<div class="tab-content">
			<!-- Master tab start-->

			<div class="tab-pane active in" id="AddUoM">
				<div class="card">
					<div class="posrelative" id="example">
						<div class="demo-section k-content">
							<div id="tabstrip" class="Firsttab">
								<ul>
									<!-- tabs -->
									<li id ="unpushedPayments" class="k-state-active">Payment Posting Details</li>
									 <li id = "pushedPayments">Pushed Payment Posting Details</li>
								</ul>

								<!--fields of field group 1  -->
								<div>
										<!-- <div class="detailscont text-right">
											<div class="col-sm-12"><button type="button" class="btn btn-default bluebutton">Push</button></div>
										</div> -->
									<div class="form-group">
										<div class="col-sm-12">
											<h4>
												<b>Payment Posting Details</b>
											</h4>
											<hr>
										</div>
									</div>
									<div class="form-group">
										<div class="col-sm-4">
											<div class="styled-input partnerTabs">
												<input type = hidden id = "eq">
                                                <input type="text"  name="" value="" readonly="readonly" class = "docNO" />
                                                <label>Document/Ref No<span class="red">*</span></label>
                                                <span></span>
                                            </div>
										</div>
										<div class="mobclearfix"></div>
										<div class="col-sm-4">
											<div class="styled-input partnerTabs">
                                                <input type="text" name="" value="" readonly="readonly" class = "vendorCode" />
                                                <label>Vendor Code<span class="red">*</span></label>
                                                <span></span>
                                            </div>
										</div>
										<div class="mobclearfix"></div>
										<div class="col-sm-4">
											<div class="styled-input partnerTabs">
                                                <input type="text"  name="" value="" readonly="readonly" class = "tendorNo" />
                                                <label>Tendor No<span class="red">*</span></label>
                                                <span></span>
                                            </div>
										</div>
									</div>
									<div class="form-group">
										<div class="col-sm-4">
											<div class="styled-input partnerTabs">
                                                <input type="text"  name="" readonly="readonly" value="" class = "paymentType" />
                                                <label>Payment Type<span class="red">*</span></label>
                                                <span></span>
                                            </div>
										</div>
										<div class="mobclearfix"></div>
										<div class="col-sm-4">
											<div class="styled-input partnerTabs">
                                                <input type="text"  name="" value="" readonly="readonly" class = "grossAmount" />
                                                <label>Gross Amount<span class="red">*</span></label>
                                                <span></span>
                                            </div>
										</div>
										<!--<div class="mobclearfix"></div>
										 <div class="col-sm-4">
											<div class="styled-input partnerTabs">
                                                <input type="text"  name="" value="" readonly="readonly" class = "charges" />
                                                <label>Charges<span class="red">*</span></label>
                                                <span></span>
                                            </div>
										</div> -->
									</div>	
									<div class="form-group">
									</div>
									<div class="col-sm-12 text-center positionABS" style="margin-bottom: 10px;">
										<button id="postPaymentDetail" class="btn btn-info">Push</button>
									</div>								
								</div>
								<!--fields of field group 1  -->
								<!--fields of field group 2  -->
									<!--fields of field group 1  -->
								<div>
								<sf:form  modelAttribute="">
										
									<div class="form-group">
										<div class="col-sm-12">
											<h4>
												<b>Payment Posting Details</b>
											</h4>
											<hr>
										</div>
									</div>
									<div class="form-group">
										<div class="col-sm-4">
											<div class="styled-input partnerTabs">
                                                <input type="text"  name="" value="" readonly="readonly" class = "docNO" />
                                                <label>Document/Ref No<span class="red">*</span></label>
                                                <span></span>
                                            </div>
										</div>
										<div class="mobclearfix"></div>
										<div class="col-sm-4">
											<div class="styled-input partnerTabs">
                                                <input type="text" name="" value="" readonly="readonly" class = "vendorCode" />
                                                <label>Vendor Code<span class="red">*</span></label>
                                                <span></span>
                                            </div>
										</div>
										<div class="mobclearfix"></div>
										<div class="col-sm-4">
											<div class="styled-input partnerTabs">
                                                <input type="text"  name="" value="" readonly="readonly" class = "tendorNo" />
                                                <label>Tendor No<span class="red">*</span></label>
                                                <span></span>
                                            </div>
										</div>
									</div>
									<div class="form-group">
										<div class="col-sm-4">
											<div class="styled-input partnerTabs">
                                                <input type="text"  name="" readonly="readonly" value="" class = "paymentType" />
                                                <label>Payment Type<span class="red">*</span></label>
                                                <span></span>
                                            </div>
										</div>
										<div class="mobclearfix"></div>
										<div class="col-sm-4">
											<div class="styled-input partnerTabs">
                                                <input type="text"  name="" value="" readonly="readonly" class = "grossAmount" />
                                                <label>Gross Amount<span class="red">*</span></label>
                                                <span></span>
                                            </div>
										</div>
										<div class="mobclearfix"></div>
										<!-- <div class="col-sm-4">
											<div class="styled-input partnerTabs">
                                                <input type="text"  name="" value="" readonly="readonly" class = "charges" />
                                                <label>Charges<span class="red">*</span></label>
                                                <span></span>
                                            </div>
										</div> -->
									</div>	
									<div class="form-group">
									<div class="col-sm-4">
											<div class="styled-input partnerTabs">
                                                <input type="text"  name="" value="" readonly="readonly" class = "lvexport" />
                                                <label>LV Export<span class="red">*</span></label>
                                                <span></span>
                                            </div>
									</div>
									</div>								
								</sf:form>
								</div>
								<!--fields of field group 1  -->
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

<script src="<%=request.getContextPath()%>/resources/${appMode}/master/js/paymentPosting.js?appVer=${appVer}"></script>
