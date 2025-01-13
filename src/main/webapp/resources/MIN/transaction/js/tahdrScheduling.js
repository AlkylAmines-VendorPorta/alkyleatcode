var tenderScheduleArray= new Array();
var tenderArray= new Array();
var schedulingArray=new Array();

var tenderStatusList=new Array();
var tenderTypeCode='';

var leftPanePageSize = 7;
var maxVisiblePageNumbers = 3;
var selectedPage = 1;
var pageSize=7;
var searchMode='none';
var searchValue='none';

$(document).ready(function(){
	setDatePicker('+1d');
	$("#tenderSchedulingForm #closingDateTime").val(setCurrentDate());
	$("#tenderSchedulingForm #openingDateTime").val(setCurrentDate());
	var dataUrl=$('#myTenderUrl').val();
	if(dataUrl==null || dataUrl=='' || dataUrl ==undefined ){
		var typeCode=$("input[name='tenderTypeCodeToggle']:checked").val();
		var data=fetchTenderList(1, pageSize, searchMode, searchValue,typeCode);
		loadSchedulingTenderList(data);
		setPagination(data.objectMap.LastPage, selectedPage , maxVisiblePageNumbers);
		/*submitToURL("getTenderDetailsForScheduling/"+typeCode, "tahdrSchedulingResp");*/
	}else{
		$('#worksToggle').removeClass('active');
		$('#worksCheckBoxId').removeAttr('checked');
		$('#auctionsCheckBoxId').removeAttr('checked');
		submitToURL(dataUrl, "tahdrSchedulingResp");
		setToggle(tenderTypeCode);
	}
	
	 $(".tabs-left li a label").text(function(index, currentText) {
		    return currentText.substr(0, 20);
		});
	$("input[name='tenderTypeCodeToggle']").on("change",function(){
		selectedPage=1;
		$('.pagination').html('');
		var data=fetchTenderList(1, pageSize, searchMode, searchValue,$(this).val());
		loadSchedulingTenderList(data);
		setPagination(data.objectMap.LastPage, selectedPage , maxVisiblePageNumbers);
		/*submitToURL("getTenderDetailsForScheduling/"+$(this).val(), "tahdrSchedulingResp");*/
		/*$('.leftPaneData').paginathing({
			perPage: 6,
		});*/
		 $(".tabs-left li a label").text(function(index, currentText) {
			    return currentText.substr(0, 20);
			});
		
	});
		
	$("input[name='filterBy']").on('change',function(event){
		$('#search_concept').html('Filter By '+$(this).data('type'));
	});
	
	$("#tenderCode").on('change',function(event){
		$("#scheduleList").removeAttr("disabled","disabled");
	});
	
	$("#scheduleList").on('change',function(event){
		event.preventDefault();
		var optionName=$('#scheduleList').find('option:selected').val();
		loadDateTimeData(optionName);
	});
	$('#pagination-here').paginate({
		pageSize:  7,
		dataSource: 'fetchTenderList',
		responseTo:  'loadSchedulingTenderListFromPagination',
		maxVisiblePageNumbers:3,
		searchBoxID : 'searchLiteralId',
		loadOnStartup: false
	});
});
function setCurrentDate(longDate)
{
	var dt;
	if(null!=longDate && undefined!=longDate && ""!=longDate){
		dt=new Date(longDate);
	}
	else{
		dt=new Date();
	}
			var dd=dt.getDate()+1;
			var MM=dt.getMonth()+1;
			var yyyy=dt.getFullYear();
			var HH= dt.getHours();
			var mm= (dt.getMinutes()<10?'0':'') + dt.getMinutes();   /*var mm= dt.getMinutes();*/
			var ss= dt.getSeconds();
		
			return dd+'-'+MM+'-'+yyyy+' '+HH+':'+mm;
	}
function loadSchedulingTenderListFromPagination(data){
	if(data.hasOwnProperty('tenderStatusList')){
		tenderStatusList=data.tenderStatusList;
    }
	if(data.hasOwnProperty('scheduleList')){
		var typecode=$('input[name=tenderTypeCodeToggle]:checked').val();
		if(typecode=='FA' || typecode=='RA'){
			loadReferenceListById('scheduleList',data.scheduleList);
	    	}
	    	else if(typecode=='WT' || typecode=='PT'){
	    		loadSchedulingList('scheduleList',data.scheduleList);
	    	}
	    	
		$.each(data.scheduleList,function(key,value){
			schedulingArray['sch_'+key]=value;
		});
		
	}
	if(data.hasOwnProperty('tahdrList')){
		loadTenderListForScheduling(data.tahdrList);
    }
}

function loadSchedulingTenderList(data){
	debugger;
	var data=data.data;
	if(data.hasOwnProperty('tenderStatusList')){
		tenderStatusList=data.tenderStatusList;
    }
	if(data.hasOwnProperty('scheduleList')){
		/*loadReferenceListById('scheduleList',data.scheduleList);*/
		var typecode=$('input[name=tenderTypeCodeToggle]:checked').val();
		if(typecode=='FA' || typecode=='RA'){
			loadReferenceListById('scheduleList',data.scheduleList);
	    	}
	    	else if(typecode=='WT' || typecode=='PT'){
	    		loadSchedulingList('scheduleList',data.scheduleList);
	    	}
		
		$.each(data.scheduleList,function(key,value){
			schedulingArray['sch_'+key]=value;
		});
		
	}
	if(data.hasOwnProperty('tahdrList')){
		loadTenderListForScheduling(data.tahdrList);
    }
}
function tahdrSchedulingResp(data){
	debugger;
	if(data.objectMap.hasOwnProperty('tenderStatusList')){
		tenderStatusList=data.objectMap.tenderStatusList;
    }
	if(data.objectMap.hasOwnProperty('scheduleList')){
		loadReferenceListById('scheduleList',data.objectMap.scheduleList);
		
		$.each(data.objectMap.scheduleList,function(key,value){
			schedulingArray['sch_'+key]=value;
		});
		
	}
	if(data.objectMap.hasOwnProperty('tahdrList')){
		loadTenderListForScheduling(data.objectMap.tahdrList);
    }
}
function setToggle(tenderTypeCode){
	 
	if(tenderTypeCode=='PT'){
		$('#procurementToggle').addClass('active');
		$('#reverseToggle').addClass('active');
		$('#worksToggle').removeClass('active');
		$('#forwardToggle').removeClass('active');
	}else{
		$('#procurementToggle').removeClass('active');
		$('#reverseToggle').removeClass('active');
		$('#worksToggle').addClass('active')
		$('#forwardToggle').addClass('active')
	}
}

function loadTenderListForScheduling(data){
			$(".leftPaneData").html("");
			var leftPanelHtml="";
			var i=0;
			var firstRow=null;
			if(!$.isEmptyObject(data)){
					$.each(data,function(key,value){
						if(!$.isEmptyObject(value))
							{
							var tahdrCode = value.tahdrCode==null?'': value.tahdrCode;
							var title = value.title==null?'':value.title;
							var tahdrId = value.tahdrId==null?'':value.tahdrId;
							var tahdrStatus = value.tahdrStatusCode==null?'':value.tahdrStatusCode;
							tenderTypeCode=value.tahdrTypeCode;
							var tenderStatus=tenderStatusList[tahdrStatus];
							var tahdrDetail=value.tahdrDetail[0];
							if(tahdrDetail!=undefined){
								var tahdrDetailId = tahdrDetail.tahdrDetailId==null?'': tahdrDetail.tahdrDetailId;
								var version  = tahdrDetail.version==null?'':tahdrDetail.version;
								var emdFee 	  = tahdrDetail.emdFee==null?'':tahdrDetail.emdFee;
								var description  = tahdrDetail.description==null?'':tahdrDetail.description;
								var tahdrValidity = tahdrDetail.tahdrValidity==null?'':tahdrDetail.tahdrValidity;
								var contactEmailId= tahdrDetail.contactEmailId==null?'':tahdrDetail.contactEmailId;
								var minQuantity= tahdrDetail.minQuantity==null?'':tahdrDetail.minQuantity;
								
								tenderArray['tender_'+tahdrId]=value;
								if(i==0){
									firstRow = value;
									leftPanelHtml = leftPanelHtml + ' <li class="active" onclick="showTahdrDetail('+tahdrId+')" id="'+tahdrId+'">';
								}else{
									leftPanelHtml = leftPanelHtml + ' <li onclick="showTahdrDetail('+tahdrId+')" id="'+tahdrId+'">';
								}
							
								leftPanelHtml = leftPanelHtml +' <a href="#Master" data-toggle="tab">'
							    +' <div class="col-md-12">'
							    +'  <label class="col-xs-6" id="'+tahdrId+'_tenderNo">'+tahdrCode+'</label>'
							    +'	<label class="col-xs-6" id="'+tahdrId+'_title">'+title+'</label>'
							    +' </div>'	
							    +' <div class="col-md-12">'
							    +'	<label class="col-xs-6" id="'+tahdrId+'_tahdrValidity">'+tahdrValidity+'</label>'
								+'	<label class="col-xs-6" id="'+tahdrId+'_tenderStatus">'+tenderStatus+'</label>'
								+' </div>'
							    +' </a>'
							    +' </li>';
								i++;
							}
						}
							    
					});
					$(".leftPaneData").html(leftPanelHtml);
					loadTahdrDetailRightPane(firstRow);
					/*$('.leftPaneData').paginathing({
						perPage: 6,
					});*/
					
					 $(".tabs-left li a label").text(function(index, currentText) {
						    return currentText.substr(0, 20);
						});
			}
}

function loadTahdrDetailRightPane(data){
		if(!$.isEmptyObject(data)){
			var tahdrCode = data.tahdrCode==null?'': data.tahdrCode;
			var title = data.title==null?'':data.title;
			var status=data.tahdrStatusCode==null?'':data.tahdrStatusCode;
			var tahdrId=data.tahdrId==null?'':data.tahdrId;
			var bidType=data.bidTypeCode==null?'': data.bidTypeCode;
			var tahdrDetail=data.tahdrDetail[0];
			var tahdrDetailId = tahdrDetail.tahdrDetailId==null?'': tahdrDetail.tahdrDetailId;
				var version  = tahdrDetail.version==null?'':tahdrDetail.version;
				var emdFee 	  =tahdrDetail.emdFee==null?'':tahdrDetail.emdFee;
				var description  = tahdrDetail.description==null?'':tahdrDetail.description;
				var tahdrValidity = tahdrDetail.tahdrValidity==null?'':tahdrDetail.tahdrValidity;
				var contactEmailId= tahdrDetail.contactEmailId==null?'':tahdrDetail.contactEmailId;
				var minQuantity= tahdrDetail.minQuantity==null?'':tahdrDetail.minQuantity;
				
				var tenderStatus=tenderStatusList[status];
				
				$("#tenderSchedulingForm #tenderCode").html(tahdrCode);
				$("#tenderSchedulingForm #tahdrCode").val(tahdrCode);
				$("#tenderSchedulingForm #tenderStatus").html(tenderStatus);
				$("#tenderSchedulingForm #tahdrId").val(tahdrId);
				$("#tenderSchedulingForm #tahdrDetailId").val(tahdrDetailId);
				
				$("#tenderSchedulingForm #scheduleList").removeClass('errorinput');
				$('#submissionDateDivId').attr('style','display: none ;');
				$("#closingDateTime").removeAttr('name');
				

		}
		var documentType=$(".documentType").val();
		/*setHeaderValues(documentType+"No.: " + tahdrCodes, documentType+"Title : " + title, "Department : " + department, "Status : " + status);*/
		setHeaderValues(documentType+" Code: "+tahdrCode, documentType+" Version : "+version, "Description: "+description, "EMD Fees : "+emdFee);
		
		
		//setHeaderValues("Tender/Auction Code: "+tahdrCode, "Tender/Auction Version : "+version, "Description: "+description, "EMD Fees : "+emdFee);
		setSchedulingDropDown(status,bidType,tahdrDetail);
}

function showTahdrDetail(id)
{
	loadTahdrDetailRightPane(tenderArray['tender_'+id]);
}

function setSchedulingDropDown(status,bidType,tahdrDetail){
	 
	$("#openingDateTime").val('');
	$("#closingDateTime").val('');
	if(status=='SCRDONE' || status=='DBOP'){
		$('#tenderSchedulingForm #scheduleList').val('PBSCH');
		setDatePicker(new Date());
		$('#tenderSchedulingForm #scheduleListDivId').addClass('readonly');
		$("#tenderSchedulingForm #openingDateTime").attr('name', "priceBidOpenningDate");
		$("#tenderSchedulingForm #closingDateTime").attr('disabled', 'disabled');
	}else if(status=='PU'){
		if(bidType=='SB'){
			$('#tenderSchedulingForm #scheduleList').val('PBSCH');
			setDatePicker(new Date());
			$('#tenderSchedulingForm #scheduleListDivId').addClass('readonly');
			$("#tenderSchedulingForm #openingDateTime").attr('name', "priceBidOpenningDate");
			$("#tenderSchedulingForm #closingDateTime").attr('disabled', 'disabled');
		}else{
			$('#tenderSchedulingForm #scheduleList').val('');
			$('#tenderSchedulingForm #scheduleListDivId').removeClass('readonly');
			$("#closingDateTime").removeAttr('disabled');
		}
	}else if(status=='DBSCH'){
		$('#tenderSchedulingForm #scheduleList').val('DBSCH');
		setDatePicker(new Date());
		$('#tenderSchedulingForm #scheduleListDivId').addClass('readonly');
		$('#submissionDateDivId').removeAttr('style');
		$("#closingDateTime").attr('name','deviationToDate');
		$("#closingDateTime").addClass('requiredField');
		$("#openingDateTime").attr('name', 'deviationOpenningDate');
		$("#openingDateTime").val('');
		$("#closingDateTime").val('');
		$("#openingDateTime").val(formatDateTime(tahdrDetail.deviationOpenningDate));
		$("#closingDateTime").val(formatDateTime(tahdrDetail.deviationToDate));
	}
	else if (status=='PBSCH'){
		$('#tenderSchedulingForm #scheduleList').val('PBSCH');
		setDatePicker(new Date());
		$('#tenderSchedulingForm #scheduleListDivId').addClass('readonly');
		$('#submissionDateDivId').attr('style','display: none ;');
		$("#closingDateTime").removeAttr('name');
		$("#closingDateTime").removeClass('requiredField');
		$("#openingDateTime").attr('name', "priceBidOpenningDate");
		$("#openingDateTime").val('');
		$("#closingDateTime").val('');
		$("#openingDateTime").val(formatDateTime(tahdrDetail.priceBidOpenningDate));
	}
	else if (status=='C1SCH'){
		$('#tenderSchedulingForm #scheduleList').val('C1SCH');
		setDatePicker(new Date());
		$('#tenderSchedulingForm #scheduleListDivId').addClass('readonly');
		$('#submissionDateDivId').removeAttr('style');
		$("#closingDateTime").attr('name','c1ToDate');
		$("#closingDateTime").addClass('requiredField');
		$("#openingDateTime").attr('name', 'c1OpenningDate');
		$("#openingDateTime").val('');
		$("#closingDateTime").val('');
		$("#openingDateTime").val(formatDateTime(tahdrDetail.c1OpenningDate));
		$("#closingDateTime").val(formatDateTime(tahdrDetail.c1ToDate));
	}
	else if (status=='AWSCH'){
		$('#tenderSchedulingForm #scheduleList').val('AWSCH');
		setDatePicker(new Date());
		$('#tenderSchedulingForm #scheduleListDivId').addClass('readonly');
		$('#submissionDateDivId').attr('style','display: none ;');
		$("#closingDateTime").removeClass('requiredField');
		$("#closingDateTime").removeAttr('name');
		$("#openingDateTime").attr('name', 'winnerSelectionDate');
		$("#openingDateTime").val('');
		$("#closingDateTime").val('');
		$("#openingDateTime").val(formatDateTime(tahdrDetail.winnerSelectionDate));
	}
	else if (status=='RBSCH'){
		$('#tenderSchedulingForm #scheduleList').val('RBSCH');
		setDatePicker(new Date());
		$('#tenderSchedulingForm #scheduleListDivId').addClass('readonly');
		$('#submissionDateDivId').removeAttr('style');
		$("#closingDateTime").attr('name','revisedBidToDate');
		$("#closingDateTime").addClass('requiredField');
		$("#openingDateTime").attr('name','revisedBidOpenningDate' );
		$("#openingDateTime").val('');
		$("#closingDateTime").val('');
		$("#openingDateTime").val(formatDateTime(tahdrDetail.revisedBidOpenningDate));
		$("#closingDateTime").val(formatDateTime(tahdrDetail.revisedBidToDate));
	}
	else if (status=='ASCH'){
		$('#tenderSchedulingForm #scheduleList').val('ASCH');
		setDatePicker(new Date());
		$('#tenderSchedulingForm #scheduleListDivId').addClass('readonly');
		$('#submissionDateDivId').removeAttr('style');
		$("#closingDateTime").attr('name','auctionFromDate');
		$("#closingDateTime").addClass('requiredField');
		$("#openingDateTime").attr('name','auctionToDate' );
		$("#lastDateId").html('Auction Start Date & Time');
		$("#OpeningDateId").html('Auction End Date & Time' );
		$("#openingDateTime").val('');
		$("#closingDateTime").val('');
		$("#openingDateTime").val(formatDateTime(tahdrDetail.auctionToDate));
		$("#closingDateTime").val(formatDateTime(tahdrDetail.auctionFromDate));
	}

	else{
		setDatePicker('+1d');
		$('#tenderSchedulingForm #scheduleList').val('');
		$("#tenderSchedulingForm #scheduleList option[value='PBSCH']").hide();
		$('#tenderSchedulingForm #scheduleListDivId').removeClass('readonly');
		$("#closingDateTime").removeAttr('disabled');
	}
	
	}

function loadDateTimeData(valueName){
	 
	$("#lastDateId").html('Last Submission Date & Time');
	$("#OpeningDateId").html('Opening Date & Time' );
	
	$("#closingDateTime").addClass('requiredField');
	
	$("#closingDateTime").val(setCurrentDate());
	$("#openingDateTime").val(setCurrentDate());

if (valueName=='PBSCH'){
	$('#submissionDateDivId').attr('style','display: none ;');
	$("#closingDateTime").removeAttr('name');
	$("#closingDateTime").removeClass('requiredField');
	$("#openingDateTime").attr('name', "priceBidOpenningDate");
	setDatePicker(new Date());
}
else if (valueName=='C1SCH'){
	$('#submissionDateDivId').removeAttr('style');
	$("#closingDateTime").attr('name','c1ToDate');
	$("#openingDateTime").attr('name', 'c1OpenningDate');
	setDatePicker('+1d');
}
else if (valueName=='AWSCH'){
	$('#submissionDateDivId').attr('style','display: none ;');
	$("#closingDateTime").removeClass('requiredField');
	$("#closingDateTime").removeAttr('name');
	$("#openingDateTime").attr('name', 'winnerSelectionDate');
	setDatePicker(new Date());
}
else if (valueName=='RBSCH'){
	$('#submissionDateDivId').removeAttr('style');
	$("#closingDateTime").attr('name','revisedBidToDate');
	$("#openingDateTime").attr('name','revisedBidOpenningDate' );
	setDatePicker('+1d');
}
else if (valueName=='ASCH'){
	$('#submissionDateDivId').removeAttr('style');
	$("#closingDateTime").attr('name','auctionFromDate');
	$("#openingDateTime").attr('name','auctionToDate' );
	$("#lastDateId").html('Auction Start Date & Time');
	$("#OpeningDateId").html('Auction End Date & Time' );
	setDatePicker(new Date());
}

}

function formatDate(longDate){
	   var dt=new Date(longDate);
	   var dd=dt.getDate();
	   var MM=dt.getMonth()+1;
	   var yyyy=dt.getFullYear();
	   var HH= dt.getHours();
	   var mm= dt.getMinutes();
	   var ss= dt.getSeconds();
	   
	   return yyyy+'-'+MM+'-'+dd;
	   /*+' '+HH+':'+mm+':'+ss;*/
}

function tenderSchedulingResp(data){
	 
	if(data.objectMap.result==true){
		Alert.info(data.objectMap.resultMessage);
	}
	else{
		if(data.objectMap.dateValidationResult!=undefined && !data.objectMap.dateValidationResult){
			if (data.objectMap.dateValidatioError.hasError) {
				var msg = 'Following Dates Are Not Filled Properly:'+'<br/>';
				$.each(data.objectMap.dateValidatioError.errors, function(key, value) {
				     msg=msg+'\n'+value.errorMessage +','+ '<br/>';
				       
				   });
				    Alert.warn(msg);
			}
		}else{
			Alert.warn(data.objectMap.resultMessage);
		}
	}
}

function savetahdrScehdule(event){
	event.preventDefault();
	submitIt('tenderSchedulingForm','updateTahdrScheduling','tenderSchedulingResp');
}
function setDatePicker(startfrom){
	$('.form_datetime2').datetimepicker('remove');
	$('.form_datetime2').datetimepicker({
    	weekStart: 1,
		autoclose: 1,
		startView: 2,
		forceParse: 0,
		startDate :  startfrom,
		format: 'dd-mm-yyyy HH:ii',
        showMeridian: 1,
		orientation: 'auto',
		pickerPosition: "top-left",
        widgetPositioning:{
            horizontal: 'auto',
            vertical: 'bottom'
        } 
    });  
}
function fetchTenderList(pageNumber, pageSize, searchMode, searchValue){
	 
	var typecode=$('input[name=tenderTypeCodeToggle]:checked').val();
	var response;
	$.ajax({
        type : "POST",
        contentType : "application/json",
        url: "fetchTenderListForScheduling?pageNumber="+pageNumber+"&pageSize="+pageSize+'&searchMode='+searchMode+'&serachValue='+searchValue+'&typeCode='+typecode,
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

function loadSchedulingList(id,list){
	 
	$("#"+id).empty();
	$("#"+id).append("<option value=''></option>" );
	$.each(list,function(key,value){
		$("#"+id).append("<option value='"+value.code+"'>"+value.name+" </option>" );
	});
}