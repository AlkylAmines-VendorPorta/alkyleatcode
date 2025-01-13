<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>

<link rel="stylesheet"
	href="<%=request.getContextPath()%>/resources/tilescommon/css/fioristyle.css" />
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/resources/tilescommon/css//borderTab.css" />
	<script src="<%=request.getContextPath()%>/resources/${appMode}/tilescommon/js/kendo.all.min.js?appVer=${appVer}"></script>
<style>
.tinput {
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
	<!-- left-side start-->
	<div class="clearfix"></div>
	<div id="mobile_first_container" class="left-side col-md-3 no-marg">
		<div class="detailsheader">
			<div class="col-sm-3 text-center brdrgt">
				<label>Invitation Report (0)</label>
			</div>
		</div>
		<div class="input-group">
			<div class="input-group-btn search-panel">
				<button type="button" class="btn btn-default dropdown-toggle"
					data-toggle="dropdown">
					<span id="search_concept">Filter by</span> <span class="caret"></span>
				</button>
				<ul class="dropdown-menu" role="menu" id="filter">
					<li data-documenttype="TENDER" class='active filterByTahdrCode'><a href="#">Tender</a></li>
					<li class="divider"></li>
					<li data-documenttype="AUCTION" class='filterByTahdrCode'><a href="#">Auction</a></li>
					<li class="divider"></li>
					<li data-documenttype="QUICK_AUCTION" class='filterByTahdrCode'><a href="#">Quick Auction</a></li>
					<li class="divider"></li>
					<li data-documenttype="QUICK_RFQ" class='filterByTahdrCode'><a href="#">Quick RFQ</a></li>
					<li class="divider"></li>
					<li data-documenttype="RFQ" class='filterByTahdrCode'><a href="#">RFQ</a></li>
				</ul>
			</div>
			<input type="hidden" name="search_param" value="all"
				id="search_param"> <input type="text" class="form-control"
				name="x" placeholder="Search term..."> <span
				class="input-group-btn">
				<button class="btn btn-default" type="button">
					<span class="glyphicon glyphicon-search"></span>
				</button>
				<button class="btn btn-default" type="button">
					<span class="glyphicon glyphicon-plus"></span>
				</button>
			</span>
		</div>
		<ul id="example" class="nav nav-tabs tabs-left example">

			<li><a href="#Master" data-toggle="tab">
					

			</a></li>
		</ul>
		<div class="clearfix"></div>
	</div>
	<!-- left-side end-->

	<!-- right-side start-->
	<div id="mobile_second_container" class="right-side col-md-9 no-marg">
		<div class="detailsheader toptabbrd">
			<div class="col-sm-9 text-center">
				<label>Invitation Details</label>
			</div>
		</div>
		<div class="clearfix"></div>
		<div class="tab-content">
			<!-- Master tab start-->
			<!-- <div class="col-md-12" id="masterDetails"></div> -->

			<div class="tab-pane active in" id="">
				<div class="card">
					<div class="posrelative" id="">
						<div class="demo-section k-content">
							<div id="tabstrip" class="Firsttab">
								<ul>
									<!-- tabs -->
									<li class="k-state-active Invitation"><div class="tabcircle"><i class="fa fa-envelope-o"></i></div>Invitation</li>
									<li class="SearchPartners" id="searchPartnerId"><div class="tabcircle"><i class="fa fa-search"></i></div>Search Partners</li>
									<li class="addPartnerClass" id="addPartnerTabId"><div class="tabcircle"><i class="fa fa-user-o"></i></div>Add Partner</li>
								</ul>
								<div class="InvitationTab">
										<div class="detailscont" id="masterDetails">
  							 	
    									</div>
									<div class="form-group">
										<div class="col-sm-12">
											<h4 class="col-xs-6 nomobpad">
												<b>Invitation Details</b>
											</h4>
											<div class="col-xs-6 nomobpad text-right top10">
												<button class="btn btn-default bluebutton SearchPartners"
													id="InvitePartners">Invite Partners</button>
											</div>
											<hr>
										</div>
									</div>
									<sf:form style="display:none;" id="hiddenformID"
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
										<div class="col-xs-12">
											<table id="auctionparticipatetable"
												class="display table table-striped table-bordered"
												cellspacing="0" width="100%">
												<thead>
													<tr>
														<th>Company Name</th>
														<th>Pan No</th>
														<th>Crn No</th>
														<th>invitation Date</th>

													</tr>
												</thead>
												<tbody>

												</tbody>
											</table>

										</div>
									</div>
								</div>
								<input style="display: none;" type="text"
									class="form-control Id"> <input style="display: none;"
									type="text" id="tahdrtype" class="form-control tahdrtype">
								<!--fields of field group 1  -->
								<div class="SearchPartnersTab">
									<div class="detailscont">
										
									</div>
									<!-- <div class="form-group">
                                                <div class="col-sm-12">
                                                    <h4><b>Serch Invitation</b></h4>
                                                    <div class="col-sm-6 text-right">
                                                    	<button class="btn btn-default bluebutton" id="sendnewinvitation">Send New Invitation</button>
                                                    </div>
                                                    <hr>
                                                </div>
                                            </div> -->
									<div class="clearfix"></div>
									<div class="col-sm-12 top20">
										<div class="card" style="padding-top: 20px;">
											<div class="col-sm-12 mobilefilter">
												<button class="btn btn-default bluebutton pull-right">
													<span class="glyphicon glyphicon-filter"></span>
												</button>
											</div>
											<div class="filterDiv">
												<div class="col-sm-2 top20">
													<label class="checkbox-inline"> <input
														type="checkbox" id="sellercheck" class="checkbox readonly"><b>Seller</b></label>
													<label class="checkbox-inline"> <input
														type="checkbox" id="buyercheck" class="checkbox readonly"><b>Buyer</b></label>
												</div>
												<div class="col-sm-3">
												<div class="styled-input ">
                                                        <input type="text"  id="emailId"/>
                                                        <label>Email Id</label>
                                                        <span></span>
                                                    </div>
													<%-- <div class="styled-input slate">
														<select id="statemaster">
															<option value="">Select State</option>

															<c:forEach var="regionList" items="${regionList}">
																<option value="${regionList.regionId}">${regionList.name}</option>
															</c:forEach>

														</select> <label>State<span class="red">*</span></label> <span></span>
													</div> --%>
												</div>
												<div class="col-sm-3">
												<div class="styled-input ">
                                                        <input type="text"  id="partner_name"/>
                                                        <label>Partner Name</label>
                                                        <span></span>
                                                    </div>
													<!-- <div class="styled-input slate">
														<select id="companytype">
														

														</select> <label>Company Type<span class="red">*</span></label> <span></span>
													</div> -->
												</div>
												<div class="col-sm-2" style="margin-top: 18px;">
													<button class="btn btn-default bluebutton" id="FilterUser">Search</button>
												</div>
											</div>
											<div class="col-sm-12">
												<table id="Serchauctionparticipate"
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
													<tbody>

													</tbody>
												</table>
												<div class="text-center" style="margin-bottom: 10px;">
													<button class="btn btn-default bluebutton top20"
														id="sendauctioninvitationtopartner">Send
														Invitation</button>
												</div>
												<div class="clearfix"></div>
											</div>
										</div>
									</div>
								</div>
								
									<div class="addPartnerClass">
										 <div class="detailscont" id="masterDetails">
  							 	
    									</div>
    									   <div class="form-group">
                                                <div class="col-sm-12">
                                                    <h4 class="col-sm-6"><b>Invitation Details</b></h4>
                                                    <div class="col-sm-6 text-right">
                                                    	<button class="btn btn-default bluebutton" id="sendnewinvitation">Send New Invitation</button>
                                                    </div>
                                                    <hr>
                                                </div>
                                            </div>
    									      <div class="form-group">
    									<sf:form id="userdetailsforinvitation"  method="POST" autocomplete="off" modelAttribute="userDto">
    									<div class="form-group">
    									<input type="hidden" name="userId" id="useridforinvitation"/>
    									
    									   <div class="col-sm-4">
                                                    <div class="styled-input ">
                                                        <input type="text" name="partner.panNumber" id="companypanno" required/>
                                                        <label>Pan Card<span class="red">*</span></label>
                                                        <span></span>
                                                    </div>
                                                </div>
                                                <div class="col-sm-4">
                                                    <div class="styled-input ">
                                                <button class="btn btn-default bluebutton inviteActionBtn"  id="getbpartnerforinvitation">Search user</button>
                                                <button class="btn btn-default bluebutton inviteActionBtn"  id="cancelInvitationBtnId">Cancel</button>
                                                </div>
                                                </div>
                                                <div id=invitationDetailDivId>
                                                <div class="clearfix"></div>
                                                <div class="col-sm-4">
                                                    <div class="styled-input ">
                                                        <input type="text" name="email" id="companyemail" class="emailaddress requiredField"/>
                                                        <label>Email Id<span class="red">*</span></label>
                                                        <span></span>
                                                    </div>
                                                </div>
                                                <div class="col-sm-4">
                                                    <div class="styled-input ">
                                                        <input type="text" name="partner.name" id="comapanyname" required/>
                                                        <label>Company Name<span class="red">*</span></label>
                                                        <span></span>
                                                    </div>
                                                </div>
                                             
                                            <div class="col-sm-4">
                                                    <div class="styled-input ">
                                                        <input type="text" name="UserDetails.mobileNo" id="companymob" class="mobile requiredField" maxlength="10"/>
                                                        <label>Mobile NO.<span class="red">*</span></label>
                                                        <span></span>
                                                    </div>
                                                </div>
                                                 <div class="col-sm-4">
                                                    <div class="styled-input ">
                                                        <input type="text" name="UserDetails.firstName" id="userfirtsname"  />
                                                        <label>First Name<span class="red">*</span></label>
                                                        <span></span>
                                                    </div>
                                                </div>
                                                 <div class="col-sm-4">
                                                    <div class="styled-input ">
                                                        <input type="text" name="UserDetails.lastName" id="userlastname" required />
                                                        <label>Last Name<span class="red">*</span></label>
                                                        <span></span>
                                                    </div>
                                                </div>
                                                 <div class="col-sm-4">
                                                    <div class="styled-input ">
                                                        <input type="text" id="initationstatus" required />
                                                        <label>Status<span class="red">*</span></label>
                                                        <span></span>
                                                    </div>
                                                </div>
                                                    <div class="col-sm-12 text-center">
													<c:if test="${access_type==''}">
													   <button class="btn btn-default save" id="sendinvitationmail">Invite</button>
													</c:if>
													<c:if test="${access_type=='INVITE_AND_CREATE'}">
													   <button class="btn btn-default save" id="sendinvitationmail">Invite</button>
													   <button class="btn btn-default save" id="sendQuickInvitationMail">Create And Invite</button>
													  <!--  <button class="btn btn-default save" id="sendinvitationmail" onclick="submitIt('userdetailsforinvitation', 'sendInvitationMail', 'sendInvitationResp');">Invite</button>
													   <button class="btn btn-default save" id="sendQuickInvitationMail" onclick="submitIt('userdetailsforinvitation', 'sendInvitationMailForQuickAuction', 'sendInvitationResp');">Create And Invite</button> -->
													</c:if>
                                                </div>                                        
                                            </div>
                                            </div>
                                           </sf:form>
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


<script src="<%=request.getContextPath()%>/resources/Dashboard/addPartner.js"></script>
<script src="<%=request.getContextPath()%>/resources/Dashboard/inviteRequest.js"></script>
<script src="<%=request.getContextPath()%>/resources/commons/js/utility.js"></script>
<script src="<%=request.getContextPath()%>/resources/commons/js/formFields.js"></script>