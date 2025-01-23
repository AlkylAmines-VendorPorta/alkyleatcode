<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
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
    <link rel="stylesheet/less" type="text/css" href="<%=request.getContextPath()%>/resource/css/styles.less" />    
  
</head>

<body>

  <div class="zoom">
        <a class="zoom-fab zoom-btn-large" id="zoomBtn"><i class="fa fa-bars"></i></a>
        <div class="zoom-menu">
            <a  id="refreshId" class="zoom-fab zoom-btn-sm zoom-btn-doc scale-transition"><i  class="fa fa-refresh" aria-hidden="true"></i></a>
            <a  id="addId" class="zoom-fab zoom-btn-sm zoom-btn-tangram scale-transition" onclick="addNewMaterialGroup()"><i  class="fa fa-plus-square"></i></a>
            <a  id="editId" class="zoom-fab zoom-btn-sm zoom-btn-report scale-transition" onclick="editPrevMaterialGroup()"><i  class="fa fa-edit"></i></a>
            <a  id="deleteId" class="zoom-fab zoom-btn-sm zoom-btn-feedback scale-transition" onclick="deleteMaterialGroup()"><i  class="fa fa-trash"></i></a>
        </div>
    </div>
    <div id="sidebar-wrapper">
        <div class="sidebar-nav">
            <div class="nav-side-menu">
                <div class="brand"><img src="<%=request.getContextPath()%>/resource/images/logo.png?appVer=${appVer}" class="navlogo logo"></div>

                <div class="menu-list">
                    <ul id="menu-content" class="menu-content">
                        <li data-toggle="collapse" data-target="#products" class="collapsed active">
                            <a href="#"><i class="fa fa-user-circle usericon" aria-hidden="true"></i>Welcome<span class="arrow"></span></a>
                        </li>
                        <li>
                            <a href="#">
                                <i class="fa fa-dashboard fa-lg"></i> Dashboard
                            </a>
                        </li>

                        <li data-toggle="collapse" data-target="#service" class="collapsed">
                            <a href="#"><i class="fa fa-globe fa-lg"></i> Services <span class="arrow"></span></a>
                        </li>
                       		<ul class="sub-menu collapse" id="service">
                         	  <li>New Service 1</li>
                         	  <li>New Service 2</li>
                          	  <li>New Service 3</li>
                            </ul>
                        <li>
                            <a href="#">
                                <i class="fa fa-user fa-lg"></i> Profile
                            </a>
                        </li>

                        <li>
                            <a href="#">
                                <i class="fa fa-users fa-lg"></i> Users
                            </a>
                        </li>
                    </ul>
                </div>
            </div>
            <div class="clearfix"></div>

        </div>
    </div>
    
    <!-- full-container start -->

  <!-- Header start -->
  
   <div class="header">
            <div class="pull-left">
                <img src="<%=request.getContextPath()%>/resource/images/E_logo.png?appVer=${appVer}" class="logo">
                    <button type="button" title="Menue" class="menu-toggle btn btn-info btnsm">
                        <i class="fa fa-bars"></i>
                    </button>
                    <a href="mastertiles" title="Go_Back_to_Tiles" class="backTotils btn btn-info btnsm"><i
						class="fa fa-arrow-left"></i></a>
                    <button title="Go_Back_to_List" class="btn btn-info btnsm backTolist"><i class="fa fa-arrow-left"></i></button>
               </div>
               <div class="pull-right"> 
              <div class="dropdown welcome">
				    Welcome |<a class="dropdown-toggle" type="button" id="dropdownMenu1" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true"><span class="glyphicon glyphicon-user userdet"></span>MSCDCL_User<span class="caret"></span></a> 
				    
				  <ul class="dropdown-menu" aria-labelledby="dropdownMenu1">
				    <li><a href="vendorRegistration">Profile</a></li>
				    <li><a href="logout">Logout</a></li>
				  </ul>
				</div>
				</div>
               <!-- <div class="welcome dropdown">
               					 <span class="glyphicon glyphicon-user" style="color:#fff"></span> Welcome MSCDCL_User |<a href="logout">Logout</a></div></div> -->
               
                
                <!-- <div class="col-xs-7 text-right actionbutton">
                    <button type="button" title="Previous" class="btn btn-info btnPrevious btnsm"><i class="fa fa-arrow-up"></i></button>
                    <button type="button" title="Next" class="btn btn-info btnNext btnsm"><i class="fa fa-arrow-down"></i></button>
                    <button type="button" title="Add/New" class="btn btn-info btnsm">
                        <i class="fa fa-plus-square"></i>
                    </button>
                    <button type="button" title="edit" class="btn btn-info btnsm">
                        <i class="fa fa-pencil-square-o"></i>
                    </button>
                    <button type="button" title="Delete" class="btn btn-info btnsm">
                        <i class="fa fa-trash"></i>
                    </button>
                </div> -->
        </div>
  <!-- Header end -->
 <div class="full-container"> 
  <!-- left-side start-->
  <div class="clearfix"></div>
  <div id="mobile_first_container" class="left-side col-md-3 no-marg">
    <div id="custom-search-input">
      <div class="input-group col-md-12">
        <input type="text" class=" search-query form-control" id="searchlitralid" onkeyup="searchuom()" placeholder="Search">
        <button class="btn btn-danger" type="button"> <span class=" glyphicon glyphicon-search"></span> </button>
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
      <!-- <a href="#" aria-label="Previous">
        <span aria-hidden="true">&laquo;</span> <span aria-hidden="true">Prev</span>
      </a>
    </li>
    <li><a href="#">1</a></li>
    <li><a href="#">2</a></li>
    <li><a href="#">3</a></li>
    <li><a href="#">4</a></li>
    <li><a href="#">5</a></li>
    <li>
      <a href="#" aria-label="Next">
        <span aria-hidden="true">Next</span> <span aria-hidden="true">&raquo;</span>
      </a> -->
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
      
      <div class="tab-pane active in" id="AddMaterialGroup">        
          <div class="panel-group" id="accordion">
            <div class="panel panel-default">
              <div class="panel-heading">
                <h4 class="panel-title"> <a data-toggle="collapse" data-parent="#accordion" href="#collapse1"> State Details</a> </h4>
              </div>
              <div id="collapse1" class="panel-collapse collapse in">
                <div class="panel-body">
                <form>
                  <label class="col-md-6 col-xs-12">Name<span class="red">*</span>
                    <input type="text" class="form-control Name" disabled="disabled">
                  </label>
                   <label class="col-md-6 col-xs-12">Code<span class="red">*</span>
                    <input type="text" class="form-control Code" disabled="disabled">
                  </label>
                  <label class="col-md-6 col-xs-12">Description
                    <input type="text" class="form-control Description" disabled="disabled">
                  </label > 
                   <label  class="col-md-6 col-xs-12">Select Country<span class="red">*</span>
                 		<select class="form-control" id="countryid" disabled="disabled">
								 <option value=""> Countries </option>
						</select>
								
				   </label>
                   <label style="display:none;" class="col-md-6 col-xs-12">Id
                    <input style="display:none;" type="text" class="form-control materialGroupId" disabled="disabled">
                  </label>            
                  <div class="col-md-6 col-xs-12">
                    <label class="checkbox-inline">
                      <input type="checkbox" value="" class=" isActive" checked="checked" disabled="disabled">
                      Active </label>
                  </div>
                  <div class="clearfix"></div>
                    <div class="col-md-12 text-center">
                      <button  type="button"  class="btn btn-info saveBtn">Save</button>
                      <button  type="button"  class="btn btn-info CancelBtn">Cancel</button>
                    </div>
                 </form>
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
    
    <div class="footer">@ All Right Reserved NovelERP</div>
   

</body>
  
    <!-- HTML5 shim and Respond.js?appVer=${appVer} for IE8 support of HTML5 elements and media queries -->
<script src="<%=request.getContextPath()%>/resource/js/jquery-3.2.1.min.js?appVer=${appVer}"></script>
<script src="<%=request.getContextPath()%>/resource/js/bootstrap.min.js?appVer=${appVer}"></script>
<script src="<%=request.getContextPath()%>/resource/js/tiles.js?appVer=${appVer}"></script>
<script src="<%=request.getContextPath()%>/resource/js/less.min.js?appVer=${appVer}"></script>
<script src="<%=request.getContextPath()%>/resource/js/color.js?appVer=${appVer}"></script>
<script src="<%=request.getContextPath()%>/resource/js/jquery.paginate.js?appVer=${appVer}"></script>
<script src="<%=request.getContextPath()%>/resource/js/bootstrap-datetimepicker.js?appVer=${appVer}"></script>

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