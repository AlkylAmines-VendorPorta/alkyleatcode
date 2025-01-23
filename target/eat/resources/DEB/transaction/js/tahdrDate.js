$(document).ready(function() {

	$("#impDatesTab").on('click',function(event){
		event.preventDefault();
		$("#leftPane").removeClass('readonly');
		cacheLi();
		setCurrentTab(this);
		getTahdrDates();
	});
	
	$(".saveTahdrDatesBtn").on("click",function(event){
		event.preventDefault();
		$("#datesSaved").val("Y");
		submitIt("TAHDRDates","saveTahdrDates","saveTahdrDetailResp");
		return false;
	});
	
	$('#cancelTahdrDates').click(function(event) {
		event.preventDefault();
		$("#datesSaved").val("N");
		var activeTahdrDetailId=$('.leftPaneData').find('li.active').attr('id');
		if(activeTahdrDetailId!=undefined){
			loadTahdrDetailToRightPane(tahdrDetailArray["tahdrDetail"+activeTahdrDetailId]);
		}else
			$('#TAHDRDates')[0].reset();
    });
	
$("#purchaseFromDate").on('change',function(){
	var purchaseToDate = $('#purchaseFromDate').val();
	var purchaseStartdate = $('#purchaseFromDate').val();
	setDateTimePickerStartWithId("purchaseDate",purchaseToDate);
	setDateTimePickerStartWithId("preBidDates",purchaseStartdate);
	setDateTimePickerStartWithId("technicalBidFromDates",purchaseStartdate);
	setDateTimePickerStartWithId("technicalBidDates",purchaseStartdate);
	setDateTimePickerStartWithId("technicalBidOpeningDates",purchaseStartdate);
	setDateTimePickerStartWithId("priceBidOpeningDates",purchaseStartdate);
	setDateTimePickerStartWithId("AnnexC1OpeningDates",purchaseStartdate);
	setDateTimePickerStartWithId("C1AnnexDates",purchaseStartdate);
	setDateTimePickerStartWithId("auctionStartDates",purchaseStartdate);
	setDateTimePickerStartWithId("auctionEndDates",purchaseStartdate);
	setDateTimePickerStartWithId("winnerDate",purchaseStartdate);
});

$("#purchaseToDate").on('change',function(){
	var purchaseEnddate = $('#purchaseToDate').val();
	setDateTimePickerStartWithId("technicalBidDates",purchaseEnddate);
	/*setDateTimePickerStartWithId("technicalBidOpeningDates",purchaseEnddate);
	setDateTimePickerStartWithId("priceBidOpeningDates",purchaseEnddate);
	setDateTimePickerStartWithId("AnnexC1OpeningDates",purchaseEnddate);
	setDateTimePickerStartWithId("C1AnnexDates",purchaseEnddate);
	setDateTimePickerStartWithId("winnerDate",purchaseEnddate);*/
});

$("#technicalBidFromDate").on('change',function(){
	var techincalToDate = $('#technicalBidFromDate').val();
	var purchaseEndDates=$('#purchaseToDate').val() ;
	var tenderType=$("input[name='tenderTypeCodeToggle']:checked").val();
	if(tenderType!='RFQ' || tenderType!='QRFQ'){
		purchaseEndDates=$('#purchaseToDate').val() ;
	}else{
		purchaseEndDates=new Date();
	}
	var setDate=dateComparatorTime($('#technicalBidFromDate').val(),$('#purchaseToDate').val());
	if(setDate==0){
		setDateTimePickerStartWithId("technicalBidDates",techincalToDate);
	}
	else if(setDate==1){
		setDateTimePickerStartWithId("technicalBidDates",techincalToDate);
	}
	else if(setDate== -1){
		setDateTimePickerStartWithId("technicalBidDates",purchaseEndDates);
	}
	
});

$("#technicalBidToDate").on('change',function(){
	var techincalBidOpenDate = $('#technicalBidToDate').val();
	var purchaseFromDate = $('#purchaseFromDate').val();
	setDateTimePickerStartEndWithId("preBidDates",purchaseFromDate,techincalBidOpenDate);
	setDateTimePickerStartWithId("technicalBidOpeningDates",techincalBidOpenDate);
});

$("#techBidOpenningDate").on('change',function(){
	var priceBidOpenDate = $('#techBidOpenningDate').val();
	setDateTimePickerStartWithId("priceBidOpeningDates",priceBidOpenDate);
	setDateTimePickerStartWithId("C1AnnexDates",priceBidOpenDate);
	setDateTimePickerStartWithId("AnnexC1OpeningDates",priceBidOpenDate);
	setDateTimePickerStartWithId("auctionStartDates",priceBidOpenDate);
	setDateTimePickerStartWithId("auctionEndDates",priceBidOpenDate);
	setDateTimePickerStartWithId("winnerDate",priceBidOpenDate);
});
	
$("#priceBidOpenningDate").on('change',function(){
	var c1OpenningDate = $('#priceBidOpenningDate').val();
	setDateTimePickerStartWithId("auctionStartDates",c1OpenningDate);
	setDateTimePickerStartWithId("auctionEndDates",c1OpenningDate);
	setDateTimePickerStartWithId("C1AnnexDates",c1OpenningDate);
	setDateTimePickerStartWithId("AnnexC1OpeningDates",c1OpenningDate);
	setDateTimePickerStartWithId("winnerDate",c1OpenningDate);
});

$("#auctionStartToDates").on('change',function(){
	var auctionToDate = $('#auctionStartToDates').val();
	setDateTimePickerStartWithId("auctionEndDates",auctionToDate);
});

$("#auctionEndToDates").on('change',function(){
	var auctionENDDate = $('#auctionEndToDates').val();
	setDateTimePickerStartWithId("C1AnnexDates",auctionENDDate);
	setDateTimePickerStartWithId("AnnexC1OpeningDates",auctionENDDate);
	setDateTimePickerStartWithId("winnerDate",auctionENDDate);
});

$("#c1ToDate").on('change',function(){
	var c1ToDate = $('#c1ToDate').val();
	setDateTimePickerStartWithId("AnnexC1OpeningDates",c1ToDate);
	setDateTimePickerStartWithId("winnerDate",c1ToDate);
});

$("#c1OpenningDate").on('change',function(){
	var c1ToDate = $('#c1OpenningDate').val();
	setDateTimePickerStartWithId("winnerDate",c1ToDate);
});

$("#publishDate").on('change',function(){
	var publishDate = $('#publishingDate').val();
	setDatePickerStartWithId("purchaseFromDate",publishDate);
});

});

function onChangeDate(className){
	$('.'+className).removeAttr('disabled','disabled');
}

function onChangeDateId(idName){
	$('#'+idName).removeAttr('disabled','disabled');
}

/* single date validation*/
function onChangeDateValidation(id,el){
	var currentDate=$(el).val().split('-');
	var f1=new Date(currentDate[0],currentDate[1],currentDate[2]);
	var dateCheckWith=$('#'+id).val().split('-');	setDateTimePickerStartWithId("technicalBidOpeningDates",techincalBidOpenDate);

	var d1=new Date(dateCheckWith[0],dateCheckWith[1],dateCheckWith[2]);
	var label=$('#'+id).data('label');
	if(d1>f1){
		$(el).addClass('errorinput');
		$(el).attr('title','should be greater than '+label);
	}else{
		$(el).removeAttr('title');
		$(el).removeClass('errorinput');
	}
	
}
/*single date validation*/

/* date & time validation*/
function onChangeDateTimeValidation(id,el){
	var currentDate=$(el).val().split(/[ -.:?,|\[\]\r\n/\\]+/);
	var f1=new Date(currentDate[0],currentDate[1],currentDate[2],currentDate[3],currentDate[4]);
	var dateCheckWith=$('#'+id).val().split(/[ -.:?,|\[\]\r\n/\\]+/);
	var d1=new Date(dateCheckWith[0],dateCheckWith[1],dateCheckWith[2],dateCheckWith[3],dateCheckWith[4]);
	var label=$('#'+id).data('label');
	if(d1>f1){
		$(el).addClass('errorinput');
		$(el).attr('title','should be greater than '+label);
		$(el).val('');
	}else{
		$(el).removeAttr('title');
		$(el).removeClass('errorinput');
	}
	
}
/*date & time validation*/

function onChangeDateValidation1(id1,id2,el){
	var currentDate=$(el).val().split('-');
	var f1=new Date(currentDate[0],currentDate[1],currentDate[2]);
	var dateCheckWith1=$('#'+id).val().split('-');
	var c1=new Date(dateCheckWith1[0],dateCheckWith1[1],dateCheckWith1[2]);
	var dateCheckWith2=$('#'+id).val().split('-');
	var c2=new Date(dateCheckWith2[0],dateCheckWith2[1],dateCheckWith2[2]);
	var label_c1=$('#'+id).data('label1');
	var label_c2=$('#'+id).data('label2');
	if(c1<=f1 && f1<=c2){
		$(el).addClass('errorinput');
		$(el).attr('title','should be in between '+label_c1+' and '+label_c2);
		$(el).val('');
	}else{
		$(el).removeAttr('title');
		$(el).removeClass('errorinput');
	}
	
}

function getTahdrDates(){
	debugger;
	if(getChangedFlag()){
		var tahdrDetailId=$(".tahdrDetailId").val();
		$('.pagination').children().remove();
		var impDatesList =fetchList("getTAHDRDates",tahdrDetailId);
		var version =impDatesList==null?'':impDatesList.objectMap==null?'':impDatesList.objectMap.tahdrDate[0]==null?'':impDatesList.objectMap.tahdrDate[0].version==null?'':impDatesList.objectMap.tahdrDate[0].version;
		var activeStatus=impDatesList==null?'':impDatesList.objectMap==null?'':impDatesList.objectMap.tahdrDate[0]==null?'':impDatesList.objectMap.tahdrDate[0].isActive==null?'':impDatesList.objectMap.tahdrDate[0].isActive;
		$('.condisn').removeClass("readonly");
		$('#tenderDatesForm .pastdate').removeClass("readonly");
		$('#purchaseFromDate').removeClass("readonly");
		$('#technicalBidFromDate').removeClass("readonly");
		$("#tenderDatesForm").removeClass("readonly");
		if(!isEmpty(impDatesList) && !isEmpty(impDatesList.objectMap)){
			loadTahdrDateToLeftPane(impDatesList.objectMap.tahdrDate);
			loadDatesFromTahdrDetail(impDatesList.objectMap.tahdrDate);
		}
		$("#editedDates").val('N');
		if(activeStatus=='Y' && (tenderStatus == 'Published' || tenderStatus=='Approved')){
			$("#TAHDRDates .editTahdrDate").css("display","inline-block");
			/*$("#TAHDRDates .editDateAfterPublish").css("display","none");*/
		}
		else{
			$("#TAHDRDates .editTahdrDate").css("display","none");
		}
		if(version>1 && activeStatus=='Y'){
			checkfortahdrversion(impDatesList);
		}
		/*if (status == 'Drafted' ){
			$("#TAHDRDates .editTahdrDate").css("display","none");
			$('#tenderDetailForm').removeClass('readonly');
			$('#tenderDatesForm').removeClass('readonly');
			$('#TAHDRDates .saveTahdrDatesBtn').show();
			$('#TAHDRDates .cancel').show();
		}
		else if(status == 'Published'){
			$("#TAHDRDates .editTahdrDate").css("display","block");
			$('#tenderDetailForm').addClass('readonly');
			$('#tenderDatesForm').addClass('readonly');
			$('#TAHDRDetail .saveTahdrDetailsBtn').hide();
			$('#TAHDRDetail .cancel').hide();
		}
		else if(status=='Approved'){
			$("#TAHDRDates .editTahdrDate").css("display","none");
			$('#tenderDetailForm').addClass('readonly');
			$('#tenderDatesForm').removeClass('readonly');
			$('#TAHDRDetail .saveTahdrDetailsBtn').hide();
			$('#TAHDRDetail .cancel').hide();
		}
		else{
			$("#TAHDRDates .editTahdrDate").css("display","none");
			$('#tenderDetailForm').addClass('readonly');
			$('#tenderDatesForm').addClass('readonly');
			$('#TAHDRDates .saveTahdrDatesBtn').hide();
			$('#TAHDRDates .cancel').hide();
		}*/
		$('#leftPane').paginathing({perPage: 10});
	 $(".tabs-left li a label").text(function(index, currentText) {
		    return currentText.substr(0, 20);
		});	
	 setChangedFlag(false);
	}else{
		getCacheLi();
	}
	
	setLeftPaneHeader("Important Dates", null);
}

function loadTahdrDateToLeftPane(tahdrDate) {
		$("#leftPane").html("");
		var leftPanelHtml = '';
		var i = 0;
		var active = "";
		var firstRow = null;
		/*tahdrDetailArray=[];*/
		if(!isEmpty(tahdrDate)){
		$.each(Object.values(tahdrDate),function(key,tahdrDetail){
			
			/*tahdrDetailArray["tahdrDetail"+tahdrDetail.tahdrDetailId]=tahdrDetail;*/
			if (i == 0) {
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
		
		/*$("#leftPane").on('click','.tahdrDetail',function(){
			debugger;
			var id=$(this).attr('id');
			loadTahdrDetailToRightPane(tahdrDetailArray["tahdrDetail"+id]);	
		});*/
		
		$('#leftPane').paginathing({perPage: 10});
	}

function editDates(event){
	event.preventDefault();
	$("#TAHDRDates").removeClass("readonly");
	$("#tenderDatesForm").removeClass("readonly");
	$('#tenderDatesForm .pastdate').removeClass("readonly");
	$('#tenderDatesForm').find('input').removeClass("readonly");
	$('#tenderDatesForm').find('input').removeAttr('tabindex','-1');
	$("#editedDates").val('Y');
	$("#datesSaved").val("N");
	$('#TAHDRDates .saveTahdrDatesBtn').show();
	$('#TAHDRDates .cancel').show();
	var d = new Date();
	var CurrentDate = d.getDate() + "-" + (d.getMonth()+1) + "-" + d.getFullYear();
	var CurrentDateTime= d.getDate() + "-" + (d.getMonth()+1) + "-" + d.getFullYear() + " " + d.getHours() +":"+ d.getMinutes();
	var saleStartDate = $('#purchaseFromDate').val();
	var bidStartDate = $('#technicalBidFromDate').val();
	if(dateComparatorTime(CurrentDateTime,saleStartDate)==1 ){
		$('.condisn').addClass("readonly");
		$('#purchaseFromDate').addClass("readonly");
	}
	if(dateComparatorTime(CurrentDateTime,bidStartDate)==1 ){
		$('.condisn').addClass("readonly");
		$('#technicalBidFromDate').addClass("readonly");
	}
}

function checkfortahdrversion(impDatesList){
	var d = new Date();
	var CurrentDate = d.getDate() + "-" + (d.getMonth()+1) + "-" + d.getFullYear();
	var CurrentDateTime= d.getDate() + "-" + (d.getMonth()+1) + "-" + d.getFullYear() + " " + d.getHours() +":"+ d.getMinutes();
	var saleStartDate = $('#purchaseFromDate').val();
	var saleEndDate = $('#purchaseToDate').val();
	var bidStartDate = $('#technicalBidFromDate').val();
	var bidEndDate =  $('#technicalBidToDate').val();
	var preBidDate =  $('#preBidDate').val();
	var techBidOpenningDate =  $('#techBidOpenningDate').val();
	var isAuction=impDatesList==null?'':impDatesList.objectMap==null?'':impDatesList.objectMap.tahdrDate[0]==null?'':impDatesList.objectMap.tahdrDate[0].tahdr==null?'':impDatesList.objectMap.tahdrDate[0].tahdr.isAuction==null?'':impDatesList.objectMap.tahdrDate[0].tahdr.isAuction;
	if(dateComparatorTime(CurrentDateTime,saleStartDate)==1 ){
		$('.purchaseFromDate').addClass("readonly");
		$('#purchaseFromDate').addClass("readonly");
	}
	if(dateComparatorTime(CurrentDateTime,saleEndDate)==1){
		$('.purchaseToDate').addClass("readonly");
		$('#purchaseToDate').addClass("readonly");
	}
	if(dateComparatorTime(CurrentDateTime,bidStartDate)==1 ){
		$('.technicalBidFromDate').addClass("readonly");
		$('#technicalBidFromDate').addClass("readonly");
	}
	if(dateComparatorTime(CurrentDateTime,bidEndDate)==1 ){
		$('.technicalBidToDate').addClass("readonly");
		$('#technicalBidToDate').addClass("readonly");
	}
	if(dateComparatorTime(CurrentDateTime,preBidDate)==1 ){
		$('.preBidDate').addClass("readonly");
		$('#preBidDate').addClass("readonly");
	}
	if(dateComparatorTime(CurrentDateTime,techBidOpenningDate)==1 ){
		$('.techBidOpenningDate').addClass("readonly");
		$('#techBidOpenningDate').addClass("readonly");
	}
	if($("#tahdrType").val()=='WT'){
		var isPBDSetLater=impDatesList==null?'':impDatesList.objectMap==null?'':impDatesList.objectMap.tahdrDate[0]==null?'':impDatesList.objectMap.tahdrDate[0].isPBDSetLater==null?'':impDatesList.objectMap.tahdrDate[0].isPBDSetLater;
		var isWinnerSelectionDateSetLater=impDatesList==null?'':impDatesList.objectMap==null?'':impDatesList.objectMap.tahdrDate[0]==null?'':impDatesList.objectMap.tahdrDate[0].isWinnerSelectionDateSetLater==null?'':impDatesList.objectMap.tahdrDate[0].isWinnerSelectionDateSetLater;
		if(isPBDSetLater!='Y'){
			var PBDDate = $('#priceBidOpenningDate').val();
			if(dateComparatorTime(CurrentDateTime,PBDDate)==1){
				$('.priceBidOpenningDate').addClass("readonly");
				$('#priceBidOpenningDate').addClass("readonly");
			}
		}
		if(isWinnerSelectionDateSetLater!='Y'){
			var winnerDate=$('#winnerSelectionDate').val();
			if(dateComparatorTime(CurrentDateTime,winnerDate)==1){
				$('.winnerSelectionDate').addClass("readonly");
				$('#winnerSelectionDate').addClass("readonly");
			}
		}
	}
	else if($("#tahdrType").val()=='PT' && isAuction=='N')
	{
		var isPBDSetLater=impDatesList==null?'':impDatesList.objectMap==null?'':impDatesList.objectMap.tahdrDate[0]==null?'':impDatesList.objectMap.tahdrDate[0].isPBDSetLater==null?'':impDatesList.objectMap.tahdrDate[0].isPBDSetLater;
		var isWinnerSelectionDateSetLater=impDatesList==null?'':impDatesList.objectMap==null?'':impDatesList.objectMap.tahdrDate[0]==null?'':impDatesList.objectMap.tahdrDate[0].isWinnerSelectionDateSetLater==null?'':impDatesList.objectMap.tahdrDate[0].isWinnerSelectionDateSetLater;
		var isAnnexureC1=impDatesList==null?'':impDatesList.objectMap==null?'':impDatesList.objectMap.tahdrDate[0]==null?'':impDatesList.objectMap.tahdrDate[0].isAnnexureC1==null?'':impDatesList.objectMap.tahdrDate[0].isAnnexureC1;
		var isSetC1Later=impDatesList==null?'':impDatesList.objectMap==null?'':impDatesList.objectMap.tahdrDate[0]==null?'':impDatesList.objectMap.tahdrDate[0].isSetC1Later==null?'':impDatesList.objectMap.tahdrDate[0].isSetC1Later;
		if(isPBDSetLater!='Y'){
			var PBDDate = $('#priceBidOpenningDate').val();
			if(dateComparatorTime(CurrentDateTime,PBDDate)==1){
				$('.priceBidOpenningDate').addClass("readonly");
				$('#priceBidOpenningDate').addClass("readonly");
			}
		}
		if(isWinnerSelectionDateSetLater!='Y'){
			var winnerDate=$('#winnerSelectionDate').val();
			if(dateComparatorTime(CurrentDateTime,winnerDate)==1){
				$('.winnerSelectionDate').addClass("readonly");
				$('#winnerSelectionDate').addClass("readonly");
			}
		}
		if(isAnnexureC1=='Y'){
		if(isSetC1Later!='Y'){
			var c1ToDate=$('#c1ToDate').val();
			var c1OpenningDate=$('#c1OpenningDate').val();
			if(dateComparatorTime(CurrentDateTime,c1ToDate)==1){
				$('.c1ToDate').addClass("readonly");
				$('#c1ToDate').addClass("readonly");
			}
			if(dateComparatorTime(CurrentDateTime,c1OpenningDate)==1){
				$('.c1OpenningDate').addClass("readonly");
				$('#c1OpenningDate').addClass("readonly");
		}
	}
	}
	}
	else if(($("#tahdrType").val()=='PT' && isAuction=='Y') || $("#tahdrType").val()=='FA')
	{
	
		var isPBDSetLater=impDatesList==null?'':impDatesList.objectMap==null?'':impDatesList.objectMap.tahdrDate[0]==null?'':impDatesList.objectMap.tahdrDate[0].isPBDSetLater==null?'':impDatesList.objectMap.tahdrDate[0].isPBDSetLater;
		var isWinnerSelectionDateSetLater=impDatesList==null?'':impDatesList.objectMap==null?'':impDatesList.objectMap.tahdrDate[0]==null?'':impDatesList.objectMap.tahdrDate[0].isWinnerSelectionDateSetLater==null?'':impDatesList.objectMap.tahdrDate[0].isWinnerSelectionDateSetLater;
		var isAnnexureC1=impDatesList==null?'':impDatesList.objectMap==null?'':impDatesList.objectMap.tahdrDate[0]==null?'':impDatesList.objectMap.tahdrDate[0].isAnnexureC1==null?'':impDatesList.objectMap.tahdrDate[0].isAnnexureC1;
		var isSetC1Later=impDatesList==null?'':impDatesList.objectMap==null?'':impDatesList.objectMap.tahdrDate[0]==null?'':impDatesList.objectMap.tahdrDate[0].isSetC1Later==null?'':impDatesList.objectMap.tahdrDate[0].isSetC1Later;
		var isAuctionDateSetLater=impDatesList==null?'':impDatesList.objectMap==null?'':impDatesList.objectMap.tahdrDate[0]==null?'':impDatesList.objectMap.tahdrDate[0].isAuctionDateSetLater==null?'':impDatesList.objectMap.tahdrDate[0].isAuctionDateSetLater;
		if(isPBDSetLater!='Y'){
			var PBDDate = $('#priceBidOpenningDate').val();
			if(dateComparatorTime(CurrentDateTime,PBDDate)==1){
				$('.priceBidOpenningDate').addClass("readonly");
				$('#priceBidOpenningDate').addClass("readonly");
			}
		}
		if(isWinnerSelectionDateSetLater!='Y'){
			var winnerDate=$('#winnerSelectionDate').val();
			if(dateComparatorTime(CurrentDateTime,winnerDate)==1){
				$('.winnerSelectionDate').addClass("readonly");
				$('#winnerSelectionDate').addClass("readonly");
			}
		}
		if(isAnnexureC1=='Y'){
		if(isSetC1Later!='Y'){
			var c1ToDate=$('#c1ToDate').val();
			var c1OpenningDate=$('#c1OpenningDate').val();
			if(dateComparatorTime(CurrentDateTime,c1ToDate)==1){
				$('.c1ToDate').addClass("readonly");
				$('#c1ToDate').addClass("readonly");
			}
			if(dateComparatorTime(CurrentDateTime,c1OpenningDate)==1){
				$('.c1OpenningDate').addClass("readonly");
				$('#c1OpenningDate').addClass("readonly");
		}
	}
	}
		
	if(isAuctionDateSetLater!='Y' ){
		var auctionStartToDates=$('#auctionStartToDates').val();
		var auctionEndToDates=$('#auctionEndToDates').val();
		if(dateComparatorTime(CurrentDateTime,auctionStartToDates)==1){
			$('auctionStartToDates').addClass("readonly");
			$('#auctionStartToDates').addClass("readonly");
		}
		if(dateComparatorTime(CurrentDateTime,auctionEndToDates)==1){
			$('.auctionEndToDates').addClass("readonly");
			$('#auctionEndToDates').addClass("readonly");
	}
	}
	}
}
/*function editDateAfterPublish(event){
	event.preventDefault();
	$("#TAHDRDates").removeClass("readonly");
	$("#tenderDatesForm").removeClass("readonly");
	$('#tenderDatesForm').find('input').removeAttr('tabindex','-1');
	$('#TAHDRDates .saveTahdrDatesBtn').show();
	$('#TAHDRDates .cancel').show();
}*/

function loadDatesFromTahdrDetail(data){
	debugger;
	if(!$.isEmptyObject(data))
		{
		if(data[0].tahdr.tahdrTypeCode=='WT'){
			if(data[0].isPBDSetLater=='Y' && data[0].isPBDSetLater!=null){
				$('.setPBDLater').attr('disabled','disabled');
				$('.setPBDLater').hide();
			}
			else{
				$('.setPBDLater').show();
				$('.setPBDLater').removeAttr('disabled');
			}
			
			if(data[0].isWinnerSelectionDateSetLater=='Y' && data[0].isWinnerSelectionDateSetLater!=null){
				$('.winnerLater').attr('disabled','disabled');
				$('.winnerLater').hide();
			}
			else{
				$('.winnerLater').show();
				$('.winnerLater').removeAttr('disabled');
			}
		}
		else if(data[0].tahdr.tahdrTypeCode=='PT' && data[0].tahdr.isAuction=='N')
			{
			if(data[0].isPBDSetLater=='Y' && data[0].isPBDSetLater!=null){
				$('.setPBDLater').attr('disabled','disabled');
				$('.setPBDLater').hide();
			}
			else{
				$('.setPBDLater').show();
				$('.setPBDLater').removeAttr('disabled');
			}
			if(data[0].isWinnerSelectionDateSetLater=='Y' && data[0].isWinnerSelectionDateSetLater!=null){
				$('.winnerLater').attr('disabled','disabled');
				$('.winnerLater').hide();
			}
			else{
				$('.winnerLater').show();
				$('.winnerLater').removeAttr('disabled');
			}

			if(data[0].isAnnexureC1=='Y' && data[0].isAnnexureC1!=null){
				if(data[0].isSetC1Later=='Y' && data[0].isSetC1Later!=null){
					$('.setC1Later').attr('disabled','disabled');
					$('.setC1Later').hide();
				}
				else{
					$('.setC1Later').show();
					$('.setC1Later').removeAttr('disabled');
				}
			}
			else{
				$('.setC1Later').attr('disabled','disabled');
				$('.setC1Later').hide();
			}
			}
		else if((data[0].tahdr.tahdrTypeCode=='PT' && data[0].tahdr.isAuction=='Y') || data[0].tahdr.tahdrTypeCode=='FA')
			{
			if(data[0].isPBDSetLater=='Y' && data[0].isPBDSetLater!=null){
				$('.setPBDLater').attr('disabled','disabled');
				$('.setPBDLater').hide();
			}
			else{
				$('.setPBDLater').show();
				$('.setPBDLater').removeAttr('disabled');
			}

			if(data[0].isAuctionDateSetLater=='Y' && data[0].isAuctionDateSetLater!=null){
				$('.setAuctionLater').attr('disabled','disabled');
				$('.setAuctionLater').hide();
			}
			else{
				$('.setAuctionLater').show();
				$('.setAuctionLater').removeAttr('disabled');
			}

			if(data[0].isWinnerSelectionDateSetLater=='Y' && data[0].isWinnerSelectionDateSetLater!=null){
				$('.winnerLater').attr('disabled','disabled');
				$('.winnerLater').hide();
			}
			else{
				$('.winnerLater').show();
				$('.winnerLater').removeAttr('disabled');
			}

			if(data[0].isAnnexureC1=='Y' && data[0].isAnnexureC1!=null){
				if(data[0].isSetC1Later=='Y' && data[0].isSetC1Later!=null){
					$('.setC1Later').attr('disabled','disabled');
					$('.setC1Later').hide();
				}
				else{
					$('.setC1Later').show();
					$('.setC1Later').removeAttr('disabled');
				}
			}else{
				$('.setC1Later').attr('disabled','disabled');
				$('.setC1Later').hide();
			}
			
			}
		
		}	
}
