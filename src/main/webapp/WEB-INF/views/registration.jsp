<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>E-Auctionapp e-Tendering</title>
    <meta name="description" content="Fullscreen Pageflip Layout with BookBlock" />
    <meta name="keywords" content="fullscreen pageflip, booklet, layout, bookblock, jquery plugin, flipboard layout, sidebar menu" />
    <meta name="author" content="Codrops" />
    <link rel="stylesheet" href="<%=request.getContextPath()%>/resource/commons/css/bootstrap.css?appVer=${appVer}">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/resource/commons/css/jquery.dataTables.min.css?appVer=${appVer}">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/resource/commons/css/style.css?appVer=${appVer}">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/resource/commons/css/dataTables.bootstrap.min.css?appVer=${appVer}">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/resource/commons/css/TableResponsive.css?appVer=${appVer}">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/resource/commons/css/Responsive.css?appVer=${appVer}">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/resource/commons/css/login.css?appVer=${appVer}">
</head>
<body>
<nav class="navbar navbar-inverse navbar-fixed-top">
        <div class="container-fluid">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="home"><img alt="" src="<%=request.getContextPath()%>/resource/commons/images/Mahadiscom_Logo.png?appVer=${appVer}"></a>
                <!-- <a href="javascript:void(0)" class="LoginLink LoginLinkMobile"><span class="glyphicon glyphicon-lock"></span> Login</a> -->
            </div>
         <!--    <div class="collapse navbar-collapse" id="myNavbar">
                <ul class="nav navbar-nav  main-nav">
                    <li class="active HomeLink"><a href="Home.html">Home</a></li>
                    <li><a href="Tenders.html">Tenders</a></li>
                    <li><a href="Bids.html">Bids</a></li>
                    <li><a href="Profile.html">Profile</a></li>
                    <li><a href="Help.html">Help</a></li>
                    <li><a href="ContactUs.html">Contact Us</a></li>
                    <li><a href="SiteMap.html">Site Map</a></li>
                </ul>
                <ul id="SmallNav" class="nav navbar-nav navbar-right sidenav">
                    <li><a href="Disclaimer.html"><span class="glyphicon glyphicon-exclamation-sign"></span>Disclaimer</a></li>
                    <li><a href="#"><span class="glyphicon glyphicon-calendar"></span>Calendar</a></li>
                    <li><a href="Download.html"><span class="glyphicon glyphicon-download-alt"></span>Downloads</a></li>
                    <li><a href="#"><span class="glyphicon glyphicon-list-alt"></span> Public Notices</a></li>
                    <li><a href="Notification.html"><span class="glyphicon glyphicon-bell"></span> Notification</a></li>
                    <li><a href="Profile.html"><span class="glyphicon glyphicon-registration-mark"></span> Registration</a></li>
                    <li><a href="javascript:void(0)" class="LoginLink"><span class="glyphicon glyphicon-lock"></span> Login</a></li>
                </ul>
            </div> -->
        </div>
    </nav>
     <div id="content-wrapper">
        <!-- PAGE CONTENT -->
        <div class="container-fluid" style="margin-bottom:40px;">
                    <div class="col-sm-4 col-sm-offset-4 mobileNoPadding cardshodow" style="background: #fff;">
                   <form action="register" method="post" id="userForm">
                    <span id="errorMsg">
                            <c:if test="${not empty errorMsg}">
							   <c:out value="${errorMsg}"/>
							</c:if></span>
                             <div class="form-group">
                             <div class="col-sm-12"  >
                               <div class="styled-input">
							      <input type="text" id="email" name="email" required />
							      <label>Email Id<span class="red">*</span></label>
							      <span></span> </div></div>
                             </div>
                             
                             <div class="form-group">
                              <div class="col-sm-6 "  >
                               <div class="styled-input">
							      <input type="password" id="password" name="password" required />
							      <label>Password<span class="red">*</span></label>
							      <span></span> </div></div>
							      
							         <div class="col-sm-6 "  >
							         <div class="styled-input">
							      <input type="password" id="confirmPswd" name="confirmPswd" required />
							      <label>Confirm Password<span class="red">*</span> 
							      <img alt="" class="okTick" src="<%=request.getContextPath()%>/resource/commons/images/ok_mark_clip_art.jpg?appVer=${appVer}" width="5%" style="display: none">
							      </label>
							      <span></span> </div></div>
                             </div>
                             
                            <div class="form-group">
                            <div class="col-sm-12"  >
                               <div class="styled-input">
							      <input type="text" id="companyName" name="partner[name]" required />
							      <label>Company Name<span class="red">*</span></label>
							      <span></span> </div></div>
                             </div>
                             
                             <div class="form-group">
                             <div class="col-sm-6 " >
                               <div class="styled-input">
							      <input type="text" id="crnNumber" name="crnNumber" required />
							      <label >CRN Number<span class="red">*</span></label>
							      <span></span> </div>
							      </div>
                             
                             <div class="col-sm-6 " >
                               <div class="styled-input">
							      <input type="text" id="panNumber" name="panNumber" required />
							      <label >Pan Number<span class="red">*</span></label>
							      <span></span> </div>
                             </div>
                             </div>
                                           
                              <div class="form-group">
                              <div class="col-sm-6 " >
                               <div class="styled-input">
							      <input type="text" id="telNumber" name="telSNumber" required />
							      <label >Tel Number<span class="red">*</span></label>
							      <span></span> </div>
                             </div>
                           
                            <div class="col-sm-6 " >
                               <div class="styled-input">
							      <input type="text" id="faxNumber" name="faxNumber" required />
							      <label >Fax Number<span class="red"></span></label>
							      <span></span> </div>
                             </div>
                             </div>
                             
                              <div class="form-group">
                            <div class="col-sm-6"  >
                               <div class="styled-input">
							      <input type="text" id="Mobile" name="Mobile" required />
							      <label>Mobile Number<span class="red">*</span></label>
							      <span></span> </div></div>
                             </div>
                             
                           <div class="clearfix"></div> 
                            <div class="clearfix"></div> 
                            <div class="clearfix"></div> 
                        <div class="form-group ">                       
						<div class="col-sm-12 text-left">
                           By clicking Register, you agree to our <a href="home" > <b>Terms </b></a> and confirm that you have read our Data Policy.
                        </div>
                        </div>
                        
                        <div class="clearfix"></div> 
                        <div class="form-group ">                       
						<div class="col-sm-12 text-right">
                            <button class="btn-all btn btn-info formSubmit" id="register">Register</button>
                           <a href="home" class="btn btn-info"> Cancel</a>
                        </div>
                        </div><div class="clearfix"></div>
                    <br>
                        <div class="clearfix"></div>
                            </form>
                    </div>
                    <!-- End Container -->
                
                     </div>   
                </div>
       
        <!-- PAGE CONTENT -->
         <div class="height20"></div>
<div class="footer">
        © 2017 E-Auctionapp. All Rights Reserved.
    </div>
</body>
<script src="<%=request.getContextPath()%>/resource/commons/js/jquery-3.2.1.min.js?appVer=${appVer}"></script>
<script src="<%=request.getContextPath()%>/resource/commons/js/bootstrap.min.js?appVer=${appVer}"></script>
<script src="<%=request.getContextPath()%>/resource/commons/js/jquery.dataTables.min.js?appVer=${appVer}"></script>
<script src="<%=request.getContextPath()%>/resource/commons/js/dataTables.bootstrap.min.js?appVer=${appVer}"></script>
<script src="<%=request.getContextPath()%>/resource/commons/js/bootstrap-datepicker.js?appVer=${appVer}"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resource/commons/js/Profile.js?appVer=${appVer}"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resource/commons/js/jquery.serializeJSON.js?appVer=${appVer}"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resource/commons/js/form.js?appVer=${appVer}"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resource/context/js/user.js?appVer=${appVer}"></script>

<script>
/*hide the tick image of confirm password  */
$(document).ready(function(){
    $(".okTick").hide();   
});
/*hide the tick image of confirm password  */
 
</script>

<!-- script for confirm password -->
<script>
$("#confirmPswd").on('blur', function () {
	    var pass1 = document.getElementById("password").value;
        var pass2 = document.getElementById("confirmPswd").value;
        if (pass1 != pass2) {
            /* alert("Passwords Do not match"); */
            $('#errorMsg').html(" Passwords Do not match.")
            document.getElementById("password").style.borderColor = "#E34234";
            document.getElementById("confirmPswd").style.borderColor = "#E34234";
            $(".okTick").hide();
        }
        else {
            
            $(".okTick").show();
        }
    });
</script>

<!-- script for confirm password -->

</html>