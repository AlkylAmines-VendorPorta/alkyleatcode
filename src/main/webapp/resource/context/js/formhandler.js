$( document ).ready(function() {
   $('.formSubmit').click(function(){
	   var formObj = $(this).parents('form:first');
	   submit(formObj);
   });
});

function postJSON(formObj){
	debugger;
	var formData = formObj.serializeJSON() ;
	var uri =formObj.attr( 'action' );
	$.ajax({
        type : "POST",
        data : JSON.stringify(formData),
        contentType : "application/json",
        url: uri,
        dataType:"json",
        
        success: function (data) {
        	debugger;
        	processResponse(data);
        	 alert("Ok");
        },
        error: function (e) {
			alert(e.message);
        }
    });
	
}

function postForm(formObj){
	debugger;
	var formData = formObj.serialize() ;
	var uri =formObj.attr( 'action' );
	$.ajax({
        type : "POST",
        data : formData,
        url: uri,        
        success: function (data) {
        	debugger;
        	processResponse(data);
        	alert("Ok");
        },
        error: function (e) {
			alert(e.message);
        }
    });	
}