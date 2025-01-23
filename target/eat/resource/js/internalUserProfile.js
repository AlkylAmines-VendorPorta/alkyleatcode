/**
 * Aman
 */

$( document ).ready(function() {
	submitToURL("getUserProfileDetail", "setUserProfileDetailLeftPanel");
});

function setUserProfileDetailLeftPanel(data){
	var active=" class='active'";
	$('#example').empty(); 
		if(!$.isEmptyObject(data.objectMap.userRole)){
			var data=data.objectMap.userRole;
			var firstName = data.user.userDetails.firstName==null?'':data.user.userDetails.firstName;
			var email 	  = data.user.userDetails.email==null?'':data.user.userDetails.email;
			var designationName  = (data.user.userDetails.designation==null || data.user.userDetails.designation.name==null)?'':data.user.userDetails.designation.name;
			var registeredAddress = data.user.userDetails.locationTypeRef==null?'':data.user.userDetails.locationTypeRef;
			
			$('#example').append('<li '+active+' onclick="showUserDetails('+data.user.userId+')" id="'+data.user.userId+'"> <a href="" class="" data-toggle="tab">'
	           +' <div class="col-md-12">'
	           +'  <label class="col-xs-6">'+data.user.name+'</label>'
	           +'   <label class="col-xs-6 mytext" data-userId="'+data.user.userId+'">'+data.user.userDetails.lastName+'</label>'
	           +'  </div>'
	           +'  <div class="col-md-12">'
	           +'    <label class="col-xs-6">'+data.role.name+'</label>'
	           +'    <label class="col-xs-6 mytext2">'+data.user.userDetails.locationTypeRef+'</label>'
	           +'  </div>'
	           +'  </a>'
	           +'  </li>');
			setUserProfileDetailRightPanel(data);
			$('#masterDetails').empty();
			$('#masterDetails').append('<div class="row"><label class="col-xs-6" ><h4 class="userName">Name: '+firstName+'</h4></label>'
		            +'<label class="col-xs-6 mytext userEmail">Email: '+email+'</label></div> '
		            +'<div class="row"><label class="col-xs-6 mytext locAddress">Address: '+registeredAddress+'</label>'
		            +'<label class="col-xs-6 mytext designationName">Designation: '+designationName+'</label></div>');
		}
}

function setUserProfileDetailRightPanel(data){
	$("#email").val(data.user.email);
	if(!$.isEmptyObject(data.user.userDetails)){
		$("#userDetailsId").val(data.user.userDetails.userDetailsId);
		$("#firstname").val(data.user.userDetails.firstName);
		$("#middlename").val(data.user.userDetails.middleName);
		$("#lastname").val(data.user.userDetails.lastName);
		
		$(".userName").html(data.user.userDetails.firstName);
		$(".userEmail").html(data.user.email);

		if(!$.isEmptyObject(data.user.userDetails.officeLocation)){
				$(".locAddress").html(data.user.userDetails.officeLocation.name);
				$("#officelocation").val(data.user.userDetails.officeLocation.name);
			}
			
		if(!$.isEmptyObject(data.user.userDetails))
			$("#officetype").val(data.user.userDetails.locationTypeRef);
		if(!$.isEmptyObject(data.user.userDetails.designation)){
				$(".designationName").html(data.user.userDetails.designation.name);
				$("#designation").val(data.user.userDetails.designation.name);
			}
	}
	if(!$.isEmptyObject(data.role)){
		$("#role").val(data.role.name);
		/*if(data.role.isAdmin=='Y'){
			$('.profileSettingClass').removeAttr('style');
		}else{
			$('.profileSettingClass').attr('style','display : none ;');
		}*/
	}
	
	if(data.user.isActive=='Y')
		$('.isActive').prop('checked', true); 
	else
		$('.isActive').prop('checked', false);
}