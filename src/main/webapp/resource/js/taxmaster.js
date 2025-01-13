
$(document).ready(function(){ 
	renderList();

	$(".save").hide();
	$(".cancel").hide();

	/*$(".save").hide();
	$(".cancel").hide();*/

	/*$(".editSave").hide();*/
	
	
	$(".save").on("click", function(event) {
		
		event.preventDefault();
		submitIt("taxFormId", "saveTax", "saveTaxResp");
		return false;
	});
	$('.cancel').click(function(event) {
		event.preventDefault();
		var activeId=$('.example').find('li.active').attr('id');
		if(activeId!=undefined)
			{
			showdetails(activeId);
			
			}
		else
			$('#taxFormId')[0].reset();
		
	});
$("#editId").on("click", function(event) {
		
		event.preventDefault();
		 $("#taxFormId").removeClass('readonly');
		 /* $(".editSave").show();
*/		  $(".save").show();
          $(".cancel").show();
	});

	
$("#deleteId").on("click", function(event) {
		
		event.preventDefault();
		/* $("#taxFormId").removeClass('readonly');*/
		submitWithParam("deleteTax", "taxId", "deleteTaxResp");
	});
});
  function addTax(event){
	  debugger;
	  event.preventDefault();
	  $(".Id").val("");
	  $("#taxFormId").removeClass('readonly');
	  $('.isActive').prop('checked', true); 
	  $(".save").show();
	  $(".cancel").show();
	  $("#taxFormId").find('input,select,textarea').val("");
	  
  }
  
  function deleteTaxResp(data){
	  debugger;
	  if(!$.isEmptyObject(data)){
		  var currentDel=$('.example').find('li.active').attr('id');
			if(data.hasError==false)

			{
				$('#'+currentDel).remove();
				$('#taxFormId #taxId').val("");
				$('#taxFormId #name').val("");
				$('#taxFormId #code').val("");
				$('#taxFormId #description').val("");
				$('#taxFormId #taxcategory').val("");
				$('#materialDetails').empty();
				Alert.info(data.message);
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
  
  function saveTaxResp(data){
	 debugger;
	 
	 if(!$.isEmptyObject(data)){
			if(data.response.hasError==false)
			{
				
		    	Alert.info(data.response.message);
		    	appendRecentRecord(data);
		    	$(".save").hide();
		    	$(".cancel").hide();
		    	/*$(".editSave").hide();*/
		    	 $("#taxFormId").addClass('readonly');
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
	  var currentTaxId=$('.example').find('li.active').attr('id');
	  $("#taxId").val(data.taxId);
	  if(data.isActive=="Y")
			activeStatus="Active";
		else
			activeStatus="InActive";
	  
		var taxCategoryName=data.taxcategory==null?'':data.taxcategory.name==null?'':data.taxcategory.name;

	  if(!$.isEmptyObject(data.taxcategory))
		  
		  $("#taxcategory").val(data.taxcategory.taxCategoryId);
	  if(currentTaxId==data.taxId){
		  $('#'+currentTaxId+'').remove();
	  }
	  else{
		  $('#'+currentTaxId+'').removeClass();
	  }
	  var active=" class='active'";
	  $('#masterDetails').empty();
		$('#masterDetails').append('<div class="row"><label class="col-xs-6" ><h4 class="detail_Name">'+data.name+'</h4></label>'
	            +'<label class="col-xs-6 mytext detail_Code">'+data.code+'</label></div> '
	            +'<div class="row"><label class="col-xs-6 mytext detail_Desc">'+data.description+'</label>'
	            +'<label class="col-xs-6 mytext detail_Active">'+taxCategoryName+'</label></div>');
		
	  $('#example').prepend('<li '+active+' onclick="showdetails('+data.taxId+')" id="'+data.taxId+'"> <a href="" class="" data-toggle="tab">'
	 
	           +' <div class="col-md-12">'
	           +'  <label class="col-xs-6">'+data.name+'</label>'
	           +'   <label class="col-xs-6 mytext" data-userId="'+data.taxId+'">'+data.description+'</label>'
	           +'  </div>'
	           +'  <div class="col-md-12">'
	           +'    <label class="col-xs-6">'+data.code+'</label>'
	           +'    <label class="col-xs-6 mytext2">'+activeStatus+'</label>'
	           +'  </div>'
	           +'  </a>'
	           +'  </li>');
	  
	  $(".Id").val(data.taxId);
	 	/*$('.saveBtn').removeAttr("onclick");
	 	$('.CancelBtn').removeAttr("onclick");*/
	 	$('#errorMsg').hide();
	 	$('#taxFormId').addClass('readonly');
	    
	 	 
	 	 	$(".detail_Name").html(data.name);
	 		$(".detail_Code").html(data.code);
	 		$(".detail_Desc").html(data.description);
	 		$(".detail_Active").html(taxCategoryName);
	 		$('.example').paginathing();

  }

	 
	 function appendTaxData(data)
	 {
	 	debugger;
	 	
	 	
	 	var currentTaxId=$('.example').find('li.active').attr('id');
	 	if(currentTaxId==data.taxId)
	 	{
	 		$('#'+data.taxId).remove();
	 	}
	 	else
	 	{
	 		$('#'+currentTaxId).removeClass('active');
	 		var oldCount=$('.reportCount').html();
	 		$('.reportCount').html(Number(oldCount)+1);
	 	}
	 	var active=" class='active'";
		var taxCategoryName=data.taxcategory==null?'':data.taxcategory.name==null?'':data.taxcategory.name;

		$('#masterDetails').empty();
		$('#masterDetails').append('<div class="row"><label class="col-xs-6" ><h4 class="detail_Name">'+data.name+'</h4></label>'
	            +'<label class="col-xs-6 mytext detail_Code">'+data.code+'</label></div> '
	            +'<div class="row"><label class="col-xs-6 mytext detail_Desc">'+data.description+'</label>'
	            +'<label class="col-xs-6 mytext detail_Active">'+taxCategoryName+'</label></div>');
		
		
	 	$('#example').prepend('<li '+active+' onclick="showdetails('+data.taxId+')" id="'+data.taxId+'"> <a href="" class="" data-toggle="tab">'
		           +' <div class="col-md-12">'
		           +'  <label class="col-xs-6">'+data.name+'</label>'
		           +'   <label class="col-xs-6 mytext" data-id="'+data.taxId+'">'+data.description+'</label>'
		           +'  </div>'
		           +'  <div class="col-md-12">'
		           +'    <label class="col-xs-6">'+data.code+'</label>'
		           +'    <label class="col-xs-6 mytext2">'+taxCategoryName+'</label>'
		           +'  </div>'
		           +'  </a>'
		           +'  </li>');
	 	$(".Id").val(data.taxId);
	 	$('.saveBtn').removeAttr("onclick");
	 	$('.CancelBtn').removeAttr("onclick");
	 	$('#errorMsg').hide();
	 	$('#taxFormId').addClass('readonly');
	    
	 	 
	 	 	$(".detail_Name").html(data.name);
	 		$(".detail_Code").html(data.code);
	 		$(".detail_Desc").html(data.description);
	 		/*$(".detail_Active").html(taxCategoryNamey);*/
	 		$(".detail_Active").html(taxCategoryName);
	 		$('.example').paginathing();
	 		
	 		}
function renderList()
{ 
	debugger;
	$.ajax({
        type : "POST",
        contentType : "application/json",
        url: "getTaxList",
        dataType:"json",
        async:false,
        success: function (data) {
        	if($.isEmptyObject(data))
        		{
        		  swal("No Tax present in List");
        		  $("#taxFormId").removeClass('readonly');
        		  $(".save").show();
        			$(".cancel").show();
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
				var taxCategoryName=data[i].taxcategory==null?'':data[i].taxcategory.name==null?'':data[i].taxcategory.name;
				$(".Id").val(data[i].taxId);
				if(!$.isEmptyObject(data[i].taxcategory)){
					$("#taxcategory").val(data[i].taxcategory.taxCategoryId);
					
				}
				
				if(activeStatus=="Active")
					$('.isActive').prop('checked', true);
				else
					$('.isActive').prop('checked', false);
				
			$('#masterDetails').empty();
			$('#masterDetails').append('<div class="row"><label class="col-xs-6" ><h4 class="detail_Name">'+data[i].name+'</h4></label>'
		            +'<label class="col-xs-6 mytext detail_Code">'+data[i].code+'</label></div> '
		            +'<div class="row"><label class="col-xs-6 mytext detail_Desc">'+data[i].description+'</label>'
		            +'<label class="col-xs-6 mytext detail_Active">'+taxCategoryName+'</label></div>');
				
			}
			
		$('#example').append('<li '+active+' onclick="showdetails('+data[i].taxId+')" id="'+data[i].taxId+'">  <a href="" class="" data-toggle="tab">'
		           +' <div class="col-md-12">'
		           +'  <label class="col-xs-6">'+data[i].name+'</label>'
		           +'   <label class="col-xs-6 mytext" data-taxid="'+data[i].taxId+'">'+data[i].description+'</label>'
		           +'  </div>'
		           +'  <div class="col-md-12">'
		           +'    <label class="col-xs-6">'+data[i].code+'</label>'
		           +'    <label class="col-xs-6 mytext2">'+activeStatus+'</label>'
		           +'  </div>'
		           +'  </a>'
		           +'  </li>');
		active="";
          }$('.example').paginathing();	
      	
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
/*$(".editSave").hide();*/
$("#taxFormId").addClass('readonly');
	$.ajax({
        type : "POST",
        contentType : "application/json",
        url: "getTax/"+id,
        dataType:"json",
        async:false,
        success: function (data) {
        	if($.isEmptyObject(data))
        		{
        		  swal("Tax details is empty-Add Details");
        		}
        	else
        	{
	        
	        	 
	        	$(".Name").val(data.name);
				$(".Code").val(data.code);
				$(".Description").val(data.description);
				var taxCategoryName=data.taxcategory==null?'':data.taxcategory.name==null?'':data.taxcategory.name;
				$(".Id").val(data.taxId);
				
				if(!$.isEmptyObject(data.taxcategory))
					$("#taxcategory").val(data.taxcategory.taxCategoryId);
				/*$(".TaxCategory").val(data.taxcategory.taxCategoryId);
*/				
				if(data.isActive=="Y")
					$('.isActive').prop('checked', true); 
				else
					$('.isActive').prop('checked', false);
				$(".detail_Name").html(data.name);
				$(".detail_Code").html(data.code);
				$(".detail_Desc").html(data.description);
				$(".detail_Active").html(taxCategoryName);
				
        	}
		        	
        },
        error: function (e) {
			swal("Exception :");
        }
    });
	}

