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
.BlackListedVendor tbody tr td {font-size:18px;}
    </style>
    <div class="container-fluid slidepadding">
        <section class="center-section">
        <h4>Download</h4>
        <div class="col-sm-6">
        <table class="Download listtable table table-striped table-bordered" width="100%">
         	<thead>
         		<tr>
         			<th></th>
         		</tr>
         	</thead>
             <tbody>
                 <tr>
                 	<td><a><span class="glyphicon glyphicon-download-alt redspan"></span></a>Adobe Reader</td>
                  </tr>
                  <tr>	
        			<td><a><span class="glyphicon glyphicon-download-alt redspan"></span></a>Secure-Sign Software </td>
        	      </tr>
        	      <tr>
        			<td><a><span class="glyphicon glyphicon-download-alt redspan"></span></a>MS Office 2007 to MS Office 2003 file Converter </td>
        		   </tr>
        		   <tr>	
        			<td><a><span class="glyphicon glyphicon-download-alt redspan"></span></a>Primo PDFSetup.exe </td>
        		  </tr> 
        		  <tr>	
        			<td><a><span class="glyphicon glyphicon-download-alt redspan"></span></a>Adobe Zip Security for 7 and 8 reader.zip</td>
                 </tr>
                 <tr>	
        			<td><a><span class="glyphicon glyphicon-download-alt redspan"></span></a>Public Notice-Vendor Registration Process</td>
                 </tr>
                 <tr>	
        			<td><a><span class="glyphicon glyphicon-download-alt redspan"></span></a>Request Format for Change in Digital Signature Holder Details</td>
                 </tr>
                 <tr>	
        			<td><a><span class="glyphicon glyphicon-download-alt redspan"></span></a>Receipt Voucher for Payment of Registration Fees.</td>
                 </tr> --%>
                 <tr>	
        			<td><a href="<%=request.getContextPath() %>/download/10"><span class="glyphicon glyphicon-download-alt redspan"></span></a>List of Item Codes of Materials Purchased by M.M.Cell</td>
                 </tr>
                 <tr>	
        			<td><a href="<%=request.getContextPath() %>/download/11"><span class="glyphicon glyphicon-download-alt redspan"></span></a>Secure-Sign Software </td>
        	      </tr>
                 <tr>	
        			<td><a href="<%=request.getContextPath() %>/download/12"><span class="glyphicon glyphicon-download-alt redspan"></span></a>Vendor Registration Process </td>
                 </tr>
             </tbody>
        </table>
        </div>                 
        </section> 
        </div>        
<script src="resources/${appMode}/transaction/js/home.js?appVer=${appVer}"></script> 
<script src="resources/${appMode}/commons/js/jquery.dataTables.min.js?appVer=${appVer}"></script>
<script src="resources/${appMode}/commons/js/dataTables.bootstrap.min.js?appVer=${appVer}"></script>
<script>
$(document).ready(function() {	
$('.Download').DataTable({
});

});
</script>