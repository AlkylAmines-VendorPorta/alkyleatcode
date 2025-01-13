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
	submitToURL("getHomePublicNoticeList", 'getPublicnoticeListResp');

});

function getPublicnoticeListResp(data){
	debugger;
	if(!($.isEmptyObject(data)))
	{
		console.log(data);
		appendPNList(data);
      
	 
	}else
		{
		$("#publicNoticetable").DataTable().destroy();
		  $('#publicNoticetable tbody').empty();
		  $('#publicNoticetable tbody').append('<tr><td colspan="14" style="text-align: center;"><b>"No Records Found"</b></td></tr>');
		  $('#publicNoticetable').DataTable();
		  mobiletable();

		}
}

function appendPNList(data)
{ 
	debugger;
	 var res="";
	 $("#publicNoticetable").DataTable().destroy();
	 $('#publicNoticetable tbody').empty();
	 var modalTableData='';
	 var count=1;
	 var publicNoticeAttach='';
	 $.each(data,function(key,value){
	debugger;
	if(!$.isEmptyObject(value.attachment)){
		 publicNoticeAttach='<a class="" href="download/'+value.attachment.attachmentId+'" style="font-size: 20px ;" id="a_FilePN_0"><span class="glyphicon glyphicon-download-alt"></span></a>';
	 }
    	 var pdate=value.publishingDate==null?'':value.publishingDate;
    	 var tahdrData =  value.tahdr==null?'':value.tahdr.tahdrCode==null?'':value.tahdr.tahdrCode;
    	var description = value.description==null?'':value.description;
    	 
    	 modalTableData +='<tr>'  
        	+'<td>'+count+' </td>'
        	+'<td>'+tahdrData+' </td>'
        	+'<td>'+description+'</td>'
        	+'<td>'+formatDate(pdate)+'</td>'
        	+'<td style="text-align:center;">'+publicNoticeAttach+'</td>'
    		+ '</tr>';
 count++;
 publicNoticeAttach='';
	 });

	 $('#publicNoticetable tbody').append(modalTableData);		//onclick="approvePayment('+i+',\''+ data.role.value+ '\')" // onclick="rejectPayment('+i+',\''+ data.role.value+ '\')"	 
	 $('#publicNoticetable').DataTable();
	 mobiletable();
     }

function formatDate(longDate){
	   var dt=new Date(longDate);
	   var dd=dt.getDate();
	   var MM=dt.getMonth()+1;
	   var yyyy=dt.getFullYear();
	   var HH= dt.getHours();
	   var mm= dt.getMinutes();
	   var ss= dt.getSeconds();
	   
	   return yyyy+'-'+MM+'-'+dd;
	   /*+' '+HH+':'+mm+':'+ss;*/
}
	
