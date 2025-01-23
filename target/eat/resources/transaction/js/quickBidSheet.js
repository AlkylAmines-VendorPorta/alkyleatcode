var statusList=new Array();
var tahdrStatus='';
var leftPanePageSize = 7;
var maxVisiblePageNumbers = 3;
var selectedPage = 1;
var pageSize=7;
var searchMode='none';
var searchValue='none';
var LastPage='';
var autoRefreshFlag=false;
var isAuctionExtended='';
$(document).ready(
		function() {
			var lengthMenu;
		    if ($(window).width() < 480) {
		        $('.mobileNav').show();
		        $.fn.DataTable.ext.pager.numbers_length = 4;       
		        lengthMenu = [ 1, 5, 7, 10, ],
		        [ 1, 5, 7, 10, ]
		    } else {        
		        lengthMenu = [ 7, 10, ],
		        [ 7, 10, ]
		    }			
			$('.listolivefbider').DataTable({
				/*
				 * "scrollY": scrY, "scrollX": scrX,
				 */
				/* "pageLength": 4, */
				"lengthMenu":lengthMenu,
				"scrollX": true,
				"bSort":false
				
			});
			

			$("input[name='tenderTypeCodeToggle']").on("change",function() {
				selectedPage=1;
				         onPageLoad();
				});
			$('.tenderTab').click(function(event) {
		        event.preventDefault();
		        cacheLi();
				setCurrentTab(this);
				$('.pagination').html('');
				if(getChangedFlag()){
					var typeCode = $('input[name=tenderTypeCodeToggle]:checked').val();
					var data=fetchTenderList(1, pageSize, searchMode, searchValue,typeCode);
					loadTenderList(data);
					LastPage=data.objectMap.LastPage;
					setPagination(data.objectMap.LastPage, selectedPage , maxVisiblePageNumbers);
					/*submitToURL("getTenderForLiveBid/" + typeCode,
							"loadTenderListForBidSheet");*/
					setChangedFlag(false);
				}else{
					getCacheLi();
					setPagination(LastPage, selectedPage , maxVisiblePageNumbers);
				}
		    });
			$('#endAuctionBtnId').click(function(event) {
				swal({
					  title: "Do You Want To extend auction and notify all bidder!",
					  text: ' This is only one time action ',
					  type: 'warning',
					  showCancelButton: true,
					  confirmButtonColor: '#3085d6',
					  cancelButtonColor: '#d33',
					  confirmButtonText: 'OK'
					})
					.then((result) => {
					  if (result.value) {
						  var tahdrId=$('#tahdrForm #tahdrId').val();
						  if(tahdrId!=""){
							  submitToURL("endAuction/" + tahdrId,
								"endAuctionResp");
						  }else{
							  Alert.warn("Something went wrong !");  
						  }  
						  
					  }
					});
		    });
			$('#pagination-here').paginate({
				pageSize:  7,
				dataSource: 'fetchTenderList',
				responseTo:  'loadTenderListPagination',
				maxVisiblePageNumbers:3,
				searchBoxID : 'searchLiteralId',
				loadOnStartup: false
			});
			if ($(window).width() < 768) {
				$('.dataTables_length').parent().addClass('col-xs-6');
			
			 $('.dataTables_filter').parent().addClass('col-xs-6');
			}
			 onPageLoad();
			 
			 
			 
});
var tenderArray = new Array();
var tahdrMaterialArray = new Array();
var rankArray = new Array();
var tahdrMaterialReqQty=0;

function onPageLoad(){
	$('.pagination').html('');
	var typeCode = $('input[name=tenderTypeCodeToggle]:checked').val();
	var data=fetchTenderList(1, pageSize, searchMode, searchValue,typeCode);
	loadTenderList(data);
	LastPage=data.objectMap.LastPage;
	setPagination(data.objectMap.LastPage, selectedPage , maxVisiblePageNumbers);
	/*submitToURL("getTenderForLiveBid/" + typeCode,
			"loadTenderListForBidSheet");*/
	setCurrentTab($("#tenderDetailTab")[0]);
    setChangedFlag(false);
}

function loadTenderListPagination(data){
	loadTenderListForBidSheet(data);
}

function loadTenderList(data){
	var data=data.data;
	loadTenderListForBidSheet(data);
}

function loadTenderListForBidSheet(data) {
	$('#auctionTimer').timer('remove');
	$('#autoRefresh').timer('remove');
	$(".leftPaneData").html("");
	var leftPanelHtml = "";
	var i = 0;
	var firstRow = null;
	var firstTahdrId = '';
	statusList=data.statusList;
	if (!$.isEmptyObject(data.tahdrList)) {
		$.each(data.tahdrList, function(key, value) {
				var tahdrCode = value.tahdrCode == null ? '' : value.tahdrCode;
				var tahdrId = value.tahdrId == null ? '' : value.tahdrId;
				var title = value.title == null ? '' : value.title;
				var emdFee = value.tahdrDetail[0].emdFee == null ? ''
						: value.tahdrDetail[0].emdFee;
				var minQuantity = value.tahdrDetail[0].minQuantity == null ? ''
						: value.tahdrDetail[0].minQuantity;
				var status = value.tahdrStatusCode == null ? '' : value.tahdrStatusCode;
				var tahdrStatus=statusList[status];
				tenderArray['Tender_' + tahdrId] = value;
				if (i == 0) {
					firstRow = value;
					firstTahdrId = tahdrId;
					leftPanelHtml = leftPanelHtml
							+ ' <li class="active" onclick="showTahdrDetail('
							+ tahdrId + ')" id="' + tahdrId + '">';
				} else {
					leftPanelHtml = leftPanelHtml
							+ ' <li onclick="showTahdrDetail(' + tahdrId
							+ ')" id="' + tahdrId + '">';
				}

				leftPanelHtml = leftPanelHtml
						+ ' <a href="#Master" data-toggle="tab">'
						+ ' <div class="col-md-12">'
						+ '  <label class="col-xs-6" id="' + tahdrId+'_tenderNo">' + tahdrCode + '</label>'
						+ '	<label class="col-xs-6" id="' + tahdrId + '_title">'+ title + '</label>' + ' </div>'
						+ ' <div class="col-md-12">'
						+ '	<label class="col-xs-6" id="' + tahdrId+ '_tahdrValidity">' + tahdrStatus + '</label>'
						+ '	<label class="col-xs-6" id="' + tahdrId + '_emdFee">'+ emdFee + '</label>' + ' </div>' + ' </a>' + ' </li>';
				i++;
		});
		$(".leftPaneData").html(leftPanelHtml);
		loadTahdrRightPane(firstRow);
		$('.tenderMaterialTab').removeClass('readonly');
		
	} else {
		loadTahdrRightPane(firstRow);
		$('.tenderMaterialTab').addClass('readonly');
		Alert.warn(data.resultMessage);
	}

	/*$('.leftPaneData').paginathing({
		perPage : 6
	});*/
	$(".tabs-left li a label").text(function(index, currentText) {
		return currentText.substr(0, 20);
	});

	$(".tabs-left li a label").text(function(index, currentText) {
		return currentText.substr(0, 20);
	});
	setLeftPaneHeader("List", $("#leftPaneData li").length);
}

function loadTahdrRightPane(data) {
	if (!$.isEmptyObject(data)) {
		var tenderCode = data.tahdrCode == null ? '' : data.tahdrCode;
		var tenderNo = data.title == null ? '' : data.title;
		var contactEmail = data.tahdrDetail[0].contactEmailId == null ? ''
				: data.tahdrDetail[0].contactEmailId;
		var description = data.tahdrDetail[0].description == null ? ''
				: data.tahdrDetail[0].description;

		var version = data.tahdrDetail[0].version == null ? ''
				: data.tahdrDetail[0].version;
		var emd = data.tahdrDetail[0].emdFee == null ? ''
				: data.tahdrDetail[0].emdFee;
		var tahdrId = data.tahdrId == null ? '' : data.tahdrId;
		isAuctionExtended=data.isAuctionExtended==null?'':data.isAuctionExtended;
		
		
		var status = data.tahdrStatusCode == null ? '' : data.tahdrStatusCode;
		tahdrStatus=status;
		var tenderStatus=statusList[status];
		$('#tahdrForm #tenderNo').html(tenderCode);
		$('#tahdrForm #tenderVersion').html(version);
		$('#tahdrForm #description').html(description);
		$('#tahdrForm #emdFee').html(emd);
		$('#tahdrForm #tahdrId').val(tahdrId);
		$('#tahdrForm #status').html(tenderStatus);

		setHeaderValues("No.: " + tenderCode, " Title : "
				+ tenderNo, "Contact EmailId : " + contactEmail,
				"Description : " + description);
		tabVisibility(status);
	} else {
		$('#tahdrForm #tenderNo').html('');
		$('#tahdrForm #tenderVersion').html('');
		$('#tahdrForm #description').html('');
		$('#tahdrForm #emdFee').html('');
		$('#tahdrForm #tahdrId').val('');
		$('#tahdrForm #status').html('');
		setHeaderValues(" No.: ", " Title : ",
				"Contact EmailId : ", "Description : ");
		tabVisibility(null);
	}
	setAuctionExtention(isAuctionExtended);
	setChildLoadFlag(true);
}

function tabVisibility(status){
	if(status=="PU"){
	$('.bidSheetTabDiv').show();
	}else{
		$('.bidSheetTabDiv').hide();	
	}
}

function showTahdrDetail(tahdrId) {
	loadTahdrRightPane(tenderArray['Tender_' + tahdrId]);
}

function loadTahdrMaterial(ele){
	cacheLi();
	setCurrentTab(ele);
	submitWithParam('getBidSheetAuctionMaterialListById','tahdrId','loadTahdrMaterialListForView');
}

function loadTahdrMaterialListForView(data) {
	$('#auctionTimer').timer('remove');
	$('#autoRefresh').timer('remove');
	$('.pagination').children().remove();
	$("#leftPane").html("");
	var leftPanelHtml = "";
	var i = 0;
	var firstRow = null;
	if (!$.isEmptyObject(data.objectMap.responseList)) {
		$.each(data.objectMap.responseList, function(key, value) {
			var tahdrMaterialId = value.tahdrMaterialId == null ? ''
					: value.tahdrMaterialId;
			var materialId = value.material == null ? ''
					: value.material.materialId;
			var name = value.material == null ? ''
					: value.material.name == null ? '' : value.material.name;
			var uomName = value.material == null ? ''
					: value.material.uom == null ? ''
							: value.material.uom.name == null ? ''
									: value.material.uom.name;
			var description = value.material == null ? ''
					: value.material.description == null ? ''
							: value.material.description;
			var hsnCode = value.material == null ? ''
					: value.material.hsnCode == null ? ''
							: value.material.hsnCode.code == null ? ''
									: value.material.hsnCode.code;

			tahdrMaterialArray["tahdrMaterial_" + tahdrMaterialId] = value;
			if (i == 0) {
				firstRow = value;

				leftPanelHtml = leftPanelHtml + ' <li class="active" id="'
						+ tahdrMaterialId
						+ '"  onclick="showTahdrMaterialDetail(this)">';
			} else {
				leftPanelHtml = leftPanelHtml + ' <li class="itemBidLi" id="'
						+ tahdrMaterialId
						+ '"  onclick="showTahdrMaterialDetail(this)">';
			}

			leftPanelHtml = leftPanelHtml
					+ ' <a href="#Master" data-toggle="tab">'
					+ ' <div class="col-md-12">'
					+ '  <label class="col-xs-6" id="' + tahdrMaterialId
					+ '_name">' + name + '</label>'
					+ '	<label class="col-xs-6" id="' + tahdrMaterialId
					+ '_uomName">' + uomName + '</label>' + ' </div>'
					+ ' <div class="col-md-12">'
					+ '	<label class="col-xs-6" id="' + tahdrMaterialId
					+ '_description">' + description + '</label>'
					+ '	<label class="col-xs-6" id="' + tahdrMaterialId
					+ '_hcnCode">' + hsnCode + '</label>' + ' </div>' + ' </a>'
					+ ' </li>';
			i++;
		});
		$(".leftPaneData").html(leftPanelHtml);
		loadTahdrMaterialRightPane(firstRow);
		
	}else{
		$(".leftPaneData").html('');
		loadTahdrMaterialRightPane(null);
		$('.statisticTab').addClass('readonly');
	}
	
	$('.leftPaneData').paginathing({
		perPage : 6
	});
	setLeftPaneHeader("Material List", $("#leftPaneData li").length);
}
function loadTahdrMaterialRightPane(data) {
	if (!$.isEmptyObject(data)) {
		var tahdrMaterialId = data.tahdrMaterialId == null ? ''
				: data.tahdrMaterialId;
		var MaterialId = data.material == null ? ''
				: data.material.materialId;
		var name = data.material.materialId == null ? '' : data.material.name;
		var code = data.material.materialId == null ? '' : data.material.code;
		var uomName = data.material.uom.name == null ? ''
				: data.material.uom.name;
		var description = data.material.description == null ? ''
				: data.material.description;
		var hsnCode = data.material.code == null ? '' : data.material.code;
		var reqQuantity = data.quantity == null ? '' : data.quantity;
		var specVersion = data.materialVersion == null ? ''
				: data.materialVersion.name;
		var tenderMatType = data.materialTypeCode == null ? ''
				: data.materialTypeCode;
	    tahdrMaterialReqQty=reqQuantity;
		$("#itemDetailForm #tahdrMaterialId").val(tahdrMaterialId);
		$("#itemDetailForm #MaterialIdForChart").val(MaterialId);
		$("#itemDetailForm #matName").html(name);
		$("#itemDetailForm #matCode").html(code);
		$("#itemDetailForm #uom").html(uomName);
		$("#itemDetailForm #description").html(description);

		$("#itemDetailForm #reqQuantity").html(reqQuantity);
		$("#itemDetailForm #specVersion").html(specVersion);
		$("#itemDetailForm #tenderMatType").html(tenderMatType);
		$('.statisticTab').removeClass('readonly');
	}
}

function showTahdrMaterialDetail(el) {
	var tahdrMaterialId = $(el).attr('id');
	loadTahdrMaterialRightPane(tahdrMaterialArray['tahdrMaterial_'
			+ tahdrMaterialId]);
}

function loadBiddersListForLiveBid(data) {
	var count=0;
	var i=0;
	var leftqty=0;
	var lengthMenu;
	var remainingTime='0s';
	
	$(".listolivefbider").DataTable().destroy();
	$('.listolivefbider tbody').empty();
	
    if ($(window).width() < 480) {
        $('.mobileNav').show();
        $.fn.DataTable.ext.pager.numbers_length = 4;       
        lengthMenu = [ 1, 5, 7, 10, ],
        [ 1, 5, 7, 10, ]
    } else {        
        lengthMenu = [ 7, 10, ],
        [ 7, 10, ]
    }
	$('.leftPaneData li').each(function() {
		$(this).attr('onclick','showBidderList(this)');
	});
	if (!$.isEmptyObject(data.objectMap.rank)) {
		rankArray=data.objectMap.rank;
	}
	if (!$.isEmptyObject(data.objectMap.remainingTime)) {
		remainingTime=data.objectMap.remainingTime;
	}
	if (!$.isEmptyObject(data.objectMap.bidderList)) {
		$.each(data.objectMap.bidderList, function(key, value) {
			var bidderData=value;
			var partnername=bidderData.partner==null?'':bidderData.partner.name;
			var partnerId=bidderData.partner==null?'':bidderData.partner.bPartnerId;
		/*	var factoryName=bidderData.itemBid==null?'':bidderData.itemBid.bidder==null?'':bidderData.itemBid.bidder.factory==null?'':bidderData.itemBid.bidder.factory.name;*/
			var offeredQuantity=bidderData.offeredQuantity==null?'':bidderData.offeredQuantity;
			var exGroupPriceRate=bidderData.exGroupPriceRate==null?'':bidderData.exGroupPriceRate;
			/*var fddAmount=bidderData.fddAmount==null?'':bidderData.fddAmount;
			var ticRate=bidderData.ticRate==null?'':bidderData.ticRate;
			var freightChargeRate=bidderData.freightChargeRate==null?'':bidderData.freightChargeRate;
			var totalExGroupPrice=bidderData.totalExGroupPrice==null?'':bidderData.totalExGroupPrice;
			var totalFreightCharge=bidderData.totalFreightCharge==null?'':bidderData.totalFreightCharge;
			var totalTic=bidderData.totalTic==null?'':bidderData.totalTic;
			var fddRate=bidderData.fddRate==null?'':bidderData.fddRate;
			var totalTax=bidderData.totalTax==null?'':bidderData.totalTax;
			var fddRateWithGST=bidderData.fddRateWithGST==null?'':bidderData.fddRateWithGST;
			var fddAmountWithGST=bidderData.fddAmountWithGST==null?'':bidderData.fddAmountWithGST;*/
			
			var getRank=rankArray['rankMap_'+partnerId]==undefined?'#':rankArray['rankMap_'+partnerId];
			var rank=getRank;
			count=count+Number(offeredQuantity);
			if(tahdrMaterialReqQty>=count){
						$('.listolivefbider tbody').append('<tr class="greenrow"><td>'+partnername+'</td>' + 
								'<td>'+rank+'</td>' + 
								'<td>'+offeredQuantity+'</td>' + 
								'<td>'+indianRupeesFormat(exGroupPriceRate)+'</td>' + 
								'</tr>');
				leftqty=tahdrMaterialReqQty-count;
			}else{
				if(i==0 && leftqty>0){
					$('.listolivefbider tbody').append('<tr class="greenrow"><td>'+partnername+'</td>' + 
							'<td>'+rank+'</td>' + 
							'<td>'+offeredQuantity+'</td>' + 
							'<td>'+indianRupeesFormat(exGroupPriceRate)+'</td>' + 
							'</tr>');
				}else{
					$('.listolivefbider tbody').append('<tr><td>'+partnername+'</td>' + 
							'<td>'+rank+'</td>' + 
							'<td>'+offeredQuantity+'</td>' + 
							'<td>'+indianRupeesFormat(exGroupPriceRate)+'</td>' + 
							'</tr>');
				}
				i++;
			}
		});
		$('.auctionSpecificBtn').removeAttr('disabled');
	} else {
		$('#auctionTimer').timer('remove');
		$('#autoRefresh').timer('remove');
		$('.listolivefbider tbody').empty();
		$('.auctionSpecificBtn').attr('disabled','disabled');
		Alert.warn(data.objectMap.resultMessage);
	}
	if(remainingTime!='0s'){
		if(!autoRefreshFlag){
			autoRefresh();
		}
		setTimer(remainingTime,true);
	}else{
		$('#autoRefresh').timer('remove');
		$('#auctionTimer').html('Times Up !');
		$('.auctionSpecificBtn').attr('disabled','disabled');
	}
	$('.listolivefbider').DataTable({
		/*"scrollX": true,*/
		"bSort":false,
		"lengthMenu":lengthMenu
	});
	
	$('table').each(
			function() {
				var text = []
				$(this).find('thead tr th').each(
						function() {
							text.push($(this).text())

							for (var i = 0; i < text.length; i++) {
								$(this).parents('table').find(
										'tbody tr td:nth-of-type('
												+ (i + 1) + ')').attr(
										'data-th', text[i])
							}
						});
			});
	if ($(window).width() < 768) {
		$('.dataTables_length').parent().addClass('col-xs-6');
	
	 $('.dataTables_filter').parent().addClass('col-xs-6');
	}
}

function showBidderList(el){
	var tahdrMaterialId = $(el).attr('id');
	$("#itemDetailForm #tahdrMaterialId").val(tahdrMaterialId);
	var tahdrId = $('#tahdrForm #tahdrId').val();
	if(tahdrId!='' && tahdrMaterialId!=''){
		submitToURL('getBidSheetByTahdrMaterialId/'+tahdrId+'/'+tahdrMaterialId,'loadBiddersListForLiveBid');
	}else{
		Alert.warn('Something went wrong');
	}
}

function loadStatisticDataWithMaterial(ele){
	cacheLi();
	setCurrentTab(ele);
	submitWithTwoParam('getStatisticDetailsListByMaterial','tahdrId','itemDetailForm #tahdrMaterialId','bidderCount');
}

function loadStatisticDataWithTender(ele){
	cacheLi();
	setCurrentTab(ele);
	submitWithTwoParam('getStatisticDetailsListByTender','tahdrId','bidderCount');
}



function showStatisticDetails(el){
	    showLoader();
		var tahdrMaterialId = $(el).attr('id');
		$("#itemDetailForm #tahdrMaterialId").val(tahdrMaterialId);
		var tahdrId=$('#tahdrForm #tahdrId').val();
		submitToURL('getStatisticDetailsListByMaterial/'+tahdrId+'/'+tahdrMaterialId,'bidderCount');
		hideLoader();
}

function bidderCount(data){
	$('.leftPaneData li').each(function() {
		$(this).attr('onclick','showStatisticDetails(this)');
	});
	if(data.objectMap.result){
			$("#statisticForm #bidderPriceBidOpenedDiv").show();
			$("#statisticForm #bidderTechnicalCountDiv").hide();
			$("#statisticForm #bidderPriceCountDiv").hide();
			$("#statisticForm #bidderAllCountDiv").hide();
			var priceBidOpenedCount=0;
			var bidderCount=data.objectMap.bidderCount==null?0:data.objectMap.bidderCount.length;
			$("#statisticForm #bidderCount").html(bidderCount);
			$.each(data.objectMap.bidderCount, function(key, value) {
				if(value.status=='DR'){
					priceBidOpenedCount++;
				}
			});
			$("#statisticForm #bidderPriceBidOpened").html(priceBidOpenedCount);
	}else{
		Alert.warn(data.objectMap.resultMessage);
	}
}

function loadHistoryChart(data){
	$('#auctionTimer').timer('remove');
	$('#autoRefresh').timer('remove');
	var chart = new CanvasJS.Chart("chartContainer", {
		  data: [
		    {
		      type: "column",
		      dataPoints:data
		    }					
		  ]
		});

		chart.render();
}

function loadBidderParticipation(data){
	$('#auctionTimer').timer('remove');
	$('#autoRefresh').timer('remove');
	var chart = new CanvasJS.Chart("PiechartContainer", {
		  data: [
		    {
		      type: "pie",
		      dataPoints:data	
		      }				
		  ]
		});

		chart.render();
}

function fetchTenderList(pageNumber, pageSize, searchMode, searchValue){
	var typecode=$('input[name=tenderTypeCodeToggle]:checked').val();
	var response;
	$.ajax({
        type : "POST",
        contentType : "application/json",
        url: "fetchTenderListForBidSheet?pageNumber="+pageNumber+"&pageSize="+pageSize+'&searchMode='+searchMode+'&serachValue='+searchValue+'&typeCode='+typecode,
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

function autoRefresh(){
    autoRefreshFlag=true;
	$('#autoRefresh').timer({
		countdown: true,
	    duration: '5s',
	    callback: function() {
	    	$('#autoRefresh').timer('pause');
	    	submitWithTwoParam('getBidSheetByTahdrMaterialId','tahdrForm #tahdrId','itemDetailForm #tahdrMaterialId','loadBiddersListForLiveBid');
	    	$('#autoRefresh').timer('reset');
	    },
	   repeat: true
	});
}

function setTimer(remainingTime,isRemainingTimeCalculated){
	var remainingDuration='0s';
	if(isRemainingTimeCalculated){
		remainingDuration=remainingTime;
	}else{
		remainingDuration=formateServerDate(remainingTime,serverTime);
	}
	$('#auctionTimer').html('');
	$('#auctionTimer').timer('remove');
	$('#auctionTimer').timer({
	    countdown: true,
	    duration: remainingDuration,
	    callback: function() {
	    	$('#auctionTimer').html('Auction End !');
	    	$("#autoRefresh").timer('remove');
	    	var tahdrId=$('#tahdrForm #tahdrId').val();
	    	saveAuctionWinner();
	    	window.location.reload();
	    }
	});
}
function loadBidStatisticData(el){
	$('#auctionTimer').timer('remove');
	$('#autoRefresh').timer('remove');
	 submitWithTwoParam('getBidStatisticByTahdrId','tahdrId','itemDetailForm #tahdrMaterialId','bidStatisticsResp');
}

function bidStatisticsResp(data){
	var dataPoints=[];
	if(!isEmpty(data) && !isEmpty(data.objectMap)){
		dataPoint=dataPointsFromBidStatistic(data.objectMap.statisticData)
	}
	
	$(".chartContainer1").CanvasJSChart({ 
		title: { 
			text: "Bid Statistic", 
			fontSize: 30
		}, 
		axisY: { 
			title: "Bids in Lakhs",
			/*includeZero: false, 
			Prefix: "Rs" */
		}, 
		axisX: { 
			title: "Date/Time of Bid ",
			/*interval: 5, 
			intervalType: "minutes"*/
			
		}, 
		data: [ 
		{ 
			type: "spline",
			toolTipContent: "{name} ,{x}: Rs {y} ",
			dataPoints:dataPoint
				/*data.objectMap.grpahdto*/
			  
		} 
		] 
	});
	
}

function dataPointsFromBidStatistic(data){
	var dataPoints=[];
	$.each(data, function(key, value) {
		var bidStatisticDto={};
		bidStatisticDto.x=new Date(value[0]);
		bidStatisticDto.y=Number(value[1]);
		bidStatisticDto.name=value[2];
		dataPoints.push(bidStatisticDto);
	});
	return dataPoints;
}

function saveAuctionWinner(){
	var tahdrId=$('#tahdrForm #tahdrId').val();
	if(tahdrId!=''){
		 submitToURL("saveQuickAuctionWinner/" + tahdrId,'quickAuctionWinnerResp'); 
	 }
}

function quickAuctionWinnerResp(data){
	if(data.objectMap.Status){
		Alert.info(data.objectMap.Message);
		window.location.reload();
	}else{
		Alert.warn(data.objectMap.Message);
		window.location.reload();
	}
}

function endAuctionResp(data){
	if(data.objectMap.Status){
		Alert.info(data.objectMap.Message);
		setAuctionExtention("Y");
	}else{
		Alert.warn(data.objectMap.Message);
	}
}

function downloadBidSheetPdf(event){
	var tahdrId=$('#tahdrForm #tahdrId').val();
	var tahdrMaterialId=$("#itemDetailForm #tahdrMaterialId").val();
	event.preventDefault();
	if(tahdrId!='' && tahdrMaterialId!='' ){
		showLoader();
		directSubmit(event,"gennerateBidSheetDoc","generateBidSheetReport/"+tahdrId+"/"+tahdrMaterialId);
		hideLoader();	
	}else{
		ALert.warn('Something went wrong !');
	}
}

function downloadCummulativeBidSheetPdf(event){
	var tahdrId=$('#tahdrForm #tahdrId').val();
	var tahdrMaterialId=$("#itemDetailForm #tahdrMaterialId").val();
	event.preventDefault();
	if(tahdrId!='' && tahdrMaterialId!='' ){
		showLoader();
		directSubmit(event,"gennerateBidSheetDoc","generateCummulativeBidSheetReport/"+tahdrId+"/"+tahdrMaterialId);
		hideLoader();	
	}else{
		ALert.warn('Something went wrong !');
	}
}

function setAuctionExtention(isExtended){
	 if(isExtended=='Y' ){
		 $('#endAuctionBtnId').attr('disabled','disabled');
		 $('#endAuctionBtnId').html('Already Extended Once');
		 $('#endAuctionBtnId').data('status','false');
	 }else{
		 $('#endAuctionBtnId').removeAttr('disabled');
		 $('#endAuctionBtnId').html('End Auction');
		 $('#endAuctionBtnId').data('status','true');
	 }
}

function refresh(){
	$('#autoRefresh').timer('reset');
}