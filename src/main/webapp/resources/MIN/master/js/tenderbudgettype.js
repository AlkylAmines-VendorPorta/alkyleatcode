
$(document).ready(function(){ 
	
	renderUomList();
	
});
var tenderbudgettypename="";
var tenderbudgettypecode="";
var desc="";
var isActive="";
var id="";


function buffervalues()
{
	tenderbudgettypename=$(".TenderbudgettypeName").val();;
	tenderbudgettypecode=$(".TenderbudgettypeCode").val();
	desc=$(".Description").val();
	if ($(".isActive").prop("checked") == true)
		isActive = "Y";
	else
	    isActive = "N";
	id=$(".tenderbudgettypeid").val();
	}


function addNewTenderbudgettype()
{
	buffervalues();
	$(".TenderbudgettypeName").val("");
	$(".TenderbudgettypeCode").val("");
	$(".Description").val("");
	$(".tenderbudgettypeid").val("");
	$(".TenderbudgettypeName").removeAttr("disabled");
	$(".TenderbudgettypeCode").removeAttr("disabled");
	$(".Description").removeAttr("disabled");
	
	
	$('.isActive').prop('checked', true);
	
	
	$('.saveBtn').show();
	$('.saveBtn').attr("onclick","addtenderbudgettype()");
	$('.CamcleBtn').show();
	$('.CamcleBtn').attr("onclick","cancel()");
	
	
	$('#refreshId').addClass('hide');
	$('#editId').addClass('hide');
	$('#deleteId').addClass('hide');
	}
function editPrevTenderbudgettype()
{
	
	buffervalues();
	$(".TenderbudgettypeName").removeAttr("disabled");
	$(".TenderbudgettypeCode").removeAttr("disabled");
	$(".Description").removeAttr("disabled");
	$(".isActive").removeAttr("disabled");
	
	$('.saveBtn').show();
	$('.CamcleBtn').show();
	$('.saveBtn').attr("onclick","edittenderbudgettype()");
	$('.CamcleBtn').attr("onclick","cancel()");
	
	$('#refreshId').addClass('hide');
	$('#addId').addClass('hide');
	$('#deleteId').addClass('hide');
	}

function cancel()
{
	
	$(".TenderbudgettypeName").val(tenderbudgettypename);
	$(".TenderbudgettypeCode").val(tenderbudgettypecode);
	$(".Description").val(desc);
	$(".tenderbudgettypeid").val(id);
	if(isActive=="Y")
		$('.isActive').prop('checked', true); 
	else
		$('.isActive').prop('checked', false);
	
	$(".TenderbudgettypeName").attr("disabled","disabled");
	$(".TenderbudgettypeCode").attr("disabled","disabled");
	$(".Description").attr("disabled","disabled");
    $('.isActive').attr("disabled","disabled");
    
    $('.saveBtn').hide();
	$('.CamcleBtn').hide();
	$('.saveBtn').removeAttr("onclick");
	$('.CamcleBtn').removeAttr("onclick");
	
			 tenderbudgettypename="";
			 tenderbudgettypecode="";
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
        url: "gettenderbudgettypelist",
        dataType:"json",
        async:false,
        success: function (data) {
        	if($.isEmptyObject(data))
        		{
        		  alert("No TenderBudgettype present in List-Add some TenderBudgettype");
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
				
				$(".TenderbudgettypeName").val(data[i].tenderBudgetTypeName);
				$(".TenderbudgettypeCode").val(data[i].tenderBudgetTypeCode);
				$(".Description").val(data[i].description);
				$(".tenderbudgettypeid").val(data[i].id);
				if(data[i].isActive=="Y")
					$('.isActive').prop('checked', true); 
				else
					$('.isActive').prop('checked', false);
				
			}
			
		$('#example').append('<li '+active+' onclick="showdetails('+data[i].id+')"> <a href="" class="" data-toggle="tab">'
		           +' <div class="col-md-12">'
		           +'  <label class="col-xs-6">Tenderbudgettype Name</label>'
		           +'   <label class="col-xs-6 mytext" data-tenderbudgettypeid="'+data[i].id+'">'+data[i].tenderBudgetTypeName+'</label>'
		           +'  </div>'
		           +'  <div class="col-md-12">'
		           +'    <label class="col-xs-6">Tenderbudgettype Code</label>'
		           +'    <label class="col-xs-6 mytext2">'+data[i].tenderBudgetTypeCode+'</label>'
		           +'  </div>'
		           +'  </a>'
		           +'  </li>');
		active="";
          }
	$('.saveBtn').hide();
	$('.CamcleBtn').hide();
	$('.saveBtn').removeAttr("onclick");
	$('.CamcleBtn').removeAttr("onclick");
	
	$(".TenderbudgettypeName").attr("disabled","disabled");
	$(".TenderbudgettypeCode").attr("disabled","disabled");
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
        url: "getTenderbudgettype/"+id,
        dataType:"json",
        async:false,
        success: function (data) {
        	if($.isEmptyObject(data))
        		{
        		  alert("Tenderbudgettype details is empty-Add Details");
        		}
        	else
        	{
	        	console.log(data);
	        	 
	        	$(".TenderbudgettypeName").val(data.tenderBudgetTypeName);
				$(".TenderbudgettypeCode").val(data.tenderBudgetTypeCode);
				$(".Description").val(data.description);
				$(".tenderbudgettypeid").val(data.id);
				if(data.isActive=="Y")
					$('.isActive').prop('checked', true); 
				else
					$('.isActive').prop('checked', false);
				
				$('.saveBtn').hide();
				$('.CamcleBtn').hide();
				$('.saveBtn').removeAttr("onclick");
				$('.CamcleBtn').removeAttr("onclick");
				
				$(".TenderbudgettypeName").attr("disabled","disabled");
				$(".TenderbudgettypeCode").attr("disabled","disabled");
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

function addtenderbudgettype()
{
	
	var tenderbudgettypeObj={};
	tenderbudgettypeObj.tenderBudgetTypeName=$(".TenderbudgettypeName").val();
	tenderbudgettypeObj.tenderBudgetTypeCode=$(".TenderbudgettypeCode").val();
	tenderbudgettypeObj.description=$(".Description").val();
	if ($(".isActive").prop("checked") == true)
		tenderbudgettypeObj.isActive = "Y";
	else
		tenderbudgettypeObj.isActive = "N";
	
		$.ajax({
	        type : "POST",
	        data : JSON.stringify(tenderbudgettypeObj),
	        contentType : "application/json",
	        url: "addTenderbudgettype",
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

function edittenderbudgettype()
{
	
	var tenderbudgettypeObj={};
	tenderbudgettypeObj.tenderBudgetTypeName=$(".TenderbudgettypeName").val();
	tenderbudgettypeObj.tenderBudgetTypeCode=$(".TenderbudgettypeCode").val();
	tenderbudgettypeObj.description=$(".Description").val();
	tenderbudgettypeObj.id=$(".tenderbudgettypeid").val();
	if ($(".isActive").prop("checked") == true)
		tenderbudgettypeObj.isActive = "Y";
	else
		tenderbudgettypeObj.isActive = "N";
	
	$.ajax({
        type : "POST",
        data : JSON.stringify(tenderbudgettypeObj),
        contentType : "application/json",
        url: "editTenderbudgettype",
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

function deleteTenderbudgettype()
{
	$('#refreshId').addClass('hide');
	$('#addId').addClass('hide');
	$('#editId').addClass('hide');
	
	input_box = confirm("Do you really want to delete this Tenderbudgettypern?");
	 if (input_box == true) {
			var id=$(".tenderbudgettypeid").val();
			$.ajax({
		        type : "POST",
		        contentType : "application/json",
		        url: "deleteTenderbudgettype/"+id,
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