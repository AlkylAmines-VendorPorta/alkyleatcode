
$(document).ready(function(){ 
	debugger;
	renderList();
	$("#saveReference").hide();
	$("#cancelReference").hide();
	$("#updateediteddepartment").hide();
	/*$(".addNewReference").click(function(){
		$("#referencemaster").removeClass("readonly");
		$("#addreference").show();
		$(".cancle").show();
		$(".Name").val("");
		$(".Code").val("");
		$(".Description").val("");
		$("#referenceId").val("");
		
		
		
	});*/
	
	$('#cancelReference').click(function(event) {
		event.preventDefault();
		var activeRefId=$('.example').find('li.active').attr('id');
		if(activeRefId!=undefined)
			{
			showdetails(activeRefId);
			
			}
		else
			$('#referencemaster')[0].reset();
		
	});

	 $("#editreference").click(function(){
		 event.preventDefault();
		 $("#cancelReference").show();
			$("#saveReference").show();
			 $("#referencemaster").removeClass("readonly");
		
		});
	
	$("#saveReference").click(function(){
		debugger;
		$("#referencemaster").addClass("readonly");
		$("#addreference").hide();
		$("#editnewreference").hide();
		$("#cancelReference").hide();
		$("#referenceListTabId").removeClass('readonly');
		//$("#editroles").show();
		submitIt("referencemaster","addNewReference", "getResponseDto");
	});
	
	$("#delreference").click(function(){
		debugger;
		$(".referenceId").attr('name', 'referenceId');
		 
		/* submitIt("referencemaster","deleteReference", "getResponseDelete");*/
		 submitWithParam("deleteReference","referenceId", "getResponseDelete");
		
	});
	
	/*$("#editreference").click(function(){
		 $("#referencemaster").removeClass("readonly");
		  $(".Id").attr('name', 'referenceId');
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
			
		});*/
	
	/*$("#savedepartment").click(function(){
		$("#departmentmaster").addClass("readonly");
		$(".save").hide();
		$(".cancle").hide();
		$("#updateediteddepartment").hide();
		submitIt("departmentmaster","addNewDepartment", "getResponseDto");
	});
	$("#deldepartment").click(function(){
		
		 $(".Id").attr('name', 'referenceId');
		 submitIt("departmentmaster","deleteDepartment", "getResponseDelete");
		
		
	});
	 $("#editdepartment").click(function(){
		 $("#departmentmaster").removeClass("readonly");
		  $(".Id").attr('name', 'referenceId');
		  $("#updateediteddepartment").show();
		  	$("#savedepartment").hide();
		  	$(".cancle").show();
		
		});
	
   $("#updateediteddepartment").click(function(){
	   $("#departmentmaster").addClass("readonly");
	   $("#updateediteddepartment").hide();
	   $(".cancle").hide();
	   submitIt("departmentmaster","updateDedpartment", "getResponseUpdate");
		
	});
	$('#searchlitralid').on('keyup', function () {
	    var value = this.value;
	    $('#example li').hide().each(function () {
	        if ($(this).text().toUpperCase().search(value.toUpperCase()) > -1) {
	        	$(this).show();;
	        }
	    });
	});*/
});

function addReference(event){
	 debugger;
	 event.preventDefault();
		$(".referenceId").val("");
		$("#referencemaster").removeClass('readonly');
		$('.isActive').prop('checked', true); 
		$("#saveReference").show();
		$("#cancelReference").show();
		$("#referenceListTabId").addClass('readonly');
		$("#referencemaster").find('input,select,textarea').val("");
}


function renderList()
{ 
	debugger;
	$("#addnewReference").removeAttr("onClick","addReferenceList(event)");
	$("#addnewReference").attr("onclick","addReference(event)");
	
	$(".example").html("");
	
	$.ajax({
        type : "POST",
        contentType : "application/json",
        url: "getReferenceList",
        dataType:"json",
        async:false,
        success: function (data) {
        	if($.isEmptyObject(data))
        		{
        		Alert.warn("No Reference present in List");
        		}
        	else
        	{
	        	appendList(data);
        	}
		        	
        },
        error: function (e){
        	Alert.warn("Exception :");
        }
    });
	}
function getResponseDto(data){
 debugger;
	 
	 if(!$.isEmptyObject(data)){
			if(data.response.hasError==false)
			{
				
		    	Alert.info(data.response.message);
		    	appendRecentRecord(data);
		    	$("#saveReference").hide();
		    	$("#cancelReference").hide();
		    	/*$(".editSave").hide();*/
		    	 $("#referencemaster").addClass('readonly');
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
function getResponseUpdate(data){
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
}
function getResponseDelete(data){
	
	debugger;
	if(!$.isEmptyObject(data)){
	  
	  var currentRefId = $('.example').find('li.active').attr('id');
	  if(data.hasError==false){
		  Alert.info(data.message);
		  $('#'+currentRefId).remove();
			$('#referencemaster #referenceId').val("");
			$('#referencemaster #Name').val("");
			$('#referencemaster #Code').val("");
			$('#referencemaster #Description').val("");
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
	
	/*debugger;
	if(data.hasError==false)
	{
		Alert.info(data.message);
		renderList();
		var currentPartnerAddressId=$('ul #example').find('li.active').attr('id');
		$('#'+currentPartnerAddressId).remove();
		$('#referencemaster')[0].reset();
		$("#referenceId").val("");
		event.preventDefault();
	}else{
		Alert.warn(data.message);
	}
	*/
	
/*	debugger;
	if(!$.isEmptyObject(data.objectMap.reference)){
		if(data.objectMap.result){
			Alert.info(data.objectMap.resultMessage);
			renderList();
		}else{
			Alert.warn(data.objectMap.resultMessage);
		}
	}else{
		Alert.warn("Someting went wrong");
	}*/
	
	
}


function appendRecentRecord(data){
	  debugger;
	  //var currentDepartmentId = $('.example').find('li.active').attr('id');
	  var currentReferenceId = $('.example').find('li.active').attr('id');
		$(".referenceId").val(data.referenceId);
		
		if(data.isActive == "Y")
			activeStatus="Active";
		else
			activeStatus="InActive";
		

		if (currentReferenceId == data.referenceId) {
			$('#' + currentReferenceId + '').remove();
		} else {
			$('#' + currentReferenceId + '').removeClass();
		}
		var active = " class='active'";
		
		$('#masterDetails').empty();
		$('#masterDetails').append('<div class="row"><label class="col-xs-6" ><h4 class="detail_Name">'+data.name+'</h4></label>'
	            +'<label class="col-xs-6 mytext detail_Code">'+data.code+'</label></div> '
	            +'<div class="row"><label class="col-xs-6 mytext detail_Desc">'+data.description+'</label>'
	            +'<label class="col-xs-6 mytext detail_Active">'+activeStatus+'</label></div>');
          
	$('#example').prepend('<li '+active+' onclick="showdetails('+data.referenceId+')" id="'+ data.referenceId + '"> <a href="" class="" data-toggle="tab">'
	           +' <div class="col-md-12">'
	           +'  <label class="col-xs-6">'+data.name+'</label>'
	           +'   <label class="col-xs-6 mytext" data-Id="'+data.referenceId+'">'+data.name+'</label>'
	           +'  </div>'
	           +'  <div class="col-md-12">'
	           +'    <label class="col-xs-6">'+data.code+'</label>'
	           +'    <label class="col-xs-6 mytext2">'+activeStatus+'</label>'
	           +'  </div>'
	           +'  </a>'
	           +'  </li>');

		/*$(".Id").val(data.referenceId);*/
		/*$('.saveBtn').removeAttr("onclick");
		$('.CancelBtn').removeAttr("onclick");*/
		$('#errorMsg').hide();
		$('#referencemaster').addClass('readonly');
		$(".detail_Name").html(data.name);
		$(".detail_Code").html(data.code);
		$(".detail_Desc").html(data.description);
		$(".detail_Active").html(activeStatus);
		$('.example').paginathing();

}





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
			$('#referenceListMaster #referenceId').val(data[i].referenceId);
				$(".Name").val(data[i].name);
				$(".Code").val(data[i].code);
				$(".Description").val(data[i].description);
				$(".referenceId").val(data[i].referenceId);
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
		$('#example').append('<li '+active+' onclick="showdetails('+data[i].referenceId+')" id="'+ data[i].referenceId + '"> <a href="" class="" data-toggle="tab">'
		           +' <div class="col-md-12">'
		           +'  <label class="col-xs-6">'+data[i].name+'</label>'
		           +'   <label class="col-xs-6 mytext" data-Id="'+data[i].referenceId+'">'+data[i].name+'</label>'
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
function showdetails(referenceId)
{
	debugger;
	$("#saveReference").hide();
	$("#cancelReference").hide();
	$('#referencemaster').addClass("readonly");
	$("#referenceListTabId").removeClass('readonly');
	
	$.ajax({
        type : "POST",
        contentType : "application/json",
        url: "getReferenceById/"+referenceId,
        dataType:"json",
        async:false,
        success: function (data) {
        	if($.isEmptyObject(data))
        		{
        		  Alter.warn("Reference details is empty");
        		}
        	else
        	{
        		var activeStatus='';
        		$("#referencemaster").addClass("readonly");
        		$('#referenceListMaster #referenceId').val(data.referenceId);
	        	$(".Name").val(data.name);
				$(".Code").val(data.code);
				$(".Description").val(data.description);
				$(".referenceId").val(data.referenceId);
				/*$("referenceId").val(data.referenceId);*/
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
        	Alert.warn("Exception :");
        }
    });}