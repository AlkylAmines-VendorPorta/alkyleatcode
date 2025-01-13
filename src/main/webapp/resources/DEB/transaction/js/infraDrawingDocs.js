var drawingDocsArray=new Array();
$(document).ready(function(){
	
	$('#cancelDocBtnId').click(function(event){
		
		event.preventDefault();
		var currentDocId = $('ul.leftPaneData').find('li.active').attr('id');
		if(currentDocId!=undefined){
			var drawingDocsData=drawingDocsArray["drawingDocs"+currentDocId];
			loadDrawingDocsRightPane(drawingDocsData);
		}else{
			  $("#drawingDocNameId").val("");
			  $("#materialDrawingDoc").val(""); 
			  $("#docComment").val("");
			  setStatus("",'docApprove','docClarify','docReject');
		}
	});
});
function onTabInfraDrawingDocsLoad(){
	event.preventDefault();
	var bPartnerId=$('.bPartnerId').val();
	var infraItemId=$('.partnerInfraItemId').val();
	if(bPartnerId!='' && infraItemId!=''){
		submitToURL('getInfraDrawingDocs?partnerId='+bPartnerId+'&infraItemId='+infraItemId, 'onTabInfraDrawingDocsLoadResp');
	}else{
		Alert.warn('Something went wrong');
		event.stopPropagation();
	}
	 
}

function onTabInfraDrawingDocsLoadResp(data){
	
	if(data.objectMap.hasOwnProperty('drawingDocList')){
		loadDrawingDocsLeftPane(data.objectMap.drawingDocList);
	}
}

function loadDrawingDocsLeftPane(data){
	
	$('.pagination').children().remove();
	$(".leftPaneData").html("");
	var leftPanelHtml="";
	var i=0;
	var active=false;
	var firstRow=null;
	if(data==null){
		  $("#drawingDocNameId").val("");
		  $("#materialDrawingDoc").val(""); 
		  $("#docComment").val("");
		  setStatus("",'docApprove','docClarify','docReject');
		  return;
		}
	$.each(data,function(key,value){
		
		drawingDocsArray["drawingDocs"+value.partnerItemDrawingDocId]=value;
		if(i==0){
			firstRow = value;
			leftPanelHtml = leftPanelHtml + ' <li onclick="showDrawingDocsInfo('+value.partnerItemDrawingDocId+')" id="'+value.partnerItemDrawingDocId+'" class="active">'
		}else{
			leftPanelHtml = leftPanelHtml + ' <li onclick="showDrawingDocsInfo('+value.partnerItemDrawingDocId+')" id="'+value.partnerItemDrawingDocId+'">'
		}
		leftPanelHtml = leftPanelHtml +' <a href="#Master" data-toggle="tab">'
        +' <div class="col-md-12">'
        +'  <label class="col-xs-6 ">'+value.partnerInfraItem.material.name+' </label>'
        +'	<label class="col-xs-6 ">'+value.materialDrawingDoc.docName+'</label>'
        +' </div>'	
        +' </a>'
        +' </li>';
		i++;
	});
	$(".leftPaneData").append(leftPanelHtml);
	$('.leftPaneData').paginathing();
	
	$(".tabs-left li a label").text(function(index, currentText) {
	    return currentText.substr(0, 20);
	});
	loadDrawingDocsRightPane(firstRow);
}
function showDrawingDocsInfo(id){
	
	var drawingDocsData=drawingDocsArray["drawingDocs"+id];
	loadDrawingDocsRightPane(drawingDocsData);
}
function loadDrawingDocsRightPane(data){
	
  if(!$.isEmptyObject(data)){
	  var docId=data.partnerItemDrawingDocId==null?'':data.partnerItemDrawingDocId;
	  var name=data.materialDrawingDoc==null?'':data.materialDrawingDoc.docName==null?'':data.materialDrawingDoc.docName;
      /*var remark=data.remark==null?'':data.remark;
      var status=data.status==null?'':data.status;*/
     
      $("#drawingDocNameId").val(name);
	  $("#partnerItemDrawingDocId").val(docId);
      
	  showDrawingDocFields(data);
  }else{
	  $("#drawingDocNameId").val("");
	  $("#partnerItemDrawingDocId").val(""); 
	  $("#docComment").val("");
	  setStatus("",'docApprove','docClarify','docReject');
  }	
}
function showDrawingDocFields(data){
	
	var pageInfo=$("#pageInfo").val();
    if(pageInfo=="vendorDetails")
	{
    	  $("#previousApprovedDiv").hide(); 
	}else if(pageInfo=="vendorApproval"){
		
		var isEEApproved=data.isEEApproved==null?'':data.isEEApproved;
        var isSEApproved=data.isSEApproved==null?'':data.isSEApproved;
        var isCEApproved=data.isCEApproved==null?'':data.isCEApproved;
        var isEDApproved=data.isEDApproved==null?'':data.isEDApproved;
        var isDIRApproved=data.isDIRApproved==null?'':data.isDIRApproved;
        var eeComment=data.eeComment==null?'':data.eeComment;
        var seComment=data.seComment==null?'':data.seComment;
        var ceComment=data.ceComment==null?'':data.ceComment;
        var edComment=data.edComment==null?'':data.edComment;
        var dirComment=data.dirComment==null?'':data.dirComment;
        
      var role=$("#roleName").val();
 	  if(role=="EXEENGR"){
 		  $("#previousApprovedDiv").hide();
 		  $("#docComment").val(eeComment);
 		  $("#prevDocComment").val("");
 		  setStatus(isEEApproved,'docApprove','docClarify','docReject');
 		  setStatus("",'prevDocApprove','prevDocClarify','prevDocReject');
 	  }else if(role=="SCRENGR"){
 		  $("#previousApprovedDiv").show();
 		  $("#docComment").val(seComment);
 		  $("#prevDocComment").val(eeComment);
 		  setStatus(isSEApproved,'docApprove','docClarify','docReject');
 		  setStatus(isEEApproved,'prevDocApprove','prevDocClarify','prevDocReject');
 	  }else if(role=="CHFENGR"){
 		  $("#previousApprovedDiv").show();
 		  $("#docComment").val(ceComment);
 		  $("#prevDocComment").val(seComment);
 		  setStatus(isCEApproved,'docApprove','docClarify','docReject');
 		  setStatus(isSEApproved,'prevDocApprove','prevDocClarify','prevDocReject');
 	  }else if(role=="EXEDIR"){
 		  $("#previousApprovedDiv").show();
 		  $("#docComment").val(edComment);
 		  $("#prevDocComment").val(ceComment);
 		  setStatus(isEDApproved,'docApprove','docClarify','docReject');
 		  setStatus(isCEApproved,'prevDocApprove','prevDocClarify','prevDocReject');
 	  }else if(role=="DIR"){
 		  $("#previousApprovedDiv").show();
 		  $("#docComment").val(dirComment);
 		  $("#prevDocComment").val(edComment);
 		  setStatus(isDIRApproved,'docApprove','docClarify','docReject');
 		  setStatus(isEDApproved,'prevDocApprove','prevDocClarify','prevDocReject');
 	  }
	   $(".infraTabs").addClass('readonly');
	   $("#saveDocBtnId").show();
	   $("#cancelDocBtnId").show();
	 
	}
}
function setNameAttrByRole(){
	
}
function saveDrawingDocsResp(data){
	
	if(!data.response.hasError)
	{
	var currentInfraDocId = $('ul.leftPaneData').find('li.active').attr('id');
	var leftPanelHtml = "";
	var partnerItemDrawingDocId = data.partnerItemDrawingDocId;

	$('#partnerItemDrawingDocId').val(partnerItemDrawingDocId);
	if (currentInfraDocId == partnerItemDrawingDocId) {
		$('#'+currentInfraDocId).remove();
	}else {
		$('#'+currentInfraDocId).removeClass('active');
	}
	
	leftPanelHtml = leftPanelHtml + ' <li onclick="showDrawingDocsInfo('+partnerItemDrawingDocId+')" id="'+partnerItemDrawingDocId+'" class="active">';
	leftPanelHtml = leftPanelHtml +' <a href="#Master" data-toggle="tab">'
    +' <div class="col-md-12">'
    +'  <label class="col-xs-6 "> '+$("#infraMaterialId").text()+'</label>'
    +'	<label class="col-xs-6 ">'+data.materialDrawingDoc.docName+'</label>'
    +' </div>'	
    +' </a>'
    +' </li>';
	$(".leftPaneData").prepend(leftPanelHtml);
	Alert.info(data.response.message);
	drawingDocsArray["drawingDocs"+data.partnerItemDrawingDocId]=data;
	}else{
		Alert.warn(data.response.message);
	}

}
function setStatus(status,approveId,clarifyId,rejectedId){
	
	if(status=="Y" || status==""){
		$("#"+approveId).prop('checked',true);
		$("#"+clarifyId).prop('checked',false);
		$("#"+rejectedId).prop('checked',false);
	}else if(status=="C"){
		$("#"+clarifyId).prop('checked',true);
		$("#"+rejectedId).prop('checked',false);
		$("#"+approveId).prop('checked',false);
	}else if(status=="N"){
		$("#"+rejectedId).prop('checked',true);
		$("#"+clarifyId).prop('checked',false);
		$("#"+approveId).prop('checked',false);
	}
}