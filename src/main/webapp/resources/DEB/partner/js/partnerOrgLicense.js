function partnerOrgLicenseResp(data){
	debugger;
	
	var userFlag=true;
	var leftPanelHtml='';
	var currentOrgLicenseId=$('ul.leftPaneData').find('li.active').attr('id');
	var partnerOrgLicenceId=data==null?'':data.partnerOrgLicenceId==null?'':data.partnerOrgLicenceId;
	if(currentOrgLicenseId==partnerOrgLicenceId)
	{
		$('#'+currentOrgLicenseId).remove();
		
	}
	var partnerOrgID=data==null?'':data.partnerOrg==null?'':data.partnerOrg.partnerOrgId==null?'':data.partnerOrg.partnerOrgId;
	$("#partnerOrgLicenseForm .partnerOrgId").val(partnerOrgID);
	$("#partnerOrgLicenseForm #partnerOrgLicenceId").val(partnerOrgLicenceId);
	leftPanelHtml=appendLicenseData(data,userFlag);
	$(".leftPaneData").prepend(leftPanelHtml);
	userFlag=false;
	var message=data==null?'':data.response==null?'':data.response.message==null?'':data.response.message;
	swal(message);	
}


function onPartnerOrgLicenseTabClick(){
	submitToURL("getPartner", 'onPartnerOrgLicenseTabLoad');	
}


function onPartnerOrgLicenseTabLoad(data){
	debugger;
	
    $("#partnerOrgLicenseForm #partnerOrgLicenceId").val("");
    if(data.objectMap.hasOwnProperty('partnerOrgLincenses')){
		if(!isEmpty(data.objectMap.partnerOrgLincenses))
			{
			loadPartnerOrgLicenseLeftPane(data.objectMap.partnerOrgLincenses);
			}
		else {
			$('#partnerOrgLicenseForm')[0].reset();
		$(".leftPaneData").html("");
		}
	}

}

/*function formatDate(longDate){
	   var dt=new Date(longDate);
	   var dd=dt.getDate();
	   var MM=dt.getMonth()+1;
	   var yyyy=dt.getFullYear();
	   var HH= dt.getHours();
	   var mm= dt.getMinutes();
	   var ss= dt.getSeconds();
	   
	   return yyyy+'-'+MM+'-'+dd;
	   +' '+HH+':'+mm+':'+ss;
}*/

function loadPartnerOrgLicenseLeftPane(data){
debugger;
$('.pagination').children().remove();
	$(".leftPaneData").html("");
	var leftPanelHtml='';
	var i=0;
	var active=false;
	var firstRow=null;
if(!isEmpty(data)){
	$.each(data,function(key,value){
		debugger;
		
		if(i==0){
			firstRow = value;
			active=true;
		}
		leftPanelHtml= leftPanelHtml +appendLicenseData(value,active);
		active=false;
		i++;
	});
}
	$(".leftPaneData").append(leftPanelHtml);
	$('.leftPaneData').paginathing();
	
	loadPartnerOrgLicenseRightPane(firstRow);
	 $(".tabs-left li a label").text(function(index, currentText) {
		    return currentText.substr(0, 20);
		});
}

function loadPartnerOrgLicenseRightPane(data){
	debugger;
	if(!isEmpty(data)){
	var licenceNo = data.licenceNo==null?'':data.licenceNo;
	var licenceValidityDate  = data.licenceValidityDate==null?'':formatDate(data.licenceValidityDate);
    var partnerOrgLicenceId= data.partnerOrgLicenceId==null?'':data.partnerOrgLicenceId;
    var partnerOrgId = data.partnerOrg.partnerOrgId==null?'':data.partnerOrg.partnerOrgId;
	var licenseType = data.licenseType==null?'':data.licenseType;
    /*$("#orgLicense").html('<h4>'+licenceNo+'</h4>');
	$("#orgLicenseValDate").html(licenceValidityDate);*/

	$("#partnerOrgLicenseForm #partnerOrgLicenceId").val(partnerOrgLicenceId);
	$("#partnerOrgLicenseForm #licenceValidityDate").val(licenceValidityDate);
	$("#partnerOrgLicenseForm #licenceNo").val(licenceNo);
	$("#partnerOrgLicenseForm .partnerOrgId").val(partnerOrgId);
	$("#partnerOrgLicenseForm #licenseType").val(licenseType);
	
	}
/*	$("#compContactForm locationTypeRef").val(data.locationTypeRef==null?'':data.locationTypeRef);*/
	
}

function appendLicenseData(value,active){
	debugger;
	 var leftPanelHtml='';
	 if(!isEmpty(value)){
	 var partnerOrgLicenceId=value.partnerOrgLicenceId==null?'':value.partnerOrgLicenceId;
	 if(active)
		 {
		    leftPanelHtml = leftPanelHtml + ' <li class="active" onclick="showOrgLicenseDetail('+partnerOrgLicenceId+')" id="'+partnerOrgLicenceId+'">';
		 }else{
			leftPanelHtml = leftPanelHtml + ' <li onclick="showOrgLicenseDetail('+partnerOrgLicenceId+')" id="'+partnerOrgLicenceId+'">';
		 }
	
	    var licenceNo = value.licenceNo==null?'':value.licenceNo;
		var licenceValidityDate  = value.licenceValidityDate==null?'':formatDate(value.licenceValidityDate);
	    var partnerOrgLicenceId= value.partnerOrgLicenceId==null?'':value.partnerOrgLicenceId;
	    var partnerOrgId = value.partnerOrg.partnerOrgId==null?'':value.partnerOrg.partnerOrgId;
		var licenseType = value.licenseType==null?'':value.licenseType;
		
    leftPanelHtml = leftPanelHtml +' <a href="#Master" data-toggle="tab">'
    +' <div class="col-md-12">'
    +'  <label class="col-xs-6" id="labelOrgLicense-'+partnerOrgLicenceId+'"> '+licenceNo+'</label>'
    +'	<label class="col-xs-6 " id="labelOrgLicenseValDate-'+partnerOrgLicenceId+'">'+licenceValidityDate+'</label>'
    +' </div>'	
    +' <div class="col-md-12" style="display: none">'
	   +'	<label class="col-xs-6" id="partnerLicenseID-'+partnerOrgLicenceId+'">'+partnerOrgLicenceId+'</label>'
	   +'	<label class="col-xs-6" id="partnerLicenseNo-'+partnerOrgLicenceId+'">'+licenceNo+'</label>'
	   +'	<label class="col-xs-6" id="partnerLicenseValidity-'+partnerOrgLicenceId+'">'+licenceValidityDate+'</label>'
	   +'	<label class="col-xs-6" id="partnerOrgId-'+partnerOrgLicenceId+'">'+partnerOrgId+'</label>'
	  +'	<label class="col-xs-6" id="partnerlicenseType-'+partnerOrgLicenceId+'">'+licenseType+'</label>'
	   +' </div>'
    +' </a>'
    +' </li>';
	
	 }
	return leftPanelHtml;
}