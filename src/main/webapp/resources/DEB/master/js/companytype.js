
$(document).ready(function(){ 
	
	renderCompanytypeList();
	
});
var companytypename="";
var companytypecode="";
var desc="";
var isActive="";
var id="";


function buffervalues()
{
	companytypename=$(".CompanytypeName").val();;
	companytypecode=$(".CompanytypeCode").val();
	desc=$(".Description").val();
	if ($(".isActive").prop("checked") == true)
		isActive = "Y";
	else
	    isActive = "N";
	id=$(".companytypeid").val();
	}


function addNewCompanytype()
{
	buffervalues();
	$(".CompanytypeName").val("");
	$(".CompanytypeCode").val("");
	$(".Description").val("");
	$(".companytypeid").val("");
	$(".CompanytypeName").removeAttr("disabled");
	$(".CompanytypeCode").removeAttr("disabled");
	$(".Description").removeAttr("disabled");
	
	
	$('.isActive').prop('checked', true);
	
	
	$('.saveBtn').show();
	$('.saveBtn').attr("onclick","addcompanytype()");
	$('.CamcleBtn').show();
	$('.CamcleBtn').attr("onclick","cancel()");
	
	
	$('#refreshId').addClass('hide');
	$('#editId').addClass('hide');
	$('#deleteId').addClass('hide');
	}
function editPrevCompanytype()
{
	
	buffervalues();
	$(".CompanytypeName").removeAttr("disabled");
	$(".CompanytypeCode").removeAttr("disabled");
	$(".Description").removeAttr("disabled");
	$(".isActive").removeAttr("disabled");
	
	$('.saveBtn').show();
	$('.CamcleBtn').show();
	$('.saveBtn').attr("onclick","editcompanytype()");
	$('.CamcleBtn').attr("onclick","cancel()");
	
	$('#refreshId').addClass('hide');
	$('#addId').addClass('hide');
	$('#deleteId').addClass('hide');
	}

function cancel()
{
	
	$(".CompanytypeName").val(companytypename);
	$(".CompanytypeCode").val(companytypecode);
	$(".Description").val(desc);
	$(".companytypeid").val(id);
	if(isActive=="Y")
		$('.isActive').prop('checked', true); 
	else
		$('.isActive').prop('checked', false);
	
	$(".CompanytypeName").attr("disabled","disabled");
	$(".CompanytypeCode").attr("disabled","disabled");
	$(".Description").attr("disabled","disabled");
    $('.isActive').attr("disabled","disabled");
    
    $('.saveBtn').hide();
	$('.CamcleBtn').hide();
	$('.saveBtn').removeAttr("onclick");
	$('.CamcleBtn').removeAttr("onclick");
	
			 companytypename="";
			 companytypecode="";
			 desc="";
			 isActive="";
			 id="";
			 
			 $('#refreshId').removeClass('hide');
			 $('#addId').removeClass('hide');
			 $('#editId').removeClass('hide');
			 $('#deleteId').removeClass('hide');
	}
function renderCompanytypeList()
{ 
	
	$.ajax({
        type : "POST",
        contentType : "application/json",
        url: "getcompanytypelist",
        dataType:"json",
        async:false,
        success: function (data) {
        	if($.isEmptyObject(data))
        		{
        		  alert("No Companytype present in List-Add some Companytype");
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
				
				$(".CompanytypeName").val(data[i].companytypeName);
				$(".CompanytypeCode").val(data[i].companytypeCode);
				$(".Description").val(data[i].description);
				$(".companytypeid").val(data[i].id);
				if(data[i].isActive==true)
					$('.isActive').prop('checked', true); 
				else
					$('.isActive').prop('checked', false);
				
			}
			
		$('#example').append('<li '+active+' onclick="showdetails('+data[i].id+')"> <a href="" class="" data-toggle="tab">'
		           +' <div class="col-md-12">'
		           +'  <label class="col-xs-6">Companytype Name</label>'
		           +'   <label class="col-xs-6 mytext" data-companytypeid="'+data[i].id+'">'+data[i].companytypeName+'</label>'
		           +'  </div>'
		           +'  <div class="col-md-12">'
		           +'    <label class="col-xs-6">Companytype Code</label>'
		           +'    <label class="col-xs-6 mytext2">'+data[i].companytypeCode+'</label>'
		           +'  </div>'
		           +'  </a>'
		           +'  </li>');
		active="";
          }
	$('.saveBtn').hide();
	$('.CamcleBtn').hide();
	$('.saveBtn').removeAttr("onclick");
	$('.CamcleBtn').removeAttr("onclick");
	
	$(".CompanytypeName").attr("disabled","disabled");
	$(".CompanytypeCode").attr("disabled","disabled");
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
        url: "getcompanytype/"+id,
        dataType:"json",
        async:false,
        success: function (data) {
        	if($.isEmptyObject(data))
        		{
        		  alert("Companytype details is empty-Add Details");
        		}
        	else
        	{
	        	console.log(data);
	        	 
	        	$(".CompanytypeName").val(data.companytypeName);
				$(".CompanytypeCode").val(data.companytypeCode);
				$(".Description").val(data.description);
				$(".companytypeid").val(data.id);
				if(data.isActive==true)
					$('.isActive').prop('checked', true); 
				else
					$('.isActive').prop('checked', false);
				
				$('.saveBtn').hide();
				$('.CamcleBtn').hide();
				$('.saveBtn').removeAttr("onclick");
				$('.CamcleBtn').removeAttr("onclick");
				
				$(".CompanytypeName").attr("disabled","disabled");
				$(".CompanytypeCode").attr("disabled","disabled");
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

function addcompanytype()
{
	
	var companytypeObj={};
	companytypeObj.companytypeName=$(".CompanytypeName").val();
	companytypeObj.companytypeCode=$(".CompanytypeCode").val();
	companytypeObj.description=$(".Description").val();
	if ($(".isActive").prop("checked") == true)
		companytypeObj.isActive = true;
	else
		companytypeObj.isActive = false;
	
		$.ajax({
	        type : "POST",
	        data : JSON.stringify(companytypeObj),
	        contentType : "application/json",
	        url: "addcompanytype",
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

function editcompanytype()
{
   
	var companytypeObj={};
	companytypeObj.companytypeName=$(".CompanytypeName").val();
	companytypeObj.companytypeCode=$(".CompanytypeCode").val();
	companytypeObj.description=$(".Description").val();
	companytypeObj.id=$(".companytypeid").val();
	if ($(".isActive").prop("checked") == true)
		companytypeObj.isActive = Boolean(true);
	else
		companytypeObj.isActive = Boolean(false);
	console.log(companytypeObj);
	$.ajax({
        type : "POST",
        data : JSON.stringify(companytypeObj),
        contentType : "application/json",
        url: "editcompanytype",
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

function deleteCompanytype()
{
	/*$('#refreshId').addClass('hide');
	$('#addId').addClass('hide');
	$('#editId').addClass('hide');*/
	
	input_box = confirm("Do you really want to delete this Companytype?");
	 if (input_box == true) {
			var id=$(".companytypeid").val();
			$.ajax({
		        type : "POST",
		        contentType : "application/json",
		        url: "deletecompanytype/"+id,
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