
$(document).ready(function(){ 
	
	renderList();
	$(".save").hide();
	$(".cancel").hide();
	/*$("#updateediteddepartment").hide();*/
	/*$(".addnewdepartment").click(function(){
		$("#departmentFormId").removeClass("readonly");
		$("#savedepartment").show();
		$(".cancel").show();
		$(".Name").val("");
		$(".Code").val("");
		$(".Description").val("");
		$("#updateediteddepartment").hide();
		 $(".Name").removeClass('readonly');
			$(".Code").removeClass('readonly');
			$(".Description").removeClass('readonly');
	});*/
	$(".save").on("click",function(){

		event.preventDefault();
		submitIt("departmentFormId", "saveDepartment", "saveDepartmentResp");
		return false;
	});
	$("#deldepartment").on("click",function(){
		debugger;
		event.preventDefault();
		 submitWithParam("deleteDepartment","Id", "deleteDepartmentResp");
		
	});
	$('.cancel').click(function(event) {
		event.preventDefault();
		var activeDeptId=$('.example').find('li.active').attr('id');
		if(activeDeptId!=undefined)
			{
			showdetails(activeDeptId);
			
			}
		else
			$('#departmentFormId')[0].reset();
		
	});

	 $("#editdepartment").click(function(){
		 event.preventDefault();
		 $(".cancel").show();
			$(".save").show();
			 $("#departmentFormId").removeClass("readonly");
		
		});
	
   /*$("#updateediteddepartment").click(function(){
	   $("#departmentFormId").addClass("readonly");
	   $("#updateediteddepartment").hide();
	   $(".cancel").hide();
	   submitIt("departmentFormId","updateDedpartment", "getResponseUpdate");
		
	});*/
	/*$('#searchlitralid').on('keyup', function () {
	    var value = this.value;
	    $('#example li').hide().each(function () {
	        if ($(this).text().toUpperCase().search(value.toUpperCase()) > -1) {
	        	$(this).show();;
	        }
	    });
	});*/
});
 function addDepartment(event){
	 debugger;
	 event.preventDefault();
		$(".Id").val("");
		$("#departmentFormId").removeClass('readonly');
		$('.isActive').prop('checked', true); 
		$(".save").show();
		$(".cancel").show();
		$("#editdepartment").removeClass('readonly');
		$("#deldepartment").removeClass('readonly');
		$("#departmentFormId").find('input,select,textarea').val("");
 }
function deleteDepartmentResp(data){
	debugger;
	if(!$.isEmptyObject(data)){
	  
	  var currentDepId = $('.example').find('li.active').attr('id');
	  if(data.hasError==false){
		  Alert.info(data.message);
		  $('#'+currentDepId).remove();
			$('#departmentFormId #Id').val("");
			$('#departmentFormId #Name').val("");
			$('#departmentFormId #Code').val("");
			$('#departmentFormId #Description').val("");
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
 function saveDepartmentResp(data){
	 debugger;

		if (!$.isEmptyObject(data)) {
			if (data.response.hasError == false) {

				Alert.info(data.response.message);
				appendRecentRecord(data);
				$(".save").hide();
				$(".cancel").hide();
				/*$(".editSave").hide();*/
				$("#departmentFormId").addClass('readonly');
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
	  //var currentDepartmentId = $('.example').find('li.active').attr('id');
	  var currentDeparId = $('.example').find('li.active').attr('id');
		$("#Id").val(data.departmentId);
		
		if(data.isActive == "Y")
			activeStatus="Active";
		else
			activeStatus="InActive";
		

		if (currentDeparId == data.departmentId) {
			$('#' + currentDeparId + '').remove();
		} else {
			$('#' + currentDeparId + '').removeClass();
		}
		var active = " class='active'";
		
		$('#masterDetails').empty();
		$('#masterDetails').append('<div class="row"><label class="col-xs-6" ><h4 class="detail_Name">'+data.name+'</h4></label>'
	            +'<label class="col-xs-6 mytext detail_Code">'+data.code+'</label></div> '
	            +'<div class="row"><label class="col-xs-6 mytext detail_Desc">'+data.description+'</label>'
	            +'<label class="col-xs-6 mytext detail_Active">'+activeStatus+'</label></div>');
          
	$('#example').prepend('<li '+active+' onclick="showdetails('+data.departmentId+')" id="'+ data.departmentId + '"> <a href="" class="" data-toggle="tab">'
	           +' <div class="col-md-12">'
	           +'  <label class="col-xs-6">'+data.name+'</label>'
	           +'   <label class="col-xs-6 mytext" data-Id="'+data.departmentId+'">'+data.name+'</label>'
	           +'  </div>'
	           +'  <div class="col-md-12">'
	           +'    <label class="col-xs-6">'+data.code+'</label>'
	           +'    <label class="col-xs-6 mytext2">'+activeStatus+'</label>'
	           +'  </div>'
	           +'  </a>'
	           +'  </li>');

		$(".Id").val(data.departmentId);
		/*$('.saveBtn').removeAttr("onclick");
		$('.CancelBtn').removeAttr("onclick");*/
		$('#errorMsg').hide();
		$('#departmentFormId').addClass('readonly');
		$(".detail_Name").html(data.name);
		$(".detail_Code").html(data.code);
		$(".detail_Desc").html(data.description);
		$(".detail_Active").html(activeStatus);
		$('.example').paginathing();

  }
function renderList()
{ 
	debugger;
	$.ajax({
        type : "POST",
        contentType : "application/json",
        url: "getDepartmentList",
        dataType:"json",
        async:false,
        success: function (data) {
        	if($.isEmptyObject(data))
        		{
        		  Alert.warn("No Department present in List");
        		  $("#editdepartment").addClass('readonly');
        			$("#deldepartment").addClass('readonly');
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
				$(".Id").val(data[i].departmentId);
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
			
		$('#example').append('<li '+active+' onclick="showdetails('+data[i].departmentId+')" id="'+ data[i].departmentId + '"> <a href="" class="" data-toggle="tab">'
		           +' <div class="col-md-12">'
		           +'  <label class="col-xs-6">'+data[i].name+'</label>'
		           +'   <label class="col-xs-6 mytext" data-Id="'+data[i].departmentId+'">'+data[i].name+'</label>'
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
function showdetails(id)
{
	debugger;
	$(".save").hide();
	$(".cancel").hide();
	$('#departmentFormId').addClass("readonly");
	$.ajax({
        type : "POST",
        contentType : "application/json",
        url: "getDepartment/"+id,
        dataType:"json",
        async:false,
        success: function (data) {
        	if($.isEmptyObject(data))
        		{
        		  Alert.warn("Department details is empty");
        		}
        	else
        	{
        		var activeStatus='';
        		$("#departmentFormId").addClass("readonly");
	        	$(".Name").val(data.name);
				$(".Code").val(data.code);
				$(".Description").val(data.description);
				$(".Id").val(data.departmentId);
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
