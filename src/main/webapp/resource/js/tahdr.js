$( document ).ready(function() {
	$('.formSubmit').click(function(event){
  	   event.preventDefault();
  	   var formObj = $(this).parents('form:first');
  	   submitForm(formObj);
  	   return false;
  });
	
	function submitForm(formObj){
		var formdata=formObj.serialize();
		var uri =formObj.attr( 'action' );
		$.ajax({
	        type : "POST",
	        data : formdata,
	        contentType : "application/json",
	        url: uri,
	        dataType:"json",
	        async:false,
	        success: function (data) {
	        	if(data.hasError==false)
	        		{
	        		  /*swal("User Not ADDED","","error");*/
	        		}
	        	else
	        	{
		        	console.log(data);
		        	/*swal("User Added Successfully","","success");*/
	        	}
			        	
	        },
	        error: function (e) {
				/*swal(e.message);*/
	        }
	    });
	}
	
})