
var bidTypeList;
var bidStatusList;
var bidderArray = new Array();
var itemBidArray= new Array();
var EMDPaymentArray= new Array();
var tahdrMaterialArray=new Array();
var lengthMenu;
var isVendor;
var isContractor;
var isCustomer;
var isInterState;
var isGstApplicable;

var documentType;
var paymentMode;
var paymentType;
var tenderType;

var selectedItemBid=new Array();
var selectedTender;

var tenderTypeCode='';

$(document).ready(function(){
	
	setCurrentTab($("#tahdrDetailsTab"));
	if ($(window).width() < 480) {
        $('.mobileNav').show();
        $.fn.DataTable.ext.pager.numbers_length = 4;       
        lengthMenu = [ 1, 5, 7, 10, ],
        [ 1, 5, 7, 10, ]
    }else{        
        lengthMenu = [ 5, 10, ],
        [ 5, 10, ]
    }
	
	$('#tahdrmaterial').DataTable({
		"ordering": false,
		"bSortable": false,
		"lengthMenu": lengthMenu
		/*"columnDefs": [
		      { "width": "300px", "targets": 0 },
		      { "width": "100px", "targets": 1 },
		      { "width": "100px", "targets": 2 },
		      { "width": "300px", "targets": 3 },
		      { "width": "200px", "targets": 4 },
		      { "width": "200px", "targets": 5 }
		    ]*/
	});
	mobiletable();
	$($.fn.dataTable.tables(true)).DataTable().columns.adjust();
	$("#submitItemBidTab").on('click',function(){
		cacheLi();
		setCurrentTab(this);
		$(this).attr("disabled","disabled");
		getBidConformation()
		$("#quotedStatusTable").DataTable().destroy();
		$("#quotedStatusTable tbody").empty();
		$.each(Object.values(tahdrMaterialArray),function(idx,tahdrMaterial){
			var count=0;
			var tr='';
			$.each(Object.values(itemBidArray),function(idx,itemBid){
				if(tahdrMaterial.material.materialId==itemBid.tahdrMaterial.material.materialId){
					var isQuoted;
					isQuoted=itemBid.status=='SBMT'?'Y':'N';
					tr=	"<tr>"+
					"<td class='col-sm-3'>"+ itemBid.tahdrMaterial.material.code +"</td>" +
					"<td class='col-sm-3'>"+ itemBid.tahdrMaterial.material.name +"</td>" +
					"<td class='col-sm-3'>"+ isQuoted +"</td>" +
					"</tr>";
					return false;
				}else{
					count++;
				}
			});
			if(count==Object.values(itemBidArray).length){
				tr=	"<tr>"+
				"<td class='col-sm-3'>"+ tahdrMaterial.material.code +"</td>" +
				"<td class='col-sm-3'>"+ tahdrMaterial.material.name +"</td>" +
				"<td class='col-sm-3'>N</td>" +
				"</tr>";
			}
			$("#quotedStatusTable tbody").append(tr);
		});
		checkCommercialBid();
		$("#quotedStatusTable").DataTable({
			"lengthMenu":lengthMenu
		});
		mobiletable();
		$(this).removeAttr("disabled");
	});
	
	
	
	$('#emdDetail').on('click',function(event){
		event.preventDefault();
		$(this).attr("disabled","disabled");
		var currentDiv=$(this).parents().find('div.k-state-active');
		var currentTab=$(this).parents().find('li.k-state-active');
		
		var newTab=$("#emdPaymentTab");
		var newDiv=$("#emdPaymentDiv");
		
		currentTab.removeClass('k-state-active');
		currentDiv.removeClass('k-state-active').css('display','none');
		
		newTab.addClass('k-state-active');
		newDiv.addClass('k-state-active').css('display','block').css('opacity','1');
		
		getEMDDetail();
		$(this).removeAttr("disabled");
	});
	
	$("#bidTab").on('click',function(){
		$(this).attr("disabled","disabled");	
		getBids();
		$(this).removeAttr("disabled");	
	});
	
	$('#fillBids').on('click',function(event){
		event.preventDefault();
		$(this).attr("disabled","disabled");
		var currentDiv=$(this).parents().find('div.k-state-active');
		var currentTab=$(this).parents().find('li.k-state-active');
		var newTab=$("#bidTab");
		var newDiv=$("#bidDiv");
		
		currentTab.removeClass('k-state-active');
		currentDiv.removeClass('k-state-active').css('display','none');
		
		newTab.addClass('k-state-active');
		newDiv.addClass('k-state-active').css('display','block').css('opacity','1');
		
		getBids();
		$(this).removeAttr("disabled");
	});
	
	
	$("#tahdrDetailsTab").on("click",function(){
		cacheLi();
		setCurrentTab(this);
		$(this).attr("disabled","disabled");
		getTahdrDetails();
		$(this).removeAttr("disabled");
	});
	
	$("#bidsTab").on("click",function(){
		$(this).attr("disabled","disabled");
		getBids();
		$(this).removeAttr("disabled");
	});
	
	$('.backToTenders').on('click',function(event){
		event.preventDefault();
		$(this).attr("disabled","disabled");
		var currentDiv=$(this).parents().find('div.k-state-active').parents().find('div.k-state-active');
		var currentTab=$(this).parents().find('li.k-state-active').parents().find('li.k-state-active');
		
		var newTab=$("#tahdrDetailsTab");
		var newDiv=$("#tahdrDetailsDiv");
		
		currentTab.removeClass('k-state-active');
		currentDiv.removeClass('k-state-active').css('display','none');
		
		newTab.addClass('k-state-active');
		newDiv.addClass('k-state-active').css('display','block').css('opacity','1');
		
		
		loadTenderToLeftPane(bidderArray);
		$(this).removeAttr("disabled");
	});
	
	
	$("input[name='toggleTenderType']").on("change",function(){
		var resp=fetchList("getPurchasedTenderByTypeCode",$(this).val());
		loadTenderToLeftPane(resp.objectMap.bidderList);
		
		if($(this).val()=="PT"){
			var FileSizeforPt=$("#fileSizeForPT").val();
			$("#fileSize").val(FileSizeforPt);
			$("#emdExemptionTable").show();
			loadFieldsForProcurement();		
		}else if($(this).val()=="WT"){
			var FileSizeforWt=$("#fileSizeForWT").val();
			$("#fileSize").val(FileSizeforWt);
			$("#emdExemptionTable").DataTable().destroy();
			$("#emdExemptionTable").hide();
			loadFieldsForWork();
		}
		$("#tabstrip").kendoTabStrip();		
		setCurrentTab($("#tahdrDetailsTab"));
		setChildLoadFlag(true);
		/*setCurrentTab(true);*/
	});
	
	$(".tahdrEMDPayment").on("click",function(event){
		event.preventDefault();
		$(this).attr("disabled","disabled");
		submitIt("emdPaymentDetailForm","saveEmdPaymentDetail","paymentDetailResp")
		$(this).removeAttr("disabled");
	});
	
	$("#emdPaymentTab").on("click",function(){
		$(this).attr("disabled","disabled");
		getEMDDetail();
		$(this).removeAttr("disabled");
	});
	
	$("#payReceiptLinkId").on("click",function(event){
		var id=$("#tahdrId").val();
		var paymentType=$("#paymentType").find('option:selected').html();
		if(paymentType=='EMD'){
			paymentType='EMD'
		}
		var anchorHtml="invoice?id="+id+"&paymentType="+paymentType;
		$("#payReceiptLinkId").attr('href',anchorHtml);
	});
	
	$("#submitItemBid").on("click",function(event){
		event.preventDefault();
		$(this).attr("disabled","disabled");
		submitIt("submitItemBidForm","submitFinalBid","itemBidSubmitResp");
		$(this).removeAttr("disabled");
	});
	
	var dataUrl=$('#myTenderUrl').val();
	if(dataUrl==null || dataUrl=='' || dataUrl ==undefined ){
		/*getTahdrDetails();*/
		loadTenders();
	}else{
		$('#worksToggle').removeClass('active');
		$('#worksCheckBoxId').removeAttr('checked');
		submitToURL(dataUrl, "setPageElements");
		setToggle(tenderTypeCode);
	}
	mobiletable();
});

function setToggle(tenderTypeCode){
	if(tenderTypeCode=='PT'){
		$('#labelProcurement').addClass('active');
		$('#labelWorks').removeClass('active');
		
	}else{
		$('#labelWorks').addClass('active');
		$('#labelProcurement').removeClass('active');
	}
}

function getEMDDetail(){
	cacheLi();
	setCurrentTab($("#emdPaymentTab"));
	/*if(getChangedFlag()){*/
		$('#emdPaymentDetailForm').find('input,select,textarea').removeClass('errorinput');
		var tahdrId=$("#tahdrId").val();
		var resp=fetchList("getEmdPaymentDetail",tahdrId);
		var tahdr=resp.objectMap.paymentDetail==null?'':resp.objectMap.paymentDetail.tahdr==null?'':resp.objectMap.paymentDetail.tahdr.tahdrTypeCode==null?'':resp.objectMap.paymentDetail.tahdr.tahdrTypeCode;
		var isTrader =resp.objectMap.paymentDetail==null?'':resp.objectMap.paymentDetail.isTrader==null?'':resp.objectMap.paymentDetail.isTrader;
		var paymentDetail = resp.objectMap.paymentDetail;
		var companyType = resp.objectMap.companyType;
		var company_type_govt= resp.objectMap.company_type_govt;
		var paymentModes= resp.objectMap.paymentMode;
		var payment=resp.objectMap.payment;
		var prodList=resp.objectMap.prodList;
		var list=[];
	
		if(tahdr=='PT' && isTrader=='N'){
			if(!$.isEmptyObject(paymentModes)){
				loadReferenceList("paymentMode",paymentModes);
		}
		}
		else if(tahdr=='PT' && isTrader=='Y'){
			loadPaymentModeList("paymentMode",paymentModes);
		}
		
		
		if($.isEmptyObject(payment)){
			populatePaymentDetailToLeftPane(list);
		}else{
			list.push(payment);
			populatePaymentDetailToLeftPane(list);
		}
		if(!$.isEmptyObject(prodList)){
			populateExemptionList(prodList,companyType,company_type_govt);
		}
		
		setChangedFlag(false);
	/*}else{
		getCacheLi();
		$('.tahdrEMDPayment').attr('style','display : none;');
	}*/
}

function loadInitData(response){
	bidTypeList=response.objectMap.bidTypeList;	
	bidStatusList=response.objectMap.bidStatusList;	
	tenderType=response.objectMap.tenderType;
	if(tenderType=='PT' || tenderType=='FA'){
    	loadReferenceList("paymentMode",response.objectMap.paymentMode);
    	}
    	else if(tenderType=='WT'){
    		loadPaymentModeList("paymentMode",response.objectMap.paymentMode);
    	}
	/*paymentMode=response.objectMap.paymentMode;
	loadReferenceList("paymentMode",paymentMode);*/
	paymentType=response.objectMap.paymentType;
	populatePaymentType(paymentType);
	isVendor=response.objectMap.isVendor;
	isContractor=response.objectMap.isContractor;
	isCustomer=response.objectMap.isCustomer;
	isGstApplicable=response.objectMap.isGstApplicable;
	isIntraState=response.objectMap.isIntraState;
	
}

function loadFieldsForWork(){
	$("#bidderGtpSubTab").hide();
	$("#bidderGtpSubTab").removeClass('active');
	var bidderGtpDiv=$("#bidderGtpSubTab").children('a').attr('href');
	$(bidderGtpDiv).removeClass('active');	
	
	$("#sectionDocumentTab").addClass('active');
	var sectionDocumentDiv=$("#sectionDocumentTab").children('a').attr('href');
	$(sectionDocumentDiv).addClass('active');
	
	$("#partsTab").hide();
	$("#partsTab").removeClass('active');
	var partsDiv=$("#partsTab").children('a').attr('href');
	$(partsDiv).removeClass('active');
	
	$("#priceSubTab").hide();
	$("#priceSubTab").removeClass('active');
	var priceSubDiv=$("#priceSubTab").children('a').attr('href');
	$(priceSubDiv).removeClass('active');	
	
	$("#sectionDocumentSubTab").addClass('active');
	var sectionDocumentSubDiv=$("#sectionDocumentSubTab").children('a').attr('href');
	$(sectionDocumentSubDiv).addClass('active');
	
	$("#deliveryDetail").hide();
	$("#deliveryDetail").removeClass('active');
	var deliveryDetailDiv=$("#deliveryDetail").children('a').attr('href');
	$(deliveryDetailDiv).removeClass('active');

	$("#commercialDocumentTab").addClass('active');
	var commercialDocumentDiv=$("#commercialDocumentTab").children('a').attr('href');
	$(commercialDocumentDiv).addClass('active');
	
	$("#tbReportUrl").val("worksTechnicalBidReport/");
	
}

function loadFieldsForProcurement(){
	$("#bidderGtpSubTab").show();
	$("#bidderGtpSubTab").addClass('active');
	var bidderGtpDiv=$("#bidderGtpSubTab").children('a').attr('href');
	$(bidderGtpDiv).addClass('active');
	
	$("#sectionDocumentTab").removeClass('active');
	var sectionDocumentDiv=$("#sectionDocumentTab").children('a').attr('href');
	$(sectionDocumentDiv).removeClass('active');
	
	$("#deliveryDetail").show();
	$("#deliveryDetail").addClass('active');
	var deliveryDetailDiv=$("#deliveryDetail").children('a').attr('href');
	$(deliveryDetailDiv).addClass('active');
	
	$("#commercialDocumentTab").removeClass('active');
	var commercialDocumentDiv=$("#commercialDocumentTab").children('a').attr('href');
	$(commercialDocumentDiv).removeClass('active');

	$("#partsTab").show();
	
	$("#priceSubTab").show();
	$("#priceSubTab").addClass('active');
	var priceSubDiv=$("#priceSubTab").children('a').attr('href');
	$(priceSubDiv).addClass('active');	
	
	$("#sectionDocumentSubTab").removeClass('active');
	var sectionDocumentSubDiv=$("#sectionDocumentSubTab").children('a').attr('href');
	$(sectionDocumentSubDiv).removeClass('active');
	
	$("#tbReportUrl").val("technicalBidReport/");
	
}

function loadFieldsForForwardAuction(){
	$("#bidderGtpSubTab").show();
	$("#bidderGtpSubTab").addClass('active');
	var bidderGtpDiv=$("#bidderGtpSubTab").children('a').attr('href');
	$(bidderGtpDiv).addClass('active');
	
	$("#sectionDocumentTab").removeClass('active');
	var sectionDocumentDiv=$("#sectionDocumentTab").children('a').attr('href');
	$(sectionDocumentDiv).removeClass('active');
	
	$("#deliveryDetail").hide();
	$("#deliveryDetail").removeClass('active');
	var deliveryDetailDiv=$("#deliveryDetail").children('a').attr('href');
	$(deliveryDetailDiv).removeClass('active');

	$("#commercialDocumentTab").addClass('active');
	var commercialDocumentDiv=$("#commercialDocumentTab").children('a').attr('href');
	$(commercialDocumentDiv).addClass('active');

	$("#partsTab").show();
	
	$("#priceSubTab").show();
	$("#priceSubTab").addClass('active');
	var priceSubDiv=$("#priceSubTab").children('a').attr('href');
	$(priceSubDiv).addClass('active');	
	
	$("#sectionDocumentSubTab").removeClass('active');
	var sectionDocumentSubDiv=$("#sectionDocumentSubTab").children('a').attr('href');
	$(sectionDocumentSubDiv).removeClass('active');
	
	$("#tbReportUrl").val("technicalBidReport/");
	
}


function loadTenderToLeftPane(bidderList){
	$('.pagination').children().remove();
	$("#leftPane").html("");
	var leftPanelHtml='';
	var i=0;
	var active="";
	var firstRow=null;

	bidderArray=[];
	if(isEmpty(bidderList)){
		Alert.warn("No Records To Display");
		$('#tahdrDoc').attr('style','display : none;');
		$('.tahdrTab').addClass('readonly');
		$("#tahdrmaterial").DataTable().destroy();
		$('#tahdrmaterial tbody').empty();
		$("#generatedDoc").append("");
		setHeaderValues("Tender Code: ","Bidder Status: ","Est. Cost In Lakhs: ","EMD Fee in INR: ");
		return;
	}else{
		$('#tahdrDoc').removeAttr('style');
		$('.tahdrTab').removeClass('readonly');
		$("#tahdrmaterial").DataTable().destroy();
		$('#tahdrmaterial tbody').empty();
		$("#generatedDoc").append("");
		
		$.each(Object.values(bidderList),function(key,bidder){
				bidderArray["bidder"+bidder.bidderId]=bidder;
				if(i==0){
					firstRow = bidder;
					active="active";
				}
				leftPanelHtml= leftPanelHtml +appendTenderData(bidder,active);
				active="";
				i++;
		});
		setLeftPaneHeader("Tender List", Object.values(bidderArray).length);	
		$("#leftPane").append(leftPanelHtml);
		loadTenderDetailsRightPane(firstRow);
	}
	/*$(".tahdr").on('click',function(){
		
	});	*/
	$('#leftPane').paginathing({perPage: 6});
	
}

function onClickTahdr(ele){
	var bidderId=$(ele).attr('bidderId');
	loadTenderDetailsRightPane(bidderArray["bidder"+bidderId]);
}

function loadMaterialToLeftPane(itemBidList){
	$('.pagination').children().remove();
	$("#leftPane").html("");	
	var leftPanelHtml='';
	var i=0;
	var active="";
	var firstRow=null;
	
	itemBidArray=[];
	$.each(Object.values(itemBidList),function(key,itemBid){
		
		itemBidArray["tahdrMaterial"+itemBid.tahdrMaterial.tahdrMaterialId]=itemBid;
		if(i==0){
			firstRow = itemBid;
			active="active";
		}
		leftPanelHtml= leftPanelHtml +appendTenderMaterialData(itemBid,active);
		active="";
		i++;
	});
	setLeftPaneHeader("Material List", Object.values(itemBidArray).length);
	
	$("#leftPane").append(leftPanelHtml);
	loadMaterialBidsToRightPane(firstRow);
	/*$(".materialLi").on('click',function(){
		
	});*/
	$('#leftPane').paginathing({perPage: 6});
	
	
}

function onClickItemBid(ele){
	var materialId=$(ele).attr('materialId');
	loadMaterialBidsToRightPane(itemBidArray["tahdrMaterial"+materialId]);
}

function appendTenderData(value,active){
	var li=	'<li tahdrDetailId="'+value.tahdr.tahdrDetail[0].tahdrDetailId+'" bidderId="'+value.bidderId+'" class="tahdr '+active+'" onclick="onClickTahdr(this);" >'+
			   	'<a data-toggle="tab">'+
				   	'<div class="col-md-12">'+
			        '<label class="col-xs-6">'+value.tahdr.tahdrCode+'</label>'+
			            '<label class="col-xs-6">'+value.tahdr.title+'</label>'+
			        '</div>'+
			        '<div class="col-md-12">'+
			            '<label class="col-xs-6">'+bidTypeList[value.tahdr.bidTypeCode]+'</label>'+
			            '<label class="col-xs-6">'+formatDate(value.tahdr.tahdrDetail[0].priceBidToDate)+'</label>'+
			        '</div>'+
			    '</a>'+
		    '</li>';
	return li;
	
	/*var liArray=[];
	liArray.push(value.tenderDetail.tahdr.tahdrCode);
	liArray.push(value.tenderDetail.tahdr.title);
	liArray.push(bidTypeList[value.tenderDetail.tahdr.bidTypeCode]);
	liArray.push(formatDate(value.tenderDetail.priceBidToDate));
	return appendArrayToLiData(liArray,value.tenderDetail.tahdrDetailId,active,'tahdr');*/
}

function appendTenderMaterialData(value,active){
	var li=	'<li materialId="'+value.tahdrMaterial.tahdrMaterialId+'" itemBidId="'+value.itemBidId+'" class="materialLi '+active+'" onclick="onClickItemBid(this);" >'+
			   	'<a data-toggle="tab">'+
			   		'<div class="col-md-12">'+
				   		'<label class="col-xs-6">'+value.tahdrMaterial.material.name+'</label>'+
				   		'<label class="col-xs-6">'+value.tahdrMaterial.materialDescription+'</label>'+
			        '</div>'+
			        '<div class="col-md-12">'+
			            '<label class="col-xs-6">'+value.tahdrMaterial.quantity+'</label>'+
			            '<label class="col-xs-6">'+value.tahdrMaterial.material.uom.name+'</label>'+
			        '</div>'+
			    '</a>'+
		    '</li>';
	return li;
}

function getBids(){

	var currentActiveDiv=$("#bidDiv").find('div.k-state-active');
	var currentActiveTab=$("#bidDiv").find('li.k-state-active');
	var newSubTab=$("#itemDetailsTab");
	var newSubDiv=$("#itemDetailsDiv");
	
	cacheLi();
	setCurrentTab(newSubTab);
	if(getChangedFlag()){		
		currentActiveTab.removeClass('k-state-active');
		currentActiveDiv.removeClass('k-state-active').css('display','none');
		
		newSubTab.addClass('k-state-active');
		newSubDiv.addClass('k-state-active').css('display','block').css('opacity','1');
		
		var tahdrDetailId=$(".tahdrDetailId").val();
		var tahdrBids=fetchList("getBidsByBidderQuery",tahdrDetailId);
		var bidderId=tahdrBids.bidderId==null?'':tahdrBids.bidderId;
		
		loadMaterialToLeftPane(tahdrBids.itemBidList);
		/*if(!$.isEmptyObject(tahdrBids.commercialBid)){
				populateCommercialBid(tahdrBids.commercialBid);
		}*/
		setChangedFlag(false);
	}else{
		getCacheLi();
	}
	
}

function getBidConformation(){
	var tahdrDetailId=$(".tahdrDetailId").val();
	var tahdrBids=fetchList("getBidsByBidderQuery",tahdrDetailId);
	var bidderId=tahdrBids.bidderId==null?'':tahdrBids.bidderId;
	loadItemBidArray(tahdrBids.itemBidList);
	/*loadMaterialToLeftPane(tahdrBids.itemBidList);*/
	var commercialBid=getCommercialBid();
	if(!$.isEmptyObject(commercialBid)){
			populateCommercialBid(commercialBid);
	}
}

function loadItemBidArray(itemBidList){
	itemBidArray=[];
	$.each(Object.values(itemBidList),function(key,itemBid){
		itemBidArray["tahdrMaterial"+itemBid.tahdrMaterial.tahdrMaterialId]=itemBid;
	});
}

function loadItemBidArray(itemBidList){
	itemBidArray=[];
	$.each(Object.values(itemBidList),function(key,itemBid){
		itemBidArray["tahdrMaterial"+itemBid.tahdrMaterial.tahdrMaterialId]=itemBid;
	});
}

function loadTenderDetailsRightPane(bidder){
	selectedTender=bidder;
	if(!$.isEmptyObject(bidder)){
		var tahdrCode=	bidder.tahdr.tahdrCode==null?'':bidder.tahdr.tahdrCode;
		var tahdrTypeCode=	bidder.tahdr.tahdrTypeCode==null?'':bidder.tahdr.tahdrTypeCode;
		var title= bidder.tahdr.title==null?'':bidder.tahdr.title;
		var estimatedCost= bidder.tahdr.tahdrDetail[0].estimatedCost==null?'':bidder.tahdr.tahdrDetail[0].estimatedCost;
		var emdFee= bidder.tahdr.tahdrDetail[0].emdFee==null?'':bidder.tahdr.tahdrDetail[0].emdFee;
		var tahdrDetailId= bidder.tahdr.tahdrDetail[0].tahdrDetailId==null?'':bidder.tahdr.tahdrDetail[0].tahdrDetailId;
		var status= bidder.tahdr.tahdrStatusCode==null?'':bidder.tahdr.tahdrStatusCode;
		tenderTypeCode=tahdrTypeCode;
		$(".bidderId").val(bidder.bidderId);
		$('#tenderCodeHdr').html(tahdrCode);
		$('#tenderTitleHdr').html(title);
		$('#estimatedCostHdr').html(estimatedCost);
		$('#emdFeeHdr').html(emdFee);
		$(".tahdrDetailId").val(tahdrDetailId);
		
		$("#amount").val(bidder.tahdr.tahdrDetail[0].emdFee);
		$("#gst").val((bidder.tahdr.tahdrDetail[0].emdFee*$("#gstRate").text())/100);
		$("#totalFee").val(Number($("#amount").val())+Number($("#gst").val()));
		$("#tahdrCode").val(bidder.tahdr.tahdrCode);
		$("#tahdrDetailId").val(bidder.tahdr.tahdrDetail[0].tahdrDetailId);
		$("#tahdrId").val(bidder.tahdr.tahdrId);
		$(".tahdrId").val(bidder.tahdr.tahdrId);
		
		$('#tahdrmaterial').DataTable().destroy();
		$('#tahdrmaterial tbody').empty();
		$("#generatedDoc").empty();
		tahdrMaterialArray=[];
		$.each(bidder.tahdr.tahdrDetail[0].tahdrMaterial,function(idx,obj){
			
			tahdrMaterialArray['tahdrMaterial'+obj.tahdrMaterialId]=obj;
			
			var tahdrMaterialId=obj.tahdrMaterialId==null?'':obj.tahdrMaterialId;
			var code=obj.material.code==null?'':obj.material.code;
			var uomCode=obj.material.uom.code==null?'':obj.material.uom.code;
			var materialDescription=obj.materialDescription==null?'':obj.materialDescription;
			var quantity=obj.quantity==null?'':obj.quantity;
			var specVersion='';
			if(!$.isEmptyObject(obj.materialVersion)){
				specVersion=obj.materialVersion.name==null?'':obj.materialVersion.name;
			}
			var materialTypeCode=obj.materialTypeCode==null?'':obj.materialTypeCode;
			
			var tr=	"<tr>"+
					"<td class='col-sm-3'>"+ code +"</td>" +
					"<td class='col-sm-1'>"+ uomCode +"</td>" +
					"<td class='col-sm-1'>"+ quantity +"</td>" +
					"<td class='col-sm-3'>"+ materialDescription +"</td>" +
					"<td class='col-sm-2'>"+ specVersion +"</td>" +
					"<td class='col-sm-2'>"+ materialTypeCode +"</td>" +
					"</tr>";
			$("#tahdrmaterial tbody").append(tr);
		});
		$('#tahdrmaterial').DataTable({
			"lengthMenu": lengthMenu
		});
		mobiletable();
		var code=bidder.tahdr.tahdrCode;
		var bStatus=bidder==null?'':bidder.status;
		var bidderStatus=bStatus==''?'':bidStatusList[bStatus];
		setHeaderValues("Tender Code: "+code,"Bidder Status: "+bidderStatus,"Est. Cost In Lakhs: "+estimatedCost,"EMD Fee in INR: "+emdFee);

		if($("input[name='toggleTenderType']:checked").val()=='PT'){
			var FileSizeforPt=$("#fileSizeForPT").val();
			$("#fileSize").val(FileSizeforPt);
		}
		else if($("input[name='toggleTenderType']:checked").val()=='WT'){
			var FileSizeforWt=$("#fileSizeForWT").val();
			$("#fileSize").val(FileSizeforWt);
		}
		if(status=='RBSCH'){
			$('.rbschClass').hide();
			$('.rbschVariable').val('Y');
		}else{
			$('.rbschClass').show();
			$('.rbschVariable').val('');
		}
	}
	setChildLoadFlag(true);
}

function loadMaterialBidsToRightPane(itemBid){
	selectedItemBid=[];
	selectedItemBid.push(itemBid);
	var itemBidId=itemBid.itemBidId==null?'':itemBid.itemBidId;
	var tahdrMaterialId=itemBid.tahdrMaterial.tahdrMaterialId==null?'':itemBid.tahdrMaterial.tahdrMaterialId;
	$(".itemBidId").val(itemBidId);
	$(".tahdrMaterialId").val(tahdrMaterialId);
	$(".materialDescription").text(itemBid.tahdrMaterial.materialDescription);
	$(".materialName").text(itemBid.tahdrMaterial.material.name);
	$(".materialCode").text(itemBid.tahdrMaterial.material.code);
	$(".materialUnit").text(itemBid.tahdrMaterial.material.uom.name);
	if(!$.isEmptyObject(itemBid.tahdrMaterial.material.hsnCode)){
		$(".hsnCodeField").text(itemBid.tahdrMaterial.material.hsnCode.code);
	}
	partsTab(itemBid.tahdrMaterial.materialTypeCode);
	$(".referedQuantity").text(itemBid.tahdrMaterial.quantity);
	var itemBidStatus;
	if(isEmpty(itemBid.status)){
		itemBidStatus='Bid Not Initiated'
	}else{
		itemBidStatus=bidStatusList[itemBid.status];
	}
	$(".itemBididStatus").text(itemBidStatus);
	try{
		document.getElementById("saveTechnicalBidForm").reset();
		document.getElementById("savePriceBidForm").reset();
	}catch(e){
		
	}
	setChildLoadFlag(true);
}

function populatePaymentType(paymentType){
	if(!isEmpty(paymentType)){
		$("#gstRate").text(paymentType.gst);
		$("#paymentType").empty();
		$("#paymentType").append('<option selected="selected" value="'+paymentType.paymentTypeId+'">'+paymentType.name+'</option>');
	}
}

function formFieldChange(el){	 
	var paymentMode=$(el).val();
	if($.isEmptyObject(paymentMode)){
		$('#paymentDate').hide();
		$('#validityDate').hide();
		$('#paymentNo').hide();
		$('#paymentRefNo').hide();
		$('#bankNamelbl').hide();
		$('#branchNamelbl').hide();
		$('#micrCode').hide();
		$('#bankName').hide();
		$('#branchName').hide();
		$('#paymentDateDiv').hide();
		$('#validityDateDiv').hide();
		$('#referenceNoDiv').hide();
		$('#bankGuaranteeAttachment').hide();
		
		$('#dateOfPayment').attr('disabled','disabled');
		$('#dateOfValidity').attr('disabled','disabled');
		$('#referenceNo').attr('disabled','disabled');
		$('#micrCode').attr('disabled','disabled');
		$('#bankName').attr('disabled','disabled');
		$('#branchName').attr('disabled','disabled');
		$('#BGId').attr('disabled','disabled');
		$('#BGAttachment').attr('disabled','disabled');
		$(".positionABS").hide();
		$("#onlineTenderFeeForm").hide();
	}else if(paymentMode=="DD"){
		$('#paymentDetailId').val('');
		$('#dateOfPayment').val(formatDate(""));
		$('#referenceNo').val("");
		$('#micrCode').val("");
		$('#bankName').val("");
		$('#branchName').val("");
		$('#dateOfValidity').val(formatDate(""));
		$("#BGAttachment").val("");
		$("#a_bgAttachment").val('');
		$("#a_bgAttachment").html('');
		$("#a_bgAttachment").removeAttr('href');
		
		$('#paymentDate').show();
		$('#paymentDateDiv').show();
		$('#validityDate').hide();
		$('#validityDateDiv').hide();
		$('#paymentNo').show();
		$('#paymentRefNo').show();
		$('#referenceNoDiv').show();
		$('#bankNamelbl').show();
		$('#branchNamelbl').show();
		$('#micrCode').show();
		$('#bankName').show();
		$('#branchName').show();
		
		$("#micrCodeDivId").show();
		$("#bankNameDivId").show();
		$("#branchNameDivId").show();
		
		$('#bankGuaranteeAttachment').hide();
		
		$('#referenceNo').addClass('onlyNumber');
		$('#referenceNo').attr('maxlength','9');
		
		$('#dateOfPayment').removeAttr('disabled','disabled');
		$('#referenceNo').removeAttr('disabled','disabled');
		$('#micrCode').removeAttr('disabled','disabled');
		$('#bankName').removeAttr('disabled','disabled');
		$('#branchName').removeAttr('disabled','disabled');
		$('#dateOfValidity').attr('disabled','disabled');
		$('#BGId').attr('disabled','disabled');
		$('#BGAttachment').attr('disabled','disabled');
		$("#BGAttachment").removeAttr('name');
		$('#paymentDate').text('DD Date');
		$('#paymentNo').text('Demand Draft No');
		$('#paymentRefNo').text('MICR NO');
		$('#bankNamelbl').text('Bank Name');
		$('#branchNamelbl').text('Branch Name');
		
		$('#paymentDate').append('<span class="red">*</span>');
		$('#paymentNo').append('<span class="red">*</span>');
		$('#paymentRefNo').append('<span class="red">*</span>');
		$('#bankNamelbl').append('<span class="red">*</span>');
		$('#branchNamelbl').append('<span class="red">*</span>');
		
		$(".positionABS").show();
		$("#onlineTenderFeeForm").hide();
	}else if(paymentMode=="CH"){
		$('#paymentDetailId').val('');
		$('#dateOfPayment').val(formatDate(""));
		$('#referenceNo').val("");
		$('#micrCode').val("");
		$('#bankName').val("");
		$('#branchName').val("");
		$('#dateOfValidity').val(formatDate(""));
		$("#BGAttachment").val('');
		$("#a_bgAttachment").val('');
		$("#a_bgAttachment").html('');
		$("#a_bgAttachment").removeAttr('href');

		$('#paymentDate').show();
		$('#paymentDateDiv').show();
		$('#validityDate').hide();
		$('#validityDateDiv').hide();
		$('#paymentNo').show();
		$('#paymentRefNo').show();
		$('#referenceNoDiv').show();
		$('#bankNamelbl').show();
		$('#branchNamelbl').show();
		$('#micrCode').show();
		$('#bankName').show();
		$('#branchName').show();
		
		$("#micrCodeDivId").show();
		$("#bankNameDivId").show();
		$("#branchNameDivId").show();
		$('#bankGuaranteeAttachment').hide();
		
		$('#referenceNo').addClass('onlyNumber');
		$('#referenceNo').attr('maxlength','9');
		
		$('#dateOfPayment').removeAttr('disabled','disabled');
		$('#referenceNo').removeAttr('disabled','disabled');
		$('#micrCode').removeAttr('disabled','disabled');
		$('#bankName').removeAttr('disabled','disabled');
		$('#branchName').removeAttr('disabled','disabled');
		$('#dateOfValidity').attr('disabled','disabled');
		$('#BGId').attr('disabled','disabled');
		$('#BGAttachment').attr('disabled','disabled');
		$('#paymentDate').text('Cheque Payment Date');
		$('#paymentNo').text('Cheque No');
		$('#paymentRefNo').text('MICR NO');
		$('#bankNamelbl').text('Bank Name');
		$('#branchNamelbl').text('Branch Name');
		
		$('#paymentDate').append('<span class="red">*</span>');
		$('#paymentNo').append('<span class="red">*</span>');
		$('#paymentRefNo').append('<span class="red">*</span>');
		$('#bankNamelbl').append('<span class="red">*</span>');
		$('#branchNamelbl').append('<span class="red">*</span>');
		
		$(".positionABS").show();
		$("#onlineTenderFeeForm").hide();
	}else if(paymentMode=="CA"){	
		$('#paymentDetailId').val('');
		$('#dateOfPayment').val(formatDate(""));
		$('#referenceNo').val("");
		$('#micrCode').val("");
		$('#bankName').val("");
		$('#branchName').val("");
		$('#dateOfValidity').val(formatDate(""));
		$("#BGAttachment").val('');
		$("#BGAttachment").val("");
		$("#a_bgAttachment").removeAttr('href');

		$('#paymentDate').show();
		$('#paymentDateDiv').show();
		$('#validityDate').hide();
		$('#validityDateDiv').hide();
		$('#paymentNo').show();
		$('#paymentRefNo').show();
		$('#referenceNoDiv').show();
		$('#bankNamelbl').show();
		$('#branchNamelbl').show();

		$('#micrCode').hide();
		$('#bankName').hide();
		$('#branchName').hide();
		$('#bankGuaranteeAttachment').hide();
		
		$('#paymentDate').text('Payment Date');
		$('#paymentNo').text('Reciept No');
		$('#paymentRefNo').text('');
		$('#bankNamelbl').text('');
		$('#branchNamelbl').text('');
		
		$('#referenceNo').addClass('onlyNumber');
		$('#referenceNo').attr('maxlength','9');
		
		$('#dateOfPayment').removeAttr('disabled','disabled');
		$('#referenceNo').removeAttr('disabled','disabled');
		$('#micrCode').attr('disabled','disabled');
		$('#bankName').attr('disabled','disabled');
		$('#branchName').attr('disabled','disabled');
		$('#BGId').attr('disabled','disabled');
		$('#BGAttachment').attr('disabled','disabled');
		$('#dateOfValidity').attr('disabled','disabled');
		
		$('#paymentDate').append('<span class="red">*</span>');
		$('#paymentNo').append('<span class="red">*</span>');
		$('#paymentRefNo span').remove();
		$('#bankNamelbl span').remove();
		$('#branchNamelbl span').remove();
		
		
		$(".positionABS").show();
		$("#onlineTenderFeeForm").hide();
	}else if(paymentMode=="ISEXEMP"){
	   $("#emdPaymentDetailForm #paymentDateDiv").hide();
	   $('#emdPaymentDetailForm #validityDate').hide();
	   $('#emdPaymentDetailForm #validityDateDiv').hide();
	   $("#emdPaymentDetailForm #referenceNoDiv").hide();
	   $("#emdPaymentDetailForm #micrCodeDivId").hide();
	   $("#emdPaymentDetailForm #bankNameDivId").hide();
	   $("#emdPaymentDetailForm #branchNameDivId").hide();
	   $('#bankGuaranteeAttachment').hide();
	   
	   $('#dateOfPayment').attr('disabled','disabled');
	   $('#dateOfValidity').attr('disabled','disabled');
		$('#referenceNo').attr('disabled','disabled');
		$('#micrCode').attr('disabled','disabled');
		$('#bankName').attr('disabled','disabled');
		$('#branchName').attr('disabled','disabled');
		$('#BGId').attr('disabled','disabled');
		$('#BGAttachment').attr('disabled','disabled');
		$("#BGAttachment").removeAttr('name');
		
	   $(".positionABS").show();
		$("#onlineTenderFeeForm").hide();
		
	}else if(paymentMode=="OP"){
		$('#paymentDate').hide();
		$('#paymentDateDiv').hide();
		$('#validityDate').hide();
		$('#validityDateDiv').hide();
		$('#paymentNo').show();
		$('#paymentRefNo').hide();
		$('#referenceNoDiv').hide();
		$('#bankNamelbl').hide();
		$('#branchNamelbl').hide();
		$('#bankGuaranteeAttachment').hide();
		
		$('#micrCode').hide();
		$('#bankName').hide();
		$('#branchName').hide();
		
		$('#paymentDate').text('Payment Date');
		$('#paymentNo').text('Reciept No');
		$('#paymentRefNo').text('');
		$('#bankNamelbl').text('');
		$('#branchNamelbl').text('');
		
		$('#paymentDate').append('<span class="red">*</span>');
		$('#paymentNo').append('<span class="red">*</span>');
		$('#paymentRefNo span').remove();
		$('#bankNamelbl span').remove();
		$('#branchNamelbl span').remove();
		$('#BGId').attr('disabled','disabled');
		$('#BGAttachment').attr('disabled','disabled');
		 $('#dateOfValidity').attr('disabled','disabled');
		 
		$(".positionABS").hide();
		$("#onlineTenderFeeForm").show();
	}else if(paymentMode=="BG"){
		$('#paymentDetailId').val('');
		$('#dateOfPayment').val(formatDate(""));
		$('#referenceNo').val("");
		$('#micrCode').val("");
		$('#bankName').val("");
		$('#branchName').val("");
		$('#dateOfValidity').val(formatDate(""));
		$("#BGAttachment").val('');
		$("#a_bgAttachment").val('');
		$("#a_bgAttachment").html('');
		$("#a_bgAttachment").removeAttr('href');

		$('#paymentDate').show();
		$('#paymentDateDiv').show();
		$('#validityDate').show();
		$('#validityDateDiv').show();
		$('#paymentNo').show();
		$('#paymentRefNo').hide();
		$('#referenceNoDiv').show();
		$('#bankNamelbl').show();
		$('#branchNamelbl').show();
		$('#micrCode').hide();
		$('#bankName').show();
		$('#branchName').show();
		
		$("#micrCodeDivId").hide();
		$("#bankNameDivId").show();
		$("#branchNameDivId").show();
		
		$('#bankGuaranteeAttachment').show();
		
		$('#dateOfPayment').removeAttr('disabled','disabled');
		$('#referenceNo').removeAttr('disabled','disabled');
		$('#micrCode').attr('disabled','disabled');
		$('#bankName').removeAttr('disabled','disabled');
		$('#branchName').removeAttr('disabled','disabled');
		$('#BGId').removeAttr('disabled');
		$('#BGAttachment').removeAttr('disabled');
		$('#dateOfValidity').removeAttr('disabled','disabled');
		$("#BGAttachment").attr('name','bankGuaranteeAttachment.attachmentId');
		$('#referenceNo').removeClass('onlyNumber');
		$('#referenceNo').removeAttr('maxlength');
		
		$('#paymentDate').text('Issue Date');
		$('#paymentNo').text('Bank Guarantee Number');
		$('#bankNamelbl').text('Bank Name');
		$('#branchNamelbl').text('Branch Name');
		
		$('#paymentDate').append('<span class="red">*</span>');
		$('#paymentNo').append('<span class="red">*</span>');
		$('#bankNamelbl').append('<span class="red">*</span>');
		$('#branchNamelbl').append('<span class="red">*</span>');
		
		$(".positionABS").show();
		$("#onlineTenderFeeForm").hide();
	}
}
function populatePaymentDetailToLeftPane(paymentDetail){
	debugger;
	$('.pagination').children().remove();
	$("#leftPane").html("");	
	var leftPanelHtml='';
	var i=0;
	var active="";
	var firstRow=null;
	
	EMDPaymentArray=[];
	$.each(Object.values(paymentDetail),function(key,payment){
		
		EMDPaymentArray["emd"+payment.paymentDetailId]=payment;
		if(i==0){
			firstRow = payment;
			active="active";
		}
		leftPanelHtml= leftPanelHtml +appendPaymentData(payment,active);
		active="";
		i++;
	});
	setLeftPaneHeader("EMD Payment", EMDPaymentArray.length);
	
	$("#leftPane").append(leftPanelHtml);
	populatePaymentDetail(firstRow);
	$("#leftPane").on('click','.payment',function(){
		
	});
	$('#leftPane').paginathing({perPage: 6});
	
}

function onClickEMDLi(ele){
	var paymentId=$(ele).attr('id');
	populatePaymentDetail(EMDPaymentArray["emd"+paymentId]);
}

function appendPaymentData(payment,active){
	var referenceNo= payment==null?'':payment.referenceNo==null?'':payment.referenceNo;
	return appendLiData(formatDate(payment.paymentDate), referenceNo, payment.paymentMode, payment.amount, payment.paymentDetailId, active, 'payment');
}

function populatePaymentDetail(paymentDetail){
	debugger;
	if($.isEmptyObject(paymentDetail)){
		$('#payOnlineLink').show();
		$('.tahdrEMDPayment').show();
		$('#payReceipt').hide();
		$('#payReceiptLinkId').hide();
		$('#paymentStatusDiv').css('display','none');
		$('#paymentStatusLabel').html('');
		
		$('#paymentDetailId').val('');
		$('#paymentMode').val('');
		$('#paymentMode').trigger('change');
		$('#dateOfPayment').val(formatDate(""));
		$('#referenceNo').val("");
		$('#micrCode').val("");
		$('#bankName').val("");
		$('#branchName').val("");
		$('#dateOfValidity').val(formatDate(""));
		$("#BGAttachment").val('');
		$("#a_bgAttachment").val('');
		$("#a_bgAttachment").html('');
		$("#a_bgAttachment").removeAttr('href');
		
		$('#paymentMode').removeClass("readonly");
		$('#paymentMode').removeClass("readonly");
		$('#dateOfPayment').removeClass("readonly");
		$('#paymentDateDiv').removeClass("readonly");
		$('#referenceNo').removeClass("readonly");
		$('#micrCode').removeClass("readonly");
		$('#bankName').removeClass("readonly");
		$('#branchName').removeClass("readonly");
		$('#dateOfValidity').removeClass("readonly");
		$('#validityDateDiv').removeClass("readonly");
		$("#BGId").removeClass("readonly");
		$('#BGAttachment').removeClass('readonly');
	}else{
		var faApproved=paymentDetail.isFAApproved==null?'':paymentDetail.isFAApproved;
		var foApproved=paymentDetail.isFOApproved==null?'':paymentDetail.isFOApproved;
		var paymentDetailId=paymentDetail.paymentDetailId==null?'':paymentDetail.paymentDetailId;
		var paymentMode = paymentDetail.paymentMode==null?'':paymentDetail.paymentMode;
		$("#paymentDetailId").val(paymentDetailId);
		if(faApproved=='Y'){
			/*$('#payReceipt').show();
			$('#payReceiptLinkId').show();*/
			if(paymentMode=='DD' || paymentMode=='BG' || (paymentMode=='OP' && paymentGatewayStatus=='Success')){
				$('#payReceipt').show();
				$('#payReceiptLinkId').show();
				}
				else{
					$('#payReceipt').hide();
					$('#payReceiptLinkId').hide();
				}
			$('#payOnlineLink').hide();
			$('#paymentDetailId').val(paymentDetail.paymentDetailId);
			$('#paymentStatusDiv').css('display','none');
			$('#paymentStatusLabel').html('');
			
			$("#amount").val(Number(paymentDetail.amount));
			$('#paymentMode').val(paymentDetail.paymentMode);
			$('#paymentMode').trigger('change');
			$('#dateOfPayment').val(formatDate(paymentDetail.paymentDate));
			$('#referenceNo').val(paymentDetail.referenceNo);
			$('#micrCode').val(paymentDetail.micrCode);
			$('#bankName').val(paymentDetail.bankName);
			$('#branchName').val(paymentDetail.branchName);
			$("#dateOfValidity").val(formatDate(paymentDetail.validityDate));
			if(paymentDetail.bankGuaranteeAttachment!=null){
				var bgId = paymentDetail==null?'':paymentDetail.bankGuaranteeAttachment == null ? '': paymentDetail.bankGuaranteeAttachment.attachmentId == null ? '': paymentDetail.bankGuaranteeAttachment.attachmentId;
				var bgName = paymentDetail==null?'':paymentDetail.bankGuaranteeAttachment == null ? '': paymentDetail.bankGuaranteeAttachment.fileName == null ? '': paymentDetail.bankGuaranteeAttachment.fileName;
				$("#BGAttachment").val(bgId);
				var url=$("#a_bgAttachment").data('url');
				$("#a_bgAttachment").attr('href',url);
				var a_bgAttachmentCopy = $("#a_officeNoteAttachment").prop('href')+'/'+bgId;
			    $("#a_bgAttachment").prop('href', a_bgAttachmentCopy);
				$("#a_bgAttachment").html(bgName);	
			}
			
			
			$('#paymentDate').addClass("readonly");
			$('#paymentMode').addClass("readonly");
			$('#dateOfPayment').addClass("readonly");
			$('#paymentDateDiv').addClass("readonly");
			$('#referenceNo').addClass("readonly");
			$('#micrCode').addClass("readonly");
			$('#bankName').addClass("readonly");
			$('#branchName').addClass("readonly");
			$("#dateOfValidity").addClass("readonly");
			$('#validityDateDiv').addClass("readonly");
			$("#BGId").addClass("readonly");
			$('#BGAttachment').addClass('readonly');
			$('.tahdrEMDPayment').hide();
			
		}
		else if(faApproved=='N' || foApproved=='N'){
			$('#payReceipt').hide();
			$('#payReceiptLinkId').hide();
			$('.tahdrEMDPayment').show();
			$('#paymentStatusDiv').css('display','block');
			$('#paymentStatusLabel').html('Your Payment is Rejected.');
			
			$('#paymentMode').val('');
			$('#paymentMode').trigger('change');
			$('#dateOfPayment').val(formatDate(""));
			$('#referenceNo').val("");
			$('#micrCode').val("");
			$('#bankName').val("");
			$('#branchName').val("");
			$('#dateOfValidity').val(formatDate(""));
			$("#BGAttachment").val('');
			$("#a_bgAttachment").val('');
			$("#a_bgAttachment").html('');
			$("#a_bgAttachment").removeAttr('href');
			$('#paymentMode').removeClass("readonly");
			$('#dateOfPayment').removeClass("readonly");
			$('#paymentDateDiv').removeClass("readonly");
			$('#referenceNo').removeClass("readonly");
			$('#micrCode').removeClass("readonly");
			$('#bankName').removeClass("readonly");
			$('#branchName').removeClass("readonly");
			$('#dateOfValidity').removeClass("readonly");
			$('#validityDateDiv').removeClass("readonly");
			$("#BGId").removeClass("readonly");
			$('#BGAttachment').removeClass('readonly');
		}
		else{
			$('#payOnlineLink').hide();
			$('#payReceipt').hide();
			$('#payReceiptLinkId').hide();
			$('.tahdrEMDPayment').hide();
			$('#paymentStatusDiv').css('display','block');
			$('#paymentStatusLabel').html('Payment is Under Approval');
			
			$("#amount").val(Number(paymentDetail.amount));
			$('#paymentMode').val(paymentDetail.paymentMode);
			$('#paymentMode').trigger('change');
			$('#dateOfPayment').val(formatDate(paymentDetail.paymentDate));
			$('#referenceNo').val(paymentDetail.referenceNo);
			$('#micrCode').val(paymentDetail.micrCode);
			$('#bankName').val(paymentDetail.bankName);
			$('#branchName').val(paymentDetail.branchName);
			$("#dateOfValidity").val(formatDate(paymentDetail.validityDate));
			if(paymentDetail.bankGuaranteeAttachment!=null){
				var bgId = paymentDetail==null?'':paymentDetail.bankGuaranteeAttachment == null ? '': paymentDetail.bankGuaranteeAttachment.attachmentId == null ? '': paymentDetail.bankGuaranteeAttachment.attachmentId;
				var bgName = paymentDetail==null?'':paymentDetail.bankGuaranteeAttachment == null ? '': paymentDetail.bankGuaranteeAttachment.fileName == null ? '': paymentDetail.bankGuaranteeAttachment.fileName;
				$("#BGAttachment").val(bgId);
				var url=$("#a_bgAttachment").data('url');
				$("#a_bgAttachment").attr('href',url);
				var a_bgAttachmentCopy = $("#a_officeNoteAttachment").prop('href')+'/'+bgId;
			    $("#a_bgAttachment").prop('href', a_bgAttachmentCopy);
				$("#a_bgAttachment").html(bgName);	
			}
			
			$('#paymentDate').addClass("readonly");
			$('#paymentMode').addClass("readonly");
			$('#dateOfPayment').addClass("readonly");
			$('#paymentDateDiv').addClass("readonly");
			$('#validityDateDiv').addClass("readonly");
			$('#referenceNo').addClass("readonly");
			$('#micrCode').addClass("readonly");
			$('#bankName').addClass("readonly");
			$('#branchName').addClass("readonly");
			$("#dateOfValidity").addClass("readonly");
			$("#BGId").addClass("readonly");
			$('#BGAttachment').addClass('readonly');
		}
		
	}
	setChildLoadFlag(true);	
}

function itemBidSubmitResp(resp){
	if(!isEmpty(resp) && !isEmpty(resp.response)){
		if(resp.response.hasError){
			if(!isEmpty(resp.response.errors)){
				$("#rightTopId").html("Bidder Status: Bid Submitted");
				Alert.warn(getErrorMsgFromList(resp.response.errors));
			}else{
				$("#rightTopId").html("Bidder Status: Drafted");
				Alert.warn(resp.response.message);
			}
		}else{
			Alert.info(resp.response.message);
		}
	}	
}

function paymentDetailResp(resp){
	debugger;
	if(resp.response.hasError){
		if(!isEmpty(resp.response.message)){
			Alert.warn(resp.response.message);
		}else{
			var msg=getErrorMsgFromList(resp.response.errors);
			Alert.warn(msg);
		}
		/*Alert.warn(resp.response.message);*/
	}else{
		Alert.info(resp.response.message);
		var list=[];
		if($.isEmptyObject(resp)){
			populatePaymentDetailToLeftPane(list);
		}else{
			list.push(resp);
			populatePaymentDetailToLeftPane(list);
			setChildLoadFlag(true);
		}
	}
	
}

function populateExemptionList(exemptedItemList,companyType,company_type_govt){
	$("#emdExemptionTable").DataTable().destroy();
	$('#emdExemptionTable tbody').empty();
	$.each(exemptedItemList,function(idx,exemption){
		if(!$.isEmptyObject(exemption)){
			var exemptedDemand=companyType==company_type_govt?'GOVERNMENT':exemption.registrationType==null?'NA':getValue(exemption.registrationType);
			var isExempted=exemption.isCEApproved=='EXEM'?'YES':'NO';
			var tr=	"<tr>"+
			"<td class='col-sm-3'>"+ exemption.material.code +"</td>" +
			"<td class='col-sm-3'>"+ exemption.material.name +"</td>" +
			"<td class='col-sm-3'>"+ exemptedDemand +"</td>" +
			"<td class='col-sm-3'>"+ isExempted +"</td>" +
			"</tr>";
			
			$("#emdExemptionTable tbody").append(tr);
		}
	});
	$("#emdExemptionTable").DataTable({
		"lengthMenu":lengthMenu
	});
	mobiletable();
}

function checkedNA(ele){
	var id=$(ele).attr('id').split('_');
	if($(ele).prop('checked')){
		$('#response_'+id[1]).attr('disabled','disabled');
		$('#file_'+id[1]).attr('disabled','disabled');
	}else{
		$('#response_'+id[1]).removeAttr('disabled');
		$('#file_'+id[1]).removeAttr('disabled');
	}
}

function partsTab(materialType){
	if(selectedTender.tahdr.tahdrTypeCode=='PT'){
		if(materialType=='single'){
			$("#partsTab").hide();
			$("#priceSubTab").show();
			$("#partsTab").removeClass("active");
			$("#priceSubTab").addClass("active");
			
			$("#tab_k").removeClass("active");
			$("#tab_e").addClass("active");
			$(".bom").removeAttr("readonly");
		}else if(materialType=='bom'){
			$("#partsTab").show();
			$("#priceSubTab").show();
			$("#partsTab").addClass("active");
			$("#priceSubTab").removeClass("active");
			
			$(".bom").attr("readonly","readonly");
			$("#tab_k").addClass("active");
			$("#tab_e").removeClass("active");
		}
	}else if(selectedTender.tahdr.tahdrTypeCode=='WT'){
		$("#partsTab").hide();
		$("#priceSubTab").hide();
		
		$("#partsTab").removeClass("active");
		$("#priceSubTab").removeClass("active");
		
		$("#tab_k").removeClass("active");
		$("#tab_e").removeClass("active");
	}else if(selectedTender.tahdr.tahdrTypeCode=='FA'){
		$("#partsTab").hide();
		
		$("#partsTab").removeClass("active");
		
		$("#tab_k").removeClass("active");
	}
	
}

function setLeftPane(){
	$("#leftPane").html("");	
	var leftPanelHtml= appendTenderMaterialData(selectedItemBid[0],"active");
	setLeftPaneHeader("Material List", '1');
	$("#leftPane").append(leftPanelHtml);
	/*$(".materialLi").on('click',function(){
		var materialId=$(this).attr('materialId');
		loadMaterialBidsToRightPane(itemBidArray["tahdrMaterial"+materialId]);
	});*/
}

function getTahdrDetails(){
	if(getChangedFlag()){
		var toggleValue=$("input[name='toggleTenderType']:checked").val();
		var resp=fetchList("getPurchasedTenderByTypeCode",toggleValue);
		setPageElements(resp);
		setChangedFlag(false);
	}else{
		getCacheLi();
	}
	
}

function loadTenders(){
	if(getChangedFlag()){
		var response=fetchList("getPurchasedTender",$("#documentType").val());
		setPageElements(response);
		setChangedFlag(false);
	}else{
		getCacheLi();
	}
}

function setPageElements(data){
	debugger;
	loadInitData(data);
	loadTenderToLeftPane(data.objectMap.bidderList);
	tenderTypeFields();
	showGstFields();
}
function tenderTypeFields(){
	if(tenderType=='WT' || tenderType=='PT'){
		if(isContractor && isVendor){
			$('#labelWorks').addClass('active');
			loadFieldsForWork();
			$("#emdExemptionTable").DataTable().destroy();
			$("#emdExemptionTable").hide();
		}else if(isContractor){
			loadFieldsForWork()
			$('#labelWorks').addClass('active');
			$("#labelProcurement").addClass('readonly', 'readonly').css({backgroundColor:'#fcc'});
			$("#emdExemptionTable").DataTable().destroy();
			$("#emdExemptionTable").hide();
		}else if (isVendor){
			$("#emdExemptionTable").show();
			loadFieldsForProcurement();
			$('#labelProcurement').addClass('active');
			$("#labelWorks").addClass('readonly', 'readonly').css({backgroundColor:'#fcc'});
		}
	}else if(tenderType=='FA'){
		if(isCustomer){
			loadFieldsForForwardAuction();
		}
	}
}

function showGstFields(){
	if(isGstApplicable=='N'){
		$("#igst").attr("disabled","disabled");
		$("#cgst").attr("disabled","disabled");
		$("#sgst").attr("disabled","disabled");
		$("#taxRate").attr("disabled","disabled");
		
		$("#igstDiv").css("display","none");
		$("#cgstDiv").css("display","none");
		$("#sgstDiv").css("display","none");
		$("#taxRateDiv").css("display","none");
		
		$("#partIgst").attr("disabled","disabled");
		$("#partCgst").attr("disabled","disabled");
		$("#partSgst").attr("disabled","disabled");
		$("#partTaxRate").attr("disabled","disabled");
		
		$("#partIgstDiv").css("display","none");
		$("#partCgstDiv").css("display","none");
		$("#partSgstDiv").css("display","none");
		$("#partTaxRateDiv").css("display","none");
		
		$("#gstCBDiv").css("display","none");
		$("#igstCBDiv").css("display","none");
		$("#cgstCBDiv").css("display","none");
		$("#sgstCBDiv").css("display","none");
		
		$("#gstCB").attr("disabled","disabled");
		$("#igstCB").attr("disabled","disabled");
		$("#cgstCB").attr("disabled","disabled");
		$("#sgstCB").attr("disabled","disabled");
		

		$("#perUnitGSTDiv").css("display","none");
		$("#totalGSTDiv").css("display","none");
		$("#fddRateWithGSTDiv").css("display","none");
		$("#fddAmountWithGSTDiv").css("display","none");
		
		$("#taxAmount").attr("disabled","disabled");
		$("#totalTax").attr("disabled","disabled");
		$("#fddRateGST").attr("disabled","disabled");
		$("#fddAmountGST").attr("disabled","disabled");
	}else {
		if(isIntraState){
			$("#igst").attr("disabled","disabled");
			$("#igstDiv").css("display","none");
			
			$("#partIgst").attr("disabled","disabled");
			$("#partIgstDiv").css("display","none");
			$("#taxLabel").empty();
			$("#taxLabel").text("CGST+SGST");
			
			$("#igstCBDiv").css("display","none");
			$("#igstCB").attr("disabled","disabled");
			
			$(".intraState").show();
			$(".interState").hide();
		}else{
			$(".intraState").hide();
			$(".interState").show();
			
			$("#taxLabel").empty();
			$("#taxLabel").text("Integrated GST");
			
			$("#partCgst").attr("disabled","disabled");
			$("#partSgst").attr("disabled","disabled");
			
			$("#partCgstDiv").css("display","none");
			$("#partSgstDiv").css("display","none");
			
			$("#cgst").attr("disabled","disabled");
			$("#sgst").attr("disabled","disabled");
			
			$("#cgstDiv").css("display","none");
			$("#sgstDiv").css("display","none");
			
			$("#cgstCBDiv").css("display","none");
			$("#sgstCBDiv").css("display","none");
			
			$("#cgstCB").attr("disabled","disabled");
			$("#sgstCB").attr("disabled","disabled");
			
		}
	}
}

function downloadTahdrDetailDoc(){
         submitWithParam("getTAHDRDocDetail", "tahdrId", "generateDocumentResp");
}

function generateDocumentResp(resp){
	if($.isEmptyObject(resp)){
		Alert.warn("Tender Details cannot be Downloaded !");
		$("#generatedDoc").empty();
	}else{
		    var tenderDocs=resp.objectMap.tahdrDetail==null?'':resp.objectMap.tahdrDetail.tenderDoc==null?'':resp.objectMap.tahdrDetail.tenderDoc;
			var anchorHtml='<a href="download/'+tenderDocs.attachmentId+'">'+tenderDocs.fileName+'</a>';
			$("#generatedDoc").empty();
			$("#generatedDoc").append(anchorHtml);
	}
}

function loadPaymentModeList(id,list){
	debugger;
	$("#"+id).empty();
	$("#"+id).append("<option value=''></option>" );
	$.each(list,function(key,value){
		$("#"+id).append("<option value='"+value.code+"'>"+value.name+" </option>" );
	});
}

function fetchAllList(bidderSecDocId, bidderId,itemBidId,tahdrDetailId){
	debugger;
	var response;
	$.ajax({
        type : "POST",
        contentType : "application/json",
        url: "deleteBidderSecDoc?bidderSecDocId="+bidderSecDocId+"&bidderId="+bidderId+"&itemBidId="+itemBidId+"&tahdrDetailId="+tahdrDetailId,
        /*url: "deleteBidderSecDoc/"+bidderSecDocId+"/"+bidderId+"/"+itemBidId,*/
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

function checkCommercialBid(){
	var tahdrId=$(".tahdrId").val();
	var resp="";
	if(selectedTender.tahdr.tahdrTypeCode=='PT'){
		resp=fetchList("getCommercialBid",tahdrId);
		if(resp.objectMap.commercialBid==null){
			$('#generateCbBtnId').attr('disabled','disabled');
		}else{
			$('#generateCbBtnId').removeAttr('disabled');
		}
	}else if(selectedTender.tahdr.tahdrTypeCode=='WT' || selectedTender.tahdr.tahdrTypeCode=='FA'){
		resp=fetchList("getCommercialBidDocs",tahdrId);
		if(resp.objectMap.bidderSecDocSet==null){
			$('#generateCbBtnId').attr('disabled','disabled');
		}else{
			$('#generateCbBtnId').removeAttr('disabled');
		}
	}
}

function updateBidderStatus(){
	var bidderId=$(".bidderId").val();
	var data=fetchList("getBidderByBidderId",bidderId);
	if(!isEmpty(data) && !isEmpty(data.objectMap)){
		var bidderStatus=data.objectMap.bidder==null?'':data.objectMap.bidder.status;
		var bStatus=bidderStatus==''?'':bidStatusList[bidderStatus];
		$(".rightTopClass").html("Bidder Status: "+bStatus);
		setChangedFlagById('tahdrDetailsTab',true);
	}
}