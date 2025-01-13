/**
 * Aman Sahu
 */

var tenderArray=new Array();
var bidderArray=new Array();
var itemBidArray= new Array();
var bidderGtp=new Array();
var bidderSecDoc=new Array();
var dataDocSec=[];
var scrutinyPoint=new Array();
var statusList=new Array();
var bidderStatusList=new Array();
var techStatus=new Array();
var commStatus=new Array();
var auditStatus=new Array();
var role='';

var tenderCode='';
var tenderVersion='';
var tenderDescription='';
var tenderEmdFee='';

var bidderName='';
var bidderPanno='';
var bidderGstNo='';
var bidderCrn='';
var tenderTypeCode='';

var matName='';
var matHsnCode='';

var leftPanePageSize = 7;
var maxVisiblePageNumbers = 3;
var selectedPage = 1;
var pageSize=7;
var searchMode='none';
var searchValue='none';
var LastPage='';
var documentType='';
$(document).ready(function(){
	/*$('.leftPaneData').paginathing({perPage: 6});
*/	 $(".tabs-left li a label").text(function(index, currentText) {
		    return currentText.substr(0, 20);
		});
	 var dataUrl=$('#myTenderUrl').val();
		if(dataUrl==null || dataUrl=='' || dataUrl ==undefined ){
			 onPageLoad();
		}else{
			$('#worksToggle').removeClass('active');
			$('#worksCheckBoxId').removeAttr('checked');
			$('#auctionsCheckBoxId').removeAttr('checked');
			submitToURL(dataUrl, "loadMyTenderList");
			setToggle(tenderTypeCode);
		}
	
	documentType=$(".documentType").val();
	$("#tenderDetailTab").on('click',function(event){
		 
		event.preventDefault();
		cacheLi();
		setCurrentTab(this);
		$('.pagination').html('');
		if(getChangedFlag()){
			var data=fetchTenderList(1, pageSize, searchMode, searchValue,$('input[name=tenderTypeCodeToggle]:checked').val());
			loadTenderList(data);
			LastPage=data.objectMap.LastPage;
			setPagination(data.objectMap.LastPage, selectedPage , maxVisiblePageNumbers);
			/*submitToURL("getTenderListForScrutiny/"+$('input[name=tenderTypeCodeToggle]:checked').val(), "loadTenderListForScrutiny");*/
			$('.scrutinyTabsId').addClass('readonly');
			$('.commScrutinyTabs').addClass('readonly');
			setChangedFlag(false);
		}else{
			getCacheLi();
			var documentType=$(".documentType").val();
			setHeaderValues(documentType+" Code: " +tenderCode , documentType+" Version : "+tenderVersion, "Description: "+tenderDescription, "EMD Fees : "+tenderEmdFee);
			setPagination(LastPage, selectedPage , maxVisiblePageNumbers);
		}
	});
	if($('.leftPaneData li').length==0){
		  $('.ulId').addClass('readonly');
		  $('.scrutinyTabsId').addClass('readonly');
		}
	$("input[name='tenderTypeCodeToggle']").on("change",function(){
		selectedPage=1;
		$('.pagination').html('');
		var data=fetchTenderList(1, pageSize, searchMode, searchValue,$(this).val());
		loadTenderList(data);
		LastPage=data.objectMap.LastPage;
		setPagination(data.objectMap.LastPage, selectedPage , maxVisiblePageNumbers);
		/*var tahdrList=submitToURL("getTenderListForScrutiny/"+$(this).val(), "loadTenderListForScrutiny");*/
		if($('.leftPaneData li').length==0){
			$('.ulId').addClass('readonly');
			$('.scrutinyTabsId').addClass('readonly');
			$('#tahdrDetailForm')[0].reset();
			$('#bidderDetailForm')[0].reset();
			$('#itemDetailForm')[0].reset();
			$('#PTSGtpForm')[0].reset();
			$('#PTSDocForm')[0].reset();
		}else
			$('.ulId').removeClass('readonly');
		setCurrentTab($("#tenderDetailTab")[0]);
	    setChangedFlag(true);
		});
	/*$('.leftPaneData').paginathing({perPage: 6});*/
	
	$('#pagination-here').paginate({
		pageSize:  7,
		dataSource: 'fetchTenderList',
		responseTo:  'loadTenderListPagination',
		maxVisiblePageNumbers:3,
		searchBoxID : 'searchLiteralId',
		loadOnStartup: false
	});
});


/*function intimidateAuditor(event){
	  
	var tahdrName=$('#addAuditorForm #tahdrName').val();
	var auditorId=$('#addAuditorForm #auditorDropDownId').val();
	var tahdrId=$('#addAuditorForm #tahdrId').val();
	event.preventDefault();
	if(tahdrName!='' && auditorId!='' && tahdrId!=''){
		submitToURL("intimidateAuditor?tahdrId="+tahdrId+"&tahdrName="+tahdrName+"&auditorId="+auditorId, "intimidateAuditorResp");
	}else{
		Alert.warn('Something went wrong');
	}
}*/
function onPageLoad(){
	var typeCode=$('input[name=tenderTypeCodeToggle]:checked').val();
	var data=fetchTenderList(1, pageSize, searchMode, searchValue,typeCode);
	loadTenderList(data);
	LastPage=data.objectMap.LastPage;
	setPagination(data.objectMap.LastPage, selectedPage , maxVisiblePageNumbers);
	$('.scrutinyTabsId').addClass('readonly');
	
	setCurrentTab($("#tenderDetailTab")[0]);
    setChangedFlag(false);
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
			var dd=dt.getDate()+1;
			var MM=dt.getMonth()+1;
			var yyyy=dt.getFullYear();
			var HH= dt.getHours();
			var mm= (dt.getMinutes()<10?'0':'') + dt.getMinutes();   /*var mm= dt.getMinutes();*/
			var ss= dt.getSeconds();
		
			return dd+'-'+MM+'-'+yyyy+' '+HH+':'+mm;
	}
function loadTenderListPagination(data){
	if(!$.isEmptyObject(data.role)){
		if(data.role=='AUDIT'){
			$('.auditClass').addClass('readonly');
			$('.auditHidClass').hide();
		}
		role=data.role;
    }
	loadTenderListForScrutiny(data);
}

function loadTenderList(data){
	data=data.data;
	if(!$.isEmptyObject(data.role)){
		if(data.role=='AUDIT'){
			$('.auditClass').addClass('readonly');
			$('.auditHidClass').hide();
		}else{
			$('.auditNotHidClass').hide();
		}
		role=data.role;
   }
	loadTenderListForScrutiny(data);
	
}
function loadMyTenderList(data){
	data=data.objectMap;
	if(!$.isEmptyObject(data.role)){
		if(data.role=='AUDIT'){
			$('.auditClass').addClass('readonly');
			$('.auditHidClass').hide();
		}else{
			$('.auditNotHidClass').hide();
		}
		role=data.role;
    }
	loadTenderListForScrutiny(data);
}

function loadTenderListForScrutiny(data){
		$(".leftPaneData").html("");
		var leftPanelHtml="";
		var i=0;
		var firstRow=null;
		statusList=data.statusList;
		if(!$.isEmptyObject(data.tahdrDetailList)){
				$.each(data.tahdrDetailList,function(key,value){
					if(!$.isEmptyObject(value.tahdr)){
						    var tahdrDetailId = value.tahdrDetailId==null?'': value.tahdrDetailId;
							var tahdrCode = value.tahdr.tahdrCode==null?'': value.tahdr.tahdrCode;
							var title = value.tahdr.title==null?'':value.tahdr.title;
							var version  = value.version==null?'':value.version;
							var emdFee 	  = value.emdFee==null?'':value.emdFee;
							var description  = value.description==null?'':value.description;
							var tahdrValidity = value.tahdrValidity==null?'':value.tahdrValidity;
							var contactEmailId= value.contactEmailId==null?'':value.contactEmailId;
							var minQuantity= value.minQuantity==null?'':value.minQuantity;
							var tahdrId = value.tahdr.tahdrId==null?'': value.tahdr.tahdrId;
							var isPBDSetLater=value.isPBDSetLater==null?'N':value.isPBDSetLater;
							
							tenderArray["tender_"+tahdrDetailId]=value;
							
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
						    +'	<label class="col-xs-6" id="'+tahdrDetailId+'_tahdrValidity">'+tahdrValidity+'</label>'
							+'	<label class="col-xs-6" id="'+tahdrDetailId+'_emdFee">'+emdFee+'</label>'
							+' </div>'
						    +' </a>'
						    +' </li>';
							i++;
						}
				});
			
				$('.ulId').removeClass('readonly');
		}
		else{
			$('.ulId').addClass('readonly');
			$('.scrutinyTabsId').addClass('readonly');
		}
		$(".leftPaneData").html(leftPanelHtml);
		loadTahdrDetailRightPane(firstRow);
			
		$(".tabs-left li a label").text(function(index, currentText) {
				    return currentText.substr(0, 20);
				});
}

function loadTahdrDetailRightPane(data){
		if(!$.isEmptyObject(data)){
			    var isAuditing=data.tahdr.isAuditing==null?'': data.tahdr.isAuditing;
			    var tahdrDetailId = data.tahdrDetailId==null?'': data.tahdrDetailId;
				var tahdrCode = data.tahdr.tahdrCode==null?'': data.tahdr.tahdrCode;
				var tahdrTypeCode = data.tahdr.tahdrTypeCode==null?'': data.tahdr.tahdrTypeCode;
				var tahdrId = data.tahdr.tahdrId==null?'': data.tahdr.tahdrId;
				var title = data.tahdr.title==null?'':data.tahdr.title;
				var version  = data.version==null?'':data.version;
				var emdFee 	  = data.emdFee==null?'':data.emdFee;
				var description  = data.description==null?'':data.description;
				var tahdrValidity = data.tahdrValidity==null?'':data.tahdrValidity;
				var contactEmailId= data.contactEmailId==null?'':data.contactEmailId;
				var minQuantity= data.minQuantity==null?'':data.minQuantity;
				var isPBDSetLater=data.isPBDSetLater==null?'N':data.isPBDSetLater;
				var bidType=data.tahdr.bidTypeCode==null?'':data.tahdr.bidTypeCode;
				tenderTypeCode=tahdrTypeCode;
				tenderCode=tahdrCode;
				tenderVersion=version;
				tenderEmdFee=emdFee;
				tenderDescription=description;
				if(tahdrTypeCode=='WT'){
					$('.pTSGtpFormTabId').hide();
				}else{
					$('.pTSGtpFormTabId').show();
				}
				
				$("#tahdrDetailForm #tenderVersion").html(version);
				$("#tahdrDetailForm #tenderNo").html(tahdrCode);
				$("#tahdrDetailForm #description").html(description);
				$("#tahdrDetailForm #emdFee").html(emdFee);
				$("#tahdrDetailForm #tahdrCode").html(tahdrCode);
				$("#tahdrDetailForm #title").html(title);
				$("#tahdrDetailForm #tahdrDetailId").val(tahdrDetailId);
				
				$("#addAuditorForm #tahdrId").val(tahdrId);
				$("#addAuditorForm #tahdrName").val(tahdrCode);
				
				$("#confirmAuditingForm #tahdrId").val(tahdrId);
				$("#confirmAuditingForm #tahdrName").val(tahdrCode);
				
				$("#deviationScheduleForm #tenderNo").val(tahdrCode);
				$("#deviationScheduleForm #isPBDSetLater").val(isPBDSetLater);
				$("#deviationScheduleForm #tahdrCode").val(tahdrCode);
				$("#deviationScheduleForm #tahdrId").val(tahdrId);
				$("#deviationScheduleForm #bidType").val(bidType);
				$("#deviationScheduleForm #tahdrDetailId").val(tahdrDetailId);
				var documentType=$(".documentType").val();
				
				setHeaderValues(documentType+" Code: " +tahdrCode , documentType+" Version : "+version, "Description: "+description, "EMD Fees : "+emdFee);
				if(isAuditing=='Y'){
					if(role!='AUDIT'){
						/*$('#preliminaryScrutinyPageId').addClass('readonly');*/
						$('.preliminaryScrutinyPage').addClass('readonly');
						$('#auditingStatus').html(documentType+' IS UNDER AUDITING');
						Alert.warn(documentType+' IS UNDER AUDITING');
					}/*else{
						$('#preliminaryScrutinyPageId').addClass('readonly');
						$('#auditingStatus').html(documentType+' IS UNDER AUDITING');
						Alert.warn(documentType+' IS UNDER AUDITING');
					}*/
				}else{
					/*$('#preliminaryScrutinyPageId').removeClass('readonly');*/
					$('.preliminaryScrutinyPage').removeClass('readonly');
					$('#auditingStatus').html('');
				}
				
		}else{
			$("#tahdrDetailForm #tenderVersion").html('');
			$("#tahdrDetailForm #tenderNo").html('');
			$("#tahdrDetailForm #description").html('');
			$("#tahdrDetailForm #emdFee").html('');
			$("#tahdrDetailForm #tahdrCode").html('');
			$("#tahdrDetailForm #title").html('');
			$("#tahdrDetailForm #tahdrDetailId").val('');
			
			$("#addAuditorForm #tahdrId").val('');
			$("#addAuditorForm #tahdrName").val('');
			
			$("#confirmAuditingForm #tahdrId").val('');
			$("#confirmAuditingForm #tahdrName").val('');
			
			$("#deviationScheduleForm #tenderNo").val('');
			$("#deviationScheduleForm #isPBDSetLater").val('');
			$("#deviationScheduleForm #tahdrCode").val('');
			$("#deviationScheduleForm #tahdrId").val('');
			$("#deviationScheduleForm #bidType").val('');
			$("#deviationScheduleForm #tahdrDetailId").val('');
			setHeaderValues(documentType+" Code: " +tenderCode , documentType+" Version : "+tenderVersion, "Description: "+tenderDescription, "EMD Fees : "+emdFee);
			
		}
		setChildLoadFlag(true);
}

function showTahdrDetail(id){
	 loadTahdrDetailRightPane(tenderArray['tender_'+id]);
}
function loadBidders(ele){
	cacheLi();
	setCurrentTab(ele);
	if(getChangedFlag()){
		submitWithParam('getBidderListByTahdrDetailId','tahdrDetailId' ,'loadBidderListForScrutiny');
		setChangedFlag(false);
	}else{
		getCacheLi();
		getBidderScrutinyStatus();
	}
	setHeaderValues(documentType+" Code: " +tenderCode, "Vendor Name: "+bidderName, "GST IN: "+bidderGstNo, "CRN NO : "+bidderCrn);
}
function loadBidderListForScrutiny(data){
	$('.pagination').children().remove();
			$(".leftPaneData").html("");
			var leftPanelHtml="";
			var i=0;
			var firstRow=null;
			bidderStatusList=data.objectMap.bidderStatusList
			$.each(data.objectMap.bidderList,function(key,value){
				if(!$.isEmptyObject(value.partner)){
					    var bidderId = value.bidderId==null?'': value.bidderId;
					    var bpartnerId = value.partner.bPartnerId==null?'': value.partner.bPartnerId;
					    var name = value.partner.name==null?'': value.partner.name;
						var panno = value.partner.panNumber==null?'': value.partner.panNumber;
						var gstNo = value.partner.gstinNo==null?'':value.partner.gstinNo;
						var crnNumber  = value.partner.crnNumber==null?'':value.partner.crnNumber;
						var status=value.status==null?'': value.status;
						var bidderStatus=bidderStatusList[status];
						bidderArray["bidder_"+bidderId]=value;
						if(i==0){
							firstRow = value;
							leftPanelHtml = leftPanelHtml + ' <li class="active" onclick="showBidderDetail('+bidderId+')" id="'+bidderId+'">';
						}else{
							leftPanelHtml = leftPanelHtml + ' <li onclick="showBidderDetail('+bidderId+')" id="'+bidderId+'">';
						}
					
						leftPanelHtml = leftPanelHtml +' <a href="#Master" data-toggle="tab">'
					    +' <div class="col-md-12">'
					    +'  <label class="col-xs-6" id="'+bidderId+'_name">'+name+'</label>'
					    +'	<label class="col-xs-6" id="'+bidderId+'_panno">'+panno+'</label>'
					    +' </div>'	
					    +' <div class="col-md-12">'
					    +'	<label class="col-xs-6" id="'+bidderId+'_gstNo">'+gstNo+'</label>'
						+'	<label class="col-xs-6" id="'+bidderId+'_crnNumber">'+bidderStatus+'</label>'
						+' </div>'
					    +' </a>'
					    +' </li>';
						i++;
					}
			});
			
			$(".leftPaneData").html(leftPanelHtml);
			loadBidderDetailRightPane(firstRow);
			$('.leftPaneData').paginathing({perPage: 6});
			
			 $(".tabs-left li a label").text(function(index, currentText) {
				    return currentText.substr(0, 20);
				});
			if(!$.isEmptyObject(data.objectMap.deviationTypeCode))
				{
					loadReferenceListById('PTSGtpForm #deviationType',data.objectMap.deviationTypeCode);
					loadReferenceListById('PTSDocForm #deviationType',data.objectMap.deviationTypeCode);
					loadReferenceListById('PCSSPForm #deviationType',data.objectMap.deviationTypeCode);
					loadReferenceListById('PCSDocForm #deviationType',data.objectMap.deviationTypeCode);
				}
			if(!$.isEmptyObject(data.objectMap.role))
			{
				if(data.objectMap.role=='AUDIT'){
					$('.auditClass').addClass('readonly');
				}
			}
				
}

function loadBidderDetailRightPane(data){
	debugger;
	$("#loading-wrapper").show();
		if(!$.isEmptyObject(data)){
			    var bidderId = data.bidderId==null?'': data.bidderId;
			    var bpartnerId = data.partner.bPartnerId==null?'': data.partner.bPartnerId;
			    var name = data.partner.name==null?'': data.partner.name;
				var panno = data.partner.panNumber==null?'': data.partner.panNumber;
				var gstNo = data.partner.gstinNo==null?'':data.partner.gstinNo;
				var crnNumber  = data.partner.crnNumber==null?'':data.partner.crnNumber;
				
				var digiFileName= data.partner==null?'No Name':data.partner.partnerCoSignCopy==null?'No Name':data.partner.partnerCoSignCopy.fileName;
				var digiFileId= data.partner==null?null:data.partner.partnerCoSignCopy==null?null:data.partner.partnerCoSignCopy.attachmentId;
				var url=$('#registrationCopydownloadLinkId').data('url');
				var status = data.status==null?'': data.status;
				var bidderStatus=bidderStatusList[status];
				bidderName=name;
				bidderPanno=panno;
				bidderGstNo=gstNo;
				bidderCrn=crnNumber;
				$("#bidderDetailForm #bidderId").val(bidderId);
				$('#gennerateCommercialScrutinyDoc #bidderId').val(bidderId);
				$('#gennerateTechnicalScrutinyDoc #bidderId').val(bidderId);
				$("#bidderDetailForm #bpartnerId").val(bpartnerId);
				$("#bidderDetailForm #name").html(name);
				$("#bidderDetailForm #panno").html(panno);
				$("#bidderDetailForm #gstNo").html(gstNo);
				$("#bidderDetailForm #crnNumber").html(crnNumber);
				$("#bidderDetailForm #status").html(bidderStatus);
				
				$('#auditorConfirmForm #bidderId').val(bidderId);
				/*$("#itemDetailForm #bidderId").val(bidderId);*/
				$("#itemDetailForm .bidderId").val(bidderId);
				$("#technicalFinalScrutinyForm #bidderId").val(bidderId);
				$("#commercialFinalScrutinyForm #bidderId").val(bidderId);
				$("#financialDetailBtnId").data('patnerid',bpartnerId);
				$('.scrutinyTabsId').removeClass('readonly');
				if(digiFileId!=null && digiFileName!='No Name' ){
					$("#bidderDetailForm #registrationCopydownloadLinkId").attr('href', url+'/'+digiFileId);
					$("#bidderDetailForm #registrationCopydownloadLinkId").html(digiFileName);
				}else{
					$("#bidderDetailForm #registrationCopydownloadLinkId").removeAttr('href');
					$("#bidderDetailForm #registrationCopydownloadLinkId").html('No File');
				}
    			
				
				setHeaderValues(documentType+" Code: " +tenderCode, "Vendor Name: "+bidderName, "GST IN: "+gstNo, "CRN NO : "+crnNumber);
				createScrutiny();
				getBidderScrutinyStatus();
		}
		else{
			
			$("#bidderDetailForm #bidderId").val('');
			$("#bidderDetailForm #bpartnerId").val('');
			$("#bidderDetailForm #name").html('');
			$("#bidderDetailForm #panno").html('');
			$("#bidderDetailForm #gstNo").html('');
			$("#bidderDetailForm #crnNumber").html('');
			
			$("#itemDetailForm .bidderId").val('');
			$("#technicalFinalScrutinyForm #bidderId").val('');
			$("#commercialFinalScrutinyForm #bidderId").val('');
			$("#financialDetailBtnId").data('patnerid','');
			$('.scrutinyTabsId').addClass('readonly');
			
			$("#bidderDetailForm #registrationCopydownloadLinkId").removeAttr('href');
			$("#bidderDetailForm #registrationCopydownloadLinkId").html("");
			
			setHeaderValues(documentType+" Code: " +tenderCode, "Vendor Name: ", "GST IN: ", "CRN NO : ");
		}
		setChildLoadFlag(true);
		$("#loading-wrapper").fadeOut("slow");
}

function showBidderDetail(bidderId){
	loadBidderDetailRightPane(bidderArray["bidder_"+bidderId]);
}

function itemScrutinyResp(data){
	if(!$.isEmptyObject(data)){
			$.each(data,function(key,value){
				if(value.scrutinyType=='TECHSCR'){
						$("#PTSGtpForm #itemScrutinyId").val(value.itemScrutinyId);
						$("#gennerateTechnicalScrutinyDoc #itemScrutinyId").val(itemScrutinyId);
						$("#PTSDocForm .itemScrutinyId").val(value.itemScrutinyId);
						/*$(".itemScrutinyLineId").val(value.itemScrutinyId);*/
						$('#technicalFinalScrutinyForm #itemScrutinyId').val(value.itemScrutinyId);
					}
				if(value.scrutinyType=='COMMSCR'){
					$("#PCSTabId").data('itemscrutinyid',value.itemScrutinyId);
					$("#gennerateCommercialScrutinyDoc #itemScrutinyId").val(itemScrutinyId);
					$("#PCSSPForm .itemScrutinyId").val(value.itemScrutinyId);
					$("#PCSDocForm .itemScrutinyId").val(value.itemScrutinyId);
					/*$(".itemScrutinyLineId").val(value.itemScrutinyId);*/
					$('#commercialFinalScrutinyForm #itemScrutinyId').val(value.itemScrutinyId);
				}
			});
			$('#startScrutinyBtnId').attr('style','display: none;');
		}
	else
	 Alert.warn('Scrutiny Failed');
}

function getFinancialDocuments(el){
	cacheLi();
	setCurrentTab(el);
	/*if(getChangedFlag()){*/
		var patnerId=$(el).data('patnerid');
		submitToURL('getPartnerFinancialDetails/'+patnerId, 'setFinancailDocumentsDetails');
	/*	setChangedFlag(false);
	}else{
		getCacheLi();
	}*/
}

function setFinancailDocumentsDetails(data){
	$(".leftPaneData").html("");
	$("#pnlFinancialDetailsId").html("");
	$("#bsFinancialDetailsId").html("");
	$("#tdFinancialDetailsId").html("");
	var fileDataHtml='';
	$.each(data.objectMap.financialAttachments,function(key,value){
		if(value.finacialType=='PNL'){
				fileDataHtml+='<div class="form-group"><label class="col-sm-12" id="pl1yr">'+ value.financialYear.name
		            +'<a class="mrgleft" href="download/'+value.attachment.attachmentId+'" id="a_File_0">'+value.attachment.fileName+'</a>'
		            +'</label></div>';
				$("#pnlFinancialDetailsId").append(fileDataHtml);
				fileDataHtml='';
			}
		else if(value.finacialType=='BSA'){
				fileDataHtml+='<div class="form-group"><label class="col-sm-12"  id="bs1yr">'+value.financialYear.name
	 			            +'<a class="mrgleft" href="download/'+value.attachment.attachmentId+'" id="a_File_0">'+value.attachment.fileName+'</a>'
	 			            +'</label></div>';
				$("#bsFinancialDetailsId").append(fileDataHtml);
				fileDataHtml='';
		   }
		else if(value.finacialType=='TD'){
				fileDataHtml+='<div class="form-group"><label>Turnover Certificate for last five years(CA Certified) : '
	     			+'<a class="mrgleft" href="download/'+value.attachment.attachmentId+'" id="a_File_0">'+value.attachment.fileName+'</a></div>'
		            +'<div class="form-group"><label>Latest Annual Turnover Amount in Rs. Lakhs : '
	         		+'<span style="font-weight:normal;" id="turnOverAmount">'+value.amount+'</span>'
	         		+'</label></div>';
				$("#tdFinancialDetailsId").append(fileDataHtml);
				fileDataHtml='';
		    }
	});
	/*setChildLoadFlag(true);*/
}

function loadItems(ele){
	cacheLi();
	setCurrentTab(ele);
	if(getChangedFlag()){
		submitWithParam('getItemListBybidderId','bidderId' ,'loadItemListForScrutiny');
		setChangedFlag(false);
	}else{
		getCacheLi();
		setHeaderValues(documentType+" Code:" +tenderCode, "Vendor Name: "+bidderName,"Material Name:"+matName, "HSN Code:"+matHsnCode);
	}
	
}

function loadItemListForScrutiny(data){
	$('.pagination').children().remove();
	$(".leftPaneData").html("");
	var leftPanelHtml="";
	var i=0;
	var firstRow=null;
	$.each(data.objectMap.ItemList,function(key,value){
		if(!$.isEmptyObject(value.itemBid.tahdrMaterial)){
				var itemBidId = value.itemBid.itemBidId==null?'': value.itemBid.itemBidId;
			    var name = value.itemBid.tahdrMaterial.material.name==null?'': value.itemBid.tahdrMaterial.material.name;
			    var uomName = value.itemBid.tahdrMaterial.material.uom.name==null?'': value.itemBid.tahdrMaterial.material.uom.name;
				var description = value.itemBid.tahdrMaterial.material.description==null?'':  value.itemBid.tahdrMaterial.material.description;
				var hsnCode = value.itemBid.tahdrMaterial.material==null?'': value.itemBid.tahdrMaterial.material.hsnCode==null?'':value.itemBid.tahdrMaterial.material.hsnCode.code==null?'':value.itemBid.tahdrMaterial.material.hsnCode.code;
				
				itemBidArray["itemBid_"+itemBidId]=value;
				if(i==0){
					firstRow = value;
					
					leftPanelHtml = leftPanelHtml + ' <li class="active" id="'+itemBidId+'"  onclick="showItemDetail(this)">';
				}else{
					leftPanelHtml = leftPanelHtml + ' <li class="itemBidLi" id="'+itemBidId+'"  onclick="showItemDetail(this)">';
				}
			
				leftPanelHtml = leftPanelHtml +' <a href="#Master" data-toggle="tab">'
			    +' <div class="col-md-12">'
			    +'  <label class="col-xs-6" id="'+itemBidId+'_name">'+name+'</label>'
			    +'	<label class="col-xs-6" id="'+itemBidId+'_uomName">'+uomName+'</label>'
			    +' </div>'	
			    +' <div class="col-md-12">'
			    +'	<label class="col-xs-6" id="'+itemBidId+'_description">'+description+'</label>'
				+'	<label class="col-xs-6" id="'+itemBidId+'_hcnCode">'+hsnCode+'</label>'
				+' </div>'
			    +' </a>'
			    +' </li>';
				i++;
			}
		
	});
	$(".leftPaneData").html(leftPanelHtml);
	loadItemBidRightPane(firstRow);
	$('.leftPaneData').paginathing({perPage: 6});
	
	 $(".tabs-left li a label").text(function(index, currentText) {
		    return currentText.substr(0, 20);
		});
	if(!$.isEmptyObject(data.objectMap.deviationTypeCode)){
			loadReferenceListById('PTSGtpForm #deviationType',data.objectMap.deviationTypeCode);
			loadReferenceListById('PTSDocForm #deviationType',data.objectMap.deviationTypeCode);
			loadReferenceListById('PCSSPForm #deviationType',data.objectMap.deviationTypeCode);
			loadReferenceListById('PCSDocForm #deviationType',data.objectMap.deviationTypeCode);
		}
}

function loadItemBidRightPane(data){
	 $("#itemDetailForm")[0].reset();
		if(!$.isEmptyObject(data)){
			 $("#loading-wrapper").show();
			  var itemBidId = data.itemBid.itemBidId==null?'': data.itemBid.itemBidId;
			    var name =data.itemBid.tahdrMaterial==null?'':data.itemBid.tahdrMaterial.material==null?'': data.itemBid.tahdrMaterial.material.name;
			    var uomName = data.itemBid.tahdrMaterial==null?'':data.itemBid.tahdrMaterial.material.uom==null?'': data.itemBid.tahdrMaterial.material.uom.name;
				var description = data.itemBid.tahdrMaterial==null?'':data.itemBid.tahdrMaterial.material.description==null?'':  data.itemBid.tahdrMaterial.material.description;
				var hsnCode = data.itemBid.tahdrMaterial==null?'':data.itemBid.tahdrMaterial.material.hsnCode==null?'':data.itemBid.tahdrMaterial.material.hsnCode.code==null?'':data.itemBid.tahdrMaterial.material.hsnCode.code;
				var tahdrMaterialId= data.itemBid.tahdrMaterial==null?'':data.itemBid.tahdrMaterial.tahdrMaterialId;
				/*var bidderGtpId= data.itemBid.technicalBid==null?'':data.itemBid.technicalBid.bidderGtp==null?'':data.itemBid.technicalBid.bidderGtp.bidderGtpId;*/
				var digiFileName= data.digiSignedDoc==null?'':data.digiSignedDoc.fileName==null?'No Name':data.digiSignedDoc.fileName;
				var digiFileId= data.digiSignedDoc==null?null:data.digiSignedDoc.attachmentId==null?null:data.digiSignedDoc.attachmentId;
				matName=name;
				matHsnCode=hsnCode;
				$("#itemDetailForm .itemBidId").val(itemBidId);
				$('#gennerateTechnicalScrutinyDoc #itemBidId').val(itemBidId);
				$("#itemDetailForm #itemBidId").val(itemBidId);
				$("#itemDetailForm #name").html(name);
				$("#itemDetailForm #uomName").html(uomName);
				$("#itemDetailForm #description").html(description);
				$("#itemDetailForm #hsnCode").html(hsnCode);
				$("#itemDetailForm #tahdrMaterialId").val(tahdrMaterialId);
				$("#itemDetailForm #startScrutinyBtnId").data('itembidid',itemBidId);
				$("#itemDetailForm #itemScrutinyId").val();
				$("#technicalFinalScrutinyForm #itemBidId").val(itemBidId);

				
				$("#PTSGtpForm #tahdrMaterialId").val(tahdrMaterialId);
				$(".tahdrMaterialId").val(tahdrMaterialId);
				
				
				$('.commScrutinyTabs').removeClass('readonly');
				
				$("#loading-wrapper").fadeOut('slow');
				submitToURL('saveItemScrutinyForTechnical/'+itemBidId, 'itemScrutinyResp');
				$(".technicalScrutinyTab").removeClass('readonly');
				
				var url=$("#itemDetailForm #technicaldownloadLinkId").data('url');
				if(digiFileId!=null){
					$("#itemDetailForm #technicaldownloadLinkId").attr('href',url);
					$("#itemDetailForm #technicaldownloadLinkId").prop('href', url+'/'+digiFileId);
				}
    			$("#itemDetailForm #technicaldownloadLinkId").html(digiFileName);
    			setHeaderValues(documentType+" Code:" +tenderCode, "Vendor Name: "+bidderName,"Material Name:"+name, "HSN Code:"+hsnCode);
		}
		else{
			$(".technicalScrutinyTab").addClass('readonly');
			$('#startScrutinyBtnId').attr('style','display: none;');
			setHeaderValues(documentType+" Code:" +tenderCode, "Vendor Name: "+bidderName,"Material Name:", "HSN Code:");
		}
		setChildLoadFlag(true);
}
function startScrutiny(el){
	 var itemBdId=$(el).data('itembidid');
	 submitToURL('saveItemScrutinyForTechnical/'+itemBdId, 'itemScrutinyResp');
	 event.preventDefault();
}

function showItemDetail(el){
	var itemBidId=$(el).attr('id');
	loadItemBidRightPane(itemBidArray["itemBid_"+itemBidId]);
}

function getDocList(){
	var itemBidId=$('.itemBidId').val();
	var dataDocSec=[];
	dataDocSec.push(itemBidArray["itemBid_"+itemBidId].commercialBid.bidderSecDoc[0]);
	if(itemBidArray["itemBid_"+itemBidId].technicalBid.bidderSecDoc.length!=0){
		dataDocSec.push(itemBidArray["itemBid_"+itemBidId].technicalBid.bidderSecDoc[0]);
		}
	loadDocDetails(dataDocSec);
}
function loadGtpDetail(ele){
	cacheLi();
	setCurrentTab(ele);
	/*if(getChangedFlag()){*/
		submitWithParam('getBidderGtpByItemScrutinyId','itemScrutinyId' ,'loadItemScrutinyLineDetails');
		/*setChangedFlag(false);
	}else{
		getCacheLi();
	}*/
}
function loadItemScrutinyLineDetails(data){
	 
	$('.pagination').children().remove();
	 
	$(".leftPaneData").html("");
	var leftPanelHtml="";
	var i=0;
	var firstRow=null;
	$.each(data,function(key,value){
		if(!$.isEmptyObject(value.bidderGtp)){
					var bidderGtpId = value.bidderGtp.bidderGtpId==null?'': value.bidderGtp.bidderGtpId;
					var gtp=getValue(value.bidderGtp.tahdrMaterialgtp.gtp.name);
					var gtpType=getValue(value.bidderGtp.tahdrMaterialgtp.gtp.gtpParameterType.name);
				bidderGtp["bidderGtp_"+bidderGtpId]=value;
				if(i==0){
					firstRow = value;
					
					leftPanelHtml = leftPanelHtml + ' <li class="active" id="'+bidderGtpId+'"  onclick="showBidderGtpDetail(this)">';
				}else{
					leftPanelHtml = leftPanelHtml + ' <li class="itemBidLi" id="'+bidderGtpId+'"  onclick="showBidderGtpDetail(this)">';
				}
			
				leftPanelHtml = leftPanelHtml +' <a href="#Master" data-toggle="tab">'
			    +' <div class="col-md-12">'
			    +'  <label class="col-xs-6" id="'+bidderGtpId+'_manufacturer">'+gtp+'</label>'
			    +'	<label class="col-xs-6" id="'+bidderGtpId+'_onAfRating">'+gtpType+'</label>'
			    +' </div>'
			    +' </a>'
			    +' </li>';
				i++;
			}
	});
	$(".leftPaneData").html(leftPanelHtml);
	loadItemScrutinyRightPane(firstRow);
	$('.leftPaneData').paginathing({perPage: 6});
	
	 $(".tabs-left li a label").text(function(index, currentText) {
		    return currentText.substr(0, 20);
		});
	}
function loadItemScrutinyRightPane(data){
	if(!$.isEmptyObject(data)){
			var intemScrutinylineId = data.itemScrutinyLineId==null?'': data.itemScrutinyLineId;
			if(!$.isEmptyObject(data.bidderGtp)){
				
					var bidderGtpId = data.bidderGtp.bidderGtpId==null?'': data.bidderGtp.bidderGtpId;
					var bidderId = data.itemScrutiny.bidder==null?'': data.itemScrutiny.bidder.bidderId;
					var itemScrutinyId = data.itemScrutiny==null?'': data.itemScrutiny.itemScrutinyId;
					var gtp =  getValue(data.bidderGtp.tahdrMaterialgtp.gtp.name);
					var gtpType =  data.bidderGtp.tahdrMaterialgtp.gtp.gtpParameterType.name==null?'':data.bidderGtp.tahdrMaterialgtp.gtp.gtpParameterType.name;
					var gtpTypeCode =  getValue(data.bidderGtp.tahdrMaterialgtp.gtp.gtpParameterType.code);
					 var deviationTypeCode=data.isDeviation==null?'T': data.isDeviation;
	    			 var deviationType=data.deviationType==null?'': data.deviationType;
	    			 var comment=data.deviationComment==null?'': data.deviationComment;
	    			 var response=gtpTypeCode=='FILE'?data.bidderGtp.fileResponse:getValue(data.bidderGtp.textResponse);
	    			 
	    			$("#gennerateTechnicalScrutinyDoc #itemScrutinyId").val(itemScrutinyId);
					$("#PTSGtpForm #bidderGtpId").val(bidderGtpId);
					$("#PTSGtpForm #bidderId").val(bidderId);
					$("#PTSGtpForm #gtpName").html(gtp);
					$("#PTSGtpForm #gtptype").html(gtpType);
					
					if(gtpTypeCode=='FILE'){
						var attId='';
						var fileName='';
						if(!$.isEmptyObject(response)){
							attId=response.attachmentId;
							fileName=response.fileName;
						}
					
						html="<a class='mrgleft' href='download/"+attId+"' >"+fileName+"</a>";
					}else{
						html=response;
					}
					$("#PTSGtpForm #bidderGtpResp").html(html);
					 $("#PTSGtpForm #deviationTypeCode").val(deviationTypeCode);
	    			 $("#PTSGtpForm #deviationType").val(deviationType);
	    			 $("#PTSGtpForm #comment").val(comment);
	    			 
					$("#PTSDocForm #bidderGtpId").val(bidderGtpId);
				}
			
			
			$("#PTSGtpForm #itemScrutinyLineId").val(intemScrutinylineId);
			$("#PTSGtpForm").removeClass('readonly');
			$("#PTSGtpForm .save").show();
		}
	else{
		$("#PTSGtpForm").addClass('readonly');
	    $("#gennerateTechnicalScrutinyDoc #itemScrutinyId").val();
		$("#PTSGtpForm #bidderGtpId").val('');
		$("#PTSGtpForm #bidderId").val('');
		$("#PTSGtpForm #gtpName").html('');
		$("#PTSGtpForm #gtptype").val('');
		$("#PTSGtpForm #bidderGtpResp").html('');
		$("#PTSGtpForm #deviationTypeCode").val('');
		$("#PTSGtpForm #deviationType").val('');
		$("#PTSGtpForm #comment").val('');
		$("#PTSDocForm #bidderGtpId").val('');
		$("#PTSGtpForm .save").hide();
	}
	
	/*setChildLoadFlag(true);*/
}
function loadTSDocumentDetail(ele){
	cacheLi();
	setCurrentTab(ele);
	/*if(getChangedFlag()){*/
		submitWithParam('getTechnicalDoc','itemBidId' ,'loadDocDetails');
		/*setChangedFlag(false);
	}else{
		getCacheLi();
	}*/
}

function loadCSDocumentDetail(ele){
		submitWithParam('getCommercialDoc','bidderId' ,'loadDocDetails');
}

function loadDocDetails(data){
	$('.pagination').children().remove();
	$(".leftPaneData").html("");
	var leftPanelHtml="";
	var i=0;
	var firstRow=null;
	if(!$.isEmptyObject(data.objectMap.ItemList)){
		$.each(data.objectMap.ItemList,function(key,value){
			 
			if(!$.isEmptyObject(value.bidderSectionDoc)){
					 var itemScrutinyLineId=value.itemScrutinyLineId==null?'':value.itemScrutinyLineId;
					 var biddersectionDocId = value.bidderSectionDoc.bidderSectionDocId==null?'': value.bidderSectionDoc.bidderSectionDocId;
					 var bidsection=value.bidderSectionDoc.bidSection==null?'': value.bidderSectionDoc.bidSection;
					 var sectionDocName=value.bidderSectionDoc.sectionDocument==null?'': value.bidderSectionDoc.sectionDocument.name;
					 var sectionDocDesc=value.bidderSectionDoc.sectionDocument==null?'': value.bidderSectionDoc.sectionDocument.description;
					 var attachment=value.bidderSectionDoc.attachment==null?'': value.bidderSectionDoc.attachment;
				 
					 bidderSecDoc["bidderSecDoc_"+biddersectionDocId]=value;
						
					
					if(i==0){
						firstRow = value;
						
						leftPanelHtml = leftPanelHtml + ' <li class="active" id="'+biddersectionDocId+'"  onclick="showDocDetailsDetail(this)">';
					}else{
						leftPanelHtml = leftPanelHtml + ' <li  id="'+biddersectionDocId+'"  onclick="showDocDetailsDetail(this)">';
					}
				
					leftPanelHtml = leftPanelHtml +' <a href="#Master" data-toggle="tab">'
				    +' <div class="col-md-12">'
				    +'  <label class="col-xs-6" id="'+biddersectionDocId+'_bidsection">'+sectionDocName+'</label>'
				    +'	<label class="col-xs-6" id="'+biddersectionDocId+'_"></label>'
				    +' </div>'	
				    +' <div class="col-md-12">'
				    +'	<label class="col-xs-6" id="'+biddersectionDocId+'_">'+sectionDocDesc+'</label>'
					+'	<label class="col-xs-6" id="'+biddersectionDocId+'_"></label>'
					+' </div>'
				    +' </a>'
				    +' </li>';
					i++;
					
				
				}
			
		});
		$(".leftPaneData").html(leftPanelHtml);
		loadDocDetailsRightPane(firstRow);
		$('#auditorDocForm').removeClass('readonly');
		$('#auditorDocForm .save').show();
	}else{
		$('#auditorDocForm').addClass('readonly');
		$('#auditorDocForm .save').hide();
	}
	
	if(role=='AUDIT'){
		 $('#auditorDocForm').removeClass('readonly');
		 
		 $('#PCSDocForm #auditorInputDivId').attr('style','display : none ;');
		 $('#PCSDocForm').addClass('readonly');
		 $('#PCSDocForm .save').hide();
	 }
	 else{
		 $('#PCSDocForm #auditorInputDivId').removeAttr('style');
		 $('#auditorDocForm').hide();
	 }
	$('.leftPaneData').paginathing({perPage: 6});
	
	 $(".tabs-left li a label").text(function(index, currentText) {
		    return currentText.substr(0, 20);
		});
}

function loadDocDetailsRightPane(data){
	if(!$.isEmptyObject(data)){
		     var itemScrutinyLineId=data.itemScrutinyLineId==null?'':data.itemScrutinyLineId
		    		 if(!$.isEmptyObject(data.bidderSectionDoc)){
			    			 var biddersectionDocId = data.bidderSectionDoc.bidderSectionDocId==null?'': data.bidderSectionDoc.bidderSectionDocId;
			    			 var bidderId = data.itemScrutiny.bidder==null?'': data.itemScrutiny.bidder.bidderId;
			    			 var itemScrutinyId = data.itemScrutiny==null?'': data.itemScrutiny.itemScrutinyId;
			    			 var bidsection=data.bidderSectionDoc.bidSection==null?'': data.bidderSectionDoc.bidSection;
			    			 var sectionDocName=data.bidderSectionDoc.sectionDocument==null?'': data.bidderSectionDoc.sectionDocument.name;
			    			 
			    			 var attachmentId=data.bidderSectionDoc.attachment==null?'': data.bidderSectionDoc.attachment.attachmentId;
			    			 var attachmentName=data.bidderSectionDoc.attachment==null?'': data.bidderSectionDoc.attachment.fileName;
			    			 
			    			 var deviationTypeCode=data.isDeviation==null?'T': data.isDeviation;
			    			 var deviationType=data.deviationType==null?'': data.deviationType;
			    			 var comment=data.deviationComment==null?'': data.deviationComment;
			    			 
			    			
			    			   
				    			if(bidsection=='TS'){
				    					var url=$("#PTSdownloadLinkId").data('url');
						    			$("#PTSDocForm #PTSdownloadLinkId").attr('href',url);
						    		    $("#PTSDocForm #PTSdownloadLinkId").prop('href', url+'/'+attachmentId);
						    			$("#PTSDocForm #PTSdownloadLinkId").html(attachmentName);
						    			
						    			 $("#PTSDocForm #bidderDocSecId").val(biddersectionDocId);
						    			 $("#PTSDocForm #itemScrutinyLineId").val(itemScrutinyLineId);
						    			 $("#PTSDocForm #documentCopy").html(sectionDocName);
						    			 $("#PTSDocForm #bidderId").val(bidderId);
						    		     $("#gennerateTechnicalScrutinyDoc #itemScrutinyId").val(itemScrutinyId);
						    			 $("#PTSDocForm #deviationTypeCode").val(deviationTypeCode);
						    			 $("#PTSDocForm #deviationType").val(deviationType);
						    			 $("#PTSDocForm #comment").val(comment);
						    			 $('#PTSDocForm .save').show();
						    			 $('#PTSDocForm').removeClass('readonly');
						    			 /*setChildLoadFlag(true);*/
				    				}
				    			else if(bidsection=='CS'){
				    				var auditorPrevStatus=data.auditorStatus==null?'':data.auditorStatus;
				    				var auditorPrevComment=data.auditorComment==null?'No Comment':data.auditorComment;
				    				$("#gennerateCommercialScrutinyDoc #itemScrutinyId").val(itemScrutinyId);
					    				var url=$("#PTSdownloadLinkId").data('url');
						    			$("#PCSDocForm #PCSdownloadLinkId").attr('href',url);
						    		    $("#PCSDocForm #PCSdownloadLinkId").prop('href', url+'/'+attachmentId);
						    			$("#PCSDocForm #PCSdownloadLinkId").html(attachmentName);
				    				
					    				 $("#PCSDocForm #bidderDocSecId").val(biddersectionDocId);
						    			 $("#PCSDocForm #itemScrutinyLineId").val(itemScrutinyLineId);
						    			 $("#PCSDocForm #documentCopy").html(sectionDocName);
						    			 $("#PCSDocForm #bidderId").val(bidderId);
						    			 
						    			 $("#PCSDocForm #deviationTypeCode").val(deviationTypeCode);
						    			 $("#PCSDocForm #deviationType").val(deviationType);
						    			 if(deviationType==''){
						    				 $("#auditorDocForm").addClass('readonly');
						    			 }
						    			 $("#PCSDocForm #comment").val(comment);
						    			 
						    			 $("#PCSDocForm #auditorDocPrevStatus").html(auditorPrevStatus);
						    			 $("#PCSDocForm #auditorDocPrevComment").html(auditorPrevComment);
						    			 
						    			 $("#PCSDocForm #auditPrevStatus").val(auditorPrevStatus);
						    			 $("#PCSDocForm #auditPrevComment").val(auditorPrevComment);
						    			 if(auditorPrevStatus.toUpperCase()=="APPROVED"){
						    				 $("#PCSDocForm").addClass('readonly');
						    				 $('#PCSDocForm .save').hide();
						    			 }else{
						    				 $("#PCSDocForm").removeClass('readonly');
						    				 $('#PCSDocForm .save').show(); 
						    			 }
						    			 
						    			 /*$('#PCSDocForm').removeClass('readonly');
						    			 $('#PCSDocForm .save').show();*/
						    			 $("#auditorDocForm #itemScrutinyLineId").val(itemScrutinyLineId);
						    			 $('#auditorDocForm #auditTypeCode').val(auditorPrevStatus);
						    			 $('#auditorDocForm #comment').val(auditorPrevComment);
				    				}
				    			
				    			$('#auditorDocForm').removeClass('readonly');
								
		    			 }
		    		 
		     $("#PTSDocForm #itemScrutinyLineId").val(itemScrutinyLineId);
		}
	
	else{
		$('#PTSDocForm').addClass('readonly');
		$("#PCSDocForm #documentCopy").html('');
		$("#PTSDocForm #documentCopy").html('');
		$('#auditorDocForm').addClass('readonly');
		$('#PTSDocForm #PTSdownloadLinkId').html("");
		$('#PCSSPForm').addClass('readonly');
		$('#PCSDocForm').addClass('readonly');
		$('#PCSSPForm #PCSdownloadLinkId').html("");
		$('#PCSDocForm .save').hide();
		$('#PTSDocForm .save').hide();
			$("#PTSDocForm #PTSdownloadLinkId").prop('href','#');
			$("#PTSDocForm #PTSdownloadLinkId").html('No File');
			$("#PCSDocForm #PCSdownloadLinkId").prop('href', '#');
 			$("#PCSDocForm #PCSdownloadLinkId").html('No File');
		$('#PTSDocForm')[0].reset();
		$('#PCSDocForm')[0].reset();
		$('#auditorDocForm #auditTypeCode').val('');
		$('#auditorDocForm #comment').val('');
		$("#gennerateTechnicalScrutinyDoc #itemScrutinyId").val('');
		$("#gennerateCommercialScrutinyDoc #itemScrutinyId").val('');
	}
	
}
function showBidderGtpDetail(el){
	var bidderGtpId=$(el).attr('id');
	loadItemScrutinyRightPane(bidderGtp["bidderGtp_"+bidderGtpId]);
}

function showDocDetailsDetail(el){
	var bidderSecDocId=$(el).attr('id');
	loadDocDetailsRightPane(bidderSecDoc["bidderSecDoc_"+bidderSecDocId]);
}

function itemScrutinyLineRespForTechnical(data){
	if(data.objectMap.result==true){
		var bidderGtpId=$("#PTSGtpForm #bidderGtpId").val();
		bidderGtp["bidderGtp_"+bidderGtpId]=data.objectMap.itemScrutinyLine;
		Alert.info(data.objectMap.resultMessage);
	}else{
		Alert.warn(data.objectMap.resultMessage);
	}
}

function itemScrutinyLineRespForTechnicalDoc(data){
	if(data.objectMap.result==true){
		var bidderDocSecId=$("#PTSDocForm #bidderDocSecId").val();
		bidderSecDoc["bidderSecDoc_"+bidderDocSecId]=data.objectMap.itemScrutinyLine;
		Alert.info(data.objectMap.resultMessage);
	}else{
		Alert.warn(data.objectMap.resultMessage);
	}
}

function itemScrutinyLineRespForCommercial(data){
	if(data.objectMap.result==true){
		var itemScrutinyLineId= $("#PCSSPForm #itemScrutinyLineId").val();
		scrutinyPoint["itemScrutinyLine_"+itemScrutinyLineId]=data.objectMap.itemScrutinyLine;
		Alert.info(data.objectMap.resultMessage);
	}else{
		Alert.warn(data.objectMap.resultMessage);
	}
}

function itemScrutinyLineRespForCommercialDoc(data){
	if(data.objectMap.result==true){
		var bidderDocSecId=$("#PCSDocForm #bidderDocSecId").val();
		bidderSecDoc["bidderSecDoc_"+bidderDocSecId]=data.objectMap.itemScrutinyLine;
		Alert.info(data.objectMap.resultMessage);
	}else{
		Alert.warn(data.objectMap.resultMessage);
	}
}

function itemScrutinyLineRespForScrutinyPoint(data){
	$("#PCSSPForm #itemScrutinyLineId").val(data.itemScrutinyLineId);
	Alert.info('Commercial Scrutiny Saved Succesfully');
}

function deviationStatusChange(el){
	var status=$(el).val();
	if(status=='N'){
			$("#PTSGtpForm #deviationType").attr('disabled','disabled');
			$("#PTSDocForm #deviationType").attr('disabled','disabled');
			$("#PCSSPForm #deviationType").attr('disabled','disabled');
			$("#PCSDocForm #deviationType").attr('disabled','disabled');
			
			$("#PTSGtpForm #deviationType").val('');
			$("#PTSDocForm #deviationType").val('');
			$("#PCSSPForm #deviationType").val('');
			$("#PCSDocForm #deviationType").val('');
		}
	else if(status=='Y'){
			$("#PTSGtpForm #deviationType").removeAttr('disabled');
			$("#PTSDocForm #deviationType").removeAttr('disabled');
			$("#PCSSPForm #deviationType").removeAttr('disabled');
			$("#PCSDocForm #deviationType").removeAttr('disabled');
			
			$("#PTSGtpForm #deviationType").removeClass('readonly');
			$("#PTSDocForm #deviationType").removeClass('readonly');
			$("#PCSSPForm #deviationType").removeClass('readonly');
			$("#PCSDocForm #deviationType").removeClass('readonly');
		}
	else {
		$("#PTSGtpForm #deviationType").removeClass('readonly');
		$("#PTSDocForm #deviationType").removeClass('readonly');
		$("#PCSSPForm #deviationType").removeClass('readonly');
		$("#PCSDocForm #deviationType").removeClass('readonly');
		
		$("#PTSGtpForm #deviationType").val('');
		$("#PTSDocForm #deviationType").val('');
		$("#PCSSPForm #deviationType").val('');
		$("#PCSDocForm #deviationType").val('');
	}
	
}
function getScrutinyPointList(el){
	 var itemScrutinyId=$(el).data('itemscrutinyid');
	 submitToURL('getScrutinyPoint/'+itemScrutinyId, 'loadScrutinyPointListForScrutiny');
	 return false;
}

function loadCommercialScrutinyDetail(ele){
	cacheLi();
	setCurrentTab(ele);
	if(getChangedFlag()){
		submitWithParam('saveItemScrutinyForCommercial','bidderId' ,'loadScrutinyPointListForScrutiny');
		setChangedFlag(false);
	}else{
		getCacheLi();
		setHeaderValues(documentType+" Code: " +tenderCode, "Vendor Name: "+bidderName, "GST IN: "+bidderGstNo, "CRN NO : "+bidderCrn);
	}
	$(".leftPaneData").html("");
}
function loadScrutinyPointListForScrutiny(data){
	debugger;
	setHeaderValues(documentType+" Code: " +tenderCode, "Vendor Name: "+bidderName, "GST IN: "+bidderGstNo, "CRN NO : "+bidderCrn);
	$('.pagination').children().remove();
	$(".leftPaneData").html("");
	if(data.objectMap.result){
		if(data.objectMap.itemScrutinyList!=undefined){
			var leftPanelHtml="";
			var i=0;
			var firstRow=null;
			$.each(data.objectMap.itemScrutinyList,function(key,value){
				if(!$.isEmptyObject(value)){
							var itemScrutinyLineId = value.itemScrutinyLineId==null?'': value.itemScrutinyLineId;
							 var scrutinypointName=value.scrutinyPoint.name==null?'': value.scrutinyPoint.name;
							 var scrutinypointDesp=value.scrutinyPoint.description==null?'': value.scrutinyPoint.description;
							 var serialNo=value.scrutinyPoint.serialNo==null?'': value.scrutinyPoint.serialNo;
						scrutinyPoint["itemScrutinyLine_"+itemScrutinyLineId]=value;
						if(i==0){
							firstRow = value;
							
							leftPanelHtml = leftPanelHtml + ' <li class="active" id="'+itemScrutinyLineId+'"  onclick="showScrutinyPointDetail(this)">';
						}else{
							leftPanelHtml = leftPanelHtml + ' <li  id="'+itemScrutinyLineId+'"  onclick="showScrutinyPointDetail(this)">';
						}
					
						leftPanelHtml = leftPanelHtml +' <a href="#Master" data-toggle="tab">'
					    +' <div class="col-md-12">'
					    +'  <label class="col-xs-12" id="'+itemScrutinyLineId+'_bidsection">'+scrutinypointName+'</label>'
					    +' </div>'	
					    +' </a>'
					    +' </li>';
						i++;
					}
			});
			if(!$.isEmptyObject(data.objectMap.itemScrutiny)){
				itemScrutinyResp(data.objectMap.itemScrutiny);
			}
			
			$(".leftPaneData").html(leftPanelHtml);
			loadScrutinyPointRightPane(firstRow);
			$('.leftPaneData').paginathing({perPage: 6});
			
			 $(".tabs-left li a label").text(function(index, currentText) {
				    return currentText.substr(0, 20);
				});
			$('.preliminaryCommScr').removeClass('readonly')
		 }
	 else{
		 Alert.warn(data.objectMap.resultMessage);
		 $('.preliminaryCommScr').addClass('readonly');
		 $('#PCSSPForm').addClass('readonly');
		 $('#auditorForm .save').hide();
		 $('#auditorForm').addClass('readonly');
	}
	}else{
		 Alert.warn(data.objectMap.resultMessage);
		 $('.preliminaryCommScr').addClass('readonly');
		 $('#PCSSPForm').addClass('readonly');
		 $('#auditorForm .save').hide();
		 $('#auditorForm').addClass('readonly');
	}
	 
	if(!$.isEmptyObject(data.objectMap.financialAttachments)){
			setFinancailDocumentsDetails(data);
		}else{
			$("#pnlFinancialDetailsId").html("");
			$("#bsFinancialDetailsId").html("");
			$("#tdFinancialDetailsId").html("");
		}
	if(!$.isEmptyObject(data.objectMap.role)){
		if(data.objectMap.role=='AUDIT'){
			$('#auditorForm').removeClass('readonly');
			$('#PCSSPForm').addClass('readonly');
			$('#PCSSPForm #PCSSPActionBtnDiveId').hide();
			$('#PCSSPForm #auditorInputDivId').attr('style','display : none ;');
		}
		else{
			$('#auditorForm').addClass('readonly');
			$('#auditorForm').hide();
			$('#PCSSPForm').removeClass('readonly');
			$('#PCSSPForm #PCSSPActionBtnDiveId').show();
			$('#PCSSPForm  #auditorInputDivId').removeAttr('style'); 
			
	    }
		
	}
	if(!$.isEmptyObject(data.objectMap.commercialBid)){
		var data=data.objectMap.commercialBid;
		var digiFileName= data.digiSignedDoc==null?'No File':data.digiSignedDoc.fileName==null?'No Name':data.digiSignedDoc.fileName;
		var digiFileId= data.digiSignedDoc==null?null:data.digiSignedDoc.attachmentId==null?null:data.digiSignedDoc.attachmentId;
		var url=$("#commercialdownloadLinkId").data('url');
		if(digiFileId!=null){
			$("#commercialdownloadLinkId").attr('href',url);
			  $("#commercialdownloadLinkId").prop('href', url+'/'+digiFileId);
		}
		$("#commercialdownloadLinkId").html(digiFileName);
	}
	 
	}
function loadScrutinyPointRightPane(data){
	 
	if(!$.isEmptyObject(data)){
		 var itemScrutinyLineId = data.itemScrutinyLineId==null?'': data.itemScrutinyLineId;
		 var bidderId = data.itemScrutiny.bidder==null?'': data.itemScrutiny.bidder.bidderId;
		 var scrutinypointName=data.scrutinyPoint.name==null?'': data.scrutinyPoint.name;
		 var scrutinypointDesp=data.scrutinyPoint.description==null?'': data.scrutinyPoint.description;
		 var itemScrutinyId=data.itemScrutiny.itemScrutinyId==null?'': data.itemScrutiny.itemScrutinyId;
		 var scrutinyPointId=data.scrutinyPoint.scrutinyPointId==null?'': data.scrutinyPoint.scrutinyPointId;
		 var deviationTypeCode=data.isDeviation==null?'T': data.isDeviation;
		 var deviationType=data.deviationType==null?'': data.deviationType;
		 var comment=data.deviationComment==null?'': data.deviationComment;
		 var auditorPrevStatus=data.auditorStatus==null?'':data.auditorStatus;
		 var auditorPrevComment=data.auditorComment==null?'No Comment':data.auditorComment;
		 
		 $("#PCSSPForm #itemScrutinyLineId").val(itemScrutinyLineId);
		 $("#PCSSPForm #scrutinyPointName").html(scrutinypointName);
		 $("#PCSSPForm #scrutinyPointDesp").html(scrutinypointDesp);
		 $("#PCSSPForm #itemScrutinyId").val(itemScrutinyId);
		 $("#PCSSPForm #scrutinyPointId").val(scrutinyPointId);
		 $("#PCSSPForm #bidderId").val(bidderId);
		 $("#gennerateCommercialScrutinyDoc #itemScrutinyId").val(itemScrutinyId);
		 $("#PCSSPForm #auditPrevStatus").val(auditorPrevStatus);
		 $("#PCSSPForm #auditPrevComment").val(auditorPrevComment);
		 $("#gennerateCommercialScrutinyDoc #itemScrutinyId").val(itemScrutinyId);
		 $("#PCSSPForm #deviationTypeCode").val(deviationTypeCode);
		 $("#PCSSPForm #deviationType").val(deviationType);
		 if(deviationTypeCode==''){
			 $("#auditorForm").addClass('readonly');
		 }
		 $("#PCSSPForm #comment").val(comment);
		 $("#commercialFinalScrutinyForm #itemScrutinyId").val(itemScrutinyId);
		 $("#auditorConfirmForm #itemScrutinyId").val(itemScrutinyId);
		 $("#PCSTabId").data('itemscrutinyid',itemScrutinyId);

		 $("#auditorForm #itemScrutinyLineId").val(itemScrutinyLineId);
		 $('#auditorForm #auditTypeCode').val(auditorPrevStatus);
		 $('#auditorForm #comment').val(auditorPrevComment);
		 
		 $('#PCSSPForm #auditorInputDivId').removeAttr('style');
		 if(auditorPrevStatus.toUpperCase()=="APPROVED"){
			 $("#PCSSPForm").addClass('readonly');
			 $('#PCSSPForm .save').hide();
		 }else{
			 $("#PCSSPForm").removeClass('readonly');
			 $('#PCSSPForm .save').show(); 
		 }
		 $('#PCSSPForm #auditorPrevStatus').html(auditorPrevStatus);
		 $('#PCSSPForm #auditorPrevComment').html(auditorPrevComment);
		 
		 
		}else{
			$("#gennerateCommercialScrutinyDoc #itemScrutinyId").val('');
			$("#auditorForm").addClass('readonly');
		}
	if(role=='AUDIT'){
		$('#PCSSPForm').addClass('readonly');
	}
	setChildLoadFlag(true);
}

function showScrutinyPointDetail(el){
	var itemScrutinyLineId=$(el).attr('id');
	loadScrutinyPointRightPane(scrutinyPoint["itemScrutinyLine_"+itemScrutinyLineId])
}

function finalScrutinyLineResp(data){
	  if(data.objectMap.resultStatus){
		  Alert.info(data.objectMap.result);
		  var bidderId=$('#bidderDetailForm #bidderId').val();
		  bidderArray["bidder_"+bidderId].status=data.objectMap.bidderStatus;
		  $('.scrDocDownloadBtnId').removeAttr('disabled');
		  removeScrutinyFileData();
	  }else{
		  Alert.warn(data.objectMap.result);
	  }
}

function loadDeviationScheduleDetail(ele){
	cacheLi();
	setCurrentTab(ele);
	/*if(getChangedFlag()){*/
		submitWithParam('checkDeviationSchedule', 'deviationScheduleForm #tahdrDetailId', 'setDeviationSchedule');
	/*	setChangedFlag(false);
	}else{
		getCacheLi();
	}*/
}

function setDeviationSchedule(data){
	$(".leftPaneData").html("");
	var documentType=$(".documentType").val();
	setHeaderValues(documentType+" Code:" +tenderCode , documentType+" Version : "+tenderVersion, "Description: "+tenderDescription, "EMD Fees : "+tenderEmdFee);
	
	
	var currentDateTime=setCurrentDate("");
	 $('#deviationScheduleForm #deviationOpenningDate').removeAttr('disabled');
	 $('#deviationScheduleForm #deviationToDate').removeAttr('disabled');
	 $('#deviationScheduleForm #deviationOpenningDate').val(currentDateTime);
	 $('#deviationScheduleForm #deviationToDate').val(currentDateTime);
	 $('#deviationScheduleForm #deviationFromDate').val(currentDateTime);
	 $('#deviationScheduleForm #checkBoxId').val('N');
	 if(!$.isEmptyObject(data.objectMap.tahdrDetail)){
		 var value=data.objectMap.tahdrDetail;
		 var openingDate = value.c1OpenningDate==null?'':value.c1OpenningDate;
		 var bidEndDate = value.c1ToDate==null?'':value.c1ToDate;
		 $('#deviationScheduleForm #deviationOpenningDate').val(setCurrentDate(openingDate));
		 $('#deviationScheduleForm #deviationToDate').val(setCurrentDate(bidEndDate));
	 }
    if(data.objectMap.auditorStatus==true){
    	if(data.objectMap.status==false){
       	 var value=data.objectMap;
       	 if(value.tcopCount>0){
       		 Alert.warn('Scrutiny of few bidder is not done !')
      		     $('#deviationScheduleForm').addClass('readonly');
       		 return;
       	 }
       	 else if(value.dvtnCount>0){
       		 $('#deviationScheduleForm #checkBoxLabelId').addClass('readonly');
      		     $('#deviationScheduleForm #checkBoxId').removeAttr('attr');
      		   $('#deviationScheduleForm').removeClass('readonly');
       		 return;
       	 }
       	 else if(value.tcopCount==0 && value.dvtnCount==0){
       		 $('#deviationScheduleForm').removeClass('readonly');
       		 $('#deviationScheduleForm #checkBoxLabelId').addClass('readonly');
       		 $('#deviationScheduleForm #checkBoxId').attr('checked','checked');
       		 $('#deviationScheduleForm #checkBoxId').val('Y');
       		 $('#deviationScheduleForm #deviationOpenningDate').attr('disabled','disabled');
       		 $('#deviationScheduleForm #deviationToDate').attr('disabled','disabled');
       		 $('#deviationScheduleForm #deviationOpenningDate').val('');
       		 $('#deviationScheduleForm #deviationToDate').val('');
       	 }
        }else{
       	 Alert.warn('No Bidder Present !')
   		  $('#deviationScheduleForm').addClass('readonly');
        }
    } else{
    	  Alert.warn(data.objectMap.resultMessage);
 		  $('#deviationScheduleForm').addClass('readonly');
    }
     
   /* setChildLoadFlag(true);*/
	
}
function deviationScheduleResp(data)
{
	 
	if(data.objectMap.resultStatus){
		
		Alert.info(data.objectMap.Status);
	}else{
		if(data.objectMap.dateValidationResult!=undefined && !data.objectMap.dateValidationResult){
			if (data.objectMap.dateValidatioError.hasError) {
				var msg = 'Following Dates Are Not Filled Properly:'+'<br/>';
				$.each(data.objectMap.dateValidatioError.errors, function(key, value) {
				     msg=msg+'\n'+value.errorMessage +','+ '<br/>';
				       
				   });
				    Alert.warn(msg);
			}else{
				Alert.warn(data.objectMap.Status);
			}
		}else{
			Alert.warn(data.objectMap.Status);
		}
		
	}
  
}
function loadCSFinalResp(ele){
	
		submitWithParam('getFinalResp','commercialFinalScrutinyForm #itemScrutinyId' ,'setFinalResp');

}
function loadTSFinalResp(ele){
	cacheLi();
	setCurrentTab(ele);
	submitWithParam('getFinalResp','technicalFinalScrutinyForm #itemScrutinyId' ,'setFinalResp');
	/*if(getChangedFlag()){
		submitWithParam('getFinalResp','technicalFinalScrutinyForm #itemScrutinyId' ,'setFinalResp');
		setChangedFlag(false);
	}else{
		getCacheLi();
	}*/
}
function setFinalResp(data){
	$('#technicalFinalScrutinyForm #statusApproved').removeAttr('disabled');
	 $('#commercialFinalScrutinyForm #statusApproved').removeAttr('disabled');
	 $('#commercialFinalScrutinyForm #ComComments').val('');
	 $('#technicalFinalScrutinyForm #finalComment').val('');
	 
	 $('#commercialFinalScrutinyForm #auditorDocPrevStatus').html('');
	   $('#commercialFinalScrutinyForm #auditorDocPrevComment').html('');
	   $("#auditorConfirmForm #auditTypeCode").val('');
	   $("#auditorConfirmForm #comment").val('');
	   $("#auditorConfirmForm #auditTypeCode").removeClass('readonly');
	   
	   if(role=='AUDIT'){
			$('#auditorConfirmForm').removeClass('readonly');
			$('#commercialFinalScrutinyForm #auditorInputDivId').attr('style','display : none ;');
			$('#commercialFinalScrutinyForm').addClass('readonly');
			$('#commercialFinalScrutinyForm .save').hide();
		}else{
			$('#commercialFinalScrutinyForm #auditorInputDivId').removeAttr('style');
			$('#auditorConfirmForm').hide();
		}
	   
	$(".leftPaneData").html("");
	if(data.objectMap.isAllScrutinized){
		if(data.objectMap.itemScrutiny!=undefined){
			   var value=data.objectMap.itemScrutiny;
			   var itemScrutinyId=value.itemScrutinyId;
			   var comment=value.preliminaryScrutinyComment==null?'':value.preliminaryScrutinyComment;
			   var status=value.preliminaryScrutinyStatus==null?'':value.preliminaryScrutinyStatus;
			   var isdeviation=data.objectMap.isDeviation;
			   if(value.scrutinyType=='TECHSCR'){
				   $('#gennerateTechnicalScrutinyDoc #itemScrutinyId').val(itemScrutinyId);
				   if(!isdeviation){
					   $('#technicalFinalScrutinyForm #finalComment').val(comment);
					   if(status!=null && status!=''){
						   setScrutinyFileData('TECHSCR');
						   $('.scrDocDownloadBtnId').removeAttr('disabled');
						   if(status.toUpperCase()=='APPROVED'){
							   $('#technicalFinalScrutinyForm #statusApproved').prop('checked',true);
						   }
						   else if(status.toUpperCase()=='DEVIATION'){
							   $('#technicalFinalScrutinyForm #statusDeviation').prop('checked',true);
							   $('#technicalFinalScrutinyForm #statusApproved').addClass('readonly');
						   }
						   else if(status.toUpperCase()=='REJECTED'){
							   $('#technicalFinalScrutinyForm #statusRejected').prop('checked',true);
						   }else {
							   $('#technicalFinalScrutinyForm #statusApproved').prop('checked',true);
							   $('#technicalFinalScrutinyForm #statusDeviation').addClass('readonly');
						   }
					   }else{
						   resetScrutinyFileData();
						   $('.scrDocDownloadBtnId').attr('disabled','disable');
						   $('#technicalFinalScrutinyForm #statusDeviation').prop('checked',true);
					   }
				   }
				   else{
					   $('#technicalFinalScrutinyForm #statusApproved').attr('disabled','disable');
					   $('#technicalFinalScrutinyForm #finalComment').val(comment);
					   if(status!=null && status!=''){
						   setScrutinyFileData('TECHSCR');
						   $('.scrDocDownloadBtnId').removeAttr('disabled');
						   if(status.toUpperCase()=='APPROVED'){
							   $('#technicalFinalScrutinyForm #statusApproved').prop('checked',true);
						   }
						   else if(status.toUpperCase()=='DEVIATION'){
							   $('#technicalFinalScrutinyForm #statusDeviation').prop('checked',true);
							   $('#technicalFinalScrutinyForm #statusApproved').addClass('readonly');
						   }
						   else if(status.toUpperCase()=='REJECTED'){
							   $('#technicalFinalScrutinyForm #statusRejected').prop('checked',true);
						   }  
					   }else{
						   resetScrutinyFileData();
						   $('.scrDocDownloadBtnId').attr('disabled','disable');
						   $('#technicalFinalScrutinyForm #statusDeviation').prop('checked',true);
					   }
				   }
				   $('#technicalFinalScrutinyForm').removeClass('readonly');
				   setChildLoadFlag(true);
			   }
			   else {
				   $('#gennerateCommercialScrutinyDoc #itemScrutinyId').val(itemScrutinyId);
				   if(!isdeviation){
					   $('#commercialFinalScrutinyForm #ComComments').val(comment);
					   if(status!=null && status!=''){
						   setScrutinyFileData('COMMSCR');
						   if(status.toUpperCase()=='APPROVED'){
							   $('#commercialFinalScrutinyForm #statusApproved').prop('checked',true);
						   }
						   else if(status.toUpperCase()=='DEVIATION'){
							   $('#commercialFinalScrutinyForm #statusDeviation').prop('checked',true);
							   $('#commercialFinalScrutinyForm #statusApproved').addClass('readonly');
						   }
						   else if(status.toUpperCase()=='REJECTED'){
							   $('#commercialFinalScrutinyForm #statusRejected').prop('checked',true);
						   }  
						   else {
							   $('#commercialFinalScrutinyForm #statusApproved').prop('checked',true);
							   $('#commercialFinalScrutinyForm #statusDeviation').addClass('readonly');
						   }
					   }else{
						   resetScrutinyFileData();
						   removeScrutinyFileData();
						   $('#commercialFinalScrutinyForm #statusDeviation').prop('checked',true);
					   }
				   }
				   else{
					   $('#commercialFinalScrutinyForm #statusApproved').attr('disabled','disable');
					   $('#commercialFinalScrutinyForm #ComComments').val(comment);
					   if(status!=null && status!=''){
						  
					   if(status.toUpperCase()=='APPROVED'){
						   $('#commercialFinalScrutinyForm #statusApproved').prop('checked',true);
					   }
					   else if(status.toUpperCase()=='DEVIATION'){
						   $('#commercialFinalScrutinyForm #statusDeviation').prop('checked',true);
						   $('#commercialFinalScrutinyForm #statusApproved').addClass('readonly');
					   }
					   else if(status.toUpperCase()=='REJECTED'){
						   $('#commercialFinalScrutinyForm #statusRejected').prop('checked',true);
					   } 
					  
					   }else{
						   resetScrutinyFileData();
						   removeScrutinyFileData();
						   $('#commercialFinalScrutinyForm #statusDeviation').prop('checked',true);
					   }
				   }
			   $('#commercialFinalScrutinyForm').removeClass('readonly');
			   if( role=='AUDIT'){
				   if(status!=null && status!=''){
						  var auditorPrevStatus=value.preliminaryAuditorStatus==null?'':value.preliminaryAuditorStatus;
						   var auditorPrevComment=value.preliminaryAuditorComment==null?'No Comment':value.preliminaryAuditorComment;
						   
						   $('#commercialFinalScrutinyForm #auditorDocPrevStatus').html(auditorPrevStatus);
						   $('#commercialFinalScrutinyForm #auditorDocPrevComment').html(auditorPrevComment);
						   
						   $("#auditorConfirmForm #auditTypeCode").val(auditorPrevStatus);
						   $("#auditorConfirmForm #comment").val(auditorPrevComment);
						   if(!data.objectMap.isAnyRejected){
							   $("#auditorConfirmForm #auditTypeCode").val('CLARIFICATION');
							   $("#auditorConfirmForm #auditTypeCode").addClass('readonly');
						   }
					  } else{
						  Alert.warn('Commercial Scrutiny Yet not Confirmed for Auditing!');
						  $("#auditorConfirmForm").addClass('readonly');
					  } 
			   }else{
				   if(status!=null){
						  var auditorPrevStatus=value.preliminaryAuditorStatus==null?'':value.preliminaryAuditorStatus;
						   var auditorPrevComment=value.preliminaryAuditorComment==null?'No Comment':value.preliminaryAuditorComment;
						   if(auditorPrevStatus==''){
							   resetScrutinyFileData();
							   removeScrutinyFileData();
							   $('.scrComDocDownloadBtnId').attr('disabled','disable');							   
						   }else{
							   setScrutinyFileData('COMMSCR');
							   $('#commercialFinalScrutinyForm #auditorDocPrevStatus').html(auditorPrevStatus);
							   $('#commercialFinalScrutinyForm #auditorDocPrevComment').html(auditorPrevComment);
							   if(auditorPrevStatus.toUpperCase()=="APPROVED"){
								   $('.scrComDocDownloadBtnId').remove('disabled','disable');	
				    				 $("#commercialFinalScrutinyForm").addClass('readonly');
				    				 $('#commercialFinalScrutinyForm .save').hide();
				    			 }else{
				    				 $("#commercialFinalScrutinyForm").removeClass('readonly');
				    				 $('#commercialFinalScrutinyForm .save').show(); 
				    			 }
						   }
						  
					  } 
			   }
			   if(role=='AUDIT'){
					$('#commercialFinalScrutinyForm').addClass('readonly');
					$('#auditorConfirmForm .save').show();
				} 
			   
			}
		
	}else{
		 resetScrutinyFileData();
		   removeScrutinyFileData();
		$('#technicalFinalScrutinyForm').addClass('readonly');
		$('#commercialFinalScrutinyForm').addClass('readonly');
		$('.scrDocDownloadBtnId').attr('disabled','disable');
		$('#gennerateTechnicalScrutinyDoc #itemScrutinyId').val('');
		$('#gennerateCommercialScrutinyDoc #itemScrutinyId').val('');
		Alert.warn('Few parameter is Left for Scrutiny');
		$('#auditorConfirmForm .save').hide();
	}
	
	}else{
		 resetScrutinyFileData();
		   removeScrutinyFileData();
		$('.scrDocDownloadBtnId').attr('disabled','disable');
		$('#technicalFinalScrutinyForm').addClass('readonly');
		$('#commercialFinalScrutinyForm').addClass('readonly');
		$('#gennerateTechnicalScrutinyDoc #itemScrutinyId').val('');
		$('#gennerateCommercialScrutinyDoc #itemScrutinyId').val('');
		$('#auditorConfirmForm .save').hide();
		Alert.warn('Few parameter is Left for Scrutiny');
	}
	
	if(role=='AUDIT' && !data.objectMap.isAllAudited){
		$('#auditorConfirmForm').addClass('readonly');
		Alert.warn('Few parameter is Left for Auditing');
		$('#auditorConfirmForm .save').hide();
	}
}
function saveAuditorCommentResp(data){
	 
	 var itemScrutinyLineId=$("#auditorForm #itemScrutinyLineId").val();
	 var auditorPrevStatus=$("#auditorForm #auditTypeCode").val();
	 var auditorPrevComment=$("#auditorForm #comment").val();
	 $('#PCSSPForm #auditorPrevStatus').html(auditorPrevStatus);
	 $('#PCSSPForm #auditorPrevComment').html(auditorPrevComment);
	 scrutinyPoint["itemScrutinyLine_"+itemScrutinyLineId].auditorStatus=auditorPrevStatus;
	 scrutinyPoint["itemScrutinyLine_"+itemScrutinyLineId].auditorComment=auditorPrevComment;
	
	if(data.objectMap.resultStatus==true){
		Alert.info(data.objectMap.resultMessage);
		
	}else{
		Alert.warn(data.objectMap.resultMessage);
	}
}
function loadAuditor(ele){
	cacheLi();
	setCurrentTab(ele);
	/*if(getChangedFlag()){*/
		submitWithParam('checkAuditingStatus', 'addAuditorForm #tahdrId', 'setAuditingStatusResp');
		/*setChangedFlag(false);
	}else{
		getCacheLi();
	}*/
}
function setAuditingStatusResp(data){
	var documentType=$(".documentType").val();
	setHeaderValues(documentType+" Code:" +tenderCode , documentType+" Version : "+tenderVersion, "Description: "+tenderDescription, "EMD Fees : "+tenderEmdFee);
	$(".leftPaneData").html("");
	if(data.objectMap.resultStatus){
		$('#addAuditorForm').removeClass('readonly');
		if(data.objectMap.hasOwnProperty('auditorList')){
			if(!$.isEmptyObject(data.objectMap.auditorList)){
					$("#addAuditorForm #auditorDropDownId").html("");
						var options = "<option value=''>Select Auditor</option>";
						$.each(data.objectMap.auditorList,function(key,value){
							options +='<option value="'+value.userId+'">'+value.name +'</option>'
						});
						$("#addAuditorForm #auditorDropDownId").append(options);
			}else{
				$('#addAuditorForm').addClass('readonly');
				Alert.warn('No Auditor Fetched');
			}
		}
		var userId=(data.objectMap.tender==null || data.objectMap.tender==undefined )?0:data.objectMap.tender.auditorUser==null?0:data.objectMap.tender.auditorUser.userId;
		if(userId!=0){
			$('#intimidateAuditorBtnId').removeAttr('style');
			$("#addAuditorForm #auditorDropDownId").val(userId);
			$("#addAuditorForm .save").hide();
		}else{
			$('#intimidateAuditorBtnId').attr('style','display: none;');
			$("#addAuditorForm #auditorDropDownId").val(0);
			$("#addAuditorForm .save").show();
		}
	}else{
		$('#addAuditorForm').addClass('readonly');
		$('#intimidateAuditorBtnId').attr('style','display: none;');
		$("#addAuditorForm #auditorDropDownId").val(0);
		Alert.warn(data.objectMap.resultMessage);
	}
	/*setChildLoadFlag(true);*/
}
function addAuditorResp(data){
	if(data.objectMap.resultStatus){
		Alert.info(data.objectMap.resultMessage);
	}else{
		Alert.warn(data.objectMap.resultMessage);
	}
}
function createScrutiny(){
	submitWithParam('createAllTechnicalScrutinyLine','bidderDetailForm #bidderId' ,'creatScrutinyResp');
}

function creatScrutinyResp(data){
	/*if(data.objectMap.result){
		Alert.info(data.objectMap.message);
	}else{
		Alert.warn(data.objectMap.message);
	}*/
}

function getBidderScrutinyStatus(){
	submitWithParam('getScrutinyStatus','bidderDetailForm #bidderId' ,'bidderScrutinyStatusResp');
}

function bidderScrutinyStatusResp(data){
	$('#tScrutinyStatusDiv').html('');
	$('#cScrutinyStatusDiv').html('');
	$('#aScrutinyStatusDiv').html('');
	var statusClass='';
	var htmlElement='';
	if(data.objectMap.hasOwnProperty('techScrutiny')){
		var count=1;
		if(!$.isEmptyObject(data.objectMap.techScrutiny)){
			$.each(data.objectMap.techScrutiny, function(key, value) {
				var materialName=value.itemBid==null?'':value.itemBid.tahdrMaterial==null?'':value.itemBid.tahdrMaterial.material
						==null?'':value.itemBid.tahdrMaterial.material.name;
			    var status=value.preliminaryScrutinyStatus==null?'PENDING':value.preliminaryScrutinyStatus.toUpperCase();
			    var scrutinyFileStatus=(value.isScrutinySubmitted==null || value.isScrutinySubmitted=='N')?'Preliminary Scrutiny File Not Uploaded':' PreliminaryScrutiny File Uploaded';
			    var scrutinyFileColor=(value.isScrutinySubmitted==null || value.isScrutinySubmitted=='N')?'rejectedStatus':'colorskyblue';
			    switch(status) {
			    case "APPROVED":
			    	statusClass = "approvedStatus";
			        break;
			    case "DEVIATION":
			    	statusClass = "deviationStatus";
			        break;
			    case "REJECTED":
			    	statusClass = "rejectedStatus";
			        break;
			    default:
			    	statusClass = "pendingStatus";
			}
				htmlElement=htmlElement+'<div class="form-group">'+
	             '<div>'+
	             '<label class="col-sm-6">'+count+'.'+materialName+':</label>'+
	                     '<span class="detspan col-sm-6 '+statusClass+'" id="" >'+status+'</span><br><span class="'+scrutinyFileColor+' col-sm-6" id="" >'+scrutinyFileStatus+'</span> </div> </div>';
				count++;
			   });
			$('#tScrutinyStatusDiv').append(htmlElement);
			htmlElement='';
		}else{
			
		}
		htmlElement='';
    }
	if(data.objectMap.hasOwnProperty('commScrutiny')){
		var value=data.objectMap.commScrutiny;
		if(value!=null){
			var cstatus=value.preliminaryScrutinyStatus==null?'PENDING':value.preliminaryScrutinyStatus.toUpperCase();
			var scrutinyFileStatus=(value.isScrutinySubmitted==null || value.isScrutinySubmitted=='N')?'Preliminary Scrutiny File Not Uploaded':' PreliminaryScrutiny File Uploaded';
			 var scrutinyFileColor=(value.isScrutinySubmitted==null || value.isScrutinySubmitted=='N')?'rejectedStatus':'colorskyblue';
			switch(cstatus) {
			    case "APPROVED":
			    	statusClass = "approvedStatus";
			        break;
			    case "DEVIATION":
			    	statusClass = "deviationStatus";
			        break;
			    case "REJECTED":
			    	statusClass = "rejectedStatus";
			        break;
			    default:
			    	statusClass = "pendingStatus";
			}
			htmlElement=htmlElement+'<span class="detspan '+statusClass+'" id="" >'+cstatus+'</span><br><span class="'+scrutinyFileColor+' col-sm-6" id="" >'+scrutinyFileStatus+'</span>';
			$('#cScrutinyStatusDiv').append(htmlElement);
			htmlElement='';
			
			 var astatus=value.preliminaryAuditorStatus==null?'PENDING':value.preliminaryAuditorStatus.toUpperCase();
			 switch(astatus) {
			    case "APPROVED":
			    	statusClass = "approvedStatus";
			        break;
			    case "CLARIFICATION":
			    	statusClass = "clarificationStatus";
			        break;
			    default:
			    	statusClass = "pendingStatus";
			}
			htmlElement=htmlElement+'<span class="detspan '+statusClass+'" id="" >'+astatus+'</span>';
			$('#aScrutinyStatusDiv').append(htmlElement);
			htmlElement='';
		}else{
			statusClass="pendingStatus"
		    var status="PENDING"
			htmlElement=htmlElement+'<span class="detspan '+statusClass+'" id="" >'+status+'</span>';
			$('#cScrutinyStatusDiv').append(htmlElement);
			htmlElement='';
			htmlElement=htmlElement+'<span class="detspan '+statusClass+'" id="" >'+status+'</span>';
			$('#aScrutinyStatusDiv').append(htmlElement);
			htmlElement='';
		}
    }
}

function fetchTenderList(pageNumber, pageSize, searchMode, searchValue){
	var typecode=$('input[name=tenderTypeCodeToggle]:checked').val();
	var response;
	$.ajax({
        type : "POST",
        contentType : "application/json",
        url: "fetchTenderListForPreliminaryScrutiny?pageNumber="+pageNumber+"&pageSize="+pageSize+'&searchMode='+searchMode+'&serachValue='+searchValue+'&typeCode='+typecode,
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

