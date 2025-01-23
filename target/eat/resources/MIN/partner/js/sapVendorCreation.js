var partnerArray = new Array();
$(document).ready(function(){
	submitToURL("getApprovedVendorsList", 'loadVendorsByStatusResp');
});

function loadVendorsByStatusResp(data){
	loadLeftPane(data.objectMap.partners);
}

function loadLeftPane(data){
	
	$(".leftPaneData").html("");
	if(data.length==0){
		  $(".leftPaneData").html("No Records Found");
		  return;
	}
	var leftPanelHtml="";
	var i=0;
	var firstRow=null;
	$.each(data,function(key,value){
		partnerArray["partner"+value.bPartnerId]=value;
		if(i==0){
			firstRow = value;
			leftPanelHtml = leftPanelHtml + ' <li onclick="showPartnerDetails('+value.bPartnerId+')" class="active">'
		}else{
			leftPanelHtml = leftPanelHtml + ' <li onclick="showPartnerDetails('+value.bPartnerId+')">'
		}
		leftPanelHtml = leftPanelHtml +' <a href="#Master" data-toggle="tab">'
        +' <div class="col-md-12">'
        +'  <label class="col-xs-6"> '+value.name+'</label>'
        +'	<label class="col-xs-6 ">'+value.crnNumber+'</label>'
        +' </div>'	
        +' <div class="col-md-12">'
        +'	<label class="col-xs-6">'+value.panNumber+'</label>';
        if(value.gstinNo !=null){
        	leftPanelHtml = leftPanelHtml	+'	<label class="col-xs-6">'+value.gstinNo+'</label>';
		}
	
		leftPanelHtml = leftPanelHtml+' </div>'
        +' </a>'
        +' </li>';
		i++;
	});
	$(".leftPaneData").html(leftPanelHtml);
	$('.leftPaneData').paginathing();
	
	loadRightPane(firstRow);
	 $(".tabs-left li a label").text(function(index, currentText) {
		    return currentText.substr(0, 20);
		});
	 
}

function showPartnerDetails(id) {
	var partnerData=partnerArray["partner"+id];
	loadRightPane(partnerData);
}
function loadRightPane(data){
	$("#partnerId").val(data.bPartnerId);
	$("#vendorName").text(data.name);
	$("#vendorCode").text(data.vendorSapCode);
	$('.selectBx').val(0);
	if(data.vendorSapCode == null || data.vendorSapCode == ''){
		$('.createCodeBtn').show();
		$('select').attr('disabled', false);
	}else{
		$('.createCodeBtn').hide();
		$('select').attr('disabled', true);
	}
}
function response(data){
	if(data.response.hasError){
		alert(data.response.message);
	}else{
		
		partnerArray["partner"+data.objectMap.partnerId].vendorSapCode = data.objectMap.vendorCode ;
		alert(data.response.message);
		$("#vendorCode").text(data.objectMap.vendorCode);
		$('.createCodeBtn').hide();
		$('select').attr('disabled', true);
	}
}