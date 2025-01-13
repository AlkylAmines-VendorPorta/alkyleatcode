/**
 * akshay
 */
$(document).ready(function() {
	getRecordCountData();
});

function getRecordCountData() {
	jQuery.ajax({
		type : "GET",
		contentType : "application/json",
		url : "dashBoardRecordCount",
		dataType : "json",
		async : true,
		success : function(data) {
			setData(data);
		},
		error : function(e) {
			console.log(e);
		}
	});
};

function setData(data) {

	for ( var i in data.tileCountMap) {
		$('#' + i).removeClass('fa-refresh fa-spin').text(data.tileCountMap[i]);
	}
}