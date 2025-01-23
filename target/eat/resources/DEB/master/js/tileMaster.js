var leftPanePageSize = 7;
var maxVisiblePageNumbers = 3;
var selectedPage = 1;
var pageSize=7;
var searchMode='none';
var searchValue='none';
var lastPage=0;
var tile=new Array();
$(document).ready(function(){
	onPageLoad();
	$("#cancelBtnId").on('click',function(event){
		event.preventDefault();
		$('.formBtn').hide();
		$('#manupulationBtnId').show();
		var tileId=$('#leftPaneData ').find('li.active').attr('id');
		if(tileId!=''){
			showTile(tileId);	
		}
	});
	$('#pagination-here').paginate({
		pageSize:  7,
		loadOnStartup : false,
		dataSource: 'fetchData',
		responseTo:  'loadTileMaster',
		maxVisiblePageNumbers:3,
		searchBoxID : 'searchLiteralId'
	});
});

function onPageLoad(){
	var data=fetchData(1, pageSize, searchMode, searchValue);
	loadTileMaster(data.data);
	lastPage=data.objectMap.LastPage;
	setPagination(lastPage, selectedPage , maxVisiblePageNumbers);
}

function loadTileMaster(data){
	loadTileLeftPane(data);
	
}

function addTile(event){
	event.preventDefault();
	$('.formBtn').show();
	$('#manupulationBtnId').hide();
	$('#tileForm').removeClass('readonly');
	$('#tileForm')[0].reset();
}

function editTile(event){
	event.preventDefault();
	$('.formBtn').show();
	$('#manupulationBtnId').hide();
	$('#tileForm').removeClass('readonly');
}

function cancel(){
	$('.formBtn').hide();
	$('#manupulationBtnId').show();
	var tileId=$('#leftPaneData ').find('li.active').attr('id');
	 showTile(tileId);
	 $('.tileFormFormEle').removeClass('errorinput');
}

function loadTileLeftPane(data){ 
	$(".leftPaneData").html("");
	var leftPanelHtml="";
	var i=0;
	var firstRow=null;
	if(!$.isEmptyObject(data)){
			$.each(data,function(key,value){
				if(!$.isEmptyObject(value)){
					    var tileId = value.tileMasterId==null?'': value.tileMasterId;
						var name = value.name==null?'': value.name;
						var code  = value.code==null?'':value.code;
						tile["tile_"+tileId]=value;
						
						if(i==0){
							firstRow = value;
							leftPanelHtml = leftPanelHtml + ' <li class="active" onclick="showTile('+tileId+')" id="'+tileId+'">';
						}else{
							leftPanelHtml = leftPanelHtml + ' <li onclick="showTile('+tileId+')" id="'+tileId+'">';
						}
						leftPanelHtml = leftPanelHtml +' <a href="#Master" data-toggle="tab">'
					    +' <div class="col-md-12">'
					    +'  <label class="col-xs-6" id="'+tileId+'_value">'+name+'</label>'
					    +'	<label class="col-xs-6" id="'+tileId+'_code">'+code+'</label>'
					    +' </div>'	
					    +' <div class="col-md-12">'
					    +'	<label class="col-xs-6" id="'+tileId+'_type"></label>'
						+'	<label class="col-xs-6" id="'+tileId+'_"></label>'
						+' </div>'
					    +' </a>'
					    +' </li>';
						i++;
					}
				 $(".leftPaneData").html(leftPanelHtml);
			});
			loadTileRightPane(firstRow);
	}
	$(".tabs-left li a label").text(function(index, currentText) {
			    return currentText.substr(0, 20);
			});
	$('.formBtn').hide();
}

function loadTileRightPane(data){
	if(!$.isEmptyObject(data)){
		    var tileId = data.tileMasterId==null?'': data.tileMasterId;
			var name = data.name==null?'': data.name;
			var code  = data.code==null?'':data.code;
			var description = data.description==null?'':data.description;
			var isActive = data.isActive==null?'':data.isActive;
			if(isActive=="Y")
				$('.isActive').prop('checked', true); 
			else
				$('.isActive').prop('checked', false);
			$('#tileForm #tileId').val(tileId);
			$('#tileForm #p_tileIdId').val(tileId);
			$('#tileForm #name').val(name);
			$('#tileForm #code').val(code);
			$('#tileForm #description').val(description);
			setHeaderValues(" Tile Name: "+name  , " Value : "+code, "Description: "+description, "  ");
	}else{
		$('#tileForm #systemConfiguratorId').val();
		$('#tileForm #p_systemConfiguratorId').val();
		$('#tileForm #name').val();
		$('#tileForm #code').val();
		$('#tileForm #description').val();
		setHeaderValues(" Tile Name: "  , " Value : ", "Description: ", "  ");
	}
}

function saveTileResp(data){
	if(data.objectMap.result){
		 var vdata=data.objectMap.tile;
		 var tileId = vdata.tileMasterId==null?'': vdata.tileMasterId;
		 
	     var currentTileMasterId=$('#leftPaneData ').find('li.active').attr('id');
		if(currentTileMasterId==tileId){
			$('#leftPaneData ').find('li.active').remove();
		}else{
			$('#leftPaneData ').find('li.active').removeClass('active');
		}
		
		 var name = vdata.name==null?'': vdata.name;
		 var code  = vdata.code==null?'':vdata.code;
		 var description = vdata.description==null?'':vdata.description;
		 var isActive = data.isActive==null?'':data.isActive;
		 if(isActive=="Y"){
			 $('#isActive').prop('checked', true); 
		 }else{
			 $('#isActive').prop('checked', false);	
		 }	
		 var leftPanelHtml="";
		 
		 leftPanelHtml = leftPanelHtml + ' <li class="active" onclick="showSysConfig('+tileId+')" id="'+tileId+'">';
		 leftPanelHtml = leftPanelHtml +' <a href="#Master" data-toggle="tab">'
		 	+' <div class="col-md-12">'
		    +'  <label class="col-xs-6" id="'+tileId+'_value">'+name+'</label>'
		    +'	<label class="col-xs-6" id="'+tileId+'_code">'+code+'</label>'
		    +' </div>'	
		    +' <div class="col-md-12">'
		    +'	<label class="col-xs-6" id="'+tileId+'_type"></label>'
			+'	<label class="col-xs-6" id="'+tileId+'_"></label>'
			+' </div>'
		    +' </a>'
		    +' </li>';
		
		 $(".leftPaneData").prepend(leftPanelHtml);
		 $('#tileForm .tileId').val(sysConfiguratorId);
		 tile["tile_"+tileId]=vdata;
		 Alert.info(data.objectMap.message);
		 cancel();
		 setHeaderValues(" Tile Name: " +name , " Value : "+code, "Description: "+description, "  ");
	}else{
		var msg = '<br>';
		var i=1;
		if(!isEmpty(data.objectMap.hasError)){
			$.each(data.objectMap.hasError.errorList, function(key, value) {
			     msg=msg+'\n'+i+'. '+value.errorMessage +','+ '<br>';
			   });
		}
		    Alert.warn(data.objectMap.message+' because '+' :'+msg);
	}
	   
}

function showTile(tileId){
	loadTileRightPane(tile["tile_"+tileId]);
}


function deleteTile(event){
	event.preventDefault();
	var tileId=$('#tileForm #tileId').val();
	if(tileId!=''){
		submitToURL('deleteTileId/'+tileId,'deleteTileResp');
	}else{
		Alert.warn('Something Went Wrong');
	}
	
}

function deleteTileResp(data){
	if(data.objectMap.result){
		var tileId=$('#tileForm #tileId').val();
		$('#leftPaneData ').find('li.active').remove();
		tile = $.grep(systemConfigurator, function(e){ 
		     return e.tileMasterId != tileId; 
		});
	    Alert.info(data.objectMap.message);	
	}else{
		 Alert.warn(data.objectMap.message);	
	}
}



function fetchData(pageNumber, pageSize, searchMode, searchValue){
	var response;
	$.ajax({
        type : "POST",
        contentType : "application/json",
        url: 'getTileMasterList/'+pageNumber+'/'+pageSize+'/'+searchMode+'/'+searchValue,
        dataType:"json",
        async:false,
        success: function (data) {
        	response=data;
        },
        error: function (e) {
			Alert.warn("Exception :");
        }
    });
	return response;
}