var userId="";
var userDetailsId="";
var userRolesId="";

var email="";
var firstname="";
var middlename="";
var lastname="";
var ofctype="";
var ofclocation="";
var designation="";
var role="";
var userRole='';
var isActive=""

var officeLocation='';

var validate=false;
$( document ).ready(function() {	
	    role=$('#login_user_role').val();
	    if(role=='LOCADM'){
  		  $('#officetype').addClass('readonly');
      	  $('#officelocation').addClass('readonly');
		}
        getUserList();
        //$('.pagination').append('<li><input type="text" class="form-control goinput"/><button class="btn btn-default goinputbtn" type="button">Go</button></li>');
       
           
        
        $('.indirectFormSubmit').click(function(event) {
            event.preventDefault();
            var optionName=$('#officetype').find('option:selected').text();
       	    $('#locationTypeRef').val(optionName);

            submitIt("userform",$('#userform').attr('action'), "processResponse");
            return false;
        });
       /* $('#searchlitralid').on('keyup', function () {
    	    var value = this.value;
    	    $('#example li').hide().each(function () {
    	        if ($(this).text().toUpperCase().search(value.toUpperCase()) > -1) {
    	        	$(this).show();;
    	        }
    	    });
    	});*/
        /*$('#example').paginathing({
            perPage: 7,
            limitPagination: 5,
            containerClass: 'panel-footer',
            pageNumbers: true
          });*/

        //page click action
      /*  $('#pagination-here').on("page", function(event){
            //show / hide content or pull via ajax etc
            $("#example li").html(); 
        });*/
        
        $('.newUserBtn').click(function(event){
        	  $('#userform').attr('action','createUser');
        	  $('.newUserBtn').addClass('readonly');
        	  $('.editUserBtn').addClass('readonly');
        	  $('.cancelbtn').show();
        	  $('.indirectFormSubmit').show();
        	  $('.isActive').prop('checked', true); 
        	  enableForm();
        	  getPrevValues();
        		$("#email").val("");
        		$("#password").val("");
        		$("#firstname").val("");
        		$('#middlename').val("");
        		$("#lastname").val("");
        		if(role!='LOCADM'){
        			$('#officetype').val(0);
            		$('#officelocation').val(0);
        		}
        		$('#designation').val(0);
        		$('#role').val(0);
        		 $('#errorMsg').hide();
        		$('#userId').val('');
        		$('#userDetailsId').val('');
        		$('#userRoleId').val('');
        });
        $('.editUserBtn').click(function(event){
        	  $('#userform').attr('action','updateUser');
        	  $('.editUserBtn').addClass('readonly');
        	  $('.newUserBtn').addClass('readonly');
        	  $('.cancelbtn').show();
        	  $('.indirectFormSubmit').show();
        	  $('#errorMsg').hide();
        	  getPrevValues();
        	  enableForm();
        	  $("#email").attr('readonly','readonly');
        });
        $('.cancelbtn').click(function(event){
        	  $('#userform').attr('action','');
        	  $('.newUserBtn').removeClass('readonly');
        	  $('.editUserBtn').removeClass('readonly');
        	  $('.cancelbtn').hide();
        	  $('#errorMsg').hide();
        	  $('.indirectFormSubmit').hide();
        	 
        	  setPrevValue();
        	  disableForm();
        });

});

function getPrevValues(){ 
	userId=$('#userId').val();
	userDetailsId=$('#userDetailsId').val();
	userRolesId=$('#userRoleId').val();
	
	email=$('#email').val();
	firstname=$('#firstname').val();
	middlename=$('#middlename').val();
	lastname=$('#lastname').val();
	ofctype=$('#officetype').val();
	ofclocation=$('#officelocation').val();
	designation=$('#designation').val();
	userRole=$('#role').val();
	if ($(".isActive").prop("checked") == true)
		isActive = "Y";
	else
	    isActive = "N";
	
	}

function setPrevValue()
{
	$('#userId').val(userId);
	$('#userDetailsId').val(userDetailsId);
	$('#userRoleId').val(userRolesId);
	
	$('#email').val(email);
	$('#firstname').val(firstname);
	$('#middlename').val(middlename);
	$('#lastname').val(lastname);
	$('#officetype').val(ofctype);
	$('#officelocation').val(ofclocation);
	$('#designation').val(designation);
	$('#role').val(userRole);
	if (isActive == "Y")
		$('.isActive').prop('checked', true); 
	else
		$('.isActive').prop('checked', false); 
	
	}
function enableForm()
{
	$('#readonly').removeClass('readonly');
	}
function disableForm()
{
	$('#readonly').addClass('readonly');
	
		$('.newUserBtn').removeClass('readonly');
		$('.editUserBtn').removeClass('readonly');
		$('.cancelbtn').hide();
		$('.indirectFormSubmit').hide();
		$("#email").removeAttr('readonly');
		
	}


function getUserList(){
	$.ajax({
        type : "POST",
        contentType : "application/json",
        url: "getUserList",
        dataType:"json",
        async:false,
        success: function (data) {
        	debugger;
        	if(!isEmpty(data) && !isEmpty(data.objectMap) && !isEmpty(data.objectMap.userRolesList)){
        		appendList(data);
        	}
        	else{
        		  Alert.warn("No User present in List");
        		  disableForm();
        		  $('#masterDetails').append('<div class="row"><label class="col-xs-6" ><h4 class="detail_Name"></h4></label>'
    			            +'<label class="col-xs-6 mytext detail_Code"></label></div> '
    			            +'<div class="row"><label class="col-xs-6 mytext detail_Desc"></label>'
    			            +'<label class="col-xs-6 mytext detail_Active"></label></div>');
        		  $('.CancelBtn').hide();
        		  $('.isActive').attr('checked','checked'); 
        	}  	
        },
        error: function (e) {
			Alert.warn("Exception :");
        }
        
    });
}

function appendRecentRecord(data){	
	$('.pagination').children().remove();
	//$('.paginate-pagination-' + plugin_index).remove();
	var currentUserId=$('.example').find('li.active').attr('id');
	$("#userId").val(data.user.userId);
	if(!$.isEmptyObject(data.user.userDetails))
	$("#userDetailsId").val(data.user.userDetails.userDetailsId);
	$("#userRoleId").val(data.userRolesId);
	if(currentUserId==data.user.userId)
	{
		$('#'+currentUserId+'').remove();
	}else{
		$('#'+currentUserId).removeClass('active');
		var oldCount=$('.reportCount').html();
		$('.reportCount').html(Number(oldCount)+1);
		$('#'+data.publicNoticeId).remove();
	}
	var active=" class='active'";
	$('#example').prepend('<li '+active+' onclick="showUserDetails('+data.user.userId+')" id="'+data.user.userId+'"> <a href="" class="" data-toggle="tab">'
	           +' <div class="col-md-12">'
	           +'  <label class="col-xs-6">'+data.user.name+'</label>'
	           +'   <label class="col-xs-6 mytext" data-userId="'+data.user.userId+'">'+data.user.userDetails.lastName+'</label>'
	           +'  </div>'
	           +'  <div class="col-md-12">'
	           +'    <label class="col-xs-6">'+$('#role').find('option:selected').text()+'</label>'
	           +'    <label class="col-xs-6 mytext2">'+data.user.userDetails.locationTypeRef+'</label>'
	           +'  </div>'
	           +'  </a>'
	           +'  </li>');
	
	$(".userName").html(data.user.userDetails.firstName);
	$(".userEmail").html(data.user.email);
	$(".locAddress").html(data.user.userDetails.location.address1);
	$(".designationName").html(data.user.userDetails.designation.name);
	/*$('#locationTypeRef').val($('#officetype').text());*/
	 $('#errorMsg').hide();
	 /*$('.example').paginate();	
	    plugin.init();*/
	 $('#example').paginathing();
	
	 
}
function renderUserList(data){
	var active=" class='active'";
	$('.pagination').children().remove();
	$('#example').empty();
	for (var i=0;i<data.length;i++)  { 
		if(!$.isEmptyObject(data[i].user.userDetails)){
			
			var userDetailId=data[i].user.userDetails.userDetailsId==null?'':data[i].user.userDetails.userDetailsId;
			var firstName = data[i].user.userDetails.firstName==null?'':data[i].user.userDetails.firstName;
			var middleName = data[i].user.userDetails.middleName==null?'':data[i].user.userDetails.middleName;
			var lastName  = data[i].user.userDetails.lastName==null?'':data[i].user.userDetails.lastName;
			var locationTypeRef =data[i].user.userDetails.locationTypeRef==null?'':data[i].user.userDetails.locationTypeRef;
			var email 	  = data[i].user.userDetails.email==null?'':data[i].user.userDetails.email;
			var mobileNo  = data[i].user.userDetailsmobileNo==null?'':data[i].user.userDetails.mobileNo;
			var designationId = (data[i].user.userDetails.designation==null || data[i].user.userDetails.designation.designationId==null)?'':data[i].user.userDetails.designation.designationId;
			var designationName  = (data[i].user.userDetails.designation==null || data[i].user.userDetails.designation.name==null)?'':data[i].user.userDetails.designation.name;
			var registeredAddress=''
			if(!$.isEmptyObject(data[i].user.userDetails.officeLocation)){
				var locationId= data[i].user.userDetails.officeLocation==null?'':data[i].user.userDetails.officeLocation.officeLocationId;
				registeredAddress = data[i].user.userDetails.officeLocation==null?'':data[i].user.userDetails.officeLocation.name==null?'':data[i].user.userDetails.officeLocation.name;
			}
				if(i==0){
							$("#userId").val(data[i].user.userId);
							$("#email").val(data[i].user.email);
							$("#userRoleId").val(data[i].userRolesId);
							
							if(!$.isEmptyObject(data[i].user.userDetails))
								{
								$("#userDetailsId").val(data[i].user.userDetails.userDetailsId);
									$("#firstname").val(data[i].user.userDetails.firstName);
									$("#middlename").val(data[i].user.userDetails.middleName);
									$("#lastname").val(data[i].user.userDetails.lastName);
									
									if(!$.isEmptyObject(data[i].user.userDetails.officeLocation))
										$("#officelocation").val(data[i].user.userDetails.officeLocation.officeLocationId);
									if(!$.isEmptyObject(data[i].user.userDetails.locationType))
										$("#officetype").val(data[i].user.userDetails.locationType.locationTypeId);
									if(!$.isEmptyObject(data[i].user.userDetails.designation))
										$("#designation").val(data[i].user.userDetails.designation.designationId);
								}
							
							
							
							if(!$.isEmptyObject(data[i].role))
								$("#role").val(data[i].role.roleId);
							
							if(data[i].user.isActive=='Y')
								$('#isActive').prop('checked', true); 
							else
								$('#isActive').prop('checked', false);
							
							$('#locationTypeRef').val(data[i].user.userDetails.locationTypeRef);
							
							$('#masterDetails').empty();
							$('#masterDetails').append('<div class="row"><label class="col-xs-6" ><h4 class="userName"> Name: '+firstName+'</h4></label>'
						            +'<label class="col-xs-6 mytext userEmail">Email: '+email+'</label></div> '
						            +'<div class="row"><label class="col-xs-6 mytext locAddress">Address: '+registeredAddress+'</label>'
						            +'<label class="col-xs-6 mytext designationName">Designation: '+designationName+'</label></div>');
					          
					}
					
				$('#example').append('<li '+active+' onclick="showUserDetails('+data[i].user.userId+')" id="'+data[i].user.userId+'"> <a href="" class="" data-toggle="tab">'
				           +' <div class="col-md-12">'
				           +'  <label class="col-xs-6">'+firstName+'</label>'
				           +'   <label class="col-xs-6 mytext" data-userId="'+data[i].user.userId+'">'+lastName+'</label>'
				           +'  </div>'
				           +'  <div class="col-md-12">'
				           +'    <label class="col-xs-6">'+data[i].role.name+'</label>'
				           +'    <label class="col-xs-6 mytext2">'+locationTypeRef+'</label>'
				           +'  </div>'
				           +'  </a>'
				           +'  </li>');
				active="";
			}
	
         }
	$('#example').paginathing();
}

function appendList(data){
	var objMap=data.objectMap;
	if(role=='LOCADM'){
		officeLocation=objMap.officeLocation;
		loadUserLocation(officeLocation);
	}

	var data=objMap.userRolesList;
	renderUserList(data);
	$('#example').paginathing();
	$(".tabs-left li a label").text(function(index, currentText) {
	    return currentText.substr(0, 20);
	});
	    
	  $('.newUserBtn').removeClass('readonly');
	  $('.editUserBtn').removeClass('readonly');
	  
	  $('.cancelbtn').hide();
	  $('.indirectFormSubmit').hide();
	  disableForm();
	  $('.reportCount').html(data.length)
	}

function showUserDetails(id){ 
	$.ajax({
        type : "POST",
        contentType : "application/json",
        url: "getUserById/"+id,
        dataType:"json",
        async:false,
        success: function (data) {
        	if($.isEmptyObject(data)){
        		  Alert.warn("User Details is Empty");
        		}
        	else{
	        	$("#userId").val(data.user.userId);
				$("#email").val(data.user.email);
				$("#userRoleId").val(data.userRolesId);
				if(!$.isEmptyObject(data.user.userDetails)){
					$("#userDetailsId").val(data.user.userDetails.userDetailsId);
					$("#firstname").val(data.user.userDetails.firstName);
					$("#middlename").val(data.user.userDetails.middleName);
					$("#lastname").val(data.user.userDetails.lastName);
					
					$(".userName").html('Name: '+(data.user.userDetails.firstName==null?'':data.user.userDetails.firstName));
					$(".userEmail").html('Email: '+data.user.email);

					if(!$.isEmptyObject(data.user.userDetails.locationTypeRef)){
						var code=$("#officetype").find('[data-code*="'+data.user.userDetails.locationTypeRef+'"]').val();
						$("#officetype").val(code);
						if(role=='LOCADM'){
							officeLoction=data.user.userDetails.officeLocation;
							loadUserLocation(officeLocation);
						}else{
							setLocationBylocType();
						}
					}else{
						$("#officetype").val('');
					}
					
					if(!$.isEmptyObject(data.user.userDetails.officeLocation))
						{
							$(".locAddress").html('Address: '+(data.user.userDetails.officeLocation==null?'':data.user.userDetails.officeLocation.name==null?'':data.user.userDetails.officeLocation.name));
							$("#officelocation").val(data.user.userDetails.officeLocation.officeLocationId);
						}else{
							$(".locAddress").html('Address: ');
							$("#officelocation").val('');
						}
						
					
						
					if(!$.isEmptyObject(data.user.userDetails.designation))
						{
							$(".designationName").html('Designation: '+data.user.userDetails.designation.name);
							$("#designation").val(data.user.userDetails.designation.designationId);
						}else{
							$(".designationName").html('Designation: ');
							$("#designation").val('');
						}
					
				}
			if(!$.isEmptyObject(data.role))
				$("#role").val(data.role.roleId);
				if(data.user.isActive=='Y')
					$('.isActive').prop('checked', true); 
				else
					$('.isActive').prop('checked', false);
        	}
		        	
        },
        error: function (e) {
			Alert.warn("Exception :");
        }
    });
		disableForm();
		$('.newUserBtn').removeClass('readonly');
		$('.editUserBtn').removeClass('readonly');
		$('.cancelbtn').hide();
		$('.indirectFormSubmit').hide();
		 $('#errorMsg').hide();
	}

function processResponse(data)
{
	$("#loading-wrapper").show();
		if(data.responseStatus==false)
		{
			$("#loading-wrapper").fadeOut("slow");
		  var errorLog = "";
			$.each(data.data,function(key, value){
				errorLog = errorLog+value.errorMessage+"\n ;";
			});
			$('#errorMsg').show();
			$('#errorMsg').html("'"+errorLog+"'")
			/* swal(data.responseMsg,"","error");*/
			Alert.warn(data.responseMsg);
		}
		else
		{
			$("#loading-wrapper").fadeOut("slow");
			console.log(data);
			/*swal(data.responseMsg,"","success");*/
			Alert.info(data.responseMsg);
			disableForm();
			appendRecentRecord(data.data);
		}
	}

function searchUser(){
	var searchliteral=$('#searchLiteralId').val();
	if(searchliteral.trim()=="")
		getUserList();
	else{
		$("#loading-wrapper").show();
			$.ajax({
		        type : "POST",
		        contentType : "application/json",
		        url: "getSearchedUser/"+searchliteral,
		        dataType:"json",
		        asycn:true,
		        success: function (data) {
		        	if($.isEmptyObject(data)){
		        		  Alert.warn("No User Matching the literal");
		        	}else{
		        		renderUserList(data);
		        	}
		        	$("#loading-wrapper").fadeOut();
		        },
		        error: function (e) {
		        	$("#loading-wrapper").fadeOut();
		        	Alert.warn("Excepton :");
		        }
		    });   
	}
		
		
}

function setLocationBylocType(){
		var locType=$("#officetype").find('option:selected').text();
			submitToURL("getOfficeLocation/"+locType, "loadUserLocation");
}

function loadUserLocation(data){
	$("#userform #officelocation").html("");
	var options = '<option value=""> </option>';
	$.each(data,function(key,value){
		options +='<option value="'+value.officeLocationId+'">'+value.name +'</option>';
	});
	$("#userform #officelocation").append(options);
}
/*function loadUserLocationType(data){
	$("#userform #officetype").html("");
	var options = "<option value=''>Select</option>";
	$.each(data,function(key,value){
		options +='<option value="'+value.countryId+'">'+value.name +'</option>'
		
	});
	$("#userform #officetype").append(options);
}
function loadUserLocation(data){
	$("#userform #officelocation").html("");
	var options = "<option value=''>Select</option>";
	$.each(data,function(key,value){
		options +='<option value="'+value.regionId+'">'+value.name +'</option>'
		
	});
	$("#userform #officelocation").append(options);
}
function loadUserRole(data){
$("#userform #role").html("");
	var options = "<option value=''>Select</option>";
	$.each(data,function(key,value){
		options +='<option value="'+value.districtId+'">'+value.name +'</option>'
		
	});
	$("#userform #role").append(options);
}
function loadSigDesignation(data){
	$("#userform #designation").html("");
	var options = "<option value=''>Select</option>";
	$.each(data,function(key,value){
		options +='<option value="'+value.designationId+'">'+value.name +'</option>'
		
	});
	$("#userform #designation").append(options);
}*/
