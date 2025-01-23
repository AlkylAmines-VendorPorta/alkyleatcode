<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@taglib uri = "http://www.springframework.org/tags/form" prefix = "sf"%>

<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/${appMode}/tilescommon/css/fioristyle.css?appVer=${appVer}"/>
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/${appMode}/tilescommon/css//borderTab.css?appVer=${appVer}"/>
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/${appMode}/tilescommon/css/mobile.css?appVer=${appVer}"/>
<style>
#otpId {
  padding-left: 15px;
  letter-spacing: 42px;
  border: 0;
  background-image: linear-gradient(to left, black 70%, rgba(255, 255, 255, 0) 0%);
  background-position: bottom;
  background-size: 50px 1px;
  background-repeat: repeat-x;
  background-position-x: 35px;
  width: 300px;
  margin: 0px auto;
  box-shadow: none;
}
.tinput{
width: 100% !important;
}
</style>
            <script>
                $(document).ready(function() {
                    $("#tabstrip").kendoTabStrip();
                    $('#leftPaneData').paginathing({perPage: 6});
                });
            </script>
            
        <div class="full-container">
        	<input type="hidden" id="myTenderUrl" class="myTenderUrl" value="${myTenderUrl}" />
            <!-- left-side start-->
            <div class="clearfix"></div>
            <div id="mobile_first_container" class="left-side col-md-3 no-marg">
                <div class="detailsheader">
        	<div class="col-sm-3 text-center brdrgt"><label>Auction Report (0)</label></div>
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
				<button class="btn btn-default" type="button">
					<span class="glyphicon glyphicon-refresh"></span>
				</button>
                    </span>
                </div>
                <div class="btn-group btnmrg" data-toggle="buttons">
							    <label class="btn btn-primary toggleTab" openTab="tenderTab">
									<input type="radio" name="tenderTypeCodeToggle" value="FA" id="option3">
									<span class="glyphicon glyphicon-ok"></span> Forward 
                                </label>
                                <label class="btn btn-primary active  toggleTab" openTab="tenderTab">
                                     <input type="radio" name="tenderTypeCodeToggle" checked="checked" value="PT" id="option3">
                                      <span class="glyphicon glyphicon-ok"></span> Reverse
                                </label>
                            </div>
                <ul id="leftPaneData" class="nav nav-tabs tabs-left leftPaneData">
                   
                </ul>
                
                <div class="clearfix"></div>
            </div>
            <!-- left-side end-->

            <!-- right-side start-->
            <div id="mobile_second_container" class="right-side col-md-9 no-marg">
            <div class="detailsheader toptabbrd">
        	<div class="col-sm-9 text-center"><label>Auction Details</label></div>
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
                                            <li class="k-state-active tenderTab"><div class="tabcircle"><i class="fa fa-info-gavel" aria-hidden="true"></i></div>Auction</li>     
											<li class="toggleTab tenderMaterialTab" openTab="materialTab" onclick="generateOtp(event)"><div class="tabcircle"><i class="fa fa-line-chart" aria-hidden="true"></i></div>Live Bid</li>
										</ul>
										<div>
										 <div class="detailscont">
  							 				
    									</div>
    									   <div class="form-group">
                                                <div class="col-sm-12">
                                                    <h4><b>Auction Details</b></h4>
                                                    <hr>
                                                </div>
                                            </div>
    									      <div class="form-group">
    									<form id="tahdrForm" class="readonly">
    									<div class="form-group">
    									 <input type="text" style="display: none;" id="tahdrId" name="" >
                                                <div class="col-sm-4">
                                                 
                                                        <label>Auction No:</label>
                                                        <span class="detspan" id="tenderNo"></span>
                                                </div>
                                                <div class="col-sm-4">
                                                 <label>Auction Version:</label>
                                                        <span class="detspan" id="tenderVersion" ></span>
                                                   
                                                </div>
                                                <div class="col-sm-4">
                                                 <label>Description:</label>
                                                        <span class="detspan" id="description" ></span>
                                                  
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <div class="col-sm-4">
                                                 <label>EMD Fees(in Rs):</label>
                                                        <span class="detspan" id="emdFee" ></span>
                                                   
                                                </div>                                                
                                            </div>
                                            <div class="form-group">
                                                <div class="col-sm-4">
                                                 <label>Overall Rank :</label>
                                                        <span class="detspan" id="overAllRank" ></span>
                                                   
                                                </div>                                                
                                            </div>
                                            </form>
										</div>
										</div>
                                        <!--fields of field group 1  -->
                                        <div>
                                         <div class="detailscont">
    									 </div>
                                        <div class="otpcontent"><br><br> <br>
                                        <div class="col-sm-6 col-sm-offset-3">
                                        <div class="panel panel-primary">
									      <div class="panel-heading text-center">Enter Your OTP</div>
									      <div class="panel-body" style="padding-top: 40px; padding-bottom: 40px;">
									      <div class="col-sm-12">
									             <label>We have sent OTP to your registered Number,check it.</label>
									          <br>
									          <sf:form id="otpFormId" modelAttribute="otp" method="POST">
									                     <input type="text" autocomplete="off" oncopy="return false" onpaste="return false" class="form-control onlyNumber" name="otp" id="otpId" maxlength="6" />  
									                     <input type="hidden" class="form-control" name="auctionId" id="tenderId"/>      
									          </sf:form>
									      </div>
												<div class="col-sm-12 top20">
												   <button class="btn btn-default save" onclick="checkOtp(event)">Submit</button>
												</div>
												<div class="col-sm-12 top20">
												   <!--  <div>
												       <button class="btn btn-default save" id="sendOtpBtnId" onclick="generateOtp()">Generate Otp</button>
												       <i class="" id="sendSpinId" style="font-size:15px"></i>
												    </div> -->
												    
												    <div>
												     <button class="btn btn-default save" id="resendOtpBtnId" onclick="resendOtp(event)" style="display: none;">Re-Send OTP</button>
												     <i class="" id="resendSpinId" style="font-size:15px"></i>
												     </div>
												  </div>
												 
										 </div>
                                        </div>
                                        </div>
                                        </div>
                                        <div class="livebidclass" style="display:none;">
                                      <!--  <div class="detailscont">
  							 				
    									</div> -->
    									
                                            <div>
                                            <div class="timerbid">
                                            <label class="bluetext">Auto Refresh in</label>
                                            <label id="autoRefresh" class="greentext">0s</label>
                                            </div>
						               	<ul class="nav nav-pills">
											<li id="" class="active LiveBidTab" data-placement="right" data-toggle="tooltip" title="Live Bid Details" >
											<a href="#tab_h" data-toggle="pill"><i class="fa fa-gavel"></i></a><label>Live Bid</label></li>
											
										  	<li id="" class="Bidhistoryli" data-toggle="tooltip" data-placement="right" title="Bid history" >
										  	<!-- <a href="#tab_i" data-toggle="pill" id="bidHistoryIconId" onclick="return submitWithTwoParam('getBidderListByTahdrId','tahdrId','itemDetailForm #tahdrMaterialId','bidderListResp');"><i class="fa fa-file-text"></i></a><label>Bid History</label></li> -->
										  	<a href="#tab_i" data-toggle="pill" onclick="myBidHistory()"><i class="fa fa-file-text"></i></a><label>Self Bid History</label></li>
										<!--  <li class="toggleTab" openTab="LiveBidTab" style="display: none ;" id="autoRefreshTabId"></li> --> 
										</ul>
				<div class="tab-content col-md-12">
					<div class="tab-pane active" id="tab_h">
						                  <form id="itemDetailForm">
                                            <input style="display: none;" id="tahdrMaterialId">
                                               
                                              </form> 
                                           
                                <div class="clearfix"></div>
                                 
                               
                                <div class="col-sm-12 bottom20">
                                <div class="card">
                                <h4 class="col-sm-12">Auction Details</h4>
                                <form id="tenderDetailsForm" class="readonly">
                                	           <div class="form-group">
                                                <div class="col-sm-3">
                                                   <label>Auction No:</label><span class="detspan" id="tahdrNo"></span>
                                                </div>
                                                <div class="col-sm-3">
                                                 <label>Total Unit Quatity :</label><span class="detspan" id="qtyId">0</span>
                                                </div>
                                                <div class="col-sm-3">
                                                 <label id="decOrIncByLblId">:</label><span class="detspan" id="decOrIncById">0 </span>
                                                </div>
                                                <div class="col-sm-3">
                                                 <label>No of Bidders: </label><span class="detspan" id="noOfBidderId">0</span>
                                                </div>
                                                </div>
                                            <div class="form-group">
                                                <div class="col-sm-3">
                                                 <label>Auction Department: </label><span class="detspan" id="department"></span>
                                                </div>
                                                <div class="col-sm-3">
                                                 <label>My Pseudo Name: </label><span class="detspan" id="psuedoName"> </span>
                                                </div>
                                                 <div class="col-sm-3">
                                                 <label>UOM: </label><span class="detspan" id="uom"> </span>
                                                </div>
                                                <div class="col-sm-3">
                                                 <label>Auction Location: </label><span class="detspan" id="location"></span>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                 <div class="col-sm-6">
                                                 <label>Bid Start Date:</label><span class="detspan" id="bidStartDateId"> </span>
                                                </div>
                                                 <div class="col-sm-6">
                                                 <label>Bid End Date:</label><span class="detspan" id="bidEndDateId"> </span>
                                                </div>
                                            </div>
                                     </form>
                                   </div>
                                </div>
                                <div class="col-sm-12 bottom20">
                                <div class="card">
                                <h4 class="col-sm-12">Bider Details</h4>
                                <sf:form id="bidFormId" modelAttribute="newBidData" method="POST">
                                <input type="text" style="display: none ;" id="priceBidId" name="priceBidId">
                                <input type="text" style="display: none ;" id="tahdrMaterialId" name="itemBid.tahdrMaterial.tahdrMaterialId">
                                <input type="text" style="display: none ;" id="tahdrId" name="itemBid.bidder.tahdr.tahdrId">
                                 <input type="text" style="display: none ;" id="fddRateWithGST" name="fddRateWithGST">
                                  <input type="text" style="display: none ;" id="preTotalFDDAmountWithGST" name="previousFddAmountWithGST">
                                  
                                <div class="form-group">
                                	<label class="col-sm-3">Per Unit Ex Works Price Rate</label>
                                	<div class="col-sm-4"><input type="text" id="inputExGroupPriceRateId" onkeyup="calculate()" class="form-control tinput onlyNumber requiredField" value="" name="exGroupPriceRate"></div>
                                	<div class="col-sm-4">
                                		<button type="button" id="newBidSubmitBtnId" onclick="newBidSubmit(event)" class="btn btn-default bluebutton bidBtnId">Bid</button>
                                		<button type="button" onclick="resetToPreviousBid(event)" class="btn btn-default bluebutton resetBidBtnId">Refresh</button>
                                		<button type="button"  id="bidHistoryIconId" onclick="return submitWithTwoParam('getBidderListByTahdrId','tahdrId','itemDetailForm #tahdrMaterialId','bidderListResp');" class="btn btn-default bluebutton">Bid History</button>
                                	</div>
                                </div>
                                </sf:form>
                                <form id="bidderDetailForm">
                                <div  class="readonly"> 
                                	<div class="form-group">
                                                <div class="col-sm-3">
                                                <label>Current Rank:</label>
                                                <span class="detspan" id="rank">NA</span>
                                                </div>
                                                <div class="col-sm-3">
                                                 <label>Quantity Proposed: </label><span class="detspan" id="offeredQuantity">0</span>
                                                </div>
                                                <div class="col-sm-3">
	                                                 <label>Per Unit Ex-Works Price(in Rs) :</label><span class="detspan" id="exGroupPriceRate"> </span>
                                                </div>
                                                <div class="col-sm-3">
                                                 <label>Total Ex-Works Price(in Rs) :</label><span class="detspan" id="totalExGroupPrice"> </span>
                                                </div>
                                            </div>
                                           <div class="form-group">
                                                <div class="col-sm-3">
                                                 <label>Per Unit TIC(in Rs) :</label><span class="detspan" id="ticRate"> </span>
                                                </div>
                                                <div class="col-sm-3">
                                                 <label>Total TIC(in Rs) :</label><span class="detspan" id="totalTic"> </span>
                                                </div>
                                                <div class="col-sm-3">
                                                 <label>Per Unit Freight charges(in Rs) : </label><span class="detspan" id="freightChargeRate"></span>
                                                </div>
                                                <div class="col-sm-3">
                                                 <label>Total Freight charges(in Rs) : </label><span class="detspan" id="totalFreightCharges"></span>
                                                </div>
                                            </div> 
											
											<div class="form-group">
                                                <div class="col-sm-3">
                                                 <label>Per Unit FDD Price Without GST(in Rs) : </label><span class="detspan" id="fddRate"></span>
                                                </div>
                                                <div class="col-sm-3">
                                                 <label>Total FDD Price Without GST(in Rs) : </label><span class="detspan" id="fddAmount"></span>
                                                </div>
                                            
                                                </div> 
											
											<div class="form-group">
                                                <div class="col-sm-3">
                                                 <label>IGST(in %) <span id="igstLable"> </span>: </label><span class="detspan" id="igst"></span>
                                                </div>
                                                <div class="col-sm-3">
                                                 <label>CGST(in %)  <span id="cgstLable"> </span>:</label><span class="detspan" id="cgst"> </span>
                                                </div>
                                                <div class="col-sm-3">
                                                 <label>SGST(in %)  <span id="sgstLable"> </span>: </label> <span class="detspan" id="sgst"></span>
                                                </div>
                                                <div class="col-sm-3">
                                                 <label>GST(in %) :</label><span class="detspan" id="taxRate"> </span>
                                                </div>
                                            </div> 
											
											 <div class="form-group">
                                                <div class="col-sm-3">
                                                 <label>Per Unit GST(in Rs) :</label><span class="detspan" id="taxAmount"> </span>
                                                </div>
                                                <div class="col-sm-3">
                                                 <label>Total GST(in Rs) : </label><span class="detspan" id="totalTax"></span>
                                                </div>
                                                <div class="col-sm-3">
                                                 <label>Per Unit FDD With GST(in Rs) :</label><span class="detspan" id="fddRateGST"> </span>
                                                </div>
                                                <div class="col-sm-3">
                                                 <label>Total FDD With GST(in Rs) : </label><span class="detspan" id="fddAmountGST"></span>
                                                </div>
                                            </div> 
                                            </div>
                                     </form>
                                            </div>
                                              
                                            
                                </div>
                                <div class="col-sm-12">
                                             	<!--  <table class="selfbidHistory tableResponsive table table-striped table-bordered" style="width:100% !important; ">
									         	<thead>
									         		
                                            		<tr>
										         		
										         		<th class="col-sm-2">Offered Qty</th>
										         		<th class="col-sm-2">Date Of Bidding</th>
										         		<th class="col-sm-2">Previous Bid Amount</th>
										         		<th class="col-sm-2">Per Unit Bid Amount</th>
										         		<th class="col-sm-2">Total Amount</th>
									         		</tr>
									         		</thead>
									         		
										         		<tbody>
										         		 
										                </tbody>
									                
									         </table> -->
									          <table class="biddertable tableResponsive table table-striped table-bordered" style="width:100% !important; ">
									         	<thead>
									         		
                                            		<tr>
										         		<th class="col-sm-2">Bidder Unique Code</th>
										         		<th class="col-sm-2">Offered Qty</th>
										         		<th class="col-sm-2">Date Of Bidding</th>
										         		<th class="col-sm-2">Bid Amount/Unit</th>
										         		<th class="col-sm-2">Bid Rate/Unit</th>
										         		<th class="col-sm-2">Total Amount</th>
									         		</tr>
									         		</thead>
									         		
										         		<tbody>
										         		 
										                </tbody>
									                
									         </table>
                                             </div>
					</div>
					<div class="tab-pane" id="tab_i">
						<div class="col-sm-12">
                                             	<!--  <table class="biddertable tableResponsive table table-striped table-bordered" style="width:100% !important; ">
									         	<thead>
									         		
                                            		<tr>
										         		<th class="col-sm-2">Bidder Name</th>
										         		<th class="col-sm-2">Factory Name</th>
										         		<th class="col-sm-2">Offered Qty</th>
										         		<th class="col-sm-2">Date Of Bidding</th>
										         		<th class="col-sm-2">Bid Amount</th>
										         		<th class="col-sm-2">Total Amount</th>
									         		</tr>
									         		</thead>
									         		
										         		<tbody>
										         		 
										                </tbody>
									                
									         </table> -->
									          <table class="selfbidHistory tableResponsive table table-striped table-bordered" style="width:100% !important; ">
									         	<thead>
									         		
                                            		<tr>
										         		
										         		<th class="col-sm-2">Offered Qty</th>
										         		<th class="col-sm-2">Date Of Bidding</th>
										         		<th class="col-sm-2">Previous Bid Amount</th>
										         		<th class="col-sm-2">Per Unit Bid Amount</th>
										         		<th class="col-sm-2">Total Amount</th>
									         		</tr>
									         		</thead>
									         		
										         		<tbody>
										         		 
										                </tbody>
									                
									         </table>
                                             </div>
					</div>
				</div>
				</div>
                           
                                <div class="clearfix"></div>
                             <!--    <div class="form-group">
                                                <div class="col-sm-12">
                                                	<button type="" class="btn btn-default bluebutton ShowDetails">Show Details</button>
                                                	<button type="" class="btn btn-default bluebutton">Bid History</button>
                                                	
                                                </div>
                                             </div>  -->
                                             
                                <!-- Master tab end-->
							</div>
                            </div>
                                        
                            
                        </div>
                        <!-- right-side end-->
                    </div>
                </div>
            </div>
            </div>
            </div>
            </div>
            </div>
  <%--           <div class="modal fade" id="otpmodal" role="dialog">
    <div class="modal-dialog modal-md">
      <div class="modal-content">
        <div class="modal-header">
          <!-- <button type="button" class="close" data-dismiss="modal">&times;</button> -->
          
          <h4 class="modal-title">Enter Your OTP</h4>
        </div>
        <div class="modal-body">
         <div class="col-sm-8">
             <label>We have sent OTP to your registered no,check it.</label>
          <br>
          <sf:form id="otpFormId" modelAttribute="otp" method="POST">
                    <input type="text" class="form-control onlyNumber" name="otp" id="otpId"/>
         	        <input type="hidden" class="form-control" name="auctionId" id="tenderId"/>         
          </sf:form>
         </div>
         <div class="col-sm-2 top20">
         	<button class="btn btn-default save" onclick="checkOtp()">Submit</button>
         </div>
         <br/><br/>
         <div class="col-sm-12 top20">
         <button class="btn btn-default save" id="resendOtpBtnId" onclick="resendOtp()">Resend Otp</button>
          <i class="" id="resendSpinId" style="font-size:15px"></i>
         </div>
           <br/><br/>
        <div class="clearfix"></div>
        </div>
        <div class="clearfix"></div>
        <div class="modal-footer">
          <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        </div>
      </div>
    </div>
  </div> --%>
<script src="<%=request.getContextPath()%>/resources/${appMode}/tilescommon/js/timer.jquery.js?appVer=${appVer}"></script>
<script src="<%=request.getContextPath()%>/resources/${appMode}/transaction/js/liveBid.js?appVer=${appVer}"></script>
 <script src="<%=request.getContextPath()%>/resources/${appMode}/transaction/js/liveBiding.js?appVer=${appVer}"></script> 
<script src="<%=request.getContextPath()%>/resources/${appMode}/commons/js/formFields.js?appVer=${appVer}"></script>
<script src="<%=request.getContextPath()%>/resources/${appMode}/tilescommon/js/bootstrap-datetimepicker.js?appVer=${appVer}"></script> 
<script src="<%=request.getContextPath()%>/resources/${appMode}/commons/js/utility.js?appVer=${appVer}"></script> 

