
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@taglib uri = "http://www.springframework.org/tags/form" prefix = "sf"%>
<%String context = request.getContextPath();%>
<link rel="stylesheet" href="<%=context%>/resources/${appMode}/tilescommon/css/fioristyle.css?appVer=${appVer}"/>
<link rel="stylesheet" href="<%=context%>/resources/${appMode}/tilescommon/css//borderTab.css?appVer=${appVer}">
<link rel="stylesheet" href="<%=context%>/resources/${appMode}/tilescommon/css/jquery-ui.css?appVer=${appVer}">
 <link rel="stylesheet" href="<%=context%>/resources/${appMode}/tilescommon/css/examples.css?appVer=${appVer}"/> 
 <link rel="stylesheet" href="<%=context%>/resources/${appMode}/tilescommon/css/fontawesome-stars.css?appVer=${appVer}"/>
<script src="<%=context%>/resources/${appMode}/commons/js/springform.js?appVer=${appVer}"></script>
<script src="<%=context%>/resources/${appMode}/commons/js/utility.js?appVer=${appVer}"></script>
<script src="<%=context%>/resources/${appMode}/commons/js/loadList.js?appVer=${appVer}"></script>
<script src="<%=context%>/resources/${appMode}/commons/js/loadItemList.js?appVer=${appVer}"></script>
<script src="<%=context%>/resources/${appMode}/commons/js/formFields.js?appVer=${appVer}"></script>
<script src="<%=context%>/resources/${appMode}/partner/js/uploadFile.js?appVer=${appVer}"></script>
<script src="<%=context%>/resources/${appMode}/tilescommon/js/jquery-ui.js?appVer=${appVer}"></script>
<script src="<%=context%>/resources/${appMode}/tilescommon/js/moment.js?appVer=${appVer}"></script>
<script src="<%=context%>/resources/${appMode}/tilescommon/js/bootstrap-datetimepicker.js?appVer=${appVer}"></script>
<script src="<%=context%>/resources/${appMode}/tilescommon/js/bootstrap-datepicker.js?appVer=${appVer}"></script>
<script src="<%=context%>/resources/${appMode}/transaction/js/winnerSelection.js?appVer=${appVer}"></script>
<script src="<%=context%>/resources/${appMode}/transaction/js/awardWinnerTahdrDetails.js?appVer=${appVer}"></script>
<script src="<%=context%>/resources/${appMode}/transaction/js/awardWinnerTahdrMaterial.js?appVer=${appVer}"></script>
<script src="<%=context%>/resources/${appMode}/transaction/js/awardWinnerMaterialBidder.js?appVer=${appVer}"></script>
<script src="<%=context%>/resources/${appMode}/tilescommon/js/jquery.barrating.js?appVer=${appVer}"></script> 
<script src="<%=request.getContextPath()%>/resources/${appMode}/tilescommon/js/number-starRating.js?appVer=${appVer}"></script>
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/${appMode}/tilescommon/css/mobile.css?appVer=${appVer}" /> 
<script src="<%=context%>/resources/${appMode}/tilescommon/js/kendo.all.min.js?appVer=${appVer}"></script>
            <script>
                $(document).ready(function() {
                    $("#tabstrip").kendoTabStrip();
                    $("#tabstrip1").kendoTabStrip();
                });
               
            </script>
    
        <!-- Header end -->
        <div class="full-container">
            <!-- left-side start-->
            <div class="clearfix"></div>
            <%-- <input type="hidden" value="${documentType }" id="documentType"/> --%>
            <div id="mobile_first_container" class="left-side col-md-3 no-marg">
            <div class="detailsheader leftPaneHeader">
        		
       		</div>         
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
                    <button class="btn btn-default" type="button"><span class="glyphicon glyphicon-search"></span></button>
                    <button class="btn btn-default addnewlist" type="button">
					<span class="glyphicon glyphicon-plus"></span>
				</button>
                    </span>
                </div>
                 <input type="hidden" class="documentType" value="${documentType}" />
                 <c:if test="${documentType.equalsIgnoreCase('Tender')}"> 
                <div class="btn-group btnmrg" data-toggle="buttons">
				    <label class="btn btn-primary toggleNewTab" openTab="tenderDetail">
						<input type="radio" name="tenderTypeCodeToggle" value="PT">
						<span class="glyphicon glyphicon-ok"></span> Procurement 
                    </label>
                    <label class="btn btn-primary active toggleNewTab" openTab="tenderDetail">
                    <input type="radio" name="tenderTypeCodeToggle" checked value="WT"> 
                    <span class="glyphicon glyphicon-ok"></span> Works
                    </label>
                </div>
               </c:if> 
				 <c:if test="${documentType.equalsIgnoreCase('Auction')}">
					<input type="radio" name="tenderTypeCodeToggle" checked style="display:none;" value="FA" >
				</c:if> 
				<c:if test="${documentType.equalsIgnoreCase('Quick Request For Proposal')}">
					<input type="radio" name="tenderTypeCodeToggle" checked style="display:none;" value="QRFQ" >
				</c:if>
				<c:if test="${documentType.equalsIgnoreCase('Request For Proposal')}">
					<input type="radio" name="tenderTypeCodeToggle" checked style="display:none;" value="RFQ" >
				</c:if>
                <ul id="leftPane" class="nav nav-tabs tabs-left leftPaneData">
				</ul>
				<p id="pagination-here"></p>
				<div class="clearfix"></div>
            </div>
            <!-- left-side end-->

            <!-- right-side start-->
            <div id="mobile_second_container" class="right-side col-md-9 no-marg">
             <div class="detailsheader toptabbrd">
        		<div class="col-sm-9 text-center"><label> Winner Selection </label></div>
        	 </div>
                <div class="clearfix"></div>
                <div class="tab-content">
                    <!-- Master tab start-->

                    <div class="tab-pane active in" id="">
                        <div class="card">
                            <div class="posrelative" id="example">
                           <div class="demo-section k-content">
                                    <div id="tabstrip" class="Firsttab">
                                        <ul id="tabUL">
                                            <!-- tabs -->
                                            <li class="k-state-active tenderDet" id="tenderDetail">Base Info Details</li>
                                            <li class="winnerItems toggleTab" id="winnerItem" openTab="ItemsofTahdrs">Winner Selection</li>
                                             <c:if test="${type == 'winnerSelection'}">
                                           		<li class="winnerConfirmationTab" id="winnerConfirmationTabId">Winner Confirmation</li>
                                            </c:if>
                                        </ul>

                                        <!--fields of field group 1  -->
									<div>
									<div class="detailscont"></div>
									<sf:form class="" id="tahdrForm" action="createTahdr" method="POST" autocomplete="off" modelAttribute="tahdr">
										<div class="form-group">
											<div class="col-sm-4">
												<h4>
													<b>Base Information Details</b>
												</h4>
											</div>
											<hr>
										</div>
										<div id="tenderBaseForm">
											<div class="form-group">
												<div class="col-sm-4">
													<div class="styled-input">
														<input style="display: none" class="tahdrId" name="" id="tenderId"> 
														<input style="display: none" class="tahdrDetail" id="tahdrDetail" >
														<input type="text" id="tahdrCode" name="" value="" readonly="readonly" />
														<label> Code<span class="red">*</span></label>
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
												<!-- <div class="col-sm-4">
													<div class="styled-input">
														<input type="text" id="EMD" name="" value="" readonly="readonly" />
														<label>EMD<span class="red">*</span></label>
														<span></span>
													</div>
												</div> -->
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
												<!-- <div class="col-sm-4 readonly">
													<div class="form-group frgrp">
														<label for="dtp_input1" class="control-label">Technical Bid Opening Date & Time<span class="red">*</span>
														</label>
														<div class="input-group date form_datetime" id="technicalBidOpeningDates" >
															<input type="text" class="form-control dateBidOpening" id="techBidOpenningDate" name="" readonly="readonly">
															<span class="input-group-addon"><span class="glyphicon glyphicon-th"></span></span>
														</div>
														<input type="hidden" id="dtp_input1" value="" /><br />
													</div>
												</div> -->
												<!-- <div class="col-sm-4 readonly">
												<div class="form-group frgrp">
													<label for="dtp_input1" class="control-label">Price Bid Opening Date & Time<span class="red">*</span>
													</label>
													<div class="input-group date form_datetime" id="priceBidOpeningDates" >
														<input type="text" class="form-control preBidOpenDate" id="priceBidOpenningDate" name="" readonly="readonly">
														<span class="input-group-addon"><span class="glyphicon glyphicon-th"></span></span>
													</div>
													<input type="hidden" id="dtp_input1" value="" /><br />
												</div>
												</div> -->
												<!-- <div class="col-sm-4 readonly">
												<div class="form-group">
													<label for="dtp_input1" class="control-label">Annexure C1 Opening Date & Time<span class="red">*</span>
													</label>
													<div class="input-group date form_datetime" id="AnnexC1OpeningDates" >
														<input type="text" class="form-control AnnextureOpenDate" id="c1OpenningDate" name="" readonly="readonly">
														<span class="input-group-addon"><span class="glyphicon glyphicon-th"></span></span>
													</div>
													<input type="hidden" id="dtp_input1" value="" /><br />
												</div>
											</div> -->
												<div class="clearfix"></div>
											</div>
										</div>
										<div class="clearfix"></div>
										<div class="col-sm-12 text-center positionABS" style="margin-bottom: 10px;">
											<button class="btn-all btn btn-info k-tabstrip-next pull-right tab-li-next">Next</button>
										</div>
									</sf:form>
								</div>
								<!--fields of field group 1  -->
                                        <!--fields of field group 2  -->
                                        <!-- <div>
                                        <div class="detailscont">
  							 				<div class="col-md-12" id="masterDetails">
                                
                           					 </div>
    									</div>
                                            <div class="form-group">
                                                <div class="col-sm-12">
                                                    <h4><b>Winner Selection</b></h4>
                                                    <hr>
                                                </div>
                                            </div>
                                      <div class="col-sm-12">
                                             	<table class="table table-bordered CommercialDocumentTable">
                                                        <thead>
                                                            <tr>
                                                            <th scope="col">Bidder</th>
                                                                <th scope="col">Awkward Quantity</th>
                                                                <th scope="col">Awkward Price</th>
                                                                <th scope="col">Comments</th>
                                                            </tr>
                                                        </thead>
                                                        <tbody>
                                                            <tr>
                                                                <th><input type="radio"></th>
                                                                <td></td>
                                                                <td><button class="btn btn-default">REG PDF</button></td>
                                                                <td><button class="btn btn-default">TECH BID PDF</button></td>
                                                            </tr>                                                                                                                       
                                                        </tbody>
                                                    </table>
                                             </div>                                            
                                        </div> -->
                                        <!--fields of field group 2  -->
                                        <!-- field 2 -->
                                        <div>
                                       <div class="detailscont">
										</div>
                                      <div id="tabstrip1">
                                        <ul>
                                            <!-- tabs -->
                                           <li class="k-state-active winnerItems ItemsofTahdrs" id="ItemsofTahdr" >Items List</li>
                                           <li id="BidderDetails">Bidder</li>
                                           <c:if test="${type == 'rating'}">
	                                            <li id="ratingDetails" >Rating</li>
                                           </c:if>
                                        </ul>			
                                        <!--tab inside tab fields-1  -->
                                  
                                      <div>
									<sf:form class="" id="itemForm"  method="POST" autocomplete="off">
										<div class="form-group">
											<div class="col-sm-4">
												<h4>
													<b>Item Details</b>
												</h4>
											</div>
											<hr>
										</div>
										<div id="ItemDetailsForm">
											<div class="form-group">
												<div class="col-sm-4">
													<div class="styled-input">
														<!-- <input style="display: none" class="tahdrId" name=""> --> 
														<input style="display: none" class="tahdrMaterial" id="tahdrMaterial" name="">
														<!-- <input style="display: none" Class="tahdrDetail" > -->
														
														<input type="text" id="materialName" name="" value="" readonly="readonly" />
														<label>Material Name<span class="red">*</span></label>
														<span></span>
													</div>
												</div>
												<div class="col-sm-4">
													<div class="styled-input">
														<input type="text" id="uom" name="" value="" readonly="readonly" />
														<label>UOM<span class="red">*</span></label>
														<span></span>
													</div>
												</div>
												<div class="col-sm-4">
													<div class="styled-input">
														<input type="text" id="quantity" class="quantity" name="" value="" readonly="readonly" />
														<label>Quantity<span class="red">*</span></label>
														<span></span>
													</div>
												</div>
												<div class="col-sm-4">
													<div class="styled-input">
														<input type="text" id="matDescription" name="" value="" readonly="readonly" />
														<label>Material Description<span class="red">*</span></label>
														<span></span>
													</div>
												</div>
												<div class="col-sm-4">
													<div class="styled-input">
														<input type="text" id="spec" name="" value="" readonly="readonly" />
														<label>Material Specification<span class="red">*</span></label>
														<span></span>
													</div>
												</div>
												<div class="col-sm-4">
													<div class="styled-input">
														<input type="text" id="matType" name="" value="" readonly="readonly" />
														<label>Material Type<span class="red">*</span></label>
														<span></span>
													</div>
												</div>
												<div class="clearfix"></div>
											</div>
										</div>
										<div class="clearfix"></div>
										<div class="col-sm-12 text-center positionABS" style="margin-bottom: 10px;">
											<button class="btn-all btn btn-info k-tabstrip-prev pull-left tab-li-prev">Previous</button>
											<button class="btn-all btn btn-info k-tabstrip-next pull-right tab-li-next">Next</button>
										</div>
									</sf:form>
								</div>
                   
                    <!-- <tab field 2.2> -->
			<div id="partsTab">
					
					<div class="tab-content col-md-11">
						<div class="tab-pane active" id="tab_e">
							<sf:form class="" id="MaterialBidders" method="POST" autocomplete="off" modelAttribute="materialBidders">
                                                   <div class="form-group">
													<div class="col-sm-12 ">
														<h4 class="col-xs-6 nopadding">
															<b>Bidder Details</b>
														</h4>
														
														<hr>
													</div>
												</div>
                <div class="form-group">  
                <input type="hidden" id="winnerSelectionId" class="winnerSelectionId" name="winnerSelectionId" value="" />
                <input type="hidden" id="bidderId" class="bidderId"  name="itemBid.bidder.bidderId" value="" />
                <input type="hidden" id="priceBidId" class="priceBidId" value="" />
                 <input type="hidden" id="itemBidId" class="itemBidId"  name="itemBid.itemBidId" value="" />
                 <input type="hidden" id="tahdrMaterialId" class="tahdrMaterialId" name="itemBid.tahdrMaterial.tahdrMaterialId" value="" />
                  <input type="hidden" id="tahdrId" class="tahdrId" name="tahdr.tahdrId" value="" />
                  <input type="hidden" class="tahdrDetail" id="tahdrDetail" name="">
               <!--  <input type="hidden"id="matId"  value="" /> -->
                
                 <div class="col-sm-4">
					<div class="styled-input">
						<input type="text" id="factoryName" name="" value="" readonly="readonly" />
							<label>Company Name<span class="red">*</span></label>
								<span></span>
					</div>
				</div>
                 <div class="col-sm-4">
					<div class="styled-input">
						<input type="text" id="tahdrName" name="" value="" readonly="readonly" />
							<label>${documentType} Name<span class="red">*</span></label>
								<span></span>
					</div>
				</div>
                <div class="col-sm-4">
					<div class="styled-input">
						<input type="text" id="tahdrNo" name="" value="" readonly="readonly" />
							<label>${documentType} No.<span class="red">*</span></label>
								<span></span>
					</div>
				</div> 
               <div class="col-sm-4 purchased">
					<div class="styled-input">
						<input type="text" id="purchasedAs" name="" value="" readonly="readonly" />
							<label>Purchased As<span class="red">*</span></label>
								<span></span>
					</div>
				</div> 
				<div class="col-sm-4 factoryOfPurchased">
					<div class="styled-input">
						<input type="text" id="factoryOfPurchased" name="" value="" readonly="readonly" />
							<label>Factory Name<span class="red">*</span></label>
								<span></span>
					</div>
				</div>
				<div class="col-sm-4">
					<div class="styled-input">
						<input type="text" id="offeredQty" name="" value="" readonly="readonly" />
							<label>Offered Quantity<span class="red">*</span></label>
								<span></span>
					</div>
				</div>
				<div class="col-sm-4">
					<div class="styled-input">
						<input type="text" id="fddRate" name="" value="" readonly="readonly" />
							<label>FDD Rate (GST/Unit)<span class="red">*</span></label>
								<span></span>
					</div>
				</div>
				<div class="col-sm-4">
					<div class="styled-input">
						<input type="text" id="fddAmountGST" name="" value="" readonly="readonly" />
							<label>FDD Amount(With GST)<span class="red">*</span></label>
								<span></span>
					</div>
				</div>
				<div class="col-sm-4">
					<div class="styled-input">
						<input type="text" id="allocateQty"  class="allocateQty onlyNumber" value="" name="allocatedQty" onkeyup="calcualteQty(this)" />
							<label>Allocate Quantity<span class="red">*</span></label>
								<span></span>
					</div>
				</div>
				<div class="col-sm-4">
					<div class="styled-input">
						<input type="text" id="fddAmountofAQ" name="" value="" readonly="readonly" />
							<label>FDD Amount<span class="red">*</span></label>
								<span></span>
					</div>
				</div>
				<div class="col-sm-4">
					<div class="styled-input">
						<input type="text" id="remQty" name="" readonly="readonly" />
							<label>Remaining Quantity<span class="red">*</span></label>
								<span></span>
					</div>
				</div>
                </div>          
                  <div class="clearfix"></div>
                    <div class="col-md-12 text-center">
                    </div>
                 <!-- </form> -->
                 <div class="col-sm-12 text-center positionABS" style="margin-bottom:10px;">
                                        <button class="btn-all btn btn-info k-tabstrip-prev btnPrevious pull-left">Previous</button>
                                        <c:if test="${type == 'winnerSelection'}">
                                        	<button class="btn btn-info winnerSelectionBidder">Save</button>
                                        </c:if>
                                        <button id="cancelBtn" class="btn-all btn btn-info k-tabstrip-next btnNext pull-right toggleTab" openTab="">Cancel</button>
                                   </div>
                 </sf:form>
                      </div>
                   </div>
             </div>
            	
			<c:if test="${type == 'rating' }">						
             <input type="hidden" class="typeOfAward" value="${type}" />
             <div>
             <div class="clearfix"></div>
             	   <div class="form-group">
                                                <div class="col-sm-12">
                                                    <h4><b>Rating</b></h4>
                                                    <!-- <div class="col-sm-6 text-right">
                                                    	<button class="btn btn-default bluebutton" id="sendnewinvitation">Send New Invitation</button>
                                                    </div> -->
                                                    <div class="clearfix"></div>
                                                    <hr>
                                                </div>
                                            </div>
                                            <div class="clearfix"></div>
    									      <div class="col-sm-8" style="margin-top: 20px;">
    									      <sf:form class="" id="rating" method="POST" autocomplete="off" modelAttribute="rating">
    									       <input type="hidden" id="" class="winnerSelectionId" name="winnerSelectionId" value="">
    									       <input type="hidden" id="bidderId" class="bidderId"  name="bidder.bidderId" value="" />
													<table
														class="Sessionaudit table table-striped table-bordered"
														width="100%" id="tahdrTableId">
														<tr>
														    <th>Quantity</th>
														    <td>
																 <select class="Star_rating" name ="qualityRating" id = "qualityRating">
																  <option value="1">1</option>
																  <option value="2">2</option>
																  <option value="3">3</option>
																  <option value="4">4</option>
																  <option value="5">5</option>
																  <option value="6">6</option>
																  <option value="7">7</option>
																  <option value="8">8</option>
																  <option value="9">9</option>
																  <option value="10">10</option>
																</select>
															</td>
														  </tr>
														  <tr>
														    <th>Delivery</th>
														    <td>
																 <select class="Star_rating" name = "deliveryRating" id = "deliveryRating">
																  <option value="1">1</option>
																  <option value="2">2</option>
																  <option value="3">3</option>
																  <option value="4">4</option>
																  <option value="5">5</option>
																  <option value="6">6</option>
																  <option value="7">7</option>
																  <option value="8">8</option>
																  <option value="9">9</option>
																  <option value="10">10</option>
																</select>
															</td>
														  </tr>
														  <tr>
														    <th>Service</th>
														    <td>
																 <select class="Star_rating" name = "serviceRating" id = "serviceRating">
																  <option value="1">1</option>
																  <option value="2">2</option>
																  <option value="3">3</option>
																  <option value="4">4</option>
																  <option value="5">5</option>
																  <option value="6">6</option>
																  <option value="7">7</option>
																  <option value="8">8</option>
																  <option value="9">9</option>
																  <option value="10">10</option>
																</select>
															</td>
														  </tr>
														  <tr>
														    <th>Price</th>
														    <td>
																 <select class="Star_rating" name = "priceRating" id = "priceRating">
																  <option value="1">1</option>
																  <option value="2">2</option>
																  <option value="3">3</option>
																  <option value="4">4</option>
																  <option value="5">5</option>
																  <option value="6">6</option>
																  <option value="7">7</option>
																  <option value="8">8</option>
																  <option value="9">9</option>
																  <option value="10">10</option>
																</select>
															</td>
														  </tr>
													</table>
											</sf:form>	
											<div class="col-sm-12" style="margin-bottom:10px;">
											<label class = "col-sm-4" >Average Rating: <span id = "averageRating"></span></label>
											<label class = "col-sm-4" >Rating: <span id = "weightageRating"></span></label>	
											</div>	
											
										</div>
										<div class="col-sm-12 text-center"><button class="btn btn-default saveRating">Submit</button></div>
             </div>
            </c:if>
           </div>
           </div>
            <c:if test="${type == 'winnerSelection'}">
            <div>
									<div class="detailscont"></div>
              	<input type="hidden" class="typeOfAward" value="${type}" />
              	<div>
              		<div class="col-sm-12 text-center" id="winnerSubmitRespDivId">
                                           <label id="submitRespLabelId"></label>
                                       </div>
             		<div class="col-sm-12 text-center"><button class="btn btn-default save winnerSubmit">Submit</button></div>
				</div>
				</div>
			</c:if>
           </div>
                                        <!-- field 2 -->
                                    </div>

                                    <!-- <div class="col-sm-12 text-center positionABS" style="margin-bottom:10px;">
                                        <button class="btn-all btn btn-info k-tabstrip-prev btnPrevious pull-left">Previous</button>
                                        <button class="btn btn-info tahdrPurchaseDetail">Save</button>
                                        <button id="cancelBtn" class="btn-all btn btn-info k-tabstrip-next btnNext pull-right toggleTab" openTab="tenderDet">Cancel</button>
                                   </div> -->
                                </div>
                                <!-- Master tab end-->

                            </div>
                        </div>
                        <!-- right-side end-->
                    </div>
                </div>
            </div>
            <div class="clearfix"></div>

<script type="text/javascript">
   $(function() {
      $('.Star_rating').barrating({
        theme: 'fontawesome-stars'
      });
   });
</script>