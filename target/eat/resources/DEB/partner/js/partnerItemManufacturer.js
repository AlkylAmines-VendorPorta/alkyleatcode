/**
 * Aman Sahu
 */
var itemManuftureArray=new Array();
$(document).ready(function(){
	$(".manufacturerCEApproveDiv").hide();
	$(".manufacturerEEApproveDiv").hide();

	var manufacturerName="";
	var emailId="";
	var mobNo="";
	var location="";
	var status= false;
	$('#addItemManufacturerBtnId').click(function(event) {
		event.preventDefault();
		$('#partnerManufacturerForm')[0].reset();
		$("#partnerManufacturerForm #partnerItemManufacturerId").val('');
		$("#partnerManufacturerForm #locationId").val('');
		$('#itemManufacturerDivId').removeClass('readonly');
		$("#partnerManufacturerForm .manufacturerCEApproveDiv").hide();
		$("#partnerManufacturerForm .manufacturerEEApproveDiv").hide();
		$('.partnerManufacturerTabs').removeClass("readonly");
		$('.manufacturerActionBtn').removeClass("readonly");
	});
	
	$('#editItemManufacturerBtnId').click(function(event) {
		event.preventDefault();
		$('#itemManufacturerDivId').removeClass('readonly');
		$('.partnerManufacturerTabs').removeClass("readonly");
		$('.manufacturerActionBtn').removeClass("readonly");
		
	});
	$('#deleteItemManufacturerBtnId').click(function(event) {
		event.preventDefault();
		deleteItemManufaturer();
    });
	
	$('#cancelItemManufacturerBtnId').click(function(event) {
		event.preventDefault();
		   
		editMode=false;
	   	/*activeTabName="";*/
		var activeItemManufacturerId=$('.leftPaneData').find('li.active').attr('id');
		if(activeItemManufacturerId!=undefined)
		{
			showItemManufacturerDetail(activeItemManufacturerId);
		}
	   else
		$('#partnerManufacturerForm')[0].reset();

	});
	$("#partnerManufacturerForm").find("input,select,textarea").change(function() {
		    
	   	 editMode=true;
	   	 activeTabName="Details Of Manufacturer";
	});
});
function getPartnerItemManufacturer(event,el)
{
	event.preventDefault();	
	var ele=$("#tradingItemTabId")[0];
	getManufacturers(event,ele);
	/*if(!editMode && !requiredFileDeleted){
		onToggleTab(el);
		submitWithParam('getPartnerItemManufacturer','bPartnerId','onItemManufacturerTabLoad');	
	}else{
		event.stopPropagation();
        Alert.warn("Please Save Changed Data Of "+activeTabName+" Tab");
	}*/
}
function getManufacturers(event,el)
{
	event.preventDefault();	
	     if(!editMode && !requiredFileDeleted){
	    	 cacheLi();
	    	 setCurrentTab(el);
	    		if(getChangedFlag()){
	    	    	submitWithParam('getPartnerItemManufacturer','bPartnerId','onItemManufacturerTabLoad');	
	    	    	setChangedFlag(false);
	    		}else{
	    			getCacheLi();
	    		}
	    		 setActiveTabName("Details Of Manufacturer",$('.leftPaneData li').length);
	    	     if($('.leftPaneData li').length==0){
	    	    	 setPartnerHeaderValues("Company Name: "+companyName, "Company Registration No. : "+registrationNo, "Vendor SAP Code : "+vendorSAPCode,"Status : "+partnerStatus);
	    	    	/* setPartnerHeaderValues("Company Name: "+companyName, "Company Registration No. : "+registrationNo, "Company Type : "+companyType,"Status : "+partnerStatus);*/
	    	     }else{
	    	         setHeaderValues("Manufacturer Name: "+manufacturerName, "Email : "+emailId, "Mobile No.: "+mobNo, "Location : "+manufacturerCity);
	    	     }
		}else{
			event.stopPropagation();
	        Alert.warn("Please Save Changed Data Of "+activeTabName+" Tab");
		}
	     $("#filterBtnId").addClass("readonly");
		 checkForFilterByRole();
}
function showSubmitFormOnManufacturerChanges()
{
	var partnerData =  $("#partnerData").val();
	if(partnerData=='partnerRegistration')
	{
		$("#partnerDetailsApprovalForm #partnerSubmitDivId").show();
		$("#partnerDetailsApprovalForm #partnerSubmitRespDivId").hide();
		$("#partnerDetailsApprovalForm #partnerApprovalDivId").hide();	
	}
}
function ItemManufacturerResp(data){
	
	setChildLoadFlag(true);
	$('.pagination').children().remove();
	if(!isEmpty(data) && !isEmpty(data.response) ){
	if(data.response.hasError==false){
		if ($("#partnerData").val() == "partnerRegistration") {
		$("#partnerManufacturerForm  .manufacturerCEApproveDiv").hide();
		$("#partnerManufacturerForm  .manufacturerEEApproveDiv").hide();
		}
		$(".disableTraderTabs").show();
		showSubmitFormOnManufacturerChanges();
		editMode=false;
	   	activeTabName="";
	   	requiredFileDeleted=false;
		var userFlag=true;
		var leftPanelHtml='';
		var currentItemManufacturerId=$('ul.leftPaneData').find('li.active').attr('id');
		var itemManufacturerId=data.partnerItemManufacturerId;
		if(currentItemManufacturerId==itemManufacturerId){
			 $('#'+currentItemManufacturerId).remove();
			}
		if(currentItemManufacturerId<itemManufacturerId)
		{
			$('#'+currentItemManufacturerId).removeClass('active');
		}
		$(".partnerManufacturerId").val(itemManufacturerId);
		$("#partnerManufacturerForm #partnerItemManufacturerId").val(itemManufacturerId);
		var locationId=data.location==null?'':data.location.locationId;
		$("#partnerManufacturerForm #locationId").val(data.location.locationId);
		leftPanelHtml=appendItemManufacturerData(data,userFlag);
		$(".leftPaneData").prepend(leftPanelHtml);
		itemManuftureArray["manufacture"+itemManufacturerId]=data;
		userFlag=false;
		setActiveTabName("Details Of Manufacturer",$('.leftPaneData li').length);
		Alert.info(data.response.message);	
		$('#tradingItem_itemTabId').removeClass('readonly');
		manufacturerName=data.name==null?'':data.name;
		emailId=data.email==null?'':data.email;
		mobNo=data.mobileNo==null?'':data.mobileNo;
		manufacturerCity=data.location==null?'':data.location.city==null?'':data.location.city;
		setHeaderValues("Manufacturer Name: "+manufacturerName, "Email : "+emailId, "Mobile No.: "+mobNo, "Location : "+manufacturerCity);
		
	}else
	Alert.warn(data.response.message);
  }
	$('.leftPaneData').paginathing();
	
	$(".tabs-left li a label").text(function(index, currentText) {
	    return currentText.substr(0, 20);
	});
	
	/*$('#saveItemManufacturerBtnId').hide();
	$('#cancelItemManufacturerBtnId').hide();*/
}
	


function onItemManufacturerTabClick(){
	   
	submitToURL("getPartner", 'onItemManufacturerTabLoad');	
}

function onItemManufacturerTabLoad(data){
	if(!isEmpty(data) && !isEmpty(data.objectMap)){
		if(data.objectMap.hasOwnProperty('countries')){
			loadCountry(data.objectMap.countries);
		}
		/*if(data.objectMap.hasOwnProperty('regions'))
			loadRegion(data.objectMap.regions);
		if(data.objectMap.hasOwnProperty('districts'))
			loadDistrict(data.objectMap.districts);
		*/
		if(data.objectMap.hasOwnProperty('partnerItemManufacturer'))
			{
			    $("#srchItemForm #orgId").val("");
				if(!$.isEmptyObject(data.objectMap.partnerItemManufacturer)){
					loadItemManufacturerLeftPane(data.objectMap.partnerItemManufacturer);
					$('#tradingItem_itemTabId').removeClass('readonly');
					$(".disableTraderTabs").show();
				}else{
					$(".disableTraderTabs").hide();
					$(".leftPaneData").html("");
					$('#tradingItem_itemTabId').addClass('readonly');
					$("#srchItemForm #orgId").val("");
					var partnerData =  $("#partnerData").val();
					if(partnerData=='partnerProfiles'){
						$("#traderExpTab").hide();
						$("#traderPerformanceTab").hide();
					}else if(partnerData=='partnerRegistration'){
						$("#editItemManufacturerBtnId").hide();
					}				
				}					
			}
				
	}
		
		setActiveTabName("Details Of Manufacturer",$('.leftPaneData li').length);
			
	}


function loadItemManufacturerLeftPane(data){
	   
	$('.pagination').children().remove(); 
	$(".leftPaneData").html("");
	var leftPanelHtml="";
	var i=0;
	var firstRow=null;
	if(!isEmpty(data)){
		$.each(data,function(key,value){
			if(!isEmpty(value)){
				var itemManufacturerId=value.partnerItemManufacturerId==null?'':value.partnerItemManufacturerId;
				itemManuftureArray["manufacture"+itemManufacturerId]=value;
				
				if(i==0){
					firstRow = value;
					active=true;
				}
				leftPanelHtml= leftPanelHtml +appendItemManufacturerData(value,active);
				active=false;
				i++;
			}
		});
		$(".leftPaneData").append(leftPanelHtml);
		$('.leftPaneData').paginathing();
		
		$(".tabs-left li a label").text(function(index, currentText) {
		    return currentText.substr(0, 20);
		});
	}
	loadItemManufacturerRightPane(firstRow);
	}
function appendItemManufacturerData(value,active){
	   
	$('.pagination').children().remove();
	 var leftPanelHtml='';
	if(!isEmpty(value)){
		var itemManufacturerId=value.partnerItemManufacturerId==null?'':value.partnerItemManufacturerId;
		 if(active) {
			    leftPanelHtml = leftPanelHtml + ' <li class="active" onclick="showItemManufacturerDetail('+itemManufacturerId+')" id="'+itemManufacturerId+'">';
			 }else{
				leftPanelHtml = leftPanelHtml + ' <li onclick="showItemManufacturerDetail('+itemManufacturerId+')" id="'+itemManufacturerId+'">';
			 }
			var name = value.name==null?'':value.name;
			var email 	  = value.email==null?'':value.email;
			var mobileNo = value.mobileNo==null?'':value.mobileNo;
			var telephone1  = value.telephone1==null?'':value.telephone1;
			var telephone2  = value.telephone2==null?'':value.telephone2;
			var fax1  = value.fax1==null?'':value.fax1;
			var fax2  = value.fax2==null?'':value.fax2;
			
			var locationId= (value.location==null||value.location.locationId==null)?'':value.location.locationId;
			var address1  = (value.location==null||value.location.address1==null)?'':value.location.address1;
			var city =      (value.location==null||value.location.city==null)?'':value.location.city;
			var district =  value.location==null?'':value.location.district==null?'':value.location.district.districtId==null?'':value.location.district.districtId;
			var country =   value.location==null?'':value.location.country==null?'':value.location.country.countryId==null?'':value.location.country.countryId;
			var region =    value.location==null?'':value.location.region==null?'':value.location.region.regionId==null?'':value.location.region.regionId;
			var postal =    (value.location==null||value.location.postal==null)?'':value.location.postal;
			
			var partnerItemManufacturerId = value.partnerItemManufacturerId==null?'':value.partnerItemManufacturerId;
			var remark=value.remark==null?'':value.remark;
			var isApproved=value.isApproved==null?'':value.isApproved;
			var eeComment=value.eeComment==null?'':value.eeComment;
			var isEEApproved=value.isEEApproved==null?'':value.isEEApproved;
			var ceComment=value.ceComment==null?'':value.ceComment;
			var isCEApproved=value.isCEApproved==null?'':value.isCEApproved;
			var factoryStatus="";
			var factoryLabelClass="";
			var traderData=setTraderStatus(isEEApproved,isCEApproved);
			tStatus=traderData.tdStatus;
			tClass=traderData.tdClass;
			leftPanelHtml = leftPanelHtml +' <a href="#Master" data-toggle="tab">'
		        +' <div class="col-md-12">'
		        +'  <label class="col-xs-6" id="labName-'+partnerItemManufacturerId+'">'+name+'</label>'
		        +'  <label class="col-xs-6 '+tClass+'" >'+tStatus+'</label>'
		        +' </div>'	
			    +' </a>'
		        +' </li>';
			 $("#partnerManufacturerForm #partnerItemManufacturerId").val(partnerItemManufacturerId);
		return leftPanelHtml;
	}
	 
	/*$('#tradingItem_itemTabId').removeClass('readonly');
	$('.leftPaneData').paginathing();*/
	
	$(".tabs-left li a label").text(function(index, currentText) {
	    return currentText.substr(0, 20);
	});
}
function setTraderStatus(isEEApproved,isCEApproved){
	   
	var tStatus="";
	var tClass="";
	if(isCEApproved=="C"){
		tStatus="CE Clarified";
		tClass="statusclerify";
	}else if(isCEApproved=="Y"){
		tStatus="CE Approved";
		tClass="statusCEApproved";
	}else if(isEEApproved=="Y"){
		tStatus="EE Approved";
		tClass="statusEEApproved";
	}else if(isEEApproved=="C"){
		tStatus="EE Clarified";
		tClass="statusclerify";
	}else if(isEEApproved=="" && isCEApproved==""){
		tStatus="Pending";
		tClass="statuspending";
	}
	return {tdStatus:tStatus, tdClass:tClass};
}
function showItemManufacturerDetail(id){
		var manufactureData=itemManuftureArray["manufacture"+id];
		loadItemManufacturerRightPane(manufactureData);
}

function loadItemManufacturerRightPane(data){
   
    editMode=false;
    
	setChildLoadFlag(true);
	/*activeTabName="";*/
	if(!$.isEmptyObject(data)){
	var name = data.name==null?'':data.name;
	var email 	  = data.email==null?'':data.email;
	var mobileNo = data.mobileNo==null?'':data.mobileNo;
	var telephone1  = data.telephone1==null?'':data.telephone1;
	var telephone2  = data.telephone2==null?'':data.telephone2;
	var fax1  = data.fax1==null?'':data.fax1;
	var fax2  = data.fax2==null?'':data.fax2;
	
	var locationId= (data.location==null||data.location.locationId==null)?'':data.location.locationId;
	var address1  = (data.location==null||data.location.address1==null)?'':data.location.address1;
	var city =      (data.location==null||data.location.city==null)?'':data.location.city;
	var district =  (data.location==null||data.location.district==null||data.location.district.districtId==null)?'':data.location.district.districtId;
	var country =   (data.location==null||data.location.country==null||data.location.country.countryId==null)?'':data.location.country.countryId;
	var region =    (data.location==null||data.location.region==null||data.location.region.regionId==null)?'':data.location.region.regionId;
	var postal =    (data.location==null||data.location.postal==null)?'':data.location.postal;
	
	var partnerItemManufacturerId = data.partnerItemManufacturerId==null?'':data.partnerItemManufacturerId;
	var remark=data.remark==null?'':data.remark;
	var isApproved=data.isApproved==null?'':data.isApproved;
	var eeComment=data.eeComment==null?'':data.eeComment;
	var isEEApproved=data.isEEApproved==null?'':data.isEEApproved;
	var ceComment=data.ceComment==null?'':data.ceComment;
	var isCEApproved=data.isCEApproved==null?'':data.isCEApproved;
	
	$("#partnerManufacturerForm #name").val(name);
	$("#partnerManufacturerForm #email").val(email);
	$("#partnerManufacturerForm #telephone1").val(telephone1);
	$("#partnerManufacturerForm #telephone2").val(telephone2);
	$("#partnerManufacturerForm #fax1").val(fax1);
	$("#partnerManufacturerForm #fax2").val(fax2);
	$("#partnerManufacturerForm #mobileNo").val(mobileNo);
		
	$("#partnerManufacturerForm #partnerItemManufacturerId").val(partnerItemManufacturerId);
	$(".partnerManufacturerId").val(partnerItemManufacturerId);
	$("#partnerManufacturerForm #locationId").val(locationId);
	$("#partnerManufacturerForm #address1").val(address1);
	$("#partnerManufacturerForm #city").val(city);
	$("#partnerManufacturerForm #country").val(country);
	loadStateByCountry('country','partnerManufacturerForm','region');
	$("#partnerManufacturerForm #region").val(region);
	loadDistrictByState('region','partnerManufacturerForm','district');
	$("#partnerManufacturerForm #district").val(district);
	$("#partnerManufacturerForm #postal").val(postal);
	$("#partnerManufacturerForm #remark").val(remark);
	
	changeButtonPropertiesByManufacturerStatus(data);
	changeManufacturerCommentAndStatusByRole('partnerManufacturerForm',isEEApproved,eeComment,isCEApproved,ceComment);
	$(".disableTraderTabs").show();
	manufacturerName=name;
	emailId=email;
	mobNo=mobileNo;
	manufacturerCity=city;
	setHeaderValues("Manufacturer Name: "+manufacturerName, "Email : "+emailId, "Mobile No.: "+mobNo, "Location : "+manufacturerCity);
	}
}
function changeButtonPropertiesByManufacturerStatus(data){
	    
	if(!isEmpty(data)){
		var isEEApproved=data.isEEApproved==null?'':data.isEEApproved;
		 var isCEApproved=data.isCEApproved==null?'':data.isCEApproved;
		 
		 var partnerData =  $("#partnerData").val();
		 $("#addItemManufacturerBtnId").addClass("readonly");
		 $("#editItemManufacturerBtnId").addClass("readonly");
		 $("#editItemManufacturerBtnId").hide();
		 $("#deleteItemManufacturerBtnId").addClass("readonly");
		 $(".partnerManufacturerTabs").addClass("readonly");
		 $(".manufacturerStatusBtn").addClass("readonly");
		 $(".manufacturerRemark").addClass("readonly");
		 $(".manufacturerEEApproveDiv").hide();
		 $(".manufacturerCEApproveDiv").hide();
		 $('.manufacturerActionBtn').addClass("readonly");
		 if(partnerData=="partnerRegistration"){
			 if(data.partner!=null && (data.partner.status=='CO' ||data.partner.status=='EEC'||  data.partner.status=='EDIT' || (data.partner.status=='DR' && (isEEApproved!="" || isCEApproved!="")))){
				     $("#addItemManufacturerBtnId").removeClass("readonly");
					 $("#editItemManufacturerBtnId").removeClass("readonly");
					 $("#editItemManufacturerBtnId").show();
					 $("#deleteItemManufacturerBtnId").removeClass("readonly");
					 $(".manufacturerApproveDiv").show();
				  }else if(data.partner!=null && (data.partner.status=='RJ' || data.partner.status=='CEC' || data.partner.isEEApproved=='Y' || data.partner.status=='IP')){
					  $("#addItemManufacturerBtnId").addClass("readonly");
					  $("#deleteItemManufacturerBtnId").addClass("readonly");
					  $("#editItemManufacturerBtnId").hide();
					  $(".manufacturerEEApproveDiv").show();
					  $(".manufacturerCEApproveDiv").show();
				  }else if(data.partner.status==null || data.partner.status=="" || data.partner.status=='DR')
					 {
					    $("#editItemManufacturerBtnId").hide();
					    $(".partnerManufacturerTabs").removeClass("readonly");
					    $(".manufacturerActionBtn").removeClass("readonly");
					    $("#addItemManufacturerBtnId").removeClass("readonly");
					    $("#deleteItemManufacturerBtnId").removeClass("readonly");
					 }  
			}else{
				$('.manufacturerActionBtn').removeClass("readonly");
				$(".manufacturerEEApproveDiv").show();
				$(".manufacturerCEApproveDiv").show();
				$(".manufacturerStatusBtn").removeClass("readonly");
				$(".manufacturerRemark").removeClass("readonly");
			}
	}
}

function deleteItemManufaturer(){
	input_box = confirm("Do you really want to delete this Item Manufacture?");
	 if (input_box == true) {
		submitWithParam('deletePartnerItemManufacturer','partnerItemManufacturerId','deleteItemManufaturerResp');
	 }
}

function deleteItemManufaturerResp(data){
	$('.pagination').children().remove();
	if(!isEmpty(data)){
		var currentItemManufactureId=$('.leftPaneData').find('li.active').attr('id');
		if(data.hasError==false){
				$('#'+currentItemManufactureId).remove();
				$('#partnerManufacturerForm')[0].reset();
				$("#partnerManufacturerForm #partnerItemManufacturerId").val('');
				$('#itemManufacturerDivId').addClass('readonly');
				
		    	Alert.info(data.message,'','success');
		    	showSubmitFormOnManufacturerChanges();
		    	if ($('.leftPaneData li').length == 0) {
		    		$('.disableTraderTabs').hide();
		    	}
			}else{
		    	Alert.warn(data.responseMsg);
			}
	}
	$('.leftPaneData').paginathing();
	
	$(".tabs-left li a label").text(function(index, currentText) {
	    return currentText.substr(0, 20);
	});
}

function loadCountry(data){
		$("#partnerManufacturerForm #country").html("");
		var options = '<option value=""></option>';
		$.each(data,function(key,value){
			if(!isEmpty(value)){
				options +='<option value="'+value.countryId+'">'+value.name +'</option>';
			}
		});
		$("#partnerManufacturerForm #country").append(options);
}

function loadRegion(data){
		$("#partnerManufacturerForm #region").html("");
		var options = '<option value=""></option>';
		$.each(data,function(key,value){
			if(!isEmpty(value)){
			options +='<option value="'+value.regionId+'">'+value.name +'</option>';
			}
			
		});

		$("#partnerManufacturerForm #region").append(options);
}

function loadDistrict(data){
		$("#partnerManufacturerForm #district").html("");
		var options = '<option value=""></option>';
		$.each(data,function(key,value){
			if(!isEmpty(value)){
			options +='<option value="'+value.districtId+'">'+value.name +'</option>';
			}
			
		});

		$("#partnerManufacturerForm #district").append(options);
}
function changeManufacturerCommentAndStatusByRole(formId,isEEApproved,eeComment,isCEApproved,ceComment)
{
	/*$("#"+formId+" .manufacturerEEApproveDiv").find('input:radio, textarea').removeAttr('disabled','disabled');
	$("#"+formId+" .manufacturerCEApproveDiv").find('input:radio, textarea').removeAttr('disabled','disabled');
*/	
	    $("#"+formId+" .manufacturerEEApproveDiv").find('input:radio, textarea').removeClass('readonly');
		$("#"+formId+" .manufacturerCEApproveDiv").find('input:radio, textarea').removeClass('readonly');
		$("#"+formId+" .manufacturerEEApproveDiv").find('input:radio, textarea').css("background-color","#FFF");
		$("#"+formId+" .manufacturerCEApproveDiv").find('input:radio, textarea').css("background-color","#FFF");

	$("#"+formId+" .manufacturerEEApproveDiv").hide();
	$("#"+formId+" .manufacturerCEApproveDiv").hide();
	   
	 var role=$("#roleData").val();
	 var partnerData =  $("#partnerData").val();
	 if(role=='EXEENGR')
	 {
		$("#"+formId+" .manufacturerEEApproveDiv").show();
	    $("#"+formId+" #eeRemark").val(eeComment);
	    $("#"+formId+" .manufacturerCEApproveDiv").find('input:radio, textarea').attr('disabled','disabled');
	    /*$("#"+formId+" .manufacturerCEApproveDiv").find('input:radio, textarea').addClass('readonly');
	    $("#"+formId+" .manufacturerCEApproveDiv").find('input:radio, textarea').css("background-color","#DADCE2");
	   */ 
	     setStatusButton(formId,isEEApproved,'eeApproveBtn','eeClarification');
	     if(isCEApproved=='C' || isCEApproved=='Y')
	    	 {
	    	     $("#"+formId+" .manufacturerCEApproveDiv").show();
	    	     $("#"+formId+" .manufacturerCEApproveDiv").find('input:radio, textarea').removeAttr('disabled','disabled');
	    	     $("#"+formId+" .manufacturerCEApproveDiv").find('input:radio, textarea').addClass('readonly');
	    		 $("#"+formId+" .manufacturerCEApproveDiv").find('input:radio, textarea').css("background-color","#DADCE2");
		    	 $("#"+formId+" #ceRemark").val(ceComment);
			     setStatusButton(formId,isCEApproved,'ceApproveBtn','ceClarification');
		     }
	     
	 }else if(role=='CHFENGR'){
		 $("#"+formId+" .manufacturerCEApproveDiv").find('input:radio, textarea').removeAttr('disabled','disabled');
		 $("#"+formId+" .manufacturerCEApproveDiv").show();
		 $("#"+formId+" #ceRemark").val(ceComment);
		 setStatusButton(formId,isCEApproved,'ceApproveBtn','ceClarification');
		 $("#"+formId+" .manufacturerEEApproveDiv").show();
		 /* $("#"+formId+" .manufacturerEEApproveDiv").find('input:radio, textarea').attr('disabled','disabled');*/
		 $("#"+formId+" .manufacturerEEApproveDiv").find('input:radio, textarea').addClass('readonly');
		 $("#"+formId+" .manufacturerEEApproveDiv").find('input:radio, textarea').css("background-color","#DADCE2");
		 $("#"+formId+" #eeRemark").val(eeComment);
		 setStatusButton(formId,isEEApproved,'eeApproveBtn','eeClarification');
		 
	 }else if(partnerData=="partnerRegistration"){
		 /*$("#"+formId+" .manufacturerEEApproveDiv").find('input:radio, textarea').attr('disabled','disabled');
		 $("#"+formId+" .manufacturerCEApproveDiv").find('input:radio, textarea').attr('disabled','disabled');
		*/ 
		   $("#"+formId+" .manufacturerEEApproveDiv").find('input:radio, textarea').addClass('readonly');
		   $("#"+formId+" .manufacturerCEApproveDiv").find('input:radio, textarea').addClass('readonly');
		   $("#"+formId+" .manufacturerEEApproveDiv").find('input:radio, textarea').css("background-color","#DADCE2");
		   $("#"+formId+" .manufacturerCEApproveDiv").find('input:radio, textarea').css("background-color","#DADCE2");
		   $("#"+formId+" .manufacturerCEApproveDiv").hide();
		 /*if(isCEApproved!="")
		    {
		    	$("#"+formId+" .manufacturerCEApproveDiv").show();
                setStatusButton(formId,isCEApproved,'ceApproveBtn','ceClarification');
		    }*/
		    if(isEEApproved!="" && isEEApproved=='C'){
		    	 $("#"+formId+" .manufacturerEEApproveDiv").show();
		         setStatusButton(formId,isEEApproved,'eeApproveBtn','eeClarification');
		    }
		    $("#"+formId+" .remark").addClass('readonly');
	        $("#"+formId+" .statusBtn").addClass('readonly');
		    $("#"+formId+" #eeRemark").val(eeComment);
		    $("#"+formId+" #ceRemark").val(ceComment);

		 
	 }

	/* if(role=='EXEENGR')
		 {
		     $("#"+formId+" #remark").val(eeComment);
		     setApprovedStatus(formId,isEEApproved);
		     
		 }else if(role=='CHFENGR'){
			 
			 $("#"+formId+" #remark").val(ceComment);
			 setApprovedStatus(formId,isCEApproved);
		 }else if(partnerData=="partnerRegistration"){
			 
			  if((isCEApproved=="Y" || isCEApproved=="C") && partnerData=="partnerRegistration")
				  {
				    $("#"+formId+" .manufacturerApproveDiv").show();
				    $("#"+formId+" .manufacturerRemark").addClass('readonly');
			        $("#"+formId+" .manufacturerStatusBtn").addClass('readonly');
				    $("#"+formId+" #remark").val(ceComment);
				    $(".changeComment").attr('name','ceComment');
		    		$(".changeButton").attr('name', 'isCEApproved');
				    
			    	setApprovedStatus(formId,isCEApproved);
				  }else if((isEEApproved=="Y" || isEEApproved=="C") && partnerData=="partnerRegistration"){
					    $("#"+formId+" .manufacturerApproveDiv").show();
					    $("#"+formId+" .manufacturerRemark").addClass('readonly');
				        $("#"+formId+" .manufacturerStatusBtn").addClass('readonly');
				        $(".changeComment").attr('name','eeComment');
				    	$(".changeButton").attr('name', 'isEEApproved');
					    $("#"+formId+" #remark").val(eeComment);
					    setApprovedStatus(formId,isEEApproved);
				  }
		 }*/
}

