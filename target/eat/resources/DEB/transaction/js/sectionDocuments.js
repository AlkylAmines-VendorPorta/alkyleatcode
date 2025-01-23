var sectionDocArray=new Array();
var materialArray=new Array();
var isSectionDocLiChanged=true;
$(document).ready(function() {
	$(".saveSectionDocBtn").on("click",function(event){
		
		event.preventDefault();
		submitIt("saveSectionDocumentForm","saveSectionDocument","saveSectionDocResp");
	});
	
	$(".addSectionDoc").on("click",function(event){
		
		event.preventDefault();
		$('#saveSectionDocumentForm')[0].reset();
		$('#saveSectionDocumentForm .save').show();
		$('#saveSectionDocumentForm .cancel').show();
	});
	
	$(".editSectionDoc").on("click",function(event){
		
		event.preventDefault();
		$('#saveSectionDocumentForm').removeClass("readonly");
	});
	
     $('.cancelSectionDocBtn').click(function(event) {
    	 
		event.preventDefault();
		var activeSectionDocId=$('.leftPaneData').find('li.active').attr('id');
		if(activeSectionDocId!=undefined){
			loadSectionDocToRightPane(sectionDocArray["sectionDoc"+activeSectionDocId]);
		}else
			$('#saveSectionDocumentForm')[0].reset();
    });
	
     $(".deleteSectionDoc").on("click",function(event){
 		
 		event.preventDefault();
 		var id= $(".sectionDocumentId").val();
 		if(id!=''){
 			requiredSectionDelResp(fetchList("deleteSectionDocument",id));	
 		}else{
 			Alert.warn('select any document');
 		}
 		
 		
 	});
     
	$("#sectionDocTab").on("click",function(){
		$("#leftPane").removeClass('readonly');
		if($(".dataUrlTypeCode").val()=='getTAHDRByTypeCode' && $(".dataUrl").val()=='tenderPreparationData'){
			$(".addSectionDoc").show();
			$(".deleteSectionDoc").show();
		}
		else if($(".dataUrlTypeCode").val()=='getTAHDRApprovalByTypeCode' && $(".dataUrl").val()=='tenderApprovalData'){
			$(".addSectionDoc").hide();
			$(".deleteSectionDoc").hide();
		}
		cacheLi();
		setCurrentTab(this);
		getSectionDocuments();
		var documentType=$(".documentType").val();
		setHeaderValues(documentType+"No.: "+tahdrCodes, documentType+"Title : "+title, "Department : "+department, "Status : "+tenderStatus);
	});
	
	$(".sectionCode").on("change",function(){
		
		if($(this).val()=='CS' ||$(this).val()==''){
			$("#sectionTahdrMaterial").hide();
			$("#sectionTahdrMaterialLabel").hide();
			$('.sectionTahdrMaterialData').hide();
			$("#sectionTahdrMaterial").attr('disabled','disabled');
		}else{
			$("#sectionTahdrMaterial").show();
			$("#sectionTahdrMaterialLabel").show();
			$('.sectionTahdrMaterialData').show();
			$("#sectionTahdrMaterial").removeAttr('disabled');
		}
	});
});

function getSectionDocuments(){
	debugger;
	var id=$(".tahdrDetailId").val();
	var tahdrDetail=tahdrDetailArray["tahdrDetail"+id];
	setRequiredDocsScope(tahdrDetail);
	if(getChangedFlag()){
		/*$('.pagination').children().remove();*/
		var resp= fetchList("getSectionDocument",id);
		var materialList=resp.objectMap.materialList;
		addMaterialinList(materialList);
		var secDocSet=resp.objectMap.secDocSet;
		loadMaterialDropDown(materialList);
		loadSectionDocToLeftPane(secDocSet);
		/*$('#leftPane').paginathing({perPage: 6});*/
	 $(".tabs-left li a label").text(function(index, currentText) {
		    return currentText.substr(0, 20);
		});	
	 
	 if (tenderStatus == 'Drafted'){
			$('#tenderRequiredDocsForm').removeClass('readonly');
			$('#sectionButtonsId').removeClass('readonly');
			$('.saveSectionDocBtn').show();
			$('.cancelSectionDocBtn').show();
		}
		else {
			$('#tenderRequiredDocsForm').addClass('readonly');
			$('#sectionButtonsId').addClass('readonly');
			$('.saveSectionDocBtn').hide();
			$('.cancelSectionDocBtn').hide();
		}
	 setChangedFlag(false);
	}else{
		getCacheLi();
	} 
	setLeftPaneHeader(" Req Documents", null);
}

function requiredSectionDelResp(data){
	event.preventDefault();
	if(!isEmpty(data) && !isEmpty(data.message)){
		var currentSectionLiId=$('ul.leftPaneData').find('li.active').attr('id');
		$('#'+currentSectionLiId).remove();
		delete sectionDocArray["sectionDoc"+currentSectionLiId];
		$('#saveSectionDocumentForm')[0].reset();
		/*resetSectionDocLeftPane(sectionDocArray);*/
		$('#leftPane').paginathing({perPage: 6});
		Alert.info(data.message);
	}
	
}

function loadMaterialDropDown(materialList){
	$("#sectionTahdrMaterial").empty();
	$.each(Object.values(materialList),function(id,material){
		$("#sectionTahdrMaterial").append("<option value='"+material.tahdrMaterialId+"'>"+material.material.name+"</option>");
	});
}

function sectionDocDelResp(data){
	if(!isEmpty(data) && !isEmpty(data.message)){
		Alert.info(data.message);	
	}
	var currentSectionDocLiId=$('ul.leftPaneData').find('li.active').attr('id');
	$('#'+currentSectionDocLiId).remove();
	$('#saveSectionDocumentForm')[0].reset();
	event.preventDefault();
}

function addMaterialinList(materialList){
	if(materialList!=null && materialList!=undefined){
		$.each(materialList,function(key,doc){
			materialArray["materialId"+doc.tahdrMaterialId]=doc;
		});
	}
	else{
		materialArray=[];	
	}
}
function resetSectionDocLeftPane(secDocs){
	$('.pagination').children().remove();
	$("#leftPane").html("");
	var leftPanelHtml='';
	var i=0;
	var active="";
	var firstRow=null;
	if(!$.isEmptyObject(secDocs)){
	$.each(secDocs,function(index, value){
			if(i==0){
				firstRow = doc;
				active="active";
			}
			leftPanelHtml= leftPanelHtml +appendSectionDocLi(doc,active);
			active="";
			i++;
		});
	}
	$("#leftPane").append(leftPanelHtml);
	$('.leftPaneData >li >a').find('.leftPaneLowerDiv').removeClass('leftPaneLowerDiv');
	loadSectionDocToRightPane(firstRow);
	setLeftPaneHeader("Required Documents", secDocs.length);
}

function loadSectionDocToLeftPane(secDocs){
	$('.pagination').children().remove();
	$("#leftPane").html("");
	var leftPanelHtml='';
	var i=0;
	var active="";
	var firstRow=null;
	sectionDocArray=[];
	if(!$.isEmptyObject(secDocs)){
	$.each(Object.values(secDocs),function(key,doc){
			
			sectionDocArray["sectionDoc"+doc.sectionDocumentId]=doc;
			if(i==0){
				firstRow = doc;
				active="active";
			}
			leftPanelHtml= leftPanelHtml +appendSectionDocLi(doc,active);
			active="";
			i++;
		});
	}
	$("#leftPane").append(leftPanelHtml);
	$('.leftPaneData >li >a').find('.leftPaneLowerDiv').removeClass('leftPaneLowerDiv');
	loadSectionDocToRightPane(firstRow);
	setLeftPaneHeader("Required Documents", Object.values(sectionDocArray).length);
	$("#leftPane").on('click', '.sectionDoc',function(){
		var id=$(this).attr('id');
		loadSectionDocToRightPane(sectionDocArray["sectionDoc"+id]);	
	});
	
	$('#leftPane').paginathing({perPage: 6});
}

function loadSectionDocToRightPane(sectionDoc){
	if(!$.isEmptyObject(sectionDoc)){
		$(".sectionDocumentId").val(getValue(sectionDoc.sectionDocumentId));
		$(".sectionCode").val(getValue(sectionDoc.code));
		$(".sectionCode").trigger('change');
		$(".documentName").val(getValue(sectionDoc.name));
		$(".documentDescription").val(getValue(sectionDoc.description));
		if(!$.isEmptyObject(sectionDoc.tahdrMaterial))
			$("#sectionTahdrMaterial").val(getValue(sectionDoc.tahdrMaterial.tahdrMaterialId));
		if (tenderStatus == 'Drafted'){
			$('#tenderRequiredDocsForm').removeClass('readonly');
			$('#sectionButtonsId').removeClass('readonly');
			$('#saveSectionDocumentForm .save').show();
			$('#saveSectionDocumentForm .cancel').show();
		}
		else{
			$('#tenderRequiredDocsForm').addClass('readonly');
			$('#sectionButtonsId').addClass('readonly');
			$('#saveSectionDocumentForm .save').hide();
			$('#saveSectionDocumentForm .cancel').hide();
		}
	}
	else{
		$('#saveSectionDocumentForm')[0].reset();
	}
	setChildLoadFlag(true);
}

function saveSectionDocResp(data){
	$('.pagination').children().remove();
	if(data.response.hasError==false){
		var leftPanelHtml='';
		var currentSectionDocId=$('ul.leftPaneData').find('li.active').attr('id');
		var sectionDocId=data.sectionDocumentId;
		if(currentSectionDocId==sectionDocId){
			$('#'+currentSectionDocId).remove();
		}
		else{
			$('#'+currentSectionDocId).removeClass('active');
		}
		$(".sectionDocumentId").val(sectionDocId);
		sectionDocArray["sectionDoc"+sectionDocId]=data;
		leftPanelHtml=appendSectionDocLi(data,"active");
		$(".leftPaneData").prepend(leftPanelHtml);
		$('.leftPaneData >li >a').find('.leftPaneLowerDiv').removeClass('leftPaneLowerDiv');
		if(data.response.hasError){
			Alert.warn(data.response.message);
		}else{
			Alert.info(data.response.message);
		}
		
		$('#leftPane').paginathing({perPage: 6});
	}else{
			if(data.response.hasError){
				Alert.warn(data.response.message);
			}else{
				Alert.info(data.response.message);
			}
		 }
	setChildLoadFlag(true);
}

function appendSectionDocLi(sectionDoc,active){
	var material='';
	if(sectionDoc.code!='CS'){
		var tahdrMaterialData=sectionDoc.tahdrMaterial==null?'':sectionDoc.tahdrMaterial.tahdrMaterialId==null?'':sectionDoc.tahdrMaterial.tahdrMaterialId;
		material=materialArray["materialId"+tahdrMaterialData];
		/*material=sectionDoc.tahdrMaterial==null?'':sectionDoc.tahdrMaterial.material==null?'':sectionDoc.tahdrMaterial.material.name==null?'':sectionDoc.tahdrMaterial.material.name;*/
		return appendLiData(bidSection[sectionDoc.code],sectionDoc.name, sectionDoc.description, material.material.name, sectionDoc.sectionDocumentId, active, 'sectionDoc');
	}else{
		return appendLiData(bidSection[sectionDoc.code],sectionDoc.name, sectionDoc.description, "", sectionDoc.sectionDocumentId, active, 'sectionDoc');
	}
}

function setRequiredDocsScope(tahdrDetail){
	debugger;
	if(tahdrDetail!=null){
		var isTechDocsNeeded=tahdrDetail.isTechnicalDocs;
		var isCommDocsNeeded=tahdrDetail.isCommercialDocs;
		var isPriceDocsNeeded=tahdrDetail.isPriceDocs;
		if(isTechDocsNeeded=='Y'){
			$(".sectionCode").find('[value="TS"]').show();
		}else{
			$(".sectionCode").find('[value="TS"]').hide();
		}
		if(isCommDocsNeeded=='Y'){
			$(".sectionCode").find('[value="CS"]').show();
		}else{
			$(".sectionCode").find('[value="CS"]').hide();
		}
		if(isPriceDocsNeeded=='Y'){
			$(".sectionCode").find('[value="PS"]').show();
		}else{
			$(".sectionCode").find('[value="PS"]').hide();
		}
	}else{
		$(".sectionCode").html("<option value=''>Select</option>");
	}
	
}