var flag="Y";


$(document).ready(function() {
	renderList();
	$(".saveRating").hide();
	$(".cancelRating").hide();
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
	$(".saveRating").on("click", function(event) {
        debugger;
		event.preventDefault();
		sum();
		if(flag=="Y"){
		submitIt("ratingWeightageFormId", "addRatingWeightage", "saveRatingResp");
	}
		return false;
	});
	$('#cancelRating').click(function(event) {
		debugger;
		event.preventDefault();
		var activeRatingId=$('.example').find('li.active').attr('id');
		if(activeRatingId!=undefined)
			{
			showdetails(activeRatingId);
			
			}
		else
			$('#ratingWeightageFormId')[0].reset();
		
	});

	/*$("#delRating").on("click", function(event) {
		event.preventDefault();
		submitWithParam("deleteHSN","Id" ,"deleteHSNResp");
	});*/

	$("#editRating").click(function() {
		event.preventDefault();
		$("#cancelRating").show();
		$("#saveRating").show();
		$("#ratingWeightageFormId").removeClass("readonly");
	});

});
function addRating(event) {
	debugger;
		event.preventDefault();
		$(".Id").val("");
		$("#ratingWeightageFormId").removeClass('readonly');
		$('.isActive').prop('checked', true); 
		$(".saveRating").show();
		$(".cancelRating").show();
		$("#ratingWeightageFormId").find('input,select,textarea').val("");
		$(".addRatingWeight").prop('disabled', true); 

}

function renderList() {
	debugger;
	$.ajax({
		type : "POST",
		contentType : "application/json",
		url : "getRatingWeightageList",
		dataType : "json",
		async : false,
		success : function(data) {
			if ($.isEmptyObject(data)) {
				Alert.warn("No Rating present in List");
				$(".addRatingWeight").prop('disabled', false); 
			} else {
				appendList(data);
				$(".addRatingWeight").prop('disabled', true); 
			}

		},
		error : function(e) {
			Alert.warn("Exception :");
		}
	});
}

function saveRatingResp(data) {
	debugger;

	if (!$.isEmptyObject(data)) {
		if (data.response.hasError == false) {
			
			Alert.info(data.response.message);
			appendRecentRecord(data);
			$(".saveRating").hide();
			$(".cancelRating").hide();
			$("#ratingWeightageFormId").addClass('readonly');
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

function sum() {
    var qualityWeightageValue = document.getElementById('qualityWeightage').value;
    var priceWeightageValue = document.getElementById('priceWeightage').value;
    var deliveryWeightageValue = document.getElementById('deliveryWeightage').value;
    var serviceWeightageValue = document.getElementById('serviceWeightage').value;
    var result = parseInt(qualityWeightageValue) + parseInt(priceWeightageValue)+ parseInt(deliveryWeightageValue)+ parseInt(serviceWeightageValue);
    if (result!=100) {
    	Alert.warn("Overall Rating Should be equal to 100");
    	flag="N";
    }
    else{
    	flag="Y";
    }
}
function appendRecentRecord(data) {
	debugger;
	var currentRatingId = $('.example').find('li.active').attr('id');

	$("#Id").val(data.ratingWeightageId);
	
	if(data.isActive == "Y")
		activeStatus="Active";
	else
		activeStatus="InActive";
	

	if (currentRatingId == data.ratingWeightageId) {
		$('#' + currentRatingId + '').remove();
	} else {
		$('#' + currentRatingId + '').removeClass();
	}
	var active = " class='active'";
	$('#masterDetails').empty();
	$('#masterDetails')
			.append(
					'<div class="row"><label class="col-xs-6" ><h4 class="detail_Name">'
							+ data.qualityWeightage
							+ '</h4></label>'
							+ '<label class="col-xs-6 mytext detail_Code">'
							+ data.priceWeightage
							+ '</label></div> '
							+ '<div class="row"><label class="col-xs-6 mytext detail_Desc">'
							+ data.deliveryWeightage + '</label>'
							+ '<label class="col-xs-6 mytext detail_Active">'
							+ activeStatus + '</label></div>');

	$('#example').prepend(
			'<li ' + active + ' onclick="showdetails(' + data.ratingWeightageId + ')" id="'
					+ data.ratingWeightageId + '"> <a href="" class="" data-toggle="tab">'
					+ ' <div class="col-md-12">' + '  <label class="col-xs-6">'
					+ data.qualityWeightage + '</label>'
					+ '   <label class="col-xs-6 mytext" data-Id="'
					+ data.ratingWeightageId + '">' + data.qualityWeightage + '</label>' + '  </div>'
					+ '  <div class="col-md-12">'
					+ '    <label class="col-xs-6">' + data.priceWeightage + '</label>'
					+ '    <label class="col-xs-6 mytext2">' + activeStatus
					+ '</label>' + '  </div>' + '  </a>' + '  </li>');

	$(".Id").val(data.ratingWeightageId);
	/*$('.saveBtn').removeAttr("onclick");
	$('.CancelBtn').removeAttr("onclick");*/
	$('#errorMsg').hide();
	$('#ratingWeightageFormId').addClass('readonly');
	$(".detail_Name").html(data.qualityWeightage);
	$(".detail_Code").html(data.priceWeightage);
	$(".detail_Desc").html(data.deliveryWeightage);
	$(".detail_Active").html(activeStatus);
	$('.example').paginathing();

}

/*function deleteHSNResp(data) {
	debugger;
	
	if(!$.isEmptyObject(data)){
		  var currentHsnDel=$('.example').find('li.active').attr('id');
		  
			if(data.hasError==false)
			{
				Alert.info(data.message);
				$('#'+currentHsnDel).remove();
				$('#ratingWeightageFormId #Id').val("");
				$('#ratingWeightageFormId #qualityWeightage').val("");
				$('#ratingWeightageFormId #priceWeightage').val("");
				$('#ratingWeightageFormId #deliveryWeightage').val("");
				$('#ratingWeightageFormId #serviceWeightage').val("");
				
				$('#masterDetails').empty();
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
				Alert.warn(data.responseMsg,"","error");
				}
			}
	
}*/
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

			$(".qualityWeightage").val(data[i].qualityWeightage);
			$(".priceWeightage").val(data[i].priceWeightage);
			$(".deliveryWeightage").val(data[i].deliveryWeightage);
			$(".serviceWeightage").val(data[i].serviceWeightage);
			$(".Id").val(data[i].ratingWeightageId);

			if(activeStatus=="Active")
				$('.isActive').prop('checked', true);
			else
				$('.isActive').prop('checked', false);
			
			

			$('#masterDetails').empty();
			$('#masterDetails')
					.append(
							'<div class="row"><label class="col-xs-6" ><h4 class="detail_Name">'
									+ data[i].qualityWeightage
									+ '</h4></label>'
									+ '<label class="col-xs-6 mytext detail_Code">'
									+ data[i].priceWeightage
									+ '</label></div> '
									+ '<div class="row"><label class="col-xs-6 mytext detail_Desc">'
									+ data[i].deliveryWeightage
									+ '</label>'
									+ '<label class="col-xs-6 mytext detail_Active">'
									+ activeStatus + '</label></div>');

		}

		$('#example').append(
				'<li ' + active + ' onclick="showdetails(' + data[i].ratingWeightageId
						+ ')" id="' + data[i].ratingWeightageId
						+ '"> <a href="" class="" data-toggle="tab">'
						+ ' <div class="col-md-12">'
						+ '  <label class="col-xs-6">' + data[i].qualityWeightage
						+ '</label>'
						+ '   <label class="col-xs-6 mytext" data-Id="'
						+ data[i].ratingWeightageId + '">' + data[i].qualityWeightage + '</label>'
						+ '  </div>' + '  <div class="col-md-12">'
						+ '    <label class="col-xs-6">' + data[i].priceWeightage
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
	$(".saveRating").hide();
	$(".cancelRating").hide();
	$('#ratingWeightageFormId').addClass("readonly");
	$.ajax({
		type : "POST",
		contentType : "application/json",
		url : "getRatingWeightageById/" + id,
		dataType : "json",
		async : false,
		success : function(data) {
			if ($.isEmptyObject(data)) {
				Alert.warn("Role details is empty");
			} else 
			
			{
				var activeStatus = '';
				$(".qualityWeightage").val(data.qualityWeightage);
				$(".priceWeightage").val(data.priceWeightage);
				$(".deliveryWeightage").val(data.deliveryWeightage);
				$(".serviceWeightage").val(data.serviceWeightage);
				$(".Id").val(data.ratingWeightageId);
				
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

				$(".detail_Name").html(data.qualityWeightage);
				$(".detail_Code").html(data.priceWeightage);
				$(".detail_Desc").html(data.deliveryWeightage);
				$(".detail_Active").html(activeStatus);

			}

		},
		error : function(e) {
			Alert.warn("Exception :");
		}
	});
}
