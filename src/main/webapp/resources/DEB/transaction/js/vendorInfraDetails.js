var vendorArray=new Array();
$(document).ready( function() {
	onPageLoad();
	setPageContextByRole();
});
function setPageContextByRole(){
	var roleName=$('#roleName').val();
	if(roleName=='VENADM' || roleName=='INFADM'){
		$('.approvalDiv').hide();
	}else{
		$('.approvalDiv').show();
	}
}

function cancel(){
	restoreChanges();
	
}
function getVendorDetails(event,el){
	event.preventDefault();
	onPageLoad();
}
function onPageLoad(){
	
	var pageInfo=$("#pageInfo").val();
	if(pageInfo=="vendorDetails")
		{
		   submitToURL('getInfraVendor', 'onTabloadVendorResp');
		}else if(pageInfo=="vendorApproval"){
		   submitToURL('getInfraVendors', 'onTabloadVendorResp');
		}
	
}
function onTabloadVendorResp(data){
	
	if(data.objectMap.hasOwnProperty('partners')){
		loadVendorLeftPane(data.objectMap.partners);
	}
	var pageInfo=$("#pageInfo").val();
	if(pageInfo=="vendorApproval"){
		if(data.objectMap.hasOwnProperty('hasError')){
			if(data.objectMap.hasError){
				Alert.warn(data.objectMap.message);
			}
		}  
	}
	if($('.leftPaneData li').length==0){
		$("#infraItemTab").addClass("readonly");
	}else{
		$("#infraItemTab").removeClass("readonly");
	}
}
function loadVendorLeftPane(data){
	$('.pagination').children().remove();
	$(".leftPaneData").html("");
	var leftPanelHtml="";
	var i=0;
	var active=false;
	var firstRow=null;
	if(data==null){
		  return;
		}
	$.each(data,function(key,value){
		
		vendorArray["infraVendor"+value.bPartnerId]=value;
		if(i==0){
			firstRow = value;
			leftPanelHtml = leftPanelHtml + ' <li onclick="showVendorInfo('+value.bPartnerId+')" class="active">'
		}else{
			leftPanelHtml = leftPanelHtml + ' <li onclick="showVendorInfo('+value.bPartnerId+')">'
		}
		leftPanelHtml = leftPanelHtml +' <a href="#Master" data-toggle="tab">'
        +' <div class="col-md-12">'
        +'  <label class="col-xs-6 "> '+value.name+'</label>'
        +'	<label class="col-xs-6 ">'+value.crnNumber+'</label>'
        +' </div>'	
        +' <div class="col-md-12">'
        +'	<label class="col-xs-6">'+value.panNumber+'</label>'
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
	loadVendorRightPane(firstRow);
}
function showVendorInfo(id){
	
	var vendorData=vendorArray["infraVendor"+id];
	loadVendorRightPane(vendorData);
}
function loadVendorRightPane(data){
	
  if(!$.isEmptyObject(data)){
	    var bPartnerId=data.bPartnerId==null?'':data.bPartnerId;
	    var name=data.name==null?'':data.name;
		var gstNo=data.gstinNo==null?'':data.gstinNo;
		var crnNo=data.crnNumber==null?'':data.crnNumber;
		var panCardNo=data.panNumber==null?'':data.panNumber;
		var companyType=data.panNumber==null?'':data.panNumber;
		
		
		$(".bPartnerId").val(bPartnerId);
		$("#companyName").val(name);
		$("#gstNo").val(gstNo);
		$("#crnNo").val(crnNo);
		$("#panCardNo").val(panCardNo); 
		setHeaderValues("Name:" +name ," Company Type : "+companyType, "CRN: "+crnNo, "PAN No : "+panCardNo);
  }else{
	    $(".bPartnerId").val("");
	    $("#companyName").val('');
		$("#gstNo").val('');
		$("#crnNo").val('');
		$("#panCardNo").val('');  
		setHeaderValues("Name:"  ," Company Type : ", "CRN: ", "PAN No : ");
  }	
}
/*function fetchVendorList(pageNumber, pageSize, searchMode, searchValue){
	var response;
	$.ajax({
        type : "POST",
        contentType : "application/json",
        url: "loadVendor?pageNumber="+pageNumber+"&pageSize="+pageSize+'&searchMode='+searchMode+'&serachValue='+searchValue,
        dataType:"json",
        async:false,
        success: function (data) {
        	response = data;
        },
        error: function (e) {
			Alert.warn(e.message);
        }
    });
	return response;
}*/