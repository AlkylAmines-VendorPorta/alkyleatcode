
$(document).ready(function(){ 
	
	renderDepartmentList();
	
});
var departmentname="";
var departmentcode="";
var desc="";
var isActive="";
var id="";


function buffervalues()
{
	departmentname=$(".DepartmentName").val();;
	departmentcode=$(".DepartmentCode").val();
	desc=$(".Description").val();
	if ($(".isActive").prop("checked") == true)
		isActive = "Y";
	else
	    isActive = "N";
	id=$(".departmentid").val();
	}


function addNewDepartment()
{
	buffervalues();
	$(".DepartmentName").val("");
	$(".DepartmentCode").val("");
	$(".Description").val("");
	$(".departmentid").val("");
	$(".DepartmentName").removeAttr("disabled");
	$(".DepartmentCode").removeAttr("disabled");
	$(".Description").removeAttr("disabled");
	
	
	$('.isActive').prop('checked', true);
	
	
	$('.saveBtn').show();
	$('.saveBtn').attr("onclick","adddepartment()");
	$('.CamcleBtn').show();
	$('.CamcleBtn').attr("onclick","cancel()");
	
	
	$('#refreshId').addClass('hide');
	$('#editId').addClass('hide');
	$('#deleteId').addClass('hide');
	}
function editPrevDepartment()
{
	
	buffervalues();
	$(".DepartmentName").removeAttr("disabled");
	$(".DepartmentCode").removeAttr("disabled");
	$(".Description").removeAttr("disabled");
	$(".isActive").removeAttr("disabled");
	
	$('.saveBtn').show();
	$('.CamcleBtn').show();
	$('.saveBtn').attr("onclick","editdepartment()");
	$('.CamcleBtn').attr("onclick","cancel()");
	
	$('#refreshId').addClass('hide');
	$('#addId').addClass('hide');
	$('#deleteId').addClass('hide');
	}

function cancel()
{
	
	$(".DepartmentName").val(departmentname);
	$(".DepartmentCode").val(departmentcode);
	$(".Description").val(desc);
	$(".departmentid").val(id);
	if(isActive=="Y")
		$('.isActive').prop('checked', true); 
	else
		$('.isActive').prop('checked', false);
	
	$(".DepartmentName").attr("disabled","disabled");
	$(".DepartmentCode").attr("disabled","disabled");
	$(".Description").attr("disabled","disabled");
    $('.isActive').attr("disabled","disabled");
    
    $('.saveBtn').hide();
	$('.CamcleBtn').hide();
	$('.saveBtn').removeAttr("onclick");
	$('.CamcleBtn').removeAttr("onclick");
	
			 departmentname="";
			 departmentcode="";
			 desc="";
			 isActive="";
			 id="";
			 
			 $('#refreshId').removeClass('hide');
			 $('#addId').removeClass('hide');
			 $('#editId').removeClass('hide');
			 $('#deleteId').removeClass('hide');
	}
function renderDepartmentList()
{ 
	
	$.ajax({
        type : "POST",
        contentType : "application/json",
        url: "getdepartmentlist",
        dataType:"json",
        async:false,
        success: function (data) {
        	if($.isEmptyObject(data))
        		{
        		  alert("No Department present in List-Add some Department");
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
				
				$(".DepartmentName").val(data[i].departmentName);
				$(".DepartmentCode").val(data[i].departmentCode);
				$(".Description").val(data[i].description);
				$(".departmentid").val(data[i].id);
				if(data[i].isActive==true)
					$('.isActive').prop('checked', true); 
				else
					$('.isActive').prop('checked', false);
				
			}
			
		$('#example').append('<li '+active+' onclick="showdetails('+data[i].id+')"> <a href="" class="" data-toggle="tab">'
		           +' <div class="col-md-12">'
		           +'  <label class="col-xs-6">Department Name</label>'
		           +'   <label class="col-xs-6 mytext" data-departmentid="'+data[i].id+'">'+data[i].departmentName+'</label>'
		           +'  </div>'
		           +'  <div class="col-md-12">'
		           +'    <label class="col-xs-6">Department Code</label>'
		           +'    <label class="col-xs-6 mytext2">'+data[i].departmentCode+'</label>'
		           +'  </div>'
		           +'  </a>'
		           +'  </li>');
		active="";
          }
	$('.saveBtn').hide();
	$('.CamcleBtn').hide();
	$('.saveBtn').removeAttr("onclick");
	$('.CamcleBtn').removeAttr("onclick");
	
	$(".DepartmentName").attr("disabled","disabled");
	$(".DepartmentCode").attr("disabled","disabled");
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
        url: "getdepartment/"+id,
        dataType:"json",
        async:false,
        success: function (data) {
        	if($.isEmptyObject(data))
        		{
        		  alert("Department details is empty-Add Details");
        		}
        	else
        	{
	        	console.log(data);
	        	 
	        	$(".DepartmentName").val(data.departmentName);
				$(".DepartmentCode").val(data.departmentCode);
				$(".Description").val(data.description);
				$(".departmentid").val(data.id);
				if(data.isActive==true)
					$('.isActive').prop('checked', true); 
				else
					$('.isActive').prop('checked', false);
				
				$('.saveBtn').hide();
				$('.CamcleBtn').hide();
				$('.saveBtn').removeAttr("onclick");
				$('.CamcleBtn').removeAttr("onclick");
				
				$(".DepartmentName").attr("disabled","disabled");
				$(".DepartmentCode").attr("disabled","disabled");
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

function adddepartment()
{
	
	var departmentObj={};
	departmentObj.departmentName=$(".DepartmentName").val();
	departmentObj.departmentCode=$(".DepartmentCode").val();
	departmentObj.description=$(".Description").val();
	if ($(".isActive").prop("checked") == true)
		departmentObj.isActive = true;
	else
		departmentObj.isActive = false;
	
		$.ajax({
	        type : "POST",
	        data : JSON.stringify(departmentObj),
	        contentType : "application/json",
	        url: "adddepartment",
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

function editdepartment()
{
    debugger;	
	var departmentObj={};
	departmentObj.departmentName=$(".DepartmentName").val();
	departmentObj.departmentCode=$(".DepartmentCode").val();
	departmentObj.description=$(".Description").val();
	departmentObj.id=$(".departmentid").val();
	if ($(".isActive").prop("checked") == true)
		departmentObj.isActive = Boolean(true);
	else
		departmentObj.isActive = Boolean(false);
	console.log(departmentObj);
	$.ajax({
        type : "POST",
        data : JSON.stringify(departmentObj),
        contentType : "application/json",
        url: "editdepartment",
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

function deleteDepartment()
{
	/*$('#refreshId').addClass('hide');
	$('#addId').addClass('hide');
	$('#editId').addClass('hide');*/
	
	input_box = confirm("Do you really want to delete this Department?");
	 if (input_box == true) {
			var id=$(".departmentid").val();
			$.ajax({
		        type : "POST",
		        contentType : "application/json",
		        url: "deletedepartment/"+id,
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