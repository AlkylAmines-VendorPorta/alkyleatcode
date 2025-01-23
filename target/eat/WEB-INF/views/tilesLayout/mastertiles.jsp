<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@taglib uri = "http://www.springframework.org/tags/form" prefix = "sf"%>
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/${appMode}/tilescommon/css/mobile.css?appVer=${appVer}">

 <c:choose>
  <c:when test="${sessionScope.userThemeColor != null}">
   <body class="${sessionScope.userThemeColor}">
  </c:when>
  <c:otherwise>
   <body class="tiles_background">
  </c:otherwise>
</c:choose>
  <input type = "hidden" id = "tahdrTileType" value = 2>
 
    <!-- full-container start -->
    <div class="full-container animated slideInLeft">
        <!-- MainProductTiles start-->

		<!-- MainProductTiles end-->
		<!-- ProductDetailsTiles start-->
		<div id="ProductDetailsTiles" class="container top20">

			<!--MasterDetailsTiles-->
			<div id="MasterDetailsTiles">
				<c:forEach var="authorisedTiles" items="${authorisedTiles}"
					varStatus="count">
					<c:if test="${authorisedTiles== 'Add User'}">
						<div class="col-md-2 col-xs-6">
							<a href="getUserView" id="">
								<div class="tiles text-center">
									<i class="fa fa-eye pull-left font36"></i><i
										class="fa fa-plus pull-left font36"></i><i
										class="fa fa-pencil pull-left font36"></i>
									<h4 class="tiles-heading">Add User</h4>
									<h6 class="tiles-count">
										<i id="addUser" class="fa fa-refresh fa-spin"></i> Records
									</h6>
									<div class="clearfix"></div>
								</div>
							</a>
						</div>
					</c:if>
					<c:if test="${authorisedTiles== 'Material'}">
						<div class="col-md-2 col-xs-6">
							<a href="material" id="">
								<div class="tiles text-center">
									<i class="fa fa-eye pull-left font36"></i>
									<h4 class="tiles-heading">Material</h4>
									<h6 class="tiles-count">
										<i id="Material" class="fa fa-refresh fa-spin"></i> Records
									</h6>

									<div class="clearfix"></div>
								</div>
							</a>
						</div>
					</c:if>
					<c:if test="${authorisedTiles== 'Document Type'}">
						<div class="col-md-2 col-xs-6">
							<a href="DocumentType" id="">
								<div class="tiles text-center">
									<i class="fa fa-eye pull-left font36"></i>
									<h4 class="tiles-heading">Document Type</h4>
									<h6 class="tiles-count">
										<i id="DocumentType" class="fa fa-refresh fa-spin"></i>
										Records
									</h6>

									<div class="clearfix"></div>
								</div>
							</a>
						</div>
					</c:if>
					<c:if test="${authorisedTiles== 'Tender Type'}">
						<div class="col-md-2 col-xs-6">
							<a href="TenderType" id="">
								<div class="tiles text-center">
									<i class="fa fa-eye pull-left font36"></i>
									<h4 class="tiles-heading">Tender Type</h4>
									<h6 class="tiles-count">
										<i id="TenderType" class="fa fa-refresh fa-spin"></i> Records
									</h6>

									<div class="clearfix"></div>
								</div>
							</a>
						</div>
					</c:if>
					<c:if test="${authorisedTiles== 'Tender Budget Type'}">
						<div class="col-md-2 col-xs-6">
							<a href="TenderBudgetType" id="">
								<div class="tiles text-center">
									<i class="fa fa-eye pull-left font36"></i>
									<h4 class="tiles-heading">Tender Budget Type</h4>
									<h6 class="tiles-count">
										<i id="TenderBudgetType" class="fa fa-refresh fa-spin"></i>
										Records
									</h6>

									<div class="clearfix"></div>
								</div>
							</a>
						</div>
					</c:if>
					<c:if test="${authorisedTiles== 'Bid Type'}">
						<div class="col-md-2 col-xs-6">
							<a href="BidType" id="">
								<div class="tiles text-center">
									<i class="fa fa-eye pull-left font36"></i>
									<h4 class="tiles-heading">Bid Type</h4>
									<h6 class="tiles-count">
										<i id="BidType" class="fa fa-refresh fa-spin"></i> Records
									</h6>
									<div class="clearfix"></div>

									<div class="clearfix"></div>
								</div>
							</a>
						</div>
					</c:if>
					<c:if test="${authorisedTiles== 'UOM'}">
						<div class="col-md-2 col-xs-6">
							<a href="uom" id="">
								<div class="tiles text-center">
									<i class="fa fa-eye pull-left font36"></i>
									<h4 class="tiles-heading">UOM</h4>
									<h6 class="tiles-count">
										<i id="UOM" class="fa fa-refresh fa-spin"></i> Records
									</h6>

									<div class="clearfix"></div>
								</div>
							</a>
						</div>
					</c:if>
					<c:if test="${authorisedTiles== 'Payment Type'}">
						<div class="col-md-2 col-xs-6">
							<a href="PaymentType" id="">
								<div class="tiles text-center">
									<i class="fa fa-eye pull-left font36"></i>
									<h4 class="tiles-heading">Payment Type</h4>
									<h6 class="tiles-count">
										<i id="PaymentType" class="fa fa-refresh fa-spin"></i> Records
									</h6>

									<div class="clearfix"></div>
								</div>
							</a>
						</div>
					</c:if>
					<c:if test="${authorisedTiles== 'Contractor Type'}">
						<div class="col-md-2 col-xs-6">
							<a href="ContractorType" id="">
								<div class="tiles text-center">
									<i class="fa fa-eye pull-left font36"></i>
									<h4 class="tiles-heading">Contractor Type</h4>
									<h6 class="tiles-count">
										<i id="ContractorType" class="fa fa-refresh fa-spin"></i>
										Records
									</h6>

									<div class="clearfix"></div>
								</div>
							</a>
						</div>
					</c:if>
					<c:if test="${authorisedTiles== 'Material Group'}">
						<div class="col-md-2 col-xs-6">
							<a href="materialGroup" id="">
								<div class="tiles text-center">
									<i class="fa fa-eye pull-left font36"></i>
									<h4 class="tiles-heading">Material Group</h4>
									<h6 class="tiles-count">
										<i id="MaterialGroup" class="fa fa-refresh fa-spin"></i>
										Records
									</h6>

									<div class="clearfix"></div>
								</div>
							</a>
						</div>
					</c:if>
					<c:if test="${authorisedTiles== 'Material SubGroup'}">
						<div class="col-md-2 col-xs-6">
							<a href="MaterialSubGroup" id="">
								<div class="tiles text-center">
									<i class="fa fa-eye pull-left font36"></i>
									<h4 class="tiles-heading">Material Sub-Group</h4>
									<h6 class="tiles-count">
										<i id="MaterialSubGroup" class="fa fa-refresh fa-spin"></i>
										Records
									</h6>
									<div class="clearfix"></div>
								</div>
							</a>
						</div>
					</c:if>
					<c:if test="${authorisedTiles== 'Material Specification'}">
						<div class="col-md-2 col-xs-6">
							<a href="MaterialSpecification" id="">
								<div class="tiles text-center">
									<i class="fa fa-eye pull-left font36"></i>
									<h4 class="tiles-heading">Material Specification</h4>
									<h6 class="tiles-count">
										<i id="MaterialSpecification" class="fa fa-refresh fa-spin"></i>
										Records
									</h6>
									<div class="clearfix"></div>
								</div>
							</a>
						</div>
					</c:if>
					<c:if test="${authorisedTiles== 'Department'}">
						<div class="col-md-2 col-xs-6">
							<a href="departmentView" id="">
								<div class="tiles text-center">
									<i class="fa fa-eye pull-left font36"></i>
									<h4 class="tiles-heading">Department</h4>
									<h6 class="tiles-count">
										<i id="Department" class="fa fa-refresh fa-spin"></i> Records
									</h6>
									<div class="clearfix"></div>
								</div>
							</a>
						</div>
					</c:if>
					<c:if test="${authorisedTiles== 'Material Version'}">
						<div class="col-md-2 col-xs-6">
							<a href="MaterialVersion" id="">
								<div class="tiles text-center">
									<i class="fa fa-eye pull-left font36"></i><i
										class="fa fa-plus pull-left font36"></i><i
										class="fa fa-pencil pull-left font36"></i>
									<h4 class="tiles-heading">Material Version</h4>
									<h6 class="tiles-count">
										<i id="MaterialVersion" class="fa fa-refresh fa-spin"></i>
										Records
									</h6>
									<div class="clearfix"></div>
								</div>
							</a>
						</div>
					</c:if>
					<c:if test="${authorisedTiles== 'GTP Parameter'}">
						<div class="col-md-2 col-xs-6">
							<a href="GtpParameter" id="">
								<div class="tiles text-center">
									<i class="fa fa-eye pull-left font36"></i><i
										class="fa fa-plus pull-left font36"></i><i
										class="fa fa-pencil pull-left font36"></i>
									<h4 class="tiles-heading">GTP Parameters</h4>
									<h6 class="tiles-count">
										<i id="GtpParameter" class="fa fa-refresh fa-spin"></i>
										Records
									</h6>
									<div class="clearfix"></div>
								</div>
							</a>
						</div>
					</c:if>
					<c:if test="${authorisedTiles== 'GTP ParameterType'}">
						<div class="col-md-2 col-xs-6">
							<a href="GtpParameterType" id="">
								<div class="tiles text-center">
									<i class="fa fa-eye pull-left font36"></i><i
										class="fa fa-plus pull-left font36"></i><i
										class="fa fa-pencil pull-left font36"></i>
									<h4 class="tiles-heading">GTP Parameters Type</h4>
									<h6 class="tiles-count">
										<i id="GtpParameterType" class="fa fa-refresh fa-spin"></i>
										Records
									</h6>
									<div class="clearfix"></div>
								</div>
							</a>
						</div>
					</c:if>
					<c:if test="${authorisedTiles== 'Public Notices'}">
						<div class="col-md-2 col-xs-6">
							<a href="PublicNotice" id="">
								<div class="tiles text-center">
									<i class="fa fa-eye pull-left font36"></i><i
										class="fa fa-plus pull-left font36"></i><i
										class="fa fa-pencil pull-left font36"></i>
									<h4 class="tiles-heading">Public Notices</h4>
									<h6 class="tiles-count">
										<i id="PublicNotice" class="fa fa-refresh fa-spin"></i>
										Records
									</h6>
									<div class="clearfix"></div>
								</div>
							</a>
						</div>
					</c:if>
					<c:if test="${authorisedTiles== 'Is Standard'}">
						<div class="col-md-2 col-xs-6">
							<a href="IsStd" id="">
								<div class="tiles text-center">
									<i class="fa fa-eye pull-left font36"></i>
									<h4 class="tiles-heading">IS-STD</h4>
									<h6 class="tiles-count">
										<i id="IsStd" class="fa fa-refresh fa-spin"></i> Records
									</h6>
									<div class="clearfix"></div>
								</div>
							</a>
						</div>
					</c:if>
					<c:if test="${authorisedTiles== 'Designation'}">
						<div class="col-md-2 col-xs-6">
							<a href="designation" id="">
								<div class="tiles text-center">
									<i class="fa fa-eye pull-left font36"></i>
									<h4 class="tiles-heading">Designation</h4>
									<h6 class="tiles-count">
										<i id="Designation" class="fa fa-refresh fa-spin"></i> Records
									</h6>
									<div class="clearfix"></div>
								</div>
							</a>
						</div>
					</c:if>
					<c:if test="${authorisedTiles== 'Company Type'}">
						<div class="col-md-2 col-xs-6">
							<a href="CompanyType" id="">
								<div class="tiles text-center">
									<i class="fa fa-eye pull-left font36"></i>
									<h4 class="tiles-heading">Company Type</h4>
									<h6 class="tiles-count">
										<i id="CompanyType" class="fa fa-refresh fa-spin"></i> Records
									</h6>
									<div class="clearfix"></div>
								</div>
							</a>
						</div>
					</c:if>
					<c:if test="${authorisedTiles== 'Registration Type'}">
						<div class="col-md-2 col-xs-6">
							<a href="RegistrationType" id="">
								<div class="tiles text-center">
									<i class="fa fa-eye pull-left font36"></i>
									<h4 class="tiles-heading">Registration Type</h4>
									<h6 class="tiles-count">
										<i id="RegistrationType" class="fa fa-refresh fa-spin"></i>
										Records
									</h6>
									<div class="clearfix"></div>
								</div>
							</a>
						</div>
					</c:if>
					<c:if test="${authorisedTiles== 'Tax'}">
						<div class="col-md-2 col-xs-6">
							<a href="tax" id="">
								<div class="tiles text-center">
									<i class="fa fa-eye pull-left font36"></i>
									<h4 class="tiles-heading">Tax</h4>
									<h6 class="tiles-count">
										<i id="Tax" class="fa fa-refresh fa-spin"></i> Records
									</h6>
									<div class="clearfix"></div>
								</div>
							</a>
						</div>
					</c:if>
					<c:if test="${authorisedTiles== 'Tax Category'}">
						<div class="col-md-2 col-xs-6">
							<a href="TaxCategory" id="">
								<div class="tiles text-center">
									<i class="fa fa-eye pull-left font36"></i>
									<h4 class="tiles-heading">Tax Category</h4>
									<h6 class="tiles-count">
										<i id="TaxCategory" class="fa fa-refresh fa-spin"></i> Records
									</h6>
									<div class="clearfix"></div>
								</div>
							</a>
						</div>
					</c:if>
					<c:if test="${authorisedTiles== 'Holder Type'}">
						<div class="col-md-2 col-xs-6">
							<a href="HolderType" id="">
								<div class="tiles text-center">
									<i class="fa fa-eye pull-left font36"></i>
									<h4 class="tiles-heading">Holder Type</h4>
									<h6 class="tiles-count">
										<i id="HolderType" class="fa fa-refresh fa-spin"></i> Records
									</h6>
									<div class="clearfix"></div>
								</div>
							</a>
						</div>
					</c:if>
					<c:if test="${authorisedTiles== 'Role'}">
						<div class="col-md-2 col-xs-6">
							<a href="role" id="">
								<div class="tiles text-center">
									<i class="fa fa-eye pull-left font36"></i>
									<h4 class="tiles-heading">Role</h4>
									<h6 class="tiles-count">
										<i id="Role" class="fa fa-refresh fa-spin"></i> Records
									</h6>
									<div class="clearfix"></div>
								</div>
							</a>
						</div>
					</c:if>
					<c:if test="${authorisedTiles== 'Material Type'}">
						<div class="col-md-2 col-xs-6">
							<a href="MaterialType" id="">
								<div class="tiles text-center">
									<i class="fa fa-eye pull-left font36"></i>
									<h4 class="tiles-heading">Material Type</h4>
									<h6 class="tiles-count">
										<i id="MaterialType" class="fa fa-refresh fa-spin"></i>
										Records
									</h6>
									<div class="clearfix"></div>
								</div>
							</a>
						</div>
					</c:if>
					<c:if test="${authorisedTiles== 'Vendor Type'}">
						<div class="col-md-2 col-xs-6">
							<a href="VendorType" id="">
								<div class="tiles text-center">
									<i class="fa fa-eye pull-left font36"></i>
									<h4 class="tiles-heading">Vendor Type</h4>
									<h6 class="tiles-count">
										<i id="VendorType" class="fa fa-refresh fa-spin"></i> Records
									</h6>
									<div class="clearfix"></div>
								</div>

							</a>
						</div>
					</c:if>
					<c:if test="${authorisedTiles== 'Exempted Categories'}">
						<div class="col-md-2 col-xs-6">
							<a href="ExemptedCategories" id="">
								<div class="tiles text-center">
									<i class="fa fa-eye pull-left font36"></i><i
										class="fa fa-plus pull-left font36"></i><i
										class="fa fa-pencil pull-left font36"></i>
									<h4 class="tiles-heading">Exempted Categories</h4>
									<h6 class="tiles-count">
										<i id="ExemptedCategories" class="fa fa-refresh fa-spin"></i>
										Records
									</h6>
									<div class="clearfix"></div>
								</div>
							</a>
						</div>
					</c:if>
					<c:if test="${authorisedTiles== 'Financial Documents'}">
						<div class="col-md-2 col-xs-6">
							<a href="FinancialDocuments" id="">
								<div class="tiles text-center">
									<i class="fa fa-eye pull-left font36"></i>
									<h4 class="tiles-heading">Financial Documents</h4>
									<h6 class="tiles-count">
										<i id="FinancialDocuments" class="fa fa-refresh fa-spin"></i>
										Records
									</h6>
									<div class="clearfix"></div>
								</div>
							</a>
						</div>
					</c:if>
					<c:if test="${authorisedTiles== 'Business Partner Group'}">
						<div class="col-md-2 col-xs-6">
							<a href="BusinessPartnerGroup" id="">
								<div class="tiles text-center">
									<i class="fa fa-eye pull-left font36"></i>
									<h4 class="tiles-heading">Business Partner Group</h4>
									<div class="clearfix"></div>
								</div>
							</a>
						</div>
					</c:if>
					<c:if test="${authorisedTiles== 'CMS'}">
						<div class="col-md-2 col-xs-6">
							<a href="cms" id="">
								<div class="tiles text-center">
									<i class="fa fa-eye pull-left font36"></i>
									<h4 class="tiles-heading">CMS</h4>
									<div class="clearfix"></div>
								</div>
							</a>
						</div>
					</c:if>
					<c:if test="${authorisedTiles== 'Reference'}">
				
				<div class="col-md-2 col-xs-6">
					<a href="reference" id="">
						<div class="tiles text-center">
							<i class="fa fa-eye pull-left font36"></i>
							<h4 class="tiles-heading">Reference</h4>
							<div class="clearfix"></div>
						</div>
					</a>
				</div>
				</c:if>
					<c:if test="${authorisedTiles== 'Location Type'}">
				<div class="col-md-2 col-xs-6">
					<a href="locationType" id="">
						<div class="tiles text-center">
							<i class="fa fa-eye pull-left font36"></i>
							<h4 class="tiles-heading">Location Type</h4>
							<div class="clearfix"></div>
						</div>
					</a>
				</div>
				</c:if>
					<c:if test="${authorisedTiles== 'HSN'}">
				<div class="col-md-2 col-xs-6">
					<a href="hsn" id="">
						<div class="tiles text-center">
							<i class="fa fa-eye pull-left font36"></i>
							<h4 class="tiles-heading">hsn</h4>
							<div class="clearfix"></div>
						</div>
					</a>
				</div>
				</c:if>
					<c:if test="${authorisedTiles== 'Rating Weightage'}">
				<div class="col-md-2 col-xs-6">
					<a href="ratingWeightage" id="">
						<div class="tiles text-center">
							<i class="fa fa-eye pull-left font36"></i>
							<h4 class="tiles-heading">Rating Weightage</h4>
							<div class="clearfix"></div>
						</div>
					</a>
				</div>
				</c:if>
					<c:if test="${authorisedTiles== 'System Configurator'}">
				<div class="col-md-2 col-xs-6">
							<a href="sysConfigurator" id="">
								<div class="tiles text-center">
									<i class="fa fa-eye pull-left font36"></i>
									<h4 class="tiles-heading">System Configurator</h4>
									
									<div class="clearfix"></div>
								</div>

							</a>
						</div>
				</c:if>
				
				</c:forEach>
			</div>
			
			<!--  MasterDetailsTiles	end -->

		</div>
		<!-- ProductDetailsTiles end-->
	</div>

	<!-- full-container end-->
	
		<script src ="<%=request.getContextPath()%>/resources/${appMode}/tilescommon/js/tenderTilesRecordCount.js?appVer=${appVer}"></script>
</body>