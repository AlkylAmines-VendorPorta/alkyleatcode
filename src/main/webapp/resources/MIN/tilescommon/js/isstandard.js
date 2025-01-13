
$(document).ready(function(){ 
	
	renderList();
	
});
/*var name="";
var code="";
var desc="";
var isActive="";
var id="";*/


/*function buffervalues()
{
	name=$(".Name").val();;
	code=$(".Code").val();
	desc=$(".Description").val();
	if ($(".isActive").prop("checked") == true)
		isActive = "Y";
	else
	    isActive = "N";
	id=$(".Id").val();
	}
*/

/*function addNewBidtype()
{
	buffervalues();
	$(".Name").val("");
	$(".Code").val("");
	$(".Description").val("");
	$(".id").val("");
	$(".Name").removeAttr("disabled");
	$(".Code").removeAttr("disabled");
	$(".Description").removeAttr("disabled");
	
	
	$('.isActive').prop('checked', true);
	
	
	$('.saveBtn').show();
	$('.saveBtn').attr("onclick","addbidtype()");
	$('.CamcleBtn').show();
	$('.CamcleBtn').attr("onclick","cancel()");
	
	
	$('#refreshId').addClass('hide');
	$('#editId').addClass('hide');
	$('#deleteId').addClass('hide');
	}
function editPrevBidtype()
{
	
	buffervalues();
	$(".Name").removeAttr("disabled");
	$(".Code").removeAttr("disabled");
	$(".Description").removeAttr("disabled");
	$(".isActive").removeAttr("disabled");
	
	$('.saveBtn').show();
	$('.CamcleBtn').show();
	$('.saveBtn').attr("onclick","editbidtype()");
	$('.CamcleBtn').attr("onclick","cancel()");
	
	$('#refreshId').addClass('hide');
	$('#addId').addClass('hide');
	$('#deleteId').addClass('hide');
	}

function cancel()
{
	
	$(".Name").val(name);
	$(".Code").val(code);
	$(".Description").val(desc);
	$(".id").val(id);
	if(isActive=="Y")
		$('.isActive').prop('checked', true); 
	else
		$('.isActive').prop('checked', false);
	
	$(".Name").attr("disabled","disabled");
	$(".Code").attr("disabled","disabled");
	$(".Description").attr("disabled","disabled");
    $('.isActive').attr("disabled","disabled");
    
    $('.saveBtn').hide();
	$('.CamcleBtn').hide();
	$('.saveBtn').removeAttr("onclick");
	$('.CamcleBtn').removeAttr("onclick");
	
			 name="";
			 code="";
			 desc="";
			 isActive="";
			 id="";
			 
			 $('#refreshId').removeClass('hide');
			 $('#addId').removeClass('hide');
			 $('#editId').removeClass('hide');
			 $('#deleteId').removeClass('hide');
	}*/
function renderList()
{ 
	debugger;
	$.ajax({
        type : "POST",
        contentType : "application/json",
        url: "getIsStdList",
        dataType:"json",
        async:false,
        success: function (data) {
        	if($.isEmptyObject(data))
        		{
        		  swal("No IsStd present in List");
        		}
        	else
        	{
	        	console.log(data);
	        	appendList(data);
	        	
        	}
		        	
        },
        error: function (e) {
			swal(e.message);
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
				
				$(".Name").val(data[i].name);
				$(".Code").val(data[i].code);
				$(".Description").val(data[i].description);
				$(".Id").val(data[i].id);
				$(".Material").val(data[i].material.id);
				if(data[i].isActive=="Y")
					$('.isActive').prop('checked', true); 
				else
					$('.isActive').prop('checked', false);
				
			}
			
		$('#example').append('<li '+active+' onclick="showdetails('+data[i].id+')"> <a href="" class="" data-toggle="tab">'
		           +' <div class="col-md-12">'
		           +'  <label class="col-xs-6">Name</label>'
		           +'   <label class="col-xs-6 mytext" data-id="'+data[i].id+'">'+data[i].name+'</label>'
		           +'  </div>'
		           +'  <div class="col-md-12">'
		           +'    <label class="col-xs-6">Code</label>'
		           +'    <label class="col-xs-6 mytext2">'+data[i].code+'</label>'
		           +'  </div>'
		           +'  </a>'
		           +'  </li>');
		active="";
          }
	$('.saveBtn').hide();
	$('.CamcleBtn').hide();
	$('.saveBtn').removeAttr("onclick");
	$('.CamcleBtn').removeAttr("onclick");
	
	$(".Name").attr("disabled","disabled");
	$(".Code").attr("disabled","disabled");
	$(".Description").attr("disabled","disabled");
    $('.isActive').attr("disabled","disabled");
	
	 $('#refreshId').removeClass('hide');
	 $('#addId').removeClass('hide');
	 $('#editId').removeClass('hide');
	 $('#deleteId').removeClass('hide');
	}
function showdetails(id)
{
	debugger;
	$.ajax({
        type : "POST",
        contentType : "application/json",
        url: "getIsStd/"+id,
        dataType:"json",
        async:false,
        success: function (data) {
        	if($.isEmptyObject(data))
        		{
        		  swal("IsStd details is empty");
        		}
        	else
        	{
	        	console.log(data);
	        	 
	        	$(".Name").val(data.name);
				$(".Code").val(data.code);
				$(".Description").val(data.description);
				$(".Id").val(data.id);
				$(".Material").val(data.material.id);
				
				
				if(data.isActive=="Y")
					$('.isActive').prop('checked', true); 
				else
					$('.isActive').prop('checked', false);
				
				
				$('.saveBtn').hide();
				$('.CamcleBtn').hide();
				$('.saveBtn').removeAttr("onclick");
				$('.CamcleBtn').removeAttr("onclick");
				
				$(".Name").attr("disabled","disabled");
				$(".Code").attr("disabled","disabled");
				$(".Description").attr("disabled","disabled");
			    $('.isActive').attr("disabled","disabled");
			    
			    $('#refreshId').removeClass('hide');
				 $('#addId').removeClass('hide');
				 $('#editId').removeClass('hide');
				 $('#deleteId').removeClass('hide');
        	}
		        	
        },
        error: function (e) {
			swal(e.message);
        }
    });}

/*function addbidtype()
{
	debugger;
	var bidtypeObj={};
	bidtypeObj.name=$(".Name").val();
	bidtypeObj.code=$(".Code").val();
	bidtypeObj.description=$(".Description").val();
	if ($(".isActive").prop("checked") == true)
		bidtypeObj.isActive = "Y";
	else
		bidtypeObj.isActive = "N";
	
		$.ajax({
	        type : "POST",
	        data : JSON.stringify(bidtypeObj),
	        contentType : "application/json",
	        url: "addbidtype",
	        dataType:"json",
	        async:false,
	        success: function (data) {
	        	console.log(data.DATA);
	        	appendList(data.DATA);
	        	swal(data.MSG);
	        	  	
	        },
	        error: function (e) {
				swal(e.message);
	        }
	    });
	
	}

function editbidtype()
{
	debugger;
	var bidtypeObj={};
	bidtypeObj.name=$(".Name").val();
	bidtypeObj.code=$(".Code").val();
	bidtypeObj.description=$(".Description").val();
	bidtypeObj.id=$(".id").val();
	if ($(".isActive").prop("checked") == true)
		bidtypeObj.isActive = "Y";
	else
		bidtypeObj.isActive = "N";
	
	$.ajax({
        type : "POST",
        data : JSON.stringify(bidtypeObj),
        contentType : "application/json",
        url: "editbidtype",
        dataType:"json",
        async:false,
        success: function (data) {
        	console.log(data.DATA);
        	appendList(data.DATA);
        	swal(data.MSG);
        	
        },
        error: function (e) {
			swal(e.message);
        }
    });
	
	}

function deletebidtype()
{
	$('#refreshId').addClass('hide');
	$('#addId').addClass('hide');
	$('#editId').addClass('hide');
	
	input_box = confirm("Do you really want to delete this Bidtype?");
	 if (input_box == true) {
			var id=$(".id").val();
			$.ajax({
		        type : "POST",
		        contentType : "application/json",
		        url: "deletebidtype/"+id,
		        dataType:"json",
		        async:false,
		        success: function (data) {
		        	console.log(data.DATA);
		        	appendList(data.DATA);
		        	swal(data.MSG);
		        },
		        error: function (e) {
					swal(e.message);
		        }
		    });
	 }
	}*/