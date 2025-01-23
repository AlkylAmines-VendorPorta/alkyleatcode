var orgProductArray=new Array();
$(document).ready(function(){
	$('#addMaterialBtnId').click(function(event) {
		event.preventDefault();
		$('#partnerOrgProductForm')[0].reset();
		$(".multiselectitem").empty();
		$("#uomId").val("");
		$("#partnerOrgProductId").val("");
		$(".multiselectitem").append("<option value=''></option>");
		$("#partnerOrgProductForm #regPartnerOrg").val("");
		$("#partnerOrgProductForm #partnerOrgBIS").val("");
		$('#industrialLicenceCopy').val("");
		$("#partnerOrgProductForm #a_industrialLicenceCopy").html('');
		$(".industrialLicenceCopy").attr('disabled',true);
		$("#partnerOrgProductForm .partnerOrgApproveDiv").hide();
    });
	$('#editMaterialBtnId').click(function(event) {
		event.preventDefault();
		
    });
	$('#deleteMaterialBtnId').click(function(event) {
		event.preventDefault();
		deleteMaterial();
    });
	$('#cancelBtnId').click(function(event) {
		event.preventDefault();
		editMode=false;
		/*activeTabName="";*/
		var activeProductId=$('.leftPaneData').find('li.active').attr('id');
		if(activeProductId!=undefined)
			{
				showProductDetail(activeProductId);
			}
		else
			$('#partnerOrgProductForm')[0].reset();
		
    });
	$("#partnerOrgProductForm").find("input,select,textarea").change(function() {
		 debugger;
	   	 editMode=true;
	   	 activeTabName="Factory Items Manufactured";
	});
	$("#partnerOrgProductForm .fileDeleteBtn").click(function() {
		 debugger;
	   	 editMode=true;
	   	 activeTabName="Factory Items Manufactured";
	   	 requiredFileDeleted=true;
	});
	$("#partnerOrgProductForm #productAddItemBtnId").click(function() {
		 debugger;
	   	 editMode=true;
	   	 activeTabName="Factory Items Manufactured";
	});
	
	
});
function getFactoryItemManufactured(event,el)
{
	event.preventDefault();	
	     
		if(!editMode && !requiredFileDeleted){
			cacheLi();
			setCurrentTab(el);
			$('#AdditemButton').removeData('callback');
			$('#AdditemButton').attr('data-callback','myselect');
			$("#srchItemForm #orgId").val("");
			$('#searchItemList').attr('onclick',"return submitIt('srchItemForm','getItemList','populateItemListWithSingleSelect');");
			
			if(getChangedFlag()){
		    	submitWithParam('getPartnerOrgProducts','partnerOrgId','onPartnerOrgProductTabLoad');	
		    	setChangedFlag(false);
			}else{
				getCacheLi();
			}
			setActiveTabName("Item Manufactured",$('.leftPaneData li').length);	
			setHeaderValues("Factory Name: "+FactoryName, "Established Date : "+establishedDate, "License No.: "+licenseNo, "Location : "+locationName);
		}else{
			event.stopPropagation();
	        Alert.warn("Please Save Changed Data Of "+activeTabName+" Tab");
		}
		$("#filterBtnId").addClass("readonly");
	    checkForFilterByRole();
}
function partnerOrgProductResp(data){
	
	$('.pagination').children().remove();
	setChildLoadFlag(true);
	if(data.response.hasError==false)
	{
		if ($("#partnerData").val() == "partnerRegistration") {
			$("#partnerOrgProductForm  .partnerOrgEEApproveDiv").hide();
			$("#partnerOrgProductForm  .partnerOrgCEApproveDiv").hide();
		}
		showSubmitFormOnOrgChanges();
		editMode=false;
		activeTabName="";
		requiredFileDeleted=false;
		populateNewMaterial(data);
		setActiveTabName("Item Manufactured",$('.leftPaneData li').length);
		Alert.info(data.response.message,'','success');
		setHeaderValues("Factory Name: "+FactoryName, "Established Date : "+establishedDate, "License No.: "+licenseNo, "Location : "+locationName);
	}
else
	{
	      if (!$.isEmptyObject(data.response.errors)) {
			var msg = '';
			$.each(data.response.errors, function(key, value) {
				msg = msg + '\n' + value.errorMessage + '\n';

			});
			Alert.warn(msg);
		   } else {
			   Alert.warn(data.response.message,'','error');
		   }
	      
	}

	$('.leftPaneData').paginathing();
}
function myselect(values)
{
		$(".multiselectitem").html("");
		var options='';
		$.each(values.map,function(index,val){
			options +='<option value="'+index+'">'+val+'</option>'
			
		});
		$(".multiselectitem").append(options);
		$(".multiselectitem").removeClass('errorinput');
		 $('#partnerOrgProductForm #uom').val(values.uom);
		 $("#uom").removeClass('errorinput');
		 $('#partnerOrgProductForm #itemName').val(values.itemName);
		
} 

function onPartnerOrgTabClick(){
	submitToURL("getPartner", 'onPartnerOrgProductTabLoad');	
}

function onPartnerOrgProductTabLoad(data){
	debugger;
	$('#AdditemButton').removeData('callback');
	$('#AdditemButton').attr('data-callback','myselect');
	$("#srchItemForm #orgId").val("");
	$('#searchItemList').attr('onclick',"return submitIt('srchItemForm','getItemList','populateItemListWithSingleSelect');");
		if (data.objectMap.hasOwnProperty('registrationTypes')) {
			loadReferenceListById('regType',data.objectMap.registrationTypes);
		}
		if(data.objectMap.hasOwnProperty('uom')){
			loadProductUom(data.objectMap.uom);
	    }
		if (data.objectMap.hasOwnProperty('orgBIS')) {
			loadPartnerOrgBIS(data.objectMap.orgBIS);
		}
		if (data.objectMap.hasOwnProperty('partnerOrgRegistrations')) {
			debugger;
			if($.isEmptyObject(data.objectMap.partnerOrgRegistrations))
				{
				   $(".showfactoryRegField").hide();
				}else{
				   $(".showfactoryRegField").show();
				}
		}
		if(data.objectMap.hasOwnProperty('partnerOrgProducts')){
			if(!$.isEmptyObject(data.objectMap.partnerOrgProducts))
				loadPartnerOrgProductLeftPane(data.objectMap.partnerOrgProducts);
			else
			{
				$(".leftPaneData").html("");
				$('#partnerOrgProductForm')[0].reset();
				$(".multiselectitem").empty();
				$("#uomId").val("");
				$("#partnerOrgProductId").val("");
				$(".multiselectitem").append("<option value=''></option>");
				$("#partnerOrgProductForm #regPartnerOrg").val("");
				$("#partnerOrgProductForm #partnerOrgBIS").val("");
				$('#industrialLicenceCopy').val("");
				$("#partnerOrgProductForm #a_industrialLicenceCopy").html('');
				$(".industrialLicenceCopy").attr('disabled',true);
				$("#partnerOrgProductForm .partnerOrgApproveDiv").hide();
			}
			
	  }
		setActiveTabName("Item Manufactured",$('.leftPaneData li').length);
		
	
	
}

function loadRegFactory(data)
{
	 
	var regPartnerOrg=data.objectMap.partnerOrgRegistrations;
	$("#partnerOrgProductForm #regPartnerOrg").html("");
	var options = "<option value=''>Select Factory Reg No</option>";
	$.each(regPartnerOrg,function(key,value){
	options +='<option value="'+value.partnerOrgRegistrationId+'">'+value.registrationNo +'</option>'
	});
	$("#partnerOrgProductForm #regPartnerOrg").append(options);
	
}



function loadPartnerOrgProductLeftPane(data){
 	 
	$('.pagination').children().remove();
	$(".leftPaneData").html("");
	var leftPanelHtml="";
	var i=0;
	var firstRow=null;
	$.each(data,function(key,value){
    debugger;		 
		var itemName = value.material==null?'':value.material.materialId==null?'':value.material.materialId;
		var item =value.material==null?'':value.material.materialId==null?'':value.material.name;
		var uom  = value.uom==null?'':value.uom.uomId==null?'':value.uom.uomId;
		var uomName  = value.uom==null?'':value.uom.name==null?'':value.uom.name;
		var quantityManufacteredPerMonth  = value.qtyManufacturedPM==0?0:value.qtyManufacturedPM;
		var turnOver=value.turnOver==null?'':value.turnOver;
		var licenceNo  = value.licenceNo==null?'':value.licenceNo;
		var partnerOrgProductId  = value.partnerOrgProductId==null?'':value.partnerOrgProductId;
		var regOrg=value.partnerOrgRegistration==null?'':value.partnerOrgRegistration.partnerOrgRegistrationId==null?'':value.partnerOrgRegistration.partnerOrgRegistrationId;
		var regOrgNo=value.partnerOrgRegistration==null?'':value.partnerOrgRegistration.registrationNo==null?'':value.partnerOrgRegistration.registrationNo;
		var regTYpe=value.registrationType==null?'':value.registrationType;
		var orgBISId=value.partnerOrgBIS==null?'':value.partnerOrgBIS.partnerOrgBisId==null?'':value.partnerOrgBIS.partnerOrgBisId;
		var industrialLicenceCopyId=value.industrialLicenceCopy==null?'':value.industrialLicenceCopy.attachmentId==null?'':value.industrialLicenceCopy.attachmentId;
		var industrialLicenceCopyName=value.industrialLicenceCopy==null?'':value.industrialLicenceCopy.fileName==null?'':value.industrialLicenceCopy.fileName;
		var remark=value.remark==null?'':value.remark;
		var isApproved=value.isApproved==null?'':value.isApproved;
		var eeComment=value.eeComment==null?'':value.eeComment;
		var isEEApproved=value.isEEApproved==null?'':value.isEEApproved;
		var ceComment=value.ceComment==null?'':value.ceComment;
		var isCEApproved=value.isCEApproved==null?'':value.isCEApproved;
		
		orgProductArray["orgProduct"+partnerOrgProductId]=value;
		if(i==0){
			firstRow = value;
			leftPanelHtml = leftPanelHtml + ' <li class="active" onclick="showProductDetail('+partnerOrgProductId+')" id="'+partnerOrgProductId+'">';
		}else{
			leftPanelHtml = leftPanelHtml + ' <li onclick="showProductDetail('+partnerOrgProductId+')" id="'+partnerOrgProductId+'">';
		}

		leftPanelHtml = leftPanelHtml +' <a href="#Master" data-toggle="tab">'
		 +' <div class="col-md-12">'
	        +'  <label class="col-xs-6" id="product_'+partnerOrgProductId+'_itemName">'+item.substr(0, 20)+'</label>'
	        +'	<label class="col-xs-6" id="product_'+partnerOrgProductId+'_uomName">'+uomName+'</label>'
	        +' </div>'	
	        +' <div class="col-md-12">'
	        +'	<label class="col-xs-6" id="product_'+partnerOrgProductId+'_qtypm">'+quantityManufacteredPerMonth+'</label>'
			+'	<label class="col-xs-6" id="product_'+partnerOrgProductId+'_turnover">'+turnOver+'</label>'
			+' </div>'
			+' </a>'
			+' <div class="col-md-12" style="display: none">'
		    +'	<label class="col-xs-6" id="product_'+partnerOrgProductId+'_orgProductId">'+partnerOrgProductId+'</label>'
		    +'	<label class="col-xs-6" id="product_'+partnerOrgProductId+'_uomId">'+uom+'</label>'
		    +'	<label class="col-xs-6" id="product_'+partnerOrgProductId+'_itemId">'+itemName+'</label>'
		    +'	<label class="col-xs-6" id="product_'+partnerOrgProductId+'_licenceno">'+licenceNo+'</label>'
		    +'	<label class="col-xs-6" id="product_'+partnerOrgProductId+'_regType">'+regTYpe+'</label>'
		    +'	<label class="col-xs-6" id="product_'+partnerOrgProductId+'_regOrg">'+regOrg+'</label>'
		    +'	<label class="col-xs-6" id="product_'+partnerOrgProductId+'_regOrgNo">'+regOrgNo+'</label>'
		    +'	<label class="col-xs-6" id="product_'+partnerOrgProductId+'_orgBIS">'+orgBISId+'</label>'
		    +'	<label class="col-xs-6" id="product_'+partnerOrgProductId+'_industrialLicenceCopyId">'+industrialLicenceCopyId+'</label>'
		    +'	<label class="col-xs-6" id="product_'+partnerOrgProductId+'_industrialLicenceCopyName">'+industrialLicenceCopyName+'</label>'
		    +'	<label class="col-xs-6" id="remark_'+partnerOrgProductId+'">'+remark+'</label>'
		    +'	<label class="col-xs-6" id="isApproved_'+partnerOrgProductId+'">'+isApproved+'</label>'
		    +'	<label class="col-xs-6" id="eeComment-'+partnerOrgProductId+'">'+eeComment+'</label>'
		    +'	<label class="col-xs-6" id="ceComment-'+partnerOrgProductId+'">'+ceComment+'</label>'
		    +'	<label class="col-xs-6" id="isEEApproved-'+partnerOrgProductId+'">'+isEEApproved+'</label>'
		    +'	<label class="col-xs-6" id="isCEApproved-'+partnerOrgProductId+'">'+isCEApproved+'</label>'
			+' </div>'
	        +' </a>'
	        +' </li>';
		i++;
	});
	$(".leftPaneData").html(leftPanelHtml);
	loadPartnerOrgProductRightPane(firstRow);
	/*$('.example').paginathing();*/
	$('.leftPaneData').paginathing();
	
	$(".tabs-left li a label").text(function(index, currentText) {
	    return currentText.substr(0, 20);
	});
	
}

function loadPartnerOrgProductRightPane(data){
	debugger;
	editMode=false;
	
	setChildLoadFlag(true);
	/*activeTabName="";*/
	if(data==null){
		$("#partnerOrgProductForm #partnerOrgProductId").val("");
		$('#partnerOrgProductForm')[0].reset();
		$(".multiselectitem").empty();
		$("#uomId").val("");
		$("#partnerOrgProductId").val("");
		$(".multiselectitem").append("<option value=''></option>");
		$("#partnerOrgProductForm #regPartnerOrg").val("");
		$("#partnerOrgProductForm #partnerOrgBIS").val("");
		$('#industrialLicenceCopy').val("");
		$("#partnerOrgProductForm #a_industrialLicenceCopy").html('');
		$(".industrialLicenceCopy").attr('disabled',true);
		$("#partnerOrgProductForm .partnerOrgEEApproveDiv").hide();
	    $("#partnerOrgProductForm .partnerOrgCEApproveDiv").hide();
		return;
	}
	
	var itemName = data.material==null?'':data.material.materialId==null?'':data.material.materialId;
	var item = data.material==null?'':data.material.name==null?'':data.material.name;
	var uom  = data.uom==null?'':data.uom.uomId==null?'':data.uom.uomId;
	var uomName  = data.uom==null?'':data.uom.name==null?'':data.uom.name;
	var quantityManufacteredPerMonth  = data.qtyManufacturedPM==0?0:data.qtyManufacturedPM;
	var turnOver=data.turnOver==null?'':data.turnOver;
	var licenceNo  = data.licenceNo==null?'':data.licenceNo;
	var partnerOrgProductId  = data.partnerOrgProductId==null?'':data.partnerOrgProductId;
	var regOrgId=data.partnerOrgRegistration==null?'':data.partnerOrgRegistration.partnerOrgRegistrationId==null?'':data.partnerOrgRegistration.partnerOrgRegistrationId;
	var regType=data.registrationType==null?'':data.registrationType;
	var orgBISId=data.partnerOrgBIS==null?'':data.partnerOrgBIS.partnerOrgBisId==null?'':data.partnerOrgBIS.partnerOrgBisId;
	var orgRegNo=data.partnerOrgRegistration==null?'':data.partnerOrgRegistration.registrationNo==null?'':data.partnerOrgRegistration.registrationNo;
	var industrialLicenceCopyId=data.industrialLicenceCopy==null?'':data.industrialLicenceCopy.attachmentId==null?'':data.industrialLicenceCopy.attachmentId;
	var industrialLicenceCopyName=data.industrialLicenceCopy==null?'':data.industrialLicenceCopy.fileName==null?'':data.industrialLicenceCopy.fileName;
	var remark=data.remark==null?'':data.remark;
	var isApproved=data.isApproved==null?'':data.isApproved;
	var eeComment=data.eeComment==null?'':data.eeComment;
	var isEEApproved=data.isEEApproved==null?'':data.isEEApproved;
	var ceComment=data.ceComment==null?'':data.ceComment;
	var isCEApproved=data.isCEApproved==null?'':data.isCEApproved;
	
	$("#partnerOrgProductForm #partnerOrgProductId").val(partnerOrgProductId);
	$("#partnerOrgProductForm #itemName").val(item);
	$("#partnerOrgProductForm #uom").val(uom);
	$("#uom").removeClass('errorinput');
	$("#partnerOrgProductForm #quantityManufacturedPerMonth").val(quantityManufacteredPerMonth);
	$("#partnerOrgProductForm #turnOver").val(turnOver);
	$("#partnerOrgProductForm #licenceNo").val(licenceNo);
	$(".multiselectitem").empty();
	$(".multiselectitem").append('<option value="'+itemName+'">'+item+'</option>');
	$(".multiselectitem").removeClass('errorinput');
	$("#partnerOrgProductForm .multiselectitem").val(itemName);
	$("#partnerOrgProductForm #regType").val(regType);
	$("#partnerOrgProductForm #regPartnerOrg").empty();
	$("#partnerOrgProductForm #regPartnerOrg").append('<option value="'+regOrgId+'">'+orgRegNo+'</option>');
	$("#partnerOrgProductForm #partnerOrgBIS").val(orgBISId);
	$("#partnerOrgProductForm #industrialLicenceCopy").val(industrialLicenceCopyId);
	$("#partnerOrgProductForm .dropDown").removeClass('errorinput');
	$("#partnerOrgProductForm .requiredFile").removeClass('errorinput');
	changeProductCommentAndStatusByRole('partnerOrgProductForm',isEEApproved,eeComment,isCEApproved,ceComment);
	
	var url=$("#a_industrialLicenceCopy").data('url');
	$("#a_industrialLicenceCopy").attr('href',url);
	var a_industrialLicenceCopy = $("#partnerOrgProductForm #a_industrialLicenceCopy").prop('href')+'/'+industrialLicenceCopyId;
    $("#partnerOrgProductForm #a_industrialLicenceCopy").prop('href', a_industrialLicenceCopy);
	$("#partnerOrgProductForm #a_industrialLicenceCopy").html(industrialLicenceCopyName);
	showProductFileDelBtn(industrialLicenceCopyId,'industrialLicenceCopy');
	setHeaderValues("Factory Name: "+FactoryName, "Established Date : "+establishedDate, "License No.: "+licenseNo, "Location : "+locationName);
	
}
function showProductDetail(id)
{
	debugger;
	var productData=orgProductArray["orgProduct"+id];
	loadPartnerOrgProductRightPane(productData);
		
}
function populateNewMaterial(data)
{
	debugger;
	var currentProductId=$('.leftPaneData').find('li.active').attr('id');
	var productId=data.partnerOrgProductId;
	if(currentProductId==productId)
	{
		$('#'+currentProductId).remove();
	}
	else
		$('#'+currentProductId).removeClass('active');
	

	$('#partnerOrgProductId').val(productId);
	if(data.industrialLicenceCopy!=null)
	{
	  data.industrialLicenceCopy.fileName=$("#partnerOrgProductForm #a_industrialLicenceCopy").text();
	}
	if(data.partnerOrgRegistration!=null)
		{
		   data.partnerOrgRegistration.registrationNo=$("#partnerOrgProductForm #regPartnerOrg option:selected").text();
		}
	orgProductArray["orgProduct"+productId]=data;

	var active=' class="active"';
	$('.leftPaneData').prepend( '<li '+active+' onclick="showProductDetail('+productId+')" id="'+productId+'">'
	+' <a href="#Master" data-toggle="tab">'
    +' <div class="col-md-12">'
    +'  <label class="col-xs-6" id="product_'+productId+'_itemName">'+$("#partnerOrgProductForm #itemName").val().substr(0, 20)+'</label>'
    +'	<label class="col-xs-6" id="product_'+productId+'_uomName">'+$('#partnerOrgProductForm #uom').find('option:selected').text()+'</label>'
    +' </div>'	
    +' <div class="col-md-12">'
    +'	<label class="col-xs-6" id="product_'+productId+'_qtypm">'+$("#partnerOrgProductForm #quantityManufacturedPerMonth").val()+'</label>'
	+'	<label class="col-xs-6" id="product_'+productId+'_turnover">'+$("#partnerOrgProductForm #turnOver").val()+'</label>'
	+' </div>'
    +' </a>'
    +' <div class="col-md-12" style="display: none">'
    +'	<label class="col-xs-6" id="product_'+productId+'_orgProductId">'+$("#partnerOrgProductForm #partnerOrgProductId").val()+'</label>'
    +'	<label class="col-xs-6" id="product_'+productId+'_uomId">'+$('#partnerOrgProductForm #uom').val()+'</label>'
    +'	<label class="col-xs-6" id="product_'+productId+'_itemId">'+$("#partnerOrgProductForm .multiselectitem").val()+'</label>'
    +'	<label class="col-xs-6" id="product_'+productId+'_licenceno">'+$("#partnerOrgProductForm #licenceNo").val()+'</label>'
    +'	<label class="col-xs-6" id="product_'+productId+'_regType">'+$("#partnerOrgProductForm #regType").val()+'</label>'
    +'	<label class="col-xs-6" id="product_'+productId+'_regOrg">'+$("#partnerOrgProductForm #regPartnerOrg").val()+'</label>'
    +'	<label class="col-xs-6" id="product_'+productId+'_orgBIS">'+$("#partnerOrgProductForm #partnerOrgBIS").val()+'</label>'
    +'	<label class="col-xs-6" id="product_'+productId+'_regOrgNo">'+$("#partnerOrgProductForm #regPartnerOrg").find('option:selected').text()+'</label>'
    +'	<label class="col-xs-6" id="product_'+productId+'_industrialLicenceCopyId">'+$("#partnerOrgProductForm #industrialLicenceCopy").val()+'</label>'
    +'	<label class="col-xs-6" id="remark_'+productId+'">'+$("#partnerOrgProductForm #remark").val()+'</label>'
    +'	<label class="col-xs-6" id="isApproved_'+productId+'">'+$("#partnerOrgProductForm #isApproved").val()+'</label>'
    +' </div>'
    +' </li>');
	
	$('.example').paginathing();
	
	$(".tabs-left li a label").text(function(index, currentText) {
	    return currentText.substr(0, 20);
	});
}
function deleteMaterial()
{
	input_box = confirm("Do you really want to delete this Material?");
	 if (input_box == true) {
		submitWithParam('deletePartnerOrgProduct','partnerOrgProductId','deleteProductResponse');
			
	 }
	 
}
function deleteProductResponse(data)
{
	$('.pagination').children().remove();
	var currentProductId=$('.leftPaneData').find('li.active').attr('id');
	if(data.hasError==false)
	{
		$('#'+currentProductId).remove();
		$('#partnerOrgProductForm')[0].reset();
		$("#partnerOrgProductId").val("");
		$("#partnerOrgProductForm #a_industrialLicenceCopy").html('');
		$(".multiselectitem").empty();
		$(".multiselectitem").append('<option value="">Select Item</option>');
		Alert.info(data.message,'','success');
		showSubmitFormOnOrgChanges();
		$('.leftPaneData').paginathing();
		
		$(".tabs-left li a label").text(function(index, currentText) {
		    return currentText.substr(0, 20);
		});
	}
else
	{
	  Alert.warn(data.responseMsg,"","error");
	}
	$('.leftPaneData').paginathing();
}
function loadProductUom(data){
		$("#partnerOrgProductForm #uom").html('');
		var options = "<option value=''>Select Uom </option>";
		$.each(data,function(key,value){
			options +='<option value="'+value.uomId+'">'+value.name +'</option>'
		});
		
		$("#partnerOrgProductForm #uom").append(options);
}
function loadPartnerOrgBIS(data)
{
	$("#partnerOrgProductForm #partnerOrgBIS").html('');
	var options = "<option value=''>Select BIS </option>";
	$.each(data,function(key,value){
		options +='<option value="'+value.partnerOrgBisId+'">'+value.bisLicenceNo +'</option>'
	});
	
	$("#partnerOrgProductForm #partnerOrgBIS").append(options);
}
function productAttachmentDeleteResp(data)
{
	if(!data.hasError)
    {		
       $('#industrialFileId').val('');
	   $('#industrialLicenceCopy').val('');
	   $("#a_industrialLicenceCopy").html('');
	   $('.industrialLicenceCopy').attr('disabled',true);
	   Alert.info(data.message);
	   var partnerOrgProductId=$("#partnerOrgProductId").val();
	   if(partnerOrgProductId!="")
		{
	      orgProductArray["orgProduct"+partnerOrgProductId].industrialLicenceCopy=null;
		}
    }else{
    	Alert.warn(data.message);
    }
}

function showProductFileDelBtn(fileId,fieldClass)
{
	debugger;
  if(fileId!='')
	  {
	    $("."+fieldClass).attr('disabled',false);
	  }else{
		  $("."+fieldClass).attr('disabled',true);
	  }
 }
function changeProductCommentAndStatusByRole(formId,isEEApproved,eeComment,isCEApproved,ceComment)
{
	debugger;
	 debugger;
	/* $("#"+formId+" .partnerOrgEEApproveDiv").find('input:radio, textarea').removeAttr('disabled','disabled');
	 $("#"+formId+" .partnerOrgCEApproveDiv").find('input:radio, textarea').removeAttr('disabled','disabled');
	*/
	    $("#"+formId+" .partnerOrgEEApproveDiv").find('input:radio, textarea').removeClass('readonly');
		$("#"+formId+" .partnerOrgCEApproveDiv").find('input:radio, textarea').removeClass('readonly');
		$("#"+formId+" .partnerOrgEEApproveDiv").find('input:radio, textarea').css("background-color","#FFF");
		$("#"+formId+" .partnerOrgCEApproveDiv").find('input:radio, textarea').css("background-color","#FFF");

	 $("#"+formId+" .partnerOrgEEApproveDiv").hide();
	 $("#"+formId+" .partnerOrgCEApproveDiv").hide();
	 
	 var role=$("#roleData").val();
	 var partnerData =  $("#partnerData").val();
	 if(role=='EXEENGR')
	 {
		$("#"+formId+" .partnerOrgEEApproveDiv").show();
	    $("#"+formId+" #eeRemark").val(eeComment);
	    /*$("#"+formId+" .partnerOrgCEApproveDiv").find('input:radio, textarea').addClass('readonly');
	    $("#"+formId+" .partnerOrgCEApproveDiv").find('input:radio, textarea').css("background-color","#DADCE2");
	  */
	    $("#"+formId+" .partnerOrgCEApproveDiv").find('input:radio, textarea').attr('disabled','disabled');
	    setProductApprovedStatus(formId,isEEApproved,'eeApproveBtn','eeExempted','eeClarification');
	    if(isCEApproved=='C' || isCEApproved=='Y')
	    	 {
	    	     $("#"+formId+" .partnerOrgCEApproveDiv").show();
	    	     $("#"+formId+" .partnerOrgCEApproveDiv").find('input:radio, textarea').removeAttr('disabled','disabled');
	    	     $("#"+formId+" .partnerOrgCEApproveDiv").find('input:radio, textarea').addClass('readonly');
	    		 $("#"+formId+" .partnerOrgCEApproveDiv").find('input:radio, textarea').css("background-color","#DADCE2");
	    		  
	    	     $("#"+formId+" #ceRemark").val(ceComment);
		    	 setProductApprovedStatus(formId,isCEApproved,'ceApproveBtn','ceExempted','ceClarification');
		     }
	     
	 }else if(role=='CHFENGR'){
		 $("#"+formId+" .partnerOrgCEApproveDiv").find('input:radio, textarea').removeAttr('disabled','disabled');
		 $("#"+formId+" .partnerOrgCEApproveDiv").show();
		 $("#"+formId+" #ceRemark").val(ceComment);
		 setProductApprovedStatus(formId,isCEApproved,'ceApproveBtn','ceExempted','ceClarification');
		 $("#"+formId+" .partnerOrgEEApproveDiv").show();
		 /*$("#"+formId+" .partnerOrgEEApproveDiv").find('input:radio, textarea').attr('disabled','disabled');*/
		 $("#"+formId+" .partnerOrgEEApproveDiv").find('input:radio, textarea').addClass('readonly');
		 $("#"+formId+" .partnerOrgEEApproveDiv").find('input:radio, textarea').css("background-color","#DADCE2");
		 $("#"+formId+" #eeRemark").val(eeComment);
		 setProductApprovedStatus(formId,isEEApproved,'eeApproveBtn','eeExempted','eeClarification');
		 
	 }else if(partnerData=="partnerRegistration"){
		 /*$("#"+formId+" .partnerOrgEEApproveDiv").find('input:radio, textarea').attr('disabled','disabled');
		 $("#"+formId+" .partnerOrgCEApproveDiv").find('input:radio, textarea').attr('disabled','disabled');
		*/ 
		   $("#"+formId+" .partnerOrgEEApproveDiv").find('input:radio, textarea').addClass('readonly');
		   $("#"+formId+" .partnerOrgCEApproveDiv").find('input:radio, textarea').addClass('readonly');
		   $("#"+formId+" .partnerOrgEEApproveDiv").find('input:radio, textarea').css("background-color","#DADCE2");
		   $("#"+formId+" .partnerOrgCEApproveDiv").find('input:radio, textarea').css("background-color","#DADCE2");
		   $("#"+formId+" .partnerOrgCEApproveDiv").hide();
		    if(isCEApproved!="")
		    {
		    	if(isCEApproved=="EXEM"){
		    	$("#"+formId+" .partnerOrgCEApproveDiv").show();
		    	setProductApprovedStatus(formId,isCEApproved,'ceApproveBtn','ceExempted','ceClarification');
		    	}
		    }
		    if(isEEApproved!=""){
		    	if(isEEApproved=="C"){
		    	 $("#"+formId+" .partnerOrgEEApproveDiv").show();
		    	 setProductApprovedStatus(formId,isEEApproved,'eeApproveBtn','eeExempted','eeClarification');
		    	}
		    }
		    $("#"+formId+" .remark").addClass('readonly');
	        $("#"+formId+" .statusBtn").addClass('readonly');
		    $("#"+formId+" #eeRemark").val(eeComment);
		    $("#"+formId+" #ceRemark").val(ceComment);
		 
	 }
	
}
function setProductApprovedStatus(formId,isApproved,approveBtnId,exemptedBtnId,clarifyBtnId)
{
	debugger;
	if(isApproved=="Y" || isApproved=="")
		{
		  $("#"+formId+" #"+approveBtnId).prop('checked',true);
		  $("#"+formId+" #"+clarifyBtnId).prop('checked',false);
		  $("#"+formId+" #"+exemptedBtnId).prop('checked',false);
		}else if(isApproved=="C"){
		    $("#"+formId+" #"+clarifyBtnId).prop('checked', true);
		    $("#"+formId+" #"+approveBtnId).prop('checked',false);
		    $("#"+formId+" #"+exemptedBtnId).prop('checked',false);
		}else if(isApproved=="EXEM"){
		    $("#"+formId+" #"+clarifyBtnId).prop('checked', false);
		    $("#"+formId+" #"+approveBtnId).prop('checked',false);
		    $("#"+formId+" #"+exemptedBtnId).prop('checked',true);
		}
}