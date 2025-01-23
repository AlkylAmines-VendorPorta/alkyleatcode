/*$(document).ready(function(){ 
	renderList();
	$('#searchlitralid').on('keyup', function () {
	    var value = this.value;
	    $('#example li').hide().each(function () {
	        if ($(this).text().toUpperCase().search(value.toUpperCase()) > -1) {
	        	$(this).show();;
	        }
	    });
	});
})*/
var plugin_index = $('.paginate').length;
var materialVersionArray= new Array();
var versionArray=new Array();
$(document).ready(function(){
	 
	/*$("#materialVersionForm #material").removeClass("readonly");*/
	$('#material').parent().addClass('noneback');
	$("#materialVersionForm #code").removeAttr("readonly","readonly");
	
		$(".tabs-left li a label").text(function(index, currentText) {
		    return currentText.substr(0, 18);
	    });
		
	$('#addVersionBtnId').click(function(event) {
		event.preventDefault();
/*		$('#materialVersionForm')[0].reset();
		$("#materialVersionId").val("");
		$()
*/		$("#materialVersionForm #name").val("");
		$("#materialVersionForm #code").val("");
		$("#materialVersionForm #description").val("");
		$("#materialVersionId").val("");
		$('#versionSpcificationId').val('');
		$('#versionSpecification').val('');
		$("#a_versionSpecification").html('');
		/*$("#material").empty();
		$("#material").append("<option value=''></option>");
		$("#materialVersionForm #material").removeClass("readonly");*/
		$("#materialVersionForm #code").removeAttr("readonly","readonly");
		});
	$('#cancelVersionBtnId').click(function(event) {
		event.preventDefault();
		var activeVersionId=$('.leftPaneData').find('li.active').attr('id');
		if(activeVersionId!=undefined)
			{
			showMaterialVersion(activeVersionId);
			}
		else
		/*	$('#materialVersionForm')[0].reset();*/
			$("#materialVersionId").val("");
		$("#materialVersionForm #name").val("");
		$("#materialVersionForm #code").val("");
		$("#materialVersionForm #description").val("");
		$('#versionSpcificationId').val('');
		$('#versionSpecification').val('');
		$("#a_versionSpecification").html('');
    });
	
	$('.addItemPopup').click(function(event){
		event.preventDefault();
		$('.commenSearchModal').modal('show');
		$(".itemTable").DataTable().destroy();
		$(".itemTable tbody").empty();
		populateMaterialGroupList(fetchList("getMaterialGroupList",null));
		$.fn.DataTable.ext.pager.numbers_length = 5;
		$('.commenSearchTable').DataTable({
			'lengthMenu': [ 5, 10 ]
		});
	
	});
});

function setHeaderValuesForVersion(leftTop,rightTop,leftBottom){
	$('#materialVersionHeaderId').empty();
	$('#materialVersionHeaderId').append('<div class="row"><label class="col-xs-6" ><h4 class="detail_Name">'+leftTop+'</h4></label>'
            +'<label class="col-xs-6 mytext detail_Code">'+rightTop+'</label></div> '
            +'<div class="row"><label class="col-xs-6 mytext detail_Desc">'+leftBottom+'</label></div>');
}

function matrialVersionTabResp(data){
	$('#AdditemButton').removeData('callback');
	$('#AdditemButton').attr('data-callback','appendMaterialList');
	$("#srchItemForm #orgId").val("");
	$('#searchItemList').attr('onclick',"return submitIt('srchItemForm','getItemList','populateItemListWithSingleSelect');");
	if(data.objectMap.hasOwnProperty('materialVersions')){
		if(!$.isEmptyObject(data.objectMap.materialVersions)){
			 materialVersionLeftPane(data.objectMap.materialVersions);
		 }else{
			$(".leftPaneData").html("");
			$("#materialVersionForm #name").val("");
			$("#materialVersionForm #code").val("");
			$("#materialVersionForm #description").val("");
			$("#materialVersionId").val("");
			$('#versionSpcificationId').val('');
			$('#versionSpecification').val('');
			$("#a_versionSpecification").html('');
			$("#materialVersionForm #code").removeAttr("readonly","readonly");
		 }
    }
	setActiveTabName("Material Version",$('.leftPaneData li').length);
}

function appendMaterialList(data){
	$("#material").html("");
	var options='';
	$.each(data.map,function(index,val){
		options +='<option value="'+index+'">'+val+'</option>'
		
	});
	$("#material").append(options);
	$("#material").removeClass('errorinput');
}

function materialVersionLeftPane(data)
{
	debugger;
	if(data.length==0)
		{
		 return;
		}
	$('.pagination').children().remove();
	$(".leftPaneData").html("");
	var leftPanelHtml="";
	var i=0;
	var active=false;
	var firstRow=null;
	$.each(data,function(key,value){
		if(value!=null){
			var materialVersionId=value.materialVersionId;
			materialVersionArray["materialVersion"+materialVersionId]=value;
			if(i==0){
				firstRow = value;
				active=true;
			}
			leftPanelHtml=leftPanelHtml+appendMaterialVersionData(value,active);
			active=false;
			i++;
		}
	});
	$(".leftPaneData").append(leftPanelHtml);
    $('.leftPaneData').paginathing();	
    
	
	$(".tabs-left li a label").text(function(index, currentText) {
	    return currentText.substr(0, 18);
    });
	loadMaterialVersionRightPane(firstRow);
}

function appendMaterialVersionData(data,active){
	if(!isEmpty(data)){
		$('.pagination').children().remove();
		 var leftPanelHtml='';
		 var materialVersionId=data.materialVersionId==null?'':data.materialVersionId;
		 var name=data.name==null?'':data.name;
		 var code=data.code==null?'':data.code;
		 var description=data.description==null?'':data.description;
		 var materialId=data.material==null?'':data.material.materialId==null?'':data.material.materialId;
		 var materialName=data.material==null?'':data.material.name==null?'':data.material.name;
	     var versionSpecificationId=data.versionSpecification==null?'':data.versionSpecification.attachmentId==null?'':data.versionSpecification.attachmentId;
	     var versionSpecificationName=data.versionSpecification==null?'':data.versionSpecification.fileName==null?'':data.versionSpecification.fileName;
	     var isActive=data.isActive==null?'':data.isActive;
	     if(active)
			 {
			    leftPanelHtml = leftPanelHtml + ' <li class="active" onclick="showMaterialVersion('+materialVersionId+')" id="'+materialVersionId+'">';
			 }else{
				leftPanelHtml = leftPanelHtml + ' <li onclick="showMaterialVersion('+materialVersionId+')" id="'+materialVersionId+'">';
			 }
		 leftPanelHtml = leftPanelHtml +' <a href="#Master" data-toggle="tab">'
		    +' <div class="col-md-12">'
		    +'  <label class="col-xs-6" id="name-'+materialVersionId+'"> '+name+'</label>'
		    +'	<label class="col-xs-6" id="code-'+materialVersionId+'" >'+code+'</label>'
		    +' </div>'
		    +' <div class="col-md-12">'
		    +'	<label class="col-xs-6" id="description-'+materialVersionId+'" >'+description+'</label>'
		    +'	<label class="col-xs-6" id="materialName-'+materialVersionId+'" >'+materialName+'</label>'
		    +' </div>'
		    +' </a>'
		    +' </li>';
		   
		return leftPanelHtml;
		$('.leftPaneData').paginathing();
	}
}
function loadMaterialVersionRightPane(data){
	if(data==null){
	 return;
	}
	
     var materialVersionId=data.materialVersionId==null?'':data.materialVersionId;
	 var name=data.name==null?'':data.name;
	 var code=data.code==null?'':data.code;
	 var description=data.description==null?'':data.description;
	 var materialId=data.material==null?'':data.material.materialId==null?'':data.material.materialId;
	 var materialName=data.material==null?'':data.material.name==null?'':data.material.name;
     var versionSpecificationId=data.versionSpecification==null?'':data.versionSpecification.attachmentId==null?'':data.versionSpecification.attachmentId;
     var versionSpecificationName=data.versionSpecification==null?'':data.versionSpecification.fileName==null?'':data.versionSpecification.fileName;
     var isActive=data.isActive==null?'':data.isActive;
    
	$("#materialVersionForm #materialVersionId").val(materialVersionId);
	$("#materialVersionForm #name").val(name);
	$("#materialVersionForm #code").val(code);
	$("#materialVersionForm #description").val(description);
	$("#materialVersionForm #material").addClass("readonly");
	$("#materialVersionForm #code").attr("readonly","readonly");
	$("#material").empty();
	$("#material").append('<option value="'+materialId+'">'+materialName+'</option>');
	$("#material").removeClass('errorinput');
	$("#materialVersionForm #material").val(materialId);
	showMaterialFileDeleteBtn(versionSpecificationId);
	
	if(isActive=="Y")
		$('#isActive').prop('checked', true);
	else
		$('#isActive').prop('checked', false);
	
	var url = $("#a_versionSpecification").data('url');
	$("#a_versionSpecification").attr('href', url);
	var a_versionSpecification = $("#materialVersionForm #a_versionSpecification").prop('href') + '/'+ versionSpecificationId;
	$("#materialVersionForm #a_versionSpecification").prop('href', a_versionSpecification);
	$("#materialVersionForm #a_versionSpecification").html(versionSpecificationName);
	setHeaderValuesForVersion("Material Version Name:"+name,"Material Version:"+code,"Material Name:"+materialName);
	
}

function showMaterialFileDeleteBtn(attachmentId) {
	if (attachmentId!="") {
		$(".versionSpecification").attr('disabled', false);
	} else {
		$(".versionSpecification").attr('disabled', true);
	}
}

function showMaterialVersion(id){
	var data=materialVersionArray["materialVersion"+id];
	loadMaterialVersionRightPane(data);
	
}

function materialVersionDelResp(data){
	if(!data.hasError){
	Alert.info(data.message);
	/*$("#materialVersionForm #material").removeClass("readonly");*/
	$("#materialVersionForm #code").removeAttr("readonly","readonly");
	var currentmaterialVersionId=$('ul.leftPaneData').find('li.active').attr('id');
	$('#'+currentmaterialVersionId).remove();
	$('#materialVersionForm')[0].reset();
	$("#materialVersionForm #materialVersionId").val("");
	 $("#materialVersionForm #name").val("");
		$("#materialVersionForm #code").val("");
		$("#materialVersionForm #description").val("");
		$("#materialVersionId").val("");
		$('#versionSpcificationId').val('');
		$('#versionSpecification').val('');
		$("#a_versionSpecification").html('');
	/*$("#material").empty();
	$("#material").append("<option value=''></option>");*/
	setActiveTabName("Material Version",$('.leftPaneData li').length);
    $('.leftPaneData').paginathing();	
    
	$(".tabs-left li a label").text(function(index, currentText) {
	    return currentText.substr(0, 18);
    });
	event.preventDefault();
	}else{
		Alert.warn(data.message);
	}
}

function materialVersionResp(data){
	
	if(!data.response.hasError){
	var currentmaterialVersionId=$('ul.leftPaneData').find('li.active').attr('id');
	var leftPanelHtml="";
	var status=true;
	var materialVersionId=data.materialVersionId;
	$('.pagination').children().remove();
	$('#materialVersionForm #materialVersionId').val(materialVersionId);
	if(currentmaterialVersionId==materialVersionId){
		$('#'+currentmaterialVersionId).remove();
	}else{
		$('#'+currentmaterialVersionId).removeClass('active');
	}	
	leftPanelHtml=appendMaterialVersionData(data,status);
	$(".leftPaneData").prepend(leftPanelHtml);
    $('.leftPaneData').paginathing();	
    
	$(".tabs-left li a label").text(function(index, currentText) {
	    return currentText.substr(0, 18);
    });
	if(data.versionSpecification!=null)
		{
	     data.versionSpecification.fileName=$("#materialVersionForm #a_versionSpecification").text();
		}
	setActiveTabName("Material Version",$('.leftPaneData li').length);
	data.material.name=$("#materialVersionForm #material option:selected").text();
	materialVersionArray["materialVersion"+materialVersionId]=data;
	setHeaderValuesForVersion("Material Version Name:"+data.name,"Material Version:"+data.code,"Material Name:"+$("#materialVersionForm #material option:selected").text());
	getDropDownValueForLabel(materialVersionId);
	status=false;
	Alert.info(data.response.message);
	$("#materialVersionForm #material").addClass("readonly");
	$("#materialVersionForm #code").attr("readonly","readonly");
	}else{
	      Alert.warn(data.response.message);
	}
	event.preventDefault();
}
function getDropDownValueForLabel(id)
{
	debugger;
	$("#materialName-"+id).html($("#materialVersionForm #material option:selected").text());
}
	
function versionSpecificationDeleteResp(data)
{
	debugger;
	$('.pagination').children().remove();
	if (!data.hasError) {
		$('#versionSpcificationId').val('');
		$('#versionSpecification').val('');
		$("#a_versionSpecification").html('');
		$('.versionSpecification').attr('disabled', true);
		Alert.info(data.message);
	}else {
		Alert.warn(data.message);
	}
	$('.leftPaneData').paginathing();
	
}

function deleteMaterialVersion(){
	var materialVersionId=$('#materialVersionId').val();
	if(materialVersionId!=''){
		submitToURL('deleteMaterialVersion/'+materialVersionId,'materialVersionDelResp');
	}else{
		Alert.warn('Please select material version !');
	}
	
	/*return submitWithParam('deleteMaterialVersion','materialVersionId','materialVersionDelResp');*/
}

/*function renderList()
{ 
	$.ajax({
        type : "POST",
        contentType : "application/json",
        url: "getMaterialVersionList",
        dataType:"json",
        async:false,
        success: function (data) {
        	if($.isEmptyObject(data))
        		{
        		  swal("No MaterialVersion present in List");
        		}
        	else
        	{
	        	appendList(data);
        	}
		        	
        },
        error: function (e) {
			swal("Exception :");
        }
    });
	}*/
/*
function appendList(data)
{
	
	var active=" class='active'";
	$('#example').empty();
	var activeStatus='';
	for (var i=0;i<data.length;i++)  {
		if(data[i].isActive=="Y")
			activeStatus="Active";
		else
			activeStatus="InActive";
		if(i==0)
			{
				
				$(".Name").val(data[i].name);
				$(".Code").val(data[i].code);
				$(".Description").val(data[i].description);
				$(".Id").val(data[i].materialVersionId);
				if(!$.isEmptyObject(data.material))
				$(".Material").val(data[i].material.magterialId);
				if(activeStatus=="Active")
					$('.isActive').prop('checked', true);
				else
					$('.isActive').prop('checked', false);
				
			$('#masterDetails').empty();
			$('#masterDetails').append('<div class="row"><label class="col-xs-6" ><h4 class="detail_Name">'+data[i].name+'</h4></label>'
		            +'<label class="col-xs-6 mytext detail_Code">'+data[i].code+'</label></div> '
		            +'<div class="row"><label class="col-xs-6 mytext detail_Desc">'+data[i].description+'</label>'
		            +'<label class="col-xs-6 mytext detail_Active">'+data[i].material.name+'</label></div>');
	          
				
			}
			
		$('#example').append('<li '+active+' onclick="showdetails('+data[i].materialVersionId+')"> <a href="" class="" data-toggle="tab">'
		           +' <div class="col-md-12">'
		           +'  <label class="col-xs-6">'+data[i].name+'</label>'
		           +'   <label class="col-xs-6 mytext" data-id="'+data[i].materialVersionId+'">'+data[i].name+'</label>'
		           +'  </div>'
		           +'  <div class="col-md-12">'
		           +'    <label class="col-xs-6">'+data[i].code+'</label>'
		           +'    <label class="col-xs-6 mytext2">'+activeStatus+'</label>'
		           +'  </div>'
		           +'  </a>'
		           +'  </li>');
		active="";
          }
	$('.example').paginathing();	
      	
      	$(".tabs-left li a label").text(function(index, currentText) {
      	    return currentText.substr(0, 18);
      });
      	$('.reportCount').html(data.length);
	}
function showdetails(id)
{
	$.ajax({
        type : "POST",
        contentType : "application/json",
        url: "getMaterialVersion/"+id,
        dataType:"json",
        async:false,
        success: function (data) {
        	if($.isEmptyObject(data))
        		{
        		  swal("MaterialVersion details is empty");
        		}
        	else
        	{
	        	$(".Name").val(data.name);
				$(".Code").val(data.code);
				$(".Description").val(data.description);
				$(".Id").val(data.materialVersionId);
				if(!$.isEmptyObject(data.material))
				$(".Material").val(data.material.materialId);
				
				
				if(data.isActive=="Y")
					$('.isActive').prop('checked', true); 
				else
					$('.isActive').prop('checked', false);
				
				$(".detail_Name").html(data.name);
				$(".detail_Code").html(data.code);
				$(".detail_Desc").html(data.description);
				$(".detail_Active").html(data.material.name);
				
        	}
		        	
        },
        error: function (e) {
			swal("Exception :");
        }
    });}
*/
