$(document).ready(function(){

	 var lengthMenu;
	    if ($(window).width() < 480) {
	        $('.mobileNav').show();
	        $.fn.DataTable.ext.pager.numbers_length = 4;       
	        lengthMenu = [ 1, 5, 7, 10, ],
	        [ 1, 5, 7, 10, ]
	    } else {        
	        lengthMenu = [ 5, 10, ],
	        [ 5, 10, ]
	    }
	    
	$('.addMatPopup').click(function(event){
		event.preventDefault();
		$('.commenSearchModal').modal('show');
		$(".itemTable").DataTable().destroy();
		$(".itemTable tbody").empty();
		populateMaterialGroupList(fetchList("getMaterialGroupList",null));/*
		populateSubGroupList(fetchList("getMaterialSubGroupList",null));*/
		$('.commenSearchTable').DataTable({
			"lengthMenu":lengthMenu
		});
	});
	
	$('#addBom').click(function(event) {
		event.preventDefault();
		$('#SpecPartstForm #materialSpecificationId').val("");
		$("#SpecPartstForm #specName").empty();
		$("#SpecPartstForm #specName").append("<option value=''></option>");
		$('#SpecPartstForm #Quantity').val("");
		
		});
	
		/*$('.addSpecBtn').click(function(event) {
	
		event.preventDefault();
		$(".Id").val("");
		$("#hsnFormId").removeClass('readonly');
		$('.isActive').prop('checked', true); 
		$(".save").show();
		$(".cancel").show();
		$("#edithsn").removeClass('readonly');
		$("#delhsn").removeClass('readonly');
		$("#hsnFormId").find('input,select,textarea').val("");
		});*/
		
	$('#editSpecBtnId').click(function(event) {
		 event.preventDefault();
		 /* $("#bomVersionFormId").removeClass('readonly');*/
  	      $(".save").show();
         $(".cancel").show();
		});

	$('#cancelSpecsBtnId').click(function(event) {
		event.preventDefault();
		var activeBomId=$('.leftPaneData').find('li.active').attr('id');
		if(activeBomId!=undefined)
			{
			showBomDetail(activeBomId);
			}
		else
			$('#bomVersionForm')[0].reset();
		
    });
	
	$('#deleteSpecBtnId').click(function(event) {
		debugger;
		event.preventDefault();
		submitWithParam('deleteMaterialSpecification','materialSpecificationId','specDelResp');
    });
	
});

function partsTabLoad(data){
	$("#addRecord").removeAttr("onclick");
	$("#addRecord").attr("onclick","addSpecBtn(event)");
	$('#AdditemButton').removeData('callback');
	$('#AdditemButton').attr('data-callback','loadMaterialPartsName');
	$('#searchItemList').attr('onclick',"return submitIt('srchItemForm','getItemList','populateItemListWithSingleSelect');");
	if(data.objectMap.hasOwnProperty('matSpec')){
		loadPartsLeftPane(data.objectMap.matSpec);
}
	
	setActiveTabName("Material Parts",$('.leftPaneData li').length);

}

function addSpecBtn(event){
	debugger;
	event.preventDefault();
	$(".Id").val("");
	$("#hsnFormId").removeClass('readonly');
	$('.isActive').prop('checked', true); 
	$(".save").show();
	$(".cancel").show();
	$("#edithsn").removeClass('readonly');
	$("#delhsn").removeClass('readonly');
	$("#hsnFormId").find('input,select,textarea').val("");	
}
function loadPartsLeftPane(data){
	debugger;
	$(".leftPaneData").html("");
	var leftPanelHtml='';
	var i=0;
	var active=false;
	var firstRow=null;
	$.each(data,function(key,value){
		if(i==0){
			firstRow = value;
			active=true;
		}
		leftPanelHtml= leftPanelHtml +appendPartsData(value,active);
		active=false;
		i++;
	});
	$(".leftPaneData").append(leftPanelHtml);
	
	loadPartsRightPane(firstRow);
}

function appendPartsData(value, active) {
	debugger;
	var leftPanelHtml = '';
	var specId = value.materialSpecificationId == null ? '' : value.materialSpecificationId;
	if (active) {
		leftPanelHtml = leftPanelHtml
				+ ' <li class="active" onclick="showPartsdetails(' + specId+ ')" id="' + specId + '">';
	} else {
		leftPanelHtml = leftPanelHtml + ' <li onclick="showPartsdetails('+ specId + ')" id="' + specId + '">';
	}
	var specification = value.specification == null ? '' : value.specification.materialId==null?'': value.specification.materialId;
	var quantity = value.quantity == null ? '': value.quantity;
	var isActive= value.isActive == null ?'':value.isActive;
	var bomVersion = value.material==null?'':value.material.bomVersionId==null?'':value.material.bomVersionId;
	/*var versionName = value.material==null?'':value.material.name==null?'':value.material.name;*/
	var specificationName= value.specification==null?$('#SpecPartstForm #specName').text():value.specification.name==null?$('#SpecPartstForm #specName').text():value.specification.name;
	 var versionName = value.material==null?$('#SpecPartstForm #bomName').val():value.material.name==null?$('#SpecPartstForm #bomName').val():value.material.name; 
	 /*var specificationName =$('#SpecPartstForm #specName').find('select').val('');*/
	
	leftPanelHtml = leftPanelHtml + ' <a href="#Master" data-toggle="tab">'
			+ ' <div class="col-md-12">'
			+ '	<label class="col-xs-6" id="labelversionName-' + specId + '">'+versionName+'</label>'
			+ '	<label class="col-xs-6 " id="labelSpecNames-' + specId + '">'+specificationName+ '</label>' + ' </div>'
			+ ' <div class="col-md-12">'
			+ '	<label class="col-xs-6" id="labelqty-' + specId + '">'+quantity+'</label>'
			+ ' </div>'
			+ ' <div class="col-md-12" style="display: none">'
			+ '	<label class="col-xs-6" id="labelVersionId-' + specId + '">'+bomVersion+'</label>'
			+ '	<label class="col-xs-6 " id="labelSpec-' + specId + '">'+specification+ '</label>' 
			+ '	<label class="col-xs-6" id="labelSpecId-' + specId + '">'+specId+'</label>'
			+ '	<label class="col-xs-6" id="labelactive-' + specId + '">'+isActive+'</label>'
			+' </div>'
			+ ' </a>' 
			+ ' </li>';

	return leftPanelHtml;
}


function showPartsdetails(id)
{
	var itemName= $("#labelSpec-" + id).html()
	var itemId= $("#labelSpecNames-" + id).html()
	$("#SpecPartstForm .bomVersionId").val($("#labelVersionId-" + id).html());
	$("#SpecPartstForm #materialSpecificationId").val($("#labelSpecId-" + id).html());
	$("#SpecPartstForm .bomName").val($("#labelversionName-" + id).html());
	$("#SpecPartstForm .specName").val($("#labelSpec-" + id).html());
	$("#SpecPartstForm .isActive").val($("#labelactive-" + id).html());
	$("#SpecPartstForm .Quantity").val($("#labelqty-" + id).html());
	/*$("#SpecPartstForm #matId").val($("#labelSpec-" + id).html());*/
	$("#SpecPartstForm .specName").empty();
	$("#SpecPartstForm .specName").append('<option value="'+itemName+'">'+itemId+'</option>');
	$("#SpecPartstForm .specName").val(itemName);
	

}

function loadPartsRightPane(data){
	debugger;
	if (data == null) {
		return;
	}
	var name = data.name == null ? '' : data.name;
	var specification = data.specification == null ? '' : data.specification.materialId==null?'': data.specification.materialId;
	var quantity = data.quantity == null ? '': data.quantity;
	var isActive= data.isActive == null ?'':data.isActive;
	var bomVersion = data.material==null?'':data.material.bomVersionId==null?'':data.material.bomVersionId;
	var versionName = data.material==null?'':data.material.name==null?'':data.material.name;
	var specId = data.materialSpecificationId == null ? '' : data.materialSpecificationId;
	var specificationName= data.specification==null?'':data.specification.name==null?'':data.specification.name;
	
	$("#SpecPartstForm .bomVersionId").val(bomVersion);
	$("#SpecPartstForm #materialSpecificationId").val(specId);
	$("#SpecPartstForm .bomName").val(versionName);
	$("#SpecPartstForm .isActive").val(isActive);
	$("#SpecPartstForm .Quantity").val(quantity);
	$("#SpecPartstForm .specName").empty();
	$("#SpecPartstForm .specName").append('<option value="'+specification+'">'+specificationName+'</option>');
	$("#SpecPartstForm .specName").val(specification);
	/*$("#SpecPartstForm #matId").val(specification);*/
}

function specDelResp(data)
{
	debugger;
	var currentPartsId=$('ul.leftPaneData').find('li.active').attr('id');
	if(data.hasError==false)
	{
		$('#'+currentPartsId).remove();
		$('#SpecPartstForm #materialSpecificationId').val("");
		$('#SpecPartstForm #specName').val("");
		$('#SpecPartstForm #Quantity').val("");
		Alert.info(data.message,'','success');
	}
	else
	{
	  Alert.warn(data.message,"","error");
	}
	
	
}

function materialSpecificationResp(data){
debugger;
	if(data.response.hasError==false)
	{
		var partsFlag=true;
		var leftPanelHtml='';
		var currentPartsId=$('ul.leftPaneData').find('li.active').attr('id');
		var specId= data.materialSpecificationId;
		var specification = data.specification == null ? '' : data.specification.materialId==null?'': data.specification.materialId;
		var bomVersion = data.material==null?'':data.material.bomVersionId==null?'':data.material.bomVersionId;
		if(currentPartsId==specId)
		{
			$('#'+currentPartsId).remove();
		}
		else
		{
			$('#'+currentPartsId).removeClass('active');
		}
		$("#SpecPartstForm #materialSpecificationId").val(specId);
		$("#SpecPartstForm .bomVersionId").val(bomVersion);
		$("#SpecPartstForm #specName").val(specification);
		leftPanelHtml=appendPartsData(data,partsFlag);
		$(".leftPaneData").prepend(leftPanelHtml);
		partsFlag=false;
		$("#saveParts").hide();
    	$("#cancelSpecsBtnId").hide();
		Alert.info(data.response.message,'','success');	
		/*setHeaderValues("Factory Name: "+FactoryName, "Established Date : "+establishedDate, "License No.: "+licenseNo, "Location : "+locationName);*/
	}
	else
		 {
		   Alert.warn(data.response.message,'','error');
		 }

	}

function loadMaterialPartsName(values){
	debugger;
	$(".specName").html("");
	var options='';
	$.each(values.map,function(index,val){
		options +='<option value="'+index+'">'+val +'</option>'
		
	});
	$(".specName").append(options);
}
