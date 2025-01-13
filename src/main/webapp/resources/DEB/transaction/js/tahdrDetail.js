var tahdrDetailArray= new Array();
var otherCommencementPeriod='';
$(document).ready(function(){
	
	var emdFeeWithEC='';
	$("#tahdrDetailTab").on('click',function(event){
		event.preventDefault();
		$("#leftPane").removeClass('readonly');
		cacheLi();
		setCurrentTab(this);
		getTahdrDetails();
	});
	
	$("#commencementPeriodMonth").on('change',function(event){
		event.preventDefault();
		setCommencementPeriodNote();
	});
	
	/*$(".techBid").on('click',function(event){
		event.preventDefault();
		
	});*/

	
	$(".saveTahdrDetailsBtn").on("click", function(event){
		debugger;
		event.preventDefault();
		
		var tahdrStatus=$('#tahdrForm #tahdrStatus').val();
		if(tahdrStatus!="" && tahdrStatus!=undefined){
			$("#TAHDRDetail #tahdrStatus").val(tahdrStatus);
			submitIt("TAHDRDetail","saveTahdrDetails","saveTahdrDetailResp");
		}
		
	});
	
	$('#cancelTahdrDetail').click(function(event) {
		event.preventDefault();
		var activeTahdrDetailId=$('.leftPaneData').find('li.active').attr('id');
		if(activeTahdrDetailId!=undefined){
			loadTahdrDetailToRightPane(tahdrDetailArray["tahdrDetail"+activeTahdrDetailId]);
		}else{
			$('#TAHDRDetail')[0].reset();
		}
    });
	
/*	$("#isPBDSetLater").on("change",function(event){
		debugger;
		event.preventDefault();
		if($(this).prop('checked')){
			$('.setPBDLater').attr('disabled','disabled');
			$('.setPBDLater').hide();
			$('#isSetC1Later').prop('checked', true);
			$('#isSetC1Later').trigger('change');
		}else{
			$('.setPBDLater').show();
			$('.setPBDLater').removeAttr('disabled');
		}
	});*/
	
	/*$("#isAuctionSetLater").on("change",function(event){
		debugger;
		event.preventDefault();
		if($(this).prop('checked')){
			$('.setAuctionLater').attr('disabled','disabled');
			$('.setAuctionLater').hide();
		}else{
			$('.setAuctionLater').show();
			$('.setAuctionLater').removeAttr('disabled');
		}
	});*/
	
	/*$("#isWinnerSetLater").on("change",function(event){
		debugger;
		event.preventDefault();
		if($(this).prop('checked')){
			$('.winnerLater').attr('disabled','disabled');
			$('.winnerLater').hide();
		}else{
			$('.winnerLater').show();
			$('.winnerLater').removeAttr('disabled');
		}
	});
*/	
	$("#isAnnexureC1").on("change",function(event){
		event.preventDefault();
		if(!$(this).prop('checked')){
			$('#isSetC1Later').prop('checked',false);
			$('.isC1Applicable').attr('disabled','disabled');
			$('.isC1Applicable').hide();
		}else{
			$('.isC1Applicable').removeAttr('disabled');
			$('.isC1Applicable').show();
		}
		//$('#isSetC1Later').trigger('change');
	});
	
/*	$("#isSetC1Later").on("change",function(event){
		debugger;
		event.preventDefault();
		if($(this).prop('checked')){
			$('.setC1Later').attr('disabled','disabled');
			$('.setC1Later').hide();
		}else{
			$('.setC1Later').show();
			$('.setC1Later').removeAttr('disabled');
		}
	});*/
	
	
	
});

function setCommencementPeriodNote(){
	var optionName=$('#TAHDRDetail #commencementPeriodMonth').find('option:selected').val();
	if(optionName=='OTHERS'){
		$('#otherCommsPeriodDivId').show();
		$("#otherCommsPeriodDivId").css("display","block");
		$('#otherCommencementPeriod').addClass('requiredField');
		$('#otherCommencementPeriod').val(otherCommencementPeriod);
	}else{
		$('#otherCommsPeriodDivId').hide();
		$("#otherCommsPeriodDivId").css("display","none");
		$('#otherCommencementPeriod').removeClass('requiredField');
		$('#otherCommencementPeriod').removeClass('errorinput');
		$('#otherCommencementPeriod').val('');
	}
}

function getTahdrDetails(){
	if(getChangedFlag()){
		$('.pagination').children().remove();
		var tahdrId=$(".tahdrId").val();
		var tahdrStaus=$(".tahdrStatus").val();
		$("#TAHDRDetail .tahdrId").val(tahdrId);
		$("#TAHDRDetail .tahdrStatus").val(tahdrStaus);
		$("#MonthDate").css("display","none");
		$("#otherCommsPeriodDivId").css("display","none");
		otherCommencementPeriod='';
		$("#commencementPeriodMonth").removeClass('dropDown');
		$("#commencementPeriodMonth").removeClass('dropDown requiredField');
		$('.isCreateVersion').val('N');
		var detailList=fetchList("getTAHDRDetail",tahdrId);
		var commencementPeriodValue=detailList==null?'':detailList.objectMap==null?'':detailList.objectMap.commencementPeriodValue==null?'':detailList.objectMap.commencementPeriodValue;
		loadReferenceList("commencementPeriodMonth",commencementPeriodValue);
		var designation= detailList==null?'':detailList.objectMap==null?'':detailList.objectMap.designations==null?'':detailList.objectMap.designations;
		populateDesignation(designation);
		if(detailList.length>0){
			loadTabsForDetail(detailList.objectMap.tahdrDetail);
		}
		else{
			$(".tahdrDetailId").val("");
			loadTabsForDetail(detailList.objectMap.tahdrDetail);
		}
	
	 $(".tabs-left li a label").text(function(index, currentText) {
		    return currentText.substr(0, 20);
		});	
	    
		/*if (status == 'Drafted'){*/
			if (tenderStatus == 'Drafted'){
			/*$("#TAHDRDetail .createVersion").css("display","none");*/
				
			/*$('#tenderDetailForm').removeClass('readonly');*/
			$('.tenderDetailFormDiv').removeClass('readonly');
			$('#textAreaDiv textarea').removeAttr('readonly');
			
			$('#tenderDatesForm').removeClass('readonly');
			$('#tenderDatesForm').find('input').removeAttr('tabindex','-1');
			$('#TAHDRDetail .saveTahdrDetailsBtn').show();
			$('#TAHDRDetail .cancel').show();
			/*$('.standardCustomDocuments').removeClass('readonly');
			$('.standardCustomDocuments2').removeAttr('disabled');
			$('#saveStdCstDocForm .save').show();
			$('#saveStdCstDocForm .cancel').show();*/
			/*$('#tenderMaterialForm').removeClass('readonly');
			$('#saveTahdrMaterialForm .saveTahdrMaterial').show();
			$('#saveTahdrMaterialForm .cancelTahdrMaterial').show();
			$('#tenderMaterialButtonsId').removeClass('readonly');*/
			$('#tenderRequiredDocsForm').removeClass('readonly');
			$('#sectionButtonsId').removeClass('readonly');
			$('#saveSectionDocumentForm .save').show();
			$('#saveSectionDocumentForm .cancel').show();
		}
		else if(tenderStatus == 'Published'){
			/*$("#TAHDRDetail .createVersion").css("display","block");*/
			
			/*$('#tenderDetailForm').addClass('readonly');*/
			$('.tenderDetailFormDiv').addClass('readonly');
			$('#textAreaDiv textarea').attr('readonly','readonly');
			
			$('#tenderDatesForm').addClass('readonly');
			$('#tenderDatesForm').find('input').attr('tabindex','-1');
			$('#TAHDRDetail .saveTahdrDetailsBtn').hide();
			$('#TAHDRDetail .cancel').hide();
			/*$('.standardCustomDocuments').addClass('readonly');
			$('.standardCustomDocuments2').attr('disabled','disabled');
			$('#saveStdCstDocForm .save').hide();
			$('#saveStdCstDocForm .cancel').hide();*/
			/*$('#tenderMaterialForm').addClass('readonly');
			$('#tenderMaterialButtonsId').addClass('readonly');
			$('#saveTahdrMaterialForm .saveTahdrMaterial').hide();
			$('#saveTahdrMaterialForm .cancelTahdrMaterial').hide();*/
			$('#tenderRequiredDocsForm').addClass('readonly');
			$('#sectionButtonsId').addClass('readonly');
			$('#saveSectionDocumentForm .save').hide();
			$('#saveSectionDocumentForm .cancel').hide();
		}
		else if(tenderStatus=='Approved'){
			/*$("#TAHDRDetail .createVersion").css("display","none");*/
			
			/*$('#tenderDetailForm').addClass('readonly');*/
			$('.tenderDetailFormDiv').addClass('readonly');
			$('#textAreaDiv textarea').attr('readonly','readonly');
			
			$('#tenderDatesForm').addClass('readonly');
			$('#tenderDatesForm').find('input').attr('tabindex','-1');
			$('#TAHDRDetail .saveTahdrDetailsBtn').hide();
			$('#TAHDRDetail .cancel').hide();
			$('#TAHDRDates .save').hide();
			$('#TAHDRDates .cancel').hide();
			/*$('.standardCustomDocuments').addClass('readonly');
			$('.standardCustomDocuments2').attr('disabled','disabled');
			$('#saveStdCstDocForm .save').hide();
			$('#saveStdCstDocForm .cancel').hide();*/
			/*$('#tenderMaterialForm').addClass('readonly');
			$('#tenderMaterialButtonsId').addClass('readonly');
			$('#saveTahdrMaterialForm .saveTahdrMaterial').hide();
			$('#saveTahdrMaterialForm .cancelTahdrMaterial').hide();*/
			$('#tenderRequiredDocsForm').addClass('readonly');
			$('#sectionButtonsId').addClass('readonly');
			$('#saveSectionDocumentForm .save').hide();
			$('#saveSectionDocumentForm .cancel').hide();
		}
		else{
				/*$("#TAHDRDetail .createVersion").css("display","none");*/
			
			/*$('#tenderDetailForm').addClass('readonly');*/
			$('.tenderDetailFormDiv').addClass('readonly');
			$('#textAreaDiv textarea').attr('readonly','readonly');
			
				$('#tenderDatesForm').addClass('readonly');	
				$('#tenderDatesForm').find('input').attr('tabindex','-1');
				$('#TAHDRDetail .saveTahdrDetailsBtn').hide();
				$('#TAHDRDetail .cancel').hide();
				/*$('.standardCustomDocuments').addClass('readonly');
				$('.standardCustomDocuments2').attr('disabled','disabled');
				$('#saveStdCstDocForm .save').hide();
				$('#saveStdCstDocForm .cancel').hide();*/
				/*$('#tenderMaterialForm').addClass('readonly');
				$('#tenderMaterialButtonsId').addClass('readonly');
				$('#saveTahdrMaterialForm .saveTahdrMaterial').hide();
				$('#saveTahdrMaterialForm .cancelTahdrMaterial').hide();*/
				$('#tenderRequiredDocsForm').addClass('readonly');
				$('#sectionButtonsId').addClass('readonly');
				$('#saveSectionDocumentForm .save').hide();
				$('#saveSectionDocumentForm .cancel').hide();
		}
		setChangedFlag(false);
	}else{
		getCacheLi();
	}
	preparationScopeCheck();
	setLeftPaneHeader("Details", null);

}

function loadTabsForDetail(tahdrDetailList){
	if(!$.isEmptyObject(tahdrDetailList)){
		loadTahdrDetailToLeftPane(tahdrDetailList);
		$('#tenderDocTab').removeClass('readonly');
		$('#impDatesTab').removeClass('readonly');
		$('#getTahdrMaterialList').removeClass('readonly');
		$('#sectionDocTab').removeClass('readonly');
		 $('#confirmation').removeClass('readonly');
		}else{
		$('#impDatesTab').addClass('readonly');
		$('#getTahdrMaterialList').addClass('readonly');
		$('#tenderDocTab').addClass('readonly');
		$('#sectionDocTab').addClass('readonly');
		$('#confirmation').addClass('readonly');
		$(".leftPaneData").html("");
		$('#TAHDRDetail')[0].reset();
		$('#isAnnexureC1').removeAttr('checked');
		$('#isAnnexureC1').prop('checked',false);
		if($('#isAnnexureC1').val()=='Y'){
		$('#isAnnexureC1').trigger('change');
		}
		else if($('#isAnnexureC1').val()=='N'){
			$('#isAnnexureC1').trigger('change');
		}
		};
}

function loadTahdrDetailToLeftPane(tahdrDetailList){
	$("#leftPane").html("");
	var leftPanelHtml='';
	var i=0;
	var active="";
	var firstRow=null;
	tahdrDetailArray=[];
	if(!isEmpty(tahdrDetailList)){
	$.each(Object.values(tahdrDetailList),function(key,tahdrDetail){
		
		tahdrDetailArray["tahdrDetail"+tahdrDetail.tahdrDetailId]=tahdrDetail;
		if(tahdrDetail.isActive=='Y'){
			firstRow = tahdrDetail;
			active="active";
		}
		leftPanelHtml= leftPanelHtml +appendTahdrDetailLi(tahdrDetail,active);
		active="";
		i++;
	});
	}
	$("#leftPane").append(leftPanelHtml);
	loadTahdrDetailToRightPane(firstRow);
	
	$("#leftPane").on('click','.tahdrDetail',function(){
		var id=$(this).attr('id');
		loadTahdrDetailToRightPane(tahdrDetailArray["tahdrDetail"+id]);
	});
	
	$('#leftPane').paginathing({perPage: 10});
}

function editVersion(event){
	event.preventDefault();
	$("#TAHDRDetail").removeClass("readonly");
}

function loadTahdrDetailToRightPane(tahdrDetail){
	debugger;
	if(!$.isEmptyObject(tahdrDetail)){
		var activeStatus=tahdrDetail.isActive==null?'':tahdrDetail.isActive;
		if(activeStatus=='Y' && tenderStatus=='Drafted'){
			$('#TAHDRDetail').find('input:text,input:checkbox,select,textarea').removeClass('readonly');
			$('#TAHDRDates').find('input:text').removeClass('readonly');
			$('#tenderDatesForm').find('input').removeAttr('tabindex','-1');
			$('#TAHDRDetail .saveTahdrDetailsBtn').show();
			$('#TAHDRDetail .cancel').show();
			$('#TAHDRDates .saveTahdrDatesBtn').show();
			$('#TAHDRDates .cancel').show();
			
			$('#tenderRequiredDocsForm').removeClass('readonly');
			$('#sectionButtonsId').removeClass('readonly');
			$('#saveSectionDocumentForm .save').show();
			$('#saveSectionDocumentForm .cancel').show();
		}
		else if(activeStatus=='N'){
			$('#TAHDRDetail').find('input:text,input:checkbox,select,textarea').addClass('readonly');
			$('#TAHDRDates').find('input:text').addClass('readonly');
			$('#tenderDatesForm').find('input').attr('tabindex','-1');
			$('#TAHDRDetail .saveTahdrDetailsBtn').hide();
			$('#TAHDRDetail .cancel').hide();
			$('#TAHDRDates .saveTahdrDatesBtn').hide();
			$('#TAHDRDates .cancel').hide();
			
			$('#tenderRequiredDocsForm').addClass('readonly');
			$('#sectionButtonsId').addClass('readonly');
			$('#saveSectionDocumentForm .save').hide();
			$('#saveSectionDocumentForm .cancel').hide();
		}
		$(".tahdrDetailId").val(getValue(tahdrDetail.tahdrDetailId));
		$("#TAHDRDetail #estimatedCost").val(getValue(tahdrDetail.estimatedCost));
		
		if($("#tahdrType").val()=='WT'){
		calculateEMDFeeWorks();
		}else{
			calculateEMDFee();
		}
		
		$('#TAHDRDetail #tahdrDetailId').val(getValue(tahdrDetail.tahdrDetailId));
		calculateGST();
		$("#TAHDRDetail #deliveryDuration").val(getValue(tahdrDetail.deliveryDuration));
		$("#TAHDRDetail #deliveryDuration").val(getValue(tahdrDetail.deliveryDuration));
		$("#TAHDRDetail #contactEmailId").val(getValue(tahdrDetail.contactEmailId));
		$("#TAHDRDetail #contactPersonName").val(getValue(tahdrDetail.contactPersonName));
		$("#TAHDRDetail #contactPersonNo").val(getValue(tahdrDetail.contactPersonNo));
		$("#TAHDRDetail #isDeviation").val(getValue(tahdrDetail.isDeviation));
		$("#TAHDRDetail #commencementPeriod").val(getValue(tahdrDetail.commencementPeriod));
		onChangeMonthDate();
		$("#TAHDRDetail #commencementPeriodMonth").val(getValue(tahdrDetail.commencementPeriodCode));
		$("#TAHDRDetail #otherCommencementPeriod").val(tahdrDetail.otherCommencementPeriod);
		otherCommencementPeriod=tahdrDetail.otherCommencementPeriod;
		setCommencementPeriodNote();
		
		if(tahdrDetail.designation!=null){
			$("#TAHDRDetail #designation").val(getValue(tahdrDetail.designation.designationId))
		}
		$("#TAHDRDetail #pricingProcCode").val(getValue(tahdrDetail.pricingProcCode));
		$("#TAHDRDetail #isAnnexureC1").val(getValue(tahdrDetail.isAnnexureC1));
		$("#TAHDRDetail #isBiennialContractRate").val(getValue(tahdrDetail.isBiennialContractRate));
		$("#TAHDRDetail #minQuantity").val(getValue(tahdrDetail.minQuantity));
		$("#TAHDRDetail #preQualReq").val(getValue(tahdrDetail.preQualReq));
		$("#TAHDRDetail #description").val(getValue(tahdrDetail.description));
		$("#TAHDRDetail #emdFee").val(getValue(tahdrDetail.emdFee));
		$("#TAHDRDetail #preBidAddr").val(getValue(tahdrDetail.preBidAddr));
		$("#TAHDRDetail #bidOpeningAddr").val(getValue(tahdrDetail.bidOpeningAddr));
		if($("#tahdrType").val()=='WT')
		{
			/*$('#TAHDRDetail #isAnnexureC1').removeAttr('checked');
			$('#TAHDRDetail #c1OpenningDate').removeAttr('disabled','disabled');
			$('#TAHDRDetail #c1ToDate').removeAttr('disabled','disabled');
			$('#TAHDRDetail #c1FromDate').removeAttr('disabled','disabled');*/
		}else{
			$("input[name='isAnnexureC1']").prop('checked', getCheckBoxVal(tahdrDetail.isAnnexureC1))
			/*$('#TAHDRDetail #isAnnexureC1').trigger('change');*/
		}
		
		$("input[name='isDeviation']").prop('checked', getCheckBoxVal(tahdrDetail.isDeviation));
		$("input[name='isAuctionDateSetLater']").prop('checked', getCheckBoxVal(tahdrDetail.isAuctionDateSetLater));
		$("input[name='isWinnerSelectionDateSetLater']").prop('checked', getCheckBoxVal(tahdrDetail.isWinnerSelectionDateSetLater));
		
		if($("input[name='isSetC1Later']").prop('checked', getCheckBoxVal(tahdrDetail.isSetC1Later))){
		/*$('#TAHDRDetail #isSetC1Later').trigger('change');*/
		}
		if($("input[name='isPBDSetLater']").prop('checked', getCheckBoxVal(tahdrDetail.isPBDSetLater))){
		/*$('#TAHDRDetail #isPBDSetLater').trigger('change');*/
		}
		$("input[name='isBiennialContractRate']").prop('checked', getCheckBoxVal(tahdrDetail.isBiennialContractRate));
		$("input[name='isActive']").prop('checked', getCheckBoxVal(tahdrDetail.isActive));
		
		
		$("input[name='isTechnicalBid']").prop('checked', getCheckBoxVal(tahdrDetail.isTechnicalBid));
		
		$("input[name='isGTP']").prop('checked', getCheckBoxVal(tahdrDetail.isGTP));
		$("input[name='isTechnicalDocs']").prop('checked', getCheckBoxVal(tahdrDetail.isTechnicalDocs));
		
		$("input[name='isCommercialDocs']").prop('checked', getCheckBoxVal(tahdrDetail.isCommercialDocs));
		$("input[name='isPriceDocs']").prop('checked', getCheckBoxVal(tahdrDetail.isPriceDocs));
		
		$("input[name='isTenderDocs']").prop('checked', getCheckBoxVal(tahdrDetail.isTenderDocs));
		$("input[name='isReqDocs']").prop('checked', getCheckBoxVal(tahdrDetail.isReqDocs));
		
		$("input[name='isPreliminaryScrutiny']").prop('checked', getCheckBoxVal(tahdrDetail.isPreliminaryScrutiny));
		$("input[name='isFinalScrutiny']").prop('checked', getCheckBoxVal(tahdrDetail.isFinalScrutiny));
		
		$("input[name='isTechnicalBid']").val(tahdrDetail.isTechnicalBid);
		$("input[name='isGTP']").val(tahdrDetail.isGTP);
		$("input[name='isTechnicalDocs']").val(tahdrDetail.isTechnicalDocs);
		
		$("input[name='isCommercialDocs']").val(tahdrDetail.isCommercialDocs);
		$("input[name='isPriceDocs']").val(tahdrDetail.isPriceDocs);
		
		$("input[name='isTenderDocs']").val(tahdrDetail.isTenderDocs);
		$("input[name='isReqDocs']").val(tahdrDetail.isReqDocs);
		
		$("input[name='isPreliminaryScrutiny']").val(tahdrDetail.isPreliminaryScrutiny);
		$("input[name='isFinalScrutiny']").val(tahdrDetail.isFinalScrutiny);
		
		
		/*$("#isSetC1Later").trigger('change');
		$("#isPBDSetLater").trigger('change');*/

		$("#TAHDRDetail #tahdrValidity").val(tahdrDetail.tahdrValidity);
		$("#TAHDRDetail #tahdrValidity").attr("title", getValue(tahdrDetail.tahdrValidity));
		$("#lastSubmissionDate").val(formatDateTime(tahdrDetail.lastSubmissionDate));
		$("#lastC1SubmissionDate").val(formatDateTime(tahdrDetail.lastC1SubmissionDate));
		/*$("#isSetC1Later").val(getCheckBoxVal(tahdrDetail.isSetC1Later));*/
		$("#technicalBidFromDate").val(formatDateTime(tahdrDetail.technicalBidFromDate));
		$("#technicalBidToDate").val(formatDateTime(tahdrDetail.technicalBidToDate));
		$("#techBidOpenningDate").val(formatDateTime(tahdrDetail.techBidOpenningDate));
		$("#commercialBidFromDate").val(formatDateTime(tahdrDetail.commercialBidFromDate));
		$("#commercialBidToDate").val(formatDateTime(tahdrDetail.commercialBidToDate));
		$("#commercialBidOpenningDate").val(formatDateTime(tahdrDetail.commercialBidOpenningDate));
		$("#purchaseFromDate").val(formatDateTime(tahdrDetail.purchaseFromDate));
		$("#purchaseToDate").val(formatDateTime(tahdrDetail.purchaseToDate));
		$("#preBidDate").val(formatDateTime(tahdrDetail.preBidDate));
		$("#priceBidFromDate").val(formatDateTime(tahdrDetail.priceBidFromDate));
		$("#priceBidToDate").val(formatDateTime(tahdrDetail.priceBidToDate));
		$("#priceBidOpenningDate").val(formatDateTime(tahdrDetail.priceBidOpenningDate));
		$("#c1FromDate").val(formatDateTime(tahdrDetail.c1FromDate));
		$("#c1ToDate").val(formatDateTime(tahdrDetail.c1ToDate));
		$("#c1OpenningDate").val(formatDateTime(tahdrDetail.c1OpenningDate));
		$("#auctionStartToDates").val(formatDateTime(tahdrDetail.auctionFromDate));
		$("#auctionEndToDates").val(formatDateTime(tahdrDetail.auctionToDate));
		$("#winnerSelectionDate").val(formatDateTime(tahdrDetail.winnerSelectionDate));
		$("#version").val(tahdrDetail.version);
		
		if(tahdrDetail.isActive=='Y' && (tenderStatus=='Published' || tenderStatus=='Approved')){
			/*$("#TAHDRDetail .createVersion").css("display","block");*/
			$("#TAHDRDates .editTahdrDate").css("display","inline-block");
		}
		else{
			/*$("#TAHDRDetail .createVersion").css("display","none");*/
			$("#TAHDRDates .editTahdrDate").css("display","none");
		}
		
		if(tahdrDetail.tahdr.isAuction=='N'){
			$("#minBidDifferenceDiv").hide();
			$("#minBidDifference").attr('disabled','disabled');
		}else{
			$("#minBidDifferenceDiv").show();
			$("#minBidDifference").removeAttr('disabled');
			$("#minBidDifference").val(tahdrDetail.minBidDifference);
		}
	
		if(activeStatus=='Y'){
		if (tenderStatus == 'Drafted'){
			/*$('#tenderDetailForm').removeClass('readonly');*/
			$('.tenderDetailFormDiv').removeClass('readonly');
			$('#textAreaDiv textarea').removeAttr('readonly');
			
			$('#tenderDatesForm').removeClass('readonly');
			$('#tenderDatesForm').find('input').removeAttr('tabindex','-1');
			$('#TAHDRDetail .save').show();
			$('#TAHDRDetail .cancel').show();
			$('#TAHDRDates .save').show();
			$('#TAHDRDates .cancel').show();
			
			/*$('.standardCustomDocuments').removeClass('readonly');
			$('.standardCustomDocuments2').removeAttr('disabled','disabled');
			$('#customDocsDiv').removeClass('readonly');
			$('.addMoreCM').removeClass('readonly');
			$('.removeMoreCM').removeClass('readonly');
			$('#saveStdCstDocForm .save').show();
			$('#saveStdCstDocForm .cancel').show();*/
		}
		else if(tenderStatus=='Approved'){
			/*$('#tenderDetailForm').addClass('readonly');*/
			$('.tenderDetailFormDiv').addClass('readonly');
			$('#textAreaDiv textarea').attr('readonly','readonly');
			
			$('#tenderDatesForm').addClass('readonly');
			$('#tenderDatesForm').find('input').attr('tabindex','-1');
			$('#TAHDRDetail .save').hide();
			$('#TAHDRDetail .cancel').hide();
			$('#TAHDRDates .save').hide();
			$('#TAHDRDates .cancel').hide();
			
			/*$('.standardCustomDocuments').addClass('readonly');
			$('.standardCustomDocuments2').attr('disabled','disabled');
			$('#customDocsDiv').addClass('readonly');
			$('.addMoreCM').addClass('readonly');
			$('.removeMoreCM').addClass('readonly');
			$('#saveStdCstDocForm .save').hide();
			$('#saveStdCstDocForm .cancel').hide();*/
		}
		else{
			/*$('#tenderDetailForm').addClass('readonly');*/
			$('.tenderDetailFormDiv').removeClass('readonly');
			$('#textAreaDiv textarea').removeAttr('readonly');
			
			$('#tenderDatesForm').addClass('readonly');
			$('#tenderDatesForm').find('input').attr('tabindex','-1');
			$('#TAHDRDetail .save').hide();
			$('#TAHDRDetail .cancel').hide();
			$('#TAHDRDates .save').hide();
			$('#TAHDRDates .cancel').hide();
			
			/*$('.standardCustomDocuments').addClass('readonly');
			$('.standardCustomDocuments2').attr('disabled','disabled');
			$('#customDocsDiv').addClass('readonly');
			$('.addMoreCM').addClass('readonly');
			$('.removeMoreCM').addClass('readonly');
			$('#saveStdCstDocForm .save').hide();
			$('#saveStdCstDocForm .cancel').hide();*/
		}
		}
		else if(activeStatus=='N'){
			$('#TAHDRDetail').find('input:text,input:checkbox,select,textarea').addClass('readonly');
			$('#TAHDRDates').find('input:text').addClass('readonly');
			$('#tenderDatesForm').find('input').attr('tabindex','-1');
			$('#TAHDRDetail .saveTahdrDetailsBtn').hide();
			$('#TAHDRDetail .cancel').hide();
			$('#TAHDRDates .saveTahdrDatesBtn').hide();
			$('#TAHDRDates .cancel').hide();
			
			/*$('.standardCustomDocuments').addClass('readonly');
			$('.standardCustomDocuments2').attr('disabled','disabled');
			$('#customDocsDiv').addClass('readonly');
			$('.addMoreCM').addClass('readonly');
			$('.removeMoreCM').addClass('readonly');
			$('#saveStdCstDocForm .save').hide();
			$('#saveStdCstDocForm .cancel').hide();*/
			
			/*$('#tenderMaterialForm').addClass('readonly');
			$('#tenderMaterialButtonsId').addClass('readonly');
			$('#saveTahdrMaterialForm .saveTahdrMaterial').hide();
			$('#saveTahdrMaterialForm .cancelTahdrMaterial').hide();*/
			
			$('#tenderRequiredDocsForm').addClass('readonly');
			$('#sectionButtonsId').addClass('readonly');
			$('#saveSectionDocumentForm .save').hide();
			$('#saveSectionDocumentForm .cancel').hide();
		}
	}
	else{
		$('#TAHDRDetail')[0].reset();
		var purchaseFromDate = $('#purchaseToDate').val()+' 00:00';
		setDateTimePickerStartEndWithId("preBidDates",purchaseFromDate,techincalBidOpenDate);
	}
	var documentType=$(".documentType").val();
	setHeaderValues("Code.: "+tahdrCodes, "Title : "+title, "Department : "+department, "Status : "+tenderStatus);
	
	setChildLoadFlag(true);
}

function saveTahdrDetailResp(data){
	debugger;
	if(!data.response.hasError){
		var leftPanelHtml='';
		var currentTahdrDetailId=$('ul.leftPaneData').find('li.active').attr('id');
		var tahdrDetailId=data.tahdrDetailId;
		var tahdrId= data.tahdr==null?'':data.tahdr.tahdrId==null?'':data.tahdr.tahdrId;
		status=tahdrStatusList[data.tahdr.tahdrStatusCode];
		tenderStatus=tahdrStatusList[data.tahdr.tahdrStatusCode];
		var tahdrDetailActiveStatus=data.isActive==null?'':data.isActive;
		if(currentTahdrDetailId==tahdrDetailId)
		{
			$('#'+currentTahdrDetailId).remove();
		}else{
			$('#'+currentTahdrDetailId).removeClass('active');
		}
		$(".tahdrDetailId").val(tahdrDetailId);
		$("#oldTahdrDetailId").val('');
		$(".tahdrId").val(tahdrId);
		leftPanelHtml=appendTahdrDetailLi(data,"active");
		tahdrDetailArray["tahdrDetail"+tahdrDetailId]=data;
		$(".leftPaneData").prepend(leftPanelHtml);
		if(tahdrId!=null){
			var detailList=fetchList("getTAHDRDetail",tahdrId);
			loadTabsForDetail(detailList.objectMap.tahdrDetail);
		}
		
		if(data.response.hasError){
			/*Alert.warn(data.response.message);*/
			swal(data.response.message);
		}else{
			Alert.info(data.response.message);
			/*$(".createVersion").hide();*/
			$("#TAHDRDates .editTahdrDate").css("display","none");
			if(data.version!=null)
			$("#TAHDRDetail #version").val(data.version);
		}
		
		if ( $('.leftPaneData li').length == 0 ) {
			 $('#impDatesTab').addClass('readonly');
			 $('#getTahdrMaterialList').addClass('readonly');
			 $('#tenderDocTab').addClass('readonly');
			 $('#sectionDocTab').addClass('readonly');
			 $('#confirmation').addClass('readonly');
		}
		else{
			$('#tenderDocTab').removeClass('readonly');
			/*$('#tenderDocsOrdering').removeClass('readonly');*/
			$('#impDatesTab').removeClass('readonly');
			 $('#getTahdrMaterialList').removeClass('readonly');
			 $('#sectionDocTab').removeClass('readonly');
			 $('#confirmation').removeClass('readonly');
		}
		
		$('#leftPane').paginathing({perPage: 10});
	}else{
		if (data.response.hasError) {
					var msg = 'Following Dates Are Not Filled Properly:'+'<br/>';
					$.each(data.response.errors, function(key, value) {
					     msg=msg+'\n'+value.errorMessage +','+ '<br/>';
					       
					   });
					    Alert.warn(msg);
				}
	}
	var documentType=$(".documentType").val();
	setHeaderValues("Code.: "+tahdrCodes, "Title : "+title, "Department : "+department, "Status : "+tenderStatus);
	/*setChildLoadFlag(true);*/
	setChildLoadFlag(true);
	setChangedFlagById("impDatesTab",true);
}

function appendTahdrDetailLi(tahdrDetail,active){
	return appendLiData(tahdrCodes, "Version :"+tahdrDetail.version, tahdrDetail.tahdrFees, tahdrDetail.emdFee, tahdrDetail.tahdrDetailId, active, 'tahdrDetail');
}

function calculateGST(){
	var tenderFees=$("#tahdrFees").val();
	var tenderFeeWithGst=((tenderFees*18)/100);
	var decimalGst= parseFloat(tenderFeeWithGst);
	decimalGst=decimalGst.toFixed(2);
	$("#gst").val(decimalGst);
	var totalTenderfeeWithGst =Number(tenderFees) + Number(tenderFeeWithGst) ;
	var decimalToatlaAmt= parseFloat(totalTenderfeeWithGst);
	decimalToatlaAmt=decimalToatlaAmt.toFixed(2);
	$("#totalFee").val(decimalToatlaAmt);
}

function calculateEMDFeeOnType(){
	if($("input[name='tenderTypeCodeToggle']:checked").val()=='WT'){
		calculateEMDFeeWorks();
	}
	else{
		calculateEMDFee();
	}
}

function calculateEMDFee(){
	var estimatedCost=Number($("#estimatedCost").val())*100000;
	if(estimatedCost>0){
		var calculatedEMDCost=((estimatedCost*1)/100);
		var decimalEMD=parseFloat(calculatedEMDCost);
		emdFeeWithEC=decimalEMD.toFixed(2);
		emdFeeWithEC=((estimatedCost*1)/100);
		$("#emdFee").val(emdFeeWithEC);
		
		if($("#estimatedCost").val()<=100)
			{
			$("#tahdrFees").val(1000);
			calculateGST();
			}
		else if($("#estimatedCost").val()>100 && $("#estimatedCost").val()<=500)
			{
			$("#tahdrFees").val(5000);
			calculateGST();
			}
		else if($("#estimatedCost").val()>500 && $("#estimatedCost").val()<=1000)
			{
			$("#tahdrFees").val(10000);
			calculateGST();
			}
		else if($("#estimatedCost").val()>1000)
			{
			$("#tahdrFees").val(25000);
			calculateGST();
			}
	}else{
		$("#tahdrFees").val('');
		$("#emdFee").val('');
		$("#gst").val('');
		$("#totalFee").val('');
	}
}

/*works*/
function calculateEMDFeeWorks(){
	var estimatedCost=Number($("#estimatedCost").val())*100000;
	if(estimatedCost>0){
		var calculatedEMDCost=((estimatedCost*1)/100);
		var decimalEMD=parseFloat(calculatedEMDCost);
		emdFeeWithEC=decimalEMD.toFixed(2);
		emdFeeWithEC=((estimatedCost*1)/100);
		$("#emdFee").val(emdFeeWithEC);
		
		if($("#estimatedCost").val()<5)
			{
			$("#tahdrFees").val(500);
			calculateGST();
			}
		else if($("#estimatedCost").val()>=5 && $("#estimatedCost").val()<20)
			{
			$("#tahdrFees").val(1000);
			calculateGST();
			}
		else if($("#estimatedCost").val()>=20 && $("#estimatedCost").val()<50)
			{
			$("#tahdrFees").val(2500);
			calculateGST();
			}
		else if($("#estimatedCost").val()>=50 && $("#estimatedCost").val()<500)
			{
			$("#tahdrFees").val(5000);
			calculateGST();
			}
		else if($("#estimatedCost").val()>=500 && $("#estimatedCost").val()<1000)
		{
		$("#tahdrFees").val(10000);
		calculateGST();
		}
		else if($("#estimatedCost").val()>=1000)
		{
		$("#tahdrFees").val(25000);
		calculateGST();
		}
	}else{
		$("#tahdrFees").val('');
		$("#emdFee").val('');
		$("#gst").val('');
		$("#totalFee").val('');
	}
	
}
/*works*/

function emdFeeLessThenCaluclatedValue(e){
	var curentEmd=$("#emdFee").val();
	if(Number(curentEmd)>emdFeeWithEC){
		$("#emdFee").val(curentEmd);
	}
	else{
		$("#emdFee").val(emdFeeWithEC);
	}
}

function onChangeDelivery(){
	var ele=$("#deliveryDuration");
	var deliveryReq=Number($(ele).val());
	if(deliveryReq<=0){
		$(ele).val('');
		Alert.warn("Delivery Requirement cannot be zero or less than zero.")
	}else if(!validateCommencementWithDelivery(ele)){
		Alert.warn("Delivery cannot be less than commencement period.");
	}
}

function onMinQuantity(){
	var minQuantity=$('#minQuantity').val();
	if(Number(minQuantity)<=100){
		$("#minQuantity").val(minQuantity);
	}
	else{
		Alert.warn("Minimum % of Offered Quantity cannot be more than 100.")
		$("#minQuantity").val('');
	}
}

function onChangeComencementPeriod(){
	var ele=$("#commencementPeriod");
	var commencementPeriod=Number($(ele).val());
	if(commencementPeriod<=0){
		$(ele).val('');
		Alert.warn("Commencement period cannot be zero or less than zero.")
	}else if(!validateCommencementWithDelivery(ele)){
		Alert.warn("Commencement Period cannot be greater than Delivery requirement.");
	}
}

function onChangeMonthDate(){
	var ele=$("#commencementPeriod").val();
	var tahdrTypeCode=$("input[name='tenderTypeCodeToggle']:checked").val();
	if(tahdrTypeCode=='WT' || tahdrTypeCode=='FA')
		{
		if(ele<0 || ele==null || ele==undefined || ele==""){
			$("#MonthDate").css("display","none");
			$("#commencementPeriodMonth").removeClass('dropDown');
			$("#commencementPeriodMonth").removeClass('dropDown requiredField');

		}else{
		$("#MonthDate").css("display","block");
		$("#commencementPeriodMonth").addClass('dropDown');
		$("#commencementPeriodMonth").addClass('dropDown requiredField');
		}
	}else{
	
		if(ele<0 || ele==null || ele==undefined || ele==""){
			$("#MonthDate").css("display","none");
		}else{
		$("#commencementPeriod").addClass('requiredField');
		$("#MonthDate").css("display","block");
		$("#commencementPeriodMonth").addClass('dropDown requiredField');
		}
	}
}

function validateCommencementWithDelivery(ele){
	var deliveryReq=Number($("#deliveryDuration").val());
	var commencementPeriod=Number($("#commencementPeriod").val());
	if(deliveryReq<commencementPeriod){
		$(ele).val('');
		return false;
	}else{
		return true;
	}
}


function populateDesignation(list){
	$(".designationList").empty();
	$(".designationList").append("<option value=''></option>" );
	$.each(list,function(idx,obj){
		$(".designationList").append("<option value='"+obj.designationId+"'>" + obj.name +" </option>" );
	});
}

function onTechnicalBidClick() {
    if ($('#isTechnicalBid').is(":checked")) {
        $(".techBid").show();
        $(".techBid").prop('checked', true);
        $(".techBid").val('Y');
    } else {
    	 $(".techBid").hide();
    	 $(".techBid").prop('checked', false);
    	 $(".techBid").val('N');
    }
    requirdedDocsCheck();
}

function requirdedDocsCheck(){
	var isRequiredDoc=false;
	if($('#isTechnicalDocs').is(":checked") || $('#isCommercialDocs').is(":checked") || $('#isPriceDocs').is(":checked")){
   	 $("#isReqDocs").prop('checked', true);
     $("#isReqDocs").val('Y');
     isRequiredDoc=true;
     $('.reqDocsClass').removeAttr('style');
     $("#isReqDocs").attr('disabled',false);
   }else{
	   $("#isReqDocs").prop('checked', false);
	   $("#isReqDocs").val('N');
	   $("#isReqDocs").attr('disabled',true);
	   $('.reqDocsClass').attr('style','display : none ;'); 
   }
	
   if(!isRequiredDoc && !$('#isGTP').is(":checked")){
	   $("#isTechnicalBid").prop('checked', false);
	   $("#isTechnicalBid").val('N');
   }
	preliminaryScrutinyCheck();
}

function gtpCheck(){
    if($('#isGTP').is(":checked")){
    	$("#isTechnicalBid").prop('checked', true);
		$("#isTechnicalBid").val('Y');	
		$('#addGtpToMaterialBtnId').show();
	}else{
		if(!$('#isReqDocs').is(":checked")){
			$("#isTechnicalBid").prop('checked', false);
			$("#isTechnicalBid").val('N');
		}
		$('#addGtpToMaterialBtnId').hide();
	}
}

function preliminaryScrutinyCheck(){
	if(!$('#isReqDocs').is(":checked") && !$('#isGTP').is(":checked")){
		 $("#isPreliminaryScrutiny").prop('checked', false);
		 $("#isPreliminaryScrutiny").val('N');
		 $("#isPreliminaryScrutiny").attr('disabled',true);
	}else{
		 $("#isPreliminaryScrutiny").attr('disabled',false);
	}
	
	if($('#isPreliminaryScrutiny').is(":checked")){
		$("#isFinalScrutiny").attr('disabled',false);
		$("#isDeviation").attr('disabled',false);
	}else{
		 $("#isDeviation").prop('checked', false);
		 $("#isDeviation").val('N');
		 $("#isDeviation").attr('disabled',true);
		
		 $("#isFinalScrutiny").prop('checked', false);
		 $("#isFinalScrutiny").val('N');
		 $("#isFinalScrutiny").attr('disabled',true);
	}
}

function onTenderDocsClick(){
	if(!$('#isTenderDocs').is(":checked")){
		$('.tenderDocsClass').attr('style','display : none ;');
	}else{
		$('.tenderDocsClass').removeAttr('style');
	}
}

function preparationScopeCheck(){
	 gtpCheck();
	 requirdedDocsCheck();
	 onTenderDocsClick();
	 /*onPreliminaryScrutinyCheck();*/
}