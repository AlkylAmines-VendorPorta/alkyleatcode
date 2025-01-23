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
        		<div class="col-sm-3 text-center brdrgt"><label>Auction Technical Scrutiny Report (5)</label></div>
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
        		<div class="col-sm-9 text-center"><label>Auction Technical Scrutiny Details</label></div>
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
                                            <li class="k-state-active">Preliminary Technical Scrutiny</li>
                                            <li>Preliminary Technical Scrutiny</li>
                                            <li>PTS GTP Details </li>
                                            <li>PTS Document Details</li>
                                            <li>PTS Verify Details</li>
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
                                                    <h4><b>Preliminary Technical Scrutiny</b></h4>
                                                    <hr>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                             
                                                <div class="col-sm-12">
                                                    <button class="btn btn-default">Start WorkFlow</button>
                                                    <button class="btn btn-default">Status</button>
                                                	<button class="btn btn-default">Download Auction</button>
                                                	<button class="btn btn-default">View Details</button>
                                                	<button class="btn btn-default">Start Scrutiny</button>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <div class="col-sm-4">
                                                    <div class="styled-input">
                                                        <select id="companyType" name="companyType" required="">
                                                        </select>
                                                        <label>Select Auction Code<span class="red">*</span></label>
                                                        <span></span>
                                                    </div>
                                                 </div>
                                                 <div class="col-sm-4">
                                                    <button class="btn btn-default top20">Search</button>
                                                 </div>
                                               </div>
                                             <div class="col-sm-12">
                                             	<table class="table table-bordered">
                                                        <thead>
                                                            <tr>
                                                                <th scope="col">Select</th>
                                                                <th scope="col">Vendor Name</th>
                                                                <th scope="col">REGPDF</th>
                                                                <th scope="col">TECHBIDPDF</th>
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
                                            <div class="clearfix"></div>
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
                                                    <h4><b>Preliminary Technical Scrutiny</b></h4>
                                                    <hr>
                                                </div>
                                            </div>                                            
                                            <div class="form-group">                                               
                                                 <div class="col-sm-8">
                                                    <label class="radio-inline top40">
                                                    <input type="radio" value="">In Order to complete PTS following details Required</label>
                                                 </div>
                                               </div>
                                               <div class="form-group">
                                             
                                                <div class="col-sm-12">
                                                    <button class="btn btn-default">Continue</button>
                                                    <button class="btn btn-default">Goto Main Page</button>
                                                </div>
                                            </div>
                                             <div class="col-sm-12">
                                             	<table class="table table-bordered">
                                                        <thead>
                                                            <tr>
                                                                <th scope="col">Scrutiny Points</th>
                                                                <th scope="col">Acceptance Status</th>
                                                                <th scope="col">Deviation Query + comments</th>
                                                                <th scope="col">Deviation Type</th>
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
                                               <div class="form-group">
                                                <div class="col-sm-12">
                                                    <button class="btn btn-default">Save And Next</button>
                                                    <button class="btn btn-default">Cancel</button>
                                                </div>
                                              </div>  
                                            <div class="clearfix"></div>
                                        </div>
                                        <!--fields of field group 2  -->
                                        
                                        <!--fields of field group 3  -->
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
                                                    <h4><b>Preliminary Technical scrutiny GTP Details</b></h4>
                                                    <hr>
                                                </div>
                                            </div>                                            
                                             <div class="col-sm-12">
                                             	<table class="table table-bordered">
                                                        <thead>
                                                            <tr>
                                                                <th>N/A</th>
                                                                <th>GTP Parameter</th>
                                                                <th>GTP Value</th>
                                                                <th>Deviation Query + comments</th>
                                                                <th>Call For Deviation</th>
                                                                <th>Deviation Query + comments</th>
                                                                <th>GTP Type</th>
                                                            </tr>
                                                        </thead>
                                                        <tbody>
                                                            <tr>
                                                                <th><input type="radio"></th>
                                                                <td></td>
                                                                <td></td>
                                                                <td></td>
                                                                <td><button class="btn btn-default">REG PDF</button></td>
                                                                <td><button class="btn btn-default">TECH BID PDF</button></td>
                                                                <td><button class="btn btn-default">TECH BID PDF</button></td>
                                                            </tr>                                                                                                                       
                                                        </tbody>
                                                    </table>
                                             </div>
                                               <div class="form-group">
                                                <div class="col-sm-12">
                                                    <button class="btn btn-default">Save</button>
                                                    <button class="btn btn-default">Cancel</button>
                                                </div>
                                              </div>                                             
                                        </div>
                                        <!--fields of field group 3  -->
                                        <!--fields of field group 4  -->
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
                                                    <h4><b>Preliminary Technical scrutiny Document Details</b></h4>
                                                    <hr>
                                                </div>
                                            </div>
                                            <div class="col-sm-12">
                                             	<table class="table table-bordered">
                                                        <thead>
                                                            <tr>
                                                                <th>N/A</th>
                                                                <th>Required Doc Name</th>
                                                                <th>Document</th>
                                                                <th>Acceptance Status</th>
                                                                <th>Deviation Query + comments</th>
                                                                <th>Deviation Type</th>
                                                            </tr>
                                                        </thead>
                                                        <tbody>
                                                            <tr>
                                                                <th><input type="radio"></th>
                                                                <td></td>
                                                                <td></td>
                                                                <td><button class="btn btn-default">REG PDF</button></td>
                                                                <td><button class="btn btn-default">TECH BID PDF</button></td>
                                                                <td><button class="btn btn-default">TECH BID PDF</button></td>
                                                            </tr>                                                                                                                       
                                                        </tbody>
                                                    </table>
                                             </div>                                                                                    
                                        </div>
                                        <!--fields of field group 4  -->
                                        <!--fields of field group 5  -->
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
                                                    <h4><b>View The Technical  scrutiny Document</b></h4>
                                                    <hr>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                    <div class="col-sm-12">
                                                    	<button class="btn btn-default">Download Technical Scrutiny PDF</button>
                                                    	<button class="btn btn-default">Goto Main Page</button>
                                                    </div>                                     
                                            </div>                                                                                       
                                        </div>
                                        <!--fields of field group 5  -->
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