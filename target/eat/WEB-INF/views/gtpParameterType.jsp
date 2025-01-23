<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
     <%@taglib uri = "http://www.springframework.org/tags/form" prefix = "sf"%>
<!DOCTYPE html>
<html lang="en">

<head>
      <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <title>E-Auctionapp e-Tendering</title>

    <!-- Bootstrap -->
    
    <link href="<%=request.getContextPath()%>/resource/css/bootstrap.css?appVer=${appVer}" rel="stylesheet">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/resource/css/font-awesome.css?appVer=${appVer}">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/resource/css/common.css?appVer=${appVer}">     
    <link rel="stylesheet" href="<%=request.getContextPath()%>/resource/css/mobile.css?appVer=${appVer}">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/resource/css/jquery.paginate.css?appVer=${appVer}" />
    <link rel="stylesheet" href="<%=request.getContextPath()%>/resource/css/bootstrap-datetimepicker.css?appVer=${appVer}" />
    <link rel="stylesheet" href="<%=request.getContextPath()%>/resource/css/animate.min.css?appVer=${appVer}" />  
    <link rel="stylesheet" href="<%=request.getContextPath()%>/resource/css/styleless.css?appVer=${appVer}" />   
    <link rel="stylesheet" href="<%=request.getContextPath()%>/resource/css/sweetalert2.min.css?appVer=${appVer}"> 
  
</head>

<body>
<div id="loading-wrapper">
 		  <div id="loading-text">LOADING</div>
 		  <div id="loading-content"></div>
		</div>

 <div class="zoom">
        <a class="zoom-fab zoom-btn-large" id="zoomBtn"><i class="fa fa-bars"></i></a>
        <div class="zoom-menu">
            <a  id="refreshId" class="zoom-fab zoom-btn-sm zoom-btn-doc scale-transition"><i  class="fa fa-refresh" aria-hidden="true"></i></a>
            <a  id="addId" class="zoom-fab zoom-btn-sm zoom-btn-tangram scale-transition" onclick="addNewGtpParameterType()"><i  class="fa fa-plus-square"></i></a>
            <a  id="editId" class="zoom-fab zoom-btn-sm zoom-btn-report scale-transition" onclick="editPrevGtpParameterType()"><i  class="fa fa-edit"></i></a>
            <a  id="deleteId" class="zoom-fab zoom-btn-sm zoom-btn-feedback scale-transition" onclick="deleteGtpParameterType()"><i  class="fa fa-trash"></i></a>
        </div>
    </div> 
    <jsp:include page="dashbordSideBar.jsp" />
    
    <!-- full-container start -->

  <!-- Header start -->
  
   <div class="header text-center">
         	<div class="col-xs-4">   <div class="pull-left">
              <%--   <img src="<%=request.getContextPath()%>/resource/images/E_logo.png?appVer=${appVer}" class="logo"> --%>
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
                <input type="text" class="form-control" name="x" placeholder="Search term...">
                <span class="input-group-btn">
                    <button class="btn btn-default" type="button"><span class="glyphicon glyphicon-search"></span></button>
                </span>
            </div>
    <ul id="example" class="nav nav-tabs tabs-left example">
      
        
    </ul>
    <nav aria-label="Page navigation">
  <ul class="pagination" id="pagination">
     <li>

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
                <h4 class="panel-title"> <a data-toggle="collapse" data-parent="#accordion" href="#collapse1"> GTP Parameter Type</a> </h4>
              </div>
              <div id="collapse1" class="panel-collapse collapse in">
                <div class="panel-body">
                <sf:form id="formId" class="readonly" action="" method="POST" autocomplete="off" modelAttribute="GtpParameterType">
                <!--  <form class="" id="formId"> -->
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
	<label style="display:none;">
                    <input style="display:none;" type="text" class="form-control Id" name="gtpParameterTypeId">
                  </label> 
   </div>            
                  <div class="col-md-6 col-xs-12">
                    <label class="checkbox-inline">
                      <input type="checkbox" name="isActive" class="isActive" value = "Y" >
                      Active </label>
                  </div>
                  <div class="clearfix"></div>
                    <div class="col-md-12 text-center">
                      <button  type="button"  class="btn btn-info saveBtn formSubmit">Save</button>
                      <button  type="button"  class="btn btn-info CancelBtn">Cancel</button>
                    </div>
                 <!-- </form> -->
                 </sf:form>
                </div>
              </div>
            </div>
          </div>        
      </div>
      
    
      </div>
      <!-- Master tab end--> 
      
                      
    </div>
  <!-- right-side end--> 
  </div>
<!-- full-container end-->
    

    <!-- full-container end-->
    <div class="clearfix"></div>
    
    <div class="footer">@ All Right Reserved E-Auctionapp</div>
   

</body>
  
    <!-- HTML5 shim and Respond.js?appVer=${appVer} for IE8 support of HTML5 elements and media queries -->
<script src="<%=request.getContextPath()%>/resource/js/jquery-3.2.1.min.js?appVer=${appVer}"></script>
<script src="<%=request.getContextPath()%>/resource/js/bootstrap.min.js?appVer=${appVer}"></script>
<script src="<%=request.getContextPath()%>/resource/js/tiles.js?appVer=${appVer}"></script>
<%-- <script src="<%=request.getContextPath()%>/resource/js/less.min.js?appVer=${appVer}"></script> --%>
<script src="<%=request.getContextPath()%>/resource/js/color.js?appVer=${appVer}"></script>
<script src="<%=request.getContextPath()%>/resource/js/jquery.paginate.js?appVer=${appVer}"></script>
<script src="<%=request.getContextPath()%>/resource/js/bootstrap-datetimepicker.js?appVer=${appVer}"></script>
<script src="<%=request.getContextPath()%>/resource/js/sweetalert2.min.js?appVer=${appVer}"></script>
    <script src="<%=request.getContextPath()%>/resource/js/gtpparametertype.js?appVer=${appVer}"></script>
    <script src="<%=request.getContextPath()%>/resources/${appMode}/commons/js/springform.js?appVer=${appVer}"></script>

<script>
$(document).ready(function(e){
    $('.search-panel .dropdown-menu').find('a').click(function(e) {
		e.preventDefault();
		var param = $(this).attr("href").replace("#","");
		var concept = $(this).text();
		$('.search-panel span#search_concept').text(concept);
		$('.input-group #search_param').val(param);
	});
});
</script>
</html>