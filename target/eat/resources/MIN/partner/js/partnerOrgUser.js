var orgUserArray=new Array();
$(document).ready(function(){
	$('#addFactoryUserBtnId').click(function(event) {
		event.preventDefault();
		$('#partnerOrgUserForm')[0].reset();
		$('#partnerOrgUserForm #partnerOrgUserId').val("");
		$('#partnerOrgUserForm #userDetailsId').val("");
		$('#partnerOrgUserForm #locationId').val("");
		$("#partnerOrgUserForm .partnerOrgApproveDiv").hide();
	    $("#orgUserOtherDivId").css('display','none');
	    $("#orgUserOtherDesignation").removeClass('requiredField');
		});
		
	$('#editFactoryUserBtnId').click(function(event) {
		event.preventDefault();
		
		});
	
	$('#cancelFactoryContactBtnId').click(function(event) {
			event.preventDefault();
			editMode=false;
		   	/*activeTabName="";*/
			var activeSignatoryId=$('.leftPaneData').find('li.active').attr('id');
			if(activeSignatoryId!=undefined)
				{
				showOrgUserDetail(activeSignatoryId);
				}
			else
				$('#partnerOrgUserForm')[0].reset();
	    });
	
	
	$('#sameAddress').click(function(event){
		
		event.preventDefault();
		 submitWithParam('copyFactoryAddress','partnerOrgId','CopyFactoryAddres');
	});
	$("#partnerOrgUserForm").find("input,select,textarea").change(function() {
		 
	   	 editMode=true;
	   	 activeTabName="Factory Contact Person";
	});
	
	$("#partnerOrgUserForm #sameAddress").click(function() {
		 
	   	 editMode=true;
	   	 activeTabName="Factory Contact Person";
	});
});
function getFactoryContactUser(event,el)
{
	    event.preventDefault();	
	 	if(!editMode && !requiredFileDeleted){
	 		cacheLi();
	 		setCurrentTab(el);
	 	    if(getChangedFlag()){
	 	     submitWithParam('getOrgUser','partnerOrgId','onOrgUserTabLoad');	
	 	     setChangedFlag(false);
	 		}else{
	 			getCacheLi();
	 		}
	 	   setActiveTabName("Contact Person",$('.leftPaneData li').length);
		   setHeaderValues("Factory Name: "+FactoryName, "Established Date : "+establishedDate, "License No.: "+licenseNo, "Location : "+locationName);
    	}else{
    		event.stopPropagation();
            Alert.warn("Please Save Changed Data Of "+activeTabName+" Tab");
    	}
	 	$("#filterBtnId").addClass("readonly");
	    checkForFilterByRole();
}
function partnerOrgUserDelResp(data)
{
	
	$('.pagination').children().remove();
	if(!isEmpty(data)){
	if(!data.hasError)
	{
	Alert.info(data.message);
	showSubmitFormOnOrgChanges();
	var currentOrgUserId=$('ul.leftPaneData').find('li.active').attr('id');
	$('#'+currentOrgUserId).remove();
	$('#partnerOrgUserForm')[0].reset();
	$('#partnerOrgUserForm #partnerOrgUserId').val("");
	$('#partnerOrgUserForm #userDetailsId').val("");
	$('#partnerOrgUserForm #locationId').val("");	
    $("#orgUserOtherDivId").css('display','none');
	$("#orgUserOtherDesignation").removeClass('requiredField');
	}else{
		Alert.warn(data.message);
	}
	}
	event.preventDefault();
	$('.leftPaneData').paginathing();
	
	 $(".tabs-left li a label").text(function(index, currentText) {
		    return currentText.substr(0, 20);
		});
	
}
function partnerOrgUserResp(data){


setChildLoadFlag(true);	
$('.pagination').children().remove();
if(!isEmpty(data)){
  if(data.response.hasError==false)
   {
		  if ($("#partnerData").val() == "partnerRegistration") {
			$("#partnerOrgUserForm  .partnerOrgEEApproveDiv").hide();
			$("#partnerOrgUserForm  .partnerOrgCEApproveDiv").hide();
		}	
	 showSubmitFormOnOrgChanges();
	 editMode=false;
	 activeTabName="";
	 requiredFileDeleted=false;
	var userFlag=true;
	var leftPanelHtml='';
	var partnerOrgUserId=data.partnerOrgUserId==null?'':data.partnerOrgUserId;
	var currentOrgUserId=$('ul.leftPaneData').find('li.active').attr('id');
	if(currentOrgUserId==partnerOrgUserId)
	{
		$('#'+currentOrgUserId).remove();
	}
	else
	{
		$('#'+currentOrgUserId).removeClass('active');
	}
	if(!isEmpty(data.partnerOrg) && !isEmpty(data.partnerOrg.partnerOrgId)){
	$("#partnerOrgUserForm #partnerOrgId").val(data.partnerOrg.partnerOrgId);
	}
	$("#partnerOrgUserForm #partnerOrgUserId").val(partnerOrgUserId);
	if(!isEmpty(data.userDetail) && !isEmpty(data.userDetail.userDetailsId)){
	$("#partnerOrgUserForm #userDetailsId").val(data.userDetail.userDetailsId);
	}
	if(!isEmpty(data.userDetail) && !isEmpty(data.userDetail.location) && !isEmpty(data.userDetail.location.locationId)){
	$("#partnerOrgUserForm #locationId").val(data.userDetail.location.locationId);
	}
	leftPanelHtml=appendOrgUserData(data,userFlag);
	orgUserArray["orgUser"+partnerOrgUserId]=data;
	$(".leftPaneData").prepend(leftPanelHtml);
	userFlag=false;
	/*$('#factoryUserFormDivId').addClass('readonly');*/
	setActiveTabName("Contact Person",$('.leftPaneData li').length);
	Alert.info(data.response.message,'','success');
	setHeaderValues("Factory Name: "+FactoryName, "Established Date : "+establishedDate, "License No.: "+licenseNo, "Location : "+locationName);
	}
	  else
		 {
		  Alert.warn(data.response.message,'','error');
		 }
}
  $('.leftPaneData').paginathing();
	
	 $(".tabs-left li a label").text(function(index, currentText) {
		    return currentText.substr(0, 20);
		});
}

function onOrgUserTabClick(){
	submitToURL("getPartner", 'onOrgUserTabLoad');	
}

function onOrgUserTabLoad(data){

	if(data.objectMap.hasOwnProperty('countries')){
        loadUserCountry(data.objectMap.countries);}
	/*if(data.objectMap.hasOwnProperty('regions')){
		loadUserRegion(data.objectMap.regions);}
	if(data.objectMap.hasOwnProperty('districts')){
		loadUserDistrict(data.objectMap.districts);}*/
	if(data.objectMap.hasOwnProperty('title')){
		loadReferenceListById('partnerOrgUserForm #title',data.objectMap.title);}
	if(data.objectMap.hasOwnProperty('designations')){
		loadUserDesignations(data.objectMap.designations);}
	
	if(data.objectMap.hasOwnProperty('partnerOrgUsers')){
		loadOrgUserLeftPane(data.objectMap.partnerOrgUsers);}
	
	setActiveTabName("Contact Person",$('.leftPaneData li').length);
}

function loadOrgUserLeftPane(data){
	
	$('.pagination').children().remove();
	$(".leftPaneData").html("");
	var leftPanelHtml='';
	var i=0;
	var active=false;
	var firstRow=null;
	if(!isEmpty(data)){
	$.each(data,function(key,value){
		 var partnerOrgUserId=value.partnerOrgUserId==null?'':value.partnerOrgUserId;
		orgUserArray["orgUser"+partnerOrgUserId]=value;
		
		if(i==0){
			firstRow = value;
			active=true;
		}
		leftPanelHtml= leftPanelHtml +appendOrgUserData(value,active);
		active=false;
		i++;
	});
	}
	$(".leftPaneData").append(leftPanelHtml);
	$('leftPaneData').paginathing();
	
	
	loadOrgUserRightPane(firstRow);
	 $(".tabs-left li a label").text(function(index, currentText) {
		    return currentText.substr(0, 20);
		});
}
function appendOrgUserData(value,active)
{
	
	 var leftPanelHtml='';
	 var partnerOrgUserId=value==null?'':value.partnerOrgUserId==null?'':value.partnerOrgUserId;
	 if(active)
		 {
		    leftPanelHtml = leftPanelHtml + ' <li class="active" onclick="showOrgUserDetail('+partnerOrgUserId+')" id="'+partnerOrgUserId+'">';
		 }else{
			leftPanelHtml = leftPanelHtml + ' <li onclick="showOrgUserDetail('+partnerOrgUserId+')" id="'+partnerOrgUserId+'">';
		 }
	
     var firstName = value==null?'':value.userDetail==null?'':value.userDetail.firstName==null?'':value.userDetail.firstName;
     var middleName  = value==null?'':value.userDetail==null?'':value.userDetail.middleName==null?'':value.userDetail.middleName;
     var lastName  = value==null?'':value.userDetail==null?'':value.userDetail.lastName==null?'':value.userDetail.lastName;
     var email 	  = value==null?'':value.userDetail==null?'':value.userDetail.email==null?'':value.userDetail.email;
     var mobileNo = value==null?'':value.userDetail==null?'':value.userDetail.mobileNo==null?'':value.userDetail.mobileNo;
     var title  = value==null?'':value.userDetail==null?'':value.userDetail.title==null?'':value.userDetail.title;
     var telephone1  = value==null?'':value.userDetail==null?'':value.userDetail.telephone1==null?'':value.userDetail.telephone1;
     var telephone2  = value==null?'':value.userDetail==null?'':value.userDetail.telephone2==null?'':value.userDetail.telephone2;
     var fax1  = value==null?'':value.userDetail==null?'':value.userDetail.fax1==null?'':value.userDetail.fax1;
     var fax2  = value==null?'':value.userDetail==null?'':value.userDetail.fax2==null?'':value.userDetail.fax2;
     var designation  = value==null?'':value.userDetail==null?'':(value.userDetail.designation==null ||value.userDetail.designation.designationId==null)?'':value.userDetail.designation.designationId;
     var address1  = value==null?'':value.userDetail==null?'':(value.userDetail.location==null || value.userDetail.location.address1==null)?'':value.userDetail.location.address1;
     var district  = value==null?'':value.userDetail==null?'':(value.userDetail.location==null || value.userDetail.location.district.districtId==null)?'':value.userDetail.location.district.districtId;
     var city  =  value==null?'':value.userDetail==null?'':(value.userDetail.location==null || value.userDetail.location.city==null)?'':value.userDetail.location.city;
     var country  = value==null?'':value.userDetail==null?'':(value.userDetail.location==null || value.userDetail.location.country.countryId==null)?'':value.userDetail.location.country.countryId;
     var region  = value==null?'':value.userDetail==null?'':(value.userDetail.location==null ||value.userDetail.location.region.regionId==null)?'':value.userDetail.location.region.regionId;
     var postal  = value==null?'':value.userDetail==null?'':(value.userDetail.location==null ||value.userDetail.location.postal==null)?'':value.userDetail.location.postal;
     var locationId=value==null?'':value.userDetail==null?'':(value.userDetail.location==null ||value.userDetail.location.locationId==null)?'':value.userDetail.location.locationId;
     var userDetailsId=value==null?'':value.userDetail==null?'':(value.userDetail==null ||value.userDetail.userDetailsId==null)?'':value.userDetail.userDetailsId;
     var partnerOrgId=value==null?'':value.partnerOrg==null?'':value.partnerOrg.partnerOrgId==null?'':value.partnerOrg.partnerOrgId;
     var remark=value==null?'':value.remark==null?'':value.remark;
	 var isApproved=value==null?'':value.isApproved==null?'':value.isApproved;
	 var eeComment=value==null?'':value.eeComment==null?'':value.eeComment;
	 var isEEApproved=value==null?'':value.isEEApproved==null?'':value.isEEApproved;
	 var ceComment=value==null?'':value.ceComment==null?'':value.ceComment;
	 var isCEApproved=value==null?'':value.isCEApproved==null?'':value.isCEApproved;

		
     leftPanelHtml = leftPanelHtml +' <a href="#Master" data-toggle="tab">'
        +' <div class="col-md-12">'
        +'  <label class="col-xs-6" id="labFirstName-'+partnerOrgUserId+'">'+firstName+'</label>'
        +'	<label class="col-xs-6 " id="labLastName-'+partnerOrgUserId+'">'+lastName+'</label>'
        +' </div>'	
        +' <div class="col-md-12">'
        +'	<label class="col-xs-6" id="labelEmail-'+partnerOrgUserId+'">'+email+'</label>'
		+'	<label class="col-xs-6" id="labelMobile-'+partnerOrgUserId+'">'+mobileNo+'</label>'
		+' </div>'
		+' <div class="col-md-12" style="display: none">'
	    +'	<label class="col-xs-6" id="labelMiddleName-'+partnerOrgUserId+'">'+middleName+'</label>'
	    +'	<label class="col-xs-6" id="labelPartnerOrgUserId-'+partnerOrgUserId+'">'+partnerOrgUserId+'</label>'
	    +'	<label class="col-xs-6" id="labelPartnerOrgId-'+partnerOrgUserId+'">'+partnerOrgId+'</label>'
	    +'	<label class="col-xs-6" id="labelTitle-'+partnerOrgUserId+'">'+title+'</label>'
	    +'	<label class="col-xs-6" id="labelTelephone1-'+partnerOrgUserId+'">'+telephone1+'</label>'
	    +'	<label class="col-xs-6" id="labelTelephone2-'+partnerOrgUserId+'">'+telephone2+'</label>'
	    +'	<label class="col-xs-6" id="labelFax1-'+partnerOrgUserId+'">'+fax1+'</label>'
	    +'	<label class="col-xs-6" id="labelFax2-'+partnerOrgUserId+'">'+fax2+'</label>'
	    +'	<label class="col-xs-6" id="labelDesignation-'+partnerOrgUserId+'">'+designation+'</label>'
	    +'	<label class="col-xs-6" id="labelAddress1-'+partnerOrgUserId+'">'+address1+'</label>'
	    +'	<label class="col-xs-6" id="labelDistrict-'+partnerOrgUserId+'">'+district+'</label>'
	    +'	<label class="col-xs-6" id="labelCity-'+partnerOrgUserId+'">'+city+'</label>'
	    +'	<label class="col-xs-6" id="labelCountry-'+partnerOrgUserId+'">'+country+'</label>'
	    +'	<label class="col-xs-6" id="labelRegion-'+partnerOrgUserId+'">'+region+'</label>'
	    +'	<label class="col-xs-6" id="labelPostal-'+partnerOrgUserId+'">'+postal+'</label>'
	    +'	<label class="col-xs-6" id="labelOrgUserDetailId-'+partnerOrgUserId+'">'+userDetailsId+'</label>'
	    +'	<label class="col-xs-6" id="labelOrgUserLocation-'+partnerOrgUserId+'">'+locationId+'</label>'
	    +'	<label class="col-xs-6" id="remark-'+partnerOrgUserId+'">'+remark+'</label>'
	    +'	<label class="col-xs-6" id="isApproved-'+partnerOrgUserId+'">'+isApproved+'</label>'
	    +'	<label class="col-xs-6" id="eeComment-'+partnerOrgUserId+'">'+eeComment+'</label>'
	    +'	<label class="col-xs-6" id="ceComment-'+partnerOrgUserId+'">'+ceComment+'</label>'
	    +'	<label class="col-xs-6" id="isEEApproved-'+partnerOrgUserId+'">'+isEEApproved+'</label>'
	    +'	<label class="col-xs-6" id="isCEApproved-'+partnerOrgUserId+'">'+isCEApproved+'</label>'
	    +' </div>'
        +' </a>'
        +' </li>';
	
	return leftPanelHtml;
}
function showOrgUserDetail(id)
{
	
	var userData=orgUserArray["orgUser"+id];
	loadOrgUserRightPane(userData);
	
}
function loadOrgUserRightPane(data){
	debugger;
	editMode=false;
	
	setChildLoadFlag(true);	
	/*activeTabName="";*/
	if(!isEmpty(data)){
	var firstName = data.userDetail.firstName==null?'':data.userDetail.firstName;
	var middleName  = data.userDetail.middleName==null?'':data.userDetail.middleName;
	var lastName  = data.userDetail.lastName==null?'':data.userDetail.lastName;
	var email 	  = data.userDetail.email==null?'':data.userDetail.email;
	var mobileNo = data.userDetail.mobileNo==null?'':data.userDetail.mobileNo;
	var title  = data.userDetail.title==null?'':data.userDetail.title;
	var telephone1  = data.userDetail.telephone1==null?'':data.userDetail.telephone1;
	var telephone2  = data.userDetail.telephone2==null?'':data.userDetail.telephone2;
	var fax1  = data.userDetail.fax1==null?'':data.userDetail.fax1;
	var fax2  = data.userDetail.fax2==null?'':data.userDetail.fax2;
	var designation  = (data.userDetail.designation==null ||data.userDetail.designation.designationId==null)?'':data.userDetail.designation.designationId;
	var address1  = (data.userDetail.location==null || data.userDetail.location.address1==null)?'':data.userDetail.location.address1;
	var district  = (data.userDetail.location==null || data.userDetail.location.district.districtId==null)?'':data.userDetail.location.district.districtId;
	var city  = (data.userDetail.location==null || data.userDetail.location.city==null)?'':data.userDetail.location.city;
	var country  = (data.userDetail.location==null || data.userDetail.location.country.countryId==null)?'':data.userDetail.location.country.countryId;
	var region  = (data.userDetail.location==null || data.userDetail.location.region.regionId==null)?'':data.userDetail.location.region.regionId;
	var postal  = (data.userDetail.location==null || data.userDetail.location.postal==null)?'':data.userDetail.location.postal;
	var partnerOrgUserId=data.partnerOrgUserId==null?'':data.partnerOrgUserId;
	var userDetailsId=(data.userDetail==null ||data.userDetail.userDetailsId==null)?'':data.userDetail.userDetailsId;
	var partnerOrgId=data.partnerOrg.partnerOrgId==null?'':data.partnerOrg.partnerOrgId;
	var locationId=(data.userDetail.location==null || data.userDetail.location.locationId==null)?'':data.userDetail.location.locationId;
	var remark=data.remark==null?'':data.remark;
	var isApproved=data.isApproved==null?'':data.isApproved;
	var eeComment=data.eeComment==null?'':data.eeComment;
	var isEEApproved=data.isEEApproved==null?'':data.isEEApproved;
	var ceComment=data.ceComment==null?'':data.ceComment;
	var isCEApproved=data.isCEApproved==null?'':data.isCEApproved;
	var otherDesignationDesc=data.userDetail==null?'':data.userDetail.otherDesignationDesc==null?'':data.userDetail.otherDesignationDesc;
		$("#partnerOrgUserForm #locationId").val(locationId);
		$("#partnerOrgUserForm #partnerOrgId").val(partnerOrgId);
		$("#partnerOrgUserForm #partnerOrgUserId").val(partnerOrgUserId);
		$("#partnerOrgUserForm #userDetailsId").val(userDetailsId);
		$("#partnerOrgUserForm #title").val(title);
		$("#partnerOrgUserForm #firstName").val(firstName);
		$("#partnerOrgUserForm #lastName").val(lastName);
		$("#partnerOrgUserForm #email").val(email);
		$("#partnerOrgUserForm #middleName").val(middleName);
		$("#partnerOrgUserForm #telephone1").val(telephone1);
		$("#partnerOrgUserForm #telephone2").val(telephone2);
		$("#partnerOrgUserForm #fax1").val(fax1);
		$("#partnerOrgUserForm #fax2").val(fax2);
		$("#partnerOrgUserForm #mobileNo").val(mobileNo);
		$("#partnerOrgUserForm #address1").val(address1);
		$("#partnerOrgUserForm #city").val(city);
		$("#partnerOrgUserForm #country").val(country);
		loadStateByCountry('country','partnerOrgUserForm','region');
		$("#partnerOrgUserForm #region").val(region);
		loadDistrictByState('region','partnerOrgUserForm','district');
		$("#partnerOrgUserForm #district").val(district);
		$("#partnerOrgUserForm #designation").val(designation);
		$("#partnerOrgUserForm #postal").val(postal);
		$("#partnerOrgUserForm #remark").val(remark);
		$("#partnerOrgUserForm .dropDown").removeClass('errorinput');
		$("#orgUserOtherDesignation").val(otherDesignationDesc);
		setOrgUserField();
	/*	setApprovedStatus('partnerOrgUserForm',isApproved);*/
		changeOrgCommentAndStatusByRole('partnerOrgUserForm',isEEApproved,eeComment,isCEApproved,ceComment);
		
	}else{
		$("#partnerOrgUserForm #locationId").val("");
		$("#partnerOrgUserForm #partnerOrgUserId").val("");
		$("#partnerOrgUserForm #userDetailsId").val("");
		$("#partnerOrgUserForm #title").val("");
		$("#partnerOrgUserForm #firstName").val("");
		$("#partnerOrgUserForm #lastName").val("");
		$("#partnerOrgUserForm #email").val("");
		$("#partnerOrgUserForm #middleName").val("");
		$("#partnerOrgUserForm #telephone1").val("");
		$("#partnerOrgUserForm #telephone2").val("");
		$("#partnerOrgUserForm #fax1").val("");
		$("#partnerOrgUserForm #fax2").val("");
		$("#partnerOrgUserForm #mobileNo").val("");
		$("#partnerOrgUserForm #address1").val("");
		$("#partnerOrgUserForm #city").val("");
		$('#district').find('option:first').attr('selected', 'selected');
		$('#country').find('option:first').attr('selected', 'selected');
		$('#region').find('option:first').attr('selected', 'selected');
		$('#designation').find('option:first').attr('selected', 'selected');
		$("#partnerOrgUserForm #postal").val("");
		$("#partnerOrgUserForm #remark").val("");
		$("#partnerOrgUserForm .partnerOrgEEApproveDiv").hide();
		$("#partnerOrgUserForm .partnerOrgCEApproveDiv").hide();
		setOrgUserField();
	}
	setHeaderValues("Factory Name: "+FactoryName, "Established Date : "+establishedDate, "License No.: "+licenseNo, "Location : "+locationName);
/*	$("#compContactForm locationTypeRef").val(data.locationTypeRef==null?'':data.locationTypeRef);*/
	
}

function loadUserCountry(data){
	
		$("#partnerOrgUserForm #country").html("");
		var options = "<option value=''></option>";
		if(!isEmpty(data)){
		$.each(data,function(key,value){
			options +='<option value="'+value.countryId+'">'+value.name +'</option>'
			
		});
		}
		$("#partnerOrgUserForm #country").append(options);
		
}

function loadUserRegion(data){
	
		$("#partnerOrgUserForm #region").html("");
		var options = "<option value=''></option>";
		if(!isEmpty(data)){
		$.each(data,function(key,value){
			options +='<option value="'+value.regionId+'">'+value.name +'</option>'
			
		});
		}
		$("#partnerOrgUserForm #region").append(options);
}
function loadUserDistrict(data){
	
		$("#partnerOrgUserForm #district").html("");
		var options = "<option value=''></option>";
		if(!isEmpty(data)){
		$.each(data,function(key,value){
			options +='<option value="'+value.districtId+'">'+value.name +'</option>'
			
		});
		}
		$("#partnerOrgUserForm #district").append(options);
}
function loadUserDesignations(data){
	
		$("#partnerOrgUserForm #designation").html("");
		var options = "<option value=''></option>";
		if(!isEmpty(data)){
		$.each(data,function(key,value){
			options +='<option value="'+value.designationId+'" data-code="'+value.code+'">'+value.name +'</option>'
			
		});
		}
		$("#partnerOrgUserForm #designation").append(options);
}


/*Function to copy address data*/
function copyAddress(copy){
	
	if(copy=='Y')
	{
		var address= $("#partnerOrgForm #address1").val();
		var city= $("#partnerOrgForm #city").val();
		var district=$("#partnerOrgForm #district").val();
		var country=$("#partnerOrgForm #country").val();
		var state=$("#partnerOrgForm #region").val();
		var pincode= $("#partnerOrgForm #postal").val();
		
		$('#partnerOrgUserForm #address1').val(address);
		$('#partnerOrgUserForm #city').val(city);
		$('#partnerOrgUserForm #country').val(country);
		loadStateByCountry('country','partnerOrgUserForm','region');
		$('#partnerOrgUserForm #region').val(state);
		loadDistrictByState('region','partnerOrgUserForm','district');
		$('#partnerOrgUserForm #district').val(district);
		$('#partnerOrgUserForm #postal').val(pincode);
		
	}else{
		$('#partnerOrgUserForm #address1').val("");
		$('#partnerOrgUserForm #city').val("");
		$('#partnerOrgUserForm #district').val("");
		$('#partnerOrgUserForm #country').val("");
		$('#partnerOrgUserForm #region').val("");
		$('#partnerOrgUserForm #postal').val("");
	}
	
}

function CopyFactoryAddres(data)
{
	
	if(data.objectMap.hasOwnProperty('orgAddress')){
		$("#partnerOrgUserForm .dropDown").removeClass('errorinput');
		copyfactorydefaultAddress(data.objectMap.orgAddress);
	}else{
		Alert.warn("Please fill factory details to do copy address");
	}
}

/*Function to copy address data*/
function copyfactorydefaultAddress(data){
	
	if(!isEmpty(data)){

		var registeredAddress = data.location==null?'':data.location.address1==null?'':data.location.address1;
			var city = data.location==null?'':data.location.city==null?'':data.location.city;
			var district  = data.location==null?'':data.location.district==null?'':data.location.district.districtId==null?'':data.location.district.districtId;
			var country  = data.location==null?'':data.location.country==null?'':data.location.country.countryId==null?'':data.location.country.countryId;
			var region  = data.location==null?'':data.location.region==null?'':data.location.region.regionId==null?'':data.location.region.regionId;
			var postal = data.location==null?'':data.location.postal==null?'':data.location.postal;
		
			$('#partnerOrgUserForm #address1').val(registeredAddress);
			$('#partnerOrgUserForm #city').val(city);
			$('#partnerOrgUserForm #country').val(country);
			loadStateByCountry('country','partnerOrgUserForm','region');
			$('#partnerOrgUserForm #region').val(region);
			loadDistrictByState('region','partnerOrgUserForm','district');
			$('#partnerOrgUserForm #district').val(district);
			$('#partnerOrgUserForm #postal').val(postal);
		}
		else{
			$('#partnerOrgUserForm #address1').val("");
			$('#partnerOrgUserForm #city').val("");
			$('#partnerOrgUserForm #district').val("");
			$('#partnerOrgUserForm #country').val("");
			$('#partnerOrgUserForm #region').val("");
			$('#partnerOrgUserForm #postal').val("");
	
}
}
function setOrgUserField(){
	var code=$("#partnerOrgUserForm #designation option:selected").data('code');
	if(code=='OTHER')
	{
	   $("#orgUserOtherDivId").css('display','block');
	   $("#orgUserOtherDesignation").addClass('requiredField');
	}else{
		 $("#orgUserOtherDivId").css('display','none');
		 $("#orgUserOtherDesignation").removeClass('requiredField');
	}
}
