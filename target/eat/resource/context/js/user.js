/*$(document).ready(function (){
	console.log("Ready to register");
	$('#register').click(function (){

		postJSON();
	});
});

function postJSON(){
	debugger;
	var formData = $('#userForm').serializeJSON() ;
	var uri = 'register';
	$.ajax({
        type : "POST",
        data : formData,
        contentType : "application/json",
        url: uri,
        dataType:"json",
        
        success: function (data) {
        	debugger;
        	processResponse(data);
        	 alert("Ok");
        },
        error: function (e) {
			alert(e);
        }
    });
	
}
*/

function processResponse(data){
	
}
