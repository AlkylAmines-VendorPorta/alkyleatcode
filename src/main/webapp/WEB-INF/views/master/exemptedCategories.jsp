<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@taglib uri = "http://www.springframework.org/tags/form" prefix = "sf"%>
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/${appMode}/tilescommon/css/fioristyle.css?appVer=${appVer}"/>
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/${appMode}/tilescommon/css/mobile.css?appVer=${appVer}"/>
  <%-- <div class="zoom">
        <a class="zoom-fab zoom-btn-large" id="zoomBtn"><i class="fa fa-bars"></i></a>
        <div class="zoom-menu">
            <a  id="refreshId" class="zoom-fab zoom-btn-sm zoom-btn-doc scale-transition"><i  class="fa fa-refresh" aria-hidden="true"></i></a>
             <c:if test="${access.modifyAccess== 'Y'}">
            <a  id="addId" class="zoom-fab zoom-btn-sm zoom-btn-tangram scale-transition" onclick="addExemptedCategories()"><i  class="fa fa-plus-square"></i></a>
            <a  id="editId" class="zoom-fab zoom-btn-sm zoom-btn-report scale-transition" onclick="editExemptedCategories()"><i  class="fa fa-edit"></i></a>
             </c:if>
             <c:if test="${access.deleteAccess== 'Y'}">
            <a  id="deleteId" class="zoom-fab zoom-btn-sm zoom-btn-feedback scale-transition" onclick="deleteExemptedCategories()"><i  class="fa fa-trash"></i></a>
            </c:if>
        </div>
    </div>  --%>
    

<div class="full-container"> 
  <!-- left-side start-->
  <div class="clearfix"></div>
  <div id="mobile_first_container" class="left-side col-md-3 no-marg">
  <div class="detailsheader">
        	<div class="col-sm-3 text-center brdrgt"><label>Exempted Categories Reports (<span class="reportCount">0</span>)</label></div>
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
                    <button class="btn btn-default addnewlist" type="button">
					<span class="glyphicon glyphicon-plus"></span>
				</button>
                </span>
            </div>
    <ul id="example" class="nav nav-tabs tabs-left example">
      
        
    </ul>
    
    <div class="clearfix"></div>
  </div>
  <!-- left-side end--> 
  
  <!-- right-side start-->
  <div id="mobile_second_container" class="right-side col-md-9 no-marg">
    <div class="detailsheader">
        	<div class="col-sm-9 text-center"><label>Exempted Categories Details</label></div>
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
		                		<button type="button" id="addId" onclick="addExemptedCategories()"class="btn btn-info bluebutton"><span class="glyphicon glyphicon-plus-sign"></span> Add </button>
		                										  
		                		<button type="button" id="editId" onclick="editExemptedCategories()"class="btn btn-info bluebutton"><span class="glyphicon glyphicon-edit"></span> Edit </button>
		                		</c:if>
		                		<c:if test="${access.deleteAccess== 'Y'}">
		                		<button type="button" id="deleteId"  class="btn btn-info bluebutton" onclick="deleteExemptedCategories()"><span class="glyphicon glyphicon-trash"></span> Delete </button>
           
            </c:if>
		                		</div>
            <sf:form id="formId" class="readonly" action="" method="POST" autocomplete="off" modelAttribute="ExemptedCategories">
                <!-- <form class="" id="formId"> -->
                  <span class="col-sm-12" id="errorMsg">
                            <c:if test="${not empty errorMsg}">
							   <c:out value="${errorMsg}"/>
							</c:if></span> 
							<div class="clearfix"></div>
                <div class="form-group">  
	   <div class="col-sm-4">
          <div class="styled-input">
            <input type="text" id=""  class="Name" name="name" required="required" />
            <label>Name<span class="red">*</span></label>
			<span></span>
          </div>
       </div>
	<div class="col-sm-4">
		<div class="styled-input">
			<input type="text" id=""  class="Code" name="code" required="required" />
			<label>Code<span class="red">*</span></label>
			<span></span>
		</div>
	</div>
	<div class="col-sm-4">
		<div class="styled-input">
			<input type="text" id=""  class="Description" name="description" required="required" />
			<label>Description</label>
			<span></span>
		</div>
	</div>
   </div> 
                   <label style="display:none;" class="col-md-6 col-xs-12">
                    <input style="display:none;" type="text" class="form-control Id" name="exemptedCategoriesId" >
                  </label>            
                  <div class="col-md-6 col-xs-12">
                    <label class="checkbox-inline">
                      <input type="checkbox" name="isActive" class="isActive" value = "Y" checked="checked">
                      Active </label>
                  </div>
                  <div class="clearfix"></div>
                    <div class="col-md-12 text-center">
                       <button  type="button"  class="btn btn-info saveBtn save indirectFormSubmit">Save</button>
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
 <script src="<%=request.getContextPath()%>/resource/js/exemptedCategories.js?appVer=${appVer}"></script>
 <script src="<%=request.getContextPath()%>/resources/${appMode}/commons/js/springform.js?appVer=${appVer}"></script>
  <script src="<%=request.getContextPath()%>/resources/${appMode}/commons/js/formFields.js?appVer=${appVer}"></script>
   <script src="<%=request.getContextPath()%>/resources/${appMode}/commons/js/commonValidation.js?appVer=${appVer}"></script>