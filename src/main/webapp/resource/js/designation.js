$(document).ready(
		function() {
			renderList();
			$(".save").hide();
			$(".cancel").hide();
			/*$("#updateediteddesignation").hide();
			$(".addnewdesignation").click(function(){
				$("#designationFormId").removeClass("readonly");
				$("#savedesignation").show();
				$(".cancel").show();
				$(".Name").val("");
				$(".Code").val("");
				$(".Description").val("");
				$(".Id").val("");
				$("#updateediteddesignation").hide();
				
			});*/
			$(".save").on(
					"click",
					function() {

						event.preventDefault();
						submitIt("designationFormId", "saveDesgination",
								"saveDesginationResp");
						return false;
					});
			$("#deldesignation").on("click", function(event) {
                        event.preventDefault();
                        submitWithParam("deleteDesignation", "Id","deleteDesignationResp");

					});
			 
			$("#editdesignation").click(function() {
				event.preventDefault();
				$("#designationFormId").removeClass("readonly");
				$(".cancel").show();
				$(".save").show();
			});

			$('.cancel').click(function(event) {
				event.preventDefault();
				var activeDesId=$('.example').find('li.active').attr('id');
				if(activeDesId!=undefined)
					{
					showdetails(activeDesId);
					
					}
				else
					$('#designationFormId')[0].reset();
				
			});
			/*$("#updateediteddesignation").click(function(){
			   $("#designationFormId").addClass("readonly");
				$("#savedesignation").hide();
				$(".cancel").hide();
				$("#updateediteddesignation").hide();
			   submitIt("designationFormId","updateDesigantion", "getResponseUpdate");
				
			});*/
			/*$('#searchlitralid').on('keyup', function () {
			    var value = this.value;
			    $('#example li').hide().each(function () {
			        if ($(this).text().toUpperCase().search(value.toUpperCase()) > -1) {
			        	$(this).show();;
			        }
			    });
			});*/
		});

 function deleteDesignationResp(data){
	 debugger;
	 if(!$.isEmptyObject(data)){
		  var currentHsnDel=$('.example').find('li.active').attr('id');
		  
			if(data.hasError==false)
			{
				Alert.info(data.message);
				$('#'+currentHsnDel).remove();
				$('#designationFormId #Id').val("");
				$('#designationFormId #Name').val("");
				$('#designationFormId #Code').val("");
				$('#designationFormId #Description').val("");
		    	$('#errorMsg').hide();
				$(".detail_Name").html('');
				$(".detail_Code").html('');
				$(".detail_Desc").html('');
				$(".detail_Active").html('');
			}
			else
				{
				var errorLog = "";
				$.each(data.data,function(key, value){
					errorLog = errorLog+value.errorMessage+"\n ;";
				});
				$('#errorMsg').show();
				$('#errorMsg').html("'"+errorLog+"'")
				Alert.warn(data.message,"","error");
				}
			}
 }
function addDesignation(event) {
	debugger;
	event.preventDefault();
	$(".Id").val("");
	$("#designationFormId").removeClass('readonly');
	$('.isActive').prop('checked', true);
	
	$(".save").show();
	$(".cancel").show();
	$("#editdesignation").removeClass('readonly');
	$("#deldesignation").removeClass('readonly');
	$("#designationFormId").find('input,select,textarea').val("");

}

function saveDesginationResp(data) {
	debugger;
	if (!$.isEmptyObject(data)) {
		if (data.response.hasError == false) {
			Alert.info(data.response.message);
			appendRecentRecord(data);
			$(".save").hide();
			$(".cancel").hide();
			/*$(".editSave").hide();*/
			$("#designationFormId").addClass('readonly');
			$('#errorMsg').hide();
		} else {
			var errorLog = "";
			$.each(data.data, function(key, value) {
				errorLog = errorLog + value.errorMessage + "\n ;";
			});
			$('#errorMsg').show();
			$('#errorMsg').html("'" + errorLog + "'")
			Alert.warn(data.responseMsg, "", "error");
		}
	}
}

function appendRecentRecord(data) {
	debugger;

	$("#Id").val(data.designationId);
	if (data.isActive == "Y") {
		activeStatus = "Active";
	} else {
		activeStatus = "InActive";
	}
	var currentDesigId = $('.example').find('li.active').attr('id');

	if (currentDesigId == data.designationId) {
		$("#" + currentDesigId + '').remove();
	} else {
		$("#" + currentDesigId + '').removeClass();
	}
	 
	 var active= "class='active'";
	 $('#masterDetails').empty();
	 
		$('#masterDetails')
		.append(
				'<div class="row"><label class="col-xs-6" ><h4 class="detail_Name">'
						+ data.name
						+ '</h4></label>'
						+ '<label class="col-xs-6 mytext detail_Code">'
						+ data.code
						+ '</label></div> '
						+ '<div class="row"><label class="col-xs-6 mytext detail_Desc">'
						+ data.description
						+ '</label>'
						+ '<label class="col-xs-6 mytext detail_Active">'
						+ activeStatus + '</label></div>');

$('#example').prepend(
	'<li ' + active + ' onclick="showdetails('
			+ data.designationId
			+ ')" id="'+ data.designationId + '"> <a href="" class="" data-toggle="tab">'
			+ ' <div class="col-md-12">'
			+ '  <label class="col-xs-6">' + data.name
			+ '</label>'
			+ '   <label class="col-xs-6 mytext" data-Id="'
			+ data.designationId + '">' + data.description
			+ '</label>' + '  </div>' + '  <div class="col-md-12">'
			+ '    <label class="col-xs-6">' + data.code
			+ '</label>' + '    <label class="col-xs-6 mytext2">'
			+ activeStatus + '</label>' + '  </div>' + '  </a>'
			+ '  </li>');

$(".Id").val(data.designationId);
$('#errorMsg').hide();
$('#designationFormId').addClass('readonly');
$(".detail_Name").html(data.name);
$(".detail_Code").html(data.code);
$(".detail_Desc").html(data.description);
$(".detail_Active").html(activeStatus);
$('.example').paginathing();

}
function renderList() {
	debugger;
	$.ajax({
		type : "POST",
		contentType : "application/json",
		url : "getDesignationList",
		dataType : "json",
		async : false,
		success : function(data) {
			if ($.isEmptyObject(data)) {
				Alert.warn("No Designation present in List");
				$("#editdesignation").addClass('readonly');
    			$("#deldesignation").addClass('readonly');
			} else {
				appendList(data);
			}
		},
		error : function(e) {
			Alert.warn("Exception :");
		}
	});
}

function appendList(data) {
	debugger;
	var active = " class='active'";
	$('#example').empty();
	var activeStatus = "";
	for (var i = 0; i < data.length; i++) {
		if (data[i].isActive == "Y")
			activeStatus = "Active";
		else
			activeStatus = "InActive";
		if (i == 0) {

			$(".Name").val(data[i].name);
			$(".Code").val(data[i].code);
			$(".Description").val(data[i].description);
			$(".Id").val(data[i].designationId);
			if (activeStatus == "Active")
				$('.isActive').prop('checked', true);
			else
				$('.isActive').prop('checked', false);

			$('#masterDetails').empty();
			$('#masterDetails')
					.append(
							'<div class="row"><label class="col-xs-6" ><h4 class="detail_Name">'
									+ data[i].name
									+ '</h4></label>'
									+ '<label class="col-xs-6 mytext detail_Code">'
									+ data[i].code
									+ '</label></div> '
									+ '<div class="row"><label class="col-xs-6 mytext detail_Desc">'
									+ data[i].description
									+ '</label>'
									+ '<label class="col-xs-6 mytext detail_Active">'
									+ activeStatus + '</label></div>');

		}

		$('#example').append(
				'<li ' + active + ' onclick="showdetails('
						+ data[i].designationId
						+ ')" id="'+ data[i].designationId + '"> <a href="" class="" data-toggle="tab">'
						+ ' <div class="col-md-12">'
						+ '  <label class="col-xs-6">' + data[i].name
						+ '</label>'
						+ '   <label class="col-xs-6 mytext" data-Id="'
						+ data[i].designationId + '">' + data[i].description
						+ '</label>' + '  </div>' + '  <div class="col-md-12">'
						+ '    <label class="col-xs-6">' + data[i].code
						+ '</label>' + '    <label class="col-xs-6 mytext2">'
						+ activeStatus + '</label>' + '  </div>' + '  </a>'
						+ '  </li>');
		active = "";
	}
	$('.example').paginathing();

	$(".tabs-left li a label").text(function(index, currentText) {
		return currentText.substr(0, 20);
	});
	$('.reportCount').html(data.length);
}
function showdetails(id) {
	debugger; 
	$(".save").hide();
	$(".cancel").hide();
	$('#designationFormId').addClass("readonly");
	$.ajax({
		type : "POST",
		contentType : "application/json",
		url : "getDesignation/" + id,
		dataType : "json",
		async : false,
		success : function(data) {
			if ($.isEmptyObject(data)) {
				Alert.warn("Designation details is empty");
			} else {
				var activeStatus = '';
				$(".Name").val(data.name);
				$(".Code").val(data.code);
				$(".Description").val(data.description);
				$(".Id").val(data.designationId);
				if (data.isActive == "Y") {
					$('.isActive').prop('checked', true);
					activeStatus = "Active";
				} else {
					$('.isActive').prop('checked', false);
					activeStatus = "InActive";
				}

				$(".detail_Name").html(data.name);
				$(".detail_Code").html(data.code);
				$(".detail_Desc").html(data.description);
				$(".detail_Active").html(activeStatus);

			}

		},
		error : function(e) {
			Alert.warn("Exception :");
		}
	});
}