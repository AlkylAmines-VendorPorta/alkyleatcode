/**
 * Aman
 */

var gtpList=new Array();
$(document).ready(function(){
	var lengthMenu;
    if ($(window).width() < 480) {
        $('.mobileNav').show();
        $.fn.DataTable.ext.pager.numbers_length = 4;       
        lengthMenu = [ 1, 5, 7, 10, ],
        [ 1, 5, 7, 10, ]
    } else {        
        lengthMenu = [ 5, 10, ],
        [ 5, 10, ]
    }
    
	$('.addMatPopup').click(function(event){
		event.preventDefault();
		$('.commenSearchModal').modal('show');
		$(".itemTable").DataTable().destroy();
		$(".itemTable tbody").empty();
		populateMaterialGroupList(fetchList("getMaterialGroupList",null));
		/*populateSubGroupList(fetchList("getMaterialSubGroupList",null));*/
		$('.commenSearchTable').DataTable({
			"lengthMenu":lengthMenu
		});
	});
	
	
	
});

 function copyGtpFrom(data){
	 debugger;
	var fromMaterialId=data.matId;
	var toMaterialId=$('#materialFormId #materialId').val();
	if(fromMaterialId!='' && toMaterialId!=''){
		submitToURL('copyGtp/'+fromMaterialId+'/'+toMaterialId, 'copiedGtpResp');
	}else{
		Alert.warn('Select Material to Copy Gtp from !');
	}
}
 function gtpListResp(data){
	 debugger;
	 console.log(data);
	 $('.pagination').children().remove();
		$(".leftPaneData").html("");
		var leftPanelHtml="";
		var i=0;
		var firstRow=null;
		if(!$.isEmptyObject(data.objectMap.gtpList)){
					$.each(data.objectMap.gtpList,function(key,value){
						
							    var gtpId = value.gtpParameterId==null?'': value.gtpParameterId;
								var name = value.name==null?'': value.name;
								var description = value.description==null?'':value.description;
								var code  = value.value==null?'':value.value;
								
							
								gtpList["GTP_"+gtpId]=value;
								if(i==0){
									firstRow = value;
									leftPanelHtml = leftPanelHtml + ' <li class="active" onclick="showGtp('+gtpId+')" id="'+gtpId+'">';
								}else{
									leftPanelHtml = leftPanelHtml + ' <li onclick="showGtp('+gtpId+')" id="'+gtpId+'">';
								}
							
								leftPanelHtml = leftPanelHtml +' <a href="#Master" data-toggle="tab">'
							    +' <div class="col-md-12">'
							    +'  <label class="col-xs-6" id="'+gtpId+'_name">'+name+'</label>'
							    +' </div>'	
							    +' <div class="col-md-12">'
							    +'	<label class="col-xs-6" id="'+gtpId+'_description">'+description+'</label>'
								+' </div>'
							    +' </a>';
								i++;
					});
					$(".leftPaneData").html(leftPanelHtml);
					loadRightPaneGtpResp(firstRow);
					$('.leftPaneData').paginathing({perPage: 6});
					 $(".tabs-left li a label").text(function(index, currentText) {
						    return currentText.substr(0, 20);
						});
					
					 $(".tabs-left li a label").text(function(index, currentText) {
						    return currentText.substr(0, 20);
						});
			}else{
				Alert.warn('No Gtp Present for selected Material !');
			}
		
	}
	function loadRightPaneGtpResp(data){
		debugger;
		  if(!$.isEmptyObject(data)){
			  	var gtpId = data.gtpParameterId==null?'': data.gtpParameterId;
				var name = data.name==null?'': data.name;
				var description = data.description==null?'':data.description;
				var isCopied  = data.isCopied==null?'':data.isCopied;
				
				$('#gtpDetailFormId #gtpId').val(gtpId);
				$('#gtpDetailFormId #gtpName').html(name);
				$('#gtpDetailFormId #description').html(description);
				if(isCopied=="Y")
					$('#isCopied').prop('checked', true); 
				else
					$('#isCopied').prop('checked', false);
				
		  }else{
			   $('#gtpDetailFormId #gtpId').val();
				$('#gtpDetailFormId #gtpName').html('');
				$('#gtpDetailFormId #description').html('');
				$('#isCopied').prop('checked', false);
		  }
		
	}
	function showGtp(gtpId){
		loadRightPaneGtpResp( gtpList["GTP_"+gtpId]);
	}
	function copiedGtpResp(data){
		if(data.objectMap.result){
			Alert.info(data.objectMap.resultMessage);
		}else{
			Alert.warn(data.objectMap.resultMessage);
		}
	}
