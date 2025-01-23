 $(function() {	
	 
    var lengthMenu;

    if ($(window).width() < 480) {
        $('.mobileNav').show();
        $.fn.DataTable.ext.pager.numbers_length = 4;       
        lengthMenu = [ 1, 5, 7, 10, ],
        [ 1, 5, 7, 10, ]
    } else {        
        lengthMenu = [ 7, 10, ],
        [ 7, 10, ]
    }
    $(".datepicker").datepicker({ 
        autoclose: true, 
        todayHighlight: true
  }).datepicker('update', new Date());
    $('#myCarousel').carousel({
        pause: 'none'
    });
    $(".Edit-Committee-button").click(function(){
        $('.EditCommittee').show();
        $('.AddSlideTab').hide();
    });
    $(".closeDiv").click(function(){
         $('.EditCommittee').hide();
         $('.AddSlideTab').show();
    });
    $('.ViewAll').click(function(){
    	
    	$('.BasicInformation').removeClass('active');
    	$('.TenderVersions').addClass('active')
    	
    });
    $('.tenderdetailsTab').click(function(){        	
    
    	$('.tenderlist').removeClass('active');
    	$('.tenderdet').addClass('active');
    });
	$('table').each(function(){		
		var text = []
		$(this).find('thead tr th').each(function(){
			text.push($(this).text())

			for (var i = 0; i < text.length; i++) {
				$(this).parents('table').find('tbody tr td:nth-of-type(' + (i + 1) +')').attr('data-th', text[i])
			}	
		});		
	});	
	$('.TenderCommitteeTable').DataTable({
		/*"scrollY": scrY,
        "scrollX": scrX,*/
        /* "pageLength": 4,*/ 
        "lengthMenu":lengthMenu
	});
	$('.TenderDetailsTable').DataTable({
	/*"scrollY": scrY,
    "scrollX": scrX,*/
    /* "pageLength": 4, */
    "lengthMenu":lengthMenu
    });
	$('.TechnicalEvaluationTable').DataTable({
	/*"scrollY": scrY,
    "scrollX": scrX,*/
    /* "pageLength": 4, */
    "lengthMenu":lengthMenu
    });
});
 (function($) {

	  'use strict';

	  $(document).on('show.bs.tab', '.nav-tabs-responsive [data-toggle="tab"]', function(e) {
	    var $target = $(e.target);
	    var $tabs = $target.closest('.nav-tabs-responsive');
	    var $current = $target.closest('li');
	    var $parent = $current.closest('li.dropdown');
			$current = $parent.length > 0 ? $parent : $current;
	    var $next = $current.next();
	    var $prev = $current.prev();
	    var updateDropdownMenu = function($el, position){
	      $el
	      	.find('.dropdown-menu')
	        .removeClass('pull-xs-left pull-xs-center pull-xs-right')
	      	.addClass( 'pull-xs-' + position );
	    };

	    $tabs.find('>li').removeClass('next prev');
	    $prev.addClass('prev');
	    $next.addClass('next');
	    
	    updateDropdownMenu( $prev, 'left' );
	    updateDropdownMenu( $current, 'center' );
	    updateDropdownMenu( $next, 'right' );
	  });

	})(jQuery);