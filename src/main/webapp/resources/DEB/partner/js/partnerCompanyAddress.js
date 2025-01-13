var compAddressArray = new Array();
$(document).ready(function(){ 
	var status= false;
	$('#addAddressBtnId').click(function(event) {
		event.preventDefault();
		$('#compAddressForm')[0].reset();
		$("#compAddressForm #locationId").val("");
		$("#compAddressForm #partnerCompanyAddressId").val("");
		$("#compAddressForm .approveDiv").hide();
		
    });

	$('#editAddressBtnId').click(function(event) {
		event.preventDefault();
		
    });
	
	$('#cancelAddressBtnId').click(function(event) {
		event.preventDefault();
		editMode=false;
	   	/*activeTabName="";*/
		var activeAddressId=$('.leftPaneData').find('li.active').attr('id');
		if(activeAddressId!=undefined)
			{
			  showCompanyAddressDetail(activeAddressId);
			}
		else
			$('#compAddressForm')[0].reset();
    });
	  $("#compAddressForm").find("input,select,textarea").change(function() {
	   	 editMode=true;
	   	 activeTabName="Company Address";
	   });
});
function getPartnerCompAddress(event,el){
	    event.preventDefault();	
		if(!editMode && !requiredFileDeleted){
			cacheLi();
			setCurrentTab(el);
			if(getChangedFlag()){
			submitWithParam('getCompanyAddress','bPartnerId','onCompAddressTabLoad');
			setChangedFlag(false);
			}else{
				getCacheLi();
			}
			setActiveTabName("Company Address",$('.leftPaneData li').length);		
			/*setPartnerHeaderValues("Company Name: "+companyName, "Company Registration No. : "+registrationNo, "Company Type : "+companyType,"Status : "+partnerStatus);*/
			setPartnerHeaderValues("Company Name: "+companyName, "Company Registration No. : "+registrationNo, "Vendor SAP Code : "+vendorSAPCode,"Status : "+partnerStatus);
		}else{
			event.stopPropagation();
	        Alert.warn("Please Save Changed Data Of "+activeTabName+" Tab");
		}
		$("#filterBtnId").addClass("readonly");
	    checkForFilterByRole();
}
function partnerCompanyAddressDelResp(data)
{
	
	$('.pagination').children().remove();
	if(!isEmpty(data)){
		if(data.hasError==false){
			Alert.info(data.message);
			var currentPartnerAddressId=$('ul.leftPaneData').find('li.active').attr('id');
			$('#'+currentPartnerAddressId).remove();
			$('#compAddressForm')[0].reset();
			$("#compAddressForm #partnerCompanyAddressId").val("");
			$("#compAddressForm #locationId").val("");
			event.preventDefault();
		}else{
			Alert.warn(data.message);
		}
		$('.leftPaneData').paginathing();
		plugin,init();
	}
}

function companyAddressResp(data){
	
	setChildLoadFlag(true);
	$('.pagination').children().remove();
	if(!isEmpty(data) && !isEmpty(data.response)){
		if(data.response.hasError==false){
			if($("#partnerData").val()=="partnerRegistration"){
				$("#compAddressForm  .approveCEDiv").hide();
				$("#compAddressForm  .approveEEDiv").hide();
			}
			editMode=false;
			activeTabName="";
			var addressFlag=true;
			var leftPanelHtml='';
			var currentPartnerAddressId=$('ul.leftPaneData').find('li.active').attr('id');
			var partnerCompanyAddressId=data.partnerCompanyAddressId;
			if(currentPartnerAddressId==partnerCompanyAddressId)
			{
				$('#'+currentPartnerAddressId).remove();
			}
			else
			{
				$('#'+currentPartnerAddressId).removeClass('active');
			}
			$("#compAddressForm #partnerCompanyAddressId").val(partnerCompanyAddressId);
			var loactionId=data.location==null?'':data.location.locationId;
			$("#compAddressForm #locationId").val(loactionId);
			if(data.location!=null && data.location.region!=null)
			{
			  data.location.region.name=$("#compAddressForm #region option:selected").text();
			}
			leftPanelHtml=appendAddressData(data,addressFlag);
			
			compAddressArray["address"+partnerCompanyAddressId]=data;
			$(".leftPaneData").prepend(leftPanelHtml);
			addressFlag=false;
			setActiveTabName("Company Address",$('.leftPaneData li').length);
			Alert.info(data.response.message,'','success');	
			/*setHeaderValues("Company Name: "+companyName, "Company Registration No. : "+registrationNo, "Pan Number : "+panNo, "Company Type : "+companyType);*/
			 setPartnerHeaderValues("Company Name: "+companyName, "Company Registration No. : "+registrationNo, "Company Type : "+companyType,"Status : "+partnerStatus);
		}
		else
			 {
			   Alert.warn(data.response.message);
			 }
	}
	$('.leftPaneData').paginathing();
	
	$(".tabs-left li a label").text(function(index, currentText) {
	    return currentText.substr(0, 20);
	});
}

function setLeftPaneDropDownOnResp(id){
	
	var country=$("#compAddressForm #country").val();
	var region=$("#compAddressForm #region").val();
	var district=$("#compAddressForm #district").val();
	$("#compCountryId-"+id).html(country);
	$("#compRegionId-"+id).html(region);
	$("#compDistrictId-"+id).html(district);
}

function onCompAddressTabClick(){
	submitToURL("getPartner", 'onCompAddressTabLoad');	
}

function onCompAddressTabLoad(data){
if(!isEmpty(data) && !isEmpty(data.objectMap)){
	if(data.objectMap.hasOwnProperty('countries')){
		loadAddCountry(data.objectMap.countries);
	}

	/*if(data.objectMap.hasOwnProperty('regions')){
		 loadAddRegion(data.objectMap.regions);}

	if(data.objectMap.hasOwnProperty('districts')){
		loadAddDistrict(data.objectMap.districts);}
	*/
	if(data.objectMap.hasOwnProperty('orgs')){
			 loadCompAddressLeftPane(data.objectMap.orgs);
	}
}
setActiveTabName("Company Address",$('.leftPaneData li').length);
}

function appendAddressData(value,active){
	
	 var leftPanelHtml='';
	 if(!isEmpty(value)){
		 var partnerCompanyAddressId = value.partnerCompanyAddressId==null?'':value.partnerCompanyAddressId;
		 if(active)
			 {
			    leftPanelHtml = leftPanelHtml + ' <li class="active" onclick="showCompanyAddressDetail('+partnerCompanyAddressId+')" id="'+partnerCompanyAddressId+'">';
			 }else{
				leftPanelHtml = leftPanelHtml + ' <li onclick="showCompanyAddressDetail('+partnerCompanyAddressId+')" id="'+partnerCompanyAddressId+'">';
			 }
		
		var locationId= value.location==null?'':value.location.locationId;
		var registeredAddress = value.location==null?'':value.location.address1;
		var postal  = value.location==null?'':value.location.postal;
		var country  = value.location==null?'':value.location.country==null?'':value.location.country.name;
		var countryId  = value.location==null?'':value.location.country==null?'':value.location.country.countryId;
		var region  = value.location==null?'': value.location.region==null?'':value.location.region.regionId;
		var regionName  = value.location==null?'': value.location.region==null?'':value.location.region.name;
		var city  = value.location.city==null?'':value.location.city;
		var district  = value.location==null?'': value.location.district==null?'':value.location.district.districtId;
		var isShipToLocation = value.isShipToAddress==null?'':value.isShipToAddress;
		var isBillToLocation = value.isBillToAddress==null?'':value.isBillToAddress;
		var remark=value.remark==null?'':value.remark;
		var isApproved=value.isApproved==null?'':value.isApproved;
		var eeComment=value.eeComment==null?'':value.eeComment;
		var isEEApproved=value.isEEApproved==null?'':value.isEEApproved;
		var ceComment=value.ceComment==null?'':value.ceComment;
		var isCEApproved=value.isCEApproved==null?'':value.isCEApproved;
		
		leftPanelHtml = leftPanelHtml +' <a href="#Master" data-toggle="tab">'
		   +' <div class="col-md-12">'
		   +'  <label class="col-xs-6" id="compAdd-'+partnerCompanyAddressId+'">'+regionName+'</label>'
		   +'	<label class="col-xs-6 " id="compCityId-'+partnerCompanyAddressId+'">'+city+'</label>'
		   +' </div>'	
		   +' <div class="col-md-12" style="display: none">'
		   +'	<label class="col-xs-6" id="compRegAdd-'+partnerCompanyAddressId+'">'+registeredAddress+'</label>'
		   +'	<label class="col-xs-6" id="compLoc-'+partnerCompanyAddressId+'">'+locationId+'</label>'
		   +'	<label class="col-xs-6" id="compPartnerAddressId-'+partnerCompanyAddressId+'">'+partnerCompanyAddressId+'</label>'
		   +'	<label class="col-xs-6" id="compCityId-'+partnerCompanyAddressId+'">'+city+'</label>'
		   +'	<label class="col-xs-6" id="compDistrictId-'+partnerCompanyAddressId+'">'+district+'</label>'
		   +'	<label class="col-xs-6" id="compCountryId-'+partnerCompanyAddressId+'">'+countryId+'</label>'
		   +'	<label class="col-xs-6" id="compRegionId-'+partnerCompanyAddressId+'">'+region+'</label>'
		   +'	<label class="col-xs-6" id="compPincode-'+partnerCompanyAddressId+'">'+postal+'</label>'
		   +'	<label class="col-xs-6" id="compisShipToLocation-'+partnerCompanyAddressId+'">'+isShipToLocation+'</label>'
		   +'	<label class="col-xs-6" id="compisBillToLocation-'+partnerCompanyAddressId+'">'+isBillToLocation+'</label>'
		   +'	<label class="col-xs-6" id="remark-'+partnerCompanyAddressId+'">'+remark+'</label>'
		   +'	<label class="col-xs-6" id="isApproved-'+partnerCompanyAddressId+'">'+isApproved+'</label>'
		   +'	<label class="col-xs-6" id="eeComment-'+partnerCompanyAddressId+'">'+eeComment+'</label>'
		   +'	<label class="col-xs-6" id="isEEApproved-'+partnerCompanyAddressId+'">'+isEEApproved+'</label>'
		   +'	<label class="col-xs-6" id="ceComment-'+partnerCompanyAddressId+'">'+ceComment+'</label>'
		   +'	<label class="col-xs-6" id="isCEApproved-'+partnerCompanyAddressId+'">'+isCEApproved+'</label>'
		   +' </div>'
		   +' </a>'
		   +' </li>';
	 }
	 return leftPanelHtml;
}

function showCompanyAddressDetail(id){
	var compAddress=compAddressArray["address"+id];
	loadCompAddressRightPane(compAddress);
	changeCommentAndStatusByRole('compAddressForm',$("#isEEApproved-"+id).html(),$("#eeComment-"+id).html(),$("#isCEApproved-"+id).html(),$("#ceComment-"+id).html())
}

function loadCompAddressLeftPane(data){
	$('.pagination').children().remove();
	$(".leftPaneData").html("");
	var leftPanelHtml='';
	var i=0;
	var active=false;
	var firstRow=null;
	if(!isEmpty(data)){
		$.each(data,function(key,value){ 
			if(!isEmpty(value)){
				var partnerCompanyAddressId = value.partnerCompanyAddressId==null?'':value.partnerCompanyAddressId;	
				compAddressArray["address"+partnerCompanyAddressId]=value;
				
				if(i==0){
					firstRow = value;
					active=true;
				}
				leftPanelHtml= leftPanelHtml +appendAddressData(value,active);
				active=false;
				i++;
			}
		});
		$(".leftPaneData").append(leftPanelHtml);
	}
	
	/*$('.example').paginathing();*/

	$('.leftPaneData').paginathing();
	$(".tabs-left li a label").text(function(index, currentText) {
		return currentText.substr(0, 15);
		});
	loadCompAddressRightPane(firstRow);
	
}

function loadCompAddressRightPane(data){
	editMode=false;
	/*activeTabName="";*/
	if(!$.isEmptyObject(data)){
		var locationId=data.location==null?'':data.location.locationId;
		var registeredAddress = data.location==null?'':data.location.address1;
		var postal  = data.location==null?'':data.location.postal;
		var country  = data.location==null?'':data.location.country==null?'':data.location.country.countryId;
		var region  = data.location==null?'': data.location.region==null?'':data.location.region.regionId;
		var city  = data.location==null?'':data.location.city;
		var district  =data.location==null?'':data.location.district==null?'':data.location.district.districtId;
	
	
	var isShipToLocation = data.isShipToAddress==null?'':data.isShipToAddress;
	var isBillToLocation = data.isBillToAddress==null?'':data.isBillToAddress;
	var partnerCompanyAddressId = data.partnerCompanyAddressId==null?'':data.partnerCompanyAddressId;	
	var remark=data.remark==null?'':data.remark;
	var isApproved=data.isApproved==null?'':data.isApproved;
	var eeComment=data.eeComment==null?'':data.eeComment;
	var isEEApproved=data.isEEApproved==null?'':data.isEEApproved;
	var ceComment=data.ceComment==null?'':data.ceComment;
	var isCEApproved=data.isCEApproved==null?'':data.isCEApproved;
	
	$("#compAddressForm #partnerCompanyAddressId").val(partnerCompanyAddressId);
	$("#compAddressForm #registeredAddress").val(registeredAddress);
	$("#compAddressForm #city").val(city);
	$("#compAddressForm #country").val(country);
	loadStateByCountry('country','compAddressForm','region');
	$("#compAddressForm #region").val(region);
	loadDistrictByState('region','compAddressForm','district');
	$("#compAddressForm #district").val(district);
	$("#compAddressForm #pincode").val(postal);
	$("#compAddressForm .dropDown").removeClass('errorinput');
	setAttribute("isShipToLocation",data.isShipToAddress);
	setAttribute("isBillToLocation",data.isBillToAddress);
	$("#compAddressForm #locationId").val(locationId);
	
	changeCommentAndStatusByRole('compAddressForm',isEEApproved,eeComment,isCEApproved,ceComment)
	
	}

	else{
		$('#compAddressForm')[0].reset();
		$("#compAddressForm #partnerCompanyAddressId").val("");
		$("#compAddressForm #locationId").val("");
	}
	 /*setPartnerHeaderValues("Company Name: "+companyName, "Company Registration No. : "+registrationNo, "Company Type : "+companyType,"Status : "+partnerStatus);*/
	setPartnerHeaderValues("Company Name: "+companyName, "Company Registration No. : "+registrationNo, "Vendor SAP Code : "+vendorSAPCode,"Status : "+partnerStatus);
	 setChildLoadFlag(true);
}


function loadAddCountry(data){

		$("#compAddressForm #country").html("");
		var options = '<option value=""></option>';
		$.each(data,function(key,value){
			if(!isEmpty(value)){
				options +='<option value="'+value.countryId+'">'+value.name +'</option>';
			}
		});
		$("#compAddressForm #country").append(options);
}

function loadAddRegion(data){
		$("#compAddressForm #region").html("");
		var options = '<option value=""></option>';
		$.each(data,function(key,value){
			if(!isEmpty(value)){
			options +='<option value="'+value.regionId+'">'+value.name +'</option>';
			}
		});
		$("#compAddressForm #region").append(options);
}

function loadAddDistrict(data){
		$("#compAddressForm #district").html("");
		var options = '<option value=""></option>';
		$.each(data,function(key,value){
			if(!isEmpty(value)){
			options +='<option value="'+value.districtId+'">'+value.name +'</option>';
			}
		});
		$("#compAddressForm #district").append(options);
}
