var autocollapse = function() {
  
  var tabs = $('#DashboradTab');
  var tabsHeight = tabs.innerHeight();
  
  if (tabsHeight >= 50) {
    while(tabsHeight > 50) {
      //console.log("new"+tabsHeight);
    	var children =[];
       children= tabs.children('li:not(:last-child)');
      var count = children.length;
      $(children[count-1]).prependTo('#collapsed');
      
      tabsHeight = tabs.innerHeight();
    }
  }
  else {
  	while(tabsHeight < 50 && (tabs.children('li').length>0)) {
  		var collapsed =[];
       collapsed = $('#collapsed').children('li');
      var count = collapsed.length;
      $(collapsed[0]).insertBefore(tabs.children('li:last-child'));
      tabsHeight = tabs.innerHeight();
    }
    if (tabsHeight>50) { // double chk height again
    	autocollapse();
    }
  }
  
};

$(document).ready(function() {
  $('.dropdown-toggle').click(function(){
	  $('#collapsed').toggleClass("dropdown-menuTab");
  });
  $('body').on('click', '#collapsed li a', function(e) {
	  $('#collapsed').removeClass("dropdown-menuTab");
	  $('#collapsed').addClass("dropdown-menu");
  });
  $(document).click(function(){
	  $('#collapsed').removeClass("dropdown-menuTab");
	  $('#collapsed').addClass("dropdown-menu");
	});
  
  	autocollapse(); // when document first loads

	$(window).on('resize', autocollapse); // when window is resized
  
});
