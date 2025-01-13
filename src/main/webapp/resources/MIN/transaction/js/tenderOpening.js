/**
 * Aman Sahu
 */

var tenderDetailArray=new Array();
var bidderBidDetailArray=new Array();
var bidderBidDetailArrayPB=new Array();
var bidderBidDetailArrayTC=new Array();
var tahdrMaterialArray=new Array();
var statusList=new Array();

var tenderStatusList=new Array();

var bidderStatusList=new Array();
var counter=false;
var role='';
var tenderOpeningType='';
var bidSubmitted=0;
var tenderBidType='';
var thadrBidType='';
var globalTenderId=0;
var viewBidDifference=0;
$(document).ready(function(){
	
	$(window).on("unload", function(e) {
	    $('#tenderLogOutBtnId').click();
	});
	
	submitToURL('getOpeningTenderList', 'setOpeningType');
	var currentDateTime=setCurrentDate("");
	
	$('#venderTenderOpeningSearchForm #vbidOpeningDate').val(currentDateTime);
	$('#tenderOpeningSearchForm #bidOpeningDate').val(currentDateTime);
	if(role=='VENADM'){
		 $('#tenderBidOpeningTabId').attr('onclick',"return submitIt('venderTenderOpeningSearchForm','searchOpeningTenderList','searchOpeningTenderListForVendorResp');");
		 $('#venderResetBtnDivId').removeAttr('style');
		 $('#userResetBtnDivId').attr('style','display : none;');
	}else{
		$('#venderResetBtnDivId').attr('style','display : none;');
		$('#userResetBtnDivId').removeAttr('style');
	}
	
	$("input[name='tenderTypeCodeToggle']").on("change",function(){
		$("#leftPane").html('');
		var typeCode =$(this).val();
		if(role=='VENADM'){
			$('#venderTenderOpeningSearchForm #vtenderTypeCode').val(typeCode);
			/*submitToURL("getOpeningTenderList", "searchOpeningTenderListForVendorResp");*/
		}
		else
		  $('#tenderOpeningSearchForm #tenderTypeCode').val(typeCode);
		/*submitToURL("getOpeningTenderList", "searchOpeningTenderListForResp");*/
		resetSearchForm();
		});
	
	
	$('.indirectFormSubmit').click(function(event) {
        event.preventDefault();
        $('.ExplorerUserPromptPopup').modal('hide');
        var inputSessionKey=$('#inputSessionKey').val();
        if(inputSessionKey!=""){
        	submitWithTwoParam('checkSessionKey','inputSessionKey','tenderOpeningForm #tahdrId','loadMemberDiv');
        }
        $('#inputSessionKey').val('');
       /* $('.wizardTab').removeClass('readonly');
    	$('#vitualJoinTenderBtnId').trigger('click');*/
        return false;
    });
	$('.indirectBidderFormSubmit').click(function(event) {
        event.preventDefault();
        submitWithParam('vendorSessionKey','tenderOpeningForm #tahdrId','loadMemberDiv');
        return false;
    });
	
	$('#tenderLogOutBtnId').click(function(event) {
        event.preventDefault();
        alert('about to logout');
        submitWithParam('tenderLogout','openAllBidForm #tahdrId','tenderLogoutResp');
        return false;
    });

	 
	var dataUrl=$('#myTenderUrl').val();
	if(dataUrl!=''){
		$('#worksToggle').removeClass('active');
		$('#worksRadioId').removeAttr('checked');
		if(role=='VENADM'){
			submitToURL(dataUrl, "searchOpeningTenderListForVendorResp");
		}else{
			submitToURL(dataUrl, "searchOpeningTenderListResp");
		}
	    setToggle(tenderTypeCode);
	}
});
/*function onTenderTabClick(){
	getCacheLi();
}
function onSearchTender(ele){
	cacheLi();
	setCurrentTab(ele);
	if(getChangedFlag()){
	if(role=='VENADM'){
		 submitIt('venderTenderOpeningSearchForm','searchOpeningTenderList','searchOpeningTenderListForVendorResp');
	}else{
		submitIt('tenderOpeningSearchForm','searchOpeningTenderList','searchOpeningTenderListResp');	
	}
	setChangedFlag(false);
	}else{
		getCacheLi();
	}
}*/
function resetSearchDetailForm(){
	$("#leftPane").html('');
	$('#tenderOpeningForm #tenderNo').html('');
	$('#tenderOpeningForm #openingDate').html('');
	$('#tenderOpeningForm #status').html('');
	$('#tenderOpeningForm #bidEndDate').html('');
	$('#tenderOpeningForm #noBidder').html('');
	$('#tenderOpeningForm #bidSubmitted').html('');
	
	$('#viewBidStatus').html('');
	$('#viewBidStatusDivId').attr('style','display: none ;');
	$('#bidOppeningDate').html('');
	$('#viewBidParentTabId').addClass('readonly');
	var documentType=$(".documentType").val();
	/*setHeaderValues(documentType+"No.: " + tahdrCodes, documentType+"Title : " + title, "Department : " + department, "Status : " + status);*/
	setHeaderValues(documentType+" No:" , documentType+" Title : ", "Contact Email Id : ", "Description : ");
	
	
	//setHeaderValues("Tender No.: ", "Tender Title : ", "Contact EmailId : ", "Description : ");
}
function resetSearchForm(){
	$('#tenderOpeningSearchForm #tahdrCode').val('');
	$('#tenderOpeningSearchForm #openingType').val('');
	$('#tenderOpeningSearchForm #openingType').removeClass('readonly');
	
	$('#venderTenderOpeningSearchForm #vtahdrCode').val('');
	$('#venderTenderOpeningSearchForm #vopeningType').val('');
	$('#venderTenderOpeningSearchForm #vopeningType').removeClass('readonly');
	resetSearchDetailForm();
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
function setCurrentDate(longDate){
	var dt;
	if(null!=longDate && undefined!=longDate && ""!=longDate){
		dt=new Date(longDate);
	}
	else{
		dt=new Date();
	}
			var dd=dt.getDate();
			var MM=dt.getMonth()+1;
			var yyyy=dt.getFullYear();
			var HH= dt.getHours();
			var mm= (dt.getMinutes()<10?'0':'') + dt.getMinutes();   /*var mm= dt.getMinutes();*/
			var ss= dt.getSeconds();
		
			return dd+'-'+MM+'-'+yyyy+' '+HH+':'+mm;
	}
function compareDates(longDate,compareWithDate){
	 
	var dt1;
	var dt2;
	if(null!=longDate && undefined!=longDate && ""!=longDate){
		dt1=longDate;
	}
	else{
		return false;
	}
	if(null!=compareWithDate && undefined!=compareWithDate && ""!=compareWithDate){
		dt2=compareWithDate;
	}else{
		dt2=new Date().getTime();
	}
			var diff=dt2-dt1;
		    if( diff<(viewBidDifference)){
		    	return true;
		    }else{
		    	return false;
		    }
	}
function setOpeningType(data){
	role=data.objectMap.role;
	if(role=='VENADM'){
		if(!$.isEmptyObject(data.objectMap.bidderTenderList)){
					
				}
		$('#venderTenderOpeningSearchForm').removeAttr('style');
		$('#tenderOpeningSearchForm').attr('style','display: none ;');
		loadReferenceListById('venderTenderOpeningSearchForm #vopeningType',data.objectMap.openingType);
	}
	else{
		$('#tenderOpeningSearchForm').removeAttr('style');
		$('#venderTenderOpeningSearchForm ').attr('style','display: none ;');
		loadReferenceListById('tenderOpeningSearchForm #openingType',data.objectMap.openingType);
	}
}
function openingTypeChange(el){
	$('#dbopConditionalId').hide();
	$('#commercialDivId').show();
	var openingType=$(el).val();
	if($.isEmptyObject(openingType))
		{
			if(role=='VENADM'){
				$('#venderTenderOpeningSearchForm #vbidOpeningDate').attr('name','');
			}
			else{
				$('#tenderOpeningSearchForm #bidOpeningDate').attr('name','');
			}
			
		}
	else if(openingType=='TCOP')
		{
			if(role=='VENADM'){
				$('#venderTenderOpeningSearchForm #vbidOpeningDate').attr('name','tenderVersion.techBidOpenningDate');
				$('#venderTenderOpeningSearchForm #vtenderStatus').val('TCOP');
			}
			else{
				$('#tenderOpeningSearchForm #bidOpeningDate').attr('name','tenderVersion.techBidOpenningDate');
			 	
			 	$('#openAllBidForm #tenderStatus').val('TCOP');
			 	$('#tenderOpeningSearchForm #tenderStatus').val('TCOP');
			}
		}
	else if(openingType=='DBOP')
		{
			if(role=='VENADM'){
				$('#venderTenderOpeningSearchForm #vbidOpeningDate').attr('name','tenderVersion.deviationOpenningDate');
				$('#venderTenderOpeningSearchForm #vtenderStatus').val('DBOP');
			
			}
			else{
				$('#tenderOpeningSearchForm #bidOpeningDate').attr('name','tenderVersion.deviationOpenningDate');
				
				$('#openAllBidForm #tenderStatus').val('DBOP');
				$('#tenderOpeningSearchForm #tenderStatus').val('DBOP');
				
			}
			$('#commercialDivId').hide();
			$('#dbopConditionalId').show();

		}
	else if(openingType=='C1OP')
		{
			if(role=='VENADM'){
				$('#venderTenderOpeningSearchForm #vbidOpeningDate').attr('name','tenderVersion.c1OpenningDate');
				$('#venderTenderOpeningSearchForm #vtenderStatus').val('C1OP');
			}
			else{
				$('#tenderOpeningSearchForm #bidOpeningDate').attr('name','tenderVersion.c1OpenningDate');
				
				$('#openAllBidForm #tenderStatus').val('C1OP');
				$('#tenderOpeningSearchForm #tenderStatus').val('C1OP');
			}
		}
	else if(openingType=='PBOP' || openingType=='SBPBOP'){
			if(role=='VENADM'){
				$('#venderTenderOpeningSearchForm #vbidOpeningDate').attr('name','tenderVersion.priceBidOpenningDate');
				$('#venderTenderOpeningSearchForm #vtenderStatus').val('PBOP');
				$('#venderTenderOpeningSearchForm #vtenderBidType').val('TB');
			}
			else{
				$('#tenderOpeningSearchForm #bidOpeningDate').attr('name','tenderVersion.priceBidOpenningDate');
				
				$('#openAllBidForm #tenderStatus').val('PBOP');
				$('#tenderOpeningSearchForm #tenderStatus').val('PBOP');
				$('#tenderOpeningSearchForm #tenderBidType').val('TB');
			}
			if(openingType=='SBPBOP'){
				$('#tenderOpeningSearchForm #tenderBidType').val('SB');
				$('#venderTenderOpeningSearchForm #vtenderBidType').val('SB');
			}
		}
	
	}
function searchOpeningTenderListResp(data){
resetSearchDetailForm();
$("#autoRefresh").timer('remove');
    $("#leftPane").html("");
    $('.pagination').children().remove();
	var leftPanelHtml="";
	var i=0;
	var firstRow=null;
	if(role=='VENADM'){
		tenderOpeningType=$('#venderTenderOpeningSearchForm #vopeningType').find('option:selected').val();
	}else{
		tenderOpeningType=$('#tenderOpeningSearchForm #openingType').find('option:selected').val();
	}
	tenderStatusList=data.objectMap.tenderStatusList;

	bidderStatusList=data.objectMap.bidderStatusList;
	
	viewBidDifference=data.objectMap.viewBidDifference;
	
	if(!$.isEmptyObject(data.objectMap.tenderCommittee)){
		$.each(data.objectMap.tenderCommittee,function(key,value){
			if(value!=null){
				var tahdrDetailId = value.tahdrDetail[0].tahdrDetailId==null?'': value.tahdrDetail[0].tahdrDetailId;
				var tahdrCode = value.tahdrCode==null?'': value.tahdrCode;
				var title = value.title==null?'':value.title;
				var version  = value.tahdrDetail[0].version==null?'':value.tahdrDetail[0].version;
				var emdFee 	  = value.tahdrDetail[0].emdFee==null?'':value.tahdrDetail[0].emdFee;
				var minQuatity = value.tahdrDetail[0].minQuatity==null?'':value.tahdrDetail[0].minQuatity;

				
				tenderDetailArray['tender_'+tahdrDetailId]=value;
				if(i==0){
					firstRow = value;
					leftPanelHtml = leftPanelHtml + ' <li class="active" onclick="showTahdrDetail('+tahdrDetailId+')" id="'+tahdrDetailId+'">';
				}else{
					leftPanelHtml = leftPanelHtml + ' <li onclick="showTahdrDetail('+tahdrDetailId+')" id="'+tahdrDetailId+'">';
				}
			
				leftPanelHtml = leftPanelHtml +' <a href="#Master" data-toggle="tab">'
			    +' <div class="col-md-12">'
			    +'  <label class="col-xs-6" id="'+tahdrDetailId+'_tenderNo">'+tahdrCode+'</label>'
			    +'	<label class="col-xs-6" id="'+tahdrDetailId+'_title">'+title+'</label>'
			    +' </div>'	
			    +' <div class="col-md-12">'
			    +'	<label class="col-xs-6" id="'+tahdrDetailId+'_tahdrValidity">'+minQuatity+'</label>'
				+'	<label class="col-xs-6" id="'+tahdrDetailId+'_emdFee">'+emdFee+'</label>'
				+' </div>'
			    +' </a>'
				i++;
			}
	});
		$("#leftPane").html(leftPanelHtml);
		searchOpeningTenderListRightPane(firstRow);
				    
	}else{
	  Alert.warn('No Scheduled Tender/Auction Found for Selected Opening Type !')
	  resetSearchForm();
	}
	
	$('.example').paginathing({perPage: 6});
	
	
	$('.wizardTab').addClass('readonly');
	
	}
function searchOpeningTenderListRightPane(data){
	var tahdrSatus='';
	var tenderId='';
	$('#viewBidStatus').html('');
	$('#viewBidStatusDivId').attr('style','display: none ;');
	if(data!=null){
		if(role=='VENADM'){
			var openingType=$('#venderTenderOpeningSearchForm #vopeningType').find('option:selected').val();
			
			
			if(!$.isEmptyObject(data.tahdrDetail[0])){
					    var tahdrDetailId = data.tahdrDetail[0].tahdrDetailId==null?'': data.tahdrDetail[0].tahdrDetailId;
					    var tahdrId = data.tahdrId==null?'': data.tahdrId;
					    tenderId=tahdrId;
					    globalTenderId=tahdrId;
						var tenderNo = data.title==null?'': data.title;
						var status = data.tahdrStatusCode==null?'':data.tahdrStatusCode;
					   
					    tahdrSatus=status;
						var bidOpenningdate = data.bidOpenedDate==null?'':data.bidOpenedDate;
						
						if(status=='DBOP'){
							$('#bidTabId').attr("onclick","return submitWithTwoParam('getBidderListByViewOpeningType','tahdrId','tahdrMaterialId' ,'loadBidderListForViewDb');");
						}else if(status=='TCOP' || status=='PBOP' || status=='C1OP'){
							$('#bidTabId').attr("onclick","return submitWithTwoParam('getBidderListByViewOpeningType','tahdrId','tahdrMaterialId' ,'loadBidderListForView');");
						}
						var tenderStatus = tenderStatusList[status];
						
						var tenderType  = data.tahdrTypeCode=='PT'?'Procurement':'Works';
						var bidTypeCode  = data.bidTypeCode==null?'': data.bidTypeCode;
						thadrBidType=bidTypeCode;
						if(bidTypeCode=='SB'){
							$('#venderTenderOpeningSearchForm #vopeningType').val('SBPBOP');
							$('#venderTenderOpeningSearchForm #vopeningType').addClass('readonly');
							$('#bidTabId').attr("onclick","return submitWithTwoParam('getBidderListByViewOpeningType','tahdrId','tahdrMaterialId' ,'loadBidderListForViewSB');");
						}else{
							$('#venderTenderOpeningSearchForm #vopeningType').removeClass('readonly');
							$('#venderTenderOpeningSearchForm #vopeningType').val(tenderOpeningType);
						}
						var description = data.tahdrDetail[0].description==null?'': data.tahdrDetail[0].description;
						 
						var tenderCode = data.tahdrCode==null?'': data.tahdrCode;
						var contactEmail = data.tahdrDetail[0].contactEmailId==null?'': data.tahdrDetail[0].contactEmailId;
						
						/*var noOfBidder  = data.bidders==null?0: data.bidders.length;*/
						bidSubmitted=0;
						/*bidAndBidderCount(status,data);*/
						
						switch (tenderOpeningType) {
					    case 'TCOP':
					    	var openingDate = data.tahdrDetail[0].techBidOpenningDate==null?'':data.tahdrDetail[0].techBidOpenningDate;
							var bidEndDate = data.tahdrDetail[0].technicalBidToDate==null?'':data.tahdrDetail[0].technicalBidToDate;
					        break;
					    case 'PBOP':
					    	var openingDate = data.tahdrDetail[0].priceBidOpenningDate==null?'':data.tahdrDetail[0].priceBidOpenningDate;
							var bidEndDate = data.tahdrDetail[0].technicalBidToDate==null?'':data.tahdrDetail[0].technicalBidToDate;
					        break;
					    case 'SBPBOP':
					    	var openingDate = data.tahdrDetail[0].priceBidOpenningDate==null?'':data.tahdrDetail[0].priceBidOpenningDate;
							var bidEndDate = '';
					        break;
					    case 'C1OP':
					    	var openingDate = data.tahdrDetail[0].c1OpenningDate==null?'':data.tahdrDetail[0].c1OpenningDate;
							var bidEndDate = data.tahdrDetail[0].c1ToDate==null?'':data.tahdrDetail[0].c1ToDate;
					        break;
					    case 'DBOP':
					    	var openingDate = data.tahdrDetail[0].deviationOpenningDate==null?'':data.tahdrDetail[0].deviationOpenningDate;
							var bidEndDate = data.tahdrDetail[0].deviationToDate==null?'':data.tahdrDetail[0].deviationToDate;
					        break;
					    default:
					    	var openingDate ='';
						    var bidEndDate = '';
					}	
						$("#tenderOpeningForm #openingDate").html(openingDate==''?'':setCurrentDate(openingDate));
						$("#tenderOpeningForm #bidEndDate").html(bidEndDate==''?'':setCurrentDate(bidEndDate));
						$("#tenderOpeningForm #tenderNo").html(tenderCode);
						$("#tenderOpeningForm #status").html(tenderStatus);
						$("#tenderOpeningForm #bidSubmitted").html(bidSubmitted);
						/*$("#tenderOpeningForm #noBidder").html(noOfBidder);*/
						$("#tenderOpeningForm #tahdrDetailId").val(tahdrDetailId);
						$("#tahdrId").val(tahdrId);
						$("#openAllBidForm #tahdrId").val(tahdrId);
						$('#openAllBidForm #tenderStatus').val($('#tenderOpeningSearchForm #openingType').find('option:selected').val());
						$('#openAllBidForm #tenderBidType').val(bidTypeCode);
						
						if(status=='TCOP' || status=='PBOP' || status=='DBOP' || status=='C1OP' ){
							
									if(compareDates(bidOpenningdate,'')){
										$('#viewBidBtnId').removeAttr('style');
										$('.viewBidTab').removeClass('readonly');
										$('#viewBidStatusDivId').attr('style','display: none ;');
									}else{
										$('#viewBidStatus').html('Cannot View Bid 2 Days After Opening Tender');
										$('#viewBidStatusDivId').removeAttr('style');
										$('#bidOppeningDate').html(setCurrentDate(bidOpenningdate));
										$('#viewBidBtnId').attr('style','display: none ;');
									}
								$('#bidderViewBidBtnId').hide();
								
							}
						else
							{
								$('#viewBidBtnId').attr('style','display: none ;');
								$('#bidderViewBidBtnId').show();
								$('.viewBidTab').addClass('readonly');
							}
						
						
						var documentType=$(".documentType").val();
						/*setHeaderValues(documentType+"No.: " + tahdrCodes, documentType+"Title : " + title, "Department : " + department, "Status : " + status);*/
						setHeaderValues(documentType+" No:" + tenderCode, documentType+" Title : " + tenderNo, "Contact Email Id : "+contactEmail, "Description : "+description);
						
						//setHeaderValues("Tender No.: "+tenderCode, "Tender Title : "+tenderNo, "Contact Email Id : "+contactEmail, "Description : "+description);
					}

			}else if (data!=null){
				var openingType=$('#tenderOpeningSearchForm #openingType').find('option:selected').val();
				
				if(!$.isEmptyObject(data.tahdrDetail[0])){
					 
						    var tahdrDetailId = data.tahdrDetail[0].tahdrDetailId==null?'': data.tahdrDetail[0].tahdrDetailId;
						    var tahdrId = data.tahdrId==null?'': data.tahdrId;
						    tenderId=tahdrId;
						    globalTenderId=tahdrId;
							var tenderNo = data.title==null?'': data.title;
							
							var status = data.tahdrStatusCode==null?'':data.tahdrStatusCode;
							tahdrSatus=status;
							var bidOpenningdate = data.bidOpenedDate==null?'':data.bidOpenedDate;
							
							if(status=='DBOP'){
								$('#bidTabId').attr("onclick","return submitWithTwoParam('getBidderListByViewOpeningType','tahdrId','tahdrMaterialId' ,'loadBidderListForViewDb');");
							}else if(status=='TCOP' || status=='PBOP' || status=='C1OP'){
								$('#bidTabId').attr("onclick","return submitWithTwoParam('getBidderListByViewOpeningType','tahdrId','tahdrMaterialId' ,'loadBidderListForView');");
							}
							
							var tenderStatus = tenderStatusList[status];
							
							var description = data.tahdrDetail[0].description==null?'': data.tahdrDetail[0].description;
							 
							var tenderCode = data.tahdrCode==null?'': data.tahdrCode;
							var contactEmail = data.tahdrDetail[0].contactEmailId==null?'': data.tahdrDetail[0].contactEmailId;
								
							var tenderType  = data.tahdrTypeCode=='PT'?'Procurement':'Works';
							var bidTypeCode  = data.tahdrDetail[0].tahdr.bidTypeCode==null?'': data.tahdrDetail[0].tahdr.bidTypeCode;
							thadrBidType=bidTypeCode;
							if(bidTypeCode=='SB'){
								$('#tenderOpeningSearchForm #openingType').val('SBPBOP');
								$('#tenderOpeningSearchForm #openingType').addClass('readonly');
								$('#bidTabId').attr("onclick","return submitWithTwoParam('getBidderListByViewOpeningType','tahdrId','tahdrMaterialId' ,'loadBidderListForViewSB');");
							}else{
								$('#tenderOpeningSearchForm #openingType').removeClass('readonly');
								$('#tenderOpeningSearchForm #openingType').val(tenderOpeningType);
							}
							/*var noOfBidder  = data.bidders==null?0: data.bidders.length;*/
							bidSubmitted=0;
							/*bidAndBidderCount(status,data);*/
							
							switch (openingType) {
						    case 'TCOP':
						    	var openingDate = data.tahdrDetail[0].techBidOpenningDate==null?'':data.tahdrDetail[0].techBidOpenningDate;
								var bidEndDate = data.tahdrDetail[0].technicalBidToDate==null?'':data.tahdrDetail[0].technicalBidToDate;
						        break;
						    case 'PBOP':
						    	var openingDate = data.tahdrDetail[0].priceBidOpenningDate==null?'':data.tahdrDetail[0].priceBidOpenningDate;
								var bidEndDate = data.tahdrDetail[0].technicalBidToDate==null?'':data.tahdrDetail[0].technicalBidToDate;
						        break;
						    case 'SBPBOP':
						    	var openingDate = data.tahdrDetail[0].priceBidOpenningDate==null?'':data.tahdrDetail[0].priceBidOpenningDate;
								var bidEndDate = '';
						        break;
						    case 'C1OP':
						    	var openingDate = data.tahdrDetail[0].c1OpenningDate==null?'':data.tahdrDetail[0].c1OpenningDate;
								var bidEndDate = data.tahdrDetail[0].c1ToDate==null?'':data.tahdrDetail[0].c1ToDate;
						        break;
						    case 'DBOP':
						    	var openingDate = data.tahdrDetail[0].deviationOpenningDate==null?'':data.tahdrDetail[0].deviationOpenningDate;
								var bidEndDate = data.tahdrDetail[0].deviationToDate==null?'':data.tahdrDetail[0].deviationToDate;
						        break;
						    default:
						    	var openingDate ='';
							    var bidEndDate = '';
						}
							
							$("#tenderOpeningForm #openingDate").html(openingDate==''?'':setCurrentDate(openingDate));
							$("#tenderOpeningForm #bidEndDate").html(bidEndDate==''?'':setCurrentDate(bidEndDate));
							$("#tenderOpeningForm #tenderNo").html(tenderCode);
							$("#tenderOpeningForm #status").html(tenderStatus);
							$("#tenderOpeningForm #bidSubmitted").html(bidSubmitted);
							/*$("#tenderOpeningForm #noBidder").html(noOfBidder);*/
							$("#tenderOpeningForm #tahdrDetailId").val(tahdrDetailId);
							$("#tahdrId").val(tahdrId);
							$("#openAllBidForm #tahdrId").val(tahdrId);
							$("#openAllBidForm #tahdrCode").val(tenderNo);
							$('#openAllBidForm #tenderStatus').val($('#tenderOpeningSearchForm #openingType').find('option:selected').val());
							$('#openAllBidForm #tenderBidType').val(bidTypeCode);
							$('#sessionKeyForm #tahdrId').val(tahdrId);
							$('#sessionKeyForm #openingType').val($('#tenderOpeningSearchForm #openingType').find('option:selected').val());
							
							if(status=='TCOP' || status=='PBOP' || status=='DBOP' || status=='C1OP' ){
								if(compareDates(bidOpenningdate,'')){
									$('#viewBidBtnId').removeAttr('style');
									$('.viewBidTab').removeClass('readonly');
									$('#viewBidStatusDivId').attr('style','display: none ;');
								}else{
									$('#viewBidStatus').html('Cannot View Bid 2 Days After Opening Tender');
									$('#viewBidStatusDivId').removeAttr('style');
									$('#bidOppeningDate').html(setCurrentDate(bidOpenningdate));
									$('#viewBidBtnId').attr('style','display: none ;');
								}
							
									$('#joinTenderBtnId').attr('style','display: none ;');
									$('#openAllBidForm').attr('style','display: none ;');
									
									$('#viewAllBidBtnId').removeAttr('style');
									
										
								}
							else
								{
								    $('#openAllBidForm').removeAttr('style');
									$('#viewBidBtnId').attr('style','display: none ;');
								    $('#joinTenderBtnId').removeAttr('style');
								    $('.viewBidTab').addClass('readonly');
								}
							var documentType=$(".documentType").val();
							/*setHeaderValues(documentType+"No.: " + tahdrCodes, documentType+"Title : " + title, "Department : " + department, "Status : " + status);*/
							setHeaderValues(documentType+" No:" + tenderCode, documentType+" Title : " + tenderNo, "Contact Email Id : "+contactEmail, "Description : "+description);
							
							//setHeaderValues("Tender No.: "+tenderCode, "Tender Title : "+tenderNo, "Contact EmailId : "+contactEmail, "Description : "+description);

						}
				

			  }	
		bidAndBidderCount(tenderOpeningType,tenderId,tahdrSatus);
		$("#tenderOpeningForm #bidSubmitted").html(bidSubmitted);
	}
	else{
	 	$('#viewBidBtnId').attr('style','display: none ;');
	 	$("#tenderOpeningForm #openingDate").html('');
		$("#tenderOpeningForm #bidEndDate").html('');
		$("#tenderOpeningForm #tenderNo").html('');
		$("#tenderOpeningForm #status").html('');
		$("#tenderOpeningForm #bidSubmitted").html('');
		$("#tenderOpeningForm #noBidder").html('');
		$("#tenderOpeningForm #tahdrDetailId").val('');
		$("#tahdrId").val(tahdrId);
		Alert.warn('No Tender or Auction Found !');
	}
}
function showTahdrDetail(tahdrDetailId){
	searchOpeningTenderListRightPane(tenderDetailArray['tender_'+tahdrDetailId]);
}
function searchOpeningTenderListForVendorResp(data){
	resetSearchDetailForm();
	$('.pagination').children().remove();
	$("#leftPane").html("");
	var leftPanelHtml="";
	var i=0;
	var firstRow=null;
	if(role=='VENADM'){
		tenderOpeningType=$('#venderTenderOpeningSearchForm #vopeningType').find('option:selected').val();
	}else{
		tenderOpeningType=$('#tenderOpeningSearchForm #openingType').find('option:selected').val();
	}
	tenderStatusList=data.objectMap.tenderStatusList;

	bidderStatusList=data.objectMap.bidderStatusList;
	
	viewBidDifference=data.objectMap.viewBidDifference;
	
	if(!$.isEmptyObject(data.objectMap.bidderTenderList)){
		if(data.objectMap.bidderTenderList.length!=0){
			$.each(data.objectMap.bidderTenderList,function(key,value){
				if(value!=null){
				        value= value.tahdr;
				        var tahdrDetailId = value.tahdrDetail[0]==null?'': value.tahdrDetail[0].tahdrDetailId;
						var tahdrCode = value.tahdrCode==null?'': value.tahdrCode;
						var title = value.title==null?'':value.title;
						var version  = value.tahdrDetail[0].version==null?'':value.tahdrDetail[0].version;
						var emdFee 	  = value.tahdrDetail[0].emdFee==null?'':value.tahdrDetail[0].emdFee;
						var minQuatity = value.tahdrDetail[0].minQuatity==null?'':value.tahdrDetail[0].minQuatity;
						tenderDetailArray['tender_'+tahdrDetailId]=value;
						if(i==0){
							firstRow = value;
							leftPanelHtml = leftPanelHtml + ' <li class="active" onclick="showTahdrDetail('+tahdrDetailId+')" id="'+tahdrDetailId+'">';
						}else{
							leftPanelHtml = leftPanelHtml + ' <li onclick="showTahdrDetail('+tahdrDetailId+')" id="'+tahdrDetailId+'">';
						}
					
						leftPanelHtml = leftPanelHtml +' <a href="#Master" data-toggle="tab">'
					    +' <div class="col-md-12">'
					    +'  <label class="col-xs-6" id="'+tahdrDetailId+'_tenderNo">'+tahdrCode+'</label>'
					    +'	<label class="col-xs-6" id="'+tahdrDetailId+'_title">'+title+'</label>'
					    +' </div>'	
					    +' <div class="col-md-12">'
					    +'	<label class="col-xs-6" id="'+tahdrDetailId+'_tahdrValidity">'+minQuatity+'</label>'
						+'	<label class="col-xs-6" id="'+tahdrDetailId+'_emdFee">'+emdFee+'</label>'
						+' </div>'
					    +' </a>'
						i++;
				}
			});
			$("#leftPane").html(leftPanelHtml);
			searchOpeningTenderListRightPane(firstRow);
		}
	}else{
		Alert.warn('No Scheduled Tender/Auction Found for Selected Opening Type !')
	}
	
	$('.example').paginathing({perPage: 6});
	
	}
function openingTenderResp(data){
	/*var openingType=$('#openingType').find('option:selected').text();*/

	if(data.objectMap.result==true)
		{
			Alert.info(data.objectMap.resultMessage);
			$('#viewAllBidBtnId').removeAttr('style');
			$('#joinTenderBtnId').attr('style','display: none ;');
			$('.wizardTab').addClass('readonly');
			$('#viewBidBtnId').removeAttr('style');
			$('#openAllBidForm #openTenderBtnId').hide();
			if(data.objectMap.openingType!='VO'){
				$('#openAllBidForm #viewBidBtnId').show();
			}
			if(data.objectMap.openingType=='DBOP'){
				$('#bidTabId').attr("onclick","return submitWithTwoParam('getBidderListByViewOpeningType','tahdrId','tahdrMaterialId' ,'loadBidderListForViewDb');");
			}else if(data.objectMap.openingType=='TCOP' || data.objectMap.openingType=='PBOP' || data.objectMap.openingType=='C1OP'){
				if(thadrBidType=='SB'){
					$('#bidTabId').attr("onclick","return submitWithTwoParam('getBidderListByViewOpeningType','tahdrId','tahdrMaterialId' ,'loadBidderListForViewSB');");
				}else{
					$('#bidTabId').attr("onclick","return submitWithTwoParam('getBidderListByViewOpeningType','tahdrId','tahdrMaterialId' ,'loadBidderListForView');");
				}
				
			}
		}
	else{
			Alert.warn(data.objectMap.resultMessage);
			$('#viewBidBtnId').attr('style','display: none ;');
			$('#joinTenderBtnId').removeAttr('style');
			if(data.objectMap.openingType!='VO'){
				$('#openAllBidForm #viewBidBtnId').hide();
			}
		}
	}
function loadMemberDiv(data){
	if(!$.isEmptyObject(data.objectMap)){
		autoRefresh();
		if(data.objectMap.result==true){
			$('#leftPane li.active').removeAttr('onclick');
			$('.wizardTab').removeClass('readonly');
			$('#leftPane li:not(.active)').remove();
			
			/*$('#joinTenderBtnId .toggleTab').trigger('click');*/
			$('#vitualJoinTenderBtnId').trigger('click');
			loadLoggedInCommitteeMember(data);
			loadBidderDiv(data);
			loadLoggedInBidderDiv(data);
			if(data.objectMap.isChairPerson){
				$('#openAllBidForm').removeAttr('style');
			}else{
				$('#openAllBidForm').attr('style','display: none ;');
			}
		}else{
			Alert.warn(data.objectMap.resultMessage);
		}
	}else{
		Alert.warn("Something went wrong !");
	}
	}
function loadLoggedInCommitteeMember(data){
	if(!$.isEmptyObject(data.objectMap.loggedInMember)){
		$("#loggedInCommitteeMemberDivId").html("");
		var fileDataHtml="";
		$.each(data.objectMap.loggedInMember,function(key,value){
			
			fileDataHtml+='<span class="col-sm-12 nopadding logspan"> <a class="col-sm-12 loglink nopadding"></a>'
	  		    +'<label class="col-sm-6 nopadding">'+value.name+'</label></span>';
		
			$("#loggedInCommitteeMemberDivId").append(fileDataHtml);
			fileDataHtml='';
	});
	}else{
		$("#loggedInCommitteeMemberDivId").html("");
	}
	}
function loadBidderDiv(data){
	if(!$.isEmptyObject(data.objectMap.bidderList)){
			$("#bidderDivId").html("");
			var fileDataHtml="";
			$.each(data.objectMap.bidderList,function(key,value){
				/*var status='';
				if(tenderOpeningType=='PBOP'){
					status=value.status==null?'Bid Not Submitted':bidderStatusList[value.status];
				}else{
				    status=(value.status=='DBSU' || value.status=='SBMT' || value.status=='C1SU')?'Bid Submitted':'Bid Not Submitted';
				}*/
				var status=value.status==null?'Bid Not Submitted':bidderStatusList[value.status];
				fileDataHtml+='<div class="col-sm-12"><div class="col-sm-12 nopadding logspan"> <a class="col-sm-12 loglink nopadding"></a>'
		  		    +'<label class="col-sm-6 text-left nopadding">'+value.created+'</label><label class="col-sm-6 text-right nopadding">'+status+'</label></div></div>';
				
				$("#bidderDivId").append(fileDataHtml);
				fileDataHtml='';
		});
		}
	}
function loadLoggedInBidderDiv(data){
	if(!$.isEmptyObject(data.objectMap.loggedInbidderList)){
			$("#loggedInVenderDivId").html("");
			var fileDataHtml="";
			$.each(data.objectMap.loggedInbidderList,function(key,value){
				
				fileDataHtml+='<div class="col-sm-12"><div class="col-sm-12 nopadding logspan"> <a class="col-sm-12 loglink nopadding"></a>'
		  		    +'<label class="col-sm-6 text-left nopadding">'+value.partner.name+'</label></div></div>';
		  		   
				$("#loggedInVenderDivId").append(fileDataHtml);
				fileDataHtml='';
		});
		}
	else{
		$("#loggedInVenderDivId").html("");
	}
	
	}

function loadTahdrMaterialListForView(data){
	$("#autoRefresh").timer('remove');
	$('.pagination').children().remove();
    $("#leftPane").html("");
	var leftPanelHtml="";
	var i=0;
	var firstRow=null;
	if(!$.isEmptyObject(data.objectMap.responseList)){
		$.each(data.objectMap.responseList,function(key,value){
			var tahdrMaterialId =value.tahdrMaterialId==null?'': value.tahdrMaterialId;
		    var name = value.material==null?'': value.material.name==null?'': value.material.name;
		    var uomName = value.material==null?'': value.material.uom==null?'': value.material.uom.name==null?'': value.material.uom.name;
			var description = value.material==null?'': value.material.description==null?'':  value.material.description;
			var hsnCode =  value.material==null?'': value.material.hsnCode==null?'':value.material.hsnCode.code==null?'': value.material.hsnCode.code;
			
			tahdrMaterialArray["tahdrMaterial_"+tahdrMaterialId]=value;
			if(i==0){
				firstRow = value;
				
				leftPanelHtml = leftPanelHtml + ' <li class="active" id="'+tahdrMaterialId+'"  onclick="showTahdrMaterialDetail(this)">';
			}else{
				leftPanelHtml = leftPanelHtml + ' <li class="itemBidLi" id="'+tahdrMaterialId+'"  onclick="showTahdrMaterialDetail(this)">';
			}
		
			leftPanelHtml = leftPanelHtml +' <a href="#Master" data-toggle="tab">'
		    +' <div class="col-md-12">'
		    +'  <label class="col-xs-6" id="'+tahdrMaterialId+'_name">'+name+'</label>'
		    +'	<label class="col-xs-6" id="'+tahdrMaterialId+'_uomName">'+uomName+'</label>'
		    +' </div>'	
		    +' <div class="col-md-12">'
		    +'	<label class="col-xs-6" id="'+tahdrMaterialId+'_description">'+description+'</label>'
			+'	<label class="col-xs-6" id="'+tahdrMaterialId+'_hcnCode">'+hsnCode+'</label>'
			+' </div>'
		    +' </a>'
		    +' </li>';
			i++;
		});
	}
$("#leftPane").html(leftPanelHtml);
loadTahdrMaterialRightPane(firstRow);
$('#leftPane').paginathing({perPage: 6});
}
function loadTahdrMaterialRightPane(data){
 
if(!$.isEmptyObject(data)){
	    var tahdrMaterialId = data.tahdrMaterialId==null?'': data.tahdrMaterialId;
	    var name = data.material.materialId==null?'': data.material.name;
	    var code = data.material.materialId==null?'': data.material.code;
	    var uomName = data.material.uom.name==null?'': data.material.uom.name;
		var description =data.material.description==null?'': data.material.description;
		var hsnCode =  data.material.code==null?'':data.material.code;
		var reqQuantity =  data.quantity==null?'':data.quantity;
		var specVersion =  data.materialVersion==null?'':data.materialVersion.name;
		var tenderMatType =  data.materialTypeCode==null?'':data.materialTypeCode;
		$("#itemDetailForm #tahdrMaterialId").val(tahdrMaterialId);
		$("#itemDetailForm #matName").html(name);
		$("#itemDetailForm #matCode").html(code);
		$("#itemDetailForm #uom").html(uomName);
		$("#itemDetailForm #description").html(description);
		
		$("#itemDetailForm #reqQuantity").html(reqQuantity);
		$("#itemDetailForm #specVersion").html(specVersion);
		$("#itemDetailForm #tenderMatType").html(tenderMatType);
}
}
function showTahdrMaterialDetail(el){
var tahdrMaterialId=$(el).attr('id');
loadTahdrMaterialRightPane(tahdrMaterialArray['tahdrMaterial_'+tahdrMaterialId]);
}

function loadBidderListForView(data){
	$('.pagination').children().remove();
    $("#leftPane").html("");
	var leftPanelHtml="";
	var i=0;
	var firstRow=null;
	if(!$.isEmptyObject(data.objectMap.bidTypeCode)){
		tenderBidType=data.objectMap.bidTypeCode;
	}
	if(!$.isEmptyObject(data.objectMap.responseList)){
		$.each(data.objectMap.responseList,function(key,bidder){
				    /*var bidderId = value.itemBid==null?'':value.itemBid.bidder.bidderId==null?'':value.itemBid.bidder.bidderId;
				    var bidType=value.itemBid==null?'':value.itemBid.bidder==null?'':value.itemBid.bidder.tahdr==null?'':value.itemBid.bidder.tahdr.tahdrStatusCode==null?'':value.itemBid.bidder.tahdr.tahdrStatusCode;
					var bidderName=value.partner==null?'':value.partner.name==null?'':value.partner.name;
					var title=value.itemBid==null?'':value.itemBid.bidder==null?'':value.itemBid.bidder.tahdr==null?'':value.itemBid.bidder.tahdr.title==null?'':value.itemBid.bidder.tahdr.title;
					var tenderNo=value.itemBid==null?'':value.itemBid.bidder==null?'':value.itemBid.bidder.tahdr==null?'':value.itemBid.bidder.tahdr.tahdrCode==null?'':value.itemBid.bidder.tahdr.tahdrCode;*/
					
					
					var tahdr=bidder==null?'':bidder.tahdr;
					
					var bidderId = bidder==null?'':bidder.bidderId;
				    var bidType=tahdr==null?'':tahdr.tahdrStatusCode==null?'':tahdr.tahdrStatusCode;
					var bidderName=bidder.partner==null?'':bidder.partner.name==null?'':bidder.partner.name;
					var title=tahdr==null?'':tahdr.title==null?'':tahdr.title;
					var tenderNo=tahdr==null?'':tahdr.tahdrCode==null?'':tahdr.tahdrCode;
			
					bidType=tenderStatusList[bidType];
						
					bidderBidDetailArray['bidder_'+bidderId]=bidder;
					if(i==0){
						firstRow = bidder;
						leftPanelHtml = leftPanelHtml + ' <li class="active" onclick="showBidderBid('+bidderId+')" id="'+bidderId+'">';
					}else{
						leftPanelHtml = leftPanelHtml + ' <li onclick="showBidderBid('+bidderId+')" id="'+bidderId+'">';
					}
				
					leftPanelHtml = leftPanelHtml +' <a href="#Master" data-toggle="tab">'
				    +' <div class="col-md-12">'
				    +'  <label class="col-xs-6" id="'+bidderId+'_name">'+bidderName+'</label>'
				    +'	<label class="col-xs-6" id="'+bidderId+'_tenderNo">'+tenderNo+'</label>'
				    +' </div>'	
				    +' <div class="col-md-12">'
				    +'	<label class="col-xs-6" id="'+bidderId+'_bidType">'+bidType+'</label>'
					+'	<label class="col-xs-6" id="'+bidderId+'_title">'+title+'</label>'
					+' </div>'
				    +' </a>'
					i++;
		});
	}
	$("#leftPane").html(leftPanelHtml);
	loadBidderListRightPane(firstRow);
	$('.example').paginathing({perPage: 6});
	}
function loadBidderListRightPane(data){
	  
	$('.pagination').children().remove();
	if(!$.isEmptyObject(data)){
		 
		    var partnerId = data.partner==null?'':data.partner.bPartnerId;
		    var status=data==null?'':data.status==null?'':data.status;
			var bidderName=data.partner==null?'':data.partner.name==null?'':data.partner.name;
			var title=data.tahdr==null?'':data.tahdr.title==null?'':data.tahdr.title;
			var tenderNo=data.tahdr==null?'':data.tahdr.tahdrCode==null?'':data.tahdr.tahdrCode;
	
			
			$('#viewBidForm #bidderName').html(bidderName);
			$('#viewBidForm #tenderName').html(title);
			$('#viewBidForm #tenderNo').html(tenderNo);
			
			var bidderId=data.bidderId;
			var tahdrId=data.tahdr.tahdrId;
			submitWithThreeParam("getOpenedBids",bidderId,tahdrId,"tahdrMaterialId","populateBidDocuments");
			
			/*if(status=='TCOP'){
					$('#viewBidForm  #tcDivId').removeAttr('style');
					$('#viewBidForm  #otherDivId').attr('style','display:none ;');
					$('#tclabelId').html('Downlod Technical Bid: ');
					$('#tcclabelId').html('Download Commercial Details: ');
					$('#technicalDivId').html('');  
					var formDataHtml='';
					var count=1;
						 
						var materialName=data.itemBid==null?'':data.itemBid.tahdrMaterial==null?'':data.itemBid.tahdrMaterial.material==null?'':data.itemBid.tahdrMaterial.material.name==null?'':data.itemBid.tahdrMaterial.material.name;
						var technicalDoc=data.digiSignedDoc==null?'':data.digiSignedDoc.attachmentId;
						var technicalDocName=data.digiSignedDoc==null?'':data.digiSignedDoc.fileName;
						if(technicalDoc==''){
							var downloadTechFile='<div class="col-sm-6"><a href="#" id="downloadTechDoc">No File</a></div><div class="clearfix"></div>';	
						}else
						var downloadTechFile='<div class="col-sm-6"><a href="/eatApp/download/'+technicalDoc+'" id="downloadTechDoc">'+technicalDocName+'</a></div><div class="clearfix"></div>';
						formDataHtml+='<div class="col-sm-6"><label>'+count+'.'+materialName+' : </label></div>'+ downloadTechFile;
						
						$('#technicalDivId').append(formDataHtml);
						formDataHtml='';
						
					var commercialDoc=data.itemBid==null?'':
					                  data.itemBid.bidder==null?'':
					                  data.itemBid.bidder.commercialBid==null?'':data.itemBid.bidder.commercialBid.digiSignedDoc==null?'':data.itemBid.bidder.commercialBid.digiSignedDoc.attachmentId;
					
					var link=$('#downloadCommDoc').data('url');
					if(commercialDoc!=''){
						$('#downloadCommDoc').html('Commercial Bid');
						$('#downloadCommDoc').attr('href',link+'/'+commercialDoc);
					}else{
						$('#downloadCommDoc').html('No File');
					}
					
				}
			else if(status=='PBOP'){
				    $('#viewBidForm  #otherDivId').removeAttr('style');
					$('#viewBidForm  #tcDivId').attr('style','display:none ;');
					$('#viewBidForm  #labelId').html('Download Price Bid :');
					$('#otherBidTypeDivId').html('');  
					var formDataHtml='';
					var count=1;
					
					    var materialName=data.itemBid==null?'':data.itemBid.tahdrMaterial==null?'':data.itemBid.tahdrMaterial.material==null?'':data.itemBid.tahdrMaterial.material.name==null?'':data.itemBid.tahdrMaterial.material.name;
						var priceBidDoc=data.digiSignedDoc==null?'':data.digiSignedDoc.attachmentId;
						var priceBidDocName=data.digiSignedDoc==null?'':data.digiSignedDoc.fileName;
						if(priceBidDoc==''){
							var downloadTechFile='<div class="col-sm-6"><a href="#" id="downloadTechDoc">No File</a></div><div class="clearfix"></div>';	
						}else
						var downloadTechFile='<div class="col-sm-6"><a href="/eatApp/download/'+priceBidDoc+'" id="downloadTechDoc">'+priceBidDocName+'</a></div><div class="clearfix"></div>';
						formDataHtml+='<div class="col-sm-6"><label>'+count+'.'+materialName+' : </label></div>'+ downloadTechFile;
						
						$('#otherBidTypeDivId').append(formDataHtml);
						formDataHtml='';
						
				}
			else if(status=='C1OP'){
				    $('#viewBidForm  #otherDivId').removeAttr('style');
					$('#viewBidForm  #tcDivId').attr('style','display:none ;');
					$('#viewBidForm  #labelId').html('Download Annexure C1 :');
					$('#otherBidTypeDivId').html('');  
					var formDataHtml='';
					var count=1;
				
					    var materialName=data.itemBid==null?'':data.itemBid.tahdrMaterial==null?'':data.itemBid.tahdrMaterial.material==null?'':data.itemBid.tahdrMaterial.material.name==null?'':data.itemBid.tahdrMaterial.material.name;
						var annexureDoc=data.digiSignedDoc==null?'':data.digiSignedDoc.attachmentId;
						var annexureDocName=data.digiSignedDoc==null?'':data.digiSignedDoc.fileName;
						if(annexureDoc==''){
							var downloadTechFile='<div class="col-sm-6"><a href="#" id="downloadTechDoc">No File</a></div><div class="clearfix"></div>';	
						}else
						var downloadTechFile='<div class="col-sm-6"><a href="/eatApp/download/'+annexureDoc+'" id="downloadTechDoc">'+annexureDocName+'</a></div><div class="clearfix"></div>';
						formDataHtml+='<div class="col-sm-6"><label>'+count+'.'+materialName+' : </label></div>'+ downloadTechFile;
						
						$('#otherBidTypeDivId').append(formDataHtml);
						formDataHtml='';
			   }*/
			
		}
	else{
		$('#viewBidForm #bidderName').html('');
		$('#viewBidForm #tenderName').html('');
		$('#viewBidForm #tenderNo').html('');
		
		$('#viewBidForm  #otherDivId').removeAttr('style');
		$('#viewBidForm  #tcDivId').attr('style','display:none ;');
		$('#viewBidForm  #labelId').html('');
		$('#otherBidTypeDivId').html(''); 
		
		$('#downloadCommDoc').html('');
		$('#downloadCommDoc').attr('href','#');
	}
	$('.example').paginathing({perPage: 6});
	
	}
function loadBidderListForViewDb(data){
	  
	$('.pagination').children().remove();
    $("#leftPane").html("");
	var leftPanelHtml="";
	var i=0;
	var firstRow=null;
	if(!$.isEmptyObject(data.objectMap.responseList)){
		$.each(data.objectMap.responseList,function(key,value){
			  
				    var bidderId = value.bidderId==null?'':value.bidderId;
					var bidderName=value.partner==null?'':value.partner.name==null?'':value.partner.name;
					var title=value.tahdr==null?'':value.tahdr.title==null?'':value.tahdr.title;
					var tenderNo=value.tahdr==null?'':value.tahdr.tahdrCode==null?'':value.tahdr.tahdrCode;
					var bidType=value.tahdr.tahdrStatusCode==null?'':value.tahdr.tahdrStatusCode;
					bidType=tenderStatusList[bidType];
					bidderBidDetailArray['bidder_'+bidderId]=value;
					if(i==0){
						firstRow = value;
						leftPanelHtml = leftPanelHtml + ' <li class="active" onclick="showBidderBid('+bidderId+')" id="'+bidderId+'">';
					}else{
						leftPanelHtml = leftPanelHtml + ' <li onclick="showBidderBid('+bidderId+')" id="'+bidderId+'">';
					}
				
					leftPanelHtml = leftPanelHtml +' <a href="#Master" data-toggle="tab">'
				    +' <div class="col-md-12">'
				    +'  <label class="col-xs-6" id="'+bidderId+'_name">'+bidderName+'</label>'
				    +'	<label class="col-xs-6" id="'+bidderId+'_tenderNo">'+tenderNo+'</label>'
				    +' </div>'	
				    +' <div class="col-md-12">'
				    +'	<label class="col-xs-6" id="'+bidderId+'_bidType">'+bidType+'</label>'
					+'	<label class="col-xs-6" id="'+bidderId+'_title">'+title+'</label>'
					+' </div>'
				    +' </a>'
					i++;
		});
	}
	$("#leftPane").html(leftPanelHtml);
	loadBidderListRightPaneDb(firstRow);
	$('.example').paginathing({perPage: 6});
	}
function loadBidderListRightPaneDb(data){
	  
	$('.pagination').children().remove();
	if(!$.isEmptyObject(data)){
		    var bidderId = data.partner==null?'':data.partner.bPartnerId;
		    var status=data.status==null?'':data.status;
			var bidderName=data.partner==null?'':data.partner.name==null?'':data.partner.name;
			var title=data.tahdr==null?'':data.tahdr.title==null?'':data.tahdr.title;
			var tenderNo=data.tahdr==null?'':data.tahdr.tahdrCode==null?'':data.tahdr.tahdrCode;
	
			
			$('#viewBidForm #bidderName').html(bidderName);
			$('#viewBidForm #tenderName').html(title);
			$('#viewBidForm #tenderNo').html(tenderNo);
			if(status=='DBOP'){
				$('#viewBidForm  #tcDivId').removeAttr('style');
				$('#viewBidForm  #otherDivId').attr('style','display:none ;');
				$('#tclabelId').html('Download Technical Deviation: ');
				$('#tcclabelId').html('Download Commercial Deviation: ');
					$('#technicalDivId').html('');  
					var formDataHtml='';
					var count=1;
					$.each(data.itemScrutinyList,function(key,value){
						if(value.scrutinyType=='TECHSCR'){
								var materialName=value.itemBid.tahdrMaterial==null?'':value.itemBid.tahdrMaterial.material==null?'':value.itemBid.tahdrMaterial.material.name;
								var deviationDoc=value.scrutinyFile==null?'':value.scrutinyFile.attachmentId;
								var deviationDocName=value.scrutinyFile==null?'':value.scrutinyFile.fileName;
								if(deviationDoc==''){
									var downloadTechFile='<div class="col-sm-6"><a href="#" id="downloadTechDoc">No Deviation</a></div><div class="clearfix"></div>';	
								}else
								var downloadTechFile='<div class="col-sm-6"><a href="/eatApp/download/'+deviationDoc+'" id="downloadTechDoc">'+deviationDocName+'</a></div><div class="clearfix"></div>';
								formDataHtml+='<div class="col-sm-6"><label>'+count+'.'+materialName+' : </label></div>'
								+ downloadTechFile;
								
								$('#technicalDivId').append(formDataHtml);
								formDataHtml='';
								count++;
							}
						/*else{
							var commercialDeviationDoc=value.scrutinyFile==null?'':value.scrutinyFile.attachmentId;
							var commercialDeviationDocName=value.scrutinyFile==null?'':value.scrutinyFile.fileName;
							var link=$('#downloadCommDoc').data('url');
							if(commercialDeviationDoc!=''){
								$('#downloadCommDoc').html(commercialDeviationDocName);
								$('#downloadCommDoc').attr('href',link+'/'+commercialDeviationDoc);
							}else{
								$('#downloadCommDoc').html('No File');
								$('#downloadCommDoc').removeAttr('href');
							}
						}*/
					});
			    }
		}
	else{
		$('#viewBidForm #bidderName').html('');
		$('#viewBidForm #tenderName').html('');
		$('#viewBidForm #tenderNo').html('');
		
		$('#viewBidForm  #tcDivId').removeAttr('style');
		$('#viewBidForm  #otherDivId').attr('style','display:none ;');
		$('#tclabelId').html('');
		$('#tcclabelId').html('');
		$('#technicalDivId').html('');  
		
		$('#downloadCommDoc').html('');
		$('#downloadCommDoc').attr('href','#');
	}
	$('.example').paginathing({perPage: 6});
	
	}
function loadBidderListForComercialBidView(data){
	$('.pagination').children().remove();
    $("#leftPane").html("");
	var leftPanelHtml="";
	var i=0;
	var firstRow=null;
	if(!$.isEmptyObject(data.objectMap.responseList)){
		$.each(data.objectMap.responseList,function(key,value){
				    var bidderId = value.bidderId==null?'':value.bidderId;
				    var bidType=value.tahdr==null?'':value.tahdr.tahdrStatusCode==null?'':value.tahdr.tahdrStatusCode;
					var bidderName=value.partner==null?'':value.partner.name==null?'':value.partner.name;
					var title=value.tahdr==null?'':value.tahdr.title==null?'':value.tahdr.title;
					var tenderNo=value.tahdr==null?'':value.tahdr.tahdrCode==null?'':value.tahdr.tahdrCode;
			
					bidType=tenderStatusList[bidType];
						
					bidderBidDetailArray['bidder_'+bidderId]=value;
					if(i==0){
						firstRow = value;
						leftPanelHtml = leftPanelHtml + ' <li class="active" onclick="showCommercialBidderBid('+bidderId+')" id="'+bidderId+'">';
					}else{
						leftPanelHtml = leftPanelHtml + ' <li onclick="showCommercialBidderBid('+bidderId+')" id="'+bidderId+'">';
					}
				
					leftPanelHtml = leftPanelHtml +' <a href="#Master" data-toggle="tab">'
				    +' <div class="col-md-12">'
				    +'  <label class="col-xs-6" id="'+bidderId+'_name">'+bidderName+'</label>'
				    +'	<label class="col-xs-6" id="'+bidderId+'_tenderNo">'+tenderNo+'</label>'
				    +' </div>'	
				    +' <div class="col-md-12">'
				    +'	<label class="col-xs-6" id="'+bidderId+'_bidType">'+bidType+'</label>'
					+'	<label class="col-xs-6" id="'+bidderId+'_title">'+title+'</label>'
					+' </div>'
				    +' </a>'
					i++;
		});
	}
	$("#leftPane").html(leftPanelHtml);
	loadBidderListForComercialBidRightView(firstRow);
	$('.example').paginathing({perPage: 6});
}
function loadBidderListForComercialBidRightView(data){
	$('.pagination').children().remove();
	if(!$.isEmptyObject(data)){
		var bidderId = data.partner==null?'':data.partner.bPartnerId;
	    var status=data.status==null?'':data.status;
		var bidderName=data.partner==null?'':data.partner.name==null?'':data.partner.name;
		var title=data.tahdr==null?'':data.tahdr.title==null?'':data.tahdr.title;
		var tenderNo=data.tahdr==null?'':data.tahdr.tahdrCode==null?'':data.tahdr.tahdrCode;
		$('#viewCommercialBidForm #bidderName').html(bidderName);
		$('#viewCommercialBidForm #tenderName').html(title);
		$('#viewCommercialBidForm #tenderNo').html(tenderNo);
		var commercialDoc='';
		var docName='';
		$.each(data.itemScrutinyList,function(key,value){
			if(value.scrutinyType=='COMMSCR'){
					commercialDoc=value.scrutinyFile==null?'':value.scrutinyFile.attachmentId;
					docName=value.scrutinyFile==null?'':value.scrutinyFile.fileName;
			   }
			});
			var link=$('#downloadCommDoc').data('url');
			if(commercialDoc!=''){
			$('#viewCommercialBidForm #downloadDoc').html(docName);
			$('#viewCommercialBidForm #downloadDoc').attr('href',link+'/'+commercialDoc);
			}else{
			$('#viewCommercialBidForm #downloadDoc').html('No Deviation');
			}
	}else{
		$('#viewCommercialBidForm #bidderName').html('');
		$('#viewCommercialBidForm #tenderName').html('');
		$('#viewCommercialBidForm #tenderNo').html('');
		
		$('#viewCommercialBidForm #downloadDoc').html('');
		$('#viewCommercialBidForm #downloadDoc').attr('href','#');
	}
	

}
function showCommercialBidderBid(bidderId){
	loadBidderListForComercialBidRightView(bidderBidDetailArray['bidder_'+bidderId]);
}
function showBidderBid(bidderId){
	 
	if(thadrBidType=='SB'){
		loadBidderListRightPaneSB(bidderBidDetailArrayTC['bidder_'+bidderId],bidderBidDetailArrayPB['bidder_'+bidderId])
	}else{
		if(role=='VENADM'){
			var vopeningType= $('#vendorTenderOpeningSearchForm #vopeningType').val();
			if(vopeningType=='DBOP'){
				loadBidderListRightPaneDb(bidderBidDetailArray['bidder_'+bidderId]);
			}else{
				loadBidderListRightPane(bidderBidDetailArray['bidder_'+bidderId]);
			}
		}else{
			var openingType= $('#tenderOpeningSearchForm #openingType').val();
			if(openingType=='DBOP'){
				loadBidderListRightPaneDb(bidderBidDetailArray['bidder_'+bidderId]);
			}else{
				loadBidderListRightPane(bidderBidDetailArray['bidder_'+bidderId]);	
			}
		}
		
	}
	
}

function loggedInUserResp(data){
	if(data.objectMap.resultStatus){
		loadLoggedInCommitteeMember(data);
	}
	else{
		Alert.info("Already Updated !");
	}
}
function loggedInBidderrResp(data){
	if(data.objectMap.resultStatus){
		loadLoggedInBidderDiv(data);
	}
	else{
		Alert.info("Already Updated !");
	}
}
function bidAndBidderCount(openType,tenderId,status){
	var data=fetchList("getBidderListByTahdrId/"+tenderId,null);
	var noOfBidder=data.objectMap.responseList==null?0:data.objectMap.responseList.length;
	if(!$.isEmptyObject(data.objectMap.responseList)){
		if(openType=='TCOP'){
			if(status=='PU'){ 
				$.each(data.objectMap.responseList,function(key,value){
					 
					if(value.status=='SBMT'){
						bidSubmitted++;
					}
				});
			}
			else if(status=='TCOP'){
				$.each(data.objectMap.responseList,function(key,value){
					 
					if(value.status=='TCOP'){
						bidSubmitted++;
					}
				});
			}
		}
		else if(openType=='DBOP'){
			if(status=='DBSCH'){ 
				$.each(data.objectMap.responseList,function(key,value){
					 
					if(value.status=='DBSU'){
						bidSubmitted++;
					}
				});
			}
			else if(status=='DBOP'){
				$.each(data.objectMap.responseList,function(key,value){
					 
					if(value.status=='DBOP'){
						bidSubmitted++;
					}
				});
			}
		}
		else if(openType=='PBOP' || openType=='SBPBOP'){
			if(status=='PU' || status=='RBSCH' || status=='PBSCH'){ 
				$.each(data.objectMap.responseList,function(key,value){
					if(value.status=='SBMT' || value.status=='DBSU' || value.status=='FCBP' || value.status=='FTBP' || value.status=='PASS' ){
						bidSubmitted++;
					}
				});
			}
			else if(status=='PBOP'){
				 
				$.each(data.objectMap.responseList,function(key,value){
					  
					if(value.status=='PBOP'){
						bidSubmitted++;
					}
				});
			}
		}
		else if(openType=='C1OP'){
			if( status=='C1SCH'){ 
				$.each(data.objectMap.responseList,function(key,value){
					 
					if(value.status=='C1SU'){
						bidSubmitted++;
					}
				});
			}
			else if(status=='C1OP'){
				$.each(data.objectMap.responseList,function(key,value){
					 
					if(value.status=='C1OP'){
						bidSubmitted++;
					}
				});
			}
		}
		
	}
	$("#tenderOpeningForm #noBidder").html(noOfBidder);
}
function tenderLogoutResp(data){
	 
	if(data.objectMap.result==true){
		Alert.info(data.objectMap.resultMessage);
		location.reload();
	}else{
		Alert.warn(data.objectMap.resultMessage);
	}
}
function loadBidderListForViewSB(data){
	  
	$('.pagination').children().remove();
    $("#leftPane").html("");
	var leftPanelHtml="";
	var i=0;
	var firstRow1=null;
	var firstRow2=null;
	if(!$.isEmptyObject(data.objectMap.bidTypeCode)){
		tenderBidType=data.objectMap.bidTypeCode;
	}
	if(!$.isEmptyObject(data.objectMap.responseList))
	{
		$.each(data.objectMap.responseList,function(key,value){
				    var bidderId = value.itemBid==null?'':value.itemBid.bidder.bidderId==null?'':value.itemBid.bidder.bidderId;
				    var bidType=value.itemBid==null?'':value.itemBid.bidder==null?'':value.itemBid.bidder.tahdr==null?'':value.itemBid.bidder.tahdr.tahdrStatusCode==null?'':value.itemBid.bidder.tahdr.tahdrStatusCode;
					var bidderName=value.partner==null?'':value.partner.name==null?'':value.partner.name;
					var title=value.itemBid==null?'':value.itemBid.bidder==null?'':value.itemBid.bidder.tahdr==null?'':value.itemBid.bidder.tahdr.title==null?'':value.itemBid.bidder.tahdr.title;
					var tenderNo=value.itemBid==null?'':value.itemBid.bidder==null?'':value.itemBid.bidder.tahdr==null?'':value.itemBid.bidder.tahdr.tahdrCode==null?'':value.itemBid.bidder.tahdr.tahdrCode;
			
					bidType=tenderStatusList[bidType];
						
					bidderBidDetailArrayPB['bidder_'+bidderId]=value;
					if(i==0){
						firstRow1 = value;
						leftPanelHtml = leftPanelHtml + ' <li class="active" onclick="showBidderBid('+bidderId+')" id="'+bidderId+'">';
					}else{
						leftPanelHtml = leftPanelHtml + ' <li onclick="showBidderBid('+bidderId+')" id="'+bidderId+'">';
					}
				
					leftPanelHtml = leftPanelHtml +' <a href="#Master" data-toggle="tab">'
				    +' <div class="col-md-12">'
				    +'  <label class="col-xs-6" id="'+bidderId+'_name">'+bidderName+'</label>'
				    +'	<label class="col-xs-6" id="'+bidderId+'_tenderNo">'+tenderNo+'</label>'
				    +' </div>'	
				    +' <div class="col-md-12">'
				    +'	<label class="col-xs-6" id="'+bidderId+'_bidType">'+bidType+'</label>'
					+'	<label class="col-xs-6" id="'+bidderId+'_title">'+title+'</label>'
					+' </div>'
				    +' </a>'
					i++;
		});
	}
	if(!$.isEmptyObject(data.objectMap.responseTcList))
	{
		$.each(data.objectMap.responseTcList,function(key,value){
				    var bidderId = value.itemBid==null?'':value.itemBid.bidder.bidderId==null?'':value.itemBid.bidder.bidderId;
						
					bidderBidDetailArrayTC['bidder_'+bidderId]=value;
					firstRow2 = value;
					
		});
	}
	$("#leftPane").html(leftPanelHtml);
	loadBidderListRightPaneSB(firstRow2,firstRow1);
	$('.example').paginathing({perPage: 6});
	}
function loadBidderListRightPaneSB(data1,data2)
{
	  
	$('.pagination').children().remove();
	if(!$.isEmptyObject(data1) && !$.isEmptyObject(data2)){
		 
		    var partnerId = data1.partner==null?'':data1.partner.bPartnerId;
		    var status=data1.itemBid==null?'':data1.itemBid.bidder==null?'':data1.itemBid.bidder.status==null?'':data1.itemBid.bidder.status;
			var bidderName=data1.partner==null?'':data1.partner.name==null?'':data1.partner.name;
			var title=data1.itemBid==null?'':data1.itemBid.bidder==null?'':data1.itemBid.bidder.tahdr==null?'':data1.itemBid.bidder.tahdr.title==null?'':data1.itemBid.bidder.tahdr.title;
			var tenderNo=data1.itemBid==null?'':data1.itemBid.bidder==null?'':data1.itemBid.bidder.tahdr==null?'':data1.itemBid.bidder.tahdr.tahdrCode==null?'':data1.itemBid.bidder.tahdr.tahdrCode;
	
			
			$('#viewBidForm #bidderName').html(bidderName);
			$('#viewBidForm #tenderName').html(title);
			$('#viewBidForm #tenderNo').html(tenderNo);
			
			
					$('#viewBidForm  #tcDivId').removeAttr('style');
					$('#viewBidForm  #otherDivId').attr('style','display:none ;');
					$('#tclabelId').html('Downlod Technical Bid: ');
					$('#tcclabelId').html('Download Commercial Details: ');
					$('#technicalDivId').html('');  
					var formDataHtml='';
					var count=1;
					/*$.each(data,function(key,value){
*/						 
						var materialName=data2.itemBid==null?'':data2.itemBid.tahdrMaterial==null?'':data2.itemBid.tahdrMaterial.material==null?'':data2.itemBid.tahdrMaterial.material.name==null?'':data2.itemBid.tahdrMaterial.material.name;
						var technicalDoc=data2.digiSignedDoc==null?'':data2.digiSignedDoc.attachmentId;
						if(technicalDoc==''){
							var downloadTechFile='<div class="col-sm-6"><a href="#" id="downloadTechDoc">No File</a></div><div class="clearfix"></div>';	
						}else
						var downloadTechFile='<div class="col-sm-6"><a href="/eatApp/download/'+technicalDoc+'" id="downloadTechDoc">TechnicalBid-'+count+'</a></div><div class="clearfix"></div>';
						formDataHtml+='<div class="col-sm-6"><label>'+count+'.'+materialName+' : </label></div>'
						/*+'<div class="col-sm-6"><a href="/eatApp/download/'+deviationDoc+'" id="downloadTechDoc">Deviation-'+count+'</a></div><div class="clearfix"></div>';*/
						+ downloadTechFile;
						/*formDataHtml+='<div class="col-sm-6"><label>'+count+'. '+materialName+' : </label></div>'
						+'<div class="col-sm-6"><a href="/eatApp/download/'+technicalDoc+'" id="downloadTechDoc">Technical Bid-'+count+'</a></div><div class="clearfix"></div>';
						*/
						$('#technicalDivId').append(formDataHtml);
						formDataHtml='';
						/*count++;*/
					/*});*/
					var commercialDoc=data1.itemBid==null?'':
						data1.itemBid.bidder==null?'':
							data1.itemBid.bidder.commercialBid==null?'':data1.itemBid.bidder.commercialBid.digiSignedDoc==null?'':data1.itemBid.bidder.commercialBid.digiSignedDoc.attachmentId;
					
					var link=$('#downloadCommDoc').data('url');
					if(commercialDoc!=''){
						$('#downloadCommDoc').html('Commercial Bid');
						$('#downloadCommDoc').attr('href',link+'/'+commercialDoc);
					}else{
						$('#downloadCommDoc').removeAttr('href');
						$('#downloadCommDoc').html('No File');
					}
					
			
				    $('#viewBidForm  #otherDivId').removeAttr('style');/*
					$('#viewBidForm  #tcDivId').attr('style','display:none ;');*/
					$('#viewBidForm  #labelId').html('Download Price Bid :');
					$('#otherBidTypeDivId').html('');  
					var formDataHtml='';
					var count=1;
					/*$.each(data.itemBidList,function(key,value){*/
						/*var materialName=data.tahdrMaterial==null?'':data.tahdrMaterial.material==null?'':data.tahdrMaterial.material.name==null?'':data.tahdrMaterial.material.name;*/
					    var materialName=data1.itemBid==null?'':data1.itemBid.tahdrMaterial==null?'':data1.itemBid.tahdrMaterial.material==null?'':data1.itemBid.tahdrMaterial.material.name==null?'':data1.itemBid.tahdrMaterial.material.name;
						var priceBidDoc=data1.digiSignedDoc==null?'':data1.digiSignedDoc.attachmentId;
						if(priceBidDoc==''){
							var downloadTechFile='<div class="col-sm-6"><a href="#" id="downloadTechDoc">No File</a></div><div class="clearfix"></div>';	
						}else
						var downloadTechFile='<div class="col-sm-6"><a href="/eatApp/download/'+priceBidDoc+'" id="downloadTechDoc">PriceBid-'+count+'</a></div><div class="clearfix"></div>';
						formDataHtml+='<div class="col-sm-6"><label>'+count+'.'+materialName+' : </label></div>'
						/*+'<div class="col-sm-6"><a href="/eatApp/download/'+deviationDoc+'" id="downloadTechDoc">Deviation-'+count+'</a></div><div class="clearfix"></div>';*/
						+ downloadTechFile;
						/*formDataHtml+='<div class="col-sm-6"><label>'+count+'. '+materialName+' : </label></div>'
						+'<div class="col-sm-6"><a href="/eatApp/download/'+priceBidDoc+'" id="downloadTechDoc">Price Bid-'+count+'</a></div><div class="clearfix"></div>';
						*/
						$('#otherBidTypeDivId').append(formDataHtml);
						formDataHtml='';
						/*count++;
					});*/
					
		}
	else{
		$('#viewBidForm #bidderName').html('');
		$('#viewBidForm #tenderName').html('');
		$('#viewBidForm #tenderNo').html('');
		
		$('#viewBidForm  #otherDivId').removeAttr('style');
		$('#viewBidForm  #tcDivId').attr('style','display:none ;');
		$('#viewBidForm  #labelId').html('');
		$('#otherBidTypeDivId').html(''); 
		
		$('#downloadCommDoc').html('');
		$('#downloadCommDoc').attr('href','#');
	}
	$('.example').paginathing({perPage: 6});
	
	}


function populateBidDocuments(response){
	var bidDocs;
	if(!isEmpty(response)){
		bidDocs=response.objectMap;
		if(!isEmpty(bidDocs)){
			var tb=bidDocs.technicalBid;
			var cb=bidDocs.commercialBid;
			if(!isEmpty(tb) && !isEmpty(cb)){
				var tbAtt=tb.digiSignedDoc;
				var cbAtt=cb.digiSignedDoc;
				if(!isEmpty(tbAtt) && !isEmpty(cbAtt)){
					
					$("#tcDivId").show();
					if(!isEmpty(tbAtt)){
						var tbHref=$("#downloadTechDoc").data("url");
						$("#downloadTechDoc").attr("href",tbHref+"/"+tbAtt.attachmentId);
						$("#downloadTechDoc").html(tbAtt.name);
					}
					
					if(!isEmpty(cbAtt)){
						var cbHref=$("#downloadCommDoc").data("url");
						$("#downloadCommDoc").attr("href",cbHref+"/"+cbAtt.attachmentId);
						$("#downloadCommDoc").html(cbAtt.name);
					}
					
				}
			}
			

			var pb=bidDocs.priceBid
			if(!isEmpty(pb)){
				var pbAtt=pb.digiSignedDoc;
				if(!isEmpty(pbAtt)){
					$("#pbDivId").show();
					if(!isEmpty(pbAtt)){
						var pbHref=$("#downloadPBDoc").data("url");
						$("#downloadPBDoc").attr("href",pbHref+"/"+pbAtt.attachmentId);
						$("#downloadPBDoc").html(pbAtt.name);
					}
					
				}
			}
			
			var c1Bid=bidDocs.annexureC1Bid;
			if(!isEmpty(c1Bid)){
				var c1Att=c1Bid.digiSignedDoc;
				if(!isEmpty(c1Att)){
					$("#c1DivId").show();
					if(!isEmpty(c1Att)){
						var c1Href=$("#downloadC1Doc").data("url");
						$("#downloadC1Doc").attr("href",c1Href+"/"+pbAtt.attachmentId);
						$("#downloadC1Doc").html(c1Att.name);
					}
					
				}
			}
		}
	}
}

