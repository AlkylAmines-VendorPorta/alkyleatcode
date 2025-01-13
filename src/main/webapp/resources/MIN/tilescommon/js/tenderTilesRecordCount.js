/**
 * akshay
 */
$(document).ready(function() {
	/*alert();*/
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
			/*alert("error");*/
		}
	});
};

function setData(data) {
	/*alert();*/
	for ( var i in data.tileCountMap) {
		$('#' + i).removeClass('fa-refresh fa-spin').text(data.tileCountMap[i]);
	}
}