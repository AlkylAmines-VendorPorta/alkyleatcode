<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
    .robot{width: 230px;}
    .accessDenied{width: 600px;}
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
<input type="hidden" id="viewType" value="${viewType}" />

 <div class="container-fluid slidepadding">
        <section class="center-section">
        
        <div class="sesiondiv">
        <c:if test="${viewType.equalsIgnoreCase('sessionExpired')}">
			<h1 class="seesionh1">Session Expired</h1>
			<h1 class="">Please log in again .</h1>
        	<!-- <img alt="" class="robot" src="resources/${appMode}/commons/images/robot.jpg?appVer=${appVer}"> -->
	    </c:if>
	    <c:if test="${viewType.equalsIgnoreCase('accessDenied')}">
			<img alt="" class="accessDenied" src="resources/${appMode}/commons/images/AccessDenied.jpg?appVer=${appVer}">
	    </c:if>
        	
        	 <H2> <a href="<%=request.getContextPath()%>"> <span class="glyphicon glyphicon-home"></span>Back To Home</a></H2>
        	 <%-- <%=request.getContextPath()%> --%>
        </div>	
        </section> 
        </div>
