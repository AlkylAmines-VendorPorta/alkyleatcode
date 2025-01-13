function autoRefresh(){
	debugger;
	$('#autoRefresh').timer({
	    countdown: true,
	    duration: '5s',
	    callback: function() {
	    	submitWithParam('getLoggedInAuditorList', 'openAllBidForm #tahdrId', 'loggedInUserResp');
	    	submitWithParam('getLoggedInBidderList', 'openAllBidForm #tahdrId', 'loggedInBidderrResp');
	    	$('#autoRefresh').timer('reset');
	    },
	   repeat: true
	});
}