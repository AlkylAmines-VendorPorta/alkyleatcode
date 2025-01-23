var partnerOEArray=new Array();
$(document).ready(function() {
	changeOEFieldByRole();
	$('#partnerOrgOEForm #isNotApplicableDGSD').click(function() {

		var active = '';
		if (this.checked) {
			active = "Y";
			$('.showDGSDField').find('input:text').val('');
		} else {
			active = "N";
		}
		showDGSDFields(active);
	});


$('#partnerOrgOEForm #isNotApplicableDGTD').click(function(){
	
	var active='';
	if (this.checked) {
		active="Y";
		$('.showDGTDField').find('input:text').val('');
	}else{
		active="N";
	}
	showDGTDFields(active);
});

	$('#cancelOEBtnId').click(function(event) {
		debugger;
		event.preventDefault();
		editMode = false;
		/* activeTabName=""; */
		var data=partnerOEArray["OE"];
		if(data.length!=0){
				loadPartnerOrgOEDRightPane(data);
			}else{
		$('#partnerOrgOEForm')[0].reset();
		$("#OEDetailsIdDGSD").val("");
		$("#OEDetailsIdDGTD").val("");
		$(".showDGTDField").show();
		$('#authorityDGTD').removeAttr('disabled', 'disabled');
		$('#RegsNoDGTD').removeAttr('disabled', 'disabled');
		$('#validFromDGTD').removeAttr('disabled', 'disabled');
		$('#validToDGTD').removeAttr('disabled', 'disabled');
		$(".showDGSDField").show();
		$('#authorityDGSD').removeAttr('disabled', 'disabled');
		$('#RegsNoDGSD').removeAttr('disabled', 'disabled');
		$('#validFromDGSD').removeAttr('disabled', 'disabled');
		$('#validToDGSD').removeAttr('disabled', 'disabled');
       }
	});
		
		$("#partnerOrgOEForm").find("input,select,textarea").change(function() {
			 debugger;
		  	 editMode=true;
		  	 activeTabName="Factory Other Eligibility Details";
		});
		$("#partnerOrgOEForm .fileDeleteBtn").click(function() {
			 debugger;
		  	 editMode=true;
		  	 activeTabName="Factory Other Eligibility Details";
		  	requiredFileDeleted=true;
		});

});
function getFactoryOEDetails(event,el)
{
	    event.preventDefault();	
		if(!editMode && !requiredFileDeleted){
			cacheLi();
			setCurrentTab(el);
			if(getChangedFlag()){
            	submitWithParam('getOrgOtherDetails','partnerOrgId','onPartnerOrgOEDTabLoad');
            	setChangedFlag(false);
			}else{
				getCacheLi();
			}
			setActiveTabName("Other Eligibilty Details",$('.leftPaneData li').length);
			setHeaderValues("Factory Name: "+FactoryName, "Established Date : "+establishedDate, "License No.: "+licenseNo, "Location : "+locationName);
		}else{
			event.stopPropagation();
	        Alert.warn("Please Save Changed Data Of "+activeTabName+" Tab");
		}
		$("#filterBtnId").addClass("readonly");
	    checkForFilterByRole();
}
function showDGSDFields(active)
{
	if(active=='Y')
		{
		  $('#isNotApplicableDGSD').val(active);
          $(".showDGSDField").hide();
          $('#authorityDGSD').attr('disabled','disabled');
          $('#RegsNoDGSD').attr('disabled','disabled');
          $('#validFromDGSD').attr('disabled','disabled');
          $('#validToDGSD').attr('disabled','disabled');
		}else{
		   $('#isNotApplicableDGSD').val(active);
		   $(".showDGSDField").show();
		   $('#authorityDGSD').removeAttr('disabled','disabled');
		   $('#RegsNoDGSD').removeAttr('disabled','disabled');
		   $('#validFromDGSD').removeAttr('disabled','disabled');
		   $('#validToDGSD').removeAttr('disabled','disabled');
		}
}

function showDGTDFields(active)
{
if(active=='Y')
	{
	  $('#isNotApplicableDGTD').val(active);
      $(".showDGTDField").hide();
      $('#authorityDGTD').attr('disabled','disabled');
      $('#RegsNoDGTD').attr('disabled','disabled');
      $('#validFromDGTD').attr('disabled','disabled');
      $('#validToDGTD').attr('disabled','disabled');
	}else{
	   $('#isNotApplicableDGTD').val(active);
	   $(".showDGTDField").show();
	   $('#authorityDGTD').removeAttr('disabled','disabled');
	   $('#RegsNoDGTD').removeAttr('disabled','disabled');
	   $('#validFromDGTD').removeAttr('disabled','disabled');
	   $('#validToDGTD').removeAttr('disabled','disabled');
	}
}
function partnerOrgOEDResp(data){
	debugger;
	$('.pagination').children().remove();
	setChildLoadFlag(true);
	if(data[0].response.hasError==false)
	{
		editMode=false;
		activeTabName="";
		requiredFileDeleted=false;
		
		if ($("#partnerData").val() == "partnerRegistration") {
			$("#partnerOrgOEForm  .partnerOrgEEApproveDiv").hide();
			$("#partnerOrgOEForm  .partnerOrgCEApproveDiv").hide();
		}
	var OEDetailsIdDGSD=data[0].partnerOrgOEId;
	var OEDetailsIdDGTD=data[1].partnerOrgOEId;
	$("#partnerOrgOEForm #partnerOrgId").val(data[0].partnerOrg.partnerOrgId);
	$("#partnerOrgOEForm #partnerOrgId").val(data[1].partnerOrg.partnerOrgId);
	$("#partnerOrgOEForm #OEDetailsIdDGSD").val(OEDetailsIdDGSD);
	$("#partnerOrgOEForm #OEDetailsIdDGTD").val(OEDetailsIdDGTD);
	Alert.info(data[0].response.message);	
	showSubmitFormOnOrgChanges();
	partnerOEArray["OE"]=data;
	}else{
		Alert.warn(data[0].response.message);
	}
	$('.leftPaneData').paginathing();
	setHeaderValues("Factory Name: "+FactoryName, "Established Date : "+establishedDate, "License No.: "+licenseNo, "Location : "+locationName);
}

function onPartnerOrgOEDTabClick(){
	submitToURL("getPartner", 'onPartnerOrgOEDTabLoad');	
}

function onPartnerOrgOEDTabLoad(data){
	
	if(data.objectMap.hasOwnProperty('partnerOrgOEs')){
		loadPartnerOrgOEDRightPane(data.objectMap.partnerOrgOEs);
		partnerOEArray["OE"]=data.objectMap.partnerOrgOEs;
}
	setActiveTabName("Other Eligibilty Details",$('.leftPaneData li').length);
}

function loadPartnerOrgOEDRightPane(data){
	
	$('.pagination').children().remove();
	$(".leftPaneData").html("");
	editMode=false;
	
	setChildLoadFlag(true);
	/*activeTabName="";*/
	if(!$.isEmptyObject(data))
	{
	var isNotApplicableDGSD = data[0].isApplicable==null?'':data[0].isApplicable;
	var isNotApplicableDGTD = data[1].isApplicable==null?'':data[1].isApplicable;
	var otherDetailTypeDGSD = data[0].oeType==null?'':data[0].oeType;
	var otherDetailTypeDGTD = data[1].oeType==null?'':data[1].oeType;
	var authorityDGSD = data[0].authority==null?'':data[0].authority;
	var authorityDGTD = data[1].authority==null?'':data[1].authority;
	var RegsNoDGSD = data[0].regsNo==null?'':data[0].regsNo;
	var RegsNoDGTD = data[1].regsNo==null?'':data[1].regsNo;
	var validFromDGSD = data[0].validFrom==null?'':formatDate(data[0].validFrom);
	var validFromDGTD = data[1].validFrom==null?'':formatDate(data[1].validFrom);
	var validToDGSD = data[0].validTo==null?'':formatDate(data[0].validTo);
	var validToDGTD = data[1].validTo==null?'':formatDate(data[1].validTo);
	var partnerOrgOEIdDGSD = data[0].partnerOrgOEId==null?'':data[0].partnerOrgOEId;
	var partnerOrgOEIdDGTD = data[1].partnerOrgOEId==null?'':data[1].partnerOrgOEId;
	var partnerOrgIdDGSD = data[0].partnerOrg.partnerOrgId=null?'':data[0].partnerOrg.partnerOrgId;
	var partnerOrgIdDGTD = data[1].partnerOrg.partnerOrgId=null?'':data[1].partnerOrg.partnerOrgId;
	var isApprovedDGSD = data[0].isApproved==null?'':data[0].isApproved;
	var isApprovedDGTD = data[1].isApproved==null?'':data[1].isApproved;
	var remarkDGSD = data[0].remark==null?'':data[0].remark;
	var remarkDGTD = data[1].remark==null?'':data[1].remark;
	var eeComment_0=data[0].eeComment==null?'':data[0].eeComment;
	var isEEApproved_0=data[0].isEEApproved==null?'':data[0].isEEApproved;
	var ceComment_0=data[0].ceComment==null?'':data[0].ceComment;
	var isCEApproved_0=data[0].isCEApproved==null?'':data[0].isCEApproved;
	var eeComment_1=data[1].eeComment==null?'':data[1].eeComment;
	var isEEApproved_1=data[1].isEEApproved==null?'':data[1].isEEApproved;
	var ceComment_1=data[1].ceComment==null?'':data[1].ceComment;
	var isCEApproved_1=data[1].isCEApproved==null?'':data[1].isCEApproved;
	
	$("#partnerOrgOEForm #OEDetailsIdDGSD").val(partnerOrgOEIdDGSD);
	$("#partnerOrgOEForm #OEDetailsIdDGTD").val(partnerOrgOEIdDGTD);
	
	$("#partnerOrgOEForm #isNotApplicableDGSD").val(isNotApplicableDGSD);
	$("#partnerOrgOEForm #isNotApplicableDGTD").val(isNotApplicableDGTD);
	
	$("#partnerOrgOEForm #otherDetailTypeDGSD").val(otherDetailTypeDGSD);
	$("#partnerOrgOEForm #otherDetailTypeDGTD").val(otherDetailTypeDGTD);
	
	$("#partnerOrgOEForm #authorityDGSD").val(authorityDGSD);
	$("#partnerOrgOEForm #authorityDGTD").val(authorityDGTD);
	
	$("#partnerOrgOEForm #RegsNoDGSD").val(RegsNoDGSD);
	$("#partnerOrgOEForm #RegsNoDGTD").val(RegsNoDGTD);
	
	$("#partnerOrgOEForm #validFromDGSD").val(validFromDGSD);
	$("#partnerOrgOEForm #validFromDGTD").val(validFromDGTD);
	
	$("#partnerOrgOEForm #validToDGSD").val(validToDGSD);
	$("#partnerOrgOEForm #validToDGTD").val(validToDGTD);
	
	$("#partnerOrgOEForm #partnerOrgId").val(partnerOrgIdDGSD);
	$("#partnerOrgOEForm #partnerOrgId").val(partnerOrgIdDGTD);
	
	setStatusRadioBtn('ee_approve_DGSD','ee_clarify_DGSD','ee_remark_0','ce_approve_DGSD','ce_clarify_DGSD','ce_remark_0','partnerOrgEEApproveDiv_0',isEEApproved_0,eeComment_0,isCEApproved_0,ceComment_0);
	setStatusRadioBtn('ee_approve_DGTD','ee_clarify_DGTD','ee_remark_1','ce_approve_DGTD','ce_clarify_DGTD','ce_remark_1','partnerOrgEEApproveDiv_1',isEEApproved_1,eeComment_1,isCEApproved_1,ceComment_1);
	
    checkForIsApplicableDGSD(isNotApplicableDGSD);
    checkForIsApplicableDGTD(isNotApplicableDGTD);
	}else{
		$("#partnerOrgOEForm #OEDetailsIdDGSD").val("");
		$("#partnerOrgOEForm #OEDetailsIdDGTD").val("");
		$('#partnerOrgOEForm')[0].reset();
		$('#partnerOrgOEForm').find('input:text').val('');
		$('#partnerOrgOEForm').find('select').val('');
		checkForIsApplicableDGTD("N");
		checkForIsApplicableDGSD("N");
		$("#partnerOrgOEForm .partnerOrgEEApproveDiv").hide();
	    $("#partnerOrgOEForm .partnerOrgCEApproveDiv").hide();
	}
	$('.leftPaneData').paginathing();
	setHeaderValues("Factory Name: "+FactoryName, "Established Date : "+establishedDate, "License No.: "+licenseNo, "Location : "+locationName);
}
function checkForIsApplicableDGSD(isApplicable)
{
	if(isApplicable=="Y")
		{
				$('#isNotApplicableDGSD').prop('checked',true);
		        $(".showDGSDField").hide();
		        $('#authorityDGSD').attr('disabled','disabled');
		        $('#RegsNoDGSD').attr('disabled','disabled');
		        $('#validFromDGSD').attr('disabled','disabled');
		        $('#validToDGSD').attr('disabled','disabled');
		}else{
			$('#isNotApplicableDGSD').prop('checked',false);
			$(".showDGSDField").show();
	        $('#authorityDGSD').removeAttr('disabled','disabled');
	        $('#RegsNoDGSD').removeAttr('disabled','disabled');
	        $('#validFromDGSD').removeAttr('disabled','disabled');
	        $('#validToDGSD').removeAttr('disabled','disabled');
		}

}
function checkForIsApplicableDGTD(isApplicable)
{
	if(isApplicable=="Y")
		{
		
		  $('#isNotApplicableDGTD').prop('checked',true);
	      $(".showDGTDField").hide();
	      $('#authorityDGTD').attr('disabled','disabled');
	      $('#RegsNoDGTD').attr('disabled','disabled');
	      $('#validFromDGTD').attr('disabled','disabled');
	      $('#validToDGTD').attr('disabled','disabled');
		}else{
			 $('#isNotApplicableDGTD').prop('checked',false);
		      $(".showDGTDField").show();
		      $('#authorityDGTD').removeAttr('disabled','disabled');
		      $('#RegsNoDGTD').removeAttr('disabled','disabled');
		      $('#validFromDGTD').removeAttr('disabled','disabled');
		      $('#validToDGTD').removeAttr('disabled','disabled');
		}

}
function setStatusRadioBtn(eeApproveBtnId,eeRejectBtnId,eeCommentId,ceApproveBtnId,ceRejectBtnId,ceCommentId,partnerOrgEEApproveDiv,isEEApproved,eeComment,isCEApproved,ceComment)
{
	 debugger;
	    $("#partnerOrgOEForm .partnerOrgEEApproveDiv").find('input:radio, textarea').removeClass('readonly');
		$("#partnerOrgOEForm .partnerOrgCEApproveDiv").find('input:radio, textarea').removeClass('readonly');
		$("#partnerOrgOEForm .partnerOrgEEApproveDiv").find('input:radio, textarea').css("background-color","#FFF");
		$("#partnerOrgOEForm .partnerOrgCEApproveDiv").find('input:radio, textarea').css("background-color","#FFF");
		
	/* $("#partnerOrgOEForm .partnerOrgEEApproveDiv").find('input:radio, textarea').removeAttr('disabled','disabled');
	 $("#partnerOrgOEForm .partnerOrgCEApproveDiv").find('input:radio, textarea').removeAttr('disabled','disabled');
	 *//*$("#partnerOrgOEForm .partnerOrgEEApproveDiv").hide();
	 $("#partnerOrgOEForm .partnerOrgCEApproveDiv").hide();*/
	 
	 var role=$("#roleData").val();
	 var partnerData =  $("#partnerData").val();
	 if(role=='EXEENGR')
	 {
	    $("#partnerOrgOEForm .partnerOrgEEApproveDiv").show();
	    $("#partnerOrgOEForm .partnerOrgCEApproveDiv").hide();
	    $("#"+eeCommentId).val(eeComment);
	    $("#partnerOrgOEForm .partnerOrgCEApproveDiv").find('input:radio, textarea').attr('disabled','disabled');
	   /* $("#partnerOrgOEForm .partnerOrgCEApproveDiv").find('input:radio, textarea').addClass('readonly');
	    $("#partnerOrgOEForm .partnerOrgCEApproveDiv").find('input:radio, textarea').css("background-color","#DADCE2");
	   */ setOEApprovedStatus(eeApproveBtnId,eeRejectBtnId,isEEApproved);
	    if(isCEApproved=='C' || isCEApproved=='Y')
	    	 {
	    	     $("#partnerOrgOEForm .partnerOrgCEApproveDiv").show();
	    	     $("#partnerOrgOEForm .partnerOrgCEApproveDiv").find('input:radio, textarea').removeAttr('disabled','disabled');
	    	     $("#partnerOrgOEForm .partnerOrgCEApproveDiv").find('input:radio, textarea').addClass('readonly');
	    		 $("#partnerOrgOEForm .partnerOrgCEApproveDiv").find('input:radio, textarea').css("background-color","#DADCE2");
	    		 $("#"+ceCommentId).val(ceComment);
		    	 setOEApprovedStatus(ceApproveBtnId,ceRejectBtnId,isCEApproved);
		     }
	     
	 }else if(role=='CHFENGR'){
		 $("#partnerOrgOEForm .partnerOrgCEApproveDiv").find('input:radio, textarea').removeAttr('disabled','disabled');
		 $("#partnerOrgOEForm  .partnerOrgCEApproveDiv").show();
		 $("#"+ceCommentId).val(ceComment);
		 setOEApprovedStatus(ceApproveBtnId,ceRejectBtnId,isCEApproved);
		 $("#partnerOrgOEForm  .partnerOrgEEApproveDiv").show();
		/* $("#partnerOrgOEForm  .partnerOrgEEApproveDiv").find('input:radio, textarea').attr('disabled','disabled');*/
		 $("#partnerOrgOEForm .partnerOrgEEApproveDiv").find('input:radio, textarea').addClass('readonly');
		 $("#partnerOrgOEForm .partnerOrgEEApproveDiv").find('input:radio, textarea').css("background-color","#DADCE2");
		 $("#"+eeCommentId).val(eeComment);
		 setOEApprovedStatus(eeApproveBtnId,eeRejectBtnId,isEEApproved);
		 
	 }else if(partnerData=="partnerRegistration"){
		   $("#partnerOrgOEForm .partnerOrgEEApproveDiv").find('input:radio, textarea').addClass('readonly');
		   $("#partnerOrgOEForm .partnerOrgCEApproveDiv").find('input:radio, textarea').addClass('readonly');
		   $("#partnerOrgOEForm .partnerOrgEEApproveDiv").find('input:radio, textarea').css("background-color","#DADCE2");
		   $("#partnerOrgOEForm .partnerOrgCEApproveDiv").find('input:radio, textarea').css("background-color","#DADCE2");
		   
		/* $("#partnerOrgOEForm  .partnerOrgEEApproveDiv").find('input:radio, textarea').attr('disabled','disabled');
		 $("#partnerOrgOEForm  .partnerOrgCEApproveDiv").find('input:radio, textarea').attr('disabled','disabled');
		*/ $("#partnerOrgOEForm .partnerOrgCEApproveDiv").hide();
		/* if(isCEApproved!="")
		    {
			 if(isCEApproved!="Y"){
			 $("#"+ceCommentId).val(ceComment);
		    	$("#partnerOrgOEForm .partnerOrgCEApproveDiv").show();
		    	setOEApprovedStatus(ceApproveBtnId,ceRejectBtnId,isCEApproved);
			 }
		    }*/
		    if(isEEApproved!=""){
		    	if(isEEApproved=="C"){
		        $("#"+eeCommentId).val(eeComment);
		    	$("#partnerOrgOEForm  #"+partnerOrgEEApproveDiv).show();
		    	 setOEApprovedStatus(eeApproveBtnId,eeRejectBtnId,isEEApproved);
		    	}else{
		    		$("#partnerOrgOEForm  #"+partnerOrgEEApproveDiv).hide();
		    	}
		    }
		    $("#partnerOrgOEForm .remark").addClass('readonly');
	        $("#partnerOrgOEForm .statusBtn").addClass('readonly');
	 }

}
function setOEApprovedStatus(approveBtnId,rejectBtnId,isApproved)
{
	if(isApproved=="Y" || isApproved=="")
		{
		  $("#"+approveBtnId).prop('checked',true);
		  $("#"+rejectBtnId).prop('checked',false);
		}else if(isApproved=="C"){
			  $("#"+approveBtnId).prop('checked',false);
			  $("#"+rejectBtnId).prop('checked',true);
		}
}
function changeOEFieldByRole()
{
	 var role=$("#roleData").val();
	 if(role=='EXEENGR'){
		 $("#remark_0").attr('name','oeList[0].eeComment');
		 $("#remark_1").attr('name','oeList[1].eeComment');
		 $("#approve_DGSD").attr('name','oeList[0].isEEApproved');
		 $("#clarify_DGSD").attr('name','oeList[0].isEEApproved');
		 $("#approve_DGTD").attr('name','oeList[1].isEEApproved');
		 $("#clarify_DGTD").attr('name','oeList[1].isEEApproved');
		 
	 }else if(role=='CHFENGR'){
		 $("#remark_0").attr('name','oeList[0].ceComment');
		 $("#remark_1").attr('name','oeList[1].ceComment');
		 $("#approve_DGSD").attr('name','oeList[0].isCEApproved');
		 $("#clarify_DGSD").attr('name','oeList[0].isCEApproved');
		 $("#approve_DGTD").attr('name','oeList[1].isCEApproved');
		 $("#clarify_DGTD").attr('name','oeList[1].isCEApproved');
	 }

}