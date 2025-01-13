function checklogin() {
	debugger;
	var user={};
	user.username=$("#username").val();
	user.password=$("#password").val();
	if($("#username").val().trim() !="" || $("#password").val().trim() !="")
		{
			$.ajax({
		        type : "GET",
		        url: "Dashboard",
		        /*data : JSON.stringify(user),
		        contentType : "application/json",*/
		        success: function (data) {
		        	/*if(data.responseCode==200)
		        	{
		        		window.location="Dashboard";
		        	}
		        	else
		        	{
		        		$("#errorMsg").html(data.responseMsg);
		        	}*/
		        },
		        error: function (e) {
		        	alert("error"+e);
		        }
		    });
		}
	else
	$("#errorMsg").html("Enter Credentials");
}