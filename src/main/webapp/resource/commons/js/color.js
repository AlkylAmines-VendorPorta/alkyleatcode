/**
 * 
 */


$(document).ready(function(){
	$('.colorbox').on('click', function(){
		 var bgcolor=$(this).css('background-color');
		 var activeLi =$(this).attr('data-foreground');
		 var hoverbtn =$(this).attr('data-hover');
	      less.modifyVars({
	        '@bgcolor': bgcolor,
	        '@activeLi':activeLi,
	        '@hoverbtn':hoverbtn
	      });
	    });
	$('.BackImg').on('click', function(){
		 var bodyback=$(this).css('background-image');		 
	      less.modifyVars({
	        '@bodyback': bodyback,
	      });
	    });
});