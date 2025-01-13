$(document).ready(function(){
	var matId;
	var matName;
	var hsn;
	var matGroup;
	var code;
	var itemTrade;
	var description;
	
	/*$('.addBom').click(function(event) {
		event.preventDefault();
		$("#bomVersionFormId").removeClass('readonly');
		$('#bomVersionForm #bomVersionId').val("");
		$('#bomVersionForm #bomVersionName').val("");
		$(".save").show();
		$(".cancel").show();
		
		});*/
		
	$('#editBom').click(function(event) {
		  event.preventDefault();
		  $("#bomVersionFormId").removeClass('readonly');
   	      $(".save").show();
          $(".cancel").show();
		});

	$('#cancelBom').click(function(event) {
		event.preventDefault();
		var activeBomId=$('.leftPaneData').find('li.active').attr('id');
		if(activeBomId!=undefined)
			{
			showBomDetail(activeBomId);
			}
		else
			$('#bomVersionForm')[0].reset();
		
    });
	
	$('#deleteBom').click(function(event) {
		debugger;
		event.preventDefault();
		submitWithParam('deleteBomVersion','bomVersionId','bomVersionDelResp');
    });
	
});

function addBom(event){
event.preventDefault();
$("#bomVersionFormId").removeClass('readonly');
$('#bomVersionForm #bomVersionId').val("");
$('#bomVersionForm #bomVersionName').val("");
/*$(".save").show();
$(".cancel").show();*/
}
function onBomVersionTabLoad(data){
	debugger;
	$("#addRecord").removeAttr("onclick");
	$("#addRecord").attr("onclick","addBom(event)");
	if(data.objectMap.hasOwnProperty('material')){
		masterData(data.objectMap.material);
}
	if(data.objectMap.hasOwnProperty('bomVersion')){
		loadTabs(data.objectMap.bomVersion);
}
	
	
	setActiveTabName("Bom Version",$('.leftPaneData li').length);

}

function loadTabs(bomList){
	if(!$.isEmptyObject(bomList))
		{
		loadBomLeftPane(bomList);
		 $('#partsTab').removeClass('readonly');
		}
	else
		{
		$('#partsTab').addClass('readonly');
		$(".leftPaneData").html("");
		};

}


function loadBomLeftPane(data){
	debugger;
	$('#pagination-here').empty();
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
		leftPanelHtml= leftPanelHtml +appendBomData(value,active);
		active=false;
		i++;
	});
	$(".leftPaneData").append(leftPanelHtml);
	$('.leftPaneData').paginathing({perPage: 6});
	loadBomRightPane(firstRow);
	$('#materialDetails').empty();
	$('#materialDetails').append('<div class="row"><label class="col-xs-6" ><h4 class="Name">'+matName+'</h4></label>'
            +'<label class="col-xs-6 mytext itemTrade">'+itemTrade+'</label></div> '
            +'<div class="row"><label class="col-xs-6 mytext Code">'+code+'</label>'
            +'<label class="col-xs-6 mytext description">'+description+'</label></div>');

}

function appendBomData(value,active)
{
	debugger;
	 var leftPanelHtml='';
	 var bomVersionId=value.bomVersionId==null?'':value.bomVersionId;
	 if(active)
		 {
		    leftPanelHtml = leftPanelHtml + ' <li class="active" onclick="showBomDetail('+bomVersionId+')" id="'+bomVersionId+'">';
		 }else{
			leftPanelHtml = leftPanelHtml + ' <li onclick="showBomDetail('+bomVersionId+')" id="'+bomVersionId+'">';
		 }
	
	    var materialId=value.material==null?'':value.material.materialId==null?'':value.material.materialId;
		var versionName = value.name==null?'':value.name;
		/*var isStandard = value.isStandard==null?'':value.isStandard;*/
		var materialName =value.material==null?$('#bomVersionForm #materialName').val():value.material.name==null?$('#bomVersionForm #materialName').val():value.material.name;

		leftPanelHtml = leftPanelHtml +' <a href="#materialDetails" data-toggle="tab">'
	    +' <div class="col-md-12">'
	    +'  <label class="col-xs-6" id="lblBomVersionId-'+bomVersionId+'">'+materialName+'</label>'
	    +'	<label class="col-xs-6 " id="lblVersionName-'+bomVersionId+'">'+versionName+'</label>'
	    +' </div>'
        +' <div class="col-md-12" style="display: none">'
		+'	<label class="col-xs-6" id="lblBomVerId-'+bomVersionId+'">'+bomVersionId+'</label>'
	    +'	<label class="col-xs-6" id="lblMaterialId-'+bomVersionId+'">'+materialId+'</label>'
	    +'	<label class="col-xs-6" id="lblMaterialName-'+bomVersionId+'" >'+materialName+'</label>'
	    +'	<label class="col-xs-6" id="lblVersionName-'+bomVersionId+'" >'+versionName+'</label>'
	   /* +'	<label class="col-xs-6" id="lblIsStandard-'+bomVersionId+'" >'+isStandard+'</label>'*/
		+' </div>'
        +' </a>'
        +' </li>';
		

		$('#materialDetails').empty();
		$('#materialDetails').append('<div class="row"><label class="col-xs-6" ><h4 class="Name">'+matName+'</h4></label>'
	            +'<label class="col-xs-6 mytext itemTrade">'+itemTrade+'</label></div> '
	            +'<div class="row"><label class="col-xs-6 mytext Code">'+code+'</label>'
	            +'<label class="col-xs-6 mytext description">'+description+'</label></div>');
	
	return leftPanelHtml;
}

function showBomDetail(id)
{
	$("#bomVersionForm #bomVersionId").val($("#lblBomVerId-"+id).html());
	$("#bomVersionForm #materialId").val($("#lblMaterialId-"+id).html());
	$("#bomVersionForm #materialName").val($("#lblMaterialName-"+id).html());
	$("#bomVersionForm #bomVersionName").val($("#lblVersionName-"+id).html());
	$('#SpecPartstForm .bomName').val($("#lblVersionName-"+id).html());
	$("#SpecPartstForm #bomVersionId").val($("#lblBomVerId-"+id).html());
	/*$("#bomVersionForm .isStandard").val($("#lblIsStandard-"+id).html());*/
	
	 
	$('#materialDetails').empty();
	$('#materialDetails').append('<div class="row"><label class="col-xs-6" ><h4 class="Name">'+matName+'</h4></label>'
            +'<label class="col-xs-6 mytext itemTrade">'+itemTrade+'</label></div> '
            +'<div class="row"><label class="col-xs-6 mytext Code">'+code+'</label>'
            +'<label class="col-xs-6 mytext description">'+description+'</label></div>');
}

function loadBomRightPane(data){
if(!$.isEmptyObject(data)){
	var bomVersionId = data.bomVersionId==null?'':data.bomVersionId;
	var matrialId = data.material==null?'':data.material.materialId==null?'':data.material.materialId;
	var materialName = data.material==null?'':data.material.name==null?'':data.material.name;
	var versionName = data.name==null?'':data.name;
	/*var isStandard = data.isStandard==null?'':data.isStandard;*/
	
}
	
if(!$.isEmptyObject(data)){	
	$("#bomVersionForm #bomVersionId").val(bomVersionId);
	$("#bomVersionForm #materialId").val(matrialId);
	$("#bomVersionForm #materialName").val(materialName);
	$("#bomVersionForm #bomVersionName").val(versionName);
	/*$("#bomVersionForm .isStandard").val(isStandard);*/
	$("#SpecPartstForm #bomVersionId").val(bomVersionId);
	$("#SpecPartstForm .bomName").val(versionName);
}
else{
	$("#bomVersionForm #bomVersionId").val("");
	$("#bomVersionForm #bomVersionName").val("");
}	
/*setHeaderValues("Factory Name: "+FactoryName, "Established Date : "+establishedDate, "License No.: "+licenseNo, "Location : "+locationName);*/
}

function bomVersionDelResp(data)
{
	debugger;
	var currentBomId=$('ul.leftPaneData').find('li.active').attr('id');
	if(data.hasError==false)
	{
		$('#'+currentBomId).remove();
		$('#bomVersionForm #bomVersionId').val("");
		$('#bomVersionForm #bomVersionName').val("");
		Alert.info(data.message,'','success');
	}
	else
	{
	  Alert.warn(data.message,"","error");
	}
	
	
}

function bomVersionResp(data){
debugger;
	if(data.response.hasError==false){
		var bomFlag=true;
		var leftPanelHtml='';
		var currentBomId=$('ul.leftPaneData').find('li.active').attr('id');
		var bomVersionId= data.bomVersionId;
		var materialId= data.material==null?'':data.material.materialId==null?'':data.material.materialId;
		var name=data.name==null?'':data.name;
		if(currentBomId==bomVersionId){
			$('#'+currentBomId).remove();
		}
		else
		{
			$('#'+currentBomId).removeClass('active');
		}
		$("#bomVersionForm #bomVersionId").val(bomVersionId);
		$("#bomVersionForm #materialId").val(materialId);
		$("#SpecPartstForm #bomVersionId").val(bomVersionId);
		$("#SpecPartstForm .bomName").val(name);
		leftPanelHtml=appendBomData(data,bomFlag);
		$(".leftPaneData").prepend(leftPanelHtml);
		
		bomFlag=false;
		Alert.info(data.response.message,'','success');	
		if($(".leftPaneData li").length==0){
			 $('#partsTab').addClass('readonly');
		}else
			{
			 $('#partsTab').removeClass('readonly');
			}
		/*setHeaderValues("Factory Name: "+FactoryName, "Established Date : "+establishedDate, "License No.: "+licenseNo, "Location : "+locationName);*/
	}
	else
		 {
		   Alert.warn(data.response.message,'','error');
		 }

	}


function masterData(data){
	
	 matId=data.materialId==null?'':data.materialId;
	 matName= data.name==null?'':data.name;
	 hsn = data.hsnCode==null?'':data.hsnCode.code==null?'':data.hsnCode.code;
	 matGroup = data.materialGroup==null?'':data.materialGroup.name==null?'':data.materialGroup.name;
	 code= data.code==null?'':data.code;
	 itemTrade =data.itemTrade==null?'':data.itemTrade;
	 description = data.description==null?'':data.description;


}