
var leftPanePageSize = 7;
var maxVisiblePageNumbers = 3;
var selectedPage = 1;

$(document).ready(function(){ 
	debugger;
	renderList();
	$("#savenewmaterial").hide();
	$("#canclematerial").hide();
	/*$(.").hide();
*/	/*$('#searchlitralid').on('keyup', function () {
	    var value = this.value;
	    $('#example li').hide().each(function () {
	        if ($(this).text().toUpperCase().search(value.toUpperCase()) > -1) {
	        	$(this).show();;
	        }
	    });
	});*/
	/*$(".addnewmaterial").click(function(){
		$(".save").show();
		$(".cancle").show();
		$(".Name").val("");
		$(".Code").val("");
		$(".Description").val("");
		$(".ItemTrade").val("");
		$("#editselectedmaterial").hide();
		$('.isActive').prop('checked', true);
		$('.TestReport').prop('checked', false);
		$('.SSI').prop('checked', false);
		$('.NSIC').prop('checked', false);
		$('.PropReport').prop('checked', false);
		 $(".Name").removeClass('readonly');
			$(".Code").removeClass('readonly');
			$(".Description").removeClass('readonly');
	});*/
	$("#savenewmaterial").on("click",function(){
		
		debugger;
		/*$("#editselectedmaterial").hide();
		$(".Id").removeAttr('name');
		submitIt("materialmasters","savenewmaterial", "getResponseDto");*/
		event.preventDefault();
		submitIt("materialFormId", "savenewmaterial", "saveMaterialResp");
		return false;
	});
	
	$("#delmaterial").on("click", function(event) {
		event.preventDefault();
		submitWithParam("deletematerialmaster", "Id", "deleteMaterialResp");
		/* $(".Id").attr('name', 'materialId');*/
		
	});
	$('#materialDetailTabId').click(function(event) {
		event.preventDefault();
		$('.pagination-container').remove();
		$('#loading-wrapper').show();
		$("#addRecord").removeAttr("onclick");
		$("#addRecord").attr("onclick","addMaterial(event)");
		/*$("#addRecord").removeClass("addVersionBtn");
		$("#addRecord").addClass("addnewmaterial");*/
		//renderList();
		var searchby = $('#searchLiteralId').val();
		var searchMode = $('input[name=filterBy]:checked').val();
		if(searchby != ''){
			var response = fetchMaterialList(selectedPage, leftPanePageSize, searchMode, searchby);
			appendList(response.data);
			setPagination(response.objectMap.LastPage, selectedPage , maxVisiblePageNumbers);
		}else{
			var response = fetchMaterialList(selectedPage, leftPanePageSize, 'none', 'none');
			appendList(response.data);
			setPagination(response.objectMap.LastPage, selectedPage , maxVisiblePageNumbers);
		}
		$('#loading-wrapper').fadeOut('slow');
		return;
    });
	
	$('.cancel').click(function(event) {
		event.preventDefault();
		var activeId=$('.example').find('li.active').attr('id');
		if(activeId!=undefined)
			{
			showdetails(activeId);
			
			}
		else
			$('#materialFormId')[0].reset();
		
	});
	$('.addnewmaterial').on("click", function(event)  {
		debugger;
		event.preventDefault();
		$(".Id").val("");
		$("#materialFormId").removeClass('readonly');
		$(".save").show();
		$(".cancel").show();
	    $("#materialFormId").find('input,select,textarea').val("");
		$("#typeCodeId").val("I");
	});
	$('#deleteCopiedGtpBtnId').click(function(event) {
		debugger;
		event.preventDefault();
		var materialId=$("#materialFormId #materialId").val();
		if(materialId!=''){
			submitToURL('deleteGtp/'+materialId, 'deleteGtpResp');
		}else{
			Alert.warn('Select Material to Delete Copied Gtp !');
		}
		return;
    });
	
	 $("#editMaterial").on("click", function(event) {
		 debugger;
			event.preventDefault();
			  $("#materialFormId").removeClass('readonly');
	     	  $(".save").show();
	          $(".cancel").show();
		});
	 $('#pagination-here').paginate({
			pageSize:  7,
			dataSource: 'fetchMaterialList',
			responseTo:  'appendList',
			maxVisiblePageNumbers:3,
			searchBoxID : 'searchLiteralId',
			searchBtnId : 'searchBtn',
		});

	
		
});
   /*$("#editselectedmaterial").click(function(){
	   submitIt("materialFormId","updatematerialmaster", "getResponseUpdate");
		
	});*/
   $("#materialDetailTabId").click(function(){
	   renderList();
   });
	

function addMaterial(event){
	event.preventDefault();
	$(".Id").val("");
	$("#materialFormId").removeClass('readonly');
	$(".save").show();
	$(".cancel").show();
    $("#materialFormId").find('input,select,textarea').val("");
	$("#typeCodeId").val("I");
}

function deleteMaterialResp(data){
	  debugger;
	  if(!$.isEmptyObject(data)){
		  var currentDel=$('#example').find('li.active').attr('id');
			if(data.hasError==false)

			{
				$('#'+currentDel).remove();
				$('#materialFormId #Id').val("");
				$('#materialFormId #name').val("");
				$('#materialFormId #code').val("");
				$('#materialFormId #description').val("");
				$('#materialFormId #itemTrade').val("");
				$('#materialFormId #matGroup').val("");
				$('#materialFormId #hsn').val("");
				$('#materialFormId #matSubGroup').val("");
				$('#materialFormId #uom').val("");
				
				$('#masterDetails').empty();
				Alert.info(data.message);
		    	$('#errorMsg').hide();
		    	$(".detail_name").html('');
				$(".detail_code").html('');
				$(".detail_Desc").html('');
				$(".detail_itemTrade").html('');
			}
			else
				{
				var errorLog = "";
				$.each(data.data,function(key, value){
					errorLog = errorLog+value.errorMessage+"\n ;";
				});
				$('#errorMsg').show();
				$('#errorMsg').html("'"+errorLog+"'")
				Alert.warn(data.message,"","error");
				}
			}
}


function saveMaterialResp(data){
	 debugger;
	 
	 if(!$.isEmptyObject(data)){
			if(data.response.hasError==false)
			{
				
		    	Alert.info(data.response.message);
		    	appendRecentRecord(data);
		    	$("#savenewmaterial").hide();
		    	$("#canclematerial").hide();
		    	/*$(".editSave").hide();*/
		    	 $("#materialFormId").addClass('readonly');
		    	$('#errorMsg').hide();
			}
			else
				{
				var errorLog = "";
				$.each(data.data,function(key, value){
					errorLog = errorLog+value.errorMessage+"\n ;";
				});
				$('#errorMsg').show();
				$('#errorMsg').html("'"+errorLog+"'")
				Alert.warn(data.responseMsg,"","error");
				}
			}
}

function appendRecentRecord(data){
	  debugger;
	  var currentMatId=$('#example').find('li.active').attr('id');
	  $("#Id").val(data.Id);
	  if(data.isActive=="Y")
			activeStatus="Active";
		else
			activeStatus="InActive";
	  
		var hsn=data.hsnCode==null?'':data.hsnCode.name==null?'':data.hsnCode.name;
		var matGroupName=data.materialGroup==null?'':data.materialGroup.name==null?'':data.materialGroup.name;
		
		if(!$.isEmptyObject(data.uom))
			$(".uom").val(data.uom.uomId);
			if(!$.isEmptyObject(data.materialGroup))
			$(".matGroup").val(data.materialGroup.materialGroupId);
			if(!$.isEmptyObject(data.materialSubGroup))
			$(".matSubGroup").val(data.materialSubGroup.materialSubGroupId);
			if(!$.isEmptyObject(data.hsnCode))
				$("#hsn").val(data.hsnCode.hsnId);
	  if(currentMatId==data.materialId){
		  $('#'+currentMatId+'').remove();
	  }
	  else{
		  $('#'+currentMatId+'').removeClass();
	  }
	  var active=" class='active'";
	$('#masterDetails').empty();
		/*$('.masterDetails').empty();*/
		$('#masterDetails').append('<div class="row"><label class="col-xs-6" ><h4 class="detail_name">'+data.name+'</h4></label>'
				 +'<label class="col-xs-6 mytext detail_code">'+data.code+'</label></div>'
		            +'<div class="row"><label class="col-xs-6 mytext detail_itemTrade">'+data.itemTrade+'</label>'
		            +'<label class="col-xs-6 mytext detail_Desc">'+data.description+'</label></div>');
		
		/*$('.masterDetails').append('<div class="row"><label class="col-xs-6" ><h4 class="Name">'+data.name+'</h4></label>'
				 +'<div class="row"><label class="col-xs-6 mytext detail_Desc">'+data.description+'</label>'
	            +'<div class="row"><label class="col-xs-6 mytext detail_itemTrade">'+data.itemTrade+'</label>'
	            +'<label class="col-xs-6 mytext Code">'+data.code+'</label></div>');*/
          
		

	
$('#example').prepend('<li '+active+' onclick="showdetails('+data.materialId+')" id="'+data.materialId+'"> <a href="" class="" data-toggle="tab">'
           +' <div class="col-md-12">'
           +'  <label class="col-xs-6">'+data.name+'</label>'
           +'   <label class="col-xs-6 mytext" data-userId="'+data.materialId+'">'+data.itemTrade+'</label>'
           +'  </div>'
           +'  <div class="col-md-12">'
           +'    <label class="col-xs-6">'+data.code+'</label>'
           +'    <label class="col-xs-6 mytext2">'+data.description+'</label>'
           +'  </div>'
           +'  </a>'
           +'  </li>');
	  
	  $(".Id").val(data.materialId);
	 	/*$('.saveBtn').removeAttr("onclick");
	 	$('.CancelBtn').removeAttr("onclick");*/
	 	$('#errorMsg').hide();
	 	$('#materialFormId').addClass('readonly');
	 	$(".detail_name").html(data.name);
	    $(".detail_code").html(data.code);
	    $(".detail_Desc").html(data.description);
	  	$(".detail_itemTrade").html(data.ItemTrade);
	 	$('.example').paginathing();
	 		

}


function renderList(){ 

var data = fetchMaterialList(1, leftPanePageSize, 'none', 'none');
       // appendList(data.data);
       // setPagination(data.objectMap.LastPage, 1, maxVisiblePageNumbers);	
        	
    	$('#AdditemButton').removeData('callback');
    	$('#AdditemButton').attr('data-callback','copyGtpFrom');
    	$('#AdditemButton').html('Select And Proceed');
    	$('#searchItemList').attr('onclick',"return submitIt('srchItemForm','getItemList','populateItemListWithSingleSelect');");     	
}
/*function renderList()
{ 
	debugger;
	$.ajax({
        type : "POST",
        contentType : "application/json",
        url: "getMaterialList",
        dataType:"json",
        async:false,
        success: function (data) {
        	if($.isEmptyObject(data))
        		{
        		  Alert.warn("No Material present in List");
        		}
        	else
        	{
	        	console.log(data);
	        	appendList(data);
	        	
        	}
        	$('#AdditemButton').removeData('callback');
        	$('#AdditemButton').attr('data-callback','copyGtpFrom');
        	$('#AdditemButton').html('Copy Gtp');
        	$('#searchItemList').attr('onclick',"return submitIt('srchItemForm','getItemList','populateItemListWithSingleSelect');");     	
        },
        error: function (e) {
			Alert.warn(e.message);
        }
    });
	}
*/


function appendList(data)
{
	debugger;
	var active=" class='active'";
	$('#example').empty();
	var activeStatus="";
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
				$(".HSNCode").val(data[i].hsnCode.hsnId);
				$(".ItemTrade").val(data[i].itemTrade);
				$(".Id").val(data[i].materialId);
				$("#bomVersionForm #materialName").val(data[i].name);
				$("#bomVersionForm #materialId").val(data[i].materialId);
				$("#materialVersionForm #material").empty();
				$("#materialVersionForm #material").append("<option value='"+data[i].materialId+"'>"+data[i].name+"</option>");
				if(!$.isEmptyObject(data[i].uom))
				$(".uom").val(data[i].uom.uomId);
				if(!$.isEmptyObject(data[i].materialGroup))
				$(".matGroup").val(data[i].materialGroup.materialGroupId);
				if(!$.isEmptyObject(data[i].materialSubGroup))
				$(".matSubGroup").val(data[i].materialSubGroup.materialSubGroupId);
				
				if(activeStatus=="Active")
					$('.isActive').prop('checked', true);
				else
					$('.isActive').prop('checked', false);
				if(data[i].isTestReport=="Y")
					$('.TestReport').prop('checked', true); 
				else
					$('.TestReport').prop('checked', false);
				if(data[i].isSSI=="Y")
					$('.SSI').prop('checked', true); 
				else
					$('.SSI').prop('checked', false);
				if(data[i].isNSIC=="Y")
					$('.NSIC').prop('checked', true); 
				else
					$('.NSIC').prop('checked', false);
				if(data[i].isPropReport=="Y")
					$('.PropReport').prop('checked', true); 
				else
					$('.PropReport').prop('checked', false);
				$('#masterDetails').empty();
				/*$('.masterDetails').empty();*/
				$('#masterDetails').append('<div class="row"><label class="col-xs-6" ><h4 class="detail_name">'+data[i].name+'</h4></label>'
			            +'<label class="col-xs-6 mytext detail_itemTrade">'+data[i].itemTrade+'</label></div> '
			            +'<div class="row"><label class="col-xs-6 mytext detail_code">'+data[i].code+'</label>'
			            +'<label class="col-xs-6 mytext detail_Desc">'+data[i].description+'</label></div>');
				
				/*$('.masterDetails').append('<div class="row"><label class="col-xs-6" ><h4 class="Name">'+data[i].name+'</h4></label>'
			            +'<label class="col-xs-6 mytext detail_itemTrade">'+data[i].itemTrade+'</label></div> '
			            +'<div class="row"><label class="col-xs-6 mytext matGroupName">'+data[i].materialGroup.name+'</label>'
			            +'<label class="col-xs-6 mytext Code">'+data[i].code+'</label></div>');*/
		          }
				

			
		$('#example').append('<li '+active+' onclick="showdetails('+data[i].materialId+')" id="'+data[i].materialId+'"> <a href="" class="" data-toggle="tab">'
		           +' <div class="col-md-12">'
		           +'  <label class="col-xs-6">'+data[i].name+'</label>'
		           +'   <label class="col-xs-6 mytext" data-id="'+data[i].materialId+'">'+data[i].itemTrade+'</label>'
		           +'  </div>'
		           +'  <div class="col-md-12">'
		           +'    <label class="col-xs-6">'+data[i].code+'</label>'
		           +'    <label class="col-xs-6 mytext2">'+data[i].description+'</label>'
		           +'  </div>'
		           +'  </a>'
		           +'  </li>');
		active="";
	}
	$('.example').paginathing();	
	
	$(".tabs-left li a label").text(function(index, currentText) {
	    return currentText.substr(0, 20);
	});
	$('.reportCount').html(data.length);
	}
function showdetails(id)
{
	debugger;
	$("#savenewmaterial").hide();
	$("#canclematerial").hide();
	$("#materialFormId").addClass('readonly');
	$.ajax({
		type : "POST",
        contentType : "application/json",
        url: "getMaterial/"+id,
        dataType:"json",
        async:false,
        success: function (data) {
        	if($.isEmptyObject(data))
        		{
        		  Alert.warn("Material details is empty-Add Details");
        		}
        	else
        	{
	        	debugger;
				$(".Name").val(data.name);
				$(".Code").val(data.code);
				$(".Description").val(data.description);
				/*$(".HSNCode select").val(data.hsnCode.hsnId);*/
				$(".ItemTrade").val(data.itemTrade);
				$(".Id").val(data.materialId);
				var hsn=data.hsnCode==null?'':data.hsnCode.name==null?'':data.hsnCode.name;
				var matGroupName=data.materialGroup==null?'':data.materialGroup.name==null?'':data.materialGroup.name;
				
				if(!$.isEmptyObject(data.uom))
					$(".uom").val(data.uom.uomId);
					if(!$.isEmptyObject(data.materialGroup))
					$(".matGroup").val(data.materialGroup.materialGroupId);
					if(!$.isEmptyObject(data.materialSubGroup))
					$(".matSubGroup").val(data.materialSubGroup.materialSubGroupId);
					if(!$.isEmptyObject(data.hsnCode))
						$("#hsn").val(data.hsnCode.hsnId);
				
				/*$(".HSNCode select").html(data.hsnCode.hsnId);
				$(".matGroupName").html(data.materialGroup.name);*/
				
				/*$(".uom select").val(data.uom.uomId);
				$(".matGroup select").val(data.materialGroup.materialGroupId);
				$(".matSubGroup select").val(data.materialSubGroup.materialSubGroupId);*/
				
					
				  	
				$("#bomVersionForm #materialName").val(data.name);
				$("#bomVersionForm #materialId").val(data.materialId);
				$("#materialVersionForm #material").empty();
				$("#materialVersionForm #material").append("<option value='"+data.materialId+"'>"+data.name+"</option>");
				
				if(data.isActive=="Y")
					$('.isActive').prop('checked', true); 
				else
					$('.isActive').prop('checked', false);
				if(data.isTestReport=="Y")
					$('.TestReport').prop('checked', true); 
				else
					$('.TestReport').prop('checked', false);
				if(data.isSSI=="Y")
					$('.SSI').prop('checked', true); 
				else
					$('.SSI').prop('checked', false);
				if(data.isNSIC=="Y")
					$('.NSIC').prop('checked', true); 
				else
					$('.NSIC').prop('checked', false);
				if(data.isPropReport=="Y")
					$('.PropReport').prop('checked', true); 
				else
					$('.PropReport').prop('checked', false);
				
				$('#masterDetails').append('<div class="row"><label class="col-xs-6" ><h4 class="detail_name">'+data.name+'</h4></label>'
						 +'<label class="col-xs-6 mytext detail_code">'+data.code+'</label></div>'
				            +'<div class="row"><label class="col-xs-6 mytext detail_itemTrade">'+data.itemTrade+'</label>'
				            +'<label class="col-xs-6 mytext detail_Desc">'+data.description+'</label></div>');
				
				/*$(".detail_name").html(data.name);
				$(".detail_code").html(data.code);
				$(".detail_Desc").html(data.description);
			  	$(".detail_itemTrade").html(data.itemTrade);*/

		        	}
		        	
        },
        error: function (e) {
        	Alert.warn("Exception :");
        }
    });
}
function deleteGtpResp(data){
	if(data.objectMap.result){
		Alert.info(data.objectMap.resultMessage);
	}else{
		Alert.warn(data.objectMap.resultMessage);
	}
}

function fetchMaterialList(pageNumber, pageSize, searchMode, searchValue){
	
	var response;
	$.ajax({
        type : "POST",
        contentType : "application/json",
        /*url: "getMaterialList/"+pageNumber+"/"+pageSize+'/'+searchMode+'/'+searchValue,*/
        url: "getMaterialList?pageNumber="+pageNumber+"&pageSize="+pageSize+"&searchMode="+searchMode+"&searchValue="+searchValue,
        dataType:"json",
        async:false,
        success: function (data) {
        	response = data;
        },
        error: function (e) {
			Alert.warn(e.message);
        }
    });
	return response;
}


