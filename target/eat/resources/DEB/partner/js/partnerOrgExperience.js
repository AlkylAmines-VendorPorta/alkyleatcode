$(document).ready(function(){
	
	$('#cancelExperienceBtnId').click(function(event) {
		
		event.preventDefault();
		editMode=false;
	   	/*activeTabName="";*/
		var activeExperienceId=$('.leftPaneData').find('li.active').attr('id');
		if(activeExperienceId!=undefined)
			{
			showOrgExpDetail(activeExperienceId);
			}
		else
			$('#partnerOrgExperienceForm')[0].reset();
    });
	
	$('.checkYear').on('keyup',function(event) {
		
		event.preventDefault();
		var year=$(this).val();
		if(year>200)
		   {
			  $(this).val('');
			  $(this).attr('placeholder','Enter valid year');
			  $(this).addClass('placeholderClass');
			  $(this).addClass('plchBorder');
		   }
	});
	$('.checkMonth').on('keyup',function(event) {
		event.preventDefault();
		var month=$(this).val();
		if(month>11)
		   {
			  $(this).val('');
			  $(this).attr('placeholder','Enter valid month');
			  $(this).addClass('placeholderClass');
			  $(this).addClass('plchBorder');
		   }
	});
	$("#partnerOrgExperienceForm").find("input,select,textarea").change(function() {
		 
	   	 editMode=true;
	   	 activeTabName="Factory Experience Details";
	});
	
});
function getExpYear(fromDate,toDate){
       
    var validTo=new Date(toDate);
    var ynew = fromDate.getFullYear();
    var mnew = fromDate.getMonth();
    var dnew = fromDate.getDate();
    var yold = validTo.getFullYear();
    var mold = validTo.getMonth();
    var dold = validTo.getDate();
    var diff = ynew - yold;
    if (mnew > mold || (mnew == mold && dnew > dold)){
        diff++;
    }
    return diff;
}
function getFactoryExperience(event,el)
{
	    event.preventDefault();	
		if(!editMode && !requiredFileDeleted){
			cacheLi();
			setCurrentTab(el);
			if(getChangedFlag()){
				submitWithParam('getOrgExperience','partnerOrgId','onPartnerOrgExperienceTabLoad');	
				setChangedFlag(false);
			}else{
				getCacheLi();
			}
			setActiveTabName("Experience Details",$('.leftPaneData li').length);
			setHeaderValues("Factory Name: "+FactoryName, "Established Date : "+establishedDate, "License No.: "+licenseNo, "Location : "+locationName);
		}else{
			event.stopPropagation();
	        Alert.warn("Please Save Changed Data Of "+activeTabName+" Tab");
		}
		$("#filterBtnId").addClass("readonly");
	    checkForFilterByRole();
}
function partnerOrgExperienceResp(data){
	$('.pagination').children().remove();
	setChildLoadFlag(true);	
	if(!isEmpty(data)){
	var hasError=data.response==null?'':data.response.hasError==null?'':data.response.hasError;
		if(!hasError)
    {
		if ($("#partnerData").val() == "partnerRegistration") {
			$("#partnerOrgExperienceForm  .partnerOrgEEApproveDiv").hide();
			$("#partnerOrgExperienceForm  .partnerOrgCEApproveDiv").hide();
		}
		editMode=false;
	   	activeTabName="";
	   	requiredFileDeleted=false;
	var leftPanelHtml='';
	var expFlag=true;
	var partnerOrgExpId=data.partnerOrgExperienceId==null?'':data.partnerOrgExperienceId;
	var currentOrgExpId=$('ul.leftPaneData').find('li.active').attr('id');
	if(currentOrgExpId==partnerOrgExpId)
	{
		$('#'+currentOrgExpId).remove();
	}
	if(!isEmpty(data.partnerOrg) && !isEmpty(data.partnerOrg.partnerOrgId)){
	$("#partnerOrgExperienceForm #partnerOrgId").val(data.partnerOrg.partnerOrgId);
	}
	$("#partnerOrgExperienceForm #partnerOrgExperienceId").val(partnerOrgExpId);
	leftPanelHtml=appendOrgExpData(data,expFlag);
	$(".leftPaneData").prepend(leftPanelHtml);
	expFlag=false;
	Alert.info(data.response.message);
	showSubmitFormOnOrgChanges();
    }else{
    	if(!isEmpty(data.response.errors))
		{
				var msg=data.response.message;
				 $.each(data.response.errors,function(key,value){
				       msg=msg+'\n'+value.errorMessage+'\n'+",";
				       
				   });
				  Alert.warn(msg);
		}else{
			Alert.warn(data.response.message);
		}
    }
}
	$('.leftPaneData').paginathing();
	setHeaderValues("Factory Name: "+FactoryName, "Established Date : "+establishedDate, "License No.: "+licenseNo, "Location : "+locationName);
}

function onpartnerOrgExperienceTabClick(){
	submitToURL("getPartner", 'onPartnerOrgExperienceTabLoad');	
}

function onPartnerOrgExperienceTabLoad(data){
	if(data.objectMap.hasOwnProperty('partnerOrgExperiences')){
		loadPartnerOrgExperienceLeftPane(data.objectMap.partnerOrgExperiences);
		}
	setActiveTabName("Experience Details",$('.leftPaneData li').length);
}

function loadPartnerOrgExperienceLeftPane(data){
	
	$('.pagination').children().remove();
	console.log(data);
	$(".leftPaneData").html("");
	var leftPanelHtml="";
	var i=0;
	var expFlag=false;
	var firstRow=null;
	if(!isEmpty(data)){
	$.each(data,function(key,value){
		
		if(i==0){
			firstRow = value;
			expFlag=true;
		}
		leftPanelHtml= leftPanelHtml +appendOrgExpData(value,expFlag);
		expFlag=false;
		
	   i++;
	});
	}
	$(".leftPaneData").html(leftPanelHtml);
	$('.leftPaneData').paginathing();
	
	$(".tabs-left li a label").text(function(index, currentText) {
	    return currentText.substr(0, 20);
	});
	loadPartnerOrgExperienceRightPane(firstRow);
}
function appendOrgExpData(value,expFlag)
{
	 var leftPanelHtml='';
	 if(!isEmpty(value)){
	 var partnerOrgExperienceId= value.partnerOrgExperienceId==null?'':value.partnerOrgExperienceId;
	 if(expFlag)
		 {
		    leftPanelHtml = leftPanelHtml + ' <li class="active" onclick="showOrgExpDetail('+partnerOrgExperienceId+')" id="'+partnerOrgExperienceId+'">';
		 }else{
			leftPanelHtml = leftPanelHtml + ' <li onclick="showOrgExpDetail('+partnerOrgExperienceId+')" id="'+partnerOrgExperienceId+'">';
		 }
	var expManufacturingYear = value.expManufacturingYear==null?'':value.expManufacturingYear;
	var expManufacturingMonths  = value.expManufacturingMonths==null?'':value.expManufacturingMonths;
	var expDesignYear 	  = value.expDesignYear==null?'':value.expDesignYear;
	var expDesignMonths  = value.expDesignMonths==null?'':value.expDesignMonths;
	var expTestingYear = value.expTestingYear==null?'':value.expTestingYear;
	var expTestingMonths  = value.expTestingMonths==null?'':value.expTestingMonths;
	var expSupplyingYear 	  = value.expSupplyingYear==null?'':value.expSupplyingYear;
	var expSupplyingMonths  = value.expSupplyingMonths==null?'':value.expSupplyingMonths;
	var partnerOrgId=value.partnerOrg==null?'':value.partnerOrg.partnerOrgId==null?'':value.partnerOrg.partnerOrgId;
	var remark=value.remark==null?'':value.remark;
	var isApproved=value.isApproved==null?'':value.isApproved;
	var eeComment=value.eeComment==null?'':value.eeComment;
	var isEEApproved=value.isEEApproved==null?'':value.isEEApproved;
	var ceComment=value.ceComment==null?'':value.ceComment;
	var isCEApproved=value.isCEApproved==null?'':value.isCEApproved;
	
	leftPanelHtml = leftPanelHtml +' <a href="#Master" data-toggle="tab">'
    +' <div class="col-md-12">'
    +'  <label class="col-xs-6" id="labelexpManufacturingYear">'+expManufacturingYear+'</label>'
    +'	<label class="col-xs-6 " id="labelexpManufacturingMonths">'+expManufacturingMonths+'</label>'
    +' </div>'	
    +' <div class="col-md-12">'
    +'	<label class="col-xs-6" id="labelexpDesignYear">'+expDesignYear+'</label>'
    +'	<label class="col-xs-6" id="labelExpDesignMonths">'+expDesignMonths+'</label>'
	+' </div>'
	+' <div class="col-md-12" style="display: none">'
	+'	<label class="col-xs-6" id="labelPartnerOrgExpId">'+partnerOrgExperienceId+'</label>'
	+'	<label class="col-xs-6" id="labelpartnerOrgId">'+partnerOrgId+'</label>'
    +'	<label class="col-xs-6" id="labelExpDesignMonths">'+expDesignMonths+'</label>'
    +'	<label class="col-xs-6" id="labelExpTestingYear">'+expTestingYear+'</label>'
    +'	<label class="col-xs-6" id="labelExpTestingMonths">'+expTestingMonths+'</label>'
    +'	<label class="col-xs-6" id="labelexpSupplyYear" >'+expSupplyingYear+'</label>'
    +'	<label class="col-xs-6" id="labelexpSupplyMonths">'+expSupplyingMonths+'</label>'
    +'	<label class="col-xs-6" id="remark">'+remark+'</label>'
    +'	<label class="col-xs-6" id="isApproved">'+isApproved+'</label>'
    +'	<label class="col-xs-6" id="eeComment">'+eeComment+'</label>'
    +'	<label class="col-xs-6" id="ceComment">'+ceComment+'</label>'
    +'	<label class="col-xs-6" id="isEEApproved">'+isEEApproved+'</label>'
    +'	<label class="col-xs-6" id="isCEApproved">'+isCEApproved+'</label>'
	+' </div>'
    +' </a>'
    +' </li>';
	 }
   return leftPanelHtml;
}
function loadPartnerOrgExperienceRightPane(data){
	
	editMode=false;
	
	setChildLoadFlag(true);	
	/*activeTabName="";*/
	if(!isEmpty(data)){
	var expManufacturingYear = data.expManufacturingYear==null?'':data.expManufacturingYear;
	var expManufacturingMonths  = data.expManufacturingMonths==null?'':data.expManufacturingMonths;
	var expDesignYear 	  = data.expDesignYear==null?'':data.expDesignYear;
	var expDesignMonths  = data.expDesignMonths==null?'':data.expDesignMonths;
	var expTestingYear = data.expTestingYear==null?'':data.expTestingYear;
	var expTestingMonths  = data.expTestingMonths==null?'':data.expTestingMonths;
	var expSupplyingYear 	  = data.expSupplyingYear==null?'':data.expSupplyingYear;
	var expSupplyingMonths  = data.expSupplyingMonths==null?'':data.expSupplyingMonths;
	var partnerOrgExperienceId= data.partnerOrgExperienceId==null?'':data.partnerOrgExperienceId;
	var partnerOrgId=data.partnerOrg==null?'':data.partnerOrg.partnerOrgId==null?'':data.partnerOrg.partnerOrgId;
	var remark=data.remark==null?'':data.remark;
	var isApproved=data.isApproved==null?'':data.isApproved;
	var eeComment=data.eeComment==null?'':data.eeComment;
	var isEEApproved=data.isEEApproved==null?'':data.isEEApproved;
	var ceComment=data.ceComment==null?'':data.ceComment;
	var isCEApproved=data.isCEApproved==null?'':data.isCEApproved;
	
	$("#partnerOrgExperienceForm #partnerOrgId").val(partnerOrgId);
	$("#partnerOrgExperienceForm #partnerOrgExperienceId").val(partnerOrgExperienceId);
	$("#partnerOrgExperienceForm #expManufacturingYear").val(expManufacturingYear);
	$("#partnerOrgExperienceForm #expManufacturingMonths").val(expManufacturingMonths);
	$("#partnerOrgExperienceForm #expDesignYear").val(expDesignYear);
	$("#partnerOrgExperienceForm #expDesignMonths").val(expDesignMonths);
	$("#partnerOrgExperienceForm #expTestingYear").val(expTestingYear);
	$("#partnerOrgExperienceForm #expTestingMonths").val(expTestingMonths);
	$("#partnerOrgExperienceForm #expSupplyingYear").val(expSupplyingYear);
	$("#partnerOrgExperienceForm #expSupplyingMonths").val(expSupplyingMonths);
	changeOrgCommentAndStatusByRole('partnerOrgExperienceForm',isEEApproved,eeComment,isCEApproved,ceComment);
	}else{
		$("#partnerOrgExperienceForm")[0].reset();
		$("#partnerOrgExperienceForm #partnerOrgExperienceId").val("");
		$("#partnerOrgExperienceForm .partnerOrgEEApproveDiv").hide();
		$("#partnerOrgExperienceForm .partnerOrgCEApproveDiv").hide();
	}
	setHeaderValues("Factory Name: "+FactoryName, "Established Date : "+establishedDate, "License No.: "+licenseNo, "Location : "+locationName);
}

function showOrgExpDetail(id){
	editMode=false;
	$("#partnerOrgExperienceForm #partnerOrgId").val($("#labelpartnerOrgId").html());
	$("#partnerOrgExperienceForm #partnerOrgExperienceId").val($("#labelPartnerOrgExpId").html());
	$("#partnerOrgExperienceForm #expManufacturingYear").val($("#labelexpManufacturingYear").html());
	$("#partnerOrgExperienceForm #expManufacturingMonths").val($("#labelexpManufacturingMonths").html());
	$("#partnerOrgExperienceForm #expDesignYear").val($("#labelexpDesignYear").html());
	$("#partnerOrgExperienceForm #expDesignMonths").val($("#labelExpDesignMonths").html());
	$("#partnerOrgExperienceForm #expTestingYear").val($("#labelExpTestingYear").html());
	$("#partnerOrgExperienceForm #expTestingMonths").val($("#labelExpTestingMonths").html());
	$("#partnerOrgExperienceForm #expSupplyingYear").val($("#labelexpSupplyYear").html());
	$("#partnerOrgExperienceForm #expSupplyingMonths").val($("#labelexpSupplyMonths").html());
	/*setApprovedStatus('partnerOrgExperienceForm',$("#isApproved"+id).html());*/
	changeCommentAndStatusByRole('partnerOrgExperienceForm',$("#isEEApproved"+id).html(),$("#eeComment"+id).html(),$("#isCEApproved"+id).html(),$("#ceComment"+id).html())
}
