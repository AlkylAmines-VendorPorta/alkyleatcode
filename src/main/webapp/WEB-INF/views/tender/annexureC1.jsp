<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@taglib uri = "http://www.springframework.org/tags/form" prefix = "sf"%>

<% String context=request.getContextPath(); %>

<link rel="stylesheet" href="<%=context%>/resources/${appMode}/tilescommon/css/fioristyle.css?appVer=${appVer}"/>
<link rel="stylesheet" href="<%=context%>/resources/${appMode}/tilescommon/css//borderTab.css?appVer=${appVer}">

<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/${appMode}/tilescommon/css/mobile.css?appVer=${appVer}">
 <script src="<%=request.getContextPath()%>/resources/${appMode}/tilescommon/js/kendo.all.min.js?appVer=${appVer}"></script>
<script src="<%=context%>/resources/${appMode}/commons/js/springform.js?appVer=${appVer}"></script>
<script src="<%=context%>/resources/${appMode}/commons/js/utility.js?appVer=${appVer}"></script>
<script src="<%=context%>/resources/${appMode}/commons/js/formFields.js?appVer=${appVer}"></script>
<script src="<%=context%>/resources/${appMode}/commons/js/commonValidation.js?appVer=${appVer}"></script>
<script src="<%=context%>/resources/${appMode}/transaction/js/annexureC1.js?appVer=${appVer}"></script>
<script src="<%=request.getContextPath()%>/resources/${appMode}/partner/js/uploadFile.js?appVer=${appVer}"></script>

            <script>
                $(document).ready(function() {
                	$("#tabstrip").kendoTabStrip();
                    $("#tabstrip2").kendoTabStrip();
                    $("#tabstrip3").kendoTabStrip();
                    $("#tabstrip4").kendoTabStrip();
                    $("#tabstrip5").kendoTabStrip();
                    $("#tabstrip6").kendoTabStrip();
                    /* $('#leftPane').paginate({
                		perPage: 6,
                	}); */
                });
            </script>
        <div class="full-container">
        <input type="hidden" id="myTenderUrl" class="myTenderUrl" value="${myTenderUrl}" />
            <!-- left-side start-->
            <div class="clearfix"></div>
            <div id="mobile_first_container" class="left-side col-md-3 no-marg">
            <div class="detailsheader leftPaneheader">
        		
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
                    <input type="text" class="form-control" name="x" placeholder="Search term...">
                    <span class="input-group-btn">
                    <button class="btn btn-default" type="button"><span class="glyphicon glyphicon-search"></span></button>
                    <button class="btn btn-default addnewlist" type="button">
					<span class="glyphicon glyphicon-plus"></span>
				</button>
                    </span>
                </div>
			       <c:if test="${documentType.equalsIgnoreCase('Tender')}">
					<div class="btn-group btnmrg" data-toggle="buttons" >
			         <label class="btn btn-primary toggleNewTab" openTab="tenderBaseInfoTab">
				         <input type="radio" name="tenderTypeCodeToggle" value="PT" >
				         <span class="glyphicon glyphicon-ok"></span> Procurement 
			         </label>
			         <label class="btn btn-primary active toggleNewTab" openTab="tenderBaseInfoTab">
				         <input type="radio" name="tenderTypeCodeToggle" checked value="WT" >
				         <span class="glyphicon glyphicon-ok"></span> Works
			         </label>
			      </div>
				</c:if>
				 <c:if test="${documentType.equalsIgnoreCase('Auction')}">
					<div class="btn-group btnmrg" data-toggle="buttons" >
			         <label class="btn btn-primary toggleNewTab" openTab="tenderBaseInfoTab">
				         <input type="radio" name="tenderTypeCodeToggle" value="FA" >
				         <span class="glyphicon glyphicon-ok"></span> Forward 
			         </label>
			         <label class="btn btn-primary active toggleNewTab" openTab="tenderBaseInfoTab">
				         <input type="radio" name="tenderTypeCodeToggle" checked value="RA" >
				         <span class="glyphicon glyphicon-ok"></span> Reverse
			         </label>
			      </div>
				</c:if>
                <ul id="leftPane" class="nav nav-tabs tabs-left example">
                    
                </ul>                
                <div class="clearfix"></div>
            </div>
            <!-- left-side end-->

            <!-- right-side start-->
            <div id="mobile_second_container" class="right-side col-md-9 no-marg">
            <div class="detailsheader toptabbrd">
        		<div class="col-sm-9 text-center"><label>Tender Details</label></div>
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
                                            <li id="tendeDetails" class="k-state-active">My Tender</li>
                                            <li id="" class="toggleTab annexureBid" openTab="matchItemC1TabId"">Annexure C1 Called Items</li>
                                            <li id="confirmAnnexureC1TabId" onclick="getAnnexureConfirmatoryData(this)" data-bidderId="" data-tahdrId="">Annexure C-1 Final Confirmation </li>
                                            <!-- <li>Bid Submission </li>
                                            <li>Digital Signing Of Bid</li>
                                            <li>Tender Bid Opening</li>
                                            <li>Tender Report Preparation</li>  -->                                         
                                        </ul>
                                        <!--fields of field group 1  -->
                                        <div>
                                        <div class="detailscont">
  							 				
    									</div>
                                            <div class="form-group">
                                                <div class="col-sm-12">
                                                    <h4><b>Tender Details</b></h4>
                                                    <hr>
                                                </div>
                                            </div>
                                            <div class="form-group" style="margin-top:20px;">                                                
                                                 	<div class="col-sm-4">
                                                        <label>Tender No :</label>
                                                        <span class="detspan"id="tahdrCode" ></span>
                                                    </div> 
                                                    <div class="col-sm-4">
                                                        <label>Description :</label>
                                                        <span class="detspan"id="description" >Browser Requirements</span>
                                                    </div> 
                                                    <div class="col-sm-4">
                                                        <label>Bid Submitted On :</label>
                                                        <span class="detspan"id="bidSubmissionDate" ></span>
                                                    </div>                                                
                                               </div>
                                               <div class="form-group">                                                 
                                                    <div class="col-sm-4">
                                                        <label>Bid Opened On :</label>
                                                        <span class="detspan"id="priceBidOpeningDate" ></span>
                                                    </div> 
                                                    <div class="col-sm-4">
                                                        <label>Last Annexure C1 Submission Date :</label>
                                                        <span class="detspan"id="c1ToDate" ></span>
                                                    </div>
                                                 <div class="col-sm-4">
                                                        <label>Annexure C1 Bid Opening Date :</label>
                                                        <span class="detspan" id="c1OpenningDate" ></span>
                                                    </div>                                                                                                   
                                               </div>
                                            <div class="clearfix"></div>
                                            <!-- <div class="form-group">                                                
                                                 <div class="col-sm-12">
                                                    <button class="btn btn-default top20 nextBtn">Fill Annexure C1 Bid</button>
                                                    <button class="btn btn-default top20">Download Tender</button>
                                                 </div>
                                               </div> -->
                                            <div class="clearfix"></div>
                                        </div>
                                        <!--fields of field group 1  -->
                                        <!--fields of field group 2  -->
                                        <div>
                                        <div class="detailscont">
  							 				
    									</div>
    									<div id="tabstrip2" class="Firsttab">
                                        <ul>
                                            <!-- tabs -->
                                            <li class="k-state-active matchItemC1TabId annexureBid" id="">Match Annexure C1</li>
                                            <li id="itemConfirmAnnexureC1TabId" onclick="itemC1Comfirmatory(this)">Item Annexure C1 Confirmation</li>                                   
                                        </ul>
                                        <!--fields of field group 1  -->
                                        <div>
                                        
                                            <div class="form-group">
                                                <div class="col-sm-12">
                                                    <h4><b>Annexture C-1</b></h4>
                                                    <hr>
                                                </div>
                                            </div> 
                                               <div class="form-group">
                                               <sf:form id="saveAnnexureC1BidForm" modelAttribute="priceBid">
                                               	<input type="hidden" id="priceBidId" name=""/>
                                             	<input type="hidden" class="itemBidId" name="itemBid.itemBidId"/>
                                             	<input type="hidden" class="tahdrMaterialId" name="itemBid.tahdrMaterial.tahdrMaterialId"/>
                                             	<input type="hidden" class="bidderId" id="bidderId" name="itemBid.bidder.bidderId"/>
                                             	<input type="hidden" class="tahdrDetailId"/>
                                             	<div class="form-group">                                                
	                                                <div class="col-sm-4 top35">
	                                                	<label>Item Code :</label>
	                                                	<span class="detspan"id="materialCode" ></span>
	                                                </div> 
	                                           		<div class="col-sm-4 top35">
	                                                	<label> Item Description :</label>
	                                                    <span class="detspan"id="ItemDescription" ></span>
	                                                </div>                                              
                                                 	<div class="col-sm-4 top35">
                                                    	<label>Total Quantity</label>
                                                        <span class="detspan"id="totalQuantity" ></span>
                                                    </div> 
                                                    <div class="clearfix"></div>
                                                   	<div class="col-sm-4 top35">
                                                        <label> L1 Price</label>
                                                        <span class="detspan"id="l1Price" ></span>
                                                    </div>
                                                    <div class="col-sm-4 top35">
                                                        <label> L1 Offered Quantity</label>
                                                        <span class="detspan"id="l1Quantity" ></span>
                                                    </div>
                                                    <div class="col-sm-4 top35">
                                                        <label> Remaining Quantity</label>
                                                        <span class="detspan"id="remainingQuantity" ></span>
                                                    </div>
                                                    <div class="mobclearfix"></div>
                                                   	<div class="col-sm-4 top35">
                                                        <div class="styled-input">
	                                                        <select id="isMatched" name="isMatched" class="dropDown">
	                                                            <option value='' selected></option>
		                                                        <option value='Y'>Yes</option>
		                                                        <option value='N'>No</option>       
	                                                        </select>                                                 
	                                                        <label>Agreed to match lowest acceptable</label>
	                                                        <span></span>
                                                    	</div>
                                                    </div>
                                                    <div class="mobclearfix"></div>
                                                    <div class="col-sm-4 top35">
                                                        <label>Matched Quantity</label>
                                                        <!-- <input type="hidden" id="matchedQuantityVal" name="priceBid.offeredQuantity" required="" /> -->
                                                        <span class="detspan"id="matchedQuantity" ></span>
                                                    </div>
                                                    <div class="col-sm-4 top35">
                                                        <label>Matched Price</label>
                                                        <!-- <input type="hidden" id="matchedPriceVal" name="priceBid.exGroupPriceRate" required="" /> -->
                                                        <span class="detspan"id="matchedPrice" ></span>
                                                    </div>
                                                    <div class="form-group col-sm-12">
                                                     <br><br>
												   <label>CONFIRMATION FOR ACCEPTING ORDER BY MATCHING RATES WITH LOWEST ACCEPTABLE TENDERER. 
													  APPLICABLE FOR INDUSTRIAL UNITS FROM MAHARASHTRA ONLY.</label>
													<p>Marketing Assistance and Purchase Preference to the units from Maharashtra (refer Clause XIX of Instructions to 
														Tenderers):-</p>
													<p class="col-sm-11">5.(a)In case your unit is located in Maharashtra and the lowest acceptable rate
														received against the tender is from the unit outside Maharashtra, please confirm          
														whether you are agreeable to accept order at that lowest acceptable rate                  
														limited to 50% (fifty percent) of our requirement.</p>
														<label class="col-sm-1">
															<input type="checkbox"  value="Y" id="clauseAId" name="clauseA" >
														</label>
													<p>APPLICABLE FOR ALL TENDERERS INCLUDING THOSE ELIGIBLE UNDER THE 
														ABOVE CLAUSES:</p>
													<p class="col-sm-11">5.(b) Please confirm whether you are agreeable to accept order at the lowest              
														acceptable rate received against the tender                                               
														[Industrial units from Maharashtra can give option under 5(b) above for balance
														quantity]</p>
														<label class="col-sm-1">
															<input type="checkbox" value="Y" id="clauseBId" name="clauseB">
														</label>
													</div>
                                                                                
                                               </div>
                                             	
                                                <div class="col-sm-12  text-center">
                                                    <button class="btn btn-default" id="saveAnnexureC1Bid">Save</button>
                                                    <!-- <button class="btn btn-default">Next</button>
                                                    <button class="btn btn-default">Goto Main Page</button> -->
                                                </div>
                                             </sf:form>
                                            </div>
                                             
                                            <div class="clearfix"></div>
                                        </div>
                                        <div>
                                            <div class="form-group">
                                                <div class="col-sm-12">
                                                    <h4><b>Annexture C-1 Confirmation</b></h4>
                                                    <hr>
                                                </div>
                                            </div> 
                                           
							                       
                                        <div class="form-group col-sm-12"> 
                                       
													<div class="form-group"> 
	                                                <sf:form id="gennerateAnnexureC1Doc">
							                        </sf:form>
	                                                    <div class="col-sm-12">
	                                                    	<button onclick="downloadAnnexureC1Pdf()" class="btn btn-default itemConfirmC1">Download Annexure C1 PDF</button>
	                                                    </div> 
                                                   </div>
                                                   <div class="form-group">
                                                       <sf:form id="annexureC1Form" modelAttribute="priceBid" method="POST" class="itemConfirmC1">
                                             	       <input type="hidden" id="priceBidId" name="priceBidId"/>
                                             	       <input type="hidden" id="itemBidId" name="itemBid.itemBidId"/>
                                             	       <!-- <input type="hidden" id="bidderId" name="itemBid.bidder.bidderId"/> -->
			                                            <div class="col-sm-4">
			                                             <label>Digital Sign Annexure C1 Bid:</label>
			                                                  <div class="input-group" >               
			                                                          <input type="file"  id="annexurec1docFileResponseUploadId" data-id="annexurec1FileResponseId" data-name="comdocFileResponseName" data-anchor="a_annexurec1FileResponse" class="form-control uploadFile requiredFile">
			                                                          <input type="hidden" id="annexurec1FileResponseId" name="digiSignedDoc.attachmentId" class="form-control" /> 
															                <span class="input-group-btn">
															               
															                    
															                     <button  id="deleteAnnexurec1AttachmentId" class="btn btn-default annexurec1FileResponseId"  onclick="return submitWithThreeParam('deleteAttachmentById','PriceBid','digiSignedDoc','annexurec1FileResponseId','annexurec1ConfirmDeviationfileDeleteResp');"><i class="fa fa-times"></i></button>
															                </span>
			           	                                      </div>
           	                                       		<a class="col-sm-12 filelabel" data-url="<%=request.getContextPath()%>/download" id="a_annexurec1FileResponse"></a>
           	                                      </div>
           	                                     
           	                                     <div class="form-group">                                             
	                                                <div class="col-sm-12 text-center">
	                                                   <button class="btn btn-default itemConfirmC1 save" id="" onclick="return submitIt('annexureC1Form','confirmItemAnnexureC1','confirmAnnexureC1Resp');">Save</button>
	                                                </div>                                     
                                       			 </div>
                                       			  </sf:form>
           	                                     </div>
													</div>
                                        
                                        </div>
                                        </div>
                                        
                                        </div>
                                        <!--fields of field group 2  -->
                                        
                                        <!--fields of field group 3  -->
                                        <div>
                                        <div class="detailscont">
  							 				
    									</div>
                                            <div class="form-group">
                                                <div class="col-sm-12">
                                                    <h4><b>Annexture C-1 Confirmation</b></h4>
                                                    <hr>
                                                </div>
                                            </div>
                                                                                        
                                             
                                               <%-- <div class="form-group"> 
	                                                <sf:form id="gennerateAnnexureC1Doc">
							                        </sf:form>
                                                    <div class="col-sm-12">
                                                    	<button onclick="downloadAnnexureC1Pdf()" class="btn btn-default">Download Annexure C1 PDF</button>
                                                    </div> 
                                               </div> --%>
                                               <!-- <div class="form-group">
                                                    <div class="col-sm-6">
                                                     <label>Digital Sign Annexure C1 bid</label>
                                                     </div>
                                               </div> -->
                                               <!-- <div class="form-group">
                                                       <sf:form id="annexureC1Form" modelAttribute="priceBid">
                                             	       <input type="hidden" id="priceBidId" name="priceBidId"/>
                                             	       <input type="hidden" id="itemBid" name="itemBid.itemBidId"/>
                                             	       <input type="hidden" id="bidderId" name="itemBid.bidder.bidderId"/>
			                                                  <div class="col-sm-6">
			                                                  <div class="input-group" >               
			                                                          <input type="file"  id="annexurec1docFileResponseUploadId" data-id="annexurec1FileResponseId" data-name="comdocFileResponseName" data-anchor="a_annexurec1FileResponse" class="form-control uploadFile">
			                                                          <input type="hidden" id="annexurec1FileResponseId" name="scrutinyFile.attachmentId" class="form-control" /> 
															                <span class="input-group-btn">
															               
															                    <button  id="deleteComDocAttachmentId" class="btn btn-default comdocFileResponseId" onclick="return submitWithParam('deleteAttachment','comdocFileResponseId','fileResponseAttachmentDeleteResp');" disabled="disabled"><i class="fa fa-times"></i></button>
															                     <button  id="deleteAnnexurec1AttachmentId" class="btn btn-default annexurec1FileResponseId"  onclick="return submitWithThreeParam('deleteAttachmentById','ItemScrutiny','scrutinyFile','comdocFileResponseId','annexurec1ConfirmDeviationfileDeleteResp');"><i class="fa fa-times"></i></button>
															                </span>
			           	                                      </div>
           	                                       		
           	                                      </div>
           	                                      </sf:form>
           	                                     </div> -->
           	                                            <div class="col-sm-4">
                                                          <a class="col-sm-12 filelabel" data-url="<%=request.getContextPath()%>/download" id="a_annexurec1FileResponse"></a>
                                                        </div> 
                                               <div class="form-group col-sm-12"> 
                                             <!--   <p> <b> ANNEXURE 'C-1" </b></p>
											   <p>CONFIRMATION FOR ACCEPTING ORDER BY MATCHING RATES WITH LOWEST ACCEPTABLE TENDERER. 
												  APPLICABLE FOR INDUSTRIAL UNITS FROM MAHARASHTRA ONLY.</p>
												<p>Marketing Assistance and Purchase Preference to the units from Maharashtra (refer Clause XIX of Instructions to 
													Tenderers):-</p>
												<p>5.(a)In case your unit is located in Maharashtra and the lowest acceptable rate
													received against the tender is from the unit outside Maharashtra, please confirm          
													whether you are agreeable to accept order at that lowest acceptable rate                  
													limited to 50% (fifty percent) of our requirement.</p>
												<p>APPLICABLE FOR ALL TENDERERS INCLUDING THOSE ELIGIBLE UNDER THE 
													ABOVE CLAUSES:</p>
												<p>5.(b) Please confirm whether you are agreeable to accept order at the lowest              
													acceptable rate received against the tender                                               
													[Industrial units from Maharashtra can give option under 5(b) above for balance
													quantity]</p> -->
												<p><b>Note:-</b></p>
												<p>1.If the tenderer gives the above confirmation for the quantity less than as indicated in Clause VIII (iv) of the
													Instructions to the Tenderers, then the above confirmation shall not be acceptable.</p>
												<p>2.Tenderers may confirm matching for one or more items originally tendered.</p>
												<p>3.Any withdrawal of confirmation for order by matching rate within validity of offer will render the entire offer 
												    invalid and shall be summarily rejected and Earnest Money Deposit shall stand forfeited.</p>
												<p>4. A tenderer will not be entitled to the benefit of offers by matching rates and will not be considered for orders 
													if his original offer is rejected on the ground of ambiguity or because of not accepting/non compliance of the 
													terms & conditions of the tender.</p>
												<p>5.In the above confirmation, if the tenderer indicates any rate, then the above confirmation given by the 
													tenderer will not be considered as valid.</p>
                                               
                                               <label class="checkbox-inline">
                                               	<input type="checkbox" checked> <b>By clicking SAVE,you will agree to above Terms and Condition. </b>
                                               </label>
                                                <div class="col-sm-12">
                                                
                                                    <!-- <button class="btn btn-default" id="">Save</button>
                                                    <button class="btn btn-default">Go To Previous Page</button>
                                                    <button class="btn btn-default">Go To Main Page</button>    -->                                                 
                                                </div>
                                              </div>   
                                              <div class="form-group">
                                             
                                                <div class="col-sm-12 text-center">
                                                   <button class="btn btn-default" id="" onclick="confirmAnnexureC1()">Save</button>
                                                </div>                                     
                                        </div>
                                        <!--fields of field group 3  -->
                                        <!-- <!--fields of field group 4  -->
                                        <!-- <div>
                                        <div class="detailscont">
  							 				<div class="col-md-12">
                                				<label class="col-xs-6"> <h4>NovelERP Solutions</h4></label>
                            					<label class="col-xs-6 ">12765456</label>
                            				</div>	
                            				<div class="col-md-12">
                           						<label class="col-xs-6">Manufacturer</label>
                            					<label class="col-xs-6 ">EOCPK88Pk</label>
                            				</div>
    									</div>
                                            <div class="form-group">
                                                <div class="col-sm-12">
                                                    <h4><b>Bid Submission</b></h4>
                                                    <hr>
                                                </div>
                                            </div>                                            
                                            <div class="form-group">
                                                    <div class="col-sm-12">
                                                    	<button class="btn btn-default">Continue</button>
                                                    	<button class="btn btn-default">Main Page</button>
                                                    </div>                                     
                                            </div>                                                                                       
                                        </div>
                                        fields of field group 4 
                                        fields of field group 5 
                                        <div>
                                        <div class="detailscont">
  							 				<div class="col-md-12">
                                				<label class="col-xs-6"> <h4>NovelERP Solutions</h4></label>
                            					<label class="col-xs-6 ">12765456</label>
                            				</div>	
                            				<div class="col-md-12">
                           						<label class="col-xs-6">Manufacturer</label>
                            					<label class="col-xs-6 ">EOCPK88Pk</label>
                            				</div>
    									</div>
                                            <div class="form-group">
                                                <div class="col-sm-12">
                                                    <h4><b>Digital Signing Of Bid</b></h4>
                                                    <hr>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <div class="col-sm-3">
                                                    <button class="btn btn-default">Download Annexture C-1</button>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <div class="col-sm-4 btnmrg2">
                                                            <input type="file" id="balanceSheetCopies_file_0" name="balanceSheetCopies[0].attachment" class="form-control">
                                                            <span></span>
                                                        </div>
                                            </div>
                                            <div class="form-group">
                                                    <div class="col-sm-12">
                                                    	<button class="btn btn-default">Save</button>
                                                    	<button class="btn btn-default">Go To Previous Page</button>
                                                    </div>                                     
                                            </div>                                                                                       
                                        </div>
                                        fields of field group 5 
                                         fields of field group 6 
                                        <div>
                                        <div class="detailscont">
  							 				<div class="col-md-12">
                                				<label class="col-xs-6"> <h4>NovelERP Solutions</h4></label>
                            					<label class="col-xs-6 ">12765456</label>
                            				</div>	
                            				<div class="col-md-12">
                           						<label class="col-xs-6">Manufacturer</label>
                            					<label class="col-xs-6 ">EOCPK88Pk</label>
                            				</div>
    									</div>
                                            <div class="form-group">
                                                <div class="col-sm-12">
                                                    <h4><b>Tender Bid opening</b></h4>
                                                    <hr>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <div class="col-sm-4">
                                                    <div class="form-group">
                                                        <label>Tender Bid Opening on Date<span class="red">*</span></label>
                                                        <div class="input-group date" data-provide="datepicker">
                                                            <input type="text" class="form-control" id="purchaseFromDate" name="purchaseFromDate">name="lastSubmissionDate"
                                                            <div class="input-group-addon">
                                                                <span class="glyphicon glyphicon-th"></span>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="col-sm-8" style="margin-top:20px;">
                                                	<button class="btn btn-default">Search</button>
                                                	<button class="btn btn-default">Search Tender(s) By Tender Code</button>
                                               </div>
                                               <div class="col-sm-12">
                                               		<table class="TenderBidOpeningTable table table-striped table-bordered" width="100%">
                                            <thead>
                                                <tr>
                                                    <th>Tender Code</th>
                                                    <th>Bid Name</th>
                                                    <th>Time</th>
                                                    <th>Action</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <tr>
                                                    <td><a class="tenderdetailsTab" data-toggle="tab" href="#TenderDetails">TestTender1103</a></td>
                                                    <td>Browser Requirements </td>
                                                    <td>21st October 2017</td>
                                                    <td><button class="btn btn-default">Submit</button></td>
                                                </tr>
                                                <tr>
                                                    <td><a class="tenderdetailsTab" data-toggle="tab" href="#TenderDetails">TestTender1103</a></td>
                                                    <td>Browser Requirements </td>
                                                    <td>21st October 2017</td>
                                                    <td><button class="btn btn-default">Submit</button></td>
                                                </tr>
                                                <tr>
                                                    <td><a class="tenderdetailsTab" data-toggle="tab" href="#TenderDetails">TestTender1103</a></td>
                                                    <td>Browser Requirements </td>
                                                    <td>21st October 2017</td>
                                                    <td><button class="btn btn-default">Submit</button></td>
                                                </tr>
                                                <tr>
                                                    <td><a class="tenderdetailsTab" data-toggle="tab" href="#TenderDetails">TestTender1103</a></td>
                                                    <td>Browser Requirements </td>
                                                    <td>21st October 2017</td>
                                                    <td><button class="btn btn-default">Submit</button></td>
                                                </tr>
                                            </tbody>
                                       </table> 
                                               </div>
                                            </div>
                                            
                                            <div class="form-group">
                                                    <div class="col-sm-12">
                                                    	<button class="btn btn-default">Save</button>
                                                    	<button class="btn btn-default">Cancel</button>
                                                    </div>                                     
                                            </div>                                                                                       
                                        </div>
                                        fields of field group 6 
                                        
                                        fields of field group 7 
                                        <div>
                                        <div class="detailscont">
  							 				<div class="col-md-12">
                                				<label class="col-xs-6"> <h4>NovelERP Solutions</h4></label>
                            					<label class="col-xs-6 ">12765456</label>
                            				</div>	
                            				<div class="col-md-12">
                           						<label class="col-xs-6">Manufacturer</label>
                            					<label class="col-xs-6 ">EOCPK88Pk</label>
                            				</div>
    									</div>
                                            <div class="form-group">
                                                <div class="col-sm-12">
                                                    <h4><b>Tender Report</b></h4>
                                                    <hr>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                            <div class="col-sm-4">
                                                    <div class="styled-input">
                                                        <select id="companyType" name="companyType" required="">
                                                        </select>
                                                        <label>Report name<span class="red">*</span></label>
                                                        <span></span>
                                                    </div>
                                                 </div>
                                                <div class="col-sm-3">
                                                    <div class="form-group">
                                                        <label>From<span class="red">*</span></label>
                                                        <div class="input-group date" data-provide="datepicker">
                                                            <input type="text" class="form-control" id="purchaseFromDate" name="purchaseFromDate">name="lastSubmissionDate"
                                                            <div class="input-group-addon">
                                                                <span class="glyphicon glyphicon-th"></span>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="col-sm-3">
                                                    <div class="form-group">
                                                        <label>To<span class="red">*</span></label>
                                                        <div class="input-group date" data-provide="datepicker">
                                                            <input type="text" class="form-control" id="purchaseFromDate" name="purchaseFromDate">name="lastSubmissionDate"
                                                            <div class="input-group-addon">
                                                                <span class="glyphicon glyphicon-th"></span>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="col-sm-2" style="margin-top:20px;">
                                                	<button class="btn btn-default">Search</button>
                                               </div>                                               
                                            </div>  
                                            <div class="form-group">
                                            <div class="col-sm-4">
                                                    <div class="styled-input">
                                                        <select id="companyType" name="companyType" required="">
                                                        </select>
                                                        <label>Tender Code<span class="red">*</span></label>
                                                        <span></span>
                                                    </div>
                                                 </div>                                            
                                                <div class="col-sm-8" style="margin-top:20px;">
                                                	<button class="btn btn-default">Generate Report</button>
                                                	<button class="btn btn-default">Download PDF</button>
                                               </div>                                               
                                            </div>                                                                                  
                                        </div>
                                        fields of field group 7  -->
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
<script>
$(document).ready(function(){
	 var lengthMenu;

	    if ($(window).width() < 480) {
	        $('.mobileNav').show();
	        $.fn.DataTable.ext.pager.numbers_length = 4;       
	        lengthMenu = [ 1, 5, 7, 10, ],
	        [ 1, 5, 7, 10, ]
	    } else {        
	        lengthMenu = [ 5, 10, ],
	        [ 5, 10, ]
	    }
	    $('table').each(function(){		
			var text = []
			$(this).find('thead tr th').each(function(){
				text.push($(this).text())

				for (var i = 0; i < text.length; i++) {
					$(this).parents('table').find('tbody tr td:nth-of-type(' + (i + 1) +')').attr('data-th', text[i])
				}	
			});		
		});	
		$('.mytendertable').DataTable({
			"lengthMenu":lengthMenu
		});
		$('.C1Confirmationtable').DataTable({
			"lengthMenu":lengthMenu
		});
		$('.TenderBidOpeningTable').DataTable({
			"lengthMenu":lengthMenu
		});
});
</script>