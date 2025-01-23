/**
 * @author Ankita
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
var roleDto;
var awardWinner;
var type;

var pageSize = 7;
var maxVisiblePage = 3;

function processResponse(data){
	if(!isEmpty(data) && !isEmpty(data.response) ){
		swal(data.response.message);
		responseData=data;
	}
}

$(document).ready(function(){
	 
	
	loadTahdrListValues();
	
	$('#pagination-here').paginate({
		pageSize:  pageSize,
		dataSource: 'fetchData',
		responseTo:  'loadTahdrToLeftPane',
		maxVisiblePageNumbers:maxVisiblePage,
		searchBoxID : 'searchLiteralId',
		loadOnStartup : false
	});
	
	$("input[name='tenderTypeCodeToggle']").on("change",function(){
		$('.pagination').children().remove();
		var tahdrList=fetchData(1,pageSize,'none','none');
		setPagination(tahdrList.objectMap.LastPage, 1 , maxVisiblePage); 
		$('.disclearfix').css('display','block');
		 $(".tabs-left li a label").text(function(index, currentText) {
			    return currentText.substr(0, 20);
		});
		loadTahdrList(tahdrList.objectMap.listTahdr,$(this).val());
	});

	$(".winnerConfirmationTab").on('click',function(event) {
		event.preventDefault();
		$('#leftPane').html('');
		setLeftPaneHeader(" Confirmation", null);
	});

});
 
function loadTahdrListValues(){
	 
	//var tahdrPreparationData=fetchList(url,null);
	var tahdrPreparationData = fetchData(1,pageSize,'none','none');
	setPagination(tahdrPreparationData.objectMap.LastPage, 1 , maxVisiblePage);

	tahdrTypeList=tahdrPreparationData.objectMap.tahdrTypeList;
	tahdrStatusList =  tahdrPreparationData.objectMap.tenderStatus;
	roleDto = tahdrPreparationData.objectMap.roleDto;
	awardWinner =tahdrPreparationData.objectMap.awardWinner.length;
	loadReferenceList("tahdrTypeCode",tahdrTypeList);
	tahdrViewButton=tahdrPreparationData.objectMap.viewButton;
	loadTahdrList(tahdrPreparationData.data,tahdrPreparationData.objectMap.tenderTypeCode);
}


function loadTahdrList(listTahdr,tenderTypeCode){
	 
	$("#tabstrip").kendoTabStrip();
	loadTahdrToLeftPane(listTahdr);
	$("#tabstrip").kendoTabStrip();
}

function fetchData(pageNumber, pageSize, searchMode, searchValue){
	 
	var response;
	var url = '';
	type=$(".typeOfAward").val();
	if(type=='winnerSelection'){
		url="tahdrAwardWinnerData/"+$("input[name='tenderTypeCodeToggle']:checked").val()+"/O/"+pageNumber+"/"+pageSize+'/'+searchMode+'/'+searchValue;	
	}else if(type=='rating'){
		url="tahdrAwardWinnerData/"+$("input[name='tenderTypeCodeToggle']:checked").val()+"/Y/"+pageNumber+"/"+pageSize+'/'+searchMode+'/'+searchValue;
	}
	var response=fetchList(url,null);
	return response;
}


