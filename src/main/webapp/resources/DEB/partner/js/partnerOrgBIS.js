var orgBISArray=new Array();
$(document).ready(function(){
	
	$('#addBISBtnId').click(function(event) {
		event.preventDefault();
		$('#partnerOrgBISForm')[0].reset();
		$('#partnerOrgBISForm #partnerOrgBISId').val("");
		$('#bisLicenceCertificate').val('');
		isNotApplicableBIS();
		$("#partnerOrgBISForm #a_bisLicenceCopy").html('');
		$(".bisLicenceCertificate").attr('disabled',true);
		$("#partnerOrgBISForm .partnerOrgApproveDiv").hide();
		});
		$('#editBISBtnId').click(function(event) {
		event.preventDefault();
	
		});

		$('.viewModal').click(function() {
						$('.changeOnclick').attr('onclick',"return submitIt('srchItemForm','getItemList','populateMultiSelectItemList')");
						$('.addItemBtn').attr('dropdownid','bisLicenseItem');
				});


		$('#cancelBISBtnId').click(function(event) {
			event.preventDefault();
			editMode=false;
			/*activeTabName="";*/
			var activeBISId=$('.leftPaneData').find('li.active').attr('id');
			if(activeBISId!=undefined)
				{
				showOrgBISDetail(activeBISId);
				}
			else
				$('#partnerOrgBISForm')[0].reset();
			
	    });
		$("#partnerOrgBISForm").find("input,select,textarea").change(function() {
			 
		   	 editMode=true;
		   	 activeTabName="Factory BIS Details";
		});
		$("#partnerOrgBISForm .fileDeleteBtn").click(function() {
			 
		   	 editMode=true;
		   	 activeTabName="Factory BIS Details";
		   	requiredFile
		   	Deleted=true;
		});
});
function getFactoryBISDetails(event,el)
{
	    event.preventDefault();	
	    if(!editMode && !requiredFileDeleted){
	    	cacheLi();
	    	setCurrentTab(el);
	    	if(getChangedFlag()){
	          submitWithParam('getOrgBISDetails','partnerOrgId','onPartnerOrgBISTabLoad');
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
	    setActiveTabName("BIS Details",$('.leftPaneData li').length);
	    setHeaderValues("Factory Name: "+FactoryName, "Established Date : "+establishedDate, "License No.: "+licenseNo, "Location : "+locationName);
	    setFactoryDate();
}
function populateMultiSelectItemList(data){
	$(".itemTable tbody").empty();
	if(!$.isEmptyObject(data)){
		var itemList='';
		for(var i=0;i<data.length;i++)
			{
				itemList +='<tr> <td><input id="'+data[i].materialId+'" class="checkedBox" type="checkbox" value="'+data[i].materialId+'"/>'+
				'<td>'+data[i].hsnCode.code+'</td>'+
				'<td id="'+data[i].materialId+'">'+data[i].code+'</td>'+
				'<td>'+data[i].name+'</td>'+
				'</tr>';
			}
		$(".itemTable tbody").append(itemList);
		}
}

function getSelectedBISItem(){
	 
	 var map = {}; 
	 var itemId='';
	 var itemName='';
	 $.each($("input.checkedBox:checked").closest("td").siblings("td:nth-child(3)"),
	            function () {
		            
		            itemId=$(this).attr('id');
		            itemName=$(this).text();
	    	    	map[itemId]=itemName;
	            });
	 appendBISItem(map);
}
function appendBISItem(data)
{
 
	$("#bisLicenseItem").html("");
	var options='';
	if(!isEmpty(data)){
		$.each(data,function(index,val){
			
			options +='<option value="'+index+'">'+val +'</option>'
			
		});
	}
	$("#bisLicenseItem").append(options);
}

function partnerOrgBISResp(data){
	
	
	$('.pagination').children().remove();
	if(!isEmpty(data) && !isEmpty(data.response)){
	if(!data.response.hasError)
	{
		if ($("#partnerData").val() == "partnerRegistration") {
			$("#partnerOrgBISForm  .partnerOrgEEApproveDiv").hide();
			$("#partnerOrgBISForm  .partnerOrgCEApproveDiv").hide();
		}
	showSubmitFormOnOrgChanges();
	var currentOrgBISId=$('ul.leftPaneData').find('li.active').attr('id');
	
	editMode=false;
	activeTabName="";
	requiredFileDeleted=false;
	var leftPanelHtml="";
	var status=true;
	var partnerOrgBISId=data.partnerOrgBisId;
	$('#partnerOrgBISForm #partnerOrgId').val(data.partnerOrg.partnerOrgId);
	$('#partnerOrgBISForm #partnerOrgBISId').val(partnerOrgBISId);
	
	if(currentOrgBISId==partnerOrgBISId)
	{
		$('#'+currentOrgBISId).remove();
	}else
	{
		$('#'+currentOrgBISId).removeClass('active');
	}	
	leftPanelHtml=appendPartnerOrgBISData(data,status);
	$(".leftPaneData").prepend(leftPanelHtml);
	if(data.bisLicenceCertificate!=null)
	{
	 data.bisLicenceCertificate.fileName=$("#partnerOrgBISForm #a_bisLicenceCopy").html();
	}
	orgBISArray["orgBIS"+partnerOrgBISId]=data;
	$("#lblbisLicenceCertificateName-"+partnerOrgBISId).html($("#partnerOrgBISForm #a_bisLicenceCopy").html());
	status=false;
	setActiveTabName("BIS Details",$('.leftPaneData li').length);
	Alert.info(data.response.message);
	setChildLoadFlag(true);
	setChangedFlagById("factoryItemManufacturedTabId",true);
	}else{
		if(!$.isEmptyObject(data.response.errors))
		{
				var msg='';
				 $.each(data.response.errors,function(key,value){
				       msg=msg+'\n'+value.errorMessage+'\n';
				       
				   });
				    Alert.warn(msg);
		}
		else{
			Alert.warn(data.response.message);
		}
	}
	}
	setHeaderValues("Factory Name: "+FactoryName, "Established Date : "+establishedDate, "License No.: "+licenseNo, "Location : "+locationName);
	$('.leftPaneData').paginathing();
	
	$(".tabs-left li a label").text(function(index, currentText) {
	    return currentText.substr(0, 20);
	});
}
function partnerOrgBISDelResp(data)
{
	if(!isEmpty(data)){
	if(data.hasError==false)
	{
	Alert.info(data.message);
	showSubmitFormOnOrgChanges();
	$('.pagination').children().remove();
	var currentOrgBISId=$('ul.leftPaneData').find('li.active').attr('id');
	$('#'+currentOrgBISId).remove();
	$('#partnerOrgBISForm')[0].reset();
	$('#partnerOrgBISForm #partnerOrgBISId').val("");
	$('#bisLicenceCertificate').val('');
	isNotApplicableBIS();
	$("#partnerOrgBISForm #a_bisLicenceCopy").html('');
	}else{
		Alert.warn(data.message);
	}
	event.preventDefault();
	}
	$('.leftPaneData').paginathing();
	
	$(".tabs-left li a label").text(function(index, currentText) {
	    return currentText.substr(0, 20);
	});
}

function onPartnerOrgBISTabClick(){
	submitToURL("getPartner", 'onPartnerOrgBISTabLoad');	
}

function onPartnerOrgBISTabLoad(data){
	
	if(data.objectMap.hasOwnProperty('orgBIS'))
		loadPartnerOrgBISLeftPane(data.objectMap.orgBIS);
	
	setActiveTabName("BIS Details",$('.leftPaneData li').length);
}
function loadPartnerOrgBISLeftPane(data){
	console.log(data);

$('.pagination').children().remove();
	$(".leftPaneData").html("");
	var leftPanelHtml="";
	var i=0;
	var active=true;
	var firstRow=null;
	if(!isEmpty(data)){
	$.each(data,function(key,value){
		
		var partnerOrgBisId = value.partnerOrgBisId==null?'':value.partnerOrgBisId;
		orgBISArray["orgBIS"+partnerOrgBisId]=value;
		
		if(i==0){
			firstRow = value;
			active=true;
		}
		leftPanelHtml=leftPanelHtml+appendPartnerOrgBISData(value,active);
		active=false;
		i++;
	 });
	}
	$(".leftPaneData").append(leftPanelHtml);
	$('.leftPaneData').paginathing();
	
	$(".tabs-left li a label").text(function(index, currentText) {
	    return currentText.substr(0, 20);
	});
	loadPartnerOrgBISRightPane(firstRow);
}
function appendPartnerOrgBISData(value,active)
{
	var leftPanelHtml="";
	if(!isEmpty(value)){
	 var partnerOrgBisId = value.partnerOrgBisId==null?'':value.partnerOrgBisId;
	 if(active)
	 {
	    leftPanelHtml = leftPanelHtml + ' <li class="active" onclick="showOrgBISDetail('+partnerOrgBisId+')" id="'+partnerOrgBisId+'">';
	 }else{
		leftPanelHtml = leftPanelHtml + ' <li onclick="showOrgBISDetail('+partnerOrgBisId+')" id="'+partnerOrgBisId+'">';
	 }
	
	if(getCheckBoxVal(value.isNotApplicable)){
		leftPanelHtml = leftPanelHtml +' <a href="#Master" data-toggle="tab"><div class="col-md-12">'
		leftPanelHtml = leftPanelHtml + ' Is Not Applicable </div></a></li>' ;
		return leftPanelHtml;
	}else{
		    var bisLicenceNo = value.bisLicenceNo==null?'':value.bisLicenceNo;
			var issueDate  = value.validFrom==null?'':formatDate(value.validFrom);
			var bisValidityDate = value.validTo==null?'':formatDate(value.validTo);
			var partnerOrgId  = value.partnerOrg==null?'':value.partnerOrg.partnerOrgId==null?'':value.partnerOrg.partnerOrgId;
			var bisLicenceCertificateId=value.bisLicenceCertificate==null?'':value.bisLicenceCertificate.attachmentId==null?'':value.bisLicenceCertificate.attachmentId;
			var bisLicenceCertificateName=value.bisLicenceCertificate==null?'':value.bisLicenceCertificate.fileName==null?'':value.bisLicenceCertificate.fileName;
			var remark=value.remark==null?'':value.remark;
			var isApproved=value.isApproved==null?'':value.isApproved;
			var eeComment=value.eeComment==null?'':value.eeComment;
			var isEEApproved=value.isEEApproved==null?'':value.isEEApproved;
			var ceComment=value.ceComment==null?'':value.ceComment;
			var isCEApproved=value.isCEApproved==null?'':value.isCEApproved;
			
			
			leftPanelHtml = leftPanelHtml +' <a href="#Master" data-toggle="tab">'
	        +' <div class="col-md-12">'
	        +'  <label class="col-xs-6" id="labelBISLicenceNo-'+partnerOrgBisId+'">'+bisLicenceNo+'</label>'
	        +'	<label class="col-xs-6 " id="labelIssueDate-'+partnerOrgBisId+'">'+issueDate+'</label>'
	        +'	<label class="col-xs-6 " id="labelValidityDate-'+partnerOrgBisId+'">'+bisValidityDate+'</label>'
	        +' </div>'	
	        +' <div class="col-md-12" style="display: none">'
	        +'  <label class="col-xs-6" id="lblPartnerOrgBisId-'+partnerOrgBisId+'"> '+partnerOrgBisId+'</label>'
	        +'	<label class="col-xs-6 " id="lblPartnerOrgId-'+partnerOrgBisId+'" >'+partnerOrgId+'</label>'
	        +'	<label class="col-xs-6 " id="lblbisLicenceCertificateId-'+partnerOrgBisId+'" >'+bisLicenceCertificateId+'</label>'
	        +'	<label class="col-xs-6 " id="lblbisLicenceCertificateName-'+partnerOrgBisId+'" >'+bisLicenceCertificateName+'</label>'
	        +'	<label class="col-xs-6 " id="remark-'+partnerOrgBisId+'" >'+remark+'</label>'
	        +'	<label class="col-xs-6 " id="isApproved-'+partnerOrgBisId+'" >'+isApproved+'</label>'
	        /*  +'	<label class="col-xs-6 " id="lblBisLicenceCertificate-'+partnerOrgBisId+'" >'+bisLicenceCertificate+'</label>'*/
	        +'	<label class="col-xs-6" id="eeComment-'+partnerOrgBisId+'">'+eeComment+'</label>'
		    +'	<label class="col-xs-6" id="ceComment-'+partnerOrgBisId+'">'+ceComment+'</label>'
		    +'	<label class="col-xs-6" id="isEEApproved-'+partnerOrgBisId+'">'+isEEApproved+'</label>'
		    +'	<label class="col-xs-6" id="isCEApproved-'+partnerOrgBisId+'">'+isCEApproved+'</label>'
			+' </div>'
	        +' </a>'
	        +' </li>';
			return leftPanelHtml;
	}
	}
	return leftPanelHtml;
}
function showOrgBISDetail(id)
{
	var bisData=orgBISArray["orgBIS"+id];
	loadPartnerOrgBISRightPane(bisData);
}

function loadPartnerOrgBISRightPane(data){
	debugger
	editMode=false;
	
	setChildLoadFlag(true);
	/*activeTabName="";*/
	if(!$.isEmptyObject(data)){
	var bisLicenceNo = data.bisLicenceNo==null?'':data.bisLicenceNo;
	var issueDate  = data.validFrom==null?'':formatDate(data.validFrom);
	var bisValidityDate = data.validTo==null?'':formatDate(data.validTo);
	var partnerOrgBisId = data.partnerOrgBisId==null?'':data.partnerOrgBisId;
	var partnerOrgId  = data.partnerOrg==null?'':data.partnerOrg.partnerOrgId==null?'':data.partnerOrg.partnerOrgId;
	var bisLicenceCertificateId=data.bisLicenceCertificate==null?'':data.bisLicenceCertificate.attachmentId==null?'':data.bisLicenceCertificate.attachmentId;
	var bisLicenceCertificateName=data.bisLicenceCertificate==null?'':data.bisLicenceCertificate.fileName==null?'':data.bisLicenceCertificate.fileName;
	var remark=data.remark==null?'':data.remark;
	var isApproved=data.isApproved==null?'':data.isApproved;
	var eeComment=data.eeComment==null?'':data.eeComment;
	var isEEApproved=data.isEEApproved==null?'':data.isEEApproved;
	var ceComment=data.ceComment==null?'':data.ceComment;
	var isCEApproved=data.isCEApproved==null?'':data.isCEApproved;
	$("#partnerOrgBISForm #isNotApplicableBis").prop('checked',getCheckBoxVal(data.isNotApplicable));
	isNotApplicableBIS();
	$("#partnerOrgBISForm #partnerOrgId").val(partnerOrgId);	
	$("#partnerOrgBISForm #partnerOrgBISId").val(partnerOrgBisId);
	$("#partnerOrgBISForm #bisLicenceNo").val(bisLicenceNo);
	$("#partnerOrgBISForm #bisIssueDate").val(issueDate);
	$("#partnerOrgBISForm #bisValidDate").val(bisValidityDate);
	$("#partnerOrgBISForm #bisLicenceCertificate").val(bisLicenceCertificateId);
	
	changeOrgCommentAndStatusByRole('partnerOrgBISForm',isEEApproved,eeComment,isCEApproved,ceComment);
	var url=$("#a_registrationCopy").data('url');
	$("#a_bisLicenceCopy").attr('href',url);
	var a_bisLicenceCertificate = $("#partnerOrgBISForm #a_bisLicenceCopy").prop('href')+'/'+bisLicenceCertificateId;
    $("#partnerOrgBISForm #a_bisLicenceCopy").prop('href', a_bisLicenceCertificate);
	$("#partnerOrgBISForm #a_bisLicenceCopy").html(bisLicenceCertificateName);
	showBISFileDelBtn(bisLicenceCertificateId,'bisLicenceCertificate');
	}else{
		 $("#partnerOrgBISForm")[0].reset();
		 isNotApplicableBIS();
		 $("#a_bisLicenceCopy").html('');
		 $("#partnerOrgBISForm  #partnerOrgBISId").val("");
		 $("#partnerOrgBISForm .partnerOrgEEApproveDiv").hide();
	     $("#partnerOrgBISForm .partnerOrgCEApproveDiv").hide();
	}
	setHeaderValues("Factory Name: "+FactoryName, "Established Date : "+establishedDate, "License No.: "+licenseNo, "Location : "+locationName);
}


function bisAttachmentDeleteResp(data)
{
   if(!isEmpty(data)){
	if(!data.hasError)
    {		
       $('#bisFileId').val('');
	   $('#bisLicenceCertificate').val('');
	   $("#a_bisLicenceCopy").html('');
	   $('.bisLicenceCertificate').attr('disabled',true);
	   Alert.info(data.message);
	   var partnerOrgBISId=$("#partnerOrgBISId").val();
	   if(partnerOrgBISId!="")
		{
	     orgBISArray["orgBIS"+partnerOrgBISId].bisLicenceCertificate=null;
		}
    }else{
    	Alert.warn(data.message);
    } 
   }
}
function showBISFileDelBtn(fileId,fieldClass)
{
	
  if(fileId!='')
	  {
	    $("."+fieldClass).attr('disabled',false);
	  }else{
		  $("."+fieldClass).attr('disabled',true);
	  }
 }

function isNotApplicableBIS(){
	var ele=$("#partnerOrgBISForm #isNotApplicableBis");
	if($(ele).prop('checked')){
		$(".isNADiv").hide();
		$(".isNAChecked").attr("disabled","disabled");
		$(".isNADiv").find("input,select").val('');
		$("#partnerOrgBISForm #a_bisLicenceCopy").html('');
	}else{
		$(".isNADiv").show();
		$(".isNAChecked").removeAttr("disabled");
	}
}