var mOrgExpArray=new Array();
$(document).ready(function(){
	
	$('#cancelMOrgExpBtnId').click(function(event) {
		debugger;
		event.preventDefault();
		editMode=false;
	   	/*activeTabName="";*/
		var activeExperienceId=$('.leftPaneData').find('li.active').attr('id');
		if(activeExperienceId!=undefined)
			{
			showOrgExpDetail(activeExperienceId);
			}
		else
			$('#mOrgExperienceForm')[0].reset();
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
	$("#mOrgExperienceForm").find("input,select,textarea").change(function() {
		 debugger;
	   	 editMode=true;
	   	 activeTabName="Manufacture Factory Experience Details";
	});
	
});
function getMFactoryExp(event,el)
{
	debugger;
	    event.preventDefault();	
		if(!editMode && !requiredFileDeleted){
			cacheLi();
			setCurrentTab(el);
			if(getChangedFlag()){
				submitWithParam('getTraderExperience','bPartnerId','onManufcatureOrgExperienceTabLoad');	
				setChangedFlag(false);
			}else{
				getCacheLi();
			}
			setActiveTabName("Experience Details",$('.leftPaneData li').length);
			setPartnerHeaderValues("Company Name: "+companyName, "Company Registration No. : "+registrationNo, "Company Type : "+companyType,"Status : "+partnerStatus);
		}else{
			event.stopPropagation();
	        Alert.warn("Please Save Changed Data Of "+activeTabName+" Tab");
		}
		$("#filterBtnId").addClass("readonly");
	    checkForFilterByRole();
}
function partnerMOrgExperienceResp(data){
	
	setChildLoadFlag(true);	
	if(!data.response.hasError)
    {
		if ($("#partnerData").val() == "partnerRegistration") {
			$("#mOrgExperienceForm  .manufacturerEEApproveDiv").hide();
			$("#mOrgExperienceForm  .manufacturerCEApproveDiv").hide();
		}
		editMode=false;
	   	activeTabName="";
	   	requiredFileDeleted=false;
	var leftPanelHtml='';
	var expFlag=true;
	var partnerOrgExpId=data.partnerOrgExperienceId;
	var currentOrgExpId=$('ul.leftPaneData').find('li.active').attr('id');
	if(currentOrgExpId==partnerOrgExpId)
	{
		$('#'+currentOrgExpId).remove();
	}
	/*$("#mOrgExperienceForm .partnerManufacturerOrgId").val(data.partnerOrg.partnerOrgId);*/
	$("#mOrgExperienceForm #mOrgExperienceId").val(partnerOrgExpId);
	leftPanelHtml=appendMOrgExpData(data,expFlag);
	$(".leftPaneData").prepend(leftPanelHtml);
	expFlag=false;
	Alert.info(data.response.message);
	showSubmitFormOnOrgChanges();
	mOrgExpArray["mOrg"+partnerOrgExpId]=data;
    }else{
    	Alert.warn(data.response.message);
    }
	 setPartnerHeaderValues("Company Name: "+companyName, "Company Registration No. : "+registrationNo, "Company Type : "+companyType,"Status : "+partnerStatus);
}

/*function onpartnerOrgExperienceTabClick(){
	submitToURL("getPartner", 'onManufcatureOrgExperienceTabLoad');	
}*/

function onManufcatureOrgExperienceTabLoad(data){
	debugger;
	if(data.objectMap.hasOwnProperty('partnerOrgExperiences'))
		loadMOrgExperienceLeftPane(data.objectMap.partnerOrgExperiences);
	setActiveTabName("Experience Details",$('.leftPaneData li').length);
}

function loadMOrgExperienceLeftPane(data){
	debugger;
	$('.pagination').children().remove();
	/*console.log(data);*/
	$(".leftPaneData").html("");
	var leftPanelHtml="";
	var i=0;
	var expFlag=false;
	var firstRow=null;
	$.each(data,function(key,value){
		var partnerOrgExperienceId= value.partnerOrgExperienceId==null?'':value.partnerOrgExperienceId;
		mOrgExpArray["mOrg"+partnerOrgExperienceId]=value;
		if(i==0){
			firstRow = value;
			expFlag=true;
		}
		leftPanelHtml= leftPanelHtml +appendMOrgExpData(value,expFlag);
		expFlag=false;
		
	   i++;
	});
	$(".leftPaneData").html(leftPanelHtml);
	$('.leftPaneData').paginathing();
	
	$(".tabs-left li a label").text(function(index, currentText) {
	    return currentText.substr(0, 20);
	});
	loadMOrgExperienceRightPane(firstRow);
}
function appendMOrgExpData(value,expFlag)
{
	 var leftPanelHtml='';
	 var partnerOrgExperienceId= value.partnerOrgExperienceId==null?'':value.partnerOrgExperienceId;
	 if(expFlag)
		 {
		    leftPanelHtml = leftPanelHtml + ' <li class="active" onclick="showMOrgExpDetail('+partnerOrgExperienceId+')" id="'+partnerOrgExperienceId+'">';
		 }else{
			leftPanelHtml = leftPanelHtml + ' <li onclick="showMOrgExpDetail('+partnerOrgExperienceId+')" id="'+partnerOrgExperienceId+'">';
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
    +'  <label class="col-xs-6" id="labelexpManufacturingYear">'+expSupplyingYear+'</label>'
    +'	<label class="col-xs-6 " id="labelexpManufacturingMonths">'+expSupplyingMonths+'</label>'
    +' </div>'	
    /*+' <div class="col-md-12">'
    +'	<label class="col-xs-6" id="labelexpDesignYear">'+expDesignYear+'</label>'
    +'	<label class="col-xs-6" id="labelExpDesignMonths">'+expDesignMonths+'</label>'
	+' </div>'*/
	+' </a>'
    +' </li>';
   return leftPanelHtml;
}
function loadMOrgExperienceRightPane(data){
	debugger;
	editMode=false;
	
	setChildLoadFlag(true);	
	/*activeTabName="";*/
	if(!$.isEmptyObject(data)){
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
	
/*	$("#mOrgExperienceForm .partnerManufacturerOrgId").val(partnerOrgId);*/
	$("#mOrgExperienceForm #mOrgExperienceId").val(partnerOrgExperienceId);
	$("#mOrgExperienceForm #mOrgExpManufacturingYear").val(expManufacturingYear);
	$("#mOrgExperienceForm #mOrgExpManufacturingMonths").val(expManufacturingMonths);
	$("#mOrgExperienceForm #mOrgExpDesignYear").val(expDesignYear);
	$("#mOrgExperienceForm #mOrgExpDesignMonths").val(expDesignMonths);
	$("#mOrgExperienceForm #mOrgExpTestingYear").val(expTestingYear);
	$("#mOrgExperienceForm #mOrgExpTestingMonths").val(expTestingMonths);
	$("#mOrgExperienceForm #mOrgExpSupplyingYear").val(expSupplyingYear);
	$("#mOrgExperienceForm #mOrgExpSupplyingMonths").val(expSupplyingMonths);
	
	changeCommentAndStatusByRole('mOrgExperienceForm',isEEApproved,eeComment,isCEApproved,ceComment);
	}else{
		$("#mOrgExperienceForm")[0].reset();
		$("#mOrgExperienceForm #mOrgExperienceId").val("");
		$("#mOrgExperienceForm .manufacturerCEApproveDiv").hide();
		$("#mOrgExperienceForm .manufacturerEEApproveDiv").hide();
	}
	 setPartnerHeaderValues("Company Name: "+companyName, "Company Registration No. : "+registrationNo, "Company Type : "+companyType,"Status : "+partnerStatus);
}

function showMOrgExpDetail(id){
	debugger;
	editMode=false;
	var data=mOrgExpArray["mOrg"+id];
	loadMOrgExperienceRightPane(data);
	/*setApprovedStatus('mOrgExperienceForm',$("#isApproved"+id).html());*/
	changeCommentAndStatusByRole('mOrgExperienceForm',$("#isEEApproved"+id).html(),$("#eeComment"+id).html(),$("#isCEApproved"+id).html(),$("#ceComment"+id).html())
}
