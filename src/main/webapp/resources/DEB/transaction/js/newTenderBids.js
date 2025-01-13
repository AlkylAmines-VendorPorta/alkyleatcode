/**
 * createdBy : Aman Sahu
 */

var tenderArray= new Array();
var tahdrMaterialArray=new Array();
var bidderBidDetailArray=new Array();
var tenderStatusList=new Array();
var bidderStatus=new Array();
var tenderTypeCode='';
var materialParts=new Array();
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
var commencementPeriod=0;
var documentType='Tender/Auction';
var LastPage=0;

var activeTenderStatus='';
var lengthMenu=0;
$(document).ready(function(){
	if ($(window).width() < 480) {
        $('.mobileNav').show();
        $.fn.DataTable.ext.pager.numbers_length = 4;       
        lengthMenu = [ 1, 5, 7, 10, ],
        [ 1, 5, 7, 10, ]
    }else{        
        lengthMenu = [ 5, 10, ],
        [ 5, 10, ]
    }
	onPageLoad();
	$("input[name='tenderTypeCodeToggle']").on("change",function(){
		selectedPage=1;
		$('.pagination').html('');
		var tenderTypeCode=$('input[name=tenderTypeCodeToggle]:checked').val();
		var data=fetchTenderList(1, pageSize, searchMode, searchValue,tenderTypeCode);
		if(data!=undefined || data!=null){
			loadTenderListFromPagination(data.data);
			setPagination(data.objectMap.LastPage, selectedPage , maxVisiblePageNumbers);
		}else{
			Alert.warn('No Recound found !')
		}
		
		 $(".tabs-left li a label").text(function(index, currentText) {
			    return currentText.substr(0, 20);
			});
		 setLeftPaneHeader(" List", $("#leftPaneData li").length);
	});
	
	$('#tenderTabId').click(function(event) {
		
		selectedPage=1;
		$('.pagination').html('');
		var tenderTypeCode=$(this).val();
		var data=fetchTenderList(1, pageSize, searchMode, searchValue,tenderTypeCode);
		if(data!=undefined || data!=null){
			loadTenderListFromPagination(data.data);
			setPagination(data.objectMap.LastPage, selectedPage , maxVisiblePageNumbers);
		}else{
			Alert.warn('No Recound found !')
		}
		 $(".tabs-left li a label").text(function(index, currentText) {
			    return currentText.substr(0, 20);
			});
		 setLeftPaneHeader(" List", $("#leftPaneData li").length);
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
	if(data!=undefined || data!=null){
		loadTenderListFromPagination(data.data);
		setPagination(data.objectMap.LastPage, selectedPage , maxVisiblePageNumbers);
	}else{
		Alert.warn('No Recound found !')
	}
	
	setCurrentTab($("#tenderTabId")[0]);
    setChangedFlag(false);
    resetFormData(typeCode);
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
							/*var budgetType=value.budgetType==null?'': value.budgetType=="CAP"?"CAPEX":"REVENUE";*/
							var tahdrTypeCode=value.tahdrTypeCode;
							var status=tenderStatusList[value.tahdrStatusCode];
							
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
							    +'	<label class="col-xs-6" id="'+tahdrId+'_status">'+status+'</label>'
								+'	<label class="col-xs-6"></label>'
								+' </div>'
							    +' </a>'
							    +' </li>';
								i++;
							}  
						resetFormData(tahdrTypeCode);
					});
					$(".leftPaneData").html(leftPanelHtml);
					 $(".tabs-left li a label").text(function(index, currentText) {
						    return currentText.substr(0, 20);
						});
					 $('.viewBidTab').removeClass('readonly');
					 $('.bidTab').addClass('readonly');
					 $('.bidderTab').addClass('readonly');
			}else{
				$('.viewBidTab').addClass('readonly');
			}
			loadTahdrDetailRightPane(firstRow);
			 
}

function loadTahdrDetailRightPane(data){
	if(!$.isEmptyObject(data)){
	  var tahdrDetail=data.tahdrDetail[0];
	  var tahdrId=data.tahdrId==null?'':data.tahdrId;
	  var tahdrCode=data.tahdrCode==null?'':data.tahdrCode;
	  var tahdrTypeCode=data.tahdrTypeCode==null?'':data.tahdrTypeCode;
	  var title=data.title==null?'':data.title;
	  var isAuction =data.isAuction;
	  var tenderStatus =data.tahdrStatusCode;
	  activeTenderStatus=tenderStatus;
	  var version=tahdrDetail==null?'':tahdrDetail.version;
	  var description=tahdrDetail==null?'':tahdrDetail.description;
	  var contact=tahdrDetail==null?'':tahdrDetail.contactEmailId;
	  var isManufacture=data.isManufacturer==null?'Not Specified':data.isManufacturer=='Y'?'YES':'NO';
	  var isTrade=data.isTrader==null?'Not Specified':data.isTrader=='Y'?'YES':'NO';
	  var tenderBidType=data.bidTypeCode;
	  var tenderDoc=tahdrDetail==null?null:tahdrDetail.tenderDoc;
	  commencementPeriod=tahdrDetail==null?null:tahdrDetail.commencementPeriod;
	  
	  if(tenderStatus=='PBOP' || tenderStatus=='C1SCH' || tenderStatus=='ASCH' 
		  || tenderStatus=='C1OP' || tenderStatus=='AWSCH' || tenderStatus=='AWC'){
		  $('.openedAuctionTab').removeAttr('style','display : none ;');
		  if(isAuction=='Y'){
			  $('.auctionTab').removeAttr('style','display : none ;');
		  }else{
			  $('.auctionTab').attr('style','display : none ;');
		  }
	  }else{
		  $('.openedAuctionTab').attr('style','display : none ;');
		  $('.auctionTab').attr('style','display : none ;');
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
		setHeaderValues(" Title: "+title, " Version : "+version, "Description: "+description, "Contact Detail : "+contact);
		$('#tahdrMaterialTabId').removeClass('readonly');
		if(tahdrTypeCode=='QRA' || tahdrTypeCode=='QFA'){
			$('.regularAuction').attr('style','display: none;');
			$('.quickAuction').removeAttr('style');
		}else{
			$('.quickAuction').attr('style','display: none;');
			$('.regularAuction').removeAttr('style');
			if(tenderBidType=='SB'){
				  $('#tenderDetailsForm #tenderBidType').html('Single Bid');
				  $('.finalScrutiny').attr('style','display : none;');
			  }else{
				  $('.finalScrutiny').removeAttr('style');
				  $('#tenderDetailsForm #tenderBidType').html('Two Bid');
			  }
		}
	}else{
		$('#tahdrMaterialTabId').addClass('readonly');
		 $('.finalScrutiny').removeAttr('style');
		 $('#tenderDetailsForm #tenderNo').html('');
		 $('#tenderDetailsForm #tahdrId').val('');
		 $('#tenderDetailsForm #trader').html('');
		 $('#tenderDetailsForm #manfacturer').html('');
		 $('#tenderDetailsForm #status').html('');
		 $('#tenderDetailsForm #noBidder').html('');
		 $('#tenderDetailsForm #tenderBidType').html('');
		 $("#downloadTenderDoc").removeAttr('href',url);
		 $("#downloadTenderDoc").html(''); 
		 ttitle='';
		 tversion='';
		 tdescription ='';
		 tcontact ='';
		setHeaderValues("", "", "", "");
	}
	setLeftPaneHeader(" List", $("#leftPaneData li").length);
	/*setChildLoadFlag(true);*/
}

function showTahdrDetail(id){
		loadTahdrDetailRightPane(tenderArray['tender_'+id]);
}

function resetBidsDetailForm(){
	var documentType=$(".documentType").val();
	setHeaderValues(" Title :" , " Title : ","Description: ", "Contact Detail : ");
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
		$('.bidTab').addClass('readonly');
	}else{
		$('.bidderTab').addClass('readonly');
		$('.bidTab').addClass('readonly');
	}
	setLeftPaneHeader("Material List", $("#leftPaneData li").length);
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
			
			$(".reqQuantity").html(reqQuantity);
			$(".specVersion").html(specVersion);
			$(".tenderMatType").html(tenderMatType);
			$(".matName").html(name);
			$(".matCode").html(code);
			$(".uom").html(uomName);
			$(".description").html(description);
			if(tenderMatType=='bom'){
				$('#partsPriceBidDiv').removeAttr('style');
			}else{
				$('#partsPriceBidDiv').attr('style','display : none ;');
			}
			
			setHeaderValues(" Title: "+ttitle, " Version : "+tversion, "Description: "+tdescription, "Contact Detail : "+tcontact);
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
		
		$(".reqQuantity").html('');
		$(".specVersion").html('');
		$(".tenderMatType").html('');
		$(".matName").html('');
		$(".matCode").html('');
		$(".uom").html('');
		$(".description").html('');
		
		$("#itemDetailForm #reqQuantity").html('');
		$("#itemDetailForm #specVersion").html('');
		$("#itemDetailForm #tenderMatType").html('');
		$('#partsPriceBidDiv').attr('style','display : none ;');
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
		setLeftPaneHeader("Bidders List", $("#leftPaneData li").length);
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
			var bStatus=status;
	        if(status!=''){
	        	status=bidderStatus[status];
	        	if(bStatus=='C1OP'){
	        		var tahdrMaterialId=$("#itemDetailForm #tahdrMaterialId").val();
	        		$('#bidderDetailForm #allocatedQty').removeAttr('style');
	        		var resp=fetchList("getAllocatedQunatity/"+tahdrMaterialId+"/"+bidderId,null);
	        		var qty=resp.objectMap.allocatedQty==null?0:resp.objectMap.allocatedQty;
	        		$('#bidderDetailForm #allocatedQty').html(qty);
	        	}
	        	else{
		        	$('#bidderDetailForm #allocatedQuantityDiv').attr('style','display : none;');
		        	$('#bidderDetailForm #allocatedQty').html(0);
		        }
	        }else{
	        	$('#bidderDetailForm #allocatedQuantityDiv').attr('style','display : none;');
	        	$('#bidderDetailForm #allocatedQty').html(0);
	        }
	        
	        $('#bidderDetailForm #partnerId').val(partnerId);
			$('#bidderDetailForm #bidderId').val(bidderId);
			$('#bidderDetailForm #bidderName').html(bidderName);
			$('#bidderDetailForm #tenderName').html(title);
			$('#bidderDetailForm #tenderNo').html(tenderNo);
			$('#bidderDetailForm #bidderStatus').html(status);
			bname=bidderName;
			setHeaderValues("Material name: "+mname, "Material Code: "+mcode, "Required Qtys: "+mreqQty, "Material Type : "+mmatType);
		}
	else{
		$('#bidderDetailForm #partnerId').val('');
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

function resetFormData(tenderTypeCode){
	resetCollapseData();
	if(tenderTypeCode=='WT'){
		  $('.workscollapseaple').attr('style','display : none;');
	}else{
		  $('.workscollapseaple').removeAttr('style');
	}
}

function resetCollapseData(){
	$('.panel-collapse').removeClass('in');
	$(".table").DataTable().destroy();
	$(".table tbody").empty();
}



