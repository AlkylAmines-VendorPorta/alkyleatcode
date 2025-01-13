/**
 * Aman Sahu
 */
var tradingItemArray=new Array();
$(document).ready(function(){
	 var lengthMenu='';

	    if ($(window).width() < 480) {
	        $('.mobileNav').show();
	        $.fn.DataTable.ext.pager.numbers_length = 4;       
	        lengthMenu = [ 1, 5, 7, 10, ],
	        [ 1, 5, 7, 10, ]
	    } else {        
	        lengthMenu = [ 5, 10, ],
	        [ 5, 10, ]
	    }
	
	$('#deleteTradingItemBtnId').click(function(event) {
		event.preventDefault();
		deleteTradingItem();
    });
	
	$('#cancelTradingItemBtnId').click(function(event) {
		event.preventDefault();
		editMode=false;
		var tradingData=tradingItemArray["tradingItem"];
		loadPartnerTradingItemRigthPane(tradingData);
	});
});
function tradingItemDelResp(data){
	if(!isEmpty(data)){
		Alert.info(data.message);
		event.preventDefault();
	}
 }

function getTradingItems(event,el){
	event.preventDefault();	
		if(!editMode && !requiredFileDeleted){
			cacheLi();
			setCurrentTab(el);
			$("#srchItemForm #orgId").val("");
			$('#AdditemButton').data('callback','populateTradingItemTable');
			$('#searchItemList').attr('onclick',"return submitIt('srchItemForm','getItemList','populateItemListWithSingleSelect');");
			if(getChangedFlag()){
		   		submitWithParam('getPartnerTradingItem','partnerItemManufacturerId','onTradingItemLoad');
		   		setChangedFlag(false);
			}else{
				getCacheLi();
			}
			setActiveTabName("Items",$('.leftPaneData li').length);
			setHeaderValues("Manufacturer Name: "+manufacturerName, "Email : "+emailId, "Mobile No.: "+mobNo, "Location : "+manufacturerCity);
		}else{
			event.stopPropagation();
	        Alert.warn("Please Save Changed Data Of "+activeTabName+" Tab");
		}
		$("#filterBtnId").addClass("readonly");
		showApprovalButton();
	    checkForFilterByRole();
}
function showApprovalButton(){
	var partnerData =  $("#partnerData").val();
	if(partnerData=='partnerRegistration'){
		$(".tradingItemsField").css('display','none');
	}else{
		$(".tradingItemsField").css('display','inline-block');
	}
}
function onTradingItemTabClick(){
	 
	submitToURL("getPartner", 'onTradingItemLoad');	
}
function populateTradingItemTable(data){
	if(!isEmpty(data)){
		var newItem='';
		newItem+='<input type="text" id="" value="'+data.itemName+'" name="name">'
			+'<input type="text" id="" value="'+$("#partnerItemManufacturerId").val()+'" name="partnerItemManufacutrer.partnerItemManufacturerId">'
			+'<input type="text" id="" value="Y" name="isActive">'
		    +'<input type="text" id="" value="'+data.matId+'" name="material.materialId">';
		
		$('#partnerTradingItemForm').append(newItem);
		submitIt('partnerTradingItemForm','savePartnerTradingItem','tradingItemResp');
	}
	
}
function tradingItemResp(data){
	 
	 $('.pagination').children().remove();
	 setChildLoadFlag(true);
	 if(!isEmpty(data) && !isEmpty(data.response)){
			if(data.response.hasError==false){
				var length=$(".tradingItemTable tbody tr").length;
				var itemList='';
					itemList +='<tr id="item_tr_'+data.partnerTradingItemId+'"> <td><strong>*</strong></td>'+
					'<td>'+data.name+'</td>'+
					'<td><button onclick="deleteTradingItem('+data.partnerTradingItemId+')" data-itemmanufacturereid="'+data.partnerItemManufacutrer.partnerItemManufacturerId+'">Delete</button></td>'+
					'</tr>';
			
					$(".tradingItemTable tbody").prepend(itemList);
					Alert.info(data.response.message);
					showSubmitFormOnManufacturerChanges();
					$("#partnerTradingItemForm  .manufacturerCEApproveDiv").hide();
					$("#partnerTradingItemForm  .manufacturerEEApproveDiv").hide();
					
					/*setHeaderValues("Company Name: "+companyName, "Company Registration No. : "+registrationNo, "Pan Number : "+panNo, "Company Type : "+companyType);*/
					setHeaderValues("Manufacturer Name: "+manufacturerName, "Email : "+emailId, "Mobile No.: "+mobNo, "Location : "+manufacturerCity);
				}
			else
				{
				 if (!$.isEmptyObject(data.response.errors)) {
						var msg = '';
						$.each(data.response.errors, function(key, value) {
							if(!isEmpty(value)){
								msg = msg + '\n' + value.errorMessage + '\n';
							}
						});
						Alert.warn(msg);
					   } else {
						   Alert.warn("Record Not Saved");
					   }
				}
				 
	 }

	$('.leftPaneData').paginathing();
	$('#partnerTradingItemForm').empty();
	}
function onTradingItemLoad(data){
	if(!isEmpty(data) && !isEmpty(data.objectMap)){
		if(data.objectMap.hasOwnProperty('partnerTradingItem'))
		{
			if(!$.isEmptyObject(data.objectMap.partnerTradingItem)){
				$('.leftPaneData li').each(function() {
					var manufacturerId=$(this).attr('id');
					$(this).attr('onclick','showItemList('+manufacturerId+')');
				});
				
				loadPartnerTradingItemRigthPane(data.objectMap.partnerTradingItem);
				tradingItemArray["tradingItem"]=data.objectMap.partnerTradingItem;
			}
			else
				{
					$(".tradingItemTable tbody").empty();
					$('.leftPaneData').html("");
					$('.pagination').children().remove();
				}
		}
	setActiveTabName("Items",$('.leftPaneData li').length);
	$("#srchItemForm #orgId").val("");
	$('#AdditemButton').data('callback','populateTradingItemTable');
	$('#searchItemList').attr('onclick',"return submitIt('srchItemForm','getItemList','populateItemListWithSingleSelect');");
	
	}
		/*$('#saveTradingItemBtnId').hide();
		$('#cancelTradingItemBtnId').hide();*/
	}

function loadPartnerTradingItemRigthPane(data){
	
	$('.leftPaneData').html("");
	$('.pagination').children().remove();
	setChildLoadFlag(true);
	$(".tradingItemTable tbody").empty();
	var itemList='';
	if(!isEmpty(data)){
		for(var i=0;i<data.length;i++){
			if(!isEmpty(data[i])){
				var length=$(".tradingItemTable tbody tr").length;
				itemList +='<tr id="item_tr_'+data[i].partnerTradingItemId+'"> <td><strong>*</strong></td>'
					+'<td>'+data[i].name+'</td>'
					+'<td><button onclick="deleteTradingItem('+data[i].partnerTradingItemId+')">Delete</button></td>'
					+'</tr>';
					$(".tradingItemTable tbody").append(itemList);
						$('table').each(function(){		
						var text = []
						$(this).find('thead tr th').each(function(){
							text.push($(this).text())

							for (var i = 0; i < text.length; i++) {
								$(this).parents('table').find('tbody tr td:nth-of-type(' + (i + 1) +')').attr('data-th', text[i])
							}	
						});		
					});
					var itemList='';
					var eeComment=data[i].partnerItemManufacutrer==null?'':data[i].partnerItemManufacutrer.itemsEEComment==null?'':data[i].partnerItemManufacutrer.itemsEEComment;
					var isEEApproved=data[i].partnerItemManufacutrer==null?'':data[i].partnerItemManufacutrer.isItemsEEApproved==null?'':data[i].partnerItemManufacutrer.isItemsEEApproved;
					var ceComment=data[i].partnerItemManufacutrer==null?'':data[i].partnerItemManufacutrer.itemsCEComment==null?'':data[i].partnerItemManufacutrer.itemsCEComment;
					var isCEApproved=data[i].partnerItemManufacutrer==null?'':data[i].partnerItemManufacutrer.isItemsCEApproved==null?'':data[i].partnerItemManufacutrer.isItemsCEApproved;
					
					changeManufacturerCommentAndStatusByRole('itemFormId',isEEApproved,eeComment,isCEApproved,ceComment);
			}
		}
	}
	
	/*var remark=data.remark==null?'':data.remark;*/
	
	/*setHeaderValues("Company Name: "+companyName, "Company Registration No. : "+registrationNo, "Pan Number : "+panNo, "Company Type : "+companyType);*/
	$('.leftPaneData').paginathing();
	setHeaderValues("Manufacturer Name: "+manufacturerName, "Email : "+emailId, "Mobile No.: "+mobNo, "Location : "+manufacturerCity);	
}
/*function loadTradingItemData(value,active){
	 
	
		 $("#partnerManufacturerForm #partnerManufacturerId").val(partnerItemManufacturerId);
	
}*/
function deleteTradingItem(id){
	event.preventDefault();
	$('#currentItemId').val(id);
	input_box = confirm("Do you really want to delete this Item?");
	 if (input_box == true) {
		submitWithParam('deletePartnerTradingItem','currentItemId','deleteTradingItemResp');
		$("#item_tr_"+id).remove();
	 }
	 
	 return false;
	}

function showItemList(id){
	submitToURL('getPartnerTradingItem/'+id,'onTradingItemLoad');
}
function deleteTradingItemResp(data){
	event.preventDefault();
	if(!isEmpty(data)){
		$('.pagination').children().remove();
		if(data.hasError==false)
		{
	    	Alert.info(data.message);
	    	showSubmitFormOnManufacturerChanges();
		}else{
	    	Alert.warn(data.responseMsg);
		}
	$('.leftPaneData').paginathing();
	 return false;
	}
	
}

function itemsApprovalResp(data){
	if(!isEmpty(data)){
		if(data.hasError){
			Alert.warn(data.message);
		}else{
			Alert.info(data.message);
		}
	}
}