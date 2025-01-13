<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>

<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/${appMode}/tilescommon/css/fioristyle.css?appVer=${appVer}"/>
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/${appMode}/tilescommon/css//borderTab.css?appVer=${appVer}" />
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/resources/${appMode}/tilescommon/css/mobile.css?appVer=${appVer}" />

<script>
	$(document).ready(function() {
		$("#tabstrip").kendoTabStrip();
		$("#tabstrip2").kendoTabStrip();
		$("#tabstrip3").kendoTabStrip();
		$("#tabstrip4").kendoTabStrip();
		$("#tabstrip5").kendoTabStrip();

	});
</script>
<div class="full-container">
	<!-- left-side start-->
	<div class="clearfix"></div>
	<input type="hidden" id="pageInfo" value="${pageInfo}">
	<input type="hidden" id="roleName" value="${role}">
	<div id="mobile_first_container" class="left-side col-md-3 no-marg">
		<div class="detailsheader">
			<div class="col-sm-3 text-center brdrgt">
				<label>Vendor List (0)</label>
			</div>
		</div>
		<div class="input-group">
			<div class="input-group-btn search-panel">
				<button type="button" class="btn btn-default dropdown-toggle"
					data-toggle="dropdown">
					<span id="search_concept">Filter by</span> <span class="caret"></span>
				</button>
				<ul class="dropdown-menu" role="menu">
					<li><a href="#contains">Code</a></li>
					<li class="divider"></li>
					<li><a href="#all">Name</a></li>
				</ul>
			</div>
			<input type="hidden" name="search_param" value="all"
				id="search_param"> <input type="text" class="form-control"
				name="x" placeholder="Search term..."> <span
				class="input-group-btn">
				<button class="btn btn-default" type="button">
					<span class="glyphicon glyphicon-search"></span>
				</button>
				<button class="btn btn-default addnewlist" type="button">
					<span class="glyphicon glyphicon-plus"></span>
				</button>
			</span>
		</div>
		<ul class="nav nav-tabs tabs-left example leftPaneData">
			
		</ul>
		<div class="clearfix"></div>
	</div>
	<!-- left-side end-->

	<!-- right-side start-->
	<div id="mobile_second_container" class="right-side col-md-9 no-marg">
		<div class="detailsheader toptabbrd">
			<div class="col-sm-9 text-center">
				<label>Vendor Details</label>
			</div>
		</div>
		<div class="clearfix"></div>
		<div class="tab-content">
			<!-- Master tab start-->

			<div class="tab-pane active in">
				<div class="card">
					<div class="posrelative" id="">
						<div class="demo-section k-content">
							<div id="tabstrip" class="Firsttab">
								<ul>
									<!-- tabs -->
									<li class="k-state-active" onclick="getVendorDetails(event,this);" id="infraVendorTab" >Vendor Infra Details</li>
									<li class="toggleTab" openTab="itemsTab" id="infraItemTab" onclick="getInfraItem(event,this)" >Item</li>
								</ul>
								<!--fields of field group 1  -->
								<div>
										<div class="detailscont">
											
										</div>
									<div class="form-group">
										<div class="col-sm-12">
											<h4>
												<b>Vendor Infra Details</b>
											</h4>
											<hr>
										</div>
									</div>
									<div class="form-group">
									 <input type="hidden" id="bPartnerId" class="bPartnerId" name="bPartnerId" />
										<div class="col-sm-4">
											<div class="styled-input partnerTabs">
                                                <input type="text" id="companyName"  readonly="readonly" required/>
                                                <label>Company Name<span class="red">*</span></label>
                                                <span></span>
                                            </div>
										</div>
										<div class="mobclearfix"></div>
										<div class="col-sm-4">
											<div class="styled-input partnerTabs">
                                                <input type="text" id="crnNo" readonly="readonly" required />
                                                <label>Company Registration No.<span class="red">*</span></label>
                                                <span></span>
                                            </div>
										</div>
										<div class="mobclearfix"></div>
										<div class="col-sm-4">
											<div class="styled-input partnerTabs">
                                                <input type="text" id="gstNo"  readonly="readonly" required />
                                                <label>GST Number<span class="red">*</span></label>
                                                <span></span>
                                            </div>
										</div>
									</div>
									<div class="form-group">
										<div class="col-sm-4">
											<div class="styled-input partnerTabs">
                                                <input type="text" id="panCardNo"  readonly="readonly"  required />
                                                <label>PAN Number<span class="red">*</span></label>
                                                <span></span>
                                            </div>
										</div>
									</div>
									
									
									
									<!-- <div class="col-sm-12 text-center positionABS" style="margin-bottom: 10px;">
								<button class="btn btn-info">Save</button>
							</div> -->
								</div>
								<!--fields of field group 1  -->
								<!--fields of field group 2  -->
								<div>
									<div class="detailscont">
											
										</div>
									<div id="tabstrip2" class="Firsttab">
								<ul>
									<!-- tabs -->
									<li class="k-state-active itemsTab"  onclick="getInfraItems(event,this);">Items details</li>
									<li onclick="onTabInfraDrawingDocsLoad(event)">Drawing Documents</li>
									<li onclick="onTabInfraTypeTestLoad(event)">Type tests</li>
									<li onclick="onTabInfraConfirmLoad(event)">Confirmation</li>
								</ul>
								<!--fields of field group 1  -->
								<div id="partsTab">
					
					<div class="tab-content col-md-11">
						<div class="tab-pane active" id="tab_e">
				<sf:form id="infraItemForm" modelAttribute="infraItem" method="POST">
                                                   <div class="form-group">
													<div class="col-sm-12 ">
														<h4 class="col-xs-6 nopadding">
															<b>Infra Item</b>
														</h4>
														<div class="col-xs-6 text-right nopadding" style="margin-top: 10px;">
															<button class="btn btn-default" id="addInfraItemBtnId"><span class="glyphicon glyphicon-plus-sign"></span>Add Item</button>
															<!-- <button class="btn btn-default" id="editInfraItemBtnId"><span class="glyphicon glyphicon-pencil"></span>Edit Item</button> -->
															
														</div>
														<hr>
													</div>
												</div>
				<div >
                <div class="form-group">
                <input type="hidden" id="partnerInfraItemId" class="partnerInfraItemId" name="partnerInfraItemId" >
                <input type="hidden" class="bPartnerId" name="partner.bPartnerId" />
				                                                
                <div class="col-sm-4">
                    <div class="styled-input readonly">
                        <select  id="infraMaterialId" name="material.materialId" class="requiredField" >
                        </select>
                        <label>Item</label>
                        <span></span>
                    </div>
                </div>
                <div class="col-sm-3 infraTabs" id="infraItemDivId" >
                       <button class="btn btn-default aditbt" id="addItemPopup">Add Infra Item</button>
                </div>
              <div class="col-sm-4">
                    <div class="styled-input readonly">
                        <input type="text" id="uomId" name="material.uom.name" readonly="readonly"/>
                        <label>UOM</label>
                        
                    </div>
                </div>
                                             
                </div>   
                <div class="form-group readonly">
                <div class="col-sm-4">
                    <div class="styled-input">
                        <input type="text" id="hsnId" name="material.hsnCode.code" readonly="readonly"/>
                        <label>HSN/SAC Code</label>
                        <span></span>
                    </div>
                </div> 
                </div>       
                  <div class="col-md-6 col-xs-12">
                    <label class="checkbox-inline readonly">
                      <input type="checkbox"  id="checkBoxId" name="isActive" class="isActive" checked >
                      Active </label>
                  </div>
                  <div class="clearfix"></div>
                    <div class="col-md-12 text-center">
                      <button class="btn btn-info save disableBtn" id="saveItemBtnId" onclick="return submitIt('infraItemForm','saveInfraItems','infraItemsResp');">Save</button>
					  <button class="btn btn-info cancel disableBtn" id="cancelInfraItemBtnId" >Cancel</button>
                    </div>
                </div>
                 </sf:form>
                      </div>
                   </div>
             </div>
								
								<div>
								<div class="form-group">
										<div class="col-sm-12">
											<h4 class="col-sm-4"  style="margin-bottom: 0px;">
												<b>Drawing Documents details</b>
											</h4>
											
											<div class="clearfix"></div>
											<hr>
											<br><br>
											<!-- <label class="col-sm-2">Level:<span class="detspan" id="drawingDocsLevelSpanId" ></span></label>
											 <label class="col-sm-5">Current Status:<span class="detspan" id="drawingDocsStatus" ></span></label>
	                                         <label class="col-sm-5">Current Comment:<span class="detspan" id="drawingDocsComment" ></span></label> -->
										</div>
									</div>
									<sf:form id="infraDrawingDocsForm" modelAttribute="infraDrawingDocs" method="POST">
									<input type="hidden" id="partnerItemDrawingDocId"  name="partnerItemDrawingDocId" >
									<input type="hidden" id="docInfraItemId" class="partnerInfraItemId" name="partnerInfraItem.partnerInfraItemId" >
                                    <input type="hidden" class="bPartnerId" id="partnerId" name="partner.bPartnerId" />
                                   <!--  <input type="hidden" id="materialDrawingDoc" name="materialDrawingDocId" /> -->
									<div class="form-group">
										<div class="col-sm-4">
											<div class="styled-input">
                                                <input type="text" id="drawingDocNameId" name="materialDrawingDoc.docName"  readonly="readonly" />
                                                <label>Document Name</label>
                                                <span></span>
                                            </div>
                                            
										</div>
										
										</div>
										<div id="previousApprovedDiv" style="display: none;">
                                                  <div class="form-group">
                                                     <div class="col-sm-6">
			                                                    <label class="radio-inline" style="margin-top:20px;">
			                                                        <input type="radio" id="prevDocApprove" value="Y" checked disabled="disabled">Approved
			                                                     </label>
			                                                      <label class="radio-inline"  style="margin-top:20px;">  
			                                                       	<input type="radio"  id="prevDocClarify" value="C" disabled="disabled">Clarification
			                                                    </label>
			                                                     <label class="radio-inline"  style="margin-top:20px;">  
			                                                       	<input type="radio"  id="prevDocReject" value="N" disabled="disabled">Reject
			                                                    </label>
			                                                    </div>
                                            		</div>
			                                            <div class="form-group">
			                                                <div class="col-sm-4">
			                                                    <div class="styled-input">
			                                                    <textarea id="prevDocComment"  maxlength="255" disabled="disabled" ></textarea>
			                                                        <label>Comments<span class="red">*</span></label>
			                                                        <span></span>
			                                                    </div>
			                                                </div>
			                                            </div>
			                                     </div>
										  <div id="approvalDivId" class="approvalDiv">
                                                  <div class="form-group">
                                                     <div class="col-sm-6">
			                                                    <label class="radio-inline" style="margin-top:20px;">
			                                                        <input type="radio" class="" name="status" id="docApprove" value="Y" checked>Approved
			                                                     </label>
			                                                      <label class="radio-inline"  style="margin-top:20px;">  
			                                                       	<input type="radio" class="" name="status" id="docClarify" value="C" >Clarification
			                                                    </label>
			                                                     <label class="radio-inline"  style="margin-top:20px;">  
			                                                       	<input type="radio" class="" name="status" id="docReject" value="N">Reject
			                                                    </label>
			                                                    </div>
                                            		</div>
			                                            <div class="form-group">
			                                                <div class="col-sm-4">
			                                                    <div class="styled-input">
			                                                    <textarea id="docComment" name="remark" class=" requiredField" maxlength="255" ></textarea>
			                                                        <label>Comments<span class="red">*</span></label>
			                                                        <span></span>
			                                                    </div>
			                                                </div>
			                                            </div>
			                                     </div>
                                            <div class="form-group text-center">
                                              	<button class="btn btn-info save" id="saveDocBtnId" onclick="return submitIt('infraDrawingDocsForm','saveinfraDrawingDocs','saveDrawingDocsResp');">Submit</button>
                                             <button class="btn btn-info cancel" id="cancelDocBtnId">Cancel</button>                                             
                                              </div>
                                       </sf:form>
								</div>
								
								<div>
								<div class="form-group">
										<div class="col-sm-12">
											<h4 class="col-sm-12"  style="margin-bottom: 0px;">
												<b>Type Tests</b>
											</h4>
											
											<div class="clearfix"></div>
											<hr>
											<br><br>
											<!--  <label class="col-sm-2">Level:<span class="detspan" id="typeTestLevelSpanId" ></span></label>
											 <label class="col-sm-5">Current Status:<span class="detspan" id="typeTestStatus" ></span></label>
	                                         <label class="col-sm-5">Current Comment:<span class="detspan" id="typeTestSpanComment" ></span></label>
 -->										</div>
									</div>
									<div class="clearfix"></div>
									<sf:form id="infraTypeTestForm" modelAttribute="infraTypeTest" method="POST">
									<input type="hidden" id="typeTestInfraItemId" class="partnerInfraItemId" name="partnerInfraItem.partnerInfraItemId" >
                                    <input type="hidden" class="bPartnerId"  id="typeTestPartnerId" name="partner.bPartnerId" />
                                    <input type="hidden" id="materialTypeTestDetails" name="partnerItemTypeTestId" />
									<div class="form-group">
										<div class="col-sm-4">
											<div class="styled-input">
                                                <input type="text" id="testTypeName" name="materialTypeTestDetails.docName" readonly="readonly"  />
                                                <label>Type Tests Name</label>
                                                <span></span>
                                            </div>
										</div>
										<!-- <div class="col-sm-4">
											<div class="styled-input partnerTabs" style="display : none;">
                                                <input type="text" id="testTypeDesc" value="" readonly="readonly"  />
                                                <label>Description</label>
                                                <span></span>
                                            </div>
										</div> -->
										<div class="col-sm-4">
                                                    <div class="form-group infraTabs">
                                                        <label>Issue Date<span class="red">*</span></label>
                                                        <div class="input-group date" data-provide="datepicker">
                                                            <input type="text" id="issueDate" class="form-control requiredField" name="issueDate" tabindex="-1">
                                                            <div class="input-group-addon">
                                                                <span class="glyphicon glyphicon-th"></span>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                        <div class="col-sm-4">
											<div class="styled-input infraTabs">
											    <input type="text" id="letterNoId" name="letterNo" class="onlyNumber"/>
                                                <label>Letter No<span class="red">*</span></label>
                                                <span></span>
                                            </div>
										</div>
										
										<div id="previousTestApprovedDiv" style="display: none;">
                                                  <div class="form-group">
                                                   
                                                   <div class="col-sm-6">
	                                                    <label class="radio-inline" style="margin-top:20px;" >
	                                                        <input type="radio" id="prevTypeTestApprove" value="Y" checked disabled="disabled">Approved
	                                                     </label>
	                                                     <label class="radio-inline"  style="margin-top:20px;">  
	                                                       	<input type="radio" class="" name="status" id="prevTypeTestClarify" value="C" disabled="disabled">Clarification
	                                                     </label>
	                                                     <label class="radio-inline"  style="margin-top:20px;">  
	                                                     <input type="radio" class="" name="status" id="prevTypeTestReject" value="N" disabled="disabled">Reject
	                                                    </label>
	                                                    
			                                        </div>
                                            		</div>
			                                            <div class="form-group">
			                                                <div class="col-sm-4">
			                                                    <div class="styled-input">
			                                                    <textarea id="prevTypeTestComment" maxlength="255" disabled="disabled"></textarea>
			                                                        <label>Comments<span class="red">*</span></label>
			                                                        <span></span>
			                                                    </div>
			                                                </div>
			                                            </div>
			                                     </div>
										  <div id="approvalDivId"  class="approvalDiv">
                                                  <div class="form-group">
                                                     <div class="col-sm-6">
			                                                    <label class="radio-inline" style="margin-top:20px;">
			                                                        <input type="radio"  name="status" id="typeTestApprove" value="Y" checked>Approved
			                                                     </label>
			                                                      <label class="radio-inline"  style="margin-top:20px;">  
			                                                       	<input type="radio" class="" name="status" id="typeTestClarify" value="C" >Clarification
			                                                    </label>
			                                                     <label class="radio-inline"  style="margin-top:20px;">  
			                                                       	<input type="radio" class="" name="status" id="typeTestReject" value="N">Reject
			                                                    </label>
			                                                    </div>
                                            		</div>
			                                            <div class="form-group">
			                                                <div class="col-sm-4">
			                                                    <div class="styled-input">
			                                                    <textarea id="typeTestComment" name="remark" class="" maxlength="255" ></textarea>
			                                                        <label>Comments<span class="red">*</span></label>
			                                                        <span></span>
			                                                    </div>
			                                                </div>
			                                            </div>
			                                     </div>
                                            <div class="form-group text-center">
                                              	<button class="btn btn-info save " id="saveTypeTestBtnId" onclick="return submitIt('infraTypeTestForm','saveTypeTest','saveTypeTestResp');">Save</button>
					                            <button class="btn btn-info cancel " id="cancelTypeTestBtnId" >Cancel</button>
                   
                                              </div>
                                         </div>
                                         </sf:form>
										</div>
								
								<div class="">
                                        	<div class="form-group">
                                                <div class="col-sm-12">
                                                    <h4 class="col-sm-12"  style="margin-bottom: 0px;"><b>Infra Item Confirmation</b></h4>
                                                   <!--  <div class="col-sm-8"><button class="btn btn-default bluebutton">Upload Clarification File</button></div> -->
                                                   
                                                    <hr>
                                                     <br><br>
                                                       <!-- <label class="col-sm-2">Level:<span class="detspan" id="confirmatoryLevelSpanId" ></span></label>
	                                                   <label class="col-sm-5">Current Status:<span class="detspan" id="confirmInfraLineStatus" ></span></label>
	                                                   <label class="col-sm-5">Current Comment:<span class="detspan" id="confirmInfraLineComment" ></span></label>
 -->                                                </div>
                                            </div>
                                          
                                          
                                            <sf:form id="approavalForm" modelAttribute="infraLine" method="POST">
                                            <div class="form-group">
                                            <input type="hidden" id="infraItemApprovalLineId" class="" name="partnerInfraItemApprovalId" >
                                            <input type="hidden" id="confirmInfraItemId" class="partnerInfraItemId" name="partnerInfraItem.partnerInfraItemId" >
                                            <input type="hidden" class="bPartnerId"  id="confirmPartnerId" name="partner.bPartnerId" />
                                             <input type="hidden" class="" id="infraApprovalLevelId" name="infraApprovalLevel.infraApprovalLevelId" /> 
                                              </div>  
                                              
                                               <!-- <div class="form-group">
                                                <div class="col-md-12 readonly">
									                    <label class="checkbox-inline" id="confirmCheckBox">
									                      <input type="checkbox"  id="confirmCheckBox" value="Y" checked readonly="readonly">
									                      Confirmed by the vendor 
									                    </label>
									                    
									                  </div>
                                              </div> -->
                                              <div style="display: none;" id="confirmMessageDiv">
                                                  <label id="confirmMessageLabelId"></label>
                                              </div>
                                             <div id="clarificationDivId" style="display : none ;">
                                                  <div class="input-group col-sm-4" id="clarificationUploadDivId">               
                                                         <label>Uploaded Clarification:<span class="red">*</span></label>
                                                         <div class="input-group infraTabs" id="infraClarifyReadonlyDiv"> 
                                                          <input type="file"  id="docFileResponseUploadId" data-id="docFileResponseId" data-name="docFileResponseName" data-anchor="a_docFileResponse" class="form-control fileResponse uploadFile">
                                                          <input type="hidden" id="docFileResponseId" name="partnerInfraItem.attachment.attachmentId" class="form-control fileResponse" /> 
												                <span class="input-group-btn">              
												                <button  id="deleteDocAttachmentId" class="btn btn-default docFileResponseId" onclick="return submitWithThreeParam('deleteAttachmentById','PartnerInfraItem','attachment','docFileResponseId','clarificationDocfileDeleteResp');"><i class="fa fa-times"></i></button>
												                </span>
           	                                            </div>
           	                                             <a class="col-sm-12 filelabel" data-url="<%=request.getContextPath()%>/download" id="a_docFileResponse"></a>
           	                                            </div>
           	                                           </div>  
                                                    <div class="clearfix"></div>
                                             
                                              <div id="approvalDivId"  class="approvalDiv">
                                                  <div class="form-group">
                                                     <div class="col-sm-6">
			                                                    <label class="radio-inline" style="margin-top:20px;">
			                                                        <input type="radio" class="" name="isApproved" id="confirmApprove" value="Y" checked>Approve
			                                                     </label>
			                                                      <label class="radio-inline"  style="margin-top:20px;">  
			                                                       	<input type="radio" class="" name="isApproved" id="confirmClarify" value="C" >Clarification
			                                                    </label>
			                                                     <label class="radio-inline"  style="margin-top:20px;">  
			                                                       	<input type="radio" class="" name="isApproved" id="confirmReject" value="N">Reject
			                                                    </label>
			                                                    </div>
                                            		</div>
			                                            <div class="form-group">
			                                                <div class="col-sm-4">
			                                                    <div class="styled-input">
			                                                    <textarea id="confirmComment" name="comment" class=" requiredField" maxlength="255" ></textarea>
			                                                        <label>Comments<span class="red">*</span></label>
			                                                        <span></span>
			                                                    </div>
			                                                </div>
			                                            </div>
			                                     </div>
                                            <div class="form-group text-center">
                                                 <button class="btn btn-info save" id="confirmApprovalBtnId" onclick="createInfraLine(event)">Submit</button>
                                              	<!-- <button class="btn btn-info save" onclick="return submitIt('approavalForm','setInfraItemLine','confirmInfraItemLineApprovalResp');">Submit</button> -->
                                              </div>
                                              </sf:form>
                                            </div>
								<!--fields of field group 1  -->
								</div>
								<!--fields of field group 2  -->
							</div>

							
						</div>
						<!-- Master tab end-->

					</div>
				</div>
				<!-- right-side end-->
			</div>
		</div>
	</div>
</div>

<script src="<%=request.getContextPath()%>/resources/${appMode}/transaction/js/vendorInfraDetails.js?appVer=${appVer}"></script>
<script src="<%=request.getContextPath()%>/resources/${appMode}/transaction/js/infraTypeTest.js?appVer=${appVer}"></script>
<script src="<%=request.getContextPath()%>/resources/${appMode}/transaction/js/infraDrawingDocs.js?appVer=${appVer}"></script>
<script src="<%=request.getContextPath()%>/resources/${appMode}/transaction/js/confirmInfraItemApproval.js?appVer=${appVer}"></script>
<script src="<%=request.getContextPath()%>/resources/${appMode}/commons/js/formFields.js?appVer=${appVer}"></script>
<script src="<%=request.getContextPath()%>/resources/${appMode}/commons/js/utility.js?appVer=${appVer}"></script>
<script src="<%=request.getContextPath()%>/resources/${appMode}/tilescommon/js/bootstrap-datetimepicker.js?appVer=${appVer}"></script>
<script src="<%=request.getContextPath()%>/resources/${appMode}/transaction/js/infraItems.js?appVer=${appVer}"></script>
<script src="<%=request.getContextPath()%>/resources/${appMode}/partner/js/uploadFile.js?appVer=${appVer}"></script>
<script src="<%=request.getContextPath()%>/resources/${appMode}/commons/js/loadList.js?appVer=${appVer}"></script>
<script src="<%=request.getContextPath()%>/resources/${appMode}/commons/js/loadItemList.js?appVer=${appVer}"></script>
<script src="http://harshniketseta.github.io/popupMultiSelect/dist/javascripts/multiselect.min.js?appVer=${appVer}"></script>

