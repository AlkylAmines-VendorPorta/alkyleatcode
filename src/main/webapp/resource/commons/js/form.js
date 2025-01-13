$( document ).ready(function() {
   $('.formSubmit').click(function(event){
	   event.preventDefault();
	   var formObj = $(this).parents('form:first');
	   submitForm(formObj);
	   return false;
   });
});

function submitForm(formObj){
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
//        	processResponse(data);
        	 alert("Ok");
        },
        error: function (e) {
			alert(e.responseText);
        }
    });
	return false;
}