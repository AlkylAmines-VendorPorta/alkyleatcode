
$(document).ready(function(){ 
	
	renderTaxList();
	
});
var taxname="";
var taxcode="";
var desc="";
var isActive="";
var id="";


function buffervalues()
{
	taxname=$(".TaxName").val();;
	taxcode=$(".TaxCode").val();
	desc=$(".Description").val();
	if ($(".isActive").prop("checked") == true)
		isActive = "Y";
	else
	    isActive = "N";
	id=$(".taxid").val();
	}


function addNewTax()
{
	buffervalues();
	$(".TaxName").val("");
	$(".TaxCode").val("");
	$(".Description").val("");
	$(".taxid").val("");
	$(".TaxName").removeAttr("disabled");
	$(".TaxCode").removeAttr("disabled");
	$(".Description").removeAttr("disabled");
	
	
	$('.isActive').prop('checked', true);
	
	
	$('.saveBtn').show();
	$('.saveBtn').attr("onclick","addtax()");
	$('.CamcleBtn').show();
	$('.CamcleBtn').attr("onclick","cancel()");
	
	
	$('#refreshId').addClass('hide');
	$('#editId').addClass('hide');
	$('#deleteId').addClass('hide');
	}
function editPrevTax()
{
	
	buffervalues();
	$(".TaxName").removeAttr("disabled");
	$(".TaxCode").removeAttr("disabled");
	$(".Description").removeAttr("disabled");
	$(".isActive").removeAttr("disabled");
	
	$('.saveBtn').show();
	$('.CamcleBtn').show();
	$('.saveBtn').attr("onclick","edittax()");
	$('.CamcleBtn').attr("onclick","cancel()");
	
	$('#refreshId').addClass('hide');
	$('#addId').addClass('hide');
	$('#deleteId').addClass('hide');
	}

function cancel()
{
	
	$(".TaxName").val(taxname);
	$(".TaxCode").val(taxcode);
	$(".Description").val(desc);
	$(".taxid").val(id);
	if(isActive=="Y")
		$('.isActive').prop('checked', true); 
	else
		$('.isActive').prop('checked', false);
	
	$(".TaxName").attr("disabled","disabled");
	$(".TaxCode").attr("disabled","disabled");
	$(".Description").attr("disabled","disabled");
    $('.isActive').attr("disabled","disabled");
    
    $('.saveBtn').hide();
	$('.CamcleBtn').hide();
	$('.saveBtn').removeAttr("onclick");
	$('.CamcleBtn').removeAttr("onclick");
	
			 taxname="";
			 taxcode="";
			 desc="";
			 isActive="";
			 id="";
			 
			 $('#refreshId').removeClass('hide');
			 $('#addId').removeClass('hide');
			 $('#editId').removeClass('hide');
			 $('#deleteId').removeClass('hide');
	}
function renderTaxList()
{ 
	
	$.ajax({
        type : "POST",
        contentType : "application/json",
        url: "gettaxlist",
        dataType:"json",
        async:false,
        success: function (data) {
        	if($.isEmptyObject(data))
        		{
        		  alert("No Tax present in List-Add some Tax");
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
				
				$(".TaxName").val(data[i].taxName);
				$(".TaxCode").val(data[i].taxCode);
				$(".Description").val(data[i].description);
				$(".taxid").val(data[i].id);
				if(data[i].isActive=="Y")
					$('.isActive').prop('checked', true); 
				else
					$('.isActive').prop('checked', false);
				
			}
			
		$('#example').append('<li '+active+' onclick="showdetails('+data[i].id+')"> <a href="" class="" data-toggle="tab">'
		           +' <div class="col-md-12">'
		           +'  <label class="col-xs-6">Tax Name</label>'
		           +'   <label class="col-xs-6 mytext" data-taxid="'+data[i].id+'">'+data[i].taxName+'</label>'
		           +'  </div>'
		           +'  <div class="col-md-12">'
		           +'    <label class="col-xs-6">Tax Code</label>'
		           +'    <label class="col-xs-6 mytext2">'+data[i].taxCode+'</label>'
		           +'  </div>'
		           +'  </a>'
		           +'  </li>');
		active="";
          }
	$('.saveBtn').hide();
	$('.CamcleBtn').hide();
	$('.saveBtn').removeAttr("onclick");
	$('.CamcleBtn').removeAttr("onclick");
	
	$(".TaxName").attr("disabled","disabled");
	$(".TaxCode").attr("disabled","disabled");
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
        url: "gettax/"+id,
        dataType:"json",
        async:false,
        success: function (data) {
        	if($.isEmptyObject(data))
        		{
        		  alert("Tax details is empty-Add Details");
        		}
        	else
        	{
	        	console.log(data);
	        	 
	        	$(".TaxName").val(data.taxName);
				$(".TaxCode").val(data.taxCode);
				$(".Description").val(data.description);
				$(".taxid").val(data.id);
				if(data.isActive=="Y")
					$('.isActive').prop('checked', true); 
				else
					$('.isActive').prop('checked', false);
				
				$('.saveBtn').hide();
				$('.CamcleBtn').hide();
				$('.saveBtn').removeAttr("onclick");
				$('.CamcleBtn').removeAttr("onclick");
				
				$(".TaxName").attr("disabled","disabled");
				$(".TaxCode").attr("disabled","disabled");
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

function addtax()
{
	
	var taxObj={};
	taxObj.taxName=$(".TaxName").val();
	taxObj.taxCode=$(".TaxCode").val();
	taxObj.description=$(".Description").val();
	if ($(".isActive").prop("checked") == true)
		taxObj.isActive = "Y";
	else
		taxObj.isActive = "N";
	
		$.ajax({
	        type : "POST",
	        data : JSON.stringify(taxObj),
	        contentType : "application/json",
	        url: "addtax",
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

function edittax()
{
	
	var taxObj={};
	taxObj.taxName=$(".TaxName").val();
	taxObj.taxCode=$(".TaxCode").val();
	taxObj.description=$(".Description").val();
	taxObj.id=$(".taxid").val();
	if ($(".isActive").prop("checked") == true)
		taxObj.isActive = "Y";
	else
		taxObj.isActive = "N";
	
	$.ajax({
        type : "POST",
        data : JSON.stringify(taxObj),
        contentType : "application/json",
        url: "edittax",
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

function deleteTax()
{
	/*$('#refreshId').addClass('hide');
	$('#addId').addClass('hide');
	$('#editId').addClass('hide');*/
	
	input_box = confirm("Do you really want to delete this Tax?");
	 if (input_box == true) {
			var id=$(".taxid").val();
			$.ajax({
		        type : "POST",
		        contentType : "application/json",
		        url: "deletetax/"+id,
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