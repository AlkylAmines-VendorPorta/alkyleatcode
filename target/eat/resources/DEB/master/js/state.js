
$(document).ready(function(){ 
	
	renderStateList();
	
});
var statename="";
var statecode="";
var desc="";
var isActive="";
var country=0;
var id="";


function buffervalues()
{
	statename=$(".StateName").val();;
	statecode=$(".StateCode").val();
	desc=$(".Description").val();
	if ($(".isActive").prop("checked") == true)
		isActive = "Y";
	else
	    isActive = "N";
	id=$(".stateid").val();
	country=("#countryid").val();
	}


function addNewState()
{
	buffervalues();
	$(".StateName").val("");
	$(".StateCode").val("");
	$(".Description").val("");
	$(".stateid").val("");
	$(".StateName").removeAttr("disabled");
	$(".StateCode").removeAttr("disabled");
	$(".Description").removeAttr("disabled");
	$('#countryid').removeAttr("disabled");
	
	$('.isActive').prop('checked', true);
	
	
	$('.saveBtn').show();
	$('.saveBtn').attr("onclick","addstate()");
	$('.CamcleBtn').show();
	$('.CamcleBtn').attr("onclick","cancel()");
	
	
	$('#refreshId').addClass('hide');
	$('#editId').addClass('hide');
	$('#deleteId').addClass('hide');
	}
function editPrevState()
{
	
	buffervalues();
	$(".StateName").removeAttr("disabled");
	$(".StateCode").removeAttr("disabled");
	$(".Description").removeAttr("disabled");
	$(".isActive").removeAttr("disabled");
	$('#countryid').removeAttr("disabled");
	
	$('.saveBtn').show();
	$('.CamcleBtn').show();
	$('.saveBtn').attr("onclick","editstate()");
	$('.CamcleBtn').attr("onclick","cancel()");
	
	$('#refreshId').addClass('hide');
	$('#addId').addClass('hide');
	$('#deleteId').addClass('hide');
	}

function cancel()
{
	
	$(".StateName").val(statename);
	$(".StateCode").val(statecode);
	$(".Description").val(desc);
	$(".stateid").val(id);
	if(isActive=="Y")
		$('.isActive').prop('checked', true); 
	else
		$('.isActive').prop('checked', false);
	("#country").val(country);
	
	$(".StateName").attr("disabled","disabled");
	$(".StateCode").attr("disabled","disabled");
	$(".Description").attr("disabled","disabled");
    $('.isActive').attr("disabled","disabled");
    $('#countryid').attr("disabled","disabled");
    
    $('.saveBtn').hide();
	$('.CamcleBtn').hide();
	$('.saveBtn').removeAttr("onclick");
	$('.CamcleBtn').removeAttr("onclick");
	
			 statename="";
			 statecode="";
			 desc="";
			 isActive="";
			 id="";
			 
			 $('#refreshId').removeClass('hide');
			 $('#addId').removeClass('hide');
			 $('#editId').removeClass('hide');
			 $('#deleteId').removeClass('hide');
	}
function renderStateList()
{ 
	$.ajax({
        type : "POST",
        contentType : "application/json",
        url: "getstatelist",
        dataType:"json",
        async:false,
        success: function (data) {
        	console.log(data.COUNTRY);
        	rendercountrylist(data.COUNTRY);
        	if($.isEmptyObject(data.STATE))
        		{
        		  alert("No State present in List-Add some State");
        		}
        	else
        	{
	        	console.log(data.STATE);
	        	appendList(data.STATE);
	        	
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
				
				$(".StateName").val(data[i].name);
				$(".StateCode").val(data[i].code);
				$(".Description").val(data[i].description);
				$("#countryid").val(data[i].country.id);
				$(".stateid").val(data[i].id);
				if(data[i].isActive==true)
					$('.isActive').prop('checked', true); 
				else
					$('.isActive').prop('checked', false);
				
			}
			
		$('#example').append('<li '+active+' onclick="showdetails('+data[i].id+')"> <a href="" class="" data-toggle="tab">'
		           +' <div class="col-md-12">'
		           +'  <label class="col-xs-6">State Name</label>'
		           +'   <label class="col-xs-6 mytext" data-stateid="'+data[i].id+'">'+data[i].name+'</label>'
		           +'  </div>'
		           +'  <div class="col-md-12">'
		           +'    <label class="col-xs-6">State Code</label>'
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
	
	$(".StateName").attr("disabled","disabled");
	$(".StateCode").attr("disabled","disabled");
	$(".Description").attr("disabled","disabled");
    $('.isActive').attr("disabled","disabled");
    
    $('#countryid').attr("disabled","disabled");
	
	 $('#refreshId').removeClass('hide');
	 $('#addId').removeClass('hide');
	 $('#editId').removeClass('hide');
	 $('#deleteId').removeClass('hide');
	}

function rendercountrylist(data)
	{
	/*$("#countrylist").append(new Option("Select Country", ""));*/
		for (var i=0;i<data.length;i++)  { 
			$("#countryid").append(new Option(data[i].name, data[i].id));
	          }
	}

function showdetails(id)
{
	
	$.ajax({
        type : "POST",
        contentType : "application/json",
        url: "getstate/"+id,
        dataType:"json",
        async:false,
        success: function (data) {
        	if($.isEmptyObject(data))
        		{
        		  alert("State details is empty-Add Details");
        		}
        	else
        	{
	        	console.log(data);
	        	 
	        	$(".StateName").val(data.name);
				$(".StateCode").val(data.code);
				$(".Description").val(data.description);
				$("#countryid").val(data.country.id);
				$(".stateid").val(data.id);
				if(data.isActive==true)
					$('.isActive').prop('checked', true); 
				else
					$('.isActive').prop('checked', false);
				
				$('.saveBtn').hide();
				$('.CamcleBtn').hide();
				$('.saveBtn').removeAttr("onclick");
				$('.CamcleBtn').removeAttr("onclick");
				
				$(".StateName").attr("disabled","disabled");
				$(".StateCode").attr("disabled","disabled");
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

function addstate()
{
	
	var stateObj={};
	stateObj.name=$(".StateName").val();
	stateObj.code=$(".StateCode").val();
	stateObj.description=$(".Description").val();
	stateObj.country={};
	stateObj.country.id=$("#countryid").val();
	if ($(".isActive").prop("checked") == true)
		stateObj.isActive = true;
	else
		stateObj.isActive = false;
	
		$.ajax({
	        type : "POST",
	        data : JSON.stringify(stateObj),
	        contentType : "application/json",
	        url: "addstate",
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

function editstate()
{
    debugger;	
	var stateObj={};
	stateObj.name=$(".StateName").val();
	stateObj.code=$(".StateCode").val();
	stateObj.description=$(".Description").val();
	stateObj.country={};
	stateObj.country.id=$("#countryid").val();
	stateObj.id=$(".stateid").val();
	if ($(".isActive").prop("checked") == true)
		stateObj.isActive = Boolean(true);
	else
		stateObj.isActive = Boolean(false);
	console.log(stateObj);
	$.ajax({
        type : "POST",
        data : JSON.stringify(stateObj),
        contentType : "application/json",
        url: "editstate",
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

function deleteState()
{
	/*$('#refreshId').addClass('hide');
	$('#addId').addClass('hide');
	$('#editId').addClass('hide');*/
	
	input_box = confirm("Do you really want to delete this State?");
	 if (input_box == true) {
			var id=$(".stateid").val();
			$.ajax({
		        type : "POST",
		        contentType : "application/json",
		        url: "deletestate/"+id,
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