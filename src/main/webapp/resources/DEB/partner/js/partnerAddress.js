$(document).ready(function(){
	
});
function loadStateByCountry(countryId,formId,stateFieldId){
	debugger;
	
	var id=$("#"+formId+" #"+countryId).val();
	if(id==undefined || id==""){
		$("#"+formId+" #"+stateFieldId).html("");
		$("#"+formId+" #"+stateFieldId).append('<option value=""></option>');
	}else{
		var url='getStateByCountry'+"/"+id;
		var data=fetchList(url,null);
		if(data.objectMap.hasOwnProperty('regions')){
			  loadStateResp(data.objectMap.regions,formId,stateFieldId);
			}else{
				$("#"+formId+" #"+stateFieldId).html("");
				$("#"+formId+" #"+stateFieldId).append('<option value=""></option>');
			}
		}
}

function loadStateResp(data,formId,stateFieldId){
	debugger;
	
	$("#"+formId+" #"+stateFieldId).html("");
	var options = '<option value=""></option>';
	if(!isEmpty(data)){
	$.each(data,function(key,value){
		options +='<option value="'+value.regionId+'">'+value.name +'</option>'
		
	});
	}
	$("#"+formId+" #"+stateFieldId).append(options);
	
}
function loadDistrictByState(stateId,formId,districtFieldId){
	debugger;
	var id=$("#"+formId+" #"+stateId).val();
	if(id==undefined || id==""){
		$("#"+formId+" #"+districtFieldId).html("");
		$("#"+formId+" #"+districtFieldId).append('<option value=""></option>');
	}else{
		var url='getDistrictByState'+"/"+id;
		var data=fetchList(url,null);
		if(data.objectMap.hasOwnProperty('districts')){
				loadDistrictResp(data.objectMap.districts,formId,districtFieldId);
			}else{
				$("#"+formId+" #"+districtFieldId).html("");
				$("#"+formId+" #"+districtFieldId).append('<option value=""></option>');
			}
		
	}
}
function loadDistrictResp(data,formId,districtFieldId){
	debugger;
	$("#"+formId+" #"+districtFieldId).html("");
	var options = '<option value=""></option>';
	if(!isEmpty(data)){
	$.each(data,function(key,value){
		options +='<option value="'+value.districtId+'">'+value.name +'</option>'
		
	});
	}
	$("#"+formId+" #"+districtFieldId).append(options);

}