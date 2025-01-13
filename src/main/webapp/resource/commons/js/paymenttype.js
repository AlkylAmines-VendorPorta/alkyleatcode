
$(document).ready(function(){ 
	
	renderPaymenttypeList();
	
});
var paymenttypename="";
var paymenttypecode="";
var desc="";
var isActive="";
var id="";


function buffervalues()
{
	paymenttypename=$(".PaymenttypeName").val();;
	paymenttypecode=$(".PaymenttypeCode").val();
	desc=$(".Description").val();
	if ($(".isActive").prop("checked") == true)
		isActive = "Y";
	else
	    isActive = "N";
	id=$(".paymenttypeid").val();
	}


function addNewPaymenttype()
{
	buffervalues();
	$(".PaymenttypeName").val("");
	$(".PaymenttypeCode").val("");
	$(".Description").val("");
	$(".paymenttypeid").val("");
	$(".PaymenttypeName").removeAttr("disabled");
	$(".PaymenttypeCode").removeAttr("disabled");
	$(".Description").removeAttr("disabled");
	
	
	$('.isActive').prop('checked', true);
	
	
	$('.saveBtn').show();
	$('.saveBtn').attr("onclick","addpaymenttype()");
	$('.CamcleBtn').show();
	$('.CamcleBtn').attr("onclick","cancel()");
	
	
	$('#refreshId').addClass('hide');
	$('#editId').addClass('hide');
	$('#deleteId').addClass('hide');
	}
function editPrevPaymenttype()
{
	
	buffervalues();
	$(".PaymenttypeName").removeAttr("disabled");
	$(".PaymenttypeCode").removeAttr("disabled");
	$(".Description").removeAttr("disabled");
	$(".isActive").removeAttr("disabled");
	
	$('.saveBtn').show();
	$('.CamcleBtn').show();
	$('.saveBtn').attr("onclick","editpaymenttype()");
	$('.CamcleBtn').attr("onclick","cancel()");
	
	$('#refreshId').addClass('hide');
	$('#addId').addClass('hide');
	$('#deleteId').addClass('hide');
	}

function cancel()
{
	
	$(".PaymenttypeName").val(paymenttypename);
	$(".PaymenttypeCode").val(paymenttypecode);
	$(".Description").val(desc);
	$(".paymenttypeid").val(id);
	if(isActive=="Y")
		$('.isActive').prop('checked', true); 
	else
		$('.isActive').prop('checked', false);
	
	$(".PaymenttypeName").attr("disabled","disabled");
	$(".PaymenttypeCode").attr("disabled","disabled");
	$(".Description").attr("disabled","disabled");
    $('.isActive').attr("disabled","disabled");
    
    $('.saveBtn').hide();
	$('.CamcleBtn').hide();
	$('.saveBtn').removeAttr("onclick");
	$('.CamcleBtn').removeAttr("onclick");
	
			 paymenttypename="";
			 paymenttypecode="";
			 desc="";
			 isActive="";
			 id="";
			 
			 $('#refreshId').removeClass('hide');
			 $('#addId').removeClass('hide');
			 $('#editId').removeClass('hide');
			 $('#deleteId').removeClass('hide');
	}
function renderPaymenttypeList()
{ 
	
	$.ajax({
        type : "POST",
        contentType : "application/json",
        url: "getpaymenttypelist",
        dataType:"json",
        async:false,
        success: function (data) {
        	if($.isEmptyObject(data))
        		{
        		  alert("No Paymenttype present in List-Add some Paymenttype");
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
				
				$(".PaymenttypeName").val(data[i].name);
				$(".PaymenttypeCode").val(data[i].shortName);
				$(".Description").val(data[i].description);
				$(".paymenttypeid").val(data[i].id);
				if(data[i].isActive=="Y")
					$('.isActive').prop('checked', true); 
				else
					$('.isActive').prop('checked', false);
				
			}
			
		$('#example').append('<li '+active+' onclick="showdetails('+data[i].id+')"> <a href="" class="" data-toggle="tab">'
		           +' <div class="col-md-12">'
		           +'  <label class="col-xs-6">Paymenttype Name</label>'
		           +'   <label class="col-xs-6 mytext" data-paymenttypeid="'+data[i].id+'">'+data[i].name+'</label>'
		           +'  </div>'
		           +'  <div class="col-md-12">'
		           +'    <label class="col-xs-6">Paymenttype Code</label>'
		           +'    <label class="col-xs-6 mytext2">'+data[i].shortName+'</label>'
		           +'  </div>'
		           +'  </a>'
		           +'  </li>');
		active="";
          }
	$('.saveBtn').hide();
	$('.CamcleBtn').hide();
	$('.saveBtn').removeAttr("onclick");
	$('.CamcleBtn').removeAttr("onclick");
	
	$(".PaymenttypeName").attr("disabled","disabled");
	$(".PaymenttypeCode").attr("disabled","disabled");
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
        url: "getpaymenttype/"+id,
        dataType:"json",
        async:false,
        success: function (data) {
        	if($.isEmptyObject(data))
        		{
        		  alert("Paymenttype details is empty-Add Details");
        		}
        	else
        	{
	        	console.log(data);
	        	 
	        	$(".PaymenttypeName").val(data.name);
				$(".PaymenttypeCode").val(data.shortName);
				$(".Description").val(data.description);
				$(".paymenttypeid").val(data.id);
				if(data.isActive=="Y")
					$('.isActive').prop('checked', true); 
				else
					$('.isActive').prop('checked', false);
				
				$('.saveBtn').hide();
				$('.CamcleBtn').hide();
				$('.saveBtn').removeAttr("onclick");
				$('.CamcleBtn').removeAttr("onclick");
				
				$(".PaymenttypeName").attr("disabled","disabled");
				$(".PaymenttypeCode").attr("disabled","disabled");
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

function addpaymenttype()
{
	
	var paymenttypeObj={};
	paymenttypeObj.name=$(".PaymenttypeName").val();
	paymenttypeObj.shortName=$(".PaymenttypeCode").val();
	paymenttypeObj.description=$(".Description").val();
	if ($(".isActive").prop("checked") == true)
		paymenttypeObj.isActive = "Y";
	else
		paymenttypeObj.isActive = "N";
	
		$.ajax({
	        type : "POST",
	        data : JSON.stringify(paymenttypeObj),
	        contentType : "application/json",
	        url: "addpaymenttype",
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

function editpaymenttype()
{
	
	var paymenttypeObj={};
	paymenttypeObj.name=$(".PaymenttypeName").val();
	paymenttypeObj.shortName=$(".PaymenttypeCode").val();
	paymenttypeObj.description=$(".Description").val();
	paymenttypeObj.id=$(".paymenttypeid").val();
	if ($(".isActive").prop("checked") == true)
		paymenttypeObj.isActive = "Y";
	else
		paymenttypeObj.isActive = "N";
	
	$.ajax({
        type : "POST",
        data : JSON.stringify(paymenttypeObj),
        contentType : "application/json",
        url: "editpaymenttype",
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

function deletePaymenttype()
{
	$('#refreshId').addClass('hide');
	$('#addId').addClass('hide');
	$('#editId').addClass('hide');
	
	input_box = confirm("Do you really want to delete this Paymenttype?");
	 if (input_box == true) {
			var id=$(".paymenttypeid").val();
			$.ajax({
		        type : "POST",
		        contentType : "application/json",
		        url: "deletepaymenttype/"+id,
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