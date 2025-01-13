<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

<style>
 thead,.dataTables_length,.dataTables_filter {
  display:none !important;
}
.BlackListedVendor tbody tr td {font-size:15px;}
    </style>
    <div class="container-fluid slidepadding">
        <section class="center-section">
        <h4>Black Listed Vendors</h4>
        <div class="col-sm-6">
         <table class="BlackListedVendor listtable table table-striped table-bordered" width="100%">
         	<thead>
         		<tr>
         			<th></th>
         		</tr>
         	</thead>
             <tbody>
                 <tr>
                 	<td><span class="glyphicon glyphicon-remove redspan"></span>Black Listed Vendor- 1</td>
                  </tr>
                  <tr>	
        			<td><span class="glyphicon glyphicon-remove redspan"></span>Black Listed Vendor- 2</td>
        	      </tr>
        	      <tr>
        			<td><span class="glyphicon glyphicon-remove redspan"></span>Black Listed Vendor- 3</td>
        		   </tr>
        		   <tr>	
        			<td><span class="glyphicon glyphicon-remove redspan"></span>Black Listed Vendor- 4</td>
        		  </tr> 
        		  <tr>	
        			<td><span class="glyphicon glyphicon-remove redspan"></span>Black Listed Vendor- 5</td>
                 </tr>
             </tbody>
        </table>
        </div>
        <!-- <ul class="blacklist">
        	
        </ul>     -->        
        </section> 
        </div>                
<script src="resources/${appMode}/transaction/js/home.js?appVer=${appVer}"></script> 


