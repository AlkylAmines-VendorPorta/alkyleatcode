$(document).ready(function(){ 
	renderList();
	$("#getdatewisedetails").click(function(){
		debugger;
		var startdate =$("#attachuploadstartdate").val();
		var enddate =$("#attachuploadenddate").val();
		if(startdate != '' ||enddate != '' ){
		
		var data = fetchAttachmentListdateWise(startdate,enddate,1, 7);
		appendList(data.data);
		$('#pagination-here').bootpag({
		    total: data.objectMap.LastPage,          
		    page: 1,            
		    maxVisible: 3,     
		    leaps: true,
		    href: "#result-page-{{number}}",
		    pageNumbers: true
		})
			
		}
		$("#attachuploadstartdate").val("");
		$("#attachuploadenddate").val("");
	});
$(".jumptopage").click(function(){
		debugger;
		var jumpIndex = $("#jumpIndex").val();
			if(jumpIndex != ''){
				isServerSidePaginationEmpty = $('#pagination-here').is(':empty');
				if(!isServerSidePaginationEmpty){	
					$('#pagination-here').empty();
					
						var response = fetchAttachmentList(jumpIndex, 7);
						data = response.data;
						var li = '';
						var active=" class='active'";
		        		$('#example').empty();
		        		var activeStatus="";
					        	for (var i=0;i<data.length;i++){
					        	
					        		
					        			if(data[i].isActive=="Y")
					        				activeStatus="Active";
					        			else
					        				activeStatus="InActive";
					        		var createdDate=formatDate(data[i].created)
					        	li = li + '<li onclick="showdetails('+data[i].attachmentId+')"> <a href="" class="" data-toggle="tab">'
					 		           +' <div class="col-md-12">'
					 		           +'  <label class="col-xs-6">'+data[i].partner.name+'</label>'
					 		           +'   <label class="col-xs-6 mytext" data-id="'+data[i].attachmentId+'">'+data[i].partner.name+'</label>'
					 		           +'  </div>'
					 		           +'  <div class="col-md-12">'
					 		           +'    <label class="col-xs-6">'+data[i].fileName+'</label>'
					 		           +'    <label class="col-xs-6 mytext2">'+activeStatus+'</label>'
					 		           +'  </div>'
					 		           +'  </a>'
					 		           +'  </li>';
					        	}
					  		$(".leftPaneData").html(li);
					  		$(".leftPaneData").children().eq(0).click().addClass('active');
						
						$('#pagination-here').bootpag({
				    	    total: response.objectMap.LastPage,          
				    	    page: jumpIndex,            
				    	    maxVisible: 3,     
				    	    leaps: true,
				    	    href: "#result-page-{{number}}",
				    	    pageNumbers: true
				    	})
					}	
		}else{
			Alert.warn('Enter Page number');
		}
	});
});
function getResponse()
{
	Alert.info("Document Downloaded");	
}
function renderList()
{ 
	var data = fetchAttachmentList(1, 7);
/*	$.ajax({
        type : "POST",
        contentType : "application/json",
        url: "getAllAttachmentst",
        dataType:"json",
        async:false,
        success: function (data) {
        	if($.isEmptyObject(data))
        		{
        		  swal("No attachment present in List");
        		}
        	else
        	{
	        	appendList(data);
        	}
		        	
        },
        error: function (e) {
			swal("Exception :");
        }
    });*/
	appendList(data.data);
	$('#pagination-here').bootpag({
	    total: data.objectMap.LastPage,          
	    page: 1,            
	    maxVisible: 3,     
	    leaps: true,
	    href: "#result-page-{{number}}",
	    pageNumbers: true
	})
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
	var createdDate=formatDate(data[i].created)
		if(i==0)
			{
			
				
				$(".Name").text(data[i].partner.name);
				$(".Code").text(data[i].fileName);
				$(".Description").text(createdDate);
				$(".Id").val(data[i].attachmentId);
				var attid =$("#attacmntid").val();
				var url = $("#downloadselectedfiles").data('url');
				$("#downloadselectedfiles").attr('href', url);
				var attachmentId = $("#downloadselectedfiles").prop('href') + '/'+ attid;
				$("#downloadselectedfiles").prop('href', attachmentId);
				if(data[i].isActive=="Y")
				if(activeStatus=="Active")
					$('.isActive').prop('checked', true);
				else
					$('.isActive').prop('checked', false);
				
			$('#masterDetails').empty();
			$('#masterDetails').append('<div class="row"><label class="col-xs-6" ><h4 class="detail_Name">'+data[i].fileName+'</h4></label>'
		            +'<label class="col-xs-6 mytext detail_Code">'+createdDate+'</label></div> '
		            +'<div class="row"><label class="col-xs-6 mytext detail_Desc">'+data[i].partner.name+'</label>'
		            +'<label class="col-xs-6 mytext detail_Active">'+activeStatus+'</label></div>');
	          
			
				
			}
			
		$('#example').append('<li '+active+' onclick="showdetails('+data[i].attachmentId+')"> <a href="" class="" data-toggle="tab">'
		           +' <div class="col-md-12">'
		           +'  <label class="col-xs-6">'+data[i].partner.name+'</label>'
		           +'   <label class="col-xs-6 mytext" data-Id="'+data[i].attachmentId+'">'+data[i].partner.name+'</label>'
		           +'  </div>'
		           +'  <div class="col-md-12">'
		           +'    <label class="col-xs-6">'+data[i].fileName+'</label>'
		           +'    <label class="col-xs-6 mytext2">'+activeStatus+'</label>'
		           +'  </div>'
		           +'  </a>'
		           +'  </li>');
		active="";
          }
	$('#pagination-here').on("page", function(event, num){
		debugger;
		var response = fetchAttachmentList(num, 7);
		data = response.data;
		var li = '';
		var active=" class='active'";
		$('#example').empty();
		var activeStatus="";
	        	for (var i=0;i<data.length;i++)  {
	        		if(data[i].isActive=="Y")
	        			activeStatus="Active";
	        		else
	        			activeStatus="InActive";
	        	li = li + '<li  onclick="showdetails('+data[i].attachmentId+')"> <a href="" class="" data-toggle="tab">'
	 		           +' <div class="col-md-12">'
	 		           +'  <label class="col-xs-6">'+data[i].partner.name+'</label>'
	 		           +'   <label class="col-xs-6 mytext" data-id="'+data[i].attachmentId+'">'+data[i].partner.name+'</label>'
	 		           +'  </div>'
	 		           +'  <div class="col-md-12">'
	 		           +'    <label class="col-xs-6">'+data[i].fileName+'</label>'
	 		           +'    <label class="col-xs-6 mytext2">'+activeStatus+'</label>'
	 		           +'  </div>'
	 		           +'  </a>'
	 		           +'  </li>';
	        	}
	  		$(".leftPaneData").html(li);
	  		$(".leftPaneData").children().eq(0).click().addClass('active');
	});
	
	/*$(".tabs-left li a label").text(function(index, currentText) {
	    return currentText.substr(0, 20);
	});*/
	$('.reportCount').html(data.length);

	}
function showdetails(id)
{
	$.ajax({
        type : "POST",
        contentType : "application/json",
        url: "getAttachmentById/"+id,
        dataType:"json",
        async:false,
        success: function (data) {
        	if($.isEmptyObject(data))
        		{
        		  swal("Attachment details is empty");
        		}
        	else
        	{
	        	 var activeStatus='';
	        	 var createdDate=formatDate(data.created)
	        	 $(".Name").text(data.partner.name);
					$(".Code").text(data.fileName);
					$(".Description").text(createdDate);
					$(".Id").val(data.attachmentId);
					var attid =$("#attacmntid").val();
					var url = $("#downloadselectedfiles").data('url');
					$("#downloadselectedfiles").attr('href', url);
					var attachmentId = $("#downloadselectedfiles").prop('href') + '/'+ attid;
					$("#downloadselectedfiles").prop('href', attachmentId);
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
				
				$(".detail_Name").html(data.fileName);
				$(".detail_Code").html(createdDate);
				$(".detail_Desc").html(data.partner.name);
				$(".detail_Active").html(activeStatus);			
				
        	}
		        	
        },
        error: function (e){
			swal("Exception :");
        }
    });
	
	}
function fetchAttachmentList(pageNumber, pageSize){
	var response;
	$.ajax({
        type : "POST",
        contentType : "application/json",
        url: "getAllAttachmentst/"+pageNumber+"/"+pageSize,
        dataType:"json",
        async:false,
        success: function (data) {
        	response = data;
        },
        error: function (e) {
			Alert.warn(e.message);
        }
    });
	return response;
}
function fetchAttachmentListdateWise(Startdate,EndDate,pageNumber, pageSize){
	var response;
	$('#loading-wrapper').fadeIn("slow");
	$.ajax({
        type : "POST",
        contentType : "application/json",
        url: "getAllAttachmentdateWise/"+Startdate+"/"+EndDate+"/"+pageNumber+"/"+pageSize,
        dataType:"json",
        async:false,
        success: function (data) {
        	response = data;
        	$('#loading-wrapper').fadeOut("slow");
        },
        error: function (e) {
			Alert.warn(e.message);
			$('#loading-wrapper').fadeOut("slow");
        }
    });
	return response;
}

