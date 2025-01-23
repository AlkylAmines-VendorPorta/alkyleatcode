
$(document).ready(function(){ 
	
	renderList();
	/*$('#searchlitralid').on('keyup', function () {
	    var value = this.value;
	    $('#example li').hide().each(function () {
	        if ($(this).text().toUpperCase().search(value.toUpperCase()) > -1) {
	        	$(this).show();;
	        }
	    });
	});*/
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
		populateMaterialGroupList(fetchList("getMaterialGroupList",null));/*
		populateSubGroupList(fetchList("getMaterialSubGroupList",null));*/
		$('.commenSearchTable').DataTable({
			"lengthMenu":lengthMenu
		});
		$('#srchItemForm .tahdrDetailTypeCode').val('I');
		$('#AdditemButton').removeData('callback');
		$('#AdditemButton').attr('data-callback','loadItemName');
		$('#searchItemList').attr('onclick',"return submitIt('srchItemForm','getItemList','populateItemListWithSingleSelect');");
			
	});
	
	$('.addSpecPopup').click(function(event){
		event.preventDefault();
		$('.commenSearchModal').modal('show');
		populateMaterialGroupList(fetchList("getMaterialGroupList",null));/*
		populateSubGroupList(fetchList("getMaterialSubGroupList",null));*/
		$('.commenSearchTable').DataTable({
			"lengthMenu":lengthMenu
		});
		$('#srchItemForm .tahdrDetailTypeCode').val('I');
		$('#AdditemButton').removeData('callback');
		$('#AdditemButton').attr('data-callback','loadSpecName');
		$('#searchItemList').attr('onclick',"return submitIt('srchItemForm','getItemList','populateItemListWithSingleSelect');");
			
	});

	$('#addSpecBtnId').click(function(event) {
		event.preventDefault();
		$('#SpecPartstForm')[0].reset();
		$('.Material').val('');
		$('.Specification').val('');
		$('#materialSpecificationId').val("");
	});
	
	$('#editSpecBtnId').click(function(event) {
		event.preventDefault();
		/* $('#factoryFormDivId').removeClass('readonly'); */
	});

	$('#cancelSpecsBtnId').click(function(event) {
		event.preventDefault();
		var activeSpecId = $('.leftPaneData').find('li.active').attr('id');
		if (activeSpecId != undefined) {
			showdetails(activeSpecId);
		} else
			$('#SpecPartstForm')[0].reset();
	});
});

function renderList()
{ debugger;
	$.ajax({
        type : "POST",
        contentType : "application/json",
        url: "getMaterialSpecificationList",
        dataType:"json",
        async:false,
        success: function (data) {
        	if($.isEmptyObject(data))
        		{
        		  Alert.info("No MaterialSpecification present in List");
        		  $('#SpecPartstForm')[0].reset();
        			$('.Material').val('');
        			$('.Specification').val('');
        			$('#materialSpecificationId').val("");
        		}
        	else
        	{
        		loadSpecLeftPane(data);
        	}
		        	
        },
        error: function (e) {
			swal("Exception :");
        }
    });
	}

function loadSpecLeftPane(data){
	debugger;
	$(".leftPaneData").html("");
	var leftPanelHtml = "";
	var i = 0;
	var active = false;
	var firstRow = null;
	$.each(data,function(key, value) {
				var SpecId = value.materialSpecificationId == null ? '': value.materialSpecificationId;

				if (i == 0) {
					firstRow = value;
					active = true;
				}
				leftPanelHtml = leftPanelHtml+ appendList(value, active);
				active = false;
				i++;

			});
	$(".leftPaneData").append(leftPanelHtml);
	$('#example').paginathing();
	loadSpecRightPane(firstRow);
}

function loadSpecRightPane(data) {
	debugger;
	if (data == null) {
		return;
	}
	var itemName = data.material == null ? '' :data.material.materialId==null?'':data.material.materialId;
	var item =data.material==null?'':data.material.materialId==null?'':data.material.name;
	var specification = data.specification == null ? '': data.specification.materialId == null ? '': data.specification.materialId;
	var specNames =data.specification==null?'':data.specification.materialId==null?'':data.specification.name;
	var name = data.name == null ? '' : data.name;
	var code = data.code == null ? '' : data.code;
	var description = data.description == null ? '' :data.description;
	var specId = data.materialSpecificationId == null ? '': data.materialSpecificationId;
	var quantity = data.quantity == null ? '': data.quantity;
	var isActive= data.isActive == null ?'':data.isActive;
	
	$(".Name").val(name);
	$(".Code").val(code);
	$(".Description").val(description);
	$(".Material").append('<option value="'+itemName+'">'+item+'</option>');
	$(".Specification").append('<option value="'+specification+'">'+specNames+'</option>');
	$("#materialSpecificationId").val(specId);
	$(".Quantity").val(quantity);
	$(".isActive").val(isActive);
	
}

/*function appendList(data)
{
	
	var active=" class='active'";
	$('#example').empty();
	var activeStatus=""
	for (var i=0;i<data.length;i++)  {
		if(data[i].isActive=="Y")
			activeStatus="Active";
		else
			activeStatus="InActive";
		if(i==0)
			{
				
				$(".Name").val(data[i].name);
				$(".Code").val(data[i].code);
				$(".Description").val(data[i].description);
				$(".Id").val(data[i].materialSpecificationId);
				if(!$.isEmptyObject(data[i].material))
				$(".Material").val(data[i].material.materialId);
				$(".Specification").val(data[i].material.materialId);
				$(".Quantity").val(data[i].quantity);
				if(activeStatus=="Active")
					$('.isActive').prop('checked', true);
				else
					$('.isActive').prop('checked', false);
				
			$('#masterDetails').empty();
			$('#masterDetails').append('<div class="row"><label class="col-xs-6" ><h4 class="detail_Name">'+data[i].name+'</h4></label>'
		            +'<label class="col-xs-6 mytext detail_Code">'+data[i].code+'</label></div> '
		            +'<div class="row"><label class="col-xs-6 mytext detail_Desc">'+data[i].description+'</label>'
		            +'<label class="col-xs-6 mytext detail_Active">'+data[i].quantity+'</label></div>');
	          
			}
			
		$('#example').append('<li '+active+' onclick="showdetails('+data[i].materialSpecificationId+')"> <a href="" class="" data-toggle="tab">'
		           +' <div class="col-md-12">'
		           +'  <label class="col-xs-6">'+data[i].name+'</label>'
		           +'   <label class="col-xs-6 mytext" data-id="'+data[i].materialSpecificationId+'">'+data[i].description+'</label>'
		           +'  </div>'
		           +'  <div class="col-md-12">'
		           +'    <label class="col-xs-6">'+data[i].code+'</label>'
		           +'    <label class="col-xs-6 mytext2">'+activeStatus+'</label>'
		           +'  </div>'
		           +'  </a>'
		           +'  </li>');
		active="";
          }
$('.example').paginathing();	
	
	$(".tabs-left li a label").text(function(index, currentText) {
	    return currentText.substr(0, 20);
});
	$('.reportCount').html(data.length);
	}
*/
function appendList(value, active) {
	debugger;
	var leftPanelHtml = '';
	var specId = value.materialSpecificationId == null ? '' : value.materialSpecificationId;
	if (active) {
		leftPanelHtml = leftPanelHtml
				+ ' <li class="active" onclick="showdetails(' + specId+ ')" id="' + specId + '">';
	} else {
		leftPanelHtml = leftPanelHtml + ' <li onclick="showdetails('+ specId + ')" id="' + specId + '">';
	}
	var name = value.name == null ? '' : value.name;
	var code = value.code == null ? '' : value.code;
	var description = value.description == null ? '' :value.description;
	var material = value.material == null ? '' : value.material.materialId==null?'': value.material.materialId;
	var specification = value.specification == null ? '' : value.specification.materialId==null?'': value.specification.materialId;
	var quantity = value.quantity == null ? '': value.quantity;
	var isActive= value.isActive == null ?'':value.isActive;
	
	$('#masterDetails').empty();
	$('#masterDetails').append('<div class="row"><label class="col-xs-6" ><h4 class="detail_Name">'+name+'</h4></label>'
            +'<label class="col-xs-6 mytext detail_Code">'+code+'</label></div> '
            +'<div class="row"><label class="col-xs-6 mytext detail_Desc">'+description+'</label>'
            +'<label class="col-xs-6 mytext detail_Active">'+quantity+'</label></div>');
      
	 var materialName =value.material == null ? $('#SpecPartstForm #Material').find('option:selected').text(): value.material.name==null?'': value.material.name;;
	 var specificationName = value.specification == null ? $('#SpecPartstForm #Specification').find('option:selected').text(): value.specification.name==null?'': value.specification.name;
	 
	leftPanelHtml = leftPanelHtml + ' <a href="#Master" data-toggle="tab">'
			+ ' <div class="col-md-12">'
			+ '  <label class="col-xs-6" id="labelName-' + specId + '">'+name+'</label>'
			+ '	<label class="col-xs-6 " id="labelCode-' + specId + '">'+code+ '</label>' + ' </div>'
			+ ' <div class="col-md-12">'
			+ '	<label class="col-xs-6" id="labelDescription-' + specId
			+ '">'+description+'</label>'
			+ '	<label class="col-xs-6" id="labelMaterial-' + specId
			+ '">'+materialName+'</label>' + ' </div>'
			+ ' <div class="col-md-12" style="display: none">'
			+ '	<label class="col-xs-6" id="labelMaterialId-' + specId
			+ '">'+material+'</label>'
			+ '	<label class="col-xs-6" id="labelSpecificationId-' + specId
			+ '">'+specification+'</label>'
			+ '	<label class="col-xs-6" id="labelSpecification-' + specId
			+ '">'+specificationName+'</label>'
			+ '	<label class="col-xs-6" id="labelQuantity-' + specId
			+ '">'+quantity+'</label>'
			+ '	<label class="col-xs-6" id="labelisactiveId-'
			+ specId+ '">'+isActive+'</label>'
			+ '	<label class="col-xs-6" id="labelSpecId-' + specId
			+ '">'+specId+'</label>'
						+' </div>'
			+ ' </a>' + ' </li>';

	return leftPanelHtml;
	$('#example').paginathing();
}

function showdetails(id) {
	debugger;
	
	
	var material=$("#labelMaterialId-"+id).html();
	var matName=$("#labelMaterial-"+id).html();
	var specifications = $("#labelSpecificationId-"+id).html();
	var specName= $("#labelSpecification-"+id).html();
	var name= $("#labelName-" + id).html();
	var code= $("#labelCode-" + id).html();
	var description= $("#labelDescription-" + id).html();
	var quantity= $("#labelQuantity-" + id).html();
	
	$(".Name").val($("#labelName-" + id).html());
	$(".Code").val($("#labelCode-" + id).html());
	$(".Description").val($("#labelDescription-" + id).html());
	$(".Material").empty();
	$(".Material").append('<option value="'+material+'">'+matName+'</option>');
	$(".Specification").empty();
	$(".Specification").append('<option value="'+specifications+'">'+specName+'</option>');
	$(".isActive").val($("#labelisactiveId-" + id).html());
	$("#materialSpecificationId").val($("#labelSpecId-" + id).html());
	$(".Quantity").val($("#labelQuantity-" + id).html());
	
	$('#masterDetails').empty();
	$('#masterDetails').append('<div class="row"><label class="col-xs-6" ><h4 class="detail_Name">'+name+'</h4></label>'
            +'<label class="col-xs-6 mytext detail_Code">'+code+'</label></div> '
            +'<div class="row"><label class="col-xs-6 mytext detail_Desc">'+description+'</label>'
            +'<label class="col-xs-6 mytext detail_Active">'+quantity+'</label></div>');
	
}

/*
function showdetails(id)
{
	$.ajax({
        type : "POST",
        contentType : "application/json",
        url: "getMaterialSpecification/"+id,
        dataType:"json",
        async:false,
        success: function (data) {
        	if($.isEmptyObject(data))
        		{
        		  swal("MaterialSpecification details is empty");
        		}
        	else
        	{
	        	
	        	
	        	$(".Name").val(data.name);
				$(".Code").val(data.code);
				$(".Description").val(data.description);
				$(".Id").val(data.materialSpecificationId);
				if(!$.isEmptyObject(data.material))
				$(".Material").val(data.material.materialId);
				
				$(".Specification").val(data.material.materialId);
				$(".Quantity").val(data.quantity);
				
				if(data.isActive=="Y")
					$('.isActive').prop('checked', true); 
				else
					$('.isActive').prop('checked', false);
				
				
				$(".detail_Name").html(data.name);
				$(".detail_Code").html(data.code);
				$(".detail_Desc").html(data.description);
				$(".detail_Active").html(data.quantity);
        	}
		        	
        },
        error: function (e) {
			swal("Exception :");
        }
    });}

*/

function materialSpecificationResp(data) {
	debugger;
	if(!data.response.hasError)
	{
	var currentSpecId = $('ul.leftPaneData').find('li.active').attr('id');
	var leftPanelHtml = "";
	var status = true;
	var materialSpecificationId = data.materialSpecificationId;

	$('#SpecPartstForm #materialSpecificationId').val(materialSpecificationId);
	$('#SpecPartstForm #Material').val(data.material.materialId);
	$('#SpecPartstForm #Specification').val(data.specification.materialId);
	if (currentSpecId == materialSpecificationId) {
		$('#' + currentSpecId).remove();
	}else {
		$('#' + currentSpecId).removeClass('active');
	}
	leftPanelHtml = appendList(data, status);
	$(".leftPaneData").prepend(leftPanelHtml);
	status = false;
	Alert.info(data.response.message);
	}else{
		Alert.warn(data.response.message);
		}
	}

function specDelResp(data){
	debugger;
	var currentSpecId=$('ul.leftPaneData').find('li.active').attr('id');
	if(data.hasError==false)
	{
		$('#'+currentSpecId).remove();
		$('#SpecPartstForm')[0].reset();
		$('.Material').val('');
		$('.Specification').val('');
		/*$('#productformDivId').addClass('readonly');*/
		Alert.info(data.message,'','success');

	}
else
	{
	  Alert.warn(data.message,"","error");
	}
}

function loadItemName(values){
	debugger;
	$(".Material").html("");
	var options='';
	$.each(values.map,function(index,val){
		options +='<option value="'+index+'">'+val +'</option>'
		
	});
	$(".Material").append(options);
}

function loadSpecName(values){
	debugger;
	$(".Specification").html("");
	var options='';
	$.each(values.map,function(index,val){
		options +='<option value="'+index+'">'+val +'</option>'
		
	});
	$(".Specification").append(options);
}