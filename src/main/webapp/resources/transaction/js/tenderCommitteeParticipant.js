var tenderCommitteeParticipantArray= new Array();
var participantUserArray=new Array();
$(document).ready(function(){
	
	$('#addParticipantBtnId').click(function(event) {
		event.preventDefault();
		$('#committeeParticipantForm')[0].reset();
		$("#committeeParticipantId").val("");
		$(".tenderCommitteeField").removeClass("readonly");
		$(".tenderCommitteeBtn").removeAttr("disabled",'disabled');
		});
	$('#cancelParticipantBtnId').click(function(event) {
		event.preventDefault();
		var activeParticipantId=$('.leftPaneData').find('li.active').attr('id');
		if(activeParticipantId!=undefined)
			{
			  showTenderCommitteeParticipant(activeParticipantId);
			}
		else
			$('#committeeParticipantForm')[0].reset();
	});
	$('#delParticipantBtnId').click(function(event) {
		event.preventDefault();
		var participantId=$('#committeeParticipantId').val();
		if(participantId!=''){
			submitToURL('deleteCommitteeParticipant?committeeParticipantId='+participantId,'tenderCommitteeParticipantDelResp');
		}else{
			Alert.warn('Select participant !');
		}
		
		
		});

	$("#committeeParticipantForm #participantId").on('change',function(){
		var selectedParticipantId=$(this).val();
		var participant=participantUserArray['participantUser'+selectedParticipantId];
		if(!isEmpty(participant)){
			var participantDetails=participant.userDetails;
			if(!isEmpty(participantDetails)){
				var participantDesignation=participantDetails.designation;
				if(!isEmpty(participantDesignation)){
					$('#committeeParticipantForm #designationId').val(getValue(participantDesignation.designationId));
				}
			}
		}
	});
	
});
function tenderCommitteeParticipantTabResp(data)
{

	if(data.objectMap.hasOwnProperty('user'))
	{
		loadPartipantUsers(data.objectMap.user);
    }
	if(data.objectMap.hasOwnProperty('designations'))
	{
		loadParticipantDesignation(data.objectMap.designations);
	}
	if(data.objectMap.hasOwnProperty('commiteeParticipants'))
	{
		loadTenderCommitteeParticipantLeftPane(data.objectMap.commiteeParticipants);
    }
  
}
function loadPartipantUsers(data)
{
	
	$("#committeeParticipantForm #participantId").html("");
	var options = '<option value=""></option>';
	$.each(data,function(key,value){
		options +='<option value="'+value.userId+'">'+value.userDetails.firstName+" "+value.userDetails.lastName+'</option>'
		participantUserArray['participantUser'+value.userId]=value;
	});

	$("#committeeParticipantForm #participantId").append(options);
	
}
function loadParticipantDesignation(data)
{
	
	$("#committeeParticipantForm #designationId").html("");
	var options = '<option value=""></option>';
	$.each(data,function(key,value){
		options +='<option value="'+value.designationId+'">'+value.name+'</option>'
		
	});

	$("#committeeParticipantForm #designationId").append(options);
}
function loadTenderCommitteeParticipantLeftPane(data)
{
	debugger;
	$('.pagination').children().remove();
	$(".leftPaneData").html("");
	if(data.length==0)
	{
		$('#committeeParticipantForm')[0].reset();
		$("#committeeParticipantId").val("");
	    return;
	}
	
	var leftPanelHtml="";
	var i=0;
	var active=false;
	var firstRow=null;
	$.each(data,function(key,value){
		var committeeParticipantId= value.committeeParticipantId==null?'':value.committeeParticipantId;
		tenderCommitteeParticipantArray["committeeParticipant"+committeeParticipantId]=value;
		if(i==0){
			firstRow = value;
			active=true;
		}
		leftPanelHtml=leftPanelHtml+appendTenderCommitteeParticipantData(value,active);
		active=false;
		i++;
		
	});
	$(".leftPaneData").append(leftPanelHtml);
	$('.leftPaneData').paginathing();
	/**/
	loadTenderCommitteeParticipantRightPane(firstRow);
}
function appendTenderCommitteeParticipantData(data,active)
{
	 debugger;
	 $('.pagination').children().remove();
	 var leftPanelHtml='';
	 var committeeParticipantId=data.committeeParticipantId==null?'':data.committeeParticipantId;
	 var designationId=data.designation==null?'':data.designation.designationId==null?'':data.designation.designationId;
	 var designationName=data.designation==null?'':data.designation.name==null?'':data.designation.name;
	 var firstName=data.user==null?'':data.user.userDetails==null?'':data.user.userDetails.firstName==null?'':data.user.userDetails.firstName;
     var lastName=data.user==null?'':data.user.userDetails==null?'':data.user.userDetails.lastName==null?'':data.user.userDetails.lastName;
 	 if(active)
		 {
		    leftPanelHtml = leftPanelHtml + ' <li class="active" onclick="showTenderCommitteeParticipant('+committeeParticipantId+')" id="'+committeeParticipantId+'">';
		 }else{
			leftPanelHtml = leftPanelHtml + ' <li onclick="showTenderCommitteeParticipant('+committeeParticipantId+')" id="'+committeeParticipantId+'">';
		 }
	 leftPanelHtml = leftPanelHtml +' <a href="#Master" data-toggle="tab">'
	    +' <div class="col-md-12">'
	    +'  <label class="col-xs-6" id="userLabel'+committeeParticipantId+'"> '+firstName+lastName+'</label>'
	    +'	<label class="col-xs-6 " id="designationLabel'+committeeParticipantId+'">'+designationName+'</label>'
	    +' </div>'
	    +' </a>'
	    +' </li>';
	 $('.leftPaneData').paginathing();
		
	return leftPanelHtml;
	
	
}
function loadTenderCommitteeParticipantRightPane(data)
{
	debugger;
	if(data==null)
		{
		 return;
		}
	
	 var committeeParticipantId=data.committeeParticipantId==null?'':data.committeeParticipantId;
	 var tenderCommitteeId=data.tenderCommittee==null?'':data.tenderCommittee.tenderCommitteeId==null?'':data.tenderCommittee.tenderCommitteeId;
	 var designationId=data.designation==null?'':data.designation.designationId==null?'':data.designation.designationId;
	 var designationName=data.designation==null?'':data.designation.name==null?'':data.designation.name;
	 var userId=data.user==null?'':data.user.userId==null?'':data.user.userId;
	 var userName=data.chairPerson==null?'':data.chairPerson.name==null?'':data.chairPerson.name;
	
	$("#committeeParticipantForm #committeeParticipantId").val(committeeParticipantId);
	$("#committeeParticipantForm #tenderCommitteeId").val(tenderCommitteeId);
	$("#committeeParticipantForm #participantId").val(userId);
	$("#committeeParticipantForm #designationId").val(designationId);
	$("#committeeParticipantForm  select").removeClass("errorinput");
	var documentType=$(".documentType").val();
	/*setHeaderValues(documentType+"No.: " + tahdrCodes, documentType+"Title : " + title, "Department : " + department, "Status : " + status);*/
	setHeaderValues(documentType+" Name:" + tahdrName, documentType+" version : " + tahdrVersionNumber, "Chairperson : " + chairPersionName, "");
	
	
	
	
	//setHeaderValues("Tender : "+tahdrName, "Tender Version : "+tahdrVersionNumber, "Chairperson : "+chairPersionName,"");
}
function showTenderCommitteeParticipant(id)
{
	var data=tenderCommitteeParticipantArray["committeeParticipant"+id];
	loadTenderCommitteeParticipantRightPane(data);

}

function tenderCommitteeParticipantDelResp(data)
{
	event.preventDefault();

	if(data.hasError==false)
	{
	/*swal(data.message);*/
	var currentcommitteeParticipantId=$('ul.leftPaneData').find('li.active').attr('id');
	$('#'+currentcommitteeParticipantId).remove();
	$('#committeeParticipantForm')[0].reset();
	$("#committeeParticipantId").val("");
	/*setActiveTabName("Factory Essential Details",$('.leftPaneData li').length);
	*/Alert.info(data.message);
	}
	else
		{
		  Alert.warn(data.message,"","error");
		}

}
function committeeParticipantResp(data){
	debugger;
	if(!$.isEmptyObject(data.objectMap.participantDto)){
		if(data.objectMap.result){
			data=data.objectMap.participantDto;
			if(!data.response.hasError){	
				var currentCommitteeParticipantId=$('ul.leftPaneData').find('li.active').attr('id');
				var leftPanelHtml="";
				var status=true;
				var committeeParticipantId=data.committeeParticipantId;
				$('#committeeParticipantForm #committeeParticipantId').val(committeeParticipantId);
				if(currentCommitteeParticipantId==committeeParticipantId)
				{
					$('#'+currentCommitteeParticipantId).remove();
				}
				else
				{
					$('#'+currentCommitteeParticipantId).removeClass('active');
				}	
				leftPanelHtml=appendTenderCommitteeParticipantData(data,status);
				$(".leftPaneData").prepend(leftPanelHtml);
				getDropDwonOfParticipantValueForLabel(committeeParticipantId);
				tenderCommitteeParticipantArray["committeeParticipant"+committeeParticipantId]=data;
				status=false;
				$("#committeeParticipantForm  select").removeClass("errorinput");
				Alert.info(data.response.message);
				}
				else{
				      Alert.warn(data.response.message);
				}
		}else{
			Alert.warn(data.objectMap.message);
		}
	}else{
		Alert.warn('Participant not Added !');
	}
	
	
}
function getDropDwonOfParticipantValueForLabel(id)
{

	$("#userLabel"+id).html($("#committeeParticipantForm #participantId option:selected").text());
	$("#designationLabel"+id).html($("#committeeParticipantForm #designationId option:selected").text());
}
	
