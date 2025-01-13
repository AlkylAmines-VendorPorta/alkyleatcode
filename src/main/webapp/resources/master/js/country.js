
$(document).ready(function(){ 
	
	renderCountryList();
	
});
var countryname="";
var countrycode="";
var desc="";
var isActive="";
var id="";


function buffervalues()
{
	countryname=$(".CountryName").val();;
	countrycode=$(".CountryCode").val();
	desc=$(".Description").val();
	if ($(".isActive").prop("checked") == true)
		isActive = "Y";
	else
	    isActive = "N";
	id=$(".countryid").val();
	}


function addNewCountry()
{
	buffervalues();
	$(".CountryName").val("");
	$(".CountryCode").val("");
	$(".Description").val("");
	$(".countryid").val("");
	$(".CountryName").removeAttr("disabled");
	$(".CountryCode").removeAttr("disabled");
	$(".Description").removeAttr("disabled");
	
	
	$('.isActive').prop('checked', true);
	
	
	$('.saveBtn').show();
	$('.saveBtn').attr("onclick","addcountry()");
	$('.CamcleBtn').show();
	$('.CamcleBtn').attr("onclick","cancel()");
	
	
	$('#refreshId').addClass('hide');
	$('#editId').addClass('hide');
	$('#deleteId').addClass('hide');
	}
function editPrevCountry()
{
	
	buffervalues();
	$(".CountryName").removeAttr("disabled");
	$(".CountryCode").removeAttr("disabled");
	$(".Description").removeAttr("disabled");
	$(".isActive").removeAttr("disabled");
	
	$('.saveBtn').show();
	$('.CamcleBtn').show();
	$('.saveBtn').attr("onclick","editcountry()");
	$('.CamcleBtn').attr("onclick","cancel()");
	
	$('#refreshId').addClass('hide');
	$('#addId').addClass('hide');
	$('#deleteId').addClass('hide');
	}

function cancel()
{
	
	$(".CountryName").val(countryname);
	$(".CountryCode").val(countrycode);
	$(".Description").val(desc);
	$(".countryid").val(id);
	if(isActive=="Y")
		$('.isActive').prop('checked', true); 
	else
		$('.isActive').prop('checked', false);
	
	$(".CountryName").attr("disabled","disabled");
	$(".CountryCode").attr("disabled","disabled");
	$(".Description").attr("disabled","disabled");
    $('.isActive').attr("disabled","disabled");
    
    $('.saveBtn').hide();
	$('.CamcleBtn').hide();
	$('.saveBtn').removeAttr("onclick");
	$('.CamcleBtn').removeAttr("onclick");
	
			 countryname="";
			 countrycode="";
			 desc="";
			 isActive="";
			 id="";
			 
			 $('#refreshId').removeClass('hide');
			 $('#addId').removeClass('hide');
			 $('#editId').removeClass('hide');
			 $('#deleteId').removeClass('hide');
	}
function renderCountryList()
{ 
	
	$.ajax({
        type : "POST",
        contentType : "application/json",
        url: "getcountrylist",
        dataType:"json",
        async:false,
        success: function (data) {
        	if($.isEmptyObject(data))
        		{
        		  alert("No Country present in List-Add some Country");
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
				
				$(".CountryName").val(data[i].name);
				$(".CountryCode").val(data[i].code);
				$(".Description").val(data[i].description);
				$(".countryid").val(data[i].id);
				if(data[i].isActive==true)
					$('.isActive').prop('checked', true); 
				else
					$('.isActive').prop('checked', false);
				
			}
			
		$('#example').append('<li '+active+' onclick="showdetails('+data[i].id+')"> <a href="" class="" data-toggle="tab">'
		           +' <div class="col-md-12">'
		           +'  <label class="col-xs-6">Country Name</label>'
		           +'   <label class="col-xs-6 mytext" data-countryid="'+data[i].id+'">'+data[i].name+'</label>'
		           +'  </div>'
		           +'  <div class="col-md-12">'
		           +'    <label class="col-xs-6">Country Code</label>'
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
	
	$(".CountryName").attr("disabled","disabled");
	$(".CountryCode").attr("disabled","disabled");
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
        url: "getcountry/"+id,
        dataType:"json",
        async:false,
        success: function (data) {
        	if($.isEmptyObject(data))
        		{
        		  alert("Country details is empty-Add Details");
        		}
        	else
        	{
	        	console.log(data);
	        	 
	        	$(".CountryName").val(data.name);
				$(".CountryCode").val(data.code);
				$(".Description").val(data.description);
				$(".countryid").val(data.id);
				if(data.isActive==true)
					$('.isActive').prop('checked', true); 
				else
					$('.isActive').prop('checked', false);
				
				$('.saveBtn').hide();
				$('.CamcleBtn').hide();
				$('.saveBtn').removeAttr("onclick");
				$('.CamcleBtn').removeAttr("onclick");
				
				$(".CountryName").attr("disabled","disabled");
				$(".CountryCode").attr("disabled","disabled");
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

function addcountry()
{
	
	var countryObj={};
	countryObj.name=$(".CountryName").val();
	countryObj.code=$(".CountryCode").val();
	countryObj.description=$(".Description").val();
	if ($(".isActive").prop("checked") == true)
		countryObj.isActive = true;
	else
		countryObj.isActive = false;
	
		$.ajax({
	        type : "POST",
	        data : JSON.stringify(countryObj),
	        contentType : "application/json",
	        url: "addcountry",
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

function editcountry()
{
    
	var countryObj={};
	countryObj.name=$(".CountryName").val();
	countryObj.code=$(".CountryCode").val();
	countryObj.description=$(".Description").val();
	countryObj.id=$(".countryid").val();
	if ($(".isActive").prop("checked") == true)
		countryObj.isActive = Boolean(true);
	else
		countryObj.isActive = Boolean(false);
	console.log(countryObj);
	$.ajax({
        type : "POST",
        data : JSON.stringify(countryObj),
        contentType : "application/json",
        url: "editcountry",
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

function deleteCountry()
{
	/*$('#refreshId').addClass('hide');
	$('#addId').addClass('hide');
	$('#editId').addClass('hide');*/
	
	input_box = confirm("Do you really want to delete this Country?");
	 if (input_box == true) {
			var id=$(".countryid").val();
			$.ajax({
		        type : "POST",
		        contentType : "application/json",
		        url: "deletecountry/"+id,
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