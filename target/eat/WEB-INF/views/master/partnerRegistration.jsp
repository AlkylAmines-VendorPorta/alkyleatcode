<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@taglib uri = "http://www.springframework.org/tags/form" prefix = "sf"%>
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/${appMode}/tilescommon/css/fioristyle.css?appVer=${appVer}"/>
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/${appMode}/tilescommon/css//borderTab.css?appVer=${appVer}" />
<link rel="stylesheet" href="http://harshniketseta.github.io/popupMultiSelect/dist/stylesheets/multiselect.min.css?appVer=${appVer}"/> 
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/${appMode}/tilescommon/css/examples.css?appVer=${appVer}"/> 
 <link rel="stylesheet" href="<%=request.getContextPath()%>/resources/${appMode}/tilescommon/css/fontawesome-stars.css?appVer=${appVer}"/>
<script src="<%=request.getContextPath()%>/resources/${appMode}/tilescommon/js/jquery.barrating.js?appVer=${appVer}"></script> 
<script src="<%=request.getContextPath()%>/resources/${appMode}/tilescommon/js/number-starRating.js?appVer=${appVer}"></script> 
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/${appMode}/tilescommon/css/mobile.css?appVer=${appVer}"/>
<script src="<%=request.getContextPath()%>/resources/${appMode}/tilescommon/js/kendo.all.min.js?appVer=${appVer}"></script> 

  
<!-- number-starRating -->
<style type = "text/css">

</style>
<!-- number-starRating -->

<style>
.form-group {
    margin-bottom: 5px;
}
.nopadd{padding:0px !important;}
.input_margin{    margin-left: 67px !important;
    margin-top: 5px;}
    </style>
<div class="full-container"> 
  <!-- left-side start-->
  <div class="clearfix"></div>
  <div id="mobile_first_container" class="left-side col-md-3 no-marg">
  <div class="detailsheader">
        	<div class="col-sm-3 text-center brdrgt"><label><span class="activeTab">Company Details</span> (<span class="reportCount">0</span>)</label></div>
        </div>
    <div class="input-group">
                <div class="input-group-btn search-panel">
                    <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">
                    	<span id="search_concept">Filter by</span> <span class="caret"></span>
                    </button>
                    <ul class="dropdown-menu" role="menu">
                      <li><a href="#contains">Code</a></li>
                      <li class="divider"></li>
                      <li><a href="#all">Name</a></li>
                    </ul>
                </div>
                <input type="hidden" name="search_param" value="all" id="search_param">         
                <input type="text" class="form-control" name="x" id="searchlitralid" placeholder="Search term...">
                <span class="input-group-btn">
                    <button class="btn btn-default" type="button"><span class="glyphicon glyphicon-search"></span></button>
                    <button class="btn btn-default" title="Add" type="button"><span class="fa fa-plus-square addnewlist"></span></button>
                </span>
            </div>
    <ul id="example" class="nav nav-tabs tabs-left example leftPaneData">
                    
                   <li class="active">
                        <a href="#Master" data-toggle="tab">
                            <div class="col-md-12">
                                <label class="col-xs-6"> NovelERP Solutions</label>
                            	<label class="col-xs-6 ">12765456</label>
                            </div>	
                            <div class="col-md-12">
                           		<label class="col-xs-6">Manufacturer</label>
                            	<label class="col-xs-6 ">EOCPK88Pk</label>
                            </div>
                            
                        </a>
                    </li>
    </ul>
    
    <div class="clearfix" ></div>
  </div>
  <!-- left-side end--> 
  <input type="hidden" id="partnerData" value="${partnerData}"/> 
  <input type="hidden" id="roleData" value="${role.value}"/> 
  <!-- right-side start-->
  <div id="mobile_second_container" class="right-side col-md-9 no-marg">
   <div class="detailsheader toptabbrd">
        	<div class="col-sm-9 text-center"><label class="activeTab">Company Details</label></div>
        </div>
    <div class="clearfix"></div>
    <div class="tab-content" > 
      <!-- Master tab start-->
      
      <div class="tab-pane active in" id="tabPane"> 
      <div class="card">       
                                   <div id="example">

                                    <div class="demo-section k-content">

                                        <div id="tabstrip" class="Firsttab">
                                            <ul>
                                                <!-- tabs -->
                                                <!-- <li class="k-state-active">Account Type</li> -->
                                                 <li id="companyDetailsTabId" onclick="getPartnerDetails(event,this);" class="k-state-active normalli"><div class="tabcircle"><i class="fa fa-info-circle" aria-hidden="true"></i></div>Company Details</li><!-- return getPartnerInfo(); -->
                                                <!--  <li class="profileSettingClass" onclick="loadProfileSetting()"><a href="#home2" data-toggle="tab" title="welcome">
							                               <span class="round-tabs one">
							                                	<i class="fa fa-cogs" aria-hidden="true"></i>
							                                </span>
							                            </a>
							                       Profile Setting </li> -->  
							                    <li data-parentTab="" id="" openTab="" onclick="loadProfileSetting()" class="profileSettingClass hidePartnerTab normalli"><div class="tabcircle"><i class="fa fa-cogs"></i></div>Profile Setting</li> 
                                                <li data-parentTab="" id="compContactTabId" openTab="companyContactTab" onclick="getPartnerCompanyContact(event,this);" class="hidePartnerTab normalli"><div class="tabcircle"><i class="fa fa-phone-square"></i></div>Company Contacts</li>
                                                <!-- <li id="digitalSignTabId" onclick="getPartnerSignatory(event,this);">Digital Signatory</li> -->
                                               <!--  <li id="managementDetailId" onclick="getPartnerDirectoryDetails(event,this);">Management Details</li> -->
                                                <!-- <li>Experience Details</li> -->
                                               
                                               <!--  <li id="factoryDetailTabId" onclick="getPartnerFactoryDetails(event,this);" class="factoryActionshow" >Factory Details</li> -->
                                               <!--  <li onclick="return submitWithParam('getOrgPBG','bPartnerId','onPartnerOrgPBGTabLoad');">PBG Details</li> -->
                                               <!--  <li id="financialDetailsTabId" onclick="getPartnerFinancialDetails(event,this);">Financial Details</li> -->                                              
                                               <!--  <li id="manufacturerTabId" onclick="getPartnerItemManufacturer(event,this);">Trading Items</li> -->
                                                <li data-parentTab="companyDetailsTabId" id="bankDetailsTabId"onclick="getPartnerBankDetails(event,this);"  class="hidePartnerTab normalli"><div class="tabcircle"><i class="fa fa-university"></i></div>Bank Details</li>   
                                               <li id="vendorPaymentTabId" class="normalli" onclick="getPartnerPaymentDetails(event,this);"><div class="tabcircle"><i class="fa fa-money"></i></div>Payment Details</li>
                                                <li data-parentTab="companyDetailsTabId" class="hidePartnerTab confirmliclick" id="confirmationTabId" onclick="return getPartnerForSubmit(event,this);"><div class="tabcircle"  id="tabcircle"><i class="fa fa-check-circle"></i></div>Confirmation</li>                                             
                                            </ul>
                                        <!--fields of field group 2  -->
                                        <div >
									<div class="detailscont">
										
									</div>
									<div class="col-xs-12 text-right nopadding" style="margin-top: 10px;">
										 <button class="btn btn-default" id="partnerEditBtnId" style="display: none;" onclick="getLoaderForEdit(event);">
											<span class="glyphicon glyphicon-plus-sign" ></span>Edit Profile</button>
									</div>
									<div>
									<sf:form action="#"  method="POST" modelAttribute="partnerDto" id="companyDetails">
									
                                        <%-- <div class="form-group">
                                        <div class="col-sm-12"><h4 ><b>Company Details</b></h4>
                                        <div class="col-xs-6 text-right nopadding"
															style="margin-top: 10px;">
															<button class="btn btn-default" id="" onclick="return submitWithParam('','','');">
																<span class="glyphicon glyphicon-edit"></span>Edit
																All tabs
															</button>
														</div><hr></div>
                                        <input type="hidden" name="bPartnerId" id="bPartnerId" class="bPartnerId" value="${partner.bPartnerId}"/>
                                        </div> --%>
                                        <div class="form-group">
                                        <div class="col-sm-12">
                                        <h4 class="col-xs-6 nopadding"><b>Company Details</b></h4>
                                        <!-- <div class="col-xs-6 text-right nopadding" style="margin-top:10px;">
                                                    <button class="btn btn-default" id=""><span class="glyphicon glyphicon-pencil"></span>Edit All Tabs</button>
                                        <hr>
                                        </div> -->
										<input type="hidden" name="bPartnerId" id="bPartnerId" class="bPartnerId" value="${partner.bPartnerId}"/>
                                        <input type="hidden" name="isActive" value="Y" />
                                        <input type="hidden" id="partnerRegistrationType" name="registrationType" value="${partner.registrationType}" />
                                        <input type="hidden" id="partnerStatus" name="status" />
                                        </div>
                                        </div>
                                      <%--  <c:set var = "isCreater" scope = "session" value = "${partner.creatorOrbidder}"/> --%>
                                  <%--      <c:if test="${isCreater.equalsIgnoreCase('bidder')}"> --%>
                                          
                                       <%--  </c:if> --%>   
                                          
                                        <%-- <c:if test="${isCreater.equalsIgnoreCase('creater')}">
                                        <div class="form-group partnerTabs" style="margin-top:20px;">
                                            <div class="col-sm-12">
                                            <label>Partner Type<span class="red">*</span></label>
                                            
                                                <label class="checkbox-inline">
                                                    <input type="checkbox" name= "isContractor" id="isContractor" value="${partner.isContractor}">Contractor</label>
                                            
                                              <label class="checkbox-inline">
                                                    <input type="checkbox" name= "isContractor" id="isContractor" value="${partner.isContractor}">Manage Reverse Auction</label>
                                            
                                                <label class="checkbox-inline">
                                                    <input type="checkbox" name="isManufacturer" id="isManufacturer" value="${partner.isManufacturer}">Manufacturer</label>
                                            
                                                <label class="checkbox-inline">
                                                    <input type="checkbox" name="isTrader" id="isTrader" value="${partner.isTrader}">Trader</label>
                                            
                                                <label class="checkbox-inline">
                                                    <input type="checkbox" name="isCustomer" id="isCustomer" value="${partner.isCustomer}">Customer</label>
                                            
                                             <label class="checkbox-inline">
                                                    <input type="checkbox" name="isCustomer" id="isCustomer" value="${partner.isCustomer}">Manage Forward Auction</label>
                                            </div>
                                        </div>    
                                        </c:if>    --%>
                                        <!-- <div style="margin:30px;">
  <button type="button" class="btn btn-info" id="info">info alert</button>
  <button type="button" class="btn btn-warning" id="warn">warn alert</button>
</div>  -->                                   <div class="form-group partnerTabs" style="margin-top:20px;">
                                            <div class="col-sm-12">
                                            <label>Partner Type<span class="red">*</span></label>
                                            
                                               <%--  <label class="checkbox-inline">
                                                    <input type="checkbox" name= "isContractor" id="isContractor" value="${partner.isContractor}">Contractor</label>
                                             --%>
                                              <label class="checkbox-inline" >
                                                    <input type="checkbox" name= "isContractor" id="isContractor" value="${partner.isContractor}"><span id="isContractorLabel">Supplier</span></label>
                                             <label class="checkbox-inline"  >
                                                    <input type="checkbox" name="isCustomer" id="isCustomer" value="${partner.isCustomer}"><span id="isCustomerLabel">Buyer</span></label>
                                       
                                              <%--   <label class="checkbox-inline">
                                                    <input type="checkbox" name="isManufacturer" id="isManufacturer" value="${partner.isManufacturer}">Manufacturer</label>
                                            
                                                <label class="checkbox-inline">
                                                    <input type="checkbox" name="isTrader" id="isTrader" value="${partner.isTrader}">Trader</label> --%>
                                            
                                               <%--  <label class="checkbox-inline">
                                                    <input type="checkbox" name="isCustomer" id="isCustomer" value="${partner.isCustomer}">Customer</label>
                                            --%> 
                                            
                                            </div>
                                        </div>  
                                            <div class="form-group">                                            
                                                <div class="col-sm-3">
                                                    <div class="styled-input partnerTabs ">
                                                        <input type="text" id="name" name="name" value="${partner.name}" required readonly="readonly"/>
                                                        <label>Company Name<span class="red">*</span></label>
                                                        <span></span>
                                                    </div>
                                                </div>
                                                <div class="col-sm-3">
                                                    <div class="styled-input partnerTabs slate">
                                                        <select id="companyType" name="companyType" required>
                                                        
                                                        <c:forEach var="companyType" items="${companyTypes}">
                                                        	<option value="${companyType.code}">${companyType.name}</option>
                                                        </c:forEach>
                                                        </select>
                                                        <label>Company Type<span class="red">*</span></label>
                                                        <span></span>
                                                    </div>
                                                </div>

                                                <div class="col-sm-4">
                                                    <div class="styled-input partnerTabs">
                                                        <input type="text" id="crnNumber" name="crnNumber" value="${partner.crnNumber}" oninput="this.value = this.value.toUpperCase()" title="Company Registration Number" maxlength="21" required readonly="readonly"/>
                                                        <label>Company Registration Number<span class="red">*</span></label>
                                                        <span></span>
                                                    </div>
                                                </div>
                                                 <%-- <div class="col-sm-3">
														<label>Attach Company Registration Certificate<span class="red">*</span></label> 
														<div class="input-group partnerTabs">               
                                                          <input type="file"  id="companyRegCopyId" data-id="companyRegCertificate" data-name="compRegCertificate" data-anchor="a_companyRegCertificate" class="form-control uploadFile requiredFile">
                                                          <input type="hidden" id="companyRegCertificate" name="companyRegCertificate.attachmentId" class="form-control" /> 
												                <span class="input-group-btn">
												                    <button class="btn btn-default companyRegCertificate fileDeleteBtn" onclick="return submitWithThreeParam('deleteAttachmentById','Bpartner','companyRegCertificate','companyRegCertificate','companyRegCertDeleteResp');" disabled="disabled"><i class="fa fa-times"></i></button>
												                </span>
           	                                            </div>
           	                                           <!--  <input type="hidden" id="licenceFileName" name="licenceCopy.fileName" class="form-control" /> -->
           	                                            <a class="col-sm-12 filelabel" data-url="<%=request.getContextPath()%>/download" id="a_companyRegCertificate"></a>
													</div> --%>
                                            </div>

                                            <div class="clearfix"></div>

                                            <div class="form-group">
                                                <div class="col-sm-3 partnerTabs" style="margin-top: 40px;">
                                                <label class="checkbox-inline">
                                                    <input type="checkbox" name="isGstApplicable" value="${partner.isGstApplicable}" onchange="gstnumShow()" id="isGstApplicable">Is GSTIN Applicable</label>
                                            	</div>
												<div class="col-sm-3 GSTidenNumb">
                                                    <div class="styled-input partnerTabs">
                                                        <input type="text" id="gstinNo" name="gstinNo" class="gstIn" value ="${partner.gstinNo}" oninput="this.value = this.value.toUpperCase()" maxlength="15" required />
                                                        <label>GST Identification Number<span class="red">*</span></label>
                                                        <span></span>
                                                    </div>
                                                </div>
                                                <%-- <div class="col-sm-3 GSTidenNumb">
														<label>Attach GSTIN Copy<span class="red">*</span></label> 
														<div class="input-group partnerTabs">               
                                                          <input type="file"  id="GSTINCopyId" data-id="GSTINCopy" data-name="GSTINName" data-anchor="a_GSTINCopy" class="form-control uploadFile">
                                                          <input type="hidden" id="GSTINCopy" name="gstinCopy.attachmentId" class="form-control" /> 
												                <span class="input-group-btn">
												                    <button class="btn btn-default GSTINCopy fileDeleteBtn" onclick="return submitWithThreeParam('deleteAttachmentById','Bpartner','gstinCopy','GSTINCopy','GSTINAttachmentDeleteResp');" disabled="disabled"><i class="fa fa-times"></i></button>
												                </span>
           	                                            </div>
           	                                           <!--  <input type="hidden" id="licenceFileName" name="licenceCopy.fileName" class="form-control" /> -->
           	                                            <a class="col-sm-12 filelabel" data-url="<%=request.getContextPath()%>/download" id="a_GSTINCopy"></a>
													</div> --%>
                                                <div class="col-sm-3">
                                                    <div class="styled-input partnerTabs slate" id="contractorTypeDiv">
                                                        <select id="contractorType" name="contractorType" required >

                                                        	
															<c:forEach var="contractorType" items="${contractorTypes}">

	                                                        	<option value="${contractorType.code}" ${contractorType.code==partner.contractorType ? 'selected' :''}>${contractorType.name}</option>
	                                                        </c:forEach>                                                        
                                                         </select>
                                                        <label>Supplier Type<span class="red">*</span></label>
                                                        <span></span>
                                                    </div>
                                                </div>
 											
                                                <div class="col-sm-3">
                                                    <div class="styled-input partnerTabs">
                                                        <input type="text" id="panNumber" class="panNumber" name="panNumber" oninput="this.value = this.value.toUpperCase()" value="${partner.panNumber}" readonly="readonly"  maxlength="10" required />
                                                        <label>Pan Number<span class="red">*</span></label>
                                                        <span></span>
                                                    </div>
                                                </div>
                                                 <%-- <div class="col-sm-3">
														<label>Attach Pan Card Copy<span class="red">*</span></label> 
														<div class="input-group partnerTabs">               
                                                          <input type="file"  id="panCardCopyId" data-id="panCardCopy" data-name="panCardName" data-anchor="a_panCardCopy" class="form-control uploadFile requiredFile">
                                                          <input type="hidden" id="panCardCopy" name="panCardCopy.attachmentId" class="form-control" /> 
												                <span class="input-group-btn">
												                    <button class="btn btn-default panCardCopy fileDeleteBtn" onclick="return submitWithThreeParam('deleteAttachmentById','Bpartner','panCardCopy','panCardCopy','PanAttachmentDeleteResp');" disabled="disabled"><i class="fa fa-times"></i></button>
												                </span>
           	                                            </div>
           	                                           <!--  <input type="hidden" id="licenceFileName" name="licenceCopy.fileName" class="form-control" /> -->
           	                                            <a class="col-sm-12 filelabel" data-url="<%=request.getContextPath()%>/download" id="a_panCardCopy"></a>
													</div> --%>
                                            </div>
                                            
                                            <div class="clearfix"></div>
                                            
                                            <div class="form-group partnerTabs" style="margin-top:15px;">
                                                <label class="col-sm-10">Note : <span class="smalltext">Registration process does not guarantee award of any contract.</span></label>
                                            </div>
                                            <div class="clearfix"></div>
                                            <div class="form-group partnerTabs">
                                             <div class="col-md-12 col-xs-12">
                                                <label class="checkbox-inline">
                                                    <input type="checkbox" name="hasAccepted" class=" isActive" checked="checked" >I have read the standards terms and conditions as specified by e-Auction and I agree to them</label>
                                            </div>
                                               <!--  <div class="col-sm-12">
                                                    <input type="checkbox" class="checkbox">
                                               <label>I have read the standards terms and conditions as specified by e-Auction and I agree to them</label>
                                                </div> -->
                                                
                                            </div>
                                            <div class="form-group approveCEDiv" >
                                                    <label class="col-sm-2"><b>Add Comment:</b> </label>
														<div class="col-sm-4">
															<textarea class="form-control remark changeComment" name="ceCompDetailComment" id="ceRemark" maxlength="255" ></textarea>
														</div>
														<div class="col-sm-6">
															<label><b>Select Status:</b> </label> 
															<input type="radio"  value="Y" id="ceApproveBtn" name="isCECompDetailApproved"  class="statusBtn changeButton" checked />Approve 
															<!-- <input type="radio" name="isApproved" value="N" id="rejectBtn" class="statusBtn changeButton" /> Reject -->
														    <input type="radio"  value="C" name="isCECompDetailApproved" class="statusBtn changeButton"  id="ceClarification" /> Clarification
														</div>
											  </div>
                                            <div class="col-sm-12 text-center positionABS" style="margin-bottom:10px;">
		                                       <!--  <button class="btn-all btn btn-info  btnPrevious pull-left tab-li-prev">Previous</button> -->
		                                        <button class="btn btn-info save disableBtn" onclick="return submitIt('companyDetails','editPartner','processCompanyDetailResponse');">Save</button>
		                                        
		                                        <button class="btn-all btn btn-info  btnNext pull-right tab-li-next">Next</button>
		                                    </div>
                                            </sf:form>
                                            </div>
                                        </div>
                                        <div class="profileSettingClass">
                                          <div class="detailscont">
					  												                            
								          </div>
                                         <div style="margin-top:20px;">
						                    <sf:form id="profileSettingForm" method="POST" autocomplete="off" modelAttribute="profileSetting">
						                     <input type="hidden" class="form-control" id="profileSettingId" name="profileSettingId"/>
						                     <input type="hidden" id="no_imageUrl"/>
						                   <h4 class="col-sm-3">URL Pattern</h4>
						                   <div class="col-sm-4">
						                   		<input type="text" class="form-control" name="urlPattern" id="urlPatternId"/>
						                   </div>
						                   <div class="clearfix"></div>
						                  
						                   <h4 class="col-sm-3">Theme Color</h4>
						                   <input type="hidden" class="form-control" id="themeColor" name="themeColor"/>
						                    <div class="col-sm-6">
						                   		<div id="bg-selector"> 
													<div class="color green" id="green_themeId" data-value="#46a744" data-themecolor="green"></div>
													<div class="color redbg" id="redbg_themeId" data-value="red"  data-themecolor="redbg"></div>
													<div class="color skyblue"  id="skyblue_themeId" data-value="#07a3e8"  data-themecolor="skyblue"></div>
													<div class="color gray" id="gray_themeId" data-value="#eaeaea"  data-themecolor="gray"></div>
													<div class="color black" id="black_themeId" data-value="#000"  data-themecolor="black"></div>
													<div class="color blue" id="blue_themeId" data-value="#00529c"  data-themecolor="blue"></div>
													<div class="color orangecol" id="orangecol_themeId" data-value="#ca630b"  data-themecolor="orangecol"></div>
												</div>
						
						
												<div class="compcontainer">
												<div class="screen monitor bgprivew">
												 
												  <div class="base">
												    <div class="grey-shadow"></div>
												    <div class="foot top"></div>
												    <div class="foot bottom"></div>
												  </div>
												</div>       
												  </div>
									  
									</div>
						                    <div class="clearfix"></div>
						             
						                    <h4 class="col-sm-3">Upload Profile Logo</h4>
						                    <div class="col-sm-6">
						                   		<div class="avatar-upload">
											        <div class="avatar-edit">
											            <input type='file' id="imageUpload" accept=".png, .jpg, .jpeg" data-id="profileLogoId" data-name="downloadMom" data-anchor="downloadMom" class="form-control uploadImageFile"/>
											            <input type="hidden" id="profileLogoId" name="profileLogo.attachmentId" class="form-control fileResponse requiredField" /> 
																			                
											            <label for="imageUpload"><i class="fa fa-camera"></i></label>
											        </div>
											        <div class="avatar-preview">
											            <div id="imagePreview" >
											            </div>
											        </div>
											         <div class="col-sm-12 text-center" style="margin-top:10px;">
											                                        <button class="btn btn-info save" >Upload Image</button>
																			        <button id="deleteProfileLogoBtnId" class="btn btn-default profileLogoId" onclick="return submitWithParam('deleteAttachment','profileLogoId','profileLogoDeleteResp');"><i class="fa fa-times"></i></button>
																			               
											          </div>
											    </div>
						                   </div>
						                   </sf:form>
						                   <div class="col-sm-12 text-center" style="margin-top:10px;">
						                                        <button class="btn btn-info save" id="saveProfileSettingBtnId" onclick="return submitIt('profileSettingForm','updateProfileSetting','updateProfileSettingResp');">Save</button>
						                                    </div>
						                    </div>
						                 </div>
                                        <!--fields of field group 2  -->

                                        <!--fields of field group 3  -->
                                        <div>
                                        <div class="detailscont">
					  												                            
								        </div>
                                        <!-- tab inside tab -->
                                            <div id="tabstrip6">                                            
                                                <ul class="fiorisecondtab">
                                                    <li id="compContactChildTabId" data-parentTab="companyDetailsTabId" onclick="getCompanyContact(event,this);" class="k-state-active companyContactTab nonFilterTab"><div class="tabcircle"><i class="fa fa-address-card"></i></div>Company Contact</li>
                                                    <li id="compAddressTabId" data-parentTab="companyDetailsTabId" onclick="getPartnerCompAddress(event,this);" class="nonFilterTab"><div class="tabcircle"><i class="fa fa-address-book"></i></div>Company Address</li>
                                                </ul>
												 <!--tab inside tab fields 1  -->
                                                <div>
                                               <sf:form id="compContactForm" modelAttribute="companyContact" method="POST">
		                                        <!-- <div class="form-group"> -->

                                                                                   
                                                 <div class="form-group">
                                                 <input type="hidden" id="bPartnerId" class="bPartnerId" name="partner.bPartnerId" />
												 <input type="hidden" id="userDetailsId" name= "userDetailsId" value=""/>
												 <input type="hidden" id="isActive" name= "isActive" value="Y"/>
                                                 <input type="hidden" id="userTypeId" name="userDetailType" value="COMPUSR"/>
                                                <!-- <div class="col-sm-3">
                                                    <div class="styled-input">
                                                        <input type="text" id="title" name="title" class="requiredField" required />
                                                        <label>Title [Mr.,Miss.,Mrs.,Dr.]<span class="red">*</span></label>
                                                        <span></span>
                                                    </div>
                                                </div> -->
                                                <div class="col-sm-3">
															<div class="styled-input partnerTabs">
																<select id="title" name="title" class="dropDown"/></select> 
																<label>Title [Mr.,Miss.,Mrs.,Dr.]<span class="red">*</span></label>
																<span></span>
															</div>
														</div>

                                                <div class="col-sm-3">
                                                    <div class="styled-input partnerTabs">
                                                        <input type="text" id="firstName" name="firstName" class="requiredField requiredalphabeticsWithSpace" maxlength="20" />
                                                        <label>First name<span class="red">*</span></label>
                                                        <span></span>
                                                    </div>
                                                </div>

                                                <div class="col-sm-3">
                                                    <div class="styled-input partnerTabs">
                                                        <input type="text" id="middleName" name="middleName" class="requiredalphabeticsWithSpace" maxlength="20"/>
                                                        <label>Middle name</label>
                                                        <span></span>
                                                    </div>
                                                </div>

                                                <div class="col-sm-3">
                                                    <div class="styled-input partnerTabs">
                                                        <input type="text" id="lastName" name="LastName" class="requiredField requiredalphabeticsWithSpace" maxlength="20" />
                                                        <label>Last name<span class="red">*</span></label>
                                                        <span></span>
                                                    </div>
                                                </div>

                                            </div>
                                            
                                                    <div class="form-group">

                                                  <div class="col-sm-3">
                                                    <div class="styled-input partnerTabs">
                                                        <input type="text" id="telephone1" name="telephone1" class=" telephone" maxlength="15" />
                                                        <label>Landline 1<span class="red"></span></label>
                                                        <span></span>
                                                    </div>
                                                </div>

                                                <div class="col-sm-3">
                                                    <div class="styled-input partnerTabs">
                                                        <input type="text" id="telephone2" name="telephone2"  class="telephone" maxlength="15" />
                                                        <label>Landline 2</label>
                                                        <span></span>
                                                    </div>
                                                </div>

                                                <div class="col-sm-3">
                                                    <div class="styled-input partnerTabs">
                                                        <input type="text" id="mobileNo" name="mobileNo"  class="requiredField mobile" maxlength="10"/>
                                                        <label>Mobile<span class="red">*</span></label>
                                                        <span></span>
                                                    </div>
                                                </div>
                                                
                                                <div class="col-sm-3">
                                                    <div class="styled-input partnerTabs">
                                                        <input type="text" id="fax1" name="fax1" class="onlyNumber" maxlength="15" />
                                                        <label>Fax 1</label>
                                                        <span></span>
                                                    </div>
                                                </div>
											
                                            </div>
                                            <div class="clearfix"></div>

                                            <div class="form-group">

                                                <div class="col-sm-3">
                                                    <div class="styled-input partnerTabs">
                                                        <input type="text" id="fax2" name="fax2" class="onlyNumber" maxlength="15" />
                                                        <label>Fax 2</label>
                                                        <span></span>
                                                    </div>
                                                </div>

                                                <div class="col-sm-3">
                                                    <div class="styled-input partnerTabs">
                                                        <input type="text" id="email" name="email" class="requiredField emailaddress"/>
                                                        <label>Email Address<span class="red">*</span></label>
                                                        <span></span>
                                                    </div>
                                                </div>
													

													<!--                                                 <div class="col-sm-3">
                                                    <div class="styled-input">
                                                        <select id="locationTypeRef" name="loactionTypeRef" required /></select>
                                                        <label>Office Location Type<span class="red">*</span></label>
                                                        <span></span>
                                                    </div>
                                                </div>
 -->                                                
<!--                                                 <div class="col-sm-3">
                                                    <div class="styled-input">
                                                        <select id="location" name="location.locationId" required /></select>
                                                        <label>Select Office Location<span class="red">*</span></label>
                                                        <span></span>
                                                    </div>
                                                </div> -->
                                                <input type="hidden" id="isActive" value="Y">

                                            </div>
                                            <div class="clearfix"></div>
                                               <div class="form-group approveEEDiv" >
                                                    <label class="col-sm-2"><b>Add EE Comment:</b> </label>
														<div class="col-sm-4">
															<textarea class="form-control remark changeComment" id="eeRemark" name="eeComment" maxlength="255" ></textarea>
														</div>
														<div class="col-sm-6">
															<label><b>Select Status:</b> </label> 
															<input type="radio"  value="Y" id="eeApproveBtn" name="isEEApproved" class="statusBtn changeButton" checked />Approve 
															<!-- <input type="radio" name="isApproved" value="N" id="rejectBtn" class="statusBtn changeButton" /> Reject -->
														    <input type="radio"  value="C" name="isEEApproved" class="statusBtn changeButton"  id="eeClarification" /> Clarification
														</div>
											  </div>
											  <div class="form-group approveCEDiv" >
                                                    <label class="col-sm-2"><b>Add CE Comment:</b> </label>
														<div class="col-sm-4">
															<textarea class="form-control remark changeComment" name="ceComment" id="ceRemark" maxlength="255" ></textarea>
														</div>
														<div class="col-sm-6">
															<label><b>Select Status:</b> </label> 
															<input type="radio"  value="Y" id="ceApproveBtn" name="isCEApproved"  class="statusBtn changeButton" checked />Approve 
															<!-- <input type="radio" name="isApproved" value="N" id="rejectBtn" class="statusBtn changeButton" /> Reject -->
														    <input type="radio"  value="C" name="isCEApproved" class="statusBtn changeButton"  id="ceClarification" /> Clarification
														</div>
											  </div>
											  
                                              <div class="col-sm-12 text-center positionABS" style="margin-bottom:10px;">
		                                        <button class="btn-all btn btn-info   btnPrevious pull-left tab-li-prev">Previous</button>
		                                        <button class="btn btn-info save disableBtn" onclick="return submitIt('compContactForm','saveCompanyContact','companyContactResp');">Save</button>
		                                        <button class="btn btn-info cancel disableBtn" id="cancelCompContactBtnId" >Reset</button>
		                                        <button class="btn-all btn btn-info   btnNext pull-right tab-li-next">Next</button>
		                                    </div>

											</sf:form>
                                            
                                            </div>
                                            <!--tab inside tab fields 1  -->
                                            
                                    <!--tab inside tab fields 2  -->
										<div>
											<sf:form id="compAddressForm" modelAttribute="companyAddress"
												method="POST">
												<!-- <div class="form-group">
	                                        <div class="col-sm-12"><h4 ><b>Company Address Details</b></h4><hr></div></div>  -->

												<div class="form-group partnerTabs">
													<div class="col-sm-12 ">
														<h4 class="col-sm-6 nopadding">
															<b>Company Address Details</b>
														</h4>
														<div class="col-sm-6 text-right nopadding"
															style="margin-top: 10px;">
															<button class="btn btn-default" id="addAddressBtnId">
																<span class="glyphicon glyphicon-plus-sign"></span>Add
															</button>
															<!-- <button class="btn btn-default" id="editAddressBtnId">
																<span class="glyphicon glyphicon-pencil"></span>Edit
															</button> -->
															<button class="btn btn-default" id="deleteAddressBtnId" onclick="return submitWithParam('deleteCompanyAddress','partnerCompanyAddressId','partnerCompanyAddressDelResp');">
																<span class="glyphicon glyphicon-trash"></span>Delete
															</button>
														</div>
														<hr>
													</div>
												</div>
												
													<div class="form-group">

														<input type="hidden" id="bPartnerId" class="bPartnerId" name="partner.bPartnerId"/> <input
															type="hidden" id="userDetailsId" /> <input type="hidden"
															id="locationId" name="location.locationId" value="" /> <input
															type="hidden" id="partnerCompanyAddressId"
															name="partnerCompanyAddressId" value="" /> <input
															type="checkbox" id="isActive" name="isActive"
															style="display: none;" checked="checked" value="Y" /> <input
															type="checkbox" id="isActive" name="location.isActive"
															style="display: none;" checked="checked" value="Y" />

														<div class="col-sm-3 partnerTabs">
															<input type="checkbox" id="isShipToLocation"
																name="isShipToAddress" value="" /> <label>Ship
																to Address<span class="red"></span>
															</label> <span></span>
														</div>

														<div class="col-sm-3 partnerTabs">
															<input type="checkbox" id="isBillToLocation"
																name="isBillToAddress" value="" /> <label>Bill to Address<span class="red"></span>
															</label> <span></span>
														</div>
													</div>

													<div class="form-group ">

														<div class="col-sm-12">
															<div class="styled-input partnerTabs">
																<textarea type="text" id="registeredAddress" class="requiredField "
																	name="location.address1" maxlength="255"></textarea>
																<label>Registered Office Address<span
																	class="red">*</span></label> <span></span>
															</div>
														</div>
													</div>

													<div class="form-group">
														<div class="col-sm-3">
															<div class="styled-input partnerTabs">
																<input type="text" id="city" name="location.city" class="requiredField requiredalphabeticsWithSpace"
																	maxlength="25" /> <label>City<span class="red">*</span></label>
																<span></span>
															</div>
														</div>

														<div class="col-sm-3">
															<div class="styled-input partnerTabs">
																<select id="district" name="location.district.districtId" class="dropDown" >
																</select> <label>District<span class="red">*</span></label> <span></span>
															</div>
														</div>

														<div class="col-sm-3">
															<div class="styled-input partnerTabs">
																<select id="region" name="location.region.regionId" class="dropDown" ></select> <label>State<span class="red">*</span></label>
																<span></span>
															</div>
														</div>

														<div class="col-sm-3">
															<div class="styled-input partnerTabs">

																<select id="country" name="location.country.countryId" class="dropDown">
																</select> <label>Country<span class="red">*</span></label>


																<!-- <select id="region" name="location.region.regionId" class="dropDown"
																	required /></select> <label>State<span class="red">*</span></label> -->



																<!-- <select id="region" name="location.region.regionId" class="dropDown"
																	required /></select> <label>State<span class="red">*</span></label> -->


																<span></span>
															</div>
														</div>

													</div>

													<div class="form-group">
														<div class="col-sm-3">
															<div class="styled-input partnerTabs">
																<input type="text" id="pincode" name="location.postal" class="requiredField pincode" maxlength="6">
																 <label>Pincode<span class="red">*</span></label>
																<span></span>
															</div>
														</div>

													</div>
												 <div class="clearfix"></div>
                                               <div class="form-group approveEEDiv" >
                                                    <label class="col-sm-2"><b>Add EE Comment:</b> </label>
														<div class="col-sm-4">
															<textarea class="form-control remark changeComment" name="eeComment"id="eeRemark" maxlength="255" ></textarea>
														</div>
														<div class="col-sm-6">
															<label><b>Select Status:</b> </label> 
															<input type="radio"  value="Y" id="eeApproveBtn"  name="isEEApproved" class="statusBtn changeButton" checked />Approve 
															<!-- <input type="radio" name="isApproved" value="N" id="rejectBtn" class="statusBtn changeButton" /> Reject -->
														    <input type="radio"  value="C" id="eeClarification" name="isEEApproved" class="statusBtn changeButton"  /> Clarification
														</div>
											  </div>
											  <div class="form-group approveCEDiv" >
                                                    <label class="col-sm-2"><b>Add CE Comment:</b> </label>
														<div class="col-sm-4">
															<textarea class="form-control remark changeComment" name="ceComment" id="ceRemark" maxlength="255" ></textarea>
														</div>
														<div class="col-sm-6">
															<label><b>Select Status:</b> </label> 
															<input type="radio"  value="Y" id="ceApproveBtn" name="isCEApproved"  class="statusBtn changeButton" checked />Approve 
															<!-- <input type="radio" name="isApproved" value="N" id="rejectBtn" class="statusBtn changeButton" /> Reject -->
														    <input type="radio"  value="C" name="isCEApproved" class="statusBtn changeButton"  id="ceClarification" /> Clarification
														</div>
											  </div>
												<div class="col-sm-12 text-center positionABS"
													style="margin-bottom: 10px;">
													<button
														class="btn-all btn btn-info   k-tabstrip-prev btnPrevious pull-left tab-li-prev">Previous</button>
													<span class="" id="formCompanyAddressDivId">
													<button class="btn btn-info save disableBtn"
														onclick="return submitIt('compAddressForm','saveCompanyAddress','companyAddressResp');">Save</button>
													<button class="btn btn-info cancel disableBtn" id="cancelAddressBtnId" >Reset</button>
													</span>
													<button
														class="btn-all btn btn-info   k-tabstrip-next btnNext pull-right tab-li-next">Next</button>
												</div>
											</sf:form>
										</div>
										<!--tab inside tab fields 2  -->

                                        </div>
                                        </div>
                                        
                                        <!--fields of field group 3  -->

                                        <!--fields of field group 4  -->
                                       <%--  <div>
                                        <div class="detailscont">
					  							 <div class="col-md-12">
					                                <label class="col-xs-6"> <h4>XXXX</h4></label>
					                            	<label class="col-xs-6 ">XXXX</label>
					                            </div>	
					                            <div class="col-md-12">
					                           		<label class="col-xs-6">XXXX</label>
					                            	<label class="col-xs-6 ">XXXX</label>
					                            </div>								                            
								        </div>
                                        <sf:form id="partnerSignatoryForm" modelAttribute="partnerSignatory" method="POST">
                                        <div class="form-group partnerTabs">
                                        <div class="col-sm-12">
                                        <h4 class="col-xs-6 nopadding"><b>Digital Signature Details</b></h4>
                                        <div class="col-xs-6 text-right nopadding" style="margin-top:10px;">
                                                    <button class="btn btn-default" id="addSignatureBtnId"><span class="glyphicon glyphicon-plus-sign"></span>Add</button>
                                                    <!-- <button class="btn btn-default" id="editSignatureBtnId"><span class="glyphicon glyphicon-pencil"></span>Edit</button> -->
                                                    <button class="btn btn-default" id="deleteSignatureBtnId"><span class="glyphicon glyphicon-trash"></span>Delete</button></div>
                                        <hr>
                                        </div>
                                        </div>
                                        
                                            <div class="form-group">
                                            
                                                      <div class="col-sm-3" style="display: none;">
                                                            <div class="styled-input">
                                                            <input type="hidden" id="bPartnerId"  class="bPartnerId" name="partner.bPartnerId" required />
                                                            <input type="hidden" id="partnerSignatoryId" name="partnerSignatoryId"  required />
                                                            <input type="hidden" id="locationId" name="location.locationId"  required />
                                                            <input type="hidden" id="userDetailId" name="userDetail.userDetailsId"  required />
                                                           </div>
                                                     </div>
                                                     <input type="checkbox" style="display:none;"id="isActive" name="isActive" value="Y" checked="checked"/>
                                                     <input type="checkbox" style="display:none;"id="LocationIsActive" name="location.isActive" value="Y" checked="checked"/>
                                                     <input type="checkbox" style="display:none;"id="userDeatilIsActive" name="userDetail.isActive" value="Y" checked="checked"/>
                                                <!-- <div class="col-sm-3">
                                                    <div class="styled-input">
                                                        <input type="text" id="title" name="userDetail.title" class="requiredField" />
                                                        <label>Title [Mr.,Miss.,Mrs.,Dr.]<span class="red">*</span></label>
                                                        <span></span>
                                                    </div>
                                                </div> -->
                                                
                                                <div class="col-sm-3 partnerTabs">
                                                <button class="btn btn-default" id="copyAddress"><span class="glyphicon glyphicon-plus-sign"></span>Copy Address</button>
															<!-- <input type="checkbox" id="copyAddress" value="" /> 
															<label>Copy Address</label> <span></span> -->
														</div>
														</div>
														
														<div class="form-group partnerTabs">
														<div class="col-sm-3">
															<div class="styled-input">
																<select id="title" name="userDetail.title" class="dropDown"/></select> 
																<label>Title [Mr.,Miss.,Mrs.]<span class="red">*</span></label>
																<span></span>
															</div>
														</div>
                                                <div class="col-sm-3">
                                                    <div class="styled-input">
                                                        <input type="text" id="firstName" name="userDetail.firstName" class="requiredField requiredalphabeticsWithSpace" maxlength="20" />
                                                        <label>First name<span class="red">*</span></label>
                                                        <span></span>
                                                    </div>
                                                </div>

                                                <div class="col-sm-3">
                                                    <div class="styled-input">
                                                        <input type="text" id="middleName" name="userDetail.middleName" class="requiredalphabeticsWithSpace" maxlength="20"/>
                                                        <label>Middle name<!-- <span class="red">*</span> --></label>
                                                        <span></span>
                                                    </div>
                                                </div>

                                                <div class="col-sm-3">
                                                    <div class="styled-input">
                                                        <input type="text" id="lastName" name="userDetail.lastName" class="requiredField requiredalphabeticsWithSpace" maxlength="20" />
                                                        <label>Last name<span class="red">*</span></label>
                                                        <span></span>
                                                    </div>
                                                </div>

                                            </div>

                                            <div class="clearfix"></div>
                                            <div class="form-group partnerTabs">
                                                    <div class="col-sm-3">
                                                    <div class="styled-input">
                                                        <input type="text" id="email" name="userDetail.email" class="requiredField emailaddress"  />
                                                        <label>Email Address<span class="red">*</span></label>
                                                        <span></span>
                                                    </div>
                                                </div>

                                                <div class="col-sm-3">
                                                    <div class="styled-input">
                                                        <select id="designationId" name="userDetail.designation.designationId" class="dropDown" /></select>
                                                        <label>Designation<span class="red">*</span></label>
                                                        <span></span>
                                                    </div>
                                                </div>
                                                    <div class="col-sm-3">
                                                    <label>Gender: <span class="red">*</span>
                                                        <input type="radio" id="radio_male" name="userDetail.gender"  class="genderType" value="male" checked> Male<br>
                                                        <input type="radio" id="radio_female" name="userDetail.gender" class="input_margin genderType" value="female"> Female<br>
                                                        <input type="radio" id="radio_other" name="userDetail.gender" class="input_margin genderType" value="other"> Other<br>
                                                    </label>
                                                </div>
                                               <div class="col-sm-3">
                                                    <div class="form-group">
                                                        <label>Date of Birth<!-- <span class="red">*</span> --></label>
                                                        <div class="input-group date" data-provide="datepicker">
                                                            <input type="text" class="form-control" title="Date of Birth" id="dob" name="userDetail.dob">
                                                            <div class="input-group-addon">
                                                                <span class="glyphicon glyphicon-th"></span>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>                                                
                                            </div>
                                           <div class="clearfix"></div>
                                           <div class="form-group partnerTabs">
												<div class="col-sm-3">
                                                    <div class="styled-input">
                                                        <input type="text" id="telephone1" name="userDetail.telephone1" class="telephone" title="Enter 10 digit number" maxlength="15"/>
                                                        <label>Landline 1<span class="red"></span></label>
                                                        <span></span>
                                                    </div>
                                                </div>

                                                <div class="col-sm-3">
                                                    <div class="styled-input">
                                                        <input type="text" id="telephone2" name="userDetail.telephone2" class="telephone" title="Enter 10 digit number" maxlength="15"/>
                                                        <label>Landline 2</label>
                                                        <span></span>
                                                    </div>
                                                </div>

                                                <div class="col-sm-3">
                                                    <div class="styled-input">
                                                        <input type="text" id="mobileNo" name="userDetail.mobileNo"  class="mobile requiredField" title="Enter 10 digit number" maxlength="10"/>
                                                        <label>Mobile<span class="red">*</span><!-- <span class="red">*</span> --></label>
                                                        <span></span>
                                                    </div>
                                                </div>
                                                <div class="col-sm-3">
                                                    <div class="styled-input">
                                                        <input type="text" id="fax1" name="userDetail.fax1"  class="onlyNumber" title="Enter 10 digit number" maxlength="15"/>
                                                        <label>Fax 1</label>
                                                        <span></span>
                                                    </div>
                                                </div>

                                                <div class="col-sm-3">
                                                    <div class="styled-input">
                                                        <input type="text" id="fax2" name="userDetail.fax2" class="onlyNumber" title="Enter 10 digit number" maxlength="15"/>
                                                        <label>Fax 2</label>
                                                        <span></span>
                                                    </div>
                                                </div> 
                                                <div class="col-sm-3">
                                                    <div class="form-group">
                                                        <label>Validity<!-- <span class="red">*</span> --></label>
                                                        <div class="input-group date" data-provide="datepicker">
                                                            <input type="text" class="form-control" title="Validity" id="validFrom" name="validFrom">
                                                            <div class="input-group-addon">
                                                                <span class="glyphicon glyphicon-th"></span>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>

                                            </div>
                                            <div class="form-group partnerTabs">
                                                <div class="col-sm-12">
                                                    <div class="styled-input">
                                                        <textarea type="text" id="registeredAddress" name="location.address1"  class="requiredField" maxlength="255"></textarea>
                                                        <label>Registered Office Address<span class="red">*</span></label>
                                                        <span></span></div>
                                                </div>

                                            </div>

                                            <div class="clearfix"></div>

                                            <div class="form-group partnerTabs">
                                                <div class="col-sm-3">
                                                    <div class="styled-input">
                                                        <input type="text" id="city" name="location.city" class="requiredField requiredalphabeticsWithSpace" maxlength="25"/>
                                                        <label>City<span class="red">*</span></label>
                                                        <span></span>
                                                    </div>
                                                </div>

                                                <div class="col-sm-3">
                                                    <div class="styled-input">
                                                        <select id="districtId" name="location.district.districtId" class="dropDown" /></select>
                                                        <label>District<span class="red">*</span></label>
                                                        <span></span>
                                                    </div>
                                                </div>

                                                <div class="col-sm-3">
                                                    <div class="styled-input">
                                                        <select id="regionId" name="location.region.regionId" class="dropDown"/></select>
                                                        <label>State<span class="red">*</span></label>
                                                        <span></span>
                                                    </div>
                                                </div>

                                                <div class="col-sm-3">
                                                    <div class="styled-input">
                                                        <select id="countryId" name="location.country.countryId" class="dropDown" /></select>
                                                        <label>Country<span class="red">*</span></label>
                                                        <span></span>
                                                    </div>
                                                </div>

                                            </div>

                                            <div class="clearfix"></div>
                                            <div class="form-group ">

                                                <div class="col-sm-3">
                                                    <div class="styled-input partnerTabs">
                                                        <input type="text" id="postal" name="location.postal" class="pincode requiredField" maxlength="6"/>
                                                        <label>Pincode<span class="red">*</span></label>
                                                        <span></span>
                                                    </div>
                                                </div>
                                                <div class="col-sm-3">
                                                    <div class="styled-input partnerTabs">
                                                        <select id="holderTypeId" name="holderType" class="dropDown"  onchange="showFileField();"/></select>
                                                        <label>Holder Type<span class="red">*</span></label>
                                                        <span></span>
                                                    </div>
                                                </div>
                                                <div class="col-sm-3" >
														<label>Attach Digitally Sign Test Document<span class="red">*</span></label> 
														<div class="input-group partnerTabs">               
                                                          <input type="file"  id="digitallySignFileId" data-id="digitallySignCopy" data-name="digitallySignCopyName" data-anchor="a_digitallySignCopyCopy" class="form-control uploadFile requiredFile">
                                                          <input type="hidden" id="digitallySignCopy" name="digitallySignTestDoc.attachmentId" class="form-control" /> 
												                <span class="input-group-btn">
												                    <button class="btn btn-default digitallySignCopy fileDeleteBtn" onclick="return submitWithThreeParam('deleteAttachmentById','PartnerSignatory','digitallySignTestDoc','digitallySignCopy','digitallySignCopyDeleteResp');" disabled="disabled"><i class="fa fa-times"></i></button>
												                </span>
           	                                            </div>
           	                                           <!--  <input type="hidden" id="licenceFileName" name="licenceCopy.fileName" class="form-control" /> -->
           	                                            <a class="col-sm-12 filelabel" data-url="<%=request.getContextPath()%>/download" id="a_digitallySignCopyCopy"></a>
													</div>
													 <div class="col-sm-3" id="attorneyCertDiv">
														<label>Attach Power Of Attorney Certificate<span class="red">*</span></label> 
														<div class="input-group partnerTabs">               
                                                          <input type="file"  id="powerAttorneyFileId" data-id="powerAttorneyCopy" data-name="powerAttorneyCopyName" data-anchor="a_powerAttorneyCopy" class="form-control uploadFile">
                                                          <input type="hidden" id="powerAttorneyCopy" name="attorneyCertificate.attachmentId" class="form-control" /> 
												                <span class="input-group-btn">
												                    <button class="btn btn-default powerAttorneyCopy" onclick="return submitWithThreeParam('deleteAttachmentById','PartnerSignatory','attorneyCertificate','powerAttorneyCopy','powerAttorneyCopyDeleteResp');" disabled="disabled"><i class="fa fa-times"></i></button>
												                </span>
           	                                            </div>
           	                                           <!--  <input type="hidden" id="licenceFileName" name="licenceCopy.fileName" class="form-control" /> -->
           	                                            <a class="col-sm-12 filelabel" data-url="<%=request.getContextPath()%>/download" id="a_powerAttorneyCopy"></a>
													</div> 

                                            </div>

                                            <div class="clearfix"></div>
                                            
                                           <div class="clearfix"></div>
                                               <div class="form-group approveEEDiv" >
                                                    <label class="col-sm-2"><b>Add EE Comment:</b> </label>
														<div class="col-sm-4">
															<textarea class="form-control remark changeComment" name="eeComment"id="eeRemark" maxlength="255" ></textarea>
														</div>
														<div class="col-sm-6">
															<label><b>Select Status:</b> </label> 
															<input type="radio"  value="Y" id="eeApproveBtn"  name="isEEApproved" class="statusBtn changeButton" checked />Approve 
															<!-- <input type="radio" name="isApproved" value="N" id="rejectBtn" class="statusBtn changeButton" /> Reject -->
														    <input type="radio"  value="C" id="eeClarification" name="isEEApproved" class="statusBtn changeButton"  /> Clarification
														</div>
											  </div>
											  <div class="form-group approveCEDiv" >
                                                    <label class="col-sm-2"><b>Add CE Comment:</b> </label>
														<div class="col-sm-4">
															<textarea class="form-control remark changeComment" name="ceComment" id="ceRemark" maxlength="255" ></textarea>
														</div>
														<div class="col-sm-6">
															<label><b>Select Status:</b> </label> 
															<input type="radio"  value="Y" id="ceApproveBtn" name="isCEApproved"  class="statusBtn changeButton" checked />Approve 
															<!-- <input type="radio" name="isApproved" value="N" id="rejectBtn" class="statusBtn changeButton" /> Reject -->
														    <input type="radio"  value="C" name="isCEApproved" class="statusBtn changeButton"  id="ceClarification" /> Clarification
														</div>
											  </div>
                                             <div class="col-sm-12 text-center positionABS" style="margin-bottom:10px;">
                                                        <button class="btn-all btn btn-info  k-tabstrip-prev btnPrevious pull-left tab-li-prev">Previous</button>
                                                        <span class="" id="sigformDivId">
                                                        <button class="btn btn-info save disableBtn" onclick="return submitIt('partnerSignatoryForm','saveSignatoryDetails','onPartnerOrgSigResp');">Save</button>
                                                         <button class="btn btn-info cancel disableBtn" id="cancelSignatoryBtnId" >Reset</button>
                                                        </span>
                                                        <button class="btn-all btn btn-info  k-tabstrip-next btnNext pull-right tab-li-next">Next</button>
                                                     </div>
                                            </sf:form>
                                        </div> --%>
                                        <!--fields of field group 4  -->

                                        								<!--fields of field group 5  -->
<%-- 								<div>
									<sf:form id="directorDetailsForm" modelAttribute="userDetails"
										method="POST">
										<div class="detailscont">
											<div class="col-md-12">
												<label class="col-xs-6"><h4>XXXX</h4></label> <label
													class="col-xs-6 ">XXXX</label>
											</div>
											<div class="col-md-12">
												<label class="col-xs-6">XXXX</label> <label
													class="col-xs-6 ">XXXX</label>
											</div>
										</div>
										<div class="form-group partnerTabs">
											<div class="col-sm-12">
												<h4 class="col-xs-6 nopadding">
													<b>Management Details</b>
												</h4>
												<div class="col-xs-6 text-right nopadding"
													style="margin-top: 10px;">
													<button class="btn btn-default" id="addDirectorBtnId">
														<span class="glyphicon glyphicon-plus-sign"></span>Add
													</button>
													<!-- <button class="btn btn-default" id="editDirectorBtnId">
														<span class="glyphicon glyphicon-pencil"></span>Edit
													</button> -->
													<button class="btn btn-default" id="deleteDirectorBtnId"
														onclick="return submitWithParam('deleteDirector','partnerDirectorDetailsId','directorDetailsDelResp');">
														<span class="glyphicon glyphicon-trash"></span>Delete
													</button>

												</div>
												<hr>
											</div>
										</div>
										
											<div class="form-group partnerTabs">

												<input type="hidden" id="bPartnerId" class="bPartnerId" name="partner.bPartnerId" /> <input
													type="hidden" id="partnerDirectorDetailsId"
													name="userDetailsId" value="" /> <input type="hidden"
													id="userDetailsId" /> <input type="hidden" id="locationId"
													name="location.locationId" value="" /> <input
													type="checkbox" id="isActive" name="isActive"
													style="display: none;" checked="checked" value="Y" /> <input
													type="checkbox" id="isActive" name="location.isActive"
													style="display: none;" checked="checked" value="Y" /> <input
													type="hidden" id="userDetailType" name="userDetailType"
													value="COMPDIR" />
												<!-- <div class="col-sm-3">
													<div class="styled-input">
														<input type="text" id="title" name="title" required class="requiredField requiredalphabeticsWithSpace" /> <label>Title
															[Mr.,Miss.,Mrs.,Dr.]<span class="red">*</span>
														</label> <span></span>
													</div>
												</div> -->
												<div class="col-sm-3">
												<button class="btn btn-default" id="sameDirectorAddress"><span class="glyphicon glyphicon-plus-sign"></span>Copy Address</button>
															<!-- <input type="checkbox" id="sameDirectorAddress" value="" /> 
															<label>Copy Address</label> <span></span> -->
														</div>
														</div>
														
													<div class="form-group partnerTabs">	
												<div class="col-sm-3">
															<div class="styled-input">
																<select id="title" name="title" class="dropDown "/></select> 
																<label>Title [Mr.,Miss.,Mrs.]<span class="red">*</span></label>
																<span></span>
															</div>
														</div>

												<div class="col-sm-3">
													<div class="styled-input">
														<input type="text" id="firstName" name="firstName" class="requiredField requiredalphabeticsWithSpace"
															maxlength="20" /> <label>First Name<span class="red">*</span></label>
														<span></span>
													</div>
												</div>

												<div class="col-sm-3">
													<div class="styled-input">
														<input type="text" id="middleName" name="middleName" class="requiredalphabeticsWithSpace"
															maxlength="20" /> <label>Middle Name</label>
														<span></span>
													</div>
												</div>

												<div class="col-sm-3">
													<div class="styled-input">
														<input type="text" id="lastName" name="lastName" class="requiredField requiredalphabeticsWithSpace" maxlength="20" />
														<label>Last Name<span class="red">*</span></label> <span></span>
													</div>
												</div>
											</div>
											<div class="form-group partnerTabs">
											<div class="col-sm-3">
													<div class="styled-input">
														<input type="text" id="email" name="email" class="requiredField emailaddress" required /> <label>Email
															Id<span class="red">*</span>
														</label> <span></span>
													</div>
												</div>

												<div class="col-sm-3">
													<div class="styled-input">

														<select id="designation" name="designation.designationId" class="dropDown"
															required /></select> <label>Designation<span class="red">*</span></label>
														<span></span>
													</div>
												</div>

												<div class="col-sm-3">
													<div class="styled-input">
														<input type="text" id="mobileNo" name="mobileNo" class="requiredField mobile"
															title="Emter 10 digit number" maxlength="10" /> <label>Mobile
															Number<span class="red">*</span>
														</label> <span></span>
													</div>
												</div>
											
												<div class="col-sm-3">
													<div class="styled-input">
														<input type="text" id="telephone1" name="telephone1" class=" telephone"
															title="Enter 10 digit number" maxlength="15" /> <label>Landline
															1<span class="red"></span>
														</label> <span></span>
													</div>
												</div>
												<div class="clearfix"></div>
												<div class="col-sm-3">
													<div class="styled-input">
														<input type="text" id="telephone2" name="telephone2" class="telephone"
															title="Enter 10 digit number" maxlength="15"/> <label>Landline
															2</label> <span></span>
													</div>
												</div>

												<div class="col-sm-3">
													<div class="styled-input">
														<input type="text" id="fax1" name="fax1" 
															title="Enter 10 digit number" maxlength="15"/> <label>Fax 1</label> <span></span>
													</div>
												</div>

												<div class="col-sm-3">
													<div class="styled-input">
														<input type="text" id="fax2" name="fax2" class="telephone"
															title="Enter 10 digit number" maxlength="15"/> <label>Fax 2</label> <span></span>
													</div>
												</div>
											</div>
											<div class="clearfix"></div>

											<div class="form-group partnerTabs">
												<div class="col-sm-12">
													<div class="styled-input">
														<textarea type="text" id="address1" name="location.address1" class="requiredField " maxlength="255"></textarea>
														<label>Registered Office Address<span class="red">*</span></label>
														<span></span>
													</div>
												</div>
											</div>

											<div class="clearfix"></div>

											<div class="form-group partnerTabs">
												<div class="col-sm-3">
													<div class="styled-input">
														<input type="text" id="city" name="location.city" class="requiredField requiredalphabeticsWithSpace" maxlength="20" />
														<label>City<span class="red">*</span></label>
													</div>
												</div>

												<div class="col-sm-3">
													<div class="styled-input">
														<select id="district" name="location.district.districtId" class="dropDown"
															required /></select> <label>District<span class="red">*</span></label>
														<span></span>
													</div>
												</div>

												<div class="col-sm-3">
													<div class="styled-input">
														<select id="country" name="location.country.countryId" class="dropDown"
															required /></select> <label>Country<span class="red">*</span></label>
														<span></span>
													</div>
												</div>


												<div class="col-sm-3">
													<div class="styled-input"> 
														<select id="region" name="location.region.regionId" class="dropDown"
															required /></select> <label>State<span class="red">*</span></label>
													</div>
												</div>
											</div>

											<div class="clearfix"></div>

											<div class="form-group partnerTabs">
												<div class="col-sm-3">
													<div class="styled-input">
														<input type="text" id="postal" name="location.postal" class="requiredField pincode"
															maxlength="6" /> <label>Pincode<span class="red">*</span></label>
														<span></span>
													</div>
												</div>

																							</div>

											<div class="clearfix"></div>

											
											<div class="clearfix"></div>
                                               <div class="form-group approveEEDiv" >
                                                    <label class="col-sm-2"><b>Add EE Comment:</b> </label>
														<div class="col-sm-4">
															<textarea class="form-control remark changeComment" name="eeComment"  id="eeRemark" maxlength="255" ></textarea>
														</div>
														<div class="col-sm-6">
															<label><b>Select Status:</b> </label> 
															<input type="radio"  value="Y" id="eeApproveBtn"  name="isEEApproved" class="statusBtn changeButton" checked />Approve 
															<!-- <input type="radio" name="isApproved" value="N" id="rejectBtn" class="statusBtn changeButton" /> Reject -->
														    <input type="radio"  value="C" id="eeClarification" name="isEEApproved" class="statusBtn changeButton"  /> Clarification
														</div>
											  </div>
											  <div class="form-group approveCEDiv" >
                                                    <label class="col-sm-2"><b>Add CE Comment:</b> </label>
														<div class="col-sm-4">
															<textarea class="form-control remark changeComment" name="ceComment" id="ceRemark" maxlength="255" ></textarea>
														</div>
														<div class="col-sm-6">
															<label><b>Select Status:</b> </label> 
															<input type="radio"  value="Y" id="ceApproveBtn"  name="isCEApproved" class="statusBtn changeButton" checked />Approve 
															<!-- <input type="radio" name="isApproved" value="N" id="rejectBtn" class="statusBtn changeButton" /> Reject -->
														    <input type="radio"  value="C" id="ceClarification" name="isCEApproved" class="statusBtn changeButton"  /> Clarification
														</div>
											  </div>
											<div class="col-sm-12 text-center positionABS"
												style="margin-bottom: 10px;">
												<button
													class="btn-all btn btn-info  k-tabstrip-prev btnPrevious pull-left tab-li-prev">Previous</button>
												<span class="" id="directorFormDivId">
												<button class="btn btn-info save disableBtn" onclick="return submitIt('directorDetailsForm','saveDirectorDetails','partnerDirectorDetailsResp');">Save</button>
												<button class="btn btn-info cancel disableBtn" id="cancelDirectorBtnId" >Reset</button>
												</span>
												<button
													class="btn-all btn btn-info  k-tabstrip-next btnNext pull-right tab-li-next">Next</button>
											</div>
										
									</sf:form>

								</div> --%>

								<!--fields of field group 5  -->

                                       
                                        <!--fields of field group 6  -->
                                        <%-- <div>
                                        
								        <div class="detailscont">
											<div class="col-md-12">
												<label class="col-xs-6"><h4>XXXX</h4></label> <label
													class="col-xs-6 ">XXXX</label>
											</div>
											<div class="col-md-12">
												<label class="col-xs-6">XXXX</label> <label
													class="col-xs-6 ">XXXX</label>
											</div>
										</div>
                                            <!-- tab inside tab -->
                                            <div id="tabstrip3">
                                                <ul>
                                                    <li onclick="return submitWithParam('getOrgDetails','bPartnerId','onPartnerOrgTabLoad');" class="k-state-active" >Factory Details</li>
                                                  <!--   <li onclick="return submitWithParam('getOrgLicense','partnerOrgId','onPartnerOrgLicenseTabLoad')">License Details</li> -->
                                                    <li class="disableFactoryTabs" onclick="return submitWithParam('getOrgUser','partnerOrgId','onOrgUserTabLoad');">Contact Person</li>


                                                    <li class="disableFactoryTabs" onclick="return submitWithParam('getOrgExperience','partnerOrgId','onPartnerOrgExperienceTabLoad');">Experience Details</li>
                                                                                                        
                                                   
                                                   <li class="disableFactoryTabs" onclick="return submitWithParam('getOrgRegistration','partnerOrgId','onPartnerOrgRegTabLoad');">Factory Registration</li>
                                                   <li class="disableFactoryTabs" onclick="return submitWithParam('getOrgBISDetails','partnerOrgId','onPartnerOrgBISTabLoad');">BIS Details</li>
                                                    <li class="disableFactoryTabs" onclick="return submitWithParam('getPartnerOrgProducts','partnerOrgId','onPartnerOrgProductTabLoad');">Items Manufactured</li>
                                                    <!-- <li>MSME Registration</li> -->
                                                    
                                                    <li class="disableFactoryTabs" onclick="return submitWithParam('getOrgPerformance','partnerOrgId','onPartnerOrgPerformanceTabLoad');">Past Performance</li>
                                                    <li class="disableFactoryTabs" onclick="return submitWithParam('getOrgRDAEC','partnerOrgId','onPartnerOrgRDAECTabLoad');">RDAEC</li>
                                                    <li class="disableFactoryTabs" onclick="return submitWithParam('getOrgOtherDetails','partnerOrgId','onPartnerOrgOEDTabLoad');">Other Eligibilty Details</li>
                                                     <li class="disableFactoryTabs" onclick="return submitWithParam('getOrgCertificate','partnerOrgId','onPartnerOrgCertificationTabLoad');">ISO Certification</li>
                                                    <li id="factoryInspectectionTabId"  style="display: none;"  onclick="return submitWithParam('getOrgInspection','partnerOrgId','onPartnerOrgInspectionTabLoad');">Factory Inspection</li>
                                                </ul>
												 <!--tab inside tab fields 1  -->
                                                <div>
                                                <sf:form id="partnerOrgForm" modelAttribute="partnerOrg" method="POST">
                                                <div class="form-group">
                                        <div class="col-sm-12">
                                        <h4 class="col-xs-6 nopadding"><b>Factory Essential Details</b></h4>
                                        <div class="col-xs-6 text-right nopadding" style="margin-top:10px;">
                                                    <button class="btn btn-default" id="addFactoryBtnId"><span class="glyphicon glyphicon-plus-sign"></span>Add</button>
                                                    <button class="btn btn-default" id="editFactoryBtnId"><span class="glyphicon glyphicon-pencil"></span>Edit</button> 
                                                    <button class="btn btn-default" id="deleteFactoryBtnId" onclick="return submitWithTwoParam('deleteOrg','partnerOrgId','orgPaymmentId','partnerOrgDelResp');"><span class="glyphicon glyphicon-trash"></span>Delete</button></div>
                                        <hr>
                                        </div>
                                        </div>
                                                  
                                                         
                                                    <div class="form-group partnerOrgTabs">
                                                    <div class="col-sm-3" style="display: none;">
                                                            <div class="styled-input">
                                                            <input type="hidden" id="bPartnerId"  class="bPartnerId" name="partner.bPartnerId" required />
                                                            <input type="hidden" id="partnerOrgLocationId"  name="location.locationId" required />
                                                            <input type="hidden" id="partnerOrgId"  name="partnerOrgId" required />
                                                            <input type="hidden" id="isActive"  name="isActive"  value="Y" required />
                                                             <input type="hidden" id="isActive"  name="location.isActive" value="Y" required />
                                                           </div>
                                                     </div>
                                                        <div class="col-sm-3">
                                                            <div class="styled-input">
                                                                <input type="text" id="name" name="name" class="requiredField requiredalphanumericWithSpaceAndDot" maxlength="100"/>
                                                                <label>Factory Name<span class="red">*</span></label>
                                                                <span></span>
                                                            </div>
                                                        </div>

                                                  <div class="col-sm-3">
                                                            <!-- <div class="styled-input">
                                                                <input type="text" id="estdDate" name="estdDate" required />
                                                                <label>Established date of Factory<span class="red">*</span></label>
                                                                <span></span>
                                                            </div> -->
                                                             <label>Established date of Factory<!-- <span class="red">*</span> --></label>
                                                                                <div class="input-group date Pickdate" data-provide="datepicker">
                                                                                    <input type="text" class="form-control" id="estdDate" name="estdDate">
                                                                                    <div class="input-group-addon">
                                                                                        <span class="glyphicon glyphicon-th"></span>
                                                                                    </div>
                                                             </div>
                                                        </div> 

                                                        <div class="col-sm-3">
                                                            <div class="styled-input">
                                                                <input type="text" id="manPower" name="manPower" class="onlyNumber requiredField" maxlength="10"/>
                                                                <label>Number Of Persons<span class="red">*</span> </label>
                                                                <span></span>
                                                            </div>
                                                        </div>

                                                        <div class="col-sm-3">
                                                            <div class="styled-input">
                                                                <input type="text" id="inspectionReportNo" name="inspectionReportNo" class="onlyNumber" />
                                                                <label>Factory Inspection Report no.</label>
                                                                <span></span>
                                                            </div>
                                                        </div>
                                                    </div>

                                                    <div class="form-group partnerOrgTabs">
                                                        <div class="col-sm-12">
                                                            <div class="styled-input">
                                                                <textarea type="text" id="address1" name="location.address1" class="requiredField" maxlength="255"></textarea>
                                                                <label>Registered Office Address<span class="red">*</span></label>
                                                                <span></span></div>
                                                        </div>
                                                    </div>

                                                    <div class="form-group partnerOrgTabs">
                                                        <div class="col-sm-3">
                                                            <div class="styled-input">
                                                                <input type="text" id="city" name="location.city" class="requiredField requiredalphabeticsWithSpace" maxlength="20"/>
                                                                <label>City<span class="red">*</span></label>
                                                                <span></span>
                                                            </div>
                                                        </div>

                                                        <div class="col-sm-3">
                                                            <div class="styled-input">
                                                                <select id="district" name="location.district.districtId" class=" dropDown" /></select>
                                                                <label>District<span class="red">*</span></label>
                                                                <span></span>
                                                            </div>
                                                        </div>
                                                        <div class="col-sm-3">
                                                            <div class="styled-input">
                                                                <select id="region" name="location.region.regionId" class=" dropDown" /></select>
                                                                <label>State<span class="red">*</span></label>
                                                                <span></span>
                                                            </div>
                                                        </div>
                                                        <div class="col-sm-3">
                                                            <div class="styled-input">
                                                                <select id="country" name="location.country.countryId" class=" dropDown" /></select>
                                                                <label>Country<span class="red">*</span></label>
                                                                <span></span>
                                                            </div>
                                                        </div>

                                                    </div>

                                                    <div class="form-group">
                                                        <div class="col-sm-3">
                                                            <div class="styled-input partnerOrgTabs">
                                                             <input type="text" id="postal" name="location.postal" class="requiredField pincode" maxlength="6"/>
                                                             <label>Pincode<span class="red">*</span></label>
                                                                <!-- <select id="postal" name="location.postal" required /></select>
                                                                <label>Pincode<span class="red">*</span></label>
                                                                <span></span> -->
                                                            </div>
                                                        </div>
                                                    <div class="col-sm-3" id="factoryLicenseDivId">
														<div class="styled-input partnerOrgTabs">
															<input type="text" id="licenceNo" name="licenceNo" class="requiredField"/> <label>Factory
																License Number<span class="red">*</span></label> 
														</div>
													    </div>
													    <div class="col-sm-3 partnerOrgTabs" id="factoryLicenseDateDivId">
														<label>Factory License Validity Date<!-- <span
															class="red">*</span> --></label>
														<div class="input-group date futureDate" data-provide="datepicker">
															<input type="text" class="form-control"
																id="licenceValidityDate" name="licenceValidityDate">
															<div class="input-group-addon">
																<span class="glyphicon glyphicon-th"></span>
															</div>
														</div>
													    </div>
													     <div class="col-sm-3" >
														<label id="factoryLicenseCopyLabel">Attach Factory License Copy<span class="red">*</span></label> 
														
														<div class="input-group partnerOrgTabs">               
                                                          <input type="file"  id="licenceFileId" data-id="licenceCopy" data-name="licenceFileName" data-anchor="a_licenceCopy" class="form-control uploadFile requiredFile">
                                                          <input type="hidden" id="licenceCopy" name="licenceCopy.attachmentId" class="form-control" /> 
												                <span class="input-group-btn">
												                    <button class="btn btn-default licenceCopy fileDeleteBtn" onclick="return submitWithThreeParam('deleteAttachmentById','PartnerOrg','licenceCopy','licenceCopy','licenceAttachmentDeleteResp');" disabled="disabled"><i class="fa fa-times"></i></button>
												                </span>
           	                                            </div>
           	                                           <!--  <input type="hidden" id="licenceFileName" name="licenceCopy.fileName" class="form-control" /> -->
           	                                            <a class="col-sm-12 filelabel" data-url="<%=request.getContextPath()%>/download" id="a_licenceCopy"></a>
													</div>
														
                                                    </div>
                                                    
                                                     <div class="form-group">
                                                     <div class="col-sm-3">
                                                            <div class="styled-input partnerOrgTabs">
                                                                <select id="IsFactoryInspection" name="isFactoryInspected" class="dropDown" onchange="onchangeoffactoryInspectiondone();">
                                                                <option value="">Select</option>
                                                                <option value="Y">Yes</option>
																<option value="N">No</option>
                                                                </select>
                                                                <label>Is Factory Inspection Done<span class="red">*</span></label>
                                                                <span></span>
                                                            </div>
                                                        </div>
                                                        <div class="col-sm-3 partnerOrgTabs factoryInspectionDone" style="display: none;">
														<label>Factory Inspection Approval Date<span
															class="red">*</span></label>
														<div class="input-group date" data-provide="datepicker">
															<input type="text" class="form-control" id="inspectionDate" name="inspectionDate">
															<div class="input-group-addon">
																<span class="glyphicon glyphicon-th"></span>
															</div>
														</div>
													    </div>
													    <div class="col-sm-3 factoryInspectionDone" style="display: none;">
														<label>Attach Inspection Report</label> <span class="red">*</span>
														<div class="input-group partnerOrgTabs">               
                                                          <input type="file"  id="inspectionId" data-id="inspectionCopy" data-name="inspectionName" data-anchor="a_inspectionCopy" class="form-control uploadFile">
                                                          <input type="hidden" id="inspectionCopy" name="inspectionReportCopy.attachmentId" class="form-control" /> 
												                <span class="input-group-btn">
												                    <button class="btn btn-default inspectionCopy fileDeleteBtn" onclick="return submitWithThreeParam('deleteAttachmentById','PartnerOrg','inspectionReportCopy','inspectionCopy','inspectionAttachmentDeleteResp');" disabled="disabled"><i class="fa fa-times"></i></button>
												                </span>
           	                                            </div>
           	                                           <!--  <input type="hidden" id="licenceFileName" name="licenceCopy.fileName" class="form-control" /> -->
           	                                            <a class="col-sm-12 filelabel" data-url="<%=request.getContextPath()%>/download" id="a_inspectionCopy"></a>
													</div>
                                                     <div class="col-sm-3 factoryInspectionDone" style="display: none;">
														<label>Attach Machinery List Copy of Factory <span class="red">*</span></label> 
														<div class="input-group partnerOrgTabs">       
                                                         <input type="file" id="machinaryFileId" data-id="machinaryListCopy" data-name="machinaryFileName" data-anchor="a_machinaryCopy" class="form-control uploadFile">
											             <input type="hidden" id="machinaryListCopy" name="machinaryListCopy.attachmentId" class="form-control" />
											                <span class="input-group-btn">
											                    <button class="btn btn-default machinaryListCopy fileDeleteBtn" onclick="return submitWithThreeParam('deleteAttachmentById','PartnerOrg','machinaryListCopy','machinaryListCopy','machinaryAttachmentDeleteResp');" disabled="disabled"><i class="fa fa-times"></i></button>
											                </span>
                                                            </div>
													  <!--   <input type="hidden" id="machinaryFileName" name="machinaryListCopy.fileName" class="form-control" /> -->
           	                                            <a class="col-sm-12 filelabel" data-url="<%=request.getContextPath()%>/download" id="a_machinaryCopy"></a>
													</div>
													<div class="col-sm-3 factoryInspectionDone" style="display: none;">
														<label>Attach List of Staff Skilled & Unskilled<span class="red">*</span></label> 
														<div class="input-group partnerOrgTabs">               
                                                          <input type="file"  id="listOfStaff" data-id="staffListCopy" data-name="staffFileName" data-anchor="a_staffCopy" class="form-control uploadFile">
                                                          <input type="hidden" id="staffListCopy" name="staffListCopy.attachmentId" class="form-control" /> 
												                <span class="input-group-btn">
												                    <button class="btn btn-default staffListCopy fileDeleteBtn" onclick="return submitWithThreeParam('deleteAttachmentById','PartnerOrg','staffListCopy','staffListCopy','staffAttachmentDeleteResp');" disabled="disabled"><i class="fa fa-times"></i></button>
												                </span>
           	                                            </div>
           	                                           <!--  <input type="hidden" id="licenceFileName" name="licenceCopy.fileName" class="form-control" /> -->
           	                                            <a class="col-sm-12 filelabel" data-url="<%=request.getContextPath()%>/download" id="a_staffCopy"></a>
													</div>
													  <div class="col-sm-3">
                                                            <div class="styled-input partnerOrgTabs">
                                                                <select id="orgPaymmentId" name="paymentDetail.paymentDetailId" class="dropDown" /></select>
                                                                <label>Payment No.<span class="red">*</span></label>
                                                                <span></span>
                                                            </div>
                                                       </div>
                                                    <input type="hidden" id="licenseType"
																name="licenseType" value="STL" />
                                                    </div>
                                                  <div class="clearfix"></div>
                                               <div class="form-group partnerOrgEEApproveDiv" >
                                                    <label class="col-sm-2"><b>Add EE Comment:</b> </label>
														<div class="col-sm-4">
															<textarea class="form-control partnerOrgRemark changeComment" name="eeComment" id="eeRemark" maxlength="255" ></textarea>
														</div>
														<div class="col-sm-6">
															<label><b>Select Status:</b> </label> 
															<input type="radio"  value="Y" id="eeApproveBtn"  name="isEEApproved" class="partnerOrgStatusBtn changeButton" checked />Approve 
															<!-- <input type="radio" name="isApproved" value="N" id="rejectBtn" class="statusBtn changeButton" /> Reject -->
														    <input type="radio"  value="C" id="eeClarification"  name="isEEApproved" class="partnerOrgStatusBtn changeButton"  /> Clarification
														</div>
											  </div>  
											     <div class="form-group partnerOrgCEApproveDiv" >
                                                    <label class="col-sm-2"><b>Add CE Comment:</b> </label>
														<div class="col-sm-4">
															<textarea class="form-control partnerOrgRemark changeComment" name="ceComment" id="ceRemark" maxlength="255" ></textarea>
														</div>
														<div class="col-sm-6">
															<label><b>Select Status:</b> </label> 
															<input type="radio"  value="Y" id="ceApproveBtn" name="isCEApproved"  class="partnerOrgStatusBtn changeButton" checked />Approve 
															<!-- <input type="radio" name="isApproved" value="N" id="rejectBtn" class="statusBtn changeButton" /> Reject -->
														    <input type="radio"  value="C" name="isCEApproved" class="partnerOrgStatusBtn changeButton"  id="ceClarification" /> Clarification
														</div>
											  </div>
                                            <div class="col-sm-12 text-center positionABS" style="margin-bottom:10px;">
		                                        <button class="btn-all btn btn-info k-tabstrip-prev  btnPrevious pull-left tab-li-prev">Previous</button>
		                                        <span id="factoryFormDivId" class="">
		                                        <button class="btn btn-info save partnerOrgActionBtn" onclick="return submitIt('partnerOrgForm','saveOrgDetails','partnerOrgResp');">Save</button>
		                                        <button class="btn btn-info cancel partnerOrgActionBtn" id="cancelFactoryDetailsBtnId" >Reset</button>
		                                        </span>
		                                        <button class="btn-all btn btn-info k-tabstrip-next btnNext pull-right tab-li-next">Next</button>
		                                    </div>
                                                   
                                          </sf:form>
                                                    
                                       </div>
                                                <!--tab inside tab fields 1  -->		
                                                
                                                <!-- tab inside tab fields 2 -->
										<div>
											<sf:form id="partnerOrgLicenseForm"
												modelAttribute="partnerOrgLicense" method="POST">
												<div class="form-group">
													<div class="col-sm-12">
														<h4 class="col-xs-6 nopadding">
															<b>Factory License Details</b>
														</h4>
														<hr>
													</div>
												</div>
												<div class="form-group">
													<div class="col-sm-3" style="display: none;">
														<div class="styled-input">
															<input type="hidden" id="bPartnerId" required /> <input
																type="hidden" class="partnerOrgId"
																name="partnerOrg.partnerOrgId" id="partnerOrgId"
																required /> <input type="hidden"
																id="partnerOrgLicenceId" name="partnerOrgLicenceId"
																required /> <input type="hidden" id="licenseType"
																name="licenseType" value="STL" />

														</div>
													</div>
													<input type="checkbox" style="display: none;" id="isActive"
														name="isActive" value="Y" checked="checked" />
													<div class="col-sm-3">
														<div class="styled-input">
															<input type="text" id="licenceNo" name="licenceNo" /> <label>Factory
																License Number</label> <span></span>
														</div>
													</div>

													<div class="col-sm-3">
														<!-- <div class="styled-input">
                                                                <input type="text" id="licenceValidityDate" name="licenceValidityDate" />
                                                                <label>Factory License Validity Date</label>
                                                                <span></span>
                                                            </div> -->
														<label>Factory License Validity Date<span
															class="red">*</span></label>
														<div class="input-group date" data-provide="datepicker">
															<input type="text" class="form-control"
																id="licenceValidityDate" name="licenceValidityDate">
															<div class="input-group-addon">
																<span class="glyphicon glyphicon-th"></span>
															</div>
														</div>
													</div>

													<div class="col-sm-3">
														<label>Attach Factory License Copy</label> <input
															type="file" id="licenceCopy" name="licenceCopy"
															class="form-control" /> <span></span>
													</div>

													<div class="col-sm-3">
														<label>Attach Machinery List Copy of Factory<span
															class="red">*</span></label> <input type="file"
															id="machinaryListCopy" name="machinaryListCopy"
															class="form-control" required /> <span></span>
													</div>
												</div>
												<div class="col-sm-12 text-center positionABS"
													style="margin-bottom: 10px;">
													<button
														class="btn-all btn btn-info k-tabstrip-prev btnPrevious pull-left">Previous</button>
													<button class="btn btn-info"
														onclick="return submitIt('partnerOrgLicenseForm','saveOrgLicense','partnerOrgLicenseResp');">Save</button>
													<button
														class="btn-all btn btn-info k-tabstrip-next btnNext pull-right">Next</button>
												</div>
											</sf:form>
										</div>
										<!-- tab inside tab fields 2 -->
                                                
                                                <!-- tab inside tab fields 3 -->
                                                <div>
                                                 <sf:form id="partnerOrgUserForm" modelAttribute="partnerOrgUser" method="POST">
                                                <div class="form-group partnerOrgTabs">
                                        <div class="col-sm-12">
                                        <h4 class="col-xs-6 nopadding"><b>Factory Contact Person Details </b></h4>
                                          <div class="col-xs-6 text-right nopadding" style="margin-top:10px;">
                                                    <button class="btn btn-default" id="addFactoryUserBtnId"><span class="glyphicon glyphicon-plus-sign"></span>Add</button>
                                                    <!-- <button class="btn btn-default" id="editFactoryUserBtnId"><span class="glyphicon glyphicon-pencil"></span>Edit User</button> -->
                                                    <button class="btn btn-default partnerOrgActionBtn" id="deleteFactoryUserBtnId" onclick="return submitWithParam('deleteOrgUser','partnerOrgUserId','partnerOrgUserDelResp');"><span class="glyphicon glyphicon-trash"></span>Delete</button></div>
                                      
                                        <hr>
                                        </div>
                                        </div>
                                                       
                                                      <div class="form-group partnerOrgTabs">
                                                       <div class="col-sm-3" style="display: none;">
                                                            <div class="styled-input">
                                                            <input type="hidden" id="bPartnerId" class="bPartnerId" name="partner.bPartnerId" required />
                                                            <input type="hidden" id="locationId" name="userDetail.location.locationId" required />
                                                            <input type="hidden" class="partnerOrgId" id="partnerOrgId"  name="partnerOrg.partnerOrgId" required />
                                                            <input type="hidden" id="partnerOrgUserId" name="partnerOrgUserId"  required />
                                                            <input type="hidden" id="userDetailsId" name="userDetail.userDetailsId"  required />
                                                            <input type="hidden" id="isActive" name="isActive"  value="Y" required />
                                                            <input type="hidden" id="isActive" name="userDetail.isActive" value="Y" required />
                                                            <input type="hidden" id="isActive" name="userDetail.location.isActive" value="Y" required />
                                                           </div>
                                                         </div>
                                                         
                                                         <div class="col-sm-3">
                                                         <button class="btn btn-default" id="sameAddress"><span class="glyphicon glyphicon-plus-sign"></span>Copy Address</button>
															<!-- <input type="checkbox" id="sameAddress" value="" />  
															<label>Copy Address</label> <span></span>-->
														</div>
														</div>
                                                        <!-- <div class="col-sm-3">
                                                            <div class="styled-input">
                                                                <input type="text" id="title" name="userDetail.title" class="requiredField requiredalphabeticsWithSpace" />
                                                                <label>Title [Mr.,Miss.,Mrs.,Dr.]<span class="red">*</span></label>
                                                                <span></span>
                                                            </div>
                                                        </div> -->
                                                        <div class="form-group partnerOrgTabs">
														<div class="col-sm-3">
															<div class="styled-input">
																<select id="title" name="userDetail.title" class="dropDown"/></select> 
																<label>Title [Mr.,Miss.,Mrs.]<span class="red">*</span></label>
																<span></span>
															</div>
														</div>
														
                                                        <div class="col-sm-3">
                                                            <div class="styled-input">
                                                                <input type="text" id="firstName" name="userDetail.firstName" class="requiredField requiredalphabeticsWithSpace" maxlength="20" />
                                                                <label>First Name<span class="red">*</span></label>
                                                                <span></span>
                                                            </div>
                                                        </div>

                                                        <div class="col-sm-3">
                                                            <div class="styled-input">
                                                                <input type="text" id="middleName" name="userDetail.middleName" class="requiredalphabeticsWithSpace" maxlength="20"/>
                                                                <label>Middle Name</label>
                                                                <span></span></div>
                                                        </div>

                                                        <div class="col-sm-3">
                                                            <div class="styled-input">
                                                                <input type="text" id="lastName" name="userDetail.lastName" class="requiredField requiredalphabeticsWithSpace" maxlength="20"/>
                                                                <label>Last Name<span class="red">*</span></label>
                                                                <span></span>
                                                            </div>
                                                        </div>
                                                    </div>
													<div class="form-group partnerOrgTabs">
													<div class="col-sm-3">
                                                            <div class="styled-input">
                                                                <input type="text" id="email" name="userDetail.email" class="requiredField emailaddress" />
                                                                <label>Email Id<span class="red">*</span></label>
                                                                <span></span>
                                                            </div>
                                                        </div>

                                                        <div class="col-sm-3">
                                                            <div class="styled-input">
                                                             <select id="designation" name="userDetail.designation.designationId" class=" dropDown" /></select>
                                                                <label>Designation<span class="red">*</span></label>
                                                               <span></span>
                                                            </div>
                                                        </div>
                                                        <div class="col-sm-3">
                                                            <div class="styled-input">
                                                                <input type="text" id="telephone1" name="userDetail.telephone1" title="Enter 10 digit number" class=" telephone" maxlength="15"/>
                                                                <label>Landline 1<span class="red"></span></label>
                                                                <span></span>
                                                            </div>
                                                        </div> 

                                                        <div class="col-sm-3">
                                                            <div class="styled-input">
                                                                <input type="text" id="telephone2" name="userDetail.telephone2" title="Enter 10 digit number" class="telephone" maxlength="15"/>
                                                                <label>Landline 2</label>
                                                                <span></span>
                                                            </div>
                                                        </div>
														<div class="clearfix"></div>
                                                        <div class="col-sm-3">
                                                            <div class="styled-input">
                                                                <input type="text" id="fax1" name="userDetail.fax1" title="Enter 10 digit number" class="onlyNumber" maxlength="15"/>
                                                                <label>Fax 1</label>
                                                                <span></span>
                                                            </div>
                                                        </div>

                                                        <div class="col-sm-3">
                                                            <div class="styled-input">
                                                                <input type="text" id="fax2" name="userDetail.fax2" title="Enter 10 digit number" class="onlyNumber" maxlength="15"/>
                                                                <label>Fax 2</label>
                                                                <span></span>
                                                            </div>
                                                        </div>
                                                    </div>

                                                    <div class="form-group ">
                                                        <div class="col-sm-3">
                                                            <div class="styled-input partnerOrgTabs">
                                                                <input type="text" id="mobileNo" name="userDetail.mobileNo" title="Enter 10 digit number" class="requiredField mobile" maxlength="10"/>
                                                                <label>Mobile Number<span class="red">*</span></label>
                                                                <span></span>
                                                            </div>
                                                        </div>
                                                     </div>
                                                     <div class="clearfix"></div>
                                                    <div class="clearfix"></div>

                                                    <div class="form-group partnerOrgTabs">
                                                        <div class="col-sm-12">
                                                            <div class="styled-input">
                                                                <textarea type="text" id="address1" name="userDetail.location.address1" class="requiredField" maxlength="255"></textarea>
                                                                <label>Registered Office Address<span class="red">*</span></label>
                                                                <span></span>
                                                            </div>
                                                        </div>
                                                    </div>

                                                    <div class="clearfix"></div>

                                                    <div class="form-group partnerOrgTabss">
                                                        <div class="col-sm-3">
                                                            <div class="styled-input partnerOrgTabs">
                                                                <input type="text" id="city" name="userDetail.location.city" class="requiredField requiredalphabeticsWithSpace"  maxlength="20"/>
                                                                <label>City<span class="red">*</span></label>
                                                                <span></span>
                                                            </div>
                                                        </div>

                                                        <div class="col-sm-3">
                                                            <div class="styled-input  partnerOrgTabs">
                                                                <select id="district" name="userDetail.location.district.districtId" class="requiredField dropDown" /></select>
                                                                <label>District<span class="red">*</span></label>
                                                                <span></span>
                                                            </div>
                                                        </div>

                                                        <div class="col-sm-3">
                                                            <div class="styled-input partnerOrgTabs">
                                                                <select id="country" name="userDetail.location.country.countryId" class="requiredField dropDown" /></select>
                                                                <label>Country<span class="red">*</span></label>
                                                                <span></span>
                                                            </div>
                                                        </div>

                                                        <div class="col-sm-3">
                                                            <div class="styled-input partnerOrgTabs">
                                                                <select id="region" name="userDetail.location.region.regionId" class="requiredField dropDown" /></select>
                                                                <label>State<span class="red">*</span></label>
                                                            </div>
                                                        </div>
                                                    </div>

                                                    <div class="clearfix"></div>

                                                    <div class="form-group partnerTabs">
                                                        <div class="col-sm-3">
                                                            <div class="styled-input">
                                                                <input type="text" id="postal" name="userDetail.location.postal" class="requiredField pincode" maxlength="6" />
                                                                <label>Pincode<span class="red">*</span></label>
                                                                <span></span>
                                                            </div>
                                                        </div>

                                                        

                                                        <!-- <div class="col-sm-3">
                                                            <div class="styled-input">
                                                                <input type="text" id="" name="" />
                                                                <label>Department</label>
                                                                <span></span>
                                                            </div>
                                                        </div> -->
                                                    </div>

                                                    <div class="clearfix"></div>

                                                    
                                               <div class="form-group partnerOrgEEApproveDiv" >
                                                    <label class="col-sm-2"><b>Add EE Comment:</b> </label>
														<div class="col-sm-4">
															<textarea class="form-control partnerOrgRemark changeComment" name="eeComment" id="eeRemark" maxlength="255" ></textarea>
														</div>
														<div class="col-sm-6">
															<label><b>Select Status:</b> </label> 
															<input type="radio"  value="Y" id="eeApproveBtn"  name="isEEApproved" class="partnerOrgStatusBtn changeButton" checked />Approve 
															<!-- <input type="radio" name="isApproved" value="N" id="rejectBtn" class="statusBtn changeButton" /> Reject -->
														    <input type="radio"  value="C" id="eeClarification"  name="isEEApproved" class="partnerOrgStatusBtn changeButton"  /> Clarification
														</div>
											  </div>  
											     <div class="form-group partnerOrgCEApproveDiv" >
                                                    <label class="col-sm-2"><b>Add CE Comment:</b> </label>
														<div class="col-sm-4">
															<textarea class="form-control partnerOrgRemark changeComment" name="ceComment" id="ceRemark" maxlength="255" ></textarea>
														</div>
														<div class="col-sm-6">
															<label><b>Select Status:</b> </label> 
															<input type="radio"  value="Y" id="ceApproveBtn" name="isCEApproved"  class="partnerOrgStatusBtn changeButton" checked />Approve 
															<!-- <input type="radio" name="isApproved" value="N" id="rejectBtn" class="statusBtn changeButton" /> Reject -->
														    <input type="radio"  value="C" name="isCEApproved" class="partnerOrgStatusBtn changeButton"  id="ceClarification" /> Clarification
														</div>
											  </div>
                                                <div class="col-sm-12 text-center positionABS" style="margin-bottom:10px;">
		                                        <button class="btn-all btn btn-info k-tabstrip-prev  btnPrevious pull-left tab-li-prev">Previous</button>
		                                        <span id="factoryUserFormDivId" class="">
		                                        <button class="btn btn-info save partnerOrgActionBtn" onclick="return submitIt('partnerOrgUserForm','saveOrgUser','partnerOrgUserResp');">Save</button>
		                                        <button class="btn btn-info cancel partnerOrgActionBtn" id="cancelFactoryContactBtnId" >Reset</button>
		                                        </span>
		                                        <button class="btn-all btn btn-info k-tabstrip-next  btnNext pull-right tab-li-next">Next</button>
		                                        </div>
		                                        
                                                    </sf:form>
                                                </div>
                                                <!-- tab inside tab fields 3 -->
                                                
                                                <!-- tab inside tab fields 4 -->
                                                 <div>
                                                <sf:form id="partnerOrgExperienceForm" modelAttribute="partnerOrgExperience" method="POST">
                                                 <div class="form-group">
                                        <div class="col-sm-12">
                                        <h4 ><b>Experience Details</b></h4>
                                        <hr>
                                        </div>
                                        </div>
                                               <div class="col-sm-12">
                                                    <label>Experience in Manufacturing: </label>

                                                    <div class="form-group partnerOrgTabs">
                                                     <div class="col-sm-3" style="display: none;">
                                                            <div class="styled-input">
                                                            <input type="hidden" id="bPartnerId" class="bPartnerId" name="partner.bPartnerId" required />
                                                            <input type="hidden" class="partnerOrgId" id="partnerOrgId"  name="partnerOrg.partnerOrgId" required />
                                                            <input type="hidden" id="partnerOrgExperienceId" name="partnerOrgExperienceId"  required />
                                                             <input type="hidden" id="isActive"  name="isActive" required />
                                                           </div>
                                                     </div>
                                                        <div class="col-sm-3">
                                                            <div class="styled-input">
                                                                <!-- <select id="expManufacturingYear" name="expManufacturingYear" /></select> -->
                                                                <input type="text" id="expManufacturingYear" name="expManufacturingYear" class="requiredField onlyNumber checkYear" maxlength="3"/>
                                                                <label>Years<span class="red">*</span></label>
                                                                <span></span>
                                                            </div>
                                                        </div>

                                                        <div class="col-sm-3">
                                                            <div class="styled-input">
                                                                <!-- <select id="expManufacturingMonths" name="expManufacturingMonths" /></select> -->
                                                                <input type="text" id="expManufacturingMonths" name="expManufacturingMonths"  class="requiredField onlyNumber checkMonth" maxlength="2"/>
                                                                <label>Months<span class="red">*</span></label>
                                                                <span></span>
                                                            </div>
                                                        </div>
                                                    </div>

                                                    <label>Experience in Design: </label>

                                                    <div class="form-group partnerOrgTabs">
                                                        <div class="col-sm-3">
                                                            <div class="styled-input">
                                                                <!-- <select id="expDesignYear" name="expDesignYear" /></select> -->
                                                                <input type="text" id="expDesignYear" name="expDesignYear" class="requiredField onlyNumber checkYear" maxlength="3"/>
                                                                <label>Years<span class="red">*</span></label>
                                                                <span></span>
                                                            </div>
                                                        </div>

                                                        <div class="col-sm-3">
                                                            <div class="styled-input">
                                                                <!-- <select id="expDesignMonths" name="expDesignMonths" /></select> -->
                                                                <input type="text" id="expDesignMonths" name="expDesignMonths" class="requiredField onlyNumber checkMonth"  maxlength="2"/>
                                                                <label>Months<span class="red">*</span></label>
                                                                <span></span>
                                                            </div>
                                                        </div>
                                                    </div>

                                                    <label>Experience in Testing: </label>

                                                    <div class="form-group partnerOrgTabs">
                                                        <div class="col-sm-3">
                                                            <div class="styled-input">
                                                                <!-- <select id="expTestingYear" name="expTestingYear" /></select> -->
                                                                <input type="text" id="expTestingYear" name="expTestingYear" class="requiredField onlyNumber checkYear" maxlength="3"/>
                                                                <label>Years<span class="red">*</span></label>
                                                                <span></span>
                                                            </div>
                                                        </div>

                                                        <div class="col-sm-3">
                                                            <div class="styled-input">
                                                                <!-- <select id="expTestingMonths" name="expTestingMonths" /></select> -->
                                                                 <input type="text" id="expTestingMonths" name="expTestingMonths" class="requiredField onlyNumber checkMonth" maxlength="2"/>
                                                                <label>Months<span class="red">*</span></label>
                                                                <span></span>
                                                            </div>
                                                        </div>
                                                    </div>

                                                    <label>Experience in Supplying: </label>

                                                    <div class="form-group partnerOrgTabs">
                                                        <div class="col-sm-3">
                                                            <div class="styled-input">
                                                                <!-- <select id="expSupplyingYear" name="expSupplyingYear" /></select> -->
                                                                <input type="text" id="expSupplyingYear" name="expSupplyingYear" class="requiredField onlyNumber checkYear" maxlength="3"/>
                                                                <label>Years<span class="red">*</span></label>
                                                                <span></span>
                                                            </div>
                                                        </div>

                                                        <div class="col-sm-3">
                                                            <div class="styled-input">
                                                               <!--  <select id="expSupplyingMonths" name="expSupplyingMonths" /></select> -->
                                                               <input type="text" id="expSupplyingMonths" name="expSupplyingMonths" class="requiredField onlyNumber checkMonth" maxlength="2"/>
                                                                <label>Months<span class="red">*</span></label>
                                                                <span></span>
                                                            </div>
                                                        </div>
                                                    </div>
                                                     <div class="clearfix"></div>
                                               <div class="form-group partnerOrgEEApproveDiv" >
                                                    <label class="col-sm-2"><b>Add EE Comment:</b> </label>
														<div class="col-sm-4">
															<textarea class="form-control partnerOrgRemark changeComment" name="eeComment" id="eeRemark" maxlength="255" ></textarea>
														</div>
														<div class="col-sm-6">
															<label><b>Select Status:</b> </label> 
															<input type="radio"  value="Y" id="eeApproveBtn"  name="isEEApproved" class="partnerOrgStatusBtn changeButton" checked />Approve 
															<!-- <input type="radio" name="isApproved" value="N" id="rejectBtn" class="statusBtn changeButton" /> Reject -->
														    <input type="radio"  value="C" id="eeClarification"  name="isEEApproved" class="partnerOrgStatusBtn changeButton"  /> Clarification
														</div>
											  </div>  
											     <div class="form-group partnerOrgCEApproveDiv" >
                                                    <label class="col-sm-2"><b>Add CE Comment:</b> </label>
														<div class="col-sm-4">
															<textarea class="form-control partnerOrgRemark changeComment" name="ceComment" id="ceRemark" maxlength="255" ></textarea>
														</div>
														<div class="col-sm-6">
															<label><b>Select Status:</b> </label> 
															<input type="radio"  value="Y" id="ceApproveBtn" name="isCEApproved"  class="partnerOrgStatusBtn changeButton" checked />Approve 
															<!-- <input type="radio" name="isApproved" value="N" id="rejectBtn" class="statusBtn changeButton" /> Reject -->
														    <input type="radio"  value="C" name="isCEApproved" class="partnerOrgStatusBtn changeButton"  id="ceClarification" /> Clarification
														</div>
											  </div>
                                                </div>
                                                <div class="col-sm-12 text-center positionABS" style="margin-bottom:10px;">
		                                        <button class="btn-all btn btn-info k-tabstrip-prev btnPrevious pull-left tab-li-prev">Previous</button>
		                                        <span id="factoryUserFormDivId" class="">
		                                        <button class="btn btn-info save partnerOrgActionBtn" onclick="return submitIt('partnerOrgExperienceForm','saveOrgExperience','partnerOrgExperienceResp');">Save</button>
		                                        <button class="btn btn-info cancel partnerOrgActionBtn" id="cancelExperienceBtnId" >Reset</button>
		                                        </span>
		                                        <button class="btn-all btn btn-info k-tabstrip-next btnNext pull-right tab-li-next">Next</button>
		                                       </div>
                                                </sf:form>
                                                </div>
                                                <!-- tab inside tab fields 4 -->
												
												 

                                              <!--tab inside tab fields 6  -->

                                                    <!--tab inside tab fields 6  -->
												<!--  factory registration tab open-->
												<div>

											<sf:form id="partnerOrgUdyogForm" modelAttribute="partnerOrgRegistration" method="POST">


												<div class="form-group partnerOrgTabs">
													<div class="col-sm-12">
														<h4 class="col-xs-6 nopadding">
															<b>Factory Registration Details</b>
														</h4>
														<div class="col-xs-6 text-right nopadding"
															style="margin-top: 10px;">
															<button class="btn btn-default" id="addFactoryRegBtnId">
																<span class="glyphicon glyphicon-plus-sign"></span>Add
															</button>
															<!-- <button class="btn btn-default" id="editFactoryRegBtnId">
																<span class="glyphicon glyphicon-pencil"></span>Edit
															</button> -->
															<button class="btn btn-default"
																id="deleteFactoryRegBtnId">
																<span class="glyphicon glyphicon-trash"></span>Delete
															</button>
														</div>
														<hr>
													</div>
												</div>
												

													<div class="form-group partnerOrgTabs">
														<div class="col-sm-3" style="display: none;">
															<div class="styled-input">
																<input type="hidden" id="bPartnerId" class="bPartnerId" name="partner.bPartnerId" required /> <input
																	type="hidden" class="partnerOrgId"
																	name="partnerOrg.partnerOrgId" id="partnerOrgId"
																	required /> <input type="hidden"
																	id="udyogRegistrationId"
																	name="partnerOrgRegistrationId" required /> <input
																	type="checkbox" id="isActive" name="isActive"
																	style="display: none;" checked="checked" value="Y" />
															</div>
														</div>
														<div class="col-sm-3">
															<input type="checkbox" id="isApplicable"
																name="isApplicable" /> <label>Not Applicable</label> <span></span>
														</div>
													</div>
													<div class="showField">
														<div class="form-group partnerOrgTabs">
															<div class="col-sm-4">
																<div class="styled-input">
																	<select id="registrationType" name="registrationType"
																		class="requiredField dropDown" onchange="onChangeRegistrationType();" /></select> <label>Registration Type<span
																		class="red">*</span></label> <span></span>
																</div>
															</div>

															<div class="col-sm-4">
																<div class="styled-input">
																	<input type="text" id="registrationAuthority"
																		name="registrationAuthority" class="requiredField requiredalphabeticsWithSpace"/>
																		 <label>
																		Registering Authority<span class="red">*</span>
																	</label> <span></span>
																</div>
															</div>

															<div class="col-sm-4">
																<div class="styled-input">
																	<input type="text" id="registrationNo"
																		name="registrationNo" class="requiredField onlyNumber" /> <label>
																		Registration Number<span class="red">*</span>
																	</label> <span></span>
																</div>
															</div>

														</div>

														<div class="form-group partnerOrgTabs">
															<div class="col-sm-4">
																<!-- <div class="styled-input">
                                                                <input type="text" id="registrationIssueDate" name="issueDate" required />
                                                                <label>Issue Date of NSIC Registration<span class="red">*</span></label>
                                                                <span></span></div> -->
																<label>Issue Date of Registration<!-- <span
																	class="red">*</span> --></label>
																<div class="input-group date" data-provide="datepicker">
																	<input type="text" class="form-control"
																		id="registrationIssueDate" name="issueDate">
																	<div class="input-group-addon">
																		<span class="glyphicon glyphicon-th"></span>
																	</div>
																</div>
															</div>
															<div class="col-sm-4">
																<div class="styled-input">
																	<select id="validityType" name="validityType" class="requiredField dropDown" onchange="validityOnChange($(this).val())"/></select> <label>Validity Type<span
																		class="red">*</span></label> <span></span>
																</div>
															</div>
															<div class="col-sm-4" id="validityDiv">
																<!--  <div class="styled-input">
                                                                <input type="text" id="validityDate" name="validityDate" required />
                                                                <label>Validity Date of NSIC Registration<span class="red">*</span></label>
                                                                <span></span></div>
                                                        </div> -->
																<label>Validity Date of Registration <span
																	class="red">*</span> </label>
																<div class="input-group date futureDate" data-provide="datepicker">
																	<input type="text" class="form-control"
																		id="validityDate" name="validityDate">
																	<div class="input-group-addon">
																		<span class="glyphicon glyphicon-th"></span>
																	</div>
																</div>
															</div>
															

														</div>
														<div class="form-group partnerOrgTabs">
															<div class="col-sm-4 MSE">
																<div class="styled-input">
																	<input type="text" id="plantInvestment"
																		name="plantInvestment" class="onlyNumber requiredField" title="Details of Investment in plant & machinery/equipments in Rs Lakhs.
																		" /> <label>
																		Plant Investment (In Rs. Lakhs)<span class="red">*</span>
																	</label> <span></span>
																</div>
															</div>
															<div class="col-sm-4 MSE">
																<div class="styled-input">
																	<select id="natureCode" name="natureCode"
																		class="requiredField dropDown" /></select> <label>Nature of Activity<span
																		class="red">*</span></label> <span></span>
																</div>
															</div>
															<div class="col-sm-4 MSE">
																<div class="styled-input">
																	<select id="categoryCode" name="categoryCode"
																		class="requiredField dropDown" /></select> <label>Category of Enterpise<span
																		class="red">*</span></label> <span></span>
																</div>
															</div>
															<div class="col-sm-4 NSIC">
																<div class="styled-input">
																	<input type="text" id="monetaryLimit"
																		name="monetaryLimit" class="onlyNumber requiredField" title="Monetary Limit in Rs Lakhs.
																		" /> <label>
																		Monetary Limit (In Rs. Lakhs)<span class="red">*</span>
																	</label> <span></span>
																</div>
															</div>
														</div>
														<div class="form-group ">
															<div class="col-sm-4">

																<label>Date of Commencement of Commercial
																	Production<!-- <span class="red">*</span> -->
																</label>
																<div class="input-group date" data-provide="datepicker">
																	<input type="text" class="form-control"
																		id="productCommencement" name="productCommencement">
																	<div class="input-group-addon">
																		<span class="glyphicon glyphicon-th"></span>
																	</div>
																</div>
															</div>
															<div class="col-sm-4">
																<label>Attach Registration Copy 
																<span class="red">*</span></label>
															<div class="input-group partnerOrgTabs">		
															 <input type="file" id="registrationFileId" data-id="registrationCopy" data-name="registrationFileName" data-anchor="a_registrationCopy"
															  class="form-control uploadFile requiredFile" required />
															 <input type="hidden" id="registrationCopy" name="registrationCopy.attachmentId" class="form-control" />
															<span class="input-group-btn">
															<button  class="btn btn-default registrationCopy fileDeleteBtn" onclick="return submitWithThreeParam('deleteAttachmentById','PartnerOrgRegistration','registrationCopy','registrationCopy','regAttachmentDeleteResp');" disabled="disabled"><i class="fa fa-times"></i></button>
															</span>
															</div>
															<!--  <input type="hidden" id="registrationFileName" name="registrationCopy.fileName" class="form-control" /> -->
             	                                            <a class="col-sm-12 filelabel" data-url="<%=request.getContextPath()%>/download" id="a_registrationCopy"></a>
															
															</div>
														</div>
														
													</div>
													<div class="clearfix"></div>
				                                    <div class="form-group partnerOrgEEApproveDiv" >
                                                    <label class="col-sm-2"><b>Add EE Comment:</b> </label>
														<div class="col-sm-4">
															<textarea class="form-control partnerOrgRemark changeComment" name="eeComment" id="eeRemark" maxlength="255" ></textarea>
														</div>
														<div class="col-sm-6">
															<label><b>Select Status:</b> </label> 
															<input type="radio"  value="Y" id="eeApproveBtn"  name="isEEApproved" class="partnerOrgStatusBtn changeButton" checked />Approve 
															<!-- <input type="radio" name="isApproved" value="N" id="rejectBtn" class="statusBtn changeButton" /> Reject -->
														    <input type="radio"  value="C" id="eeClarification"  name="isEEApproved" class="partnerOrgStatusBtn changeButton"  /> Clarification
														</div>
											  </div>  
											     <div class="form-group partnerOrgCEApproveDiv" >
                                                    <label class="col-sm-2"><b>Add CE Comment:</b> </label>
														<div class="col-sm-4">
															<textarea class="form-control partnerOrgRemark changeComment" name="ceComment" id="ceRemark" maxlength="255" ></textarea>
														</div>
														<div class="col-sm-6">
															<label><b>Select Status:</b> </label> 
															<input type="radio"  value="Y" id="ceApproveBtn" name="isCEApproved"  class="partnerOrgStatusBtn changeButton" checked />Approve 
															<!-- <input type="radio" name="isApproved" value="N" id="rejectBtn" class="statusBtn changeButton" /> Reject -->
														    <input type="radio"  value="C" name="isCEApproved" class="partnerOrgStatusBtn changeButton"  id="ceClarification" /> Clarification
														</div>
											  </div>
													<div class="col-sm-12 text-center positionABS"
														style="margin-bottom: 10px;">
														<button
															class="btn-all btn btn-info k-tabstrip-prev btnPrevious pull-left tab-li-prev">Previous</button>
														<span id="OrgRegFormId" class="">
														<button class="btn btn-info save partnerOrgActionBtn" onclick="return submitIt('partnerOrgUdyogForm','saveOrgRegistration','partnerOrgRegistrationResp');">Save</button>
														<button class="btn btn-info cancel partnerOrgActionBtn"  id="cancelOrgRegBtnId">Reset</button>
														</span>
														<button
															class="btn-all btn btn-info k-tabstrip-next btnNext pull-right tab-li-next">Next</button>
													</div>
												
											</sf:form>
										</div>
										<!-- factory reg tab closed -->
												<!-- BIS Open -->
												  <div>
                                                <sf:form id="partnerOrgBISForm" modelAttribute="partnerOrgBIS" method="POST">
                                                <div class="form-group partnerOrgTabs">
			                                        <div class="col-sm-12">
			                                        <h4 class="col-sm-8"><b>Bureau of Indian Standards</b></h4>
			                                        <div class="col-sm-4 text-right"><button class="btn btn-default" id="addBISBtnId"><span class="glyphicon glyphicon-plus-sign"></span>Add</button>
                                                   <!--  <button class="btn btn-default" id="editBISBtnId"><span class="glyphicon glyphicon-pencil"></span>Edit</button> -->
                                                    <button class="btn btn-default" id="deleteBISBtnId" onclick="return submitWithParam('deleteBIS','partnerOrgBISId','partnerOrgBISDelResp');"><span class="glyphicon glyphicon-trash"></span>Delete</button></div>
                                                    </div><hr>
			                                       
			                                        </div>
			                                        
                                                    <div class="form-group ">
                                                     <div class="col-sm-3" style="display: none;">
                                                            <div class="styled-input partnerOrgTabs">
                                                            <input type="hidden" id="bPartnerId"  class="bPartnerId" name="partner.bPartnerId" required />
                                                            <input type="hidden" class="partnerOrgId" id="partnerOrgId"   name="partnerOrg.partnerOrgId" required />
                                                            <input type="hidden" id="partnerOrgBISId"  name="partnerOrgBisId" required />
                                                             <input type="hidden" id="isActive"  name="isActive" value="Y" required />
                                                      </div>
                                                   </div>
                                                        <div class="col-sm-12 partnerOrgTabs">
	                                                     	<label class="checkbox-inline">
	                                                    		<input type="checkbox" id="isNotApplicableBis"	name="isNotApplicable" value="Y" onchange="isNotApplicableBIS()">Not Applicable
	                                                    	</label>
                                                   		</div>
                                                        <div class="col-sm-3 partnerOrgTabs isNADiv">
                                                            <div class="styled-input">
                                                                <input type="text" id="bisLicenceNo" name="bisLicenceNo" title="Bureau of Indian Standards License Number" class="isNAChecked requiredField alphaNumericWithSpace" />
                                                                <label>BIS License number<span class="red">*</span></label>
                                                                <span></span>
                                                            </div>
                                                        </div>

                                                        <div class="col-sm-3 partnerOrgTabs isNADiv">
                                                            <!-- <div class="styled-input">
                                                                <input type="text" id="bisIssueDate" name="bisIssueDate" required />
                                                                <label>Issue Date of BIS License<span class="red">*</span></label>
                                                                <span></span>
                                                            </div> -->
                                                             <label>Issue Date of BIS License<!-- <span class="red">*</span> --></label>
                                                                       <div class="input-group date Pickdate" data-provide="datepicker">
                                                                         <input type="text" class="form-control isNAChecked" id="bisIssueDate" name="validFrom">
                                                                          <div class="input-group-addon">
                                                                       <span class="glyphicon glyphicon-th"></span>
                                                                   </div>
                                                             </div>
                                                        </div>

                                                        <div class="col-sm-3 partnerOrgTabs isNADiv">
                                                            <!-- <div class="styled-input">
                                                                <input type="text" id="bisValidityDate" name="bisValidityDate" required />
                                                                <label>Validity Date of BIS License<span class="red">*</span></label>
                                                                <span></span>
                                                            </div> -->
                                                             <label>Validity Date of BIS License<!-- <span class="red">*</span> --></label>
                                                                       <div class="input-group date futureDate" data-provide="datepicker">
                                                                         <input type="text" class="form-control isNAChecked" id="bisValidDate" name="validTo">
                                                                          <div class="input-group-addon">
                                                                       <span class="glyphicon glyphicon-th"></span>
                                                                   </div>
                                                             </div>
                                                        </div>

                                                       <div class="col-sm-3 isNADiv">
                                                            <label>Attach BIS License Certificate<!-- <span class="red">*</span> --></label>
                                                            <div class="input-group partnerOrgTabs">
                                                            <input type="file" id="bisFileId" data-id="bisLicenceCertificate" data-name="bisLicenceFileName" data-anchor="a_bisLicenceCopy"
								                             class="form-control uploadFile isNAChecked" required />
                                                            <input type="hidden" id="bisLicenceCertificate" name="bisLicenceCertificate.attachmentId" class="form-control isNAChecked" />
                                                            <span class="input-group-btn">
                                                            <button  class="btn btn-default bisLicenceCertificate fileDeleteBtn" onclick="return submitWithThreeParam('deleteAttachmentById','PartnerOrgBIS','bisLicenceCertificate','bisLicenceCertificate','bisAttachmentDeleteResp');" disabled="disabled"><i class="fa fa-times"></i></button>
                                                            </span>
                                                            </div>
                                                            <!--  <input type="hidden" id="bisLicenceFileName" name="bisLicenceCertificate.fileName" class="form-control" /> -->
             	                                            <a class="col-sm-12 filelabel" data-url="<%=request.getContextPath()%>/download" id="a_bisLicenceCopy"></a>
                                                        </div>

                                                    </div>
                                                     <div class="clearfix"></div>
		                                               <div class="form-group partnerOrgEEApproveDiv" >
                                                    <label class="col-sm-2"><b>Add EE Comment:</b> </label>
														<div class="col-sm-4">
															<textarea class="form-control partnerOrgRemark changeComment" name="eeComment" id="eeRemark" maxlength="255" ></textarea>
														</div>
														<div class="col-sm-6">
															<label><b>Select Status:</b> </label> 
															<input type="radio"  value="Y" id="eeApproveBtn"  name="isEEApproved" class="partnerOrgStatusBtn changeButton" checked />Approve 
															<!-- <input type="radio" name="isApproved" value="N" id="rejectBtn" class="statusBtn changeButton" /> Reject -->
														    <input type="radio"  value="C" id="eeClarification"  name="isEEApproved" class="partnerOrgStatusBtn changeButton"  /> Clarification
														</div>
											  </div>  
											    <div class="form-group partnerOrgCEApproveDiv" >
                                                    <label class="col-sm-2"><b>Add CE Comment:</b> </label>
														<div class="col-sm-4">
															<textarea class="form-control partnerOrgRemark changeComment " name="ceComment" id="ceRemark" maxlength="255" ></textarea>
														</div>
														<div class="col-sm-6">
															<label><b>Select Status:</b> </label> 
															<input type="radio"  value="Y" id="ceApproveBtn" name="isCEApproved"  class="partnerOrgStatusBtn changeButton" checked />Approve 
															<!-- <input type="radio" name="isApproved" value="N" id="rejectBtn" class="statusBtn changeButton" /> Reject -->
														    <input type="radio"  value="C" name="isCEApproved" class="partnerOrgStatusBtn changeButton"  id="ceClarification" /> Clarification
														</div>
											  </div>
                                                
                                                <div class="col-sm-12 text-center positionABS" style="margin-bottom:10px;">
		                                              <button class="btn-all btn btn-info k-tabstrip-prev btnPrevious pull-left tab-li-prev">Previous</button>
		                                             <span id="bisFormDivId" class="">
		                                              <button class="btn btn-info save partnerOrgActionBtn" onclick="return submitIt('partnerOrgBISForm','saveOrgBISDetails','partnerOrgBISResp');">Save</button>
		                                             <button class="btn btn-info cancel partnerOrgActionBtn"  id="cancelBISBtnId">Reset</button>
		                                             </span>
		                                              <button class="btn-all btn btn-info k-tabstrip-next btnNext pull-right tab-li-next">Next</button>
		                                        </div>
                                                </sf:form>
                                                </div>
                                                 <!-- BIS close -->
                                                 
                                                <!--tab inside tab fields 7  -->
                                                <div>
                                           <sf:form id="partnerOrgProductForm" modelAttribute="partnerOrgProduct" method="POST">
                                           <div class="form-group partnerOrgTabs">
                                        <div class="col-sm-12">
                                        <!-- <h4 ><b>Items Manufactured by Firms</b></h4> -->
                                        <h4 class="col-xs-6 nopadding"><b>Items Manufactured by Firms</b></h4>
                                        <div class="col-xs-6 text-right nopadding" style="margin-top:10px;">
                                                    <button class="btn btn-default" id="addMaterialBtnId"><span class="glyphicon glyphicon-plus-sign"></span>Add</button>
                                                    <!-- <button class="btn btn-default" id="editMaterialBtnId"><span class="glyphicon glyphicon-pencil"></span>Edit</button> -->
                                                    <button class="btn btn-default" id="deleteMaterialBtnId"><span class="glyphicon glyphicon-trash"></span>Delete</button></div>
                                        <hr>
                                        </div>
                                        </div>
                                       
                                           <div class="form-group partnerOrgTabs">
                                                    <div class="col-sm-3" style="display: none;">
                                                            <div class="styled-input">
                                                            <input type="hidden" id="bPartnerId" class="bPartnerId" name="partner.bPartnerId"  required />
                                                            <input type="hidden" class="partnerOrgId" id="partnerOrgId"  name="partnerOrg.partnerOrgId" required />
                                                            <input type="hidden" id="partnerOrgProductId" name="partnerOrgProductId" required />
                                                            <input type="checkbox" style="display:none;"id="isActive" name="isActive" value="Y" checked="checked"/>
                                                           </div>
                                                     </div>
                                                     
                                                     <div class="col-sm-3">
                                                           <div class="styled-input">
                                                                <select  class="multiselectitem dropDown requiredField" name="material.materialId" >
                                                                 <option value="">  </option> 
                                                                </select>
                                                                <label> Select Item<span class="red">*</span></label>
                                                                <span></span>
                                                            </div>
                                                        </div>
                                                        <div class="col-sm-1">
                                                        	<button class="btn btn-default aditbt addItemPopup" id="">Add Item</button>
                                                        </div>
                                                        <div class="col-sm-3">
                                                            <div class="styled-input readonly">
                                                                <input type="text" id="itemName" name="material.name" class="requiredField" />
                                                                <label>Item Name<span class="red">*</span></label>
                                                                <span></span>
                                                            </div>
                                                        </div>

                                                        <div class="col-sm-3">
                                                           <div class="styled-input readonly">
                                                                <select id="uom" name="uom.uomId" class="requiredField dropDown" >
                                                                <option> Select UOM </option>
                                                                </select>
                                                                <label>UOM<span class="red">*</span></label>
                                                                <span></span>
                                                            </div>
                                                        </div>
                                                        </div>
													 <div class="form-group partnerOrgTabs">
                                                        <div class="col-sm-3">
                                                            <div class="styled-input">
                                                                <input type="text" id="quantityManufacturedPerMonth" name="qtyManufacturedPM" title="Quantity Manufactured Per Month" class="requiredField onlyNumber" />
                                                                <label>Quantity Manufactured/Month<span class="red">*</span></label>
                                                                <span></span></div>
                                                        </div>
                                                        <div class="col-sm-3">
                                                            <div class="styled-input">
                                                                <input type="text" id="turnOver" name="turnOver" title="Turnover Per Annum For Item(In Lakhs)" class="requiredField onlyNumber" />
                                                                <label>Turnover Per Annum For Item(in Lakhs)<span class="red">*</span></label>
                                                                <span></span></div>
                                                        </div>
                                                                                                       
                                                        <div class="col-sm-3">
                                                            <div class="styled-input">
                                                                <input type="text" id="licenceNo" name="licenceNo" class="onlyNumber" />
                                                                <label>Industrial license number</label>
                                                                <span></span>
                                                            </div>
                                                        </div>
                                                        </div>
                                                     <div class="form-group">
                                                        <div class="col-sm-3 ">
                                                            <!-- <div class="styled-input"> -->
                                                              <label>Industrial License Copy<!-- <span class="red">*</span> --></label>
                                                               <div class="input-group partnerOrgTabs">
                                                                <input type="file"  id="industrialFileId" data-id="industrialLicenceCopy" data-anchor="a_industrialLicenceCopy" class="form-control uploadFile" required />
                                                                <input type="hidden" id="industrialLicenceCopy" name="industrialLicenceCopy.attachmentId" class="form-control" />
                                                                <span class="input-group-btn">
                                                                <button  class="btn btn-default industrialLicenceCopy fileDeleteBtn" onclick="return submitWithThreeParam('deleteAttachmentById','PartnerOrgProduct','industrialLicenceCopy','industrialLicenceCopy','productAttachmentDeleteResp');" disabled="disabled"><i class="fa fa-times"></i></button>
                                                                </span>
                                                                </div>
                                                                 <a class="col-sm-12 filelabel" data-url="<%=request.getContextPath()%>/download" id="a_industrialLicenceCopy"></a>
                                                               <!-- </div> -->
                                                        </div>
                                                         <div class="col-sm-3 partnerOrgTabs">
                                                               <div class="styled-input">
                                                                <select id="regType" name="registrationType"  onchange="return submitWithTwoParam('getPartnerOrgByRegType','regType','partnerOrgId','loadRegFactory');" >
                                                                <option> Select Registration Type </option>
                                                                </select>
                                                                <label>Registration Type<!-- <span class="red">*</span> --></label>
                                                                <span></span>
                                                            </div>
                                                            </div>
                                                            <div class="col-sm-3 partnerOrgTabs">
                                                               <div class="styled-input">
                                                                <select id="regPartnerOrg" name="partnerOrgRegistration.partnerOrgRegistrationId" >
                                                                <option>Select Factory</option>
                                                                </select>
                                                                <label>Registered Factory<span class="red"></span></label>
                                                                <span></span>
                                                            </div>
                                                          </div>
                                                         <div class="col-sm-3 partnerOrgTabs">
                                                               <div class="styled-input">
                                                                <select id="partnerOrgBIS" name="partnerOrgBIS.partnerOrgBisId">
                                                                </select>
                                                                <label>BIS<span class="red"></span></label>
                                                                <span></span>
                                                            </div>
                                                          </div>
                                                    </div>
                                                     <div class="clearfix"></div>
		                                              <div class="form-group partnerOrgEEApproveDiv" >
                                                    <label class="col-sm-2"><b>Add EE Comment:</b> </label>
														<div class="col-sm-4">
															<textarea class="form-control partnerOrgRemark changeComment" name="eeComment" id="eeRemark" maxlength="255" ></textarea>
														</div>
														<div class="col-sm-6">
															<label><b>Select Status:</b> </label> 
															<input type="radio"  value="Y" id="eeApproveBtn"  name="isEEApproved" class="partnerOrgStatusBtn changeButton" checked />Approve 
															  <input type="radio"  value="EXEM" id="eeExempted" name="isEEApproved" class="partnerOrgStatusBtn changeButton" />Exempted
														    <input type="radio"  value="C" id="eeClarification"  name="isEEApproved" class="partnerOrgStatusBtn changeButton"  /> Clarification
														</div>
											  </div>  
											    <div class="form-group partnerOrgCEApproveDiv" >
                                                    <label class="col-sm-2"><b>Add CE Comment:</b> </label>
														<div class="col-sm-4">
															<textarea class="form-control partnerOrgRemark changeComment" name="ceComment" id="ceRemark" maxlength="255" ></textarea>
														</div>
														<div class="col-sm-6">
															<label><b>Select Status:</b> </label> 
															<input type="radio"  value="Y" id="ceApproveBtn"  name="isCEApproved" class="partnerOrgStatusBtn changeButton" checked />Approve 
															  <input type="radio"  value="EXEM" id="ceExempted" name="isCEApproved" class="partnerOrgStatusBtn changeButton" />Exempted
														    <input type="radio"  value="C" id="ceClarification"  name="isCEApproved" class="partnerOrgStatusBtn changeButton"  /> Clarification
														</div>
											  </div>  
                                                    <div class="col-sm-12 text-center positionABS" style="margin-bottom:10px;">
		                                        <button class="btn-all btn btn-info k-tabstrip-prev btnPrevious pull-left tab-li-prev">Previous</button>
		                                       <span class="" id="productformDivId">
		                                        <button class="btn btn-info save partnerOrgActionBtn" id="saveBtnId save" onclick="return submitIt('partnerOrgProductForm','savePartnerOrgProduct','partnerOrgProductResp');">Save</button>
		                                         <button class="btn btn-info cancel partnerOrgActionBtn"  id="cancelBtnId">Reset</button>
		                                       </span>
		                                        <button class="btn-all btn btn-info k-tabstrip-next btnNext pull-right tab-li-next">Next</button>
		                                    </div>
		                                    
                                                   </sf:form>
                                                </div>
                                                <!--tab inside tab fields 7   -->

                                                <!--tab inside tab fields 8   -->
                                                <!-- <div>
                                                <div class="form-group">
			                                        <div class="col-sm-12">
			                                        <h4 ><b>Small Scale Industry Registration Details</b></h4>
			                                        <hr>
			                                        </div>
			                                        </div>
			                                        
                                                    <div class="form-group">
                                                        <div class="col-sm-3">
                                                            <input type="checkbox" id="" name="" />
                                                            <label>Not Applicable</label>
                                                            <span></span>
                                                        </div>
                                                    </div>

                                                    <div class="form-group">
                                                        <div class="col-sm-4">
                                                            <div class="styled-input">
                                                                <select id="" name="" /></select>
                                                                <label>Type of SSI Registration</label>
                                                                <span></span>
                                                            </div>
                                                        </div>

                                                        <div class="col-sm-4">
                                                            <div class="styled-input">
                                                                <input type="text" id="" name="" required />
                                                                <label>SSI Registration Authority<span class="red">*</span></label>
                                                                <span></span></div>
                                                        </div>

                                                        <div class="col-sm-4">
                                                            <div class="styled-input">
                                                                <input type="text" id="" name="" required />
                                                                <label>SSI Registration Number<span class="red">*</span></label>
                                                                <span></span></div>
                                                        </div>
                                                    </div>

                                                    <div class="form-group">
                                                        <div class="col-sm-4">
                                                            <div class="styled-input">
                                                                <input type="text" id="" name="" required />
                                                                <label>Issue Date of SSI Registration<span class="red">*</span></label>
                                                                <span></span></div>
                                                        </div>

                                                        <div class="col-sm-4">
                                                            <div class="styled-input">
                                                                <input type="text" id="" name="" required />
                                                                <label>Validity Date of SSI Registration<span class="red">*</span></label>
                                                                <span></span>
                                                            </div>
                                                        </div>

                                                        <div class="col-sm-4">
                                                            <div class="styled-input">
                                                                <input type="text" id="" name="" />
                                                                <label>Date of Commencement of Commercial Production</label>
                                                                <span></span></div>
                                                        </div>
                                                    </div>

                                                    <div class="form-group">
                                                        <div class="col-sm-4">
                                                            <div class="styled-input">
                                                                <select id="" name="" required /></select>
                                                                <label>Items For Which SSI Registration is Given<span class="red">*</span></label>
                                                                <span></span></div>
                                                        </div>

                                                        <div class="col-sm-4">
                                                            <div class="styled-input">
                                                                <select id="" name="" required /></select>
                                                                <label>SSI Registration Certificate is under renewal process<span class="red">*</span></label>
                                                                <span></span></div>
                                                        </div>

                                                        <div class="col-sm-4">
                                                            <label>Attach SSI Registration Copy<span class="red">*</span></label>
                                                            <input type="file" id="" name="" class="form-control" required />
                                                            <span></span>
                                                        </div>

                                                    </div>
                                                </div>
 -->                                                <!--tab inside tab fields 8   -->

                                        <!--tab inside tab fields 9   -->
										
										<!--tab inside tab fields 9  -->

										<!--tab inside tab fields 10  -->
										<div>
											<sf:form id="partnerOrgPerformanceForm"
												modelAttribute="partnerOrgPerformance" method="POST">
												<div class="form-group partnerOrgTabs">
													<div class="col-sm-12">
														<h4 class="col-xs-6 nopadding">
															<b>Past Performance Details</b>
														</h4>
														<div class="col-xs-6 text-right nopadding"
															style="margin-top: 10px;">
															<button class="btn btn-default" id="addPerformanceBtnId">
																<span class="glyphicon glyphicon-plus-sign"></span>Add
															</button>
															<!-- <button class="btn btn-default" id="editPerformanceBtnId">
																<span class="glyphicon glyphicon-pencil"></span>Edit

															</button> -->
															<button class="btn btn-default" id="deletePerformanceBtnId" >
															<span class="glyphicon glyphicon-trash"></span>Delete</button>
															</div>

															<hr>
														</div>
													</div>
												

												

													<div class="form-group partnerOrgTabs">
														<div class="col-sm-3" style="display: none;">
															<div class="styled-input">
																<input type="hidden" id="bPartnerId" class="bPartnerId" name="partner.bPartnerId" required /> <input
																	type="hidden" class="partnerOrgId"
																	name="partnerOrg.partnerOrgId" id="partnerOrgId"
																	required /> <input type="hidden" id="performanceId"
																	name="partnerOrgPerformanceId" required />
																<!--  <input type="hidden" id="materialId"  name="material.materialId" required /> -->
																<input
																	type="checkbox" id="isActive" name="isActive"
																	style="display: none;" checked="checked" value="Y" />
															</div>
														</div>

														<div class="col-sm-3">
															<div class="styled-input">
																<select class="singleselectitem dropDown requiredField" id="itemName" name="material.materialId" >
																<option value=""></option>
																</select> <label>Item Name<span class="red">*</span></label>
																<span></span>
															</div>
														</div>
														<div class="col-sm-1">
                                                        	<button class="btn btn-default aditbt addItemPopup" id="">Add Item</button>
                                                        </div>
														<div class="col-sm-3">
															<div class="styled-input">
																<input type="text" id="firmName" name="firmName"
																	class="requiredField requiredalphabeticsWithSpace" /> <label>Name of Firm<span
																	class="red">*</span></label> <span></span>
															</div>
														</div>

														<div class="col-sm-3">
															<label>Order Start Date<span class="red"></span></label>
															<div class="input-group date" data-provide="datepicker">
																<input type="text" class="form-control"
																	id="orderStartDate" name="orderStartDate">
																<div class="input-group-addon">
																	<span class="glyphicon glyphicon-th"></span>
																</div>
															</div>
														</div>

													</div>
													<div class="form-group partnerOrgTabs">
														<div class="col-sm-3">
															<label>Order Completion Date<span class="red"></span></label>
															<div class="input-group date" data-provide="datepicker">
																<input type="text" class="form-control"
																	id="orderEndDate" name="orderEndDate">
																<div class="input-group-addon">
																	<span class="glyphicon glyphicon-th"></span>
																</div>
															</div>
														</div>
														<div class="col-sm-3">
															<div class="styled-input">
																<input type="text" id="quantitySupplied"
																	name="quantitySupplied" class="requiredField onlyNumber" /> <label>Quantity
																	Supplied<span class="red">*</span>
																</label> <span></span>
															</div>
														</div>

														<div class="col-sm-3">
															<div class="styled-input">
																<input type="text" id="referenc1" name="referenc1" class="requiredalphabeticsWithSpace"/> <label>Reference
																	1</label> <span></span>
															</div>
														</div>
														<div class="col-sm-3">
															<div class="styled-input">
																<input type="text" id="referenc2" name="referenc2" class="requiredalphabeticsWithSpace" /> <label>Reference
																	2</label> <span></span>
															</div>
														</div>
														</div>
														<div class="form-group">
														

															<div class="col-sm-3">
															<label>Certificate Awarded (If Any)</label> 
															<div class="input-group partnerOrgTabs">
																<input type="file" id="certificateFileId" data-id="certificateAward" data-anchor="a_certificateAward" class="form-control uploadFile" />
															    <input type="hidden" id="certificateAward" name="certificateAward.attachmentId" class="form-control" />
															<span class="input-group-btn">
															  <button  class="btn btn-default certificateAward fileDeleteBtn" onclick="return submitWithThreeParam('deleteAttachmentById','PartnerOrgPerformance','certificateAward','certificateAward','performanceAttachmentDeleteResp');" disabled="disabled"><i class="fa fa-times"></i></button>
															</span>
															</div>
															<a class="col-sm-12 filelabel" data-url="<%=request.getContextPath()%>/download" id="a_certificateAward"></a>
								                          </div>
								                          <div class="col-sm-3">
															<div class="styled-input partnerOrgTabs">
																<input type="text" id="poNumber" name="poNumber" class="requiredField alphaNumeric" /> <label>P.O. Number <span class="red">*</span></label>
															</div>
														  </div>

													</div>
													<div class="clearfix"></div>
		                                               <div class="form-group partnerOrgEEApproveDiv" >
		                                                    <label class="col-sm-2"><b>Add EE Comment:</b> </label>
															 <div class="col-sm-4">
															     <textarea class="form-control partnerOrgRemark changeComment"  name="eeComment" id="eeRemark" maxlength="255" ></textarea>
														    </div>
														<div class="col-sm-6">
															<label><b>Select Status:</b> </label> 
															<input type="radio"  value="Y" id="eeApproveBtn" name="isEEApproved" class="partnerOrgStatusBtn changeButton" checked />Approve 
															<!-- <input type="radio" name="isApproved" value="N" id="rejectBtn" class="statusBtn changeButton" /> Reject -->
														    <input type="radio"  value="C" id="eeClarification"  name="isEEApproved" class="partnerOrgStatusBtn changeButton"  /> Clarification
														</div>
													  </div>
													  <div class="form-group partnerOrgCEApproveDiv" >
		                                                    <label class="col-sm-2"><b>Add CE Comment:</b> </label>
															 <div class="col-sm-4">
															     <textarea class="form-control partnerOrgRemark changeComment" name="ceComment" id="ceRemark" maxlength="255" ></textarea>
														    </div>
														<div class="col-sm-6">
															<label><b>Select Status:</b> </label> 
															<input type="radio"  value="Y" id="ceApproveBtn"  name="isCEApproved" class="partnerOrgStatusBtn changeButton" checked />Approve 
															<!-- <input type="radio" name="isApproved" value="N" id="rejectBtn" class="statusBtn changeButton" /> Reject -->
														    <input type="radio"  value="C" id="ceClarification" name="isCEApproved" class="partnerOrgStatusBtn changeButton"  /> Clarification
														</div>
													  </div>
													<div class="col-sm-12 text-center positionABS"
														style="margin-bottom: 10px;">
														<button
															class="btn-all btn btn-info k-tabstrip-prev btnPrevious pull-left tab-li-prev">Previous</button>
														<span id="OrgPerformanceFormId" class="">
														<button class="btn btn-info saveBtnId save partnerOrgActionBtn" onclick="return submitIt('partnerOrgPerformanceForm','savePartnerOrgPerformance','partnerOrgPerformanceResp');">Save</button>
														<button class="btn btn-info  cancel partnerOrgActionBtn" id="cancelPerformanceBtnId">Reset</button>
														</span>
														<button
															class="btn-all btn btn-info k-tabstrip-next btnNext pull-right tab-li-next">Next</button>
													</div>
												
											</sf:form>
										</div>
										<!--tab inside tab fields 10  -->

										<!--tab inside tab fields 11 -->
										<div>
											<sf:form id="partnerOrgRDAECForm" modelAttribute="partnerOrgRDAEC" method="POST">
												<div class="form-group">
													<div class="col-sm-12">
														<h4>
															<b>Regional Development Authority Eligibility</b>
														</h4>
														<hr>
													</div>
												</div>

												<div class="form-group partnerOrgTabs">
													<div class="col-sm-3" style="display: none;">
														<div class="styled-input">
															<input type="hidden" id="bPartnerId" class="bPartnerId" name="partner.bPartnerId" required /> 
															<input type="hidden" class="partnerOrgId" name="partnerOrg.partnerOrgId" id="partnerOrgId"
																required />
														    <input type="hidden" id="partnerOrgRDAECId"name="partnerOrgRDAECId" required /> 
														    <input type="checkbox" id="isActive" name="isActive"
																style="display: none;" checked="checked" value="Y" />
														</div>
													</div>
													<div class="col-sm-3">
														<input type="checkbox" id="isApplicable"
															name="isApplicable" /> <label>Not Applicable</label> <span></span>
													</div>
												</div>
												<div class="showField">
													<div class="form-group partnerOrgTabs">
														<div class="col-sm-3">
															<div class="styled-input">
																<input type="text" id="elegibilityType"
																	name="elegibilityType" class="requiredField requiredalphabeticsWithSpace"/>
																
																<label>Type of Eligibility<span class="red">*</span></label>
																<span></span>
															</div>
														</div>

														<div class="col-sm-3">
															<div class="styled-input">
																<select id="developingRegion" name="developingRegion"
																	class="requiredField dropDown" /></select> <label>Developing Region<span
																	class="red">*</span></label> <span></span>
															</div>
														</div>

														<div class="col-sm-3">
															<div class="styled-input">
																<input type="text" id="isPioneer" name="isPioneer" class="requiredField requiredalphabeticsWithSpace" />
																<!-- <select id="isPioneer" name="isPioneer" /></select> -->
																<label>Pioneer Industry<span class="red">*</span></label>
															</div>
														</div>

														<div class="col-sm-3">
															<!-- <div class="styled-input">
                                                                <input type="text" id="validFrom" name="validFrom" required />
                                                                <label>Issue Date of Eligibility<span class="red">*</span></label>
                                                                <span></span>
                                                            </div> -->
															<label>Issue Date of Eligibility<!-- <span class="red">*</span> --></label>
															<div class="input-group date" data-provide="datepicker">
																<input type="text" class="form-control" id="validFrom"
																	name="validFrom">
																<div class="input-group-addon">
																	<span class="glyphicon glyphicon-th"></span>
																</div>
															</div>

														</div>
													</div>
													<div class="form-group">
														<div class="col-sm-3 partnerOrgTabs">
															<!-- <div class="styled-input">
                                                                <input type="text" id="validTo" name="validTo" required />
                                                                <label>Validity Date of Eligibility Certificate<span class="red">*</span></label>
                                                                <span></span>
                                                            </div> -->
															<label>Validity Date of Eligibility Certificate<!-- <span
																class="red">*</span> --></label>
															<div class="input-group date " data-provide="datepicker">
																<input type="text" class="form-control" id="validTo"
																	name="validTo">
																<div class="input-group-addon">
																	<span class="glyphicon glyphicon-th"></span>
																</div>
															</div>
														</div>
														<div class="col-sm-3">
															<label>Attach Valid Eligibility Certificate<!-- <span
																class="red">*</span> --></label>
																<div class="input-group partnerOrgTabs">
															    <input type="file" id="eligibilityFileId" data-id="eligibilityCertificate" data-anchor="a_eligibilityCertificate" class="form-control uploadFile" />
																<input  type="hidden" id="eligibilityCertificate" name="eligibilityCertificate.attachmentId" class="form-control" />
																 <span class="input-group-btn">
																 <button  class="btn btn-default eligibilityCertificate fileDeleteBtn" onclick="return submitWithThreeParam('deleteAttachmentById','PartnerOrgRDAEC','eligibilityCertificate','eligibilityCertificate','attachmentRDAECDeleteResp');" disabled="disabled"><i class="fa fa-times"></i></button>
																 </span>
																 </div>
																 <a class="col-sm-12 filelabel" data-url="<%=request.getContextPath()%>/download" id="a_eligibilityCertificate"></a>
							                            </div>
													</div>
													
												</div>
												<div class="clearfix"></div>
		                                               <div class="form-group partnerOrgEEApproveDiv" >
		                                                    <label class="col-sm-2"><b>Add EE Comment:</b> </label>
															 <div class="col-sm-4">
															     <textarea class="form-control partnerOrgRemark changeComment"  name="eeComment" id="eeRemark" maxlength="255" ></textarea>
														    </div>
														<div class="col-sm-6">
															<label><b>Select Status:</b> </label> 
															<input type="radio"  value="Y" id="eeApproveBtn" name="isEEApproved" class="partnerOrgStatusBtn changeButton" checked />Approve 
															<!-- <input type="radio" name="isApproved" value="N" id="rejectBtn" class="statusBtn changeButton" /> Reject -->
														    <input type="radio"  value="C" id="eeClarification"  name="isEEApproved" class="partnerOrgStatusBtn changeButton"  /> Clarification
														</div>
													  </div>
													  <div class="form-group partnerOrgCEApproveDiv" >
		                                                    <label class="col-sm-2"><b>Add CE Comment:</b> </label>
															 <div class="col-sm-4">
															     <textarea class="form-control partnerOrgRemark changeComment" name="ceComment" id="ceRemark" maxlength="255" ></textarea>
														    </div>
														<div class="col-sm-6">
															<label><b>Select Status:</b> </label> 
															<input type="radio"  value="Y" id="ceApproveBtn"  name="isCEApproved" class="partnerOrgStatusBtn changeButton" checked />Approve 
															<!-- <input type="radio" name="isApproved" value="N" id="rejectBtn" class="statusBtn changeButton" /> Reject -->
														    <input type="radio"  value="C" id="ceClarification" name="isCEApproved" class="partnerOrgStatusBtn changeButton"  /> Clarification
														</div>
													  </div>
												<div class="col-sm-12 text-center positionABS"
													style="margin-bottom: 10px;">
													<button
														class="btn-all btn btn-info k-tabstrip-prev btnPrevious pull-left tab-li-prev">Previous</button>
													<span id="formRDAECDivId" class="">
													<button class="btn btn-info save partnerOrgActionBtn" onclick="return submitIt('partnerOrgRDAECForm','saveOrgRDAEC','partnerOrgRDAECResp');">Save</button>
													<button class="btn btn-info cancel partnerOrgActionBtn"  id="cancelRDAECBtnId">Reset</button>
													</span>
													<button
														class="btn-all btn btn-info k-tabstrip-next btnNext pull-right tab-li-next">Next</button>
												</div>
											</sf:form>
										</div>
										<!--tab inside tab fields 11  -->

                                                <!-- tab inside tab fields 12  -->
                                             <div>
                                              <sf:form id="partnerOrgOEForm" modelAttribute="partnerOrgOE" method="POST">
                                                
                                                <div class="form-group">
			                                        <div class="col-sm-12">
			                                        <h4 ><b>Other Eligibility Details</b></h4>
			                                        <hr>
			                                        </div>
			                                    </div>
			                                        
                                                    <div class="form-group partnerOrgTabs">
                                                       <div class="col-sm-3" style="display: none;">
                                                            <div class="styled-input">
                                                            <input type="hidden" id="bPartnerId" class="bPartnerId"  name="oeList[0].partner.bPartnerId" required />
                                                            <input type="hidden" id="bPartnerId" class="bPartnerId"  name="oeList[1].partner.bPartnerId" required />
                                                              <input type="hidden" id="OEDetailsIdDGSD" name="oeList[0].partnerOrgOEId" required />
                                                              <input type="hidden" id="OEDetailsIdDGTD" name="oeList[1].partnerOrgOEId" required />
                                                              <input type="hidden" class="partnerOrgId" name="oeList[0].partnerOrg.partnerOrgId" id="partnerOrgId" required />
                                                              <input type="hidden" class="partnerOrgId" name="oeList[1].partnerOrg.partnerOrgId" id="partnerOrgId" required />
														    <input type="checkbox" id="isActiveDGSD" name="oeList[0].isActive" style="display: none;" checked="checked" value="Y" />
														    <input type="checkbox" id="isActiveDGTD" name="oeList[1].isActive" style="display: none;" checked="checked" value="Y" />
                                                           <input type="hidden"  name="oeList[0].oeType" id="otherDetailTypeDGSD" value="DGSD" required />
                                                           <input type="hidden"  name="oeList[1].oeType" id="otherDetailTypeDGTD" value="DGTD" required />
                                                           </div>
                                                     </div>
                                                      <div class="col-sm-12">
                                                     <label class="checkbox-inline">
                                                    <input type="checkbox" id="isNotApplicableDGSD"	name="oeList[0].isApplicable">Not Applicable for GEM Registration Eligibility</label>
                                                   
                                                        <!-- <div class="col-sm-3">
														<input type="checkbox" id="isNotApplicableDGSD"
															name="oeList[0].isApplicable" /> <label>Not Applicable for DGS&D Registration Eligibility</label>
                                                            <span></span>
                                                        </div> -->
                                                        </div>
                                                    </div>
                                                    <div>
												<div class="showDGSDField">
                                                    <div class="form-group partnerOrgTabs">

                                                        <div class="col-sm-3">
                                                            <div class="styled-input">
                                                                <input type="text" id="authorityDGSD" name="oeList[0].authority" class="requiredField requiredalphabeticsWithSpace" />
                                                                <label>GEM Registering Authority<span class="red">*</span></label>
                                                                <span></span>
                                                            </div>
                                                        </div>

                                                        <div class="col-sm-3">
                                                            <div class="styled-input">
                                                                <input type="text" id="RegsNoDGSD" name="oeList[0].RegsNo" class="requiredField onlyNumber" />
                                                                <label>GEM Registration Number<span class="red">*</span></label>
                                                                <span></span>
                                                            </div>
                                                        </div>

                                                        <div class="col-sm-3">
                                                            <!-- <div class="styled-input">
                                                                <input type="text" id="validFromDGS&D" name="validFrom" required />
                                                                <label>Issue Date Of DGS&D Registration<span class="red">*</span></label>
                                                                <span></span>
                                                            </div> -->
                                                            <label>Issue Date Of GEM Registration<!-- <span class="red">*</span> --></label>
                                                        <div class="input-group date" data-provide="datepicker">
                                                            <input type="text" class="form-control" id="validFromDGSD" name="oeList[0].validFrom">
                                                            <div class="input-group-addon">
                                                                <span class="glyphicon glyphicon-th"></span>
                                                            </div>
                                                        </div>
                                                        </div>
                                                        <div class="col-sm-3">
                                                            <!-- <div class="styled-input">
                                                                <input type="text" id="validToDGTD" name="validTo" required />
                                                                <label>Validity Date Of DGTD Registration<span class="red">*</span></label>
                                                                <span></span>
                                                            </div> -->
                                                            <label>Validity Date Of GEM Registration<!-- <span class="red">*</span> --></label>
                                                        <div class="input-group date" data-provide="datepicker">
                                                            <input type="text" class="form-control" id="validToDGSD" name="oeList[0].validTo">
                                                            <div class="input-group-addon">
                                                                <span class="glyphicon glyphicon-th"></span>
                                                            </div>
                                                        </div>
                                                        </div>
                                                    </div>
                                                   </div>
                                                   <div class="clearfix"></div>
		                                               <div class="form-group partnerOrgEEApproveDiv" id="partnerOrgEEApproveDiv_0">
		                                                    <label class="col-sm-2"><b>Add EE Comment:</b> </label>
															 <div class="col-sm-4">
															     <textarea class="form-control partnerOrgRemark changeComment"  name="oeList[0].eeComment" id="ee_remark_0" maxlength="255" ></textarea>
														    </div>
														<div class="col-sm-6">
															<label><b>Select Status:</b> </label> 
															<input type="radio"  value="Y" id="ee_approve_DGSD" name="oeList[0].isEEApproved" class="partnerOrgStatusBtn changeButton" checked />Approve 
															<!-- <input type="radio" name="isApproved" value="N" id="rejectBtn" class="statusBtn changeButton" /> Reject -->
														    <input type="radio"  value="C" id="ee_clarify_DGSD"  name="oeList[0].isEEApproved" class="partnerOrgStatusBtn changeButton"  /> Clarification
														</div>
													  </div>
													  <div class="form-group partnerOrgCEApproveDiv" id="partnerOrgCEApproveDiv_0">
		                                                    <label class="col-sm-2"><b>Add CE Comment:</b> </label>
															 <div class="col-sm-4">
															     <textarea class="form-control partnerOrgRemark changeComment" name="oeList[0].ceComment" id="ce_remark_0" maxlength="255" ></textarea>
														    </div>
														<div class="col-sm-6">
															<label><b>Select Status:</b> </label> 
															<input type="radio"  value="Y" id="ce_approve_DGSD"  name="oeList[0].isCEApproved" class="partnerOrgStatusBtn changeButton" checked />Approve 
															<!-- <input type="radio" name="isApproved" value="N" id="rejectBtn" class="statusBtn changeButton" /> Reject -->
														    <input type="radio"  value="C" id="ce_clarify_DGSD" name="oeList[0].isCEApproved" class="partnerOrgStatusBtn changeButton"  /> Clarification
														</div>
													  </div>
                                                    </div>
                                                     <div class="clearfix"></div>
												     <hr style="border-top: 1px solid #000;">
												    <div class="clearfix"></div>
                                                    <div class="form-group partnerOrgTabs">
                                                  <div class="col-sm-12" style="padding-top: 5px">  <label class="checkbox-inline">
                                                    <input type="checkbox" id="isNotApplicableDGTD" name="oeList[1].isApplicable" >Not Applicable for DGTD Registration Eligibility</label>
                                                        <!-- <div class="col-sm-3">
                                                            <input type="checkbox" id="isNotApplicableDGTD" name="oeList[1].isApplicable" />
                                                            <label>Not Applicable for DGTD Registration Eligibility</label>
                                                            <span></span>
                                                        </div> -->
                                                        </div>
                                                    </div>
                                                    <div>
												<div class="showDGTDField">
                                                    <div class="form-group partnerOrgTabs">

                                                        <div class="col-sm-3">
                                                            <div class="styled-input">
                                                                <input type="text" id="authorityDGTD" name="oeList[1].authority" class="requiredField requiredalphabeticsWithSpace" />
                                                                <label>DGTD Registering Authority<span class="red">*</span></label>
                                                                <span></span>
                                                            </div>
                                                        </div>

                                                        <div class="col-sm-3">
                                                            <div class="styled-input">
                                                                <input type="text" id="RegsNoDGTD" name="oeList[1].RegsNo" class="requiredField onlyNumber" />
                                                                <label>DGTD Registration Number<span class="red">*</span></label>
                                                                <span></span>
                                                            </div>
                                                        </div>

                                                        <div class="col-sm-3">
                                                            <!-- <div class="styled-input">
                                                                <input type="text" id="validFromDGTD" name="validFrom" required />
                                                                <label>Issue Date Of DGTD Registration<span class="red">*</span></label>
                                                                <span></span>
                                                            </div> -->
                                                            <label>Issue Date Of DGTD Registration<!-- <span class="red">*</span> --></label>
                                                        <div class="input-group date" data-provide="datepicker">
                                                            <input type="text" class="form-control" id="validFromDGTD" name="oeList[1].validFrom">
                                                            <div class="input-group-addon">
                                                                <span class="glyphicon glyphicon-th"></span>
                                                            </div>
                                                        </div>
                                                        </div>

                                                        <div class="col-sm-3">
                                                            <!-- <div class="styled-input">
                                                                <input type="text" id="validToDGTD" name="validTo" required />
                                                                <label>Validity Date Of DGTD Registration<span class="red">*</span></label>
                                                                <span></span>
                                                            </div> -->
                                                            <label>Validity Date Of DGTD Registration<!-- <span class="red">*</span> --></label>
                                                        <div class="input-group date" data-provide="datepicker">
                                                            <input type="text" class="form-control" id="validToDGTD" name="oeList[1].validTo">
                                                            <div class="input-group-addon">
                                                                <span class="glyphicon glyphicon-th"></span>
                                                            </div>
                                                        </div>
                                                        </div>
                                                    </div>
                                                    
                                                    </div>
                                                    <div class="clearfix"></div>
		                                               <div class="form-group partnerOrgEEApproveDiv" id="partnerOrgEEApproveDiv_1">
		                                                    <label class="col-sm-2"><b>Add EE Comment:</b> </label>
															 <div class="col-sm-4">
															     <textarea class="form-control partnerOrgRemark changeComment"  name="oeList[1].eeComment" id="ee_remark_1" maxlength="255" ></textarea>
														    </div>
														<div class="col-sm-6">
															<label><b>Select Status:</b> </label> 
															<input type="radio"  value="Y" id="ee_approve_DGTD" name="oeList[1].isEEApproved" class="partnerOrgStatusBtn changeButton" checked />Approve 
															<!-- <input type="radio" name="isApproved" value="N" id="rejectBtn" class="statusBtn changeButton" /> Reject -->
														    <input type="radio"  value="C" id="ee_clarify_DGTD"  name="oeList[1].isEEApproved" class="partnerOrgStatusBtn changeButton"  /> Clarification
														</div>
													  </div>
													  <div class="form-group partnerOrgCEApproveDiv" id="partnerOrgCEApproveDiv_1">
		                                                    <label class="col-sm-2"><b>Add CE Comment:</b> </label>
															 <div class="col-sm-4">
															     <textarea class="form-control partnerOrgRemark changeComment" name="oeList[1].ceComment" id="ce_remark_1" maxlength="255" ></textarea>
														    </div>
														<div class="col-sm-6">
															<label><b>Select Status:</b> </label> 
															<input type="radio"  value="Y" id="ce_approve_DGTD"  name="oeList[1].isCEApproved" class="partnerOrgStatusBtn changeButton" checked />Approve 
															<!-- <input type="radio" name="isApproved" value="N" id="rejectBtn" class="statusBtn changeButton" /> Reject -->
														    <input type="radio"  value="C" id="ce_clarify_DGTD" name="oeList[1].isCEApproved" class="partnerOrgStatusBtn changeButton"  /> Clarification
														</div>
													  </div>
                                                    </div>
                                                    <div class="col-sm-12 text-center positionABS" style="margin-bottom:10px;">
		                                        <button class="btn-all btn btn-info k-tabstrip-prev btnPrevious pull-left tab-li-prev">Previous</button>
		                                      <span id="formDGSDDivId" class="">
		                                        <button class="btn btn-info save partnerOrgActionBtn" onclick="return submitIt('partnerOrgOEForm','saveOrgOtherDetails','partnerOrgOEDResp');">Save</button>
		                                       <button class="btn btn-info cancel partnerOrgActionBtn"  id="cancelOEBtnId">Reset</button>
		                                       </span>
		                                        <button class="btn-all btn btn-info k-tabstrip-next btnNext pull-right tab-li-next">Next</button>
		                                    </div>
                                                    </sf:form>
                                                </div>
                                                <!-- tab inside tab fields 12  -->
											    <!-- tab for iso certification -->
											                                                    <div>
                                                 <sf:form id="partnerOrgCertificationForm" modelAttribute="partnerOrgCertification" method="POST">
                                                  <div class="form-group">
			                                        <div class="col-sm-12">
			                                        <h4>
			                                          <b>Factory ISO Certification Details</b></h4>
			                                        	<hr>
														</div>
														</div>	
			                                       
                                                    <div class="form-group partnerOrgTabs">
                                                    <div class="col-sm-3" style="display: none;">
                                                            <div class="styled-input">
                                                            <input type="hidden" id="bPartnerId" class="bPartnerId" name="partner.bPartnerId" required />
                                                            <input type="hidden" class="partnerOrgId" id="partnerOrgId" name="partnerOrg.partnerOrgId" required />
                                                            <input type="hidden" id="isoCertificationId"  name="partnerOrgCertificateId" required />
                                                              <input type="hidden" id="isActive"  name="isActive" value="Y" required />
<!--                                                             <input type="checkbox" id="isActive" name="isActive" style="display: none;" checked="checked" value="Y" />
 -->                                                       </div>
                                                     </div>
                                                        <div class="col-sm-3">
                                                            <input type="checkbox" id="isNotApplicable" name="isNotApplicable" />
                                                            <label>Not Applicable</label>
                                                            <span></span>
                                                        </div>
                                                    </div>
                                                    <div class="showIsoFields">
                                                    <div class="form-group partnerOrgTabs">
                                                        <div class="col-sm-4">
                                                            <div class="styled-input">
                                                                <input type="text" id="isoName" name="isoName" class="requiredField" maxlength="100"/>
                                                                <label>Name of ISO Standard<span class="red">*</span></label>
                                                                <span></span>
                                                            </div>
                                                        </div>

                                                        <div class="col-sm-4">
                                                            <div class="styled-input">
                                                                <input type="text" id="isoCertifyingAuthority" name="isoCertifyingAuthority" class="requiredField requiredalphabeticsWithSpace" />
                                                                <label>ISO Certifying Authority<span class="red">*</span></label>
                                                                <span></span></div>
                                                        </div>
                                                           <div class="col-sm-4">
																<label>Validity Date of ISO Certification<!-- <span class="red">*</span> --></label>
																<div class="input-group date" data-provide="datepicker">
																	<input type="text" class="form-control"
																		id="isoValidityDate" name="isoValidityDate">
																	<div class="input-group-addon">
																		<span class="glyphicon glyphicon-th"></span>
																	</div>
																</div>
															</div>
                                                        <div class="col-sm-4">
                                                            <div class="styled-input">
                                                                <input type="text" id="isoCertificationNo" name="isoCertificationNo" class="requiredField onlyNumber" />
                                                                <label>ISO Certificate Number<span class="red">*</span></label>
                                                                <span></span>
                                                                </div>
                                                        </div>
                                                        </div>
                                                       <div class="form-group partnerOrgTabs">
                                                        <div class="col-sm-3">
															<label>Attach ISO Certificate Copy <span
																class="red">*</span></label>
																<div class="input-group partnerOrgTabs">
															    <input type="file" id="certificateCopyId" data-id="certificateCopy" data-anchor="a_certificateCopy" class="form-control uploadFile requiredFile" />
																<input  type="hidden" id="certificateCopy" name="certificateCopy.attachmentId" class="form-control " />
																 <span class="input-group-btn">
																 <button  class="btn btn-default certificateCopy fileDeleteBtn" onclick="return submitWithThreeParam('deleteAttachmentById','PartnerOrgCertification','certificateCopy','certificateCopy','certificateCopyDeleteResp');" disabled="disabled"><i class="fa fa-times"></i></button>
																 </span>
																 </div>
																 <a class="col-sm-12 filelabel" data-url="<%=request.getContextPath()%>/download" id="a_certificateCopy"></a>
							                            </div>
                                                       </div>
                                                    </div>
                                                  <div class="clearfix"></div>
				                                               <div class="form-group partnerOrgEEApproveDiv" >
		                                                    <label class="col-sm-2"><b>Add EE Comment:</b> </label>
															 <div class="col-sm-4">
															     <textarea class="form-control partnerOrgRemark changeComment"  name="eeComment" id="eeRemark" maxlength="255" ></textarea>
														    </div>
														<div class="col-sm-6">
															<label><b>Select Status:</b> </label> 
															<input type="radio"  value="Y" id="eeApproveBtn" name="isEEApproved" class="partnerOrgStatusBtn changeButton" checked />Approve 
															<!-- <input type="radio" name="isApproved" value="N" id="rejectBtn" class="statusBtn changeButton" /> Reject -->
														    <input type="radio"  value="C" id="eeClarification"  name="isEEApproved" class="partnerOrgStatusBtn changeButton"  /> Clarification
														</div>
													  </div>
													  <div class="form-group partnerOrgCEApproveDiv" >
		                                                    <label class="col-sm-2"><b>Add CE Comment:</b> </label>
															 <div class="col-sm-4">
															     <textarea class="form-control partnerOrgRemark changeComment" name="ceComment" id="ceRemark" maxlength="255" ></textarea>
														    </div>
														<div class="col-sm-6">
															<label><b>Select Status:</b> </label> 
															<input type="radio"  value="Y" id="ceApproveBtn"  name="isCEApproved" class="partnerOrgStatusBtn changeButton" checked />Approve 
															<!-- <input type="radio" name="isApproved" value="N" id="rejectBtn" class="statusBtn changeButton" /> Reject -->
														    <input type="radio"  value="C" id="ceClarification" name="isCEApproved" class="partnerOrgStatusBtn changeButton"  /> Clarification
														</div>
													  </div>
                                                  
                                              <div class="col-sm-12 text-center positionABS" style="margin-bottom:10px;">
		                                        <button class="btn-all btn btn-info k-tabstrip-prev btnPrevious pull-left tab-li-prev">Previous</button>
		                                      <span id="formIsoDivId">
		                                        <button class="btn btn-info save partnerOrgActionBtn" onclick="return submitIt('partnerOrgCertificationForm','saveOrgCertificate','partnerOrgCertificationResp');">Save</button>
		                                      
		                                      <button class="btn btn-info cancel partnerOrgActionBtn"  id="cancelISOBtnId">Reset</button>
		                                      </span>
		                                        <button class="btn-all btn btn-info k-tabstrip-next btnNext pull-right tab-li-next">Next</button>
		                                    </div>
		                                    </sf:form>
                                                </div>
											    <!-- tab closed for certification -->

                                                <!-- tab inside tab fields 13  -->
                                             <div>
                                                 <div class="form-group">
			                                        <div class="col-sm-12">
			                                        <h4><b>Factory Inspection Report</b></h4>
			                                        <hr>
			                                        </div>
			                                    </div>
                                                	<div class="form-group" style="margin-top:20px;"><div class="col-sm-12">
                                                    <sf:form id="orgInspectionForm" modelAttribute="partnerOrgInspection" method="POST">
                                                         <div class="form-group" style="display: none;">
                                                         <input type="hidden" name="orgInspectionId" id="orgInspectionId"/>
                                                         <input type="hidden" class="partnerOrgId" name="partnerOrg.partnerOrgId" id="partnerOrgId" required />
                                                         <input type="hidden" id="bPartnerId" class="bPartnerId"  name="partner.bPartnerId" />
                                                         <input type="hidden" name="isActive" value="Y" />
                                                         </div>
														<div class="form-group" style="margin-top: 20px;">
															<div class="col-sm-12">
																<label class="checkbox-inline">
																<input type="checkbox" class="validCheckbox" name="isNoteReceived" id="isNoteReceived" /> Whether note received from Committee.</label>
															</div>
														</div>
														<div class="form-group" style="margin-top: 20px;">
															<div class="col-sm-12">
																<label class="checkbox-inline">
																<input type="checkbox" class="validCheckbox" name="isCapableForManufacturing" id="isCapableForManufacturing" /> Whether the factory/works is capable of manufacturing the items stated.</label>
															</div>
														</div>
														<div class="form-group" style="margin-top: 20px;">
															<div class="col-sm-12">
																<label class="checkbox-inline">
																<input type="checkbox" class="validCheckbox" name="isAdequetTesting" id="isAdequetTesting" /> Whether the factory/works is having adequate testing facility for items.</label>
															</div>
														</div>
														<div class="form-group" style="margin-top: 20px;">
															<div class="col-sm-12">
																<label class="checkbox-inline">
																<input type="checkbox" class="validCheckbox" name="isFinanciallyCapable" id="isFinanciallyCapable" /> Whether the manufacturer is having adequate financial resources/capacity to execute the order.</label>
															</div>
														</div>
														<div class="form-group" style="margin-top: 20px;">
															<div class="col-sm-12">
																<label class="checkbox-inline">
																<input type="checkbox" class="validCheckbox" name="isAllInspectionReportFilled" id="isAllInspectionReportFilled" /> Whether all required details in factory inspection report are filled.</label>
															</div>
														</div>
														<div class="form-group" style="margin-top: 20px;">
															<div class="col-sm-12">
																<label class="checkbox-inline">
																<input type="checkbox" class="validCheckbox" name="isMachineWorking" id="isMachineWorking" /> Whether all the machines listed in the list given by the manufacturer are in working condition.</label>
															</div>
														</div>
														<div class="form-group" style="margin-top: 20px;">
															<div class="col-sm-12">
																<label class="checkbox-inline">
																<input type="checkbox" class="validCheckbox" name="isCalibrationCertified" id="isCalibrationCertified" /> Whether all the testing equipments are having valid calibration certificate.</label>
															</div>
														</div>
														<div class="form-group" style="margin-top: 20px;">
															<div class="col-sm-12">
																<label class="checkbox-inline">
																<input type="checkbox" class="validCheckbox"  name="isOtherItemsManufactured" id="isOtherItemsManufactured" /> Whether other items are manufactured by the Tender.</label>
															</div>
														</div>

														<div class="form-group partnerOrgEEApproveDiv" >
		                                                    <label class="col-sm-2"><b>Add EE Comment:</b> </label>
															 <div class="col-sm-4">
															     <textarea class="form-control partnerOrgRemark changeComment"  name="eeComment" id="eeRemark" maxlength="255" ></textarea>
														    </div>
														<div class="col-sm-6">
															<label><b>Select Status:</b> </label> 
															<input type="radio"  value="Y" id="eeApproveBtn" name="isEEApproved" class="partnerOrgStatusBtn changeButton" checked />Approve 
															<!-- <input type="radio" name="isApproved" value="N" id="rejectBtn" class="statusBtn changeButton" /> Reject -->
														    <input type="radio"  value="C" id="eeClarification"  name="isEEApproved" class="partnerOrgStatusBtn changeButton"  /> Clarification
														</div>
													  </div>
													  <div class="form-group partnerOrgCEApproveDiv" >
		                                                    <label class="col-sm-2"><b>Add CE Comment:</b> </label>
															 <div class="col-sm-4">
															     <textarea class="form-control partnerOrgRemark changeComment" name="ceComment" id="ceRemark" maxlength="255" ></textarea>
														    </div>
														<div class="col-sm-6">
															<label><b>Select Status:</b> </label> 
															<input type="radio"  value="Y" id="ceApproveBtn"  name="isCEApproved" class="partnerOrgStatusBtn changeButton" checked />Approve 
															<!-- <input type="radio" name="isApproved" value="N" id="rejectBtn" class="statusBtn changeButton" /> Reject -->
														    <input type="radio"  value="C" id="ceClarification" name="isCEApproved" class="partnerOrgStatusBtn changeButton"  /> Clarification
														</div>
													  </div>
                                                <div class="col-sm-12 text-center positionABS" style="margin-bottom:10px;">
		                                        <button class="btn-all btn btn-info k-tabstrip-prev btnPrevious pull-left tab-li-prev">Previous</button>
		                                        <button class="btn btn-info save partnerOrgActionBtn" onclick="return submitIt('orgInspectionForm','saveOrgInspection','orgInspectionResp');">Save</button>
		                                        <button class="btn btn-info cancel partnerOrgActionBtn"  id="cancelOrgInspection">Reset</button>
		                                        <button class="btn-all btn btn-info k-tabstrip-next btnNext pull-right tab-li-next">Next</button>
		                                        </div>
                                                </sf:form>
												</div></div>
                                                </div>


                                             
                                                <!-- tab inside tab fields 13  -->

                                                

                                            </div>
                                            <!-- tab inside tab -->

                                        </div>
 --%>                                        <!--fields of field group 6  -->
                                        <!-- tab inside tab PBG Details -->
                                               <%--  <div>
                                                <sf:form id="partnerOrgPBGForm" modelAttribute="partnerOrgPBG" method="POST">
												<div class="detailscont">
											<div class="col-md-12">
												<label class="col-xs-6"><h4>XXXX</h4></label> <label
													class="col-xs-6 ">XXXX</label>
											</div>
											<div class="col-md-12">
												<label class="col-xs-6">XXXX</label> <label
													class="col-xs-6 ">XXXX</label>
											</div>
										</div>
												<!-- <div class="form-group">
													<div class="col-sm-12">
														<h4>
															<b>Permanent Bank Guarantee Details</b>
														</h4>
														<hr>
													</div>
												</div> -->
												<div class="form-group partnerTabs">
													<div class="col-sm-12">
														<h4 class="col-xs-6 nopadding">
															<b>Permanent Bank Guarantee Details</b>
														</h4>
														
														<div class="col-xs-6 text-right nopadding"
															style="margin-top: 10px;">
															<button class="btn btn-default" id="addPBGBtnId">
																<span class="glyphicon glyphicon-plus-sign"></span>Add
															</button>
															<!-- <button class="btn btn-default" id="editPBGBtnId">
																<span class="glyphicon glyphicon-pencil"></span>Edit
															</button> -->
															<button class="btn btn-default"
																id="deletePBGBtnId"
																onclick="return submitWithParam('deleteOrgPBG','PBGId','partnerPBGDelResp');">
																<span class="glyphicon glyphicon-trash"></span>Delete
															</button>
														</div>
														<hr>
													</div>
												</div>
												
												<div class="form-group partnerTabs">
                                         <div class="col-sm-3" style="display: none;">
                                                            <div class="styled-input">
                                                            <input type="hidden" id="bPartnerId" class="bPartnerId" name="partner.bPartnerId"  required />
                                                             <!-- <input type="hidden" class="partnerOrgId" id="partnerOrgId"  name="partnerOrg.partnerOrgId" required /> -->
                                                             <input type="hidden" id="PBGId" name="partnerOrgPbgId" required />
                                                              <input type="hidden" id="isActive"  name="isActive" value="Y" required />
                                                           </div>
                                                     </div>
                                                  </div>
                                                    <div class="form-group partnerTabs">
                                                        <div class="col-sm-3">
                                                            <input type="checkbox" id="isNotApplicable" name="isNotApplicable" />
                                                            <label>Not Applicable</label>
                                                            <span></span>
                                                        </div>
                                                    </div>
                                                   <div class="showPBGField">
                                                    <div class="form-group partnerTabs">
                                                        <div class="col-sm-4">
                                                    		<div class="styled-input">
                                                        		<select id="factoryName" name="partnerOrg.partnerOrgId" /></select>
                                                        		<label>Factory Name<span class="red">*</span></label>
                                                        		<span></span>
                                                    		</div>
                                               			 </div>
                                                        
                                                        <div class="col-sm-4">
                                                            <div class="styled-input">
                                                                <input type="text" id="bankGuaranteeNo" name="bankGuaranteeNo" class="requiredField onlyNumber" />
                                                                <label>Bank Guarantee Number<span class="red">*</span></label>
                                                                <span></span>
                                                            </div>
                                                        </div>

                                                        <div class="col-sm-4">
                                                            <div class="styled-input">
                                                                <input type="text" id="pbgAmount" name="pbgAmount" title="Permanent Bank Guarantee Amount" class="requiredField onlyNumber"  />
                                                                <label>PBG Amount(Rs)<span class="red">*</span></label>
                                                                <span></span></div>
                                                        </div>

                                                        
													</div>
                                                    <div class="form-group ">
                                                    <div class="col-sm-4 partnerTabs">
                                                            <!-- <div class="styled-input">
                                                                <input type="text" id="issueDate" name="issueDate" required />
                                                                <label>Issue date<span class="red">*</span></label>
                                                                <span></span></div> -->
                                                                 <label>Issue date<!-- <span class="red">*</span> --></label>
                                                                                <div class="input-group date" data-provide="datepicker">
                                                                                    <input type="text" class="form-control" id="issueDate" name="issueDate" class="">
                                                                          <div class="input-group-addon">
                                                                       <span class="glyphicon glyphicon-th"></span>
                                                                  </div>
                                                        </div>
                                                    </div>
                                                      <div class="col-sm-4 partnerTabs">
                                                            <!-- <div class="styled-input">
                                                                <input type="text" id="validityDate" name="validityDate" required />
                                                                <label>Validity date<span class="red">*</span></label>
                                                                <span></span></div>
                                                         -->
                                                         <label>Validity date<!-- <span class="red">*</span> --></label>
                                                                                <div class="input-group date" data-provide="datepicker">
                                                                                    <input type="text" class="form-control" id="validityDate" name="validityDate">
                                                                          <div class="input-group-addon">
                                                                       <span class="glyphicon glyphicon-th"></span>
                                                                         </div>
                                                                        </div> 
                                                          </div>
                                                        <div class="col-sm-4">
                                                            <label>Bank Guarantee Copy<!-- <span class="red">*</span> --></label>
                                                            <div class="input-group partnerTabs">
                                                            <input type="file" id="bankFileId" data-id="bankGauranteeCopy" data-anchor="a_bankGauranteeCopy" class="form-control uploadFile"  />
                                                             <input type="hidden" id="bankGauranteeCopy" name="bankGauranteeCopy.attachmentId" class="form-control" />
                                                            <span class="input-group-btn">
                                                            <button  class="btn btn-default bankGauranteeCopy" onclick="return submitWithParam('deleteAttachment','bankGauranteeCopy','pbgAttachmentDeleteResp');" disabled="disabled"><i class="fa fa-times"></i></button>
                                                            </span>
                                                            </div>
                                                            <a class="col-sm-12 filelabel" data-url="<%=request.getContextPath()%>/download" id="a_bankGauranteeCopy"></a>
                                                        </div>
								
											
                                                    </div>
                                                  
                                                    </div>
                                                    <div class="clearfix"></div>
		                                               <div class="form-group approveDiv" >
		                                                    <label class="col-sm-2"><b>Add Comment:</b> </label>
																<div class="col-sm-4">
																	<textarea class="form-control remark" id="remark" name="remark" ></textarea>
																</div>
																<div class="col-sm-6">
																	<label><b>Select Status:</b> </label> 
																	<input type="radio" name="isApproved" value="Y" id="approveBtn"  class="statusBtn" checked />Approve 
																	<input type="radio" name="isApproved" value="N" id="rejectBtn" class="statusBtn" /> Reject
																</div>
													  </div>
                                                    <div class="col-sm-12 text-center positionABS" style="margin-bottom:10px;">
		                                        <button class="btn-all btn btn-info k-tabstrip-prev btnPrevious pull-left">Previous</button>
		                                       <span id="PBGFormId" class="">
		                                        <button class="btn btn-info  save disableBtn" onclick="return submitIt('partnerOrgPBGForm','saveOrgPBG','partnerOrgPBGResp');">Save</button>
		                                        <button class="btn btn-info cancel disableBtn" id="cancelPBGBtnId"> Reset</button>
		                                          </span>
		                                        <button class="btn-all btn btn-info k-tabstrip-next btnNext pull-right">Next</button>
		                                    </div>
                                                    </sf:form>
                                                </div>
                                                 --%>
                                                <!-- tab inside tab PBG Details -->
                                                
                                         <!--fields of field group 7  -->

					

						      <%--  <div>
						                  <div class="detailscont">

											<div class="col-md-12">
												<label class="col-xs-6"><h4>XXXX</h4></label> <label
													class="col-xs-6 ">XXXX</label>
											</div>
											<div class="col-md-12">
												<label class="col-xs-6">XXXX</label> <label
													class="col-xs-6 ">XXXX</label>
											</div>
										</div>


									<!-- <div class="form-group">
=======
										<div class="col-md-12">
                                          <label class="col-xs-6">
												<h4>Ankush Agarwal</h4>
											</label> <label class="col-xs-6 ">7524536745</label>
                                         </div>
                                        <div class="col-md-12">
                                       <label class="col-xs-6">Annkush.a@novelerp.com</label> <label class="col-xs-6 ">Andheri Mumbai</label>
                                         </div>
                                         </div> -->
                                      <div class="form-group">



										<div class="col-sm-12">


											<h4>
												<b>Financial Details</b>
											</h4>


											<hr>


										</div>


									</div>
									<sf:form id="partnerFinancialDetailsForm" modelAttribute="partnerFinancialAttachments" method="POST">
										<div class="col-sm-4">
                                            <div class="form-group">
                                                   <div class="col-sm-12">


													<h4>
														<b>Profit and Loss Account <span class="red">*</span></b>
													</h4>


													<hr>
                                                   </div>


											</div>
                                      <div class="form-group col-sm-12 nopadd">
                                      <div style="display: none;">
                                          


												</div>

                                               <div class="col-sm-12 nopadding">
                                               	<div class="form-group frmgrpbrd">
												 <div class="col-sm-5 nopadd partnerTabs">
                                                     <select class="form-control loadYears requiredField dropDown"  id="pnlYearId_0" name="financialAttachments[0].financialYear.financialYearId" onchange="getYearValidDate('pnlYearId_0','pnlYearValidFrom_0','pnlYearValidTo_0'); checkYearValue('pnlYearId_0','pnlYearId_1','pnlYearId_2');">
                                                     </select>
									                     <input type="hidden" id="pnlYearValidFrom_0" name="financialAttachments[0].validFrom" />
									                      <input type="hidden" id="pnlYearValidTo_0" name="financialAttachments[0].validTo" />

									                      <!-- <input type="hidden" name="financialAttachments[0].attachment.fileName" id="pnlFileName_0"  /> --> 
                                                      
                                                        </div>
												<div class="col-sm-7 nopadd" >


													<div class="input-group partnerTabs">
													<input type="hidden" id="bPartnerId" class="bPartnerId" name="financialAttachments[0].partner.bPartnerId" />
                                                    <input type="hidden" name="financialAttachments[0].isActive" value="Y" />
                                                     <input type="hidden" name="financialAttachments[0].finacialType" value="" class="pnlType" id="pnlType_0"/>
                                                      <input type="hidden" id="pnlFinancialAttachmentId_0" name="financialAttachments[0].financialAttachmentId" class="form-control" /> 

                                                      <input type="file" id="pnlFileId_0" data-id="pnlFile_0" data-name="pnlFileName_0" data-anchor="a_pnlFile_0" class="form-control uploadFile requiredFile"  /> 
                                                      <input type="hidden" id="pnlFile_0" name="financialAttachments[0].attachment.attachmentId" class="form-control" /> 
                                                      <span class="input-group-btn">
                                                      <button class="btn btn-default pnlFile_0 fileDeleteBtn"	onclick="return submitWithThreeParam('deleteAttachmentById','PartnerFinancialAttachment','attachment','pnlFile_0','firstPNLAttachmentDeleteResp');" disabled="disabled"><i class="fa fa-times"></i>
													   </button>
                                                         </span>
                                                          </div>
                                                           <a class="col-sm-12 filelabel" data-url="<%=request.getContextPath()%>/download" id="a_pnlFile_0"></a>
                                                     
                                                 </div>
                                                 
                                                        <!-- <label class="col-sm-12 filelabel pnlFileName_0" >No File Found</label> -->
                                                      <!-- <div class="clearfix"></div> -->
		                                               <div class="approveEEDiv" id="approveEEDiv_0" >
		                                                    <div class="col-sm-12 nopadding">
																	<label><b>EE Status:</b> </label> 
																	<input type="radio"  value="Y"  name="financialAttachments[0].isEEApproved" id="ee_isApproved_0_1" class="statusBtn changeButton" checked />Approve 
																	<!-- <input type="radio" name="financialAttachments[0].isApproved" value="N"  id="isApproved_0_2" class="statusBtn" /> Reject -->
																    <input type="radio"  value="C" name="financialAttachments[0].isEEApproved"  class="statusBtn changeButton"  id="ee_isApproved_0_2" /> Clarification
																</div>
													  </div>
													  <div class="approveCEDiv" id="approveCEDiv_0">
		                                                    <div class="col-sm-12 nopadding">
																	<label><b>CE Status:</b> </label> 
																	<input type="radio"  value="Y"  name="financialAttachments[0].isCEApproved" id="ce_isApproved_0_1" class="statusBtn changeButton" checked />Approve 
																	<!-- <input type="radio" name="financialAttachments[0].isApproved" value="N"  id="isApproved_0_2" class="statusBtn" /> Reject -->
																    <input type="radio"  value="C" name="financialAttachments[0].isCEApproved" class="statusBtn changeButton"  id="ce_isApproved_0_2" /> Clarification
																</div>
													  </div>
                                                </div>
                                                </div>
                                                <div class="clearfix"></div>                                                
                                                
                                                <div class="col-sm-12 nopadding">
                                                <div class="form-group frmgrpbrd">
												


												<div class="col-sm-5 nopadd partnerTabs">


													<select class="form-control loadYears requiredField dropDown" id="pnlYearId_1" name="financialAttachments[1].financialYear.financialYearId" onchange="getYearValidDate('pnlYearId_1','pnlYearValidFrom_1','pnlYearValidTo_1');  checkYearValue('pnlYearId_1','pnlYearId_0','pnlYearId_2');">
                                                     </select> 
											               <input type="hidden" id="pnlYearValidFrom_1" name="financialAttachments[1].validFrom" /> 
											               <input type="hidden" id="pnlYearValidTo_1" name="financialAttachments[1].validTo" />

											               <!-- <input type="hidden" name="financialAttachments[1].attachment.fileName" id="pnlFileName_1" /> -->
                                                 
                                                  </div>
                                                  <div class="col-sm-7 nopadd">


													<div class="input-group partnerTabs">
													<input type="hidden" id="bPartnerId" class="bPartnerId" name="financialAttachments[1].partner.bPartnerId" />
                                                           <input type="hidden" name="financialAttachments[1].finacialType" class="pnlType" value="" id="pnlType_1"/>
														<input type="hidden" name="financialAttachments[1].isActive"  value="Y" />
                                                        <input type="hidden" id="pnlFinancialAttachmentId_1" name="financialAttachments[1].financialAttachmentId" class="form-control" />

														<input type="file" id="pnlFileId_1" data-id="pnlFile_1" data-name="pnlFileName_1" data-anchor="a_pnlFile_1" class="form-control uploadFile requiredFile" />

                                                       <input type="hidden" id="pnlFile_1" name="financialAttachments[1].attachment.attachmentId" class="form-control" /> 


														<span class="input-group-btn">
                                                      <button class="btn btn-default pnlFile_1 fileDeleteBtn" onclick="return submitWithThreeParam('deleteAttachmentById','PartnerFinancialAttachment','attachment','pnlFile_1','secondPNLAttachmentDeleteResp');" disabled="disabled"><i class="fa fa-times"></i>
														</button>
                                                        </span>


													</div>
                                                   <a class="col-sm-12 filelabel" data-url="<%=request.getContextPath()%>/download" id="a_pnlFile_1"></a>
                                                </div>
                                                   <div class="approveEEDiv" id="approveEEDiv_1">
		                                                    <div class="col-sm-12 nopadding">
																	<label><b>EE Status:</b> </label> 
																	<input type="radio"  value="Y" id="ee_isApproved_1_1"  name="financialAttachments[1].isEEApproved" class="statusBtn changeButton" checked />Approve 
																	<!-- <input type="radio" name="financialAttachments[1].isApproved" value="N"  id="isApproved_1_2" class="statusBtn" /> Reject -->
																     <input type="radio"  value="C" id="ee_isApproved_1_2" name="financialAttachments[1].isEEApproved" class="statusBtn changeButton"   /> Clarification
																</div>
													  </div>
													  <div class="approveCEDiv" id="approveCEDiv_1">
		                                                    <div class="col-sm-12 nopadding">
																	<label><b>CE Status:</b> </label> 
																	<input type="radio"  value="Y" id="ce_isApproved_1_1"  name="financialAttachments[1].isCEApproved" class="statusBtn changeButton" checked />Approve 
																	<!-- <input type="radio" name="financialAttachments[1].isApproved" value="N"  id="isApproved_1_2" class="statusBtn" /> Reject -->
																     <input type="radio"  value="C" id="ce_isApproved_1_2" name="financialAttachments[1].isCEApproved" class="statusBtn changeButton"   /> Clarification
																</div>
													  </div>
                                                  </div>
                                                  </div>
                                                 <!--  <label class="col-sm-12 filelabel pnlFileName_1" >No File Found</label> -->
                                               <div class="clearfix"></div>
												<div class="col-sm-12 nopadding">
												
												<div class="form-group frmgrpbrd">

												<div class="col-sm-5 nopadd partnerTabs">


													<select class="form-control loadYears requiredField dropDown" id="pnlYearId_2" name="financialAttachments[2].financialYear.financialYearId" onchange="getYearValidDate('pnlYearId_2','pnlYearValidFrom_2','pnlYearValidTo_2'); checkYearValue('pnlYearId_2','pnlYearId_1','pnlYearId_0');">


													</select> <input type="hidden" id="pnlYearValidFrom_2" name="financialAttachments[2].validFrom" /> 
													<input type="hidden" id="pnlYearValidTo_2" name="financialAttachments[2].validTo" />

												  <!-- <input type="hidden" name="financialAttachments[2].attachment.fileName" id="pnlFileName_2"/>
												   -->  
												   
												    </div>
												    <div class="col-sm-7 nopadd">


													<div class="input-group partnerTabs">
													<input type="hidden" id="bPartnerId" class="bPartnerId" name="financialAttachments[2].partner.bPartnerId" />
														<input type="hidden" name="financialAttachments[2].isActive" value="Y" />

                                                          <input type="hidden" name="financialAttachments[2].finacialType" class="pnlType" value="" id="pnlType_2"/>
														<input type="hidden" id="pnlFinancialAttachmentId_2" name="financialAttachments[2].financialAttachmentId" class="form-control" />

														 <input type="file" id="pnlFileId_2" data-id="pnlFile_2" data-name="pnlFileName_2" data-anchor="a_pnlFile_2" class="form-control uploadFile requiredFile" />
                                                             <input type="hidden" id="pnlFile_2" name="financialAttachments[2].attachment.attachmentId" class="form-control" /> 


														<span class="input-group-btn">


															<button class="btn btn-default pnlFile_2 fileDeleteBtn" onclick="return submitWithThreeParam('deleteAttachmentById','PartnerFinancialAttachment','attachment','pnlFile_2','thirdPNLAttachmentDeleteResp');" disabled="disabled">
																<i class="fa fa-times"></i>
															</button>


														</span>


													</div>
                                                        <a class="col-sm-12 filelabel" data-url="<%=request.getContextPath()%>/download" id="a_pnlFile_2"></a>
												   </div>
												    <div class="approveEEDiv" id="approveEEDiv_2">
		                                                    <div class="col-sm-12 nopadding">
																	<label><b>EE Status:</b> </label> 
																	<input type="radio"  value="Y" name="financialAttachments[2].isEEApproved" id="ee_isApproved_2_1" class="statusBtn changeButton" checked />Approve 
																	<!-- <input type="radio" name="financialAttachments[2].isApproved" value="N" id="isApproved_2_2" class="statusBtn" /> Reject -->
																    <input type="radio"  value="C"  name="financialAttachments[2].isEEApproved" class="statusBtn changeButton"  id="ee_isApproved_2_2" /> Clarification
																</div>
													  </div>
													  <div class="approveCEDiv" id="approveCEDiv_2">
		                                                    <div class="col-sm-12 nopadding">
																	<label><b>CE Status:</b> </label> 
																	<input type="radio"  value="Y"  id="ce_isApproved_2_1"  name="financialAttachments[2].isCEApproved" class="statusBtn changeButton" checked />Approve 
																	<!-- <input type="radio" name="financialAttachments[2].isApproved" value="N" id="isApproved_2_2" class="statusBtn" /> Reject -->
																    <input type="radio"  value="C"  name="financialAttachments[2].isCEApproved" class="statusBtn changeButton"  id="ce_isApproved_2_2" /> Clarification
																</div>
													  </div>
												    </div>
												    </div>
												   <!--  <label class="col-sm-12 filelabel pnlFileName_2" >No File Found</label> -->
                                               <div class="clearfix"></div>
											</div>
										</div>
										
										<div class="col-sm-4">
											<div class="form-group">


												<div class="col-sm-12">


													<h4>
														<b>Balance Sheet Account <span class="red">*</span></b>
													</h4>


													<hr>


												</div>


											</div>
											<div class="form-group">

												 <div class="col-sm-12 nopadding" >
												 <div class="form-group frmgrpbrd">
												


												<div class="col-sm-5 nopadd partnerTabs">


													<select class="form-control loadYears requiredField dropDown" id="bsaYearId_1" name="financialAttachments[3].financialYear.financialYearId" onchange="getYearValidDate('bsaYearId_1','bsaYearValidFrom_1','bsaYearValidTo_1'); checkYearValue('bsaYearId_1','bsaYearId_2','bsaYearId_3');">
                                                     </select> 
													<input type="hidden" id="bsaYearValidFrom_1" name="financialAttachments[3].validFrom" /> 
													<input type="hidden" id="bsaYearValidTo_1" name="financialAttachments[3].validTo" />
                                                      
													<!-- <input type="hidden" name="financialAttachments[3].attachment.fileName" id="bsaFileName_1"/> -->
                                                    </div>
                                                    <div class="col-sm-7 nopadd">


													<div class="input-group partnerTabs">


														<input type="hidden" name="financialAttachments[3].isActive" value="Y" />
														<input type="hidden" id="bPartnerId" class="bPartnerId" name="financialAttachments[3].partner.bPartnerId" />
                                                          <input type="hidden" name="financialAttachments[3].finacialType" class="bsaType" value="" id="bsaType_1"/>
														<input type="hidden" id="bsaFinancialAttachmentId_1" name="financialAttachments[3].financialAttachmentId" class="form-control" /> 

														<input type="file" id="bsaFileId_1" data-id="bsaFile_1" data-name="bsaFileName_1" data-anchor="a_bsaFile_1" class="form-control uploadFile requiredFile" />

                                                       <input type="hidden" id="bsaFile_1" name="financialAttachments[3].attachment.attachmentId" class="form-control" /> 


														<span class="input-group-btn">
                                                           <button class="btn btn-default bsaFile_1 fileDeleteBtn" onclick="return submitWithThreeParam('deleteAttachmentById','PartnerFinancialAttachment','attachment','bsaFile_1','firstBSAAttachmentDeleteResp');" disabled="disabled">
																<i class="fa fa-times"></i>
															</button>
                                                       </span>


													</div>
                                                 <a class="col-sm-12 filelabel" data-url="<%=request.getContextPath()%>/download" id="a_bsaFile_1"></a>

												</div>
                                                   <!--  <label class="col-sm-12 filelabel bsaFileName_1" >No File Found</label> -->
                                                     <div class="approveEEDiv" id="approveEEDiv_3">
		                                                    <div class="col-sm-12 nopadding">
																	<label><b>EE Status:</b></label> 
																	<input type="radio" value="Y" id="ee_isApproved_3_1" name="financialAttachments[3].isEEApproved" class="statusBtn changeButton" checked />Approve 
																	<!-- <input type="radio" name="financialAttachments[3].isApproved" value="N" id="isApproved_3_2" class="statusBtn" /> Reject -->
																    <input type="radio"  value="C" name="financialAttachments[3].isEEApproved" class="statusBtn changeButton"  id="ee_isApproved_3_2" /> Clarification
																</div>
													  </div>
													  <div class="approveCEDiv" id="approveCEDiv_3">
		                                                    <div class="col-sm-12 nopadding">
																	<label><b>CE Status:</b></label> 
																	<input type="radio" value="Y" id="ce_isApproved_3_1" name="financialAttachments[3].isCEApproved" class="statusBtn changeButton" checked />Approve 
																	<!-- <input type="radio" name="financialAttachments[3].isApproved" value="N" id="isApproved_3_2" class="statusBtn" /> Reject -->
																    <input type="radio"  value="C" name="financialAttachments[3].isCEApproved" class="statusBtn changeButton"  id="ce_isApproved_3_2" /> Clarification
																</div>
													  </div>
                                              </div>
												</div>
												<div class="clearfix"></div>

                                              <div class="col-sm-12 nopadding">
                                              <div class="form-group frmgrpbrd">
												


												<div class="col-sm-5 nopadd partnerTabs">


													<select class="form-control loadYears requiredField dropDown" id="bsaYearId_2" name="financialAttachments[4].financialYear.financialYearId" onchange="getYearValidDate('bsaYearId_2','bsaYearValidFrom_2','bsaYearValidTo_2'); checkYearValue('bsaYearId_2','bsaYearId_1','bsaYearId_3');">
                                                    </select> 
                                                    <input type="hidden" id="bsaYearValidFrom_2" name="financialAttachments[4].validFrom" /> 
                                                    <input type="hidden" id="bsaYearValidTo_2" name="financialAttachments[4].validTo" />

                                                    <!-- <input type="hidden" name="financialAttachments[4].attachment.fileName" id="bsaFileName_2" /> -->
												</div><!-- <label class="col-sm-12 filelabel bsaFileName_2" >No File Found</label> -->
                                                  <div class="col-sm-7 nopadd ">


													<div class="input-group partnerTabs">


														<input type="hidden" name="financialAttachments[4].isActive" value="Y" />
														<input type="hidden" id="bPartnerId" class="bPartnerId" name="financialAttachments[4].partner.bPartnerId" />
														 <input type="hidden" name="financialAttachments[4].finacialType" class="bsaType" value="" id="bsaType_2"/>
                                                        <input type="hidden" id="bsaFinancialAttachmentId_2" name="financialAttachments[4].financialAttachmentId" class="form-control" /> 

														<input type="file" id="bsaFileId_2" data-id="bsaFile_2" data-name="bsaFileName_2" data-anchor="a_bsaFile_2" class="form-control uploadFile requiredFile" />

													   <input type="hidden" id="bsaFile_2" name="financialAttachments[4].attachment.attachmentId" class="form-control" /> 
                                                         <span class="input-group-btn">


															<button class="btn btn-default bsaFile_2 fileDeleteBtn" onclick="return submitWithThreeParam('deleteAttachmentById','PartnerFinancialAttachment','attachment','bsaFile_2','secondBSAAttachmentDeleteResp');" disabled="disabled">
																<i class="fa fa-times"></i>
															</button>


														</span>


													</div><a class="col-sm-12 filelabel" data-url="<%=request.getContextPath()%>/download" id="a_bsaFile_2"></a>
                                                 
												</div>
                                                  <div class="approveEEDiv" id="approveEEDiv_4">
		                                                    <div class="col-sm-12 nopadding">
																	<label><b>EE Status:</b> </label> 
																	<input type="radio"  value="Y" id="ee_isApproved_4_1" name="financialAttachments[4].isEEApproved" class="statusBtn changeButton" checked />Approve 
																	<!-- <input type="radio" name="financialAttachments[4].isApproved" value="N"  id="isApproved_4_2" class="statusBtn" /> Reject -->
																     <input type="radio"  value="C" name="financialAttachments[4].isEEApproved" class="statusBtn changeButton"  id="ee_isApproved_4_2" /> Clarification
																</div>
													  </div>
													  <div class="approveCEDiv" id="approveCEDiv_5">
		                                                    <div class="col-sm-12 nopadding">
																	<label><b>CE Status:</b> </label> 
																	<input type="radio"  value="Y" id="ce_isApproved_4_1" name="financialAttachments[4].isCEApproved" class="statusBtn changeButton" checked />Approve 
																	<!-- <input type="radio" name="financialAttachments[4].isApproved" value="N"  id="isApproved_4_2" class="statusBtn" /> Reject -->
																     <input type="radio"  value="C" name="financialAttachments[4].isCEApproved" class="statusBtn changeButton"  id="ce_isApproved_4_2" /> Clarification
																</div>
													  </div>
                                                  </div>
                                                  </div>
												<div class="clearfix"></div>

                                              <div class="col-sm-12 nopadding">
                                              <div class="form-group frmgrpbrd">
												


												<div class="col-sm-5 nopadd partnerTabs">


													<select class="form-control loadYears requiredField dropDown" id="bsaYearId_3" name="financialAttachments[5].financialYear.financialYearId" onchange="getYearValidDate('bsaYearId_3','bsaYearValidFrom_3','bsaYearValidTo_3'); checkYearValue('bsaYearId_3','bsaYearId_2','bsaYearId_1');">


													</select>
													 <input type="hidden" id="bsaYearValidFrom_3" name="financialAttachments[5].validFrom" />
													  <input type="hidden" id="bsaYearValidTo_3" name="financialAttachments[5].validTo" />
                                                  <!-- <input type="hidden" name="financialAttachments[5].attachment.fileName" id="bsaFileName_3" /> -->
												</div><!-- <label class="col-sm-12 filelabel bsaFileName_3" >No File Found</label> -->
                                                      <div class="col-sm-7 nopadd">


													<div class="input-group partnerTabs">


														<input type="hidden" name="financialAttachments[5].isActive" value="Y" />
														<input type="hidden" id="bPartnerId" class="bPartnerId" name="financialAttachments[5].partner.bPartnerId" />
														<input type="hidden" name="financialAttachments[5].finacialType" class="bsaType" value="" id="bsaType_3"/>
                                                        <input type="hidden" id="bsaFinancialAttachmentId_3" name="financialAttachments[5].financialAttachmentId" class="form-control" /> 
														<input type="file" id="bsaFileId_3" data-id="bsaFile_3" data-name="bsaFileName_3" data-anchor="a_bsaFile_3" class="form-control uploadFile requiredFile" />


														 <input type="hidden" id="bsaFile_3" name="financialAttachments[5].attachment.attachmentId" class="form-control" />


														<span class="input-group-btn">


															<button class="btn btn-default bsaFile_3 fileDeleteBtn" onclick="return submitWithThreeParam('deleteAttachmentById','PartnerFinancialAttachment','attachment','bsaFile_3','thirdBSAAttachmentDeleteResp');" disabled="disabled"><i class="fa fa-times"></i>
															</button>


														</span>


													</div> <a class="col-sm-12 filelabel" data-url="<%=request.getContextPath()%>/download"  id="a_bsaFile_3"></a>
												
												</div>
                                                      <div class="approveEEDiv" id="approveEEDiv_5">
		                                                    <div class="col-sm-12 nopadding">
																	<label><b>EE Status:</b> </label> 
																	<input type="radio"  value="Y" id="ee_isApproved_5_1" name="financialAttachments[5].isEEApproved" class="statusBtn changeButton" checked />Approve 
																	<!-- <input type="radio" name="financialAttachments[5].isApproved" value="N" id="isApproved_5_2" class="statusBtn" /> Reject -->
																    <input type="radio" value="C" name="financialAttachments[5].isEEApproved" class="statusBtn changeButton"  id="ee_isApproved_5_2" /> Clarification
																</div>
													  </div>
													   <div class="approveCEDiv" id="approveCEDiv_5">
		                                                    <div class="col-sm-12 nopadding">
																	<label><b>CE Status:</b> </label> 
																	<input type="radio"  value="Y" id="ce_isApproved_5_1" name="financialAttachments[5].isCEApproved" class="statusBtn changeButton" checked />Approve 
																	<!-- <input type="radio" name="financialAttachments[5].isApproved" value="N" id="isApproved_5_2" class="statusBtn" /> Reject -->
																    <input type="radio" value="C" name="financialAttachments[5].isCEApproved" class="statusBtn changeButton"  id="ce_isApproved_5_2" /> Clarification
																</div>
													  </div>
													  </div>
                                              </div>
											</div>
										</div>
										<div class="col-sm-4 nopadding">
											<div class="form-group">


												<div class="col-sm-12">


													<h4>
														<b>Turnover Details <span class="red">*</span> </b>
													</h4>


													<hr>


												</div>


											</div>


											<div class="form-group frmgrpbrd">


												<div class="col-sm-12 btnmrg2">


													<label>Turnover Certificate for last five years(CA Certified) <span class="red">*</span> </label>
                                                  </div>


												<div class="col-sm-8 btnmrg2">


													<div class="input-group partnerTabs">


														<input type="hidden" name="financialAttachments[6].isActive" value="Y" />
														<input type="hidden" id="bPartnerId" class="bPartnerId" name="financialAttachments[6].partner.bPartnerId" />
                                                        <input type="hidden" name="financialAttachments[6].finacialType" class="tdType" value="" id="tdType_1"/>
														<input type="hidden" id="tdFinancialAttachmentId_1" name="financialAttachments[6].financialAttachmentId" class="form-control" />

														 <input type="file" id="tdFileId_1" data-id="tdFile_1" data-name="tdFileName_1"  data-anchor="a_tdFile_1" class="form-control uploadFile requiredFile"/>



													  <input type="hidden" id="tdFile_1" name="financialAttachments[6].attachment.attachmentId" class="form-control" /> 


														<span class="input-group-btn">


															<button class="btn btn-default tdFile_1 fileDeleteBtn" onclick="return submitWithThreeParam('deleteAttachmentById','PartnerFinancialAttachment','attachment','tdFile_1','loadTDAttachmentDeleteResp');" disabled="disabled">
																<i class="fa fa-times"></i>
															</button>


														</span>
                                                     <!--  <input type="hidden" name="financialAttachments[6].attachment.fileName" id="tdFileName_1" /> -->


													</div>
													
													<!-- <label class="col-sm-12 tdFileName_1 textblue" >No File Found</label> -->
													 <a class="col-sm-12 filelabel"  data-url="<%=request.getContextPath()%>/download"   id="a_tdFile_1"></a>
													
                                                 </div>
                                                  
                                                 <div class="clearfix"></div>


												<div class="col-sm-7 ">
													<label>Latest Annual Turnover Amount in Rs. Lakhs<span class="red">*</span></label>
												</div>


												<div class="col-sm-5 partnerTabs">
                                                    <input type="text" id="tdAmountId" name="financialAttachments[6].amount" class="form-control requiredField onlyNumber" value="0"/>
                                                   </div>
                                                  
                                                <div class="clearfix"></div><br>
												<div>Note: Amount should match with annual turnover in the uploaded document.</div>
                                                 <div class="approveEEDiv" id="approveEEDiv_6">
		                                                    <div class="col-sm-12">
																	<label><b>EE Status:</b> </label> 
																	<input type="radio"  value="Y"  id="ee_isApproved_6_1" name="financialAttachments[6].isEEApproved"  class="statusBtn changeButton" checked />Approve 
																	<!-- <input type="radio" name="financialAttachments[6].isApproved" value="N" id="isApproved_6_2" class="statusBtn"  /> Reject -->
															        <input type="radio"  value="C" name="financialAttachments[6].isEEApproved" class="statusBtn changeButton"  id="ee_isApproved_6_2" /> Clarification
															</div>
												</div>
												 <div class="approveCEDiv" id="approveCEDiv_6">
		                                                    <div class="col-sm-12">
																	<label><b>CE Status:</b> </label> 
																	<input type="radio"  value="Y"  id="ce_isApproved_6_1" name="financialAttachments[6].isCEApproved" class="statusBtn changeButton" checked />Approve 
																	<!-- <input type="radio" name="financialAttachments[6].isApproved" value="N" id="isApproved_6_2" class="statusBtn"  /> Reject -->
															        <input type="radio"  value="C" name="financialAttachments[6].isCEApproved" class="statusBtn changeButton"  id="ce_isApproved_6_2" /> Clarification
															</div>
												</div>
												
										   </div>
                                         </div>
										<div class="col-sm-12 text-center positionABS"
											style="margin- bottom: 10px;">


											<button class="btn-all btn btn-info k-tabstrip-prev btnPrevious pull-left tab-li-prev">Previous</button>



											<button class="btn btn-info save disableBtn" onclick="return submitIt('partnerFinancialDetailsForm','savePartnerFinancialDetails','partnerFinancialDetailsResp');">Save</button>
                                            <button class="btn btn-info cancel disableBtn" id="cancelFinancialBtnId" >Reset</button>

											<button class="btn-all btn btn-info k-tabstrip-next btnNext pull-right tab-li-next">Next</button>
										</div>
									</sf:form>
									<!-- tab inside 

tab -->

								</div>								
 --%>								<!--fields of field group 7  -->

                                       <!--fields of field group 8  -->
                                       <%-- <div>
                                       <div class="detailscont">
											<div class="col-md-12">
												<label class="col-xs-6"><h4>XXXX</h4></label> <label
													class="col-xs-6 ">XXXX</label>
											</div>
											<div class="col-md-12">
												<label class="col-xs-6">XXXX</label> <label
													class="col-xs-6 ">XXXX</label>
											</div>
										</div>
                                      <div id="tabstrip1">
                                        <ul>
                                            <!-- tabs -->
                                            <li class="k-state-active" onclick="return submitWithParam('getPartnerItemManufacturer','bPartnerId','onItemManufacturerTabLoad');">Details Of Manufacturer</li>
                                            <li class="readonly disableTraderTabs" id="tradingItem_itemTabId" onclick="return submitWithParam('getPartnerTradingItem','partnerItemManufacturerId','onTradingItemLoad');">Items</li>
                                            <li class="disableTraderTabs" id="traderFactoryTab " onclick="return submitWithParam('getManufacturerOrg','partnerItemManufacturerId','onpManufacturerOrgTabResp');">Manufacturer Factory Details</li>
                                        </ul>

                                        <!--fields of field group 1  -->
                                        <div>
                                      <sf:form id="partnerManufacturerForm" modelAttribute="partnerManufacturer" method="POST">
                                 
                                        <div class="form-group">
			                                        <div class="col-sm-12">
			                                        <!-- <h4 ><b>Details Of Manufacturer</b></h4> -->
			                                        <h4 class="col-xs-4 nopadding"><b>Details Of Manufacturer</b></h4>
                                        			<div class="col-xs-8 text-right nopadding" style="margin-top:10px;">
                                                    <button class="btn btn-default" id="addItemManufacturerBtnId"><span class="glyphicon glyphicon-plus-sign"></span>Add</button>
                                                    <button class="btn btn-default" id="editItemManufacturerBtnId"><span class="glyphicon glyphicon-pencil"></span>Edit</button>
                                                    <button class="btn btn-default" id="deleteItemManufacturerBtnId"><span class="glyphicon glyphicon-trash"></span>Delete</button></div>
                                        
			                                        <hr>
			                                        </div>
			                                        </div>
			                                        <div id="itemManufacturerDivId" class="">
                                            <div class="form-group partnerManufacturerTabs">
												
												<input type="hidden" id="bPartnerId" class="bPartnerId" name="partner.bPartnerId"  required />
                                                <input type="hidden" id="partnerItemManufacturerId" class="partnerManufacturerId" name="partnerItemManufacturerId" required />
                                                <input type="hidden" id="locationId" name="location.locationId" required />
                                                 <input type="checkbox" style="display:none;"id="LocationIsActive" name="location.isActive" value="Y" checked="checked"/>
                                                 <input type="checkbox" style="display:none;"id="ItemManufactureIsActive" name="isActive" value="Y" checked="checked"/>
                                                <div class="col-sm-3">
                                                    <div class="styled-input">
                                                        <input type="text" id="name" name="name" class="requiredField requiredalphabeticsWithSpace" maxlength="50" />
                                                        <label>Name of Manufacturer<span class="red">*</span></label>
                                                        <span></span>
                                                    </div>
                                                </div>

                                                <div class="col-sm-3">
                                                    <div class="styled-input">
                                                        <input type="text" id="email" name="email" class="requiredField emailaddress" required />
                                                        <label>Email Id<span class="red">*</span></label>
                                                        <span></span>
                                                    </div>
                                                </div>
                                                          <div class="col-sm-3">
                                                    <div class="styled-input">
                                                        <input type="text" id="telephone1" name="telephone1" class=" telephone" maxlength="15" />
                                                        <label>Telephone1(10 digit)<span class="red"></span></label>
                                                        <span></span>
                                                    </div>
                                                </div>

                                                <div class="col-sm-3">
                                                    <div class="styled-input">
                                                        <input type="text" id="telephone2" name="telephone2" class="telephone" maxlength="15" />
                                                        <label>Telephone2(10 digit)</label>
                                                        <span></span>
                                                    </div>
                                                </div>

                                                <div class="col-sm-3">
                                                    <div class="styled-input">
                                                        <input type="text" id="fax1" name="fax1" class="onlyNumber" maxlength="15"/>
                                                        <label>Fax 1(10 digit)</label>
                                                        <span></span>
                                                    </div>
                                                </div>
                                                <div class="col-sm-3">
                                                    <div class="styled-input">
                                                        <input type="text" id="fax2" name="fax2" class="onlyNumber" maxlength="15"/>
                                                        <label>Fax 2(10 digit)</label>
                                                        <span></span>
                                                    </div>
                                                </div>

                                                <div class="col-sm-3">
                                                    <div class="styled-input">
                                                        <input type="text" id="mobileNo" name="mobileNo" class="requiredField telephone" maxlength="10"/>
                                                        <label>Mobile Number(10 digit)<span class="red">*</span></label>
                                                        <span></span>
                                                    </div>
                                                </div>
                                            </div>

                                            <div class="form-group partnerManufacturerTabs">
                                                <div class="col-sm-12">
                                                    <div class="styled-input">
                                                        <textarea type="text" id="address1" name="location.address1" class="requiredField" maxlength="255"></textarea>
                                                        <label>Registered Office Address<span class="red">*</span></label>
                                                        <span></span>
                                                    </div>
                                                </div>
                                            </div>

                                            <div class="clearfix"></div>

                                            <div class="form-group partnerManufacturerTabs">
                                                <div class="col-sm-3">
                                                    <div class="styled-input">
                                                        <input type="text" id="city" name="location.city" class="requiredField requiredalphabeticsWithSpace" maxlength="20" />
                                                        <label>City<span class="red">*</span></label>
                                                        <span></span>
                                                    </div>
                                                </div>

                                                <div class="col-sm-3">
                                                    <div class="styled-input">
                                                        <select id="district" name="location.district.districtId" class="dropDown" required /></select>
                                                        <label>District<span class="red">*</span></label>
                                                        <span></span>
                                                    </div>
                                                </div>

                                                <div class="col-sm-3">
                                                    <div class="styled-input">
                                                        <select id="country" name="location.country.countryId" class="dropDown" required /></select>
                                                        <label>Country<span class="red">*</span></label>
                                                        <span></span>
                                                    </div>
                                                </div>

                                                <div class="col-sm-3">
                                                    <div class="styled-input">
                                                        <select id="region" name="location.region.regionId" class="dropDown" required /></select>
                                                        <label>State<span class="red">*</span></label>
                                                    </div>
                                                </div>
                                            </div>

                                            <div class="clearfix"></div>

                                            <div class="form-group partnerManufacturerTabs">
                                                <div class="col-sm-3">
                                                    <div class="styled-input">
                                                        <input type="text" id="postal" name="location.postal" class="requiredField pincode" maxlength="6"/>
                                                        <label>Pincode<span class="red">*</span></label>
                                                        <span></span>
                                                    </div>
                                                </div>
                                            </div>
                                            </div>
                                            <div class="clearfix"></div>
		                                               <div class="form-group manufacturerEEApproveDiv" >
		                                                    <label class="col-sm-2"><b>Add EE Comment:</b> </label>
																<div class="col-sm-4">
															<textarea class="form-control manufacturerRemark changeComment" name="eeComment" id="eeRemark" maxlength="255" ></textarea>
														</div>
														<div class="col-sm-6">
															<label><b>Select Status:</b> </label> 
															<input type="radio"  value="Y" id="eeApproveBtn" name="isEEApproved" class="manufacturerStatusBtn changeButton" checked />Approve 
															<!-- <input type="radio" name="isApproved" value="N" id="rejectBtn" class="statusBtn changeButton" /> Reject -->
														    <input type="radio"  value="C" id="eeClarification" name="isEEApproved" class="manufacturerStatusBtn changeButton"  /> Clarification
														</div>
													  </div>
													   <div class="form-group manufacturerCEApproveDiv" >
		                                                    <label class="col-sm-2"><b>Add CE Comment:</b> </label>
																<div class="col-sm-4">
															<textarea class="form-control manufacturerRemark changeComment" name="ceComment" id="ceRemark" maxlength="255" ></textarea>
														</div>
														<div class="col-sm-6">
															<label><b>Select Status:</b> </label> 
															<input type="radio"  value="Y" id="ceApproveBtn"  name="isCEApproved" class="manufacturerStatusBtn changeButton" checked />Approve 
															<!-- <input type="radio" name="isApproved" value="N" id="rejectBtn" class="statusBtn changeButton" /> Reject -->
														    <input type="radio"  value="C" id="ceClarification" name="isCEApproved" class="manufacturerStatusBtn changeButton"  /> Clarification
														</div>
													  </div>
													  
                                             <div class="col-sm-12 text-center positionABS" style="margin-bottom:10px;">
		                                        <button class="btn-all btn btn-info k-tabstrip-prev btnPrevious pull-left tab-li-prev">Previous</button>
		                                        <button class="btn btn-info save manufacturerActionBtn" id="saveItemManufacturerBtnId save" onclick="return submitIt('partnerManufacturerForm','savePartnerItemManufacturer','ItemManufacturerResp');">Save</button>
		                                        <button class="btn btn-info cancel manufacturerActionBtn" id="cancelItemManufacturerBtnId">Reset</button>
		                                        <button class="btn-all btn btn-info k-tabstrip-next btnNext pull-right tab-li-next">Next</button>
		                                    </div>
                                            </sf:form>
                                        </div> 
                                        <!--fields of field group 1  -->
                                        <!--fields of field group 2  -->
                                        <div>
                                         <sf:form style="display:none;" id="partnerTradingItemForm" method="POST" modelAttribute="partnerTradingItem">
			                                
			                                <button class="btn btn-default partnerManufacturerTabs">Save</button>
			                            </sf:form>
                                      <sf:form id="itemFormId" modelAttribute="" method="POST">
                                       
                                            <div class="form-group partnerManufacturerTabs">
                                                <div class="col-sm-12">
                                                    <h4><b>Items</b></h4>
                                                    <hr>
                                                </div>
                                            </div>
                                            
                                            

                                            <div class="form-group partnerManufacturerTabs">
                                            <input type="hidden" id="bPartnerId" class="bPartnerId" name="partner.bPartnerId"  required />
                                                <div class="col-sm-12">
                                                    <button class="btn btn-default addItemPopup" id="">Add Item</button>
                                                    <input type="text" style="display: none ;" id="currentItemId" value="">
                                                </div>
                                            </div>
                                            <div class="form-group partnerManufacturerTabs">
                                            <div class="col-sm-12">
	                                                    <table class="table table-bordered tradingItemTable">
                                                        <thead>
                                                            <tr>
                                                                <th data-field="state" data-checkbox="true"></th>
                                                                <th  data-field="id">ItemName</th>
                                                                <th>Action</th>
                                                            </tr>
                                                        </thead>
                                                        <tbody>
                                                                                         
                                                        </tbody>
                                                    </table>
	                                                </div>
	                                                </div>
	                                               <!--  <div class="clearfix"></div>
		                                               <div class="form-group approveDiv" >
		                                                    <label class="col-sm-2"><b>Add Comment:</b> </label>
																<div class="col-sm-4">
															<textarea class="form-control remark changeComment" id="remark"  ></textarea>
														</div>
														<div class="col-sm-6">
															<label><b>Select Status:</b> </label> 
															<input type="radio"  value="Y" id="approveBtn"  class="statusBtn changeButton" checked />Approve 
															<input type="radio" name="isApproved" value="N" id="rejectBtn" class="statusBtn changeButton" /> Reject
														    <input type="radio"  value="C" id="clarification" class="statusBtn changeButton"  /> Clarification
														</div>
													  </div> -->
	                                                 <div class="col-sm-12 text-center positionABS" style="margin-bottom:10px;">
				                                        <button class="btn-all btn btn-info k-tabstrip-prev btnPrevious pull-left tab-li-prev">Previous</button>
				                                        <!-- <button class="btn btn-info disableBtn" id="saveTradingItemBtnId save" onclick="return submitIt('partnerTradingItemForm','savePartnerTradingItem','tradingItemResp');">Save</button>
				                                        <button class="btn btn-info cancel disableBtn" id="cancelTradingItemBtnId">Reset</button> -->
				                                        <button class="btn-all btn btn-info k-tabstrip-next btnNext pull-right tab-li-next">Next</button>
		                                    		</div> 
		                                    </sf:form>
                                        </div>
                                        <!--fields of field group 2  -->
                                    
                                      
                                        <!--fields of field group 8  -->
                                        <!-- fields of manufacture factory Tab -->
                                        <div>
                                                <sf:form id="manufacturerOrgForm" modelAttribute="partnerManufacturerOrg" method="POST">
                                                <div class="form-group partnerManufacturerTabs">
                                        <div class="col-sm-12">
                                        <h4 class="col-xs-6 nopadding"><b>Factory Essential Details</b></h4>
                                        <div class="col-xs-6 text-right nopadding" style="margin-top:10px;">
                                                    <button class="btn btn-default" id="addManufacturerFactoryBtnId"><span class="glyphicon glyphicon-plus-sign"></span>Add</button>
                                                    <!-- <button class="btn btn-default" id="editFactoryBtnId"><span class="glyphicon glyphicon-pencil"></span>Edit</button> -->
                                                    <button class="btn btn-default" id="deleteManufacturerFactoryBtnId" onclick="return submitWithParam('deleteManufacturerOrg','partnerManufacturerOrgId','partnerManufacturerOrgDelResp');"><span class="glyphicon glyphicon-trash"></span>Delete</button></div>
                                        <hr>
                                        </div>
                                        </div>
                                                  
                                                         
                                                    <div class="form-group partnerManufacturerTabs">
                                                    <div class="col-sm-3" style="display: none;">
                                                            <div class="styled-input">
                                                            <input type="hidden"   class="partnerManufacturerId" name="partnerItemManufacturer.partnerItemManufacturerId" required />
                                                            <input type="hidden" id="bPartnerId"  class="bPartnerId" name="partner.bPartnerId" required />
                                                            <input type="hidden" id="partnerManufacturerOrgLocationId"  name="location.locationId" required />
                                                            <input type="hidden" id="partnerManufacturerOrgId"  name="partnerOrgId" required />
                                                            <input type="hidden" id="isActive"  name="isActive"  value="Y" required />
                                                             <input type="hidden" id="isActive"  name="location.isActive" value="Y" required />
                                                           </div>
                                                     </div>
                                                        <div class="col-sm-3">
                                                            <div class="styled-input">
                                                                <input type="text" id="manufacturerOrgName" name="name" class="requiredField requiredalphanumericWithSpaceAndDot" maxlength="100"/>
                                                                <label>Factory Name<span class="red">*</span></label>
                                                                <span></span>
                                                            </div>
                                                        </div>

                                                  <div class="col-sm-3">
                                                            <!-- <div class="styled-input">
                                                                <input type="text" id="estdDate" name="estdDate" required />
                                                                <label>Established date of Factory<span class="red">*</span></label>
                                                                <span></span>
                                                            </div> -->
                                                             <label>Established date of Factory<!-- <span class="red">*</span> --></label>
                                                                                <div class="input-group date Pickdate" data-provide="datepicker">
                                                                                    <input type="text" class="form-control" id="manufacturerOrgEstdDate" name="estdDate">
                                                                                    <div class="input-group-addon">
                                                                                        <span class="glyphicon glyphicon-th"></span>
                                                                                    </div>
                                                             </div>
                                                        </div> 

                                                        <div class="col-sm-3">
                                                            <div class="styled-input">
                                                                <input type="text" id="manufacturerOrgManPower" name="manPower" class="onlyNumber requiredField" maxlength="10"/>
                                                                <label>Number Of Persons<span class="red">*</span> </label>
                                                                <span></span>
                                                            </div>
                                                        </div>

                                                        <div class="col-sm-3">
                                                            <div class="styled-input">
                                                                <input type="text" id="manufacturerOrgInspectionReportNo" name="inspectionReportNo" class="onlyNumber" />
                                                                <label>Factory Inspection Report no.</label>
                                                                <span></span>
                                                            </div>
                                                        </div>
                                                    </div>

                                                    <div class="form-group partnerManufacturerTabs">
                                                        <div class="col-sm-12">
                                                            <div class="styled-input">
                                                                <textarea id="manufacturerOrgAddress1" name="location.address1" class="requiredField" maxlength="255"></textarea>
                                                                <label>Registered Office Address<span class="red">*</span></label>
                                                                <span></span></div>
                                                        </div>
                                                    </div>

                                                    <div class="form-group partnerManufacturerTabs">
                                                        <div class="col-sm-3">
                                                            <div class="styled-input">
                                                                <input type="text" id="manufacturerOrgcity" name="location.city" class="requiredField requiredalphabeticsWithSpace" maxlength="20"/>
                                                                <label>City<span class="red">*</span></label>
                                                                <span></span>
                                                            </div>
                                                        </div>

                                                        <div class="col-sm-3">
                                                            <div class="styled-input">
                                                                <select id="manufacturerOrgDistrict" name="location.district.districtId" class=" dropDown" /></select>
                                                                <label>District<span class="red">*</span></label>
                                                                <span></span>
                                                            </div>
                                                        </div>
                                                        <div class="col-sm-3">
                                                            <div class="styled-input">
                                                                <select id="manufacturerOrgRegion" name="location.region.regionId" class=" dropDown" /></select>
                                                                <label>State<span class="red">*</span></label>
                                                                <span></span>
                                                            </div>
                                                        </div>
                                                        <div class="col-sm-3">
                                                            <div class="styled-input">
                                                                <select id="manufacturerOrgCountry" name="location.country.countryId" class=" dropDown" /></select>
                                                                <label>Country<span class="red">*</span></label>
                                                                <span></span>
                                                            </div>
                                                        </div>

                                                    </div>

                                                    <div class="form-group">
                                                        <div class="col-sm-3">
                                                            <div class="styled-input partnerManufacturerTabs">
                                                             <input type="text" id="manufacturerOrgPostal" name="location.postal" class="requiredField pincode" maxlength="6"/>
                                                             <label>Pincode<span class="red">*</span></label>
                                                                <!-- <select id="postal" name="location.postal" required /></select>
                                                                <label>Pincode<span class="red">*</span></label>
                                                                <span></span> -->
                                                            </div>
                                                        </div>
                                                    <div class="col-sm-3" id="manufacturerfactoryLicenseDivId">
														<div class="styled-input partnerManufacturerTabs">
															<input type="text" id="manufacturerOrgLicenceNo" name="licenceNo" class="requiredField"/> <label>Factory
																License Number<span class="red">*</span></label> 
														</div>
													    </div>
													    <div class="col-sm-3 partnerManufacturerTabs" id="manufacturerFactoryLicenseDateDivId">
														<label>Factory License Validity Date<!-- <span
															class="red">*</span> --></label>
														<div class="input-group date futureDate" data-provide="datepicker">
															<input type="text" class="form-control"
																id="manufacturerOrgLicenceValidityDate" name="licenceValidityDate">
															<div class="input-group-addon">
																<span class="glyphicon glyphicon-th"></span>
															</div>
														</div>
													    </div>
													     <div class="col-sm-3" >
														<label id="manufacturerfactoryLicenseCopyLabel">Attach Factory License Copy<span class="red">*</span></label> 
														
														<div class="input-group partnerManufacturerTabs">               
                                                          <input type="file"  id="manufacturerOrgLicenceFileId" data-id="manufacturerOrgLicenceCopy" data-name="licenceFileName" data-anchor="a_manufacturerOrgLicenceCopy" class="form-control uploadFile requiredFile">
                                                          <input type="hidden" id="manufacturerOrgLicenceCopy" name="licenceCopy.attachmentId" class="form-control" /> 
												                <span class="input-group-btn">
												                    <button class="btn btn-default manufacturerOrgLicenceCopy fileDeleteBtn" onclick="return submitWithThreeParam('deleteAttachmentById','PartnerOrg','partnerOrgId','partnerManufacturerOrgId','licenceCopy','manufacturerOrgLicenceCopy','manufacturerOrgLicenceAttachmentDeleteResp');" disabled="disabled"><i class="fa fa-times"></i></button>
												                </span>
           	                                            </div>
           	                                           <!--  <input type="hidden" id="licenceFileName" name="licenceCopy.fileName" class="form-control" /> -->
           	                                            <a class="col-sm-12 filelabel" data-url="<%=request.getContextPath()%>/download" id="a_manufacturerOrgLicenceCopy"></a>
													</div>
														
                                                    </div>
                                                     <div class="form-group">
                                                     <div class="col-sm-3">
                                                            <div class="styled-input partnerManufacturerTabs">
                                                                <select id="isOrgInspection" name="isFactoryInspected" class="dropDown" onchange="showInspectionFiles();">
                                                                <option value="">Select</option>
                                                                <option value="Y">Yes</option>
																<option value="N">No</option>
                                                                </select>
                                                                <label>Is Factory Inspection Done<span class="red">*</span></label>
                                                                <span></span>
                                                            </div>
                                                        </div>
                                                     <div class="col-sm-3 manufacturerOrgInspectionDone" style="display: none;">
														<label>Attach Machinery List Copy of Factory<span class="red">*</span></label> 
														<div class="input-group partnerManufacturerTabs">       
                                                         <input type="file" id="manufacturerOrgMachinaryFileId" data-id="manufacturerOrgMachinaryListCopy" data-name="machinaryFileName" data-anchor="a_manufacturerOrgMachinaryCopy" class="form-control uploadFile">
											             <input type="hidden" id="manufacturerOrgMachinaryListCopy" name="machinaryListCopy.attachmentId" class="form-control" />
											                <span class="input-group-btn">
											                    <button class="btn btn-default manufacturerOrgMachinaryListCopy fileDeleteBtn" onclick="return submitWithThreeParam('deleteAttachmentById','PartnerOrg','machinaryListCopy','manufacturerOrgMachinaryListCopy','manufacturerOrgMachinaryAttachmentDeleteResp');" disabled="disabled"><i class="fa fa-times"></i></button>
											                </span>
                                                            </div>
													  <!--   <input type="hidden" id="machinaryFileName" name="machinaryListCopy.fileName" class="form-control" /> -->
           	                                            <a class="col-sm-12 filelabel" data-url="<%=request.getContextPath()%>/download" id="a_manufacturerOrgMachinaryCopy"></a>
													</div>
													<div class="col-sm-3 manufacturerOrgInspectionDone" style="display: none;">
														<label>Attach Inspection Report<span class="red">*</span></label> 
														<div class="input-group partnerManufacturerTabs">               
                                                          <input type="file"  id="manufacturerOrgInspectionId" data-id="manufacturerOrgInspectionCopy" data-name="inspectionName" data-anchor="a_manufacturerOrgInspectionCopy" class="form-control uploadFile">
                                                          <input type="hidden" id="manufacturerOrgInspectionCopy" name="inspectionReportCopy.attachmentId" class="form-control" /> 
												                <span class="input-group-btn">
												                    <button class="btn btn-default manufacturerOrgInspectionCopy fileDeleteBtn" onclick="return submitWithThreeParam('deleteAttachmentById','PartnerOrg','inspectionReportCopy','manufacturerOrgInspectionCopy','manufacturerOrgInspectionAttachmentDeleteResp');" disabled="disabled"><i class="fa fa-times"></i></button>
												                </span>
           	                                            </div>
           	                                           <!--  <input type="hidden" id="licenceFileName" name="licenceCopy.fileName" class="form-control" /> -->
           	                                            <a class="col-sm-12 filelabel" data-url="<%=request.getContextPath()%>/download" id="a_manufacturerOrgInspectionCopy"></a>
													</div>
														<div class="col-sm-3 partnerManufacturerTabs manufacturerOrgInspectionDone" style="display: none;">
														<label>Factory Inspection Approval Date<span
															class="red">*</span></label>
														<div class="input-group date" data-provide="datepicker">
															<input type="text" class="form-control" id="manufacturerOrgInspectionDate" name="inspectionDate">
															<div class="input-group-addon">
																<span class="glyphicon glyphicon-th"></span>
															</div>
														</div>
													    </div>
                                                      
													 
                                                    <input type="hidden" id="manufacturerOrgLicenseType"
																name="licenseType" value="STL" />
                                                    </div>
                                                    <div class="form-group">
                                                    <div class="col-sm-3  manufacturerOrgInspectionDone" style="display: none;">
														<label>Attach List of Staff Skilled & Unskilled<span class="red">*</span></label> 
														<div class="input-group partnerManufacturerTabs">               
                                                          <input type="file"  id="manufacturerListOfStaff" data-id="manufacturerStaffListCopy" data-name="staffFileName" data-anchor="a_manufacturerStaffListCopy" class="form-control uploadFile">
                                                          <input type="hidden" id="manufacturerStaffListCopy" name="staffListCopy.attachmentId" class="form-control" /> 
												                <span class="input-group-btn">
												                    <button class="btn btn-default manufacturerStaffListCopy fileDeleteBtn" onclick="return submitWithThreeParam('deleteAttachmentById','PartnerOrg','staffListCopy','staffListCopy','manufacturerStaffCopyDelResp');" disabled="disabled"><i class="fa fa-times"></i></button>
												                </span>
           	                                            </div>
           	                                            <a class="col-sm-12 filelabel" data-url="<%=request.getContextPath()%>/download" id="a_manufacturerStaffListCopy"></a>
													</div>
                                                    </div>
                                                  <div class="clearfix"></div>
                                                    <div class="form-group manufacturerEEApproveDiv" >
		                                                    <label class="col-sm-2"><b>Add EE Comment:</b> </label>
																<div class="col-sm-4">
															<textarea class="form-control manufacturerRemark changeComment" name="eeComment" id="eeRemark" maxlength="255" ></textarea>
														</div>
														<div class="col-sm-6">
															<label><b>Select Status:</b> </label> 
															<input type="radio"  value="Y" id="eeApproveBtn" name="isEEApproved" class="manufacturerStatusBtn changeButton" checked />Approve 
															<!-- <input type="radio" name="isApproved" value="N" id="rejectBtn" class="statusBtn changeButton" /> Reject -->
														    <input type="radio"  value="C" id="eeClarification" name="isEEApproved" class="manufacturerStatusBtn changeButton"  /> Clarification
														</div>
													  </div>
													   <div class="form-group manufacturerCEApproveDiv" >
		                                                    <label class="col-sm-2"><b>Add CE Comment:</b> </label>
																<div class="col-sm-4">
															<textarea class="form-control manufacturerRemark changeComment" name="ceComment" id="ceRemark" maxlength="255" ></textarea>
														</div>
														<div class="col-sm-6">
															<label><b>Select Status:</b> </label> 
															<input type="radio"  value="Y" id="ceApproveBtn"  name="isCEApproved" class="manufacturerStatusBtn changeButton" checked />Approve 
															<!-- <input type="radio" name="isApproved" value="N" id="rejectBtn" class="statusBtn changeButton" /> Reject -->
														    <input type="radio"  value="C" id="ceClarification" name="isCEApproved" class="manufacturerStatusBtn changeButton"  /> Clarification
														</div>
													  </div>  
                                            <div class="col-sm-12 text-center positionABS" style="margin-bottom:10px;">
		                                        <button class="btn-all btn btn-info k-tabstrip-prev  btnPrevious pull-left tab-li-prev">Previous</button>
		                                        <span id="factoryFormDivId" class="">
		                                        <button class="btn btn-info save manufacturerActionBtn" onclick="return submitIt('manufacturerOrgForm','saveManucturerOrgDetails','partnerManuctureOrgResp');">Save</button>
		                                        <button class="btn btn-info cancel manufacturerActionBtn" id="cancelManuctureFactoryDetailsBtnId" >Reset</button>
		                                        </span>
		                                        <button class="btn-all btn btn-info k-tabstrip-next btnNext pull-right tab-li-next">Next</button>
		                                    </div>
                                                   
                                          </sf:form>
                                                    
                                       </div>
                                        </div>
                                     </div>
 --%>                                       <!--  close of fields of manufacture factory Tab -->
                                        <!--fields of field group 9  -->
                                        <div>
                                        <div class="detailscont">
											<div class="col-md-12">
												<label class="col-xs-6"><h4>XXXX</h4></label> <label
													class="col-xs-6 ">XXXX</label>
											</div>
											<div class="col-md-12">
												<label class="col-xs-6">XXXX</label> <label
													class="col-xs-6 ">XXXX</label>
											</div>
										</div>
                                        <sf:form id="partnerBankDetailsForm" modelAttribute="partnerBankDetail" method="POST">
                                        
                                              <div class="col-sm-12" style="margin-bottom:10px;">
			                                        <h4><b>Bank Details</b></h4>
			                                        <hr>
			                                  </div>
			                                  <br>
			                                  <div class="clearfix"></div>
			                                  
			                                 
                                                    <div class="form-group partnerTabs">
                                              <input type="hidden" id="bPartnerId" class="bPartnerId"  name="partner.bPartnerId" required />
                                               <input type="hidden" id="isActive"  name="isActive" value="Y" required />
                                              <input type="hidden" id="partnerBankDetailsId" name="partnerBankDetailId" required />
                                                
                                              <div class="col-sm-3">
										        <div class="styled-input">
										         <select id="bankName" name="bankNameDetails.bankNameDetailsId" class="dropDown" onchange="loadBranchName()">
                                                  </select>
                                                  <label>Bank Name<span class="red">*</span></label>
										           <!-- <select type="text" id="bankName" name="bankName" class="requiredalphabeticsWithSpace requiredField" maxlength="50"/>
										           <label>Bank Name<span class="red">*</span></label>
										           <span></span> -->
										      </div>
										     </div>
										     <div class="col-sm-3">
										        <div class="styled-input">
										        <select id="branchName" name="branchName.bankBranchDetailsId" class="dropDown" onchange="loadIFSCCode()">
                                                  </select>
                                                  <label>Branch Name<span class="red">*</span></label>
										           <!-- <input type="text" id="branchName" name="branchName" class="requiredalphabeticsWithSpace requiredField" maxlength="50"/>
										           <label>Branch Name<span class="red">*</span></label>
										           <span></span> -->
										      </div>
										     </div>
										     <div class="col-sm-3">
										        <div class="styled-input">
										          <input type="text" id="accountNumber" name="accountNumber" class="requiredField onlyNumber" />
										           <label>Account No<span class="red">*</span></label>
										           <span></span>
										        </div>
										     </div>
										     <div class="col-sm-3">
										        <div class="styled-input">
										          <input type="text" id="ifscCode" name="ifscCode" class="requiredField ifscCode" oninput="this.value = this.value.toUpperCase()" maxlength="11" />
										           <label>IFSC Code<span class="red">*</span></label>
										           <span></span>
										        </div>
										     </div>
										     
                                        </div>
                                        <div class="clearfix"></div>
                                        <div class="form-group ">                                             
										     <div class="col-sm-3">
										        <div class="styled-input partnerTabs">
										          <input type="text" id="mobileNo" name="mobileNo" class="requiredField mobile" maxlength="10" />
										           <label>Mobile Number<span class="red">*</span></label>
										           <span></span>
										        </div>
										     </div>
										     <div class="col-sm-3">
										        <div class="styled-input partnerTabs">
										          <input type="text" id="benificaryName" name="benificaryName" class="requiredField requiredalphabeticsWithSpace" maxlength="50"/>
										           <label>Beneficiary Name<span class="red">*</span></label>
										           <span></span>
										        </div>
										     </div>
										      <div class="col-sm-3">
														<label>Bank Details File<span class="red">*</span></label> 
														<div class="input-group partnerTabs">       
                                                         <input type="file" id="bankDetailFileId" data-id="bankDetailFile" data-name="bankDetailFileName" data-anchor="a_bankDetailFile" class="form-control uploadFile requiredFile">
											             <input type="hidden" id="bankDetailFile" name="bankDetailFile.attachmentId" class="form-control" />
											                <span class="input-group-btn">
											                    <button class="btn btn-default bankDetailFile fileDeleteBtn" onclick="return submitWithThreeParam('deleteAttachmentById','PartnerBankDetail','bankDetailFile','bankDetailFile','bankDetailFileDelResp');" disabled="disabled"><i class="fa fa-times"></i></button>
											                </span>
                                                            </div>
           	                                            <a class="col-sm-12 filelabel" data-url="<%=request.getContextPath()%>/download" id="a_bankDetailFile"></a>
													</div>
										     </div>
										     <div class="clearfix"></div>
		                                               <div class="form-group approveEEDiv" >
		                                                    <label class="col-sm-2"><b>Add Comment:</b> </label>
																<div class="col-sm-4">
															      <textarea class="form-control remark changeComment" name="eeComment" id="eeRemark" maxlength="255" ></textarea>
														       </div>
														<div class="col-sm-6">
															<label><b>Select Status:</b> </label> 
															<input type="radio"  value="Y" id="eeApproveBtn"  name="isEEApproved" class="statusBtn changeButton" checked />Approve 
															<!-- <input type="radio" name="isApproved" value="N" id="rejectBtn" class="statusBtn changeButton" /> Reject -->
														    <input type="radio"  value="C" id="eeClarification" name="isEEApproved" class="statusBtn changeButton"  /> Clarification
														</div>
													  </div>
													  <div class="form-group approveCEDiv" >
		                                                    <label class="col-sm-2"><b>Add Comment:</b> </label>
																<div class="col-sm-4">
															      <textarea class="form-control remark changeComment" name="ceComment" id="ceRemark" maxlength="255" ></textarea>
														       </div>
														<div class="col-sm-6">
															<label><b>Select Status:</b> </label> 
															<input type="radio"  value="Y" id="ceApproveBtn"  name="isCEApproved" class="statusBtn changeButton" checked />Approve 
															<!-- <input type="radio" name="isApproved" value="N" id="rejectBtn" class="statusBtn changeButton" /> Reject -->
														    <input type="radio"  value="C" id="ceClarification" name="isCEApproved" class="statusBtn changeButton"  /> Clarification
														</div>
													  </div>
													  <div class="col-sm-12 text-center positionABS" style="margin-bottom:10px;">
		                                        <button class="btn-all btn btn-info k-tabstrip-prev btnPrevious pull-left tab-li-prev">Previous</button>
		                                        <span id="bankformDivId" class="">
		                                        <button class="btn btn-info save disableBtn" onclick="return submitIt('partnerBankDetailsForm','savePartnerBankDetail','partnerBankDetailsResp');">Save</button>
		                                        <button class="btn btn-info cancel disableBtn" id="cancelBankBtnId" >Reset</button>
		                                        </span>
		                                        <button class="btn-all btn btn-info k-tabstrip-next btnNext pull-right tab-li-next">Next</button>
		                                    </div>
		                                    								     </sf:form>
                                        </div>
                                   
                                        
                                        <!--fields of field group 9  -->

<!-- tab inside tab fields 14  -->
                                                <div>
                                            <sf:form id="partnerOrgPaymentForm" modelAttribute="paymentDetail" method="POST">
                                                <div class="detailscont">
											<div class="col-md-12">
												<label class="col-xs-6"><h4>XXXX</h4></label> <label
													class="col-xs-6 ">XXXX</label>
											</div>
											<div class="col-md-12">
												<label class="col-xs-6">XXXX</label> <label
													class="col-xs-6 ">XXXX</label>
											</div>
										</div>
                                                <div class="form-group ">
                                                               <div class="col-sm-12">
																	<h4 class="col-xs-6 nopadding">
																		<b>Payment Details</b>
																	</h4>
                                                                
                                                                <div class="col-xs-6 text-right nopadding"
																	style="margin-top: 10px;">
																	<div class="form-group">
																	<button class="btn btn-default" id="addPaymentBtnId" >
																		<span class="glyphicon glyphicon-plus-sign"></span>Add
																	</button>
																	
																	<span class="partnerTabs">
																	<button class="btn btn-default" id="deletePaymentBtnId"
																		onclick="return submitWithParam('deletePaymentDetail','partnerOrgPaymentId','paymentDelResp');">
																		<span class="glyphicon glyphicon-trash"></span>Delete
																	</button>
				                                                    </span>
				                                                    </div>
																</div>
                                                                <hr>
                                                                </div>
                                                  </div>
                                                                <!-- <div class="form-group partnerTabs">
		                                                          <div class="col-sm-4">
																	<label><b>Payment For:</b> </label> 
																	<input type="radio"  name="vendorTypePayment" value="TP" id="traderPayment" checked />Trader 
																    <input type="radio"  name="vendorTypePayment" value="MP" id="manufacturerPayment"  />Manufacturer
																  </div>
                                                                </div> -->
                                                    <div class="form-group partnerTabs">
                                                          <div class="col-sm-3" style="display: none;">
                                                          <div class="styled-input">
                                                            <input type="hidden" id="bPartnerId" class="bPartnerId" name="partner.bPartnerId" required />
                                                           <!--  <input type="hidden" class="partnerOrgId" id="partnerOrgId"  name="partnerOrg.partnerOrgId" required /> -->
                                                            <input type="hidden" id="partnerOrgPaymentId" name="paymentDetailId"  required />
                                                         <input type="hidden" id="isActive"  name="isActive" value="Y"required />
                                                          </div>
                                                     </div>
                                                        
                                                        <div class="col-sm-3">
                                                            <div class="styled-input">
                                                                <select id="paymenttype" name="paymentType.PaymentTypeId" class="dropDown" onchange="changeFormFieldValue(this);" /></select>
                                                                <label>Type of Payment<span class="red">*</span></label>
                                                                <span></span>
                                                            </div>
                                                        </div>

                                                        <div class="col-sm-3">
                                                            <label>Mode of Payment: <span class="red">*</span>
                                                                <input type="radio" id="paymentmode" name="paymentMode" value="DD" checked> DD
                                                            </label>
                                                        </div>

                                                        <div class="col-sm-3">
                                                            <!-- <div class="styled-input"> -->
                                                                <!-- <input type="text" id="" name="" required />
                                                                <label>DD Date<span class="red">*</span></label>
                                                                <span></span> -->
                                                                <label>DD Date<span class="red">*</span></label>
                                                                                <div class="input-group date Pickdate" data-provide="datepicker">
                                                                                    <input type="text" class="form-control requiredField " id="demandDraftDate" name="paymentDate" >
                                                                                    <div class="input-group-addon">
                                                                                        <span class="glyphicon glyphicon-th"></span>
                                                                                    </div>
                                                                                </div>
                                                           <!--  </div> -->
                                                        </div>

                                                        <div class="col-sm-3">
                                                            <div class="styled-input">
                                                                <input type="text" id="demandDraftNo" name="referenceNo" class="requiredField onlyNumber" maxlength="6" />
                                                                <label>Demand Draft Number<span class="red">*</span></label>
                                                                <span></span>
                                                            </div>
                                                        </div>

                                                    </div>

                                                    <div class="form-group partnerTabs">
                                                        <div class="col-sm-3">
                                                            <div class="styled-input">
                                                                <input type="text" id="micrNo" name="micrCode" class="requiredField onlyNumber" maxlength="9"/>
                                                                <label>MICR Code<span class="red">*</span></label>
                                                                <span></span>
                                                            </div>
                                                        </div>

                                                        <div class="col-sm-3">
                                                            <div class="styled-input">
                                                                <input type="text" id="amount" readonly="readonly" />
                                                                <label>Amount in Rs.<span class="red">*</span></label>
                                                                <span></span>
                                                            </div>
                                                        </div>

                                                      <!--   <div class="col-sm-3">
                                                            <div class="styled-input">
                                                                <input type="text" id="gstin" name="gstin" required />
                                                                <label>GST Identification Number(GSTIN) <span class="red">*</span></label>
                                                                <span></span>
                                                            </div>
                                                        </div> -->

                                                        <div class="col-sm-4">
                                                            <div class="styled-input">
                                                                <input type="text" id="gst" readonly="readonly"  />
                                                                <label>GST (@<span id="gstRate"></span><span>%</span> on Amount:SAC No.998599) in Rs.<span class="red">*</span></label>
                                                                <span></span>
                                                            </div>
                                                        </div>
                                                    </div>

                                                    <div class="form-group partnerTabs">
                                                        <div class="col-sm-3">
                                                            <div class="styled-input">
                                                                <input type="text" id="totalAmount" readonly="readonly"  />
                                                                <label>Total Amount including GST in Rs.<span class="red">*</span></label>
                                                                <span></span>
                                                            </div>
                                                        </div>

                                                        <div class="col-sm-3">
                                                            <div class="styled-input">
                                                                <input type="text" id="bankName" name="bankName" class="requiredField requiredalphabeticsWithSpace" maxlength="50"/>
                                                                <label>Bank Name<span class="red">*</span></label>
                                                                <span></span>
                                                            </div>
                                                        </div>

                                                        <div class="col-sm-3">
                                                            <div class="styled-input">
                                                                <input type="text" id="branchName" name="branchName" class="requiredField requiredalphabeticsWithSpace" maxlength="25"/>
                                                                <label>Branch Name <span class="red">*</span></label>
                                                                <span></span>
                                                            </div>
                                                        </div>

                                                    </div>
                                                    <div class="clearfix"></div>
                                               <div class="form-group approveEEDiv" >
                                                    <label class="col-sm-2"><b>Add FO Comment:</b> </label>
														<div class="col-sm-4">
															<textarea class="form-control remark changeComment" name="eeComment"  id="eeRemark" maxlength="255" ></textarea>
														</div>
														<div class="col-sm-6">
															<label><b>Select Status:</b> </label> 
															<input type="radio"  value="Y" id="eeApproveBtn"  name="isEEApproved" class="statusBtn changeButton" checked />Approve 
															<!-- <input type="radio" name="isApproved" value="N" id="rejectBtn" class="statusBtn changeButton" /> Reject -->
														    <input type="radio"  value="C" id="eeClarification" name="isEEApproved" class="statusBtn changeButton"  /> Clarification
														</div>
											  </div>
											  <div class="form-group approveCEDiv" >
                                                    <label class="col-sm-2"><b>Add FA Comment:</b> </label>
														<div class="col-sm-4">
															<textarea class="form-control remark changeComment" name="ceComment" id="ceRemark" maxlength="255" ></textarea>
														</div>
														<div class="col-sm-6">
															<label><b>Select Status:</b> </label> 
															<input type="radio"  value="Y" id="ceApproveBtn"  name="isCEApproved" class="statusBtn changeButton" checked />Approve 
															<!-- <input type="radio" name="isApproved" value="N" id="rejectBtn" class="statusBtn changeButton" /> Reject -->
														    <input type="radio"  value="C" id="ceClarification" name="isCEApproved" class="statusBtn changeButton"  /> Clarification
														</div>
											  </div>
                                                     <div class="col-sm-12 text-center positionABS" style="margin-bottom:10px;">
                                                                                <button class="btn-all btn btn-info  k-tabstrip-prev btnPrevious pull-left tab-li-prev">Previous</button>
                                                                                <span id="PayemntFormDivId" class="">
                                                                                <button class="btn btn-info save disableBtn" onclick="return submitIt('partnerOrgPaymentForm','saveOrgPaymentDetails','partnerPaymentDetailResp');">Save</button>
                                                                                <button class="btn btn-info cancel disableBtn" id="cancelPaymentBtnId" >Reset</button>
                                                                                </span>
                                                                                <button class="btn-all btn btn-info  k-tabstrip-next btnNext pull-right tab-li-next">Next</button>
                                                                           </div>
                                                    </sf:form>
                                                </div>
                                              
                                                <!-- tab inside tab fields 14  -->

                                         <div>
                                         <div class="detailscont">
											<div class="col-md-12">
												<label class="col-xs-6"><h4>XXXX</h4></label> <label
													class="col-xs-6 ">XXXX</label>
											</div>
											<div class="col-md-12">
												<label class="col-xs-6">XXXX</label> <label
													class="col-xs-6 ">XXXX</label>
											</div>
										</div>
                                         <sf:form id="partnerDetailsApprovalForm" modelAttribute="partnerDetails" method="POST">
                                        
										<input type="hidden" id="bPartnerId" class="bPartnerId" name="bPartnerId" />
								        <div class="form-group">
								       <%--  <div class="col-sm-4" id="partnerSignFileDivId">
														<label>Upload Vendor Registration Copy<span class="red">*</span></label> 
														<div class="input-group partnerTabs">       
                                                         <input type="file" id="partnerSignCopyId" data-id="partnerSignCopy" data-name="partnerSignCopyName" data-anchor="a_partnerSignCopy" class="form-control uploadFile requiredFile">
											             <input type="hidden" id="partnerSignCopy" name="partnerSignCopy.attachmentId" class="form-control" />
											                <span class="input-group-btn">
											                    <button class="btn btn-default partnerSignCopy fileDeleteBtn" onclick="return submitWithThreeParam('deleteAttachmentById','Bpartner','partnerSignCopy','partnerSignCopy','partnerSignCopyDelResp');" disabled="disabled"><i class="fa fa-times"></i></button>
											                </span>
                                                            </div>
           	                                            <a class="col-sm-12 filelabel" data-url="<%=request.getContextPath()%>/download" id="a_partnerSignCopy"></a>
											</div> --%>
                                       
                                       
                                       <div class="col-sm-4" id="ceSignCopyDivId">
														<label>Upload Sign Copy<span class="red">*</span></label> 
														<div class="input-group ceSignCopyDiv">       
                                                         <input type="file" id="ceSignCopyId" data-id="ceSignCopy" data-name="ceSignCopyName" data-anchor="a_ceSignCopy" class="form-control uploadFile requiredFile">
											             <input type="hidden" id="ceSignCopy" name="ceSignCopy.attachmentId" class="form-control" />
											                <span class="input-group-btn">
											                    <button class="btn btn-default ceSignCopy fileDeleteBtn" onclick="return submitWithThreeParam('deleteAttachmentById','Bpartner','ceSignCopy','ceSignCopy','ceSignCopyDelResp');" disabled="disabled"><i class="fa fa-times"></i></button>
											                </span>
                                                            </div>
           	                                            <a class="col-sm-12 filelabel" data-url="<%=request.getContextPath()%>/download" id="a_ceSignCopy"></a>
											</div>
											<div class="col-sm-4" id="partnerCoSignCopyDivId" style="display: none;">
														<label>Upload Vendor Co-Sign Copy<span class="red">*</span></label> 
														<div class="input-group partnerCoSignCopyDiv">       
                                                         <input type="file" id="partnerCoSignCopyId" data-id="partnerCoSignCopy" data-name="partnerCoSignCopyName" data-anchor="a_partnerCoSignCopy" class="form-control uploadFile">
											             <input type="hidden" id="partnerCoSignCopy" name="partnerCoSignCopy.attachmentId" class="form-control" />
											                <span class="input-group-btn">
											                    <button class="btn btn-default partnerCoSignCopy fileDeleteBtn" onclick="return submitWithThreeParam('deleteAttachmentById','Bpartner','partnerCoSignCopy','partnerCoSignCopy','partnerCoSignCopyDelResp');" disabled="disabled"><i class="fa fa-times"></i></button>
											                </span>
                                                            </div>
           	                                            <a class="col-sm-12 filelabel" data-url="<%=request.getContextPath()%>/download" id="a_partnerCoSignCopy"></a>
											</div>
                                       </div>
								        <div class="col-sm-12 text-center" id="partnerSubmitDivId">
								        	 <div class="form-group"  style="margin-top:20px;">
								        	  <div class="col-sm-4">
                                                <label><b>Click Here To Submit</b>
                                                </label>
                                             <button style="margin-left:20px;" class="btn-all btn btn-info btnPrevious  save" id="submitBtnId" onclick="showSubmitLoader(event);">Submit</button>
                                             <div class="col-sm-12 text-center positionABS" style="margin-bottom:10px;">
                                             <button class="btn-all btn btn-info k-tabstrip-prev btnPrevious pull-left tab-li-prev">Previous</button>
                                             
                                       </div>
                                       </div>
                                       <div class="col-sm-4">
                                                 <label><b>Click Here To Export</b>
                                                </label>
                                                 <button style="margin-left:20px;" class="btn-all btn btn-info save" id="exportBtnId"  onclick="return directSubmit(event,'companyDetails','partnerReport');">Export</button>
                                                </div>
                                                 <!-- <div class="col-sm-12 text-center positionABS" style="margin-bottom:10px;">
                                                 <button class="btn-all btn btn-info k-tabstrip-prev btnPrevious pull-left tab-li-prev">Previous</button>
                                                  </div> -->
                                       </div> 
                                       </div>
                                       <div class="col-sm-12 text-center" id="partnerSubmitRespDivId">
                                           <label id="submitRespLabelId"></label>
                                       </div>
                                       
                                       
                                        <div class="col-sm-12" id="partnerApprovalDivId">
								      
                                                <div class="form-group">
                                                
                                                <label class="col-sm-2"><b>Add Comment:</b>
                                                </label>
                                                <div class="col-sm-4">
                                                <textarea  class="form-control" id="remarks" name="remarks" maxlength="255"></textarea>
                                                </div>
                                                <div class="col-sm-6">
                                                <label><b>Select Status:</b>
                                                </label>
                                                <input type="radio" name="isApproved" value="Y" id="approveBtnId" checked /> Approve
                                                <input type="radio" name="isApproved" value="N" id="rejectBtnId" /> Reject
                                                <input type="radio" name="isApproved" value="C" id="clarification" /> Clarification
                                                </div>
                                                </div>
                                                <div class="col-sm-12">
                                                <div class="form-group" style="margin-top:20px;">
                                                <div class="col-sm-4">
                                                <label><b>Click Here To Submit</b>
                                                </label>
                                                 <button style="margin-left:20px;" class="btn-all btn btn-info save" id="partnerApprovalBtnId" onclick="showApproveLoader(event);">Submit</button>
                                                </div>
                                               <!--  </div> -->
                                                <!-- <div class="form-group" style="margin-top:20px;"> -->
                                                <div class="col-sm-4">
                                                 <label><b>Click Here To Export</b>
                                                </label>
                                                 <button style="margin-left:20px;" class="btn-all btn btn-info save" id="exportBtnId"  onclick="return directSubmit(event,'companyDetails','partnerReport');">Export</button>
                                                </div>
                                                 <div class="col-sm-12 text-center positionABS" style="margin-bottom:10px;">
                                                 <button class="btn-all btn btn-info k-tabstrip-prev btnPrevious pull-left tab-li-prev">Previous</button>
                                                  </div>
                                             <!-- </div> -->
                                             </div>
                                             </div>
                                       </div>
                                       <div class="col-sm-12" id="partnerRegCompleteDivId" style="display: none;">
                                               <div class="col-sm-4">
                                                <label><b>Click Here</b>
                                                </label>
                                                 <button style="margin-left:20px;" class="btn-all btn btn-info save" onclick="completeRegistrationProcess(event);">Complete Registarion</button>
                                                </div>
                                                <div class="col-sm-12 text-center positionABS" style="margin-bottom:10px;">
                                                  <button class="btn-all btn btn-info k-tabstrip-prev btnPrevious pull-left tab-li-prev">Previous</button>
                                                </div>
                                       </div>
                                            </sf:form>
                                        </div>
                                        <!--fields of field group 9  -->
                                        <!-- <div>
                        <div class="form-group">
                                                          <div class="col-sm-3">
													      <div class="styled-input">
													      <input type="text" id="" name="" required />
													      <label>Factory Name<span class="red">*</span></label>
													      <span></span>
													      </div></div>

													       <div class="col-sm-3">
													      <div class="styled-input">
													      <input type="text" id="" name=""  />
													      <label>Established date of Factory</label>
													      <span></span>
													      </div></div>

													       <div class="col-sm-3">
													      <div class="styled-input">
													      <input type="text" id="" name=""  />
													      <label>Manpower</label>
													      <span></span>
													      </div></div>

													       <div class="col-sm-3">
													      <div class="styled-input">
													      <input type="text" id="" name=""  />
													      <label>Factory Inspection Report no.</label>
													      <span></span>
													      </div></div>
                                        		  </div>

                                        		  <div class="form-group">
                                        		          <div class="col-sm-12">
                                                          <div class="styled-input">
													      <textarea type="text" id="address" name="address" required ></textarea>
													      <label>Full Address of Factory<span class="red">*</span></label>
													      <span></span></div></div> 
                                        		  </div>

                                        		    <div class="form-group">
                                                        <div class="col-sm-3">
						                               	  <div class="styled-input">
						                                  <input type="text" id="" name="" required />
													      <label>City<span class="red">*</span></label>
													      <span></span> 
													      </div></div>

													      <div class="col-sm-3">
						                               	  <div class="styled-input">
						                                  <select  id="" name="" required /></select>
													      <label>District<span class="red">*</span></label>
													      <span></span> 
													      </div></div>

													      <div class="col-sm-3">
						                               	  <div class="styled-input">
						                                  <select  id="" name="" required /></select>
													      <label>Country<span class="red">*</span></label>
													      <span></span> 
													      </div></div>

													      <div class="col-sm-3">
						                               	  <div class="styled-input">
						                                  <select  id="" name="" required /></select>
													      <label>State<span class="red">*</span></label>
													      <span></span> 
													      </div></div>

                                                        </div>

                                                        <div class="form-group">
                                                        <div class="col-sm-3">
						                               	  <div class="styled-input">
						                                  <select  id="" name="" required /></select>
													      <label>Pincode<span class="red">*</span></label>
													      <span></span> 
													      </div></div>
                                                        </div>
                    </div> -->
                                        <!-- <div>
                        <div class="form-group">
                                                          <div class="col-sm-3">
													      <div class="styled-input">
													      <input type="text" id="" name="" required />
													      <label>Factory License Number<span class="red">*</span></label>
													      <span></span>
													      </div></div>

													       <div class="col-sm-3">
													      <div class="styled-input">
													      <input type="text" id="" name="" required />
													      <label>Factory License Validity Date<span class="red">*</span></label>
													      <span></span>
													      </div></div>

													      <div class="col-sm-3">
						                               	  <label>Attach Factory License Copy<span class="red">*</span></label>
						                               	  <input type="file" id="" name="" class="form-control"  required />
													      <span></span> 
													      </div>

													      <div class="col-sm-3">
						                               	  <label>Attach Machinery List Copy of Factory<span class="red">*</span></label>
						                               	  <input type="file" id="" name="" class="form-control"  required />
													      <span></span> 
													      </div>
												</div>

												<div class="form-group">
												<div class="col-sm-3">
						                               	  <label>Attach Testing Equipments List<span class="red">*</span></label>
						                               	  <input type="file" id="" name="" class="form-control"  required />
													      <span></span> 
													      </div>
												</div>
                    </div>
                     <div>
                        <p>Ut orci ligula, varius ac consequat in, rhoncus in dolor. Mauris pulvinar molestie accumsan. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae.</p>
                    </div> -->
                                        <!-- <div>
                        <div class="form-group">
						                                  <div class="col-sm-3">
						                               	  <div class="styled-input">
						                                  <select  id="" name="" required /></select>
													      <label>Item Name<span class="red">*</span></label>
													      <span></span> 
													      </div></div>

													      <div class="col-sm-3">
													      <div class="styled-input">
													      <select  id="" name="" required /></select>
													      <label>Name of Firm<span class="red">*</span></label>
													      <span></span></div> </div>

													     <div class="col-sm-3">
													      <div class="styled-input">
													      <input type="text" id="" name="" required />
													      <label>Order Start Date<span class="red">*</span></label>
													      <span></span></div> </div>

													      <div class="col-sm-3">
													      <div class="styled-input">
													      <select  id="" name="" required /></select>
													      <label>Order Completion Date<span class="red">*</span></label>
													      <span></span></div> </div>
													   </div>

										<div class="form-group">
						                                  <div class="col-sm-3">
													      <div class="styled-input">
													      <input type="text" id="" name="" required />
													      <label>Quantity Supplied<span class="red">*</span></label>
													      <span></span></div> </div>

						                                  <div class="col-sm-3">
						                               	  <div class="styled-input">
						                               	  <input type="text" id="" name=""  />
						                                  <label>Reference 1</label>
													      <span></span> 
													      </div></div>

													      <div class="col-sm-3">
													      <div class="styled-input">
													      <select  id="" name=""  /></select>
													      <label>Reference 2</label>
													      <span></span></div> </div>

													      <div class="col-sm-3">
						                               	  <label>Certificate Awarded (If Any)</label>
						                               	  <input type="file" id="" name="" class="form-control"   />
													      <span></span> 
													      </div>

											</div>
                    </div>                  
                    <div>
                        <div class="form-group">

                                         				  <div class="col-sm-3">
						                               	  <div class="styled-input">
						                               	  <select  id="" name="" required /></select>
						                                  <label>Type of Payment<span class="red">*</span></label>
													      <span></span> 
													      </div></div>

													      <div class="col-sm-3">
                                                          <label>Mode of Payment: <span class="red">*</span>
													      <input type="radio" name="DD" value="DD" checked> DD
 														  </label> 
													      </div>

													      <div class="col-sm-3">
						                               	  <div class="styled-input">
						                               	  <input type="text" id="" name="" required />
						                                  <label>DD Date<span class="red">*</span></label>
													      <span></span> 
													      </div></div>

													      <div class="col-sm-3">
						                               	  <div class="styled-input">
						                               	  <input type="text" id="" name="" required />
						                                  <label>Demand Draft Number<span class="red">*</span></label>
													      <span></span> 
													      </div></div>

													      </div>

											<div class="form-group">
													      <div class="col-sm-3">
						                               	  <div class="styled-input">
						                               	  <input type="text" id="" name="" required />
						                                  <label>MICR Code<span class="red">*</span></label>
													      <span></span> 
													      </div></div>

													      <div class="col-sm-3">
						                               	  <div class="styled-input">
						                               	  <input type="text" id="" name="" required />
						                                  <label>Amount in Rs.<span class="red">*</span></label>
													      <span></span> 
													      </div></div>

													      <div class="col-sm-3">
						                               	  <div class="styled-input">
						                               	  <input type="text" id="" name="" required />
						                                  <label>GST Identification Number(GSTIN) <span class="red">*</span></label>
													      <span></span> 
													      </div></div>

													      <div class="col-sm-3">
						                               	  <div class="styled-input">
						                               	  <input type="text" id="" name="" required />
						                                  <label>GST(@18% on Amount:SAC No.998599) in Rs. <span class="red">*</span></label>
													      <span></span> 
													      </div></div>
											 </div>

											 <div class="form-group">
													      <div class="col-sm-3">
						                               	  <div class="styled-input">
						                               	  <input type="text" id="" name="" required />
						                                  <label>Total Amount including GST in Rs.<span class="red">*</span></label>
													      <span></span> 
													      </div></div>

													      <div class="col-sm-3">
						                               	  <div class="styled-input">
						                               	  <input type="text" id="" name="" required />
						                                  <label>Bank Name<span class="red">*</span></label>
													      <span></span> 
													      </div></div>

													      <div class="col-sm-3">
						                               	  <div class="styled-input">
						                               	  <input type="text" id="" name="" required />
						                                  <label>Branch Name <span class="red">*</span></label>
													      <span></span> 
													      </div></div>

											 </div>
                    </div> -->
                                        <!-- <div>
                                <div class="form-group">
                                          <label><b>CONGRATULATIONS.....!  

                                          You have successfully filled all the details.</b>
                                          </label></div>

                                          <div class="form-group">
                                          <button class="btn-all btn btn-info btnPrevious pull-left">Click Here</button>
                                          </div>

                                          <div class="form-group">
                                          <label>Do you want to apply for the registration now?</label>
                                           <input type="radio" name="" value="" checked> No
                                           <input type="radio" name="" value="" checked> Yes

                                           </div>
                    </div> -->

                                    </div>
                                    <!-- remove previous save and next buttons from here and moved to each tab  -->
                                    <br>
								<div class="clearfix"></div>
                                </div>

            </div>                </div>     
      </div>
      
    
      </div>
      <!-- Master tab end--> 
      
                      
    </div>
  <!-- right-side end--> 
</div>



<script src="<%=request.getContextPath()%>/resources/${appMode}/commons/tilescommon/js/jquery-3.2.1.min.js?appVer=${appVer}"></script>
<script src="<%=request.getContextPath()%>/resources/commons/js/liCache.js"></script>
<script src="<%=request.getContextPath()%>/resources/commons/js/utility.js"></script>
<script src="<%=request.getContextPath()%>/resources/partner/js/partner.js"></script>
<script src="<%=request.getContextPath()%>/resources/partner/js/partnerApproval.js"></script> 
<script src="<%=request.getContextPath()%>/resources/partner/js/partnerCompanyContact.js"></script>
<script src="<%=request.getContextPath()%>/resources/partner/js/partnerOrg.js"></script>
<script src="<%=request.getContextPath()%>/resources/partner/js/partnerOrgLicense.js"></script>
<script src="<%=request.getContextPath()%>/resources/partner/js/partnerOrgUser.js"></script>
<script src="<%=request.getContextPath()%>/resources/partner/js/partnerOrgPBG.js"></script>
<script src="<%=request.getContextPath()%>/resources/partner/js/partnerOrgPerformance.js"></script>
<script src="<%=request.getContextPath()%>/resources/partner/js/partnerOrgExperience.js"></script>
<script src="<%=request.getContextPath()%>/resources/partner/js/partnerOrgProduct.js"></script>
<script src="<%=request.getContextPath()%>/resources/partner/js/partnerOrgRDAEC.js"></script>
<script src="<%=request.getContextPath()%>/resources/partner/js/partnerOrgOED.js"></script>
<script src="<%=request.getContextPath()%>/resources/partner/js/partnerOrgCertification.js"></script>
<script src="<%=request.getContextPath()%>/resources/partner/js/partnerOrgBIS.js"></script>
<script src="<%=request.getContextPath()%>/resources/partner/js/partnerOrgRegistration.js"></script>
<script src="<%=request.getContextPath()%>/resources/partner/js/partnerOrgPayment.js"></script>
<script src="<%=request.getContextPath()%>/resources/partner/js/partnerSignatory.js"></script>
<script src="<%=request.getContextPath()%>/resources/partner/js/partnerCompanyAddress.js"></script>
<script src="<%=request.getContextPath()%>/resources/partner/js/PartnerBankDetails.js"></script>
<script src="<%=request.getContextPath()%>/resources/partner/js/partnerDirectorDetails.js"></script>
<script src="<%=request.getContextPath()%>/resources/partner/js/partnerItemManufacturer.js"></script>
<script src="<%=request.getContextPath()%>/resources/partner/js/partnerTradingItem.js"></script>
<script src="<%=request.getContextPath()%>/resources/partner/js/partnerManufacturerOrg.js"></script>
<script src="<%=request.getContextPath()%>/resources/partner/js/partnerFinancialDetails.js"></script>
<script src="<%=request.getContextPath()%>/resources/partner/js/partnerOrgInspection.js"></script>
<%-- <script src="<%=request.getContextPath()%>/resources/partner/js/uploadFile.js"></script> --%>
  <script src="<%=request.getContextPath()%>/resources/${appMode}/partner/js/uploadFile.js?appVer=${appVer}"></script>

<script src="<%=request.getContextPath()%>/resources/commons/js/loadList.js"></script>
<script src="<%=request.getContextPath()%>/resources/commons/js/loadItemList.js"></script>

<script src="<%=request.getContextPath()%>/resources/${appMode}/commons/js/popupMultiSelect.js?appVer=${appVer}"></script>
 <script src="<%=request.getContextPath()%>/resource/js/profileSetting.js?appVer=${appVer}"></script>
<!-- <script src="http://harshniketseta.github.io/popupMultiSelect/dist/javascripts/multiselect.min.js"></script> -->
<script src="<%=request.getContextPath()%>/resources/commons/js/formFields.js"></script>
<script src="<%=request.getContextPath()%>/resources/commons/js/commonValidation.js"></script>

<script>
	$(document).ready(function() {
		$("#tabstrip").kendoTabStrip();
		$("#tabstrip2").kendoTabStrip();
		$("#tabstrip3").kendoTabStrip();
		$("#tabstrip4").kendoTabStrip();
		$("#tabstrip5").kendoTabStrip();
		$("#tabstrip6").kendoTabStrip();
	});
</script>

<script type="text/javascript">
$("#tabstrip1").kendoTabStrip();
  $("#my-language").multiselect(
      {
        title: "Select Language"
        //maxSelectionAllowed: 5
      });
  $(".selectall").change(function () {
	    $("input:checkbox.selectitemchecbox").prop('checked', $(this).prop("checked"));
	});
  
	var date = new Date();
	date.setDate(date.getDate());
	$('.Pickdate').datepicker({
		endDate : date
	});
	$('.futureDate').datepicker({
		startDate : date
	});
</script>
