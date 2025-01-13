<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

<!-- <style>
 thead,.dataTables_length,.dataTables_filter {
  display:none !important;
}
.BlackListedVendor tbody tr td {font-size:15px;}
    </style> -->
        <div class="container-fluid slidepadding">
        <c:if test="${customerType.equalsIgnoreCase('Y')}">
      		<input type="hidden" id="customersType" value="Y" >
      </c:if>
      <c:if test="${customerType.equalsIgnoreCase('N')}">
      		<input type="hidden" id="customersType" value="N" >
      </c:if>
        <section class="center-section">
        <h4 id="CustomerHeader"></h4>
       <!--  <div class="col-sm-6"> -->
       <div class="col-sm-6 nopaddingleft">
         <table class="table table-striped table-bordered" width="100%" id="RegisteredCustList">
         	<thead>
         		<tr>
         			<th>Name Of Customers</th>
         			<th>Email Address</th>
         			<!-- <th>Company Name</th> -->
         		</tr>
         	</thead>
             <tbody>
                 <!-- <tr>
                 	<td><span class="glyphicon glyphicon-ok greenspan"></span>Registered Vendor- 1</td>
                  </tr>
                  <tr>	
        			<td><span class="glyphicon glyphicon-ok greenspan"></span>Registered Vendor- 2</td>
        	      </tr>
        	      <tr>
        			<td><span class="glyphicon glyphicon-ok greenspan"></span>Registered Vendor- 3</td>
        		   </tr>
        		   <tr>	
        			<td><span class="glyphicon glyphicon-ok greenspan"></span>Registered Vendor- 4</td>
        		  </tr> 
        		  <tr>	
        			<td><span class="glyphicon glyphicon-ok greenspan"></span>Registered Vendor- 5</td>
                 </tr> -->
             </tbody>
        </table>
        </div>
        <!-- <ul class="blacklist">
        	
        </ul>     -->        
        </section> 
        </div>              
<script src="<%=request.getContextPath()%>/resources/${appMode}/tilescommon/js/jquery-3.2.1.min.js?appVer=${appVer}"></script>
<script src="<%=request.getContextPath()%>/resources/${appMode}/transaction/js/home.js?appVer=${appVer}"></script> 
<script src="<%=request.getContextPath()%>/resources/${appMode}/transaction/js/headerCustomerRegistration.js?appVer=${appVer}"></script>



