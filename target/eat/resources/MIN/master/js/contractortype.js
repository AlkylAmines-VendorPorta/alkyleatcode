
$(document).ready(function(){ 
	
	renderContractortypeList();
	
});
var contractortypename="";
var contractortypecode="";
var desc="";
var isActive="";
var id="";


function buffervalues()
{
	contractortypename=$(".ContractortypeName").val();;
	contractortypecode=$(".ContractortypeCode").val();
	desc=$(".Description").val();
	if ($(".isActive").prop("checked") == true)
		isActive = "Y";
	else
	    isActive = "N";
	id=$(".contractortypeid").val();
	}


function addNewContractortype()
{
	buffervalues();
	$(".ContractortypeName").val("");
	$(".ContractortypeCode").val("");
	$(".Description").val("");
	$(".contractortypeid").val("");
	$(".ContractortypeName").removeAttr("disabled");
	$(".ContractortypeCode").removeAttr("disabled");
	$(".Description").removeAttr("disabled");
	
	
	$('.isActive').prop('checked', true);
	
	
	$('.saveBtn').show();
	$('.saveBtn').attr("onclick","addcontractortype()");
	$('.CamcleBtn').show();
	$('.CamcleBtn').attr("onclick","cancel()");
	
	
	$('#refreshId').addClass('hide');
	$('#editId').addClass('hide');
	$('#deleteId').addClass('hide');
	}
function editPrevContractortype()
{
	
	buffervalues();
	$(".ContractortypeName").removeAttr("disabled");
	$(".ContractortypeCode").removeAttr("disabled");
	$(".Description").removeAttr("disabled");
	$(".isActive").removeAttr("disabled");
	
	$('.saveBtn').show();
	$('.CamcleBtn').show();
	$('.saveBtn').attr("onclick","editcontractortype()");
	$('.CamcleBtn').attr("onclick","cancel()");
	
	$('#refreshId').addClass('hide');
	$('#addId').addClass('hide');
	$('#deleteId').addClass('hide');
	}

function cancel()
{
	
	$(".ContractortypeName").val(contractortypename);
	$(".ContractortypeCode").val(contractortypecode);
	$(".Description").val(desc);
	$(".contractortypeid").val(id);
	if(isActive=="Y")
		$('.isActive').prop('checked', true); 
	else
		$('.isActive').prop('checked', false);
	
	$(".ContractortypeName").attr("disabled","disabled");
	$(".ContractortypeCode").attr("disabled","disabled");
	$(".Description").attr("disabled","disabled");
    $('.isActive').attr("disabled","disabled");
    
    $('.saveBtn').hide();
	$('.CamcleBtn').hide();
	$('.saveBtn').removeAttr("onclick");
	$('.CamcleBtn').removeAttr("onclick");
	
			 contractortypename="";
			 contractortypecode="";
			 desc="";
			 isActive="";
			 id="";
			 
			 $('#refreshId').removeClass('hide');
			 $('#addId').removeClass('hide');
			 $('#editId').removeClass('hide');
			 $('#deleteId').removeClass('hide');
	}
function renderContractortypeList()
{ 
	
	$.ajax({
        type : "POST",
        contentType : "application/json",
        url: "getcontractortypelist",
        dataType:"json",
        async:false,
        success: function (data) {
        	if($.isEmptyObject(data))
        		{
        		  alert("No Contractortype present in List-Add some Contractortype");
        		}
        	else
        	{
	        	console.log(data);
	        	appendList(data);
	        	
        	}
		        	
        },
        error: function (e) {
			alert(e.message);
        }
    });
	}

function appendList(data)
{
	
	var active=" class='active'";
	$('#example').empty();
	/*plugin.kill();*/
	for (var i=0;i<data.length;i++)  { 
		if(i==0)
			{
				
				$(".ContractortypeName").val(data[i].contractortypeName);
				$(".ContractortypeCode").val(data[i].contractortypeCode);
				$(".Description").val(data[i].description);
				$(".contractortypeid").val(data[i].id);
				if(data[i].isActive=="Y")
					$('.isActive').prop('checked', true); 
				else
					$('.isActive').prop('checked', false);
				
			}
			
		$('#example').append('<li '+active+' onclick="showdetails('+data[i].id+')"> <a href="" class="" data-toggle="tab">'
		           +' <div class="col-md-12">'
		           +'  <label class="col-xs-6">Contractortype Name</label>'
		           +'   <label class="col-xs-6 mytext" data-contractortypeid="'+data[i].id+'">'+data[i].contractortypeName+'</label>'
		           +'  </div>'
		           +'  <div class="col-md-12">'
		           +'    <label class="col-xs-6">Contractortype Code</label>'
		           +'    <label class="col-xs-6 mytext2">'+data[i].contractortypeCode+'</label>'
		           +'  </div>'
		           +'  </a>'
		           +'  </li>');
		active="";
          }
	$('.saveBtn').hide();
	$('.CamcleBtn').hide();
	$('.saveBtn').removeAttr("onclick");
	$('.CamcleBtn').removeAttr("onclick");
	
	$(".ContractortypeName").attr("disabled","disabled");
	$(".ContractortypeCode").attr("disabled","disabled");
	$(".Description").attr("disabled","disabled");
    $('.isActive').attr("disabled","disabled");
	
	 $('#refreshId').removeClass('hide');
	 $('#addId').removeClass('hide');
	 $('#editId').removeClass('hide');
	 $('#deleteId').removeClass('hide');
	}
function showdetails(id)
{
	
	$.ajax({
        type : "POST",
        contentType : "application/json",
        url: "getcontractortype/"+id,
        dataType:"json",
        async:false,
        success: function (data) {
        	if($.isEmptyObject(data))
        		{
        		  alert("Contractortype details is empty-Add Details");
        		}
        	else
        	{
	        	console.log(data);
	        	 
	        	$(".ContractortypeName").val(data.contractortypeName);
				$(".ContractortypeCode").val(data.contractortypeCode);
				$(".Description").val(data.description);
				$(".contractortypeid").val(data.id);
				if(data.isActive=="Y")
					$('.isActive').prop('checked', true); 
				else
					$('.isActive').prop('checked', false);
				
				$('.saveBtn').hide();
				$('.CamcleBtn').hide();
				$('.saveBtn').removeAttr("onclick");
				$('.CamcleBtn').removeAttr("onclick");
				
				$(".ContractortypeName").attr("disabled","disabled");
				$(".ContractortypeCode").attr("disabled","disabled");
				$(".Description").attr("disabled","disabled");
			    $('.isActive').attr("disabled","disabled");
			    
			    $('#refreshId').removeClass('hide');
				 $('#addId').removeClass('hide');
				 $('#editId').removeClass('hide');
				 $('#deleteId').removeClass('hide');
        	}
		        	
        },
        error: function (e) {
			alert(e.message);
        }
    });}

function addcontractortype()
{
	debugger;
	var contractortypeObj={};
	contractortypeObj.contractortypeName=$(".ContractortypeName").val();
	contractortypeObj.contractortypeCode=$(".ContractortypeCode").val();
	contractortypeObj.description=$(".Description").val();
	if ($(".isActive").prop("checked") == true)
		contractortypeObj.isActive = "Y";
	else
		contractortypeObj.isActive = "N";
	
		$.ajax({
	        type : "POST",
	        data : JSON.stringify(contractortypeObj),
	        contentType : "application/json",
	        url: "addcontractortype",
	        dataType:"json",
	        async:false,
	        success: function (data) {
	        	console.log(data.DATA);
	        	appendList(data.DATA);
	        	alert(data.MSG);
	        	  	
	        },
	        error: function (e) {
				alert(e.message);
	        }
	    });
	
	}

function editcontractortype()
{
	debugger;
	var contractortypeObj={};
	contractortypeObj.contractortypeName=$(".ContractortypeName").val();
	contractortypeObj.contractortypeCode=$(".ContractortypeCode").val();
	contractortypeObj.description=$(".Description").val();
	contractortypeObj.id=$(".contractortypeid").val();
	if ($(".isActive").prop("checked") == true)
		contractortypeObj.isActive = "Y";
	else
		contractortypeObj.isActive = "N";
	
	$.ajax({
        type : "POST",
        data : JSON.stringify(contractortypeObj),
        contentType : "application/json",
        url: "editcontractortype",
        dataType:"json",
        async:false,
        success: function (data) {
        	console.log(data.DATA);
        	appendList(data.DATA);
        	alert(data.MSG);
        	
        },
        error: function (e) {
			alert(e.message);
        }
    });
	
	}

function deleteContractortype()
{
	$('#refreshId').addClass('hide');
	$('#addId').addClass('hide');
	$('#editId').addClass('hide');
	
	input_box = confirm("Do you really want to delete this Contractortype?");
	 if (input_box == true) {
			var id=$(".contractortypeid").val();
			$.ajax({
		        type : "POST",
		        contentType : "application/json",
		        url: "deletecontractortype/"+id,
		        dataType:"json",
		        async:false,
		        success: function (data) {
		        	console.log(data.DATA);
		        	appendList(data.DATA);
		        	alert(data.MSG);
		        },
		        error: function (e) {
					alert(e.message);
		        }
		    });
	 }
	}