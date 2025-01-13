var tahdrArray = new Array();

$(document).ready(function() {
			
			$(".tenderDet").on('click',function(event) {
						debugger;
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

/*function loadAllTabs(tahdrList){
		if(!$.isEmptyObject(tahdrList))
			{
			loadTahdrToLeftPane(tahdrList);
			 $('#tahdrDetailTab').removeClass('readonly');
			}
		else
			{
			$('#tahdrDetailTab').addClass('readonly');
			$('#impDatesTab').addClass('readonly');
			$('#tenderDocTab').addClass('readonly');
			$('#getTahdrMaterialList').addClass('readonly');
			$('#sectionDocTab').addClass('readonly');
			$('#confirmation').addClass('readonly');
			$(".leftPaneData").html("");
			};

}
*/

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
	setLeftPaneHeader(" List", Object.values(tahdrArray).length);
	loadTahdrToRightPane(firstRow);
	$("#leftPane").on('click', '.tahdr', function() {
		var id = $(this).attr('id');
		loadTahdrToRightPane(tahdrArray["tahdr" + id]);
	});
	//$('#leftPane').paginathing({perPage: 6});
	$(".tabs-left li a label").text(function(index, currentText) {
	    return currentText.substr(0, 20);
	});
}

function loadTahdrToRightPane(tahdr){
	if(!$.isEmptyObject(tahdr)){
		$(".tahdrId").val(tahdr.tahdrId);
		$(".tahdrDetail").val(getValue(tahdr.tahdrDetail[0].tahdrDetailId));
		$("#tahdrForm #tahdrCode").val(getValue(tahdr.tahdrCode));
		$("#tahdrForm #totalBidderCount").val(tahdr.bidders.length);
		$("#tahdrForm #awardWinnerCount").val(awardWinner);
		$("#tahdrForm #EMD").val(getValue(tahdr.tahdrDetail[0].emdFee));
		$("#tahdrForm #tahdrStatus").val(getValue(tahdrStatusList[tahdr.tahdrStatusCode]));
		if(tahdr.tahdrStatusCode=='QAWCO'){
			$('.savebtn').hide();
			$("#submitRespLabelId").html('Winner Already Selected');
		}else{
			$('.savebtn').show();
			$("#submitRespLabelId").html('');
			$('#confirmationTabMessage').hide('Winner Already Selected');
		}
		$("#tahdrForm #saleStartDate").val(formatDate(tahdr.tahdrDetail[0].purchaseFromDate));
		$("#tahdrForm #technicalBidToDate").val(formatDateTime(tahdr.tahdrDetail[0].technicalBidToDate));
		$("#tahdrForm #techBidOpenningDate").val(formatDateTime(tahdr.tahdrDetail[0].techBidOpenningDate));
		$("#tahdrForm #priceBidOpenningDate").val(formatDateTime(tahdr.tahdrDetail[0].priceBidOpenningDate));
		$("#tahdrForm #c1OpenningDate").val(formatDateTime(tahdr.tahdrDetail[0].c1OpenningDate));
		var documentType=$(".documentType").val();
		/*setHeaderValues(documentType+"No.: " + tahdrCodes, documentType+"Title : " + title, "Department : " + department, "Status : " + status);*/
		setHeaderValues(documentType+" No.: " +tahdr.tahdrCode ,"Total No. Of Bidder : "+tahdr.bidders.length, "Award Winner Count : "+awardWinner, "EMD : "+tahdr.tahdrDetail[0].emdFee);
		
		
		//setHeaderValues("Tender No.: "+tahdr.tahdrCode, "Total No. Of Bidder : "+tahdr.bidders.length, "Award Winner Count : "+awardWinner, "EMD : "+tahdr.tahdrDetail[0].emdFee);
			}
}
	
function submitTahdrResp(data){
	if (data.success) {
		$("#submitRespLabelId").html(data.message);
	} else {
		$("#submitRespLabelId").html(data.message);
	}
}