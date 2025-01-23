function populateMaterialGroupList(list){
	
	$(".materialGroupList").empty();
	$(".materialSubGroupList").empty();
	$(".materialList").empty();
	$(".materialGroupList").append("<option value=' '></option>" );
	$.each(list,function(idx,obj){
		$(".materialGroupList").append("<option value='"+obj.materialGroupId+"'>"+ obj.code +"-" + obj.name +" </option>" );
	});
}

function populateSubGroupList(list){
	
	$(".materialSubGroupList").empty();
	$(".materialList").empty();
	$(".materialSubGroupList").append("<option value=' '></option>" );
	$.each(list,function(idx,obj){
		$(".materialSubGroupList").append("<option value='"+obj.materialSubGroupId+"'>"+ obj.code +"-" + obj.name +" </option>" );
	});
}
function populateMaterialList(list){
	
	$(".materialList").empty();
	$(".materialList").append("<option value=' '></option>" );
	$.each(list,function(idx,obj){
		$(".materialList").append("<option value='"+obj.materialId+"' uom='"+obj.uom.name+"' description='"+ obj.description +"'>"+ obj.code +"-" + obj.name +" </option>" );
	});
}

function populateDepartment(list){
	
	$(".departmentList").empty();
	$(".departmentList").append("<option value=''></option>" );
	$.each(list,function(idx,obj){
		$(".departmentList").append("<option value='"+obj.departmentId+"'>" + obj.name +" </option>" );
	});
}

function populateLocationType(list){
	
	$("#officeType").empty();
	$("#officeType").append("<option value=''></option>" );
	$.each(list,function(idx,obj){
		$("#officeType").append("<option value='"+obj.locationTypeId+"'>" + obj.name +" </option>" );
	});
}
function populateMyTenderLocationTypeForVendor(list){
	$("#officeType").empty();
	$("#officeType").append("<option value=''></option>" );
	$.each(list,function(idx,obj){
		$("#officeType").append("<option value='"+idx+"'>" + obj +" </option>" );
	});
}