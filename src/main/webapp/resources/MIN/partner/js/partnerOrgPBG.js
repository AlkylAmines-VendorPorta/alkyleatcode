$(document).ready(function(){
	$('#partnerOrgPBGForm #isNotApplicable').click(function(){
		
		var active='';
		if (this.checked) {
			active="Y";
			$('.showPBGField').find('input:text').val('');
			$('.showPBGField').find('select').val('');
		}else{
			active="N";
		}
		showPBGDetail(active);
	});
	
	$('#addPBGBtnId').click(function(event) {
		
		event.preventDefault();
		$('#partnerOrgPBGForm')[0].reset();
		$("#PBGId").val("");
		/*$('#savePBGBtnId').show();
		$('#cancelPBGBtnId').show();*/
		/*$('#PBGFormId').removeClass('readonly');*/
		$('#bankGauranteeCopy').val("");
		$("#partnerOrgPBGForm #a_bankGauranteeCopy").html('');
		
		$(".bankGauranteeCopy").attr('disabled',true);
		/*$('#partnerOrgPBGForm #isNotApplicable').trigger('click');
			debugger;*/

		$('#partnerOrgPBGForm #isNotApplicable').click(function(){

			var active='';
			if (this.checked) {
				active="Y";
				$('.showPBGField').find('input:text').val('');
				$('.showPBGField').find('select').val('');
			}else{
				active="N";
			}
			showPBGDetail(active);
		});
		
    });
	
	$('#editPBGBtnId').click(function(event) {
		event.preventDefault();
		/*$('#savePBGBtnId').show();
		$('#cancelPBGBtnId').show();*/
		/*$('#PBGFormId').removeClass('readonly');*/
		
		$('#partnerOrgPBGForm #isNotApplicable').click(function(){
			var active='';
			if (this.checked) {
				active="Y";
				$('.showPBGField').find('input:text').val('');
				$('.showPBGField').find('select').val('');
			}else{
				active="N";
			}
			showPBGDetail(active);
		});
		
    });
	$('#cancelPBGBtnId').click(function(event) {
		debugger;
		event.preventDefault();
		var activePBGId=$('.leftPaneData').find('li.active').attr('id');
		if(activePBGId!=undefined)
			{
			 showOrgPBGDetail(activePBGId);
			}
		else
			$('#partnerOrgPBGForm')[0].reset();
		
    });
});

function partnerPBGDelResp(data)
{
	
	$('.pagination').children().remove();
	Alert.info(data.message);
	var currentOrgPBGId=$('ul.leftPaneData').find('li.active').attr('id');
	$('#'+currentOrgPBGId).remove();
	$('#partnerOrgPBGForm')[0].reset();
	$("#partnerOrgPBGForm #a_bankGauranteeCopy").html('');
	event.preventDefault();
	$('.leftPaneData').paginathing();
	
	$(".tabs-left li a label").text(function(index, currentText) {
	    return currentText.substr(0, 20);
	});
}

function showPBGDetail(active)
{
	if(active=='Y')
		{
		  $('#isNotApplicable').val(active);
          $(".showPBGField").hide();
          $('#factoryName').attr('disabled','disabled');
          $('#pbgAmount').attr('disabled','disabled');
          $('#bankGuaranteeNo').attr('disabled','disabled');
          $('#issueDate').attr('disabled','disabled');
          $('#validityDate').attr('disabled','disabled');
          $('#bankFileId').attr('disabled','disabled');
		}else{
		   $('#isNotApplicable').val(active);
		   $(".showPBGField").show();
		   $('#factoryName').removeAttr('disabled','disabled');
		   $('#pbgAmount').removeAttr('disabled','disabled');
		   $('#bankGuaranteeNo').removeAttr('disabled','disabled');
		   $('#issueDate').removeAttr('disabled','disabled');
		   $('#validityDate').removeAttr('disabled','disabled');
		   $('#bankFileId').removeAttr('disabled','disabled');
		}
}

/*function formatDate(longDate){
	   var dt=new Date(longDate);
	   var dd=dt.getDate();
	   var MM=dt.getMonth()+1;
	   var yyyy=dt.getFullYear();
	   var HH= dt.getHours();
	   var mm= dt.getMinutes();
	   var ss= dt.getSeconds();
	   
	   return yyyy+'-'+MM+'-'+dd;
	   +' '+HH+':'+mm+':'+ss;
}*/

function partnerOrgPBGResp(data){
	$('.pagination').children().remove();
	if(data.response.hasError==false)
	{
		var pbgFlag=true;
		var leftPanelHtml='';
		var partnerOrgPBGId=data.partnerOrgPbgId;
		var currentOrgPBGId=$('ul.leftPaneData').find('li.active').attr('id');
		if(currentOrgPBGId==partnerOrgPBGId)
		{
			$('#'+currentOrgPBGId).remove();
		}
		else
		{
			$('#'+currentOrgPBGId).removeClass('active');
		}
		$("#partnerOrgPBGForm #PBGId").val(partnerOrgPBGId);
		leftPanelHtml=appendOrgPBGData(data,pbgFlag);
		$(".leftPaneData").prepend(leftPanelHtml);
		$("#lblbankGauranteeCopyName-"+partnerOrgPBGId).html($("#partnerOrgPBGForm #a_bankGauranteeCopy").html());
		pbgFlag=false;
		/*$('#PBGFormId').addClass('readonly');*/
		Alert.info(data.response.message,'','success');
	}	
else
	Alert.warn(data.response.message,'','error');
	$('leftPaneData').paginathing();
	
	 $(".tabs-left li a label").text(function(index, currentText) {
		    return currentText.substr(0, 20);
		});
}

function onPartnerOrgPBGTabClick(){
	submitToURL("getPartner", 'onPartnerOrgPBGTabLoad');	
}

function onPartnerOrgPBGTabLoad(data){
	console.log(data);
	
	if(data.objectMap.hasOwnProperty('orgs'))
        loadFactoryDetails(data.objectMap.orgs);
	if(data.objectMap.hasOwnProperty('partnerOrgPbgs'))
		loadPartnerOrgPBGLeftPane(data.objectMap.partnerOrgPbgs);
	
	setActiveTabName("Permanent Bank Gaurantee  Details",$('.leftPaneData li').length);
}


function loadPartnerOrgPBGLeftPane(data){
	$('.pagination').children().remove();
	$(".leftPaneData").html("");
	var leftPanelHtml="";
	var i=0;
	var pbgFlag=false;
	var firstRow=null;
	$.each(data,function(key,value){
		
		if(i==0){
			firstRow = value;
			pbgFlag=true;
		}
		leftPanelHtml=leftPanelHtml+appendOrgPBGData(value,pbgFlag);
		pbgFlag=false;
		 i++;
	});
	$(".leftPaneData").append(leftPanelHtml);
	$('.leftPaneData').paginathing();
	
	 $(".tabs-left li a label").text(function(index, currentText) {
		    return currentText.substr(0, 20);
		});
	loadPartnerOrgPBGRightPane(firstRow);
}

function appendOrgPBGData(value,pbgFlag)
{
	
	var leftPanelHtml='';
	var partnerOrgPbgId= value.partnerOrgPbgId==null?'':value.partnerOrgPbgId;
	 if(pbgFlag)
	 {
	    leftPanelHtml = leftPanelHtml + ' <li class="active" onclick="showOrgPBGDetail('+partnerOrgPbgId+')" id="'+partnerOrgPbgId+'">';
	 }else{
		leftPanelHtml = leftPanelHtml + ' <li onclick="showOrgPBGDetail('+partnerOrgPbgId+')" id="'+partnerOrgPbgId+'">';
	 }
	var bankGuaranteeNo = value.bankGuaranteeNo==null?'':value.bankGuaranteeNo;
	var pbgAmount = value.pbgAmount==null?'':value.pbgAmount;
	var issueDate = value.issueDate==null?'':formatDate(value.issueDate);
	var validityDate = value.validityDate==null?'':formatDate(value.validityDate);
	var isNotApplicable = value.isNotApplicable==null?'':value.isNotApplicable;
	var bankGauranteeCopyId=value.bankGauranteeCopy==null?'':value.bankGauranteeCopy.attachmentId==null?'':value.bankGauranteeCopy.attachmentId;
	var bankGauranteeCopyName=value.bankGauranteeCopy==null?'':value.bankGauranteeCopy.fileName==null?'':value.bankGauranteeCopy.fileName;
	var partnerOrgId=value.partnerOrg==null?'':value.partnerOrg.partnerOrgId==null?'':value.partnerOrg.partnerOrgId;
	var remark=value.remark==null?'':value.remark;
	var isApproved=value.isApproved==null?'':value.isApproved;
	
	leftPanelHtml = leftPanelHtml +' <a href="#Master" data-toggle="tab">'
    +' <div class="col-md-12">'
    +'  <label class="col-xs-6" id="lblBankGuaranteeNo-'+partnerOrgPbgId+'"> '+bankGuaranteeNo+'</label>'
    +'	<label class="col-xs-6 " id="lblPbgAmount-'+partnerOrgPbgId+'">'+pbgAmount+'</label>'
    +' </div>'	
    +' <div class="col-md-12">'
    +'	<label class="col-xs-6" id="lblIssueDate-'+partnerOrgPbgId+'">'+issueDate+'</label>'
	+'	<label class="col-xs-6" id="lblValidityDate-'+partnerOrgPbgId+'">'+validityDate+'</label>'
	+' </div>'
	+' <div class="col-md-12" style="display: none">'
	+'	<label class="col-xs-6" id="lblpartnerOrgId-'+partnerOrgPbgId+'">'+partnerOrgId+'</label>'
	+'	<label class="col-xs-6" id="lblpartnerOrgPbgId-'+partnerOrgPbgId+'">'+partnerOrgPbgId+'</label>'
    +'	<label class="col-xs-6" id="lblIsNotApplicable-'+partnerOrgPbgId+'">'+isNotApplicable+'</label>'
    +'	<label class="col-xs-6" id="lblbankGauranteeCopyId-'+partnerOrgPbgId+'">'+bankGauranteeCopyId+'</label>'
    +'	<label class="col-xs-6" id="lblbankGauranteeCopyName-'+partnerOrgPbgId+'">'+bankGauranteeCopyName+'</label>'
    +'	<label class="col-xs-6" id="remark-'+partnerOrgPbgId+'">'+remark+'</label>'
    +'	<label class="col-xs-6" id="isApproved-'+partnerOrgPbgId+'">'+isApproved+'</label>'
	+' </div>'
	+' </a>'
    +' </li>';
	return leftPanelHtml;
}
function showOrgPBGDetail(id)
{
	
	$("#partnerOrgPBGForm #factoryName").val($("#lblpartnerOrgId-"+id).html());
	$("#partnerOrgPBGForm #PBGId").val($("#lblpartnerOrgPbgId-"+id).html());
	$("#partnerOrgPBGForm #bankGuaranteeNo").val($("#lblBankGuaranteeNo-"+id).html());
	$("#partnerOrgPBGForm #pbgAmount").val($("#lblPbgAmount-"+id).html()); 
	$("#partnerOrgPBGForm #issueDate").val($("#lblIssueDate-"+id).html());
	$("#partnerOrgPBGForm #validityDate").val($("#lblValidityDate-"+id).html());
	$("#partnerOrgPBGForm #isNotApplicable").val($("#lblIsNotApplicable-"+id).html());
	$("#partnerOrgPBGForm #bankGauranteeCopy").val($("#lblbankGauranteeCopyId-"+id).html());
	$("#partnerOrgPBGForm #a_bankGauranteeCopy").html($("#lblbankGauranteeCopyName-"+id).html());
	$("#partnerOrgPBGForm #remark").val($("#remark-"+id).html());
	setApprovedStatus('partnerOrgPBGForm',$("#isApproved-"+id).html());
}

function loadPartnerOrgPBGRightPane(data){
	
	if(!$.isEmptyObject(data)){
	var bankGuaranteeNo = data.bankGuaranteeNo==null?'':data.bankGuaranteeNo;
	var pbgAmount = data.pbgAmount==null?'':data.pbgAmount;
	var issueDate = data.issueDate==null?'':formatDate(data.issueDate);
	var validityDate = data.validityDate==null?'':formatDate(data.validityDate);
    var isNotApplicable = data.isNotApplicable==null?'':data.isNotApplicable;
	var partnerOrgPbgId= data.partnerOrgPbgId==null?'':data.partnerOrgPbgId;
	var partnerOrgId=data.partnerOrg==null?'':data.partnerOrg.partnerOrgId==null?'':data.partnerOrg.partnerOrgId;
	var bankGauranteeCopyId=data.bankGauranteeCopy==null?'':data.bankGauranteeCopy.attachmentId==null?'':data.bankGauranteeCopy.attachmentId;
	var bankGauranteeCopyName=data.bankGauranteeCopy==null?'':data.bankGauranteeCopy.fileName==null?'':data.bankGauranteeCopy.fileName;
	var remark=data.remark==null?'':data.remark;
	var isApproved=data.isApproved==null?'':data.isApproved;
	
	$("#partnerOrgPBGForm #factoryName").val(partnerOrgId);
	$("#partnerOrgPBGForm #PBGId").val(partnerOrgPbgId);
	$("#partnerOrgPBGForm #bankGuaranteeNo").val(bankGuaranteeNo);
	$("#partnerOrgPBGForm #pbgAmount").val(pbgAmount); 
	$("#partnerOrgPBGForm #issueDate").val(issueDate);
	$("#partnerOrgPBGForm #validityDate").val(validityDate);
	$("#partnerOrgPBGForm #isNotApplicable").val(isNotApplicable);
	$("#partnerOrgPBGForm #bankGauranteeCopy").val(bankGauranteeCopyId);
	$("#partnerOrgPBGForm #remark").val(remark);
	setApprovedStatus('partnerOrgPBGForm',isApproved);
	
	var url=$("#a_bankGauranteeCopy").data('url');
	$("#a_bankGauranteeCopy").attr('href',url);
	var a_bankGauranteeCopy = $("#partnerOrgPBGForm #a_bankGauranteeCopy").prop('href')+'/'+bankGauranteeCopyId;
    $("#partnerOrgPBGForm #a_bankGauranteeCopy").prop('href', a_bankGauranteeCopy);
	$("#partnerOrgPBGForm #a_bankGauranteeCopy").html(bankGauranteeCopyName);
	
	showPBGFileDelBtn(bankGauranteeCopyId,'bankGauranteeCopy');
	}
}


function loadFactoryDetails(data){

	
		$("#partnerOrgPBGForm #factoryName").html("");
		var options = "<option value=''>Select Item</option>";
		$.each(data,function(key,value){
			options +='<option value="'+value.partnerOrgId+'">'+value.name +'</option>'
			
		});

		$("#partnerOrgPBGForm #factoryName").append(options);
}

function showPBGFileDelBtn(fileId,fieldClass)
{
	debugger;
  if(fileId!='' ||fileId!=null )
	  {
	    $("."+fieldClass).attr('disabled',false);
	  }else{
		  $("."+fieldClass).attr('disabled',true);
	  }
 }
function pbgAttachmentDeleteResp(data)
{
	if(!data.hasError)
    {		
       $('#bankFileId').val('');
	   $('#bankGauranteeCopy').val('');
	   $('#a_bankGauranteeCopy').html('');
	   $('.bankGauranteeCopy').attr('disabled',true);
	   Alert.info(data.message);
    }else{
    	Alert.warn(data.message);
    }
}