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
    
    <!-- HTML5 shim and Respond.js?appVer=${appVer} for IE8 support of HTML5 elements and media queries -->
    <script src="<%=request.getContextPath()%>/resource/js/jquery-3.2.1.min.js?appVer=${appVer}"></script>
<script src="<%=request.getContextPath()%>/resource/js/bootstrap.min.js?appVer=${appVer}"></script>
<script src="<%=request.getContextPath()%>/resource/js/tiles.js?appVer=${appVer}"></script>
<script src="<%=request.getContextPath()%>/resource/js/less.min.js?appVer=${appVer}"></script>
<script src="<%=request.getContextPath()%>/resource/js/color.js?appVer=${appVer}"></script>
<script src="<%=request.getContextPath()%>/resource/js/jquery.paginate.js?appVer=${appVer}"></script>
<script src="<%=request.getContextPath()%>/resource/js/bootstrap-datetimepicker.js?appVer=${appVer}"></script>
    <!-- WARNING: Respond.js?appVer=${appVer} doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js?appVer=${appVer}"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js?appVer=${appVer}"></script>
    <![endif]-->
 
</head>

<!-- <body> -->
 <div id="preloader"></div>
  <div class="zoom">
        <a class="zoom-fab zoom-btn-large" id="zoomBtn"><i class="fa fa-bars"></i></a>
        <div class="zoom-menu">
            <a class="zoom-fab zoom-btn-sm zoom-btn-doc scale-transition scale-out"><i class="fa fa-refresh" aria-hidden="true"></i></a>
            <a class="zoom-fab zoom-btn-sm zoom-btn-tangram scale-transition scale-out"><i class="fa fa-plus-square"></i></a>
            <a class="zoom-fab zoom-btn-sm zoom-btn-report scale-transition scale-out"><i class="fa fa-edit"></i></a>
            <a class="zoom-fab zoom-btn-sm zoom-btn-feedback scale-transition scale-out"><i class="fa fa-trash"></i></a>
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
                        <ul class="sub-menu collapse" id="products">
                            <li><a href="#">Logout</a></li>
                        </ul>
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
    <div class="full-container"> 
  <!-- Header start -->
   <div class="header">
            <div class="col-xs-5">
                <img src="<%=request.getContextPath()%>/resource/images/E_logo.png?appVer=${appVer}" class="logo">
                    <button type="button" title="Menue" class="menu-toggle btn btn-info btnsm">
                        <i class="fa fa-bars"></i>
                    </button>
                    <a href="tendertiles" title="Go Back" class="BAtilse btn btn-info btnsm"><i
						class="fa fa-arrow-left"></i></a>
                    <button title="Go Back" class="btn btn-info btnsm BAlist"><i class="fa fa-arrow-left"></i></button>
                </div>
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
                <div class="col-xs-7"><div class="welcome pull-right"> <span class="glyphicon glyphicon-user" style="color:#fff"></span> Welcome MSCDCL_User |<a href="home">Logout</a></div></div>
        </div>
  <!-- Header end -->

  <!-- left-side start-->
  <div class="clearfix"></div>
  <div id="mobile_first_container" class="left-side col-md-3 no-marg animated slideInRight">
    <div id="custom-search-input">
      <div class="input-group col-md-12">
        <input type="text" class="  search-query form-control" placeholder="Serch">
        <button class="btn btn-danger" type="button"> <span class=" glyphicon glyphicon-search"></span> </button>
      </div>
    </div>
    <ul id="example" class="nav nav-tabs tabs-left">
      <li class="active"> 
      <a href="#AddUoM" class="AddUoM" data-toggle="tab">
        <div class="col-md-12">
          <label class="col-xs-6">UOM Name</label>
          <label class="col-xs-6">Kilogram</label>
        </div>
        <div class="col-md-12">
          <label class="col-xs-6">UOM Code</label>
          <label class="col-xs-6">KG</label>
        </div>
        </a>
        </li>
        
        <li> 
      <a href="#AddUoM2" class="AddUoM" data-toggle="tab">
        <div class="col-md-12">
          <label class="col-xs-6">UOM Name</label>
          <label class="col-xs-6">Kilogram</label>
        </div>
        <div class="col-md-12">
          <label class="col-xs-6">UOM Code</label>
          <label class="col-xs-6">KG</label>
        </div>
        </a>
        </li>
        
    </ul>
    <div class="clearfix"></div>
  </div>
  <!-- left-side end--> 
  
  <!-- right-side start-->
  <div id="mobile_second_container" class="right-side col-md-9 no-marg animated slideInRight">
    <div class="clearfix"></div>
    <div class="tab-content"> 
      <!-- Master tab start-->
      
      <div class="tab-pane active in" id="AddUoM">        
          <div class="panel-group" id="accordion">
            <div class="panel panel-default">
              <div class="panel-heading">
                <h4 class="panel-title"> <a data-toggle="collapse" data-parent="#accordion" href="#collapse1"> UOM Details</a> </h4>
              </div>
              <div id="collapse1" class="panel-collapse collapse in">
                <div class="panel-body">
                <form>
                  <label class="col-md-6 col-xs-12">UOM Name<span class="red">*</span>
                    <input type="text" class="form-control UOMName" disabled="disabled">
                  </label>
                   <label class="col-md-6 col-xs-12">UOM Code<span class="red">*</span>
                    <input type="text" class="form-control UOMCod" disabled="disabled">
                  </label>
                  <label class="col-md-6 col-xs-12">Description<span class="red">*</span>
                    <input type="text" class="form-control Description" disabled="disabled">
                  </label>            
                  <div class="col-md-6 col-xs-12">
                    <label class="checkbox-inline">
                      <input type="checkbox" value="">
                      Active </label>
                  </div>
                  <div class="clearfix"></div>
                    <div class="col-md-12 text-center">
                      <button type="button"  class="btn btn-info saveBtn">Save</button>
                      <button type="button"  class="btn btn-info CamcleBtn">Camcle</button>
                    </div>
                 </form>
                </div>
              </div>
            </div>
          </div>        
      </div>
      
      <div class="tab-pane" id="AddUoM2">       
          <div class="panel-group" id="accordion">
            <div class="panel panel-default">
              <div class="panel-heading">
                <h4 class="panel-title"> <a data-toggle="collapse" data-parent="#accordion" href="#collapse1"> UOM Details</a> </h4>
              </div>
              <div id="collapse1" class="panel-collapse collapse in">
                <div class="panel-body">
                 <form>
                  <label class="col-md-6 col-xs-12">UOM Name2<span class="red">*</span>
                    <input type="text" class="form-control UOMName" disabled="disabled">
                  </label>
                   <label class="col-md-6 col-xs-12">UOM Code2<span class="red">*</span>
                    <input type="text" class="form-control UOMCod" disabled="disabled">
                  </label>
                  <label class="col-md-6 col-xs-12">Description2<span class="red">*</span>
                    <input type="text" class="form-control Description" disabled="disabled">
                  </label>                  
                  <div class="col-md-6 col-xs-12">
                    <label class="checkbox-inline">
                      <input type="checkbox" value="">
                      Active </label>
                  </div>
                  <div class="clearfix"></div>
                  <div class="col-md-12 text-center">
                      <button type="button"  class="btn btn-info">Save</button>
                      
                    </div>
                   </form>
                </div>
              </div>
            </div>
          </div>
      </div>
      <!-- Master tab end--> 
      
                      
    </div>
  </div>
  <!-- right-side end--> 
</div>
<!-- full-container end-->
    <div class="feedback">
        <div id="" style='display:none;' class="feedback-form col-xs-4 col-md-4 panel panel-default">
            <div class="form panel-body">
                <div class="col-lg-12 text-center">SIDEBAR ACTIVE COLOR</div>
                <div class="clearfix"></div>
                <div class="divider"></div>
                <ul class="colorpicker">
                    <li class="colorbox" style="background:#cc0000" data-foreground="#fbd1d1" data-hover="#fbd1d1"></li>
                    <li class="colorbox" style="background:#586d1f" data-foreground="#eefbcc" data-hover="#eefbcc"></li>
                    <li class="colorbox" style="background:#205b98" data-foreground="#dfefff" data-hover="#dfefff"></li>
                    <li class="colorbox" style="background:#991238" data-foreground="#ffdee7" data-hover="#ffdee7"></li>
                    <li class="colorbox" style="background:#00796a" data-foreground="#ddfdda" data-hover="#ddfdda"></li>
                    <li class="colorbox" style="background:#2c2c2c" data-foreground="#d6d6d6" data-hover="#d6d6d6"></li>
                    <li class="colorbox" style="background:#F44336" data-foreground="#fde1de" data-hover="#fde1de"></li>
                    <li class="colorbox" style="background:#2196F3" data-foreground="#cfeaff" data-hover="#cfeaff"></li>
                    <li class="colorbox" style="background:#4caf50" data-foreground="#deffdf" data-hover="#deffdf"></li>
                    <li class="colorbox" style="background:#ff9800" data-foreground="#fffadf" data-hover="#fffadf"></li>
                    <li class="colorbox" style="background:#26a69a" data-foreground="#eafffd" data-hover="#eafffd"></li>
                    <li class="colorbox" style="background:#9c27b0" data-foreground="#fae6ff" data-hover="#fae6ff"></li>
                    <li class="colorbox" style="background:#283593" data-foreground="#c3caff" data-hover="#c3caff"></li>
                    <li class="colorbox" style="background:#424242" data-foreground="#d2d2d2" data-hover="#d2d2d2"></li>
                    <li class="colorbox" style="background:#546e7a" data-foreground="#d8edf7" data-hover="#d8edf7"></li>
                    <li class="colorbox" style="background:#00bcd4" data-foreground="#e1fcff" data-hover="#e1fcff"></li>
                    <li class="colorbox" style="background:#e91e63" data-foreground="#e8e0ff" data-hover="#e8e0ff"></li>
                    <li class="colorbox" style="background:#544741" data-foreground="#dadada" data-hover="#dadada"></li>
                </ul>
            </div>
            <div class="col-lg-12 text-center">TILES BACKGROUND IMAGES</div>
            <div class="clearfix"></div>
            <div class="divider"></div>
            <ul class="backgroundImg">
                <li class="BackImg" style="background:url('<%=request.getContextPath()%>/resource/images/2.jpg?appVer=${appVer}')"></li>
                <li class="BackImg" style="background:url('<%=request.getContextPath()%>/resource/images/4.jpg?appVer=${appVer}')"></li>
                <li class="BackImg" style="background:url('<%=request.getContextPath()%>/resource/images/5.jpg?appVer=${appVer}')"></li>
                <li class="BackImg" style="background:url('<%=request.getContextPath()%>/resource/images/6.jpg?appVer=${appVer}')"></li>
                <li class="BackImg" style="background:url('<%=request.getContextPath()%>/resource/images/1.jpg?appVer=${appVer}')"></li>
                <li class="BackImg" style="background:url('<%=request.getContextPath()%>/resource/images/3.jpg?appVer=${appVer}')"></li>
                <li class="BackImg" style="background:url('<%=request.getContextPath()%>/resource/images/7.jpg?appVer=${appVer}')"></li>
                <li class="BackImg" style="background:url('<%=request.getContextPath()%>/resource/images/8.jpg?appVer=${appVer}')"></li>
                <li class="BackImg" style="background:url('<%=request.getContextPath()%>/resource/images/9.jpg?appVer=${appVer}')"></li>
            </ul>
            <div class="clearfix"></div>
        </div>
        <div class="feedback-tab"><i class="fa fa-cog"></i></div>
    </div>

    <!-- full-container end-->
    <div class="clearfix"></div>
    <div class="footer">@ All Right Reserved NovelERP</div>
</body>

</html>