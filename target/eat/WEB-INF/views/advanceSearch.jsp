<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@taglib uri = "http://www.springframework.org/tags/form" prefix = "sf"%>
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/${appMode}/tilescommon/css/fioristyle.css?appVer=${appVer}"/>


<style>
.input-group .form-control {
	z-index: 0;
}
.advancehide{display:none;}
.pagination {
	position: inherit !important;
}
.datetimepicker{top:60px !important;}
@media only screen and (max-width: 768px) {
.datetimepicker {
    top: 218px !important;
}
}
</style>
	<div class="container" style="margin-top: 75px; margin-bottom: 30px;">
		<div class="tab-content">
			<!-- Master tab start-->

			<div class="tab-pane active in" id="tenAucData">
				<div class="card">
					<div class="posrelative" id="example">
						
							<div class="demo-section k-content">
								<div id="tabstrip" class="Firsttab">
									<ul>
										<!-- tabs -->
										<li class="k-state-active serchcteriali">Advance Search
											>> Specify Search Criteria</li>
										<li class="listoftenderli" style="display: none;">List Of
											Tenders/Auction</li>
									</ul>

									<!--fields of field group 1  -->

									<div class="serchcteria">
										<!-- <div class="form-group" style="margin-top: -10px;">
											<div class="col-sm-3">
												<div class="styled-input">
													  <select id="tender"  required></select>
													<label>Tender</label> <span></span>
												</div>
											</div>
											<div class="col-sm-3">
												<div class="styled-input">
													 <select id="auction" required></select>
													<label>Auction</label> <span></span>
												</div>
											</div>
											<div class="col-sm-3">
												<div class="styled-input">
													<select id="auction1"  required></select>
													<label>Auction</label> <span></span>
												</div>
											</div>
											<div class="col-sm-3">
												<div class="styled-input">
													 <select id="auction2"  required></select>
													<label>Auction</label> <span></span>
												</div>
											</div>
										</div> -->
										<sf:form id="advanceSearchFormId" action="" method="POST" autocomplete="off" modelAttribute="searchParameter">
										<div class="form-group" style="margin-top: 20px;">
											<div class="col-sm-12">
												<label>Search by Tender Data</label>
												<hr>
											</div>
										</div>
										<div class="form-group" style="margin-bottom: 0px;" >
											<div class="col-sm-3">
												<div class="styled-input">
													<input type="text" id="tenderNo" class="reset" name="tahdr.tahdrCode" >
													<label>Tender Code</label> <span></span>
												</div>
											</div>
											<div class="col-sm-3">
											 <div class="styled-input">
												    <select id="tahdrTypeCode"  name="tahdr.tahdrTypeCode" class="resetDropDown" > 
												      
												
													 </select>
													<label>Tender Type</label> <span></span>
												</div> 
											</div>
											<div class="col-sm-3">
												 <div class="styled-input">
													 <select id="bidTypeCode" name="tahdr.bidTypeCode" class="resetDropDown" > 
											
													 </select>
													 <label>Tender Bid Type</label> <span></span>
												</div> 
											</div>
											<div class="col-sm-3">
												<div class="styled-input">
													  <select name="tahdrMaterial.materialTypeCode"  > 
													    <option value="">Select</option>
													    <option value="single">Single</option>
													    <option value="BOM">BOM</option>
													   </select> 
													<label>Material Type</label> <span></span>
												</div>
											</div>
										</div>
										<div class="form-group" style="margin-top: 20px;">
											<div class="col-sm-12">
												<label>Search by Tender Dates</label>
												<hr>
											</div>
										</div>
										<div class="form-group">
											<div class="col-sm-6">
											<label class="col-sm-12">Technical Bid Date</label>
											<div class="clearfix"></div>
											<div class="col-sm-6 nomopa">
												<div class="form-group">
													<div class="input-group date form_datetime">
													  <input type="text" class="form-control reset" placeholder="From" name="tahdrDetail.technicalBidFromDate"> 
														<div class="input-group-addon">
															<span class="glyphicon glyphicon-th"></span>
														</div>
													</div>
												</div>
												</div>
												<div class="col-sm-6 nomopa">
												<div class="form-group">
													<div class="input-group date form_datetime">
														 <input type="text" class="form-control reset" placeholder="To" name="tahdrDetail.technicalBidToDate"> 
														<div class="input-group-addon">
															<span class="glyphicon glyphicon-th"></span>
														</div>
													</div>
												</div>
												</div>
											 </div>
											<!-- <label class="col-sm-4">Commercial Bid Date
												<div class="form-group">
													<div class="input-group date" data-provide="datepicker">
														<input type="text" class="form-control reset" placeholder="From" name="tahdrDetail.commercialBidFromDate">
														<div class="input-group-addon">
															<span class="glyphicon glyphicon-th"></span>
														</div>
													</div>
												</div>
												<div class="form-group">
													<div class="input-group date" data-provide="datepicker">
														 <input type="text" class="form-control reset" placeholder="To" name="tahdrDetail.commercialBidToDate"> 
														<div class="input-group-addon">
															<span class="glyphicon glyphicon-th"></span>
														</div>
													</div>
												</div>
											</label> -->
											 <label class="col-sm-3">Price Bid Opening Date
												<div class="form-group">
													<div class="input-group date form_datetime">
														<input type="text" class="form-control reset"  name="tahdrDetail.priceBidOpenningDate"> 
														<div class="input-group-addon">
															<span class="glyphicon glyphicon-th"></span>
														</div>
													</div>
												</div>
												<!-- <div class="form-group">
													<div class="input-group date" data-provide="datepicker">
														  <input type="text" class="form-control reset" placeholder="To" name="tahdrDetail.priceBidToDate">
														<div class="input-group-addon">
															<span class="glyphicon glyphicon-th"></span>
														</div>
													</div>
												</div> -->
											</label> 
											<label class="col-sm-3">Techno Commercial Bid Opening Date
												<div class="form-group">
													<div class="input-group date form_datetime">
														  <input type="text" class="form-control reset"  name="tahdrDetail.techBidOpenningDate">
														<div class="input-group-addon">
															<span class="glyphicon glyphicon-th"></span>
														</div>
													</div>
												</div>
											</label> 
											
											<div class="col-sm-4" style="margin-top: 15px;">
												<button class="btn btn-info serchlistoftender indirectFormSubmit">
													<span class="fa fa-search"></span>Search
												</button>
												<button class="btn btn-info"  onclick="resetField()">
													<span class="fa fa-refresh"></span>Reset
												</button>
											</div>
										</div>
									</sf:form>
									</div>

									<!--fields of field group 1  -->
									<div class="listoftender" style="display: none;">
										<div class="col-sm-12 nomopa" style="margin-top: 20px;">
											<table
												class="tahdrTable table tableResponsive table-striped table-bordered"
												width="100%" id="tahdrTableId">
												<thead>
													<tr>
														<th>Tender No</th>
														<th>Tender Type</th>
														<th>Tender Bid Type</th>
														<th>Techno Commercial Bid OpeningDate</th>
														<!-- <th>CommercialBid OpeningDate</th> -->
														<!-- <th>Submission Due</th> -->
														<th>Tender Fee(Rs)</th>
														<!-- <th>Download</th> -->
													</tr>
												</thead>
												<tbody >

												</tbody>
											</table>
										</div>
										<div class="col-sm-4" style="margin-bottom: 5px;">
											<button class="btn btn-info ChangeCriteria">Change
												Criteria</button>
										</div>
									</div>
									</div>
									</div>
						
					</div>

				</div>
				<!-- Master tab end-->

			</div>
		</div>
		<!-- right-side end-->
	</div>
 
<script src="<%=request.getContextPath()%>/resources/${appMode}/commons/js/utility.js?appVer=${appVer}"></script> 
<script	src="<%=request.getContextPath()%>/resources/${appMode}/commons/js/springform.js?appVer=${appVer}"></script>
<script	src="<%=request.getContextPath()%>/resources/${appMode}/commons/js/advanceSearch.js?appVer=${appVer}"></script>
<script src="<%=request.getContextPath()%>/resources/${appMode}/commons/js/formFields.js?appVer=${appVer}"></script>
<script src="<%=request.getContextPath()%>/resources/${appMode}/commons/js/commonValidation.js?appVer=${appVer}"></script>
<script	src="<%=request.getContextPath()%>/resources/${appMode}/tilescommon/js/bootstrap-datetimepicker.js?appVer=${appVer}"></script>
<script type="text/javascript">
	var date = new Date();
	date.setDate(date.getDate());

	$('.form_datetime').datetimepicker({
		weekStart : 1,
		autoclose : true,
		startView : 2,
		forceParse : 0,
		format : 'dd-mm-yyyy 00:00',
		showMeridian : 0,
		orientation : 'auto',
		pickerPosition : "top-left",
		/* startDate : date, */
		pick12HourFormat : false,
		widgetPositioning : {
			horizontal : 'auto',
			vertical : 'bottom'
		}
		
	});
</script>
