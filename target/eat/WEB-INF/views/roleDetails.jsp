<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@taglib uri = "http://www.springframework.org/tags/form" prefix = "sf"%>
  <link rel="stylesheet" href="<%=request.getContextPath()%>/resources/${appMode}/tilescommon/css/fioristyle.css?appVer=${appVer}"/>
  <link rel="stylesheet" href="<%=request.getContextPath()%>/resources/${appMode}/tilescommon/css//borderTab.css?appVer=${appVer}">
 <link rel="stylesheet" href="<%=request.getContextPath()%>/resources/${appMode}/tilescommon/css/roleButton.css?appVer=${appVer}" />
 <link rel="stylesheet" href="<%=request.getContextPath()%>/resources/${appMode}/tilescommon/css/mobile.css?appVer=${appVer}" />
 <script src="<%=request.getContextPath()%>/resources/${appMode}/tilescommon/js/kendo.all.min.js?appVer=${appVer}"></script>
  <script src="<%=request.getContextPath()%>/resource/js/roleDetails.js?appVer=${appVer}"></script>
  <script src="<%=request.getContextPath()%>/resources/${appMode}/commons/js/springform.js?appVer=${appVer}"></script>
   <script src="<%=request.getContextPath()%>/resources/${appMode}/commons/js/formFields.js?appVer=${appVer}"></script>
  <style>
  #mobile_second_container {
    margin-bottom: 35px;
} .detailscont{margin-bottom: 10px;}</style>
                <!--js included  -->
               
                <div class="full-container">
                    <!-- left-side start-->
                    <div class="clearfix"></div>
                    <div id="mobile_first_container" class="left-side col-md-3 no-marg">
                    <div class="detailsheader">
        		<div class="col-sm-3 text-center brdrgt"><label>Role Access Report (<span class="reportCount">12</span>)</label></div>
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
                    <button class="btn btn-default addnewlist" type="button"><span class="glyphicon glyphicon-plus"></span></button>
                            </span>
                        </div>
                        <ul id="example" class="nav nav-tabs tabs-left example">
                            <%int i = 0;%>
                                <c:forEach var="roleList" items="${roleList}" varStatus="count">
                                    <li <c:if test="${count.index == 0}">class="active"</c:if>>
                                        <a href="#Master" data-toggle="tab" onclick="showDetails('${roleList.roleId}')" id="${roleList.roleId}">
                                            <%if(i==0){%>
                                                <input type="hidden" id="activeRole" value="${roleList.roleId}" />
                                                <% i++;}%>
                                                <div>
                                                    <div class="col-md-12">
                                                        <label class="col-xs-6">${roleList.name}</label>
                                                        <label class="col-xs-6"> ${roleList.description}</label>
                                                    </div>
                                                    <div class="col-md-12">
                                                        <label class="col-xs-6">${roleList.value}</label>
                                                        <label class="col-xs-6"> ${roleList.viewName}</label>
                                                    </div>
                                                   </div>

                                        </a>
                                    </li>
                                </c:forEach>
                        </ul>
                        
                        <div class="clearfix"></div>
                    </div>
                    <!-- left-side end-->

                    <!-- right-side start-->
                    <div id="mobile_second_container" class="right-side col-md-9 no-marg">
                    <div class="detailsheader toptabbrd">
        	<div class="col-sm-9 text-center"><label>Role Access Details</label></div>
        </div>
                        <div class="clearfix"></div>
                        <div class="tab-content">
                            <!-- Master tab start-->
                            <sf:form style="display:none;" id="formId" action="saveRoleAccessMasters" method="POST" autocomplete="off" modelAttribute="roleAccessMaster">
                                <table id="hiddenFormTableId">
                                    <tbody>
                                    </tbody>
                                </table>
                                <button class="btn btn-default formSubmit">Save</button>
                            </sf:form>
                            <form id="detailsFormId">
                                <div class="tab-pane active in">
                                    <div class="card">
                                        <div >
                                            <div class="demo-section k-content">
                                                <div id="tabstrip" class="Firsttab">
                                                    <ul>
                                                        <c:forEach var="tileList" items="${tileList}" varStatus="count">
                                                            <!-- tabs -->
                                                            <li <c:if test="${tileList.name == 'Dashboard'}"> class="k-state-active"</c:if> onclick="cancel()">${tileList.name}</li>
                                                        </c:forEach>
                                                    </ul>

                                                    <!--fields of field group 1  -->

                                                    <div>
													   <div class="detailscont">
				  							 				
				    									</div>
                                                        <div class="form-group">
                                                        
                                                            <div class="col-sm-6">
                                                                <div class="row" style="margin-bottom: 15px;">
                                                                    <div class="col-xs-6"><b>Sub Tile</b> </div>
                                                                    <div class="col-xs-2"><b>View</b></div>
                                                                    <div class="col-xs-2"><b>Edit</b></div>
                                                                    <div class="col-xs-2"><b>Delete</b></div>
                                                                </div>
                                                                <c:forEach var="subTileList" items="${subTileList}" varStatus="count">

                                                                    <c:if test="${subTileList.parentId== 1}">
                                                                        <div class="row">
                                                                            <div class="col-xs-6"><b>${subTileList.name}</b></div>
                                                                            <input type="text" style="display:none;" id="${subTileList.code}_roleAccessId" />
                                                                            <div class="col-xs-2">
                                                                                <input type="checkbox" id="${subTileList.code}_View" onclick="onCheckBoxChange(${subTileList.tileMasterId},'${subTileList.code}',this)" name="" />
                                                                                <div class="btn-group">
                                                                                    <label for="${subTileList.code}_View" class="btn btn-default">
                                                                                        <span class="glyphicon glyphicon-ok"></span>
                                                                                        <span> </span>
                                                                                    </label>
                                                                                </div>
                                                                            </div>
                                                                            <div class="col-xs-2">
                                                                                <input type="checkbox" id="${subTileList.code}_Edit" onclick="onCheckBoxChange(${subTileList.tileMasterId},'${subTileList.code}',this)" name="" />
                                                                                <div class="btn-group">
                                                                                    <label for="${subTileList.code}_Edit" class="btn btn-default">
                                                                                        <span class="glyphicon glyphicon-ok"></span>
                                                                                        <span> </span>
                                                                                    </label>
                                                                                </div>
                                                                            </div>
                                                                            <div class="col-xs-2">
                                                                                <input type="checkbox" id="${subTileList.code}_Delete" onclick="onCheckBoxChange(${subTileList.tileMasterId},'${subTileList.code}',this)" name="" />
                                                                                <div class="btn-group">
                                                                                    <label for="${subTileList.code}_Delete" class="btn btn-default">
                                                                                        <span class="glyphicon glyphicon-ok"></span>
                                                                                        <span> </span>
                                                                                    </label>
                                                                                </div>
                                                                            </div>

                                                                        </div>
                                                                    </c:if>
                                                                </c:forEach>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <!--fields of field group 1  -->
                                                    <!--fields of field group 2  -->
                                                    <div>
                                                    <div class="detailscont">
				  							 				
				    									</div>
                                                        <div class="form-group">
                                                            <div class="col-sm-6">
                                                                <div class="row" style="margin-bottom: 15px;">
                                                                    <div class="col-xs-6"><b>Master Name</b></div>
                                                                    <div class="col-xs-2"><b>View</b></div>
                                                                    <div class="col-xs-2"><b>Edit</b></div>
                                                                    <div class="col-xs-2"><b>Delete</b></div>
                                                                </div>
                                                                <c:forEach var="subTileList" items="${subTileList}" varStatus="count">
                                                                    <c:if test="${subTileList.parentId== 2 && count.index<=15}">

                                                                        <div class="row">
                                                                            <div class="col-xs-6"><b>${subTileList.name}</b></div>
                                                                            <input type="text" style="display:none;" id="${subTileList.code}_roleAccessId" />
                                                                            <div class="col-xs-2">
                                                                                <input type="checkbox" id="${subTileList.code}_View" onclick="onCheckBoxChange(${subTileList.tileMasterId},'${subTileList.code}',this)" autocomplete="off" />
                                                                                <div class="btn-group">
                                                                                    <label for="${subTileList.code}_View" class="btn btn-default">
                                                                                        <span class="glyphicon glyphicon-ok"></span>
                                                                                        <span> </span>
                                                                                    </label>
                                                                                </div>
                                                                            </div>
                                                                            <div class="col-xs-2">
                                                                                <input type="checkbox" id="${subTileList.code}_Edit" onclick="onCheckBoxChange(${subTileList.tileMasterId},'${subTileList.code}',this)" autocomplete="off" />
                                                                                <div class="btn-group">
                                                                                    <label for="${subTileList.code}_Edit" class="btn btn-default">
                                                                                        <span class="glyphicon glyphicon-ok"></span>
                                                                                        <span> </span>
                                                                                    </label>
                                                                                </div>
                                                                            </div>
                                                                            <div class="col-xs-2">
                                                                                <input type="checkbox" id="${subTileList.code}_Delete" onclick="onCheckBoxChange(${subTileList.tileMasterId},'${subTileList.code}',this)" autocomplete="off" />
                                                                                <div class="btn-group">
                                                                                    <label for="${subTileList.code}_Delete" class="btn btn-default">
                                                                                        <span class="glyphicon glyphicon-ok"></span>
                                                                                        <span> </span>
                                                                                    </label>
                                                                                </div>
                                                                            </div>

                                                                        </div>
                                                                    </c:if>
                                                                </c:forEach>
                                                            </div>

                                                            <div class="col-sm-6">
                                                                <div class="row" style="margin-bottom: 15px;">
                                                                    <div class="col-xs-6"><b>Master Name</b></div>
                                                                    <div class="col-xs-2"><b>View</b></div>
                                                                    <div class="col-xs-2"><b>Edit</b></div>
                                                                    <div class="col-xs-2"><b>Delete</b></div>
                                                                </div>
                                                                <c:forEach var="subTileList" items="${subTileList}" varStatus="count">
                                                                    <c:if test="${subTileList.parentId== 2 && count.index>15}">

                                                                        <div class="row">
                                                                            <div class="col-xs-6"><b>${subTileList.name}</b></div>
                                                                            <input type="text" style="display:none;" id="${subTileList.code}_roleAccessId" />
                                                                            <div class="col-xs-2">
                                                                                <input type="checkbox" id="${subTileList.code}_View" onclick="onCheckBoxChange(${subTileList.tileMasterId},'${subTileList.code}',this)" autocomplete="off" />
                                                                                <div class="btn-group">
                                                                                    <label for="${subTileList.code}_View" class="btn btn-default">
                                                                                        <span class="glyphicon glyphicon-ok"></span>
                                                                                        <span> </span>
                                                                                    </label>
                                                                                </div>
                                                                            </div>
                                                                            <div class="col-xs-2">
                                                                                <input type="checkbox" id="${subTileList.code}_Edit" onclick="onCheckBoxChange(${subTileList.tileMasterId},'${subTileList.code}',this)" autocomplete="off" />
                                                                                <div class="btn-group">
                                                                                    <label for="${subTileList.code}_Edit" class="btn btn-default">
                                                                                        <span class="glyphicon glyphicon-ok"></span>
                                                                                        <span> </span>
                                                                                    </label>
                                                                                </div>
                                                                            </div>
                                                                            <div class="col-xs-2">
                                                                                <input type="checkbox" id="${subTileList.code}_Delete" onclick="onCheckBoxChange(${subTileList.tileMasterId},'${subTileList.code}',this)" autocomplete="off" />
                                                                                <div class="btn-group">
                                                                                    <label for="${subTileList.code}_Delete" class="btn btn-default">
                                                                                        <span class="glyphicon glyphicon-ok"></span>
                                                                                        <span> </span>
                                                                                    </label>
                                                                                </div>
                                                                            </div>

                                                                        </div>
                                                                    </c:if>
                                                                </c:forEach>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <!--fields of field group 2  -->

                                                    <!--fields of field group 3  -->

                                                    <div>
                                                    <div class="detailscont">
				  							 				
				    									</div>
                                                        <div class="form-group">
                                                            <div class="col-sm-6">
                                                                <div class="row" style="margin-bottom:15px;">
                                                                    <div class="col-xs-6"><b>Tender Details</b></div>
                                                                    <div class="col-xs-2"><b>View</b></div>
                                                                    <div class="col-xs-2"><b>Edit</b></div>
                                                                    <div class="col-xs-2"><b>Delete</b></div>
                                                                </div>
                                                                <c:forEach var="subTileList" items="${subTileList}" varStatus="count">
                                                                    <c:if test="${subTileList.parentId== 3}">
                                                                        <div class="row">
                                                                            <div class="col-xs-6"><b>${subTileList.name}</b></div>
                                                                            <input type="text" style="display:none;" id="${subTileList.code}_roleAccessId" />
                                                                            <div class="col-xs-2">
                                                                                <input type="checkbox" id="${subTileList.code}_View" onclick="onCheckBoxChange(${subTileList.tileMasterId},'${subTileList.code}',this)" autocomplete="off" />
                                                                                <div class="btn-group">
                                                                                    <label for="${subTileList.code}_View" class="btn btn-default">
                                                                                        <span class="glyphicon glyphicon-ok"></span>
                                                                                        <span> </span>
                                                                                    </label>
                                                                                </div>
                                                                            </div>
                                                                            <div class="col-xs-2">
                                                                                <input type="checkbox" id="${subTileList.code}_Edit" onclick="onCheckBoxChange(${subTileList.tileMasterId},'${subTileList.code}',this)" autocomplete="off" />
                                                                                <div class="btn-group">
                                                                                    <label for="${subTileList.code}_Edit" class="btn btn-default">
                                                                                        <span class="glyphicon glyphicon-ok"></span>
                                                                                        <span> </span>
                                                                                    </label>
                                                                                </div>
                                                                            </div>
                                                                            <div class="col-xs-2">
                                                                                <input type="checkbox" id="${subTileList.code}_Delete" onclick="onCheckBoxChange(${subTileList.tileMasterId},'${subTileList.code}',this)" autocomplete="off" />
                                                                                <div class="btn-group">
                                                                                    <label for="${subTileList.code}_Delete" class="btn btn-default">
                                                                                        <span class="glyphicon glyphicon-ok"></span>
                                                                                        <span> </span>
                                                                                    </label>
                                                                                </div>
                                                                            </div>
                                                                        </div>
                                                                    </c:if>
                                                                </c:forEach>
                                                            </div>
                                                        </div>
                                                    </div>

                                                    <!--fields of field group 3  -->

                                                    <!--fields of field group 4  -->

                                                    <div>
                                                    <div class="detailscont">
				  							 				
				    									</div>
                                                        <div class="form-group">
                                                            <div class="col-sm-6">
                                                                <div class="row" style="margin-bottom: 15px;">
                                                                    <div class="col-xs-6"><b>Auction Details</b></div>
                                                                    <div class="col-xs-2"><b>View</b></div>
                                                                    <div class="col-xs-2"><b>Edit</b></div>
                                                                    <div class="col-xs-2"><b>Delete</b></div>
                                                                </div>
                                                                <c:forEach var="subTileList" items="${subTileList}" varStatus="count">
                                                                    <c:if test="${subTileList.parentId== 4}">
                                                                        <div class="row">
                                                                            <div class="col-xs-6"><b>${subTileList.name}</b></div>
                                                                            <input type="text" style="display:none;" id="${subTileList.code}_roleAccessId" />
                                                                            <div class="col-xs-2">
                                                                                <input type="checkbox" id="${subTileList.code}_View" onclick="onCheckBoxChange(${subTileList.tileMasterId},'${subTileList.code}',this)" autocomplete="off" />
                                                                                <div class="btn-group">
                                                                                    <label for="${subTileList.code}_View" class="btn btn-default">
                                                                                        <span class="glyphicon glyphicon-ok"></span>
                                                                                        <span> </span>
                                                                                    </label>
                                                                                </div>
                                                                            </div>
                                                                            <div class="col-xs-2">
                                                                                <input type="checkbox" id="${subTileList.code}_Edit" onclick="onCheckBoxChange(${subTileList.tileMasterId},'${subTileList.code}',this)" autocomplete="off" />
                                                                                <div class="btn-group">
                                                                                    <label for="${subTileList.code}_Edit" class="btn btn-default">
                                                                                        <span class="glyphicon glyphicon-ok"></span>
                                                                                        <span> </span>
                                                                                    </label>
                                                                                </div>
                                                                            </div>
                                                                            <div class="col-xs-2">
                                                                                <input type="checkbox" id="${subTileList.code}_Delete" onclick="onCheckBoxChange(${subTileList.tileMasterId},'${subTileList.code}',this)" autocomplete="off" />
                                                                                <div class="btn-group">
                                                                                    <label for="${subTileList.code}_Delete" class="btn btn-default">
                                                                                        <span class="glyphicon glyphicon-ok"></span>
                                                                                        <span> </span>
                                                                                    </label>
                                                                                </div>
                                                                            </div>
                                                                        </div>
                                                                    </c:if>
                                                                </c:forEach>
                                                            </div>
                                                        </div>
                                                    </div>

                                                    <!--fields of field group 4  -->

                                                    <!--fields of field group 5  -->

                                                    <div>
                                                    <div class="detailscont">
				  							 				
				    									</div>
                                                        <div class="form-group">
                                                            <div class="col-sm-6">
                                                                <div class="row" style="margin-bottom: 15px;">
                                                                    <div class="col-xs-6"><b>Bids</b></div>
                                                                    <div class="col-xs-2"><b>View</b></div>
                                                                    <div class="col-xs-2"><b>Edit</b></div>
                                                                    <div class="col-xs-2"><b>Delete</b></div>
                                                                </div>
                                                                <c:forEach var="subTileList" items="${subTileList}" varStatus="count">
                                                                    <c:if test="${subTileList.parentId== 5}">
                                                                        <div class="row">
                                                                            <div class="col-xs-6"><b>${subTileList.name}</b></div>
                                                                            <input type="text" style="display:none;" id="${subTileList.code}_roleAccessId" />
                                                                            <div class="col-xs-2">
                                                                                <input type="checkbox" id="${subTileList.code}_View" onclick="onCheckBoxChange(${subTileList.tileMasterId},'${subTileList.code}',this)" autocomplete="off" />
                                                                                <div class="btn-group">
                                                                                    <label for="${subTileList.code}_View" class="btn btn-default">
                                                                                        <span class="glyphicon glyphicon-ok"></span>
                                                                                        <span> </span>
                                                                                    </label>
                                                                                </div>
                                                                            </div>
                                                                            <div class="col-xs-2">
                                                                                <input type="checkbox" id="${subTileList.code}_Edit" onclick="onCheckBoxChange(${subTileList.tileMasterId},'${subTileList.code}',this)" autocomplete="off" />
                                                                                <div class="btn-group">
                                                                                    <label for="${subTileList.code}_Edit" class="btn btn-default">
                                                                                        <span class="glyphicon glyphicon-ok"></span>
                                                                                        <span> </span>
                                                                                    </label>
                                                                                </div>
                                                                            </div>
                                                                            <div class="col-xs-2">
                                                                                <input type="checkbox" id="${subTileList.code}_Delete" onclick="onCheckBoxChange(${subTileList.tileMasterId},'${subTileList.code}',this)" autocomplete="off" />
                                                                                <div class="btn-group">
                                                                                    <label for="${subTileList.code}_Delete" class="btn btn-default">
                                                                                        <span class="glyphicon glyphicon-ok"></span>
                                                                                        <span> </span>
                                                                                    </label>
                                                                                </div>
                                                                            </div>
                                                                        </div>
                                                                    </c:if>
                                                                </c:forEach>
                                                            </div>
                                                        </div>
                                                    </div>

                                                    <!--fields of field group 5  -->
                                                    <!--fields of field group 6  -->

                                                    <div>
                                                    <div class="detailscont">
				  							 				
				    									</div>
                                                        <div class="form-group">
                                                            <div class="col-sm-6">
                                                                <div class="row" style="margin-bottom: 15px;">
                                                                    <div class="col-xs-6"><b>Payments</b></div>
                                                                    <div class="col-xs-2"><b>View</b></div>
                                                                    <div class="col-xs-2"><b>Edit</b></div>
                                                                    <div class="col-xs-2"><b>Delete</b></div>
                                                                </div>
                                                                <c:forEach var="subTileList" items="${subTileList}" varStatus="count">
                                                                    <c:if test="${subTileList.parentId== 6}">
                                                                        <div class="row">
                                                                            <div class="col-xs-6"><b>${subTileList.name}</b></div>
                                                                            <input type="text" style="display:none;" id="${subTileList.code}_roleAccessId" />
                                                                            <div class="col-xs-2">
                                                                                <input type="checkbox" id="${subTileList.code}_View" onclick="onCheckBoxChange(${subTileList.tileMasterId},'${subTileList.code}',this)" autocomplete="off" />
                                                                                <div class="btn-group">
                                                                                    <label for="${subTileList.code}_View" class="btn btn-default">
                                                                                        <span class="glyphicon glyphicon-ok"></span>
                                                                                        <span> </span>
                                                                                    </label>
                                                                                </div>
                                                                            </div>
                                                                            <div class="col-xs-2">
                                                                                <input type="checkbox" id="${subTileList.code}_Edit" onclick="onCheckBoxChange(${subTileList.tileMasterId},'${subTileList.code}',this)" autocomplete="off" />
                                                                                <div class="btn-group">
                                                                                    <label for="${subTileList.code}_Edit" class="btn btn-default">
                                                                                        <span class="glyphicon glyphicon-ok"></span>
                                                                                        <span> </span>
                                                                                    </label>
                                                                                </div>
                                                                            </div>
                                                                            <div class="col-xs-2">
                                                                                <input type="checkbox" id="${subTileList.code}_Delete" onclick="onCheckBoxChange(${subTileList.tileMasterId},'${subTileList.code}',this)" autocomplete="off" />
                                                                                <div class="btn-group">
                                                                                    <label for="${subTileList.code}_Delete" class="btn btn-default">
                                                                                        <span class="glyphicon glyphicon-ok"></span>
                                                                                        <span> </span>
                                                                                    </label>
                                                                                </div>
                                                                            </div>
                                                                        </div>
                                                                    </c:if>
                                                                </c:forEach>
                                                            </div>
                                                        </div>
                                                    </div>

                                                    <!--fields of field group 6  -->
                                                    <!--fields of field group 7  -->

                                                    <div>
                                                    <div class="detailscont">
				  							 				
				    									</div>
                                                        <div class="form-group">
                                                            <div class="col-sm-6">
                                                                <div class="row" style="margin-bottom: 15px;">
                                                                    <div class="col-xs-6"><b>Reports</b></div>
                                                                    <div class="col-xs-2"><b>View</b></div>
                                                                    <div class="col-xs-2"><b>Edit</b></div>
                                                                    <div class="col-xs-2"><b>Delete</b></div>
                                                                </div>
                                                                <c:forEach var="subTileList" items="${subTileList}" varStatus="count">
                                                                    <c:if test="${subTileList.parentId== 7}">
                                                                        <div class="row">
                                                                            <div class="col-xs-6"><b>${subTileList.name}</b></div>
                                                                            <input type="text" style="display:none;" id="${subTileList.code}_roleAccessId" />
                                                                            <div class="col-xs-2">
                                                                                <input type="checkbox" id="${subTileList.code}_View" onclick="onCheckBoxChange(${subTileList.tileMasterId},'${subTileList.code}',this)" autocomplete="off" />
                                                                                <div class="btn-group">
                                                                                    <label for="${subTileList.code}_View" class="btn btn-default">
                                                                                        <span class="glyphicon glyphicon-ok"></span>
                                                                                        <span> </span>
                                                                                    </label>
                                                                                </div>
                                                                            </div>
                                                                            <div class="col-xs-2">
                                                                                <input type="checkbox" id="${subTileList.code}_Edit" onclick="onCheckBoxChange(${subTileList.tileMasterId},'${subTileList.code}',this)" autocomplete="off" />
                                                                                <div class="btn-group">
                                                                                    <label for="${subTileList.code}_Edit" class="btn btn-default">
                                                                                        <span class="glyphicon glyphicon-ok"></span>
                                                                                        <span> </span>
                                                                                    </label>
                                                                                </div>
                                                                            </div>
                                                                            <div class="col-xs-2">
                                                                                <input type="checkbox" id="${subTileList.code}_Delete" onclick="onCheckBoxChange(${subTileList.tileMasterId},'${subTileList.code}',this)" autocomplete="off" />
                                                                                <div class="btn-group">
                                                                                    <label for="${subTileList.code}_Delete" class="btn btn-default">
                                                                                        <span class="glyphicon glyphicon-ok"></span>
                                                                                        <span> </span>
                                                                                    </label>
                                                                                </div>
                                                                            </div>
                                                                        </div>
                                                                    </c:if>
                                                                </c:forEach>
                                                            </div>
                                                        </div>
                                                    </div>

                                                    <!--fields of field group 7  -->
                                                    <!--fields of field group 8  -->

                                                    <div>
                                                    <div class="detailscont">
				  							 				
				    									</div>
                                                        <div class="form-group">
                                                            <div class="col-sm-6">
                                                                <div class="row" style="margin-bottom: 15px;">
                                                                    <div class="col-xs-6"><b>Purchase Proposal</b></div>
                                                                    <div class="col-xs-2"><b>View</b></div>
                                                                    <div class="col-xs-2"><b>Edit</b></div>
                                                                    <div class="col-xs-2"><b>Delete</b></div>
                                                                </div>
                                                                <c:forEach var="subTileList" items="${subTileList}" varStatus="count">
                                                                    <c:if test="${subTileList.parentId== 8}">
                                                                        <div class="row">
                                                                            <div class="col-xs-6"><b>${subTileList.name}</b></div>
                                                                            <input type="text" style="display:none;" id="${subTileList.code}_roleAccessId" />
                                                                            <div class="col-xs-2">
                                                                                <input type="checkbox" id="${subTileList.code}_View" onclick="onCheckBoxChange(${subTileList.tileMasterId},'${subTileList.code}',this)" autocomplete="off" />
                                                                                <div class="btn-group">
                                                                                    <label for="${subTileList.code}_View" class="btn btn-default">
                                                                                        <span class="glyphicon glyphicon-ok"></span>
                                                                                        <span> </span>
                                                                                    </label>
                                                                                </div>
                                                                            </div>
                                                                            <div class="col-xs-2">
                                                                                <input type="checkbox" id="${subTileList.code}_Edit" onclick="onCheckBoxChange(${subTileList.tileMasterId},'${subTileList.code}',this)" autocomplete="off" />
                                                                                <div class="btn-group">
                                                                                    <label for="${subTileList.code}_Edit" class="btn btn-default">
                                                                                        <span class="glyphicon glyphicon-ok"></span>
                                                                                        <span> </span>
                                                                                    </label>
                                                                                </div>
                                                                            </div>
                                                                            <div class="col-xs-2">
                                                                                <input type="checkbox" id="${subTileList.code}_Delete" onclick="onCheckBoxChange(${subTileList.tileMasterId},'${subTileList.code}',this)" autocomplete="off" />
                                                                                <div class="btn-group">
                                                                                    <label for="${subTileList.code}_Delete" class="btn btn-default">
                                                                                        <span class="glyphicon glyphicon-ok"></span>
                                                                                        <span> </span>
                                                                                    </label>
                                                                                </div>
                                                                            </div>
                                                                        </div>
                                                                    </c:if>
                                                                </c:forEach>
                                                            </div>
                                                        </div>
                                                    </div>

                                                    <!--fields of field group 8  -->
                                                    <!--fields of field group 9  -->

                                                    <div>
                                                    <div class="detailscont">
				  							 				
				    									</div>
                                                        <div class="form-group">
                                                            <div class="col-sm-6">
                                                                <div class="row" style="margin-bottom: 15px;">
                                                                    <div class="col-xs-6"><b>Mail</b></div>
                                                                    <div class="col-xs-2"><b>View</b></div>
                                                                    <div class="col-xs-2"><b>Edit</b></div>
                                                                    <div class="col-xs-2"><b>Delete</b></div>
                                                                </div>
                                                                <c:forEach var="subTileList" items="${subTileList}" varStatus="count">
                                                                    <c:if test="${subTileList.parentId== 9}">
                                                                        <div class="row">
                                                                            <div class="col-xs-6"><b>${subTileList.name}</b></div>
                                                                            <input type="text" style="display:none;" id="${subTileList.code}_roleAccessId" />
                                                                            <div class="col-xs-2">
                                                                                <input type="checkbox" id="${subTileList.code}_View" onclick="onCheckBoxChange(${subTileList.tileMasterId},'${subTileList.code}',this)" autocomplete="off" />
                                                                                <div class="btn-group">
                                                                                    <label for="${subTileList.code}_View" class="btn btn-default">
                                                                                        <span class="glyphicon glyphicon-ok"></span>
                                                                                        <span> </span>
                                                                                    </label>
                                                                                </div>
                                                                            </div>
                                                                            <div class="col-xs-2">
                                                                                <input type="checkbox" id="${subTileList.code}_Edit" onclick="onCheckBoxChange(${subTileList.tileMasterId},'${subTileList.code}',this)" autocomplete="off" />
                                                                                <div class="btn-group">
                                                                                    <label for="${subTileList.code}_Edit" class="btn btn-default">
                                                                                        <span class="glyphicon glyphicon-ok"></span>
                                                                                        <span> </span>
                                                                                    </label>
                                                                                </div>
                                                                            </div>
                                                                            <div class="col-xs-2">
                                                                                <input type="checkbox" id="${subTileList.code}_Delete" onclick="onCheckBoxChange(${subTileList.tileMasterId},'${subTileList.code}',this)" autocomplete="off" />
                                                                                <div class="btn-group">
                                                                                    <label for="${subTileList.code}_Delete" class="btn btn-default">
                                                                                        <span class="glyphicon glyphicon-ok"></span>
                                                                                        <span> </span>
                                                                                    </label>
                                                                                </div>
                                                                            </div>
                                                                        </div>
                                                                    </c:if>
                                                                </c:forEach>
                                                            </div>
                                                        </div>
                                                    </div>

                                                    <!--fields of field group 9  -->
                                                    <!--fields of field group 10  -->

                                                    <div>
                                                    <div class="detailscont">
				  							 				
				    									</div>
                                                        <div class="form-group">
                                                            <div class="col-sm-6">
                                                                <div class="row" style="margin-bottom: 15px;">
                                                                    <div class="col-xs-6"><b>Role Details</b></div>
                                                                    <div class="col-xs-2"><b>View</b></div>
                                                                    <div class="col-xs-2"><b>Edit</b></div>
                                                                    <div class="col-xs-2"><b>Delete</b></div>
                                                                </div>
                                                                <c:forEach var="subTileList" items="${subTileList}" varStatus="count">
                                                                    <c:if test="${subTileList.parentId== 10}">
                                                                        <div class="row">
                                                                            <div class="col-xs-6"><b>${subTileList.name}</b></div>
                                                                            <input type="text" style="display:none;" id="${subTileList.code}_roleAccessId" />
                                                                            <div class="col-xs-2">
                                                                                <input type="checkbox" id="${subTileList.code}_View" onclick="onCheckBoxChange(${subTileList.tileMasterId},'${subTileList.code}',this)" autocomplete="off" />
                                                                                <div class="btn-group">
                                                                                    <label for="${subTileList.code}_View" class="btn btn-default">
                                                                                        <span class="glyphicon glyphicon-ok"></span>
                                                                                        <span> </span>
                                                                                    </label>
                                                                                </div>
                                                                            </div>
                                                                            <div class="col-xs-2">
                                                                                <input type="checkbox" id="${subTileList.code}_Edit" onclick="onCheckBoxChange(${subTileList.tileMasterId},'${subTileList.code}',this)" autocomplete="off" />
                                                                                <div class="btn-group">
                                                                                    <label for="${subTileList.code}_Edit" class="btn btn-default">
                                                                                        <span class="glyphicon glyphicon-ok"></span>
                                                                                        <span> </span>
                                                                                    </label>
                                                                                </div>
                                                                            </div>
                                                                            <div class="col-xs-2">
                                                                                <input type="checkbox" id="${subTileList.code}_Delete" onclick="onCheckBoxChange(${subTileList.tileMasterId},'${subTileList.code}',this)" autocomplete="off" />
                                                                                <div class="btn-group">
                                                                                    <label for="${subTileList.code}_Delete" class="btn btn-default">
                                                                                        <span class="glyphicon glyphicon-ok"></span>
                                                                                        <span> </span>
                                                                                    </label>
                                                                                </div>
                                                                            </div>
                                                                        </div>
                                                                    </c:if>
                                                                </c:forEach>
                                                            </div>
                                                        </div>
                                                    </div>

                                                    <!--fields of field group 10  -->
                                                     <!--fields of field group 11  -->
                                                    
                                                    <div>
                                                    <div class="detailscont">
				  							 				
				    									</div>
                                                        <div class="form-group">
                                                            <div class="col-sm-6">
                                                                <div class="row" style="margin-bottom: 15px;">
                                                                    <div class="col-xs-6"><b>Registration</b></div>
                                                                    <div class="col-xs-2"><b>View</b></div>
                                                                    <div class="col-xs-2"><b>Edit</b></div>
                                                                    <div class="col-xs-2"><b>Delete</b></div>
                                                                </div>
                                                                <c:forEach var="subTileList" items="${subTileList}" varStatus="count">
                                                                    <c:if test="${subTileList.parentId== 64}">
                                                                        <div class="row">
                                                                            <div class="col-xs-6"><b>${subTileList.name}</b></div>
                                                                            <input type="text" style="display:none;" id="${subTileList.code}_roleAccessId" />
                                                                            <div class="col-xs-2">
                                                                                <input type="checkbox" id="${subTileList.code}_View" onclick="onCheckBoxChange(${subTileList.tileMasterId},'${subTileList.code}',this)" autocomplete="off" />
                                                                                <div class="btn-group">
                                                                                    <label for="${subTileList.code}_View" class="btn btn-default">
                                                                                        <span class="glyphicon glyphicon-ok"></span>
                                                                                        <span> </span>
                                                                                    </label>
                                                                                </div>
                                                                            </div>
                                                                            <div class="col-xs-2">
                                                                                <input type="checkbox" id="${subTileList.code}_Edit" onclick="onCheckBoxChange(${subTileList.tileMasterId},'${subTileList.code}',this)" autocomplete="off" />
                                                                                <div class="btn-group">
                                                                                    <label for="${subTileList.code}_Edit" class="btn btn-default">
                                                                                        <span class="glyphicon glyphicon-ok"></span>
                                                                                        <span> </span>
                                                                                    </label>
                                                                                </div>
                                                                            </div>
                                                                            <div class="col-xs-2">
                                                                                <input type="checkbox" id="${subTileList.code}_Delete" onclick="onCheckBoxChange(${subTileList.tileMasterId},'${subTileList.code}',this)" autocomplete="off" />
                                                                                <div class="btn-group">
                                                                                    <label for="${subTileList.code}_Delete" class="btn btn-default">
                                                                                        <span class="glyphicon glyphicon-ok"></span>
                                                                                        <span> </span>
                                                                                    </label>
                                                                                </div>
                                                                            </div>
                                                                        </div>
                                                                    </c:if>
                                                                </c:forEach>
                                                            </div>
                                                        </div>
                                                    </div>

                                                    <!--fields of field group 11  -->
                                                    <!--fields of field group 12  -->
                                                    
                                                    <div>
                                                    <div class="detailscont">
				  							 				
				    									</div>
                                                        <div class="form-group">
                                                            <div class="col-sm-6">
                                                                <div class="row" style="margin-bottom: 15px;">
                                                                    <div class="col-xs-6"><b>Utilities</b></div>
                                                                    <div class="col-xs-2"><b>View</b></div>
                                                                    <div class="col-xs-2"><b>Edit</b></div>
                                                                    <div class="col-xs-2"><b>Delete</b></div>
                                                                </div>
                                                                <c:forEach var="subTileList" items="${subTileList}" varStatus="count">
                                                                    <c:if test="${subTileList.parentId== 65}">
                                                                        <div class="row">
                                                                            <div class="col-xs-6"><b>${subTileList.name}</b></div>
                                                                            <input type="text" style="display:none;" id="${subTileList.code}_roleAccessId" />
                                                                            <div class="col-xs-2">
                                                                                <input type="checkbox" id="${subTileList.code}_View" onclick="onCheckBoxChange(${subTileList.tileMasterId},'${subTileList.code}',this)" autocomplete="off" />
                                                                                <div class="btn-group">
                                                                                    <label for="${subTileList.code}_View" class="btn btn-default">
                                                                                        <span class="glyphicon glyphicon-ok"></span>
                                                                                        <span> </span>
                                                                                    </label>
                                                                                </div>
                                                                            </div>
                                                                            <div class="col-xs-2">
                                                                                <input type="checkbox" id="${subTileList.code}_Edit" onclick="onCheckBoxChange(${subTileList.tileMasterId},'${subTileList.code}',this)" autocomplete="off" />
                                                                                <div class="btn-group">
                                                                                    <label for="${subTileList.code}_Edit" class="btn btn-default">
                                                                                        <span class="glyphicon glyphicon-ok"></span>
                                                                                        <span> </span>
                                                                                    </label>
                                                                                </div>
                                                                            </div>
                                                                            <div class="col-xs-2">
                                                                                <input type="checkbox" id="${subTileList.code}_Delete" onclick="onCheckBoxChange(${subTileList.tileMasterId},'${subTileList.code}',this)" autocomplete="off" />
                                                                                <div class="btn-group">
                                                                                    <label for="${subTileList.code}_Delete" class="btn btn-default">
                                                                                        <span class="glyphicon glyphicon-ok"></span>
                                                                                        <span> </span>
                                                                                    </label>
                                                                                </div>
                                                                            </div>
                                                                        </div>
                                                                    </c:if>
                                                                </c:forEach>
                                                            </div>
                                                        </div>
                                                    </div>

                                                    <!--fields of field group 12  -->
                                                    <!--fields of field group 13  -->
                                                    
                                                    <div>
                                                    <div class="detailscont">
				  							 				
				    									</div>
                                                        <div class="form-group">
                                                            <div class="col-sm-6">
                                                                <div class="row" style="margin-bottom: 15px;">
                                                                    <div class="col-xs-6"><b>Certificate</b></div>
                                                                    <div class="col-xs-2"><b>View</b></div>
                                                                    <div class="col-xs-2"><b>Edit</b></div>
                                                                    <div class="col-xs-2"><b>Delete</b></div>
                                                                </div>
                                                                <c:forEach var="subTileList" items="${subTileList}" varStatus="count">
                                                                    <c:if test="${subTileList.parentId== 66}">
                                                                        <div class="row">
                                                                            <div class="col-xs-6"><b>${subTileList.name}</b></div>
                                                                            <input type="text" style="display:none;" id="${subTileList.code}_roleAccessId" />
                                                                            <div class="col-xs-2">
                                                                                <input type="checkbox" id="${subTileList.code}_View" onclick="onCheckBoxChange(${subTileList.tileMasterId},'${subTileList.code}',this)" autocomplete="off" />
                                                                                <div class="btn-group">
                                                                                    <label for="${subTileList.code}_View" class="btn btn-default">
                                                                                        <span class="glyphicon glyphicon-ok"></span>
                                                                                        <span> </span>
                                                                                    </label>
                                                                                </div>
                                                                            </div>
                                                                            <div class="col-xs-2">
                                                                                <input type="checkbox" id="${subTileList.code}_Edit" onclick="onCheckBoxChange(${subTileList.tileMasterId},'${subTileList.code}',this)" autocomplete="off" />
                                                                                <div class="btn-group">
                                                                                    <label for="${subTileList.code}_Edit" class="btn btn-default">
                                                                                        <span class="glyphicon glyphicon-ok"></span>
                                                                                        <span> </span>
                                                                                    </label>
                                                                                </div>
                                                                            </div>
                                                                            <div class="col-xs-2">
                                                                                <input type="checkbox" id="${subTileList.code}_Delete" onclick="onCheckBoxChange(${subTileList.tileMasterId},'${subTileList.code}',this)" autocomplete="off" />
                                                                                <div class="btn-group">
                                                                                    <label for="${subTileList.code}_Delete" class="btn btn-default">
                                                                                        <span class="glyphicon glyphicon-ok"></span>
                                                                                        <span> </span>
                                                                                    </label>
                                                                                </div>
                                                                            </div>
                                                                        </div>
                                                                    </c:if>
                                                                </c:forEach>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <!--fields of field group 13  -->
                                                    <!--fields of field group 14  -->
                                                    <div>
                                                    <div class="detailscont">
				  							 				
				    									</div>
                                                        <div class="form-group">
                                                            <div class="col-sm-6">
                                                                <div class="row" style="margin-bottom: 15px;">
                                                                    <div class="col-xs-6"><b>Payment Approval</b></div>
                                                                    <div class="col-xs-2"><b>View</b></div>
                                                                    <div class="col-xs-2"><b>Edit</b></div>
                                                                    <div class="col-xs-2"><b>Delete</b></div>
                                                                </div>
                                                                <c:forEach var="subTileList" items="${subTileList}" varStatus="count">
                                                                    <c:if test="${subTileList.parentId== 72}">
                                                                        <div class="row">
                                                                            <div class="col-xs-6"><b>${subTileList.name}</b></div>
                                                                            <input type="text" style="display:none;" id="${subTileList.code}_roleAccessId" />
                                                                            <div class="col-xs-2">
                                                                                <input type="checkbox" id="${subTileList.code}_View" onclick="onCheckBoxChange(${subTileList.tileMasterId},'${subTileList.code}',this)" autocomplete="off" />
                                                                                <div class="btn-group">
                                                                                    <label for="${subTileList.code}_View" class="btn btn-default">
                                                                                        <span class="glyphicon glyphicon-ok"></span>
                                                                                        <span> </span>
                                                                                    </label>
                                                                                </div>
                                                                            </div>
                                                                            <div class="col-xs-2">
                                                                                <input type="checkbox" id="${subTileList.code}_Edit" onclick="onCheckBoxChange(${subTileList.tileMasterId},'${subTileList.code}',this)" autocomplete="off" />
                                                                                <div class="btn-group">
                                                                                    <label for="${subTileList.code}_Edit" class="btn btn-default">
                                                                                        <span class="glyphicon glyphicon-ok"></span>
                                                                                        <span> </span>
                                                                                    </label>
                                                                                </div>
                                                                            </div>
                                                                            <div class="col-xs-2">
                                                                                <input type="checkbox" id="${subTileList.code}_Delete" onclick="onCheckBoxChange(${subTileList.tileMasterId},'${subTileList.code}',this)" autocomplete="off" />
                                                                                <div class="btn-group">
                                                                                    <label for="${subTileList.code}_Delete" class="btn btn-default">
                                                                                        <span class="glyphicon glyphicon-ok"></span>
                                                                                        <span> </span>
                                                                                    </label>
                                                                                </div>
                                                                            </div>
                                                                        </div>
                                                                    </c:if>
                                                                </c:forEach>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    
                                                     <div>
                                                    <div class="detailscont">
				  							 				
				    									</div>
                                                        <div class="form-group">
                                                            <div class="col-sm-6">
                                                                <div class="row" style="margin-bottom: 15px;">
                                                                    <div class="col-xs-6"><b>Session AUdit</b></div>
                                                                    <div class="col-xs-2"><b>View</b></div>
                                                                    <div class="col-xs-2"><b>Edit</b></div>
                                                                    <div class="col-xs-2"><b>Delete</b></div>
                                                                </div>
                                                                <c:forEach var="subTileList" items="${subTileList}" varStatus="count">
                                                                    <c:if test="${subTileList.parentId== 81}">
                                                                        <div class="row">
                                                                            <div class="col-xs-6"><b>${subTileList.name}</b></div>
                                                                            <input type="text" style="display:none;" id="${subTileList.code}_roleAccessId" />
                                                                            <div class="col-xs-2">
                                                                                <input type="checkbox" id="${subTileList.code}_View" onclick="onCheckBoxChange(${subTileList.tileMasterId},'${subTileList.code}',this)" autocomplete="off" />
                                                                                <div class="btn-group">
                                                                                    <label for="${subTileList.code}_View" class="btn btn-default">
                                                                                        <span class="glyphicon glyphicon-ok"></span>
                                                                                        <span> </span>
                                                                                    </label>
                                                                                </div>
                                                                            </div>
                                                                            <div class="col-xs-2">
                                                                                <input type="checkbox" id="${subTileList.code}_Edit" onclick="onCheckBoxChange(${subTileList.tileMasterId},'${subTileList.code}',this)" autocomplete="off" />
                                                                                <div class="btn-group">
                                                                                    <label for="${subTileList.code}_Edit" class="btn btn-default">
                                                                                        <span class="glyphicon glyphicon-ok"></span>
                                                                                        <span> </span>
                                                                                    </label>
                                                                                </div>
                                                                            </div>
                                                                            <div class="col-xs-2">
                                                                                <input type="checkbox" id="${subTileList.code}_Delete" onclick="onCheckBoxChange(${subTileList.tileMasterId},'${subTileList.code}',this)" autocomplete="off" />
                                                                                <div class="btn-group">
                                                                                    <label for="${subTileList.code}_Delete" class="btn btn-default">
                                                                                        <span class="glyphicon glyphicon-ok"></span>
                                                                                        <span> </span>
                                                                                    </label>
                                                                                </div>
                                                                            </div>
                                                                        </div>
                                                                    </c:if>
                                                                </c:forEach>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    
                                                    <div>
                                                    <div class="detailscont">
				  							 				
				    									</div>
                                                        <div class="form-group">
                                                            <div class="col-sm-6">
                                                                <div class="row" style="margin-bottom: 15px;">
                                                                    <div class="col-xs-6"><b>Approve Vendor</b></div>
                                                                    <div class="col-xs-2"><b>View</b></div>
                                                                     <div class="col-xs-2"><b>Edit</b></div>
                                                                    <div class="col-xs-2"><b>Delete</b></div>
                                                                </div>
                                                                <c:forEach var="subTileList" items="${subTileList}" varStatus="count">
                                                                    <c:if test="${subTileList.parentId== 101}">
                                                                        <div class="row">
                                                                            <div class="col-xs-6"><b>${subTileList.name}</b></div>
                                                                            <input type="text" style="display:none;" id="${subTileList.code}_roleAccessId" />
                                                                            <div class="col-xs-2">
                                                                                <input type="checkbox" id="${subTileList.code}_View" onclick="onCheckBoxChange(${subTileList.tileMasterId},'${subTileList.code}',this)" autocomplete="off" />
                                                                                <div class="btn-group">
                                                                                    <label for="${subTileList.code}_View" class="btn btn-default">
                                                                                        <span class="glyphicon glyphicon-ok"></span>
                                                                                        <span> </span>
                                                                                    </label>
                                                                                </div>
                                                                            </div>
                                                                            <div class="col-xs-2">
                                                                                <input type="checkbox" id="${subTileList.code}_Edit" onclick="onCheckBoxChange(${subTileList.tileMasterId},'${subTileList.code}',this)" autocomplete="off" />
                                                                                <div class="btn-group">
                                                                                    <label for="${subTileList.code}_Edit" class="btn btn-default">
                                                                                        <span class="glyphicon glyphicon-ok"></span>
                                                                                        <span> </span>
                                                                                    </label>
                                                                                </div>
                                                                            </div>
                                                                            <div class="col-xs-2">
                                                                                <input type="checkbox" id="${subTileList.code}_Delete" onclick="onCheckBoxChange(${subTileList.tileMasterId},'${subTileList.code}',this)" autocomplete="off" />
                                                                                <div class="btn-group">
                                                                                    <label for="${subTileList.code}_Delete" class="btn btn-default">
                                                                                        <span class="glyphicon glyphicon-ok"></span>
                                                                                        <span> </span>
                                                                                    </label>
                                                                                </div>
                                                                            </div>
                                                                        </div>
                                                                    </c:if>
                                                                </c:forEach>
                                                            </div>
                                                        </div>
                                                    </div>
 													<%-- <div>
                                                    <div class="detailscont">
				  							 				
				    									</div>
                                                        <div class="form-group">
                                                            <div class="col-sm-6">
                                                                <div class="row" style="margin-bottom: 15px;">
                                                                    <div class="col-xs-6"><b>Infra Approval</b></div>
                                                                    <div class="col-xs-2"><b>View</b></div>
                                                                     <div class="col-xs-2"><b>Edit</b></div>
                                                                    <div class="col-xs-2"><b>Delete</b></div>
                                                                </div>
                                                                <c:forEach var="subTileList" items="${subTileList}" varStatus="count">
                                                                    <c:if test="${subTileList.parentId== 130}">
                                                                        <div class="row">
                                                                            <div class="col-xs-6"><b>${subTileList.name}</b></div>
                                                                            <input type="text" style="display:none;" id="${subTileList.code}_roleAccessId" />
                                                                            <div class="col-xs-2">
                                                                                <input type="checkbox" id="${subTileList.code}_View" onclick="onCheckBoxChange(${subTileList.tileMasterId},'${subTileList.code}',this)" autocomplete="off" />
                                                                                <div class="btn-group">
                                                                                    <label for="${subTileList.code}_View" class="btn btn-default">
                                                                                        <span class="glyphicon glyphicon-ok"></span>
                                                                                        <span> </span>
                                                                                    </label>
                                                                                </div>
                                                                            </div>
                                                                            <div class="col-xs-2">
                                                                                <input type="checkbox" id="${subTileList.code}_Edit" onclick="onCheckBoxChange(${subTileList.tileMasterId},'${subTileList.code}',this)" autocomplete="off" />
                                                                                <div class="btn-group">
                                                                                    <label for="${subTileList.code}_Edit" class="btn btn-default">
                                                                                        <span class="glyphicon glyphicon-ok"></span>
                                                                                        <span> </span>
                                                                                    </label>
                                                                                </div>
                                                                            </div>
                                                                            <div class="col-xs-2">
                                                                                <input type="checkbox" id="${subTileList.code}_Delete" onclick="onCheckBoxChange(${subTileList.tileMasterId},'${subTileList.code}',this)" autocomplete="off" />
                                                                                <div class="btn-group">
                                                                                    <label for="${subTileList.code}_Delete" class="btn btn-default">
                                                                                        <span class="glyphicon glyphicon-ok"></span>
                                                                                        <span> </span>
                                                                                    </label>
                                                                                </div>
                                                                            </div>
                                                                        </div>
                                                                    </c:if>
                                                                </c:forEach>
                                                            </div>
                                                        </div>
                                                    </div> --%>
                                                     <div>
                                                    <div class="detailscont">
				  							 				
				    									</div>
                                                        <div class="form-group">
                                                            <div class="col-sm-6">
                                                                <div class="row" style="margin-bottom: 15px;">
                                                                    <div class="col-xs-6"><b>Partner </b></div>
                                                                    <div class="col-xs-2"><b>View</b></div>
                                                                     <div class="col-xs-2"><b>Edit</b></div>
                                                                    <div class="col-xs-2"><b>Delete</b></div>
                                                                </div>
                                                                <c:forEach var="subTileList" items="${subTileList}" varStatus="count">
                                                                    <c:if test="${subTileList.parentId== 114}">
                                                                        <div class="row">
                                                                            <div class="col-xs-6"><b>${subTileList.name}</b></div>
                                                                            <input type="text" style="display:none;" id="${subTileList.code}_roleAccessId" />
                                                                            <div class="col-xs-2">
                                                                                <input type="checkbox" id="${subTileList.code}_View" onclick="onCheckBoxChange(${subTileList.tileMasterId},'${subTileList.code}',this)" autocomplete="off" />
                                                                                <div class="btn-group">
                                                                                    <label for="${subTileList.code}_View" class="btn btn-default">
                                                                                        <span class="glyphicon glyphicon-ok"></span>
                                                                                        <span> </span>
                                                                                    </label>
                                                                                </div>
                                                                            </div>
                                                                            <div class="col-xs-2">
                                                                                <input type="checkbox" id="${subTileList.code}_Edit" onclick="onCheckBoxChange(${subTileList.tileMasterId},'${subTileList.code}',this)" autocomplete="off" />
                                                                                <div class="btn-group">
                                                                                    <label for="${subTileList.code}_Edit" class="btn btn-default">
                                                                                        <span class="glyphicon glyphicon-ok"></span>
                                                                                        <span> </span>
                                                                                    </label>
                                                                                </div>
                                                                            </div>
                                                                            <div class="col-xs-2">
                                                                                <input type="checkbox" id="${subTileList.code}_Delete" onclick="onCheckBoxChange(${subTileList.tileMasterId},'${subTileList.code}',this)" autocomplete="off" />
                                                                                <div class="btn-group">
                                                                                    <label for="${subTileList.code}_Delete" class="btn btn-default">
                                                                                        <span class="glyphicon glyphicon-ok"></span>
                                                                                        <span> </span>
                                                                                    </label>
                                                                                </div>
                                                                            </div>
                                                                        </div>
                                                                    </c:if>
                                                                </c:forEach>
                                                            </div>
                                                        </div>
                                                    </div>
                                                     
                                                     <div>
                                                    <div class="detailscont">
				  							 				
				    									</div>
                                                        <div class="form-group">
                                                            <div class="col-sm-6">
                                                                <div class="row" style="margin-bottom: 15px;">
                                                                    <div class="col-xs-6"><b>Payments Approval</b></div>
                                                                    <div class="col-xs-2"><b>View</b></div>
                                                                     <div class="col-xs-2"><b>Edit</b></div>
                                                                    <div class="col-xs-2"><b>Delete</b></div>
                                                                </div>
                                                                <c:forEach var="subTileList" items="${subTileList}" varStatus="count">
                                                                    <c:if test="${subTileList.parentId== 118}">
                                                                        <div class="row">
                                                                            <div class="col-xs-6"><b>${subTileList.name}</b></div>
                                                                            <input type="text" style="display:none;" id="${subTileList.code}_roleAccessId" />
                                                                            <div class="col-xs-2">
                                                                                <input type="checkbox" id="${subTileList.code}_View" onclick="onCheckBoxChange(${subTileList.tileMasterId},'${subTileList.code}',this)" autocomplete="off" />
                                                                                <div class="btn-group">
                                                                                    <label for="${subTileList.code}_View" class="btn btn-default">
                                                                                        <span class="glyphicon glyphicon-ok"></span>
                                                                                        <span> </span>
                                                                                    </label>
                                                                                </div>
                                                                            </div>
                                                                            <div class="col-xs-2">
                                                                                <input type="checkbox" id="${subTileList.code}_Edit" onclick="onCheckBoxChange(${subTileList.tileMasterId},'${subTileList.code}',this)" autocomplete="off" />
                                                                                <div class="btn-group">
                                                                                    <label for="${subTileList.code}_Edit" class="btn btn-default">
                                                                                        <span class="glyphicon glyphicon-ok"></span>
                                                                                        <span> </span>
                                                                                    </label>
                                                                                </div>
                                                                            </div>
                                                                            <div class="col-xs-2">
                                                                                <input type="checkbox" id="${subTileList.code}_Delete" onclick="onCheckBoxChange(${subTileList.tileMasterId},'${subTileList.code}',this)" autocomplete="off" />
                                                                                <div class="btn-group">
                                                                                    <label for="${subTileList.code}_Delete" class="btn btn-default">
                                                                                        <span class="glyphicon glyphicon-ok"></span>
                                                                                        <span> </span>
                                                                                    </label>
                                                                                </div>
                                                                            </div>
                                                                        </div>
                                                                    </c:if>
                                                                </c:forEach>
                                                            </div>
                                                        </div>
                                                    </div>
                                                     <div>
                                                    <div class="detailscont">
				  							 				
				    									</div>
                                                        <div class="form-group">
                                                            <div class="col-sm-6">
                                                                <div class="row" style="margin-bottom: 15px;">
                                                                    <div class="col-xs-6"><b>Invite Participation</b></div>
                                                                    <div class="col-xs-2"><b>View</b></div>
                                                                     <div class="col-xs-2"><b>Edit</b></div>
                                                                    <div class="col-xs-2"><b>Delete</b></div>
                                                                </div>
                                                                <c:forEach var="subTileList" items="${subTileList}" varStatus="count">
                                                                    <c:if test="${subTileList.parentId== 120}">
                                                                        <div class="row">
                                                                            <div class="col-xs-6"><b>${subTileList.name}</b></div>
                                                                            <input type="text" style="display:none;" id="${subTileList.code}_roleAccessId" />
                                                                            <div class="col-xs-2">
                                                                                <input type="checkbox" id="${subTileList.code}_View" onclick="onCheckBoxChange(${subTileList.tileMasterId},'${subTileList.code}',this)" autocomplete="off" />
                                                                                <div class="btn-group">
                                                                                    <label for="${subTileList.code}_View" class="btn btn-default">
                                                                                        <span class="glyphicon glyphicon-ok"></span>
                                                                                        <span> </span>
                                                                                    </label>
                                                                                </div>
                                                                            </div>
                                                                            <div class="col-xs-2">
                                                                                <input type="checkbox" id="${subTileList.code}_Edit" onclick="onCheckBoxChange(${subTileList.tileMasterId},'${subTileList.code}',this)" autocomplete="off" />
                                                                                <div class="btn-group">
                                                                                    <label for="${subTileList.code}_Edit" class="btn btn-default">
                                                                                        <span class="glyphicon glyphicon-ok"></span>
                                                                                        <span> </span>
                                                                                    </label>
                                                                                </div>
                                                                            </div>
                                                                            <div class="col-xs-2">
                                                                                <input type="checkbox" id="${subTileList.code}_Delete" onclick="onCheckBoxChange(${subTileList.tileMasterId},'${subTileList.code}',this)" autocomplete="off" />
                                                                                <div class="btn-group">
                                                                                    <label for="${subTileList.code}_Delete" class="btn btn-default">
                                                                                        <span class="glyphicon glyphicon-ok"></span>
                                                                                        <span> </span>
                                                                                    </label>
                                                                                </div>
                                                                            </div>
                                                                        </div>
                                                                    </c:if>
                                                                </c:forEach>
                                                            </div>
                                                        </div>
                                                    </div>
                                                     <div>
                                                    <div class="detailscont">
				  							 				
				    									</div>
                                                        <div class="form-group">
                                                            <div class="col-sm-6">
                                                                <div class="row" style="margin-bottom: 15px;">
                                                                    <div class="col-xs-6"><b>Pending Request</b></div>
                                                                    <div class="col-xs-2"><b>View</b></div>
                                                                     <div class="col-xs-2"><b>Edit</b></div>
                                                                    <div class="col-xs-2"><b>Delete</b></div>
                                                                </div>
                                                                <c:forEach var="subTileList" items="${subTileList}" varStatus="count">
                                                                    <c:if test="${subTileList.parentId== 122}">
                                                                        <div class="row">
                                                                            <div class="col-xs-6"><b>${subTileList.name}</b></div>
                                                                            <input type="text" style="display:none;" id="${subTileList.code}_roleAccessId" />
                                                                            <div class="col-xs-2">
                                                                                <input type="checkbox" id="${subTileList.code}_View" onclick="onCheckBoxChange(${subTileList.tileMasterId},'${subTileList.code}',this)" autocomplete="off" />
                                                                                <div class="btn-group">
                                                                                    <label for="${subTileList.code}_View" class="btn btn-default">
                                                                                        <span class="glyphicon glyphicon-ok"></span>
                                                                                        <span> </span>
                                                                                    </label>
                                                                                </div>
                                                                            </div>
                                                                            <div class="col-xs-2">
                                                                                <input type="checkbox" id="${subTileList.code}_Edit" onclick="onCheckBoxChange(${subTileList.tileMasterId},'${subTileList.code}',this)" autocomplete="off" />
                                                                                <div class="btn-group">
                                                                                    <label for="${subTileList.code}_Edit" class="btn btn-default">
                                                                                        <span class="glyphicon glyphicon-ok"></span>
                                                                                        <span> </span>
                                                                                    </label>
                                                                                </div>
                                                                            </div>
                                                                            <div class="col-xs-2">
                                                                                <input type="checkbox" id="${subTileList.code}_Delete" onclick="onCheckBoxChange(${subTileList.tileMasterId},'${subTileList.code}',this)" autocomplete="off" />
                                                                                <div class="btn-group">
                                                                                    <label for="${subTileList.code}_Delete" class="btn btn-default">
                                                                                        <span class="glyphicon glyphicon-ok"></span>
                                                                                        <span> </span>
                                                                                    </label>
                                                                                </div>
                                                                            </div>
                                                                        </div>
                                                                    </c:if>
                                                                </c:forEach>
                                                            </div>
                                                        </div>
                                                    </div>
                                                     <div>
                                                    <div class="detailscont">
				  							 				
				    									</div>
                                                        <div class="form-group">
                                                            <div class="col-sm-6">
                                                                <div class="row" style="margin-bottom: 15px;">
                                                                    <div class="col-xs-6"><b>Workflows</b></div>
                                                                    <div class="col-xs-2"><b>View</b></div>
                                                                     <div class="col-xs-2"><b>Edit</b></div>
                                                                    <div class="col-xs-2"><b>Delete</b></div>
                                                                </div>
                                                                <c:forEach var="subTileList" items="${subTileList}" varStatus="count">
                                                                    <c:if test="${subTileList.parentId== 124}">
                                                                        <div class="row">
                                                                            <div class="col-xs-6"><b>${subTileList.name}</b></div>
                                                                            <input type="text" style="display:none;" id="${subTileList.code}_roleAccessId" />
                                                                            <div class="col-xs-2">
                                                                                <input type="checkbox" id="${subTileList.code}_View" onclick="onCheckBoxChange(${subTileList.tileMasterId},'${subTileList.code}',this)" autocomplete="off" />
                                                                                <div class="btn-group">
                                                                                    <label for="${subTileList.code}_View" class="btn btn-default">
                                                                                        <span class="glyphicon glyphicon-ok"></span>
                                                                                        <span> </span>
                                                                                    </label>
                                                                                </div>
                                                                            </div>
                                                                            <div class="col-xs-2">
                                                                                <input type="checkbox" id="${subTileList.code}_Edit" onclick="onCheckBoxChange(${subTileList.tileMasterId},'${subTileList.code}',this)" autocomplete="off" />
                                                                                <div class="btn-group">
                                                                                    <label for="${subTileList.code}_Edit" class="btn btn-default">
                                                                                        <span class="glyphicon glyphicon-ok"></span>
                                                                                        <span> </span>
                                                                                    </label>
                                                                                </div>
                                                                            </div>
                                                                            <div class="col-xs-2">
                                                                                <input type="checkbox" id="${subTileList.code}_Delete" onclick="onCheckBoxChange(${subTileList.tileMasterId},'${subTileList.code}',this)" autocomplete="off" />
                                                                                <div class="btn-group">
                                                                                    <label for="${subTileList.code}_Delete" class="btn btn-default">
                                                                                        <span class="glyphicon glyphicon-ok"></span>
                                                                                        <span> </span>
                                                                                    </label>
                                                                                </div>
                                                                            </div>
                                                                        </div>
                                                                    </c:if>
                                                                </c:forEach>
                                                            </div>
                                                        </div>
                                                    </div>
                                                     <div>
                                                    <div class="detailscont">
				  							 				
				    									</div>
                                                        <div class="form-group">
                                                            <div class="col-sm-6">
                                                                <div class="row" style="margin-bottom: 15px;">
                                                                    <div class="col-xs-6"><b>Payment Posting</b></div>
                                                                    <div class="col-xs-2"><b>View</b></div>
                                                                     <div class="col-xs-2"><b>Edit</b></div>
                                                                    <div class="col-xs-2"><b>Delete</b></div>
                                                                </div>
                                                                <c:forEach var="subTileList" items="${subTileList}" varStatus="count">
                                                                    <c:if test="${subTileList.parentId== 126}">
                                                                        <div class="row">
                                                                            <div class="col-xs-6"><b>${subTileList.name}</b></div>
                                                                            <input type="text" style="display:none;" id="${subTileList.code}_roleAccessId" />
                                                                            <div class="col-xs-2">
                                                                                <input type="checkbox" id="${subTileList.code}_View" onclick="onCheckBoxChange(${subTileList.tileMasterId},'${subTileList.code}',this)" autocomplete="off" />
                                                                                <div class="btn-group">
                                                                                    <label for="${subTileList.code}_View" class="btn btn-default">
                                                                                        <span class="glyphicon glyphicon-ok"></span>
                                                                                        <span> </span>
                                                                                    </label>
                                                                                </div>
                                                                            </div>
                                                                            <div class="col-xs-2">
                                                                                <input type="checkbox" id="${subTileList.code}_Edit" onclick="onCheckBoxChange(${subTileList.tileMasterId},'${subTileList.code}',this)" autocomplete="off" />
                                                                                <div class="btn-group">
                                                                                    <label for="${subTileList.code}_Edit" class="btn btn-default">
                                                                                        <span class="glyphicon glyphicon-ok"></span>
                                                                                        <span> </span>
                                                                                    </label>
                                                                                </div>
                                                                            </div>
                                                                            <div class="col-xs-2">
                                                                                <input type="checkbox" id="${subTileList.code}_Delete" onclick="onCheckBoxChange(${subTileList.tileMasterId},'${subTileList.code}',this)" autocomplete="off" />
                                                                                <div class="btn-group">
                                                                                    <label for="${subTileList.code}_Delete" class="btn btn-default">
                                                                                        <span class="glyphicon glyphicon-ok"></span>
                                                                                        <span> </span>
                                                                                    </label>
                                                                                </div>
                                                                            </div>
                                                                        </div>
                                                                    </c:if>
                                                                </c:forEach>
                                                            </div>
                                                        </div>
                                                    </div>
                                                     <div>
                                                    <div class="detailscont">
				  							 				
				    									</div>
                                                        <div class="form-group">
                                                            <div class="col-sm-6">
                                                                <div class="row" style="margin-bottom: 15px;">
                                                                    <div class="col-xs-6"><b>Create Contact</b></div>
                                                                    <div class="col-xs-2"><b>View</b></div>
                                                                     <div class="col-xs-2"><b>Edit</b></div>
                                                                    <div class="col-xs-2"><b>Delete</b></div>
                                                                </div>
                                                                <c:forEach var="subTileList" items="${subTileList}" varStatus="count">
                                                                    <c:if test="${subTileList.parentId== 128}">
                                                                        <div class="row">
                                                                            <div class="col-xs-6"><b>${subTileList.name}</b></div>
                                                                            <input type="text" style="display:none;" id="${subTileList.code}_roleAccessId" />
                                                                            <div class="col-xs-2">
                                                                                <input type="checkbox" id="${subTileList.code}_View" onclick="onCheckBoxChange(${subTileList.tileMasterId},'${subTileList.code}',this)" autocomplete="off" />
                                                                                <div class="btn-group">
                                                                                    <label for="${subTileList.code}_View" class="btn btn-default">
                                                                                        <span class="glyphicon glyphicon-ok"></span>
                                                                                        <span> </span>
                                                                                    </label>
                                                                                </div>
                                                                            </div>
                                                                            <div class="col-xs-2">
                                                                                <input type="checkbox" id="${subTileList.code}_Edit" onclick="onCheckBoxChange(${subTileList.tileMasterId},'${subTileList.code}',this)" autocomplete="off" />
                                                                                <div class="btn-group">
                                                                                    <label for="${subTileList.code}_Edit" class="btn btn-default">
                                                                                        <span class="glyphicon glyphicon-ok"></span>
                                                                                        <span> </span>
                                                                                    </label>
                                                                                </div>
                                                                            </div>
                                                                            <div class="col-xs-2">
                                                                                <input type="checkbox" id="${subTileList.code}_Delete" onclick="onCheckBoxChange(${subTileList.tileMasterId},'${subTileList.code}',this)" autocomplete="off" />
                                                                                <div class="btn-group">
                                                                                    <label for="${subTileList.code}_Delete" class="btn btn-default">
                                                                                        <span class="glyphicon glyphicon-ok"></span>
                                                                                        <span> </span>
                                                                                    </label>
                                                                                </div>
                                                                            </div>
                                                                        </div>
                                                                    </c:if>
                                                                </c:forEach>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div>
                                                    <div class="detailscont">
				  							 				
				    									</div>
                                                        <div class="form-group">
                                                            <div class="col-sm-6">
                                                                <div class="row" style="margin-bottom: 15px;">
                                                                    <div class="col-xs-6"><b>Infra Approval</b></div>
                                                                    <div class="col-xs-2"><b>View</b></div>
                                                                     <div class="col-xs-2"><b>Edit</b></div>
                                                                    <div class="col-xs-2"><b>Delete</b></div>
                                                                </div>
                                                                <c:forEach var="subTileList" items="${subTileList}" varStatus="count">
                                                                    <c:if test="${subTileList.parentId== 130}">
                                                                        <div class="row">
                                                                            <div class="col-xs-6"><b>${subTileList.name}</b></div>
                                                                            <input type="text" style="display:none;" id="${subTileList.code}_roleAccessId" />
                                                                            <div class="col-xs-2">
                                                                                <input type="checkbox" id="${subTileList.code}_View" onclick="onCheckBoxChange(${subTileList.tileMasterId},'${subTileList.code}',this)" autocomplete="off" />
                                                                                <div class="btn-group">
                                                                                    <label for="${subTileList.code}_View" class="btn btn-default">
                                                                                        <span class="glyphicon glyphicon-ok"></span>
                                                                                        <span> </span>
                                                                                    </label>
                                                                                </div>
                                                                            </div>
                                                                            <div class="col-xs-2">
                                                                                <input type="checkbox" id="${subTileList.code}_Edit" onclick="onCheckBoxChange(${subTileList.tileMasterId},'${subTileList.code}',this)" autocomplete="off" />
                                                                                <div class="btn-group">
                                                                                    <label for="${subTileList.code}_Edit" class="btn btn-default">
                                                                                        <span class="glyphicon glyphicon-ok"></span>
                                                                                        <span> </span>
                                                                                    </label>
                                                                                </div>
                                                                            </div>
                                                                            <div class="col-xs-2">
                                                                                <input type="checkbox" id="${subTileList.code}_Delete" onclick="onCheckBoxChange(${subTileList.tileMasterId},'${subTileList.code}',this)" autocomplete="off" />
                                                                                <div class="btn-group">
                                                                                    <label for="${subTileList.code}_Delete" class="btn btn-default">
                                                                                        <span class="glyphicon glyphicon-ok"></span>
                                                                                        <span> </span>
                                                                                    </label>
                                                                                </div>
                                                                            </div>
                                                                        </div>
                                                                    </c:if>
                                                                </c:forEach>
                                                            </div>
                                                        </div>
                                                    </div>
                                                     <!--fields of field group 13  -->
                                                     <!--fields of field group 14 -->
                                                      <div>
                                                    <div class="detailscont">
				  							 				
				    									</div>
                                                        <div class="form-group">
                                                            <div class="col-sm-6">
                                                                <div class="row" style="margin-bottom: 15px;">
                                                                    <div class="col-xs-6"><b>Quick Rfq</b></div>
                                                                    <div class="col-xs-2"><b>View</b></div>
                                                                     <div class="col-xs-2"><b>Edit</b></div>
                                                                    <div class="col-xs-2"><b>Delete</b></div>
                                                                </div>
                                                                <c:forEach var="subTileList" items="${subTileList}" varStatus="count">
                                                                    <c:if test="${subTileList.parentId== 132}">
                                                                        <div class="row">
                                                                            <div class="col-xs-6"><b>${subTileList.name}</b></div>
                                                                            <input type="text" style="display:none;" id="${subTileList.code}_roleAccessId" />
                                                                            <div class="col-xs-2">
                                                                                <input type="checkbox" id="${subTileList.code}_View" onclick="onCheckBoxChange(${subTileList.tileMasterId},'${subTileList.code}',this)" autocomplete="off" />
                                                                                <div class="btn-group">
                                                                                    <label for="${subTileList.code}_View" class="btn btn-default">
                                                                                        <span class="glyphicon glyphicon-ok"></span>
                                                                                        <span> </span>
                                                                                    </label>
                                                                                </div>
                                                                            </div>
                                                                            <div class="col-xs-2">
                                                                                <input type="checkbox" id="${subTileList.code}_Edit" onclick="onCheckBoxChange(${subTileList.tileMasterId},'${subTileList.code}',this)" autocomplete="off" />
                                                                                <div class="btn-group">
                                                                                    <label for="${subTileList.code}_Edit" class="btn btn-default">
                                                                                        <span class="glyphicon glyphicon-ok"></span>
                                                                                        <span> </span>
                                                                                    </label>
                                                                                </div>
                                                                            </div>
                                                                            <div class="col-xs-2">
                                                                                <input type="checkbox" id="${subTileList.code}_Delete" onclick="onCheckBoxChange(${subTileList.tileMasterId},'${subTileList.code}',this)" autocomplete="off" />
                                                                                <div class="btn-group">
                                                                                    <label for="${subTileList.code}_Delete" class="btn btn-default">
                                                                                        <span class="glyphicon glyphicon-ok"></span>
                                                                                        <span> </span>
                                                                                    </label>
                                                                                </div>
                                                                            </div>
                                                                        </div>
                                                                    </c:if>
                                                                </c:forEach>
                                                            </div>
                                                        </div>
                                                    </div>
   													<!--fields of field group 15 -->
                                                      <div>
                                                    <div class="detailscont">
				  							 				
				    									</div>
                                                        <div class="form-group">
                                                            <div class="col-sm-6">
                                                                <div class="row" style="margin-bottom: 15px;">
                                                                    <div class="col-xs-6"><b> Rfq</b></div>
                                                                    <div class="col-xs-2"><b>View</b></div>
                                                                     <div class="col-xs-2"><b>Edit</b></div>
                                                                    <div class="col-xs-2"><b>Delete</b></div>
                                                                </div>
                                                                <c:forEach var="subTileList" items="${subTileList}" varStatus="count">
                                                                    <c:if test="${subTileList.parentId== 138}">
                                                                        <div class="row">
                                                                            <div class="col-xs-6"><b>${subTileList.name}</b></div>
                                                                            <input type="text" style="display:none;" id="${subTileList.code}_roleAccessId" />
                                                                            <div class="col-xs-2">
                                                                                <input type="checkbox" id="${subTileList.code}_View" onclick="onCheckBoxChange(${subTileList.tileMasterId},'${subTileList.code}',this)" autocomplete="off" />
                                                                                <div class="btn-group">
                                                                                    <label for="${subTileList.code}_View" class="btn btn-default">
                                                                                        <span class="glyphicon glyphicon-ok"></span>
                                                                                        <span> </span>
                                                                                    </label>
                                                                                </div>
                                                                            </div>
                                                                            <div class="col-xs-2">
                                                                                <input type="checkbox" id="${subTileList.code}_Edit" onclick="onCheckBoxChange(${subTileList.tileMasterId},'${subTileList.code}',this)" autocomplete="off" />
                                                                                <div class="btn-group">
                                                                                    <label for="${subTileList.code}_Edit" class="btn btn-default">
                                                                                        <span class="glyphicon glyphicon-ok"></span>
                                                                                        <span> </span>
                                                                                    </label>
                                                                                </div>
                                                                            </div>
                                                                            <div class="col-xs-2">
                                                                                <input type="checkbox" id="${subTileList.code}_Delete" onclick="onCheckBoxChange(${subTileList.tileMasterId},'${subTileList.code}',this)" autocomplete="off" />
                                                                                <div class="btn-group">
                                                                                    <label for="${subTileList.code}_Delete" class="btn btn-default">
                                                                                        <span class="glyphicon glyphicon-ok"></span>
                                                                                        <span> </span>
                                                                                    </label>
                                                                                </div>
                                                                            </div>
                                                                        </div>
                                                                    </c:if>
                                                                </c:forEach>
                                                            </div>
                                                        </div>
                                                    </div>
                                                       <!--fields of field group 16 -->
                                                      <div>
                                                    <div class="detailscont">
				  							 				
				    									</div>
                                                        <div class="form-group">
                                                            <div class="col-sm-6">
                                                                <div class="row" style="margin-bottom: 15px;">
                                                                    <div class="col-xs-6"><b>Quick Auction</b></div>
                                                                    <div class="col-xs-2"><b>View</b></div>
                                                                     <div class="col-xs-2"><b>Edit</b></div>
                                                                    <div class="col-xs-2"><b>Delete</b></div>
                                                                </div>
                                                                <c:forEach var="subTileList" items="${subTileList}" varStatus="count">
                                                                    <c:if test="${subTileList.parentId== 144}">
                                                                        <div class="row">
                                                                            <div class="col-xs-6"><b>${subTileList.name}</b></div>
                                                                            <input type="text" style="display:none;" id="${subTileList.code}_roleAccessId" />
                                                                            <div class="col-xs-2">
                                                                                <input type="checkbox" id="${subTileList.code}_View" onclick="onCheckBoxChange(${subTileList.tileMasterId},'${subTileList.code}',this)" autocomplete="off" />
                                                                                <div class="btn-group">
                                                                                    <label for="${subTileList.code}_View" class="btn btn-default">
                                                                                        <span class="glyphicon glyphicon-ok"></span>
                                                                                        <span> </span>
                                                                                    </label>
                                                                                </div>
                                                                            </div>
                                                                            <div class="col-xs-2">
                                                                                <input type="checkbox" id="${subTileList.code}_Edit" onclick="onCheckBoxChange(${subTileList.tileMasterId},'${subTileList.code}',this)" autocomplete="off" />
                                                                                <div class="btn-group">
                                                                                    <label for="${subTileList.code}_Edit" class="btn btn-default">
                                                                                        <span class="glyphicon glyphicon-ok"></span>
                                                                                        <span> </span>
                                                                                    </label>
                                                                                </div>
                                                                            </div>
                                                                            <div class="col-xs-2">
                                                                                <input type="checkbox" id="${subTileList.code}_Delete" onclick="onCheckBoxChange(${subTileList.tileMasterId},'${subTileList.code}',this)" autocomplete="off" />
                                                                                <div class="btn-group">
                                                                                    <label for="${subTileList.code}_Delete" class="btn btn-default">
                                                                                        <span class="glyphicon glyphicon-ok"></span>
                                                                                        <span> </span>
                                                                                    </label>
                                                                                </div>
                                                                            </div>
                                                                        </div>
                                                                    </c:if>
                                                                </c:forEach>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="col-sm-12 text-center" style="margin-bottom:5px;">
                                                    <button class="btn btn-default save indirectFormSubmit">Save</button>
                                                    <button class="btn btn-default cancel" onclick="cancel()">Cancel</button>
                                                </div>
                                                <div class="clearfix"></div>
                                            </div>
                                            <!-- Master tab end-->

                                        </div>
                                    </div>
                                    <!-- right-side end-->
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
