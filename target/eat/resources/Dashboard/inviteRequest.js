$(document).ready(function(){ 
	var typeCode='TENDER';
	renderList(typeCode);
	LoadReferenceList();
	/*$(".SearchPartners").hide();*/
	
	$('#auctionparticipate').DataTable();
	$('#Serchauctionparticipate').DataTable();
	
	$('.Invitation').click(function(){
		var typeCode=$('#filter li.active').data('documenttype');
		renderList(typeCode);
	});
	
	$('.filterByTahdrCode').click(function(){
		var typeCode=$(this).data('documenttype');
		$('#filter li.active').removeClass('active');
		$(this).addClass('active');
		renderList(typeCode);
	});
	
	$('#searchPartnerId').click(function(){
		$('#example li:not(.active)').remove();
		var tahdrId=$('#example li.active').data('id');
		/*var tahdrtype =$("#tahdrtype").val();
		if(tahdrtype == "RA"){
			$("#sellercheck").prop('checked', true);
		}else if(tahdrtype == "PT"){
			$("#sellercheck").prop('checked', true);
		}else{
			$("#buyercheck").prop('checked', true);
		}*/
		/*submitWithParam('getAllParners','tahdrtype', 'setListtotable');*/
		/*submitWithParam('getAllParners','tahdrtype', 'setListtotable');*/
		if(tahdrId!='' || tahdrId!=undefined){
			submitToURL('getEligiblePartnersByTahdrId/'+tahdrId, 'setListtotable');
		}
	});
	
	$('#InvitePartners').click(function(){
		$('#searchPartnerId').click();
	});
	
	$("#FilterUser").click(function(){
		var flg="";
		var email =$("#emailId").val();
		if(email==''){
			email='NULL';
		}
		var name =$("#partner_name").val();
		if(name==''){
			name='NULL';
		}
			if($("#sellercheck").prop("checked") && $("#buyercheck").prop("checked")){
				flg="SB"
			}
			if($("#sellercheck").prop("checked") && !$("#buyercheck").prop("checked")){
				flg="S"
			}
			if(!$("#sellercheck").prop("checked") && $("#buyercheck").prop("checked")){
				flg="B"
			}
		submitToURL("getFilteredPartners/"+email+"/"+name+"/"+flg,'setListtotable');
	});
	
	$("#sendauctioninvitationtopartner").click(function(){
		 $('#hiddenformID tbody').empty();
		var newRecord='';
		var aucid=$(".Id").val();
		var count=0;
		$("#aucid").val(aucid);
		$("input:checkbox[name='bpartnerlist']:checked").each(function(){
			 newRecord+='<tr><td><input type="text"  name="partners['+count+'].bPartnerId" value="'+$(this).val()+'"/></td></tr>';
			 $('#hiddenformID tbody').append(newRecord);
			 newRecord='';
			 count++;
		});
		var trCount=$('#hiddenformID tr').length;
		if(trCount!=0){
			submitIt("hiddenformID","invitationQuickRfqParticipant", "getvalue");
		}
		else{
			Alert.warn('No Partner Selected !');
		}
	});
	$("#addPartnerBtnId").click(function(){
		
	});
	
});
function reponseFilter(){
	
}

function renderList(typeCode){ 
	if(typeCode!='' || typeCode!=undefined){
		submitToURL("getTenderByTypeCode/"+typeCode, "renderListResp");
	}
}

function renderListResp(data){
	var typeCode=$('#filter li.active').data('documenttype');
	if($.isEmptyObject(data.objectMap.tenderList)){
	     swal("No "+typeCode);
		 $('#example').empty();
	     $('#searchPartnerId').addClass('readonly');
	}else{
  	appendList(data.objectMap.tenderList);
  	 $('#searchPartnerId').removeClass('readonly');
   }
}

function appendList(data){
	debugger;
	var active=" class='active'";
	$('#example').empty();
	var activeStatus="";
	for (var i=0;i<data.length;i++)  {
		if(data[i].isActive=="Y")
			activeStatus="Active";
		else
			activeStatus="InActive";
		
		var statusCode ;
		if(data[i].tahdrTypeCode == "PT" && data[i].isAuction =="Y"){
			statusCode ="RA";
        }else{
        	statusCode =data[i].tahdrTypeCode;
        }
		
				if(i==0){
					 $('#auctionparticipatetable tbody').empty();
					 $("#auctionparticipatetable").DataTable().destroy();
					
					 for(var j=0;j<data[i].auctionParticipant.length;j++){
						 var createdDate=formatDate(data[i].auctionParticipant[j].created)
						 var companyName=data[i].auctionParticipant[j]==null?'':data[i].auctionParticipant[j].bPartner.name==null?'':data[i].auctionParticipant[j].bPartner.name;
						 var pancard=data[i].auctionParticipant[j]==null?'':data[i].auctionParticipant[j].bPartner.panNumber==null?'':data[i].auctionParticipant[j].bPartner.panNumber;
						 var crnno=data[i].auctionParticipant[j]==null?'':data[i].auctionParticipant[j].bPartner.crnNumber==null?'':data[i].auctionParticipant[j].bPartner.crnNumber;
						
				
						 $('#auctionparticipatetable tbody').append('<tr><td>'+companyName+'</td>' +
				    			  '<td>'+pancard+'</td>' +
				    			  '<td>'+crnno+'</td>'+ 
				    			  '<td>'+createdDate+'</td>' +
				    			  
				    			 
				       	 '</tr>');
					 }
					$(".Id").val(data[i].tahdrId);
					$(".tahdrtype").val(statusCode)
					
			$('#masterDetails').empty();
			$('#masterDetails').append('<div class="col-sm-12"><label class="col-xs-6" ><h4 class="detail_Name">'+data[i].title+'</h4></label>'
		            +'<label class="col-xs-6 mytext detail_Code">'+data[i].tahdrCode+'</label></div> '
		            +'<div class="col-sm-12"><label class="col-xs-6 mytext detail_Desc">'+data[i].bidTypeCode+'</label>'
		            +'<label class="col-xs-6 mytext detail_Active">'+statusCode+'</label></div>');
			}
			
		$('#example').append('<li '+active+' data-id="'+data[i].tahdrId+'" onclick="showdetails_documenttype('+data[i].tahdrId+')"> <a href="" class="" data-toggle="tab">'
		           +' <div class="col-md-12">'
		           +'  <label class="col-xs-6">'+data[i].title+'</label>'
		           +'   <label class="col-xs-6 mytext" data-Id="'+data[i].tahdrId+'">'+data[i].tahdrCode+'</label>'
		           +'  </div>'
		           +'  <div class="col-md-12">'
		           +'    <label class="col-xs-6">'+data[i].bidTypeCode+'</label>'
		           +'    <label class="col-xs-6 mytext2">'+statusCode+'</label>'
		           +'  </div>'
		           +'  </a>'
		           +'  </li>');
		active="";
          }
	$('.example').paginathing({
		perPage : 7
	});		
		
		$(".tabs-left li a label").text(function(index, currentText) {
		    return currentText.substr(0, 20);
	});
		$('.reportCount').html(data.length);
	}
function showdetails_documenttype(id){
	
	$.ajax({
        type : "POST",
        contentType : "application/json",
        url: "getPrivateAuctionDetailsByID/"+id,
        dataType:"json",
        async:false,
        success: function (data) {
        	if($.isEmptyObject(data)){
        		  swal("Something Went Wrong");
        	}else{
        		 $('#auctionparticipatetable tbody').empty();
				  $("#auctionparticipatetable").DataTable().destroy();
				  var statusCode ;
					if(data.tahdrTypeCode == "PT" && data.isAuction =="Y"){
						statusCode ="RA";
			        }else{
			        	statusCode =data.tahdrTypeCode;
			        }
				 for(var j=0;j<data.auctionParticipant.length;j++){
					 var createdDate=formatDate(data.auctionParticipant[j].created)
					 var companyName=data.auctionParticipant[j]==null?'':data.auctionParticipant[j].bPartner.name==null?'':data.auctionParticipant[j].bPartner.name;
					 var pancard=data.auctionParticipant[j]==null?'':data.auctionParticipant[j].bPartner.panNumber==null?'':data.auctionParticipant[j].bPartner.panNumber;
					 var crnno=data.auctionParticipant[j]==null?'':data.auctionParticipant[j].bPartner.crnNumber==null?'':data.auctionParticipant[j].bPartner.crnNumber;
					
					 $('#auctionparticipatetable tbody').append('<tr><td>'+companyName+'</td>' +
			    			  '<td>'+pancard+'</td>' +
			    			  '<td>'+crnno+'</td>'+ 
			    			  '<td>'+createdDate+'</td>' +
			    			  
			    			 
			       	 '</tr>');
				 }
				$(".Id").val(data.tahdrId);
				$(".tahdrtype").val(statusCode)
				if(data.isActive=="Y")
				{
					$('.isActive').prop('checked', true);
					activeStatus="Active";
				}
				else
				{
					$('.isActive').prop('checked', false);
					activeStatus="InActive";
				}
				
				$(".detail_Name").html(data.title);
				$(".detail_Code").html(data.tahdrCode);
				$(".detail_Desc").html(data.bidTypeCode);
				$(".detail_Active").html(statusCode);			
				
        	}
		        	
        },
        error: function (e){
			swal("Exception :");
        }
    });
}

function getvalue(data){
	if(data.hasError==false){
		Alert.info(data.message);
		$('#hiddenformId tbody').empty();
		/*$(".SearchPartners").hide();*/
		/*$('.Invitation').click();*/
	}else{
		Alert.warn("Something went wrong");
	}
	
}

function setListtotable(data){
	if(!($.isEmptyObject(data))){
		appendBpartnerList(data);
	}else{
		$('#Serchauctionparticipate tbody').empty();
		$('#Serchauctionparticipate tbody').append('<tr><td colspan="14" style="text-align: center;"><b>"No Records Found"</b></td></tr>');
	}
}

function appendBpartnerList(data){
	 $("#Serchauctionparticipate").DataTable().destroy();
	  $("#Serchauctionparticipate tbody").empty();
	  
	 for(var i=0;i<data.length;i++){
		 var mobileNo=data[i].userDetails==null?'':data[i].userDetails.mobileNo==null?'':data[i].userDetails.mobileNo;
		 var bpaertnerid=data[i].partner==null?'':data[i].partner.bPartnerId==null?'':data[i].partner.bPartnerId;
		 var cname=data[i].partner==null?'':data[i].partner.name==null?'':data[i].partner.name;
		 var firstname=data[i].userDetails==null?'':data[i].userDetails.firstName==null?'':data[i].userDetails.firstName;
		 var pancode=data[i].partner==null?'':data[i].partner.panNumber==null?'':data[i].partner.panNumber;
		 var emailid=data[i]==null?'':data[i].email==null?'':data[i].email;
		 $('#Serchauctionparticipate tbody').append('<tr><td><input type="checkbox" class="BPDetails" name="bpartnerlist" value="'+bpaertnerid+'"></td>' +
  			  '<td>'+cname+'</td>' +
  			  '<td>'+firstname+'</td>'+ 
  			  '<td>'+pancode+'</td>' +
  			  '<td>'+emailid+'</td>' +
  			  '<td>'+mobileNo+'</td>' +
     	 '</tr>');
	 }
	 $('#Serchauctionparticipate').DataTable();
}

function onPageLoad(data){
	if(data.objectMap.hasOwnProperty('companyTypes')){
		loadReferenceListById('companytype',data.objectMap.companyTypes)
	}
}

function LoadReferenceList(){
 submitToURL("getRefrenceDropdownList", 'onPageLoad');
}