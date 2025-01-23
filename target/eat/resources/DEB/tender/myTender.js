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
var bidderStatus = new Array();
//var plugin_index = $('.paginate').length;
var roleDto;
var dataUrlTypeCode;
var dataUrl;
var leftPanePageSize = 10;
var maxVisiblePageNumbers = 3;
var selectedPage = 1;
var url='';

function processResponse(data){

	swal(data.response.message);
	responseData=data;
	
}

$(document).ready(function(){
	
			var lengthMenu;
			var documentType=$(".documentType").val();
			if(documentType=='Tender'){
				url="value.tenderUrl";
			}else if(documentType=='Auction'){
				url="value.auctionUrl";
			}
		
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
			populateMaterialGroupList(fetchList("getMaterialGroupList",null));/*
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
		var subGrpList=fetchList("getSubGroupByGroup",id);
		populateSubGroupList(subGrpList);
	})
	
	$(".materialSubGroupList").on("change",function(){
		var id= $(this).val();
		var materialList=fetchList("getMaterialBySubGroup",id);
		populateMaterialList(materialList);
	})
	
	$(".toggleNewTab").on("click",function(){
		$('.tenderBaseInfoTabli').addClass('k-state-active');
		$('.tenderBaseInfoTabli').css({'display': 'inline-block'});
		$('.tenderBaseInfoTabdiv').addClass('k-state-active');
		$('.tenderBaseInfoTabdiv').css({'display': 'block'});
	})
	
	$("input[name='tenderTypeCodeToggle']").on("change",function(){
			
		$('.pagination').children().remove();
		$('.tenderBaseInfoTab').addClass('k-state-active');
		$('.tenderBaseInfoTab').css({'display': 'inline-block'});
		$("#tahdrForm")[0].reset();
		var typeCode=$(this).val();
		/*var url="getMyTendersByTypeCode"+"/"+typeCode+"/"+1+"/"+10;*/
		selectedPage = 1;
		var url="getMyTendersByTypeCode?tenderTypeCode="+typeCode+"&pageNumber=1&pageSize="+leftPanePageSize+"&searchMode=none&searchValue=none"
		var tahdrList=fetchList(url);
		/*var tahdrList=fetchList("getMyTendersByTypeCode",typeCode);*/
		/*+"/"+1+"/"+10*/
		$('.disclearfix').css('display','block');
		
		if ( $('.leftPaneData li').length == 0 ) {
			$('.pagination').children().remove();
			$('#tahdrDetailTab').addClass('readonly');
			$('#impDatesTab').addClass('readonly');
			$('#tenderDocTab').addClass('readonly');
			/*$('#tenderDocsOrdering').addClass('readonly');*/
			$('#getTahdrMaterialList').addClass('readonly');
			$('#sectionDocTab').addClass('readonly');
			$('#confirmation').addClass('readonly');
			//$('#leftPane').paginathing({perPage: 6});
				
		}
		else{
			$('.pagination').children().remove();
			 $('#tahdrDetailTab').removeClass('readonly');
			 //$('#leftPane').paginathing({perPage: 6});					
			 $(".tabs-left li a label").text(function(index, currentText) {
				    return currentText.substr(0, 20);
				});
		}
		/*loadTahdrList(tahdrList,$(this).val());*/
		loadTahdrToLeftPane(tahdrList.data);
		setPagination(tahdrList.objectMap.LastPage, selectedPage, maxVisiblePageNumbers,'pagination-myTender-here');
		if(typeCode=='WT'){
			loadFieldsForWork();
		}else if(typeCode=='PT'){
			loadFieldsForProcurement();	
		}
		
		/*if(roleDto.value=='EXEENGR'){
			$("#officeType").val('HO');
			$("#officeType").addClass('readonly');
			loadOfficeLocation();
		}*/
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
	$("#myTenderBaseInfoTab").on('click',function(event) {

			
		$('.pagination').children().remove();
		$("#tahdrForm")[0].reset();
		var typeCode=$('input[name=tenderTypeCodeToggle]:checked').val();
		/*var url="getMyTendersByTypeCode"+"/"+typeCode+"/"+1+"/"+10;
		var tahdrList=fetchList(url);*/
		var searchby = $('#searchLiteralId').val();
		var searchMode = $('input[name=filterBy]:checked').val();
		if(searchby != ''){
			var response = fetchMyTenderList(selectedPage, leftPanePageSize, searchMode, searchby);
			loadTahdrToLeftPane(response.data);
			setPagination(response.objectMap.LastPage, selectedPage , maxVisiblePageNumbers,'pagination-myTender-here');
		}else{
			var response = fetchMyTenderList(selectedPage, leftPanePageSize, 'none', 'none');
			loadTahdrToLeftPane(response.data);
			setPagination(response.objectMap.LastPage, selectedPage , maxVisiblePageNumbers,'pagination-myTender-here');
		}
		/*var tahdrList=fetchList("getMyTendersByTypeCode",typeCode);*/
		/*+"/"+1+"/"+10*/
		$('.disclearfix').css('display','block');
		
		if ( $('.leftPaneData li').length == 0 ) {
			$('#tahdrDetailTab').addClass('readonly');
			$('#impDatesTab').addClass('readonly');
			$('#tenderDocTab').addClass('readonly');
			/*$('#tenderDocsOrdering').addClass('readonly');*/
			$('#getTahdrMaterialList').addClass('readonly');
			$('#sectionDocTab').addClass('readonly');
			$('#confirmation').addClass('readonly');
			//$('#leftPane').paginathing({perPage: 6});
				
		}
		else{
			 $('#tahdrDetailTab').removeClass('readonly');
			 //$('#leftPane').paginathing({perPage: 6});					
			 $(".tabs-left li a label").text(function(index, currentText) {
				    return currentText.substr(0, 20);
				});
		}
		/*loadTahdrList(tahdrList,$(this).val());*/
		/*loadTahdrToLeftPane(tahdrList);*/
		if(typeCode=='WT'){
			loadFieldsForWork();
		}else if(typeCode=='PT'){
			loadFieldsForProcurement();	
		}
		
		/*if(roleDto.value=='EXEENGR'){
			$("#officeType").val('HO');
			$("#officeType").addClass('readonly');
			loadOfficeLocation();
		}*/
	
	});
	
	if ( $( "#pagination-myTender-here" ).length ) {
		 
		$('#pagination-myTender-here').paginate({
			pageSize:  10,
			dataSource: 'fetchMyTenderList',
			responseTo:  'loadTahdrToLeftPane',
			maxVisiblePageNumbers:3,
			searchBoxID : 'searchLiteralId',
			loadOnStartup : false,
			pId: 'pagination-myTender-here'
		});
	 
	}
	
	
	/*dataUrl=$(".dataUrl").val();
	loadTahdrListDropDowns(dataUrl+"/"+$("input[name='tenderTypeCodeToggle']:checked").val());*/
	/*loadTahdrListDropDowns("getMyTenders/"+'WT'+"/"+1+"/"+10);*/
	loadTahdrListDropDowns("getMyTenders?tenderTypeCode="+$("input[name='tenderTypeCodeToggle']:checked").val()+"&pageNumber=1&pageSize="+leftPanePageSize+"&searchMode=none&searchValue=none");
	/*$('#mTenderBaseForm').addClass('readonly');
	$('#myTenderDatesForm').addClass('readonly');
	$('#myTenderDetailForm').addClass('readonly');
	$('#myTenderStdCustDocsForm').addClass('readonly');
	$('#myTenderMaterialForm').addClass('readonly');
	$('#myTenderRequiredDocsForm').addClass('readonly');*/
});
function itemListDropDown(values)
{
	
		$("#selectedMaterialId").html("");
		var options='';
		$.each(values.map,function(index,val){
			options +='<option value="'+index+'">'+val +'</option>'
			
		});
		$("#selectedMaterialId").append(options);
		
		 $('#selectedMaterialUom').val(values.uomName);
		 $('#selectedMaterialDesc').val(values.matDescription);
		 
} 
function populateTahdrMaterialDetail(tahdrMaterial){
	$("#saveTahdrMaterialForm").attr("action","updateTahdrMaterial");
	$(".saveTahdrMaterial").css("display","none");
	$(".updateTahdrMaterial").css("display","inline-block");
	
}

function loadTahdrListDropDowns(url){
	
	var tahdrPreparationData=fetchList(url,null);
	tahdrTypeList=tahdrPreparationData.objectMap.tahdrTypeList;
	bidTypeList=tahdrPreparationData.objectMap.bidTypeList;
	priceBasis=tahdrPreparationData.objectMap.priceBasis;
	officeType=tahdrPreparationData.objectMap.officeType;
	budgetType=tahdrPreparationData.objectMap.budgetType;
	bidSection=tahdrPreparationData.objectMap.bidSection;
	tahdrStatusList =  tahdrPreparationData.objectMap.tenderStatus;
	bidderStatus=tahdrPreparationData.objectMap.bidderStatus;
	console.log(bidderStatus);
	roleDto = tahdrPreparationData.objectMap.roleDto;
	loadReferenceList("tahdrTypeCode",tahdrTypeList);
	loadReferenceList("bidTypeCode",bidTypeList);
	loadReferenceList("priceBasis",priceBasis);
	/*loadReferenceList("officeType",officeType);*/
	loadReferenceList("budgetType",budgetType);
	loadReferenceList("sectionCode",bidSection);
	populateDepartment(tahdrPreparationData.objectMap.deptList);
	/*if(roleDto.value=='VENADM'){
		populateMyTenderLocationTypeForVendor(tahdrPreparationData.objectMap.officeType);
	}else{
		populateLocationType(tahdrPreparationData.objectMap.officeType);
	}*/
	populateLocationType(tahdrPreparationData.objectMap.officeType);
	tahdrViewButton=tahdrPreparationData.objectMap.viewButton;
	/*loadTahdrList(tahdrPreparationData.objectMap.listTahdr,tahdrPreparationData.objectMap.tenderType);*/
	/*loadTahdrList(tahdrPreparationData.data,tahdrPreparationData.objectMap);*/
	loadTahdrList(tahdrPreparationData.data,tahdrPreparationData.objectMap);
	setPagination(tahdrPreparationData.objectMap.LastPage, 1, maxVisiblePageNumbers,'pagination-myTender-here');
}


function loadTahdrList(listTahdr,data){
	
	$("#tahdrType").val(data.tenderType);
	$("#tabstrip").kendoTabStrip();
	$("#tahdrForm")[0].reset();
	if(roleDto.value=='VENADM'){
		if(!$.isEmptyObject(data)){
			
			var isContractor=data.isContractor;
			var isVendor=data.isVendor;
			var isManufacturer=data.isManufacturer;
			if(isContractor && isVendor){
    			$('#labelWorks').addClass('active');
    			loadFieldsForWork();
    		}else if(isContractor){
        			$('#labelWorks').addClass('active');
        			$("#labelProcurement").removeClass('active');
        			$("#procurementToggleId").removeAttr('checked');
        			$("#worksToggleId").attr('checked','checked');
        			$("#labelProcurement").addClass('readonly');
        			$("#labelProcurement").css({backgroundColor:'#fcc'});
        			loadFieldsForWork();
        	}else if (isVendor){
        			$('#labelProcurement').addClass('active');
        			$("#labelWorks").removeClass('active');
        			$("#worksToggleId").removeAttr('checked');
        			$("#procurementToggleId").attr('checked','checked');
        			$("#labelWorks").addClass('readonly');
        			$("#labelWorks").css({backgroundColor:'#fcc'});
        			loadFieldsForProcurement();	
        	}else{
        		$("#labelWorks").addClass('readonly', 'readonly').css({backgroundColor:'#fcc'});
        		$("#labelProcurement").addClass('readonly', 'readonly').css({backgroundColor:'#fcc'});
        	}
		}else{
			
		}	
	}else {
		/*$("#officeType").val('HO');
		$("#officeType").addClass('readonly');*/
		$('#labelWorks').addClass('active');
		/*loadOfficeLocation();*/
		loadFieldsForWork();
	}
	loadTahdrToLeftPane(listTahdr);
	$("#tabstrip").kendoTabStrip();
}

/*function showSubmitButton(listTahdr,role){
	
	
	if(role=='EXEENGR' && listTahdr.tahdrStatusCode=='DR'){
	$("#tahdrSubmitDivId").show();
	$("#ApproveRejlDivId").hide();
	
	}
	else if(role=='EXEENGR' && listTahdr.tahdrStatusCode=='IP'){
		$("#tahdrSubmitDivId").addClass('readonly');
		$("#ApproveRejlDivId").hide();
	}
	else if(role=='CHFENGR' && listTahdr.tahdrStatusCode=='IP'){
		$("#ApproveRejlDivId").show();
		$("#tahdrSubmitDivId").hide();
	}
	
}*/

function loadFieldsForWork(){
	$(".workTender").find('input, textarea,select').removeAttr('disabled','disabled');
	$(".workTender").show();
	$("#sectionTahdrMaterialData").hide();
	$(".procurementTender").find('input, textarea,select').attr('disabled','disabled');
	$(".procurementTender").hide();
	$("#commencementPeriod").removeClass("requiredField");
	$("#commencementPeriodSpan").text("");
}

function loadFieldsForProcurement(){
	$(".workTender").find('input, textarea,select').attr('disabled','disabled');
	$(".workTender").hide();
	$(".procurementTender").find('input, textarea,select').removeAttr('disabled','disabled');
	$(".procurementTender").show();
	$('.disclearfix').css('display','block');
	$("#commencementPeriod").addClass("requiredField");
	$("#commencementPeriodSpan").text("*");
}
function onCheckIsAuction(){
	if($('#isAuction').prop('checked')){
		$('#minBidDifferenceDiv').show();
		$('#minBidDifference').removeAttr('disabled');
	}else{
		$('#minBidDifferenceDiv').hide();
		$('#minBidDifference').attr('disabled','disabled');
	}
}

function fetchMyTenderList(pageNumber, pageSize, searchMode, searchValue){
	
	var response;
	$.ajax({
        type : "POST",
        contentType : "application/json",
        /*url: "getMaterialList/"+pageNumber+"/"+pageSize+'/'+searchMode+'/'+searchValue,*/
        /*url: "getMaterialList?pageNumber="+pageNumber+"&pageSize="+pageSize+"&searchMode="+searchMode+"&searchValue="+searchValue,*/
        url: "getMyTendersByTypeCode?tenderTypeCode="+$("input[name='tenderTypeCodeToggle']:checked").val()+"&pageNumber="+pageNumber+"&pageSize="+pageSize+"&searchMode="+searchMode+"&searchValue="+searchValue,
        dataType:"json",
        async:false,
        success: function (data) {
        	response = data;
        },
        error: function (e) {
			Alert.warn(e.message);
        }
    });
	return response;
}