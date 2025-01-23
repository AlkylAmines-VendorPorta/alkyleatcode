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
}
</style>
  <div class="full-container"> 
  <!-- left-side start-->
  <div class="clearfix"></div>
  <div id="mobile_first_container" class="left-side col-md-3 no-marg">
  <div class="detailsheader">
        	<div class="col-sm-3 text-center brdrgt"><label>User Report (<span class="reportCount">1</span>)</label></div>
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
                    <button class="btn btn-default " type="button" onclick="searchUser()"><span class="glyphicon glyphicon-search"></span></button>
		    <button class="btn btn-default addnewlist" type="button"><span class="glyphicon glyphicon-plus"></span></button>
                </span>            </div>
    <ul id="example" class="nav nav-tabs tabs-left example">
      
        
    </ul>
    
    <div class="clearfix"></div>
  </div>
  <!-- left-side end--> 
  
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
                        <li class="profileSettingClass" onclick="loadProfileSetting()">
                            <a href="#home2" data-toggle="tab" title="welcome">
                                <span class="round-tabs one">
                                	<i class="fa fa-cogs" aria-hidden="true"></i>
                                </span>
                            </a>
                        </li>                        
                    </ul>
                </div>
                <div class="tab-content">
                    <div class="tab-pane fade in active" id="home">
            <sf:form id="userform" action="" method="POST" autocomplete="off" modelAttribute="userRole" class="readonly">
             
                		<div class="form-group">                		
                
                		
                		<%-- <div class="col-sm-12 text-right">
		                		 <c:if test="${access.modifyAccess== 'Y'}">
		                		<button type="button" class="btn btn-info bluebutton newUserBtn"><span class="glyphicon glyphicon-plus-sign"></span>Add New User</button>
		                										  
		                		<button type="button" class="btn btn-info bluebutton editUserBtn"><span class="glyphicon glyphicon-edit"></span>Edit User Details</button>
		                		</c:if>
		                		</div> --%>   </div>
                                                 <span class="readonly" id="readonly">
                                                <div class="form-group">
                                                    <div class="col-sm-4">
                                                    <div class="styled-input">
                                                        <input type="text" id="email" name="user.email" required="required" />
                                                         
                                                        <label>User Login Id<span class="red">*</span></label>
                                                        <span></span>
                                                    </div>
                                                    
                                                </div>
                                                <!-- <div class="col-sm-4">
                                                    <div class="styled-input">
                                                        <input type="text" id="password" name="" readonly="readonly" />
                                                         <label>Password</label>
                                                        <span></span>
                                                    </div>
                                                    
                                                </div> -->
                                                
                                                </div>
                                                <div class="form-group">
                                                <div class="col-sm-4">
                                                    <div class="styled-input">
                                                        <input type="text" id="firstname"  name="" required="required" />
                                                        <label>First Name<span class="red">*</span></label>
                                                        <span></span>
                                                    </div>
                                                </div>
                                                 <div class="col-sm-4">
                                                    <div class="styled-input">
                                                        <input type="text" id="middlename" name="" required="required" />
                                                        <label>Middle Name</label>
                                                        <span></span>
                                                    </div>
                                                </div>
                                                <div class="col-sm-4">
                                                    <div class="styled-input">
                                                        <input type="text" id="lastname" name="" required="required" />
                                                        <label>Last Name<span class="red">*</span></label>
                                                        <span></span>
                                                    </div>
                                                </div> 
                                                </div>
                                                 <div class="form-group">
                                                 <!-- <div class="col-sm-4">
                                                    <div class="styled-input">
                                                        <input type="email" id="" name="" required="required" />
                                                        <label>Email Address<span class="red">*</span></label>
                                                        <span></span>
                                                    </div>
                                                </div>        -->                                         
                                                <div class="col-sm-4">
                                                    <div class="styled-input">
                                                        <%-- <select id="officetype" name="">
															<c:forEach var="locationtype" items="${locationtype}">
																<option value="${locationtype.locationTypeId}">${locationtype.name}</option>
															</c:forEach>
                                                        </select>
                                                        <label>Office Type<span class="red">*</span></label> --%>
                                                        <input type="text" id="officetype" name="" required="required" />
                                                         <label>Office Type<span class="red">*</span></label>
                                                        
                                                        <span></span>
                                                    </div>
                                                     <input type="text" style="display: none;" id=locationTypeRef  name="" />
                                                </div>
                                                <div class="col-sm-4">
                                                    <div class="styled-input">
                                                       <%--  <select id="officelocation" name="" >
															<c:forEach var="location" items="${location}">
																<option value="${location.locationId}">${location.address1}</option>
															</c:forEach>
                                                        </select>
                                                        <label>Office Location<span class="red">*</span></label> --%>
                                                         <input type="text" id="officelocation" name="" required="required" />
                                                         <label>Office Location<span class="red">*</span></label>
                                                        <span></span>
                                                    </div>
                                                </div>
                                                <div class="col-sm-4">
                                                    <div class="styled-input">
                                                        <%-- <select id="designation" name="" >
															<c:forEach var="designation" items="${designation}">
																<option value="${designation.designationId}">${designation.name}</option>
															</c:forEach>
                                                        </select>
                                                        <label>Designation<span class="red">*</span></label>
                                                        <span></span> --%>
                                                        <input type="text" id="designation" name="" required="required" />
                                                         <label>Designation<span class="red">*</span></label>
                                                        <span></span>
                                                    </div>
                                                </div>    
                                            </div>
                                            <div class="form-group">                                                                                             
                                                <div class="col-sm-4">
                                                    <div class="styled-input">
                                                        <%-- <select id="role" name="" >
															<c:forEach var="role" items="${role}">
																<option value="${role.roleId}">${role.name}</option>
															</c:forEach>
                                                        </select>
                                                        <label>Groups<span class="red">*</span></label>
                                                        <span></span> --%>
                                                        <input type="text" id="role" name="" required="required" />
                                                         <label>Groups<span class="red">*</span></label>
                                                        
                                                        <span></span>
                                                    </div>
                                                </div>
                                                <div class="col-sm-4">
                                                    <label class="checkbox-inline top40">
													<input type="checkbox" value="Y" name="" class="isActive" checked="checked"/>Is Active</label>
                                                </div>
                                                <input type="checkbox" style="display: none;" value="Y" name="" class="isActive" checked="checked"/>
                                                </div>
                                                 </span> 
                                            
                                            
                  <div class="clearfix"></div>
                 
                    <div class="col-md-12 text-center">
                      <!-- <button type="button" style="display:none;" class="btn btn-info indirectFormSubmit save">Save</button>
                      <button type="button" style="display:none;" class="btn btn-info cancelbtn cancel">Cancel</button> -->
                    </div>
                 </sf:form>
                    </div>
                    <div class="profileSettingClass tab-pane fade" id="home2">
                    <sf:form id="profileSettingForm" method="POST" autocomplete="off" modelAttribute="profileSetting">
                     <input type="hidden" class="form-control" id="profileSettingId" name="profileSettingId"/>
                     <input type="hidden" id="no_imageUrl"/>
                   <h4 class="col-sm-3">URL Pattern</h4>
                   <div class="col-sm-4">
                   		<input type="text" class="form-control" name="urlPattern" id="urlPatternId"/>
                   </div>
                   <div class="clearfix"></div>
                  
                   <h4 class="col-sm-3">Theme Color</h4>
                   <input type="hidden" class="form-control" id="themeColor" name="themeColor"/>
                    <div class="col-sm-6">
                   		<div id="bg-selector"> 
							<div class="color green" id="green_themeId" data-value="#46a744" data-themecolor="green"></div>
							<div class="color redbg" id="redbg_themeId" data-value="red"  data-themecolor="redbg"></div>
							<div class="color skyblue"  id="skyblue_themeId" data-value="#07a3e8"  data-themecolor="skyblue"></div>
							<div class="color gray" id="gray_themeId" data-value="#eaeaea"  data-themecolor="gray"></div>
							<div class="color black" id="black_themeId" data-value="#000"  data-themecolor="black"></div>
							<div class="color blue" id="blue_themeId" data-value="#00529c"  data-themecolor="blue"></div>
							<div class="color orangecol" id="orangecol_themeId" data-value="#ca630b"  data-themecolor="orangecol"></div>
						</div>


						<div class="compcontainer">
						<div class="screen monitor bgprivew">
						 
						  <div class="base">
						    <div class="grey-shadow"></div>
						    <div class="foot top"></div>
						    <div class="foot bottom"></div>
						  </div>
						</div>       
						  </div>
			  
			</div>
                    <div class="clearfix"></div>
             
                    <h4 class="col-sm-3">Upload Profile Logo</h4>
                    <div class="col-sm-6">
                   		<div class="avatar-upload">
					        <div class="avatar-edit">
					            <input type='file' id="imageUpload" accept=".png, .jpg, .jpeg" data-id="profileLogoId" data-name="downloadMom" data-anchor="downloadMom" class="form-control uploadImageFile"/>
					            <input type="hidden" id="profileLogoId" name="profileLogo.attachmentId" class="form-control fileResponse requiredField" /> 
													                
					            <label for="imageUpload"><i class="fa fa-camera"></i></label>
					        </div>
					        <div class="avatar-preview">
					            <div id="imagePreview" >
					            </div>
					        </div>
					         <div class="col-sm-12 text-center" style="margin-top:10px;">
					                                        <button class="btn btn-info save" >Upload Image</button>
													        <button id="deleteProfileLogoBtnId" class="btn btn-default profileLogoId" onclick="return submitWithParam('deleteAttachment','profileLogoId','profileLogoDeleteResp');"><i class="fa fa-times"></i></button>
													               
					          </div>
					    </div>
                   </div>
                   </sf:form>
                   <div class="col-sm-12 text-center" style="margin-top:10px;">
                                        <button class="btn btn-info save" id="saveProfileSettingBtnId" onclick="return submitIt('profileSettingForm','updateProfileSetting','updateProfileSettingResp');">Save</button>
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
  <script src="<%=request.getContextPath()%>/resource/js/internalUserProfile.js?appVer=${appVer}"></script>
   <script src="<%=request.getContextPath()%>/resource/js/profileSetting.js?appVer=${appVer}"></script>
   <script src="<%=request.getContextPath()%>/resources/${appMode}/commons/js/formFields.js?appVer=${appVer}"></script>
   <script src="<%=request.getContextPath()%>/resources/${appMode}/commons/js/utility.js?appVer=${appVer}"></script>
      <script src="<%=request.getContextPath()%>/resources/${appMode}/commons/js/springform.js?appVer=${appVer}"></script>
      <script src="<%=request.getContextPath()%>/resources/${appMode}/partner/js/uploadFile.js?appVer=${appVer}"></script>