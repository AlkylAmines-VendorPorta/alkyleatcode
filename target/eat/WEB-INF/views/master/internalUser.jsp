<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@taglib uri = "http://www.springframework.org/tags/form" prefix = "sf"%>
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/${appMode}/tilescommon/css/fioristyle.css?appVer=${appVer}"/>
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/${appMode}/tilescommon/css/mobile.css?appVer=${appVer}"/>
<style>.form-group {
    margin-bottom: 5px;
}</style>
  <div class="full-container"> 
  <!-- left-side start-->
  <div class="clearfix"></div>
  <div id="mobile_first_container" class="left-side col-md-3 no-marg">
  <div class="detailsheader">
        	<div class="col-sm-3 text-center brdrgt"><label>User Report (<span class="reportCount">0</span>)</label></div>
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
                <input type="text" class="form-control" name="x" id="searchLiteralId"  placeholder="Search term...">
                <span class="input-group-btn">
                    <button class="btn btn-default" type="button" onclick="searchUser()"><span class="glyphicon glyphicon-search"></span></button>
                    <button class="btn btn-default addnewlist newUserBtn" type="button"><span class="glyphicon glyphicon-plus"></span></button>
                </span>            </div>
    <ul id="example" class="nav nav-tabs tabs-left example">
      
        
    </ul>
    
    <div class="clearfix"></div>
  </div>
  <!-- left-side end--> 
  <input type="hidden" id="login_user_role" value="${logggedIn_userrole}" />
  <!-- right-side start-->
  <div id="mobile_second_container" class="right-side col-md-9 no-marg">
    <div class="detailsheader">
        	<div class="col-sm-9 text-center"><label>User  Detail</label></div>
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
                            <a href="#home" data-toggle="tab" title="welcome">
                                <span class="round-tabs one">
                                <i class="fa fa-info" aria-hidden="true"></i>
                                </span>
                            </a>
                        </li>                        
                    </ul>
                </div>
                <div class="tab-content">
                    <div class="tab-pane fade in active" id="home">
            <sf:form id="userform" action="" method="POST" autocomplete="off" modelAttribute="userRole">
             
                		<div class="form-group">                		
                		<input type="hidden" name="user.userId" id="userId" /> 
                		<input type="hidden" name="userRolesId" id="userRoleId" /> 
                		<input type="hidden" name="user.userDetails.userDetailsId" id="userDetailsId" /> 
                		<span class="col-sm-12" id="errorMsg">
                            <c:if test="${not empty errorMsg}">
							   <c:out value="${errorMsg}"/>
							</c:if></span>
                		<div class="col-sm-12 text-right">
		                		 <c:if test="${access.modifyAccess== 'Y'}">
		                		<button type="button" class="btn btn-info bluebutton newUserBtn"><span class="glyphicon glyphicon-plus-sign"></span>Add New User</button>
		                										  
		                		<button type="button" class="btn btn-info bluebutton editUserBtn"><span class="glyphicon glyphicon-edit"></span>Edit User Details</button>
		                		</c:if>
		                		</div>
                                            
                                           
                                             
                                                </div>
                                                 <span class="readonly" id="readonly">
                                                <div class="form-group">
                                                    <div class="col-sm-4">
                                                    <div class="styled-input">
                                                        <input type="text" id="email" name="user.email" required="required" />
                                                         
                                                        <label>User Login Id<span class="red">*</span></label>
                                                        <span></span>
                                                    </div>
                                                    
                                                </div>
                                                
                                                
                                                </div>
                                                <div class="form-group">
                                                <div class="col-sm-4">
                                                    <div class="styled-input">
                                                        <input type="text" id="firstname"  name="user.userDetails.firstName" required="required" />
                                                        <label>First Name<span class="red">*</span></label>
                                                        <span></span>
                                                    </div>
                                                </div>
                                                 <div class="col-sm-4">
                                                    <div class="styled-input">
                                                        <input type="text" id="middlename" name="user.userDetails.middleName" required="required" />
                                                        <label>Middle Name</label>
                                                        <span></span>
                                                    </div>
                                                </div>
                                                <div class="col-sm-4">
                                                    <div class="styled-input">
                                                        <input type="text" id="lastname" name="user.userDetails.lastName" required="required" />
                                                        <label>Last Name<span class="red">*</span></label>
                                                        <span></span>
                                                    </div>
                                                </div> 
                                                </div>
                                                 <div class="form-group">
                                                                                          
                                                <div class="col-sm-4">
                                                    <div class="styled-input">
                                                        <select id="officetype" name="user.userDetails.locationType.locationTypeId" onchange="setLocationBylocType()">
                                                          <option value="" disabled selected></option>
															<c:forEach var="locationtype" items="${locationtype}">
																<option value="${locationtype.locationTypeId}" data-code="${locationtype.name}" >${locationtype.name}</option>
															</c:forEach>
                                                        </select>
                                                        <label>Office Type<span class="red">*</span></label>
                                                        <span></span>
                                                    </div>
                                                     <input type="text" style="display: none;" id=locationTypeRef  name="user.userDetails.locationTypeRef" />
                                                </div>
                                                <div class="col-sm-4">
                                                    <div class="styled-input">
                                                        <select id="officelocation" name="user.userDetails.officeLocation.officeLocationId" >
                                                           <%-- <option value="0" disabled selected></option>
															<c:forEach var="location" items="${location}">
																<option value="${location.officeLocationId}">${location.name}</option>
															</c:forEach> --%>
                                                        </select>
                                                        <label>Office Location<span class="red">*</span></label>
                                                        <span></span>
                                                    </div>
                                                </div>
                                                <div class="col-sm-4">
                                                    <div class="styled-input">
                                                        <select id="designation" name="user.userDetails.designation.designationId" >
                                                          <option value="0" disabled selected></option>
															<c:forEach var="designation" items="${designation}">
																<option value="${designation.designationId}">${designation.name}</option>
															</c:forEach>
                                                        </select>
                                                        <label>Designation<span class="red">*</span></label>
                                                        <span></span>
                                                    </div>
                                                </div>    
                                            </div>
                                            <div class="form-group">  
												<div class="mobclearfix"></div>                                                                                          
                                                <div class="col-sm-4">
                                                    <div class="styled-input slate">
                                                        <select id="role" name="role.roleId" >
                                                        <!-- <option value="0" disabled selected>Select Role</option> -->
															<c:forEach var="role" items="${role}">
																<option value="${role.roleId}">${role.name}</option>
															</c:forEach>
                                                        </select>
                                                        <label>Groups<span class="red">*</span></label>
                                                        <span></span>
                                                    </div>
                                                </div>
                                                <div class="mobclearfix"></div>
                                                <div class="col-sm-4">
                                                    <label class="checkbox-inline top40">
													<input type="checkbox" value="Y" name="user.isActive" class="isActive" checked="checked"/>Is Active</label>
                                                </div>
                                                <input type="checkbox" style="display: none;" value="Y" name="isActive" class="isActive" checked="checked"/>
                                                </div>
                                                 </span> 
                                            
                                            
                  <div class="clearfix"></div>
                 
                    <div class="col-md-12 text-center">
                      <button type="button" style="display:none;" class="btn btn-info indirectFormSubmit save">Save</button>
                      <button type="button" style="display:none;" class="btn btn-info cancelbtn cancel">Cancel</button>
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
  <script src="<%=request.getContextPath()%>/resource/js/internalUser.js?appVer=${appVer}"></script>
  <script src="<%=request.getContextPath()%>/resources/${appMode}/commons/js/springform.js?appVer=${appVer}"></script>
   <script src="<%=request.getContextPath()%>/resources/${appMode}/commons/js/formFields.js?appVer=${appVer}"></script>
   <script src="<%=request.getContextPath()%>/resources/${appMode}/commons/js/utility.js?appVer=${appVer}"></script>
   <script src="<%=request.getContextPath()%>/resources/${appMode}/commons/js/commonValidation.js?appVer=${appVer}"></script>