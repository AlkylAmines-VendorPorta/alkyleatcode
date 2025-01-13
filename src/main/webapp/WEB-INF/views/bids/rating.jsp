<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@taglib uri = "http://www.springframework.org/tags/form" prefix = "sf"%>

<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/${appMode}/tilescommon/css/fioristyle.css?appVer=${appVer}"/>
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/${appMode}/tilescommon/css/borderTab.css?appVer=${appVer}"/>
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/${appMode}/tilescommon/css/jquery.rateyo.css?appVer=${appVer}"/>
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/${appMode}/tilescommon/css/mobile.css?appVer=${appVer}"/>
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
        	<div class="col-sm-3 text-center brdrgt"><label>Rating Report (0)</label></div>
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
                    <button class="btn btn-default addnewlist" type="button"><span class="glyphicon glyphicon-plus"></span></button>
                    </span>
                </div>
                  <ul id="example" class="nav nav-tabs tabs-left example">
      
        
    </ul>
               <!--  <div class="btn-group btnmrg" data-toggle="buttons">
							    <label class="btn btn-primary toggleTab" openTab="tenderTab">
									<input type="radio" class="invitationlist" name="tenderTypeCodeToggle" value="FA" id="option3">
									<span class="glyphicon glyphicon-ok"></span> Invitation 
                                </label>
                                <label class="btn btn-primary active  toggleTab" openTab="tenderTab">
                                     <input type="radio" class="invitationlist"  name="tenderTypeCodeToggle" checked="checked" value="RA" id="option3">
                                      <span class="glyphicon glyphicon-ok"></span> Request
                                </label>
                            </div> -->
                <ul id="leftPaneData" class="nav nav-tabs tabs-left leftPaneData">
                   
                </ul>
                
                <div class="clearfix"></div>
            </div>
            <!-- left-side end-->

            <!-- right-side start-->
            <div id="mobile_second_container" class="right-side col-md-9 no-marg">
            <div class="detailsheader toptabbrd">
        	<div class="col-sm-9 text-center"><label>Rating Details</label></div>
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
                                            <li class="k-state-active tenderTab">Item</li></ul>
										<div>
										<!--  <div class="detailscont">
  							 				 <div class="col-md-12">
                                <label class="col-xs-6">NovelERP Solutions</label>
                            	<label class="col-xs-6 ">12765456</label>
                            </div>	
                            <div class="col-md-12">
                           		<label class="col-xs-6">Manufacturer</label>
                            	<label class="col-xs-6 ">EOCPK88Pk</label>
                            </div>
    									</div> -->
    									   <div class="form-group">
                                                <div class="col-sm-12">
                                                    <h4><b>Item</b></h4>
                                                    <!-- <div class="col-sm-6 text-right">
                                                    	<button class="btn btn-default bluebutton" id="sendnewinvitation">Send New Invitation</button>
                                                    </div> -->
                                                    <hr>
                                                </div>
                                            </div>
    									      <div class="col-sm-8" style="margin-top: 20px;">
											<table
												class="Sessionaudit table table-striped table-bordered"
												width="100%" id="tahdrTableId">
												<tr>
												    <th>Quantity</th>
												    <td><div class="rateYo"></div></td>
												  </tr>
												  <tr>
												    <th>Delivery</th>
												    <td><div class="rateYo"></div></td>
												  </tr>
												  <tr>
												    <th>Service</th>
												    <td><div class="rateYo"></div></td>
												  </tr>
												  <tr>
												    <th>Price</th>
												    <td><div class="rateYo"></div></td>
												  </tr>
											</table>
											<div class="col-sm-12 text-center"><button class="btn btn-default save">Submit</button></div>
										</div>
										</div>
                                        <!--fields of field group 1  -->
                                                                   
                        </div>
                        <!-- right-side end-->
                    </div>
                </div>
            </div>
            </div>
            </div>
            </div>
            </div>
           <script src="<%=request.getContextPath()%>/resources/${appMode}/tilescommon/js/jquery.rateyo.js?appVer=${appVer}"></script>
           <script>
           $(function () {
        	   
        	   $(".rateYo").rateYo({
        	     numStars: 10,
        	     fullStar:true
        	   });
        	  
        	 });
           </script>