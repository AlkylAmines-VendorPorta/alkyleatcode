
$(document).ready(function(){ 
	debugger;
	//renderReferenceList();
	$("#saveReferenceList").hide();
	$("#cancelreferenceList").hide();
	$("#updateediteddepartment").hide();
	$(".addNewReferenceList").click(function(event){
		event.preventDefault();
		$("#referenceListMaster").removeClass("readonly");
		$("#addreferencelist").show();
		$(".cancle").show();
		$(".referenceCode").val("");
		$(".Name").val("");
		$(".Code").val("");
		$(".Description").val("");
		$(".referenceListId").val("");
		
	});
	
	$("#saveReferenceList").click(function(event){
		debugger;
		event.preventDefault();
		$("#referenceListMaster").addClass("readonly");
		$("#addreference").hide();
		$("#editnewreference").hide();
		$(".cancle").hide();
		//$("#editroles").show();
		submitIt("referenceListMaster","addNewReferenceList", "getResponseListDto");
	});
	
	$("#referenceListTabId").click(function(event){
		debugger;
		$('#referenceListMaster')[0].reset();
		var refid=$("#referenceId").val();
		submitWithParam('getReferenceListTab','referenceId','appendLists');
	});
	
	$('#cancelreferenceList').click(function(event) {
		debugger;
		event.preventDefault();
		var activeRefListId=$('.example').find('li.active').attr('id');
		if(activeRefListId!=undefined)
			{
			showdetail(activeRefListId);
			
			}
		else
			$('#referenceListMaster')[0].reset();
		
	});
	
	 $("#editReferenceList").click(function(){
		 event.preventDefault();
		 $("#cancelreferenceList").show();
			$("#saveReferenceList").show();
			 $("#referenceListMaster").removeClass("readonly");
		
		});
	 
	 $("#delReferenceList").click(function(){
			debugger;
			$(".referenceId").attr('name', 'referenceId');
			 
			/* submitIt("referencemaster","deleteReference", "getResponseDelete");*/
			 submitWithParam("deleteReferenceList","referenceListId", "getResponseDeleteList");
			
		});
	
	/*$(".ReferenceList").click(function(){
		$(".example").html("");
		debugger;
		renderReferenceList();
		
		
	});*/
	
	 $("#addreferencelist").click(function(event){
		debugger;
		event.preventDefault();
		$("#referenceListMaster").addClass("readonly");
		$("#addreferencelist").hide();
		$("#editnewreferencelist").hide();
		$("#cancelreferenceList").hide();
		//$("#editroles").show();
		submitIt("referenceListMaster","addNewReferenceList", "getResponse");
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

function addReferenceList(event){
	 debugger;
	 event.preventDefault();
		$("#referenceListId").val("");
		$("#referenceListMaster").removeClass('readonly');
		$('.isActive').prop('checked', true); 
		$("#saveReferenceList").show();
		$("#cancelreferenceList").show();
		$('#referenceListMaster')[0].reset();
		
}

function getResponseDeleteList(data){
	
	debugger;
	if(!$.isEmptyObject(data)){
	  
	  var currentRefListId = $('.example').find('li.active').attr('id');
	  if(data.hasError==false){
		  Alert.info(data.message);
		  $('#'+currentRefListId).remove();
			$('#referenceListMaster #referenceListId').val("");
			$('#referenceListMaster #referenceCode').val("");
			$('#referenceListMaster #Name').val("");
			$('#referenceListMaster #Code').val("");
			$('#referenceListMaster #Description').val("");
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
		Alert.warn(data.responseMsg,"","error");
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
        		  swal("No Reference present in List");
        		}
        	else
        	{
	        	appendLists(data);
        	}
		        	
        },
        error: function (e){
			swal("Exception :");
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
		    	$("#saveReferenceList").hide();
		    	$("#cancelreferenceList").hide();
		    	/*$(".editSave").hide();*/
		    	 $("#referenceListMaster").addClass('readonly');
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
		$("#referenceListId").val(data.referenceListId);
		$("#referenceIdnew").val(data.reference.referenceId);
		
		if(data.isActive == "Y")
			activeStatus="Active";
		else
			activeStatus="InActive";
		

		if (currentReferenceListId == data.referenceListId) {
			$('#' + currentReferenceListId + '').remove();
		} else {
			$('#' + currentReferenceListId + '').removeClass();
		}
		var active = " class='active'";
		
		$('#masterDetails').empty();
		$('#masterDetails').append('<div class="row"><label class="col-xs-6" ><h4 class="detail_Name">'+data.name+'</h4></label>'
	            +'<label class="col-xs-6 mytext detail_Code">'+data.code+'</label></div> '
	            +'<div class="row"><label class="col-xs-6 mytext detail_Desc">'+data.description+'</label>'
	            +'<label class="col-xs-6 mytext detail_Active">'+activeStatus+'</label></div>');
        
	$('#example').prepend('<li '+active+' onclick="showdetail('+data.referenceListId+')" id="'+ data.referenceListId + '"> <a href="" class="" data-toggle="tab">'
	           +' <div class="col-md-12">'
	           +'  <label class="col-xs-6">'+data.name+'</label>'
	           +'   <label class="col-xs-6 mytext" data-Id="'+data.referenceListId+'">'+data.name+'</label>'
	           +'  </div>'
	           +'  <div class="col-md-12">'
	           +'    <label class="col-xs-6">'+data.code+'</label>'
	           +'    <label class="col-xs-6 mytext2">'+activeStatus+'</label>'
	           +'  </div>'
	           +'  </a>'
	           +'  </li>');

		$(".Id").val(data.referenceListId);
		/*$('.saveBtn').removeAttr("onclick");
		$('.CancelBtn').removeAttr("onclick");*/
		$('#errorMsg').hide();
		$('#referenceListMaster').addClass('readonly');
		$(".detail_Name").html(data.name);
		$(".detail_Code").html(data.code);
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
	$("#addnewReference").removeAttr("onClick","addReference(event)");
	$("#addnewReference").attr("onClick","addReferenceList(event)");
	
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
			
			    $(".referenceCode").val(data[i].referenceCode);
				$(".Name").val(data[i].name);
				$(".Code").val(data[i].code);
				$(".Description").val(data[i].description);
				$("#referenceListId").val(data[i].referenceListId);
/*				$(".referenceId").val(data[i].referenceId);
*/				/*$("#referenceId").val(data[i].referenceListId);*/
				$("#referenceIdnew").val(data[i].reference.referenceId);
				if(activeStatus=="Active")
					$('.isActive').prop('checked', true);
				else
					$('.isActive').prop('checked', false);
				
			$('#masterDetails').empty();
			$('#masterDetails').append('<div class="row"><label class="col-xs-6" ><h4 class="detail_Name">'+data[i].name+'</h4></label>'
		            +'<label class="col-xs-6 mytext detail_Code">'+data[i].code+'</label></div> '
		            +'<div class="row"><label class="col-xs-6 mytext detail_Desc">'+data[i].description+'</label>'
		            +'<label class="col-xs-6 mytext detail_Active">'+activeStatus+'</label></div>');
	          
				
			}
			debugger;
		$('#example').prepend('<li '+active+' onclick="showdetail('+data[i].referenceListId+')" id="'+ data[i].referenceListId + '"> <a href="" class="" data-toggle="tab">'
		           +' <div class="col-md-12">'
		           +'  <label class="col-xs-6">'+data[i].name+'</label>'
		           +'   <label class="col-xs-6 mytext" data-Id="'+data[i].referenceListId+'">'+data[i].name+'</label>'
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
	    return currentText.substr(0, 20);
});
	$('.reportCount').html(data.length);
	}
function showdetail(referenceListId)
{
	debugger;
	$("#saveReferenceList").hide();
	$("#cancelreferenceList").hide();
	$('#referenceListMaster').addClass("readonly");
	$.ajax({
        type : "POST",
        contentType : "application/json",
        url: "getReferenceListById/"+referenceListId,
        dataType:"json",
        async:false,
        success: function (data) {
        	if($.isEmptyObject(data))
        		{
        		  swal("Reference details is empty");
        		}
        	else
        	{
        		var activeStatus='';
        		$("#referencemaster").addClass("readonly");
        		$(".referenceCode").val(data.referenceCode);
	        	$(".Name").val(data.name);
				$(".Code").val(data.code);
				$(".Description").val(data.description);
				$("#referenceId").val(data.referenceId);
				$("#referenceListId").val(data.referenceListId);
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
				$(".detail_Active").html(activeStatus);
			
        	}
		        	
        },
        error: function (e) {
			swal("Exception :");
        }
    });}