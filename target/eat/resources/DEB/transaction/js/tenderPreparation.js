/**
 * @author Ankush
 */

var responseData;
var tableRow;
var bidTypeList;
var tahdrTypeList;
var priceBasis;
var gtpCount;
var tahdrMaterial;
var officeType;
var budgetType;
var bidSection;
var tahdrStatusList;
var tahdrViewButton = new Array();
var roleDto;
var dataUrlTypeCode;
var dataUrl;
var leftPanePageSize = 10;
var maxVisiblePageNumbers = 3;
var selectedPage = 1;

function processResponse(data){
	if(!isEmpty(data) && !isEmpty(data.response)){
		swal(data.response.message);
		responseData=data;
	}
}

$(document).ready(function(){
			var lengthMenu;
			
		    if ($(window).width() < 480) {
		        $('.mobileNav').show();
		        $.fn.DataTable.ext.pager.numbers_length = 4;       
		        lengthMenu = [ 1, 5, 7, 10, ],
		        [ 1, 5, 7, 10, ]
		    } else {        
		        lengthMenu = [ 5, 10, ],
		        [ 5, 10, ]
		    }
		    
		$('.addItemPopup').click(function(event){
			event.preventDefault();
			$('.commenSearchModal').modal('show');
			$(".itemTable").DataTable().destroy();
			$(".itemTable tbody").empty();
			var data=fetchList("getMaterialGroupList",null);
			populateMaterialGroupList(data);/*
			populateSubGroupList(fetchList("getMaterialSubGroupList",null));*/
			
		});
	
	/*$(".searchMaterial").on("click",function(){
		$("#tahdrMaterialDiv").css("display","none");
		$("#selectMaterialDiv").css("display","block");
		populateMaterialGroupList(fetchList("getMaterialGroupList",null));
	});*/
	
	$("#selectMaterialBtn").on("click",function(){
		$("#tahdrMaterialDiv").css("display","block");
		$("#selectMaterialDiv").css("display","none");
		$("#selectedMaterialId").empty();
		$("#selectedMaterialId").append($('#srchMaterial').find(":selected"));
		$("#selectedMaterialDesc").val($('#selectedMaterialId').children().attr("description"));
		$("#selectedMaterialUom").val($('#selectedMaterialId').children().attr("uom"));
	});
	
	$(".resetSrchMaterial").on("click", function(event){
		event.preventDefault();
		var grpList=fetchList("getMaterialGroupList",null);
		populateMaterialGroupList(grpList);
	});
	
	$(".cancelSrchMaterial").on("click", function(event){
		event.preventDefault();
		$("#tahdrMaterialDiv").css("display","block");
		$("#selectMaterialDiv").css("display","none");
	});
	
	$(".materialGroupList").on("change",function(){
		var id= $(this).val();
		if(id!=''){
			var subGrpList=fetchList("getSubGroupByGroup",id);
			populateSubGroupList(subGrpList);
		}
	})
	
	$(".materialSubGroupList").on("change",function(){
		var id= $(this).val();
		if(id!=''){
		var materialList=fetchList("getMaterialBySubGroup",id);
		populateMaterialList(materialList);
		}
	})
	
	
	
	$("input[name='tenderTypeCodeToggle']").on("change",function(){
		$('.pagination-container').remove();
		$("#tahdrForm")[0].reset();
		var tenderTypeCode=$(this).val();
		dataUrlTypeCode=$(".dataUrlTypeCode").val();
		/*var url=dataUrlTypeCode+"/"+$(this).val()+"/"+1+"/"+leftPanePageSize+"/none"+"/none"*/
		selectedPage = 1;
		var url=dataUrlTypeCode+"?tahdrTypeCode="+$(this).val()+"&pageNumber=1&pageSize="+leftPanePageSize+"&searchMode=none&searchValue=none"
		var tahdrList=fetchList(url);
		$('.disclearfix').css('display','block');
		if ( $('.leftPaneData li').length == 0 ) {
			$('.pagination').children().remove();
			$('#tahdrDetailTab').addClass('readonly');
			$('#impDatesTab').addClass('readonly');;
			$('#tenderDocTab').addClass('readonly');
			/*$('#tenderDocsOrdering').addClass('readonly');*/
			$('#getTahdrMaterialList').addClass('readonly');
			$('#sectionDocTab').addClass('readonly');
			$('#confirmation').addClass('readonly');
			//$('#leftPane').paginathing({perPage: 6});
				
		}
		else{
			$('.pagination-container').remove();
			 $('#tahdrDetailTab').removeClass('readonly');
			 //$('#leftPane').paginathing({perPage: 6});					
			 $(".tabs-left li a label").text(function(index, currentText) {
				    return currentText.substr(0, 20);
				});
		}
		
		if($(".dataUrlTypeCode").val()=='getTAHDRByTypeCode' && $(".dataUrl").val()=='tenderPreparationData'){
			$(".addTahd").show();
		}
		else if($(".dataUrlTypeCode").val()=='getTAHDRApprovalByTypeCode' && $(".dataUrl").val()=='tenderApprovalData'){
			$(".addTahd").hide();
		}
		if(!isEmpty(tahdrList) && !isEmpty(tahdrList.objectMap)){
			loadTahdrList(tahdrList.data,$(this).val());
			setPagination(tahdrList.objectMap.LastPage, selectedPage, maxVisiblePageNumbers);
			setCurrentTab($("#tenderBaseInfoTab"));
			setChildLoadFlag(true);
		}
		
	});

	$("#budgetType").on("change",function(event){
		
		event.preventDefault();
		var budgetType=$(this).val();
		if($.isEmptyObject(budgetType)){
			$('#schemeNameDivId').show();
			$('#schemeCodeDivId').show();
			$('#schemeName').removeAttr('disabled');
			$('#schemeCode').removeAttr('disabled');
		}else if(budgetType=="RVN"){
			$('#schemeNameDivId').hide();
			$('#schemeCodeDivId').hide();
			$('#schemeName').attr('disabled','disabled');
			$('#schemeCode').attr('disabled','disabled');
		}else if(budgetType=="CAP"){
			$('#schemeNameDivId').val('');
			$('#schemeCodeDivId').val('');
			$('#schemeNameDivId').show();
			$('#schemeCodeDivId').show();
			$('#schemeName').removeAttr('disabled');
			$('#schemeCode').removeAttr('disabled');
		}
	});
	
	$("#reqDocTable").on("change",".enableAddRow",function(){
		var id=$(this).attr("id").split('_');
		var tr=$(this).parents('tr');
		var siblingsArray=$(tr).find('.enableAddRow');
		var flag=true;
		$.each(siblingsArray,function(idx,sibling){
			if($.isEmptyObject($(sibling).val())){
				flag=false;
			}
		});
		if(flag){
			$(tr).find(".addRow").removeAttr("disabled");
		}else{
			$(tr).find(".addRow").attr("disabled","disabled");
		}
	});
	dataUrl=$(".dataUrl").val();
	loadTahdrListDropDowns(dataUrl+"?tenderTypeCode="+$("input[name='tenderTypeCodeToggle']:checked").val()+"&pageNumber=1&pageSize="+leftPanePageSize+"&searchMode=none&searchValue=none");
	
});

function itemListDropDown(values){
		$("#selectedMaterialId").html("");
		$("#saveTahdrMaterialForm")[0].reset();
		var options='';
		var materialId;
		if(!isEmpty(values) && !isEmpty(values.map)){
		$.each(values.map,function(index,val){
			options +='<option value="'+index+'">'+val +'</option>'
			materialId=index;
		});
		}
		$("#selectedMaterialId").append(options);
		var material=fetchList("getMaterial",materialId);
		var uomName=values==null?'':values.uomName==null?'':values.uomName;
		var matDescription=values==null?'':values.matDescription==null?'':values.matDescription; 
		var materialCode=material==null?'':material.code==null?'':material.code;
		var hsnCode=material==null?'':material.hsnCode==null?'':material.hsnCode.code==null?'':material.hsnCode.code;
		$('#selectedMaterialUom').val(uomName);
		 $('#selectedMaterialDesc').val(matDescription);
		 $('#selectedItemCode').val(materialCode);
		 $('#selectedHSNCode').val(hsnCode);
		 var materialSelectedId=$("#selectedMaterialId").val();
			includeSpec(materialSelectedId);
} 

function populateTahdrMaterialDetail(tahdrMaterial){
	$("#saveTahdrMaterialForm").attr("action","updateTahdrMaterial");
	$(".saveTahdrMaterial").css("display","none");
	$(".updateTahdrMaterial").css("display","inline-block");
}

function loadTahdrListDropDowns(url){
	var tahdrPreparationData=fetchList(url,null);
	tahdrTypeList=tahdrPreparationData==null?'':tahdrPreparationData.objectMap==null?'':tahdrPreparationData.objectMap.tahdrTypeList==null?'':tahdrPreparationData.objectMap.tahdrTypeList;
	bidTypeList=tahdrPreparationData==null?'':tahdrPreparationData.objectMap==null?'':tahdrPreparationData.objectMap.bidTypeList==null?'':tahdrPreparationData.objectMap.bidTypeList;
	priceBasis=tahdrPreparationData==null?'':tahdrPreparationData.objectMap==null?'':tahdrPreparationData.objectMap.priceBasis==null?'':tahdrPreparationData.objectMap.priceBasis;
	officeType=tahdrPreparationData==null?'':tahdrPreparationData.objectMap==null?'':tahdrPreparationData.objectMap.officeType==null?'':tahdrPreparationData.objectMap.officeType;
	budgetType=tahdrPreparationData==null?'':tahdrPreparationData.objectMap==null?'':tahdrPreparationData.objectMap.budgetType==null?'':tahdrPreparationData.objectMap.budgetType;
	bidSection=tahdrPreparationData==null?'':tahdrPreparationData.objectMap==null?'':tahdrPreparationData.objectMap.bidSection==null?'':tahdrPreparationData.objectMap.bidSection;
	tahdrStatusList =  tahdrPreparationData==null?'':tahdrPreparationData.objectMap==null?'':tahdrPreparationData.objectMap.tenderStatus==null?'':tahdrPreparationData.objectMap.tenderStatus;
	roleDto = tahdrPreparationData==null?'':tahdrPreparationData.objectMap==null?'':tahdrPreparationData.objectMap.roleDto==null?'':tahdrPreparationData.objectMap.roleDto;
	loadReferenceList("tahdrTypeCode",tahdrTypeList);
	loadReferenceList("bidTypeCode",bidTypeList);
	loadReferenceList("priceBasis",priceBasis);
	/*loadReferenceList("officeType",officeType);*/
	loadReferenceList("budgetType",budgetType);
	loadReferenceList("sectionCode",bidSection);
	var deptlist=tahdrPreparationData==null?'':tahdrPreparationData.objectMap==null?'':tahdrPreparationData.objectMap.deptList==null?'':tahdrPreparationData.objectMap.deptList;
	populateDepartment(deptlist);
	var offType=tahdrPreparationData==null?'':tahdrPreparationData.objectMap==null?'':tahdrPreparationData.objectMap.officeType==null?'':tahdrPreparationData.objectMap.officeType;
	populateLocationType(offType)
	tahdrViewButton=tahdrPreparationData==null?'':tahdrPreparationData.objectMap==null?'':tahdrPreparationData.objectMap.viewButton==null?'':tahdrPreparationData.objectMap.viewButton;
	setCurrentTab($("#tenderBaseInfoTab"));
	setChangedFlag(false);
	if(!isEmpty(tahdrPreparationData) && !isEmpty(tahdrPreparationData.objectMap)){
		loadTahdrList(tahdrPreparationData.objectMap.listTahdr.data,tahdrPreparationData.objectMap.tenderTypeCode);
		setPagination(tahdrPreparationData.objectMap.listTahdr.objectMap.LastPage, 1, maxVisiblePageNumbers);
	}
}


function loadTahdrList(listTahdr,tenderTypeCode){
	$("#tabstrip").kendoTabStrip();
	$("#tahdrForm")[0].reset();
	
	if($(".dataUrlTypeCode").val()=='getTAHDRByTypeCode' && $(".dataUrl").val()=='tenderPreparationData'){
		$(".addTahd").show();
		
		
	}
	else if($(".dataUrlTypeCode").val()=='getTAHDRApprovalByTypeCode' && $(".dataUrl").val()=='tenderApprovalData'){
		$(".addTahd").hide();
		
	}
	
	$("#tahdrType").val(tenderTypeCode);
	if(tenderTypeCode=="PT"){		
		loadFieldsForProcurement();		
	}else if(tenderTypeCode=="WT"){
		loadFieldsForWork();
	}

	loadTahdrToLeftPane(listTahdr);
	$("#tabstrip").kendoTabStrip();
}


function loadFieldsForWork(){
	$(".workTender").find('input, textarea,select').removeAttr('disabled','disabled');
	$(".workTender").show();
	$("#sectionTahdrMaterialData").hide();
	$(".procurementTender").find('input, textarea,select,radio').attr('disabled','disabled');
	$(".procurementTender").hide();
	$("#commencementPeriod").removeClass("requiredField");
	$("#commencementPeriod").attr("onchange", "onChangeMonthDate()");
	$("#commencementPeriodSpan").text("");
	$("#selectedMaterialVersionId").removeClass("requiredField");
}

function loadFieldsForProcurement(){
	$(".workTender").find('input, textarea,select').attr('disabled','disabled');
	$(".workTender").hide();
	$(".procurementTender").find('input, textarea,select,radio').removeAttr('disabled','disabled');
	$(".procurementTender").show();
	$('.disclearfix').css('display','block');
	$("#commencementPeriod").addClass("requiredField");
	$("#commencementPeriod").attr("onchange", "onChangeComencementPeriod(); onChangeMonthDate();");
	$("#commencementPeriodSpan").text("*");
	$("#selectedMaterialVersionId").addClass("requiredField");
}
function onCheckIsAuction(){
	if($('#isAuction').prop('checked')){
		$('#minBidDifferenceDiv').show();
		$('.auctionDates').show();
		$('.auctionLater').show();
		$('#minBidDifference').removeAttr('disabled');
		$('#auctionStartToDates').removeAttr('disabled');
		$('#auctionEndToDates').removeAttr('disabled');
		$('#isAuctionSetLater').removeAttr('disabled');
		$('.basisdisclearfix').hide();
	}else{
		$('#minBidDifferenceDiv').hide();
		$('.auctionDates').hide();
		$('.auctionLater').hide();
		$('.basisdisclearfix').show();
		$('#minBidDifference').attr('disabled','disabled');
		$('#auctionStartToDates').attr('disabled','disabled');
		$('#auctionEndToDates').attr('disabled','disabled');
		$('#isAuctionSetLater').attr('disabled','disabled');
	}
}
