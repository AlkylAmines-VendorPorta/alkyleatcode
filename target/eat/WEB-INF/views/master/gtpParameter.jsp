<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@taglib uri = "http://www.springframework.org/tags/form" prefix = "sf"%>
<%@taglib uri = "http://www.springframework.org/tags/form" prefix = "sf"%>
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/${appMode}/tilescommon/css/fioristyle.css?appVer=${appVer}"/>
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/${appMode}/tilescommon/css/mobile.css?appVer=${appVer}"/>

     <%-- <div class="zoom">
        <a class="zoom-fab zoom-btn-large" id="zoomBtn"><i class="fa fa-bars"></i></a>
        <div class="zoom-menu">
           <a  id="refreshId" class="zoom-fab zoom-btn-sm zoom-btn-doc scale-transition"><i  class="fa fa-refresh" aria-hidden="true"></i></a>
             <c:if test="${access.modifyAccess== 'Y'}">
            <a  id="addId" class="zoom-fab zoom-btn-sm zoom-btn-tangram scale-transition" onclick="addNewGtpParameter()"><i  class="fa fa-plus-square"></i></a>
            <a  id="editId" class="zoom-fab zoom-btn-sm zoom-btn-report scale-transition" onclick="editPrevGtpParameter()"><i  class="fa fa-edit"></i></a>
            </c:if>
             <c:if test="${access.deleteAccess== 'Y'}">
            <a  id="deleteId" class="zoom-fab zoom-btn-sm zoom-btn-feedback scale-transition" onclick="deleteGtpParameter()"><i  class="fa fa-trash"></i></a>
            </c:if>
        </div>
    </div>  --%>
    <div class="full-container"> 
  <!-- left-side start-->
  <div class="clearfix"></div>
  <div id="mobile_first_container" class="left-side col-md-3 no-marg">
  <div class="detailsheader">
        	<div class="col-sm-3 text-center brdrgt"><label>GTP Parameter Reports (<span class="reportCount">0</span>)</label></div>
        </div>
    <div class="input-group">
                <div class="input-group-btn search-panel">
                    <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">
                    	<span id="search_concept">Filter by</span> <span class="caret"></span>
                    </button>
                    <ul class="dropdown-menu" role="menu">
                      <li><a href="#contains"><input type="radio" name="filterBy" value="code" /> Code</a></li>
                      <li class="divider"></li>
                      <li><a href="#all"><input type="radio" name="filterBy" value="name" /> Name</a></li>
                    </ul>
                </div>
                <input type="hidden" name="search_param" value="all" id="search_param">         
                <input type="text" class="form-control" name="x" id="searchLiteralId" placeholder="Search term...">
                <span class="input-group-btn">
                    <button class="btn btn-default" type="button"><span class="glyphicon glyphicon-search"></span></button>
                    <button class="btn btn-default addnewlist" type="button" onclick="addNewGtpParameter()">
					<span class="glyphicon glyphicon-plus"></span>
				</button>
                    
                </span>
            </div>
    <ul id="example" class="nav nav-tabs tabs-left example">
      
        
    </ul>
    <p id="pagination-here"></p>
    
    <div class="clearfix"></div>
  </div>
  <!-- left-side end--> 
  
  <!-- right-side start-->
  <div id="mobile_second_container" class="right-side col-md-9 no-marg">
    <div class="detailsheader">
        	<div class="col-sm-9 text-center"><label>GTP Parameter Details</label></div>
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
                    <div class="col-sm-12 text-right" style="margin-top:7px;">
		                		  <c:if test="${access.modifyAccess== 'Y'}">
		                		<!-- <button type="button" id="addId" onclick="addNewGtpParameter()" class="btn btn-info bluebutton"><span class="glyphicon glyphicon-plus-sign"></span>Add</button>
		                		 -->								  
		                		<button type="button" id="editId"  class="btn btn-info bluebutton"><span class="glyphicon glyphicon-edit"></span>Edit</button>
		                		</c:if>
		                		<c:if test="${access.deleteAccess== 'Y'}">
		                		<button type="button" id="deleteId"  class="btn btn-info bluebutton" "><span class="glyphicon glyphicon-trash"></span>Delete</button>
           
            </c:if>
		                		</div>
            <sf:form class="readonly" id="formId" action="" method="POST" autocomplete="off" modelAttribute="GtpParameter">
                <!--  <form class="" id="formId"> -->
                  <span class="col-sm-12" id="errorMsg">
                            <c:if test="${not empty errorMsg}">
							   <c:out value="${errorMsg}"/>
							</c:if></span> 
							<div class="clearfix"></div>
                  <div class="form-group">  
	   <div class="col-sm-4">
          <div class="styled-input">
            <input type="text" id="name"  class="Name requiredField requiredalphabeticsWithSpace" name="name" required="required" />
            <label>Name<span class="red">*</span></label>
			<span></span>
          </div>
       </div>
	<div class="col-sm-4">
		<div class="styled-input">
			<input type="text" id="code"  class="Code requiredField alphaNumeric" name="code" required="required" />
			<label>Code<span class="red">*</span></label>
			<span></span>
		</div>
	</div>
	<div class="col-sm-4">
		<div class="styled-input">
			<input type="text" id="description"  class="Description" name="description" required="required" />
			<label>Description</label>
			<span></span>
		</div>
	</div>
	<div class="col-sm-4">
		<div class="styled-input">
			<select id="gtpId"  name="gtpParameterType.gtpParameterTypeId" class="gtpParameterType dropDown" required="required" >
			<option value="0">Select GTP ParameterType</option>
			<c:forEach var="gtpParameterType" items="${gtpParameterType}">
				<option value="${gtpParameterType.gtpParameterTypeId}">${gtpParameterType.name}</option>
			</c:forEach>
			</select>
			<label>GTP Parameter Type<span class="red">*</span></label>
			<span></span>
		</div>
	</div>
	<div class="col-sm-4">
                    <div class="styled-input">
                         <select  id="matName"  class="matName" name="material.materialId" required="required">
                        </select>
                        <label>Material<span class="red">*</span></label>
                        <span></span>
                    </div>
                </div>
                <div class="col-sm-2">
                       <button class="btn btn-default aditbt addMatPopup" id="materialId">Add Items</button>
                </div>
   </div> 
   <div class="form-group readonly">
                <div class="col-sm-4">
                    <div class="styled-input">
                        <input type="text" id="itemCodeId"  class="itemTrade" name="" />
                        <label>Item Trade</label>
                        <span></span>
                    </div>
                </div> 
                <div class="col-sm-4">
                    <div class="styled-input">
                        <input type="text" id="hsnCodeId"  class="hsnCodeId" name="" />
                        <label>HSN/SAC Code</label>
                        <span></span>
                    </div>
                </div> 
                </div>       
                   <label style="display:none;" class="col-md-6 col-xs-12">
                    <input style="display:none;" type="text" class="form-control Id" name="gtpParameterId" id="Id">
                  </label>            
                  <div class="col-md-6 col-xs-12">
                    <label class="checkbox-inline">
                      <input type="checkbox" value="Y" name="isActive" class="isActive" checked="checked"/>
                      Active </label>
                  </div>
                  <div class="clearfix"></div>
                    <div class="col-md-12 text-center">
                       <button  type="button"  class="btn btn-info saveBtn indirectFormSubmit save">Save</button>
                      <button  type="button"  class="btn btn-info CancelBtn cancel">Cancel</button>
                    </div>
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
	<script src="<%=request.getContextPath()%>/resource/js/gtpParameter.js?appVer=${appVer}"></script>
    <script src="<%=request.getContextPath()%>/resources/${appMode}/commons/js/springform.js?appVer=${appVer}"></script>
    <script src="<%=request.getContextPath()%>/resources/${appMode}/commons/js/formFields.js?appVer=${appVer}"></script>
    <script src="<%=request.getContextPath()%>/resources/${appMode}/commons/js/commonValidation.js?appVer=${appVer}"></script>
    <script src="<%=request.getContextPath()%>/resources/${appMode}/commons/js/loadList.js?appVer=${appVer}"></script>
	<script src="<%=request.getContextPath()%>/resources/${appMode}/commons/js/loadItemList.js?appVer=${appVer}"></script>
	<script src="<%=request.getContextPath()%>/resources/${appMode}/commons/js/utility.js?appVer=${appVer}"></script>
      