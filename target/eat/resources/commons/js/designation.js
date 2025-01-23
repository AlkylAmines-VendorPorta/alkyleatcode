
$(document).ready(function(){ 
	
	renderDesignationList();
	
});
var designationname="";
var designationcode="";
var desc="";
var isActive="";
var id="";


function buffervalues()
{
	designationname=$(".DesignationName").val();;
	designationcode=$(".DesignationCode").val();
	desc=$(".Description").val();
	if ($(".isActive").prop("checked") == true)
		isActive = "Y";
	else
	    isActive = "N";
	id=$(".designationid").val();
	}


function addNewDesignation()
{
	buffervalues();
	$(".DesignationName").val("");
	$(".DesignationCode").val("");
	$(".Description").val("");
	$(".designationid").val("");
	$(".DesignationName").removeAttr("disabled");
	$(".DesignationCode").removeAttr("disabled");
	$(".Description").removeAttr("disabled");
	
	
	$('.isActive').prop('checked', true);
	
	
	$('.saveBtn').show();
	$('.saveBtn').attr("onclick","adddesignation()");
	$('.CamcleBtn').show();
	$('.CamcleBtn').attr("onclick","cancel()");
	
	
	$('#refreshId').addClass('hide');
	$('#editId').addClass('hide');
	$('#deleteId').addClass('hide');
	}
function editPrevDesignation()
{
	
	buffervalues();
	$(".DesignationName").removeAttr("disabled");
	$(".DesignationCode").removeAttr("disabled");
	$(".Description").removeAttr("disabled");
	$(".isActive").removeAttr("disabled");
	
	$('.saveBtn').show();
	$('.CamcleBtn').show();
	$('.saveBtn').attr("onclick","editdesignation()");
	$('.CamcleBtn').attr("onclick","cancel()");
	
	$('#refreshId').addClass('hide');
	$('#addId').addClass('hide');
	$('#deleteId').addClass('hide');
	}

function cancel()
{
	
	$(".DesignationName").val(designationname);
	$(".DesignationCode").val(designationcode);
	$(".Description").val(desc);
	$(".designationid").val(id);
	if(isActive=="Y")
		$('.isActive').prop('checked', true); 
	else
		$('.isActive').prop('checked', false);
	
	$(".DesignationName").attr("disabled","disabled");
	$(".DesignationCode").attr("disabled","disabled");
	$(".Description").attr("disabled","disabled");
    $('.isActive').attr("disabled","disabled");
    
    $('.saveBtn').hide();
	$('.CamcleBtn').hide();
	$('.saveBtn').removeAttr("onclick");
	$('.CamcleBtn').removeAttr("onclick");
	
			 designationname="";
			 designationcode="";
			 desc="";
			 isActive="";
			 id="";
			 
			 $('#refreshId').removeClass('hide');
			 $('#addId').removeClass('hide');
			 $('#editId').removeClass('hide');
			 $('#deleteId').removeClass('hide');
	}
function renderDesignationList()
{ 
	
	$.ajax({
        type : "POST",
        contentType : "application/json",
        url: "getdesignationlist",
        dataType:"json",
        async:false,
        success: function (data) {
        	if($.isEmptyObject(data))
        		{
        		  alert("No Designation present in List-Add some Designation");
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
				
				$(".DesignationName").val(data[i].designationName);
				$(".DesignationCode").val(data[i].designationCode);
				$(".Description").val(data[i].description);
				$(".designationid").val(data[i].id);
				if(data[i].isActive==true)
					$('.isActive').prop('checked', true); 
				else
					$('.isActive').prop('checked', false);
				
			}
			
		$('#example').append('<li '+active+' onclick="showdetails('+data[i].id+')"> <a href="" class="" data-toggle="tab">'
		           +' <div class="col-md-12">'
		           +'  <label class="col-xs-6">Designation Name</label>'
		           +'   <label class="col-xs-6 mytext" data-designationid="'+data[i].id+'">'+data[i].designationName+'</label>'
		           +'  </div>'
		           +'  <div class="col-md-12">'
		           +'    <label class="col-xs-6">Designation Code</label>'
		           +'    <label class="col-xs-6 mytext2">'+data[i].designationCode+'</label>'
		           +'  </div>'
		           +'  </a>'
		           +'  </li>');
		active="";
          }
	$('.saveBtn').hide();
	$('.CamcleBtn').hide();
	$('.saveBtn').removeAttr("onclick");
	$('.CamcleBtn').removeAttr("onclick");
	
	$(".DesignationName").attr("disabled","disabled");
	$(".DesignationCode").attr("disabled","disabled");
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
        url: "getdesignation/"+id,
        dataType:"json",
        async:false,
        success: function (data) {
        	if($.isEmptyObject(data))
        		{
        		  alert("Designation details is empty-Add Details");
        		}
        	else
        	{
	        	console.log(data);
	        	 
	        	$(".DesignationName").val(data.designationName);
				$(".DesignationCode").val(data.designationCode);
				$(".Description").val(data.description);
				$(".designationid").val(data.id);
				if(data.isActive==true)
					$('.isActive').prop('checked', true); 
				else
					$('.isActive').prop('checked', false);
				
				$('.saveBtn').hide();
				$('.CamcleBtn').hide();
				$('.saveBtn').removeAttr("onclick");
				$('.CamcleBtn').removeAttr("onclick");
				
				$(".DesignationName").attr("disabled","disabled");
				$(".DesignationCode").attr("disabled","disabled");
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

function adddesignation()
{
	
	var designationObj={};
	designationObj.designationName=$(".DesignationName").val();
	designationObj.designationCode=$(".DesignationCode").val();
	designationObj.description=$(".Description").val();
	if ($(".isActive").prop("checked") == true)
		designationObj.isActive = true;
	else
		designationObj.isActive = false;
	
		$.ajax({
	        type : "POST",
	        data : JSON.stringify(designationObj),
	        contentType : "application/json",
	        url: "adddesignation",
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

function editdesignation()
{
    debugger;	
	var designationObj={};
	designationObj.designationName=$(".DesignationName").val();
	designationObj.designationCode=$(".DesignationCode").val();
	designationObj.description=$(".Description").val();
	designationObj.id=$(".designationid").val();
	if ($(".isActive").prop("checked") == true)
		designationObj.isActive = Boolean(true);
	else
		designationObj.isActive = Boolean(false);
	console.log(designationObj);
	$.ajax({
        type : "POST",
        data : JSON.stringify(designationObj),
        contentType : "application/json",
        url: "editdesignation",
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

function deleteDesignation()
{
	/*$('#refreshId').addClass('hide');
	$('#addId').addClass('hide');
	$('#editId').addClass('hide');*/
	
	input_box = confirm("Do you really want to delete this Designation?");
	 if (input_box == true) {
			var id=$(".designationid").val();
			$.ajax({
		        type : "POST",
		        contentType : "application/json",
		        url: "deletedesignation/"+id,
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