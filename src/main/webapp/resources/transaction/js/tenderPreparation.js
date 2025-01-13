/**
 * @author Ankush
 */

var responseData;
var tableRow;
var bidTypeList;
var tahdrTypeList;
var priceBasis;
var gtpCount;
var tahdrMaterial;
var officeType;
var budgetType;
var bidSection;
var tahdrStatusList;
var tahdrViewButton = new Array();
//var plugin_index = $('.paginate').length;
var roleDto;
var dataUrlTypeCode;
var dataUrl;
var leftPanePageSize = 10;
var maxVisiblePageNumbers = 3;
var selectedPage = 1;
var bpartnerarray = [];
function processResponse(data){

	swal(data.response.message);
	responseData=data;
	
}

$(document).ready(function(){
	debugger;
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
		    
		    
		$('.addItemPopup').click(function(event){
			event.preventDefault();
			$('.commenSearchModal').modal('show');
			$(".itemTable").DataTable().destroy();
			$(".itemTable tbody").empty();
			populateMaterialGroupList(fetchList("getMaterialGroupList",null));/*
			populateSubGroupList(fetchList("getMaterialSubGroupList",null));*/
			
		});
		/*$("#CreatePrivateAuction").on('change',function(event){
			event.preventDefault();
			  debugger;
			  var status=$('input:radio[class=checkregistrationtype]').attr('checked');
			  var status= $('#CreatePrivateAuction').is(':checked');
			  if($(this).prop('checked')){
				  var auctionID=$(".tahdrId").val();
				  $('.CreatePrivateAuctiontab').show();	
				  
				  submitToURL('getAllParners', 'setListtotable');
				  if(auctionID != ""){
				  submitWithParam('getSelectedParticipant', 'tenderID', 'checkAllSelecetdParticipant')
				  }
			  }else{
				  $('.CreatePrivateAuctiontab').hide();	 
			  }
			 
			  	    	
		    });
		*/
		
		/*$('.BPDetails').on('click',function(){
			alert();
			var newRecord='';
			$("input:checkbox[name='bpartnerlist']:checked").each(function(){
				bpartnerarray.push($(this).val());
				 newRecord+='<tr><td><input type="checkbox"  name="bPartnerId" value="'+$(this).val()+'"/></td></tr>';
				 $('#hiddenformId tbody').append(newRecord);
			});
		});*/
		/*$("#sendpartnerinvitation").click(function(){
			var newRecord='';
			var aucid=$(".tahdrId").val();
			var count=0;
			$("#aucid").val(aucid);
			$("input:checkbox[name='bpartnerlist']:checked").each(function(){
				debugger;
				 newRecord+='<tr><td><input type="text"  name="partners['+count+'].bPartnerId" value="'+$(this).val()+'"/></td></tr>';
				 $('#hiddenformId tbody').append(newRecord);
				 newRecord='';
				 count++;
				
			});
			var trCount=$('#hiddenformId tr').length;
			if(trCount!=0){
				submitIt("hiddenformId","invitationAuctionParticipant", "getvalue");
			}
			else{
				Alert.warn('No Partner Selected !');
			}
			$('#hiddenformId tbody').empty();
		});*/
		
		
	    /*$('#vendorselectiontable tfoot th').each(function () {
	        var title = $('#example thead th').eq($(this).index()).text();
	        $(this).html('<input type="text" placeholder="Search ' + title + '" />');
	    });

	    // DataTable
	    var table = $('#vendorselectiontable').DataTable();

	    // Apply the search
	    table.columns().eq(0).each(function (colIdx) {
	        $('input', table.column(colIdx).footer()).on('keyup change', function () {
	            table.column(colIdx)
	                .search(this.value)
	                .draw();
	        });
	    });*/
		/*$('.vendorselectiontable').DataTable();*/
	/*$(".searchMaterial").on("click",function(){
		$("#tahdrMaterialDiv").css("display","none");
		$("#selectMaterialDiv").css("display","block");
		populateMaterialGroupList(fetchList("getMaterialGroupList",null));
	});*/
	
	$("#selectMaterialBtn").on("click",function(){
		$("#tahdrMaterialDiv").css("display","block");
		$("#selectMaterialDiv").css("display","none");
		$("#selectedMaterialId").empty();
		$("#selectedMaterialId").append($('#srchMaterial').find(":selected"));
		$("#selectedMaterialDesc").val($('#selectedMaterialId').children().attr("description"));
		$("#selectedMaterialUom").val($('#selectedMaterialId').children().attr("uom"));
	});
	
	$(".resetSrchMaterial").on("click", function(event){
		event.preventDefault();
		var grpList=fetchList("getMaterialGroupList",null);
		populateMaterialGroupList(grpList);
	});
	
	$(".cancelSrchMaterial").on("click", function(event){
		event.preventDefault();
		$("#tahdrMaterialDiv").css("display","block");
		$("#selectMaterialDiv").css("display","none");
	});
	
	$(".materialGroupList").on("change",function(){
		var id= $(this).val();
		var subGrpList=fetchList("getSubGroupByGroup",id);
		populateSubGroupList(subGrpList);
	})
	
	$(".materialSubGroupList").on("change",function(){
		var id= $(this).val();
		var materialList=fetchList("getMaterialBySubGroup",id);
		populateMaterialList(materialList);
	})
	
	
	
	$("input[name='tenderTypeCodeToggle']").on("change",function(){
		debugger;
		$('.pagination-container').remove();
		$("#tahdrForm")[0].reset();
		var tenderTypeCode=$(this).val();
		dataUrlTypeCode=$(".dataUrlTypeCode").val();
		/*var url=dataUrlTypeCode+"/"+$(this).val()+"/"+1+"/"+leftPanePageSize+"/none"+"/none"*/
		selectedPage = 1;
		var url=dataUrlTypeCode+"?tahdrTypeCode="+$(this).val()+"&pageNumber=1&pageSize="+leftPanePageSize+"&searchMode=none&searchValue=none"
		var tahdrList=fetchList(url);
		$('.disclearfix').css('display','block');
		if ( $('.leftPaneData li').length == 0 ) {
			$('.pagination').children().remove();
			$('#tahdrDetailTab').addClass('readonly');
			$('#impDatesTab').addClass('readonly');;
			$('#tenderDocTab').addClass('readonly');
			/*$('#tenderDocsOrdering').addClass('readonly');*/
			$('#getTahdrMaterialList').addClass('readonly');
			$('#sectionDocTab').addClass('readonly');
			$('#confirmation').addClass('readonly');
			//$('#leftPane').paginathing({perPage: 6});
				
		}
		else{
			$('.pagination-container').remove();
			 $('#tahdrDetailTab').removeClass('readonly');
			 //$('#leftPane').paginathing({perPage: 6});					
			 $(".tabs-left li a label").text(function(index, currentText) {
				    return currentText.substr(0, 20);
				});
		}
		
		if(roleDto.value=='EXEENGR' && tenderTypeCode=='PT'){
			$("#officeType").val('HO');
			$("#officeType").addClass('readonly');
			loadOfficeLocation();
		}
		else{
			$("#officeType").val("");
			$("#officeType").removeClass('readonly');
		}
		
		if($(".dataUrlTypeCode").val()=='getTAHDRByTypeCode' && $(".dataUrl").val()=='tenderPreparationData'){
			$(".addTahd").show();
		}
		else if($(".dataUrlTypeCode").val()=='getTAHDRApprovalByTypeCode' && $(".dataUrl").val()=='tenderApprovalData'){
			$(".addTahd").hide();
		}
		loadTahdrList(tahdrList.data,$(this).val());
		setPagination(tahdrList.objectMap.LastPage, selectedPage, maxVisiblePageNumbers);
		setChildLoadFlag(true);
	});

	$("#budgetType").on("change",function(event){
		debugger;
		event.preventDefault();
		var budgetType=$(this).val();
		if($.isEmptyObject(budgetType)){
			$('#schemeNameDivId').show();
			$('#schemeCodeDivId').show();
			$('#schemeName').removeAttr('disabled');
			$('#schemeCode').removeAttr('disabled');
		}else if(budgetType=="RVN"){
			$('#schemeNameDivId').hide();
			$('#schemeCodeDivId').hide();
			$('#schemeName').attr('disabled','disabled');
			$('#schemeCode').attr('disabled','disabled');
		}else if(budgetType=="CAP"){
			$('#schemeNameDivId').val('');
			$('#schemeCodeDivId').val('');
			$('#schemeNameDivId').show();
			$('#schemeCodeDivId').show();
			$('#schemeName').removeAttr('disabled');
			$('#schemeCode').removeAttr('disabled');
		}
	});
	
	$("#reqDocTable").on("change",".enableAddRow",function(){
		var id=$(this).attr("id").split('_');
		var tr=$(this).parents('tr');
		var siblingsArray=$(tr).find('.enableAddRow');
		var flag=true;
		$.each(siblingsArray,function(idx,sibling){
			if($.isEmptyObject($(sibling).val())){
				flag=false;
			}
		});
		if(flag){
			$(tr).find(".addRow").removeAttr("disabled");
		}else{
			$(tr).find(".addRow").attr("disabled","disabled");
		}
	});
	dataUrl=$(".dataUrl").val();
	/*loadTahdrListDropDowns(dataUrl+"/"+$("input[name='tenderTypeCodeToggle']:checked").val());*/
	loadTahdrListDropDowns(dataUrl+"?tenderTypeCode="+$("input[name='tenderTypeCodeToggle']:checked").val()+"&pageNumber=1&pageSize="+leftPanePageSize+"&searchMode=none&searchValue=none");
});
function getvalue(data){
	alert();
};

function setListtotable(data){
	debugger;
	if(!($.isEmptyObject(data)))
	{
		console.log(data);
		appendBpartnerList(data);
	}else
		{
		 
		  $('#bpartnerdetailtab tbody').empty();
		  $("#bpartnerdetailtab").DataTable().destroy();
		  $('#bpartnerdetailtab tbody').append('<tr><td colspan="14" style="text-align: center;"><b>"No Records Found"</b></td></tr>');
		  $('#bpartnerdetailtab').DataTable();

		}
}
/*function checkAllSelecetdParticipant(data){
	debugger;
	if(!($.isEmptyObject(data)))
	{
	 for(i=0;i<data.length;i++){
		 var bpartner =data[i].bPartner.bPartnerId;
		 $("input:checkbox[value='" + bpartner + "']").prop('checked', true);
		 $("input:checkbox[value='" + bpartner + "']").addClass('readonly');
	 }
	}else{
		return
	}
}*/

function appendBpartnerList(data){
	 
	  $('#bpartnerdetailtab tbody').empty();
	  $("#bpartnerdetailtab").DataTable().destroy();
	 for(i=0;i<data.length;i++){
		 var mobileNo=data[i].userDetails==null?'':data[i].userDetails.mobileNo==null?'':data[i].userDetails.mobileNo;
		 var bpaertnerid=data[i].partner==null?'':data[i].partner.bPartnerId==null?'':data[i].partner.bPartnerId;
		 var cname=data[i].partner==null?'':data[i].partner.name==null?'':data[i].partner.name;
		 var firstname=data[i].userDetails==null?'':data[i].userDetails.firstName==null?'':data[i].userDetails.firstName;
		 var pancode=data[i].partner==null?'':data[i].partner.panNumber==null?'':data[i].partner.panNumber;
		 var emailid=data[i].userDetails==null?'':data[i].userDetails.email==null?'':data[i].userDetails.email;
		 $('#bpartnerdetailtab tbody').append('<tr><td><input type="checkbox" class="BPDetails" name="bpartnerlist" value="'+bpaertnerid+'"></td>' +
    			  '<td>'+cname+'</td>' +
    			  '<td>'+firstname+'</td>'+ 
    			  '<td>'+pancode+'</td>' +
    			  '<td>'+emailid+'</td>' +
    			  '<td>'+mobileNo+'</td>' +
    			 
       	 '</tr>');
		 

		    // DataTable
		    var table = $('#bpartnerdetailtab').DataTable();
		    $('#bpartnerdetailtab tfoot th').each(function () {
		        var title = $('#bpartnerdetailtab thead th').eq($(this).index()).text();
		        $(this).html('<input type="text" placeholder="Search ' + title + '" />');
		    });

		    // Apply the search
		    table.columns().eq(0).each(function (colIdx) {
		        $('input', table.column(colIdx).footer()).on('keyup change', function () {
		            table.column(colIdx)
		                .search(this.value)
		                .draw();
		        });
		    });
		 
	 }
	
}
function itemListDropDown(values)
{
	debugger;
		$("#selectedMaterialId").html("");
		var options='';
		var materialId;
		$.each(values.map,function(index,val){
			options +='<option value="'+index+'">'+val +'</option>'
			materialId=index;
		});
		$("#selectedMaterialId").append(options);
		var material=fetchList("getMaterial",materialId);
		 $('#selectedMaterialUom').val(values.uomName);
		 $('#selectedMaterialDesc').val(values.matDescription);
		 $('#selectedItemCode').val(material.code);
		 $('#selectedHSNCode').val(material.hsnCode.code);
		 var materialSelectedId=$("#selectedMaterialId").val();
			includeSpec(materialSelectedId);
} 
function populateTahdrMaterialDetail(tahdrMaterial){
	$("#saveTahdrMaterialForm").attr("action","updateTahdrMaterial");
	$(".saveTahdrMaterial").css("display","none");
	$(".updateTahdrMaterial").css("display","inline-block");
	
}

function loadTahdrListDropDowns(url){
	debugger;
	var tahdrPreparationData=fetchList(url,null);
	tahdrTypeList=tahdrPreparationData.objectMap.tahdrTypeList;
	bidTypeList=tahdrPreparationData.objectMap.bidTypeList;
	priceBasis=tahdrPreparationData.objectMap.priceBasis;
	officeType=tahdrPreparationData.objectMap.officeType;
	budgetType=tahdrPreparationData.objectMap.budgetType;
	bidSection=tahdrPreparationData.objectMap.bidSection;
	tahdrStatusList =  tahdrPreparationData.objectMap.tenderStatus;
	roleDto = tahdrPreparationData.objectMap.roleDto;
	loadReferenceList("tahdrTypeCode",tahdrTypeList);
	loadReferenceList("bidTypeCode",bidTypeList);
	loadReferenceList("priceBasis",priceBasis);
	/*loadReferenceList("officeType",officeType);*/
	loadReferenceList("budgetType",budgetType);
	loadReferenceList("sectionCode",bidSection);
	populateDepartment(tahdrPreparationData.objectMap.deptList);
	populateLocationType(tahdrPreparationData.objectMap.officeType)
	tahdrViewButton=tahdrPreparationData.objectMap.viewButton;
	setCurrentTab($("#tenderBaseInfoTab"));
	setChangedFlag(false);
	loadTahdrList(tahdrPreparationData.objectMap.listTahdr.data,tahdrPreparationData.objectMap.tenderTypeCode);
	setPagination(tahdrPreparationData.objectMap.listTahdr.objectMap.LastPage, 1, maxVisiblePageNumbers);
}


function loadTahdrList(listTahdr,tenderTypeCode){
	debugger;
	$("#tabstrip").kendoTabStrip();
	$("#tahdrForm")[0].reset();
	if(roleDto.value=='EXEENGR' && tenderTypeCode=="PT"){
		$("#officeType").val('HO');
		$("#officeType").addClass('readonly');
		loadOfficeLocation();
	}
	
	if($(".dataUrlTypeCode").val()=='getTAHDRByTypeCode' && $(".dataUrl").val()=='tenderPreparationData'){
		$(".addTahd").show();
	}
	else if($(".dataUrlTypeCode").val()=='getTAHDRApprovalByTypeCode' && $(".dataUrl").val()=='tenderApprovalData'){
		$(".addTahd").hide();
	}
	
	$("#tahdrType").val(tenderTypeCode);
	if(tenderTypeCode=="PT"){		
		loadFieldsForProcurement();		
	}else if(tenderTypeCode=="WT"){
		loadFieldsForWork();
	}

	loadTahdrToLeftPane(listTahdr);
	$("#tabstrip").kendoTabStrip();
}

/*function showSubmitButton(listTahdr,role){
	debugger;
	
	if(role=='EXEENGR' && listTahdr.tahdrStatusCode=='DR'){
	$("#tahdrSubmitDivId").show();
	$("#ApproveRejlDivId").hide();
	
	}
	else if(role=='EXEENGR' && listTahdr.tahdrStatusCode=='IP'){
		$("#tahdrSubmitDivId").addClass('readonly');
		$("#ApproveRejlDivId").hide();
	}
	else if(role=='CHFENGR' && listTahdr.tahdrStatusCode=='IP'){
		$("#ApproveRejlDivId").show();
		$("#tahdrSubmitDivId").hide();
	}
	
}*/

function loadFieldsForWork(){
	$(".workTender").find('input, textarea,select').removeAttr('disabled','disabled');
	$(".workTender").show();
	$("#sectionTahdrMaterialData").hide();
	$(".procurementTender").find('input, textarea,select').attr('disabled','disabled');
	$(".procurementTender").hide();
	$("#commencementPeriod").removeClass("requiredField");
	$("#commencementPeriod").attr("onchange", "onChangeMonthDate()");
	$("#commencementPeriodSpan").text("");
	$("#selectedMaterialVersionId").removeClass("requiredField");
}

function loadFieldsForProcurement(){
	$(".workTender").find('input, textarea,select').attr('disabled','disabled');
	$(".workTender").hide();
	$(".procurementTender").find('input, textarea,select').removeAttr('disabled','disabled');
	$(".procurementTender").show();
	$('.disclearfix').css('display','block');
	$("#commencementPeriod").addClass("requiredField");
	$("#commencementPeriod").attr("onchange", "onChangeComencementPeriod(); onChangeMonthDate();");
	$("#commencementPeriodSpan").text("*");
	$("#selectedMaterialVersionId").addClass("requiredField");
}
/*function onCheckIsAuction(){
	if($('#isAuction').prop('checked')){
		$('#minBidDifferenceDiv').show();
		$('#minBidDifference').removeAttr('disabled');
	}else{
		$('#minBidDifferenceDiv').hide();
		$('#minBidDifference').attr('disabled','disabled');
	}
}
*/