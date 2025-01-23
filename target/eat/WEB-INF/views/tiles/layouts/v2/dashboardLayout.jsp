<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
			
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
		<!-- Context path Set -->
       		<% String context=request.getContextPath(); %>
		<!-- Context path Set -->
		<!--css included  -->
        <link href="<%=context%>/resources/${appMode}/tilescommon/css/bootstrap.css?appVer=${appVer}" rel="stylesheet">
        <link rel="stylesheet" href="<%=context%>/resources/${appMode}/tilescommon/css/font-awesome.css?appVer=${appVer}"> 
        <link rel="stylesheet" href="<%=context%>/resources/${appMode}/tilescommon/css/dataTables.bootstrap.min.css?appVer=${appVer}">
        <link rel="stylesheet" href="<%=context%>/resources/${appMode}/tilescommon/css/kendo.common-material.min.css?appVer=${appVer}" />
        <link rel="stylesheet" href="<%=context%>/resources/${appMode}/tilescommon/css/kendo.material.min.css?appVer=${appVer}" />
        <link rel="stylesheet" href="<%=context%>/resources/${appMode}/tilescommon/css/kendo.material.mobile.min.css?appVer=${appVer}" /> 
        <link rel="stylesheet" href="<%=context%>/resources/${appMode}/tilescommon/css/TableResponsive.css?appVer=${appVer}">       
        <link rel="stylesheet" href="<%=context%>/resources/${appMode}/tilescommon/css/bootstrap-datepicker.css?appVer=${appVer}">
        <link rel="stylesheet" href="<%=context%>/resources/${appMode}/tilescommon/css/bootstrap-timepicker.css?appVer=${appVer}">
        <link rel="stylesheet" href="<%=context%>/resources/${appMode}/tilescommon/css/bootstrap-datetimepicker.css?appVer=${appVer}">
        <link rel="stylesheet" href="<%=context%>/resources/${appMode}/tilescommon/css/common.css?appVer=${appVer}">
        <link rel="stylesheet" href="<%=context%>/resources/${appMode}/tilescommon/css/mobile.css?appVer=${appVer}">       
    	<link rel="stylesheet" href="<%=context%>/resources/${appMode}/tilescommon/css/jquery.paginate.css?appVer=${appVer}" />
    	<link rel="stylesheet" href="<%=context%>/resources/${appMode}/tilescommon/css/animate.min.css?appVer=${appVer}" />
    	<link rel="stylesheet" href="<%=context%>/resources/${appMode}/tilescommon/css/sweetalert2.min.css?appVer=${appVer}"> 
        <link rel="stylesheet" href="<%=context%>/resources/${appMode}/tilescommon/css/styleless.css?appVer=${appVer}" />
        <!--css included  --> 
		
        <!--js included  -->
        <script src="<%=context%>/resources/${appMode}/tilescommon/js/jquery-3.2.1.min.js?appVer=${appVer}"></script>        	
		<script src="<%=context%>/resources/${appMode}/tilescommon/js/sweetalert2.min.js?appVer=${appVer}"></script>
        <script src="<%=context%>/resources/${appMode}/tilescommon/js/bootstrap.min.js?appVer=${appVer}"></script>
        <script src="<%=context%>/resources/${appMode}/tilescommon/js/jquery.dataTables.min.js?appVer=${appVer}"></script>
        <script src="<%=context%>/resources/${appMode}/tilescommon/js/dataTables.bootstrap.min.js?appVer=${appVer}"></script>        
        <script src="<%=context%>/resources/${appMode}/tilescommon/js/bootstrap-datepicker.js?appVer=${appVer}"></script>
        <script src="<%=context%>/resources/${appMode}/tilescommon/js/bootstrap-timepicker.js?appVer=${appVer}"></script>
        <script src="<%=context%>/resources/${appMode}/tilescommon/js/tiles.js?appVer=${appVer}"></script>
        <%-- <script src="<%=context%>/resources/${appMode}/tilescommon/js/color.js?appVer=${appVer}"></script> --%>
       <%--  <script src="<%=context%>/resources/${appMode}/tilescommon/js/kendo.all.min.js?appVer=${appVer}"></script>  --%>           
		 <script src="<%=context%>/resources/${appMode}/tilescommon/js/bootpag.js?appVer=${appVer}"></script>	
		<script src="<%=context%>/resources/${appMode}/tilescommon/js/jquery.bootpag.min.js?appVer=${appVer}"></script>
		<script src="<%=context%>/resources/${appMode}/tilescommon/js/core.js?appVer=${appVer}"></script>
		<script src="<%=context%>/resources/${appMode}/commons/js/commonValidation.js?appVer=${appVer}"></script>
		<script src="<%=context%>/resources/${appMode}/commons/js/springform.js?appVer=${appVer}"></script>
		<script src="<%=context%>/resources/${appMode}/commons/js/checkbox.js?appVer=${appVer}"></script>
		<script src="<%=context%>/resources/${appMode}/commons/js/dateOperations.js?appVer=${appVer}"></script>
            <!--js included  -->
            
            <script>
                $(document).ready(function() {
                	if ($(window).width() > 768) { 
                        $(".dropdown").hover(            
                                function() {
                                    $('.dropdown-menu', this).stop( true, true ).fadeIn("fast");
                                    $(this).toggleClass('open');
                                    $('b', this).toggleClass("caret caret-up");                
                                },
                                function() {
                                    $('.dropdown-menu', this).stop( true, true ).fadeOut("fast");
                                    $(this).toggleClass('open');
                                    $('b', this).toggleClass("caret caret-up");                
                                });
                        }
                	
                	$(".factoryActionshow").click(function(){
                	    $(".zoom").show();
                	});
                
                   /*  $("#tabstrip").kendoTabStrip();
                    $("#tabstrip2").kendoTabStrip();
                    $("#tabstrip3").kendoTabStrip();
                    $("#tabstrip4").kendoTabStrip();
                    $("#tabstrip5").kendoTabStrip();
                    $("#tabstrip6").kendoTabStrip(); */
                    $('a.backTotils').click(function(){
                		parent.history.back();
                		return false;
                	});
                    
                });

                function valueChanged() {
                    if ($('.Materialvender').is(":checked"))
                        $(".Subvender").show();
                    else
                        $(".Subvender").hide();
                }
            </script>
            
            <style>
                .k-animation-container,
                .k-animation-container *,
                .k-animation-container:after,
                .k-block .k-header,
                .k-dialog .k-window-content,
                .k-list-container,
                .k-widget,
                .k-widget *,
                .k-widget:before {
                    -webkit-box-sizing: unset;
                    box-sizing: border-box !important;
                }
                
            </style>
    </head>


<body>
		<tiles:insertAttribute name="header" />			
	<section class="homeSection">
		<tiles:insertAttribute name="body" />
	</section>
		<tiles:insertAttribute name="footer" />
</body>
</html>