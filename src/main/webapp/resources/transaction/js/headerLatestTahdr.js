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

	  if ( ! $.fn.DataTable.isDataTable( '#tahdrTable' ) ) {
		  
		  $('#tahdrTable').DataTable({
			  "lengthMenu":lengthMenu,
		  });
		  mobiletable();
	  }
	  
	 $('#tahdrTable').on("click",".viewTahdrDetail",function(){
		 debugger;
		var tahdrId =  $(this).data('thdrcode');
		$('#tndrCode').text($(this).text());
		submitToURL('getTAHDRDetailsById/'+tahdrId,'tahdrDetailsResponse');
		$("#ViewTenderDetailModal").modal('show');
	 }); 
   
});
	
$( window ).on( "load", function() {
	debugger;
	getTahdr();
});

function getTahdr(){
    var value= $('#latestTAHDR').val();
	submitToURL('getTahdrTypeCode/'+value,'TahdrDetailsTableResponse');
	headers(value);
}



function TahdrDetailsTableResponse(data){
	if(!($.isEmptyObject(data.DATA)))
	{
		console.log(data.DATA);
		appendTahdrList(data.DATA);
		
	 
	}else
		{
		$("#tahdrTable").DataTable().destroy();
		  $('#tahdrTable tbody').empty();
		  $('#tahdrTable tbody').append('<tr><td colspan="14" style="text-align: center;"><b>"No Records Found"</b></td></tr>');
		  if ( ! $.fn.DataTable.isDataTable( '#tahdrTable' ) ) {
			  $('#tahdrTable').DataTable();
			  mobiletable();
		  }

		}
}
function appendTahdrList(data)
{ debugger;
	 var res="";
	 $("#tahdrTable").DataTable().destroy();
	 $('#tahdrTable tbody').empty();
     for(i=0;i<data.length;i++){
    	 var cdate="";
    	 var tdate="";
    	 var techDate="";
    	 var suDueDate="";
    	 var momdata='';
    	 if(data[i].purchaseFromDate!=null)
    		 {
    		   cdate=formatDate(data[i].purchaseFromDate);
    		 }
    	  if(data[i].purchaseToDate!=null)
    		  {
    		    tdate=formatDate(data[i].purchaseToDate);
    		  }
    	  if(data[i].techBidOpenningDate!=null)
		  {
    		  techDate=formatDate(data[i].techBidOpenningDate);
		  }
    	 /* if(data[i].purchaseToDate!=null)
		  {
		    tdate=formatDate(data[i].purchaseToDate);
		  }*/
    	 if(!$.isEmptyObject(data[i].tahdr.mom)){
    		 momdata='<a class="" href="download/'+data[i].tahdr.mom.attachmentId+'" style="font-size: 20px ;" id="a_File_0"><span class="glyphicon glyphicon-download-alt"></span></a>';
    	 }
    	 $('#tahdrTable tbody').append('<tr><td><a href ="#" class = "viewTahdrDetail" data-thdrCode="'+data[i].tahdr.tahdrId+'">'+data[i].tahdr.tahdrCode+"</a></td>" +
    			  '<td>'+data[i].description+'</td>' +
    			  '<td>'+cdate+'</td>'+ 
    			  '<td>'+tdate+'</td>' +
    			  '<td>'+techDate+'</td>' +
    			  '<td>'+techDate+'</td>' +
    			  '<td>'+data[i].tahdrFees+'</td>' +
		          /*"<td><button class='btn btn-info' onclick=vendorPayment("+data[i].tahdr.tahdrCode+") >Purchase</button></td>" +*/
		         /* "<td></td>" +*/
		          '<td>'+momdata+'</td>' +
       	 '</tr>');
     }
	  if ( ! $.fn.DataTable.isDataTable( '#tahdrTable' ) ) {
		  $('#tahdrTable').DataTable();
		  mobiletable();
	  }
   

}

function tahdrDetailsResponse(data){
	debugger;
	if(data.objectMap.tahdr != undefined){
		if(data.objectMap.tahdr.tahdrDetail[0] != undefined){
			
			var tahdr = data.objectMap.tahdr;
			var tahdrDetail = tahdr.tahdrDetail[0]; 
			var tenderFee = tahdrDetail.tahdrFees;
			var gst = 18 ;
			var gstAmount = (tahdrDetail.tahdrFees*gst)/100;
			var totalAmount = tenderFee + gstAmount;
			
			$('#bidType').text(tahdr.bidTypeCode=='TB'?'Tow Bid':'Single Bid');
			$('#materialDesc').text(tahdrDetail.description);
			$('#estimatedCost').text(tahdrDetail.estimatedCost);
			$('#tenderFee').text(tahdrDetail.tahdrFees);
			$('#gst').text(gstAmount);
			$('#ttltndrFee').text(totalAmount);
			$('#tndrVldt').text(tahdrDetail.tahdrValidity);
			$('#tndrSaleOpnDate').text(formatDateTime(tahdrDetail.purchaseFromDate));
			$('#tndrSaleClsDate').text(formatDateTime(tahdrDetail.purchaseToDate));
			$('#cntctEmail').text(tahdrDetail.contactEmailId);
			$('#sbmsnDate').text('');
			$('#tchnCmrclBidDate').text(formatDateTime(tahdrDetail.techBidOpenningDate));
			$('#prcBid').text(formatDateTime(tahdrDetail.priceBidOpenningDate));
			$('#dvsnDate').text(tahdrDetail.deviationOpenningDate);
			$('#anxrc1Date').text(formatDateTime(tahdrDetail.c1OpenningDate));
			
			if(tahdr.tahdrTypeCode == 'PT'){
				if(!isEmpty(tahdrDetail.tenderDoc)){
					$('#dwnldTahdrDtil').attr('href','./download/'+tahdrDetail.tenderDoc.attachmentId);
				}
			}else{
				if(tahdrDetail.estimatedCost < 800){
					if(!isEmpty(tahdrDetail.tenderDoc)){
						$('#dwnldTahdrDtil').attr('href','./download/'+tahdrDetail.tenderDoc.attachmentId);
					}
				}else{
					$('#dwnldTahdrDtil').hide();
				}
			}
			
		}
	}
}

function headers(data){
	debugger;
	if(data=='WT')
		$("#tahdrHeader").html("Latest Tender Works");
	if(data=='PT')
		$("#tahdrHeader").html("Latest Tender Procurement");
	if(data=='RA')
		$("#tahdrHeader").html("Latest Auction Reverse");
	if(data=='FA')
		$("#tahdrHeader").html("Latest Auction Forward");
}