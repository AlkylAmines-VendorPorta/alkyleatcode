<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/${appMode}/tilescommon/css/fioristyle.css?appVer=${appVer}" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/${appMode}/tilescommon/css/mobile.css?appVer=${appVer}"/>

<div class="full-container">
	<!-- left-side start-->
	<div class="clearfix"></div>
	<div id="mobile_first_container" class="left-side col-md-3 no-marg">
		<div class="detailsheader">
			<div class="col-sm-3 text-center brdrgt">
				<label>Material Version Report (<span class="reportCount">0</span>)
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
				<button class="btn btn-default" id="addVersionBtnId"  type="button">
					<span class="glyphicon glyphicon-plus"></span>
				</button>
			</span>
		</div>
		<ul id="example" class="nav nav-tabs tabs-left example leftPaneData">

		</ul>

		<div class="clearfix"></div>
	</div>
	<!-- left-side end-->

	<!-- right-side start-->
	<div id="mobile_second_container" class="right-side col-md-9 no-marg">
		<div class="detailsheader">
			<div class="col-sm-9 text-center">
				<label>Material Version Detail</label>
			</div>
		</div>
		<div class="clearfix"></div>
		<div class="detailscont">
			<div class="col-md-12" id="materialVersionHeaderId"></div>

		</div>
		<div class="tab-content">
			<!-- Master tab start-->

			<div class="tab-pane active in" id="AddMaterialGroup">
				<section>
					<div class="board">
						<div class="board-inner">
							<ul class="nav nav-tabs" id="myTab">

								<div class="liner"></div>
								<li class="active"><a href="#home" data-toggle="tab"
									title="Information"> <span class="round-tabs one"> <i
											class="fa fa-info" aria-hidden="true"></i>
									</span>
								</a></li>
							</ul>
						</div>
						<div class="tab-content">
							<div class="tab-pane fade in active" id="home">
							<div class="col-sm-12 text-right" style="margin-top: 7px;">
							<button type="button" id="editVersion"
										class="btn btn-info bluebutton">
										<span class="glyphicon glyphicon-edit"></span>Edit
									</button>
							<button type="button" id="delVersion"
								class="btn btn-info bluebutton">
								<span class="glyphicon glyphicon-trash"></span>Delete
							</button>
						</div>
						<div class="clearfix"></div>
								<sf:form id="materialVersionForm" class="readonly"
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
													class="Code onlyNumber requiredField" required="required" />
												<label>Version Number<span class="red">*</span></label> <span></span>
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
										<div class="col-sm-4">
											<div class="styled-input slate">
												<select id="material" name="material.materialId"
													class="Material dropDown" required="required">
													<option value=""></option>
													<c:forEach var="material" items="${material}">
														<option value="${material.materialId}">${material.name}</option>
													</c:forEach>
												</select> <label>Material<span class="red">*</span></label> <span></span>
											</div>
										</div>
										<div class="col-sm-3">
											<label>Version Specification</label>
											<div class="input-group">
												<input type="file" id="versionSpcificationId"
													data-id="versionSpecification"
													data-name="versionSpecificationName"
													data-anchor="a_versionSpecification"
													class="form-control uploadFile"> <input
													type="hidden" id="versionSpecification"
													name="versionSpecification.attachmentId"
													class="form-control" /> <span class="input-group-btn">
													<button class="btn btn-default versionSpecification"
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
											class="readonly" value="Y" checked="checked"> Active
										</label>
									</div>
									<div class="clearfix"></div>
									<div class="col-md-12 text-center">
										<button class="btn btn-info save saveVersion"
											onclick="return submitIt('materialVersionForm','saveMaterialVersion','materialVersionResp');">Save</button>
										<button class="btn btn-info cancel" id="cancelVersionBtnId">Cancel</button>
									</div>
								</sf:form>
							</div>
							<div class="clearfix"></div>
						</div>
					</div>
				</section>
			</div>

		</div>
		<!-- Master tab end-->

	</div>
	<!-- right-side end-->
</div>

<!-- full-container end-->
<script src="<%=request.getContextPath()%>/resources/${appMode}/master/js/materialVersion.js?appVer=${appVer}"></script>
 <script src="<%=request.getContextPath()%>/resources/${appMode}/partner/js/uploadFile.js?appVer=${appVer}"></script>
<script src="<%=request.getContextPath()%>/resources/${appMode}/commons/js/formFields.js?appVer=${appVer}"></script>
<script src="<%=request.getContextPath()%>/resources/${appMode}/commons/js/loadItemList.js?appVer=${appVer}"></script>
<script src="<%=request.getContextPath()%>/resources/${appMode}/commons/js/loadList.js?appVer=${appVer}"></script>