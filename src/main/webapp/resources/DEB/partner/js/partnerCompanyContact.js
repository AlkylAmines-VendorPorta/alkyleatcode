var companyContactArray=new Array();
$(document).ready(function(){
	$('#cancelCompContactBtnId').click(function(event) {
		debugger;
		event.preventDefault();
		editMode=false;
	   	/*activeTabName="";*/
	   	/*var activeContactId=$('.leftPaneData').find('li.active').attr('id');*/
		var activeContactId=$("#compContactForm #userDetailsId").val();
		if(activeContactId!="" || activeContactId!=undefined)
			{
			  var data=companyContactArray["companyContact"+activeContactId];
			  loadCompContactRightPane(data);
			}
		else
			$('#compContactForm')[0].reset();
		
    });
	
	 $("#compContactForm").find("input,select,textarea").change(function() {
   	 editMode=true;
   	 activeTabName="Company Contact";
   });
});
function getPartnerCompanyContact(event,el)
{
	event.preventDefault();	
	var ele=$("#compContactChildTabId")[0];
	getCompanyContact(event,ele);
	/*debugger;
	event.preventDefault();	
	if(!editMode && !requiredFileDeleted){
		onToggleTab(el);
		submitWithParam('getCompanyContact','bPartnerId','onCompContactTabLoad');
	}else{
		event.stopPropagation();
		Alert.warn("Please Save Changed Data Of "+activeTabName+" Tab");
	}*/
	
}
function getCompanyContact(event,el)
{
	
	    event.preventDefault();	
		if(!editMode && !requiredFileDeleted){
			$('#pagination-here').empty();
		    cacheLi();
			setCurrentTab(el);
			if(getChangedFlag()){
			submitWithParam('getCompanyContact','bPartnerId','onCompContactTabLoad');
			setChangedFlag(false);
			}else{
				getCacheLi();
			}
			setActiveTabName("Company Contact",$('.leftPaneData li').length);
			/*setPartnerHeaderValues("Company Name: "+companyName, "Company Registration No. : "+registrationNo, "Company Type : "+companyType,"Status : "+partnerStatus);*/
			setPartnerHeaderValues("Company Name: "+companyName, "Company Registration No. : "+registrationNo, "Vendor SAP Code : "+vendorSAPCode,"Status : "+partnerStatus);
		}else{
			event.stopPropagation();
			Alert.warn("Please Save Changed Data Of "+activeTabName+" Tab");
		}
		$("#filterBtnId").addClass("readonly");
	    checkForFilterByRole();
}
function companyContactResp(data){
	debugger;
	
	setChildLoadFlag(true);
	$('.pagination').children().remove();
	if(!isEmpty(data)){
		var hasError=data.response==null?'':data.response.hasError==null?'':data.response.hasError;
	if(hasError==false)
	{
	if($("#partnerData").val()=="partnerRegistration")
	{
		$("#compContactForm  .approveCEDiv").hide();
		$("#compContactForm  .approveEEDiv").hide();
	} 
	editMode=false;
	activeTabName="";
	requiredFileDeleted=false;
	var addressFlag=true;
	var leftPanelHtml='';
	var partnerCompanyContactId=data.userDetailsId==null?'':data.userDetailsId;
	$("#compContactForm #userDetailsId").val(partnerCompanyContactId);
	companyContactArray["companyContact"+partnerCompanyContactId]=data;
	addressFlag=false;
	setActiveTabName("Company Contact",$('.leftPaneData li').length);
	Alert.info(data.response.message,'','success');	
	/*setHeaderValues("Company Name: "+companyName, "Company Registration No. : "+registrationNo, "Pan Number : "+panNo, "Company Type : "+companyType);*/
	 setPartnerHeaderValues("Company Name: "+companyName, "Company Registration No. : "+registrationNo, "Company Type : "+companyType,"Status : "+partnerStatus);
	}
	else
	 {
	   Alert.warn(data.response.message,'','error');
	 }
	}
	
	$(".tabs-left li a label").text(function(index, currentText) {
	    return currentText.substr(0, 20);
	});
}


function onCompContactTabClick(){
	submitToURL("getPartner", 'onCompContactTabLoad');	
}

function onCompContactTabLoad(data){
	debugger;
	if(data.objectMap.hasOwnProperty('locationTypes')){
			loadLocationTypes(data.objectMap.locationTypes);
	    }
		if(data.objectMap.hasOwnProperty('title')){
			loadReferenceListById('title',data.objectMap.title);
		}
		if(data.objectMap.hasOwnProperty('companyContacts')){
			if(!isEmpty(data) && !isEmpty(data.objectMap) && !isEmpty(data.objectMap.companyContacts))
				{
					loadCompContactLeftPane(data.objectMap.companyContacts);
				}
			else
				{
					$(".leftPaneData").html("");
					$('#compContactForm')[0].reset();
					$("#compContactForm #userDetailsId").val("");
				}
			
				
	}
		setActiveTabName("Company Contact",$('.leftPaneData li').length);
}
function loadCompanyContact(data){
	debugger;
    $('.pagination').children().remove();
	$(".leftPaneData").html("");
	loadCompContactRightPane(data);
	 $(".tabs-left li a label").text(function(index, currentText) {
		    return currentText.substr(0, 20);
		});
}
function loadCompContactLeftPane(data){
	debugger;
	$('.pagination').children().remove();
	$(".leftPaneData").html("");
	
	var i=0;
	var firstRow=null;
	if(!isEmpty(data)){
	$.each(data,function(key,value){
		var compContactId = value.userDetailsId==null?'':value.userDetailsId;
	
		companyContactArray["companyContact"+compContactId]=value;
		if(i==0){
			firstRow = value;
		}

	});
	}
	$('.leftPaneData').paginathing();
	
	 $(".tabs-left li a label").text(function(index, currentText) {
		    return currentText.substr(0, 20);
		});
	loadCompContactRightPane(firstRow);
}

function loadCompContactRightPane(data){
	debugger;
	editMode=false;
	/*activeTabName="";*/
	var firstName = data==null?'':data.firstName==null?'':data.firstName;
	var lastName  = data==null?'':data.lastName==null?'':data.lastName;
	var email 	  = data==null?'':data.email==null?'':data.email;
	var mobileNo  = data==null?'':data.mobileNo==null?'':data.mobileNo;
	var userDetailsId = data==null?'':data.userDetailsId==null?'':data.userDetailsId;
	var title  = data==null?'':data.title==null?'':data.title;
	var middleName  = data==null?'':data.middleName==null?'':data.middleName;
	var telephone1  = data==null?'':data.telephone1==null?'':data.telephone1;
	var telephone2  = data==null?'':data.telephone2==null?'':data.telephone2;
	var fax1  = data==null?'':data.fax1==null?'':data.fax1;
	var fax2  = data==null?'':data.fax2==null?'':data.fax2;
	var mobileNo = data==null?'':data.mobileNo==null?'':data.mobileNo;
	var remark=data==null?'':data.remark==null?'':data.remark;
	var isApproved=data==null?'':data.isApproved==null?'':data.isApproved;
	var eeComment=data==null?'':data.eeComment==null?'':data.eeComment;
	var isEEApproved=data==null?'':data.isEEApproved==null?'':data.isEEApproved;
	var ceComment=data==null?'':data.ceComment==null?'':data.ceComment;
	var isCEApproved=data==null?'':data.isCEApproved==null?'':data.isCEApproved;
	
	$("#labelFirstName").html('<h4>'+firstName+'</h4>');
	$("#labelLastName").html(lastName);
	$("#labelEmail").html(email);
	$("#labelMobileNo").html(mobileNo);
	
	$("#compContactForm #userDetailsId").val(userDetailsId);
	$("#compContactForm #title").val(title);
	$("#compContactForm #firstName").val(firstName);
	$("#compContactForm #lastName").val(lastName);
	$("#compContactForm #email").val(email);
	$("#compContactForm #middleName").val(middleName);
	$("#compContactForm #telephone1").val(telephone1);
	$("#compContactForm #telephone2").val(telephone2);
	$("#compContactForm #fax1").val(fax1);
	$("#compContactForm #fax2").val(fax2);
	$("#compContactForm #mobileNo").val(mobileNo);

	changeCommentAndStatusByRole('compContactForm',isEEApproved,eeComment,isCEApproved,ceComment);
	setApprovedStatus('compContactForm',isApproved);
	/*setPartnerHeaderValues("Company Name: "+companyName, "Company Registration No. : "+registrationNo, "Company Type : "+companyType,"Status : "+partnerStatus);*/
	setPartnerHeaderValues("Company Name: "+companyName, "Company Registration No. : "+registrationNo, "Vendor SAP Code : "+vendorSAPCode,"Status : "+partnerStatus);
	setChildLoadFlag(true);
	
}

function loadLocationTypes(data){
		$("#locationTypeRef").html("");
		var options = "<option>Select Location Type </option>";
if(!isEmpty(data)){
		$.each(data,function(key,value){
			options = options+'<option>'
		});
}
}