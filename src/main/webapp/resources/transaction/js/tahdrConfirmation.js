//var plugin_index = $('.paginate').length;
var tahdrConfirmationArray= new Array();
var rowCount=$('#approvalMatrixTableBody tr').length;
var userValue;
$(document).ready(function() {
	
	$("#confirmation").on('click',function(event) {
		cacheLi();
		setCurrentTab(this);
		getConfirmation();
		setLeftPaneHeader("Confirmation", null);
	});
	$('.tableResponsive').each(function(){		
		var text = []
		$(this).find('thead tr th').each(function(){
			text.push($(this).text())

			for (var i = 0; i < text.length; i++) {
				$(this).parents('.tableResponsive').find('tbody tr td:nth-of-type(' + (i + 1) +')').attr('data-th', text[i])
			}	
		});		
	}); 
	$(".addMatrix").on('click',function(event){
		debugger;
		event.preventDefault();
		var approvalData=fetchList("getApprovalMatrixData",$(".tahdrId").val());
		var tableId='approvalMatrixTableBody';
		addApprovalMatrixRow(approvalData,tableId);
		mobiletable();
	});
	
});

function getConfirmation(){
	debugger;
	$("#leftPane").html("");
	if(getChangedFlag()){
		$('.pagination').children().remove();
		event.preventDefault();
		$("#approvalMatrixDiv").css("display","none");
		var confirmData=fetchList("getTAHDR/"+$(".tahdrId").val()+"/"+$('#TAHDRDetail #tahdrDetailId').val(),null);
		if(confirmData!=null && confirmData!=undefined && confirmData.objectMap!=null){
			loadConfirmleftPaneData(confirmData.objectMap.tahdr);
			showButtons(confirmData.objectMap.tahdr,confirmData.objectMap.tahdrApprovalMatrix);
			showApprovalMatrixRoleAndStatusWise(confirmData.objectMap.approvalMatrix,confirmData.objectMap.tahdr);
		}
		setChangedFlag(false);
	}else{
		getCacheLi();
	}
	setLeftPaneHeader(" Confirmation", null);
}

function loadConfirmleftPaneData(tahdrData){
	$("#leftPane").html("");
	$('.pagination').children().remove();
	var leftPanelHtml='';
	var i=0;
	var active="";
	var firstRow=null;
	if(!$.isEmptyObject(tahdrData)){
		firstRow = tahdrData;
		active="active";
		leftPanelHtml= leftPanelHtml +appendConfirmlLi(tahdrData,active);
		active="";
	}
	$("#leftPane").append(leftPanelHtml);
	
	$("#leftPane").on('click','.tahdrConfirmation',function(){
		var id=$(this).attr('id');
		showButtons(firstRow);
	});
	
	$('#leftPane').paginathing({perPage: 10});
	
}

function appendConfirmlLi(tahdrData, active) {
	
	return appendLiData(tahdrData.tahdrCode,tahdrStatusList[tahdrData.tahdrStatusCode], tahdrData.title,bidTypeList[tahdrData.bidTypeCode], tahdrData.tahdrId, active, 'tahdrConfirmation');
}


function showButtons(tahdr,approvalMatrixStatus){
	
	$("#ShowButton").empty();
	$(".generateDoc").show();
	$("#tenderConfirmationDivId").removeClass('readonly');
	if ($.isEmptyObject(tahdr)) {

		return;
	}
	
	var officeNoteId = tahdr.tahdrDetail[0]==null?'':tahdr.tahdrDetail[0].officeNote == null ? '': tahdr.tahdrDetail[0].officeNote.attachmentId == null ? '': tahdr.tahdrDetail[0].officeNote.attachmentId;
	var officeNoteName = tahdr.tahdrDetail[0]==null?'':tahdr.tahdrDetail[0].officeNote == null ? '': tahdr.tahdrDetail[0].officeNote.fileName == null ? '': tahdr.tahdrDetail[0].officeNote.fileName;	
	var comment= tahdr.remarks==null?'':tahdr.remarks;
	var publishDate = tahdr.tahdrDetail[0]==null?'':tahdr.tahdrDetail[0].publishingDate==null?'':tahdr.tahdrDetail[0].publishingDate;
	var addButton = '';
	$("#approveComment").removeClass('alphaNumericWithSpaceAndSpecialCharacter requiredField');
	$("#publishComment").removeClass('alphaNumericWithSpaceAndSpecialCharacter requiredField');
	$(".PublishingDate").removeClass('requiredField');
	$("#publishComment").removeAttr('name');
	$("#approveComment").removeAttr('name');
	if(tahdr.tahdrStatusCode=='PU'){
		$("#tahdrConfirmationFormFieldID").addClass('readonly');
	}
	else{
		$("#tahdrConfirmationFormFieldID").removeClass('readonly');
		$("#tenderConfirmationDivId").removeClass('readonly');
	}
	
	if(tahdr.tahdrStatusCode=='VO' && (approvalMatrixStatus==null||approvalMatrixStatus.status=='' || approvalMatrixStatus.status==null)){
		$("#approvalComment").hide();
		$("#publishingComments").hide();
		$("#publishDate").hide();
		$("#officeNote").hide();
		addButton=addButton+'<h3>'+tahdr.tahdrCode+' is Been Abandoned</h3>';
	}
	else if(tahdr.tahdrStatusCode==''){
		$("#approvalComment").hide();
		$("#publishingComments").hide();
		$("#publishDate").hide();
		$("#officeNote").hide();
	}
	else if(tahdr.tahdrStatusCode=='DR' && (approvalMatrixStatus==null||approvalMatrixStatus.status=='' || approvalMatrixStatus.status==null)){
		$("#publishingComments").show();
		$("#publishComment").attr('name','remarks');
		$("#publishComment").val('');
		$("#publishComment").addClass('alphaNumericWithSpaceAndSpecialCharacter requiredField');
		$("#approvalComment").hide();
		$("#approveComment").removeAttr('name');
		$("#publishDate").show();
		$(".PublishingDate").val('');
		$(".PublishingDate").addClass('requiredField');
		$("#officeNote").show();
		$("#officeNote").val('');
		$("#a_officeNoteAttachment").val('');
		var documentType=$(".documentType").val();
		addButton=addButton+'<button class="btn btn-default statusButtons"  id="approvalBtn-PU" value="PU" onclick="buttonStatus(this.id,event)" >Approve And Publish</button>'+
		'<button class="btn btn-default statusButtons"  id="approvalBtn-IP" value="IP" onclick="buttonStatus(this.id,event)" >Send For Approval</button>'+
		'<button class="btn btn-default statusButtons"  id="approvalBtn-VO" value="VO" onclick="buttonStatus(this.id,event)" >Cancel'+documentType+'</button>';
	}
	else if(tahdr.tahdrStatusCode=='AP' && (approvalMatrixStatus==null||approvalMatrixStatus.status=='' || approvalMatrixStatus.status==null)){
		$("#approvalComment").show();
		$("#approveComment").attr('name','remarks');
		$("#approveComment").val(comment);
		$("#approveComment").addClass('readonly');
		$("#publishingComments").show();
		$("#publishComment").removeAttr('name');
		$("#publishComment").val('');
		$("#publishComment").addClass('alphaNumericWithSpaceAndSpecialCharacter requiredField');
		$("#publishDate").show();
		$(".PublishingDate").val('');
		$(".PublishingDate").addClass('requiredField');
		$("#officeNote").show();
		$("#officeNoteAttachment").val(officeNoteId);
		/*$("#a_officeNoteAttachment").html(officeNoteName);*/
		var url=$("#a_officeNoteAttachment").data('url');
		$("#a_officeNoteAttachment").attr('href',url);
		var a_officeNoteCopy = $("#a_officeNoteAttachment").prop('href')+'/'+officeNoteId;
	    $("#a_officeNoteAttachment").prop('href', a_officeNoteCopy);
		$("#a_officeNoteAttachment").html(officeNoteName);
		var documentType=$(".documentType").val();
		addButton=addButton+'<button class="btn btn-default statusButtons"  id="approvalBtn-PU" value="PU" onclick="buttonStatus(this.id,event)" >Publish</button>'+
		'<button class="btn btn-default statusButtons"  id="approvalBtn-VO" value="VO" onclick="buttonStatus(this.id,event)" >Cancel'+documentType+'</button>';
	}
	else if(tahdr.tahdrStatusCode=='RJ' && (approvalMatrixStatus==null||approvalMatrixStatus.status=='' || approvalMatrixStatus.status==null)){
		$("#publishingComments").hide();
		$("#publishComment").removeAttr('name');
		$("#approvalComment").show();
		$("#approveComment").attr('name','remarks');
		$("#approveComment").val(comment);
		$("#approveComment").addClass('readonly');
		$("#publishDate").hide();
		$("#officeNote").hide();
		var documentType=$(".documentType").val();
		addButton=addButton+'<h3>'+documentType+':'+tahdr.tahdrCode+' is Been Rejected</h3>';
	}
	else if(tahdr.tahdrStatusCode=='PU' && (approvalMatrixStatus==null||approvalMatrixStatus.status=='' || approvalMatrixStatus.status==null)){
		$("#approvalComment").hide();
		$("#approveComment").removeAttr('name');
		$("#publishingComments").show();
		$("#publishComment").attr('name','remarks');
		$("#publishComment").val(comment);
		$("#publishComment").addClass('readonly');
		$("#publishDate").show();
		$(".PublishingDate").val(formatDate(publishDate));
		$(".PublishingDate").addClass('readonly');
		$("#officeNote").show();
		$("#officeNoteAttachment").val(officeNoteId);
		/*$("#a_officeNoteAttachment").html(officeNoteName);*/
		var url=$("#a_officeNoteAttachment").data('url');
		$("#a_officeNoteAttachment").attr('href',url);
		var a_officeNoteCopy = $("#a_officeNoteAttachment").prop('href')+'/'+officeNoteId;
	    $("#a_officeNoteAttachment").prop('href', a_officeNoteCopy);
		$("#a_officeNoteAttachment").html(officeNoteName);
		var documentType=$(".documentType").val();
		addButton=addButton+'<button class="btn btn-default statusButtons"  id="approvalBtn-VO" value="VO" onclick="buttonStatus(this.id,event)" >Cancel'+documentType+'</button>';
	}
	
	if(tahdr.tahdrStatusCode=='IP'&& (approvalMatrixStatus==null||approvalMatrixStatus.status=='' || approvalMatrixStatus.status==null)){
		$("#approvalComment").hide();
		$("#publishingComments").hide();
		$("#publishDate").hide();
		$("#officeNote").hide();
		var documentType=$(".documentType").val();
		addButton=addButton+'<h3>'+documentType+':'+tahdr.tahdrCode+' is In Progress</h3>';
	}
	if(!$.isEmptyObject(approvalMatrixStatus)){

		if(tahdr.tahdrStatusCode=='IP' && approvalMatrixStatus.status=='IP')
		{
		$("#publishingComments").hide();
		$("#publishComment").removeAttr('name');
		$("#approvalComment").show();
		$("#approveComment").attr('name','remarks');
		$("#approveComment").val('');
		$("#approveComment").addClass('alphaNumericWithSpaceAndSpecialCharacter requiredField');
		/*$("#approveComment").val(comment);*/
		$("#publishDate").hide();
		$("#officeNote").show();
		$("#officeNoteAttachment").val(officeNoteId);
		/*$("#a_officeNoteAttachment").html(officeNoteName);*/
		var url=$("#a_officeNoteAttachment").data('url');
		$("#a_officeNoteAttachment").attr('href',url);
		var a_officeNoteCopy = $("#a_officeNoteAttachment").prop('href')+'/'+officeNoteId;
	    $("#a_officeNoteAttachment").prop('href', a_officeNoteCopy);
		$("#a_officeNoteAttachment").html(officeNoteName);
		var documentType=$(".documentType").val();
		addButton=addButton+'<button class="btn btn-default statusButtons" id="approvalBtn-AP" value="AP" onclick="buttonStatus(this.id,event)" >Approve</button>'+
		'<button class="btn btn-default statusButtons" id="approvalBtn-RJ" value="RJ" onclick="buttonStatus(this.id,event)" >Reject</button>'+
		'<button class="btn btn-default statusButtons" id="approvalBtn-VO" value="VO" onclick="buttonStatus(this.id,event)" >Cancel'+documentType+'</button>';
		}
	
	}
	
	if(tahdr.tahdrStatusCode!='DR' && tahdr.tahdrStatusCode!='IP' && tahdr.tahdrStatusCode!='AP' && tahdr.tahdrStatusCode!='RJ' && tahdr.tahdrStatusCode!='PU' && tahdr.tahdrStatusCode!='VO')
	{
		$("#approvalComment").hide();
		$("#publishingComments").hide();
		$("#publishDate").hide();
		$("#officeNote").hide();
		var documentType=$(".documentType").val();
		addButton=addButton+'<h3>'+documentType+':'+tahdr.tahdrCode+' is Under '+tenderStatus+'</h3>';
	}
		
		$("#ShowButton").append(addButton);
		if(tahdr.tahdrStatusCode=='DR'){
		$("#generateButton").show();	
		}
		else{
			$("#generateButton").hide();
		}
		
		if(!$.isEmptyObject(tahdr.tahdrDetail)){
		if(!$.isEmptyObject(tahdr.tahdrDetail[0].tenderDoc)){
			var att=tahdr.tahdrDetail[0].tenderDoc;
			$("#generatedDocAnchor").empty();
			generateDocumentResp(att);
		}
		else{
			$("#generatedDocAnchor").empty();
		}
	}
	setChildLoadFlag(true);
}
function ApprovalResp(data) {
	debugger;
	if ($.isEmptyObject(data.objectMap.statusData)) {
		return;
	}
	if (!data.objectMap.statusData.hasError) {
		showStatusMessage(tahdrViewButton, data);
	} else {
		if (!$.isEmptyObject(data.objectMap.statusData.errors)) {
			var msg = 'Following Tabs Not Filled:';
			$.each(data.objectMap.statusData.errors, function(key, value) {
				msg = msg + value.errorMessage;
			});
			Alert.warn(msg);
		}
		else if(data.objectMap.statusData.hasError){
			Alert.warn(data.objectMap.statusData.message);
		}
	}
}

function officeNoteDeleteResp(data) {
	
	if (!data.hasError) {
		$('#officeNoteId').val('');
		$('#officeNoteAttachment').val('');
		$("#a_officeNoteAttachment").html('');
		$('.officeNoteAttachment').attr('disabled', true);
		Alert.info(data.message);
	} else {
		Alert.warn(data.message);
	}

}

function buttonStatus(btnId,event){
	
	event.preventDefault();
	var tahdrStatus = $("#" + btnId).val();
	if(tahdrStatus=='IP'){
		var tahdrId= $(".tahdrId").val();
		submitWithParam("approvalAddedforTahdr", "tahdrId", "checkForApprovalAddedForIP");
	}
	else{
	$("#tahdrApprovalForm #tahdrStatusCode").val(tahdrStatus);
	submitIt("tahdrApprovalForm", "submitTahdrDetails", "ApprovalResp");
	}
}

function checkForApprovalAddedForIP(data){
	debugger;
	var tahdrStatus='IP'
	console.log(data);
	if(!data.objectMap.result){
	Alert.warn(data.objectMap.resultMessage);
}
else{
	$("#publishComment").removeClass('alphaNumericWithSpaceAndSpecialCharacter requiredField');
	$(".PublishingDate").removeClass('requiredField');
	$("#tahdrApprovalForm #tahdrStatusCode").val(tahdrStatus);
	submitIt("tahdrApprovalForm", "submitTahdrDetails", "ApprovalResp");
}
}

function showStatusMessage(tahdrViewButton, data) {
	
	$.each(tahdrViewButton, function(key, value) {
		if (data.objectMap.statusData.message == value.code) {
			var documentType=$(".documentType").val();
			Alert.info(documentType+"is :"+value.name);
		}
	});
	$("#tenderConfirmationDivId").addClass('readonly');
	if(!$.isEmptyObject(data.objectMap.tahdrData)){
		debugger;
	loadConfirmleftPaneData(data.objectMap.tahdrData);
	tahdrCodes=data.objectMap.tahdrData.tahdrCode==null?'':data.objectMap.tahdrData.tahdrCode;
	title=data.objectMap.tahdrData.title==null?'':data.objectMap.tahdrData.title;
	department=data.objectMap.tahdrData.department==null?'':data.objectMap.tahdrData.department.name==null?'':data.objectMap.tahdrData.department.name;
	status=tahdrStatusList[data.objectMap.tahdrData.tahdrStatusCode];
	tenderStatus=tahdrStatusList[data.objectMap.tahdrData.tahdrStatusCode];
	var documentType=$(".documentType").val();
	setHeaderValues(documentType+"No.: "+tahdrCodes, documentType+"Title : "+title, "Department : "+department, "Status : "+tenderStatus);
	}
}

function generateDocument(event){
	event.preventDefault();
	showLoader();
	var tahdrId=$(".tahdrId").val();
	submitToURL("tenderReport/"+tahdrId , "generateDocumentResp");
	var documentType=$(".documentType").val();
	Alert.info(documentType+" document has been generated successfully.");
	hideLoader();
}

function generateDocumentResp(resp){
	if($.isEmptyObject(resp)){
		$("#generatedDocAnchor").empty();
	}else if(!$.isEmptyObject(resp.response) && resp.response.hasError){
		Alert.warn(resp.response.message);
		$("#generatedDocAnchor").empty();
	}else{
			var anchorHtml='<a href="download/'+resp.attachmentId+'">'+resp.fileName+'</a>';
			$("#generatedDocAnchor").empty();
			$("#generatedDocAnchor").append(anchorHtml);
	}
}

function addApprovalMatrixRow(userData,tableId){
	debugger;
	var table = document.getElementById(tableId);
    rowCount = table.rows.length;
    var row = table.insertRow(rowCount);
    var tahdrId=$(".tahdrId").val();
    var s = "<select id='userId-"+(rowCount+1)+"' class='dropDown form-control2 form-control' form='approvalMatrix-"+(rowCount+1)+"' name='user.userId'><option value=''>Select User </option>";
    var arr = [];

    $.each(userData.objectMap.userData, function(key, value) {
        s += '<option value="' + value.userId + '">' + value.email+'-'+value.userDetails.firstName+value.userDetails.lastName + '</option>'
    });
    s += '</select>';
    
    row.innerHTML = row.innerHTML +
    "<tr id='tableCount-"+(rowCount+1)+"'>"
    +"<td style='display:none;'><form id='approvalMatrix-"+(rowCount+1)+"' modelAttribute='approvalMatrix' method='POST' autocomplete='off'></form>"+
  	"<input type='hidden' form='approvalMatrix-"+(rowCount+1)+"' name='tahdrApprovalMatrixId' class='tahdrApprovalMatrix'  id='tahdrApprovalMatrixId-"+(rowCount+1)+"'/>" +
	"<input type='hidden' form='approvalMatrix-"+(rowCount+1)+"' name='tahdr.tahdrId' id='tahdrId' value='"+tahdrId+"'/></td>"+
    "<td><input type='hidden' form='approvalMatrix-"+(rowCount+1)+"' name='levels' id='level' value='"+(rowCount+1)+"'/> "+'Level-'+(rowCount+1) +"</td>"+
    "<td>"+s+"</td>"+
    "<td><textarea class='form-control form-control2' id='remarks' disabled='disabled'></textarea></td>"+
    "<td><textarea class='form-control form-control2' id='status' disabled='disabled'></textarea></td>"+
    "<td><button class='btn btn-default saveApprovalMatrix' id='saveApprovalMatrix-"+(rowCount+1)+"' onclick='buttonsave(this.id,event)'>Save</button><input type='button' class='btn btn-default deleteRow' value='Remove' id='deleteApprovalMatrix-"+(rowCount+1)+"'  onclick='deleteApprovalMatrix(this.id,event);' /> <input type='button'  class='btn btn-default editRow' id='editApprovalMatrix-"+(rowCount+1)+"'  onclick='editApprovalMatrix(this.id,event)' value='Edit' style='display:none'/></td></tr>";
   /* onclick='this.parentNode.parentNode.parentNode.removeChild(this.parentNode.parentNode);'*/
    mobiletable();
}

function buttonsave(btnId,event){
	debugger;
	event.preventDefault();
	var approvalid = btnId;
	var value = approvalid.split("-");
	var name=value[0];
	var valueId = value[1];
	rowCount=valueId;
	var userId = $('#userId-'+valueId).val();
	if (userId=="" || userId==null || userId==undefined) {
		 $('#userId-'+valueId).focus();
		 $('#userId-'+valueId).addClass('errorinput');
	}else{
		
		 $('#userId-'+valueId).removeClass('errorinput');
	var formId="approvalMatrix-"+valueId;
	var action="saveApprovalMatrix";
	var callBack="approvalMatrixResp";
	submitIt(formId,action, callBack);
	
	}
}

function approvalMatrixResp(approvedMatrix){
	debugger;
	if(approvedMatrix.response.hasError==false)
	{
		var approvalMatrixId= approvedMatrix.tahdrApprovalMatrixId;
		var tahdrApprovalMatrixId= "tahdrApprovalMatrixId-"+rowCount;
		$("#"+tahdrApprovalMatrixId).val(approvalMatrixId);
		Alert.info(approvedMatrix.response.message);
		userValue='Y';
		$("#editApprovalMatrix-"+rowCount).show();
		$("#saveApprovalMatrix-"+rowCount).hide();
		$("#userId-"+rowCount).attr("disabled", true);
	}
	else{
		Alert.warn(approvedMatrix.response.message);
		$("#editApprovalMatrix-"+rowCount).hide();
		$("#saveApprovalMatrix-"+rowCount).show();
		$("#userId-"+rowCount).removeAttr("disabled");
	}
}

function deleteApprovalMatrix(btnId,event){
	debugger;
	event.preventDefault();
	var approvalid = btnId;
	var value = approvalid.split("-");
	var name=value[0];
	var valueId = value[1];
	var formId="tahdrApprovalMatrixId-"+valueId;
	var ApprovalMatrixId=$('#'+formId).val();
	if(ApprovalMatrixId!=null && ApprovalMatrixId!=""){
		var deleteTD="approvalMatrix-"+valueId;
		var tahdrId=$("#tahdrId").val();
		var deletedData=fetchList("deleteApprovalMatrix/"+ApprovalMatrixId+"/"+tahdrId,null)
		approvalMatrixDelresp(deletedData,approvalid);
	}
	else{
		$('#approvalMatrixTable tr:last').remove();
	}
}

function approvalMatrixDelresp(data,deleteId){
	
	if(!data.hasError){
		if(!$.isEmptyObject(data.objectMap)){
			showApprovalMatrixRoleAndStatusWise(data.objectMap.approvalData,data.objectMap.tahdr)
		}
		else if($.isEmptyObject(data.objectMap) && data.message=="Record deleted"){
			$('#'+deleteId).parent().parent().remove();
		}
	Alert.info(data.message);
	}
	else{
		Alert.warn("Approval Cannot be Deleted");
	}
	}

function editApprovalMatrix(btnId,event){
	event.preventDefault();
	var approvalid = btnId;
	var value = approvalid.split("-");
	var name=value[0];
	var valueId = value[1];
	rowCount=valueId;
	$("#userId-"+rowCount).removeAttr("disabled");
	$("#editApprovalMatrix-"+rowCount).hide();
	$("#saveApprovalMatrix-"+rowCount).show();
	
}

function showApprovalMatrixData(approvalData,tahdrData){
	 debugger;
	 var res="";
	 if(!$.isEmptyObject(tahdrData)){
	var tahdrId=tahdrData.tahdrId==null?'':tahdrData.tahdrId;
	var remarks = tahdrData.remarks==null?'':tahdrData.remarks;
	/*$("#approvalMatrixTable").DataTable().destroy();*/
	 $('#approvalMatrixTable tbody').empty();
	 $.each(approvalData, function(key, value) {
		var statusName=value.status==null?'':value.status;
		var remarks=value.remarks==null?'':value.remarks;
		 if(statusName=='AP'){
			 statusName='Approved';
		}
		 else if(statusName=='RJ'){
			 statusName='Rejected';
		 }
		 else if(statusName=='IP'){
			 statusName="";
		 }
		 
		 if(tenderStatus=='Drafted'){
			 remarks='';
		 }
		  
		 $('#approvalMatrixTable tbody').append('<tr>'+
			  '<td style="display:none;">'+
			  '<input type="hidden" class="tahdrApprovalMatrix" id="tahdrApprovalMatrixId-'+value.levels+'" value="'+value.tahdrApprovalMatrixId+'"/>' +
			  '<input type="hidden" id="tahdrId" value="'+value.tahdrId+'"/></td>'+
			  '<td  class="col-sm-2" id="level">'+'Level-'+value.levels+'</td>' +
   			  '<td  class="col-sm-3">'+value.user.email+'-'+value.user.userDetails.firstName+value.user.userDetails.lastName+'</td>' +
   			  '<td class="col-sm-2">'+remarks+'</td>'+ 
   			  '<td class="col-sm-2">'+statusName+'</td>' +
   			  '<td><button class="btn btn-default saveApprovalMatrix" id="saveApprovalMatrix-'+value.levels+'" onclick="buttonsave(this.id,event)" style="display:none">Save</button><input type="button" class="btn btn-default deleteRow" value="Remove" id="deleteApprovalMatrix-'+value.levels+'" onclick="deleteApprovalMatrix(this.id,event);" /><input type="button"  class="btn btn-default editRow" id="editApprovalMatrix-'+value.levels+'" onclick="editApprovalMatrix(this.id,event)" value="Edit" /></td>' +
      	 '</tr>');
   	  $('#approvalMatrixTable').DataTable();
	    });
	 mobiletable();
	 }
     else{
    	 $('#approvalMatrixTable tbody').empty();
    	 }
	
	
     }
   
function showApprovalMatrixRoleAndStatusWise(approvalData,tahdrData){
	debugger;
	if($(".dataUrlTypeCode").val()=='getTAHDRByTypeCode' && $(".dataUrl").val()=='tenderPreparationData'){
		if(roleDto.value=='EXEENGR'){
			if(tenderStatus=='Drafted'){
				$("#approvalMatrixDiv").css("display","block");
				$("#approvalMatrixDiv").removeClass('readonly');
				showApprovalMatrixData(approvalData,tahdrData);
			}
			else if(tenderStatus=='In Progress' || tenderStatus=='Approved' || tenderStatus=='Rejected' || tenderStatus=='Publish'){
				$("#approvalMatrixDiv").css("display","block");
				$("#approvalMatrixDiv").addClass('readonly');
				showApprovalMatrixData(approvalData,tahdrData);
			}
		}
		else if(roleDto.value!='EXEENGR'){
			 if(tenderStatus=='Drafted' || tenderStatus=='In Progress' || tenderStatus=='Approved' || tenderStatus=='Rejected' || tenderStatus=='Publish'){
				$("#approvalMatrixDiv").css("display","none");
				$("#approvalMatrixDiv").addClass('readonly');
			}
		}
	}
	else if($(".dataUrlTypeCode").val()=='getTAHDRApprovalByTypeCode' && $(".dataUrl").val()=='tenderApprovalData'){
		$("#approvalMatrixDiv").css("display","none");
		$("#approvalMatrixDiv").addClass('readonly');
	}
	
	
}
