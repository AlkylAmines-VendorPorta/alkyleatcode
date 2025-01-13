var tahdrDetailStdDocs;
var count=9;
var customDocCount=0;
var next = 1;
//var plugin_index = $('.paginate').length;
$(document).ready(function(){
	$("#tenderStdCustDocsForm").on("change",".standardCustomDoc",function(){
		if($(this).prop("checked")){
			var li=	'<li id='+$(this).data("value")+'  class="ui-state-default"><span class="ui-icon ui-icon-arrowthick-2-n-s"></span>'+$(this).data('name');
				li+='<input type="hidden" class="sequence"  value="'+$("#orderTenderDocs li").length+'" >';
				li+='</li>';
			$("#orderTenderDocs").append(li);
			
		}else{
			$("#orderTenderDocs #"+$(this).data("value")).remove();
			var stdCustDocId=$("#stdCstDoc_"+$(this).attr('id')).val();
			if($.isEmptyObject()){
				removeStdCustDoc(stdCustDocId);
			}
		}
	});
	
	$(".saveTenderDocs").on("click",function(event){
		debugger;
		event.preventDefault();
		prepareForm();
		/*test*/
		var anyBoxesChecked = false;

		/*if (customCheck == false) {
		      Alert.warn("Add atleast one Standard Documents");
		    }
		*/ 
	    $('#saveStdCstDocForm input[type="checkbox"]').each(function() {
	        if ($(this).is(":checked")) {
	            anyBoxesChecked = true;
	        }
	    });
	 
	    if (anyBoxesChecked == false) {
	      Alert.warn("Add atleast one Standard Documents");
	    } 
	    else if(anyBoxesChecked == true){
	    	 submitIt('saveStdCstDocForm','saveStdCstDoc','saveTenderDocsResp')
	    }
		
	});
	
	$("#tenderDocTab").on("click",function(event){
		event.preventDefault();
		cacheLi();
		setCurrentTab(this);
		getStdCustDosc();
	});
	
	$("#customDocsDiv").on("click",".addMoreCM",function(event){
	event.preventDefault();
	
    $(this).removeClass('addMoreCM');
    $(this).removeClass('btn-default');
    $(this).addClass('removeMoreCM');
    $(this).addClass('btn-danger');
    $(this).html('Remove')
    addNewCMDiv();
	});
	
    $("#customDocsDiv").on("click",".removeMoreCM",function(event){
		event.preventDefault();
		var checkEle=$(this).parent().parent().find(".standardCustomDoc");
		$("#orderTenderDocs #"+$(checkEle).data("value")).remove();
		if(checkEle.prop("checked")){
			var checkId=$("#stdCstDoc_"+$(checkEle).attr('id')).val();
			if(!$.isEmptyObject(checkId)){
				removeStdCustDoc(checkId);	
			}
		}
    	$(this).parent().parent().remove();
    });

	$(".cancelTenderDocs").on("click",function(event){
		event.preventDefault();
		populateTenderDoc(tahdrDetailStdDocs);
	});
	
	/*$('#cancelTahdrDetail').click(function(event) {
		event.preventDefault();
		var activeTahdrDetailId=$('.leftPaneData').find('li.active').attr('id');
		if(activeTahdrDetailId!=undefined){
			loadTahdrDetailToRightPane(tahdrDetailArray["tahdrDetail"+activeTahdrDetailId]);
		}else
			$('#TAHDRDetail')[0].reset();
    });*/
	
	$("#customDocsDiv").on("change",".standardCustomDocuments2",function(){
		var idCode=$(this).data('id').split("_");
		$("#"+idCode[1]).removeAttr("disabled");
	});
});

function getStdCustDosc(){
	if(getChangedFlag()){
		tahdrDetailStdDocs=[];
		var id=$(".tahdrDetailId").val();
		$('.pagination').children().remove();
		var tahdrDetail=fetchList('getStdCstDoc',id);
		/*var activeStatus=tahdrDetail.isActive==null?'':tahdrDetail.isActive;*/
		/*if(activeStatus=='Y'){
			if (tenderStatus == 'Drafted'){
				$('.standardCustomDocuments').removeClass('readonly');
				$('.standardCustomDocuments2').removeAttr('disabled','disabled');
				$('#customDocsDiv').removeClass('readonly');
				$('.addMoreCM').removeClass('readonly');
				$('.removeMoreCM').removeClass('readonly');
				$('#saveStdCstDocForm .save').show();
				$('#saveStdCstDocForm .cancel').show();
			}
			else if(tenderStatus=='Approved'){
				$('.standardCustomDocuments').addClass('readonly');
				$('.standardCustomDocuments2').attr('disabled','disabled');
				$('#customDocsDiv').addClass('readonly');
				$('.addMoreCM').addClass('readonly');
				$('.removeMoreCM').addClass('readonly');
				$('#saveStdCstDocForm .save').hide();
				$('#saveStdCstDocForm .cancel').hide();
			}
			else{
				$('.standardCustomDocuments').addClass('readonly');
				$('.standardCustomDocuments2').attr('disabled','disabled');
				$('#customDocsDiv').addClass('readonly');
				$('.addMoreCM').addClass('readonly');
				$('.removeMoreCM').addClass('readonly');
				$('#saveStdCstDocForm .save').hide();
				$('#saveStdCstDocForm .cancel').hide();
			}
			}
			else if(activeStatus=='N'){
				$('.standardCustomDocuments').addClass('readonly');
				$('.standardCustomDocuments2').attr('disabled','disabled');
				$('#customDocsDiv').addClass('readonly');
				$('.addMoreCM').addClass('readonly');
				$('.removeMoreCM').addClass('readonly');
				$('#saveStdCstDocForm .save').hide();
				$('#saveStdCstDocForm .cancel').hide();
			}*/
		if(!$.isEmptyObject(tahdrDetail)){
			tahdrDetailStdDocs=tahdrDetail.standardCustomDoc;
		}
		populateTenderDoc(tahdrDetailStdDocs);
		$('#leftPane').paginathing({perPage: 6});
	 $(".tabs-left li a label").text(function(index, currentText) {
		    return currentText.substr(0, 20);
		});	
	    
	   /* plugin.init();*/
	    
		setChangedFlag(true);
	}else{
		getCacheLi();
	}
}

function saveTenderDocsResp(resp){
	if(!$.isEmptyObject(resp)){
		if(!$.isEmptyObject(resp.response)){
			if(resp.response.hasError){
				Alert.warn(resp.response.message);
			}else{
				Alert.info(resp.response.message);
			}
		}
	$.each(resp.standardCustomDoc,function(idx,stdCstDoc){
		$('#stdCstDoc_'+stdCstDoc.code).val(stdCstDoc.standardCustomDocId);
	});
	}
	setChildLoadFlag(true);
}

function populateTenderDoc(standardCustomDoc){
	debugger;
	customDocCount=0;
	$("#saveStdCstDocForm")[0].reset();
	$("#orderTenderDocs").empty();
	$("#customDocsDiv").empty();
	$( "#orderTenderDocs" ).sortable();
    $( "#orderTenderDocs" ).disableSelection();
    var documentType=$(".documentType").val();
	setHeaderValues(documentType+"No.: "+tahdrCodes, documentType+"Title : "+title, "Department : "+department, "Status : "+tenderStatus);
	if(!$.isEmptyObject(standardCustomDoc)){
		$.each(standardCustomDoc,function(idx,stdCstDoc){
			if(stdCstDoc.code.startsWith("CM")){
				populateCustomDocs(stdCstDoc);
			}
			if(stdCstDoc.isActive=='Y'){
				$('#'+stdCstDoc.code).val(stdCstDoc.isActive);
				$('#'+stdCstDoc.code).prop('checked',true);
				
				var li=	'<li id='+$('#'+stdCstDoc.code).data("value")+'  class="ui-state-default"><span class="ui-icon ui-icon-arrowthick-2-n-s"></span>'+$('#'+stdCstDoc.code).data("name");
				li+='<input type="hidden" class="sequence" value="'+stdCstDoc.index+'" >';
				li+='</li>';
			
				$("#orderTenderDocs").append(li);
				
			}else{
				$('#'+stdCstDoc.code).prop('checked',false);
			}
			$('#stdCstDoc_'+stdCstDoc.code).val(stdCstDoc.standardCustomDocId);
			if(!$.isEmptyObject(stdCstDoc.attachment)){
				$('#attachment_'+stdCstDoc.code).val(stdCstDoc.attachment.attachmentId);
				$('#anchor_'+stdCstDoc.code).val(stdCstDoc.attachment.fileName);
			}
	});
		
	}
	 	if (tenderStatus == 'Drafted'){
			$('.standardCustomDocuments2').removeAttr('disabled');
		}
		else{
			$('.standardCustomDocuments2').attr('disabled','disabled');
		}
	addNewCMDiv();
	setChildLoadFlag(true);
}

function removeStdCustDoc(id){
	sequenceLi();
	var resp=fetchList("removeCutomDoc",id);
}

function prepareForm(){
	var divArr=$("#tenderStdCustDocsForm").find('.stdCustDocDiv');
	count=0;
	$.each(divArr, function(idx,obj){
		
		var ele=$(obj).find(".standardCustomDoc");
		if($(ele).prop('checked')){
			$(ele).attr('name','standardCustomDocList['+count+'].isActive')
			$("#stdCstDoc_"+$(ele).attr('id')).attr('name',"standardCustomDocList["+count+"].standardCustomDocId");
			$("#std_doc_code_"+$(ele).attr('id')).attr('name',"standardCustomDocList["+count+"].code");
			$("#attachment_"+$(ele).attr('id')).attr('name',"standardCustomDocList["+count+"].attachment.attachmentId");
			$("#orderTenderDocs #"+$(ele).data("value")).find(".sequence").attr("name","standardCustomDocList["+count+"].index");
			count++;
		}else{
			$(ele).removeAttr('name');
			$("#stdCstDoc_"+$(ele).attr('id')).removeAttr('name');
			$("#std_doc_code_"+$(ele).attr('id')).removeAttr('name');
			$("#attachment_"+$(ele).attr('id')).removeAttr('name');
			$("#orderTenderDocs #"+$(ele).data("value")).find(".sequence").removeAttr('name');
			
		}	
	});
	sequenceLi();
}

function sequenceLi(){
	var liArr=$("#orderTenderDocs li");
	var cnt=0;
	$.each(liArr, function(idx,obj){
			$(obj).find(".sequence").val(cnt);
			cnt++;					
		});
}

function populateCustomDocs(custDoc){
    if(!$.isEmptyObject(custDoc)){
		customDocCount++;
    	var att=custDoc.attachment;
    	var attId;
    	var attName;
    	var dis;
    	if(!$.isEmptyObject(att)){
    		dis='';
    		attId=att.attachmentId;
    		attName=att.fileName;
    	}else{
    		dis='disabled="disabled"';
    	}
    	
    	var html='<div class="form-group stdCustDocDiv">'
   		 +'<div class="col-sm-4 col-xs-2">'
   		 +'<label class="checkbox-inline checkbox10">'
   		 +'<input type="hidden" id="stdCstDoc_'+custDoc.code+'" data-value="'+count+'">'
   		 +'<input type="checkbox" '+dis+' value="Y" class="standardCustomDoc standardCustomDocuments2" id="'+custDoc.code+'" data-value="'+count+'" data-name="Custom Document '+custDoc.code.split('CM')[1]+'">'
   		 +'<input type="hidden" value="'+custDoc.code+'" id="std_doc_code_'+custDoc.code+'">'
   		+'<a id="anchor_'+custDoc.code+'" data-url="download" href="download/'+attId+'">'+attName+'</a>'
   		 +'</label>'
   		 +'</div>'
   		 +'<div class="col-sm-5 col-xs-6">'
   		+'<input type="file" data-id="attachment_'+custDoc.code+'" data-anchor="anchor_'+custDoc.code+'" class="attachment form-control uploadFile standardCustomDocuments2" value="'+next+'">'
   		 +'<input type="hidden" id="attachment_'+custDoc.code+'" class="attachment" value="'+attId+'">' 		 
   		 +'</div>'
   		 +'<div class="col-sm-3 col-xs-4">'
   		 +'<button class="removeMoreCM btn btn-danger standardCustomDocuments2">Remove</button>'
   		 +'</div>'
   		 +'</div>';
    	$(".customDocs").append(html);
    }
    var i=Number(custDoc.code.split('CM')[1]);
    if(customDocCount<=i){
    	customDocCount=i++;
    }
    
    
    count++;
}

function addNewCMDiv(){
	customDocCount++;
	var html='<div class="form-group stdCustDocDiv">'
		 +'<div class="col-sm-4 col-xs-2">'
		 +'<label class="checkbox-inline checkbox10">'
		 +'<input type="hidden" id="stdCstDoc_CM'+customDocCount+'" data-value="'+count+'">'
		 +'<input type="checkbox" disabled="disabled" value="Y" class="standardCustomDoc standardCustomDocuments2" id="CM'+customDocCount+'" data-value="'+count+'" data-name="Custom Document '+customDocCount+'">'
		 +'<a id="anchor_CM'+customDocCount+'"  data-url="download"></a>'
		 +'<input type="hidden" value="CM'+customDocCount+'" id="std_doc_code_CM'+customDocCount+'">'
		 +'</label>'
		 +'</div>'
		 +'<div class="col-sm-5 col-xs-6">'
		 +'<input type="file" data-id="attachment_CM'+customDocCount+'" data-anchor="anchor_CM'+customDocCount+'" class="attachment form-control uploadFile standardCustomDocuments2" value="'+next+'">'
		 +'<input type="hidden" id="attachment_CM'+customDocCount+'" class="attachment" >'
		 +'</div>'
		 +'<div class="col-sm-3 col-xs-4">'
		 +'<button class="addMoreCM btn btn-default standardCustomDocuments2">Add More</button>'
  		 +'</div>'
		 +'</div>';
	

    $(".customDocs").append(html);
    count++;
}