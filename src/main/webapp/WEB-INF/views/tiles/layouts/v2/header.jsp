<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@taglib uri = "http://www.springframework.org/tags/form" prefix = "sf"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<sec:csrfMetaTags />

<style>.logindrop{
    right: 0; left: initial;}</style>
		<!-- div id="loading-wrapper">
 		  <div id="loading-text">LOADING</div>
 		  <div id="loading-content"></div>
		</div> -->
<!-- 		<div id="content-wrapper">
	<div class="ring">
		Loading
		<span></span>
	</div>
</div> -->
<div id="loading-wrapper">
<div id="loading-content">
	<div id="loader">
    <div id="loader_1" class="loader"></div>
    <div id="loader_2" class="loader"></div>
    <div id="loader_3" class="loader"></div>
    <div id="loader_4" class="loader"></div>
    <div id="loader_5" class="loader"></div>
    <div id="loader_6" class="loader"></div>
    <div id="loader_7" class="loader"></div>
    <div id="loader_8" class="loader"></div>
    </div>
    </div>
</div>
		<div class="alert-backdrop"></div>
	
        <div id="sidebar-wrapper">
            <div class="sidebar-nav">
                <div class="nav-side-menu">
                    <div class="brand">E-Tender</div>

                    <div class="menu-list">
                        <ul id="menu-content" class="menu-content">                            
                            <li>
                                <a href="#">
                                    <i class="fa fa-dashboard fa-lg"></i> Dashboard
                                </a>
                            </li>
                             <li>
                                <a href="dashboard1">
                                    <i class="fa fa-dashboard fa-lg"></i> Dashboard 1
                                </a>
                            </li>
                           <!--  <li>
                                <a href="tenderview">
                                    <i class="fa fa-dashboard fa-lg"></i> Tender view 
                                </a>
                            </li>
                            <li>
                                <a href="dashboardView">
                                    <i class="fa fa-dashboard fa-lg"></i> Dashboard 2
                                </a>
                            </li>
                           <li>
                                <a href="dashboard3">
                                    <i class="fa fa-dashboard fa-lg"></i> Dashboard 3
                                </a>
                            </li>
                            <li>
                                <a href="dashboard4">
                                    <i class="fa fa-dashboard fa-lg"></i> Dashboard 4
                                </a>
                            </li> -->
							<li>
	                            <a href="advanceSearch">
	                                <i class="fa fa-search fa-lg"></i>Advance Search
	                            </a>
                            </li>
                            <li>
                                <a href="partnerRegistration">
                                    <i class="fa fa-user fa-lg"></i> Profile
                                </a>
                            </li>
                            <li>
                                <a href="partnerRegistration">
                                    <i class="fa fa-calendar-check-o"></i> Session Audit
                                </a>
                            </li>
                            <li data-toggle="collapse" data-target="#Master" class="collapsed">
                                <a href="#"><i class="fa fa-sticky-note"></i> Master <span class="arrow"></span></a>                           
		                        <ul class="sub-menu collapse" id="Master">
		                            <li><a href="getUserView">Add User</a></li>
		               				<li><a href="material">Material</a></li>               
		                			<li><a href="DocumentType">Document Type</a></li>                
		               		    	<li><a href="TenderType">Tender Type</a></li>               
		               				<li><a href="TenderBudgetType">Tender Budget Type</a></li>                
		                			<li><a href="BidType">Bid Type</a></li>                
		                			<li><a href="uom">UOM</a></li>                
		                			<li><a href="PaymentType">Payment Type</a></li>               
		                			<li><a href="ContractorType">Contractor Type</a></li>               
		                			<li><a href="materialGroup">Material Group</a></li>                
		                			<li><a href="MaterialSubGroup">Material Sub-Group</a></li>
		                			<li><a href="MaterialSpecification">Material Specification</a></li>                
		                			<li><a href="departmentView">Department</a></li>                
		                			<li><a href="MaterialVersion">Material Version</a></li>                 
		                			<li><a href="GtpParameter">GTP Parameters</a></li>
		                			<li><a href="GtpParameterType">GTP Parameters Type</a></li>                
		                			<li><a href="PublicNotice">Public Notices</a></li>                
		                			<li><a href="IsStd">IS-STD</a></li>                 
		               				<li><a href="designation">Designation</a></li>                
		                			<li><a href="CompanyType">Company Type</a></li>
		                			<li><a href="RegistrationType">Registration Type</a></li>
		                			<li><a href="tax">Tax</a></li>                
		                			<li><a href="TaxCategory">Tax Category</a></li>
		                			<li><a href="HolderType">Holder Type</a></li>                 
		                		    <li><a href="role">Role</a></li>
		                		    <li><a href="MaterialType">Material Type</a></li>
		                		    <li><a href="VendorType">Vendor Type</a></li>
		                		    <li><a href="ExemptedCategories">Exempted Categories</a></li>
		                		    <li><a href="FinancialDocuments">Financial Documents</a></li>
		                		    <li><a href="BusinessPartnerGroup">Business Partner Group</a></li>		                		    
		                       </ul>
                            </li>
                            <li data-toggle="collapse" data-target="#Tenders" class="collapsed">
                                <a href="#"><i class="fa fa-file-pdf-o"></i> Tenders <span class="arrow"></span></a>                           
		                        <ul class="sub-menu collapse" id="Tenders">
		                            <li><a href="tenderPreparation">Tender Preparation</a></li>
                    				<li><a href="tenderPublishing">Tender Publishing</a></li>                     
                    				<li><a href="tenderScheduling">Tender Scheduling</a></li>                     
                    				<li><a href="tenderPurchase">Tender Purchase</a></li>                    
                    				<li><a href="tenderSubmission">Tender Submission</a></li>                   
                    				<li><a href="tenderOpening">Tender Opening</a></li>                    
                    				<li><a href="finalScrutiny">Final Scrutiny</a></li>                   
                    				<li><a href="tenderTechnicalScrutiny">Preliminary Scrutiny</a></li>
		                        </ul>
		                     </li>
		                     <li data-toggle="collapse" data-target="#Auction" class="collapsed">
                                <a href="#"><i class="fa fa-gavel"></i> Auction <span class="arrow"></span></a>                           
		                        <ul class="sub-menu collapse" id="Auction">
		                            <li><a href="tenderPreparation">Auction Preparation</a></li>
                    				<li><a href="tenderPublishing">Auction Publishing</a></li>                     
                    				<li><a href="tenderScheduling">Auction Scheduling</a></li>                     
                    				<li><a href="tenderPurchase">Auction Purchase</a></li>                    
                    				<li><a href="tenderSubmission">Auction Submission</a></li>                   
                    				<li><a href="tenderOpening">Auction Opening</a></li>                    
                    				<li><a href="tenderCommercialScrutiny">Auction Commercial Scrutiny</a></li>                   
                    				<li><a href="tenderTechnicalScrutiny">Auction Technical Scrutiny</a></li>
		                        </ul>
		                     </li>                           
                            <li>
                                <a href="bidsdetails">
                                    <i class="glyphicon glyphicon-list-alt fa-lg"></i> Bid
                                </a>
                            </li>
                            <!-- <li>
                                <a href="#">
                                    <i class="fa fa-money fa-lg"></i> Payment
                                </a>
                            </li>
                            <li>
                                <a href="#">
                                    <i class="fa fa-file-text fa-lg"></i> Reports
                                </a>
                            </li> -->
                           <!--  <li>
                                <a href="#">
                                    <i class="fa fa-shopping-cart fa-lg"></i> Purchase Proposal
                                </a>
                            </li> -->
                            <li>
                                <a href="mail">
                                    <i class="fa fa-envelope fa-lg"></i> Mail
                                </a>
                            </li>
                            <li>
                                <a href="roleDetails">
                                    <i class="fa fa-info-circle fa-lg"></i> Role Details
                                </a>
                            </li>
                        </ul>
                    </div>
                </div>
                <div class="clearfix"></div>

            </div>
        </div>

        <!-- full-container start -->

        <!-- Header start -->
<c:choose>
  <c:when test="${sessionScope.userThemeColor != null}">
   <div class="${sessionScope.userThemeColor} header text-center">
  </c:when>
  <c:otherwise>
  <div class="header text-center">
  </c:otherwise>
</c:choose>
        
            <div class="col-xs-4">
            <div class="pull-left">
               <%--  <img src="<%=request.getContextPath()%>/resources/tilescommon/images/E_logo.png" class="logo"> --%>
                <button type="button" title="Menu" class="menu-toggle btn btn-info btnsm">
                    <i class="fa fa-bars"></i>
                </button>
                <a href="" title="Go_Back_to_Tiles" class="backTotils btn btn-info btnsm"><i
						class="fa fa-arrow-left"></i></a>
                <button title="Go_Back_to_List" class="btn btn-info btnsm backTolist"><i class="fa fa-arrow-left"></i></button>
            </div></div> <div class="col-xs-4"><a href="dashboard">
           <%--  <img src="<%=request.getContextPath()%>/resources/tilescommon/images/eauction2.png" class="dash_logo"></a> --%>
           <c:choose>
			  <c:when test="${sessionScope.userLogo != null}">
			  <img src='data:image/png;base64,${sessionScope.userLogo}' class="dash_logo" id="header_logoId"></a>
			  </c:when>
			  <c:otherwise>
			  <img src="<%=request.getContextPath()%>/resources/tilescommon/images/eauction2.png" class="dash_logo"></a>
			  </c:otherwise>
			</c:choose>
            </div>
	<div class="col-xs-4">
            <div class="pull-right advancehide">
				<div id = "timer"></div>
                <div class="dropdown welcome">                
                    Welcome |<a class="dropdown-toggle" type="button" id="dropdownMenu1" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true"><span class="glyphicon glyphicon-user userdet"></span><c:out value="${sessionScope.UserSession.email }"/><span class="caret"></span></a>
					<ul class="dropdown-menu logindrop" aria-labelledby="dropdownMenu1">
                        <li><a href="partnerRegistration">Profile</a></li>
                        <li><a href="changePassword">Change Password</a></li>
                        <li><a href="logout">Logout</a></li>
                    </ul>
                </div></div>
            </div>

        </div>
 <!-- Header end -->
 <script>
 $(document).ready(function(){
	 var $myGroup = $('#menu-content');
	 $myGroup.on('show.bs.collapse','.collapse', function() {
	     $myGroup.find('.collapse.in').collapse('hide');
	 }); 
 });
 </script>
 
 <script language="JavaScript">
 var currDateSec  = <%= new java.util.Date().getTime() %>
 clock(currDateSec);
function clock(msec){
	var time = new Date(msec);
	var hr = time.getHours()
	var min = time.getMinutes()
	var sec = time.getSeconds()
	var date = time.getDate() + '/' +(time.getMonth()+1) +'/'+time.getFullYear();
	
	var ampm = " PM "
	if (hr < 12){
		ampm = " AM "
	}
	if (hr > 12){
		hr -= 12
	}
	if (hr < 10){
		hr = " " + hr
	}
	if (min < 10){
		min = "0" + min
	}
	if (sec < 10){
		sec = "0" + sec
	}
	$("#timer").text(date+' '+hr + ":" + min + ":" + sec + ampm);
	setTimeout("clock("+(msec+1000)+")", 1000)
	}
</script>
 
<!-- Modal -->
  <div class="modal fade commenSearchModal" id="" role="dialog">
    <div class="modal-dialog">
    
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title">Search</h4>
        </div>
        <div class="modal-body"  style="padding-top: 0px;">
        										<sf:form id="srchItemForm" modelAttribute="material" method="POST">
         										<div class="form-group">
         										<input type="hidden" class="tahdrDetailTypeCode" name="typeCode" >
	                                            <input type="hidden" id="orgId" name="partnerOrgId">
	                                            <input type="hidden" class="bPartnerId" name="bPartnerId">
	                                                <div class="col-sm-5">
	                                                    <div class="styled-input">
                                                        <input type="text" id="srchItemCode" name="code">
                                                        <label>Item Code<span class="red">*</span></label>
                                                        <span></span>
	                                                    </div>
	                                                    </div>
	                                                    <div class="col-sm-5">
	                                                    <div class="styled-input">
                                                        <input type="text" id="srchItemName" name="name">
                                                        <label>Item Name<span class="red">*</span></label>
                                                        <span></span>
	                                                    </div>
	                                                    </div>
	                                                    <div class="col-sm-2">
	                                                    <button style="margin-top:20px;" class="btn btn-default searchItemList" id="searchItemList"><i class="fa fa-search fa-lg"></i></button></div>
	                                                    </div>
         										 <div class="form-group">
	                                                <div class="col-sm-6">
	                                                    <div class="styled-input">
	                                                        <select id="srchMaterialGroup" name="materialGroupId" class="materialGroupList" onchange="populateMatSubGroupChange(this)" >
	                                                        </select>
	                                                        <label>Select Material Group<span class="red">*</span></label>
	                                                        <span></span>
	                                                    </div>
	                                                </div>
	                                                <div class="col-sm-6">
	                                                    <div class="styled-input">
	                                                        <select id="srchMaterialSubGroup" name="materialSubGroupId" class="materialSubGroupList" >
	                                                        </select>
	                                                        <label>Select Sub Group<span class="red">*</span></label>
	                                                        <span></span>
	                                                    </div>
	                                                </div>
	                                                
	                                            </div>
	                                           
	                                            </sf:form>
	                                            
	                                            <div class="form-group">
	                                            <div class="col-sm-12">
	                                                    <table class="table table-bordered commenSearchTable itemTable">
                                                        <thead>
                                                            <tr>
                                                                <th data-field="state" data-checkbox="true"><input type="checkbox" class="selectall"></th>
                                                                <th  data-field="id">HSN/SAC Code</th>
                                                                <th>Item Code</th>
                                                                <th>Item Name</th>
                                                            </tr>
                                                        </thead>
                                                        <tbody>
                                                                                         
                                                        </tbody>
                                                    </table>
	                                                </div>
	                                                </div>
	                                                
        </div>
        <div class="modal-footer">
          <button type="button" id="AdditemButton" class="btn btn-default save" data-dismiss="modal" data-callback="">Add Items</button>
          <button type="button" id="closeItemBtn" class="btn btn-default cancle" data-dismiss="modal">Close</button>
        </div>
      </div>
      
    </div>
  </div>
  <!-- Modal -->
  <div class="modal fade commenVendorModal" id="" role="dialog">
    <div class="modal-dialog">
    
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title">Search</h4>
        </div>
        <div class="modal-body"  style="padding-top: 0px;">
        										<sf:form id="srchItemForm" modelAttribute="material" method="POST">
         										<div class="form-group">
         										<input type="hidden" class="tahdrDetailTypeCode" name="typeCode" >
	                                            <input type="hidden" id="orgId" name="partnerOrgId">
	                                                <div class="col-sm-5">
	                                                    <div class="styled-input">
                                                        <input type="text" id="srchItemCode" name="code">
                                                        <label>Item Code<span class="red">*</span></label>
                                                        <span></span>
	                                                    </div>
	                                                    </div>
	                                                    <div class="col-sm-5">
	                                                    <div class="styled-input">
                                                        <input type="text" id="srchItemName" name="name">
                                                        <label>Item Name<span class="red">*</span></label>
                                                        <span></span>
	                                                    </div>
	                                                    </div>
	                                                    <div class="col-sm-2">
	                                                    <button style="margin-top:20px;" class="btn btn-default searchItemList" id="searchItemList"><i class="fa fa-search fa-lg"></i></button></div>
	                                                    </div>
         										 <div class="form-group">
	                                                <div class="col-sm-6">
	                                                    <div class="styled-input">
	                                                        <select id="srchMaterialGroup" name="materialGroupId" class="materialGroupList" onchange="populateMatSubGroupChange(this)" >
	                                                        </select>
	                                                        <label>Select Material Group<span class="red">*</span></label>
	                                                        <span></span>
	                                                    </div>
	                                                </div>
	                                                <div class="col-sm-6">
	                                                    <div class="styled-input">
	                                                        <select id="srchMaterialSubGroup" name="materialSubGroupId" class="materialSubGroupList" >
	                                                        </select>
	                                                        <label>Select Sub Group<span class="red">*</span></label>
	                                                        <span></span>
	                                                    </div>
	                                                </div>
	                                                
	                                            </div>
	                                           
	                                            </sf:form>
	                                            
	                                            <div class="form-group">
	                                            <div class="col-sm-12">
	                                                    <table class="table table-bordered commenSearchTable itemTable">
                                                        <thead>
                                                            <tr>
                                                                <th data-field="state" data-checkbox="true"><input type="checkbox" class="selectall"></th>
                                                                <th  data-field="id">HSN/SAC Code</th>
                                                                <th>Item Code</th>
                                                                <th>Item Name</th>
                                                            </tr>
                                                        </thead>
                                                        <tbody>
                                                                                         
                                                        </tbody>
                                                    </table>
	                                                </div>
	                                                </div>
	                                                
        </div>
        <div class="modal-footer">
          <button type="button" id="AdditemButton" class="btn btn-default save" data-dismiss="modal" data-callback="">Add Items</button>
          <button type="button" id="closeItemBtn" class="btn btn-default cancle" data-dismiss="modal">Close</button>
        </div>
      </div>
    </div>
  </div>