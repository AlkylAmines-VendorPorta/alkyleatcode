
$(document).ready(function(){ 
	renderList();
	$(".save").hide();
	/*$("#editselecteduom").hide();*/
	$(".cancel").hide();
	
	
	/*$(".addnewuom").click(function(){
		$("#uomFormId").removeClass("readonly");
		$("#uomFormId")
		$("#savenewuom").show();
		$(".cancle").show();
		$(".Name").val("");
		$(".Code").val("");
		$(".Description").val("");
		$("#editselecteduom").hide();
		 $(".Name").removeClass('readonly');
			$(".Code").removeClass('readonly');
			$(".Description").removeClass('readonly');
	});*/
	
	
	$(".save").on("click", function(event) {

		event.preventDefault();
		submitIt("uomFormId", "addNewUom", "saveUOMResp");
		return false;
	});
	
	$('.cancel').click(function(event) {
		event.preventDefault();
		var activeUOMId=$('.example').find('li.active').attr('id');
		if(activeUOMId!=undefined)
			{
			showdetails(activeUOMId);
			
			}
		else
			$('#uomFormId')[0].reset();
		
	});
	
	$("#deluom").click(function(){
		event.preventDefault();
		submitWithParam("deleteUOM","Id", "deleteUomResp");
		 /*submitIt("uomFormId","Id", "getResponseDelete");*/
		
		
	});
	 $("#edituom").click(function(){
		 event.preventDefault();
		 $("#uomFormId").removeClass("readonly");
		  $('.save').show();
		  $('.cancel').show();
		  
		
		});
	
   /*$("#editselecteduom").click(function(){
	   $("#savenewuom").hide();
		$("#editselecteduom").hide();
		 $("#uomFormId").addClass("readonly");
		 $(".cancle").hide();
	   submitIt("uomFormId","upadateUom", "getResponseUpdate");
		
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
       
     function addUOM(event){
    	 debugger;
    	 event.preventDefault();
    		$(".Id").val("");
    		$("#uomFormId").removeClass('readonly');
    		$('.isActive').prop('checked', true); 
    		$(".save").show();
    		$(".cancel").show();
    		$("#edituom").removeClass('readonly');
    		$("#deluom").removeClass('readonly');
    		$("#uomFormId").find('input,select,textarea').val("");
     }

     
     function deleteUomResp(data){
    	 debugger;
    		
    		if(!$.isEmptyObject(data)){
    			  var currentUOMDel=$('.example').find('li.active').attr('id');
    			  
    				if(data.hasError==false)
    				{
    					Alert.info(data.message);
    					$('#'+currentUOMDel).remove();
    					$('#uomFormId #Id').val("");
    					$('#uomFormId #Name').val("");
    					$('#uomFormId #Code').val("");
    					$('#uomFormId #Description').val("");
    					
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
    
     
function renderList()
{ 
	debugger;
	$.ajax({
        type : "POST",
        contentType : "application/json",
        url: "getUomList",
        dataType:"json",
        async:false,
        success: function (data) {
        	if($.isEmptyObject(data)){
        		Alert.warn("No Uom present in List");
        		 $("#deluom").addClass('readonly');
          		  $("#edituom").addClass('readonly');
        		}
        	else{
	        	appendList(data);
        	}	
        },
        error: function (e) {
        	Alert.warn("Exception: ");
        }
    });
	}


function appendList(data)
{
	debugger;
	var active=" class='active'";
	$('#example').empty();
	var activeStatus='';
	for (var i=0;i<data.length;i++)  
	{ 
		if(data[i].isActive=="Y")
			activeStatus="Active";
		else
			activeStatus="InActive";
		if(i==0)
			{
				$(".Name").val(data[i].name);
				$(".Code").val(data[i].code);
				$(".Description").val(data[i].description);
				$(".Id").val(data[i].uomId);
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
		$('#example').prepend('<li '+active+' onclick="showdetails('+data[i].uomId+')" id="'+data[i].uomId+'"> <a href="" class="" data-toggle="tab">'
		           +' <div class="col-md-12">'
		           +'  <label class="col-xs-6">'+data[i].name+'</label>'
		           +'   <label class="col-xs-6 mytext" data-uomid="'+data[i].uomId+'">'+data[i].description+'</label>'
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
function saveUOMResp(data){
	debugger;

	if (!$.isEmptyObject(data)) {
		if (data.response.hasError == false) {

			Alert.info(data.response.message);
			appendRecentRecord(data);
			$(".save").hide();
			$(".cancel").hide();
			/*$(".editSave").hide();*/
			$("#uomFormId").addClass('readonly');
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
	var currentUOMId = $('.example').find('li.active').attr('id');

	$("#Id").val(data.uomId);
	
	if(data.isActive == "Y")
		activeStatus="Active";
	else
		activeStatus="InActive";
	

	if (currentUOMId == data.uomId) {
		$('#' + currentUOMId + '').remove();
	} else {
		$('#' + currentUOMId + '').removeClass();
	}
	var active = " class='active'";
	$('#masterDetails').empty();
	$('#masterDetails').append('<div class="row"><label class="col-xs-6" ><h4 class="detail_Name">'+data.name+'</h4></label>'
            +'<label class="col-xs-6 mytext detail_Code">'+data.code+'</label></div> '
            +'<div class="row"><label class="col-xs-6 mytext detail_Desc">'+data.description+'</label>'
            +'<label class="col-xs-6 mytext detail_Active">'+activeStatus+'</label></div>');
      
	
$('#example').prepend('<li '+active+' onclick="showdetails('+data.uomId+')" id="'+data.uomId+'"> <a href="" class="" data-toggle="tab">'
           +' <div class="col-md-12">'
           +'  <label class="col-xs-6">'+data.name+'</label>'
           +'   <label class="col-xs-6 mytext" data-uomid="'+data.uomId+'">'+data.description+'</label>'
           +'  </div>'
           +'  <div class="col-md-12">'
           +'    <label class="col-xs-6">'+data.code+'</label>'
           +'    <label class="col-xs-6 mytext2">'+activeStatus+'</label>'
           +'  </div>'
           +'  </a>'
           +'  </li>');

	$(".Id").val(data.uomId);
	/*$('.saveBtn').removeAttr("onclick");
	$('.CancelBtn').removeAttr("onclick");*/
	$('#errorMsg').hide();
	$('#uomFormId').addClass('readonly');
	
	$(".detail_Name").html(data.name);
	$(".detail_Code").html(data.code);
	$(".detail_Desc").html(data.description);
	$(".detail_Active").html(activeStatus);
	$('.example').paginathing();


}

function searchuom()
{
	debugger;
	var searchliteral=$('#searchlitralid').val();
	var url='';
	if(searchliteral.trim()=="")
		url="getUomList";
	else
		url="getsearcheduom/"+searchliteral;
	$("#loading-wrapper").fadeIn();
		$.ajax({
	        type : "POST",
	        contentType : "application/json",
	        url: url,
	        dataType:"json",
	        asycn:true,
	        success: function (data) {
	        	if($.isEmptyObject(data))
	        		{
	        		Alert.warn("No record Matching the literal");
	        		}
	        	else
	        		{
	        		   appendList(data);
	        		}
	        	$("#loading-wrapper").fadeOut();
	        },
	        error: function (e) {
	        	$("#loading-wrapper").fadeOut();
	        	Alert.warn("Excepton :");
	        }
	    });   
		
}

function showdetails(id)
{
	debugger;
	$(".save").hide();
	$(".cancel").hide();
	$('#uomFormId').addClass("readonly");
	$.ajax({
        type : "POST",
        contentType : "application/json",
        url: "getUom/"+id,
        dataType:"json",
        async:false,
        success: function (data) {
        	if($.isEmptyObject(data))
        		{
        		Alert.warn("Uom details is empty-Add Details");
        		}
        	else
        	{
	        	 var activeStatus='';
	        	$(".Name").val(data.name);
				$(".Code").val(data.code);
				$(".Description").val(data.description);
				$(".Id").val(data.uomId);
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
    });
	}