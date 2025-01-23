<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@taglib uri = "http://www.springframework.org/tags/form" prefix = "sf"%>
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/${appMode}/tilescommon/css/fioristyle.css?appVer=${appVer}"/>
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/${appMode}/tilescommon/css/mobile.css?appVer=${appVer}"/>

<div class="full-container"> 
  <!-- left-side start-->
  <div class="clearfix"></div>
  <div id="mobile_first_container" class="left-side col-md-3 no-marg">
  <div class="detailsheader">
        	<div class="col-sm-3 text-center brdrgt"><label>CMS Report (<span class="reportCount">0</span>)</label></div>
        </div>
    <div class="input-group">
                <div class="input-group-btn search-panel">
                    <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">
                    	<span id="search_concept">Filter by</span> <span class="caret"></span>
                    </button>
                    <ul class="dropdown-menu dropwidth" role="menu">
                      <li>
                      				<!-- <div class="col-sm-12">
													<div class="form-group frgrp condisn">
														<label for="dtp_input1" class="control-label">
															uploaded From<span class="red">*</span>
														</label>
														<div class="input-group date form_datetime"	data-provide="datepicker">
															<input type="text" class="form-control"	id="attachuploadstartdate"/>
															<div class="input-group-addon">
																<span class="glyphicon glyphicon-th"></span>
															</div>
														</div>
													</div>
												</div> -->
												<div class="col-sm-12">
													<div class="form-group frgrp">
														<label for="dtp_input1" class="control-label">Uploaded From<span class="red">*</span>
														</label>
														<div class="input-group date form_datetime">
															<input type="text" class="form-control" id="attachuploadstartdate" name=""
																data-label="Bid Start Date & Time">
															<span class="input-group-addon"><span class="glyphicon glyphicon-th"></span></span>
														</div>
														<input type="hidden" id="dtp_input1" value="" /><br />
													</div>
												</div>
												<div class="col-sm-12">
													<div class="form-group frgrp">
														<label for="dtp_input1" class="control-label">Uploaded To<span class="red">*</span>
														</label>
														<div class="input-group date form_datetime">
															<input type="text" class="form-control" id="attachuploadenddate" name=""
																data-label="Bid Start Date & Time">
															<span class="input-group-addon"><span class="glyphicon glyphicon-th"></span></span>
														</div>
														<input type="hidden" id="dtp_input1" value="" /><br />
													</div>
												</div>
												<!-- <div class="col-sm-12">
													<div class="form-group frgrp condisn">
														<label for="dtp_input1" class="control-label">
															Uploaded To<span class="red">*</span>
														</label>
														<div class="input-group date form_datetime"	data-provide="datepicker">
															<input type="text" class="form-control"	id="attachuploadenddate"/>
															<div class="input-group-addon">
																<span class="glyphicon glyphicon-th"></span>
															</div>
														</div>
													</div>
												</div> -->
														<div class="col-sm-12 text-center">
															<button class="btn btn-default bluebutton" id="getdatewisedetails">Search</button>
														</div>
                      	
                      </li>
                      <!-- <li class="divider"></li>
                      <li><a href="#all">Type</a></li> -->
                    </ul>
                </div>
                <input type="hidden" name="search_param" value="all" id="search_param">         
                <input type="text" class="search-query form-control" name="x" id="searchLiteralId"  placeholder="Search term...">
                <span class="input-group-btn">
                    <button class="btn btn-default" type="button" onclick="searchuom()"><span class="glyphicon glyphicon-search"></span></button>
                    <button class="btn btn-default" type="button" onclick="renderList()"><span class="glyphicon glyphicon-refresh"></span></button>
               <!-- <button class="btn btn-default addnewuom" type="button"><span class="glyphicon glyphicon-plus"></span></button> -->
                </span>
            </div>
    <ul id="example" class="nav nav-tabs tabs-left example leftPaneData">
      
        
    </ul>
     <p id="pagination-here"></p>
    
    <div class="clearfix"></div>
  </div>
  <!-- left-side end--> 
  
  <!-- right-side start-->
  <div id="mobile_second_container" class="right-side col-md-9 no-marg">
    <div class="detailsheader">
        	<div class="col-sm-9 text-center"><label>CMS Detail</label></div>
        </div>
    <div class="clearfix"></div>
    <div class="detailscont">
  							 <div class="col-md-12" id="masterDetails">
                                
                            </div>
                            
                            
    </div>
    <div class="tab-content"> 
      <!-- Master tab start-->
      
      <div class="tab-pane active in" id="">
      <section>
            <div class="board">
                <div class="board-inner">
                    <ul class="nav nav-tabs" id="myTab">
                        <div class="liner"></div>
                        <li class="active">
                            <a href="#home" data-toggle="tab" title="Information">
                                <span class="round-tabs one">
                                <i class="fa fa-info" aria-hidden="true"></i>
                                </span>
                            </a>
                        </li>                        
                    </ul>
                </div>
                <div class="tab-content">
                    <div class="tab-pane fade in active" id="home">
                     <!--  <div class="col-sm-12 text-right">
		                		 <div class="form-group">
		                		<button type="button" class="btn btn-info bluebutton" id="deluom"><span class="glyphicon glyphicon-trash"></span>Delete</button>
		                										  
		                		<button type="button" class="btn btn-info bluebutton" id="edituom"><span class="glyphicon glyphicon-edit"></span>Edit</button>
		                		</div>
		                		</div> -->
		                		<div class="clearfix"></div>
		                		                   <!--  <div class="col-sm-12 mobilefilter"><button class="btn btn-default bluebutton pull-right"><span class="glyphicon glyphicon-filter"></span></button></div>
                                            <div class="filterDiv"> -->
                                            <!-- <div class="col-sm-2 top20">
                               					<label class="checkbox-inline">
                                                <input type="checkbox" id="sellercheck" class="checkbox readonly" ><b>Seller</b></label> 
                                                <label class="checkbox-inline">
                                                <input type="checkbox" id="buyercheck" class="checkbox readonly" ><b>Buyer</b></label>                                                         	
                                            </div> -->
                                            <%-- <div class="col-sm-3">
															<div class="styled-input slate">
																 <select  id="statemaster">
                        	
							<c:forEach var="regionList" items="${regionList}">
								<option value="${regionList.regionId}">${regionList.name}</option>
							</c:forEach>
							
                        </select>
																<label>State<span class="red">*</span></label>
																<span></span>
															</div>
														</div> --%>
														<%-- <div class="col-sm-3">
															<div class="styled-input slate">
																<select  id="companytype">
                
							<c:forEach var="companyTypeList" items="${companyTypeList}">
								<option value="${companyTypeList.companyTypeId}">${companyTypeList.name}</option>
							</c:forEach>
							
                        </select>
																<label>Company Type<span class="red">*</span></label>
																<span></span>
															</div>
														</div> --%>
														<!-- <div class="col-sm-4">
													<div class="form-group frgrp condisn">
														<label for="dtp_input1" class="control-label">
															Sale Start Date<span class="red">*</span>
														</label>
														<div class="input-group date Pickdate"	data-provide="datepicker">
															<input type="text" class="form-control"	name="purchaseFromDate">
															<div class="input-group-addon">
																<span class="glyphicon glyphicon-th"></span>
															</div>
														</div>
													</div>
												</div> -->
												<!-- <div class="col-sm-4">
													<div class="form-group frgrp condisn">
														<label for="dtp_input1" class="control-label">
															Sale Start Date<span class="red">*</span>
														</label>
														<div class="input-group date Pickdate"	data-provide="datepicker">
															<input type="text" class="form-control"	name="purchaseFromDate">
															<div class="input-group-addon">
																<span class="glyphicon glyphicon-th"></span>
															</div>
														</div>
													</div>
												</div> -->
														<!-- <div class="col-sm-2" style="margin-top:18px;">
															<button class="btn btn-default bluebutton" id="">Search</button>
														</div> -->
													<!-- 	</div> -->
														<div class="clearfix"></div>
            <sf:form id="cmsmaster" class="top20 readonly" method="POST" autocomplete="off" modelAttribute="Attachment">
                <div class="form-group">  
                <div class="col-sm-4">
                                                 <label>Name :-</label>
                                                        <span class="detspan Name"></span>
                                                   
                                                </div> 
                                                <div class="col-sm-4">
                                                 <label>File Name :-</label>
                                                        <span class="detspan Code" ></span>
                                                   
                                                </div> 
                                                <div class="col-sm-4">
                                                 <label>Uploaded on :-</label>
                                                        <span class="detspan Description"></span>
                                                   
                                                </div> 
               </div> 
               <label style="display:none;" class="col-md-6 col-xs-12">
                    <input style="display:none;" type="text" id="attacmntid" class="form-control Id">
                  </label>    
                                            <div class="col-md-6 col-xs-12">
                                                <label class="checkbox-inline">
                                                    <input type="checkbox" value="" class=" isActive" > Active </label>
                                            </div>
                                            <div class="clearfix"></div>
                                            
                                        </sf:form>
                                        <div class="col-md-12 text-center">
                                              <a data-url="<%=request.getContextPath()%>/download"   class="btn btn-info save" id="downloadselectedfiles">Download</a>
                      
                                            </div>
                                       
                    </div>
                    <div class="clearfix"></div>
                </div>
            </div>
		 </section>        
      </div>
      
    
      </div>
      <!-- Master tab end--> 
      
                      
    </div>
  <!-- right-side end--> 
  </div>   
 <script src="<%=request.getContextPath()%>/resource/js/cms.js?appVer=${appVer}"></script>
   <script src="<%=request.getContextPath()%>/resources/${appMode}/commons/js/formFields.js?appVer=${appVer}"></script>
 <script src="<%=request.getContextPath()%>/resources/${appMode}/commons/js/springform.js?appVer=${appVer}"></script>
<script src="<%=request.getContextPath()%>/resources/${appMode}/commons/js/utility.js?appVer=${appVer}"></script>
<script src="<%=request.getContextPath()%>/resources/${appMode}/commons/js/loadList.js?appVer=${appVer}"></script>
<script src="<%=request.getContextPath()%>/resources/${appMode}/commons/js/formFields.js?appVer=${appVer}"></script>
<script src="<%=request.getContextPath()%>/resources/${appMode}/commons/js/loadItemList.js?appVer=${appVer}"></script>
<script
	src="<%=request.getContextPath()%>/resources/${appMode}/tilescommon/js/bootstrap-datetimepicker.js?appVer=${appVer}"></script>
<script type="text/javascript">
	var date = new Date();
	date.setDate(date.getDate());

	$('.form_datetime').datetimepicker({
		autoclose : true,
		forceParse : 0,
		format : 'dd-mm-yyyy hh:ii',
		orientation : 'auto',
		pickerPosition : "bottom-left",
		pick12HourFormat : false
	});
</script>