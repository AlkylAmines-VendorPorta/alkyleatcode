<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@taglib uri = "http://www.springframework.org/tags/form" prefix = "sf"%>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/resources/tilescommon/css/fioristyle.css" />

<div class="full-container">
	<!-- left-side start-->
	<div class="clearfix"></div>
	<div id="mobile_first_container" class="left-side col-md-3 no-marg">
		<div class="detailsheader">
			<div class="col-sm-3 text-center brdrgt">
				<label>Rating Weightage Report (<span class="reportCount">0</span>)
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
				id="search_param"> <input type="text"
				class="search-query form-control" name="x" id="searchLiteralId"
				placeholder="Search term..."> <span class="input-group-btn">
				<button class="btn btn-default" type="button" onclick="searchuom()">
					<span class="glyphicon glyphicon-search"></span>
				</button>
				<button class="btn btn-default addnewList addRatingWeight" onclick="addRating(event)"
					type="button">
					<span class="glyphicon glyphicon-plus"></span>
				</button>
			</span>
		</div>
		<ul id="example" class="nav nav-tabs tabs-left example">


		</ul>

		<div class="clearfix"></div>
	</div>
	<!-- left-side end-->

	<!-- right-side start-->
	<div id="mobile_second_container" class="right-side col-md-9 no-marg">
		<div class="detailsheader">
			<div class="col-sm-9 text-center">
				<label>Rating Weightage</label>
			</div>
		</div>
		<div class="clearfix"></div>
		<div class="detailscont">
			<div class="col-md-12" id="masterDetails"></div>


		</div>
		<div class="tab-content">
			<!-- Master tab start-->

			<div class="tab-pane active in" id="">
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
								<div class="col-sm-12 text-right">
									<div class="form-group">
										<!-- <button type="button" class="btn btn-info bluebutton"
											id="delRating">
											<span class="glyphicon glyphicon-trash"></span>Delete
										</button> -->

										<button type="button" class="btn btn-info bluebutton"
											id="editRating">
											<span class="glyphicon glyphicon-edit"></span>Edit
										</button>
									</div>
								</div>
								<div class="clearfix"></div>
								<sf:form id="ratingWeightageFormId" class="readonly" method="POST"
									autocomplete="off" modelAttribute="ratingWeightage">
									<div class="form-group">
										<div class="col-sm-4">
											<div class="styled-input">
												<input type="text" id="qualityWeightage" name="qualityWeightage" class="qualityWeightage onlyNumber requiredField"
													/> <label>Quality Weightage<span
													class="red">*</span></label> <span></span>
											</div>
										</div>
										<div class="col-sm-4">
											<div class="styled-input">
												<input type="text" id="priceWeightage" name="priceWeightage" class="priceWeightage onlyNumber requiredField"
													/> <label>Price Weightage<span
													class="red">*</span></label> <span></span>
											</div>
										</div>
										<div class="col-sm-4">
											<div class="styled-input">
												<input type="text" id="deliveryWeightage" name="deliveryWeightage" class="deliveryWeightage onlyNumber requiredField" 
												 /> <label>Delivery Weightage<span
													class="red">*</span></label> <span></span>
											</div>
										</div>
									<div class="col-sm-4">
											<div class="styled-input">
												<input type="text" id="serviceWeightage" name="serviceWeightage"
													class="serviceWeightage onlyNumber requiredField"   /> <label>Service Weightage<span
													class="red">*</span></label> <span></span>
											</div>
										</div>
									<label style="display: none;" class="col-md-6 col-xs-12">
										<input style="display: none;" type="text"
										class="form-control Id" name="ratingWeightageId" id="Id">
									</label>

									<div class="col-md-4" style="margin-top: 40px;">
										<!--  <label class="checkbox-inline">
						  <input type="checkbox" value="Y" class="isActive" checked="checked">
						  Active </label> -->
										<div class="col-sm-4">
											<label class="checkbox-inline "> <input
												type="checkbox" value="Y" name="isActive" class="isActive"
												checked="checked" />IsActive
											</label>
										</div>
										<input type="checkbox" style="display: none;" value="Y"
											name="isActive" class="isActive" checked="checked" />
									</div>
									</div>
									<div class="clearfix"></div>
									<div class="col-md-12 text-center">
										<button type="button" class="btn btn-info save saveRating"
											id="saveRating">Save</button>
										<!-- <button  type="button"  class="btn btn-info save" id="editselectedhsn">Save</button> -->
										<button type="button" class="btn btn-info cancel cancelRating" id="cancelRating">Cancel</button>
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
<script src="<%=request.getContextPath()%>/resource/js/ratingWeightage.js"></script>
<script
	src="<%=request.getContextPath()%>/resources/commons/js/formFields.js"></script>