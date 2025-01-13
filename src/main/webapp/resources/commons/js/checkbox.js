$(document).ready(function(){
	$(':checkbox').change(function(){
			if(this.checked){
        	this.value="Y"
        }else{
			this.value="N"            
        }
       }
	);
});

function setAttribute(id,value){
	if("Y" == value){
		$("#"+id).attr("checked","checked")
	}else{
		$("#"+id).removeAttr("checked");
	}
	$("#"+id).val(value);
}