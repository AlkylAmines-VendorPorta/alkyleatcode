<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
     <%@taglib uri = "http://www.springframework.org/tags/form" prefix = "sf"%>
    <!DOCTYPE html>
    <html lang="en">


    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">

        <!--css included  -->
        <link href="<%=request.getContextPath()%>/resource/css/bootstrap.css?appVer=${appVer}" rel="stylesheet">
        <link rel="stylesheet" href="<%=request.getContextPath()%>/resource/css/font-awesome.css?appVer=${appVer}">
        <link rel="stylesheet" href="<%=request.getContextPath()%>/resource/css/common.css?appVer=${appVer}">
        <link rel="stylesheet" href="<%=request.getContextPath()%>/resource/css/mobile.css?appVer=${appVer}">
        <link rel="stylesheet" href="<%=request.getContextPath()%>/resource/css/kendo.common-material.min.css?appVer=${appVer}" />
        <link rel="stylesheet" href="<%=request.getContextPath()%>/resource/css/kendo.material.min.css?appVer=${appVer}" />
        <link rel="stylesheet" href="<%=request.getContextPath()%>/resource/css/kendo.material.mobile.min.css?appVer=${appVer}" />
        <link rel="stylesheet" href="<%=request.getContextPath()%>/resource/css/styleless.css?appVer=${appVer}" />
         <link rel="stylesheet" href="<%=request.getContextPath()%>/resource/css/sweetalert2.min.css?appVer=${appVer}">  
        <!--css included  -->

        <!--js included  -->
        <script src="<%=request.getContextPath()%>/resource/js/jquery-3.2.1.min.js?appVer=${appVer}"></script>
        <script src="<%=request.getContextPath()%>/resource/js/bootstrap.min.js?appVer=${appVer}"></script>
        <script src="<%=request.getContextPath()%>/resource/js/tiles.js?appVer=${appVer}"></script>
        <script src="<%=request.getContextPath()%>/resource/js/internalUser.js?appVer=${appVer}"></script>
		<script src="<%=request.getContextPath()%>/resource/js/sweetalert2.min.js?appVer=${appVer}"></script>
		<script src="<%=request.getContextPath()%>/resources/${appMode}/commons/js/springform.js?appVer=${appVer}"></script>
		
        <%-- 
	    <script src="<%=request.getContextPath()%>/resource/js/less.min.js?appVer=${appVer}"></script> --%>
            <script src="<%=request.getContextPath()%>/resource/js/color.js?appVer=${appVer}"></script>
            <script src="<%=request.getContextPath()%>/resource/js/kendo.all.min.js?appVer=${appVer}"></script>
            <!--js included  -->
       

    </head>

    <body style="background:#fff !important;">
		<div id="loading-wrapper">
 		  <div id="loading-text">LOADING</div>
 		  <div id="loading-content"></div>
		</div>
      
        <jsp:include page="dashbordSideBar.jsp" />

        <!-- full-container start -->

        <!-- Header start -->

        <div class="header text-center">
            <div class="col-xs-4"><div class="pull-left">
                <%-- <img src="<%=request.getContextPath()%>/resource/images/E_logo.png?appVer=${appVer}" class="logo"> --%>
                <button type="button" title="Menue" class="menu-toggle btn btn-info btnsm">
                    <i class="fa fa-bars"></i>
                </button>
                <a href="mastertiles" title="Go_Back_to_Tiles" class="backTotils btn btn-info btnsm"><i
						class="fa fa-arrow-left"></i></a>
                <button title="Go_Back_to_List" class="btn btn-info btnsm backTolist"><i class="fa fa-arrow-left"></i></button>
            </div></div>
            <div class="col-xs-4"><img src="<%=request.getContextPath()%>/resource/images/Mahadiscom_Logo.jpg?appVer=${appVer}" class="dash_logo"></div>
	<div class="col-xs-4">
            <div class="pull-right">
                <div class="dropdown welcome">
                    Welcome |<a class="dropdown-toggle" type="button" id="dropdownMenu1" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true"><span class="glyphicon glyphicon-user userdet"></span>MSCDCL_User<span class="caret"></span></a>

                    <ul class="dropdown-menu" aria-labelledby="dropdownMenu1">
                        <li><a href="vendorRegistration">Profile</a></li>
                        <li><a href="logout">Logout</a></li>
                    </ul>
                </div></div>
            </div>

        </div>
        <!-- Header end -->
        <div class="full-container"> 
  <!-- left-side start-->
  <div class="clearfix"></div>
  <div id="mobile_first_container" class="left-side col-md-3 no-marg">
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
                <input type="text" class="form-control" name="x" id="searchlitralid"  placeholder="Search term...">
                <span class="input-group-btn">
                    <button class="btn btn-default" type="button" onclick="searchUser()"><span class="glyphicon glyphicon-search"></span></button>
                </span>
            </div>
    <ul id="example" class="nav nav-tabs tabs-left example">
  
    </ul>
    <nav aria-label="Page navigation">
  <ul class="pagination">
    <li>
      <a href="#" aria-label="Previous">
        <span aria-hidden="true">&laquo;</span>
      </a>
    </li>
    <li><a href="#">1</a></li>
    <li>
      <a href="#" aria-label="Next">
        <span aria-hidden="true">&raquo;</span>
      </a>
    </li>
  </ul>
</nav>
    <div class="clearfix"></div>
  </div>
  <!-- left-side end--> 
  
  <!-- right-side start-->
  <div id="mobile_second_container" class="right-side col-md-9 no-marg">
    <div class="clearfix"></div>
    <div class="tab-content"> 
      <!-- Master tab start-->
      
      <div class="tab-pane active in" id="">
      <div class="panel-group" id="accordion">
            <div class="panel panel-default">
              <div class="panel-heading">
                <h4 class="panel-title"> <a data-toggle="collapse" data-parent="#accordion" href="#collapse1">User form</a> </h4></div>
               
              </div>
              <div id="collapse1" class="panel-collapse collapse in">
                <div class="panel-body">
                <sf:form id="userform" action="" method="POST" autocomplete="off" modelAttribute="userRole">
             
                		<div class="form-group">                		
                		<input type="hidden" name="user.userId" id="userId" /> 
                		<input type="hidden" name="userRolesId" id="userRoleId" /> 
                		<input type="hidden" name="user.userDetails.userDetailsId" id="userDetailsId" /> 
                		<span class="col-sm-12" id="errorMsg">
                            <c:if test="${not empty errorMsg}">
							   <c:out value="${errorMsg}"/>
							</c:if></span>
                		<div class="col-sm-12 text-right"><button type="button" class="btn btn-info newUserBtn"><span class="glyphicon glyphicon-plus-sign"></span>Add New User</button>
                										  <button type="button" class="btn btn-info editUserBtn"><span class="glyphicon glyphicon-edit"></span>Edit User Details</button></div>
                                              
                                           
                                             
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
                                                 <!-- <div class="col-sm-4">
                                                    <div class="styled-input">
                                                        <input type="email" id="" name="" required="required" />
                                                        <label>Email Address<span class="red">*</span></label>
                                                        <span></span>
                                                    </div>
                                                </div>        -->                                         
                                                <div class="col-sm-4">
                                                    <div class="styled-input">
                                                        <select id="officetype" name="user.userDetails.locationType.locationTypeId" >
                                                        <!-- <option value="0" disabled selected>Select Office Type</option> -->
															<c:forEach var="locationtype" items="${locationtype}">
																<option value="${locationtype.locationTypeId}">${locationtype.name}</option>
															</c:forEach>
                                                        </select>
                                                        <label>Office Type<span class="red">*</span></label>
                                                        <span></span>
                                                    </div>
                                                </div>
                                                <div class="col-sm-4">
                                                    <div class="styled-input">
                                                        <select id="officelocation" name="user.userDetails.location.locationId" >
                                                        <!-- <option value="0" disabled selected>Select Office Location</option> -->
															<c:forEach var="location" items="${location}">
																<option value="${location.locationId}">${location.address1}</option>
															</c:forEach>
                                                        </select>
                                                        <label>Office Location<span class="red">*</span></label>
                                                        <span></span>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                 <div class="col-sm-4">
                                                    <div class="styled-input">
                                                        <select id="designation" name="user.userDetails.designation.designationId" >
                                                        <!-- <option value="0" disabled selected>Select Designation</option> -->
															<c:forEach var="designation" items="${designation}">
																<option value="${designation.designationId}">${designation.name}</option>
															</c:forEach>
                                                        </select>
                                                        <label>Designation<span class="red">*</span></label>
                                                        <span></span>
                                                    </div>
                                                </div>                                                
                                                <div class="col-sm-4">
                                                    <div class="styled-input">
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
                                                <div class="col-sm-4">
                                                    <label class="checkbox-inline top40">
													<input type="checkbox" class="isActive" id="isActive" disabled="disabled">Is Active</label>
                                                </div>
                                                 </span> 
                                            </div>
                                            
                  <div class="clearfix"></div>
                 
                    <div class="col-md-12 text-center">
                      <button type="button" style="display:none;" class="btn btn-info formSubmit save">Save</button>
                      <button type="button" style="display:none;" class="btn btn-info cancelbtn cancel">Cancel</button>
                    </div>
                 </sf:form>
                </div>
              </div>
            </div>
          </div>				
  <!-- right-side end--> 
  </div>
</div>
</div>
        <div class="clearfix"></div>

        <div class="footer">© 2017 E-Auctionapp. All Rights Reserved.</div>

    </body>

    </html>