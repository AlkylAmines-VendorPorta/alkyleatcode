var tahdrArray = new Array();
var awardWinner='';
$(document).ready(function() {
			$(".tenderDet").on('click',function(event) {
						$('.pagination').children().remove();
						event.preventDefault();
						
						/*type=$(".typeOfAward").val();
						var url='';
						if(type=='winnerSelection'){
							url="tahdrAwardWinnerData/"+$("input[name='tenderTypeCodeToggle']:checked").val()+"/N";	
						}
						else if(type=='rating'){
							url="tahdrAwardWinnerData/"+$("input[name='tenderTypeCodeToggle']:checked").val()+"/Y";
						}
						
						var tahdrData=fetchData();
						var tahdrData=fetchList(url,null);						
						
						var tahdrData=fetchList("tahdrAwardWinnerData", $("input[name='tenderTypeCodeToggle']:checked").val());
						loadTahdrToLeftPane(tahdrData.objectMap.listTahdr);
						$('#leftPane').paginathing({perPage: 6});*/
						loadTahdrListValues();
					
					});

		});


function appendTahdrLi(tahdr, active) {
	return appendLiData(tahdr.tahdrCode, tahdr.title,tahdrStatusList[tahdr.tahdrStatusCode],tahdr.tahdrCode, tahdr.tahdrId, active, 'tahdr');
}

function loadTahdrToLeftPane(tahdrList) {
	$("#leftPane").html("");
	var leftPanelHtml = '';
	var i = 0;
	var active = "";
	var firstRow = null;
	tahdrArray = [];
	$.each(Object.values(tahdrList), function(key, tahdr) {
		
		tahdrArray["tahdr" + tahdr.tahdrId] = tahdr;
		if (i == 0) {
			firstRow = tahdr;
			active = "active";
		}
		leftPanelHtml = leftPanelHtml + appendTahdrLi(tahdr, active);
		active = "";
		i++;
	});
	$("#leftPane").append(leftPanelHtml);
	setLeftPaneHeader("Tender List", Object.values(tahdrArray).length);
	loadTahdrToRightPane(firstRow);
	$("#leftPane").on('click', '.tahdr', function() {
		var id = $(this).attr('id');
		loadTahdrToRightPane(tahdrArray["tahdr" + id]);
	});
	$(".tabs-left li a label").text(function(index, currentText) {
	    return currentText.substr(0, 20);
	});
}

function loadTahdrToRightPane(tahdr){
	if(!$.isEmptyObject(tahdr)){
		var tahdrDetail=tahdr.tahdrDetail[0];
		var tahdrId=tahdr.tahdrId;
		var tahdrDetailId=tahdrDetail==null?null:tahdrDetail.tahdrDetailId;
		var tahdrCode=tahdr.tahdrCode;
		var bidderLength=tahdr.bidders==null?0:tahdr.bidders.length;
		var emdFee=tahdrDetail==null?'':tahdrDetail.emdFee;
		var purchaseFromDate=tahdrDetail==null?'':tahdrDetail.purchaseFromDate;
		var technicalBidToDate=tahdrDetail==null?'':tahdrDetail.technicalBidToDate;
		var techBidOpenningDate=tahdrDetail==null?'':tahdrDetail.techBidOpenningDate;
		var priceBidOpenningDate=tahdrDetail==null?'':tahdrDetail.priceBidOpenningDate;
		var c1OpenningDate=tahdrDetail==null?'':tahdrDetail.priceBidOpenningDate;
		$(".tahdrId").val(tahdrId);
		$(".tahdrDetail").val(tahdrDetailId);
		$("#tahdrForm #tahdrCode").val(tahdrCode);
		$("#tahdrForm #totalBidderCount").val(bidderLength);
		/*$("#tahdrForm #awardWinnerCount").val(awardWinner);*/
		awardWinnerCount();
		$("#tahdrForm #EMD").val(emdFee);
		$("#tahdrForm #tahdrStatus").val(getValue(tahdrStatusList[tahdr.tahdrStatusCode]));
		$("#tahdrForm #saleStartDate").val(formatDate(purchaseFromDate));
		$("#tahdrForm #technicalBidToDate").val(formatDateTime(technicalBidToDate));
		$("#tahdrForm #techBidOpenningDate").val(formatDateTime(techBidOpenningDate));
		$("#tahdrForm #priceBidOpenningDate").val(formatDateTime(priceBidOpenningDate));
		$("#tahdrForm #c1OpenningDate").val(formatDateTime(c1OpenningDate));
		var documentType=$(".documentType").val();
		
		setHeaderValues(" No.: " +tahdrCode ,"Total No. Of Bidder : "+bidderLength, "Award Winner Count : "+awardWinner, "");
		$('#winnerItem').removeClass('readonly');
		$('#winnerConfirmationTabId').removeClass('readonly');
	}else{
		$('#winnerItem').addClass('readonly');
		$('#winnerConfirmationTabId').addClass('readonly');
		setHeaderValues(" " ," ", "", " ");
	}
}
	
function awardWinnerCount(){
	var tahdrId=$("#tenderId").val();
	if(tahdrId!=undefined && tahdrId!=''){
		submitToURL("getAwardWinnerCount/"+tahdrId,"awardWinnerCountResponse");	
	}
}

function awardWinnerCountResponse(data){
	if(!isEmpty(data) && !isEmpty(data.objectMap)){
		$("#tahdrForm #awardWinnerCount").val(data.objectMap.biddercount);
		awardWinner=data.objectMap.biddercount;
	}
}