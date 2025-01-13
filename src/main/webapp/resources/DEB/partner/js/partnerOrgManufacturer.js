function partnerOrgManufacturerResp(data){
	swal(data.responseDto.message);	
}

function onPartnerOrgManufacturerTabClick(){
	submitToURL("getPartner", 'onPartnerOrgManufacturerTabLoad');	
}

function onPartnerOrgManufacturerTabLoad(data){
	debugger;
	$.each(data,function(key,value){
		if(key=='objectMap'){
			var responseMap = value;
			/*loadPartnerOrgManufacturerLeftPane(responseMap.companyContacts);*/
		}
		/*if(key=="locationTypes"){
			loadLocationTypes(value.locationTypes)
		}*/
		if(key=='countries')
		{
	      loadCountry(value);
		}
		if(key=='regions')
			{
			 loadRegion(value);
			}
		if(key=='districts')
			{
			loadDistrict(value);
			}
	});
}

function loadPartnerOrgManufacturerLeftPane(data){
	$(".leftPaneData").html("");
	var leftPanelHtml="";
	var i=0;
	var firstRow=null;
	$.each(data,function(key,value){
		
		var name = value.name==null?'':value.name;
		var email 	  = value.userDetail.email==null?'':value.userDetail.email;
		var mobileNo = value.userDetail.mobileNo==null?'':value.userDetail.mobileNo;
		var telephone1  = value.userDetail.telephone1==null?'':value.userDetail.telephone1;
		var telephone2  = value.userDetail.telephone2==null?'':value.userDetail.telephone2;
		var fax1  = value.userDetail.fax1==null?'':value.userDetail.fax1;
		var fax2  = value.userDetail.fax2==null?'':value.userDetail.fax2;
		
		var address1  = value.location.address1==null?'':value.location.address1;
		var city = value.location.city==null?'':value.location.city;
		var district = value.location.district.districtId==null?'':value.location.district.districtId;
		var country = value.location.country.countryId==null?'':value.location.country.countryId;
		var region =  value.location.region.regionId==null?'':value.location.region.regionId;
		var pincode = value.location.postal==null?'':value.location.postal;
		var partnerManufacturerId = value.manufacturerId==null?'':value.manufacturerId;
		
		if(i==0){
			firstRow = value;
			leftPanelHtml = leftPanelHtml + ' <li class="active">';
		}else{
			leftPanelHtml = leftPanelHtml + ' <li>';
		}

		leftPanelHtml = leftPanelHtml +' <a href="#Master" data-toggle="tab">'
        +' <div class="col-md-12">'
        +'  <label class="col-xs-6" id="manufacturerName"> '+name+'</label>'
        +'	<label class="col-xs-6 " id="manufacturerEmail">'+email+'</label>'
        +' </div>'	
        +' <div class="col-md-12">'
        +'	<label class="col-xs-6" id="manufacturerMobileNo">'+mobileNo+'</label>'
		+' </div>'
		+' <div class="col-md-12" style="display: none">'
		+'	<label class="col-xs-6" id="manufacturer_Name">'+name+'</label>'
	    +'	<label class="col-xs-6" id="manufacturer_Id">'+partnerManufacturerId+'</label>'
	    +'	<label class="col-xs-6" id="manufacturerTelephone1">'+telephone1+'</label>'
	    +'	<label class="col-xs-6" id="manufacturerTelephone2">'+telephone2+'</label>'
	    +'	<label class="col-xs-6" id="manufacturerFax1">'+fax1+'</label>'
	    +'	<label class="col-xs-6" id="manufacturerFax2">'+fax2+'</label>'
	    +'	<label class="col-xs-6" id="manufacturerMobileNo">'+mobileNo+'</label>'
	    +'	<label class="col-xs-6" id="manufacturerEmail">'+email+'</label>'
	    +'	<label class="col-xs-6" id="manufacturerAddress1">'+address1+'</label>'
	    +'	<label class="col-xs-6" id="manufacturerDistrict">'+district+'</label>'
	    +'	<label class="col-xs-6" id="manufacturerCity">'+city+'</label>'
	    +'	<label class="col-xs-6" id="manufacturerCountry">'+country+'</label>'
	    +'	<label class="col-xs-6" id="manufacturerRegion">'+region+'</label>'
	    +'	<label class="col-xs-6" id="manufacturerPostal">'+pincode+'</label>'
	    +'	<label class="col-xs-6" id="manufacturerDistrictName">'+value.location.district.name+'</label>'
	    +'	<label class="col-xs-6" id="manufacturerCountryName">'+value.location.country.name+'</label>'
	    +'	<label class="col-xs-6" id="manufacturerRegionName">'+value.location.region.name+'</label>'
	    +' </div>'
        +' </a>'
        +' </li>';
		i = i++;
	});
	$(".leftPaneData").html(leftPanelHtml);
	loadPartnerOrgManufacturerRightPane(firstRow);
}

function loadPartnerOrgManufacturerRightPane(data){
	
	var name = data.name==null?'':data.name;
	var email 	  = data.userDetail.email==null?'':data.userDetail.email;
	var mobileNo = data.userDetail.mobileNo==null?'':data.userDetail.mobileNo;
	var telephone1  = data.userDetail.telephone1==null?'':data.userDetail.telephone1;
	var telephone2  = data.userDetail.telephone2==null?'':data.userDetail.telephone2;
	var fax1  = data.userDetail.fax1==null?'':data.userDetail.fax1;
	var fax2  = data.userDetail.fax2==null?'':data.userDetail.fax2;
	
	var address1  = data.location.address1==null?'':data.location.address1;
	var city = data.location.city==null?'':data.location.city;
	var district = data.location.district.districtId==null?'':data.location.district.districtId;
	var country = data.location.country.countryId==null?'':data.location.country.countryId;
	var region =  data.location.region.regionId==null?'':data.location.region.regionId;
	var pincode = data.location.postal==null?'':data.location.postal;
	var partnerManufacturerId = data.manufacturerId==null?'':data.manufacturerId;

	/*$("#labelFirstName").html('<h4>'+firstName+'</h4>');
	$("#labelLastName").html(lastName);
	$("#labelEmail").html(email);
	$("#labelMobileNo").html(mobileNo);*/
	
	$("#partnerManufacturerForm #name").val(name);
	$("#partnerManufacturerForm #email").val(email);
	$("#partnerManufacturerForm #telephone1").val(telephone1);
	$("#partnerManufacturerForm #telephone2").val(telephone2);
	$("#partnerManufacturerForm #fax1").val(fax1);
	$("#partnerManufacturerForm #fax2").val(fax2);
	$("#partnerManufacturerForm #mobileNo").val(mobileNo);
		
	$("#partnerManufacturerForm #partnerDirectorDetailsId").val(partnerDirectorDetailsId);
	$("#partnerManufacturerForm #address1").val(address1);
	$("#partnerManufacturerForm #city").val(city);
	$("#partnerManufacturerForm #district").val(district);
	$("#partnerManufacturerForm #country").val(country);
	$("#partnerManufacturerForm #region").val(region);
	$("#partnerManufacturerForm #postal").val(pincode);
	
	
/*	$("#compContactForm locationTypeRef").val(data.locationTypeRef==null?'':data.locationTypeRef);*/
	
}


function loadCountry(data){
	debugger;
		$("#partnerManufacturerForm #country").html("");
		var options = "<option value=''></option>";
		$.each(data,function(key,value){
			options +='<option value="'+value.countryId+'">'+value.name +'</option>'
			
		});
		$("#partnerManufacturerForm #country").append(options);
}

function loadRegion(data){
	debugger;
		$("#partnerManufacturerForm #region").html("");
		var options = "<option value=''></option>";
		$.each(data,function(key,value){
			options +='<option value="'+value.regionId+'">'+value.name +'</option>'
			
		});

		$("#partnerManufacturerForm #region").append(options);
}

function loadDistrict(data){
	debugger;
		$("#partnerManufacturerForm #district").html("");
		var options = "<option value=''></option>";
		$.each(data,function(key,value){
			options +='<option value="'+value.districtId+'">'+value.name +'</option>'
			
		});

		$("#partnerManufacturerForm #district").append(options);
}


function loadLocationTypes(data){
	function loadLocationTypes(data){
		$("#locationTypeRef").html("");
		var options = "<option value=''></option>";
		$.each(data,function(key,value){
			options = options+'<option>'
		});
	}
}