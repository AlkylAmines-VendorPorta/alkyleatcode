var tenderDetailArray=new Array();
var itemBidArray=new Array();
var bidderArray=new Array();
var bidderGtpArray=new Array();
var scrutinyPointArray=new Array();
var documentsArray=new Array();
var statusList=new Array();

var tenderCode='';
var tenderContact='';
var tenderDescription='';
var tenderDepartment='';
var tenderTypeCode='';
$(document).ready(function(){
	 debugger;
	 $("#tabstrip").kendoTabStrip();
     $('#leftPaneData').paginathing({perPage: 6});
     var dataUrl=$('#myTenderUrl').val();
 	if(dataUrl==null || dataUrl=='' || dataUrl ==undefined ){
 		onPageLoad();
 	}else{
 		$('#worksToggle').removeClass('active');
		$('#worksCheckBoxId').removeAttr('checked');
		$('#auctionsCheckBoxId').removeAttr('checked');
 		submitToURL(dataUrl, "loadTenderListForScrutiny");
 		setToggle(tenderTypeCode);
 	}
     
	$("#tenderDetailTab").on('click',function(event){
		  debugger;
		event.preventDefault();
		var tenderTypeCode=$('input[name=tenderTypeCodeToggle]:checked').val();
		cacheLi();
		setCurrentTab(this);
		if(getChangedFlag()){
			submitToURL("getBidderTenderListForDeviation/"+tenderTypeCode, "loadTenderListForScrutiny");
			$('.deviationTabs').addClass('readonly');
			$('.technicalComfirmation').addClass('readonly');
			$('.commercialConfirmation').addClass('readonly');
			setChangedFlag(false);
		}else{
			getCacheLi();
			var documentType=$(".documentType").val();
			/*setHeaderValues(documentType+"No.: " + tahdrCodes, documentType+"Title : " + title, "Department : " + department, "Status : " + status);*/
			setHeaderValues(documentType+" Code:" +tenderCode , documentType+" Contact : "+tenderContact, "Description: "+tenderDescription, "Department : "+tenderDepartment);
			
			
			
			//setHeaderValues("Tender Code: "+tenderCode, "Tender Contact : "+tenderContact, "Description: "+tenderDescription, "Department : "+tenderDepartment);
		}
	});
	
	$("input[name='tenderTypeCodeToggle']").on("change",function(){
		$('.pagination').children().remove();
		var tahdrList=submitToURL("getBidderTenderListForDeviation/"+$(this).val(), "loadTenderListForScrutiny");
		if($('.leftPaneData li').length==0){
			$('.bidderTabs').addClass('readonly');
			$('.deviationTabs').addClass('readonly');
		}
	    $(".tabs-left li a label").text(function(index, currentText){
		    return currentText.substr(0, 20);
		});	
	});
});

function downloadTechnicalDeviationPdf(){
	debugger;
	var bidderId=$('#bidderForm #bidderId').val();
	var itemBidId=$("#itemDetailForm #itemBidId").val();
	event.preventDefault();
	showLoader();
	directSubmit(event,"gennerateTechnicalDeviationDoc","generateTechnicalDeviationReport/"+bidderId+"/"+itemBidId);
	hideLoader();
}
function downloadCommercialDeviationPdf(){
	debugger;
	var bidderId=$('#bidderForm #bidderId').val();
	event.preventDefault();
	showLoader();
	directSubmit(event,"gennerateCommercialDeviationDoc","generateCommercialDeviationReport/"+bidderId);
	hideLoader();
}
function onPageLoad(){
	debugger;
	var typeCode=$("input[name='tenderTypeCodeToggle']:checked").val();
	submitToURL("getBidderTenderListForDeviation/"+typeCode, "loadTenderListForScrutiny");
	$('.bidderTabs').addClass('readonly');
	$('.deviationTabs').addClass('readonly');
	
	setCurrentTab($("#tenderDetailTab"));
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
function fileResponseAttachmentDeleteResp(data){
	
	if(!data.hasError){		
       $('#fileResponseuploadId').val('');
	   $('#fileResponseId').val('');
	   $("#a_fileResponse").html('');
	   $('.fileResponseId').attr('disabled',true);
	   Alert.info(data.message);
    }else{
    	Alert.warn(data.message);
    }
}
function loadTenderListForScrutiny(data){
	debugger;
	$("#tahdrDetailForm #tenderNo").html("");
	$("#tahdrDetailForm #description").html("");
	$("#tahdrDetailForm #department").html("");
	$("#tahdrDetailForm #tenderType").html("");
	var documentType=$(".documentType").val();
	/*setHeaderValues(documentType+"No.: " + tahdrCodes, documentType+"Title : " + title, "Department : " + department, "Status : " + status);*/
	setHeaderValues(documentType+" No.:", documentType+" Title : ", "Contact EmailId : ", "Description : ");
	
	//setHeaderValues("Tender No.: ", "Tender Title : ", "Contact EmailId : ", "Description : ");
	        $('.pagination').children().remove();
			$(".leftPaneData").html("");
			var leftPanelHtml="";
			var i=0;
			var firstRow=null;
			statusList=data.objectMap.statusList;
			$.each(data.objectMap.tahdrList,function(key,value){
				debugger;
				if(!$.isEmptyObject(value)){
					    var tahdrDetailId = value.tahdrDetail==null?'':value.tahdrDetail[0].tahdrDetailId==null?'': value.tahdrDetail[0].tahdrDetailId;
						var tahdrCode = value.tahdrCode==null?'': value.tahdrCode;
						var title = value.title==null?'':value.title;
						var version  = value.tahdrDetail[0].version==null?'':value.tahdrDetail[0].version;
						var emdFee 	  = value.tahdrDetail[0].emdFee==null?'':value.tahdrDetail[0].emdFee;
						var description  = value.tahdrDetail[0].description==null?'':value.tahdrDetail[0].description;
						var tahdrValidity = value.tahdrDetail[0].tahdrValidity==null?'':value.tahdrDetail[0].tahdrValidity;
						var contactEmailId= value.tahdrDetail[0].contactEmailId==null?'':value.tahdrDetail[0].contactEmailId;
						var minQuantity= value.tahdrDetail[0].minQuantity==null?'':value.tahdrDetail[0].minQuantity;
						
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
					    +'	<label class="col-xs-6" id="'+tahdrDetailId+'_tahdrValidity">'+tahdrValidity+'</label>'
						+'	<label class="col-xs-6" id="'+tahdrDetailId+'_emdFee">'+emdFee+'</label>'
						+' </div>'
					    +' </a>'
					    +' </li>';
						i++;
					}
				
			});
			$(".leftPaneData").html(leftPanelHtml);
			loadTahdrDetailRightPane(firstRow);
			$('#leftPaneData').paginathing({perPage: 6});
		 $(".tabs-left li a label").text(function(index, currentText) {
			    return currentText.substr(0, 20);
			});	
			
}

function loadTahdrDetailRightPane(data){
	debugger;
		if(!$.isEmptyObject(data))
		{
			    var tahdrDetailId = data.tahdrDetail==null?'':data.tahdrDetail[0].tahdrDetailId==null?'': data.tahdrDetail[0].tahdrDetailId;
			    
			    var contactEmailId = data.tahdrDetail[0].contactEmailId==null?'': data.tahdrDetail[0].contactEmailId;
				var tahdrCode = data.tahdrCode==null?'': data.tahdrCode;
				
				var tenderNo = data.title==null?'': data.title;
				var department = data.department==null?'':data.department.name;
				var description  = data.tahdrDetail[0].description==null?'':data.tahdrDetail[0].description;
				var submissionDate = data.tahdrDetail[0].deviationToDate==null?'':data.tahdrDetail[0].deviationToDate;
				var tenderType  = data.tahdrTypeCode=='PT'?'Procurement':'Works';
				var tahdrTypeCode = data.tahdrTypeCode==null?'': data.tahdrTypeCode;
				
				tenderTypeCode=tahdrTypeCode;
				
				tenderCode=tahdrCode;
				tenderContatct=contactEmailId;
				tenderDepartment=department;
				tenderDescription=description;
				
				if(tahdrTypeCode=='WT'){
					$('.pTSGtpFormTabId').hide();
				}else{
					$('.pTSGtpFormTabId').show();
				}
				
				$("#tahdrDetailForm #submissionDate").html(submissionDate==''?'':formatDateTime(submissionDate));
				$("#tahdrDetailForm #tenderNo").html(tenderNo);
				$("#tahdrDetailForm #description").html(description);
				$("#tahdrDetailForm #department").html(department);
				$("#tahdrDetailForm #tenderType").html(tenderType);
				$("#tahdrDetailForm #tahdrDetailId").val(tahdrDetailId);
				
				$('.bidderTabs').removeClass('readonly');
				var documentType=$(".documentType").val();
				/*setHeaderValues(documentType+"No.: " + tahdrCodes, documentType+"Title : " + title, "Department : " + department, "Status : " + status);*/
				setHeaderValues(documentType+" No.:"+tahdrCode, documentType+" Title : "+tenderNo, "Contact Email Id : "+contactEmailId, "Description : "+description);
				
				
				//setHeaderValues("Tender No.: "+tahdrCode, "Tender Title : "+tenderNo, "Contact Email Id : "+contactEmailId, "Description : "+description);
		}else{
			if(tahdrTypeCode=='WT'){
				$('.pTSGtpFormTabId').hide();
			}else{
				$('.pTSGtpFormTabId').show();
			}
			
			$("#tahdrDetailForm #submissionDate").html('');
			$("#tahdrDetailForm #tenderNo").html('');
			$("#tahdrDetailForm #description").html('');
			$("#tahdrDetailForm #department").html('');
			$("#tahdrDetailForm #tenderType").html('');
			$("#tahdrDetailForm #tahdrDetailId").val('');
		}
		setChildLoadFlag(true);	
}
function showTahdrDetail(id)
{
	loadTahdrDetailRightPane(tenderDetailArray['tender_'+id]);
}
function loadBidder(ele){
	cacheLi();
	setCurrentTab(ele);
	if(getChangedFlag()){
		submitWithParam('getDeviationBidderListByTahdrDetailId','tahdrDetailId' ,'loadBidderListForScrutiny');
		setChangedFlag(false);
	}else{
		getCacheLi();
	}
}
function loadBidderListForScrutiny(data){
	$('.pagination').children().remove();
	$("#bidderForm #name").html("");
	$("#bidderForm #panno").html("");
	$("#bidderForm #gstNo").html("");
	$("#bidderForm #crnNumber").html("");
	setHeaderValues("Partner Name: ", "PAN Number : ", "GSTIN Number : ", "CRN Number : ");
	
			$(".leftPaneData").html("");
			var leftPanelHtml="";
			var i=0;
			var firstRow=null;
			$.each(data.objectMap.bidderList,function(key,value){
				if(!$.isEmptyObject(value.partner))
					{
					    var bidderId = value.bidderId==null?'': value.bidderId;
					    var bpartnerId = value.partner.bPartnerId==null?'': value.partner.bPartnerId;
					    var name = value.partner.name==null?'': value.partner.name;
						var panno = value.partner.panNumber==null?'': value.partner.panNumber;
						var gstNo = value.partner.gstinNo==null?'':value.partner.gstinNo;
						var crnNumber  = value.partner.crnNumber==null?'':value.partner.crnNumber;
						
						bidderArray['bidder_'+bidderId]=value;
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
						+'	<label class="col-xs-6" id="'+bidderId+'_crnNumber">'+crnNumber+'</label>'
						+' </div>'
					    +' </a>'
					    +' </li>';
						i++;
					}
				
			});
			$(".leftPaneData").html(leftPanelHtml);
			loadBidderDetailRightPane(firstRow);
			$('#leftPaneData').paginathing({perPage: 6});
			
			$('.ConfirmTabs').removeClass('readonly');
}

function loadBidderDetailRightPane(data){
		if(!$.isEmptyObject(data)){
			    var bidderId = data.bidderId==null?'': data.bidderId;
			    var bpartnerId = data.partner.bPartnerId==null?'': data.partner.bPartnerId;
			    var name = data.partner.name==null?'': data.partner.name;
				var panno = data.partner.panNumber==null?'': data.partner.panNumber;
				var gstNo = data.partner.gstinNo==null?'':data.partner.gstinNo;
				var crnNumber  = data.partner.crnNumber==null?'':data.partner.crnNumber;
				
				$("#bidderForm #bidderId").val(bidderId);
				$("#bidderForm #bpartnerId").val(bpartnerId);
				$("#bidderForm #name").html(name);
				$("#bidderForm #panno").html(panno);
				$("#bidderForm #gstNo").html(gstNo);
				$("#bidderForm #crnNumber").html(crnNumber);
				
				$("#itemDetailForm .bidderId").val(bidderId);
				
				$('.deviationTabs').addClass('readonly');
				$('.scrutinyTab').removeClass('readonly');
				$('.commDocTab').removeClass('readonly');
				$('#commercialConfirmTabId').removeClass('readonly');
				$('#itemTabId').removeClass('readonly');
				/*$('.technicalConfirmation').addClass('readonly');
				$('.commercialConfirmation').addClass('readonly');*/
				
				setHeaderValues("Partner Name: "+name, "PAN Number : "+panno, "GSTIN Number : "+gstNo, "CRN Number : "+crnNumber);
		}
		else{
			$("#bidderForm #bidderId").val('');
			$("#bidderForm #bpartnerId").val('');
			$("#bidderForm #name").html('');
			$("#bidderForm #panno").html('');
			$("#bidderForm #gstNo").html('');
			$("#bidderForm #crnNumber").html('');
			
			$("#itemDetailForm .bidderId").val('');
			$('.scrutinyTab').addClass('readonly');
			$('.commDocTab').addClass('readonly');
			/*$('#commercialConfirmTabId').addClass('readonly');*/
			$('#itemTabId').addClass('readonly');
			$('#technicalConfirmTabId').addClass('readonly');
		}
		setChildLoadFlag(true);
}
function showBidderDetail(bidderId){
	loadBidderDetailRightPane(bidderArray['bidder_'+bidderId])
}
function loadItemList(ele){
	cacheLi();
	setCurrentTab(ele);
	/*if(getChangedFlag()){*/
		submitWithParam('getItemListBybidderId','bidderId' ,'loadItemListForScrutiny');
		/*setChangedFlag(false);
	}else{
		getCacheLi();
	}*/
}
function loadItemListForScrutiny(data){
	debugger;
	$('.pagination').children().remove();
	$(".leftPaneData").html("");
	var leftPanelHtml="";
	var i=0;
	var firstRow=null;
	$.each(data.objectMap.ItemList,function(key,value){
		if(!$.isEmptyObject(value.itemBid.tahdrMaterial)){
					var itemBidId = value.itemBid.itemBidId==null?'': value.itemBid.itemBidId;
				    var name = value.itemBid.tahdrMaterial.material==null?'': value.itemBid.tahdrMaterial.material.name==null?'': value.itemBid.tahdrMaterial.material.name;
				    var uomName = value.itemBid.tahdrMaterial.material==null?'': value.itemBid.tahdrMaterial.material.uom==null?'': value.itemBid.tahdrMaterial.material.uom.name==null?'': value.itemBid.tahdrMaterial.material.uom.name;
					var description = value.itemBid.tahdrMaterial.material==null?'': value.itemBid.tahdrMaterial.material.description==null?'':  value.itemBid.tahdrMaterial.material.description;
					var hsnCode =  value.itemBid.tahdrMaterial.material==null?'': value.itemBid.tahdrMaterial.material.hsnCode==null?'':value.itemBid.tahdrMaterial.material.hsnCode.code==null?'': value.itemBid.tahdrMaterial.material.hsnCode.code;
					
					itemBidArray["itemBid_"+itemBidId]=value.itemBid;
					if(i==0){
						firstRow = value.itemBid;
						
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
	$('#leftPaneData').paginathing({perPage: 6});
	
	/*if(!$.isEmptyObject(data.objectMap.deviationTypeCode))
	loadReferenceListById('PTSGtpForm #deviationType',data.objectMap.deviationTypeCode);
	loadReferenceListById('PTSDocForm #deviationType',data.objectMap.deviationTypeCode);
	loadReferenceListById('PCSSPForm #deviationType',data.objectMap.deviationTypeCode);
	loadReferenceListById('PCSDocForm #deviationType',data.objectMap.deviationTypeCode);*/
	
	}
function loadItemBidRightPane(data){
	 debugger;
		if(!$.isEmptyObject(data)){
			  var itemBidId = data.itemBidId==null?'': data.itemBidId;
			    var name = data.tahdrMaterial.material.materialId==null?'': data.tahdrMaterial.material.name;
			    var code = data.tahdrMaterial.material.materialId==null?'': data.tahdrMaterial.material.code;
			    var uomName = data.tahdrMaterial.material.uom.name==null?'': data.tahdrMaterial.material.uom.name;
				var description = data.tahdrMaterial.material.description==null?'':  data.tahdrMaterial.material.description;
				var hsnCode =  data.tahdrMaterial.material.code==null?'':data.tahdrMaterial.material.code;
				var requiredQuantity =  data.tahdrMaterial.quantity==null?'':data.tahdrMaterial.quantity;
				var specVersion =  data.tahdrMaterial.materialVersion==null?'':data.tahdrMaterial.materialVersion.name;
				var tenderMaterialType =  data.tahdrMaterial.materialTypeCode==null?'':data.tahdrMaterial.materialTypeCode;
				var tahdrMaterialId= data.tahdrMaterial.tahdrMaterialId==null?'':data.tahdrMaterial.tahdrMaterialId;
				/*var bidderGtpId= data.technicalBid.bidderGtp.bidderGtpId==null?'':data.technicalBid.bidderGtp.bidderGtpId;*/
				
				
				$("#itemDetailForm .itemBidId").val(itemBidId);
				$("#itemDetailForm #itemBidId").val(itemBidId);
				$("#itemDetailForm #matName").html(name);
				$("#itemDetailForm #matCode").html(code);
				$("#itemDetailForm #uom").html(uomName);
				$("#itemDetailForm #description").html(description);
				$("#itemDetailForm #tahdrMaterialId").val(tahdrMaterialId);
				$("#itemDetailForm #reqQuantity").html(requiredQuantity);
				$("#itemDetailForm #specVersion").html(specVersion);
				$("#itemDetailForm #tenderMatType").html(tenderMaterialType);
				$('#confirmationTechnicalDevitionForm #itemBidId').val(itemBidId);
				$("#technicalDeviationForm #itemBidId").val(itemBidId);
				$("#technicalDocumentDeviationForm #itemBidId").val(itemBidId);
				
				
				$('.deviationTabs').removeClass('readonly');
				
				/*$('.technicalConfirmation').addClass('readonly');
				$('.commercialConfirmation').addClass('readonly');*/
		}else{
			$("#itemDetailForm .itemBidId").val('');
			$("#itemDetailForm #itemBidId").val('');
			$("#itemDetailForm #matName").html('');
			$("#itemDetailForm #matCode").html('');
			$("#itemDetailForm #uom").html('');
			$("#itemDetailForm #description").html('');
			$("#itemDetailForm #tahdrMaterialId").val('');
			$("#itemDetailForm #reqQuantity").html('');
			$("#itemDetailForm #specVersion").html('');
			$("#itemDetailForm #tenderMatType").html('');
			
			$("#technicalDeviationForm #itemBidId").val('');
			$("#technicalDocumentDeviationForm #itemBidId").val('');
			
			$('.deviationTabs').addClass('readonly');
		}
		/*setChildLoadFlag(true);*/
}
function showItemDetail(el){
	var itemBidId=$(el).attr('id');
	loadItemBidRightPane(itemBidArray['itemBid_'+itemBidId]);
}
function loadBidderGtpList(ele){
	cacheLi();
	setCurrentTab(ele);
	/*if(getChangedFlag()){*/
		submitWithParam('getBidderGtpByItemBidId','itemDetailForm #itemBidId' ,'loadBidderGtpListForScrutiny');
		/*setChangedFlag(false);
	}else{
		getCacheLi();
	}*/
}
function loadBidderGtpListForScrutiny(data){
	debugger;
	$('.pagination').children().remove();
			 
			$(".leftPaneData").html("");
			var leftPanelHtml="";
			var i=0;
			var firstRow=null;
			$.each(data.objectMap.bidderGtpList,function(key,value){
				if(!$.isEmptyObject(value)){
							var bidderGtpId = value.bidderGtp.bidderGtpId==null?'': value.bidderGtp.bidderGtpId;
							var gtpType =  value.bidderGtp.tahdrMaterialgtp.gtp.gtpParameterType.name==null?'':value.bidderGtp.tahdrMaterialgtp.gtp.gtpParameterType.name;
							var gtpName =  value.bidderGtp.tahdrMaterialgtp.gtp.name==null?'':value.bidderGtp.tahdrMaterialgtp.gtp.name;
							
						bidderGtpArray["bidderGtp_"+bidderGtpId]=value;
						if(i==0){
							firstRow = value;
							
							leftPanelHtml = leftPanelHtml + ' <li class="active" id="'+bidderGtpId+'"  onclick="showBidderGtpDetail(this)">';
						}else{
							leftPanelHtml = leftPanelHtml + ' <li class="itemBidLi" id="'+bidderGtpId+'"  onclick="showBidderGtpDetail(this)">';
						}
					
						leftPanelHtml = leftPanelHtml +' <a href="#Master" data-toggle="tab">'
					    +' <div class="col-md-12">'
					    +'  <label class="col-xs-6" id="'+bidderGtpId+'_manufacturer">'+gtpType+'</label>'
					    +'	<label class="col-xs-6" id="'+bidderGtpId+'_onAfRating">'+gtpName+'</label>'
					    +' </div>'	
					    +' <div class="col-md-12">'
					    +'	<label class="col-xs-6" id="'+bidderGtpId+'_service"></label>'
						+'	<label class="col-xs-6" id="'+bidderGtpId+'_onAnRating"></label>'
						+' </div>'
					    +' </a>'
					    +' </li>';
						i++;
					}
			});
			$(".leftPaneData").html(leftPanelHtml);
			loadBidderGtpRightPane(firstRow);
			$('#leftPaneData').paginathing({perPage: 6});
			
			
			if(data.objectMap.role=='VENADM')
				{
				 $('#technicalDeviationForm  .finalStatus').attr('disabled','disabled');
				 $('#technicalDeviationForm #finalScrutinyDivId').hide();
			/*	 $('#technicalDeviationForm .save').attr('onclick','return submitIt("technicalDeviationForm","saveTechnicalDeviationResponse","bidderDeviationResponseSubmitResp");');*/
				 $('#technicalDeviationForm .save').attr('onclick','return submitIt("technicalDeviationForm","saveTechnicalDeviationResponse","bidderGtpDeviationSubmitResp");');
				}
			else
				{
				  $('#technicalDeviationForm .finalStatus').removeAttr('disabled');
				  $('#technicalDeviationForm #finalScrutinyDivId').show();
				  $('#technicalDeviationForm #finalScrutinyDivId').show();
				  $('#technicalDeviationForm #techFileResponseUploadId').hide();
				  $('#technicalDeviationForm #deleteTechnicalAttachmentId').hide();
				  $('#technicalDeviationForm .save').attr('onclick','return submitIt("technicalDeviationForm","saveFinalTechnicalDeviationResponse","bidderDeviationResponseSubmitResp");');
				/*  $('#technicalDeviationForm #textResponse').attr('readonly','readonly');*/
				  $('#technicalDeviationForm #textResponse').attr('disabled','disabled');
				}
}
function loadBidderGtpRightPane(data){
			 
	debugger;
	$("#technicalDeviationForm")[0].reset();
	$("#technicalDeviationForm #downloadFileResponse").html('');
	$('#technicalDeviationForm .save').show();
			if(!$.isEmptyObject(data)){
					var itemScrutinylineId = data.itemScrutinyLineId==null?'': data.itemScrutinyLineId;
					var itemScrutinyId = data.itemScrutiny==null?'': data.itemScrutiny.itemScrutinyId;
							var bidderGtpId = data.bidderGtp.bidderGtpId==null?'': data.bidderGtp.bidderGtpId;
							var bidderId=data.itemScrutiny.bidder==null?'':data.itemScrutiny.bidder.bidderId;
							var gtpType =  data.bidderGtp.tahdrMaterialgtp.gtp.gtpParameterType.name==null?'':data.bidderGtp.tahdrMaterialgtp.gtp.gtpParameterType.name;
							var gtpName =  data.bidderGtp.tahdrMaterialgtp.gtp.name==null?'':data.bidderGtp.tahdrMaterialgtp.gtp.name;
			    			var deviationType=data.deviationType=='FILE'?'FILE': data.deviationType;
			    			var comment=data.deviationComment==null?'': data.deviationComment;
			    			
			    			var bidderTextComment=data.textResponse==null?'':data.textResponse;
			    			var fileResponse=data.fileResponse==null?'':data.fileResponse.attachmentId==null?'':data.fileResponse.attachmentId;
			    		    var fileResponseName=data.fileResponse==null?'':data.fileResponse.fileName==null?'':data.fileResponse.fileName;
			    		    
			    		    var finalScrutinyComment=data.finalScrutinyComment==null?'':data.finalScrutinyComment;
			    		    
			    		   var url=$("#a_techFileResponse").data('url');
			    			/*$("#downloadFileResponse").attr('href',url);*/
			    		    /*$("#technicalDeviationForm #downloadFileResponse").prop('href', url+'/'+fileResponse);
			    			$("#technicalDeviationForm #downloadFileResponse").html(fileResponseName);*/
			    			
			    			$("#technicalDeviationForm #techFileResponseId").val(fileResponse);
			    			$("#technicalDeviationForm #a_techFileResponse").prop('href', url+'/'+fileResponse);
			    			$("#technicalDeviationForm #a_techFileResponse").html(fileResponseName);
			    			if(fileResponse==''){
			    				/*$("#technicalDeviationForm #downloadFileResponse").removeAttr('href');
			    				$("#technicalDeviationForm #downloadFileResponse").html('');*/
			    				$("#technicalDeviationForm #a_techFileResponse").removeAttr('href');
			    				$("#technicalDeviationForm #a_techFileResponse").html('');
			    				$('#technicalDeviationForm #deleteTechnicalAttachmentId').attr('disabled','disabled');
			    			}
			    			else{
			    				$('#technicalDeviationForm #deleteTechnicalAttachmentId').removeAttr('disabled');
			    			}
			    		    if(bidderTextComment!='')
			    		    	{
				    		    	 $("#technicalDeviationForm #textResponse").val(bidderTextComment);
				    		    	/* $("#technicalDeviationForm #textResponse").addClass('readonly');*/
				    		    	/* $("#technicalDeviationForm .actionbtn").hide();*/
			    		    	}
			    		    else{
			    		    	/* $("#technicalDeviationForm .actionbtn").show();*/
			    		    	/* $("#technicalDeviationForm #textResponse").removeClass('readonly');*/
			    		    	/* $("#technicalDeviationForm #techFileResponseUploadId").show();*/
			    		    }
			    		    	
							
			    		    $("#technicalDeviationForm #deviationType").html(deviationType);
			    			$("#technicalDeviationForm #itemScrutinyLineId").val(itemScrutinylineId);
			    			$("#technicalDeviationForm #bidderId").val(bidderId);
			    			$("#technicalDeviationForm #itemScrutinyId").val(itemScrutinyId);
			    			$("#confirmationTechnicalDevitionForm #itemScrutinyId").val(itemScrutinyId);
			    			$("#confirmationTechnicalDevitionForm #bidderId").val(bidderId);
							$("#technicalDeviationForm #bidderGtpId").val(bidderGtpId);
							
							$("#technicalDeviationForm #gtpType").html(gtpType);
							$("#technicalDeviationForm #gtp").html(gtpName);
							
							$("#technicalDeviationForm #finalScrutinyComment").val(finalScrutinyComment);
			    			/*$("#technicalDeviationForm #deviationType").val(deviationType);*/
			    			$("#technicalDeviationForm #deviationComment").html(comment);
			    			
			    			if(deviationType=='FILE')
							{
								 $('#technicalDeviationForm #fileResponseDivId').removeAttr('style');
								 $('#technicalDeviationForm #textResponseDivId').attr('style','display: none;');
								 $('#technicalDeviationForm #textResponseDivId').attr('disabled','disabled');
								 $('#technicalDeviationForm .fileResponse').removeAttr('disabled');
								 $('#technicalDeviationForm .textResponse').attr('disabled','disabled');
							}
							else
								{
									$('#technicalDeviationForm #textResponseDivId').removeAttr('style');
									$('#technicalDeviationForm #fileResponseDivId').attr('style','display: none;');
									$('#technicalDeviationForm .fileResponse').attr('disabled','disabled');
									$('#technicalDeviationForm .textResponse').removeAttr('disabled');
								}
								
								$('#technicalConfirmTabId').removeClass('readonly');
								$("#technicalDeviationForm").removeClass('readonly');
								
								if(fileResponse!='' || bidderTextComment!=''){
								/*	$('#technicalDeviationForm .save').hide();*/
								}
								$('.bidderGtpTab').removeClass('readonly');
					}
			else{
				$('#technicalDeviationForm .save').hide();
				$('#technicalDeviationForm').addClass('readonly');
				$("#technicalDeviationForm #gtpType").html('');
				$("#technicalDeviationForm #gtp").html('');
				$("#technicalDeviationForm #deviationComment").html('');
				$("#downloadFileResponse").removeAttr('href');
				$("#technicalDeviationForm #downloadFileResponse").html('');
				$('.bidderGtpTab').addClass('readonly');
				$('#technicalConfirmTabId').addClass('readonly');
				$("#technicalDeviationForm #deviationType").html('');
				$("#technicalDeviationForm #a_techFileResponse").removeAttr('href');
				$("#technicalDeviationForm #a_techFileResponse").html('');
				$("#technicalDeviationForm #textResponse").val();
				$('#technicalDeviationForm #deleteTechnicalAttachmentId').attr('disabled','disabled');
			}
			/*setChildLoadFlag(true);*/
}
function showBidderGtpDetail(el){
	var bidderGtpId=$(el).attr('id');
	loadBidderGtpRightPane(bidderGtpArray["bidderGtp_"+bidderGtpId]);
}
function bidderDeviationResponseSubmitResp(data){
	 
	if(data.objectMap.resultStatus==true){
			Alert.info(data.objectMap.Status);
	}
	else{
			Alert.warn(data.objectMap.Status);
	}
	
}
function loadScrutinyPointList(ele){
		cacheLi();
		setCurrentTab(ele);
		/*if(getChangedFlag()){*/
			submitWithParam('getScrutinyPointByBidderId','bidderId' ,'loadScrutinyPointListForScrutiny');
			/*setChangedFlag(false);
		}else{
			getCacheLi();
		}*/
}
function loadScrutinyPointListForScrutiny(data){
	debugger;
	$('.pagination').children().remove();
	 if(data.objectMap.scrutinyPointList!=undefined){
		  
			$(".leftPaneData").html("");
			var leftPanelHtml="";
			var i=0;
			var firstRow=null;
			$.each(data.objectMap.scrutinyPointList,function(key,value){
				debugger;
				if(!$.isEmptyObject(value)){
							var itemScrutinyLineId = value.itemScrutinyLineId==null?'': value.itemScrutinyLineId;
							 var scrutinypointName=value.scrutinyPoint.name==null?'': value.scrutinyPoint.name;
							 var scrutinypointDesp=value.scrutinyPoint.description==null?'': value.scrutinyPoint.description;
						scrutinyPointArray["itemScrutinyLine_"+itemScrutinyLineId]=value;
						if(i==0){
							firstRow = value;
							
							leftPanelHtml = leftPanelHtml + ' <li class="active" id="'+itemScrutinyLineId+'"  onclick="showScrutinyPointDetail(this)">';
						}else{
							leftPanelHtml = leftPanelHtml + ' <li  id="'+itemScrutinyLineId+'"  onclick="showScrutinyPointDetail(this)">';
						}
					
						leftPanelHtml = leftPanelHtml +' <a href="#Master" data-toggle="tab">'
					    +' <div class="col-md-12">'
					    +'  <label class="col-xs-6" id="'+itemScrutinyLineId+'_bidsection">'+scrutinypointName+'</label>'
					    +'	<label class="col-xs-6" id="'+itemScrutinyLineId+'_"></label>'
					    +' </div>'	
					    +' <div class="col-md-12">'
					    +'	<label class="col-xs-6" id="'+itemScrutinyLineId+'_"></label>'
						+'	<label class="col-xs-6" id="'+itemScrutinyLineId+'_"></label>'
						+' </div>'
					    +' </a>'
					    +' </li>';
						i++;
					}
			});
			$(".leftPaneData").html(leftPanelHtml);
			loadScrutinyPointRightPane(firstRow);
			$('#leftPaneData').paginathing({perPage: 6});
			
			if(data.objectMap.role=='VENADM')
				{
				 $('#commercialDeviationForm .finalStatus').attr('disabled','disabled');
				 $('#commercialDeviationForm #finalScrutinyDivId').hide();
				 /*$('#commercialDeviationForm .save').attr('onclick','return submitIt("commercialDeviationForm","saveCommercialDeviationResponse","bidderDeviationResponseSubmitResp");');*/
				 $('#commercialDeviationForm .save').attr('onclick','return submitIt("commercialDeviationForm","saveCommercialDeviationResponse","scrutinyDeviationSubmitResp");');
				}
			else
				{
				  $('#commercialDeviationForm .finalStatus').removeAttr('disabled');
				  $('#commercialDeviationForm #finalScrutinyDivId').show();
				  

				  $('#commercialDeviationForm #commFileResponseUploadId').hide();
				  $('#commercialDeviationForm #deletecommercialAttachmentId').hide();
				  
				  $('#commercialDeviationForm .save').attr('onclick','return submitIt("commercialDeviationForm","saveFinalCommercialDeviationResponse","bidderDeviationResponseSubmitResp");');
				  $('#commercialDeviationForm #textResponse').attr('disabled','disabled');
				}

		 }
	 
		 
}
function loadScrutinyPointRightPane(data){
    debugger;
	$("#commercialDeviationForm #downloadFileResponse").html('');
	$("#commercialDeviationForm")[0].reset();
	$("#commercialDeviationForm .save").show();
	if(!$.isEmptyObject(data)){
		 var itemScrutinyLineId = data.itemScrutinyLineId==null?'': data.itemScrutinyLineId;
		 var bidderId=data.itemScrutiny.bidder==null?'':data.itemScrutiny.bidder.bidderId;
		 var scrutinypointName=data.scrutinyPoint.name==null?'': data.scrutinyPoint.name;
		 var scrutinypointDesp=data.scrutinyPoint.description==null?'': data.scrutinyPoint.description;
		 var itemScrutinyId=data.itemScrutiny.itemScrutinyId==null?'': data.itemScrutiny.itemScrutinyId;
		 var scrutinyPointId=data.scrutinyPoint.scrutinyPointId==null?'': data.scrutinyPoint.scrutinyPointId;
		 
		 var deviationType=data.deviationType=='FILE'?'FILE': data.deviationType;
	     var comment=data.deviationComment==null?'': data.deviationComment;
	     
	     var finalScrutinyComment=data.finalScrutinyComment==null?'':data.finalScrutinyComment;
	     $("#commercialDeviationForm #finalScrutinyComment").val(finalScrutinyComment);
	     $("#commercialDeviationForm #deviationType").html(deviationType);
	     var bidderTextComment=data.textResponse==null?'':data.textResponse;
			$("#commercialDeviationForm #textResponse").val(bidderTextComment);
			
			var bidderFileId=data.fileResponse==null?'':data.fileResponse.attachmentId;
			var bidderFileName=data.fileResponse==null?'':data.fileResponse.fileName;
			var url=$("#a_commFileResponse").data('url');
			$("#commercialDeviationForm #commFileResponseId").val(bidderFileId);
			$("#commercialDeviationForm #a_commFileResponse").prop('href', url+'/'+bidderFileId);
			$("#commercialDeviationForm #a_commFileResponse").html(bidderFileName);
			if(bidderFileId==''){
				/*$("#technicalDeviationForm #downloadFileResponse").removeAttr('href');
				$("#technicalDeviationForm #downloadFileResponse").html('');*/
				$("#commercialDeviationForm #a_commFileResponse").removeAttr('href');
				$("#commercialDeviationForm #a_commFileResponse").html('');
				$('#commercialDeviationForm #deletecommercialAttachmentId').attr('disabled','disabled');
			}
			else{
				$('#commercialDeviationForm #deletecommercialAttachmentId').removeAttr('disabled');
			}
	    			/*var url=$("#commercialDeviationForm #downloadFileResponse").data('url');
	    			$("#commercialDeviationForm #downloadFileResponse").attr('href',url);
	    		    $("#commercialDeviationForm #downloadFileResponse").prop('href', url+'/'+bidderFileId);
	    			$("#commercialDeviationForm #downloadFileResponse").html(bidderFileName);
	    			if(bidderFileId==''){
	    				$("#commercialDeviationForm #downloadFileResponse").removeAttr('href');
	    				$("#commercialDeviationForm #downloadFileResponse").html('');
	    				$("#commercialDeviationForm #a_commFileResponse").html('');
	    			}
	    			else{
	    				$("#commercialDeviationForm #a_commFileResponse").html('');
	    			}*/
		 
		 $("#commercialDeviationForm #itemScrutinyLineId").val(itemScrutinyLineId);
		 
		 $("#confirmationCommercialDevitionForm #itemScrutinyId").val(itemScrutinyId);
		 $("#confirmationCommercialDevitionForm #bidderId").val(bidderId);
		 
		 $("#commercialDeviationForm #scrutinyPointName").html(scrutinypointName);
		 $("#commercialDeviationForm #scrutinyPointDesp").html(scrutinypointDesp);
		 $("#commercialDeviationForm #itemScrutinyId").val(itemScrutinyId);
		 $("#commercialDeviationForm #scrutinyPointId").val(scrutinyPointId);
		 
		 $("#commercialDeviationForm #deviationComment").html(comment);
		 

			if(deviationType=='FILE')
				{
					 $('#commercialDeviationForm #fileResponseDivId').removeAttr('style');
					 $('#commercialDeviationForm #textResponseDivId').attr('style','display: none;');
					 $('#commercialDeviationForm #textResponseDivId').attr('disabled','disabled');
					 $('#commercialDeviationForm .fileResponse').removeAttr('disabled');
					 $('#commercialDeviationForm .textResponse').attr('disabled','disabled');
				}
		   else
				{
					$('#commercialDeviationForm #textResponseDivId').removeAttr('style');
					$('#commercialDeviationForm #fileResponseDivId').attr('style','display: none;');
					$('#commercialDeviationForm .fileResponse').attr('disabled','disabled');
					$('#commercialDeviationForm .textResponse').removeAttr('disabled');
				}
			/*$('.commercialConfirmation').removeClass('readonly');*/
			$('#commercialDeviationForm').removeClass('readonly');
			$('#commercialDeviationForm .save').show();
			
			$('#commercialConfirmTabId').removeClass('readonly');
			if(bidderFileId!='' || bidderTextComment!=''){
				/*$("#commercialDeviationForm .save").hide();*/
			}
		}
	else{
		/*$('.scrutinyTab').addClass('readonly');*/
		$("#commercialDeviationForm #a_commFileResponse").removeAttr('href');
		$("#commercialDeviationForm #a_commFileResponse").html('');
		$('#commercialDeviationForm').addClass('readonly');
		$('#commercialDeviationForm .save').hide();
		 $("#commercialDeviationForm #scrutinyPointName").html('');
		 $("#commercialDeviationForm #scrutinyPointDesp").html('');
		 $("#commercialDeviationForm #itemScrutinyId").val('');
		 $("#commercialDeviationForm #scrutinyPointId").val('');
		 $("#commercialDeviationForm #deviationType").html('');
		 $("#commercialDeviationForm #deviationComment").html('');
	}
	/*setChildLoadFlag(true);*/
}
function showScrutinyPointDetail(el){
	var scrutinyPointId=$(el).attr('id');
	loadScrutinyPointRightPane(scrutinyPointArray["itemScrutinyLine_"+scrutinyPointId]);
}

function loadTSDocumentDetail(ele){
	cacheLi();
	setCurrentTab(ele);
	/*if(getChangedFlag()){*/
		submitWithParam('getTechnicalDocumentsByItemBidId','itemDetailForm #itemBidId' ,'loadTechnicalDocumentList');
		/*setChangedFlag(false);
	}else{
		getCacheLi();
	}*/
}
function loadCSDocumentDetail(ele){
	submitWithParam('getCommercialDocumentsBybidderId','bidderId' ,'loadCommercialDocumentList');
}
function loadTechnicalDocumentList(data){
	loadDocumentListForScrutiny(data);
	if(data.objectMap.role=='VENADM')
	{
	 $('#technicalDocumentDeviationForm .finalStatus').attr('disabled','disabled');
	 $('#technicalDocumentDeviationForm #finalScrutinyDivId').hide();
	/* $('#technicalDocumentDeviationForm .save').attr('onclick','return submitIt("technicalDocumentDeviationForm","saveTechnicalDocumentDeviationResponse","bidderDeviationResponseSubmitResp");');*/
	 $('#technicalDocumentDeviationForm .save').attr('onclick','return submitIt("technicalDocumentDeviationForm","saveTechnicalDocumentDeviationResponse","techDocDeviationSubmitResp");');
	 }
else{
	  $('#technicalDocumentDeviationForm .finalStatus').removeAttr('disabled');
	  $('#technicalDocumentDeviationForm #finalScrutinyDivId').show();
	  
	  $('#technicalDocumentDeviationForm #deleteTechDocAttachmentId').hide();
	  $('#technicalDocumentDeviationForm #docFileResponseUploadId').hide();
	  
	  $('#technicalDocumentDeviationForm .save').attr('onclick','return submitIt("technicalDocumentDeviationForm","saveTechnicalFinalDocumentDeviationResponse","bidderDeviationResponseSubmitResp");');
	  $('#technicalDocumentDeviationForm #textResponse').attr('disabled','disabled');
	}
}
function loadCommercialDocumentList(data){
	loadDocumentListForScrutiny(data);
	if(data.objectMap.role=='VENADM'){
	 $('#commercialDocumentDeviationForm .finalStatus').attr('disabled','disabled');
	 $('#commercialDocumentDeviationForm #finalScrutinyDivId').hide();
	/* $('#commercialDocumentDeviationForm .save').attr('onclick','return submitIt("commercialDocumentDeviationForm","saveCommercialDocumentDeviationResponse","bidderDeviationResponseSubmitResp");');*/
	 $('#commercialDocumentDeviationForm .save').attr('onclick','return submitIt("commercialDocumentDeviationForm","saveCommercialDocumentDeviationResponse","commDocDeviationSubmitResp");');
	 }
else{
	  $('#commercialDocumentDeviationForm .finalStatus').removeAttr('disabled');
	  $('#commercialDocumentDeviationForm #finalScrutinyDivId').show();
	  
	  $('#technicalDocumentDeviationForm #deleteCommDocAttachmentId').hide();
	  $('#technicalDocumentDeviationForm #commDocFileResponseUploadId').hide();
	  
	  $('#commercialDocumentDeviationForm .save').attr('onclick','return submitIt("commercialDocumentDeviationForm","saveCommercialFinalDocumentDeviationResponse","bidderDeviationResponseSubmitResp");');
	  $('#commercialDocumentDeviationForm #textResponse').attr('disabled','disabled');
	}
}

function loadDocumentListForScrutiny(data){
	debugger;
	$('.pagination').children().remove();
	$(".leftPaneData").html("");
	var leftPanelHtml="";
	var i=0;
	var firstRow=null;
	$.each(data.objectMap.documentList,function(key,value){
		debugger;
		if(!$.isEmptyObject(value.bidderSectionDoc)){
			
				 var itemScrutinyLineId=value.itemScrutinyLineId==null?'':value.itemScrutinyLineId;
				 var biddersectionDocId = value.bidderSectionDoc.bidderSectionDocId==null?'': value.bidderSectionDoc.bidderSectionDocId;
				 var bidsection=value.bidderSectionDoc.bidSection==null?'': value.bidderSectionDoc.bidSection;
				 var sectionDoc=value.bidderSectionDoc.sectionDocument==null?'': value.bidderSectionDoc.sectionDocument;
				 var attachment=value.bidderSectionDoc.attachment==null?'': value.bidderSectionDoc.attachment;
				 
				 var sectionDocName=value.bidderSectionDoc.sectionDocument==null?'': value.bidderSectionDoc.sectionDocument.name;
				 var sectionDocDesc=value.bidderSectionDoc.sectionDocument==null?'': value.bidderSectionDoc.sectionDocument.description;
			 
				 documentsArray["bidderSecDoc_"+biddersectionDocId]=value;
					
				
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
	$('#leftPaneData').paginathing({perPage: 6});
	
	/*if(data.objectMap.role=='VENADM')
		{
		 $('#documentDeviationForm .finalStatus').attr('disabled','disabled');
		 $('#documentDeviationForm #finalScrutinyDivId').hide();
		 $('#documentDeviationForm .save').attr('onclick','return submitIt("documentDeviationForm","saveDocumentDeviationResponse","bidderDeviationResponseSubmitResp");');
		}
	else
		{
		  $('#documentDeviationForm .finalStatus').removeAttr('disabled');
		  $('#documentDeviationForm #finalScrutinyDivId').show();
		  $('#documentDeviationForm .save').attr('onclick','return submitIt("documentDeviationForm","saveFinalDocumentDeviationResponse","bidderDeviationResponseSubmitResp");');
		  $('#documentDeviationForm #textResponse').attr('disabled','disabled');
		}*/
	}
function loadDocDetailsRightPane(data)
{
	debugger;
	$("#commercialDocumentDeviationForm #downloadFileResponse").html('');
	$("#technicalDocumentDeviationForm #downloadFileResponse").html('');
	$("#technicalDocumentDeviationForm .save").show();
	$("#commercialDocumentDeviationForm .save").show();
	if(!$.isEmptyObject(data)){
			debugger;
		     var itemScrutinyLineId=data.itemScrutinyLineId==null?'':data.itemScrutinyLineId;
		     var itemScrutinyId=data.itemScrutiny==null?'':data.itemScrutiny.itemScrutinyId;
		    		 if(!$.isEmptyObject(data.bidderSectionDoc)){
			    			 var biddersectionDocId = data.bidderSectionDoc.bidderSectionDocId==null?'': data.bidderSectionDoc.bidderSectionDocId;
			    			 var bidderId=data.itemScrutiny.bidder==null?'':data.itemScrutiny.bidder.bidderId;
			    			 var bidsection=data.bidderSectionDoc.bidSection==null?'': data.bidderSectionDoc.bidSection;
			    			 var sectionDoc=data.bidderSectionDoc.sectionDocument==null?'': data.bidderSectionDoc.sectionDocument;
			    			 var attachment=data.bidderSectionDoc.attachment==null?'': data.bidderSectionDoc.attachment;
			    			 
			    			 var sectionDocName=data.bidderSectionDoc.sectionDocument==null?'': data.bidderSectionDoc.sectionDocument.name;
							 var sectionDocDesc=data.bidderSectionDoc.sectionDocument==null?'': data.bidderSectionDoc.sectionDocument.description;
			    			 
			    			 var deviationType=data.deviationType=='FILE'?'FILE': data.deviationType;
			    		     var comment=data.deviationComment==null?'': data.deviationComment;
			    		     
			    		        var bidderTextComment=data.textResponse==null?'':data.textResponse;
			    				var bidderFileId=data.fileResponse==null?'':data.fileResponse.attachmentId;
			    				var bidderFileName=data.fileResponse==null?'':data.fileResponse.fileName;
			    				/*$("#documentDeviationForm #textResponse").val(bidderTextComment);*/
			    				
			    				var finalScrutinyComment=data.finalScrutinyComment==null?'':data.finalScrutinyComment;
			    				$("#technicalDocumentDeviationForm #finalScrutinyComment").val(finalScrutinyComment);
			    				$("#confirmationTechnicalDevitionForm #itemScrutinyId").val(itemScrutinyId);
			    				$("#confirmationTechnicalDevitionForm #bidderId").val(bidderId);
			    		if(bidsection=='TS'){
			    			$('#technicalConfirmTabId').removeClass('readonly');
			    			$('.techDocTab').removeClass('readonly');
			    			$("#technicalDocumentDeviationForm #biddersectionDocId").val(biddersectionDocId);
			    			 var url=$("#a_docFileResponse").data('url');
				    			$("#technicalDocumentDeviationForm #docFileResponseId").val(bidderFileId);
				    			$("#technicalDocumentDeviationForm #a_docFileResponse").prop('href', url+'/'+bidderFileId);
				    			$("#technicalDocumentDeviationForm #a_docFileResponse").html(bidderFileName);
				    			if(bidderFileId==''){
				    				$("#technicalDocumentDeviationForm #a_docFileResponse").removeAttr('href');
				    				$("#technicalDocumentDeviationForm #a_docFileResponse").html('');
				    				$('#technicalDocumentDeviationForm #deleteTechDocAttachmentId').attr('disabled','disabled');
				    			}
				    			else{
				    				$('#technicalDocumentDeviationForm #deleteTechDocAttachmentId').removeAttr('disabled');
				    			}
			    			/*if(bidderFileId==''){
			    				$("#technicalDocumentDeviationForm #downloadFileResponse").removeAttr('href');
			    				$("#technicalDocumentDeviationForm #downloadFileResponse").html('');
			    				$("#technicalDocumentDeviationForm #a_docFileResponse").html('');
			    			}
			    			else{
			    				$("#technicalDocumentDeviationForm #a_docFileResponse").html('');
			    			}
			    			
					    			var url=$("#technicalDocumentDeviationForm #downloadFileResponse").data('url');
					    			$("#technicalDocumentDeviationForm #downloadFileResponse").attr('href',url);
					    		    $("#technicalDocumentDeviationForm #downloadFileResponse").prop('href', url+'/'+bidderFileId);
					    			$("#technicalDocumentDeviationForm #downloadFileResponse").html(bidderFileName);*/
					    			
					    			$("#technicalDocumentDeviationForm #bidderSectionDocId").html(biddersectionDocId);
			    			
					    			$("#technicalDocumentDeviationForm #finalScrutinyComment").val(finalScrutinyComment);
				    				$("#technicalDocumentDeviationForm #textResponse").val(bidderTextComment);
					    			$("#technicalDocumentDeviationForm #itemScrutinyLineId").val(itemScrutinyLineId);
					    			$("#technicalDocumentDeviationForm #itemScrutinyId").val(itemScrutinyId);
					    			$("#technicalDocumentDeviationForm #bidderId").val(bidderId);
					    			 
					    			$("#technicalDocumentDeviationForm #uploadedDoc").html(sectionDocName);
					    			$("#technicalDocumentDeviationForm #deviationComment").html(comment);
					    			
					    			$("#technicalDocumentDeviationForm #deviationType").html(deviationType);
					    			
					    			/*$("#technicalDocumentDeviationForm").removeClass('readonly');*/
					    			/*if(bidderTextComment!='')
				    		    	{
					    		    	 $("#technicalDocumentDeviationForm #textResponse").val(bidderTextComment);
					    		    	 $("#technicalDocumentDeviationForm #textResponse").addClass('readonly');
					    		    	 $("#technicalDocumentDeviationForm .actionbtn").hide();
				    		    	}
					    		    else{
					    		    	 $("#technicalDocumentDeviationForm .actionbtn").show();
					    		    	 $("#technicalDocumentDeviationForm #textResponse").removeClass('readonly');
					    		    	 $("#technicalDocumentDeviationForm #techFileResponseUploadId").show();
					    		    }*/
				    			 
				    			 if(deviationType=='FILE'){
						 					 $('#technicalDocumentDeviationForm #fileResponseDivId').removeAttr('style');
						 					 $('#technicalDocumentDeviationForm #textResponseDivId').attr('style','display: none;');
						 					 $('#technicalDocumentDeviationForm #textResponseDivId').attr('disabled','disabled');
						 					 $('#technicalDocumentDeviationForm .fileResponse').removeAttr('disabled');
						 					 $('#technicalDocumentDeviationForm .textResponse').attr('disabled','disabled');
						 			}
				    			 else{
						 					$('#technicalDocumentDeviationForm #textResponseDivId').removeAttr('style');
						 					$('#technicalDocumentDeviationForm #fileResponseDivId').attr('style','display: none;');
						 					$('#technicalDocumentDeviationForm .fileResponse').attr('disabled','disabled');
						 					$('#technicalDocumentDeviationForm .textResponse').removeAttr('disabled');
						 		}
				    			 $("#technicalDocumentDeviationForm").removeClass('readonly');
				    			 $("#technicalDocumentDeviationForm .save").show();
				    			 if(bidderFileId!='' || bidderTextComment!=''){
						    			/*$("#technicalDocumentDeviationForm .save").hide();*/
						    			
									}
				    			/* setChildLoadFlag(true);*/
			    			}
			    		else if(bidsection=='CS'){
			    			
			    			$('.commDocTab').removeClass('readonly');
			    			$("#commercialDocumentDeviationForm #biddersectionDocId").val(biddersectionDocId);
			    			 var url=$("#a_commDocFileResponse").data('url');
				    			$("#commercialDocumentDeviationForm #commDocFileResponseId").val(bidderFileId);
				    			$("#commercialDocumentDeviationForm #a_commDocFileResponse").prop('href', url+'/'+bidderFileId);
				    			$("#commercialDocumentDeviationForm #a_commDocFileResponse").html(bidderFileName);
				    			if(bidderFileId==''){
				    				$("#commercialDocumentDeviationForm #a_commDocFileResponse").removeAttr('href');
				    				$("#commercialDocumentDeviationForm #a_commDocFileResponse").html('');
				    				$('#commercialDocumentDeviationForm #deleteCommDocAttachmentId').attr('disabled','disabled');
				    			}
				    			else{
				    				$('#commercialDocumentDeviationForm #deleteCommDocAttachmentId').removeAttr('disabled');
				    			}
			    			/*if(bidderFileId==''){
			    				$("#commercialDocumentDeviationForm #downloadFileResponse").removeAttr('href');
			    				$("#commercialDocumentDeviationForm #downloadFileResponse").html('');
			    				$("#commercialDocumentDeviationForm #a_commDocFileResponse").html('');
			    			}
			    			else{
			    				$("#commercialDocumentDeviationForm #a_commDocFileResponse").html('');
			    			}
			    			
				    			var url=$("#commercialDocumentDeviationForm #downloadFileResponse").data('url');
				    			$("#commercialDocumentDeviationForm #downloadFileResponse").attr('href',url);
				    		    $("#commercialDocumentDeviationForm #downloadFileResponse").prop('href', url+'/'+bidderFileId);
				    			$("#commercialDocumentDeviationForm #downloadFileResponse").html(bidderFileName);*/
				    			$("#commercialDocumentDeviationForm #bidderSectionDocId").html(biddersectionDocId);
				    			
			    				$("#commercialDocumentDeviationForm #textResponse").val(bidderTextComment);
				    			$("#commercialDocumentDeviationForm #itemScrutinyLineId").val(itemScrutinyLineId);
				    			 $("#commercialDocumentDeviationForm #itemScrutinyId").val(itemScrutinyId);
				    			 $("#commercialDocumentDeviationForm #bidderId").val(bidderId);
				    			 $("#commercialDocumentDeviationForm #deviationType").html(deviationType);
				    			 $("#commercialDocumentDeviationForm #uploadedDoc").html(sectionDocName);
				    			 $("#commercialDocumentDeviationForm #deviationComment").html(comment);
				    			 $("#commercialDocumentDeviationForm #finalScrutinyComment").val(finalScrutinyComment);
				    			 if(deviationType=='FILE')
						 				{
						 					 $('#commercialDocumentDeviationForm #fileResponseDivId').removeAttr('style');
						 					 $('#commercialDocumentDeviationForm #textResponseDivId').attr('style','display: none;');
						 					 $('#commercialDocumentDeviationForm #textResponseDivId').attr('disabled','disabled');
						 					 $('#commercialDocumentDeviationForm .fileResponse').removeAttr('disabled');
						 					 $('#commercialDocumentDeviationForm .textResponse').attr('disabled','disabled');
						 				}
				    			 else
						 				{
						 					$('#commercialDocumentDeviationForm #textResponseDivId').removeAttr('style');
						 					$('#commercialDocumentDeviationForm #fileResponseDivId').attr('style','display: none;');
						 					$('#commercialDocumentDeviationForm .fileResponse').attr('disabled','disabled');
						 					$('#commercialDocumentDeviationForm .textResponse').removeAttr('disabled');
						 				}
				    			 $("#commercialDocumentDeviationForm").removeClass('readonly');
				    			 $("#commercialDocumentDeviationForm .save").show();
				    			 if(bidderFileId!='' || bidderTextComment!=''){
						    			/*$("#commercialDocumentDeviationForm .save").hide();*/
						    			
									}
				    			 $("#confirmationCommercialDevitionForm #itemScrutinyId").val(itemScrutinyId);
				    			 $("#confirmationCommercialDevitionForm #bidderId").val(bidderId);
			    			}
			    		
		    			 }
		}
	else{
		$("#technicalDocumentDeviationForm").addClass('readonly');
		$("#technicalDocumentDeviationForm .save").hide();
		$("#technicalDocumentDeviationForm #downloadFileResponse").removeAttr('href');
		$("#technicalDocumentDeviationForm #downloadFileResponse").html('');
		$("#technicalDocumentDeviationForm #textResponse").val('');
		$("#technicalDocumentDeviationForm #deviationType").html('');
		$("#commercialDocumentDeviationForm #deviationType").html('');
		$("#commercialDocumentDeviationForm").addClass('readonly');
		$("#commercialDocumentDeviationForm #downloadFileResponse").removeAttr('href');
		$("#commercialDocumentDeviationForm #downloadFileResponse").html('');
		$("#commercialDocumentDeviationForm #textResponse").val('');
		$("#commercialDocumentDeviationForm .save").hide()
		
	}
}
function showDocDetailsDetail(el){
	var bidderSecDocId=$(el).attr('id');
	loadDocDetailsRightPane(documentsArray["bidderSecDoc_"+bidderSecDocId])
}
function loadCSConfirmResp(ele){
	submitWithParam('getCommercialStatusBybidderId','bidderId' ,'loadConfirmationForScrutiny');
}
function loadTSConfirmResp(ele){
cacheLi();
setCurrentTab(ele);
submitWithParam('getTechnicalStatusByItemBidId','itemBidId' ,'loadConfirmationForScrutiny');
} 
function loadConfirmationForScrutiny(data){
	debugger;
	    $('#confirmationTechnicalDevitionForm #a_techdocFileResponse').html('');
	    $('#confirmationCommercialDevitionForm #a_comdocFileResponse').html('');
	    $("#confirmationTechnicalDevitionForm .save").show();
	    $("#confirmationCommercialDevitionForm .save").show();
	   /* $("#confirmationCommercialDevitionForm #confirmCheckBox").prop('checked',false);
	    $("#confirmationTechnicalDevitionForm #confirmCheckBox").prop('checked',false);*/
	    
	
	$(".leftPaneData").html("");
	var value=data.objectMap.ItemList;
	if($.isEmptyObject(value)){
			
		/*Alert.warn('All Deviation Has not Submitted');*/
		    $("#technicalConfirmStatus").html('No Technical Deviation Called To Submit and Confirm');
			$("#techConfirmMsgDivId").removeAttr('style');
			$("#commercialConfirmStatus").html('No Commercial Deviation Called To Submit and Confirm');
			$("#commConfirmMsgDivId").removeAttr('style');
			
			$('#confirmationTechnicalDevitionForm .save').hide();
			$('#confirmationCommercialDevitionForm .save').hide();
			
		    $('#confirmationTechnicalDevitionForm').attr('readonly','readonly');
		    $('#confirmationCommercialDevitionForm').attr('readonly','readonly');
		    $('#confirmationTechnicalDevitionForm .finalStatus').attr('disabled','disabled');
		    $('#confirmationCommercialDevitionForm .finalStatus').attr('disabled','disabled');
		}
	else{
		    $("#technicalConfirmStatus").html('');
			$("#techConfirmMsgDivId").attr('style','display: none ;');
			$("#commercialConfirmStatus").html('');
			$("#commConfirmMsgDivId").attr('style','display: none ;');
			
		 if(value[0].itemScrutiny.scrutinyType=='TECHSCR'){
		    	var url=$("#confirmationTechnicalDevitionForm #a_techdocFileResponse").data('url');
		    	var attachmentId=value[0].itemScrutiny.scrutinyFile==null?'':value[0].itemScrutiny.scrutinyFile.attachmentId;
 			var name=value[0].itemScrutiny.scrutinyFile==null?'':value[0].itemScrutiny.scrutinyFile.fileName;
 			if(attachmentId!=''){
 				    $("#confirmationTechnicalDevitionForm #a_techdocFileResponse").prop('href', url+'/'+attachmentId);
 	    			$("#confirmationTechnicalDevitionForm #a_techdocFileResponse").html(name);
 	    			$("#confirmationTechnicalDevitionForm #techdocFileResponseId").val(attachmentId);
 	    			/*$("#confirmationTechnicalDevitionForm .save").hide();*/
 	    			$("#confirmationTechnicalDevitionForm #confirmCheckBox").prop('checked',true);
 	    			/*Alert.info('Already Submitted');*/
 	    			$("#technicalConfirmStatus").html('Techical Confirmation Already Done');
 	    			$("#techConfirmMsgDivId").removeAttr('style');
 	    			$('#commercialDocumentDeviationForm #deleteTechDocAttachmentId').removeAttr('disabled');
               
 			}else{
 				$("#confirmationTechnicalDevitionForm #techdocFileResponseId").val('');
 				 $("#confirmationTechnicalDevitionForm #a_techdocFileResponse").removeAttr('href');
 				$("#confirmationTechnicalDevitionForm #a_techdocFileResponse").html('');
 				$("#confirmationTechnicalDevitionForm #a_techdocFileResponse").html('');
 				$('#confirmationTechnicalDevitionForm #deleteTechDocAttachmentId').attr('disabled','disabled');
 				/*$("#technicalConfirmStatus").html('');
	    		$("#techConfirmMsgDivId").attr('style','display: none ;');*/
 			}
 			/*setChildLoadFlag(true);   */
		    }else{
		    	var url=$("#confirmationCommercialDevitionForm #downloadTechFileResponse").data('url');
 			var attachmentId=value[0].itemScrutiny.scrutinyFile==null?'':value[0].itemScrutiny.scrutinyFile.attachmentId;
 			var name=value[0].itemScrutiny.scrutinyFile==null?'':value[0].itemScrutiny.scrutinyFile.fileName;
 			if(attachmentId!=''){
 				    $("#confirmationCommercialDevitionForm #a_comdocFileResponse").prop('href', url+'/'+attachmentId);
 	    			$("#confirmationCommercialDevitionForm #a_comdocFileResponse").html(name);
 	    			$("#confirmationCommercialDevitionForm #comdocFileResponseId").val(attachmentId);
 	    			/*$("#confirmationCommercialDevitionForm .save").hide();*/
 	    			$("#confirmationCommercialDevitionForm #confirmCheckBox").prop('checked',true);
 	    			/*Alert.info('Already Submitted');*/
 	    			$("#commercialConfirmStatus").html('Commercial Confirmation Already Done');
 	    			$("#commConfirmMsgDivId").removeAttr('style');
 			}else{
 				$("#confirmationCommercialDevitionForm #comdocFileResponseId").val('');
				 $("#confirmationCommercialDevitionForm #a_comdocFileResponse").removeAttr('href');
				$("#confirmationCommercialDevitionForm #a_comdocFileResponse").html('');
				$('#confirmationCommercialDevitionForm #deleteComDocAttachmentId').attr('disabled','disabled');
 				/*$("#commercialConfirmStatus").html('');
	    		$("#commConfirmMsgDivId").attr('style','display: none ;');*/
 			}
			    
		    }
	}
	/*if(data.objectMap.role!='VENADM')
		{
		  $('#confirmationTechnicalDevitionForm .save').attr('onclick','return submitIt("confirmationTechnicalDevitionForm","confirmFinalTechnicalScrutiny","confirmDeviationSubmitResp");');
		  $('#confirmationCommercialDevitionForm .save').attr('onclick','return submitIt("confirmationTechnicalDevitionForm","confirmFinalCommercialScrutiny","confirmDeviationSubmitResp");');
		  $('#confirmationTechnicalDevitionForm .finalStatus').removeAttr('disabled');
		  $('#confirmationCommercialDevitionForm .finalStatus').attr('disabled','disabled');
		  $('#confirmationTechnicalDevitionForm #finalScrutinyDivId').removeAttr('style');
		  $('#confirmationCommercialDevitionForm #finalScrutinyDivId').removeAttr('style')
		  $('#confirmationTechnicalDevitionForm #confirmCheckBox').attr('style','display: none;');
		  $('#confirmationCommercialDevitionForm #confirmCheckBox').attr('style','display: none;');
		  
		}*/
	}
function confirmDeviationSubmitResp(data)
{
	if(data.objectMap.statusResult){
			Alert.info(data.objectMap.Status);
		}
	else{
		    Alert.warn(data.objectMap.Status);
		}
	
	}
function scrutinyDeviationSubmitResp(data)
{
	debugger;
	if(data.objectMap.resultStatus==true){
		    var itemScrutinyLineId=$('#commercialDeviationForm #itemScrutinyLineId').val();
			scrutinyPointArray["itemScrutinyLine_"+itemScrutinyLineId]=data.objectMap.itemScrutinyLine;
			/*$("#commercialDeviationForm .save").hide();*/
			Alert.info(data.objectMap.Status);
	}
	else{
		$("#a_commFileResponse").html('');
		$("#a_commFileResponse").attr('href','#');
			Alert.warn(data.objectMap.Status);
	}
	
}
function bidderGtpDeviationSubmitResp(data)
{
	debugger;
	if(data.objectMap.resultStatus==true){
		 var bidderGtpId=$('#technicalDeviationForm #bidderGtpId').val();
		 bidderGtpArray["bidderGtp_"+bidderGtpId]=data.objectMap.itemScrutinyLine;
			/*$("#technicalDeviationForm .save").hide();*/
			Alert.info(data.objectMap.Status);
	}
	else{
		$("#a_techFileResponse").html('');
		$("#a_techFileResponse").attr('href','#');
			Alert.warn(data.objectMap.Status);
	}
	
}
function techDocDeviationSubmitResp(data)
{
	debugger;
	if(data.objectMap.resultStatus==true){
		 var biddersectionDocId=$('#technicalDocumentDeviationForm #bidderSectionDocId').val();
		 documentsArray["bidderSecDoc_"+biddersectionDocId]=data.objectMap.itemScrutinyLine;
			/*$("#technicalDeviationForm .save").hide();*/
			Alert.info(data.objectMap.Status);
	}
	else{
		$("#a_docFileResponse").html('');
		$("#a_docFileResponse").attr('href','#');
			Alert.warn(data.objectMap.Status);
	}
	
}
function commDocDeviationSubmitResp(data)
{
	debugger;
	if(data.objectMap.resultStatus==true){
		 var biddersectionDocId=$('#commercialDocumentDeviationForm #bidderSectionDocId').val();
		 documentsArray["bidderSecDoc_"+biddersectionDocId]=data.objectMap.itemScrutinyLine;
			/*$("#technicalDeviationForm .save").hide();*/
			Alert.info(data.objectMap.Status);
	}
	else{
		$("#a_commDocFileResponse").html('');
		$("#a_commDocFileResponse").attr('href','#');
			Alert.warn(data.objectMap.Status);
	}
}

function gtpDeviationfileDeleteResp(data) {
	debugger;
	if (!data.hasError) {
		$('#techFileResponseUploadId').val('');
		$('#techFileResponseId').val('');
		$("#a_techFileResponse").html('');
		$('.techFileResponseId').attr('disabled', true);
		Alert.info(data.message);
		var bidderGtpId=$("#technicalDeviationForm #bidderGtpId").val();
		if(bidderGtpId!=""){
			bidderGtpArray["bidderGtp_"+bidderGtpId].fileResponse=null;
		}
	} else {
		Alert.warn(data.message);
	}

}
function techDocDeviationfileDeleteResp(data) {
	debugger;
	if (!data.hasError) {
		$('#docFileResponseUploadId').val('');
		$('#docFileResponseId').val('');
		$("#a_docFileResponse").html('');
		$('.docFileResponseId').attr('disabled', true);
		Alert.info(data.message);
		var biddersectionDocId=$("#technicalDocumentDeviationForm #biddersectionDocId").val();
		if(biddersectionDocId!=""){
			documentsArray["bidderSecDoc_"+biddersectionDocId].fileResponse=null;
		}
	} else {
		Alert.warn(data.message);
	}

}
function commDocDeviationfileDeleteResp(data) {
	debugger;
	if (!data.hasError) {
		$('#commDocFileResponseUploadId').val('');
		$('#commDocFileResponseId').val('');
		$("#a_commDocFileResponse").html('');
		$('.commDocFileResponseId').attr('disabled', true);
		Alert.info(data.message);
		var biddersectionDocId=$("#commercialDocumentDeviationForm #biddersectionDocId").val();
		if(biddersectionDocId!=""){
			documentsArray["bidderSecDoc_"+biddersectionDocId].fileResponse=null;
		}
	} else {
		Alert.warn(data.message);
	}

}
function scrutinyPointDeviationfileDeleteResp(data) {
	debugger;
	if (!data.hasError) {
		$('#commFileResponseUploadId').val('');
		$('#commFileResponseId').val('');
		$("#a_commFileResponse").html('');
		$('.commfileResponseId').attr('disabled', true);
		Alert.info(data.message);
		var itemScrutinyLineId= $("#commercialDeviationForm #itemScrutinyLineId").val();
		if(itemScrutinyLineId!=""){
			scrutinyPointArray["itemScrutinyLine_"+itemScrutinyLineId].fileResponse=null;
		}
	} else {
		Alert.warn(data.message);
	}

}
function techConfirmDeviationfileDeleteResp(data) {
	debugger;
	if (!data.hasError) {
		$('#techdocFileResponseUploadId').val('');
		$('#techdocFileResponseId').val('');
		$("#a_techdocFileResponse").html('');
		$('.techdocFileResponseId').attr('disabled', true);
		Alert.info(data.message);
		/*var bidderGtpId=$("#technicalDeviationForm #bidderGtpId").val();
		if(bidderGtpId!=""){
			bidderGtpArray["bidderGtp_"+bidderGtpId].bidderGtp.bidderGtpId=null;
		}*/
	} else {
		Alert.warn(data.message);
	}

}
function commConfirmDeviationfileDeleteResp(data) {
	debugger;
	if (!data.hasError) {
		$('#comdocFileResponseUploadId').val('');
		$('#comdocFileResponseId').val('');
		$("#a_comdocFileResponse").html('');
		$('.comdocFileResponseId').attr('disabled', true);
		Alert.info(data.message);
		/*var bidderGtpId=$("#technicalDeviationForm #bidderGtpId").val();
		if(bidderGtpId!=""){
			bidderGtpArray["bidderGtp_"+bidderGtpId].bidderGtp.bidderGtpId=null;
		}*/
	} else {
		Alert.warn(data.message);
	}

}