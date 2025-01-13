<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@taglib uri = "http://www.springframework.org/tags/form" prefix = "sf"%>

<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/${appMode}/tilescommon/css/fioristyle.css?appVer=${appVer}"/>
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/${appMode}/tilescommon/css//borderTab.css?appVer=${appVer}">
            <script>
                $(document).ready(function() {
                    $("#tabstrip").kendoTabStrip();
                });
            </script>
            
        <div class="full-container">
            <!-- left-side start-->
            <div class="clearfix"></div>
            <div id="mobile_first_container" class="left-side col-md-3 no-marg">
                <div class="detailsheader">
        	<div class="col-sm-3 text-center brdrgt"><label>auction Committee Report (5)</label></div>
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
                    <button class="btn btn-default" type="button">
					<span class="glyphicon glyphicon-plus"></span>
				</button>
                    </span>
                </div>
                <div class="btn-group btnmrg" data-toggle="buttons">
							    <label class="btn btn-primary">
									<input type="radio" name="options" id="option3">
									<span class="glyphicon glyphicon-ok"></span> Procurement 
                                </label>
                                <label class="btn btn-primary">
                                     <input type="radio" name="options" id="option3">
                                      <span class="glyphicon glyphicon-ok"></span> Works
                                </label>
                            </div>
                <ul id="example" class="nav nav-tabs tabs-left example">
                    <li class="active">
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
                    </li>
                </ul>
                
                <div class="clearfix"></div>
            </div>
            <!-- left-side end-->

            <!-- right-side start-->
            <div id="mobile_second_container" class="right-side col-md-9 no-marg">
            <div class="detailsheader toptabbrd">
        	<div class="col-sm-9 text-center"><label>auction Committee Details</label></div>
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
                                            <li class="k-state-active">auction Committee</li>
                                            <li>auction Committee Participate</li>
                                        </ul>

                                        <!--fields of field group 1  -->
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
                                                    <h4><b>auction Committee </b></h4>
                                                    <hr>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <div class="col-sm-3">
                                                    <div class="styled-input">
                                                        <select id="companyType" name="companyType" required></select>
                                                        <label>auction Code<span class="red">*</span></label>
                                                        <span></span>
                                                    </div>
                                                    <div class="clearfix"></div>
                                                </div>
                                                <div class="col-sm-3">
                                                    <div class="styled-input">
                                                        <select id="companyType" name="companyType" required></select>
                                                        <label>User Type<span class="red">*</span></label>
                                                        <span></span>
                                                    </div>
                                                    <div class="clearfix"></div>
                                                </div>
                                                <div class="col-sm-3">
                                                    <div class="styled-input">
                                                        <select id="companyType" name="companyType" required></select>
                                                        <label>Location<span class="red">*</span></label>
                                                        <span></span>
                                                    </div>
                                                    <div class="clearfix"></div>
                                                </div>
                                                <div class="col-sm-3">
                                                    <div class="styled-input">
                                                        <select id="companyType" name="companyType" required></select>
                                                        <label>Title<span class="red">*</span></label>
                                                        <span></span>
                                                    </div>
                                                    <div class="clearfix"></div>
                                                </div>
                                              </div>
                                             
                                            <div class="form-group">                                                
                                                <div class="col-sm-4">
                                                    <button class="btn btn-default" style="margin-top: 20px;">Search</button>
                                                </div>
                                            </div>                                            
                                        </div>
                                        <!--fields of field group 1  -->
                                        <!--fields of field group 2  -->
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
                                                    <h4><b>auction Committee Participate</b></h4>
                                                    <hr>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <div class="col-sm-3">
                                                    <div class="styled-input">
                                                        <select id="companyType" name="companyType" required></select>
                                                        <label>auction Code<span class="red">*</span></label>
                                                        <span></span>
                                                    </div>
                                                    <div class="clearfix"></div>
                                                </div>
                                                <div class="col-sm-3">
                                                    <div class="styled-input">
                                                        <select id="companyType" name="companyType" required></select>
                                                        <label>User Type<span class="red">*</span></label>
                                                        <span></span>
                                                    </div>
                                                    <div class="clearfix"></div>
                                                </div>
                                                <div class="col-sm-3">
                                                    <div class="styled-input">
                                                        <select id="companyType" name="companyType" required></select>
                                                        <label>Location<span class="red">*</span></label>
                                                        <span></span>
                                                    </div>
                                                    <div class="clearfix"></div>
                                                </div>
                                                <div class="col-sm-3">
                                                    <div class="styled-input">
                                                        <select id="companyType" name="companyType" required></select>
                                                        <label>Title<span class="red">*</span></label>
                                                        <span></span>
                                                    </div>
                                                    <div class="clearfix"></div>
                                                </div>
                                              </div>
                                             
                                            <div class="form-group">                                                
                                                <div class="col-sm-4">
                                                    <button class="btn btn-default" style="margin-top: 20px;">Search</button>
                                                </div>
                                            </div>                                            
                                        </div>
                                        <!--fields of field group 2  -->
                                    </div>

                                    <div class="col-sm-12 text-center positionABS" style="margin-bottom:10px;">
                                        
                                        <button class="btn btn-info save">Save</button>
                                        <button class="btn btn-info cancel">Cancel</button>
                                        
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
           