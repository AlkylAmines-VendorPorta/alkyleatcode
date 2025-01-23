
$(document).ready(function(){ 
	debugger;
	//renderReferenceList();
	$("#saveofficeType").hide();
	$("#canceLOfficeType").hide();
	$("#delOfficeTypeList").hide();
	$("#editOfficeTypeList").hide();
	
	
	$(".addofficeType").click(function(event){
		event.preventDefault();
		$("#officeTypeMaster").removeClass("readonly");
		$("#saveofficeType").show();
		$("#canceLOfficeType").show();
		$(".Name").val("");
		$(".Code").val("");
		$(".Description").val("");
		$(".officeTypeId").val("");
		
	});
	
	$("#saveofficeType").click(function(event){
		debugger;
		event.preventDefault();
		$("#officeTypeMaster").addClass("readonly");
		$("#addnewlocation").hide();
		$(".cancle").hide();
		//$("#editroles").show();
		submitIt("officeTypeMaster","addNewOfficeType", "getResponseListDto");
	});
	
	$('#canceLOfficeType').click(function(event) {
		event.preventDefault();
		var activeOfficeId=$('.example').find('li.active').attr('id');
		if(activeOfficeId!=undefined)
			{
			showdetail(activeOfficeId);
			
			}
		else
			$('#officeTypeMaster')[0].reset();
		
	});
	
	$("#officeTypeTabId").click(function(event){
		debugger;
		$('#officeTypeMaster')[0].reset();
		var refid=$("#locationId").val();
		$("#locationIdnew").val(refid)
		submitWithParam('getofficeTypeListTab','locationId','appendLists');
		
	});
	
	
	 $("#editOfficeTypeList").click(function(){
		 event.preventDefault();
		 $("#canceLOfficeType").show();
			$("#saveofficeType").show();
			 $("#officeTypeMaster").removeClass("readonly");
		
		});
	 
	 $("#delOfficeTypeList").click(function(){
			debugger;
			$(".referenceId").attr('name', 'referenceId');
			 
			/* submitIt("referencemaster","deleteReference", "getResponseDelete");*/
			 submitWithParam("deleteOfficeType","officeTypeId", "getResponseDeleteList");
			
		});
	
	/*$(".ReferenceList").click(function(){
		$(".example").html("");
		debugger;
		renderReferenceList();
		
		
	});*/
	
	 $("#addofficeType").click(function(event){
		debugger;
		event.preventDefault();
		$("#officeTypeMaster").addClass("readonly");
		$("#addofficeType").hide();
		$("#editOfficeTypeList").hide();
		$("#canceLOfficeType").hide();
	});
	
	 /*	$("#delreference").click(function(){
		debugger;
		 $(".referencelistId").attr('name', 'referencelistId');
		 
		 submitIt("referencemaster","deleteReference", "getResponseDelete");
		 submitWithParam("deleteReference","referenceId", "getResponseDelete");
		
	});
	
	$("#editreference").click(function(){
		 $("#referencemaster").removeClass("readonly");
		  $(".referencelistId").attr('name', 'referencelistId');
		  $("#editnewreference").show();
		  	$("#addreference").hide();
		  	$(".cancle").show();
		
		});
	
	 $("#editnewreference").click(function(){
		   $("#referencemaster").addClass("readonly");
		   $("#addreference").hide();
		   $("#editnewreference").hide();
		   $(".cancle").hide();
		   submitIt("referencemaster","editReference", "getResponseUpdate");
			
		});
	*/
	
});

function addofficeType(event){
	 debugger;
	 event.preventDefault();
		$("#officeTypeId").val("");
		$("#officeTypeMaster").removeClass('readonly');
		$('.isActive').prop('checked', true); 
		$("#saveofficeType").show();
		$("#canceLOfficeType").show();
		$('#officeTypeMaster')[0].reset();
		
}

function getResponseDeleteList(data){
	
	debugger;
	if(!$.isEmptyObject(data)){
	  
	  var currentRefListId = $('.example').find('li.active').attr('id');
	  if(data.hasError==false){
		  Alert.info(data.message);
		  $('#'+currentRefListId).remove();
			$('#officeTypeMaster #officeTypeId').val("");
			$('#officeTypeMaster #Name').val("");
			$('#officeTypeMaster #Description').val("");
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

function renderReferenceList()
{ 
	var referenceListId=$("#referenceId").val();
	debugger;
	$.ajax({
        type : "POST",
        contentType : "application/json",
        url: "getReferenceListTab/"+referenceListId,
        dataType:"json",
        async:false,
        success: function (data) {
        	if($.isEmptyObject(data))
        		{
        		  Alert.warn("No Office Type present in List");
        		}
        	else
        	{
	        	appendLists(data);
        	}
		        	
        },
        error: function (e){
        	Alert.warn("Exception :");
        }
    });
	}
function getResponseListDto(data){
	
 debugger;
	 
	 if(!$.isEmptyObject(data)){
			if(data.response.hasError==false)
			{
				
		    	Alert.info(data.response.message);
		    	appendRecentRecordList(data);
		    	$("#saveofficeType").hide();
		    	$("#canceLOfficeType").hide();
		    	/*$(".editSave").hide();*/
		    	 $("#officeTypeMaster").addClass('readonly');
		    	 $("#addnewlocation").show();
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
	
	/*if($.isEmptyObject(data))
	{
	  swal("Some Thing went wrong");
	}
else
{
	 swal("Role inserted successfully");
	 renderReferenceList();
}
}	*/
	/*debugger;
	if(!$.isEmptyObject(data.objectMap.referenceList)){
		if(data.objectMap.result){
			Alert.info(data.objectMap.resultMessage);
			renderReferenceList();
		}else{
			Alert.warn(data.objectMap.resultMessage);
		}
	}else{
		Alert.warn("Someting went wrong");
	}*/

/* function getResponseUpdate(data){
	debugger;
	if(!$.isEmptyObject(data.objectMap.reference)){
		if(data.objectMap.result){
			Alert.info(data.objectMap.resultMessage);
			renderReferenceList();
		}else{
			Alert.warn(data.objectMap.resultMessage);
		}
	}else{
		Alert.warn("Someting went wrong");
	}
}
function getResponseDelete(data){
	debugger;
	if(data.hasError==false)
	{
		Alert.info(data.message);
		renderReferenceList();
		var currentPartnerAddressId=$('ul #example').find('li.active').attr('id');
		$('#'+currentPartnerAddressId).remove();
		$('#referencemaster')[0].reset();
		$("#referenceId").val("");
		event.preventDefault();
	}else{
		Alert.warn(data.message);
	}
	
	
	debugger;
	if(!$.isEmptyObject(data.objectMap.reference)){
		if(data.objectMap.result){
			Alert.info(data.objectMap.resultMessage);
			renderList();
		}else{
			Alert.warn(data.objectMap.resultMessage);
		}
	}else{
		Alert.warn("Someting went wrong");
	}
	
	
}*/

function appendRecentRecordList(data){
	  debugger;
	  //var currentDepartmentId = $('.example').find('li.active').attr('id');
	  var currentReferenceListId = $('.example').find('li.active').attr('id');
		$("#officeTypeId").val(data.officeLocationId);
		$("#locationIdnew").val(data.locationTypeId);
		
		if(data.isActive == "Y")
			activeStatus="Active";
		else
			activeStatus="InActive";
		

		if (currentReferenceListId == data.officeLocationId) {
			$('#' + currentReferenceListId + '').remove();
		} else {
			$('#' + currentReferenceListId + '').removeClass();
		}
		var active = " class='active'";
		
		$('#masterDetails').empty();
		$('#masterDetails').append('<div class="row"><label class="col-xs-6" ><h4 class="detail_Name">'+data.name+'</h4></label>'
	           /* +'<label class="col-xs-6 mytext detail_Code">'+data.locationTypeRef+'</label></div> '*/
	            +'<label class="col-xs-6 mytext detail_Desc">'+data.description+'</label>'
	            +'<label class="col-xs-12 mytext detail_Active">'+activeStatus+'</label></div>');
        
	$('#example').prepend('<li '+active+' onclick="showdetail('+data.officeLocationId+')" id="'+ data.officeLocationId + '"> <a href="" class="" data-toggle="tab">'
	           +' <div class="col-md-12">'
	           +'  <label class="col-xs-6">'+data.name+'</label>'
	           +'   <label class="col-xs-6 mytext" data-Id="'+data.officeLocationId+'">'+data.name+'</label>'
	           +'  </div>'
	           +'  <div class="col-md-12">'
	          /* +'    <label class="col-xs-6">'+data.locationTypeRef+'</label>'*/
	           +'    <label class="col-xs-6 mytext2">'+activeStatus+'</label>'
	           +'  </div>'
	           +'  </a>'
	           +'  </li>');

		$(".Id").val(data.officeLocationId);
		/*$('.saveBtn').removeAttr("onclick");
		$('.CancelBtn').removeAttr("onclick");*/
		$('#errorMsg').hide();
		$('#officeTypeMaster').addClass('readonly');
		$(".detail_Name").html(data.name);
		$(".detail_Code").html(data.locationTypeRef);
		$(".detail_Desc").html(data.description);
		$(".detail_Active").html(activeStatus);
		$('.example').paginathing();

}





/*function addReferenceList(event){
	 debugger;
	 event.preventDefault();
		$(".referenceId").val("");
		$("#referenceListMaster").removeClass('readonly');
		$('.isActive').prop('checked', true); 
		$(".save").show();
		$(".cancel").show();
		$("#referenceListMaster").find('input,select,textarea').val("");
}*/


function appendLists(data)
{
	debugger;
	$("#addnewlocation").removeAttr("onClick","addLocation(event)");
	$("#addnewlocation").attr("onClick","addofficeType(event)");
	$("#delOfficeTypeList").show();
	$("#editOfficeTypeList").show();
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
			
			    $("#officeTypeId").val(data[i].officeLocationId);
				$(".Name").val(data[i].name);
				$(".Code").val(data[i].locationTypeRef);
				$(".Description").val(data[i].description);
				$("#locationIdnew").val(data[i].locationTypeId);

				
				if(activeStatus=="Active")
					$('.isActive').prop('checked', true);
				else
					$('.isActive').prop('checked', false);
				
			$('#masterDetails').empty();
			$('#masterDetails').append('<div class="row"><label class="col-xs-6" ><h4 class="detail_Name">'+data[i].name+'</h4></label>'
		           /* +'<label class="col-xs-6 mytext detail_Code">'+data[i].locationTypeRef+'</label></div> '*/
		            +'<label class="col-xs-6 mytext detail_Desc">'+data[i].description+'</label>'
		            +'<label class="col-xs-12 mytext detail_Active">'+activeStatus+'</label></div>');
	          
				
			}
			debugger;
		$('#example').prepend('<li '+active+' onclick="showdetail('+data[i].officeLocationId+')" id="'+ data[i].officeLocationId + '"> <a href="" class="" data-toggle="tab">'
		           +' <div class="col-md-12">'
		           +'  <label class="col-xs-6">'+data[i].name+'</label>'
		           +'   <label class="col-xs-6 mytext" data-Id="'+data[i].officeLocationId+'">'+data[i].name+'</label>'
		           +'  </div>'
		           +'  <div class="col-md-12">'
		          /* +'    <label class="col-xs-6">'+data[i].locationTypeRef+'</label>'*/
		           +'    <label class="col-xs-6 mytext2">'+activeStatus+'</label>'
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
function showdetail(officeLocationId)
{
	debugger;
	$("#saveofficeType").hide();
	$("#canceLOfficeType").hide();
	$.ajax({
        type : "POST",
        contentType : "application/json",
        url: "getofficeTypeById/"+officeLocationId,
        dataType:"json",
        async:false,
        success: function (data) {
        	if($.isEmptyObject(data))
        		{
        		Alert.warn("Office Type details is empty");
        		}
        	else
        	{
        		var activeStatus='';
        		$("#officeTypeMaster").addClass("readonly");
	        	$(".Name").val(data.name);
				$(".Code").val(data.locationTypeRef);
				$(".Description").val(data.description);
				$("#locationIdnew").val(data.locationTypeId);
				$("#officeTypeId").val(data.officeLocationId);
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
				$(".detail_Code").html(data.locationTypeRef);
				$(".detail_Desc").html(data.description);
				$(".detail_Active").html(activeStatus);
			
        	}
		        	
        },
        error: function (e) {
        	Alert.warn("Exception :");
        }
    });}