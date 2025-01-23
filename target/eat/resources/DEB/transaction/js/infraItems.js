var infraItemArray=new Array();
$(document).ready(function(){
	var pageInfo=$("#pageInfo").val();
    if(pageInfo=="vendorDetails")
	{
    	$('#addInfraItemBtnId').show();
	}else{
		$('#addInfraItemBtnId').hide();
	}

	$('#addInfraItemBtnId').click(function(event){
        event.preventDefault();
        emptyInfraItemForm();
    });
	$('#addItemPopup').click(function(event){
		
		event.preventDefault();
		$('.commenSearchModal').modal('show');
		$(".itemTable").DataTable().destroy();
		$(".itemTable tbody").empty();
		populateMaterialGroupList(fetchList("getMaterialGroupList",null));
		/*populateSubGroupList(fetchList("getMaterialSubGroupList",null));*/
		$.fn.DataTable.ext.pager.numbers_length = 5;
		$('.commenSearchTable').DataTable({
			"lengthMenu":lengthMenu
		});
	
	});
	$('#cancelInfraItemBtnId').click(function(event){
		
		event.preventDefault();
		var currentInfraItemId = $('ul.leftPaneData').find('li.active').attr('id');
		if(currentInfraItemId!=undefined){
			var itemData=infraItemArray["infraItem"+currentInfraItemId];
			loadInfraItemRightPane(itemData);
		}else{
			emptyInfraItemForm();
		}
	});
	
});

function getInfraItem(event,el){
	 event.preventDefault();
	 submitWithParam('getInfraItems','bPartnerId','onInfraItemTabLoad');
	 $('#AdditemButton').removeData('callback');
	 $('#AdditemButton').attr('data-callback','loadItemsResp');
	 $('#searchItemList').attr('onclick',"return submitIt('srchItemForm','getItemList','populateItemListWithSingleSelect');");
}
function getInfraItems(event,el){
	submitWithParam('getInfraItems','bPartnerId','onInfraItemTabLoad');
	 $('#AdditemButton').removeData('callback');
	 $('#AdditemButton').attr('data-callback','loadItemsResp');
	 $('#searchItemList').attr('onclick',"return submitIt('srchItemForm','getItemList','populateItemListWithSingleSelect');");
}
function loadItemsResp(data){
	
	$("#infraMaterialId").html("");
	var options='';
	$.each(data.map,function(index,val){
		options +='<option value="'+index+'">'+val+'</option>'
		
	});
	$("#infraMaterialId").append(options);
	$("#infraMaterialId").removeClass('errorinput');
	 $('#uomId').val(data.uomName);
	 $("#uomId").removeClass('errorinput');
	 $('#hsnId').val(data.hsn);
}
function onInfraItemTabLoad(data){
	
	if(data.objectMap.hasOwnProperty('infraItems'))
		loadInfraItemLeftPane(data.objectMap.infraItems);
}
function emptyInfraItemForm(){
	 $("#partnerInfraItemId").val("");	
	 $(".partnerInfraItemId").val("");	
	 $("#infraMaterialId").empty();
	/* setAttribute("checkBoxId","N");*/
	 $("#infraItemForm")[0].reset();
	 $(".infraTabs").removeClass('readonly');
	  $("#saveItemBtnId").show();
	  $("#cancelInfraItemBtnId").show();
	  $("#saveTypeTestBtnId").show();
	  $("#cancelTypeTestBtnId").show();
	  $("#confirmApprovalBtnId").show();
}
function loadInfraItemLeftPane(data){
	$('.pagination').children().remove();
	$(".leftPaneData").html("");
	var leftPanelHtml="";
	var i=0;
	var active=false;
	var firstRow=null;
	if(data==null){
		  emptyInfraItemForm();
		  return;
		}
	$.each(data,function(key,value){
		
		infraItemArray["infraItem"+value.partnerInfraItemId]=value;
		var materialName=value.material==null?'':value.material.name==null?'':value.material.name;
		var materialId=value.material==null?'':value.material.materialId==null?'':value.material.materialId;
			
		if(i==0){
			firstRow = value;
			leftPanelHtml = leftPanelHtml + ' <li onclick="showInfraItemInfo('+value.partnerInfraItemId+')" id="'+value.partnerInfraItemId+'" class="active">'
		}else{
			leftPanelHtml = leftPanelHtml + ' <li onclick="showInfraItemInfo('+value.partnerInfraItemId+')" id="'+value.partnerInfraItemId+'">'
		}
		leftPanelHtml = leftPanelHtml +' <a href="#Master" data-toggle="tab">'
        +' <div class="col-md-12">'
        +'  <label class="col-xs-6 "> '+materialName+'</label>'
        +'	<label class="col-xs-6 ">'+""+'</label>'
        +' </div>'	
        +' </a>'
        +' </li>';
		i++;
	});
	$(".leftPaneData").append(leftPanelHtml);
	$('.leftPaneData').paginathing();
	
	$(".tabs-left li a label").text(function(index, currentText) {
	    return currentText.substr(0, 20);
	});
	loadInfraItemRightPane(firstRow);
}
function showInfraItemInfo(id){
	
	var itemData=infraItemArray["infraItem"+id];
	loadInfraItemRightPane(itemData);
}
function loadInfraItemRightPane(data){
	
  if(!$.isEmptyObject(data)){
	    var materialName=data.material==null?'':data.material.name==null?'':data.material.name;
		var materialId=data.material==null?'':data.material.materialId==null?'':data.material.materialId;
		var partnerInfraItemId=data.partnerInfraItemId==null?'':data.partnerInfraItemId;
		var uomName=data.material==null?'':data.material.uom==null?'':data.material.uom.name==null?'':data.material.uom.name;
		var hsnCode=data.material==null?'':data.material.hsnCode==null?'':data.material.hsnCode.code==null?'':data.material.hsnCode.code;
		var level=data.levelNo==null?'':data.levelNo;
		var status=data.status==null?'':data.status;
		$("#partnerInfraItemId").val(partnerInfraItemId);
		$(".partnerInfraItemId").val(partnerInfraItemId);
		$("#infraMaterialId").empty();
		$("#infraMaterialId").append('<option value="'+materialId+'">'+materialName+'</option>');
		$("#infraMaterialId").val(materialId);
		$("#infraMaterialId").removeClass('errorinput');
		$("#uomId").val(uomName);
		$("#hsnId").val(hsnCode);
		setAttribute("checkBoxId",data.isActive);
		status=showStatus(data);
		setHeaderValues("Item Name:" +materialName ,"Level:"+level, "Status:"+status, " ");
		showInfraVendorFields(data);
  }else{
	  emptyInfraItemForm();
	  setHeaderValues("Item Name:"  ,"  ", " ", " ");
  }
}
function showStatus(data){
	var status=data.status==null?'':data.status;
	if(status=='DR'){
		return 'Drafted';
	}else if(status=='IP'){
		return 'In Progress';
	}else if(status=='CLRFN'){
		return 'Clarified';
	}else if(status=='CO'){
		return 'Approved';
	}else if(status=='RJ'){
		return 'Rejected';
	}
}
function showInfraVendorFields(data){
	var status=data.status==null?'':data.status;
	var pageInfo=$("#pageInfo").val();
	    if(pageInfo=="vendorDetails")
		{
		   if(status=="DR" || status=="EDIT")
			   {
			      $(".infraTabs").removeClass('readonly');
				  $("#saveItemBtnId").show();
				  $("#cancelInfraItemBtnId").show();
				  $("#saveTypeTestBtnId").show();
				  $("#cancelTypeTestBtnId").show();
				  $("#confirmApprovalBtnId").show();
			   }else{
				  $(".infraTabs").addClass('readonly');
				  $("#saveItemBtnId").hide();
				  $("#cancelInfraItemBtnId").hide();
				  $("#saveTypeTestBtnId").hide();
				  $("#cancelTypeTestBtnId").hide();
				  $("#confirmApprovalBtnId").hide();
			   }
		      $("#saveDocBtnId").hide();
			  $("#cancelDocBtnId").hide();
		}else if(pageInfo=="vendorApproval"){
		   $(".infraTabs").addClass('readonly');
		   $("#saveItemBtnId").hide();
		   $("#cancelInfraItemBtnId").hide();
		   $("#saveDocBtnId").show();
		   $("#cancelDocBtnId").show();
		   $("#saveTypeTestBtnId").show();
		   $("#cancelTypeTestBtnId").show();
		   $("#confirmApprovalBtnId").show();
		}
}
function infraItemsResp(data){
	
	if(!data.response.hasError)
	{
	var currentInfraItemId = $('ul.leftPaneData').find('li.active').attr('id');
	var leftPanelHtml = "";
	var status = true;
	var partnerInfraItemId = data.partnerInfraItemId;

	$('#infraItemForm #partnerInfraItemId').val(partnerInfraItemId);
	$(".partnerInfraItemId").val(partnerInfraItemId);
	if (currentInfraItemId == partnerInfraItemId) {
		$('#'+currentInfraItemId).remove();
	}else {
		$('#'+currentInfraItemId).removeClass('active');
	}
	
	leftPanelHtml = leftPanelHtml + ' <li onclick="showInfraItemInfo('+partnerInfraItemId+')" id="'+partnerInfraItemId+'" class="active">';
	leftPanelHtml = leftPanelHtml +' <a href="#Master" data-toggle="tab">'
    +' <div class="col-md-12">'
    +'  <label class="col-xs-6 "> '+$("#infraMaterialId").text()+'</label>'
    +'	<label class="col-xs-6 ">'+""+'</label>'
    +' </div>'	
    +' </a>'
    +' </li>';
	$(".leftPaneData").prepend(leftPanelHtml);
	Alert.info(data.response.message);
	data.material.name=$("#infraMaterialId").text();
	infraItemArray["infraItem"+partnerInfraItemId]=data;
	}else{
		if(!$.isEmptyObject(data.response.errors))
		{
				var msg=data.response.message;
				  $.each(data.response.errors,function(key,value){
				       msg=msg+'\n'+value.errorMessage+',';
				       
				   });
				   Alert.warn(msg);
		}else{
			Alert.warn(data.response.message);
		}
	}
}