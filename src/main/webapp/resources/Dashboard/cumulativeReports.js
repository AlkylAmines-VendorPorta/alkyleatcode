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

var itemArray=new Array();
var bidders=new Array();
var materialList=new Array();
var total=new Array();
$(document).ready(function(){
	debugger;
	 if (!$("#bidSheetTblId").is(":blk-transpose"))
         $("#bidSheetTblId").transpose({ mode: 0 });
	 var lengthMenu;
	 if ($(window).width() < 480) {
	        $('.mobileNav').show();
	        $.fn.DataTable.ext.pager.numbers_length = 4;       
	        lengthMenu = [ 1, 5, 7, 10, ],
	        [ 1, 5, 7, 10, ]
	    } else {        
	        lengthMenu = [ 5, 10, ],
	        [ 5, 10, ]
	    }
	 $('.tableResponsive').DataTable();
	$('.filterByTahdrCode').click(function(){
		var tenderType=$(this).data('documenttype');
		$('#filter li.active').removeClass('active');
		$(this).addClass('active');
		onPageLoad(tenderType);
	});
	
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
	
	$('#tenderDetailTab').click(function(event) {
		
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
    });
	
	$('#pagination-here').paginate({
		pageSize:  7,
		dataSource: 'fetchTenderList',
		responseTo:  'loadTenderListFromPagination',
		maxVisiblePageNumbers:3,
		searchBoxID : 'searchLiteralId',
		loadOnStartup: false
	});

	var tenderType='TENDER';
	onPageLoad(tenderType);
});

function dataBind(){
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
	
	$('.toggleTab').on('click',function(e){
		e.preventDefault();
		onToggleTab(this);
	});
	
}

function setTogglebtn(tenderType){
	var htmlToggle='';
	if(tenderType=='AUCTION'){
		htmlToggle=htmlToggle+' <label class="btn btn-primary toggleTab" openTab="tenderDetailsTab">'+
		'<input type="radio" name="tenderTypeCodeToggle" value="FA">'+
		'<span class="glyphicon glyphicon-ok"></span> Forward '+
    '</label><label class="btn btn-primary active toggleTab" openTab="tenderDetailsTab">'+
   ' <input type="radio" name="tenderTypeCodeToggle" checked value="RA"> '+
   ' <span class="glyphicon glyphicon-ok"></span> Reverse   </label>';
	}else if(tenderType=='QUICK_AUCTION'){
		htmlToggle=htmlToggle+' <label class="btn btn-primary toggleTab" openTab="tenderDetailsTab">'+
		'<input type="radio" name="tenderTypeCodeToggle" value="QFA">'+
		'<span class="glyphicon glyphicon-ok"></span> Quick Forward '+
    '</label><label class="btn btn-primary active toggleTab" openTab="tenderDetailsTab">'+
   ' <input type="radio" name="tenderTypeCodeToggle" checked value="QRA"> '+
   ' <span class="glyphicon glyphicon-ok"></span> Quick Reverse   </label>';
	}else if(tenderType=='QUICK_RFQ'){
		htmlToggle=htmlToggle+' <input type="radio" name="tenderTypeCodeToggle" checked style="display:none;" value="QRFQ" >';
	}else if(tenderType=='RFQ'){
		htmlToggle=htmlToggle+' <input type="radio" name="tenderTypeCodeToggle" checked style="display:none;" value="RFQ" >';
	}else{
		htmlToggle=htmlToggle+' <label class="btn btn-primary toggleTab" openTab="tenderDetailsTab">'+
		'<input type="radio" name="tenderTypeCodeToggle" value="PT">'+
		'<span class="glyphicon glyphicon-ok"></span> Procurement '+
    '</label><label class="btn btn-primary active toggleTab" openTab="tenderDetailsTab">'+
   ' <input type="radio" name="tenderTypeCodeToggle" checked value="WT"> '+
   ' <span class="glyphicon glyphicon-ok"></span> Works   </label>';
	}
	$('#toggleBtn').html(htmlToggle);
	dataBind();
}
function onPageLoad(tenderType){
	setTogglebtn(tenderType);
	var typeCode=$("input[name='tenderTypeCodeToggle']:checked").val();
	documentType=$(".documentType").val();
	var data=fetchTenderList(1, pageSize, searchMode, searchValue,typeCode);
	if(data!=undefined || data!=null){
		loadTenderListFromPagination(data.data);
		setPagination(data.objectMap.LastPage, selectedPage , maxVisiblePageNumbers);
	}else{
		Alert.warn('No Recound found !')
	}
	
	setCurrentTab($("#tenderDetailTab")[0]);
    setChangedFlag(false);
    var documentType=$(".documentType").val();
	setLeftPaneHeader(" List", $("#leftPaneData li").length);
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
	  var createdOn=data.created;
	  var createdBy=data.createdBy==null?'':data.createdBy.name;
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
	  if(tahdrTypeCode=='WT'){
		  $('.cumulativeReportsTab').addClass('readonly');
		  $('.cumulativeReportsTab').attr('style','display: none;');
	  }else{
		  $('.cumulativeReportsTab').removeClass('readonly');
		  $('.cumulativeReportsTab').removeAttr('style'); 
	  }
	  
	  $('#tenderDetailsForm #tahdrId').val(tahdrId);
	  $('#tenderDetailsForm #tenderNo').html(tahdrCode);
	  $('#tenderDetailsForm #trader').html(isTrade);
	  $('#tenderDetailsForm #createdOn').html(formatDateTime(createdOn));
	  $('#tenderDetailsForm #createdBy').html(createdBy);
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
		$('.cumulativeReportsTab').addClass('readonly');
		 $('.cumulativeReportsTab').removeAttr('style'); 
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
	setHeaderValues(" Title :" , " Version : ","Description: ", "Contact Detail : ");
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

function loadCumulativeData(){
	var tahdrId= $('#tenderDetailsForm #tahdrId').val();
	if(tahdrId!='' || tahdrId!=undefined){
		submitToURL('getCumulativeSheetByTahdrId/'+tahdrId, 'loadCumulativeDataResp');
		/*submitToURL('getCumulativeReportByTahdrId/'+tahdrId, 'loadCumulativeDataResp');*/
	}
}

function loadCumulativeDataResp(data){
	total=new Array();
	/*$(".v_listolivefbider").DataTable().destroy();*/
	$('.v_listolivefbider tbody').empty();
	$('.v_listolivefbider tfoot').empty();
	$('.v_listolivefbider thead').empty();
		
		$('#leftPaneData li:not(.active)').hide();
		/*$('#leftPaneData li:not(.active)').remove();*/
	    
		if (!$.isEmptyObject(data.objectMap.bidder)) {
			bidders=data.objectMap.bidder;
		}
		if (!$.isEmptyObject(data.objectMap.materialList)) {
			materialList=data.objectMap.materialList;
		}
		if (!$.isEmptyObject(data.objectMap.rank)) {
			rankArray=data.objectMap.rank;
		}
		if(!$.isEmptyObject(data.objectMap.materialList) && !$.isEmptyObject(data.objectMap.bidderList)){
			var html=contructCumulativeTable(materialList,bidders);
			
			$('#bidSheetTblId').html(html);
			$('.v_listolivefbider tbody').append('');
			if (!$.isEmptyObject(data.objectMap.bidderList)) {
				var data = data.objectMap.bidderList;
				$.each(data, function(key, value) {
					var bidderData=value;
					var matId=bidderData.itemBid==null?'':bidderData.itemBid.tahdrMaterial.tahdrMaterialId;
					var bidderId=bidderData.itemBid==null?'':bidderData.itemBid.bidder==null?'':bidderData.itemBid.bidder.bidderId;
					var offeredQuantity=bidderData.offeredQuantity==null?'':bidderData.offeredQuantity;
					var fddAmountWithGST=bidderData.fddAmountWithGST==null?'':bidderData.fddAmountWithGST;
					var formatfddAmountWithGST=indianRupeesFormatWithDecimal(fddAmountWithGST);
					var rating=bidderData.partner==null?'':Number(bidderData.partner.rating);
					var preVal=total[bidderId]==undefined?0:total[bidderId];
					total[bidderId]=Number(preVal)+Number(fddAmountWithGST);
					$('#'+matId+'-'+bidderId+'').html(formatfddAmountWithGST);
					
				});
				$.each(bidders, function(key, value) {
					var statusClass='';
					var bidderId=value==null?'':value.bidderId;
					var rating=value.partner==null?'':Number(value.partner.rating);
					var partnerId=value.partner==null?'':value.partner.bPartnerId;
					 if(rating<4){
						 statusClass = "rejectedStatus";
					 }else if(rating>4 && ( rating<6 || rating==6)){
					    	statusClass = "deviationStatus";
					 }else if( rating>7){
					    	statusClass = "approvedStatus";
					 }else{
					    	statusClass = "pendingStatus";
					 }
					$('#star-'+bidderId+'').html(rating+' out of 10');
					$('#star-'+bidderId+'').addClass(statusClass);
					var t=total[bidderId];
					if(t!=undefined && t!="NaN"){
						var totalAmount=indianRupeesFormat(t);
						$('#total-'+bidderId+'').html(totalAmount);
					}
					/*var overAllRank=rankArray['rankMap_'+partnerId];
					if(overAllRank!=undefined && overAllRank!="NaN"){
						$('#rank-'+bidderId+'').html(overAllRank);
					}*/
				});
				setMinumumValue();
				
			} else {
				$('.v_listolivefbider tbody').empty();
				Alert.warn(data.objectMap.resultMessage);
			}
		}else{
			var html='<thead><tr><th>Material Name</th><th>Bidder Name</th></tr></thead>';
				$('#bidSheetTblId').html(html);
				Alert.warn('Data Unavailable');
		}
		
		
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
		
		/* var currentMode = $("#bidSheetTblId").data("tp_mode");
	     if (currentMode == undefined) {
	         $("#bidSheetTblId").transpose("transpose");
	         $("#btnTpVertical").html("Reset");
	     }
	     else {
	         $("#bidSheetTblId").transpose("reset");
	         $("#btnTpVertical").html("Transpose");
	     }*/
		setLeftPaneHeader("Cumulative Sheet", null);
}



function downloadCumulativeDetailsPdf(ele){
	event.preventDefault();
	var docType=$(ele).data('type');
	var tenderCode=  $('#tenderDetailsForm #tenderNo').html();
	$("#bidSheetTblId").tableHTMLExport({type:docType,filename:'Cumulative'+tenderCode+'.'+docType+''});
	Alert.info('Pdf Dowloaded!');
}