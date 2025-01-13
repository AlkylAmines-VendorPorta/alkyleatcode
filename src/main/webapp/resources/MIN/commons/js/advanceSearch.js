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
	submitToURL("getTenderSearchDetail", "populateSearchDetail");
	$('.indirectFormSubmit').click(function(event) {
		debugger;
        event.preventDefault();
        submitIt('advanceSearchFormId','getSearchList', 'processResponse');
        return false;
    });
	$("#tabstrip").kendoTabStrip();
	
	$('.serchlistoftender').click(function() {
		$('.serchcteriali').hide();
		$('.listoftenderli').show();
		$('.serchcteria').hide();
		$('.listoftender').show();
		$('.listoftenderli').addClass('k-state-active');
	});
	
	$('.ChangeCriteria').click(function() {
		$('.serchcteriali').show();
		$('.listoftenderli').hide();
		$('.serchcteria').show();
		$('.listoftender').hide();
		$('.serchcteriali').addClass('k-state-active');
	});
	mobiletable();
	 /*$('#tahdrTableId').DataTable({
			
		   	"lengthMenu" : [ [ 7, 15, 25, 30 ], [ 7, 15, 25, "All" ] ]
		});*/
	
	 $('.tahdrTable').on("click",".viewTahdrDetail",function(){
		 debugger;
		var tahdrId =  $(this).data('thdrcode');
		$('#tndrCode').text($(this).text());
		submitToURL('getTAHDRDetailsById/'+tahdrId,'tahdrDetailsResponse');
		$("#ViewTenderDetailModal").modal('show');
	 }); 

});

function populateSearchDetail(data)
{
	debugger;
	console.log(data);
	/*loadReferenceListById(id,data.);
	loadReferenceListById(id,list);*/
			if(data.hasOwnProperty('bidType')){
				if(!$.isEmptyObject(data.bidType))
						loadReferenceListById('bidTypeCode',data.bidType);
		}
			if(data.hasOwnProperty('tahdrType')){
				if(!$.isEmptyObject(data.tahdrType))
					loadReferenceListById('tahdrTypeCode',data.tahdrType);
		}
	}

function processResponse(data)
{
	debugger;
	if(!($.isEmptyObject(data.DATA)))
	{
		console.log(data.DATA);
		appendList(data.DATA);
      
	 
	}else
		{
		  $('#tahdrTableId tbody').empty();
		  $('#tahdrTableId tbody').append('<tr><td colspan="14" style="text-align: center;"><b>"No More Records Found"</b></td></tr>');
		  $('#tahdrTableId').DataTable();

		}
}
function appendList(data)
{ 
	 var res="";
	 $("#tahdrTableId").DataTable().destroy();
	 $('#tahdrTableId tbody').empty();
     for(i=0;i<data.length;i++){
    	 var cdate="";
    	 var tdate="";
    	 if(data[i].commercialBidOpenningDate!=null)
    		 {
    		   cdate=formatDate(data[i].commercialBidOpenningDate);
    		 }
    	  if(data[i].techBidOpenningDate!=null)
    		  {
    		    tdate=formatDate(data[i].techBidOpenningDate);
    		  }
    	 
    	  
    	 $('#tahdrTableId tbody').append("<tr><td><a href ='#' class = 'viewTahdrDetail' data-thdrCode='"+data[i].tahdr.tahdrId+"'>"+data[i].tahdr.tahdrCode+"</a></td>" +
    			  "<td>"+data[i].tahdr.tahdrTypeCode+"</td>" +
    			  "<td>"+data[i].tahdr.bidTypeCode+"</td>" + 
		          "<td>"+tdate+"</td>" +
		          /*"<td>"+cdate+"</td>" +*/
		          "<td>"+data[i].tahdrFees+"</td>" +
		          /*"<td><button class='btn btn-info' onclick=vendorPayment("+data[i].tahdr.tahdrCode+") >Purchase</button></td>" +*/
       	 "</tr>");
     }
     $('#tahdrTableId').DataTable({
    	 lengthMenu:lengthMenu
     });
     mobiletable();

}

function resetField()
{	
	if(event!=undefined)
		event.preventDefault();
	$(".reset").val('');
	$(".resetDropDown").val("");
	
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

function tahdrDetailsResponse(data){
	debugger;
	if(!$.isEmptyObject(data.objectMap.websiteUrl)){
		$(".webUrl").text(data.objectMap.websiteUrl);
	}
	if(data.objectMap.tahdr != undefined){
		if(data.objectMap.tahdr.tahdrDetail[0] != undefined){
			
			var tahdr = data.objectMap.tahdr;
			var tahdrDetail = tahdr.tahdrDetail[0]; 
			var tenderFee = tahdrDetail.tahdrFees;
			var gst = 18 ;
			var gstAmount = (tahdrDetail.tahdrFees*gst)/100;
			var totalAmount = tenderFee + gstAmount;
			var isPriceBid= tahdr.tahdrDetail[0]==null?'':tahdr.tahdrDetail[0].isPBDSetLater==null?'':tahdr.tahdrDetail[0].isPBDSetLater;
			var isAnnexure= tahdr.tahdrDetail[0]==null?'':tahdr.tahdrDetail[0].isAnnexureC1==null?'':tahdr.tahdrDetail[0].isAnnexureC1;
			var isSetC1 = tahdr.tahdrDetail[0]==null?'':tahdr.tahdrDetail[0].isSetC1Later==null?'':tahdr.tahdrDetail[0].isSetC1Later;
			
			$('#bidType').text(tahdr.bidTypeCode=='TB'?'Two Bid':'Single Bid');
			$('#materialDesc').text(tahdrDetail.description);
			$('#estimatedCost').text(tahdrDetail.estimatedCost);
			$('#tenderFee').text(tahdrDetail.tahdrFees);
			$('#gst').text(gstAmount);
			$('#ttltndrFee').text(totalAmount);
			$('#tndrVldt').text(tahdrDetail.tahdrValidity);
			$('#tndrSaleOpnDate').text(formatDateTime(tahdrDetail.purchaseFromDate));
			$('#tndrSaleClsDate').text(formatDateTime(tahdrDetail.purchaseToDate));
			$('#cntctEmail').text(tahdrDetail.contactEmailId);
			$('#sbmsnDate').text(formatDateTime(tahdrDetail.technicalBidFromDate));
			$('#tchnCmrclBidDate').text(formatDateTime(tahdrDetail.techBidOpenningDate));
			$('#prcBid').text(formatDateTime(tahdrDetail.priceBidOpenningDate));
			$('#dvsnDate').text(tahdrDetail.deviationOpenningDate);
			$('#anxrc1Date').text(formatDateTime(tahdrDetail.c1OpenningDate));
			
			if(isPriceBid=='Y'){
				$('#prcBid').text('');
			}
			else{
				$('#prcBid').text(formatDateTime(tahdrDetail.priceBidOpenningDate));
			}
		
			if(tahdr.tahdrTypeCode == 'PT' || tahdr.tahdrTypeCode == 'FA'){
				if(isAnnexure=='N'){
					$('#anxrc1Date').text('');
				}
				else if(isAnnexure=='Y'){
					if(isSetC1=='Y'){
						$('#anxrc1Date').text('');
					}
					else{
						$('#anxrc1Date').text(formatDateTime(tahdrDetail.c1OpenningDate));
					}
				}	
			}
			else{
				$('#anxrc1Date').text('');
			}
			
			if(tahdr.tahdrTypeCode == 'PT'){
				if(!isEmpty(tahdrDetail.officeNote)){
					$('#dwnldTahdrDtil').attr('href','./download/'+tahdrDetail.officeNote.attachmentId);
				}else{
					$('#dwnldTahdrDtil').hide();
				}
			}else{
				alert(tahdrDetail.estimatedCost);
				if(tahdrDetail.estimatedCost < 800){
					alert("less");
					if(!isEmpty(tahdrDetail.tenderDoc)){
						$('#dwnldTahdrDtil').attr('href','./download/'+tahdrDetail.tenderDoc.attachmentId);
					}
				}else{
					alert("greater");
					$('#dwnldTahdrDtil').hide();
				}
			}
			
		}
	}
}
