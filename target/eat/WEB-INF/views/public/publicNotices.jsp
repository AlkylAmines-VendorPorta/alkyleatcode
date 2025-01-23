<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>


 <div class="container-fluid slidepadding">
        <section class="center-section">
        <h4>Public Notices</h4>
        <div class="col-sm-12 nopaddingleft">
         <table class="table table-striped tableResponsive table-bordered" width="100%" id="publicNoticetable">
         	<thead>
         		<tr>
         			<th>Sr.No</th>
         			<th>Tender Number</th>
         			<th>Title Of Notice</th>
         			<th>Published On</th>
         			<th>Download</th>
         		</tr>
         	</thead>
             <tbody>
                <!--  <tr>
                 	<td>1</td>
                 	<td>e-Auction 2013-2017</td>
                 	<td>15-12-2017</td>
                 	<td><button class="btn btn-primary">Download</button></td>
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
<script src="<%=request.getContextPath()%>/resources/${appMode}/transaction/js/headerPublicNotice.js?appVer=${appVer}"></script>


