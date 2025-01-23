/**
 * createdBy : Aman Sahu
 */

var tenderArray= new Array();
var tahdrMaterialArray=new Array();
var bidderBidDetailArray=new Array();
var tenderStatusList=new Array();
var bidderStatus=new Array();
var tenderTypeCode='';

var leftPanePageSize = 7;
var maxVisiblePageNumbers = 3;
var selectedPage = 1;
var pageSize=7;
var searchMode='none';
var searchValue='none';

var ttitle='';
var tversion='';
var tdescription ='';
var tcontact ='';

var mcode='';
var mname='';
var mreqQty='';
var mmatType='';

var bname='';

var documentType='Tender/Auction';
var LastPage=0;

$(document).ready(function(){
	onPageLoad();
	$("input[name='tenderTypeCodeToggle']").on("change",function(){
		selectedPage=1;
		$('.pagination').html('');
		var data=fetchTenderList(1, pageSize, searchMode, searchValue,$('input[name=tenderTypeCodeToggle]:checked').val());
		loadTenderListFromPagination(data.data);
		setPagination(data.objectMap.LastPage, selectedPage , maxVisiblePageNumbers);
		
		 $(".tabs-left li a label").text(function(index, currentText) {
			    return currentText.substr(0, 20);
			});
		 documentType=$(".documentType").val();
	});
	
	$('#tenderTabId').click(function(event) {
		/*event.preventDefault();
		cacheLi();
		setCurrentTab(this);
		$('.pagination').html('');
		if(getChangedFlag()){
			var data=fetchTenderList(1, pageSize, searchMode, searchValue,$(this).val());
			loadTenderListFromPagination(data.data);
			LastPage=data.objectMap.LastPage
			setPagination(data.objectMap.LastPage, selectedPage , maxVisiblePageNumbers);
			
			setChangedFlag(false);
		}else{
			getCacheLi();
			setHeaderValues(documentType+" Title: "+ttitle, documentType+" Version : "+tversion, "Description: "+tdescription, "Contact Detail : "+tcontact);
			setPagination(LastPage, selectedPage , maxVisiblePageNumbers);
		}*/
		selectedPage=1;
		$('.pagination').html('');
		var data=fetchTenderList(1, pageSize, searchMode, searchValue,$(this).val());
		loadTenderListFromPagination(data.data);
		setPagination(data.objectMap.LastPage, selectedPage , maxVisiblePageNumbers);
		
		 $(".tabs-left li a label").text(function(index, currentText) {
			    return currentText.substr(0, 20);
			});
    });
	
	$('#pagination-here').paginate({
		pageSize:  7,
		dataSource: 'fetchTenderList',
		responseTo:  'loadTenderListFromPagination',
		maxVisiblePageNumbers:3,
		searchBoxID : 'searchLiteralId',
		loadOnStartup: false
	});

});
function onPageLoad(){
	var typeCode=$("input[name='tenderTypeCodeToggle']:checked").val();
	documentType=$(".documentType").val();
	var data=fetchTenderList(1, pageSize, searchMode, searchValue,typeCode);
	loadTenderListFromPagination(data.data);
	setPagination(data.objectMap.LastPage, selectedPage , maxVisiblePageNumbers);
	
	setCurrentTab($("#tenderTabId")[0]);
    setChangedFlag(false);
}

function loadTenderListFromPagination(data){
	if(data.hasOwnProperty('tenderStatusList')){
		tenderStatusList=data.tenderStatusList;
    }
	if(data.hasOwnProperty('tahdrList')){
		loadTenderList(data.tahdrList);
    }
}

function loadTenderList(data){
			$(".leftPaneData").html("");
			var leftPanelHtml="";
			var i=0;
			var firstRow=null;
			if(!$.isEmptyObject(data)){
					$.each(data,function(key,value){
						if(!$.isEmptyObject(value)){
							var tahdrId = value.tahdrId==null?'':value.tahdrId;
							var tahdrCode = value.tahdrCode==null?'': value.tahdrCode;
							var bidType=value.bidTypeCode==null?'': value.bidTypeCode=='SB'?"Single Bid":"Two Bid";
							var budgetType=value.budgetType==null?'': value.budgetType=="CAP"?"CAPEX":"REVENUE";
							
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
							    +'	<label class="col-xs-6">'+bidType+'</label>'
							    +' </div>'	
							    +' <div class="col-md-12">'
							    +'	<label class="col-xs-6" id="'+tahdrId+'_status">'+budgetType+'</label>'
								+'	<label class="col-xs-6"></label>'
								+' </div>'
							    +' </a>'
							    +' </li>';
								i++;
							}  
					});
					$(".leftPaneData").html(leftPanelHtml);
					loadTahdrDetailRightPane(firstRow);
					 $(".tabs-left li a label").text(function(index, currentText) {
						    return currentText.substr(0, 20);
						});
					 $('.viewBidTab').removeClass('readonly');
					 $('.bidTab').addClass('readonly');
					 $('.bidderTab').addClass('readonly');
			}else{
				$('.viewBidTab').addClass('readonly');
			}
}

function loadTahdrDetailRightPane(data){
	if(!$.isEmptyObject(data)){
	  var tahdrDetail=data.tahdrDetail[0];
	  var tahdrId=data.tahdrId==null?'':data.tahdrId;
	  var tahdrCode=data.tahdrCode==null?'':data.tahdrCode;
	  var title=data.title==null?'':data.title;
	  var version=tahdrDetail.version==null?'':tahdrDetail.version;
	  var description=tahdrDetail.description==null?'':tahdrDetail.description;
	  var contact=tahdrDetail.contactEmailId==null?'':tahdrDetail.contactEmailId;
	  var isManufacture=data.isManufacturer==null?'Not Specified':data.isManufacturer=='Y'?'YES':'NO';
	  var isTrade=data.isTrader==null?'Not Specified':data.isTrader=='Y'?'YES':'NO';
	  var tenderBidType=data.bidTypeCode;
	  var tenderDoc=tahdrDetail.tenderDoc;
	  
	  if(tenderBidType=='SB'){
		  $('#tenderDetailsForm #tenderBidType').html('Single Bid');
		  $('.finalScrutiny').attr('style','display : none;');
	  }else{
		  $('.finalScrutiny').removeAttr('style');
		  $('#tenderDetailsForm #tenderBidType').html('Two Bid');
	  }
	  
	  $('#tenderDetailsForm #tahdrId').val(tahdrId);
	  $('#tenderDetailsForm #tenderNo').html(tahdrCode);
	  $('#tenderDetailsForm #trader').html(isTrade);
	  $('#tenderDetailsForm #manfacturer').html(isManufacture);
	  var status=data.tahdrStatusCode==null?'':data.tahdrStatusCode;
	  status=tenderStatusList[status]
	  $('#tenderDetailsForm #status').html(status);
	 if(tenderDoc!=null){
		 var url=$("#downloadTenderDoc").data('url');
		 var fileId=tenderDoc.attachmentId;
		 var fileName=tenderDoc.fileName;
		 $("#downloadTenderDoc").attr('href',url);
		 $("#downloadTenderDoc").prop('href', url+'/'+fileId);
		 $("#downloadTenderDoc").html(fileName); 
	 }else{
		 $("#downloadTenderDoc").removeAttr('href',url);
		 $("#downloadTenderDoc").html(''); 
	 }
	 ttitle=title;
	 tversion=version;
	 tdescription =description;
	 tcontact =contact;
	 var data=fetchList('getBidderListByTahdrId',tahdrId);
	 $('#tenderDetailsForm #noBidder').html(data.objectMap.responseList.length);
		setHeaderValues(documentType+" Title: "+title, documentType+" Version : "+version, "Description: "+description, "Contact Detail : "+contact);
	}else{
		 $('.finalScrutiny').removeAttr('style');
		 $('#tenderDetailsForm #tahdrId').val('');
		 $('#tenderDetailsForm #trader').html('');
		 $('#tenderDetailsForm #manufacturer').html('');
		 $('#tenderDetailsForm #status').html('');
		 $('#tenderDetailsForm #noBidder').html('');
		 $('#tenderDetailsForm #tenderBidType').html('');
		 ttitle='';
		 tversion='';
		 tdescription ='';
		 tcontact ='';
		setHeaderValues(documentType+" Title: ", documentType+" Version : ", "Description: ", "Contact Detail : ");
	}
	/*setChildLoadFlag(true);*/
}

function showTahdrDetail(id){
loadTahdrDetailRightPane(tenderArray['tender_'+id]);
}

function resetBidsDetailForm(){

	var documentType=$(".documentType").val();
	setHeaderValues(documentType+" Title :" , documentType+" Title : ","Description: ", "Contact Detail : ");

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

function fetchTenderList(pageNumber, pageSize, searchMode, searchValue){
	var typecode=$('input[name=tenderTypeCodeToggle]:checked').val();
	var response;
	$.ajax({
        type : "POST",
        contentType : "application/json",
        url: "getTenders?pageNumber="+pageNumber+"&pageSize="+pageSize+'&searchMode='+searchMode+'&serachValue='+searchValue+'&typeCode='+typecode,
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

function loadMaterial(ele){
	/*cacheLi();
	setCurrentTab(ele);
	if(getChangedFlag()){*/
	     submitWithParam('getTAHDRMaterialListByTahdrId','tahdrId','loadTahdrMaterialListForView');
		/*setChangedFlag(false);
	}else{
		getCacheLi();
		getBidderScrutinyStatus();
	}
	setHeaderValues(documentType+" Title: "+ttitle, documentType+" Version : "+tversion, "Description: "+tdescription, "Contact Detail : "+tcontact);*/
}

function loadTahdrMaterialListForView(data){
	$('.pagination').children().remove();
    $(".leftPaneData").html("");
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
		$(".leftPaneData").html(leftPanelHtml);
		loadTahdrMaterialRightPane(firstRow);
		$('.leftPaneData').paginathing({perPage: 6});
		$('.bidderTab').removeClass('readonly');
	}else{
		$('.bidderTab').addClass('readonly');
	}

}

function showTahdrMaterialDetail(el){
	var tahdrMaterialId=$(el).attr('id');
	loadTahdrMaterialRightPane(tahdrMaterialArray["tahdrMaterial_"+tahdrMaterialId]);	
}

function loadTahdrMaterialRightPane(data){
	if(!$.isEmptyObject(data)){
		    var tahdrMaterialId = data.tahdrMaterialId==null?'': data.tahdrMaterialId;
		    var name = data.material.materialId==null?'': data.material.name;
		    var code = data.material.materialId==null?'': data.material.code;
		    var uomName = data.material.uom.name==null?'': data.material.uom.name;
			var description =data.material.description==null?'': data.material.description;
			var reqQuantity =  data.quantity==null?'':data.quantity;
			var specVersion =  data.materialVersion==null?'':data.materialVersion.name;
			var tenderMatType =  data.materialTypeCode==null?'':data.materialTypeCode;
			$("#itemDetailForm #tahdrMaterialId").val(tahdrMaterialId);
			$("#itemDetailForm #matName").html(name);
			$("#itemDetailForm #matCode").html(code);
			$("#itemDetailForm #uom").html(uomName);
			$("#itemDetailForm #description").html(description);
			
			mcode=code;
			mname=name;
			mreqQty=reqQuantity;
			mmatType=tenderMatType;
			
			$("#itemDetailForm #reqQuantity").html(reqQuantity);
			$("#itemDetailForm #specVersion").html(specVersion);
			$("#itemDetailForm #tenderMatType").html(tenderMatType);
			setHeaderValues(documentType+" Title: "+ttitle, documentType+" Version : "+tversion, "Description: "+tdescription, "Contact Detail : "+tcontact);
	}else{
		mcode='';
		mname='';
		mreqQty='';
		mmatType='';
		$("#itemDetailForm #tahdrMaterialId").val('');
		$("#itemDetailForm #matName").html('');
		$("#itemDetailForm #matCode").html('');
		$("#itemDetailForm #uom").html('');
		$("#itemDetailForm #description").html('');
		
		$("#itemDetailForm #reqQuantity").html('');
		$("#itemDetailForm #specVersion").html('');
		$("#itemDetailForm #tenderMatType").html('');
	}
}

function loadBidders(ele){
	/*cacheLi();
	setCurrentTab(ele);
	if(getChangedFlag()){*/
		submitWithTwoParam('getBidderList','tenderDetailsForm #tahdrId','itemDetailForm #tahdrMaterialId' ,'loadBidderListForView');
		/*setChangedFlag(false);
	}else{
		getCacheLi();
		getBidderScrutinyStatus();
	}
	setHeaderValues("Material name: "+mname, "Material Code: "+mcode, "Required Qtys: "+mreqQty, "Material Type : "+mmatType);*/
}

function loadBidderListForView(data){
	$('.pagination').children().remove();
    $(".leftPaneData").html("");
	var leftPanelHtml="";
	var i=0;
	var firstRow=null;
	if(!$.isEmptyObject(data.objectMap.bidderStatusList)){
		bidderStatus=data.objectMap.bidderStatusList;
	}
	if(!$.isEmptyObject(data.objectMap.bidderList)){
		$.each(data.objectMap.bidderList,function(key,bidder){
					var tahdr=bidder==null?'':bidder.tahdr;
					var bidderId = bidder==null?'':bidder.bidderId;
					var bidderName=bidder.partner==null?'':bidder.partner.name==null?'':bidder.partner.name;
					var title=tahdr==null?'':tahdr.title==null?'':tahdr.title;
					var tenderNo=tahdr==null?'':tahdr.tahdrCode==null?'':tahdr.tahdrCode;
						
					bidderBidDetailArray['bidder_'+bidderId]=bidder;
					if(i==0){
						firstRow = bidder;
						leftPanelHtml = leftPanelHtml + ' <li class="active" onclick="showBidderDetails('+bidderId+')" id="'+bidderId+'">';
					}else{
						leftPanelHtml = leftPanelHtml + ' <li onclick="showBidderDetails('+bidderId+')" id="'+bidderId+'">';
					}
					leftPanelHtml = leftPanelHtml +' <a href="#Master" data-toggle="tab">'
				    +' <div class="col-md-12">'
				    +'  <label class="col-xs-6" id="'+bidderId+'_name">'+bidderName+'</label>'
				    +'	<label class="col-xs-6" id="'+bidderId+'_tenderNo"></label>'
				    +' </div>'	
				    +' <div class="col-md-12">'
				    +'	<label class="col-xs-6" id="'+bidderId+'_bidType"></label>'
					+'	<label class="col-xs-6" id="'+bidderId+'_title"></label>'
					+' </div>'
				    +' </a>'
					i++;
		});
		$(".leftPaneData").html(leftPanelHtml);
		loadBidderListRightPane(firstRow);
		$('.leftPaneData').paginathing({perPage: 6});
		$('.bidTab').removeClass('readonly');
	}else{
		$('.bidTab').addClass('readonly');
		$('#bidderDetailForm #bidderId').val('');
		$('#bidderDetailForm #bidderName').html('');
		$('#bidderDetailForm #tenderName').html('');
		$('#bidderDetailForm #tenderNo').html('');
		$('#bidderDetailForm #bidderStatus').html('');
	}
	
}

function loadBidderListRightPane(data){
	if(!$.isEmptyObject(data)){
		    var partnerId = data.partner==null?'':data.partner.bPartnerId;
		    var bidderId = data.bidderId==null?'':data.bidderId;
			var bidderName=data.partner==null?'':data.partner.name==null?'':data.partner.name;
			var title=data.tahdr==null?'':data.tahdr.title==null?'':data.tahdr.title;
			var tenderNo=data.tahdr==null?'':data.tahdr.tahdrCode==null?'':data.tahdr.tahdrCode;
			
			var status=data.status==null?'':data.status;
	        if(status!=''){
	        	status=bidderStatus[status];	
	        }
	        
			$('#bidderDetailForm #bidderId').val(bidderId);
			$('#bidderDetailForm #bidderName').html(bidderName);
			$('#bidderDetailForm #tenderName').html(title);
			$('#bidderDetailForm #tenderNo').html(tenderNo);
			$('#bidderDetailForm #bidderStatus').html(status);
			bname=bidderName;
			setHeaderValues("Material name: "+mname, "Material Code: "+mcode, "Required Qtys: "+mreqQty, "Material Type : "+mmatType);
		}
	else{
		$('#bidderDetailForm #bidderId').val('');
		$('#bidderDetailForm #bidderName').html('');
		$('#bidderDetailForm #tenderName').html('');
		$('#bidderDetailForm #tenderNo').html('');
		$('#bidderDetailForm #bidderStatus').html('');
	}
}

function showBidderDetails(id){
	loadBidderListRightPane(bidderBidDetailArray['bidder_'+id]);
}

function loadBidderBidsForView(data){
	$('#leftPaneData li:not(.active)').remove();
	$(".docs").removeAttr('href');
	$(".docs").html('');
	var url=$("#downloadTechDoc").data('url');
	if(!$.isEmptyObject(data.objectMap.technicalBid)){
	    var value=data.objectMap.technicalBid;
		var techBid=value.digiSignedDoc==null?null:value.digiSignedDoc.attachmentId;
		if(techBid!=null){
			var file=value.digiSignedDoc==null?'':value.digiSignedDoc.fileName;
			$("#downloadTechDoc").attr('href',url);
			$("#downloadTechDoc").prop('href', url+'/'+techBid);
			$("#downloadTechDoc").html(file);
		}
	}
	if(!$.isEmptyObject(data.objectMap.commercialBid)){
		 var value=data.objectMap.commercialBid;
			var commBid=value.digiSignedDoc==null?null:value.digiSignedDoc.attachmentId;
			if(commBid!=null){
				var file=value.digiSignedDoc==null?null:value.digiSignedDoc.fileName;
				$("#downloadCommDoc").attr('href',url);
				$("#downloadCommDoc").prop('href', url+'/'+commBid);
				$("#downloadCommDoc").html(file);
			}
	}
	if(!$.isEmptyObject(data.objectMap.technicalDeviation)){
		 var value=data.objectMap.technicalDeviation;
			var techScrutiny=value.scrutinyFile==null?null:value.scrutinyFile.attachmentId;
			if(techScrutiny!=null){
				var file=value.scrutinyFile==null?null:value.scrutinyFile.fileName;
				$("#downloadTechDvtnDoc").attr('href',url);
				$("#downloadTechDvtnDoc").prop('href', url+'/'+techScrutiny);
				$("#downloadTechDvtnDoc").html(file);
			}
	}
	if(!$.isEmptyObject(data.objectMap.commcialDeviation)){
		 var value=data.objectMap.commcialDeviation;
			var commScrutiny=value.scrutinyFile==null?null:value.scrutinyFile.attachmentId;
			if(commScrutiny!=null){
				var file=value.scrutinyFile==null?null:value.scrutinyFile.fileName;
				$("#downloadOtherDoc").attr('href',url);
				$("#downloadOtherDoc").prop('href', url+'/'+commScrutiny);
				$("#downloadOtherDoc").html(file);
			}
	}
    if(!$.isEmptyObject(data.objectMap.priceBid)){
    	var value=data.objectMap.priceBid;
    	if(value!=null){
    		var isAnnexureBid=value.isAnnexureC1==null?'N':value.isAnnexureC1;
    		if(isAnnexureBid=='N'){
    			/*$('#c1DivId').attr('style','display: none ;');*/
    			$('#pbDivId').removeAttr('style','display: none ;');
    			$('#infolabelId').html('');
         		var priceBid=value.digiSignedDoc==null?null:value.digiSignedDoc.attachmentId;
         		if(priceBid!=null){
         			var file=value.digiSignedDoc==null?null:value.digiSignedDoc.fileName;
         			$("#downloadPBDoc").removeAttr('disabled');
         			$("#downloadPBDoc").attr('href',url);
    				$("#downloadPBDoc").prop('href', url+'/'+priceBid);
    				$("#downloadPBDoc").html(file);
    				$("#downloadC1Doc").html('Annexure C1 not accepted yet or not called for Annexure c1');
    				$("#downloadC1Doc").attr('disabled','disabled');
         		}
        	}else{
        		var value1=data.objectMap.tempPriceBid
         		var annexureBid=value.digiSignedDoc==null?null:value.digiSignedDoc.attachmentId;
         		if(annexureBid!=null){
         			/*$('#pbDivId').attr('style','display: none ;');*/
        			$('#c1DivId').removeAttr('style','display: none ;');
        			$('#infolabelId').html('(Agreed Annexcure-C1 )');
         			var file=value.digiSignedDoc==null?null:value.digiSignedDoc.fileName;
         			$("#downloadC1Doc").removeAttr('disabled');
         			$("#downloadC1Doc").attr('href',url);
         			$("#downloadC1Doc").prop('href', url+'/'+annexureBid);
         			$("#downloadC1Doc").html(file);
         		}
         		var priceBid=value1.digiSignedDoc==null?null:value1.digiSignedDoc.attachmentId;
         		if(priceBid!=null){
         			var file=value.digiSignedDoc==null?null:value.digiSignedDoc.fileName;
         			$("#downloadPBDoc").removeAttr('disabled');
         			$("#downloadPBDoc").attr('href',url);
    				$("#downloadPBDoc").prop('href', url+'/'+priceBid);
    				$("#downloadPBDoc").html(file);
         		}
        	}
    	}
	}else{
		$("#downloadPBDoc").html('Price Bid of Tender/Auction is not opened yet ');
		$("#downloadPBDoc").attr('disabled','disabled');
		$("#downloadC1Doc").html('Annexure C1 of Tender/Auction is not opened yet');
		$("#downloadC1Doc").attr('disabled','disabled');
	}
	setHeaderValues(documentType+" Title: "+ttitle, "Partner Name: "+bname, "Material: "+mname, "Required Qtys: "+mreqQty);
}

function loadPreliminaryScrutiny(ele){
	$('#leftPaneData li:not(.active)').remove();
	submitWithParam('getPreliminaryScrutinyFiles','bidderDetailForm #bidderId','loadScrutinyFileResp');
	setHeaderValues(documentType+" Title: "+ttitle, "Partner Name: "+bname, "Material: "+mname, "Required Qtys: "+mreqQty);
}

function loadFinalScrutiny(ele){
	$('#leftPaneData li:not(.active)').remove();
	submitWithParam('getFinalScrutinyFiles','bidderDetailForm #bidderId','loadScrutinyFileResp');
	setHeaderValues(documentType+" Title: "+ttitle, "Partner Name: "+bname, "Material: "+mname, "Required Qtys: "+mreqQty);
}

function loadScrutinyFileResp(data){
	console.log(data);
	var level=data.objectMap.scrutinyLevel.toLowerCase();
	$('#'+level+'_tScrutinyStatusDiv').html('');
	$('#'+level+'_cScrutinyStatusDiv').html('');
	var statusClass='';
	var htmlElement='';
	if(data.objectMap.hasOwnProperty('techScrutinyFile')){
		var count=1;
		if(!$.isEmptyObject(data.objectMap.techScrutinyFile)){
			$.each(data.objectMap.techScrutinyFile, function(key, value) {
				var materialName=value.itemBid==null?'':value.itemBid.tahdrMaterial==null?'':value.itemBid.tahdrMaterial.material
						==null?'':value.itemBid.tahdrMaterial.material.name;
			    var attachmentId=value.attachment==null?'':value.attachment.attachmentId;
			    var fileName=value.attachment==null?'':value.attachment.fileName;
				htmlElement=htmlElement+'<div class="form-group">'+
	             '<div>'+
	             '<label class="col-sm-6">'+count+'.'+materialName+':</label>'+
	                     '<a href="download/'+attachmentId+'" id="" >'+fileName+'</span></div> </div>';
				count++;
			   });
			$('#'+level+'_tScrutinyStatusDiv').append(htmlElement);
			htmlElement='';
		}else{
			$('#'+level+'_tScrutinyStatusDiv').append('Not Uploaded Yet');
		}
		htmlElement='';
    }
	if(data.objectMap.hasOwnProperty('commScrutinyFile')){
		 
		var value=data.objectMap.commScrutinyFile;
		if(value!=null){
			 var attachmentId=value.attachment==null?'':value.attachment.attachmentId;
			    var fileName=value.attachment==null?'':value.attachment.fileName;
			htmlElement=htmlElement+'<a href="download/'+attachmentId+'" id="" >'+fileName+'</span></div> </div>';
			$('#'+level+'_cScrutinyStatusDiv').append(htmlElement);
			htmlElement='';
			
		}else{
			$('#'+level+'_cScrutinyStatusDiv').append('Not Uploaded Yet');
		}
		 
		
    }
}
