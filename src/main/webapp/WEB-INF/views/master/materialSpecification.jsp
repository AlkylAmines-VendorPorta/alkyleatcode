<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@taglib uri = "http://www.springframework.org/tags/form" prefix = "sf"%>
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/${appMode}/tilescommon/css/fioristyle.css?appVer=${appVer}" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/${appMode}/tilescommon/css/mobile.css?appVer=${appVer}"/>
                    <div class="full-container">
                        <!-- left-side start-->
                        <div class="clearfix"></div>
                        <div id="mobile_first_container" class="left-side col-md-3 no-marg">
                            <div class="detailsheader">
                                <div class="col-sm-3 text-center brdrgt">
                                    <label>Material Bom Report (<span class="reportCount">0</span>)</label>
                                </div>
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
                                <input type="text" class="form-control" name="x" id="searchLiteralId" placeholder="Search term...">
                                <span class="input-group-btn">
                    <button class="btn btn-default" type="button"><span class="glyphicon glyphicon-search"></span></button>
                                </span>
                            </div>
                            <!-- <ul id="example" class="nav nav-tabs tabs-left example"> -->
                            <ul id="example" class="nav nav-tabs tabs-left example leftPaneData">

                            </ul>

                            <div class="clearfix"></div>
                        </div>
                        <!-- left-side end-->

                        <!-- right-side start-->
                        <div id="mobile_second_container" class="right-side col-md-9 no-marg">
                            <div class="detailsheader">
                                <div class="col-sm-9 text-center">
                                    <label>Material Part Detail</label>
                                </div>
                            </div>
                            <div class="clearfix"></div>
                            <div class="detailscont">
                                 <div class="col-md-12" id="masterDetails">
                                
                           			 </div>

                            </div>
                            <div class="tab-content">
                                <!-- Master tab start-->

                                <div class="tab-pane active in" id="AddMaterialGroup">
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
                                                   <!--  <form class="readonly"> -->
                                                   <sf:form id="SpecPartstForm" modelAttribute="materialSpecification" method="POST">
                                                   <div class="form-group">
													<div class="col-sm-12 ">
														<h4 class="col-xs-6 nopadding">
															<b>Material Parts</b>
														</h4>
														<div class="col-xs-6 text-right nopadding" style="margin-top: 10px;">
															<button class="btn btn-default" id="addSpecBtnId"><span class="glyphicon glyphicon-plus-sign"></span>Add</button>
															<button class="btn btn-default" id="editSpecBtnId"><span class="glyphicon glyphicon-pencil"></span>Edit</button>
															<button class="btn btn-default" id="deleteSpecBtnId" onclick="return submitWithParam('deleteMaterialSpecification','materialSpecificationId','specDelResp');">
																<span class="glyphicon glyphicon-trash"></span>Delete
															</button>
														</div>
														<hr>
													</div>
												</div>
                <div class="form-group">  
                
                <input type="hidden"id="materialSpecificationId" name="materialSpecificationId" value="" /> 
                
                <div class="col-sm-3">
                    <div class="styled-input slate">
                        <select type="text" id="Material"  class="Material" name="material.materialId" required="required">
                        	<%-- <c:forEach var="material" items="${material}">
								<option value="${material.materialId}">${material.name}</option>
							</c:forEach>  --%>
                        </select>
                        <label>Material<span class="red">*</span></label>
                        <span></span>
                    </div>
                </div>
                <div class="col-sm-1">
                       <button class="btn btn-default aditbt addMatPopup" id="materialId">Add Item</button>
                </div>
                <div class="col-sm-4">
                    <div class="styled-input">
                        <input type="text" id="specName"  class="Name" name="name" required="required" />
                        <label>Part Name<span class="red">*</span></label>
                        <span></span>
                    </div>
                </div>
                <div class="col-sm-4">
                    <div class="styled-input">
                        <input type="text" id="speccode"  class="Code" name="code" required="required" />
                        <label>Part Code<span class="red">*</span></label>
                        <span></span>
                    </div>
                </div>
                <div class="col-sm-4">
                    <div class="styled-input">
                        <input type="text" id=""  class="Description" name="description" required="required" />
                        <label>Part Description</label>
                        <span></span>
                    </div>
                </div>
                
                <div class="col-sm-4">
                    <div class="styled-input">
                        <input type="text" id=""  class="Quantity" name="quantity" required="required" />
                        <label>Quantity (per Set)</label>
                        <span></span>
                    </div>
                </div> 
                <div class="col-sm-3">
                    <div class="styled-input slate">
                        <!-- <input type="text" id="Material"  class="Specification" name="specification.name" required="required" />
                        <label>Specification</label> -->
                         <select type="text" id="Specification"  class="Specification" name="specification.materialId" required="required">
                        	<%-- <c:forEach var="material" items="${material}">
								<option value="${material.materialId}">${material.code}</option>
							</c:forEach>  --%>
                        </select>
                        <label>Part<span class="red">*</span></label>
                        <span></span>
                    </div>
                </div>
                <div class="col-sm-1">
                       <button class="btn btn-default aditbt addSpecPopup" id="SpecMaterial">Add Item</button>
                </div>
                </div>          
                  <div class="col-md-6 col-xs-12">
                    <label class="checkbox-inline">
                      <input type="checkbox" value="" class=" isActive"  >
                      Active </label>
                  </div>
                  <div class="clearfix"></div>
                    <div class="col-md-12 text-center">
                      <button class="btn btn-info save disableBtn" onclick="return submitIt('SpecPartstForm','saveSpecification','materialSpecificationResp');">Save</button>
					  <button class="btn btn-info cancel disableBtn" id="cancelSpecsBtnId" >Cancel</button>
                    </div>
                 <!-- </form> -->
                 </sf:form>
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

    <!-- full-container end-->
    
  
<script src="<%=request.getContextPath()%>/resource/js/materialSpecification.js?appVer=${appVer}"></script>
 <script src="<%=request.getContextPath()%>/resources/${appMode}/commons/js/formFields.js?appVer=${appVer}"></script>
 <script src="<%=request.getContextPath()%>/resources/${appMode}/commons/js/loadList.js?appVer=${appVer}"></script>
<script src="<%=request.getContextPath()%>/resources/${appMode}/commons/js/loadItemList.js?appVer=${appVer}"></script>
 