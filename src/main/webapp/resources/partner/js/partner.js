var partnerArray = new Array();
var editMode=false;
/*var activeTabId="";*/
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
$(document).ready(function(){
	
	 var lengthMenu;
	 var companyName;
	 var registrationNo;
	 var panNo;
	 var companyType;
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
		debugger;
		var type=$("#partnerRegistrationType").val();
		if (this.checked && type=="PARTICIPANT") {
			$("#contractorTypeDiv").show();
			$("#contractorType").removeAttr('disabled','disabled');
		}else{
			$("#contractorTypeDiv").hide();
			$("#contractorType").attr('disabled','disabled');
		}
		
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
	
	
	$('#isGstApplicable').click(function(){
	if (this.checked) {
	  $("#GSTINCopyId").addClass("requiredFile");
	}else{
		$("#GSTINCopyId").removeClass("requiredFile");
	}
	});
	$('#confirmationTabId').click(function(){
		$(".leftPaneData").html("");
	});
	
	/*$(".approveCEDiv").hide();
    $(".approveEEDiv").hide();*/
	
	$('.searchLeftPane').on('click', 'li', function() {
	    $('.searchLeftPane li.active').removeClass('active');
	    $(this).addClass('active');
	});
    
    $('#partnerDetailsApprovalForm  input[type=radio][name=isApproved]').change(function(){
    	var role=$("#roleData").val();
    	if ($(this).val()=="Y" && role=="NVLADM")
    		{
    		 $("#ceSignCopyDivId").show();
  		     $("#ceSignCopyId").addClass("requiredFile");
            }else{
            	$("#ceSignCopyDivId").hide();
     		    $("#ceSignCopyId").removeClass("requiredFile");
            }
	});
    
    $("#companyDetails").find("input,select,textarea").change(function() {
    	debugger
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
	 debugger;
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
function loadSearchLeftPane()
{
	debugger;
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
	debugger;
	if(!setPageSelected){
		selectedPage = 1;
		setPageSelected = false;
	}
	$('#pagination-here').empty();
	partnerLastPage = data.objectMap.lastPage;	
	
	loadLeftPane(data.objectMap.partners);
	setActiveTabName("Company Details",$('.leftPaneData li').length);
	if($('.leftPaneData li').length==0){
		$("#companyDetails #bPartnerId").val('');
		$("#companyDetails")[0].reset();
		$(".nonFilterTab").addClass('readonly');
	}else{
		$(".nonFilterTab").removeClass('readonly');
	}
}
function getPartnerDetails(event,el)
{
	debugger;
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
			setPartnerHeaderValues("Company Name: "+companyName, "Company Registration No. : "+registrationNo, "Company Type : "+companyType,"Status : "+partnerStatus);
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
	debugger;
	event.preventDefault();	

		if(!editMode && !requiredFileDeleted){
			$('#pagination-here').empty();
			cacheLi();
			setCurrentTab(el);
			if(getChangedFlag()){
			 submitWithParam('getPartnerDetails','bPartnerId','onConfirmationTabLoad');
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
			setActiveTabName("Confirmation",$('.leftPaneData li').length);
			setPartnerHeaderValues("Company Name: "+companyName, "Company Registration No. : "+registrationNo, "Company Type : "+companyType,"Status : "+partnerStatus);
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
	
	debugger;
	if(data.response.hasError)
		{
		   debugger;
		     if(!$.isEmptyObject(data.response.errors)){
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
		    var status=data.status==null?'':data.status;
		    var isEEApproved=data.isEEApproved==null?'':data.isEEApproved;
		    var isCEApproved=data.isCEApproved==null?'':data.isCEApproved;
		    var partnerData=$("#partnerData").val();
		    if(partnerData=="partnerRegistration")
		     {
		    	partnerStatus=showPartnerStatusAlert(status,isEEApproved,isCEApproved);
		    	$("#companyDetails  .approveCEDiv").hide();
				$("#companyDetails  .approveEEDiv").hide();
		     }
		    setPartnerHeaderValues("Company Name: "+companyName, "Company Registration No. : "+registrationNo, "Company Type : "+companyType,"Status : "+partnerStatus);
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
			partnerArray["partner"+data.bPartnerId]=data;
		}
}
function loadOfficeType(data){
	debugger;
		$("#companyDetails #officeType").html("");
		var options = '<option value=""></option>';
		$.each(data,function(key,value){
			options +='<option value="'+value.locationTypeId+'" data-code="'+value.code+'">'+value.name +'</option>'
		});
		$("#companyDetails #officeType").append(options);
}
function loadOfficeLocation(){
	debugger;
	var code=$("#companyDetails #officeType option:selected").data('code');
	if(code==undefined || code==''){
		/*Alert.warn("Invalid Office Type Selected");*/
	}else{
		submitToURL('getOfficeLocation/'+code,'loadOfficeLocationResp');
	}
}
function loadOfficeLocationResp(data){
	debugger;
	$("#companyDetails #officeLocation").html("");
	var options = '<option value=""></option>';
	$.each(data,function(key,value){
		options +='<option value="'+value.officeLocationId+'">'+value.name +'</option>'
	});
	$("#companyDetails #officeLocation").append(options);
}

function onPageLoad(data){
	debugger;
	if(data.objectMap.hasOwnProperty('companyTypes')){
		loadReferenceListById('companyType',data.objectMap.companyTypes)
	}
	if(data.objectMap.hasOwnProperty('contractorTypes')){
		loadReferenceListById('contractorType',data.objectMap.contractorTypes)
	}
   
	$.each(data,function(key,value){
		if(key=='objectMap'){
			var responseMap = value;
			loadLeftPane(responseMap.partners);
			if($.isEmptyObject(responseMap.partners))
				{
				   $(".hidePartnerTab").hide();
				}else{
					$(".hidePartnerTab").show();
				}
			 setActiveTabName("Company Details",responseMap.partners.length);
		}
		else
			setActiveTabName("Company Details",$('.leftPaneData li').length);
	});
	
}


function loadLeftPane(data){
	debugger;
	$('.pagination').children().remove();
	$(".leftPaneData").html("");
	if(data.length==0)
		{
		  $(".leftPaneData").html("No Records Found");
		  return;
		}
	var leftPanelHtml="";
	var i=0;
	var firstRow=null;
	$.each(data,function(key,value){
		partnerArray["partner"+value.bPartnerId]=value;
		if(i==0){
			firstRow = value;
			leftPanelHtml = leftPanelHtml + ' <li onclick="showPartnerDetails('+value.bPartnerId+')" class="active">'
		}else{
			leftPanelHtml = leftPanelHtml + ' <li onclick="showPartnerDetails('+value.bPartnerId+')">'
		}
		leftPanelHtml = leftPanelHtml +' <a href="#Master" data-toggle="tab">'
        +' <div class="col-md-12">'
        +'  <label class="col-xs-6"> '+value.name+'</label>'
        +'	<label class="col-xs-6 ">'+value.crnNumber+'</label>'
        +' </div>'	
        +' <div class="col-md-12">'
        +'	<label class="col-xs-6">'+value.panNumber+'</label>';
        if(value.gstinNo !=null){
        	leftPanelHtml = leftPanelHtml	+'	<label class="col-xs-6">'+value.gstinNo+'</label>';
		}
	
		leftPanelHtml = leftPanelHtml+' </div>'
        +' </a>'
        +' </li>';
		i++;
	});
	$(".leftPaneData").html(leftPanelHtml);
	$('.leftPaneData').paginathing();
	
	loadRightPane(firstRow);
	 $(".tabs-left li a label").text(function(index, currentText) {
		    return currentText.substr(0, 20);
		});
}
function showPartnerDetails(id) {
	var partnerData=partnerArray["partner"+id];
	loadRightPane(partnerData);
	
}
function loadRightPane(data){
	debugger;
	$(".approveCEDiv").hide();
    $(".approveEEDiv").hide();
	var panCardCopyId = data.panCardCopy == null ? '': data.panCardCopy.attachmentId == null ? '': data.panCardCopy.attachmentId;
	var panCardCopyName = data.panCardCopy == null ? '': data.panCardCopy.fileName == null ? '': data.panCardCopy.fileName;

	var GSTINCopyId = data.gstinCopy == null ? '': data.gstinCopy.attachmentId == null ? '': data.gstinCopy.attachmentId;
	var GSTINCopyName = data.gstinCopy == null ? '': data.gstinCopy.fileName == null ? '' : data.gstinCopy.fileName;

	var companyRegCertificateId = data.companyRegCertificate == null ? '': data.companyRegCertificate.attachmentId == null ? '': data.companyRegCertificate.attachmentId;
	var companyRegCertificateName = data.companyRegCertificate == null ? '': data.companyRegCertificate.fileName == null ? '' : data.companyRegCertificate.fileName;
	
	companyName = data.name == null ? '':data.name;
	registrationNo = data.crnNumber==null?'':data.crnNumber;
	panNo= data.panNumber==null?'':data.panNumber;
	companyType=data.companyType==null?'':data.companyType;
	var status=data.status==null?'':data.status;
	var isEEApproved=data.isEEApproved==null?'':data.isEEApproved;
	var isCEApproved=data.isCEApproved==null?'':data.isCEApproved;
	var eeComment=data.eeComment==null?'':data.eeComment;
	var ceComment=data.ceComment==null?'':data.ceComment; 
	 
	var eeCompDetailComment=data.eeCompDetailComment==null?'':data.eeCompDetailComment;
	var isEECompDetailApproved=data.isEECompDetailApproved==null?'':data.isEECompDetailApproved;
	var ceCompDetailComment=data.ceCompDetailComment==null?'':data.ceCompDetailComment;
	var isCECompDetailApproved=data.isCECompDetailApproved==null?'':data.isCECompDetailApproved;

	   
	$("#labelName").html(data.name);
	$("#labelPanNumber").html(data.panNumber);
	$("#labelCrnNumber").html(data.crnNumber);
	$("#labelGstinNo").html(data.gstinNo);	
	$("#name").val(data.name);
	$("#bPartnerId").val(data.bPartnerId);
	$(".bPartnerId").val(data.bPartnerId);
	$("#companyType").val(data.companyType);
	$("#contractorType").val(data.contractorType);
	$("#crnNumber").val(data.crnNumber);
	$("#panNumber").val(data.panNumber);
	$("#gstinNo").val(data.gstinNo);
	$("#companyDetails #panCardCopy").val(panCardCopyId);
	$("#companyDetails #companyRegCertificate").val(companyRegCertificateId);
	$("#companyDetails #GSTINCopy").val(GSTINCopyId);
	$("#companyDetails #partnerRegistrationType").val(data.registrationType);
	$("#companyDetails #partnerStatus").val(status);
	changeCheckBoxLabel(data.registrationType);
	showContrctorType(data.contractorType);
	setAttribute("isGstApplicable", data.isGstApplicable);
	gstNumberShow(data.isGstApplicable);
	setAttribute("isCustomer", data.isCustomer);
	setAttribute("isContractor",data.isContractor);
	setAttribute("isManufacturer", data.isManufacturer);
	setAttribute("isTrader",data.isTrader);
	showFactoryDetailTab(data.isManufacturer);
	showManufacturerTab(data.isTrader);
	showVendorPaymentTab(data.isManufacturer,data.isTrader);
	changeFieldsForPartnerStatus(data);
	changeFormFieldName();
	showFactoryInspectionTab();
	showPaymentTab(data.registrationType);
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

	var url = $("#a_companyRegCertificate").data('url');
	$("#a_companyRegCertificate").attr('href', url);
	var a_companyRegCertificate = $("#companyDetails #a_companyRegCertificate").prop('href') + '/'+ companyRegCertificateId;
	$("#companyDetails #a_companyRegCertificate").prop('href', a_companyRegCertificate);
	$("#companyDetails #a_companyRegCertificate").html(companyRegCertificateName);
	
	showCEFileDiv();
	showPartnerFileDeleteBtn(panCardCopyId, GSTINCopyId,companyRegCertificateId);
	changeCommentAndStatusByRole('companyDetails',isEECompDetailApproved,eeCompDetailComment,isCECompDetailApproved,ceCompDetailComment);
    partnerStatus=showPartnerStatusAlert(status,isEEApproved,isCEApproved);
	setPartnerHeaderValues("Company Name: "+companyName, "Company Registration No. : "+registrationNo, "Company Type : "+companyType,"Status : "+partnerStatus);
}

function showPartnerStatusAlert(status,isEEApproved,isCEApproved)
{
	if (status == 'IP' && (isEEApproved == "" || isEEApproved == null)) {
		/*Alert.info("Partner is Submitted!");*/
		return "Submitted";
	} else if (status == 'DR' || status=="") {
		/*Alert.info("Partner is in drafted mode")*/
		return "Drafted";
	} else if (status == "EDIT") {
		/*Alert.info("Partner is in edit mode after approved");*/
		return "Editing";
	} else if (status == "CO") {
		/*Alert.info("Partner is approved");*/
		return "Approved";
	}else if (status == "RJ") {
	    /*Alert.info("Partner is rejected");*/
		return "Rejected";
	}/*else if (status == "EEC") {
		Alert.info("Partner is clarified by executive engineer");
		return "Need Clarification By Executive Engineer";
	} */else if (status == "CEC") {
		/*Alert.info("Partner is clarified by chief engineer");*/
		return "Need Clarification By Novel Admin";
	} /*else if (status == 'IP' && isEEApproved == "Y") {
		Alert.info("Partner is approved by executive engineer");
		return "Approved By Executive Engineer";
	}*/
}
function showContrctorType(contractorType)
{
	if(contractorType=="" || contractorType==null)
	{
		$("#contractorTypeDiv").hide();
		$("#contractorType").attr('disabled','disabled');
	}
	else{
		$("#contractorTypeDiv").show();
		$("#contractorType").removeAttr('disabled','disabled');
		
	}
}
function showPartnerFileDeleteBtn(panCardAttachmentId, GSTINAttachmentId,certificateAttachmentId) {
	debugger;
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
	
}
function showCEFileDiv()
{
	var role=$("#roleData").val();
	if(role=="NVLADM")
		{
		   $(".ceSignCopyDiv").removeClass("readonly");
		   $("#ceSignCopyDivId").show();
		   $("#ceSignCopyId").addClass("requiredFile");
		}else{
		   $("#ceSignCopyDivId").hide();
		   $("#ceSignCopyId").removeClass("requiredFile");
		}
}
function showPartnerRegistrationFiles(data)
{
	debugger;
	var ceSignCopyId = data.ceSignCopy == null ? '': data.ceSignCopy.attachmentId == null ? '': data.ceSignCopy.attachmentId;
	var ceSignCopyName = data.ceSignCopy == null ? '': data.ceSignCopy.fileName == null ? '': data.ceSignCopy.fileName;
	var partnerSignCopyId = data.partnerSignCopy == null ? '': data.partnerSignCopy.attachmentId == null ? '': data.partnerSignCopy.attachmentId;
	var partnerSignCopyName = data.partnerSignCopy == null ? '': data.partnerSignCopy.fileName == null ? '': data.partnerSignCopy.fileName;
	var partnerCoSignCopyId = data.partnerCoSignCopy == null ? '': data.partnerCoSignCopy.attachmentId == null ? '': data.partnerCoSignCopy.attachmentId;
	var partnerCoSignCopyName = data.partnerCoSignCopy == null ? '': data.partnerCoSignCopy.fileName == null ? '': data.partnerCoSignCopy.fileName;
	
	$("#partnerDetailsApprovalForm #ceSignCopy").val(ceSignCopyId);
	var url = $("#a_ceSignCopy").data('url');
	$("#a_ceSignCopy").attr('href', url);
	var a_ceSignCopy = $("#partnerDetailsApprovalForm #a_ceSignCopy").prop('href') + '/'+ ceSignCopyId;
	$("#partnerDetailsApprovalForm #a_ceSignCopy").prop('href', a_ceSignCopy);
	$("#partnerDetailsApprovalForm #a_ceSignCopy").html(ceSignCopyName);
	
	$("#partnerDetailsApprovalForm #partnerSignCopy").val(partnerSignCopyId);
	var url = $("#a_partnerSignCopy").data('url');
	$("#a_partnerSignCopy").attr('href', url);
	var a_partnerSignCopy = $("#partnerDetailsApprovalForm #a_partnerSignCopy").prop('href') + '/'+ partnerSignCopyId;
	$("#partnerDetailsApprovalForm #a_partnerSignCopy").prop('href', a_partnerSignCopy);
	$("#partnerDetailsApprovalForm #a_partnerSignCopy").html(partnerSignCopyName);
	
	$("#partnerDetailsApprovalForm #partnerCoSignCopy").val(partnerCoSignCopyId);
	var url = $("#a_partnerCoSignCopy").data('url');
	$("#a_partnerCoSignCopy").attr('href', url);
	var a_partnerCoSignCopy = $("#partnerDetailsApprovalForm #a_partnerCoSignCopy").prop('href') + '/'+ partnerCoSignCopyId;
	$("#partnerDetailsApprovalForm #a_partnerCoSignCopy").prop('href', a_partnerCoSignCopy);
	$("#partnerDetailsApprovalForm #a_partnerCoSignCopy").html(partnerCoSignCopyName);
	
	$("#partnerCoSignCopy").attr('disabled','disabled');
	$("#partnerCoSignCopyId").removeClass("requiredFile");
    $(".ceSignCopyDiv").removeClass("readonly");
    showCEFileDiv();
    
	var partnerData =  $("#partnerData").val();
	var regType =  $("#partnerRegistrationType").val();
	if((data.status=="CO" && data.isCEApproved=="Y") && partnerData=="partnerRegistration" && regType=="PARTICIPANT")
		{
		  $(".ceSignCopyDiv").addClass("readonly");
		  $("#ceSignCopyDivId").show();
		  $("#partnerCoSignCopyId").addClass("requiredFile");
		  $("#partnerCoSignCopy").removeAttr('disabled','disabled');
		  $("#partnerCoSignCopyDivId").css("display","block");
		  $("#partnerCoSignCopyDivId").removeClass("readonly");
		  $("#partnerRegCompleteDivId").css("display","block");
		  $("#partnerSubmitRespDivId").hide();
		}
	 if(regType=="PARTICIPANT" && partnerCoSignCopyId!="" && (data.status=="CO" && data.isCEApproved=="Y") && partnerData=="partnerRegistration")
		 {
		    $("#partnerSubmitRespDivId").show();
			$("#submitRespLabelId").html("Your Registration Process Completed!");
			$(".partnerCoSignCopyDiv").addClass("readonly");
			$("#partnerRegCompleteDivId").hide();
		 }
	
	showPartnerRegistrationFileDelBtn(partnerSignCopyId,ceSignCopyId,partnerCoSignCopyId);
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
debugger;
var partnerData = $("#partnerData").val();
var role=$("#roleData").val();

	if (partnerData == 'partnerProfiles' && role == "NVLADM") {
		if (partnerStatus.status == "IP" && (partnerStatus.isCEApproved == "" || partnerStatus.isCEApproved == null)) {
			$(".partnerTabs").addClass("readonly");
			/*$(".approveEEDiv").show();*/
			$(".approveCEDiv").show();
			$("#partnerEditBtnId").hide();
			$(".remark").removeClass('readonly');
			$(".statusBtn").removeClass('readonly');
			$(".disableBtn").removeClass('readonly');
			$("#partnerDetailsApprovalForm #partnerApprovalDivId").show();
			$("#partnerDetailsApprovalForm #partnerSubmitRespDivId").hide();
			$("#partnerDetailsApprovalForm #partnerSubmitDivId").hide();
		} else if (partnerStatus.status == "CO" && partnerStatus.isCEApproved == "Y") {
			$(".partnerTabs").addClass("readonly");
			/*$(".approveEEDiv").show();*/
			$(".approveCEDiv").show();
			$(".remark").removeClass('readonly');
			$(".statusBtn").removeClass('readonly');
			$(".disableBtn").removeClass('readonly');
			$("#partnerEditBtnId").hide();
			$("#partnerDetailsApprovalForm #partnerSubmitRespDivId").show();
			$("#submitRespLabelId").html("Partner Approved: " + remarks);
			$("#partnerDetailsApprovalForm #partnerSubmitDivId").hide();
			$("#partnerDetailsApprovalForm #partnerApprovalDivId").hide();
		} else if (partnerStatus.status == "CEC" && partnerStatus.isCEApproved == "C") {
			$(".partnerTabs").addClass("readonly");
			/*$(".approveEEDiv").show();*/
			$(".approveCEDiv").show();
			$(".remark").removeClass('readonly');
			$(".statusBtn").removeClass('readonly');
			$(".disableBtn").removeClass('readonly');
			$("#partnerEditBtnId").hide();
			$("#partnerDetailsApprovalForm #partnerSubmitRespDivId").show();
			$("#submitRespLabelId").html("Partner Clarified For: " + remarks);
			$("#partnerDetailsApprovalForm #partnerSubmitDivId").hide();
			$("#partnerDetailsApprovalForm #partnerApprovalDivId").hide();
		} else if (partnerStatus.status == "RJ" && partnerStatus.isCEApproved == "N") {
			$(".partnerTabs").addClass("readonly");
			/*$(".approveEEDiv").show();*/
			$(".approveCEDiv").show();
			$(".remark").addClass('readonly');
			$(".statusBtn").addClass('readonly');
			$(".disableBtn").addClass('readonly');
			$("#partnerEditBtnId").hide();
			$("#partnerDetailsApprovalForm #partnerSubmitRespDivId").show();
			$("#submitRespLabelId").html("Partner Rejected For: " + remarks);
			$("#partnerDetailsApprovalForm #partnerSubmitDivId").hide();
			$("#partnerDetailsApprovalForm #partnerApprovalDivId").hide();
		}
	}
 else if ((partnerStatus.status == 'IP' || partnerStatus.status == 'CO' || partnerStatus.status == 'RJ'  || partnerStatus.status == 'CEC')&& partnerData != 'partnerProfiles') {
		/*$(".approveEEDiv").hide();*/
		$(".approveCEDiv").hide();
		$(".remark").addClass('readonly');
		$(".statusBtn").addClass('readonly');
		$(".disableBtn").addClass('readonly');
		$(".partnerTabs").addClass("readonly");
		if (partnerStatus.status == 'CO' || partnerStatus.status == 'CEC') {
			$("#partnerEditBtnId").show();
		} else {
			$("#partnerEditBtnId").hide();
		}

		$("#partnerDetailsApprovalForm #partnerSubmitRespDivId").show();
		$("#submitRespLabelId").html("Your Registration Profile Has Been Submitted !");
		$("#partnerDetailsApprovalForm #partnerSubmitDivId").hide();
		$("#partnerDetailsApprovalForm #partnerApprovalDivId").hide();
		$(".cancel").removeAttr("disabled", "disabled");
	} else if ((partnerStatus.status == 'DR' || partnerStatus.status == 'EDIT'
			|| partnerStatus.status == null || partnerStatus.status == '')
			&& partnerData != 'partnerProfiles') {
		/*$(".approveEEDiv").hide();*/
		$(".approveCEDiv").hide();
		$("#partnerEditBtnId").hide();
		$("#partnerRegCompleteDivId").hide();
		$("#partnerCoSignCopyDivId").hide();
		$(".remark").addClass("readonly");
		$(".statusBtn").addClass("readonly");
		$(".disableBtn").removeClass('readonly');
		$(".disableBtn").removeAttr('disabled', 'disabled');
		$(".partnerTabs").removeClass("readonly");
		$("#partnerDetailsApprovalForm #partnerSubmitDivId").show();
		$("#partnerDetailsApprovalForm #partnerSubmitRespDivId").hide();
		$("#partnerDetailsApprovalForm #partnerApprovalDivId").hide();
		$(".cancel").removeAttr("disabled", "disabled");
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
	showVendorPaymentTab(data,null);
	
}
function showManufacturerTab(data)
{   
	if(data=='Y')
    {
		$("#manufacturerTabId").show();
		
    }else{
    	$("#manufacturerTabId").hide();
    	
    }
	showVendorPaymentTab(null,data);
	
}
function showVendorPaymentTab(isManufacturer,isTrader)
{
	if(isManufacturer=='Y' || isTrader=="Y" ||  $("#isManufacturer").prop("checked") == true || $("#isTrader").prop("checked") == true)
    {
		$("#vendorPaymentTabId").show();
    }else{
    	$("#vendorPaymentTabId").hide();
    }
}
function showPaymentTab(regType){
	debugger;
	if(regType=='CREATER'){
		$("#vendorPaymentTabId").show();
	}else{
		$("#vendorPaymentTabId").hide();
	}
}

function gstnumShow() {
	 if ($('#isGstApplicable').is(":checked")){
	        $(".GSTidenNumb").show();
	        $("#gstinNo").addClass('requiredField');
	        }
	    else{
	        $(".GSTidenNumb").hide();
	    	$("#gstinNumber").val("");
	    	 $("#gstinNo").val("");
             $("#gstinNo").removeClass('requiredField');
	    }
}

function gstNumberShow(value) {
	 if ("Y"== value){
	     $(".GSTidenNumb").show();
	     $("#GSTINCopyId").addClass("requiredFile");
			
	 }else{
	     $(".GSTidenNumb").hide();
	     $("#gstinNumber").val("");
	     $("#GSTINCopyId").removeClass("requiredFile");
	 }
}

function loadContractorType(data){
	$("#companyDetails #contractorType").html("");
	var options = '<option></option>';
	$.each(data,function(key,value){
		options +='<option value="'+value.code+'">'+value.name +'</option>'
		
	});

	$("#companyDetails #contractorType").append(options);
}

function loadCompanyType(data){
	$("#companyDetails #companyType").html("");
	var options = '<option></option>';
	$.each(data,function(key,value){
		options +='<option value="'+value.code+'">'+value.name +'</option>'
		
	});

	$("#companyDetails #companyType").append(options);
}

function partnerDetailsSubmitResp(data){
	debugger;
	$('#loading-wrapper').hide();
	if($.isEmptyObject(data))
	{
	  return;
	}
	if(!data.hasError)
		{
		  getPartnerInfo();
		

		}else{
			if(!$.isEmptyObject(data.errors))
				{
				   
				var msg='Following Tabs Are Not Filled: ';
				 $.each(data.errors,function(key,value){
				       msg=msg+'\n'+value.errorMessage+'\n'+',';
				       
				   });
				    Alert.warn(msg);
				}
		}
	}

function partnerProfileEditResp(data) {
	debugger;
	if (!data.response.hasError) {
		$('#loading-wrapper').hide();
		$("#partnerEditBtnId").hide();
		getPartnerInfo();

	}
}

function PanAttachmentDeleteResp(data) {
	debugger;
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



function GSTINAttachmentDeleteResp(data) {
	debugger;
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
function companyRegCertDeleteResp(data) {
	debugger;
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
	debugger;
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
	debugger;
	if(isApproved=="Y")
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
	debugger;
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
	 /*if(role=='EXEENGR')
		 {
		    $("#"+formId+" .approveEEDiv").show();
		    $("#"+formId+" #eeRemark").val(eeComment);
		    $("#"+formId+" .approveCEDiv").find('input:radio, textarea').attr('disabled','disabled');
		    $("#"+formId+" .approveCEDiv").find('input:radio, textarea').addClass('readonly');
		    $("#"+formId+" .approveCEDiv").find('input:radio, textarea').css("background-color","#DADCE2");
		    
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
		     
		 }else */
	    if(role=='NVLADM'){
			 $("#"+formId+" .partnerOrgCEApproveDiv").find('input:radio, textarea').removeAttr('disabled','disabled');
			 $("#"+formId+" .approveCEDiv").show();
			 $("#"+formId+" #ceRemark").val(ceComment);
			 setStatusButton(formId,isCEApproved,'ceApproveBtn','ceClarification');
			/* $("#"+formId+" .approveEEDiv").show();
			 $("#"+formId+" .approveEEDiv").find('input:radio, textarea').attr('disabled','disabled');
			 $("#"+formId+" .approveEEDiv").find('input:radio, textarea').addClass('readonly');
			 $("#"+formId+" .approveEEDiv").find('input:radio, textarea').css("background-color","#DADCE2");
			 $("#"+formId+" #eeRemark").val(eeComment);
			 setStatusButton(formId,isEEApproved,'eeApproveBtn','eeClarification');*/
			 
		 }else if(partnerData=="partnerRegistration"){
			   /* $("#"+formId+" .approveEEDiv").find('input:radio, textarea').attr('disabled','disabled');
			    $("#"+formId+" .approveCEDiv").find('input:radio, textarea').attr('disabled','disabled');
			    */
			  /* $("#"+formId+" .approveEEDiv").find('input:radio, textarea').addClass('readonly');*/
			   $("#"+formId+" .approveCEDiv").find('input:radio, textarea').addClass('readonly');
			/*   $("#"+formId+" .approveEEDiv").find('input:radio, textarea').css("background-color","#DADCE2");*/
			   $("#"+formId+" .approveCEDiv").find('input:radio, textarea').css("background-color","#DADCE2");
			    $("#"+formId+" .approveCEDiv").hide();
			 /*   if(isCEApproved!="")
			    {
			    	if(isCEApproved!="Y"){
			    	$("#"+formId+" .approveCEDiv").show();
                    setStatusButton(formId,isCEApproved,'ceApproveBtn','ceClarification');}
			    }
			    if(isEEApproved!="")
			    {
			    	if(isEEApproved=="C"){
			    	 $("#"+formId+" .approveEEDiv").show();
			    	 setStatusButton(formId,isEEApproved,'eeApproveBtn','eeClarification');}
			    }*/
			    $("#"+formId+" .remark").addClass('readonly');
		        $("#"+formId+" .statusBtn").addClass('readonly');
			  /*  $("#"+formId+" #eeRemark").val(eeComment);*/
			    $("#"+formId+" #ceRemark").val(ceComment);
			 
		 }
}
function showSubmitLoader(event)
{
	debugger;
	event.preventDefault();
	/*$('#loading-wrapper').show();*/
	submitIt('partnerDetailsApprovalForm','submitPartnerDetails','partnerDetailsSubmitResp');
	/*submitWithParam('submitPartnerDetails','bPartnerId','partnerDetailsSubmitResp');*/
}
function showApproveLoader(event)
{
debugger;
	event.preventDefault();
	/*$('#loading-wrapper').show();*/
	submitIt('partnerDetailsApprovalForm','partnerRegistrationApproval','partnerApprovalResp');	
}
function partnerApprovalResp(data)
{
	debugger;
	if(!data.hasError)
	{
	submitToURL("getPartners", 'onPageLoad');
	$("#partnerDetailsApprovalForm #partnerSubmitRespDivId").show();
	$("#submitRespLabelId").html(data.message);
	$("#partnerDetailsApprovalForm #partnerSubmitDivId").hide();
	$("#partnerDetailsApprovalForm #partnerApprovalDivId").hide();
	$("#partnerDetailsApprovalForm #remarks").val("");
	}else
		{
		
		if(!$.isEmptyObject(data.errors))
		{
		   var msg='Please do clarification or rejection as you have not approved following tabs: ';
		   $.each(data.errors,function(key,value){
		       msg=msg+'\n'+value.errorMessage+'\n'+',';
		       
		   });
		    Alert.warn(msg);
		}else{
			Alert.warn(data.message);
		}
	}
	$('#loading-wrapper').hide();
}
function getLoaderForEdit(event)
{
	event.preventDefault();
	$('#loading-wrapper').show();
	submitWithParam('updatePartnerProfile','bPartnerId','partnerProfileEditResp');	
}
function onConfirmationTabLoad(data)
{
	debugger;
	changeFieldsForPartnerStatus(data);
}
/*function setPartnerHeaderValues(leftTop,rightTop,leftBottom,rightBottom,fifthValue){
	$(".detailscont").empty();
	$(".detailscont").append('<div class="col-md-12">'+
            '<label class="col-md-6"><h4>'+leftTop+'</h4></label>'+
            '<label class="col-md-6"><h4>'+rightTop+'</h4></label>'+
        '</div>'+
        '<div class="col-md-12">'+
            '<label class="col-md-4"><h4>'+leftBottom+'</h4></label>'+
            '<label class="col-md-4"><h4>'+rightBottom+'</h4></label>'+
            '<label class="col-md-4"><h4>'+fifthValue+'</h4></label>'+
        '</div>');
}*/
function ceSignCopyDelResp(data)
{
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
function partnerSignCopyDelResp(data)
{
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
function completeRegistrationProcess(event)
{
	debugger;
	event.preventDefault();
	submitIt('partnerDetailsApprovalForm','completePartnerRegistration','completePartnerRegistrationResp');
}
function completePartnerRegistrationResp(data)
{
	debugger;
	$("#partnerSubmitRespDivId").show();
	$("#submitRespLabelId").html("Your Registration Process Completed!");
	$(".partnerCoSignCopyDiv").addClass("readonly");
	$("#partnerRegCompleteDivId").hide();
	/*Alert.info("Your Registration Process Completed!");*/
}
function partnerCoSignCopyDelResp(data)
{
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
function changeCheckBoxLabel(registrationType)
{
	debugger;
	if(registrationType=="CREATER")
		{
		  $("#isContractorLabel").text("Manage Forward Auction");
		  $("#isCustomerLabel").text("Manage Reverse Auction");
		}else if(registrationType=="PARTICIPANT"){
			$("#isContractorLabel").text("Supplier");
			$("#isCustomerLabel").text("Buyer");
		}
}
function checkForFilterByRole(){
	debugger;
	var partnerData =  $("#partnerData").val();
	if(partnerData == 'partnerProfiles'){
	   if(vendorStatus==""){
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
	   /*$(".manufacturerEEApproveDiv").find('input:radio, textarea').css("background-color","#DADCE2");
	   $(".manufacturerCEApproveDiv").find('input:radio, textarea').css("background-color","#DADCE2");
	   $(".manufacturerEEApproveDiv").find('input:radio, textarea').css("background-color","#FFF");
	   $(".manufacturerCEApproveDiv").find('input:radio, textarea').css("background-color","#FFF");*/
	}
}
function checkForFilterOnTabLoad(){
	debugger;
	var partnerData =  $("#partnerData").val();
	if(partnerData == 'partnerProfiles'){
		$("#filterBtnId").removeClass('readonly');
	    checkForFilterByRole();
	}else{
		$("#filterBtnId").addClass('readonly');
	}
}
function loadLeftPane(data){
debugger;	   
	$('.pagination').children().remove();
	$(".leftPaneData").html("");
	checkForFilterOnTabLoad();
	if(data.length==0)
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
	$.each(data,function(key,value){
		partnerArray["partner"+value.bPartnerId]=value;
		if(i==0){
			firstRow = value;
			leftPanelHtml = leftPanelHtml + ' <li onclick="showPartnerDetails('+value.bPartnerId+')" class="active">'
		}else{
			leftPanelHtml = leftPanelHtml + ' <li onclick="showPartnerDetails('+value.bPartnerId+')">'
		}
		leftPanelHtml = leftPanelHtml +' <a href="#Master" data-toggle="tab">'
        +' <div class="col-md-12">'
        +'  <label class="col-xs-6"> '+value.name+'</label>'
        +'	<label class="col-xs-6 ">'+value.crnNumber+'</label>'
        +' </div>'	
        +' <div class="col-md-12">'
        +'	<label class="col-xs-6">'+value.panNumber+'</label>';
        if(value.gstinNo !=null){
        	leftPanelHtml = leftPanelHtml	+'	<label class="col-xs-6">'+value.gstinNo+'</label>';
		}
	
		leftPanelHtml = leftPanelHtml+' </div>'
        +' </a>'
        +' </li>';
		i++;
	});
	$(".leftPaneData").html(leftPanelHtml);
	/*$('.leftPaneData').paginathing();*/
	
	loadRightPane(firstRow);
	 $(".tabs-left li a label").text(function(index, currentText) {
		    return currentText.substr(0, 20);
	 });
	 
}
function showPartnerDetails(id) {
	var partnerData=partnerArray["partner"+id];
	loadRightPane(partnerData);
	
}
/*function loadRightPane(data){
	
	editMode=false;
	activeTabName="";
	var panCardCopyId = data.panCardCopy == null ? '': data.panCardCopy.attachmentId == null ? '': data.panCardCopy.attachmentId;
	var panCardCopyName = data.panCardCopy == null ? '': data.panCardCopy.fileName == null ? '': data.panCardCopy.fileName;

	var GSTINCopyId = data.gstinCopy == null ? '': data.gstinCopy.attachmentId == null ? '': data.gstinCopy.attachmentId;
	var GSTINCopyName = data.gstinCopy == null ? '': data.gstinCopy.fileName == null ? '' : data.gstinCopy.fileName;

	var companyRegCertificateId = data.companyRegCertificate == null ? '': data.companyRegCertificate.attachmentId == null ? '': data.companyRegCertificate.attachmentId;
	var companyRegCertificateName = data.companyRegCertificate == null ? '': data.companyRegCertificate.fileName == null ? '' : data.companyRegCertificate.fileName;
	
	var partnershipDEEDCopyId = data.partnershipDEEDCopy == null ? '': data.partnershipDEEDCopy.attachmentId == null ? '': data.partnershipDEEDCopy.attachmentId;
	var partnershipDEEDCopyName = data.partnershipDEEDCopy == null ? '': data.partnershipDEEDCopy.fileName == null ? '' : data.partnershipDEEDCopy.fileName;
	
	companyName = data.name == null ? '':data.name;
	registrationNo = data.crnNumber==null?'':data.crnNumber;
	panNo= data.panNumber==null?'':data.panNumber;
	companyType=data.companyType==null?'':data.companyType;
	var status=data.status==null?'':data.status;
	var eeComment=data.eeComment==null?'':data.eeComment;
	var isEEApproved=data.isEEApproved==null?'':data.isEEApproved;
	var ceComment=data.ceComment==null?'':data.ceComment;
	var isCEApproved=data.isCEApproved==null?'':data.isCEApproved;
	
	var eeCompDetailComment=data.eeCompDetailComment==null?'':data.eeCompDetailComment;
	var isEECompDetailApproved=data.isEECompDetailApproved==null?'':data.isEECompDetailApproved;
	var ceCompDetailComment=data.ceCompDetailComment==null?'':data.ceCompDetailComment;
	var isCECompDetailApproved=data.isCECompDetailApproved==null?'':data.isCECompDetailApproved;
	var isRenewed=data.isRenewed==null?'':data.isRenewed;
	var isTraderRenewed=data.isTraderRenewed==null?'':data.isTraderRenewed;
	
	var officeLocation=data.officeLocation==null?'':data.officeLocation.officeLocationId==null?'':data.officeLocation.officeLocationId;
	var officeType=data.officeType==null?'':data.officeType.locationTypeId==null?'':data.officeType.locationTypeId;
	$("#labelName").html(data.name);
	$("#labelPanNumber").html(data.panNumber);
	$("#labelCrnNumber").html(data.crnNumber);
	$("#labelGstinNo").html(data.gstinNo);	
	$("#name").val(data.name);
	$("#bPartnerId").val(data.bPartnerId);
	$(".bPartnerId").val(data.bPartnerId);
	$("#companyType").val(data.companyType);
	$("#contractorType").val(data.contractorType);
	$("#crnNumber").val(data.crnNumber);
	$("#panNumber").val(data.panNumber);
	$("#gstinNo").val(data.gstinNo);
	$("#companyDetails #panCardCopy").val(panCardCopyId);
	$("#companyDetails #companyRegCertificate").val(companyRegCertificateId);
	$("#companyDetails #GSTINCopy").val(GSTINCopyId);
	$("#companyDetails #partnershipDEEDCopy").val(partnershipDEEDCopyId);
	$("#companyDetails #officeType").val(officeType);
	loadOfficeLocation();
	$("#companyDetails #officeLocation").val(officeLocation);
	setStarRating('rating',data.rating);
	showContrctorType(data.contractorType);
	setAttribute("isGstApplicable", data.isGstApplicable);
	gstNumberShow(data.isGstApplicable);
	setAttribute("isCustomer", data.isCustomer);
	setAttribute("isContractor",data.isContractor);
	setAttribute("isManufacturer", data.isManufacturer);
	setAttribute("isTrader",data.isTrader);
	showFactoryDetailTab(data.isManufacturer);
	showManufacturerTab(data.isTrader);
	showVendorPaymentTab(data.isManufacturer,data.isTrader);
	changeFieldsForPartnerStatus(data);
	changeFormFieldName();
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
	
	showCEFileDiv();
	$(".approveCEDiv").hide();
    $(".approveEEDiv").hide();
	showPartnerFileDeleteBtn(panCardCopyId, GSTINCopyId,companyRegCertificateId,partnershipDEEDCopyId);
	changeCommentAndStatusByRole('companyDetails',isEECompDetailApproved,eeCompDetailComment,isCECompDetailApproved,ceCompDetailComment);
    partnerStatus=showPartnerStatusAlert(status,isEEApproved,isCEApproved);
	setPartnerHeaderValues("Company Name: "+companyName, "Company Registration No. : "+registrationNo, "Company Type : "+companyType,"Status : "+partnerStatus);
	setChildLoadFlag(true);
	$("#partnerRenewalLabel").html("");
	if(isTraderRenewed=="N" || isRenewed=="N"){
		$("#partnerRenewalStatusDiv").css('display','block');
	    $("#partnerRenewalLabel").html("Vendor is expired");
	}
	checkForFilterByRole();
}*/
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
	debugger;
	$("#partnerOrgRenewalLabel").html("");
	var msg="Following Factories are expired: ";
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
function showRejectedPaymentList(payments)
{
	debugger;  
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
	
}
function showPartnerStatusAlert(status,isEEApproved,isCEApproved)
{
	if (status == 'IP' && (isEEApproved == "" || isEEApproved == null)) {
		
		return "Submitted";
	} else if (status == 'DR' || status=="") {
		
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
	} else if (status == 'IP' && isEEApproved == "Y") {
		
		return "Approved By Executive Engineer";
	}
}
function showContrctorType(contractorType)
{
	if(contractorType=="" || contractorType==null)
	{
		$("#contractorTypeDiv").hide();
		$("#contractorType").attr('disabled','disabled');
	}
	else{
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
		   $("#ceSignCopyDivId").show();
		   $("#ceSignCopyId").addClass("requiredFile");
		}else{
		   $("#ceSignCopyDivId").hide();
		   $("#ceSignCopyId").removeClass("requiredFile");
		}
}
function showPartnerRegistrationFiles(data)
{
	   
	var ceSignCopyId = data.ceSignCopy == null ? '': data.ceSignCopy.attachmentId == null ? '': data.ceSignCopy.attachmentId;
	var ceSignCopyName = data.ceSignCopy == null ? '': data.ceSignCopy.fileName == null ? '': data.ceSignCopy.fileName;
	var partnerSignCopyId = data.partnerSignCopy == null ? '': data.partnerSignCopy.attachmentId == null ? '': data.partnerSignCopy.attachmentId;
	var partnerSignCopyName = data.partnerSignCopy == null ? '': data.partnerSignCopy.fileName == null ? '': data.partnerSignCopy.fileName;
	var partnerCoSignCopyId = data.partnerCoSignCopy == null ? '': data.partnerCoSignCopy.attachmentId == null ? '': data.partnerCoSignCopy.attachmentId;
	var partnerCoSignCopyName = data.partnerCoSignCopy == null ? '': data.partnerCoSignCopy.fileName == null ? '': data.partnerCoSignCopy.fileName;
	var isRegCompleted=data.isRegCompleted==null?'':data.isRegCompleted;
	$("#partnerDetailsApprovalForm #ceSignCopy").val(ceSignCopyId);
	var url = $("#a_ceSignCopy").data('url');
	$("#a_ceSignCopy").attr('href', url);
	var a_ceSignCopy = $("#partnerDetailsApprovalForm #a_ceSignCopy").prop('href') + '/'+ ceSignCopyId;
	$("#partnerDetailsApprovalForm #a_ceSignCopy").prop('href', a_ceSignCopy);
	$("#partnerDetailsApprovalForm #a_ceSignCopy").html(ceSignCopyName);
	
	$("#partnerDetailsApprovalForm #partnerSignCopy").val(partnerSignCopyId);
	var url = $("#a_partnerSignCopy").data('url');
	$("#a_partnerSignCopy").attr('href', url);
	var a_partnerSignCopy = $("#partnerDetailsApprovalForm #a_partnerSignCopy").prop('href') + '/'+ partnerSignCopyId;
	$("#partnerDetailsApprovalForm #a_partnerSignCopy").prop('href', a_partnerSignCopy);
	$("#partnerDetailsApprovalForm #a_partnerSignCopy").html(partnerSignCopyName);
	
	$("#partnerDetailsApprovalForm #partnerCoSignCopy").val(partnerCoSignCopyId);
	var url = $("#a_partnerCoSignCopy").data('url');
	$("#a_partnerCoSignCopy").attr('href', url);
	var a_partnerCoSignCopy = $("#partnerDetailsApprovalForm #a_partnerCoSignCopy").prop('href') + '/'+ partnerCoSignCopyId;
	$("#partnerDetailsApprovalForm #a_partnerCoSignCopy").prop('href', a_partnerCoSignCopy);
	$("#partnerDetailsApprovalForm #a_partnerCoSignCopy").html(partnerCoSignCopyName);
	
	$("#partnerCoSignCopy").attr('disabled','disabled');
	$("#partnerCoSignCopyId").removeClass("requiredFile");
	$(".ceSignCopyDiv").removeClass("readonly");
    showCEFileDiv();
    
	var partnerData =  $("#partnerData").val();
	if((data.status=="CO" && data.isCEApproved=="Y") && (isRegCompleted=="N" || isRegCompleted=="") && partnerData=="partnerRegistration" && (data.isManufacturer=="Y" || data.isTrader=="Y"))
		{
		  $(".ceSignCopyDiv").addClass("readonly");
		  $("#ceSignCopyDivId").show();
		  $("#cosignLabel").text("DownLoad Sign File");
		  $(".ceSignCopyDiv").hide();
		  $("#partnerCoSignCopyId").addClass("requiredFile");
		  $("#partnerCoSignCopy").removeAttr('disabled','disabled');
		  $("#partnerCoSignCopyDivId").css("display","block");
		  $("#partnerCoSignCopyDivId").removeClass("readonly");
		  $("#partnerRegCompleteDivId").css("display","block");
		  $("#partnerSubmitRespDivId").hide();
		}
	 if((partnerCoSignCopyId!="" && isRegCompleted=="Y")&& (data.status=="CO" && data.isCEApproved=="Y") && partnerData=="partnerRegistration")
		 {
		    $("#partnerSubmitRespDivId").show();
			$("#submitRespLabelId").html("Your Registration Process Completed!");
			$(".partnerCoSignCopyDiv").addClass("readonly");
			$("#partnerRegCompleteDivId").hide();
		 }
	showPartnerRegistrationFileDelBtn(partnerSignCopyId,ceSignCopyId,partnerCoSignCopyId);
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
/*function changeFieldsForPartnerStatus(partnerStatus)
{
	debugger;
	var partnerData =  $("#partnerData").val();
	var role=$("#roleData").val();
	  if(partnerData == 'partnerProfiles' && role=="EXEENGR")
		  {
		     if((partnerStatus.status=="IP" && (partnerStatus.isEEApproved=="" || partnerStatus.isEEApproved==null )) || (partnerStatus.status=="CEC" && partnerStatus.isEEApproved=="Y"))
		    	 {
		    	    $(".partnerTabs").addClass("readonly");
					$(".approveEEDiv").show();
					if(partnerStatus.status=="CEC" && partnerStatus.isEEApproved=="Y"){
					$(".approveCEDiv").show();}
					$("#partnerEditBtnId").hide();
					$(".remark").removeClass('readonly');
					$(".statusBtn").removeClass('readonly');
					$(".disableBtn").removeClass('readonly');
					$("#partnerDetailsApprovalForm #partnerApprovalDivId").show();
					$("#partnerDetailsApprovalForm #partnerSubmitRespDivId").hide();
					$("#partnerDetailsApprovalForm #partnerSubmitDivId").hide();
		    	 }else if(partnerStatus.status=="EEC" && partnerStatus.isEEApproved=="C"){
		    		 var remarks=partnerStatus==null?'':partnerStatus.eeComment==null?'':partnerStatus.eeComment;
		    		    $(".partnerTabs").addClass("readonly");
		    		    $(".approveEEDiv").show();
						$(".approveCEDiv").show();
						$(".remark").removeClass('readonly');
						$(".statusBtn").removeClass('readonly');
						$(".disableBtn").removeClass('readonly');
						$("#partnerEditBtnId").hide();
						$("#partnerDetailsApprovalForm #partnerSubmitRespDivId").show();
						$("#submitRespLabelId").html("Partner Clarified For: " + remarks);
						$("#partnerDetailsApprovalForm #partnerSubmitDivId").hide();
						$("#partnerDetailsApprovalForm #partnerApprovalDivId").hide();
		    	 }else  if((partnerStatus.status=="IP" || partnerStatus.status=="CO" || partnerStatus.status=="RJ" )&& partnerStatus.isEEApproved=="Y"){
		    		 var remarks='';
		    		 if(partnerStatus.status=="IP" || partnerStatus.status=="CO"){
		    			 remarks=partnerStatus==null?'':partnerStatus.eeComment==null?'':partnerStatus.eeComment;
		    		 }else if(partnerStatus.status=="RJ"){
		    			 remarks=partnerStatus==null?'':partnerStatus.ceComment==null?'':partnerStatus.ceComment;
		    		 }
		    		$(".partnerTabs").addClass("readonly");
		 			$(".approveEEDiv").show();
		 			$(".approveCEDiv").show();
		 			$(".remark").removeClass('readonly');
		 			$(".statusBtn").removeClass('readonly');
		 			$(".disableBtn").removeClass('readonly');
		 			$("#partnerEditBtnId").hide();
		 			$("#partnerDetailsApprovalForm #partnerSubmitRespDivId").show();
		 			$("#submitRespLabelId").html("Partner Approved: " +remarks) ;
		 			$("#partnerDetailsApprovalForm #partnerSubmitDivId").hide();
		 			$("#partnerDetailsApprovalForm #partnerApprovalDivId").hide();
		    	 }else if(partnerStatus.status=="RJ" && partnerStatus.isEEApproved=="N"){
		    		 var remarks=partnerStatus==null?'':partnerStatus.eeComment==null?'':partnerStatus.eeComment;
		    		 $(".partnerTabs").addClass("readonly");
		 			$(".approveEEDiv").show();
		 			$(".approveCEDiv").show();
		 			$(".remark").addClass('readonly');
		 			$(".statusBtn").addClass('readonly');
		 			$(".disableBtn").addClass('readonly');
		 			$("#partnerEditBtnId").hide();
		 			$("#partnerDetailsApprovalForm #partnerSubmitRespDivId").show();
		 			$("#submitRespLabelId").html("Partner Rejected For: " + remarks);
		 			$("#partnerDetailsApprovalForm #partnerSubmitDivId").hide();
		 			$("#partnerDetailsApprovalForm #partnerApprovalDivId").hide();
		    	 }
		  }else if(partnerData == 'partnerProfiles' && role=="CHFENGR"){
			  if(partnerStatus.status=="IP" && (partnerStatus.isCEApproved=="" || partnerStatus.isCEApproved==null))
				  {
				    $(".partnerTabs").addClass("readonly");
					$(".approveEEDiv").show();
					$(".approveCEDiv").show();
					$("#partnerEditBtnId").hide();
					$(".remark").removeClass('readonly');
					$(".statusBtn").removeClass('readonly');
					$(".disableBtn").removeClass('readonly');
					$("#partnerDetailsApprovalForm #partnerApprovalDivId").show();
					$("#partnerDetailsApprovalForm #partnerSubmitRespDivId").hide();
					$("#partnerDetailsApprovalForm #partnerSubmitDivId").hide();
				  }else if(partnerStatus.status=="CO" && partnerStatus.isCEApproved=="Y"){
					  var remarks=partnerStatus==null?'':partnerStatus.ceComment==null?'':partnerStatus.ceComment;
					    $(".partnerTabs").addClass("readonly");
			 			$(".approveEEDiv").show();
			 			$(".approveCEDiv").show();
			 			$(".remark").removeClass('readonly');
			 			$(".statusBtn").removeClass('readonly');
			 			$(".disableBtn").removeClass('readonly');
			 			$("#partnerEditBtnId").hide();
			 			$("#partnerDetailsApprovalForm #partnerSubmitRespDivId").show();
			 			$("#submitRespLabelId").html("Partner Approved: " +remarks) ;
			 			$("#partnerDetailsApprovalForm #partnerSubmitDivId").hide();
			 			$("#partnerDetailsApprovalForm #partnerApprovalDivId").hide();
				  }else if(partnerStatus.status=="CEC" && partnerStatus.isCEApproved=="C"){
					  var remarks=partnerStatus==null?'':partnerStatus.ceComment==null?'':partnerStatus.ceComment;
					    $(".partnerTabs").addClass("readonly");
						$(".approveEEDiv").show();
						$(".approveCEDiv").show();
						$(".remark").removeClass('readonly');
						$(".statusBtn").removeClass('readonly');
						$(".disableBtn").removeClass('readonly');
						$("#partnerEditBtnId").hide();
						$("#partnerDetailsApprovalForm #partnerSubmitRespDivId").show();
						$("#submitRespLabelId").html("Partner Clarified For: " + remarks);
						$("#partnerDetailsApprovalForm #partnerSubmitDivId").hide();
						$("#partnerDetailsApprovalForm #partnerApprovalDivId").hide();
				  }else if(partnerStatus.status=="RJ" && partnerStatus.isCEApproved=="N"){
					  var remarks=partnerStatus==null?'':partnerStatus.ceComment==null?'':partnerStatus.ceComment;
					    $(".partnerTabs").addClass("readonly");
			 			$(".approveEEDiv").show();
			 			$(".approveCEDiv").show();
			 			$(".remark").addClass('readonly');
			 			$(".statusBtn").addClass('readonly');
			 			$(".disableBtn").addClass('readonly');
			 			$("#partnerEditBtnId").hide();
			 			$("#partnerDetailsApprovalForm #partnerSubmitRespDivId").show();
			 			$("#submitRespLabelId").html("Partner Rejected For: " + remarks);
			 			$("#partnerDetailsApprovalForm #partnerSubmitDivId").hide();
			 			$("#partnerDetailsApprovalForm #partnerApprovalDivId").hide();
				  }
		  }
	   if((partnerStatus.status == 'IP'|| partnerStatus.status=='CO') && partnerData == 'partnerProfiles') {
		
		var remarks=partnerStatus.remarks==null?'':partnerStatus.remarks;
		$(".cancel").attr("disabled","disabled");
		if(partnerStatus.isApproved == 'Y') {
			
			$(".partnerTabs").addClass("readonly");
			$(".approveEEDiv").show();
			$(".approveCEDiv").show();
			$(".remark").removeClass('readonly');
			$(".statusBtn").removeClass('readonly');
			$(".disableBtn").removeClass('readonly');
			
			$("#partnerEditBtnId").hide();
			$("#partnerDetailsApprovalForm #partnerSubmitRespDivId").show();
			$("#submitRespLabelId").html("Partner Approved: " +remarks) ;
			$("#partnerDetailsApprovalForm #partnerSubmitDivId").hide();
			$("#partnerDetailsApprovalForm #partnerApprovalDivId").hide();
		}else if(partnerStatus.isApproved == 'N') {

			$(".partnerTabs").addClass("readonly");
			$(".approveEEDiv").show();
			$(".approveCEDiv").show();
			$(".remark").addClass('readonly');
			$(".statusBtn").addClass('readonly');
			$(".disableBtn").addClass('readonly');
			$("#partnerEditBtnId").hide();
			$("#partnerDetailsApprovalForm #partnerSubmitRespDivId").show();
			$("#submitRespLabelId").html("Partner Rejected For: " + remarks);
			$("#partnerDetailsApprovalForm #partnerSubmitDivId").hide();
			$("#partnerDetailsApprovalForm #partnerApprovalDivId").hide();
		}else if(partnerStatus.isApproved == 'C' || partnerStatus.isCEApproved=='C')
			{
				$(".partnerTabs").addClass("readonly");
				$(".approveEEDiv").show();
				$(".approveCEDiv").show();
				$(".remark").removeClass('readonly');
				$(".statusBtn").removeClass('readonly');
				$(".disableBtn").removeClass('readonly');
				$("#partnerEditBtnId").hide();
				$("#partnerDetailsApprovalForm #partnerSubmitRespDivId").hide();
				$("#submitRespLabelId").html("Partner Clarified For: " + remarks);
				$("#partnerDetailsApprovalForm #partnerSubmitDivId").hide();
				$("#partnerDetailsApprovalForm #partnerApprovalDivId").show();
			
			}else if(partnerStatus.isApproved == null|| partnerStatus.isApproved == "") {

			$(".partnerTabs").addClass("readonly");
			$(".approveEEDiv").show();
			$(".approveCEDiv").show();
			$("#partnerEditBtnId").hide();
			$(".remark").removeClass('readonly');
			$(".statusBtn").removeClass('readonly');
			$(".disableBtn").removeClass('readonly');
			$("#partnerDetailsApprovalForm #partnerApprovalDivId").show();
			$("#partnerDetailsApprovalForm #partnerSubmitRespDivId").hide();
			$("#partnerDetailsApprovalForm #partnerSubmitDivId").hide();
		}
		   
	}else if((partnerStatus.status=='IP' || partnerStatus.status=='CO' ||  partnerStatus.status=='RJ' || partnerStatus.status=='EEC' || partnerStatus.status=='CEC') &&  partnerData!= 'partnerProfiles') 
		{
			  $(".approveEEDiv").hide();
			  $(".approveCEDiv").hide();
			  $(".remark").addClass('readonly');
			  $(".statusBtn").addClass('readonly');
			  $(".disableBtn").addClass('readonly');
			  $(".partnerTabs").addClass("readonly");
			  if(partnerStatus.status=='CO' || partnerStatus.status=='EEC')
				{
				  $("#partnerEditBtnId").show(); 
				}else{
				  $("#partnerEditBtnId").hide(); 
				}
			  
			$("#partnerDetailsApprovalForm #partnerSubmitRespDivId").show();
			$("#submitRespLabelId").html("Your Registration Profile Has Been Submitted !");
			$("#partnerDetailsApprovalForm #partnerSubmitDivId").hide();
			$("#partnerDetailsApprovalForm #partnerApprovalDivId").hide();
			$(".cancel").removeAttr("disabled","disabled");
		 }else if((partnerStatus.status=='DR' || partnerStatus.status=='EDIT' || partnerStatus.status==null || partnerStatus.status=='' )&&  partnerData!= 'partnerProfiles')
		   {
			    $(".approveEEDiv").hide();
				$(".approveCEDiv").hide();
				$("#partnerEditBtnId").hide();
				$(".remark").addClass("readonly");
				$(".statusBtn").addClass("readonly");
				$(".disableBtn").removeClass('readonly');
				$(".disableBtn").removeAttr('disabled','disabled');
				$(".partnerTabs").removeClass("readonly");
				$("#partnerDetailsApprovalForm #partnerSubmitDivId").show();
				$("#partnerDetailsApprovalForm #partnerSubmitRespDivId").hide();
				$("#partnerDetailsApprovalForm #partnerApprovalDivId").hide();
				$("#partnerCoSignCopyDivId").css("display","none");
				$("#partnerCoSignCopyDivId").addClass("readonly");
				$("#partnerRegCompleteDivId").css("display","none");
				$(".cancel").removeAttr("disabled","disabled");
		   }
			 
	   showPartnerRegistrationFiles(partnerStatus);
}
*/
function showFactoryDetailTab(data){
	if(data=='Y')
		{
		  $("#factoryDetailTabId").show();
		  
		}
	else{
		$("#factoryDetailTabId").hide();
	}
	showVendorPaymentTab(data,null);
	
}
function showManufacturerTab(data)
{   
	if(data=='Y')
    {
		$("#manufacturerTabId").show();
		
    }else{
    	$("#manufacturerTabId").hide();
    	
    }
	showVendorPaymentTab(null,data);
	
}
function showVendorPaymentTab(isManufacturer,isTrader)
{
	if(isManufacturer=='Y' || isTrader=="Y" ||  $("#isManufacturer"). prop("checked") == true || $("#isTrader"). prop("checked") == true)
    {
		$("#vendorPaymentTabId").show();
    }else{
    	$("#vendorPaymentTabId").hide();
    }
}


function gstnumShow() {
	 if ($('#isGstApplicable').is(":checked")){
	        $(".GSTidenNumb").show();
	        $('.defaultshow').show();
	        $("#gstinNo").addClass('requiredField');
	        }
	    else{
	        $(".GSTidenNumb").hide();
	    	$("#gstinNumber").val("");
	    	 $("#gstinNo").val("");
	    	 $('.defaultshow').hide();
             $("#gstinNo").removeClass('requiredField');
	    }
}

function gstNumberShow(value) {
	 if ("Y"== value){
	     $(".GSTidenNumb").show();
	     $("#GSTINCopyId").addClass("requiredFile");
			
	 }else{
	     $(".GSTidenNumb").hide();
	     $("#gstinNumber").val("");
	     $("#GSTINCopyId").removeClass("requiredFile");
	 }
}

function loadContractorType(data){
	$("#companyDetails #contractorType").html("");
	var options = '<option></option>';
	$.each(data,function(key,value){
		options +='<option value="'+value.code+'">'+value.name +'</option>'
		
	});

	$("#companyDetails #contractorType").append(options);
}

function loadCompanyType(data){
	$("#companyDetails #companyType").html("");
	var options = '<option></option>';
	$.each(data,function(key,value){
		options +='<option value="'+value.code+'">'+value.name +'</option>'
		
	});

	$("#companyDetails #companyType").append(options);
}

function partnerDetailsSubmitResp(data){
debugger;
	$('#loading-wrapper').hide();
	if($.isEmptyObject(data))
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
		        	  window.location.href="/eatApp/"; 
		          } 
		        });
		}else{
			if(!$.isEmptyObject(data.errors))
				{
				  var msg=data.message;
				/*var msg='Following Tabs Are Not Filled: ';*/
				 $.each(data.errors,function(key,value){
				       msg=msg+'\n'+value.errorMessage+'\n'+',';
				       
				   });
				Alert.warn(msg);
			}
		}
	}

function partnerProfileEditResp(data) {
	   
	if (!data.response.hasError) {
		$('#loading-wrapper').hide();
		$("#partnerEditBtnId").hide();
		getPartnerInfo();

	}
}

function PanAttachmentDeleteResp(data) {
	
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



function GSTINAttachmentDeleteResp(data) {

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
function companyRegCertDeleteResp(data) {
	
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
	debugger;  
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
function showApproveLoader(event)
{
   
	event.preventDefault();
	submitIt('partnerDetailsApprovalForm','partnerRegistrationApproval','partnerApprovalResp');	
}
function partnerApprovalResp(data)
{
	   
	if(!data.hasError)
	{
		vendorStatus="";
		partneSearchStatus='';
		var response=getVendorBySearch(1,pageSize,'none','none');
		loadSearchVendorsResp(response);
	/*submitToURL("getPartners", 'onPageLoad');*/
	$("#partnerDetailsApprovalForm #partnerSubmitRespDivId").show();
	$("#submitRespLabelId").html(data.message);
	$("#partnerDetailsApprovalForm #partnerSubmitDivId").hide();
	$("#partnerDetailsApprovalForm #partnerApprovalDivId").hide();
	$("#partnerDetailsApprovalForm #remarks").val("");
	}else
		{
		
		if(!$.isEmptyObject(data.errors))
		{
		   /*var msg='Please do clarification or rejection as you have not approved following tabs: ';*/
			var msg=data.message;
			$.each(data.errors,function(key,value){
		       msg=msg+'\n'+value.errorMessage+'\n'+',';
		       
		   });
		    Alert.warn(msg);
		}else{
			Alert.warn(data.message);
		}
	}
	$('#loading-wrapper').hide();
}
function getLoaderForEdit(event)
{
	event.preventDefault();
	$('#loading-wrapper').show();
	submitWithParam('updatePartnerProfile','bPartnerId','partnerProfileEditResp');	
}
function onConfirmationTabLoad(data)
{
	debugger; 
	changeFieldsForPartnerStatus(data);
	setPartnerHeaderValues("Company Name: "+companyName, "Company Registration No. : "+registrationNo, "Company Type : "+companyType,"Status : "+partnerStatus);
}
function setPartnerHeaderValues(leftTop,rightTop,leftBottom,rightBottom){
	$(".detailscont").empty();
	$(".detailscont").append('<div class="col-md-12">'+
            '<label class="col-md-6">'+leftTop+'</label>'+
            '<label class="col-md-6">'+rightTop+'</label>'+
        '</div>'+
        '<div class="col-md-12" style="margin-top:10px;">'+
            '<label class="col-md-6">'+leftBottom+'</label>'+
            '<label class="col-md-6">'+rightBottom+'</label>'+
           /* '<label class="col-md-4">'+fifthValue+'</label>'+*/
        '</div>');
}
function ceSignCopyDelResp(data)
{
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
function partnerSignCopyDelResp(data)
{
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
function completeRegistrationProcess(event)
{
	   
	event.preventDefault();
	submitIt('partnerDetailsApprovalForm','completePartnerRegistration','completePartnerRegistrationResp');
}
function completePartnerRegistrationResp(data)
{
	   debugger;
	if(!data.response.hasError)
		{
			$("#partnerSubmitRespDivId").show();
			$("#submitRespLabelId").html(data.response.message);
			$(".partnerCoSignCopyDiv").addClass("readonly");
			$("#partnerRegCompleteDivId").hide();
		}else{
			if(!$.isEmptyObject(data.response.errors))
			{
			    var msg=data.response.message;
				 $.each(data.response.errors,function(key,value){
				       msg=msg+'\n'+value.errorMessage+'\n'+',';
				       
				   });
				Alert.warn(msg);
			}else{
			   Alert.warn(data.response.message);
			}
			
		}
}
function partnerCoSignCopyDelResp(data)
{
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
function partnershipDEEDCopyDelResp(data)
{
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