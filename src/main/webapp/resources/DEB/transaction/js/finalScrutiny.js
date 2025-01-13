var tenderDetailArray=new Array();
var itemBidArray=new Array();
var bidderArray=new Array();
var bidderGtpArray=new Array();
var scrutinyPointArray=new Array();
var documentsArray=new Array();
var bidderStatusList=new Array();
var statusList=new Array();
var role='';

var tenderCode='';
var tenderContact='';
var tenderDescription='';
var tenderDepartment='';

var bidderName='';
var bidderPanno='';
var bidderGstNo='';
var bidderCrn='';

var matName='';
var matHsnCode='';

var tenderTypeCode='';

var leftPanePageSize = 7;
var maxVisiblePageNumbers = 3;
var selectedPage = 1;
var pageSize=10;
var searchMode='none';
var searchValue='none';
var LastPage='';
var documentType='';
$(document).ready(function(){
	documentType=$(".documentType").val();
	$("#tabstrip").kendoTabStrip();
  /*  $('#leftPaneData').paginathing({perPage: 6});*/
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
	
	$("#tenderDetailTab").on('click',function(event){
		event.preventDefault();
		cacheLi();
		setCurrentTab(this);
		$('.pagination').html('');
		if(getChangedFlag()){
			var typeCode=$('input[name=tenderTypeCodeToggle]:checked').val();
			var data=fetchTenderList(1, pageSize, searchMode, searchValue,typeCode);
			loadTenderList(data);
			LastPage=data.objectMap.LastPage;
			setPagination(data.objectMap.LastPage, selectedPage , maxVisiblePageNumbers);
			/*submitToURL("getTenderListForFinalScrutiny/"+typeCode, "loadTenderListForScrutiny");*/
			$('.technicalComfirmation').addClass('readonly');
			$('.commercialConfirmation').addClass('readonly');
			setChangedFlag(false);
		}else{
			getCacheLi();
			setPagination(LastPage, selectedPage , maxVisiblePageNumbers);
		}
		var documentType=$(".documentType").val();
		setHeaderValues(documentType+" Code:" +tenderCode, documentType+" Contact : "+tenderContact, "Description: "+tenderDescription, "Department : "+tenderDepartment);
	
	});
	
	$("input[name='tenderTypeCodeToggle']").on("change",function(){
		/*$('.pagination').children().remove();*/
		selectedPage=1;
		$('.pagination').html('');
		var typeCode=$(this).val();
		var data=fetchTenderList(1, pageSize, searchMode, searchValue,typeCode);
		loadTenderList(data);
		LastPage=data.objectMap.LastPage;
		setPagination(data.objectMap.LastPage, selectedPage , maxVisiblePageNumbers);
		/*submitToURL("getTenderListForFinalScrutiny/"+$(this).val(), "loadTenderListForScrutiny");*/
		if($('.leftPaneData li').length==0){
			$('.bidderTabs').addClass('readonly');
			$('.deviationTabs').addClass('readonly');
		}
	    $(".tabs-left li a label").text(function(index, currentText) {
		    return currentText.substr(0, 20);
		});
	    setCurrentTab($("#tenderDetailTab")[0]);
	    setChangedFlag(true);
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
	/*submitToURL("getTenderListForFinalScrutiny/"+typeCode, "loadTenderListForScrutiny");*/
	$('.bidderTabs').addClass('readonly');
	$('.deviationTabs').addClass('readonly');
	
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
function loadTenderListPagination(data){
	if(!$.isEmptyObject(data.role)){
		role=data.role;
		if(role=='AUDIT'){
			$('#auditorForm').removeAttr('style');
			$('#auditorDocForm').removeAttr('style');
			$('#auditorConfirmForm').removeAttr('style');
			$('.auditorClass').hide();
		}else{
			$('#auditorForm').attr('style','display : none ;');
			$('#auditorDocForm').attr('style','display : none ;');
			$('#auditorConfirmForm').attr('style','display : none ;');
			$('.auditNotHidClass').hide();
		}
	}
	loadTenderListForScrutiny(data);
	
}

function loadTenderList(data){
	data=data.data;
	if(!$.isEmptyObject(data.role)){
		role=data.role;
		if(role=='AUDIT'){
			$('#auditorForm').removeAttr('style');
			$('#auditorDocForm').removeAttr('style');
			$('#auditorConfirmForm').removeAttr('style');
			$('.auditorClass').hide();
		}else{
			$('#auditorForm').attr('style','display : none ;');
			$('#auditorDocForm').attr('style','display : none ;');
			$('#auditorConfirmForm').attr('style','display : none ;');
			$('.auditNotHidClass').hide();
		}
	}
	loadTenderListForScrutiny(data);
}

function loadMyTenderList(data){
	data=data.objectMap;
	if(!$.isEmptyObject(data.objectMap.role)){
		role=data.objectMap.role;
		if(role=='AUDIT'){
			$('#auditorForm').removeAttr('style');
			$('#auditorDocForm').removeAttr('style');
			$('#auditorConfirmForm').removeAttr('style');
			$('.auditorClass').hide();
		}else{
			$('#auditorForm').attr('style','display : none ;');
			$('#auditorDocForm').attr('style','display : none ;');
			$('#auditorConfirmForm').attr('style','display : none ;');
			$('.auditNotHidClass').hide();
		}
	}
	loadTenderListForScrutiny(data);
}

function loadTenderListForScrutiny(data){
	$("#tahdrDetailForm #tenderNo").html("");
	$("#tahdrDetailForm #description").html("");
	$("#tahdrDetailForm #department").html("");
	$("#tahdrDetailForm #tenderType").html("");
	var documentType=$(".documentType").val();
	setHeaderValues(documentType+" No.: ", documentType+" Title : ", "Contact EmailId : ", "Description : ");
			$(".leftPaneData").html("");
			var leftPanelHtml="";
			var i=0;
			var firstRow=null;
			statusList=data.statusList;
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
			/*$('#leftPaneData').paginathing({perPage: 6});*/
		 $(".tabs-left li a label").text(function(index, currentText) {
			    return currentText.substr(0, 20);
			});	
}

function loadTahdrDetailRightPane(data){
		if(!$.isEmptyObject(data)){
			var isAuditing=data.tahdr.isAuditing==null?'': data.tahdr.isAuditing;
			    var tahdrDetailId = data.tahdrDetailId==null?'': data.tahdrDetailId;
			    var contactEmailId = data.contactEmailId==null?'': data.contactEmailId;
				var tenderNo = data.tahdr.title==null?'': data.tahdr.title;
				var tahdrCode = data.tahdr.tahdrCode==null?'': data.tahdr.tahdrCode;
				var tahdrId = data.tahdr.tahdrId==null?'': data.tahdr.tahdrId;
				var department = data.tahdr.department==null?'':data.tahdr.department.name;
				var description  = data.description==null?'':data.description;
				var submissionDate = data.submissionDate==null?'':data.submissionDate;
				var tenderType  = data.tahdr.tahdrTypeCode=='PT'?'Procurement':'Works';
				var tahdrTypeCode = data.tahdr.tahdrTypeCode==null?'': data.tahdr.tahdrTypeCode;
				tenderTypeCode=tahdrTypeCode;
				tenderCode=tahdrCode;
				tenderContact=contactEmailId;
				tenderDescription=description;
				tenderDepartment=department;
				
				if(tahdrTypeCode=='WT'){
					$('.pTSGtpFormTabId').hide();
				}else{
					$(".pTSGtpFormTabId").map(function() {
						var status=$(this).is('hidden');
					    if(!status){
					    	var id=$(this).attr('id');
					    	$('#'+id).show();
					   }
					});
					
					/*$('.pTSGtpFormTabId').show();*/
				}
				
				$("#tahdrDetailForm #submissionDate").html(submissionDate==''?'':formateDate(submissionDate));
				$("#tahdrDetailForm #tenderNo").html(tenderNo);
				$("#tahdrDetailForm #description").html(description);
				$("#tahdrDetailForm #department").html(department);
				$("#tahdrDetailForm #tenderType").html(tenderType);
				$("#tahdrDetailForm #tahdrDetailId").val(tahdrDetailId);
				$("#confirmAuditingForm #tahdrId").val(tahdrId);
				$("#confirmAuditingForm #tahdrName").val(tahdrCode);
				
				$('.bidderTabs').removeClass('readonly');
				
				$('#confirmationFinalScrutinyForm #tahdrId').val(tahdrId);
				$('#confirmationFinalScrutinyForm #tahdrName').val(tahdrCode);
				setHeaderValues(documentType+" No.: " +tahdrCode, documentType+" Title : "+tenderNo, "Contact EmailId : "+contactEmailId, "Description : "+description);
				
				if(isAuditing=='Y'){
					if(role!='AUDIT'){
						$('.finalScrutinyPage').addClass('readonly');
						$('#auditingStatus').html(documentType+' IS UNDER FINAL AUDITING');
						Alert.warn(documentType+' IS UNDER FINAL AUDITING');
					}/*else{
						$('#preliminaryScrutinyPageId').addClass('readonly');
						$('#auditingStatus').html(documentType+' IS UNDER AUDITING');
						Alert.warn(documentType+' IS UNDER AUDITING');
					}*/
				}else{
					$('.finalScrutinyPage').removeClass('readonly');
					$('#auditingStatus').html('');
				}
		}else{
			if(tahdrTypeCode=='WT'){
				$('.pTSGtpFormTabId').hide();
			}else{
				$(".pTSGtpFormTabId").map(function() {
					var status=$(this).is('hidden');
				    if(!status){
				    	var id=$(this).attr('id');
				    	$('#'+id).show();
				   }
				});
			}
			
			$('#confirmationFinalScrutinyForm #tahdrId').val('');
			$('#confirmationFinalScrutinyForm #tahdrName').val('');
			
			$("#tahdrDetailForm #submissionDate").html('');
			$("#tahdrDetailForm #tenderNo").html('');
			$("#tahdrDetailForm #description").html('');
			$("#tahdrDetailForm #department").html('');
			$("#tahdrDetailForm #tenderType").html('');
			$("#tahdrDetailForm #tahdrDetailId").val('');
			
			$("#confirmAuditingForm #tahdrId").val('');
			$("#confirmAuditingForm #tahdrName").val('');
		}
		
		setChildLoadFlag(true);	
}

function showTahdrDetail(id){
	loadTahdrDetailRightPane(tenderDetailArray['tender_'+id]);
}

function loadBidders(ele){
	loadBidder(ele);
}

function loadBidder(ele){
	cacheLi();
	setCurrentTab(ele);
	if(getChangedFlag()){
		submitWithParam('getDeviationBidderListByTahdrDetailId','tahdrDetailId' ,'loadBidderListForScrutiny');
		setChangedFlag(false);
	}else{
		getBidderScrutinyStatus();
		getCacheLi();
	}
	setHeaderValues(documentType+" No.: " +tenderCode,"Partner Name: "+bidderName, "GSTIN Number : "+bidderGstNo, "CRN Number : "+bidderCrn);
}

function loadBidderListForScrutiny(data){
	$('.pagination').children().remove();
	
	$("#bidderForm #name").html("");
	$("#bidderForm #panno").html("");
	$("#bidderForm #gstNo").html("");
	$("#bidderForm #crnNumber").html("");
	setHeaderValues(documentType+" No.: ","Partner Name: ", "GSTIN Number : ", "CRN Number : ");
	
	bidderStatusList=data.objectMap.bidderStatusList
	
			$(".leftPaneData").html("");
			var leftPanelHtml="";
			var i=0;
			var firstRow=null;
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
						+'	<label class="col-xs-6" id="'+bidderId+'_crnNumber">'+bidderStatus+'</label>'
						+' </div>'
					    +' </a>'
					    +' <div class="col-md-12" style="display: none">'
					    +' </li>';
						i++;
					}
			});
			$(".leftPaneData").html(leftPanelHtml);
			loadBidderDetailRightPane(firstRow);
			$('#leftPaneData').paginathing({perPage: 6});
			
			$('.ConfirmTabs').addClass('readonly');
}

function loadBidderDetailRightPane(data){
		if(!$.isEmptyObject(data)){
			    var bidderId = data.bidderId==null?'': data.bidderId;
			    var bpartnerId = data.partner.bPartnerId==null?'': data.partner.bPartnerId;
			    var name = data.partner.name==null?'': data.partner.name;
				var panno = data.partner.panNumber==null?'': data.partner.panNumber;
				var gstNo = data.partner.gstinNo==null?'':data.partner.gstinNo;
				var crnNumber  = data.partner.crnNumber==null?'':data.partner.crnNumber;
				var status=data.status==null?'': data.status;
				var bidderStatus=bidderStatusList[status];
				if(bidderStatus==undefined){
					bidderStatus='';
				}
				bidderName=name;
				bidderPanno=panno;
				bidderGstNo=gstNo;
				bidderCrn=crnNumber;
				$('#gennerateFinalTechnicalScrutinyDoc #bidderId').val(bidderId);
				$('#gennerateFinalCommercialScrutinyDoc #bidderId').val(bidderId);
				$("#bidderForm #bidderId").val(bidderId);
				$("#bidderForm #bpartnerId").val(bpartnerId);
				$("#bidderForm #name").html(name);
				$("#bidderForm #panno").html(panno);
				$("#bidderForm #gstNo").html(gstNo);
				$("#bidderForm #crnNumber").html(crnNumber);
				$("#bidderForm #status").html(bidderStatus);
				$("#confirmationCommercialDevitionForm #bidderId").val(bidderId);
				$( "#auditorConfirmForm #bidderId").val(bidderId);

				$("#itemDetailForm .bidderId").val(bidderId);
				$("#confirmationTechnicalDevitionForm #bidderId").val(bidderId);
				$('.deviationTabs').addClass('readonly');
				$('#itemTabId').removeClass('readonly');
				/*$('.technicalConfirmation').addClass('readonly');
				$('.commercialConfirmation').addClass('readonly');*/
				$('.commercialTabs').removeClass('readonly');
				setHeaderValues(documentType+" No.: " +tenderCode,"Partner Name: "+bidderName, "GSTIN Number : "+bidderGstNo, "CRN Number : "+bidderCrn);	
				getBidderScrutinyStatus();
		}
		else{
			bidderName='';
			bidderPanno='';
			bidderGstNo='';
			bidderCrn='';
			$('#itemTabId').addClass('readonly');
			$('.commercialTabs').addClass('readonly');
			
			$("#bidderForm #bidderId").val('');
			$("#bidderForm #bpartnerId").val('');
			$("#bidderForm #name").html('');
			$("#bidderForm #panno").html('');
			$("#bidderForm #gstNo").html('');
			$("#bidderForm #crnNumber").html('');
			$("#bidderForm #status").html('');
			$("#itemDetailForm .bidderId").val();
			$("#confirmationTechnicalDevitionForm #bidderId").val();
		}
		if(role=='AUDIT'){
			$('#commercialDeviationForm .save').hide();
			$('#commercialDeviationForm #finalScrutinyDivId').addClass('readonly');
			$('#commercialDocumentDeviationForm .save').hide();
			$('#commercialDocumentDeviationForm #finalScrutinyDivId').addClass('readonly');
			$('#confirmationCommercialDevitionForm .save').hide();
			$('#confirmationCommercialDevitionForm #finalScrutinyDivId').addClass('readonly');
		}
		setChildLoadFlag(true);	
}

function showBidderDetail(bidderId){
	loadBidderDetailRightPane(bidderArray['bidder_'+bidderId])
}

function loadItems(ele){
	cacheLi();
	setCurrentTab(ele);
	if(getChangedFlag()){
		submitWithParam('getItemListForFinalBybidderId','bidderId' ,'loadItemListForScrutiny');
		setChangedFlag(false);
	}else{
		getCacheLi();
	}
}

function loadItemListForScrutiny(data){
	$('.pagination').children().remove();
	$(".leftPaneData").html("");
	var leftPanelHtml="";
	var i=0;
	var firstRow=null;
	$.each(data.objectMap.ItemList,function(key,value){
		if(!$.isEmptyObject(value.tahdrMaterial)){
					var itemBidId = value.itemBidId==null?'': value.itemBidId;
				    var name = value.tahdrMaterial.material==null?'': value.tahdrMaterial.material.name==null?'': value.tahdrMaterial.material.name;
				    var uomName = value.tahdrMaterial.material==null?'': value.tahdrMaterial.material.uom==null?'': value.tahdrMaterial.material.uom.name==null?'': value.tahdrMaterial.material.uom.name;
					var description = value.tahdrMaterial.material==null?'': value.tahdrMaterial.material.description==null?'':  value.tahdrMaterial.material.description;
					var hsnCode =  value.tahdrMaterial.material==null?'': value.tahdrMaterial.material.hsnCode==null?'':value.tahdrMaterial.material.hsnCode.code==null?'': value.tahdrMaterial.material.hsnCode.code;
					
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
	$('#leftPaneData').paginathing({perPage: 6});
	
	}
function loadItemBidRightPane(data){
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
				matName=name;
				matHsnCode=hsnCode;
				$('#gennerateFinalTechnicalScrutinyDoc #itemBidId').val(itemBidId);
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
				
				$("#confirmationTechnicalDevitionForm #itemBidId").val(itemBidId);
				$('.deviationTabs').removeClass('readonly');
				
				var deviationFile=fetchList('getTechnicalScrutinyDeviation/'+itemBidId,null);
				if(deviationFile!=null){
					var attachmentId=deviationFile.scrutinyFile==null?'':deviationFile.scrutinyFile.attachmentId;
					var name=deviationFile.scrutinyFile==null?'':deviationFile.scrutinyFile.fileName;
					var url=$("#itemDetailForm #downloadTechScrutinyFile").data('url');
					if(attachmentId!=''){
						    $("#itemDetailForm #downloadTechScrutinyFile").prop('href', url+'/'+attachmentId);
			    			$("#itemDetailForm #downloadTechScrutinyFile").html(name);
			    			$("#itemDetailForm #downloadTechScrutinyFile").val(attachmentId);
					}
				}else{
					$("#itemDetailForm #downloadTechScrutinyFile").removeAttr('href');
					$("#itemDetailForm #downloadTechScrutinyFile").html('');
					$("#itemDetailForm #downloadTechScrutinyFile").val('');
				}
				
		}else{
			matName='';
			matHsnCode='';
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
			
			$("#confirmationTechnicalDevitionForm #itemBidId").val('');
			$('.deviationTabs').addClass('readonly');
		}
		setHeaderValues(documentType+" No.: " +tenderCode,"Partner Name: "+bidderName, "Material : "+matName, "Hsn Code : "+matHsnCode);
		
		setChildLoadFlag(true);
}

function showItemDetail(el){
	var itemBidId=$(el).attr('id');
	loadItemBidRightPane(itemBidArray['itemBid_'+itemBidId]);
}

function loadBidderGtp(ele){
	cacheLi();
	setCurrentTab(ele);
	submitWithParam('getBidderGtpByItemBidId','itemBidId' ,'loadBidderGtpListForScrutiny');
	setHeaderValues(documentType+" No.: " +tenderCode,"Partner Name: "+bidderName, "Material : "+matName, "Hsn Code : "+matHsnCode);
}

function loadBidderGtpListForScrutiny(data){
	$('.pagination').children().remove();
			$(".leftPaneData").html("");
			var leftPanelHtml="";
			var i=0;
			var firstRow=null;
			if(!$.isEmptyObject(data.objectMap.bidderGtpList)){
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
				
			}else{
				$("#technicalDeviationForm .save").hide();
				$("#technicalDeviationForm").addClass('readonly');
				 $("#technicalDeviationForm #downloadFileResponse").removeAttr('href');
	    		 $("#technicalDeviationForm #downloadFileResponse").html('');
	    		 $('#technicalDeviationForm #finalScrutinyComment').val('');
	    		 $("#technicalDeviationForm #itemScrutinyLineId").val('');
	    		 $("#technicalDeviationForm #bidderId").val('');
	    		$("#confirmationTechnicalDevitionForm #itemScrutinyId").val('');
	    		
				$("#technicalDeviationForm #bidderGtpId").val('');
				$("#technicalDeviationForm #gtpType").html('');
				$("#technicalDeviationForm #gtp").html('');
	    		$("#technicalDeviationForm #deviationComment").html('');
	    		$("#confirmationTechnicalDevitionForm ").addClass('readonly');
			}
			
			
			
			if(data.objectMap.role=='VENADM')
				{
				 $('#technicalDeviationForm  .finalStatus').attr('disabled','disabled');
				 $('#technicalDeviationForm #finalScrutinyDivId').hide();
				 $('#technicalDeviationForm .save').attr('onclick','return submitIt("technicalDeviationForm","saveTechnicalDeviationResponse","bidderDeviationResponseSubmitResp");');
				}
			else
				{
				  $('#technicalDeviationForm .finalStatus').removeAttr('disabled');
				  $('#technicalDeviationForm #finalScrutinyDivId').show();
				  $('#technicalDeviationForm #finalScrutinyDivId').show();
				  $('#technicalDeviationForm #techFileResponseUploadId').hide();
				  $('#technicalDeviationForm #deleteTechnicalAttachmentId').hide();
				  /*$('#technicalDeviationForm .save').attr('onclick','return submitIt("technicalDeviationForm","saveFinalTechnicalDeviationResponse","bidderDeviationResponseSubmitResp");');*/
				  $('#technicalDeviationForm .save').attr('onclick','return submitIt("technicalDeviationForm","saveFinalTechnicalDeviationResponse","bidderGtpFinalSubmitResp");');
				/*  $('#technicalDeviationForm #textResponse').attr('readonly','readonly');*/
				  $('#technicalDeviationForm #textResponse').attr('disabled','disabled');
				}
}

function loadBidderGtpRightPane(data){
	$("#technicalDeviationForm")[0].reset();
	$("#technicalDeviationForm").addClass('readonly');
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
			    			var fileResponse=data.fileResponse==null?'':(data.fileResponse.attachmentId==null?'':data.fileResponse.attachmentId);
			    		    var fileResponseName=data.fileResponse==null?'':data.fileResponse.fileName==null?'':data.fileResponse.fileName;
			    		    
			    		    var finalScrutinyComment=data.finalScrutinyComment==null?'':data.finalScrutinyComment;
			    		    var finalScrutinyStatus=data.finalScrutinyStatus==null?'':data.finalScrutinyStatus;
			    		    if(finalScrutinyStatus=='Approved'){
			    		    	$('#technicalDeviationForm #approvedId').prop('checked',true);
			    		    }else if(finalScrutinyStatus=='Rejected'){
			    		    	$('#technicalDeviationForm #rejectId').prop('checked',true);
			    		    }
			    			$('#gennerateFinalTechnicalScrutinyDoc #itemScrutinyId').val(itemScrutinyId);
			    		    $('#technicalDeviationForm #finalScrutinyComment').val(finalScrutinyComment);
			    		    $("#technicalDeviationForm #deviationType").html(deviationType);
			    		   if(fileResponse!=null){
			    			   var url=$("#a_techFileResponse").data('url');
			    			   $("#downloadFileResponse").attr('href',url);
				    		   $("#technicalDeviationForm #downloadFileResponse").prop('href', url+'/'+fileResponse);
				    		   $("#technicalDeviationForm #downloadFileResponse").html(fileResponseName);
				    		   $("#technicalDeviationForm #techFileResponseUploadId").hide();
			    			   
			    		   }else{
			    			   $("#technicalDeviationForm #techFileResponseUploadId").show();
			    		   }
			    			
			    		    if(bidderTextComment!='')
			    		    	{
				    		    	 $("#technicalDeviationForm #textResponse").val(bidderTextComment);
				    		    	 $("#technicalDeviationForm #textResponse").addClass('readonly');
			    		    	}
			    		    else{
			    		    	 $("#technicalDeviationForm .actionbtn").show();
			    		    	 $("#technicalDeviationForm #textResponse").removeClass('readonly');
			    		    }
			    		    	
							
			    			
			    			$("#technicalDeviationForm #itemScrutinyLineId").val(itemScrutinylineId);
			    			$("#technicalDeviationForm #bidderId").val(bidderId);
			    			$("#confirmationTechnicalDevitionForm #itemScrutinyId").val(itemScrutinyId);
			    			$("#confirmationTechnicalDevitionForm #bidderId").val(bidderId);
							$("#technicalDeviationForm #bidderGtpId").val(bidderGtpId);
							
							$("#technicalDeviationForm #gtpType").html(gtpType);
							$("#technicalDeviationForm #gtp").html(gtpName);
							
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
								
								$("#technicalDeviationForm .save").show();
					}else{
						$("#technicalDeviationForm .save").hide();
						$("#technicalDeviationForm").addClass('readonly');
						 $("#technicalDeviationForm #deviationType").html('');
					}
	
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

function loadScrutinyPoint(ele){
	cacheLi();
	setCurrentTab(ele);
	submitWithParam('getScrutinyPointByBidderId','bidderId' ,'loadScrutinyPointListForScrutiny');
	setHeaderValues(documentType+" No.: " +tenderCode,"Partner Name: "+bidderName, "GSTIN Number : "+bidderGstNo, "CRN Number : "+bidderCrn);
}

function loadScrutinyPointListForScrutiny(data){
	$('.pagination').children().remove();
	 if(data.objectMap.scrutinyPointList!=undefined){
			$(".leftPaneData").html("");
			var leftPanelHtml="";
			var i=0;
			var firstRow=null;
			$.each(data.objectMap.scrutinyPointList,function(key,value){
				 
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
				 $('#commercialDeviationForm .save').attr('onclick','return submitIt("commercialDeviationForm","saveCommercialDeviationResponse","bidderDeviationResponseSubmitResp");');
				}
			else{
				  $('#commercialDeviationForm .finalStatus').removeAttr('disabled');
				  $('#commercialDeviationForm #finalScrutinyDivId').show();
				  

				  $('#commercialDeviationForm #commFileResponseUploadId').hide();
				  $('#commercialDeviationForm #deletecommercialAttachmentId').hide();
				  
				  $('#commercialDeviationForm .save').attr('onclick','return submitIt("commercialDeviationForm","saveFinalCommercialDeviationResponse","scrutinyFinalSubmitResp");');
				  $('#commercialDeviationForm #textResponse').attr('disabled','disabled');
				}
		 }
}

function loadScrutinyPointRightPane(data){
	$("#commercialDeviationForm")[0].reset();
	if(!$.isEmptyObject(data)){
		 var itemScrutinyLineId = data.itemScrutinyLineId==null?'': data.itemScrutinyLineId;
		 var bidderId=data.itemScrutiny.bidder==null?'':data.itemScrutiny.bidder.bidderId;
		 var scrutinypointName=data.scrutinyPoint.name==null?'': data.scrutinyPoint.name;
		 var scrutinypointDesp=data.scrutinyPoint.description==null?'': data.scrutinyPoint.description;
		 var itemScrutinyId=data.itemScrutiny.itemScrutinyId==null?'': data.itemScrutiny.itemScrutinyId;
		 var scrutinyPointId=data.scrutinyPoint.scrutinyPointId==null?'': data.scrutinyPoint.scrutinyPointId;
		 
		 var auditorPrevStatus=data.finalAuditorStatus==null?'':data.finalAuditorStatus;
		 var auditorPrevComment=data.finalAuditorComment==null?'Yet Not Commented':data.finalAuditorComment;
		 $("#commercialDeviationForm #auditPrevStatus").val(auditorPrevStatus);
		 $("#commercialDeviationForm #auditPrevComment").val(auditorPrevComment);
		 $("#commercialDeviationForm #auditPrevStatus").html(auditorPrevStatus);
		 $("#commercialDeviationForm #auditPrevComment").html(auditorPrevComment);
		 
		 var deviationType=data.deviationType=='FILE'?'FILE': data.deviationType;
	     var comment=data.deviationComment==null?'': data.deviationComment;
	     $("#commercialDeviationForm #deviationType").html(deviationType);
	     var finalScrutinyComment=data.finalScrutinyComment==null?'':data.finalScrutinyComment;
	     $("#commercialDeviationForm #finalScrutinyComment").val(finalScrutinyComment);
	     
	     var finalScrutinyStatus=data.finalScrutinyStatus==null?'':data.finalScrutinyStatus;
	     if(finalScrutinyStatus=='Approved'){
	    	 $("#commercialDeviationForm #approvedId").prop('checked',true);
	     }else if(finalScrutinyStatus=='Rejected'){
	    	 $("#commercialDeviationForm #rejectedId").prop('checked',true);
	     }
	     var bidderTextComment=data.textResponse==null?'':data.textResponse;
			$("#commercialDeviationForm #textResponse").val(bidderTextComment);
			
			var bidderFileId=data.fileResponse==null?'':data.fileResponse.attachmentId;
			var bidderFileName=data.fileResponse==null?'':data.fileResponse.fileName;
	    			var url=$("#commercialDeviationForm #downloadFileResponse").data('url');
	    			$("#commercialDeviationForm #downloadFileResponse").attr('href',url);
	    		    $("#commercialDeviationForm #downloadFileResponse").prop('href', url+'/'+bidderFileId);
	    			$("#commercialDeviationForm #downloadFileResponse").html(bidderFileName);
		 
		 $("#commercialDeviationForm #itemScrutinyLineId").val(itemScrutinyLineId);
		 $("#auditorForm #itemScrutinyLineId").val(itemScrutinyLineId);
		 
		 $("#auditorForm #auditTypeCode").val(auditorPrevStatus);
		 $("#auditorForm #comment").val(auditorPrevComment);
		 $("#commercialDeviationForm #auditorPrevStatus").html(auditorPrevStatus);
		 $("#commercialDeviationForm #auditorPrevComment").html(auditorPrevComment);
		 
		 $("#confirmationCommercialDevitionForm #itemScrutinyId").val(itemScrutinyId);
		 $("#auditorConfirmForm #itemScrutinyId").val(itemScrutinyId);
		 $("#confirmationCommercialDevitionForm #bidderId").val(bidderId);
		 $( "#auditorConfirmForm #bidderId").val(bidderId);
		
		 $("#commercialDeviationForm #scrutinyPointName").html(scrutinypointName);
		 $("#commercialDeviationForm #scrutinyPointDesp").html(scrutinypointDesp);
		 $("#commercialDeviationForm #itemScrutinyId").val(itemScrutinyId);
		 $("#commercialDeviationForm #scrutinyPointId").val(scrutinyPointId);
		 
		 $("#commercialDeviationForm #deviationComment").html(comment);
		 

			if(deviationType=='FILE'){
					 $('#commercialDeviationForm #fileResponseDivId').removeAttr('style');
					 $('#commercialDeviationForm #textResponseDivId').attr('style','display: none;');
					 $('#commercialDeviationForm #textResponseDivId').attr('disabled','disabled');
					 $('#commercialDeviationForm .fileResponse').removeAttr('disabled');
					 $('#commercialDeviationForm .textResponse').attr('disabled','disabled');
				}
		   else{
					$('#commercialDeviationForm #textResponseDivId').removeAttr('style');
					$('#commercialDeviationForm #fileResponseDivId').attr('style','display: none;');
					$('#commercialDeviationForm .fileResponse').attr('disabled','disabled');
					$('#commercialDeviationForm .textResponse').removeAttr('disabled');
				}
			/*$('.commercialConfirmation').removeClass('readonly');*/
			if(auditorPrevStatus.toUpperCase()=="APPROVED"){
				 $("#commercialDeviationForm").addClass('readonly');
				 $('#commercialDeviationForm .save').hide();
			 }else{
				 $("#commercialDeviationForm").removeClass('readonly');
				 $('#commercialDeviationForm .save').show(); 
			 }
			$('#commercialConfirmTabId').removeClass('readonly');
			if(role=='AUDIT'){
				$('#auditorForm').removeClass('readonly');
				$('#auditorForm .save').show();
				$('#commercialDeviationForm .save').hide(); 
			}
			
			var deviationFile=fetchList('getCommercialScrutinyDeviation/'+bidderId,null);;
			if(deviationFile!=null){
				var attachmentId=deviationFile.scrutinyFile==null?'':deviationFile.scrutinyFile.attachmentId;
				var name=deviationFile.scrutinyFile==null?'':deviationFile.scrutinyFile.fileName;
				var url=$("#commercialDeviationForm #downloadCommScrutinyFile").data('url');
				if(attachmentId!=''){
					    $("#commercialDeviationForm #downloadCommScrutinyFile").prop('href', url+'/'+attachmentId);
		    			$("#commercialDeviationForm #downloadCommScrutinyFile").html(name);
		    			$("#commercialDeviationForm #downloadCommScrutinyFile").val(attachmentId);
				}
			}else{
				$("#commercialDeviationForm #downloadCommScrutinyFile").removeAttr('href');
				$("#commercialDeviationForm #downloadCommScrutinyFile").html('');
				$("#commercialDeviationForm #downloadCommScrutinyFile").val('');
			}
		}
	else{
		$("#commercialDeviationForm").addClass('readonly');
		$('#commercialDeviationForm .save').hide();
		
		$("#auditorForm").addClass('readonly');
		$('#auditorForm .save').hide();
		 $("#commercialDeviationForm #deviationType").html('');
		 $("#commercialDeviationForm #auditPrevStatus").val('');
		 $("#commercialDeviationForm #auditPrevComment").val('');
		 $("#commercialDeviationForm #auditPrevStatus").html('');
		 $("#commercialDeviationForm #auditPrevComment").html('');
		 
        $("#commercialDeviationForm #finalScrutinyComment").val('');
			$("#commercialDeviationForm #textResponse").val('');
	    			$("#commercialDeviationForm #downloadFileResponse").removeAttr('href');
	    			$("#commercialDeviationForm #downloadFileResponse").html('');
		 
		 $("#auditorForm #auditTypeCode").val('');
		 $("#auditorForm #comment").val('');
		 $("#commercialDeviationForm #auditorPrevStatus").html('');
		 $("#commercialDeviationForm #auditorPrevComment").html('');
		 
		 $("#commercialDeviationForm #scrutinyPointName").html('');
		 $("#commercialDeviationForm #scrutinyPointDesp").html('');
		 
		 $("#commercialDeviationForm #deviationComment").html('');
					 $('#commercialDeviationForm #textResponseDivId').attr('style','display: none;');
					 $('#commercialDeviationForm #textResponseDivId').attr('disabled','disabled');
					$('#commercialDeviationForm #fileResponseDivId').attr('style','display: none;');
					$('#commercialDeviationForm .fileResponse').attr('disabled','disabled');
				
		
	}
}
function showScrutinyPointDetail(el){
	var scrutinyPointId=$(el).attr('id');
	loadScrutinyPointRightPane(scrutinyPointArray["itemScrutinyLine_"+scrutinyPointId]);
}

function loadTechnicalDoc(ele){
	cacheLi();
	setCurrentTab(ele);
	submitWithParam('getTechnicalDocumentsByItemBidId','itemBidId' ,'loadTechnicalDocumentList');
}
function loadTechnicalDocumentList(data){
	setHeaderValues(documentType+" No.: " +tenderCode,"Partner Name: "+bidderName, "Material : "+matName, "Hsn Code : "+matHsnCode);
	if(!$.isEmptyObject(data.objectMap.documentList)){
		loadDocumentListForScrutiny(data.objectMap.documentList);
	}else{
		$(".leftPaneData").html("");
		$("#technicalDocumentDeviationForm #deviationType").html('');
		$("#technicalDocumentDeviationForm").addClass('readonly');
		$('#technicalDocumentDeviationForm .save').hide();
		$('#technicalDocumentDeviationForm #finalScrutinyComment').val('');
	    $("#technicalDocumentDeviationForm #downloadFileResponse").removeAttr('href');
		$("#technicalDocumentDeviationForm #downloadFileResponse").html('');
		$("#technicalDocumentDeviationForm #finalScrutinyComment").val('');
		$("#technicalDocumentDeviationForm #textResponse").val('');
		$("#technicalDocumentDeviationForm #itemScrutinyLineId").val('');
		$("#technicalDocumentDeviationForm #itemScrutinyId").val('');
		$("#technicalDocumentDeviationForm #uploadedDoc").html('');
		$("#technicalDocumentDeviationForm #deviationComment").html('');
		$('#technicalDocumentDeviationForm #textResponseDivId').attr('style','display: none;');
			$('#technicalDocumentDeviationForm #textResponseDivId').attr('disabled','disabled');
			$('#technicalDocumentDeviationForm #fileResponseDivId').attr('style','display: none;');
			$('#technicalDocumentDeviationForm .fileResponse').attr('disabled','disabled');
	}
	
	  $('#technicalDocumentDeviationForm .finalStatus').removeAttr('disabled');
	  $('#technicalDocumentDeviationForm #finalScrutinyDivId').show();
	  
	  $('#technicalDocumentDeviationForm #deleteTechDocAttachmentId').hide();
	  $('#technicalDocumentDeviationForm #docFileResponseUploadId').hide();
	  
	 /* $('#technicalDocumentDeviationForm .save').attr('onclick','return submitIt("technicalDocumentDeviationForm","saveTechnicalFinalDocumentDeviationResponse","bidderDeviationResponseSubmitResp");');*/
	  $('#technicalDocumentDeviationForm .save').attr('onclick','return submitIt("technicalDocumentDeviationForm","saveTechnicalFinalDocumentDeviationResponse","techDocFinalSubmitResp");');
	  $('#technicalDocumentDeviationForm #textResponse').attr('disabled','disabled');

}
function loadCommercialDoc(ele){
	cacheLi();
	setCurrentTab(ele);
	submitWithParam('getCommercialDocumentsBybidderId','bidderId' ,'loadCommercialDocumentList');
}
function loadCommercialDocumentList(data){
	setHeaderValues(documentType+" No.: " +tenderCode,"Partner Name: "+bidderName, "GSTIN Number : "+bidderGstNo, "CRN Number : "+bidderCrn);	
	if(!$.isEmptyObject(data.objectMap.documentList)){
		loadDocumentListForScrutiny(data.objectMap.documentList);
	}else{
		$(".leftPaneData").html("");
		$("#commercialDocumentDeviationForm").addClass('readonly');
		$('#commercialDocumentDeviationForm .save').hide();
		$("#commercialDocumentDeviationForm #deviationType").html('');
		$("#technicalDocumentDeviationForm").addClass('readonly');
		$('#technicalDocumentDeviationForm .save').hide();
		
		$("#auditorDocForm").addClass('readonly');
		$('#auditorDocForm .save').hide();
			 $("#auditorDocForm #auditTypeCode").val('');
		 $("#auditorDocForm #comment").val('');
		 $("#commercialDocumentDeviationForm #auditorDocPrevStatus").html('');
		 $("#commercialDocumentDeviationForm #auditorDocPrevComment").html('');
		 $('#commercialDocumentDeviationForm #finalScrutinyComment').val('');
		    $("#commercialDocumentDeviationForm #downloadFileResponse").removeAttr('href');
			$("#commercialDocumentDeviationForm #downloadFileResponse").html('');
			$("#commercialDocumentDeviationForm #finalScrutinyComment").val('');
			$("#commercialDocumentDeviationForm #textResponse").val('');
			$("#commercialDocumentDeviationForm #itemScrutinyLineId").val('');
			$("#commercialDocumentDeviationForm #itemScrutinyId").val('');
			$("#commercialDocumentDeviationForm #uploadedDoc").html('');
			$("#commercialDocumentDeviationForm #deviationComment").html('');
			$('#commercialDocumentDeviationForm #textResponseDivId').attr('style','display: none;');
 			$('#commercialDocumentDeviationForm #textResponseDivId').attr('disabled','disabled');
 			$('#commercialDocumentDeviationForm #fileResponseDivId').attr('style','display: none;');
 			$('#commercialDocumentDeviationForm .fileResponse').attr('disabled','disabled');
	}
	
	  $('#commercialDocumentDeviationForm .finalStatus').removeAttr('disabled');
	  $('#commercialDocumentDeviationForm #finalScrutinyDivId').show();
	  
	  $('#commercialDocumentDeviationForm #deleteCommDocAttachmentId').hide();
	  $('#commercialDocumentDeviationForm #commDocFileResponseUploadId').hide();
	  
	  /*$('#commercialDocumentDeviationForm .save').attr('onclick','return submitIt("commercialDocumentDeviationForm","saveCommercialFinalDocumentDeviationResponse","bidderDeviationResponseSubmitResp");');*/
	  $('#commercialDocumentDeviationForm .save').attr('onclick','return submitIt("commercialDocumentDeviationForm","saveCommercialFinalDocumentDeviationResponse","commDocFinalSubmitResp");');
	  $('#commercialDocumentDeviationForm #textResponse').attr('disabled','disabled');
}


function loadDocumentListForScrutiny(data){
	$('.pagination').children().remove();
	$(".leftPaneData").html("");
	var leftPanelHtml="";
	var i=0;
	var firstRow=null;
	$.each(data,function(key,value){
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
}

function loadDocDetailsRightPane(data){
	$("#commercialDocumentDeviationForm").addClass('readonly');
	$("#technicalDocumentDeviationForm").addClass('readonly');
	$("#commercialDocumentDeviationForm #downloadFileResponse").html('');
	$("#technicalDocumentDeviationForm #downloadFileResponse").html('');
	if(!$.isEmptyObject(data)){
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
							 
							 
							 var auditorPrevStatus=data.finalAuditorStatus==null?'':data.finalAuditorStatus;
							 var auditorPrevComment=data.finalAuditorComment==null?'Yet Not Commented':data.finalAuditorComment;
							
							 
			    			 
			    			 var deviationType=data.deviationType=='FILE'?'FILE': data.deviationType;
			    		     var comment=data.deviationComment==null?'': data.deviationComment;
			    		     
			    		        var bidderTextComment=data.textResponse==null?'':data.textResponse;
			    				var bidderFileId=data.fileResponse==null?'':data.fileResponse.attachmentId;
			    				var bidderFileName=data.fileResponse==null?'':data.fileResponse.fileName;
			    				
			    				 var finalScrutinyComment=data.finalScrutinyComment==null?'':data.finalScrutinyComment;
					    		    var finalScrutinyStatus=data.finalScrutinyStatus==null?'':data.finalScrutinyStatus;
					    		    
			    				
			    				var finalScrutinyComment=data.finalScrutinyComment==null?'':data.finalScrutinyComment;
			    				$("#technicalDocumentDeviationForm #finalScrutinyComment").val(finalScrutinyComment);
			    				
			    				var finalScrutinyStatus=data.finalScrutinyStatus==null?'':data.finalScrutinyStatus;
			    			    
			    			 
			    		if(bidsection=='TS'){
			    			$("#technicalDocumentDeviationForm #deviationType").html(deviationType);
			    			if(finalScrutinyStatus=='Approved'){
			    		    	$('#technicalDocumentDeviationForm #approvedId').prop('checked',true);
			    		    }else if(finalScrutinyStatus=='Rejected'){
			    		    	$('#technicalDocumentDeviationForm #rejectId').prop('checked',true);
			    		    }
			    		    $('#technicalDocumentDeviationForm #finalScrutinyComment').val(finalScrutinyComment);
			    		    
					    			var url=$("#technicalDocumentDeviationForm #downloadFileResponse").data('url');
					    			$("#technicalDocumentDeviationForm #downloadFileResponse").attr('href',url);
					    		    $("#technicalDocumentDeviationForm #downloadFileResponse").prop('href', url+'/'+bidderFileId);
					    			$("#technicalDocumentDeviationForm #downloadFileResponse").html(bidderFileName);

					    			$("#technicalDocumentDeviationForm #bidderSectionDocId").val(biddersectionDocId);
			    			
					    			$("#technicalDocumentDeviationForm #finalScrutinyComment").val(finalScrutinyComment);
				    				$("#technicalDocumentDeviationForm #textResponse").val(bidderTextComment);
					    			$("#technicalDocumentDeviationForm #itemScrutinyLineId").val(itemScrutinyLineId);
					    			$("#technicalDocumentDeviationForm #itemScrutinyId").val(itemScrutinyId);
					    			$("#technicalDocumentDeviationForm #bidderId").val(bidderId);
					    			 
					    			$("#technicalDocumentDeviationForm #uploadedDoc").html(sectionDocName);
					    			$("#technicalDocumentDeviationForm #deviationComment").html(comment);
					    			
					    			
					    			
					    			$("#technicalDocumentDeviationForm").removeClass('readonly');
				    			 
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
				    			 $('#technicalDocumentDeviationForm .save').show();
			    			}
			    		else if(bidsection=='CS'){
			    			
			    			 $("#auditorDocForm #auditTypeCode").val(auditorPrevStatus);
							 $("#auditorDocForm #comment").val(auditorPrevComment);
							 $("#commercialDocumentDeviationForm #auditorDocPrevStatus").html(auditorPrevStatus);
							 $("#commercialDocumentDeviationForm #auditorDocPrevComment").html(auditorPrevComment);
							 $("#commercialDocumentDeviationForm #deviationType").html(deviationType);
			    			if(finalScrutinyStatus=='Approved'){
			    		    	$('#commercialDocumentDeviationForm #approvedId').prop('checked',true);
			    		    }else if(finalScrutinyStatus=='Rejected'){
			    		    	$('#commercialDocumentDeviationForm #rejectId').prop('checked',true);
			    		    }
			    		    $('#commercialDocumentDeviationForm #finalScrutinyComment').val(finalScrutinyComment);
			    			
				    			var url=$("#commercialDocumentDeviationForm #downloadFileResponse").data('url');
				    			$("#commercialDocumentDeviationForm #downloadFileResponse").attr('href',url);
				    		    $("#commercialDocumentDeviationForm #downloadFileResponse").prop('href', url+'/'+bidderFileId);
				    			$("#commercialDocumentDeviationForm #downloadFileResponse").html(bidderFileName);

				    			$("#commercialDocumentDeviationForm #bidderSectionDocId").val(biddersectionDocId);
				    			
			    				$("#commercialDocumentDeviationForm #textResponse").val(bidderTextComment);
				    			$("#commercialDocumentDeviationForm #itemScrutinyLineId").val(itemScrutinyLineId);
				    			$("#auditorDocForm #itemScrutinyLineId").val(itemScrutinyLineId);
				    			 $("#commercialDocumentDeviationForm #itemScrutinyId").val(itemScrutinyId);
				    			 $("#commercialDocumentDeviationForm #bidderId").val(bidderId);
				    			 
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
				    			 if(auditorPrevStatus.toUpperCase()=="APPROVED"){
				    				 $("#commercialDocumentDeviationForm").addClass('readonly');
				    				 $('#commercialDocumentDeviationForm .save').hide();
				    			 }else{
				    				 $("#commercialDocumentDeviationForm").removeClass('readonly');
				    				 $('#commercialDocumentDeviationForm .save').show(); 
				    			 }
				    			 /*$("#commercialDocumentDeviationForm").removeClass('readonly');*/
				    			 if(role=='AUDIT'){
				    					$('#auditorDocForm').removeClass('readonly');
				    					$('#auditorDocForm .save').show();
				    					$('#commercialDocumentDeviationForm .save').hide(); 
				    				}
			    			}
			    			 
		    			 }
		}else{
			$("#commercialDocumentDeviationForm").addClass('readonly');
			$('#commercialDocumentDeviationForm .save').hide();
			
			$("#technicalDocumentDeviationForm").addClass('readonly');
			$('#technicalDocumentDeviationForm .save').hide();
			
			$("#auditorDocForm").addClass('readonly');
			$('#auditorDocForm .save').hide();
			
			$('#technicalDocumentDeviationForm #finalScrutinyComment').val('');
		    $("#technicalDocumentDeviationForm #downloadFileResponse").removeAttr('href');
			$("#technicalDocumentDeviationForm #downloadFileResponse").html('');
			$("#technicalDocumentDeviationForm #finalScrutinyComment").val('');
			$("#technicalDocumentDeviationForm #textResponse").val('');
			$("#technicalDocumentDeviationForm #itemScrutinyLineId").val('');
			$("#technicalDocumentDeviationForm #itemScrutinyId").val('');
			$("#technicalDocumentDeviationForm #uploadedDoc").html('');
			$("#technicalDocumentDeviationForm #deviationComment").html('');
			$('#technicalDocumentDeviationForm #textResponseDivId').attr('style','display: none;');
 			$('#technicalDocumentDeviationForm #textResponseDivId').attr('disabled','disabled');
 			$('#technicalDocumentDeviationForm #fileResponseDivId').attr('style','display: none;');
 			$('#technicalDocumentDeviationForm .fileResponse').attr('disabled','disabled');
 		
 			 $("#auditorDocForm #auditTypeCode").val('');
			 $("#auditorDocForm #comment").val('');
			 $("#commercialDocumentDeviationForm #auditorDocPrevStatus").html('');
			 $("#commercialDocumentDeviationForm #auditorDocPrevComment").html('');
			 $('#commercialDocumentDeviationForm #finalScrutinyComment').val('');
			    $("#commercialDocumentDeviationForm #downloadFileResponse").removeAttr('href');
				$("#commercialDocumentDeviationForm #downloadFileResponse").html('');
				$("#commercialDocumentDeviationForm #finalScrutinyComment").val('');
				$("#commercialDocumentDeviationForm #textResponse").val('');
				$("#commercialDocumentDeviationForm #itemScrutinyLineId").val('');
				$("#commercialDocumentDeviationForm #itemScrutinyId").val('');
				$("#commercialDocumentDeviationForm #uploadedDoc").html('');
				$("#commercialDocumentDeviationForm #deviationComment").html('');
				$('#commercialDocumentDeviationForm #textResponseDivId').attr('style','display: none;');
	 			$('#commercialDocumentDeviationForm #textResponseDivId').attr('disabled','disabled');
	 			$('#commercialDocumentDeviationForm #fileResponseDivId').attr('style','display: none;');
	 			$('#commercialDocumentDeviationForm .fileResponse').attr('disabled','disabled');
			
		}
	}

function showDocDetailsDetail(el){
	var bidderSecDocId=$(el).attr('id');
	loadDocDetailsRightPane(documentsArray["bidderSecDoc_"+bidderSecDocId])
	
}
function loadCommercialConfirmatory(ele){
	cacheLi();
	setCurrentTab(ele);
	submitWithParam('getFinalCommercialStatusBybidderId','bidderId' ,'loadConfirmationForScrutiny');
}

function loadTechnicalConfirmatory(ele){
	cacheLi();
	setCurrentTab(ele);
	submitWithParam('getFinalTechnicalStatusByItemBidId','itemBidId' ,'loadConfirmationForScrutiny');
}

function loadConfirmationForScrutiny(data){
	$('#a_finalCommFileResponse').html('');
	$('#a_finalCommFileResponse').removeAttr('href');
	$('#a_finalTechFileResponse').html('');
	$('#a_finalTechFileResponse').removeAttr('href');
	
	    $('#confirmationTechnicalDevitionForm').removeAttr('readonly');
	    $('#confirmationCommercialDevitionForm').removeAttr('readonly');
	    $('#confirmationTechnicalDevitionForm .finalStatus').removeAttr('disabled');
	    $('#confirmationTechnicalDevitionForm #a_techdocFileResponse').html('');
	    $('#confirmationCommercialDevitionForm #a_comdocFileResponse').html('');
	    $('#confirmationTechnicalDevitionForm #finalScrutinyComment').val('');
	    $('#confirmationCommercialDevitionForm #finalScrutinyComment').val('');
	    
	    $("#technicalConfirmStatus").html('');
		$("#techConfirmMsgDivId").attr('style','display : none;');
		$("#commercialConfirmStatus").html('');
		$("#commConfirmMsgDivId").attr('style','display : none;');
		
		$("#confirmationCommercialDevitionForm").removeClass('readonly');
		$('#confirmationCommercialDevitionForm .save').show(); 
	
	$(".leftPaneData").html("");
	var value=data.objectMap.ItemList;
	if($.isEmptyObject(value)){
			
		   $("#techConfirmMsgDivId").removeAttr('style');
		   $("#commConfirmMsgDivId").removeAttr('style');
		    $("#technicalConfirmStatus").html('No Technical Deviation Called');
			$("#commercialConfirmStatus").html('No Commercial Deviation Called');
			
			
		    $('#confirmationTechnicalDevitionForm').addClass('readonly');
		    $('#confirmationCommercialDevitionForm').addClass('readonly');
		    $('#confirmationTechnicalDevitionForm .finalStatus').attr('disabled','disabled');
		    
		    $("#confirmationTechnicalDevitionForm #downloadTechFileResponse").removeAttr('href');
			$("#confirmationTechnicalDevitionForm #downloadTechFileResponse").html('');
			$("#confirmationTechnicalDevitionForm #downloadTechFileResponse").val('');
			
			 $("#confirmationCommercialDevitionForm #downloadComFileResponse").removeAttr('href');
 			$("#confirmationCommercialDevitionForm #downloadComFileResponse").html('');
 			$("#confirmationCommercialDevitionForm #downloadComFileResponse").val('');
 			$("#auditorConfirmForm .save").hide();
 			$("#auditorConfirmForm").addClass('readonly');
 			$("#auditorConfirmForm #auditTypeCode").val('');
 			$("#auditorConfirmForm #comment").val('');
 			$("#confirmationCommercialDevitionForm #auditorConfirmPrevStatus").html('');
			$("#confirmationCommercialDevitionForm #auditorConfirmPrevComment").html('');
			resetScrutinyFileData(); 
		}
	else{
		 if(value[0].itemScrutiny.scrutinyType=='TECHSCR'){
			 $('#confirmationTechnicalDevitionForm').removeClass('readonly');
			   
		    	var url=$("#confirmationTechnicalDevitionForm #downloadTechFileResponse").data('url');
		    	var itemScrutinyId=value[0].itemScrutiny==null?'':value[0].itemScrutiny.itemScrutinyId;
		    	$('#gennerateFinalTechnicalScrutinyDoc #itemScrutinyId').val(itemScrutinyId);
		    	$("#confirmationTechnicalDevitionForm #itemScrutinyId").val(itemScrutinyId);
		    	var attachmentId=value[0].itemScrutiny.scrutinyFile==null?'':value[0].itemScrutiny.scrutinyFile.attachmentId;
			var name=value[0].itemScrutiny.scrutinyFile==null?'':value[0].itemScrutiny.scrutinyFile.fileName;
			if(attachmentId!=''){
				    $("#confirmationTechnicalDevitionForm #downloadTechFileResponse").prop('href', url+'/'+attachmentId);
	    			$("#confirmationTechnicalDevitionForm #downloadTechFileResponse").html(name);
	    			$("#confirmationTechnicalDevitionForm #downloadTechFileResponse").val(attachmentId);
			}
			 $('#confirmationTechnicalDevitionForm #finalScrutinyComment').val(value[0].itemScrutiny.finalScrutinyComment);
			 if(data.objectMap.isAnyRejected){
				 $('#confirmationTechnicalDevitionForm #rejectedId').prop('checked',true); 
				 $('#confirmationTechnicalDevitionForm #approvedId').addClass('readonly'); 
				 $('#confirmationTechnicalDevitionForm #rejectedId').addClass('readonly');
			 }else{
				 $('#confirmationTechnicalDevitionForm #approvedId').removeClass('readonly');  
				 $('#confirmationTechnicalDevitionForm #rejectedId').removeClass('readonly');  
			 }
			 if(value[0].itemScrutiny.finalScrutinyStatus=='Approved'){
				 $('#confirmationTechnicalDevitionForm #approvedId').prop('checked',true);
			 }else if(value[0].itemScrutiny.finalScrutinyStatus=='Rejected'){
				 $('#confirmationTechnicalDevitionForm #rejectedId').prop('checked',true); 
			 }else{
				 $('#confirmationTechnicalDevitionForm #rejectedId').prop('checked',true); 
			 }
			 setScrutinyFileData('TECHSCR');
		    }else{
		    	var itemScrutiny=value[0].itemScrutiny;
		    	 var auditorPrevStatus=itemScrutiny.finalAuditorStatus==null?'':itemScrutiny.finalAuditorStatus;
				 var auditorPrevComment=itemScrutiny.finalAuditorComment==null?'Yet Not Commented':itemScrutiny.finalAuditorComment;
				 
		    	$("#confirmationCommercialDevitionForm #itemScrutinyId").val(itemScrutiny.itemScrutinyId);
		    	$('#gennerateFinalCommercialScrutinyDoc #itemScrutinyId').val(itemScrutiny.itemScrutinyId);
		    	$("#auditorConfirmForm #itemScrutinyId").val(itemScrutiny.itemScrutinyId);
				 $("#confirmationCommercialDevitionForm #bidderId").val(itemScrutiny.bidder.bidderId);
				 $( "#auditorConfirmForm #bidderId").val(itemScrutiny.bidder.bidderId);
				 
		    	 $("#auditorConfirmForm #auditTypeCode").val(itemScrutiny.finalAuditorStatus);
				 $("#auditorConfirmForm #comment").val(itemScrutiny.finalAuditorComment);
				 
				 $("#confirmationCommercialDevitionForm #auditPrevStatus").val(itemScrutiny.finalAuditorStatus);
				 $("#confirmationCommercialDevitionForm #auditPrevComment").val(itemScrutiny.finalAuditorComment);
				 
				 $("#confirmationCommercialDevitionForm #auditorConfirmPrevStatus").html(itemScrutiny.finalAuditorStatus);
				 $("#confirmationCommercialDevitionForm #auditorConfirmPrevComment").html(itemScrutiny.finalAuditorComment);
				 
		    	 $('#confirmationCommercialDevitionForm').removeClass('readonly');
		    	var url=$("#confirmationCommercialDevitionForm #downloadTechFileResponse").data('url');
			var attachmentId=value[0].itemScrutiny.scrutinyFile==null?'':value[0].itemScrutiny.scrutinyFile.attachmentId;
			var name=value[0].itemScrutiny.scrutinyFile==null?'':value[0].itemScrutiny.scrutinyFile.fileName;
			if(attachmentId!=''){
				    $("#confirmationCommercialDevitionForm #downloadComFileResponse").prop('href', url+'/'+attachmentId);
	    			$("#confirmationCommercialDevitionForm #downloadComFileResponse").html(name);
	    			$("#confirmationCommercialDevitionForm #downloadComFileResponse").val(attachmentId);
			}
			  $('#confirmationCommercialDevitionForm #finalScrutinyComment').val(value[0].itemScrutiny.finalScrutinyComment);
			  if(value[0].itemScrutiny.finalScrutinyStatus=='Approved'){
				  $('#confirmationCommercialDevitionForm #approvedId').prop('checked',true);
				 }else if(value[0].itemScrutiny.finalScrutinyStatus=='Rejected'){
					 $('#confirmationCommercialDevitionForm #rejectedId').prop('checked',true);
				 }
			  if(auditorPrevStatus.toUpperCase()=="APPROVED"){
					 $("#confirmationCommercialDevitionForm").addClass('readonly');
					 $('#confirmationCommercialDevitionForm .save').hide();
				 }else{
					 $("#confirmationCommercialDevitionForm").removeClass('readonly');
					 $('#confirmationCommercialDevitionForm .save').show(); 
				 }	
			  if(role=='AUDIT'){
				  $("#auditorConfirmForm .save").show();
		 		  $("#auditorConfirmForm").removeClass('readonly');
				}
			  setScrutinyFileData('COMMSCR');
		    }
		 if(!data.objectMap.isAnyRejected){
			   $("#auditorConfirmForm #auditTypeCode").val('CLARIFICATION');
			   $("#auditorConfirmForm #auditTypeCode").addClass('readonly');
		   }
	}
	if(data.objectMap.role!='VENADM'){
		  $('#confirmationTechnicalDevitionForm .save').attr('onclick','return submitIt("confirmationTechnicalDevitionForm","confirmFinalTechnicalScrutiny","confirmDeviationSubmitResp");');
		  $('#confirmationCommercialDevitionForm .save').attr('onclick','return submitIt("confirmationCommercialDevitionForm","confirmFinalCommercialScrutiny","confirmDeviationSubmitResp");');
		  $('#confirmationTechnicalDevitionForm .finalStatus').removeAttr('disabled');
		  $('#confirmationTechnicalDevitionForm #finalScrutinyDivId').removeAttr('style');
		  $('#confirmationCommercialDevitionForm #finalScrutinyDivId').removeAttr('style')
		  $('#confirmationTechnicalDevitionForm #confirmCheckBox').attr('style','display: none;');
		  $('#confirmationCommercialDevitionForm #confirmCheckBox').attr('style','display: none;');
		  
		}
	if(role=='AUDIT'){
		$("#confirmationCommercialDevitionForm").addClass('readonly');
		$('#confirmationCommercialDevitionForm .save').hide(); 
	}
	}

function confirmDeviationSubmitResp(data){
	if(data.objectMap.resultStatus==true){
			Alert.info(data.objectMap.Status);
			removeScrutinyFileData();
	}else{
		    Alert.warn(data.objectMap.Status);
	}
}

function loadFinalScrutinyResp(ele){
	cacheLi();
	setCurrentTab(ele);
	/*if(getChangedFlag()){*/
		submitWithParam('getFinalScrutinyStatus','confirmationFinalScrutinyForm #tahdrId' ,'loadFinalScrutinyStatusResp');
		/*setChangedFlag(false);
	}else{
		getCacheLi();
	}*/
		var documentType=$(".documentType").val();
		/*setHeaderValues(documentType+"No.: " + tahdrCodes, documentType+"Title : " + title, "Department : " + department, "Status : " + status);*/
		setHeaderValues(documentType+" Code: " +tenderCode, documentType+" Contact : "+tenderContact,"Description: "+tenderDescription, "Department : "+tenderDepartment);
		
		//setHeaderValues("Tender Code: "+tenderCode, "Tender Contact : "+tenderContact, "Description: "+tenderDescription, "Department : "+tenderDepartment);
}

function loadFinalScrutinyStatusResp(data){
	$(".leftPaneData").html("");
	if(data.objectMap.result){
		if(!$.isEmptyObject(data.objectMap.resultObject)){
			if(data.objectMap.resultObject.tahdrStatusCode=='SCRDONE'){
				$('#confirmationFinalScrutinyForm').addClass('readonly');
				$('#confirmationFinalScrutinyForm #confirmCheckBox').attr('checked','checked');
				$('#confirmationFinalScrutinyForm #confirmCheckBox').removeAttr('name');
				$('#confirmCheckBox').html('FinalScrutiny Has been Updated');
				$('#finalDeviationSaveBtnId').hide();
			}
			else{
				$('#confirmationFinalScrutinyForm #confirmCheckBox').attr('checked','checked');
				$('#confirmationFinalScrutinyForm #confirmCheckBox').addClass('readonly');
				$('#finalDeviationSaveBtnId').show();
				$('#confirmationFinalScrutinyForm').removeClass('readonly');
			}
		}else{
			Alert.warn(data.objectMap.resultMessage);
		}
		
	}else{
		Alert.warn(data.objectMap.resultMessage);
		$('#confirmationFinalScrutinyForm #confirmCheckBox').attr('disabled','disabled');
		$('#confirmationFinalScrutinyForm').addClass('readonly');
	}
	/*setChildLoadFlag(true);	*/
}

function confirmFinalScrutinyResp(data){
	if(!$.isEmptyObject(data)){
		if(data.objectMap.Status){
			Alert.info(data.objectMap.resultMessage);
		}
		else{
			Alert.warn(data.objectMap.resultMessage);
		}
		
	}
	else
		Alert.warn('Not Submitted !');
}

function scrutinyFinalSubmitResp(data){
	if(data.objectMap.resultStatus){
	      var itemScrutinyLineId=$('#commercialDeviationForm #itemScrutinyLineId').val();
			scrutinyPointArray["itemScrutinyLine_"+itemScrutinyLineId]=data.objectMap.itemScrutinyLine;
			Alert.info(data.objectMap.Status);
	}
	else{
			Alert.warn(data.objectMap.Status);
	}
	
}

function commDocFinalSubmitResp(data){
	if(data.objectMap.resultStatus){
		 var biddersectionDocId=$('#commercialDocumentDeviationForm #biddersectionDocId').val();
		 documentsArray["bidderSecDoc_"+biddersectionDocId]=data.objectMap.itemScrutinyLine;
			/*$("#technicalDeviationForm .save").hide();*/
			Alert.info(data.objectMap.Status);
	}
	else{
			Alert.warn(data.objectMap.Status);
	}
	
}

function bidderGtpFinalSubmitResp(data){
	if(data.objectMap.resultStatus){
		 var bidderGtpId=$('#technicalDeviationForm #bidderGtpId').val();
		 bidderGtpArray["bidderGtp_"+bidderGtpId]=data.objectMap.itemScrutinyLine;
			/*$("#technicalDeviationForm .save").hide();*/
			Alert.info(data.objectMap.Status);
	}
	else{
			Alert.warn(data.objectMap.Status);
	}
	
}

function techDocFinalSubmitResp(data){
	if(data.objectMap.resultStatus){
		 var biddersectionDocId=$('#technicalDocumentDeviationForm #bidderSectionDocId').val();
		 documentsArray["bidderSecDoc_"+biddersectionDocId]=data.objectMap.itemScrutinyLine;
			/*$("#technicalDeviationForm .save").hide();*/
			Alert.info(data.objectMap.Status);
	}
	else{
			Alert.warn(data.objectMap.Status);
	}
}

function commDocFinalSubmitResp(data){
	if(data.objectMap.resultStatus){
		 var biddersectionDocId=$('#commercialDocumentDeviationForm #bidderSectionDocId').val();
		 documentsArray["bidderSecDoc_"+biddersectionDocId]=data.objectMap.itemScrutinyLine;
			/*$("#technicalDeviationForm .save").hide();*/
			Alert.info(data.objectMap.Status);
	}
	else{
			Alert.warn(data.objectMap.Status);
	}
}

function saveFinalAuditorCommentResp(data){
	if(data.objectMap.resultStatus){
		var itemScrutinyLineId=$("#auditorForm #itemScrutinyLineId").val();
		 var auditorPrevStatus=$("#auditorForm #auditTypeCode").val();
		 var auditorPrevComment=$("#auditorForm #comment").val();
		 scrutinyPointArray["itemScrutinyLine_"+itemScrutinyLineId].finalAuditorStatus=auditorPrevStatus;
		 scrutinyPointArray["itemScrutinyLine_"+itemScrutinyLineId].finalAuditorComment=auditorPrevComment;
		 
		 $("#commercialDeviationForm #auditorPrevStatus").html(auditorPrevStatus);
		 $("#commercialDeviationForm #auditorPrevComment").html(auditorPrevComment);
		Alert.info(data.objectMap.resultMessage);
		
	}else{
		Alert.warn(data.objectMap.resultMessage);
	}
}

function saveFinalDocAuditorCommentResp(data){
	if(data.objectMap.resultStatus==true){
		var biddersectionDocId=$("#commercialDocumentDeviationForm #bidderSectionDocId").val();
		 var auditorPrevStatus=$("#auditorDocForm #auditTypeCode").val();
		 var auditorPrevComment=$("#auditorDocForm #comment").val();
		 documentsArray["bidderSecDoc_"+biddersectionDocId].finalAuditorStatus=auditorPrevStatus;
		 documentsArray["bidderSecDoc_"+biddersectionDocId].finalAuditorComment=auditorPrevComment;
		 
		 $("#commercialDocumentDeviationForm #auditorDocPrevStatus").html(auditorPrevStatus);
		 $("#commercialDocumentDeviationForm #auditorDocPrevComment").html(auditorPrevComment);
		Alert.info(data.objectMap.resultMessage);
		
	}else{
		Alert.warn(data.objectMap.resultMessage);
	}
}

function saveFinalConfirmAuditorCommentResp(data){
	if(data.objectMap.resultStatus){
		 var auditorPrevStatus=$("#auditorConfirmForm #auditTypeCode").val();
		 var auditorPrevComment=$("#auditorConfirmForm #comment").val();
		 
		 $("#confirmationCommercialDevitionForm #auditorConfirmPrevStatus").html(auditorPrevStatus);
		 $("#confirmationCommercialDevitionForm #auditorConfirmPrevComment").html(auditorPrevComment);
		Alert.info(data.objectMap.resultMessage);
		
	}else{
		Alert.warn(data.objectMap.resultMessage);
	}
}

function downloadReportResp(data){
	/*if(data.objectMap.result){
		Alert.info(data.objectMap.resultMessage);
	}else{
		Alert.warn(data.objectMap.resultMessage);
	}*/
}

function getBidderScrutinyStatus(){
	submitWithParam('getScrutinyStatus','bidderForm #bidderId' ,'bidderScrutinyStatusResp');
}

function bidderScrutinyStatusResp(data){
	$('#tScrutinyStatusDiv').html('');
	$('#cScrutinyStatusDiv').html('');
	$('#aScrutinyStatusDiv').html('');
	var statusClass='';
	var htmlElement='';
	if(data.objectMap.hasOwnProperty('techScrutiny')){
		 
		var count=1;
		$.each(data.objectMap.techScrutiny, function(key, value) {
			var materialName=value.itemBid==null?'':value.itemBid.tahdrMaterial==null?'':value.itemBid.tahdrMaterial.material
					==null?'':value.itemBid.tahdrMaterial.material.name;
		    var preliminaryStatus=value.preliminaryScrutinyStatus==null?'NA':value.preliminaryScrutinyStatus.toUpperCase();
		    var scrutinyFileStatus=(value.isFinalScrutinySubmitted==null || value.isFinalScrutinySubmitted=='N')?'Final Scrutiny File Not Uploaded':'Final Scrutiny File Uploaded';
		    var scrutinyFileColor=(value.isFinalScrutinySubmitted==null || value.isFinalScrutinySubmitted=='N')?'rejectedStatus':'colorskyblue';
		    var status='';
		    if(preliminaryStatus!='DEVIATION'){
		    	status="Preliminary ";
		    	 status += value.preliminaryScrutinyStatus==null?'NA':value.preliminaryScrutinyStatus.toUpperCase();
		    	 scrutinyFileStatus=(value.isScrutinySubmitted==null || value.isScrutinySubmitted=='N')?'Preliminary Scrutiny File Not Uploaded':'Preliminary Scrutiny File Uploaded';
			    	scrutinyFileColor=(value.isScrutinySubmitted==null || value.isScrutinySubmitted=='N')?'rejectedStatus':'colorskyblue'; 
		    }else{
		    	 status=value.finalScrutinyStatus==null?'PENDING':value.finalScrutinyStatus.toUpperCase();
		    }
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
                     '<span class="detspan col-sm-6 '+statusClass+'" id="" >'+status+'</span><br><span class="'+scrutinyFileColor+' col-sm-6" id="" >'+scrutinyFileStatus+'</span></div> </div>';
			count++;
		   });
		$('#tScrutinyStatusDiv').append(htmlElement);
		htmlElement='';
    }
	if(data.objectMap.hasOwnProperty('commScrutiny')){
		 
		var value=data.objectMap.commScrutiny;
		 var cstatus=value.finalScrutinyStatus==null?'PENDING':value.finalScrutinyStatus.toUpperCase();
		 var preliminaryStatus=value.preliminaryScrutinyStatus==null?'NA':value.preliminaryScrutinyStatus.toUpperCase();
		 var scrutinyFileStatus=(value.isFinalScrutinySubmitted==null || value.isFinalScrutinySubmitted=='N')?'Final Scrutiny File Not Uploaded':'Final Scrutiny File Uploaded';
		 var scrutinyFileColor=(value.isFinalScrutinySubmitted==null || value.isFinalScrutinySubmitted=='N')?'rejectedStatus':'colorskyblue'; 
		 if(preliminaryStatus!='REJECTED'){
		    	cstatus="Preliminary ";
		    	cstatus += value.preliminaryScrutinyStatus==null?'NA':value.preliminaryScrutinyStatus.toUpperCase();
		    	scrutinyFileStatus=(value.isScrutinySubmitted==null || value.isScrutinySubmitted=='N')?'Preliminary Scrutiny File Not Uploaded':'Preliminary Scrutiny File Uploaded';
		    	scrutinyFileColor=(value.isScrutinySubmitted==null || value.isScrutinySubmitted=='N')?'rejectedStatus':'colorskyblue'; 
		    }else{
		    	cstatus=value.finalScrutinyStatus==null?'PENDING':value.finalScrutinyStatus.toUpperCase();
		    }
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
		htmlElement=htmlElement+'<span class="detspan '+statusClass+'" id="" >'+cstatus+'</span><br><span class="'+scrutinyFileColor+'" id="" >'+scrutinyFileStatus+'</span>';
		$('#cScrutinyStatusDiv').append(htmlElement);
		htmlElement='';
		
		 var astatus=value.finalAuditorStatus==null?'PENDING':value.finalAuditorStatus.toUpperCase();
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
    }
	
}

function fetchTenderList(pageNumber, pageSize, searchMode, searchValue){
	var typecode=$('input[name=tenderTypeCodeToggle]:checked').val();
	var response;
	$.ajax({
        type : "POST",
        contentType : "application/json",
        url: "fetchTenderListForFinalScrutiny?pageNumber="+pageNumber+"&pageSize="+pageSize+'&searchMode='+searchMode+'&serachValue='+searchValue+'&typeCode='+typecode,
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