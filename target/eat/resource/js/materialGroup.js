$(document).ready(
		function() {
			$('.save').hide();
			$(".cancel").hide();

			renderList();
			/*$('#searchlitralid').on('keyup', function () {
			    var value = this.value;
			    $('#example li').hide().each(function () {
			        if ($(this).text().toUpperCase().search(value.toUpperCase()) > -1) {
			        	$(this).show();;
			        }
			    });
			});*/
			/*$(".addMaterialGroup").click(function() {
				debugger;
				event.preventDefault();
				$(".Id").val("");
				$('.save').show();
				$(".cancel").show();
				$("#materialGroupFormId").removeClass("readonly");
				$('.isActive').prop('checked', true); 
				$("#materialGroupFormId").find('input,select,textarea').val("");
			});
			*/
			
				$(".save").on("click", function(event) {
						event.preventDefault();
						submitIt("materialGroupFormId", "saveMaterialGroup","saveMaterialGroupResp");
						return false;

					});
			
			$(".cancel").click(function(event) {
				event.preventDefault();
				var activeMaterialGroupId=$('.example').find('li.active').attr('id');
				if(activeMaterialGroupId!=undefined)
					{
					showdetails(activeMaterialGroupId);
					
					}
				else
					$('#materialGroupFormId')[0].reset();
				
			});
			/*$("#delMaterialGroup").click(
					function() {
						alert();
						$(".Id").attr('name', 'materialGroupId');
						submitIt("materialGroupFormId","deleteMaterialGroup",	"getResponseDelete");

					});*/
			$("#delMaterialGroup").on("click", function(event) {
				event.preventDefault();
				submitWithParam("deleteMaterialGroup","Id" ,"deleteMaterialGroupResp");
			});
			
			/*$("#delhsn").on("click", function(event) {
				event.preventDefault();
				submitWithParam("deleteHSN","Id" ,"deleteHSNResp");
			});*/
			
			$("#editMaterialGroup").click(function() {
				event.preventDefault();
				$(".cancel").show();
				$(".save").show();
				$("#materialGroupFormId").removeClass('readonly');
			});
          
			/*$("#editselecetdmaterialgrop").click(function() {
				alert();
				$(".Id").attr('name', 'materialGroupId');
				  $("#saveeditedmaterialgroup").show();
				$('.save').hide();
				$("#materialGroupFormId").removeClass("readonly");
				 $("#saveeditedmaterialgroup").show();
				$(".cancel").show();

			});*/
			/* $("#saveeditedmaterialgroup").click(function(){
				   submitIt("materialGroupFormId","updatematerialgroupmaster", "getResponseUpdate");
				   $("#materialGroupFormId").addClass("readonly");
				   $("#saveeditedmaterialgroup").hide();
					$(".cancel").hide();
					
					
				});*/
		});

function addMaterialGroup(event){
	debugger;
	event.preventDefault();
	$(".Id").val("");
	$('.save').show();
	$(".cancel").show();
	$("#materialGroupFormId").removeClass('readonly');
	$('.isActive').prop('checked', true); 
	$("#delMaterialGroup").removeClass('readonly');
		$("#editMaterialGroup").removeClass('readonly');
	$("#materialGroupFormId").find('input,select,textarea').val("");
}

function deleteMaterialGroupResp(data){
	debugger;
	if(!$.isEmptyObject(data)){
		var currentGroupDel=$('.example').find('li.active').attr('id');
		if(data.hasError==false){
			Alert.info(data.message);
			$('#'+currentGroupDel).remove();
			$('#materialGroupFormId #Id').val("");
			$('#materialGroupFormId #Name').val("");
			$('#materialGroupFormId #Code').val("");
			$('#materialGroupFormId #Description').val("");
			
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

function renderList() {
	debugger;
	$.ajax({
		type : "POST",
		contentType : "application/json",
		url : "getMaterialGroupList",
		dataType : "json",
		async : false,
		success : function(data) {
			if ($.isEmptyObject(data)) {
				Alert.warn("No MaterialGroup present in List");
				  $("#delMaterialGroup").addClass('readonly');
      			$("#editMaterialGroup").addClass('readonly');
			} else {
				appendList(data);

			}

		},
		error : function(e) {
			Alert.info("Exception :");
		}
	});
}
function saveMaterialGroupResp(data) {
	debugger;

	if (!$.isEmptyObject(data)) {
		if (data.response.hasError == false) {

			Alert.info(data.response.message);
			appendRecentRecord(data);
			$(".save").hide();
			$(".cancel").hide();
			/*$(".editSave").hide();*/
			$("#materialGroupFormId").addClass('readonly');
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
	var currentGroupId = $('.example').find('li.active').attr('id');
	/*var currentHSNId = $('.example').find('li.active').attr('id');*/
	$("#Id").val(data.materialGroupId);

	if (data.isActive == "Y")
		activeStatus = "Active";
	else
		activeStatus = "InActive";

	if (currentGroupId == data.materialGroupId) {
		$('#' + currentGroupId + '').remove();
	} else {
		$('#' + currentGroupId + '').removeClass();
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
			'<li ' + active + ' onclick="showdetails(' + data.materialGroupId
					+ ')" id="' + data.materialGroupId + '"> <a href="" class="" data-toggle="tab">'
					+ ' <div class="col-md-12">' + '  <label class="col-xs-6">'
					+ data.name + '</label>'
					+ '   <label class="col-xs-6 mytext" data-id="'
					+ data.materialGroupId + '">' + data.description
					+ '</label>' + '  </div>' + '  <div class="col-md-12">'
					+ '    <label class="col-xs-6">' + data.code + '</label>'
					+ '    <label class="col-xs-6 mytext2">' + activeStatus
					+ '</label>' + '  </div>' + '  </a>' + '  </li>');

	$(".Id").val(data.materialGroupId);
	$('#errorMsg').hide();
	$('#materialGroupFormId').addClass('readonly');
	$(".detail_Name").html(data.name);
	$(".detail_Code").html(data.code);
	$(".detail_Desc").html(data.description);
	$(".detail_Active").html(activeStatus);

	$('.example').paginathing();

}

function appendList(data) {
debugger;
	var active = " class='active'";
	$('#example').empty();
	var activeStatus = '';
	for (var i = 0; i < data.length; i++) {
		if (data[i].isActive == "Y")
			activeStatus = "Active";
		else
			activeStatus = "InActive";
		if (i == 0) {

			$(".Name").val(data[i].name);
			$(".Code").val(data[i].code);
			$(".Description").val(data[i].description);
			$(".Id").val(data[i].materialGroupId);
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
						+ data[i].materialGroupId + ')" id="' + data[i].materialGroupId + '"> <a href="" class="" data-toggle="tab">'
						+ ' <div class="col-md-12">'
						+ '  <label class="col-xs-6">' + data[i].name
						+ '</label>'
						+ '   <label class="col-xs-6 mytext" data-id="'
						+ data[i].materialGroupId + '">' + data[i].description
						+ '</label>' + '  </div>' + '  <div class="col-md-12">'
						+ '    <label class="col-xs-6">' + data[i].code
						+ '</label>' + '    <label class="col-xs-6 mytext2">'
						+ activeStatus + '</label>' + '  </div>' + '  </a>'
						+ '  </li>');
		active = "";
	}
	$('.example').paginathing();

	$(".tabs-left li a label").text(function(index, currentText) {
		return currentText.substr(0, 18);
	});
	$('.reportCount').html(data.length);

}
function showdetails(id) {
	debugger;
	
	$(".save").hide();
	$(".cancel").hide();
	$("#materialGroupFormId").addClass('readonly');
	
	$.ajax({
		type : "POST",
		contentType : "application/json",
		url : "getMaterialGroup/" + id,
		dataType : "json",
		async : false,
		success : function(data) {
			if ($.isEmptyObject(data)) {
				Alert.info("MaterialGroup details is empty");
			} else {
				var activeStatus = '';
				$(".Name").val(data.name);
				$(".Code").val(data.code);
				$(".Description").val(data.description);
				$(".Id").val(data.materialGroupId);
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
			Alert.info("Exception :");
		}
	});
}
