var pageSize = 7;
var maxVisiblePageNumbers = 3;

$(document).ready(function() {
	renderList();

	var lengthMenu;
	if ($(window).width() < 480) {
		$('.mobileNav').show();
		$.fn.DataTable.ext.pager.numbers_length = 4;
		lengthMenu = [ 1, 5, 7, 10, ], [ 1, 5, 7, 10, ]
	} else {
		lengthMenu = [ 5, 10, ], [ 5, 10, ]
	}

	$('.indirectFormSubmit').on("click", function() {
		/*
		 * gtptypeName = $('.gtpParameterType').find('option:selected').text();
		 * event.preventDefault();
		 * 
		 * submitIt("formId", $('#formId').attr('action'), "processResponse");
		 */
		event.preventDefault();
		submitIt("formId", "saveGtpParameter", "processResponse");
		return false;
		/* $('.example').paginathing(); */
	});
	/*
	 * $('#searchlitralid').on('keyup', function () { var value = this.value;
	 * $('#example li').hide().each(function () { if
	 * ($(this).text().toUpperCase().search(value.toUpperCase()) > -1) {
	 * $(this).show();; } }); });
	 */

	$("#editId").on("click", function(event) {
		debugger;
		event.preventDefault();
		$("#formId").removeClass('readonly');
		$(".saveBtn").show();
		$(".CancelBtn").show();
	});
	
	$("#deleteId").on("click" , function(evt){
		debugger;
		event.preventDefault();
		submitWithParam("deleteGtpParameter", "Id", "deleteMaterialResp");
	})

	$('.addMatPopup').click(function(event) {
		event.preventDefault();
		$('.commenSearchModal').modal('show');
		$(".itemTable").DataTable().destroy();
		$(".itemTable tbody").empty();
		populateMaterialGroupList(fetchList("getMaterialGroupList", null));/*
																			 * populateSubGroupList(fetchList("getMaterialSubGroupList",null));
																			 */
		$('.commenSearchTable').DataTable({
			"lengthMenu" : lengthMenu
		});
	});

	$('#pagination-here').paginate({
		pageSize : 7,
		dataSource : 'fetchData',
		responseTo : 'appendList',
		maxVisiblePageNumbers : 3,
		searchBoxID : 'searchLiteralId'
	});
});

var name = "";
var code = "";
var desc = "";
var isActive = "";
var id = "";
var gtpparametertypeid = "";
var matId = "";
var matName = "";
var gtptypeName = '';
function getPrevValues() {
	name = $(".Name").val();
	code = $(".Code").val();
	desc = $(".Description").val();
	matId = $('.matName').val();
	matName = $('.matName').find('option:selected').text();
	if ($(".isActive").prop("checked") == true)
		isActive = "Y";
	else
		isActive = "N";
	id = $(".Id").val();
	gtpparametertypeid = $(".gtpParameterType").val();
}

function setPrevValues() {
	$(".Name").val(name);
	$(".Code").val(code);
	$(".Description").val(desc);
	$('.matName').val(matId);
	if (isActive == "Y")
		$('.isActive').prop('checked', true);
	else
		$('.isActive').prop('checked', false);
	$(".Id").val(id);
	$(".gtpParameterType").val(gtpparametertypeid);

}

function deleteMaterialResp(data){
	debugger;
	if(!$.isEmptyObject(data)){
		  var currentDel=$('#example').find('li.active').attr('id');
			if(data.hasError==false)

			{
				$('#'+currentDel).remove();
				$('#formId #Id').val("");
				$('#formId #name').val("");
				$('#formId #code').val("");
				$('#formId #description').val("");
				$('#formId #itemCodeId').val("");
				$('#formId #gtpId').val("");
				$('#formId #matName').val("");
				$('#formId #hsnCodeId').val("");
								
				$('#masterDetails').empty();
				Alert.info(data.message);
		    	$('#errorMsg').hide();
		    	$(".Name").html('');
				$(".Code").html('');
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
function addNewGtpParameter() {
	getPrevValues();
	$(".Name").val("");
	$(".Code").val("");
	$(".Description").val("");
	$(".Id").val("");
	$(".itemTrade").val('');
	$(".hsnCodeId").val('');
	$('.matName').empty();
	$(".gtpParameterType").val(0);
	$('.tahdrDetailTypeCode').val('I');
	$('#formId').removeClass('readonly');
	$('#errorMsg').hide();
	$('.isActive').attr('checked', 'checked');
	$('.isActive').addClass('readonly');

	$('.saveBtn').show();
	$('#formId').attr("action", "saveGtpParameter");
	$('.CancelBtn').show();
	$('.CancelBtn').attr("onclick", "cancel()");

	$('#refreshId').addClass('readonly');
	$('#editId').addClass('readonly');
	$('#deleteId').addClass('readonly');
}
/*
 * function editPrevGtpParameter() {
 * 
 * getPrevValues(); $('#formId').removeClass('readonly'); $('#errorMsg').hide();
 * $('.saveBtn').show(); $('.CancelBtn').show(); $('#formId').attr("action",
 * "editGtpParameter"); $('.CancelBtn').attr("onclick", "cancel()");
 * $('.isActive').removeAttr('readonly');
 * 
 * $('#refreshId').addClass('readonly'); $('#addId').addClass('readonly');
 * $('#deleteId').addClass('readonly'); }
 */
function cancel() {
	debugger;
	$(".Name").val(name);
	$(".Code").val(code);
	$(".Description").val(desc);
	$(".Id").val(id);
	$(".gtpParameterType").val(gtpparametertypeid);
	$('.matName').val(matId);
	if (isActive == "Y")
		$('.isActive').prop('checked', true);
	else
		$('.isActive').prop('checked', false);
	$('#formId').addClass('readonly');
	$('#errorMsg').hide();
	$('.saveBtn').hide();
	$('.CancelBtn').hide();
	$('.saveBtn').removeAttr("onclick");
	$('.CancelBtn').removeAttr("onclick");

	name = "";
	code = "";
	desc = "";
	isActive = "";
	id = "";
	gtpparametertypeid = "";
	matId = "";
	matName = "";

	$('#refreshId').removeClass('readonly');
	$('#addId').removeClass('readonly');
	$('#editId').removeClass('readonly');
	$('#deleteId').removeClass('readonly');
}
function renderList() {
	$('.tahdrDetailTypeCode').val('I');
	$('#AdditemButton').removeData('callback');
	$('#AdditemButton').attr('data-callback', 'loadMaterialName');
	$('#searchItemList')
			.attr(
					'onclick',
					"return submitIt('srchItemForm','getItemList','populateItemListWithSingleSelect');");
	var response = fetchData(1, pageSize, 'none', 'none');
	if (response != undefined) {
		appendList(response.data);
		setPagination(pageSize, 1, maxVisiblePageNumbers);
	}

}

function appendList(data) {
	debugger;

	var active = " class='active'";
	// $('.pagination').children().remove();
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
			$(".Id").val(data[i].gtpParameterId);
			if (!$.isEmptyObject(data[i].gtpParameterType))
				$(".gtpParameterType").val(
						data[i].gtpParameterType.gtpParameterTypeId);
			if (!$.isEmptyObject(data[i].material)) {
				$(".matName").empty();
				$(".matName").append(
						'<option value="' + data[i].material.materialId + '">'
								+ data[i].material.name + '</option>');

				$('#formId #itemCodeId').val(data[i].material.itemTrade);
				var hsn = data[i].material.hsnCode == null ? ''
						: data[i].material.hsnCode.code == null ? ''
								: data[i].material.hsnCode.code
				$('#formId #hsnCodeId').val(hsn);
			}
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
									+ data[i].gtpParameterType.name
									+ '</label></div>');

		}
		var gtpTypeName = data[i].gtpParameterType == null ? ''
				: data[i].gtpParameterType.name = null ? ''
						: data[i].gtpParameterType.name;
		$('#example').append(
				'<li ' + active + ' onclick="showdetails('
						+ data[i].gtpParameterId + ')" id="'
						+ data[i].gtpParameterId
						+ '"> <a href="" class="" data-toggle="tab">'
						+ ' <div class="col-md-12">'
						+ '  <label class="col-xs-6">' + data[i].name
						+ '</label>'
						+ '   <label class="col-xs-6 mytext" data-id="'
						+ data[i].gtpParameterId + '">' + data[i].description
						+ '</label>' + '  </div>' + '  <div class="col-md-12">'
						+ '    <label class="col-xs-6">' + data[i].code
						+ '</label>' + '    <label class="col-xs-6 mytext2">'
						+ gtpTypeName + '</label>' + '  </div>' + '  </a>'
						+ '  </li>');
		active = "";
	}
	/* $('.example').paginathing(); */

	$(".tabs-left li a label").text(function(index, currentText) {
		return currentText.substr(0, 20);
	});
	$('.reportCount').html(data.length);
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
function showdetails(id) {
	debugger;
	$.ajax({
		type : "POST",
		contentType : "application/json",
		url : "getGtpParameterById/" + id,
		dataType : "json",
		async : false,
		success : function(data) {
			if ($.isEmptyObject(data)) {
				Alert.info("Gtp Parameter details is empty");
			} else {

				$(".Name").val(data.name);
				$(".Code").val(data.code);
				$(".Description").val(data.description);

				$(".Id").val(data.gtpParameterId);
				if (!$.isEmptyObject(data.gtpParameterType))
					$(".gtpParameterType").val(
							data.gtpParameterType.gtpParameterTypeId);
				$(".matName").empty();
				if (!$.isEmptyObject(data.material)) {
					$(".matName").append(
							'<option value="' + data.material.materialId + '">'
									+ data.material.name + '</option>');
					$('#formId #itemCodeId').val(data.material.itemTrade);
					var hsn = data.material.hsnCode == null ? ''
							: data.material.hsnCode.code == null ? ''
									: data.material.hsnCode.code
					$('#formId #hsnCodeId').val(hsn);
				} else {
					$('#formId #itemCodeId').val('');
					$('#formId #hsnCodeId').val('');
				}
				if (data.isActive == "Y")
					$('.isActive').prop('checked', true);
				else
					$('.isActive').prop('checked', false);

				$(".detail_Name").html(data.name);
				$(".detail_Code").html(data.code);
				$(".detail_Desc").html(data.description);
				$(".detail_Active").html(data.gtpParameterType.name);

				$('.saveBtn').hide();
				$('.CancelBtn').hide();
				$('.saveBtn').removeAttr("onclick");
				$('.CancelBtn').removeAttr("onclick");
				$('#formId').addClass('readonly');
				$('#errorMsg').hide();

				$('#refreshId').removeClass('readonly');
				$('#addId').removeClass('readonly');
				$('#editId').removeClass('readonly');
				$('#deleteId').removeClass('readonly');
			}

		},
		error : function(e) {
			Alert.warn("Exception :");
		}
	});
}

function appendRecentData(data) {
	debugger;
	// $('.pagination').children().remove();
	var currentGtpParameterId = $('.example').find('li.active').attr('id');
	var gtpTypeName;
	
	if (!$.isEmptyObject(data.gtpParameterType))
	$(".gtpParameterType").val(
			data.gtpParameterType.gtpParameterTypeId);
	
	if(data.gtpParameterType.name==null){
		gtpTypeName=$("#gtpId").find("option:selected").text();
	}else{
		gtpTypeName = data.gtpParameterType == null?'': data.gtpParameterType.name = null?'':data.gtpParameterType.name;
	}
	 
	if (currentGtpParameterId == data.gtpParameterId) {
		$('#' + data.gtpParameterId).remove();
	} else {
		$('#' + currentGtpParameterId).removeClass('active');
		var oldCount = $('.reportCount').html();
		$('.reportCount').html(Number(oldCount) + 1);
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
							+ data.description
							+ '</label>'
							+ '<label class="col-xs-6 mytext detail_Active">'
							+ data.gtpParameterType.name
							+ '</label></div>');
	$('#example').prepend(
			'<li ' + active + ' onclick="showdetails(' + data.gtpParameterId+ ')" id="' + data.gtpParameterId+ '"> <a href="" class="" data-toggle="tab">'
					+ ' <div class="col-md-12">' + '  <label class="col-xs-6">'
					+ data.name + '</label>'
					+ '   <label class="col-xs-6 mytext" data-id="'
					+ data.gtpParameterId + '">' + data.description
					+ '</label>' + '  </div>' + '  <div class="col-md-12">'
					+ '<label class="col-xs-6">' + data.code + '</label>'
					+ '<label class="col-xs-6 mytext2">' + gtpTypeName
					+ '</label>' + '  </div>' + '  </a>' + '  </li>');
	$(".Id").val(data.gtpParameterId);
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
	$(".detail_Active").html(gtpTypeName);
	/* $('.example').paginathing(); */

}

function processResponse(data) {
	if (data.response.hasError == false) {
		appendRecentData(data);
		Alert.info(data.response.message);
		$('.saveBtn').hide();
		$('.CancelBtn').hide();
		$("#formId").addClass('readonly');
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

/*function deleteGtpParameter() {
	$('#refreshId').addClass('readonly');
	$('#addId').addClass('readonly');
	$('#editId').addClass('readonly');
	$('.pagination').children().remove();
	input_box = confirm("Do you really want to delete this Gtp Parameter?");
	if (input_box == true) {
		var id = $(".Id").val();
		$.ajax({
			type : "POST",
			contentType : "application/json",
			url : "deleteGtpParameter/" + id,
			dataType : "json",
			async : false,
			success : function(data) {

				if (data.responseStatus == true) {
					$('#' + id).remove();
					var oldCount = $('.reportCount').html();
					$('.reportCount').html(Number(oldCount) - 1);
					Alert.info(data.responseMsg);
					$('#refreshId').removeClass('readonly');
					$('#addId').removeClass('readonly');
					$('#editId').removeClass('readonly');
					$(".Name").val("");
					$(".Code").val("");
					$(".Description").val("");
					$(".Id").val("");
					$(".gtpParameterType").val(0);
					$(".matName").val(0);
					$('#errorMsg').hide();
				} else {
					Alert.warn(data.responseMsg, "", "error");
				}

			},
			error : function(e) {
				Alert.warn("Exception :");
			}
		});
		 $('.example').paginathing(); 

	}
}*/

function loadMaterialName(values) {
	debugger;
	$(".matName").html("");
	var options = '';
	$.each(values.map, function(index, val) {
		options += '<option value="' + index + '">' + val + '</option>'

	});
	$('#formId #itemCodeId').val(values.itemTrade);
	$('#formId #hsnCodeId').val(values.hsn);
	$(".matName").append(options);
}

function fetchData(pageNumber, pageSize, searchMode, searchValue) {
	var response;
	$
			.ajax({
				type : "POST",
				contentType : "application/json",
				url : 'getGtpParameterList/' + pageNumber + '/' + pageSize
						+ '/' + searchMode + '/' + searchValue,
				dataType : "json",
				async : false,
				success : function(data) {
					if ($.isEmptyObject(data)) {
						Alert.info("No GtpParameter present in List");
						$('#masterDetails')
								.append(
										'<div class="row"><label class="col-xs-6" ><h4 class="detail_Name"></h4></label>'
												+ '<label class="col-xs-6 mytext detail_Code"></label></div> '
												+ '<div class="row"><label class="col-xs-6 mytext detail_Desc"></label>'
												+ '<label class="col-xs-6 mytext detail_Active"></label></div>');

						$('.CancelBtn').hide();
					} else {
						response = data;
					}

				},
				error : function(e) {
					Alert.warn("Exception :");
				}
			});
	return response;
}