$(document).ready(function(){
	var documentType=$(".documentType").val();
	
	$(".addGtpToMaterial").on("click",function(event){
		event.preventDefault();
		gtpCount=0;
		$('#tahdrMaterialGtpListFormTable tbody').empty();
		$("#tahdrMaterialDiv").css("display","none");
		$("#tahdrMaterialParts").css("display","none");
		$("#tahdrMaterialGtp").css("display","block");
		$("#leftPane").addClass('readonly');
		var id= $(".tahdrMaterialId").val();
		var materialId=$('#selectedMaterialId').val();
		if(materialId!=''){
			var getTahdrMarterialResp=fetchList( "getTahdrGtpByMaterialId/"+id,materialId);
			populateTahdrMaterialGTP(getTahdrMarterialResp.objectMap.tahdrGtpList);	
		}
	});
	
	$("#cancelTahdrMaterialGtp").on("click",function(){
		$("#cancelTahdrMaterialGtp").hide();
		var id= $(".tahdrMaterialId").val();
		if(materialId!=''){
		var getTahdrMarterialResp=fetchList( "getTahdrGtpByMaterialId/"+id,materialId);
		populateTahdrMaterialGTP(getTahdrMarterialResp.objectMap.tahdrGtpList);
		}
	});
	
	$("#backToTahdrMaterial").on("click",function(event){
		event.preventDefault();
		$("#cancelTahdrMaterialGtp").hide();
		$("#leftPane").removeClass('readonly');
		$("#tahdrMaterialDiv").css("display","block");
		$("#tahdrMaterialGtp").css("display","none");
		setHeaderValues(documentType+"No.: "+tahdrCodes, documentType+"Title : "+title, "Department : "+department, "Status : "+tenderStatus);
	});
	
	$("#tahdrMaterialGtpTable tbody").on("change", ".tahdrMaterialGtpCheck" ,function(){
		$("#cancelTahdrMaterialGtp").show();
		addGtpToHiddenForm(this);		
	});
	
	$(".saveTahdrMaterialGtp").on("click", function(event){
		var trList=$("#tahdrMaterialGtpListFormTable tbody tr");
		gtpCount=0;
		$.each(trList,function(row,obj){
			var tdList=$(obj).find("td");
			$.each(tdList,function(col,obj){
				$(obj).find(".materialGtpCheck").attr("name","gtpList["+gtpCount+"].tahdrMaterialGtpId");
				$(obj).find(".isAdded").attr("name","gtpList["+gtpCount+"].isAdded");
				$(obj).find(".gtpParameter").attr("name","gtpList["+gtpCount+"].gtp.gtpParameterId");
				$(obj).find(".gtpParameterType").attr("name","gtpList["+gtpCount+"].gtp.gtpParameterType.gtpParameterTypeId");
				$(obj).find(".material").attr("name","gtpList["+gtpCount+"].tahdrMaterial.tahdrMaterialId");
				$(obj).find(".isPublish").attr("name","gtpList["+gtpCount+"].isPublish");
				$(obj).find(".tahdrDetailId").attr("name","gtpList["+gtpCount+"].tahdrDetail.tahdrDetailId");
			});
			gtpCount++;
		});
		submitIt("tahdrMaterialGtpListForm","saveTahdrMaterialGtp", "saveGtpResp");
	});
	
});

function populateTahdrMaterialGTP(materialGTPData){
	$("#tahdrMaterialGtpTable tbody").empty();
	
	$.each(materialGTPData,function(idx,obj){
		var isChecked=obj.tahdrMaterialGtpId==null?"":"checked='checked'";
		var gtpParameterId = obj.gtp.gtpParameterId==null?'':obj.gtp.gtpParameterId;
		var gtpParameterTypeId = obj.gtp.gtpParameterType.gtpParameterTypeId==null?'':obj.gtp.gtpParameterType.gtpParameterTypeId;
		var	tr="<tr>" +
		"<td> <input type='checkbox' "+isChecked+" class='tahdrMaterialGtpCheck tmg"+gtpParameterId+"' value='Y' tahdrMaterialGtpId='"+getValue(obj.tahdrMaterialGtpId)+"' /></td>" +
		"<td> <input type='hidden' class='gtpParameterId' value='"+gtpParameterId+"'/>"+ obj.gtp.name +"</td>" +
		"<td> <input type='hidden' class='gtpParameterTypeId' value='"+gtpParameterTypeId+"' />"+ obj.gtp.gtpParameterType.name +"</td>" +
		/*"<td> <input  type='checkbox' class='isPublish' value='"+getValue(obj.isPublish)+"'/></td>" +*/
		"</tr>";
		$("#tahdrMaterialGtpTable tbody").append(tr);
	});
	var documentType=$(".documentType").val();
	setHeaderValues("Material Name: "+materialName, "Material Description : "+materialDesc, "Quantity : "+qty, "UOM : "+uom);
}

function saveGtpResp(respData){
	if(!respData.success){
		Alert.warn(respData.message);
	}else{
		Alert.info(respData.message);
	}
	
	
	$.each(respData.objectMap.gtpDtoList,function(idx,obj){
		$("#tahdrMaterialGtpTable tbody").find(".tmg"+obj.gtp.gtpParameterId).attr("tahdrMaterialGtpId",getValue(obj.tahdrMaterialGtpId));
	});
	$("#tahdrMaterialGtpListFormTable tbody").empty();
}

function addGtpToHiddenForm(ele){
	var gtpId=$(ele).closest('tr').find(".gtpParameterId").val();
	var gtpTypeId=$(ele).closest('tr').find(".gtpParameterId").val();
	var isAdded;
	if($(ele).prop("checked")){
		isAdded='Y';
	}else{
		isAdded='N';
	}
	var existingTr=$("#tahdrMaterialGtpListFormTable tbody").find('.gtp'+gtpId);
	if(existingTr.length==0){
		var tr="<tr class='gtp"+gtpId+"'>" +
		"<td> <input class='materialGtpCheck' value='"+$(ele).attr("tahdrMaterialGtpId")+"' /></td>" +
		"<td> <input class='isAdded' value='"+isAdded+"' /></td>" +
		"<td> <input class='gtpParameter' value='"+gtpId+"'/></td>" +
		"<td> <input class='gtpParameterType' value='"+gtpTypeId+"'/></td>" +
		"<td> <input class='material' value='"+$(".tahdrMaterialId").val()+"'/></td>" +
		"<td> <input class='tahdrDetailId' value='"+$(".tahdrDetailId").val()+"'/></td>" +
		/*"<td> <input class='isPublish' value='"+$(this).closest('tr').find(".isPublish").val()+"'/></td>" +*/
		"</tr>";
		$("#tahdrMaterialGtpListFormTable tbody").append(tr);
	}else{
		existingTr.remove();
	}
}

function onChangeSelectAll(ele){
	$("#tahdrMaterialGtpListFormTable tbody").empty();
	var tahdrGtpList=$(".tahdrMaterialGtpCheck");
	$.each(tahdrGtpList,function(idx,tahdrGtp){
		$(tahdrGtp).prop("checked",$(ele).prop("checked"));
		addGtpToHiddenForm(tahdrGtp);
	});
}