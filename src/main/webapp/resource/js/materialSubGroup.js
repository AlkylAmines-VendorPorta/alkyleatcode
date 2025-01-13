$(document).ready(function(){ 
	renderList();
	$(".save").hide();
	$(".cancel").hide();
	$("#editselectedmaterialsubgroups").hide();
	
	/*$(".addmaterialsubgroup").click(function(){
		$(".save").show();
		$(".cancel").show();
		$(".Name").val("");
		$(".Code").val("");
		$(".Description").val("");
		$(".matGroup").val("");
		$("#editselectedmaterialsubgroups").hide();
		$("#materialsubgroupmaster").removeClass("readonly");
		 $(".Name").removeClass('readonly');
			$(".Code").removeClass('readonly');
			$(".Description").removeClass('readonly');
	});*/
	/*$("#addnewmaterialsubgroup").click(function(){
		$("#editselectedmaterialsubgroups").hide();
		submitIt("materialSubGroupFormId","saveMaterialSubGroup", "getResponseDto");
		$(".save").hide();
		$(".cancel").hide();
		$("#materialsubgroupmaster").addClass("readonly");
	});*/
	
$(".save").on("click", function(event) {
		
		event.preventDefault();
		submitIt("materialSubGroupFormId", "saveMaterialSubGroup", "saveMaterialSubGroupResp");
		return false;
	});
	
	$("#deleteId").click(function(){
		event.preventDefault();
		 submitWithParam("deleteMaterialSubGroup","Id", "deleteMaterialSubGroupResp");
	});

	 $("#editId").on("click", function(event) {
		 event.preventDefault();
		 $(".save").show();
		  $(".cancel").show();
		  $("#materialSubGroupFormId").removeClass("readonly");
		
		});
	 $('.cancel').click(function(event) {
			event.preventDefault();
			var activeSubGroupId=$('.example').find('li.active').attr('id');
			if(activeSubGroupId!=undefined)
				{
				showdetails(activeSubGroupId);
				}
			else
				$('#materialSubGroupFormId')[0].reset();
			
		});
	/*$('#searchlitralid').on('keyup', function () {
	    var value = this.value;
	    $('#example li').hide().each(function () {
	        if ($(this).text().toUpperCase().search(value.toUpperCase()) > -1) {
	        	$(this).show();;
	        }
	    });
	});*/
});
 
function deleteMaterialSubGroupResp(data){
	debugger;
	 if(!$.isEmptyObject(data)){
		  var currentSubDel=$('.example').find('li.active').attr('id');
		  
			if(data.hasError==false)
			{
				Alert.info(data.message);
				$('#'+currentSubDel).remove();
				$('#materialSubGroupFormId #Id').val("");
				$('#materialSubGroupFormId #Name').val("");
				$('#materialSubGroupFormId #Code').val("");
				$('#materialSubGroupFormId #Description').val("");
				$('#materialSubGroupFormId #materialGroup').val("");
				/*$('#masterDetails').empty();*/
		    	$('#errorMsg').hide();
		    	$(".detail_Name").html('');
				$(".detail_Code").html('');
				$(".detail_Desc").html('');
				$(".detail_Active").html('');
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


 function addMaterialSubGroup(event){
	 debugger;
	 event.preventDefault();
	  $(".Id").val("");
	  $("#materialSubGroupFormId").removeClass('readonly');
	  $('.isActive').prop('checked', true); 
	  $(".save").show();
	  $(".cancel").show();
	  $("#deleteId").removeClass('readonly');
		$("#editId").removeClass('readonly');
	  $("#materialSubGroupFormId").find('input,select,textarea').val("");
 }
function renderList()
{ 
	debugger;
	$.ajax({
        type : "POST",
        contentType : "application/json",
        url: "getMaterialSubGroupList",
        dataType:"json",
        async:false,
        success: function (data) {
        	if($.isEmptyObject(data))
        		{
        		  Alert.warn("No MaterialSubGroup present in List");
        		  $("#deleteId").addClass('readonly');
        			$("#editId").addClass('readonly');
        		}
        	else
        	{
	        	appendList(data);
        	}	
        },
        error: function (e) {
			Alert.info("Exception :");
        }
    });
	}
function saveMaterialSubGroupResp(data){
	debugger;
	 if(!$.isEmptyObject(data)){
			if(data.response.hasError==false)
			{
				
		    	Alert.info(data.response.message);
		    	appendRecentRecord(data);
		    	$(".save").hide();
		    	$(".cancel").hide();
		    	/*$(".editSave").hide();*/
		    	 $("#materialSubGroupFormId").addClass('readonly');
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

	 
	  var currentSubGroupId=$('.example').find('li.active').attr('id');
	  var matGroupName='';
	  var matGroup=$('#materialGroup').find('option:selected').text();
	  $("#Id").val(data.materialSubGroupId);
	  if(data.isActive=="Y")
			activeStatus="Active";
		else
			activeStatus="InActive";
	  
		var matGroupName=data.materialGroup==null?'':data.materialGroup.name==null?'':data.materialGroup.name;
      
	  if(!$.isEmptyObject(data.materialGroup))
		{  
		  $("#materialGroup").val(data.materialGroup.materialGroupId);
		}
	   
	  if(currentSubGroupId==data.materialSubGroupId){
		  $('#'+currentSubGroupId+'').remove();
	  }
	  else{
		  $('#'+currentSubGroupId+'').removeClass();
	  }
	  var active=" class='active'";
	  if(matGroupName!=null && matGroupName!=""){
		  matGroupName=matGroupName
	  }
	  else{
		  matGroupName=matGroup;
	  }
	  
	  $('#masterDetails').empty();
		$('#masterDetails').append('<div class="row"><label class="col-xs-6" ><h4 class="detail_Name">'+data.name+'</h4></label>'
	            +'<label class="col-xs-6 mytext detail_Code">'+data.code+'</label></div> '
	            +'<div class="row"><label class="col-xs-6 mytext detail_Desc">'+data.description+'</label>'
	            +'<label class="col-xs-6 mytext detail_Active">'+matGroupName+'</label></div>');
			
	$('#example').prepend('<li '+active+' onclick="showdetails('+data.materialSubGroupId+')" id="'+data.materialSubGroupId+'"> <a href="" class="" data-toggle="tab">'
	           +' <div class="col-md-12">'
	           +'  <label class="col-xs-6">'+data.name+'</label>'
	           +'   <label class="col-xs-6 mytext" data-id="'+data.materialSubGroupId+'">'+data.description+'</label>'
	           +'  </div>'
	           +'  <div class="col-md-12">'
	           +'    <label class="col-xs-6">'+data.code+'</label>'
	           +'    <label class="col-xs-6 mytext2">'+activeStatus+'</label>'
	           +'  </div>'
	           +'  </a>'
	           +'  </li>');
	  
	  $(".Id").val(data.materialSubGroupId);
	 	/*$('.saveBtn').removeAttr("onclick");
	 	$('.CancelBtn').removeAttr("onclick");*/
	 	$('#errorMsg').hide();
	 	$('#materialSubGroupFormId').addClass('readonly');
	    
	 	 
	 	$(".detail_Name").html(data.name);
		$(".detail_Code").html(data.code);
		$(".detail_Desc").html(data.description);
	 		$(".detail_Active").html(matGroupName);
	 		$('.example').paginathing();


}

function appendList(data)
{
	debugger;
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
				$(".Id").val(data[i].materialSubGroupId);
				
				if(!$.isEmptyObject(data[i].materialGroup))
				$(".materialGroup").val(data[i].materialGroup.materialGroupId);
				
				if(activeStatus=="Active")
					$('.isActive').prop('checked', true);
				else
					$('.isActive').prop('checked', false);
				
			$('#masterDetails').empty();
			$('#masterDetails').append('<div class="row"><label class="col-xs-6" ><h4 class="detail_Name">'+data[i].name+'</h4></label>'
		            +'<label class="col-xs-6 mytext detail_Code">'+data[i].code+'</label></div> '
		            +'<div class="row"><label class="col-xs-6 mytext detail_Desc">'+data[i].description+'</label>'
		            +'<label class="col-xs-6 mytext detail_Active">'+data[i].materialGroup.name+'</label></div>');
	          
				
			}
			
		$('#example').append('<li '+active+' onclick="showdetails('+data[i].materialSubGroupId+')" id="'+data[i].materialSubGroupId+'"> <a href="" class="" data-toggle="tab">'
		           +' <div class="col-md-12">'
		           +'  <label class="col-xs-6">'+data[i].name+'</label>'
		           +'   <label class="col-xs-6 mytext" data-id="'+data[i].materialSubGroupId+'">'+data[i].description+'</label>'
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
	debugger;
	$(".save").hide();
	$(".cancel").hide();
	$("#materialSubGroupFormId").addClass('readonly');
	$.ajax({
        type : "POST",
        contentType : "application/json",
        url: "getMaterialSubGroup/"+id,
        dataType:"json",
        async:false,
        success: function (data) {
        	if($.isEmptyObject(data))
        		{
        		  Alert.info("MaterialSubGroup details is empty");
        		}
        	else
        	{
	        	var activeStatus='';
				$(".Name").val(data.name);
				$(".Code").val(data.code);
				$(".Description").val(data.description);
				$(".Id").val(data.materialSubGroupId);
				var matGroupName=data.materialGroup==null?'':data.materialGroup.name==null?'':data.materialGroup.name;
				/*var taxCategoryName=data.taxcategory==null?'':data.taxcategory.name==null?'':data.taxcategory.name;*/
				if(!$.isEmptyObject(data.materialGroup))
					
						$("#materialGroup").val(data.materialGroup.materialGroupId);
						/*$(".detail_Active").html(data.materialGroup.name);*/
					
				/*else
					$(".detail_Active").html("");
				*/
				if(data.isActive=="Y")
				{
					$('.isActive').prop('checked', true);
					activeStatus="Active";
				}
				else
				{
					$('.isActive').prop('checked', false);
					activeStatus="InActive";
				}
				
				$(".detail_Name").html(data.name);
				$(".detail_Code").html(data.code);
				$(".detail_Desc").html(data.description);
				$(".detail_Active").html(matGroupName);
				/*$(".detail_Active").html(activeStatus);*/
				
				
        	}
		        	
        },
        error: function (e) {
			Alert.info("Exception :");
        }
    });}
