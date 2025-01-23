
$(document).ready(function(){ 

	renderList();
	$(".save").hide();
	$(".cancel").hide();
	$("#editselectedtaxcat").hide();
	
	
	
	$(".addnewtaxcat").click(function(){
		$(".save").show();
		$(".cancel").show();
		$(".Name").val("");
		$(".Code").val("");
		$(".Description").val("");
		$(".taxCatId").val("");
		$('.isActive').prop('checked', true);
		 $("#taxCatFormId").removeClass('readonly');
		
	});
	$("#addnewtaxcat").click(function(){
		event.preventDefault();
		submitIt("taxCatFormId","addNewTaxCat", "getResponseDto");
	});
	
	$('.cancel').click(function(event) {
		event.preventDefault();
		var activeTaxCatId=$('.example').find('li.active').attr('id');
		if(activeTaxCatId!=undefined)
			{
			showdetails(activeTaxCatId);
			
			}
		else
			$('#taxCatFormId')[0].reset();
		
	});
	
	$("#delrole").on("click", function(event) {
		event.preventDefault();
		
		 submitWithParam("deleteTaxCat","taxCatId","getResponseDelete");
		
		
	});
	 $("#editrole").click(function(){
		 event.preventDefault();
		  /*$(".Id").attr('name', 'taxCategoryId');*/
		 $("#taxCatFormId").removeClass('readonly');
		 $(".save").show();
		 $(".cancel").show();
		  
		
		});
	
  /* $("#editselectedtaxcat").click(function(){
	   submitIt("taxcatmaster","upadatetaxCat", "getResponseUpdate");
		
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
function renderList()
{ 
	debugger;
	$.ajax({
        type : "POST",
        contentType : "application/json",
        url: "getTaxCategoryList",
        dataType:"json",
        async:false,
        success: function (data) {
        	if($.isEmptyObject(data))
        		{
        		  Alert.warn("No TaxCategory present in List");
        		  $("#taxCatFormId").removeClass('readonly');
        		  $(".save").show();
        			$(".cancel").show();
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


    function getResponseDto(data){
    	debugger;
    	if(!$.isEmptyObject(data)){
    		if(data.response.hasError==false){
    			Alert.info(data.response.message);
    			appendRecentRecord(data);
    			$(".save").hide();
		    	$(".cancel").hide();
		    	/*$(".editSave").hide();*/
		    	 $("#taxCatFormId").addClass('readonly');
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
    	  var currentTaxCatId=$('.example').find('li.active').attr('id');
    	  

    	  $("#taxCatId").val(data.taxCategoryId);
    	  if(data.isActive=="Y")
    			activeStatus="Active";
    		else
    			activeStatus="InActive";
    	  
    	  if(currentTaxCatId==data.taxCategoryId){
    		  $('#'+currentTaxCatId+'').remove();
    	  }
    	  else{
    		  $('#'+currentTaxCatId+'').removeClass();
    	  }
    	  var active=" class='active'";
    	  $('#masterDetails').empty();
			$('#masterDetails').append('<div class="row"><label class="col-xs-6" ><h4 class="detail_Name">'+data.name+'</h4></label>'
		            +'<label class="col-xs-6 mytext detail_Code">'+data.code+'</label></div> '
		            +'<div class="row"><label class="col-xs-6 mytext detail_Desc">'+data.description+'</label>'
		            +'<label class="col-xs-6 mytext detail_Active">'+activeStatus+'</label></div>');
	          
			
				
			
			
		$('#example').prepend('<li '+active+' onclick="showdetails('+data.taxCategoryId+')" id="'+data.taxCategoryId+'" > <a href="" class="" data-toggle="tab">'
		           +' <div class="col-md-12">'
		           +'  <label class="col-xs-6">'+data.name+'</label>'
		           +'   <label class="col-xs-6 mytext" data-id="'+data.taxCategoryId+'">'+data.description+'</label>'
		           +'  </div>'
		           +'  <div class="col-md-12">'
		           +'    <label class="col-xs-6">'+data.code+'</label>'
		           +'    <label class="col-xs-6 mytext2">'+activeStatus+'</label>'
		           +'  </div>'
		           +'  </a>'
		           +'  </li>');
    	  
    	  $(".taxCatId").val(data.taxCategoryId);
    	 	/*$('.saveBtn').removeAttr("onclick");
    	 	$('.CancelBtn').removeAttr("onclick");*/
    	 	$('#errorMsg').hide();
    	 	$('#taxCatFormId').addClass('readonly');
    	 	$(".detail_Name").html(data.name);
			$(".detail_Code").html(data.code);
			$(".detail_Desc").html(data.description);
			$(".detail_Active").html(activeStatus);
    	 	 
    	 		$('.example').paginathing();

      
    }
/*function getResponseDto(data){
	if($.isEmptyObject(data))
	{
	  swal("Some Thing went wrong");
	}
else
{
	 swal("Role inserted successfully");
	 renderList();
}
}*/
/*function getResponseUpdate(data){
	if($.isEmptyObject(data))
	{
	  swal("Some Thing went wrong");
	}
else
{
	 swal("Role Updated successfully");
	 renderList();
}
}*/
function getResponseDelete(data){
	debugger;
	
	if(!$.isEmptyObject(data)){
		  var currentDel=$('.example').find('li.active').attr('id');
		  
			if(data.hasError==false)
			{
				Alert.info(data.message);
				$('#'+currentDel).remove();
				$('#taxCatFormId #taxCatId').val("");
				$('#taxCatFormId #Name').val("");
				$('#taxCatFormId #Code').val("");
				$('#taxCatFormId #Description').val("");
				
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
				$(".taxCatId").val(data[i].taxCategoryId);
				
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
			
		$('#example').append('<li '+active+' onclick="showdetails('+data[i].taxCategoryId+')" id="'+data[i].taxCategoryId+'"> <a href="" class="" data-toggle="tab">'
		           +' <div class="col-md-12">'
		           +'  <label class="col-xs-6">'+data[i].name+'</label>'
		           +'   <label class="col-xs-6 mytext" data-id="'+data[i].taxCategoryId+'">'+data[i].description+'</label>'
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
	$("#taxCatFormId").addClass('readonly');
	$.ajax({
        type : "POST",
        contentType : "application/json",
        url: "getTaxCategory/"+id,
        dataType:"json",
        async:false,
        success: function (data) {
        	if($.isEmptyObject(data))
        		{
        		  Alert.warn("TaxCategory details is empty");
        		}
        	else
        	{
	        	
	        	 var activeStatus='';
	        	$(".Name").val(data.name);
				$(".Code").val(data.code);
				$(".Description").val(data.description);
				$(".taxCatId").val(data.taxCategoryId);
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
				/*
				if(data.isActive=="Y")
					$('.isActive').prop('checked', true); 
				else
					$('.isActive').prop('checked', false);*/
				$(".detail_Name").html(data.name);
				$(".detail_Code").html(data.code);
				$(".detail_Desc").html(data.description);
				$(".detail_Active").html(activeStatus);
				
				
        	}
		        	
        },
        error: function (e) {
			Alert.warn("Exception :");
        }
    });
	}