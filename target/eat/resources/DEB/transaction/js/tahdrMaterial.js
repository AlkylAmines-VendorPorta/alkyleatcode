var tahdrMaterialArray= new Array();
var versionArray=new Array();
var isTahdrMaterialLiChanged=true;
var tahdrMaterialWithMaterialMap=new Array();
var materialName='';
var materialDesc='';
var qty='';
var uom=''; 
$(document).ready(function(){
	
	$("#getTahdrMaterialList").on("click", function(event){
		$("#tahdrMaterialDiv").css("display","block");
		$("#tahdrMaterialParts").css("display","none");
		$("#tahdrMaterialGtp").css("display","none");
		$("#leftPane").removeClass('readonly');
		if($(".dataUrlTypeCode").val()=='getTAHDRByTypeCode' && $(".dataUrl").val()=='tenderPreparationData'){
			$(".addTahdrMaterial").show();
			$(".deleteTahdrMaterial").show();
		}
		else if($(".dataUrlTypeCode").val()=='getTAHDRApprovalByTypeCode' && $(".dataUrl").val()=='tenderApprovalData'){
			$(".addTahdrMaterial").hide();
			$(".deleteTahdrMaterial").hide();
		}
		cacheLi();
		setCurrentTab(this);
		getTahdrMaterial();
		var documentType=$(".documentType").val();
		setHeaderValues(documentType+"No.: "+tahdrCodes, documentType+"Title : "+title, "Department : "+department, "Status : "+tenderStatus);
	});
	
	$(".addTahdrMaterial").on("click",function(event){
		event.preventDefault();
		$("#saveTahdrMaterialForm")[0].reset();
		/*$("input[name='materialTypeCode'][value='single']").attr('checked', true);*/
		$("input[name='aboutSpecCode'][value='inc']").attr('checked', true);
		$("#selectedMaterialId").empty();
		$("#selectedMaterialId").val("");
		$(".MaterialForVersion").hide();
		$('#leftPane').paginathing({perPage: 10});
		$('#saveTahdrMaterialForm .saveTahdrMaterial').show();
		$('#saveTahdrMaterialForm .cancelTahdrMaterial').show();
		if($(".Single").attr("checked") == "checked"){
			$('.viewPartsBtn').hide();
			$('.BOMVersion').hide();
			$("#BOMVersionId").html('');
			$("#BOMVersionId").removeClass('dropDown');
		}	
		else{
			$('.viewPartsBtn').hide();
			$('.BOMVersion').show();
			$("#BOMVersionId").addClass('dropDown');
			loadBomVersion();
		}
	});
	
	$(".editTahdrMaterial").on("click",function(event){
		
		event.preventDefault();
		$("#tahdrMaterialList").css("display","none");
		$("#tahdrMaterialDiv").css("display","block");
		tableRow=$("input[name='tahdrMaterialRadio']:checked").parents("tr:first");
		var id= $("input[name='tahdrMaterialRadio']:checked").val();
		if(id!=''){
			populateTahdrMaterialDetail(fetchList("getTAHDRMaterialById",id));
		}
	});
	
$(".createTahdrMaterialParts").on("click",function(event){
		event.preventDefault();
	});
	
	$(".deleteTahdrMaterial").on("click",function(event){
		event.preventDefault();
		var id= $(".tahdrMaterialId").val();
		if(id!=''){
			tahdrMaterialDelResp(fetchList("deleteTahdrMaterial",id));
			setChildLoadFlag(true);
		}
	});
	
	$(".saveTahdrMaterial").on("click", function(event){
		   event.preventDefault();
		   if($.isEmptyObject($("#selectedMaterialVersionId").val())){
			   if($("#tahdrType").val()=='PT'){
					 $("#selectedMaterialVersionId").removeAttr("disabled");
				}
			   else{
				   $("#selectedMaterialVersionId").attr("disabled","disabled");
			   }
			   /*$("#selectedMaterialVersionId").attr("disabled","disabled");*/
		   }
		   submitIt("saveTahdrMaterialForm","saveTahdrMaterial","saveTahdrMaterialResp")
		   $("#selectedMaterialVersionId").removeAttr("disabled");
	});
	
	$(".cancelTahdrMaterial").on("click", function(event){
		event.preventDefault();/*
		 if($(".Single").prop('checked')==true){
				$('.viewPartsBtn').hide();
				$('.BOMVersion').hide();
			}
			else{
				$('.viewPartsBtn').hide();
				$('.BOMVersion').show();
			}*/
		var activeMaterialId=$('.leftPaneData').find('li.active').attr('id');
		if(activeMaterialId!=undefined){
			loadTahdrMaterialToRightPane(tahdrMaterialArray["tahdrMaterial"+activeMaterialId]);
		}else
			$('#saveTahdrMaterialForm')[0].reset();
		
	});
	
	
	$("input[name='aboutSpecCode']").on("change", function(){
		var val=$(this).val();
		var materialId=$("#selectedMaterialId").val();
		if(val=="ref"){
			referSpec();
		}else if(val=="inc"){
			includeSpec(materialId);
		}
	});
	
	/*$("#getMaterialForVersion").on("change", function(){
		debugger;
		var materialId=$(this).val();
		var list =[];
		var versionList=fetchList("getMaterialVersionByMaterialId",materialId);
		populateMaterialVersion(versionList);
	});*/
	
	$("#getMaterialForVersion").on("change", function(){
		var materialId=$(this).val();
		var list =[];
		list.push(tahdrMaterialArray["tahdrMaterial"+materialId].materialVersion);
		populateMaterialVersion(list);
	});
	
});

function getTahdrMaterial(){
	if(getChangedFlag()){
		$('.pagination').children().remove();
		var typeCode='';
		if($("#tahdrType").val()=='WT'){
			$('.tahdrDetailTypeCode').val('');
			$('.tahdrDetailTypeCode').val('S');
		}
		else{
			$('.tahdrDetailTypeCode').val('');
			$('.tahdrDetailTypeCode').val('I');
		}
		$('#AdditemButton').removeData('callback');
		$('#AdditemButton').attr('data-callback','itemListDropDown');
		$('#searchItemList').attr('onclick',"return submitIt('srchItemForm','getItemList','populateItemListWithSingleSelect');");
		/*var materialId=$("#selectedMaterialId").val();
		includeSpec(materialId);*/
		var id=$(".tahdrDetailId").val();
		if(id!=''){
			loadAllTabsForMaterial(fetchList("getTAHDRMaterialList",id));
		}
		else{
			$("#saveTahdrMaterialForm")[0].reset();
		}
		
		
		/*if (status == 'Drafted'){
			$('#tenderMaterialForm').removeClass('readonly');
			$('#saveTahdrMaterialForm .saveTahdrMaterial').show();
			$('#saveTahdrMaterialForm .cancelTahdrMaterial').show();
			$('#tenderMaterialButtonsId').removeClass('readonly');
		}
		else{
			$('#tenderMaterialForm').addClass('readonly');
			$('#tenderMaterialButtonsId').addClass('readonly');
			$('#saveTahdrMaterialForm .saveTahdrMaterial').hide();
			$('#saveTahdrMaterialForm .cancelTahdrMaterial').hide();
		}*/
		
		/*$('#leftPane').paginathing({perPage: 10});*/
	 $(".tabs-left li a label").text(function(index, currentText) {
		    return currentText.substr(0, 20);
		});	
	    
	    /*plugin.init();*/
	 setChangedFlag(false);
	}else{
		getCacheLi();
	}
	setLeftPaneHeader(" Materials", null);
}

function populateMaterialForVersion(materialList){
	$("#getMaterialForVersion").empty();
	$("#getMaterialForVersion").append("<option >Select Material</option>");
	$.each(Object.values(materialList),function(idx,tahdrMaterial){
		if(tahdrMaterial.material.materialId!=$('#selectedMaterialId').val()){
			$("#getMaterialForVersion").append("<option value='"+tahdrMaterial.tahdrMaterialId+"'>"+tahdrMaterial.material.name+"</option>");
		}
	});
}

function populateMaterialVersion(versionList){
	$("#selectedMaterialVersionId").empty();
	$("#selectedMaterialVersionId").append("<option></option>");
	versionArray=[];
	$.each(versionList,function(idx,version){
		versionArray["version_"+version.materialVersionId]=version;
		$("#selectedMaterialVersionId").append("<option value='"+version.materialVersionId+"'>"+version.name+"</option>");
	});
}

function tahdrMaterialDelResp(data){
	event.preventDefault();
	if(!isEmpty(data)){
		 if(data.hasError){
			 Alert.warn(data.message);
		   }else{
			Alert.info(data.message);
			var currentMaterialLiId=$('ul.leftPaneData').find('li.active').attr('id');
			$('#'+currentMaterialLiId).remove();
			delete tahdrMaterialArray["tahdrMaterial"+currentMaterialLiId];
			$("#saveTahdrMaterialForm")[0].reset();
			$('#saveTahdrMaterialForm').find('select,textarea').val('');
		    }
	}
}

function loadAllTabsForMaterial(tahdrMaterialList){
	if(!$.isEmptyObject(tahdrMaterialList))
	{
		loadTahdrMaterialToLeftPane(tahdrMaterialList);
	/*$('#sectionDocTab').removeClass('readonly');*/
	}

else
	{
	 /*$('#sectionDocTab').addClass('readonly');*/
	  $(".leftPaneData").html("");
	  tahdrMaterialArray=[];
	  $('#saveTahdrMaterialForm')[0].reset();
	  $('#selectedMaterialId').val("");
	  if($("input[name='materialTypeCode']").val()=='single'){
			$('.viewPartsBtn').hide();
			$('.BOMVersion').hide();
			$("#BOMVersionId").html('');
			$("#BOMVersionId").removeClass('dropDown');
		}	
		else{
			$('.viewPartsBtn').hide();
			$('.BOMVersion').show();
			$("#BOMVersionId").addClass('dropDown');
			loadBomVersion();
		}
	  
	  if($("input[name='aboutSpecCode']").val()=='inc'){
			$('#selectedMaterialVersionId').show();
			$('.MaterialForVersion').hide();
	  }
	  else if($("input[name='aboutSpecCode']").val()=='ref'){
		  $('.MaterialForVersion').show();
		
	  }
	  $('#tenderMaterialForm').removeClass('readonly');
		$('#saveTahdrMaterialForm .saveTahdrMaterial').show();
		$('#saveTahdrMaterialForm .cancelTahdrMaterial').show();
		$(".tahdrMaterialTableGTP").removeClass('readonly');
		$('.addTahdrMaterial').removeClass('readonly');
		$('.editTahdrMaterial').removeClass('readonly');
		$('.addGtpToMaterial').removeClass('readonly');
		$('.addGtpToMaterial').html('<span class="glyphicon glyphicon-plus-sign"></span>Add GTP');
		$(".saveTahdrMaterialGtp").show();
	}
	
}

function loadTahdrMaterialToLeftPane(tahdrMaterialList){
	$("#leftPane").html("");
	var leftPanelHtml='';
	var i=0;
	var active="";
	var firstRow=null;
	tahdrMaterialArray=[];
	tahdrMaterialWithMaterialMap=[];
	if(!isEmpty(tahdrMaterialList)){
	$.each(Object.values(tahdrMaterialList),function(key,tahdrMaterial){
		
		tahdrMaterialArray["tahdrMaterial"+tahdrMaterial.tahdrMaterialId]=tahdrMaterial;
		tahdrMaterialWithMaterialMap["material"+tahdrMaterial.material.materialId]=tahdrMaterial.tahdrMaterialId;
		if(i==0){
			firstRow = tahdrMaterial;
			active="active";
		}
		leftPanelHtml= leftPanelHtml +appendTahdrMaterialLi(tahdrMaterial,active);
		active="";
		i++;
	});
	}
	$("#leftPane").append(leftPanelHtml);
	loadTahdrMaterialToRightPane(firstRow);
	setLeftPaneHeader("Item List", Object.values(tahdrMaterialArray).length);
	$("#leftPane").on('click','.tahdrMaterial',function(){
		var id=$(this).attr('id');
		loadTahdrMaterialToRightPane(tahdrMaterialArray["tahdrMaterial"+id]);	
	});
	
	$('#leftPane').paginathing({perPage: 10});
}

function loadTahdrMaterialToRightPane(tahdrMaterial){
	if(!$.isEmptyObject(tahdrMaterial)){
		$(".tahdrMaterialId").val(getValue(tahdrMaterial.tahdrMaterialId));
		
		if(!$.isEmptyObject(tahdrMaterial.material)){
			$("#selectedMaterialDesc").val(tahdrMaterial.material.description);
			$("#selectedMaterialId").empty();
			$("#selectedMaterialId").append("<option value='"+tahdrMaterial.material.materialId+"'>"+tahdrMaterial.material.name+"</option>");
			$('#selectedItemCode').val(tahdrMaterial.material.code);
			if(!$.isEmptyObject(tahdrMaterial.material.uom)){
				$("#selectedMaterialUom").val(tahdrMaterial.material.uom.name);
			}
			if(!$.isEmptyObject(tahdrMaterial.material.hsnCode)){
				$('#selectedHSNCode').val(tahdrMaterial.material.hsnCode.code);
			}
			 
		}
		$("#materialQuantity").val(getValue(tahdrMaterial.quantity));
		$("input[name='aboutSpecCode'][value='" + tahdrMaterial.aboutSpecCode + "']").prop('checked', true);
		$("input[name='aboutSpecCode']:checked").val(getValue(tahdrMaterial.aboutSpecCode));
		
		if('ref'==tahdrMaterial.aboutSpecCode){
			referSpec();
			$("#selectedMaterialVersionId").empty();
			$("#selectedMaterialVersionId").append("<option></option>");
			if(!$.isEmptyObject(tahdrMaterial.materialVersion)){
				$("#selectedMaterialVersionId").append("<option value='"+tahdrMaterial.materialVersion.materialVersionId+"'>"+tahdrMaterial.materialVersion.name+"</option>");
				var tahdrMaterialId=tahdrMaterialWithMaterialMap["material"+tahdrMaterial.materialVersion.material.materialId];
				$("#getMaterialForVersion").val(tahdrMaterialId);
				$("#selectedMaterialVersionId").val(tahdrMaterial.materialVersion.materialVersionId);
			}
		}else{
			includeSpec(tahdrMaterial.material.materialId);
			if(!$.isEmptyObject(tahdrMaterial.materialVersion)){
				$("#selectedMaterialVersionId").val(tahdrMaterial.materialVersion.materialVersionId);
			}
		}
		
		$("input[name='materialTypeCode'][value='" + tahdrMaterial.materialTypeCode + "']").prop('checked', true);
		materialTypeChange(tahdrMaterial.materialTypeCode);
		
		if(tahdrMaterial.materialTypeCode!="single"){
			if(!$.isEmptyObject(tahdrMaterial.bomVersion)){
				//$('#BOMVersionId').empty();
				//$('#BOMVersionId').append("<option value='" + tahdrMaterial.bomVersion.bomVersionId + "'>" + tahdrMaterial.bomVersion.name + "</option>");
				$('#BOMVersionId').val(tahdrMaterial.bomVersion.bomVersionId);
			}
			$("#BOMVersionId").addClass('dropDown');
			onChangeBOMVersion($('#BOMVersionId'));
		}			
		
		/*if($("input[name='materialTypeCode'][value='" + tahdrMaterial.materialTypeCode + "']").val()=='single'){
			$('.viewPartsBtn').hide();
			$('.BOMVersion').hide();
		}	
		else{
			$('.viewPartsBtn').show();
			$('.BOMVersion').show();
			if(!$.isEmptyObject(tahdrMaterial.bomVersion))
				$('#BOMVersionId').empty();
			$('#BOMVersionId').append("<option value='" + tahdrMaterial.bomVersion.bomVersionId + "'>" + tahdrMaterial.bomVersion.name + "</option>");
		}*/
		materialName=tahdrMaterial.material==null?'':tahdrMaterial.material.name==null?'':tahdrMaterial.material.name;
		materialDesc=tahdrMaterial.material==null?'':tahdrMaterial.material.description==null?'':tahdrMaterial.material.description;
		qty=tahdrMaterial.quantity==null?'':tahdrMaterial.quantity;
		uom=tahdrMaterial.material==null?'':tahdrMaterial.material.uom==null?'':tahdrMaterial.material.uom.name==null?'':tahdrMaterial.material.uom.name;
		
		/*if (status == 'Drafted'){
			$('#tenderMaterialForm').removeClass('readonly');
			$('#saveTahdrMaterialForm .save').show();
			$('#saveTahdrMaterialForm .cancel').show();
		}
		else{
			$('#tenderMaterialForm').addClass('readonly');
			$('#saveTahdrMaterialForm .save').hide();
			$('#saveTahdrMaterialForm .cancel').hide();
		}*/

	
		var activeStatus=tahdrMaterial.tahdrDetail==null?'':tahdrMaterial.tahdrDetail.isActive==null?'':tahdrMaterial.tahdrDetail.isActive;
		if(activeStatus!=null){
			if(activeStatus=='Y'){
				if (tenderStatus == 'Drafted'){
					$('#tenderMaterialForm').removeClass('readonly');
					$('#saveTahdrMaterialForm .saveTahdrMaterial').show();
					$('#saveTahdrMaterialForm .cancelTahdrMaterial').show();
					/*$('#tenderMaterialButtonsId').removeClass('readonly');*/
					$(".tahdrMaterialTableGTP").removeClass('readonly');
					$('.addTahdrMaterial').removeClass('readonly');
					$('.editTahdrMaterial').removeClass('readonly');
					$('.addGtpToMaterial').removeClass('readonly');
					$('.addGtpToMaterial').html('<span class="glyphicon glyphicon-plus-sign"></span>Add GTP');
					$(".saveTahdrMaterialGtp").show();
				}
				else if(tenderStatus=='Approved'){
					$('#tenderMaterialForm').addClass('readonly');
					/*$('#tenderMaterialButtonsId').addClass('readonly');*/
					$(".tahdrMaterialTableGTP").addClass('readonly');
					$('.addTahdrMaterial').addClass('readonly');
					$('.editTahdrMaterial').addClass('readonly');
					$('.addGtpToMaterial').removeClass('readonly');
					$('.addGtpToMaterial').html('<span class="glyphicon glyphicon-plus-sign"></span>View GTP');
					$(".saveTahdrMaterialGtp").hide();
					$('#saveTahdrMaterialForm .saveTahdrMaterial').hide();
					$('#saveTahdrMaterialForm .cancelTahdrMaterial').hide();
				}
				else{
					$('#tenderMaterialForm').addClass('readonly');
					/*$('#tenderMaterialButtonsId').addClass('readonly');*/
					$(".tahdrMaterialTableGTP").addClass('readonly');
					$('.addTahdrMaterial').addClass('readonly');
					$('.editTahdrMaterial').addClass('readonly');
					$('.addGtpToMaterial').removeClass('readonly');
					$('.addGtpToMaterial').html('<span class="glyphicon glyphicon-plus-sign"></span>View GTP');
					$(".saveTahdrMaterialGtp").hide();
					$('#saveTahdrMaterialForm .saveTahdrMaterial').hide();
					$('#saveTahdrMaterialForm .cancelTahdrMaterial').hide();
				}
				}
				else if(activeStatus=='N'){
					$('#tenderMaterialForm').addClass('readonly');
					/*$('#tenderMaterialButtonsId').addClass('readonly');*/
					$(".tahdrMaterialTableGTP").addClass('readonly');
					$('.addTahdrMaterial').addClass('readonly');
					$('.editTahdrMaterial').addClass('readonly');
					$('.addGtpToMaterial').removeClass('readonly');
					$('.addGtpToMaterial').html('<span class="glyphicon glyphicon-plus-sign"></span>View GTP');
					$(".saveTahdrMaterialGtp").hide();
					$('#saveTahdrMaterialForm .saveTahdrMaterial').hide();
					$('#saveTahdrMaterialForm .cancelTahdrMaterial').hide();
				}	
		}
	}
	else{
		$('#saveTahdrMaterialForm')[0].reset();
		$('#selectedMaterialId').val("");
		
	}
	var documentType=$(".documentType").val();
	setHeaderValues(documentType+"No.: "+tahdrCodes, documentType+"Title : "+title, "Department : "+department, "Status : "+tenderStatus);
	
	setChildLoadFlag(true);
	
}

function saveTahdrMaterialResp(data){
	
	if(data.response.hasError==false)
	{
		var leftPanelHtml='';
		var currentMaterialId=$('ul.leftPaneData').find('li.active').attr('id');
		var tahdrMaterialId=data.tahdrMaterialId;
		var materialId=data.material.materialId;
		if(currentMaterialId==tahdrMaterialId)
		{
			$('#'+currentMaterialId).remove();
		}
		if(currentMaterialId<tahdrMaterialId)
		{
			$('#'+currentMaterialId).removeClass('active');
		}
		$(".tahdrMaterialId").val(tahdrMaterialId);
		leftPanelHtml=appendTahdrMaterialLi(data,"active");
		if(!$.isEmptyObject(data.materialVersion)){
			data.materialVersion=versionArray["version_"+data.materialVersion.materialVersionId];
		}
		
		tahdrMaterialArray["tahdrMaterial"+tahdrMaterialId]=data;
		$(".leftPaneData").prepend(leftPanelHtml);
		
		if(data.response.hasError){
			Alert.warn(data.response.message);
		}else{
			Alert.info(data.response.message);
		}
		
		/*if ( $('.leftPaneData li').length == 0 ) {
			 $('#sectionDocTab').addClass('readonly');
		}
		else{
			 $('#sectionDocTab').removeClass('readonly');
		}*/
		
		$('#leftPane').paginathing({perPage: 10});
		$("#saveTahdrMaterial").find("inpt,select,textarea").removeClass("errorinput");
	}
	else
		 {
			if(data.response.hasError){
				Alert.warn(data.response.message);
			}else{
				Alert.info(data.response.message);
			}
		 }
	setChildLoadFlag(true);
	setChangedFlagById("sectionDocTab",true);
	}

function appendTahdrMaterialLi(tahdrMaterial,active){
	return appendLiData(tahdrMaterial.material.name, tahdrMaterial.materialDescription, tahdrMaterial.material.uom.name, tahdrMaterial.quantity, tahdrMaterial.tahdrMaterialId, active, 'tahdrMaterial');
}


function onMaterialTypeChange(type){
	var materialType=$(type).val();
	if(($('#selectedMaterialId').val()==null || $('#selectedMaterialId').val()=='undefined' || $('#selectedMaterialId').val()=='' || $('#selectedMaterialId').val()=='Select Item') && (materialType=='bom')){
		Alert.warn("Add Atleast One Material.");
		$("#saveTahdrMaterialForm")[0].reset();
	}
	else{
	materialTypeChange(type.value);
	}
}

function materialTypeChange(typeCode){
	if(typeCode=='single'){
		$('.viewPartsBtn').hide();		
		$('.BOMVersion').hide();
		$("#BOMVersionId").html('');
		$("#BOMVersionId").removeClass('dropDown');
	}	
	else{
		$('.viewPartsBtn').hide();
		$('.BOMVersion').show();
		$("#BOMVersionId").addClass('dropDown');
		loadBomVersion();
	}
}

function loadBomVersion(){
	var optionName = $('#selectedMaterialId').find('option:selected').val();
	/*submitToURL('getBOMListFromMaterial/' + optionName,'BOMVersionResp');*/
	if(optionName!=null && optionName!='Select Item'){
	submitToURL('getBOMListForTahdrMaterial/' + optionName,'BOMVersionResp');
}
}

function BOMVersionResp(data) {
	$("#BOMVersionId").html('');
	var options = '<option value="">Select BOM Version </option>';
	$.each(data.objectMap.bomVersion, function(key, value) {
		options += '<option value="' + value.bomVersionId + '">' + value.name + '</option>'
	});

	$("#BOMVersionId").append(options);
}


function includeSpec(materialId){
	var versionList=fetchList("getMaterialVersionByMaterialId",materialId);
	populateMaterialVersion(versionList);
	$(".MaterialForVersion").hide();
}

function referSpec(){
	populateMaterialForVersion(tahdrMaterialArray);
	$(".MaterialForVersion").show();
	$("#selectedMaterialVersionId").empty();
}

function onChangeBOMVersion(ele){
	if(!$.isEmptyObject($("#BOMVersionId").val())){
		$('.viewPartsBtn').show();
	}else{
		$('.viewPartsBtn').hide();
	}
 }