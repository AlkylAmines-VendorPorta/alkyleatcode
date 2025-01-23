$(document).ready(function(){ 
	
	renderList();
	$('.indirectFormSubmit').click(function(event) {
        event.preventDefault();
        		submitIt("formId",$('#formId').attr('action'), "processResponse");
        return false;
    });
	/*$('#searchlitralid').on('keyup', function () {
	    var value = this.value;
	    $('#example li').hide().each(function () {
	        if ($(this).text().toUpperCase().search(value.toUpperCase()) > -1) {
	        	$(this).show();;
	        }
	    });
	});*/
	
});
function formatDate(longDate){
	   var dt=new Date(longDate);
	   var dd=dt.getDate();
	   var MM=dt.getMonth()+1;
	   var yyyy=dt.getFullYear();
	   var HH= dt.getHours();
	   var mm= dt.getMinutes();
	   var ss= dt.getSeconds();
	   
	   return yyyy+'-'+MM+'-'+dd;
	   /*+' '+HH+':'+mm+':'+ss;*/
}
var name="";
var code="";
var desc="";
var isActive="";
var id="";
var publishingDate=""
var tenderSaleOpeningDate="";
var tenderSaleClosingDate="";
var matter="";
var title="";
var link="";

var validate=false;

function getPrevValues()
{
	name=$(".Name").val();;
	code=$(".Code").val();
	desc=$(".Description").val();
	if ($(".isActive").prop("checked") == true)
		isActive = "Y";
	else
	    isActive = "N";
	id=$(".Id").val();
	publishingDate=$(".PublishingDate").val();
	tenderSaleOpeningDate=$(".TenderSaleOpeningDate").val();
	tenderSaleClosingDate=$(".TenderSaleClosingDate").val();
	matter=$(".Matter").val();
	title=$(".Title").val();
	}

function setPrevValues()
{
	$(".Name").val(name);
	$(".Code").val(code);
	$(".Description").val(desc);
	if (isActive == "Y")
		$('.isActive').prop('checked', true);
	else
		$('.isActive').prop('checked', false);
	$(".Id").val(id);
	
	$(".PublishingDate").val(publishingDate);
	$(".TenderSaleOpeningDate").val(tenderSaleOpeningDate);
	$(".TenderSaleClosingDate").val(tenderSaleClosingDate);
	$(".Matter").val(matter);
	$(".Title").val(title);
	}

function validateInputs()
{
	$('#formId :input[type=text]').each(function(){
		$('#errorMsg').show();
		var fieldname=$(this).attr("name");
		if(fieldname=="Id")
			{
				validate=true;
			}
		else if($(this).val().trim()!=""){
		   validate=true;
	    }else 
	    {
	    	$('#errorMsg').html("'"+fieldname+" is required"+"'")
	    	validate=false;
	    	return false;}
	 });
	/*if($(".").val()==0 ||$(".").val()==null)
		{
			$('#errorMsg').html("'...... is required'")
	    	validate=false;
	    	return false;
		}*/
	}

	function addPublicNotice()
	{
		getPrevValues();
		$(".Name").val("");
		$(".Code").val("");
		$(".Description").val("");
		$(".Id").val("");
		$(".OpeningDate").val("");
		$(".ClosingDate").val("");
		
		$(".Title").val("");
		$(".Matter").val("");
		
		$(".TenderSaleOpeningDate").val("");
		$(".TenderSaleClosingDate").val("");
		
		$(".PublishingDate").val("");
		
		$('#errorMsg').hide();
		
		$('#formId').removeClass('readonly');
		$('.isActive').prop('checked', true);
		/*$('.isActive').attr('readonly',true);*/
		
		$('.saveBtn').show();
		$('#formId').attr("action","savePublicNotices");
		$('.CancelBtn').show();
		$('.CancelBtn').attr("onclick","cancel()");
		
		
		$('#refreshId').addClass('readonly');
		$('#editId').addClass('readonly');
		$('#deleteId').addClass('readonly');
	}
function editPublicNotice()
{
	
	getPrevValues();
	$('#formId').removeClass('readonly');
	$('#errorMsg').hide();
	$('.saveBtn').show();
	$('.CancelBtn').show();
	$('#formId').attr("action","editPublicNotices");
	$('.CancelBtn').attr("onclick","cancel()");
	
	$('#refreshId').addClass('readonly');
	$('#addId').addClass('readonly');
	$('#deleteId').addClass('readonly');
	}

function cancel()
{
	setPrevValues();
	$('#formId').addClass('readonly');
	$('#errorMsg').hide();
    $('.saveBtn').hide();
	$('.CancelBtn').hide();
	$('.saveBtn').removeAttr("onclick");
	$('.CancelBtn').removeAttr("onclick");
	/*if(isActive=="Y")
		$('.isActive').prop('checked', true);
	else
		$('.isActive').removeAttr('checked');*/
	name="";
	code="";
	desc="";
	isActive="";
	id="";
	openingdate="";
	closingdate="";
	link="";
			 
			 $('#refreshId').removeClass('readonly');
			 $('#addId').removeClass('hidreadonlye');
			 $('#editId').removeClass('readonly');
			 $('#deleteId').removeClass('readonly');
	}
function renderList()
{ 
	
	$.ajax({
        type : "POST",
        contentType : "application/json",
        url: "getPublicNoticelist",
        dataType:"json",
        async:false,
        success: function (data) {
        	if($.isEmptyObject(data))
        		{
        		  Alert.info("No PublicNotice present in List");
        		  $('#masterDetails').append('<div class="row"><label class="col-xs-6" ><h4 class="detail_Name"></h4></label>'
  			            +'<label class="col-xs-6 mytext detail_Code"></label></div> '
  			            +'<div class="row"><label class="col-xs-6 mytext detail_Desc"></label>'
  			            +'<label class="col-xs-6 mytext detail_Active"></label></div>');
        		  $('.CancelBtn').hide();
        		}
        	else
        	{
	        	appendList(data);
        	}
        },
        error: function (e) {
			Alert.warn("Exception :");
        }
    });
	}
function appendList(data)
{
	debugger;
	var active=" class='active'";
	$('.pagination').children().remove();
	$('#example').empty();
	var activeStatus="";
	for (var i=0;i<data.length;i++)  { 
		var dataName=data[i].name==null?'':data[i].name;
		var dataCode=data[i].code==null?'':data[i].code;
		var dataDescription=data[i].description==null?'':data[i].description;
		var dataId=data[i].publicNoticeId==null?'':data[i].publicNoticeId;
		var dataTitle=data[i].title==null?'':data[i].title;
		var dataMatter=data[i].matter==null?'':data[i].matter;
		var tenderSaleOpeningDate=data[i].tenderSaleOpeningDate==null?'':formatDate(data[i].tenderSaleOpeningDate);
		var tenderSaleClosingDate=data[i].tenderSaleClosingDate==null?'':formatDate(data[i].tenderSaleClosingDate);
		var publishingDate=data[i].publishingDate==null?'':formatDate(data[i].publishingDate);
		if(data[i].isActive=="Y")
			activeStatus="Active";
		else
			activeStatus="InActive";
		if(i==0)
			{
				
				$(".Name").val(dataName);
				$(".Code").val(dataCode);
				$(".Description").val(dataDescription);
				$(".Id").val(dataId);
				
				$(".Title").val(dataTitle);
				$(".Matter").val(dataMatter);
				$(".TenderSaleOpeningDate").val(tenderSaleOpeningDate);
				$(".TenderSaleClosingDate").val(tenderSaleClosingDate);
				
				$(".PublishingDate").val(publishingDate);
				
				if(activeStatus=="Active")
					$('.isActive').prop('checked', true);
				else
					$('.isActive').prop('checked', false);
				$('#masterDetails').empty();
				$('#masterDetails').append('<div class="row"><label class="col-xs-6" ><h4 class="detail_Name">'+dataName+'</h4></label>'
			            +'<label class="col-xs-6 mytext detail_Code">'+dataCode+'</label></div> '
			            +'<div class="row"><label class="col-xs-6 mytext detail_Desc">'+dataDescription+'</label>'
			            +'<label class="col-xs-6 mytext detail_Active">'+tenderSaleClosingDate+'</label></div>');
			}
			
		$('#example').append('<li '+active+' onclick="showdetails('+dataId+')" id="'+dataId+'"> <a href="" class="" data-toggle="tab">'
		           +' <div class="col-md-12">'
		           +'  <label class="col-xs-6">'+dataName+'</label>'
		           +'   <label class="col-xs-6 mytext" data-Id="'+dataId+'">'+dataDescription+'</label>'
		           +'  </div>'
		           +'  <div class="col-md-12">'
		           +'    <label class="col-xs-6">'+dataCode+'</label>'
		           +'    <label class="col-xs-6 mytext2">'+tenderSaleClosingDate+'</label>'
		           +'  </div>'
		           +'  </a>'
		           +'  </li>');
		active="";
          }
	$(".tabs-left li a label").text(function(index, currentText) {
	    return currentText.substr(0, 18);
});
$('.example').paginathing();

	
	

	$('.reportCount').html(data.length);
	$('.saveBtn').hide();
	$('.CancelBtn').hide();
	$('.saveBtn').removeAttr("onclick");
	$('.CancelBtn').removeAttr("onclick");
	
	$('#formId').addClass('readonly');
	 $('#refreshId').removeClass('readonly');
	 $('#addId').removeClass('readonly');
	 $('#editId').removeClass('readonly');
	 $('#deleteId').removeClass('readonly');
	}
function showdetails(id)
{
	
	$.ajax({
        type : "POST",
        contentType : "application/json",
        url: "getPublicNotice/"+id,
        dataType:"json",
        async:false,
        success: function (data) {
        	if($.isEmptyObject(data))
        		{
        		  Alert.info("Public Notice details is empty");
        		}
        	else
        	{
	        	 
	        	$(".Name").val(data.name);
				$(".Code").val(data.code);
				$(".Description").val(data.description);
				$(".Title").val(data.title);
				$(".Matter").val(data.matter);
				$(".TenderSaleOpeningDate").val(formatDate(data.tenderSaleOpeningDate));
				$(".TenderSaleClosingDate").val(formatDate(data.tenderSaleClosingDate));
				
				$(".PublishingDate").val(formatDate(data.publishingDate));
				$(".Id").val(data.publicNoticeId);
				if(data.isActive=="Y")
					$('.isActive').prop('checked', true); 
				else
					$('.isActive').prop('checked', false);
				
				
				$(".detail_Name").html(data.name);
				$(".detail_Code").html(data.code);
				$(".detail_Desc").html(data.description);
				$(".detail_Active").html(formatDate(data.tenderSaleClosingDate));
				
				$('.saveBtn').hide();
				$('.CancelBtn').hide();
				$('.saveBtn').removeAttr("onclick");
				$('.CancelBtn').removeAttr("onclick");
				$('#errorMsg').hide();
				$('#formId').addClass('readonly');
			    $('#refreshId').removeClass('readonly');
				 $('#addId').removeClass('readonly');
				 $('#editId').removeClass('readonly');
				 $('#deleteId').removeClass('readonly');
        	}
		        	
        },
        error: function (e) {
			Alert.warn("Exception :");
        }
    });
	}
function appendRecentData(data)
{
	var currentPublicNoticeId=$('.example').find('li.active').attr('id');
	$('.pagination').children().remove();
	if(currentPublicNoticeId==data.publicNoticeId)
	{
		$('#'+data.publicNoticeId).remove();
		
	}

	else
	{
		$('#'+currentPublicNoticeId).removeClass('active');
		var oldCount=$('.reportCount').html();
		$('.reportCount').html(Number(oldCount)+1);
	}
	var active=" class='active'";
	$('#example').prepend('<li '+active+' onclick="showdetails('+data.publicNoticeId+')" id="'+data.publicNoticeId+'"> <a href="" class="" data-toggle="tab">'
	           +' <div class="col-md-12">'
	           +'  <label class="col-xs-6">'+data.name+'</label>'
	           +'   <label class="col-xs-6 mytext" data-id="'+data.publicNoticeId+'">'+data.name+'</label>'
	           +'  </div>'
	           +'  <div class="col-md-12">'
	           +'    <label class="col-xs-6">'+data.code+'</label>'
	           +'    <label class="col-xs-6 mytext2">'+formatDate(data.tenderSaleOpeningDate)+'</label>'
	           +'  </div>'
	           +'  </a>'
	           +'  </li>');
	$(".Id").val(data.publicNoticeId);
	$('.saveBtn').hide();
	$('.CancelBtn').hide();
	$('.saveBtn').removeAttr("onclick");
	$('.CancelBtn').removeAttr("onclick");
	$('#errorMsg').hide();
	
	$('#formId').addClass('readonly');
    
	$('#refreshId').removeClass('readonly');
	 $('#addId').removeClass('readonly');
	 $('#editId').removeClass('readonly');
	 $('#deleteId').removeClass('readonly');
	 
	 	$(".detail_Name").html(data.name);
		$(".detail_Code").html(data.code);
		$(".detail_Desc").html(data.description);
		$(".detail_Active").html(formatDate(data.tenderSaleClosingDate));
		$(".tabs-left li a label").text(function(index, currentText) {
		    return currentText.substr(0, 18);
	});
		$('.example').paginathing();
		
		}

function processResponse(data)
{
	if(data.responseStatus==true)
	{
		appendRecentData(data.data);
    	Alert.info(data.responseMsg);
    	$('#errorMsg').hide();
	}
	else
		{
		 	var errorLog = "";
			$.each(data.data,function(key, value){
				errorLog = errorLog+value.errorMessage+"\n ;";
			});
			$('#errorMsg').show();
			$('#errorMsg').html("'"+errorLog+"'")
			 Alert.warn(data.responseMsg,"","error");
		}
	}



function deletePublicNotice()
{
	$('.pagination').children().remove();
	$('#refreshId').addClass('readonly');
	$('#addId').addClass('readonly');
	$('#editId').addClass('readonly');
	input_box = confirm("Do you really want to delete this Public Notices?");
	 if (input_box == true) {
			var id=$(".Id").val();
			$.ajax({
		        type : "POST",
		        contentType : "application/json",
		        url: "deletePublicNotices/"+id,
		        dataType:"json",
		        async:false,
		        success: function (data) {
		        	console.log(data);
		        	if(data.responseStatus==true)
		        		{
			        		$('#'+id).remove();
			        		var oldCount=$('.reportCount').html();
			        		$('.reportCount').html(Number(oldCount)-1);
			        		
			            	Alert.info(data.responseMsg);
			            	$('#refreshId').removeClass('readonly');
			            	$('#addId').removeClass('readonly');
			            	$('#editId').removeClass('readonly');
			            	$(".Name").val("");
			            	$(".Code").val("");
			            	$(".Description").val("");
			            	$(".Id").val("");
			            	
			            	$(".Title").val("");
							$(".Matter").val("");
							$(".TenderSaleOpeningDate").val("");
							$(".TenderSaleClosingDate").val("");
							
							$(".PublishingDate").val("");
			            	
			            	$('#errorMsg').hide();
		        		}
		        	else
		        		{
		        		Alert.warn(data.responseMsg,"","error");
		        		}
		        },
		        error: function (e) {
					Alert.warn(e.message);
		        }
			});
		        $('.example').paginathing();
		        
		    
	 }
	}