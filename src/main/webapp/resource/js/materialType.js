
$(document).ready(function(){ 
	renderList();
	$(".save").hide();
	$(".cancel").hide();
	/*$("#editselectedmaterialtype").hide();
	$(".addnewmaterialtype").click(function(){
		$(".save").show();
		$(".cancel").show();
		$(".Name").val("");
		$(".Code").val("");
		$(".Description").val("");
		$("#editselectedmaterialtype").hide();
		$("#materialypemaster").removeClass("readonly");
		 $(".Name").removeClass('readonly');
			$(".Code").removeClass('readonly');
			$(".Description").removeClass('readonly');
	});*/
	$('.save').on("click", function(){
		event.preventDefault();
		submitIt("materialFormId","saveMaterialType", "saveMaterialTypeResp")
	});
	$("#delmattype").on('click', function(){
		event.preventDefault();
		submitWithParam("deleteMaterialType","Id" ,"deleteMaterialTypeResp");
	});
	
	 $('.cancel').click(function(event) {
			event.preventDefault();
			var activeMatId=$('.example').find('li.active').attr('id');
			if(activeHSNId!=undefined)
				{
				showdetails(activeMatId);
				
				}
			else
				$('#materialFormId')[0].reset();
			
		});
	 $("#editmattype").click(function() {
			event.preventDefault();
			$("#cancel").show();
			$('.save').show();
			$("#materialFormId").removeClass("readonly");
		});

	
   /*$("#editselectedmaterialtype").click(function(){
	   submitIt("materialypemaster","upadateMaterialType", "getResponseUpdate");
	   $("#materialypemaster").addClass("readonly");
		$(".cancel").hide();
		$("#editselectedmaterialtype").hide();
		
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

  function addMaterialType(event){
	  debugger;
	  event.preventDefault();
	  $(".Id").val("");
	  $("#materialFormId").removeClass('readonly');
	  $('.isActive').prop('checked', true); 
	  $('.save').show();
	  $('.cancel').show();
	  $('#materialFormId').find('input,select,textarea').val("");
  }
  
  function deleteMaterialTypeResp(data){
	  debugger;
	  if(!$.isEmptyObject(data)){
		  var currentMatTypeDel=$('.example').find('li.active').attr('id');
		  
			if(data.hasError==false)
			{
				Alert.info(data.message);
				$('#'+currentMatTypeDel).remove();
				$('#materialFormId #Id').val("");
				$('#materialFormId #Name').val("");
				$('#materialFormId #Code').val("");
				$('#materialFormId #Description').val("");
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
  
  function saveMaterialTypeResp(data){
	  debugger;

		if (!$.isEmptyObject(data)) {
			if (data.response.hasError == false) {

				Alert.info(data.response.message);
				appendRecentRecord(data);
				$(".save").hide();
				$(".cancel").hide();
				/*$(".editSave").hide();*/
				$("#materialFormId").addClass('readonly');
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
	  var currentMaterialTypeId=$('.example').find('li.active').attr('id')
	  
	  $("#Id").val(data.materialTypeId);
		
		if(data.isActive == "Y")
			activeStatus="Active";
		else
			activeStatus="InActive";
		

		if (currentMaterialTypeId == data.materialTypeId) {
			$('#' + currentMaterialTypeId + '').remove();
		} else {
			$('#' + currentMaterialTypeId + '').removeClass();
		}
	  var active=" class='active'";
	  $("#masterDetails").empty();
	  $('#masterDetails').append('<div class="row"><label class="col-xs-6" ><h4 class="detail_Name">'+data.name+'</h4></label>'
	            +'<label class="col-xs-6 mytext detail_Code">'+data.code+'</label></div> '
	            +'<div class="row"><label class="col-xs-6 mytext detail_Desc">'+data.description+'</label>'
	            +'<label class="col-xs-6 mytext detail_Active">'+activeStatus+'</label></div>');
			
	$('#example').prepend('<li '+active+' onclick="showdetails('+data.materialTypeId+')" id="'+ data.materialTypeId + '"> <a href="" class="" data-toggle="tab">'
	           +' <div class="col-md-12">'
	           +'  <label class="col-xs-6">'+data.name+'</label>'
	           +'   <label class="col-xs-6 mytext" data-Id="'+data.materialTypeId+'">'+data.description+'</label>'
	           +'  </div>'
	           +'  <div class="col-md-12">'
	           +'    <label class="col-xs-6">'+data.code+'</label>'
	           +'    <label class="col-xs-6 mytext2">'+activeStatus+'</label>'
	           +'  </div>'
	           +'  </a>'
	           +'  </li>'); 
	
	("#Id").val(data.materialTypeId);
	("#errorMsg").hide();
	("#materialFormId").addClass('readonly');
	$(".detail_Name").html(data.name);
	$(".detail_Code").html(data.code);
	$(".detail_Desc").html(data.description);
	$(".detail_Active").html(activeStatus);
	$('.example').paginathing();
	
  }
function renderList()
{ debugger;
	
	$.ajax({
        type : "POST",
        contentType : "application/json",
        url: "getMaterialTypeList",
        dataType:"json",
        async:false,
        success: function (data) {
        	if($.isEmptyObject(data))
        		{
        		  swal("No MaterialType present in List");
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
				$(".Id").val(data[i].materialTypeId);
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
			
		$('#example').append('<li '+active+' onclick="showdetails('+data[i].materialTypeId+')"  id="'+ data[i].materialTypeId + '"> <a href="" class="" data-toggle="tab">'
		           +' <div class="col-md-12">'
		           +'  <label class="col-xs-6">'+data[i].name+'</label>'
		           +'   <label class="col-xs-6 mytext" data-Id="'+data[i].materialTypeId+'">'+data[i].description+'</label>'
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
	$('.save').hide();
	$('cancel').hide();
	$('#materialFormId').addClass("readonly");
	$.ajax({
        type : "POST",
        contentType : "application/json",
        url: "getMaterialType/"+id,
        dataType:"json",
        async:false,
        success: function (data) {
        	if($.isEmptyObject(data))
        		{
        		  swal("MaterialType details is empty");
        		}
        	else
        	{
	        	 var activeStatus='';
	        	$(".Name").val(data.name);
				$(".Code").val(data.code);
				$(".Description").val(data.description);
				$(".Id").val(data.materialTypeId);
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
			swal(e.message);
        }
    });
}