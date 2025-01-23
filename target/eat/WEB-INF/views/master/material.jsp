<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/${appMode}/tilescommon/css/fioristyle.css?appVer=${appVer}"/>
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/${appMode}/tilescommon/css//borderTab.css?appVer=${appVer}">
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/${appMode}/tilescommon/css/mobile.css?appVer=${appVer}"/>
<script src="<%=request.getContextPath()%>/resources/${appMode}/tilescommon/js/kendo.all.min.js?appVer=${appVer}"></script>

<div class="full-container">
	<!-- left-side start-->
	<div class="clearfix"></div>
	<div id="mobile_first_container" class="left-side col-md-3 no-marg">
		<div class="detailsheader">
			<div class="col-sm-3 text-center brdrgt">
				<label>Material Report (<span class="reportCount">0</span>)
				</label>
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
				name="x" id="searchLiteralId" placeholder="Search term...">
			<span class="input-group-btn">
				<button class="btn btn-default" type="button">
					<span class="glyphicon glyphicon-search"></span>
				</button>
				<button class="btn btn-default  addnewlist " id="addRecord" type="button"
					onclick="addMaterial(event);">
					<span class="glyphicon glyphicon-plus"></span>
				</button>
			</span>
		</div>
		<ul id="example" class="nav nav-tabs tabs-left example leftPaneData">


		</ul>
		<p id="pagination-here"></p>
		<div class="clearfix"></div>
	</div>
	<!-- left-side end-->

	<!-- right-side start-->
	<div id="mobile_second_container" class="right-side col-md-9 no-marg">

		<div class="clearfix"></div>

		<div class="tab-content">
			<!-- Master tab start-->

			<div class="tab-pane active in" id="AddMaterialGroup">
				<section>
					<div class="demo-section k-content">
						<div id="tabstrip" class="Firsttab">
							<ul>
								<!-- tabs -->
								<li class="k-state-active materialDetail"
									id="materialDetailTabId">Material Details</li>
								<li class="BOM" openTab="bomVersionClassTab"
									onclick="return submitWithParam('getBOMListFromMaterial','materialId','onBomVersionTabLoad');">BOM
									Version</li>
								<li class=""
									onclick="return submitWithParam('getGTPListByMaterialId','materialId','gtpListResp');">Associated
									GTP</li>
									<li class="materialVersion"
									onclick="return submitWithParam('getMaterialVersionList','materialId','matrialVersionTabResp');">Material
									Version</li>
							</ul>

							<!--fields of field group 1  -->
							<div>
								<div class="detailscont">
									<div class="col-md-12" id="masterDetails"></div>

								</div>
								<div class="board">
									<div class="board-inner">
										<ul class="nav nav-tabs" id="myTab">
											<div class="liner"></div>
											<li class="active"><a href="#home" data-toggle="tab"
												title="Information"> <span class="round-tabs one">
														<i class="fa fa-info" aria-hidden="true"></i>
												</span>
											</a></li>
										</ul>
									</div>
									<div class="tab-content">
										<div class="tab-pane fade in active" id="home">
											<div class="col-sm-12 text-right">
												<div class="form-group">
													<button type="button" class="btn btn-info bluebutton"
														id="delmaterial">
														<span class="glyphicon glyphicon-trash"></span>Delete
													</button>

													<button type="button" class="btn btn-info bluebutton"
														id="editMaterial">
														<span class="glyphicon glyphicon-edit"></span>Edit
													</button>
												</div>
											</div>

											<sf:form id="materialFormId" method="POST" autocomplete="off"
												modelAttribute="Material" class="readonly">
												<div class="form-group">
													<input type="hidden" id="materialId" class="Id" value="" />
													<input type="hidden" id="typeCodeId" class="typeCode" name="typeCode" value="I"  />
													
													<div class="col-sm-4">
														<div class="styled-input">
															<input type="text" id="name" class="Name" name="name"
																required="required" /> <label>Name<span
																class="red">*</span></label> <span></span>
														</div>
													</div>
													<div class="col-sm-4">
														<div class="styled-input">
															<input type="text" id="code" class="Code" name="code"
																required="required" /> <label>Code<span
																class="red">*</span></label> <span></span>
														</div>
													</div>
													<div class="col-sm-4">
														<div class="styled-input">
															<input type="text" id="description" class="Description"
																name="description" required="required" /> <label>Description<span
																class="red">*</span></label> <span></span>
														</div>
													</div>
												</div>
												<div class="form-group">
													<div class="col-sm-4">
														<div class="styled-input slate">
															<select id="hsn" name="hsnCode.hsnId" class="HSNCode"
																required="required">
																<!-- <option value="" disabled selected>Select Material Group</option> -->
																<c:forEach var="hsn" items="${hsn}">
																	<option value="${hsn.hsnId}">${hsn.name}</option>
																</c:forEach>

															</select> <label>HSN<span class="red">*</span></label>
														</div>
													</div>
													<div class="col-sm-4">
														<div class="styled-input slate">
															<select id="matGroup"
																name="materialGroup.materialGroupId" class="matGroup"
																required="required">
																<!-- <option value="" disabled selected>Select Material Group</option> -->
																<c:forEach var="materialGroup" items="${materialGroup}">
																	<option value="${materialGroup.materialGroupId}">${materialGroup.name}</option>
																</c:forEach>

															</select> <label>Material Group<span class="red">*</span></label>
															<span></span>
														</div>
													</div>
													<div class="col-sm-4">
														<div class="styled-input slate">
															<select id="matSubGroup"
																name="materialSubGroup.materialSubGroupId"
																class="matSubGroup" required="required">
																<!-- <option value="" disabled selected>Select Material SubGroup</option> -->
																<c:forEach var="materialSubGroup"
																	items="${materialSubGroup}">
																	<option value="${materialSubGroup.materialSubGroupId}">${materialSubGroup.name}</option>
																</c:forEach>

															</select> <label>Material Sub-Group<span class="red">*</span></label>
															<span></span>
														</div>
													</div>
												</div>
												<div class="form-group">
													<div class="col-sm-4">
														<div class="styled-input slate">
															<select id="uom" name="uom.uomId" class="uom"
																required="required">
																<!-- <option value="" disabled selected>Select Material SubGroup</option> -->
																<c:forEach var="uom" items="${uom}">
																	<option value="${uom.uomId}">${uom.name}</option>
																</c:forEach>

															</select> <label>UOM<span class="red">*</span></label> <span></span>
														</div>
													</div>
													<div class="col-sm-4">
														<div class="styled-input">
															<input type="text" id="itemTrade" name="itemTrade"
																class="ItemTrade" required="required" /> <label>Item
																Trade<span class="red">*</span>
															</label> <span></span>
														</div>
													</div>
													<div class="col-sm-4">
														<div class="col-md-12 col-xs-12">
															<label class="checkbox-inline"> <input
																type="checkbox" value="Y" class="isActive">
																Active
															</label>
											<input type="checkbox" style="display: none;" value="Y"
									  			name="isActive" class="isActive" checked="checked" />
										              </div>
														<div class="col-md-12 col-xs-12">
															<label class="checkbox-inline"> <input
																type="checkbox" name="isTestReport" value="Y"
																class="TestReport"> Test Report
															</label>
														</div>
														<div class="col-md-12 col-xs-12">
															<label class="checkbox-inline"> <input
																type="checkbox" name="isSSI" value="Y" class="SSI">
																MSE
															</label>
														</div>
														<div class="col-md-12 col-xs-12">
															<label class="checkbox-inline"> <input
																type="checkbox" name="isNSIC" value="Y" class="NSIC">
																NSIC
															</label>
														</div>
														<div class="col-md-12 col-xs-12">
															<label class="checkbox-inline"> <input
																type="checkbox" name="isPropReport" value="Y"
																class="PropReport"> Prop Report
															</label>
														</div>
													</div>
												</div>
												<label style="display: none;" class="col-md-6 col-xs-12">
													<input style="display: none;" type="text" name="materialId"
													class="form-control Id" id="Id">
												</label>
												<div class="clearfix"></div>
												<div class="col-md-12 text-center">
													<button type="button" class="btn btn-info save"
														id="savenewmaterial">Save</button>
													<!-- <button  type="button"  class="btn btn-info save" id="editselectedmaterial">Save</button> -->
													<button type="button" class="btn btn-info cancel"
														id="canclematerial">Cancel</button>
												</div>
											</sf:form>

											<div class="form-group">
												<div class="col-sm-2">
													<button type="button"
														class="btn btn-info bluebutton addMatPopup">Copy
														GTP From Material</button>

												</div>
											</div>
											<div class="form-group">
												<div class="col-sm-2">
													<button type="button" class="btn btn-info bluebutton"
														id="deleteCopiedGtpBtnId">Delete Copied GTP</button>
												</div>
											</div>


										</div>
										<div class="clearfix"></div>
									</div>
								</div>
							</div>
							<!--fields of field group 1  -->
							<!-- field 2 -->
							<div id="BOMDiv">
								<div class="detailscont">
									<div class="col-md-12" id="materialDetails"></div>
								</div>
								<div class="board">
									<div class="board-inner">
										<ul class="nav nav-tabs" id="myTab">
											<div class="liner"></div>
											<li class="active"><a href="#home" data-toggle="tab"
												title="Information"> <span class="round-tabs one">
														<i class="fa fa-info" aria-hidden="true"></i>
												</span>
											</a></li>
										</ul>
									</div>
									<div id="tabstrip2">
										<ul>
											<li id="bomVersionTab"
												class="k-state-active bomVersionClassTab"
												onclick="return submitWithParam('getBOMListFromMaterial','materialId','onBomVersionTabLoad');">BOM
												Version</li>
											<li class="readonly" id="partsTab"
												onclick="return submitWithParam('getMaterialSpecificationFromBomId','bomVersionId','partsTabLoad');">Parts</li>
										</ul>

										<!--tab inside tab fields 1  -->

										<div id="bomVersionDiv">
											<div class="col-sm-12" style="margin-top: 20px;">

												<div class="tab-content col-md-11">
													<div class="tab-pane active" id="tab_a">
														<sf:form id="bomVersionForm" modelAttribute="bomVersion"
															method="POST">
															<div class="form-group">
																<div class="col-sm-12 ">
																	<h4 class="col-xs-6 nopadding">
																		<b>BOM Version</b>
																	</h4>
																	<div class="col-xs-6 text-right nopadding"
																		style="margin-top: 10px;">
																		 <!-- <button class="btn btn-default" id="addBom">
																			<span class="glyphicon glyphicon-plus-sign"></span>Add
																		</button> -->
																		<button class="btn btn-default" id="editBom">
																			<span class="glyphicon glyphicon-pencil"></span>Edit
																		</button>
																		<button class="btn btn-default" id="deleteBom">
																			<span class="glyphicon glyphicon-trash"></span>Delete
																		</button>
																	</div>
																	<hr>
																</div>
															</div>
															<div class="form-group" class="readonly" id="bomVersionFormId">

																<input type="hidden" id="bomVersionId"
																	class="bomVersionId" name="bomVersionId" value="" /> <input
																	type="hidden" id="materialId"
																	name="material.materialId" value="" />

																<div class="col-sm-4">
																	<div class="styled-input readonly">
																		<input type="text" id="materialName"
																			class="materialName" required="required" /> <label>Material
																			Name<span class="red">*</span>
																		</label> <span></span>
																	</div>
																</div>
																<div class="col-sm-4">
																	<div class="styled-input">
																		<input type="text" id="bomVersionName"
																			class="bomVersionName onlyNumber" name="name"
																			 /> <label>Version<span
																			class="red">*</span></label> <span></span>
																	</div>
																</div>
																<div class="col-sm-4">
																	<div class="col-md-12 col-xs-12">
																		<label class="checkbox-inline"> <input
																			type="checkbox" value="" class="isStandard"
																			name="isStandard"> Standard
																		</label>
																	</div>
																</div>
															</div>
															<div class="clearfix"></div>
															<div class="col-sm-12 text-center positionABS"
																style="margin-bottom: 10px;">
																<button
																	class="btn-all btn btn-info   btnPrevious pull-left tab-li-prev">Previous</button>
																<button class="btn btn-info save disableBtn"
																	onclick="return submitIt('bomVersionForm','saveBomVersion','bomVersionResp');">Save</button>
																<button class="btn btn-info cancel disableBtn"
																	id="cancelCompContactBtnId">Cancel</button>
																<button
																	class="btn-all btn btn-info   btnNext pull-right tab-li-next">Next</button>
															</div>
															<!-- </form> -->
														</sf:form>
													</div>

												</div>
												<!-- tab content -->
											</div>
										</div>

										<div id="partsTab">

											<div class="tab-content col-md-11">
												<div class="tab-pane active" id="tab_e">
													<sf:form id="SpecPartstForm"
														modelAttribute="materialSpecification" method="POST">
														<div class="form-group">
															<div class="col-sm-12 ">
																<h4 class="col-xs-6 nopadding">
																	<b>Material Parts</b>
																</h4>
																<div class="col-xs-6 text-right nopadding"
																	style="margin-top: 10px;">
																	 <!-- <button class="btn btn-default" id="addSpecBtnId">
																		<span class="glyphicon glyphicon-plus-sign"></span>Add
																	</button>  -->
																	<button class="btn btn-default" id="editSpecBtnId">
																		<span class="glyphicon glyphicon-pencil"></span>Edit
																	</button>
																	<button class="btn btn-default" id="deleteSpecBtnId">
																		<span class="glyphicon glyphicon-trash"></span>Delete
																	</button>
																</div>
																<hr>
															</div>
														</div>
														<div class="form-group">
															<input type="hidden" id="bomVersionId"
																class="bomVersionId" name="material.bomVersionId"
																value="" /> <input type="hidden"
																id="materialSpecificationId"
																name="materialSpecificationId" value="" />
															<!--  <input type="hidden"id="matId"  value="" /> -->

															<div class="col-sm-4">
																<div class="styled-input readonly">
																	<input type="text" id="bomName" class="bomName"
																		required="required" /> <label>BOM Version<span
																		class="red">*</span></label>

																</div>
															</div>
															<div class="col-sm-3">
																<div class="styled-input slate">
																	<select id="specName" class="specName"
																		name="specification.materialId" required="required">
																	</select> <label>Parts<span class="red">*</span></label> <span></span>
																</div>
															</div>
															<div class="col-sm-1">
																<button class="btn btn-default aditbt addMatPopup"
																	id="materialId">Add Parts</button>
															</div>
															<div class="col-sm-4">
																<div class="styled-input">
																	<input type="text" id="Quantity" class="Quantity onlyNumber"
																		name="quantity" /> <label>Quantity
																		(per Set)</label> <span></span>
																</div>
															</div>

														</div>
														<div class="col-md-6 col-xs-12">
															<label class="checkbox-inline"> <input
																type="checkbox" value="" class=" isActive">
																Active
															</label>
														</div>
														<div class="clearfix"></div>
														<div class="col-md-12 text-center">
															<button class="btn btn-info save disableBtn" id="saveParts"
																onclick="return submitIt('SpecPartstForm','saveSpecification','materialSpecificationResp');">Save</button>
															<button class="btn btn-info cancel disableBtn"
																id="cancelSpecsBtnId">Cancel</button>
														</div>
														<!-- </form> -->
													</sf:form>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
							<!-- field 2 -->
							<div>
								<div class="detailscont">
									<div class="col-md-12" class="masterDetails"></div>

								</div>
								<div class="board">
									<div class="board-inner">
										<ul class="nav nav-tabs" id="myTab">
											<div class="liner"></div>
											<li class="active"><a href="#home" data-toggle="tab"
												title="Information"> <span class="round-tabs one">
														<i class="fa fa-info" aria-hidden="true"></i>
												</span>
											</a></li>
										</ul>
									</div>
									<div class="form-group">
										<div class="col-sm-12">
											<h4>
												<b>GTP Details</b>
											</h4>
											<hr>
										</div>
									</div>
									<form id="gtpDetailFormId">
										<div class="form-group">
											<input type="text" style="display: none;" id="gtpId" />
											<div class="col-sm-4">
												<label>GTP Name: </label> <span class="detspan" id="gtpName"></span>
											</div>
											<div class="col-sm-4">
												<label>Description: </label> <span class="detspan"
													id="description"></span>
											</div>
											<div class="col-sm-4 readonly">
												<label class="checkbox-inline"> <input
													type="checkbox" id="isCopied" class="readonly"> IS
													Copied
												</label>
											</div>

										</div>
									</form>
								</div>
							</div>

							<!-- material Version Start-->
							<div id="MaterialVersionDiv">
								<div class="detailscont">
									<div class="col-md-12" id="materialDetails"></div>
								</div>
								<div class="board">
									<div class="board-inner">
										<ul class="nav nav-tabs" id="myTab">
											<div class="liner"></div>
											<li class="active"><a href="#home" data-toggle="tab"
												title="Information"> <span class="round-tabs one">
														<i class="fa fa-info" aria-hidden="true"></i>
												</span>
											</a></li>
										</ul>
									</div>
									<div class="col-sm-12 text-right" style="margin-top: 2px;">
										<!-- <button class="btn btn-default bluebutton"
											id="addVersionBtnId">
											<span class="glyphicon glyphicon-plus-sign"></span>Add
										</button> -->
										<button type="button" class="btn btn-info bluebutton" id="editVersion">
											<span class="glyphicon glyphicon-edit"></span>Edit
										</button>
										<button class="btn btn-default bluebutton"
											id="delVersionBtnId"
											onclick="return submitWithParam('deleteMaterialVersion','materialVersionId','materialVersionDelResp');">
											<span class="glyphicon glyphicon-trash"></span>Delete
										</button>
									</div>
									<div class="clearfix"></div>

									<div class="tab-content">
										<div class="tab-pane fade in active" id="home">
											<sf:form id="materialVersionForm"
												modelAttribute="materialVersion" method="POST">
												<input type="hidden" id="materialVersionId"
													name="materialVersionId">
												<div class="form-group">
													<div class="col-sm-4">
														<div class="styled-input">
															<input type="text" id="name" name="name"
																class="Name requiredField" required="required" /> <label>Name<span
																class="red">*</span></label> <span></span>
														</div>
													</div>
													<div class="col-sm-4">
														<div class="styled-input">
															<input type="text" id="code" name="code"
																class="Code onlyNumber requiredField"
																required="required" /> <label>Version Number<span
																class="red">*</span></label> <span></span>
														</div>
													</div>
													<div class="col-sm-4">
														<div class="styled-input">
															<textarea id="description" name="description"
																class="Description" required="required"></textarea>
															<!-- <input type="text" id="description" name="description" class="Description" required="required" /> -->
															<label>Description</label> <span></span>
														</div>
													</div>
													<div class="col-sm-3 readonly">
														<div class="styled-input">
															<select id="material" name="material.materialId"
																class="Material dropDown">
																<%-- <option value=""></option>
																<c:forEach var="material" items="${material}">
																   <option value="${material.materialId}">${material.name}</option>
																</c:forEach> --%>
															</select> <label>Material<span class="red">*</span></label> <span></span>
														</div>

													</div>
													<div class="col-sm-1 readonly">
														<button
															class="btn btn-default aditbt bluebutton addItemPopup"
															id="matrialAddItemBtn">Add Item</button>
													</div>
													<div class="col-sm-3">
														<label>Version Specification<span class="red">*</span></label>
														<div class="input-group">
															<input type="file" id="versionSpcificationId"
																data-id="versionSpecification"
																data-name="versionSpecificationName"
																data-anchor="a_versionSpecification"
																class="form-control uploadFile"> <input
																type="hidden" id="versionSpecification"
																name="versionSpecification.attachmentId"
																class="form-control" /> <span class="input-group-btn">
																<button
																	class="btn btn-default versionSpecification bluebutton"
																	onclick="return submitWithParam('deleteAttachment','versionSpecification','versionSpecificationDeleteResp');"
																	disabled="disabled">
																	<i class="fa fa-times"></i>
																</button>
															</span>
														</div>
														<!--  <input type="hidden" id="licenceFileName" name="licenceCopy.fileName" class="form-control" /> -->
														<a class="col-sm-12 filelabel"
															data-url="<%=request.getContextPath()%>/download"
															id="a_versionSpecification"></a>
													</div>
												</div>
												<label style="display: none;" class="col-md-6 col-xs-12">
													<input style="display: none;" type="text"
													class="form-control Id">
												</label>
												<div class="col-md-6 col-xs-12">
													<label class="checkbox-inline readonly"> <input
														type="checkbox" name="isActive" id="isActive"
														class="readonly" value="Y" checked="checked">
														Active
													</label>
												</div>
												<div class="clearfix"></div>
												<div class="col-md-12 text-center">
													<button class="btn btn-info save saveMatVersion"
														onclick="return submitIt('materialVersionForm','saveMaterialVersion','materialVersionResp');">Save</button>
													<button class="btn btn-info cancel" id="cancelVersionBtnId">Cancel</button>
												</div>
											</sf:form>
										</div>
										<div class="clearfix"></div>
									</div>
								</div>
							</div>
							<!-- material Version end-->
						</div>
				</section>
			</div>


		</div>
		<!-- Master tab end-->


	</div>
	<!-- right-side end-->
</div>
<script src="<%=request.getContextPath()%>/resource/js/material.js?appVer=${appVer}"></script>
 <script src="<%=request.getContextPath()%>/resource/js/materialGtp.js?appVer=${appVer}"></script>
 <script src="<%=request.getContextPath()%>/resource/js/bomVersion.js?appVer=${appVer}"></script>
 <script src="<%=request.getContextPath()%>/resource/js/materialParts.js?appVer=${appVer}"></script>
 <script src="<%=request.getContextPath()%>/resources/${appMode}/master/js/materialVersion.js?appVer=${appVer}"></script>
 <script src="<%=request.getContextPath()%>/resources/${appMode}/commons/js/formFields.js?appVer=${appVer}"></script>
 <script src="<%=request.getContextPath()%>/resources/${appMode}/commons/js/springform.js?appVer=${appVer}"></script>
<script src="<%=request.getContextPath()%>/resources/${appMode}/commons/js/utility.js?appVer=${appVer}"></script>
<script src="<%=request.getContextPath()%>/resources/${appMode}/commons/js/loadList.js?appVer=${appVer}"></script>
<script src="<%=request.getContextPath()%>/resources/${appMode}/commons/js/formFields.js?appVer=${appVer}"></script>
<script src="<%=request.getContextPath()%>/resources/${appMode}/commons/js/loadItemList.js?appVer=${appVer}"></script>
 <script src="<%=request.getContextPath()%>/resources/${appMode}/partner/js/uploadFile.js?appVer=${appVer}"></script>

<script type="text/javascript">
	$("#tabstrip").kendoTabStrip();
	$("#tabstrip2").kendoTabStrip();
</script>