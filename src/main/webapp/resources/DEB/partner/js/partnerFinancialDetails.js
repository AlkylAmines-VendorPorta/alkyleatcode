var financialDetailsArray=new Array();
var eeClarifyFlag=false;
$(document).ready(function(){
	$('#cancelFinancialBtnId').click(function(event) {
		event.preventDefault();
		editMode=false;
	   	/*activeTabName="";*/
	   	var data=financialDetailsArray["FD"];
	   	if(data==undefined){
	    $('#partnerFinancialDetailsForm')[0].reset();
	    $("#partnerFinancialDetailsForm #pnlFile_0").val("");
	    $("#partnerFinancialDetailsForm #pnlFile_1").val("");
	    $("#partnerFinancialDetailsForm #pnlFile_2").val("");
	    $("#partnerFinancialDetailsForm #bsaFile_1").val("");
	    $("#partnerFinancialDetailsForm #bsaFile_2").val("");
	    $("#partnerFinancialDetailsForm #bsaFile_3").val("");
	    $("#partnerFinancialDetailsForm #tdFile_1").val("");
	   
	    $("#partnerFinancialDetailsForm #a_pnlFile_0").html("");
	    $("#partnerFinancialDetailsForm #a_pnlFile_1").html("");
	    $("#partnerFinancialDetailsForm #a_pnlFile_2").html("");
	    $("#partnerFinancialDetailsForm #a_bsaFile_1").html("");
	    $("#partnerFinancialDetailsForm #a_bsaFile_2").html("");
	    $("#partnerFinancialDetailsForm #a_bsaFile_3").html("");
	    $("#partnerFinancialDetailsForm #a_tdFile_1").html("");
	    $('.pnlFile_0').attr('disabled',true);
	    $('.pnlFile_1').attr('disabled',true);
	    $('.pnlFile_2').attr('disabled',true);
	    $('.bsaFile_1').attr('disabled',true);
	    $('.bsaFile_2').attr('disabled',true);
	    $('.bsaFile_3').attr('disabled',true);
	    $('.tdFile_1').attr('disabled',true);
	    
	   	}else{
	   		loadPartnerFinanceRightPane(data);
	   	}
	    
    });
	
	 $("#partnerFinancialDetailsForm").find("input,select,textarea").change(function() {
	   	 editMode=true;
	   	 activeTabName="Financial Details";
	 });
	 $("#partnerFinancialDetailsForm .fileDeleteBtn").click(function() {
	   	 editMode=true;
	   	 activeTabName="Financial Details";
	     requiredFileDeleted=true;
	 });
});

function getPartnerFinancialDetails(event,el){
	    event.preventDefault();
	    if(!editMode && !requiredFileDeleted){
	    	$('#pagination-here').empty();
	    	cacheLi();
	    	setCurrentTab(el);
	    	if(getChangedFlag()){
	          submitWithParam('getPartnerFinancialDetails','bPartnerId','onPartnerFinanceTabLoad');	
	          setChangedFlag(false);
	    	}else{
	    		getCacheLi();
	    	}
	        setActiveTabName("Financial Details", $('.leftPaneData li').length); 
	        setPartnerHeaderValues("Company Name: "+companyName, "Company Registration No. : "+registrationNo, "Vendor SAP Code : "+vendorSAPCode,"Status : "+partnerStatus);
	 	   /* setPartnerHeaderValues("Company Name: "+companyName, "Company Registration No. : "+registrationNo, "Company Type : "+companyType,"Status : "+partnerStatus);*/
        }else{
			event.stopPropagation();
	        Alert.warn("Please Save Changed Data Of "+activeTabName+" Tab");
		}
	    $("#filterBtnId").addClass("readonly");
	    checkForFilterByRole();
}

var financialYearArray = new Array();
function partnerFinanceResp(data){
	if(!isEmpty(data) && !isEmpty(data.responseDto)){
		Alert.info(data.responseDto.message);	
	}
}

function onPartnerFinanceTabClick(){
	submitToURL("getPartner", 'onCompContactTabLoad');	
}

function onPartnerFinanceTabLoad(data){
	   
	if(!isEmpty(data) && !isEmpty(data.objectMap)){
	    if(data.objectMap.hasOwnProperty('financialType')){
	    	if (!$.isEmptyObject(data.objectMap.financialType)){
	    	var list=data.objectMap.financialType;
	    	$.each(list,function(key,value){
	    		if(!isEmpty(value)){
	    			if(key=='PNL')
	    	    		$('.pnlType').attr('value',key);
	    	    		if(key=='BSA')
	    	    	    $('.bsaType').attr('value',key);
	    	    		if(key=='TD')
	    	    	    $('.tdType').attr('value',key);
	    		}
	    	});
	    	}
	    }
	    if(data.objectMap.hasOwnProperty('years')){
	    	   if (!$.isEmptyObject(data.objectMap.years)){
	    	    loadYears(data.objectMap.years);
	    	   }
	    	}
	    if(data.objectMap.hasOwnProperty('financialAttachments')){
	    	$('.pagination').children().remove();
	    	loadPartnerFinanceRightPane(data.objectMap.financialAttachments);
	    	$('.leftPaneData').paginathing();
	    	setPartnerHeaderValues("Company Name: "+companyName, "Company Registration No. : "+registrationNo, "Vendor SAP Code : "+vendorSAPCode,"Status : "+partnerStatus);
	    	/*setPartnerHeaderValues("Company Name: "+companyName, "Company Registration No. : "+registrationNo, "Company Type : "+companyType,"Status : "+partnerStatus);*/
		}
	}
	 $("#partnerFinancialDetailsForm input,select").removeClass("errorinput");
	    setActiveTabName("Financial Details", $('.leftPaneData li').length);
}

function loadPartnerFinanceRightPane(data){
   	
				$(".leftPaneData").html("");
				editMode=false;
				
				setChildLoadFlag(true);
				/*activeTabName="";*/
				if(!isEmpty(data)){
					$("#partnerFinancialDetailsForm  select").removeClass("requiredField");
					financialDetailsArray["FD"]=data;
					if($("#isManufacturer").val()=="Y" || $("#isTrader").val()=="Y")
							{
							    $("#partnerFinancialDetailsForm .red").show();
							    $("#partnerFinancialDetailsForm  input[type=file]").addClass("requiredFile");
							    $("#partnerFinancialDetailsForm  #tdAmountId").addClass("requiredField");
								$("#partnerFinancialDetailsForm  select").addClass("dropDown");
								
							}else{
								$("#partnerFinancialDetailsForm .red").hide();
							    $("#partnerFinancialDetailsForm  input[type=file]").removeClass("requiredFile");
							    $("#partnerFinancialDetailsForm  #tdAmountId").removeClass("requiredField");
								$("#partnerFinancialDetailsForm  select").removeClass("dropDown");
								
							}
	               
	                   
	                $.each(data,function(key,financeData){
	                	if(!isEmpty(financeData)){
	                		var attachmentId=financeData.attachment==null?'':financeData.attachment.attachmentId==null?'':financeData.attachment.attachmentId;
		    				var attachmentLabelId=financeData.attachment==null?'':financeData.attachment.fileName==null?'':financeData.attachment.fileName;	
		    				var financialYearId=financeData.financialYear==null?'':financeData.financialYear.financialYearId==null?'':financeData.financialYear.financialYearId;
		    				var isApproved=financeData.isApproved==null?'':financeData.isApproved;
		    				var remark=financeData.remark==null?'':financeData.remark;				
		    				var isEEApproved=financeData.isEEApproved==null?'':financeData.isEEApproved;
		    				var eeComment=financeData.eeComment==null?'':financeData.eeComment;
		    				var isCEApproved=financeData.isCEApproved==null?'':financeData.isCEApproved;
		    				var ceComment=financeData.ceComment==null?'':financeData.ceComment;
		    				
		                	if(financeData.finacialType=="PNL"){   
		                		/*var pnlYear=$("#pnlYearId_"+key).val();*/
		                		var pnlYear=$(".pnlYear");
		                		for(var i=0;i<pnlYear.length;i++){
		                			   
		                			if($("#pnlYearId_"+i).val()==financeData.financialYear.financialYearId){
		                				$("#partnerFinancialDetailsForm #pnlFinancialAttachmentId_"+i).val(financeData.financialAttachmentId);
		                				$("#partnerFinancialDetailsForm #pnlFile_"+i).val(attachmentId);
		                				setRadioBtn('ee_isApproved_'+i+'_1','ee_isApproved_'+i+'_2','ce_isApproved_'+i+'_1','ce_isApproved_'+i+'_2','approveEEDiv_'+i,isEEApproved,eeComment,isCEApproved,ceComment);
		                				var url=$("#a_pnlFile_"+i).data('url');
		                				$("#a_pnlFile_"+i).attr('href',url);
		                				var a_pnlFile = $("#partnerFinancialDetailsForm #a_pnlFile_"+i).prop('href')+'/'+attachmentId;
		                			    $("#partnerFinancialDetailsForm #a_pnlFile_"+i).prop('href', a_pnlFile);
		                			    if(financeData.response==null)
		                					{
		                			    	  $("#partnerFinancialDetailsForm #a_pnlFile_"+i).html(attachmentLabelId);
		                					}
		                				
		                			    $("#partnerFinancialDetailsForm #pnlType_"+i).val(financeData.finacialType);
		                				$("#partnerFinancialDetailsForm #pnlYearValidFrom_"+i).val(formatDate(financeData.validFrom));
		                				$("#partnerFinancialDetailsForm #pnlYearValidTo_"+i).val(formatDate(financeData.validTo));
		                				$("#partnerFinancialDetailsForm #pnlYearId_"+i).val(financialYearId);
		                				showFilesDelBtn(attachmentId,'pnlFile_'+i);
		                		
		                			}
		                		}
		                	}
		                	 if(financeData.finacialType=="BSA"){
		                     	var bsaYear=$(".bsaYear");
		                		for(var i=1;i<=bsaYear.length;i++){
		                			   
		                			if($("#bsaYearId_"+i).val()==financeData.financialYear.financialYearId){
		                				$("#partnerFinancialDetailsForm #bsaFinancialAttachmentId_"+i).val(financeData.financialAttachmentId);
		                				$("#partnerFinancialDetailsForm #bsaFile_"+i).val(attachmentId);
		                				setRadioBtn('ee_isApproved_'+i+'_1','ee_isApproved_'+i+'_2','ce_isApproved_'+i+'_1','ce_isApproved_'+i+'_2','approveEEDiv_'+i,isEEApproved,eeComment,isCEApproved,ceComment);
		                				var url=$("#a_bsaFile_"+i).data('url');
		                				$("#a_bsaFile_"+i).attr('href',url);
		                				var a_pnlFile = $("#partnerFinancialDetailsForm #a_bsaFile_"+i).prop('href')+'/'+attachmentId;
		                			    $("#partnerFinancialDetailsForm #a_bsaFile_"+i).prop('href', a_pnlFile);
		                			    if(financeData.response==null)
		                					{
		                			    	  $("#partnerFinancialDetailsForm #a_bsaFile_"+i).html(attachmentLabelId);
		                					}
		                				
		                			    $("#partnerFinancialDetailsForm #bsaType_"+i).val(financeData.finacialType);
		                				$("#partnerFinancialDetailsForm #bsaYearValidFrom_"+i).val(formatDate(financeData.validFrom));
		                				$("#partnerFinancialDetailsForm #bsaYearValidTo_"+i).val(formatDate(financeData.validTo));
		                				$("#partnerFinancialDetailsForm #bsaYearId_"+i).val(financialYearId);
		                				showFilesDelBtn(attachmentId,'bsaFile_'+i);
		                		
		                			}
		                		}
		                 	 }
		                	 if(financeData.finacialType=="TD"){
		                		 setRadioBtn('ee_isApproved_6_1','ee_isApproved_6_2','ce_isApproved_6_1','ce_isApproved_6_2','approveEEDiv_6',isEEApproved,eeComment,isCEApproved,ceComment);
									
		     					$("#partnerFinancialDetailsForm #tdFinancialAttachmentId_1").val(financeData.financialAttachmentId);
		     					$("#partnerFinancialDetailsForm #tdFile_1").val(attachmentId);
		     					
		     					var url=$("#a_tdFile_1").data('url');
		     					$("#a_tdFile_1").attr('href',url);
		     					var a_tdFile_1 = $("#partnerFinancialDetailsForm #a_tdFile_1").prop('href')+'/'+attachmentId;
		     				    $("#partnerFinancialDetailsForm #a_tdFile_1").prop('href', a_tdFile_1);
		     				    if(financeData.response==null)
		     				    {
		     				      $("#partnerFinancialDetailsForm #a_tdFile_1").html(attachmentLabelId);
		     				    }
		     				   
		     				    $("#partnerFinancialDetailsForm #tdType_1").val(financeData.finacialType);
		     					$("#partnerFinancialDetailsForm #tdAmountId").val(financeData.amount);
		     					showFilesDelBtn(attachmentId,'tdFile_1');  
		     					
		     					$("#partnerFinancialDetailsForm  #eeRemark_6").val(eeComment);
		     					$("#partnerFinancialDetailsForm  #ceRemark_6").val(ceComment);
		                	 }
	                	}
	                	
	                });	
				}
				
                
                
				/*var pnlFile_0=data[0].attachment==null?'':data[0].attachment.attachmentId==null?'':data[0].attachment.attachmentId;
				var pnlLabelId_0=data[0].attachment==null?'':data[0].attachment.fileName==null?'':data[0].attachment.fileName;	
				var pnlYearId_0=data[0].financialYear==null?'':data[0].financialYear.financialYearId==null?'':data[0].financialYear.financialYearId;
				
				var isApproved_0=data[0].isApproved==null?'':data[0].isApproved;
				var remark_0=data[0].remark==null?'':data[0].remark;				
				var isEEApproved_0=data[0].isEEApproved==null?'':data[0].isEEApproved;
				var eeComment_0=data[0].eeComment==null?'':data[0].eeComment;
				var isCEApproved_0=data[0].isCEApproved==null?'':data[0].isCEApproved;
				var ceComment_0=data[0].ceComment==null?'':data[0].ceComment;
				
				$("#partnerFinancialDetailsForm #pnlFinancialAttachmentId_0").val(data[0].financialAttachmentId);
				$("#partnerFinancialDetailsForm #pnlFile_0").val(pnlFile_0);
				setRadioBtn('ee_isApproved_0_1','ee_isApproved_0_2','ce_isApproved_0_1','ce_isApproved_0_2','approveEEDiv_0',isEEApproved_0,eeComment_0,isCEApproved_0,ceComment_0);
								
				var url=$("#a_pnlFile_0").data('url');
				$("#a_pnlFile_0").attr('href',url);
				var a_pnlFile_0 = $("#partnerFinancialDetailsForm #a_pnlFile_0").prop('href')+'/'+pnlFile_0;
			    $("#partnerFinancialDetailsForm #a_pnlFile_0").prop('href', a_pnlFile_0);
			    if(data[0].response==null)
					{
			    	  $("#partnerFinancialDetailsForm #a_pnlFile_0").html(pnlLabelId_0);
					}
				
			    $("#partnerFinancialDetailsForm #pnlType_0").val(data[0].finacialType);
				$("#partnerFinancialDetailsForm #pnlYearValidFrom_0").val(formatDate(data[0].validFrom));
				$("#partnerFinancialDetailsForm #pnlYearValidTo_0").val(formatDate(data[0].validTo));
				$("#partnerFinancialDetailsForm #pnlYearId_0").val(pnlYearId_0);
				showFilesDelBtn(pnlFile_0,'pnlFile_0');
		
				var pnlFile_1=data[1].attachment==null?'':data[1].attachment.attachmentId==null?'':data[1].attachment.attachmentId;
				var pnlLabelId_1=data[1].attachment==null?'':data[1].attachment.fileName==null?'':data[1].attachment.fileName;	
				var pnlYearId_1=data[1].financialYear==null?'':data[1].financialYear.financialYearId==null?'':data[1].financialYear.financialYearId;
				
				var isApproved_1=data[1].isApproved==null?'':data[1].isApproved;
				var remark_1=data[1].remark==null?'':data[1].remark;
				var isEEApproved_1=data[1].isEEApproved==null?'':data[1].isEEApproved;
				var eeComment_1=data[1].eeComment==null?'':data[1].eeComment;
				var isCEApproved_1=data[1].isCEApproved==null?'':data[1].isCEApproved;
				var ceComment_1=data[1].ceComment==null?'':data[1].ceComment;
				
				$("#partnerFinancialDetailsForm #pnlFinancialAttachmentId_1").val(data[1].financialAttachmentId);
				$("#partnerFinancialDetailsForm #pnlFile_1").val(pnlFile_1);
				setRadioBtn('ee_isApproved_1_1','ee_isApproved_1_2','ce_isApproved_1_1','ce_isApproved_1_2','approveEEDiv_1',isEEApproved_1,eeComment_1,isCEApproved_1,ceComment_1);
				
				var url=$("#a_pnlFile_1").data('url');
				$("#a_pnlFile_1").attr('href',url);
				var a_pnlFile_1 = $("#partnerFinancialDetailsForm #a_pnlFile_1").prop('href')+'/'+pnlFile_1;
			    $("#partnerFinancialDetailsForm #a_pnlFile_1").prop('href', a_pnlFile_1);
			    if(data[1].response==null)
			    {
			    	$("#partnerFinancialDetailsForm #a_pnlFile_1").html(pnlLabelId_1);
			    }
				
			    $("#partnerFinancialDetailsForm #pnlType_1").val(data[1].finacialType);
				$("#partnerFinancialDetailsForm #pnlYearValidFrom_1").val(formatDate(data[1].validFrom));
				$("#partnerFinancialDetailsForm #pnlYearValidTo_1").val(formatDate(data[1].validTo));
				$("#partnerFinancialDetailsForm #pnlYearId_1").val(pnlYearId_1);
				showFilesDelBtn(pnlFile_1,'pnlFile_1');
				
					var pnlFile_2=data[2].attachment==null?'':data[2].attachment.attachmentId==null?'':data[2].attachment.attachmentId;
					var pnlLabelId_2=data[2].attachment==null?'':data[2].attachment.fileName==null?'':data[2].attachment.fileName;	
					var pnlYearId_2=data[2].financialYear==null?'':data[2].financialYear.financialYearId==null?'':data[2].financialYear.financialYearId;
					
					var isApproved_2=data[2].isApproved==null?'':data[2].isApproved;
					var remark_2=data[2].remark==null?'':data[2].remark;
					var isEEApproved_2=data[2].isEEApproved==null?'':data[2].isEEApproved;
					var eeComment_2=data[2].eeComment==null?'':data[2].eeComment;
					var isCEApproved_2=data[2].isCEApproved==null?'':data[2].isCEApproved;
					var ceComment_2=data[2].ceComment==null?'':data[2].ceComment;
					
					$("#partnerFinancialDetailsForm #pnlFinancialAttachmentId_2").val(data[2].financialAttachmentId);
					$("#partnerFinancialDetailsForm #pnlFile_2").val(pnlFile_2);
					setRadioBtn('ee_isApproved_2_1','ee_isApproved_2_2','ce_isApproved_2_1','ce_isApproved_2_2','approveEEDiv_2',isEEApproved_2,eeComment_2,isCEApproved_2,ceComment_2);
					
					var url=$("#a_pnlFile_2").data('url');
					$("#a_pnlFile_2").attr('href',url);
					var a_pnlFile_2 = $("#partnerFinancialDetailsForm #a_pnlFile_2").prop('href')+'/'+pnlFile_2;
				    $("#partnerFinancialDetailsForm #a_pnlFile_2").prop('href', a_pnlFile_2);
				    if(data[2].response==null)
				    {
				      $("#partnerFinancialDetailsForm #a_pnlFile_2").html(pnlLabelId_2);
				    }
					
				    $("#partnerFinancialDetailsForm #pnlType_2").val(data[2].finacialType);
					$("#partnerFinancialDetailsForm #pnlYearValidFrom_2").val(formatDate(data[2].validFrom));
					$("#partnerFinancialDetailsForm #pnlYearValidTo_2").val(formatDate(data[2].validTo));
					$("#partnerFinancialDetailsForm #pnlYearId_2").val(pnlYearId_2);
					showFilesDelBtn(pnlFile_2,'pnlFile_2');
					
					var bsaFile_1=data[3].attachment==null?'':data[3].attachment.attachmentId==null?'':data[3].attachment.attachmentId;
					var bsaLabelId_1=data[3].attachment==null?'':data[3].attachment.fileName==null?'':data[3].attachment.fileName;	
					var bsaYearId_1=data[3].financialYear==null?'':data[3].financialYear.financialYearId==null?'':data[3].financialYear.financialYearId;

					var isApproved_3=data[3].isApproved==null?'':data[3].isApproved;
					var remark_3=data[3].remark==null?'':data[3].remark;
					var isEEApproved_3=data[3].isEEApproved==null?'':data[3].isEEApproved;
					var eeComment_3=data[3].eeComment==null?'':data[3].eeComment;
					var isCEApproved_3=data[3].isCEApproved==null?'':data[3].isCEApproved;
					var ceComment_3=data[3].ceComment==null?'':data[3].ceComment;
					
					$("#partnerFinancialDetailsForm #bsaFinancialAttachmentId_1").val(data[3].financialAttachmentId);
					$("#partnerFinancialDetailsForm #bsaFile_1").val(bsaFile_1);
					setRadioBtn('ee_isApproved_3_1','ee_isApproved_3_2','ce_isApproved_3_1','ce_isApproved_3_2','approveEEDiv_3',isEEApproved_3,eeComment_3,isCEApproved_3,ceComment_3);
									
					var url=$("#a_bsaFile_1").data('url');
					$("#a_bsaFile_1").attr('href',url);
					var a_bsaFile_1 = $("#partnerFinancialDetailsForm #a_bsaFile_1").prop('href')+'/'+bsaFile_1;
				    $("#partnerFinancialDetailsForm #a_bsaFile_1").prop('href', a_bsaFile_1);
				    if(data[3].response==null)
				    {
				       $("#partnerFinancialDetailsForm #a_bsaFile_1").html(bsaLabelId_1);
				    }
				    
				    $("#partnerFinancialDetailsForm #bsaType_1").val(data[3].finacialType);
					$("#partnerFinancialDetailsForm #bsaYearValidFrom_1").val(formatDate(data[3].validFrom));
					$("#partnerFinancialDetailsForm #bsaYearValidTo_1").val(formatDate(data[3].validTo));
					$("#partnerFinancialDetailsForm #bsaYearId_1").val(bsaYearId_1);
					showFilesDelBtn(bsaFile_1,'bsaFile_1');
					
					
					var bsaFile_2=data[4].attachment==null?'':data[4].attachment.attachmentId==null?'':data[4].attachment.attachmentId;
					var bsaLabelId_2=data[4].attachment==null?'':data[4].attachment.fileName==null?'':data[4].attachment.fileName;	
					var bsaYearId_2=data[4].financialYear==null?'':data[4].financialYear.financialYearId==null?'':data[4].financialYear.financialYearId;
					
					var isApproved_4=data[4].isApproved==null?'':data[4].isApproved;
					var remark_4=data[4].remark==null?'':data[4].remark;
					
					var isEEApproved_4=data[4].isEEApproved==null?'':data[4].isEEApproved;
					var eeComment_4=data[4].eeComment==null?'':data[4].eeComment;
					var isCEApproved_4=data[4].isCEApproved==null?'':data[4].isCEApproved;
					var ceComment_4=data[4].ceComment==null?'':data[4].ceComment;
					
					$("#partnerFinancialDetailsForm #bsaFinancialAttachmentId_2").val(data[4].financialAttachmentId);
					$("#partnerFinancialDetailsForm #bsaFile_2").val(bsaFile_2);
					setRadioBtn('ee_isApproved_4_1','ee_isApproved_4_2','ce_isApproved_4_1','ce_isApproved_4_2','approveEEDiv_4',isEEApproved_4,eeComment_4,isCEApproved_4,ceComment_4);
					
					var url=$("#a_bsaFile_2").data('url');
					$("#a_bsaFile_2").attr('href',url);
					var a_bsaFile_2 = $("#partnerFinancialDetailsForm #a_bsaFile_2").prop('href')+'/'+bsaFile_2;
				    $("#partnerFinancialDetailsForm #a_bsaFile_2").prop('href', a_bsaFile_2);
				    if(data[4].response==null)
				    {
				      $("#partnerFinancialDetailsForm #a_bsaFile_2").html(bsaLabelId_2);
				    }
					
				    $("#partnerFinancialDetailsForm #bsaType_2").val(data[4].finacialType);
					$("#partnerFinancialDetailsForm #bsaYearValidFrom_2").val(formatDate(data[4].validFrom));
					$("#partnerFinancialDetailsForm #bsaYearValidTo_2").val(formatDate(data[4].validTo));
					$("#partnerFinancialDetailsForm #bsaYearId_2").val(bsaYearId_2);
					showFilesDelBtn(bsaFile_2,'bsaFile_2');
					
					
					var bsaFile_3=data[5].attachment==null?'':data[5].attachment.attachmentId==null?'':data[5].attachment.attachmentId;
					var bsaLabelId_3=data[5].attachment==null?'':data[5].attachment.fileName==null?'':data[5].attachment.fileName;	
					var bsaYearId_3=data[5].financialYear==null?'':data[5].financialYear.financialYearId==null?'':data[5].financialYear.financialYearId;
					
					var isApproved_5=data[5].isApproved==null?'':data[5].isApproved;
					var remark_5=data[5].remark==null?'':data[5].remark;
					var isEEApproved_5=data[5].isEEApproved==null?'':data[5].isEEApproved;
					var eeComment_5=data[5].eeComment==null?'':data[5].eeComment;
					var isCEApproved_5=data[5].isCEApproved==null?'':data[5].isCEApproved;
					var ceComment_5=data[5].ceComment==null?'':data[5].ceComment;
					
					setRadioBtn('ee_isApproved_5_1','ee_isApproved_5_2','ce_isApproved_5_1','ce_isApproved_5_2','approveEEDiv_5',isEEApproved_5,eeComment_5,isCEApproved_5,ceComment_5);
					
					$("#partnerFinancialDetailsForm #bsaFinancialAttachmentId_3").val(data[5].financialAttachmentId);
					$("#partnerFinancialDetailsForm #bsaFile_3").val(bsaFile_3);
					
					var url=$("#a_bsaFile_3").data('url');
					$("#a_bsaFile_3").attr('href',url);
					var a_bsaFile_3 = $("#partnerFinancialDetailsForm #a_bsaFile_3").prop('href')+'/'+bsaFile_3;
				    $("#partnerFinancialDetailsForm #a_bsaFile_3").prop('href', a_bsaFile_3);
				    if(data[5].response==null)
				    {
				      $("#partnerFinancialDetailsForm #a_bsaFile_3").html(bsaLabelId_3);
				    }
				    
				    $("#partnerFinancialDetailsForm #bsaType_3").val(data[5].finacialType);
					$("#partnerFinancialDetailsForm #bsaYearValidFrom_3").val(formatDate(data[5].validFrom));
					$("#partnerFinancialDetailsForm #bsaYearValidTo_3").val(formatDate(data[5].validTo));
					$("#partnerFinancialDetailsForm #bsaYearId_3").val(bsaYearId_3);
					showFilesDelBtn(bsaFile_3,'bsaFile_3');
					
					
					var tdFile_1=data[6].attachment==null?'':data[6].attachment.attachmentId==null?'':data[6].attachment.attachmentId;
					var tdLabelId_1=data[6].attachment==null?'':data[6].attachment.fileName==null?'':data[6].attachment.fileName;	
					
					var isApproved_6=data[6].isApproved==null?'':data[6].isApproved;
					var remark_6=data[6].remark==null?'':data[6].remark;
					var isEEApproved_6=data[6].isEEApproved==null?'':data[6].isEEApproved;
					var eeComment_6=data[6].eeComment==null?'':data[6].eeComment;
					var isCEApproved_6=data[6].isCEApproved==null?'':data[6].isCEApproved;
					var ceComment_6=data[6].ceComment==null?'':data[6].ceComment;
					
					setRadioBtn('ee_isApproved_6_1','ee_isApproved_6_2','ce_isApproved_6_1','ce_isApproved_6_2','approveEEDiv_6',isEEApproved_6,eeComment_6,isCEApproved_6,ceComment_6);
										
					$("#partnerFinancialDetailsForm #tdFinancialAttachmentId_1").val(data[6].financialAttachmentId);
					$("#partnerFinancialDetailsForm #tdFile_1").val(tdFile_1);
					
					var url=$("#a_tdFile_1").data('url');
					$("#a_tdFile_1").attr('href',url);
					var a_tdFile_1 = $("#partnerFinancialDetailsForm #a_tdFile_1").prop('href')+'/'+tdFile_1;
				    $("#partnerFinancialDetailsForm #a_tdFile_1").prop('href', a_tdFile_1);
				    if(data[6].response==null)
				    {
				      $("#partnerFinancialDetailsForm #a_tdFile_1").html(tdLabelId_1);
				    }
				   
				    $("#partnerFinancialDetailsForm #tdType_1").val(data[6].finacialType);
					$("#partnerFinancialDetailsForm #tdAmountId").val(data[6].amount);
					showFilesDelBtn(tdFile_1,'tdFile_1');  
*/					
                setPartnerHeaderValues("Company Name: "+companyName, "Company Registration No. : "+registrationNo, "Vendor SAP Code : "+vendorSAPCode,"Status : "+partnerStatus);
					/*setPartnerHeaderValues("Company Name: "+companyName, "Company Registration No. : "+registrationNo, "Company Type : "+companyType,"Status : "+partnerStatus);*/
}
function partnerFinancialDetailsResp(data){
	setChildLoadFlag(true);
	editMode=false;
	activeTabName="";
	requiredFileDeleted=false;
	eeClarifyFlag=false;
	if(!isEmpty(data)){
		loadPartnerFinanceRightPane(data);
		Alert.info(data[0].response.message);	
	}
	/*setPartnerHeaderValues("Company Name: "+companyName, "Company Registration No. : "+registrationNo, "Company Type : "+companyType,"Status : "+partnerStatus);*/
	setPartnerHeaderValues("Company Name: "+companyName, "Company Registration No. : "+registrationNo, "Vendor SAP Code : "+vendorSAPCode,"Status : "+partnerStatus);

}
function loadYears(data){
      if(!isEmpty(data)){
    	  $.each(data,function(key,value){
    		  if(!isEmpty(value)){
    			  financialYearArray["FY"+value.financialYearId]=value;	
    		  }
    	   });
    	   $("#partnerFinancialDetailsForm .loadYears").html("");
    	   $("#partnerFinancialDetailsForm .loadYears").addClass("readonly");
    	   $("#pnlYearId_0").append('<option value="'+data[0].financialYearId+'">'+data[0].name+'</option>');
    	   $("#pnlYearId_0").val(data[0].financialYearId);
    	   getYearValidDate('pnlYearId_0','pnlYearValidFrom_0','pnlYearValidTo_0');
    	   $("#pnlYearId_1").append('<option value="'+data[1].financialYearId+'">'+data[1].name+'</option>');
    	   $("#pnlYearId_1").val(data[1].financialYearId);
    	   getYearValidDate('pnlYearId_1','pnlYearValidFrom_1','pnlYearValidTo_1');
    	   $("#pnlYearId_2").append('<option value="'+data[2].financialYearId+'">'+data[2].name+'</option>');
    	   $("#pnlYearId_2").val(data[2].financialYearId);
    	   getYearValidDate('pnlYearId_2','pnlYearValidFrom_2','pnlYearValidTo_2');
    	   $("#bsaYearId_1").append('<option value="'+data[0].financialYearId+'">'+data[0].name+'</option>');
    	   $("#bsaYearId_1").val(data[0].financialYearId);
    	   getYearValidDate('bsaYearId_1','bsaYearValidFrom_1','bsaYearValidTo_1');
    	   $("#bsaYearId_2").append('<option value="'+data[1].financialYearId+'">'+data[1].name+'</option>');
    	   $("#bsaYearId_2").val(data[1].financialYearId);
    	   getYearValidDate('bsaYearId_2','bsaYearValidFrom_2','bsaYearValidTo_2');
    	   $("#bsaYearId_3").append('<option value="'+data[2].financialYearId+'">'+data[2].name+'</option>');
    	   $("#bsaYearId_3").val(data[2].financialYearId);
    	   getYearValidDate('bsaYearId_3','bsaYearValidFrom_3','bsaYearValidTo_3'); 
      }
}
function getYearValidDate(id,firstParamField,secondParamField){
	var val=$("#"+id).val();
	var data=financialYearArray["FY"+val];
	if(!isEmpty(data)){
		$("#"+firstParamField).val(formatDate(data.validfrom));
		$("#"+secondParamField).val(formatDate(data.validTo));
	}
}

function checkYearValue(id,firstParamField,secondParamField){
	var selectedValue=$("#"+id).val();
	var firstValue=$("#"+firstParamField).val();
	var secondValue=$("#"+secondParamField).val();
	if(selectedValue==firstValue || selectedValue==secondValue){
		   $("#"+id).val("");
		   Alert.warn("Financial Year Is Already Selected");
		}
}

function firstPNLAttachmentDeleteResp(data){
	if(!isEmpty(data)){
		if(!data.hasError){		
		       $('#pnlFileId_0').val('');
			   $('#pnlFile_0').val('');
			   $('#a_pnlFile_0').html('');
			   $('.pnlFile_0').attr('disabled',true);
			   Alert.info(data.message);
			   financialDetailsArray["FD"][0].attachment=null;
			  
		    }else{
		    	Alert.warn("Attachment Is Already In Use");
		    }
	}
}

function secondPNLAttachmentDeleteResp(data){
	if(!isEmpty(data)){
	if(!data.hasError){		
       $('#pnlFileId_1').val('');
	   $('#pnlFile_1').val('');
	   $('#a_pnlFile_1').html('');
	   $('.pnlFile_1').attr('disabled',true);
	   Alert.info(data.message);
	   financialDetailsArray["FD"][1].attachment=null;
    }else{
    	Alert.warn("Attachment Is Already In Use");
    }
	}
}
function thirdPNLAttachmentDeleteResp(data){
	if(!isEmpty(data)){
	if(!data.hasError){		
       $('#pnlFileId_2').val('');
	   $('#pnlFile_2').val('');
	   $('#a_pnlFile_2').html('');
	   $('.pnlFile_2').attr('disabled',true);
	   Alert.info(data.message);
	   financialDetailsArray["FD"][2].attachment=null;
    }else{
    	Alert.warn("Attachment Is Already In Use");
    }
	}
}

function firstBSAAttachmentDeleteResp(data){
	if(!isEmpty(data)){
	if(!data.hasError){		
       $('#bsaFileId_1').val('');
	   $('#bsaFile_1').val('');
	   $('#a_bsaFile_1').html('');
	   $('.bsaFile_1').attr('disabled',true);
	   Alert.info(data.message);
	   financialDetailsArray["FD"][3].attachment=null;
    }else{
    	Alert.warn("Attachment Is Already In Use");
    }
	}
}
function secondBSAAttachmentDeleteResp(data){
	if(!isEmpty(data)){
	if(!data.hasError){		
       $('#bsaFileId_2').val('');
	   $('#bsaFile_2').val('');
	   $('#a_bsaFile_2').html('');
	   $('.bsaFile_2').attr('disabled',true);
	   Alert.info(data.message);
	   financialDetailsArray["FD"][4].attachment=null;
    }else{
    	Alert.warn("Attachment Is Already In Use");
    }
	}
}

function thirdBSAAttachmentDeleteResp(data){
	if(!isEmpty(data)){
	if(!data.hasError){		
       $('#bsaFileId_3').val('');
	   $('#bsaFile_3').val('');
	   $('#a_bsaFile_3').html('');
	   $('.bsaFile_3').attr('disabled',true);
	   Alert.info(data.message);
	   financialDetailsArray["FD"][5].attachment=null;
    }else{
    	Alert.warn("Attachment Is Already In Use");
    }
	}
}

function loadTDAttachmentDeleteResp(data){
	if(!isEmpty(data)){
	if(!data.hasError){		
       $('#tdFileId_1').val('');
	   $('#tdFile_1').val('');
	   $('#a_tdFile_1').html('');
	   $('.tdFile_1').attr('disabled',true);
	   Alert.info(data.message);
	   financialDetailsArray["FD"][6].attachment=null;
    }else{
    	Alert.warn("Attachment Is Already In Use");
    }
	}
}

function showFilesDelBtn(fileId,fieldClass){
	   
  if(fileId!=''){
	    $("."+fieldClass).attr('disabled',false);
	  }else{
		  $("."+fieldClass).attr('disabled',true);
	  }
}

function setRadioBtn(eeApproveBtnId,eeClarifyBtnId,ceApproveBtnId,ceClarifyBtnId,eeApproveDivId,isEEApproved,eeComment,isCEApproved,ceComment){
	   
	$("#partnerFinancialDetailsForm .approveEEDiv").find('input:radio, textarea').removeClass('readonly');
	$("#partnerFinancialDetailsForm .approveCEDiv").find('input:radio, textarea').removeClass('readonly');
	$("#partnerFinancialDetailsForm .approveEEDiv").find('input:radio, textarea').css("background-color","#FFF");
	$("#partnerFinancialDetailsForm .approveCEDiv").find('input:radio, textarea').css("background-color","#FFF");
	
	/* $("#partnerFinancialDetailsForm .approveEEDiv").find('input:radio, textarea').removeAttr('disabled','disabled');
     $("#partnerFinancialDetailsForm .approveCEDiv").find('input:radio, textarea').removeAttr('disabled','disabled');
    */ /*$("#partnerFinancialDetailsForm .approveEEDiv").hide();
     $("#partnerFinancialDetailsForm .approveCEDiv").hide();*/
	
	/*$("#partnerFinancialDetailsForm  #eeRemark_6").val(eeComment);
	$("#partnerFinancialDetailsForm  #ceRemark_6").val(ceComment);*/
		
	var partnerData =  $("#partnerData").val();
	var role=$("#roleData").val();
	if(role=='EXEENGR')
		{
	         $("#partnerFinancialDetailsForm .approveEEDiv").show();
	         $("#partnerFinancialDetailsForm .approveCEDiv").hide();
	         $("#partnerFinancialDetailsForm .approveEEDiv").find('input:radio, textarea').removeClass('readonly');
	         $("#partnerFinancialDetailsForm .approveEEDiv").find('input:radio, textarea').css("background-color","#FFF");
	         $("#partnerFinancialDetailsForm .approveCEDiv").find('input:radio, textarea').addClass('readonly');
	         $("#partnerFinancialDetailsForm .approveCEDiv").find('input:radio, textarea').css("background-color","#DADCE2");
			
			/* $("#partnerFinancialDetailsForm .approveEEDiv").find('input:radio, textarea').removeAttr('disabled','disabled');*/
		     $("#partnerFinancialDetailsForm .approveCEDiv").find('input:radio, textarea').attr('disabled','disabled');
		     if(isCEApproved!="")
		    	 {
		    	   $("#partnerFinancialDetailsForm .approveCEDiv").show();
		    	   $("#partnerFinancialDetailsForm .approveCEDiv").find('input:radio, textarea').removeAttr('disabled','disabled');
		    	 }
		}else if(role=='CHFENGR'){
			
			 $("#partnerFinancialDetailsForm .approveCEDiv").find('input:radio, textarea').removeAttr('disabled','disabled');
			 $("#partnerFinancialDetailsForm .approveCEDiv").show();
			 $("#partnerFinancialDetailsForm .approveEEDiv").show();
			/* $("#partnerFinancialDetailsForm .approveEEDiv").find('input:radio, textarea').attr('disabled','disabled');
		     $("#partnerFinancialDetailsForm .approveCEDiv").find('input:radio, textarea').removeAttr('disabled','disabled');
*/		
			 $("#partnerFinancialDetailsForm .approveCEDiv").find('input:radio, textarea').removeClass('readonly');
	         $("#partnerFinancialDetailsForm .approveCEDiv").find('input:radio, textarea').css("background-color","#FFF");
	         $("#partnerFinancialDetailsForm .approveEEDiv").find('input:radio, textarea').addClass('readonly');
	         $("#partnerFinancialDetailsForm .approveEEDiv").find('input:radio, textarea').css("background-color","#DADCE2");
				 
		}
	if(isCEApproved=="Y"|| isCEApproved=="")
		{
		     $("#"+ceApproveBtnId).prop('checked',true);
		     $("#"+ceClarifyBtnId).prop('checked',false);
		}else if(isCEApproved=="C"){
			 $("#"+ceApproveBtnId).prop('checked',false);
		     $("#"+ceClarifyBtnId).prop('checked',true);
	  }
	 if(isEEApproved=='Y' || isEEApproved==""){
		 $("#"+eeApproveBtnId).prop('checked',true);
	     $("#"+eeClarifyBtnId).prop('checked',false);
	 }else if(isEEApproved=='C'){
		 $("#"+eeApproveBtnId).prop('checked',false);
	     $("#"+eeClarifyBtnId).prop('checked',true);
	  }  
     if(partnerData=="partnerRegistration")
       {
    	 /*$("#partnerFinancialDetailsForm .approveEEDiv").find('input:radio, textarea').attr('disabled','disabled');
	     $("#partnerFinancialDetailsForm .approveCEDiv").find('input:radio, textarea').attr('disabled','disabled');
	    */ 
    	   $("#partnerFinancialDetailsForm .approveEEDiv").find('input:radio, textarea').addClass('readonly');
		   $("#partnerFinancialDetailsForm .approveCEDiv").find('input:radio, textarea').addClass('readonly');
		   $("#partnerFinancialDetailsForm .approveEEDiv").find('input:radio, textarea').css("background-color","#DADCE2");
		   $("#partnerFinancialDetailsForm .approveCEDiv").find('input:radio, textarea').css("background-color","#DADCE2");
		   $("#partnerFinancialDetailsForm .approveCEDiv").hide();
		   $("#partnerFinancialDetailsForm #"+eeApproveDivId).hide();
		   /*$("#partnerFinancialDetailsForm #eeCommentDivId").hide();*/
		      /*if(isCEApproved!="")
		    	  {
		    	  if(isCEApproved!="Y"){
		    	    $("#partnerFinancialDetailsForm .approveCEDiv").show();
			        $("#partnerFinancialDetailsForm .statusBtn").attr('disabled','disabled');
		    	  }
		    	  }*/
	     if(isEEApproved!=""){
       	  if(isEEApproved=="C"){
       	  eeClarifyFlag=true;
   		  $("#partnerFinancialDetailsForm #"+eeApproveDivId).show();
   		  $("#partnerFinancialDetailsForm #eeCommentDivId").show();
		  /*$("#partnerFinancialDetailsForm .statusBtn").attr('disabled','disabled');*/
   		  $("#partnerFinancialDetailsForm .statusBtn").addClass('readonly');
       	  }else{
       		$("#partnerFinancialDetailsForm #"+eeApproveDivId).hide();
       	  }
   	  }
	     if(!eeClarifyFlag)
	    	 {
	    	   $("#partnerFinancialDetailsForm #eeCommentDivId").hide();
	    	 }
   }
}

function checkAmount(){
	var amount=$("#tdAmountId").val();
	if(amount==""){
		 $("#tdAmountId").val(0);
		}
}