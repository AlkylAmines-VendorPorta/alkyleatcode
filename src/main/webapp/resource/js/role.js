$(document).ready(function(){ 
	renderList();
	$(".save").hide();
	$(".cancel").hide();
	
	$(".save").on("click", function(event) {

		event.preventDefault();
		submitIt("roleFormId", "saveRole", "saveRoleResp");
		return false;
	});
	$('.cancel').click(function(event) {
		event.preventDefault();
		var activeRoleId=$('.example').find('li.active').attr('id');
		if(activeRoleId!=undefined)
			{
			showdetails(activeRoleId);
			
			}
		else
			$('#roleFormId')[0].reset();
		
	});

	$("#delrole").on("click", function(event) {
		event.preventDefault();
		submitWithParam("deleteRole","Id" ,"deleteRoleResp");
	});

	$("#editRole").click(function() {
		event.preventDefault();
		$(".cancel").show();
		$(".save").show();
		$("#roleFormId").removeClass("readonly");
	});
});

function addRoleBtn(event){
	event.preventDefault();
	$(".Id").val('');
	$("#roleFormId").removeClass('readonly');
	$('.isActive').prop('checked', true); 
	$(".save").show();
	$(".cancel").show();
	$("#editRole").removeClass('readonly');
	$("#delrole").removeClass('readonly');
	$("#roleFormId").find('input,select,textarea').val("");

}
function deleteRoleResp(data){
	debugger;

	if(!$.isEmptyObject(data)){
		  var currentHsnDel=$('.example').find('li.active').attr('id');
		  
			if(data.hasError==false)
			{
				Alert.info(data.message);
				$('#'+currentHsnDel).remove();
				$('#roleFormId #Id').val("");
				$('#roleFormId #Name').val("");
				$('#roleFormId #Code').val("");
				$('#roleFormId #Description').val("");
				
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
				Alert.warn(data.responseMsg,"","error");
				}
			}
	
}
function saveRoleResp(data){
	debugger;
	if (!$.isEmptyObject(data)) {
		if (data.response.hasError == false) {

			Alert.info(data.response.message);
			appendRecentRecord(data);
			$(".save").hide();
			$(".cancel").hide();
			/*$(".editSave").hide();*/
			$("#roleFormId").addClass('readonly');
			$('#errorMsg').hide();
		} else {
			var errorLog = "";
			$.each(data.data, function(key, value) {
				errorLog = errorLog + value.errorMessage + "\n ;";
			});
			$('#errorMsg').show();
			$('#errorMsg').html("'" + errorLog + "'")
			Alert.warn(data.responseMsg, "", "error");
		}
	}
}

function appendRecentRecord(data){
	debugger;
	var currentRole = $('.example').find('li.active').attr('id');

	$("#Id").val(data.roleId);
	
	if(data.isActive == "Y")
		activeStatus="Active";
	else
		activeStatus="InActive";
	

	if (currentRole == data.roleId) {
		$('#' + currentRole + '').remove();
	} else {
		$('#' + currentRole + '').removeClass();
	}
	var active = " class='active'";
	$('#masterDetails').empty();
	$('#masterDetails').append('<div class="row"><label class="col-xs-6" ><h4 class="detail_Name">'+data.name+'</h4></label>'
            +'<label class="col-xs-6 mytext detail_Code">'+data.value+'</label></div> '
            +'<div class="row"><label class="col-xs-6 mytext detail_Desc">'+data.description+'</label>'
            +'<label class="col-xs-6 mytext detail_Active">'+activeStatus+'</label></div>');
      
	
$('#example').prepend('<li '+active+' onclick="showdetails('+data.roleId+')" id="'+ data.roleId + '"> <a href="" class="" data-toggle="tab">'
           +' <div class="col-md-12">'
           +'  <label class="col-xs-6">'+data.name+'</label>'
           +'   <label class="col-xs-6 mytext" data-Id="'+data.roleId+'">'+data.name+'</label>'
           +'  </div>'
           +'  <div class="col-md-12">'
           +'    <label class="col-xs-6">'+data.value+'</label>'
           +'    <label class="col-xs-6 mytext2">'+activeStatus+'</label>'
           +'  </div>'
           +'  </a>'
           +'  </li>');

	$(".Id").val(data.roleId);
	$('#errorMsg').hide();
	$('#roleFormId').addClass('readonly');
	$(".detail_Name").html(data.name);
	$(".detail_Code").html(data.value);
	$(".detail_Desc").html(data.description);
	$(".detail_Active").html(activeStatus);	
	$('.example').paginathing();
}
function renderList()
{ 
	$.ajax({
        type : "POST",
        contentType : "application/json",
        url: "getRoleList",
        dataType:"json",
        async:false,
        success: function (data) {
        	if($.isEmptyObject(data))
        		{
        		  Alert.warn("No Role present in List");
        		  $("#editRole").addClass('readonly');
        		  $("#delrole").addClass('readonly');
        		}
        	else
        	{
	        	appendList(data);
        	}
		        	
        },
        error: function (e) {
			Alert.warn("Exception :");
        }
    });
	}


function appendList(data)
{
	
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
				$(".Code").val(data[i].value);
				$(".Description").val(data[i].description);
				$(".Id").val(data[i].roleId);
				if(data[i].isActive=="Y")
				if(activeStatus=="Active")
					$('.isActive').prop('checked', true);
				else
					$('.isActive').prop('checked', false);
				
			$('#masterDetails').empty();
			$('#masterDetails').append('<div class="row"><label class="col-xs-6" ><h4 class="detail_Name">'+data[i].name+'</h4></label>'
		            +'<label class="col-xs-6 mytext detail_Code">'+data[i].value+'</label></div> '
		            +'<div class="row"><label class="col-xs-6 mytext detail_Desc">'+data[i].description+'</label>'
		            +'<label class="col-xs-6 mytext detail_Active">'+activeStatus+'</label></div>');
	          
			
				
			}
			
		$('#example').prepend('<li '+active+' onclick="showdetails('+data[i].roleId+')" id="'+ data[i].roleId + '"> <a href="" class="" data-toggle="tab">'
		           +' <div class="col-md-12">'
		           +'  <label class="col-xs-6">'+data[i].name+'</label>'
		           +'   <label class="col-xs-6 mytext" data-Id="'+data[i].roleId+'">'+data[i].name+'</label>'
		           +'  </div>'
		           +'  <div class="col-md-12">'
		           +'    <label class="col-xs-6">'+data[i].value+'</label>'
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
function showdetails(id)
{
	$(".save").hide();
	$(".cancel").hide();
	$('#roleFormId').addClass("readonly");
	$.ajax({
        type : "POST",
        contentType : "application/json",
        url: "getRoleById/"+id,
        dataType:"json",
        async:false,
        success: function (data) {
        	if($.isEmptyObject(data))
        		{
        		  Alert.warn("Role details is empty");
        		}
        	else
        	{
	        	 var activeStatus='';
	        	$(".Name").val(data.name);
				$(".Code").val(data.value);
				$(".Description").val(data.description);
				$(".Id").val(data.roleId);
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
				$(".detail_Code").html(data.value);
				$(".detail_Desc").html(data.description);
				$(".detail_Active").html(activeStatus);			
				
        	}
		        	
        },
        error: function (e){
			Alert.warn("Exception :");
        }
    });
	}