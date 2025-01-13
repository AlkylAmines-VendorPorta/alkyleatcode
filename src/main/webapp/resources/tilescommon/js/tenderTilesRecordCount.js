/**
 * akshay
 */
$(document).ready(function() {
	var tileID = $('#tahdrTileType').val();
	getRecordCountData(tileID);
});

function getRecordCountData(tileID) {
	jQuery.ajax({
		type : "POST",
		contentType : "application/json",
		url : "tahdrRecordCount/"+tileID,
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