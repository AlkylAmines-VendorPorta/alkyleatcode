var liCache=new Array();
var liChangedFlag=new Array();
function cacheLi(){
	var ele=getCurrentTab();
	var index=$(ele).attr("aria-controls");
	if(typeof $("#leftPane").html()!='undefined'){
		liCache[index]=$('#leftPane').html();
	}else if(typeof $("#example").html()!='undefined'){
		liCache[index]=$('#example').html();
	}else if(typeof $("#leftPaneData").html()!='undefined'){
		liCache[index]=$('#leftPaneData').html();
	}
}

function getCacheLi(){
		var ele=getCurrentTab();
		var index=$(ele).attr("aria-controls");
		if(typeof $("#leftPane").html()!='undefined'){
			$("#leftPane").html('');
			$("#leftPane").html(liCache[index]);
		}else if(typeof $("#example").html()!='undefined'){
			$("#example").html('');
			$("#example").html(liCache[index]);
		}else if(typeof $("#leftPaneData").html()!='undefined'){
			$("#leftPaneData").html('');
			$("#leftPaneData").html(liCache[index]);
		}
}

function loadNewList(event){
	if($.isEmptyObject(event)){
		event.preventDefault();
	}
	var currentTab= getCurrentTab();
	setChangedFlag(true);
	$(currentTab).trigger('click');
}

function getChangedFlag(){
	var ele=getCurrentTab();
	var flagName=$(ele).attr("aria-controls");
	if(typeof liChangedFlag[flagName]=='undefined'){
		return true;
	}else{
		return liChangedFlag[flagName];
	}
}

function getParentFlag(){
	var ele=getCurrentTab();
	var parentTab=$("#"+ele.dataset.parenttab);
	var flagName=$(parentTab).attr("aria-controls");
	if(typeof liChangedFlag[flagName]=='undefined'){
		return true;
	}else{
		return liChangedFlag[flagName];
	}
}

function setChangedFlag(value){
	var ele=getCurrentTab();
	var flagName=$(ele).attr("aria-controls");
	setChangedFlagVal(flagName,value);
}

function setParentFlag(value){
	var ele=getCurrentTab();
	var parentTab=$("#"+ele.dataset.parenttab);
	var flagName=$(parentTab).attr("aria-controls");
	setChangedFlagVal(flagName,value);
}

function setChangedFlagVal(key,value){
	liChangedFlag[key]=value;
}

function setChangedFlagById(id,value){
	var ele=$("#"+id);
	var flagName=$(ele).attr("aria-controls");
	setChangedFlagVal(flagName,value);
}

function setChildLoadFlag(value){
	var ele=getCurrentTab();
	var id=$(ele).attr("id");
	var childList=$("[data-parentTab='"+id+"'");
	$.each(childList,function(idx,child){
		var flagName=$(child).attr("aria-controls");
		setChangedFlagVal(flagName,value);
	});
}