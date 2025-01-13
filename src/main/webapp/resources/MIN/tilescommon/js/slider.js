var $item = $('.carousel .item'); 
var $wHeight = $(window).height();
$item.eq(0).addClass('active');
$item.height($wHeight); 
$item.addClass('full-screen');

$('.carousel img').each(function() {
  var $src = $(this).attr('src');
  var $color = $(this).attr('data-color');
  $(this).parent().css({
    'background-image' : 'url(' + $src + ')',
    'background-color' : $color
  });
  $(this).remove();
});
/*w3.includeHTML();*/
/*$('.navbar-nav').on('click', 'li', function() {
	alert('shbksb');
    $('.navbar-nav li.active').removeClass('active');
    $(this).addClass('active');
});*/	
$(window).on('resize', function (){
  $wHeight = $(window).height();
  $item.height($wHeight);
});

$('.carousel').carousel({
  interval: 6000,
  pause: "false"
});
$('#myCarousel').carousel({
    pause: 'none'
});

/*var oldX=0;
function dragRowEvent(e){
	if(e.pageX>oldX){
		$('.paginate_button.next').trigger('click');
	}else if(e.pageX<oldX){
		$('.paginate_button.previous').trigger('click');
	}
	
}

function dragStartEvent(e){
	oldX=e.pageX;
}

var oldTouch;
function touchDragRowEvent(e){
	var touches=e.changedTouches;
	if(touches[0].pageX>oldX){
		$('.paginate_button.next').trigger('click');
	}else if(touches[i]<oldX){
		$('.paginate_button.previous').trigger('click');
	}
	
}*/

function touchDragStartEvent(e){
	var touches=e.changedTouches;
	oldX=touches[0].pageX;
}