
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
<script src="<%=context%>/resources/${appMode}/tilescommon/js/jquery.barrating.js?appVer=${appVer}"></script> 
<script src="<%=context%>/resources/${appMode}/transaction/js/commonVendorRating.js?appVer=${appVer}"></script> 
<script src="<%=request.getContextPath()%>/resources/${appMode}/tilescommon/js/number-starRating.js?appVer=${appVer}"></script> 
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/${appMode}/tilescommon/css/mobile.css?appVer=${appVer}"/>
<script src="<%=request.getContextPath()%>/resources/${appMode}/tilescommon/js/kendo.all.min.js?appVer=${appVer}"></script> 

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
                <input type="text" id="projectContext"  style="display:none;" value="<%=request.getContextPath()%>" >
                    <div class="input-group-btn search-panel">
                        <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">
                            <span id="search_concept">Filter by</span> <span class="caret"></span>
                        </button>
                        <ul class="dropdown-menu" role="menu" id="filter">
							<li data-documenttype="TENDER" class='active filterByTenderType'><a href="#">Tender</a></li>
							<li class="divider"></li>
							<li data-documenttype="AUCTION" class='filterByTenderType'><a href="#">Auction</a></li>
							<li class="divider"></li>
							<li data-documenttype="QUICK_AUCTION" class='filterByTenderType'><a href="#">Quick Auction</a></li>
							<li class="divider"></li>
							<li data-documenttype="QUICK_RFQ" class='filterByTenderType'><a href="#">Quick RFQ</a></li>
							<li class="divider"></li>
							<li data-documenttype="RFQ" class='filterByTenderType'><a href="#">RFQ</a></li>
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
                
                <div class="btn-group btnmrg" data-toggle="buttons" id="toggleBtn">
				   
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
        		<div class="col-sm-9 text-center"><label> Vendor Rating</label></div>
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
                                            <li class="k-state-active tenderDet"> Vendor Rating</li>
                                        </ul>

                                        <!--fields of field group 1  -->
									<div>
									<form class="" id="bidderDetailsForm" method="POST" autocomplete="off">
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
						                
						                <input type="hidden" id="itemBidId" class="itemBidId"  name="itemBid.itemBidId" value="" />
						                <input type="hidden" id="tahdrMaterialId" class="tahdrMaterialId" name="itemBid.tahdrMaterial.tahdrMaterialId" value="" />
						                <input type="hidden" id="tahdrId" class="tahdrId" name="tahdr.tahdrId" value="" />
						                <input type="hidden" class="tahdrDetail" id="tahdrDetail" name="tahdr.tahdrDetail.tahdrDetailId">
											               <!--  <input type="hidden"id="matId"  value="" /> -->
											                
											                 <div class="col-sm-4">
																<div class="styled-input">
																	<input type="text" id="factoryName" name="" value="" readonly="readonly" />
																		<label>Company Name<span class="red">*</span></label>
																			<span></span>
																</div>
															</div>
											                <!--  <div class="col-sm-4">
																<div class="styled-input">
																	<input type="text" id="tahdrName" name="" value="" readonly="readonly" />
																		<label>Tender Name<span class="red">*</span></label>
																			<span></span>
																</div>
															</div> -->
											                <div class="col-sm-4">
																<div class="styled-input">
																	<input type="text" id="tahdrNo" name="" value="" readonly="readonly" />
																		<label>Tender No.<span class="red">*</span></label>
																			<span></span>
																</div>
															</div> 
											               <!-- <div class="col-sm-4 purchased">
																<div class="styled-input">
																	<input type="text" id="purchasedAs" name="" value="" readonly="readonly" />
																		<label>Purchased As<span class="red">*</span></label>
																			<span></span>
																</div>
															</div>  -->
															<div class="col-sm-4 factoryOfPurchased">
																<div class="styled-input">
																	<input type="text" id="factoryOfPurchased" name="" value="" readonly="readonly" />
																		<label>Factory Name<span class="red">*</span></label>
																			<span></span>
																</div>
															</div>
															<!-- <div class="col-sm-4">
																<div class="styled-input">
																	<input type="text" id="offeredQty" name="" value="" readonly="readonly" />
																		<label>Offered Quantity<span class="red">*</span></label>
																			<span></span>
																</div>
															</div> -->
															<!-- <div class="col-sm-4">
																<div class="styled-input">
																	<input type="text" id="fddRate" name="" value="" readonly="readonly" />
																		<label>FDD Rate (GST/Unit)<span class="red">*</span></label>
																			<span></span>
																</div>
															</div> -->
															<!-- <div class="col-sm-4">
																<div class="styled-input">
																	<input type="text" id="fddAmountGST" name="" value="" readonly="readonly" />
																		<label>FDD Amount(With GST)<span class="red">*</span></label>
																			<span></span>
																</div>
															</div> -->
															<div class="col-sm-4">
																<div class="styled-input">
																	<input type="text" id="allocateQty"  class="allocateQty onlyNumber" value="" readonly="readonly"  />
																		<label>Allocate Quantity<span class="red">*</span></label>
																			<span></span>
																</div>
															</div>
															<!-- <div class="col-sm-4">
																<div class="styled-input">
																	<input type="text" id="fddAmountofAQ" name="" value="" readonly="readonly" />
																		<label>FDD Amount<span class="red">*</span></label>
																			<span></span>
																</div>
															</div> -->
															
											                </div>          
											                  <div class="clearfix"></div>
											                    <div class="col-md-12 text-center">
											                    </div>
											                 <!-- </form> -->
											                
											                 </form>
                 								<div class="col-sm-8" style="margin-top: 20px;">
    									      <sf:form class="" id="rating" method="POST" autocomplete="off" modelAttribute="rating">
    									       <input type="hidden" id="" class="winnerSelectionId" name="winnerSelectionId" value="">
    									       <input type="hidden" id=" " class="bidderId"  name="itemBid.bidder.bidderId" value="" />
													<table class="Sessionaudit table table-striped table-bordered" width="100%" id="tahdrTableId">
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
											<div class="col-sm-12" style="margin-bottom:10px;">
											<label class = "col-sm-4" >Average Rating: <span id = "averageRating"></span></label>
											<label class = "col-sm-4" >Rating: <span id = "weightageRating"></span></label>	
											</div>	
											<div class="col-sm-12 text-center"><button class="btn btn-default saveRating">Submit</button></div>
											</sf:form>	
										</div>
								</div>
					           </div>
					           </div> 
					            
					           </div>
                                        <!-- field 2 -->
                                    </div>

                                    
                                </div>
                                <!-- Master tab end-->

                            </div>
                        </div>
                        <!-- right-side end-->
                    </div>
            <div class="clearfix"></div>

<script type="text/javascript">
   $(function() {
      $('.Star_rating').barrating({
        theme: 'fontawesome-stars'
      });
   });
</script>