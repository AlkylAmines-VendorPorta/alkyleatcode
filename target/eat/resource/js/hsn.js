$(document).ready(function() {
	renderList();
	$(".save").hide();
	$(".cancel").hide();
	/*$("#editselectedhsn").hide();*/

	/*$(".addnewhsn").click(function(){
		$(".save").show();
		$(".cancle").show();
		$(".Name").val("");
		$(".Code").val("");
		$(".Description").val("");
		$("#editselectedhsn").hide();
		$("#hsnFormId").removeClass("readonly");
		
	});
	 *//*$("#savenewhsn").click(function(){
		$("#editselectedhsn").hide();
		submitIt("hsnmaster","addNewHSN", "getResponseDto");
		$("#hsnmaster").addClass("readonly");
		$(".save").hide();
		$("#cancel").hide();
		submitIt("taxCatFormId","addNewTaxCat", "getResponseDto");
	});*/
	$(".save").on("click", function(event) {

		event.preventDefault();
		submitIt("hsnFormId", "addNewHSN", "saveHSNResp");
		return false;
	});
	$('.cancel').click(function(event) {
		event.preventDefault();
		var activeHSNId=$('.example').find('li.active').attr('id');
		if(activeHSNId!=undefined)
			{
			showdetails(activeHSNId);
			
			}
		else
			$('#hsnFormId')[0].reset();
		
	});

	$("#delhsn").on("click", function(event) {
		event.preventDefault();
		submitWithParam("deleteHSN","Id" ,"deleteHSNResp");
	});

	$("#edithsn").click(function() {
		event.preventDefault();
		$("#cancel").show();
		$("#savenewhsn").show();
		$("#hsnFormId").removeClass("readonly");
	});

});
function addHSN(event) {
	event.preventDefault();
	$(".Id").val("");
	$("#hsnFormId").removeClass('readonly');
	$('.isActive').prop('checked', true); 
	$(".save").show();
	$(".cancel").show();
	$("#edithsn").removeClass('readonly');
	$("#delhsn").removeClass('readonly');
	$("#hsnFormId").find('input,select,textarea').val("");

}

function renderList() {
	$.ajax({
		type : "POST",
		contentType : "application/json",
		url : "getHSNList",
		dataType : "json",
		async : false,
		success : function(data) {
			if ($.isEmptyObject(data)) {
				Alert.warn("No HSN present in List");
				 $("#edithsn").addClass('readonly');
       		  $("#delhsn").addClass('readonly');
			} else {
				appendList(data);
			}

		},
		error : function(e) {
			Alert.info("Exception :");
		}
	});
}

function saveHSNResp(data) {
	debugger;

	if (!$.isEmptyObject(data)) {
		if (data.response.hasError == false) {

			Alert.info(data.response.message);
			appendRecentRecord(data);
			$(".save").hide();
			$(".cancel").hide();
			/*$(".editSave").hide();*/
			$("#hsnFormId").addClass('readonly');
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
	var currentHSNId = $('.example').find('li.active').attr('id');

	$("#Id").val(data.hsnId);
	
	if(data.isActive == "Y")
		activeStatus="Active";
	else
		activeStatus="InActive";
	

	if (currentHSNId == data.hsnId) {
		$('#' + currentHSNId + '').remove();
	} else {
		$('#' + currentHSNId + '').removeClass();
	}
	var active = " class='active'";
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
							+ data.description + '</label>'
							+ '<label class="col-xs-6 mytext detail_Active">'
							+ activeStatus + '</label></div>');

	$('#example').prepend(
			'<li ' + active + ' onclick="showdetails(' + data.hsnId + ')" id="'
					+ data.hsnId + '"> <a href="" class="" data-toggle="tab">'
					+ ' <div class="col-md-12">' + '  <label class="col-xs-6">'
					+ data.name + '</label>'
					+ '   <label class="col-xs-6 mytext" data-Id="'
					+ data.hsnId + '">' + data.name + '</label>' + '  </div>'
					+ '  <div class="col-md-12">'
					+ '    <label class="col-xs-6">' + data.code + '</label>'
					+ '    <label class="col-xs-6 mytext2">' + activeStatus
					+ '</label>' + '  </div>' + '  </a>' + '  </li>');

	$(".Id").val(data.hsnId);
	/*$('.saveBtn').removeAttr("onclick");
	$('.CancelBtn').removeAttr("onclick");*/
	$('#errorMsg').hide();
	$('#hsnFormId').addClass('readonly');
	$(".detail_Name").html(data.name);
	$(".detail_Code").html(data.code);
	$(".detail_Desc").html(data.description);
	$(".detail_Active").html(activeStatus);
	$('.example').paginathing();

}

function deleteHSNResp(data) {
	debugger;
	
	if(!$.isEmptyObject(data)){
		  var currentHsnDel=$('.example').find('li.active').attr('id');
		  
			if(data.hasError==false)
			{
				Alert.info(data.message);
				$('#'+currentHsnDel).remove();
				$('#hsnFormId #Id').val("");
				$('#hsnFormId #Name').val("");
				$('#hsnFormId #Code').val("");
				$('#hsnFormId #Description').val("");
				
				/*$('#masterDetails').empty();*/
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
function appendList(data) {
	debugger;
	var active = " class='active'";
	$('#example').empty();
	var activeStatus = "";
	for (var i = 0; i < data.length; i++) {
		if(data[i].isActive=="Y")
			activeStatus="Active";
		else
			activeStatus="InActive";
		if (i == 0) {

			$(".Name").val(data[i].name);
			$(".Code").val(data[i].code);
			$(".Description").val(data[i].description);
			$(".Id").val(data[i].hsnId);

			if(activeStatus=="Active")
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
				'<li ' + active + ' onclick="showdetails(' + data[i].hsnId
						+ ')" id="' + data[i].hsnId
						+ '"> <a href="" class="" data-toggle="tab">'
						+ ' <div class="col-md-12">'
						+ '  <label class="col-xs-6">' + data[i].name
						+ '</label>'
						+ '   <label class="col-xs-6 mytext" data-Id="'
						+ data[i].hsnId + '">' + data[i].name + '</label>'
						+ '  </div>' + '  <div class="col-md-12">'
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
	$('#hsnFormId').addClass("readonly");
	$.ajax({
		type : "POST",
		contentType : "application/json",
		url : "getHSneById/" + id,
		dataType : "json",
		async : false,
		success : function(data) {
			if ($.isEmptyObject(data)) {
				Alert.info("Role details is empty");
			} else 
			
			{
				var activeStatus = '';
				$(".Name").val(data.name);
				$(".Code").val(data.code);
				$(".Description").val(data.description);
				$(".Id").val(data.hsnId);
				
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

				/*if(data.isActive=="Y")
					$('.isActive').prop('checked', true); 
				else
					$('.isActive').prop('checked', false);*/

				$(".detail_Name").html(data.name);
				$(".detail_Code").html(data.code);
				$(".detail_Desc").html(data.description);
				$(".detail_Active").html(activeStatus);

			}

		},
		error : function(e) {
			Alert.info("Exception :");
		}
	});
}
