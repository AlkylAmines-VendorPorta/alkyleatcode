$(document).ready(function(){ 
	
	renderTendertypeList();
	
});
var tendertypename="";
var tendertypecode="";
var desc="";
var isActive="";
var id="";

function buffervalues()
{
	tendertypename=$(".TendertypeName").val();;
	tendertypecode=$(".TendertypeCod").val();
	desc=$(".Description").val();
	if ($(".isActive").prop("checked") == true)
		isActive = "Y";
	else
	    isActive = "N";
	id=$(".tendertypeid").val();
	}

function addNewTendertype()
{
	buffervalues();
	$(".TendertypeName").val("");
	$(".TendertypeCod").val("");
	$(".Description").val("");
	$(".tendertypeid").val("");
	$(".TendertypeName").removeAttr("disabled");
	$(".TendertypeCod").removeAttr("disabled");
	$(".Description").removeAttr("disabled");
	
	
	$('.isActive').prop('checked', true);
	
	
	$('.saveBtn').show();
	$('.saveBtn').attr("onclick","addtendertype()");
	$('.CamcleBtn').show();
	$('.CamcleBtn').attr("onclick","cancel()");
	
	
	$('#refreshId').addClass('hide');
	$('#editId').addClass('hide');
	$('#deleteId').addClass('hide');
}

function editPrevTendertype()
{
	
	buffervalues();
	$(".TendertypeName").removeAttr("disabled");
	$(".TendertypeCod").removeAttr("disabled");
	$(".Description").removeAttr("disabled");
	$(".isActive").removeAttr("disabled");
	
	$('.saveBtn').show();
	$('.CamcleBtn').show();
	$('.saveBtn').attr("onclick","edittendertype()");
	$('.CamcleBtn').attr("onclick","cancel()");
	
	$('#refreshId').addClass('hide');
	$('#addId').addClass('hide');
	$('#deleteId').addClass('hide');
	}

function cancel()
{
	
	$(".TendertypeName").val(tendertypename);
	$(".TendertypeCod").val(tendertypecode);
	$(".Description").val(desc);
	$(".uomid").val(id);
	if(isActive=="Y")
		$('.isActive').prop('checked', true); 
	else
		$('.isActive').prop('checked', false);
	
	$(".TendertypeName").attr("disabled","disabled");
	$(".TendertypeCod").attr("disabled","disabled");
	$(".Description").attr("disabled","disabled");
    $('.isActive').attr("disabled","disabled");
    
    $('.saveBtn').hide();
	$('.CamcleBtn').hide();
	$('.saveBtn').removeAttr("onclick");
	$('.CamcleBtn').removeAttr("onclick");
	
	tendertypename="";
	tendertypecode="";
			 desc="";
			 isActive="";
			 id="";
			 
			 $('#refreshId').removeClass('hide');
			 $('#addId').removeClass('hide');
			 $('#editId').removeClass('hide');
			 $('#deleteId').removeClass('hide');
	}
function renderTendertypeList()
{ 
	
	$.ajax({
        type : "POST",
        contentType : "application/json",
        url: "gettendertypelist",
        dataType:"json",
        async:false,
        success: function (data) {
        	if($.isEmptyObject(data))
        		{
        		  alert("No TenderType present in List-Add some TenderType");
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
				
				$(".TendertypeName").val(data[i].tenderTypeName);
				$(".TendertypeCod").val(data[i].tenderTypeCode);
				$(".Description").val(data[i].desc);
				$(".tendertypeid").val(data[i].id);
				if(data[i].isActive=="Y")
					$('.isActive').prop('checked', true); 
				else
					$('.isActive').prop('checked', false);
				
			}
			
		$('#example').append('<li '+active+' onclick="showdetails('+data[i].id+')"> <a href="" class="" data-toggle="tab">'
		           +' <div class="col-md-12">'
		           +'  <label class="col-xs-6">TenderType Name</label>'
		           +'   <label class="col-xs-6 mytext" data-tendertypeid="'+data[i].id+'">'+data[i].tenderTypeName+'</label>'
		           +'  </div>'
		           +'  <div class="col-md-12">'
		           +'    <label class="col-xs-6">TenderType Code</label>'
		           +'    <label class="col-xs-6 mytext2">'+data[i].tenderTypeCode+'</label>'
		           +'  </div>'
		           +'  </a>'
		           +'  </li>');
		active="";
          }
	$('.saveBtn').hide();
	$('.CamcleBtn').hide();
	$('.saveBtn').removeAttr("onclick");
	$('.CamcleBtn').removeAttr("onclick");
	
	$(".TendertypeName").attr("disabled","disabled");
	$(".TendertypeCod").attr("disabled","disabled");
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
        url: "gettendertype/"+id,
        dataType:"json",
        async:false,
        success: function (data) {
        	if($.isEmptyObject(data))
        		{
        		  alert("TenderType details is empty-Add Details");
        		}
        	else
        	{
	        	console.log(data);
	        	 
	        	$(".TendertypeName").val(data.tenderTypeName);
				$(".TendertypeCod").val(data.tenderTypeCode);
				$(".Description").val(data.desc);
				$(".tendertypeid").val(data.id);
				if(data.isActive=="Y")
					$('.isActive').prop('checked', true); 
				else
					$('.isActive').prop('checked', false);
				
				$('.saveBtn').hide();
				$('.CamcleBtn').hide();
				$('.saveBtn').removeAttr("onclick");
				$('.CamcleBtn').removeAttr("onclick");
				
				$(".TendertypeName").attr("disabled","disabled");
				$(".TendertypeCod").attr("disabled","disabled");
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

function addtendertype()
{
	debugger;
	var tendertypeObj={};
	tendertypeObj.tenderTypeName=$(".TendertypeName").val();
	tendertypeObj.tenderTypeCode=$(".TendertypeCod").val();
	tendertypeObj.desc=$(".Description").val();
	if ($(".isActive").prop("checked") == true)
		tendertypeObj.isActive = "Y";
	else
		tendertypeObj.isActive = "N";
	
		$.ajax({
	        type : "POST",
	        data : JSON.stringify(tendertypeObj),
	        contentType : "application/json",
	        url: "addtendertype",
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

function edittendertype()
{
	debugger;
	var tendertypeObj={};
	tendertypeObj.tenderTypeName=$(".TendertypeName").val();
	tendertypeObj.tenderTypeCode=$(".TendertypeCod").val();
	tendertypeObj.desc=$(".Description").val();
	tendertypeObj.id=$(".tendertypeid").val();
	if ($(".isActive").prop("checked") == true)
		tendertypeObj.isActive = "Y";
	else
		tendertypeObj.isActive = "N";
	
	$.ajax({
        type : "POST",
        data : JSON.stringify(tendertypeObj),
        contentType : "application/json",
        url: "edittendertype",
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

function deleteTendertype()
{
	/*$('#refreshId').addClass('hide');
	$('#addId').addClass('hide');
	$('#editId').addClass('hide');*/
	debugger;
	input_box = confirm("Do you really want to delete this TenderType?");
	 if (input_box == true) {
			var id=$(".tendertypeid").val();
			$.ajax({
		        type : "POST",
		        contentType : "application/json",
		        url: "deletetendertype/"+id,
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