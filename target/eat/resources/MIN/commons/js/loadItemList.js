/**
 * 
 */
/*$("#my-language").multiselect(
      {
        title: "Select Language"
        //maxSelectionAllowed: 5
      });
  $(".selectall").change(function () {
	    $("input:checkbox.selectitemchecbox").prop('checked', $(this).prop("checked"));
	});*/
$(document).ready(function(){
	/*var lengthMenu;
	
    if ($(window).width() < 480) {
        $('.mobileNav').show();
        $.fn.DataTable.ext.pager.numbers_length = 4;       
        lengthMenu = [ 1, 5, 7, 10, ],
        [ 1, 5, 7, 10, ]
    } else {        
        lengthMenu = [ 7, ],
        [ 7, ]
    }
    */
	/*$('#AdditemButton').click(function(){
		debugger
		if($(this).attr('callback')!="")
			 { 
			    var callback=$(this).attr('callback');
			    window[callback]();
			 }else if($(this).attr('dropdownid')!=""){
				 
				 getSelectedItem($(this).attr('dropdownid'));
			 }
		 
	 });*/
	
	$('.itemTable').DataTable({
		"lengthMenu": [ 5, 10, 20, 75, 100 ],
		"ordering": false
		 
	});
});
function getSelectedItem(fieldId){
	 debugger;
	 var map = {}; 
	 var itemId='';
	 var itemName='';
	 $.each($("input.checkedBox:checked").closest("td").siblings("td:nth-child(3)"),
	            function () {
		            debugger;
		            itemId=$(this).attr('id');
		            itemName=$(this).text();
	    	    	map[itemId]=itemName;
	            });
	 appendItem(map,fieldId);
}
function appendItem(data,fieldId)
{
 debugger;
	$("#"+fieldId).html("");//bisLicenseItem
	var options='';
	$.each(data,function(index,val){
		options +='<option value="'+index+'">'+val +'</option>'
		
	});
	$("#"+fieldId).append(options);
}



$('#closeItemBtn').click(function(event){
	event.preventDefault();
	$(".itemTable tbody").empty();
	$('#srchItemForm')[0].reset();
	$('#srchItemForm').find('input,select').val('');
	$("#atleastOneFieldMsg").hide();
  });
  $('#AdditemButton').click(function(event){
	  event.preventDefault();
	  var callback=$('#AdditemButton').data('callback');
		    var bunchdata = {}; 
		    bunchdata.map = {};
				 var materialId='';
				 var selectedCount=0;
			     $.each($("input.selectitemradio:checked").closest("td").siblings("td:nth-child(3)"),
			            function () {
					    	 bunchdata.uom=$(this).data('uomid');
					    	 bunchdata.uomName=$(this).data('uomname');
					    	 bunchdata.itemCode=$(this).text();
					    	 bunchdata.hsn=$(this).data('hsn');
					    	 bunchdata.itemName=$(this).data('itemname');
					    	 bunchdata.matDescription=$(this).data('matdescription');
					    	 bunchdata.matId=$(this).data('materialid');
			    	    	 materialId=$(this).data('materialid');
			    	    	 bunchdata.map [materialId]=$(this).data('itemname');
			    	    	 selectedCount++;
			            });
			     if(selectedCount>0 && callback.trim()!=""){
			    	 		window[callback](bunchdata);
			    	 }
			     else
			    	 Alert.warn("Item not Added");
			    
				$(".itemTable tbody").empty();
				$('#srchItemForm')[0].reset();
  });
  
function populateMatSubGroupChange(el){
	var matGroupId=$(el).find('option:selected').val();
	populateSubGroupList(fetchList("getSubGroupByGroup",matGroupId));
}

function populateItemListWithSingleSelect(data){
	$(".itemTable").DataTable().destroy();
	$(".itemTable tbody").empty();
	if(!$.isEmptyObject(data)){
		var itemList='';
		for(var i=0;i<data.length;i++){
			var materialId=data[i].materialId==null?'':data[i].materialId;
			var hsnCode= data[i].hsnCode==null?'':data[i].hsnCode.code==null?'':data[i].hsnCode.code;
			var itemCode = data[i].itemCode==null?'':data[i].itemCode;
			var name= data[i].name==null?'':data[i].name;
			var uomId=data[i].uom==null?'':data[i].uom.uomId==null?'':data[i].uom.uomId;
			var uomName=data[i].uom==null?'':data[i].uom.name==null?'':data[i].uom.name;
			var description=data[i].description==null?'':data[i].description;
			var materialGroupName=data[i].materialGroup==null?'':data[i].materialGroup.name==null?'':data[i].materialGroup.name;
			var materialSubGroupName=data[i].materialSubGroup==null?'':data[i].materialSubGroup.name==null?'':data[i].materialSubGroup.name;
			
			itemList +='<tr> <td><input id="'+materialId+'" class="selectitemradio" type="radio" value="'+materialId+'" name="materialRadio" />'+
			'<td>'+hsnCode+'</td>'+
			'<td data-itemCode="'+itemCode+'" data-hsn="'+hsnCode+'" data-itemname="'+name+'" data-uomid="'+uomId+'" data-uomname="'+uomName+'" data-matdescription="'+description+'" data-materialid="'+materialId+'">'+itemCode+'</td>'+
			'<td>'+name+'</td>'+
			'<td>'+description+'</td>'+
			'<td>'+materialGroupName+'</td>'+
			'<td>'+materialSubGroupName+'</td>'+
			'<td>'+uomName+'</td>'+
			'</tr>';	
			/*itemList +='<tr> <td><input id="'+data[i].materialId+'" class="selectitemradio" type="radio" value="'+data[i].materialId+'" name="materialRadio" />'+
				'<td>'+data[i].hsnCode.code+'</td>'+
				'<td data-itemCode="'+data[i].itemCode+'" data-hsn="'+data[i].hsnCode.code+'" data-itemname="'+data[i].name+'" data-uomid="'+data[i].uom.uomId+'" data-uomname="'+data[i].uom.name+'" data-matdescription="'+data[i].description+'" data-materialid="'+data[i].materialId+'">'+data[i].itemCode+'</td>'+
				'<td>'+data[i].name+'</td>'+
				'<td>'+data[i].materialGroup.name+'</td>'+
				'<td>'+data[i].materialSubGroup.name+'</td>'+
				'<td>'+data[i].uom.name+'</td>'+
				'</tr>';*/
			}
		$(".itemTable tbody").append(itemList);
		$(".itemTable").DataTable({
			"lengthMenu": [ 5, 10, 75, 100 ],
			"scrollX": true,
			"ordering": false
		});
		
		/*$(".commenSearchTable").DataTable();
		$(".itemTable").DataTable();*/	
		}
	}

function populateItemListWithMultiSelect(data){
	$(".itemTable tbody").empty();
	if(!$.isEmptyObject(data)){
		var itemList='';
		for(var i=0;i<data.length;i++)
			{
				
			}
		$(".itemTable tbody").append(itemList);
		}
	}
