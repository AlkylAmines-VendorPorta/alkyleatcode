var leftPanePageSize = 7;
var maxVisiblePageNumbers = 3;
var selectedPage = 1;
var pageSize=7;
var searchMode='none';
var searchValue='none';
var lastPage=0;
var systemConfigurator=new Array();
$(document).ready(function(){
	onPageLoad();
	$("#cancelBtnId").on('click',function(event){
		event.preventDefault();
		$('.formBtn').hide();
		$('#manupulationBtnId').show();
		var sysConfiguratorId=$('#leftPaneData ').find('li.active').attr('id');
		showSysConfig(sysConfiguratorId);
	});
	$('#pagination-here').paginate({
		pageSize:  7,
		 loadOnStartup : false,
		dataSource: 'fetchData',
		responseTo:  'loadSysConfigurator',
		maxVisiblePageNumbers:3,
		searchBoxID : 'searchLiteralId'
	});
});

function onPageLoad(){
	var data=fetchData(1, pageSize, searchMode, searchValue);
	loadSysConfigurator(data.data);
	lastPage=data.objectMap.LastPage;
	setPagination(lastPage, selectedPage , maxVisiblePageNumbers);
}

function loadSysConfigurator(data){
	loadSysConfiguratorLeftPane(data);
}

function reloadSysConfigurator(){
	swal({
		  title: "Do You Want To Load Partner Specific Configurator!",
		  text: ' This is only one time action ',
		  type: 'warning',
		  showCancelButton: true,
		  confirmButtonColor: '#3085d6',
		  cancelButtonColor: '#d33',
		  confirmButtonText: 'OK'
		})
		.then((result) => {
		  if (result.value) {
			  fetchList("addPartnerSystemConfifurator",null);
		  }
		});
}

function reloadAppContext(){
	var data=fetchListByGETMethod("reloadServletContext",null);
	if(data==='OK'){
		swal('Reload Successfull !','Servlet Context Reloaded','success');
	}else{
		swal('Reload Failed !','Servlet Context Not Reloaded','error');
	}
}

function addSysConfigurator(){
	$('.formBtn').show();
	$('#manupulationBtnId').hide();
	$('#sysConfiguratorForm').removeClass('readonly');
	$('#sysConfiguratorForm')[0].reset();
}

function editSysConfigurator(){
	$('.formBtn').show();
	$('#manupulationBtnId').hide();
	$('#sysConfiguratorForm').removeClass('readonly');
}

function cancel(){
	$('.formBtn').hide();
	$('#manupulationBtnId').show();
	var sysConfiguratorId=$('#leftPaneData ').find('li.active').attr('id');
	 showSysConfig(sysConfiguratorId);
	 $('.sysConfiguratorFormEle').removeClass('errorinput');
}

function loadSysConfiguratorLeftPane(data){ 
	$(".leftPaneData").html("");
	var leftPanelHtml="";
	var i=0;
	var firstRow=null;
	if(!$.isEmptyObject(data)){
			$.each(data,function(key,value){
				if(!$.isEmptyObject(value)){
					    var sysConfiguratorId = value.systemConfiguratorId==null?'': value.systemConfiguratorId;
						var name = value.name==null?'': value.name;
						var sysValue = value.value==null?'':value.value;
						var code  = value.code==null?'':value.code;
						var configType = value.configType==null?'':value.configType;
						systemConfigurator["sysConfig_"+sysConfiguratorId]=value;
						
						if(i==0){
							firstRow = value;
							leftPanelHtml = leftPanelHtml + ' <li class="active" onclick="showSysConfig('+sysConfiguratorId+')" id="'+sysConfiguratorId+'">';
						}else{
							leftPanelHtml = leftPanelHtml + ' <li onclick="showSysConfig('+sysConfiguratorId+')" id="'+sysConfiguratorId+'">';
						}
						leftPanelHtml = leftPanelHtml +' <a href="#Master" data-toggle="tab">'
					    +' <div class="col-md-12">'
					    +'  <label class="col-xs-6" id="'+sysConfiguratorId+'_value">'+sysValue+'</label>'
					    +'	<label class="col-xs-6" id="'+sysConfiguratorId+'_code">'+code+'</label>'
					    +' </div>'	
					    +' <div class="col-md-12">'
					    +'	<label class="col-xs-6" id="'+sysConfiguratorId+'_type">'+configType+'</label>'
						+'	<label class="col-xs-6" id="'+sysConfiguratorId+'_"></label>'
						+' </div>'
					    +' </a>'
					    +' </li>';
						i++;
					}
				 $(".leftPaneData").html(leftPanelHtml);
			});
			loadSysConfiguratorRightPane(firstRow);
	}
	$(".tabs-left li a label").text(function(index, currentText) {
			    return currentText.substr(0, 20);
			});
	$('.formBtn').hide();
}

function loadSysConfiguratorRightPane(data){
	if(!$.isEmptyObject(data)){
		    var sysConfiguratorId = data.systemConfiguratorId==null?'': data.systemConfiguratorId;
			var name = data.name==null?'': data.name;
			var value = data.value==null?'':data.value;
			var code  = data.code==null?'':data.code;
			var description = data.description==null?'':data.description;
			var configType = data.configType==null?'':data.configType;
			var isActive = data.isActive==null?'':data.isActive;
			if(isActive=="Y")
				$('.isActive').prop('checked', true); 
			else
				$('.isActive').prop('checked', false);
			$('#sysConfiguratorForm #systemConfiguratorId').val(sysConfiguratorId);
			$('#sysConfiguratorForm #p_systemConfiguratorId').val(sysConfiguratorId);
			$('#sysConfiguratorForm #name').val(name);
			$('#sysConfiguratorForm #value').val(value);
			$('#sysConfiguratorForm #code').val(code);
			$('#sysConfiguratorForm #description').val(description);
			$('#sysConfiguratorForm #configType').val(configType);
			setHeaderValues(" Configurator Key: "+value  , " Value : "+name, "Description: "+description, "  ");
	}else{
		$('#sysConfiguratorForm #systemConfiguratorId').val();
		$('#sysConfiguratorForm #p_systemConfiguratorId').val();
		$('#sysConfiguratorForm #name').val();
		$('#sysConfiguratorForm #value').val();
		$('#sysConfiguratorForm #code').val();
		$('#sysConfiguratorForm #description').val();
		$('#sysConfiguratorForm #configType').val();
		setHeaderValues(" Configurator Key: "  , " Value : ", "Description: ", "  ");
	}
}

function saveSysConfiguratorResp(data){
	if(data.objectMap.result){
		 var vdata=data.objectMap.systemConfigurator;
		 var sysConfiguratorId = vdata.systemConfiguratorId==null?'': vdata.systemConfiguratorId;
		 
	     var curentSysConfigurationId=$('#leftPaneData ').find('li.active').attr('id');
		if(curentSysConfigurationId==sysConfiguratorId){
			$('#leftPaneData ').find('li.active').remove();
		}else{
			$('#leftPaneData ').find('li.active').removeClass('active');
		}
		
		 var name = vdata.name==null?'': vdata.name;
		 var value = vdata.value==null?'':vdata.value;
		 var code  = vdata.code==null?'':vdata.code;
		 var description = vdata.description==null?'':vdata.description;
		 var configType = vdata.configType==null?'':vdata.configType;
		 var isActive = data.isActive==null?'':data.isActive;
		 if(isActive=="Y"){
			 $('#isActive').prop('checked', true); 
		 }else{
			 $('#isActive').prop('checked', false);	
		 }	
		 var leftPanelHtml="";
		 
		 leftPanelHtml = leftPanelHtml + ' <li class="active" onclick="showSysConfig('+sysConfiguratorId+')" id="'+sysConfiguratorId+'">';
		 leftPanelHtml = leftPanelHtml +' <a href="#Master" data-toggle="tab">'
		 	+' <div class="col-md-12">'
		    +'  <label class="col-xs-6" id="'+sysConfiguratorId+'_value">'+value+'</label>'
		    +'	<label class="col-xs-6" id="'+sysConfiguratorId+'_code">'+code+'</label>'
		    +' </div>'	
		    +' <div class="col-md-12">'
		    +'	<label class="col-xs-6" id="'+sysConfiguratorId+'_type">'+configType+'</label>'
			+'	<label class="col-xs-6" id="'+sysConfiguratorId+'_"></label>'
			+' </div>'
		    +' </a>'
		    +' </li>';
		
		 $(".leftPaneData").prepend(leftPanelHtml);
		 $('#sysConfiguratorForm .sysConfigurator').val(sysConfiguratorId);
		 systemConfigurator["sysConfig_"+sysConfiguratorId]=vdata;
		 Alert.info(data.objectMap.message);
		 cancel();
		 setHeaderValues(" Configurator KeY: "+value  , " Value : "+name, "Description: "+description, "  ");
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

function showSysConfig(sysConfiguratorId){
	loadSysConfiguratorRightPane(systemConfigurator["sysConfig_"+sysConfiguratorId]);
}


function deleteSysConfigurator(){
	var sysConfiguratorId=$('#sysConfiguratorForm #systemConfiguratorId').val();
	if(sysConfiguratorId!=''){
		submitToURL('deleteSysConfiguratorId/'+sysConfiguratorId,'deleteSysConfiguratorResp');
	}else{
		Alert.warn('Something Went Wrong');
	}
	
}

function deleteSysConfiguratorResp(data){
	if(data.objectMap.result){
		var sysConfiguratorId=$('#sysConfiguratorForm #systemConfiguratorId').val();
		$('#leftPaneData ').find('li.active').remove();
		systemConfigurator = $.grep(systemConfigurator, function(e){ 
		     return e.systemConfiguratorId != sysConfiguratorId; 
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
        url: 'getSysConfiguratorList/'+pageNumber+'/'+pageSize+'/'+searchMode+'/'+searchValue,
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