<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@taglib uri = "http://www.springframework.org/tags/form" prefix = "sf"%>

<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/${appMode}/tilescommon/css/fioristyle.css?appVer=${appVer}"/>
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/${appMode}/tilescommon/css//borderTab.css?appVer=${appVer}"/>
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/${appMode}/tilescommon/css/mobile.css?appVer=${appVer}"/>
<script src="<%=request.getContextPath()%>/resources/${appMode}/tilescommon/js/kendo.all.min.js?appVer=${appVer}"></script>
<style>
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
            <!-- left-side start-->
            <div class="clearfix"></div>
            <div id="mobile_first_container" class="left-side col-md-3 no-marg">
                <div class="detailsheader">
        	<div class="col-sm-3 text-center brdrgt"><label>Session Audit(0)</label></div>
        </div>               
                <div class="input-group">
                    <div class="input-group-btn search-panel">
                        <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">
                            <span id="search_concept">Filter by</span> <span class="caret"></span>
                        </button>
                        <ul class="dropdown-menu" role="menu">
                             <li><a href="#contains"><input type="radio" name="filterBy" value="userName" />User Email</a></li>
                             <li class="divider"></li>
                             <li><a href="#all"><input type="radio" name="filterBy" value="remoteIp" />IP Address</a></li>
                             <li class="divider"></li>
                             <li><a href="#all"><input type="radio" name="filterBy" value="userRole" />Role</a></li>
                        </ul>
                    </div>
                    <input type="hidden" name="search_param" value="all" id="search_param">
                    <input type="text" class="form-control" name="x" id = "searchLiteralId" placeholder="Search term...">
                    <span class="input-group-btn">
                     <button class="btn btn-default" type="button" id = "searchBtn"><span class="glyphicon glyphicon-search"></span></button>
                    <button class="btn btn-default addnewlist" type="button"><span class="glyphicon glyphicon-plus"></span></button>
                    </span>
                </div>
                  <ul id="leftPaneData" class="nav nav-tabs tabs-left example leftPaneData">
                  </ul>
                  <p id="pagination-here"></p>
              
                
                <div class="clearfix"></div>
            </div>
            <!-- left-side end-->

            <!-- right-side start-->
            <div id="mobile_second_container" class="right-side col-md-9 no-marg">
            <div class="detailsheader toptabbrd">
        	<div class="col-sm-9 text-center"><label>Session Audit</label></div>
        </div>
                <div class="clearfix"></div>
                <div class="tab-content">
                    <!-- Master tab start-->
                     <div class="col-md-12" id="masterDetails">
                                
                            </div>

                    <div class="tab-pane active in" id="">
                        <div class="card">
                            <div class="posrelative" id="">
                           <div class="demo-section k-content">
                                    <div id="tabstrip" class="Firsttab">
                                        <ul>
                                            <!-- tabs -->
                                            <li class="k-state-active tenderTab" id="sessionAuditTabId">Session Audit</li>
                                            <li class="activityTab" id="activityTabId" onclick="loadActivity()">Session Activity</li>
                                        </ul>
										<div>
										
    									   <div class="form-group">
                                                <div class="col-sm-12">
                                                    <h4><b>Session Info</b></h4>
                                                    <hr>
                                                </div>
                                            </div>
    									      <div class="col-sm-12" style="margin-top: 20px;">
											<sf:form id="sessionFormId">
											<div class="form-group">
											<input type="hidden" id="userSessionId"/>
											   <div class="col-sm-4">
	                                                 <label>User Email: </label>
	                                                 <span class="detspan" id="email"></span>
                                                </div>
                                                <div class="col-sm-4">
                                                <label>Session Id : </label>
                                                <span class="detspan" id="sessionId"></span>
                                                </div>
                                                <div class="col-sm-4">
	                                                 <label>Client IP Address : </label>
	                                                 <span class="detspan" id="remoteIP"></span>
                                                </div>
                                                </div>
                                                <div class="clearfix"></div>
                                                <div class="form-group">
                                                 <div class="col-sm-4">
                                                 <label>Session Log In Time : </label>
                                                        <span class="detspan" id="loginTime"></span>
                                                   
                                                </div>
                                                <div class="col-sm-4">
	                                                 <label>Session Log Out Time : </label>
	                                                 <span class="detspan" id="logoutTime"></span>
                                                </div>
                                                <div class="col-sm-4">
	                                                 <label>Duration: </label>
	                                                 <span class="detspan" id="duration"></span>
                                                </div>
                                                </div>
                                                 <div class="form-group">
                                                 <!--  <div class="col-sm-4">
	                                                 <label>User Type: </label>
	                                                 <span class="detspan" id="userType"></span>
                                                </div> -->
                                                <div class="col-sm-4">
	                                                 <label>Device Type: </label>
	                                                 <span class="detspan" id="deviceType"></span>
                                                </div>
                                                <div class="col-sm-4">
	                                                 <label>MAC Address: </label>
	                                                 <span class="detspan" id="macAddress"></span>
                                                </div>
                                                </div>
                                                </sf:form>
										</div>
										</div>
                                        <!--fields of field group 1  -->
                                        <div>
										
										<div id="priceBidTblDivId">
    									  <div class="form-group">
                                                <div class="col-sm-12">
                                                    <h4><b>Price Bids</b></h4>
                                                    <label  id="infolabelId"></label>
                                                    <hr>
                                                </div>
                                            </div>
                                              <div class="form-group">
                                              <div class="col-sm-12">
		                                             <div class="panel-group" id="accordion">
													    <div class="panel panel-default bidpanel workscollapseaple">
													      <div onclick="getAllBidsSubmitted(this)" id="collapseableDivId" data-open="false" class="panel-heading" data-toggle="collapse" data-parent="#accordion" href="#price_Bid">
													        <h4 class="panel-title">
													         Price Bid
													        </h4>
													      </div>
													      <!-- <div id="price_Bid" class="panel-collapse collapse">
													        <div class="panel-body bidpBody"> -->
													        <div style=" overflow-x: scroll;">
												            <table class="table table-striped tableResponsive table-bordered" style="width:100% !important;" id="price_Bid_Table">
				                                               <thead>
				                                                   <tr>
					                                                <th>Tender Code</th>
					                                                <th>Version</th>
				                                                    <th>Material Name</th>
													         		<th>Qty. Offered</th>
													         		<th>Per Unit Ex-Works Price</th>
													         		<th>Per Unit Freight charges(in Rs)</th>
													         		<th>Per Unit TIC(In Rs)</th>
													         		<th>Per unit FDD Price Without GST</th>
													         		<th>Total Ex-Works Price</th>
													         		<th>Total Freight charges</th>
													         		<th>Total TIC</th>
													         		<th>Total FDD Price Without GST</th>
													         		<th>Total GST</th>
													         		<th>Per unit FDD With GST</th>
													         		<th>Total FDD With GST</th>
													         		<th>Total FDD With GST</th>
													         		<th>Total FDD With GST</th>
													         		<th>Total FDD With GST</th>
													         		<th>Total FDD With GST</th>
				                                                   </tr>
				                                               </thead>
				                                               <tbody>                                            
				                                               </tbody>
                                           					</table>
                                           					</div>
															<!-- </div>
													      </div> -->
													    </div>
													  </div> 
													  </div>
		                                           </div>
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
<script src="<%=request.getContextPath()%>/resources/${appMode}/commons/js/utility.js?appVer=${appVer}"></script>
<script src="<%=request.getContextPath()%>/resources/${appMode}/master/js/sessionAudit.js?appVer=${appVer}"></script>
<script src="<%=request.getContextPath()%>/resources/${appMode}/master/js/sessionAuditActivity.js?appVer=${appVer}"></script>
<script src="<%=request.getContextPath()%>/resources/${appMode}/commons/js/formFields.js?appVer=${appVer}"></script>