var partnerArray = new Array();
var editMode=false;
var activeTabName="";
var requiredFileDeleted=false;
var vendorStatus="";
var pageSize = 7;
var partnerLastPage;
var selectedPage = 1;
var setPageSelected = false;
var maxVisiblePageNumbers = 3;
var partneSearchStatus='';
var dataSourceURL='';
var partnerStatusCheck=false;
$(document).ready(function(){
	
	 var lengthMenu;
	 var companyName;
	 var registrationNo;
	 var panNo;
	 var vendorSAPCode;
	 var partnerStatus;
	    if ($(window).width() < 480) {
	        $('.mobileNav').show();
	        $.fn.DataTable.ext.pager.numbers_length = 4;       
	        lengthMenu = [ 1, 5, 7, 10, ],
	        [ 1, 5, 7, 10, ]
	    } else {        
	        lengthMenu = [ 5, 10, ],
	        [ 5, 10, ]
	    }
	    
	      
	/*$('.nonFilterTab').click(function(event){
		event.preventDefault();
		if(!editMode && !requiredFileDeleted){
		  $("#filterBtnId").addClass("readonly");
		  checkForFilterByRole();
		}
		
    });*/
	
	$('.addItemPopup').click(function(event){
		event.preventDefault();
		$('.commenSearchModal').modal('show');
		$(".itemTable").DataTable().destroy();
		$(".itemTable tbody").empty();
		populateMaterialGroupList(fetchList("getMaterialGroupList",null));
		/*populateSubGroupList(fetchList("getMaterialSubGroupList",null));*/
		$.fn.DataTable.ext.pager.numbers_length = 5;
		$('.commenSearchTable').DataTable({
			"lengthMenu":lengthMenu
		});
	
	});
	var partnerData =  $("#partnerData").val();
	if(partnerData == 'partnerProfiles'){
		$("#filterBtnId").removeClass('readonly');
		loadSearchLeftPane();
		/*submitToURL("getPartners", 'onPageLoad');*/
		vendorStatus="";
		partneSearchStatus='';
		var response=getVendorBySearch(1,pageSize,'none','none');
		loadSearchVendorsResp(response);
	}else{
		$("#filterBtnId").addClass('readonly');
		submitToURL("getPartner", 'onPageLoad');
		$('.leftPaneData').paginathing();
		
	}
	$('.confirm').click(function(){
		Alert.info({
			  title: 'Are you sure?',
			  text: 'You will not be able to recover this imaginary file!',
			  type: 'warning',
			  showCancelButton: true,
			  confirmButtonColor: '#3085d6',
			  cancelButtonColor: '#d33',
			  confirmButtonText: 'Confirm',
			  cancelButtonText: 'Cancel',
			  confirmButtonClass: 'confirm-class',
			  cancelButtonClass: 'cancel-class',
			  closeOnConfirm: false,
			  closeOnCancel: false
			},
			function(isConfirm) {
			  if (isConfirm) {
			    Alert.info(
			      'Deleted!',
			      'Your file has been deleted.',
			      'success'
			    );
			  } else {
				  Alert.warn(
			      'Cancelled',
			      'Your imaginary file is safe :)',
			      'error'
			    );
			  }
			});
	});
	
	$('#cancelCompDetailsBtnId').click(function(event){
		event.preventDefault();
		var compDetailsId=$('.leftPaneData').find('li.active').attr('id');
		if(compDetailsId!=undefined)
			{
			  var compData=partnerArray["partner"+compDetailsId];
			  loadRightPane(compData);
			}else{
			  $('#companyDetails')[0].reset();
		    }
	});
	$('#isInfra').click(function(){
		if(this.checked){
			showVendorPaymentTab(null,null,"Y");
			
		}else{
			showVendorPaymentTab(null,null,"N");
		}
	}); 
	$('#isManufacturer').click(function(){
		var value='';
		if (this.checked) {
			value="Y";
			
		}else{
			value="N";
			
		}
		showFactoryDetailTab(value);
		
	}); 
	$('#isTrader').click(function(){
		var value='';
		if (this.checked) {
			value="Y";
			
		}else{
			value="N";
			
		}
		showManufacturerTab(value);
	});
	
	$('#isContractor').click(function(){
		if (this.checked) {
			$("#contractorTypeDiv").show();
			$("#contractorType").removeAttr('disabled','disabled');
		}else{
			$("#contractorTypeDiv").hide();
			$("#contractorType").attr('disabled','disabled');
		}
	});
	
	$("#clarification").click(function(){
		$("#remarks").addClass("requiredField");
	});
	$('#rejectBtnId').click(function(){
		$("#remarks").addClass("requiredField");
	});
	$('#approveBtnId').click(function(){
		$("#remarks").removeClass("requiredField");
	});
	
	$('#rejectBtn').click(function(){
		$("#remark").addClass("requiredField");
	});
	$('#approveBtn').click(function(){
		$("#remark").removeClass("requiredField");
	});
	
	
	/*$('#isGstApplicable').click(function(){
		debugger;
	if (this.checked) {
	  $("#GSTINCopyId").addClass("requiredFile");
	}else{
		$("#GSTINCopyId").removeClass("requiredFile");
	}
	});*/
	/*$('#confirmationTabId').click(function(){
		$(".leftPaneData").html("");
	});*/
	
	/*$(".approveCEDiv").hide();
    $(".approveEEDiv").hide();*/
	
	$('.searchLeftPane').on('click', 'li', function() {
	    $('.searchLeftPane li.active').removeClass('active');
	    $(this).addClass('active');
	});
    
   
    $("#companyDetails").find("input,select,textarea").change(function() {
    	 editMode=true;
    	 activeTabName="Company Details";
    	
    });
    $("#companyDetails .fileDeleteBtn").click(function() {
    	 editMode=true;
    	 requiredFileDeleted=true;
    	 activeTabName="Company Details";
    });

    $('#pagination-here').paginate({
		pageSize:  7,
		loadOnStartup : false,
		dataSource: 'getVendorBySearch',
		responseTo:  'loadSearchVendorsResp',
		maxVisiblePageNumbers:3,
		searchBoxID : 'searchLiteralId',
		getAllData : true	
	});
/* $('#pagination-here').on("page", function(event, num){
	 
	 selectedPage = num;
	 setPageSelected = true;
	
			if(vendorStatus=="")
				{
					submitToURL("nextPagePartners/"+num+"/"+pageSize, 'onPageLoad');
				}else  if(vendorStatus=="APPROVED"){
					submitWithThreeParam('getVendorsList',num,pageSize,'approveStatusId','loadVendorsByStatusResp');
				}else if(vendorStatus=="REJECTED"){ 
					submitWithThreeParam('getVendorsList',num,pageSize,'rejectStatusId','loadVendorsByStatusResp');
				}else if(vendorStatus=="CLARIFIED"){
					submitWithThreeParam('getVendorsList',num,pageSize,'clarifyStatusId','loadVendorsByStatusResp');
				}
	  		$(".leftPaneData").children().eq(0).click().addClass('active');
	});*/
 
/* $(".jumptopage").click(function(event){
	 	event.preventDefault();
		var jumpIndex = $("#jumpIndex").val();
		setPageSelected = true;
		 
			if(jumpIndex != ''){
				selectedPage = jumpIndex;
				isServerSidePaginationEmpty = $('#pagination-here').is(':empty');
				if(!isServerSidePaginationEmpty){
					
					if(vendorStatus=="")
					{
						submitToURL("nextPagePartners/"+jumpIndex+"/"+pageSize, 'onPageLoad');
					}else  if(vendorStatus=="APPROVED"){
						submitWithThreeParam('getVendorsList',jumpIndex,pageSize,'approveStatusId','loadVendorsByStatusResp');
					}else if(vendorStatus=="REJECTED"){
						submitWithThreeParam('getVendorsList',jumpIndex,pageSize,'rejectStatusId','loadVendorsByStatusResp');
					}else if(vendorStatus=="CLARIFIED"){
						submitWithThreeParam('getVendorsList',jumpIndex,pageSize,'clarifyStatusId','loadVendorsByStatusResp');
					}
		  		$(".leftPaneData").children().eq(0).click().addClass('active');
				}	
		}else{
			Alert.warn('Enter Page number');
		}
	});
*/ 
});

function blackListing(event){
	event.preventDefault();
	var bpartnerId=$('#companyDetails #bPartnerId').val();
	if(bpartnerId!=""){
		$('#blackListFormId #tagrgetPartnerId').val(bpartnerId);
		return $('#blackListModal').modal('show');
	}
}

function blackListingResp(data){

	if(!isEmpty(data) && !isEmpty(data.objectMap) && !isEmpty(data.objectMap.result)){
	var msg= data.objectMap.message==null?'':data.objectMap.message;
		if(data.objectMap.result){
		Alert.info(msg);
		$('#rightBottomId').html("Status: BLACKLISTED");
		var bpartnerId=$('#companyDetails #bPartnerId').val();
		partnerArray["partner"+bpartnerId].isApproved="B";
		$('#blackListingDivId').hide();	
	}else{
		Alert.warn(msg);
		$('#blackListingDivId').show();
		$('#blackListingBtnId').removeClass('btn-warning');
		$('#blackListingBtnId').addClass('btn-info');
		
		$('#blackListingBtnId').addClass('btn-warning');
		$('#blackListingBtnId').removeClass('btn-info');
	}
	}
	return $('#blackListModal').modal('hide');
}
function setBlacklistingStatus(status){

	 var role=$("#roleData").val();
	 if(role=='EXEENGR'){
		 if(status=='B'){
			 $('#rightBottomId').html("Status: BLACKLISTED");
			 $('#blackListingDivId').hide();
			}else{
				 $('#blackListingDivId').show();
				$('#blackListingBtnId').removeClass('btn-warning');
				$('#blackListingBtnId').addClass('btn-info');
				
				$('#blackListingBtnId').addClass('btn-warning');
				$('#blackListingBtnId').removeClass('btn-info');
				$('#blackListingBtnId').html('Blacklist this Vendor');
			} 
	 }
	
}
function loadSearchLeftPane(){
	$(".searchLeftPane").html("");
	var leftPanelHtml="";
	leftPanelHtml = leftPanelHtml + '<li style="display:none;"><a href="#contains">'+
	'<input type="radio" name="filterBy" value="none" checked=checked/></a></li>';
	leftPanelHtml = leftPanelHtml + '<li onclick="getApproveVendors();">'
	+' <a href="#Master" data-toggle="tab">'+'Approved Vendors'+'</a>'+'</li>';
    leftPanelHtml = leftPanelHtml + '<li onclick="getClarifiedVendors();">'
    +' <a href="#Master" data-toggle="tab">'+'Clarified Vendors'+'</a>'+'</li>';
    leftPanelHtml = leftPanelHtml + '<li onclick="getRejectedVendors();">'
	+' <a href="#Master" data-toggle="tab">'+'Rejected Vendors'+'</a>'+'</li>';
    leftPanelHtml = leftPanelHtml + '<li onclick="getPartnerInfo();">'
	+' <a href="#Master" data-toggle="tab">'+'Pending Approval'+'</a>'+'</li>';
    $(".searchLeftPane").html(leftPanelHtml);
   
}
function getVendorBySearch(pageNumber,pageSize,searchMode,searchValue){
	var response;
	var dataURL;
	if(vendorStatus==""){
		dataURL="getPartners?";
	}else{
		dataURL="getVendorsList?approveStatus="+partneSearchStatus+"&";
	}
	$.ajax({
        type : "POST",
        contentType : "application/json",
        url: dataURL+"pageNumber="+pageNumber+"&pageSize="+pageSize+"&searchMode=none&searchValue="+searchValue,
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
function loadSearchVendorsResp(data){
	if(vendorStatus==""){
		onPageLoad(data);
	}else{
		loadVendorsByStatusResp(data);
	}
	setPagination(data.objectMap.lastPage, selectedPage , maxVisiblePageNumbers);
}
function getApproveVendors()
{   
	selectedPage = 1;
	setPageSelected = true;
	vendorStatus="APPROVED";
	partneSearchStatus=$("#approveStatusId").val();
	var response=getVendorBySearch(1,pageSize,'none','none');
	loadSearchVendorsResp(response);
	setPagination(response.objectMap.lastPage, selectedPage , maxVisiblePageNumbers);
    /*submitWithParam('getVendors','approveStatusId','loadVendorsByStatusResp');*/
	/*submitWithThreeParam('getVendorsList',1,pageSize,'approveStatusId','loadVendorsByStatusResp');*/
}
function getClarifiedVendors()
{
	selectedPage = 1;
	setPageSelected = true;
	vendorStatus="CLARIFIED";
	partneSearchStatus=$("#clarifyStatusId").val();
	var response=getVendorBySearch(1,pageSize,'none','none');
	loadSearchVendorsResp(response);
	setPagination(response.objectMap.lastPage, selectedPage , maxVisiblePageNumbers);
	/*submitWithParam('getVendors','clarifyStatusId','loadVendorsByStatusResp');*/
	/*submitWithThreeParam('getVendorsList',1,pageSize,'clarifyStatusId','loadVendorsByStatusResp');*/
}
function getRejectedVendors()
{
	selectedPage = 1;
	setPageSelected = true;
	vendorStatus="REJECTED";
	partneSearchStatus=$("#rejectStatusId").val();
	var response=getVendorBySearch(1,pageSize,'none','none');
	loadSearchVendorsResp(response);
	setPagination(response.objectMap.lastPage, selectedPage , maxVisiblePageNumbers);
	/*submitWithParam('getVendors','rejectStatusId','loadVendorsByStatusResp');*/
	/*submitWithThreeParam('getVendorsList',1,pageSize,'rejectStatusId','loadVendorsByStatusResp');*/
}
function loadVendorsByStatusResp(data)
{
	
	if(!setPageSelected){
		selectedPage = 1;
		setPageSelected = false;
	}
	$('#pagination-here').empty();
	
	loadLeftPane(data.objectMap.partners);
	setActiveTabName("Company Details",$('.leftPaneData li').length);
	
	if($('.leftPaneData li').length==0){
		$("#companyDetails #bPartnerId").val('');
		$("#companyDetails")[0].reset();
		$(".nonFilterTab").addClass('readonly');
	}else{
		$(".nonFilterTab").removeClass('readonly');
	}
	
		setPagination(data.objectMap.lastPage,selectedPage,3);
	
}
function getPartnerDetails(event,el)
{
		event.preventDefault();	
		$('.pagination-container').remove();
		if(!editMode && !requiredFileDeleted){
		    checkForFilterOnTabLoad();
			cacheLi();
			setCurrentTab(el);
			if(getChangedFlag()){
			if(vendorStatus=="")
				{
				    getPartnerInfo();
				}else  if(vendorStatus=="APPROVED"){
  				    /*submitWithParam('getVendors','approveStatusId','loadVendorsByStatusResp');*/
					submitWithThreeParam('getVendorsList',1,pageSize,'approveStatusId','loadVendorsByStatusResp');
				}else if(vendorStatus=="REJECTED"){
					/*submitWithParam('getVendors','rejectStatusId','loadVendorsByStatusResp');*/
					submitWithThreeParam('getVendorsList',1,pageSize,'rejectStatusId','loadVendorsByStatusResp');
				}else if(vendorStatus=="CLARIFIED"){
					/*submitWithParam('getVendors','clarifyStatusId','loadVendorsByStatusResp');*/
					submitWithThreeParam('getVendorsList',1,pageSize,'clarifyStatusId','loadVendorsByStatusResp');
				}
			   setChangedFlag(false);
			}else{
				 $('#pagination-here').bootpag({
				    	total: partnerLastPage,          
				    	page: selectedPage,            
				    	maxVisible: 3,     
				    	leaps: true,
				    	href: "#result-page-{{number}}",
				    	pageNumbers: true
				    });
				getCacheLi();
			}
			setActiveTabName("Company Details",$('.leftPaneData li').length);
			setPartnerHeaderValues("Company Name: "+companyName, "Company Registration No. : "+registrationNo, "Vendor SAP Code : "+vendorSAPCode,"Status : "+partnerStatus);
		}else{
			event.stopPropagation();
			Alert.warn("Please Save Changed Data Of "+activeTabName+" Tab");
		}
		var partnerData =  $("#partnerData").val();
		if(partnerData=='partnerRegistration'){
			$('.leftPaneData').paginathing();
		}
		if($('.leftPaneData li').length==0){
			$("#companyDetails #bPartnerId").val('');
			$("#companyDetails")[0].reset();
			$(".nonFilterTab").addClass('readonly');
		}else{
			$(".nonFilterTab").removeClass('readonly');
		}
}
function getPartnerForSubmit(event,el)
{
	    event.preventDefault();	
		if(!editMode && !requiredFileDeleted){
			$('#pagination-here').empty();
			
			
			/*if(getChangedFlag()){
			*/
			
			var ptrId=$("#companyDetails #bPartnerId").val();
			var responseData=fetchList('getPartnerDetails',ptrId);
			 /*submitWithParam('getPartnerDetails','bPartnerId','onConfirmationTabLoad');*/
			 onConfirmationTabLoad(responseData,event,el);
			 
			/*}else{
				 $('#pagination-here').bootpag({
				    	total: partnerLastPage,          
				    	page: selectedPage,            
				    	maxVisible: 3,     
				    	leaps: true,
				    	href: "#result-page-{{number}}",
				    	pageNumbers: true
				    });
				getCacheLi();
			}*/
			 
			setActiveTabName("Confirmation",$('.leftPaneData li').length);
			setPartnerHeaderValues("Company Name: "+companyName, "Company Registration No. : "+registrationNo, "Vendor SAP Code : "+vendorSAPCode,"Status : "+partnerStatus);
		}else{
			event.stopPropagation();
			Alert.warn("Please Save Changed Data Of "+activeTabName+" Tab");
		}
		$("#filterBtnId").addClass("readonly");
	    checkForFilterByRole();
}
function showFactoryInspectionTab()
{
	var role=$("#roleData").val();
	var partnerData=$("#partnerData").val();
	 if((role=='EXEENGR' || role=='CHFENGR') && partnerData=="partnerProfiles")
	 {
	     $("#factoryInspectectionTabId").css("display","inline-block");
	 }else if(partnerData=="partnerRegistration"){
		 $("#factoryInspectectionTabId").css("display","none");
	 }
	
}

function getPartnerInfo(){
	vendorStatus="";
	partneSearchStatus='';
	var partnerData =  $("#partnerData").val();
	if(partnerData == 'partnerProfiles'){
		/*submitToURL("getPartners", 'onPageLoad');*/
		vendorStatus="";
		partneSearchStatus='';
		var response=getVendorBySearch(1,pageSize,'none','none');
		loadSearchVendorsResp(response);		
	}else{
		submitToURL("getPartner", 'onPageLoad');
		
	}
	return false;
}

function processCompanyDetailResponse(data){
	
	  if(!isEmpty(data)){ 
	   var hasError=data.response.hasError==null?'':data.response.hasError;
		  if(hasError)
		{
		
		     if(!isEmpty(data.response.errors)){
		        var errorList=data.response.errors;
		        var errorLog='Following Filled Are Mandatory: ';
				   $.each(errorList,function(key,value){
					   errorLog=errorLog+value.errorMessage+'\n'+',';
				       
				   });
				  
		        Alert.warn(errorLog);
		     }else{
		    	 Alert.warn(data.response.message);
		     }
		}else{
			
			editMode=false;
			activeTabName="";
			requiredFileDeleted=false;
			Alert.info(data.response.message);
			companyName=data.name==null?'':data.name;
			registrationNo=data.crnNumber==null?'':data.crnNumber;
			panNo=data.panNumber==null?'':data.panNumber;
		    companyType=data.companyType==null?'':data.companyType;
		    vendorSAPCode=data.vendorSapCode==null?'':data.vendorSapCode;
		    if(vendorSAPCode==""){
		    	vendorSAPCode="Not Generated";
		    }
		    var status=data.status==null?'':data.status;
		    var isEEApproved=data.isEEApproved==null?'':data.isEEApproved;
		    var isCEApproved=data.isCEApproved==null?'':data.isCEApproved;
		    var partnerData=$("#partnerData").val();
		    if(partnerData=="partnerRegistration")
		     {
		    	partnerStatus=showPartnerStatusAlert(data);
		    	$("#companyDetails  .approveCEDiv").hide();
				$("#companyDetails  .approveEEDiv").hide();
		     }
		    setPartnerHeaderValues("Company Name: "+companyName, "Company Registration No. : "+registrationNo, "Vendor SAP Code : "+vendorSAPCode,"Status : "+partnerStatus);
			if(data.panCardCopy!=null)
				{
				  data.panCardCopy.fileName=$("#companyDetails #a_panCardCopy").html();
					
				}
			if(data.gstinCopy!=null)
			{
			  data.gstinCopy.fileName=$("#companyDetails #a_GSTINCopy").html();
				
			}
			if(data.companyRegCertificate!=null)
			{
			  data.companyRegCertificate.fileName=$("#companyDetails #a_companyRegCertificate").html();
				
			}
			if(data.partnershipDEEDCopy!=null)
			{
			  data.partnershipDEEDCopy.fileName=$("#companyDetails #a_partnershipDEEDCopy").html();
				
			}
			partnerArray["partner"+data.bPartnerId]=data;
		}
	  }
}
function loadOfficeType(data){

		$("#companyDetails #officeType").html("");
		var options = '<option value=""></option>';
		if(!isEmpty(data)){
		$.each(data,function(key,value){
			options +='<option value="'+value.locationTypeId+'" data-code="'+value.code+'">'+value.name +'</option>'
		});
		}
		$("#companyDetails #officeType").append(options);
		
}
function loadOfficeLocation(){
	
	var code=$("#companyDetails #officeType option:selected").data('code');
	if(code==undefined || code==''){
		/*Alert.warn("Invalid Office Type Selected");*/
	}else{
		submitToURL('getOfficeLocation/'+code,'loadOfficeLocationResp');
	}
}
function loadOfficeLocationResp(data){

	$("#companyDetails #officeLocation").html("");
	var options = '<option value=""></option>';
	if(!isEmpty(data)){
	$.each(data,function(key,value){
		options +='<option value="'+value.officeLocationId+'">'+value.name +'</option>'
	});
	}
	$("#companyDetails #officeLocation").append(options);
	
}
function onPageLoad(data){

	$('#pagination-here').empty();
	if(!isEmpty(data) && !isEmpty(data.objectMap)){
	if(data.objectMap.hasOwnProperty('companyTypes')){
		loadReferenceListById('companyType',data.objectMap.companyTypes)
	}
	if(data.objectMap.hasOwnProperty('contractorTypes')){
		loadReferenceListById('contractorType',data.objectMap.contractorTypes)
	}
	if(data.objectMap.hasOwnProperty('officeType')){
		loadOfficeType(data.objectMap.officeType)
	}
	}
	    loadLeftPane(data.data);
		if(!isEmpty(data.data)){
		  setActiveTabName("Company Details",data.data.length);
		}
    /*$.each(data,function(key,value){
		if(key=='objectMap'){
			var responseMap = value;
			loadLeftPane(responseMap.partners);
			setActiveTabName("Company Details",responseMap.partners.length);
		}
		else
			setActiveTabName("Company Details",$('.leftPaneData li').length);
	});*/
    setCurrentTab($("#companyDetailsTabId"));
    setChangedFlag(false);
    /*if(data.objectMap.hasOwnProperty('paymentDetails')){
    	 showRejectedPaymentList(data.objectMap.paymentDetails);
	}*/
    if(data.objectMap.hasOwnProperty('paymentResponse')){
   	 showRejectedPaymentList(data.objectMap.paymentResponse);
	}
    if(data.objectMap.hasOwnProperty('partnerOrgs')){
    	  showOrgRenewalLabel(data.objectMap.partnerOrgs);
	}
    partnerLastPage = data.objectMap.lastPage==null?0:data.objectMap.lastPage;
    $('#pagination-here').bootpag({
    	total: partnerLastPage,          
    	page: selectedPage,            
    	maxVisible: 3,     
    	leaps: true,
    	href: "#result-page-{{number}}",
    	pageNumbers: true
    });
	
}
function checkForFilterByRole(){
	
	var partnerData =  $("#partnerData").val();
	/*var partnerCurrentStaus=$("#partnerCurrentStaus").val();*/
	if(partnerData == 'partnerProfiles'){
	   if(vendorStatus=="" && !partnerStatusCheck/*partnerCurrentStaus!="CO"*/ ){
		       var role=$("#roleData").val();
			    if(role == 'EXEENGR'){
			    	 $(".approveEEDiv").find('input:radio, textarea').removeClass('readonly');
			    	 $(".approveEEDiv").find('input:radio, textarea').css("background-color","#FFF");
			    	 $(".partnerOrgEEApproveDiv").find('input:radio, textarea').removeClass('readonly');
			    	 $(".partnerOrgEEApproveDiv").find('input:radio, textarea').css("background-color","#FFF");
			    	 $(".manufacturerEEApproveDiv").find('input:radio, textarea').removeClass('readonly');
			    	 $(".manufacturerEEApproveDiv").find('input:radio, textarea').css("background-color","#FFF");
			    }else if(role == 'CHFENGR'){
			    	 $(".approveCEDiv").find('input:radio, textarea').removeClass('readonly');
			    	 $(".partnerOrgCEApproveDiv").find('input:radio, textarea').removeClass('readonly');
			    	 $(".manufacturerCEApproveDiv").find('input:radio, textarea').removeClass('readonly');
			    	 $(".approveCEDiv").find('input:radio, textarea').css("background-color","#FFF");
			    	 $(".partnerOrgCEApproveDiv").find('input:radio, textarea').css("background-color","#FFF");
			    	 $(".manufacturerEEApproveDiv").find('input:radio, textarea').css("background-color","#FFF");
			    }
			    $(".partnerOrgActionBtn").removeAttr('disabled','disabled');
				$(".manufacturerActionBtn").removeAttr('disabled','disabled');
				$(".disableBtn").removeAttr('disabled','disabled');
			$("#payOnlinePtnrRegBtn").attr('disabled','disabled');
			$("#savePaymentBtnId").attr('disabled','disabled');
			$("#cancelPaymentBtnId").attr('disabled','disabled');
		    
		}else{
			$(".changeComment").addClass("readonly");
			$(".changeButton").addClass("readonly");
			$(".changeComment").css("background-color","#DADCE2");
			$(".changeButton").css("background-color","#DADCE2");
			$(".partnerOrgActionBtn").attr('disabled','disabled');
			$(".manufacturerActionBtn").attr('disabled','disabled');
			$(".disableBtn").attr('disabled','disabled');
			$("#payOnlinePtnrRegBtn").attr('disabled','disabled');
		}
	 }
}
function checkForFilterOnTabLoad(){
	
	var partnerData =  $("#partnerData").val();
	if(partnerData == 'partnerProfiles'){
		$("#filterBtnId").removeClass('readonly');
	    checkForFilterByRole();
	}else{
		$("#filterBtnId").addClass('readonly');
	}
}
function loadLeftPane(data){
   
	$('.pagination').children().remove();
	$(".leftPaneData").html("");
	/*checkForFilterOnTabLoad();*/
	
	if(isEmpty(data))
		{
		  $(".leftPaneData").html("No Records Found");
		  $("#bPartnerId").val('');
		  $("#companyDetails")[0].reset();
		  $(".nonFilterTab").addClass('readonly');
		  return;
		}else{
		  $(".nonFilterTab").removeClass('readonly');
		}
	var leftPanelHtml="";
	var i=0;
	var firstRow=null;
	if(!isEmpty(data)){
	$.each(data,function(key,value){
		partnerArray["partner"+value.bPartnerId]=value;
		var status=value.status==null?'':value.status;
		var isEEApproved=value.isEEApproved==null?'':value.isEEApproved;
		var isCEApproved=value.isCEApproved==null?'':value.isCEApproved;
		var bPartnerId= value.bPartnerId==null?'':value.bPartnerId;
		var name= value.name==null?'':value.name;
		var crnNumber=value.crnNumber==null?'':value.crnNumber;
		var panNumber=value.panNumber==null?'':value.panNumber;
		
		var ptrData=setPartnerStatus(value);
		var ptrClass=ptrData==null?'':ptrData.pClass;
		var ptrStatus=ptrData==null?'':ptrData.pStatus;
		if(i==0){
			firstRow = value;
			leftPanelHtml = leftPanelHtml + ' <li onclick="showPartnerDetails('+bPartnerId+')" id="'+bPartnerId+'" class="active">'
		}else{
			leftPanelHtml = leftPanelHtml + ' <li onclick="showPartnerDetails('+bPartnerId+')" id="'+bPartnerId+'">'
		}
		leftPanelHtml = leftPanelHtml +' <a href="#Master" data-toggle="tab">'
        +' <div class="col-md-12">'
        +'  <label class="col-xs-6 "> '+name+'</label>'
        +'	<label class="col-xs-6 ">'+crnNumber+'</label>'
        +' </div>'	
        +' <div class="col-md-12">'
        +'	<label class="col-xs-6">'+panNumber+'</label>'
	    +'	<label class="col-xs-6 '+ptrClass+'">'+ptrStatus+'</label>';
       /* if(value.gstinNo !=null){
        	leftPanelHtml = leftPanelHtml	+'	<label class="col-xs-6">'+value.gstinNo+'</label>';
		}*/
	
		leftPanelHtml = leftPanelHtml+' </div>'
        +' </a>'
        +' </li>';
		i++;
	});
	}
	$(".leftPaneData").html(leftPanelHtml);
	/*$('.leftPaneData').paginathing();*/
	
	loadRightPane(firstRow);
	 $(".tabs-left li a label").text(function(index, currentText) {
		    return currentText.substr(0, 20);
	 });
	
}
function setPartnerStatus(data){

	var ptrStatus="";
	var ptrClass="";
	var status=data==null?'':data.status==null?'':data.status;
	var isCEApproved=data==null?'':data.isCEApproved==null?'':data.isCEApproved;
	var isEEApproved=data==null?'':data.isEEApproved==null?'':data.isEEApproved;
	var isSetToFinalApproval=data==null?'':data.isSetToFinalApproval==null?'':data.isSetToFinalApproval;
	
	if(status=="RJ"){
		ptrStatus="Rejected";
		ptrClass="statusrejected";
	}else if(status=="CEC"){
		ptrStatus="CE Clarified";
		ptrClass="statusclerify";
	}else if(status=="CO" && isCEApproved=="Y"){
		ptrStatus="CE Approved";
		ptrClass="statusCEApproved";
	}else if(status=="IP" && isEEApproved=="Y" && (isSetToFinalApproval==null || isSetToFinalApproval=="")){
		ptrStatus="EE Approved";
		ptrClass="statusEEApproved";
	}else if(status=="IP" && isEEApproved=="Y" && isSetToFinalApproval=="Y" && (isCEApproved==null || isCEApproved=="")){
		ptrStatus="CE Final Approval Pending";
		ptrClass="statuspending";
	}else if(status=="EEC"){
		ptrStatus="EE Clarified";
		ptrClass="statusclerify";
	}else if(status=="DR" || status=="IP" || status=="EDIT" || status==""){
		ptrStatus="Pending";
		ptrClass="statuspending";
	}
	return {pStatus:ptrStatus, pClass:ptrClass};
}

function showPartnerDetails(id) {
	var partnerData=partnerArray["partner"+id];
	loadRightPane(partnerData);
}
function loadRightPane(data){

	editMode=false;
	partnerStatusCheck=false;
	/*activeTabName="";*/
	var panCardCopyId = data==null?'':data.panCardCopy == null ? '': data.panCardCopy.attachmentId == null ? '': data.panCardCopy.attachmentId;
	var panCardCopyName = data==null?'':data.panCardCopy == null ? '': data.panCardCopy.fileName == null ? '': data.panCardCopy.fileName;

	var GSTINCopyId =data==null?'':data.gstinCopy == null ? '': data.gstinCopy.attachmentId == null ? '': data.gstinCopy.attachmentId;
	var GSTINCopyName = data==null?'':data.gstinCopy == null ? '': data.gstinCopy.fileName == null ? '' : data.gstinCopy.fileName;

	var companyRegCertificateId = data==null?'':data.companyRegCertificate == null ? '': data.companyRegCertificate.attachmentId == null ? '': data.companyRegCertificate.attachmentId;
	var companyRegCertificateName = data==null?'':data.companyRegCertificate == null ? '': data.companyRegCertificate.fileName == null ? '' : data.companyRegCertificate.fileName;
	
	var partnershipDEEDCopyId = data==null?'':data.partnershipDEEDCopy == null ? '': data.partnershipDEEDCopy.attachmentId == null ? '': data.partnershipDEEDCopy.attachmentId;
	var partnershipDEEDCopyName = data==null?'':data.partnershipDEEDCopy == null ? '': data.partnershipDEEDCopy.fileName == null ? '' : data.partnershipDEEDCopy.fileName;
	
	companyName = data==null?'':data.name == null ? '':data.name;
	registrationNo = data==null?'':data.crnNumber==null?'':data.crnNumber;
	panNo= data==null?'':data.panNumber==null?'':data.panNumber;
	companyType=data==null?'':data.companyType==null?'':data.companyType;
    vendorSAPCode=data==null?'':data.vendorSapCode==null?'':data.vendorSapCode;
    if(vendorSAPCode==""){
    	vendorSAPCode="Not Generated";
    }
	var status=data==null?'':data.status==null?'':data.status;
	var eeComment=data==null?'':data.eeComment==null?'':data.eeComment;
	var isApproved=data==null?'':data.isApproved==null?'':data.isApproved;
	var isEEApproved=data==null?'':data.isEEApproved==null?'':data.isEEApproved;
	var ceComment=data==null?'':data.ceComment==null?'':data.ceComment;
	var isCEApproved=data==null?'':data.isCEApproved==null?'':data.isCEApproved;
	
	var eeCompDetailComment=data==null?'':data.eeCompDetailComment==null?'':data.eeCompDetailComment;
	var isEECompDetailApproved=data==null?'':data.isEECompDetailApproved==null?'':data.isEECompDetailApproved;
	var ceCompDetailComment=data==null?'':data.ceCompDetailComment==null?'':data.ceCompDetailComment;
	var isCECompDetailApproved=data==null?'':data.isCECompDetailApproved==null?'':data.isCECompDetailApproved;
	var isRenewed=data==null?'':data.isRenewed==null?'':data.isRenewed;
	var isTraderRenewed=data==null?'':data.isTraderRenewed==null?'':data.isTraderRenewed;
	var officeLocation=data==null?'':data.officeLocation==null?'':data.officeLocation.officeLocationId==null?'':data.officeLocation.officeLocationId;
	var officeType=data==null?'':data.officeType==null?'':data.officeType.locationTypeId==null?'':data.officeType.locationTypeId;
	var partnerCoSignCopyId = data==null?'':data.partnerCoSignCopy == null ? '': data.partnerCoSignCopy.attachmentId == null ? '': data.partnerCoSignCopy.attachmentId;
	var partnerCoSignCopyName = data==null?'':data.partnerCoSignCopy == null ? '': data.partnerCoSignCopy.fileName == null ? '': data.partnerCoSignCopy.fileName;
	var isRegCompleted=data==null?'':data.isRegCompleted==null?'':data.isRegCompleted;
	var isSetToFinalApproval=data==null?'':data.isSetToFinalApproval==null?'':data.isSetToFinalApproval;
	
	var partnerData =  $("#partnerData").val();
	if(isRegCompleted=="Y" && partnerCoSignCopyId!="" && partnerData=="partnerRegistration" ){
	    $("#vendorCoSignedFileDiv").show();
	    var url = $("#downloadCoSignFile").data('url');
		var coSignedURL=url+partnerCoSignCopyId;
		$("#companyDetails #downloadCoSignFile").prop('href',coSignedURL);
		$("#companyDetails #downloadCoSignFile").html(partnerCoSignCopyName);
	}else{
	    $("#vendorCoSignedFileDiv").hide();
	}
	var Name=data==null?'':data.name==null?'':data.name;
	var PanNumber=data==null?'':data.panNumber==null?'':data.panNumber;
	var CrnNumber=data==null?'':data.crnNumber==null?'':data.crnNumber;
	var GSTINNo=data==null?'':data.gstinNo==null?'':data.gstinNo;
	var businessPartnerId=data==null?'':data.bPartnerId==null?'':data.bPartnerId;
	var compType=data==null?'':data.companyType==null?'':data.companyType;
	var contractType=data==null?'':data.contractorType==null?'':data.contractorType;
	$('#partner_CompType').val(companyType);
	$("#labelName").html(Name);
	$("#labelPanNumber").html(PanNumber);
	$("#labelCrnNumber").html(CrnNumber);
	$("#labelGstinNo").html(GSTINNo);	
	$("#name").val(Name);
	$("#bPartnerId").val(businessPartnerId);
	$(".bPartnerId").val(businessPartnerId);
	$("#companyType").val(compType);
	$("#contractorType").val(contractType);
	$("#crnNumber").val(CrnNumber);
	$("#panNumber").val(PanNumber);
	$("#gstinNo").val(GSTINNo);
	$("#companyDetails #panCardCopy").val(panCardCopyId);
	$("#companyDetails #companyRegCertificate").val(companyRegCertificateId);
	$("#companyDetails #GSTINCopy").val(GSTINCopyId);
	$("#companyDetails #partnershipDEEDCopy").val(partnershipDEEDCopyId);
	$("#companyDetails #officeType").val(officeType);
	$("#partnerCurrentStaus").val(status);
	loadOfficeLocation();
	$("#companyDetails #officeLocation").val(officeLocation);

	var rating=data==null?'':data.rating==null?'':data.rating;
	setStarRating('rating',rating);
	setAttribute("isGstApplicable", data.isGstApplicable);
	gstNumberShow(data.isGstApplicable);
	setAttribute("isCustomer", data.isCustomer);
	setAttribute("isContractor",data.isContractor);
	showContrctorType(data.isContractor);
	setAttribute("isManufacturer", data.isManufacturer);
	setAttribute("isTrader",data.isTrader);
	setAttribute("isInfra", data.isInfra);
	showFactoryDetailTab(data.isManufacturer);
	showManufacturerTab(data.isTrader);
	showVendorPaymentTab(data.isManufacturer,data.isTrader,data.isInfra);
	changeFieldsForPartnerStatus(data);
	/*changeFormFieldName();*/
	showFactoryInspectionTab();
	showPartnershipCopy();
	var url = $("#a_panCardCopy").data('url');
	$("#a_panCardCopy").attr('href', url);
	var a_panCardCopy = $("#companyDetails #a_panCardCopy").prop('href') + '/'+ panCardCopyId;
	$("#companyDetails #a_panCardCopy").prop('href', a_panCardCopy);
	$("#companyDetails #a_panCardCopy").html(panCardCopyName);

	var url = $("#a_GSTINCopy").data('url');
	$("#a_GSTINCopy").attr('href', url);
	var a_GSTINCopy = $("#companyDetails #a_GSTINCopy").prop('href') + '/'+ GSTINCopyId;
	$("#companyDetails #a_GSTINCopy").prop('href', a_GSTINCopy);
	$("#companyDetails #a_GSTINCopy").html(GSTINCopyName);

	var url = $("#a_partnershipDEEDCopy").data('url');
	$("#a_partnershipDEEDCopy").attr('href', url);
	var a_partnershipDEEDCopy = $("#companyDetails #a_partnershipDEEDCopy").prop('href') + '/'+ partnershipDEEDCopyId;
	$("#companyDetails #a_partnershipDEEDCopy").prop('href', a_partnershipDEEDCopy);
	$("#companyDetails #a_partnershipDEEDCopy").html(partnershipDEEDCopyName);
	
	var url = $("#a_companyRegCertificate").data('url');
	$("#a_companyRegCertificate").attr('href', url);
	var a_companyRegCertificate = $("#companyDetails #a_companyRegCertificate").prop('href') + '/'+ companyRegCertificateId;
	$("#companyDetails #a_companyRegCertificate").prop('href', a_companyRegCertificate);
	$("#companyDetails #a_companyRegCertificate").html(companyRegCertificateName);
	
	/*showCEFileDiv();*/
	$(".approveCEDiv").hide();
    $(".approveEEDiv").hide();
	showPartnerFileDeleteBtn(panCardCopyId, GSTINCopyId,companyRegCertificateId,partnershipDEEDCopyId);
	changeCommentAndStatusByRole('companyDetails',isEECompDetailApproved,eeCompDetailComment,isCECompDetailApproved,ceCompDetailComment);
    partnerStatus=showPartnerStatusAlert(data);
	setPartnerHeaderValues("Company Name: "+companyName, "Company Registration No. : "+registrationNo, "Vendor SAP Code : "+vendorSAPCode,"Status : "+partnerStatus);
	setChildLoadFlag(true);
	$("#partnerRenewalLabel").html("");
	if(isTraderRenewed=="N" || isRenewed=="N"){
		$("#partnerRenewalStatusDiv").css('display','block');
	    $("#partnerRenewalLabel").html("Vendor is expired");
	}
	var role=$("#roleData").val();
	if(partnerData=="partnerProfiles" && role=="CHFENGR"){
		if(isSetToFinalApproval=="Y" && status=="IP" && isEEApproved=="Y" && isCEApproved==""){
			partnerStatusCheck=true;
			/*checkForFinalApproval();*/
		}
	}
	
	checkForFilterByRole();
	setBlacklistingStatus(isApproved);
}
function checkForFinalApproval(){
	$(".changeComment").addClass("readonly");
	$(".changeButton").addClass("readonly");
	$(".changeComment").css("background-color","#DADCE2");
	$(".changeButton").css("background-color","#DADCE2");
	$(".partnerOrgActionBtn").attr('disabled','disabled');
	$(".manufacturerActionBtn").attr('disabled','disabled');
	$(".disableBtn").attr('disabled','disabled');
	$("#payOnlinePtnrRegBtn").attr('disabled','disabled');
}
function showPartnershipCopy()
{
	var type=$("#companyDetails #companyType").val();
	if(type=="PARTNERSHIP")
		{
		   $("#partnershipDEEDDivId").css('display','block');
		   $("#partnershipDEEDCopyId").addClass("requiredFile");
		   $("#partnershipDEEDCopy").removeAttr('disabled','disabled');
		}else{
			$("#partnershipDEEDDivId").css('display','none');
		    $("#partnershipDEEDCopyId").removeClass("requiredFile");
			$("#partnershipDEEDCopy").attr('disabled','disabled');
		}
}
function showOrgRenewalLabel(partnerOrgs)
{
	
	$("#partnerOrgRenewalLabel").html("");
	var msg="Following Factories are expired: ";
	if(!isEmpty(partnerOrgs)){
	$.each(partnerOrgs,function(key,value){
		var isRenewed=value.isRenewed==null?'':value.isRenewed;
		var name=value.name==null?'':value.name;
		if(isRenewed=="N")
			{
			  $("#partnerOrgRenewalStatusDiv").css('display','block');
			    msg=msg+name+",";
			  $("#partnerOrgRenewalLabel").html(msg);
			}
	});
	}
}
function showRejectedPaymentList(response){
	$("#paymentStatusLabel").empty();
	$("#paymentStatusDiv").css('display','none');
	var hasError=response==null?'':response.hasError==null?'':response.hasError;
	if(hasError){
			     $("#paymentStatusDiv").css('display','block');
			     var msg=response.message==null?'':response.message;
			     if(!isEmpty(response) && !isEmpty(response.errors)){
			     $.each(response.errors,function(key,value){
				       msg=msg+'\n'+value.errorMessage+',';
				       
				 });
				 $("#paymentStatusLabel").html(msg);    
		}
	}
}
/*function showRejectedPaymentList(payments)
{
	$("#paymentStatusLabel").empty();
	$("#paymentStatusDiv").css('display','none');
	var msg="Following Payments Are Rejected: ";
	var type;
	$.each(payments,function(key,value){
		var isFOApproved=value.isFOApproved==null?'':value.isFOApproved;
		var isFAApproved=value.isFAApproved==null?'':value.isFAApproved;
		if(isFOApproved=="N" || isFAApproved=="N"){
			$("#paymentStatusDiv").css('display','block');
			if(value.paymentType.code=="RG")
				{
				  type="Registration Fee";
				}
			if(value.paymentType.code=="RN")
				{
				  type="Registration Renewal Fee";
				}
			msg=msg+type+"-"+value.referenceNo+",";
			$("#paymentStatusLabel").html(msg);
		}
	});
	
}*/
function showPartnerStatusAlert(data){
		var status=data==null?'':data.status==null?'':data.status;
		var isEEApproved=data==null?'':data.isEEApproved==null?'':data.isEEApproved;
		var isSetToFinalApproval=data==null?'':data.isSetToFinalApproval==null?'':data.isSetToFinalApproval;
		
	if (status == 'IP' && (isEEApproved == "" || isEEApproved == null)) {
		
		return "Submitted";
	} else if (status == 'DR' ) {
		
		return "Drafted";
	} else if (status == "EDIT") {
		
		return "Editing";
	} else if (status == "CO") {
		
		return "Approved";
	}else if (status == "RJ") {
	    
		return "Rejected";
	}else if (status == "EEC") {
		
		return "Need Clarification By Executive Engineer";
	} else if (status == "CEC") {
		
		return "Need Clarification By Chief Engineer";
	} else if (status == 'IP' && isEEApproved == "Y" && (isSetToFinalApproval==null || isSetToFinalApproval=="")) {
		
		return "Approved By Executive Engineer";
	}else if(status==""){
		return "Null";
	}else if(isSetToFinalApproval=="Y" && status=="IP"){
		return "CE Final Approval Pending";
	}
}

function showContrctorType(isContractor)
{
	if(isContractor=="N")
	{
		$("#contractorTypeDiv").hide();
		$("#contractorType").attr('disabled','disabled');
	}
	else if(isContractor=="Y" || $("#isContractor").prop(":checked")){
		$("#contractorTypeDiv").show();
		$("#contractorType").removeAttr('disabled','disabled');		
	}
}
function showPartnerFileDeleteBtn(panCardAttachmentId, GSTINAttachmentId,certificateAttachmentId,partnershipDEEDCopyId) {

	if (panCardAttachmentId!="") {
		$(".panCardCopy").attr('disabled', false);
	} else {
		$(".panCardCopy").attr('disabled', true);
	}
	if (GSTINAttachmentId!="") {
		$(".GSTINCopy").attr('disabled', false);
	} else {
		$(".GSTINCopy").attr('disabled', true);
	}
	if (certificateAttachmentId != '') {
		$(".companyRegCertificate").attr('disabled', false);
	} else {
		$(".companyRegCertificate").attr('disabled', true);
	}
	if (partnershipDEEDCopyId != '') {
		$(".partnershipDEEDCopy").attr('disabled', false);
	} else {
		$(".partnershipDEEDCopy").attr('disabled', true);
	}
}
function showCEFileDiv()
{
	var role=$("#roleData").val();
	if(role=="CHFENGR")
		{
		   $(".ceSignCopyDiv").removeClass("readonly");
		   $(".ceSignCopyDivClass").css('display','block');
		   /*$(".ceSignCopyDivClass").show();*/
		   $("#ceSignCopyId").addClass("requiredFile");
		}else{
		   $(".ceSignCopyDivClass").css('display','none');
		   /*$(".ceSignCopyDivClass").hide();*/
		   $("#ceSignCopyId").removeClass("requiredFile");
		}
}
function showPartnerRegistrationFiles(data)
{
	var ceSignCopyId = data==null?'':data.ceSignCopy == null ? '': data.ceSignCopy.attachmentId == null ? '': data.ceSignCopy.attachmentId;
	var ceSignCopyName = data==null?'':data.ceSignCopy == null ? '': data.ceSignCopy.fileName == null ? '': data.ceSignCopy.fileName;
	var partnerSignCopyId = data==null?'':data.partnerSignCopy == null ? '': data.partnerSignCopy.attachmentId == null ? '': data.partnerSignCopy.attachmentId;
	var partnerSignCopyName = data==null?'':data.partnerSignCopy == null ? '': data.partnerSignCopy.fileName == null ? '': data.partnerSignCopy.fileName;
	var partnerCoSignCopyId = data==null?'':data.partnerCoSignCopy == null ? '': data.partnerCoSignCopy.attachmentId == null ? '': data.partnerCoSignCopy.attachmentId;
	var partnerCoSignCopyName = data==null?'':data.partnerCoSignCopy == null ? '': data.partnerCoSignCopy.fileName == null ? '': data.partnerCoSignCopy.fileName;
	var isRegCompleted=data==null?'':data.isRegCompleted==null?'':data.isRegCompleted;
	$("#partnerDetailsApprovalForm #ceSignCopy").val(ceSignCopyId);
	var url = $("#a_ceSignCopy").data('url');
	$("#a_ceSignCopy").attr('href', url);
	var a_ceSignCopy = $("#partnerDetailsApprovalForm #a_ceSignCopy").prop('href') + '/'+ ceSignCopyId;
	$("#partnerDetailsApprovalForm #a_ceSignCopy").prop('href', a_ceSignCopy);
	$("#partnerDetailsApprovalForm #a_ceSignCopy").html(ceSignCopyName);
	
	$("#partnerDetailsApprovalForm #partnerSignCopy").val(partnerCoSignCopyId);
	var url = $("#a_partnerSignCopy").data('url');
	$("#a_partnerSignCopy").attr('href', url);
	var a_partnerSignCopy = $("#partnerDetailsApprovalForm #a_partnerSignCopy").prop('href') + '/'+ partnerCoSignCopyId;
	$("#partnerDetailsApprovalForm #a_partnerSignCopy").prop('href', a_partnerSignCopy);
	$("#partnerDetailsApprovalForm #a_partnerSignCopy").html(partnerCoSignCopyName);
	
	$("#partnerDetailsApprovalForm #partnerCoSignCopy").val(partnerCoSignCopyId);
	var url = $("#a_partnerCoSignCopy").data('url');
	$("#a_partnerCoSignCopy").attr('href', url);
	var a_partnerCoSignCopy = $("#partnerDetailsApprovalForm #a_partnerCoSignCopy").prop('href') + '/'+ partnerCoSignCopyId;
	$("#partnerDetailsApprovalForm #a_partnerCoSignCopy").prop('href', a_partnerCoSignCopy);
	$("#partnerDetailsApprovalForm #a_partnerCoSignCopy").html(partnerCoSignCopyName);
	
	$("#partnerCoSignCopy").attr('disabled','disabled');
	$("#partnerCoSignCopyId").removeClass("requiredFile");
	/*$(".ceSignCopyDiv").removeClass("readonly");*/
   /* showCEFileDiv();*/
	$("#ceSignedFileDiv").css('display','none');
	var partnerData =  $("#partnerData").val();
	var status= data==null?'':data.status==null?'':data.status;
	var isContractor=data==null?'':data.isContractor==null?'':data.isContractor;
	var isCustomer=data==null?'':data.isCustomer==null?'':data.isCustomer;
	var isManufacturer=data==null?'':data.isManufacturer==null?'':data.isManufacturer;
	var isTrader=data==null?'':data.isTrader==null?'':data.isTrader;
	var isCEApproved=data==null?'':data.isCEApproved==null?'':data.isCEApproved;
	if((status=="DR" || status=="EDIT") && partnerData=="partnerRegistration" && (isContractor=="Y" || isCustomer=="Y")){
		$("#partnerSignFileDivId").css('display','block');
		$("#partnerSignCopyId").addClass("requiredFile");
		$("#partnerSignCopy").removeAttr("disabled",'disabled');
		showVendorRegFileDelBtn(partnerCoSignCopyId);
	}else{
		$("#partnerSignFileDivId").css('display','none');
		$("#partnerSignCopyId").removeClass("requiredFile");
		$("#partnerSignCopy").attr("disabled",'disabled');
	}
	if((status=="CO" && isCEApproved=="Y") && (isRegCompleted=="N" || isRegCompleted=="") && partnerData=="partnerRegistration" && (isManufacturer=="Y" || isTrader=="Y"))
		{
		  
		  /*$(".ceSignCopyDiv").addClass("readonly");*/
		  /*$(".ceSignCopyDivClass").show();*/
		    $(".ceSignCopyDivClass").css('display','none');
		    var downloadURL =$("#a_ceSignedDownloadFile").data('url');
		    var downloadCeSignedURL=downloadURL+"/"+ceSignCopyId;
		    $("#a_ceSignedDownloadFile").prop('href',downloadCeSignedURL);
		    $("#a_ceSignedDownloadFile").html(ceSignCopyName);
		  /* $("#cosignLabel").text("DownLoad Sign File");
		  $(".ceSignCopyDiv").hide();*/
		  $("#ceSignedFileDiv").css('display','block');
		  $("#partnerCoSignCopyId").addClass("requiredFile");
		  $("#partnerCoSignCopy").removeAttr('disabled','disabled');
		  $("#partnerCoSignCopyDivId").css("display","block");
		  $("#partnerCoSignCopyDivId").removeClass("readonly");
		  $("#partnerRegCompleteDivId").css("display","block");
		  $("#partnerSubmitRespDivId").hide();
		  $("#partnerDetailsApprovalForm #partnerCoSignCopy").val("");
		  $("#partnerDetailsApprovalForm #a_partnerCoSignCopy").html("");
		}
	 if((partnerCoSignCopyId!="" && isRegCompleted=="Y")&& (status=="CO" && isCEApproved=="Y") && partnerData=="partnerRegistration")
		 {
		    $("#partnerSubmitRespDivId").show();
			$("#submitRespLabelId").html("Your Registration Process Completed!");
			$(".partnerCoSignCopyDiv").addClass("readonly");
			$("#partnerRegCompleteDivId").hide();
		 }
	 var role=$("#roleData").val();
	 /*if(data.status=="CO" && data.isCEApproved=="Y" && partnerData=="partnerProfiles"){*/
	 var finalApproval=data==null?'':data.isSetToFinalApproval==null?'':data.isSetToFinalApproval;
	 if(finalApproval=="Y" && status=="IP" && partnerData=="partnerProfiles"){
		 if(role=="CHFENGR")
			{
			   $("#submitRespLabelId").hide();
			   $(".ceSignCopyDiv").removeClass("readonly");
			   $(".ceSignCopyDivClass").css('display','block');
			   /*$(".ceSignCopyDivClass").show();*/
			   $("#ceSignCopyId").addClass("requiredFile");
			}
     }else{ 
    	 if(role=="CHFENGR")
			{
    		   $(".ceSignCopyDivClass").css('display','none');
    		  /* $(".ceSignCopyDivClass").hide();*/
			   $("#ceSignCopyId").removeClass("requiredFile");
			}
     }	
	 showPartnerRegistrationFileDelBtn(partnerCoSignCopyId,ceSignCopyId,partnerCoSignCopyId);
}
function showVendorRegFileDelBtn(partnerSignCopyId){
		if(partnerSignCopyId!=""){
			$(".partnerSignCopy").attr('disabled', false);
		}else{
			$(".partnerSignCopy").attr('disabled', true);
		}
}
function showPartnerRegistrationFileDelBtn(partnerSignCopyId,ceSignCopyId,partnerCoSignCopyId)
{
	if(partnerSignCopyId!=""){
		$(".partnerSignCopy").attr('disabled', false);
	}else{
		$(".partnerSignCopy").attr('disabled', true);
	}
	if(ceSignCopyId!=""){
		$(".ceSignCopy").attr('disabled', false);
	}else{
		$(".ceSignCopy").attr('disabled', true);
	}
	if(partnerCoSignCopyId!=""){
		$(".partnerCoSignCopy").attr('disabled', false);
	}else{
		$(".partnerCoSignCopy").attr('disabled', true);
	}
}
function changeFieldsForPartnerStatus(partnerStatus)
{
	var partnerData =  $("#partnerData").val();
	var role=$("#roleData").val();
		var status=partnerStatus==null?'':partnerStatus.status==null?'':partnerStatus.status;
		var isCEApproved=partnerStatus==null?'':partnerStatus.isCEApproved==null?'':partnerStatus.isCEApproved;
		var isEEApproved=partnerStatus==null?'':partnerStatus.isEEApproved==null?'':partnerStatus.isEEApproved;
		var isSetToFinalApproval=partnerStatus==null?'':partnerStatus.isSetToFinalApproval==null?'':partnerStatus.isSetToFinalApproval;
		
	if(partnerData == 'partnerProfiles' && role=="EXEENGR")
		  {
		     if((status=="IP" && (isEEApproved=="" || isEEApproved==null )) || (status=="CEC" && isEEApproved=="Y"))
		    	 {
		    	    $(".partnerTabs").addClass("readonly");
					$("#partnerEditBtnId").hide();
					$(".disableBtn").removeClass('readonly');
					$("#partnerDetailsApprovalForm #partnerApprovalDivId").show();
					$("#partnerDetailsApprovalForm #partnerSubmitRespDivId").hide();
					$("#partnerDetailsApprovalForm .partnerSubmitDiv").hide();
					/*$("#partnerDetailsApprovalForm #partnerSubmitDivId").hide();*/
		    	 }else if(status=="EEC" && isEEApproved=="C"){
		    		 var remarks=partnerStatus==null?'':partnerStatus.eeComment==null?'':partnerStatus.eeComment;
		    		    $(".partnerTabs").addClass("readonly");
		    		    $(".disableBtn").removeClass('readonly');
						$("#partnerEditBtnId").hide();
						$("#partnerDetailsApprovalForm #partnerSubmitRespDivId").show();
						$("#submitRespLabelId").html("Partner Clarified For: " + remarks);
						$("#partnerDetailsApprovalForm .partnerSubmitDiv").hide();
						$("#partnerDetailsApprovalForm #partnerApprovalDivId").hide();
		    	 }else  if((status=="IP" || status=="CO" || status=="RJ" )&& isEEApproved=="Y"){
		    		 var remarks='';
		    		 if(status=="IP" || status=="CO"){
		    			 remarks=partnerStatus==null?'':partnerStatus.eeComment==null?'':partnerStatus.eeComment;
		    		 }else if(status=="RJ"){
		    			 remarks=partnerStatus==null?'':partnerStatus.ceComment==null?'':partnerStatus.ceComment;
		    		 }
		    		$(".partnerTabs").addClass("readonly");
		 			$(".disableBtn").removeClass('readonly');
		 			$("#partnerEditBtnId").hide();
		 			$("#partnerDetailsApprovalForm #partnerSubmitRespDivId").show();
		 			$("#submitRespLabelId").html("Partner Approved: " +remarks) ;
		 			$("#partnerDetailsApprovalForm .partnerSubmitDiv").hide();
		 			$("#partnerDetailsApprovalForm #partnerApprovalDivId").hide();
		    	 }else if(status=="RJ" && isEEApproved=="N"){
		    		 var remarks=partnerStatus==null?'':partnerStatus.eeComment==null?'':partnerStatus.eeComment;
		    		 $(".partnerTabs").addClass("readonly");
		 			$(".disableBtn").addClass('readonly');
		 			$("#partnerEditBtnId").hide();
		 			$("#partnerDetailsApprovalForm #partnerSubmitRespDivId").show();
		 			$("#submitRespLabelId").html("Partner Rejected For: " + remarks);
		 			$("#partnerDetailsApprovalForm .partnerSubmitDiv").hide();
		 			$("#partnerDetailsApprovalForm #partnerApprovalDivId").hide();
		    	 }
		  }else if(partnerData == 'partnerProfiles' && role=="CHFENGR"){
			  if(status=="IP" && (isSetToFinalApproval==null|| isSetToFinalApproval=="") && (isCEApproved=="" || isCEApproved==null))
				  {
				    $(".partnerTabs").addClass("readonly");
					$("#partnerEditBtnId").hide();
					$(".disableBtn").removeClass('readonly');
					$("#partnerDetailsApprovalForm #partnerApprovalDivId").show();
					$("#partnerDetailsApprovalForm #partnerSubmitRespDivId").hide();
					$("#partnerDetailsApprovalForm .partnerSubmitDiv").hide();
				  }else if((status=="CO" && isCEApproved=="Y") || (status=="IP" && isSetToFinalApproval=="Y")){
					  var remarks=partnerStatus==null?'':partnerStatus.remarks==null?'':partnerStatus.remarks;
					    $(".partnerTabs").addClass("readonly");
			 			$(".disableBtn").removeClass('readonly');
			 			$("#partnerEditBtnId").hide();
			 			$("#partnerDetailsApprovalForm #partnerSubmitRespDivId").show();
			 			$("#submitRespLabelId").html("Partner Approved: " +remarks) ;
			 			$("#partnerDetailsApprovalForm .partnerSubmitDiv").hide();
			 			$("#partnerDetailsApprovalForm #partnerApprovalDivId").hide();
			 			
				  }else if(status=="CEC" && isCEApproved=="C"){
					  var remarks=partnerStatus==null?'':partnerStatus.ceComment==null?'':partnerStatus.ceComment;
					    $(".partnerTabs").addClass("readonly");
						$(".disableBtn").removeClass('readonly');
						$("#partnerEditBtnId").hide();
						$("#partnerDetailsApprovalForm #partnerSubmitRespDivId").show();
						$("#submitRespLabelId").html("Partner Clarified For: " + remarks);
						$("#partnerDetailsApprovalForm .partnerSubmitDiv").hide();
						$("#partnerDetailsApprovalForm #partnerApprovalDivId").hide();
				  }else if(status=="RJ" && isCEApproved=="N"){
					  var remarks=partnerStatus==null?'':partnerStatus.ceComment==null?'':partnerStatus.ceComment;
					    $(".partnerTabs").addClass("readonly");
			 			$(".disableBtn").addClass('readonly');
			 			$("#partnerEditBtnId").hide();
			 			$("#partnerDetailsApprovalForm #partnerSubmitRespDivId").show();
			 			$("#submitRespLabelId").html("Partner Rejected For: " + remarks);
			 			$("#partnerDetailsApprovalForm .partnerSubmitDiv").hide();
			 			$("#partnerDetailsApprovalForm #partnerApprovalDivId").hide();
				  }
		  }else if((status=='IP' || status=='CO' ||  status=='RJ' || status=='EEC' || status=='CEC') &&  partnerData!= 'partnerProfiles') 
		    {
			  $(".disableBtn").addClass('readonly');
			  $(".partnerTabs").addClass("readonly");
			  if(status=='CO' || status=='EEC')
				{
				  $("#partnerEditBtnId").show(); 
				}else{
				  $("#partnerEditBtnId").hide(); 
				}
			  
			$("#partnerDetailsApprovalForm #partnerSubmitRespDivId").show();
			$("#submitRespLabelId").html("Your Registration Profile Has Been Submitted !");
			$("#partnerDetailsApprovalForm .partnerSubmitDiv").hide();
			$("#partnerDetailsApprovalForm #partnerApprovalDivId").hide();
			$(".cancel").removeAttr("disabled","disabled");
		 }else if((status=='DR' || status=='EDIT' || status==null || status=='' )&&  partnerData!= 'partnerProfiles')
		   {
			    $("#partnerEditBtnId").hide();
				$(".disableBtn").removeClass('readonly');
				$(".disableBtn").removeAttr('disabled','disabled');
				$(".partnerTabs").removeClass("readonly");
				$("#partnerDetailsApprovalForm .partnerSubmitDiv").show();
				$("#partnerDetailsApprovalForm #partnerSubmitRespDivId").hide();
				$("#partnerDetailsApprovalForm #partnerApprovalDivId").hide();
				$("#partnerCoSignCopyDivId").css("display","none");
				$("#partnerRegCompleteDivId").css("display","none");
				$(".cancel").removeAttr("disabled","disabled");
		   }
			 
	   showPartnerRegistrationFiles(partnerStatus);
}

function showFactoryDetailTab(data){
	if(data=='Y')
		{
		  $("#factoryDetailTabId").show();
		  
		}
	else{
		$("#factoryDetailTabId").hide();
	}
	showVendorPaymentTab(data,null,null);
	
}
function showManufacturerTab(data)
{   
	if(data=='Y')
    {
		$("#manufacturerTabId").show();
		
    }else{
    	$("#manufacturerTabId").hide();
    	
    }
	showVendorPaymentTab(null,data,null);
	
}
function showVendorPaymentTab(isManufacturer,isTrader,isInfra)
{
	if(isManufacturer=='Y' || isTrader=="Y" || isInfra=="Y" ||  $("#isManufacturer"). prop("checked") == true || $("#isTrader"). prop("checked") == true || $("#isInfra"). prop("checked") == true)
    {
		$("#vendorPaymentTabId").show();
    }else{
    	$("#vendorPaymentTabId").hide();
    }
}


function gstnumShow(){
	debugger;
	 if ($('#isGstApplicable').is(":checked")){
	        $(".GSTidenNumb").show();
	        $('.defaultshow').show();
	       /* $("#gstinNo").addClass('requiredField');
	        $("#GSTINCopyId").addClass("requiredFile");*/
	        $(".GSTidenNumb").find('input').removeAttr('disabled','disabled');
	        $(".GSTidenNumb").find('input').removeClass('errorinput');
	    }else{
	        $(".GSTidenNumb").hide();
	    	/*$("#gstinNumber").val("");
	    	 $("#gstinNo").val("");*/
	    	 $('.defaultshow').hide();
            /* $("#gstinNo").removeClass('requiredField');
             $("#GSTINCopyId").removeClass("requiredFile");*/
             $(".GSTidenNumb").find('input').attr('disabled','disabled');
             $(".GSTidenNumb").find('input').val('');
             $("#a_GSTINCopy").html('');
	    }
}

function gstNumberShow(value) {
	debugger;
	 if ("Y"== value){
	     $(".GSTidenNumb").show();
	     /*$("#GSTINCopyId").addClass("requiredFile");*/
	     $(".GSTidenNumb").find('input').removeAttr('disabled','disabled');
	     $(".GSTidenNumb").find('input').removeClass('errorinput');
	 }else{
	     $(".GSTidenNumb").hide();
	    /* $("#gstinNumber").val("");*/
	    /* $("#GSTINCopyId").removeClass("requiredFile");*/
	     $(".GSTidenNumb").find('input').attr('disabled','disabled');
	     $(".GSTidenNumb").find('input').val('');
	     $("#a_GSTINCopy").html('');
	 }
}

function loadContractorType(data){
	$("#companyDetails #contractorType").html("");
	var options = '<option></option>';
	if(!isEmpty(data)){
	$.each(data,function(key,value){
		options +='<option value="'+value.code+'">'+value.name +'</option>'
		
	});
	}
	$("#companyDetails #contractorType").append(options);
	
}

function loadCompanyType(data){
	$("#companyDetails #companyType").html("");
	var options = '<option></option>';
	if(!isEmpty(data)){
	$.each(data,function(key,value){
		options +='<option value="'+value.code+'">'+value.name +'</option>'
		
	});
	}
	$("#companyDetails #companyType").append(options);
}

function partnerDetailsSubmitResp(data){

	$('#loading-wrapper').hide();
	if(isEmpty(data))
	{
	  return;
	}
	if(!data.hasError)
		{
		  /*getPartnerInfo();*/
			swal({
		          title: "Done",
		          text: data.message,
		          icon: "success",
		          type: "success",
		          dangerMode: true,
		          confirmButtonText:"Logout",
		        }).then(function(isConfirm) {
		          if (isConfirm) {
		        	  /*$("#newUserRegistrationForm")[0].reset();*/
		        	  window.location.href="/eatApp/logout"; 
		          } 
		        });
		}else{
				  var msg=data.message;
				/*var msg='Following Tabs Are Not Filled: ';*/
				 $.each(data.errors,function(key,value){
				       msg=msg+'\n'+value.errorMessage+'\n'+',';
				       
				   });
				Alert.warn(msg);
			    
		}
	}

function partnerProfileEditResp(data) {
	 /*&& !isEmpty(data.response.hasError)*/ 
	data.response.hasError
	if(!isEmpty(data) && !isEmpty(data.response) && !isEmpty(data.response.hasError)  ){
	   if (!data.response.hasError) {
		/*$('#loading-wrapper').hide();*/
		$("#loading-wrapper").fadeOut("slow");
		$("#partnerEditBtnId").hide();
		getPartnerInfo();
	   }
	}
}

function PanAttachmentDeleteResp(data) {
	if(!isEmpty(data)){
	if (!data.hasError) {
		$('#panCardCopyId').val('');
		$('#panCardCopy').val('');
		$("#a_panCardCopy").html('');
		$('.panCardCopy').attr('disabled', true);
		Alert.info(data.message);
		var bPartnerId=$("#bPartnerId").val();
		if(bPartnerId!="")
		{
		 partnerArray["partner"+bPartnerId].panCardCopy=null;
		}
	} else {
		Alert.warn(data.message);
	}
}
}



function GSTINAttachmentDeleteResp(data) {
if(!isEmpty(data)){
	if (!data.hasError) {
		$('#GSTINCopyId').val('');
		$('#GSTINCopy').val('');
		$("#a_GSTINCopy").html('');
		$('.GSTINCopy').attr('disabled', true);
		Alert.info(data.message);
		var bPartnerId=$("#bPartnerId").val();
		if(bPartnerId!="")
		{
		 partnerArray["partner"+bPartnerId].gstinCopy=null;
		}
	} else {
		Alert.warn(data.message);
	}
}
}
function companyRegCertDeleteResp(data) {
	if(!isEmpty(data)){
	if (!data.hasError) {
		$('#companyRegCopyId').val('');
		$('#companyRegCertificate').val('');
		$("#a_companyRegCertificate").html('');
		$('.companyRegCertificate').attr('disabled', true);
		Alert.info(data.message);
		var bPartnerId=$("#bPartnerId").val();
		if(bPartnerId!="")
		{
		  partnerArray["partner"+bPartnerId].companyRegCertificate=null;
		}
	} else {
		Alert.warn(data.message);
	}
	}
}

function changeFormFieldName()
{
    var role=$("#roleData").val();
    if(role=='EXEENGR')
    	{
    	  $(".changeComment").attr('name','eeComment');
    	  $(".changeButton").attr('name', 'isEEApproved');
    	  
    	}else if(role=='CHFENGR'){ 
    		 $(".changeComment").attr('name','ceComment');
    		 $(".changeButton").attr('name', 'isCEApproved');
    	}
}
function setApprovedStatus(formId,isApproved)
{

	if(isApproved=="Y")
		{
		  $("#"+formId+" #approveBtn").prop('checked',true);
		  $("#"+formId+" #clarification").prop('checked',false);
		}else if(isApproved=="C"){
			/*$("#"+formId+" #rejectBtn").attr('checked', 'checked');*/
		    $("#"+formId+" #clarification").prop('checked', true);
		    $("#"+formId+" #approveBtn").prop('checked',false);
		}
}
function setStatusButton(formId,isApproved,approveBtnId,rejectBtnId)
{
	
	if(isApproved=="Y" || isApproved=="")
		{
		  $("#"+formId+" #"+approveBtnId).prop('checked',true);
		  $("#"+formId+" #"+rejectBtnId).prop('checked',false);
		}else if(isApproved=="C"){
			$("#"+formId+" #"+rejectBtnId).prop('checked', true);
		    $("#"+formId+" #"+approveBtnId).prop('checked',false);
		}
}
function changeCommentAndStatusByRole(formId,isEEApproved,eeComment,isCEApproved,ceComment)
{
	  
	$("#"+formId+" .approveEEDiv").find('input:radio, textarea').removeClass('readonly');
	$("#"+formId+" .approveCEDiv").find('input:radio, textarea').removeClass('readonly');
	$("#"+formId+" .approveEEDiv").find('input:radio, textarea').css("background-color","#FFF");
	$("#"+formId+" .approveCEDiv").find('input:radio, textarea').css("background-color","#FFF");
	/*$("#"+formId+" .approveEEDiv").find('input:radio, textarea').removeAttr('disabled','disabled');
	$("#"+formId+" .approveCEDiv").find('input:radio, textarea').removeAttr('disabled','disabled');
   */ 
	$("#"+formId+" .approveCEDiv").hide();
    $("#"+formId+" .approveEEDiv").hide();
    
	 var role=$("#roleData").val();
	 var partnerData =  $("#partnerData").val();
	 if(role=='EXEENGR')
		 {
		    $("#"+formId+" .approveEEDiv").show();
		    $("#"+formId+" #eeRemark").val(eeComment);
		    /*$("#"+formId+" .approveCEDiv").find('input:radio, textarea').attr('disabled','disabled');*/
		    /*$("#"+formId+" .approveCEDiv").find('input:radio, textarea').addClass('readonly');
		    $("#"+formId+" .approveCEDiv").find('input:radio, textarea').css("background-color","#DADCE2");
		    */
		     $("#"+formId+" .approveCEDiv").find('input:radio, textarea').attr('disabled','disabled');
		     setStatusButton(formId,isEEApproved,'eeApproveBtn','eeClarification');
		     if(isCEApproved=='C' || isCEApproved=='Y')
		    	 {
		    	     $("#"+formId+" .approveCEDiv").show();
		    	     $("#"+formId+" .approveCEDiv").find('input:radio, textarea').removeAttr('disabled','disabled');
		    	     $("#"+formId+" .approveCEDiv").find('input:radio, textarea').addClass('readonly');
		    	     $("#"+formId+" .approveCEDiv").find('input:radio, textarea').css("background-color","#DADCE2");
			    	 $("#"+formId+" #ceRemark").val(ceComment);
				     setStatusButton(formId,isCEApproved,'ceApproveBtn','ceClarification');
			     }
		     
		 }else if(role=='CHFENGR'){
			 $("#"+formId+" .partnerOrgCEApproveDiv").find('input:radio, textarea').removeAttr('disabled','disabled');
			 $("#"+formId+" .approveCEDiv").show();
			 $("#"+formId+" #ceRemark").val(ceComment);
			 setStatusButton(formId,isCEApproved,'ceApproveBtn','ceClarification');
			 $("#"+formId+" .approveEEDiv").show();
			/* $("#"+formId+" .approveEEDiv").find('input:radio, textarea').attr('disabled','disabled');*/
			 $("#"+formId+" .approveEEDiv").find('input:radio, textarea').addClass('readonly');
			 $("#"+formId+" .approveEEDiv").find('input:radio, textarea').css("background-color","#DADCE2");
			 $("#"+formId+" #eeRemark").val(eeComment);
			 setStatusButton(formId,isEEApproved,'eeApproveBtn','eeClarification');
			 
		 }else if(partnerData=="partnerRegistration"){
			   /* $("#"+formId+" .approveEEDiv").find('input:radio, textarea').attr('disabled','disabled');
			    $("#"+formId+" .approveCEDiv").find('input:radio, textarea').attr('disabled','disabled');
			    */
			   $("#"+formId+" .approveEEDiv").find('input:radio, textarea').addClass('readonly');
			   $("#"+formId+" .approveCEDiv").find('input:radio, textarea').addClass('readonly');
			   $("#"+formId+" .approveEEDiv").find('input:radio, textarea').css("background-color","#DADCE2");
			   $("#"+formId+" .approveCEDiv").find('input:radio, textarea').css("background-color","#DADCE2");
			   $("#"+formId+" .approveCEDiv").hide();
			    /*if(isCEApproved!="")
			    {
			    	if(isCEApproved!="Y"){
			    	$("#"+formId+" .approveCEDiv").show();
                    setStatusButton(formId,isCEApproved,'ceApproveBtn','ceClarification');}
			    }*/
			    if(isEEApproved!="")
			    {
			    	if(isEEApproved=="C"){
			    	 $("#"+formId+" .approveEEDiv").show();
			    	 setStatusButton(formId,isEEApproved,'eeApproveBtn','eeClarification');}
			    }
			    $("#"+formId+" .remark").addClass('readonly');
		        $("#"+formId+" .statusBtn").addClass('readonly');
			    $("#"+formId+" #eeRemark").val(eeComment);
			    $("#"+formId+" #ceRemark").val(ceComment);
			 
		 }
}
function showSubmitLoader(event)
{
	   
	event.preventDefault();
	submitIt('partnerDetailsApprovalForm','submitPartnerDetails','partnerDetailsSubmitResp');
	/*submitWithParam('submitPartnerDetails','bPartnerId','partnerDetailsSubmitResp');*/
}
function submitApproval(event){
	
	event.preventDefault();
	submitIt('partnerDetailsApprovalForm','submitApproval','submitApprovalResp');
}
function submitApprovalResp(data){
	
	if(!isEmpty(data)){
	if(!data.hasError)
	{
		vendorStatus="";
		partneSearchStatus='';
		/*var response=getVendorBySearch(1,pageSize,'none','none');
		loadSearchVendorsResp(response);*/	
	$("#partnerDetailsApprovalForm #partnerSubmitRespDivId").hide();
	$("#partnerDetailsApprovalForm .partnerSubmitDiv").hide();
	$("#partnerDetailsApprovalForm #partnerApprovalDivId").hide();
    
	setChildLoadFlag(true);
	setChangedFlagById("companyDetailsTabId",true);
	selectedPage=1;
	swal({
        title: "Done",
        text: data.message,
        icon: "success",
        type: "success",
        dangerMode: true,
      }).then(function(isConfirm) {
        if (isConfirm) {
            $("#companyDetailsTabId").trigger('click');
        } 
      });
	}else
		{
		    var msg=data.message;
			$.each(data.errors,function(key,value){
		       msg=msg+'\n'+value.errorMessage+'\n'+',';
		       
		   });
		    Alert.warn(msg);
	}
	$('#loading-wrapper').hide();
}	
}
function showApproveLoader(event)
{
	event.preventDefault();
	submitIt('partnerDetailsApprovalForm','partnerRegistrationApproval','partnerApprovalResp');	
}
function partnerApprovalResp(data)
{
	  
	if(!isEmpty(data)){
	 if(!data.response.hasError)
	{
		vendorStatus="";
		partneSearchStatus='';
		
	$("#partnerDetailsApprovalForm #partnerSubmitRespDivId").hide();
	/*$("#submitRespLabelId").show();
	$("#submitRespLabelId").html(data.response.message);
	*/$("#partnerDetailsApprovalForm .partnerSubmitDiv").hide();
	$("#partnerDetailsApprovalForm #partnerApprovalDivId").hide();
	$("#partnerDetailsApprovalForm #remarks").val("");
		/*if("Y"==data.isCEApproved && "CO"==data.status){*/
	   if("Y"==data.isSetToFinalApproval && "IP"==data.status){
		  showCEFileDiv();
		}else{
			setChildLoadFlag(true);
			setChangedFlagById("companyDetailsTabId",true);
			selectedPage=1;
			swal({
		        title: "Done",
		        text: data.response.message,
		        icon: "success",
		        type: "success",
		        dangerMode: true,
		      }).then(function(isConfirm) {
		        if (isConfirm) {
		            $("#companyDetailsTabId").trigger('click');
		        } 
		      });
		}
	}else
		{
		   /*var msg='Please do clarification or rejection as you have not approved following tabs: ';*/
			var msg=data.response.message;
			$.each(data.response.errors,function(key,value){
		       msg=msg+'\n'+value.errorMessage+'\n'+',';
		       
		   });
		    Alert.warn(msg);
		
	}
	}
	$('#loading-wrapper').hide();
}
function getLoaderForEdit(event)
{
	
	event.preventDefault();
	$("#loading-wrapper").show();
	setTimeout(function(){
	submitWithParam('updatePartnerProfile','bPartnerId','partnerProfileEditResp');	
	});
}
function onConfirmationTabLoad(data,event,el)
{
	
	if(data==undefined){
		event.stopPropagation();
		Alert.warn("Something went wrong!");
		return;
	}
	var error=data==null?'':data.response==null?'':data.response.hasError==null?'':data.response.hasError;
	if(error && vendorStatus==""){
		event.stopPropagation();
		/*$(".ceSignCopyDivClass").hide();*/
		$(".ceSignCopyDivClass").css('display','none');
		$("#partnerCoSignCopyDivId").hide();
		$(".partnerSubmitDiv").hide();
		$("#partnerApprovalDivId").hide();
		$("#partnerRegCompleteDivId").hide();
		$("#partnerSubmitRespDivId").show();
		var msg=data==null?'':data.response==null?'':data.response.message==null?'':data.response.message;
		if(!isEmpty(data) && !isEmpty(data.response) && !isEmpty(data.response.errors)){
		$.each(data.response.errors,function(key,value){
	       msg=msg+'\n'+value.errorMessage+'\n'+",";
	    });
		}
	   Alert.warn(msg);
	}else{
		cacheLi();
	    setCurrentTab(el);
		$(".leftPaneData").html("");
	     changeFieldsForPartnerStatus(data);
	}
	setPartnerHeaderValues("Company Name: "+companyName, "Company Registration No. : "+registrationNo, "Vendor SAP Code : "+vendorSAPCode,"Status : "+partnerStatus);
}
function setPartnerHeaderValues(leftTop,rightTop,leftBottom,rightBottom){
	$(".detailscont").empty();
	$(".detailscont").append('<div class="col-md-12">'+
            '<label class="col-md-6">'+leftTop+'</label>'+
            '<label class="col-md-6">'+rightTop+'</label>'+
        '</div>'+
        '<div class="col-md-12" style="margin-top:10px;">'+
            '<label class="col-md-6">'+leftBottom+'</label>'+
            '<label class="col-md-6" id="rightBottomId">'+rightBottom+'</label>'+
           /* '<label class="col-md-4">'+fifthValue+'</label>'+*/
        '</div>');
}
function ceSignCopyDelResp(data)
{
	if(!isEmpty(data)){
	if (!data.hasError) {
		$('#ceSignCopyId').val('');
		$('#ceSignCopy').val('');
		$("#a_ceSignCopy").html('');
		$('.ceSignCopy').attr('disabled', true);
		Alert.info(data.message);
		var bPartnerId=$("#bPartnerId").val();
		if(bPartnerId!="")
		{
		  partnerArray["partner"+bPartnerId].ceSignCopy=null;
		}
	} else {
		Alert.warn(data.message);
	}
	}
}
function partnerSignCopyDelResp(data)
{
	if(!isEmpty(data)){
	if (!data.hasError) {
		$('#partnerSignCopyId').val('');
		$('#partnerSignCopy').val('');
		$("#a_partnerSignCopy").html('');
		$('.partnerSignCopy').attr('disabled', true);
		Alert.info(data.message);
		var bPartnerId=$("#bPartnerId").val();
		if(bPartnerId!="")
		{
		  partnerArray["partner"+bPartnerId].partnerSignCopy=null;
		}
	}else{
		Alert.warn(data.message);
	}
	}
}
function completeRegistrationProcess(event)
{
	event.preventDefault();
	submitIt('partnerDetailsApprovalForm','completePartnerRegistration','completePartnerRegistrationResp');
}
function completePartnerRegistrationResp(data)
{
var resperror=data==null?'':data.response==null?'':data.response.hasError==null?'':data.response.hasError;
	if(!resperror)
		{
			$("#partnerSubmitRespDivId").show();
			$("#submitRespLabelId").html(data.response.message);
			/*$(".partnerCoSignCopyDiv").addClass("readonly");*/
			$("#partnerRegCompleteDivId").hide();
			$("#ceSignedFileDiv").css('display','none');
			  $("#partnerCoSignCopyId").removeClass("requiredFile");
			  /*$("#partnerCoSignCopy").attr('disabled','disabled');*/
			  $("#partnerCoSignCopyDivId").css("display","none");
			 /* $("#partnerCoSignCopyDivId").removeClass("readonly");*/
			  $("#partnerRegCompleteDivId").css("display","none");
			  swal({
		          title: "Done",
		          text: data.response.message,
		          icon: "success",
		          type: "success",
		          dangerMode: true,
		          confirmButtonText:"Logout",
		        }).then(function(isConfirm) {
		          if (isConfirm) {
		        	  window.location.href="/eatApp/logout"; 
		          } 
		        });
		}else{
			    var msg=data.response.message;
				 $.each(data.response.errors,function(key,value){
				       msg=msg+'\n'+value.errorMessage+'\n'+',';
				       
				   });
				Alert.warn(msg);
			}
}
function partnerCoSignCopyDelResp(data)
{
	if(!isEmpty(data)){
	if (!data.hasError) {
		$('#partnerCoSignCopyId').val('');
		$('#partnerCoSignCopy').val('');
		$("#a_partnerCoSignCopy").html('');
		$('.partnerCoSignCopy').attr('disabled', true);
		Alert.info(data.message);
		var bPartnerId=$("#bPartnerId").val();
		if(bPartnerId!="")
		{
		  partnerArray["partner"+bPartnerId].partnerCoSignCopy=null;
		}
	} else {
		Alert.warn(data.message);
	}
	}
}
function partnershipDEEDCopyDelResp(data)
{
	if(!isEmpty(data)){
	if (!data.hasError) {
		$('#partnershipDEEDCopyId').val('');
		$('#partnershipDEEDCopy').val('');
		$("#a_partnershipDEEDCopy").html('');
		$('.partnershipDEEDCopy').attr('disabled', true);
		Alert.info(data.message);
		var bPartnerId=$("#bPartnerId").val();
		if(bPartnerId!="")
		{
		  partnerArray["partner"+bPartnerId].partnershipDEEDCopy=null;
		}
	} else {
		Alert.warn(data.message);
	}
	}
}

function setRating(id, rate) {
	$('#' + id).barrating('destroy');
	$('#' + id).barrating({
		initialRating : rate,
		theme : 'fontawesome-stars',
		readonly : 'true'
	});
}
function setStarRating(id, rate){
	 $('#' + id).text(rate);
	 $('span.stars').stars();
}