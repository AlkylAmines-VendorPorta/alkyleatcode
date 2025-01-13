
$(document).ready(function(){ 
	
	renderUomList();
	
});
swal({
	  title: 'Error!',
	  text: 'Do you want to continue',
	  type: 'error',
	  confirmButtonText: 'Cool'
	});

swal({
	  title: 'Are you sure?',
	  text: "You won't be able to revert this!",
	  type: 'warning',
	  showCancelButton: true,
	  confirmButtonColor: '#3085d6',
	  cancelButtonColor: '#d33',
	  confirmButtonText: 'Yes, delete it!'
	}).then((result) => {
	  if (result.value) {
	    swal(
	      'Deleted!',
	      'Your file has been deleted.',
	      'success'
	    )
	  }
	});

/*swal(
		  'Good job!',
		  'You clicked the button!',
		  'success'
		);*/
var uomname="";
var uomcode="";
var desc="";
var isActive="";
var id="";


function buffervalues()
{
	uomname=$(".UOMName").val();;
	uomcode=$(".UOMCod").val();
	desc=$(".Description").val();
	if ($(".isActive").prop("checked") == true)
		isActive = "Y";
	else
	    isActive = "N";
	id=$(".uomid").val();
	}


function addNewUom()
{
	buffervalues();
	$(".UOMName").val("");
	$(".UOMCod").val("");
	$(".Description").val("");
	$(".uomid").val("");
	$(".UOMName").removeAttr("disabled");
	$(".UOMCod").removeAttr("disabled");
	$(".Description").removeAttr("disabled");
	
	
	$('.isActive').prop('checked', true);
	
	
	$('.saveBtn').show();
	$('.saveBtn').attr("onclick","adduom()");
	$('.CamcleBtn').show();
	$('.CamcleBtn').attr("onclick","cancel()");
	
	
	$('#refreshId').addClass('hide');
	$('#editId').addClass('hide');
	$('#deleteId').addClass('hide');
	}
function editPrevUom()
{
	
	buffervalues();
	$(".UOMName").removeAttr("disabled");
	$(".UOMCod").removeAttr("disabled");
	$(".Description").removeAttr("disabled");
	$(".isActive").removeAttr("disabled");
	
	$('.saveBtn').show();
	$('.CamcleBtn').show();
	$('.saveBtn').attr("onclick","edituom()");
	$('.CamcleBtn').attr("onclick","cancel()");
	
	$('#refreshId').addClass('hide');
	$('#addId').addClass('hide');
	$('#deleteId').addClass('hide');
	}

function cancel()
{
	
	$(".UOMName").val(uomname);
	$(".UOMCod").val(uomcode);
	$(".Description").val(desc);
	$(".uomid").val(id);
	if(isActive=="Y")
		$('.isActive').prop('checked', true); 
	else
		$('.isActive').prop('checked', false);
	
	$(".UOMName").attr("disabled","disabled");
	$(".UOMCod").attr("disabled","disabled");
	$(".Description").attr("disabled","disabled");
    $('.isActive').attr("disabled","disabled");
    
    $('.saveBtn').hide();
	$('.CamcleBtn').hide();
	$('.saveBtn').removeAttr("onclick");
	$('.CamcleBtn').removeAttr("onclick");
	
			 uomname="";
			 uomcode="";
			 desc="";
			 isActive="";
			 id="";
			 
			 $('#refreshId').removeClass('hide');
			 $('#addId').removeClass('hide');
			 $('#editId').removeClass('hide');
			 $('#deleteId').removeClass('hide');
	}
function renderUomList()
{ 
	
	$.ajax({
        type : "POST",
        contentType : "application/json",
        url: "getuomlist",
        dataType:"json",
        async:false,
        success: function (data) {
        	if($.isEmptyObject(data))
        		{
        		  alert("No Uom present in List-Add some uom");
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
				
				$(".UOMName").val(data[i].uomName);
				$(".UOMCod").val(data[i].uomCode);
				$(".Description").val(data[i].descprition);
				$(".uomid").val(data[i].id);
				if(data[i].isActive=="Y")
					$('.isActive').prop('checked', true); 
				else
					$('.isActive').prop('checked', false);
				
			}
			
		$('#example').append('<li '+active+' onclick="showdetails('+data[i].id+')"> <a href="" class="" data-toggle="tab">'
		           +' <div class="col-md-12">'
		           +'  <label class="col-xs-6">Uom Name</label>'
		           +'   <label class="col-xs-6 mytext" data-uomid="'+data[i].id+'">'+data[i].uomName+'</label>'
		           +'  </div>'
		           +'  <div class="col-md-12">'
		           +'    <label class="col-xs-6">UOM Code</label>'
		           +'    <label class="col-xs-6 mytext2">'+data[i].uomCode+'</label>'
		           +'  </div>'
		           +'  </a>'
		           +'  </li>');
		active="";
          }
	$('.saveBtn').hide();
	$('.CamcleBtn').hide();
	$('.saveBtn').removeAttr("onclick");
	$('.CamcleBtn').removeAttr("onclick");
	
	$(".UOMName").attr("disabled","disabled");
	$(".UOMCod").attr("disabled","disabled");
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
        url: "getuom/"+id,
        dataType:"json",
        async:false,
        success: function (data) {
        	if($.isEmptyObject(data))
        		{
        		  alert("Uom details is empty-Add Details");
        		}
        	else
        	{
	        	console.log(data);
	        	 
	        	$(".UOMName").val(data.uomName);
				$(".UOMCod").val(data.uomCode);
				$(".Description").val(data.descprition);
				$(".uomid").val(data.id);
				if(data.isActive=="Y")
					$('.isActive').prop('checked', true); 
				else
					$('.isActive').prop('checked', false);
				
				$('.saveBtn').hide();
				$('.CamcleBtn').hide();
				$('.saveBtn').removeAttr("onclick");
				$('.CamcleBtn').removeAttr("onclick");
				
				$(".UOMName").attr("disabled","disabled");
				$(".UOMCod").attr("disabled","disabled");
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

function adduom()
{
	
	var uomObj={};
	uomObj.uomName=$(".UOMName").val();
	uomObj.uomCode=$(".UOMCod").val();
	uomObj.descprition=$(".Description").val();
	if ($(".isActive").prop("checked") == true)
		uomObj.isActive = "Y";
	else
		uomObj.isActive = "N";
	
		$.ajax({
	        type : "POST",
	        data : JSON.stringify(uomObj),
	        contentType : "application/json",
	        url: "adduom",
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

function edituom()
{
	
	var uomObj={};
	uomObj.uomName=$(".UOMName").val();
	uomObj.uomCode=$(".UOMCod").val();
	uomObj.descprition=$(".Description").val();
	uomObj.id=$(".uomid").val();
	if ($(".isActive").prop("checked") == true)
		uomObj.isActive = "Y";
	else
		uomObj.isActive = "N";
	
	$.ajax({
        type : "POST",
        data : JSON.stringify(uomObj),
        contentType : "application/json",
        url: "edituom",
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

function deleteuom()
{
	/*$('#refreshId').addClass('hide');
	$('#addId').addClass('hide');
	$('#editId').addClass('hide');*/
	
	input_box = confirm("Do you really want to delete this UOM?");
	 if (input_box == true) {
			var id=$(".uomid").val();
			$.ajax({
		        type : "POST",
		        contentType : "application/json",
		        url: "deleteuom/"+id,
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