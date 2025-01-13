var liCache=[];
/*var activeTabId;
var editMode=false;*/
var currentTab;
$(document).ready(function(){
	$(':checkbox').change(function(){
			if(this.checked){
        	this.value="Y"
        }else{
			this.value="N"            
        }
       }
	);
	
	$('#searchLiteralId').on('keyup', function () {
		isServerSidePaginationEmpty = $('#pagination-here').is(':empty');
		if(isServerSidePaginationEmpty){
		    var value = this.value;
		    $('#example li').hide().each(function () {
		        if ($(this).text().toUpperCase().search(value.toUpperCase()) > -1) {
		        	$(this).show();
		        }
		    });
		    
		    $('#leftPane li').hide().each(function () {
		        if ($(this).text().toUpperCase().search(value.toUpperCase()) > -1) {
		        	$(this).show();
		        }
		    });
		    $('.reportCount').html($('.leftPaneData > li:visible').length);
		}
	});
	
	$(".loadList").on("click", function(event){
		event.preventDefault();
		var url=$(this).attr("url");
		jQuery.ajax({
			type : "POST",
			contentType : "application/json",
			url : url,
			dataType:"json",
	        async:false,
	        success : function(data) {
			window[url](data);
			},
			error : function(ex) {
				formFieldExceptionHandler(ex);
			}
		});
	});
	
	$(".loadListCascaded").on("change", function(){
		var url=$(this).attr("url");
		var id=$(this).val();
		jQuery.ajax({
			type : "POST",
			contentType : "application/json",
			url : url+'/'+id,
			dataType:"json",
	        async:false,
	        success : function(data) {
			window[url](data);
			},
			error : function(ex) {
				formFieldExceptionHandler(ex);
			}
		});
	});
	
	$('.toggleTab').on('click',function(e){
		e.preventDefault();
		onToggleTab(this);
	});
	
	$('.toggleTabEdit').on('click',function(e){
		e.preventDefault();
		if(!editMode && !requiredFileDeleted){
			onToggleTab(this);
		}else{
			e.stopPropagation();
			Alert.warn("Please Save Changed Data Of "+activeTabName+" Tab");
		}
	});
	
	$('.toggleNewTab').on('click',function(e){
		e.preventDefault();
		var openTab=$(this).attr('openTab');
		var currentTab=$("#tabUL").find('li.k-state-active');
		var currentDiv=$("#"+currentTab.attr('aria-controls'));
		var newTab=$("#"+openTab);
		var newDiv=$("#"+newTab.attr('aria-controls'));
		
		currentDiv.removeClass('k-state-active').css('display','none');
		currentTab.removeClass('k-state-active');
		
		newDiv.addClass('k-state-active').css('display','block').css('opacity','1');
		newTab.addClass('k-state-active');
		
	});
	

	$('.tab-li-next').on('click',function(e){
		e.preventDefault();
		$(this).attr("disabled","disabled");
		var currentDiv=$(this).parents('.k-state-active')[0];
		var currentTab=$('[aria-controls='+$(currentDiv).attr("id")+']');
		var nextTab;
		nextTab= findNextTab(currentTab);
		if(!nextTab.hasClass('readonly')){
			while($(nextTab).css('display')=='none'){
				nextTab=findNextTab(nextTab);
			}
			
			nextTab.trigger('click');
			if($("#"+$(nextTab).attr('aria-controls')).children().find('li.k-state-active').length>0){
				var childDeActiveTab=$("#"+$(nextTab).attr('aria-controls')).children().find('li.k-state-active');
				var childDeActiveDiv=$("#"+$(childDeActiveTab).attr('aria-controls'));
				childDeActiveTab.removeClass('k-state-active');
				childDeActiveDiv.removeClass('k-state-active').css('display','none');
				
				var childActiveTab=$("#"+$(nextTab).attr('aria-controls')).children().find('li.k-first');
				var childActiveDiv=$("#"+childActiveTab.attr('aria-controls'));
				childActiveTab.addClass('k-state-active');
				childActiveDiv.addClass('k-state-active').css('display','block').css('opacity','1');
			}
		}
		$(this).removeAttr("disabled");
	});
	
	$('.tab-li-prev').on('click',function(e){
		e.preventDefault();
		$(this).attr("disabled","disabled");
		var currentDiv=$(this).parents('.k-state-active')[0];
		var currentTab=$('[aria-controls='+$(currentDiv).attr("id")+']');
		var prevTab;
		var prevDiv;
		
		prevTab=findPrevTab(currentTab);
		if(!prevTab.hasClass('readonly')){
			while($(prevTab).css('display')=='none'){
				prevTab=findPrevTab(prevTab);
			}
			
			prevTab.trigger('click');
						
			if($("#"+$(prevTab).attr('aria-controls')).children().find('li.k-state-active').length>0){
				var childDeActiveTab=$("#"+$(prevTab).attr('aria-controls')).children().find('li.k-state-active');
				var childDeActiveDiv=$("#"+$(childDeActiveTab).attr('aria-controls'));
				childDeActiveTab.removeClass('k-state-active');
				childDeActiveDiv.removeClass('k-state-active').css('display','none');
				
				var childActiveTab=$("#"+$(prevTab).attr('aria-controls')).children().find('li.k-first');
				var childActiveDiv=$("#"+childActiveTab.attr('aria-controls'));
				childActiveTab.addClass('k-state-active');
				childActiveDiv.addClass('k-state-active').css('display','block').css('opacity','1');
			}
		}
		$(this).removeAttr("disabled");
	});
	
	$(".readonly").on("focus",function(){
		$(this).blur();
	});
	
	$(":input").on("focus",function(){
		var readOnlyEle=$(this).closest('.readonly');
		if(readOnlyEle.length>0){
			$(this).blur();
		}
	});
	
});

function setAttribute(id,value){
	
	if("Y" == value){
		$("#"+id).prop("checked",true);
	}else{
		$("#"+id).prop("checked",false);
	}
	$("#"+id).val(value);
}

function setActiveTabName(name,count)
{
	$('.activeTab').html(name);
	$('.reportCount').html(count);
}

function fetchList(addr,id){
	var response;
		var url;
		if(id==null){
			url=addr;
		}else{
			url=addr+"/"+id;
		}
		jQuery.ajax({
			type : "POST",
			contentType : "application/json",
			url : url,
			dataType:"json",
	        async:false,
	        success : function(data) {
			response=data;
			},
			error : function(ex) {
				formFieldExceptionHandler(ex);
			}
		});
		return response;
	};

function loadReferenceList(cls,list){
	$("."+cls).empty();
	$("."+cls).append("<option value=''></option>" );
	$.each(list,function(key,value){
		$("."+cls).append("<option value='"+key+"'>"+ value+" </option>" );
	});
}

function loadReferenceListById(id,list){
	$("#"+id).empty();
	$("#"+id).append("<option value=''></option>" );
	$.each(list,function(key,value){
		$("#"+id).append("<option value='"+key+"'>"+ value+" </option>" );
	});
}

function findNextTab(currentTab){
	var nextTab;
	if(currentTab.hasClass('k-last')){
		var parentDiv=currentTab.parents('.k-state-active')[0];
		var parentTab=$('[aria-controls='+$(parentDiv).attr("id")+']');
		nextTab=parentTab.next('li');
	}else{
		nextTab=currentTab.next('li');
	}
	return nextTab;
}

function findPrevTab(currentTab){
	var prevTab;
	if(currentTab.hasClass('k-first')){
		var parentDiv=currentTab.parents('.k-state-active')[0];
		var parentTab=$('[aria-controls='+$(parentDiv).attr("id")+']');
		prevTab=parentTab.prev('li');
	}else{
		prevTab=currentTab.prev('li');
	}
	return prevTab;
}

function onToggleTab(ele){
	var openTab=$(ele).attr('openTab');
	var currentDiv=$(ele).parents().find('div.k-state-active');
	var currentTab=$(ele).parents().find('li.k-state-active');
	var newTab=$("."+openTab);
	var newDiv=$("#"+newTab.attr('aria-controls'));
	currentDiv.removeClass('k-state-active').css('display','none');
	currentTab.removeClass('k-state-active');
	
	newDiv.addClass('k-state-active').css('display','block').css('opacity','1');
	newTab.addClass('k-state-active');
}

function getCurrentTab(){
	return currentTab;
}

function setCurrentTab(ele){
	currentTab=ele;
}

function formFieldExceptionHandler(ex){
	var status=ex.status;
	if(status==401 || status==405 || status==403){
		window.location.href="sessionOut";
	}
	else if(status==200){
		
	}
	else{
		Alert.warn("Exception: " + ex.responseText);	
	}
}