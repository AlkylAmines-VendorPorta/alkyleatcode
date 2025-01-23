var bankDetailArray=new Array();
$(document).ready(function(){
	$('#cancelBankBtnId').click(function(event) {
		event.preventDefault();
		editMode=false;
	   	/*activeTabName="";*/
		var activeBankId=$("#partnerBankDetailsForm  #partnerBankDetailsId").val();
		if(activeBankId!="" || activeBankId!=undefined)
			{
			   showBankDetail(activeBankId);
			}else{
				$("#partnerBankDetailsForm  #partnerBankDetailsId").val("");
				$('#partnerBankDetailsForm')[0].reset();
			    $('#bankDetailFileId').val('');
			    $('#bankDetailFile').val('');
			    $("#a_bankDetailFile").html('');
			    $('.bankDetailFile').attr('disabled', true);
		    }
    });
	$("#partnerBankDetailsForm").find("input,select,textarea").change(function() {
	   	 editMode=true;
	   	 activeTabName="Bank Details";
	   });
	$("#partnerBankDetailsForm .fileDeleteBtn").click(function() {
	   	 editMode=true;
	   	 activeTabName="Bank Details";
	     requiredFileDeleted=true;
    });
});
function getPartnerBankDetails(event,el)
{
	event.preventDefault();	
		if(!editMode && !requiredFileDeleted){
			$('#pagination-here').empty();
			cacheLi();
			setCurrentTab(el);
			if(getChangedFlag()){
			  submitWithParam('getPartnerBankDetails','bPartnerId','onBankDetailsTabLoad');	
			  setChangedFlag(false);
			}else{
				getCacheLi();
			}
			setActiveTabName("Bank Details",$('.leftPaneData li').length);
			setPartnerHeaderValues("Company Name: "+companyName, "Company Registration No. : "+registrationNo, "Vendor SAP Code : "+vendorSAPCode,"Status : "+partnerStatus);
			/*setPartnerHeaderValues("Company Name: "+companyName, "Company Registration No. : "+registrationNo, "Company Type : "+companyType,"Status : "+partnerStatus);*/
		}else{
			event.stopPropagation();
	        Alert.warn("Please Save Changed Data Of "+activeTabName+" Tab");
		}
		$("#filterBtnId").addClass("readonly");
	    checkForFilterByRole();
}
function partnerBankDetailsResp(data){
	
	if(!isEmpty(data)){
	if ($("#partnerData").val() == "partnerRegistration") {
		$("#partnerBankDetailsForm  .partnerOrgEEApproveDiv").hide();
		$("#partnerBankDetailsForm  .partnerOrgCEApproveDiv").hide();
	}
	$('.pagination').children().remove();	
	setChildLoadFlag(true);
	editMode=false;
	activeTabName="";
	requiredFileDeleted=false;
	var leftPanelHtml='';
	var active=true;
	
		var partnerBankDetailId=data.partnerBankDetailId;
		var currentBankDetailId=$('ul.leftPaneData').find('li.active').attr('id');
		$("#partnerBankDetailsForm #partnerBankDetailsId").val(partnerBankDetailId);
		if(currentBankDetailId==partnerBankDetailId)
		{
			$('#'+currentBankDetailId).remove();
		}else{
			$('#'+currentBankDetailId).removeClass('active');
		}
		
		leftPanelHtml=appendBankDetaisData(data,active);
		$(".leftPaneData").prepend(leftPanelHtml);
		if(data.bankDetailFile!=null){
		   data.bankDetailFile.fileName=$("#partnerBankDetailsForm #a_bankDetailFile").html();
		}
		bankDetailArray["bank"+partnerBankDetailId]=data;
		active=false;
	    Alert.info(data.response.message);
	}
		
    $('.leftPaneData').paginathing();
    setPartnerHeaderValues("Company Name: "+companyName, "Company Registration No. : "+registrationNo, "Vendor SAP Code : "+vendorSAPCode,"Status : "+partnerStatus);
    /*setPartnerHeaderValues("Company Name: "+companyName, "Company Registration No. : "+registrationNo, "Company Type : "+companyType,"Status : "+partnerStatus);*/
}


function onPaTabClick(){
	submitToURL("getPartner", 'onBankDetailsTabLoad');	
}

function onBankDetailsTabLoad(data){
	if(!isEmpty(data) && !isEmpty(data.objectMap)){
		if(data.objectMap.hasOwnProperty('bankName')){
			loadBankNameData(data.objectMap.bankName);
			}
			if(data.objectMap.hasOwnProperty('partnerBankDetails'))
			{
				loadBankDetailsLeftPane(data.objectMap.partnerBankDetails);
				setPartnerHeaderValues("Company Name: "+companyName, "Company Registration No. : "+registrationNo, "Vendor SAP Code : "+vendorSAPCode,"Status : "+partnerStatus);
				 /*setPartnerHeaderValues("Company Name: "+companyName, "Company Registration No. : "+registrationNo, "Company Type : "+companyType,"Status : "+partnerStatus);*/
			}
	}
	setActiveTabName("Bank Details",$('.leftPaneData li').length);
}

function loadBankDetailsLeftPane(data){
	
	$('.pagination').children().remove();
	$(".leftPaneData").html("");
	var leftPanelHtml="";
	var i=0;
	var active=false;
	var firstRow=null;
	if(!isEmpty(data)){
		$.each(data,function(key,value){
			if(!isEmpty(value)){
				var partnerBankDetailsId = value.partnerBankDetailId==null?'':value.partnerBankDetailId;
				bankDetailArray["bank"+partnerBankDetailsId]=value;
				if(i==0){
					firstRow = value;
					active=true;
				}
				leftPanelHtml=leftPanelHtml+appendBankDetaisData(value,active);
				active=false;
			 i++;
			}
		});
	}
	$(".leftPaneData").html(leftPanelHtml);
	$('.leftPaneData').paginathing();
	
	$(".tabs-left li a label").text(function(index, currentText) {
	    return currentText.substr(0, 20);
	});
	loadBankDetailsRightPane(firstRow);

}
function appendBankDetaisData(value,active){
	 var leftPanelHtml='';
	 if(!isEmpty(value)){
		 var partnerBankDetailsId = value.partnerBankDetailId==null?'':value.partnerBankDetailId;
			
			var bankName = value.bankNameDetails==null?'':value.bankNameDetails.bankNameDetailsId==null?'':value.bankNameDetails.bankNameDetailsId;
			var accountNumber = value.accountNumber==null?'':value.accountNumber;
			var ifscCode = value.branchName==null?'':value.branchName.iFSCCode==null?'':value.branchName.iFSCCode;
			var benificaryName = value.benificaryName==null?'':value.benificaryName;
			var mobileNo = value.mobileNo==null?'':value.mobileNo;
			var remark=value.remark==null?'':value.remark;
			var isApproved=value.isApproved==null?'':value.isApproved;
			var eeComment=value.eeComment==null?'':value.eeComment;
			var isEEApproved=value.isEEApproved==null?'':value.isEEApproved;
			var ceComment=value.ceComment==null?'':value.ceComment;
			var isCEApproved=value.isCEApproved==null?'':value.isCEApproved;
			var branchName=value.branchName==null?'':value.branchName.bankBranchDetailsId==null?'':value.branchName.bankBranchDetailsId;
			leftPanelHtml = leftPanelHtml +' <a href="#Master" data-toggle="tab">'
			    +' <div class="col-md-12" style="display: none">'
				+'	<label class="col-xs-6" id="labelMobileNo-'+partnerBankDetailsId+'">'+mobileNo+'</label>'
				+'  <label class="col-xs-6" id="labelbankName-'+partnerBankDetailsId+'">'+bankName+'</label>'
			    +'	<label class="col-xs-6" id="labelBankAccNo-'+partnerBankDetailsId+'">'+accountNumber+'</label>'
			    +'	<label class="col-xs-6" id="labelifscCode-'+partnerBankDetailsId+'">'+ifscCode+'</label>'
			    +'	<label class="col-xs-6" id="labelBenificaryName-'+partnerBankDetailsId+'">'+benificaryName+'</label>'
			    +'	<label class="col-xs-6" id="labelPartnerBankDetailsId-'+partnerBankDetailsId+'">'+partnerBankDetailsId+'</label>'
			    +'	<label class="col-xs-6" id="remark-'+partnerBankDetailsId+'">'+remark+'</label>'
			    +'	<label class="col-xs-6" id="isApproved-'+partnerBankDetailsId+'">'+isApproved+'</label>'
			    +'	<label class="col-xs-6" id="eeComment-'+partnerBankDetailsId+'">'+eeComment+'</label>'
			    +'	<label class="col-xs-6" id="ceComment-'+partnerBankDetailsId+'">'+ceComment+'</label>'
			    +'	<label class="col-xs-6" id="isEEApproved-'+partnerBankDetailsId+'">'+isEEApproved+'</label>'
			    +'	<label class="col-xs-6" id="isCEApproved-'+partnerBankDetailsId+'">'+isCEApproved+'</label>'
			    +'	<label class="col-xs-6" id="labelbranchName-'+partnerBankDetailsId+'">'+branchName+'</label>'
			    +' </div>'
		       +' </a>'
		       +' </li>';
	 }
	return leftPanelHtml;
}
function showBankDetail(id){
	var bankData=bankDetailArray["bank"+id];
	loadBankDetailsRightPane(bankData);
}
function loadBankDetailsRightPane(data){
	
	editMode=false;
	
	setChildLoadFlag(true);
	/*activeTabName="";*/
	if(!$.isEmptyObject(data)){
	var bankName = data.bankNameDetails==null?'':data.bankNameDetails.bankNameDetailsId==null?'':data.bankNameDetails.bankNameDetailsId;
	var accountNumber = data.accountNumber==null?'':data.accountNumber;
    var ifscCode=data.ifscCode==null?'':data.ifscCode;
	/*var ifscCode = data.branchName==null?'':data.branchName.iFSCCode==null?'':data.branchName.iFSCCode;*/
	var benificaryName = data.benificaryName==null?'':data.benificaryName;
	var mobileNo = data.mobileNo==null?'':data.mobileNo;
	var partnerBankDetailsId = data.partnerBankDetailId==null?'':data.partnerBankDetailId;
	var remark=data.remark==null?'':data.remark;
	var isApproved=data.isApproved==null?'':data.isApproved;
	var eeComment=data.eeComment==null?'':data.eeComment;
	var isEEApproved=data.isEEApproved==null?'':data.isEEApproved;
	var ceComment=data.ceComment==null?'':data.ceComment;
	var isCEApproved=data.isCEApproved==null?'':data.isCEApproved;
	var branchName=data.branchName==null?'':data.branchName.bankBranchDetailsId==null?'':data.branchName.bankBranchDetailsId;
	var bankDetailFileId = data.bankDetailFile == null ? '': data.bankDetailFile.attachmentId == null ? '': data.bankDetailFile.attachmentId;
	var bankDetailFileName = data.bankDetailFile == null ? '': data.bankDetailFile.fileName == null ? '': data.bankDetailFile.fileName;
	
	if(bankName!=null){
		$("#partnerBankDetailsForm #bankName").val(bankName);
		loadBranchName();
	}
	if(branchName!=null){
		$("#partnerBankDetailsForm #branchName").val(branchName);
		loadIFSCCode();
	}
	$("#partnerBankDetailsForm #ifscCode").val(ifscCode);
	$("#partnerBankDetailsForm #accountNumber").val(accountNumber);
	$("#partnerBankDetailsForm #benificaryName").val(benificaryName);
	$("#partnerBankDetailsForm #mobileNo").val(mobileNo);
	$("#partnerBankDetailsForm #partnerBankDetailsId").val(partnerBankDetailsId);
	$("#partnerBankDetailsForm #bankDetailFile").val(bankDetailFileId);
	
	var url = $("#a_bankDetailFile").data('url');
	$("#a_bankDetailFile").attr('href', url);
	var a_bankDetailFile = $("#partnerBankDetailsForm #a_bankDetailFile").prop('href') + '/'+ bankDetailFileId;
	$("#partnerBankDetailsForm #a_bankDetailFile").prop('href', a_bankDetailFile);
	$("#partnerBankDetailsForm #a_bankDetailFile").html(bankDetailFileName);
	showBankFileDelBtn(bankDetailFileId);
	changeCommentAndStatusByRole('partnerBankDetailsForm',isEEApproved,eeComment,isCEApproved,ceComment);
	}else{
		$('#partnerBankDetailsForm')[0].reset();
		$("#partnerBankDetailsForm #partnerBankDetailsId").val("");
		$('#bankDetailFileId').val('');
		$('#bankDetailFile').val('');
		$("#a_bankDetailFile").html('');
		$('.bankDetailFile').attr('disabled', true);
		
		
	}
	setPartnerHeaderValues("Company Name: "+companyName, "Company Registration No. : "+registrationNo, "Vendor SAP Code : "+vendorSAPCode,"Status : "+partnerStatus);
	 /*setPartnerHeaderValues("Company Name: "+companyName, "Company Registration No. : "+registrationNo, "Company Type : "+companyType,"Status : "+partnerStatus);*/
}
function showBankFileDelBtn(bankDetailFileId){
	if (bankDetailFileId!= '') {
		$(".bankDetailFile").attr('disabled', false);
	} else {
		$(".bankDetailFile").attr('disabled', true);
	}
}

function bankDetailFileDelResp(data){
	if(!isEmpty(data)){
		if (!data.hasError) {
			$('#bankDetailFileId').val('');
			$('#bankDetailFile').val('');
			$("#a_bankDetailFile").html('');
			$('.bankDetailFile').attr('disabled', true);
			Alert.info(data.message);
			var partnerBankDetailsId=$("#partnerBankDetailsId").val();
			if(partnerBankDetailsId!="")
			{
			  bankDetailArray["bank"+partnerBankDetailsId].bankDetailFile=null;
			}
		} else {
			Alert.warn(data.message);
		}
	}
}

function loadBankNameData(BankNameData){
	
	$("#partnerBankDetailsForm #bankName").html('');
	var options = '<option value="">Select Bank</option>';
	$.each(BankNameData, function(key, value) {
		if(!isEmpty(value)){
			options += '<option value="' + value.bankNameDetailsId + '">' + value.name + '</option>';
		}
	});

	$("#partnerBankDetailsForm #bankName").append(options);
}

function loadBranchName(){
	var optionName = $('#partnerBankDetailsForm #bankName').find('option:selected').val();
	if(optionName!=''){
	 submitToURL('getBranchName/' + optionName,'loadBranchData');
	}
}

function loadBranchData(BranchData){
	
	$("#partnerBankDetailsForm #branchName").html('');
	var options = '<option value="">Select Branch</option>';
	$.each(BranchData, function(key, value) {
		if(!isEmpty(value)){
		options += '<option value="' + value.bankBranchDetailsId + '">' + value.branchName + '</option>';
		}
	});

	$("#partnerBankDetailsForm #branchName").append(options);
}

function loadIFSCCode(){
	var optionName = $('#partnerBankDetailsForm #branchName').find('option:selected').val();
	if(optionName!=undefined){
	 submitToURL('getIFSCCode/' + optionName,'loadIFSC');
	}
}

function loadIFSC(data){
	if(!isEmpty(data) && !isEmpty(data.objectMap)){
		if(!$.isEmptyObject(data.objectMap.ifsc))
		{
			var ifscCode=data.objectMap.ifsc==null?'':data.objectMap.ifsc.iFSCCode==null?'':data.objectMap.ifsc.iFSCCode;
			$("#partnerBankDetailsForm #ifscCode").val(ifscCode);
		 $('#partnerBankDetailsForm #ifscCode').addClass('readonly');
		}
		else{
			$("#partnerBankDetailsForm #ifscCode").val('');
			 $('#partnerBankDetailsForm #ifscCode').addClass('readonly');
		}
	}else{
			$("#partnerBankDetailsForm #ifscCode").val('');
			 $('#partnerBankDetailsForm #ifscCode').addClass('readonly');
	}
}