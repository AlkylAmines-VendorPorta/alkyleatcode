$(document).ready(function(){ 
	
});

$( window ).on( "load", function() {
	getRegisteredVendors();
});
function getRegisteredVendors(){
	debugger;
	var value= $('#Vendorstype').val();
	submitToURL('getRegisteredVendorList/'+value,'vendorList');
	headers();
}


function vendorList(data){
	if(!($.isEmptyObject(data)))
	{
		console.log(data);
		appendRegisteredList(data);
      
	 
	}else
		{
		$("#RegisteredStatusList").DataTable().destroy();
		  $('#RegisteredStatusList tbody').empty();
		  $('#RegisteredStatusList tbody').append('<tr><td colspan="14" style="text-align: center;"><b>"No Records Found"</b></td></tr>');
		  $('#RegisteredStatusList').DataTable();

		}
}

function appendRegisteredList(data)
{ 
	debugger;
	 var res="";
	 $("#RegisteredStatusList").DataTable().destroy();
	 $('#RegisteredStatusList tbody').empty();
	 $("#RegisteredStatusList2").DataTable().destroy();
	 $('#RegisteredStatusList2 tbody').empty();
	 var modalTableData='';
	 var modalWorkTableData = '';
	
	 $.each(data.objectMap.DATA,function(key,value){

    	 var email =  value.email==null?'':value.email;
    	var firstName = value.partner==null?'':value.partner.name==null?'':value.partner.name;
    	 /*var firstName= value.par==null?'':value.user.userDetails==null?'':value.user.userDetails.firstName==null?'':value.user.userDetails.firstName;*/
    	 /*var lastName=value.user==null?'':value.user.userDetails==null?'':value.user.userDetails.lastName==null?'':value.user.userDetails.lastName;*/
         if((value.partner.isContractor) == 'Y'){
        	 modalWorkTableData +='<tr>'  
             	+'<td>'+firstName+' </td>'
             	+'<td>'+email+' </td>'
         		+ '</tr>';
        }

          if((value.partner.isManufacturer) == 'Y' || (value.partner.isTrader) == 'Y'){
    	 modalTableData +='<tr>'  
        	+'<td>'+firstName+' </td>'
        	+'<td>'+email+' </td>'
    		+ '</tr>';
         }
	 });

	 $('#RegisteredStatusList2 tbody').append(modalTableData);		//onclick="approvePayment('+i+',\''+ data.role.value+ '\')" // onclick="rejectPayment('+i+',\''+ data.role.value+ '\')"	 
	 $('#RegisteredStatusList2').DataTable();
	 $('#RegisteredStatusList tbody').append(modalWorkTableData);		//onclick="approvePayment('+i+',\''+ data.role.value+ '\')" // onclick="rejectPayment('+i+',\''+ data.role.value+ '\')"	 
	 $('#RegisteredStatusList').DataTable();
     }
   
function headers(){
	debugger;
	if($('#Vendorstype').val()=='Y'){
		$("#s").html("Registered Vendors Works");
	$("#s2").html("Registered Vendors Procurement");
		/*$("#vendorType").html("Registered Vendors");*/
	}
	if($('#Vendorstype').val()=='N'){
		$("#s").html("Blacklisted Vendors Works");
	$("#s2").html("Blacklisted Vendors Procurement");
		/*$("#vendorType").html("BlackListed Vendors");*/
    }
	if($('#Vendorstype').val()=='B'){
		$("#s").html("Blacklisted Vendors Works");
	$("#s2").html("Blacklisted Vendors Procurement");
		/*$("#vendorType").html("BlackListed Vendors");*/
    }
}