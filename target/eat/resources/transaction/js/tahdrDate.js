$(document).ready(function() {

	$("#impDatesTab").on('click',function(event){
		event.preventDefault();
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
	var purchaseStartdate = $('#purchaseFromDate').val()+' 00:00';
	setDatePickerStartWithId("purchaseDate",purchaseToDate);
	setDateTimePickerStartWithId("preBidDates",purchaseStartdate);
	setDateTimePickerStartWithId("technicalBidFromDates",purchaseStartdate);
	setDateTimePickerStartWithId("technicalBidToDate",purchaseStartdate);
	setDateTimePickerStartWithId("technicalBidOpeningDates",purchaseStartdate);
	setDateTimePickerStartWithId("priceBidOpeningDates",purchaseStartdate);
	setDateTimePickerStartWithId("AnnexC1OpeningDates",purchaseStartdate);
	setDateTimePickerStartWithId("C1AnnexDates",purchaseStartdate);
	setDateTimePickerStartWithId("auctionStartDates",purchaseStartdate);
	setDateTimePickerStartWithId("auctionEndDates",purchaseStartdate);
	setDateTimePickerStartWithId("winnerDate",purchaseStartdate);
});

$("#purchaseToDate").on('change',function(){
	var purchaseEnddate = $('#purchaseToDate').val()+' 00:00';
	setDateTimePickerStartWithId("technicalBidToDate",purchaseEnddate);
	setDateTimePickerStartWithId("technicalBidOpeningDates",purchaseEnddate);
	setDateTimePickerStartWithId("priceBidOpeningDates",purchaseEnddate);
	setDateTimePickerStartWithId("AnnexC1OpeningDates",purchaseEnddate);
	setDateTimePickerStartWithId("C1AnnexDates",purchaseEnddate);
	setDateTimePickerStartWithId("winnerDate",purchaseEnddate);
});

/*$("#technicalBidDates").on('change',function(){
	var purchaseEnddate = $('#purchaseToDate').val()+' 00:00';
	var technicalBidDates = $('#technicalBidDates').val();
	setDateTimePickerStartEndWithId("priceBidOpeningDates",purchaseEnddate,technicalBidDates);
});*/

/*$("#preBidDate").on('change',function(){
	var preBidToDate = $('#preBidDate').val();
	setDateTimePickerStartWithId("technicalBidEndDates",preBidToDate);
});*/

$("#technicalBidFromDate").on('change',function(){
	debugger;
	var techincalToDate = $('#technicalBidFromDate').val();
	setDateTimePickerStartWithId("technicalBidDates",techincalToDate);
});

$("#technicalBidToDate").on('change',function(){
	debugger;
	var techincalBidOpenDate = $('#technicalBidToDate').val();
	var purchaseFromDate = $('#purchaseFromDate').val()+' 00:00';
	setDateTimePickerStartEndWithId("preBidDates",purchaseFromDate,techincalBidOpenDate);
	setDateTimePickerStartWithId("technicalBidOpeningDates",techincalBidOpenDate);
});

$("#techBidOpenningDate").on('change',function(){
	debugger;
	var priceBidOpenDate = $('#techBidOpenningDate').val();
	setDateTimePickerStartWithId("priceBidOpeningDates",priceBidOpenDate);
	setDateTimePickerStartWithId("C1AnnexDates",priceBidOpenDate);
	setDateTimePickerStartWithId("auctionStartDates",priceBidOpenDate);
	setDateTimePickerStartWithId("winnerDate",priceBidOpenDate);
});
	
$("#priceBidOpenningDate").on('change',function(){
	debugger;
	var c1OpenningDate = $('#priceBidOpenningDate').val();
	setDateTimePickerStartWithId("auctionStartDates",c1OpenningDate);
	setDateTimePickerStartWithId("C1AnnexDates",c1OpenningDate);
	setDateTimePickerStartWithId("winnerDate",c1OpenningDate);
});

$("#auctionStartToDates").on('change',function(){
	debugger;
	var auctionToDate = $('#auctionStartToDates').val();
	setDateTimePickerStartWithId("auctionEndDates",auctionToDate);
});

$("#auctionEndToDates").on('change',function(){
	debugger;
	var auctionENDDate = $('#auctionEndToDates').val();
	setDateTimePickerStartWithId("priceBidOpeningDates",auctionENDDate);
	setDateTimePickerStartWithId("C1AnnexDates",auctionENDDate);
	setDateTimePickerStartWithId("AnnexC1OpeningDates",auctionENDDate);
	setDateTimePickerStartWithId("winnerDate",auctionENDDate);
});

$("#c1ToDate").on('change',function(){
	debugger;
	var c1ToDate = $('#c1ToDate').val();
	setDateTimePickerStartWithId("AnnexC1OpeningDates",c1ToDate);
	setDateTimePickerStartWithId("winnerDate",c1ToDate);
});

$("#c1OpenningDate").on('change',function(){
	debugger;
	var c1ToDate = $('#c1OpenningDate').val();
	setDateTimePickerStartWithId("C1AnnexDates",c1ToDate);
	setDateTimePickerStartWithId("winnerDate",c1ToDate);
});

$("#publishDate").on('change',function(){
	debugger;
	var publishDate = $('#publishingDate').val();
	setDatePickerStartWithId("purchaseFromDate",publishDate);
});

});

function onChangeDate(className){
	debugger;
	$('.'+className).removeAttr('disabled','disabled');
	
}

function onChangeDateId(idName){
	debugger;
	$('#'+idName).removeAttr('disabled','disabled');
	
}

/* single date validation*/
function onChangeDateValidation(id,el){
	debugger;
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
	debugger;
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
	debugger;
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
		$('.condisn').removeClass("readonly");
		$('#purchaseFromDate').removeClass("readonly");
		$('#technicalBidFromDate').removeClass("readonly");
		loadTahdrDateToLeftPane(impDatesList.objectMap.tahdrDate);
		loadDatesFromTahdrDetail(impDatesList.objectMap.tahdrDate);
		$("#editedDates").val('N');
		if(tenderStatus == 'Published'){
			$("#TAHDRDates .editTahdrDate").css("display","block");
		}
		else{
			$("#TAHDRDates .editTahdrDate").css("display","none");
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
}

function loadTahdrDateToLeftPane(tahdrDate) {
	debugger;
		$("#leftPane").html("");
		var leftPanelHtml = '';
		var i = 0;
		var active = "";
		var firstRow = null;
		/*tahdrDetailArray=[];*/
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
	debugger;
	event.preventDefault();
	$("#TAHDRDates").removeClass("readonly");
	$("#tenderDatesForm").removeClass("readonly");
	$("#editedDates").val('Y');
	$("#datesSaved").val("N");
	$('#TAHDRDates .saveTahdrDatesBtn').show();
	$('#TAHDRDates .cancel').show();
	var d = new Date();
	var CurrentDate = d.getDate() + "-" + (d.getMonth()+1) + "-" + d.getFullYear();
	var CurrentDateTime= d.getDate() + "-" + (d.getMonth()+1) + "-" + d.getFullYear() + " " + d.getHours() +":"+ d.getMinutes();
	var saleStartDate = $('#purchaseFromDate').val();
	var bidStartDate = $('#technicalBidFromDate').val();
	if(CurrentDate > saleStartDate){
		$('.condisn').addClass("readonly");
		$('#purchaseFromDate').addClass("readonly");
	}
	if(CurrentDateTime > bidStartDate ){
		$('.condisn').addClass("readonly");
		$('#technicalBidFromDate').addClass("readonly");
	}
}

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
		else if((data[0].tahdr.tahdrTypeCode=='PT' && data[0].tahdr.isAuction=='Y') || data[0].tahdr.tahdrTypeCode=='FA' || data[0].tahdr.tahdrTypeCode=='RA')
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
