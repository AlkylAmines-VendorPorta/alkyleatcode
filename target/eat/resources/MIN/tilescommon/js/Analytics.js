$(document).ready(function(){
	$(".cancel").hide();
	submitWithParam('getTotalRevenue','FiscalYear','loadTotalRevenue');
	$("#FiscalYear").on('change', function(){

		submitWithParam('getTotalRevenue','FiscalYear','loadTotalRevenue');
	});
	$("#FiscalYearForSaving").on('change', function(){
		debugger;
		var selectedFiscalYear=$("#FiscalYearForSaving").val();
		var selectedMaterial=$("#MaterialForSaving").val();
		var selectedDepartment=$("#DepartmentIDForSaving").val();
		if(selectedMaterial == null){
			selectedMaterial=0;
		}
		submitToURL("getSavingDetails/"+selectedFiscalYear+"/"+selectedMaterial+"/"+selectedDepartment,'LoadTotalSaving');
	});
$("#MaterialForSaving").on('change', function(){
	var selectedFiscalYear=$("#FiscalYearForSaving").val();
	var selectedMaterial=$("#MaterialForSaving").val();
	var selectedDepartment=$("#DepartmentIDForSaving").val();
	if(selectedMaterial == null){
		selectedMaterial=0;
	}
	submitToURL("getSavingDetails/"+selectedFiscalYear+"/"+selectedMaterial+"/"+selectedDepartment,'LoadTotalSaving');
	
	});
$("#DepartmentIDForSaving").on('change', function(){
	var selectedFiscalYear=$("#FiscalYearForSaving").val();
	var selectedMaterial=$("#MaterialForSaving").val();
	var selectedDepartment=$("#DepartmentIDForSaving").val();
	if(selectedMaterial == null){
		selectedMaterial=0;
	}
	submitToURL("getSavingDetails/"+selectedFiscalYear+"/"+selectedMaterial+"/"+selectedDepartment,'LoadTotalSaving');
	
});
	$("#getTotalSavingDetails").click(function(){
		debugger;
		var selectedFiscalYear=$("#FiscalYearForSaving").val();
		var selectedMaterial=0;
		var selectedDepartment=0;
		submitToURL("getSavingDetails/"+selectedFiscalYear+"/"+selectedMaterial+"/"+selectedDepartment,'LoadTotalSaving');
	});
	$('.addMatPopup').click(function(event){
		event.preventDefault();
		$('.commenSearchModal').modal('show');
		populateMaterialGroupList(fetchList("getMaterialGroupList",null));/*
		populateSubGroupList(fetchList("getMaterialSubGroupList",null));*/
		$('.commenSearchTable').DataTable({
			"lengthMenu":lengthMenu
		});
		/*$('#srchItemForm .tahdrDetailTypeCode').val('I');*/
		$('#AdditemButton').removeData('callback');
		$('#AdditemButton').attr('data-callback','loadItemName');
		$('#searchItemList').attr('onclick',"return submitIt('srchItemForm','getItemList','populateItemListWithSingleSelect');");
			
	});
	$(".cancel").click(function(){
		$("#MaterialForSaving").html("");
		$(".cancel").hide();
		$("#MaterialForSaving").trigger("change");
		$('#materialId').removeClass('pull-right');
	});
});
function loadItemName(values){
	debugger;
	$("#MaterialForSaving").html("");
	var options='';
	$.each(values.map,function(index,val){
		options +='<option value="'+index+'">'+val +'</option>'
		
	});
	$("#MaterialForSaving").append(options);
	$(".cancel").show();
	$('#materialId').addClass('pull-right');
	$("#MaterialForSaving").trigger("change");
}
function loadTotalRevenue(data)
{
	console.log(data);
	var options = {
			animationEnabled: true,
			title:{
				text: "Total Revenue"   
			},
			axisY:{
				title:"Amount in Rs."
			},
			toolTip: {
				shared: true,
				reversed: true
			},
			data: [{
				type: "stackedColumn",
				name: "Revenue From Registration",
				showInLegend: "true",
				yValueFormatString: "#,##0 in INR",
				dataPoints: data.objectMap.RegRevenue
			},
			{
				type: "stackedColumn",
				name: "Revenue From Registration Renuewel",
				showInLegend: "true",
				yValueFormatString: "#,##0 in INR",
				dataPoints: data.objectMap.RegRenuewelRevenue
			},
			{
				type: "stackedColumn",
				name: "Revenue From Tender Fee",
				showInLegend: "true",
				yValueFormatString: "#,##0 in INR",
				dataPoints: data.objectMap.TenderFeeRevenue
			},
			{
				type: "stackedColumn",
				name: "Revenue From EMD Fees",
				showInLegend: "true",
				yValueFormatString: "#,##0 in INR",
				dataPoints: data.objectMap.EmdRevenue
			}
			
			]
		};

		$("#chartContainer").CanvasJSChart(options);
	}
function LoadTotalSaving(data)
{
	console.log(data);
	var options = {
			animationEnabled: true,
			title:{
				text: "Total Saving"   
			},
			axisY:{
				title:"Amount in Rs."
			},
			toolTip: {
				shared: true,
				reversed: true
			},
			data: [{
				type: "stackedColumn",
				name: "Saving From Procurment Tenders",
				showInLegend: "true",
				yValueFormatString: "#,##0 in INR",
				dataPoints: data.objectMap.PTSaving
			},
			{
				type: "stackedColumn",
				name: "Saving From Works Tenders",
				showInLegend: "true",
				yValueFormatString: "#,##0 in INR",
				dataPoints: data.objectMap.WTSaving
			},
			{
				type: "stackedColumn",
				name: "Saving From Fordwar Auction",
				showInLegend: "true",
				yValueFormatString: "#,##0 in INR",
				dataPoints: data.objectMap.FASaving
			},
			{
				type: "stackedColumn",
				name: "Saving From Reverse Auction",
				showInLegend: "true",
				yValueFormatString: "#,##0 in INR",
				dataPoints: data.objectMap.RASaving
			}
			
			]
		};

		$("#savingChart").CanvasJSChart(options);
	}