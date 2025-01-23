/**
 * Aman Sahu
 */
 
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
		
	});
});
function tradingItemDelResp(data){
	Alert.info(data.message);
	event.preventDefault();
	
 }
function getTradingItems(event,el)
{
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
	    checkForFilterByRole();
}
function onTradingItemTabClick(){
	 
	submitToURL("getPartner", 'onTradingItemLoad');	
}
function populateTradingItemTable(data)
{
	debugger;
	var newItem='';
	newItem+='<input type="text" id="" value="'+data.itemName+'" name="name">'
		+'<input type="text" id="" value="'+$("#partnerItemManufacturerId").val()+'" name="partnerItemManufacutrer.partnerItemManufacturerId">'
		+'<input type="text" id="" value="Y" name="isActive">'
	    +'<input type="text" id="" value="'+data.matId+'" name="material.materialId">';
	
	$('#partnerTradingItemForm').append(newItem);
	submitIt('partnerTradingItemForm','savePartnerTradingItem','tradingItemResp');
}
function tradingItemResp(data)
{
	 debugger;
	 $('.pagination').children().remove();
	 setChildLoadFlag(true);
	if(data.response.hasError==false)
		{
		var length=$(".tradingItemTable tbody tr").length;
		var itemList='';
			itemList +='<tr id="item_tr_'+data.partnerTradingItemId+'"> <td><strong>*</strong></td>'+
			'<td>'+data.name+'</td>'+
			'<td><button onclick="deleteTradingItem('+data.partnerTradingItemId+')" data-itemmanufacturereid="'+data.partnerItemManufacutrer.partnerItemManufacturerId+'">Delete</button></td>'+
			'</tr>';
	
			$(".tradingItemTable tbody").prepend(itemList);
			Alert.info(data.response.message,'','success');
			showSubmitFormOnManufacturerChanges();
			/*setHeaderValues("Company Name: "+companyName, "Company Registration No. : "+registrationNo, "Pan Number : "+panNo, "Company Type : "+companyType);*/
			setHeaderValues("Manufacturer Name: "+manufacturerName, "Email : "+emailId, "Mobile No.: "+mobNo, "Location : "+manufacturerCity);
		}
	else
		{
		 if (!$.isEmptyObject(data.response.errors)) {
				var msg = '';
				$.each(data.response.errors, function(key, value) {
					msg = msg + '\n' + value.errorMessage + '\n';

				});
				Alert.warn(msg);
			   } else {
				   Alert.warn("Record Not Saved",'','error');
			   }
		}
		
	$('.leftPaneData').paginathing();
	$('#partnerTradingItemForm').empty();
	}
function onTradingItemLoad(data){
	debugger;
		if(data.objectMap.hasOwnProperty('partnerTradingItem'))
			{
				if(!$.isEmptyObject(data.objectMap.partnerTradingItem)){
					$('.leftPaneData li').each(function() {
						var manufacturerId=$(this).attr('id');
						$(this).attr('onclick','showItemList('+manufacturerId+')');
					});
					
					loadPartnerTradingItemRigthPane(data.objectMap.partnerTradingItem);
				}
				else
					{
						$(".tradingItemTable tbody").empty();
					}
			}
		setActiveTabName("Items",$('.leftPaneData li').length);
		$("#srchItemForm #orgId").val("");
		$('#AdditemButton').data('callback','populateTradingItemTable');
		$('#searchItemList').attr('onclick',"return submitIt('srchItemForm','getItemList','populateItemListWithSingleSelect');");
		
		/*$('#saveTradingItemBtnId').hide();
		$('#cancelTradingItemBtnId').hide();*/
		
		
		
	}

function loadPartnerTradingItemRigthPane(data)
{
	debugger;
	$('.pagination').children().remove();
	setChildLoadFlag(true);
	$(".tradingItemTable tbody").empty();
	var itemList='';
	for(var i=0;i<data.length;i++)
		{
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
		}
	var remark=data.remark==null?'':data.remark;
	var isApproved=data.isApproved==null?'':data.isApproved;
	var eeComment=data.eeComment==null?'':data.eeComment;
	var isEEApproved=data.isEEApproved==null?'':data.isEEApproved;
	var ceComment=data.ceComment==null?'':data.ceComment;
	var isCEApproved=data.isCEApproved==null?'':data.isCEApproved;
	$("#itemFormId #remark").val(remark);
	/*changeCommentAndStatusByRole('itemFormId',isEEApproved,eeComment,isCEApproved,ceComment);*/
	/*setApprovedStatus('itemFormId',isApproved);*/
	/*setHeaderValues("Company Name: "+companyName, "Company Registration No. : "+registrationNo, "Pan Number : "+panNo, "Company Type : "+companyType);*/
	$('.leftPaneData').paginathing();
	setHeaderValues("Manufacturer Name: "+manufacturerName, "Email : "+emailId, "Mobile No.: "+mobNo, "Location : "+manufacturerCity);	
}
/*function loadTradingItemData(value,active){
	 
	
		 $("#partnerManufacturerForm #partnerManufacturerId").val(partnerItemManufacturerId);
	
}*/
function deleteTradingItem(id)
{
	 
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
function deleteTradingItemResp(data)
{
	event.preventDefault();
	$('.pagination').children().remove();
	if(data.hasError==false)
		{
	    	Alert.info(data.message,'','success');
	    	showSubmitFormOnManufacturerChanges();
		}
	else
		{
	    	Alert.warn(data.responseMsg,"","error");
		}
	$('.leftPaneData').paginathing();
	 return false;
	}
