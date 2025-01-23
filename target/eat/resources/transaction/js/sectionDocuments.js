var sectionDocArray=new Array();
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
 		
 		requiredSectionDelResp(fetchList("deleteSectionDocument",id));
 		
 	});
     
	$("#sectionDocTab").on("click",function(){
		cacheLi();
		setCurrentTab(this);
		getSectionDocuments();
	});
	
	$(".sectionCode").on("change",function(){
		
		if($(this).val()=='CS'){
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
	if(getChangedFlag()){
		/*$('.pagination').children().remove();*/
		var id=$(".tahdrDetailId").val();
		var resp= fetchList("getSectionDocument",id);
		var materialList=resp.objectMap.materialList;
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
}

function requiredSectionDelResp(data)
{
	Alert.info(data.message);
	var currentSectionLiId=$('ul.leftPaneData').find('li.active').attr('id');
	$('#'+currentSectionLiId).remove();
	delete sectionDocArray["sectionDoc"+currentSectionLiId];
	$('#saveSectionDocumentForm')[0].reset();
	event.preventDefault();
}

function loadMaterialDropDown(materialList){
	
	$("#sectionTahdrMaterial").empty();
	$.each(Object.values(materialList),function(id,material){
		$("#sectionTahdrMaterial").append("<option value='"+material.tahdrMaterialId+"'>"+material.material.name+"</option>");
	});
}

function sectionDocDelResp(data)
{
	Alert.info(data.message);
	var currentSectionDocLiId=$('ul.leftPaneData').find('li.active').attr('id');
	$('#'+currentSectionDocLiId).remove();
	$('#saveSectionDocumentForm')[0].reset();
	event.preventDefault();
}

function loadSectionDocToLeftPane(secDocs){
	$('.pagination').children().remove();
	$("#leftPane").html("");
	var leftPanelHtml='';
	var i=0;
	var active="";
	var firstRow=null;
	sectionDocArray=[];
	/*if($.isEmptyObject(secDocs)){*/
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
	/*}*/
	$("#leftPane").append(leftPanelHtml);
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
	if(data.response.hasError==false)
	{
		var leftPanelHtml='';
		var currentSectionDocId=$('ul.leftPaneData').find('li.active').attr('id');
		var sectionDocId=data.sectionDocumentId;
		if(currentSectionDocId==sectionDocId)
		{
			$('#'+currentSectionDocId).remove();
		}
		if(currentSectionDocId<sectionDocId)
		{
			$('#'+currentSectionDocId).removeClass('active');
		}
		$(".sectionDocumentId").val(sectionDocId);
		leftPanelHtml=appendSectionDocLi(data,"active");
		sectionDocArray["sectionDoc"+sectionDocId]=data;
		$(".leftPaneData").prepend(leftPanelHtml);
		
		if(data.response.hasError){
			Alert.warn(data.response.message);
		}else{
			Alert.info(data.response.message);
		}
		
		$('#leftPane').paginathing({perPage: 6});
	}
	else
		 {
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
		material=sectionDoc.tahdrMaterial==null?'':sectionDoc.tahdrMaterial.material==null?'':sectionDoc.tahdrMaterial.material.name==null?'':sectionDoc.tahdrMaterial.material.name;
	}
	return appendLiData(bidSection[sectionDoc.code],sectionDoc.name, sectionDoc.description, material, sectionDoc.sectionDocumentId, active, 'sectionDoc');
}