/**
 * Aman
 */
$(document).ready(function() {
                        $("#tabstrip").kendoTabStrip();
                        renderList();
                        $('.indirectFormSubmit').click(function(event) {
                            event.preventDefault();
                            if($('#formId tr').length!=0)
                            	{
                            		submitIt("formId","saveRoleAccessMasters", "processResponse");
                            	}
                            else
                            	swal("Nothing To Submit");
                            return false;
                        });
                        $('.example').paginathing();	
                    	
                    	$(".tabs-left li a label").text(function(index, currentText) {
                    	    return currentText.substr(0, 20);
                    	});
                    	
                    });
var pTileId=0;
var currentTile=[];
var map = new Object();
var prevCode='';
var currentRole='';
var isCanceled=false;

function cancel()
{
	debugger;
	isCanceled=true;
	if($('#formId tr').length!=0)
		{
			var input_box = confirm("Changes is not saved,Do you want to Save them ?");
		 if (input_box == true) {
			 submitIt("formId","saveRoleAccessMasters", "processResponse");
		 }
		 else
			{
			 	$('#formId tbody').empty();
				showDetails($('#activeRole').val());
				currentTile=[];
			}
		}
		if(event!=undefined)
			event.preventDefault();
	
	isCanceled=false;
}

function renderList()
{
	var id=$('#activeRole').val();
	$.ajax({
        type : "POST",
        contentType : "application/json",
        url: "getRoleAccessMaster/"+id,
        dataType:"json",
        async:false,
        success: function (data) {
        	if($.isEmptyObject(data))
        		{
        		  swal("Role details is not present");
        		}
        	else
        	{
	        	setAccessDetails(data);
	        	swal(currentRole+' Access Details is Set Now!','','success');
        	}  	
        },
        error: function (e) {
			swal("Exception:  ");
        }
    });
	}
function onCheckBoxChange(tileId,code,el)
{
	debugger;
	var roleId=$('#activeRole').val();
	var count=$('#formId tr').length;
	var id=$(el).attr("id");
	
	checkForViewOnly(id);
	var newRecord='';
	
	var roleAccessId=$('#'+code+'_roleAccessId').val();
			
	
	if(jQuery.inArray(code, currentTile) == -1)
	{
		 newRecord+='<tr><td><input type="text" id="'+code+'_roleAccessMasterId" name="roleList['+count+'].roleAccessMasterId"/></td>'
		 +'<td><input type="text" id="'+tileId+'_role" name="roleList['+count+'].role.roleId"/></td>'
		+'<td><input type="text" id="'+tileId+'_tile" name="roleList['+count+'].tile.tileMasterId"/></td>'
		+'<td><input type="checkbox" id="'+tileId+'_'+code+'_View" name="roleList['+count+'].viewOnly" value="Y"/></td>'
		+'<td><input type="checkbox" id="'+tileId+'_'+code+'_Edit" name="roleList['+count+'].modifyAccess" value="Y"/></td>'
		+'<td><input type="checkbox" id="'+tileId+'_'+code+'_Delete" name="roleList['+count+'].deleteAccess" value="Y"/></td></tr>';
	  currentTile.push(code);
	
			$('#formId tbody').append(newRecord);
			$('#'+code+'_roleAccessMasterId').val(roleAccessId);
			$('#'+tileId+'_role').val(roleId);
			$('#'+tileId+'_tile').val(tileId);
			if($("#"+code+'_View').prop("checked") == true)
				{
					$('#'+tileId+'_'+code+'_View').val("Y");
					$('#'+tileId+'_'+code+'_View').attr("checked","checked");
				}
			else
				$('#'+tileId+'_'+code+'_View').val("N");
			
			if($("#"+code+'_Edit').prop("checked") == true)
				{
					$('#'+tileId+'_'+code+'_Edit').val("Y");
					$('#'+tileId+'_'+code+'_Edit').attr("checked","checked");
				}
			else
				$('#'+tileId+'_'+code+'_Edit').val("N");
			
			if($("#"+code+'_Delete').prop("checked") == true)
				{
					$('#'+tileId+'_'+code+'_Delete').val("Y");
					$('#'+tileId+'_'+code+'_Delete').attr("checked","checked");
				}
				
			else 
				$('#'+tileId+'_'+code+'_Delete').val("N");
		}
	else
		{
		$('#'+code+'_roleAccessMasterId').val(roleAccessId);
			if($("#"+id).prop("checked") == true)
				{
					$("#"+tileId+"_"+id).val("Y");
					$("#"+tileId+"_"+id).attr("checked","checked");
				}
			else
				$("#"+tileId+"_"+id).val("N");
		 	
		}
	/*pTileId=tileId;
	prevCode=code;*/
	
	}
function checkForViewOnly(id)
{
	var checkBoxType = id.split("_");
	if(checkBoxType[1]!="View")
		{
			if($("#"+checkBoxType[0]+"_View").prop("checked") == false)
				{
				  	$("#"+checkBoxType[0]+"_Edit").prop('checked', false);
					$("#"+checkBoxType[0]+"_Delete").prop('checked', false);
				    swal(checkBoxType[0]+" View Is Mandatory For This");
				}
			else
				{
					$("#"+checkBoxType[0]+"_Edit").removeClass('readonly');
					$("#"+checkBoxType[0]+"_Delete").removeClass('readonly');
				}
		}
	else
		{
		
			if($("#"+checkBoxType[0]+"_View").prop("checked") == true)
				{
					$("#"+checkBoxType[0]+"_Edit").removeClass('readonly');
					$("#"+checkBoxType[0]+"_Delete").removeClass('readonly');
				}
			else
				{
					$("#"+checkBoxType[0]+"_Edit").prop('checked', false);
					$("#"+checkBoxType[0]+"_Delete").prop('checked', false);
					$("#"+checkBoxType[0]+"_Edit").addClass('readonly');
					$("#"+checkBoxType[0]+"_Delete").addClass('readonly');
				}
			
		}
	
}

function processResponse(data)
{
	if(data.responseStatus==true)
	{
		$('#formId tbody').empty();
		for(var i=0;i<currentTile.length;i++)
			{
				if(jQuery.inArray(data.data[i].tile.code, currentTile) !== -1)
				{
				  $("#"+data.data[i].tile.code+"_roleAccessId").val(data.data[i].roleAccessMasterId);
				}
			}
		currentTile=[];
    	swal(data.responseMsg,'','success');
    	$('#errorMsg').hide();
	}
	else
		{
			var errorLog = "";
			$.each(data.data,function(key, value){
				errorLog = errorLog+value.errorMessage+"\n ;";
			});
			$('#errorMsg').show();
			$('#errorMsg').html("'"+errorLog+"'")
			swal(data.responseMsg,"","error");
		}
}
function setAccessDetails(data)
{
	for(var i=0;i<data.length;i++)
		{
		if(i==0)
			{
			  currentRole=data[i].role.name;
			}
		 var tilename=data[i].tile.code;
		 $('#'+tilename+'_roleAccessId').val(data[i].roleAccessMasterId);
		 if(data[i].viewOnly=="Y")
				$('#'+tilename+'_View').prop('checked', true); 
			else
				$('#'+tilename+'_View').prop('checked', false);
		 if(data[i].modifyAccess=="Y")
				$('#'+tilename+'_Edit').prop('checked', true); 
			else
				$('#'+tilename+'_Edit').prop('checked', false);
		 if(data[i].deleteAccess=="Y")
				$('#'+tilename+'_Delete').prop('checked', true); 
			else
				$('#'+tilename+'_Delete').prop('checked', false);
		  
		}
}
function showDetails(id)
{
	$('#detailsFormId :input[type=checkbox]').each(function(){
		$(this).prop('checked', false);
	 });
	$('#detailsFormId :input[type=text]').each(function(){
		$(this).val("");
	 });
	$('#activeRole').val(id);
	$("#loader_wrapper").fadeIn();
	$.ajax({
        type : "POST",
        contentType : "application/json",
        url: "getRoleAccessMaster/"+id,
        dataType:"json",
        async:false,
        success: function (data) {
        	if($.isEmptyObject(data))
        		{
        		$("#loader_wrapper").fadeOut("slow");

        		  swal("Accessibility has not been Given");
        		}
        	else
        	{
        		$("#loader_wrapper").fadeOut("slow");
	        	setAccessDetails(data);
	        	if(!isCanceled)
	        		{
	        			swal(currentRole+' Access Details is Set Now!','','success');
	        		}
        	}  	
        },
        error: function (e) {
        	$("#loader_wrapper").fadeOut("slow");
			swal("Exception: ");
        }
    });
	}