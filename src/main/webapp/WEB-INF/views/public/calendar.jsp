<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/${appMode}/tilescommon/css/bootstrap-datepicker.css?appVer=${appVer}">
<style>
.table-condensed thead, .table-condensed tbody {border-bottom: none;}
.table-condensed tbody tr td, .table-condensed thead tr td{padding:5px !important;}
.table-condensed tbody tr{border-bottom:0px;}
.form-group { margin-bottom: 10px;}
.btn-primary span{margin-right:5px;}
</style>
 <div class="container-fluid slidepadding">
        <section class="center-section">
        <h4>Calendar</h4>
        <div class="col-sm-12 nopaddingleft">
          <div class="form-group">
        <div class="col-sm-3 nopaddingleft">
             <div class="form-group">
                 <label>From<span class="red">*</span></label>
                 <div class="input-group date" data-provide="datepicker">
                     <input type="text" class="form-control" name="">
                     <div class="input-group-addon">
                         <span class="glyphicon glyphicon-th"></span>
                     </div>
                 </div>
             </div>
         </div> 
         <div class="col-sm-3">
             <div class="form-group">
                 <label>To<span class="red">*</span></label>
                 <div class="input-group date" data-provide="datepicker">
                     <input type="text" class="form-control" name="">
                     <div class="input-group-addon">
                         <span class="glyphicon glyphicon-th"></span>
                     </div>
                 </div>
             </div>
         </div> 
         <div class="col-sm-1"><button class="btn btn-primary" style="margin-top: 25px;"><span class="glyphicon glyphicon-search"></span>Search</button></div>
         </div>
         </div>
         <div class="clearfix"></div>
         <div class="col-sm-12 nopaddingleft">
         <table class="BlackListedVendor table table-striped table-bordered" width="100%">
         	<thead>
         		<tr>
         			<th>Tender Code</th>
         			<th>Bid Type</th>
         			<th>Submission Date</th>
         			<th>Opening Date</th>
         		</tr>
         	</thead>
             <tbody>
                 <tr>
                 	<td>TestBilldesk </td>
                 	<td>Open</td>
                 	<td> 19 Dec 2017  </td>
                 	<td>31 Jan 2018 </td>
                  </tr>
                  
             </tbody>
        </table>
        </div>
        <!-- <ul class="blacklist">
        	
        </ul>     -->        
        </section> 
        </div>
        <script src="<%=request.getContextPath()%>/resources/${appMode}/commons/js/jquery-3.2.1.min.js?appVer=${appVer}"></script>
<script src="resources/${appMode}/transaction/js/home.js?appVer=${appVer}"></script>
<script	src="<%=request.getContextPath()%>/resources/${appMode}/tilescommon/js/bootstrap-datepicker.js?appVer=${appVer}"></script>
