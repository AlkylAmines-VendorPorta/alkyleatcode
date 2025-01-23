/**
 * Aman
 */
var tenderArray= new Array();
var tenderVersionArray=new Array();
var chairPersonArray=new Array();
var momArray=new Array();

var leftPanePageSize = 7;
var maxVisiblePageNumbers = 3;
var selectedPage = 1;
var pageSize=7;
var searchMode='none';
var searchValue='none';
var LastPage='';
$(document).ready(function(){
	onPageLoad();
	/*submitToURL("getTenderListForPreBidMeeting","loadTenderListForPreBidMeeting");*/
	$("#cancelBtnId").on("click", function(event) {
		 
		event.preventDefault();
		return false;
	});
	$("input[name='tenderTypeCodeToggle']").on("change",function(){
		/*$('.pagination').children().remove();*/
		selectedPage=1;
		$('.prebidmeetingtab').addClass('k-state-active');
		$('.prebidmeetingtab').css({'display': 'inline-block'});
		var data=fetchTenderList(1, pageSize, searchMode, searchValue,$('input[name=tenderTypeCodeToggle]:checked').val());
		loadTenderList(data);
		LastPage=data.objectMap.LastPage;
		setPagination(data.objectMap.LastPage, selectedPage , maxVisiblePageNumbers);
		/*submitToURL("getTenderListForPreBidMeeting/"+$(this).val(), "loadTenderListForMOM");*/
	   /* $(".tabs-left li a label").text(function(index, currentText){
		    return currentText.substr(0, 20);
		});	*/
	});
	$('#pagination-here').paginate({
		pageSize:  7,
		dataSource: 'fetchTenderList',
		responseTo:  'loadTenderListPagination',
		maxVisiblePageNumbers:3,
		searchBoxID : 'searchLiteralId',
		loadOnStartup: false
	});
});
function onPageLoad(){
	 
	var typeCode=$("input[name='tenderTypeCodeToggle']:checked").val();
	var data=fetchTenderList(1, pageSize, searchMode, searchValue,typeCode);
	loadTenderList(data);
	LastPage=data.objectMap.LastPage;
	setPagination(data.objectMap.LastPage, selectedPage , maxVisiblePageNumbers);
	/*submitToURL("getTenderListForPreBidMeeting/"+typeCode,"loadTenderListForMOM");*/
}
function loadTenderListPagination(data){
	loadTenderListForMOM(data);
}
function loadTenderList(data){
	 
	data=data.data;
	loadTenderListForMOM(data);
}
function loadTenderListForMOM(data){
	 
	/*$('.pagination').children().remove();*/
	$(".leftPaneData").html("");
	var leftPanelHtml="";
	var i=0;
	var firstRow=null;
	if(!$.isEmptyObject(data.tahdrList)){
			$.each(data.tahdrList,function(key,value){
				if(!$.isEmptyObject(value)){
						var tahdrCode = value.tahdrCode==null?'': value.tahdrCode;
						var tahdrId = value.tahdrId==null?'': value.tahdrId;
						var title = value.title==null?'':value.title;
						var bidType=value.bidTypeCode==null?'':value.bidTypeCode=='SB'?'Single Bid':'Two Bid';
						
						tenderArray["tender_"+tahdrId]=value;
						
						if(i==0){
							firstRow = value;
							leftPanelHtml = leftPanelHtml + ' <li class="active" onclick="showTahdr('+tahdrId+')" id="'+tahdrId+'">';
						}else{
							leftPanelHtml = leftPanelHtml + ' <li onclick="showTahdr('+tahdrId+')" id="'+tahdrId+'">';
						}
					
						leftPanelHtml = leftPanelHtml +' <a href="#Master" data-toggle="tab">'
					    +' <div class="col-md-12">'
					    +'  <label class="col-xs-6" id="'+tahdrId+'_tenderNo">'+tahdrCode+'</label>'
					    +'	<label class="col-xs-6" id="'+tahdrId+'_title">'+title+'</label>'
					    +' </div>'	
					    +' <div class="col-md-12">'
					    +'	<label class="col-xs-6" id="'+tahdrId+'_tahdrValidity"></label>'
						+'	<label class="col-xs-6" id="'+tahdrId+'_bidType">'+bidType+'</label>'
						+' </div>'
					    +' </a>'
					    +' </li>';
						i++;
					}
			});
	}
	else{
		
	}
	$(".leftPaneData").html(leftPanelHtml);
	loadTahdrDetailRightPane(firstRow);
	/*$('.leftPaneData').paginathing({perPage: 6});*/

	$(".tabs-left li a label").text(function(index, currentText) {
			    return currentText.substr(0, 20);
	});
}
function loadTahdrDetailRightPane(data){
	 
		if(!$.isEmptyObject(data)){
			    var tahdrDetailId = data.tahdrDetail[0].tahdrDetailId==null?'': data.tahdrDetail[0].tahdrDetailId;
				var tahdrCode = data.tahdrCode==null?'': data.tahdrCode;
				var tahdrTypeCode = data.tahdrTypeCode==null?'': data.tahdrTypeCode;
				var tahdrId = data.tahdrId==null?'': data.tahdrId;
				var title = data.title==null?'':data.title;
				var version  = data.tahdrDetail[0].version==null?'':data.tahdrDetail[0].version;
				var emdFee 	  = data.tahdrDetail[0].emdFee==null?'':data.tahdrDetail[0].emdFee;
				var description  = data.tahdrDetail[0].description==null?'':data.tahdrDetail[0].description;
				var tahdrValidity = data.tahdrDetail[0].tahdrValidity==null?'':data.tahdrDetail[0].tahdrValidity;
				var contactEmailId= data.tahdrDetail[0].contactEmailId==null?'':data.tahdrDetail[0].contactEmailId;
				var minQuantity= data.tahdrDetail[0].minQuantity==null?'':data.tahdrDetail[0].minQuantity;
				
				
				var momName=data.mom==null?'':data.mom.fileName;
				var momId=data.mom==null?'':data.mom.attachmentId;

				
				$("#uploadMomForm #tenderVersion").html(version);
				$("#uploadMomForm #tenderNo").html(tahdrCode);
				$("#uploadMomForm #description").html(description);
				$("#uploadMomForm #emdFee").html(emdFee);
				$("#uploadMomForm #tahdrCode").html(tahdrCode);
				$("#uploadMomForm #title").html(title);
				$("#uploadMomForm #tahdrDetailId").val(tahdrDetailId);
				
				$("#uploadMomForm #tenderNo").val(tahdrCode);
				$("#uploadMomForm #tahdrId").val(tahdrId);
				
				 var url=$("#downloadMom").data('url');
	    			
	    			$("#uploadMomForm #techFileResponseId").val(momId);
	    			$("#uploadMomForm #downloadMom").prop('href', url+'/'+momId);
	    			$("#uploadMomForm #downloadMom").html(momName);
	    			if(momId==''){
	    				$("#uploadMomForm #downloadMom").removeAttr('href');
	    				$("#uploadMomForm #downloadMom").html('');
	    				$('#uploadMomForm #deleteMomAttachmentId').attr('disabled','disabled');
	    			}
	    			else{
	    				$('#uploadMomForm #deleteMomAttachmentId').removeAttr('disabled');
	    			}
	
				
				setHeaderValues("Tender Code: "+tahdrCode, "Tender Version : "+version, "Description: "+description, "EMD Fees : "+emdFee);
				
		}else{
			$("#uploadMomForm #downloadMom").removeAttr('href');
			$("#uploadMomForm #downloadMom").html('');
			$('#uploadMomForm #deleteMomAttachmentId').attr('disabled','disabled');
			$("#uploadMomForm #tenderVersion").html('');
			$("#uploadMomForm #tenderNo").html('');
			$("#uploadMomForm #description").html('');
			$("#uploadMomForm #emdFee").html('');
			$("#uploadMomForm #tahdrCode").html('');
			$("#uploadMomForm #title").html('');
			$("#uploadMomForm #tenderNo").val('');
			$("#uploadMomForm #tahdrId").val('');
			setHeaderValues("Tender Code: ", "Tender Version : ", "Description: ", "EMD Fees : ");
		}
}

function showTahdr(id){
	 
	 loadTahdrDetailRightPane(tenderArray['tender_'+id]);
}
function loadTenderListForPreBidMeeting(data){ 
	 
	/*var count=0;
	if(!$.isEmptyObject(data)){
		$.each(data.objectMap.tenderDetailList,function(key,value){
			 
			tenderArray[count]=value.tahdr;
			tenderVersionArray["tenderVersion_"+value.tahdr.tahdrId]=value.version;
			if(!$.isEmptyObject(value.tahdr.mom)){
				momArray['mom_'+value.tahdr.tahdrId]=value.tahdr.mom;
			}
			if(!$.isEmptyObject(value.tenderCommittee)){
				if(!$.isEmptyObject(value.tenderCommittee.chairPerson)){
					chairPersonArray["chairPerson_"+value.tahdr.tahdrId]=value.tenderCommittee.chairPerson.name;
				}
			}
			
			count++;
		});
		loadTenders(tenderArray);
	}*/
	if(!$.isEmptyObject(data)){
		$.each(data.objectMap.tahdrList,function(key,value){
			 
			tenderArray['tender_'+value.tahdrId]=value;
		});
		loadTenders(tenderArray);
	}
}

function loadTenders(data){
	$("#uploadMomForm #tenderId").html("");
	var options = '<option value="">Select</option>';
	$.each(data,function(key,value){
		options +='<option value="'+value.tahdrId+'">'+value.tahdrCode+'</option>'
	});
	$("#uploadMomForm #tenderId").append(options);
	
}

function uploadMOMResp(data){
	 
	
	if(data.objectMap.resultStatus==true){
		Alert.info(data.objectMap.resultMessage);
		var tahdrId=$("#uploadMomForm #tahdrId").val();
		tenderArray["tender_"+tahdrId].mom=data.objectMap.TAHDR.mom;
	}
	else{
		Alert.warn(data.objectMap.resultMessage);
	}
}

function getSetOtherDetails(el){
	  
	 var tahdrIds=$(el).find('option:selected').val();
	 
	 var tahdrId=$('#tenderId').find('option:selected').val();
	 var tahdrNo=$('#tenderId').find('option:selected').text();
	/* $("#uploadMomForm #tenderVersionId").val(tenderVersionArray["tenderVersion_"+tahdrId]);*/
	/* submitToURL('getTenderCommitteeByTahdrId/'+tahdrIds, 'setCommitteDetails')*/
	/* $("#uploadMomForm #chairPersonId").val(chairPersonArray["chairPerson_"+tahdrId]);*/
	 $("#uploadMomForm #tenderNo").val(tahdrNo);
	/* if(!$.isEmptyObject(momArray['mom_'+tahdrId])){
		 var mom=momArray['mom_'+tahdrId];
		 var url= $("#uploadMomForm #downloadPrevMom").data('url');
		 $("#uploadMomForm #downloadPrevMom").attr('href',url+'/'+mom.attachmentId);
		 $("#uploadMomForm #downloadPrevMom").html(mom.fileName);
		}
	 else{
		 $("#uploadMomForm #downloadPrevMom").attr('href',url);
		 $("#uploadMomForm #downloadPrevMom").html('');
	 }*/
	 var tahdr=tenderArray['tender_'+tahdrId];
	 if(!$.isEmptyObject(tahdr)){
		 var mom=tahdr.mom==null?'':tahdr.mom;
		 if(mom!=''){
			 var url= $("#uploadMomForm #downloadPrevMom").data('url');
			 $("#uploadMomForm #downloadPrevMom").attr('href',url+'/'+mom.attachmentId);
			 $("#uploadMomForm #downloadPrevMom").html(mom.fileName);
		 }else{
			 $("#uploadMomForm #downloadPrevMom").attr('href',url);
			 $("#uploadMomForm #downloadPrevMom").html('');
		 }
		}
	 else{
		
	 }
	 
	}
function momAttachmentDeleteResp(data){
	 
	if (!data.hasError) {
		$('#momUploadId').val('');
		$('#momResponseId').val('');
		$("#downloadMom").html('');
		$('.momResponseId').attr('disabled', true);
		Alert.info(data.message);
		var tahdrId=$("#uploadMomForm #tahdrId").val();
		if(tahdrId!=""){
			tenderArray["tender_"+tahdrId].mom=null;
		}
	} else {
		Alert.warn(data.message);
	}

}
function fetchTenderList(pageNumber, pageSize, searchMode, searchValue){
	var typecode=$('input[name=tenderTypeCodeToggle]:checked').val();
	var response;
	$.ajax({
        type : "POST",
        contentType : "application/json",
        url: "fetchTenderListForPreBid?pageNumber="+pageNumber+"&pageSize="+pageSize+'&searchMode='+searchMode+'&serachValue='+searchValue+'&typeCode='+typecode,
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

/*function setCommitteDetails(data){
	 
	
	$("#uploadMomForm #chairPersonId").val('');
	if(!$.isEmptyObject(data.objectMap.tenderCommittee)){
		var data=data.objectMap.tenderCommittee;
		var chairPersonName=data==null?'':data.chairPerson==null?'':data.chairPerson.name;
		$("#uploadMomForm #chairPersonId").val(chairPersonName);
	}
	$("#uploadMomForm #chairPersonId").val('');
}*/