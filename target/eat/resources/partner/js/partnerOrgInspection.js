var inspectionArray=new Array();
$(document).ready(function(){

	$('#cancelOrgInspection').click(function(event) {
		event.preventDefault();
		editMode=false;
	  	/*activeTabName="";*/
		debugger;
		var orgInspectionId=$("#orgInspectionForm  #orgInspectionId").val();
		if(orgInspectionId!="")
		{
		  var data=inspectionArray["OI"+orgInspectionId];
		  loadPartnerOrgInspectionRightPane(data);
		}else
			{
			  $("#orgInspectionForm  #orgInspectionId").val("");
			  $('#orgInspectionForm')[0].reset();
			}
    });
	$("#orgInspectionForm").find("input,select,textarea").change(function() {
		 debugger;
	  	 editMode=true;
	  	 activeTabName="Factory Inspection";
	});
	
});
function getFactoryInspection(event,el)
{
	event.preventDefault();	
	
		if(!editMode && !requiredFileDeleted){
			cacheLi();
			setCurrentTab(el);
			if(getChangedFlag()){
        		submitWithParam('getOrgInspection','partnerOrgId','onPartnerOrgInspectionTabLoad');
        		setChangedFlag(false);
			}else{
				getCacheLi();
			}
		}else{
			event.stopPropagation();
	        Alert.warn("Please Save Changed Data Of "+activeTabName+" Tab");
		}
		$("#filterBtnId").addClass("readonly");
	    checkForFilterByRole();
	    setActiveTabName("Factory Inspection",$('.leftPaneData li').length);
	    setHeaderValues("Factory Name: "+FactoryName, "Established Date : "+establishedDate, "License No.: "+licenseNo, "Location : "+locationName);
}
function onPartnerOrgInspectionTabLoad(data) {
debugger;
    $("#orgInspectionForm  .cancel").removeAttr("disabled","disabled");
    if (data.objectMap.hasOwnProperty('partnerOrg')) {
    	if(!$.isEmptyObject(data.objectMap.partnerOrg)){
    		 if(data.objectMap.partnerOrg.isFactoryInspected=="N")
		    	{
		    	   $("#orgInspectionForm .orgInspecitonField").addClass("readonly");
		    	}else{
		    		$("#orgInspectionForm .orgInspecitonField").removeClass("readonly");
		    	}
    	}
    }
	if (data.objectMap.hasOwnProperty('orgInspection')) {
		if(!$.isEmptyObject(data.objectMap.orgInspection))
		    {
		      loadOrgInspectionData(data.objectMap.orgInspection);
		    }else{
		       $("#orgInspectionForm  #orgInspectionId").val("");
		       $('#orgInspectionForm')[0].reset();
		       $("#orgInspectionForm .partnerOrgEEApproveDiv").find('input:radio, textarea').removeAttr('disabled','disabled');
		  	   $("#orgInspectionForm .partnerOrgCEApproveDiv").find('input:radio, textarea').attr('disabled','disabled');
		  	   $("#orgInspectionForm .partnerOrgEEApproveDiv").show();
		  	   $("#orgInspectionForm .partnerOrgCEApproveDiv").hide();
		  	
		    }
		}
}
function loadOrgInspectionData(orgInspection)
{
	debugger;
	$.each(orgInspection,function(key, data) {
		var orgInspectionId=data.orgInspectionId==null?'':data.orgInspectionId;
	    inspectionArray["OI"+orgInspectionId]=data;
		loadPartnerOrgInspectionRightPane(data);
		
	});
}
function loadPartnerOrgInspectionRightPane(data) {
	debugger;
	$('.pagination').children().remove();
	$(".leftPaneData").html("");
	editMode=false;
	
	setChildLoadFlag(true);
	/*activeTabName="";*/
	var orgInspectionId=data.orgInspectionId==null?'':data.orgInspectionId;
	var partnerOrgId=data.partnerOrg==null?'':data.partnerOrg.partnerOrgId==null?'':data.partnerOrg.partnerOrgId;
	var eeComment=data.eeComment==null?'':data.eeComment;
	var isEEApproved=data.isEEApproved==null?'':data.isEEApproved;
	var ceComment=data.ceComment==null?'':data.ceComment;
	var isCEApproved=data.isCEApproved==null?'':data.isCEApproved;
    var isNoteReceived=data.isNoteReceived==null?'':data.isNoteReceived;
    var isCapableForManufacturing=data.isCapableForManufacturing==null?'':data.isCapableForManufacturing;
    var isAdequetTesting=data.isAdequetTesting==null?'':data.isAdequetTesting;
    var isFinanciallyCapable=data.isFinanciallyCapable==null?'':data.isFinanciallyCapable;
    var isAllInspectionReportFilled=data.isAllInspectionReportFilled==null?'':data.isAllInspectionReportFilled;
    var isMachineWorking=data.isMachineWorking==null?'':data.isMachineWorking;
    var isCalibrationCertified=data.isCalibrationCertified==null?'':data.isCalibrationCertified;
    var isOtherItemsManufactured=data.isOtherItemsManufactured==null?'':data.isOtherItemsManufactured;
    var otherItemsManufactured=data.otherItemsManufactured==null?'':data.otherItemsManufactured;
    
    $("#orgInspectionForm  #orgInspectionId").val(orgInspectionId);
    setAttribute("isNoteReceived",isNoteReceived);
    setAttribute("isCapableForManufacturing",isCapableForManufacturing);
    setAttribute("isAdequetTesting",isAdequetTesting);
    setAttribute("isFinanciallyCapable",isFinanciallyCapable);
    setAttribute("isAllInspectionReportFilled",isAllInspectionReportFilled);
    setAttribute("isMachineWorking",isMachineWorking);
    setAttribute("isCalibrationCertified",isCalibrationCertified);
    setAttribute("isOtherItemsManufactured",isOtherItemsManufactured);
    $("#otherItemsManufactured").val(otherItemsManufactured);
    changeOrgCommentAndStatusByRole('orgInspectionForm',isEEApproved,eeComment,isCEApproved,ceComment);
    $('.leftPaneData').paginathing();
}
function orgInspectionResp(data){
	debugger;
	
	setChildLoadFlag(true);
	$('.pagination').children().remove();
if(data.response.hasError==false)
{
	var addressFlag=true;
	var inspectionId=data.orgInspectionId;
	$("#orgInspectionForm  #orgInspectionId").val(inspectionId);
    inspectionArray["OI"+inspectionId]=data;
	editMode=false;
	activeTabName="";
	requiredFileDeleted=false;
	addressFlag=false;
	Alert.info(data.response.message);	
}
else{
	if(!$.isEmptyObject(data.response.errors))
	{
			var msg='';
			 $.each(data.response.errors,function(key,value){
			       msg=msg+'\n'+value.errorMessage+',';
			       
			   });
			    Alert.warn(msg);
	}
	else{
		Alert.warn(data.response.message);
	}
}
 $('.leftPaneData').paginathing();
}


