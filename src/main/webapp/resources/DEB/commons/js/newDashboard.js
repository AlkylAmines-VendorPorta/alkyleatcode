/**
 * Aman Sahu
 */
var tableId='';
$(document).ready(function() {

			getTenderByTypeCode('WT','tahdrWorksTable');
			 var lengthMenu;
			 var searching;
			    if ($(window).width() < 480) {
			        $('.mobileNav').show();
			        $.fn.DataTable.ext.pager.numbers_length = 4;       
			        lengthMenu = [ 1, 5, 7, 10, ],
			        searching = false
			    } else {        
			        lengthMenu = [ 5, 10, ],
			        searching = true
			    }
			    $('table').each(function(){		
					var text = []
					$(this).find('thead tr th').each(function(){
						text.push($(this).text())

						for (var i = 0; i < text.length; i++) {
							$(this).parents('table').find('tbody tr td:nth-of-type(' + (i + 1) +')').attr('data-th', text[i])
						}	
					});		
				});	
			    $('.list-group-item').click(function () {
			        $("html, body").animate({
			            scrollTop: 0
			        }, 600);
			        return false;
			    });
			    
			    $('table').DataTable().destroy();
				$('table').DataTable({
					"lengthMenu":lengthMenu,
					"searching": searching
				}); 
				mobiletable();
				 $('.tendworkdet').click(function(){
					 $('table').DataTable().destroy();
				    	$('.Procurementstender_details_containt').hide();
				    	$('.ReverseAuction_details_containt').hide();
				    	$('.ForwardAuction_details_containt').hide();
				    	$('.workstender_details_containt').show();
				    	$('.tendworkdet').addClass('cardactive');
				    	$('.tendprodet').removeClass('cardactive');
				    	$('.foauctdet').removeClass('cardactive');
				    	$('.reauctdet').removeClass('cardactive');
				    	$('table').DataTable({
							"lengthMenu":lengthMenu,
							"searching": searching
						}); 
				    	mobiletable();
				    	$($.fn.dataTable.tables(true)).DataTable().columns.adjust();
				    });
				    $('.tendprodet').click(function(){
				    	$('table').DataTable().destroy();
				    	$('.Procurementstender_details_containt').show();
				    	$('.ReverseAuction_details_containt').hide();
				    	$('.ForwardAuction_details_containt').hide();
				    	$('.workstender_details_containt').hide();
				    	$('.tendworkdet').removeClass('cardactive');
				    	$('.tendprodet').addClass('cardactive');
				    	$('.foauctdet').removeClass('cardactive');
				    	$('.reauctdet').removeClass('cardactive');
				    	$('table').DataTable({
							"lengthMenu":lengthMenu,
							"searching": searching
						}); 
				    	mobiletable();
				    	$($.fn.dataTable.tables(true)).DataTable().columns.adjust();
				    });
				    $('.foauctdet').click(function(){
				    	$('table').DataTable().destroy();
				    	$('.Procurementstender_details_containt').hide();
				    	$('.ReverseAuction_details_containt').hide();
				    	$('.ForwardAuction_details_containt').show();
				    	$('.workstender_details_containt').hide();
				    	$('.tendworkdet').removeClass('cardactive');
				    	$('.tendprodet').removeClass('cardactive');
				    	$('.foauctdet').addClass('cardactive');
				    	$('.reauctdet').removeClass('cardactive');
				    	$('table').DataTable({
							"lengthMenu":lengthMenu,
							"searching": searching
						}); 
				    	mobiletable();
				    	$($.fn.dataTable.tables(true)).DataTable().columns.adjust();
				    });
				    $('.reauctdet').click(function(){
				    	$('table').DataTable().destroy();
				    	$('.Procurementstender_details_containt').hide();
				    	$('.ReverseAuction_details_containt').show();
				    	$('.ForwardAuction_details_containt').hide();
				    	$('.workstender_details_containt').hide();
				    	$('.tendworkdet').removeClass('cardactive');
				    	$('.tendprodet').removeClass('cardactive');
				    	$('.foauctdet').removeClass('cardactive');
				    	$('.reauctdet').addClass('cardactive');
				    	$('table').DataTable({
							"lengthMenu":lengthMenu,
							"searching": searching
						}); 
				    	mobiletable();
				    	$($.fn.dataTable.tables(true)).DataTable().columns.adjust();
				    });
				    
				    $('#tahdrWorksTable').on("click",".viewTahdrDetail",function(){
						 debugger;
						var tahdrId =  $(this).data('thdrcode');
						$('#tndrCode').text($(this).text());
						submitToURL('getTAHDRDetailsById/'+tahdrId,'tahdrDetailsResponse');
						$("#ViewTenderDetailModal").modal('show');
					 }); 
			});



function getTenderByTypeCode(typeCode,selectedTableId){
	tableId=selectedTableId;
	$("#loading-wrapper").show();
	submitToURL('getLatestTahdrTypeCode/'+typeCode, 'loadTenders');
	$("#loading-wrapper").fadeOut("slow");
}
function loadTenders(data){
	setLoadTender(data,tableId);
}
function setLoadTender(data,tableId)
{
	debugger;
	$("#loading-wrapper").show();
	console.log(data);
	if(!$.isEmptyObject(data.DATA)){
		var data=data.DATA;
		 var res="";
		 $('#'+tableId).DataTable().destroy();
		 $('#'+tableId+' tbody').empty();
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
	    	 $('#'+tableId+' tbody').append('<tr><td><a href ="#" class = "viewTahdrDetail" data-thdrCode="'+data[i].tahdr.tahdrId+'">'+data[i].tahdr.tahdrCode+"</a></td>" +
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
	   
	     $('#'+tableId).DataTable();
   	  $($.fn.dataTable.tables(true)).DataTable().columns.adjust();
	     
	}
	}

function tahdrDetailsResponse(data){
	
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
				if(!isEmpty(tahdrDetail.officeNote)){
					$('#dwnldTahdrDtil').attr('href','./download/'+tahdrDetail.officeNote.attachmentId);
				}
			}else{
				if(tahdrDetail.estimatedCost < 800){
					if(!isEmpty(tahdrDetail.officeNote)){
						$('#dwnldTahdrDtil').attr('href','./download/'+tahdrDetail.officeNote.attachmentId);
					}
				}else{
					$('#dwnldTahdrDtil').hide();
				}
			}
			
		}
	}
}
