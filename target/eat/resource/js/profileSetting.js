$( document ).ready(function() {
	$("#saveProfileSettingBtnId").on("click", function(event) {
		event.preventDefault();
	});
	
	$("#imageUpload").change(function() {
	    readURL(this);
	});
	
	$(".color").click(function(){
	var color = $(this).attr("data-value");
	var colorClasss = $(this).attr("data-themecolor");
	$(".bgprivew").css("background", color);
	$("#themeColor").val(colorClasss);
	});
	
	
	if (Modernizr.inputtypes.color) {
	$(".picker").css("display", 'inline-block');
	var c = document.getElementById('colorpicker');
	c.addEventListener('change', function(e) {
	//d.innerHTML = c.value;
	var color = c.value;
	$(".bgprivew").css("background", color);
	}, false);
	}
       
 });

function pickColor() {
	$("#colorpicker").click();
}

function readURL(input) {
    if (input.files && input.files[0]) {
        var reader = new FileReader();
        reader.onload = function(e) {
            $('#imagePreview').css('background-image', 'url('+e.target.result +')');
            $('#imagePreview').hide();
            $('#imagePreview').fadeIn(650);
        }
        reader.readAsDataURL(input.files[0]);
    }
}

function getImage(attachmentId){
	submitToURL("getFile/"+attachmentId, "loadImage");
}

function loadImage(data){
	 $('#imagePreview').css('background-image', 'url(data:image/png;base64,'+data+')');
     $('#imagePreview').hide();
     $('#imagePreview').fadeIn(650);
}

function loadProfileSetting(){
	submitToURL("getProfileSetting", "loadProfileSettingResp");
}

function loadProfileSettingResp(data){
	if(!isEmpty(data) && !isEmpty(data.objectMap)){
		$('#no_imageUrl').val(data.objectMap.no_image);
	    var profile=data.objectMap.profile;
		if(!isEmpty(profile)){
			var profileSettingId=profile.profileSettingId;
			var urlPattern=profile.urlPattern;
			var themeColor=profile.themeColor;
			var color=$('#'+themeColor+'_themeId').data('value');
			var logo=profile.profileLogo;
			var logoUrl=logo!=null?logo.path:'';
			var logo_name=logo.name;
			var logo_path=logoUrl+'\\'+logo_name;
			$('#profileSettingForm #profileSettingId').val(profileSettingId);
			$('#profileSettingForm #urlPatternId').val(urlPattern);
			$('#profileSettingForm #themeColor').val(themeColor);
			$(".bgprivew").css("background", color);
			if(logo!=null){
				$('#profileSettingForm #profileLogoId').val(logo.attachmentId);
				loadImage(data.objectMap.ImageFile);
			}else{
				$('#profileSettingForm #profileLogoId').val('');
				 $('#imagePreview').attr("style","background-image: url('"+data.objectMap.no_image+"');");
			}
		}else{
			$('#profileSettingForm #profileSettingId').val("");
			$('#profileSettingForm #urlPatternId').val("");
			$('#imagePreview').css("background-image"," url('"+data.objectMap.no_image+"');");
			$(".bgprivew").css("background", '');
		}
	}
}

function profileLogoDeleteResp(data){
	if(!isEmpty(data)){
		if (!data.hasError) {
			$('#imageUpload').val('');
			$('#profileLogoId').val('');
			$('.profileLogoId').attr('disabled', true);
			Alert.info(data.message);
		} else {
			Alert.warn(data.message);
		}	
	}
}

function updateProfileSettingResp(data){
	if(!isEmpty(data) && !isEmpty(data.objectMap)){
		if (data.objectMap.RESULT) {
			Alert.info(data.objectMap.MESSAGE);
		} else {
			Alert.warn(data.objectMap.message);
		}	
	}
}