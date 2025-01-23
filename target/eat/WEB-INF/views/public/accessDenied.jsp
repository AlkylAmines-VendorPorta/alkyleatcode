<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

<style>
.sesiondiv{width: 100%;
    text-align: center;
    margin-top: 107px;
    color: black;
    font-weight: bold;
    font-size: 64px;
    font-family: initial;}
    .seesionh1{    font-size: 50px;
    font-weight: bold;}
    .robot{width: 600px;}
    #SmallNav{display: none;}
    .navbar-form{display: none;}
    .main-nav{display: none;}
    .navbar-inverse{ border-bottom: 2px solid #ccc;}
    .navbar-brand{width: 100%;
    overflow: hidden;
    height: 103px;}
    .navbar-brand > img {
    display: block;
    margin-left: 41%;
    height: 85px;
}
.navbar-header{width: 100%;
    text-align: center;}
</style>
 <div class="container-fluid slidepadding">
        <section class="center-section">
        
        <div class="sesiondiv">
        	<img alt="" class="robot" src="resources/${appMode}/commons/images/AccessDenied.jpg?appVer=${appVer}">
        	 <H2>Take me to <a href="<%=request.getContextPath()%>"> <span class="glyphicon glyphicon-home"></span> Home</a></H2>
        </div>	
        </section> 
        </div>
