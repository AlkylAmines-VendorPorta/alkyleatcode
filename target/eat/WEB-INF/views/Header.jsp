<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/resource/css/bootstrap.css?appVer=${appVer}">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/resource/css/jquery.dataTables.min.css?appVer=${appVer}">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/resource/css/style.css?appVer=${appVer}">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/resource/css/homeSlider.css?appVer=${appVer}">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/resource/css/dataTables.bootstrap.min.css?appVer=${appVer}">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/resource/css/TableResponsive.css?appVer=${appVer}">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/resource/css/Responsive.css?appVer=${appVer}">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/resource/css/animate.min.css?appVer=${appVer}" media="all"></head>
<body>
<nav class="navbar navbar-inverse navbar-fixed-top">
        <div class="container-fluid">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="#"><img alt="" src="<%=request.getContextPath()%>/resource/images/Mahadiscom_Logo.png?appVer=${appVer}"></a>
                <a href="javascript:void(0)" class="LoginLink LoginLinkMobile"><span class="glyphicon glyphicon-lock"></span> Login</a>
            </div>
            <div class="collapse navbar-collapse" id="myNavbar">
                <ul class="nav navbar-nav  main-nav">
                    <li class="active HomeLink"><a href="home" id="home">Home</a></li>
                    <li><a href="tenders" id="tenders">Tenders</a></li>
                    <li><a href="bids" id="bids">Bids</a></li>
                    <li><a href="profile" id="profile">Profile</a></li>
                    <li><a href="help">Help</a></li>
                    <li><a href="contactus">Contact Us</a></li>
                    <li><a href="sitemap">Site Map</a></li>
                </ul>
                <ul id="SmallNav" class="nav navbar-nav navbar-right sidenav">
                    <li><a href="disclaimer"><span class="glyphicon glyphicon-exclamation-sign"></span>Disclaimer</a></li>
                    <li><a href="#"><span class="glyphicon glyphicon-th-list"></span>Related Links</a></li>
                    <li><a href="#"><span class=" glyphicon glyphicon-credit-card"></span>Payments</a></li>
                   <li><a href="#"><span class=" glyphicon glyphicon-cog"></span>Utilities</a></li>
                    <li><a href="#"><span class="glyphicon glyphicon-calendar"></span>Calendar</a></li>
                    <li><a href="download"><span class="glyphicon glyphicon-download-alt"></span>Downloads</a></li>
                    <li><a href="#"><span class="glyphicon glyphicon-list-alt"></span> Public Notices</a></li>
                    <li><a href="Notification"><span class="glyphicon glyphicon-bell"></span> Notification</a></li>
                    <li><a href="registration"><span class="glyphicon glyphicon-registration-mark"></span> Registration</a></li>
                    <li><a href="javascript:void(0)" class="LoginLink"><span class="glyphicon glyphicon-lock"></span> Login</a></li>
                </ul>
                <div class="welcome"> <span class="glyphicon glyphicon-user" style="color:gray"></span> Welcome TestContract |<a href="">Logout</a></div>
            </div>
        </div>
    </nav>
</body>
<script src="<%=request.getContextPath()%>/resource/js/jquery-3.2.1.min.js?appVer=${appVer}"></script>
<script> 
$(document).ready(function() {
	$(".nav a").on("click", function(){
		   $(".nav").find(".active").removeClass("active");
		   $(this).parent().addClass("active");
		});
});</script>

</html>