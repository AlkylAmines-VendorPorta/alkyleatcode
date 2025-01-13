var sessionAuditArray=new Array();
var leftPanePageSize = 10;
var maxVisiblePageNumbers = 3;
var selectedPage = 1;
var sessionString='';
$(document).ready(function(){
	$("#sessionAuditTabId").on("click", function(event) {
		event.preventDefault();
		var searchMode=$("input[name='filterBy']:checked").val();
		var searchValue=$('#searchLiteralId').val();
		if(searchMode!=null && searchValue!=null){
			var data=getSessionAuditList(1, leftPanePageSize, searchMode, searchValue);
			setData(data);
		}else{
			 onPageLoad();
		}
		
	});
	
	$('#pagination-here').paginate({
		pageSize:  10,
		dataSource: 'getSessionAuditList',
		responseTo:  'loadSessionAuditLeftPane',
		maxVisiblePageNumbers:3,
		searchBoxID : 'searchLiteralId',
		loadOnStartup : false	
	});
	
	onPageLoad();
});

function onPageLoad(){
	var data=getSessionAuditList(1, leftPanePageSize, 'none', 'none');
	setData(data);
}
function resetSessionDetails(){
	$("#sessionId").text('');	
	$("#loginTime").text('');
	$("#logoutTime").text('');
	$("#remoteIP").text('');
	$("#userSessionId").val('');
	$("#email").text('');
	$("#deviceType").text('');
	$("#macAddress").text('');
}

function setData(data){
	if(data!=null){
		loadSessionAuditLeftPane(data.data);
		setPagination(data.objectMap.LastPage, selectedPage , maxVisiblePageNumbers);
	}else{
		resetSessionDetails();
		Alert.warn('No Record to render page !');
	}
}
function getSessionAuditResp(data){
	loadSessionAuditLeftPane(data.data);
}

function getSessionAuditList(pageNumber, pageSize, searchMode, searchValue){
	resetSessionDetails();
	var response;
	if(searchValue!=''){
		$.ajax({
	        type : "POST",
	        contentType : "application/json",
	        url: "getSessionList?pageNumber="+pageNumber+"&pageSize="+pageSize+"&searchMode="+searchMode+"&searchValue="+searchValue,
	        dataType:"json",
	        async:false,
	        success: function (data) {
	        	response = data;
	        },
	        error: function (e) {
				Alert.warn(e.message);
	        }
	    });
	}else{
		Alert.warn('Please provide searching literals');
	}
	return response;
}

function onSessionAuditTabLoad(data){
		if(data.objectMap.hasOwnProperty('sessionAudit'))
		loadSessionAuditLeftPane(data.objectMap.sessionAudit);
		setActiveTabName("Session Audit",$('.leftPaneData li').length);
}

function loadSessionAuditLeftPane(data){
	//$('.pagination').children().remove();
	$(".leftPaneData").html("");
	var leftPanelHtml="";
	var i=0;
	var active=false;
	var firstRow=null;
	if(data==null){
		  return;
	}else{
		$.each(data,function(key,value){
			sessionAuditArray["session"+value.userSessionId]=value;
			if(i==0){
				firstRow = value;
				active=true;
			}
			leftPanelHtml=leftPanelHtml+appendSessionAuditData(value,active);
			active=false;
		    i++;
		});
	}
	$(".leftPaneData").append(leftPanelHtml);
	
	$(".tabs-left li a label").text(function(index, currentText) {
	    return currentText.substr(0, 20);
	});
	loadSessionAuditRightPane(firstRow);
}
function appendSessionAuditData(value,active){
	//$('.pagination').children().remove();
	var leftPanelHtml="";
	var userSessionId  = value.userSessionId==null?'':value.userSessionId;
	 if(active){
	    leftPanelHtml = leftPanelHtml + ' <li class="active" onclick="showSessionDetail('+userSessionId+')" id="'+userSessionId+'">';
	 }else{
		leftPanelHtml = leftPanelHtml + ' <li onclick="showSessionDetail('+userSessionId+')" id="'+userSessionId+'">';
	 }
	var sessionId = value.sessionId==null?'':value.sessionId;
	var remoteIp = value.remoteIp==null?'':value.remoteIp;
	var logoutTime  = value.logoutTime==null?'':value.logoutTime;
	var loginTime  = value.loginTime==null?'':value.loginTime;
	
	leftPanelHtml = leftPanelHtml +' <a href="#Master" data-toggle="tab">'
    +' <div class="col-md-12">'
    +'	<label class="col-xs-6" id="sessionLabel-'+userSessionId+'">'+sessionId+'</label>'
    +'	<label class="col-xs-6" id="remoteLabel-'+userSessionId+'">'+remoteIp+'</label>'
	+' </div>'
	+' </a>'
    +' </li>';
	return leftPanelHtml;
	
	$(".tabs-left li a label").text(function(index, currentText) {
	    return currentText.substr(0, 20);
	});
}

function showSessionDetail(id){
	var session=sessionAuditArray["session"+id];
	loadSessionAuditRightPane(session);
}

function loadSessionAuditRightPane(data){
	$('#collapseableDivId').data('open','false');
	$('#collapseableDivId').addClass('collapsed');
	$('#collapseableDivId').attr('aria-expanded','false');
	if(!$.isEmptyObject(data)){
		var userSessionId  = data.userSessionId==null?'':data.userSessionId;
		var sessionId = data.sessionId==null?'':data.sessionId;
		var remoteIp = data.remoteIp==null?'':data.remoteIp;
		var logoutTime  = data.logoutTime==null?'':formatDateTime(data.logoutTime);
		var loginTime  = data.loginTime==null?'':formatDateTime(data.loginTime);
	    var userName=data.userName==null?'':data.userName;
	    var deviceType=data.deviceType==null?'':data.deviceType;
	    var macAddress=data.macAddress==null?'':data.macAddress;
	    sessionString=sessionId;
		$("#sessionId").text(sessionId);	
		$("#loginTime").text(loginTime);
		$("#logoutTime").text(logoutTime);
		$("#remoteIP").text(remoteIp);
		$("#userSessionId").val(userSessionId);
		$("#email").text(userName);
		$("#deviceType").text(deviceType);
		$("#macAddress").text(macAddress);
		var duration="";
		if(logoutTime!=""){
			duration=getDuration(data.loginTime,data.logoutTime);
		}
		$("#duration").text(duration);
		}
}

function getDuration(loginTime,logoutTime){
	var startTime = new Date(loginTime);
	var endTime = new Date(logoutTime);
    var diff = endTime - startTime;
    var diffSeconds = diff/1000;
	var hour = Math.floor(diffSeconds/3600);
	var HH=Math.round(hour);
	var minutes = Math.floor(diffSeconds%3600)/60;
    var MM=Math.round(minutes);
	var formatted = ((HH < 10)?("0" + HH):HH) + ":" + ((MM < 10)?("0" + MM):MM)
	return formatted;	
}

