<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/resources/${appMode}/tilescommon/css/fioristyle.css?appVer=${appVer}" />
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/resources/${appMode}/tilescommon/css//borderTab.css?appVer=${appVer}">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/resources/${appMode}/tilescommon/css/mobile.css?appVer=${appVer}" />

<div class="full-container">
	<!-- left-side start-->
	<div class="clearfix"></div>
	<div id="mobile_first_container" class="left-side col-md-3 no-marg">
		<div class="detailsheader">
			<div class="col-sm-3 text-center brdrgt">
				<label>Tile Report (<span class="reportCount">0</span>)
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
					<li><a href="#all"><input type="radio" name="filterBy"
							value="name" checked /> Name</a></li>
				</ul>
			</div>
			<input type="hidden" name="search_param" value="all"
				id="search_param"> <input type="text" class="form-control"
				name="x" id="searchLiteralId" placeholder="Search term...">
			<span class="input-group-btn">
				<button class="btn btn-default" type="button" id="searchBtn">
					<span class="glyphicon glyphicon-search"></span>
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
								<li class="k-state-active" id="dashboradTileTabId">Dash Board Tile</li>
								<li class="" id="subTileTabId">Sub Tile</li>
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
									<div class="form-group">
										<div class="col-sm-12">
											<h4>
												<b>Dash Board Tile Details</b>
											</h4>
											<hr>
										</div>
									</div>
									<div class="tab-content">
										<div class="tab-pane fade in active" id="home">
											<div class="col-sm-12 text-right" style="margin-top: 7px;"
												id="manupulationBtnId">
												<button type="button" id="addId" onclick="addTile(event)"
													class="btn btn-info bluebutton">
													<span class="glyphicon glyphicon-plus-sign"></span>Add
												</button>
												<button type="button" id="editId" onclick="editTile(event)"
													class="btn btn-info bluebutton">
													<span class="glyphicon glyphicon-edit"></span>Edit
												</button>
												<button type="button" id="deleteId"
													class="btn btn-info bluebutton" onclick="deleteTile(event)">
													<span class="glyphicon glyphicon-trash"></span>Delete
												</button>

											</div>
											<sf:form class="readonly" id="tileForm" method="POST"
												autocomplete="off" modelAttribute="TileMaster">
												<div class="clearfix"></div>
												<input type="text" style="display: none;" class=""
													id="p_tileMasterId" />
												<input type="text" style="display: none;" class=""
													id="tileMasterId" name="tileMasterId" />
												<div class="form-group">
													<div class="col-sm-4">
														<div class="styled-input">
															<input type="text" id="value"
																class="tileFormEle value requiredField" name="value" />
															<label>Name<span class="red">*</span></label> <span></span>
														</div>
													</div>
													<div class="col-sm-4">
														<div class="styled-input">
															<input type="text" id="code"
																class="tileFormEle code requiredField" name="code" /> <label>Code<span
																class="red">*</span></label> <span></span>
														</div>
													</div>

													<div class="clearfix"></div>
													<div class="col-sm-4">
														<div class="styled-input">
															<input type="text" id="description"
																class="tileFormEle requiredField" name="description" />
															<label>Description<span class="red">*</span></label> <span></span>
														</div>
													</div>


												</div>

												<div class="col-md-6 col-xs-12">
													<label class="checkbox-inline"> <input
														type="checkbox" id="isActive" value="Y" name="isActive"
														class="isActive" checked="checked" /> Active
													</label>
												</div>

												<div class="clearfix"></div>
												<div class="col-md-12 text-center">
													<button type="button"
														class="btn btn-info saveBtn save formBtn"
														onclick="return submitIt('tileForm','saveTileMaster','saveTileResp')">Save</button>
													<button type="button" id="cancelBtnId"
														class="btn btn-info CancelBtn cancel formBtn">Cancel</button>
												</div>
											</sf:form>
										</div>
										<div class="clearfix"></div>
									</div>
								</div>
							</div>
							<!--fields of field group 1  -->
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
												<b>Sub Tile Details</b>
											</h4>
											<hr>
										</div>
									</div>
									<div class="col-sm-12 text-right" style="margin-top: 7px;"
												id="manupulationBtnId">
												<button type="button" id="addSubtileId" onclick="addSubTile(event)"
													class="btn btn-info bluebutton">
													<span class="glyphicon glyphicon-plus-sign"></span>Add
												</button>
												<button type="button" id="editSubtileId" onclick="editSubTile(event)"
													class="btn btn-info bluebutton">
													<span class="glyphicon glyphicon-edit"></span>Edit
												</button>
												<button type="button" id="deleteSubtileId"
													class="btn btn-info bluebutton" onclick="deleteSubTile(event)">
													<span class="glyphicon glyphicon-trash"></span>Delete
												</button>

											</div>
									
									<form>
										<div class="form-group"></div>
									</form>
								</div>
							</div>
						</div>
				</section>
			</div>


		</div>
		<!-- Master tab end-->


	</div>
	<!-- right-side end-->
</div>
<script
	src="<%=request.getContextPath()%>/resources/${appMode}/master/js/tileMaster.js?appVer=${appVer}"></script>
<script
	src="<%=request.getContextPath()%>/resources/${appMode}/commons/js/springform.js?appVer=${appVer}"></script>
<script
	src="<%=request.getContextPath()%>/resources/${appMode}/commons/js/formFields.js?appVer=${appVer}"></script>
<script
	src="<%=request.getContextPath()%>/resources/${appMode}/commons/js/commonValidation.js?appVer=${appVer}"></script>
<script
	src="<%=request.getContextPath()%>/resources/${appMode}/commons/js/utility.js?appVer=${appVer}"></script>
<script type="text/javascript">
	$("#tabstrip").kendoTabStrip();
	$("#tabstrip2").kendoTabStrip();
</script>