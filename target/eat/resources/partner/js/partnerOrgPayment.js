var paymentArray=new Array();
var paymentTypeArray=new Array();
var paymentOrgArray=new Array();
var paymentPartnerArray=new Array();
$(document).ready(function(){
	showButtonByRole();
	showReceiptByRole();
	setVendorPaymentType();
	$('#addPaymentBtnId').click(function(event) {
		event.preventDefault();
		$("#renewalValidityDateDiv").css('display','none');
		$("#factoryDivId").css('display','none');
		$("#payOnlinePtnrRegBtn").css('display','none');
		$("#savePaymentBtnId").show();
		$('#partnerOrgPaymentForm')[0].reset();
		$("#partnerOrgPaymentForm #partnerOrgPaymentId").val("");
		$("#partnerOrgPaymentForm #demandDraftDate").val("");
		$("#partnerOrgPaymentForm #demandDraftNo").val("");
		$("#partnerOrgPaymentForm #micrNo").val("");
		$("#partnerOrgPaymentForm #bankName").val("");
		$("#partnerOrgPaymentForm #branchName").val("");
		$("#partnerOrgPaymentForm .approveDiv").hide();
		$("#traderPayment").removeAttr('readonly','readonly');
		$("#manufacturerPayment").removeAttr('readonly','readonly');
		setVendorPaymentType();
		$("#partnerOrgPaymentForm .partnerTabs").removeClass("readonly");
		$("#partnerOrgPaymentForm .disableBtn").removeClass("readonly");
		$("#partnerOrgPaymentForm .disableBtn").removeAttr('disabled','disabled');
		$("#partnerOrgPaymentForm .approveEEDiv").hide();
		$("#partnerOrgPaymentForm .approveCEDiv").hide();
		$(".onlinePayment").removeClass("readonly");
		$(".onlinePaymentBtn").removeAttr('disabled','disbaled');
		$("#partnerOrgPaymentForm #payReceipt").hide();
		$("#partnerOrgPaymentForm #paymentReceiptBtnId").css('display','none');
	});

	$('input[type=radio][name=paymentMode]').change(function(){
			if($(this).val()=="OP"){
			  $(".onlinePaymentFields").find("input").attr('disabled','disabled');
			  $(".hidePaymentFields").hide();
			  $("#savePaymentBtnId").hide();
			  $("#payOnlinePtnrRegBtn").css('display','block');
			}else{
				$(".onlinePaymentFields").find("input").removeAttr('disabled','disabled');
				$(".hidePaymentFields").show();
				$("#savePaymentBtnId").show();
				$("#payOnlinePtnrRegBtn").css('display','none')
			}
	});
	$('#cancelPaymentBtnId').click(function(event) {
		event.preventDefault();
		editMode=false;
	   	/*activeTabName="";*/
		var activePaymentId=$('.leftPaneData').find('li.active').attr('id');
		if(activePaymentId!=undefined)
			{
			 showOrgPaymentDetail(activePaymentId);
			}
		else
		  {   
			$('#partnerOrgPaymentForm')[0].reset();
		    $("#partnerOrgPaymentForm .disableBtn").removeAttr('disabled','disabled');
		  }
    });
	
	 $("#payOnlinePtnrRegBtn").click(function(event){
	    	debugger;
	    	event.preventDefault();
	    	var validate=$("#regPaymentType").val() != '';
	    	var type=$("#paymenttype").find('option:selected').data('code');
	    	var vendorType=$("input[name='vendorTypePayment']:checked"). val();
	    	if(type=="RN" && vendorType=="MP")
	    		{
	    		    $("#onlinePaymentFactoryId").removeAttr('disabled','disabled');
	    		    if($("#onlinePaymentFactoryId").val()=='')
	    		    	{
	    		    	  Alert.warn("Please Select Factory");
	    		    	  return;
	    		    	}
	    		}else{
	    			 $("#onlinePaymentFactoryId").attr('disabled','disabled');
	    		}
	    	if(validate){
	    		 $("#RegonlnPmntForm").submit();
	    	}else{
	    		Alert.warn("Please select Type of payment");
	    	}
	    	
	    });
	
	$("#partnerOrgPaymentForm").find("input,select,textarea").change(function() {
    	 editMode=true;
    	 activeTabName="Payment Details";
    });
	
	$("#partnerOrgPaymentForm #paymentReceiptBtnId").on("click",function(event){
		debugger;
		var id=$("#partnerOrgPaymentForm #partnerOrgPaymentId").val();
		var paymentType=$("#partnerOrgPaymentForm #paymenttype").find('option:selected').html();
		if(paymentType=='Tender/Auction Fee'){
			paymentType='TF'
		}
		else if(paymentType=='Registration Fee'){
			paymentType='RG'
		}
		else if(paymentType=='Registration Renewal Fee'){
			paymentType='RN'
		}
		var anchorHtml="invoice?id="+id+"&paymentType="+paymentType;
		$("#partnerOrgPaymentForm #paymentReceiptBtnId").attr('href',anchorHtml);
	});
	
});

function getPartnerPaymentDetails(event,el)
{
        event.preventDefault();	
        if(!editMode && !requiredFileDeleted){
			$('#pagination-here').empty();
			cacheLi();
			setCurrentTab(el);
			if(getChangedFlag()){
			   submitWithParam('getPaymentDetails','bPartnerId','onOrgPaymentDetailTabLoad');	
			   setChangedFlag(false);
			}else{
				getCacheLi();
			}
			setActiveTabName("Payment Details",$('.leftPaneData li').length);
			setPartnerHeaderValues("Company Name: "+companyName, "Company Registration No. : "+registrationNo, "Company Type : "+companyType,"Status : "+partnerStatus);
		}else{
			event.stopPropagation();
	        Alert.warn("Please Save Changed Data Of "+activeTabName+" Tab");
		}
        $("#filterBtnId").addClass("readonly");
	    checkForFilterByRole();
}
function showButtonByRole()
{
	var partnerData=$("#partnerData").val();
	if(partnerData!="partnerRegistration")
		{
		   $("#partnerOrgPaymentForm .disableBtn").attr('disabled','disabled');
		   /*$("#payOnlinePtnrRegBtn").attr('disabled','disabled');*/
		   $("#payOnlinePtnrRegBtn").css('display','block');
		}else{
			$("#partnerOrgPaymentForm .disableBtn").removeAttr('disabled','disabled');
			/*$("#payOnlinePtnrRegBtn").removeAttr('disabled','disabled');*/
			$("#payOnlinePtnrRegBtn").css('display','none');
		}
}

function showReceiptByRole()
{
	var partnerData=$("#partnerData").val();
	if(partnerData!="partnerRegistration")
		{
		   $("#partnerOrgPaymentForm .disableBtn").attr('disabled','disabled');
		   /*$("#payOnlinePtnrRegBtn").attr('disabled','disabled');*/
		   $("#partnerOrgPaymentForm #payReceipt").hide();
		   $("#partnerOrgPaymentForm #paymentReceiptBtnId").css('display','none');
		}else{
			$("#partnerOrgPaymentForm .disableBtn").removeAttr('disabled','disabled');
			/*$("#payOnlinePtnrRegBtn").removeAttr('disabled','disabled');*/
			 $("#partnerOrgPaymentForm #payReceipt").show();
			$("#partnerOrgPaymentForm #paymentReceiptBtnId").css('display','block');
		}
}


function paymentDelResp(data){
	$('.pagination').children().remove();
	if(data.hasError==false)
	{
	Alert.info(data.message);
	var currentPaymentId=$('ul.leftPaneData').find('li.active').attr('id');
	$('#'+currentPaymentId).remove();
	$('#partnerOrgPaymentForm')[0].reset();
	$("#partnerOrgPaymentForm #partnerOrgPaymentId").val("");
	$("#traderPayment").removeAttr('readonly','readonly');
	$("#manufacturerPayment").removeAttr('readonly','readonly');
	event.preventDefault();
	$("#partnerOrgPaymentForm .disableBtn").removeAttr('disabled','disabled');
	}else{
		Alert.warn(data.message);
	}
	$('.leftPaneData').paginathing();
	
	$(".tabs-left li a label").text(function(index, currentText) {
	    return currentText.substr(0, 20);
	});
 }
function partnerPaymentDetailResp(data){
	
	
	$('.pagination').children().remove();
	if ($("#partnerData").val() == "partnerRegistration") {
		$("#partnerOrgPaymentForm  .approveCEDiv").hide();
		$("#partnerOrgPaymentForm  .approveEEDiv").hide();
	}
	editMode=false;
   	activeTabName="";
    requiredFileDeleted=false;
	var leftPanelHtml='';
	var active=true;
	var paymentDetailId=data.paymentDetailId;
	var currentOrgPaymentId=$('ul.leftPaneData').find('li.active').attr('id');
	var isFOApproved=data.isFOApproved==null?'':data.isFOApproved;
	var isFAApproved=data.isFAApproved==null?'':data.isFAApproved;
	var foComment=data.foComment==null?'':data.foComment;
	var faComment=data.faComment==null?'':data.faComment;
	
	if(currentOrgPaymentId==paymentDetailId)
	{
		$('#'+currentOrgPaymentId).remove();
	}
	else
	{
		$('#'+currentOrgPaymentId).removeClass('active');
	}
	$("#partnerOrgPaymentForm #partnerOrgPaymentId").val(paymentDetailId);
	if(data.paymentType!=null)
		{
		  data.paymentType.name=$("#partnerOrgPaymentForm #paymenttype option:selected").text();
		}
	leftPanelHtml=appendPartnerOrgPaymentDetailData(data,active);
	active=false;
	$(".leftPaneData").prepend(leftPanelHtml);
	paymentArray["payment"+paymentDetailId]=data;
	Alert.info(data.response.message,'','success');
	showDeleteBtn(isFOApproved,isFAApproved);
	setPaymentApprovalStatus(isFOApproved,foComment,isFAApproved,faComment);
	setChildLoadFlag(true);
	setChangedFlagById("factoryDetailsTab",true);
	/*setHeaderValues("Company Name: "+companyName, "Company Registration No. : "+registrationNo, "Pan Number : "+panNo, "Company Type : "+companyType);*/
	 setPartnerHeaderValues("Company Name: "+companyName, "Company Registration No. : "+registrationNo, "Company Type : "+companyType,"Status : "+partnerStatus);
	$('.leftPaneData').paginathing();
	
	$(".tabs-left li a label").text(function(index, currentText) {
	    return currentText.substr(0, 20);
	});
	
}
function setVendorPaymentType()
{  
	var isManufacturer=$("#isManufacturer").val(); 
    var isTrader=$("#isTrader").val();
	if(isManufacturer=='Y' && isTrader=="Y" )
    {
		$("#manufacturerPayment").removeAttr('readonly','readonly');
		$("#traderPayment").removeAttr('readonly','readonly');
		$("#traderPayment").prop('checked',true);
		$("#manufacturerPayment").prop('checked',false);
		
    }else if(isManufacturer=='Y' && isTrader=='N'){
    	$("#manufacturerPayment").removeAttr('readonly','readonly');
		$("#traderPayment").attr('readonly','readonly');
		$("#manufacturerPayment").prop('checked',true);
		$("#traderPayment").prop('checked',false);
    }else if(isManufacturer=='N' && isTrader=='Y'){
    	$("#manufacturerPayment").attr('readonly','readonly');
		$("#traderPayment").removeAttr('readonly','readonly');
		$("#manufacturerPayment").prop('checked',false);
		$("#traderPayment").prop('checked',true);
    }
}
function onPartnerPaymentDetailTabClick(){
	submitToURL("getPartner", 'onPartnerOrgPaymentTabLoad');	
}

function onOrgPaymentDetailTabLoad(data){
	var partnerData =  $("#partnerData").val();
	if(partnerData == 'partnerProfiles'){
		$("#partnerOrgPaymentForm #addPaymentBtnId").addClass("readonly");
		$("#payOnlineBtnId").attr('disabled','disabled');
	}else{
		$("#partnerOrgPaymentForm #addPaymentBtnId").removeClass("readonly");
		$("#payOnlineBtnId").removeAttr('disabled','disabled');
	}
	debugger;	
	if(data.objectMap.hasOwnProperty('paymentType'))	
		loadPaymentType(data.objectMap.paymentType);
	if(data.objectMap.hasOwnProperty('paymentDetails'))
		loadPartnerPaymentDetailLeftPane(data.objectMap.paymentDetails);
	if(data.objectMap.hasOwnProperty('partnerOrg'))
		loadFactory(data.objectMap.partnerOrg);
	if(data.objectMap.hasOwnProperty('partner'))
		{
		  if(!$.isEmptyObject(data.objectMap.partner))
			  {
			     var partner=data.objectMap.partner;
			     paymentPartnerArray['ptr']=partner;
			     if(!(partner.intraState))
			    	 {
			    	    $(".igstField").show();
			    	    $(".igstField").find("input:text").removeAttr("disabled","disabled");
			    	    $(".taxField").hide();
			    	    $(".taxField").find("input:text").attr("disabled","disabled");
			    	 }else{
			    		 $(".igstField").hide();
				    	 $(".igstField").find("input:text").attr("disabled","disabled");
				    	 $(".taxField").show();
				    	 $(".taxField").find("input:text").removeAttr("disabled","disabled");
			    	 }
			     if(partner.status=="RJ")
			    	 {
			    	    $("#partnerOrgPaymentForm #addPaymentBtnId").addClass("readonly");
			    	 }
			  }
		}
	    setActiveTabName("Payment Details",$('.leftPaneData li').length);
}

function loadPaymentType(data)
{
	$("#partnerOrgPaymentForm #paymenttype").html("");
	var options = "<option value=''>Select Payment Type</option>";
	$("#partnerOrgPaymentForm #paymenttype").append(options);
	$.each(data,function(idx,obj){
		
	     paymentTypeArray["paymentType"+obj.code]=obj;
	     $("#partnerOrgPaymentForm #paymenttype").append('<option data-code="'+obj.code+'" value="'+obj.paymentTypeId+'">'+obj.name+'</option>');
	});
	
}
function changeFormFieldValue(el)
{
	debugger;
	$("#renewalValidityDate").val("");
	var type=$(el).find('option:selected').data('code');
	var value=$(el).find('option:selected').val();
	$("#regPaymentType").val(value);    /*set to hidden field for online payment form*/ 
	var vendorTypevalue = $("input[name='vendorTypePayment']:checked").val();
	var code='';
	if(type=='RG')
		{
		 code='RG';
		 var data=paymentTypeArray["paymentType"+code];
		    $("#partnerOrgPaymentForm #gstRate").text(data.gst);
			$("#partnerOrgPaymentForm #amount").val(data.amount);
			var gstAmount=((data.amount*data.gst)/100);
			$("#partnerOrgPaymentForm #gst").val(gstAmount);
			$("#partnerOrgPaymentForm #totalAmount").val(Number($("#partnerOrgPaymentForm #amount").val())+Number($("#partnerOrgPaymentForm #gst").val()));
			$("#partnerOrgPaymentForm #igst").val(data.gst);
			$("#partnerOrgPaymentForm #igstAmount").val(gstAmount);
			var tax=data.gst/2;
			var taxAmount=gstAmount/2;
			$("#partnerOrgPaymentForm #cgst").val(tax);
			$("#partnerOrgPaymentForm #cgstAmount").val(taxAmount);
			$("#partnerOrgPaymentForm #sgst").val(tax);
			$("#partnerOrgPaymentForm #sgstAmount").val(taxAmount);
			$("#renewalValidityDateDiv").css('display','none');
			/*$("#factoryDivId").css('display','none');
			$("#factoryId").attr('disabled','disabled');*/
		}else if(type=='RN'){
			code='RN';
			/*$("#factoryDivId").css('display','block');
			$("#factoryId").removeAttr('disabled','disabled');*/
			var data=paymentTypeArray["paymentType"+code];
			$("#partnerOrgPaymentForm #gstRate").text(data.gst);
			$("#partnerOrgPaymentForm #amount").val(data.amount);
			var gstAmount=((data.amount*data.gst)/100);
			$("#partnerOrgPaymentForm #gst").val(gstAmount);
			$("#partnerOrgPaymentForm #totalAmount").val(Number($("#partnerOrgPaymentForm #amount").val())+Number($("#partnerOrgPaymentForm #gst").val()));
			$("#partnerOrgPaymentForm #igst").val(data.gst);
			$("#partnerOrgPaymentForm #igstAmount").val(gstAmount);
			var tax=data.gst/2;
			var taxAmount=gstAmount/2;
			$("#partnerOrgPaymentForm #cgst").val(tax);
			$("#partnerOrgPaymentForm #cgstAmount").val(taxAmount);
			$("#partnerOrgPaymentForm #sgst").val(tax);
			$("#partnerOrgPaymentForm #sgstAmount").val(taxAmount);
			$("#renewalValidityDateDiv").css('display','block');
		}
	var value = $("input[name='vendorTypePayment']:checked"). val();
	showFactory(vendorTypevalue,type);
	$("#partnerOrgPaymentForm #factoryId").val("");
	setTraderPaymentAmount(vendorTypevalue,type);
}
function setTraderPaymentAmount(vendorType,paymentType){
	debugger;
	var ptrData=paymentPartnerArray["ptr"];
	var data=paymentTypeArray["paymentType"+paymentType];
	if(vendorType=="TP" && paymentType=="RN" && ptrData!=undefined && ptrData.validTo!=null){
	            var year=getYear(new Date(),ptrData.validTo);
	            if(year>0){
				$("#partnerOrgPaymentForm #gstRate").text(data.gst);
				var amount=(data.amount)*year;
				$("#partnerOrgPaymentForm #amount").val(amount);
				var gstAmount=((amount*data.gst)/100);
				$("#partnerOrgPaymentForm #gst").val(gstAmount);
				$("#partnerOrgPaymentForm #totalAmount").val(Number($("#partnerOrgPaymentForm #amount").val())+Number($("#partnerOrgPaymentForm #gst").val()));
				$("#partnerOrgPaymentForm #igst").val(data.gst);
				$("#partnerOrgPaymentForm #igstAmount").val(gstAmount);
				var tax=data.gst/2;
				var taxAmount=gstAmount/2;
				$("#partnerOrgPaymentForm #cgst").val(tax);
				$("#partnerOrgPaymentForm #cgstAmount").val(taxAmount);
				$("#partnerOrgPaymentForm #sgst").val(tax);
				$("#partnerOrgPaymentForm #sgstAmount").val(taxAmount);
				/*var date=new Date(ptrData.validTo);
				date.setFullYear(date.getFullYear()+1);
				$("#renewalValidityDate").val(formatDate(date));*/
				
	          }
	}
}
function loadPartnerPaymentDetailLeftPane(data){
	debugger;
	$('.pagination').children().remove();
	$(".leftPaneData").html("");
	var leftPanelHtml="";
	var i=0;
	var active=false;
	var firstRow=null;
	if(data==null)
		{
		  return;
		}
	$.each(data,function(key,value){
		debugger;
		paymentArray["payment"+value.paymentDetailId]=value;
		if(i==0){
			firstRow = value;
			active=true;
		}
		leftPanelHtml=leftPanelHtml+appendPartnerOrgPaymentDetailData(value,active);
		active=false;
	    i++;
	});
	$(".leftPaneData").append(leftPanelHtml);
	$('.leftPaneData').paginathing();
	
	$(".tabs-left li a label").text(function(index, currentText) {
	    return currentText.substr(0, 20);
	});
	loadPartnerOrgPaymentRightPane(firstRow);
}
function appendPartnerOrgPaymentDetailData(value,active)
{
	$('.pagination').children().remove();
	var leftPanelHtml="";
	var partnerOrgPaymentId  = value.paymentDetailId==null?'':value.paymentDetailId;
	 if(active)
	 {
	    leftPanelHtml = leftPanelHtml + ' <li class="active" onclick="showOrgPaymentDetail('+partnerOrgPaymentId+')" id="'+partnerOrgPaymentId+'">';
	 }else{
		leftPanelHtml = leftPanelHtml + ' <li onclick="showOrgPaymentDetail('+partnerOrgPaymentId+')" id="'+partnerOrgPaymentId+'">';
	 }

	var paymenttype = value.paymentType.paymentTypeId==null?'':value.paymentType.paymentTypeId;
	var paymenttypeName = value.paymentType.name==null?'':value.paymentType.name;
	var paymentmode  = value.paymentMode==null?'':value.paymentMode;
	var demandDraftDate  = value.paymentDate==null?'':formatDate(value.paymentDate);
	var demandDraftNo =value.referenceNo==null?'':value.referenceNo;
	var micrCode  = value.micrCode==null?'':value.micrCode;
	var amount  = value.amount==null?'':value.amount;
	var gst  = value.gst==null?'':value.gst;
	var totalAmount  = value.total==null?'':value.total;
	var bankName  = value.bankName==null?'':value.bankName;
	var branchName  = value.branchName==null?'':value.branchName;
	var partnerOrgId=value.partnerOrg==null?'':value.partnerOrg.partnerOrgId==null?'':value.partnerOrg.partnerOrgId;
	var remark=value.remark==null?'':value.remark;
	var isApproved=value.isApproved==null?'':value.isApproved;	
	var vendorTypePayment=value.vendorTypePayment==null?'':value.vendorTypePayment;
	leftPanelHtml = leftPanelHtml +' <a href="#Master" data-toggle="tab">'
    +' <div class="col-md-12">'
    +'	<label class="col-xs-6" id="paymenttypeName-'+partnerOrgPaymentId+'">'+paymenttypeName+'</label>'
    +'	<label class="col-xs-6" id="labelDemandDraftDate-'+partnerOrgPaymentId+'">'+demandDraftDate+'</label>'
	+' </div>'
	+' <div class="col-md-12" style="display: none">'
	+'  <label class="col-xs-6" id="labelpaymentTypeName-'+partnerOrgPaymentId+'">'+value.paymentType.name+'</label>'
	+'	<label class="col-xs-6" id="lblAmount-'+partnerOrgPaymentId+'">'+amount+'</label>'
	+'	<label class="col-xs-6" id="lblPartnerOrgPaymentId-'+partnerOrgPaymentId+'">'+partnerOrgPaymentId+'</label>'
    +'	<label class="col-xs-6" id="lblPartnerOrgId-'+partnerOrgPaymentId+'">'+partnerOrgId+'</label>'
    +'	<label class="col-xs-6" id="lblDemandDraftNo-'+partnerOrgPaymentId+'">'+demandDraftNo+'</label>'
    +'	<label class="col-xs-6" id="lblMicrCode-'+partnerOrgPaymentId+'">'+micrCode+'</label>'
    +'	<label class="col-xs-6" id="lblgst-'+partnerOrgPaymentId+'" >'+gst+'</label>'
    +'	<label class="col-xs-6" id="lbltotalAmount-'+partnerOrgPaymentId+'" >'+totalAmount+'</label>'
    +'	<label class="col-xs-6" id="lblbankName-'+partnerOrgPaymentId+'" >'+bankName+'</label>'
    +'	<label class="col-xs-6" id="lblbranchName-'+partnerOrgPaymentId+'" >'+branchName+'</label>'
    +'	<label class="col-xs-6" id="lblPaymentTypeId-'+partnerOrgPaymentId+'">'+paymenttype+'</label>'
    +'	<label class="col-xs-6" id="lblPaymentMode-'+partnerOrgPaymentId+'" >'+paymentmode+'</label>'
    +'	<label class="col-xs-6" id="lblIsFOApproved-'+partnerOrgPaymentId+'" >'+value.isFOApproved+'</label>'
    +'	<label class="col-xs-6" id="lblIsFAApproved-'+partnerOrgPaymentId+'" >'+value.isFAApproved+'</label>'
    +'	<label class="col-xs-6" id="faComment-'+partnerOrgPaymentId+'" >'+value.faComment+'</label>'
    +'	<label class="col-xs-6" id="foComment-'+partnerOrgPaymentId+'" >'+value.foComment+'</label>'
    +'	<label class="col-xs-6" id="remark-'+partnerOrgPaymentId+'" >'+remark+'</label>'
    +'	<label class="col-xs-6" id="isApproved-'+partnerOrgPaymentId+'" >'+isApproved+'</label>'
	+' </div>'
    +' </a>'
    +' </li>';
	return leftPanelHtml;
	$('.leftPaneData').paginathing();
	
	$(".tabs-left li a label").text(function(index, currentText) {
	    return currentText.substr(0, 20);
	});
}
function showOrgPaymentDetail(id){
	var paymentData=paymentArray["payment"+id];
	loadPartnerOrgPaymentRightPane(paymentData);
}
function loadPartnerOrgPaymentRightPane(data){
	debugger;
	editMode=false;
	setChildLoadFlag(true);
	/*activeTabName="";*/
	if(!$.isEmptyObject(data)){
    var partnerValidTo=data.partner==null?"":data.partner.validTo==null?'':formatDate(data.partner.validTo);
    var factoryValidTo=data.partnerOrg==null?"":data.partnerOrg.validTo==null?'':formatDate(data.partnerOrg.validTo);
	var paymenttype = data.paymentType.paymentTypeId==null?'':data.paymentType.paymentTypeId;
	var paymentmode  = data.paymentMode==null?'':data.paymentMode;
	var demandDraftDate  = data.paymentDate==null?'':formatDate(data.paymentDate);
	var demandDraftNo =data.referenceNo==null?'':data.referenceNo;
	var micrCode  = data.micrCode==null?'':data.micrCode;
	var amount  = data.amount==null?'':data.amount;
	var gst  = data.gst==null?'':data.gst;
	var gstAmount  = data.gstAmount==null?'':data.gstAmount;
	var totalAmount  = data.total==null?'':data.total;
	var bankName  = data.bankName==null?'':data.bankName;
	var branchName  = data.branchName==null?'':data.branchName;
	var partnerOrgPaymentId  = data.paymentDetailId==null?'':data.paymentDetailId;
	var partnerOrgId=data.partnerOrg==null?'':data.partnerOrg.partnerOrgId==null?'':data.partnerOrg.partnerOrgId;
	var remark=data.remark==null?'':data.remark;
	var isApproved=data.isApproved==null?'':data.isApproved;
	var isFOApproved=data.isFOApproved==null?'':data.isFOApproved;
	var isFAApproved=data.isFAApproved==null?'':data.isFAApproved;
	var foComment=data.foComment==null?'':data.foComment;
	var faComment=data.faComment==null?'':data.faComment;
	var vendorTypePayment=data.vendorTypePayment==null?'':data.vendorTypePayment;
	var igst=data.igst==null?'':data.igst;
	var igstAmount=data.igstAmount==null?'':data.igstAmount;
	var cgst=data.cgst==null?'':data.cgst;
	var cgstAmount=data.cgstAmount==null?'':data.cgstAmount;
	var sgst=data.sgst==null?'':data.sgst;
	var sgstAmount=data.sgstAmount==null?'':data.sgstAmount;
	var paymentStatus=data.paymentGatewayStatus==null?'':data.paymentGatewayStatus;
	$("#partnerOrgPaymentForm #partnerOrgId").val(partnerOrgId);
	$("#partnerOrgPaymentForm #partnerOrgPaymentId").val(partnerOrgPaymentId);
	$("#partnerOrgPaymentForm #paymenttype").val(paymenttype);
	/*$("#partnerOrgPaymentForm #paymentmode").val(paymentmode);*/
	$("#partnerOrgPaymentForm #demandDraftDate").val(demandDraftDate);
	$("#partnerOrgPaymentForm #demandDraftNo").val(demandDraftNo);
	$("#partnerOrgPaymentForm #micrNo").val(micrCode);
	$("#partnerOrgPaymentForm #amount").val(amount);
	$("#partnerOrgPaymentForm #gst").val(gstAmount);
	$("#partnerOrgPaymentForm #gstRate").text(gst);
	$("#partnerOrgPaymentForm #totalAmount").val(totalAmount);
	$("#partnerOrgPaymentForm #bankName").val(bankName);
	$("#partnerOrgPaymentForm #branchName").val(branchName);
	$("#partnerOrgPaymentForm #igst").val(igst);
	$("#partnerOrgPaymentForm #igstAmount").val(igstAmount);
	$("#partnerOrgPaymentForm #cgst").val(cgst);
	$("#partnerOrgPaymentForm #cgstAmount").val(cgstAmount);
	$("#partnerOrgPaymentForm #sgst").val(sgst);
	$("#partnerOrgPaymentForm #sgstAmount").val(sgstAmount);
	$("#partnerOrgPaymentForm .dropDown").removeClass('errorinput');
	$("#partnerOrgPaymentForm #paymentStatus").val(paymentStatus);
	$("#partnerOrgPaymentForm #factoryId").val(partnerOrgId);
	var paymentTypeCode=$("#paymenttype").find('option:selected').data('code');
	showFactory(vendorTypePayment,paymentTypeCode);
	setTraderPaymentAmount(vendorTypePayment,paymentTypeCode);
	setPaymentType(vendorTypePayment);
	setPaymentApprovalStatus(isFOApproved,foComment,isFAApproved,faComment);
	showDeleteBtn(isFOApproved,isFAApproved)
	setPartnerHeaderValues("Company Name: "+companyName, "Company Registration No. : "+registrationNo, "Company Type : "+companyType,"Status : "+partnerStatus);
	checkForOnlinePayment(paymentmode);
	setRenewalValidityDate(vendorTypePayment,paymentTypeCode,partnerValidTo,factoryValidTo);
	if(isFAApproved=='Y'){
		$("#partnerOrgPaymentForm .disableBtn").attr('disabled','disabled');
		$("#partnerOrgPaymentForm #payReceipt").show();
		$("#partnerOrgPaymentForm #paymentReceiptBtnId").css('display','block');
	}
	else{
		$("#partnerOrgPaymentForm .disableBtn").removeAttr('disabled','disabled');
		$("#partnerOrgPaymentForm #payReceipt").hide();
		$("#partnerOrgPaymentForm #paymentReceiptBtnId").css('display','none');
	}
	showReceiptByRole();
	}else{
		$('#partnerOrgPaymentForm')[0].reset();
		$("#partnerOrgPaymentForm #partnerOrgPaymentId").val("");
		$("#partnerOrgPaymentForm #payReceipt").hide();
		$("#partnerOrgPaymentForm #paymentReceiptBtnId").css('display','none');
		setVendorPaymentType();
		setPartnerHeaderValues("Company Name: "+companyName, "Company Registration No. : "+registrationNo, "Company Type : "+companyType,"Status : "+partnerStatus);
	}
	
}
function setRenewalValidityDate(vendorTypePayment,paymentTypeCode,partnerValidTo,factoryValidTo){
debugger;	
	/*var date=null;
	if(vendorTypePayment=="MP" && paymentTypeCode=="RN"){
		date=factoryValidTo;
	}else if(vendorTypePayment=="TP" && paymentTypeCode=="RN"){
		date=partnerValidTo;
	}
	$("#renewalValidityDate").val(date);*/
}
function checkForOnlinePayment(paymentMode)
{
	if(paymentMode=="OP")
		{
		   $(".onlinePayment").addClass("readonly");
		   $(".onlinePaymentBtn").attr('disabled','disbaled');
		   $("#paymentmode_DD").prop('checked',false);
		   $("#paymentmode_OP").prop('checked',true);
		   $("#paymentStatusDivId").css('display','block');
		   $("#payOnlinePtnrRegBtn").css('display','block');
		   $("#savePaymentBtnId").hide();
		}else{
			$("#paymentStatusDivId").css('display','none');
		    $(".onlinePayment").removeClass("readonly");
			$("#paymentmode_DD").prop('checked',true);
			$("#paymentmode_OP").prop('checked',false);
			$("#payOnlinePtnrRegBtn").css('display','none');
			$("#savePaymentBtnId").show();
		}
}
function setPaymentType(vendorTypePayment)
{
	if(vendorTypePayment=='TP')
		{
			$("#traderPayment").prop('checked',true);
			$("#manufacturerPayment").prop('checked',false);
		    
		}else if(vendorTypePayment=='MP'){
			$("#traderPayment").prop('checked',false);
			$("#manufacturerPayment").prop('checked',true);
		}
	$("#traderPayment").attr('readonly','readonly');
	$("#manufacturerPayment").attr('readonly','readonly');

}
function setPaymentApprovalStatus(isFOApproved,foComment,isFAApproved,faComment)
{
	 debugger;
	 $("#partnerOrgPaymentForm .approveEEDiv").show();
	 $("#partnerOrgPaymentForm .approveCEDiv").show();
	 $("#partnerOrgPaymentForm .remark").attr('disabled','disabled');
     $("#partnerOrgPaymentForm .statusBtn").attr('disabled','disabled');
     
     $("#partnerOrgPaymentForm  #eeRemark").val(foComment);
     $("#partnerOrgPaymentForm  #ceRemark").val(faComment);
    if(isFAApproved=="Y" || isFOApproved=="Y" )
    	{
    	  if($("#partnerData").val()=="partnerProfiles"){
    	   $("#partnerOrgPaymentForm .approveEEDiv").show();
    	   $("#partnerOrgPaymentForm .approveCEDiv").show();
    	  }else{
    		  $("#partnerOrgPaymentForm .approveEEDiv").hide();
       	      $("#partnerOrgPaymentForm .approveCEDiv").hide();
    	  }
    	 if(isFOApproved=="Y")
    		 {
    		    $("#partnerOrgPaymentForm #foApproveBtn").prop('checked', true);
       	        $("#partnerOrgPaymentForm #foClarification").prop('checked', false);
    		 }
    	 if(isFAApproved=="Y")
		 {
		    $("#partnerOrgPaymentForm #faApproveBtn").prop('checked', true);
   	        $("#partnerOrgPaymentForm #faClarification").prop('checked', false);
		 }
    	}
    if(isFAApproved=="N" || isFOApproved=="N" )
    		{
		    	if (isFOApproved == "N") {
					$("#partnerOrgPaymentForm #foApproveBtn").prop('checked', false);
					$("#partnerOrgPaymentForm #foClarification").prop('checked', true);
					$("#partnerOrgPaymentForm .approveCEDiv").hide();
				}
				if (isFAApproved == "N") {
					$("#partnerOrgPaymentForm .approveCEDiv").show();
					$("#partnerOrgPaymentForm #faApproveBtn").prop('checked', false);
					$("#partnerOrgPaymentForm #faClarification").prop('checked', true);
				}
    		}else{
    			$("#partnerOrgPaymentForm .approveEEDiv").hide();
    			$("#partnerOrgPaymentForm .approveCEDiv").hide();
    		}
}
function showDeleteBtn(isFOApproved,isFAApproved)
{
	debugger;
   if(isFOApproved=="Y" || isFOApproved=="N" || isFAApproved=="Y" || isFAApproved=="N" )
	   {
	     $("#deletePaymentBtnId").attr('disabled','disabled');
	     $("#partnerOrgPaymentForm .disableBtn").attr('disabled','disabled');
	   }else{
		   $("#deletePaymentBtnId").removeAttr('disabled','disabled');
		   $("#partnerOrgPaymentForm .disableBtn").removeAttr('disabled','disabled');
	   }
}
function loadFactory(data)
{
	$("#partnerOrgPaymentForm #factoryId").html("");
	var options = "<option value=''>Select Factory</option>";
	$("#partnerOrgPaymentForm #factoryId").append(options);
	$.each(data,function(idx,obj){
		paymentOrgArray["org"+obj.partnerOrgId]=obj;
		$("#partnerOrgPaymentForm #factoryId").append('<option value="'+obj.partnerOrgId+'">'+obj.name+'</option>');
	});
}
function changePaymentField(el){
	$("#renewalValidityDate").val("");
	var type=$(el).val();
	$("#onlineVendorType").val(type);
	var paymenttype=$("#paymenttype").find('option:selected').data('code');
	showFactory(type,paymenttype);
	$("#partnerOrgPaymentForm #factoryId").val("");
	setTraderPaymentAmount(type,paymenttype);
}
function showFactory(vendorType,paymentType)
{
	if(vendorType=="MP" && paymentType=="RN")
	{
		$("#factoryDivId").css('display','block');
		$("#factoryId").removeAttr('disabled','disabled');
	}else{
		$("#factoryDivId").css('display','none');
		$("#factoryId").attr('disabled','disabled');
	}
}
function setPaymentAmount()
{
	debugger;
	$("#renewalValidityDate").val("");
	var factoryId=$("#factoryId").val();
	$("#onlinePaymentFactoryId").val(factoryId);
	var paymentType=$("#paymenttype").find('option:selected').data('code');
	var vendorType=$("input[name='vendorTypePayment']:checked"). val();
	var org=paymentOrgArray["org"+factoryId];
	
	/*if(org==undefined || org.validTo==null)
		{
		  $("#partnerOrgPaymentForm #gstRate").text("");
		  $("#partnerOrgPaymentForm #amount").val("");
		  $("#partnerOrgPaymentForm #gst").val("");
		  $("#partnerOrgPaymentForm #totalAmount").val("");
		  $("#partnerOrgPaymentForm #igst").val("");
		  $("#partnerOrgPaymentForm #igstAmount").val("");
		  $("#partnerOrgPaymentForm #cgst").val("");
		  $("#partnerOrgPaymentForm #cgstAmount").val("");
		  $("#partnerOrgPaymentForm #sgst").val("");
		  $("#partnerOrgPaymentForm #sgstAmount").val("");
		  Alert.warn("Factoty Validity Date is Empty");
		  return;
		}*/
	
    if(paymentType=="RN" && vendorType=="MP" && org!=undefined && org.validTo!=null) /*org.isRenewed=="N" &&*/
    	{
    	    var year=getYear(new Date(),org.validTo);
    	    if(year>0){
	    	var data=paymentTypeArray["paymentType"+paymentType];
			$("#partnerOrgPaymentForm #gstRate").text(data.gst);
			var amount=(data.amount)*year;
			$("#partnerOrgPaymentForm #amount").val(amount);
			var gstAmount=((amount*data.gst)/100);
			$("#partnerOrgPaymentForm #gst").val(gstAmount);
			$("#partnerOrgPaymentForm #totalAmount").val(Number($("#partnerOrgPaymentForm #amount").val())+Number($("#partnerOrgPaymentForm #gst").val()));
			$("#partnerOrgPaymentForm #igst").val(data.gst);
			$("#partnerOrgPaymentForm #igstAmount").val(gstAmount);
			var tax=data.gst/2;
			var taxAmount=gstAmount/2;
			$("#partnerOrgPaymentForm #cgst").val(tax);
			$("#partnerOrgPaymentForm #cgstAmount").val(taxAmount);
			$("#partnerOrgPaymentForm #sgst").val(tax);
			$("#partnerOrgPaymentForm #sgstAmount").val(taxAmount);
			/*var orgDate=new Date(org.validTo);
			orgDate.setFullYear(orgDate.getFullYear()+1);
			$("#renewalValidityDate").val(formatDate(orgDate));*/
    	   }
    	}
}
function getYear(currentDate,validto){
    debugger;
   
    var validTo=new Date(validto);
    var ynew = currentDate.getFullYear();
    var mnew = currentDate.getMonth();
    var dnew = currentDate.getDate();
    var yold = validTo.getFullYear();
    var mold = validTo.getMonth();
    var dold = validTo.getDate();
    var diff = ynew - yold;
    if (mnew > mold || (mnew == mold && dnew > dold)) {
        diff++;
    }
    /*var diff = ynew - yold;
    if (mold > mnew) diff--;
    else {
        if (mold == mnew) {
            if (dold > dnew) diff--;
        }
    }*/
    return diff;
}