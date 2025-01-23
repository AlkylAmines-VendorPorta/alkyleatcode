$(document).ready(function(){ 
	renderList();
	/*$('#searchlitralid').on('keyup', function () {
	    var value = this.value;
	    $('#example li').hide().each(function () {
	        if ($(this).text().toUpperCase().search(value.toUpperCase()) > -1) {
	        	$(this).show();;
	        }
	    });
	});*/
});
function renderList()
{ 
	 
	$.ajax({
        type : "POST",
        contentType : "application/json",
        url: "getFinancialDocumentsList",
        dataType:"json",
        async:false,
        success: function (data) {
        	if($.isEmptyObject(data))
        		{
        		  swal("No Financial Document present in List");
        		}
        	else
        	{
	        	appendList(data);
        	}
		        	
        },
        error: function (e) {
			swal("Exception :");
        }
    });
	}

function appendList(data)
{
	
	var active=" class='active'";
	$('#example').empty();
	var activeStatus="";
	for (var i=0;i<data.length;i++)  { 
		if(data[i].isActive=="Y")
			activeStatus="Active";
		else
			activeStatus="InActive";
		if(i==0)
			{
				
				$(".Name").val(data[i].name);
				$(".Code").val(data[i].code);
				$(".Description").val(data[i].description);
				$(".Id").val(data[i].financialDocumentsId);
				if(activeStatus=="Active")
					$('.isActive').prop('checked', true);
				else
					$('.isActive').prop('checked', false);
				
			$('#masterDetails').empty();
			$('#masterDetails').append('<div class="row"><label class="col-xs-6" ><h4 class="detail_Name">'+data[i].name+'</h4></label>'
		            +'<label class="col-xs-6 mytext detail_Code">'+data[i].code+'</label></div> '
		            +'<div class="row"><label class="col-xs-6 mytext detail_Desc">'+data[i].description+'</label>'
		            +'<label class="col-xs-6 mytext detail_Active">'+activeStatus+'</label></div>');
	          
				
			}
			
		$('#example').append('<li '+active+' onclick="showdetails('+data[i].financialDocumentsId+')"> <a href="" class="" data-toggle="tab">'
		           +' <div class="col-md-12">'
		           +'  <label class="col-xs-6">'+data[i].name+'</label>'
		           +'   <label class="col-xs-6 mytext" data-id="'+data[i].financialDocumentsId+'">'+data[i].description+'</label>'
		           +'  </div>'
		           +'  <div class="col-md-12">'
		           +'    <label class="col-xs-6">'+data[i].code+'</label>'
		           +'    <label class="col-xs-6 mytext2">'+activeStatus+'</label>'
		           +'  </div>'
		           +'  </a>'
		           +'  </li>');
		active="";
          }
$('.example').paginathing();	
	
	$(".tabs-left li a label").text(function(index, currentText) {
	    return currentText.substr(0, 20);
});
	$('.reportCount').html(data.length);
	}
function showdetails(id)
{
	$.ajax({
        type : "POST",
        contentType : "application/json",
        url: "getFinancialDocuments/"+id,
        dataType:"json",
        async:false,
        success: function (data) {
        	if($.isEmptyObject(data))
        		{
        		  swal("Financial Documents is empty");
        		}
        	else
        	{
	        	 var activeStatus="";
	        	$(".Name").val(data.name);
				$(".Code").val(data.code);
				$(".Description").val(data.description);
				$(".Id").val(data.financialDocumentsId);
				if(data.isActive=="Y")
				{
					$('.isActive').prop('checked', true);
					activeStatus="Active";
				}
				else
				{
					$('.isActive').prop('checked', false);
					activeStatus="InActive";
				}
				
				$(".detail_Name").html(data.name);
				$(".detail_Code").html(data.code);
				$(".detail_Desc").html(data.description);
				$(".detail_Active").html(activeStatus);
				
        	}
		        	
        },
        error: function (e) {
			swal("Exception :");
        }
    });}