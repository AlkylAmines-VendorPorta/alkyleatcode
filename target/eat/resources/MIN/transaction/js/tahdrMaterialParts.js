$(document).ready(function(){
	
	$(".createTahdrMaterialParts").on("click",function(event){
		debugger;
		event.preventDefault();
		gtpCount=0;
		$('#tahdrMaterialPartsListFormTable tbody').empty();
		$("#tahdrMaterialDiv").css("display","none");
		$("#tahdrMaterialGtp").css("display","none");
		$("#tahdrMaterialParts").css("display","block");
		var id= $("#BOMVersionId").val();
		if(id!=null){
		var getPartsResp=fetchList( "getMaterialSpecificationFromMaterial",id);
		populateTahdrMaterialParts(getPartsResp.objectMap.materialSpecification)
		}
		else{
			$("#tahdrMaterialPartsTable tbody").empty();
		}
	});
	
	$("#cancelTahdrMaterialParts").on("click",function(){
		$("#cancelTahdrMaterialParts").hide();
		var id= $(".tahdrMaterialId").val();
	});
	
	$("#backToTahdrMaterialTab").on("click",function(event){
		event.preventDefault();
		$("#cancelTahdrMaterialParts").hide();
		$("#tahdrMaterialDiv").css("display","block");
		$("#tahdrMaterialGtp").css("display","none");
		$("#tahdrMaterialParts").css("display","none");
	});
	
	
});

function populateTahdrMaterialParts(partsData){
	debugger;
	$("#tahdrMaterialPartsTable tbody").empty();
	$.each(partsData,function(idx,obj){
		var MaterialSpec=obj.specification==null?'':obj.specification.name==null?"":obj.specification.name;
		var quantity = obj.quantity==null?'':obj.quantity;
		/*var gtpParameterTypeId = obj.gtp.gtpParameterType.gtpParameterTypeId==null?'':obj.gtp.gtpParameterType.gtpParameterTypeId;*/
		var	tr="<tr>" +
		/*"<td> <input type='checkbox' "+isChecked+" class='tahdrMaterialGtpCheck tmg"+gtpParameterId+"' value='Y' tahdrMaterialGtpId='"+getValue(obj.tahdrMaterialGtpId)+"' /></td>" +*/
		"<td> <input type='hidden' class='gtpParameterId' />"+ MaterialSpec +"</td>" +
		"<td> <input type='hidden' class='gtpParameterTypeId'  />"+ quantity +"</td>" +
		/*"<td> <input  type='checkbox' class='isPublish' value='"+getValue(obj.isPublish)+"'/></td>" +*/
		"</tr>";
		$("#tahdrMaterialPartsTable tbody").append(tr);
	});
}
