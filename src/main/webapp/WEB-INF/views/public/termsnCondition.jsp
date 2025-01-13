<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@taglib uri = "http://www.springframework.org/tags/form" prefix = "sf"%>

<style>
 thead,.dataTables_length,.dataTables_filter {
  display:none !important;
}
.dataTables_wrapper .dataTables_paginate .paginate_button {
    box-sizing: border-box;
    display: inline-block;
    min-width: 1.5em;
    padding: 0px;
    margin-left: 0px;
    text-align: center;
    text-decoration: none !important;
    cursor: pointer;
    *: ;
    cursor: hand;
    color: #333 !important;
    border: none;
    border-radius: 2px;
}
.BlackListedVendor tbody tr td {font-size:15px;}
    </style>
    <div class="container-fluid slidepadding">
        <section class="center-section">
        <h4>Terms & Conditions</h4>
        <div class="col-sm-6">
        <table class="Download listtable table table-striped table-bordered" width="100%">
         	<thead>
         		<tr>
         			<th></th>
         		</tr>
         	</thead>
             <tbody>
                 <tr>
                 	<td><a><span class="glyphicon glyphicon-download-alt redspan"></span></a>CONDITIONS OF TENDER AND SUPPLY SECTION II 33KV </td>
                  </tr>
                  <tr>	
        			<td><a><span class="glyphicon glyphicon-download-alt redspan"></span></a>SECTION I INSTRUCTIONS TO THE TENDERER 33KV </td>
        	      </tr>
        	      <tr>
        			<td><a><span class="glyphicon glyphicon-download-alt redspan"></span></a>SECTION I RE </td>
        		   </tr>
        		   <tr>	
        			<td><a><span class="glyphicon glyphicon-download-alt redspan"></span></a>SECTION II RE </td>
        		  </tr>
             </tbody>
        </table>
        </div>                 
        </section> 
        </div>        
       <%--   <script src="<%=request.getContextPath()%>/resource/js/jquery-3.2.1.min.js?appVer=${appVer}"></script> --%>
<script src="resources/${appMode}/transaction/js/home.js?appVer=${appVer}"></script> <!-- 
<script src="resources/${appMode}/commons/js/jquery.dataTables.min.js?appVer=${appVer}"></script>
<script src="resources/${appMode}/commons/js/dataTables.bootstrap.min.js?appVer=${appVer}"></script> -->
<script>
$(document).ready(function() {	
$('.Download').DataTable({
});

});
</script>