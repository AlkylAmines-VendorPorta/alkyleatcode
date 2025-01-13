<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">

<head>
<%@ page isELIgnored="false" %>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>E-Auctionapp e-Tendering</title>
    <meta name="description" content="Fullscreen Pageflip Layout with BookBlock" />
    <meta name="keywords" content="fullscreen pageflip, booklet, layout, bookblock, jquery plugin, flipboard layout, sidebar menu" />
    <meta name="author" content="Codrops" />
    <link rel="stylesheet" href="<%=request.getContextPath()%>/resource/css/bootstrap.css?appVer=${appVer}">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/resource/css/jquery.dataTables.min.css?appVer=${appVer}">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/resource/css/style.css?appVer=${appVer}">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/resource/css/homeSlider.css?appVer=${appVer}">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/resource/css/dataTables.bootstrap.min.css?appVer=${appVer}">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/resource/css/TableResponsive.css?appVer=${appVer}">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/resource/css/Responsive.css?appVer=${appVer}">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/resource/css/animate.min.css?appVer=${appVer}" media="all">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/resource/css/login.css?appVer=${appVer}" media="all">
    
   <%--  <script type="text/javascript" src="<%=request.getContextPath()%>/resource/js/login.js?appVer=${appVer}"> 
</script>   --%>

<style>
.capbox {
	    /* background-color: #92D433; */
    /* border: #B3E272 0px solid; */
    border-width: 0px 12px 0px 0px;
    display: inline-block;
    zoom: 1;
    /* padding: 15px 40px 15px 15px; */
        width: 100%;
            margin-top: 10px;
	}

.capbox-inner {
	font: bold 11px arial, sans-serif;
	color: #000000;
	background-color: #ffffff;
	margin: 5px auto 0px auto;
	padding: 3px;
	-moz-border-radius: 4px;
	-webkit-border-radius: 4px;
	border-radius: 4px;
	}

#CaptchaDiv {
    font: bold 17px verdana, arial, sans-serif;
    font-style: italic;
    color: #000000;
    background-color: #eaeaea;
    padding: 4px;
    -moz-border-radius: 4px;
    -webkit-border-radius: 4px;
    border-radius: 0PX;
    text-align: center;
	}

/* #CaptchaInput { margin: 1px 0px 1px 0px; width: 135px; } */
</style>

</head>

<body style="background:#fff !important;">
    <nav class="navbar navbar-inverse navbar-fixed-top">
        <div class="container-fluid">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="#"><img alt="" src="<%=request.getContextPath()%>/resource/images/Mahadiscom_Logo.png?appVer=${appVer}"></a>
                <!-- <a href="javascript:void(0)" class="LoginLink LoginLinkMobile"><span class="glyphicon glyphicon-lock"></span> Login</a> -->
            </div>
            <div class="collapse navbar-collapse" id="myNavbar">
                <ul class="nav navbar-nav  main-nav">
                    <li class="active HomeLink"><a href="home">Home</a></li>
                    <li><a href="tenders">Tenders</a></li>
                    <li><a href="bids">Bids</a></li>
                    <li><a href="profile">Profile</a></li>
                    <li><a href="help">Help</a></li>
                    <li><a href="contactus">Contact Us</a></li>
                    <li><a href="sitemap">Site Map</a></li>
                </ul>
                 <ul id="SmallNav" class="nav navbar-nav navbar-right sidenav">
                    <li><a href="disclaimer"><span class="glyphicon glyphicon-exclamation-sign"></span>Disclaimer</a></li>
                    <li><a href="#"><span class="glyphicon glyphicon-th-list"></span>Related Links</a></li>
                    <!-- <li><a href="#"><span class=" glyphicon glyphicon-credit-card"></span>Payments</a></li>
                   <li><a href="#"><span class=" glyphicon glyphicon-cog"></span>Utilities</a></li> -->
                    <li><a href="#"><span class="glyphicon glyphicon-calendar"></span>Calendar</a></li>
                    <li><a href="download"><span class="glyphicon glyphicon-download-alt"></span>Downloads</a></li>
                    <li><a href="#"><span class="glyphicon glyphicon-list-alt"></span> Public Notices</a></li>
                    <li><a href="Notification"><span class="glyphicon glyphicon-bell"></span> Notification</a></li>
                    <li><a href="registration"><span class="glyphicon glyphicon-registration-mark"></span> Registration</a></li><!-- 
                    <li><a href="javascript:void(0)" class="LoginLink"><span class="glyphicon glyphicon-lock"></span> Login</a></li> -->
                </ul>
            </div>
        </div>
    </nav>
    <section class="homeSection">
        <div class="container-fluid NoPadding">
            <!-- Add this css File in head tag-->
            <!-- <div id="first-slider">
                <div id="carousel-example-generic" class="carousel slide carousel-fade">
                    Indicators
                    <ol class="carousel-indicators">
                        <li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>
                        <li data-target="#carousel-example-generic" data-slide-to="1"></li>
                        <li data-target="#carousel-example-generic" data-slide-to="2"></li>
                        <li data-target="#carousel-example-generic" data-slide-to="3"></li>
                    </ol>
                    Wrapper for slides
                    <div class="carousel-inner" role="listbox">
                        Item 1
                        <div class="item active slide1">
                            <div class="row">
                                <div class="container">
                                    <div class="col-md-3 text-right">
                                        <img class="slideicon" data-animation="animated zoomInLeft" src="images/window-domain.png?appVer=${appVer}">
                                    </div>
                                    <div class="col-md-9 text-left">
                                        <h3 data-animation="animated bounceInDown">Welcome To MSEDCL</h3>
                                        <h4 data-animation="animated bounceInUp">E-Tendering And Procurement</h4>
                                    </div>
                                </div>
                            </div>
                        </div>
                        Item 2
                        <div class="item slide2">
                            <div class="row">
                                <div class="container">
                                    <div class="col-md-9 text-right">
                                        <h3 data-animation="animated bounceInDown"> BUYER</h3>
                                        <h4 data-animation="animated bounceInUp">Armed with complete understanding of your companys spending, work with the right suppliers at the right time 
to lower costs and minimize risks.</h4>
                                    </div>
                                    <div class="col-md-3 text-right">
                                        <img class="slideicon" data-animation="animated zoomInLeft" src="images/rack-server-unlock.png?appVer=${appVer}">
                                    </div>
                                </div>
                            </div>
                        </div>
                        Item 3
                        <div class="item slide3">
                            <div class="row">
                                <div class="container">
                                    <div class="col-md-3 text-left">
                                        <img class="slideicon" data-animation="animated zoomInLeft" src="images/globe-network.png?appVer=${appVer}">
                                    </div>
                                    <div class="col-md-9 text-left">
                                        <h3 data-animation="animated bounceInDown">SUPPLIER</h3>
                                        <h4 data-animation="animated bounceInUp">Grow your business and boost sales connecting with buyers anytime, anywhere.</h4>
                                    </div>

                                </div>
                            </div>
                        </div>
                        Item 4
                        <div class="item slide4">
                            <div class="row">
                                <div class="container">
                                    <div class="col-md-9 text-right">
                                        <h3 data-animation="animated bounceInDown">Buyer-Seller Relationship in the</h3>
                                        <h4 data-animation="animated bounceInUp">Procurement of Logistical Services</h4>
                                    </div>
                                    <div class="col-md-3 text-right">
                                        <img class="slideicon" data-animation="animated zoomInLeft" src="images/internet-speed.png?appVer=${appVer}">
                                    </div>
                                </div>
                            </div> 
                        </div>
                        End Item 4

                    </div>
                    End Wrapper for slides
                    <a class="left carousel-control" href="#carousel-example-generic" role="button" data-slide="prev">
                        <i class="glyphicon glyphicon-menu-left"></i><span class="sr-only">Previous</span>
                    </a>
                    <a class="right carousel-control" href="#carousel-example-generic" role="button" data-slide="next">
                        <i class="glyphicon glyphicon-menu-right"></i><span class="sr-only">Next</span>
                    </a>
                </div>
            </div> -->
<!-- <section class="LogInDiv"> -->
        <div class="container-fluid NoPadding margin100">            
            <div class="col-sm-2 col-sm-push-10 colsmpush10 nopadding ">
                    <div class="card padding20 animated loginDivMobile zoomInLeft">
                        <div class="text-center">
                            <h3 class="loginheading"><b>Log In</b></h3></div>
                        <form id="ajax-login-form" action="/eTendering/login" method="post" role="form" autocomplete="off">
                            <div class="form-group">
                            <span id="errorMsg">
                            <c:if test="${not empty errorMsg}">
							   <c:out value="${errorMsg}"/>
							</c:if></span>
                           </div> 
                               <div class="styled-input">
							      <input type="text" id="username" name="username" required />
							      <label>Email</label>
							      <span></span> </div>

                            
                               <div class="styled-input">
							      <input type="password" id="password" name="password" required />
							      <label>Password</label>
							      <span></span> </div>
                             
                              <!-- captcha code  -->
                             <br>
               					 <div class="col-sm-12">
                    				  <div class="row form-group  formgroup">
                                  	  <!--  <label class="col-sm-4 control-label">Captcha:</label> -->
                                      <div class="capbox">

                                        <div id="CaptchaDiv"></div>

                                        <div class="capbox-inner">
                                        <input type="hidden" id="txtCaptcha">
                                        <!-- <div class="styled-input">
                                        <input type="text" name="CaptchaInput" id="CaptchaInput" name="CaptchaInput" size="15">
                                        <label>Type the above number:</label>
										</div>	 -->
										 <div class="form-group">
			                               <div class="styled-input">
										      <input type="text" id="CaptchaInput" name="CaptchaInput" required />
										      <label>Type the above number</label>
										      <span></span> </div>
			                             </div>
                                        </div>
                            													
                           			 <!-- <div class="form-group">
									<error>&nbsp;</error>
						   		 	</div> -->
									</div>
									</div>
									</div>
                      			 
				      			

                             <!-- captcha code -->
                                <div class="row">
                                    <div class="col-xs-12 buttonmargin">
                                        <input type="submit" name="login-submit" id="login-submit" tabindex="4" class="form-control btn btn-info" value="Log In">
                                    </div>
                                </div>
                                <div class="clearfix"></div>
                            <div class="form-group">
                                <div class="row">
                                    <div class="col-xs-6">
                                        <a href="registration" class="loginBtn">New User ?</a>
                                        </div>
                                         <div class="col-xs-6">
                                        <a href="" class="forgot-password">Forgot Password?</a>
                                    </div>
                                </div>
                            </div>
                            <input type="hidden" class="hide" name="token" id="token" value="a465a2791ae0bae853cf4bf485dbe1b6">
                        </form>
                    </div>
                </div>
                <div class="col-sm-5 col-sm-pull-2 colsmpull2">
	        <div class="panel panel-info card animated zoomInLeft">
 				  <div class="panel-heading blinker"><span class="glyphicon glyphicon-book"></span>Latest Tenders</div>
					 <div class="homepanel-body panel-body">
                                   <div class="col-xs-4 nopadding"><b>Title</b></div>
                                    <div class="col-xs-4 nopadding"><b>Sale Opening</b></div>
                                    <div class="col-xs-4 nopadding"><b>Submission Due </b></div>
                               <marquee direction="up" height="320px" scrollamount="3" onmouseover="this.stop()" onmouseout="this.start()">
                                  <div class="form-group rowcolor">
                                    <div class="col-xs-4 nopadding"><a href="Tenders.html">Maharashtra State Electricity Distribution Co. Ltd.</a></div>
                                    <div class="col-xs-4 nopadding">21st October 2017</div>
                                    <div class="col-xs-4 nopadding">21st October 2017</div>
                                  </div>                                  
                                  <div class="clearfix"></div>
                                  <div class="form-group">
                                    <div class="col-xs-4 nopadding"><a href="Tenders.html">Maharashtra State Electricity Distribution Co. Ltd.</a></div>
                                    <div class="col-xs-4 nopadding">21st October 2017</div>
                                    <div class="col-xs-4 nopadding">21st October 2017</div>
                                  </div>
                                  <div class="clearfix"></div>
                                  <div class="form-group rowcolor">
                                    <div class="col-xs-4 nopadding"><a href="Tenders.html">Maharashtra State Electricity Distribution Co. Ltd.</a></div>
                                    <div class="col-xs-4 nopadding">21st October 2017</div>
                                    <div class="col-xs-4 nopadding">21st October 2017</div>
                                  </div>
                                  <div class="clearfix"></div>
                                </marquee>
					 </div>
  		    </div>
        </div>   
        <div class="col-sm-5 col-sm-pull-2 colsmpull2">
        			<div class="panel panel-info card animated zoomInLeft marginleft">
     				  <div class="blinker panel-heading"><span class="glyphicon glyphicon-book"></span>Latest Announcements</div>
    					 <div class="homepanel-body panel-body">
                                   <div class="col-xs-4 nopadding"><b>Title</b></div>
                                    <div class="col-xs-4 nopadding"><b>Sale Closing</b></div>
                                    <div class="col-xs-4 nopadding"><b>Submission Due </b></div>
                               <marquee direction="up" height="320px" scrollamount="3" onmouseover="this.stop()" onmouseout="this.start()">
                                  <div class="form-group rowcolor">
                                    <div class="col-xs-4 nopadding"><a href="Tenders.html">Maharashtra State Electricity Distribution Co. Ltd.</a></div>
                                    <div class="col-xs-4 nopadding">21st October 2017</div>
                                    <div class="col-xs-4 nopadding">21st October 2017</div>
                                  </div>
                                  <div class="clearfix"></div>
                                  <div class="form-group">
                                    <div class="col-xs-4 nopadding"><a href="Tenders.html">Maharashtra State Electricity Distribution Co. Ltd.</a></div>
                                    <div class="col-xs-4 nopadding">21st October 2017</div>
                                    <div class="col-xs-4 nopadding">21st October 2017</div>
                                  </div>
                                  <div class="clearfix"></div>
                                  <div class="form-group rowcolor">
                                    <div class="col-xs-4 nopadding"><a href="Tenders.html">Maharashtra State Electricity Distribution Co. Ltd.</a></div>
                                    <div class="col-xs-4 nopadding">21st October 2017</div>
                                    <div class="col-xs-4 nopadding">21st October 2017</div>
                                  </div>
                                  <div class="clearfix"></div>
                                </marquee>
					 </div>
  				    </div>
       			 </div> 
        </div>
    <!-- </section> -->
    
            <div class="container">
                <h3 class="headingBig">The MSECDL e-Tendering Network</h3>
                <h5 class="headingSmall">Get the most complete, most widely adopted strategic sourcing solutions</h5>
                <hr class="Divider">
                <div class="form-group">
                    <div class="col-sm-4">
                        <div class="Homecard text-center padding20 margin15">
                            <span class="glyphicon glyphicon-calendar Homeicon"></span>
                            <h4>FAST TIME-TO-VALUE</h4>
                            <p>Enjoy fast time-to-value and low total-cost-of-ownership with cloud-based delivery.</p>
                        </div>
                    </div>
                    <div class="col-sm-4">
                        <div class="Homecard text-center padding20 margin15">
                            <span class="glyphicon glyphicon-user Homeicon"></span>
                            <h4>USER-FRIENDLY APPLICATION</h4>
                            <p>From the simplest RFX to the most complex multi-parameter bids, handy wizards and tools help you launch events, collaborate.</p>
                        </div>
                    </div>
                    <div class="col-sm-4">
                        <div class="Homecard text-center padding20 margin15">
                            <span class="glyphicon glyphicon-dashboard Homeicon"></span>
                            <h4>QUICK AND EASY SUPPLIER DISCOVERY</h4>
                            <p>Enjoy fast time-to-value and low total-cost-of-ownership with cloud-based delivery.</p>
                        </div>
                    </div>
                </div>
                <div class="clearfix"></div>
                <div class="form-group">
                    <div class="col-sm-4">
                        <div class="Homecard text-center padding20 margin15">
                            <span class="glyphicon glyphicon-retweet Homeicon"></span>
                            <h4>SOURCING LIFECYCLE AUTOMATION</h4>
                            <p>Automate everything from supplier discovery and dynamic negotiations to savings tracking.</p>
                        </div>
                    </div>
                    <div class="col-sm-4">
                        <div class="Homecard text-center padding20 margin15">
                            <span class="glyphicon glyphicon-globe Homeicon"></span>
                            <h4>SOURCING EVENT SUPPORT</h4>
                            <p>Ensure event success with sourcing support desk and event-day management assistance. support is offered to both buyers and sellers.</p>
                        </div>
                    </div>
                    <div class="col-sm-4">
                        <div class="Homecard text-center padding20 margin15">
                            <span class="glyphicon glyphicon-time Homeicon"></span>
                            <h4>FAST TIME-TO-VALUE</h4>
                            <p>Enjoy fast time-to-value and low total-cost-of-ownership with cloud-based delivery.</p>
                        </div>
                    </div>
                </div>
            </div>
            <div class="container">
                <h3 class="headingBig">Our Service</h3>
                <h5 class="headingSmall">Improved process efficiency, faster payments, and more transparent transactions. Thats what Charles Seafoods Supply gets from collaborating </h5>
                <hr class="Divider">
                <div class="form-group col-sm-9 nopadding">
                    <div class="col-sm-6">
                        <div class="col-sm-2 col-xs-2"><span class="glyphicon glyphicon-calendar feautureicon"></span></div>
                        <div class="col-sm-10 col-xs-10">
                            <h4>FAST TIME-TO-VALUE</h4>
                            <p>Enjoy fast time-to-value and low total-cost-of-ownership with cloud-based delivery.</p>
                        </div>
                    </div>
                    <div class="col-sm-6">
                        <div class="col-sm-2 col-xs-2">
                            <span class="glyphicon glyphicon-user feautureicon"></span>
                        </div>
                        <div class="col-sm-10 col-xs-10">
                            <h4>USER-FRIENDLY APPLICATION</h4>
                            <p>From the simplest RFX to the most complex multi-parameter bids, handy wizards and tools help you launch events, collaborate.</p>
                        </div>
                    </div>
                    <div class="col-sm-6">
                        <div class="col-sm-2 col-xs-2">
                            <span class="glyphicon glyphicon-dashboard feautureicon"></span>
                        </div>
                        <div class="col-sm-10 col-xs-10">
                            <h4>QUICK AND EASY SUPPLIER DISCOVERY</h4>
                            <p>Enjoy fast time-to-value and low total-cost-of-ownership with cloud-based delivery.</p>
                        </div>
                    </div>

                    <div class="col-sm-6">
                        <div class="col-sm-2 col-xs-2">
                            <span class="glyphicon glyphicon-retweet feautureicon"></span>
                        </div>
                        <div class="col-sm-10 col-xs-10">
                            <h4>SOURCING LIFECYCLE AUTOMATION</h4>
                            <p>Automate everything from supplier discovery and dynamic negotiations to savings tracking.</p>
                        </div>
                    </div>
                    <div class="col-sm-6">
                        <div class="col-sm-2 col-xs-2">
                            <span class="glyphicon glyphicon-globe feautureicon"></span>
                        </div>
                        <div class="col-sm-10 col-xs-10">
                            <h4>SOURCING EVENT SUPPORT</h4>
                            <p>Ensure event success with sourcing support desk and event-day management assistance. support is offered to both buyers and sellers.</p>
                        </div>
                    </div>
                    <div class="col-sm-6">
                        <div class="col-sm-2 col-xs-2">
                            <span class="glyphicon glyphicon-time feautureicon"></span>
                        </div>
                        <div class="col-sm-10 col-xs-10">
                            <h4>FAST TIME-TO-VALUE</h4>
                            <p>Enjoy fast time-to-value and low total-cost-of-ownership with cloud-based delivery.</p>
                        </div>
                    </div>
                </div>
                <div class="col-sm-3 nopadding">
                    <h4>See More...</h4>
                    <div class="panel-group" id="accordion">
                        <div class="panel panel-default">
                            <div class="panel-heading">
                                <h4 class="panel-title">
        <a class="accordion-toggle" data-toggle="collapse" data-parent="#accordion" href="#collapseOne">
          CONDITIONS OF TENDER 
        </a>
      </h4>
                            </div>
                            <div id="collapseOne" class="panel-collapse collapse in">
                                <div class="panel-body">
                                    Anim pariatur cliche reprehenderit, enim eiusmod high life ccusamus terry richardson ad squid. 3 wolf moon officia aute, non cupidatat skateboard dolor brunch. Food truck quinoa.
                                </div>
                            </div>
                        </div>
                        <div class="panel panel-default">
                            <div class="panel-heading">
                                <h4 class="panel-title">
        <a class="accordion-toggle" data-toggle="collapse" data-parent="#accordion" href="#collapseTwo">
         SECTION I INSTRUCTIONS 
        </a>
      </h4>
                            </div>
                            <div id="collapseTwo" class="panel-collapse collapse">
                                <div class="panel-body">
                                    Anim pariatur cliche reprehenderit, enim eiusmod high life .
                                </div>
                            </div>
                        </div>
                        <div class="panel panel-default">
                            <div class="panel-heading">
                                <h4 class="panel-title">
        <a class="accordion-toggle" data-toggle="collapse" data-parent="#accordion" href="#collapseThree">
          SECTION II RE
        </a>
      </h4>
                            </div>
                            <div id="collapseThree" class="panel-collapse collapse">
                                <div class="panel-body">
                                    Anim pariatur cliche reprehenderit, enim eiusmod high life accusamus .
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
    
    <div class="footer">
        © 2017 MSECDL. All Rights Reserved.
    </div>
</body>
<script src="<%=request.getContextPath()%>/resource/js/jquery-3.2.1.min.js?appVer=${appVer}"></script>
<script src="<%=request.getContextPath()%>/resource/js/bootstrap.min.js?appVer=${appVer}"></script>
<script src="<%=request.getContextPath()%>/resource/js/homeSlider.js?appVer=${appVer}"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resource/js/Home.js?appVer=${appVer}">
   </script>
<script> 
$(document).ready(function() {
	$(".nav a").on("click", function(){
		   $(".nav").find(".active").removeClass("active");
		   $(this).parent().addClass("active");
		});
});</script>

<script>
$("#login-submit").on("click", function(){
	debugger;
	checkform();
	ValidCaptcha();
	});

// Captcha Script

function checkform(){
	debugger;
var why = "";

if(CaptchaInput.value == ""){
why += "- Please Enter CAPTCHA Code.\n";
}
if(CaptchaInput.value != ""){
if(ValidCaptcha(CaptchaInput.value) == false){
why += "- The CAPTCHA Code Does Not Match.\n";
}
}
if(why != ""){
alert(why);
return false;
}
}

var alpha = new Array('A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z','a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z');
var i;
for (i=0;i<6;i++){
  var a = alpha[Math.floor(Math.random() * alpha.length)];
  var b = Math.ceil(Math.random() * 9)+ '';
  var c = alpha[Math.floor(Math.random() * alpha.length)];
  var d = Math.ceil(Math.random() * 9)+ '';
  var e = Math.ceil(Math.random() * 9)+ '';
  var f = alpha[Math.floor(Math.random() * alpha.length)];
  var g = Math.ceil(Math.random() * 9)+ '';
}
  var code = a + ' ' + b + ' ' + ' ' + c + ' ' + d + ' ' + e + ' '+ f + ' ' + g;

document.getElementById("txtCaptcha").value = code;
document.getElementById("CaptchaDiv").innerHTML = code;

// Validate input against the generated number
function ValidCaptcha(){
	debugger;
var str1 = removeSpaces(document.getElementById('txtCaptcha').value);
var str2 = removeSpaces(document.getElementById('CaptchaInput').value);
if (str1 == str2){
return true;
}else{
return false;
}
}

// Remove the spaces from the entered and generated code
function removeSpaces(string){
return string.split(' ').join('');
}


</script>
</html>