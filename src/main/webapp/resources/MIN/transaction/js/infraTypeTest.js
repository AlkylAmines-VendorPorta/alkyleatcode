var typeTestArray=new Array();
$(document).ready(function(){
	$('#cancelTypeTestBtnId').click(function(event){
		
		event.preventDefault();
		var currentTestId = $('ul.leftPaneData').find('li.active').attr('id');
		if(currentTestId!=undefined){
			var typeTestData=typeTestArray["testTest"+currentTestId];
			loadTypeTestRightPane(typeTestData);
		}else{
			  $("#materialTypeTestDetails").val("");
			  $("#testTypeName").val("");
			  $("#issueDate").val("");
			  $("#letterNoId").val("");
			  $("#typeTestComment").val("");
			  setStatus("",'typeTestApprove','typeTestClarify','typeTestReject');
		}
	});
});
function onTabInfraTypeTestLoad(event){
	event.preventDefault();
	var bPartnerId=$('.bPartnerId').val();
	var infraItemId=$('.partnerInfraItemId').val();
	if(bPartnerId!='' && infraItemId!=''){
	 submitToURL('getInfraTypeTest?partnerId='+bPartnerId+'&infraItemId='+infraItemId, 'onTabInfraTypeTestLoadResp');
	}else{
		Alert.warn('Something went wrong');
		event.stopPropagation();
	}
}

function onTabInfraTypeTestLoadResp(data){
	
	console.log(data);
	if(data.objectMap.hasOwnProperty('typeTestList'))
		loadTypeTestLeftPane(data.objectMap.typeTestList);
}

function loadTypeTestLeftPane(data){
	$('.pagination').children().remove();
	$(".leftPaneData").html("");
	var leftPanelHtml="";
	var i=0;
	var active=false;
	var firstRow=null;
	if(data==null){
	      $("#materialTypeTestDetails").val("");
		  $("#testTypeName").val("");
		  $("#issueDate").val("");
		  $("#letterNoId").val("");
		  $("#typeTestComment").val("");
		  setStatus("",'typeTestApprove','typeTestClarify','typeTestReject');
		  return;
		}
	$.each(data,function(key,value){
		
		typeTestArray["testTest"+value.partnerItemTypeTestId]=value;
		if(i==0){
			firstRow = value;
			leftPanelHtml = leftPanelHtml + ' <li onclick="showTypeTestInfo('+value.partnerItemTypeTestId+')" id="'+value.partnerItemTypeTestId+'" class="active">'
		}else{
			leftPanelHtml = leftPanelHtml + ' <li onclick="showTypeTestInfo('+value.partnerItemTypeTestId+')" id="'+value.partnerItemTypeTestId+'">'
		}
		leftPanelHtml = leftPanelHtml +' <a href="#Master" data-toggle="tab">'
        +' <div class="col-md-12">'
        +'  <label class="col-xs-6 ">'+value.partnerInfraItem.material.name+' </label>'
        +'	<label class="col-xs-6 ">'+value.materialTypeTestDetails.docName+'</label>'
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
	loadTypeTestRightPane(firstRow);
}
function showTypeTestInfo(id){
	
	var typeTestData=typeTestArray["testTest"+id];
	loadTypeTestRightPane(typeTestData);
}
function loadTypeTestRightPane(data){
	
  if(!$.isEmptyObject(data)){
	  var typeTestId=data.partnerItemTypeTestId==null?'':data.partnerItemTypeTestId;
	  var docName=data.materialTypeTestDetails==null?'':data.materialTypeTestDetails.docName==null?'':data.materialTypeTestDetails.docName;
	  var issueDate=data.issueDate==null?'':formatDate(data.issueDate);
	  var letterNo=data.letterNo==null?'':data.letterNo;
	  var remark=data.remark==null?'':data.remark;
	  var status=data.status==null?'':data.status;
	  $("#materialTypeTestDetails").val(typeTestId);
	  $("#testTypeName").val(docName);
	  $("#issueDate").val(issueDate);
	  $("#letterNoId").val(letterNo);
	  /*$("#typeTestComment").val(remark);*/
	 /* setStatus(status,'typeTestApprove','typeTestClarify','typeTestReject');*/
	  showTypeTestFields(data);
  }else{
	  $("#materialTypeTestDetails").val("");
	  $("#testTypeName").val("");
	  $("#issueDate").val("");
	  $("#letterNoId").val("");
	  $("#typeTestComment").val("");
	  setStatus("",'typeTestApprove','typeTestClarify','typeTestReject');
	  
  }	
}
function showTypeTestFields(data){
	var pageInfo=$("#pageInfo").val();
    if(pageInfo=="vendorDetails")
	{
        $("#previousTestApprovedDiv").hide(); 
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
 		  $("#previousTestApprovedDiv").hide();
 		  $("#typeTestComment").val(eeComment);
 		  $("#prevTypeTestComment").val("");
 		  setStatus(isEEApproved,'typeTestApprove','typeTestClarify','typeTestReject');
 		  setStatus("",'prevTypeTestApprove','prevTypeTestClarify','prevTypeTestReject');
 	  }else if(role=="SCRENGR"){
 		  $("#previousTestApprovedDiv").show();
 		  $("#typeTestComment").val(seComment);
 		  $("#prevTypeTestComment").val(eeComment);
 		  setStatus(isSEApproved,'typeTestApprove','typeTestClarify','typeTestReject');
 		  setStatus(isEEApproved,'prevTypeTestApprove','prevTypeTestClarify','prevTypeTestReject');
 	  }else if(role=="CHFENGR"){
 		  $("#previousTestApprovedDiv").show();
 		  $("#typeTestComment").val(ceComment);
 		  $("#prevTypeTestComment").val(seComment);
 		  setStatus(isCEApproved,'typeTestApprove','typeTestClarify','typeTestReject');
 		  setStatus(isSEApproved,'prevTypeTestApprove','prevTypeTestClarify','prevTypeTestReject');
 	  }else if(role=="EXEDIR"){
 		  $("#previousTestApprovedDiv").show();
 		  $("#typeTestComment").val(edComment);
 		  $("#prevTypeTestComment").val(ceComment);
 		  setStatus(isEDApproved,'typeTestApprove','typeTestClarify','typeTestReject');
 		  setStatus(isCEApproved,'prevTypeTestApprove','prevTypeTestClarify','prevTypeTestReject');
 	  }else if(role=="DIR"){
 		  $("#previousTestApprovedDiv").show();
 		  $("#typeTestComment").val(dirComment);
 		  $("#prevTypeTestComment").val(edComment);
 		  setStatus(isDIRApproved,'typeTestApprove','typeTestClarify','typeTestReject');
 		  setStatus(isEDApproved,'prevTypeTestApprove','prevTypeTestClarify','prevTypeTestReject');
 	  }
	   
	}
}
function saveTypeTestResp(data){
	if(!data.response.hasError)
	{
	var currentInfraTypeTestId = $('ul.leftPaneData').find('li.active').attr('id');
	var leftPanelHtml = "";
	var status = true;
	var partnerItemTypeTestId = data.partnerItemTypeTestId;

	$('#materialTypeTestDetails').val(partnerItemTypeTestId);
	if (currentInfraTypeTestId == partnerItemTypeTestId) {
		$('#'+currentInfraTypeTestId).remove();
	}else {
		$('#'+currentInfraTypeTestId).removeClass('active');
	}
	
	leftPanelHtml = leftPanelHtml + ' <li onclick="showTypeTestInfo('+partnerItemTypeTestId+')" id="'+partnerItemTypeTestId+'" class="active">';
	leftPanelHtml = leftPanelHtml +' <a href="#Master" data-toggle="tab">'
    +' <div class="col-md-12">'
    +'  <label class="col-xs-6 "> '+$("#infraMaterialId").text()+'</label>'
    +'	<label class="col-xs-6 ">'+data.materialTypeTestDetails.docName+'</label>'
    +' </div>'	
    +' </a>'
    +' </li>';
	$(".leftPaneData").prepend(leftPanelHtml);
	Alert.info(data.response.message);
	typeTestArray["testTest"+partnerItemTypeTestId]=data;
	}else{
		Alert.warn(data.response.message);
	}

}