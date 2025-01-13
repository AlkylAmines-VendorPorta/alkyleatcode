var directoryDetailArray=new Array();
$(document).ready(function(){
	
var status= false;
$('#addDirectorBtnId').click(function(event) {
	event.preventDefault();
	$('#directorDetailsForm')[0].reset();
	$("#directorDetailsForm #locationId").val("");
	$("#partnerDirectorDetailsId").val("");
	$("#directorDetailsForm .approveDiv").hide();
	$("#mgmtOtherDesignationDivId").css('display','none');
    $("#mgmtOtherDesignation").removeClass('requiredField');
});

$('#editDirectorBtnId').click(function(event) {
	event.preventDefault();
});

$('#cancelDirectorBtnId').click(function(event) {
	event.preventDefault();
	editMode=false;
   	/*activeTabName="";*/
	var activeDirectorId=$('.leftPaneData').find('li.active').attr('id');
	if(activeDirectorId!=undefined)
		{
		  showDirectorDetail(activeDirectorId);
		}
	else
		$('#directorDetailsForm')[0].reset();
});

		$('#sameDirectorAddress').click(function(event){
			event.preventDefault();
			 submitWithParam('copyDirectorAddress','bPartnerId','CopyAllDirectorAddres');
		});
		
		$("#directorDetailsForm").find("input,select,textarea").change(function() {
		  	 editMode=true;
		  	 activeTabName="Management Details";
		});
		$("#directorDetailsForm #sameDirectorAddress").click(function() {
		   	 editMode=true;
		     activeTabName="Management Details";
		});

});

function getPartnerDirectoryDetails(event,el){
	event.preventDefault();
	
		if(!editMode && !requiredFileDeleted){
			$('#pagination-here').empty();
			cacheLi();
			setCurrentTab(el);
			if(getChangedFlag()){
			    submitWithParam('getDirectorDetails','bPartnerId','onDirectorDetailsTabLoad');	
				setChangedFlag(false);
			}else{
				getCacheLi();
			}
			setActiveTabName("Management Details",$('.leftPaneData li').length);
			setPartnerHeaderValues("Company Name: "+companyName, "Company Registration No. : "+registrationNo, "Vendor SAP Code : "+vendorSAPCode,"Status : "+partnerStatus);
			/*setPartnerHeaderValues("Company Name: "+companyName, "Company Registration No. : "+registrationNo, "Company Type : "+companyType,"Status : "+partnerStatus);*/
		}else{
			event.stopPropagation();
	        Alert.warn("Please Save Changed Data Of "+activeTabName+" Tab");
		}
		$("#filterBtnId").addClass("readonly");
	    checkForFilterByRole();
}
function directorDetailsDelResp(data){
	$('.pagination').children().remove();
	if(!isEmpty(data)){
		if(data.hasError==false){
			
			Alert.info(data.message);
			var currentDirectorId=$('ul.leftPaneData').find('li.active').attr('id');
			$('#'+currentDirectorId).remove();
			$('#directorDetailsForm')[0].reset();
			$("#partnerDirectorDetailsId").val("");
			$("#directorDetailsForm #locationId").val("");
			event.preventDefault();
			$("#mgmtOtherDesignationDivId").css('display','none');
			$("#mgmtOtherDesignation").removeClass('requiredField');
			}else{
				Alert.warn(data.message);
			}
	}
	
	$('.leftPaneData').paginathing();
	
	$(".tabs-left li a label").text(function(index, currentText) {
	    return currentText.substr(0, 20);
	});
 }



function partnerDirectorDetailsResp(data){
	
	if(!isEmpty(data)){
	setChildLoadFlag(true);
	$('.pagination').children().remove();
	if ($("#partnerData").val() == "partnerRegistration") {
		$("#directorDetailsForm  .approveCEDiv").hide();
		$("#directorDetailsForm  .approveEEDiv").hide();
	}
	editMode=false;
	activeTabName="";
	requiredFileDeleted=false;
	var userFlag=true;
	var leftPanelHtml='';
	var currentDirectorId=$('ul.leftPaneData').find('li.active').attr('id');

		var partnerDirectorDetailsId=data.userDetailsId;
		if(currentDirectorId==partnerDirectorDetailsId)
			{
			 $('#'+currentDirectorId).remove();
			}
		else
		{
			$('#'+currentDirectorId).removeClass('active');
		}
		
		$("#directorDetailsForm #partnerDirectorDetailsId").val(partnerDirectorDetailsId);
		var locationId=data.location==null?'':data.location.locationId;
		$("#directorDetailsForm #locationId").val(locationId);
	    if(data.designation!=null){
	    	   data.designation.name=$("#directorDetailsForm #designation option:selected").text();
	    	}
		leftPanelHtml=appendDirectorData(data,userFlag);
		directoryDetailArray["directoryDetail"+partnerDirectorDetailsId]=data;
		$(".leftPaneData").prepend(leftPanelHtml);
		userFlag=false;
		Alert.info(data.response.message);
	}
	setActiveTabName("Management Details",$('.leftPaneData li').length);
	setPartnerHeaderValues("Company Name: "+companyName, "Company Registration No. : "+registrationNo, "Vendor SAP Code : "+vendorSAPCode,"Status : "+partnerStatus);
	 /*setPartnerHeaderValues("Company Name: "+companyName, "Company Registration No. : "+registrationNo, "Company Type : "+companyType,"Status : "+partnerStatus);*/
	$('.leftPaneData').paginathing();
	
	$(".tabs-left li a label").text(function(index, currentText) {
	    return currentText.substr(0, 20);
	});
}
	


function onPartnerDirectorDetailsTabClick(){
	submitToURL("getPartner", 'onDirectorDetailsTabLoad');	
}

function onDirectorDetailsTabLoad(data){
	if(!isEmpty(data) && !isEmpty(data.objectMap)){
		if(data.objectMap.hasOwnProperty('countries')){
			loadDirectorCountry(data.objectMap.countries);
		}
		/*if(data.objectMap.hasOwnProperty('regions')){
			loadDirectorRegion(data.objectMap.regions);}
		if(data.objectMap.hasOwnProperty('districts')){
			loadDirectorDistrict(data.objectMap.districts);}*/
		if(data.objectMap.hasOwnProperty('designations')){
			loadDirectorDesignation(data.objectMap.designations);
		}
		if(data.objectMap.hasOwnProperty('title')){
			loadReferenceListById('directorDetailsForm #title',data.objectMap.title);
		}
		if(data.objectMap.hasOwnProperty('userDetails')){
			loadDirectorDetailsLeftPane(data.objectMap.userDetails);
		}
	}
		setActiveTabName("Management Details",$('.leftPaneData li').length);
		
}


function loadDirectorDetailsLeftPane(data){
	$('.pagination').children().remove();
	$(".leftPaneData").html("");
	var leftPanelHtml="";
	var i=0;
	var firstRow=null;
	if(!isEmpty(data)){
		$.each(data,function(key,value){
			if(!isEmpty(value)){
				var partnerDirectorDetailsId=value.userDetailsId==null?'':value.userDetailsId;
				directoryDetailArray["directoryDetail"+partnerDirectorDetailsId]=value;
				if(i==0){
					firstRow = value;
					active=true;
				}
				leftPanelHtml= leftPanelHtml +appendDirectorData(value,active);
				active=false;
				i++;
			}
		});
		$(".leftPaneData").append(leftPanelHtml);
	}
	$('.leftPaneData').paginathing();
	
	$(".tabs-left li a label").text(function(index, currentText) {
	    return currentText.substr(0, 20);
	});
	loadDirectorDetailsRightPane(firstRow);
	}
function appendDirectorData(value,active){
	$('.pagination').children().remove();
	 var leftPanelHtml='';
	 if(!isEmpty(value)){
		 var partnerDirectorDetailsId=value.userDetailsId==null?'':value.userDetailsId;
		 if(active)
			 {
			    leftPanelHtml = leftPanelHtml + ' <li class="active" onclick="showDirectorDetail('+partnerDirectorDetailsId+')" id="'+partnerDirectorDetailsId+'">';
			 }else{
				leftPanelHtml = leftPanelHtml + ' <li onclick="showDirectorDetail('+partnerDirectorDetailsId+')" id="'+partnerDirectorDetailsId+'">';
			 }

		
			var firstName = value.firstName==null?'':value.firstName;
			var middleName  = value.middleName==null?'':value.middleName;
			var lastName  = value.lastName==null?'':value.lastName;
			var email 	  = value.email==null?'':value.email;
			var mobileNo = value.mobileNo==null?'':value.mobileNo;
			var title  = value.title==null?'':value.title;
			var telephone1  = value.telephone1==null?'':value.telephone1;
			var telephone2  = value.telephone2==null?'':value.telephone2;
			var fax1  = value.fax1==null?'':value.fax1;
			var fax2  = value.fax2==null?'':value.fax2;
			var userDetailType  = value.userDetailType==null?'':value.userDetailType;
			
			var designation=(value.designation==null||value.designation.designationId==null)?'':value.designation.designationId;
			var designationName=(value.designation==null||value.designation.name==null)?'':value.designation.name;
			var locationId= (value.location==null||value.location.locationId==null)?'':value.location.locationId;
			var address1  = (value.location==null||value.location.address1==null)?'':value.location.address1;
			var city =      (value.location==null||value.location.city==null)?'':value.location.city;
			var district =  (value.location==null||value.location.district==null|| value.location.district.districtId==null)?'':value.location.district.districtId;
			var country =   (value.location==null||value.location.country ==null|| value.location.country.countryId==null)?'':value.location.country.countryId;
			var region =    (value.location==null||value.location.region  ==null|| value.location.region.regionId==null)?'':value.location.region.regionId;
			var postal =    (value.location==null||value.location.postal==null)?'':value.location.postal;
			
			var partnerDirectorDetailsId = value.userDetailsId==null?'':value.userDetailsId;
			var remark=value.remark==null?'':value.remark;
			var isApproved=value.isApproved==null?'':value.isApproved;
			var eeComment=value.eeComment==null?'':value.eeComment;
			var isEEApproved=value.isEEApproved==null?'':value.isEEApproved;
			var ceComment=value.ceComment==null?'':value.ceComment;
			var isCEApproved=value.isCEApproved==null?'':value.isCEApproved;

			 leftPanelHtml = leftPanelHtml +' <a href="#Master" data-toggle="tab">'
		        +' <div class="col-md-12">'
		        +'  <label class="col-xs-6" id="labFirstName-'+partnerDirectorDetailsId+'">'+firstName+'</label>'
		        +'	<label class="col-xs-6 " id="labLastName-'+partnerDirectorDetailsId+'">'+lastName+'</label>'
		        +' </div>'	
		        +' <div class="col-md-12">'
		        +'	<label class="col-xs-6" id="labelDesignationName-'+partnerDirectorDetailsId+'">'+designationName+'</label>'
				+' </div>'
				+' <div class="col-md-12" style="display: none">'
			    +'	<label class="col-xs-6" id="labelEmail-'+partnerDirectorDetailsId+'">'+email+'</label>'
				+'	<label class="col-xs-6" id="labelMobile-'+partnerDirectorDetailsId+'">'+mobileNo+'</label>'
			    +'	<label class="col-xs-6" id="labelMiddleName-'+partnerDirectorDetailsId+'">'+middleName+'</label>'
			    +'	<label class="col-xs-6" id="labelPartnerDirectorDetailsId-'+partnerDirectorDetailsId+'">'+partnerDirectorDetailsId+'</label>'
			    +'	<label class="col-xs-6" id="labelLocationId-'+partnerDirectorDetailsId+'">'+locationId+'</label>'
			    +'	<label class="col-xs-6" id="labelTitle-'+partnerDirectorDetailsId+'">'+title+'</label>'
			    +'	<label class="col-xs-6" id="labelTelephone1-'+partnerDirectorDetailsId+'">'+telephone1+'</label>'
			    +'	<label class="col-xs-6" id="labelTelephone2-'+partnerDirectorDetailsId+'">'+telephone2+'</label>'
			    +'	<label class="col-xs-6" id="labelFax1-'+partnerDirectorDetailsId+'">'+fax1+'</label>'
			    +'	<label class="col-xs-6" id="labelFax2-'+partnerDirectorDetailsId+'">'+fax2+'</label>'
			    +'	<label class="col-xs-6" id="labelDesignation-'+partnerDirectorDetailsId+'">'+designation+'</label>'
			    +'	<label class="col-xs-6" id="labelAddress1-'+partnerDirectorDetailsId+'">'+address1+'</label>'
			    +'	<label class="col-xs-6" id="labelDistrict-'+partnerDirectorDetailsId+'">'+district+'</label>'
			    +'	<label class="col-xs-6" id="labelCity-'+partnerDirectorDetailsId+'">'+city+'</label>'
			    +'	<label class="col-xs-6" id="labelCountry-'+partnerDirectorDetailsId+'">'+country+'</label>'
			    +'	<label class="col-xs-6" id="labelRegion-'+partnerDirectorDetailsId+'">'+region+'</label>'
			    +'	<label class="col-xs-6" id="labelPostal-'+partnerDirectorDetailsId+'">'+postal+'</label>'
			    +'	<label class="col-xs-6" id="labeluserDetailType-'+partnerDirectorDetailsId+'">'+userDetailType+'</label>'
			    +'	<label class="col-xs-6" id="remark-'+partnerDirectorDetailsId+'">'+remark+'</label>'
			    +'	<label class="col-xs-6" id="isApproved-'+partnerDirectorDetailsId+'">'+isApproved+'</label>'
			    +'	<label class="col-xs-6" id="eeComment-'+partnerDirectorDetailsId+'">'+eeComment+'</label>'
			    +'	<label class="col-xs-6" id="ceComment-'+partnerDirectorDetailsId+'">'+ceComment+'</label>'
			    +'	<label class="col-xs-6" id="isEEApproved-'+partnerDirectorDetailsId+'">'+isEEApproved+'</label>'
			    +'	<label class="col-xs-6" id="isCEApproved-'+partnerDirectorDetailsId+'">'+isCEApproved+'</label>'
			    +' </div>'
		        +' </a>'
		        +' </li>';
 
	 }
	
	
	$('.leftPaneData').paginathing();
	
	$(".tabs-left li a label").text(function(index, currentText) {
	    return currentText.substr(0, 20);
	});
	return leftPanelHtml;
	
}
function showDirectorDetail(id){
	var directoryDetail=directoryDetailArray["directoryDetail"+id];
		loadDirectorDetailsRightPane(directoryDetail);
}
function loadDirectorDetailsRightPane(data){
	editMode=false;
	
	setChildLoadFlag(true);
	/*activeTabName="";*/
	if(!$.isEmptyObject(data)){
	var firstName = data.firstName==null?'':data.firstName;
	var middleName  = data.middleName==null?'':data.middleName;
	var lastName  = data.lastName==null?'':data.lastName;
	var email 	  = data.email==null?'':data.email;
	var mobileNo = data.mobileNo==null?'':data.mobileNo;
	var title  = data.title==null?'':data.title;
	var telephone1  = data.telephone1==null?'':data.telephone1;
	var telephone2  = data.telephone2==null?'':data.telephone2;
	var fax1  = data.fax1==null?'':data.fax1;
	var fax2  = data.fax2==null?'':data.fax2;
	var designation  = data.designation==null?'':data.designation.designationId;
	var locationId= (data.location==null||data.location.locationId==null)?'':data.location.locationId;
	var address1  = data.location.address1==null?'':data.location.address1;
	var city = data.location==null?'':data.location.city;
	var district = data.location==null?'':data.location.district==null?'':data.location.district.districtId;
	var country = data.location==null?'':data.location.country==null?'':data.location.country.countryId;
	var region =  data.location==null?'':data.location.region==null?'':data.location.region.regionId;
	var pincode = data.location.postal==null?'':data.location.postal;
	var partnerDirectorDetailsId = data.userDetailsId==null?'':data.userDetailsId;
	var userDetailType = data.userDetailType==null?'':data.userDetailType;
	var remark=data.remark==null?'':data.remark;
	var isApproved=data.isApproved==null?'':data.isApproved;
	var eeComment=data.eeComment==null?'':data.eeComment;
	var isEEApproved=data.isEEApproved==null?'':data.isEEApproved;
	var ceComment=data.ceComment==null?'':data.ceComment;
	var isCEApproved=data.isCEApproved==null?'':data.isCEApproved;
	var otherDesignationDesc=data.otherDesignationDesc==null?'':data.otherDesignationDesc;
	
	$("#directorDetailsForm #title").val(title);
	$("#directorDetailsForm #firstName").val(firstName);
	$("#directorDetailsForm #lastName").val(lastName);
	$("#directorDetailsForm #email").val(email);
	$("#directorDetailsForm #middleName").val(middleName);
	$("#directorDetailsForm #telephone1").val(telephone1);
	$("#directorDetailsForm #telephone2").val(telephone2);
	$("#directorDetailsForm #fax1").val(fax1);
	$("#directorDetailsForm #fax2").val(fax2);
	$("#directorDetailsForm #mobileNo").val(mobileNo);
	$("#directorDetailsForm #designation").val(designation);
		
	$("#directorDetailsForm #partnerDirectorDetailsId").val(partnerDirectorDetailsId);
	$("#directorDetailsForm #locationId").val(locationId);
	$("#directorDetailsForm #address1").val(address1);
	$("#directorDetailsForm #city").val(city);
	$("#directorDetailsForm #district").val(district);
	$("#directorDetailsForm #country").val(country);
	loadStateByCountry('country','directorDetailsForm','region');
	$("#directorDetailsForm #region").val(region);
	loadDistrictByState('region','directorDetailsForm','district');
	$("#directorDetailsForm #district").val(district);
	$("#directorDetailsForm #postal").val(pincode);
	$("#directorDetailsForm #userDetailType").val(userDetailType);
	$("#directorDetailsForm .dropDown").removeClass('errorinput');
	$("#directorDetailsForm #mgmtOtherDesignation").val(otherDesignationDesc);
	showMgmtField();
	changeCommentAndStatusByRole('directorDetailsForm',isEEApproved,eeComment,isCEApproved,ceComment);
	}	
	else{
		$('#directorDetailsForm')[0].reset();
		showMgmtField();
	}
	/* setPartnerHeaderValues("Company Name: "+companyName, "Company Registration No. : "+registrationNo, "Company Type : "+companyType,"Status : "+partnerStatus);*/
	setPartnerHeaderValues("Company Name: "+companyName, "Company Registration No. : "+registrationNo, "Vendor SAP Code : "+vendorSAPCode,"Status : "+partnerStatus);
}

function loadDirectorCountry(data){
	
		$("#directorDetailsForm #country").html("");
		var options = "<option value=''></option>";
		$.each(data,function(key,value){
			if(!isEmpty(value)){
				options +='<option value="'+value.countryId+'">'+value.name +'</option>'	;
			}	
		});
		$("#directorDetailsForm #country").append(options);
}

/*function loadDirectorRegion(data){
	
		$("#directorDetailsForm #region").html("");
		var options = "<option value=''></option>";
		$.each(data,function(key,value){
			options +='<option value="'+value.regionId+'">'+value.name +'</option>'
			
		});

		$("#directorDetailsForm #region").append(options);
}

function loadDirectorDistrict(data){
	
		$("#directorDetailsForm #district").html("");
		var options = "<option value=''></option>";
		$.each(data,function(key,value){
			options +='<option value="'+value.districtId+'">'+value.name +'</option>'
			
		});

		$("#directorDetailsForm #district").append(options);
}*/

function loadDirectorDesignation(data){
	
		$("#directorDetailsForm #designation").html("");
		var options = "<option value=''></option>";
		$.each(data,function(key,value){
			if(!isEmpty(value)){
			options +='<option value="'+value.designationId+'" data-code="'+value.code+'">'+value.name +'</option>';
			}
			
		});

		$("#directorDetailsForm #designation").append(options);
}

function CopyAllDirectorAddres(data){
	
		if(!isEmpty(data) && !isEmpty(data.objectMap) && data.objectMap.hasOwnProperty('location')){
			$("#directorDetailsForm .dropDown").removeClass('errorinput');
			copyDirectorAddress(data.objectMap.location);
		}else{
				Alert.warn("Please fill contact address to do copy address");
			}
}

/*Function to copy address data*/
function copyDirectorAddress(data){
	if(!$.isEmptyObject(data)){
		var registeredAddress = data.location==null?'':data.location.address1;
		var city = data.location==null?'':data.location.city;
		var districtId  = data.location==null?'':data.location.district==null?'':data.location.district.districtId;
		var countryId  = data.location==null?'':data.location.country==null?'':data.location.country.countryId;
		var regionId  = data.location==null?'':data.location.region==null?'':data.location.region.regionId;
		var postal = data.location.postal==null?'':data.location.postal;
		
		$('#directorDetailsForm #address1').val(registeredAddress);
		$('#directorDetailsForm #city').val(city);
		$('#directorDetailsForm #country').val(countryId);
		loadStateByCountry('country','directorDetailsForm','region');
		$('#directorDetailsForm #region').val(regionId);
		loadDistrictByState('region','directorDetailsForm','district');
		$('#directorDetailsForm #district').val(districtId);
		$('#directorDetailsForm #postal').val(postal);
		
	}else{
		$('#directorDetailsForm #address1').val("");
		$('#directorDetailsForm #city').val("");
		$('#directorDetailsForm #district').val("");
		$('#directorDetailsForm #country').val("");
		$('#directorDetailsForm #region').val("");
		$('#directorDetailsForm #postal').val("");
	}
		
}
function showMgmtField(){
	var code=$("#directorDetailsForm #designation option:selected").data('code');
	if(code=='OTHER')
	{
	   $("#mgmtOtherDesignationDivId").css('display','block');
	   $("#mgmtOtherDesignation").addClass('requiredField');
	}else{
		 $("#mgmtOtherDesignationDivId").css('display','none');
		 $("#mgmtOtherDesignation").removeClass('requiredField');
	}
}
