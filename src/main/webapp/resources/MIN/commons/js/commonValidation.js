var validationFlag=false;
var arr_error=[
	"Enter Six Digit Pincode",
	"Enter Only number",
	"Password and Confirmed Password do not match",
	"Invalid Email",
	"The password you have entered does not match the guidelines mentioned above. Please re-enter.",
	"This Field is required",
	"Enter Correct Mobile No.",
	"Invalid Number",
	"Pan Card should be eg.ABCDF1234H",
	"From date should be less than To date",
	"To date should be greater than From date",
	"Invalid Decimal value",
	"Enter only alphabetes",
	"Enter only alpha-numeric value",
	"Special character not allowed",
	"Enter value more than Zero",
	"Enter correct Telephone no.",
	"Enter correct Year value",
	"Enter correct Tractor Sr No",
	"Enter correct Registration No",
	"Credit Limit below 10k only",
	"Enter alpha numeric with single hyphen",
	"Select One",
	"IFSC Code should be eg. 11 digit alphanumeric",
	"Enter proper year",
	"File is Mandatory",
	"Only / or - special characters can be used",
	"GSTIN No should be eg.12PANNO1234R1WE1",
	"Date is Mandatory",
	"Cannot be Zero",
	"Only Number with or without decimal"
 ];

$(document).ready(function(){
	
	checkKeyPressEvents();
	$('.mobile,.multiplemobile,.onlyNumber,.pincode,.telephone,.year').on('keypress', function(e) {
        debugger;
		var k = (e.which) ? e.which : e.keyCode;
        var ok = k >= 48 && k <= 57 || k==127 || k==8 || k==9; // 0-9
     
        if (!ok){
            e.preventDefault();
        }
	})
	
	$(".dropDown,.requiredField,.requiredDate,.requireddot,.emailaddress,.ifscCode,.panNumber,.gstIn,.onlyNumber,.onlyNumberWithDecimal,.mobile,.requiredalphabetics,.requiredalphabeticsWithSpace,.requiredalphanumericWithSpaceAndDot,.multiplemobile,.pincode,.telephone,.expYear,.alphaNumeric,.alphaNumericWithSpace,.alphaNumericWithSpaceAndSpecialCharacter,.moreThanZero,.requiredFile,.requiredDate").change(function(){
		
		if($(this).val()!="" || $(this).attr('disabled')=='disabled'){
			$(this).removeClass('errorinput');
	   	}else{
	   		$(this).addClass('errorinput');
	   	}
   });
  $(".requiredField").on('keyup', function (){
		$(this).removeAttr('style');
		/*$(this).removeAttr('title');*/
   });
			
	
	});
function checkValidationOnSubmit(id){
	validationFlag=true;
	
	var isValid=false;
	var valArray =$('#'+id).find(".atleastOneField");
	if(valArray.length>0){
		if(!checkOfAtleastOne(valArray)){
			$("#atleastOneFieldMsg").show();
			validationFlag= false;
		}
		
	}else{
		$("#atleastOneFieldMsg").hide();
	}
	var valArray =$('#'+id).find('input, textarea,select');


$.each(valArray,function(index,obj){
	
	if($(obj).attr("disabled")!="disabled" && $(obj).attr("type")!="hidden"){
		
		// Decimal point number
		
		if($(obj).hasClass("requireddot"))
		 /*$("#"+id+" .requireddot").each(function(e)*/
				 {
					 if($(this).val()==null || $(this).val()==""){
						 $(this).removeClass('errorinput');
				    	   removeValidationMsg(this);
				    	   if(!$(this).hasClass('requiredField'))
				    		   $(this).val('0');
				     }else{	
			            var nostr =/^[0-9]*\.?[0-9]*$/; 
					    if(checkPattern($(this).val(),nostr)){
					    	var arr=$(this).val().split('');
					    	if(arr[arr.length-1]=='.'){
					    		if(!$(this).hasClass('requiredField'))	
					    			$(this).val('0');
					    		$(this).addClass('errorinput');
					    	     setValidationMsg(11,this);
					    	     validationFlag=false;
					    	}else{
					    		$(this).removeClass('errorinput');
					    		removeValidationMsg(this);
					    	}
					    }else{
					    	if(!$(this).hasClass('requiredField'))
					    	     $(this).val('0');
					    	$(this).addClass('errorinput');
					    	     setValidationMsg(11,this);
					    	     validationFlag=false;
					    }
				     }   
		} 
		// Email Address validation
		if($(obj).hasClass("emailaddress"))
		
		{
			    if ($(this).val()==""){
			    	$(this).removeClass('errorinput');
			    	removeValidationMsg(this);
			    } else{	
			    	var emailReg = /^([\w-\.]+@([\w-]+\.)+[\w-]{2,4})?$/;
			    	/*if(checkPattern($(this).val(),emailReg)){*/
			    	if(emailReg.test($(this).val())){
			    		$(this).removeClass('errorinput');	
			    		removeValidationMsg(this);
			    	}else{
			    		$(this).addClass('errorinput');
			    		setValidationMsg(3,this);
			    		validationFlag=false;
			    	}
			    }    
		  }
		
		// ankita
		if($(obj).hasClass("onlyone")){
			if($(this).val()!="" || $(this).attr('disabled')=='disabled'){
				$(this).removeClass('errorinput');
		   	}else{
		   		$(this).addClass('errorinput');
		   	}
		  }
		
		// IFSC validation
		if($(obj).hasClass("ifscCode")){
		    if ($(this).val()==""){
		    	$(this).removeClass('errorinput');
		    	removeValidationMsg(this);
		    }else{	
		    	var ifscCode = /^([A-Z]{4}[A-Z0-9]{7})+$/;
		    	if(ifscCode.test($(this).val())){
		    		$(this).removeClass('errorinput');
		    			removeValidationMsg(this);
		    	}else{
		    		$(this).addClass('errorinput');
		    		setValidationMsg(23,this);
		    		validationFlag=false;
		    	}
		    }    
	     }
		
		if($(obj).hasClass("onlyNumberWithDecimal"))
		{
	    if ($(this).val()==""){
	    	$(this).removeClass('errorinput');
	    	removeValidationMsg(this);
	    }else{	
	    	var onlyNumberWithDecimal =/^\d+(?:\.\d{1,2})?$/;    // /^[0-9]+([,.][0-9]+)?$/g; 
	    	if(onlyNumberWithDecimal.test($(this).val())){
	    		$(this).removeClass('errorinput');
	    			removeValidationMsg(this);
	    	}else{
	    		$(this).addClass('errorinput');
	    		setValidationMsg(30,this);
	    		validationFlag=false;
	    	}
	    }    
       }
		if($(obj).hasClass("panNumber"))
		{
			
	    if ($(this).val()==""){
	    	$(this).removeClass('errorinput');
	    	removeValidationMsg(this);
	    }else{	
	    	var panno = /^([a-zA-Z]{5}[0-9]{4}[a-zA-Z]{1})+$/;
	    	if(panno.test($(this).val())){
	    		$(this).removeClass('errorinput');
	    			removeValidationMsg(this);
	    	}else{
	    		$(this).addClass('errorinput');
	    		setValidationMsg(8,this);
	    		validationFlag=false;
	    	}
	    }    
  }
		if($(obj).hasClass("gstIn"))
		{
			 
	    if ($(this).val()==""){
	    	$(this).removeClass('errorinput');
	    	removeValidationMsg(this);
	    }else{	
	    	var gstin = /^([0-9]{2}[a-zA-Z]{5}[0-9]{4}[a-zA-Z]{1}[0-9]{1}[a-zA-Z]{1}[a-zA-Z0-9]{1})+$/;
	    	if(gstin.test($(this).val())){
	    		$(this).removeClass('errorinput');
	    			removeValidationMsg(this);
	    	}else{
	    		$(this).addClass('errorinput');
	    		setValidationMsg(27,this);
	    		validationFlag=false;
	    	}
	    }    
  }
		// Validate Number
		if($(obj).hasClass("onlyNumber"))  
		{
			  
			  if($(this).val()==null || $(this).val()==""){
				  $(this).removeClass('errorinput');
				   removeValidationMsg(this);
				   if(!$(this).hasClass('requiredField')){
					   
				   }
					  
			  }else{
				  if($(this).val().match('^[0-9]+$')){
					  $(this).removeClass('errorinput');
			    		 removeValidationMsg(this);
			      }else{
			    	  if(!$(this).hasClass('requiredField'))
			    		  $(this).addClass('errorinput');
			    		 	setValidationMsg(1,this);
			    		 	validationFlag=false;
				  }
			  }
			    	
		  }
		 // Mobile Number Validation
		if($(obj).hasClass("mobile"))
			{
			 
			 if($(this).val()==null || $(this).val()==""){
				 $(this).addClass('errorinput');
				 setValidationMsg(6,this);
			 }else{
				 var mobileNo = /^([0-9]{10})+$/;
				 /*if($(this).val().match('^[0-9]+$')){*/
				 if(mobileNo.test($(this).val())){
				 	 if ($(this).val()<999999999){
				 		$(this).addClass('errorinput');
							setValidationMsg(6,this);
							validationFlag=false;
			    		}else{
			    			$(this).removeClass('errorinput');
							removeValidationMsg(this);
						}
				 }else{
					 $(this).addClass('errorinput');
					 setValidationMsg(6,this);
					 validationFlag=false;
				 }
			   }
		  }
		// Alphabetics Validation
		if($(obj).hasClass("requiredalphabetics")){
			
				var val=$(this).val();
				var alphaExp = /^[a-zA-Z ]+$/;
				if(val==null || val==""){
					$(this).removeClass('errorinput');
					removeValidationMsg(this);
				}else{	
				   if(val.charAt(0)==' '){  
						   $(this).val("");
						   $(this).addClass('errorinput');
						   setValidationMsg(12,this);
						   validationFlag=false;
					}else if($(this).val().match(alphaExp)){
						$(this).removeClass('errorinput');
						removeValidationMsg(this);
					}else{
						$(this).addClass('errorinput');
						  setValidationMsg(12,this);
						  validationFlag=false;
					}
				}
			}
		 
		if($(obj).hasClass("requiredalphabeticsWithSpace")){
			
				var val=$(this).val();
				var alphaExp = /^[a-zA-Z ]+$/;
				if($(this).val()==null || $(this).val()==""){
					$(this).removeClass('errorinput');
			    	   removeValidationMsg(this);
			    }else{
					if(val.charAt(0)==' '){
						$(this).addClass('errorinput');
						   setValidationMsg(12,this);
						   validationFlag=false;
					}else if($(this).val().match(alphaExp)){
						$(this).removeClass('errorinput');
						removeValidationMsg(this);
					}else{
						$(this).addClass('errorinput');
						  setValidationMsg(12,this);
						  validationFlag=false;
					}
			    }
			}
		if($(obj).hasClass("requiredalphanumericWithSpaceAndDot")){
			
			var val=$(this).val();
			var alphaExp = /^[a-zA-Z0-9\.\s]+$/;
			if($(this).val()==null || $(this).val()==""){
				$(this).removeClass('errorinput');
		    	   removeValidationMsg(this);
		    }else{
				if(val.charAt(0)==' '){
					$(this).addClass('errorinput');
					   setValidationMsg(13,this);
					   validationFlag=false;
				}else if($(this).val().match(alphaExp)){
					$(this).removeClass('errorinput');
					removeValidationMsg(this);
				}else{
					$(this).addClass('errorinput');
					  setValidationMsg(13,this);
					  validationFlag=false;
				}
		    }
		}
		// Multiple Mobile Number Validation
		if($(obj).hasClass("multiplemobile")){
				var val=$(this).val();
				var alphaExp = /^(\d{10},)*\d{10}$/; 
				if($(this).val()==null || $(this).val()==""){
					$(this).removeClass('errorinput');
			    	   removeValidationMsg(this);
			    }else{
					if(val.charAt(0)==' '){  
						$(this).addClass('errorinput');
						setValidationMsg(6,this);
						validationFlag=false;
					}else if($(this).val().match(alphaExp)){
						$(this).removeClass('errorinput');
						removeValidationMsg(this);
					}else{
						$(this).addClass('errorinput');
						setValidationMsg(6,this);
						validationFlag=false;
					}
			    }
			}
		
		//Pin Code validation 
		if($(obj).hasClass("pincode")){ 
			   var pin_val=$(this).val();
			   if(pin_val==null || pin_val=="" || pin_val.length==0){
				   $(this).removeClass('errorinput');
				   removeValidationMsg(this);
			   }else
				 {
				   if($(this).val().match('^[0-9]+$')){
					   if(pin_val.length<6 || pin_val.length>6){
						   $(this).addClass('errorinput');
						   setValidationMsg(0,this);
						   validationFlag=false;
					   }else{
						   $(this).removeClass('errorinput');
						   removeValidationMsg(this);
					   }
				   }else{
					   $(this).addClass('errorinput');
					   setValidationMsg(1,this);
					   validationFlag=false;
				   }
				 }
		  }
		
		// Telephone Number Validation
		if($(obj).hasClass("telephone")){ 
			   var val=$(this).val();
			   if(val==null || val=="" || val.length==0){
				   $(this).removeClass('errorinput');
				   removeValidationMsg(this);
			   }else
				 {
				   if($(this).val().match('^[0-9]+$')){
					   if(val.length<8 || val.length>15){
						   $(this).addClass('errorinput');
						   setValidationMsg(16,this);
						   validationFlag=false;
					   }else{
						   $(this).removeClass('errorinput');
						   removeValidationMsg(this);
					   }
				   }else{
					   $(this).addClass('errorinput');
					   setValidationMsg(1,this);
					   validationFlag=false;
				   }
				   
				 }
		  }
		
		// Experience year  Validation
		if($(obj).hasClass("expYear")){
			  
			   var val=$(this).val();
			   if(val==null || val=="" || val.length==0){
				   $(this).removeClass('errorinput');
				   removeValidationMsg(this);
			   }else
				 {
				   if($(this).val().match('^[0-9]+$')){
					   if(val.length<1 || val.length>2){
						   $(this).addClass('errorinput');
						   setValidationMsg(24,this);
						   validationFlag=false;
					   }else{
						   $(this).removeClass('errorinput');
						   removeValidationMsg(this);
					   }
				   }else{
					   $(this).addClass('errorinput');
					   setValidationMsg(1,this);
					   validationFlag=false;
				   }
				   
				 }
		  }
		
		//From Date validation 
		if($(obj).hasClass("fromDate")){
			   var toDate=$('.toDate').val();
			   var fromDate=$(this).val();
			   if(toDate==null || toDate=="" || toDate.length==0){
				 //  removeValidationMsg(this);
			   }else{
				   nfrdt=fromDate.substring(3,5)+'/'+fromDate.substring(0,2)+'/'+fromDate.substring(6);
					  ntodt=toDate.substring(3,5)+'/'+toDate.substring(0,2)+'/'+toDate.substring(6);
					  var frmdt=new Date(nfrdt);
					  var todt=new Date(ntodt);
					  var frdd=frmdt.getDate();
					  var frmm=frmdt.getMonth()+1;
					  var fryy=frmdt.getFullYear();
					   if(frdd<10){
						   frdd='0'+frdd;
					   }
					   if(frmm<10){
						   frmm='0'+frmm;
					   }
					   frmdt=fryy+'-'+frmm+'-'+frdd;
					   
					   todd=todt.getDate();
					   tomm=todt.getMonth()+1;
					   toyy=todt.getFullYear();
					   if(todd<10){
						   todd='0'+todd;
					   }
					   if(tomm<10){
						   tomm='0'+tomm;
					   }
					   todt=toyy+'-'+tomm+'-'+todd;
					   
					   if(frmdt>todt){
						   $(this).addClass('errorinput');
						   setValidationMsg(9,this);
						   validationFlag=false;
					   }else{
						   $(this).removeClass('errorinput');
						   removeValidationMsg(this);
					   }
				 }
	    }
		 
		//To Date validation 
		if($(obj).hasClass("toDate")){
			   var fromDate=$('.fromDate').val();
			   var toDate=$(this).val();
			   if(toDate==null || toDate=="" || toDate.length==0){
				   //removeValidationMsg(this);
			   }else{
				  if(fromDate==null || fromDate=="" || fromDate.length==0){
					  $(this).removeClass('errorinput');
					  removeValidationMsg(this);
				  }else{
					  nfrdt=fromDate.substring(3,5)+'/'+fromDate.substring(0,2)+'/'+fromDate.substring(6);
					  ntodt=toDate.substring(3,5)+'/'+toDate.substring(0,2)+'/'+toDate.substring(6);
					  var frmdt=new Date(nfrdt);
					  var todt=new Date(ntodt);
					  var frdd=frmdt.getDate();
					  var frmm=frmdt.getMonth()+1;
					  var fryy=frmdt.getFullYear();
					   if(frdd<10){
						   frdd='0'+frdd;
					   }
					   if(frmm<10){
						   frmm='0'+frmm;
					   }
					   frmdt=fryy+'-'+frmm+'-'+frdd;
					   
					   todd=todt.getDate();
					   tomm=todt.getMonth()+1;
					   toyy=todt.getFullYear();
					   if(todd<10){
						   todd='0'+todd;
					   }
					   if(tomm<10){
						   tomm='0'+tomm;
					   }
					   todt=toyy+'-'+tomm+'-'+todd;
					   
					   if(frmdt>todt){
						   $(this).addClass('errorinput');
						   setValidationMsg(10,this);
						   validationFlag=false;
					   }else{
						   $(this).removeClass('errorinput');
						   removeValidationMsg(this);
					   }  
				  } 
			   }
		  }
		
		if($(obj).hasClass("dropDown")){
			  
			  if($(this).val()!=""){
				  $(this).removeClass('errorinput');
		    		 removeValidationMsg(this);
			  }else{
		    	$(this).addClass('errorinput');
		    	setValidationMsg(22,this);
		    	validationFlag=false;
			  }
		  }
		// Alpha Numeric Validation
		if($(obj).hasClass("alphaNumeric")){
		
			
				   if($(this).val()==null || $(this).val()==""){
					   $(this).removeClass('errorinput');
			    	   removeValidationMsg(this);
			       }else{
				    	 if($(this).val().match(/^[a-zA-Z0-9]+$/)){
				    		 $(this).removeClass('errorinput');
				    		 removeValidationMsg(this);
				    	 }else{
				    		 $(this).addClass('errorinput');
				    		 	setValidationMsg(13,this);
				    		 	validationFlag=false;
				    	}
			       }
		  }
		
		// Alpha Numeric with Space Validation
				if($(obj).hasClass("alphaNumericWithSpace")){
					
			 	   if($(this).val()==null || $(this).val()==""){
			 		  $(this).removeClass('errorinput');
			    	   removeValidationMsg(this);
			       }else{
				    	 if($(this).val().match(/^[a-zA-Z0-9 ]+$/)){
				    		 $(this).removeClass('errorinput');
				    		 removeValidationMsg(this);
				    	 }else{
				    		 $(this).addClass('errorinput');
				    		 setValidationMsg(13,this);
				    		 validationFlag=false;
				    	}
			       }
		  }
				
				// Alpha Numeric with Space and special characters Validation
				if($(obj).hasClass("alphaNumericWithSpaceAndSpecialCharacter")){
					
			 	   if($(this).val()==null || $(this).val()==""){
			 		  $(this).removeClass('errorinput');
			    	   removeValidationMsg(this);
			       }else{
				    	 if($(this).val().match(/^[a-zA-Z0-9\/\-\s]+$/)){
				    		 $(this).removeClass('errorinput');
				    		 removeValidationMsg(this);
				    	 }else{
				    		 $(this).addClass('errorinput');
				    		 setValidationMsg(26,this);
				    		 validationFlag=false;
				    	}
			       }
		  }
				
		 // Number greater than zero
		if($(obj).hasClass("moreThanZero")){
			  if(Number($(this).val())>0){
				  $(this).removeClass('errorinput');
		    		 removeValidationMsg(this);
		    	}else{
		    		$(this).addClass('errorinput');
		    		setValidationMsg(15,this);
		    	}
		  }
		
		 /* For File Mandatory */
		if($(obj).hasClass("requiredFile")){
			 
			var hiddenField=$(this).data('id');
			  if($("#"+hiddenField).val()!=""){
				     $(this).removeClass("errorinput");
		    		 removeValidationMsg(this);
		    		 
		    	}else{
		    		$(this).addClass('errorinput');
		    		setValidationForFile(25,this);
		    		validationFlag=false;
		    	}
		  }
		 /* For Date Mandatory*/
		if($(obj).hasClass("requiredDate")){
			 
			  if($(this).val()!=""){
				     $(this).removeClass("errorinput");
		    		 removeValidationMsg(this);
		    		 
		    	}else{
		    		$(this).addClass('errorinput');
		    		setValidationForFile(28,this);
		    		validationFlag=false;
		    	}
		  }
		
		if(validationFlag){	
			   
			   var h=1
			   if($(obj).hasClass("requiredField")){
			   
				   
				    if ($(this).val()==""  || $(this).val()=="select"){
						if(h==1){
							console.log('mandatory field-->>'+$(this).attr('id'));
							$(this).focus();
						} 
						h++;
						$(this).addClass('errorinput');
				    	setValidationMsg(5,this);
						validationFlag=false;
					 }else{
						 $(this).removeClass('errorinput');
						 removeValidationMsg(this);
						 
					 }
			   }
		   }
			
	}
	
	
	
});
return validationFlag;
}

function checkOfAtleastOne(valArray){
	 var isValid=false;
	var fieldsArray =valArray;
		$.each(fieldsArray,function(index,obj){
			
			if($(obj).val()!=null){
			if($(obj).val().trim()!=""){
				isValid=true;
			  }
			}
		});
	return isValid;
}



function setValidationForFile(msgNo,field)
{
	 
	var msg=arr_error[msgNo];
	$(field).val(''); 
	$(field).attr('placeholder',msg);
	$(field).attr('title',msg);
	$(field).addClass("errorinput");
	tabAlert();
	
}
function setValidationMsg(msgNo,field){
	var msg=arr_error[msgNo];
	 
	    $(field).attr('title',msg);
	    $(field).addClass("errorinput");
	    console.log($(field).attr('name')+'  '+msg);
		tabAlert();
	

} 

function removeValidationMsg(field){
	 
	$(field).removeClass('plchBorder');
	//Start code to remove hightlight error
	var result = $(".tab-pane.active").find(".plchBorder");
	//console.log("removeValidationMsg: "+result.length);
	if(result.length == 0)
		$('li.active > a').removeClass('tab-error-cls');
	else
		$('li.active > a').addClass('tab-error-cls');
	//End code to remove hightlight error
	
}

function highlightTabs(el){
	 
	var $tabpane = el.closest('.tab-pane');
	if($tabpane.length > 0){ /* check if it is a tab-pane*/
	    var $tabpaneid = $tabpane[0].id // finding closest tab-pane id //** added by swapnil **//
	    var $tabpanelink = $("a[href='#"+$tabpaneid+"']").first() // finding link for the id  //  added by swapnil //
	}
	
	 if($tabpane.length > 0){ /* check if it is a tab-pane*/	        
		 $tabpanelink.addClass('tab-error-cls');        
    }
	 
}
function tabAlert(){
 
	if($('.tab-pane').hasClass('active')){
		var result = $(".tab-pane.active").find(".plchBorder");
		//console.log("setValidationMsg: "+result.length);
		if(result.length > 0)
			$('li.active > a').addClass('tab-error-cls');
		else
			$('li.active > a').removeClass('tab-error-cls');		
	}
	
	$.each($('.tab-pane'), function(key, value) {
		var result = $(value).find(".plchBorder");
		if (result.length > 0) {
			highlightTabs($(value))
		} else {
			
		}
	});
	
};

function checkKeyPressEvents(){
	 
	// Restrict to enter letters in Number field
	$('.mobile,.multiplemobile,.onlyNumber,.pincode,.telephone,.year').on('keypress', function(e) {
        var k = (e.which) ? e.which : e.keyCode;
        var ok = k >= 48 && k <= 57 || k==127 || k==8 || k==9; // 0-9
     
        if (!ok){
            e.preventDefault();
        }
	});
	
	// Restrict to enter letters in Number field
	$('.decimalNumber').on('keypress', function(e) {
        var k = (e.which) ? e.which : e.keyCode;
        var ok = k >= 48 && k <= 57 || k==127 || k==8 || k==46 || k==9; // 0-9
     
        if (!ok){
            e.preventDefault();
        }
	});
	
	// Restrict to enter letters in Number field and multiple dots and only 2 numbers after decimal
	$('.onlyNumberWithDecimal').on('keypress', function(e) {
	
	  if ((event.which != 46 || $(this).val().indexOf('.') != -1)&&(event.which < 48 || event.which > 57)) {
		    //alert('hello');
		   		if((event.which != 46 || $(this).val().indexOf('.') != -1)){
		      }
		      event.preventDefault();
		   }
		   if(this.value.indexOf(".")>-1 && (this.value.split('.')[1].length > 1))		{
		        event.preventDefault();
		    }
}); 
		   
	// Restrict to enter number in Alphabet field
	$('.requiredalphabetics').on('keypress', function(e) {
        var k = (e.which) ? e.which : e.keyCode;
        var ok = k >= 65 && k <= 90 || // A-Z
            k >= 97 && k <= 122 || k==127 || k==8 || k==9; // a-z
     
        if (!ok){
            e.preventDefault();
        }
	}); 
	
	// Restrict to enter number in Alphabet and space field
	$('.requiredalphabeticsWithSpace').on('keypress', function(e) {
        var k = (e.which) ? e.which : e.keyCode;
        var ok = k >= 65 && k <= 90 || // A-Z
            k >= 97 && k <= 122 || k==127 || k==8 || k==32 || k==9; // a-z
     
        if (!ok){
            e.preventDefault();
        }
	}); 

	// keypress event for alphanumeric
	$('.alphaNumeric').on('keypress', function(e) {
        //var k = e.which;
        var k = (e.which) ? e.which : e.keyCode;
        var ok = k >= 48 && k <= 57 || k==127 || k==8 || // 0-9
		        k >= 65 && k <= 90 || // A-Z
	            k >= 97 && k <= 122 || k==127 || k==9; // a-z
     
        if (!ok){
            e.preventDefault();
        }
  });
 
	// keypress event for alphanumeric with space
 $('.alphaNumericWithSpace').on('keypress', function(e) {
        //var k = e.which;
	 	var k = (e.which) ? e.which : e.keyCode;
        var ok = k >= 48 && k <= 57 || k==127 || k==8 || // 0-9
		        k >= 65 && k <= 90 || // A-Z
	            k >= 97 && k <= 122 || k==127 || k==9 || k==32; // a-z
     
        if (!ok){
            e.preventDefault();
        }
  });
} 
function isZero(ele){
	var k = Number($(ele).val()) ;
 
    if (k==0 || k=='0' ){
	   	 var msg=arr_error[29];
	   	 Alert.warn(msg);
	   	 $(ele).val('');
	   	 $(ele).addClass('errorinput');
    }else{
		 $(ele).removeClass('errorinput');
    }
}

function isZero(ele){
	var k = Number($(ele).val()) ;
 
    if (k==0 || k=='0' ){
	   	 return true;
    }else{
		return false;
    }
}

function isZeroValue(value){
	var k = Number(value) ;
 
    if (k==0 || k=='0' ){
	   	 return true;
    }else{
		return false;
    }
}

