
$(document).ready(function(){ 
	
	renderRegistrationtypeList();
	
});
var registrationtypename="";
var registrationtypecode="";
var desc="";
var isActive="";
var id="";


function buffervalues()
{
	registrationtypename=$(".RegistrationtypeName").val();;
	registrationtypecode=$(".RegistrationtypeCode").val();
	desc=$(".Description").val();
	if ($(".isActive").prop("checked") == true)
		isActive = "Y";
	else
	    isActive = "N";
	id=$(".registrationtypeid").val();
	}


function addNewRegistrationtype()
{
	buffervalues();
	$(".RegistrationtypeName").val("");
	$(".RegistrationtypeCode").val("");
	$(".Description").val("");
	$(".registrationtypeid").val("");
	$(".RegistrationtypeName").removeAttr("disabled");
	$(".RegistrationtypeCode").removeAttr("disabled");
	$(".Description").removeAttr("disabled");
	
	
	$('.isActive').prop('checked', true);
	
	
	$('.saveBtn').show();
	$('.saveBtn').attr("onclick","addregistrationtype()");
	$('.CamcleBtn').show();
	$('.CamcleBtn').attr("onclick","cancel()");
	
	
	$('#refreshId').addClass('hide');
	$('#editId').addClass('hide');
	$('#deleteId').addClass('hide');
	}
function editPrevRegistrationtype()
{
	
	buffervalues();
	$(".RegistrationtypeName").removeAttr("disabled");
	$(".RegistrationtypeCode").removeAttr("disabled");
	$(".Description").removeAttr("disabled");
	$(".isActive").removeAttr("disabled");
	
	$('.saveBtn').show();
	$('.CamcleBtn').show();
	$('.saveBtn').attr("onclick","editregistrationtype()");
	$('.CamcleBtn').attr("onclick","cancel()");
	
	$('#refreshId').addClass('hide');
	$('#addId').addClass('hide');
	$('#deleteId').addClass('hide');
	}

function cancel()
{
	
	$(".RegistrationtypeName").val(registrationtypename);
	$(".RegistrationtypeCode").val(registrationtypecode);
	$(".Description").val(desc);
	$(".registrationtypeid").val(id);
	if(isActive=="Y")
		$('.isActive').prop('checked', true); 
	else
		$('.isActive').prop('checked', false);
	
	$(".RegistrationtypeName").attr("disabled","disabled");
	$(".RegistrationtypeCode").attr("disabled","disabled");
	$(".Description").attr("disabled","disabled");
    $('.isActive').attr("disabled","disabled");
    
    $('.saveBtn').hide();
	$('.CamcleBtn').hide();
	$('.saveBtn').removeAttr("onclick");
	$('.CamcleBtn').removeAttr("onclick");
	
			 registrationtypename="";
			 registrationtypecode="";
			 desc="";
			 isActive="";
			 id="";
			 
			 $('#refreshId').removeClass('hide');
			 $('#addId').removeClass('hide');
			 $('#editId').removeClass('hide');
			 $('#deleteId').removeClass('hide');
	}
function renderRegistrationtypeList()
{ 
	
	$.ajax({
        type : "POST",
        contentType : "application/json",
        url: "getregistrationtypelist",
        dataType:"json",
        async:false,
        success: function (data) {
        	if($.isEmptyObject(data))
        		{
        		  alert("No Registrationtype present in List-Add some Registrationtype");
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
				
				$(".RegistrationtypeName").val(data[i].registrationtypeName);
				$(".RegistrationtypeCode").val(data[i].registrationtypeCode);
				$(".Description").val(data[i].description);
				$(".registrationtypeid").val(data[i].id);
				if(data[i].isActive==true)
					$('.isActive').prop('checked', true); 
				else
					$('.isActive').prop('checked', false);
				
			}
			
		$('#example').append('<li '+active+' onclick="showdetails('+data[i].id+')"> <a href="" class="" data-toggle="tab">'
		           +' <div class="col-md-12">'
		           +'  <label class="col-xs-6">Registrationtype Name</label>'
		           +'   <label class="col-xs-6 mytext" data-registrationtypeid="'+data[i].id+'">'+data[i].registrationtypeName+'</label>'
		           +'  </div>'
		           +'  <div class="col-md-12">'
		           +'    <label class="col-xs-6">Registrationtype Code</label>'
		           +'    <label class="col-xs-6 mytext2">'+data[i].registrationtypeCode+'</label>'
		           +'  </div>'
		           +'  </a>'
		           +'  </li>');
		active="";
          }
	$('.saveBtn').hide();
	$('.CamcleBtn').hide();
	$('.saveBtn').removeAttr("onclick");
	$('.CamcleBtn').removeAttr("onclick");
	
	$(".RegistrationtypeName").attr("disabled","disabled");
	$(".RegistrationtypeCode").attr("disabled","disabled");
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
        url: "getregistrationtype/"+id,
        dataType:"json",
        async:false,
        success: function (data) {
        	if($.isEmptyObject(data))
        		{
        		  alert("Registrationtype details is empty-Add Details");
        		}
        	else
        	{
	        	console.log(data);
	        	 
	        	$(".RegistrationtypeName").val(data.registrationtypeName);
				$(".RegistrationtypeCode").val(data.registrationtypeCode);
				$(".Description").val(data.description);
				$(".registrationtypeid").val(data.id);
				if(data.isActive==true)
					$('.isActive').prop('checked', true); 
				else
					$('.isActive').prop('checked', false);
				
				$('.saveBtn').hide();
				$('.CamcleBtn').hide();
				$('.saveBtn').removeAttr("onclick");
				$('.CamcleBtn').removeAttr("onclick");
				
				$(".RegistrationtypeName").attr("disabled","disabled");
				$(".RegistrationtypeCode").attr("disabled","disabled");
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

function addregistrationtype()
{
	
	var registrationtypeObj={};
	registrationtypeObj.registrationtypeName=$(".RegistrationtypeName").val();
	registrationtypeObj.registrationtypeCode=$(".RegistrationtypeCode").val();
	registrationtypeObj.description=$(".Description").val();
	if ($(".isActive").prop("checked") == true)
		registrationtypeObj.isActive = true;
	else
		registrationtypeObj.isActive = false;
	
		$.ajax({
	        type : "POST",
	        data : JSON.stringify(registrationtypeObj),
	        contentType : "application/json",
	        url: "addregistrationtype",
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

function editregistrationtype()
{
    debugger;	
	var registrationtypeObj={};
	registrationtypeObj.registrationtypeName=$(".RegistrationtypeName").val();
	registrationtypeObj.registrationtypeCode=$(".RegistrationtypeCode").val();
	registrationtypeObj.description=$(".Description").val();
	registrationtypeObj.id=$(".registrationtypeid").val();
	if ($(".isActive").prop("checked") == true)
		registrationtypeObj.isActive = Boolean(true);
	else
		registrationtypeObj.isActive = Boolean(false);
	console.log(registrationtypeObj);
	$.ajax({
        type : "POST",
        data : JSON.stringify(registrationtypeObj),
        contentType : "application/json",
        url: "editregistrationtype",
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

function deleteRegistrationtype()
{
	/*$('#refreshId').addClass('hide');
	$('#addId').addClass('hide');
	$('#editId').addClass('hide');*/
	
	input_box = confirm("Do you really want to delete this Registrationtype?");
	 if (input_box == true) {
			var id=$(".registrationtypeid").val();
			$.ajax({
		        type : "POST",
		        contentType : "application/json",
		        url: "deleteregistrationtype/"+id,
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