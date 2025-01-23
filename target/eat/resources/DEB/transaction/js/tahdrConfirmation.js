//var plugin_index = $('.paginate').length;
var tahdrConfirmationArray= new Array();
var rowCount=$('#approvalMatrixTableBody tr').length;
var userValue;
$(document).ready(function() {
	
	$("#confirmation").on('click',function(event) {
		event.preventDefault();
		$("#leftPane").removeClass('readonly');
		$(".addMatrix").removeClass('readonly');
		cacheLi();
		setCurrentTab(this);
		getConfirmation();
		var documentType=$(".documentType").val();
		setHeaderValues(documentType+"No.: "+tahdrCodes, documentType+"Title : "+title, "Department : "+department, "Status : "+tenderStatus);
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
		event.preventDefault();
		var approvalData=fetchList("getApprovalMatrixData",$(".tahdrId").val());
		var tableId='approvalMatrixTableBody';
		addApprovalMatrixRow(approvalData,tableId);
		mobiletable();
		$(".addMatrix").addClass('readonly');
		$(".addClassToRow").addClass('readonly');
		$("#approvalBtn-PU").addClass("readonly");
	});
	
});

function getConfirmation(){
	
	if(getChangedFlag()){
		$('.pagination').children().remove();
		$("#approvalMatrixDiv").css("display","none");
		var confirmData=fetchList("getTAHDR/"+$(".tahdrId").val()+"/"+$('#TAHDRDetail #tahdrDetailId').val(),null);
		if(confirmData!=null && confirmData!=undefined && confirmData.objectMap!=null){
			loadConfirmleftPaneData(confirmData.objectMap.tahdr);
		}
		var isActive=showButtons(confirmData.objectMap.tahdr,confirmData.objectMap.tahdrDetail,confirmData.objectMap.tahdrApprovalMatrix);
		showApprovalMatrixRoleAndStatusWise(confirmData.objectMap.approvalMatrix,confirmData.objectMap.tahdr,confirmData.objectMap.tahdrApprovalMatrix);
		/*if($('#approvalMatrixTableBody').find('tr').length==0){*/
		if(confirmData.objectMap.approvalMatrix.length==0){
			$("#approvalBtn-PU").removeClass("readonly");
		}
		else{
			$("#approvalBtn-PU").addClass("readonly");
		}
		if(isActive=='N'){
			$('.inactiveVersion_Confirmation').attr('style','display : none ;');
		}else{
			$('.inactiveVersion_Confirmation').removeAttr('style');
		}
		var status=confirmData.objectMap.tahdr==null?'':confirmData.objectMap.tahdr.tahdrStatusCode;
		if(status=='DR'){
			$('#generateButton').removeAttr('style');
			$('#addMatrixBtnId').removeAttr('style');
		}else{
			$('#generateButton').attr('style','display : none ;');
			$('#addMatrixBtnId').attr('style','display : none ;');
		}
		if(status=='AP'){
			$("#approvalBtn-PU").removeClass("readonly");
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


function showButtons(tahdr,tahdrDetail,approvalMatrixStatus){
	
	$("#ShowButton").empty();
	$(".generateDoc").show();
	$("#tenderConfirmationDivId").removeClass('readonly');
	if ($.isEmptyObject(tahdr)) {

		return;
	}
	
	var officeNoteId = tahdrDetail==null?'':tahdrDetail.officeNote == null ? '': tahdrDetail.officeNote.attachmentId == null ? '': tahdrDetail.officeNote.attachmentId;
	var officeNoteName = tahdrDetail==null?'':tahdrDetail.officeNote == null ? '': tahdrDetail.officeNote.fileName == null ? '': tahdrDetail.officeNote.fileName;	
	var isActive= tahdrDetail==null?'':tahdrDetail.isActive;
	var comment= tahdr.remarks==null?'':tahdr.remarks;
	var publishDate = tahdrDetail==null?'':tahdrDetail.publishingDate==null?'':tahdrDetail.publishingDate;
	var addButton = '';
	
	$("#approveComment").removeClass('requiredField');
	$("#publishComment").removeClass('requiredField');
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
		$("#publishingComments").show();
		$("#publishComment").attr('name','remarks');
		$("#publishComment").val(comment);
		$("#publishComment").addClass('readonly');
		if(publishDate!=null && publishDate!=""){
			$("#publishDate").show();
			$(".PublishingDate").val(formatDate(publishDate));
			$(".PublishingDate").addClass('readonly');	
		}
		if(officeNoteId!=null){
			$("#officeNote").show();
			$("#officeNoteAttachment").val(officeNoteId);
			/*$("#a_officeNoteAttachment").html(officeNoteName);*/
			var url=$("#a_officeNoteAttachment").data('url');
			$("#a_officeNoteAttachment").attr('href',url);
			var a_officeNoteCopy = $("#a_officeNoteAttachment").prop('href')+'/'+officeNoteId;
		    $("#a_officeNoteAttachment").prop('href', a_officeNoteCopy);
			$("#a_officeNoteAttachment").html(officeNoteName);	
		}
		else{
			$("#officeNote").show();
			$("#officeNote").val('');
			$("#a_officeNoteAttachment").val('');
			$("#a_officeNoteAttachment").html('');
			$("#a_officeNoteAttachment").removeAttr('href');
		}
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
		$("#publishComment").addClass('requiredField');
		$("#approvalComment").hide();
		$("#approveComment").removeAttr('name');
		$("#publishDate").show();
		$(".PublishingDate").val('');
		$(".PublishingDate").addClass('requiredField');
		$("#officeNote").show();
		if(officeNoteId!=null){
			$("#officeNoteAttachment").val(officeNoteId);
			/*$("#a_officeNoteAttachment").html(officeNoteName);*/
			var url=$("#a_officeNoteAttachment").data('url');
			$("#a_officeNoteAttachment").attr('href',url);
			var a_officeNoteCopy = $("#a_officeNoteAttachment").prop('href')+'/'+officeNoteId;
		    $("#a_officeNoteAttachment").prop('href', a_officeNoteCopy);
			$("#a_officeNoteAttachment").html(officeNoteName);	
		}
		else{
			$("#officeNote").val('');
			$("#a_officeNoteAttachment").val('');
			$("#a_officeNoteAttachment").html('');
			$("#a_officeNoteAttachment").removeAttr('href');
		}
		var documentType=$(".documentType").val();
		addButton=addButton+'<button class="btn btn-default statusButtons inactiveVersion_Confirmation"  id="approvalBtn-PU" value="PU" onclick="buttonStatus(this.id,event)" >Approve And Publish</button>';
		if(tahdr.tahdrTypeCode!='RFQ'){
			addButton=addButton+'<button class="btn btn-default statusButtons inactiveVersion_Confirmation"  id="approvalBtn-IP" value="IP" onclick="buttonStatus(this.id,event)" >Send For Approval</button>';
		}
		addButton=addButton+'<button class="btn btn-default statusButtons inactiveVersion_Confirmation"  id="approvalBtn-VO" value="VO" onclick="buttonStatus(this.id,event)" >Cancel '+documentType+'</button>';
	}
	else if(tahdr.tahdrStatusCode=='AP' && (approvalMatrixStatus==null||approvalMatrixStatus.status=='' || approvalMatrixStatus.status==null)){
		$("#approvalComment").show();
		$("#approveComment").attr('name','remarks');
		$("#approveComment").val(comment);
		$("#approveComment").addClass('readonly');
		$("#publishingComments").show();
		$("#publishComment").removeAttr('name');
		$("#publishComment").val('');
		$("#publishComment").addClass('requiredField');
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
		/*addButton=addButton+'<button class="btn btn-default statusButtons inactiveVersion_Confirmation" id="approvalBtn-RJ" value="RJ" onclick="buttonStatus(this.id,event)" >Reject</button>'+
		'<button class="btn btn-default statusButtons inactiveVersion_Confirmation"  id="approvalBtn-PU" value="PU" onclick="buttonStatus(this.id,event)" >Publish</button>'+
		'<button class="btn btn-default statusButtons inactiveVersion_Confirmation"  id="approvalBtn-VO" value="VO" onclick="buttonStatus(this.id,event)" >Cancel '+documentType+'</button>';*/
		addButton=addButton+'<button class="btn btn-default statusButtons inactiveVersion_Confirmation"  id="approvalBtn-PU" value="PU" onclick="buttonStatus(this.id,event)" >Publish</button>'+
		'<button class="btn btn-default statusButtons inactiveVersion_Confirmation"  id="approvalBtn-VO" value="VO" onclick="buttonStatus(this.id,event)" >Cancel '+documentType+'</button>';
	}
	else if(tahdr.tahdrStatusCode=='RJ' && (approvalMatrixStatus==null||approvalMatrixStatus.status=='' || approvalMatrixStatus.status==null)){
		$("#publishingComments").hide();
		$("#publishComment").removeAttr('name');
		$("#approvalComment").show();
		$("#approveComment").attr('name','remarks');
		$("#approveComment").val(comment);
		$("#approveComment").addClass('readonly');
		$("#publishDate").hide();
		if(officeNoteId!=null){
			$("#officeNote").show();
			$("#officeNoteAttachment").val(officeNoteId);
			/*$("#a_officeNoteAttachment").html(officeNoteName);*/
			var url=$("#a_officeNoteAttachment").data('url');
			$("#a_officeNoteAttachment").attr('href',url);
			var a_officeNoteCopy = $("#a_officeNoteAttachment").prop('href')+'/'+officeNoteId;
		    $("#a_officeNoteAttachment").prop('href', a_officeNoteCopy);
			$("#a_officeNoteAttachment").html(officeNoteName);	
		}
		else{
			$("#officeNote").hide();
			$("#officeNote").val('');
			$("#a_officeNoteAttachment").val('');
			$("#a_officeNoteAttachment").html('');
			$("#a_officeNoteAttachment").removeAttr('href');
		}
		var documentType=$(".documentType").val();
		addButton=addButton+'<h3>'+documentType+':'+tahdr.tahdrCode+' has Been Rejected</h3><br>'+
		'<button class="btn btn-default statusButtons inactiveVersion_Confirmation" id="approvalBtn-VO" value="VO" onclick="buttonStatus(this.id,event)" >Cancel '+documentType+'</button>';;
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
		addButton=addButton+'<button class="btn btn-default statusButtons inactiveVersion_Confirmation"  id="approvalBtn-VO" value="VO" onclick="buttonStatus(this.id,event)" >Cancel '+documentType+'</button>';
	}
	
	if(tahdr.tahdrStatusCode=='IP'&& (approvalMatrixStatus==null||approvalMatrixStatus.status=='' || approvalMatrixStatus.status==null)){
		$("#approvalComment").hide();
		$("#publishingComments").hide();
		$("#publishDate").hide();
		$("#officeNote").show();
		if(officeNoteId!=null){

			$("#officeNoteAttachment").val(officeNoteId);
			/*$("#a_officeNoteAttachment").html(officeNoteName);*/
			var url=$("#a_officeNoteAttachment").data('url');
			$("#a_officeNoteAttachment").attr('href',url);
			var a_officeNoteCopy = $("#a_officeNoteAttachment").prop('href')+'/'+officeNoteId;
		    $("#a_officeNoteAttachment").prop('href', a_officeNoteCopy);
			$("#a_officeNoteAttachment").html(officeNoteName);	
		}
		else{
			$("#officeNote").val('');
			$("#a_officeNoteAttachment").val('');
			$("#a_officeNoteAttachment").html('');
			$("#a_officeNoteAttachment").removeAttr('href');
		}
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
		$("#approveComment").addClass('requiredField');
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
		addButton=addButton+'<button class="btn btn-default statusButtons inactiveVersion_Confirmation" id="approvalBtn-AP" value="AP" onclick="buttonStatus(this.id,event)" >Approve</button>'+
		'<button class="btn btn-default statusButtons inactiveVersion_Confirmation" id="approvalBtn-RJ" value="RJ" onclick="buttonStatus(this.id,event)" >Reject</button>'+
		'<button class="btn btn-default statusButtons inactiveVersion_Confirmation" id="approvalBtn-VO" value="VO" onclick="buttonStatus(this.id,event)" >Cancel '+documentType+'</button>';
		}
	
	}
	
	if(tahdr.tahdrStatusCode!='DR' && tahdr.tahdrStatusCode!='IP' && tahdr.tahdrStatusCode!='AP' && tahdr.tahdrStatusCode!='RJ' && tahdr.tahdrStatusCode!='PU' && tahdr.tahdrStatusCode!='VO')
	{
		$("#approvalComment").hide();
		$("#publishingComments").show();
		$("#publishComment").attr('name','remarks');
		$("#publishComment").val(comment);
		$("#publishComment").addClass('readonly');
		$("#publishDate").show();
		$(".PublishingDate").val(formatDate(publishDate));
		$(".PublishingDate").addClass('readonly');
		if(officeNoteId!=null){
			$("#officeNote").show();
			$("#officeNoteAttachment").val(officeNoteId);
			/*$("#a_officeNoteAttachment").html(officeNoteName);*/
			var url=$("#a_officeNoteAttachment").data('url');
			$("#a_officeNoteAttachment").attr('href',url);
			var a_officeNoteCopy = $("#a_officeNoteAttachment").prop('href')+'/'+officeNoteId;
		    $("#a_officeNoteAttachment").prop('href', a_officeNoteCopy);
			$("#a_officeNoteAttachment").html(officeNoteName);	
		}
		else{
			$("#officeNote").show();
			$("#officeNote").val('');
			$("#a_officeNoteAttachment").val('');
			$("#a_officeNoteAttachment").html('');
			$("#a_officeNoteAttachment").removeAttr('href');
		}
		var documentType=$(".documentType").val();
		addButton=addButton+'<h3>'+documentType+':'+tahdr.tahdrCode+' is Under '+tenderStatus+'</h3><br>'+
		'<button class="btn btn-default statusButtons inactiveVersion_Confirmation" id="approvalBtn-VO" value="VO" onclick="buttonStatus(this.id,event)" >Cancel '+documentType+'</button>';
	}
		
		$("#ShowButton").append(addButton);
		if(tahdr.tahdrStatusCode=='DR'){
		$("#generateButton").show();
		$("#generatedDocText").hide();
		}
		else{
			$("#generateButton").hide();
			$("#generatedDocText").show();
		}
		if(!$.isEmptyObject(tahdrDetail.tenderDoc)){
			var att=tahdrDetail.tenderDoc;
			$("#generatedDocAnchor").empty();
			generateDocumentResp(att);
		}
		else{
			$("#generatedDocAnchor").empty();
		}
	setChildLoadFlag(true);
	return isActive;
}
function ApprovalResp(data) {
	if ($.isEmptyObject(data.objectMap.statusData)) {
		return;
	}
	if (!data.objectMap.statusData.hasError) {
		showStatusMessage(tahdrViewButton, data);
	} else {
		if (!$.isEmptyObject(data.objectMap.statusData.errors)) {
			var msg = '';
			$.each(data.objectMap.statusData.errors, function(key, value) {
				msg = msg + value.errorMessage+'.';
			});
			
		if(data.objectMap.statusData.errors[0].errorCode=='invalid.saleDate' && tenderStatus=='Approved' ){
			$(".editTahdr").css("display","inline-block");
			msg= msg +' '+'You need to Reject the'+' '+ $(".documentType").val()+', '+'if you want to change the Sale From Date.';
			Alert.warn(msg);
		}
		else if(data.objectMap.statusData.errors[0].errorCode=='invalid.saleDate' && tenderStatus=='In Progress'){
			$("#approvalBtn-AP").addClass("readonly");
			msg= msg +' '+'You need to Reject the'+' '+$(".documentType").val()+'.';
			Alert.warn(msg);
		}
		else{
			Alert.warn(msg);
		}
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
		$("#a_officeNoteAttachment").removeAttr('href');
		$("#a_officeNoteAttachment").html('');
		$('.officeNoteAttachment').attr('disabled', true);
		Alert.info(data.message);
	} else {
		Alert.warn(data.message);
	}

}

function buttonStatus(btnId,event){
	debugger;
	event.preventDefault();
	var tahdrStatus = $("#" + btnId).val();
	if(tahdrStatus=='IP'){
		var tahdrId= $(".tahdrId").val();
		submitWithParam("approvalAddedforTahdr", "tahdrId", "checkForApprovalAddedForIP");
	}
	else if(tahdrStatus=='VO'){
		$("#tahdrApprovalForm #tahdrStatusCode").val(tahdrStatus);
		CancelTahdr(event);
	}
	else{
	$("#tahdrApprovalForm #tahdrStatusCode").val(tahdrStatus);
	submitIt("tahdrApprovalForm", "submitTahdrDetails", "ApprovalResp");
	}
	
}

function CancelTahdr(event){
	
	event.preventDefault();
	swal({
		  title: 'Are you sure you want to cancel the Tender/Auction ?',
		  type: 'warning',
		  showCancelButton: true,
		  confirmButtonColor: '#3085d6',
		  cancelButtonColor: '#d33',
		  confirmButtonText: 'Yes'
		})
		.then(function(result){
		  if (result.value) {
				submitIt("tahdrApprovalForm", "submitTahdrDetails", "ApprovalResp");
		  }
		})
}

function checkForApprovalAddedForIP(data){
	var tahdrStatus='IP'
	if(!data.objectMap.result){
	Alert.warn(data.objectMap.resultMessage);
	}else{
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
			Alert.info(documentType+' '+"is :"+value.name);
		}
	});
	$("#tenderConfirmationDivId").addClass('readonly');
	/*$("#approvalMatrixDiv").addClass('readonly');*/
	$(".editDateAfterPublish").css("display","none");
	if(!$.isEmptyObject(data.objectMap.tahdrData)){
		
	loadConfirmleftPaneData(data.objectMap.tahdrData);
	tahdrCodes=data.objectMap.tahdrData.tahdrCode==null?'':data.objectMap.tahdrData.tahdrCode;
	title=data.objectMap.tahdrData.title==null?'':data.objectMap.tahdrData.title;
	department=data.objectMap.tahdrData.department==null?'':data.objectMap.tahdrData.department.name==null?'':data.objectMap.tahdrData.department.name;
	status=tahdrStatusList[data.objectMap.tahdrData.tahdrStatusCode];
	tenderStatus=tahdrStatusList[data.objectMap.tahdrData.tahdrStatusCode];
	var documentType=$(".documentType").val();
	setHeaderValues(documentType+"No.: "+tahdrCodes, documentType+"Title : "+title, "Department : "+department, "Status : "+tenderStatus);
	resetTenderStatus(tenderStatus);
	}
}

function generateDocument(event){
	event.preventDefault();
	showLoader();
	var tahdrId=$(".tahdrId").val();
	submitToURL("tenderReport/"+tahdrId , "generateDocumentResp");
	hideLoader();
	var documentType=$(".documentType").val();
	Alert.info(documentType+" document has been generated successfully.");
	
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
	
	var table = document.getElementById(tableId);
    rowCount = table.rows.length;
    var row = table.insertRow(rowCount);
    var tahdrId=$(".tahdrId").val();
    var s = "<select id='userId-"+(rowCount+1)+"' class='dropDown form-control2 form-control' form='approvalMatrix-"+(rowCount+1)+"' name='user.userId'><option value=''>Select User </option>";
    var arr = [];

    $.each(userData.objectMap.userData, function(key, value) {
        s += '<option id="approvalUserOptId_' + value.userId + '"  data-name="'+value.userDetails.firstName+' '+value.userDetails.lastName +'" title ="' + value.email+'-'+value.userDetails.firstName+' '+value.userDetails.lastName + '" value="' + value.userId + '">' + value.email+'-'+value.userDetails.firstName+' '+value.userDetails.lastName + '</option>'
    });
    s += '</select>';
    
    row.innerHTML = row.innerHTML +
    "<tr id='tableCount-"+(rowCount+1)+"'>"
    +"<td style='display:none;'><form id='approvalMatrix-"+(rowCount+1)+"' modelAttribute='approvalMatrix' method='POST' autocomplete='off'></form>"+
  	"<input type='hidden' form='approvalMatrix-"+(rowCount+1)+"' name='tahdrApprovalMatrixId' class='tahdrApprovalMatrix'  id='tahdrApprovalMatrixId-"+(rowCount+1)+"'/>" +
	"<input type='hidden' form='approvalMatrix-"+(rowCount+1)+"' name='tahdr.tahdrId' id='tahdrId' value='"+tahdrId+"'/></td>"+
    "<td><input type='hidden' form='approvalMatrix-"+(rowCount+1)+"' name='levels' id='level' value='"+(rowCount+1)+"'/> "+'Level-'+(rowCount+1) +"</td>"+
    '<td><span id="spanSelectedUserSpanId'+(rowCount+1)+'" class="activeTab">Name:</span><br>'+s+'</td>'+
    "<td><textarea class='form-control form-control2' id='remarks' disabled='disabled'></textarea></td>"+
    "<td><textarea class='form-control form-control2' id='status' disabled='disabled'></textarea></td>"+
    "<td><button class='btn btn-default saveApprovalMatrix' id='saveApprovalMatrix-"+(rowCount+1)+"' onclick='buttonsave(this.id,event)'>Save</button><input type='button' class='btn btn-default deleteRow' value='Remove' id='deleteApprovalMatrix-"+(rowCount+1)+"'  onclick='deleteApprovalMatrix(this.id,event);' /> <input type='button'  class='btn btn-default editRow' id='editApprovalMatrix-"+(rowCount+1)+"'  onclick='editApprovalMatrix(this.id,event)' value='Edit' style='display:none'/></td></tr>";
   /* onclick='this.parentNode.parentNode.parentNode.removeChild(this.parentNode.parentNode);'*/
    mobiletable();
}

function buttonsave(btnId,event){
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
	
	if(approvedMatrix.response.hasError==false){
		var approvalMatrixId= approvedMatrix.tahdrApprovalMatrixId;
		var tahdrApprovalMatrixId= "tahdrApprovalMatrixId-"+rowCount;
		$("#"+tahdrApprovalMatrixId).val(approvalMatrixId);
		Alert.info(approvedMatrix.response.message);
		userValue='Y';
		$("#editApprovalMatrix-"+rowCount).show();
		$("#saveApprovalMatrix-"+rowCount).hide();
		/*$("#userId-"+rowCount).attr("disabled", true);*/
		/*$("#userId-"+rowCount).addClass("readonly");*/
		$(".addMatrix").removeClass('readonly');
		$(".saveApprovalMatrix").addClass('addClassToRow');
		$(".deleteRow").addClass('addClassToRow');
		$(".editRow").addClass('addClassToRow');
		$(".addClassToRow").removeClass('readonly');
		
		var selectedUser=$('#userId-'+rowCount).find(":selected").data('name');
		 $('#userId-'+rowCount).attr('title',selectedUser);
		 $('#spanSelectedUserSpanId'+rowCount).html('Name:'+selectedUser);
	}
	else{
		Alert.warn(approvedMatrix.response.message);
		$("#editApprovalMatrix-"+rowCount).hide();
		$("#saveApprovalMatrix-"+rowCount).show();
		$("#userId-"+rowCount).removeAttr("disabled");
		$(".addMatrix").addClass('readonly');
	}
}

function deleteApprovalMatrix(btnId,event){
	
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
		$(".addMatrix").removeClass('readonly');
		$(".addClassToRow").removeClass('readonly');
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
	$(".addMatrix").removeClass('readonly');
	$(".addClassToRow").removeClass('readonly');
	}
	else{
		Alert.warn("Approval Cannot be Deleted");
		$(".addMatrix").addClass('readonly');
	}
	
	if($('#approvalMatrixTableBody').find('tr').length==0){
		$("#approvalBtn-PU").removeClass("readonly");
	}
	else{
		$("#approvalBtn-PU").addClass("readonly");
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

function editMatrix(btnId,event){
	event.preventDefault();
	var approvalid = btnId;
	var value = approvalid.split("-");
	var name=value[0];
	var valueId = value[1];
	rowCount=valueId;
	$("#userId-"+rowCount).removeAttr("disabled");
	$("#editApprovalMatrix-"+rowCount).hide();
	$("#saveApprovalMatrix-"+rowCount).show();
	var approvalData=fetchList("getApprovalMatrixData",$(".tahdrId").val());
	editUserDropDown(approvalData,valueId);
}

function editUserDropDown(userData,valueId){
	
	$.each(userData.objectMap.userData, function(key, value) {   
		$("#userId-"+valueId)
	         .append('<option id="approvalUserOptId_'+ value.userId+'" data-name="'+value.userDetails.firstName+' '+value.userDetails.lastName +'" title ="' + value.email+'-'+value.userDetails.firstName+' '+value.userDetails.lastName + '" value="' + value.userId + '">' + value.email+'-'+value.userDetails.firstName+' '+value.userDetails.lastName + '</option>');
	});
	 var selectedUser=$('#userId-'+valueId).find(":selected").data('name');
	 $('#userId-'+valueId).attr('title',selectedUser);
	 $('#spanSelectedUserSpanId'+valueId).html('Name:'+selectedUser);
}

function showApprovalMatrixData(approvalData,tahdrData){
	
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
			  '<td style="display:none;"><form id="approvalMatrix-'+(value.levels)+'" modelAttribute="approvalMatrix" method="POST" autocomplete="off"></form>'+
			  '<input type="hidden" form="approvalMatrix-'+(value.levels)+'" name="tahdrApprovalMatrixId" class="tahdrApprovalMatrix" id="tahdrApprovalMatrixId-'+value.levels+'" value="'+value.tahdrApprovalMatrixId+'"/>' +
			  '<input type="hidden" form="approvalMatrix-'+(value.levels)+'" name="tahdr.tahdrId" id="tahdrId" value="'+tahdrId+'"/></td>'+
			  '<td  class="col-sm-2" id="level"><input type="hidden" form="approvalMatrix-'+(value.levels)+'" name="levels" id="level" value="'+(value.levels)+'"/> '+'Level-'+value.levels+'</td>' +
   			  '<td  class="col-sm-3"><span id="spanSelectedUserSpanId'+value.levels+'" class="activeTab">Name:'+value.user.userDetails.firstName+' '+value.user.userDetails.lastName+'</span>'+
   			  '<br><select id="userId-'+(value.levels)+'" class="dropDown form-control2 form-control" form="approvalMatrix-'+(value.levels)+'" name="user.userId">'+
   			  '<option id="approvalUserOptId_' + value.user.userId + '" data-name="'+value.user.userDetails.firstName+' '+value.user.userDetails.lastName +'" class="approvalUserOptCls" title ="' + value.user.email+'-'+value.user.userDetails.firstName+' '+value.user.userDetails.lastName + '" value="' + value.user.userId + '">' + value.user.email+'-'+value.user.userDetails.firstName+' '+value.user.userDetails.lastName + '</option></td>'+
   			  '<td class="col-sm-2">'+remarks+'</td>'+ 
   			  '<td class="col-sm-2">'+statusName+'</td>' +
   			  '<td><button class="btn btn-default saveApprovalMatrix addClassToRow" id="saveApprovalMatrix-'+value.levels+'" onclick="buttonsave(this.id,event)" style="display:none">Save</button><input type="button" class="btn btn-default deleteRow addClassToRow" value="Remove" id="deleteApprovalMatrix-'+value.levels+'" onclick="deleteApprovalMatrix(this.id,event);" /><input type="button"  class="btn btn-default editRow addClassToRow" id="editApprovalMatrix-'+value.levels+'" onclick="editMatrix(this.id,event)" value="Edit" /></td>' +
      	 '</tr>');
		 var selectedUser=$('#userId-'+value.levels).find(":selected").data('name');
		 $('#userId-'+value.levels).attr('title',selectedUser);
   	  $('#approvalMatrixTable').DataTable();
	    });
	 mobiletable();
	 }
     else{
    	 $('#approvalMatrixTable tbody').empty();
    }
	
	
  }
   
function showApprovalMatrixRoleAndStatusWise(approvalData,tahdrData,tahdrApprovalMatrix){
	
	if($(".dataUrlTypeCode").val()=='getTAHDRByTypeCode' && $(".dataUrl").val()=='tenderPreparationData'){
		if(roleDto.value=='EXEENGR'){
			if(tenderStatus=='Drafted'){
				$("#approvalMatrixDiv").css("display","block");
				/*$("#approvalMatrixTable").css("display","block");*/
				/*$("#approvalMatrixDiv").removeClass('readonly');*/
				showApprovalMatrixData(approvalData,tahdrData);
			}
			else if(tenderStatus=='In Progress' || tenderStatus=='Approved' || tenderStatus=='Rejected' || tenderStatus=='Publish'){
				$("#approvalMatrixDiv").css("display","block");
				/*$("#approvalMatrixTable").css("display","block");*/
				$("#approvalMatrixDiv").addClass('readonly');
				showApprovalMatrixData(approvalData,tahdrData);
			}
			else{
				$("#approvalMatrixDiv").css("display","block");
				/*$("#approvalMatrixTable").css("display","block");*/
				$("#approvalMatrixDiv").addClass('readonly');
				showApprovalMatrixData(approvalData,tahdrData);
			}
		}
		else if(roleDto.value!='EXEENGR'){
			 if(tenderStatus=='Drafted' || tenderStatus=='In Progress' || tenderStatus=='Approved' || tenderStatus=='Rejected' || tenderStatus=='Publish'){
				/*$("#approvalMatrixDiv").css("display","none");
			   $("#approvalMatrixTable").css("display","none");*/
				/*$("#approvalMatrixDiv").addClass('readonly');*/
			}
		}
	}
	else if($(".dataUrlTypeCode").val()=='getTAHDRApprovalByTypeCode' && $(".dataUrl").val()=='tenderApprovalData'){
		if(!$.isEmptyObject(tahdrApprovalMatrix)){
			if(tahdrApprovalMatrix.levels==1){
				$("#approvalMatrixDiv").css("display","none");
				$("#approvalMatrixTable").css("display","none");
				$("#approvalMatrixDiv").addClass('readonly');
			}
			else{
				$("#approvalMatrixDiv").css("display","block");
				/*$("#approvalMatrixTable").css("display","block");*/
				$("#approvalMatrixDiv").addClass('readonly');
				showApprovalMatrixData(approvalData,tahdrData);
				$(".addMatrix").css("display","none");
			}
		}
		
	}
	
	
}

function resetTenderStatus(status){
	tenderStatus=status;
	$('.detailscont #rightBottomId').html('Status : '+status);
	$('#leftPane').find('li.active.tahdr #rightTopId').html(status);
	$('#leftPane').find('li.active.tahdrConfirmation #rightTopId').html(status);
}