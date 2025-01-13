$(document).ready(function(){ 
	renderList();
	$('.indirectFormSubmit').click(function(event) {
        event.preventDefault();
        		submitIt("formId",$('#formId').attr('action'), "processResponse");
        return false;
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
var name="";
var code="";
var desc="";
var isActive="";
var id="";

var validate=false;

function getPrevValues()
{
	name=$(".Name").val();
	code=$(".Code").val();
	desc=$(".Description").val();
	if ($(".isActive").prop("checked") == true)
		isActive = "Y";
	else
	    isActive = "N";
	id=$(".Id").val();
	}
function setPrevValues()
{
	$(".Name").val(name);
	$(".Code").val(code);
	$(".Description").val(desc);
	if (isActive == "Y")
		$('.isActive').prop('checked', true);
	else
		$('.isActive').prop('checked', false);
	$(".Id").val(id);
	}

function addNewGtpParameterType()
{
	getPrevValues();
	$(".Name").val("");
	$(".Code").val("");
	$(".Description").val("");
	$(".Id").val("");
	$('#formId').removeClass('readonly');
	$('.isActive').prop('checked', true);
	
	
	$('.saveBtn').show();
	$('#formId').attr("action","saveGtpParameterType");
	$('.CancelBtn').show();
	$('.CancelBtn').attr("onclick","cancel()");
	
	
	$('#refreshId').addClass('readonly');
	$('#editId').addClass('readonly');
	$('#deleteId').addClass('readonly');
	}
function editPrevGtpParameterType()
{
	getPrevValues();
	$('#formId').removeClass('readonly');
	$('.saveBtn').show();
	$('.CancelBtn').show();
	$('#formId').attr("action","editGtpParameterType");
	$('.CancelBtn').attr("onclick","cancel()");
	
	$('#refreshId').addClass('readonly');
	$('#addId').addClass('readonly');
	$('#deleteId').addClass('readonly');
	}

function cancel()
{
	setPrevValues();
	$(".Name").val(name);
	$(".Code").val(code);
	$(".Description").val(desc);
	$(".Id").val(id);
	if(isActive=="Y")
		$('.isActive').prop('checked', true); 
	else
		$('.isActive').prop('checked', false);
	
	$('#formId').addClass('readonly');
    $('.saveBtn').hide();
	$('.CancelBtn').hide();
	$('.saveBtn').removeAttr("onclick");
	$('.CancelBtn').removeAttr("onclick");
	$('#errorMsg').hide();
	
			 name="";
			 code="";
			 desc="";
			 isActive="";
			 id="";
			 
			 $('#refreshId').removeClass('readonly');
			 $('#addId').removeClass('readonly');
			 $('#editId').removeClass('readonly');
			 $('#deleteId').removeClass('readonly');
	}
function renderList()
{ 
	
	$.ajax({
        type : "POST",
        contentType : "application/json",
        url: "getGtpParameterTypeList",
        dataType:"json",
        async:false,
        success: function (data) {
    
        	if($.isEmptyObject(data))
        		{
        		  Alert.info("No Gtp Parameter Type present in List");
        		  $('#masterDetails').append('<div class="row"><label class="col-xs-6" ><h4 class="detail_Name"></h4></label>'
    			            +'<label class="col-xs-6 mytext detail_Code"></label></div> '
    			            +'<div class="row"><label class="col-xs-6 mytext detail_Desc"></label>'
    			            +'<label class="col-xs-6 mytext detail_Active"></label></div>');
        		  $('.CancelBtn').hide();
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
	$('.pagination').children().remove();
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
				$(".Id").val(data[i].gtpParameterTypeId);
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
			
		$('#example').append('<li '+active+' onclick="showdetails('+data[i].gtpParameterTypeId+')" id="'+data[i].gtpParameterTypeId+'"> <a href="" class="" data-toggle="tab">'
		           +' <div class="col-md-12">'
		           +'  <label class="col-xs-6">'+data[i].name+'</label>'
		           +'   <label class="col-xs-6 mytext" data-Id="'+data[i].gtpParameterTypeId+'">'+data[i].description+'</label>'
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
	$('.saveBtn').hide();
	$('.CancelBtn').hide();
	$('.saveBtn').removeAttr("onclick");
	$('.CancelBtn').removeAttr("onclick");
	$('#errorMsg').hide();
	$('#formId').addClass('readonly');
	 $('#refreshId').removeClass('readonly');
	 $('#addId').removeClass('readonly');
	 $('#editId').removeClass('readonly');
	 $('#deleteId').removeClass('readonly');
	}
function showdetails(id)
{
	
	$.ajax({
        type : "POST",
        contentType : "application/json",
        url: "getGtpParameterType/"+id,
        dataType:"json",
        async:false,
        success: function (data) {
        	
        	if($.isEmptyObject(data))
        		{
        		  Alert.info(" Gtp Parameter details is empty");
        		}
        	else
        	{
	        	 var activeStatus='';
	        	$(".Name").val(data.name);
				$(".Code").val(data.code);
				$(".Description").val(data.description);
				$(".Id").val(data.gtpParameterTypeId);
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
				
				$('.saveBtn').hide();
				$('.CancelBtn').hide();
				$('.saveBtn').removeAttr("onclick");
				$('.CancelBtn').removeAttr("onclick");
				$('#formId').addClass('readonly');
				
			    $('#refreshId').removeClass('readonly');
				 $('#addId').removeClass('readonly');
				 $('#editId').removeClass('readonly');
				 $('#deleteId').removeClass('readonly');
				 $('#errorMsg').hide();
        	}
		        	
        },
        error: function (e) {
        	
			ALert.warn("Exception :");
        }
    });
	}
function appendRecentData(data)
{
	$('.pagination').children().remove();
	var currentGtpParameterTypeId=$('.example').find('li.active').attr('id');
	if(currentGtpParameterTypeId==data.gtpParameterTypeId)
	{
		$('#'+data.gtpParameterTypeId).remove();
	}
	else
	{
		$('#'+currentGtpParameterTypeId).removeClass('active');
		var oldCount=$('.reportCount').html();
		$('.reportCount').html(Number(oldCount)+1);
	}
	var active=" class='active'";
	$('#example').prepend('<li '+active+' onclick="showdetails('+data.gtpParameterTypeId+')" id="'+data.gtpParameterTypeId+'"> <a href="" class="" data-toggle="tab">'
	           +' <div class="col-md-12">'
	           +'  <label class="col-xs-6">'+data.name+'</label>'
	           +'   <label class="col-xs-6 mytext" data-id="'+data.gtpParameterTypeId+'">'+data.description+'</label>'
	           +'  </div>'
	           +'  <div class="col-md-12">'
	           +'    <label class="col-xs-6">'+data.code+'</label>'
	           +'    <label class="col-xs-6 mytext2">Active</label>'
	           +'  </div>'
	           +'  </a>'
	           +'  </li>');
	$(".Id").val(data.gtpParameterTypeId);
	$('.saveBtn').hide();
	$('.CancelBtn').hide();
	$('.saveBtn').removeAttr("onclick");
	$('.CancelBtn').removeAttr("onclick");
	$('#formId').addClass('readonly');
    
    $('#refreshId').removeClass('readonly');
	 $('#addId').removeClass('readonly');
	 $('#editId').removeClass('readonly');
	 $('#deleteId').removeClass('readonly');
	 $('#errorMsg').hide();
	 $('.example').paginathing();
	 
		}

function processResponse(data)
{
	if(data.responseStatus==true)
	{
		appendRecentData(data.data);
    	Alert.info(data.responseMsg);
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

function deleteGtpParameterType()
{
	$('.pagination').children().remove();
	$('#refreshId').addClass('readonly');
	$('#addId').addClass('readonly');
	$('#editId').addClass('readonly');
	
	input_box = confirm("Do you really want to delete this Gtp Parameter Type?");
	 if (input_box == true) {
			var id=$(".Id").val();
			$.ajax({
		        type : "POST",
		        contentType : "application/json",
		        url: "deleteGtpParameterType/"+id,
		        dataType:"json",
		        async:false,
		        success: function (data) {
		        
		        	if(data.responseStatus==true)
		        		{
		        			$('#'+id).remove();
		        			var oldCount=$('.reportCount').html();
			        		$('.reportCount').html(Number(oldCount)-1);
			            	Alert.info(data.responseMsg);
			            	$('#refreshId').removeClass('readonly');
			            	$('#addId').removeClass('readonly');
			            	$('#editId').removeClass('readonly');
			            	$(".Name").val("");
			            	$(".Code").val("");
			            	$(".Description").val("");
			            	$(".Id").val("");
			            	$('#errorMsg').hide();
		        		}
		        	else
		        		{
			            Alert.warn(data.responseMsg,"","error");
		        		}
		        	
		        },
		        error: function (e) {
					Alert.warn(e.message);
		        }
		    });
			$('.example').paginathing();
			
	 }
	}