
$(document).ready(function(){ 
	
	renderBidtypeList();
	
});
var bidtypename="";
var bidtypecode="";
var desc="";
var isActive="";
var id="";


function buffervalues()
{
	bidtypename=$(".BidtypeName").val();;
	bidtypecode=$(".BidtypeCode").val();
	desc=$(".Description").val();
	if ($(".isActive").prop("checked") == true)
		isActive = "Y";
	else
	    isActive = "N";
	id=$(".bidtypeid").val();
	}


function addNewBidtype()
{
	buffervalues();
	$(".BidtypeName").val("");
	$(".BidtypeCode").val("");
	$(".Description").val("");
	$(".bidtypeid").val("");
	$(".BidtypeName").removeAttr("disabled");
	$(".BidtypeCode").removeAttr("disabled");
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
	$(".BidtypeName").removeAttr("disabled");
	$(".BidtypeCode").removeAttr("disabled");
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
	
	$(".BidtypeName").val(bidtypename);
	$(".BidtypeCode").val(bidtypecode);
	$(".Description").val(desc);
	$(".bidtypeid").val(id);
	if(isActive=="Y")
		$('.isActive').prop('checked', true); 
	else
		$('.isActive').prop('checked', false);
	
	$(".BidtypeName").attr("disabled","disabled");
	$(".BidtypeCode").attr("disabled","disabled");
	$(".Description").attr("disabled","disabled");
    $('.isActive').attr("disabled","disabled");
    
    $('.saveBtn').hide();
	$('.CamcleBtn').hide();
	$('.saveBtn').removeAttr("onclick");
	$('.CamcleBtn').removeAttr("onclick");
	
			 bidtypename="";
			 bidtypecode="";
			 desc="";
			 isActive="";
			 id="";
			 
			 $('#refreshId').removeClass('hide');
			 $('#addId').removeClass('hide');
			 $('#editId').removeClass('hide');
			 $('#deleteId').removeClass('hide');
	}
function renderBidtypeList()
{ 
	
	$.ajax({
        type : "POST",
        contentType : "application/json",
        url: "getbidtypelist",
        dataType:"json",
        async:false,
        success: function (data) {
        	if($.isEmptyObject(data))
        		{
        		  alert("No Bidtype present in List-Add some Bidtype");
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
				
				$(".BidtypeName").val(data[i].bidTypeName);
				$(".BidtypeCode").val(data[i].bidTypeCode);
				$(".Description").val(data[i].description);
				$(".bidtypeid").val(data[i].id);
				if(data[i].isActive=="Y")
					$('.isActive').prop('checked', true); 
				else
					$('.isActive').prop('checked', false);
				
			}
			
		$('#example').append('<li '+active+' onclick="showdetails('+data[i].id+')"> <a href="" class="" data-toggle="tab">'
		           +' <div class="col-md-12">'
		           +'  <label class="col-xs-6">Bidtype Name</label>'
		           +'   <label class="col-xs-6 mytext" data-bidtypeid="'+data[i].id+'">'+data[i].bidTypeName+'</label>'
		           +'  </div>'
		           +'  <div class="col-md-12">'
		           +'    <label class="col-xs-6">Bidtype Code</label>'
		           +'    <label class="col-xs-6 mytext2">'+data[i].bidTypeCode+'</label>'
		           +'  </div>'
		           +'  </a>'
		           +'  </li>');
		active="";
          }
	$('.saveBtn').hide();
	$('.CamcleBtn').hide();
	$('.saveBtn').removeAttr("onclick");
	$('.CamcleBtn').removeAttr("onclick");
	
	$(".BidtypeName").attr("disabled","disabled");
	$(".BidtypeCode").attr("disabled","disabled");
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
        url: "getbidtype/"+id,
        dataType:"json",
        async:false,
        success: function (data) {
        	if($.isEmptyObject(data))
        		{
        		  alert("Bidtype details is empty-Add Details");
        		}
        	else
        	{
	        	console.log(data);
	        	 
	        	$(".BidtypeName").val(data.bidTypeName);
				$(".BidtypeCode").val(data.bidTypeCode);
				$(".Description").val(data.description);
				$(".bidtypeid").val(data.id);
				if(data.isActive=="Y")
					$('.isActive').prop('checked', true); 
				else
					$('.isActive').prop('checked', false);
				
				$('.saveBtn').hide();
				$('.CamcleBtn').hide();
				$('.saveBtn').removeAttr("onclick");
				$('.CamcleBtn').removeAttr("onclick");
				
				$(".BidtypeName").attr("disabled","disabled");
				$(".BidtypeCode").attr("disabled","disabled");
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

function addbidtype()
{
	debugger;
	var bidtypeObj={};
	bidtypeObj.bidTypeName=$(".BidtypeName").val();
	bidtypeObj.bidTypeCode=$(".BidtypeCode").val();
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
	        	alert(data.MSG);
	        	  	
	        },
	        error: function (e) {
				alert(e.message);
	        }
	    });
	
	}

function editbidtype()
{
	debugger;
	var bidtypeObj={};
	bidtypeObj.bidTypeName=$(".BidtypeName").val();
	bidtypeObj.bidTypeCode=$(".BidtypeCode").val();
	bidtypeObj.description=$(".Description").val();
	bidtypeObj.id=$(".bidtypeid").val();
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
        	alert(data.MSG);
        	
        },
        error: function (e) {
			alert(e.message);
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
			var id=$(".bidtypeid").val();
			$.ajax({
		        type : "POST",
		        contentType : "application/json",
		        url: "deletebidtype/"+id,
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