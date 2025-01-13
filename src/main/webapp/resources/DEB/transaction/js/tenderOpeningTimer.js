function autoRefresh(){
	$('#autoRefresh').timer({
	    duration: '5s',
	    callback: function() {
	    	submitWithParam('getLoggedInAuditorList', 'openAllBidForm #tahdrId', 'loggedInUserResp');
	    	submitWithParam('getLoggedInBidderList', 'openAllBidForm #tahdrId', 'loggedInBidderrResp');
	    	if(role!='EXEENGR' && !counter && globalTenderId!=0){
	    		$('#autoRefresh').timer('pause');
	    		$("#loading-wrapper").show();
	    		submitToURL("checkTenderOpened/" + globalTenderId,
				"viewBidFuction");
	    		$('#autoRefresh').timer('resume');
	    		$("#loading-wrapper").hide();
	    	}
	    	$('#autoRefresh').timer('reset');
	    },
	   repeat: true
	});
}
function viewBidFuction(data){
	if(data.objectMap.result){
		if(data.objectMap.tenderStatus==tenderOpeningType){
			$('#viewBidBtnId_1').removeAttr('style');	
			counter=true;
		}
	}
	
}
