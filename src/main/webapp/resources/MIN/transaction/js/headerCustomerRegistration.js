$(document).ready(function(){ 
	
});

$( window ).on( "load", function() {
	debugger;
	getRegisteredCustomer();
});

function getRegisteredCustomer(){
	debugger;
	var value= $('#customersType').val();
	submitToURL('getRegisteredCustomerList/'+value,'customerList');
	headers();
}

function customerList(data){
	if(!($.isEmptyObject(data)))
	{
		console.log(data);
		appendCustomerList(data);
	}else
		{
		  $("#RegisteredCustList").DataTable().destroy();
		  $('#RegisteredCustList tbody').empty();
		  $('#RegisteredCustList tbody').append('<tr><td colspan="14" style="text-align: center;"><b>"No Records Found"</b></td></tr>');
		  $('#RegisteredCustList').DataTable();

		}
}

function appendCustomerList(data)
{ 
	debugger;
	 var res="";
	 $("#RegisteredCustList").DataTable().destroy();
	 $('#RegisteredCustList tbody').empty();
	 var modalTableData='';
	
	 $.each(data.objectMap.DATA,function(key,value){
	debugger;
    	 var nameVendor=value.partner==null?'':value.partner.name==null?'':value.partner.name;
    	 var email =  value.email==null?'':value.email;
    	/*var companyName = value.partner==null?'':value.partner.name?'':value.partner.name;*/
    	 
    	 modalTableData +='<tr>'  
        	+'<td>'+nameVendor+' </td>'
        	+'<td>'+email+' </td>'
        	/*+'<td>'+companyName+'</td>'*/
    		+ '</tr>';

	 });

	 $('#RegisteredCustList tbody').append(modalTableData);		//onclick="approvePayment('+i+',\''+ data.role.value+ '\')" // onclick="rejectPayment('+i+',\''+ data.role.value+ '\')"	 
	 $('#RegisteredCustList').DataTable();
     }
   
function headers(){
	debugger;
	if($('#customersType').val()=='Y')
		$("#CustomerHeader").html("Registered Customers");
	if($('#customersType').val()=='N')
		$("#CustomerHeader").html("BlackListed Customers");
}